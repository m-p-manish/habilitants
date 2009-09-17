/*
Copyright Stéphane Georges Popoff, (mai - juin 2009)

spopoff@rocketmail.com

Ce logiciel est un programme informatique servant à gérer des habilitations.

Ce logiciel est régi par la licence [CeCILL|CeCILL-B|CeCILL-C] soumise au droit français et
respectant les principes de diffusion des logiciels libres. Vous pouvez
utiliser, modifier et/ou redistribuer ce programme sous les conditions
de la licence [CeCILL|CeCILL-B|CeCILL-C] telle que diffusée par le CEA, le CNRS et l'INRIA
sur le site "http://www.cecill.info".

En contrepartie de l'accessibilité au code source et des droits de copie,
de modification et de redistribution accordés par cette licence, il n'est
offert aux utilisateurs qu'une garantie limitée.  Pour les mêmes raisons,
seule une responsabilité restreinte pèse sur l'auteur du programme,  le
titulaire des droits patrimoniaux et les concédants successifs.

A cet égard  l'attention de l'utilisateur est attirée sur les risques
associés au chargement,  à l'utilisation,  à la modification et/ou au
développement et à la reproduction du logiciel par l'utilisateur étant
donné sa spécificité de logiciel libre, qui peut le rendre complexe à
manipuler et qui le réserve donc à des développeurs et des professionnels
avertis possédant  des  connaissances  informatiques approfondies.  Les
utilisateurs sont donc invités à charger  et  tester  l'adéquation  du
logiciel à leurs besoins dans des conditions permettant d'assurer la
sécurité de leurs systèmes et ou de leurs données et, plus généralement,
à l'utiliser et l'exploiter dans les mêmes conditions de sécurité.

Le fait que vous puissiez accéder à cet en-tête signifie que vous avez
pris connaissance de la licence [CeCILL|CeCILL-B|CeCILL-C], et que vous en avez accepté les
termes.
 */
package com.spopoff.db3;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.tools.RunScript;
//import org.h2.engine.Session;
import org.h2.jdbc.JdbcConnection;
import org.h2.message.Trace;

/**
 * Cette classe permet le contrôle du datamart TechDecision.
 * @author spopoff@rocketmail.com
 * @version 0.3
 */
public class Datamart {

    private JdbcConnection conn = null;
    private InputStream in = null;
    private String req = "";
    private boolean empty = true;
    private boolean logMax = true;
    private String jdbcUrl = "";
    private String userName = "";
    private String password = "";
    private int numConn = 0;
    private Trace trace = null;

    public void setKonn(Connection konn) {
        this.conn = (JdbcConnection)konn;
    }
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public boolean isLogMax() {
        return logMax;
    }

    public void setLogMax(boolean logMax) {
        this.logMax = logMax;
    }
    private void setTraceLevel(){
        if(logMax){
            execSet("SET TRACE_LEVEL_FILE 3;");
            execSet("SET TRACE_LEVEL_SYSTEM_OUT 3;");
        }else{
            execSet("SET TRACE_LEVEL_FILE 0;");
            execSet("SET TRACE_LEVEL_SYSTEM_OUT 0;");
        }
    }
    /**
     * Initialise la base de donnée en mémoire
     * @throws java.lang.Exception
     */
    public void initDb() throws Exception {
        System.out.println("****** Initialisation DB3 dans Datamart");
        Class.forName("org.h2.Driver");
        conn = (JdbcConnection)DriverManager.getConnection(jdbcUrl,userName,password);
        trace = conn.getSession().getTrace();
        execSet("SET DB_CLOSE_DELAY 0;");
        if(!testSchema("SPOPOFF")){
            if (chargeSchema() == null) {
                trace.info("erreur sur chargement createDB3.sql "
                        + getClass().getPackage().getName());
            } else {
                setTraceLevel();
                RunScript.execute(conn, new InputStreamReader(in));
            }
            System.out.println("****** Nouvelle base:");
        }else{
            System.out.println("****** TRUNCATE tout:");
            execSet("SET LOG 0;");
            execSet("SET UNDO_LOG 0;");
            if (chargeTruncate() == null) {
                trace.info("erreur sur chargement truncateTables.sql "
                        + getClass().getPackage().getName());
            } else {
                setTraceLevel();
                RunScript.execute(conn, new InputStreamReader(in));
            }
            trace.info("****** Base vide:"+numConn);
        }
        conn.close();
        trace.info("****** Fin Initialisation DB3 dans Datamart");
    }
    /**
     * Charge le schéma dans un flux
     * @return
     */
    private InputStream chargeSchema(){
        in = getClass().getResourceAsStream("createDB3.sql");
        return in;
    }
    /**
     * Charge le fichier pour vider les tables
     * @return
     */
    private InputStream chargeTruncate(){
        in = getClass().getResourceAsStream("truncateTables.sql");
        return in;
    }
    /**
     * Met à jour une variable
     * @param set
     */
    private void execSet(String set){
        Statement stat = null;
        try {
            stat = conn.createStatement();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return;
        }
        try{
        stat.execute(set);
        }catch(Exception err){
            System.err.println(err.toString());
        }
    }
    /**
     * Met à jour une variable
     * @param set
     */
    private Boolean testSchema(String schema){
        Statement stat = null;
        Boolean ret = false;
        ResultSet rs = null;
        String set = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME='"+schema+"';";
        try {
            stat = conn.createStatement();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return false;
        }
        try{
            rs = stat.executeQuery(set);
        }catch(Exception err){
            System.err.println(err.toString());
        }
        try{
            while (rs.next ()){
                if(rs.getString(1).equals(schema)){
                    ret = true;
                }
            }
        }catch(SQLException err){
            System.err.println(err.toString());
        }
        return ret;
    }
    /**
     * Ferme la connexion à la base pas la base elle même
     */
    public void closeDb(){
        try {
            trace.info("****** Ferme connexion:"+numConn);
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    /**
     * met à zéro la pile de requête
     */
    public void clearRequest(){
        req = "";
    }
    /**
     * ajoute une requête
     * @param reqq
     */
    public void addRequest(String reqq){
        trace.info("****** ajoute une requête:"+reqq);
        req += reqq+";";
    }
    /**
     * Fait la somme des requêtes et soumet
     */
    public void submitRequest(){
        try {
            StringReader inr = new StringReader(req);
            execSet("SET LOG 0");
            execSet("SET UNDO_LOG 0");
            setTraceLevel();
            RunScript.execute(conn, inr);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }finally{
            //execSet("SET LOG 1");
            //execSet("SET UNDO_LOG 1");

        }
    }
    public ResultSet submitSelect(String sel){
        trace.info("Execute une série de requêtes ...");
        ResultSet rs = null;
        try {
            if(logMax){
                System.out.println(sel);
            }
            StringReader inr = new StringReader(sel);
            setTraceLevel();
            rs = RunScript.execute(conn, inr);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }finally{
            if(logMax){
                System.out.println(rs.toString());
            }
            return rs;
        }

    }
}
