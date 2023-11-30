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
  pg071006020CFG  clsConfig = new pg071006020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071006020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071006020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave del Requisito|Descripción del Requisito|";    // modificar
  String cCveOrdenar  = "iCveRequisito|cDscRequisito|";  // modificar
  String cDscFiltrar  = "Clave del Requisito|Descripción del Requisito|";    // modificar
  String cCveFiltrar  = "iCveRequisito|cDscRequisito|";  // modificar
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

  TDGRLMotivo dGRLMotivo = new TDGRLMotivo();
  TVGRLMotivo vGRLMotivo = new TVGRLMotivo();
  Vector vcGRLMotivo;
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
       cClave  = ""+bs.getFieldValue("iCveRequisito", "");
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
                              <td colspan="5" class="ETablaT">Requisitos por Motivo
                              </td>
                              <%
                              out.print("<tr><td class='ETablaTR'>Motivo:</td><td class='ETabla' colspan='4'>");
                                out.print("<select name=\"iCveMotivo\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\" >");
                                  vcGRLMotivo = dGRLMotivo.FindByAll(" where lActivo = 1 order by cDscMotivo ");
                                  if (vcGRLMotivo.size() > 0){
                                    for (int i = 0; i < vcGRLMotivo.size(); i++){
                                      vGRLMotivo = (TVGRLMotivo)vcGRLMotivo.get(i);
                                      if (request.getParameter("iCveMotivo")!=null && request.getParameter("iCveMotivo").compareToIgnoreCase(new Integer(vGRLMotivo.getICveMotivo()).toString())==0)
                                        out.print("<option value = " + vGRLMotivo.getICveMotivo() + " selected>" + vGRLMotivo.getCDscMotivo() + "</option>");
                                      else
                                        out.print("<option value = " + vGRLMotivo.getICveMotivo() + ">" + vGRLMotivo.getCDscMotivo() + "</option>");
                                    }
                                  }
                                  else{
                                    out.print("<option value = 0>Datos no disponibles...</option>");
                                  }
                               out.print("</select></td></tr>");

                               out.print("<tr>");
                                 out.print("<td class='ETablaT'>Clave</td>");
                                 out.print("<td class='ETablaT'>Requisito</td>");
                                 out.print("<td class='ETablaT'>Obligatorio</td>");
                                 out.print("<td class='ETablaT'>No. Copias</td>");
                                 out.print("<td class='ETablaT'>Asignar<input type=\"checkbox\" name=\"chKGeneral\" OnClick=\"ChangeAllCheck(document, this.checked , 'chk')\"</td>");
                              out.print("</tr>");

                                 if (bs != null){
                                   bs.start();
                                   int iObjeto = 1;
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                       out.print("<td class='ETablaC'>" + bs.getFieldValue("iCveRequisito") + "</td>");
                                       out.print("<td class='ETablaC'>" + bs.getFieldValue("cDscRequisito") + "</td>");

                                       TVGRLReqxMotivo vGRLReqxMotivo = new TVGRLReqxMotivo();
                                       TDGRLReqxMotivo dGRLReqxMotivo = new TDGRLReqxMotivo();
                                       Vector vcGRLReqxMotivo = new Vector();
                                       if(request.getParameter("iCveMotivo")!=null && request.getParameter("iCveMotivo").compareTo("")!=0)
                                         vcGRLReqxMotivo = dGRLReqxMotivo.FindByAll(" where iCveMotivo = " + request.getParameter("iCveMotivo") + " and iCveRequisito = " + bs.getFieldValue("iCveRequisito"), "");
                                       if(vcGRLReqxMotivo.size()>0){
                                         vGRLReqxMotivo = (TVGRLReqxMotivo) vcGRLReqxMotivo.get(0);
                                         if((int)vGRLReqxMotivo.getLObligatorio()==1)
                                           out.print("<td align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveRequisito") + "' name='chkO" + iObjeto + "' checked></td>");
                                         else
                                           out.print("<td align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveRequisito") + "' name='chkO" + iObjeto + "'></td>");
                                         out.print("<td align='center'><input type='text' value='" + vGRLReqxMotivo.getICopias() + "' size=2 maxlength=2 name='txt" + iObjeto + "'></td>");
                                         out.print("<input type='hidden' name='ttxt" + iObjeto + "'>");
                                         out.print("<td align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveRequisito") + "' name='chk" + iObjeto + "' checked></td>");
                                       }
                                       else{
                                         out.print("<td align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveRequisito") + "' name='chkO" + iObjeto + "'></td>");
                                         out.print("<td align='center'><input type='text' size=2 maxlength=2 name='txt" + iObjeto + "'></td>");
                                         out.print("<input type='hidden' name='ttxt" + iObjeto + "'>");
                                         out.print("<td align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveRequisito") + "' name='chk" + iObjeto + "'></td>");
                                       }
                                       out.print("<input type='hidden' value='" + bs.getFieldValue("iCveRequisito") + "' name='hdChk" + iObjeto + "'></td>");

                                     out.print("</tr>");
                                     iObjeto++;
                                   }
                                   out.print("<input type='hidden' name='hdTotalReg' value='" + iObjeto + "'>");
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='5'>No existen datos coincidentes con el filtro proporcionado.</td>");
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