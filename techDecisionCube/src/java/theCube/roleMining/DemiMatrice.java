/*
Copyright Stéphane Georges Popoff, (février 2009) 

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
import java.util.*;
//import org.apache.log4j.Level;
/**
 * Demie matrice pour compararison de comptes
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
public class DemiMatrice {
    String sFile = null;
    Boolean bErr = false;
    String sErr = null;
    int iItems = 0;
    List<Cpt1> listC1 = null;
    Boolean bFull = false;
    public DemiMatrice(LinkedList objU, String sFichier, Boolean bFull){
        this.bFull = bFull;
        init(objU, sFichier);
    }
    public DemiMatrice(LinkedList objU, String sFichier){
        init(objU, sFichier);
    }
    private void init(LinkedList objU, String sFichier){
        int i = 0;
        int j = 0;
        sFile = sFichier;
        Iterator itCpte = null;
        try{
            itCpte = (Iterator) objU.iterator();
        } catch(Exception ex){
            bErr = true;
            sErr = "Erreur list CpteElec " + ex.toString();
            return;
        }
        Iterator itCpte2 = null;
        List objU2 = null;
        IItemElec objCpte;
        IItemElec objCpte2;
        objU2 =  (LinkedList)objU.clone();
        itCpte2 = objU2.iterator();
        Cpt1 cpt1;
        listC1 = new LinkedList();
        System.out.println( "Initialisation demie matrice*****************************");
        while(itCpte.hasNext()  ){
            i++;
            objCpte = (IItemElec)itCpte.next();
            if(!bFull){
                cpt1 = new Cpt1(objCpte.getId());
            }else{
                cpt1 = new Cpt1(objCpte.getId(),objCpte.getName());
            }
            listC1.add(cpt1);
            j = 0;
            while(itCpte2.hasNext()){
                objCpte2 = (IItemElec)itCpte2.next();
                j++ ;
                if(j>i){
                   //System.out.println( "cpt1:"+objCpte.getId()+" cpt2:"+objCpte2.getId());
                   if(!bFull){
                       cpt1.addCpt2(objCpte2.getId());
                   }else{
                       cpt1.addCpt2(objCpte2.getId(),objCpte2.getName());
                   }
                } 
            }
            itCpte2 = objU2.iterator();
        }
        iItems = i;
        System.out.println( "Initialisation terminée de "+iItems +" comptes");
    }
    public Boolean siErreur(){
        return bErr;
    }
    public String erreurTxt(){
        return sErr;
    }
    public List getListeC1(){
        return listC1;
    }
    public class Cpt1 implements IItemElec{
        private int id;
        private List<Cpt2> listC2 = null;
        private String sNom;
        public Cpt1(int id){
            this.id = id;
            listC2=new LinkedList();
        }
        public Cpt1(int id, String sNom){
            this.id = id;
            this.sNom = sNom;
            listC2=new LinkedList();
        }
        public int getId() {
            return id;
        }
        public void addCpt2(int id){
            listC2.add(new Cpt2(id));
        }
        public void addCpt2(int id, String sNom){
            listC2.add(new Cpt2(id, sNom));
        }
        public List getListeC2(){
            return listC2;
        }

        public String getIdString() {
            return null;
        }

        public String getName() {
            return sNom;
        }
    }
    public class Cpt2 implements IItemElec{
        private int id;
        private String sNom;
        public Cpt2(int id){
            this.id = id;
        }
        public Cpt2(int id, String sNom){
            this.id = id;
            this.sNom = sNom;
        }
        public int getId() {
            return id;
        }

        public String getIdString() {
            return null;
        }

        public String getName() {
            return sNom;
        }
    }
}
