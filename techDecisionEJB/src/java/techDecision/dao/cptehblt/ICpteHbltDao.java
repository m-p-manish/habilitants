/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.dao.cptehblt;

import java.util.List;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.CpteHblt;

/**
 *
 * @author spopoff
 */
public interface ICpteHbltDao {

    void create(CpteHblt cpteHblt) throws RollbackFailureException, Exception;

    void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    void edit(CpteHblt cpteHblt) throws NonexistentEntityException, RollbackFailureException, Exception;

    CpteHblt findCpteHblt(Integer id);

    List<CpteHblt> findCpteHbltEntities();

    List<CpteHblt> findCpteHbltEntities(int maxResults, int firstResult);

    Long getCpteHbltCount();

    void init();

}
