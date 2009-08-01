/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.dao.idntattr;

import java.util.List;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.IdntAttrs;

/**
 *
 * @author spopoff
 */
public interface IIdntattrDao {

    void create(IdntAttrs idntAttrs) throws RollbackFailureException, Exception;

    void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    void edit(IdntAttrs idntAttrs) throws NonexistentEntityException, RollbackFailureException, Exception;

    IdntAttrs findIdntAttrs(Integer id);

    List<IdntAttrs> findIdntAttrsEntities();

    List<IdntAttrs> findIdntAttrsEntities(int maxResults, int firstResult);

    Long getIdntAttrsCount();

}
