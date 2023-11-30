<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg071003041CFG  clsConfig = new pg071003041CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071003041.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071003041.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Descripción U.M.|";    // modificar
  String cCveOrdenar  = "cDscUniMed|";  // modificar
  String cDscFiltrar  = "Descripción U.M.|";    // modificar
  String cCveFiltrar  = "cDscUniMed|";  // modificar
  String cTipoFiltrar = "8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Si";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  TDVEHEmpMantto dVEHEmpMantto = new TDVEHEmpMantto();
  TVVEHEmpMantto vVEHEmpMantto = new TVVEHEmpMantto();
  Vector vcVEHEmpMantto;

  TVGRLProcesoUM vGRLProcesoUM = new TVGRLProcesoUM();
  TDGRLProcesoUM dGRLProcesoUM = new TDGRLProcesoUM();
  Vector vcGRLProcesoUM = new Vector();

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
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave  = ""+bs.getFieldValue("iCveProceso", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type='hidden' name='hdEmpMantto' value=''>

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                              <% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <td colspan="4" class="ETablaT">Asignación de Procesos
                              </td>

              <%

                out.print("<tr>");
                out.print("<td class='EEtiqueta'>Área:</td>");
                out.print("<td class='ETabla' colspan='4'>");
                out.println("<select name=\"iCveProceso\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                  TDGRLProceso dGRLProceso = new TDGRLProceso();
                  TVGRLProceso vGRLProceso = new TVGRLProceso();
                  Vector vcGRLProceso = new Vector();
                  vcGRLProceso = dGRLProceso.FindByAll("", " order by cDscProceso ");
                  if (vcGRLProceso.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLProceso.size(); i++){
                      vGRLProceso = (TVGRLProceso)vcGRLProceso.get(i);
                      if (request.getParameter("iCveProceso")!=null && request.getParameter("iCveProceso").compareToIgnoreCase(new Integer(vGRLProceso.getICveProceso()).toString())==0)
                        out.print("<option value = " + vGRLProceso.getICveProceso() + " selected>" + vGRLProceso.getCDscProceso() + "</option>");
                      else
                        out.print("<option value = " + vGRLProceso.getICveProceso() + ">" + vGRLProceso.getCDscProceso() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                out.print("</select></td>");
                out.print("</tr>");

                               out.print("<tr>");
                                 out.print("<td class='ETablaT'>Clave</td>");
                                 out.print("<td class='ETablaT'>Unidad Médica</td>");
                                 out.print("<td class='ETablaT'>Interconsulta</td>");
                                 out.print("<td class=\"ETablaT\">Asignar<input type=\"checkbox\" name=\"chKGeneral\" OnClick=\"ChangeAllCheck(document, this.checked , 'chk')\"></td>");
                              out.print("</tr>");
                                 if (bs != null){
                                   bs.start();
                                   int iObjeto = 1;
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                       out.print("<td class='ETablaR'>" + bs.getFieldValue("iCveUniMed") + "</td>");
                                       out.print("<td class='ETabla'>" + bs.getFieldValue("cDscUniMed") + "</td>");

                                       if(request.getParameter("iCveProceso")!=null && request.getParameter("iCveProceso").compareTo("null")!=0)
                                         vcGRLProcesoUM = dGRLProcesoUM.FindByAll(" where iCveUniMed = " + bs.getFieldValue("iCveUniMed") + " and iCveProceso = " + request.getParameter("iCveProceso"));
                                       else
                                         vcGRLProcesoUM = dGRLProcesoUM.FindByAll(" where iCveUniMed = " + bs.getFieldValue("iCveUniMed") + " and iCveProceso = 0 ");

                                       if(vcGRLProcesoUM.size() > 0){
                                         vGRLProcesoUM = (TVGRLProcesoUM) vcGRLProcesoUM.get(0);
                                         int iLInterconsulta = vGRLProcesoUM.getLInterconsulta();
                                         if(iLInterconsulta==1)
                                           out.print("<td align='center'><input type='checkbox' name='chkl" + iObjeto + "' checked></td>");
                                         else
                                           out.print("<td align='center'><input type='checkbox' name='chkl" + iObjeto + "'></td>");

                                         out.print("<td align='center'><input type='checkbox' value='' name='chk" + iObjeto + "' checked></td>");
                                       }
                                       else{
                                         out.print("<td align='center'><input type='checkbox' name='chkl" + iObjeto + "'></td>");
                                         out.print("<td align='center'><input type='checkbox' value='' name='chk" + iObjeto + "'></td>");
                                       }

                                     out.print("<input type='hidden' name='chkl2" + iObjeto + "' value=''>");
                                     out.print("<input type='hidden' name='hdUniMed" + iObjeto + "' value='" + bs.getFieldValue("iCveUniMed") + "'>");

                                     iObjeto++;
                                   }
                                   out.print("<input type='hidden' name='hdTotalReg' value='" + iObjeto + "'>");
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='4'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                   out.print("</tr>");
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