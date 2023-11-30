<%/**
 * Title: pg070101072.jsp
 * Description: Catalogo de Configuracion del Examen
 * Copyright:
 * Company:
 * @author AG SA
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
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>
<html>
<%
  pg070101072CFG  clsConfig = new pg070101072CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101072.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101072.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Proceso|";    // modificar
  String cCveOrdenar  = "iCveProceso|";  // modificar
  String cDscFiltrar  = "Proceso|";    // modificar
  String cCveFiltrar  = "cDscProceso|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = "";
  if (request.getParameter("hdBoton").toString() != null
      && (request.getParameter("hdBoton").toString().equalsIgnoreCase("modificar")
          || request.getParameter("hdBoton").toString().equalsIgnoreCase("nuevo")) || bs == null){

     cUpdStatus="SaveCancelOnly";

  }else{
     cUpdStatus="UpdateOnly";
  }


  String cNavStatus  = "Disabled";//clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();


  String cCatalogo = "pg070101072.jsp";

 Vector vMedSrv = new Vector();
 TDMEDServicio dMEDServicio = new TDMEDServicio();
 vMedSrv = dMEDServicio.FindByAll(" Where lActivo = 1 ");
 TVMEDServicio vMEDServicio = new TVMEDServicio();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)

  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<script>
function changeCheck(numero){
        //alert('Entro a la accion changeCheck');
      var form = document.forms[0];
       //alert('agregue el form');
<%
      TVMEDServicio vTVS = new TVMEDServicio();
      TDMEDServicio dMedSrv = new TDMEDServicio();
      Vector vSrvs = new Vector();
      vSrvs = dMedSrv.FindByAll(" Where lActivo = 1 ");
      TVGRLMotivo vTVMots = new TVGRLMotivo();
      TVGRLMotivo vTVMotAlt = new TVGRLMotivo();
      TDGRLMotivo dGrlMots = new TDGRLMotivo();
      Vector vMots = new Vector();
      if(request.getParameter("iCveProceso")!=null){
         vMots = dGrlMots.FindByAll(" Where iCveProceso = "+request.getParameter("iCveProceso")+" And lActivo = 1");
      }else{
         vMots = dGrlMots.FindByAll(" Where iCveProceso = 0 And lActivo = 1");
      }
      if (vMots.size() > 0){
         for (int j = 0; j < vSrvs.size(); j++){
             vTVS = (TVMEDServicio)vSrvs.get(j);
             for (int k = 0; k < vMots.size(); k++){
                 vTVMots = (TVGRLMotivo)vMots.get(k);
%>
                 if(form.General<%=j%>.checked){
                   //form.<%=vTVMots.getCDscMotivo().replace(' ','_')%><%=j%>.checked=false;
               <%    for (int l = 0; l < vMots.size(); l++){
                       vTVMotAlt = (TVGRLMotivo)vMots.get(k);  %>
                           form.<%=vTVMotAlt.getCDscMotivo().replace(' ','_')%><%=j%>.checked=true; 
             <%      } %>
                 }
<%
             }
         }
      }
%>
}

function checkChange(){
    //alert('entro a la accion checkChange');
      var form = document.forms[0];
      //alert('cargue la variable form');
<%
      vSrvs = dMedSrv.FindByAll(" Where lActivo = 1 ");
      if(request.getParameter("iCveProceso")!=null){
         vMots = dGrlMots.FindByAll(" Where iCveProceso = "+request.getParameter("iCveProceso")+" And lActivo = 1");
      }else{
         vMots = dGrlMots.FindByAll(" Where iCveProceso = 0 And lActivo = 1");
      }
      if (vMots.size() > 0){
         for (int j = 0; j < vSrvs.size(); j++){
             vTVS = (TVMEDServicio)vSrvs.get(j);
             for (int k = 0; k < vMots.size(); k++){
                 vTVMots = (TVGRLMotivo)vMots.get(k);
%>
                 if(form.<%=vTVMots.getCDscMotivo().replace(' ','_')%><%=j%>.checked==false){
                   form.General<%=j%>.checked=false;
                 }
<%
             }
         }
      }
%>
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
       cClave2  = ""+bs.getFieldValue("iCveSistema", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="Query" value="">
  <input type="hidden" name="SLSProceso" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
  <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
     <tr>
         <td colspan="4" class="ETablaT">
             Configuración del Examen
         </td>
     </tr>
     <tr>
         <td>
         <%
            TEtiCampo vEti = new TEtiCampo();
            boolean lCaptura = clsConfig.getCaptura();
            boolean lNuevo = clsConfig.getNuevo();

            if (lNuevo){

                // Algo nuevo
         %>
               <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                  <tr>
         <%
                    out.println(vEti.Texto("ETablaT","Proceso:"));
                    out.println("<td class=\"ETablaT\">");
                    out.print(vEti.SelectOneRowSinTD("iCveProceso","", (Vector) AppCacheManager.getColl("GRLProceso",""),"iCveProceso","cDscProceso",request,"0"));
                    out.println("</td>");
         %>
                  </tr>
               </table>
               <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                  <tr>
                      <td class="ETablaT" rowspan="2">SERVICIO</td>
         <%
                      TVGRLMotivo vTVMotivo = new TVGRLMotivo();
                      TDGRLMotivo dGrlMotivo = new TDGRLMotivo();
                      Vector vMotivos = new Vector();
                      vMotivos = dGrlMotivo.FindByAll("");
                      if (vMotivos.size() > 0){
         %>
                      <td class="ETablaT" ColSpan="<%=vMotivos.size()+1%>">Motivo</td>
                  </tr>
                      <td class="ETablaT">GENERAL</td>
         <%
                         for (int i = 0; i < vMotivos.size(); i++){
                             vTVMotivo = (TVGRLMotivo)vMotivos.get(i);
                             out.println(vEti.Texto("EEtiquetaC",vTVMotivo.getCDscMotivo()));
                         }
                      }
         %>
                     </td>
                  </tr>
         <%
                      TVMEDServicio vTVServicio = new TVMEDServicio();
                      TVMEDServExamen vTVServExamen = new TVMEDServExamen();
                      TVMEDServExamen vTVServExamen_Alt = new TVMEDServExamen();
                      TDMEDServicio dMedServicio = new TDMEDServicio();
                      Vector vServicios = new Vector();
                      vServicios = dMedServicio.FindByAll(" Where lActivo = 1 ");
                      if (vServicios.size() > 0){
                         for (int z = 0; z < vServicios.size(); z++){
                             boolean flaged = false;
                             boolean fPainted = false;
                             vTVServicio = (TVMEDServicio)vServicios.get(z);
                             out.println("<tr>");
                             out.println(vEti.Texto("EEtiquetaL",vTVServicio.getCDscServicio()));
         %>
                             <input type="hidden" name="iCveServicio<%=z%>" value="<%=vTVServicio.getICveServicio()%>">
         <%
                                   out.println("<td align=\"center\">");
                                   out.println("<input type=\"checkbox\" name=\"General"+z+"\" value=\"0\" onClick=\"JavaScript:changeCheck();\">");
                                   out.println("</td>");
                             for (int y = 0; y < vMotivos.size(); y++){
                                 vTVMotivo = (TVGRLMotivo)vMotivos.get(y);
                                 out.println("<td align=\"center\">");
                                 out.println("<input type=\"checkbox\" name=\""+vTVMotivo.getCDscMotivo().replace(' ','_')+z+"\" value=\""+vTVMotivo.getICveMotivo()+"\" onClick=\"JavaScript:checkChange();\">");
                                 out.println("</td>");
                             }
                             out.println("</tr>");
                         }
                      }
         %>
               </table>
         <%
            }else{
if (bs != null){

         %>
               <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
               <!--input type="hidden" name="iCveProceso" value=""!-->
                  <tr>
         <%
                          String iCveP = bs.getFieldValue("iCveProceso","").toString();
                          //System.out.println("iCveP==> "+iCveP);
          /*                out.print(vEti.EtiCampo("EEtiqueta","Proceso:","ECampo","text",10,10,"cDscProceso",bs.getFieldValue("cDscProceso","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
         */%>
         <%
                    out.println(vEti.Texto("ETablaT","Proceso:"));
                    out.println("<td class=\"ETablaT\">");
                    out.print(vEti.SelectOneRowSinTD("iCveProceso","JavaScript:fillBusca();", (Vector) AppCacheManager.getColl("GRLProceso",""),"iCveProceso","cDscProceso",request,bs.getFieldValue("iCveProceso","").toString()));
                    out.println("</td>");
         %>

                  </tr>
               </table>
               <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                  <tr>
                      <td class="ETablaT" rowspan="2">SERVICIO</td>
         <%

                      TVGRLMotivo vTVMotivo = new TVGRLMotivo();
                      TVGRLMotivo vTVM = new TVGRLMotivo();
                      TDGRLMotivo dGrlMotivo = new TDGRLMotivo();
                      Vector vMotivos = new Vector();
                      if(request.getParameter("iCveProceso") != null){
                         vMotivos = dGrlMotivo.FindByAll("Where iCveProceso = "+request.getParameter("iCveProceso").toString()+" And lActivo = 1");
                      }else{
                         vMotivos = dGrlMotivo.FindByAll(" Where iCveProceso = "+iCveP+" And lActivo = 1");
                      }
         %>
                      <td class="ETablaT" ColSpan="<%=vMotivos.size()+1%>">Motivo</td>
                  </tr>
                      <td class="ETablaT">GENERAL</td>
         <%
                      if (vMotivos.size() > 0){
                         for (int i = 0; i < vMotivos.size(); i++){
                             vTVMotivo = (TVGRLMotivo)vMotivos.get(i);
                             out.println(vEti.Texto("ETablaT",vTVMotivo.getCDscMotivo()));
                         }
                      }
         %>
                     </td>
                  </tr>
         <%
                      TVMEDServicio vTVServicio = new TVMEDServicio();
                      TVMEDServExamen vTVServExamen = new TVMEDServExamen();
                      TVMEDServExamen vTVServExamen_Alt = new TVMEDServExamen();
                      TDMEDServicio dMedServicio = new TDMEDServicio();
                      TDMEDServExamen dMedServExamen = new TDMEDServExamen();
                      Vector vServicios = new Vector();
                      Vector vMedServExamen = new Vector();

                      vServicios = dMedServicio.FindByAll(" Where lActivo = 1 ");
                      //System.out.println(request.getParameter("iCveProceso"));
                      if(request.getParameter("iCveProceso") != null){
                         vMedServExamen = dMedServExamen.FindByAll2(" Where a.iCveProceso = "+request.getParameter("iCveProceso").toString() +" and ");
                      }else{
                         vMedServExamen = dMedServExamen.FindByAll2(" Where a.iCveProceso = "+iCveP + " and ");
                      }
                      if (vServicios.size() > 0){
                         for (int z = 0; z < vServicios.size(); z++){
                             boolean flaged = false;
                             boolean fPainted = false;
                             vTVServicio = (TVMEDServicio)vServicios.get(z);
                             out.println("<tr>");
                             int iCuenta = 0;
                             out.println(vEti.Texto("EEtiquetaL",vTVServicio.getCDscServicio()));
         %>
                             <input type="hidden" name="iCveServicio<%=z%>" value="<%=vTVServicio.getICveServicio()%>">
         <%
                                 /**
                                  * Este bloque de codigo sirve para determinar el agregado de
                                  * el toggle box de general al inicio del renglon
                                  */
                                 for (int xp = 0; xp < vMedServExamen.size(); xp++){
                                     vTVServExamen_Alt = (TVMEDServExamen)vMedServExamen.get(xp);
                                     if ((vTVServExamen_Alt.getICveServicio() == vTVServicio.getICveServicio()) &&
                                         (vTVServExamen_Alt.getICveMotivo()==0)){
                                         flaged = true;
                                         break;
                                     }
                                 }
                                 String cDisable = "";
                                 String cChecked = "";
                                 if (!lCaptura)
                                    cDisable = "disabled";

                                 if (vTVServExamen_Alt.getICveMotivo() == 0 &&
                                     flaged &&
                                     vTVServExamen_Alt.getICveServicio() == vTVServicio.getICveServicio()){
                                   out.println("<td align=\"center\">");
                                   out.println("<input type=\"checkbox\" name=\"General"+z+"\" value=\"0\" " + cDisable + " checked onClick=\"JavaScript:changeCheck('"+ vTVServExamen_Alt.getICveProceso()+"');\">");
                                   out.println("</td>");
                                   flaged = false;
                                 }else{
                                   out.println("<td align=\"center\">");
                                   out.println("<input type=\"checkbox\" name=\"General"+z+"\" value=\"0\" " + cDisable + " onClick=\"JavaScript:changeCheck('"+ vTVServExamen_Alt.getICveProceso()+"');\">");
                                   out.println("</td>");
                                 }
                                 /**
                                  * Fin del codigo para insertar
                                  * el toggle box de general al inicio del renglon
                                  */

                             for (int y = 0; y < vMotivos.size(); y++){
                                 vTVMotivo = (TVGRLMotivo)vMotivos.get(y);
                                 for (int x = 0; x < vMedServExamen.size(); x++){
                                     vTVServExamen = (TVMEDServExamen)vMedServExamen.get(x);
                                     if (vTVServExamen.getICveServicio() == vTVServicio.getICveServicio() &&
                                         vTVServExamen.getICveMotivo() == vTVMotivo.getICveMotivo() &&
                                         vTVServExamen.getICveProceso() == Integer.parseInt(bs.getFieldValue("iCveProceso","").toString())){
                                         fPainted = true;
                                         if (vTVServExamen.getICveMotivo() != 0){
                                            out.println("<td align=\"center\">");
                                            out.println("<input type=\"checkbox\" name=\""+vTVServExamen.getCDscMotivo().replace(' ','_')+z+"\" value=\""+vTVServExamen.getICveMotivo()+"\" " + cDisable + " checked onClick=\"JavaScript:checkChange();\">");
                                            out.println("</td>");
                                            iCuenta++;
                                         }else{
                                            out.println("<td align=\"center\">");
                                            out.println("<input type=\"checkbox\" name=\""+vTVServExamen.getCDscMotivo().replace(' ','_')+z+"\" value=\""+vTVServExamen.getICveMotivo()+"\" " + cDisable + " onClick=\"JavaScript:checkChange();\">");
                                            out.println("</td>");
                                         }
                                     }else{
                                        if(vTVServExamen.getICveMotivo() == vTVMotivo.getICveMotivo() &&
                                            vTVServExamen.getICveServicio() == vTVServicio.getICveServicio() &&
                                            vTVServExamen.getICveProceso() == Integer.parseInt(bs.getFieldValue("iCveProceso","").toString())){
                                            fPainted = false;
                                        }
                                     }
                                 }
                                 if (!fPainted){
                                    out.println("<td align=\"center\">");
                                    out.println("<input type=\"checkbox\" name=\""+vTVMotivo.getCDscMotivo().replace(' ','_')+z+"\" value=\""+vTVMotivo.getICveMotivo()+"\" " + cDisable + " onClick=\"JavaScript:checkChange();\">");
                                    out.println("</td>");
                                    //fPainted = true;
                                 }else{
                                    fPainted = false;
                                 }
                             }
                             if (iCuenta == vMotivos.size()){
                                //System.out.println("ESTE RENGLON FUE GENERAL");
                                for (int w = 0; w < vMotivos.size(); w++){
                                    vTVM = (TVGRLMotivo)vMotivos.get(w);
                                %>
                                    <script>
                                    formx = document.forms[0];
                                    formx.General<%=z%>.checked=true;
                                    formx.<%=vTVM.getCDscMotivo().replace(' ','_')%><%=z%>.checked=true;
                                    </script>
                                <%
                                }
                             }
                             out.println("</tr>");
                         }
                      }

                      if (clsConfig.getLPagina(cCatalogo)){
                             out.println("<tr>");
                             out.print("<td class=\"EEtiquetaC\" colspan=\""+vMotivos.size()+2+"\">");
                             out.print(vEti.clsAnclaTexto("EAncla","Detalle del Examen","JavaScript:fIr('" + bs.getFieldValue("iCveProceso","") + "'," + "'');","Detalle del Examen"));
                             out.print("</td>");
                             out.println("</tr>");
                      }

         %>
               </table>
         <%
}else{
/////////////////////

         %>
               <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                  <tr>
         <%
                    out.println("<td>");
                    out.print(vEti.SelectOneRowSinTD("iCveProceso","JavaScript:fillBusca();", (Vector) AppCacheManager.getColl("GRLProceso",""),"iCveProceso","cDscProceso",request,"0"));
                    out.println("</td>");
         %>
                  </tr>
               </table>
               <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                  <tr>
                      <td class="ETablaT" rowspan="2">Servicio</td>
         <%
                      TVGRLMotivo vTVMotivo = new TVGRLMotivo();
                      TDGRLMotivo dGrlMotivo = new TDGRLMotivo();
                      Vector vMotivos = new Vector();
                      if(request.getParameter("iCveProceso") !=  null)
                         vMotivos = dGrlMotivo.FindByAll(" Where iCveProceso = "+request.getParameter("iCveProceso").toString()+" And lActivo = 1");
                      else
                        vMotivos = dGrlMotivo.FindByAll(" Where iCveProceso = "+ Integer.parseInt(vParametros.getPropEspecifica("EPIProceso")) + " And lActivo = 1");
         %>
                      <td class="ETablaT" ColSpan="<%=vMotivos.size()+1%>">Motivo</td>
                  </tr>

                      <td class="ETablaT">GENERAL</td>
         <%
                      if (vMotivos.size() > 0){
                         for (int i = 0; i < vMotivos.size(); i++){
                             vTVMotivo = (TVGRLMotivo)vMotivos.get(i);
                             out.println(vEti.Texto("ETablaT",vTVMotivo.getCDscMotivo()));
                         }
                      }
         %>
                     </td>
                  </tr>
         <%
                      TVMEDServicio vTVServicio = new TVMEDServicio();
                      TVMEDServExamen vTVServExamen = new TVMEDServExamen();
                      TVMEDServExamen vTVServExamen_Alt = new TVMEDServExamen();
                      TDMEDServicio dMedServicio = new TDMEDServicio();
                      Vector vServicios = new Vector();
                      vServicios = dMedServicio.FindByAll(" Where lActivo = 1 ");
                      if (vServicios.size() > 0){
                         for (int z = 0; z < vServicios.size(); z++){
                             boolean flaged = false;
                             boolean fPainted = false;
                             vTVServicio = (TVMEDServicio)vServicios.get(z);
                             out.println("<tr>");
                             out.println(vEti.Texto("EEtiquetaL",vTVServicio.getCDscServicio()));
         %>
                             <input type="hidden" name="iCveServicio<%=z%>" value="<%=vTVServicio.getICveServicio()%>">
         <%
                                   out.println("<td align=\"center\">");
                                   out.println("<input type=\"checkbox\" name=\"General"+z+"\" value=\"0\" onClick=\"JavaScript:changeCheck();\">");
                                   out.println("</td>");
                             if (vMotivos.size() >0){
                                for (int y = 0; y < vMotivos.size(); y++){
                                    vTVMotivo = (TVGRLMotivo)vMotivos.get(y);
                                    out.println("<td align=\"center\">");
                                    out.println("<input type=\"checkbox\" name=\""+vTVMotivo.getCDscMotivo().replace(' ','_')+z+"\" value=\""+vTVMotivo.getICveMotivo()+"\" onClick=\"JavaScript:checkChange();\">");
                                    out.println("</td>");
                                }
                             }
                             out.println("</tr>");
                         }
                      }
         %>
               </table>
         <%

/////////////////////
}
            }
         %>
         </td>
     </tr>
  </table><% // Fin de Datos %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>

</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
