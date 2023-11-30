
<%/**
 * Title:pg070402031.jsp
 * Description:Documentación de Investigación
 * Copyright:
 * Company:
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
  pg070402031CFG  clsConfig = new pg070402031CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070402031.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402031.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());


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
<%
             Vector vcInvResultado = new Vector();
             vcInvResultado = clsConfig.getINVResultado();
             if(vcInvResultado.size()>0){
               TVINVResultado vINVResultado = new TVINVResultado();
               int iCveRamaAnt = 0; // Sirve para pintar una sola vez el encabezado de la rama
%>
                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                <tr>
                    <td class="ETablaT" ColSpan="3">Apartados
                    </td>
                </tr>
<%
                for(int r=0;r<vcInvResultado.size();r++){ // Itera sobre los Resultados
                   vINVResultado = (TVINVResultado)vcInvResultado.get(r);
                   if (vINVResultado.getICveRama()>iCveRamaAnt || vINVResultado.getICveRama()!=iCveRamaAnt){
                      out.println("<tr>");
                      out.println("<td class='ETablaT' ColSpan='3'>");
                      out.println("Apartado: ");
                      out.println(vINVResultado.getCDscRama());
                      out.println("<input type=\"hidden\" name=\"iCveRama"+r+"\" value=\""+vINVResultado.getICveRama()+"\">");
                      out.println("</td>");
                      out.println("</tr>");
                   }
                   out.println("<tr>");
                   out.println("<td class='EEtiqueta'>");
                   out.println(vINVResultado.getCPregunta());
                   out.println("<input type=\"hidden\" name=\"iCveSintoma"+r+"\" value=\""+vINVResultado.getICveSintoma()+"\">");
                   out.println("</td>");
                   if(vINVResultado.getICveTpoResp() == 1){
                     out.println("<td class='ECampo' ColSpan='3'>");
                     if (vINVResultado.getLLogico()!=0){
                        out.println("<Input Type='checkbox' Name='lLogico"+r+"' Value='1' Checked>");
                     }else{
                        out.println("<Input Type='checkbox' Name='lLogico"+r+"' Value='1'>");
                     }
                     out.println("</td>");
                   }
                   if(vINVResultado.getICveTpoResp() == 2){
                     if (vINVResultado.getCCaracter()!=null && vINVResultado.getCCaracter().length()>0){
                        out.println("<td class='ECampo' ColSpan='2'>");
                        out.println("<textarea cols='20' rows='5'  name='cCaracter"+r+"'>");
                        out.println(vINVResultado.getCCaracter());
                        out.println("</textarea>");
                     }else{
                        out.println("<td class='ECampo' ColSpan='2'>");
                        out.println("<textarea cols='20' rows='5'  name='cCaracter"+r+"'>");
                        out.println("</textarea>");
                     }
                   }
                   if(vINVResultado.getICveTpoResp() == 3){
                     out.println("<td class='ECampo' ColSpan='2'>");
                     if (vINVResultado.getDValorIni()!=0){
                        out.println("<Input Type='Text' Name='dValorIni"+r+"' Value='"+vINVResultado.getDValorIni()+"'>");
                     }else{
                        out.println("<Input Type='Text' Name='dValorIni"+r+"' Value='0'>");
                     }
                     out.println("</td>");
                   }
                   if(vINVResultado.getICveTpoResp() == 4){
                     out.println("<td class='ECampo' ColSpan='2'>");
                     if (vINVResultado.getCCaracter()!=null && vINVResultado.getCCaracter().length()>0){
                        out.println("<Input Type='Text' Name='cCaracter"+r+"' Value='"+vINVResultado.getCCaracter()+"'>");
                     }else{
                        out.println("<Input Type='Text' Name='cCaracter"+r+"' Value=''>");
                     }
                     out.println("</td>");
                   }
                   if(vINVResultado.getICveTpoResp() == 5){
                     out.println("<td class='ECampo'>");
                     if (vINVResultado.getDValorIni()!=0){
                        out.println("<Input Type='Text' Name='dValorIni"+r+"' Value='"+vINVResultado.getDValorIni()+"'>");
                     }else{
                        out.println("<Input Type='Text' Name='dValorIni"+r+"' Value='0'>");
                     }
                     out.println("</td>");
                     out.println("<td class='ECampo'>");
                     if (vINVResultado.getDValorIni()!=0){
                        out.println("<Input Type='Text' Name='dValorFin"+r+"' Value='"+vINVResultado.getDValorFin()+"'>");
                     }else{
                        out.println("<Input Type='Text' Name='dValorFin"+r+"' Value='0'>");
                     }
                     out.println("</td>");
                   }
                   out.println("</tr>");
                   iCveRamaAnt = vINVResultado.getICveRama();
                }
%>
                </table>
               <script JavaScript>
<%
               // Matriz de campos - NO MOVER
               // autor: Javier Mendoza
               if(vcInvResultado.size()>0){
                 TVINVResultado matrixINVResultado = new TVINVResultado();
                 for(int m=0;m<vcInvResultado.size();m++){ // Itera sobre los Resultados
                    matrixINVResultado = (TVINVResultado)vcInvResultado.get(m);
                      if(matrixINVResultado.getICveTpoResp() == 1){
%>
               aINVResultado[<%=m%>]=['lLogico<%=m%>','<%=matrixINVResultado.getCPregunta()%>','<%=matrixINVResultado.getCDscRama()%>','<%=matrixINVResultado.getLObligatorio()%>','<%=matrixINVResultado.getICveTpoResp()%>'];
<%
                      }
                      if(matrixINVResultado.getICveTpoResp() == 2){
%>
               aINVResultado[<%=m%>]=['cCaracter<%=m%>','<%=matrixINVResultado.getCPregunta()%>','<%=matrixINVResultado.getCDscRama()%>','<%=matrixINVResultado.getLObligatorio()%>','<%=matrixINVResultado.getICveTpoResp()%>'];
<%
                      }
                      if(matrixINVResultado.getICveTpoResp() == 3){
%>
               aINVResultado[<%=m%>]=['dValorIni<%=m%>','<%=matrixINVResultado.getCPregunta()%>','<%=matrixINVResultado.getCDscRama()%>','<%=matrixINVResultado.getLObligatorio()%>','<%=matrixINVResultado.getICveTpoResp()%>'];
<%
                      }
                      if(matrixINVResultado.getICveTpoResp() == 4){
%>
               aINVResultado[<%=m%>]=['cCaracter<%=m%>','<%=matrixINVResultado.getCPregunta()%>','<%=matrixINVResultado.getCDscRama()%>',<%=matrixINVResultado.getLObligatorio()%>,'<%=matrixINVResultado.getICveTpoResp()%>'];
<%
                      }
                      if(matrixINVResultado.getICveTpoResp() == 5){
%>
               aINVResultado[<%=m%>]=['dValorIni<%=m%>','<%=matrixINVResultado.getCPregunta()%>','<%=matrixINVResultado.getCDscRama()%>','<%=matrixINVResultado.getLObligatorio()%>','<%=matrixINVResultado.getICveTpoResp()%>','dValorFin<%=m%>'];
<%
                      }
                 }
               }
%>
              </script>
<%
             }else{
%>
               <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
<%
                out.println("<tr>");
                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                out.println("</tr>");
%>
               </table>
<%
             }
%>
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

