/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.dao.objscpte;

import java.util.List;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.ObjsCpte;

/**
 *
 * @author spopoff
 */
public interface IObjsCpteDao {

    void create(ObjsCpte objsCpte) throws RollbackFailureException, Exception;

    void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    void edit(ObjsCpte objsCpte) throws NonexistentEntityException, RollbackFailureException, Exception;

    ObjsCpte findObjsCpte(Integer id);

    List<ObjsCpte> findObjsCpteEntities();

    List<ObjsCpte> findObjsCpteEntities(int maxResults, int firstResult);

    Long getObjsCpteCount();
    
    void init();

    List<ObjsCpte> relatedObjs(int cpteId);

    List<ObjsCpte> relatedCpte(int objsId);

}
