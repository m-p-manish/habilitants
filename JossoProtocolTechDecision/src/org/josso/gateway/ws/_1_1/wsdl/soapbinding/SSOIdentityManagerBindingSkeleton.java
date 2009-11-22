/*
 * JOSSO: Java Open Single Sign-On
 *
 * Copyright 2004-2009, Atricore, Inc.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 */

/**
 * SSOIdentityManagerBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jul 21, 2005 (10:26:06 GMT-03:00) WSDL2Java emitter.
 */

package org.josso.gateway.ws._1_1.wsdl.soapbinding;

public class SSOIdentityManagerBindingSkeleton implements org.josso.gateway.ws._1_1.wsdl.SSOIdentityManager, org.apache.axis.wsdl.Skeleton {
    private org.josso.gateway.ws._1_1.wsdl.SSOIdentityManager impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "FindUserInSessionRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "FindUserInSessionRequestType"), org.josso.gateway.ws._1_1.protocol.FindUserInSessionRequestType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("findUserInSession", _params, new javax.xml.namespace.QName("", "FindUserInSessionResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "FindUserInSessionResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findUserInSession", "findUserInSession"));
        _oper.setSoapAction("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findUserInSession");
        _myOperationsList.add(_oper);
        if (_myOperations.get("findUserInSession") == null) {
            _myOperations.put("findUserInSession", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("findUserInSession")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("InvalidSessionErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findUserInSession", "InvalidSessionError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.InvalidSessionErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "InvalidSessionErrorType"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("NoSuchUserErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findUserInSession", "NoSuchUserError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.NoSuchUserErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "NoSuchUserErrorType"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("SSOIdentityManagerErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findUserInSession", "SSOIdentityManagerError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.SSOIdentityManagerErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "SSOIdentityManagerErrorType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "FindUserInSecurityDomainRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "FindUserInSecurityDomainRequestType"), org.josso.gateway.ws._1_1.protocol.FindUserInSecurityDomainRequestType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("findUserInSecurityDomain", _params, new javax.xml.namespace.QName("", "FindUserInSecurityDomainResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "FindUserInSecurityDomainResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findUserInSecurityDomain", "findUserInSecurityDomain"));
        _oper.setSoapAction("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findUserInSecurityDomain");
        _myOperationsList.add(_oper);
        if (_myOperations.get("findUserInSecurityDomain") == null) {
            _myOperations.put("findUserInSecurityDomain", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("findUserInSecurityDomain")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("NoSuchUserErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findUserInSecurityDomain", "NoSuchUserError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.NoSuchUserErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "NoSuchUserErrorType"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("SSOIdentityManagerErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findUserInSecurityDomain", "SSOIdentityManagerError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.SSOIdentityManagerErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "SSOIdentityManagerErrorType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "FindRolesBySSOSessionIdRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "FindRolesBySSOSessionIdRequestType"), org.josso.gateway.ws._1_1.protocol.FindRolesBySSOSessionIdRequestType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("findRolesBySSOSessionId", _params, new javax.xml.namespace.QName("", "FindRolesBySSOSessionIdResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "FindRolesBySSOSessionIdResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findRolesBySSOSessionId", "findRolesBySSOSessionId"));
        _oper.setSoapAction("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findRolesBySSOSessionId");
        _myOperationsList.add(_oper);
        if (_myOperations.get("findRolesBySSOSessionId") == null) {
            _myOperations.put("findRolesBySSOSessionId", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("findRolesBySSOSessionId")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("InvalidSessionErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findRolesBySSOSessionId", "InvalidSessionError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.InvalidSessionErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "InvalidSessionErrorType"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("SSOIdentityManagerErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/findRolesBySSOSessionId", "SSOIdentityManagerError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.SSOIdentityManagerErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "SSOIdentityManagerErrorType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "UserExistsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "UserExistsRequestType"), org.josso.gateway.ws._1_1.protocol.UserExistsRequestType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("userExists", _params, new javax.xml.namespace.QName("", "UserExistsResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "UserExistsResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/userExists", "userExists"));
        _oper.setSoapAction("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/userExists");
        _myOperationsList.add(_oper);
        if (_myOperations.get("userExists") == null) {
            _myOperations.put("userExists", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("userExists")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("SSOIdentityManagerErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityManager/userExists", "SSOIdentityManagerError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.SSOIdentityManagerErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "SSOIdentityManagerErrorType"));
        _oper.addFault(_fault);
    }

    public SSOIdentityManagerBindingSkeleton() {
        this.impl = new org.josso.gateway.ws._1_1.wsdl.soapbinding.SSOIdentityManagerBindingImpl();
    }

    public SSOIdentityManagerBindingSkeleton(org.josso.gateway.ws._1_1.wsdl.SSOIdentityManager impl) {
        this.impl = impl;
    }
    public org.josso.gateway.ws._1_1.protocol.FindUserInSessionResponseType findUserInSession(org.josso.gateway.ws._1_1.protocol.FindUserInSessionRequestType findUserInSessionRequest) throws java.rmi.RemoteException, org.josso.gateway.ws._1_1.protocol.InvalidSessionErrorType, org.josso.gateway.ws._1_1.protocol.NoSuchUserErrorType, org.josso.gateway.ws._1_1.protocol.SSOIdentityManagerErrorType
    {
        org.josso.gateway.ws._1_1.protocol.FindUserInSessionResponseType ret = impl.findUserInSession(findUserInSessionRequest);
        return ret;
    }

    public org.josso.gateway.ws._1_1.protocol.FindUserInSecurityDomainResponseType findUserInSecurityDomain(org.josso.gateway.ws._1_1.protocol.FindUserInSecurityDomainRequestType findUserInSecurityDomainRequest) throws java.rmi.RemoteException, org.josso.gateway.ws._1_1.protocol.NoSuchUserErrorType, org.josso.gateway.ws._1_1.protocol.SSOIdentityManagerErrorType
    {
        org.josso.gateway.ws._1_1.protocol.FindUserInSecurityDomainResponseType ret = impl.findUserInSecurityDomain(findUserInSecurityDomainRequest);
        return ret;
    }

    public org.josso.gateway.ws._1_1.protocol.FindRolesBySSOSessionIdResponseType findRolesBySSOSessionId(org.josso.gateway.ws._1_1.protocol.FindRolesBySSOSessionIdRequestType findRolesBySSOSessionIdRequest) throws java.rmi.RemoteException, org.josso.gateway.ws._1_1.protocol.InvalidSessionErrorType, org.josso.gateway.ws._1_1.protocol.SSOIdentityManagerErrorType
    {
        org.josso.gateway.ws._1_1.protocol.FindRolesBySSOSessionIdResponseType ret = impl.findRolesBySSOSessionId(findRolesBySSOSessionIdRequest);
        return ret;
    }

    public org.josso.gateway.ws._1_1.protocol.UserExistsResponseType userExists(org.josso.gateway.ws._1_1.protocol.UserExistsRequestType userExistsRequest) throws java.rmi.RemoteException, org.josso.gateway.ws._1_1.protocol.SSOIdentityManagerErrorType
    {
        org.josso.gateway.ws._1_1.protocol.UserExistsResponseType ret = impl.userExists(userExistsRequest);
        return ret;
    }

}
