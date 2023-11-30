<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>


<html>
<%
  pg070306021CFG  clsConfig = new pg070306021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070306021.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306021.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cClave3    = "";
  String cPosicion  = "";
  String cPropiedad = "";


  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción Breve|";    // modificar
  String cCveOrdenar  = "C.iCveCtrolCalibra|C.cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave|Descripción Breve|";    // modificar
  String cCveFiltrar  = "C.iCveCtrolCalibra|C.cDscBreve|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Reporte";             // modificar
  TFechas tf = new TFechas();
  java.sql.Date d = null;
  String fechaFormateada = "";


  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  if (bs!=null && ((bs.getFieldValue("lAgotado","0")!=null && bs.getFieldValue("lAgotado","0").toString().compareTo("1")==0) ||
                   (bs.getFieldValue("lBaja","0")!=null && bs.getFieldValue("lBaja","0").toString().compareTo("1")==0)))
     cUpdStatus = "AddOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();

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
       form.target="_self";

       if (tipo == 1) {
           if (form.iCuantCual.checked) {
               form.hdReporte.value = 'Analisis Confirmatorio';
               form.hdBoton.value = 'Reporte';
               //form.submit();
               cPagina= "<html><head><title>Reporte Excel</title></head>" +
               "  <b>INSTRUCCIONES </b>" +
               " <br> 1.Presione el Botón <b>Generar Excel</b> para obtener el reporte. " +
               " <br> 2.Espere que se presente la ventana <b>Descarga de Archivos</b>." +
               " <br> 3.Presione el botón Abrir y aguarde un momento para que el archivo sea presentado.  " +
               " <body>"+
               "<form method=\"post\" action=\"servXLSpg070306021?hdReporte=" + form.hdReporte.value + "&iCveLaboratorio=" + form.iCveLaboratorio.value + "&hdCLote=" + form.hdCLote.value + "&hdICveReactivo=" + form.hdICveReactivo.value + "\"" +
               " enctype=\"multipart/form-data\">" +
               " <input type=\"submit\" value=\"Generar Excel\"> " +
               " </form>" +
               " <br> Favor de esperar a que se presente el archivo de Excel ...  " +
               " </body>" +
               "</html>";
               wExp = window.open("", "", "width=600,height=200,status=no,resizable=yes,menubar=yes,titlebar=yes,top=200,left=200,screenY=200,screenX=200,scrollbars=yes");
               wExp.document.write(cPagina);
            } else {
                 alert("La casilla de verificación: CG/EM \n no está seleccionada \n No se puede generar este reporte");
            }
       } else if (tipo == 2) {
            if (form.iCual.checked) {
                 form.hdReporte.value = 'Analisis Presuntivo';
                 form.hdBoton.value = 'Reporte';
                 //form.submit(); 
                 cPagina= "<html><head><title>Reporte Excel</title></head>" +
                 "  <b>INSTRUCCIONES </b>" +
                 " <br> 1.Presione el Botón <b>Generar Excel</b> para obtener el reporte. " +
                 " <br> 2.Espere que se presente la ventana <b>Descarga de Archivos</b>." +
                 " <br> 3.Presione el botón Abrir y aguarde un momento para que el archivo sea presentado.  " +
                 " <body>"+
                 "<form method=\"post\" action=\"servXLSpg070306021?hdReporte=" + form.hdReporte.value +
                                                "&iCveLaboratorio=" + form.iCveLaboratorio.value +
                                                "&hdCLote=" + form.hdCLote.value +
                                                "&hdICveReactivo=" + form.hdICveReactivo.value +
                                                "&iCveCtrolCalibra=" + form.hdCampoClave3.value +
                                                "&hdCampoClave3=" + form.hdCampoClave3.value +
                                                "&iAnio=" + form.iAnio.value +
                                                "&cLote=" + form.hdCLote.value +
                                                "&cDscCtrolCalibra=" + form.hdCDscCtrolCalibra.value +
                                                "&cDscBreve=" + form.hdCDscBreve.value +
                                                "&iCveSustancia=" + form.hdICveSustancia.value +
                                                "&dVolumen=" + form.hdDVolumen.value +
                                                "&dConcentracion=" + form.hdDConcentracion.value +
                                                "&iCveEmpleoCalib=" + form.hdICveEmpleoCalib.value +
                                                "&iCuantCual=" + form.hdICuantCual.value +
                                                "&iCual=" + form.hdICual.value +
                                                "&iViales=" + form.hdIViales.value +
                                                "&dtPreparacion=" + form.hdDtPreparacion.value +
                                                "&dtCaducidad=" + form.hdDtCaducidad.value +
                                                "&dtAutoriza=" + form.hdDtAutoriza.value +
                                                "&iCveUsuAutoriza=" + form.hdICveUsuAutoriza.value +
                                                "&lAgotado=" + form.hdLAgotado.value +
                                                "&dtAgotado=" + form.hdDtAgotado.value +
                                                "&lBaja=" + form.hdLBaja.value +
                                                "&dtBaja=" + form.hdDtBaja.value +
                                                "&iCveMarcaSust=" + form.hdICveMarcaSust.value +
                                                "&cObservacion=" + form.hdCObservacion.value +
                                                "&iCveUsuPrepara=" + form.hdICveUsuPrepara.value +
                                                "&iCveEquipo=" + form.hdICveEquipo.value +
                                                "&dtValoracion=" + form.hdDtValoracion.value +
                                                "&dVolUtilizado=" + form.hdDVolUtilizado.value +
                                                "\"" +
                 " enctype=\"multipart/form-data\">" +
                 " <input type=\"submit\" value=\"Generar Excel\"> " +
                 " </form>" +
                 " <br> Favor de esperar a que se presente el archivo de Excel ...  " +
                 " </body>" +
                 "</html>";
                 wExp = window.open("", "", "width=600,height=200,status=no,resizable=yes,menubar=yes,titlebar=yes,top=200,left=200,screenY=200,screenX=200,scrollbars=yes");
                 wExp.document.write(cPagina);
            } else {
                 alert("La casilla de verificación: Inmunoensayo \n No está seleccionada \n No se puede generar este reporte");
            }
       }
  }

  function baja() {
      form = document.forms[0];
      form.hdBoton.value = 'Actual';
      form.target = 'FRMDatos';
      form.action = 'pg070306031.jsp?RAC='+form.hdCampoClave.value+'&RACiCveCtrolCalibra='+form.hdCampoClave3.value+'&RACANIO='+form.iAnio.value;
      form.submit();
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
       cClave2  = ""+bs.getFieldValue("iCveLaboratorio", "");
       cClave3  = ""+bs.getFieldValue("iCveCtrolCalibra", "");
     }

TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <!-- input type="hidden" name="hdCampoClave" value="<%//=cClave2%>"-->
  <!-- input type="hidden" name="hdCampoClave3" value="<%//=cClave3%>" -->

  <input type="hidden" name="hdCampoClave" value="<%if(cClave2.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave2);%>">
  <input type="hidden" name="hdCampoClave3" value="<%if(cClave3.compareTo("")==0) out.print(request.getParameter("hdCampoClave3")); else out.print(cClave3);%>">


  <input type="hidden" name="iCveUniMed" value="<% if (request.getParameter("iCveUniMed") != null) out.print(request.getParameter("iCveUniMed"));%>">

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
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td>
<tr>
<td valign="top">
                         <%
                           TEtiCampo vEti = new TEtiCampo();
                           boolean lCaptura = clsConfig.getCaptura();
                           boolean lNuevo = clsConfig.getNuevo();

                         if (!lCaptura) {
                         %>
                         <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <%
                            %>

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
                                      String cCveLabx = ""+request.getParameter("iCveUniMed");
                                      if ( cCveLabx!=null && cCveLabx.compareToIgnoreCase("null")!=0&&cCveLabx.compareTo("")!=0)
                                         out.print(vEti.SelectOneRowSinTD("iCveLaboratorio","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,cCveLabx));
                                      else
                                         out.print(vEti.SelectOneRowSinTD("iCveLaboratorio","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0"));
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
                            <%
                            %>

                             <tr>
                             <td colspan="4" class="ETablaT">Calibradores</td>
                             </tr>

                             <%
                               if (lNuevo){ // Modificar de acuerdo al catálogo específico


                                    //Este lo muestra cuando es nuevo

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
                                     <%                                                       //fCambiaLab('" + clsConfig.getAccionOriginal() + "');
                                         out.println(vEti.SelectOneRowSinTD("iCveLaboratorio", "", (Vector) AppCacheManager.getColl("GRLUniMed",""), "iCveUniMed", "cDscUniMed", request, "0"));
                                     %>
                                 </td>
                               </tr>

                                    <%

                                    /* [Lote] , [Empleado para]*/

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Código:","ECampo","text",25,25,"cLote","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println(vEti.Texto("EEtiqueta","Empleado para:"));
                                    %>
                                    <td class="ETabla">
                                    <%
                                         Vector vcEmpleoCalib = new Vector();
                                         TDEmpleoCalib dEmpleoCalib = new  TDEmpleoCalib();
                                         vcEmpleoCalib = dEmpleoCalib.FindByAll(" order by cDscEmpleoCalib");
                                         out.print(vEti.SelectOneRowSinTD("iCveEmpleoCalib","",vcEmpleoCalib,"iCveEmpleoCalib","cDscEmpleoCalib",request,"0"));
                                    %>
                                    </td>
                                    <%
                                    out.println("</tr>");


                                    /* [Descripcion] */
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Descripción:","ECampo","text",75,100,3,"cDscCtrolCalibra","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");


                                    /* [Breve] */
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Breve:","ECampo","text",50,50,3,"cDscBreve","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");


                                    /* [Marca] , [Utilizado en]*/
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Marca:"));
                                    %>
                                    <td class="ETabla">
                                    <%
                                         Vector vcMarcaSust = new Vector();
                                         TDTOXMarcaSust dMarcaSust = new TDTOXMarcaSust();
                                         vcMarcaSust = dMarcaSust.FindByAll(" order by cDscMarcaSust ");
                                         out.print(vEti.SelectOneRowSinTD("iCveMarcaSust","",vcMarcaSust,"iCveMarcaSust","cDscMarcaSust",request,"0"));
                                         out.println(vEti.Texto("EEtiqueta","Utilizado en:"));

                                         out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"iCual\" Value=\"1\">InmunoEnsayo");
                                         out.println("<input type=\"checkbox\" name=\"iCuantCual\" value=\"1\">CG/EM</td>");
                                    %>
                                    <%
                                    out.println("</tr>");


                                    /* [Preparacion] */
                                    out.println("<tr>");
                                   %>
                                    <td colspan="4" class="ETablaT">Preparación</td>
                                   <%
                                    out.println("</tr>");


                                    /* [A partir de], [Reactivo] */

                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Tipo de Reactivo:"));
                                    %>
                                    <td class="ETabla">

                                    <%
                                    Vector vcTpoReact = new Vector();
                                    TDTOXTpoReact dTpoReact = new TDTOXTpoReact();
                                    vcTpoReact = dTpoReact.FindByAll(" order by cDscTpoReact ");    //llenaSLT(1,this.value,'','',document.forms[0].iCveReactivo);
                                    out.print(vEti.SelectOneRowSinTD("iCveTpoReact","llenaSLT(1,this.value,document.forms[0].iCveLaboratorio.value,'',document.forms[0].iCveReactivo);",vcTpoReact,"iCveTpoReact","cDscTpoReact",request,"0",true));
                                    %>
                                    </td>

                                    <%
                                    out.println(vEti.Texto("EEtiqueta","Reactivo:"));
                                    %>
                                    <td class="ETabla">
                                    <Select name="iCveReactivo" Size="1">
                                    </Select>
                                     </td>
                                    <%
                                    out.println("</tr>");

                                    /* [Volumen Utilizado] */

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Volumen Utilizado:","ECampo","text",10,10,"dVolUtilizado","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");



                                    /* [Fecha] , [Responsable]*/
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtPreparacion","",0,"","fMayus(this);",false,true,lCaptura));

                                    Vector vcPersonal;
                                    int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                    int iUniMed = 0;
                                    if(request.getParameter("iCveLaboratorio") == null){
                                       vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                    if(vcPersonal.size() != 0){
                                       iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                    }
                                   }else{
                                         iUniMed = Integer.parseInt(request.getParameter("iCveLaboratorio"),10);
                                    }
                                    vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                                    out.println(vEti.Texto("EEtiqueta","Por:"));
                                    out.println("<td>");
                                    out.print(vEti.SelectOneRowSinTD("iCveUsuPrepara", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, ""));
                                    out.println("</td>");



                                    //out.print(vEti.EtiCampo("EEtiqueta","Responsable:","ECampo","text",50,50,"iCveUsuPrepara", "Combo iCveUsuPrepara",0,"","fMayus(this);",false,true,lCaptura));


                                    out.println("</tr>");


                                    /* [Sustancia] , [Concentracion]*/
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Sustancia Psicotrópica:"));
                                    %>
                                    <td class="ETabla">
                                    <%
                                    Vector vcSustancia = new Vector();
                                    TDTOXSustancia dSustancia = new TDTOXSustancia();
                                    vcSustancia = dSustancia.FindByAll(""," order by cDscSustancia ");
                                    out.print(vEti.SelectOneRowSinTD("iCveSustancia","",vcSustancia,"iCveSustancia","cDscSustancia",request,"0"));
                                    %>
                                    </td>
                                    <td class="EEtiqueta">Concentración:</td>
                                    <td class="ECampo"><input type="text" size="13" maxlength="13" name="dConcentracion" value=""> ng/ml</td>
                                    <%
//                                    out.print(vEti.EtiCampo("EEtiqueta","Concentración:","ECampo","text",13,13,"dConcentracion","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");


                                    /* [Volumen] , [Viales]*/
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Volumen (mililitros):","ECampo","text",13,13,"dVolumen", "",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Número de Viales:","ECampo","text",13,13,"iViales","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    /* [Caducidad] */
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Caducidad:","ECampo","text",10,10,3,"dtCaducidad","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");


                                    /* [Autorizado] , [Por]*/
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Autorizado:","ECampo","text",10,10,"dtAutoriza", "",0,"","fMayus(this);",false,true,lCaptura));

                                    //TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                    //Vector vcPersonal;
                                    iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                    iUniMed = 0;
                                    if(request.getParameter("iCveLaboratorio") == null){
                                       vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                    if(vcPersonal.size() != 0){
                                       iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                    }
                                   }else{
                                         iUniMed = Integer.parseInt(request.getParameter("iCveLaboratorio"),10);
                                    }
                                    vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                                    out.println(vEti.Texto("EEtiqueta","Por:"));
                                    out.println("<td>");
                                    out.print(vEti.SelectOneRowSinTD("iCveUsuAutoriza", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, ""));
                                    out.println("</td>");
                                    out.println("</tr>");

                                    //out.print(vEti.EtiCampo("EEtiqueta","Por:","ECampo","text",50,50,"iCveUsuAutoriza", "Combo-iCveUsuAutoriza",0,"","fMayus(this);",false,true,lCaptura));

                                    out.println("</tr>");

                                    /* [Observacion] */
                                    out.println("<tr>");
                                    out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observación:","ECampo",110,3,3,"cObservacion","",0,"","",true,true,true));
                                    out.println("</tr>");

                                    /* [Situaciion] */
                                    out.println("<tr>");
                                   %>
                                    <td colspan="4" class="ETablaT">Situación</td>
                                   <%
                                    out.println("</tr>");


									/* [Valoracion] */

                                    out.println("<tr><td class=\"ETablaT\" colspan=\"4\">Valoraci&oacute;n</td></tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n Experimental:","ECampo","text",10,7,"dConcentExper","",0,"fMayus(this);","",true,true,lCaptura,request));
                                    out.println(vEti.EtiCampo("EEtiqueta","Fecha de Valoraci&oacute;n:","ECampo","text",10,8,"dtValoracion","",0,"fMayus(this);","",true,true,lCaptura,request));
                                    out.println("</tr><tr>");
                                    out.println(vEti.EtiSelectOneRowCS("EEtiqueta","Equipo:","ECampo","iCveEquipo","0","",3, new TDTOXEquipo().FindByAll(" where lCuantCual = 1 ", " order by cCveEquipo "), "iCveEquipo","cCveEquipo", request,"-1",true,true,lCaptura));
                                    out.println("</tr>");

                                    /* [Agotado, Baja], [Fecha Agotado, Fecha Baja] */
                                    out.println("<tr>");
                                    out.println("<td colspan=\"2\" class=\"ETabla\"><input type=\"checkbox\" name=\"lAgotado\"   onClick=\"javascript:fHabilitafecha();\"   value=\"1\">Agotado<br>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Fecha:","ECampo","text",10,10,3,"dtAgotado", "",0,"","fMayus(this);",false,false,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lBaja\"value=\"1\" disabled>Baja</td>");
                                    out.println("</tr>");


/**************** MODIFICAR Y DESPLEGAR ****************/

                               }
                               else{      // Para Desplegar o Para Modificar
                                if (bs != null){
                             %>

                                 <tr>
                             <%
                                 if (lCaptura) {
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
                                 } else out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",50,50,"iAnio", bs.getFieldValue("iAnio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                 if (lCaptura) {
                                 out.println(vEti.Texto("EEtiqueta","Laboratorio:"));
                                 %>
                                 <td class="ETabla">
                                     <%                                                      //fCambiaLab('" + clsConfig.getAccionOriginal() + "');
                                         out.println(vEti.SelectOneRowSinTD("iCveLaboratorio", "", (Vector) AppCacheManager.getColl("GRLUniMed",""), "iCveUniMed", "cDscUniMed", request, "0"));
                                     %>
                                 </td>
                              <%
                                } else out.print(vEti.EtiCampo("EEtiqueta","Laboratorio:","ECampo","text",50,50,"cDscUniMed", bs.getFieldValue("cDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                               %>
                               </tr>
                                    <%


                                   /* [Clave del Calibrador]*/
                                    if (!lCaptura){
                                         out.print("<tr>"+vEti.EtiCampoCS("EEtiqueta","Clave del Calibrador:","ECampo","text",13,13,3,"iCveCtrolCalibra",bs.getFieldValue("iCveCtrolCalibra","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura)+"</tr>");
                                    }

                                   /* [Lote] , [Empleado para]*/

                                    out.println("<tr>");
                                    if (lCaptura) {
                                           out.print(vEti.EtiCampo("EEtiqueta","No. Código:","ECampo","text",25,25,"cLote",bs.getFieldValue("cLote","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    } else {
                                           out.print(vEti.EtiCampo("EEtiqueta","No. Código:","ECampo","text",25,25,"cLote",bs.getFieldValue("cLote","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                           <!-- Agregado por Rafael Alcocer Caldera 25/Ago/2006 -->
                                           <!-- *********************************************** -->
                                           <input type="hidden" name="hdCLote" value="<%= bs.getFieldValue("cLote","&nbsp;").toString() %>">
                                    <%
                                    }

                                    if (lCaptura){
                                    out.println(vEti.Texto("EEtiqueta","Empleado para:"));
                                    %>
                                    <td class="ETabla">
                                    <%
                                         Vector vcEmpleoCalib = new Vector();
                                         TDEmpleoCalib dEmpleoCalib = new  TDEmpleoCalib();
                                         vcEmpleoCalib = dEmpleoCalib.FindByAll(" order by cDscEmpleoCalib");
                                         out.print(vEti.SelectOneRowSinTD("iCveEmpleoCalib","",vcEmpleoCalib,"iCveEmpleoCalib","cDscEmpleoCalib",request,bs.getFieldValue("iCveEmpleoCalib","&nbsp;").toString()));
                                    %>
                                    </td>
                                    <%
                                    } else {
                                        out.print(vEti.EtiCampo("EEtiqueta","Empleado para:","ECampo","text",50,50,"cDscEmpleoCalib", bs.getFieldValue("cDscEmpleoCalib","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                        %>
                                        <!-- Agregado por Rafael Alcocer Caldera 25/Ago/2006 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdICveEmpleoCalib" value="<%= bs.getFieldValue("iCveEmpleoCalib","&nbsp;") %>">
                                        <%
                                    }
                                    out.println("</tr>");


                                    /* [Descripcion] */
                                    out.println("<tr>");
                                    if (lCaptura) {
                                           out.print(vEti.EtiCampoCS("EEtiqueta","Descripción:","ECampo","text",75,100,3,"cDscCtrolCalibra",bs.getFieldValue("cDscCtrolCalibra","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    } else {
                                           out.print(vEti.EtiCampoCS("EEtiqueta","Descripción:","ECampo","text",75,100,3,"cDscCtrolCalibra",bs.getFieldValue("cDscCtrolCalibra","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                           <!-- Agregado por Rafael Alcocer Caldera 12/Jul/2007 -->
                                           <!-- *********************************************** -->
                                           <input type="hidden" name="hdCDscCtrolCalibra" value="<%= bs.getFieldValue("cDscCtrolCalibra","&nbsp;").toString() %>">
                                    <%
                                    }
                                    out.println("</tr>");


                                    /* [Breve] */
                                    out.println("<tr>");
                                    if (lCaptura) {
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Breve:","ECampo","text",50,50,3,"cDscBreve",bs.getFieldValue("cDscBreve","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    } else {
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Breve:","ECampo","text",50,50,3,"cDscBreve",bs.getFieldValue("cDscBreve","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                           <!-- Agregado por Rafael Alcocer Caldera 12/Jul/2007 -->
                                           <!-- *********************************************** -->
                                           <input type="hidden" name="hdCDscBreve" value="<%= bs.getFieldValue("cDscBreve","&nbsp;").toString() %>">
                                    <%
                                    }
                                    out.println("</tr>");


                                    /* [Marca] , [Utilizado en]*/
                                    out.println("<tr>");
                                    if (lCaptura) {
                                    out.println(vEti.Texto("EEtiqueta","Marca:"));
                                    %>
                                    <td class="ETabla">
                                    <%
                                         Vector vcMarcaSust = new Vector();
                                         TDTOXMarcaSust dMarcaSust = new TDTOXMarcaSust();
                                         vcMarcaSust = dMarcaSust.FindByAll(" order by cDscMarcaSust ");
                                         out.print(vEti.SelectOneRowSinTD("iCveMarcaSust","",vcMarcaSust,"iCveMarcaSust","cDscMarcaSust",request,bs.getFieldValue("iCveMarcaSust","&nbsp;").toString()));
                                    } else {
                                        out.print(vEti.EtiCampo("EEtiqueta","Marca:","ECampo","text",50,50,"cDscMarcaSust", bs.getFieldValue("cDscMarcaSust","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                    	<!-- Agregado por Rafael Alcocer Caldera 12/Jul/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdICveMarcaSust" value="<%= bs.getFieldValue("iCveMarcaSust","&nbsp;").toString() %>">
                                    <%
                                    }
                                     %>

                                    <%
                                     if (lCaptura) {
                                         out.println(vEti.Texto("EEtiqueta","Utilizado en:"));
                                         String cChecked = "checked";

                                          if (bs.getFieldValue("lCual","0").toString().compareToIgnoreCase("1")!=0)
                                             out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"iCual\" Value=\"1\">InmunoEnsayo");
                                          else
                                             out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"iCual\" Value=\"1\" " + cChecked +  ">InmunoEnsayo");
                                          if (bs.getFieldValue("lCuantCual","0").toString().compareToIgnoreCase("0")!=0)
                                             out.println("<input type=\"checkbox\" name=\"iCuantCual\" Value=\"1\" "+ cChecked + ">CG/EM</td>");
                                          else
                                             out.println("<input type=\"checkbox\" name=\"iCuantCual\" Value=\"1\" " + ">CG/EM</td>");

                                     } else {
                                          String cDisable = "disabled";
                                          String cChecked = "checked";

                                         out.println(vEti.Texto("EEtiqueta","Utilizado en:"));

                                          if (bs.getFieldValue("lCual","0").toString().compareToIgnoreCase("1")!=0) {
                                             out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"iCual\" Value=\"1\" disabled>InmunoEnsayo");
                                          } else {
                                             out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"iCual\" Value=\"1\" disabled " + cChecked +  ">InmunoEnsayo");
                                          }

                                          if (bs.getFieldValue("lCuantCual","0").toString().compareToIgnoreCase("0")!=0) {
                                             out.println("<input type=\"checkbox\" name=\"iCuantCual\" Value=\"1\" disabled "+ cChecked + ">CG/EM</td>");
                                          } else {
                                             out.println("<input type=\"checkbox\" name=\"iCuantCual\" Value=\"1\" disabled " + ">CG/EM</td>");
                                          }
                                          // out.print(vEti.EtiCampo("EEtiqueta","Utilizado en:","ECampo","text",50,50,"iCuantCual", bs.getFieldValue("iCuantCual","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));

                                          %>
                                           		<!-- Agregado por Rafael Alcocer Caldera 25/Ago/2006 -->
                                           		<!-- *********************************************** -->
                                                <input type="hidden" name="hdICual" value="<%= bs.getFieldValue("lCual","0").toString() %>">
                                           		<input type="hidden" name="hdICuantCual" value="<%= bs.getFieldValue("lCuantCual","0").toString() %>">
                                          <%

                                     }
                                    %>
                                    <%
                                    out.println("</tr>");


                                    /* [Preparacion] */
                                    out.println("<tr>");
                                   %>
                                    <td colspan="4" class="ETablaT">Preparación</td>
                                   <%
                                    out.println("</tr>");

                                    /* A partir de */
                                    out.println("<tr>");
                                    if (lCaptura) {
                                    out.println(vEti.Texto("EEtiqueta","A partir de:"));
                                    %>
                                    <td class="ETabla">

                                    <%  /*Estaba un <select name="iCveTpoReact"> </select>*/
                                         Vector vcTpoReact = new Vector();
                                         TDTOXTpoReact dTpoReact = new TDTOXTpoReact();
                                         vcTpoReact = dTpoReact.FindByAll(" order by cDscTpoReact");
                                        // out.print(vEti.SelectOneRowSinTD("iCveTpoReact","llenaSLT(1,this.value,'','',document.forms[0].iCveReactivo);",vcTpoReact,"iCveTpoReact","cDscTpoReact",request,bs.getFieldValue("iCveTpoReact","&nbsp;").toString()));
                                        out.print(vEti.SelectOneRowSinTD("iCveTpoReact","llenaSLT(2,this.value,document.forms[0].iCveLaboratorio.value,'',document.forms[0].iCveReactivo);",vcTpoReact,"iCveTpoReact","cDscTpoReact",request,bs.getFieldValue("iCveTpoReact","&nbsp;").toString(),true));


                                    } else out.print(vEti.EtiCampo("EEtiqueta","Tipo de Reactivo:","ECampo","text",50,50,"cDscTpoReact", bs.getFieldValue("cDscTpoReact","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                    </td>

                                    <%
                                    if (lCaptura) {
                                          out.println(vEti.Texto("EEtiqueta","Reactivo:"));
                                        %>
                                         <td class="ETabla">
                                         <Select name="iCveReactivo" Size="1">
                                        <%

                                        TDTOXReactivo dReactivo = new TDTOXReactivo();
                                        TVTOXReactivo vReactivo = new TVTOXReactivo();
                                        Vector vcReactivo = new Vector();
                                        String cCveTpoReact = bs.getFieldValue("iCveTpoReact","").toString();
                                        String cCveLaboratorio = bs.getFieldValue("iCveLaboratorio","").toString();
                                        String cWhere = "";
                                        String cOrden = " order by R.iCodigo";
                                        if (cCveTpoReact.compareTo("null") != 0 && cCveTpoReact.compareTo("") != 0   &&
                                            cCveLaboratorio.compareTo("null") != 0 && cCveLaboratorio.compareTo("")!= 0) {
                                           cWhere = " where R.iCveLaboratorio = " +cCveLaboratorio + " and R.iCveTpoReact = " + cCveTpoReact ;

                                           vcReactivo = dReactivo.FindByReactivosNV(cWhere + cOrden);   //dReactivo.FindByAll(cWhere,cOrden);
                                           if ( vcReactivo.size() > 0 ){
                                                out.print("<option value=0>Seleccione...</option>");
                                                for ( int i=0; i < vcReactivo.size(); i++){
                                                   vReactivo = (TVTOXReactivo)vcReactivo.get(i);
                                                   if (bs.getFieldValue("iCveReactivo")!=null&& bs.getFieldValue("iCveReactivo").toString().compareToIgnoreCase(new Integer(vReactivo.getICveReactivo()).toString())==0){
                                                     out.print("<option value = "+ vReactivo.getICveReactivo() + " selected>" + vReactivo.getICodigo() + " - " + vReactivo.getCDscBreve() + "</option>");
                                                   }
                                                   else
                                                     out.print("<option value = " + vReactivo.getICveReactivo() + ">" + vReactivo.getICodigo() + " - " + vReactivo.getCDscBreve() + "</option>");
                                                }
                                           } else out.print("<option value = 0>Datos no disponibles...</option>");
                                        }
                                        %>
                                        </Select>
                                        </td>
                                        <%
                                    } else {
                                        TDTOXReactivo dReactivo = new TDTOXReactivo();
                                        TVTOXReactivo vReactivo = new TVTOXReactivo();
                                        Vector vcReactivo = new Vector();
                                        String cCveTpoReact = bs.getFieldValue("iCveTpoReact","").toString();
                                        String cCveLaboratorio = bs.getFieldValue("iCveLaboratorio","").toString();
                                        String cCveReactivo = bs.getFieldValue("iCveReactivo","").toString();
                                        String cWhere = "";
                                        String cOrden = " order by iCveReactivo";
                                        String cCodigo = "";

                                        %>
                                        <!-- Agregado por Rafael Alcocer Caldera 25/Ago/2006 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdICveReactivo" value="<%= bs.getFieldValue("iCveReactivo","") %>">
                                        <%

                                        if (cCveTpoReact.compareTo("null") != 0 && cCveTpoReact.compareTo("") != 0   &&
                                            cCveLaboratorio.compareTo("null") != 0 && cCveLaboratorio.compareTo("")!= 0 &&
                                            cCveReactivo.compareTo("null") != 0 && cCveReactivo.compareTo("")!= 0 ) {
                                           cWhere = "where R.iCveReactivo = "+ cCveReactivo +" and R.iCveTpoReact = " + cCveTpoReact + " and R.iCveLaboratorio = "+cCveLaboratorio;
                                           vcReactivo = dReactivo.FindByReactivosNV(cWhere + cOrden);   //dReactivo.FindByAll(cWhere,cOrden);

                                           if ( vcReactivo.size() > 0 ){
                                              vReactivo = (TVTOXReactivo) vcReactivo.get(0);
                                              out.print(vEti.EtiCampo("EEtiqueta","Reactivo:","ECampo","text",50,50,"cDscReactivo", vReactivo.getICodigo() +"-"+  bs.getFieldValue("cDscReactivo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                           }
                                        }
                                    }
                                      out.println("</tr>");

                                    /* [Volumen Utilizado] */

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Volumen Utilizado:","ECampo","text",10,10,"dVolUtilizado",""+ bs.getFieldValue("dVolUtilizado",""),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                        <!-- Agregado por Rafael Alcocer Caldera 12/JUl/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdDVolUtilizado" value="<%= bs.getFieldValue("dVolUtilizado","") %>">
                                    <%
                                    out.println("</tr>");

                                    /* [Fecha] , [Responsable]*/
                                    out.println("<tr>");
                                    if (lCaptura) {
                                        out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtPreparacion",bs.getFieldValue("cDtPreparacion","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    } else {
                                        out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtPreparacion",bs.getFieldValue("cDtPreparacion","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                        <!-- Agregado por Rafael Alcocer Caldera 12/JUl/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdDtPreparacion" value="<%= bs.getFieldValue("cDtPreparacion","&nbsp;").toString() %>">
                                    <%
                                    }

                                    if (lCaptura) {
                                              Vector vcPersonal;
                                              int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                              int iUniMed = 0;
                                              if(request.getParameter("iCveLaboratorio") == null){
                                              vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                              if(vcPersonal.size() != 0){
                                              iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                              }
                                              }else{
                                                 iUniMed = Integer.parseInt(request.getParameter("iCveLaboratorio"),10);
                                              }
                                              vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                                              out.println(vEti.Texto("EEtiqueta","Responsable:"));
                                              out.println("<td>");
                                              out.print(vEti.SelectOneRowSinTD("iCveUsuPrepara", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, bs.getFieldValue("iCveUsuPrepara","").toString()));
                                              out.println("</td>");

                                    } else {

                                    	if (lCaptura) {
                                        	out.print(vEti.EtiCampo("EEtiqueta","Responsable:","ECampo","text",25,25,"cDscUsuPrepara",bs.getFieldValue("cDscUsuPrepara","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    	} else {
                                            out.print(vEti.EtiCampo("EEtiqueta","Responsable:","ECampo","text",25,25,"cDscUsuPrepara",bs.getFieldValue("cDscUsuPrepara","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));                                    out.println("</tr>");
                                    	}

                                        %>
                                        <!-- Agregado por Rafael Alcocer Caldera 12/JUl/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdICveUsuPrepara" value="<%= bs.getFieldValue("iCveUsuPrepara","").toString() %>">
                                    <%
                                    }


                                    /* [Sustancia] , [Concentracion]*/
                                    out.println("<tr>");
                                    if (lCaptura) {
                                    out.println(vEti.Texto("EEtiqueta","Sustancia Psicotrópica:"));
                                    %>
                                    <td class="ETabla">
                                    <%
                                    Vector vcSustancia = new Vector();
                                    TDTOXSustancia dSustancia = new TDTOXSustancia();
                                    vcSustancia = dSustancia.FindByAll(""," order by cDscSustancia ");
                                    out.print(vEti.SelectOneRowSinTD("iCveSustancia","",vcSustancia,"iCveSustancia","cDscSustancia",request,bs.getFieldValue("iCveSustancia","").toString()));
                                    } else {
                                    	out.print(vEti.EtiCampo("EEtiqueta","Sustancia Psicotrópica:","ECampo","text",20,20,"cDscSustancia",bs.getFieldValue("cDscSustancia","&nbsp").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                        <!-- Agregado por Rafael Alcocer Caldera 12/JUl/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdICveSustancia" value="<%= bs.getFieldValue("iCveSustancia","") %>">
                                    <%
                                    }
                                    %>
                                    </td>
                                    <%
                                    if (lCaptura){
                                      out.println("<td class=\"EEtiqueta\">Concentración:</td>");
                                      out.println("<td class=\"ECampo\"><input type=\"text\" size=\"13\" maxlength=\"13\" name=\"dConcentracion\" value=\""+bs.getFieldValue("dConcentracion","&nbsp;").toString()+"\"> ng/ml</td>");
                                    }else{
                                      out.print(vEti.EtiCampo("EEtiqueta","Concentración:","ECampo","text",13,13,"dConcentracion",bs.getFieldValue("dConcentracion","&nbsp;").toString() + " ng/ml ",0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                           <!-- Agregado por Rafael Alcocer Caldera 12/Jul/2007 -->
                                           <!-- *********************************************** -->
                                           <input type="hidden" name="hdDConcentracion" value="<%= bs.getFieldValue("dConcentracion","&nbsp;").toString() %>">
                                    <%
                                    }
                                    out.println("</tr>");


                                    /* [Volumen] , [Viales]*/
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Volumen (mililitros):","ECampo","text",13,13,"dVolumen",bs.getFieldValue("dVolumen","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                           <!-- Agregado por Rafael Alcocer Caldera 12/Jul/2007 -->
                                           <!-- *********************************************** -->
                                           <input type="hidden" name="hdDVolumen" value="<%= bs.getFieldValue("dVolumen","&nbsp;").toString() %>">
                                    <%
                                    out.print(vEti.EtiCampo("EEtiqueta","Número de Viales:","ECampo","text",13,13,"iViales",bs.getFieldValue("iViales","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                           <!-- Agregado por Rafael Alcocer Caldera 12/Jul/2007 -->
                                           <!-- *********************************************** -->
                                           <input type="hidden" name="hdIViales" value="<%= bs.getFieldValue("iViales","&nbsp;").toString() %>">
                                    <%
                                    out.println("</tr>");

                                    /* [Caducidad] */
                                    out.println("<tr>");
                                    if (lCaptura) {
                                        out.print(vEti.EtiCampoCS("EEtiqueta","Caducidad:","ECampo","text",10,10,3,"dtCaducidad", bs.getFieldValue("cDtCaducidad","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    } else {
                                        out.print(vEti.EtiCampoCS("EEtiqueta","Caducidad:","ECampo","text",10,10,3,"dtCaducidad", bs.getFieldValue("cDtCaducidad","&nbsp").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                        <!-- Agregado por Rafael Alcocer Caldera 12/JUl/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdDtCaducidad" value="<%= bs.getFieldValue("cDtCaducidad","&nbsp;").toString() %>">
                                    <%
                                    }
                                    out.println("</tr>");


                                    /* [Autorizado] , [Por]*/
                                    out.println("<tr>");
                                    if (lCaptura) {
                                        out.print(vEti.EtiCampo("EEtiqueta","Autorizado:","ECampo","text",10,10,"dtAutoriza", bs.getFieldValue("cDtAutoriza","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    } else {
                                        out.print(vEti.EtiCampo("EEtiqueta","Autorizado:","ECampo","text",10,10,"dtAutoriza", bs.getFieldValue("cDtAutoriza","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                        <!-- Agregado por Rafael Alcocer Caldera 12/JUl/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdDtAutoriza" value="<%= bs.getFieldValue("cDtAutoriza","&nbsp;").toString() %>">
                                    <%
                                    }

                                    if (lCaptura) {
                                              vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                              Vector vcPersonal;
                                              int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                              int iUniMed = 0;
                                              if(request.getParameter("iCveLaboratorio") == null){
                                              vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                              if(vcPersonal.size() != 0){
                                              iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                              }
                                              }else{
                                                 iUniMed = Integer.parseInt(request.getParameter("iCveLaboratorio"),10);
                                              }
                                              vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                                              out.println(vEti.Texto("EEtiqueta","Responsable:"));
                                              out.println("<td>");
                                              out.print(vEti.SelectOneRowSinTD("iCveUsuAutoriza", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, bs.getFieldValue("iCveUsuAutoriza","").toString()));
                                              out.println("</td>");

                                    } else {
                                    	out.print(vEti.EtiCampo("EEtiqueta","Por:","ECampo","text",50,50,"cDscUsuAutoriza", bs.getFieldValue("cDscUsuAutoriza","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    %>
                                        <!-- Agregado por Rafael Alcocer Caldera 12/JUl/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdICveUsuAutoriza" value="<%= bs.getFieldValue("iCveUsuAutoriza","").toString() %>">
                                    <%
                                    }
                                    out.println("</tr>");

                                    /* [Observacion] */
                                    out.println("<tr>");
                                    out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observación:","ECampo",110,3,3,"cObservacion",bs.getFieldValue("cObservacion","&nbsp;").toString(),0,"","",true,true,lCaptura));
                                    %>
                                        <!-- Agregado por Rafael Alcocer Caldera 12/JUl/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdCObservacion" value="<%= bs.getFieldValue("cObservacion","&nbsp;").toString() %>">
                                    <%
                                    out.println("</tr>");
                                    /* [Valoracion] */

                                    out.println("<tr><td class=\"ETablaT\" colspan=\"4\">Valoraci&oacute;n</td></tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n Experimental:","ECampo","text",10,7,"dConcentExper", ""+ bs.getFieldValue("dConcentExper",""),0,"fMayus(this);","",false,true,lCaptura,request));

                                    // dtValoracion formato dd/mm/aaaa (cDtValoracion)
                                    out.println(vEti.EtiCampo("EEtiqueta","Fecha de Valoraci&oacute;n:","ECampo","text",10,8,"dtValoracion", bs.getFieldValue("cDtValoracion","").toString() ,0,"fMayus(this);","",false,true,lCaptura,request));
                                    %>
                                        <!-- Agregado por Rafael Alcocer Caldera 12/JUl/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdDtValoracion" value="<%= bs.getFieldValue("cDtValoracion","").toString() %>">
                                    <%
                                    out.println("</tr><tr>");
                                     String descEquipo = "0123";
                                    if(lCaptura==false){
                                        descEquipo = bs.getFieldValue("cCveEquipo","0").toString();                                        
                                    }
                                    out.println(vEti.EtiSelectOneRowCS("EEtiqueta","Equipo:","ECampo","iCveEquipo",descEquipo,"",3, new TDTOXEquipo().FindByAll(" where lCuantCual = 1 ", " order by cCveEquipo "), "iCveEquipo","cCveEquipo", request,"-1",false,true,lCaptura));
                                    %>
                                        <!-- Agregado por Rafael Alcocer Caldera 12/JUl/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdICveEquipo" value="<%= request.getParameter("iCveEquipo") %>">
                                    <%
                                    out.println("</tr>");



                                    /* [Situaciion] */
                                    fechaFormateada = "&nbsp;"; // re-set del valor
                                    out.println("<tr>");
                                   %>
                                    <td colspan="4" class="ETablaT">Situación</td>
                                   <%
                                    out.println("</tr>");

                                    /* [Agotado, Baja], [Fecha Agotado, Fecha Baja] */
                                    out.println("<tr>");
                                   String cDisable = "";
                                   String cChecked = "";

                                   if (lCaptura) {
                                        /*Despliegue para Modificar*/

                                         // Si ya esta Dado de Baja
                                         if (bs.getFieldValue("lBaja","0").toString().compareToIgnoreCase("0")!=0){

                                              cDisable = "disabled";
                                              if (bs.getFieldValue("lAgotado","0").toString().compareToIgnoreCase("0")!=0)
                                                  cChecked = "checked"; else cChecked = "";
                                              out.println("<td colspan=\"2\" class=\"ETabla\"><input type=\"checkbox\" name=\"lAgotado\" value=\"1\" " + cDisable + " " +  cChecked + ">Agotado<br>");

                                              out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtBaja", bs.getFieldValue("cDtBaja","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));

                                             out.println("</tr><tr><td class='ETabla' colspan ='4'>");
                                              if (bs.getFieldValue("lBaja","0").toString().compareToIgnoreCase("0")!=0)
                                                  cChecked = "checked"; else cChecked = "";
                                              out.println("<class=\"ETabla\"><input type=\"checkbox\" name=\"lBaja\" value=\"1\" " + cDisable + " " + cChecked + ">Baja");
                                              out.println("</td>");


                                              out.println("</tr>");

                                         } else {  // No Esta Dado de Baja
                                              cDisable = "disabled";
                                              if (bs.getFieldValue("lAgotado","0").toString().compareToIgnoreCase("0")!=0)
                                              cChecked = "checked"; else cChecked = "";
                                              out.println("<td colspan=\"2\" class=\"ETabla\"><input type=\"checkbox\" name=\"lAgotado\" value=\"1\"   onClick=\"javascript:fHabilitafecha();\"   " + cChecked + ">Agotado<br>");

                                              out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtAgotado", bs.getFieldValue("cDtAgotado","").toString(),0,"","fMayus(this);",false,false,lCaptura));

                                              out.println("</tr><tr><td class='ETabla' colspan ='4'>");

                                              if (bs.getFieldValue("lBaja","0").toString().compareToIgnoreCase("0")!=0)
                                                cChecked = "checked"; else cChecked = "";
                                              out.println("<class=\"ETabla\"><input type=\"checkbox\" name=\"lBaja\" value=\"1\" " + cDisable + " " + cChecked + ">Baja");
                                              out.println("</td>");
                                              out.println("</tr>");
                                         }

                                   } else {

                                          //Despliegue Con Datos Solo Lectura
                                       cDisable = "disabled";
                                       if (bs.getFieldValue("lAgotado","0").toString().compareToIgnoreCase("0")!=0)
                                          cChecked = "checked"; else cChecked = "";
                                       out.println("<td colspan=\"2\" class=\"ETabla\"><input type=\"checkbox\" name=\"lAgotado\" value=\"1\" " + cDisable + " " +  cChecked + ">Agotado<br>");


                                       if (bs.getFieldValue("lBaja","0").toString().compareToIgnoreCase("0")!=0)
                                            out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtBaja", bs.getFieldValue("cDtBaja","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       else
                                            out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtAgotado", bs.getFieldValue("cDtAgotado","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));

                                       out.println("</tr><tr><td class='ETabla' colspan ='2'>");
                                       if (bs.getFieldValue("lBaja","0").toString().compareToIgnoreCase("0")!=0)
                                          cChecked = "checked"; else cChecked = "";
                                       out.println("<class=\"ETabla\"><input type=\"checkbox\" name=\"lBaja\" value=\"1\" " + cDisable + " " +  cChecked + ">Baja");
                                       out.println("</td>");
                                       out.println("<td class=\"ECampoC\">");
                                       out.println(vEti.clsAnclaTexto("EAncla","Baja","JavaScript:baja();","Baja"));
                                       out.println("</td>");
                                       out.println("</tr>");
                                    %>
                                        <!-- Agregado por Rafael Alcocer Caldera 12/JUl/2007 -->
                                        <!-- *********************************************** -->
                                        <input type="hidden" name="hdLAgotado" value="<%= bs.getFieldValue("lAgotado","0").toString() %>">
                                        <input type="hidden" name="hdDtAgotado" value="<%= bs.getFieldValue("cDtAgotado","&nbsp;").toString() %>">
                                        <input type="hidden" name="hdLBaja" value="<%= bs.getFieldValue("lBaja","0").toString() %>">
                                        <input type="hidden" name="hdDtBaja" value="<%= bs.getFieldValue("cDtBaja","&nbsp;").toString() %>">
                                    <%
                                   }

                                   out.println("<tr>");
                                   out.println("<td class=\"ECampoC\" colspan='2'>");
                                   out.println(vEti.clsAnclaTexto("EAncla","An&aacute;lisis Confirmatorio","JavaScript:generaXLS(1);","Reporte"));
                                   out.println("</td>");
                                   out.println("<td class=\"ECampoC\" colspan='2'>");
                                   out.println(vEti.clsAnclaTexto("EAncla","An&aacute;lisis Presuntivo","JavaScript:generaXLS(2);","Reporte"));
                                   out.println("</td>");
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
<% vEntorno.clearListaImgs();%>
</html>
