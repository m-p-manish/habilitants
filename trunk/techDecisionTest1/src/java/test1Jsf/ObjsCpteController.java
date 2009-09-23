/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.ObjsCpteEjb;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import techDecision.entites.ObjsCpte;
import test1Jsf.util.JsfUtil;
import daoJpa.exceptions.NonexistentEntityException;
import test1Jsf.util.PagingInfo;

/**
 *
 * @author spopoff
 */
public class ObjsCpteController {

    public ObjsCpteController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (ObjsCpteEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objsCpteJpa");
        pagingInfo = new PagingInfo();
        converter = new ObjsCpteConverter();
    }
    private ObjsCpte objsCpte = null;
    private List<ObjsCpte> objsCpteItems = null;
    private ObjsCpteEjb jpaController = null;
    private ObjsCpteConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getObjsCpteCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getObjsCpteItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findObjsCpteEntities(), false);
    }

    public SelectItem[] getObjsCpteItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findObjsCpteEntities(), true);
    }

    public ObjsCpte getObjsCpte() {
        if (objsCpte == null) {
            objsCpte = (ObjsCpte) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentObjsCpte", converter, null);
        }
        if (objsCpte == null) {
            objsCpte = new ObjsCpte();
        }
        return objsCpte;
    }

    public String listSetup() {
        reset(true);
        return "objsCpte_list";
    }

    public String createSetup() {
        reset(false);
        objsCpte = new ObjsCpte();
        return "objsCpte_create";
    }

    public String create() {
        try {
            jpaController.create(objsCpte);
            JsfUtil.addSuccessMessage("ObjsCpte was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("objsCpte_detail");
    }

    public String editSetup() {
        return scalarSetup("objsCpte_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        objsCpte = (ObjsCpte) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentObjsCpte", converter, null);
        if (objsCpte == null) {
            String requestObjsCpteString = JsfUtil.getRequestParameter("jsfcrud.currentObjsCpte");
            JsfUtil.addErrorMessage("The objsCpte with id " + requestObjsCpteString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String objsCpteString = converter.getAsString(FacesContext.getCurrentInstance(), null, objsCpte);
        String currentObjsCpteString = JsfUtil.getRequestParameter("jsfcrud.currentObjsCpte");
        if (objsCpteString == null || objsCpteString.length() == 0 || !objsCpteString.equals(currentObjsCpteString)) {
            String outcome = editSetup();
            if ("objsCpte_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit objsCpte. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(objsCpte);
            JsfUtil.addSuccessMessage("ObjsCpte was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentObjsCpte");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("ObjsCpte was successfully deleted.");
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

    public List<ObjsCpte> getObjsCpteItems() {
        if (objsCpteItems == null) {
            getPagingInfo();
            objsCpteItems = jpaController.findObjsCpteEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return objsCpteItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "objsCpte_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "objsCpte_list";
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
        objsCpte = null;
        objsCpteItems = null;
        pagingInfo.setItemCount(-1L);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        ObjsCpte newObjsCpte = new ObjsCpte();
        String newObjsCpteString = converter.getAsString(FacesContext.getCurrentInstance(), null, newObjsCpte);
        String objsCpteString = converter.getAsString(FacesContext.getCurrentInstance(), null, objsCpte);
        if (!newObjsCpteString.equals(objsCpteString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    public List<ObjsCpte> relationObjs(int id){
        return jpaController.relatedObjs(id);
    }

}
