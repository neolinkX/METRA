<%/**
 * Title: pg070103030
 * Description:
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070103030CFG  clsConfig = new pg070103030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070103030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070103030.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = true;                  // modificar
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
  String cUpdStatus  = "SaveCancelOnly";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  int iNumExamen = 0;

  int iCveMotivo = 0;
  if (vParametros.getPropEspecifica("EPIProceso") != null){
     iCveMotivo = Integer.parseInt(vParametros.getPropEspecifica("EPIProceso"));
  }
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  function fSelectedPer(iCvePersonal,iCveExpediente,iNumExamen){
    form = document.forms[0];
    form.target =  "FRMDatos";
    form.hdICvePersonal.value = iCvePersonal;
    form.iCveExpediente.value = iCveExpediente;
    form.iNumExamen.value = iNumExamen;
    form.submit();
  }

function fChgArea(fArea,Cont){
<%
TDGRLReqxMotivo dTDRqxMt = new TDGRLReqxMotivo();
Vector vRqxMt = new Vector();
vRqxMt = dTDRqxMt.FindByAllWithReq(" Where a.iCveMotivo = "+iCveMotivo+" And lActivo = 1");
%>

  form = document.forms[0];
      cText = fArea.value;
      if(cText.length > 499)
        fArea.value = cText = cText.substring(0,499);
<%
      if (vRqxMt.size() > 0){
         for (int w = 0; w < vRqxMt.size(); w++){
%>
            if (Cont == <%=w%>){
               form.iNoLetras<%=w%>.value = 499 - cText.length;
            }
<%
         }
      }
%>
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
<%  if (request.getParameter("hdICvePersonal") != null){
%>
       <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal").toString()%>">
<%
    }else{
%>
        <input type="hidden" name="hdICvePersonal" value="">
<%
    }
%>
<%  if (request.getParameter("iNumExamen") != null){
%>
     <input type="hidden" name="iNumExamen" value="<%=request.getParameter("iNumExamen")%>">
<%
        if (request.getParameter("iNumExamen").toString().length()>0){
           iNumExamen = Integer.parseInt(request.getParameter("iNumExamen").toString());
        }
    }else{
%>
        <input type="hidden" name="iNumExamen" value="">

<%
        iNumExamen = 0;
    }
%>
<%  if (request.getParameter("iCveExpediente") != null){
%>
  <input type="hidden" name="iCveExpediente" value="<%=request.getParameter("iCveExpediente")%>">
<%
    }else{
%>
        <input type="hidden" name="iCveExpediente" value="">
<%
    }
%>
  <input type="hidden" name="hdRowNum" value="1">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="iCveMotivo" value="<%=iCveMotivo%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <%
  if(clsConfig.getAccesoValido()){
     TVPERDatos vPerDatos=clsConfig.findUser();
  %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" align="center" class="ETablaT">Búsqueda de Personal
                              </td>
                            </tr>
                            <tr>
                              <td colspan="5" align="center">
                               <%=vEti.clsAnclaTexto("EAncla","Buscar","JavaScript:fSelPer(1);", "Buscar","")%>
                              </td>
                            </tr>
                          </table>&nbsp;

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Datos del Personal
                              </td>
                            </tr>
                        <%
                        if(vPerDatos!=null && iNumExamen != 0){
                        %>
                            <tr>
                              <td class="EEtiqueta">Nombre:</td>
                              <td class="ECampo"><%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%></td>
                              <td class="EEtiqueta" colspan="2">Expediente:</td>
                              <td class="ECampo"><%=vPerDatos.getICveExpediente()%></td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Edad:</td>
                              <td class="ECampo"><%=clsConfig.getEdad(vPerDatos.getDtNacimiento())%> Años</td>
                              <td class="EEtiqueta" colspan="2">Sexo:</td>
                              <%
                                 if (vPerDatos.getCSexo().equalsIgnoreCase("M")){
                              %>
                              <td class="ECampo">Masculino</td>
                              <%
                                 }else{
                              %>
                              <td class="ECampo">Femenino</td>
                              <%
                                 }
                              %>
                            </tr>
                          </table>&nbsp;

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Registro de Documentos
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Descripción Breve</td>
                              <td class="ETablaT">Descripción</td>
                              <td class="ETablaT">Obligatorio</td>
                              <td class="ETablaT">Entregado</td>
                              <td class="ETablaT">Observaciones</td>

                            </tr>
                            <%
                               TVGRLReqxMotivo vTVReqxMotivo = new TVGRLReqxMotivo();
                               TDGRLReqxMotivo dTDReqxMotivo = new TDGRLReqxMotivo();
                               Vector vReqxMotivo = new Vector();
                               vReqxMotivo = dTDReqxMotivo.FindByAllWithReq(" Where a.iCveMotivo = "+iCveMotivo+" And lActivo = 1");
                               if (bs != null){
                                  if (vReqxMotivo.size() > 0){
                                     for (int i = 0; i < vReqxMotivo.size(); i++){
                                         boolean flaged = false;
                                         vTVReqxMotivo = (TVGRLReqxMotivo)vReqxMotivo.get(i);
                                         out.println("<tr>");
                                         out.print(vEti.Texto("ECampo",""+ vTVReqxMotivo.getCDscReqBreve()));
                                         out.print(vEti.Texto("ECampo",""+ vTVReqxMotivo.getCDscRequisito()));
                                         if (vTVReqxMotivo.getLObligatorio() == 1){
                                            out.print(vEti.Texto("EEtiquetaC","Si"));
                                         }else{
                                            out.print(vEti.Texto("EEtiquetaC","No"));
                                         }
                                         bs.firstRow();
                                         bs.start();
                                         while(bs.nextRow()){
                                           if (Integer.parseInt(bs.getFieldValue("iCveRequisito", "").toString()) == vTVReqxMotivo.getICveRequisito()){
                                              out.println("<td align=\"center\">");
                                              out.println("<input type=\"checkbox\" name=\"iCveRequisito"+i+"\" value=\""+bs.getFieldValue("iCveRequisito", "").toString()+"\" Checked>");
                                              out.println("</td>");
                                              out.println("<td>");
                                              out.println("<textarea cols=\"50\" rows=\"2\"  name=\"cObservacion"+i+"\" value=\""+bs.getFieldValue("cObservacion", "")+"\" onkeypress=\"fChgArea(this,"+i+");\" onchange=\"fChgArea(this,"+i+");\">");
                                              out.println(bs.getFieldValue("cObservacion", "")+"</textarea>");
                                              out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras"+i+"\" disabled>");
                                              out.println("</td>");
                                              flaged =  true;
                                           }
                                        }
                                        if (!flaged){
                                              out.println("<td align=\"center\">");
                                              out.println("<input type=\"checkbox\" name=\"iCveRequisito"+i+"\" value=\""+vTVReqxMotivo.getICveRequisito()+"\">");
                                              out.println("</td>");
                                              out.println("<td>");
                                              out.println("<textarea cols=\"50\" rows=\"2\"  name=\"cObservacion"+i+"\" value=\"\" onkeypress=\"fChgArea(this,"+i+");\" onchange=\"fChgArea(this,"+i+");\">");
                                              out.println("</textarea>");
                                              out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras"+i+"\" disabled>");
                                              out.println("</td>");
                                        }
                                              out.println("</tr>");
                                     }
                                   }
                               }else{
                                  if (vReqxMotivo.size() > 0){
                                     for (int i = 0; i < vReqxMotivo.size(); i++){
                                        vTVReqxMotivo = (TVGRLReqxMotivo)vReqxMotivo.get(i);
                                        out.println("<tr>");
                                        out.print(vEti.Texto("ECampo",""+ vTVReqxMotivo.getCDscReqBreve()));
                                        out.print(vEti.Texto("ECampo",""+ vTVReqxMotivo.getCDscRequisito()));
                                        if (vTVReqxMotivo.getLObligatorio() == 1){
                                           out.print(vEti.Texto("EEtiquetaC","Si"));
                                        }else{
                                           out.print(vEti.Texto("EEtiquetaC","No"));
                                        }
                                        out.println("<td align=\"center\">");
                                        out.println("<input type=\"checkbox\" name=\"iCveRequisito"+i+"\" value=\""+vTVReqxMotivo.getICveRequisito()+"\" >");
                                        out.println("</td>");
                                        out.println("<td>");
                                        out.println("<textarea cols=\"50\" rows=\"2\"  name=\"cObservacion"+i+"\" value=\"\" onkeypress=\"fChgArea(this,"+i+");\" onchange=\"fChgArea(this,"+i+");\">");
                                        out.println("</textarea>");
                                        out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras"+i+"\" disabled>");
                                        out.println("</td>");
                                        out.println("</tr>");
                                     }
                                  }
                               }
                        }else{
                        %>
                            <tr>
                              <td class="EEtiquetaC" colspan="4" align="center">Datos no disponibles</td>
                            </tr>
                        <%
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
