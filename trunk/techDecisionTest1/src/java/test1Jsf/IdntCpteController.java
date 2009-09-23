/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.IdntCpteEjb;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import techDecision.entites.IdntCpte;
import test1Jsf.util.JsfUtil;
import daoJpa.exceptions.NonexistentEntityException;
import test1Jsf.util.PagingInfo;

/**
 *
 * @author spopoff
 */
public class IdntCpteController {

    public IdntCpteController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (IdntCpteEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "idntCpteJpa");
        pagingInfo = new PagingInfo();
        converter = new IdntCpteConverter();
    }
    private IdntCpte idntCpte = null;
    private List<IdntCpte> idntCpteItems = null;
    private IdntCpteEjb jpaController = null;
    private IdntCpteConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getIdntCpteCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getIdntCpteItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findIdntCpteEntities(), false);
    }

    public SelectItem[] getIdntCpteItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findIdntCpteEntities(), true);
    }

    public IdntCpte getIdntCpte() {
        if (idntCpte == null) {
            idntCpte = (IdntCpte) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentIdntCpte", converter, null);
        }
        if (idntCpte == null) {
            idntCpte = new IdntCpte();
        }
        return idntCpte;
    }

    public String listSetup() {
        reset(true);
        return "idntCpte_list";
    }

    public String createSetup() {
        reset(false);
        idntCpte = new IdntCpte();
        return "idntCpte_create";
    }

    public String create() {
        try {
            jpaController.create(idntCpte);
            JsfUtil.addSuccessMessage("IdntCpte was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("idntCpte_detail");
    }

    public String editSetup() {
        return scalarSetup("idntCpte_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        idntCpte = (IdntCpte) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentIdntCpte", converter, null);
        if (idntCpte == null) {
            String requestIdntCpteString = JsfUtil.getRequestParameter("jsfcrud.currentIdntCpte");
            JsfUtil.addErrorMessage("The idntCpte with id " + requestIdntCpteString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String idntCpteString = converter.getAsString(FacesContext.getCurrentInstance(), null, idntCpte);
        String currentIdntCpteString = JsfUtil.getRequestParameter("jsfcrud.currentIdntCpte");
        if (idntCpteString == null || idntCpteString.length() == 0 || !idntCpteString.equals(currentIdntCpteString)) {
            String outcome = editSetup();
            if ("idntCpte_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit idntCpte. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(idntCpte);
            JsfUtil.addSuccessMessage("IdntCpte was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentIdntCpte");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("IdntCpte was successfully deleted.");
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

    public List<IdntCpte> getIdntCpteItems() {
        if (idntCpteItems == null) {
            getPagingInfo();
            idntCpteItems = jpaController.findIdntCpteEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return idntCpteItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "idntCpte_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "idntCpte_list";
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
        idntCpte = null;
        idntCpteItems = null;
        pagingInfo.setItemCount(-1L);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        IdntCpte newIdntCpte = new IdntCpte();
        String newIdntCpteString = converter.getAsString(FacesContext.getCurrentInstance(), null, newIdntCpte);
        String idntCpteString = converter.getAsString(FacesContext.getCurrentInstance(), null, idntCpte);
        if (!newIdntCpteString.equals(idntCpteString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
