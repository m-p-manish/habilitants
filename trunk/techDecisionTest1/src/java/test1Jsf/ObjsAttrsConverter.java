/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.ObjsAttrsEjb;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import techDecision.entites.ObjsAttrs;

/**
 *
 * @author spopoff
 */
public class ObjsAttrsConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        ObjsAttrsEjb controller = (ObjsAttrsEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objsAttrsJpa");
        return controller.findObjsAttrs(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ObjsAttrs) {
            ObjsAttrs o = (ObjsAttrs) object;
            return o.getPkattrObjs() == null ? "" : o.getPkattrObjs().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: techDecision.entites.ObjsAttrs");
        }
    }

}
