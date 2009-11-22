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
 * SSOSessionManagerBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2.1 Jul 21, 2005 (10:26:06 GMT-03:00) WSDL2Java emitter.
 */

package org.josso.gateway.ws._1_1.wsdl.soapbinding;

public class SSOSessionManagerBindingSkeleton implements org.josso.gateway.ws._1_1.wsdl.SSOSessionManager, org.apache.axis.wsdl.Skeleton {
    private org.josso.gateway.ws._1_1.wsdl.SSOSessionManager impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AccessSessionRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "AccessSessionRequestType"), org.josso.gateway.ws._1_1.protocol.AccessSessionRequestType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("accessSession", _params, new javax.xml.namespace.QName("", "AccessSessionResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "AccessSessionResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/SessionManager/accessSession", "accessSession"));
        _oper.setSoapAction("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/SessionManager/accessSession");
        _myOperationsList.add(_oper);
        if (_myOperations.get("accessSession") == null) {
            _myOperations.put("accessSession", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("accessSession")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("NoSuchSessionErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/SessionManager/accessSession", "NoSuchSessionError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.NoSuchSessionErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "NoSuchSessionErrorType"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("SSOSessionErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/SessionManager/accessSession", "SSOSessionError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.SSOSessionErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "SSOSessionErrorType"));
        _oper.addFault(_fault);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SessionRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "SessionRequestType"), org.josso.gateway.ws._1_1.protocol.SessionRequestType.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getSession", _params, new javax.xml.namespace.QName("", "SessionResponse"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "SessionResponseType"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/SessionManager/getSession", "getSession"));
        _oper.setSoapAction("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/SessionManager/getSession");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getSession") == null) {
            _myOperations.put("getSession", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getSession")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("NoSuchSessionErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/SessionManager/getSession", "NoSuchSessionError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.NoSuchSessionErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "NoSuchSessionErrorType"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("SSOSessionErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/wsdl/soapbinding/SessionManager/getSession", "SSOSessionError"));
        _fault.setClassName("org.josso.gateway.ws._1_1.protocol.SSOSessionErrorType");
        _fault.setXmlType(new javax.xml.namespace.QName("http://josso.org/gateway/ws/1.1/protocol", "SSOSessionErrorType"));
        _oper.addFault(_fault);
    }

    public SSOSessionManagerBindingSkeleton() {
        this.impl = new org.josso.gateway.ws._1_1.wsdl.soapbinding.SSOSessionManagerBindingImpl();
    }

    public SSOSessionManagerBindingSkeleton(org.josso.gateway.ws._1_1.wsdl.SSOSessionManager impl) {
        this.impl = impl;
    }
    public org.josso.gateway.ws._1_1.protocol.AccessSessionResponseType accessSession(org.josso.gateway.ws._1_1.protocol.AccessSessionRequestType accessSessionRequest) throws java.rmi.RemoteException, org.josso.gateway.ws._1_1.protocol.NoSuchSessionErrorType, org.josso.gateway.ws._1_1.protocol.SSOSessionErrorType
    {
        org.josso.gateway.ws._1_1.protocol.AccessSessionResponseType ret = impl.accessSession(accessSessionRequest);
        return ret;
    }

    public org.josso.gateway.ws._1_1.protocol.SessionResponseType getSession(org.josso.gateway.ws._1_1.protocol.SessionRequestType sessionRequest) throws java.rmi.RemoteException, org.josso.gateway.ws._1_1.protocol.NoSuchSessionErrorType, org.josso.gateway.ws._1_1.protocol.SSOSessionErrorType
    {
        org.josso.gateway.ws._1_1.protocol.SessionResponseType ret = impl.getSession(sessionRequest);
        return ret;
    }

}
