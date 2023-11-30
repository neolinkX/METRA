<%/**
 * Title: Listado de las Sustancias
 * Description: Listado de las Sustancias
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>

<html>
<%

  pg070306040CFG  clsConfig = new pg070306040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070306040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070306011.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "|";    // modificar
  String cCveOrdenar  = "|";  // modificar
  String cDscFiltrar  = "|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');

  }

  function fIrCatalogo(cCampoClave, cCampoClave1){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave;
    form.hdCampoClave2.value = cCampoClave1;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070306011.jsp';
    form.submit();
  }

  function fValidaFecha(cFecha,cName){
    var cErr="";
    var acTmp=cFecha.split("/");
    var dtFecha=new Date(acTmp[2],acTmp[1]-1,acTmp[0]);
    if(isNaN(dtFecha)) cErr="- La "+cName+" no es una fecha valida\n";
    return cErr;
  }

  function fValidaTodo(form){
    var cErr="";
    var cTmp="";
    form = document.forms[0];
    if (form.chkfprep.checked){
       cTmp=form.dtPreparacion.value;
       if(cTmp.length>0)
          cErr+=fValidaFecha(cTmp,"Fecha de preparación");
       else
          cErr+=fValidaFecha(cTmp,"Fecha de preparación");
    }
    if (form.chkfcad.checked){
       cTmp=form.dtCaducidad.value;
       if(cTmp.length>0)
          cErr+=fValidaFecha(cTmp,"Fecha de Caducidad");
       else
          cErr+=fValidaFecha(cTmp,"Fecha de Caducidad");
    }
    if (cErr.length>0){
      alert(cErr);
      return false;
    }
    return true;
  }

</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>" onsubmit="return fValidaTodo(this);">
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

  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">


  <input type="hidden" name="hdBuscar" value="N">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="3" class="ETablaT">Filtros</td>
                            </tr>
<%
                                String vsituacion = "";
                                if (request.getParameter("vsituacion") != null) {
                                   vsituacion = request.getParameter("vsituacion");
                                }

                                out.println("<tr><td colspan=\"2\" class=\"EEtiqueta\">Laboratorio:</td>");
                                out.println("<td class='ECampo'>");
                                out.println(vEti.SelectOneRowSinTD("iCveLaboratorio", "fCambiaLab('" + clsConfig.getAccionOriginal() + "');", (Vector) AppCacheManager.getColl("GRLUniMed",""), "iCveUniMed", "cDscUniMed", request, "0",true));
                                out.println("</td></tr>");
                                String checked = "";

                                if (request.getParameter("chkfprep") != null && request.getParameter("chkfprep").equals("1"))
                                    checked = " checked";
                                else
                                    checked = "";
                                out.println("<tr><td><input type=\"checkbox\"" + checked + " name=\"chkfprep\" value=\"1\"></td>");
                                out.println(vEti.EtiCampo("EEtiquetaL","Preparación:","ECampo","text",15,10,"dtPreparacion","",0,"","fMayus(this);",false,true,true,request));
                                out.println("</tr>");

                                if (request.getParameter("chkfcad") != null && request.getParameter("chkfcad").equals("1"))
                                    checked = " checked";
                                else
                                    checked = "";
                                out.println("<tr><td><input type=\"checkbox\"" + checked + " name=\"chkfcad\" value=\"1\"></td>");
                                out.println(vEti.EtiCampo("EEtiquetaL","Caducidad:","ECampo","text",15,10,"dtCaducidad","",0,"","fMayus(this);",false,true,true,request));
                                out.println("</tr>");

                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                Vector vcPersonal;
                                int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                int iUniMed = 0;
                                if(request.getParameter("iCveUniMed") != null && request.getParameter("iCveUniMed").compareTo("")!=0){
                                   iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
                                }else{
                                   vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                   if(vcPersonal.size() != 0){
                                       iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                   }
                                }
                                vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                                if (request.getParameter("chkPreparadoPor") != null && request.getParameter("chkPreparadoPor").equals("1"))
                                    checked = " checked";
                                else
                                    checked = "";
                                out.println("<tr><td><input type=\"checkbox\"" + checked + " name=\"chkPreparadoPor\" value=\"1\"></td>");
                                out.println(vEti.Texto("EEtiquetaL","Preparado por:"));
                                out.println("<td>");
                                out.print(vEti.SelectOneRowSinTD("iCveUsuPrepara", "fCambiaLab('" + clsConfig.getAccionOriginal() + "');", vcPersonal,"iCveUsuario", "cNomCompleto", request, ""));
                                out.println("</td>");
                                out.println("</tr>");

                                TDTOXMarcaSust marca = new TDTOXMarcaSust();
                                Vector vMarcas = marca.FindByAll("");

                                if (request.getParameter("chkMarca") != null && request.getParameter("chkMarca").equals("1"))
                                    checked = " checked";
                                else
                                    checked = "";
                                out.println("<tr><td><input type=\"checkbox\"" + checked + " name=\"chkMarca\" value=\"1\"></td>");
                                out.println(vEti.Texto("EEtiquetaL","Marca:"));
                                out.println("<td>");
                                out.print(vEti.SelectOneRowSinTD("iCveMarcaSust", "", vMarcas,"iCveMarcaSust", "cDscMarcaSust", request, ""));
                                out.println("</td></tr>");

                                if (request.getParameter("chkSituacion") != null && request.getParameter("chkSituacion").equals("1"))
                                    checked = " checked";
                                else
                                    checked = "";
                                out.println("<tr><td><input type=\"checkbox\"" + checked + " name=\"chkSituacion\" value=\"1\"></td>");
                                out.println(vEti.Texto("EEtiquetaL","Situación:"));

                                if (request.getParameter("situacion") != null && request.getParameter("situacion").equals("enuso"))
                                    checked = " checked";
                                else
                                    checked = "";
                                out.println("<td class=\"EEtiqueta\" style=\"text-align:left\"><input type=\"radio\" name=\"situacion\" value=\"enuso\" " + checked + ">En uso<br>");

                                if (request.getParameter("situacion") != null && request.getParameter("situacion").equals("agotado"))
                                    checked = " checked";
                                else
                                    checked = "";
                                out.println("<input type=\"radio\" name=\"situacion\" value=\"agotado\" " + checked + ">Agotado<br>");

                                if (request.getParameter("situacion") != null && request.getParameter("situacion").equals("baja"))
                                    checked = " checked";
                                else
                                    checked = "";
                                out.println("<input type=\"radio\" name=\"situacion\" value=\"baja\" " + checked + ">Baja</td></tr>");

                                out.println("<tr><td colspan=\"3\" class='ETablaC'>");
                                out.println(new TCreaBoton().clsCreaBoton(vEntorno, 1, "Buscar", "bBuscar", vEntorno.getTipoImg(), "Buscar el Lote Confirmativo","Buscar", vParametros));
                                out.println("</td></tr>");


%>
</table>
<br>
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
<%                              if (bs != null){ %>

                                   <tr><td class="ETablaT" colspan="7">Reactivos</td></tr>
                                   <tr>
                                       <td class="ETablaT">Clave</td>
                                       <td class="ETablaT">Descripción Breve</td>
                                       <td class="ETablaT">Tipo</td>
                                       <td class="ETablaT">Fecha de Preparación</td>
                                       <td class="ETablaT" colspan="3">Volumen Total</td>
                                   </tr>
                            <%
                                   TFechas fecha = new TFechas();
                                   java.util.Calendar otra = java.util.Calendar.getInstance();
                                   bs.start();
                                   while(bs.nextRow()){
                                       out.println("<tr>");
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveReactivo", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscBreve", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscTpoReact", "&nbsp;")));

                                       out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("cPreparacion", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("dVolumen", "&nbsp;")));

                                       out.print("<td class=\"ETablaC\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" + bs.getFieldValue("iCveLaboratorio","") + "','" + bs.getFieldValue("iCveReactivo","") + "'," + "'pg070306011.jsp');", "Ir a..."));
                                       out.print("</td>");

                                       /*out.print("<td class=\"ETablaC\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Comportamiento","JavaScript:document.forms[0].submit();",""));
                                       out.println("</td>");*/
                                       out.println("</tr>");
                                   }
                               }else{
                                  out.println("<tr>");
                                  out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 5,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                  out.println("</tr>");
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
