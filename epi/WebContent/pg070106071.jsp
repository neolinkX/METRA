<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Oscar Castrejón Adame.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<html>
<%
  pg070106071CFG  clsConfig = new pg070106071CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070106071.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070106071.jsp\" target=\"FRMCuerpo"); // modificar
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

  String cAsignacion  = "pg071003032.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Nombre|";    // modificar
  String cCveOrdenar  = "iCveRubroNoAp|cDscRubroNoAp|";  // modificar
  String cDscFiltrar  = "Clave|Nombre|";    // modificar
  String cCveFiltrar  = "iCveRubroNoAp|cDscRubroNoAp|";  // modificar
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
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070106071.js)
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
       cClave  = ""+bs.getFieldValue("iCveRubroNoAp", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdUniMed" value="<%out.print(request.getParameter("iCveMotivoNoAp"));%>">
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
                            </tr>

            <tr>
              <td class="ETablaT" colspan='4'>Motivos de No Aptitud:</td>
            </tr>
            <tr>
              <td class="ETablaC"></td>
              <td class="ETablaC" colspan='3'>
              <%
              TDPERMotivoNoAp DPERMotivoNoAp = new TDPERMotivoNoAp();
              TVPERMotivoNoAp VPERMotivoNoAp = new TVPERMotivoNoAp();
              Vector vcPERMotivoNoAp = new Vector();
              if(request.getParameter("hdBoton").compareTo("Guardar")==0 || request.getParameter("hdBoton").compareTo("GuardarA")==0 || request.getParameter("hdBoton").compareTo("Cancelar")==0){
                out.print("<select name=\"iCveMotivoNoAp\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcPERMotivoNoAp = DPERMotivoNoAp.FindByAll("" + " order by cDscMotivoNoAp ");
                if (vcPERMotivoNoAp.size() > 0){
                  for (int i = 0; i < vcPERMotivoNoAp.size(); i++){
                    VPERMotivoNoAp = (TVPERMotivoNoAp)vcPERMotivoNoAp.get(i);
                    if (request.getParameter("hdiCveMotivoNoAp")!=null && request.getParameter("hdiCveMotivoNoAp").compareToIgnoreCase(new Integer(VPERMotivoNoAp.getICveMotivoNoAp()).toString())==0)
                      out.print("<option value = " + VPERMotivoNoAp.getICveMotivoNoAp() + " selected>" + VPERMotivoNoAp.getCDscMotivoNoAp() + "</option>");
                    else
                      out.print("<option value = " + VPERMotivoNoAp.getICveMotivoNoAp() + ">" + VPERMotivoNoAp.getCDscMotivoNoAp() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              else{
                if(request.getParameter("hdBoton").compareTo("Nuevo")==0 || request.getParameter("hdBoton").compareTo("Modificar")==0){
                  out.print("<select name=\"iCveMotivoNoAp\">");
                  //out.print("<select name=\"iCveMotivoNoAp\" disabled>");
                  }
                else
                  out.print("<select name=\"iCveMotivoNoAp\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcPERMotivoNoAp = DPERMotivoNoAp.FindByAll("" + " order by cDscMotivoNoAp ");
                if (vcPERMotivoNoAp.size() > 0){
                  if(request.getParameter("iCveMotivoNoAp")==null || request.getParameter("iCveMotivoNoAp").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  else if(request.getParameter("iCveMotivoNoAp")!=null && Integer.parseInt(request.getParameter("iCveMotivoNoAp"))<1 )
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcPERMotivoNoAp.size(); i++){
                    VPERMotivoNoAp = (TVPERMotivoNoAp)vcPERMotivoNoAp.get(i);
                    if (request.getParameter("iCveMotivoNoAp")!=null && request.getParameter("iCveMotivoNoAp").compareToIgnoreCase(new Integer(VPERMotivoNoAp.getICveMotivoNoAp()).toString())==0)
                      out.print("<option value = " + VPERMotivoNoAp.getICveMotivoNoAp() + " selected>" + VPERMotivoNoAp.getCDscMotivoNoAp() + "</option>");
                    else
                      out.print("<option value = " + VPERMotivoNoAp.getICveMotivoNoAp() + ">" + VPERMotivoNoAp.getCDscMotivoNoAp() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              out.print("</select>");
              out.print("</td>");
              out.print("</tr>");

              out.print("<tr><td colspan='4' class='ETablaT'>Módulo</td></tr>");

              boolean lAsignacion = clsConfig.getLPagina(cAsignacion);

// N U E V O

                               if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                 out.println("<td>");
                                 out.print("<input type='text' size='10' disabled>");
                                 out.print("</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print("<td class='EEtiqueta'>Descripción:</td>");
                                 out.print("<td colspan='3'><input type='text' name='cDscRubroNoAp' size=150 maxLength=150 OnBlur='fMayus(this);'></td>");
                                 out.println("</tr>");
                               }
                               else{
                                 if (bs != null){ // M O D I F I C A R
                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                     out.println("<tr>");
                                     out.println("<td class='EEtiqueta'>Clave:</td>");
                                     out.println("<td class='ECampo'>" + bs.getFieldValue("iCveRubroNoAp", "&nbsp;") + "</td>");
                                     out.println("</tr>");
                                     out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Descripción:</td>");
                                     out.print("<td colspan='3'><input type='text' name='cDscRubroNoAp' size=150 maxLength=150 OnBlur='fMayus(this);' value='");
                                     //out.print(""+bs.getFieldValue("cDscModulo"));
                                     out.print(""+bs.getFieldValue("cDscRubroNoAp"));
                                     out.print("'></td>");
                                     out.print("</tr>");
                                   }
                                   else{ // V E R   D A T O S
                                     out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Clave:</td>");
                                     out.print("<td class='ECampo'>" + bs.getFieldValue("iCveRubroNoAp", "") + "</td>");
                                     out.print("<input type='hidden' name='iCveRubroNoAp' value='" + bs.getFieldValue("iCveMotivoNoAp", "&nbsp;") + "'>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Descripción:</td>");
                                     out.print("<td class='ECampo' colspan='3'>" + bs.getFieldValue("cDscRubroNoAp", "&nbsp;") + "</td>");
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