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
 * SSOIdentityProviderBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jul 21, 2005 (10:26:06 GMT-03:00) WSDL2Java emitter.
 */

package org.josso.gateway.ws._1_1.wsdl.soapbinding;

public class SSOIdentityProviderBindingSkeleton implements org.josso.gateway.ws._1_1.wsdl.SSOIdentityProvider, org.apache.axis.wsdl.Skeleton {
    private org.josso.gateway.ws._1_1.wsdl.SSOIdentityProvider impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResolveAuthenticationAssertionRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "ResolveAuthenticationAssertionRequestType"), org.josso.gateway.ws._1_1.protocol.ResolveAuthenticationAssertionRequestType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("resolveAuthenticationAssertion", _params, new javax.xml.namespace.QName("", "ResolveAuthenticationAssertionResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "ResolveAuthenticationAssertionResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityProvider/resolveAuthenticationAssertion", "resolveAuthenticationAssertion"));
        _oper.setSoapAction("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityProvider/resolveAuthenticationAssertion");
        _myOperationsList.add(_oper);
        if (_myOperations.get("resolveAuthenticationAssertion") == null) {
            _myOperations.put("resolveAuthenticationAssertion", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("resolveAuthenticationAssertion")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("AssertionNotValidFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityProvider/resolveAuthenticationAssertion", "AssertionNotValidError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.AssertionNotValidErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "AssertionNotValidErrorType"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("SSOIdentityProviderFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityProvider/resolveAuthenticationAssertion", "SSOIdentityProviderError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.SSOIdentityProviderErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "SSOIdentityProviderErrorType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AssertIdentityWithSimpleAuthenticationRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "AssertIdentityWithSimpleAuthenticationRequestType"), org.josso.gateway.ws._1_1.protocol.AssertIdentityWithSimpleAuthenticationRequestType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("assertIdentityWithSimpleAuthentication", _params, new javax.xml.namespace.QName("", "AssertIdentityWithSimpleAuthenticationResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "AssertIdentityWithSimpleAuthenticationResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityProvider/assertIdentityWithSimpleAuthentication", "assertIdentityWithSimpleAuthentication"));
        _oper.setSoapAction("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityProvider/assertIdentityWithSimpleAuthentication");
        _myOperationsList.add(_oper);
        if (_myOperations.get("assertIdentityWithSimpleAuthentication") == null) {
            _myOperations.put("assertIdentityWithSimpleAuthentication", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("assertIdentityWithSimpleAuthentication")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("SSOIdentityProviderFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityProvider/assertIdentityWithSimpleAuthentication", "SSOIdentityProviderError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.SSOIdentityProviderErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "SSOIdentityProviderErrorType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "GlobalSignoffRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "GlobalSignoffRequestType"), org.josso.gateway.ws._1_1.protocol.GlobalSignoffRequestType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("globalSignoff", _params, new javax.xml.namespace.QName("", "GlobalSignoffResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "GlobalSignoffResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityProvider/globalSignoff", "globalSignoff"));
        _oper.setSoapAction("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityProvider/globalSignoff");
        _myOperationsList.add(_oper);
        if (_myOperations.get("globalSignoff") == null) {
            _myOperations.put("globalSignoff", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("globalSignoff")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("SSOIdentityProviderFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/IdentityProvider/globalSignoff", "SSOIdentityProviderError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.SSOIdentityProviderErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "SSOIdentityProviderErrorType"));
        _oper.addFault(_fault);
    }

    public SSOIdentityProviderBindingSkeleton() {
        this.impl = new org.josso.gateway.ws._1_1.wsdl.soapbinding.SSOIdentityProviderBindingImpl();
    }

    public SSOIdentityProviderBindingSkeleton(org.josso.gateway.ws._1_1.wsdl.SSOIdentityProvider impl) {
        this.impl = impl;
    }
    public org.josso.gateway.ws._1_1.protocol.ResolveAuthenticationAssertionResponseType resolveAuthenticationAssertion(org.josso.gateway.ws._1_1.protocol.ResolveAuthenticationAssertionRequestType resolveAuthenticationAssertionRequest) throws java.rmi.RemoteException, org.josso.gateway.ws._1_1.protocol.AssertionNotValidErrorType, org.josso.gateway.ws._1_1.protocol.SSOIdentityProviderErrorType
    {
        org.josso.gateway.ws._1_1.protocol.ResolveAuthenticationAssertionResponseType ret = impl.resolveAuthenticationAssertion(resolveAuthenticationAssertionRequest);
        return ret;
    }

    public org.josso.gateway.ws._1_1.protocol.AssertIdentityWithSimpleAuthenticationResponseType assertIdentityWithSimpleAuthentication(org.josso.gateway.ws._1_1.protocol.AssertIdentityWithSimpleAuthenticationRequestType assertIdentityWithSimpleAuthenticationRequest) throws java.rmi.RemoteException, org.josso.gateway.ws._1_1.protocol.SSOIdentityProviderErrorType
    {
        org.josso.gateway.ws._1_1.protocol.AssertIdentityWithSimpleAuthenticationResponseType ret = impl.assertIdentityWithSimpleAuthentication(assertIdentityWithSimpleAuthenticationRequest);
        return ret;
    }

    public org.josso.gateway.ws._1_1.protocol.GlobalSignoffResponseType globalSignoff(org.josso.gateway.ws._1_1.protocol.GlobalSignoffRequestType globalSignoffRequest) throws java.rmi.RemoteException, org.josso.gateway.ws._1_1.protocol.SSOIdentityProviderErrorType
    {
        org.josso.gateway.ws._1_1.protocol.GlobalSignoffResponseType ret = impl.globalSignoff(globalSignoffRequest);
        return ret;
    }

}
