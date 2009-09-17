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
package techDecision2.dao.groupe;

import java.util.List;
import java.util.ArrayList;
import javax.ejb.Stateless;
import techDecision2.exception.*;
import techDecision2.entites.Groupe;
import techDecision2.entites.Memberof;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public class GroupeBean implements GroupeRemote, GroupeLocal {
    @PersistenceContext(unitName="techDecisionEJB2PU")
    private EntityManager em;

    public void create(Groupe groupe) throws TechDecision2Erreurs, Exception {
        if (groupe.getMemberofCollection1() == null) {
            groupe.setMemberofCollection1(new ArrayList<Memberof>());
        }
        try {
            List<Memberof> attachedMemberofCollection = new ArrayList<Memberof>();
            for (Memberof memberofCollectionMemberofToAttach : groupe.getMemberofCollection1()) {
                memberofCollectionMemberofToAttach = em.getReference(memberofCollectionMemberofToAttach.getClass(), memberofCollectionMemberofToAttach.getPkmemberof());
                attachedMemberofCollection.add(memberofCollectionMemberofToAttach);
            }
            groupe.setMemberofCollection1(attachedMemberofCollection);
            List<Memberof> attachedMemberofCollection1 = new ArrayList<Memberof>();
            for (Memberof memberofCollection1MemberofToAttach : groupe.getMemberofCollection1()) {
                memberofCollection1MemberofToAttach = em.getReference(memberofCollection1MemberofToAttach.getClass(), memberofCollection1MemberofToAttach.getPkmemberof());
                attachedMemberofCollection1.add(memberofCollection1MemberofToAttach);
            }
            groupe.setMemberofCollection1(attachedMemberofCollection1);
            em.persist(groupe);
            for (Memberof memberofCollectionMemberof : groupe.getMemberofCollection1()) {
                Groupe oldFkgroupenestedOfMemberofCollectionMemberof = memberofCollectionMemberof.getFkgroupenested();
                memberofCollectionMemberof.setFkgroupenested(groupe);
                memberofCollectionMemberof = em.merge(memberofCollectionMemberof);
                if (oldFkgroupenestedOfMemberofCollectionMemberof != null) {
                    oldFkgroupenestedOfMemberofCollectionMemberof.getMemberofCollection1().remove(memberofCollectionMemberof);
                    oldFkgroupenestedOfMemberofCollectionMemberof = em.merge(oldFkgroupenestedOfMemberofCollectionMemberof);
                }
            }
            for (Memberof memberofCollection1Memberof : groupe.getMemberofCollection1()) {
                Groupe oldFkgroupeOfMemberofCollection1Memberof = memberofCollection1Memberof.getFkgroupe();
                memberofCollection1Memberof.setFkgroupe(groupe);
                memberofCollection1Memberof = em.merge(memberofCollection1Memberof);
                if (oldFkgroupeOfMemberofCollection1Memberof != null) {
                    oldFkgroupeOfMemberofCollection1Memberof.getMemberofCollection1().remove(memberofCollection1Memberof);
                    oldFkgroupeOfMemberofCollection1Memberof = em.merge(oldFkgroupeOfMemberofCollection1Memberof);
                }
            }
        } catch (Exception ex) {
            if (findGroupe(groupe.getPkgroupe()) != null) {
                throw new TechDecision2Erreurs(new PreexistingEntityException("Groupe " + groupe + " already exists.", ex),1);
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws TechDecision2Erreurs, Exception {
        try {
            Groupe groupe;
            try {
                groupe = em.getReference(Groupe.class, id);
                groupe.getPkgroupe();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The groupe with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Memberof> memberofCollection1OrphanCheck = groupe.getMemberofCollection1();
            for (Memberof memberofCollection1OrphanCheckMemberof : memberofCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Groupe (" + groupe + ") cannot be destroyed since the Memberof " + memberofCollection1OrphanCheckMemberof + " in its memberofCollection1 field has a non-nullable fkgroupe field.");
            }
            if (illegalOrphanMessages != null) {
                throw new TechDecision2Erreurs(new IllegalOrphanException(illegalOrphanMessages),2);
            }
            List<Memberof> memberofCollection = groupe.getMemberofCollection1();
            for (Memberof memberofCollectionMemberof : memberofCollection) {
                memberofCollectionMemberof.setFkgroupenested(null);
                memberofCollectionMemberof = em.merge(memberofCollectionMemberof);
            }
            em.remove(groupe);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public void edit(Groupe groupe) throws TechDecision2Erreurs, Exception {
        try {
            Groupe persistentGroupe = em.find(Groupe.class, groupe.getPkgroupe());
            List<Memberof> memberofCollection1Old = persistentGroupe.getMemberofCollection1();
            List<Memberof> memberofCollection1New = groupe.getMemberofCollection1();
            List<String> illegalOrphanMessages = null;
            for (Memberof memberofCollection1OldMemberof : memberofCollection1Old) {
                if (!memberofCollection1New.contains(memberofCollection1OldMemberof)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Memberof " + memberofCollection1OldMemberof + " since its fkgroupe field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new TechDecision2Erreurs(new IllegalOrphanException(illegalOrphanMessages),3);
            }
            List<Memberof> attachedMemberofCollection1New = new ArrayList<Memberof>();
            for (Memberof memberofCollection1NewMemberofToAttach : memberofCollection1New) {
                memberofCollection1NewMemberofToAttach = em.getReference(memberofCollection1NewMemberofToAttach.getClass(), memberofCollection1NewMemberofToAttach.getPkmemberof());
                attachedMemberofCollection1New.add(memberofCollection1NewMemberofToAttach);
            }
            memberofCollection1New = attachedMemberofCollection1New;
            groupe.setMemberofCollection1(memberofCollection1New);
            groupe = em.merge(groupe);
            for (Memberof memberofCollection1NewMemberof : memberofCollection1New) {
                if (!memberofCollection1Old.contains(memberofCollection1NewMemberof)) {
                    Groupe oldFkgroupeOfMemberofCollection1NewMemberof = memberofCollection1NewMemberof.getFkgroupe();
                    memberofCollection1NewMemberof.setFkgroupe(groupe);
                    memberofCollection1NewMemberof = em.merge(memberofCollection1NewMemberof);
                    if (oldFkgroupeOfMemberofCollection1NewMemberof != null && !oldFkgroupeOfMemberofCollection1NewMemberof.equals(groupe)) {
                        oldFkgroupeOfMemberofCollection1NewMemberof.getMemberofCollection1().remove(memberofCollection1NewMemberof);
                        oldFkgroupeOfMemberofCollection1NewMemberof = em.merge(oldFkgroupeOfMemberofCollection1NewMemberof);
                    }
                }
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = groupe.getPkgroupe();
                if (findGroupe(id) == null) {
                    throw new TechDecision2Erreurs(new NonexistentEntityException("The groupe with id " + id + " no longer exists."),4);
                }
            }
            throw new TechDecision2Erreurs(ex,5);
        } finally {
        }
    }

    public Groupe findGroupe(Integer id) {
        try {
            return em.find(Groupe.class, id);
        } finally {
        }
    }

    public List<Groupe> findGroupeEntities() {
        return findGroupeEntities(true, -1, -1);
    }

    public List<Groupe> findGroupeEntities(int maxResults, int firstResult) {
        return findGroupeEntities(false, maxResults, firstResult);
    }

    public int getGroupeCount() {
        try {
            return ((Long) em.createQuery("select count(o) from Groupe as o").getSingleResult()).intValue();
        } finally {
        }
    }
    
    private List<Groupe> findGroupeEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from Groupe as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }
}
