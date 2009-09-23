/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.ObjsAttrsEjb;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import techDecision.entites.ObjsAttrs;
import test1Jsf.util.JsfUtil;
import daoJpa.exceptions.NonexistentEntityException;
import test1Jsf.util.PagingInfo;

/**
 *
 * @author spopoff
 */
public class ObjsAttrsController {

    public ObjsAttrsController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (ObjsAttrsEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objsAttrsJpa");
        pagingInfo = new PagingInfo();
        converter = new ObjsAttrsConverter();
    }
    private ObjsAttrs objsAttrs = null;
    private List<ObjsAttrs> objsAttrsItems = null;
    private ObjsAttrsEjb jpaController = null;
    private ObjsAttrsConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getObjsAttrsCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getObjsAttrsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findObjsAttrsEntities(), false);
    }

    public SelectItem[] getObjsAttrsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findObjsAttrsEntities(), true);
    }

    public ObjsAttrs getObjsAttrs() {
        if (objsAttrs == null) {
            objsAttrs = (ObjsAttrs) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentObjsAttrs", converter, null);
        }
        if (objsAttrs == null) {
            objsAttrs = new ObjsAttrs();
        }
        return objsAttrs;
    }

    public String listSetup() {
        reset(true);
        return "objsAttrs_list";
    }

    public String createSetup() {
        reset(false);
        objsAttrs = new ObjsAttrs();
        return "objsAttrs_create";
    }

    public String create() {
        try {
            jpaController.create(objsAttrs);
            JsfUtil.addSuccessMessage("ObjsAttrs was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("objsAttrs_detail");
    }

    public String editSetup() {
        return scalarSetup("objsAttrs_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        objsAttrs = (ObjsAttrs) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentObjsAttrs", converter, null);
        if (objsAttrs == null) {
            String requestObjsAttrsString = JsfUtil.getRequestParameter("jsfcrud.currentObjsAttrs");
            JsfUtil.addErrorMessage("The objsAttrs with id " + requestObjsAttrsString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String objsAttrsString = converter.getAsString(FacesContext.getCurrentInstance(), null, objsAttrs);
        String currentObjsAttrsString = JsfUtil.getRequestParameter("jsfcrud.currentObjsAttrs");
        if (objsAttrsString == null || objsAttrsString.length() == 0 || !objsAttrsString.equals(currentObjsAttrsString)) {
            String outcome = editSetup();
            if ("objsAttrs_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit objsAttrs. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(objsAttrs);
            JsfUtil.addSuccessMessage("ObjsAttrs was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentObjsAttrs");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("ObjsAttrs was successfully deleted.");
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

    public List<ObjsAttrs> getObjsAttrsItems() {
        if (objsAttrsItems == null) {
            getPagingInfo();
            objsAttrsItems = jpaController.findObjsAttrsEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return objsAttrsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "objsAttrs_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "objsAttrs_list";
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
        objsAttrs = null;
        objsAttrsItems = null;
        pagingInfo.setItemCount(-1L);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        ObjsAttrs newObjsAttrs = new ObjsAttrs();
        String newObjsAttrsString = converter.getAsString(FacesContext.getCurrentInstance(), null, newObjsAttrs);
        String objsAttrsString = converter.getAsString(FacesContext.getCurrentInstance(), null, objsAttrs);
        if (!newObjsAttrsString.equals(objsAttrsString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
