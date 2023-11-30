<% /**
 * Title:         Comportamiento de Controles de Calidad para Análisis Presuntivo
 * Description:   Reporte
 * Copyright:     2006
 * Company:       Micros Personales
 * @author        Rafael Alcocer Caldera
 * @version       1.0
 * Clase:         pg0703060110
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.Vector"%>
<html>
<%
  pg0703060110CFG  clsConfig = new pg0703060110CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg0703060110.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg0703060110.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Sustancia|Fecha de Generación|Fecha de Análisis|Fecha de Autorización|";    // modificar
  String cCveOrdenar  = "LC.iCveSustancia|LC.dtGeneracion|LC.dtAnalisis|LC.dtAutorizacion|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cClave2   = "";
  String cClave3   = "";
  String cClaveA    = "";
  String cClaveB   = "";
  String cClaveC   = "";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  // Con esta funcion se obtiene el valor del Checkbox
  function getCheckValue(objeto) {
    return objeto.checked ? objeto.value : 0;
  }

  // Con esta funcion se obtiene el valor del Radio Button
  function getRadioValue(objeto) {
    var iRadio = 0 ;

    for(i=0; i < objeto.length; i++ ) {
      if (objeto[i].checked) {
        iRadio = parseInt(objeto[i].value);
      }
    }

    return iRadio;
  }

  // Con esta funcion se obtiene el valor del Combo
  function getComboValue(objeto) {
    return objeto[objeto.selectedIndex].value;
  }

  // Abrir nueva ventana para generar el reporte de Excel
  function generaXLS(tipo) {
    form = document.forms[0];
    form.target = "_self";
    var lICveLaboratorio = false;
    var lDtFechaI = false;
    var lDtFechaF = false;
    var lICveEquipo = false;

    if (form.iCveLaboratorio.selectedIndex == 0) {
        lICveLaboratorio = false;
        alert("Seleccione un Laboratorio");
    } else {
        lICveLaboratorio = true;
    }

    if (form.dtFechaI.value == "") {
      lDtFechaI = false;
      alert("Seleccione una Fecha Inicial");
    } else {
      lDtFechaI = true;
    }

    if (form.dtFechaF.value == "") {
      lDtFechaF = false;
      alert("Seleccione una Fecha Final");
    } else {
      lDtFechaF = true;
    }

    for (i = 0; i < form.iCveEquipo.length; i++) {
      if (form.iCveEquipo[i].checked == false) {
          lICveEquipo = false;
      } else {
          lICveEquipo = true;
          break;
      }
    }

    if (lICveEquipo == false) {
      alert("Seleccione un Equipo");
    }

    if (lICveLaboratorio == true &&
        lDtFechaI == true &&
        lDtFechaF == true &&
        lICveEquipo == true) {

        if (tipo == 1) {
            form.hdReporte.value = 'Reporte';
            cPagina= "<html><head><title>Reporte Excel</title></head>" +
            "  <b>INSTRUCCIONES </b>" +
            " <br> 1.Presione el Botón <b>Generar Excel</b> para obtener el reporte. " +
            " <br> 2.Espere que se presente la ventana <b>Descarga de Archivos</b>." +
            " <br> 3.Presione el botón Abrir y aguarde un momento para que el archivo sea presentado.  " +
            " <body>"+
            "<form method=\"post\" action=\"servXLSpg0703060110?iCveLaboratorio=" + form.iCveLaboratorio.value + "&cLote=" + getComboValue(form.cLote) + "&dtFechaI=" + form.dtFechaI.value + "&dtFechaF=" + form.dtFechaF.value + "&iCveEquipo=" + getRadioValue(form.iCveEquipo) + "&cRespuesta=" + form.cRespuesta.value + "\"" +
            " enctype=\"multipart/form-data\">" +
            " <input type=\"submit\" value=\"Generar Excel\"> " +
            " </form>" +
            " <br> Favor de esperar a que se presente el archivo de Excel ...  " +
            " </body>" +
            "</html>";
            wExp = window.open("", "", "width=600,height=200,status=no,resizable=yes,menubar=yes,titlebar=yes,top=200,left=200,screenY=200,screenX=200,scrollbars=yes");
            wExp.document.write(cPagina);
        }

        //form.submit();
    }
  }

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
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdCDscSustancia">
  <input type="hidden" name="hdReporte">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
      /*
       if("Reporte".compareToIgnoreCase(clsConfig.getAccion()) ==0) {
           out.println(clsConfig.getActiveX());
       }
       */
  %>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td><input type="hidden" name="hdBoton" value=""></td>
     <td valign="top">
        <!-- ############################## -->
        <!-- TABLA REFERENTE A LA SUSTANCIA -->
        <!-- ############################## -->
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr><td colspan="8" class="ETablaT">Comportamiento de Controles de Calidad para An&aacute;lisis Presuntivo</td></tr>
          <tr><%out.print(vEti.Texto("EEtiqueta", "Año:"));%><td>
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
            <%
              out.println(vEti.Texto("EEtiqueta","Laboratorio:"));
              TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
              TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
              TVGRLUMUsuario vUMUsuario = new TVGRLUMUsuario();
              Vector vUMusuario = new Vector();

              vUMusuario = dUMUsuario.getUniMedxUsu2(vUsuario.getICveusuario()," AND iCveProceso = " + vParametros.getPropEspecifica("ToxProcesoLab"));
              out.println("<td class='ECampo'>");
              out.println("<SELECT NAME=\"iCveLaboratorio\" SIZE=\"1\" onChange=\"\">");
              out.println("<option value=\"0\" selected>Seleccione...</option>");
              if (vUMusuario.size()>0){
                 for (int i = 0; i< vUMusuario.size();i++){
                     vUMUsuario = (TVGRLUMUsuario)vUMusuario.get(i);
                     if (request.getParameter("iCveLaboratorio")!=null&&request.getParameter("iCveLaboratorio").toString().compareTo(vUMUsuario.getICveUniMed()+"")==0)
                        out.println("<option value=\""+vUMUsuario.getICveUniMed()+"\" selected>"+vUMUsuario.getCDscUniMed()+"</option>");
                     else
                        out.println("<option value=\""+vUMUsuario.getICveUniMed()+"\">"+vUMUsuario.getCDscUniMed()+"</option>");
                 }
              }else
                  out.println("<option value=\"-1\">Datos no disponibles</option>");
              out.println("</SELECT>");
              out.println("</td>");
            %>
          </tr>
          <tr>
             <!-- Modificado por Rafael Alcocer Caldera -->
             <td class="EEtiqueta">Código del control:</td>
             <td class="ECampo"><%
                  //out.println(vEti.SelectOneRowSinTD("SLSSustancia", "", (Vector) AppCacheManager.getColl("TOXReactivo",""), "iCveRactivo", "iCodigo", true, request, "0", true));
                  //out.println(vEti.SelectOneRowSinTD("SLSSustancia", "", (Vector) AppCacheManager.getColl("TOXSustancia",""), "iCveSustancia", "cDscSustancia", true, request, "0", true));
                  TDTOXCtrolCalibra dToxCtrolCalibra = new TDTOXCtrolCalibra();
                  Vector vcToxCtrolCalibra = dToxCtrolCalibra.FindByAll(" where C.lCual = 1 ", " order by C.cLote ");
                  out.println(vEti.SelectOneRowSinTD("cLote", "", vcToxCtrolCalibra, "cLote", "cLote", true, request, "0", true));
             %>
             </td>
             <%
                  out.print(vEti.EtiCampo("EEtiqueta", "Respuesta:", "ECampo", "text", 33, 33, "cRespuesta", "", 3, "", "", true, true, true));
             %>
             </td>
          </tr>
          <tr><%
                  out.println(vEti.EtiCampo("EEtiqueta","Fecha Inicial:", "ETabla","text",10,10,"dtFechaI","",0,"","",true,true, true, request));
                  out.println(vEti.EtiCampo("EEtiqueta","Fecha Final:", "ETabla","text",10,10,"dtFechaF","",0,"","",true,true, true, request));
           %></tr>
        </table>
        <!-- ######################### -->
        <!-- TABLA REFERENTE AL EQUIPO -->
        <!-- ######################### -->
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
            <td colspan="7" class="ETablaT">Equipos</td>
          </tr>
            <%
            	if (bs != null){
                    bs.start();
                    out.println("<tr>");
                    out.println("<td class=\"ETablaT\">Cve. Equipo</td>");
                    out.println("<td class=\"ETablaT\">Descripción</td>");
                    out.println("<td class=\"ETablaT\">No. Serie</td>");
                    out.println("<td class=\"ETablaT\" colspan=\"2\">Modelo</td>");
                    out.println("</tr>");

                    int count = 0;

                    while(bs.nextRow()){
                    	out.println("<tr>");
                        out.print("<td class=\"ETabla\">" + vEti.ObjRadioSE("ETabla","iCveEquipo",(String)bs.getFieldValue("iCveEquipo", "&nbsp;"),(String)bs.getFieldValue("iCveEquipo", "&nbsp;"),"1","","","",0,true,true) + "</td>");
                    	out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscEquipo", "&nbsp;")));
                    	out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNumSerie", "&nbsp;")));
                    	out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cModelo", "&nbsp;")));
                   }
                } else {
                    out.println("<tr>");
                    out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                    out.println("</tr>");
                }
            %>
        </table>
        <!-- ########################## -->
        <!-- TABLA REFERENTE AL REPORTE -->
        <!-- ########################## -->
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
            <td>
              <% out.println(vEti.clsAnclaTexto("EAncla", "Reporte", "JavaScript:generaXLS(1);", "Reporte"));%>
            </td>
          </tr>
        </table>
        <!-- ########################## -->
      </td>
      </tr>
      <tr>
        <td></td>
      </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

