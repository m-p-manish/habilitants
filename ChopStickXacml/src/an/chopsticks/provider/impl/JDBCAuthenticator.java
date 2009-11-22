package an.chopsticks.provider.impl;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

//import sun.misc.BASE64Encoder;
import an.util.Base64;

import an.chopsticks.provider.Authenticator;
import an.chopsticks.provider.Provider;
import an.chopsticks.service.Attribute;
import an.chopsticks.service.AttributeType;
import an.chopsticks.service.AuthenticationFailedException;
import an.chopsticks.service.AuthenticationFlag;
import an.chopsticks.service.Context;
import an.chopsticks.service.Credential;
import an.chopsticks.service.DefaultContextImpl;
import an.chopsticks.service.ProviderInitializationException;
import an.chopsticks.service.Service;
import an.chopsticks.service.Subject;
import an.config.ConfigElement;
import an.control.OperationFailedException;
import an.control.Status;
import an.datatype.encryption;
import an.log.LogFactory;
import an.log.Logger;
import an.util.ConnectionPoolException;
import an.util.SimpleJDBCConnectionPool;
import an.xml.XMLGeneralException;

public class JDBCAuthenticator implements Authenticator {
    static final String ELEM_JDBCCONNECTION = "JDBCConnection";
    static final String ATTR_JDBCDRIVER = "JDBCDriver";
    static final String ATTR_CONNECTIONURL = "connectionURL";
    static final String ATTR_DBUSER = "dbUser";
    static final String ATTR_DBPASSWORD = "dbPassword";
    static final String ELEM_AUTHENTICATION = "Authentication";
    static final String ATTR_AUTHENTICATIONSQL = "authenticationSQL";
    static final String ATTR_DIGESTALG = "digestAlg";
    static final String ELEM_CONNECTIONPOOL = "ConnectionPool";
    static final String ATTR_SIZE = "size";
    static final String ATTR_TIMEOUT = "timeout";

    private MessageDigest md;
    private Base64 encoder;
    private SimpleJDBCConnectionPool pool;

    private String providerName;
    private AuthenticationFlag atnFlag;
    private String jdbcDriver;
    private String connectionURL;
    private String dbUser;
    private encryption pwdEncryption;
    private String sql;
    private String digestAlg;
    private int poolSize;
    private int timeout;

    public JDBCAuthenticator(Service service, ConfigElement config) throws ProviderInitializationException {
        try {
            loadConfigurations(config);
            System.out.println("Initializing JDBC Authentication provider '" + providerName + "' ...");
            encoder = new Base64();
            md = MessageDigest.getInstance(digestAlg);
        }
        catch (Exception e) {
            throw new ProviderInitializationException("Error occurs while initializing JDBCAuthenticator.", e);
        }
        System.out.println("JDBC Authentication provider '" + providerName + "' initialized.");
    }

    private void loadConfigurations(ConfigElement config) throws XMLGeneralException {
        providerName = (String)config.getAttributeValueByName(Provider.ATTR_PROVIDER_NAME);
        String strAtnFlag = (String)config.getAttributeValueByName(ATTR_AUTHENTICATIONFLAG);
        atnFlag = AuthenticationFlag.valueOf(strAtnFlag);
        ConfigElement jdbcConn = (ConfigElement)config.getSingleXMLElementByName(ELEM_JDBCCONNECTION);
        jdbcDriver = (String)jdbcConn.getAttributeValueByName(ATTR_JDBCDRIVER);
        connectionURL = (String)jdbcConn.getAttributeValueByName(ATTR_CONNECTIONURL);
        dbUser = (String)jdbcConn.getAttributeValueByName(ATTR_DBUSER);
        pwdEncryption = (encryption)jdbcConn.getAttributeValueByName(ATTR_DBPASSWORD);
        ConfigElement atn = (ConfigElement)config.getSingleXMLElementByName(ELEM_AUTHENTICATION);
        sql = (String)atn.getAttributeValueByName(ATTR_AUTHENTICATIONSQL);
        digestAlg = (String)atn.getAttributeValueByName(ATTR_DIGESTALG);
        ConfigElement pool = (ConfigElement)config.getSingleXMLElementByName(ELEM_CONNECTIONPOOL);
        poolSize = (Integer)pool.getAttributeValueByName(ATTR_SIZE);
        timeout = (Integer)pool.getAttributeValueByName(ATTR_TIMEOUT);
    }

