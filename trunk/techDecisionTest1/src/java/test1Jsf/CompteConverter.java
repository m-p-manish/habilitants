/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.CompteEjb;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import techDecision.entites.Compte;

/**
 *
 * @author spopoff
 */
public class CompteConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        CompteEjb controller = (CompteEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "compteJpa");
        return controller.findCompte(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Compte) {
            Compte o = (Compte) object;
            return o.getPkcpte() == null ? "" : o.getPkcpte().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: techDecision.entites.Compte");
        }
    }

}
