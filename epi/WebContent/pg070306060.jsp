<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<%
  pg070306060CFG  clsConfig = new pg070306060CFG();                 // modicar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070306060.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306060.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Número de Serie|No. de Refrigerador|";    // modificar
  String cCveOrdenar  = "cNumSerie|iCveRefrigerador|";  // modificar
  String cDscFiltrar  = "Número de Serie|No. de Refrigerador|";    // modificar
  String cCveFiltrar  = "cNumSerie|iCveRefrigerador|";  // modificar
  String cTipoFiltrar = "7|7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Reporte";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070306060.js)
  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070306060P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
  function fValidaTodo(){
    var form = document.forms[0];
        cVMsg = '';
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }

    if(form.hdBoton.value == 'Guardar'){
      if(form.dTempAmbiente){
        if(form.dTempAmbiente.lenght >= 0){
          if(!fdecimal(form.dTempAmbiente.value){
            cVMsg = cVMsg + "\n - El valor de la Temperatura Ambiente debe ser un valor numérico. \n";
          }
        }
      }
      if(form.dHumedad){
        if(form.dHumedad.length >= 0){
          if(!fdecimal(form.dHumedad.value){
            cVMsg = cVMsg + "\n - El valor de la Humedad debe ser un valor numérico. \n";
          }
        }
      }
    }

    if (form.hdBoton.value == 'Nuevo') {
       //if (form.iCveArea)
       //  cVMsg = cVMsg + fSinValor(form.iCveArea,1,'Área', true);



      if (form.iCveArea){
         if (form.iCveArea.value <= 0 || form.iCveArea.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algún área. \n";
         }
      }

      if (form.iCveTurnoValida){
         if (form.iCveTurnoValida.value <= 0 || form.iCveTurnoValida.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algún turno. \n";
         }
      }

      if (form.dtFechaActual){
        cVMsg = cVMsg + fSinValor(form.dtFechaActual,5,'Fecha:', true);

         if (form.dtFechaActual.value != null){
         Dia  = form.dtFechaActual.value.substring(0,2);
         Mes  = form.dtFechaActual.value.substring(3,5)-1;
         Anio = form.dtFechaActual.value.substring(6,10);
         dtFecApi = new Date(Anio,Mes,Dia);

         Dia  = form.hdHoy.value.substring(0,2);
         Mes  = form.hdHoy.value.substring(3,5)-1;
         Anio = form.hdHoy.value.substring(6,10);
         dtFecHoy = new Date(Anio,Mes,Dia);
          if (dtFecApi > dtFecHoy){
            cVMsg = cVMsg + "\n - La Fecha no puede ser mayor al día de hoy. \n";
           }
         }
      }


      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cPropiedad)
         cVMsg = cVMsg + fSinValor(form.cPropiedad,1,'Propiedad', true);
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }

  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
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
       cClave2  = ""+bs.getFieldValue("iCveArea", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="hdTotalReg" value="">
  <%TFechas dtFecha = new TFechas(); %>
  <input type="hidden" name="hdHoy" value="<%=dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/")%>" >

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Registro de Temperaturas
                              </td>
                            </tr>
                              <tr><% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                                   TFechas oFecha = new TFechas();
                                  String cHoy = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(),"/");
                               %>
                            </tr>
                            <%
                                 TDTOXArea dArea = new TDTOXArea();
                                 out.print(vEti.Texto("EEtiqueta", "Refrigeradores del Área de:"));
                                 out.print("<td>");
                                 out.print(vEti.SelectOneRowSinTD("iCveArea", "llenaSLT(1,this.value,'','',document.forms[0].iCveTurnoValida);", dArea.FindByAll(), "iCveArea", "cDscArea", request, "0", true));
                                 out.print("</td>");

                                 out.print("<tr>");
                                 out.println(vEti.Texto("EEtiqueta","Turno:"));
                                 out.println("<td class='ECampo'>");

                                 if (request.getParameter("iCveArea")!=null){
                                    TDTOXTurnoValida dTOXTurnoValida = new TDTOXTurnoValida();
                                    TVTOXTurnoValida vTOXTurnoValida = new TVTOXTurnoValida();
                                    Vector vcTurnoValida = new Vector();
                                    vTOXTurnoValida.setICveArea(new Integer(request.getParameter("iCveArea")).intValue());
                                    vcTurnoValida = dTOXTurnoValida.FindByAll2(vTOXTurnoValida);
                                    out.print(vEti.SelectOneRowSinTD("iCveTurnoValida", "", vcTurnoValida, "iCveTurnoValida", "cDscTurno", request, request.getParameter("iCveTurnoValida"),true));
                                 }else{
                                    out.println("<SELECT NAME='iCveTurnoValida' SIZE='1' onChange=''>");
                                    out.println("</SELECT>");
                                 }
                                 out.print("</tr>");
                                // if(request.getParameter("hdBoton")!=null && request.getParameter("hdBoton").toString().compareTo("Nuevo")!=0){
                                   out.println("<tr>");
                                   /*if (request.getParameter("dtFechaActual")!=null){
                                       out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtFecha",cHoy,0,"","fMayus(this);",false,true,true,request));
                                       out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtFechaActual", request.getParameter("dtFechaActual"), 4, "", "", true, true, true));
                                   }else{*/
                                       out.print(vEti.EtiCampo("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10, "dtFechaActual","", 4, "", "", true, true, true, request));
                                   //}
                                   out.println("</tr>");
                                // }

                            %>
                          </table><% // Fin de Datos %>
                          <p>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <%
                              int i = 1;
                              if(bs != null){

                            	 /*Se agregaron los campos de Temp. Ambiental y Humedad Relativa */

                            	 out.println("<tr><td class=\"ETablaT\" colspan=\"3\">Temperatura Ambiental y Humedad Relativa</td></tr>");

                            	 out.print("<tr>");
                            	 out.println(vEti.EtiCampo("EEtiqueta","Temperatura Ambiental:","ECampo","text",5,3,"dTempAmbiente","",0,"fMayus(this);","",true,true,lCaptura,request));
                            	 out.print("</tr>");

                            	 out.print("<tr>");
                            	 out.println(vEti.EtiCampo("EEtiqueta","Humedad Relativa:","ECampo","text",3,3,"dHumedad","",0,"fMayus(this);","",true,true,lCaptura,request));
                            	 out.print("</tr>");

                                 bs.start();
                                 out.print("<tr>");
                                 out.print("<td class=\"ETablaT\">Número de Serie</td>");
                                 out.print("<td class=\"ETablaT\">Refrigerador / Congelador</td>");
                                 out.print("<td class=\"ETablaT\">Temperatura</td>");
                                 out.print("</tr>");

                                   while(bs.nextRow()){

                                   /*String cWhere = " where iAnio = " + bs.getField +
                                            " and iCveTurnoValida = " + vTurnoRef.getICveTurnoValida() +
                                            " and iMes = " + vTurnoRef.getIMes() +
                                            " and iDia =  " + vTurnoRef.getIDia();

                                   //        System.out.println("cWhere 306060: " + cWhere);*/
                                     /*Vector vcRegistrado = new Vector();
                                     vcRegistrado = dTurnoRef.FindByAll(cWhere);*/

                                     out.print("<tr>");
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cNumSerie", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscEquipo", "&nbsp;").toString()));
                                     out.print("<input type=\"hidden\" name=\"hdRefrigerador" + i + "\">");
                                     out.print("<script language=\"JavaScript\">form=document.forms[0];form.hdRefrigerador"+i+".value="+bs.getFieldValue("iCveRefrigerador")+";</script>");
                                     if(request.getParameter("hdBoton")!=null && request.getParameter("hdBoton").toString().compareTo("Nuevo")==0){
                                       out.print("<td>");
                                       out.print("<input type=\"text\" name=\"dTemp" + i + "\">");
                                       out.print("</td>");
                                     }else{
                                       out.print(vEti.Texto("ECampo", bs.getFieldValue("dTemperatura", "&nbsp;").toString()));
                                     }
                                     out.print("</tr>");
                                     i++;
                                   }
                                   %><script language='JavaScript'>form.hdTotalReg.value=<%out.print(i);%>;</script><%
                              }
                              else{
                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                out.println("</tr>");
                              }
                            %>
                          </table><% // Fin de Datos %>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
