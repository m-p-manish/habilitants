/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.dao.habilitant;

import java.util.List;
import techDecision.dao.exceptions.IllegalOrphanException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.PreexistingEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.Habilitant;

/**
 *
 * @author spopoff
 */
public interface IHabilitantDao {

    void create(Habilitant habilitant) throws PreexistingEntityException, RollbackFailureException, Exception;

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void edit(Habilitant habilitant) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    Habilitant findHabilitant(Integer id);

    Habilitant findHabilitant(String sVal);

    List<Habilitant> findHabilitantEntities();

    List<Habilitant> findHabilitantEntities(int maxResults, int firstResult);

    Long getHabilitantCount();

    List<Habilitant> findHabilitantEntities(boolean all, int maxResults, int firstResult);

    void init();
}
