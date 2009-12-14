/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package techDecision.xacmlPep;

/**
 *
 * @author spopoff
 */
public interface WSclientInterface {

    String simpleQuestionService(String user, String ress, String akte);
    String getNomPdp();
    void setNomPdp(String nom);

}
