<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<html>
<%
  pg071009000CFG  clsConfig = new pg071009000CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg071009000.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071009000.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "ICVEAYUDA|Nombre|";    // modificar
  String cCveOrdenar  = "ICVEAYUDA|CDSAYUDA|";  // modificar
  String cDscFiltrar  = "ICVEAYUDA|Nombre|";    // modificar
  String cCveFiltrar  = "ICVEAYUDA|CDSAYUDA|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Reporte";             // modificar

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

  TDPais dPais = new TDPais();
  TVPais vPais = new TVPais();
  Vector vcPais = new Vector();

  TDEntidadFed dEntidadFed = new TDEntidadFed();
  TVEntidadFed vEntidadFed = new TVEntidadFed();
  Vector vcEntidadFed = new Vector();

  TDMunicipio dMunicipio = new TDMunicipio();
  TVMunicipio vMunicipio = new TVMunicipio();
  Vector vcMunicipio = new Vector();

  TDSEGUsuario dSEGUsuario = new TDSEGUsuario();
  TVSEGUsuario vSEGUsuario = new TVSEGUsuario();
  Vector vcSEGUsuario = new Vector();

  TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
  TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();
  Vector vcGRLUMUsuario = new Vector();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg071003011.js)
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
       cClave  = ""+bs.getFieldValue("iCveAyuda", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%if(cPosicion.compareTo("")==0) out.print(request.getParameter("hdRowNum")); else out.print(cPosicion);%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr><% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                              <td colspan="4" class="ETablaT">Informaci&oacute;n del paquete o documento de Ayuda
                              </td>
                            </tr>
                            <%
// N U E V O

                               if (lNuevo){ // Modificar de acuerdo al catálogo específico

                                 out.println("<tr>");
                                   out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                   out.println("<td>");
                                   out.print("<input type='text' size='10' disabled>");
                                   out.println("</td>");
                                   out.print("<td class='EEtiqueta'>Vigente:</td>");
                                   out.print("<td class='ECampo'>Si<input type='radio' name='lVigente' value='Si' checked>");
                                   out.print("No<input type='radio' name='lVigente' value='No'></td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                   out.print("<td class='EEtiqueta'>Nombre:</td>");
                                   out.print("<td colspan='3'><input type='text' name='cDsAyuda' size=80 maxLength=100 OnBlur='fMayus(this);'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
                                 out.println("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Descripci&oacute;n:</td>");
                                   //out.print("<td colspan='3'><input type='text' name='cCalle' size=40 maxLength=50 OnBlur='fMayus(this);'></td>");
                                   out.print("<td colspan='3'> <textarea cols=\"80\" rows=\"10\"  name=\"cDscDescripcion\" value=\"null\" onkeypress=\"fChgArea(this);\" ");
                                   out.print(" onchange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\"></textarea></td>");
                                 out.print("</tr>");
                                 
                                 out.println("<tr>");
	                                 out.print("<td class='EEtiqueta'>URL:</td>");
	                                 out.print("<td colspan='3'><input type='text' name='cUrl' size=80 maxLength=300></td>");
                               	 out.println("</tr>");
                                 
                                 out.print("<tr>");
                                 	out.print("<td colspan='4' class='ETablaST'>Categor&iacute;a</td>");
                               	 out.print("</tr>");

                               	 
                               	out.print("<tr><td class=\"EEtiqueta\">Manual:</td>");
                               		out.print("<td><input type=\"checkbox\" name=\"lManual\" value=\"1\"></td>");
                               	out.print("<td class=\"EEtiqueta\">Guia:</td>");
                               		out.print("<td><input type=\"checkbox\" name=\"lGuia\" value=\"1\"></td></tr>");
                               	out.print("<tr><td class=\"EEtiqueta\">Software:</td>");
                               		out.print("<td><input type=\"checkbox\" name=\"lSoftware\" value=\"1\"></td>");
                               	 out.print("<td class='EEtiqueta'>Solo disponible para el Administrador:</td>");
                               		out.print("<td class='ECampo'>Si<input type='radio' name='lAdmin' value='Si' checked>");
                                    out.print("No<input type='radio' name='lAdmin' value='No'></td>");
                               		out.print("</tr>"); 
                               	 
							  }
                               else{
                                 if (bs != null){

// M O D I F I C A R

                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                	   
                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Clave:</td>");
                                       out.print("<td class='ECampo'>" + bs.getFieldValue("iCveAyuda", "&nbsp;") + "</td>");
                                       out.print("<td class='EEtiqueta'>Vigente:</td>");
                                       if(Integer.parseInt(bs.getFieldValue("lVigente").toString())==1){
                                         out.print("<td class='ECampo'>Si<input type='radio' name='lVigente' value='Si' checked>");
                                         out.print("No<input type='radio' name='lVigente' value='No'></td>");
                                       }
                                       else{
                                         out.print("<td class='ECampo'>Si<input type='radio' name='lVigente' value='Si'>");
                                         out.print("No<input type='radio' name='lVigente' value='No' checked></td>");
                                       }
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Nombre:</td>");
                                       out.print("<td colspan='3'><input type='text' name='cDsAyuda' size=80 maxLength=100 OnBlur='fMayus(this);' value='");
                                       out.print(""+bs.getFieldValue("cDsAyuda"));
                                       out.print("'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Descripci&oacute;n:</td>");
                                       out.print("<td colspan='3'> <textarea cols=\"80\" rows=\"10\"  name=\"cDscDescripcion\" value=\"\" onkeypress=\"fChgArea(this);\" ");
                                       out.print(" onchange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">"+bs.getFieldValue("cDscDescripcion", "&nbsp;")+"</textarea></td>");
                                     out.print("</tr>");
                                     
                                     
                                     out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>URL:</td>");
                                     out.print("<td colspan='3'><input type='text' name='cUrl' size=80 maxLength=300  value='");
                                     out.print(""+bs.getFieldValue("cUrl"));
                                     out.print("'></td>");
                                  	 out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td colspan='4' class='ETablaST'>Categor&iacute;a</td>");
                                     out.print("</tr>");
                                     
                                     out.println("<tr>");
                                     out.println(vEti.Texto("EEtiqueta","Manual:"));
                                     String cDisable4 = "";
                                     String cChecked4 = "";
                                     //if (!lCaptura)
                                        //cDisable4 = "disabled";
                                     if (bs.getFieldValue("lManual","0").toString().compareToIgnoreCase("0")!=0)
                                         cChecked4 = "checked";
                                     out.println("<td>");
                                     out.println("<input type=\"checkbox\" name=\"lCPersonal\" value=\"1\" " + cDisable4 + " " +  cChecked4+ ">");
                                     out.println("</td>");
                                     out.println(vEti.Texto("EEtiqueta","Guia:"));
                                     String cDisable5 = "";
                                     String cChecked5 = "";
                                     //if (!lCaptura)
                                        //cDisable5 = "disabled";
                                     if (bs.getFieldValue("lGuia","0").toString().compareToIgnoreCase("0")!=0)
                                         cChecked5 = "checked";
                                     out.println("<td>");
                                     out.println("<input type=\"checkbox\" name=\"lGuia\" value=\"1\" " + cDisable5 + " " +  cChecked5+ ">");
                                     out.println("</td>");
                                     out.println("</tr>");
                                     
                                     
                                     out.println("<tr>");
                                     out.println(vEti.Texto("EEtiqueta","Software:"));
                                     String cDisable6 = "";
                                     String cChecked6 = "";
                                     //if (!lCaptura)
                                        //cDisable6 = "disabled";
                                     if (bs.getFieldValue("lSoftware","0").toString().compareToIgnoreCase("0")!=0)
                                         cChecked6 = "checked";
                                     out.println("<td>");
                                     out.println("<input type=\"checkbox\" name=\"lSoftware\" value=\"1\" " + cDisable6 + " " +  cChecked6+ ">");
                                     out.println("</td>");
                                     out.print("<td class='EEtiqueta'>Solo disponible para el Administrador:</td>");
                                     if(Integer.parseInt(bs.getFieldValue("lAdmin").toString())==1){
                                       out.print("<td class='ECampo'>Si<input type='radio' name='lAdmin' value='Si' checked>");
                                       out.print("No<input type='radio' name='lAdmin' value='No'></td>");
                                     }
                                     else{
                                       out.print("<td class='ECampo'>Si<input type='radio' name='lAdmin' value='Si'>");
                                       out.print("No<input type='radio' name='lAdmin' value='No' checked></td>");
                                     }

                                     out.println("</tr>");


                                     
                                   }
                                   else{

// V E R   D A T O S

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Clave:</td>");
                                       out.print("<td class='ECampo'>" + bs.getFieldValue("iCveAyuda", "") + "</td>");
                                       out.print("<input type='hidden' name='iCveAyuda' value='" + bs.getFieldValue("iCveAyuda", "&nbsp;") + "'>");
                                       out.print("<td class='EEtiqueta'>Vigente:</td>");
                                       if(Integer.parseInt(""+bs.getFieldValue("lVigente"))==0)
                                         out.print("<td class='ECampo'>NO &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
                                       else
                                         out.print("<td class='ECampo'>SI &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Nombre:</td>");
                                       out.print("<td colspan='3' class='ECampo'>" + bs.getFieldValue("cDsAyuda", "&nbsp;") + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Descripci&oacute;n:</td>");
                                       out.print("<td colspan='3' class='ECampo'>" + bs.getFieldValue("cDscDescripcion", "&nbsp;") + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>URL:</td>");
                                       out.print("<td colspan='3' class='ECampo'>" + bs.getFieldValue("cUrl", "&nbsp;") + "</td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                       out.print("<td colspan='4' class='ETablaST'>Categor&iacute;a</td>");
                                     out.print("</tr>");


                                     out.println(vEti.Texto("EEtiqueta","Manual:"));
                                     String cDisable4 = "";
                                     String cChecked4 = "";
                                        cDisable4 = "disabled";
                                     if (bs.getFieldValue("lManual","0").toString().compareToIgnoreCase("0")!=0)
                                         cChecked4 = "checked";
                                     out.println("<td>");
                                     out.println("<input type=\"checkbox\" name=\"lCPersonal\" value=\"1\" " + cDisable4 + " " +  cChecked4+ ">");
                                     out.println("</td>");
                                     out.println(vEti.Texto("EEtiqueta","Guia:"));
                                     String cDisable5 = "";
                                     String cChecked5 = "";
                                        cDisable5 = "disabled";
                                     if (bs.getFieldValue("lGuia","0").toString().compareToIgnoreCase("0")!=0)
                                         cChecked5 = "checked";
                                     out.println("<td>");
                                     out.println("<input type=\"checkbox\" name=\"lGuia\" value=\"1\" " + cDisable5 + " " +  cChecked5+ ">");
                                     out.println("</td>");
                                     out.println("</tr>");
                                     
                                     
                                     out.println("<tr>");
                                     out.println(vEti.Texto("EEtiqueta","Software:"));
                                     String cDisable6 = "";
                                     String cChecked6 = "";
                                        cDisable6 = "disabled";
                                     if (bs.getFieldValue("lSoftware","0").toString().compareToIgnoreCase("0")!=0)
                                         cChecked6 = "checked";
                                     out.println("<td>");
                                     out.println("<input type=\"checkbox\" name=\"lSoftware\" value=\"1\" " + cDisable6 + " " +  cChecked6+ ">");
                                     out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
                                     out.print("<td class='EEtiqueta'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Solo disponible para el Administrador:</td>");
                                     if(Integer.parseInt(""+bs.getFieldValue("lAdmin"))==0)
                                       out.print("<td class='ECampo'>NO</td>");
                                     else
                                       out.print("<td class='ECampo'>SI</td>");

                                     out.println("</tr>");
                                   }
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='4'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                   out.print("</tr>");
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
