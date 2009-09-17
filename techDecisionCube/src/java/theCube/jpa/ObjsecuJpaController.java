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
package theCube.jpa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import theCube.entities.Compte;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import oracle.toplink.essentials.indirection.IndirectList;
import theCube.entities.Habilitant;
import theCube.entities.ObjsAttrs;
import theCube.entities.Objsecu;
import theCube.jpa.exceptions.IllegalOrphanException;
import theCube.jpa.exceptions.NonexistentEntityException;
import theCube.jpa.exceptions.PreexistingEntityException;
import theCube.jpa.exceptions.RollbackFailureException;
import theCube.roleMining.TalendConnection;
import theCube.roleMining.DemiMatrice;
import theCube.roleMining.DemiMatrice.Cpt1;
import theCube.roleMining.DemiMatrice.Cpt2;
import theCube.roleMining.Habilitants;

/**
 * classe JPA supportant les objets de securité
 * @author spopoff@rocketmail.com
 * @version 0.8
 */
public class ObjsecuJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "techDecisionCubePU")
    private EntityManagerFactory emf = null;
    private ObjsAttrsJ3 objsAttrS = null;
    private ObjsCpteJpaController objsCpte = null;
    private final String dec="--";
    private Habilitants jpaHblts = null;
    private  UniqueArrayList unic = null;
    private  ArrayList<Objsecu> objss = null;
    private MessageDigest empreinte = null;
    private int seuilHblt = 1;
    private String rapportObjs = "";

    public String getRapportObjs() {
        return rapportObjs;
    }

    public void setRapportObjs(String rapportObjs) {
        this.rapportObjs = rapportObjs;
    }


    /**
     * passe la référence du jpa Habilitants
     * @param jpaHblts
     */
    public void setJpaHblts(Habilitants jpaHblts) {
        this.jpaHblts = jpaHblts;
    }
    /**
     * Passe la référence du jpa pour les attributs
     * @param objsAttrs
     */
    public void setObjsAttrs(ObjsAttrsJ3 objsAttrs) {
        this.objsAttrS = objsAttrs;
    }
    /**
     * Passe la référence du jpa pour les comptes liés aux objet de sécurité
     * @param objsCpte
     */
    public void setObjsCpte(ObjsCpteJpaController objsCpte) {
        this.objsCpte = objsCpte;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    private void creer(Objsecu objsecu, EntityManager em) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (objsecu.getCpteColl() == null) {
            objsecu.setCpteColl(new ArrayList<Compte>());
        }
        if (objsecu.getHbltColl() == null) {
            objsecu.setHbltColl(new ArrayList<Habilitant>());
        }
        try {
            utx.begin();
            em.joinTransaction();
            Collection<Compte> attachedCpteColl = new ArrayList<Compte>();
            for (Compte cpteCollCompteToAttach : objsecu.getCpteColl()) {
                cpteCollCompteToAttach = em.getReference(cpteCollCompteToAttach.getClass(), cpteCollCompteToAttach.getPkcpte());
                attachedCpteColl.add(cpteCollCompteToAttach);
            }
            objsecu.setCpteColl(attachedCpteColl);
            Collection<Habilitant> attachedHbltColl = new ArrayList<Habilitant>();
            for (Habilitant hbltCollHabilitantToAttach : objsecu.getHbltColl()) {
                hbltCollHabilitantToAttach = em.getReference(hbltCollHabilitantToAttach.getClass(), hbltCollHabilitantToAttach.getPkhblt());
                attachedHbltColl.add(hbltCollHabilitantToAttach);
            }
            objsecu.setHbltColl(attachedHbltColl);
            em.persist(objsecu);
            for (Compte cpteCollCompte : objsecu.getCpteColl()) {
                cpteCollCompte.getObjsColl().add(objsecu);
                cpteCollCompte = em.merge(cpteCollCompte);
            }
            for (Habilitant hbltCollHabilitant : objsecu.getHbltColl()) {
                hbltCollHabilitant.getObjsColl().add(objsecu);
                hbltCollHabilitant = em.merge(hbltCollHabilitant);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findObjsecu(objsecu.getPkobjs()) != null) {
                throw new PreexistingEntityException("Objsecu " + objsecu + " already exists.", ex);
            }
            throw ex;
        } finally {
        }
    }

    public void create(Objsecu objsecu, EntityManager em) throws PreexistingEntityException, RollbackFailureException, Exception {
        creer(objsecu, em);
    }
    public void create(Objsecu objsecu) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        creer(objsecu, em);
        if (em != null) {
            em.close();
        }
    }
    private void modif(Objsecu objsecu, EntityManager em) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            utx.begin();
            em.joinTransaction();
            Objsecu persistentObjsecu = em.find(Objsecu.class, objsecu.getPkobjs());
            Collection<Compte> cpteCollOld = persistentObjsecu.getCpteColl();
            Collection<Compte> cpteCollNew = objsecu.getCpteColl();
            Collection<Habilitant> hbltCollOld = persistentObjsecu.getHbltColl();
            Collection<Habilitant> hbltCollNew = objsecu.getHbltColl();
            Collection<ObjsAttrs> objsAttrsCollectionOld = persistentObjsecu.getObjsAttrsCollection();
            Collection<ObjsAttrs> objsAttrsCollectionNew = objsecu.getObjsAttrsCollection();
            List<String> illegalOrphanMessages = null;
            for (ObjsAttrs objsAttrsCollectionOldObjsAttrs : objsAttrsCollectionOld) {
                if (!objsAttrsCollectionNew.contains(objsAttrsCollectionOldObjsAttrs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ObjsAttrs " + objsAttrsCollectionOldObjsAttrs + " since its fkobjs field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Compte> attachedCpteCollNew = new ArrayList<Compte>();
            for (Compte cpteCollNewCompteToAttach : cpteCollNew) {
                cpteCollNewCompteToAttach = em.getReference(cpteCollNewCompteToAttach.getClass(), cpteCollNewCompteToAttach.getPkcpte());
                attachedCpteCollNew.add(cpteCollNewCompteToAttach);
            }
            cpteCollNew = attachedCpteCollNew;
            objsecu.setCpteColl(cpteCollNew);
            Collection<Habilitant> attachedHbltCollNew = new ArrayList<Habilitant>();
            for (Habilitant hbltCollNewHabilitantToAttach : hbltCollNew) {
                hbltCollNewHabilitantToAttach = em.getReference(hbltCollNewHabilitantToAttach.getClass(), hbltCollNewHabilitantToAttach.getPkhblt());
                attachedHbltCollNew.add(hbltCollNewHabilitantToAttach);
            }
            hbltCollNew = attachedHbltCollNew;
            objsecu.setHbltColl(hbltCollNew);
            Collection<ObjsAttrs> attachedObjsAttrsCollectionNew = new ArrayList<ObjsAttrs>();
            for (ObjsAttrs objsAttrsCollectionNewObjsAttrsToAttach : objsAttrsCollectionNew) {
                objsAttrsCollectionNewObjsAttrsToAttach = em.getReference(objsAttrsCollectionNewObjsAttrsToAttach.getClass(), objsAttrsCollectionNewObjsAttrsToAttach.getPkattrObjs());
                attachedObjsAttrsCollectionNew.add(objsAttrsCollectionNewObjsAttrsToAttach);
            }
            objsAttrsCollectionNew = attachedObjsAttrsCollectionNew;
            objsecu.setObjsAttrsCollection(objsAttrsCollectionNew);
            objsecu = em.merge(objsecu);
            for (Compte cpteCollOldCompte : cpteCollOld) {
                if (!cpteCollNew.contains(cpteCollOldCompte)) {
                    cpteCollOldCompte.getObjsColl().remove(objsecu);
                    cpteCollOldCompte = em.merge(cpteCollOldCompte);
                }
            }
            for (Compte cpteCollNewCompte : cpteCollNew) {
                if (!cpteCollOld.contains(cpteCollNewCompte)) {
                    cpteCollNewCompte.getObjsColl().add(objsecu);
                    cpteCollNewCompte = em.merge(cpteCollNewCompte);
                }
            }
            for (Habilitant hbltCollOldHabilitant : hbltCollOld) {
                if (!hbltCollNew.contains(hbltCollOldHabilitant)) {
                    hbltCollOldHabilitant.getObjsColl().remove(objsecu);
                    hbltCollOldHabilitant = em.merge(hbltCollOldHabilitant);
                }
            }
            for (Habilitant hbltCollNewHabilitant : hbltCollNew) {
                if (!hbltCollOld.contains(hbltCollNewHabilitant)) {
                    hbltCollNewHabilitant.getObjsColl().add(objsecu);
                    hbltCollNewHabilitant = em.merge(hbltCollNewHabilitant);
                }
            }
            for (ObjsAttrs objsAttrsCollectionNewObjsAttrs : objsAttrsCollectionNew) {
                if (!objsAttrsCollectionOld.contains(objsAttrsCollectionNewObjsAttrs)) {
                    Objsecu oldFkobjsOfObjsAttrsCollectionNewObjsAttrs = objsAttrsCollectionNewObjsAttrs.getFkobjs();
                    objsAttrsCollectionNewObjsAttrs.setFkobjs(objsecu);
                    objsAttrsCollectionNewObjsAttrs = em.merge(objsAttrsCollectionNewObjsAttrs);
                    if (oldFkobjsOfObjsAttrsCollectionNewObjsAttrs != null && !oldFkobjsOfObjsAttrsCollectionNewObjsAttrs.equals(objsecu)) {
                        oldFkobjsOfObjsAttrsCollectionNewObjsAttrs.getObjsAttrsCollection().remove(objsAttrsCollectionNewObjsAttrs);
                        oldFkobjsOfObjsAttrsCollectionNewObjsAttrs = em.merge(oldFkobjsOfObjsAttrsCollectionNewObjsAttrs);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = objsecu.getPkobjs();
                if (findObjsecu(id) == null) {
                    throw new NonexistentEntityException("The objsecu with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }
    public void edit(Objsecu objsecu, EntityManager em) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        modif(objsecu, em);
    }

    public void edit(Objsecu objsecu) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        modif(objsecu, em);
        if (em != null) {
            em.close();
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Objsecu objsecu;
            try {
                objsecu = em.getReference(Objsecu.class, id);
                objsecu.getPkobjs();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objsecu with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ObjsAttrs> objsAttrsCollectionOrphanCheck = objsecu.getObjsAttrsCollection();
            for (ObjsAttrs objsAttrsCollectionOrphanCheckObjsAttrs : objsAttrsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objsecu (" + objsecu + ") cannot be destroyed since the ObjsAttrs " + objsAttrsCollectionOrphanCheckObjsAttrs + " in its objsAttrsCollection field has a non-nullable fkobjs field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Compte> cpteColl = objsecu.getCpteColl();
            for (Compte cpteCollCompte : cpteColl) {
                cpteCollCompte.getObjsColl().remove(objsecu);
                cpteCollCompte = em.merge(cpteCollCompte);
            }
            Collection<Habilitant> hbltColl = objsecu.getHbltColl();
            for (Habilitant hbltCollHabilitant : hbltColl) {
                hbltCollHabilitant.getObjsColl().remove(objsecu);
                hbltCollHabilitant = em.merge(hbltCollHabilitant);
            }
            em.remove(objsecu);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Objsecu> findObjsecuEntities() {
        return findObjsecuEntities(true, -1, -1);
    }

    public List<Objsecu> findObjsecuEntities(int maxResults, int firstResult) {
        return findObjsecuEntities(false, maxResults, firstResult);
    }

    private List<Objsecu> findObjsecuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            return lesObjs(all, maxResults, firstResult, em);
        } finally {
            em.close();
        }
    }

    private List<Objsecu> lesObjs(boolean all, int maxResults, int firstResult, EntityManager em) {
        List<Objsecu> ret = null;
        try{
            Query q = em.createNativeQuery("SELECT * FROM SPOPOFF.OBJSECU", Objsecu.class);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            ret =  q.getResultList();
        }catch(Exception err){
            System.err.println("Erreur lesObjs "+err.toString());
        }
        return ret;
    }

    public Objsecu findObjsecu(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objsecu.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjsecuCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Objsecu as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    /**
     * Fonction qui sur un hash des habilitants trouve l'objet de securité
     * @param hash
     * @return Objsecu un objet de securité ou rien
     */
    public Objsecu findObjsecu(String hash) {
        EntityManager em = getEntityManager();
        Objsecu ret = null;
        try{
        ret = unObjsecu(hash);
        }catch(Exception err){
            System.err.println("Erreur findObjsecu "+err.toString());
        }finally{
            em.close();
        }
        return ret;
    }
    /**
     * Fonction qui sur un hash des habilitants trouve l'objet de securité
     * @param hash
     * @return Objsecu un objet de securité ou rien
     */
    private Objsecu unObjsecu(String hash) {
        Objsecu retour = null;
        for(Objsecu r : objss){
            for(ObjsAttrs oa : r.getObjsAttrsCollection()){
                if(oa.getAttr().equals("HASH") && oa.getVal().equals(hash)){
                    retour = r;
                    break;
                }
            }
        }
        return retour;
    }
    /**
     * La grosse procédure qui analyse les comptes, recherche les clones, fabrique
     * des rôles applicatifs (objet de sécurité), hiérarchise ces objets et simplifie
     * cette hiérarchie
     * @param fichier un fichier de résultat
     * @param objMat une demi matrice de comparaison des comptes
     * @return
     */
    public String simplifyHiera(String fichier, DemiMatrice objMat, int seuilHblt){
        EntityManager em = getEntityManager();
        List<Objsecu> ret = new LinkedList();
        List<Objsecu> ret2 = new LinkedList();
        List<Objsecu> ret3 = new LinkedList();
        String retour = null;
        unic = new UniqueArrayList();
        objss = new ArrayList();
        try {
            empreinte = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.toString());
        }
        this.seuilHblt = seuilHblt;
        //on compare les habilitants des comptes, on fabrique les rôles app = objet de sécurité
        //avec les attribut HASH et DESCRAP ,liaison habilitant et compte
        System.out.println("***** Commence la comparaison deux à deux !");
        for(Cpt1 c1: (List<Cpt1>)objMat.getListeC1()){
           for(Cpt2 c2 : (List<Cpt2>)c1.getListeC2()){
               //un peut débile je refais une cherche de compte alors que j'ai
               //la demie matrice !
               Compte cc1 = em.getReference(Compte.class, c1.getId());
               Compte cc2 = em.getReference(Compte.class, c2.getId());
               String srep = comparonsHabilitants(cc1, cc2, em);
               System.out.println("résultat comparaison="+srep);
           }
        }
        System.out.println("***** Fin la comparaison deux à deux !");
        try{
        //tout le code pour simplifier la hiérarchie créée précédement
            //on commence par trier la liste objet de sécurite
           System.out.println("**** Trier ordre1 !");
            int l = 0;
            int lPos = 0;
            try{
                for(Objsecu r : objss){
                        //r.setObjsAttrsCollection(objsAttrS.relatedAttrs(r.getId(), em));
                    if(ret.size()==0){
                        //si ret est vide on ajoute le premier noeud
                        ret.add(r);
                    }else{
                        //on boucle sur la liste ret pour trouver la bonne place au rôle
                        l = 0;
                        lPos = -1;
                        for(Objsecu rr : ret){
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
               System.err.println("Erreur ordre1 "+err.toString());
               return null;
            }
            for(Objsecu r : ret){
                System.out.println("Info ordre1 desc="+r.getDescription()+" nb cpte="+r.getCpteColl().size());
            }
            //on continue en triant à l'envers
           System.out.println("**** Trier ordre2 !");
            l = 0;
            lPos = 0;
            try{
                for(Objsecu r : ret){
                        //r.setObjsAttrsCollection(objsAttrS.relatedAttrs(r.getId()));
                    if(ret2.size()==0){
                        //si ret est vide on ajoute le premier noeud
                        ret2.add(r);
                    }else{
                        //on boucle sur la liste ret pour trouver la bonne place au rôle
                        l = 0;
                        lPos = -1;
                        for(Objsecu rr : ret){
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
                        if(lPos>=0 && lPos<ret2.size()){
                            ret2.add(lPos, r);
                        } else{
                            ret2.add(r);
                        }
                    }
                }
            }catch(Exception err){
               System.err.println("Erreur ordre2 "+err.toString());
               return null;
            }
           //on croise les deux classements pour trouver la hiérarchie des rôles app
           System.out.println("***** Début de la recherche des cousins !");
           for(Objsecu r : ret){
               for(Objsecu rr : ret2){
                    if(r.getId()==rr.getId()) break;
                    //sinon on lance la recherche des sous rôles
                    System.out.println(" cousin?="+r.getId()+" "+rr.getId());
                    r.cousin(rr);
               }
           }

           String[] hier = new String[3];
           System.out.println("**** Parcourir hiérarchie marquer les positions !");
           String prem = parcoursHieraRoles(ret,hier, em);

           System.out.println(prem);

           System.out.println("**** Boucle de persistence !");
           for(Objsecu r: ret){
                System.out.println("persiste objs="+r.getId());
                try {
                    r = em.merge(r);
                } catch (Exception ex) {
                    System.err.println("Erreur simplifyHiera "+ex);
                }
           }

           //Les rôles sont l'ordre du plus loin au plus proche, cela va permettre de couper
           //les liaisons entre rôles qui remontent trop haut
           System.out.println("**** trier ordre3 !");
            l = 0;
            lPos = 0;
            try{
                for(Objsecu r : ret){
                    if(ret3.size()==0){
                        //si ret est vide on ajoute le premier noeud
                        ret3.add(r);
                    }else{
                        //on boucle sur la liste ret pour trouver la bonne place au rôle
                        l = 0;
                        lPos = -1;
                        for(Objsecu rr : ret3){
                            // si le compte en cours contient une position plus longue ou égale
                            if(r.getPosition().length()>=rr.getPosition().length()){
                                //alors c'est un bon candidat pour être avant
                                lPos = l;
                                if(lPos==-1)lPos=0;
                                break;
                            }
                            l++;
                        }
                        if(lPos>=0 && lPos<ret.size()){
                            ret3.add(lPos, r);
                        } else{
                            ret3.add(r);
                        }
                    }

                }
            }catch(Exception err){
               System.err.println("Erreur ordre3 "+err.toString());
            }
           System.out.println("***** une boucle pour simplifier la hiérachie");
           //je constitue une liste des rôles déja traitées et je les supprimes des enfants
           //sur les noeuds supérieurs
           LinkedList<Objsecu> blackL = new LinkedList<Objsecu>();
           for(Objsecu r: ret3){
               for(Objsecu rr : blackL){
                   //un rôle enfant à comme position x.x.x son parent a x.x il
                   // ne faut pas supprimer ce lien. par contre une enfant en position
                   //x.x.x.x se retrouve dans un parent x.x alors qu'il était déjà parent
                   //d'un rôle en x.x.x dans ce cas il faut élaguer
                   //Le test suivant dit le rôle en cours (r) est plus haut dans la hiérarchie
                   //que l'enfant (rr) ET il (r) n'est pas son parent (rr)
                   if(r.getPosition().length()<rr.getPosition().length() && !(r.getPosition().equals(rr.getParent()))){
                       System.out.println("elague autre="+rr.getId()+" moi="+r.getId());
                       elaguer(rr, r);
                   }
                   //attention il faut faire le ménage des comptes des parents (r)
                   //ils n'ont pas besoin de référencer le comptes déjà consommé par leurs
                   //enfants (rr)
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
           //virer les positions calculées
           System.out.println("*** virer les positions !");
           Query q = em.createQuery("DELETE FROM ObjsAttrs where attr = 'POSRAP'");
           try{
               utx.begin();
               em.joinTransaction();
               q.executeUpdate();
               utx.commit();
           }catch(Exception err){
               System.err.println("Erreur delete posrap "+err.toString());
           }
           //virer les parent calculées
           System.out.println("*** virer les parents !");
           Query qq = em.createQuery("DELETE FROM ObjsAttrs where attr = 'PARENTRAP'");
           try{
               utx.begin();
               em.joinTransaction();
               qq.executeUpdate();
               utx.commit();
           }catch(Exception err){
               System.err.println("Erreur delete parentrap "+err.toString());
           }
           //refaire un calcul de hiérarchier et sortir
           hier = new String[3];
           System.out.println("**** Parcourir hiérarchie marquer les positions !");
           retour = parcoursHieraRoles(ret,hier, em);
           rapportObjs = retour;
           System.out.println("**** Boucle de persistence !");
           for(Objsecu r: ret){
                System.out.println("persiste objs="+r.getId());
                try {
                    r = em.merge(r);
                } catch (Exception ex) {
                    System.err.println("Erreur simplifyHiera "+ex);
                }
           }
           //fin des traitements
           for(Objsecu r: ret3){
               rapportObjs+="\nLe profil applicatif objs="+r.getId()+" contient les comptes:\n";
               for(Compte c: r.getCpteColl()){
                   rapportObjs+="   Compte="+c.getId()+" habilitants="+c.listHabilitants().toString()+"\n";
               }
           }

        }catch(Exception err){
            System.err.println("Erreur simplifyHiera "+err.toString());
        }finally{
            if (em != null) {
                em.close();
            }
        }
        return retour;
    }
   /**
    * Une procèdure qui parcours les rôles applicatifs obtenus et marque les
    * informations sur les parents ainsi que la position dans la hiérarchie
    * @param ordre une liste de rôle dans un certain ordre
    * @param hier un tableau avec le décalage
    * @return parcoursHieraRoles retourne une présentation hiérarchique des rôles
    */
   private String parcoursHieraRoles(List<Objsecu> ordre,String[] hier, EntityManager emmm){
       // une boucle pour mettre en forme la hiérarchie
       //String[] hier = new String[3];
       int iPos = 1;
       String sPos = "";
       String sId = "";
       for(Objsecu r: ordre){
           if(!(r.getSommet()) && !(r.getEnfant())){
               System.out.println("* Pas sommet et pas enfant = seul objs="+r.getId()+" hblts="+r.getHabilitants());
               sPos = ""+iPos;
               sId = ""+r.getId();
               hier[0] = hier[0]+"\nSeul pos="+sPos+" id="+sId+" nom="+r.getDescription()+" hblt="+r.getHabilitants()+" nb Cpt="+r.getCpteColl().size()+"\n";
               hier[1] = "";
               hier[2] = ""+iPos;
               iPos++;
           }
           if(r.getSommet() && !r.getEnfant()){
               System.out.println("** Sommet et pas enfant = début hiérarchie obs="+r.getId()+" hblts="+r.getHabilitants());
                sPos = ""+iPos;
                //r.setPosition(sPos);
                hier[0] = hier[0]+"\nSommet pos="+sPos+" id="+r.getId()+" nom="+r.getDescription()+" hblt="+r.getHabilitants()+" nb Cpt="+r.getCpteColl().size()+"\n";
                hier[1] = "";
                hier[2] = ""+iPos;
                iPos++;
                hier = allonsEnfants(hier, true, r, emmm);
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
    public String[] allonsEnfants(String[] s, Boolean bPrem, Objsecu rapp, EntityManager emm){
        int iPos = 0;
        //on persiste la position actuelle de l'objs
        try{
           rapp.setPosition(s[2].toString());
           utx.begin();
           emm.joinTransaction();
           rapp = emm.merge(rapp);
           utx.commit();
        }catch(Exception err){
            System.err.println("Erreur allonsEnfants posrap3 "+err.toString());
            return null;
        }

        if(!bPrem){
            s[1]+=dec;
            s[0]+=s[1]+"Enfant parent="+rapp.getParent()+" pos="+rapp.getPosition()+" id="+rapp.getId()+" nom="+rapp.getDescription()+" nb Cpt="+rapp.getCpteColl().size()+" hblt="+rapp.getHabilitants()+"\n";
        }
        String save = s[0];
        s[0] = "";
        //parcourir la liste des enfants d'un rôle app
        System.out.println("Pour tous les enfants de objs="+rapp.getId()+" position parent="+rapp.getPosition());
        for(ObjsAttrs oa: rapp.getObjsAttrsCollection()){
            if(oa.getAttr().equals("ENFANTID")){
                System.out.println("Pour ENFANTID="+oa.getVal());
                String save2 = s[0].toString();
                s[0] = "";
                iPos++;
                s[2] = rapp.getPosition() + "." + iPos;
                //on persiste la position du parent
                ObjsAttrs oaa3 = new ObjsAttrs(new Integer(TalendConnection.Aleat()), "PARENTRAP", rapp.getPosition());
                for(Objsecu r: objss){
                    if(r.getId()==new Integer(oa.getVal())){
                        oaa3.setFkobjs(r);
                        r.setParent(rapp.getPosition());
                        System.out.println("allonsenfants PARENTRAP="+rapp.getPosition()+" next position="+s[2].toString());
                        s = allonsEnfants(s, false, r, emm);
                        break;
                    }
                }
                s[0] = save2 + s[0];
                int i = s[1].length() - dec.length() + 1;
                if (i >= 0) {
                    s[1] = s[1].substring(0, i);
                }
            }
        }
        int i = s[1].length()-dec.length()+1;
        if(i>=0) s[1] = s[1].substring(0, i);
        s[0] = save+s[0];
        return s;
    }
    /**
     * Procèdure pour retirer un rôle dans la liste des enfants afin
     * d'élaguer la hiérarchie des raccourcis obtenus pendant la recherche
     * des cousins
     * @param autre le rôle à supprimer
     * @param  moi le rôle de référence
     */
    private void elaguer(Objsecu autre, Objsecu moi){
        for(ObjsAttrs ao : moi.getObjsAttrsCollection()){
            if(ao.getAttr().equals("ENFANTID") && ao.getVal().equals(""+autre.getId())){
                moi.getObjsAttrsCollection().remove(ao);
                break;
            }
        }
    }
    /**
     * procèdure qui cherche les compte de l'autre dans moi
     * et les supprime, ne faudrait-il pas faire la suppression
     * de moi en tant qu'objs dans pour les comptes ?
     * @param autre
     * @param moi
     */
    private void deprovisionne(Objsecu autre, Objsecu moi){
         for(Compte autC: autre.getCpteColl()){
            try{
                moi.getCpteColl().remove(autC);
            }catch(Exception err){
                //attention entre les erreurs existe pas / collection vide et le reste
                System.err.println("Erreur deprovisionne "+err.toString());
            }
            System.err.println("Info deprovisionne moi="+moi.getId()+" nb cpte="+moi.getCpteColl().size());
        }
    }
    /**
     * Méthode pour faire la comparaison entre deux comptes sur la base des habilitants
     * de nombreux cas sont possibles:
     * En premier on vérifie l'utilisation du profil (habilitant spécifique), s'il est utilisé
     * alors il faut que les deux comptes ai le même.
     * Ensuite on découvre une nouvelle séquence d'habilitant (clone) en commun alors on fabrique
     * l'objet de sécurité et on charge les deux comptes
     * les habilitants communs sont déjà connus, on cherche alors sur l'objet de sécurité
     * si l'association est faite et on ajoute le ou les comptes le cas échéant
     * @param autre
     * @return comparonsHabilitants La chaîne des habilitants communs séparés par ||
     */
    public String comparonsHabilitants(Compte moi,Compte autre, EntityManager emmmm) {
        String res = "";
        String sSep = "";
        LinkedList<Habilitant> lh = null;
        int iMinHbltCommun = 0;
        //si on utilise un profil il faut d'abors une egalite de profil
        if(!moi.getProfil().isEmpty()){
            if(!(moi.getProfil().equals(autre.getProfil()))) return null;
        }
        try{
            //on prépare la liste des habilitants
            lh = new LinkedList<Habilitant>();
            //première boucle d'habilitant
            for(String h1: moi.listHabilitants()){
                //deuxième boucle d'habilitant
                for(String h : autre.listHabilitants()){
                    //si pareil
                    if(h.equals(h1)){
                        //on ajoute à la liste
                        iMinHbltCommun++;
                        res += sSep.concat(h1);
                        //la vraie listes des habilitants
                        lh.add(jpaHblts.findHabilitant(h1));
                        if(sSep.equals("")){ sSep = "||";}
                        break;
                    }
                }
            }
        }catch(Exception err){
            System.err.println(Compte.class.getName()+" "+err.toString());
        }
        //si plusieurs habilitants en communs (on pourrait mettre ce seuil en variable)
        if(iMinHbltCommun>seuilHblt){
            //on ajoute la séquence dans un tableau qui n'accepte pas les doublons
            empreinte.reset();
            empreinte.update(res.getBytes());
            byte[] hash = empreinte.digest();
            //String hash = "";
            Boolean novo = false;
            if(unic.add(res)){
                novo = true;
            }
            //si nouvo est vrai alors on fait la création de l'objet de secu et l'ajout des
            //comptes direct, sinon on fait des recherches pour chacun des comptes
            //on balance sur les objet de secu pour ajouter ou modifier
            System.out.println("Trouvé habilitant en commun:"+iMinHbltCommun+" entre:"+moi.getId()+" et:"+autre.getId());
            System.out.println("hash:"+hash+" taille liste habilitant:"+lh.size()+" novo="+novo);
            addMinCommun(hashToString(hash).substring(0, 10), moi.getId(), autre.getId(), lh, objsAttrS, novo, emmmm, res);
        }
        return res;
    }
    private String hashToString(byte[] hash) {
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
    /**
     * Méthode pour vérifier et ajouter éventuellement un rôle applicatif (objet de secu) obtenus
     * à partir de la comparaison des habilitants de deux comptes
     * @param sSignature les habilitants
     * @param iCpt1 compte 1
     * @param iCpt2 compte 2
     */
    public void addMinCommun(String sSignature, int iCpt1, int iCpt2, List<Habilitant> lHblt, ObjsAttrsJ3 oOA, Boolean nouvo, EntityManager emmm, String hblt){
        //l'objet de sécurité existe il faut tester chaque compte
        if(!nouvo){
            findTrans(sSignature, iCpt1, iCpt2, emmm);
            return;
        // c'est une création complète objet de sécurité / liaison habilitant / liaison comptes
        }else{
            System.out.println("Ajoute roleApp et comptes liés c1="+iCpt1+" c2="+iCpt2);
            Integer alea = new Integer(TalendConnection.Aleat());
            Objsecu s = new Objsecu(alea);
            s.setDescription("RAPP_"+s.getPkobjs());
            s.setHbltColl(lHblt);
            //c'est du neuf !
            add2Cptes(iCpt1, iCpt2, s, false, emmm);
            System.out.println("Ajoute le Hash à="+alea);
            ObjsAttrs h = new ObjsAttrs(TalendConnection.Aleat());
            h.setFkobjs(s);
            h.setAttr("HASH");
            h.setVal(sSignature);
            Collection<ObjsAttrs> ll = s.getObjsAttrsCollection();
            ll.add(h);
            s.setObjsAttrsCollection(ll);
            objss.add(s);
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
    private Boolean findTrans(String sTrans, int iCptId1, int iCptId2, EntityManager emm){
        Boolean bRes = false;
        Objsecu os = null;
        //on doit toujours trouver un objet de sécurité
        try{
            os = unObjsecu(sTrans);
        }catch(Exception err){
            System.err.println("Rien trouvé comme roleApp pour hash="+sTrans+" err="+err.toString());
            return false;
        }
        //cela doit toujours marcher
        if(os!=null){
            System.out.println("Ajoute les ou les comptes au roleApp:"+os.getDescription()+" c1="+iCptId1+" c2="+iCptId2);
            add2Cptes(iCptId1, iCptId2, os, true, emm);
            return true;
        }else{
            System.err.println("Info Rien trouvé comme roleApp pour hash="+sTrans);
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
    public void add2Cptes(int cpt1, int cpt2, Objsecu s, Boolean edit, EntityManager emm){
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
/*                if(lecas<4){
                    lecas = trouveComptes(cpt1,cpt2, s.getPkobjs(), emm);
                } */
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
                    Compte c2 = emm.getReference(Compte.class, cpt2);
                    s.getCpteColl().add(c2);
                    System.out.println("Info add2Cptes ajoute cpte2="+cpt2+" à objs="+s.getDescription());
                    bEdit = true;
                }catch(Exception err){
                    System.err.println("Ereur dans add2Cptes cas1 "+err.toString());
                }
                break;
            case 3: //trouvé cpt2 ajoute cpt1
                try{
                    Compte c1 = emm.getReference(Compte.class, cpt1);
                    s.getCpteColl().add(c1);
                    System.out.println("Info add2Cptes ajoute cpte1="+cpt1+" à objs="+s.getDescription());
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
                    Compte c32 = emm.getReference(Compte.class, cpt2);
                    Compte c31 = emm.getReference(Compte.class, cpt1);
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
    }
    /**
     * Fonction pour vérifier la présence des comptes pour un objet de securité donné
     * utilise la table de liaison objet de sécurité / compte
     * @param iC1 id compte 1
     * @param iC2 id Compte 2
     * @param em l'entity manager
     * @return trouveComptes si 1 =trouvé compte 1, si 3 =trouvé compte 2, si 4 =trouvé les deux, si 0 rien trouvé, si -1 erreur
     */
    public int trouveComptes(int iC1, int iC2, int pkid, EntityManager em){
        int iRes = 0;
        Compte ret = null;
        try {
            Query q = em.createNativeQuery("SELECT DISTINCT PKCPTE FROM SPOPOFF.COMPTE, SPOPOFF.OBJS_CPTE WHERE PKCPTE = FKCPTE AND FKCPTE="+iC1+" AND FKOBJS="+pkid+";", Compte.class);
            //ret = (ObjsAttrs) q.getSingleResult();
            ret = (Compte) q.getSingleResult();
            //compte 1 trouvé
            iRes = 1;
        }catch(NoResultException err){
            System.out.println("Pas trouvé compte1="+iC1);
        }catch(NonUniqueResultException ex){
            System.err.println("trouvé plusieurs valeurs pour compte1="+iC1);
            return -1;
        }catch(oracle.toplink.essentials.exceptions.QueryException err){
            System.out.println("grosse erreur pour compte1="+iC1+" "+err.toString());
        }catch(java.lang.IllegalStateException ex){
            System.err.println("Erreur sur le SQL pour le compte1="+iC1);
        }
/*
        if(ret!=null){
            iRes = 1;
        }
 */
        ret = null;
        try {
            Query q = em.createNativeQuery("SELECT DISTINCT PKCPTE FROM SPOPOFF.COMPTE, SPOPOFF.OBJS_CPTE WHERE PKCPTE = FKCPTE AND FKCPTE="+iC2+" AND FKOBJS="+pkid+";", Compte.class);
            ret = (Compte) q.getSingleResult();
            //soit 3 (compte 2 trouvé) soit 4 (trouvé les deux)
            iRes += 3;
        }catch(NoResultException err){
            System.out.println("Pas trouvé compte2="+iC2);
        }catch(NonUniqueResultException ex){
            System.err.println("trouvé plusieurs valeurs pour compte2="+iC2);
            return -1;
        }catch(oracle.toplink.essentials.exceptions.QueryException err){
            System.out.println("grosse erreur pour compte2="+iC2+" "+err.toString());
        }catch(java.lang.IllegalStateException ex){
            System.err.println("trouveComptes Erreur sur le SQL pour le compte2="+iC2+" "+ex.toString());
        }
        return iRes;
    }
    /**
     * Enregistre dans un fichier les rôles
     * @param sFile
     * @param bAppend
     * @throws java.io.IOException
     */
    public void cloze(String sFile, Boolean bAppend) throws IOException{
        String str="**** Roles ****\n";
        Writer output = null;
        try{
           for(Objsecu o: findObjsecuEntities()){
                str+=o.toString()+" ";
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
    public class UniqueArrayList extends ArrayList {
        /**
         * Only add the object if there is not
         * another copy of it in the list
         */
        private int lastEqual;

        public int getLastEqual() {
            return lastEqual+1;
        }
        @Override
        public boolean add(Object obj) {
            for (int i = 0; i < size(); i++) {
                if (obj.equals(get(i))) {
                    lastEqual = i;
                    return false;
                }
            }
            return super.add(obj);
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
