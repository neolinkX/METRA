<%/**
 * Title: pg070402013.jsp
 * Description:
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Jaime Enrique Suárez Romero
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<html>
<%
  pg070402013CFG  clsConfig = new pg070402013CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070402013.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402013.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave1="",cClave2="",cClave3="",cClave4="",cPosicion="";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Cve.Vehículo|Matrícula|";    // modificar
  String cCveOrdenar  = "iCveVehiculo|cMatricula|";  // modificar
  String cDscFiltrar  = "Cve.Vehículo|Matrícula|";    // modificar
  String cCveFiltrar  = "iCveVehiculo|cMatricula|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
    fOnSetLocal();
  }
</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selEmp.js"%>"></SCRIPT>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <input type="hidden" name="hdcDscMdoTrasn" value="<%=(request.getParameter("hdcDscMdoTrasn") != null) ? request.getParameter("hdcDscMdoTrasn") : ""%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave1  = ""+bs.getFieldValue("iAnio", "");
       cClave2  = ""+bs.getFieldValue("iCveMdoTrans", "");
       cClave3  = ""+bs.getFieldValue("iIDDGPMPT", "");
       cClave4  = ""+bs.getFieldValue("iCveVehiculo", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave1%>">
  <input type="hidden" name="hdiAnio" value="<%=cClave1%>">
  <input type="hidden" name="hdiCveMdoTrans" value="<%=cClave2%>">
  <input type="hidden" name="hdiIDDGPMPT" value="<%=request.getParameter("hdiIDDGPMPT")!=null?request.getParameter("hdiIDDGPMPT"):"0"%>">
  <input type="hidden" name="hdiCveVehiculo" value="<%=cClave4%>">

<%-- inicio de los campos para saltar a la pagina pg070402010--%>
  <input type="hidden" name="iAnioSel" value="<%=request.getParameter("iAnioSel")!=null?request.getParameter("iAnioSel"):"0"%>">
  <input type="hidden" name="iCveMdoTransSel" value="<%=request.getParameter("iCveMdoTransSel")!=null?request.getParameter("iCveMdoTransSel"):"0"%>">
  <input type="hidden" name="iCveUniMedSel" value="<%=request.getParameter("iCveUniMedSel")!=null?request.getParameter("iCveUniMedSel"):"0"%>">
<%-- fin de los campos para saltar a la pagina pg070402010--%>

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr><td colspan="6" class="ETablaT">Vehículos Involucrados en el Accidente</td></tr>
                            <%
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Año:"));
                                    out.print(vEti.Texto("ECampo",""+request.getParameter("iAnioSel")));
                                    out.print(vEti.Texto("EEtiqueta","Modo de Transporte:"));
                                    out.print(vEti.Texto("ECampo",""+request.getParameter("iCveMdoTransSel")));
                                    out.print(vEti.Texto("EEtiqueta","IDDGPMPT:"));
                                    out.print(vEti.Texto("ECampo",""+request.getParameter("hdiIDDGPMPT")));
                                    out.println("</tr>");
                                    out.println("<tr><td colspan=\"6\" class=\"ETablaT\">Vehículos Involucrados</td></tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Matrícula","ECampo","text",20,20,5,"cMatricula","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Propietario:","ECampo","text",70,250,5,"cPropietario","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Empresa:"));
                                    out.print("<td colspan=\"5\">"+vEti.CampoSinCelda("text",70,70,"cEmpresa","",0,"","",false,false));
                                    out.print("&nbsp;"+vEti.clsAnclaTexto("EAncla","Buscar Empresa","JavaScript:fSelEmp();","Buscar Empresa","Empresa")+"</td>");
                                    out.print("<input type=\"hidden\" name=\"iCveEmpresa\" value=\"\">");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Servicio:"));
                                    out.print("<td colspan=\"5\"><select name=\"iCveServPrestado\" size=\"1\"></select>");
                                    out.println("</tr>");
                                    out.println("<tr><td colspan=\"6\" class=\"ETablaT\">Origen</td></tr>");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","País:"));
                                    out.print("<td colspan=\"2\"><select name=\"iCvePaisOr\"  onChange=\"fSubOculto('pg070402013P.jsp','edoOr',this.value);\" size=\"1\"></select>");
                                    out.print(vEti.Texto("EEtiqueta","Entidad Federativa:"));
                                    out.print("<td colspan=\"2\"><select name=\"iCveEdoOr\"  size=\"1\"></select>");
                                    out.println("</tr><tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Origen:","ECampo","text",70,100,5,"cOrigen","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr><td colspan=\"6\" class=\"ETablaT\">Destino</td></tr>");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","País:"));
                                    out.print("<td colspan=\"2\"><select name=\"iCvePaisDest\"  onChange=\"fSubOculto('pg070402013P.jsp','edoDest',this.value);\" size=\"1\"></select>");
                                    out.print(vEti.Texto("EEtiqueta","Entidad Federativa:"));
                                    out.print("<td colspan=\"2\"><select name=\"iCveEdoDest\"  size=\"1\"></select>");
                                    out.println("</tr><tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Destino:","ECampo","text",70,100,5,"cDestino","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr><td colspan=\"6\" class=\"ETablaT\">Personas Involucradas</td></tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Federales:","ECampo","text",3,3,"iPerFedInvolucra","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Estatales:","ECampo","text",3,3,"iPerEdoInvolucra","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Particulares:","ECampo","text",3,3,"iPerPartInvolucra","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                }
                                else {
                                 if (bs != null){
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Año:"));
                                    out.print(vEti.TextoCS("ECampo",bs.getFieldValue("iAnio","&nbsp;").toString(),2));
                                    out.print(vEti.Texto("EEtiqueta","Modo de Transporte:"));
                                    out.print(vEti.TextoCS("ECampo",bs.getFieldValue("cDscMdoTrans","&nbsp;").toString(),2));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","IDDGPMPT:"));
                                    out.print(vEti.TextoCS("ECampo",bs.getFieldValue("iIDDGPMPT","&nbsp;").toString(),2));
                                    out.print(vEti.Texto("EEtiqueta","Clave del Vehículo:"));
                                    out.print(vEti.TextoCS("ECampo",bs.getFieldValue("iCveVehiculo","&nbsp;").toString(),2));
                                    out.println("</tr>");
                                    out.println("<tr><td colspan=\"6\" class=\"ETablaT\">Vehículos Involucrados</td></tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Matrícula","ECampo","text",20,20,5,"cMatricula",bs.getFieldValue("cMatricula","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Propietario:","ECampo","text",70,250,5,"cPropietario",bs.getFieldValue("cPropietario","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Empresa:"));
                                    if(lCaptura){
                                      out.print("<td colspan=\"5\">"+vEti.CampoSinCelda("text",70,70,"cEmpresa",bs.getFieldValue("cDscEmpresa","&nbsp;").toString(),0,"","",false,false));
                                      out.print("&nbsp;"+vEti.clsAnclaTexto("EAncla","Buscar Empresa","JavaScript:fSelEmp();","Buscar Empresa","Empresa")+"</td>");
                                      out.print("<input type=\"hidden\" name=\"iCveEmpresa\" value=\""+ bs.getFieldValue("iCveEmpresa","") +"\">");
                                    }
                                    else
                                      out.print(vEti.TextoCS("ECampo",bs.getFieldValue("cDscEmpresa","&nbsp;").toString(),5));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Servicio:"));
                                    if(lCaptura){
                                      out.print("<td colspan=\"5\"><select name=\"iCveServPrestado\" size=\"1\"></select>");
                                      out.print("<input type=\"hidden\" name=\"hdiCveServPrestado\" value=\"" + bs.getFieldValue("iCveServPrestado","") + "\">");
                                    }
                                    else
                                      out.print(vEti.TextoCS("ECampo",bs.getFieldValue("cDscServPrestado","&nbsp;").toString(),5));
                                    out.println("</tr>");
                                    out.println("<tr><td colspan=\"6\" class=\"ETablaT\">Origen</td></tr>");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","País:"));
                                    if(lCaptura){
                                      out.print("<td colspan=\"2\"><select name=\"iCvePaisOr\"  onChange=\"fSubOculto('pg070402013P.jsp','edoOr',this.value);\" size=\"1\"></select>");
                                      out.print("<input type=\"hidden\" name=\"hdiCvePaisOr\" value=\"" + bs.getFieldValue("iCvePaisOr","") + "\">");
                                    }else
                                      out.print(vEti.TextoCS("ECampo",bs.getFieldValue("cDscPaisOr","&nbsp;").toString(),2));
                                    out.print(vEti.Texto("EEtiqueta","Entidad Federativa:"));
                                    if(lCaptura){
                                      out.print("<td colspan=\"2\"><select name=\"iCveEdoOr\"  size=\"1\"></select>");
                                      out.print("<input type=\"hidden\" name=\"hdiCveEdoOr\" value=\"" + bs.getFieldValue("iCveEdoOr","") + "\">");
                                    }else
                                      out.print(vEti.TextoCS("ECampo",bs.getFieldValue("cDscEdoOr","&nbsp;").toString(),2));
                                    out.println("</tr><tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Origen:","ECampo","text",70,100,5,"cOrigen",bs.getFieldValue("cOrigen","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr><td colspan=\"6\" class=\"ETablaT\">Destino</td></tr>");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","País:"));
                                    if(lCaptura){
                                      out.print("<td colspan=\"2\"><select name=\"iCvePaisDest\"  onChange=\"fSubOculto('pg070402013P.jsp','edoDest',this.value);\" size=\"1\"></select>");
                                      out.print("<input type=\"hidden\" name=\"hdiCvePaisDest\" value=\"" + bs.getFieldValue("iCvePaisDest","") + "\">");
                                    }else
                                      out.print(vEti.TextoCS("ECampo",bs.getFieldValue("cDscPaisDest","&nbsp;").toString(),2));
                                    out.print(vEti.Texto("EEtiqueta","Entidad Federativa:"));
                                    if(lCaptura){
                                      out.print("<td colspan=\"2\"><select name=\"iCveEdoDest\"  size=\"1\"></select>");
                                      out.print("<input type=\"hidden\" name=\"hdiCveEdoDest\" value=\"" + bs.getFieldValue("iCveEdoDest","") + "\">");
                                    }else
                                      out.print(vEti.TextoCS("ECampo",bs.getFieldValue("cDscEdoDest","&nbsp;").toString(),2));
                                    out.println("</tr><tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Destino:","ECampo","text",70,100,5,"cDestino",bs.getFieldValue("cDestino","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr><td colspan=\"6\" class=\"ETablaT\">Personas Involucradas</td></tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Federales:","ECampo","text",3,3,"iPerFedInvolucra",bs.getFieldValue("iPerFedInvolucra","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Estatales:","ECampo","text",3,3,"iPerEdoInvolucra",bs.getFieldValue("iPerEdoInvolucra","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Particulares:","ECampo","text",3,3,"iPerPartInvolucra",bs.getFieldValue("iPerPartInvolucra","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Total:","ECampo","text",3,3,5,"iTotal",bs.getFieldValue("iTotal","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                  }
                                 else{
                                   out.print(vEti.Texto("ECampo", "No existen datos coincidentes con el filtro proporcionado."));
                                 }
                                }
                            %>
                          </table><% // Fin de Datos %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>