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
  pg070305030CFG  clsConfig = new pg070305030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070305030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070305030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Lote Cualitativo|Fecha de Generación|Fecha de Entrega|Fecha de Análisis|Fecha de Autorización|";    // modificar
  String cCveOrdenar  = "LC.iCveLoteCualita|LC.dtGeneracion|EC.dtEntrega|EC.dtProcesado|EC.dtAutorizado|";  // modificar
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
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
  }

  return true;

}


  function fIrCatalogo(cCampoClave, cPropiedad){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070303071.jsp';
    form.submit();
  }

  function fIrpagina(iAnio, iCveLaboratorio, iCveLoteCualita, iCveExamCualita, pgAccion){
    form = document.forms[0];
    form.hdCampoClave.value = iAnio;
    form.hdLCondicion.value = "";
    form.hdLOrdenar.value = "";
    form.iAnio.value = iAnio;
    form.iCveUniMed.value = iCveLaboratorio;
    form.iCveLoteCualita.value = iCveLoteCualita;
    form.iCveExamCualita.value = iCveExamCualita;
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
  <input type="hidden" name="iCveUniMed" value="">
  <input type="hidden" name="iCveExamCualita" value="">

  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="hdCampoClaveA" value="<%=cClaveA%>">
  <input type="hidden" name="hdCampoClaveB" value="<%=cClaveB%>">
  <input type="hidden" name="hdCampoClaveC" value="<%=cClaveC%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){%>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td valign="top">
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr><td colspan="8" class="ETablaT">B&uacute;squeda del Lote Presuntivo</td></tr>
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
              out.println("<td class='ECampo'>  ");
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
             <%
                  out.println(vEti.EtiCampo("EEtiqueta","Lote Presuntivo:", "ETabla","text",4,4,"iCveLoteCualita","",0,"","",true,true, true, request));
                  out.println(vEti.ObjRadioCE("EEtiqueta","Fecha de:","ETabla", "RSFecha","1","Generación","1","","","",0,true,true));
/*                  out.println("<td class=\"ETabla\">" + vEti.ObjRadioSE("ETabla", "RSFecha","2","Entrega","1","","","",0,true,true) + "</td>");
                  out.println("<td class=\"ETabla\">" + vEti.ObjRadioSE("ETabla", "RSFecha","3","Análisis","1","","","",0,true,true) + "</td>");
                  out.println("<td class=\"ETabla\">" + vEti.ObjRadioSE("ETabla", "RSFecha","4","Autorización","1","","","",0,true,true) + "</td>"); */
           %></tr>
          <tr><%
                  out.println(vEti.EtiCampo("EEtiqueta","De:", "ETabla","text",10,10,"dtFechaI","",0,"","",true,true, true, request));
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
                              <td class="ETablaT">Año</td>
                              <td class="ETablaT">Lote</td>
                              <td class="ETablaT">Generaci&oacute;n</td>
                              <td class="ETablaT">Exámenes realizados</td>
                              <td class="ETablaT">Muestras</td>
                              <td class="ETablaT">Autorizado</td>
                              <td class="ETablaT" colspan="2">Pendientes</td>
                            </tr>
                             <% // modificar según listado
                             TVTOXLoteExCualita Lote;
                             TFechas Fecha = new TFechas();
                             for(int i=0; i < clsConfig.VRegistros.size(); i++){
                                Lote = new TVTOXLoteExCualita ();
                                Lote = (TVTOXLoteExCualita) clsConfig.VRegistros.get(i);
                                out.print("<tr class=\"ETabla\">");
                                out.print(vEti.Texto("ETabla",   String.valueOf(Lote.VECualita.getIAnio() )));
                                out.print(vEti.Texto("ETablaR",  String.valueOf(Lote.VECualita.getICveLoteCualita() )));
                                out.print(vEti.Texto("ETablaC",  Lote.getDtGeneracion()           != null? Fecha.getFechaDDMMYYYY(Lote.getDtGeneracion(),"/") : "-" ));
                                out.print(vEti.Texto("ETablaR",  String.valueOf(Lote.getINumExamen() )));
                                out.print(vEti.Texto("ETablaR",  String.valueOf(Lote.getIAnalizados())));
                                out.print(vEti.Texto("ETablaR",  String.valueOf(Lote.getIAnalizados())));
                                out.print(vEti.Texto("ETablaR",  String.valueOf(Lote.getIPendientes())));
                                out.print("<td class=\"ETabla\">");
                                out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrpagina('" + Lote.VECualita.getIAnio() + "', '" + Lote.VECualita.getICveLaboratorio() + "', '" + Lote.VECualita.getICveLoteCualita() + "', '1', 'pg070305051.jsp');", ""));
                                out.print("</td>");
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
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
