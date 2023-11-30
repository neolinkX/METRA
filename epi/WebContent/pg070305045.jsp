<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
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
  pg070305045CFG  clsConfig = new pg070305045CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070305045.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070305045.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Fecha de calibración|Equipo|";    // modificar
  String cCveOrdenar  = "CE.Fecha|CE.iCveEquipo|";  // modificar
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

function fValidaBuscar(){
  var form = document.forms[0];
  var cVMsg = '';
  if( form.iCveLaboratorio){
      //alert("value: " + form.iCveLaboratorio.value);
     if (form.iCveLaboratorio.value <= 0){

      cVMsg = cVMsg + "\n -Debe seleccionar un laboratorio. ";
     }

    if(form.dtFechaI && form.dtFechaI)
      if(!(form.dtFechaI.value=="" && form.dtFechaI.value=="")){
     if (form.dtFechaI)
        cVMsg = cVMsg + fSinValor(form.dtFechaI,5,'Fec. De:', true);

      if (form.dtFechaF)
        cVMsg = cVMsg + fSinValor(form.dtFechaF,5,'Fec. Hasta:', true);

      if (form.dtFechaI){
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


  }

  if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
  }

  return true;

}

  function fIrpg070305041(iAnio, iCveLaboratorio, iCveSustancia, iCveLoteCuantita, pgAccion){
    form = document.forms[0];
    form.hdCampoClave.value = iAnio;
    form.hdLCondicion.value = "";
    form.hdLOrdenar.value = "";
    form.hdCampoClave2.value = iCveLaboratorio;
    form.hdCampoClave3.value = iCveSustancia;
    form.hdRowNum.value = iCveLoteCuantita;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = pgAccion;
    form.submit();
  }
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  // consulta de la calibración
  function fDetalle(iCveEquipo, iCveCalib){
    form = document.forms[0];
    form.iCveEquipo.value = iCveEquipo;
    form.iCveCalib.value  = iCveCalib;
    form.hdBoton.value = 'Detalle';
    form.target = 'FRMDatos';
    form.submit();
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
/*       cPosicion = Integer.toString(bs.pageNo());
       cClave = ""+bs.getFieldValue("iAnio", "");
       cClave2 = ""+bs.getFieldValue("iCveUniMed", "");
       cClave3 = ""+bs.getFieldValue("iCveEnvio", "");
       cClaveA = ""+bs.getFieldValue("iAnio", "");
       cClaveB = ""+bs.getFieldValue("iCveUniMed", "");
       cClaveC = ""+bs.getFieldValue("iCveEnvio", "");
*/
     }
  %>

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
  <input type="hidden" name="iCveEquipo" value="0">
  <input type="hidden" name="iCveCalib"  value="0">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){%>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td valign="top">
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr><td colspan="8" class="ETablaT">B&uacute;squeda del Lote Cuantitativo</td></tr>
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
             <td class="EEtiqueta">Equipo:</td>
             <td class="ETabla">
               <%
                  TDTOXEquipo dTOXEquipo = new TDTOXEquipo();
                  TVTOXEquipo vTOXEquipo = new TVTOXEquipo();
                  Vector vEquipo = new Vector();
                  vEquipo = dTOXEquipo.FindByAll(" WHERE lCuantCual = 1 ","");
                  out.println(vEti.SelectOneRowSinTD("SLSEquipo", "", vEquipo, "iCveEquipo", "cCveEquipo", request, "-1",true,true));
                %>
             </td>
             </tr>
          <tr><%
                  out.println(vEti.EtiCampo("EEtiqueta","Calibrado de:", "ETabla","text",10,10,"dtFechaI","",0,"","",true,true, true, request));
                  out.println(vEti.EtiCampo("EEtiqueta","Hasta:", "ETabla","text",10,10,"dtFechaF","",0,"","",true,true, true, request));
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
                              <td class="ETablaT">Equipo</td>
                              <td class="ETablaT">N&uacute;nmero de Serie</td>
                              <td class="ETablaT">Fecha</td>
                              <td class="ETablaT">Realizada por</td>
                              <td class="ETablaT">Calibración<br>Correcta</td>
                              <td class="ETablaT" colspan="2">Observaci&oacute;n</td>
                            </tr>
                             <% // modificar según listado
                             TVTOXCalibEquipo Calib;
                             TFechas Fecha = new TFechas();
                             for(int i=0; i < clsConfig.VRegistros.size(); i++){
                                Calib = (TVTOXCalibEquipo) clsConfig.VRegistros.get(i);
                                out.print("<tr class=\"ETabla\">");
                                out.print(vEti.Texto("ETablaR",  String.valueOf(i+1)));
                                out.print(vEti.Texto("ETabla",  Calib.getCCveEquipo() != null ? Calib.getCCveEquipo() : "-"));
                                out.print(vEti.Texto("ETabla",  Calib.getCNumSerie() != null ? Calib.getCNumSerie() : "-"));
                                out.print(vEti.Texto("ETablaC",  Calib.getFecha() != null ? Fecha.getFechaDDMMYYYY(Calib.getFecha(),"/") : "-"));
                                out.print(vEti.Texto("ETabla",  Calib.getCNomCalibra() != null ? Calib.getCNomCalibra() : "-"));
                                out.print(vEti.Texto("ETablaC",  Calib.getlCorrecto().intValue() == 1 ? "Sí":"No"));
                                out.println(vEti.Texto("ETabla", Calib.getcObservacion()!= null ? Calib.getcObservacion() : "-"));
                                out.print("<td class=\"ETabla\">");
                                out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fDetalle('" + Calib.getiCveEquipo() + "', '" + Calib.getiCveCalib() + "');", ""));
                                out.print("</td>");
                                out.print("</tr>");
                             }
                            %>
                          </table>
                          <%
                            if(!clsConfig.vDetalleC.isEmpty()){
                              %>  </td></tr>
                                  <tr><td> &nbsp;
                                  </td></tr>
                                    <tr><td>
                                    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                     <tr><td colspan="13" class="ETablaT">Detalle de la Calibraci&oacute;n</td></tr>
                                     <%
                                        Calib = (TVTOXCalibEquipo) clsConfig.vDetalleC.get(0);
                                        out.print("<tr class=\"ETabla\">");
	                                  out.print(vEti.Texto("EEtiqueta","Equipo").append(vEti.Texto("ETabla",  Calib.getCCveEquipo() != null ? Calib.getCCveEquipo() : "-")));
        	                          out.print(vEti.Texto("EEtiqueta","No. Serie:").append(vEti.TextoCS("ETabla",  Calib.getCNumSerie() != null ? Calib.getCNumSerie() : "-",2)));
                                        out.print("</tr><tr class=\"ETabla\">");
                	                 out.print(vEti.Texto("EEtiqueta","Realizada:").append(vEti.Texto("ETablaC",  Calib.getFecha() != null ? Fecha.getFechaDDMMYYYY(Calib.getFecha(),"/") : "-")));
                        	          out.print(vEti.Texto("EEtiqueta","Por:").append(vEti.TextoCS("ETabla",  Calib.getCNomCalibra() != null ? Calib.getCNomCalibra() : "-",2)));
                                        out.print("</tr><tr class=\"ETabla\">");
                                 	 out.print(vEti.Texto("EEtiqueta","Situación:").append(vEti.TextoCS(Calib.getlCorrecto().intValue() == 1 ? "ETabla":"EEtiquetaL",  Calib.getlCorrecto().intValue() == 1 ? "Calibración Correcta":"Calibración incorrecta", 4)));
                                        out.print("</tr><tr class=\"ETabla\">");
	                                 out.println(vEti.Texto("EEtiqueta","Observación:").append(vEti.TextoCS("ETabla", Calib.getcObservacion()!= null ? Calib.getcObservacion() : "-",4)));
                                        out.print("</tr><tr class=\"ETabla\">");
                                %>
                               <td class="ETablaT">Par&aacute;metro</td>
                              <td class="ETablaT">Valor Minimo</td>
                              <td class="ETablaT">Valor Obtenido</td>
                              <td class="ETablaT">Valor Maximo</td>
                              <td class="ETablaT">Resultado</td>
                               <%
                                        out.print("</tr>");
                                        TVDinRep02 vDatos;
                                        for(int i = 0; i < Calib.vParametros.size(); i++){
                                          out.print("<tr>");
                                          vDatos = (TVDinRep02) Calib.vParametros.get(i);
                                          out.print(vEti.Texto("ETabla",  vDatos.get("cDscParam") != null ? vDatos.get("cDscParam").toString() : "-"));
                                          out.print(vEti.Texto("ETablaR",  vDatos.get("dValorMin") != null ? vDatos.get("dValorMin").toString() : "-"));
                                          out.print(vEti.Texto("EEtiqueta",  vDatos.get("dValor") != null ? vDatos.get("dValor").toString() : "-"));
                                          out.print(vEti.Texto("ETablaR",  vDatos.get("dValorMax") != null ? vDatos.get("dValorMax").toString() : "-"));
                                          out.print(vEti.Texto(vDatos.getString("Estilo"),  vDatos.get("cResultado") != null ? vDatos.get("cResultado").toString() : "-"));
                                          out.print("</tr>");
                                        }
                                     %>
                                    </table>
                                  </td></tr>
                              <%
                            } // despliegue del detalle
                          } // existen registros para presentar
                               else{
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                 out.println("</tr>");
                               }

                          %>
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
