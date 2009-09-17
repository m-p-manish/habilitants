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

import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import theCube.entities.CpteAttrs;
import theCube.electre.ElectreBean;
import theCube.jsf.util.JsfUtil;
import theCube.jsf.util.PagingInfo;

/**
 * Contrôleur de la page des attributs de compte dédiée à l'attribut CLASST
 * Je sais on pourrait faire mieux
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class CpteAttrsController {

    public CpteAttrsController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (ElectreBean) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "ElectreBean");
        pagingInfo = new PagingInfo();
        converter = new CpteAttrsConverter();
    }
    private CpteAttrs cpteAttrs = null;
    private List<CpteAttrs> cpteAttrsItems = null;
    private ElectreBean jpaController = null;
    private CpteAttrsConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getCpteAttrsCount("CLASST"));
        }
        return pagingInfo;
    }

    public SelectItem[] getCpteAttrsItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findCpteAttrsEntities("CLASST"), false);
    }

    public SelectItem[] getCpteAttrsItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findCpteAttrsEntities("CLASST"), true);
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
        return "cpteattrs_list";
    }

    public List<CpteAttrs> getCpteAttrsItems() {
        if (cpteAttrsItems == null) {
            getPagingInfo();
            cpteAttrsItems = jpaController.findCpteAttrsEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem(), "CLASST");
        }
        return cpteAttrsItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "cpteattrs_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "cpteattrs_list";
    }

    private void reset(boolean resetFirstItem) {
        cpteAttrs = null;
        cpteAttrsItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
