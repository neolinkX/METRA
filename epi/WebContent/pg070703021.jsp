<%/**
 * Title:        Asignación de Vehículos
 * Description:  Asignación de Vehículos
 * Copyright:    2004
 * Company:      Micros Personales S.A. de C.V.
 * @author       Marco Antonio Hernández García
 * @version      1.0
 * Clase:        pg070703021.jsp
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>

<html>
<%
  pg070703021CFG  clsConfig = new pg070703021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070703021.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070703021.jsp\" target=\"FRMCuerpo");
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg0702061.jsp";
  String cOperador    = "1";
  String cDscOrdenar  = "Vehículo|Núm.Serie|Placas|";
  String cCveOrdenar  = "VEHVehiculo.iCveVehiculo|VEHVehiculo.cNumSerie|VEHVehiculo.cPlacas|";
  String cDscFiltrar  = "Vehículo|Núm.Serie|Placas|";
  String cCveFiltrar  = "VEHVehiculo.iCveVehiculo|VEHVehiculo.cNumSerie|VEHVehiculo.cPlacas|";

  String cTipoFiltrar = "7|8|8|";
  boolean lFiltros    = true;
  boolean lIra        = true;
  String cEstatusIR   = "Imprimir";

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
  if (request.getParameter("hdBoton").toString().compareTo("Validar")==0)
     cUpdStatus  = "SaveOnly";

  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  String cTemp = "";
  TFechas dtFecha = new TFechas();
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  int iCveProceso     = Integer.parseInt(vParametros.getPropEspecifica("CtrlVeh")); // 7
  int iCveEtapa       = Integer.parseInt(vParametros.getPropEspecifica("CTREtapaInicial")); // 1
  int iCveSolicitante = Integer.parseInt(vParametros.getPropEspecifica("CTRSolicitanteIni")); // 1
  String cFiltro = "";
  String cOrden  = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070703021.js"%>"></SCRIPT>
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
     Vector vTemp = new Vector();
     if (bs != null){
       vTemp = bs.getPageVector();
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">

  <input type="hidden" name="RadioCheck" value="">

  <input type="hidden" name="iAnio" value="<% if (request.getParameter("iAnio") != null) out.print(request.getParameter("iAnio"));%>">
  <input type="hidden" name="iCveUniMed" value="<% if (request.getParameter("iCveUniMed") != null) out.print(request.getParameter("iCveUniMed"));%>">
  <input type="hidden" name="iCveSolicitud" value="<% if (request.getParameter("iCveSolicitud") != null) out.print(request.getParameter("iCveSolicitud"));%>">

  <input type="hidden" name="iCveUsuSesion" value="<%=vUsuario!=null?vUsuario.getICveusuario():0%>">
  <input type="hidden" name="iCveProceso" value="<%=iCveProceso%>">
  <input type="hidden" name="iCveEtapa" value="<%=iCveEtapa%>">
  <input type="hidden" name="iCveSolicitante" value="<%=iCveSolicitante%>">
  <input type="hidden" name="cToday" value="<%=dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/")%>">

  <input type="hidden" name="dtSolicitudDe" value="<% if (request.getParameter("dtSolicitudDe") != null) out.print(request.getParameter("dtSolicitudDe"));%>">
  <input type="hidden" name="dtSolicitudA" value="<% if (request.getParameter("dtSolicitudA") != null) out.print(request.getParameter("dtSolicitudA"));%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
    <tr>
      <td valign="top">
          <input type="hidden" name="hdBoton" value="">
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="6">Datos de la Solicitud</td>
            </tr>
            <%
              int iVehiculo = 0;
              int iTipo   = 0;
              int iMarca  = 0;
              int iModelo = 0;
              TVVEHSolicitud vVEHSolicitud = new TVVEHSolicitud();
              vVEHSolicitud = (TVVEHSolicitud)clsConfig.getSolicitud();
              if (vVEHSolicitud.getICveTpoVehiculo()>=0){
                  iTipo = vVEHSolicitud.getICveTpoVehiculoVEH();
                  iMarca = vVEHSolicitud.getICveMarca();
                  iModelo = vVEHSolicitud.getICveModelo();
                  iVehiculo = vVEHSolicitud.getICveVehiculo();
                  out.print("<tr>");
                  out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",10,10,"", vVEHSolicitud.getIAnio()+"",0,"","fMayus(this);",false,true,false));
                  out.print("<td>&nbsp;</td>");
                  out.print("<td>&nbsp;</td>");
                  out.print(vEti.EtiCampo("EEtiqueta","Núm. Solicitud:","ECampo","text",10,10,"", vVEHSolicitud.getICveSolicitud()+"",0,"","fMayus(this);",false,true,false));
                  out.print("</tr>");

                  out.print("<tr>");
                  out.print(vEti.EtiCampo("EEtiqueta","Unidad Médica:","ECampo","text",10,10,"", vVEHSolicitud.getCDscUniMed(),0,"","fMayus(this);",false,true,false));
                  out.print(vEti.EtiCampo("EEtiqueta","Módulo:","ECampo","text",10,10,"", vVEHSolicitud.getCDscModulo(),0,"","fMayus(this);",false,true,false));
                  out.print(vEti.EtiCampo("EEtiqueta","Área:","ECampo","text",10,10,"", vVEHSolicitud.getCDscArea(),0,"","fMayus(this);",false,true,false));
                  out.print("</tr>");

                  out.print("<tr>");
                  if (vVEHSolicitud.getDtRegistro()!=null && vVEHSolicitud.getDtRegistro().toString().compareTo("")!=0 &&
                      vVEHSolicitud.getDtRegistro().toString().compareTo("null")!=0)
                     cTemp = dtFecha.getFechaDDMMYYYY(vVEHSolicitud.getDtRegistro(),"/");
                  else
                     cTemp = "&nbsp;";
                  out.print(vEti.EtiCampo("EEtiqueta","Fecha Registro:","ECampo","text",10,10,"", cTemp+"",0,"","fMayus(this);",false,true,false));

                  if (vVEHSolicitud.getDtSolicitud()!=null && vVEHSolicitud.getDtSolicitud().toString().compareTo("")!=0 &&
                      vVEHSolicitud.getDtSolicitud().toString().compareTo("null")!=0)
                     cTemp = dtFecha.getFechaDDMMYYYY(vVEHSolicitud.getDtSolicitud(),"/");
                  else
                     cTemp = "&nbsp;";
                  out.print(vEti.EtiCampo("EEtiqueta","Fecha y Hora de <br> Solicitud:","ECampo","text",10,10,"", cTemp+"",0,"","fMayus(this);",false,true,false));
                  int iDias = vVEHSolicitud.getITmpAsignado()/24;
                  int iHoras = (vVEHSolicitud.getITmpAsignado()-(iDias*24));
                  out.print(vEti.EtiCampo("EEtiqueta","Tiempo Requerido:","ECampo","text",10,10,"", iDias+" días, "+ iHoras +" hrs.",0,"","fMayus(this);",false,true,false));
                  out.print("</tr>");

                  out.print("<tr>");
                  out.print(vEti.EtiCampo("EEtiqueta","Usuario Solicita:","ECampo","text",10,10,"", vVEHSolicitud.getCDscUsuSolic(),0,"","fMayus(this);",false,true,false));
                  if (vVEHSolicitud.getCDscUsuAsigna()==null || vVEHSolicitud.getCDscUsuAsigna().equals("null"))
                     cTemp = "&nbsp;";
                  else
                     cTemp =  vVEHSolicitud.getCDscUsuAsigna();
                  out.print(vEti.EtiCampo("EEtiqueta","Usuario Asignó:","ECampo","text",10,10,"", cTemp,0,"","fMayus(this);",false,true,false));

                  if (vVEHSolicitud.getDtAsignado()!=null && vVEHSolicitud.getDtAsignado().toString().compareTo("")!=0 &&
                      vVEHSolicitud.getDtAsignado().toString().compareTo("null")!=0)
                     cTemp = dtFecha.getFechaDDMMYYYY(vVEHSolicitud.getDtAsignado(),"/");
                  else
                     cTemp = "&nbsp;";

                  out.print(vEti.EtiCampo("EEtiqueta","Fecha de Asignación:","ECampo","text",10,10,"", cTemp+"",0,"","fMayus(this);",false,true,false));
                  out.print("</tr>");

                  out.print("<tr>");
                  out.print(vEti.EtiCampo("EEtiqueta","Motivo:","ECampo","text",10,10,"", vVEHSolicitud.getCDscMotivo(),0,"","fMayus(this);",false,true,false));
                  out.print(vEti.EtiCampoCS("EEtiqueta","Destino:","ECampo","text",10,10,3,"", vVEHSolicitud.getCDestino(),0,"","fMayus(this);",false,true,false));
                  out.print("</tr>");

                  out.print("<tr>");
                  out.print(vEti.EtiCampoCS("EEtiqueta","Tipo de Vehículo:","ECampo","text",10,10,5,"", vVEHSolicitud.getCDscBreveTpoVeh(),0,"","fMayus(this);",false,true,false));
                  out.print("</tr>");

              }
              else{
                out.println("<tr>");
                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos para la Solicitud especificada", 3, "", "", true, true, false));
                out.println("</tr>");
              }
           %>
          </table>
         <input type="hidden" name="iCveVEHAsignado" value="<%=iVehiculo%>">
          &nbsp;
          <%if (request.getParameter("hdBoton").toString().compareTo("Validar")==0){%>
          <table border="1" class="ETablaInfo" align="center">
              <%  Vector vAlarmas = new Vector();
                  vAlarmas = (Vector)clsConfig.getAlarmas();
                  if(vAlarmas.size()>0){
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\" colspan=\"2\">Alarmas</td>");
                     out.println("</tr>");
                     out.println("<tr>");
                     for (int i = 0; i<vAlarmas.size(); i++){
                        out.print("<td class=\"EEtiqueta\" colspan=\"2\">"+vAlarmas.get(i)+"</td>");
                     }
                     out.println("</tr>");
                  }
                  else{
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\" colspan=\"2\">No existen problemas con esta Asignación!</td>");
                     out.println("</tr>");
                  }
              %>
         </table>
          <%}
            if (vTemp.size()>0){
          %>
         &nbsp;
          <table border="1" class="ETablaInfo" align="center">
            <tr>
              <td>
                 <%  out.print(vEti.clsAnclaTexto("EAncla","Validar","JavaScript:fValidar();", "Validar")); %>
               </td>
            </tr>
         </table>

          <%}%>
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="12">Listado de Vehículos</td>
            </tr>

            <tr>
            <%
                out.println("<td class=\"EEtiqueta\" class=\"EEtiqueta\" colspan=\"2\">Tipo de Vehículo:</td>");
                out.println("<td class=\"ECampo\" colspan=\"2\">");
                TDVEHTpoVehiculo dVEHTpoVehiculo = new TDVEHTpoVehiculo();
                TVVEHTpoVehiculo vVEHTpoVehiculo = new TVVEHTpoVehiculo();
                Vector vTpoVehiculo = new Vector();
                   vTpoVehiculo = dVEHTpoVehiculo.FindByAll();
                if (vTpoVehiculo.size()>0){
                   if (request.getParameter("iCveTpoVehiculo")!=null)
                      out.print(vEti.SelectOneRowSinTD("iCveTpoVehiculo","",vTpoVehiculo,"iCveTpoVehiculo","cDscBreve",request,"0",true));
                   else
                      out.print(vEti.SelectOneRowSinTD("iCveTpoVehiculo","",vTpoVehiculo,"iCveTpoVehiculo","cDscBreve",request,""+iTipo,true));
                }
                else{
                   out.println("<SELECT NAME=\"iCveTpoVehiculo\" SIZE=\"1\">");
                   out.println("<option value=\"0\">Datos no disponibles</option>");
                   out.println("</SELECT>");
                }

                  out.println("<td class=\"EEtiqueta\" class=\"EEtiqueta\">Marca:</td>");
                  out.println("<td class=\"ECampo\" colspan=\"3\">");
                  TDVEHMarca dVEHMarca = new TDVEHMarca();
                  TVVEHMarca vVEHMarca = new TVVEHMarca();
                  Vector vMarca = new Vector();
                     vMarca = dVEHMarca.FindByAll();
                  if (vMarca.size()>0){
                     if (request.getParameter("iCveMarca")!=null)
                        out.print(vEti.SelectOneRowSinTD("iCveMarca","llenaSLT(1,this.value,'','',document.forms[0].iCveModelo);",vMarca,"iCveMarca","cDscBreve",request,"0",true));
                     else
                        out.print(vEti.SelectOneRowSinTD("iCveMarca","llenaSLT(1,this.value,'','',document.forms[0].iCveModelo);",vMarca,"iCveMarca","cDscBreve",request,""+iMarca,true));
                  }

                  else{
                     out.println("<SELECT NAME=\"iCveMarca\" SIZE=\"1\">");
                     out.println("<option value=\"0\">Datos no disponibles</option>");
                     out.println("</SELECT>");
                  }
                  out.println("</td>");
                  out.println("<td class=\"EEtiqueta\" class=\"EEtiqueta\">Modelo:</td>");
                  out.println("<td class=\"ECampo\" colspan=\"2\">");
                  TDVEHModelo dVEHModelo = new TDVEHModelo();
                  TVVEHModelo vVEHModelo = new TVVEHModelo();
                  Vector vModelo = new Vector();
                  if (request.getParameter("iCveMarca")!=null){
                     cFiltro = " WHERE VEHModelo.iCveMarca = " + request.getParameter("iCveMarca");
                     cOrden = " ORDER BY VEHModelo.cDscBreve ";
                     vModelo = dVEHModelo.FindByAll(cFiltro,cOrden);
                  }else{
                     if (iMarca>0){
                        cFiltro = " WHERE VEHModelo.iCveMarca = " + iMarca;
                        cOrden = " ORDER BY VEHModelo.cDscBreve ";
                        vModelo = dVEHModelo.FindByAll(cFiltro,cOrden);
                     }
                  }
                  if (vModelo.size()>0)
                     out.print(vEti.SelectOneRowSinTD("iCveModelo","",vModelo,"iCveModelo","cDscBreve",request,""+iModelo,true));
                  else{
                     out.println("<SELECT NAME=\"iCveModelo\" SIZE=\"1\">");
                     out.println("<option value=\"0\">Datos no disponibles</option>");
                     out.println("</SELECT>");
                  }
                  out.println("</td>");
            %>
            </tr>

            <tr>
              <td class="ETablaT">Núm.</td>
              <td class="ETablaT">Placa</td>
              <td class="ETablaT">Tipo</td>
              <td class="ETablaT">Marca</td>
              <td class="ETablaT">Modelo</td>
              <td class="ETablaT">Año</td>
              <td class="ETablaT">Color</td>
              <td class="ETablaT">Núm. Serie</td>
              <td class="ETablaT">Núm. Motor</td>
              <td class="ETablaT" colspan="2">Inventario</td>
            </tr>
            <%
               if (bs != null){
                  boolean lRadio = false;
                  if (vTemp.size()>1)
                     lRadio = true;

                   bs.start();
                   while(bs.nextRow()){
                        out.println("<tr>");
                        out.println("<input type=\"hidden\" name=\"iCveTpoVehiculo"+bs.getFieldValue("iCveVehiculo", "0")+"\" value=\""+bs.getFieldValue("iCveTpoVehiculo", "0")+"\">");
                        out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveVehiculo", "0")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cPlacas", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscTpoVehiculo", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscMarca", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscModelo", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iAnioVeh", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscColor", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNumSerie", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNumMotor", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cInventario", "&nbsp;")));
                        if (lRadio){
                           if (request.getParameter("RadioVEH")!=null && request.getParameter("RadioVEH").toString().compareTo("")!=0 &&
                               request.getParameter("RadioVEH").toString().compareTo("null")!=0)
                           iVehiculo = new Integer(request.getParameter("RadioVEH").toString()).intValue();

                           if (bs.getFieldValue("iCveVehiculo").toString().compareTo(""+iVehiculo)==0)
                               out.print("<td><input type=\"radio\" name=\"RadioVEH\" value=\""+bs.getFieldValue("iCveVehiculo", "0")+"\" checked></td>");
                           else
                               out.print("<td><input type=\"radio\" name=\"RadioVEH\" value=\""+bs.getFieldValue("iCveVehiculo", "0")+"\"></td>");

                        }else{
                           if (request.getParameter("RadioVEH")!=null && request.getParameter("RadioVEH").toString().compareTo("")!=0 &&
                               request.getParameter("RadioVEH").toString().compareTo("null")!=0)
                           iVehiculo = new Integer(request.getParameter("RadioVEH").toString()).intValue();

                           if (bs.getFieldValue("iCveVehiculo").toString().compareTo(""+iVehiculo)==0)
                               out.print("<td><input type=\"checkbox\" name=\"RadioVEH\" value=\""+bs.getFieldValue("iCveVehiculo", "0")+"\" checked></td>");
                           else
                               out.print("<td><input type=\"checkbox\" name=\"RadioVEH\" value=\""+bs.getFieldValue("iCveVehiculo", "0")+"\"></td>");
                        }
                   }
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
