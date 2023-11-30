<%/**
 * Title: Autorización de Lotes Presuntivos
 * Description: Autorización de Lotes Presuntivos
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco Antonio Hernández García
 * @version 1.0
 * Clase: pg070303030.CFG
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>




<html>
<%
  pg070303030CFG  clsConfig = new pg070303030CFG();

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070303030.jsp");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070303030.jsp\" target=\"FRMCuerpo");
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070303030.jsp";
  String cOperador    = "1";
  String cDscOrdenar  = "Muestra|";
  String cCveOrdenar  = "toxmuestralote.iCveMuestra|";
  String cDscFiltrar  = "Muestra|";
  String cCveFiltrar  = "toxmuestralote.iCveMuestra|";
  String cTipoFiltrar = "7|";
  boolean lFiltros    = true;
  boolean lIra        = false;

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

  String cUpdStatus  = clsConfig.getUpdStatus(); //clsConfig.getUpdStatus(); "SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cEstatusIR   = clsConfig.cImprimir;

  String cCanWrite   = "Yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070303030P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070303030P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

  function fAccion(cAccion){
    form = document.forms[0];
    if(confirm("¿Está Seguro de Autorizar el Lote Seleccionado?")){
      form.target="_self";
      form.hdBoton.value = cAccion;
      form.submit();
    }
  }

  function fValidaTodo(){
    var form = document.forms[0];
    if (form.hdBoton.value == 'Guardar') {
      if(!confirm("¿Está Seguro de Eliminar los Registro seleccionados?"))
        return false;
    }
    return true;
  }

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
         <input type="hidden" name="hdBoton" value="">
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="ETablaT" colspan="8">Búsqueda del Lote</td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Año:</td>
                              <td class="ETabla">
                                 <select name="iAnio" size="1" onChange="">
                                    <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                          if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                             out.print("<option value = " + i + " selected>" + i + "</option>");
                                          else
                                             out.print("<option value = " + i + ">" + i + "</option>");
                                       }
                                    %>
                                 </select>
                              </td>
                              <td class="EEtiqueta">Laboratorio:</td>
                              <td class="ETabla">
                                  <%  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                      int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                      out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,document.forms[0].iAnio.value,document.forms[0].iCveUniMed.value,'',document.forms[0].iCveLoteCualita);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                                  %>
                              </td>
                              <td class="EEtiqueta">Lote:</td>
                              <td class="ETabla">
                                 <select name="iCveLoteCualita" size="1">
                                  <%
                                     if(request.getParameter("iAnio") != null && request.getParameter("iCveUniMed") != null){
                                       TDLoteCualita dLoteCualita = new TDLoteCualita();
                                       TVLoteCualita vLoteCualita = new TVLoteCualita();
                                       vLoteCualita.setIAnio(new Integer(request.getParameter("iAnio")).intValue());
                                       vLoteCualita.setICveLaboratorio(new Integer(request.getParameter("iCveUniMed")).intValue());
                                       Vector vExCuali = new Vector();
                                       vExCuali = dLoteCualita.FindAllLoteCualita(vLoteCualita);
                                       if (vExCuali.size() > 0){
                                          out.print("<option value = 0>Seleccione...</option>");
                                          for (int i = 0; i < vExCuali.size(); i++){
                                             vLoteCualita = (TVLoteCualita)vExCuali.get(i);
                                             if (request.getParameter("iCveLoteCualita")!=null&&request.getParameter("iCveLoteCualita").compareToIgnoreCase(new Integer(vLoteCualita.getICveLoteCualita()).toString())==0)
                                                out.print("<option value = " + vLoteCualita.getICveLoteCualita() + " selected>" + vLoteCualita.getICveLoteCualita() + "</option>");
                                             else
                                                out.print("<option value = " + vLoteCualita.getICveLoteCualita() + ">" + vLoteCualita.getICveLoteCualita() + "</option>");
                                          }
                                       }else{
                                          out.print("<option value = 0>Datos no disponibles...</option>");
                                       }
                                     }
                                  %>
                                 </select>
                              </td>
                            </tr>
                          </table>
                          &nbsp;
                          <%
                          if(bs!=null ){
                          %>
                        <table border="0" align="center" cellspacing="0" cellpadding="3">
                        <tr valign="top"><td>
                          <table border="1" class="ETablaInfo" align="center">
                            <tr>
                             <td colspan="3" class="ETablaT">Orden del Lote</td>
                            </tr>
                            <tr>
                             <td class="ETablaT">Unidad  Médica</td>
                             <td class="ETablaT">Envío</td>
                             <td class="ETablaT">Orden</td>
                            </tr>
                              <%
                                 Vector VOrden = clsConfig.getVOrden();
                                 if(VOrden.size() > 0){
                                   for(int i=0; i<VOrden.size();i++){
                                      out.print("<tr>");
                                      out.print(vEti.Texto("ETabla", ((TVTOXEnvioXLote) VOrden.get(i)).VEnvio.getCDscUniMed() ));
                                      out.print(vEti.Texto("ETablaR", "" + ((TVTOXEnvioXLote) VOrden.get(i)).VEnvio.getICveEnvio() ));
  out.print(vEti.CeldaCampo("ETabla", "input", 2, 2, "FOrden" + ((TVTOXEnvioXLote) VOrden.get(i)).VEnvio.getICveUniMed() + "-" + ((TVTOXEnvioXLote) VOrden.get(i)).VEnvio.getICveEnvio(), String.valueOf (((TVTOXEnvioXLote) VOrden.get(i)).getIOrden()), 0,"","", true, true, true));
                                      out.print("</tr>");
                                   }
                            out.print("<tr><td colspan=\"3\" class=\"ETablaC\">" + vEti.clsAnclaTexto("EAncla","Guardar Orden","JavaScript:fAccion('Orden');", "Orden") + "</td></tr>");
                                 }
                              %>
                          </table>
                           </td>
                          <td>&nbsp;&nbsp;</td>
                          <td>
                          <table border="1" class="ETablaInfo" align="center">
                            <tr>
                              <td class="ETablaT" colspan="2">Controles Externos</td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">N&uacute;m. Controles a integrar:</td>
                              <%
                                out.print(vEti.CeldaCampo("ETabla", "input", 2, 2, "FCtrolExt" , "0", 0,"","", true, true, true, request));
                              %>
                            </tr>
                            <tr>
                             <td colspan="2" class="ETablaT">Selección del Equipo</td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Equipo:</td>
                              <td class="EEtiqueta">
                                 <%
                                    TVTOXEquipo vToxEquipo = new TVTOXEquipo();
                                    TDTOXEquipo dToxEquipo = new TDTOXEquipo();
                                    Vector         vEquipo = new Vector();
                                    String cFiltro = " WHERE TOXEquipo.lCuantCual = 0 ";
                                    String cOrden  = " ORDER BY TOXEquipo.iCveEquipo ";
                                    vEquipo = dToxEquipo.FindByAll(cFiltro,cOrden);
                                    if (vEquipo.size() > 0)
                                       out.print(vEti.SelectOneRowSinTD("iCveEquipo","",vEquipo,"iCveEquipo","cDscEquipo",request,"0"));
                                    else
                                       out.print("Equipos no Disponibles...");
                                 %>
                              </td>
                            </tr>
                           </table>
                        </td></tr>
                        </table>
                          &nbsp;
                          <table align="center">
                            <tr>
                              <td><%  out.print(vEti.clsAnclaTexto("EAncla","Autorizar","JavaScript:fAccion('Autorizar');", "Autorizar")); %>
                              </td>
                              <td>&nbsp;&nbsp;</td>
                              <td><%  out.print(vEti.clsAnclaTexto("EAncla","Reporte","JavaScript:fAccion('Reporte');", "Reporte")); %>
                              </td>
                            </tr>
                          </table>

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="ETablaT">Orden</td>
                              <td class="ETablaT">NIM</td>
                              <td class="ETablaT">Eliminar<br>
                                                  <input type="checkbox" name="lGActivo" value="1" onClick="ChangeAllCheck(document, this.checked, 'checked');"></td>
                            </tr>
                             <% // modificar según listado
                                 int i = 1;
                                 bs.start();
                                 while(bs.nextRow()){
                                    out.println("<tr>");
                                       out.print(vEti.Texto("ETablaR",""+ i++));
                                       if (bs.getFieldValue("iCveMuestra").toString().compareToIgnoreCase("0")==0)
                                          out.print(vEti.Texto("ETabla",""+ "Control"));
                                       else
                                          out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveMuestra").toString()));
                                       out.print("<td class=\"ETablaC\">");
                                       out.print("<input type=\"checkbox\" name=\"" + "checked" +bs.getFieldValue("iCveMuestra", "&nbsp;")+"\" value=\""+bs.getFieldValue("iCveMuestra", "&nbsp;")+"\">");
                                       out.print("</td>");
                                     out.print("</tr>");
                                 }
                            %>
                          </table>
                          &nbsp;
                          &nbsp;
                          <%
                          }else{
                          %>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="ETablaT">No existen Muestras para el Año y el Laboratorio elegidos!</td>
                              </td>
                            </tr>
                          </table>
                          <%
                          }
                          %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>