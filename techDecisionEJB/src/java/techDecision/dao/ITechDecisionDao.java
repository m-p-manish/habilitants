/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.dao;

import techDecision.dao.exceptions.TechDecisionErreurs;
import techDecision.entites.*;

/**
 *
 * @author spopoff
 */
public interface ITechDecisionDao {

    void createCompte(Compte cpte) throws TechDecisionErreurs;

    void init();
    void createIdentite(Identite idnt) throws TechDecisionErreurs;
    void lierIdntCpte(int idIdnt, int idCpte) throws TechDecisionErreurs;

}
