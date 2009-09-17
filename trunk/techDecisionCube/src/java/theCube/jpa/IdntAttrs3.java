/*
Copyright Stéphane Georges Popoff, (mai 2009)

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

import java.util.List;
import javax.persistence.Query;
import theCube.entities.IdntAttrs;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
public class IdntAttrs3 {
    @PersistenceContext(unitName="techDecisionCubePU")
    private EntityManager em ;

    public List<IdntAttrs> findIdntAttrsEntities() {
        return findIdntAttrsEntities(true, -1, -1);
    }

    public List<IdntAttrs> findIdntAttrsEntities(int maxResults, int firstResult) {
        return findIdntAttrsEntities(false, maxResults, firstResult);
    }

    public List<IdntAttrs> findIdntAttrsEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createNativeQuery("SELECT * FROM SPOPOFF.IDNT_ATTRS", IdntAttrs.class);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public IdntAttrs findIdntAttrs(Integer id) {
        try {
            return em.find(IdntAttrs.class, id);
        } finally {
        }
    }

    public int getIdntAttrsCount() {
        if(em==null) return 0;
        try {
            return ((Long) em.createQuery("select count(o) from IdntAttrs as o").getSingleResult()).intValue();
        } finally {
        }
    }
    public List<IdntAttrs> relatedAttrs(int idntId){
        List<IdntAttrs> ret = null;
        try {
            Query qHbltVal = em.createNativeQuery("SELECT * FROM IDNT_ATTRS WHERE FKIDNT = ?idntID", IdntAttrs.class);
            qHbltVal.setParameter("idntID", idntId);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            String s = err.toString();
        }
        return ret;
    }

}
