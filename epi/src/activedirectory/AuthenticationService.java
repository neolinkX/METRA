/**
 * AuthenticationService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package activedirectory;

public interface AuthenticationService extends javax.xml.rpc.Service {
    public java.lang.String getAuthenticationSoapPortAddress();

    public activedirectory.Authentication getAuthenticationSoapPort() throws javax.xml.rpc.ServiceException;

    public activedirectory.Authentication getAuthenticationSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
