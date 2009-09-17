/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techdecisionloader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author spopoff
 */
public class ETLlikeTest {
    private ETLlike instance = null;
    public ETLlikeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testInit() {
        System.out.println("init");
        String sHost = "localhost";
        String sPort = "3721";
        instance = new ETLlike();
        instance.init(sHost, sPort);
    }

    @Test
    public void testAjouteUtilisateur() {
        System.out.println("ajouteUtilisateur");
        String nom = "spo";
        String dept = "dev";
        String fonction = "con";
        instance.ajouteUtilisateur(nom, dept, fonction);
    }

    @Test
    public void testAjouteCompte() {
        System.out.println("ajouteCompte");
        String nom = "spo";
        int userId = 35334;
        String profil = "HHH";
        String habilitants = "HHH+BBBB+CCCC";
        instance.ajouteCompte(nom, userId, profil, habilitants);
    }

}