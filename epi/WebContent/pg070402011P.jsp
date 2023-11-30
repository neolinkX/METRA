<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<html>
<%
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402011P.jsp");
  vEntorno.setOnLoad("fOnLoad();");

  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cTitulo=request.getParameter("hdTitulo")!=null?vParametros.getPropEspecifica("NomModulo")+" "+request.getParameter("hdTitulo"):"";
  String cAccion = ""+request.getParameter("hdAccion");
  if(cAccion.compareTo("") != 0){
    try{
       HashMap hmPermisos=(HashMap)request.getSession(true).getAttribute("PermisosUsuario");
       if(hmPermisos.containsKey(cAccion)){
          StringTokenizer stTitulo=new StringTokenizer(""+hmPermisos.get(cAccion),"|");
          cTitulo=stTitulo.nextToken();
       }
    }catch(Exception e){
       System.out.print("pg070402011P.jsp:");
       e.printStackTrace();
    }
  }
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700007.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdBusca" value="">
  <input type="hidden" name="hdLlave" value="">
  <input type="hidden" name="hdAccion" value="<%=request.getParameter("hdAccion")!=null?request.getParameter("hdAccion"):""%>">
  <table border="0" width="100%" height="100%" background="<%=cRutaImg%>fondoSTitulo<%=cTipoImg%>">
    <tr><td class="ETituloTPag" align="center" width="100%" valign="middle"><%=cTitulo.toUpperCase()%></td></tr>
  </table>
</form>
</body>
<SCRIPT LANGUAGE="JavaScript">
<%  String cBusca=request.getParameter("hdBusca");
    int i = 0;
    if("ESTADOS".equals(cBusca)){
      String cCvePais=request.getParameter("hdLlave")!=null?request.getParameter("hdLlave"):"-1";
      Vector vcRecords=new pg070402011CFG().getEntidadFeds(cCvePais.length()!=0?cCvePais:"-1");
      for(Enumeration e=vcRecords.elements();e.hasMoreElements();){
        TVGRLEntidadFed vGRLEntidadFed=(TVGRLEntidadFed)e.nextElement();
%>  fa(<%=vGRLEntidadFed.getICveEntidadFed()%>,'<%=vGRLEntidadFed.getCNombre()%>');
<%    }
    }else if("MUNICIPIOS".equals(cBusca)){
      String cCvePais="-1",cCveEntidadFed="-1",cLlave=request.getParameter("hdLlave");
      try{
        String[] aLlaves=cLlave.split("\\|");
        cCvePais=aLlaves[0];
        cCveEntidadFed=aLlaves[1];
      }catch(ArrayIndexOutOfBoundsException e){}
      Vector vcRecords=new pg070402011CFG().getMunicipios(cCvePais,cCveEntidadFed);
      for(Enumeration e=vcRecords.elements();e.hasMoreElements();){
        TVGRLMunicipio vGRLMunicipio=(TVGRLMunicipio)e.nextElement();
%>  fa(<%=vGRLMunicipio.getICveMunicipio()%>,'<%=vGRLMunicipio.getCNombre()%>');
<%    }
    }else if("CAUSAS".equals(cBusca)){
      String cCveTpoCausa=request.getParameter("hdLlave")!=null?request.getParameter("hdLlave"):"-1";
      Vector vcRecords=new pg070402011CFG().getCausas(cCveTpoCausa.length()!=0?cCveTpoCausa:"-1");
      for(Enumeration e=vcRecords.elements();e.hasMoreElements();){
        TVINVCausa vINVCausa=(TVINVCausa)e.nextElement();
%>  fa(<%=vINVCausa.getICveCausa()%>,'<%=vINVCausa.getCDscCausa()%>');
<%    }
    }
%>  fSub(aGen,'<%=cBusca%>');
</SCRIPT>
</html>