<%/**
 * Title: Seguimiento.
 * Description: Seguimiento.
 * Copyright: Micros Personales, S.A. de C.V.
 * Company: Micros Personales, S.A. de C.V.
 * @author LCI. Oscar Castrejón Adame.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.*" %>


<html>
<%
  pg070603032CFG  clsConfig = new pg070603032CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070603032.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070603032.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802033.jsp";       // modificar
  String cDetalle     = "pg070802033.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|";    // modificar
  String cCveOrdenar  = "iCveMovimiento|";  // modificar
  String cDscFiltrar  = "Clave|";    // modificar
  String cCveFiltrar  = "iCveMovimiento|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar

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
  String cUpdStatus  = clsConfig.getUpdStatus(); //"SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cEstatusIR   = clsConfig.cImprimir;            // modificar
  String cCanWrite   = "yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  TFechas dtFecha = new TFechas();
  Vector vEquipo = new Vector();
  Vector vMdoTrans = new Vector();
  Vector vUnidades = new Vector();
  Vector vPeriodPla = new Vector();
  Vector vEtapa     = new Vector();
  Vector vSolicitante = new Vector();
  String cvEquipos = "";
  String cvEtapa    = "";
  String cvSolicitante = "";
  String cvUsuReg = "";
  TFechas Fecha = new TFechas();
  java.sql.Date dtToday = Fecha.TodaySQL();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  function fListado1(rwId,rwId2){
     forma = document.forms[0];

     forma.hdIni.value    = rwId;
     forma.hdIni2.value   = rwId2;
     forma.hdBoton.value  = 'Actual';
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070502034.jsp';
     forma.submit();
  }

  function llenaSLT(opc,val1,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           window.parent.FRMOtroPanel.location="pg070603032P.jsp?opc=" + opc + "&val1=" + val1 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070603032P.jsp?opc=" + opc + "&val1=" + val1 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fSubmitForm1(theButton){
    var form = document.forms[0];
    form.hdBoton.value = theButton;
    if(form.hdBoton.value == 'Imprimir')
      fImprimir();
    else{
      form.target="_self";
      if (window.fValidaTodo)
        lSubmitir = fValidaTodo();
      else
        lSubmitir = true;
      if (lSubmitir){
        window.parent.parent.FRMCuerpo.FRMFiltro.FRMBusqueda.fSubmiteInt();
      }else
        form.hdBoton.value = "";
    }
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

  <input type="hidden" name="hdCPropiedad" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="10" class="ETablaT">Mantenimiento Seleccionado</td>
           </tr><tr>
                <%
                 if (request.getParameter("hdIni") != null){
                   if (request.getParameter("hdIni") != ""){
                      out.println("<input type=\"hidden\" name=\"hdIni\" value=\"" + request.getParameter("hdIni") + "\">");
                      out.println("<input type=\"hidden\" name=\"iCveEquipo\" value=\"" + request.getParameter("hdIni") + "\">");
                      out.println("<input type=\"hidden\" name=\"hdCampoClave\" value=\"" + request.getParameter("hdIni") + "\">");
                   }
                   else {
                      out.println("<input type=\"hidden\" name=\"hdIni\" value=\"\">");
                      out.println("<input type=\"hidden\" name=\"iCveEquipo\" value=\"\">");
                      out.println("<input type=\"hidden\" name=\"hdCampoClave\" value=\"\">");
                   }
                 }
                 else {
                    if (request.getParameter("iCveEquipo") != null){
                      out.println("<input type=\"hidden\" name=\"hdIni\" value=\"" + request.getParameter("iCveEquipo") + "\">");
                      out.println("<input type=\"hidden\" name=\"iCveEquipo\" value=\"" + request.getParameter("iCveEquipo") + "\">");
                      out.println("<input type=\"hidden\" name=\"hdCampoClave\" value=\"" + request.getParameter("iCveEquipo") + "\">");
                    } else {
                      out.println("<input type=\"hidden\" name=\"hdIni\" value=\"\">");
                      out.println("<input type=\"hidden\" name=\"iCveEquipo\" value=\"\">");
                      out.println("<input type=\"hidden\" name=\"hdCampoClave\" value=\"\">");
                    }
                 }

                 if (request.getParameter("hdIni2") != null)
                   if (request.getParameter("hdIni2") != ""){
                      out.println("<input type=\"hidden\" name=\"hdIni2\" value=\"" + request.getParameter("hdIni2") + "\">");
                      out.println("<input type=\"hidden\" name=\"iCveMantenimiento\" value=\"" + request.getParameter("hdIni2") + "\">");
                   }
                   else {
                      out.println("<input type=\"hidden\" name=\"hdIni2\" value=\"\">");
                      out.println("<input type=\"hidden\" name=\"iCveMantenimiento\" value=\"\">");
                   }
                 else {
                    if (request.getParameter("iCveMantenimiento") != null){
                      out.println("<input type=\"hidden\" name=\"hdIni2\" value=\"" + request.getParameter("iCveMantenimiento") + "\">");
                      out.println("<input type=\"hidden\" name=\"iCveMantenimiento\" value=\"" + request.getParameter("iCveMantenimiento") + "\">");
                    } else {
                      out.println("<input type=\"hidden\" name=\"hdIni2\" value=\"\">");
                      out.println("<input type=\"hidden\" name=\"iCveMantenimiento\" value=\"\">");
                    }
                 }

                 if (request.getParameter("SLSUsrSolicita") != null)
                   if (request.getParameter("SLSUsrSolicita") != "")
                      out.println("<input type=\"hidden\" name=\"SLSUsrSolicita\" value=\"" + request.getParameter("SLSUsrSolicita") + "\">");
                   else
                      out.println("<input type=\"hidden\" name=\"SLSUsrSolicita\" value=\"\">");
                 else
                   out.println("<input type=\"hidden\" name=\"SLSUsrSolicita\" value=\"\">");

                 out.println("<input type=\"hidden\" name=\"dtToday\" value=\""+ Fecha.getFechaDDMMYYYY(dtToday,"/") +  "\">");
                 out.println("<input type=\"hidden\" name=\"hdExterno\" value=\"0\">");


                 vEquipo = clsConfig.vEquipos;
                 if (!vEquipo.isEmpty()){
                   TVEQMEquipo VEQMEquipo = new TVEQMEquipo();
                   VEQMEquipo = (TVEQMEquipo) vEquipo.get(0);
                   out.print("<td class=\"EEtiqueta\">No:</td>");
                   out.print("<td class=\"ETablaR\">" + VEQMEquipo.getICveEquipo() + "</td>");
                   out.print("<td class=\"EEtiqueta\">Clave:</td>");
                   out.print("<td class=\"ETablaR\">" + VEQMEquipo.getCCveEquipo() + "</td>");
                   out.print("<td class=\"EEtiqueta\">Denominación:</td>");
                   out.print("<td class=\"ECampo\">" + VEQMEquipo.getCDscEquipo() + "</td>");
                   out.print("</tr>");
                 } else
                    out.print("<td class=\"ETablaT\">&nbsp</td>");

// Información de la Empresa Seleccionada.
                %>
           </tr>
         </table>
      </td>
      </tr>
      <tr>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                             <% // modificar según listado
                               out.print("<tr>");
                               out.print("<td colspan=\"7\" class=\"ETablaT\">Seguimiento del Mantenimiento</td>");
                               out.print("</tr>");
                               out.print("<tr>");
                               out.print(vEti.Texto("ETablaT", "Clave"));
                               out.print(vEti.Texto("ETablaT", "Fecha"));
                               out.print(vEti.Texto("ETablaT", "Solicita"));
                               out.print(vEti.Texto("ETablaT", "Externo"));
                               out.print(vEti.Texto("ETablaT", "Etapa"));
                               out.print(vEti.Texto("ETablaT", "Observación"));
                               out.print(vEti.Texto("ETablaT", "Usuario"));
                               out.print("</tr>");
                               vEtapa = clsConfig.vEtapa;
                               vSolicitante = clsConfig.vSolicitante;

                               if (bs != null){
                                   bs.start();
                                   vPeriodPla = clsConfig.vPeriodPla;
                                   while(bs.nextRow()){
                                     boolean lPinto = false;
                                     boolean lPinto2 = false;
                                     TVEQMSeguimiento x = (TVEQMSeguimiento) bs.getCurrentBean();
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveMovimiento", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETablaC", dtFecha.getFechaDDMMYYYY(x.getDtSolicitud(),"/")));
                                     if (!vSolicitante.isEmpty()){
                                       for(int i=0;i<vSolicitante.size();i++){
                                         TVGRLSolicitante VGRLSolicitante = new TVGRLSolicitante();
                                         VGRLSolicitante = (TVGRLSolicitante) vSolicitante.get(i);
                                         if ((new Integer(VGRLSolicitante.getiCveSolictante())).toString().compareToIgnoreCase(bs.getFieldValue("iCveSolicitante", "&nbsp;").toString()) == 0){
                                            if (VGRLSolicitante.getlExterno() == 1)
                                               out.print(vEti.Texto("ETabla", VGRLSolicitante.getcDscSolicitante() + "(Ext)"));
                                            else
                                               out.print(vEti.Texto("ETabla", VGRLSolicitante.getcDscSolicitante()));
                                            lPinto2 = true;
                                         }
                                       }
                                     }
                                     if (!lPinto2)
                                        out.print(vEti.Texto("ETablaR", "&nbsp"));
                                     //Si tiene Solicitante Externo lo pone.
                                     if (bs.getFieldValue("cSolicitante", "&nbsp;") != null)
                                       if (bs.getFieldValue("cSolicitante", "&nbsp;").toString().compareToIgnoreCase("") != 0 &&
                                           bs.getFieldValue("cSolicitante", "&nbsp;").toString().compareToIgnoreCase("null") != 0)
                                          out.println(vEti.EtiAreaTextoSINEtiqueta("ECampo",25,2,"TXTSolicitante" + bs.getFieldValue("iCveMovimiento", "&nbsp;").toString(),bs.getFieldValue("cSolicitante", "&nbsp;").toString(),4,"","fMayus(this);",true,true,false));
                                       else
                                          out.print(vEti.Texto("ETablaR", "&nbsp"));
                                     else
                                        out.print(vEti.Texto("ETablaR", "&nbsp"));
                                     if (!vEtapa.isEmpty()){
                                       for(int i=0;i<vEtapa.size();i++){
                                         TVGRLEtapa VGRLEtapa = new TVGRLEtapa();
                                         VGRLEtapa = (TVGRLEtapa) vEtapa.get(i);
                                         if ((new Integer(VGRLEtapa.getiCveEtapa())).toString().compareToIgnoreCase(bs.getFieldValue("iCveEtapa", "&nbsp;").toString()) == 0){
                                            out.print(vEti.Texto("ETabla", VGRLEtapa.getcDscEtapa()));
                                            lPinto = true;
                                         }
                                       }
                                     }
                                     if (!lPinto)
                                       out.print(vEti.Texto("ETablaR", "&nbsp"));
                                     out.println(vEti.EtiAreaTextoSINEtiqueta("ECampo",25,2,"TXTObservacion",bs.getFieldValue("cObservacion", "&nbsp;").toString(),4,"","",true,false,true));
                                     TVUsuario vUsuario = new TVUsuario();
                                     vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                     Vector vcPersonal = new Vector();
                                     int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CalEqProceso"));
                                     int iUniMed = 0;
                                     if(request.getParameter("iCveUniMed") == null){
                                       vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                       if(vcPersonal.size() != 0){
                                          iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                        }
                                     }else{
                                        if (new Integer(request.getParameter("iCveUniMed").toString()).intValue() <= 0){
                                           vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                           if(vcPersonal.size() != 0){
                                             iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                           }
                                        } else
                                           iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
                                     }
                                     vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
                                     boolean lPinto3 = false;
                                     if (!vcPersonal.isEmpty()){
                                       for(int i=0;i<vcPersonal.size();i++){
                                         TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();
                                         vGRLUMUsuario = (TVGRLUMUsuario) vcPersonal.get(i);
                                         if ((new Integer(vGRLUMUsuario.getICveUsuario())).toString().compareToIgnoreCase(bs.getFieldValue("iCveUsuReg", "&nbsp;").toString()) == 0){
                                           out.print(vEti.Texto("ETabla", vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() ));
                                           lPinto3 = true;
                                         }
                                       }

                                     }
                                     if (!lPinto3)
                                      out.print("<td class=\"ECampo\">&nbsp</td>");
                                     out.print("</tr>");
                                   }
                               }

                               if (clsConfig.getlGuardar()){

                               out.print("<tr>");
                               out.print(vEti.Texto("ETablaR", "&nbsp"));
                               out.print("<td class=\"ECampo\">");
                               out.print(vEti.CampoSinCelda("input",10,10,"dtSolicitud", Fecha.getFechaDDMMYYYY(dtToday,"/"),3,"","",true,true));
                               out.print("</td>");
                               out.print("<td class=\"ECampo\">");
                               out.print("<select name=\"SLSSolicitante\" onChange=\"llenaSLT(1,document.forms[0].SLSSolicitante.value,document.forms[0].hdExterno);\">");
                               out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                               if (!vSolicitante.isEmpty()){
                                 for(int i=0;i<vSolicitante.size();i++){
                                   TVGRLSolicitante VGRLSolicitante = new TVGRLSolicitante();
                                   VGRLSolicitante = (TVGRLSolicitante) vSolicitante.get(i);
                                   if (VGRLSolicitante.getlExterno() == 1)
                                      out.print("<option value=\"" + VGRLSolicitante.getiCveSolictante()  + "\">" + VGRLSolicitante.getcDscSolicitante() + "(Ext)" + "</option>");
                                   else
                                      out.print("<option value=\"" + VGRLSolicitante.getiCveSolictante()  + "\">" + VGRLSolicitante.getcDscSolicitante() + "</option>");

                                 }
                               }
                               out.print("</select>");
                               out.print("</td>");
                               out.println(vEti.EtiAreaTextoSINEtiqueta("ECampo",25,2,"TXTSolicitante","",4,"","fMayus(this);",true,true,true));
                               out.print("<td class=\"ECampo\">");
                               out.print("<select name=\"SLSEtapa\">");
                               out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                               if (!vEtapa.isEmpty()){
                                 for(int i=0;i<vEtapa.size();i++){
                                   TVGRLEtapa VGRLEtapa = new TVGRLEtapa();
                                   VGRLEtapa = (TVGRLEtapa) vEtapa.get(i);
                                   out.print("<option value=\"" + VGRLEtapa.getiCveEtapa()  + "\">" + VGRLEtapa.getcDscEtapa() + "</option>");
                                 }
                               }
                               out.print("</select>");
                               out.print("</td>");
                               out.println(vEti.EtiAreaTextoSINEtiqueta("ECampo",25,2,"TXTObservacion","",4,"","fMayus(this);",true,true,true));
                               out.print("<td class=\"ECampo\">");
                               TVUsuario vUsuario = new TVUsuario();
                               vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                               Vector vcPersonal = new Vector();
                               int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CalEqProceso"));
                               int iUniMed = 0;

                               if(request.getParameter("iCveUniMed") == null){
                                  vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                  if(vcPersonal.size() != 0){
                                     iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                  }
                               }else{
                                   if (new Integer(request.getParameter("iCveUniMed").toString()).intValue() <= 0){
                                      vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                      if(vcPersonal.size() != 0){
                                        iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                      }
                                   } else
                                      iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
                               }
                               vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
                               out.print(vEti.SelectOneRowSinTD("SLSUsuReg", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, ""));
                               //out.print(vEti.CampoSinCelda("input",10,10,"FILUsuReg", "",3,"","",true,true));
                               out.print("</td>");
                               out.print("</tr>");

                               }
                            %>
                          </table>
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
