<%/**
 * Title: pg070301111.jsp
 * Description: Catalog de Marcas de sustancias
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version 1.0
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<%
  pg070306080CFG  clsConfig = new pg070306080CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070306080.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306080.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Año|";    // modificar
  String cCveOrdenar  = "iCveAbsorvancia|iAnio|";  // modificar
  String cDscFiltrar  = "Clave|Año|";    // modificar
  String cCveFiltrar  = "iCveAbsorvancia|iAnio|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
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
  String cUpdStatus  = "AddSaveOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  String valEdicion = "";

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070306011.js)

  function fValidaTodo(){
    var form = document.forms[0];
       if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarA"){
           if (confirm("Desea Salvar?")){
              return true;
           }else{
              return false;
           }
      }else{
         return true;
      }
}
function fIrGenerar(){

    form = document.forms[0];
    form.hdBoton.value = <%=valEdicion%>
    form.hdBotonAlt.value = form.hdBoton.value;
    form.hdBoton.value = "aGenerar";
    //alert(form.hdBotonAlt.value);
  if (form.hdBoton.value == "aGenerar"){
    if (form.nOMuestras.value != ''){
       form.target =  "_self";
       form.action = 'pg070306081.jsp';
       form.submit();
    }else{
       alert("El numero de muestras debe tener valor para poder realizar la generacion");
    }
  }
  }

function fIrVerGenerar(){
       form = document.forms[0];
       form.hdBoton.value = 'Actual';
       form.target =  "FRMDatos";
       form.action = 'pg070306081.jsp';
       form.submit();
}

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
       cClave2  = ""+bs.getFieldValue("iCveSistema", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="hdBotonAlt" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="9" class="ETablaT">Parametros de Validación
                              </td>
                            </tr>

                            <%
                                TEtiCampo vEti = new TEtiCampo();
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                TDEquipo dEquipo = new TDEquipo();

                                    TFechas dtFecha = new TFechas();
                                    int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
                                    java.util.Date today = new java.util.Date();
                                    java.sql.Date defFecha = new java.sql.Date(today.getTime());
                                    java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
                                    String dFecha = "";
                                    String iCveAbsorvancia = "";

                                if (lNuevo){
                                    dFecha =dtFecha.getFechaDDMMYYYY(defaultFecha,"/");
                                    %>
                                     <input type="hidden" name="dtEstudio" value="<%=dFecha%>">
                                     <input type="hidden" name="iAnio" value="<%=iAnio%>">
                                    <%
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",5,10,"dtEstudio",dFecha,0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println(vEti.Texto("EEtiqueta","Equipo:"));
                                    out.println("<td>");
                                    out.print(vEti.SelectOneRowSinTD("iCveEquipo","",dEquipo.FindByAll(),"iCveEquipo","cDscEquipo",request,"0"));
                                    out.println("</td>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. de Muestras:","ECampo","text",10,10,"nOMuestras","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiAreaTexto("EEtiqueta","Observaciones:","ECampo",20,5,"cObservacion","",0,"","",true,true,true));
                                    out.println("</tr>");
                                    %>
                                      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                    <%
                                         out.println("<tr>");
                                            out.print("<td class=\"ECampo\">");
                                               out.print(vEti.clsAnclaTexto("EAncla","Generacion","JavaScript:fIrGenerar();","Generar datos de muestras"));
                                            out.print("</td>");
                                         out.println("</tr>");
                                    %>
                                     </table>
                                    <%

                                }
                                else {
                                     if (bs!=null){
                                        dFecha =dtFecha.getFechaDDMMYYYY(defaultFecha,"/");
                                        iCveAbsorvancia = bs.getFieldValue("iCveAbsorvancia","").toString();
                                        %>
                                        <input type="hidden" name="iCveAbsorvancia" value="<%=bs.getFieldValue("iCveAbsorvancia","").toString()%>">
                                        <input type="hidden" name="dtEstudio" value="<%=dFecha%>">
                                        <input type="hidden" name="iAnio" value="<%=iAnio%>">
                                        <%
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Codigo Absorvancia:","ECampo","text",5,10,"iCveAbsorvancia",iCveAbsorvancia,0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",5,10,"dtEstudio",dFecha,0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");
                                        valEdicion = request.getParameter("hdBoton");
                                        if (valEdicion.equalsIgnoreCase("Modificar")){
                                           out.println("<tr>");
                                           out.println(vEti.Texto("EEtiqueta","Equipo:"));
                                           out.println("<td>");
                                           out.print(vEti.SelectOneRowSinTD("iCveEquipo","",dEquipo.FindByAll(),"iCveEquipo","cDscEquipo",request,bs.getFieldValue("iCveEquipo","&nbsp;").toString()));
                                           out.println("</td>");
                                           out.println("</tr>");
                                        }else{
                                           out.println("<tr>");
                                           out.print(vEti.EtiCampo("EEtiqueta","Equipo:","ECampo","text",10,10,"cDscEquipo",bs.getFieldValue("cDscEquipo","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                           out.println("</tr>");
                                        }
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","No. de Muestras:","ECampo","text",10,10,"nOMuestras","",0,"","fMayus(this);",false,true,lCaptura));
                                        out.println("</tr>");
                                        out.println("<tr>");
                                        out.print(vEti.EtiAreaTexto("EEtiqueta","Observaciones:","ECampo",20,5,"cObservacion",bs.getFieldValue("cObservacion","&nbsp;").toString(),0,"","",true,true,lCaptura));
                                        out.println("</tr>");
                                        %>
                                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                        <%
                                             out.println("<tr>");
                                                out.print("<td class=\"ECampo\">");
                                                  %><input type="hidden" name="nOMuestras" value="1"><%
                                                   out.print(vEti.clsAnclaTexto("EAncla","Ver Lista Generada","JavaScript:fIrVerGenerar();","Ver datos de muestras"));
                                                out.print("</td>");
                                             out.println("</tr>");
                                        %>
                                         </table>
                                        <%
                                    }
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 5,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                    out.println("</tr>");
                                }
                            %>
                          <input type="hidden" name="iCveUsuResp" value="<%=vUsuario.getICveusuario()%>">
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
