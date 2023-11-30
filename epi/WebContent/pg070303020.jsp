<%/**
 * Title: Generación de Lotes de Muestras Recibidas
 * Description: Se asignan las Muestras a los lotes Cualitativos
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco Antonio Hernández García
 * @version 1.0
 * Clase: pg070303020.CFG
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.TDLoteCualita" %>
<%@ page import="gob.sct.medprev.vo.TVLoteCualita" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.util.*"%>




<html>
<%
  pg070303020CFG  clsConfig = new pg070303020CFG();

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070303020.jsp");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070303020.jsp\" target=\"FRMCuerpo");
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070303020.jsp";
  String cOperador    = "1";
  String cDscOrdenar  = "Orden|NIM|Envío|";
  String cCveOrdenar  = "TOXMuestra.iOrden|toxmuestra.iCveMuestra|toxenvio.iCveEnvio|";
  String cDscFiltrar  = "Orden|NIM|Envío|";
  String cCveFiltrar  = "TOXMuestra.iOrden|toxmuestra.iCveMuestra|toxenvio.iCveEnvio|";
  String cTipoFiltrar = "7|7|7|";
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

  String cUpdStatus  = "Hidden"; //clsConfig.getUpdStatus(); "SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cEstatusIR   = clsConfig.cImprimir;

  String cCanWrite   = "No";
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
           window.parent.FRMOtroPanel.location="pg070303020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070303020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

  function fGenLote(){
    var form=document.forms[0];
    var elems=form.elements;
    var noHaySeleccionados=true;
    for(var i=0;i<elems.length;i++){
      var elem=elems[i];
      if(elem.type.toUpperCase()=="CHECKBOX" && elem.name.indexOf("checked")==0 && elem.checked)
        noHaySeleccionados=false;
    }
    if(noHaySeleccionados) alert("Debe seleccionar al menos una muestra.");
    else{
      var msg="¿ Desea Agregar las muestras seleccionadas al lote seleccionado ? ";
      if(form.iCveLoteCualita.value==0) msg="¿ Desea Generar un nuevo lote con las muestras seleccionadas ? ";
        if (confirm(msg)){
          form.target="_self";
          form.hdBoton.value="Generar";
          form.submit();
        }
    }
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
     
   //        System.out.println("Este es el metodo "+vEntorno.getMetodoForm());
   //        System.out.println("Esta es la accion "+vEntorno.getActionForm());
     
     
     
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
                              <td class="ETablaT" colspan="6">Búsqueda del Lote</td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Año:</td>
                              <td class="ETabla">
                                 <select name="iAnio" size="1">
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
                                      out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                                      // llenaSLT(1,document.forms[0].iAnio.value,this.value,'',document.forms[0].iCveLoteCualita);
                                  %>
                              </td>
                               <%
                               if(request.getParameter("iAnio") != null && request.getParameter("iCveUniMed") != null){
                               %>
                              <td class="EEtiqueta">Lote:</td>
                              <td class="ETabla">
                                 <select name="iCveLoteCualita" size="1">
                                  <%
                                      TDLoteCualita dLoteCualita = new TDLoteCualita();
                                      TVLoteCualita vLoteCualita = new TVLoteCualita();
                                      vLoteCualita.setIAnio(new Integer(request.getParameter("iAnio")).intValue());
                                      vLoteCualita.setICveLaboratorio(new Integer(request.getParameter("iCveUniMed")).intValue());
                                      Vector vExCuali = new Vector();
                                      vExCuali = dLoteCualita.FindAllLoteCualita(vLoteCualita);
                                      out.print("<option value = 0>Lote Nuevo</option>");
                                      for (int i = 0; i < vExCuali.size(); i++){
                                         vLoteCualita = (TVLoteCualita)vExCuali.get(i);
                                         if (request.getParameter("iCveLoteCualita")!=null&&request.getParameter("iCveLoteCualita").compareToIgnoreCase(new Integer(vLoteCualita.getICveLoteCualita()).toString())==0)
                                            out.print("<option value = " + vLoteCualita.getICveLoteCualita() + " selected>" + vLoteCualita.getICveLoteCualita() + "</option>");
                                         else
                                            out.print("<option value = " + vLoteCualita.getICveLoteCualita() + ">" + vLoteCualita.getICveLoteCualita() + "</option>");
                                      }
                                  %>
                                 </select>
                              </td>
                               <%
                                  }
                               %>
                          <tr class="ETablaST"><td colspan="6">Búsqueda de las muestras por Unidad Médica</td></tr>
                          <tr class="ETabla">
                               <td class="EEtiqueta" colspan="3">Unidad Médica:</td>
                          <td class="ETabla" colspan="3">
                                  <%  out.print(vEti.SelectOneRowSinTD("iCveUnidad","",vUsuario.getVUniMed(),"iClave","cDescripcion",request,"0",true));%>
                          </td>
                          </tr>
                          </table>
                          &nbsp;
                          <%
                          if(request.getParameter("iAnio") != null && request.getParameter("iCveUniMed") != null && bs!=null ){
                          %>
                          <table border="1" class="ETablaInfo" align="center">
                            <tr>
                              <td>
                                 <%  out.print(vEti.clsAnclaTexto("EAncla","Generar Lote","JavaScript:fGenLote();", "Lote")); %>
                               </td>
                            </tr>
                         </table>
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <td class="ETablaT" colspan="7">Muestras</td>
                            <tr>
                              <td class="ETablaT">Unidad Médica</td>
                              <td class="ETablaT">Envío</td>
                              <td class="ETablaT">NIM</td>
                              <td class="ETablaT">Recolección</td>
                              <td class="ETablaT">Proceso</td>
                              <td class="ETablaT">Motivo</td>
                              <td class="ETablaT">Selección<br>
                                                  <input type="checkbox" name="lGActivo" value="1" onClick="ChangeAllCheck(document, this.checked, 'checked');"></td>
                            </tr>
                             <% // modificar según listado
                                 bs.start();
                                 while(bs.nextRow()){
                                    out.println("<tr>");
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscUM", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveEnvio", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iCveMuestra", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("dtRecoleccion", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscProceso", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscMotivo", "&nbsp;")));
                                       out.print("<td class=\"ETablaC\">");
                                       out.print("<input type=\"checkbox\" name=\"" + "checked" +bs.getFieldValue("iCveMuestra", "&nbsp;")+"\" value=\""+bs.getFieldValue("iCveMuestra", "&nbsp;")+"\">");
                                       out.print("</td>");
                                     out.print("</tr>");
                                 }
                            %>
                          </table>
                          <%
                          }else{
                            out.println("<table border=\"1\" class=\"ETablaInfo\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\">");
                            out.println("<tr>");
                            out.println("<td class=\"ETablaT\" colspan=\"7\">Muestras</td>");
                            out.println("</tr>");
                            out.println("<tr>");
                            out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen muestras para asignar a Lotes con el filtro proporcionado", 3, "", "", true, true, false));
                            out.println("</tr>");
                            out.println("</table>");
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
