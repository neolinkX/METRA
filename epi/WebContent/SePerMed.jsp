<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<title>Selector de Medicos</title>
<%
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setNombrePagina("SEPerMed.jsp");
  TEtiCampo vEti = new TEtiCampo();
  TSimpleCampo vSCampo = new TSimpleCampo();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\SePerMed.js"></SCRIPT>-->
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>SePerMed.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></SCRIPT>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">
<form method="POST" action="_self">&nbsp;
  <input type="hidden" name="hdBuscar" value="0">
  <input type="hidden" name="hdCondicion" value="">
  <input type="hidden" name="hdiCvePersonal">
  <input type="hidden" name="hdTipo" value="<%if((""+request.getParameter("hdTipo")).compareTo("null") != 0) out.print(""+request.getParameter("hdTipo")); %>">
  <input type="hidden" name="hdPreDet" value="">
  <table class="ETablaInfo" background="" border="1" align="center" cellspacing="0" cellpadding="3">
    <% String cTipo = ""+request.getParameter("hdTipo");
       if(cTipo.compareTo("1") == 0 || cTipo.compareTo("2") == 0){
    %>
    <tr>
      <td class="ETablaT" colspan="2">Seleccione Unidad Médica y Módulo</td>
    </tr>
    <tr>
      <td class="ETabla" colspan="2">
         <%
           String cPreDet = "";
           if((""+request.getParameter("hdPreDet")).compareTo("1")==0){
             cPreDet = "" + request.getParameter("iCveUniMed") + "|" + request.getParameter("iCveModulo") + "|";
             request.getSession(true).setAttribute("SelPer",cPreDet);
           }

           String cOmision1 = "", cOmision2="";
           if(request.getSession(true).getAttribute("SelPer")!=null){
             StringTokenizer stSelPer = new StringTokenizer(""+request.getSession(true).getAttribute("SelPer"),"|");
             cOmision1=stSelPer.nextToken();
             cOmision2=stSelPer.nextToken();
           }

           String cUniMed = "";
           TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
           int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("EPIProceso"));
           out.print(vEti.SelectOneRowSinTD("iCveUniMed","fChgUniMed();",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,cOmision1)+"&nbsp;");
           if(request.getParameter("iCveUniMed") == null){
             Vector vcUsuario = vUsuario.getVUniFiltro(iCveProceso);
             boolean lVer = false;
             for(int i=0;i<vcUsuario.size();i++){
               cUniMed = ""+((TVGRLUMUsuario)vcUsuario.get(i)).getICveUniMed();
               if(cUniMed.compareTo(cOmision1) == 0){
                  lVer = true;
                  break;
               }
               if(lVer == false){
                  cUniMed = ""+((TVGRLUMUsuario)vcUsuario.get(0)).getICveUniMed();
               }
             }
           }else{
             cUniMed = ""+request.getParameter("iCveUniMed");
           }
           out.print(vEti.SelectOneRowSinTD("iCveModulo","",(Vector) AppCacheManager.getColl("GRLModulo",cUniMed+"|"),"iCveModulo","cDscModulo",request,cOmision2)+"&nbsp;");
           out.print(vEti.clsAnclaTexto("ETabla","Predeterminar","javascript:fPreDet();","Predeterminar"));
         %>
      </td>
    </tr>
    <%
       }
    %>
    <tr>
      <td class="ETablaT" colspan="2">Selector de Medicos</td>
    </tr>
    <%
      out.print("<tr>");
      //out.print(vSCampo.EtiCampo("EEtiqueta", "RFC","ECampo", "text", 13, 13, "cRFC", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      out.print(vSCampo.EtiCampo("EEtiqueta", "USUARIO","ECampo", "text", 13, 13, "cUSUARIO", "", "\" onkeyup=\"fVer(event);", "", true, true, true, request));
      out.print("</tr>");
      //out.print("<tr>");
      //out.print(vSCampo.EtiCampo("EEtiqueta", "CURP","ECampo", "text", 30, 30, "cCURP", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      //out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Paterno","ECampo", "text", 35, 35, "cPaterno", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Materno","ECampo", "text", 35, 35, "cMaterno", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Nombre(s)","ECampo", "text", 35, 35, "cNombre", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "No. Usuario","ECampo", "text", 35, 35, "iCveUsuario", "", "", "", true, true, true, request));
      out.print("</tr>");
      out.print("<tr><td colspan=\"2\" align=\"center\">");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar","javascript:fBuscar();","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Cancelar","javascript:window.close();","Cancelar"));
      out.print("</td></tr></table>&nbsp;");
      String cBuscar = ""+request.getParameter("hdBuscar");
      System.out.println("cBuscar = "+cBuscar);
      if(cBuscar.compareTo("1") == 0){
       //TDPERDatos dPerDatos = new TDPERDatos();
       TDSEGUsuario dSegUsuario = new TDSEGUsuario();
       cBuscar = ""+request.getParameter("hdCondicion");
       
      System.out.println("--"+request.getParameter("hdCondicion"));

       Vector vcPersonal = new Vector();

       try{
         if(request.getParameter("hdTipo").compareTo("") == 0){
           vcPersonal = dSegUsuario.FindByAll(cBuscar);
         }
         if(request.getParameter("hdTipo").compareTo("1") == 0){
           //vcPersonal = dSegUsuario.FindBySelExamen(cBuscar,Integer.parseInt(request.getParameter("iCveUniMed"),10),Integer.parseInt(request.getParameter("iCveModulo"),10),1);
             vcPersonal = dSegUsuario.FindByAll(cBuscar);
         }
         if(request.getParameter("hdTipo").compareTo("2") == 0){
           //vcPersonal = dSegUsuario.FindBySelExamen(cBuscar,Integer.parseInt(request.getParameter("iCveUniMed"),10),Integer.parseInt(request.getParameter("iCveModulo"),10),2);
             vcPersonal = dSegUsuario.FindByAll(cBuscar);
         }
         if(request.getParameter("hdTipo").compareTo("3") == 0){
           //vcPersonal = dSegUsuario.FindBySelExamen(cBuscar,Integer.parseInt(request.getParameter("iCveUniMed"),10),Integer.parseInt(request.getParameter("iCveModulo"),10),3);
             vcPersonal = dSegUsuario.FindByAll(cBuscar);
         }
         if(request.getParameter("hdTipo").compareTo("4") == 0){
           vcPersonal = dSegUsuario.FindByAll(cBuscar);
         }
       }catch(Exception e){
         vcPersonal = new Vector();
         e.printStackTrace();
       }

       //TVPERDatos vPerDatos;
       TVSEGUsuario vSEGUsuario;
       if(vcPersonal.size() > 0){
       %>
        <SCRIPT LANGUAGE="JavaScript">
           window.resizeTo(550,500);
        </SCRIPT>
        <table  class="ETablaInfo" border="1" align="center" cellspacing="0" cellpadding="3">
           <tr>
             <td class="ETablaT">CUSUARIO</td>
             <!--<td class="ETablaT">CURP</td>-->
             <td class="ETablaT">Nombre</td>
             <td class="ETablaT">Apellido Paterno</td>
             <td class="ETablaT">Apellido Materno</td>
             <td class="ETablaT" colspan="2">Clave de Personal</td>
           </tr>
          <%
            for(int i=0;i<vcPersonal.size();i++){
              //vPerDatos = (TVPERDatos) vcPersonal.get(i);
                vSEGUsuario = (TVSEGUsuario) vcPersonal.get(i);
              out.print("<tr>");
              out.print(vEti.Texto("ETabla",vSEGUsuario.getCUsuario()));
              //out.print(vEti.Texto("ETabla",(""+vPerDatos.getCCURP()).compareTo("null") == 0 ? "&nbsp;" : vPerDatos.getCCURP()));
              out.print(vEti.Texto("ETabla",vSEGUsuario.getCNombre()));
              out.print(vEti.Texto("ETabla",""+vSEGUsuario.getCApMaterno()));
              out.print(vEti.Texto("ETabla",""+vSEGUsuario.getCApPaterno()));
              out.print("<td>"+vEti.clsAnclaTexto("ETabla",""+vSEGUsuario.getICveUsuario(),"javascript:fSelected('"+vSEGUsuario.getICveUsuario()+ "');","Selected")+"</td>");
              //out.print("<td>"+vEti.clsAnclaTexto("ETabla","Detalle","javascript:fDetalle('"+vPerDatos.getICvePersonal()+"');","Detalle")+"</td>");
              out.print("</tr>");
            }
          %>
       </table>
       <%
       }else{
       %> 
        <SCRIPT LANGUAGE="JavaScript">
           window.resizeTo(550,390);
        </SCRIPT>        
        <center>
         <font color="Gray">
             <b>No existe Personal bajo el criterio de búsqueda seleccionado!</b>
         </font>
         <a href="javascript:window.close()">Salir</a>
         </center>
       
             <%
       }
      }
       %>
</form>
</body>
</html>