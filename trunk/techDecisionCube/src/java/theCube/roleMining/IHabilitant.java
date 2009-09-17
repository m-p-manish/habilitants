package theCube.roleMining;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;

/**
 *
 * @author spopoff
 */
public interface IHabilitant extends Serializable {

    @Override
    boolean equals(Object obj);

    int getId();

    String getName();

    int getType();

    @Override
    int hashCode();

    @Override
    String toString();
    
}
