
/**
 * PdpServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
        package techDecision.xacmlPdp;

        /**
        *  PdpServiceMessageReceiverInOut message receiver
        */

        public class PdpServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutSyncMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        PdpServiceSkeletonInterface skel = (PdpServiceSkeletonInterface)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJava(op.getName().getLocalPart())) != null)){

        

            if("getDecision".equals(methodName)){
                
                os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument response3 = null;
	                        os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument wrappedParam =
                                                             (os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               response3 =
                                                   
                                                   
                                                         skel.getDecision(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), response3, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //

            private  org.apache.axiom.om.OMElement  toOM(os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault{
            org.apache.axiom.om.impl.builder.StAXOMBuilder builder = new org.apache.axiom.om.impl.builder.StAXOMBuilder
            (org.apache.axiom.om.OMAbstractFactory.getOMFactory(),new org.apache.axis2.util.StreamWrapper(param.newXMLStreamReader())) ;
            org.apache.axiom.om.OMElement documentElement = builder.getDocumentElement();

            

            ((org.apache.axiom.om.impl.OMNodeEx)documentElement).setParent(null);
            return documentElement;
            }
        

            private  org.apache.axiom.om.OMElement  toOM(os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault{
            org.apache.axiom.om.impl.builder.StAXOMBuilder builder = new org.apache.axiom.om.impl.builder.StAXOMBuilder
            (org.apache.axiom.om.OMAbstractFactory.getOMFactory(),new org.apache.axis2.util.StreamWrapper(param.newXMLStreamReader())) ;
            org.apache.axiom.om.OMElement documentElement = builder.getDocumentElement();

            

            ((org.apache.axiom.om.impl.OMNodeEx)documentElement).setParent(null);
            return documentElement;
            }
        
                            private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument param, boolean optimizeContent)
                            throws org.apache.axis2.AxisFault {
                            org.apache.axiom.soap.SOAPEnvelope envelope = factory.getDefaultEnvelope();
                            if (param != null){
                            envelope.getBody().addChild(toOM(param, optimizeContent));
                            }
                            return envelope;
                            }
                        


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }

        public org.apache.xmlbeans.XmlObject fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{
        try{
        

            if (os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument.class.equals(type)){
            if (extraNamespaces!=null){
            return os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument.Factory.parse(
            param.getXMLStreamReaderWithoutCaching(),
            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
            }else{
            return os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument.Factory.parse(
            param.getXMLStreamReaderWithoutCaching());
            }
            }

        

            if (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument.class.equals(type)){
            if (extraNamespaces!=null){
            return os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument.Factory.parse(
            param.getXMLStreamReaderWithoutCaching(),
            new org.apache.xmlbeans.XmlOptions().setLoadAdditionalNamespaces(extraNamespaces));
            }else{
            return os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument.Factory.parse(
            param.getXMLStreamReaderWithoutCaching());
            }
            }

        
        }catch(java.lang.Exception e){
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        return null;
        }

        
        

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    