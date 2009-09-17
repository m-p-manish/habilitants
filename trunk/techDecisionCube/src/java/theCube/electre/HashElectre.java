/*
Copyright Stéphane Georges Popoff, (février - juillet 2009)

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
package theCube.electre;
import theCube.roleMining.DemiMatrice;
import theCube.roleMining.DemiMatrice.Cpt1;
import theCube.roleMining.DemiMatrice.Cpt2;
import theCube.roleMining.Identites;
import theCube.roleMining.IItemElec;
import theCube.entities.Identite;
import java.util.*;
import java.io.*;
//import theCube.roleMining.TalendConnection;
//import org.apache.log4j.Level;


/**
 * Classe qui supporte les données et méthodes (getter et setter)  spécifiques à Electre<br>
 * On fonctionne dans le cadre d'un tableau à deux dimensions qui permet de faire toutes les
 * comparaisons entre tous les comptes, mais en fait on ne travaille que sur la moitié du tableau
 * et sans la médiane car on ne compare pas un compte à lui même<br>
 * <table><tbody><tr><th></th><th>A</th><th>B</th><th>C</th><th>D</th></tr><tr><td>A</td><td>-</td><td>X</td><td>X</td><td>X</td></tr>
 * <tr><td>B</td><td>-</td><td>-</td><td>X</td><td>X</td></tr><tr><td>C</td><td>-</td><td>-</td><td>-</td><td>X</td></tr><tr><td>D</td><td>-</td><td>-</td><td>-</td><td>-</td></tr></tbody></table><br/>
 * Donc nous aurons des traitement sur les lignes: un compte de rang1 cpt1 (ligne) et les comptes de rang2 cpt2 (colonnes)
 * mais aussi des traitements sur les colonnes, ce qui pose des problèmes de références car nous n'avons pas toutes
 * les cases du tableau, donc quand une case est vide dans une colonne on va la chercher en symétrie par rapport
 * à la diagonale
 * Attention la notion de compte n'est pas à prendre au pied de la lettre car cette méthode peut s'appliquer
 * A des identités de la même manière il aurait mieux valut parler d'items.
 * 
 * @author Stéphane Popoff
 * @version 0.5
 */
public class HashElectre {
    private Map lesComptes1 = new HashMap();
    private List listComptes1 = new LinkedList();
    private String sErr = "";
    private Boolean bErr = false;
    private int iItems = 0;//
    private String[] lesAtt  = {"description","uid","cpteId","id","","SommeStricte1",
        "FormuleSuperieur1","FormuleSeuil1","TabConcordanceHaut","note","TabConcordanceSomL","TabConcordanceClass",
        "FlagProdIncomp","TabConcordanceSomC","Soustrait","SommeStricte2","FormuleSuperieur2","FormuleSeuil2",
        "TabConcordanceBas","lesClasses","leClassement"};
    private final String csCn = "uid";
    private final String csAnn = "description";
    private final String csCid = "cpteId";
    private final String csId = "id";
    private final String csSS1 = "SommeStricte1";
    private final String csFS1 = "FormuleSuperieur1";
    private final String csFse1 = "FormuleSeuil1";
    private final String csTcO = "TabConcordanceHaut";
    private final String csNt = "note";
    private final String csTc1 = "TabConcordanceSomL";
    private final String csTcc = "TabConcordanceClass";
    private final String cs1c = "FlagProdIncomp";
    private final String csTck = "TabConcordanceSomC";
    private final String csKls = "Soustrait";
    private final String csSS2 = "SommeStricte2";
    private final String csFS2 = "FormuleSuperieur2";
    private final String csFse2 = "FormuleSeuil2";
    private final String csTcB = "TabConcordanceBas";
    private final String csKplus = "leClassement";
    private String sFile = "";
    private String rapport = "";

