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
package theCube.electre;

//import theCube.roleMining.TalendConnection;
import java.lang.reflect.*;
import java.util.*;
//import org.apache.log4j.Level;
/**
 * Cette classe est la principale de la méthode Electre elle implémente une interface
 * qui est aussi connu de la classe qui porte la comparaison, cela fiabilise la communication
 * @author spopoff@rocketmail.com
 * @version 0.5
 */
public class CSurClassor implements IClassement {
    //private TalendConnection objDb = null;
    private Object objComp = null;
    private HashElectre objDe = null;
    private Boolean bErr = false;
    private String sErr ="";
    //private Set<Integer[]> itTri = new TreeSet(new CompareTab());
    private List<OrdreFin> itTri = new LinkedList();
    private Map itClass = new HashMap();//
    private int iMaxClasse = 0;
    private String sCodeC = null;
    private String classComp = "";
    /**
     * Constructeur classe
     * Ne fait que copier des références sur des variables locales
     * @param classComp le nom de la classe qui implémente celle là
     * @param objPerso la référence à la classe de comparaison
     * @param objEle la référence à la classe de données Electre (Dom)
    */
    public CSurClassor(String classComp, Object objPerso, HashElectre objEle){
         try{
            objDe = objEle;
            //objDb = objSgbd;
            objComp = objPerso;
        }catch(Exception err){
            bErr = true;
            sErr =  "Erreur Init_ " +err.toString() ;
        }
        this.classComp = classComp;
    }
    /**
     *
     * Constructeur de la classe pourrais être le nom de la classe mais
     * n'existait pas en VBA. Historique
     * Ne fait que copier des références sur des variables locales
     * @param classComp le nom de la classe qui implémente celle là
     * @param sFile un nom et chemin de fichier pour sauver le Dom
     * @param objPerso la référence à la classe de comparaison
     * @param objEle la référence à la classe de données Electre (Dom)
     * @deprecated
     */
    public void init(String classComp, String sFile, Object objPerso, HashElectre objEle){
        //initElectre(sFile);
        if(bErr) return;
        try{
            objDe = objEle;
            //objDb = objSgbd;
            objComp = objPerso;
        }catch(Exception err){
            bErr = true;
            sErr =  "Erreur Init_ " +err.toString() ;
        }
        this.classComp = classComp;
    }
    /**
     *
     * Fontion pour obtenir le seuil de décision choisit dans la classe de comparaison
     * utilise la Reflexion
     * @return seuilGlobal un double entre 0.5 et 1.0 (exclus)
     */
    public Double seuilGlobal(){
        Class cl = null;
        Double sg;
        try {
            cl = Class.forName(classComp);
        } catch (ClassNotFoundException ex) {
            sErr = ex.toString();
            bErr = false;
            return 0.0;
        }
        Method mthd = null;
        try {
            mthd = cl.getMethod("getSeuilGlobal");
        } catch (NoSuchMethodException ex) {
            sErr = ex.toString();
            bErr = false;
            return 0.0;
        } catch (SecurityException ex) {
            sErr = ex.toString();
            bErr = false;
            return 0.0;
        }
        try {
            sg = (Double) mthd.invoke(objComp);
        } catch (Exception err) {
            sErr = err.toString();
            bErr = false;
            return 0.0;
        }
        return sg;
    }
    /**
     *
     * Fontion pour obtenir le total des poids des critères de la classe de comparaison
     * utilise la Reflexion
     * @return totalPond un entier généralement autour de 100
     */
    public int totalPond(){
        int totalPond_$ = 0;
        Class cl = null;
        try {
            cl = Class.forName(classComp);
        } catch (ClassNotFoundException ex) {
            sErr = ex.toString();
            bErr = false;
            return 0;
        }
        Method mthd = null;
        try {
            mthd = cl.getMethod("totalPond");
        } catch (NoSuchMethodException ex) {
            sErr = ex.toString();
            bErr = false;
            return 0;
        } catch (SecurityException ex) {
            sErr = ex.toString();
            bErr = false;
            return 0;
        }
        try {
            totalPond_$ = (Integer) mthd.invoke(objComp);
        } catch (Exception err) {
            sErr = err.toString();
            bErr = false;
            return 0;
        }
        return totalPond_$;
    }
     /**
     *
     * Retourne vrai si une erreur dans précédent traitement
     * @return siErreur vrai si erreur
     */
    public boolean siErreur() {
        return bErr;
    }
     /**
     *
     * Retourne le texte de la dernière erreur
     * @return erreurTxt une erreur en texte
     */
    public String erreurTxt() {
        return sErr;
    }
     /**
     *
     * nettoie les indicateurs d'erreur pour la prochaine fois
     */
    public void clearErreur(){
        bErr = false;
        sErr = "";
    }
     /**
     *
     * Retourne le texte qui caratérise la classe de compaison et que l'on ajoute
     * au numéro de la classe (au sens Electre) pour obtenir le nom de la classe
     * Utilise la Reflexion
     * @return codeCrit quelques lettres
     */
    public String codeCrit(){
        /* method return value */
        if(!(sCodeC==null)) return sCodeC;
        Class cl = null;
        try {
            cl = Class.forName(classComp);
        } catch (ClassNotFoundException ex) {
            sErr = ex.toString();
            bErr = false;
            return null;
        }
        Method mthd = null;
        try {
            mthd = cl.getMethod("getClassementCode");
        } catch (NoSuchMethodException ex) {
            sErr = ex.toString();
            bErr = false;
            return null;
        } catch (SecurityException ex) {
            sErr = ex.toString();
            bErr = false;
            return null;
        }
        try {
            sCodeC = (String) mthd.invoke(objComp);
        } catch (Exception err) {
            sErr = err.toString();
            bErr = false;
            return null;
        }
       return sCodeC;
    }
     /**
     *
     * Procédure utilisée à la fin de toutes les comparaisons
     */
    public void finDesBoucles(){
        System.out.println("Début de la fin");
        electreFinElm();
        if(bErr){
            System.err.println("Erreur à la fin !!!!!!!! "+sErr);
            return;
        }
        System.out.println( "Fin de la fin Vraiment !!!!!!!!!!!!!");
    }
     /**
     *
     * Procédure utilisée à la fin pour sauver les données du Dom (HashElectre)
     * A Finir
     */
    private void clozeElectre() {
        try {
            objDe.cloze();
        } catch (Exception ex) {
            bErr = true;
            sErr=ex.toString() ;
        }
        if(objDe.siErreur()){
            bErr = true;
            sErr = objDe.erreurTxt();
        }
    }
     /**
     *
     * Fonction qui fait le calcul en ligne pour un compte donné (1 cp1 tous les cp2), la valeur retournée n'est pas utilisée
     * en générale<br>
     * Utilise la Reflexion
     * @param inDligne numéro du compte de rang1 en cours d'évaluation
     * @return electreFinElmProd -1 une erreur, 0 fin normale sans incomparabilité, 1 fin normale avec incomparabilité
     */
    public int electreFinElmProd(int inDligne){
        int electreFinElmProd_$ = 0;
        int sommeEnLigne = 0;
        int inDcolonne = 0;//
        int sommeStricte1 = 0;// //En Entier//// //En Entier
        int sommeStricte2 = 0;// //En Entier//// //En Entier
        Double caseTab = 0.0;
        Double caseTabComplement = 0.0;
        Boolean incomp = false;
        String sRem1 = "";
        int vPonderation = 0;//
        Double SeuilGlob = seuilGlobal();
        bErr = false;
        String dec = null;
        System.out.println( "Début Traitement de fin de comparaison pour cpt1:" + inDligne);
        try{
            sommeEnLigne = 0;
            ICompte1 cpt0 = objDe.getCompte1(inDligne);
            vPonderation = totalPond();
            // on récupère les comptes de rang2 du compte en cours
            Iterator it1 = cpt0.itLesComptes2().iterator();
            //on boucle sur tous les comptes de rang2
            while(it1.hasNext()){
                ICompte2 cpt1 = (ICompte2)it1.next();
                inDcolonne = cpt1.getId();
                //ce test permet de ne pas faire la comparaison entre les comptes identiques
                if(!(cpt0.getId()==cpt1.getId())){
                    System.out.println("cpt1:" + inDligne+" contre cpt2:"+inDcolonne);
                    dec = "";
 //les resultats des cases de la premiere
 // formule (a>=b) pour les utiliser dans le test de robustesse
                    //on divise les poids obtenus (cp1>=cp2) par la somme des poids Donc on dilue l'info ;-)
                    objDe.setFormuleSeuil1(inDligne, inDcolonne, objDe.formuleSeuil1(inDligne, inDcolonne) / new Double(vPonderation) );
                    if(objDe.siErreur()){
                        bErr = true;
                        sErr = "electreFinElmProd1 "+objDe.erreurTxt();
                        return -1;
                    }
                    //on divise les poids obtenus (cp2>=cp1) par la somme des poids
                    objDe.setFormuleSeuil2(inDligne, inDcolonne, objDe.formuleSeuil2(inDligne, inDcolonne) / new Double(vPonderation) );
                    if(objDe.siErreur()){
                        bErr = true;
                        sErr = "electreFinElmProd2 "+objDe.erreurTxt();
                        return -1;
                    }
                    // si résultat de (cp2 > cp1) est plus grand ou égale que resultat de (cp1 >cp2)
                    if(objDe.sommeStricte2(inDligne, inDcolonne)>=objDe.sommeStricte1(inDligne, inDcolonne)){
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd3 "+objDe.erreurTxt();
                            return -1;
                        }
                        //alors cp2 a gagné sur cp1
                        objDe.setFormuleSuperieur1(inDligne, inDcolonne, 1); //idem objDe.FormuleSeuil
 //mais ici pour la deuxieme formule (a>b)
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd4 "+objDe.erreurTxt();
                            return -1;
                        }
                    } else{
                        //sinon cp2 à perdu
                        objDe.setFormuleSuperieur1(inDligne, inDcolonne, 0);
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd5 "+objDe.erreurTxt();
                            return -1;
                        }
                    } 
                    // si resultat de (cp1 > cp2) est plus grand ou égal à résultat de (cp2 > cp1)
                    if(objDe.sommeStricte1(inDligne, inDcolonne)>=objDe.sommeStricte2(inDligne, inDcolonne)){
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd6 "+objDe.erreurTxt();
                            return -1;
                        }
                        //alors cp1 à gagné sur cp2
                        dec = dec+"/mesure de cp1 > cp2 est plus grande("+objDe.sommeStricte1(inDligne, inDcolonne)+") ou égale à mesure cp2 > cp1 ("+objDe.sommeStricte2(inDligne, inDcolonne)+")/";
                        objDe.setFormuleSuperieur2(inDligne, inDcolonne,  1); //idem
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd7 "+objDe.erreurTxt();
                            return -1;
                        }
                    }else{
                        //sinon cp1 a perdu contre cp2
                        dec = dec+"/cp1 n'est pas strictement supérieur à cp2:"+objDe.sommeStricte1(inDligne, inDcolonne)+"<"+objDe.sommeStricte2(inDligne, inDcolonne)+"/";
                        objDe.setFormuleSuperieur2(inDligne, inDcolonne, 0);
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd8 "+objDe.erreurTxt();
                            return -1;
                        }
                    } 
                    //(cp1 >= cp2)
                    caseTab = objDe.formuleSeuil1(inDligne, inDcolonne); //pour sauvegarder
                    if(objDe.siErreur()){
                        bErr = true;
                        sErr = "electreFinElmProd9 "+objDe.erreurTxt();
                        return -1;
                    }
                    //(cp2 >= cp1)
                    caseTabComplement = objDe.formuleSeuil2(inDligne, inDcolonne); //idem
                    if(objDe.siErreur()){
                        bErr = true;
                        sErr = "electreFinElmProd10 "+objDe.erreurTxt();
                        return -1;
                    }
                    //(cp2 > cp1)
                    sommeStricte2 = objDe.sommeStricte2(inDligne, inDcolonne);
                    if(objDe.siErreur()){
                        bErr = true;
                        sErr = "electreFinElmProd11 "+objDe.erreurTxt();
                        return -1;
                    }
                    //(cp1 > cp2)
                    sommeStricte1 = objDe.sommeStricte1(inDligne, inDcolonne);
                    if(objDe.siErreur()){
                        bErr = true;
                        sErr = "electreFinElmProd12 "+objDe.erreurTxt();
                        return -1;
                    }
                    //si résultat de (cp1>=cp2) dépasse seuil de décision ET que
                    //résultat de (cp1 > cp2) est plus grand ou égale à résultat de 
                    //(cp2 > cp1)
                    if(caseTab>=SeuilGlob && sommeStricte1>=sommeStricte2){
                        //alors cp1 est meilleur que cp2
                        dec = dec+"/mesure de cp1>=cp2 ("+caseTab+") dépasse seuil de décision ("+SeuilGlob+")ET mesure de cp1 > cp2("+sommeStricte1+") est plus grande ou égale à la mesure de cp2 > cp1("+sommeStricte2+")/";
                        objDe.setTabConcordance(inDligne, 0, inDcolonne, 1);
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd13 "+objDe.erreurTxt();
                            return -1;
                        }
                    }else{
                        //sinon cp1 n'est pas meilleur que cp2 (indifférent)
                        dec = dec+"/cp1 n'est pas meilleur que cp2/";
                        objDe.setTabConcordance(inDligne, 0, inDcolonne, 0);
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd14 "+objDe.erreurTxt();
                            return -1;
                        }
                    } 
                    //si résultat de (cp2>=cp1) dépasse seuil de décision ET que résultat
                    //de (cp2 > cp1) est plus grand ou égal aux résultat de (cp1>cp2)
                    if(caseTabComplement>=SeuilGlob && sommeStricte2>=sommeStricte1){
                        //alors cp2 est meilleur que cp1
                        dec = dec+"/mesure de cp2>=cp1 ("+caseTabComplement+") dépasse seuil de décision ("+SeuilGlob+")ET mesure de cp2 > cp1("+sommeStricte2+") est plus grande ou égale à la mesure de cp1 > cp2("+sommeStricte1+")/";
                        objDe.setTabConcordance(inDligne, 1, inDcolonne, 1);
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd15 "+objDe.erreurTxt();
                            return -1;
                        }
                    }else{
                        //sinon cp2 n'est pas meilleur que cp1 (indifférent)
                        dec = dec+"/cp2 n'est pas meilleur que cp1/";
                        objDe.setTabConcordance(inDligne, 0, inDcolonne, 0);
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd16 "+objDe.erreurTxt();
                            return -1;
                        }
                    } 
 //' cette condition est pour verifier l'incomparabilité
                    //si cp1 ET cp2 sont indifférents au sens précédent alors les avantages
                    //de l'un sont les défauts de l'autre, ils sont donc incomparables
                    if(objDe.tabConcordance(inDligne, 0, inDcolonne).equals(0) && objDe.tabConcordance(inDligne, 1, inDcolonne).equals(0)){
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd17 "+objDe.erreurTxt();
                            return -1;
                        }
                        objDe.setFlagProdIncomp(inDligne, 1); //pour apres verifier lesqules des produits sont incomparables
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd18 "+objDe.erreurTxt();
                            return -1;
                        }
                        objDe.setFlagProdIncomp(inDcolonne, 1); //idem
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElmProd19 "+objDe.erreurTxt();
                            return -1;
                        }
                        incomp = true; //'pour vérifier apres qu'il y a incomparabilite pour ce noeud
 //on met les produits incomparables en annotation si non robustesse
                        sRem1.concat(" / ").concat("cp1:"+inDligne).concat(" est incomparable avec ").concat("cp2:"+inDcolonne);
                        sRem1.concat(" Conditions non vérifiées(1ou2): 1(indicePf) ").concat(caseTab.toString()).concat(" >= (seuil) ");
                        sRem1.concat(SeuilGlob.toString()).concat(" Et 2(PF)").concat(""+sommeStricte1);
                        sRem1.concat(" >= (PF) ").concat(""+sommeStricte2).concat(" et inversement\n");
                        dec = dec+"/cp1 et cp2 incompatatibles/";
                    } 
                //on met à jour le commentaire décision
                objDe.setDecisionLigne(inDligne, inDcolonne, dec);
                } 
                //on totalise les cas ou cp1 est meilleur que des cp2
                sommeEnLigne = sommeEnLigne + (Integer)objDe.tabConcordance(inDligne, 0, inDcolonne);
                if(objDe.siErreur()){
                    bErr = true;
                    sErr = "electreFinElmProd120 "+objDe.erreurTxt();
                    return -1;
                }
            }
            // Fin de la boucle de tous les comptes de niveau 2
            if(incomp){
                //on cumul le texte justifiant les incomparables
                objDe.annoter(inDligne, objDe.getNote(inDligne)+sRem1);
                if(objDe.siErreur()){
                    bErr = true;
                    sErr = "electreFinElmProd121 "+objDe.erreurTxt();
                    return -1;
                }
                //on cumul le texte justifiant les incomparables
                objDe.annoter(inDcolonne, objDe.getNote(inDcolonne)+sRem1);
                if(objDe.siErreur()){
                    bErr = true;
                    sErr = "electreFinElmProd122 "+objDe.erreurTxt();
                    return -1;
                }
                electreFinElmProd_$ = 1;
            } else{
                electreFinElmProd_$ = 0;
            }
            //met à jour sur cp1 la somme de tous les surclassements
            objDe.setTabConcordance(inDligne, 4, inDcolonne, sommeEnLigne); //ou IndColonne +1 à cause de pour ..suivant
            if(objDe.siErreur()){
                bErr = true;
                sErr = "electreFinElmProd123 "+objDe.erreurTxt();
                return -1;
            }
        }catch(Exception ex){
            bErr = true;
            sErr =  "Erreur ElectreFinElmProd24 ".concat(ex.toString());
            return -1;
        }
        System.out.println( "Fin Traitement de fin de comparaison pour cpt1:" + inDligne);
        return electreFinElmProd_$;
    }
     /**
     *
     * Procédure utilisée à la fin pour terminer les comparaisons
     */
    private void electreFinElm(){

        int inDligne = 0;
        int i = 0;//
        int inDcolonne =0;//
        int iMax = 0;//
        int sommeEnColonne = 0;
        theCube.electre.HashElectre.Compte1 cpt1 = null;
 // boucle pour sommer les colonnes
        iMax = objDe.getItems();

        Iterator it1 = objDe.itLesComptes().iterator();
        Iterator it2 = null;
        while(it1.hasNext()){
            cpt1 = (theCube.electre.HashElectre.Compte1)it1.next();
            inDligne = cpt1.getId();
            sommeEnColonne = 0;
            it2 = objDe.itLesComptes().iterator();
            while(it2.hasNext()){
                theCube.electre.HashElectre.Compte1 cpt2 = (theCube.electre.HashElectre.Compte1)it2.next();
                inDcolonne = cpt2.getId();
                if(!(cpt1.getId()==cpt2.getId())){
                    System.out.println("Fin de la fin pour cpt1:" + cpt1.getId()+" et cpt2:"+cpt2.getId());
                    if(objDe.existeCase(cpt1.getId(), cpt2.getId())){
                        //si on est dans l'ordre cp1 > cp2 alors c'est le cas 0 de tabconcordance
                        try{
                            sommeEnColonne = sommeEnColonne + (Integer)objDe.tabConcordance(cpt1.getId(), 0, cpt2.getId());
                        }catch(Exception err){
                            bErr = true;
                            sErr = "electreFinElm1A0 "+err.toString();
                            return;
                        }
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElm1A "+objDe.erreurTxt();
                            return;
                        }
                    }else{
                        //sinon c'est le cas inverse et l'on prend le cas 1
                        try{
                            sommeEnColonne = sommeEnColonne + (Integer)objDe.tabConcordance(cpt2.getId(), 1, cpt1.getId());
                        }catch(Exception err){
                            bErr = true;
                            sErr = "electreFinElm1B0 "+err.toString();
                            return;
                        }
                        if(objDe.siErreur()){
                            bErr = true;
                            sErr = "electreFinElm1B "+objDe.erreurTxt();
                            return;
                        }
                    }
                }
            }/*Next  inDligne*/
//sur la ligne suivante pour le compte indColonne la somme colonne
            System.out.println("Fin de la fin pour colonne cpt1:" + cpt1.getId()+" **************");
            try{
                objDe.setTabConcordance(cpt1.getId(), 5, 0, sommeEnColonne);
            }catch(Exception err){
                bErr = true;
                sErr = "electreFinElm20 "+err.toString();
                return;
            }
            if(objDe.siErreur()){
                bErr = true;
                sErr = "electreFinElm2 "+objDe.erreurTxt();
                return;
            }
        }
    // boucle pour la soustraction des sommes donc le classement
        System.out.println( "************ Dernière boucle de la Fin de la fin **************");
        it1 = objDe.itLesComptes().iterator();
        while(it1.hasNext()){
            cpt1 = (theCube.electre.HashElectre.Compte1)it1.next();
            i = cpt1.getId();
            try{
                objDe.setTabConcordance(i, 6, 0,  (Integer)objDe.tabConcordance(i, 5, 0)-(Integer)objDe.tabConcordance(i, 4, 0));
            }catch(Exception err){
                bErr = true;
                sErr = "electreFinElm30 "+err.toString();
                return;
            }
            if(objDe.siErreur()){
                bErr = true;
                sErr = "electreFinElm3 "+objDe.erreurTxt();
                return;
            }
//je recopie sur une autre ligne de la matrice pour pouvoir faire le tri
            setDecisionFinale(i,"différence entre total colonne("+objDe.tabConcordance(i, 5, 0)+
                    ") et total ligne("+objDe.tabConcordance(i, 4, 0)+") egale="+objDe.tabConcordance(i, 6, 0));
            try{
                OrdreFin o = new OrdreFin((Integer)objDe.tabConcordance(i, 6, 0), i);
                int k=0;
                int l=0;
                if(itTri.size()==0){
                    itTri.add(o);
                }else{
                    Iterator it = itTri.iterator();
                    while(it.hasNext()){
                        OrdreFin oo = (OrdreFin)it.next();
                        k++;
                        if(oo.compareTo(o)<0){
                            l=k;
                        }else{
                            l = k-1;
                            break;
                        }
                    }
                    itTri.add(l,o);
                }
            }catch(Exception err){
                bErr = true;
                sErr = "electreFinElm5 "+err.toString();
                return;
            }
        }/*Next  i*/
        try{
            classementNoeud(); // procedure de classement du noeud
        }catch(Exception err){
                bErr = true;
                sErr = "electreFinElm5 "+err.toString();
                return;
        }
    }
     /**
     *
     * Procédure de classement final des comptes suivant Electre
     */
    private void classementNoeud(){
        int oldTabc = -255;
        int iClasse = 0;
        String cod = codeCrit();
        theCube.electre.HashElectre.Compte1 cpt1 = null;
//        for(Integer[] tabC : itTri){
//            if(!(oldTabc==tabC[0])){
//                iClasse++;
//            }
        for(OrdreFin tabC : itTri){
            if(!(oldTabc==tabC.ordre())){
                iClasse++;
            }
            oldTabc = tabC.ordre();
            //objDe.setTabConcordance( tabC[1], 9, 0, iClasse);
            cpt1 = (theCube.electre.HashElectre.Compte1) objDe.getCompte1(tabC.cpte());
            cpt1.setLeClassement(cod+iClasse);
            System.out.println( "Classement de cp1:"+cpt1.getId()+" est:"+cod+iClasse+" incomp="+cpt1.getFlagProdIncomp());
        }
        iMaxClasse = iClasse;
    }
     /**
     *
     * Fonction qui retourne le nombre maximal de classe obtenu par Electre
     * @return maxClasses un entier
     */
    public int maxClasses() {
        return iMaxClasse;
    }
     /**
     *
     * Fonction qui retourne le nom de la classe obtenu par Electre
     * @return getClassement un nom
     */
    public String getClassement(int iUse) {
        return (String)objDe.tabConcordance(iUse, 9, iUse);
    }
     /**
     *
     * Procédure de sauvegarde des données de comparaison Electre
     */
    public void cloze() {
        clozeElectre();
    }
    public void setDecisionFinale(int iCpt1, String dec) {
        ICompte1 cpt1 = (ICompte1)objDe.getCompte1(iCpt1);
        cpt1.setDecisionFinale(dec);
    }

    public String getDecisionFinale(int iCpt1) {
        ICompte1 cpt1 = (ICompte1)objDe.getCompte1(iCpt1);
        return cpt1.getDecisionFinale();
    }
    /**
     * Lire Element de décision entre deux comptes sur une analyse au niveau critère Crit(cp2 <-> cp1)
     * @param iCpt1 code du compte de rang 1
     * @param iCpt2 code du compte de rang 2
     * @return getDecisionCriteres texte de la décision
     */
    public String getDecisionCriteres(int iCpt1, int iCpt2){
        return objDe.getDecisionCriteres(iCpt1, iCpt2);
    }
    /**
     * Ecrire Element de décision entre deux comptes sur une analyse au niveau critère Crit(cp2 <-> cp1)
     * @param iCpt1 code du compte de rang 1
     * @param iCpt2 code du compte de rang 2
     * @param dec texte de la décision
     */
    public void setDecisionCriteres(int iCpt1, int iCpt2, String dec){
        objDe.setDecisionCriteres(iCpt1, iCpt2, dec);
    }
    /**
     * Element de décision entre deux comptes sur une analyse en ligne (cp1 -> cp2x)
     * @param iCpt1 code du compte de rang 1
     * @param iCpt2 code du compte de rang 2
     * @param dec texte de la décision
     */
    public void setDecisionLigne(int iCpt1, int iCpt2, String dec){
        objDe.setDecisionLigne(iCpt1, iCpt2, dec);
    }
    /**
     * Element de décision entre deux comptes sur une analyse en ligne (cp1 -> cp2x)
     * @param iCpt1 code du compte de rang 1
     * @param iCpt2 code du compte de rang 2
     * @return getDecisionLigne texte de la décision
     */
    public String getDecisionLigne(int iCpt1, int iCpt2){
        return objDe.getDecisionLigne(iCpt1, iCpt2);
    }
     /**
     *
     * Classe qui sert à faire la comparaison sur deux valeurs (voir fin electreFinElm)
     */
    public static class CompareTab implements Comparator{
            public int compare(Object arg0, Object arg1) {
            CSurClassor.OrdreFin un = (CSurClassor.OrdreFin) arg0;
            CSurClassor.OrdreFin de = (CSurClassor.OrdreFin) arg1;
            if(un.ordre() > de.ordre()){
                return 1;
            } else if(un.ordre() < de.ordre()){
                return -1;
            } else{
                return 0;
            }
        }
    }
    public class OrdreFin implements Comparable<OrdreFin>{
        private final int iOrdre, iCpt;
        public OrdreFin(int iOrdre, int iCpt){
            this.iOrdre = iOrdre;
            this.iCpt = iCpt;
        }
        public int ordre(){return iOrdre;}
        public int cpte(){return iCpt;}
        public int compareTo(CSurClassor.OrdreFin arg0) {
            int iRet=0;
            if(arg0.ordre()>iOrdre){
                iRet= 1;
            }else if(arg0.ordre()<iOrdre){
                iRet= -1;
            }
            return iRet;
        }
        @Override
        public boolean equals(Object o){
            Boolean bRet= false;
            CSurClassor.OrdreFin arg0 = (CSurClassor.OrdreFin)o;
            if(arg0.ordre()==iOrdre){bRet=true;}
            return bRet;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 47 * hash + this.iOrdre;
            hash = 47 * hash + this.iCpt;
            return hash;
        }
    }
}
