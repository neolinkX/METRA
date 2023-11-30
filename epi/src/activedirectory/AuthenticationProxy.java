package activedirectory;

public class AuthenticationProxy implements activedirectory.Authentication {
  private String _endpoint = null;
  private activedirectory.Authentication authentication = null;
  
  public AuthenticationProxy() {
    _initAuthenticationProxy();
  }
  
  public AuthenticationProxy(String endpoint) {
    _endpoint = endpoint;
    _initAuthenticationProxy();
  }
  
  private void _initAuthenticationProxy() {
    try {
      authentication = (new activedirectory.AuthenticationServiceLocator()).getAuthenticationSoapPort();
      if (authentication != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)authentication)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)authentication)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (authentication != null)
      ((javax.xml.rpc.Stub)authentication)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public activedirectory.Authentication getAuthentication() {
    if (authentication == null)
      _initAuthenticationProxy();
    return authentication;
  }
  
  public int authUsr(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (authentication == null)
      _initAuthenticationProxy();
    return authentication.authUsr(username, password);
  }
  
  
}