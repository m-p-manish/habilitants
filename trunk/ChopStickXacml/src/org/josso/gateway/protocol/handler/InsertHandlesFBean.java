/*
Copyright Stéphane Georges Popoff, (novembre 2009)

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

package org.josso.gateway.protocol.handler;

import java.beans.PropertyEditor;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

/**
 * permet de charger une liste dans un bean
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class InsertHandlesFBean implements FactoryBean, InitializingBean {
    private List<ProtocolHandler> children;
    private String vecteurList = null;

    public InsertHandlesFBean() {
        System.out.println("init le BeanFactory pour la liste handlers");
        BeanWrapper monW = null;
        try {
            monW = new BeanWrapperImpl(this);
            monW.registerCustomEditor(List.class, "handlers", new HandlerEditor(List.class, true));
        } catch (Exception e) {
            System.err.println("Marche pas l'inscription de l'éditeur via beanWrapper "+e.toString());
        }
        PropertyEditor pe = monW.findCustomEditor(List.class, "handlers");
        if(pe==null){
            System.out.println("Echec inscription de l'éditeur via beanWrapper pour handlers");
        }else{
            System.out.println("inscription de l'éditeur via beanWrapper pour handlers");
        }

    }
    public void setVecteurList(String vecteurList) {
        this.vecteurList = vecteurList;
    }

    public Object getObject() throws Exception {
        return children;
    }

    public Class getObjectType() {
        return List.class;
    }

    public boolean isSingleton() {
        return false;
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("on charge la liste handlers");
        if(vecteurList==null) throw new IllegalArgumentException("remplir propriété vecteurList");
        //pour l'instant on ajoute un seul enfant
        try {
            children = Arrays.asList((ProtocolHandler) new XacmlPDPHandler());
        } catch (Exception e) {
            System.err.println("Marche pas le cast sur XacmlPDPHandler");
        }
    }

public class HandlerEditor extends CustomCollectionEditor {

	public HandlerEditor(Class collectionType, boolean nullAsEmptyCollection) {
		super(collectionType, nullAsEmptyCollection);
	}

	@Override
	protected Object convertElement(Object element) {
		ProtocolHandler ph = (ProtocolHandler) new XacmlPDPHandler();
		return ph;
	}
}}
