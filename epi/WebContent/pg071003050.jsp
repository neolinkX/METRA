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
  pg071003050CFG  clsConfig = new pg071003050CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071003050.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071003050.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave del Area|Descripción del Area|";    // modificar
  String cCveOrdenar  = "iCveServicio|cDscArea|";  // modificar
  String cDscFiltrar  = "Clave del Area|Descripción del Area|";    // modificar
  String cCveFiltrar  = "iCveServicio|cDscArea|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
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

  TVGRLServUM vGRLServUM = new TVGRLServUM();
  TDGRLServUM dGRLServUM = new TDGRLServUM();
  Vector vcGRLServUM = new Vector();

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
       cClave  = ""+bs.getFieldValue("iCveServicio", "");
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
                              <td colspan="4" class="ETablaT">Asignación de Servicos
                              </td>

              <%

              TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
              TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
              Vector vcGRLUniMed = new Vector();
              vcGRLUniMed = dGRLUniMed.FindByAll("", " order by cDscUniMed ");

              out.print("<tr>");
                out.print("<td class='EEtiqueta'>Unidad Médica:</td>");
                String cValActual = "";
                boolean lFirst = false;
                BeanScroller beanSc = new BeanScroller(vcGRLUniMed);
                if(request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareTo("")!=0)
                  cValActual = "" + request.getParameter("iCveUniMed");
                else
                  cValActual = "0";
                out.print("<td><select name=\"iCveUniMed\" OnChange=\"llenaSLT(1,this.value,'','',document.forms[0].iCveModulo);\">");
                if (beanSc != null) {
                  out.print("<option value=0>Seleccione...</option>");
                  for (int i = 1; i <= beanSc.rowSize(); i++) {
                    beanSc.setRowIdx(i);
                    String CampoClave = "" + beanSc.getFieldValue("iCveUniMed", "");
                    if (cValActual.compareTo("") == 0 && lFirst == false) {
                      CampoClave = " selected ";
                      lFirst = true;
                    }
                    else {
                      if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
                        CampoClave = " selected ";
                      }else{
                        CampoClave = "";
                      }
                    }
                    out.print("<option " + CampoClave + " value=\"" + beanSc.getFieldValue("iCveUniMed", "" + i) + "\">" + beanSc.getFieldValue("cDscUniMed", "") + "</option>");
                  }
                  out.print("</select></td>");
                }

                out.print("<td class='EEtiqueta'>Módulo:</td>");
                out.print("<td class='ETabla'>");
                out.println("<select name=\"iCveModulo\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                if(request.getParameter("iCveUniMed")!= null){
                  TDGRLModulo dGRLModulo = new TDGRLModulo();
                  TVGRLModulo vGRLModulo = new TVGRLModulo();
                  Vector vcGRLModulo = new Vector();
                  vcGRLModulo = dGRLModulo.FindByAll(" where iCveUniMed = " + request.getParameter("iCveUniMed"));
                  if (vcGRLModulo.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLModulo.size(); i++){
                      vGRLModulo = (TVGRLModulo)vcGRLModulo.get(i);
                      if (request.getParameter("iCveModulo")!=null && request.getParameter("iCveModulo").compareToIgnoreCase(new Integer(vGRLModulo.getICveModulo()).toString())==0)
                        out.print("<option value = " + vGRLModulo.getICveModulo() + " selected>" + vGRLModulo.getCDscModulo() + "</option>");
                      else
                        out.print("<option value = " + vGRLModulo.getICveModulo() + ">" + vGRLModulo.getCDscModulo() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else if((request.getParameter("iCveUniMed")!=null && Integer.parseInt(request.getParameter("iCveUniMed").toString())<1) || request.getParameter("iCveUniMed")==null)
                  out.println("<option value='0' selected>Datos no disponibles</option>");
                out.print("</select></td>");

                               out.print("<tr>");
                                 out.print("<td class='ETablaT'>Clave</td>");
                                 out.print("<td class='ETablaT' colspan='2'>Servicio</td>");
                                 out.print("<td class=\"ETablaT\">Asignar<input type=\"checkbox\" name=\"chKGeneral\" OnClick=\"ChangeAllCheck(document, this.checked , 'chk')\"></td>");
                              out.print("</tr>");
                                 if (bs != null && request.getParameter("iCveUniMed")!=null && Integer.parseInt(request.getParameter("iCveUniMed"))>0 && request.getParameter("iCveModulo")!=null && Integer.parseInt(request.getParameter("iCveModulo"))>0){
                                   bs.start();
                                   int iObjeto = 1;
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                       out.print("<td class='ETablaR'>" + bs.getFieldValue("iCveServicio") + "</td>");
                                       out.print("<td class='ETabla' colspan='2'>" + bs.getFieldValue("cDscServicio") + "</td>");

                                       if(request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareTo("")!=0 && request.getParameter("iCveModulo")!=null && request.getParameter("iCveModulo").compareTo("")!=0)
                                         vcGRLServUM = dGRLServUM.FindByAll(" where iCveUniMed = " + request.getParameter("iCveUniMed") + " and iCveModulo = " + request.getParameter("iCveModulo") + " and iCveServicio = " + bs.getFieldValue("iCveServicio"));
                                       if(vcGRLServUM.size()>0){
                                         vGRLServUM = (TVGRLServUM)vcGRLServUM.get(0);
                                         out.print("<td align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveServicio") + "' name='chk" + iObjeto + "' checked></td>");
                                       }
                                       else{
                                         out.print("<td align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveServicio") + "' name='chk" + iObjeto + "'></td>");
                                       }
                                       out.print("<input type='hidden' value='" + bs.getFieldValue("iCveServicio") + "' name='hdChk" + iObjeto + "'></td>");
                                     out.print("</tr>");
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