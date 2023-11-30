
<%/**
 * Title:pg070402032.jsp
 * Description: Emitir Recomendación
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Javier Mendoza - Skynet
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070402032CFG  clsConfig = new pg070402032CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070402032.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402032.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070402032.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No Disponible|";    // modificar
  String cCveOrdenar  = "No Disponible|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "No Disponible|";  // modificar
  String cTipoFiltrar = "8|";                // modificar

  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "";             // modificar

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
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  Vector vcInvRecomFinal         = new Vector();
  Vector vcInvRegistro           = new Vector();
  vcInvRecomFinal                = clsConfig.listaInvRecomFinal();
  vcInvRegistro                  = clsConfig.listaInvRegistro();
  TVINVRecomFinal vINVRecomFinal = new TVINVRecomFinal();
  TVINVRegistro   vINVRegistro   = new TVINVRegistro();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selInvReco.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"invReco.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
  var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';


  function fOnMinR(){
    form = document.forms[0];
    if (form.iCveRecomendacion.value.length > 0 && form.iCveRecomendacion.value != null){
       if(form.iCveRecomendacion.selectedIndex >= <%=vcInvRecomFinal.size()%>){
          form.iCveRecomendacion.options [form.iCveRecomendacion.selectedIndex] = null;
       }else{
          alert("Las Recomendaciones YA Grabadas NO Pueden ser Eliminadas");
       }
    }else{
       alert("Debe Seleccionar Primero una Recomendacion");
    }
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
  <%
  if(clsConfig.getAccesoValido()){
     TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
     out.println("<input type=\"hidden\" name=\"iCveUsuario\" value=\""+vUsuario.getICveusuario()+"\">");
     out.println("<input type=\"hidden\" name=\"iIDDGPMPT\" value=\""+request.getParameter("iIDDGPMPT")+"\">");
     out.println("<input type=\"hidden\" name=\"iIDMdoTrans\" value=\""+request.getParameter("iIDMdoTrans")+"\">");
     out.println("<input type=\"hidden\" name=\"dtAccidente\" value=\""+request.getParameter("dtAccidente")+"\">");
     out.println("<input type=\"hidden\" name=\"iCveMotivo\" value=\""+request.getParameter("iCveMotivo")+"\">");
     out.println("<input type=\"hidden\" name=\"iCveMdoTrans\" value=\""+request.getParameter("iCveMdoTrans")+"\">");
     out.println("<input type=\"hidden\" name=\"iAnio\" value=\""+request.getParameter("iAnio")+"\">");
     out.println("<input type=\"hidden\" name=\"iCveProceso\" value=\""+vParametros.getPropEspecifica("INVProceso")+"\">");
     out.println("<input type=\"hidden\" name=\"cDscBreve\" value=\""+request.getParameter("cDscBreve")+"\">");
     out.println("<input type=\"hidden\" name=\"cDscMdoTrans\" value=\""+request.getParameter("cDscMdoTrans")+"\">");
     out.println("<input type=\"hidden\" name=\"iCveUniMed\" value=\""+request.getParameter("iCveUniMed")+"\">");
     out.println("<input type=\"hidden\" name=\"dtAsigna\" value=\""+request.getParameter("dtAsigna")+"\">");
  %>
     <tr>
         <td>&nbsp;
         </td>
     </tr>
     <tr>
         <td>
             <input type="hidden" name="hdBoton" value="">
         </td>
         <td valign="top">
             <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                <tr>
                    <td colspan="5" class="ETablaT">Datos del Accidente
                    </td>
                </tr>
                <tr>
                    <td class="EEtiqueta">Año:</td>
  <%
                    if(request.getParameter("iAnio")!=null){
                      out.println("<td Class='ECampo'>");
                      out.println(request.getParameter("iAnio"));
                      out.println("</td>");
                    }
  %>
                    <td class="EEtiqueta">Modo de Transporte:</td>
  <%
                    if(request.getParameter("cDscMdoTrans")!=null){
                      out.println("<td Class='ECampo'>");
                      out.println(request.getParameter("cDscMdoTrans"));
                      out.println("</td>");
                    }
  %>
                </tr>
                <tr>
                    <td class="EEtiqueta">Identificador de Medicina Preventiva:</td>
  <%
                    if(request.getParameter("iIDDGPMPT")!=null){
                      out.println("<td Class='ECampo'>");
                      out.println(request.getParameter("iIDDGPMPT"));
                      out.println("</td>");
                    }
  %>

                    <td class="EEtiqueta">Identificador de Modo de Transporte:</td>
  <%
                    if(request.getParameter("iIDMdoTrans")!=null){
                      out.println("<td Class='ECampo'>");
                      out.println(request.getParameter("iIDMdoTrans"));
                      out.println("</td>");
                    }
  %>

                </tr>
                <tr>
                    <td class="EEtiqueta">Fecha del Accidente:</td>
  <%
                    if(request.getParameter("dtAccidente")!=null){
                      out.println("<td Class='ECampo'>");
                      out.println(request.getParameter("dtAccidente"));
                      out.println("</td>");
                    }
  %>
                    <td class="EEtiqueta">Descripcion Breve:</td>
  <%
                    if(request.getParameter("cDscBreve")!=null){
                      out.println("<td Class='ECampo'>");
                      out.println(request.getParameter("cDscBreve"));
                      out.println("</td>");
                    }
  %>

                </tr>
             </table>
             &nbsp;
             <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr>
                <td colspan="7" class="ETablaT">Recomendación
                </td>
             </tr>
             <%
               out.println("<tr>");
               out.println("<td class=\"ETablaT\" RowSpan=3>Recomendación:</td>");
               out.println("<td RowSpan=2  ColSpan=2>");
               out.println("<Input Type\"text\" Class=\"ECampo\" Name=\"iCveRecomendacionS\" maxlength=\"20\" size=\"20\" Value=\"\" ReadOnly>");
               out.println("</td>");
               out.println("<td RowSpan=2>");
               out.println(vEti.clsAnclaTexto("EAncla","Buscar","javascript:fSelInvReco(document.forms[0].iCveRecomendacionS);", "Buscar Recomendación",""));
               out.println("</td>");
               out.println("<td>");
               out.println("<Input Type=\"Button\" Value=\"==>>\" Name=\"Add\" onClick=\"JavaScript:fOnAddR();\">");
               out.println("</td>");
               out.println("<td RowSpan=2 ColSpan=2>");
               out.println("<select name=\"iCveRecomendacion\" size=\"5\" MULTIPLE>");
               if (vcInvRecomFinal.size()>0){
                  for (int r=0;r<vcInvRecomFinal.size();r++){
                     vINVRecomFinal = (TVINVRecomFinal)vcInvRecomFinal.get(r);
                     out.println("<option value="+vINVRecomFinal.getICveRecomendacion()+">"+vINVRecomFinal.getCDscRecomendacion()+"</option>");
                  }
               }
               out.println("</td>");
               out.println("</tr>");
               out.println("<tr>");
               out.println("<td>");
               out.println("<Input Type=\"Button\" Value=\"<<==\" Name=\"Min\" onClick=\"JavaScript:fOnMinR();\">");
               out.println("</td>");
               out.println("</tr>");
             %>
             <tr>
                <td colspan="7" class="ETablaT">Conclusión
                </td>
             </tr>
             <tr>
             <%
             if (vcInvRegistro.size() > 0){
                for (int a=0;a<vcInvRegistro.size();a++){
                  vINVRegistro = (TVINVRegistro) vcInvRegistro.get(a);
                  if(vINVRegistro.getCConclusion() != null && vINVRegistro.getCConclusion().length()>0){
                     out.print(vEti.EtiAreaTextoCS("EEtiqueta","Nota Final:","ECampo",70,10,6,"cConclusionH", vINVRegistro.getCConclusion(),0,"","fMayus(this);",false,false,true));
                     out.println("<input type=\"hidden\" name=\"cConclusion\" value=\""+vINVRegistro.getCConclusion()+"\">");
                  }else{
                     out.print(vEti.Texto("EEtiquetaC","Nota Final:"));
                     out.println("<td ColSpan=6>");
                     out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cConclusion\" value=\""+vINVRegistro.getCConclusion()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\">");
                     out.println(vINVRegistro.getCConclusion()+"</textarea>");
                     out.println("<Input Type=\"Text\" Size=\"4\" Value=\"2000\" Name=\"iNoLetras\" readonly>");
                     out.println("</td>");
                  }
                }
             }else{
                     out.print(vEti.Texto("EEtiquetaC","Nota Final:"));
                     out.println("<td ColSpan=6>");
                     out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cConclusion\" value=\"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\">");
                     out.println("</textarea>");
                     out.println("<Input Type=\"Text\" Size=\"4\" Value=\"2000\" Name=\"iNoLetras\" readonly>");
                     out.println("</td>");
             }
             %>
             </tr>
             </table>
         </td>
     </tr>
  <%
    }else{
  %>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%
    }
  %>
  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

