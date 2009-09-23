/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.IdentiteEjb;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import techDecision.entites.Identite;

/**
 *
 * @author spopoff
 */
public class IdentiteConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        IdentiteEjb controller = (IdentiteEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "identiteJpa");
        return controller.findIdentite(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Identite) {
            Identite o = (Identite) object;
            return o.getPkidnt() == null ? "" : o.getPkidnt().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: techDecision.entites.Identite");
        }
    }

}
