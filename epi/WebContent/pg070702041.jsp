<%/**
 * Title: Detalle de Mantenimientos
 * Description: Detalle de Mantenimientos
 * Copyright: Micros
 * Company: Micros
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070702041CFG
 */%>

<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*" %>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="java.util.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>

<html>
<%
  pg070702041CFG  clsConfig = new pg070702041CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070702041.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070702041.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070702041.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "|";    // modificar
  String cCveOrdenar  = "|";  // modificar
  String cDscFiltrar  = "|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Reporte";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "SaveOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  if (request.getParameter("hdLectura") != null && request.getParameter("hdLectura").equals("1"))
     cCanWrite = "No";

  if (bs == null)
     cNavStatus = "Disabled";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

</SCRIPT>
<head>
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">

  <input type="hidden" name="dtInicio" value="<% if (request.getParameter("dtInicio") != null)  out.print(request.getParameter("dtInicio"));%>">
  <input type="hidden" name="dtFin" value="<% if (request.getParameter("dtFin") != null)  out.print(request.getParameter("dtFin"));%>">
  <input type="hidden" name="iCveUniMed" value="<% if (request.getParameter("iCveUniMed") != null)  out.print(request.getParameter("iCveUniMed"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
   TVUsuario tvUsu = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=request.getParameter("hdCampoClave") %>">
  <input type="hidden" name="hdLectura" value="<%=request.getParameter("hdLectura") %>">
  <input type="hidden" name="iCveMantenimiento" value="<%=request.getParameter("iCveMantenimiento") %>">
  <input type="hidden" name="iCveVehiculo" value="<%=request.getParameter("iCveVehiculo") %>">
  <input type="hidden" name="iCveProceso" value="<%=vParametros.getPropEspecifica("CtrlVeh") %>">
  <input type="hidden" name="iCveEtapaCa" value="<%=vParametros.getPropEspecifica("VEHEtapaCancela") %>">
  <input type="hidden" name="iCveEtapaCo" value="<%=vParametros.getPropEspecifica("VEHEtapaFin") %>">
  <input type="hidden" name="iCveSolicitante" value="<%=vParametros.getPropEspecifica("VEHSolicitaInicio") %>">
  <input type="hidden" name="iCveUsuReg" value="<%=tvUsu!=null?tvUsu.getICveusuario():0%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                 <tr>
                                    <td class="ETablaT" colspan="4">Datos del Vehículo</td>
                                 </tr>
                                 <tr>
