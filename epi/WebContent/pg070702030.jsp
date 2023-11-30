<%/**
 * Title:       Programación de Mantenimientos
 * Description: Programación de Mantenimientos
 * Copyright:   2004
 * Company:     Micros Personales
 * @author      Marco Antonio Hernández García
 * @version     1.0
 * Clase:       pg070702030.jsp
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.StringTokenizer"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070702030CFG  clsConfig = new pg070702030CFG();

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070702030.jsp");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070702030.jsp\" target=\"FRMCuerpo");
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";
  String cOperador    = "1";
  String cDscOrdenar  = "Vehículo|";
  String cCveOrdenar  = "VEHVehiculo.iCveVehiculo|";
  String cDscFiltrar  = "Vehículo|";
  String cCveFiltrar  = "VEHVehiculo.iCveVehiculo|";
  String cTipoFiltrar = "7|";
  boolean lFiltros    = true;
  boolean lIra        = false;
  String cEstatusIR   = "Imprimir";

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
  String cUpdStatus  = "SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  Vector vMantProg = new Vector();
  Vector vMantNProg = new Vector();

  if (clsConfig.getMantProg()!=null)
     vMantProg = (Vector)clsConfig.getMantProg();
  if (clsConfig.getNMantProg()!=null)
     vMantNProg = (Vector)clsConfig.getNMantProg();

  TDVEHTpoMantto dVEHTpoMantto = new TDVEHTpoMantto();
  Vector vTemp = new Vector();

  int iAnioIni = new Integer(vParametros.getPropEspecifica("iAniosIni")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnioFin = dtFecha.getIntYear(dtFecha.TodaySQL())+1;
  String cMeses = vParametros.getPropEspecifica("NombresMes");
  StringTokenizer st = new StringTokenizer(cMeses,",");
  int i = 0;
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  int iCveProceso     = Integer.parseInt(vParametros.getPropEspecifica("CtrlVeh")); // 7
  int iCveEtapa       = Integer.parseInt(vParametros.getPropEspecifica("CTREtapaInicial")); // 1
  int iCveSolicitante = Integer.parseInt(vParametros.getPropEspecifica("CTRSolicitanteIni")); // 1
  DecimalFormat df = new DecimalFormat("#,##0");

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
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
  <input type="hidden" name="iCveUsuSesion" value="<%=vUsuario!=null?vUsuario.getICveusuario():0%>">
  <input type="hidden" name="iCveProceso" value="<%=iCveProceso%>">
  <input type="hidden" name="iCveEtapa" value="<%=iCveEtapa%>">
  <input type="hidden" name="iCveSolicitante" value="<%=iCveSolicitante%>">
  <input type="hidden" name="cToday" value="<%=dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/")%>">
  <input type="hidden" name="cDscTpoMantto" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
          <input type="hidden" name="hdBoton" value="">
          &nbsp;
     <%  if (request.getParameter("hdBoton")!=null && request.getParameter("hdBoton").toString().compareTo("Guardar")!=0){  %>
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="6">Programar Mantenimiento</td>
            </tr>
            <tr>
               <%
                out.println("<td class=\"EEtiqueta\">Tipo de <br> Mantenimiento:</td>");
                out.println("<td colspan=\"1\">");
                vTemp = dVEHTpoMantto.FindByAll("","");
                out.print(vEti.SelectOneRowSinTD("iCveTpoMantto","",vTemp,"iCveTpoMantto","cDscBreve",request,"0",true));
                out.println("</td>");
                out.print(vEti.EtiCampo("EEtiqueta","Fecha de Solicitud:","ECampo","text",10,10,"dtSolicitud","",0,"","fMayus(this);",false,true,true,request));
                out.print(vEti.EtiCampo("EEtiqueta","Fecha Programada:","ECampo","text",10,10,"dtProgramada","",0,"","fMayus(this);",false,true,true,request));
               %>
            </tr>
          </table>
          &nbsp;

          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="2">Mostrar Vehículo</td>
            </tr>
            <tr>
                  <%
                  out.println("<td class=\"EEtiquetaL\">");
                  if (request.getParameter("lTpoVeh")!=null && request.getParameter("lTpoVeh").toString().compareTo("1")==0)
                       out.println("<input type=\"checkbox\" name=\"lTpoVeh\" value=\"1\" onclick=\"Activar1(document.forms[0].iCveTpoVehiculo,!this.checked);\" checked> Tipo de Vehículo:");
                  else
                       out.println("<input type=\"checkbox\" name=\"lTpoVeh\" value=\"1\" onclick=\"Activar1(document.forms[0].iCveTpoVehiculo,!this.checked);\"> Tipo de Vehículo:");
                  out.println("</td>");
                  out.println("<td>");
                  TDVEHTpoVehiculo dVEHTpoVehiculo = new TDVEHTpoVehiculo();
                  TVVEHTpoVehiculo vVEHTpoVehiculo = new TVVEHTpoVehiculo();
                  Vector vTpoVehiculo = new Vector();
                  vTpoVehiculo = dVEHTpoVehiculo.FindByAll();
                  if (vTpoVehiculo.size()>0)
                     out.print(vEti.SelectOneRowSinTD("iCveTpoVehiculo","",vTpoVehiculo,"iCveTpoVehiculo","cDscBreve",request,"0",true));
                  else{
                     out.println("<SELECT NAME=\"iCveTpoVehiculo\" SIZE=\"1\">");
                     out.println("<option value=\"0\">Datos no disponibles</option>");
                     out.println("</SELECT>");
                  }
                  out.println("</td>");
                 %>
            </tr>
            <tr>
                <%
                  out.println("<td class=\"EEtiquetaL\">");
                  if (request.getParameter("lMarca")!=null && request.getParameter("lMarca").toString().compareTo("1")==0)
                       out.println("<input type=\"checkbox\" name=\"lMarca\" value=\"1\" onclick=\"Activar1(document.forms[0].iCveMarca,!this.checked);\" checked> Marca:");
                  else
                       out.println("<input type=\"checkbox\" name=\"lMarca\" value=\"1\" onclick=\"Activar1(document.forms[0].iCveMarca,!this.checked);\"> Marca:");
                  out.println("</td>");
                  out.println("<td>");
                  TDVEHMarca dVEHMarca = new TDVEHMarca();
                  TVVEHMarca vVEHMarca = new TVVEHMarca();
                  Vector vMarca = new Vector();
                     vMarca = dVEHMarca.FindByAll();
                  if (vMarca.size()>0)
                     out.print(vEti.SelectOneRowSinTD("iCveMarca","",vMarca,"iCveMarca","cDscBreve",request,"0",true));
                  else{
                     out.println("<SELECT NAME=\"iCveMarca\" SIZE=\"1\">");
                     out.println("<option value=\"0\">Datos no disponibles</option>");
                     out.println("</SELECT>");
                  }
                  out.println("</td>");
              %>
            </tr>
            <tr>
                <%
                  out.println("<td class=\"EEtiquetaL\">");
                  if (request.getParameter("lTpoMantto")!=null && request.getParameter("lTpoMantto").toString().compareTo("1")==0)
                       out.println("<input type=\"checkbox\" name=\"lTpoMantto\" value=\"1\" onclick=\"Activar2(!this.checked);\" checked> Basado en Mantenimientos:");
                  else
                       out.println("<input type=\"checkbox\" name=\"lTpoMantto\" value=\"1\" onclick=\"Activar2(!this.checked);\"> Basado en Mantenimientos:");
                  out.println("</td>");
                  out.println("<td class=\"EEtiquetaL\">");
                     out.println("<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
                      out.println("<tr>");
                       out.println("<td class=\"EEtiquetaL\" colspan=\"2\">Tipo de Mantenimiento:");
                       vTemp = dVEHTpoMantto.FindByAll("","");
                       out.print(vEti.SelectOneRowSinTD("iCveTpoMantto2","",vTemp,"iCveTpoMantto","cDscBreve",request,"0",true));
                       out.println("</td>");
                      out.println("</tr>");

                      out.println("<tr>");
                        out.println("<td class=\"EEtiquetaL\">");
                         if (request.getParameter("Mant1")!=null && request.getParameter("Mant1").toString().compareTo("1")==0)
                             out.println("<input type=\"radio\" name=\"Mant1\" value=\"1\" onclick=\"Activar3(!this.checked,this.checked);\" checked>Ultimo Mantto. Realizado:");
                         else
                             out.println("<input type=\"radio\" name=\"Mant1\" value=\"1\" onclick=\"Activar3(!this.checked,this.checked);\">Ultimo Mantto. Realizado:");
                       out.println("</td>");

                        out.println("<td class=\"EEtiquetaL\">");
                        if (request.getParameter("Prov")!=null && request.getParameter("Prov").toString().compareTo("1")==0){
                          out.println("<input type=\"radio\" value=\"1\" checked name=\"Prov\">Kilometraje<br>");
                          out.println("<input type=\"radio\" value=\"2\" name=\"Prov\">Vencimiento de Periodo");
                        }else{
                          out.println("<input type=\"radio\" value=\"1\" name=\"Prov\">Kilometraje<br>");
                          out.println("<input type=\"radio\" value=\"2\" checked name=\"Prov\">Vencimiento de Periodo");
                        }
                       out.println("</td>");



                      out.println("<tr>");
                        out.println("<td class=\"EEtiquetaL\">");
                         if (request.getParameter("Mant1")!=null && request.getParameter("Mant1").toString().compareTo("2")==0)
                             out.println("<input type=\"radio\" name=\"Mant1\" value=\"2\" onclick=\"Activar3(this.checked,!this.checked);\" checked>Especificación del Proveedor:");
                         else
                             out.println("<input type=\"radio\" name=\"Mant1\" value=\"2\" onclick=\"Activar3(this.checked,!this.checked);\">Especificación del Proveedor:");
                       out.println("</td>");

                       out.println("<td class=\"EEtiqueta\">Año:");
                       out.println("<select name=\"iAnio\" size=\"1\">");
                       for (int j = iAnioIni; j <= iAnioFin; j++){
                         if (request.getParameter("iAnio")!=null && request.getParameter("iAnio").toString().compareTo(""+j)==0)
                            out.print("<option value = " + j + " selected>" + j + "</option>");
                         else
                            out.print("<option value = " + j + ">" + j + "</option>");
                       }
                       out.println("</select>");
                       out.println("Mes:<select name=\"iMes\" size=\"1\">");
                       while (st.hasMoreTokens()) {
                          ++i;
                          if (request.getParameter("iMes")!=null && request.getParameter("iMes").toString().compareTo(""+i)==0)
                             out.print("<option value = " + i + " selected>" + st.nextToken() + "</option>");
                          else
                             out.print("<option value = " + i + ">" + st.nextToken() + "</option>");
                       }
                       out.println("</select>");
                       out.println("</td>");
                      out.println("</tr>");
                    out.println("</td>");
                   out.println("</table>");
              %>
            </tr>

            <tr>
              <%
                  out.println("<td class=\"EEtiqueta\">Ubicación en Unidad Médica:</td>");
                  out.println("<td>");
                  if (vUsuario!=null)
                     out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                  out.println("</td>");
              %>
            </tr>
          </table>

          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="12">Vehículos Encontrados</td>
            </tr>
            <tr>
              <td class="ETablaT">Clave</td>
              <td class="ETablaT">Tipo Vehículo</td>
              <td class="ETablaT">Marca</td>
              <td class="ETablaT">Modelo</td>
              <td class="ETablaT">Núm. Serie</td>
              <td class="ETablaT">Inventario</td>
              <td class="ETablaT">Placas</td>
              <td class="ETablaT">Unidad Médica</td>
              <td class="ETablaT">Núm.<br>Ultimo<br>Mantto.</td>
              <td class="ETablaT">Kilómetros <br>Recorridos</td>
              <td class="ETablaT">Fecha<br>Ultimo<br>Mantto.</td>
              <td class="ETablaT">Programar</td>
            </tr>
            <% boolean lDespliega = true;
               if (bs != null){
                   bs.start();
                   int iMesToday = dtFecha.getIntMonth(dtFecha.TodaySQL());
                   int iMesIni = 0;
                   double km = 0;
                   int iMes = 0;
                   int iKmMantto = 0;
                   while(bs.nextRow()){
                      iMes = new Integer(bs.getFieldValue("iMesMantto", "0").toString()).intValue();
                      km   = new Double(bs.getFieldValue("iKmFinal", "0").toString()).doubleValue() -
                             new Double(bs.getFieldValue("iKilometraje", "0").toString()).doubleValue();

/*kilometraje*/       if (request.getParameter("Prov")!=null && request.getParameter("Prov").toString().compareTo("1")==0){
                          lDespliega = false;
                          if (new Double(bs.getFieldValue("iKmMantto", "0").toString()).doubleValue() <= km)
                             lDespliega = true;
                      }else

