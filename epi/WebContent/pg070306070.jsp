<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.pg070306070CFG"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.ingsw.TEtiCampo"%>




<html>


<%
  pg070306070CFG  clsConfig = new pg070306070CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070306070.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306070.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cClave3    = "";
  String cClave4    = "";
  String cPosicion  = "";
  String cPropiedad = "";


  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|";    // modificar
  String cCveOrdenar  = "iCveValPres|";  // modificar
  String cDscFiltrar  = "Clave|";    // modificar
  String cCveFiltrar  = "iCveValPres|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Reporte";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas      = clsConfig.getPaginas();
  String cDscPaginas   = clsConfig.getDscPaginas();
  BeanScroller bs      = clsConfig.getBeanScroller();
  String cRutaImg      = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg      = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus    = clsConfig.getUpdStatus();
  String cNavStatus    = clsConfig.getNavStatus();
  String cOtroStatus   = clsConfig.getOtroStatus();
  String cCanWrite     = clsConfig.getCanWrite();
  String cSaveAction   = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  String cPagina = "";

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());


%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070306011.js)




  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }

  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070306021P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070306021P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

</script>


<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>

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
       cClave2  = ""+bs.getFieldValue("iAnio", "");
       cClave3  = ""+bs.getFieldValue("iCveLaboratorio", "");
       cClave4  = ""+bs.getFieldValue("iCveValPres", "");
     }

     TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCampoClave3" value="<%=cClave3%>">
  <input type="hidden" name="hdCampoClave4" value="<%=cClave4%>">
  <input type="hidden" name="iCveUsuResp" value="<%=vUsuario.getICveusuario()%>">

  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">


  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){%>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                         <%
                           TEtiCampo vEti = new TEtiCampo();
                           boolean lCaptura = clsConfig.getCaptura();
                           boolean lNuevo = clsConfig.getNuevo();

                         if (!lCaptura) {
                         %>
                         <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                          <tr>
                           <td class="ETablaT" colspan="4">Filtrar</td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Año:</td>
                              <td class="ETabla">
                                 <select name="iAnio" size="1">
                                    <%
                                       for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                          if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                             out.print("<option value = " + i + " selected>" + i + "</option>");
                                          else
                                             out.print("<option value = " + i + ">" + i + "</option>");
                                       }
                                    %>
                                 </select>
                              </td>
                              <td class="EEtiqueta">Laboratorio:</td>
                              <td class="ETabla">
                                  <%  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                      out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                                      // llenaSLT(1,document.forms[0].iAnio.value,this.value,'',document.forms[0].iCveLoteCualita);
                                  %>
                              </td>
                            </tr>
                          </table>
                          <br>
                          <%
                         }
                          %>


                           <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>

                             <tr>
                             <td colspan="4" class="ETablaT">Parametros de Validación</td>
                             </tr>

                             <%
                               if (lNuevo){ // Opcion de Nueva Captura


                                          /* [Año] , [Laboratorio]*/

                                          %>
                                          <tr>
                                          <%
                                           out.println(vEti.Texto("EEtiqueta","Año:"));
                                          %>
                                          <td class="ETabla">
                                          <select name="iAnio" size="1">
                                          <%
                                                 //TEtiCampo vEti = new TEtiCampo();
                                               for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                                 if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                                    out.print("<option value = " + i + " selected>" + i + "</option>");
                                                 else
                                                    out.print("<option value = " + i + ">" + i + "</option>");
                                               }
                                          %>
                                          </select>
                                          </td>
                                          <%
                                             out.println(vEti.Texto("EEtiqueta","Laboratorio:"));
                                          %>
                                          <td class="ETabla">
                                          <%                                       //"fCambiaLab('" + clsConfig.getAccionOriginal() + "');"
                                            out.println(vEti.SelectOneRowSinTD("iCveLaboratorio","",(Vector) AppCacheManager.getColl("GRLUniMed",""), "iCveUniMed", "cDscUniMed", request, "0"));
                                          %>
                                          </td>
                                          </tr>

                                          <%

                                          /* [Fecha] */
                                           out.println("<tr>");
                                           out.print(vEti.EtiCampoCS("EEtiqueta","Fecha:","ECampo","text",10,10,3,"dtEstudio","&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                           out.println("</tr>");

                                          /* [Equipo] */
                                          out.println("<tr>");
                                          out.println(vEti.Texto("EEtiqueta","Equipo:"));
                                          out.println("<td colspan=\"3\" class=\"ETabla\">");
                                          Vector vcEquipos = new Vector();
                                          DAOEQMEquipo dEquipos = new  DAOEQMEquipo();
                                          vcEquipos = dEquipos.FindByAll();
                                          out.print(vEti.SelectOneRowSinTD("iCveEquipo","",vcEquipos,"iCveEquipo","cDscEquipo",request,"0"));
                                          out.println("</td></tr>");
                                          /*out.print(vEti.EtiCampoCS("EEtiqueta","Equipo:","ECampo","text",10,10,3,"iCveEquipo","&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");*/

                                          /* [Droga] */
                                          out.println("<tr>");
                                          out.println(vEti.Texto("EEtiqueta","Droga:"));
                                          out.println("<td colspan=\"3\" class=\"ETabla\">");
                                          Vector vcSustancias = new Vector();
                                          DAOTOXSustancia dSustancias = new  DAOTOXSustancia();
                                          vcSustancias = dSustancias.FindByAll();
                                          out.print(vEti.SelectOneRowSinTD("iCveSustancia","",vcSustancias,"iCveSustancia","cDscSustancia",request,""));
                                          out.println("</td></tr>");
                                          /*out.print(vEti.EtiCampoCS("EEtiqueta","Droga:","ECampo","text",10,10,3,"iCveSustancia","&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");*/

                                          /* [Corte] */
                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Corte:","ECampo","text",13,13,3,"dCorte","",0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          /* [Negativo] */
                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Negativo:","ECampo","text",13,13,3,"dCorteNeg","",0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          /* [Positivo] */
                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Positivo:","ECampo","text",13,13,3,"dCortePost","",0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          /* [No. Muestras]  */
                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","No. Muestras:","ECampo","text",10,10,3,"iMuestras","",0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          /* [Calibrador] */
                                          out.println("<tr>");
                                          out.println(vEti.Texto("EEtiqueta","Calibrador:"));
                                          out.println("<td colspan=\"3\" class=\"ETabla\">");

                                          Vector vcCtrolCalib = new Vector();
                                          TDTOXCtrolCalibra dCtrolCalib = new  TDTOXCtrolCalibra();
                                          vcCtrolCalib = dCtrolCalib.FindByAll2(""," order by iCveCtrolCalibra");
                                          out.print(vEti.SelectOneRowSinTD("iCveCtrolCalibra","",vcCtrolCalib,"iCveCtrolCalibra","cDscCtrolCalibra",request,"0"));

                                          out.println("</td></tr>");
                                          /*out.print(vEti.EtiCampoCS("EEtiqueta","Calibrador:","ECampo","text",10,10,3,"iCveCtrolCalibra","&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");*/

                                          /* [Observacion] */
                                          out.println("<tr>");
                                          out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observación:","ECampo",80,6,3,"cObservacion","",0,"","fMayus(this);",true,true,true));
                                          out.println("</tr>");

                                          /*out.println("<tr>");
                                          out.print("<A HREF=\"pg070306071.jsp\">Ir a</a>");
                                          out.println("</tr>");*/




/**************** MODIFICAR Y DESPLEGAR ****************/

                               }
                               else{      // Para Desplegar o Para Modificar
                                    if (bs != null){

                                          out.println("<tr>");

                                           /* [Año] */
                                          if (lCaptura) {
                                              out.println(vEti.Texto("EEtiqueta","Año:"));
                                              %>
                                              <td class="ETabla">
                                              <select name="iAnio" size="1">
                                              <%
                                                   //if (bs.getFieldValue("iCveReactivo")!=null&& bs.getFieldValue("iCveReactivo").toString().compareToIgnoreCase(new Integer(vReactivo.getICveReactivo()).toString())==0){
                                                   //  out.print("<option value = "+ vReactivo.getICveReactivo() + " selected>" + vReactivo.getCDscReactivo() + "</option>");


                                                   for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                                   if (bs.getFieldValue("iAnio")!=null&&bs.getFieldValue("iAnio").toString().compareToIgnoreCase(new Integer(i).toString())==0){
                                                    out.print("<option value = " + i + " selected>" + i + "</option>");
                                                   }
                                                   else
                                                       out.print("<option value = " + i + ">" + i + "</option>");
                                                   }
                                              %>
                                              </select>
                                              </td>
                                              <%
                                          } else out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",50,50,"iAnio", bs.getFieldValue("iAnio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));

                                          if (lCaptura) {
                                              out.println(vEti.Texto("EEtiqueta","Laboratorio:"));
                                              %>
                                              <td class="ETabla">
                                              <%
                                              out.println(vEti.SelectOneRowSinTD("iCveLaboratorio", "fCambiaLab('" + clsConfig.getAccionOriginal() + "');", (Vector) AppCacheManager.getColl("GRLUniMed",""), "iCveUniMed", "cDscUniMed", request, bs.getFieldValue("iCveLaboratorio").toString()));
                                              out.println("</td>");
                                          } else out.print(vEti.EtiCampo("EEtiqueta","Laboratorio:","ECampo","text",50,50,"cDscUniMed", bs.getFieldValue("cDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          /* Parametro de Validacion */
                                           out.println("<tr>");
                                           out.print(vEti.EtiCampoCS("EEtiqueta","Parametro de Validación:","ECampo","text",10,10,3,"iCveValPres",bs.getFieldValue("iCveValPres","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                           out.println("</tr>");

                                          /* [Fecha] */
                                           out.println("<tr>");
                                           out.print(vEti.EtiCampoCS("EEtiqueta","Fecha:","ECampo","text",10,10,3,"dtEstudio",bs.getFieldValue("cDtEstudio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                           out.println("</tr>");

                                         /* [Equipo] */
                                          out.println("<tr>");
                                          if (lCaptura) {
                                                out.println(vEti.Texto("EEtiqueta","Equipo:"));
                                                out.println("<td colspan=\"3\" class=\"ETabla\">");
                                                Vector vcEquipos = new Vector();
                                                DAOEQMEquipo dEquipos = new  DAOEQMEquipo();
                                                vcEquipos = dEquipos.FindByAll();

                                                out.print(vEti.SelectOneRowSinTD("iCveEquipo","",vcEquipos,"iCveEquipo","cDscEquipo",request,bs.getFieldValue("iCveEquipo","&nbsp;").toString()));
                                                out.println("</td>");
                                          }
                                          else out.print(vEti.EtiCampoCS("EEtiqueta","Equipo:","ECampo","text",10,10,3,"iCveEquipo",bs.getFieldValue("cDscEquipo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          /* [Droga] */
                                          out.println("<tr>");
                                          if (lCaptura) {
                                                out.println(vEti.Texto("EEtiqueta","Droga:"));
                                                out.println("<td colspan=\"3\" class=\"ETabla\">");
                                                Vector vcSustancias = new Vector();
                                                DAOTOXSustancia dSustancias = new  DAOTOXSustancia();
                                                vcSustancias = dSustancias.FindByAll();
                                                out.print(vEti.SelectOneRowSinTD("iCveSustancia","",vcSustancias,"iCveSustancia","cDscSustancia",request,bs.getFieldValue("iCveSustancia","&nbsp;").toString()));
                                                out.println("</td>");

                                          } else out.print(vEti.EtiCampoCS("EEtiqueta","Droga:","ECampo","text",10,10,3,"iCveSustancia",bs.getFieldValue("cDscSustancia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          /* [Corte] */
                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Corte:","ECampo","text",13,13,3,"dCorte",bs.getFieldValue("dCorte","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          /* [Negativo] */
                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Negativo:","ECampo","text",13,13,3,"dCorteNeg",bs.getFieldValue("dCorteNeg","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          /* [Positivo] */
                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Positivo:","ECampo","text",13,13,3,"dCortePost",bs.getFieldValue("dCortePost","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          /* [No. Muestras]*/
                                          if (lCaptura ) {
                                               out.println("<tr>");
                                               out.print(vEti.EtiCampoCS("EEtiqueta","No. Muestras:","ECampo","text",10,10,3,"iMuestras","&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                               out.println("</tr>");
                                          }


                                          /* [Calibrador] */
                                          out.println("<tr>");
                                          if (lCaptura){
                                                out.println(vEti.Texto("EEtiqueta","Calibrador:"));
                                                out.println("<td colspan=\"3\" class=\"ETabla\">");
                                                Vector vcCtrolCalib = new Vector();
                                                TDTOXCtrolCalibra dCtrolCalib = new  TDTOXCtrolCalibra();
                                                vcCtrolCalib = dCtrolCalib.FindByAll2(""," order by iCveCtrolCalibra");
                                                out.print(vEti.SelectOneRowSinTD("iCveCtrolCalibra","",vcCtrolCalib,"iCveCtrolCalibra","cDscCtrolCalibra",request,bs.getFieldValue("iCveCtrolCalibra").toString()));
                                                out.println("</td>");
                                          } else out.print(vEti.EtiCampoCS("EEtiqueta","Calibrador:","ECampo","text",10,10,3,"iCveCtrolCalibra",bs.getFieldValue("cDscCtrolCalibra","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          /* [Observacion] */
                                          out.println("<tr>");
                                          out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observación:","ECampo",80,6,3,"cObservacion",bs.getFieldValue("cObservacion","&nbsp;").toString(),0,"","fMayus(this);",true,true,lCaptura));
                                          out.println("</tr>");

                                    }
                                     else{
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
<% vEntorno.clearListaImgs();
    String cBoton = "";
    cBoton = request.getParameter("hdBoton");
    if ( cBoton.compareTo("null") !=0 && cBoton.compareToIgnoreCase("Guardar") ==0){
        int icveVal = clsConfig.getICveValPres();
        cPagina = "pg070306071.jsp?hdCampoClave4=" +  icveVal + "&hdBoton=Nuevo";
   %>
      <jsp:forward page="<%=cPagina%>"/>
   <%
    }


%>
</html>