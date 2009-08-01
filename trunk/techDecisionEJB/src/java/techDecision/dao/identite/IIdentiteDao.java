/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.dao.identite;

import java.util.List;
import techDecision.dao.exceptions.*;
import techDecision.entites.Identite;

/**
 *
 * @author spopoff
 */
public interface IIdentiteDao {

    void create(Identite identite) throws PreexistingEntityException, RollbackFailureException, Exception;

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void edit(Identite identite) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    Identite findIdentite(Integer id);

    Identite findIdentite(String nom);

    List<Identite> findIdentiteEntities();

    List<Identite> findIdentiteEntities(int maxResults, int firstResult);

    Long getIdentiteCount();

    List<Identite> findIdentiteWhere(String sWhere) throws TechDecisionErreurs;
}
