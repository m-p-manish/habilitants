/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.HabilitantEjb;
import daoEjb.ObjsHbltEjb;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import techDecision.entites.Habilitant;
import test1Jsf.util.JsfUtil;
import daoJpa.exceptions.NonexistentEntityException;
import daoJpa.exceptions.IllegalOrphanException;
import test1Jsf.util.PagingInfo;

/**
 *
 * @author spopoff
 */
public class HabilitantController {

    public HabilitantController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (HabilitantEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "habilitantJpa");
        pagingInfo = new PagingInfo();
        converter = new HabilitantConverter();
        ejbObjsHblt = (ObjsHbltEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objsHbltJpa");
    }
    private Habilitant habilitant = null;
    private List<Habilitant> habilitantItems = null;
    private HabilitantEjb jpaController = null;
    private HabilitantConverter converter = null;
    private PagingInfo pagingInfo = null;
    private ObjsHbltEjb ejbObjsHblt = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getHabilitantCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getHabilitantItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findHabilitantEntities(), false);
    }

    public SelectItem[] getHabilitantItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findHabilitantEntities(), true);
    }

    public Habilitant getHabilitant() {
        if (habilitant == null) {
            habilitant = (Habilitant) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentHabilitant", converter, null);
        }
        if (habilitant!=null){
            habilitant.setObjsHbltCollection(ejbObjsHblt.relatedObjs(habilitant.getPkhblt()));
        }
        if (habilitant == null) {
            habilitant = new Habilitant();
        }
        return habilitant;
    }

    public String listSetup() {
        reset(true);
        return "habilitant_list";
    }

    public String createSetup() {
        reset(false);
        habilitant = new Habilitant();
        return "habilitant_create";
    }

    public String create() {
        try {
            jpaController.create(habilitant);
            JsfUtil.addSuccessMessage("Habilitant was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("habilitant_detail");
    }

    public String editSetup() {
        return scalarSetup("habilitant_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        habilitant = (Habilitant) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentHabilitant", converter, null);
        if (habilitant == null) {
            String requestHabilitantString = JsfUtil.getRequestParameter("jsfcrud.currentHabilitant");
            JsfUtil.addErrorMessage("The habilitant with id " + requestHabilitantString + " no longer exists.");
            return relatedOrListOutcome();
        }
        habilitant.setObjsHbltCollection(ejbObjsHblt.relatedObjs(habilitant.getPkhblt()));
        return destination;
    }

    public String edit() {
        String habilitantString = converter.getAsString(FacesContext.getCurrentInstance(), null, habilitant);
        String currentHabilitantString = JsfUtil.getRequestParameter("jsfcrud.currentHabilitant");
        if (habilitantString == null || habilitantString.length() == 0 || !habilitantString.equals(currentHabilitantString)) {
            String outcome = editSetup();
            if ("habilitant_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit habilitant. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(habilitant);
            JsfUtil.addSuccessMessage("Habilitant was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentHabilitant");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Habilitant was successfully deleted.");
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

    public List<Habilitant> getHabilitantItems() {
        if (habilitantItems == null) {
            getPagingInfo();
            habilitantItems = jpaController.findHabilitantEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return habilitantItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "habilitant_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "habilitant_list";
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
        habilitant = null;
        habilitantItems = null;
        pagingInfo.setItemCount(-1L);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Habilitant newHabilitant = new Habilitant();
        String newHabilitantString = converter.getAsString(FacesContext.getCurrentInstance(), null, newHabilitant);
        String habilitantString = converter.getAsString(FacesContext.getCurrentInstance(), null, habilitant);
        if (!newHabilitantString.equals(habilitantString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
