/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.CpteAttrsEjb;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import techDecision.entites.CpteAttrs;

/**
 *
 * @author spopoff
 */
public class CpteAttrsConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        CpteAttrsEjb controller = (CpteAttrsEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cpteAttrsJpa");
        return controller.findCpteAttrs(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof CpteAttrs) {
            CpteAttrs o = (CpteAttrs) object;
            return o.getPkattrCpte() == null ? "" : o.getPkattrCpte().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: techDecision.entites.CpteAttrs");
        }
    }

}
