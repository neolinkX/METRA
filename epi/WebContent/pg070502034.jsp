<%/**
 * Title:         Personal de la Plantilla
 * Description:   Listado que muestra la Informaión del Personal de la Plantilla
 * Copyright:     2004
 * Company:       Micros Personales
 * @author        Marco Antonio Hernández García
 * @version       1.0
 * Clase:         pg070502034
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>

<html>
<%
  pg070502034CFG  clsConfig = new pg070502034CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070502034.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502034.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070502035.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Número|RFC|CURP|Nombre|Apellido Paterno|Apellido Materno|";
  String cCveOrdenar  = "CTRPersonal.iNumPersonal|CTRPersonal.cRFC|CTRPersonal.cCURP|CTRPersonal.cNombre|CTRPersonal.cApPaterno|CTRPersonal.cApMaterno|";
  String cDscFiltrar  = "Número|RFC|CURP|Nombre|Apellido Paterno|Apellido Materno|";
  String cCveFiltrar  = "CTRPersonal.iNumPersonal|CTRPersonal.cRFC|CTRPersonal.cCURP|CTRPersonal.cNombre|CTRPersonal.cApPaterno|CTRPersonal.cApMaterno|";
  String cTipoFiltrar = "7|8|8|8|8|8|";
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
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  String cFiltro = "";
  int iPlantilla = 0;
  String cDscEmpresa = "";
  TDCTRPlantilla dCTRPlantilla  = new TDCTRPlantilla();
  TVCTRPlantilla vCTRPlantilla  = new TVCTRPlantilla();
  Vector vPlantilla = new Vector();
  Vector vAptitud = new Vector();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  // Agregado RAFAEL ALCOCER CALDERA 03/Ago/2006
  function GeneraXLS(){
    form = document.forms[0];
    cPagina='<html><head><title>Archivo Excel</title></head>'+
            '<body>'+
            '<form method="post" action="servCtrUpXLS?icveempresa='+form.hdIni.value+'&icveplantilla='+form.hdIni2.value+'" name="formXLS" enctype="multipart/form-data">'+
              'Seleccione el archivo: <input type="file" name="fileName"/>'+
              '<br />'+
              '<input type="submit" value="Cargar archivo"/>'+
            '</form>'+
            '</body>'+
            '</html>';
    wExp = window.open("", "", "width=600,height=200,status=no,resizable=yes,menubar=yes,titlebar=yes,top=200,left=200,screenY=200,screenX=200,scrollbars=yes");
    wExp.document.write(cPagina);
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
       iPlantilla = new Integer(bs.getFieldValue("iCvePlantilla", "0").toString()).intValue();
       cDscEmpresa = bs.getFieldValue("cDscEmpresa", "&nbsp;").toString();
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="SLSUniMed" value="<%if (request.getParameter("SLSUniMed") != null) out.print(request.getParameter("SLSUniMed"));%>">
  <input type="hidden" name="SLSMdoTransporte" value="<%if (request.getParameter("SLSMdoTransporte") != null) out.print(request.getParameter("SLSMdoTransporte"));%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdIni" value="<%  /*EMPRESA*/   if (request.getParameter("hdIni") != null) out.print(request.getParameter("hdIni"));%>">
  <input type="hidden" name="cDscEmpresa" value="<% if (request.getParameter("cDscEmpresa") != null && request.getParameter("cDscEmpresa").toString().compareTo("") != 0 && request.getParameter("cDscEmpresa").toString().compareTo("null") != 0) out.print(request.getParameter("cDscEmpresa")); else out.print(cDscEmpresa);%>">
  <input type="hidden" name="hdIni2" value="<% /*PLANTILLA*/ if (request.getParameter("hdIni2") != null) out.print(request.getParameter("hdIni2"));%>">
  <input type="hidden" name="iNumPersonal" value="<% if (request.getParameter("iNumPersonal") != null) out.print(request.getParameter("iNumPersonal"));%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
        cFiltro =  " WHERE CTRPlantilla.iCveEmpresa = " + request.getParameter("hdIni") +
                   "   AND CTRPlantilla.iCvePlantilla = " + request.getParameter("hdIni2");
        vPlantilla = (Vector)dCTRPlantilla.FindByAll(cFiltro,"");
        if (vPlantilla.size()>0){
           vCTRPlantilla = (TVCTRPlantilla)vPlantilla.get(0);
           iPlantilla = vCTRPlantilla.getiCvePlantilla();
           cDscEmpresa = vCTRPlantilla.getCDscEmpresa();
        }

  %>
    <tr>
      <td valign="top">
          <input type="hidden" name="hdBoton" value="">
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="2">Datos del Transportista</td>
            </tr>
            <tr>
              <td class="EEtiqueta">Plantilla</td> <td class="ETabla"><%=iPlantilla%></td>
            </tr>
            <tr>
              <td class="EEtiqueta">Nombre o <br> Razón Social</td> <td class="ETabla"><%=cDscEmpresa%></td>
            </tr>
          </table>
          &nbsp;
          <!-- Agregado RAFAEL ALCOCER CALDERA 03/Ago/2006 -->
          <!-- ******************************************* -->
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class=\"ECampo\">
              	<%
                  out.print(vEti.clsAnclaTexto("EAncla","Cargar archivo...","JavaScript:GeneraXLS();","XLS"));
              	%>
              </td>
            </tr>
          </table>
          <!-- ******************************************* -->
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="8">Personal de la Plantilla</td>
            </tr>
            <tr>
              <td class="ETablaT">No.</td>
              <td class="ETablaT">RFC</td>
              <td class="ETablaT">CURP</td>
              <td class="ETablaT">Nombre</td>
              <td class="ETablaT">Puesto</td>
              <td class="ETablaT">Licencia</td>
              <td class="ETablaT" colspan="2">Aptitud</td>
            </tr>
            <%
               if (bs != null){
                   bs.start();
                   while(bs.nextRow()){
                       out.println("<tr>");
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iNumPersonal", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cRFC", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cCURP", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNombre", "&nbsp;") + " " +
                                                         bs.getFieldValue("cApPaterno", "&nbsp;") + " " +
                                                         bs.getFieldValue("cApMaterno", "&nbsp;")));
                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("cDscPuesto", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cLicencia", "&nbsp;")));

                       if (new Integer(bs.getFieldValue("iCveExpediente", "").toString()).intValue() != 0){
                          vAptitud = clsConfig.fillAptitud(new Integer(bs.getFieldValue("iCveEmpresa", "&nbsp;").toString()).intValue(),
                                                           new Integer(bs.getFieldValue("iCvePlantilla", "&nbsp;").toString()).intValue(),
                                                           new Integer(bs.getFieldValue("iNumPersonal", "&nbsp;").toString()).intValue());
                          if (!vAptitud.isEmpty()){
                             TVCTRPersonal VCTRPersonal = new TVCTRPersonal();
                             VCTRPersonal = (TVCTRPersonal) vAptitud.get(0);
                             if (VCTRPersonal.getICP() == 1)
                                out.print(vEti.Texto("ETabla",""+ "Personal Apto"));
                             if (VCTRPersonal.getICP() == 0)
                                out.print(vEti.Texto("ETabla",""+ "Personal No Apto"));
                             if (VCTRPersonal.getICP() != 1 && VCTRPersonal.getICP() != 0)
                                out.print(vEti.Texto("ETabla",""+ "Sin Información"));
                         }
                       } else
                         out.print(vEti.Texto("ETabla",""+ "Sin Información"));
                       if (clsConfig.getLPagina(cCatalogo)){
                           out.print("<td class=\"ECampo\">");
                           out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" +
                                                       bs.getFieldValue("iCveEmpresa","") + "','" +
                                                      bs.getFieldValue("iCvePlatilla","") + "','" +
                                                      bs.getFieldValue("iNumPersonal","") + "'," + "'pg070502034');","Ir a..."));
                           out.print("</td>");
                       }
                   }
               }else{
                       out.println("<tr>");
                       out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 6,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                       out.println("</tr>");
                   }

            %>
          </table>
      </td>
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

