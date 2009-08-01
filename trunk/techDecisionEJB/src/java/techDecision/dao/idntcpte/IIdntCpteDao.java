/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.dao.idntcpte;

import java.util.List;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.IdntCpte;

/**
 *
 * @author spopoff
 */
public interface IIdntCpteDao {

    void create(IdntCpte idntCpte) throws RollbackFailureException, Exception;

    void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    void edit(IdntCpte idntCpte) throws NonexistentEntityException, RollbackFailureException, Exception;

    IdntCpte findIdntCpte(Integer id);

    List<IdntCpte> findIdntCpteEntities();

    List<IdntCpte> findIdntCpteEntities(int maxResults, int firstResult);

    Long getIdntCpteCount();

    void init();

}
