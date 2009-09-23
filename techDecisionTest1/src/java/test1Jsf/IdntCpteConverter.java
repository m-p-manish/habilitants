/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.IdntCpteEjb;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import techDecision.entites.IdntCpte;

/**
 *
 * @author spopoff
 */
public class IdntCpteConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        IdntCpteEjb controller = (IdntCpteEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "idntCpteJpa");
        return controller.findIdntCpte(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof IdntCpte) {
            IdntCpte o = (IdntCpte) object;
            return o.getPkidntCpte() == null ? "" : o.getPkidntCpte().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: techDecision.entites.IdntCpte");
        }
    }

}
