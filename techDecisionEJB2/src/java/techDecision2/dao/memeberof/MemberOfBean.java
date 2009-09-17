/*
Copyright Stéphane Georges Popoff, (avril 2009)

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
package techDecision2.dao.memeberof;

import java.util.List;
//import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import techDecision2.exception.*;
import techDecision2.entites.Memberof;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import techDecision2.entites.Groupe;
import techDecision2.tools.UniqueArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import techDecision.dao.identite.IIdentiteDaoLocal;
import techDecision.entites.Identite;
import javax.ejb.EJB;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.3
 */
@EJB(name="ejbRefIdnt",beanInterface=techDecision.dao.identite.IIdentiteDaoLocal.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public class MemberOfBean implements MemberOfRemote, MemberOfLocal {
    @PersistenceContext(unitName="techDecisionEJB2PU")
    private EntityManager em;
    private List<Groupe> deepGroup = null;
    private Groupe oldG = null;
    private IIdentiteDaoLocal ejbUtil;
    private List<Identite> deepIdnt = null;
    
    public void create(Memberof memberof) throws TechDecision2Erreurs, Exception {
        try {
            Groupe fkgroupenested = memberof.getFkgroupenested();
            if (fkgroupenested != null) {
                fkgroupenested = em.getReference(fkgroupenested.getClass(), fkgroupenested.getPkgroupe());
                memberof.setFkgroupenested(fkgroupenested);
            }
            Groupe fkgroupe = memberof.getFkgroupe();
            if (fkgroupe != null) {
                fkgroupe = em.getReference(fkgroupe.getClass(), fkgroupe.getPkgroupe());
                memberof.setFkgroupe(fkgroupe);
            }
            em.persist(memberof);
            if (fkgroupenested != null) {
                fkgroupenested.getMemberofCollection1().add(memberof);
                fkgroupenested = em.merge(fkgroupenested);
            }
            if (fkgroupe != null) {
                fkgroupe.getMemberofCollection1().add(memberof);
                fkgroupe = em.merge(fkgroupe);
            }
        } catch (Exception ex) {
            if (findMemberof(memberof.getPkmemberof()) != null) {
                throw new TechDecision2Erreurs(new PreexistingEntityException("Memberof " + memberof + " already exists.", ex),6);
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws TechDecision2Erreurs, Exception {
        try {
            Memberof memberof;
            try {
                memberof = em.getReference(Memberof.class, id);
                memberof.getPkmemberof();
            } catch (EntityNotFoundException enfe) {
                throw new TechDecision2Erreurs(new NonexistentEntityException("The memberof with id " + id + " no longer exists.", enfe),7);
            }
            Groupe fkgroupenested = memberof.getFkgroupenested();
            if (fkgroupenested != null) {
                fkgroupenested.getMemberofCollection1().remove(memberof);
                fkgroupenested = em.merge(fkgroupenested);
            }
            Groupe fkgroupe = memberof.getFkgroupe();
            if (fkgroupe != null) {
                fkgroupe.getMemberofCollection1().remove(memberof);
                fkgroupe = em.merge(fkgroupe);
            }
            em.remove(memberof);
        } catch (Exception ex) {
            throw new TechDecision2Erreurs(ex,8);
        } finally {
        }
    }

    public void edit(Memberof memberof) throws TechDecision2Erreurs, Exception {
        try {
            Memberof persistentMemberof = em.find(Memberof.class, memberof.getPkmemberof());
            Groupe fkgroupenestedOld = persistentMemberof.getFkgroupenested();
            Groupe fkgroupenestedNew = memberof.getFkgroupenested();
            Groupe fkgroupeOld = persistentMemberof.getFkgroupe();
            Groupe fkgroupeNew = memberof.getFkgroupe();
            if (fkgroupenestedNew != null) {
                fkgroupenestedNew = em.getReference(fkgroupenestedNew.getClass(), fkgroupenestedNew.getPkgroupe());
                memberof.setFkgroupenested(fkgroupenestedNew);
            }
            if (fkgroupeNew != null) {
                fkgroupeNew = em.getReference(fkgroupeNew.getClass(), fkgroupeNew.getPkgroupe());
                memberof.setFkgroupe(fkgroupeNew);
            }
            memberof = em.merge(memberof);
            if (fkgroupenestedOld != null && !fkgroupenestedOld.equals(fkgroupenestedNew)) {
                fkgroupenestedOld.getMemberofCollection1().remove(memberof);
                fkgroupenestedOld = em.merge(fkgroupenestedOld);
            }
            if (fkgroupenestedNew != null && !fkgroupenestedNew.equals(fkgroupenestedOld)) {
                fkgroupenestedNew.getMemberofCollection1().add(memberof);
                fkgroupenestedNew = em.merge(fkgroupenestedNew);
            }
            if (fkgroupeOld != null && !fkgroupeOld.equals(fkgroupeNew)) {
                fkgroupeOld.getMemberofCollection1().remove(memberof);
                fkgroupeOld = em.merge(fkgroupeOld);
            }
            if (fkgroupeNew != null && !fkgroupeNew.equals(fkgroupeOld)) {
                fkgroupeNew.getMemberofCollection1().add(memberof);
                fkgroupeNew = em.merge(fkgroupeNew);
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = memberof.getPkmemberof();
                if (findMemberof(id) == null) {
                    throw new TechDecision2Erreurs(new NonexistentEntityException("The memberof with id " + id + " no longer exists."),9);
                }
            }
            throw ex;
        } finally {
        }
    }

    public Memberof findMemberof(Integer id) {
        try {
            return em.find(Memberof.class, id);
        } finally {
        }
    }

    public List<Memberof> findMemberofEntities() {
        return findMemberofEntities(true, -1, -1);
    }

    public List<Memberof> findMemberofEntities(int maxResults, int firstResult) {
        return findMemberofEntities(false, maxResults, firstResult);
    }

    public int getMemberofCount() {
        try {
            return ((Long) em.createQuery("select count(o) from Memberof as o").getSingleResult()).intValue();
        } finally {
        }
    }

    public List<Memberof> relatedGroup(int groupId) throws TechDecision2Erreurs{
        List<Memberof> ret = null;
        try {
            Query qHbltVal = em.createNativeQuery("SELECT * FROM MEMBEROF WHERE FKGROUPE = ?fkGroupe", Memberof.class);
            qHbltVal.setParameter("fkGroupe", groupId);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            throw new TechDecision2Erreurs(err,91);
        }
        return ret;
    }

    private List<Memberof> findMemberofEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from Memberof as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public List<Groupe> relatedGroupDeep(int groupId, boolean start) throws TechDecision2Erreurs{
        if(start) deepGroup = new UniqueArrayList();
        for(Memberof unMof : relatedGroup(groupId)){
            if(!(unMof.getFkgroupenested()==null)){
                if(deepGroup.add(unMof.getFkgroupenested())){
                    relatedGroupDeep(unMof.getFkgroupenested().getPkgroupe(),false);
                }else{
                    throw new TechDecision2Erreurs("Liaison circulaire dans emboitement de groupe",90);
                }
            }
        }
        return deepGroup;
    }

    public List<Identite> relatedIdentite(int grpId) throws TechDecision2Erreurs {
        Context ctx = null;
        Integer pkIdnt = null;
        try{
            try {
                ctx = new InitialContext();
            } catch (NamingException ex) {
                throw new TechDecision2Erreurs(ex,96);
            }
            try {
                ejbUtil = (IIdentiteDaoLocal) ctx.lookup("java:comp/env/ejbRefIdnt");
                ejbUtil.init();
            } catch (NamingException ex) {
                throw new TechDecision2Erreurs(ex,97);
            }
            deepIdnt = new UniqueArrayList();
            for(Memberof unMof : relatedGroup(grpId)){
               try{
                    pkIdnt = unMof.getFkidentity();
               }catch(Exception err){
                   throw new TechDecision2Erreurs(err,98);
               }
               if(!(pkIdnt==null)){
                   try {
                        deepIdnt.add(ejbUtil.findIdentite(pkIdnt));
                   }catch(Exception err){
                       throw new TechDecision2Erreurs(err,99);
                   }
                }
            }
            deepGroup = relatedGroupDeep(grpId,true);
        }catch(TechDecision2Erreurs err){
            throw new TechDecision2Erreurs(err,95);
        }
         return deepIdnt;
    }

    public List<Identite> relatedIdentiteDeep(int grpId, boolean start) throws TechDecision2Erreurs {
        Context ctx = null;
        Integer pkIdnt = null;
        try{
            try {
                ctx = new InitialContext();
            } catch (NamingException ex) {
                throw new TechDecision2Erreurs(ex,80);
            }
            try {
                ejbUtil = (IIdentiteDaoLocal) ctx.lookup("java:comp/env/ejbRefIdnt");
                ejbUtil.init();
            } catch (NamingException ex) {
                throw new TechDecision2Erreurs(ex,81);
            }
            deepIdnt = new UniqueArrayList();
            for(Memberof unMof : relatedGroup(grpId)){
               try{
                    pkIdnt = unMof.getFkidentity();
               }catch(Exception err){
                   throw new TechDecision2Erreurs(err,82);
               }
               if(!(pkIdnt==null)){
                   try {
                        deepIdnt.add(ejbUtil.findIdentite(pkIdnt));
                   }catch(Exception err){
                       throw new TechDecision2Erreurs(err,83);
                   }
                }
            }
            deepGroup = relatedGroupDeep(grpId,true);
            for(Groupe grp : deepGroup){
                relatedIdnt(grp.getPkgroupe());
            }
        }catch(TechDecision2Erreurs err){
            throw new TechDecision2Erreurs(err,84);
        }
         return deepIdnt;
    }
    private void relatedIdnt(int grpId) throws TechDecision2Erreurs {
        Integer pkIdnt = null;
        try{
            for(Memberof unMof : relatedGroup(grpId)){
               try{
                    pkIdnt = unMof.getFkidentity();
               }catch(Exception err){
                   throw new TechDecision2Erreurs(err,85);
               }
               if(!(pkIdnt==null)){
                   try {
                        deepIdnt.add(ejbUtil.findIdentite(pkIdnt));
                   }catch(Exception err){
                       throw new TechDecision2Erreurs(err,86);
                   }
                }
            }
        }catch(TechDecision2Erreurs err){
            throw new TechDecision2Erreurs(err,87);
        }
    }

}
