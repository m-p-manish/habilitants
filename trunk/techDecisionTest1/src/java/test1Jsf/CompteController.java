/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test1Jsf;

import daoEjb.CompteEjb;
import daoEjb.ObjsCpteEjb;
import daoEjb.CpteAttrsEjb;
import daoEjb.IdntCpteEjb;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import techDecision.entites.Compte;
import test1Jsf.util.JsfUtil;
import daoJpa.exceptions.NonexistentEntityException;
import daoJpa.exceptions.IllegalOrphanException;
import test1Jsf.util.PagingInfo;

/**
 *
 * @author spopoff
 */
public class CompteController {

    private Compte compte = null;
    private List<Compte> compteItems = null;
    private CompteEjb jpaController = null;
    private ObjsCpteEjb ejbObjsCpte = null;
    private CpteAttrsEjb ejbCpteAttrs = null;
    private IdntCpteEjb ejbIdntCpte = null;
    private CompteConverter converter = null;
    private PagingInfo pagingInfo = null;
    private CpteHbltController ctrlCpteHblt = null;

    public CompteController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (CompteEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "compteJpa");
        ejbObjsCpte = (ObjsCpteEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objsCpteJpa");
        ejbCpteAttrs = (CpteAttrsEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cpteAttrsJpa");
        ejbIdntCpte = (IdntCpteEjb) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "idntCpteJpa");
        pagingInfo = new PagingInfo();
        converter = new CompteConverter();
        //référence à un autre controleur ?
        ctrlCpteHblt = (CpteHbltController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cpteHblt");
    }
    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getCompteCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getCompteItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findCompteEntities(), false);
    }

    public SelectItem[] getCompteItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findCompteEntities(), true);
    }

    public Compte getCompte() {
        if (compte == null) {
            compte = (Compte) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCompte", converter, null);
        }
        if (compte == null) {
            compte = new Compte();
        }
        return compte;
    }

    public String listSetup() {
        reset(true);
        return "compte_list";
    }

    public String createSetup() {
        reset(false);
        compte = new Compte();
        return "compte_create";
    }

    public String create() {
        try {
            jpaController.create(compte);
            JsfUtil.addSuccessMessage("Compte was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return detailSetup2();
    }

    public String detailSetup2() {
        reset(false);
        compte = (Compte) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCompte", converter, null);
        if (compte == null) {
            String requestCompteString = JsfUtil.getRequestParameter("jsfcrud.currentCompte");
            JsfUtil.addErrorMessage("The compte with id " + requestCompteString + " no longer exists.");
            return relatedOrListOutcome();
        }
        ctrlCpteHblt.setIc(compte.getPkcpte());
        compte.setObjsCpteCollection(ejbObjsCpte.relatedObjs(compte.getPkcpte()));
        compte.setCpteAttrsCollection(ejbCpteAttrs.relatedAttrs(compte.getPkcpte()));
        compte.setIdntCpteCollection(ejbIdntCpte.relatedIdnt(compte.getPkcpte()));
        return "compte2_detail";
    }

    public String editSetup() {
        return scalarSetup("compte_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        compte = (Compte) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCompte", converter, null);
        if (compte == null) {
            String requestCompteString = JsfUtil.getRequestParameter("jsfcrud.currentCompte");
            JsfUtil.addErrorMessage("The compte with id " + requestCompteString + " no longer exists.");
            return relatedOrListOutcome();
        }
        compte.setObjsCpteCollection(ejbObjsCpte.relatedObjs(compte.getPkcpte()));
        compte.setCpteAttrsCollection(ejbCpteAttrs.relatedAttrs(compte.getPkcpte()));
        compte.setIdntCpteCollection(ejbIdntCpte.relatedIdnt(compte.getPkcpte()));
        
        return destination;
    }

    public String edit() {
        String compteString = converter.getAsString(FacesContext.getCurrentInstance(), null, compte);
        String currentCompteString = JsfUtil.getRequestParameter("jsfcrud.currentCompte");
        if (compteString == null || compteString.length() == 0 || !compteString.equals(currentCompteString)) {
            String outcome = editSetup();
            if ("compte_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit compte. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(compte);
            JsfUtil.addSuccessMessage("Compte was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCompte");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Compte was successfully deleted.");
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

    public List<Compte> getCompteItems() {
        if (compteItems == null) {
            getPagingInfo();
            compteItems = jpaController.findCompteEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return compteItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "compte_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "compte_list";
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
        compte = null;
        compteItems = null;
        pagingInfo.setItemCount(-1L);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Compte newCompte = new Compte();
        String newCompteString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCompte);
        String compteString = converter.getAsString(FacesContext.getCurrentInstance(), null, compte);
        if (!newCompteString.equals(compteString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
