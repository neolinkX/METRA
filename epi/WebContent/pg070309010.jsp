<% /**
 * Title:         Nota Medica Psicologia
 * Description:   Reporte
 * Copyright:     2006
 * Company:
 * @author        Ing. Rafael Alcocer Caldera
 * @version       1.0
 * Clase:         pg070309010
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
  pg070309010CFG  clsConfig = new pg070309010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070309010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070309010.jsp\" target=\"FRMCuerpo"); // modificar
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

    var lNumExpediente = false;
    var lNumExamen = false;
    var lServicio = false;
    var lDtFecha = false;

    if (form.numExpediente.value == "") {
         lNumExpediente = false;
         alert("Ingrese el No. de Expediente");
    } else {
         lNumExpediente = true;
    }

    if (form.numExamen.value == "") {
         lNumExamen = false;
         alert("Ingrese el No. de Examen");
    } else {
         lNumExamen = true;
    }

    if (form.iCveServicio.selectedIndex == 0) {
        lServicio = false;
        alert("Seleccione un Servicio");
    } else {
        lServicio = true;
    }

    if (form.dtFecha.value == "") {
      lDtFecha = false;
      alert("Seleccione una Fecha");
    } else {
      lDtFecha = true;
    }

    if (lNumExpediente == true &&
        lNumExamen == true &&
        lServicio == true &&
        lDtFecha == true) {

        if (tipo == 1) {
        	form.hdReporte.value = 'Reporte';
            cPagina= "<html><head><title>Reporte Excel</title></head>" +
            "  <b>INSTRUCCIONES </b>" +
            " <br> 1.Presione el Botón <b>Generar Excel</b> para obtener el reporte. " +
            " <br> 2.Espere que se presente la ventana <b>Descarga de Archivos</b>." +
            " <br> 3.Presione el botón Abrir y aguarde un momento para que el archivo sea presentado.  " +
            " <body>"+
            "<form method=\"post\" action=\"servXLSpg070309010?numExpediente=" + form.numExpediente.value + + "&numExamen=" + form.numExamen.value + "&iCveServicio=" + getComboValue(form.iCveServicio) + "&dtFecha=" + form.dtFecha.value + "\"" +
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
        <!-- ############## -->
        <!-- TABLA DE DATOS -->
        <!-- ############## -->
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr><td colspan="4" class="ETablaT">NOTA MÉDICA</td></tr>
          <tr>
            </td>
             <%
                  out.print(vEti.EtiCampo("EEtiqueta", "No. Expediente:", "ECampo", "text", 33, 33, "numExpediente", "", 3, "", "", true, true, true, request));
             %>
            </td>
            </td>
             <%
                  out.print(vEti.EtiCampo("EEtiqueta", "No. Examen:", "ECampo", "text", 33, 33, "numExamen", "", 3, "", "", true, true, true, request));
             %>
            </td>
          </tr>
          <tr>
            <td class="EEtiqueta">Servicio:</td>
            <td class="ECampo">
                 <%
                  TDMEDServicio dMedServicio = new TDMEDServicio();
                  Vector vcMedServicio = dMedServicio.FindByAll("");
                  out.println(vEti.SelectOneRowSinTD("iCveServicio", "", vcMedServicio, "iCveServicio", "cDscServicio", true, request, "0", true));
                  out.println(vEti.EtiCampo("EEtiqueta","Fecha:", "ETabla","text",10,10,"dtFecha","",0,"","",true,true, true, request));
                 %>
            </td>
          </tr>
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
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
            <td colspan="2" class="ETablaT">NOTA MÉDICA ANTERIOR</td>
            <td colspan="4" class="ETablaT">NOTA MÉDICA ACTUAL</td>
          </tr>
          <tr>
            <!-- #################### -->
            <!-- NOTA MEDICA ANTERIOR -->
            <!-- #################### -->
            <td>
              <%
                TDEXPServicio dExpServicio = new TDEXPServicio();
                StringBuffer bufferNotaMedicaAnterior = new StringBuffer();

                if (request.getParameter("numExpediente") != null && !(request.getParameter("numExpediente").compareToIgnoreCase("") == 0) &&
                    request.getParameter("numExamen") != null && !(request.getParameter("numExamen").compareToIgnoreCase("") == 0) &&
                    request.getParameter("iCveServicio") != null && !(request.getParameter("iCveServicio").compareToIgnoreCase("") == 0)) {

                         Vector vcExpServicio = dExpServicio.getNotaMed(request.getParameter("numExpediente"),
                                                                        request.getParameter("numExamen"),
                                                                        request.getParameter("iCveServicio"));
                         TVEXPServicio vexpServicio = new TVEXPServicio();

                	 for (int i = 0; i < vcExpServicio.size(); i++) {
                    		vexpServicio = (TVEXPServicio)vcExpServicio.get(i);
                    		bufferNotaMedicaAnterior.append(vexpServicio.getCNotaMedica());
                    		bufferNotaMedicaAnterior.append("\n");
                	 }
                }

                out.println(vEti.EtiAreaTextoSINEtiqueta("ETabla", 60, 20, "notaAnterior", bufferNotaMedicaAnterior.toString(), 0, "", "", true, true, true));
               %>
            </td>
            <!-- ################## -->
            <!-- NOTA MEDICA ACTUAL -->
            <!-- ################## -->
            <td>
              <% out.println(vEti.EtiAreaTextoSINEtiqueta("ETabla", 60, 20, "notaActual", "", 0, "", "", true, true, true)); %>
            </td>
          </tr>
        </table>
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
