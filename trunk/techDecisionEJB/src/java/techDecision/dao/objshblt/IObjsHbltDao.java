/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.dao.objshblt;

import java.util.List;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.ObjsHblt;

/**
 *
 * @author spopoff
 */
public interface IObjsHbltDao {

    void create(ObjsHblt objsHblt) throws RollbackFailureException, Exception;

    void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    void edit(ObjsHblt objsHblt) throws NonexistentEntityException, RollbackFailureException, Exception;

    ObjsHblt findObjsHblt(Integer id);

    List<ObjsHblt> findObjsHbltEntities();

    List<ObjsHblt> findObjsHbltEntities(int maxResults, int firstResult);

    Long getObjsHbltCount();

}
