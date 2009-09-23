/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.IdntAttrsEjb;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import techDecision.entites.IdntAttrs;

/**
 *
 * @author spopoff
 */
public class IdntAttrsConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        IdntAttrsEjb controller = (IdntAttrsEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "idntAttrsJpa");
        return controller.findIdntAttrs(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof IdntAttrs) {
            IdntAttrs o = (IdntAttrs) object;
            return o.getPkattrIdnt() == null ? "" : o.getPkattrIdnt().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: techDecision.entites.IdntAttrs");
        }
    }

}
