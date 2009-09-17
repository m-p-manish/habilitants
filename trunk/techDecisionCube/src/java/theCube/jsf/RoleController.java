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
import theCube.entities.Role;
import theCube.jpa.Role3;
import theCube.jsf.util.JsfUtil;
import theCube.jpa.exceptions.NonexistentEntityException;
import theCube.jpa.exceptions.IllegalOrphanException;
import theCube.jsf.util.PagingInfo;

/**
 * Contrôleur de la page des rôles
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class RoleController {

    public RoleController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (Role3) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "role3Jpa");
        pagingInfo = new PagingInfo();
        converter = new RoleConverter();
    }
    private Role role = null;
    private List<Role> roleItems = null;
    private Role3 jpaController = null;
    private RoleConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getRoleCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getRoleItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findRoleEntities(), false);
    }

    public SelectItem[] getRoleItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findRoleEntities(), true);
    }

    public Role getRole() {
        if (role == null) {
            role = (Role) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentRole", converter, null);
        }
        if (role == null) {
            role = new Role();
        }
        return role;
    }

    public String listSetup() {
        reset(true);
        return "role_list";
    }

    public String detailSetup() {
        return scalarSetup("role_detail");
    }

    public String editSetup() {
        return scalarSetup("role_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        role = (Role) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentRole", converter, null);
        if (role == null) {
            String requestRoleString = JsfUtil.getRequestParameter("jsfcrud.currentRole");
            JsfUtil.addErrorMessage("The role with id " + requestRoleString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Role> getRoleItems() {
        if (roleItems == null) {
            getPagingInfo();
            roleItems = jpaController.findRoleEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return roleItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "role_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "role_list";
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
        role = null;
        roleItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
