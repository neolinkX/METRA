<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402013P.jsp");
  vEntorno.setOnLoad("fOnLoad();");

  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cRutaAyuda = vParametros.getPropEspecifica("html");
  String cTitulo = "" + request.getParameter("hdTitulo");
  if(cTitulo.compareTo("null") == 0)
     cTitulo = "";
  else
     cTitulo = vParametros.getPropEspecifica("NomModulo") + " " + cTitulo;

  String cAccion = ""+request.getParameter("hdAccion");
  if(cAccion.compareTo("") != 0){
    try{
       HashMap hmPermisos = (HashMap) request.getSession(true).getAttribute("PermisosUsuario");
       if(hmPermisos.containsKey(cAccion)){
          StringTokenizer stTitulo = new StringTokenizer(""+hmPermisos.get(cAccion),"|");
          cTitulo = stTitulo.nextToken();
       }
    }catch(Exception e){
       System.out.print("pg070402013P.jsp:");
       e.printStackTrace();
    }
  }
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700007.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  var RutaFuncs = "<%=vParametros.getPropEspecifica("RutaFuncs")%>";
  var Style = '<%=vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS")%>';
  var cAccion = '<%= request.getParameter("hdAccion")!=null ? request.getParameter("hdAccion") : vEntorno.getActionForm().substring(0,vEntorno.getActionForm().length()-5) + ".jsp" %>';
  var bkFondo = '<%=cRutaImg%>fondoSTitulo<%=cTipoImg%>';
  var cTitulo = '<%=cTitulo.toUpperCase()%>';
  fPag();
<%
   String cBusca = ""+request.getParameter("hdBusca");
   int i = 0;
   Vector vcRecords = new Vector();
   if(cBusca.equals("pais")){
     vcRecords = new TDPais().FindByAll();
     TVPais vPais;
     for(i=0;i < vcRecords.size();i++){
       vPais = (TVPais) vcRecords.get(i);
       out.println("fa(" + vPais.getICvePais() + ",'" + vPais.getCNombre() + "');");
     }
   }
   if(cBusca.equals("edoOr") || cBusca.equals("edoDest")){
     vcRecords = new TDEntidadFed().FindByAll("where iCvePais = " + request.getParameter("hdLlave"));
     TVEntidadFed vEdo;
     for(i=0;i < vcRecords.size();i++){
       vEdo = (TVEntidadFed) vcRecords.get(i);
       out.println("fa(" + vEdo.getICveEntidadFed() + ",'" + vEdo.getCNombre() + "');");
     }
   }
   if(cBusca.equals("servpres")){
     vcRecords = new TDCTRServPrestado().FindByAll(""," order by cDscServPrestado");
     TVCTRServPrestado vSrvPres;
     for(i=0;i < vcRecords.size();i++){
       vSrvPres = (TVCTRServPrestado) vcRecords.get(i);
       out.println("fa(" + vSrvPres.getICveServPrestado() + ",'" + vSrvPres.getCDscServPrestado() + "');");
     }
   }
   out.println("fSub(aGen,'" + cBusca + "');");
%>
</SCRIPT>
