/*
Copyright Stéphane Georges Popoff, (mai - juillet 2009)

spopoff@rocketmail.com

Ce logiciel est un programme informatique servant à gérer des habilitations.

Ce logiciel est régi par la licence [CeCILL|CeCILL-B|CeCILL-C] soumise au droit français et
respectant les principes de diffusion des logiciels libres. Vous pouvez
utiliser, modifier et/ou redistribuer ce programme sous les conditions
de la licence [CeCILL|CeCILL-B|CeCILL-C] telle que diffusée par le CEA, le CNRS et l'INRIA
sur le site "http://www.cecill.info".

En contrepartie de l'accessibilité au code source et des droits de copie,
de modification et de redistribution accordés par cette licence, il n'est
offert aux utilisateurs qu'une garantie limitée.  Pour les mêmes raisons,
seule une responsabilité restreinte pèse sur l'auteur du programme,  le
titulaire des droits patrimoniaux et les concédants successifs.

A cet égard  l'attention de l'utilisateur est attirée sur les risques
associés au chargement,  à l'utilisation,  à la modification et/ou au
développement et à la reproduction du logiciel par l'utilisateur étant
donné sa spécificité de logiciel libre, qui peut le rendre complexe à
manipuler et qui le réserve donc à des développeurs et des professionnels
avertis possédant  des  connaissances  informatiques approfondies.  Les
utilisateurs sont donc invités à charger  et  tester  l'adéquation  du
logiciel à leurs besoins dans des conditions permettant d'assurer la
sécurité de leurs systèmes et ou de leurs données et, plus généralement,
à l'utiliser et l'exploiter dans les mêmes conditions de sécurité.

Le fait que vous puissiez accéder à cet en-tête signifie que vous avez
pris connaissance de la licence [CeCILL|CeCILL-B|CeCILL-C], et que vous en avez accepté les
termes.
 */
package theCube.jsf;

import theCube.jpa.Identite3;
import theCube.jpa.IdntAttrs3;
//import theCube.jpa.IdntCpte3;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import theCube.entities.Identite;
import theCube.jsf.util.JsfUtil;
import theCube.jsf.util.PagingInfo;
import theCube.dao.DataLoaderH2;

/**
 * Contrôleur de la page des identités
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class IdentiteController {

    private Identite identite = null;
    private List<Identite> identiteItems = null;
    private Identite3 jpaController = null;
    private IdntAttrs3 jpaIdntAttrs = null;
    //private IdntCpte3 jpaIdntCpte = null;
    private IdentiteConverter converter = null;
    private PagingInfo pagingInfo = null;
    private DataLoaderH2 dm = null;

    public IdentiteController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (Identite3) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "identite3Jpa");
        jpaIdntAttrs = (IdntAttrs3) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "idntAttrs3Jpa");
        //jpaIdntCpte = (IdntCpte3) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "idntCpte3Jpa");
        dm = (DataLoaderH2) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "database3");
        pagingInfo = new PagingInfo();
        converter = new IdentiteConverter();

    }
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

    public String detailSetup() {
        return scalarSetup("identite_detail");
    }

    private String scalarSetup(String destination) {
        reset(false);
        identite = (Identite) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentIdentite", converter, null);
        if (identite == null) {
            String requestIdentiteString = JsfUtil.getRequestParameter("jsfcrud.currentIdentite");
            JsfUtil.addErrorMessage("The identite with id " + requestIdentiteString + " no longer exists.");
            return relatedOrListOutcome();
        }
        identite.setIdntAttrsCollection(jpaIdntAttrs.relatedAttrs(identite.getPkidnt()));
        //identite.setIdntCpteCollection(jpaIdntCpte.relatedIdnt(identite.getPkidnt()));
        return destination;
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
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
