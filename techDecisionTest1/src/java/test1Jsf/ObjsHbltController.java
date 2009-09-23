/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.ObjsHbltEjb;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import techDecision.entites.ObjsHblt;
import test1Jsf.util.JsfUtil;
import daoJpa.exceptions.NonexistentEntityException;
import test1Jsf.util.PagingInfo;

/**
 *
 * @author spopoff
 */
public class ObjsHbltController {

    public ObjsHbltController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (ObjsHbltEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objsHbltJpa");
        pagingInfo = new PagingInfo();
        converter = new ObjsHbltConverter();
    }
    private ObjsHblt objsHblt = null;
    private List<ObjsHblt> objsHbltItems = null;
    private ObjsHbltEjb jpaController = null;
    private ObjsHbltConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getObjsHbltCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getObjsHbltItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findObjsHbltEntities(), false);
    }

    public SelectItem[] getObjsHbltItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findObjsHbltEntities(), true);
    }

    public ObjsHblt getObjsHblt() {
        if (objsHblt == null) {
            objsHblt = (ObjsHblt) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentObjsHblt", converter, null);
        }
        if (objsHblt == null) {
            objsHblt = new ObjsHblt();
        }
        return objsHblt;
    }

    public String listSetup() {
        reset(true);
        return "objsHblt_list";
    }

    public String createSetup() {
        reset(false);
        objsHblt = new ObjsHblt();
        return "objsHblt_create";
    }

    public String create() {
        try {
            jpaController.create(objsHblt);
            JsfUtil.addSuccessMessage("ObjsHblt was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("objsHblt_detail");
    }

    public String editSetup() {
        return scalarSetup("objsHblt_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        objsHblt = (ObjsHblt) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentObjsHblt", converter, null);
        if (objsHblt == null) {
            String requestObjsHbltString = JsfUtil.getRequestParameter("jsfcrud.currentObjsHblt");
            JsfUtil.addErrorMessage("The objsHblt with id " + requestObjsHbltString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String objsHbltString = converter.getAsString(FacesContext.getCurrentInstance(), null, objsHblt);
        String currentObjsHbltString = JsfUtil.getRequestParameter("jsfcrud.currentObjsHblt");
        if (objsHbltString == null || objsHbltString.length() == 0 || !objsHbltString.equals(currentObjsHbltString)) {
            String outcome = editSetup();
            if ("objsHblt_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit objsHblt. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(objsHblt);
            JsfUtil.addSuccessMessage("ObjsHblt was successfully updated.");
        } catch (NonexistentEntityException ne) {
            JsfUtil.addErrorMessage(ne.getLocalizedMessage());
            return listSetup();
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String destroy() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentObjsHblt");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("ObjsHblt was successfully deleted.");
        } catch (NonexistentEntityException ne) {
            JsfUtil.addErrorMessage(ne.getLocalizedMessage());
            return relatedOrListOutcome();
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<ObjsHblt> getObjsHbltItems() {
        if (objsHbltItems == null) {
            getPagingInfo();
            objsHbltItems = jpaController.findObjsHbltEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return objsHbltItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "objsHblt_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "objsHblt_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        objsHblt = null;
        objsHbltItems = null;
        pagingInfo.setItemCount(-1L);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        ObjsHblt newObjsHblt = new ObjsHblt();
        String newObjsHbltString = converter.getAsString(FacesContext.getCurrentInstance(), null, newObjsHblt);
        String objsHbltString = converter.getAsString(FacesContext.getCurrentInstance(), null, objsHblt);
        if (!newObjsHbltString.equals(objsHbltString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