    public String getRapport() {
        return rapport;
    }
    /**
     * Procèdure d'initialisation du container de données
     * @param objMat un objet qui contient une demie matrice de comparaison (voir DemiMatrice)
     * @param sFichier le nom et le chemin d'accès au fichier de sauvegarde des données
     */
    public void init(DemiMatrice objMat, String sFichier){
        int i = 0;
        int j = 0;
        sFile = sFichier;
        ICompte1 cpt1 = null;
        ICompte2 cpt2 = null;
        System.out.println("Initialisation matrice Hash Electre *****************************");
        for(Cpt1 cc1: (List<Cpt1>)objMat.getListeC1()){
        //for(itCpte2 = objU2.iterator();itCpte.hasNext();){
            try{
                i++;
                cpt1 = new Compte1(cc1.getId(),cc1.getName());
                listComptes1.add(cpt1);
                Object put = lesComptes1.put(cc1.getId(), (ICompte1)cpt1);
                j = 0;
                
            }catch(Exception err){
                System.err.println("erreur boucle 1 à i="+i+" "+err.toString());
            }
           for(Cpt2 cc2 : (List<Cpt2>)cc1.getListeC2()){
                j++ ;
                //System.out.println("cpt1:"+cc1.getId()+" cpt2:"+cc2.getId());
                try{
                    cpt2 = (ICompte2)cpt1.addCompte2(cc2.getId(),cc2.getName());
                    //pourquoi 2 et 1 ?
                    cpt2.setTabConcordanceHaut(2);
                    cpt2.setTabConcordanceBas(1);
                }catch(Exception err){
                    System.err.println("erreur boucle 2 à j="+j+" "+err.toString());
                }
            }
        }
    iItems = i;
    System.out.println("Initialisation terminée de "+iItems+" compte1 taille "+lesComptes1.size());
    }
    /**
     * Procèdure de sérialisation des données Electre
     */
    public void cloze() throws IOException{
        String str="";
        Writer output = null;
        try{
            Iterator it1 = itLesComptes().iterator();
            Iterator it2 = null;
            while(it1.hasNext()){
                Compte1 cpt1 = (Compte1)it1.next();
                it2 = cpt1.listComptes2.iterator();
                str=str+cpt1.toString();
                while(it2.hasNext()){
                    Compte2 cpt2 = (Compte2)it2.next();
                    str=str+cpt2.toString();
                }
            }
            System.out.println( "Serialization terminée");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        rapport = str;
        try {
            output = new BufferedWriter(new FileWriter(sFile, false));
              //FileWriter always assumes default encoding is OK!
            output.write(str);
        } finally {
            output.close();
        }
            
    }

    /**
     * Fonction pour obtenir une référence à un compte de rang1
     * @param id le code du compte
     * @return un compte de rang1
     */
    public ICompte1 getCompte1(int id){
        return (ICompte1)lesComptes1.get(id);
    }
    /**
     * Fonction pour obtenir le nombre de comptes de rang 1
     * @return getItems un compte de rang1
     */
    public int getItems(){
        /* method return value */
        return iItems;
    }
    /**
     * Fonction pour obtenir la liste des comptes1
     * @return itLesComptes la liste des comptes de rang1
     */
    public List itLesComptes(){
        return listComptes1;
    }
    /**
     * Fonction pour lire les données du tableau de concordance
     * Cas 0 si 1 signifie que cp1 est meilleur que cp2, sinon 0 que cp1 et cp2 sont indifférents<br>
     * Cas 1 si 1 signifie que cp2 est meilleur que cp1, sinon 0 que cp1 et cp2 sont indifférents<br>
     * Cas 4 le total des surclassements pour un compte de rang1 sur ses comptes de rang2 (en ligne)
     * Cas 5 le total des surclassements d'un compte pour tous ceux de sa colonne
     * Cas 6 la différence entre le cas 5 et 4 ce qui donne le classement
     * Cas 9 le numéro de la classe en partant de 1 issue du précédent
     * @param iP1 code du compte de rang1
     * @param iDecal code le l'information demandée 0=csTcO = "TabConcordanceHaut"; 1=csTcB = "TabConcordanceBas";
     * 4=csTc1 = "TabConcordanceSomL"; 5=csTck = "TabConcordanceSomC"; 6=csKls = "Soustrait"; 9=csKlss = "lesClasses"
     * @param iP2 code du compte de rang2
     * @return tabConcordance un chiffre ou un texte
     */
    public Object tabConcordance(int iP1, int iDecal, int iP2){
        Object tabConcordance_$=0;
        String sSel="";
        String sAtt;//
        bErr = false;
        sSel=""+iP1+"/"+iP2 ;
        switch(iDecal){
            case 0://0=csTcO = "TabConcordanceHaut"
                if(iP2==iP1){
 //rien
                }else{
                        sAtt = csTcB;
                    try{
                        tabConcordance_$ = (Integer)getAttribut(sSel, sAtt);
                    }catch(Exception err){
                        System.err.println(" Err_getTabConcordance0 " +err.toString());
                        bErr = true;
                        sErr = "Err_getTabConcordance0 " +err.toString();
                        return -1;
                    }
                }
            break;
            case 1://1=csTcB = "TabConcordanceBas"
                if(iP2==iP1){
 //rien
                }else{
                        sAtt = csTcO;
                    try{
                        tabConcordance_$ = (Integer)getAttribut(sSel, sAtt);
                    }catch(Exception err){
                        System.err.println(" Err_setTabConcordance0 " +err.toString());
                        bErr = true;
                        sErr = "Err_setTabConcordance0 " +err.toString();
                        return -1;
                    }
                }
            break;
            case 4://4=csTc1 = "TabConcordanceSomL"
                sSel = ""+iP1 ;
                sAtt = csTc1;
                try{
                    tabConcordance_$ = (Integer)getAttribut(sSel, sAtt);
                }catch(Exception err){
                    System.err.println(" Err_setTabConcordance4 " +err.toString());
                    bErr = true;
                    sErr = "Err_setTabConcordance4 " +err.toString();
                    return -1;
                }
            break;
            case 5://total colonne 5=csTck = "TabConcordanceSomC"
                sSel = ""+iP1 ;
                sAtt = csTck;
                try{
                    tabConcordance_$ = (Integer)getAttribut(sSel, sAtt);
                }catch(Exception err){
                    System.err.println(" Err_setTabConcordance5 " +err.toString());
                    bErr = true;
                    sErr = "Err_setTabConcordance5 " +err.toString();
                    return -1;
                }
            break;
            case 6://classement 6=csKls = "Soustrait"
                sSel = ""+iP1 ;
                sAtt = csKls;
                try{
                    tabConcordance_$ = (Integer)getAttribut(sSel, sAtt);
                }catch(Exception err){
                    System.err.println(" Err_setTabConcordance6 " +err.toString());
                    bErr = true;
                    sErr = "Err_setTabConcordance6 " +err.toString();
                    return -1;
                }
            break;
            case 9://les classes plus 9=csKlss = "lesClasses"
                sSel = ""+iP1 ;
                sAtt = csKplus;
                try{
                    tabConcordance_$ = (String)getAttribut(sSel, sAtt);
                }catch(Exception err){
                    System.err.println(" Err_setTabConcordance6 " +err.toString());
                    bErr = true;
                    sErr = "Err_setTabConcordance6 " +err.toString();
                    return -1;
                }
            break;
        }
        if(bErr){
            sErr = "Err_setTabConcordance car:" +sErr;
        }
        return tabConcordance_$;
    }
    /**
     * Fonction pour écrire les données du tableau de concordance<br>
     * Cas 0 si 1 signifie que cp1 est meilleur que cp2, sinon 0 que cp1 et cp2 sont indifférents<br>
     * Cas 1 si 1 signifie que cp2 est meilleur que cp1, sinon 0 que cp1 et cp2 sont indifférents<br>
     * Cas 4 le total des surclassements pour un compte de rang1 sur ses comptes de rang2 (en ligne)
     * Cas 5 le total des surclassements d'un compte pour tous ceux de sa colonne
     * Cas 6 la différence entre le cas 5 et 4 ce qui donne le classement
     * Cas 9 le numéro de la classe en partant de 1 issue du précédent
     * @param iP1 code du compte de rang1
     * @param iDecal code le l'information demandée 0=csTcO = "TabConcordanceHaut"; 1=csTcB = "TabConcordanceBas";
     * 4=csTc1 = "TabConcordanceSomL"; 5=csTck = "TabConcordanceSomC"; 6=csKls = "Soustrait"; 9=csKlss = "lesClasses"
     * @param iP2 code du compte de rang2
     * @param iVal la valeur numérique à écrire
     */
    public void setTabConcordance(int iP1, int iDecal, int iP2, int iVal){
        String sSel="" ;
        String sAtt ;//
        bErr = false;
        sSel=""+iP1+"/"+iP2 ;
            /*Select Case iDecal */
        switch(iDecal){
            case 0://tableau en ligne en colonne sur deux moitiés
                if(iP2==iP1){
 //rien
                }else{
                        sAtt = csTcB;
                    try{
                        setAttribut(sSel, sAtt, iVal);
                    }catch(Exception err){
                        System.err.println(" Err_setTabConcordance0 " +err.toString());
                        bErr = true;
                        sErr = "Err_setTabConcordance0 " +err.toString();
                        return;
                    }
                }
            break;
            case 1://tableau en ligne en colonne sur deux moitiés
                if(iP2==iP1){
 //rien
                }else{
                        sAtt = csTcO;
                    try{
                        setAttribut(sSel, sAtt, iVal);
                    }catch(Exception err){
                        System.err.println(" Err_setTabConcordance0 " +err.toString());
                        bErr = true;
                        sErr = "Err_setTabConcordance0 " +err.toString();
                        return;
                    }
                }
            break;
            case 4: //total des surclssements en ligne
                sSel = ""+iP1 ;
                sAtt = csTc1;
                try{
                    setAttribut(sSel, sAtt, iVal);
                }catch(Exception err){
                    System.err.println(" Err_setTabConcordance4 " +err.toString());
                    bErr = true;
                    sErr = "Err_setTabConcordance4 " +err.toString();
                    return;
                }
            break;
            case 5://total colonne
                sSel = ""+iP1 ;
                sAtt = csTck;
                try{
                    setAttribut(sSel, sAtt, iVal);
                }catch(Exception err){
                    System.err.println(" Err_setTabConcordance5 " +err.toString());
                    bErr = true;
                    sErr = "Err_setTabConcordance5 " +err.toString();
                    return;
                }
            break;
            case 6://classement
                sSel = ""+iP1 ;
                sAtt = csKls;
                try{
                    setAttribut(sSel, sAtt, iVal);
                }catch(Exception err){
                    System.err.println(" Err_setTabConcordance6 " +err.toString());
                    bErr = true;
                    sErr = "Err_setTabConcordance6 " +err.toString();
                    return;
                }
            break;
            case 9://les classes plus
                sSel = ""+iP1 ;
                sAtt = csKplus;
                try{
                    setAttribut(sSel, sAtt, iVal);
                }catch(Exception err){
                    System.err.println(" Err_setTabConcordance6 " +err.toString());
                    bErr = true;
                    sErr = "Err_setTabConcordance6 " +err.toString();
                    return;
                }
            break;
        }
        if(bErr){
            sErr = "Err_setTabConcordanceXX" +iDecal + "car:" +sErr;
        }
    }
    /**
     * Fonction pour lire l'attribut csFse2 = "FormuleSeuil2" (valeur du poids obtenu dans un duel ou cp2 =< cp1)
     * @param iP1 code du compte de rang1
     * @param iP2 code du compte de rang2
     * @return formuleSeuil2 la valeur numérique
     */
    public Double formuleSeuil2(int iP1, int iP2){
        Double formuleSeuil_$ = 0.0;
        String sSel=""+iP1+"/"+iP2;
        try{
            formuleSeuil_$ = (Double)getAttribut(sSel, csFse2);
        }catch(Exception err){
            System.err.println(" Err_formuleSeuil2 " +err.toString());
            bErr = true;
            sErr = "Err_formuleSeuil2 " +err.toString();
            return -1.0;
        }
        if(bErr)formuleSeuil_$ = -1.0;
        return formuleSeuil_$;
    }
    /**
     * Fonction pour lire l'attribut csFse1 = "FormuleSeuil1" (valeur du poids obtenu dans un duel ou cp1 >= cp2)
     * @param iP1 code du compte de rang1
     * @param iP2 code du compte de rang2
     * @return formuleSeuil2 la valeur numérique
     */
    public Double formuleSeuil1(int iP1, int iP2){
        Double formuleSeuil_$ = 0.0;
        String sSel=""+iP1+"/"+iP2;
        try{
            formuleSeuil_$ = (Double)getAttribut(sSel, csFse1);
        }catch(Exception err){
            System.err.println(" Err_formuleSeuil1 " +err.toString());
            bErr = true;
            sErr = "Err_formuleSeuil1 " +err.toString();
            return -1.0;
        }
        if(bErr)formuleSeuil_$ = -1.0;
        return formuleSeuil_$;
    }
    /**
     * Fonction pour écrire l'attribut csFse2 = "FormuleSeuil2" (valeur du poids obtenu dans un duel ou cp2 <= cp1)
     * @param iP1 code du compte de rang1
     * @param iP2 code du compte de rang2
     * @param iVal le poids obtenu
     */
    public void setFormuleSeuil2(int iP1, int iP2, double iVal){
        String sSel=""+iP1+"/"+iP2;
        try{
            setAttribut(sSel, csFse2, iVal);
        }catch(Exception err){
            System.err.println(" Err_setFormuleSeuil2 " +err.toString());
            bErr = true;
            sErr = "Err_setFormuleSeuil2 " +err.toString();
        }
    }
    /**
     * Fonction pour écrire l'attribut csFse1 = "FormuleSeuil1" (valeur du poids obtenu dans un duel ou cp1 >= cp2)
     * @param iP1 code du compte de rang1
     * @param iP2 code du compte de rang2
     * @param iVal le poids obtenu
     */
    public void setFormuleSeuil1(int iP1, int iP2, double iVal){
        String sSel=""+iP1+"/"+iP2;//
        try{
            setAttribut(sSel, csFse1, iVal);
        }catch(Exception err){
            System.err.println(" Err_setFormuleSeuil1 " +err.toString());
            bErr = true;
            sErr = "Err_setFormuleSeuil1 " +err.toString();
        }
    }
    /**
     * Fonction pour lire l'attribut csFS2 = "FormuleSuperieur2" 1 indique que cp1 à gagné sur cp2;
     * 0 indique que cp1 a perdu contre cp2
     * @param iP1 code du compte de rang1
     * @param iP2 code du compte de rang2
     * @return formuleSuperieur2 1 ou 0
     */
    public int formuleSuperieur2(int iP1, int iP2){
        int formuleSuperieur_$ = 0;
        String sSel=""+iP1+"/"+iP2;
        try{
            formuleSuperieur_$ = (Integer)getAttribut(sSel, csFS2);
        }catch(Exception err){
            System.err.println(" Err_formuleSuperieur2 " +err.toString());
            bErr = true;
            sErr = "Err_formuleSuperieur2 " +err.toString();
            return -1;
        }
        if(bErr)formuleSuperieur_$ = -1;
        return formuleSuperieur_$;
    }
    /**
     * Fonction pour lire l'attribut csFS1 = "FormuleSuperieur1" 1 indique que cp2 a gagné sur cp1;
     * 0 indique que cp2 à perduu contre cp1
     * @param iP1 code du compte de rang1
     * @param iP2 code du compte de rang2
     * @return formuleSuperieur1 1 ou 0
     */
    public int formuleSuperieur1(int iP1, int iP2){
        int formuleSuperieur_$ = 0;
        String sSel=""+iP1+"/"+iP2;
        try{
            formuleSuperieur_$ = (Integer)getAttribut(sSel, csFS1);
        }catch(Exception err){
            System.err.println(" Err_formuleSuperieur1 " +err.toString());
            bErr = true;
            sErr = "Err_formuleSuperieur1 " +err.toString();
            return -1;
        }
        if(bErr)formuleSuperieur_$ = -1;
        return formuleSuperieur_$;
    }
    /**
     * Fonction pour écrire l'attribut csFS2 = "FormuleSuperieur2" 1 indique que cp1 à gagné sur cp2;
     * 0 indique que cp1 a perdu contre cp2
     * @param iP1 code du compte de rang1
     * @param iP2 code du compte de rang2
     * @param iVal 1 ou 0
     */
    public void setFormuleSuperieur2(int iP1, int iP2, int iVal){
        String sSel=""+iP1+"/"+iP2;//
        try{
            setAttribut(sSel, csFS2, iVal);
        }catch(Exception err){
            System.err.println(" Err_setFormuleSuperieur2 " +err.toString());
            bErr = true;
            sErr = "Err_setFormuleSuperieur2 " +err.toString();
        }
    }
    /**
     * Fonction pour lire l'attribut csFS1 = "FormuleSuperieur1" 1 indique que cp2 a gagné sur cp1;
     * 0 indique que cp2 à perduu contre cp1
     * @param iP1 code du compte de rang1
     * @param iP2 code du compte de rang2
     * @param iVal 1 ou 0
     */
    public void setFormuleSuperieur1(int iP1, int iP2, int iVal){
        String sSel=""+iP1+"/"+iP2;//
        try{
            setAttribut(sSel, csFS1, iVal);
        }catch(Exception err){
            System.err.println(" Err_setFormuleSuperieur1 " +err.toString());
            bErr = true;
            sErr = "Err_setFormuleSuperieur1 " +err.toString();
        }
    }
    /**
     * Fonction pour lire l'attribut csSS1 = "SommeStricte1" qui totalise tous les poids obtenus
     * dans le cas (cp1 > cp2)
     * @param iP1 code du compte de rang1
     * @param iP2 code du compte de rang2
     * @return sommeStricte1 la somme des poids
     */
    public int sommeStricte1(int iP1, int iP2){
        int sommeStricte_$ = 0;
        String sSel=""+iP1+"/"+iP2;
        try{
            sommeStricte_$ = (Integer)getAttribut(sSel, csSS1);
        }catch(Exception err){
            System.err.println(" Err_sommeStricte1 " +err.toString());
            bErr = true;
            sErr = "Err_sommeStricte1 " +err.toString();
            return -1;
        }
        if(bErr)sommeStricte_$ = -1;
        return sommeStricte_$;
    }
    /**
     * Fonction pour lire l'attribut csSS2 = "SommeStricte2" qui totalise tous les poids obtenus
     * dans le cas (cp2 > cp1)
     * @param iP1 code du compte de rang1 cp1
     * @param iP2 code du compte de rang2 cp2
     * @return sommeStricte2 la somme des poids
     */
    public int sommeStricte2(int iP1, int iP2){
        int sommeStricte_$ = 0;
        String sSel=""+iP1+"/"+iP2;
        try{
            sommeStricte_$ = (Integer)getAttribut(sSel, csSS2);
        }catch(Exception err){
            System.err.println(" Err_sommeStricte2 " +err.toString());
            bErr = true;
            sErr = "Err_sommeStricte2 " +err.toString();
            return -1;
        }
        if(bErr)sommeStricte_$ = -1;
        return sommeStricte_$;
    }
    /**
     * Fonction pour écrire l'attribut csSS1 = "SommeStricte1" qui totalise tous les poids obtenus
     * dans le cas (cp1 > cp2)
     * @param iP1 code du compte de rang1 cp1
     * @param iP2 code du compte de rang2 cp2
     * @param iVal la nouvelle somme des poids
     */
    public void setSommeStricte1(int iP1, int iP2, int iVal){
        String sSel=""+iP1+"/"+iP2;//
        try{
            setAttribut(sSel, csSS1, iVal);
        }catch(Exception err){
            System.err.println(" Err_setSommeStricte1 " +err.toString());
            bErr = true;
            sErr = "Err_setSommeStricte1 " +err.toString();
        }
    }
    /**
     * Fonction pour écrire l'attribut csSS2 = "SommeStricte2" qui totalise tous les poids obtenus
     * dans le cas (cp2 > cp1)
     * @param iP1 code du compte de rang1 cp1
     * @param iP2 code du compte de rang2 cp2
     * @param iVal la nouvelle somme des poids
     */
    public void setSommeStricte2(int iP1, int iP2, int iVal){
        String sSel=""+iP1+"/"+iP2;//
        try{
            setAttribut(sSel, csSS2, iVal);
        }catch(Exception err){
            System.err.println(" Err_setSommeStricte2 " +err.toString());
            bErr = true;
            sErr = "Err_setSommeStricte2 " +err.toString();
        }
    }
    /**
     * Fonction pour lire l'attribut cs1c = "FlagProdIncomp" qui indique que ce compte est incomparable
     * avec un autre, mais lequel ? Lire le commentaire dans le champ description
     * @param iP1 code du compte de rang1 cp1
     * @return flagProdIncomp vrai si incomparable avec un autre
     */
    public Boolean flagProdIncomp(int iP1){
        Boolean flagProdIncomp_$=false;
        String sSel=""; 
        sSel=""+iP1;
        try{
            flagProdIncomp_$ = (Boolean)getAttribut(sSel, cs1c);
        }catch(Exception err){
            System.err.println(" Err_flagProdIncomp " +err.toString());
            bErr = true;
            sErr = "Err_flagProdIncomp " +err.toString();
            return bErr;
        }
        return flagProdIncomp_$;
    }
    /**
     * Fonction pour écrire l'attribut cs1c = "FlagProdIncomp" qui indique que ce compte est incomparable
     * avec un autre, mais lequel ? Lire le commentaire dans le champ description
     * @param iP1 code du compte de rang1 cp1
     * @param iVal 1 si incomparable avec un autre, sinon 0
     */
    public void setFlagProdIncomp(int iP1, int iVal){
        String sSel=""+iP1;
        try{
            if(iVal==1){
                setAttribut(sSel, cs1c, true);
            }else{
                setAttribut(sSel, cs1c, false);
            }
        }catch(Exception err){
            System.err.println(" Err_setFlagProdIncomp " +err.toString());
            bErr = true;
            sErr = "Err_setFlagProdIncomp " +err.toString();
        }
    }
    /**
     * Fonction pour écrire l'attribut csAnn = "description" qui contient le texte justifiant de l'incomparabilité
     * @param iPos code du compte de rang1 cp1
     * @param sVal le texte décrivant les incomparables
     */
    public void annoter(int iPos, String sVal){
        String sSel=""+iPos;//
        try{
            setAttribut(sSel, csAnn, sVal);
        }catch(Exception err){
            System.err.println(" Err_annoter " +err.toString());
            bErr = true;
            sErr = "Err_annoter " +err.toString();
        }
    }
    /**
     * Fonction pour lire l'attribut csAnn = "description" qui contient le texte justifiant de l'incomparabilité
     * @param iPos code du compte de rang1 cp1
     * @return getNote le texte décrivant les incomparables
     */
    public String getNote(int iPos){
        String getNote_$ = null;
        String sSel =""+iPos;
        try{
            getNote_$ = (String)getAttribut(sSel, csAnn);
        }catch(Exception err){
            System.err.println(" Err_getNoteAnnotation " +err.toString());
            bErr = true;
            sErr = "Err_getNoteAnnotation " +err.toString();
            return null;
        }
        return getNote_$;
    }

    public int getCptePos(int iPosCompte){
        int getCptePos_$ = 0;
        String sSel = ""+iPosCompte;
        try{
            getCptePos_$ = (Integer)getAttribut(sSel, csId);
        }catch(Exception err){
            System.err.println(" Err_getCptePos " +err.toString());
            bErr = true;
            sErr = "Err_getCptePos " +err.toString();
            return -1;
        }
        if(bErr)getCptePos_$ = -1;
        return getCptePos_$;
    }
    public int getCpteId(int iPosCompte){
        String sSel = ""+iPosCompte;
        int getCpteId_$ = 0;
        try{
            getCpteId_$ = (Integer)getAttribut(sSel, csCid);
        }catch(Exception err){
            System.err.println(" Err_getCpteId " +err.toString());
            bErr = true;
            sErr = "Err_getCpteId " +err.toString();
            return -1;
        }
        if(bErr) getCpteId_$ = -1;
        return getCpteId_$;
    }
/**
 * Fonction qui retourne vrai si une erreur est signalée
 * @return vrai pour une erreur
 */
    public Boolean siErreur(){
        return bErr;
    }
/**
 * Fonction qui retourne le texte de la dernière erreur signalée
 * @return un texte decrivant l'erreur
 */
    public String erreurTxt(){
        return sErr;
    }
/**
 * procédure qui écrit sur un attribut d'un compte de rang 1 ou deux (en fait on utilise de setter de l'attribut)
 * @param sSel contient soit le code du compte de rang1 ou les deux comptes rang 1 et 2 séparé par /
 * @param sAtt l'attribut à modifier
 * @param vVal la valeur à mettre change en fonction de l'attribut
 */
    private void setAttribut(String sSel, String sAtt, Object vVal){
        Compte1 cpt1 = null;
        Compte2 cpt2 = null;
        String[] idcpte = sSel.split("/");
        if(idcpte.length==1){
            int idcpt = new Integer(idcpte[0]);
            cpt1 = (Compte1)lesComptes1.get(idcpt);
            if(cpt1 == null ){
                 sErr =  " + rien de la sélection cpt1 =".concat(sSel) ;
                 bErr = true;
                 System.err.println(" Err_setAttribut "+sErr);
                 return;
            } 
        }else{
            int idcpt = new Integer(idcpte[0]);
            int idcpt2 = new Integer(idcpte[1]);
            cpt1 = (Compte1)lesComptes1.get(idcpt);
            if(cpt1 == null ){
                 sErr =  " + rien de la sélection cpt1 =".concat(sSel) ;
                 bErr = true;
                 System.err.println(" Err_setAttribut "+sErr);
                 return;
            } 
            cpt2 = (Compte2)cpt1.lesComptes2.get(idcpt2);
            if(cpt2 == null ){
                 sErr =  " + rien de la sélection cpt2 =".concat(sSel) ;
                 bErr = true;
                 System.err.println(" Err_setAttribut "+sErr);
                 return;
            } 
        }
        switch(numAttrib(sAtt)){
            case 0: //"description"
                cpt1.setDescription((String)vVal);
                break;
            case 1: //"uid"
                break; // A la construction seulement
            case 2: //"cpteId"
                break; // A la construction seulement
            case 3: //"id"
                break; // A la construction seulement
            case 4: // Erreur l'attribut vide
                 sErr =  " Attribut vide";
                 bErr = true;
                 System.err.println(" Err_getAttribut "+sErr);
                 break;
            case 5: //"SommeStricte1", (cp1 > cp2)
                cpt2.setSommeStricte1((Integer)vVal);
                 break;
            case 6://"FormuleSuperieur1"
                cpt2.setFormuleSuperieur1((Integer)vVal);
                 break;
            case 7://"FormuleSeuil1" cp1 >= cp2
                cpt2.setFormuleSeuil1((Double)vVal);
                 break;
            case 8://"TabConcordanceHaut"
                cpt2.setTabConcordanceHaut((Integer)vVal);
                 break;
            case 9://"note"
                cpt1.setNote((Double)vVal);
                 break;
            case 10://"TabConcordanceSomL"
                cpt1.setTabConcordanceSomL((Integer)vVal);
                 break;
            case 11://"TabConcordanceClass",
                cpt1.setTabConcordanceClass((Integer)vVal);
                 break;
            case 12://"FlagProdIncomp" 
                if((Integer)vVal==0){
                    cpt1.setFlagProdIncomp(false);
                } else {
                    cpt1.setFlagProdIncomp(true);
                }
                 break;
            case 13://"TabConcordanceSomC"
                cpt1.setTabConcordanceSomC((Integer)vVal);
                 break;
            case 14://"Soustrait"
                cpt1.setSoustrait((Integer)vVal);
                 break;
            case 15://"SommeStricte2" cp2 > cp1
                cpt2.setSommeStricte2((Integer)vVal);
                 break;
            case 16://"FormuleSuperieur2"
                cpt2.setFormuleSuperieur2((Integer)vVal);
                 break;
            case 17://"FormuleSeuil2", cp2 >= cp1
                cpt2.setFormuleSeuil2((Double)vVal);
                 break;
            case 18://"TabConcordanceBas"
                cpt2.setTabConcordanceBas((Integer)vVal);
                 break;
            case 19://"lesClasses"
                 sErr =  "Attribut sans usage".concat(sAtt) ;
                 bErr = true;
                 System.err.println(" Err_getAttribut "+sErr);
                 break;
            case 20://"leClassement"
                cpt1.setLeClassement((String)vVal);
                 break;
            default://Une erreur dans le tableau
                 sErr =  " Erreur sur Attribut".concat(sAtt) ;
                 bErr = true;
                 System.err.println(" Err_getAttribut "+sErr);
                 break;
        }
    }
/**
 * Fonction qui donne la position de l'attribut dans le tableau au début de la classe, utilisé dans la fonction setAttribut
 * @param sAttt l'attribut à modifier
 * @return numAttrib la position
 */
    private int numAttrib(String sAttt){
        int iRet = 0;
        for(iRet=0;iRet<lesAtt.length;iRet++){
            if(lesAtt[iRet].equals(sAttt)){
                break;
            }
        }
        return iRet;
    }
    /**
     * Fonction qui lit sur un attribut d'un compte de rang 1 ou deux (en fait on utilise le getter de l'attribut)
     * @param sSel contient soit le code du compte de rang1 ou les deux comptes rang 1 et 2 séparé par /
     * @param sAtt l'attribut à modifier
     * @return getAttribut la valeur de l'attribut
     */
    Object getAttribut(String sSel, String sAtt){

        /* method return value */
        Object getAttribut_$ = null;
        Compte1 cpt1 = null;
        Compte2 cpt2 = null;
        String[] idcpte = sSel.split("/");
        if(idcpte.length==1){
            int idcpt = new Integer(idcpte[0]);
            cpt1 = (Compte1)lesComptes1.get(idcpt);
            if(cpt1 == null ){
                 sErr =  " + rien dans la sélection ="+sSel+" pour attribut "+sAtt ;
                 bErr = true;
                 System.err.println(" Err_getAttribut "+sErr);
                 return null;
            } 
        }else{
            int idcpt = new Integer(idcpte[0]);
            int idcpt2 = new Integer(idcpte[1]);
            cpt1 = (Compte1)lesComptes1.get(idcpt);
            if(cpt1 == null ){
                 sErr =  " + rien dans la sélection ="+sSel+" pour attribut "+sAtt ;
                 bErr = true;
                 System.err.println(" Err_getAttribut "+sErr);
                 return null;
            } 
            cpt2 = (Compte2)cpt1.lesComptes2.get(idcpt2);
            if(cpt2 == null ){
                 sErr =  " + rien de la sélection =".concat(sSel) +" pour attribut "+sAtt;
                 bErr = true;
                 System.err.println(" Err_getAttribut "+sErr);
                 return null;
            } 
        }
        switch(numAttrib(sAtt)){
            case 0: //"description"
                getAttribut_$ = new String(cpt1.getDescription());
                break;
            case 1: //"uid"
                if(cpt2 == null ){
                     getAttribut_$ = new Integer(cpt1.getUid());
                } else{
                     getAttribut_$ = new Integer(cpt2.getUid());                
                } break;
            case 2: //"cpteId"
                if(cpt2 == null ){
                     getAttribut_$ = new Long(cpt1.getCpteId());
                } else{
                     getAttribut_$ = new Long(cpt2.getCpteId());                
                } break;
            case 3: //"id"
                if(cpt2 == null ){
                     getAttribut_$ = new Integer(cpt1.getId());
                } else{
                     getAttribut_$ = new Integer(cpt2.getId());                
                } break;
            case 4: //Erreur l'attribut vide
                 sErr =  " Attribut vide";
                 bErr = true;
                 System.err.println(" Err_getAttribut "+sErr);
                 return null;
            case 5: //"SommeStricte1",
                getAttribut_$ = new Integer(cpt2.getSommeStricte1());
                 break;
            case 6://"FormuleSuperieur1"
                getAttribut_$ = new Integer(cpt2.getFormuleSuperieur1());
                 break;
            case 7://"FormuleSeuil1"
                getAttribut_$ = new Double(cpt2.getFormuleSeuil1());
                 break;
            case 8://"TabConcordanceHaut"
                getAttribut_$ = new Integer(cpt2.getTabConcordanceHaut());
                 break;
            case 9://"note"
                getAttribut_$ = new Double(cpt1.getNote());
                 break;
            case 10://"TabConcordanceSomL"
                getAttribut_$ = new Integer(cpt1.getTabConcordanceSomL());
                 break;
            case 11://"TabConcordanceClass",
                getAttribut_$ = new Integer(cpt1.getTabConcordanceClass());
                 break;
            case 12://"FlagProdIncomp" 
                getAttribut_$ = new Boolean(cpt1.getFlagProdIncomp());
                 break;
            case 13://"TabConcordanceSomC"
                getAttribut_$ = new Integer(cpt1.getTabConcordanceSomC());
                 break;
            case 14://"Soustrait"
                getAttribut_$ = new Integer(cpt1.getSoustrait());
                 break;
            case 15://"SommeStricte2"
                getAttribut_$ = new Integer(cpt2.getSommeStricte2());
                 break;
            case 16://"FormuleSuperieur2"
                getAttribut_$ = new Integer(cpt2.getFormuleSuperieur2());
                 break;
            case 17://"FormuleSeuil2",
                getAttribut_$ = new Double(cpt2.getFormuleSeuil2());
                 break;
            case 18://"TabConcordanceBas"
                getAttribut_$ = new Integer(cpt2.getTabConcordanceBas());
                 break;
            case 19://"lesClasses"
                 sErr =  "Attribut sans usage".concat(sAtt) ;
                 bErr = true;
                 System.err.println(" Err_getAttribut "+sErr);
                 return null;
            case 20://"leClassement"
                getAttribut_$ = new String(cpt1.getLeClassement());
                 break;
            default://Une erreur dans le tableau
                 sErr =  " Erreur sur Attribut".concat(sAtt) ;
                 bErr = true;
                 System.err.println(" Err_getAttribut "+sErr);
                 return null;
        }
        return getAttribut_$;
    }
    /**
     * Element de décision entre deux comptes sur une analyse au niveau critère Crit(cp2 <-> cp1)
     * @param iCpt1 code du compte de rang 1
     * @param iCpt2 code du compte de rang 2
     * @param dec texte de la décision
     */
    public void setDecisionCriteres(int iCpt1, int iCpt2, String dec){
        if(existeCase(iCpt1,iCpt2)){
            Compte1 cpt1 = (Compte1)lesComptes1.get(iCpt1);
            Compte2 cpt2 = (Compte2) cpt1.lesComptes2.get(iCpt2);
            cpt2.setDecisionCriteres(cpt2.getDecisionCriteres()+dec);
        } else{
            bErr =true;
            sErr = "Mauvaise position cp1/cp2 "+iCpt1+"/"+iCpt2;
        }
    }
    /**
     * Element de décision au niveau compte justifiant le classement du compte (cpx)
     * @param iCpt1 code du compte de rang 1
     * @return getDecisionFinale texte de la décision
     */
    public String getDecisionCriteres(int iCpt1, int iCpt2){
        String sRet = null;
        if(existeCase(iCpt1,iCpt2)){
            Compte1 cpt1 = (Compte1)lesComptes1.get(iCpt1);
            Compte2 cpt2 = (Compte2) cpt1.lesComptes2.get(iCpt2);
            sRet = cpt2.getDecisionCriteres();
        } else{
            bErr =true;
            sErr = "Mauvaise position cp1/cp2 "+iCpt1+"/"+iCpt2;
        }
        return sRet;
    }
    /**
     * Element de décision entre deux comptes sur une analyse en ligne (cp1 -> cp2x)
     * @param iCpt1 code du compte de rang 1
     * @param iCpt2 code du compte de rang 2
     * @param dec texte de la décision
     */
    public void setDecisionLigne(int iCpt1, int iCpt2, String dec){
        if(existeCase(iCpt1,iCpt2)){
            Compte1 cpt1 = (Compte1)lesComptes1.get(iCpt1);
            Compte2 cpt2 = (Compte2) cpt1.lesComptes2.get(iCpt2);
            cpt2.setDecisionLigne(cpt2.getDecisionLigne()+dec);
        } else{
            bErr =true;
            sErr = "Mauvaise position cp1/cp2 "+iCpt1+"/"+iCpt2;
        }
    }
    /**
     * Element de décision entre deux comptes sur une analyse en ligne (cp1 -> cp2x)
     * @param iCpt1 code du compte de rang 1
     * @param iCpt2 code du compte de rang 2
     * @return getDecisionLigne texte de la décision
     */
    public String getDecisionLigne(int iCpt1, int iCpt2){
        String sRet = null;
        if(existeCase(iCpt1,iCpt2)){
            Compte1 cpt1 = (Compte1)lesComptes1.get(iCpt1);
            Compte2 cpt2 = (Compte2) cpt1.lesComptes2.get(iCpt2);
            sRet = cpt2.getDecisionLigne();
        } else{
            bErr =true;
            sErr = "Mauvaise position cp1/cp2 "+iCpt1+"/"+iCpt2;
        }
        return sRet;
    }
     /**
     * Fonction qui permet de vérifier si une case existe dans le tableau
     * @param iP1 compte1 (colonne)
     * @param iP2 compte2 (ligne)
     * @return existeCase Vrai si existe dans cet ordre
     */
   public Boolean existeCase(int iP1,int iP2){
        Boolean bExiste = true;
        try{
            // vérifions si case iP1/iP2 existe
            Compte1 cpt1 = (Compte1)lesComptes1.get(iP1);
            theCube.electre.HashElectre.Compte2 cpt2 = (theCube.electre.HashElectre.Compte2) cpt1.lesComptes2.get(iP2);
            if(cpt2==null){
                bExiste = false;
            }
        }catch(Exception err){
            bExiste = false;
        }
        return bExiste;
    }
    /**
     * Classe qui porte les informations des comptes de rang 1
     */
    public class Compte1 implements ICompte1, Serializable{
        private int id;
        private String uid;
        private String description;
        private Long cpteId;
        private int tabConcordanceSomL;
        private int tabConcordanceClass;
        private int tabConcordanceSomC;
        private int soustrait;
        private String leClassement;
        private Double note;
        private Boolean flagProdIncomp;
        public Map lesComptes2 = new HashMap();
        public List listComptes2 = new LinkedList();
        private String decCrit = null;
        private String decCol = null;
        private String decLig = null;
        private String decCase1 = null;
        private String decCase2 = null;
        private String decFin = null;
        /**
         * Constructeur d'un compte de rang 1
         * @param id le code unique 
         * @param uid le code de l'identité liée au compte
         * @param cpteId un code qui ne sert à rien
         */
        public Compte1(int id, String uid, Long cpteId){
            this.uid = uid;
            this.id = id;
            this.cpteId = cpteId;
        }
        /**
         * Constructeur d'un compte de rang 1
         * @param id le code unique
         * @param uid le code de l'identité liée au compte
         * @param cpteId un code qui ne sert à rien
         */
        public Compte1(int id, String uid){
            this.uid = uid;
            this.id = id;
        }
        /**
         * Fonction qui ajoute un compte de niveau 2, il l'ajoute à un hash pour la recherche et une liste pour le parcours
         * @param id le code unique
         * @param uid le code de l'identité liée au compte
         * @param cpteId un code qui ne sert à rien
         * @return addCompte2 une référence sur l'objet créé
         */
        public Compte2 addCompte2(int id, String uid){
            Compte2 cpte = new Compte2(id, uid);
            lesComptes2.put(id, cpte);
            listComptes2.add(cpte);
            return cpte;
        }
        /**
         * Fonction qui retourne une liste pour le parcours des comptes de rang2
         * @return itLesComptes2 une liste
         */
        public List itLesComptes2(){
            return listComptes2;
        }
        /**
         * voir ICompte1
         * @return getUid
         */
        public String getUid(){
            return uid;
        }
        /**
         * voir ICompte1
         * @return getDescription
         */
        public String getDescription(){
            return description;
        }
        /**
         * Voir ICompte1
         * @param sVal
         */
        public void setDescription(String sVal){
            description = sVal;
        }
        /**
         * Voir ICompte1
         * @return getId
         */
        public int getId(){
            return id;
        }
        /**
         * Voir ICompte1
         * @return getCpteId
         */
        public Long getCpteId(){
            return cpteId;
        }
        /**
         * Voir ICompte1
         * @param iVal
         */
        public void setTabConcordanceSomL(int iVal){
            tabConcordanceSomL = iVal;
        }
        /**
         * Voir ICompte1
         * @param iVal
         */
        public void setTabConcordanceClass(int iVal){
            tabConcordanceClass = iVal;
        }
        /**
         * Voir ICompte1
         * @param iVal
         */
        public void setTabConcordanceSomC(int iVal){
            tabConcordanceSomC = iVal;
        }
        /**
         * Voir ICompte1
         * @param iVal
         */
        public void setSoustrait(int iVal){
            soustrait = iVal;
        }
        /**
         * Voir ICompte1
         * @param sVal
         */
        public void setLeClassement(String sVal){
            leClassement = sVal;
        }
        /**
         * Voir ICompte1
         * @param dVal
         */
        public void setNote(Double dVal){
            note = dVal;
        }
        /**
         * Voir ICompte1
         * @param bVal
         */
        public void setFlagProdIncomp(Boolean bVal){
            flagProdIncomp = bVal;
        }
        /**
         * Voir ICompte1
         * @return getTabConcordanceSomL
         */
        public int getTabConcordanceSomL(){
            return tabConcordanceSomL;
        }
        /**
         * Voir ICompte1
         * @return getTabConcordanceClass
         */
        public int getTabConcordanceClass(){
            return tabConcordanceClass;
        }
        /**
         * Voir ICompte1
         * @return getTabConcordanceSomC
         */
        public int getTabConcordanceSomC(){
            return tabConcordanceSomC;
        }
        /**
         * Voir ICompte1
         * @return getSoustrait
         */
        public int getSoustrait(){
            return soustrait;
        }
        public String getLeClassement(){
            return leClassement;
        }
        /**
         * Voir ICompte1
         * @return getNote
         */
        public Double getNote(){
            return note;
        }
        public Boolean getFlagProdIncomp(){
            return flagProdIncomp;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("|Compte1|id=").append(id).append(": uid=").append(uid).append(": description=").append(description).append(": cpteId=(");
            sb.append(cpteId).append(")\n").append("|Decision finale=").append(decFin).append("\n");
            sb.append("tabConcordanceSomL=(").append(tabConcordanceSomL).append("): tabConcordanceClass=(").append(tabConcordanceClass).append("): tabConcordanceSomC=(");
            sb.append(tabConcordanceSomC).append("): soustrait=(").append(soustrait).append("): leClassement=").append(leClassement).append(": flagProdIncomp=");
            sb.append(flagProdIncomp).append("\n");
    		
            return sb.toString();
        }
        /**
         * Voir ICompte1
         * @param dec
         */
        public void setDecisionFinale(String dec) {
            decFin = dec;
        }
        /**
         * Voir ICompte1
         * @return getDecisionFinale
         */
        public String getDecisionFinale() {
            return decFin;
        }
    }
    /**
     * Classe qui porte les informations des comptes de rang 2
     */
    public class Compte2 implements ICompte2{
        private int id;
        private String uid;
        private Long cpteId;
        private Double formuleSeuil1;
        private Double formuleSeuil2;
        private int formuleSuperieur1;
        private int sommeStricte2;
        private int formuleSuperieur2;
        private int sommeStricte1;
        private int tabConcordanceHaut;
        private int tabConcordanceBas;
        private String decCrit = null;
        private String decCol = null;
        private String decLig = null;
        private String decCase1 = null;
        private String decCase2 = null;
        /**
         * Contructeur d'un compte de rang 2
         * @param id le vrai code unique aléatoire
         * @param uid la référence à l'utilisateur
         * @param cpteId un code sans usage
         */
        public Compte2(int id, String uid, Long cpteId){
            this.uid = uid;
            this.id = id;
            this.cpteId = cpteId;
        }
        /**
         * Contructeur d'un compte de rang 2
         * @param id le vrai code unique aléatoire
         * @param uid la référence à l'utilisateur
         * @param cpteId un code sans usage
         */
        public Compte2(int id, String uid){
            this.uid = uid;
            this.id = id;
        }
        /**
         * Voir ICompte2
         * @return getUid
         */
        public String getUid(){
            return uid;
        }
        /**
         * Voir ICompte2
         * @return getId
         */
        public int getId(){
            return id;
        }
        /**
         * Voir ICompte2
         * @return getCpteId
         */
        public Long getCpteId(){
            return cpteId;
        }
        /**
         * Voir ICompte2
         * @param iVal
         */
        public void setFormuleSeuil1(Double iVal){
            formuleSeuil1 = iVal;
        }
        /**
         * Voir ICompte2
         * @param iVal
         */
        public void setFormuleSeuil2(Double iVal){
            formuleSeuil2 = iVal;
        }
        /**
         * Voir ICompte2
         * @param iVal
         */
        public void setFormuleSuperieur1(int iVal){
            formuleSuperieur1 = iVal;
        }
        /**
         * Voir ICompte2
         * @param iVal
         */
        public void setSommeStricte2(int iVal){
            sommeStricte2 = iVal;
        }
        /**
         * Voir ICompte2
         * @param iVal
         */
        public void setFormuleSuperieur2(int iVal){
            formuleSuperieur2 = iVal;
        }
        /**
         * Voir ICompte2
         * @param iVal
         */
        public void setSommeStricte1(int iVal){
            sommeStricte1 = iVal;
        }
        /**
         * Voir ICompte2
         * @param iVal
         */
        public void setTabConcordanceHaut(int iVal){
            tabConcordanceHaut = iVal;
        }
        /**
         * Voir ICompte2
         * @param iVal
         */
        public void setTabConcordanceBas(int iVal){
            tabConcordanceBas = iVal;
        }
        /**
         * Voir ICompte2
         * @return getFormuleSeuil1
         */
        public Double getFormuleSeuil1(){
            return formuleSeuil1;
        }
        /**
         * Voir ICompte2
         * @return getFormuleSeuil2
         */
        public Double getFormuleSeuil2(){
            return formuleSeuil2;
        }
        /**
         * Voir ICompte2
         * @return getFormuleSuperieur1
         */
        public int getFormuleSuperieur1(){
            return formuleSuperieur1;
        }
        /**
         * Voir ICompte2
         * @return getSommeStricte2
         */
        public int getSommeStricte2(){
            return sommeStricte2;
        }
        /**
         * Voir ICompte2
         * @return getFormuleSuperieur2
         */
        public int getFormuleSuperieur2(){
            return formuleSuperieur2;
        }
        /**
         * Voir ICompte2
         * @return getSommeStricte1
         */
        public int getSommeStricte1(){
            return sommeStricte1;
        }
        /**
         * Voir ICompte2
         * @return getTabConcordanceHaut
         */
        public int getTabConcordanceHaut(){
            return tabConcordanceHaut;
        }
        /**
         * Voir ICompte2
         * @return getTabConcordanceBas
         */
        public int getTabConcordanceBas(){
            return tabConcordanceBas;
        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("  |Compte2|id=").append(id).append(":uid=").append(uid).append(": cpteId=(");
            sb.append(cpteId).append(")\n    |DecisionCrit=").append(decCrit).append("\n");
            sb.append("   |DecisionLigne=").append(decLig).append("\n");
            sb.append("   formuleSeuil1=(").append(formuleSeuil1).append("): formuleSeuil2=(").append(formuleSeuil2).append("): formuleSuperieur1=(");
            sb.append(formuleSuperieur1).append("): sommeStricte2=(").append(sommeStricte2).append("): formuleSuperieur2=(").append(formuleSuperieur2).append("): sommeStricte1=(");
            sb.append(sommeStricte1).append("): tabConcordanceHaut=(").append(tabConcordanceHaut).append("): tabConcordanceBas=(").append(tabConcordanceBas).append(")\n");

            return sb.toString();
        }
        /**
         * Ecriture Element de décision entre deux comptes sur une analyse au niveau critère Crit(cp2 <-> cp1)
         * @param dec texte de la décision
         */
        public void setDecisionCriteres(String dec){
            decCrit = dec;
        } 

        /**
         * Lecture Element de décision entre deux comptes sur une analyse au niveau critère Crit(cp2 <-> cp1)
         * @return getDecisionCriteres texte de la décision
         */
        public String getDecisionCriteres(){
            return decCrit;
        }
        /**
         * Element de décision entre deux comptes sur une analyse en ligne (cp1 -> cp2x)
         * @return getDecisionLigne texte de la décision
         */
        public String getDecisionLigne(){
            return decLig;
        }
        /**
         * Element de décision entre deux comptes sur une analyse en ligne (cp1 -> cp2x)
         * @param dec texte de la décision
         */
        void setDecisionLigne(String dec){
            decLig =dec;
        }
    }

}
