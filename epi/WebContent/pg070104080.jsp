<%/**
 * Title: Selección de Expediente para los Servicios de Laboratorio Clínico
 * Description: Selección de Expediente para los Servicios de Laboratorio Clínico
 * Copyright: 2006
 * Company: Micros Personales SA de CV
 * @author Itzia María del Camen Sánchez Méndez
 * @version 1
 * Clase: pg070104080
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.msc.*" %>
<%@ page import="com.micper.seguridad.dao.*" %>
<%@ page import="com.micper.excepciones.*" %>

<html>
<%
  pg070104080CFG  clsConfig = new pg070104080CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104080.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104080.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070104080.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Grupo|Inicio vigencia|Fin vigencia|Vigente|";    // modificar
  String cCveOrdenar  = "cDscGrupo|dtInicio|dtFin|lVigente|";  // modificar
  String cDscFiltrar  = "Inicio vigencia|Fin vigencia|";    // modificar
  String cCveFiltrar  = "p.dtInicio|p.dtFin|";  // modificar
  String cTipoFiltrar = "5|5|";                // modificar 7/8
  boolean lFiltros    = false;                  // modificar
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
  BeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = "Hidden";//clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  String cNumAnalisis = request.getParameter("hdNumAnalisis") == null || request.getParameter("hdNumAnalisis").equals("") || request.getParameter("hdNumAnalisis").equals("null") ? "" : request.getParameter("hdNumAnalisis");

  //System.out.println("········ cNumAnalisis: " + cNumAnalisis);

  int iCveUniMed =  Integer.parseInt(request.getParameter("iCveUniMed")==null||request.getParameter("iCveUniMed").equals("")||request.getParameter("iCveUniMed").equals("null")?"0":request.getParameter("iCveUniMed"));
  int iCveModulo =  Integer.parseInt(request.getParameter("iCveModulo")==null||request.getParameter("iCveModulo").equals("")||request.getParameter("iCveModulo").equals("null")?"0":request.getParameter("iCveModulo"));
  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("EPIProceso"));
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"PermCombos.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  // Agregado RAFAEL ALCOCER CALDERA 02/Oct/2006
  // Variable global para hacer referencia a la subventana
  var newWindow;

  // Agregado RAFAEL ALCOCER CALDERA 02/Oct/2006
  // Generar y llenar la nueva ventana
  function makeNewWindow() {
    form = document.forms[0];

    if (!(form.iCveUniMed.value == -1)) {
         if (!(form.iCveModulo.value == -1)) {
              if (!(form.iCveServicio.value == -1)) {
                   if (!(form.iCveExpediente.value == "")) {
                   	// asegurarse de que no se habia abierto
       			if (!newWindow || newWindow.closed) {
           			newWindow = window.open("", "", "width=600,height=300,status=no,resizable=yes,menubar=yes,titlebar=yes,top=100,left=400,screenY=100,screenX=400,scrollbars=yes");
                       		// retardar escritura hasta que la ventana exista en IE/Windows
           			setTimeout("writeToWindow()", 50);
           			newWindow.focus();
           			form.hdGenerarNumAnalisis.value = 'GenerarNumAnalisis';
       			} else if (newWindow.focus) {
           			// la ventana ya estaba abierta y con focus para traerla al frente
           			newWindow.focus();
           			form.hdGenerarNumAnalisis.value = 'GenerarNumAnalisis';
       			}

                        //alert("form.hdNumAnalisis.value: " + form.hdNumAnalisis.value);
                        newWindow.document.write('No. de Análisis: ');
                        newWindow.document.write('<br/>');
                        newWindow.document.write(form.hdNumAnalisis.value);
       			newWindow.focus();
       			form.submit();
                   } else {
                        alert("Ingrese un No. de Expediente");
                   }
              } else {
                   alert("Seleccione un Servicio");
              }
         } else {
              alert("Seleccione un Módulo");
         }
    } else {
         alert("Seleccione la Unidad Médica");
    }
  }

  // Agregado RAFAEL ALCOCER CALDERA 02/Oct/2006
  function writeToWindow() {
      form = document.forms[0];

      // crear la nueva ventana
      var cPagina='<html><head><title>Código de Barras</title></head>'+
                  '<body>'+
                  '<form method="post" action="barcode4j.jsp?'+
                  'numAnalisis='+form.hdNumAnalisis.value+
                  '" name="formBarCode">'+
                  //'No. de Análisis'+
                  '<br/>'+
                  //'<input type="text" name="txtNumAnalisis"/>'+
                  '<br/>'+
                  '<input type="submit" value="Configurar Código de Barras"/>'+
                  '</form>'+
                  '</body>'+
                  '</html>';
      // escribir HTML al documento de la nueva ventana
      newWindow.document.write(cPagina);
      newWindow.document.close( ); // cerrar el stream
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
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave  = ""+bs.getFieldValue("iCveEspecialidad", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">

  <input type="hidden" name="iNumExamen" value="">
  <input type="hidden" name="iCvePerfil" value="">
  <input type="hidden" name="iCvePersonal" value="">
  <input type="hidden" name="iCveProceso" value="<%=vParametros.getPropEspecifica("EPIProceso")%>">
  <input type="hidden" name="tpoBusqueda" value="unMedico">
  <input type="hidden" name="ramaInicial" value="0">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="hdGenerarNumAnalisis">

  </html>
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td valign="top">
    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="5">Recepción en el Laboratorio de Análisis Clínicos</td></tr>
      <tr>
        <td class="EEtiqueta">Unidad M&eacute;dica:</td>
        <td class="ETabla">
          <%= vEti.SelectOneRowSinTD("iCveUniMed","{forma = document.forms[0];fActualizaCombo('3',forma,this,forma.iCveModulo,this.value,0," + iCveProceso + " );}",  vUsuario.getVUniMed(),"iClave","cDescripcion",request,"0",true)%>
        </td>
        <td class="EEtiqueta">M&oacute;dulo:</td>
        <td class="ETabla"><%=vEti.SelectOneRowSinTD("iCveModulo","{forma = document.forms[0];fActualizaCombo('5',forma,this,forma.iCveServicio,forma.iCveUniMed.value,this.value," + iCveProceso + " );}",  vUsuario.getModuloFUP(iCveUniMed,iCveProceso),"iClave","cDescripcion",request,"0",true)%>
        </td>
      </tr>
      <tr>
        <td class="EEtiqueta">Servicio:</td>
        <td class="ETabla" colspan="3">
          <%= vEti.SelectOneRowSinTD("iCveServicio","",  new TDGRLUSUMedicos().findServEsp(vUsuario.getICveusuario(),iCveUniMed,iCveProceso,iCveModulo, "EPIServLab"),"iClave","cDescripcion",request,"0",true)%>
        </td>
      </tr>
      <tr>
        <td class="EEtiqueta" colspan="1">Expediente:</td>
        <% out.print(vEti.CeldaCampo("ETabla", "text", 30, 9, "iCveExpediente", "", 0, "", "", true, true, true, request));%>
        <td class="ETabla"><%
           out.print(vEti.clsAnclaTexto("EAnclaC","Buscar expediente","javascript:fBuscar();","Buscar")); %>
        </td><td class="ETabla"><%
           out.print(vEti.clsAnclaTexto("EAncla","Buscar Personal","JavaScript:fSelPer();", "Buscar Personal","")); %>
       </td></tr>
     <!-- Agregado por Rafael Alcocer Caldera 26/Oct/2006 -->
     <!-- *********************************************** -->
     <tr>
       <td class="ETablaC" colspan="4">
            <%
              out.print(vEti.clsAnclaTexto("EAncla","Generar No. de Análisis", "JavaScript:makeNewWindow();", "Generar No. de Análisis", "GenerarNumAnalisis"));

               TFechas t = new TFechas();
               int y = t.getIntYear(t.TodaySQL());
               GenerarNumAnalisis generarNumAnalisis = new GenerarNumAnalisis();

               if (request.getParameter("iCveUniMed") != null) {
                    TDEXPCtrolLab dExpCtrolLab = new TDEXPCtrolLab();
                    TDPERDatos dPerDatos = new TDPERDatos();
                    Vector vectorExpCtrolLab = new Vector();
                    TVExpCtrolLab vExpCtrolLab = new TVExpCtrolLab();

                    try {
                    	vectorExpCtrolLab = dExpCtrolLab.findByMaxConsecutivo();

                    	// Aqui solo voy a obtener un solo registro
                    	for (int i = 0; i < vectorExpCtrolLab.size(); i++) {
                             vExpCtrolLab = (TVExpCtrolLab)vectorExpCtrolLab.get(i);
                    	}
                    } catch (DAOException ex) {
                    	ex.printStackTrace();
                    }

                    if (request.getParameter("iCveExpediente") != null && (!request.getParameter("iCveExpediente").equals("null"))) {
                         TVPERDatos vPerDatos = dPerDatos.findUserbyExp(Integer.parseInt(request.getParameter("iCveExpediente")));

                         if (vPerDatos != null) {
                             //System.out.println("vPerDatos.getICveExpediente(): " + vPerDatos.getICveExpediente());
                             //System.out.println("vExpCtrolLab.getICveExpediente(): " + vExpCtrolLab.getICveExpediente());

                             if (vPerDatos.getICveExpediente() > 0 && !(vPerDatos.getCNombreCompleto().compareToIgnoreCase("") == 0)) {
                                 //System.out.println("getICveAnalisis: " + vExpCtrolLab.getICveAnalisis());

                                 String cDigitos = String.valueOf(vExpCtrolLab.getICveAnalisis());

                                 if (cDigitos.length() == 9) {
                                     cDigitos = cDigitos.substring(3, 9);
                         	     //System.out.println("cDigitos(9): " + cDigitos);
                                 } else if (cDigitos.length() == 10) {
                         	     cDigitos = cDigitos.substring(4, 10);
                         	     //System.out.println("cDigitos(10): " + cDigitos);
                                 }

                                 cNumAnalisis = generarNumAnalisis.getSigNumAnalisis("" + y, request.getParameter("iCveUniMed"), cDigitos);

                                 %>
                                 <input type="hidden" name="hdNumAnalisis" value="<%= cNumAnalisis %>">
                                 <%
/*
                               //        System.out.println("%%%%%%%%%");
                               //        System.out.println("getINextConsecutivo: " + dExpCtrolLab.getINextConsecutivo());
                               //        System.out.println("y: " + y);
                               //        System.out.println("iCveUniMed: " + iCveUniMed);
                               //        System.out.println("iCveModulo: " + iCveModulo);
                               //        System.out.println("Integer.parseInt(cNumAnalisis): " + Integer.parseInt(cNumAnalisis));
                               //        System.out.println("Integer.parseInt(iCveExpediente): " + Integer.parseInt(request.getParameter("iCveExpediente")));
                               //        System.out.println("vUsuario.getCUsuario(): " + vUsuario.getCUsuario());
                               //        System.out.println("%%%%%%%%%");

                                 vExpCtrolLab.setIConsAnalisis(dExpCtrolLab.getINextConsecutivo());
                                 vExpCtrolLab.setIAnio(y);
                                 vExpCtrolLab.setICveUniMed(iCveUniMed);
                                 vExpCtrolLab.setICveModulo(iCveModulo);
                                 vExpCtrolLab.setICveAnalisis(Integer.parseInt(cNumAnalisis));
                                 vExpCtrolLab.setICveExpediente(Integer.parseInt(request.getParameter("iCveExpediente")));
                                 vExpCtrolLab.setCUsuaAplicado(vUsuario.getCUsuario());
                                 vExpCtrolLab.setCEstudios("");
                                 vExpCtrolLab.setCMotivo("");
                                 vExpCtrolLab.setDtSolicitud(t.TodaySQL());

                                 dExpCtrolLab.insertExpCtrolLab(null, vExpCtrolLab);

                               //        System.out.println("Inserted: " + dExpCtrolLab.getIInserted());
                                 */

                                 //System.out.println("cNumAnalisis: " + cNumAnalisis);
                             }
                        }
                    }
               }

             %>
       </td>
     </tr>
     <!-- *********************************************** -->
     <tr>
       <td class="ETablaT" >Expediente</td>
       <td class="ETablaT" >RFC</td>
       <td class="ETablaT" >Fecha</td>
     </tr>
     <% // Despliegue de Datos
       if (bs != null){
          String exp = bs.getFieldValue("iCveExpediente","").toString();
          String exm = bs.getFieldValue("iNumExamen","").toString();
          String per = bs.getFieldValue("iCvePersonal","").toString();
          TFechas Fecha = new TFechas();
          %>
          <tr>
           <td class="ETablaC" colspan="4"><%
           out.print(vEti.clsAnclaTexto("EAnclaC","Capturar Servicio",
           "javascript:if(fValidaSelectores())fIr('pg070104030.jsp'," + exp + "," + exm + "," + per + ");","exp"));
           %>
           </td>
           <td class="ETablaC" colspan="1"><%=bs.getFieldValue("cRFC","&nbsp;")%></td>
           <td class="ETablaC" colspan="1"><%=Fecha.getFechaDDMMYYYY(Fecha.getSQLDatefromSQLString(bs.getFieldValue("dtSolicitado",Fecha.TodaySQL().toString()).toString()),"/")%></td>
         </tr>
         <%
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