<%                               if (clsConfig.tvVehiculo != null) {
                                    out.println(vEti.Texto("EEtiqueta", "Tipo de Vehículo:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCDscTpoVehiculo()));
                                    out.println(vEti.Texto("EEtiqueta", "Placas:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCPlacas()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Marca:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCDscMarca()));
                                    out.println(vEti.Texto("EEtiqueta", "Modelo:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCDscModelo() + "-" + clsConfig.tvVehiculo.getIAnioVeh()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Núm. Serie:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCNumSerie()));
                                    out.println(vEti.Texto("EEtiqueta", "Núm. Motor:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCNumMotor()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Inventario:"));
                                    out.println(vEti.TextoCS("ECampo", clsConfig.tvVehiculo.getCInventario(), 3));
                                 }
%>                               </tr>
                              </table>
                          <br>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="4" class="ETablaT">Detalle del Mantenimiento</td>
                            </tr>
                             <% // modificar según listado
                              if (bs != null){
                                 String readonly="";
                                 boolean lCaptura = clsConfig.getCaptura();
                                 if (request.getParameter("hdLectura") != null && request.getParameter("hdLectura").equals("0"))
                                    lCaptura = true;
                                 else {
                                    lCaptura = false;
                                    readonly = " readonly";
                                 }
                                 bs.start();
                                 int i = 0;
                                 while(bs.nextRow()){
                                    if (bs.getFieldValue("lConcluido").equals("1") || bs.getFieldValue("lCancelado").equals("1")) {
                                       lCaptura = false;
                                       readonly = " readonly";
                                       cUpdStatus = "Hidden";
                                       out.println("<input type=\"hidden\" name=\"modificar\" value=\"N\">");
                                    } else {
                                       out.println("<input type=\"hidden\" name=\"modificar\" value=\"S\">");
                                    }
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Mantenimiento:","ECampo","text",10,10,"iCveMantenimiento", bs.getFieldValue("iCveMantenimiento","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Tipo de Mantenimiento:","ECampo","text",10,10,"iCveTpoMantto", bs.getFieldValue("cDscTpoMantto","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha de Solicitud:","ECampo","text",10,10,"dtSolicitud", bs.getFieldValue("dtSolicitud","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Solicita:","ECampo","text",15,15,"iCveUsuSolicita", bs.getFieldValue("cDscUsuSolicita","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha de Inicio:","ECampo","text",10,10,"dtInicia", bs.getFieldValue("dtInicia","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Programado para:","ECampo","text",10,10,"dtProgramado", bs.getFieldValue("dtProgramado","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaT", "Asignación", 4));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Empresa Responsable:"));
                                    out.print("<td>" + vEti.SelectOneRowSinTD("iCveEmpMantto", "", clsConfig.vEmpMantto, "iCveEmpMantto", "cDscBreve", request, bs.getFieldValue("iCveEmpMantto","0").toString(), false, lCaptura) + "</td>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Kilometraje:","ECampo","text",10,10,"iKilometraje", bs.getFieldValue("iKilometraje","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Autoriza:"));
                                    out.print("<td colspan='3'>" + vEti.SelectOneRowSinTD("iCveUsuAutoriza", "", clsConfig.vUsuarios, "iCveUsuAutoriza", "cDscUsuAutoriza", request, bs.getFieldValue("iCveUsuAutoriza","0").toString(), false, lCaptura) + "</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println("<td class='EEtiqueta'>Accesorios:<p class='EPieR'>Caracteres Disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetrasAcc\" value=\"\" disabled></p></td>");
                                    out.print("<td colspan='3'><textarea name=\"cAccesorios\" cols=\"110\" rows=\"7\" onBlur=\"fMayus(this);\" onkeyup=\"fChgArea(this,1);\" onchange=\"fChgArea(this);\"" + readonly + ">" + bs.getFieldValue("cAccesorios", "") +"</textarea></td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaT", "Recepción", 4));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println("<td class='EEtiqueta'>Resultado:<p class='EPieR'>Caracteres Disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetrasRes\" value=\"\" disabled></p></td>");
                                    out.print("<td><textarea name=\"cResultado\" cols=\"40\" rows=\"7\" onBlur=\"fMayus(this);\" onkeyup=\"fChgArea(this,2);\" onchange=\"fChgArea(this);\"" + readonly + ">" + bs.getFieldValue("cResultado", "") +"</textarea></td>");
                                    out.println("<td class='EEtiqueta'>Observaciones:<p class='EPieR'>Caracteres Disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetrasObs\" value=\"\" disabled></p></td>");
                                    out.print("<td><textarea name=\"cObservaciones\" cols=\"40\" rows=\"7\" onBlur=\"fMayus(this);\" onkeyup=\"fChgArea(this,3);\" onchange=\"fChgArea(this);\"" + readonly + ">" + bs.getFieldValue("cObservaciones", "") +"</textarea></td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha de Recepción:","ECampo","text",10,10,"dtRecepcion", bs.getFieldValue("dtRecepcion","").toString(),0,"","",false,true,lCaptura));
                                    out.print(vEti.Texto("EEtiqueta","Usuario Recibe:"));
                                    out.print("<td>" + vEti.SelectOneRowSinTD("iCveUsuRecibe", "", clsConfig.vUsuarios, "iCveUsuAutoriza", "cDscUsuAutoriza", request, bs.getFieldValue("iCveUsuRecibe","0").toString(),false,lCaptura) + "</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaT", "Conclusión o Cancelación", 4));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Concluír:"));
                                    String checked = "";
                                    String disabled = "";
                                    if (bs.getFieldValue("lConcluido").equals("1"))
                                       checked = " checked";
                                    if (!lCaptura)
                                       disabled = " disabled";
                                    out.print("<td class='ECampo' colspan='3'><input type='checkbox' name='lConcluido' value='1'" + checked + disabled + "></td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Cancelar:"));
                                    if (bs.getFieldValue("lCancelado").equals("1"))
                                       checked = " checked";
                                    else
                                       checked = "";
                                    if (!lCaptura)
                                       disabled = " disabled";
                                    else
                                       disabled = "";
                                    out.print("<td class='ECampo'><input type='checkbox' name='lCancelado' value='1'" + checked + disabled + "></td>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtCancelacion", bs.getFieldValue("dtCancelacion","&nbsp;").toString(),0,"","",false,false,false));
                                    out.println("</tr>");
                                  }
                               } else  {
// No hay Datos
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta", "Mensaje:"));
                                   out.print("<td class='ECampo' colspan='10'>No existen datos coincidentes con el filtro proporcionado</td>");
                                   out.println("</tr>");
                               }
                            %>
                          </table>
  </td></tr>
  <%out.println(clsConfig.getReporte());}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
