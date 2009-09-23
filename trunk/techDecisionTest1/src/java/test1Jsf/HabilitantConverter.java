/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.HabilitantEjb;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import techDecision.entites.Habilitant;

/**
 *
 * @author spopoff
 */
public class HabilitantConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        HabilitantEjb controller = (HabilitantEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "habilitantJpa");
        return controller.findHabilitant(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Habilitant) {
            Habilitant o = (Habilitant) object;
            return o.getPkhblt() == null ? "" : o.getPkhblt().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: techDecision.entites.Habilitant");
        }
    }

}
