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
<%@ page import="java.util.*" %>
<%@ page import="com.micper.ingsw.*"%>

<html>
<%
  pg071003061CFG  clsConfig = new pg071003061CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071003061.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071003061.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave del Proceso|";    // modificar
  String cCveOrdenar  = "GRLProcesoUM.iCveProceso|";  // modificar
  String cDscFiltrar  = "Clave del Proceso|";    // modificar
  String cCveFiltrar  = "GRLProcesoUM.iCveProceso|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Si";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

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
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="iCveUsuario" value="<%out.print(request.getParameter("iCveUsuario"));%>">
  <input type="hidden" name="cApPaterno" value="<%out.print(request.getParameter("cApPaterno"));%>">
  <input type="hidden" name="cApMaterno" value="<%out.print(request.getParameter("cApMaterno"));%>">
  <input type="hidden" name="cNombre" value="<%out.print(request.getParameter("cNombre"));%>">
  <input type="hidden" name="cUsuario" value="<%out.print(request.getParameter("cUsuario"));%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Usuario
                              </td>
                            </tr>
                             <% // modificar según listado

                               TDSEGUsuario dSEGUsuario = new TDSEGUsuario();
                               TVSEGUsuario vSEGUsuario = new TVSEGUsuario();
                               Vector vcSEGUsuario = new Vector();
                               vcSEGUsuario = dSEGUsuario.FindByAll(" where iCveUsuario = " + request.getParameter("iCveUsuario"));

                               out.print("<tr>");
                               out.print("<td class=\"ETablaTR\">Clave</td>");
                               out.print("<td class=\"ETablaT\">Primer Apellido</td>");
                               out.print("<td class=\"ETablaT\">Segundo Apellido</td>");
                               out.print("<td class=\"ETablaT\">Nombre</td>");
                               out.print("<td class=\"ETablaT\">Clave Usuario</td>");
                               out.print("</tr>");

                               if(vcSEGUsuario.size()>0){
                                 vSEGUsuario = (TVSEGUsuario)vcSEGUsuario.get(0);
                                 out.print("<tr>");
                                 out.print(vEti.Texto("ETablaR", vSEGUsuario.getICveUsuario()+""));
                                 out.print(vEti.Texto("ETabla", vSEGUsuario.getCApPaterno()));
                                 out.print(vEti.Texto("ETabla", vSEGUsuario.getCApMaterno()));
                                 out.print(vEti.Texto("ETabla", vSEGUsuario.getCNombre()));
                                 out.print(vEti.Texto("ETabla", vSEGUsuario.getCUsuario()));
                               }
                               else{
                                 out.print(vEti.Texto("ECampo", "No existen datos coincidentes con el filtro proporcionado."));
                               }

            out.print("<tr>");
              out.print("<td class='ETablaT' colspan='5'>Unidad Médica</td>");
            out.print("</tr>");
            out.print("<tr>");
              out.print("<td align='center' colspan='5'>");
              TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
              TVGRLUniMed vGRLUniMed = new TVGRLUniMed();
              Vector vcGRLUniMed = new Vector();

                out.print("<select name=\"iCveUniMed\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcGRLUniMed = dGRLUniMed.FindByAll("", " order by cDscUniMed ");
                if (vcGRLUniMed.size() > 0){
                  boolean lNull = false;
                  for (int i = 0; i < vcGRLUniMed.size(); i++){
                    vGRLUniMed = (TVGRLUniMed)vcGRLUniMed.get(i);
                    if (request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareToIgnoreCase(new Integer(vGRLUniMed.getICveUniMed()).toString())==0)
                      out.print("<option value = '" + vGRLUniMed.getICveUniMed() + "' selected>" + vGRLUniMed.getCDscUniMed() + "</option>");
                    else if ((request.getParameter("iCveUniMed")==null && lNull==false) || (request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareTo("null")==0 && lNull==false) || (request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareTo("")==0 && lNull==false) || (request.getParameter("iCveUniMed")!=null && Integer.parseInt(request.getParameter("iCveUniMed"))<1 && lNull==false)){
                      out.print("<option value = '0' selected>Seleccione...</option>");
                      lNull = true;
                    }
                    else
                      out.print("<option value = '" + vGRLUniMed.getICveUniMed() + "'>" + vGRLUniMed.getCDscUniMed() + "</option>");
                  }
                }

              out.print("</select>");
              out.print("</td>");
              out.print("</tr>");


                               if (bs != null){
                                   out.print("<tr>");
                                   out.print("<td class='ETablaT'>Clave</td>");
                                   out.print("<td class='ETablaT' colspan=3>Proceso</td>");
                                   out.print("<td class='ETablaT'>Asignar<input type=\"checkbox\" name=\"chKGeneral\" OnClick=\"ChangeAllCheck(document, this.checked , 'chk')\"></td>");
                                   out.print("</tr>");
                                   bs.start();
                                   int iObjeto = 1;
                                   while(bs.nextRow()){
                                     TVGRLUMUsuarioMP vGRLUMUsuario = new TVGRLUMUsuarioMP();
                                     TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
                                     Vector vcGRLUMUsuario = new Vector();
                                     out.print("<tr>");
                                       out.print("<td class='ETablaR'>" + bs.getFieldValue("iCveProceso") + "</td>");
                                       out.print("<td class='ETabla' colspan=3>" + bs.getFieldValue("cDscProceso") + "</td>");

                                       if(request.getParameter("iCveUniMed")!=null)
                                         vcGRLUMUsuario = dGRLUMUsuario.FindByAllSimple(" where iCveUniMed = " + request.getParameter("iCveUniMed") + " and iCveUsuario = " + request.getParameter("iCveUsuario") + " and iCveProceso = " + bs.getFieldValue("iCveProceso"));
                                       if(vcGRLUMUsuario.size()>0){
                                         vGRLUMUsuario = (TVGRLUMUsuarioMP)vcGRLUMUsuario.get(0);
                                         out.print("<td align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveProceso") + "' name='chk" + iObjeto + "' checked></td>");
                                       }
                                       else{
                                         out.print("<td align='center' colspan='2'><input type='checkbox' value='" + bs.getFieldValue("iCveProceso") + "' name='chk" + iObjeto + "'></td>");
                                       }
                                       out.print("<input type='hidden' value='" + bs.getFieldValue("iCveProceso") + "' name='hdChk" + iObjeto + "'></td>");
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
                          </table>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
