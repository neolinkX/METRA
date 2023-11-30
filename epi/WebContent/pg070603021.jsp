<%/**
 * Title:       Registro de Solicitud de Mantenimiento
 * Description: Se solicita el Mantenimiento de un Equipo determinado
 * Copyright:   2004
 * Company:     Micros Personales S.A. de C.V.
 * @author      Marco A. Hernández García
 * @version     1.0
 * Clase:       pg070603021CFG
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<html>
<%
  pg070603021CFG  clsConfig = new pg070603021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070603021.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070603021.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Mantenimiento|";    // modificar
  String cCveOrdenar  = "EQMMantenimiento.iCveMantenimiento|";  // modificar
  String cDscFiltrar  = "Mantenimiento|";    // modificar
  String cCveFiltrar  = "EQMMantenimiento.iCveMantenimiento|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "IR";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();

  if(bs!=null){
     if ((bs.getFieldValue("lCancelado")!=null && bs.getFieldValue("lCancelado","0").toString().compareTo("1")==0) ||
         (bs.getFieldValue("lConcluido")!=null && bs.getFieldValue("lConcluido","0").toString().compareTo("1")==0))
        cUpdStatus = "Hidden";
  }

  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();

  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  TFechas tf = new TFechas();
  String fechaFormateada = "";
  TFechas tFecha = new TFechas();
  String cToday  = "";
  cToday = tFecha.getFechaDDMMYYYY(tFecha.TodaySQL(),"/");
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

  int iProceso = 0;
  int EQMEtapaInicio = 0;
  int EQMSolicitaInicio = 0;

  iProceso = new Integer(vParametros.getPropEspecifica("CalEqProceso")).intValue();
  EQMEtapaInicio = new Integer(vParametros.getPropEspecifica("EQMEtapaInicio")).intValue();
  EQMSolicitaInicio = new Integer(vParametros.getPropEspecifica("EQMSolicitaInicio")).intValue();

  DAOGRLMotivo dGRLMotivo = new DAOGRLMotivo();
  Vector vMotivo = new Vector();
  TDEQMTpoMantto dEQMTpoMantto = new TDEQMTpoMantto();
  Vector vTemp = new Vector();


%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070603021.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070603021.js)
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
     cClave2 = request.getParameter("hdCampoClave");
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave2  = ""+bs.getFieldValue("iCveEquipo", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="iProceso" value="<%=cClave2%>">
  <input type="hidden" name="EQMEtapaInicio" value="<%=EQMEtapaInicio%>">
  <input type="hidden" name="EQMSolicitaInicio" value="<%=EQMSolicitaInicio%>">
  <input type="hidden" name="Usuario" value="<%=vUsuario.getICveusuario()%>">
  <input type="hidden" name="Today" value="<%=cToday%>">

  <input type="hidden" name="iCveUniMed" value="<% if (request.getParameter("iCveUniMed") != null) out.print(request.getParameter("iCveUniMed")); else out.print("-1");%>">
  <input type="hidden" name="iCveModulo" value="<% if (request.getParameter("iCveModulo") != null) out.print(request.getParameter("iCveModulo")); else out.print("-1");%>">
  <input type="hidden" name="iCveArea" value="<% if (request.getParameter("iCveArea") != null) out.print(request.getParameter("iCveArea")); else out.print("-1");%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
        if(request.getParameter("hdBoton").toString().compareTo("Generar Reporte")==0)
           out.println(clsConfig.getActiveX());
 %>
  <tr>
     <td valign="top">
        <input type="hidden" name="hdBoton" value="">
        &nbsp;
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
           <tr>
              <td class="ETablaT" colspan="4">Equipo</td>
           </tr>
            <tr><% TEtiCampo vEti = new TEtiCampo();
                 boolean lCaptura = clsConfig.getCaptura();
                 boolean lNuevo = clsConfig.getNuevo();
             %>
          </tr>
          <%
               TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
               TVEQMEquipo vEQMEquipo = new TVEQMEquipo();
               Vector      vEquipo    = new Vector();
               vEquipo = dEQMEquipo.FindByAllDesc(" WHERE EqmEquipo.iCveEquipo = " + request.getParameter("hdCampoClave"));
               if (vEquipo.size()>0){
                 vEQMEquipo = (TVEQMEquipo)vEquipo.get(0);
                 out.println("<tr>");
                 out.print(vEti.EtiCampo("EEtiqueta", "Clasificación:", "ECampo", "text", 25, 50, "", vEQMEquipo.getCDscBreveClasif(), 3, "", "", true, true, false));
                 out.print(vEti.EtiCampo("EEtiqueta", "Tipo:", "ECampo", "text", 25, 50, "", vEQMEquipo.getCDscBreveTpoEquipo(), 3, "", "", true, true, false));
                 out.println("</tr>");

                 out.println("<tr>");
                 out.print(vEti.EtiCampo("EEtiqueta", "Equipo:", "ECampo", "text", 25, 50, "", vEQMEquipo.getCDscEquipo(), 3, "", "", true, true, false));
                 out.print(vEti.EtiCampo("EEtiqueta", "Marca:", "ECampo", "text", 25, 50, "", vEQMEquipo.getCDscBreveMarca(), 3, "", "", true, true, false));
                 out.println("</tr>");


                 out.println("<tr>");
                 out.print(vEti.EtiCampo("EEtiqueta", "Modelo:", "ECampo", "text", 25, 50, "", vEQMEquipo.getCModelo(), 3, "", "", true, true, false));
                 out.print(vEti.EtiCampo("EEtiqueta", "Serie:", "ECampo", "text", 25, 50, "", vEQMEquipo.getCNumSerie(), 3, "", "", true, true, false));
                 out.println("</tr>");

                 out.println("<tr>");
                 out.print(vEti.EtiCampoCS("EEtiqueta", "Inventario:", "ECampo", "text", 25, 50, 3,"", vEQMEquipo.getCInventario(), 3, "", "", true, true, false));
                 out.println("</tr>");
               }
          %>
        </table><% // Fin de Datos %>
        &nbsp;
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
           <tr>
              <td class="ETablaT" colspan="4">Datos del Mantenimiento</td>
           </tr>
             <%
               if (lNuevo){ // Modificar de acuerdo al catálogo específico
                 out.println("<tr>");
                    out.print(vEti.EtiCampo("EEtiqueta","Programado:","ECampo","text",10,10,"dtProgramado","&nbsp;",0,"","fMayus(this);",false,true,true));
                    out.println("<td class=\"EEtiqueta\">Fecha de Solicitud:</td>");
                    out.println("<td class=\"ECampo\"><input type=\"text\" size=\"10\" maxlength=\"10\" name=\"dtSolicitud\" value=\""+cToday+"\" ></td>");
                 out.println("</tr>");

                 out.println("<tr>");
                    out.println("<td class=\"EEtiqueta\">Tipo de <br> Mantenimiento:</td>");
                    out.println("<td>");
                    vTemp = dEQMTpoMantto.FindByAll(" where lActivo = 1"," order by cDscTpoMantto ");
                    out.print(vEti.SelectOneRowSinTD("iCveTpoMantto","",vTemp,"iCveTpoMantto","cDscBreve",request,"0",true));
                    out.println("</td>");
                    out.println("<td class=\"EEtiqueta\">Motivo:</td>");
                    out.println("<td>");
                    vMotivo = dGRLMotivo.FindByProceso(iProceso);
                    out.print(vEti.SelectOneRowSinTD("iCveMotivo","",vMotivo,"iCveMotivo","cDscMotivo",request,"0",true));
                    out.println("</td>");
                 out.println("</tr>");
                 out.println("<tr>");
                    out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observación:","ECampo",80,2,3,"cObservaciones","",0,"","fMayus(this);",false,true,true,request));
                 out.println("</tr>");
               }
               else{
                 if (bs != null){
                     out.println("<input type=\"hidden\" name=\"iCveMantenimiento\" value=\""+bs.getFieldValue("iCveMantenimiento","0").toString()+"\">");
                     out.println("<tr>");
                       out.print(vEti.EtiCampo("EEtiqueta","Mantenimiento:","ECampo","text",10,10,"iCveMantenimiento",bs.getFieldValue("iCveMantenimiento","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                       if (lCaptura){
                          out.println("<td class=\"EEtiqueta\">Fecha de Solicitud:</td>");
                          out.println("<td class=\"ECampo\"><input type=\"text\" size=\"10\" maxlength=\"10\" name=\"dtSolicitud\" value=\""+bs.getFieldValue("dtSolicitud","&nbsp;").toString()+"\" ></td>");
                       }else{
                          out.print(vEti.EtiCampo("EEtiqueta","Fecha de Solicitud:","ECampo","text",10,10,"dtSolicitud",bs.getFieldValue("dtSolicitud","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                       }
                     out.println("</tr>");
                     out.println("<tr>");
                           out.print(vEti.EtiCampoCS("EEtiqueta","Programado:","ECampo","text",10,10,3,"dtProgramado",bs.getFieldValue("dtProgramado","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                     out.println("</tr>");
                     out.println("<tr>");
                        if (lCaptura){
                           out.println("<td class=\"EEtiqueta\">Tipo de <br> Mantenimiento:</td>");
                           out.println("<td>");
                           vTemp = dEQMTpoMantto.FindByAll(" where lActivo = 1"," order by cDscTpoMantto ");
                           out.print(vEti.SelectOneRowSinTD("iCveTpoMantto","",vTemp,"iCveTpoMantto","cDscBreve",request,bs.getFieldValue("iCveTpoMantto","0").toString(),true));
                           out.println("</td>");
                           out.println("<td class=\"EEtiqueta\">Motivo:</td>");
                           out.println("<td>");
                              vMotivo = dGRLMotivo.FindByProceso(iProceso);
                              out.print(vEti.SelectOneRowSinTD("iCveMotivo","",vMotivo,"iCveMotivo","cDscMotivo",request,bs.getFieldValue("iCveMotivo","0").toString(),true));
                           out.println("</td>");
                        }
                        else{
                           out.print(vEti.EtiCampo("EEtiqueta","Tipo de Mantenimiento:","ECampo","text",10,10,"cDscTpoMantto",bs.getFieldValue("cDscTpoMantto","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                           out.print(vEti.EtiCampo("EEtiqueta","Motivo:","ECampo","text",10,10,"cDscMotivo",bs.getFieldValue("cDscMotivo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                        }
                     out.println("<tr>");
                     if (lCaptura)
                        out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observación:","ECampo",80,2,3,"cObservaciones",bs.getFieldValue("cObservaciones","").toString(),0,"","fMayus(this);",false,lCaptura,true,request));
                     else
                        out.print(vEti.EtiCampoCS("EEtiqueta","Observación:","ECampo","text",10,10,3,"cObservaciones",bs.getFieldValue("cObservaciones","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                     out.println("</tr>");
                 }
                 else{
                   out.println("<tr>");
                   out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                   out.println("</tr>");
                 }
               }
             %>
        </table>
        &nbsp;
        <script language="JavaScript">
           form = document.forms[0];
           if (form.dtSolicitud)
              form.dtSolicitud.readOnly = true;
        </script>
     </td>
   </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
