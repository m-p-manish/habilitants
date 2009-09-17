/*
Copyright Stéphane Georges Popoff, (mai - juillet 2009)

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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import oracle.toplink.essentials.indirection.IndirectList;
import theCube.entities.Compte;
import theCube.entities.Objsecu;
import theCube.jpa.exceptions.IllegalOrphanException;
import theCube.jpa.exceptions.NonexistentEntityException;
import theCube.jpa.exceptions.RollbackFailureException;
import theCube.jpa.ObjsAttrsJ3;

/**
 * Classe qui gèrent les rôles applicatifs dans des objets de sécurité
 * @author spopoff@rocketmail.com
 * @version 0.5
 */
public class ObjSecus extends theCube.jpa.ObjsecuJpaController {
    private Comptes cptes = null;
    private ObjsAttrsJ3 objsAttrs = null;

    public ObjSecus(){
        System.out.println("Initialisation des Roles en fait les objets de sécurité");
    }
    /**
     * Passe la référence du jpa pour les comptes
     * @param cptes
     */
    public void setCptes(Comptes cptes) {
        this.cptes = cptes;
    }
    /**
     * Passe la référence du jpa pour les attributs
     * @param objsAttrs
     */
    @Override
    public void setObjsAttrs(ObjsAttrsJ3 objsAttrs) {
        this.objsAttrs = objsAttrs;
        super.setObjsAttrs(objsAttrs);
    }
    @Override
    public String getRapportObjs() {
        return super.getRapportObjs();
    }

