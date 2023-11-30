
<%/**
 * Title: Diagnóstico de alerta y posible no aptitud
 * Description: Diagnóstico de alerta y posible no aptitud
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Romeo Sanchez
 * @version 1
 * Clase: pg070101080
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<%
  pg070101083CFG  clsConfig = new pg070101083CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070101083.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101083.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|Desc. Breve|";    // modificar
  String cCveOrdenar  = "cCIE|cDscBreve|cDscDiagnostico|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|Desc. Breve|";    // modificar
  String cCveFiltrar  = "cCIE|cDscBreve|cDscDiagnostico|";  // modificar
  String cTipoFiltrar = "8|8|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  TFechas tf = new TFechas();
  java.sql.Date d = null;
  String fechaFormateada = "";

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
  String cNavStatus  = "Hidden";//clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
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
  <input type="hidden" name="FILNumReng" value="500">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="500"> <!- <%=cPosicion%> -->
  <input type="hidden" name="hdCampoClave" value="<%=request.getParameter("hdCampoClave")%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdSaveAction" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">



                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="ETablaTR">Perfil:
                              </td>

<td class="ECampo">
    <SELECT NAME="iCvePerfil" SIZE="1" onChange="fCambiaPerfil('Actual');">
        <option value="0">Seleccione...</option>
<%
//System.out.println("accion: " + request.getParameter("hdBoton"));
//System.out.println("obtenida: " + request.getParameter("hdCampoClave"));

   Vector perfiles = new TDMEDPerfilMC().findPerfilGpoMdo(" where iCvePerfil IN (select iCvePerfil from MEDPerfilEspec) "," order by m.cDscMdoTrans, g.cDscGrupo ");
   String selected="";
   String optionSel="";
   String llave="";
   String valor="";
   for(int c = 0; c < perfiles.size(); c++) {
       TVMEDPerfilMC tv = (TVMEDPerfilMC) perfiles.elementAt(c);
       if (tv.getDtInicio()==null) {
           fechaFormateada = "&nbsp;";
       } else {
           d = tv.getDtInicio();
           fechaFormateada = sdf.format(d);
       }
       llave = ""+tv.getICvePerfil();
       valor = tv.getCDscMdoTrans()+" - "+tv.getCDscGrupo()+" ["+fechaFormateada+"]";
       selected = llave.equals(request.getParameter("iCvePerfil"))?"SELECTED":"";
       optionSel = selected.equals("SELECTED")?valor:"";
%>
        <option <%=selected%> value="<%=llave%>"><%=valor%></option>
<% } %>
    </SELECT>
</td>
                              <td class="ETablaTR">Especialidad:
                              </td>
<%
int pf = -1;
if (request.getParameter("iCvePerfil")!=null && !request.getParameter("iCvePerfil").equals("")) {
   pf = Integer.parseInt(request.getParameter("iCvePerfil"));
} else if (request.getParameter("hdCampoClave")!=null && !request.getParameter("hdCampoClave").equals("")) {
   pf = Integer.parseInt(request.getParameter("hdCampoClave"));
}

Vector esp = new TDMEDPerfilEspec().findEspecDescr(" WHERE a.iCvePerfil="+pf,"");

// agregar la opción 0 "Seleccione..."
TVMEDPerfilEspec foo = new TVMEDPerfilEspec();
foo.setICveEspecialidad(0);
foo.setCDscEspecialidad("Seleccione...");
esp.insertElementAt(foo,0);

              out.print(vEti.SelectOneRow("ECampo", "iCveEspecialidad", "fCambia('Actual');", esp , "iCveEspecialidad","cDscEspecialidad", request, optionSel));
// buscar...
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Buscar",
                                                                        "javascript:fSubmit('Actual')","Buscar"));
                                           out.print("</td>");
// Ir a...
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Ver perfiles",
                                                                        "javascript:fIr('pg070101080','pg070101080.jsp');","Ver perfiles"));
                                           out.print("</td>");

%>


</tr>
</table>
&nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Diagnósticos
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Clave</td>
                              <td class="ETablaT">Descripción breve</td>
                              <td class="ETablaT">Descripción completa</td>
                              <td class="ETablaT">Asignar</td>
                              <td class="ETablaT">Alarma</td>
                            </tr>
                            <%
                               if (bs != null){
                                   bs.start();
                                   int c = 0;
                                   while(bs.nextRow()){

                                       String iCvePerfil = bs.getFieldValue("iCvePerfil", "").toString();

                                       String iCveEspecialidad = bs.getFieldValue("iCveEspecialidad", "").toString();
                                       String iCveDiagnostico = bs.getFieldValue("iCveDiagnostico", "").toString();
                                       String lActivo = bs.getFieldValue("lActivo", "").toString();
                                       String cCIE = bs.getFieldValue("cCIE", "").toString();
                                       String cDscBreve = bs.getFieldValue("cDscBreve", "").toString();
                                       String cDscDiagnostico = bs.getFieldValue("cDscDiagnostico", "").toString();
//System.out.println("llave: " + iCvePerfil +",\t"+iCveEspecialidad+",\t"+iCveDiagnostico);
                                       boolean existePerfilDiag = true;
                                       if ( (iCvePerfil==null||iCvePerfil.equals("0")) ) {
                                            existePerfilDiag = false;
                                       } else {
                                            existePerfilDiag = true;
                                       }

                                       out.println("<tr>");
%>
<input type="hidden" name="existe_<%=c%>" value="<%=existePerfilDiag%>">

<input type="hidden" name="iCvePerfil_<%=c%>" value="<%=request.getParameter("iCvePerfil")%>">

<input type="hidden" name="iCveEspecialidad_<%=c%>" value="<%=iCveEspecialidad%>">
<input type="hidden" name="iCveDiagnostico_<%=c%>" value="<%=iCveDiagnostico%>">

<input type="hidden" name="cCIE_<%=c%>" value="<%=cCIE%>">
<input type="hidden" name="cDscBreve_<%=c%>" value="<%=cDscBreve%>">
<input type="hidden" name="cDscDiagnostico_<%=c%>" value="<%=cDscDiagnostico%>">

<input type="hidden" name="lActivo_<%=c%>" value="0">

<%
                                    // sólo se despliegan
                                        out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("cCIE", "&nbsp;")));
                                        out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("cDscBreve", "&nbsp;")));
                                        out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("cDscDiagnostico", "&nbsp;")));
                                    // funcionalidad de checkbox:
                                    // a). si está marcado Asignar, se habilita Alarma
                                    // b). si no está marcado Asignar, se deshabilita Alarma
                                        out.print(vEti.ToggleMov("ETablaC","lAsignar_"+c,existePerfilDiag?"1":"0", "javascript:fChgTog(this, lAlarma_"+c+");", 0, true, "1","0",true));
                                        out.print(vEti.ToggleMov("ETablaC","lAlarma_"+c,existePerfilDiag?bs.getFieldValue("lAlarma", "0").toString():"0","",0,true,"1","0",existePerfilDiag));

                                        c++;
                                   }
// este campo almacena el total de renglones mostrados en la página actual
%><input type="hidden" name="hdTotalRows" value="<%=c%>">

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
<%
// guardar
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Guardar",
                                                                        "javascript:fSubmit('Guardar')","Guardar"));
                                           out.print("</td>");
// cancelar
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Cancelar",
                                                                        "javascript:fSubmit('Cancelar')","Cancelar"));
                                           out.print("</td>");

%>
                            </tr>
                          </table>
<%
                               } else {
                                  // no hay datos para mostrar
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampoCS("EResalta", "Mensaje:", "EResalta", "text", 25, 50, 4, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                      out.println("</tr>");
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
