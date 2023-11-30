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
  pg070601072CFG  clsConfig = new pg070601072CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070601072.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070601072.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave del Servicio|Descripción del Servicio|";    // modificar
  String cCveOrdenar  = "iCveTpoMantto|cDscTpoMantto|";  // modificar
  String cDscFiltrar  = "Clave del Servicio|Descripción del Servicio|";    // modificar
  String cCveFiltrar  = "iCveTpoMantto|cDscTpoMantto|";  // modificar
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

  TDEQMEmpMtto dEQMEmpMtto = new TDEQMEmpMtto();
  TVEQMEmpMtto vEQMEmpMtto = new TVEQMEmpMtto();
  Vector vcEQMEmpMtto;
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
       cClave  = ""+bs.getFieldValue("iCveTpoMantto", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
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
                               %>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <td colspan="3" class="ETablaT">Tipos de Mantenimiento por Empresa
                              </td>
                              <%
                              out.print("<tr><td class='ETablaTR'>Empresa:</td><td class='ETabla' colspan='2'>");
                                out.print("<select name=\"iCveEmpMtto\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\" >");
                                  vcEQMEmpMtto = dEQMEmpMtto.FindByAll(" where lActivo = 1 ", " order by iCveEmpMtto ");
                                  if (vcEQMEmpMtto.size() > 0){
                                    for (int i = 0; i < vcEQMEmpMtto.size(); i++){
                                      vEQMEmpMtto = (TVEQMEmpMtto)vcEQMEmpMtto.get(i);
                                      if (request.getParameter("iCveEmpMtto")!=null && request.getParameter("iCveEmpMtto").compareToIgnoreCase(new Integer(vEQMEmpMtto.getICveEmpMtto()).toString())==0)
                                        out.print("<option value = " + vEQMEmpMtto.getICveEmpMtto() + " selected>" + vEQMEmpMtto.getCDscEmpMtto() + "</option>");
                                      else
                                        out.print("<option value = " + vEQMEmpMtto.getICveEmpMtto() + ">" + vEQMEmpMtto.getCDscEmpMtto() + "</option>");
                                    }
                                  }
                                  else{
                                    out.print("<option value = 0>Datos no disponibles...</option>");
                                  }
                               out.print("</select></td></tr>");

                               out.print("<tr>");
                                 out.print("<td class='ETablaT'>Clave</td>");
                                 out.print("<td class='ETablaT'>Mantenimiento</td>");
                                 out.print("<td class='ETablaT'>Asignar</td>");
                              out.print("</tr>");

                                 if (bs != null){
                                   bs.start();
                                   int iChk = 1;
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                       out.print("<td class='ETablaC'>" + bs.getFieldValue("iCveTpoMantto") + "</td>");
                                       out.print("<td class='ETablaC'>" + bs.getFieldValue("cDscBreve") + "</td>");

                                       TVEQMMttoXEmp vEQMMttoXEmp = new TVEQMMttoXEmp();
                                       TDEQMMttoXEmp dEQMMttoXEmp = new TDEQMMttoXEmp();
                                       Vector vcEQMMttoXEmp = new Vector();
                                       if(request.getParameter("iCveEmpMtto")!=null && request.getParameter("iCveEmpMtto").compareTo("")!=0)
                                         vcEQMMttoXEmp = dEQMMttoXEmp.FindByAll(" where iCveEmpMtto = " + request.getParameter("iCveEmpMtto") + " and iCveTpoMantto = " + bs.getFieldValue("iCveTpoMantto"), "");
                                       else
                                         vcEQMMttoXEmp = dEQMMttoXEmp.FindByAll(" where iCveEmpMtto = 1 and iCveTpoMantto = " + bs.getFieldValue("iCveTpoMantto"), "");
                                       if(vcEQMMttoXEmp.size()>0)
                                         out.print("<td align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveTpoMantto") + "' name='chk" + iChk + "' checked></td>");
                                       else
                                         out.print("<td align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveTpoMantto") + "' name='chk" + iChk + "'></td>");
                                       out.print("<input type='hidden' value='" + bs.getFieldValue("iCveTpoMantto") + "' name='hdChk" + iChk + "'></td>");
                                     out.print("</tr>");
                                     iChk++;
                                   }
                                   out.print("<input type='hidden' name='hdTotalReg' value='" + iChk + "'>");
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='3'>No existen datos coincidentes con el filtro proporcionado.</td>");
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