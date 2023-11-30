<%/**
 * Title: Catálogo de Perfil Médico Científico
 * Description: Catálogo de Perfil Médico Científico
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Romeo Sanchez
 * @version 1
 * Clase: pg070101080
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<html>
<%
String accion = request.getParameter("hdBoton");
//System.out.print("accion: " + accion);

  pg070101081CFG  clsConfig = new pg070101081CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101081.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101081.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave     = "";
  String cPosicion  = "";
  String cPropiedad = "";
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  TFechas tf = new TFechas();
  java.sql.Date d = null;
  String fechaFormateada = "";
  boolean showCombo = accion.equals("Modificar")?true:false;
//System.out.println("-showCombo: " + showCombo);

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Modo de transporte|Grupo|";    // modificar
  String cCveOrdenar  = "iCvePerfil|iCveMdoTrans|iCveGrupo|";  // modificar
  String cDscFiltrar  = "Clave|";    // modificar
  String cCveFiltrar  = "iCvePerfil|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cCatalogo    = "pg070101080.jsp";       // modificar
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
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070306011.js)
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
       // cPosicion almacena la posición del registro actual...
       cPosicion = Integer.toString(bs.rowNo());
//System.out.println("iCvePerfil  : " + bs.getFieldValue("iCvePerfil").toString());
//System.out.println("hdCampoClave: " + request.getParameter("hdCampoClave"));
       cClave  = bs!=null?bs.getFieldValue("iCvePerfil").toString():request.getParameter("hdCampoClave");
//       cClave  = request.getParameter("iCvePerfil");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     } else {
//System.out.println("no hay bs");
//System.out.println("hdCampoClave: " + request.getParameter("hdCampoClave"));
       cClave  = request.getParameter("iCvePerfil");
       cPosicion = request.getParameter("hdRowNum");
     }
%>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
<input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">


  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <%
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                Vector vcPersonal;
                            %>
                            <tr><td class="ETablaT" colspan="6">Perfil Médico Científico</td></tr>
                            <%
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){
//                                    out.println("<tr>");
//                                    out.print(vEti.EtiCampo("EEtiqueta", "Clave", "ECampo", "text", 8, 8, "iCvePerfil", "", 0, "", "fMayus(this);", false, false,lCaptura, request));
//                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println("<input type=\"hidden\" name=\"iCvePerfil\" value=\""+cClave+"\">"); // para que almacene el valor

                                    out.print(vEti.Texto("EEtiqueta","Modo de transporte"));
                                    out.print(vEti.SelectOneRow("ECampo", "iCveMdoTrans", "fCambiaGpo('Nuevo');", new TDGRLMdoTrans().findByAll(""), "iCveMdoTrans","cDscMdoTrans", request, ""));
                                    out.print(vEti.Texto("EEtiqueta","Grupo"));
                                    out.print(vEti.SelectOneRow("ECampo", "iCveGrupo", "", new TDGRLGrupo().findByAll("where lActivo = 1 and iCveMdoTrans = " + request.getParameter("iCveMdoTrans")), "iCveGrupo","cDscGrupo", request, ""));

                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta", "Inicio de Vigencia", "ECampo", "text", 10, 10,"dtInicio", "", 0, "", "fValFecha(this.value);", false, true,lCaptura, request));
                                    out.print(vEti.EtiCampo("EEtiqueta", "Fin de Vigencia", "ECampo", "text", 10, 10,"dtFin", "", 0, "", "fValFecha(this.value);", false, true,lCaptura, request));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Vigente"));
                                    out.print(vEti.ToggleMovCS("ECampo", 3, "lVigente", "", "", 0, true, "1", "0", true));
                                    out.println("</tr>");
                                }
                                else {

                                   if (bs != null) {

                                int acc = accion.equals("Modificar")?1:0;
                                switch (acc) {
                                case 0:
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampoCS("EEtiqueta", "Clave", "ECampo", "text", 8, 8, 3, "iCvePerfil", ""+bs.getFieldValue("iCvePerfil","&nbsp;"), 0, "", "fMayus(this);", false, true,lCaptura, request));
                                      out.println("</tr>");
                                      // intencionalmente sin break; para ejecución en cascada
                                case 1:
                                      out.println("<tr>");
                                      out.println("<input type=\"hidden\" name=\"iCvePerfil\" value=\""+""+bs.getFieldValue("iCvePerfil","&nbsp;")+"\">"); // para que almacene el valor
                                }


if (showCombo) {
//System.out.println("-mostrar combos: ");
                                    out.print(vEti.Texto("EEtiqueta","Modo de transporte"));
                                    out.print(vEti.SelectOneRow("ECampo", "iCveMdoTrans", "fCambiaGpo('Modificar');", new TDGRLMdoTrans().findByAll(""), "iCveMdoTrans","cDscMdoTrans", request, ""));
                                    out.print(vEti.Texto("EEtiqueta","Grupo"));
          // este select debe actualizarse al cambiar el combo de transporte
                                    out.print(vEti.SelectOneRow("ECampo", "iCveGrupo", "", new TDGRLGrupo().findByAll("where lActivo = 1 and iCveMdoTrans = " + request.getParameter("iCveMdoTrans")), "iCveGrupo","cDscGrupo", request, ""));
} else {
//System.out.println("-NO mostrar combos: ");

String trans = "no encontrado";
String gpo   = "no encontrado";

Vector v = new TDGRLMdoTrans().findByAll(" where iCveMdoTrans="+bs.getFieldValue("iCveMdoTrans","-1"));
if (v.size()!=0){
   TVGRLMdoTrans tvt = (TVGRLMdoTrans)v.elementAt(0);
   trans = tvt.getCDscMdoTrans();
v = new TDGRLGrupo().findByAll(" where iCveGrupo="+bs.getFieldValue("iCveGrupo","-1") + " and iCveMdoTrans="+tvt.getICveMdoTrans());
}
                                     out.print(vEti.EtiCampo("EEtiqueta", "Modo de transporte","ECampo", "text", 13, 13, "cDscMdoTrans", trans , 0,"", "", false, true, lCaptura, request));
                                     out.println("<input type=\"hidden\" name=\"iCveMdoTrans\" value=\""+""+bs.getFieldValue("iCveMdoTrans","&nbsp;")+"\">"); // para que almacene el valor
if (v.size()!=0){
   TVGRLGrupo tvg = (TVGRLGrupo)v.elementAt(0);
   gpo = tvg.getCDscGrupo();
}
                                     out.print(vEti.EtiCampo("EEtiqueta", "Grupo","ECampo", "text", 13, 13, "cDscGrupo", gpo , 0,"", "", false, true, lCaptura, request));
                                     out.println("<input type=\"hidden\" name=\"iCveGrupo\" value=\""+""+bs.getFieldValue("iCveGrupo","&nbsp;")+"\">"); // para que almacene el valor

//System.out.println("transporte/grupo:" + trans + " / " + gpo);
}
                                      out.println("<tr>");

if (bs.getFieldValue("dtInicio","")==null ||
    bs.getFieldValue("dtInicio","").equals("null")) {
  fechaFormateada = "&nbsp;";
} else {
  d = tf.getSQLDatefromSQLString(bs.getFieldValue("dtInicio","").toString());
  fechaFormateada = sdf.format(d);
}
                                      out.print(vEti.EtiCampo("EEtiqueta", "Inicio de Vigencia", "ECampo", "text", 10, 10, "dtInicio", fechaFormateada, 0, "", "fValFecha(this.value);", false, true,lCaptura, request));
if (bs.getFieldValue("dtFin","")==null ||
    bs.getFieldValue("dtFin","").equals("null")) {
  fechaFormateada = "&nbsp;";
} else {
  d = tf.getSQLDatefromSQLString(bs.getFieldValue("dtFin","").toString());
  fechaFormateada = sdf.format(d);
}
                                      out.print(vEti.EtiCampo("EEtiqueta", "Fin de Vigencia", "ECampo", "text", 10, 10, "dtFin", fechaFormateada, 0, "", "fValFecha(this.value);", false, true,lCaptura, request));
                                      out.println("</tr>");

                                      out.println("<tr>");
                                      out.print(vEti.Texto("EEtiqueta","Vigente"));
                                      out.print(vEti.ToggleMovCS("ECampo", 3, "lVigente", ""+bs.getFieldValue("lVigente", "&nbsp;"), "", 0, true, "1", "0", lCaptura));
                                      out.println("</tr>");
out.println("<table border=\"1\" class=\"ETablaInfo\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\">");
                                      out.println("<tr>");
// detalle
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Detalle",
                                                                        "javascript:fIr('" +
                                                                        bs.getFieldValue("iCvePerfil","") + "'," +
                                                                        "'pg070101085','pg070101085.jsp');","Detalle"));
                                           out.print("</td>");
// especialidad
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Especialidad",
                                                                        "javascript:fIr('" +
                                                                        bs.getFieldValue("iCvePerfil","") + "'," +
                                                                        "'pg070101082','pg070101082.jsp');","Especialidad"));
                                           out.print("</td>");
// alertas
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Alertas",
                                                                        "javascript:fIr('" +
                                                                        bs.getFieldValue("iCvePerfil","") + "'," +
                                                                        "'pg070101083','pg070101083.jsp');","Alertas"));
                                           out.print("</td>");
// evaluación
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Evaluaci&oacute;n",
                                                                        "javascript:fIr('" +
                                                                        bs.getFieldValue("iCvePerfil","") + "'," +
                                                                        "'pg070101084','pg070101084.jsp');","Evaluaci&oacute;n"));
                                           out.print("</td>");
                                      out.println("</tr>");
out.println("</table>");

                                   }else{
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



