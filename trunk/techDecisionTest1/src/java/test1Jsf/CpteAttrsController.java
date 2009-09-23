/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.CpteAttrsEjb;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import techDecision.entites.CpteAttrs;
import test1Jsf.util.JsfUtil;
import daoJpa.exceptions.NonexistentEntityException;
import test1Jsf.util.PagingInfo;

/**
 *
 * @author spopoff
 */
public class CpteAttrsController {

    public CpteAttrsController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (CpteAttrsEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cpteAttrsJpa");
        pagingInfo = new PagingInfo();
        converter = new CpteAttrsConverter();
    }
    private CpteAttrs cpteAttrs = null;
    private List<CpteAttrs> cpteAttrsItems = null;
    private CpteAttrsEjb jpaController = null;
    private CpteAttrsConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getCpteAttrsCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getCpteAttrsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findCpteAttrsEntities(), false);
    }

    public SelectItem[] getCpteAttrsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findCpteAttrsEntities(), true);
    }

    public CpteAttrs getCpteAttrs() {
        if (cpteAttrs == null) {
            cpteAttrs = (CpteAttrs) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCpteAttrs", converter, null);
        }
        if (cpteAttrs == null) {
            cpteAttrs = new CpteAttrs();
        }
        return cpteAttrs;
    }

    public String listSetup() {
        reset(true);
        return "cpteAttrs_list";
    }

    public String createSetup() {
        reset(false);
        cpteAttrs = new CpteAttrs();
        return "cpteAttrs_create";
    }

    public String create() {
        try {
            jpaController.create(cpteAttrs);
            JsfUtil.addSuccessMessage("CpteAttrs was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("cpteAttrs_detail");
    }

    public String editSetup() {
        return scalarSetup("cpteAttrs_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        cpteAttrs = (CpteAttrs) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCpteAttrs", converter, null);
        if (cpteAttrs == null) {
            String requestCpteAttrsString = JsfUtil.getRequestParameter("jsfcrud.currentCpteAttrs");
            JsfUtil.addErrorMessage("The cpteAttrs with id " + requestCpteAttrsString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String cpteAttrsString = converter.getAsString(FacesContext.getCurrentInstance(), null, cpteAttrs);
        String currentCpteAttrsString = JsfUtil.getRequestParameter("jsfcrud.currentCpteAttrs");
        if (cpteAttrsString == null || cpteAttrsString.length() == 0 || !cpteAttrsString.equals(currentCpteAttrsString)) {
            String outcome = editSetup();
            if ("cpteAttrs_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit cpteAttrs. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(cpteAttrs);
            JsfUtil.addSuccessMessage("CpteAttrs was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCpteAttrs");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("CpteAttrs was successfully deleted.");
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

    public List<CpteAttrs> getCpteAttrsItems() {
        if (cpteAttrsItems == null) {
            getPagingInfo();
            cpteAttrsItems = jpaController.findCpteAttrsEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return cpteAttrsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "cpteAttrs_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "cpteAttrs_list";
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
        cpteAttrs = null;
        cpteAttrsItems = null;
        pagingInfo.setItemCount(-1L);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        CpteAttrs newCpteAttrs = new CpteAttrs();
        String newCpteAttrsString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCpteAttrs);
        String cpteAttrsString = converter.getAsString(FacesContext.getCurrentInstance(), null, cpteAttrs);
        if (!newCpteAttrsString.equals(cpteAttrsString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
