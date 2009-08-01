/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.dao.objsattr;

import java.util.List;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.ObjsAttrs;

/**
 *
 * @author spopoff
 */
public interface IObjsAttrsDao {

    void create(ObjsAttrs objsAttrs) throws RollbackFailureException, Exception;

    void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    void edit(ObjsAttrs objsAttrs) throws NonexistentEntityException, RollbackFailureException, Exception;

    ObjsAttrs findObjsAttrs(Integer id);

    List<ObjsAttrs> findObjsAttrsEntities();

    List<ObjsAttrs> findObjsAttrsEntities(int maxResults, int firstResult);

    Long getObjsAttrsCount();

    void init();

    List<ObjsAttrs> relatedAttrs(int objsId);
}
