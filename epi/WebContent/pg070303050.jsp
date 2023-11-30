<%/**
 * Title: pg070303011
 * Description:Listado para el manejo de muestras
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version 1.0
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>

<html>
<%
  //System.out.println("PASANDO DECLARACIONES");
  pg070303050CFG  clsConfig = new pg070303050CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070303050.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070303050.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "|";    // modificar
  String cCveOrdenar  = "|";  // modificar
  String cDscFiltrar  = "|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  // LLamado al Output Header
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  TCreaBoton bBtn = new TCreaBoton();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "";
  if (request.getParameter("hdBoton") != null){
     if (request.getParameter("hdBoton").toString().equalsIgnoreCase("New_Cal")){
         cUpdStatus  = "SaveCancelOnly"; //clsConfig.getUpdStatus();
     }else{
         cUpdStatus  = "Hidden"; //clsConfig.getUpdStatus();
     }
  }
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  String cFlag_Edit = "0";
  boolean Flag_Pinta = false;
  int iCveEquipo = 0;
  cFlag_Edit = request.getParameter("hdEditar");
  if (cFlag_Edit == null){
     cFlag_Edit = "0";
  }
//System.out.println("PASANDO DECLARACIONES ASIGNE 0 AL FLAG");
////        System.out.println("Bandera de EDICION ==>>> "+cFlag_Edit);

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fValidaTodo(){
    var form = document.forms[0];
    if (form.iCveLoteCualita.length == 0 || form.iCveExamCualita.length == 0 ||
        form.iCveLoteCualita.value == 0 || form.iCveExamCualita.value == 0){
        alert("Favor de asignar valores al lote y al analisis");
    }else{
       if (form.hdBoton.value != 'Cancelar') {
         cVMsg = '';
         if (form.Lote)
           cVMsg = cVMsg + fSinValor(form.iCveLoteCualita,'Lote ', false);
         if (form.iExamen)
           cVMsg = cVMsg + fSinValor(form.iCveExamCualita,'Examen ', false);
           if (cVMsg != ''){
             alert("Datos no Válidos: \n" + cVMsg);
             return false;
           }
       }
        return true;
    }
   }

  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070303050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070303050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }
function fIrEditar(){
    form = document.forms[0];
    if (form.iCveLoteCualita.length == 0 || form.iCveExamCualita.length == 0 ||
        form.iCveLoteCualita.value == 0 || form.iCveExamCualita.value == 0){
        alert("Favor de asignar valores al lote y al analisis");
    }else{
       form.hdEditar.value = 1;
       form.hdBoton.value = "New_Cal";
       form.action = 'pg070303050.jsp';
       form.target =  "_self";
       form.submit();
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
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdEditar" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="ETablaT" colspan="9">Búsqueda del Lote</td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Año:</td>
                              <td class="ETabla">
                                 <select name="iAnio" size="1" onChange="">
                                    <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
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
                                  <%  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                      int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                      out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,document.forms[0].iAnio.value,document.forms[0].iCveUniMed.value,'',document.forms[0].iCveLoteCualita);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                                  %>
                              </td>
                              <td class="EEtiqueta">Lote:</td>
                              <td class="ETabla">
                                 <select name="iCveLoteCualita" size="1" onChange="llenaSLT(2,document.forms[0].iAnio.value,document.forms[0].iCveUniMed.value,document.forms[0].iCveLoteCualita.value,document.forms[0].iCveExamCualita);">
                                  <%
                                     if(request.getParameter("iAnio") != null && request.getParameter("iCveUniMed") != null){
                                       TDLoteCualita dLoteCualita = new TDLoteCualita();
                                       TVLoteCualita vLoteCualita = new TVLoteCualita();
                                       vLoteCualita.setIAnio(new Integer(request.getParameter("iAnio")).intValue());
                                       vLoteCualita.setICveLaboratorio(new Integer(request.getParameter("iCveUniMed")).intValue());
                                       Vector vExCuali = new Vector();
                                       vExCuali = dLoteCualita.FindAllLoteCualita2(vLoteCualita);
                                       if (vExCuali.size() > 0){
                                          out.print("<option value = 0>Seleccione...</option>");
                                          for (int i = 0; i < vExCuali.size(); i++){
                                             vLoteCualita = (TVLoteCualita)vExCuali.get(i);
                                             if (request.getParameter("iCveLoteCualita")!=null&&request.getParameter("iCveLoteCualita").compareToIgnoreCase(new Integer(vLoteCualita.getICveLoteCualita()).toString())==0)
                                                out.print("<option value = " + vLoteCualita.getICveLoteCualita() + " selected>" + vLoteCualita.getICveLoteCualita() + "</option>");
                                             else
                                                out.print("<option value = " + vLoteCualita.getICveLoteCualita() + ">" + vLoteCualita.getICveLoteCualita() + "</option>");
                                          }
                                       }else{
                                          out.print("<option value = 0>Datos no disponibles...</option>");
                                       }
                                     }
                                  %>
                                 </select>
                              </td>

                              <td class="EEtiqueta">Análisis:</td>
                              <td class="ETabla">
                                 <select name="iCveExamCualita" size="1">
                                  <%
                                     if(request.getParameter("iAnio") != null && request.getParameter("iCveUniMed") != null && request.getParameter("iCveLoteCualita") != null){
                                        TDTOXExamenCualita dExamenCualita = new TDTOXExamenCualita();
                                        TVTOXExamenCualita vExamenCualita = new TVTOXExamenCualita();
                                        Vector vExamCualita = new Vector();
                                        String cFiltro = "";
                                        String cOrden  = "";
                                        cFiltro = " WHERE TOXExamenCualita.iAnio = " + request.getParameter("iAnio") +
                                                  "   AND TOXExamenCualita.iCveLaboratorio = " + request.getParameter("iCveUniMed") +
                                                  "   AND TOXExamenCualita.iCveLoteCualita = " + request.getParameter("iCveLoteCualita");
                                        cOrden = " ORDER BY TOXExamenCualita.iCveExamCualita " ;
                                        vExamCualita = dExamenCualita.FindByAll(cFiltro,cOrden);
                                       if (vExamCualita.size() > 0){
                                          out.print("<option value = 0>Seleccione...</option>");
                                          for (int i = 0; i < vExamCualita.size(); i++){
                                             vExamenCualita = (TVTOXExamenCualita)vExamCualita.get(i);
                                             if (request.getParameter("iCveExamCualita")!=null&&request.getParameter("iCveExamCualita").compareToIgnoreCase(new Integer(vExamenCualita.getICveExamCualita()).toString())==0){
                                                out.print("<option value = " + vExamenCualita.getICveExamCualita() + " selected>" + vExamenCualita.getICveExamCualita() + "</option>");
                                                iCveEquipo = vExamenCualita.getICveEquipo();
                                             }
                                             else
                                                out.print("<option value = " + vExamenCualita.getICveExamCualita() + ">" + vExamenCualita.getICveExamCualita() + "</option>");
                                          }
                                       }else{
                                          out.print("<option value = 0>Datos no disponibles...</option>");
                                       }
                                     }
                                  %>
                                 </select>
                              </td>
                              <td>
                              <%
                               out.println(bBtn.clsCreaBoton(vEntorno, 1, "BtnBuscar","bBuscar", vEntorno.getTipoImg(),"Buscar Envios","BtnBuscar", vParametros));
                              %>
                              </td>

                            </tr>
                          </table>
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="11" class="ETablaT">Calibradores del Lote por Sustancia
                              </td>
                            </tr>

      <%
      boolean lEspecial = false;
        if (iCveEquipo == Integer.parseInt(vParametros.getPropEspecifica("TOXEquipoEsp").toString()))
          lEspecial = true;
      %>
      <tr>
        <td class="ETablaT" colspan="2" rowspan="2">Sustancia</td>
        <td class="ETablaT" rowspan="2">Reactivo</td>
        <td class="ETablaT" colspan="4"><%= lEspecial ? "Absorbancia" : "Coeficiente de Correlaci&oacute;n" %></td>
        <td class="ETablaT" rowspan="2">Acción Correctiva</td>
        <td class="ETablaT" rowspan="2">Observación</td>
        <td class="ETablaT" rowspan="2">Actualizado</td>
      </tr>
      <tr>
        <td class="ETablaT"><%= lEspecial ? "Negativo " : "Curva Te&oacute;rica" %></td>
        <td class="ETablaT"><%= lEspecial ? "Corte " : "Curva Experimental" %></td>
        <td class="ETablaT"><%= lEspecial ? "Positivo" : "&nbsp;" %></td>
        <td class="ETablaT">Aceptado</td>
      </tr>
                            <%
                               Vector vTOXListaSust = new Vector();
                               DAOTOXSustancia dSustancia = new DAOTOXSustancia();
                               TDTOXReactivo   dReactivo  = new TDTOXReactivo();
                               TDTOXCalibCualita dCalibrador = new TDTOXCalibCualita();
                               TDTOXAcCorrectiva dCorrectiva = new TDTOXAcCorrectiva();
                               vTOXListaSust = dSustancia.FindByAll();
                               boolean flag = false;
                               String  cApproved="";
                               int idSustA = 0;
                               int ChkRow = 0;
                               int ChkRow2 = 0;
                               int ContRow = 0;
                               Vector vReactivo = new Vector();
                               TFechas Fecha = new TFechas();
                               vReactivo = dReactivo.FindByAll(" Where lCuantCual = 0 and lAgotado = 0 and LBaja = 0 and dtCaducidad >= '" + Fecha.TodaySQL() + "'","");
                               if (bs != null){
                                   bs.firstRow();
                                   bs.start();
                                   while(bs.nextRow()){
                                   if (cFlag_Edit.compareToIgnoreCase("1")!=0){
                                       out.println("<tr>");
                                       out.print("<td>");
                                       out.print("</td>");
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscSustancia", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iCodigo", "&nbsp;")));
                                       //out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscCtrolCalibra", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("dCorteNeg", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("dCorte", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("dCortePost", "&nbsp;")));
                                       if (bs.getFieldValue("lAutorizado", "").toString().equalsIgnoreCase("1")){
                                          out.print(vEti.Texto("EEtiquetaC","Si"));
                                       }else{
                                          out.print(vEti.Texto("EEtiquetaC","No"));
                                       }
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscAcCorrectiva", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cObservacion", "&nbsp;")));
                                       if (bs.getFieldValue("lActualizado", "&nbsp;").toString().equalsIgnoreCase("1")){
                                          out.print(vEti.Texto("ETabla","Si"));
                                       }else{
                                          out.print(vEti.Texto("ETabla","No"));
                                       }
                                       out.println("</tr>");
                                    }else{
                                       if (bs.getFieldValue("lAutorizado", "").toString().equalsIgnoreCase("0") && bs.getFieldValue("lActualizado", "").toString().equalsIgnoreCase("0")){ // en este bloque se determina si esta permitido editar este registro
                                          out.println("<tr>");
                                          out.println("<td>");
                                          out.println("<input type=\"checkbox\" name=\"lActualizarA"+ContRow+"\" value=\"1\" checked readonly=\"true\">");
                                          out.println("</td>");
                                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscSustancia", "&nbsp;")));
                                       %>
                                          <input type="hidden" name="iCveSustancia<%=ContRow%>" value="<%=bs.getFieldValue("iCveSustancia","0").toString()%>">
                                          <input type="hidden" name="iCveCalibCualita<%=ContRow%>" value="<%=bs.getFieldValue("iCveCalibCualita","0").toString()%>">
                                          <input type="hidden" name="iCveCtrolCalibra<%=ContRow%>" value="0">
                                       <%
                                          out.println("<td>");
                                          out.print(vEti.SelectOneRowSinTD("iCveReactivo"+ContRow,"",vReactivo,"iCveReactivo","iCodigo",request,bs.getFieldValue("iCveReactivo", "0").toString()));
                                          out.println("</td>");
                                          //out.println("<td>");
                                          //out.print(vEti.SelectOneRowSinTD("iCveCtrolCalibra"+ContRow,"",dCalibrador.FindByAllc(" Where lCuantCual = 0 "),"iCveCtrolCalibra","cDscBreve",request,bs.getFieldValue("iCveCtrolCalibra", "0").toString()));
                                          //out.println("</td>");
                                          out.println("<td>");
                                          out.print(vEti.CampoSinCelda("Text",5,5,"dCorteNeg"+ContRow,bs.getFieldValue("dCorteNeg", "0").toString(),0,"","",false,true));
                                          out.println("</td>");
                                          out.println("<td>");
                                          out.print(vEti.CampoSinCelda("Text",5,5,"dCorte"+ContRow,bs.getFieldValue("dCorte", "0").toString(),0,"","",false,true));
                                          out.println("</td>");
                                          out.println("<td>");
                                          out.print(vEti.CampoSinCelda("Text",5,5,"dCortePost"+ContRow,bs.getFieldValue("dCortePost", "0").toString(),0,"","",false,true));
                                          out.println("</td>");
                                          out.println("<td>");
                                          out.println("</td>");
                                          out.println("<td>");
                                          out.print(vEti.SelectOneRowSinTD("iCveAcCorrectiva"+ContRow,"",dCorrectiva.FindByAll(),"iCveAcCorrectiva","cDscAcCorrectiva",request,bs.getFieldValue("iCveAcCorrectiva", "0").toString()));
                                          out.println("</td>");
                                          out.println("<td>");
                                          out.print(vEti.CampoSinCelda("Text",50,50,"cObservacion"+ContRow,bs.getFieldValue("cObservacion", "").toString(),0,"","",false,true));
                                          out.println("</td>");
                                          out.println("</tr>");
                                          ContRow++; // Este lleva el no de renglones generados
                                      }
                                   }
                                   out.println("</tr>");
                                 }//Cierra While
                               }//cierra el bs.null
                               if (cFlag_Edit.compareToIgnoreCase("1")==0){
                                  for (int w=0;w<vTOXListaSust.size();w++){ // Esta iteracion sirve para crear el registro addicional
                                      TVTOXSustancia vTOXMI = new TVTOXSustancia();
                                      vTOXMI = (TVTOXSustancia) vTOXListaSust.get(w);

                                      out.println("</tr>");
                                      out.println("<td>");
                                      out.println("<input type=\"checkbox\" name=\"lActualizarB"+ContRow+"\" value=\"1\">");
                                      out.println("</td>");
                                      out.print(vEti.Texto("ETabla",""+ vTOXMI.getcDscSustancia()));
                                       %>
                                          <input type="hidden" name="iCveSustancia<%=ContRow%>" value="<%=vTOXMI.getiCveSustancia()%>">
                                          <input type="hidden" name="iCveCtrolCalibra<%=ContRow%>" value="0">
                                       <%
                                      out.println("<td>");
                                      out.print(vEti.SelectOneRowSinTD("iCveReactivo"+ContRow,"",vReactivo,"iCveReactivo","iCodigo",request,"0"));
                                      out.println("</td>");
                                      //out.println("<td>");
                                      //out.print(vEti.SelectOneRowSinTD("iCveCtrolCalibra"+ContRow,"",dCalibrador.FindByAllc(" Where lCuantCual = 0 "),"iCveCtrolCalibra","cDscBreve",request,"0"));
                                      //out.println("</td>");
                                      out.println("<td>");
                                      out.print(vEti.CampoSinCelda("Text",5,5,"dCorteNeg"+ContRow,"0",0,"","",false,true));
                                      out.println("</td>");
                                      out.println("<td>");
                                      out.print(vEti.CampoSinCelda("Text",5,5,"dCorte"+ContRow,"0",0,"","",false,true));
                                      out.println("</td>");
                                      out.println("<td>");
                                      out.print(vEti.CampoSinCelda("Text",5,5,"dCortePost"+ContRow,"0",0,"","",false,true));
                                      out.println("</td>");
                                      out.println("<td>");
                                      out.println("</td>");
                                      out.println("<td>");
                                      out.print(vEti.SelectOneRowSinTD("iCveAcCorrectiva"+ContRow,"",dCorrectiva.FindByAll(),"iCveAcCorrectiva","cDscAcCorrectiva",request,"0"));
                                      out.println("</td>");
                                      out.println("<td>");
                                      out.print(vEti.CampoSinCelda("Text",50,50,"cObservacion"+ContRow,"",0,"","",false,true));
                                      out.println("</td>");
                                      out.println("</tr>");
                                      ContRow++;
                                  }
                               }
                            %>
                          <input type="hidden" name="hdUserId" value="<%=vUsuario.getICveusuario()%>">
                          <input type="hidden" name="TotRows" value="<%=ContRow%>">
                          </table>
                          &nbsp;
                         <%
                         if(bs == null){
                         if (cFlag_Edit.compareToIgnoreCase("1")!=0){
                         %>
                            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <%
                            out.println("<tr>");
                                out.print("<td class=\"ECampo\">");
                                    out.print(vEti.clsAnclaTexto("EAncla","Nueva Calibracion","JavaScript:fIrEditar();","Ver muestras del envío"));
                                out.print("</td>");
                            out.println("</tr>");
                            %>
                            </table>
                         <%
                         }
                         }
                         if(bs != null){
                         if (cFlag_Edit.compareToIgnoreCase("1")!=0){
                         %>
                            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <%
                            out.println("<tr>");
                                out.print("<td class=\"ECampo\">");
                                    out.print(vEti.clsAnclaTexto("EAncla","Ver Calibracion","JavaScript:fIrEditar();","Ver muestras del envío"));
                                out.print("</td>");
                            out.println("</tr>");
                            %>
                            </table>
                         <%
                         }
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
