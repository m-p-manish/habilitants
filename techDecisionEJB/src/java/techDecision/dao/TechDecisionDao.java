/*
Copyright Stéphane Georges Popoff, (mai 2009 - mars 2010)

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
package techDecision.dao;
import java.util.List;
import techDecision.entites.*;
import techDecision.dao.exceptions.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.5
 */
@Stateless(mappedName="ejbTechDecision")
public class TechDecisionDao implements ITechDecisionDaoLocal, ITechDecisionDaoRemote {
    @PersistenceContext(unitName="techDecisionEJBPU")
    private EntityManager em;
    private CompteJpaController objCpte = null;
    private IdentiteJpaController objIdnt = null;
    private IdntCpteJpaController objIdCp = null;
    private HabilitantJpaController objHblt = null;
    private ObjsecuJpaController objObjS = null;
    private CpteHbltJpaController objCpHb = null;
    private CpteAttrsJpaController objCpAtt = null;
    private IdntAttrsJpaController objIdAtt = null;
    private ObjsAttrsJpaController objOsAtt = null;
    private ObjsHbltJpaController objOsHbl = null;
    private ObjsCpteJpaController objOsCpt = null;

    public void init(){
        objCpte = new CompteJpaController(em);
        objIdnt = new IdentiteJpaController(em);
        objIdCp = new IdntCpteJpaController(em);
        objHblt = new HabilitantJpaController(em);
        objObjS = new ObjsecuJpaController(em);
        objCpHb = new CpteHbltJpaController(em);
        objCpAtt = new CpteAttrsJpaController(em);
        objIdAtt = new IdntAttrsJpaController(em);
        objOsAtt = new ObjsAttrsJpaController(em);
        objOsHbl = new ObjsHbltJpaController(em);
        objOsCpt = new ObjsCpteJpaController(em);
    }
    public void cloze() {
        try {
            em.close();
        } catch (Exception e) {
            System.err.println("On ferme!");
        }
    }
    public void createCompte(Compte cpte) throws TechDecisionErreurs{
        try {
            objCpte.create(cpte);
        } catch (PreexistingEntityException ex) {
            throw new TechDecisionErreurs(ex.toString(),1);
        } catch (RollbackFailureException ex) {
            throw new TechDecisionErreurs(ex.toString(),2);
        } catch (Exception ex) {
            throw new TechDecisionErreurs(ex.toString(),3);
        }
    }

    public void createIdentite(Identite idnt) {
         try {
            objIdnt.create(idnt);
        } catch (PreexistingEntityException ex) {
            throw new TechDecisionErreurs(ex.toString(),4);
        } catch (RollbackFailureException ex) {
            throw new TechDecisionErreurs(ex.toString(),5);
        } catch (Exception ex) {
            throw new TechDecisionErreurs(ex.toString(),6);
        }
   }

    public void lierIdntCpte(int idIdnt, int idCpte) throws TechDecisionErreurs {
        Compte cpte = null;
        Identite idnt = null;
        try{
            if(objIdCp.trouveIdntCpte(idIdnt, idCpte)!=null){
                System.out.println("Identité et compte déjà lié idnt="+idIdnt+" cpte="+idCpte);
                return;
            }
            //Integer key = lien.getPkidntCpte();
            cpte = objCpte.findCompte(idCpte);
            idnt = objIdnt.findIdentite(idIdnt);
            if(cpte==null||idnt==null){
                System.err.println("Identité ou compte pas trouvé idnt="+idIdnt+" cpte="+idCpte);
                return;
            }
        }catch(Exception err){
            System.err.println("Erreur liaison compte / idnt="+idIdnt+" cpte="+idCpte+" "+err.toString());
            return;
        }
        IdntCpte lien = new IdntCpte();
        lien.setFkcpte(cpte);
        lien.setFkidnt(idnt);
        try {
            objIdCp.create(lien);
        } catch (PreexistingEntityException ex) {
            throw new TechDecisionErreurs(ex.toString(),7);
        } catch (RollbackFailureException ex) {
            throw new TechDecisionErreurs(ex.toString(),8);
        } catch (Exception ex) {
            throw new TechDecisionErreurs(ex.toString(),9);
        }
   }

