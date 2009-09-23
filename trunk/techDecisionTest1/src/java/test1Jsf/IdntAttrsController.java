/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.IdntAttrsEjb;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import techDecision.entites.IdntAttrs;
import test1Jsf.util.JsfUtil;
import daoJpa.exceptions.NonexistentEntityException;
import test1Jsf.util.PagingInfo;

/**
 *
 * @author spopoff
 */
public class IdntAttrsController {

    public IdntAttrsController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (IdntAttrsEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "idntAttrsJpa");
        pagingInfo = new PagingInfo();
        converter = new IdntAttrsConverter();
    }
    private IdntAttrs idntAttrs = null;
    private List<IdntAttrs> idntAttrsItems = null;
    private IdntAttrsEjb jpaController = null;
    private IdntAttrsConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getIdntAttrsCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getIdntAttrsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findIdntAttrsEntities(), false);
    }

    public SelectItem[] getIdntAttrsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findIdntAttrsEntities(), true);
    }

    public IdntAttrs getIdntAttrs() {
        if (idntAttrs == null) {
            idntAttrs = (IdntAttrs) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentIdntAttrs", converter, null);
        }
        if (idntAttrs == null) {
            idntAttrs = new IdntAttrs();
        }
        return idntAttrs;
    }

    public String listSetup() {
        reset(true);
        return "idntAttrs_list";
    }

    public String createSetup() {
        reset(false);
        idntAttrs = new IdntAttrs();
        return "idntAttrs_create";
    }

    public String create() {
        try {
            jpaController.create(idntAttrs);
            JsfUtil.addSuccessMessage("IdntAttrs was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("idntAttrs_detail");
    }

    public String editSetup() {
        return scalarSetup("idntAttrs_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        idntAttrs = (IdntAttrs) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentIdntAttrs", converter, null);
        if (idntAttrs == null) {
            String requestIdntAttrsString = JsfUtil.getRequestParameter("jsfcrud.currentIdntAttrs");
            JsfUtil.addErrorMessage("The idntAttrs with id " + requestIdntAttrsString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String idntAttrsString = converter.getAsString(FacesContext.getCurrentInstance(), null, idntAttrs);
        String currentIdntAttrsString = JsfUtil.getRequestParameter("jsfcrud.currentIdntAttrs");
        if (idntAttrsString == null || idntAttrsString.length() == 0 || !idntAttrsString.equals(currentIdntAttrsString)) {
            String outcome = editSetup();
            if ("idntAttrs_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit idntAttrs. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(idntAttrs);
            JsfUtil.addSuccessMessage("IdntAttrs was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentIdntAttrs");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("IdntAttrs was successfully deleted.");
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

    public List<IdntAttrs> getIdntAttrsItems() {
        if (idntAttrsItems == null) {
            getPagingInfo();
            idntAttrsItems = jpaController.findIdntAttrsEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return idntAttrsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "idntAttrs_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "idntAttrs_list";
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
        idntAttrs = null;
        idntAttrsItems = null;
        pagingInfo.setItemCount(-1L);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        IdntAttrs newIdntAttrs = new IdntAttrs();
        String newIdntAttrsString = converter.getAsString(FacesContext.getCurrentInstance(), null, newIdntAttrs);
        String idntAttrsString = converter.getAsString(FacesContext.getCurrentInstance(), null, idntAttrs);
        if (!newIdntAttrsString.equals(idntAttrsString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
