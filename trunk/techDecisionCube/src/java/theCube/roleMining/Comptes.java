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
import theCube.entities.Habilitant;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.*;
//import org.apache.log4j.Level;
import theCube.entities.Compte;
import theCube.entities.Identite;

/**
 * Collection des comptes
 * @author spopoff@rocketmail.com
 * @version 0.5
 */
public class Comptes extends theCube.jpa.Compte3 implements IComptes {

    protected static List<ICpte> cptes = null;
    protected static List<CpteElec> cptesElec = null;
    private static Boolean bErreur = false;
    private static String sErreur = "";
    private int lastId = 0;
    private Compte lastCpte = null;
    private int iMaxCpte = 0;
    private int iLastCpteElec = 0;
    private int iNbHblt[] = new int[3];
    private Habilitants objHblts = null;
    /**
     * Contructeur unique
     * @param h la référence à la collection des habilitants
     * @param r la référence à la collection des rôles applicatifs
     * @throws java.lang.Exception
     */
    public Comptes() throws Exception {
//    	objHblts = (Habilitants)h;
//    	objRoles = (RolesApp)r;
        cptes = new LinkedList();
        System.out.println("Initialise les comptes");
    }
    /**
     * Donne le prochain identifiant de compte utilise Aleat
     * @return getNextId un entier entre 10000 et 100000 environ
     * @throws java.lang.Exception
     */
    protected int getNextId() throws Exception {
    	
        lastId =  TalendConnection.Aleat();
        return lastId;
    	
    }
    /**
     * Retourne le nombre total de comptes de la colection
     * @return getMaxCpte
     */
    public int getMaxCpte(){
        return super.getCompteCount();
    }
    public Boolean siErreur(){
        return bErreur;
    }
    public String erreurTxt(){
        return sErreur;
    }
     /**
      * Retourne tous les comptes dans une liste
      * @return getComptes une liste etous les comptes
      */
    public Collection<Compte> getComptes() {
        return super.findCompteEntities();
    }
    /**
     * Retourne tous les comptes Electre (Fait appel à comptesElec)
     * @return getComptesElec la liste des comptes Electre
     */
    public List getComptesElec() {
        if(cptesElec==null) cptesElec = comptesElec(false);
        return cptesElec;
    }
    /**
     * Retourne tous les comptes Electre (Fait appel à comptesElec)
     * @return getComptesElec la liste des comptes Electre
     */
    public List getComptesElec(Boolean full) {
        if(cptesElec==null) cptesElec = comptesElec(full);
        return cptesElec;
    }
    /**
     * Procèdure qui calcul la liste des comptes Electre (appellé une fois)
     * @return comptesElec une liste
     */
    private List comptesElec(Boolean full){
        System.out.println("Initialisation des comptes Electre (extraction à partir des comptes)");
        List results = new LinkedList<CpteElec>();
        int k = 0;
//        cptes = (List<ICpte>) super.findCompteEntities();
        Iterator<Compte> iter = super.findCompteEntities().iterator();
        CpteElec ue;
        int i = 1;
        Comparable[] tTri = null;
        ListIterator ltIt = null;
        System.out.println("Pour tous les comptes fabrication des comptes Electre pour demieMatrice");
        while (iter.hasNext()) {
            Compte u = (Compte) iter.next();
            System.out.println("Nouveau compte Electre ");
            ue = new CpteElec(u.getId(), u.getName(), u.listHabilitants().size(), u.listHabilitants().toString());
            if(full){
                for(Habilitant h : u.getHbltColl()){
                    switch(h.getType()){
                        case 17: ue.setH17(ue.getH17()+1);break;
                        case 18: ue.setH18(ue.getH18()+1);break;
                        case 19: ue.setH19(ue.getH19()+1);break;
                    }
                }
            }
            results.add(ue);
        }
        try {
            tTri = new Comparable[results.size()];
            ltIt = results.listIterator();
            tTri = (Comparable[]) results.toArray(tTri);
            java.util.Arrays.sort(tTri, new CompareElec());
            while ( ltIt.hasNext() )
            {
                ltIt.next();
                ltIt.set(tTri[k++]);
            }
        } catch(Exception err) {
            bErreur = true;
            sErreur = "Erreur tri Cpteelec "+err.toString();
            System.err.println( sErreur);
        }
        return results;

    }
    /**
    * Transcription de Public Function nbreHblt(lTipHblt As Long, lUsrId As Long) As Long
    * permet de connaitre le nombre d'habilitant pour un compte donné et un type donné
    * @param typeHablt type d'habilitant 17, 18, 19
    * @param compteId la clé du compte
    * @return nbreHblt le nombre total d'habilitant
    */
    public int nbreHblt(int typeHablt, int compteId){
        int iRep = 0;
        IItemElec e = null;
        CpteElec ee = null;
        if(iLastCpteElec==compteId){
            switch(typeHablt){
                case 17: iRep = iNbHblt[0]; break;
                case 18: iRep = iNbHblt[1]; break;
                case 19: iRep = iNbHblt[2]; break;
            }
            return iRep;
        }else{
            iLastCpteElec = compteId;
        }
        try{
        e = trouveCompteElec(compteId);
        ee = (CpteElec)e;
        } catch (Exception ex){
            System.err.println("Dans trouveCompteElec " + ex.toString());
            iRep = -1;
            return iRep;
        }
        if(e==null){
            System.err.println("ne trouve rien dans trouveCompteElec avec:" + compteId);
            iRep = -1;
            return iRep;
        }
        switch(typeHablt){
            case 17: iRep = ee.getH17(); iNbHblt[0] = iRep; break;
            case 18: iRep = ee.getH18(); iNbHblt[1] = iRep; break;
            case 19: iRep = ee.getH19(); iNbHblt[2] = iRep; break;
        }
        return iRep;
    }
 /**
 * Retourne un compte sur la base de son userId
 * @param id userId
 * @return trouveCompte un compte Cpte
 */
    public Compte trouveCompte(int id) {
    	return super.findCompte(id);
    }
 /**
 * Retourne un compte Electre sur la base de son userId
 * @param id userId
 * @return trouveCompte un compte Cpte
 */
    public IItemElec trouveCompteElec(int id) {
        CpteElec result = null;
        Iterator<CpteElec> iter = cptesElec.iterator();
        while (iter.hasNext()) {
            CpteElec u = (CpteElec) iter.next();
            if (u.id == id) {
                result = u;
            }
        }
        return result;
    }
    /**
     * Ajoute un classement à un compte
     * @param iCpte identifiant du compte
     * @param classe le classement
     */
    public void setClasse(int iCpte, String classe) {
        Compte cp = trouveCompte(iCpte);
        cp.setClasse(classe);
    }
    /**
     * Donne la fonction de la dernière identité qui est liée au compte
     * @param cpteId
     * @return fonction
     */
    public String getFonction(int cpteId){
        String ret = "";
        Compte cp = super.findCompte(cpteId);
        for(Identite idnt : cp.getIdntColl()){
            ret = idnt.getFonction();
        }
        return ret;
    }
    /**
     * Passe une référence sur les habilitants
     * @param objHblts
     */
    public void setObjHblts(Habilitants objHblts) {
        this.objHblts = objHblts;
    }
    /**
     * donne le max des habilitants de type 17
     * @return
     */
    public int getMax17(){
        return objHblts.getMax17();
    }
    /**
     * donne le max des habilitants de type 18
     * @return
     */
    public int getMax18(){
        return objHblts.getMax18();
    }
     /**
     * donne le max des habilitants de type 19
     * @return
     */
    public int getMax19(){
        return objHblts.getMax19();
    }
    /**
     * Classe de comparaison en vue de classement des comptes Electre
     */
    public static class CompareElec implements Comparator{
            public int compare(Object arg0, Object arg1) {
            CpteElec un = (CpteElec) arg0;
            CpteElec de = (CpteElec) arg1;
            if(de.iNbHbl > un.iNbHbl){
                return -1;
            } else if(de.iNbHbl < un.iNbHbl){
                return 1;
            } else{
                return de.lesHabilitants.compareTo(un.lesHabilitants);
            }
        }


    }
    /**
     * Cette classe est légèrement différente de la classe Cpte, mais est-ce une 
     * bonne idée ? Par exemple des compteurs en fonction des
     * types d'habilitant. C'est comme une synthèse des informations d'un Cpte
     */
    public static class CpteElec implements Serializable, Comparable, IItemElec {
    	