    public void createHabilitant(Habilitant hblt) throws TechDecisionErreurs {
         try {
            objHblt.create(hblt);
        } catch (PreexistingEntityException ex) {
            throw new TechDecisionErreurs(ex.toString(),10);
        } catch (RollbackFailureException ex) {
            throw new TechDecisionErreurs(ex.toString(),11);
        } catch (Exception ex) {
            throw new TechDecisionErreurs(ex.toString(),12);
        }
         System.out.println("créé habilitant id="+hblt.getPkhblt()+" val="+hblt.getVal()+" type="+hblt.getType());
        em.flush();
    }

    public void createObjetSecu(Objsecu objSecu) throws TechDecisionErreurs {
         try {
            objObjS.create(objSecu);
        } catch (PreexistingEntityException ex) {
            throw new TechDecisionErreurs(ex.toString(),13);
        } catch (RollbackFailureException ex) {
            throw new TechDecisionErreurs(ex.toString(),14);
        } catch (Exception ex) {
            throw new TechDecisionErreurs(ex.toString(),15);
        }
    }
    public void lierCpteHblt(int idCpte, int idHblt) throws TechDecisionErreurs {
        CpteHblt lien = new CpteHblt();
        //Integer key = lien.getPkidntCpte();
        Compte cpte = objCpte.findCompte(idCpte);
        Habilitant hblt = objHblt.findHabilitant(idHblt);
        lien.setFkcpte(cpte);
        lien.setFkhblt(hblt);
        try {
            objCpHb.create(lien);
        } catch (PreexistingEntityException ex) {
            throw new TechDecisionErreurs(ex.toString(),16);
        } catch (RollbackFailureException ex) {
            throw new TechDecisionErreurs(ex.toString(),17);
        } catch (Exception ex) {
            throw new TechDecisionErreurs(ex.toString(),18);
        }
   }

    public void ajouteAttr(int iType, String sAttr, String sVal, int id) throws TechDecisionErreurs {
        switch(iType){
            case 1: //compte
                Compte cpte = null;
                try{
                    cpte = objCpte.findCompte(id);
                }catch(Exception err){
                    return;
                }
                if(cpte==null) throw new TechDecisionErreurs("Pas de compte id="+id, 19);
                CpteAttrs obj = new CpteAttrs();
                obj.setFkcpte(cpte);
                obj.setAttr(sAttr);
                obj.setVal(sVal);
                try {
                    objCpAtt.create(obj);
                } catch (PreexistingEntityException ex) {
                    throw new TechDecisionErreurs(ex.toString(),20);
                } catch (RollbackFailureException ex) {
                    throw new TechDecisionErreurs(ex.toString(),21);
                } catch (Exception ex) {
                    throw new TechDecisionErreurs(ex.toString(),22);
                }
                break;
            case 2: //identite
                Identite idnt = null;
                try{
                    idnt = objIdnt.findIdentite(id);
                }catch(Exception err){
                    System.err.println("Pas trouvé id="+id);
                    return;
                }
                if(idnt==null){
                    System.err.println("Pas trouvé id="+id);
                    return;
                }
                for(IdntAttrs ia: idnt.getIdntAttrsCollection()){
                    if(ia.getAttr().equals(sAttr)){
                        ia.setVal(sVal);
                        try {
                            objIdAtt.edit(ia);
                        } catch (PreexistingEntityException ex) {
                            throw new TechDecisionErreurs(ex.toString(),241);
                        } catch (RollbackFailureException ex) {
                            throw new TechDecisionErreurs(ex.toString(),252);
                        } catch (Exception ex) {
                            throw new TechDecisionErreurs(ex.toString(),262);
                        }
                        return;
                    }
                }
                IdntAttrs objj = new IdntAttrs();
                objj.setFkidnt(idnt);
                objj.setAttr(sAttr);
                objj.setVal(sVal);
                try {
                    objIdAtt.create(objj);
                } catch (PreexistingEntityException ex) {
                    throw new TechDecisionErreurs(ex.toString(),24);
                } catch (RollbackFailureException ex) {
                    throw new TechDecisionErreurs(ex.toString(),25);
                } catch (Exception ex) {
                    throw new TechDecisionErreurs(ex.toString(),26);
                }
                break;
            case 3: //objsecu
                Objsecu secu = null;
                try{
                    secu = objObjS.findObjsecu(id);
                }catch(Exception err){
                    return;
                }
                if(secu==null) throw new TechDecisionErreurs("Pas d objet secu id="+id, 27);
                ObjsAttrs objjj = new ObjsAttrs();
                objjj.setFkobjs(secu);
                objjj.setAttr(sAttr);
                objjj.setVal(sVal);
                try {
                    objOsAtt.create(objjj);
                } catch (PreexistingEntityException ex) {
                    throw new TechDecisionErreurs(ex.toString(),420);
                } catch (RollbackFailureException ex) {
                    throw new TechDecisionErreurs(ex.toString(),421);
                } catch (Exception ex) {
                    throw new TechDecisionErreurs(ex.toString(),422);
                }
                break;
        }
    }
    public int getIdHblt(String sVal) throws TechDecisionErreurs {
        int idH = 0;
        Habilitant hb = null;
         try {
            hb = objHblt.findHabilitant(sVal);
        } catch (Exception ex) {
            throw new TechDecisionErreurs(ex.toString(),23);
        }
        if(!(hb==null)) idH = hb.getPkhblt();
        return idH;
    }

