/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.ObjsecuEjb;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import techDecision.entites.Objsecu;

/**
 *
 * @author spopoff
 */
public class ObjsecuConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        ObjsecuEjb controller = (ObjsecuEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objsecuJpa");
        return controller.findObjsecu(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Objsecu) {
            Objsecu o = (Objsecu) object;
            return o.getPkobjs() == null ? "" : o.getPkobjs().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: techDecision.entites.Objsecu");
        }
    }

}
