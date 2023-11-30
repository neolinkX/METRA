<%/**
 * Title:       Detalle del Mantenimiento
 * Description: Detalle del Mantenimiento
 * Copyright:   2004
 * Company:     Macros Personales, S.A. de C.V.
 * @author      Marco Antonio Hernández García
 * @version     1.0
 * Clase:       pg070603031
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
<%@ page import="com.micper.seguridad.dao.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<html>
<%
  pg070603031CFG  clsConfig = new pg070603031CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070603031.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070603031.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cUpdStatus  = "SaveCancelOnly";

  boolean lCaptura = clsConfig.getCaptura();
  if(bs!=null){
     if (bs.getFieldValue("lCancelado")!=null && bs.getFieldValue("lCancelado","0").toString().compareTo("0")==0 &&
         bs.getFieldValue("lConcluido")!=null && bs.getFieldValue("lConcluido","0").toString().compareTo("0")==0){
        lCaptura = true;
     }else
        cUpdStatus = "Hidden";
  }

/*
  if (request.getParameter("lCaptura")!=null)
     cUpdStatus  = "Hidden";
*/
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

  int iProceso = 0;
  iProceso = new Integer(vParametros.getPropEspecifica("CalEqProceso")).intValue();
  DAOGRLMotivo dGRLMotivo = new DAOGRLMotivo();
  Vector vMotivo = new Vector();
  TDEQMTpoMantto dEQMTpoMantto = new TDEQMTpoMantto();
  Vector vTemp = new Vector();

  TDEQMEmpMtto dEQMEmpMtto = new TDEQMEmpMtto();
  Vector vEmpMtto = new Vector();
  String cTemp = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070603031.js"%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070603031.js)
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
  <input type="hidden" name="iCveEquipo" value="<% if (request.getParameter("iCveEquipo") != null)  out.print(request.getParameter("iCveEquipo"));%>">
  <input type="hidden" name="iCveMantenimiento" value="<% if (request.getParameter("iCveMantenimiento") != null)  out.print(request.getParameter("iCveMantenimiento"));%>">

  <input type="hidden" name="lSolicitados" value="<% if (request.getParameter("lSolicitados") != null)  out.print(request.getParameter("lSolicitados"));%>">
  <input type="hidden" name="dtDeSol" value="<% if (request.getParameter("dtDeSol") != null)  out.print(request.getParameter("dtDeSol"));%>">
  <input type="hidden" name="dtASol" value="<% if (request.getParameter("dtASol") != null)  out.print(request.getParameter("dtASol"));%>">
  <input type="hidden" name="lProgramados" value="<% if (request.getParameter("lProgramados") != null)  out.print(request.getParameter("lProgramados"));%>">
  <input type="hidden" name="dtDePro" value="<% if (request.getParameter("dtDePro") != null)  out.print(request.getParameter("dtDePro"));%>">
  <input type="hidden" name="dtAPro" value="<% if (request.getParameter("dtAPro") != null)  out.print(request.getParameter("dtAPro"));%>">

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
                 boolean lNuevo = clsConfig.getNuevo();
             %>
          </tr>
          <%
               TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
               TVEQMEquipo vEQMEquipo = new TVEQMEquipo();
               Vector      vEquipo    = new Vector();
               vEquipo = dEQMEquipo.FindByAllDesc(" WHERE EqmEquipo.iCveEquipo = " + request.getParameter("iCveEquipo"));
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
              <td class="ETablaT" colspan="4">Detalle</td>
           </tr>
           <tr>
              <td class="ETablaT" colspan="4">Datos del Mantenimiento</td>
           </tr>
             <%
               if (lNuevo){ // Modificar de acuerdo al catálogo específico
               }
               else{
                 if (bs != null){
                     out.println("<input type=\"hidden\" name=\"Today\" value=\""+cToday+"\">");
                     out.println("<input type=\"hidden\" name=\"iCveMantenimiento\" value=\""+bs.getFieldValue("iCveMantenimiento","0").toString()+"\">");
                     out.println("<input type=\"hidden\" name=\"iCveUsuSolicita\" value=\""+bs.getFieldValue("iCveUsuSolicita","0").toString()+"\">");
                     out.println("<input type=\"hidden\" name=\"dtSolicitud\" value=\""+bs.getFieldValue("dtSolicitud","").toString()+"\">");
                     out.println("<input type=\"hidden\" name=\"dtProgramado\" value=\""+bs.getFieldValue("dtProgramado","").toString()+"\">");
                     out.println("<input type=\"hidden\" name=\"iCveTpoMantto\" value=\""+bs.getFieldValue("iCveTpoMantto","").toString()+"\">");
                     out.println("<input type=\"hidden\" name=\"iCveMotivo\" value=\""+bs.getFieldValue("iCveMotivo","").toString()+"\">");
                     out.println("<tr>");
                     out.print(vEti.EtiCampo("EEtiqueta","Mantenimiento:","ECampo","text",10,10,"iCveMantenimiento",bs.getFieldValue("iCveMantenimiento","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                        out.print(vEti.EtiCampo("EEtiqueta","Fecha de Solicitud:","ECampo","text",10,10,"dtSolicitud",bs.getFieldValue("dtSolicitud","").toString(),0,"","fMayus(this);",false,true,false));
                     out.println("</tr>");

                     out.println("<tr>");
                        out.print(vEti.EtiCampoCS("EEtiqueta","Programado:","ECampo","text",10,10,3,"dtProgramado",bs.getFieldValue("dtProgramado","").toString(),0,"","fMayus(this);",false,true,false));
                     out.println("</tr>");

                     out.println("<tr>");
                       out.print(vEti.EtiCampo("EEtiqueta","Tipo de Mantenimiento:","ECampo","text",10,10,"cDscTpoMantto",bs.getFieldValue("cDscTpoMantto","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                       out.print(vEti.EtiCampo("EEtiqueta","Motivo:","ECampo","text",10,10,"cDscMotivo",bs.getFieldValue("cDscMotivo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                     out.println("</tr>");

                     out.println("<tr>");
                        if (bs.getFieldValue("cDscUsuSolicita")!=null && bs.getFieldValue("cDscUsuSolicita").toString().compareTo("")!=0 &&
                            bs.getFieldValue("cDscUsuSolicita").toString().compareTo("null")!=0)
                           cTemp = bs.getFieldValue("cDscUsuSolicita").toString();
                        else
                           cTemp = "&nbsp;";
                        out.print(vEti.EtiCampoCS("EEtiqueta","Usuario que Solicita:","ECampo","text",10,10,3,"cDscUsuSolicita",cTemp,0,bs.getFieldValue("cDscUsuSolicita","").toString(),"fMayus(this);",false,true,false));
                     out.println("</tr>");

                     out.println("<tr>");
                           out.print(vEti.EtiAreaTexto("EEtiqueta","Accesorios:","ECampo",30,2,"cAccesorios",bs.getFieldValue("cAccesorios","").toString(),0,"","fMayus(this);",false,lCaptura,true,request));
                           out.print(vEti.EtiAreaTexto("EEtiqueta","Análisis <br> Operativo:","ECampo",30,2,"cAnalisisOper",bs.getFieldValue("cAnalisisOper","").toString(),0,"","fMayus(this);",false,lCaptura,true,request));
                     out.println("</tr>");

                     out.println("<tr>");
                        if (lCaptura){
                           vEmpMtto = dEQMEmpMtto.FindByAll(" WHERE EQMEmpMtto.lActivo = 1 ","");
                           out.println("<td class=\"EEtiqueta\">Responsable:</td>");
                           out.println("<td class=\"EEtiquetaL\">");
                           out.print(vEti.SelectOneRowSinTD("iCveEmpMtto","",vEmpMtto,"iCveEmpMtto","cDscEmpMtto",request,bs.getFieldValue("iCveEmpMtto","0").toString(),true));
                           out.println("</td>");
                        }else{
                           if (bs.getFieldValue("cDscEmpMtto")!=null && bs.getFieldValue("cDscEmpMtto").toString().compareTo("")!=0 &&
                               bs.getFieldValue("cDscEmpMtto").toString().compareTo("null")!=0)
                              cTemp = bs.getFieldValue("cDscEmpMtto","&nbsp;").toString();
                           else
                              cTemp = "&nbsp;";
                           out.print(vEti.EtiCampoCS("EEtiqueta","Responsable:","ECampo","text",10,10,3,"cDscEmpMtto",cTemp,0,"","fMayus(this);",false,true,false));
                        }

                      out.println("</tr>");
                      TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();
                      Vector vcPersonal  = new Vector();
                      int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CalEqProceso"));
                      vcPersonal = new TDGRLUMUsuario().FindByAll(" where grlumusuario.icveproceso = " + iCveProceso + " " );

                     out.println("<tr>");
                        if (lCaptura){
                           out.println("<td class=\"EEtiqueta\">Usuario que Recibe:</td>");
                           out.println("<td class=\"EEtiquetaL\">");
                           out.println("<select size=\"1\" name=\"iCveUsuRecibe\">");
                           if (vcPersonal.size() > 0){
                             out.println("<option value=\"0\">Seleccione...</option>");
                             for (int i = 0; i < vcPersonal.size(); i++){
                                vGRLUMUsuario = (TVGRLUMUsuario) vcPersonal.get(i);
                                if (bs.getFieldValue("iCveUsuRecibe","0").toString().compareTo(vGRLUMUsuario.getICveUsuario()+"")==0)
                                   out.println("<option value=\""+vGRLUMUsuario.getICveUsuario()+"\" selected> " + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + " </option>");
                                else
                                   out.println("<option value=\""+vGRLUMUsuario.getICveUsuario()+"\"> " + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + " </option>");
                             }
                           }else{
                                out.println("<option value=\"-1\">Datos no disponibles</option>");
                           }
                           out.println("</select>");
                           out.println("</td>");
                        }else{
                           if (bs.getFieldValue("cDscUsuRecibe")!=null && bs.getFieldValue("cDscUsuRecibe").toString().compareTo("")!=0 &&
                               bs.getFieldValue("cDscUsuRecibe").toString().compareTo("null")!=0)
                              cTemp = bs.getFieldValue("cDscUsuRecibe").toString();
                           else
                              cTemp = "&nbsp;";
                           out.print(vEti.EtiCampo("EEtiqueta","Usuario que Recibe:","ECampo","text",10,10,"cDscUsuRecibe",cTemp,0,"","fMayus(this);",false,true,false));
                        }


                        if (lCaptura){
                           out.println("<td class=\"EEtiqueta\">Autoriza:</td>");
                           out.println("<td class=\"EEtiquetaL\">");
                           out.println("<select size=\"1\" name=\"iCveUsuAutoriza\">");
                           if (vcPersonal.size() > 0){
                             out.println("<option value=\"0\">Seleccione...</option>");
                             for (int i = 0; i < vcPersonal.size(); i++){
                                vGRLUMUsuario = (TVGRLUMUsuario) vcPersonal.get(i);
                                if (bs.getFieldValue("iCveUsuAutoriza","").toString().compareTo(vGRLUMUsuario.getICveUsuario()+"")==0)
                                   out.println("<option value=\""+vGRLUMUsuario.getICveUsuario()+"\" selected> " + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + " </option>");
                                else
                                   out.println("<option value=\""+vGRLUMUsuario.getICveUsuario()+"\"> " + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + " </option>");
                             }
                           }else{
                                out.println("<option value=\"-1\">Datos no disponibles</option>");
                           }
                           out.println("</select>");
                           out.println("</td>");
                        }else{
                           if (bs.getFieldValue("cDscUsuAutoriza")!=null && bs.getFieldValue("cDscUsuAutoriza").toString().compareTo("")!=0 &&
                               bs.getFieldValue("cDscUsuAutoriza").toString().compareTo("null")!=0)
                              cTemp = bs.getFieldValue("cDscUsuAutoriza").toString();
                           else
                              cTemp = "&nbsp;";
                           out.print(vEti.EtiCampo("EEtiqueta","Usuario que Autoriza:","ECampo","text",10,10,"cDscUsuAutoriza",cTemp,0,"","fMayus(this);",false,true,false));
                        }
                     out.println("</tr>");

                     out.println("<tr>");
                        out.print(vEti.EtiCampoCS("EEtiqueta","Nombre:","ECampo","text",100,100,3,"cNombre",bs.getFieldValue("cNombre","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                     out.println("</tr>");

                     out.println("<tr>");
                        out.print(vEti.EtiCampoCS("EEtiqueta","Recepción:","ECampo","text",10,10,3,"dtRecepcion",bs.getFieldValue("dtRecepcion","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                     out.println("</tr>");

                     out.println("<tr>");
                           out.print(vEti.EtiAreaTexto("EEtiqueta","Resultado:","ECampo",30,2,"cResultado",bs.getFieldValue("cResultado","").toString(),0,"","fMayus(this);",false,lCaptura,true,request));
                           out.print(vEti.EtiAreaTexto("EEtiqueta","Observaciones:","ECampo",30,2,"cObservaciones",bs.getFieldValue("cObservaciones","").toString(),0,"","fMayus(this);",false,lCaptura,true,request));
                     out.println("</tr>");

                     out.println("<tr>");
                     if (lCaptura){
                        out.println("<td class=\"EEtiqueta\">Cancelado:</td>");
                        out.println("<td><input type=\"checkbox\" name=\"lCancelado\" value=\"1\" onClick=\"document.forms[0].lConcluido.disabled=this.checked;\"></td>");
                        out.println("<td class=\"EEtiqueta\">Concluido:</td>");
                        out.println("<td><input type=\"checkbox\" name=\"lConcluido\" value=\"1\" onClick=\"document.forms[0].lCancelado.disabled=this.checked;\"></td>");
                     }
                     else{
                        out.println("<td class=\"EEtiqueta\">Cancelado:</td>");
                        if (bs.getFieldValue("lCancelado","0").toString().compareTo("1")==0)
                           out.println("<td><input type=\"checkbox\" name=\"lCancelado\" value=\"1\" disabled checked></td>");
                        else
                           out.println("<td><input type=\"checkbox\" name=\"lCancelado\" value=\"1\" disabled></td>");
                        out.println("<td class=\"EEtiqueta\">Concluido:</td>");
                        if (bs.getFieldValue("lConcluido","0").toString().compareTo("1")==0)
                           out.println("<td><input type=\"checkbox\" name=\"lConcluido\" value=\"1\" disabled checked></td>");
                        else
                           out.println("<td><input type=\"checkbox\" name=\"lConcluido\" value=\"1\" disabled></td>");
                     }
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
