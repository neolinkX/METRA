<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.Vector" %>
<html>
<%
  pg070301151CFG  clsConfig = new pg070301151CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070301151.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070301151.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "", cClave3 = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveParamCalib|cDscParam|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveParamCalib|cDscParam|";  // modificar
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
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave3  = ""+bs.getFieldValue("iCveLaboratorio", "");
       cClave2  = ""+bs.getFieldValue("iCveParamCalib", "");

     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCampoClave1" value="<%=cClave3%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                            %>

                             <tr>
                              <td colspan="5" class="ETablaT">Par&aacute;metros de Calibraci&oacute;n
                              </td>
                            </tr><tr><%
                                   out.println(vEti.Texto("EEtiqueta","Laboratorio:"));
	        	      TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
	        	      TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
        	      TVGRLUMUsuario vUMUsuario = new TVGRLUMUsuario();
	              Vector vUMusuario = new Vector();
	              vUMusuario = dUMUsuario.getUniMedxUsu2(vUsuario.getICveusuario()," AND iCveProceso = " + vParametros.getPropEspecifica("ToxProcesoLab"));
        	      out.println("<td class='ECampo'>");
	              out.println("<SELECT NAME=\"iCveLaboratorio\" SIZE=\"1\" onChange=\"\">");
        	      out.println("<option value=\"0\" selected>Seleccione...</option>");
	              if (vUMusuario.size()>0){
        	         for (int i = 0; i< vUMusuario.size();i++){
                	     vUMUsuario = (TVGRLUMUsuario)vUMusuario.get(i);
	                     if (request.getParameter("iCveLaboratorio")!=null&&request.getParameter("iCveLaboratorio").toString().compareTo(vUMUsuario.getICveUniMed()+"")==0)
        	                out.println("<option value=\""+vUMUsuario.getICveUniMed()+"\" selected>"+vUMUsuario.getCDscUniMed()+"</option>");
                	     else
                        	out.println("<option value=\""+vUMUsuario.getICveUniMed()+"\">"+vUMUsuario.getCDscUniMed()+"</option>");
	                 }
        	      }else
                	  out.println("<option value=\"-1\">Datos no disponibles</option>");
	              out.println("</SELECT>");
        	      out.println("</td>");
            %>
          </tr>

                             <%
                               if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                //Este lo muestra cuando es nuevo

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripci&oacute;n:", "ECampo", "text", 100, 100, "cDscParam", "", 4, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiToggle("EEtiqueta","Activo:","ECampo","lActivo","0","",1,true,"Si","No",lCaptura));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Valor M&iacute;nimo:", "ECampo", "text", 8, 10, "dValorMin", "0", 4, "", "", true, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Valor M&aacute;ximo:", "ECampo", "text", 8, 10, "dValorMax", "0", 4, "", "", true, true, lCaptura));
                                 out.println("</tr>");

                               }
                               else{      // Para Insertar Un Registro
                                 if (bs != null){
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 5, 5, "iCveParamCalib", bs.getFieldValue("iCveParamCalib","&nbsp;").toString(), 4, "", "", true, true, false));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Descripci&oacute;n:", "ECampo", "text", 100, 100, "cDscParam", bs.getFieldValue("cDscParam","&nbsp;").toString(), 4, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta","Activo:"));
                                   String cDisable = "";
                                   String cChecked = "";
                                   if (!lCaptura)
                                      cDisable = "disabled";
                                   if (bs.getFieldValue("lActivo","0").toString().compareToIgnoreCase("0")!=0)
                                       cChecked = "checked";
                                   out.println("<td>");
                                   out.println("<input type=\"checkbox\" name=\"lActivo\" value=\"1\" " + cDisable + " " +  cChecked + ">");
                                   out.println("</td>");
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Valor M&iacute;nimo:", "ECampo", "text", 8, 10, "dValorMin", bs.getFieldValue("dValorMin","0").toString(), 4, "", "", true, true, lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Valor M&aacute;nimo:", "ECampo", "text", 8, 10, "dValorMax", bs.getFieldValue("dValorMax","0").toString(), 4, "", "", true, true, lCaptura));
                                   out.println("</tr>");


                                 }
                                 else{
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
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
