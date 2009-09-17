/*
Copyright Stéphane Georges Popoff, (juillet 2009)

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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import theCube.entities.Objsecu;
import theCube.roleMining.ObjSecus;
import theCube.jsf.util.JsfUtil;
import theCube.jpa.exceptions.NonexistentEntityException;
import theCube.jpa.exceptions.IllegalOrphanException;
import theCube.jsf.util.PagingInfo;

/**
 * Contrôleur des objets de securité (rôles applicatifs)
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class ObjsecuController {

    public ObjsecuController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (ObjSecus) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objSecusBean");
        pagingInfo = new PagingInfo();
        converter = new ObjsecuConverter();
    }
    private Objsecu objsecu = null;
    private List<Objsecu> objsecuItems = null;
    private ObjSecus jpaController = null;
    private ObjsecuConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getObjsecuCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getObjsecuItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findObjsecuEntities(), false);
    }

    public SelectItem[] getObjsecuItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findObjsecuEntities(), true);
    }

    public Objsecu getObjsecu() {
        if (objsecu == null) {
            objsecu = (Objsecu) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentObjsecu", converter, null);
        }
        if (objsecu == null) {
            objsecu = new Objsecu();
        }
        return objsecu;
    }

    public String listSetup() {
        reset(true);
        return "objsecu_list";
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Objsecu> getObjsecuItems() {
        if (objsecuItems == null) {
            getPagingInfo();
            objsecuItems = jpaController.findObjsecuEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return objsecuItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "objsecu_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "objsecu_list";
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
        objsecu = null;
        objsecuItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
