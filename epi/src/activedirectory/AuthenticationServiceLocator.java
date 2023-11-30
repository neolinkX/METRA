/**
 * AuthenticationServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package activedirectory;

public class AuthenticationServiceLocator extends org.apache.axis.client.Service implements activedirectory.AuthenticationService {

    public AuthenticationServiceLocator() {
    }


    public AuthenticationServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AuthenticationServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AuthenticationSoapPort
    private java.lang.String AuthenticationSoapPort_address = "http://desarrollo.sct.gob.mx:80/wsADAuth/Authentication";
    //private java.lang.String AuthenticationSoapPort_address = "http://ws.sct.gob.mx/wsADAuth/Authentication?WSDL";

    public java.lang.String getAuthenticationSoapPortAddress() {
        return AuthenticationSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AuthenticationSoapPortWSDDServiceName = "AuthenticationSoapPort";

    public java.lang.String getAuthenticationSoapPortWSDDServiceName() {
        return AuthenticationSoapPortWSDDServiceName;
    }

    public void setAuthenticationSoapPortWSDDServiceName(java.lang.String name) {
        AuthenticationSoapPortWSDDServiceName = name;
    }

    public activedirectory.Authentication getAuthenticationSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AuthenticationSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAuthenticationSoapPort(endpoint);
    }

    public activedirectory.Authentication getAuthenticationSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            activedirectory.AuthenticationServiceSoapBindingStub _stub = new activedirectory.AuthenticationServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getAuthenticationSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAuthenticationSoapPortEndpointAddress(java.lang.String address) {
        AuthenticationSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (activedirectory.Authentication.class.isAssignableFrom(serviceEndpointInterface)) {
                activedirectory.AuthenticationServiceSoapBindingStub _stub = new activedirectory.AuthenticationServiceSoapBindingStub(new java.net.URL(AuthenticationSoapPort_address), this);
                _stub.setPortName(getAuthenticationSoapPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AuthenticationSoapPort".equals(inputPortName)) {
            return getAuthenticationSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://activedirectory", "AuthenticationService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://activedirectory", "AuthenticationSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AuthenticationSoapPort".equals(portName)) {
            setAuthenticationSoapPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
