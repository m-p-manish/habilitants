package an.chopsticks.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import an.config.ConfigElement;

/**
 * We should design a session manager to keep all session information, such as a session-subject table.
 */
public final class SessionManager {
    static final String ATTR_PERSISTENTABLE = "persistentable";
    static final String ATTR_SIZE = "size";
    static final String ATTR_TIMEOUT = "timeout";
    static final String ATTR_TIMEOUTTYPE = "timeoutType";

    static final String TYPE_ACCESS = "access";
    static final String TYPE_CREATION = "creation";

    private static Map<ServiceManager, SessionManager> sessionMgrReg = new HashMap<ServiceManager, SessionManager>();

    private Map<Object, Session> sessions = new Hashtable<Object, Session>();
    private boolean persistentable;
    private int size;
    private long timeout;
    private boolean fromAccess = true;

    public synchronized static SessionManager getInstance(ServiceManager svcMgr) {
        SessionManager mgr = sessionMgrReg.get(svcMgr);
        if (mgr == null) {
            mgr = new SessionManager(svcMgr);
            sessionMgrReg.put(svcMgr, mgr);
        }
        return mgr;
    }

    private SessionManager(ServiceManager svcMgr) {
        ConfigElement config = svcMgr.getSessionManagerConfiguration();
        loadConfigurations(config);
    }

    private void loadConfigurations(ConfigElement config) {
        persistentable = (Boolean)config.getAttributeValueByName(ATTR_PERSISTENTABLE);
        size = (Integer)config.getAttributeValueByName(ATTR_SIZE);
        timeout = (Long)config.getAttributeValueByName(ATTR_TIMEOUT);
        String timeoutType = (String)config.getAttributeValueByName(ATTR_TIMEOUTTYPE);
        if (timeoutType.equalsIgnoreCase(TYPE_CREATION)) {
            fromAccess = false;
        }
    }

    public synchronized Session getSession() throws SessionSizeExceedCapacityException {
        // New a session
        return addNewSession(null);
    }

    public synchronized Session getSession(Object id) throws SessionSizeExceedCapacityException {
        // Get an existing session by clientSession.
        Session session = sessions.get(id);
        // If no session exist, we create one.
        if (session == null) {
            session = addNewSession(id);
        }
        // If exists, but invalid, we discard the original one and create a new one
        else if (!session.isValid()) {
            invalidateSession(session);
            session = addNewSession(id);
        }
        return session;
    }

    private Session addNewSession(Object id) throws SessionSizeExceedCapacityException {
        if (size >= 0 && sessions.size() == size) {
            // First clean up some invalid sessions.
            removeInvalidSessions(100);
            if (sessions.size() == size) {
                throw new SessionSizeExceedCapacityException("We have reached the capacity of Session Manager, " +
                		"we current only support " + size + " sessions in manager.");
            }
        }
        // Add a new session.
        Session session;
        if (id == null) {
            session = new DefaultSessionImpl(timeout, fromAccess);
        }
        else {
            session = new DefaultSessionImpl(id, timeout, fromAccess);
        }
        sessions.put(session.getSessionId(), session);
        return session;
    }

    private void removeInvalidSessions(int num) {
        Collection<Session> all = sessions.values();
        int removed = 0;
        for (Session each : all) {
            if (removed < num) {
                if (!each.isValid()) {
                    invalidateSession(each);
                }
            }
        }
    }

    private void invalidateSession(Session aSession) {
        aSession.invalidate();
        sessions.remove(aSession.getSessionId());
    }

    public synchronized void shutdown() {
        Collection<Session> all = sessions.values();
        for (Session each : all) {
            each.invalidate();
        }
        sessions.clear();
    }

    /**
     * Load session from underlying persistence layer.
     * @param session
     * @return
     * @throws SessionPersistenceException 
     */
    protected Session loadSession(Session session) throws SessionPersistenceException {
        if (persistentable) {
            // TODO
            return null;
        }
        else {
            // throw an exception
            throw new SessionPersistenceException("");
        }
    }

    protected void saveSession(Session session) throws SessionPersistenceException {
        if (persistentable) {
            // TODO
        }
        else {
            // throw an exception
            throw new SessionPersistenceException("");
        }
    }

    protected void deleteSession(Session session) throws SessionPersistenceException {
        if (persistentable) {
            // TODO
        }
        else {
            // throw an exception
            throw new SessionPersistenceException("");
        }
    }

    /*packaged*/ static void cleanup() {
        Collection<SessionManager> mgrs = sessionMgrReg.values();
        for (SessionManager each : mgrs) {
            each.shutdown();
        }
        sessionMgrReg.clear();
    }

    /*packaged*/ static void cleanup(ServiceManager svcMgr) {
        SessionManager mgr = sessionMgrReg.get(svcMgr);
        if (mgr != null) {
            mgr.shutdown();
        }
        sessionMgrReg.remove(svcMgr);
    }
}