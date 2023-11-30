<%/**
 * Title: Consulta de Positivos
 * Description: Consulta de las Muestras Confirmadas como Positivas.
 * Copyright: 2006, 28 Marzo 2006
 * Company: Micros Personales, S.A. de C.V.
 * @author Itzia María del Carmen Sánchez Méndez.
 * @version 1.0
 * Clase:
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
  pg070305060CFG  clsConfig = new pg070305060CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070305060.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070305060.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No disponible|";    // modificar
  String cCveOrdenar  = "|";  // modificar
  String cDscFiltrar  = "NIM|";    // modificar
  String cCveFiltrar  = "A.iCveMuestra|";  // modificar
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
  boolean lDeshecho = false;
  if(request.getParameter("TBXDeshecho") != null)
     lDeshecho = true;
  String lBandera = "0";
  if(request.getParameter("hdBandera") != null)
     lBandera = request.getParameter("hdBandera").toString();

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

function fValidaBuscar(){
  var form = document.forms[0];
  var cVMsg = '';
  if( form.iCveLaboratorio){
     if (form.iCveLaboratorio.value <= 0){
      cVMsg = cVMsg + "\n -Debe seleccionar un laboratorio. ";
     }
    if(form.dtFechaI && form.dtFechaI.disabled == false){
     if (form.dtFechaI && form.dtFechaI.value != "")
        cVMsg = cVMsg + fSinValor(form.dtFechaI,5,'Fec. De:', true);

      if (form.dtFechaF && form.dtFechaF.value != "")
        cVMsg = cVMsg + fSinValor(form.dtFechaF,5,'Fec. Hasta:', true);

      if (form.dtFechaI && form.dtFechaI.value != ""){
         if (form.dtFechaI.value != null){
         Dia  = form.dtFechaI.value.substring(0,2);
         Mes  = form.dtFechaI.value.substring(3,5)-1;
         Anio = form.dtFechaI.value.substring(6,10);
         dtFecApi = new Date(Anio,Mes,Dia);

         Dia  = form.dtFechaF.value.substring(0,2);
         Mes  = form.dtFechaF.value.substring(3,5)-1;
         Anio = form.dtFechaF.value.substring(6,10);
         dtFecHoy = new Date(Anio,Mes,Dia);
          if (dtFecApi > dtFecHoy){
            cVMsg = cVMsg + "\n - La Fecha de inicial de análisis no puede ser mayor a la fecha final. \n";
           }
         }
      }
    }
    // Validar las fechas de deshecho
    if(form.dtFechaID && form.dtFechaID.disabled == false && form.dtFechaID.value != ""){
     if (form.dtFechaID.value != "")
        cVMsg = cVMsg + fSinValor(form.dtFechaID,5,'La fecha inicial de deshecho', true);

      if (form.dtFechaFD && form.dtFechaFD.value != "")
        cVMsg = cVMsg + fSinValor(form.dtFechaFD,5,'La fecha final de deshecho', true);

      if (form.dtFechaID && form.dtFechaID.value != ""){
         if (form.dtFechaI.value != null){
         Dia  = form.dtFechaID.value.substring(0,2);
         Mes  = form.dtFechaID.value.substring(3,5)-1;
         Anio = form.dtFechaID.value.substring(6,10);
         dtFecApi = new Date(Anio,Mes,Dia);

         Dia  = form.dtFechaFD.value.substring(0,2);
         Mes  = form.dtFechaFD.value.substring(3,5)-1;
         Anio = form.dtFechaFD.value.substring(6,10);
         dtFecHoy = new Date(Anio,Mes,Dia);
          if (dtFecApi > dtFecHoy){
            cVMsg = cVMsg + "\n - La Fecha de inicial del periodo de deshecho no puede ser mayor a la fecha final. \n";
           }
         }
      }
    } // Fin de la validación.
  }

  if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
  }

  return true;

}

// función para activar el Radio Set
function fDeshecho(lOpcion){
 form = document.forms[0];
  if(lOpcion == '3'){
     form.dtFechaID.disabled = false;
     form.dtFechaFD.disabled = false;
  }else{
     form.dtFechaID.disabled = true;
     form.dtFechaFD.disabled = true;
  }
}


// Función para solicitar el Deshecho
function fConsDesh(objeto){
  // Deshabilitar Radio para consulta de Situación
   form = document.forms[0];
   if(form.TBXDeshecho.checked){
     lGrupo1 = true;
     lGrupo2 = false;
     fDeshecho('1');
   }else{
     lGrupo1 = false;
     lGrupo2 = true;


   }

     // Año
     form.iAnio.disabled = lGrupo1;
     // Rango
     form.dtFechaI.disabled = lGrupo1;
     form.dtFechaF.disabled = lGrupo1;
     // RSTipo
     form.RSTipo[0].disabled = lGrupo1;
     form.RSTipo[1].disabled = lGrupo1;
     form.RSTipo[2].disabled = lGrupo1;
     // Sustancia
     form.iCveSustancia.disabled = lGrupo1;
     // Situacion
     form.RSSituacion[0].disabled = lGrupo1;
     form.RSSituacion[1].disabled = lGrupo1;
     form.RSSituacion[2].disabled = lGrupo1;

     form.dtFechaAD.disabled = lGrupo2;
}


  function fIrpagina(iAnio, iCveLaboratorio, iCveLoteCualita, iCveExamCualita, pgAccion){
    form = document.forms[0];
    form.hdCampoClave.value = iAnio;
    form.hdLCondicion.value = "";
    form.hdLOrdenar.value = "";
    form.hdCampoClave2.value = iCveLaboratorio;
    form.hdCampoClave3.value = iCveUniMed;
    form.hdRowNum.value = iCveLoteCuantita;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = pgAccion;
    form.submit();
  }


  function fAccion(cAccion){
    form = document.forms[0];
    if(cAccion == "Deshecho" && form.TBXDeshecho.checked)
      if(!confirm("¿Está Seguro de Deshechar las muestras?"))
        return false;
    if(cAccion == "Deshecho" && !form.TBXDeshecho.checked){
      return false;
    }
    if(cAccion == "BuscarD" && !form.TBXDeshecho.checked){
      return false;
    }
    if(cAccion == "BuscarD")
      form.hdBandera.value = "1";
    form.target="_self";
    form.hdBoton.value = cAccion;
    form.submit();
  }

   function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070305060P.jsp?iOpc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&cCombo=" + objDes.name +  "&hdAccion=" + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
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
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave2" value="<%=cClave2%>">
  <input type="hidden" name="hdCampoClave3" value="<%=cClave3%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdIAnio" value="">
  <input type="hidden" name="hdICveUniMed" value="">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="hdCampoClaveA" value="<%=cClaveA%>">
  <input type="hidden" name="hdCampoClaveB" value="<%=cClaveB%>">
  <input type="hidden" name="hdCampoClaveC" value="<%=cClaveC%>">
  <input type="hidden" name="hdBandera"     value="<%=lBandera%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
    // Impresión del Reporte o Listado de Positivas
        if("Reporte".compareToIgnoreCase(clsConfig.getAccion()) ==0) {
           out.println(clsConfig.getActiveX());
        }
        if("Listado".compareToIgnoreCase(clsConfig.getAccion()) ==0 ||
           "Deshecho".equalsIgnoreCase(clsConfig.getAccion())) {
           out.println(clsConfig.getActiveY());
        }
    %>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td valign="top">
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr><td colspan="8" class="ETablaT">Consulta de Muestras Positivas</td></tr>
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
              out.println("<td class='ECampo' colspan=\"2\">  ");
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
          </tr><tr><td class="ETablaST" colspan="4">Filtros Adicionales</td></tr>
          <tr><%
                  out.println(vEti.ObjRadioCE("EEtiqueta","Fecha de:","ETabla", "RSFecha","1","Análisis","1","","","",0,true,true));
           %></tr>
          <tr><%
                  out.println(vEti.EtiCampo("EEtiqueta","De:", "ETabla","text",10,10,"dtFechaI","",0,"","",true,true, true, request));
                  out.println(vEti.EtiCampo("EEtiqueta","Hasta:", "ETabla\" colspan=\"2","text",10,10,"dtFechaF","",0,"","",true,true, true, request));
           %></tr>
          <tr>
            <%
              /*
              String iCveUniMed = request.getParameter("iCveUniMed")!= null  ? request.getParameter("iCveUniMed").toString() : "0";
              String iCveModulo = request.getParameter("iCveModulo")!= null  ? request.getParameter("iCveModulo").toString() : "0";
              out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
              out.println("<td class='ECampo'>");
                out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(2,this.value,'','',document.forms[0].iCveModulo);", vUsuario.getVUniMed(),"iClave","cDescripcion",request,"0",true,true));
              out.println("</td>");
              out.println(vEti.Texto("EEtiqueta","M&oacute;dulo:"));
              out.println("<td class='ECampo'>");
                out.print(vEti.SelectOneRowSinTD("iCveModulo","", new TDGRLModulo().FindByAll("where iCveUniMed = " + iCveUniMed ," order by cDscModulo"),"iCveModulo","cDscModulo",request,"0",true,true));
              out.println("</td>");  */
            %>
          </tr>
          <tr><%
              String cOpcion = "1";
              if(request.getParameter("RSTipo") != null &&
                 request.getParameter("RSTipo").toString().length() > 0)
                 cOpcion = request.getParameter("RSTipo").toString();
              out.println(vEti.ObjRadioCE("EEtiqueta","Buscar:","ETabla", "RSTipo","1","Confirmados",cOpcion,"","","",0,true,true));
              out.println("<td class=\"ETabla\">" + vEti.ObjRadioSE("ETabla", "RSTipo","2","Con alguna sustancia pendiente",cOpcion,"","","",0,true,true) + "</td>");
              out.println("<td class=\"ETabla\">" + vEti.ObjRadioSE("ETabla", "RSTipo","3","Ambos",cOpcion,"","","",0,true,true) + "</td>");
         %></tr><tr><td class="EEtiqueta">Sustancia:</td>
             <td class="ECampo"><%
                  out.println(vEti.SelectOneRowSinTD("iCveSustancia", "", new TDTOXSustancia().FindByAll("","order by cDscSustancia "), "iCveSustancia", "cDscSustancia", true, request, "0", true));
             %>
             </td>
          </tr></tr>
           <tr><%
           if(request.getParameter("RSSituacion") != null &&
                 request.getParameter("RSSituacion").toString().length() > 0)
                 cOpcion = request.getParameter("RSSituacion").toString();
              out.println(vEti.ObjRadioCE("EEtiqueta","Situación de la Muestra:","ETabla\" colspan=3\"", "RSSituacion","1","Indistinto",cOpcion,"fDeshecho('1');","","",0,true,true));
              out.println("<tr><td>&nbsp;</td><td class=\"ETabla\" colspan='3'>" + vEti.ObjRadioSE("ETabla", "RSSituacion","2","En resguardo",cOpcion,"fDeshecho('2');","","",0,true,true) + "</td></tr>");
              out.println("<tr><td>&nbsp;</td><td class=\"ETabla\" colspan='3'>" + vEti.ObjRadioSE("ETabla", "RSSituacion","3","Deshechadas entre el d&iacute;a",cOpcion,"fDeshecho('3');","","",0,true,true) + "</td>");
              %></tr>
              <tr><%
              out.println(vEti.EtiCampo("EEtiqueta","&nbsp;","ETabla","text",10,10,"dtFechaID","",0,"","",true,true, true, request));
              out.println(vEti.EtiCampo("EEtiqueta"," y :", "ETabla","text",10,10,"dtFechaFD","",0,"","",true,true, true, request));
         %></tr>
        <script language="JavaScript">fDeshecho('<%=cOpcion%>');</script>
          <tr><td colspan="8" class="ETablaT">Deshecho de Muestras Positivas</td></tr>
          <% out.print(vEti.ToggleMov("ETablaR", "TBXDeshecho",lDeshecho?"1":"0" ,"fConsDesh(this)", 59, true, "1", "0", true));
             out.println(vEti.EtiCampo("EEtiqueta","Analizadas antes del:","ETabla","text",10,10,"dtFechaAD","",0,"","",true,true, true, request));
             out.println("<td>");
             if("0".equalsIgnoreCase(lBandera))
               out.print(vEti.clsAnclaTexto("EAncla","Buscar muestras a deshechar","JavaScript:fAccion('BuscarD');", "BuscarD"));
             else
               out.print(vEti.clsAnclaTexto("EAncla","Deshechar las muestras","JavaScript:fAccion('Deshecho');", "Deshecho"));
             out.println("</td>");
          %></tr>
         <tr><td class="ETablaST" colspan="4">Generar a Excel:</td></tr>
          <tr><%
                 out.println("<td class='ETablaC' colspan='2'>");
                 out.print(vEti.clsAnclaTexto("EAncla","Reporte de Positivos","JavaScript:fAccion('Reporte');", "Reporte"));
                 out.println("</td><td class='ETablaC' colspan='2'>");
                 out.print(vEti.clsAnclaTexto("EAncla","Listado de Positivos","JavaScript:fAccion('Listado');", "Listado"));
                 out.println("</td>");
         %></tr>
          </table>
      </td>
      </tr>
      <tr>
      <td valign="top">&nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="13" class="ETablaT">Lotes
                              </td>
                            </tr>
                          <% if (bs != null){ %>
                            <tr>
                              <td class="ETablaT">No.</td>
                              <td class="ETablaT">A&ntilde;o</td>
                              <td class="ETablaT">NIM</td>
                              <td class="ETablaT">An&aacute;lisis</td>
                            <!--  <td class="ETablaT">Unidad M&eacute;dica</td>
                              <td class="ETablaT">M&oacute;dulo</td>
                              <td class="ETablaT">Proceso</td>
                              <td class="ETablaT">Motivo</td> -->
                              <td class="ETablaT">Fecha de Recolecci&oacute;n</td>
                              <td class="ETablaT">Estado</td>
                              <td class="ETablaT">Sustancias Confirmadas</td>
                              <td class="ETablaT">Sustancias</td>
                              <td class="ETablaT">Fecha de Deshecho</td>
                            </tr>
                             <% // modificar según listado
                             TVMuestra VMuestra;
                             TFechas Fecha = new TFechas();
                             for(int i=0; i < clsConfig.VRegistros.size(); i++){
                                VMuestra = new TVMuestra();
                                VMuestra = (TVMuestra) clsConfig.VRegistros.get(i);
                                out.print("<tr class=\"ETabla\">");
                                out.println(vEti.Texto("ETablaR", String.valueOf(i + 1)));
                                out.print(vEti.Texto("ETabla",   String.valueOf(VMuestra.getIAnio() )));
                                out.print(vEti.Texto("ETablaR",  String.valueOf(VMuestra.getCMuestra()) ));
                                out.print(vEti.Texto("ETablaR",  VMuestra.getCAnalisis() ));
                               /* out.print(vEti.Texto("ETabla",   VMuestra.getCDscUM() ));
                                out.print(vEti.Texto("ETabla",   VMuestra.getCDscModulo() ));
                                out.print(vEti.Texto("ETabla",   VMuestra.getCDscProceso() ));
                                out.print(vEti.Texto("ETabla",   VMuestra.getCDscMotivo() )); */
                                out.print(vEti.Texto("ETablaC",   Fecha.getFechaDDMMYYYY(VMuestra.getDtRecoleccion(),"/") ));
                                out.print(vEti.Texto("ETablaC",   VMuestra.getISustConf() >= VMuestra.getISustPost() ? "Confirmada":"Pendiente"));
                                out.print(vEti.Texto("ETablaC",   VMuestra.getISustConf() >= VMuestra.getISustPost() ? " " + VMuestra.getISustPost(): "Confirmadas " + String.valueOf(VMuestra.getISustConf()) + " de " + VMuestra.getISustPost()));
                                out.println(vEti.Texto("EEtiquetaPL", clsConfig.getOtrasSust(VMuestra.vResultado)));
                                out.println(vEti.Texto("ETabla", VMuestra.getDtDeshecho() != null ? Fecha.getFechaDDMMYYYY(VMuestra.getDtDeshecho(),"/") : "-"));
                                out.print("</tr>");
                             }
                            %>
                          </table>
                          <% }
                               else{
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                 out.println("</tr>");
                               }

                          %>
    <td></tr>
    <script language="JavaScript">fConsDesh(document.forms[0].TBXDeshecho);</script>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
