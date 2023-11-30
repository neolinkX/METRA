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
<%@ page import="java.text.*" %>
<html>
<%
  pg070502013CFG  clsConfig = new pg070502013CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070502013.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502013.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";
  String cPais    = "";
  String cEstado    = "";
  String cMunicipio    = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Domicilio|";    // modificar
  String cCveOrdenar  = "iCveDomicilio|";  // modificar
  String cDscFiltrar  = "Domicilio|";    // modificar
  String cCveFiltrar  = "iCveDomicilio|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
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

  TDPais dPais = new TDPais();
  TVPais vPais = new TVPais();
  Vector vcPais = new Vector();

  TDEntidadFed dEntidadFed = new TDEntidadFed();
  TVEntidadFed vEntidadFed = new TVEntidadFed();
  Vector vcEntidadFed = new Vector();

  TDMunicipio dMunicipio = new TDMunicipio();
  TVMunicipio vMunicipio = new TVMunicipio();
  Vector vcMunicipio = new Vector();

  TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
  TVGRLEmpresas vGRLEmpresas = new TVGRLEmpresas();
  Vector vcGRLEmpresas = new Vector();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070502013.js)

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
       cClave  = ""+bs.getFieldValue("iCveDomicilio", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdEmpresa" value="<%out.print(request.getParameter("iCveEmpresa"));%>">
  <input type="hidden" name="iCveMdoTrans" value="<%if(request.getParameter("iCveMdoTrans")!=null) out.print(request.getParameter("iCveMdoTrans"));%>">
  <input type="hidden" name="iCveUniMed" value="<%if(request.getParameter("iCveUniMed")!=null) out.print(request.getParameter("iCveUniMed"));%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                              <% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                                   DecimalFormat dfNumber = new DecimalFormat("00000");
                               %>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <td colspan="4" class="ETablaT">Información del Transportista
                              </td>
                              <%
                              out.print("<tr><td class='ETablaTR'>Transportista:</td><td class='ETabla' colspan='3'>");
                                if(request.getParameter("hdBoton").compareTo("Modificar")==0 || request.getParameter("hdBoton").compareTo("Nuevo")==0)
                                  out.print("<select name=\"iCveEmpresa\" size=\"1\" disabled>");
                                else
                                  out.print("<select name=\"iCveEmpresa\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                                  vcGRLEmpresas = dGRLEmpresas.FindByAll(" order by iCveEmpresa ");
                                  if (vcGRLEmpresas.size() > 0){
                                    if(request.getParameter("iCveEmpresa")!=null && Integer.parseInt(request.getParameter("iCveEmpresa").toString())<1)
                                      out.print("<option value = 0>Seleccione...</option>");
                                    for (int i = 0; i < vcGRLEmpresas.size(); i++){
                                      vGRLEmpresas = (TVGRLEmpresas)vcGRLEmpresas.get(i);
                                      if (request.getParameter("iCveEmpresa")!=null && request.getParameter("iCveEmpresa").compareToIgnoreCase(new Integer(vGRLEmpresas.getICveEmpresa()).toString())==0)
                                        out.print("<option value = " + vGRLEmpresas.getICveEmpresa() + " selected>" + vGRLEmpresas.getCDenominacion() + "</option>");
                                      else if (request.getParameter("hdEmpresa")!=null && request.getParameter("hdEmpresa").compareToIgnoreCase(new Integer(vGRLEmpresas.getICveEmpresa()).toString())==0)
                                        out.print("<option value = " + vGRLEmpresas.getICveEmpresa() + " selected>" + vGRLEmpresas.getCDenominacion() + "</option>");
                                      else
                                        out.print("<option value = " + vGRLEmpresas.getICveEmpresa() + ">" + vGRLEmpresas.getCDenominacion() + "</option>");
                                    }
                                  }
                                  else{
                                    out.print("<option value = 0>Datos no disponibles...</option>");
                                  }
                               out.print("</select></td></tr>");

                               if((request.getParameter("iCveEmpresa")!=null && Integer.parseInt(request.getParameter("iCveEmpresa").toString())>0) || request.getParameter("hdEmpresa")!=null){
                                 if(request.getParameter("iCveEmpresa")!=null)
                                   vcGRLEmpresas = dGRLEmpresas.FindByAll2(" where iCveEmpresa = " + request.getParameter("iCveEmpresa") + " order by iCveEmpresa ");
                                 else if(request.getParameter("hdEmpresa")!=null)
                                   vcGRLEmpresas = dGRLEmpresas.FindByAll2(" where iCveEmpresa = " + request.getParameter("hdEmpresa") + " order by iCveEmpresa ");
                                 if (vcGRLEmpresas.size() > 0){
                                   for (int i = 0; i < vcGRLEmpresas.size(); i++)
                                     vGRLEmpresas = (TVGRLEmpresas)vcGRLEmpresas.get(i);

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Clave:</td>");
                                     out.print("<td class='ECampo'>" + vGRLEmpresas.getICveEmpresa() + "</td>");
                                     out.print("<td class='EEtiqueta'>Id. DGPMPT:</td>");
                                     out.print("<td class='ECampo'>" + vGRLEmpresas.getcIDDGPMPT() + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>ID. Mdo Transporte:</td>");
                                     out.print("<td class='ECampo'>" + vGRLEmpresas.getICveMdoTrans() + "</td>");
                                     out.print("<td class='EEtiqueta'>Modo de Transporte:</td>");
                                     out.print("<td class='ECampo'>" + vGRLEmpresas.getCDscMdoTrans() + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='ETablaT' colspan='4'>Domicilio Fiscal</td>");
                                   out.print("</tr>");
                                 }
                               }
// N U E V O

                               if (lNuevo){ // Modificar de acuerdo al catálogo específico

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Calle:</td>");
                                   out.print("<td colspan='3'><input type='text' name='cCalle' size=50 maxLength=50 OnBlur='fMayus(this);'></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Colonia:</td>");
                                   out.print("<td><input type='text' name='cColonia' size=50 maxLength=30 OnBlur='fMayus(this);'></td>");
                                   out.print("<td class='EEtiqueta'>C.P.:</td>");
                                   out.print("<td><input type='text' name='iCP' size=10 maxLength=5></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Ciudad:</td>");
                                   out.print("<td><input type='text' name='cCiudad' size=50 maxLength=100 OnBlur='fMayus(this);'></td>");
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
                                 out.print("</tr>");

                                 out.print("<tr>");
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

                                   out.print("<td class='EEtiqueta'>Delegación o MUN (Municipio):</td>");
                                   out.print("<td><select name='iCveMunicipio'>");
                                   if(request.getParameter("iCveEstado")!=null){
                                     if(Integer.parseInt(request.getParameter("iCveEstado").toString())<1)
                                       out.print("<option selected>Datos no disponibles...</option>");
                                   }
                                   else
                                       out.print("<option selected>Datos no disponibles...</option>");
                                   out.print("</select></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Teléfono:</td>");
                                   out.print("<td><input type='text' name='cTel' size=20 maxLength=20></td>");
                                   out.print("<td class='EEtiqueta'>Fax:</td>");
                                   out.print("<td><input type='text' name='cFax' size=20 maxLength=20></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Correo Electrónico:</td>");
                                   out.print("<td colspan='3'><input type='text' name='cCorreoElec' size=50 maxLength=50></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta'>Domicilio Fiscal Actual:</td>");
                                   out.print("<td colspan='3'><input type='checkbox' name='chklActivo'></td>");
                                 out.print("</tr>");

                               }
                               else{
                                 if (bs != null){

// M O D I F I C A R

                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Calle:</td>");
                                       out.print("<td colspan='3'><input type='text' name='cCalle' size=50 maxLength=50 OnBlur='fMayus(this);' value='");
                                       out.print(""+bs.getFieldValue("cCalle"));
                                       out.print("'></td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Colonia:</td>");
                                       out.print("<td><input type='text' name='cColonia' size=50 maxLength=30 OnBlur='fMayus(this);' value='");
                                       out.print(""+bs.getFieldValue("cColonia"));
                                       out.print("'></td>");
                                       out.print("<td class='EEtiqueta'>C.P.:</td>");
                                       out.print("<td><input type='text' name='iCP' size=10 maxLength=5 value='");
                                       //out.print(""+bs.getFieldValue("iCP"));
                                       out.print(""+dfNumber.format(new Double(bs.getFieldValue("iCP").toString())));
                                       out.print("'></td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Ciudad:</td>");
                                       out.print("<td><input type='text' name='cCiudad' size=50 maxLength=100 OnBlur='fMayus(this);' value='");
                                       out.print(""+bs.getFieldValue("cCiudad"));
                                       out.print("'></td>");
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
                                     out.print("</tr>");

                                     out.print("<tr>");
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

                                       out.print("<td class='EEtiqueta'>Delegación o MUN (Municipio):</td>");
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
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Teléfono:</td>");
                                       out.print("<td><input type='text' name='cTel' size=20 maxLength=20 value='");
                                       out.print(""+bs.getFieldValue("cTel"));
                                       out.print("'></td>");
                                       out.print("<td class='EEtiqueta'>Fax:</td>");
                                       out.print("<td><input type='text' name='cFax' size=20 maxLength=20 value='");
                                       out.print(""+bs.getFieldValue("cFax"));
                                       out.print("'></td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Correo Electrónico:</td>");
                                       out.print("<td colspan='3'><input type='text' name='cCorreoElec' size=50 maxLength=50 value='");
                                       out.print(""+bs.getFieldValue("cCorreoElec"));
                                       out.print("'></td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Domicilio Fiscal Actual:</td>");
                                       if(Integer.parseInt(bs.getFieldValue("lActivo").toString())==0)
                                         out.print("<td colspan='3'><input type='checkbox' name='chklActivo'></td>");
                                       else
                                         out.print("<td colspan='3'><input type='checkbox' name='chklActivo' checked></td>");
                                     out.print("</tr>");
                                   }
                                   else{

// V E R   D A T O S

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Calle:</td>");
                                       out.print("<td class='ECampo' colspan='3'>" + bs.getFieldValue("cCalle", "&nsbp;") + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Colonia:</td>");
                                       out.print("<td class='ECampo'>" + bs.getFieldValue("cColonia", "&nbsp;") + "</td>");
                                       out.print("<td class='EEtiqueta'>C.P.:</td>");
                                       out.print("<td class='ECampo'>" + dfNumber.format(new Double(bs.getFieldValue("iCP").toString()))  + "</td>");
                                        //bs.getFieldValue("iCP", "&nbsp;") + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Ciudad:</td>");
                                       out.print("<td class='ECampo'>" + bs.getFieldValue("cCiudad", "&nbsp;") + "</td>");
                                       vcPais = dPais.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais", "&nbsp;"));
                                       if (vcPais.size() > 0)
                                         for (int i = 0; i < vcPais.size(); i++)
                                           vPais = (TVPais)vcPais.get(i);
                                       out.print("<td class='EEtiqueta'>Pais:</td>");
                                       out.print("<td class='ECampo'>" + vPais.getCNombre() + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais", "&nbsp;") + " and iCveEntidadFed = " + bs.getFieldValue("iCveEstado", "&nbsp;"));
                                       if (vcEntidadFed.size() > 0)
                                         for (int i = 0; i < vcEntidadFed.size(); i++)
                                           vEntidadFed = (TVEntidadFed)vcEntidadFed.get(i);
                                       out.print("<td class='EEtiqueta'>EDO (Estado):</td>");
                                       out.print("<td class='ECampo'>" + vEntidadFed.getCNombre() + "</td>");
                                       vcMunicipio = dMunicipio.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais", "&nbsp;") + " and iCveEntidadFed = " + bs.getFieldValue("iCveEstado", "&nbsp;") + " and iCveMunicipio = " + bs.getFieldValue("iCveMunicipio", "&nbsp;"));
                                       if (vcMunicipio.size() > 0)
                                         for (int i = 0; i < vcMunicipio.size(); i++)
                                           vMunicipio = (TVMunicipio)vcMunicipio.get(i);
                                       out.print("<td class='EEtiqueta'>Delegación o MUN (Municipio):</td>");
                                       out.print("<td class='ECampo'>" + vMunicipio.getCNombre() + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Teléfono:</td>");
                                       out.print("<td class='ECampo'>" + bs.getFieldValue("cTel", "&nbsp;") + "</td>");
                                       out.print("<td class='EEtiqueta'>Fax:</td>");
                                       out.print("<td class='ECampo'>" + bs.getFieldValue("cFax", "&nbsp;") + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Correo Electrónico:</td>");
                                       out.print("<td class='ECampo' colspan='3'>" + bs.getFieldValue("cCorreoElec", "&nbsp;") + "</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Domicilio Fiscal Actual:</td>");
                                       if(Integer.parseInt(bs.getFieldValue("lActivo", "&nbsp;").toString())==0)
                                         out.print("<td colspan='3'><input type='checkbox' name='chklActivo' disabled></td>");
                                       else
                                         out.print("<td colspan='3'><input type='checkbox' name='chklActivo' checked disabled></td>");
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