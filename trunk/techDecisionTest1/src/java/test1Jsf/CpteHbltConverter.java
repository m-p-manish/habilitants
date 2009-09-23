/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.CpteHbltEjb;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import techDecision.entites.CpteHblt;

/**
 *
 * @author spopoff
 */
public class CpteHbltConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        CpteHbltEjb controller = (CpteHbltEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cpteHbltJpa");
        return controller.findCpteHblt(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof CpteHblt) {
            CpteHblt o = (CpteHblt) object;
            return o.getPkcpteHblt() == null ? "" : o.getPkcpteHblt().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: techDecision.entites.CpteHblt");
        }
    }

}
