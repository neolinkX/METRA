<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="java.util.*"%>
<html>
<%
  pg070103020CFG  clsConfig = new pg070103020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070103020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Valor|";    // modificar
  String cCveOrdenar  = "cValor|";  // modificar
  String cDscFiltrar  = "Valor|";    // modificar
  String cCveFiltrar  = "cValor|";  // modificar
  String cTipoFiltrar = "8|";                // modificar
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
  String cUpdStatus  = "Hidden";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  var cFoto1ClassID = '<%=vParametros.getPropEspecifica("Foto1ClassID")%>';
  var cFoto1CodeBase = '<%=vParametros.getPropEspecifica("Foto1CodeBase")%>';
  var cFoto1GetRuta = '<%=vParametros.getPropEspecifica("Foto1GetRuta")%>';

  var cFirma1ClassID = '<%=vParametros.getPropEspecifica("Firma1ClassID")%>';
  var cFirma1CodeBase = '<%=vParametros.getPropEspecifica("Firma1CodeBase")%>';
  var cFirma1GetRuta = '<%=vParametros.getPropEspecifica("Firma1GetRuta")%>';

  var cHuella1ClassID = '<%=vParametros.getPropEspecifica("Huella1ClassID")%>';
  var cHuella1CodeBase = '<%=vParametros.getPropEspecifica("Huella1CodeBase")%>';
  var cHuella1GetRuta = '<%=vParametros.getPropEspecifica("Huella1GetRuta")%>';

  var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';

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
  <input type="hidden" name="hdTipo" value="<%=request.getParameter("hdTipo")!=null ? request.getParameter("hdTipo") : ""%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <tr>
                              <td class="ETablaT">Digitalización</td>
                            </tr>
                            <tr>
                              <td class="ETablaC">
                              <%
                               out.print(vEti.clsAnclaTexto("EAncla","Buscar Personal","JavaScript:fSelPer(1)", "Buscar Personal"));
                              %>
                              </td>
                            </tr>
                          </table>&nbsp;
