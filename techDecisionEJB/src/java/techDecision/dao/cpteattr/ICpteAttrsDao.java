/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.dao.cpteattr;

import java.util.List;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.CpteAttrs;

/**
 *
 * @author spopoff
 */
public interface ICpteAttrsDao {

    void create(CpteAttrs cpteAttrs) throws RollbackFailureException, Exception;

    void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    void edit(CpteAttrs cpteAttrs) throws NonexistentEntityException, RollbackFailureException, Exception;

    CpteAttrs findCpteAttrs(Integer id);

    List<CpteAttrs> findCpteAttrsEntities();

    List<CpteAttrs> findCpteAttrsEntities(int maxResults, int firstResult);

    Long getCpteAttrsCount();

    void init();

}
