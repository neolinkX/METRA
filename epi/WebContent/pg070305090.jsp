<%/**
 * Title: Informe de An�lisis Toxicol�gicos realizados.
 * Description: Informe de An�lisis Toxicol�gicos realizados.
 * Copyright: 2006, Agosto 14
 * Company: Micros Personales, S.A. de C.V.
 * @author Itzia Mar�a del Carmen S�nchez M�ndez.
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
  pg070305090CFG  clsConfig = new pg070305090CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070305090.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070305090.jsp\" target=\"FRMCuerpo"); // modificar
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
      //alert("value: " + form.iCveLaboratorio.value);
     if (form.iCveLaboratorio.value <= 0){

      cVMsg = cVMsg + "\n -Debe seleccionar un laboratorio. ";
     }

     if (form.dtFechaI && form.dtFechaI.value != "")
        cVMsg = cVMsg + fSinValor(form.dtFechaI,5,'Fec. De::', true);

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
            cVMsg = cVMsg + "\n - La Fecha de inicial no puede ser mayor a la fecha final. \n";
           }
         }
      }



  }

  if (cVMsg != ''){
          alert("Datos no V�lidos: \n" + cVMsg);
          return false;
  }

  return true;

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
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
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
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
    // Impresi�n del Reporte o Listado de Positivas
        if("Reporte".compareToIgnoreCase(clsConfig.getAccion()) ==0) {
           out.println(clsConfig.getActiveX());
        }
    %>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td valign="top">
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr><td colspan="8" class="ETablaT">Informe de An&aacute;lisis Toxicol&oacute;gicos</td></tr>
          <tr><%
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
          </tr>
          <tr><%
                  out.println(vEti.EtiCampo("EEtiqueta","Periodo de:", "ETabla","text",10,10,"dtFechaI","",0,"","",true,true, true, request));
                  out.println(vEti.EtiCampo("EEtiqueta","Hasta:", "ETabla\" colspan=\"2","text",10,10,"dtFechaF","",0,"","",true,true, true, request));
           %></tr>
          <tr><%
                 out.println("<td class='ETablaC' colspan='4'>");
                 out.print(vEti.clsAnclaTexto("EAncla","Env&iacute;ar a Excel","JavaScript:fAccion('Reporte');", "Reporte"));
                 out.println("</td>");
         %></tr>
          </table>
      </td>
      </tr>
      <tr>
      <td valign="top">&nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="13" class="ETablaT">Reporte
                              </td>
                            </tr>
                          <% if (bs != null){
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "Se envi&oacute; la informaci&oacute;n a Excel.", 3, "", "", true, true, false));
                                 out.println("</tr>");
                              }else{
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado.", 3, "", "", true, true, false));
                                 out.println("</tr>");
                               }

                          %>
    <td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