<% String cVer = ""+request.getParameter("hdTipo");
   if(cVer.compareTo("") != 0 && cVer.compareTo("null") != 0){
%>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <tr>
                              <td class="ETablaT" colspan="6">Datos del Personal</td>
                            </tr>
                            <tr>
                              <%
                               TVPERDatos vPERDatos = new TDPERDatos().findUser(Integer.parseInt(cVer,10));
                               out.print(vEti.Texto("EETiqueta", "Expediente"));
                               out.print(vEti.Texto("ECampo", ""+vPERDatos.getICveExpediente()));
                               out.print(vEti.Texto("EETiqueta", "Clave"));
                               out.print(vEti.Texto("ECampo", ""+vPERDatos.getICvePersonal()));
                               out.print(vEti.Texto("EETiqueta", "Nombre"));
                               out.print(vEti.Texto("ECampo", vPERDatos.getCNombreCompleto())+"</tr><tr>");
                               out.print(vEti.Texto("EETiqueta", "Sexo"));
                               out.print(vEti.TextoCS("ECampo", vPERDatos.getCSexo(),2));
                               out.print(vEti.TextoCS("EETiqueta", "Fecha de Nacimiento",2));
                               out.print(vEti.Texto("ECampo", ""+vPERDatos.getDtNacimiento()));
                              %>
                            </tr>
                          </table>&nbsp;

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <tr>
                              <td colspan="5" class="ETablaT">Digitalización de Datos del Personal
                              </td>
                            </tr>

                            <tr>
                              <td colspan="5" class="ETablaT">Fotografía</td>
                              <%
                                TDPERFoto dPERFoto = new TDPERFoto();
                                PageBeanScroller bsFoto = new PageBeanScroller(dPERFoto.FindByAll("where iCvePersonal = " + cVer),100);
                                if (bsFoto != null){
                                  if(bsFoto.rowSize()>0){
                                     bsFoto.firstPage();
                                     bsFoto.start();
                                     while(bsFoto.nextRow()){
                                       out.print("<tr>");
                                       out.print(vEti.Texto("ECampo", bsFoto.getFieldValue("iCveFoto", "&nbsp;").toString()));
                                       out.print(vEti.Texto("ECampo", bsFoto.getFieldValue("dtCapturada", "&nbsp;").toString()));
                                       out.print("<td colspan=\"3\" class=\"ECampo\" align=\"center\" >");
                                       out.print(vEti.clsAnclaTexto("EAncla","Mostrar","JavaScript:fShowPict('Foto',"+cVer+","+bsFoto.getFieldValue("iCveFoto","")+");", "Mostrar"));
                                       out.print("</td>");
                                       out.print("</tr>");
                                     }
                                  }
                                }
                                if(clsConfig.getCanWrite().compareTo("yes") == 0){
                                   out.print("<tr><td colspan=\"5\" class=\"EEtiquetaC\">");
                                   out.print(vEti.clsAnclaTexto("EAncla","Capturar Fotografía","JavaScript:fGetFoto();", "Capturar Fotografía"));
                                   out.print("</td></tr>");
                                }
                              %>
                            </tr>
                            <tr>
                              <td colspan="5" class="ETablaT">Huellas Digitales</td>
                              <%
                                TDGRLHuellas dGRLHuellas = new TDGRLHuellas();
                                PageBeanScroller bsHuella = new PageBeanScroller(dGRLHuellas.FindByAll(""),100);
                                if (bsHuella != null){
                                   out.print("<tr>");
                                   out.print(vEti.Texto("ETablaT","Clave"));
                                   out.print(vEti.Texto("ETablaT","Descripción"));
                                   out.print(vEti.TextoCS("ETablaT","Obligatorio",3));
                                   out.print("</tr>");
                                   TDPERHuella dPERHuella = new TDPERHuella();
                                   bsHuella.firstPage();
                                   bsHuella.start();
                                   while(bsHuella.nextRow()){
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ECampo", bsHuella.getFieldValue("iCveHuella", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bsHuella.getFieldValue("cDscHuella", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bsHuella.getFieldValue("lObligatoria", "&nbsp;").toString()));
                                     if(dPERHuella.FindByAll("where iCvePersonal = " + cVer + " and iCveHuella = " + bsHuella.getFieldValue("iCveHuella", "")).size() == 1){
                                       out.print(vEti.Texto("ECampo", bsHuella.getFieldValue("dtCapturada", "&nbsp;").toString()));
                                       out.print("<td class=\"ECampo\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Mostrar","JavaScript:fShowPict('Huella',"+cVer+","+bsHuella.getFieldValue("iCveHuella", "")+");", "Mostrar"));
                                       out.print("</td>");
                                     }else{
                                       out.print(vEti.Texto("ECampo","--"));
                                       if(clsConfig.getCanWrite().compareTo("yes") == 0){
                                         out.print("<td class=\"ECampo\">");
                                         out.print(vEti.clsAnclaTexto("EAncla","Tomar Huella","JavaScript:fGetHuella('"+bsHuella.getFieldValue("iCveHuella", "")+"');", "Tomar Huella"));
                                         out.print("</td>");
                                       }
                                     }
                                     out.print("</tr>");
                                   }
                                }
                              %>
                            </tr>
                            <tr>
                              <td colspan="5" class="ETablaT">Firma</td>
                              <%
                                TDPERFirma dPERFirma = new TDPERFirma();
                                PageBeanScroller bsFirma = new PageBeanScroller(dPERFirma.FindByAll("where iCvePersonal = " + cVer),100);
                                if (bsFirma != null){
                                   if(bsFirma.rowSize()>0){
                                     bsFirma.firstPage();
                                     bsFirma.start();
                                     while(bsFirma.nextRow()){
                                       out.print("<tr>");
                                       out.print(vEti.Texto("ECampo", bsFirma.getFieldValue("dtCapturada", "&nbsp;").toString()));
                                       out.print("<td colspan=\"4\" class=\"EEtiquetaC\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Mostrar","JavaScript:fShowPict('Firma',"+cVer+");", "Mostrar"));
                                       out.print("</td>");
                                       out.print("</tr>");
                                     }
                                  }
                                  else{
                                    if(clsConfig.getCanWrite().compareTo("yes") == 0){
                                      out.print("<tr><td colspan=\"5\" class=\"EEtiquetaC\">");
                                      out.print(vEti.clsAnclaTexto("EAncla","Capturar Firma","JavaScript:fGetFirma();", "Capturar Firma"));
                                      out.print("</td></tr>");
                                    }
                                  }
                                }
                              %>
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
