/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.dao.objsecu;

import java.util.List;
import techDecision.dao.exceptions.IllegalOrphanException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.PreexistingEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.Objsecu;

/**
 *
 * @author spopoff
 */
public interface IObjesecuDao {

    void create(Objsecu objsecu) throws PreexistingEntityException, RollbackFailureException, Exception;

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void edit(Objsecu objsecu) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    Objsecu findObjsecu(Integer id);

    List<Objsecu> findObjsecuEntities();

    List<Objsecu> findObjsecuEntities(int maxResults, int firstResult);

    Long getObjsecuCount();

    void init();
}