    @Override
    public void setRapportObjs(String rapportObjs) {
        super.setRapportObjs(rapportObjs);
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
        Objsecu os = null;
        //on doit toujours trouver un objet de sécurité
        try{
            os = super.findObjsecu(sTrans);
        }catch(Exception err){
            System.err.println("Rien trouvé comme roleApp pour hash="+sTrans+" err="+err.toString());
            return false;
        }
        //cela doit toujours marcher
        if(os!=null){
            System.out.println("Ajoute les ou les comptes au roleApp:"+os.getDescription()+" c1="+iCptId1+" c2="+iCptId2);
            add2Cptes(iCptId1, iCptId2, os, true);
            return true;
        }
        return bRes;
    }
    /**
     * Ajoute les comptes à la collection du rôle applicatif le cas échéant
     * met à jour ou ajoute à ce dernier
     * @param cpt1 id du compte 1
     * @param cpt2 id du compte 2
     * @param s roleApp objet de sécurité
     * @param edit si vrai mise à jour roleApp, si faux ajoute le roleApp
     */
    public void add2Cptes(int cpt1, int cpt2, Objsecu s, Boolean edit){
        Boolean bEdit = false;
        int lecas = 0;
        //si edit vrai l'objet de sécu existe déjà
        if(edit){
            //on cherche l'association des comptes avec l'objet de sécurité
            try{
                //en prelier on vérifie dans les collections
                for(Compte c1: s.getCpteColl()){
                    if(c1.getId()==cpt1){
                        lecas=1;
                        break;
                    }
                }
                for(Compte c2: s.getCpteColl()){
                    if(c2.getId()==cpt2){
                        lecas+=3;
                        break;
                    }
                }
                //si les deux comptes ne sont pas déjà présent alors on cherche dans la base
                if(lecas<4){
                    lecas = cptes.trouveComptes(cpt1,cpt2, s.getPkobjs());
                }
            }catch(Exception err){
                System.err.println("Ereur dans trouveComptes c1="+cpt1+" c2="+cpt2+" objs="+s.getPkobjs()+" "+err.toString());
                // c'est peut être normal ;-) mais inquiétant
            }
       //si edit est faux c'est que c'est du neuf donc cas 0 (ajoute tout)
        }else{
            lecas = 0;
        }
        //trouveComptes si 1 =trouvé compte 1, si 3 =trouvé compte 2, si 4 =trouvé les deux, si 0 rien trouvé, si -1 erreur
        if(lecas==-1){
            System.err.println("Ereur dans trouveComptes / add2Cptes c1="+cpt1+" c2="+cpt2+" objs="+s.getPkobjs());
            return;
        }
        switch(lecas){
            case 1: //trouve cpt1 ajoute cpt2
                try{
                    Compte c2 = cptes.findCompte(cpt2);
                    s.getCpteColl().add(c2);
                    bEdit = true;
                }catch(Exception err){
                    System.err.println("Ereur dans add2Cptes cas1 "+err.toString());
                }
                break;
            case 3: //trouvé cpt2 ajoute cpt1
                try{
                    Compte c1 = cptes.findCompte(cpt1);
                    s.getCpteColl().add(c1);
                    bEdit = true;
                }catch(Exception err){
                    System.err.println("Ereur dans add2Cptes cas3 "+err.toString());
                }
                break;
            case 4: //cpt2 et cpt1 on ne fait rien
                System.out.println("on ne fait rien pour role applicatif description="+s.getDescription()+" lecas="+lecas+" (1=c1, 3=c2, 0=c1&c2)");
                break;
            case 0: //ni cpt2 ni cpt1
                try{
                    Compte c32 = cptes.findCompte(cpt2);
                    Compte c31 = cptes.findCompte(cpt1);
                    IndirectList ll = null;//new LinkedList();
                    ll = (IndirectList) s.getCpteColl();
                    if(ll==null) ll = new IndirectList();
                    ll.add(c32);
                    ll.add(c31);
                    s.setCpteColl(ll);
                    bEdit = true;
                }catch(Exception err){
                    System.err.println("Ereur dans add2Cptes cas0 "+err.toString());
                }
                break;
        }
        try {
            if(edit && bEdit){
                System.out.println("persiste (edit) le role applicatif description="+s.getDescription()+" lecas="+lecas+" (1=c1, 3=c2, 0=c1&c2)");
                super.edit(s);
            }else if(!edit && bEdit){
                System.out.println("persiste (create) le role applicatif description="+s.getDescription());
                super.create(s);
           }
        } catch (IllegalOrphanException ex) {
            System.err.println(ex.toString());
        } catch (NonexistentEntityException ex) {
            System.err.println(ex.toString());
        } catch (RollbackFailureException ex) {
            System.err.println(ex.toString());
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }
    /**
     * retourne une liste des rôles triée sur le nombre d'hblt ascendant (1>n) et
     * le nombre de comptes descendant (n>1)
     * @return getOrdre1 une liste triée
     */
    public List<Objsecu> getOrdre1(Boolean full){
        return lordre1(full);
    }
    /**
     * retourne une liste des rôles triée sur le nombre d'hblt ascendant (1>n) et
     * le nombre de comptes descendant (n>1)
     * @return getOrdre1 une liste triée
     */
    public List<Objsecu> getOrdre1(){
        return lordre1(false);
    }
    /**
     * fonction privée supportant les public getOrdre1
     * @return
     */
    private List<Objsecu> lordre1(Boolean full){
        List<Objsecu> ret = new LinkedList();
        int l = 0;
        int lPos = 0;
        try{
            for(Objsecu r : super.findObjsecuEntities()){
                if(full){
                    r.setObjsAttrsCollection(objsAttrs.relatedAttrs(r.getId()));
                }
                if(ret.size()==0){
                    //si ret est vide on ajoute le premier noeud
                    ret.add(r);
                }else{
                    //on boucle sur la liste ret pour trouver la bonne place au rôle
                    l = 0;
                    lPos = -1;
                    for(Iterator<Objsecu> itt = ret.iterator(); itt.hasNext() ;){
                        Objsecu rr = itt.next();
                        // si le compte en cours contient moins ou autant d'Hblt que celui de la nouvelle collection
                        if(r.getNbreHblt()<=rr.getNbreHblt()){
                            //alors c'est un bon candidat pour être avant
                            lPos = l;
                            //si les comptes sont plus nombreux ou égaux c'est le cas
                            if(r.getNbreCpt()>=rr.getNbreCpt()){
                                lPos = l;
                                break;
                            } else {
                                //sinon on essaye plus loin
                                lPos++;
                            }
                        }
                        l++;
                    }
                    if(lPos>=0 && lPos<ret.size()){
                        ret.add(lPos, r);
                    } else{
                        ret.add(r);
                    }
                }

            }
        }catch(Exception err){
           System.err.println(ObjSecus.class.getName()+" getOrdre1 "+err.toString());
        }
        return ret;
    }
    /**
     * L'inverse du premier
     * @return getOrdre1 une liste triée
     */
    public List<Objsecu> getOrdre2(){
        List<Objsecu> ret = new LinkedList();
        int l = 0;
        int lPos = 0;
        try{
            for(Iterator<Objsecu> it = super.findObjsecuEntities().iterator(); it.hasNext() ;){
                Objsecu r = it.next();
                if(ret.size()==0){
                    //si ret est vide on ajoute le premier noeud
                    ret.add(r);
                }else{
                    //on boucle sur la liste ret pour trouver la bonne place au rôle
                    l = 0;
                    lPos = -1;
                    for(Iterator<Objsecu> itt = ret.iterator(); itt.hasNext() ;){
                        Objsecu rr = itt.next();
                        // si le compte en cours contient plus d'Hblt que celui de la nouvelle collection
                        if(r.getNbreHblt()>rr.getNbreHblt()){
                            //alors c'est un bon candidat pour être avant
                            lPos = l;
                            if(lPos==-1)lPos=0;
                            break;
                        }else if(r.getNbreHblt()==rr.getNbreHblt()){
                            if(r.getNbreCpt()<rr.getNbreCpt()){
                                lPos = l;
                                if(lPos==-1)lPos=0;
                                break;
                            }
                        }
                        l++;
                    }
                    if(lPos>=0 && lPos<ret.size()){
                        ret.add(lPos, r);
                    } else{
                        ret.add(r);
                    }
                }

            }
        }catch(Exception err){
           System.err.println(ObjSecus.class.getName()+" getOrdre2 "+err.toString());
        }
        return ret;
    }
}
