/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.ObjsHbltEjb;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import techDecision.entites.ObjsHblt;

/**
 *
 * @author spopoff
 */
public class ObjsHbltConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        ObjsHbltEjb controller = (ObjsHbltEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objsHbltJpa");
        return controller.findObjsHblt(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ObjsHblt) {
            ObjsHblt o = (ObjsHblt) object;
            return o.getPkobjsHblt() == null ? "" : o.getPkobjsHblt().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: techDecision.entites.ObjsHblt");
        }
    }

}