    private void initializeConnectionPool() throws ConnectionPoolException {
        // TODO we should first decryption password to plain text
        System.out.println("Initializing database connection pool ...");
        pool = new SimpleJDBCConnectionPool(
                jdbcDriver, connectionURL, dbUser, pwdEncryption.getValue(), poolSize, timeout);
        System.out.println("Connection pool initialized.");
    }

    @SuppressWarnings("unchecked")
    public Context authenticate(Credential cred, Context context) throws AuthenticationFailedException {
        if (!cred.getType().equals(Credential.TYPE_ATN_NAMEPASS)) {
            throw new AuthenticationFailedException("Expected '" + Credential.TYPE_ATN_NAMEPASS + "' type credential, "
                    + " but we got '" + cred.getType() + "'");
        }

        // Get the credential object
        Map<String, Object> namePass = (Map<String, Object>)cred.getCredentialObject();
        // Retrieve information from it
        String userName = (String)namePass.get(Credential.KEY_USERNAME);
        byte[] test = (byte[])namePass.get(Credential.KEY_PASSWORD);
        // Generate the DB string
        String testInDb = encoder.encode(md.digest(test));
        // Clear sensitive data
        for (int x = 0; x < test.length; x ++) {
            test[x] = 0;
        }

        Connection conn = null;
        try {
            while (true) {
                try {
                    // If connection is not null, it is the repaired one, we will try it again.
                    if (conn == null) {
                        conn = pool.getConnection();
                    }

                    PreparedStatement ps = conn.prepareStatement(sql);
                    // pass in the 2 parameters.
                    ps.setString(1, userName);
                    ps.setString(2, testInDb);
                    ResultSet result = ps.executeQuery();
                    // If there is record returned, we consider authentication succeeded.
                    if (result.next()) {
                        // Add the subject name to response context
                        Context response = new DefaultContextImpl();
                        Attribute subjAttr = new Attribute(Subject.ATTR_SUBJECT, AttributeType.String, userName);
                        response.setAttribute(subjAttr);
                        return response;
                    }
                    else {
                        throw new AuthenticationFailedException(
                                "Authentication failed using given username and password.");
                    }
                }
                catch (SQLException sqlEx) {
                    // The connection may be broken, we are trying to repair it.
                    try {
                        Logger logger = LogFactory.getLogger();
                        logger.warn("The connection may be broken, we are trying to repair it.", sqlEx);
                        pool.repairConnection(conn);
                        continue;
                    } catch (Exception e) {
                        // The connection can't be repaired, we throw the original exception.
                        throw new AuthenticationFailedException(
                                "Error occurs while authenticating against database.", sqlEx);
                    }
                }
                catch (AuthenticationFailedException atnEx) {
                    throw atnEx;
                }
                catch (Exception e) {
                    throw new AuthenticationFailedException("Error occurs while authenticating against database.", e);
                }
            }
        }
        // We should return the connection to pool finally.
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public String getSupportedCredentialType() {
        return Credential.TYPE_ATN_NAMEPASS;
    }

    public AuthenticationFlag getFlag() {
        return atnFlag;
    }

    public String getProviderName() {
        return providerName;
    }

    public Status getStatus() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object getStatusProperty(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    public void pause() throws OperationFailedException {
        throw new OperationFailedException("We don't support pause operation on this provider.");
    }

    public void resume() throws OperationFailedException {
        throw new OperationFailedException("We don't support resume operation on this provider.");
    }

    public void shutdown() throws OperationFailedException {
        System.out.println("Shutting down JDBC authorization provider '" + providerName + "' ...");
        pool.shutdown();
        System.out.println("JDBC authorization provider '" + providerName + "' shutdown.");
    }

    public void shutdownForce() throws OperationFailedException {
        shutdown();
    }

    public void start() throws OperationFailedException {
        System.out.println("Starting JDBC authentication provider '" + providerName + "' ...");
        try {
            initializeConnectionPool();
        } catch (ConnectionPoolException e) {
            throw new OperationFailedException("Failed to start JDBC authenticator '" + providerName + "'", e);
        }
        System.out.println("JDBC authentication provider '" + providerName + "' started.");
    }
}