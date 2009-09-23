/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.CpteHbltEjb;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import techDecision.entites.CpteHblt;
import test1Jsf.util.JsfUtil;
import daoJpa.exceptions.NonexistentEntityException;
import techDecision.entites.Habilitant;
import test1Jsf.util.PagingInfo;

/**
 *
 * @author spopoff
 */
public class CpteHbltController {

    public CpteHbltController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (CpteHbltEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cpteHbltJpa");
        pagingInfo = new PagingInfo();
        converter = new CpteHbltConverter();
    }
    private CpteHblt cpteHblt = null;
    private List<CpteHblt> cpteHbltItems = null;
    private CpteHbltEjb jpaController = null;
    private CpteHbltConverter converter = null;
    private PagingInfo pagingInfo = null;
    private List<Habilitant> hbltItems = null;
    private Integer ic = null;

    public void setIc(Integer ic) {
        this.ic = ic;
    }

    public List<Habilitant> getHbltItems() {
        hbltItems = jpaController.relatedHblt(ic);
        return hbltItems;
    }

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getCpteHbltCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getCpteHbltItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findCpteHbltEntities(), false);
    }

    public SelectItem[] getCpteHbltItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findCpteHbltEntities(), true);
    }

    public CpteHblt getCpteHblt() {
        if (cpteHblt == null) {
            cpteHblt = (CpteHblt) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCpteHblt", converter, null);
        }
        if (cpteHblt == null) {
            cpteHblt = new CpteHblt();
        }
        return cpteHblt;
    }

    public String listSetup() {
        reset(true);
        return "cpteHblt_list";
    }

    public String createSetup() {
        reset(false);
        cpteHblt = new CpteHblt();
        return "cpteHblt_create";
    }

    public String create() {
        try {
            jpaController.create(cpteHblt);
            JsfUtil.addSuccessMessage("CpteHblt was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("cpteHblt_detail");
    }

    public String editSetup() {
        return scalarSetup("cpteHblt_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        cpteHblt = (CpteHblt) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCpteHblt", converter, null);
        if (cpteHblt == null) {
            String requestCpteHbltString = JsfUtil.getRequestParameter("jsfcrud.currentCpteHblt");
            JsfUtil.addErrorMessage("The cpteHblt with id " + requestCpteHbltString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String cpteHbltString = converter.getAsString(FacesContext.getCurrentInstance(), null, cpteHblt);
        String currentCpteHbltString = JsfUtil.getRequestParameter("jsfcrud.currentCpteHblt");
        if (cpteHbltString == null || cpteHbltString.length() == 0 || !cpteHbltString.equals(currentCpteHbltString)) {
            String outcome = editSetup();
            if ("cpteHblt_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit cpteHblt. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(cpteHblt);
            JsfUtil.addSuccessMessage("CpteHblt was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCpteHblt");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("CpteHblt was successfully deleted.");
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

    public List<CpteHblt> getCpteHbltItems() {
        if (cpteHbltItems == null) {
            getPagingInfo();
            cpteHbltItems = jpaController.findCpteHbltEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return cpteHbltItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "cpteHblt_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "cpteHblt_list";
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
        cpteHblt = null;
        cpteHbltItems = null;
        pagingInfo.setItemCount(-1L);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        CpteHblt newCpteHblt = new CpteHblt();
        String newCpteHbltString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCpteHblt);
        String cpteHbltString = converter.getAsString(FacesContext.getCurrentInstance(), null, cpteHblt);
        if (!newCpteHbltString.equals(cpteHbltString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
