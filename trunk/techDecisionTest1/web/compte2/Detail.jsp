<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Détail d'un compte qui marche</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Compte avec détail</h1>
            <h:outputText value="Permet d'obtenir le détail d'un compte (les données liées) en passant par les EJB"/><br/>
            <h:outputText value="Les lien SHOW et EDIT fonctionne sur les données liées mais pas DESTROY qui génére une erreur au moment du recalcul des collection"/><br/><br/>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Pkcpte:"/>
                    <h:outputText value="#{compte.compte.pkcpte}" title="Pkcpte" />
                    <h:outputText value="Pkcpte2:"/>
                    <h:outputText value="#{compte.compte.pkcpte2}" title="Pkcpte2" />
                    <h:outputText value="L'objet de sécurité (application):" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty compte.compte.objsCpteCollection}" value="(No Items)"/>
                        <h:dataTable value="#{compte.compte.objsCpteCollection}" var="item"
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty compte.compte.objsCpteCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="PkobjsCpte"/>
                                </f:facet>
                                <h:outputText value=" #{item.pkobjsCpte}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkcpte"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkcpte}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkobjs"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkobjs}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{objsCpte.detailSetup}">
                                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsCpte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="compte" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CompteController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{objsCpte.editSetup}">
                                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsCpte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="compte" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CompteController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{objsCpte.destroy}">
                                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsCpte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="compte" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CompteController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="Les attributs du compte:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty compte.compte.cpteAttrsCollection}" value="(No Items)"/>
                        <h:dataTable value="#{compte.compte.cpteAttrsCollection}" var="item"
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty compte.compte.cpteAttrsCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="PkattrCpte"/>
                                </f:facet>
                                <h:outputText value=" #{item.pkattrCpte}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkcpte"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkcpte}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Attr"/>
                                </f:facet>
                                <h:outputText value=" #{item.attr}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Val"/>
                                </f:facet>
                                <h:outputText value=" #{item.val}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{cpteAttrs.detailSetup}">
                                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCpteAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cpteAttrs.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="compte" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CompteController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{cpteAttrs.editSetup}">
                                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCpteAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cpteAttrs.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="compte" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CompteController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{cpteAttrs.destroy}">
                                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentCpteAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cpteAttrs.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="compte" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CompteController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="Les identités associées:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty compte.compte.idntCpteCollection}" value="(No Items)"/>
                        <h:dataTable value="#{compte.compte.idntCpteCollection}" var="item"
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty compte.compte.idntCpteCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="PkidntCpte"/>
                                </f:facet>
                                <h:outputText value=" #{item.pkidntCpte}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkcpte"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkcpte}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkidnt"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkidnt}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{idntCpte.detailSetup}">
                                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentIdntCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][idntCpte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="compte" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CompteController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{idntCpte.editSetup}">
                                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentIdntCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][idntCpte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="compte" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CompteController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{idntCpte.destroy}">
                                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentIdntCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][idntCpte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="compte" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CompteController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="Les habilitants" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty cpteHblt.hbltItems}" value="(No Items)"/>
                        <h:dataTable value="#{cpteHblt.hbltItems}" var="item"
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px"
                                     rendered="#{not empty cpteHblt.hbltItems}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="PkHblt"/>
                                </f:facet>
                                <h:outputText value=" #{item.pkhblt}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="value"/>
                                </f:facet>
                                <h:outputText value=" #{item.val}"/>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{compte.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{compte.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{compte.createSetup}" value="New Compte" />
                <br />
                <h:commandLink action="#{compte.listSetup}" value="Show All Compte Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
