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
  pg071003064CFG  clsConfig = new pg071003064CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071003062.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071003062.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave de la Rama|";    // modificar
  String cCveOrdenar  = "iCveRama|";  // modificar
  String cDscFiltrar  = "Clave de la Rama|";    // modificar
  String cCveFiltrar  = "iCveRama|";  // modificar
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

  TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
  TVGRLUMUsuarioMP vGRLUMUsuario = new TVGRLUMUsuarioMP();
  Vector vcGRLUMUsuario = new Vector();

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

// U N I D A D   M E D I C A

            out.print("<tr>");
              out.print("<td class='ETablaT' colspan='5'>Unidad Médica</td>");
            out.print("</tr>");

            out.print("<tr>");
              out.print("<td class='EEtiqueta' colspan=2>Unidad Médica:</td>");
              out.print("<td class='ETabla' colspan='3'>");
              dGRLUMUsuario = new TDGRLUMUsuario();
              vGRLUMUsuario = new TVGRLUMUsuarioMP();
              vcGRLUMUsuario = new Vector();

                out.print("<select name=\"iCveUniMed\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcGRLUMUsuario = dGRLUMUsuario.getUniMedxUsu2(Integer.parseInt(request.getParameter("iCveUsuario")));
                if (vcGRLUMUsuario.size() > 0){
                    boolean lNull = false;
                    for (int i = 0; i < vcGRLUMUsuario.size(); i++){
                      vGRLUMUsuario = (TVGRLUMUsuarioMP)vcGRLUMUsuario.get(i);
                      if (request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareToIgnoreCase(new Integer(vGRLUMUsuario.getICveUniMed()).toString())==0)
                        out.print("<option value = '" + vGRLUMUsuario.getICveUniMed() + "' selected>" + vGRLUMUsuario.getCDscUniMed() + "</option>");
                      else if ((request.getParameter("iCveUniMed")==null && lNull==false) || (request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareTo("null")==0 && lNull==false) || (request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareTo("")==0 && lNull==false) || (request.getParameter("iCveUniMed")!=null && Integer.parseInt(request.getParameter("iCveUniMed"))<1 && lNull==false)){
                        out.print("<option value='0' selected>Seleccione...</option>");
                        lNull = true;
                      }
                      else
                        out.print("<option value = '" + vGRLUMUsuario.getICveUniMed() + "'>" + vGRLUMUsuario.getCDscUniMed() + "</option>");
                    }
                }

              out.print("</select>");
              out.print("</td>");
              out.print("</tr>");
              out.print("<tr>");
                out.print("<td class='EEtiqueta' colspan=2>Proceso:</td>");
                out.print("<td class='ETabla' colspan=3>");
                out.println("<select name=\"iCveProceso\">");
                if(request.getParameter("iCveUniMed")!= null){
                  dGRLUMUsuario = new TDGRLUMUsuario();
                  vGRLUMUsuario = new TVGRLUMUsuarioMP();
                  vcGRLUMUsuario = new Vector();
                  vcGRLUMUsuario = dGRLUMUsuario.getProcesos2(Integer.parseInt(request.getParameter("iCveUsuario")), Integer.parseInt(request.getParameter("iCveUniMed")));
                  if (vcGRLUMUsuario.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLUMUsuario.size(); i++){
                      vGRLUMUsuario = (TVGRLUMUsuarioMP)vcGRLUMUsuario.get(i);
                      if (request.getParameter("iCveProceso")!=null && request.getParameter("iCveProceso").compareToIgnoreCase(new Integer(vGRLUMUsuario.getICveProceso()).toString())==0)
                        out.print("<option value = " + vGRLUMUsuario.getICveProceso() + " selected>" + vGRLUMUsuario.getCDscProceso() + "</option>");
                      else
                        out.print("<option value = " + vGRLUMUsuario.getICveProceso() + ">" + vGRLUMUsuario.getCDscProceso() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else if((request.getParameter("iCveUniMed")!=null && Integer.parseInt(request.getParameter("iCveUniMed").toString())<1) || request.getParameter("iCveUniMed")==null)
                  out.println("<option value='0' selected>Datos no disponibles</option>");
                out.print("</select></td>");
              out.print("</tr>");

// M O D U L O

              out.print("<tr>");
                out.print("<td class='EEtiqueta' colspan=2>Módulo:</td>");
                out.print("<td class='ETabla' colspan=3>");
                out.println("<select name=\"iCveModulo\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                if(request.getParameter("iCveUniMed")!= null){
                  TDGRLServUM dGRLServUM = new TDGRLServUM();
                  TVGRLServUM vGRLServUM = new TVGRLServUM();
                  Vector vcGRLServUM = new Vector();
                  vcGRLServUM = dGRLServUM.getModulosXUniMed(request.getParameter("iCveUniMed"));
                  if (vcGRLServUM.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLServUM.size(); i++){
                      vGRLServUM = (TVGRLServUM)vcGRLServUM.get(i);
                      if (request.getParameter("iCveModulo")!=null && request.getParameter("iCveModulo").compareToIgnoreCase(new Integer(vGRLServUM.getICveModulo()).toString())==0)
                        out.print("<option value = " + vGRLServUM.getICveModulo() + " selected>" + vGRLServUM.getCDscModulo() + "</option>");
                      else
                        out.print("<option value = " + vGRLServUM.getICveModulo() + ">" + vGRLServUM.getCDscModulo() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else if((request.getParameter("iCveUniMed")!=null && Integer.parseInt(request.getParameter("iCveUniMed").toString())<1) || request.getParameter("iCveUniMed")==null)
                  out.println("<option value='0' selected>Datos no disponibles</option>");
                out.print("</select></td>");
              out.print("</tr>");

// S E R V I C I O

              out.print("<tr>");
                out.print("<td class='EEtiqueta' colspan=2>Servicio:</td>");
                out.print("<td class='ETabla' colspan=3>");
                out.println("<select name=\"iCveServicio\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                if(request.getParameter("iCveModulo")!= null){
                  TDGRLServUM dGRLServUM = new TDGRLServUM();
                  TVGRLServUM vGRLServUM = new TVGRLServUM();
                  Vector vcGRLServUM = new Vector();
                  vcGRLServUM = dGRLServUM.getServXUMMod(request.getParameter("iCveUniMed"), request.getParameter("iCveModulo"));
                  if (vcGRLServUM.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLServUM.size(); i++){
                      vGRLServUM = (TVGRLServUM)vcGRLServUM.get(i);
                      if (request.getParameter("iCveServicio")!=null && request.getParameter("iCveServicio").compareToIgnoreCase(new Integer(vGRLServUM.getICveServicio()).toString())==0)
                        out.print("<option value = " + vGRLServUM.getICveServicio() + " selected>" + vGRLServUM.getCDscServicio() + "</option>");
                      else
                        out.print("<option value = " + vGRLServUM.getICveServicio() + ">" + vGRLServUM.getCDscServicio() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else if((request.getParameter("iCveUniMed")!=null && Integer.parseInt(request.getParameter("iCveUniMed").toString())<1) || request.getParameter("iCveUniMed")==null)
                  out.println("<option value='0' selected>Datos no disponibles</option>");
                out.print("</select></td>");
              out.print("</tr>");

                               if (bs != null){
                                   out.print("<tr>");
                                   out.print("<td class='ETablaT'>Clave</td>");
                                   out.print("<td class='ETablaT' colspan=3>Rama</td>");
                                   out.print("<td class='ETablaT'>Asignar<input type=\"checkbox\" name=\"chKGeneral\" OnClick=\"ChangeAllCheck(document, this.checked , 'chk')\"></td>");
                                   out.print("</tr>");
                                   bs.start();
                                   int iObjeto = 1;
                                   while(bs.nextRow()){
                                     TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
                                     TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
                                     Vector vcGRLUSUMedicos = new Vector();
                                     out.print("<tr>");
                                       out.print("<td class='ETablaR'>" + bs.getFieldValue("iCveRama") + "</td>");
                                       out.print("<td class='ETabla' colspan=3>" + bs.getFieldValue("cDscRama") + "</td>");

                                       if(request.getParameter("iCveUniMed")!=null)
                                         vcGRLUSUMedicos = dGRLUSUMedicos.FindByAllSimple(" where iCveUsuario = " + request.getParameter("iCveUsuario") + " and iCveUniMed = " + request.getParameter("iCveUniMed") + " and iCveProceso = " + request.getParameter("iCveProceso") + " and iCveModulo = " + request.getParameter("iCveModulo") + " and iCveServicio = " + request.getParameter("iCveServicio") + " and iCveRama = " + bs.getFieldValue("iCveRama"));
                                       if(vcGRLUSUMedicos.size()>0){
                                         vGRLUSUMedicos = (TVGRLUSUMedicos)vcGRLUSUMedicos.get(0);
                                         out.print("<td align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveRama") + "' name='chk" + iObjeto + "' checked></td>");
                                       }
                                       else{
                                         out.print("<td align='center' colspan='2'><input type='checkbox' value='" + bs.getFieldValue("iCveRama") + "' name='chk" + iObjeto + "'></td>");
                                       }
                                       out.print("<input type='hidden' value='" + bs.getFieldValue("iCveRama") + "' name='hdChk" + iObjeto + "'></td>");
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
