/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.IdentiteEjb;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import techDecision.entites.Identite;
import test1Jsf.util.JsfUtil;
import daoJpa.exceptions.NonexistentEntityException;
import daoJpa.exceptions.IllegalOrphanException;
import test1Jsf.util.PagingInfo;

/**
 *
 * @author spopoff
 */
public class IdentiteController {

    public IdentiteController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (IdentiteEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "identiteJpa");
        pagingInfo = new PagingInfo();
        converter = new IdentiteConverter();
    }
    private Identite identite = null;
    private List<Identite> identiteItems = null;
    private IdentiteEjb jpaController = null;
    private IdentiteConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getIdentiteCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getIdentiteItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findIdentiteEntities(), false);
    }

    public SelectItem[] getIdentiteItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findIdentiteEntities(), true);
    }

    public Identite getIdentite() {
        if (identite == null) {
            identite = (Identite) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentIdentite", converter, null);
        }
        if (identite == null) {
            identite = new Identite();
        }
        return identite;
    }

    public String listSetup() {
        reset(true);
        return "identite_list";
    }

    public String createSetup() {
        reset(false);
        identite = new Identite();
        return "identite_create";
    }

    public String create() {
        try {
            jpaController.create(identite);
            JsfUtil.addSuccessMessage("Identite was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("identite_detail");
    }

    public String editSetup() {
        return scalarSetup("identite_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        identite = (Identite) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentIdentite", converter, null);
        if (identite == null) {
            String requestIdentiteString = JsfUtil.getRequestParameter("jsfcrud.currentIdentite");
            JsfUtil.addErrorMessage("The identite with id " + requestIdentiteString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String identiteString = converter.getAsString(FacesContext.getCurrentInstance(), null, identite);
        String currentIdentiteString = JsfUtil.getRequestParameter("jsfcrud.currentIdentite");
        if (identiteString == null || identiteString.length() == 0 || !identiteString.equals(currentIdentiteString)) {
            String outcome = editSetup();
            if ("identite_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit identite. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(identite);
            JsfUtil.addSuccessMessage("Identite was successfully updated.");
        } catch (IllegalOrphanException oe) {
            JsfUtil.addErrorMessages(oe.getMessages());
            return null;
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentIdentite");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Identite was successfully deleted.");
        } catch (IllegalOrphanException oe) {
            JsfUtil.addErrorMessages(oe.getMessages());
            return null;
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

    public List<Identite> getIdentiteItems() {
        if (identiteItems == null) {
            getPagingInfo();
            identiteItems = jpaController.findIdentiteEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return identiteItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "identite_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "identite_list";
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
        identite = null;
        identiteItems = null;
        pagingInfo.setItemCount(-1L);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Identite newIdentite = new Identite();
        String newIdentiteString = converter.getAsString(FacesContext.getCurrentInstance(), null, newIdentite);
        String identiteString = converter.getAsString(FacesContext.getCurrentInstance(), null, identite);
        if (!newIdentiteString.equals(identiteString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
