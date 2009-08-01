/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testejb;
//import javax.

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import techdecisionloader.ETLlike;
//import techDecision.dao.identite.IIdentiteDaoRemote;
//import techDecision.dao.exceptions.PreexistingEntityException;
//import techDecision.dao.exceptions.RollbackFailureException;
//import techDecision.entites.Identite;
/**
 *
 * @author spopoff
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    //private static Identite idnt  = null;
    //private static IIdentiteDaoRemote dao = null;
    private static InitialContext ctx = null;
    private static final String ejbName = "ejbIdentite";

    public static void main(String[] args) {
        try{
            zzzz("papamaman");
            ETLlike instance = new ETLlike();
            try {
                instance.init("localhost", "9780");
            } catch (Exception err) {
                System.out.println(err.toString());
                System.exit(0);
            }
            String nom = "spo2";
            String dept = "dev";
            String fonction = "con";
            try {
                instance.ajouteUtilisateur(nom, dept, fonction);
            } catch (Exception err) {
                System.out.println(err.toString());
                System.exit(1);
            }
            int userId = 3243433;
            String profil = "HHH";
            String habilitants = "HHH+BBBB+CCCC";
            try {
                instance.ajouteCompte(nom, userId, profil, habilitants);
            } catch (Exception err) {
                System.out.println(err.toString());
                System.exit(1);
            }
            //init("localhost", "3721");
        }catch(Exception ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //init("localhost", "3721");
    }
    private static String zipToString(String clair) throws Exception{
          byte data[] = clair.getBytes();
          ZipOutputStream zipOut = new ZipOutputStream(new ByteArrayOutputStream());
          ZipEntry entry = new ZipEntry("/home/spopoff/bidon.zip");
          zipOut.putNextEntry(entry);
          zipOut.write(data, 0, data.length);
          String result = zipOut.toString();
          //zipOut.close();
          zipOut.finish();
          return result;
    }
    private static void zzzz(String clair){
        FileOutputStream fos = null;
        try {
           fos = new FileOutputStream("/home/spopoff/bidon.zip");
           GZIPOutputStream gz = null;
            try {
                gz = new GZIPOutputStream(fos);
                ObjectOutputStream oos = new ObjectOutputStream(gz);
                oos.writeObject(clair);
                oos.flush();
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    gz.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
/*    private static void init(String sHost, String sPort) {
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial",
                                 "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs",
                                 "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state",
                                 "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

        // optional.  Defaults to localhost.  Only needed if web server is running
        // on a different host than the appserver
        props.setProperty("org.omg.CORBA.ORBInitialHost", sHost);

        // optional.  Defaults to 3700.  Only needed if target orb port is not 3700.
        props.setProperty("org.omg.CORBA.ORBInitialPort", sPort);
        try {
            // TODO code application logic here
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
        }
        try {
            dao = (IIdentiteDaoRemote) ctx.lookup(ejbName);
        } catch (NamingException ex) {
        }
        try{
            dao.init();
        }catch(Exception err){
        }
        try {
            dao.create(new Identite(122325, "spo", "con", "maison"));
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            idnt = dao.findIdentite(122325);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        idnt.setDepartement("travail");
        try {
            dao.edit(idnt);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dao.destroy(122325);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
}