/*Vencimiento de */    if (request.getParameter("Prov")!=null && request.getParameter("Prov").toString().compareTo("2")==0){
/*Periodo*/               iMesIni = 0;
                          lDespliega = false;
                          if (bs.getFieldValue("dtInicia")!=null && bs.getFieldValue("dtInicia","0").toString().compareTo("")!=0){
                             // Con Mantenimientos
                             iMesIni = new Integer(bs.getFieldValue("dtInicia").toString().substring(3,5)).intValue();
                          }else{
                             // Sin Mantenimientos
                             if (bs.getFieldValue("dtIniMantto")!=null && bs.getFieldValue("dtIniMantto","0").toString().compareTo("")!=0){
                                iMesIni = new Integer(bs.getFieldValue("dtIniMantto").toString().substring(3,5)).intValue();
                             }
                          }
                          if ((iMesToday - iMesIni) >= iMes)
                             lDespliega = true;
                       }else

/*Especificación del */if (request.getParameter("Mant1")!=null && request.getParameter("Mant1").toString().compareTo("2")==0){
/*Proveedor*/             Date dFechaIni;
                          Date dFechaFin;
                          Date dFechaInicial;
                          iMes = new Integer(request.getParameter("iMes")).intValue();
                          int iAnio = new Integer(request.getParameter("iAnio")).intValue();
                          dFechaIni = dtFecha.getDateSQL("1",""+iMes,""+iAnio);
                          lDespliega = false;
                          if (iMes == 12){
                             iMes = 01;
                             iAnio++;
                          }else
                             iMes++;
                          dFechaFin = dtFecha.getDateSQL("1",""+iMes,""+iAnio);
                          dFechaFin = dtFecha.aumentaDisminuyeDias(dFechaFin,-1);

                          if (bs.getFieldValue("dtInicia")!=null && bs.getFieldValue("dtInicia","").toString().compareTo("")!=0){
                             // Con Mantenimientos
                             dFechaInicial = dtFecha.getDateSQL(bs.getFieldValue("dtInicia").toString());
                             if (dFechaInicial.compareTo(dFechaIni)>=0  && dFechaInicial.compareTo(dFechaFin)<=0)
                                lDespliega = true;
                          }
                       }

                       if (lDespliega){
                          out.println("<tr>");
                          out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveVehiculo", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscTpoVehiculo", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscMarca", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscModelo", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNumSerie", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cInventario", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cPlacas", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscUniMed", "&nbsp;")));
                          out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveMantenimiento", "&nbsp;")));
                          out.print(vEti.Texto("ETablaR",""+ df.format(Double.parseDouble(""+km))));
                          out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("dtInicia", "&nbsp;")));
                          out.print("<td class=\"ECampoC\">");
                          out.print("<input type=\"checkbox\" name=\"check"+bs.getFieldValue("iCveVehiculo", "0")+"\" value=\"1\">");
                          out.print("</td>");
                       }
                   }
               }else{
                 out.println("<tr>");
                 out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 10,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                 out.println("</tr>");
               }
            %>
          </table>
          <script language="JavaScript">
            form = document.forms[0];
            form.iCveTpoVehiculo.disabled  = !form.lTpoVeh.checked;
            form.iCveMarca.disabled        = !form.lMarca.checked;
            form.iCveTpoMantto2.disabled   = !form.lTpoMantto.checked;
            form.Mant1[0].disabled         = !form.lTpoMantto.checked;
            form.Mant1[1].disabled         = !form.lTpoMantto.checked;
            form.Prov[0].disabled          = !form.Mant1[0].checked;
            form.Prov[1].disabled          = !form.Mant1[0].checked;
            form.iAnio.disabled         = !form.Mant1[1].checked;
            form.iMes.disabled       = !form.Mant1[1].checked;
          </script>
     <%  }else{  %>
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="5">Mantenimientos Programados</td>
            </tr>
            <tr>
              <td class="ETablaT">Vehículo</td>
              <td class="ETablaT">Placa</td>
              <td class="ETablaT">Núm. Mantto.</td>
              <td class="ETablaT">Tipo de Mantenimiento</td>
              <td class="ETablaT">Fecha de Programación</td>
            </tr>
            <%
               TVVEHMantenimiento vVEHMantProg  = new TVVEHMantenimiento();
               if (vMantProg.size()>0){
                  for(int j=0;j<vMantProg.size();j++){
                     vVEHMantProg = (TVVEHMantenimiento)vMantProg.get(j);
                     out.println("<tr>");
                     out.print("<td class=\"ETablaR\">"+vVEHMantProg.getICveVehiculo()+"</td>");
                     out.print("<td class=\"ETabla\">"+vVEHMantProg.getCPlacas()+"</td>");
                     out.print("<td class=\"ETablaR\">"+vVEHMantProg.getICveMantenimiento()+"</td>");
                     out.print("<td class=\"ETabla\">"+vVEHMantProg.getCDscTpoMantto()+"</td>");
                     out.print("<td class=\"ETablaC\">"+dtFecha.getFechaDDMMYYYY(vVEHMantProg.getDtProgramado(),"/")+"</td>");
                     out.println("</tr>");
                  }
               }
               else{
                 out.println("<tr>");
                 out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 4,"Mensaje", "Ya existen mantenimientos de este tipo programados en la fecha indicada.", 3, "", "", true, true, false));
                 out.println("</tr>");
               }

            %>
          </table>
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="5">Mantenimientos Programados con Anterioridad</td>
            </tr>
            <tr>
              <td class="ETablaT">Vehículo</td>
              <td class="ETablaT">Placa</td>
              <td class="ETablaT">Núm. Mantto.</td>
              <td class="ETablaT">Tipo de Mantenimiento</td>
              <td class="ETablaT">Fecha de Programación</td>
            </tr>
            <%
               TVVEHMantenimiento vVEHNMantProg  = new TVVEHMantenimiento();
               if (vMantNProg.size()>0){
                  for(int j=0;j<vMantNProg.size();j++){
                     vVEHNMantProg = (TVVEHMantenimiento)vMantNProg.get(j);
                     out.println("<tr>");
                     out.print("<td class=\"ETablaR\">"+vVEHNMantProg.getICveVehiculo()+"</td>");
                     out.print("<td class=\"ETabla\">"+vVEHNMantProg.getCPlacas()+"</td>");
                     out.print("<td class=\"ETablaR\">"+vVEHNMantProg.getICveMantenimiento()+"</td>");
                     out.print("<td class=\"ETabla\">"+vVEHNMantProg.getCDscTpoMantto()+"</td>");
                     out.print("<td class=\"ETablaC\">"+dtFecha.getFechaDDMMYYYY(vVEHNMantProg.getDtProgramado(),"/")+"</td>");
                     out.println("</tr>");
                  }
               }
               else{
                 out.println("<tr>");
                 out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 4,"Mensaje", "No existen mantenimientos programados anteriormente.", 3, "", "", true, true, false));
                 out.println("</tr>");
               }
            %>
          </table>
     <%  }%>
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

