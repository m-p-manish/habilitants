/*
Copyright Stéphane Georges Popoff, (février - juin 2009)

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

package theCube.roleMining;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Level;
import theCube.entities.Habilitant;


/**
 * Container des habilitants uniques pour datamart H2
 * @author spopoff@rocketmail.com
 * @version 0.3
 */
public class Habilitants extends theCube.jpa.Habilitant3 {
    // This would be equivalent to a table name...
    protected List<Habilitant> habilitants = null;
    private Boolean bErreur = false;
    private String sErreur = "";
    private Habilitant lastHblt = null;
    private int lastId = 0;
    private static int iMax17 = 0;
    private static int iMax18 = 0;
    private static int iMax19 = 0;
    
    public Habilitants() throws Exception {
    	
        habilitants = new LinkedList();
        System.out.println("Initialise Tous les Habilitants");
    }

    public IHabilitant trouveHabilitant(String nom) {
        return (IHabilitant) super.findHabilitant(nom);
    }

    public boolean existHabilitant(String nom){
        boolean match = false;
        if(super.findHabilitant(nom)!=null) match = true;
        return match;
    }    
    public int getMax17(){
        if(iMax17 == 0) iMax17 = super.getTypeHabilitantCount(17);
        return iMax17;
    }
    public int getMax18(){
        if(iMax18 == 0) iMax18 = super.getTypeHabilitantCount(18);
        return iMax18;
    }
    public int getMax19(){
        if(iMax19 == 0) iMax19 = super.getTypeHabilitantCount(19);
        return iMax19;
    }
    public Iterator<Habilitant> getHabilitants() {
    	
        return super.findHabilitantEntities().iterator();
        
    }
    /**
     * Ebregistre dans un fichier les habilitants
     * @param sFile
     * @param bAppend
     * @throws java.io.IOException
     */
    public void cloze(String sFile, Boolean bAppend) throws IOException{
        String str="**** Habilitants ****\n";
        Writer output = null;
        try{
           for(Habilitant h: super.findHabilitantEntities()){
                str+=h.toString()+" ";
           }
           System.out.println("Serialization terminée");
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        try {
            output = new BufferedWriter(new FileWriter(sFile, bAppend));
              //FileWriter always assumes default encoding is OK!
            output.write(str);
        } finally {
            output.close();
        }
            
    }
    
}