    public int getIdIdnt(String userName) throws TechDecisionErreurs {
        int idI = -1;
        Identite id = null;
         try {
            id = objIdnt.findIdentite(userName);
        } catch (Exception ex) {
            throw new TechDecisionErreurs(ex.toString(),924);
        }
        if(id!=null){
            idI = id.getPkidnt();
        }
        return idI;
    }

    public List listIdentite() throws TechDecisionErreurs {
        try{
            return objIdnt.findIdentiteEntities();
        }catch(Exception err){
            throw new TechDecisionErreurs(err.toString(),25);
        }
    }

    public void modifierIdentite(Identite idnt) throws TechDecisionErreurs {
        try{
            objIdnt.edit(idnt);
        }catch(Exception err){
            throw new TechDecisionErreurs(err.toString(),26);
        }
    }

    public List<Identite> getAllIdentite() throws TechDecisionErreurs {
        try{
            return objIdnt.findIdentiteEntities();
        }catch(Exception err){
            throw new TechDecisionErreurs(err.toString(),27);
        }
    }

    public Boolean existAppli(String nomApp){
        Boolean ret = false;
        ret = objOsAtt.existNomAppli(nomApp);
        return ret;
    }

    public Integer getPkAppli(String sNom) {
        Integer ret = null;
        try{
            ret =  objOsAtt.getPkAppli(sNom);
        }catch(Exception err){
            System.err.println("Erreur getPkAppli rien trouvé pour appliHash="+sNom+" "+err.toString());
        }
        return ret;
    }

    public Habilitant findHabilitantByVal(String sVal) {
        return objHblt.findHabilitant(sVal);
    }

    public Boolean existHblt4Objs(int idCpte, int idObjs) {
        Boolean ret = false;
        try {
            ret =  objOsHbl.existHblt4Objs(idCpte, idObjs);
        } catch (Exception e) {
            System.err.println("Erreur existHblt4Objs "+e.toString());
        }
        return ret;
    }

    public void createObjsHblt(int pk, int idObjs, int idHblt) throws TechDecisionErreurs {
        try {
            objOsHbl.create(pk, idObjs, idHblt);
         } catch (Exception ex) {
            System.err.println("Erreur create ObjsHblt "+ex.toString());
            throw new TechDecisionErreurs(ex.toString(),28);
        }
    }

    public void lierCpteObjs(int pk, int cpte, int objs) {
        try {
            objOsCpt.create(pk, cpte, objs);
         } catch (Exception ex) {
            System.err.println("Erreur create ObjsHblt "+ex.toString());
        }
    }

    public int getIdCpte(String nomCpte) {
        return objCpAtt.getId4NomCpte(nomCpte);
    }

    public Integer getIdCpteUid(String sUid) {
        Integer ret = null;
        try{
            ret =  objCpAtt.getId4UidCpte(sUid);
        }catch(Exception err){
            System.err.println("Erreur cherche compte sur uid="+sUid+" "+err.toString());
        }
        return ret;
    }

