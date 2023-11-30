<%/**
 * Title:        Catálogo de Solicitudes de Vehículos
 * Description:  Solicitudes de Vehículos
 * Copyright:    2004
 * Company:      Micros Personales S.A. de C.V.
 * @author       Marco Antonio Hernández GArcía
 * @version      1.0
 * Clase:        pg070703010
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.caching.AppCacheManager"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.text.*"%>
<html>
<%
  pg070703010CFG  clsConfig = new pg070703010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070703010.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070703010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  if (vUsuario != null)
    clsConfig.setICveUsrSesion(vUsuario.getICveusuario());
  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Solicitud|";    // modificar
  String cCveOrdenar  = "VEHSolicitud.iCveSolicitud|";  // modificar
  String cDscFiltrar  = "Solicitud|";    // modificar
  String cCveFiltrar  = "VEHSolicitud.iCveSolicitud|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar
  boolean lFirmas     = true;
  boolean lCancelada  = false;

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();

  if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
    cPosicion = Integer.toString(bs.rowNo());
    cClave2  = ""+bs.getFieldValue("iCveSistema", "");
    cPropiedad = ""+bs.getFieldValue("cPropiedad", "");

    TDVEHEtapaXSolic VEtapa = new TDVEHEtapaXSolic();
    Vector vEtapas = VEtapa.FindByAll(" WHERE iAnio = " + bs.getFieldValue("iAnio") +
                                      " AND iCveUniMed = " + bs.getFieldValue("iCveUniMed") +
                                      " AND iCveSolicitud = " + bs.getFieldValue("iCveSolicitud"), "");
    if (vEtapas != null && vEtapas.size() > 0){
      cUpdStatus = "AddOnly";
      lFirmas = false;
    }
    if (bs.getFieldValue("dtCancelacion","").toString().length() > 0 &&
        !bs.getFieldValue("dtCancelacion","").toString().equalsIgnoreCase("null")){
      cUpdStatus = "AddOnly";
      lFirmas = false;
      lCancelada = true;
    }
  }

  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlVeh")); // 7
  String iMinutos = vParametros.getPropEspecifica("iMinutos"); //00,15,30,45
  Vector vModulo = new Vector();
  Vector vArea = new Vector();
  DAOGRLAreaModulo dAreaModulo = new DAOGRLAreaModulo();
  String cFiltro  = "";
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  String cTemp = "";

  boolean lCaptura = clsConfig.getCaptura();
  boolean lNuevo = clsConfig.getNuevo();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
 <% if(clsConfig.getAccesoValido()){%>
   <tr>
      <td valign="top">
         &nbsp;
         <input type="hidden" name="hdBoton" value="">

         <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
            <% if (!lCaptura && lFirmas){%>
               <td align="center" bgcolor="#FFFFFF">
                  <img src=<%= vParametros.getPropEspecifica("RutaImg")%>logo_SCT.gif
                       alt="SCT">
               </td>
               <td class="ETablaT" colspan="3">Solicitud de Vehículo</td>
            <% }
               else { %>
               <td class="ETablaT" colspan="4">Solicitud de Vehículo</td>
            <% }%>
            </tr>
            <tr>
               <td class="ETablaT" colspan="4">Datos Generales:</td>
            </tr>
              <%
                  TEtiCampo vEti = new TEtiCampo();
                  if (lNuevo){
                      String User = "" + vUsuario.getICveusuario();
                      out.println("<tr>");
                      out.println("<td class=\"EEtiqueta\">Unidad Médica:</td>");
                      out.println("<td>");
                      if (vUsuario!=null)
                         out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,"+User+",this.value,'','',document.forms[0].iCveModulo);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",false,request,"0",true));
                      out.println("</td>");

                      //Módulo
                      TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
                      out.print(vEti.Texto("EEtiqueta", "Módulo:"));
                      if (request.getParameter("iCveModulo") == null){
                         out.println("<td class='ECampo'>");
                         out.println("<SELECT NAME='iCveModulo' SIZE='1' onChange=\"llenaSLT(2,document.forms[0].iCveUniMed.value,this.value," + vUsuario.getICveusuario()  + ",document.forms[0].iCveArea);\">");
                         out.println("</SELECT>");
                       }
                       else{
                         TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
                         Vector vGModulo = new Vector();
                         cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                                   " and GRLUsuMedicos.iCveUniMed =  " + request.getParameter("iCveUniMed") ;
                         vGModulo = dGRLUSUMedicos.FindModulos(cFiltro);
                         out.print(vEti.SelectOneRow("ECampo","iCveModulo","llenaSLT(2,document.forms[0].iCveUniMed.value,this.value," + vUsuario.getICveusuario() + ",document.forms[0].iCveArea);",vGModulo,"iCveModulo","cDscModulo",request,""));
                       }

                      out.println("</td>");
                      out.println("</tr>");
                      out.println("<tr>");

                      //Área
                      DAOGRLAreaModulo dAreaModuloC = new DAOGRLAreaModulo();
                      out.print(vEti.Texto("EEtiqueta", "Área:"));
                      if (request.getParameter("iCveArea") == null){
                         out.println("<td class='ECampo'>");
                         out.println("<SELECT NAME='iCveArea' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCveUniMed.value,document.forms[0].iCveModulo.value,this.value,document.forms[0].iCveUsuSolic);\">");
                         out.println("</SELECT>");
                       }
                       else{
                         TVGRLAreaModulo vAreaModulo = new  TVGRLAreaModulo();
                         Vector vcAreaModulo = new Vector();
                         cFiltro = " WHERE UA.iCveUniMed  = " + request.getParameter("iCveUniMed") +
                                   "   AND UA.iCveModulo  = " + request.getParameter("iCveModulo") +
                                   "   AND UA.iCveUsuario = " + vUsuario.getICveusuario() +
                                   " ORDER BY A.cDscArea ";
                         vcAreaModulo = dAreaModuloC.FindPermUsuario(cFiltro);
                         out.print(vEti.SelectOneRow("ECampo","iCveArea","llenaSLT(3,document.forms[0].iCveUniMed.value,document.forms[0].iCveModulo.value,this.value,document.forms[0].iCveUsuSolic);",vcAreaModulo,"iCveArea","cDscArea",request,""));
                        }

                      out.println("</td>");
                      out.print(vEti.EtiCampo("EEtiqueta","Fecha de Registro:","ECampo","text",10,10,"dtRegistro","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");

                       out.println("<tr>");
                      //UsuSolicita
                      TDGRLUsuArea dGRLUsuArea = new TDGRLUsuArea();
                      out.print(vEti.Texto("EEtiqueta", "Usuario que Solicita:"));
                      if (request.getParameter("iCveUsuSolic") == null){
                         out.println("<td class='ECampo' colspan='4'>");
                         out.println("<SELECT NAME='iCveUsuSolic' SIZE='1'>");
                         out.println("</SELECT>");
                       }
                       else{
                         TVGRLUsuArea vGRLUsuArea = new  TVGRLUsuArea();
                         Vector vUsuarios = new Vector();
                         cFiltro = " where GRLUsuArea.iCveUniMed = " + request.getParameter("iCveUniMed") +
                                   " and GRLUsuArea.iCveModulo = " + request.getParameter("iCVeModulo") +
                                   " and GRLUsuArea.iCveArea = " + request.getParameter("iCveArea") +
                                   " ORDER BY cNombre, cApPaterno, cApMaterno ";
                         vUsuarios = dGRLUsuArea.FindByAll(cFiltro);
                         out.print(vEti.SelectOneRow("ECampo","iCveUsuSolic","",vUsuarios,"iCveUsuario","cNombreCompleto",request,""));
                        }
                    out.println("</tr>");
                    out.println("<tr>");

                    //UsuConductor
                      TDGRLUsuArea dGRLUsuAreaC = new TDGRLUsuArea();
                      out.print(vEti.Texto("EEtiqueta", "Usuario Conductor:"));
                      if (request.getParameter("iCveUsuConductor") == null){
                         out.println("<td class='ECampo'>");
                         out.println("<SELECT NAME='iCveUsuConductor' SIZE='1'>");
                         out.println("</SELECT>");
                       }
                       else{
                         TVGRLUsuArea vGRLUsuArea = new  TVGRLUsuArea();
                         Vector vUsuarios = new Vector();
                         cFiltro = " where GRLUsuArea.iCveUniMed = " + request.getParameter("iCveUniMed") +
                                   " and GRLUsuArea.iCveModulo = " + request.getParameter("iCVeModulo") +
                                   " and GRLUsuArea.iCveArea = " + request.getParameter("iCveArea") +
                                   " ORDER BY cNombre, cApPaterno, cApMaterno ";
                         vUsuarios = dGRLUsuAreaC.FindByAll(cFiltro);
                         out.print(vEti.SelectOneRow("ECampo","iCveUsuConductor","",vUsuarios,"iCveUsuario","cNombreCompleto",request,""));
                        }

                      out.println("</td>");

                      out.print(vEti.EtiCampo("EEtiqueta","Licencia:","ECampo","text",20,20,"cLicencia","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");
                      out.println("<tr>");
                      out.println("<td class=\"EEtiqueta\">Licencia <br> Permanente:</td>");
                      out.println("<td><input type=\"checkbox\" name=\"lLicPermanente\" value=\"1\" ></td>");
                      out.print(vEti.EtiCampo("EEtiqueta","Vencimiento de <br> Licencia:","ECampo","text",10,10,"dtVenceLic","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");

                      out.println("<tr>");
                      out.println("<td class=\"ETablaT\" colspan=\"4\">Datos Específicos:</td>");
                      out.println("</tr>");

                      out.println("<tr>");
                      out.println("<td class=\"EEtiqueta\">Motivo:</td>");
                      out.println("<td>");
                      DAOGRLMotivo dGRLMotivo = new DAOGRLMotivo();
                      TVGRLMotivo  vGRLMotivo = new TVGRLMotivo();
                      Vector          vMotivo = new Vector();
                      vMotivo = dGRLMotivo.FindByProceso(iCveProceso);
                      if (vMotivo.size()>0)
                         out.print(vEti.SelectOneRowSinTD("iCveMotivo","",vMotivo,"iCveMotivo","cDscMotivo",request,"0",true));
                      else{
                         out.println("<SELECT NAME=\"iCveMotivo\" SIZE=\"1\">");
                         out.println("<option value=\"0\">Datos no disponibles</option>");
                         out.println("</SELECT>");
                      }
                      out.println("</td>");
                      out.print(vEti.EtiAreaTexto("EEtiqueta","Destino:","ECampo",30,3,"cDestino","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");

                      out.println("<tr>");
                      out.println("<td class=\"EEtiqueta\">Tipo de Vehículo:</td>");
                      out.println("<td>");
                      TDVEHTpoVehiculo dVEHTpoVehiculo = new TDVEHTpoVehiculo();
                      TVVEHTpoVehiculo vVEHTpoVehiculo = new TVVEHTpoVehiculo();
                      Vector vTpoVehiculo = new Vector();
                      vTpoVehiculo = dVEHTpoVehiculo.FindByAll();
                      out.println("<SELECT NAME=\"iCveTpoVehiculo\" SIZE=\"1\">");
                      if (vTpoVehiculo.size()>0){
                         out.println("<option value=\"0\">Indistinto</option>");
                         for (int i = 0; i <vTpoVehiculo.size();i++){
                            vVEHTpoVehiculo = (TVVEHTpoVehiculo)vTpoVehiculo.get(i);
                            out.println("<option value=\""+vVEHTpoVehiculo.getICveTpoVehiculo()+"\">"+vVEHTpoVehiculo.getCDscBreve()+"</option>");
                         }
                      }
                      else
                         out.println("<option value=\"0\">Indistinto</option>");
                      out.println("</SELECT>");
                      out.println("</td>");
                      out.println("<td class=\"EEtiqueta\">Tiempo Requerido:</td>");
                      out.println("<td class=\"ECampo\">");
                      out.println("<input type=\"text\" size=\"5\" maxlength=\"5\" name=\"idias\" value=\"\"  onChange=\"\">días");
                      out.println("<input type=\"text\" size=\"5\" maxlength=\"5\" name=\"ihoras\" value=\"\"  onChange=\"\">hrs.");
                      out.println("</td>");
//                      out.print(vEti.EtiCampo("EEtiqueta","Tiempo Requerido:","ECampo","text",5,5,"iTmpAsignado","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");

                      out.println("<tr>");
                      out.print(vEti.EtiCampo("EEtiqueta","Fecha de Solicitud:","ECampo","text",10,10,"dtSolicitud","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("<td class=\"EEtiqueta\">Hora de Solicitud:</td>");
                      out.println("<td>");
                      out.println("<SELECT NAME=\"iHoras\" SIZE=\"1\">");
                      TNumeros tNum = new TNumeros();
                      String cValor = "";
                      for (int i=0;i<24;i++){
                         cValor = tNum.getNumeroSinSeparador(new Integer(i),2);
                         out.println("<option value=\""+cValor+"\">"+cValor+"</option>");
                      }
                      out.println("</SELECT>");

                      out.println("<SELECT NAME=\"iMinutos\" SIZE=\"1\">");
                      StringTokenizer st = new StringTokenizer(iMinutos,",");
                      cValor = "";
                      while (st.hasMoreTokens()) {
                         cValor = tNum.getNumeroSinSeparador(new Integer(st.nextToken()),2);
                         out.println("<option value=\""+cValor+"\">"+cValor+"</option>");
                      }
                      out.println("</SELECT>");
                      out.println("</td>");
                      out.println("</tr>");

                      out.println("<tr>");
                      out.println("<td class=\"ETablaT\" colspan=\"4\">Número de Solicitud:</td>");
                      out.println("</tr>");


                      out.println("<tr>");
                      out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",4,4,"iAnio",""+iAnio,0,"","fMayus(this);",false,true,lCaptura));
                      out.print(vEti.EtiCampo("EEtiqueta","Solicitud:","ECampo","text",6,6,"iCveSolicitud","&nbsp;",0,"","fMayus(this);",false,true,false));
                      out.println("</tr>");
                  %>
                   <script language="JavaScript">
                       form = document.forms[0];
                       if (document.forms[0].iAnio)
                           document.forms[0].iAnio.readOnly = true;
                   </script>
                  <%
                  }
                  else {
                      if (bs!=null){
                          out.println("<input type=\"hidden\" name=\"iAnio\" value=\""+bs.getFieldValue("iAnio","&nbsp;").toString()+"\">");
                          out.println("<input type=\"hidden\" name=\"iCveSolicitud\" value=\""+bs.getFieldValue("iCveSolicitud","&nbsp;").toString()+"\">");
                          out.println("<input type=\"hidden\" name=\"iCveUniMed\" value=\""+bs.getFieldValue("iCveUniMed","&nbsp;").toString()+"\">");
                          if (lCaptura){
                             vModulo =  (Vector) AppCacheManager.getColl("GRLModulo",bs.getFieldValue("iCveUniMed","0").toString()+"|");
                             out.println("<tr>");
                             out.print(vEti.EtiCampo("EEtiqueta","Unidad Médica:","ECampo","text",2,2,"iCveUniMed", bs.getFieldValue("cDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                             out.print(vEti.EtiCampo("EEtiqueta","Módulo:","ECampo","text",2,2,"iCveModulo", bs.getFieldValue("cDscModulo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                             out.println("</tr>");

                             out.println("<tr>");

                             cFiltro = " WHERE GRLAreaModulo.iCveUniMed = " + bs.getFieldValue("iCveUniMed","0").toString() +
                                       "   AND GRLAreaModulo.iCveModulo = " + bs.getFieldValue("iCveModulo","0").toString() +
                                       "   AND GRLAreaModulo.iCveArea   = " + bs.getFieldValue("iCveArea","0").toString();
                             vArea = dAreaModulo.FindByAll(cFiltro);
                             out.print(vEti.EtiCampo("EEtiqueta","Area:","ECampo","text",2,2,"iCveArea", ((TVGRLAreaModulo)vArea.get(0)).getCDscArea(),0,"","fMayus(this);",false,true,false));
                             out.print(vEti.EtiCampo("EEtiqueta","Fecha de Registro:","ECampo","text",10,10,"dtRegistro", bs.getFieldValue("dtRegistro","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                             out.println("</tr>");

                         out.println("<tr>");
                         //UsuSolicita
                         TDGRLUsuArea dGRLUsuArea = new TDGRLUsuArea();
                         out.print(vEti.EtiCampo("EEtiqueta","Usuario que Solicita:","ECampo\" colspan=\"3","text",5,5,"iCveUsuSolic", bs.getFieldValue("cDscUsuSolic","0").toString(), 0,"","fMayus(this);",false,true,false));
                         out.println("</tr>");

                             out.println("<tr>");
                             out.println("<td class=\"ETablaT\" colspan=\"4\">Datos Personales:</td>");
                             out.println("</tr>");

                             out.println("<tr>");

                        //UsuConductor
                         dGRLUsuArea = new TDGRLUsuArea();
                         out.print(vEti.Texto("EEtiqueta", "Usuario Conductor:"));
                         TVGRLUsuArea vGRLUsuArea = new  TVGRLUsuArea();
                         Vector vUsuarios = new Vector();
                         cFiltro = " where GRLUsuArea.iCveUniMed = " + bs.getFieldValue("iCveUniMed","0").toString() +
                                   " and GRLUsuArea.iCveModulo = " + bs.getFieldValue("iCveModulo","0").toString() +
                                   " and GRLUsuArea.iCveArea = " + bs.getFieldValue("iCveArea","0").toString() +
                                   " and UM.iCveProceso = " + vParametros.getPropEspecifica("CtrlVeh") +
                                   " ORDER BY cNombre, cApPaterno, cApMaterno ";
                         vUsuarios = dGRLUsuArea.FindByAll(cFiltro);
                         out.println("<td>");
                         out.println("<SELECT NAME=\"iCveUsuConductor\" SIZE=\"1\">");
                         if (vUsuarios.size()>0){
                            out.println("<option value=\"0\">Seleccione...</option>");
                            for(int i =0;i<vUsuarios.size();i++){
                               vGRLUsuArea = (TVGRLUsuArea)vUsuarios.get(i);
                               if (bs.getFieldValue("iCveUsuConductor")!=null && bs.getFieldValue("iCveUsuConductor","0").toString().compareTo(""+vGRLUsuArea.getICveUsuario())==0)
                                  out.println("<option value=\""+vGRLUsuArea.getICveUsuario()+"\" selected>"+vGRLUsuArea.getCNombreCompleto()+"</option>");
                               else
                                  out.println("<option value=\""+vGRLUsuArea.getICveUsuario()+"\">"+vGRLUsuArea.getCNombreCompleto()+"</option>");
                            }
                         }else{
                            out.println("<option value=\"0\">Datos no disponibles</option>");
                         }
                         out.println("</SELECT>");
                         out.println("</td>");



                             out.print(vEti.EtiCampo("EEtiqueta","Licencia:","ECampo","text",20,20,"cLicencia", bs.getFieldValue("cLicencia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.println("</tr>");

                             out.println("<tr>");
                             out.println("<td class=\"EEtiqueta\">Licencia <br> Permanente:</td>");
                             if (bs.getFieldValue("lLicPermanente","0").toString().compareTo("1")==0)
                                out.println("<td><input type=\"checkbox\" name=\"lLicPermanente\" value=\"1\" checked ></td>");
                             else
                                out.println("<td><input type=\"checkbox\" name=\"lLicPermanente\" value=\"1\" ></td>");
                             out.print(vEti.EtiCampo("EEtiqueta","Vencimiento de <br> Licencia:","ECampo","text",10,10,"dtVenceLic", bs.getFieldValue("dtVenceLic","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.println("</tr>");

                             out.println("<tr>");
                             out.println("<td class=\"ETablaT\" colspan=\"4\">Datos Específicos:</td>");
                             out.println("</tr>");

                             out.println("<tr>");
                             out.println("<td class=\"EEtiqueta\">Motivo:</td>");
                             out.println("<td>");
                             DAOGRLMotivo dGRLMotivo = new DAOGRLMotivo();
                             TVGRLMotivo  vGRLMotivo = new TVGRLMotivo();
                             Vector          vMotivo = new Vector();
                             vMotivo = dGRLMotivo.FindByProceso(iCveProceso);
                             if (vMotivo.size()>0)
                                out.print(vEti.SelectOneRowSinTD("iCveMotivo","",vMotivo,"iCveMotivo","cDscMotivo",request,bs.getFieldValue("iCveMotivo","0").toString(),true));
                             else{
                                out.println("<SELECT NAME=\"iCveMotivo\" SIZE=\"1\">");
                                out.println("<option value=\"0\">Datos no disponibles</option>");
                                out.println("</SELECT>");
                             }
                             out.println("</td>");
                             out.print(vEti.EtiAreaTexto("EEtiqueta","Destino:","ECampo",30,3,"cDestino",bs.getFieldValue("cDestino","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.println("</tr>");

                             out.println("<tr>");
                             out.println("<td class=\"EEtiqueta\">Tipo de Vehículo:</td>");

                             out.println("<td>");
                             TDVEHTpoVehiculo dVEHTpoVehiculo = new TDVEHTpoVehiculo();
                             TVVEHTpoVehiculo vVEHTpoVehiculo = new TVVEHTpoVehiculo();
                             Vector vTpoVehiculo = new Vector();
                             vTpoVehiculo = dVEHTpoVehiculo.FindByAll();

                             out.println("<SELECT NAME=\"iCveTpoVehiculo\" SIZE=\"1\">");
                             if (vTpoVehiculo.size()>0){
                                out.println("<option value=\"0\">Indistinto</option>");
                                for (int i = 0; i <vTpoVehiculo.size();i++){
                                   vVEHTpoVehiculo = (TVVEHTpoVehiculo)vTpoVehiculo.get(i);
                                   if (bs.getFieldValue("iCveTpoVehiculo","0").toString().compareTo(vVEHTpoVehiculo.getICveTpoVehiculo()+"")==0)
                                      out.println("<option value=\""+vVEHTpoVehiculo.getICveTpoVehiculo()+"\" selected>"+vVEHTpoVehiculo.getCDscBreve()+"</option>");
                                   else
                                      out.println("<option value=\""+vVEHTpoVehiculo.getICveTpoVehiculo()+"\">"+vVEHTpoVehiculo.getCDscBreve()+"</option>");
                                }
                             }
                             else
                                out.println("<option value=\"0\">Indistinto</option>");
                             out.println("</SELECT>");
                             out.println("</td>");

                             int iTiempoAsignado = 0;
                             int iHoras = 0;
                             int iDias = 0;
                             if (bs.getFieldValue("iTmpAsignado")!=null && bs.getFieldValue("iTmpAsignado").toString().compareTo("")!=0 &&
                                 bs.getFieldValue("iTmpAsignado").toString().compareTo("null")!=0)
                             iTiempoAsignado = new Integer(bs.getFieldValue("iTmpAsignado").toString()).intValue();
                             iDias =  iTiempoAsignado/24;
                             iHoras = (iTiempoAsignado-(iDias*24));
                             out.println("<td class=\"EEtiqueta\">Tiempo Requerido:</td>");
                             out.println("<td class=\"ETabla\">");
                             out.println("<input type=\"text\" size=\"5\" maxlength=\"5\" name=\"idias\" value=\"" + iDias + "\"  onChange=\"\">días");
                             out.println("<input type=\"text\" size=\"5\" maxlength=\"5\" name=\"ihoras\" value=\"" + iHoras + "\"  onChange=\"\">hrs.");
                             out.println("</td>");
                             out.println("</tr>");

                             out.println("<tr>");
                             out.print(vEti.EtiCampo("EEtiqueta","Fecha de Solicitud:","ECampo","text",10,10,"dtSolicitud", bs.getFieldValue("dtSolicitud","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
//                             out.print(vEti.EtiCampo("EEtiqueta","Hora de Solicitud:","ECampo","text",10,10,"tsSolicitud", bs.getFieldValue("tsSolicitud","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                      String cHoraSol = bs.getFieldValue("tsSolicitud","").toString();
                      String cMinuSol = "";
                      StringTokenizer stHora = new StringTokenizer(cHoraSol,",");
                      if (stHora.countTokens() == 2){
                        cHoraSol = stHora.nextToken(",");
                        cHoraSol = stHora.nextToken(",");
                        stHora = new StringTokenizer(cHoraSol,":");
                        cHoraSol = stHora.nextToken(":");
                        cMinuSol = stHora.nextToken(":");
                      }
                      int iHoraSol = Integer.parseInt(cHoraSol.trim(),10);
                      int iMinuSol = Integer.parseInt(cMinuSol.trim(),10);
                      out.println("<td class=\"EEtiqueta\">Hora de Solicitud:</td>");
                      out.println("<td>");
                      out.println("<SELECT NAME=\"iHoras\" SIZE=\"1\">");
                      TNumeros tNum = new TNumeros();
                      String cValor = "";
                      for (int i=0;i<24;i++){
                         cValor = tNum.getNumeroSinSeparador(new Integer(i),2);
                         if (i == iHoraSol)
                           out.println("<option selected value=\""+cValor+"\">"+cValor+"</option>");
                         else
                           out.println("<option value=\""+cValor+"\">"+cValor+"</option>");
                      }
                      out.println("</SELECT>");

                      out.println("<SELECT NAME=\"iMinutos\" SIZE=\"1\">");
                      StringTokenizer st = new StringTokenizer(iMinutos,",");
                      cValor = "";
                      while (st.hasMoreTokens()) {
                         cValor = tNum.getNumeroSinSeparador(new Integer(st.nextToken()),2);
                         if (Integer.parseInt(cValor,10) == iMinuSol)
                           out.println("<option selected value=\""+cValor+"\">"+cValor+"</option>");
                         else
                           out.println("<option value=\""+cValor+"\">"+cValor+"</option>");
                      }
                      out.println("</SELECT>");
                      out.println("</td>");
                             out.println("</tr>");

                             out.println("<tr>");
                             out.println("<td class=\"ETablaT\" colspan=\"4\">Número de Solicitud:</td>");
                             out.println("</tr>");

                             out.println("<tr>");
                             out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",4,4,"iAnio", bs.getFieldValue("iAnio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                             out.print(vEti.EtiCampo("EEtiqueta","Solicitud:","ECampo","text",6,6,"iCveSolicitud", bs.getFieldValue("iCveSolicitud","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                             out.println("</tr>");
                          }else{
                             out.println("<tr>");
                             out.print(vEti.EtiCampo("EEtiqueta","Unidad Médica:","ECampo","text",2,2,"iCveUniMed", bs.getFieldValue("cDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.print(vEti.EtiCampo("EEtiqueta","Módulo:","ECampo","text",5,5,"iCveModulo", bs.getFieldValue("cDscModulo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.println("</tr>");

                             out.println("<tr>");
                             out.print(vEti.EtiCampo("EEtiqueta","Área:","ECampo","text",5,5,"iCveArea", bs.getFieldValue("cDscArea","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.print(vEti.EtiCampo("EEtiqueta","Fecha de Registro:","ECampo","text",10,10,"dtRegistro", bs.getFieldValue("dtRegistro","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.println("</tr>");

                             out.println("<tr>");
                             out.print(vEti.EtiCampoCS("EEtiqueta","Usuario que Solicita:","ECampo","text",5,5,3,"iCveUsuSolic", bs.getFieldValue("cDscUsuSolic","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.println("</tr>");

                             out.println("<tr>");
                             out.println("<td class=\"ETablaT\" colspan=\"4\">Datos Personales:</td>");
                             out.println("</tr>");

                             out.println("<tr>");
                             out.print(vEti.EtiCampo("EEtiqueta","Usuario Coductor:","ECampo","text",5,5,"cDscUsuConductor", bs.getFieldValue("cDscUsuConductor","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.print(vEti.EtiCampo("EEtiqueta","Licencia:","ECampo","text",20,20,"cLicencia", bs.getFieldValue("cLicencia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.println("</tr>");

                             out.println("<tr>");
                             cTemp = "NO";
                             if (bs.getFieldValue("lLicPermanente").toString().compareTo("1")==0)
                                cTemp = "SI";
                              out.print(vEti.EtiCampo("EEtiqueta","Licencia <br> Permanente:","ECampo","text",1,1,"lLicPermanente", cTemp,0,"","fMayus(this);",false,true,lCaptura));
                             out.print(vEti.EtiCampo("EEtiqueta","Vencimiento de <br> Licencia:","ECampo","text",10,10,"dtVenceLic", bs.getFieldValue("dtVenceLic","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.println("</tr>");

                             out.println("<tr>");
                             out.println("<td class=\"ETablaT\" colspan=\"4\">Datos Específicos:</td>");
                             out.println("</tr>");

                             out.println("<tr>");
                             out.print(vEti.EtiCampo("EEtiqueta","Motivo:","ECampo","text",5,5,"iCveMotivo", bs.getFieldValue("cDscMotivo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.print(vEti.EtiCampo("EEtiqueta","Destino:","ECampo","text",100,100,"cDestino", bs.getFieldValue("cDestino","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.println("</tr>");

                             out.println("<tr>");
                             String cTemporal = bs.getFieldValue("cDscBreveTpoVeh","Indistinto").toString();
                             cTemporal = cTemporal.equals("null")?"Indistinto":cTemporal;
                             out.print(vEti.EtiCampo("EEtiqueta","Tipo de Vehículo:","ECampo","text",5,5,"cDscTpoVehiculo", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                             int iTmpAsignado = 0;
                             int ihoras = 0;
                             int idias = 0;
                             if (bs.getFieldValue("iTmpAsignado")!=null && bs.getFieldValue("iTmpAsignado").toString().compareTo("")!=0 &&
                                 bs.getFieldValue("iTmpAsignado").toString().compareTo("null")!=0)
                             iTmpAsignado = new Integer(bs.getFieldValue("iTmpAsignado").toString()).intValue();
                             idias =  iTmpAsignado/24;
                             ihoras = (iTmpAsignado-(idias*24));
//                             out.print(vEti.EtiCampo("EEtiqueta","Tiempo Requerido:","ECampo","text",5,5,"iTmpAsignado", bs.getFieldValue("iTmpAsignado","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             out.println("<td class=\"EEtiqueta\">Tiempo Requerido:</td>");
                             out.println("<td class=\"ETabla\">");
                             out.println(idias + " días, " + ihoras + " hrs.");
                             out.println("</td>");

                             out.println("</tr>");

                             out.println("<tr>");
                             out.print(vEti.EtiCampo("EEtiqueta","Fecha de Solicitud:","ECampo","text",10,10,"dtSolicitud", bs.getFieldValue("dtSolicitud","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                             cTemp = "&nbsp;";
                             if (bs.getFieldValue("tsSolicitud","").toString().compareTo("")!=0 && bs.getFieldValue("tsSolicitud","").toString().compareTo("null")!=0)
                                cTemp =  bs.getFieldValue("tsSolicitud","").toString();
                             out.print(vEti.EtiCampo("EEtiqueta","Hora de Solicitud:","ECampo","text",5,5,"tsSolicitud", cTemp,0,"","fMayus(this);",false,true,lCaptura));
                             out.println("</tr>");

                             out.println("<tr>");
                             out.println("<td class=\"ETablaT\" colspan=\"4\">Número de Solicitud:</td>");
                             out.println("</tr>");

                             out.println("<tr>");
                             out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",4,4,"iAnio", bs.getFieldValue("iAnio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                             out.print(vEti.EtiCampo("EEtiqueta","Solicitud:","ECampo","text",6,6,"iCveSolicitud", bs.getFieldValue("iCveSolicitud","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                             out.println("</tr>");
                          }
                      }
                  }
              %>
         </table><% // Fin de Datos %>
         <%
        if (lCancelada)
          out.println("<br><table class=\"ETablaInfo\" align=\"center\" cellspacin=0 cellpading = 3><tr><td class=\"ETablaT\" colspan=\"4\">Solicitud Cancelada por el Usuario</td></tr></table>");
           if (!lCaptura && lFirmas){%>
         &nbsp;
         <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3" ><% // Inicio de Datos %>
           <tr>
             <td class="ETablaT">Solicitante</td>
             <td class="ETablaT">Conductor</td>
           </tr>
           <tr>
             <td class="ETabla" width="50%">
               <table border="0">
                 <tr>
                   <td class="ETabla"><strong>Nombre:&nbsp;&nbsp;</strong><%= bs!=null?bs.getFieldValue("cDscUsuSolic","&nbsp;").toString():"&nbsp;" %></td>
                 </tr>
                 <tr>
                   <td class="ETabla"><br><strong>Cargo:&nbsp;&nbsp;</strong>___________________________________</td>
                 </tr>
                 <tr>
                   <td class="ETablaC"><br><br><br><br><br>______________________________<br><strong>Firma</strong></td>
                 </tr>
               </table>
             </td>
             <td class="ETabla" width="50%">
               <table border="0">
                 <tr>
                   <td class="ETabla"><strong>Nombre:&nbsp;&nbsp;</strong><%= bs!=null?bs.getFieldValue("cDscUsuConductor","&nbsp;").toString():"&nbsp;" %></td>
                 </tr>
                 <tr>
                   <td class="ETabla"><br><strong>Cargo:&nbsp;&nbsp;</strong>___________________________________</td>
                 </tr>
                 <tr>
                   <td class="ETablaC"><br><br><br><br><br>______________________________<br><strong>Firma</strong></td>
                 </tr>
               </table>
             </td>
           </tr>
         </table>
         <%}%>
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
