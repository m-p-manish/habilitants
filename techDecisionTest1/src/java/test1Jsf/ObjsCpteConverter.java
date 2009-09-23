/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.ObjsCpteEjb;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import techDecision.entites.ObjsCpte;

/**
 *
 * @author spopoff
 */
public class ObjsCpteConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        ObjsCpteEjb controller = (ObjsCpteEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objsCpteJpa");
        return controller.findObjsCpte(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ObjsCpte) {
            ObjsCpte o = (ObjsCpte) object;
            return o.getPkobjsCpte() == null ? "" : o.getPkobjsCpte().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: techDecision.entites.ObjsCpte");
        }
    }

}