    public Integer trouveIdntCpte(int idnt, int cpte) {
        return objIdCp.trouveIdntCpte(idnt, cpte);
    }
    /**
     * Recherche les doublon de liaison entre identité et compte et les réduit
     * @param idnt
     * @param cpte
     */
    public void corrigeCpteIdnt(int idnt, int cpte) {
        try{
            Integer ret = objIdCp.trouveIdntCpte(idnt, cpte);
            if(ret==null) return;
            if(ret.equals(-1)){
                objIdCp.delDoublonAddOne(idnt, cpte);
            }
        }catch(Exception err){
            System.err.println("Erreur corrigeCpteIdnt "+err.toString());
        }
    }
    /**
     * ajoute la liaison si nécessaire entre un habilitant et un compte
     * @param cpte
     * @param hblt
     */
    public void ajouteCpteHblt(String cpte, String hblt) {
        Integer ic = 0;
        Habilitant h = null;
        Compte c = null;
         try {
            ic = objCpAtt.getId4UidCpte(cpte);
            if(ic==0) return;
            h = objHblt.findHabilitant(hblt);
            if(h==null) return;
            c = objCpte.findCompte(ic);
            if(!objCpHb.existCpteHblt(c, h)){
                CpteHblt ch = new CpteHblt();
                ch.setFkcpte(c);
                ch.setFkhblt(h);
                objCpHb.create(ch);
                System.out.println("Info ajouteCpteHblt lié cpt = "+c.toString()+" hblt="+h.toString());
            }
         } catch (Exception ex) {
            System.err.println("Erreur ajouteCpteHblt "+ex.toString());
        }

    }
    /**
     * ajoute un habilitant identifié par sa valeur et son type à un compte
     * connu par son uid
     * @param cpte
     * @param hblt
     * @param iType
     */
    public void ajouteCpteHblt2(String cpte, String hblt, int iType) {
        Integer ic = 0;
        Habilitant h = null;
        Compte c = null;
         try {
            ic = objCpAtt.getId4UidCpte(cpte);
            if(ic==0) return;
            try{
                h = objHblt.findHabilitant(hblt, iType);
               if(h.getPkhblt()==0 && h.getType()==0){
                   System.err.println("habilitant en doubles Hblt=" + hblt  +" et type=" + iType);
                   return;
               }
            }catch(NullPointerException ee){
               //c'est pas une erreur
               System.out.println("pas trouvé habilitant Hblt=" + hblt  +" et type=" + iType);
               return;
            }catch(Exception err){
               System.err.println("Erreur sur findHbltByValAndType sHblt=" + hblt  +" et type=" + iType+" "+err.toString());
               return;
            }
            if(h==null) return;
            c = objCpte.findCompte(ic);
            if(!objCpHb.existCpteHblt(c, h)){
                CpteHblt ch = new CpteHblt();
                ch.setFkcpte(c);
                ch.setFkhblt(h);
                objCpHb.create(ch);
            }
         } catch (Exception ex) {
            System.err.println("Erreur ajouteCpteHblt "+ex.toString());
        }

    }
    /**
     * retrouve la clé d'une identité sur son hash sinon zéro
     * @param empreinte
     * @return
     */
    public Integer trouveIdntOnHash(String empreinte) {
        Integer ret = 0;
        IdntAttrs o= null;
        try{
            o = objIdAtt.getId4IdntHash(empreinte);
        } catch(Exception err){
            System.err.println("Erreur trouveIdntOnHash "+err.toString());
        }
        if(o!=null){
            ret = o.getFkidnt().getPkidnt();
        }
        return ret;
    }

    public Habilitant findHbltByValAndType(String sVal, int iType) {
        Habilitant ret  = null;
        try{
            ret =  objHblt.findHabilitant(sVal, iType);
        }catch(Exception err){
            System.err.println("Erreur dans EJB findHbltByValAndType "+err.toString());
        }
        return ret;
    }

    public void truncateTable(int iTable) {
        System.out.println("Info tronque table="+iTable);
        switch(iTable){
            case 1:
                //attribut objet sécurité
                objOsAtt.truncate();
            break;
            case 2:
                //attribut compte
                objCpAtt.truncate();
            break;
            case 3:
                //objsecu compte
                objOsCpt.truncate();
            break;
            case 4:
                //objsecu habilitant
                objOsHbl.truncate();
            break;
            case 5:
                //objsecu
                objObjS.truncate();
            break;
            case 6:
                //identite compte
                objIdCp.truncate();
            break;
            case 7:
                //compte habilitant
                objCpHb.truncate();
            break;
            case 8:
                //habilitant
                objHblt.truncate();
            break;
            case 9:
                //compte
                objCpte.truncate();
            break;
        }
    }

    public Boolean existHabilitant(String sVal, int iType) {
        return objHblt.existHabilitant(sVal, iType);
    }

}
