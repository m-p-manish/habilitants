/*
Copyright Stéphane Georges Popoff, (juillet 2009)

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
package theCube.newRoleMiner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import theCube.entities.Identite;
import theCube.roleMining.TalendConnection;

/**
 * Fabrication des rôles à partir des habilitants de tous les comptes des identités
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
public class RoleBuilder implements ActionListener {
    String sFile = null;
    List<Idnt1> listI1 = null;
    UniqueArrayList listUH = null;
    UniqueArrayList unic = null;
    List<Hblt> listH = null;
    List<Rol> listR = null;
    List<Rol> listR1 = null;
    List<Rol> listR2 = null;
    List<Rol> listR3 = null;
    List<RolLink> listRL = null;
    Boolean bFull = false;
    private int seuilHblt = 0;
    private int seuilIdnt = 0;
    private MessageDigest empreinte = null;
    private final String dec = "--";
    private Boolean stop = false;

    public void init(List<Identite> objU, String sFichier){
       int i = 0;
       int j = 0;
       sFile = sFichier;
       try{
           try {
                if (objU.size() == 0) {
                    System.err.println("Erreur list Identité vide ");
                    return;
                }
            } catch (Exception ex) {
                System.err.println("Erreur list Identité " + ex.toString());
                return;
            }
            System.out.println("Info RoleBuilder code liste identité");
            List<Identite> objU2 = new LinkedList<Identite>();
            for (Identite ii : objU) {
                objU2.add(ii);
            }
            //collection des identités de rang 1
            listI1 = new LinkedList<Idnt1>();
            System.out.println("Info RoleBuilder Initialisation demie matrice*****************************");
            for (Identite i1 : objU) {
                i++;
                Idnt1 ii1 = null;
                if (!bFull) {
                    ii1 = new Idnt1(i1.getId());
                } else {
                    ii1 = new Idnt1(i1.getId(), i1.getUsername());
                }
                listI1.add(ii1);
                j = 0;
                for (Identite i2 : objU2) {
                    j++;
                    if (j > i) {
                        //System.out.println("idnt1:" + ii1.getId() + " idnt2:" + i2.getId());
                        if (!bFull) {
                            ii1.addIdnt2(i2.getId());
                        } else {
                            ii1.addIdnt2(i2.getId(), i2.getName());
                        }
                    }
                }
            }
        }catch(Exception err){
            System.err.println("Erreur Initialisation demie matrice identités "+err.toString());
        }
        System.out.println("Initialisation terminée de " + i + " identités");
        try{
        listUH = new UniqueArrayList();
        unic = new UniqueArrayList();
        listH = new LinkedList<Hblt>();
        listR = new LinkedList<Rol>();
        listR1 = new LinkedList<Rol>();
        listR2 = new LinkedList<Rol>();
        listR3 = new LinkedList<Rol>();
        listRL = new LinkedList<RolLink>();
        }catch(Exception err){
            System.err.println("Erreur Initialisation listes "+err.toString());
        }
        try {
            empreinte = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Erreur Initialisation pas de SHA "+ex.toString());
        }catch(Exception err){
            System.err.println("Erreur Initialisation hasher "+err.toString());
        }
        System.out.println("Initialisation complète");
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
    /**
     * Nombre d'habilitant en commun significatif pour déclencher la création d'un rôle
     * @param seuilHblt
     */
    public void setSeuilHblt(int seuilHblt) {
        this.seuilHblt = seuilHblt;
    }
    /**
     * Nbre d'identité que doit contenir un rôle à minima
     * @param seuilIdnt
     */
    public void setSeuilIdnt(int seuilIdnt) {
        this.seuilIdnt = seuilIdnt;
    }
    public void premierTour(){
        System.out.println("**** Info RoleBuilder.premierTour taille liste="+listI1.size());
        for(Idnt1 i1 : listI1){
            if(stop){
                break;
            }
            System.out.println("Info RoleBuilder.premierTour comparons idnt1="+i1.getId());
            for(Idnt2 i2 : i1.getListeI2()){
                if(stop){
                    break;
                }
                comparonsHabilitants(i1, i2);
            }
        }
    }
    /**
     * Fonction pour supprimer les rôles contenant peux d'identité
     */
    public void supprimerPetitRole(){
        //on commence par trier la liste des rôles
       LinkedList<Rol> listDel = new LinkedList<Rol>();
       System.out.println("**** Supprimer petit rôles ! listR.size="+listR.size());
        try{
            for(Rol r : listR){
                if(r.getListI().size()<seuilIdnt){
                    listDel.add(r);
                }
            }
            for(Rol rr : listDel){
                listR.remove(rr);
            }
        }catch(Exception err){
           System.err.println("Erreur RoleBuilder.supprimerPetitRole "+err.toString());
        }
        System.out.println("Info RoleBuilder.supprimerPetitRole taille="+listR.size());
    }

    /**
     * on fabrique la liste de premier ordre pas beaucoup d'habilitant, de nombreuses identités
     */
    public void trierOrdre1(){
        //on commence par trier la liste des rôles
       System.out.println("**** Trier ordre1 ! listR.size="+listR.size());
        int l = 0;
        int lPos = 0;
        try{
            for(Rol r : listR){
                if(stop){
                    break;
                }
                if(listR1.size()==0){
                    //si listR1 est vide on ajoute le premier noeud
                    listR1.add(r);
                }else{
                    //on boucle sur la liste listR1 pour trouver la bonne place au rôle
                    l = 0;
                    lPos = -1;
                    for(Rol rr : listR1){
                        // si le rôle en cours contient moins ou autant d'Hblt que celui de la nouvelle collection
                        if(r.getNbreHblt()<=rr.getNbreHblt()){
                            //alors c'est un bon candidat pour être avant
                            lPos = l;
                            //si les comptes sont plus nombreux ou égaux c'est le cas
                            if(r.getNbreIdnt()>=rr.getNbreIdnt()){
                                lPos = l;
                                break;
                            } else {
                                //sinon on essaye plus loin
                                lPos++;
                            }
                        }
                        l++;
                    }
                    if(lPos>=0 && lPos<listR1.size()){
                        listR1.add(lPos, r);
                    } else{
                        listR1.add(r);
                    }
                }
            }
        }catch(Exception err){
           System.err.println("Erreur RoleBuilder.trierOrdre1 "+err.toString());
        }
        System.out.println("Info RoleBuilder.trierOrdre1 taille="+listR1.size());
/*
        for(Rol r : listR1){
            System.out.println("Info RoleBuilder.trierOrdre1 role="+r.getId());
        }
 */
    }
    /**
     * on fabrique la liste de deuxième ordre pas beaucoup d'identité, pas beaucoup d'habilitant
     */
    public void trierOrdre2(){
       System.out.println("**** Trier ordre2 ! listR.size="+listR.size());
        int l = 0;
        int lPos = 0;
        try{
            for(Rol r : listR){
                if(stop){
                    break;
                }
                if(listR2.size()==0){
                    //si ret est vide on ajoute le premier noeud
                    listR2.add(r);
                }else{
                    //on boucle sur la liste ret pour trouver la bonne place au rôle
                    l = 0;
                    lPos = -1;
                    for(Rol rr : listR){
                        // si le rôle en cours contient plus d'Hblt que celui de la nouvelle collection
                        if(r.getNbreHblt()>rr.getNbreHblt()){
                            //alors c'est un bon candidat pour être avant
                            lPos = l;
                            if(lPos==-1)lPos=0;
                            break;
                        }else if(r.getNbreHblt()==rr.getNbreHblt()){
                            if(r.getNbreIdnt()<rr.getNbreIdnt()){
                                lPos = l;
                                if(lPos==-1)lPos=0;
                                break;
                            }
                        }
                        l++;
                    }
                    if(lPos>=0 && lPos<listR2.size()){
                        listR2.add(lPos, r);
                    } else{
                        listR2.add(r);
                    }
                }
            }
        }catch(Exception err){
           System.err.println("Erreur ordre2 "+err.toString());
        }
        System.out.println("Info RoleBuilder.trierOrdre2 taille="+listR2.size());
/*
        for(Rol r : listR2){
            System.out.println("Info RoleBuilder.trierOrdre2 role="+r.getId());
        }
 */
    }
    /**
     * Liste des rôles du plus loin au plus proche (au sens position hiérarchique, vers les feuilles)
     */
    public void trierOrdre3(){
       //Les rôles sont l'ordre du plus loin au plus proche, cela va permettre de couper
       //les liaisons entre rôles qui remontent trop haut
       System.out.println("**** trier ordre3 ! listR.size="+listR.size());
        int l = 0;
        int lPos = 0;
        try{
            for(Rol r : listR){
                if(stop){
                    break;
                }
                if(listR3.size()==0){
                    //si ret est vide on ajoute le premier noeud
                    listR3.add(r);
                }else{
                    //on boucle sur la liste ret pour trouver la bonne place au rôle
                    l = 0;
                    lPos = -1;
                    for(Rol rr : listR3){
                        //System.out.println("Info RoleBuolder.trierOrdre3 identifiant r="+r.getId()+" rr="+rr.getId());
                        //System.out.println("Info RoleBuolder.trierOrdre3 position r="+r.getPosition()+" rr="+rr.getPosition());
                        // si le compte en cours contient une position plus longue ou égale
                        if(r.getPosition().length()>=rr.getPosition().length()){
                            //alors c'est un bon candidat pour être avant
                            lPos = l;
                            if(lPos==-1)lPos=0;
                            break;
                        }
                        l++;
                    }
                    if(lPos>=0 && lPos<listR.size()){
                        listR3.add(lPos, r);
                    } else{
                        listR3.add(r);
                    }
                }

            }
        }catch(Exception err){
           System.err.println("Erreur ordre3 "+err.toString());
        }
        System.out.println("Info RoleBuilder.trierOrdre3 taille="+listR3.size());
/*
        for(Rol r : listR3){
            System.out.println("Info RoleBuilder.trierOrdre3 role="+r.getId());
        }
 */
    }
    /**
     * fonction qui fabrique la hiérarchie des rôles
     * @param twice faux pour la première passe, vrai pour la deuxième
     */
    public String hierarchisons(Boolean twice){
           //on croise les deux classements pour trouver la hiérarchie des rôles app
           //si c'est la deuxième fois on fait le ménage de la hiérarchie
           if(twice){
               listRL = new LinkedList<RolLink>();
           }
           System.out.println("***** Début de la recherche des cousins !");
           for(Rol r : listR1){
                if(stop){
                    break;
                }
               for(Rol rr : listR2){
                    if(stop){
                        break;
                    }
                    if(r.getId()==rr.getId()) break;
                    //sinon on lance la recherche des sous rôles
                    //System.out.println(" cousin?="+r.getId()+" "+rr.getId());
                    r.cousin(rr);
               }
           }
           //on fait un nouvelle boucle pour virer les petits rôles
           LinkedList<Rol> listDel = new LinkedList<Rol>();
           LinkedList<RolLink> listRLdel = new LinkedList<RolLink>();
           for(Rol rr: listR){
               if(rr.getTobedel()){
                   listDel.add(rr);
                   for(RolLink rl: listRL){
                       if(rl.getEnfant().equals(rr.getId())||rl.getParent().equals(rr.getId())){
                           listRLdel.add(rl);
                       }
                   }
               }
           }
           for(Rol rrr: listDel){
               //rrr.g
               listR.remove(rrr);
           }
           for(RolLink rll : listRLdel){
               listRL.remove(rll);
           }
           for(RolLink rl: listRL){
               System.out.println("Info RoleBuider hiérarchie parent="+rl.getParent()+" enfant="+rl.getEnfant());
           }
           String[] hier = new String[3];
           System.out.println("**** Parcourir hiérarchie marquer les positions !");
            if(stop){
                return "rien";
            }
           String prem = parcoursHieraRoles(listR1,hier);

           System.out.println(prem);
           return prem;
    }
    public String rapport(){
        String ret = "";
        for(Rol r : listR){
            ret+="\n Role="+r.getId()+" contient "+r.getListI().size()+" identités:\n";
            for(Idnt i : r.getListI()){
                //ret+="  Identité="+i.getPkIdnt()+" habilitants="+findIdnt(i.getPkIdnt()).getHabilitants()+"\n";
                ret+="  Idnt="+i.getPkIdnt()+" ";
            }
        }
        return ret;
    }
   /**
    * procèdure qui simplifie la précédente hiérarchie
    */
    public void simplifionsHiera(){
       System.out.println("***** une boucle pour simplifier la hiérachie");
       //je constitue une liste des rôles déja traitées et je les supprimes des enfants
       //sur les noeuds supérieurs
       LinkedList<Rol> blackL = new LinkedList<Rol>();
       for(Rol r: listR3){
            if(stop){
                break;
            }
           for(Rol rr : blackL){
                if(stop){
                    break;
                }
               //un rôle enfant à comme position x.x.x son parent a x.x il
               // ne faut pas supprimer ce lien. par contre une enfant en position
               //x.x.x.x se retrouve dans un parent x.x alors qu'il était déjà parent
               //d'un rôle en x.x.x dans ce cas il faut élaguer
               //Le test suivant dit le rôle en cours (r) est  plus dans la hiérarchie
               //que l'enfant (rr) ET il (r) n'est pas son parent (rr)
               if(r.getPosition().length()<rr.getPosition().length() && !(r.getPosition().equals(rr.getParent()))){
                   //System.out.println("elague autre="+rr.getId()+" moi="+r.getId());
                   elaguer(rr, r);
               }
               //attention il faut faire le ménage des comptes des parents
               //ils n'ont pas besoin de référencer le comptes déjà consommé par leurs
               //enfants
               //
               if(r.getPosition().equals(rr.getParent())){
                   //deprovisionne(rr, r);
                   r.deprovisionne(rr);
               }
           }
           if(r.getEnfant()){
                blackL.add(r);
           }
       }
   }
    /**
     * Procèdure pour retirer un rôle dans la liste des enfants afin
     * d'élaguer la hiérarchie des raccourcis obtenus pendant la recherche
     * des cousins
     * @param autre le rôle à supprimer (enfant)
     * @param  moi le rôle de référence (parent)
     */
    private void elaguer(Rol autre, Rol moi){
        for(RolLink rl : listRL){
            if(rl.getParent().equals(moi.getId()) && rl.getEnfant().equals(autre.getId())){
                listRL.remove(rl);
                break;
            }
        }
    }
    /**
     * procèdure qui cherche les identité de l'autre dans moi
     * et les supprime
     * @param autre
     * @param moi
     * @deprecated
     */
    private void deprovisionne(Rol autre, Rol moi){
         for(Idnt i: autre.getListI()){
            try{
                moi.getListI().remove(i);
            }catch(Exception err){
                //attention entre les erreurs existe pas / collection vide et le reste
                System.err.println("Erreur deprovisionne "+err.toString());
            }
        }
    }
    private String parcoursHieraRoles(List<Rol> ordre,String[] hier){
       // une boucle pour mettre en forme la hiérarchie
       //String[] hier = new String[3];
       int iPos = 1;
       String sPos = "";
       String sId = "";
       for(Rol r: ordre){
            if(stop){
                break;
            }
           if(!(r.getSommet()) && !(r.getEnfant())){
               System.out.println("* Pas sommet et pas enfant = seul rôle="+r.getId()+" hblts="+r.getHabilitants());
               sPos = ""+iPos;
               sId = ""+r.getId();
               hier[0] = hier[0]+"\nSeul pos="+sPos+" nb idnt="+r.getNbreIdnt()+" nb Hblt="+r.getListH().size()+" id="+sId+" nom="+r.getDescription()+" hblt="+r.getHabilitants()+"\n";
               //hier[0] = "rien";
               hier[1] = "";
               hier[2] = ""+iPos;
               r.setPosition(sPos);
               iPos++;
           }
           if(r.getSommet() && !r.getEnfant()){
               System.out.println("** Sommet et pas enfant = début hiérarchie obs="+r.getId()+" hblts="+r.getHabilitants());
                sPos = ""+iPos;
                //r.setPosition(sPos);
                hier[0] = hier[0]+"\nSommet pos="+sPos+" nb idnt="+r.getNbreIdnt()+" nb Hblt="+r.getListH().size()+" id="+r.getId()+" nom="+r.getDescription()+" hblt="+r.getHabilitants()+"\n";
                //hier[0] = "rien";
                hier[1] = "";
                hier[2] = ""+iPos;
                iPos++;
                hier = allonsEnfants(hier, true, r);
                hier[1] = "";
           }
       }
       return hier[0];
   }
    /**
     * Fonction qui suit le chainage des rôles et le présente dans une string
     * @param s un tableau de String avec le rapport en 0, le décalage en 1 et la position du parent en 2
     * @param bPrem un indicateur pour le passage du premier noeud
     * @param r le rôle applicatif en cours
     * @return allonsEnfants une partie de la hiérarchie des rôles
     */
    public String[] allonsEnfants(String[] s, Boolean bPrem, Rol rapp){
        int iPos = 0;
        //on persiste la position actuelle de l'objs
        try{
           rapp.setPosition(s[2].toString());
        }catch(Exception err){
            System.err.println("Erreur allonsEnfants posrap3 "+err.toString());
            return null;
        }

        if(!bPrem){
            s[1]+=dec;
            s[0]+=s[1]+"Enfant pos="+rapp.getPosition()+" nb Idnt="+rapp.getNbreIdnt()+" nb Hblt="+rapp.getListH().size()+" id="+rapp.getId()+" nom="+rapp.getDescription()+" hblt="+rapp.getHabilitants()+"\n";
        }
        String save = s[0];
        s[0] = "";
        //parcourir la liste des enfants d'un rôle app
        //System.out.println("Pour tous les enfants du rôle="+rapp.getId()+" position parent="+rapp.getPosition());
        for(RolLink rl: listRL){
            if(stop){
                break;
            }
            if(rl.getParent().equals(rapp.getId())){
                //System.out.println("Pour ENFANTID="+rl.getEnfant());
                String save2 = s[0].toString();
                s[0] = "";
                iPos++;
                s[2] = rapp.getPosition() + "." + iPos;
                //on persiste la position du parent
                Rol r = findRol(rl.getEnfant());
                r.setParent(rapp.getPosition());
                //System.out.println("allonsenfants PARENTRAP="+rapp.getPosition()+" next position="+s[2].toString());
                s = allonsEnfants(s, false, r);
                s[0] = save2 + s[0];
                //s[0]="rien";
                int i = s[1].length() - dec.length() + 1;
                if (i >= 0) {
                    s[1] = s[1].substring(0, i);
                }
            }
        }
        int i = s[1].length()-dec.length()+1;
        if(i>=0) s[1] = s[1].substring(0, i);
        s[0] = save+s[0];
        //s[0]="rien";
        return s;
    }
    /**
     * Méthode pour faire la comparaison entre deux identités sur la base des habilitants
     * deux cas sont possibles:
     * 1 On découvre une nouvelle séquence d'habilitant (clone) en commun alors on fabrique
     *   le rôle et on charge les deux identités
     * 2 les habilitants communs sont déjà connus, on cherche alors sur le rôle
     *   si l'association est faite et on ajoute le ou les identités le cas échéant
     * @param autre
     * @return comparonsHabilitants La chaîne des habilitants communs séparés par ||
     */
    private String comparonsHabilitants(Idnt1 moi,Idnt2 autre) {
        String res = "";
        String sSep = "";
        LinkedList<Hblt> lh = null;
        int iMinHbltCommun = 0;
        try{
            //on prépare la liste des habilitants
            lh = new LinkedList<Hblt>();
            //première boucle d'habilitant
            for(Hblt h1: moi.getListH()){
                if(stop){
                    break;
                }
                //deuxième boucle d'habilitant
                for(Hblt h : getListHpkIdnt(autre.getId())){
                    if(stop){
                        break;
                    }
                    //si pareil
                    if(h.equals(h1)){
                        //on ajoute à la liste
                        iMinHbltCommun++;
                        res += sSep.concat(h1.getVal());
                        //la vraie listes des habilitants
                        lh.add(h1);
                        if(sSep.equals("")){ sSep = "||";}
                        break;
                    }
                }
            }
        }catch(Exception err){
            System.err.println("Erreur RoleBuilder.comparonsHabilitants "+err.toString());
        }
        //si plusieurs habilitants en communs (on pourrait mettre ce seuil en variable)
        if(iMinHbltCommun>seuilHblt){
            //on ajoute la séquence dans un tableau qui n'accepte pas les doublons
            Boolean novo = false;
            res = sortedHabilitants(lh);
            if(unic.add(res)){
                novo = true;
            }
            //si nouvo est vrai alors on fait la création de l'objet de secu et l'ajout des
            //comptes direct, sinon on fait des recherches pour chacun des comptes
            //on balance sur les objet de secu pour ajouter ou modifier
            //System.out.println("Trouvé habilitant en commun:"+iMinHbltCommun+" entre:"+moi.getId()+" et:"+autre.getId());
            //System.out.println(" taille liste habilitant:"+lh.size()+" novo="+novo+" hblts="+res);
            addMinCommun(hacherMenu(res), moi.getId(), autre.getId(), lh, novo);
        }
        return res;
    }
    public String sortedHabilitants(LinkedList<Hblt> lh){
        ArrayList<String> ll = new ArrayList<String>();
        for(Hblt h: lh){
            ll.add(h.getHash());
        }
        Collections.sort(ll);
        return ll.toString();
    }

    public String hacherMenu(String res){
        //System.out.println("Info RoleBuilder.hacherMenu res="+res);
        empreinte.reset();
        empreinte.update(res.getBytes());
        byte[] hash = empreinte.digest();
        return hashToString(hash).substring(0, 10);
    }
    /**
     * Méthode pour vérifier et ajouter éventuellement un rôle applicatif (objet de secu) obtenus
     * à partir de la comparaison des habilitants de deux comptes
     * @param sSignature les habilitants
     * @param iCpt1 compte 1
     * @param iCpt2 compte 2
     */
    public void addMinCommun(String sSignature, int iCpt1, int iCpt2, List<Hblt> lHblt, Boolean nouvo){
        //l'objet de sécurité existe il faut tester chaque compte
        if(!nouvo){
            findTrans(sSignature, iCpt1, iCpt2);
            return;
        // c'est une création complète rôle / liaison habilitant / liaison comptes
        }else{
            //System.out.println("Info Ajoute role et identités liés c1="+iCpt1+" c2="+iCpt2);
            Integer alea = new Integer(TalendConnection.Aleat());
            Rol r = new Rol(alea);
            //System.out.println("Info Ajoute le Hash à="+alea);
            r.setHash(sSignature);
            r.setDescription("ROLE_"+r.getHash());
            //c'est du neuf ! ajoute les identités
            r.getListH().addAll(lHblt);
            add2Idnts(iCpt1, iCpt2, r, false);
        }
    }
    /**
     * Méthode pour vérifier si un rôle existe déjà et le cas échéant ajouter les utilisateurs
     * à la collection
     * @param sTrans Signature du rôle
     * @param iCptId1 compte 1
     * @param iCptId2 compte 2
     * @return vrai si trouvé
     */
    private Boolean findTrans(String sTrans, int iCptId1, int iCptId2){
        Boolean bRes = false;
        Rol r = null;
        //on doit toujours trouver un rôle
        try{
            r = findRol(sTrans);
        }catch(Exception err){
            System.err.println("Rien trouvé comme role pour hash="+sTrans+" err="+err.toString());
            return false;
        }
        //cela doit toujours marcher
        if(r!=null){
            //System.out.println("Ajoute les ou les identités au roleApp:"+r.getDescription()+" c1="+iCptId1+" c2="+iCptId2);
            add2Idnts(iCptId1, iCptId2, r, true);
            return true;
        }
        return bRes;
    }
    /**
     * Ajoute les identités à la collection du rôle (le cas échéant)
     * met à jour ou ajoute à ce dernier
     * @param cpt1 id identité 1
     * @param cpt2 id identité 2
     * @param s role
     * @param edit si vrai mise à jour role, si faux ajoute le role
     */
    public void add2Idnts(int cpt1, int cpt2, Rol r, Boolean edit){
        Boolean bEdit = false;
        int lecas = 0;
        Idnt ii1 = new Idnt(cpt1);
        Idnt ii2 = new Idnt(cpt2);
        //si edit vrai le rôle existe déjà
        if(edit){
            //on cherche l'association des identités avec le rôle
            try{
                //System.out.println("Info RoleBuider.add2Idnts listIdnt dans role taille="+r.getListI().size());
                //en premier on vérifie dans les collections
                for(Idnt i1: r.getListI()){
                    //System.out.println("Info RoleBuider.add2Idnts pkIdnt="+i1.g);
                    if(i1.getPkIdnt()==cpt1){
                        lecas=1;
                        ii1 = i1;
                        break;
                    }
                }
            }catch(Exception err){
                System.err.println("Ereur dans add2Idnts i1="+cpt1+" role="+r.getDescription()+" lecas="+lecas+" "+err.toString());
                // c'est peut être normal ;-) mais inquiétant
            }
            try{
                //en premier on vérifie dans les collections
                for(Idnt i2: r.getListI()){
                    if(i2.getPkIdnt()==cpt2){
                        lecas+=3;
                        ii2 = i2;
                        break;
                    }
                }
            }catch(Exception err){
                System.err.println("Ereur dans add2Idnts i2="+cpt2+" role="+r.getDescription()+" lecas="+lecas+" "+err.toString());
                // c'est peut être normal ;-) mais inquiétant
            }
       //si edit est faux c'est que c'est du neuf donc cas 0 (ajoute tout)
        }else{
            lecas = 0;
        }
        switch(lecas){
            case 1: //trouve cpt1 ajoute cpt2
                try{
                    r.addIdnt(ii2);
                    bEdit = true;
                }catch(Exception err){
                    System.err.println("Ereur dans add2Idnts cas1 "+err.toString());
                }
                break;
            case 3: //trouvé cpt2 ajoute cpt1
                try{
                    r.addIdnt(ii1);
                    bEdit = true;
                }catch(Exception err){
                    System.err.println("Ereur dans add2Idnts cas3 "+err.toString());
                }
                break;
            case 4: //cpt2 et cpt1 on ne fait rien
                //System.out.println("on ne fait rien pour role description="+r.getDescription()+" lecas="+lecas+" (1=c1, 3=c2, 0=c1&c2)");
                break;
            case 0: //ni cpt2 ni cpt1
                try{
                    r.addIdnt(ii1);
                    r.addIdnt(ii2);
                    bEdit = true;
                }catch(Exception err){
                    System.err.println("Ereur dans add2Idnt cas0 "+err.toString());
                }
                break;
        }
        //on ajoute le rôle que si c'est du neuf
        if(bEdit&&!edit) listR.add(r);
    }
    /**
     * transforme un tableau de byte en une string
     * @param hash
     * @return
     */
    private String hashToString(byte[] hash) {
        //System.out.println("Info RoleBuilder.hashToString ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0xFF;
            if(v < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(v, 16));
        }
        return sb.toString();
    }
    public void addHbltIdnt(Integer i, Integer pkHblt, String val, String hash){
        //System.out.println("Info RoleBuilder.addHbltIdnt hash="+hash+" val="+val);
        try{
            if(listUH.add(hash)){
                Hblt h = new Hblt(pkHblt);
                h.setHash(hash);
                h.setVal(val);
                listH.add(h);
                findIdnt(i).addHblt(h);
            }else{
                findIdnt(i).addHblt(listH.get(listUH.getLastEqual()));
            }
        }catch(Exception err){
            System.err.println("Erreur RoleBuilder.addHbltIdnt hash="+hash+" val="+val+" "+err.toString());
        }
    }
    public List<Hblt> getListHpkIdnt(Integer pkIdnt){
        return findIdnt(pkIdnt).getListH();
    }
    public Idnt1 findIdnt(Integer pkIdnt){
        Idnt1 ret = null;
        for(Idnt1 i : listI1){
            if(i.getPkIdnt().equals(pkIdnt)){
                ret = i;
                break;
            }
        }
        return ret;
    }
    public List<Idnt1> getListeI1(){
        return listI1;
    }
    public void stopper(ActionEvent e){
        System.out.println("Info reçu event ***********************");
        stop=true;
    }
    public void processAction(ActionEvent event) throws AbortProcessingException {
        System.out.println("Info reçu event *********************");
        stop=true;
    }
    public class Idnt{
        private Integer pkIdnt = null;

        public Integer getPkIdnt() {
            return pkIdnt;
        }
        public Idnt(Integer pkIdnt){
            this.pkIdnt = pkIdnt;
        }
    }
    public class Idnt1 extends Idnt{
        private List<Idnt2> listI2 = null;
        private List<Hblt> listH = null;
        private String sNom;
        private String lesHblt = null;
        public Idnt1(Integer id){
            super(id);
            listI2=new LinkedList<Idnt2>();
            listH = new LinkedList();
        }
        public void addHblt(Hblt h){
            listH.add(h);
        }
        public Idnt1(Integer id, String sNom){
            super(id);
            this.sNom = sNom;
            listI2=new LinkedList<Idnt2>();
        }
        public Integer getId() {
            return super.getPkIdnt();
        }
        public void addIdnt2(int id){
            listI2.add(new Idnt2(id));
        }
        public void addIdnt2(int id, String sNom){
            listI2.add(new Idnt2(id, sNom));
        }
        public List<Idnt2> getListeI2(){
            return listI2;
        }

        public List<Hblt> getListH() {
            return listH;
        }
        public String getName() {
            return sNom;
        }
        public String getHabilitants(){
            String ret = "";
            if(lesHblt==null){
                ArrayList<String> ll = new ArrayList<String>();
                for(Hblt h: listH){
                    ll.add(h.getVal());
                }
                Collections.sort(ll);
                ret = ll.toString();
            }else{
                ret = lesHblt;
            }
            return ret;
        }
    }
    public class Idnt2 {
        private int id;
        private String sNom;
        public Idnt2(int id){
            this.id = id;
        }
        public Idnt2(int id, String sNom){
            this.id = id;
            this.sNom = sNom;
        }
        public int getId() {
            return id;
        }

        public String getName() {
            return sNom;
        }
    }
    public class Hblt{
        private Integer pkHblt=  null;
        private String val = null;
        private String hash = null;

        public Hblt(Integer pkHblt){
            this.pkHblt = pkHblt;
        }
        public Integer getPkHblt(){
            return pkHblt;
        }
        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
        @Override
        public boolean equals(Object o){
            Boolean ret = false;
            Hblt h = (Hblt) o;
            if(this.hash.equals(h.getHash())) ret = true;
            return ret;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 41 * hash + (this.hash != null ? this.hash.hashCode() : 0);
            return hash;
        }
    }
    public Rol findRol(String hash){
        Rol ret = null;
        for(Rol r : listR){
            if(r.getHash().equals(hash)){
                ret = r;
                break;
            }
        }
        return ret;
    }
    public Rol findRol(Integer pkRol){
        Rol ret = null;
        for(Rol r : listR){
            if(r.getId()==pkRol){
                ret = r;
                break;
            }
        }
        return ret;
    }
    public RolLink findRolLink(Integer parent, Integer enfant){
        RolLink ret = null;
        for(RolLink rl : listRL){
            if(rl.getEnfant().equals(enfant) && rl.getParent().equals(parent)){
                ret = rl;
                break;
            }
        }
        return ret;
    }
    public List<RolLink> getRolLink(){
        return listRL;
    }
    public class RolLink{
        private Integer parent = null;
        private Integer enfant = null;

        public RolLink(Integer parent, Integer enfant){
            this.enfant = enfant;
            this.parent = parent;
        }

        public Integer getEnfant() {
            return enfant;
        }

        public Integer getParent() {
            return parent;
        }
    }
    public List<Rol> getRoles(){
        return listR;
    }
    public class Rol{
        private Integer pkRol = null;
        private String hash = null;
        private String description = null;
        private LinkedList<Hblt> listH = null;
        private LinkedList<Idnt> listI = null;
        private Boolean enfant = false;
        private Boolean sommet =  false;
        private String position = null;
        private String posParent = null;
        private String lesHblt = null;
        private Boolean tobedel = false;

        public Rol(Integer pkRol){
            this.pkRol = pkRol;
            listH = new LinkedList<Hblt>();
            listI = new LinkedList<Idnt>();
        }
        public void addIdnt(Idnt i){
            listI.add(i);
        }
        public void addHblt(Hblt h){
            listH.add(h);
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
        public List<Idnt> getListI(){
            return listI;
        }
        public List<Hblt> getListH(){
            return listH;
        }
        public int getNbreHblt(){
            return listH.size();
        }
        public int getNbreIdnt(){
            return listI.size();
        }
        public int getId(){
            return pkRol;
        }
        public String getPosition() {
            return position;
        }

        public Boolean getTobedel() {
            return tobedel;
        }

        public void setTobedel(Boolean tobedel) {
            this.tobedel = tobedel;
        }

        public void setPosition(String position) {
            this.position = position;
        }
        public void setParent(String position) {
            this.posParent = position;
        }
        public String getParent() {
            return posParent;
        }
        public String getHabilitants(){
        String ret = "";
        if(lesHblt==null){
            ArrayList<String> ll = new ArrayList<String>();
            for(Hblt h: listH){
                ll.add(h.getVal());
            }
            Collections.sort(ll);
            ret =  ll.toString();
        }else{
            ret = lesHblt;
        }
        return ret;
        }
        /**
         * Procèdure pour rechercher les rôles dans les rôles. Attntion cette procèdure
         * trouve des liaisons et établis des raccourcis entre plusieurs niveau en effet
         * si un rôle à comme habilitant AB alors il est parent de ABC mais aussi ABCD. La
         * procèdure elague va permettre de couper la liaison entre AB et ABC.
         * @param autre un autre rôle
         */
        public void cousin(Rol autre){
            int iComm = 0;
            String inter = "";
            //System.out.println("Info RoleBuilder.cousin habilitant1 nb="+listH.size());
            for(Hblt hh: listH){
                String h = hh.getHash();
                //System.out.println("Info RoleBuilder.cousin habilitant2 nb="+autre.getListH().size());
                for(Hblt hhhh: autre.getListH()){
                    String hhh = hhhh.getHash();
                    if(h.equals(hhh)){
                        iComm++;
                        inter += " ";
                        inter += hhh;
                        break;
                    }else{
                        //System.out.println("Info RoleBuilder.cousin habilitants différents h="+h+" hhh="+hhh);
                    }
                }
            }
            if(listH.size()==iComm && autre.getListH().size()>listH.size()){
                //si l'autre ne contient pas assez d'identité on le flingue
                if(autre.getListI().size()<seuilIdnt){
                    autre.setTobedel(true);
                }else{
                    //sinon
                    //déprovisionne le rôle en cours car en face c'est un sous rôle
                    //c'est à dire un rôle avec une racine commune et plusieurs
                    //habilitant en plus, il est plus spécifique donc on garde le provisioning
                    //Le rôle en cours représente la racine
                    //System.out.println("habilitant en commun="+iComm+" val="+inter+" autre(plus long)"+autre.getHabilitants());
                    deprovisionne(autre);
                    //System.out.println("Objs="+autre.getId()+" est un enfant");
                    autre.setEnfant();
                    setSommet();
                    setFilius(autre.getId());
                    //System.out.println("Objs="+pkRol+" est un sommet");
                }
                //listeR.add(autre);
            }else{
                //System.out.println("habilitants en commun="+iComm+" longueur ma listH="+listH.size()+" longueur autre listH="+autre.getListH().size());
            }
        }
        /**
         * Procèdure pour retirer les identités qui sont consommé par un rôle plus
         * spécifique, donc autre est un enfant du rôle actuel
         * @param autre le rôle plus spécifique car enfant
         */
        public void deprovisionne(Rol autre){
            int pos = 0;
            Boolean del = false;
            for(Idnt i : autre.getListI()){
                //Boolean ret = listI.remove((Idnt)i);
                //int pos = listI.indexOf((Idnt)i);
                //System.out.println("Deprovisionne idnt="+i.getPkIdnt()+" de role="+pkRol+" resultat="+ret+" position="+pos);
                pos = 0;
                del = false;
                for(Idnt ii : listI){
                    if(ii.getPkIdnt().equals(i.getPkIdnt())){
                        del = true;
                        //System.out.println("Info Deprovisionne idnt="+i.getPkIdnt());
                        break;
                    }
                    pos++;
                }
                if(del){
                    try{
                        listI.remove(pos);
                    }catch(Exception err){
                        System.err.println("Erreur Deprovisionne idnt="+err.toString());
                    }
                }
            }
        }
        /**
         * ajoute à la liste ENFANTID le cas échéant
         */
        public void setFilius(Integer id){
            if(findRolLink(pkRol, id)==null){
                listRL.add(new RolLink(pkRol, id));
            }
        }
        /**
         * Marque un rôle comme enfant
         * si la collection des attributs est vide (improbable) on ajoute
         * si la collection des attributs contient ENFANTRAP on met à jour la valeur
         * sinon on fabrique l'attribut et on l'ajoute à la collection
         */
        public void setEnfant(){
            enfant = true;
        }
        public Boolean getEnfant(){
            return enfant;
        }
        /**
         * Marque un rôle le premier parent
         * si la collection des attributs est vide (improbable) on ajoute
         * si la collection des attributs contient SOMMETRAP on met à jour la valeur
         * sinon on fabrique l'attribut et on l'ajoute à la collection
         */
        public void setSommet(){
            sommet = true;
        }
        public Boolean getSommet(){
            return sommet;
        }
/*
        public Integer getParent(){
            Integer ret = null;
            for(RolLink rl : listRL){
                if(rl.getEnfant().equals(pkRol)){
                    ret = rl.getParent();
                }
            }
            return ret;
        }
 */
    }
    public class UniqueArrayList extends ArrayList {
        /**
         * Only add the object if there is not
         * another copy of it in the list
         */
        private int lastEqual;

        public int getLastEqual() {
            return lastEqual;
        }
        @Override
        public boolean add(Object obj) {
            lastEqual = 0;
            for (int i = 0; i < size(); i++) {
                if (obj.equals(get(i))) {
                    lastEqual = i;
                    return false;
                }
            }
            super.add(obj);
            return true;
        }

        @Override
        public boolean addAll(Collection c) {
            boolean result = true;
            for (Object t : c) {
                if (!add(t)) {
                    result = false;
                }
            }
            return result;
        }
    }
}
