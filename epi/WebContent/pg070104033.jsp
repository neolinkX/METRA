<%/**
 * Title: Selección del paciente al servicio
 * Description: Selección del paciente al servicio
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Romeo Sanchez
 * @version 1
 * Clase: pg070104033
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070104033CFG  clsConfig = new pg070104033CFG();                 // modificar
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  clsConfig.setUsuario(vUsuario.getICveusuario());
  String accion = request.getParameter("hdBoton");

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104033.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104033.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070104033.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "|";    // modificar
  String cCveOrdenar  = "|";  // modificar
  String cDscFiltrar  = "|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "|";                // modificar 7/8
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar
  boolean lCaptura = accion.startsWith("Guardar")?false:true;

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
  String cUpdStatus  = lCaptura?"SaveCancelOnly":"Hidden";
  String cNavStatus  = "Hidden";//clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

//int iCveUniMed =  Integer.parseInt(request.getParameter("iCveUniMed")==null?"0":request.getParameter("iCveUniMed"));
//int iCveProceso = Integer.parseInt(request.getParameter("iCveProceso")==null?"0":request.getParameter("iCveProceso"));
//int iCveModulo =  Integer.parseInt(request.getParameter("iCveModulo")==null?"0":request.getParameter("iCveModulo"));
int iCveServicio =  Integer.parseInt(request.getParameter("iCveServicio")==null?"0":request.getParameter("iCveServicio"));
//int iCveRama =  Integer.parseInt(request.getParameter("iCveRama")==null?"0":request.getParameter("iCveRama"));

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
    function activa(actual, rama) {
        form = document.forms[0];
            for (var i=0; i < form.elements.length; i++) {
                    nombre = form.elements[i].name;
                    sSint = "checkSintoma";
                    sintoma = nombre.substring(12,nombre.indexOf("_")); // 12 es la longitud de 'checkSintoma'
                    if (sintoma==rama) {
                            form.elements[i].checked=actual.checked;
                    }
            }
    }

    function activaX(actual, rama) {
        form = document.forms[0];
            var idx=0;
            var check=0;
            for (var i=0; i < form.elements.length; i++) {
                    nombre = form.elements[i].name;
                    sintoma = nombre.substring(12,nombre.indexOf("_")); // 12 es la longitud de 'checkSintoma'
                    if (sintoma==rama) {
                         rama = eval('form.checkRama'+rama);
                         rama.checked = actual.checked;
                    }
            }
    }

function truncar(campo){
  campo.value=campo.value.substring(0,499);
return true;
}

function validaNumero(campo){
  if(isNaN(campo.value)) {
    alert("Debe escribir un número");
    campo.focus();
    return false;
  }
  return true;
}

function fBuscar(){
  form = document.forms[0];
  var cVMsg = "";
  if (form.iCveServicio) {
        if (form.iCveServicio.value==-1) {
           cVMsg += "\n - El campo 'Servicio' es Obligatorio, favor de seleccionar.";
        }
  }
  if (cVMsg != ''){
      alert("Datos no Válidos: \n" + cVMsg);
  }  else {
          form.hdBoton.value = 'Actual';
          form.tpoBusqueda.value = 'buscarPorServicio';
          form.target = "_self";
          form.submit();
  }
}

  function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 1999)
      fArea.value = cText = cText.substring(0,1999);
    form.iNoLetras.value = 1999 - cText.length;
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
  <input type="hidden" name="FILNumReng" value="500">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     } else {
       cPosicion = request.getParameter("hdRowNum");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">

  <input type="hidden" name="iCvePerfil" value="<%=request.getParameter("iCvePerfil")%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo")%>">
  <input type="hidden" name="iCvePersonal" value="<%=request.getParameter("iCvePersonal")%>">
  <input type="hidden" name="iCveExpediente" value="<%=request.getParameter("iCveExpediente")%>">
  <input type="hidden" name="iNumExamen" value="<%=request.getParameter("iNumExamen")%>">
  <input type="hidden" name="iCveProceso" value="<%=vParametros.getPropEspecifica("EPIProceso")%>">
  <input type="hidden" name="tpoBusqueda" value="<%=request.getParameter("tpoBusqueda")%>">
  <input type="hidden" name="iCveUsuario" value="<%=vUsuario.getICveusuario()%>">

   <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdCDscGrupo" value="">
  <input type="hidden" name="hdICvePerfil" value="">



  <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr><td class="ETablaT" colspan="6">Datos del Personal</td></tr>
<% // inicializador de datos
   TVPERDatos personal = clsConfig.getPersonal();
   long edad = clsConfig.getEdadPersonal();
%>
<!-- Personal -->

                            <tr>
                              <td class="EEtiqueta" colspan="1">Nombre:</td>
                              <td class="ETabla" colspan="2"><%=personal.getCApPaterno()+" "+personal.getCApMaterno()+" "+personal.getCNombre()%>
                              </td>
                              <td class="EEtiqueta" colspan="2">Expediente:</td>
                              <td class="ETabla" colspan="1"><%=personal.getICveExpediente()%>
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Edad:</td>
                              <td class="ETabla" colspan="2"><%=edad%>&nbsp;a&ntilde;os
                              </td>
                              <td class="EEtiqueta" colspan="2">Sexo:</td>
                              <td class="ETabla" colspan="1"><%=personal.getCSexo().equalsIgnoreCase("M")?"Masculino":"Femenino"%>
                              </td>
                            </tr>
<!-- Servicio -->
<%
   Vector servicios = clsConfig.getSelectServicios(); // obtiene los servicios marcados como interconsulta
   String servicioAnterior = request.getParameter("iCveServicio");
%>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Servicio:</td>
                              <td class="ETabla" colspan="4">
                               <%
                                 out.print(vEti.SelectOneRowSinTD("iCveServicio", "", servicios, "iCveServicio", "cDscServicio", request, servicioAnterior));
                               %>
                              </td>
                              <td class="ETabla" colspan="1">
<%
                                 out.print(vEti.clsAnclaTexto("EAnclaC","Buscar",
                                                              "javascript:fBuscar();","Buscar"));
%>
                            </td>
                              </td>
                            </tr>


                            <%
                               boolean esNuevaRama = true;
                               int ramaActual = 0;
                               int ramaAnterior = 0;
                               if (bs != null){
                                   bs.start();
                                   int c=0;
                                   while(bs.nextRow()){

                                      ramaActual = Integer.parseInt(bs.getFieldValue("iCveRama","").toString());
                                      esNuevaRama = ramaActual!=ramaAnterior?true:false;

                                      if(esNuevaRama) {
// Rama
                            %>
                              <tr>
                                <td class="ETablaT" colspan="1">Rama:</td>
                                <td class="ETabla" colspan="5"><%=bs.getFieldValue("cDscRama", "&nbsp;").toString()%></td>
                              </tr>
                              <tr>
                                <td class="ETablaT" colspan="5">Síntoma</td>
                                <td class="ETablaT" colspan="1"><input type="checkbox" name="<%="checkRama"+ramaActual%>"
                                     onClick="javascript:activa(this, <%=ramaActual%>)">&nbsp;Agregar</td>
                              </tr>
                            <%        }

// Sintomas:
                             %>
                            <tr>
                                <td class="ETabla" colspan="5" width="555"><%=bs.getFieldValue("cDscBreve", "&nbsp;").toString()%></td>
                             <%
                                    out.print(vEti.ToggleMov("ETablaC","checkSintoma"+ramaActual+"_"+bs.getFieldValue("iCveSintoma", ""),
                                              bs.getFieldValue("lActivo").toString(),"javascript:activaX(this, " + ramaActual + ")",0,lCaptura,"SI","NO", true));
                             %>
                            </tr>
                            <%
                                      c++;
                                      ramaAnterior = ramaActual;
                                   }

// detalle de la interconsulta
%>  <input type="hidden" name="hdTotalRows" value="<%=c%>">
<p>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <tr>
                               <td class="ETablaT" colspan="2">Detalle de la Interconsulta</td>
                            </tr>
                            <tr>
                               <td class="EEtiqueta" colspan="1">Unidad Médica / Módulo:</td>
                               <%
                                  if(lCaptura) { %>
                               <td class="ECampo" colspan="1">
                               <%=clsConfig.getSelectUniMedModulo()%>
                               </td>
                                  <%} else {%>
                               <td class="ECampo" colspan="1"><%=clsConfig.getSelectedUniMedModulo()%></td>
                                  <%}%>
                            </tr>
                            <tr>
                            <% if (lCaptura) {%>
                               <%=vEti.EtiCampoCS("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10, 1,"dtPropAtencion", "" , 0, "", "fValFecha(this.value);", false, true ,lCaptura, request)%>
                            <% } else {%>
                               <td class="EEtiqueta" colspan="1">Fecha:</td>
                               <td class="ECampo" colspan="1">
                               <%=request.getParameter("dtPropAtencion")%>
                               </td>
                            <% } %>
                            </tr>
                            <tr>
<%
                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Nota:");
                                  if(lCaptura) {
                                    out.println("<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p>");
                                  }
                                    out.println("</td>");
                                    out.println("<td class=\"ECampo\" colspan=\"1\"><textarea "+(lCaptura?"":"readonly")+" cols=\"50\" rows=\"20\"  name=\"cMtvoSolicitud\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                                  if(!lCaptura) {
                                    out.println(request.getParameter("cMtvoSolicitud"));
                                  }
                                    out.println("</textarea></td>");
                                    out.println("</tr>");
%>
                            </tr>
                          </table>


<%
                               } else {
/*
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 6, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
*/
                               }
                           %>
                          </table>
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