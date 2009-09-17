/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package theCube.roleMining;

/**
 *
 * @author spopoff
 */
public interface IIdentite extends java.io.Serializable {

    String getDept();

    String getFonction();

    int getId();

    String getName();

    /**
     * Ajoute un classement à l'identité
     * @param sVal le classement
     */
    void setClasse(String sVal);

    @Override
    String toString();

}
