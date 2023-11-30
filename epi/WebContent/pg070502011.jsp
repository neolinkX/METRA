<%/**
 * Title: Catalogo de las Empresas
 * Description: Catalogo de las Empresas
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070502011CFG
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>

<html>
<%
  pg070502011CFG  clsConfig = new pg070502011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070502011.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502011.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave Transportista|ID DGPMPT|Razón Social|RFC|";    // modificar
  String cCveOrdenar  = "GRLEmpresas.iCveEmpresa|GRLEmpresas.cIDDGPMPT|GRLEmpresas.cDenominacion|GRLEmpresas.cRFC|";  // modificar
  String cDscFiltrar  = "Clave Transportista|ID DGPMPT|Razón Social|RFC|";    // modificar
  String cCveFiltrar  = "GRLEmpresas.iCveEmpresa|GRLEmpresas.cIDDGPMPT|GRLEmpresas.cDenominacion|GRLEmpresas.cRFC|";  // modificar
  String cTipoFiltrar = "7|8|8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "";             // modificar

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
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)

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
       cClave2  = ""+bs.getFieldValue("iCveEmpresa", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     } else {
       cClave2 = request.getParameter("hdCampoClave");
       cPosicion = request.getParameter("hdRowNum");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="iCveEmpresa" value="<%=cClave2 %>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="10" class="ETablaT">Transportistas
                              </td>
                            </tr>
<%
//Conexion con otras paginas
                 if (request.getParameter("SLSUniMed") != null && request.getParameter("SLSUniMed") != "")
                      out.print("<input type=\"hidden\" name=\"SLSUniMed\" value=\"" + request.getParameter("SLSUniMed") + "\">");
                 else
                      out.print("<input type=\"hidden\" name=\"SLSUniMed\" value=\"\">");

                 if (request.getParameter("SLSMdoTransporte") != null && request.getParameter("SLSMdoTransporte") != "")
                      out.print("<input type=\"hidden\" name=\"SLSMdoTransporte\" value=\"" + request.getParameter("SLSMdoTransporte")  + "\">");
                 else
                      out.print("<input type=\"hidden\" name=\"SLSMdoTransporte\" value=\"\">");

                 if (request.getParameter("hdIni") != null && request.getParameter("hdIni") != "")
                      out.print("<input type=\"hidden\" name=\"hdIni\" value=\"" + request.getParameter("hdIni") + "\">");
                 else
                      out.print("<input type=\"hidden\" name=\"hdIni\" value=\"\">");

                 out.print("<input type=\"hidden\" name=\"hdIni2\" value=\"\">");

// Esto ya es de la pagina
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo) {
                                    out.println("<tr>");
                                    out.print("<td colspan='4' class='EETiqueta'><div align='center'>Transportistas del Modo de Transporte ");
                                    out.print(vEti.SelectOneRowSinTD("iCveMdoTrans","",clsConfig.vMdoTrans,"iCveMdoTrans","cDscMdoTrans",request,"0"));
                                    out.print(" controladas por Unidad Médica: ");
//                                    out.print(vEti.SelectOneRowSinTD("ECampo", "", clsConfig.vLabs,"iCveUniMed","cDscUniMed",request,"0"));
                                    TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                    Vector vUnidades = new Vector();
                                    String cvUnidades = "";
                                    int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
                                    vUnidades = vUsuario.getVUniFiltro(iCveProceso);

                                    out.print("<select name=\"iCveUniMed\">");
                                    if (request.getParameter("iCveUniMed") != null){
                                      if (request.getParameter("iCveUniMed") != "")
                                        cvUnidades = request.getParameter("iCveUniMed");
                                    }
                                  if (cvUnidades.compareToIgnoreCase("") == 0 || cvUnidades.compareToIgnoreCase("0") == 0)
                                    out.print("<option selected value=\"" + "0"  + "\">" + "Sin Asignar" + "</option>");
                                  else
                                    out.print("<option value=\"" + "0"  + "\">" + "Sin Asignar" + "</option>");
                                  if (!vUnidades.isEmpty()){
                                    for (int i=0;i<vUnidades.size();i++){
                                      TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                                      VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                                      if (cvUnidades.compareToIgnoreCase(new Integer(VGRLUMUsuario.getICveUniMed()).toString()) == 0)
                                         out.print("<option selected value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                      else
                                         out.print("<option value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                    }
                                  }
                                  out.print("</select>");

                                    out.println("</div></td></tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","ID DGPMPT:","ECampo","text",20,18,"cIDDGPMPT","",0,"","",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","ID Modo Transporte:","ECampo","text",20,18, "iIDMdoTrans","",0, "", "", false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.TextoCS("ETablaT", "Datos Generales:", 4));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta", "Tipo de Persona:"));
                                    out.println("<td class='ECampo'><input type='radio' name='cTpoPersona' value='F' onClick=\"fCambiaPersona('F');\">Física</td>");
                                    out.println("<td class='ECampo'><input type='radio' name='cTpoPersona' value='M' onClick=\"fCambiaPersona('M');\">Moral</td>");
                                    out.println("<td class='ECampo'>&nbsp;</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",15,13,"cRFC","",0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",20,18,"cCURP","",0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",25,25,"cApPaterno","",0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",25,25,"cApMaterno","",0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Nombre:","ECampo","text",100,100, 3,"cNombreRS","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Razón Social:","ECampo","text",50,50, 3,"cDenominacion","",0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha de Registro:","ECampo","text",11,11,"dtRegistro","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.Texto("EEtiqueta", "Grupo:"));
                                    out.print(vEti.SelectOneRow("ECampo","iCveGrupoEmp","",clsConfig.vGpoEmp,"iCveGrupoEmp","cDscBreve",request,"0"));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RPA:","ECampo","text",25,25,"cCveRPA","",0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.Texto("EEtiqueta", "Origen de la Información:"));
                                    out.print(vEti.SelectOneRow("ECampo","iCveOrigenInf","",clsConfig.vOrigenInf,"iCveOrigenInf","cDscOrigenInf",request,"0"));
                                    out.println("</tr>");
                                }
                                else {
                                    if (lCaptura) {
                                       // Modificar
                                       out.println("<tr>");
                                       out.print("<td colspan='4' class='EETiqueta'>Transportistas del Modo de Transporte ");
                                       out.print(vEti.SelectOneRowSinTD("iCveMdoTrans","",clsConfig.vMdoTrans,"iCveMdoTrans","cDscMdoTrans",request,"0"));
                                       out.print(" controladas por Unidad Médica: ");
//                                       out.print(vEti.SelectOneRowSinTD("iCveUniMed", "", clsConfig.vLabs,"iCveUniMed","cDscUniMed",request,"0"));
                                    TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                    Vector vUnidades = new Vector();
                                    String cvUnidades = "";
                                    int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
                                    vUnidades = vUsuario.getVUniFiltro(iCveProceso);

                                    out.print("<select name=\"iCveUniMed\">");
                                    if (request.getParameter("iCveUniMed") != null){
                                      if (request.getParameter("iCveUniMed") != "")
                                        cvUnidades = request.getParameter("iCveUniMed");
                                    }
                                  if (cvUnidades.compareToIgnoreCase("") == 0 || cvUnidades.compareToIgnoreCase("0") == 0)
                                    out.print("<option selected value=\"" + "0"  + "\">" + "Sin Asignar" + "</option>");
                                  else
                                    out.print("<option value=\"" + "0"  + "\">" + "Sin Asignar" + "</option>");
                                  if (!vUnidades.isEmpty()){
                                    for (int i=0;i<vUnidades.size();i++){
                                      TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                                      VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                                      if (cvUnidades.compareToIgnoreCase(new Integer(VGRLUMUsuario.getICveUniMed()).toString()) == 0)
                                         out.print("<option selected value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                      else
                                         out.print("<option value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                    }
                                  }
                                  out.print("</select>");

                                       out.println("</td></tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","ID DGPMPT:","ECampo","text",20,18,"cIDDGPMPT",bs.getFieldValue("cIDDGPMPT","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.EtiCampo("ECampo","ID Modo Transporte:","ECampo","text",20,18, "iIDMdoTrans",bs.getFieldValue("iIDMdoTrans","&nbsp;").toString(),0, "", "", false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.TextoCS("ETablaT", "Datos Generales:", 4));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.Texto("EEtiqueta", "Tipo de Persona:"));
                                       String check = "";
                                       if (bs.getFieldValue("cTpoPersona").equals("F"))
                                          check = " checked";
                                       out.println("<td class='ECampo'><input type='radio' name='cTpoPersona' value='F'" + check + " onClick=\"fCambiaPersona('F');\">Física</td>");
                                       check = "";
                                       if (bs.getFieldValue("cTpoPersona").equals("M"))
                                          check = " checked";
                                       out.println("<td class='ECampo'><input type='radio' name='cTpoPersona' value='M'" + check + " onClick=\"fCambiaPersona('M');\">Moral</td>");
                                       out.println("<td class='ECampo'>&nbsp;</td>");
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",15,13,"cRFC", bs.getFieldValue("cRFC","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",20,18,"cCURP", bs.getFieldValue("cCurp","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",25,25,"cApPaterno",bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",25,25,"cApMaterno",bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampoCS("EEtiqueta","Nombre:","ECampo","text",100,100,3,"cNombreRS",bs.getFieldValue("cNombreRS","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampoCS("EEtiqueta","Razón Social:","ECampo","text",50,50,3,"cDenominacion",bs.getFieldValue("cDenominacion","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       TFechas f = new TFechas();
                                       TVGRLEmpresas tvEm = new TVGRLEmpresas();
                                       String fecha = "&nbsp;";
                                       tvEm = (TVGRLEmpresas) bs.getCurrentBean();
                                       if (tvEm.getDtRegistro() != null && !tvEm.getDtRegistro().equals(""))
                                           fecha = f.getFechaDDMMYYYY(tvEm.getDtRegistro(), "/");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Fecha de Registro:","ECampo","text",11,11,"dtRegistro",fecha,0,"","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.Texto("EEtiqueta", "Grupo:"));
                                       out.print(vEti.SelectOneRow("ECampo","iCveGrupoEmp","",clsConfig.vGpoEmp,"iCveGrupoEmp","cDscBreve",request,"0"));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","RPA:","ECampo","text",25,25,"cCveRPA",bs.getFieldValue("cCveRPA").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.Texto("EEtiqueta", "Origen de la Información:"));
                                       out.print(vEti.SelectOneRow("ECampo","iCveOrigenInf","",clsConfig.vOrigenInf,"iCveOrigenInf","cDscOrigenInf",request,"0"));
                                       out.println("</tr>");

                                    } else
                                        if (bs != null) {
                                    // Solo Mostrar Datos
                                       out.println("<tr>");
                                       out.print("<td colspan='4' class='EETiqueta'>Transportistas del Modo de Transporte ");
                                       out.print(vEti.SelectOneRowSinTD("iCveMdoTrans", "fCambioFiltro();",clsConfig.vMdoTransT,"iCveMdoTrans","cDscMdoTrans",request,"0"));



                                  // Unidades Médicas
                                  out.println(" controladas por Unidad Médica ");
//                                  out.println(vEti.SelectOneRowSinTD("iCveUniMed", "fCambioFiltro();", clsConfig.labs, "iCveUniMed", "cDscUniMed", request, "0"));
                                  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                  Vector vUnidades = new Vector();
                                  String cvUnidades = "";
                                  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
                                  vUnidades = vUsuario.getVUniFiltro(iCveProceso);

                                  out.print("<select name=\"iCveUniMed\" onChange=\"fCambioFiltro();\">");
                                  if (request.getParameter("iCveUniMed") != null){
                                    if (request.getParameter("iCveUniMed") != "")
                                       cvUnidades = request.getParameter("iCveUniMed");
                                  }
                                  if (cvUnidades.compareToIgnoreCase("") == 0 || cvUnidades.compareToIgnoreCase("0") == 0)
                                    out.print("<option selected value=\"" + "0"  + "\">" + "Sin Asignar" + "</option>");
                                  else
                                    out.print("<option value=\"" + "0"  + "\">" + "Sin Asignar" + "</option>");
                                  if (!vUnidades.isEmpty()){
                                    for (int i=0;i<vUnidades.size();i++){
                                      TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                                      VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                                      if (cvUnidades.compareToIgnoreCase(new Integer(VGRLUMUsuario.getICveUniMed()).toString()) == 0)
                                         out.print("<option selected value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                      else
                                         out.print("<option value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                    }
                                  }
                                  out.print("</select>");




                                       out.println("</td></tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampoCS("EEtiqueta","Clave:","ECampo","text",10,4,3,"iCveEmpresa", bs.getFieldValue("iCveEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","ID DGPMPT:","ECampo","text",20,18,"cIDDGPMPT",bs.getFieldValue("cIDDGPMPT","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.EtiCampo("EEtiqueta","ID Modo de Transporte:","ECampo","text",20,18,"iIDMdoTrans",bs.getFieldValue("iIDMdoTrans","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.TextoCS("ETablaT", "Datos Generales:", 4));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.Texto("EEtiqueta", "Tipo de Persona:"));
                                       String check = "";
                                       if (bs.getFieldValue("cTpoPersona").equals("F"))
                                          check = " checked";
                                       out.println("<td class='ECampo'><input type='radio' name='cTpoPersona' value='F'" + check + " disabled>Física</td>");
                                       check = "";
                                       if (bs.getFieldValue("cTpoPersona").equals("M"))
                                          check = " checked";
                                       out.println("<td class='ECampo'><input type='radio' name='cTpoPersona' value='M'" + check + " disabled>Moral</td>");
                                       out.println("<td class='ECampo'>&nbsp;</td>");
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",15,13,"cRFC", bs.getFieldValue("cRFC","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",20,18,"cCURP", bs.getFieldValue("cCurp","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",25,25,"cApPaterno",bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",25,25,"cApMaterno",bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampoCS("EEtiqueta","Nombre:","ECampo","text",100,100,3,"cNombreRS",bs.getFieldValue("cNombreRS","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampoCS("EEtiqueta","Razón Social:","ECampo","text",50,50,3,"cDenominacion",bs.getFieldValue("cDenominacion","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       TFechas f = new TFechas();
                                       TVGRLEmpresas tvEm = new TVGRLEmpresas();
                                       String fecha = "&nbsp;";
                                       tvEm = (TVGRLEmpresas) bs.getCurrentBean();
                                       if (tvEm.getDtRegistro() != null && !tvEm.getDtRegistro().equals(""))
                                           fecha = f.getFechaDDMMYYYY(tvEm.getDtRegistro(), "/");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Fecha de Registro:","ECampo","text",11,11,"dtRegistro",fecha,0,"","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.Texto("EEtiqueta", "Grupo:"));
                                       out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscBreve").toString()));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","RPA:","ECampo","text",25,25,"cCveRPA",bs.getFieldValue("cCveRPA","&nbsp;").toString(),0,"fMayus(this)","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.Texto("EEtiqueta", "Origen de la Información:"));
                                       out.print(vEti.Texto("ECampo",bs.getFieldValue("cDscOrigenInf").toString()));
                                       out.println("</tr>");
                                       out.println("<input type=\"hidden\" name=\"iCveGrupoEmp\" value=\"" + bs.getFieldValue("iCveGrupoEmp") + "\">" );
                                       out.println("<input type=\"hidden\" name=\"iCveOrigenInf\" value=\"" + bs.getFieldValue("iCveOrigenInf") + "\">" );
                                    } else {
                                       out.println("<tr>");
                                       out.print("<td colspan='4' class='EETiqueta'>Transportistas del Modo de Transporte ");
                                       out.print(vEti.SelectOneRowSinTD("iCveMdoTrans", "fCambioFiltro();",clsConfig.vMdoTransT,"iCveMdoTrans","cDscMdoTrans",request,"0"));


                                  // Unidades Médicas
                                  out.println(" controladas por Unidad Médica ");
//                                  out.println(vEti.SelectOneRowSinTD("iCveUniMed", "fCambioFiltro();", clsConfig.labs, "iCveUniMed", "cDscUniMed", request, "0"));
                                  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                  Vector vUnidades = new Vector();
                                  String cvUnidades = "";
                                  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
                                  vUnidades = vUsuario.getVUniFiltro(iCveProceso);

                                  out.print("<select name=\"iCveUniMed\" onChange=\"fCambioFiltro();\">");
                                  if (request.getParameter("iCveUniMed") != null){
                                    if (request.getParameter("iCveUniMed") != "")
                                       cvUnidades = request.getParameter("iCveUniMed");
                                  }
                                  if (cvUnidades.compareToIgnoreCase("") == 0 || cvUnidades.compareToIgnoreCase("0") == 0)
                                    out.print("<option selected value=\"" + "0"  + "\">" + "Sin Asignar" + "</option>");
                                  else
                                    out.print("<option value=\"" + "0"  + "\">" + "Sin Asignar" + "</option>");
                                  if (!vUnidades.isEmpty()){
                                    for (int i=0;i<vUnidades.size();i++){
                                      TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                                      VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                                      if (cvUnidades.compareToIgnoreCase(new Integer(VGRLUMUsuario.getICveUniMed()).toString()) == 0)
                                         out.print("<option selected value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                      else
                                         out.print("<option value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                    }
                                  }
                                  out.print("</select>");



                                       out.println("</td></tr>");
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