        private static final long serialVersionUID = 1;
    	
        private int id;
        private String lesHabilitants;
        private int iNbHbl;
        private String name;
        private Long lCpte;
        private int iNbH17;
        private int iNbH18;
        private int iNbH19;
        /**
         * Constructeur identique au compte
         * @param id l'identifiant du compte de référence
         * @param name le lien vrs l'identité
         * @param lCpte le lien externe
         * @param iNb le nombre d'habilitant
         * @param sHblt les habilitant en chaine
         */
        public CpteElec(int id, String name, int iNb, String sHblt) {
    		
            this.id = id;
            this.name = name;
            this.iNbHbl = iNb;
            this.lesHabilitants = sHblt;
        }
        @Override
        public String toString() {
    		
            StringBuilder sb = new StringBuilder(64);
            sb.append("|CptElec id=").append(id).append(": ").append(name).append(": (");
            sb.append(lCpte).append("): nbHblt=(").append(iNbHbl).append("): ");
            sb.append("nbH19=(").append(iNbH19).append(") nbH18=(").append(iNbH18);
            sb.append(")\n|  Habilitants=").append(lesHabilitants).append("\n");
    		
            return sb.toString();
        }
        /**
         * Retourne le nombre d'habilitant de type 17
         * @return getH17
         */
        public int getH17() {
            return this.iNbH17;
        }
        /**
         * Retourne le nombre d'habilitant de type 18
         * @return getH18
         */
        public int getH18() {
            return this.iNbH18;
        }
        /**
         * Retourne le nombre d'habilitant de type 19
         * @return getH19
         */
        public int getH19() {
            return this.iNbH19;
        }
        /**
         * Met à jour le nombre d'habilitant de type 17
         * @param i
         */
         public void setH17(int i) {
            this.iNbH17 = i;
        }
        /**
         * Met à jour le nombre d'habilitant de type 18
         * @param i
         */
        public void setH18(int i) {
            this.iNbH18 = i;
        }
        /**
         * Met à jour le nombre d'habilitant de type 19
         * @param i
         */
        public void setH19(int i) {
            this.iNbH19 = i;
        }
        /**
         * Voir IItemElec
         * @return getId
         */
        public int getId() {
            return this.id;
        }
        /**
         * Voir IItemElec
         * @return getIdString
         */
        public String getIdString() {
            return ""+this.id;
        }
        /**
         * Voir IItemElec
         * @return getName
         */
        public String getName() {
            return this.name;
        }
    	
        @Override
        public int hashCode() {
    		
            return this.id;
        }
            	
        @Override
        public boolean equals(Object obj) {
    		
            if (obj instanceof CpteElec) {
    			
                CpteElec u = (CpteElec) obj;
    			
                if (u.id == this.id) {
                    return true;
                }
    			
            }
    		
            return false;
        }

        public int compareTo(Object arg0) {
            return lesHabilitants.compareTo(arg0.toString());
        }

    }

}
