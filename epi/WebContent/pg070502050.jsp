<%/**
 * Title:         Concentrado de Empresas
 * Description:   Concentrado de Empresas
 * Copyright:     2004
 * Company:       Microe Personales S.A. de C.V.
 * @author        Marco Antonio Hernández García
 * @version       1.0
 * Clase:         pg070502050
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070502050CFG  clsConfig = new pg070502050CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070502050.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502050.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070502032.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Plantilla|RFC|Razón Social|";    // modificar
  String cCveOrdenar  = "CTRPlantilla.iCvePlantilla|GRLEmpresas.cRFC|GRLEmpresas.cDscEmpresa|";  // modificar
  String cDscFiltrar  = "Plantilla|RFC|Razón Social|";    // modificar
  String cCveFiltrar  = "CTRPlantilla.iCvePlantilla|GRLEmpresas.cRFC|GRLEmpresas.cDscEmpresa|";  // modificar
  String cTipoFiltrar = "7|8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                 // modificar
  String cEstatusIR   = "Imprimir";            // modificar
  TFechas Fecha = new TFechas();
  String dtToday = Fecha.getFechaDDMMYYYY(Fecha.TodaySQL(),"/");


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
  DecimalFormat df = new DecimalFormat("##,###,##0.00");
  String cValor = "";
  TFechas dtFecha = new TFechas();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
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
  <input type="hidden" name="hdIni" value="<%  /*EMPRESA*/   if (request.getParameter("hdIni") != null) out.print(request.getParameter("hdIni"));%>">
  <input type="hidden" name="hdIni2" value="<% /*PLANTILLA*/ if (request.getParameter("hdIni2") != null) out.print(request.getParameter("hdIni2"));%>">

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="<%if (request.getParameter("hdBoton") != null)  out.print(request.getParameter("hdBoton"));%>">
  <input type="hidden" name="hdToday" value="<%=dtToday%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
            &nbsp;
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr>
               <td colspan="6" class="ETablaT">Filtro de la Busqueda</td>
             </tr>
             <tr>
               <td class="EEtiquetaL">Unidad Médica que Controla: </td>
               <%
                  out.println("<td>");
                  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
                  out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                  out.println("</td>");

               %>
             </tr>
             <tr>
               <td colspan="6" class="EEtiquetaL">
                <%
                  if (request.getParameter("lPeriodo")!=null && request.getParameter("lPeriodo").toString().compareTo("1")==0)
                       out.println("<input type=\"checkbox\" name=\"lPeriodo\" value=\"1\" onclick=\"Activar1(!this.checked);\" checked> Periodo:");
                  else
                       out.println("<input type=\"checkbox\" name=\"lPeriodo\" value=\"1\" onclick=\"Activar1(!this.checked);\"> Periodo:");
                %>
               </td>
             </tr>
             <tr>
                <%
                  out.println("<td class=\"EEtiqueta\">");
                  if (request.getParameter("lProgramada")!=null && request.getParameter("lProgramada").toString().compareTo("1")==0)
                       out.println("<input type=\"checkbox\" name=\"lProgramada\" value=\"1\"  onclick=\"Activar2(!this.checked);\" checked> Programada:");
                  else
                       out.println("<input type=\"checkbox\" name=\"lProgramada\" value=\"1\" onclick=\"Activar2(!this.checked);\" > Programada:");
                  out.println("</td>");
                  out.println("<td class=\"EEtiqueta\">Año:</td>");
                  out.println("<td class=\"ETabla\">");
                  out.println("<select name=\"iAnio\" size=\"1\" onchange=\"llenaSLT(2,this.value,'','',document.forms[0].iCvePeriodPla);\">");
                  out.print("<option value = \"-1\" selected>Seleccione...</option>");
                   for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                        if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                           out.print("<option value = " + i + " selected>" + i + "</option>");
                        else
                           out.print("<option value = " + i + ">" + i + "</option>");
                     }
                  out.println("</select>");
                  out.println("</td>");

                  out.println("<td class=\"EEtiqueta\">Periodo:</td>");
                  out.println("<td class=\"EEtiqueta\" colspan=\"2\">");
                  TDCTRPeriodPla dCTRPeriodPla = new TDCTRPeriodPla();
                  TVCTRPeriodPla vCTRPeriodPla = new TVCTRPeriodPla();
                  Vector vPeriodo = new Vector();
                  if (request.getParameter("iAnio")!=null)
                     vPeriodo = dCTRPeriodPla.FindByAll(" WHERE CTRPeriodPla.iAnio = " + request.getParameter("iAnio").toString() + " AND CTRPeriodPla.lActivo = 1 "," ORDER BY CTRPeriodPla.iCvePeriodPla ");
                  if (vPeriodo.size()>0)
                     out.print(vEti.SelectOneRowSinTD("iCvePeriodPla","",vPeriodo,"iCvePeriodPla","cDscPeriodPla",request,"0",true));
                  else{
                     out.println("<SELECT NAME=\"iCvePeriodPla\" SIZE=\"1\">");
                     out.println("</SELECT>");
                  }
                  out.println("</td>");
                %>
                </tr>
                <tr>
                <%
                  out.println("<td class=\"EEtiqueta\">");
                  if (request.getParameter("lExtra")!=null && request.getParameter("lExtra").toString().compareTo("1")==0)
                       out.println("<input type=\"checkbox\" name=\"lExtra\" value=\"1\" onclick=\"Activar3(!this.checked);\" checked> Extra:");
                  else
                       out.println("<input type=\"checkbox\" name=\"lExtra\" value=\"1\" onclick=\"Activar3(!this.checked);\"> Extra:");

                  out.println("</td>");
                  out.print(vEti.EtiCampoCS("EEtiqueta"," ","ECampo","text",10,10,4,"dtSolicitud","",0,"","fMayus(this);",false,true,true,request));
                %>
                </tr>
               <tr>
                 <td colspan="6" class="EEtiquetaL">
                  <%
                    if (request.getParameter("lUbicacion")!=null && request.getParameter("lUbicacion").toString().compareTo("1")==0)
                         out.println("<input type=\"checkbox\" name=\"lUbicacion\" value=\"1\" onclick=\"Activar4(!this.checked);\" checked> Ubicación del Transportista:");
                    else
                         out.println("<input type=\"checkbox\" name=\"lUbicacion\" value=\"1\" onclick=\"Activar4(!this.checked);\" > Ubicación de la empresa:");
                  %>
                 </td>
               </tr>
               <tr>
                 <%
                   String cWhere = "";
                   TDPais dPais = new TDPais();
                   TVPais vPais = new TVPais();
                   Vector VecPais = new Vector();
                   VecPais = dPais.FindByAll(" order by cNombre ");

                   TDEntidadFed dEntidadFed = new TDEntidadFed();
                   TVEntidadFed vEntidadFed = new TVEntidadFed();
                   Vector VecEntidadFed = new Vector();

                   TDMunicipio dMunicipio = new TDMunicipio();
                   TVMunicipio vMunicipio = new TVMunicipio();
                   Vector VecMunicipio = new Vector();

                   out.println("<td class=\"EEtiquetaL\">Pais:</td>");
                   out.println("<td class=\"EEtiquetaL\">");
                   out.print(vEti.SelectOneRowSinTD("iCvePais","llenaSLT(3,this.value,'','',document.forms[0].iCveEstado);",VecPais,"iCvePais","cNombre",request,"0",true));
                   out.println("</td>");
                   out.println("<td class=\"EEtiquetaL\">EDO (Estado):</td>");
                   out.println("<td>");
                   if (request.getParameter("iCvePais")!=null && request.getParameter("iCveEstado")!=null){
                       VecEntidadFed = dEntidadFed.FindByAll(" Where iCvePais = "+request.getParameter("iCvePais"));
                       out.print(vEti.SelectOneRowSinTD("iCveEstado","llenaSLT(4,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio);",VecEntidadFed,"iCveEntidadFed","cNombre",request,"0",true));
                   }
                   else{
                      out.println("<select name=\"iCveEstado\" size=\"1\" onchange=\"llenaSLT(4,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio);\">");
                      out.println("</select>");
                   }
                   out.println("</td>");
                   out.println("<td class=\"EEtiqueta\">Municipio o Delegación:</td>");
                   out.println("<td>");
                   if (request.getParameter("iCvePais")!=null && request.getParameter("iCveEstado")!=null && request.getParameter("iCveMunicipio")!=null){
                      cWhere = " where iCvePais = " + request.getParameter("iCvePais") + " and iCveEntidadFed = " + request.getParameter("iCveEstado");
                      VecMunicipio = dMunicipio.FindByAll(cWhere);
                      out.print(vEti.SelectOneRowSinTD("iCveMunicipio","",VecMunicipio,"iCveMunicipio","cNombre",request,"0",true));
                   }
                   else{
                      out.println("<select name=\"iCveMunicipio\" size=\"1\">");
                      out.println("</select>");
                   }
                   out.println("</td>");
                 %>
               </tr>
               <tr>
                 <td colspan="6" class="EEtiquetaL">
                  <%
                    if (request.getParameter("lEmpresa")!=null && request.getParameter("lEmpresa").toString().compareTo("1")==0)
                         out.println("<input type=\"checkbox\" name=\"lEmpresa\" value=\"1\" onclick=\"Activar5(!this.checked);\" checked> Transportista:");
                    else
                         out.println("<input type=\"checkbox\" name=\"lEmpresa\" value=\"1\" onclick=\"Activar5(!this.checked);\"> Transportista:");
                  %>
                 </td>
               </tr>
               <tr>
                  <%
                    out.print(vEti.EtiCampo("EEtiqueta", "RFC:", "ECampo", "text", 15, 15,"cRFC", "", 3, "", "", true, true, true,request));
                    out.print(vEti.EtiCampoCS("EEtiqueta", "Denominación:", "ECampo", "text", 30, 100,3,"cDenominacion", "", 3, "", "", true, true, true,request));
                  %>
               </tr>
               <tr>
                  <%
                    out.print(vEti.EtiCampo("EEtiqueta", "ID DGPMPT:", "ECampo", "text", 15, 15,"cIDDGPMPT", "", 5, "", "", true, true, true,request));
                  %>
               </tr>
            </table>
            &nbsp;
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
              <%
                 float fDiferencia = 0;
                 String cPersona = "";
                 if (bs != null){
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\">ID</td>");
                     out.println("<td class=\"ETablaT\">ID Modo de <br> Transporte</td>");
                     out.println("<td class=\"ETablaT\">RFC</td>");
                     out.println("<td class=\"ETablaT\">Persona</td>");
                     out.println("<td class=\"ETablaT\">Denominación</td>");
                     out.println("<td class=\"ETablaT\">Plantilla</td>");
                     out.println("<td class=\"ETablaT\">Año</td>");
                     out.println("<td class=\"ETablaT\" colspan=\"4\">Periodo</td>");
                     out.println("</tr>");
                     bs.start();
                     while(bs.nextRow()){
                         if (bs.getFieldValue("cTpoPersona", "&nbsp;").toString().compareTo("F")==0)
                            cPersona = "Física";
                         else
                            if (bs.getFieldValue("cTpoPersona", "&nbsp;").toString().compareTo("M")==0)
                               cPersona = "Moral";
                            else
                               cPersona = bs.getFieldValue("cTpoPersona", "&nbsp;").toString();

                         out.println("<tr>");
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cIDDGPMPT", "&nbsp;")));
                         out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iIDMdoTrans", "&nbsp;")));
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cRFC", "&nbsp;")));

                         out.print(vEti.Texto("ETabla",""+ cPersona));
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscEmpresa", "&nbsp;")));
                         out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCvePlantilla", "&nbsp;")));
                         out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iAnio", "&nbsp;")));
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscPeriodPla", "&nbsp;")));

                         out.print("<td class=\"ECampo\">");
                         out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" + bs.getFieldValue("iCveEmpresa", "0") + "','" + bs.getFieldValue("iCvePlantilla", "0") + "','pg070502032.jsp');","Detalle"));
                         out.print("</td>");

                         out.print("<td class=\"ECampo\">");
                         out.print(vEti.clsAnclaTexto("EAncla","Seguimiento","JavaScript:fIrCatalogo('" + bs.getFieldValue("iCveEmpresa", "0") + "','" + bs.getFieldValue("iCvePlantilla", "0") + "','pg070502033.jsp');","Seguimiento"));
                         out.print("</td>");

                         out.print("<td class=\"ECampo\">");
                         out.print(vEti.clsAnclaTexto("EAncla","Registrar","JavaScript:fIrCatalogo('" + bs.getFieldValue("iCveEmpresa", "0") + "','" + bs.getFieldValue("iCvePlantilla", "0") + "','pg070502034.jsp');","Registrar"));
                         out.print("</td>");

                     }
                 } else {
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\" colspan=\"11\">Mensaje</td>");
                     out.println("</tr>");
                     out.println("<tr>");
                     out.println("<td class=\"ECampo\">No Existen datos con el Filtro Proporcionado</td>");
                     out.println("</tr>");
                 }

              %>
            </table>
      <script language="JavaScript">
        form = document.forms[0];
        form.lProgramada.disabled   = !form.lPeriodo.checked;
        form.lExtra.disabled        = !form.lPeriodo.checked;
        form.iAnio.disabled         = !form.lProgramada.checked;
        form.iCvePeriodPla.disabled = !form.lProgramada.checked;
        form.dtSolicitud.disabled       = !form.lExtra.checked;
        form.iCvePais.disabled      = !form.lUbicacion.checked;
        form.iCveEstado.disabled    = !form.lUbicacion.checked;
        form.iCveMunicipio.disabled = !form.lUbicacion.checked;
        form.cRFC.disabled          = !form.lEmpresa.checked;
        form.cDenominacion.disabled = !form.lEmpresa.checked;
        form.cIDDGPMPT.disabled     = !form.lEmpresa.checked;
      </script>
      </td>
    </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');
      </script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>


