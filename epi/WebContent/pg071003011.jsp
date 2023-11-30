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
  pg071003011CFG  clsConfig = new pg071003011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg071003011.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071003011.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Clave|Nombre|";    // modificar
  String cCveOrdenar  = "iCveUniMed|cDscUniMed|";  // modificar
  String cDscFiltrar  = "Clave|Nombre|";    // modificar
  String cCveFiltrar  = "iCveUniMed|cDscUniMed|";  // modificar
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
       cClave  = ""+bs.getFieldValue("iCveUniMed", "");
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
                              <td colspan="4" class="ETablaT">Informaci&oacute;n de la Unidad M&eacute;dica
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
                                   out.print("<td colspan='3'><input type='text' name='cDscUniMed' size=40 maxLength=30 OnBlur='fMayus(this);'></td>");
                                 out.println("</tr>");

                                 out.print("<tr>");
                                   out.print("<td colspan='4' class='ETablaST'>Ubicación</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Calle:</td>");
                                   out.print("<td colspan='3'><input type='text' name='cCalle' size=40 maxLength=50 OnBlur='fMayus(this);'></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Colonia:</td>");
                                   out.print("<td><input type='text' name='cColonia' size=40 maxLength=30 OnBlur='fMayus(this);'></td>");
                                   out.print("<td class='EEtiqueta'>Ciudad:</td>");
                                   out.print("<td><input type='text' name='cCiudad' size=40 maxLength=50 OnBlur='fMayus(this);'></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Pais:</td>");
                                   out.print("<td>");
                                   out.print("<select name=\"iCvePais\" size=\"1\" OnChange=\"llenaSLT(1,document.forms[0].iCvePais.value,'','',document.forms[0].iCveEstado);\">");
                                   vcPais = dPais.FindByAll(" order by cNombre ");
                                   if (vcPais.size() > 0){
                                     out.print("<option value = 0>Seleccione...</option>");
                                     for (int i = 0; i < vcPais.size(); i++){
                                       vPais = (TVPais)vcPais.get(i);
                                         out.print("<option value = " + vPais.getICvePais() + ">" + vPais.getCNombre() + "</option>");
                                     }
                                   }
                                   else{
                                     out.print("<option value = 0>Datos no disponibles...</option>");
                                   }
                                   out.print("</select>");
                                   out.print("</td>");

                                   out.print("<td class='EEtiqueta'>EDO (Estado):</td>");
                                   out.print("<td>");
                                   out.print("<select name=\"iCveEstado\" size=\"1\" OnChange=\"llenaSLT(2,document.forms[0].iCvePais.value,document.forms[0].iCveEstado.value,'',document.forms[0].iCveMunicipio);\">");
                                   if(request.getParameter("iCvePais")!=null){
                                     if(Integer.parseInt(request.getParameter("iCvePais").toString())<1)
                                       out.print("<option selected>Datos no disponibles...</option>");
                                   }
                                   else
                                       out.print("<option selected>Datos no disponibles...</option>");
                                   out.print("</select></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Municipio o Delegación:</td>");
                                   out.print("<td><select name='iCveMunicipio'>");
                                   if(request.getParameter("iCveEstado")!=null){
                                     if(Integer.parseInt(request.getParameter("iCveEstado").toString())<1)
                                       out.print("<option selected>Datos no disponibles...</option>");
                                   }
                                   else
                                       out.print("<option selected>Datos no disponibles...</option>");
                                   out.print("</select></td>");

                                   out.print("<td class='EEtiqueta'>C.P.:</td>");
                                   out.print("<td><input type='text' name='iCP' size=10 maxLength=5></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Teléfono:</td>");
                                   out.print("<td><input type='text' name='cTel' size=40 maxLength=20></td>");
                                   out.print("<td class='EEtiqueta'>Correo Electrónico:</td>");
                                   out.print("<td><input type='text' name='cCorreoE' size=40 maxLength=100></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td colspan='4' class='ETablaST'>Configuración</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Jefe de la Unidad Médica:</td>");
                                   out.println("<td colspan='3'><select name='iCveUsuResp'>");
                                       out.print("<option value='0'>Datos no disponibles...</option>");
                                   out.println("</select></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>¿Cuenta con CIS Telefónico?:</td>");
                                   out.print("<td class='ECampo'>Si<input type='radio' name='lCis' value='Si' checked></td>");
                                   out.print("<td class='ECampo' colspan='2' >No<input type='radio' name='lCis' value='No'></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>¿Utiliza el Sistema de Ingresos DGPOP?:</td>");
                                   out.print("<td class='ECampo'>Si<input type='radio' name='lPago' value='Si' checked></td>");
                                   out.print("<td colspan='2' class='ECampo'>No<input type='radio' name='lPago' value='No'></td>");
                                 out.print("</tr>");
                                 out.print("<tr>");
                                 out.print(vEti.EtiCampoCS("EEtiqueta","Unidad Administrativa de Ingresos:","ETabla","text",8,5,3,"iCveUddAdmiva","0",0,"","fMayus(this);",true,true,true,request));
                                 out.print("</tr>");
                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>¿Es una Unidad Médica Privada?:</td>");
                                   out.print("<td class='ECampo'>Si<input type='radio' name='lPrivada' value='Si' checked></td>");
                                   out.print("<td colspan='2' class='ECampo'>No<input type='radio' name='lPrivada' value='No'></td>");
                                 out.print("</tr>");

                                                               }
                               else{
                                 if (bs != null){

// M O D I F I C A R

                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Clave:</td>");
                                       out.print("<td class='ECampo'>" + bs.getFieldValue("iCveUniMed", "&nbsp;") + "</td>");
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
                                       out.print("<td colspan='3'><input type='text' name='cDscUniMed' size=40 maxLength=30 OnBlur='fMayus(this);' value='");
                                       out.print(""+bs.getFieldValue("cDscUniMed"));
                                       out.print("'></td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td colspan='4' class='ETablaST'>Ubicación</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Calle:</td>");
                                       out.print("<td colspan='3'><input type='text' name='cCalle' size=40 maxLength=50 OnBlur='fMayus(this);' value='");
                                       out.print(""+bs.getFieldValue("cCalle", "&nbsp;"));
                                       out.print("'></td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Colonia:</td>");
                                       out.print("<td><input type='text' name='cColonia' size=40 maxLength=30 OnBlur='fMayus(this);' value='");
                                       out.print(""+bs.getFieldValue("cColonia", "&nbsp;"));
                                       out.print("'></td>");
                                       out.print("<td class='EEtiqueta'>Ciudad:</td>");
                                       out.print("<td><input type='text' name='cCiudad' size=40 maxLength=50 OnBlur='fMayus(this);' value='");
                                       if(bs.getFieldValue("cCiudad").toString().compareTo("null")==0)
                                         out.print("&nbsp;");
                                       else
                                         out.print(""+bs.getFieldValue("cCiudad", "&nbsp;"));
                                       out.print("'></td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Pais:</td>");
                                       out.print("<td>");
                                       out.print("<select name=\"iCvePais\" size=\"1\" OnChange=\"llenaSLT(1,document.forms[0].iCvePais.value,'','',document.forms[0].iCveEstado);\">");
                                       vcPais = dPais.FindByAll();
                                       if (vcPais.size() > 0){
                                         out.print("<option value = 0>Seleccione...</option>");
                                         for (int i = 0; i < vcPais.size(); i++){
                                           vPais = (TVPais)vcPais.get(i);
                                             if(Integer.parseInt(bs.getFieldValue("iCvePais").toString()) == Integer.parseInt(vPais.getICvePais()+""))
                                               out.print("<option selected value = " + vPais.getICvePais() + ">" + vPais.getCNombre() + "</option>");
                                             else
                                               out.print("<option value = " + vPais.getICvePais() + ">" + vPais.getCNombre() + "</option>");
                                         }
                                       }
                                       else{
                                         out.print("<option value = 0>Datos no disponibles...</option>");
                                       }
                                       out.print("</select>");
                                       out.print("</td>");

                                       out.print("<td class='EEtiqueta'>EDO (Estado):</td>");
                                       out.print("<td>");
                                       out.print("<select name=\"iCveEstado\" size=\"1\" OnChange=\"llenaSLT(2,document.forms[0].iCvePais.value,document.forms[0].iCveEstado.value,'',document.forms[0].iCveMunicipio);\">");
                                       vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais"));
                                       if (vcEntidadFed.size() > 0){
                                         out.print("<option value = 0>Seleccione...</option>");
                                         for (int i = 0; i < vcEntidadFed.size(); i++){
                                           vEntidadFed = (TVEntidadFed)vcEntidadFed.get(i);
                                             if(Integer.parseInt(bs.getFieldValue("iCveEstado").toString()) == Integer.parseInt(vEntidadFed.getICveEntidadFed()+""))
                                               out.print("<option selected value = " + vEntidadFed.getICveEntidadFed() + ">" + vEntidadFed.getCNombre() + "</option>");
                                             else
                                               out.print("<option value = " + vEntidadFed.getICveEntidadFed() + ">" + vEntidadFed.getCNombre() + "</option>");
                                         }
                                       }
                                       else{
                                         out.print("<option value = 0>Datos no disponibles...</option>");
                                       }
                                       out.print("</select>");
                                       out.print("</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Municipio o Delegación:</td>");
                                       out.print("<td>");
                                       out.print("<select name=\"iCveMunicipio\" size=\"1\">");
                                       vcMunicipio = dMunicipio.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais") + " and iCveEntidadFed = " + bs.getFieldValue("iCveEstado"));
                                       if (vcMunicipio.size() > 0){
                                         out.print("<option value = 0>Seleccione...</option>");
                                         for (int i = 0; i < vcMunicipio.size(); i++){
                                           vMunicipio = (TVMunicipio)vcMunicipio.get(i);
                                             if(Integer.parseInt(bs.getFieldValue("iCveMunicipio").toString()) == Integer.parseInt(vMunicipio.getICveMunicipio()+""))
                                               out.print("<option selected value = " + vMunicipio.getICveMunicipio() + ">" + vMunicipio.getCNombre() + "</option>");
                                             else
                                               out.print("<option value = " + vMunicipio.getICveMunicipio() + ">" + vMunicipio.getCNombre() + "</option>");
                                         }
                                       }
                                       else{
                                         out.print("<option value = 0>Datos no disponibles...</option>");
                                       }
                                       out.print("</select>");
                                       out.print("</td>");
                                       out.print("<td class='EEtiqueta'>C.P.:</td>");
                                       out.print("<td><input type='text' name='iCP' size=10 maxLength=5 value='");
                                       out.print(""+bs.getFieldValue("iCP", "&nbsp;"));
                                       out.print("'></td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Teléfono:</td>");
                                       out.print("<td><input type='text' name='cTel' size=40 maxLength=20 value='");
                                       if(bs.getFieldValue("cCiudad").toString().compareTo("null")==0)
                                         out.print("&nbsp;");
                                       else
                                         out.print(""+bs.getFieldValue("cTel", "&nbsp;"));
                                       out.print("'></td>");
                                       out.print("<td class='EEtiqueta'>Correo Electrónico:</td>");
                                       out.print("<td><input type='text' name='cCorreoE' size=40 maxLength=100 value='");
                                       out.print(""+bs.getFieldValue("cCorreoE", "&nbsp;"));
                                       out.print("'></td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td colspan='4' class='ETablaST'>Configuración</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Jefe de la Unidad Médica:</td>");
                                       vcGRLUMUsuario = dGRLUMUsuario.FindByDistinct(" where iCveUniMed = " + request.getParameter("hdCampoClave") + " order by SEGUsuario.cNombre ");
                                       out.println("<td colspan='3'><select name='iCveUsuResp'>");
                                       if(vcGRLUMUsuario.size()<1)
                                         out.print("<option value='0'>Datos no disponibles...</option>");

                                       for(int i=0; i<vcGRLUMUsuario.size(); i++){
                                         vGRLUMUsuario = (TVGRLUMUsuario) vcGRLUMUsuario.get(i);
                                         if((vGRLUMUsuario.getICveUsuario() == Integer.parseInt(bs.getFieldValue("iCveUsuResp").toString())))
                                           out.print("<option value='" + vGRLUMUsuario.getICveUsuario() + "' selected>" + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + "</option>");
                                         else if((vGRLUMUsuario.getICveUsuario() == Integer.parseInt(request.getParameter("hdCampoClave"))))
                                           out.print("<option value='" + vGRLUMUsuario.getICveUsuario() + "' selected>" + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + "</option>");
                                         else
                                           out.print("<option value='" + vGRLUMUsuario.getICveUsuario() + "'>" + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + "</option>");
                                       }
                                       out.println("</select></td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>¿Cuenta con CIS Telefónico?:</td>");
                                       if(Integer.parseInt(bs.getFieldValue("lCis").toString())==1){
                                         out.print("<td class='ECampo'>Si<input type='radio' name='lCis' value='Si' checked></td>");
                                         out.print("<td colspan='2'  class='ECampo'>No<input type='radio' name='lCis' value='No'></td>");
                                       }
                                       else{
                                         out.print("<td class='ECampo'>Si<input type='radio' name='lCis' value='Si'></td>");
                                         out.print("<td colspan='2' class='ECampo'>No<input type='radio' name='lCis' value='No' checked></td>");
                                       }
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>¿Utiliza el Sistema de Ingresos DGPOP?:</td>");
                                       if(Integer.parseInt(bs.getFieldValue("lPago").toString())==1){
                                         out.print("<td class='ECampo'>Si<input type='radio' name='lPago' value='Si' checked></td>");
                                         out.print("<td colspan='2' class='ECampo'>No<input type='radio' name='lPago' value='No'></td>");
                                       }
                                       else{
                                         out.print("<td class='ECampo'>Si<input type='radio' name='lPago' value='Si'></td>");
                                         out.print("<td colspan='2' class='ECampo'>No<input type='radio' name='lPago' value='No' checked></td>");
                                       }
                                     out.print("</tr>");
                                 out.print("<tr>");
                                 out.print(vEti.EtiCampoCS("EEtiqueta","Unidad Administrativa de Ingresos:","ETabla","text",8,5,3,"iCveUddAdmiva",bs.getFieldValue("iCveUddAdmiva","0").toString(),0,"","fMayus(this);",true,true,true,request));
                                 out.print("</tr>");
 
                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>¿Es una Unidad Médica Privada?:</td>");
                                       if(Integer.parseInt(bs.getFieldValue("lPrivada").toString())==1){
                                         out.print("<td class='ECampo'>Si<input type='radio' name='lPrivada' value='Si' checked></td>");
                                         out.print("<td colspan='2' class='ECampo'>No<input type='radio' name='lPrivada' value='No'></td>");
                                       }
                                       else{
                                         out.print("<td class='ECampo'>Si<input type='radio' name='lPrivada' value='Si'></td>");
                                         out.print("<td colspan='2' class='ECampo'>No<input type='radio' name='lPrivada' value='No' checked></td>");
                                       }
                                     out.print("</tr>");

                                     
                                   }
                                   else{

// V E R   D A T O S

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Clave:</td>");
                                       out.print("<td class='ECampo'>" + bs.getFieldValue("iCveUniMed", "") + "</td>");
                                       out.print("<input type='hidden' name='iCveUniMed' value='" + bs.getFieldValue("iCveUniMed", "&nbsp;") + "'>");
                                       out.print("<td class='EEtiqueta'>Vigente:</td>");
                                       if(Integer.parseInt(""+bs.getFieldValue("lVigente"))==0)
                                         out.print("<td class='ECampo'>NO</td>");
                                       else
                                         out.print("<td class='ECampo'>SI</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Nombre:</td>");
                                       out.print("<td colspan='3' class='ECampo'>" + bs.getFieldValue("cDscUniMed", "&nbsp;") + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td colspan='4' class='ETablaST'>Ubicación</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Calle:</td>");
                                       out.print("<td colspan='3' class='ECampo'>" + bs.getFieldValue("cCalle", "&nbsp;") + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Colonia:</td>");
                                       out.print("<td class='ECampo'>" + bs.getFieldValue("cColonia", "&nbsp;") + "</td>");
                                       out.print("<td class='EEtiqueta'>Ciudad:</td>");
                                       if(bs.getFieldValue("cCiudad", "&nbsp;").toString().compareTo("null")==0)
                                         out.print("<td class='ECampo'>&nbsp;</td>");
                                       else
                                         out.print("<td class='ECampo'>" + bs.getFieldValue("cCiudad", "&nbsp;") + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       vcPais = dPais.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais", "&nbsp;"));
                                       if (vcPais.size() > 0)
                                         for (int i = 0; i < vcPais.size(); i++)
                                           vPais = (TVPais)vcPais.get(i);
                                       out.print("<td class='EEtiqueta'>Pais:</td>");
                                       out.print("<td class='ECampo'>" + vPais.getCNombre() + "</td>");
                                       vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais", "&nbsp;") + " and iCveEntidadFed = " + bs.getFieldValue("iCveEstado", "&nbsp;"));
                                       if (vcEntidadFed.size() > 0)
                                         for (int i = 0; i < vcEntidadFed.size(); i++)
                                           vEntidadFed = (TVEntidadFed)vcEntidadFed.get(i);
                                       out.print("<td class='EEtiqueta'>EDO (Estado):</td>");
                                       out.print("<td class='ECampo'>" + vEntidadFed.getCNombre() + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       vcMunicipio = dMunicipio.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais", "&nbsp;") + " and iCveEntidadFed = " + bs.getFieldValue("iCveEstado", "&nbsp;") + " and iCveMunicipio = " + bs.getFieldValue("iCveMunicipio", "&nbsp;"));
                                       if (vcMunicipio.size() > 0)
                                         for (int i = 0; i < vcMunicipio.size(); i++)
                                           vMunicipio = (TVMunicipio)vcMunicipio.get(i);
                                       out.print("<td class='EEtiqueta'>MUN (Municipio):</td>");
                                       out.print("<td class='ECampo'>" + vMunicipio.getCNombre() + "</td>");
                                       out.print("<td class='EEtiqueta'>C.P.:</td>");
                                       out.print("<td class='ECampo'>" + bs.getFieldValue("iCP", "&nbsp;") + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Teléfono:</td>");
                                       if(bs.getFieldValue("cTel", "&nbsp;").toString().compareTo("null")==0)
                                         out.print("<td class='ECampo'>&nbsp;</td>");
                                       else
                                         out.print("<td class='ECampo'>" + bs.getFieldValue("cTel", "&nbsp;") + "</td>");
                                       out.print("<td class='EEtiqueta'>Correo Electrónico:</td>");
                                       out.print("<td class='ECampo'>" + bs.getFieldValue("cCorreoE", "&nbsp;") + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td colspan='4' class='ETablaST'>Configuración</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Jefe de la Unidad Médica:</td>");
                                       vcSEGUsuario = dSEGUsuario.FindByAll(" where iCveUsuario = " + bs.getFieldValue("iCveUsuResp"));
                                       if (vcSEGUsuario.size() > 0)
                                         for (int i = 0; i < vcSEGUsuario.size(); i++)
                                           vSEGUsuario = (TVSEGUsuario)vcSEGUsuario.get(i);
                                       if(vcSEGUsuario.size()<1)
                                         out.print("&nbsp;");
                                       else
                                         out.print("<td colspan='3' class='ECampo'>" + vSEGUsuario.getCNombre() + " " + vSEGUsuario.getCApPaterno() + " " + vSEGUsuario.getCApMaterno() + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>¿Cuenta con CIS Telefónico?:</td>");
                                       if(Integer.parseInt(""+bs.getFieldValue("lCis"))==0)
                                         out.print("<td colspan='3' class='ECampo'>NO</td>");
                                       else
                                         out.print("<td colspan='3' class='ECampo'>SI</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>¿Utiliza el Sistema de Ingresos DGPOP?:</td>");
                                       if(Integer.parseInt(""+bs.getFieldValue("lPago"))==0)
                                         out.print("<td colspan='3' class='ECampo'>NO</td>");
                                       else
                                         out.print("<td colspan='3' class='ECampo'>SI</td>");
                                     out.print("</tr>");
                                 out.print("<tr>");
                                 out.print(vEti.EtiCampoCS("EEtiqueta","Unidad Administrativa de Ingresos:","ETabla","text",8,5,3,"iCveUddAdmiva",bs.getFieldValue("iCveUddAdmiva","0").toString(),0,"","fMayus(this);",true,true,false,request));
                                 out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>¿Es una Unidad Médica Privada?:</td>");
                                       if(Integer.parseInt(""+bs.getFieldValue("lPrivada"))==0)
                                         out.print("<td colspan='3' class='ECampo'>NO</td>");
                                       else
                                         out.print("<td colspan='3' class='ECampo'>SI</td>");
                                     out.print("</tr>");

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
