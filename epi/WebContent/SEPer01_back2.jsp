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
<title>Selector de Personal</title>
<%
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setNombrePagina("SEPer01.jsp");
  TEtiCampo vEti = new TEtiCampo();
  TSimpleCampo vSCampo = new TSimpleCampo();
  int TamVec=0;
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
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
      <td class="ETablaT" colspan="2">Selector de Personal</td>
    </tr>
    <%
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "RFC","ECampo", "text", 13, 13, "cRFC", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "CURP","ECampo", "text", 30, 30, "cCURP", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
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
      out.print(vSCampo.EtiCampo("EEtiqueta", "No. Personal","ECampo", "text", 35, 35, "iCvePersonal", "", "", "", true, true, true, request));
      out.print("</tr>");
      out.print("<tr><td colspan=\"2\" align=\"center\">");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar","javascript:fBuscar();","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Cancelar","javascript:window.close();","Cancelar"));
      out.print("</td></tr></table>&nbsp;");
      String cBuscar = ""+request.getParameter("hdBuscar");
      if(cBuscar.compareTo("1") == 0){
       TDPERDatos dPerDatos = new TDPERDatos();
       cBuscar = ""+request.getParameter("hdCondicion");

       Vector vcPersonal = new Vector();
     
       try{
         if(request.getParameter("hdTipo").compareTo("") == 0){
           //vcPersonal = dPerDatos.FindBySelector(cBuscar);
           //vcPersonal = dPerDatos.FindBySelExamGenera(cBuscar);
           //if(vcPersonal.size() == 0){
           vcPersonal = dPerDatos.FindBySelector(cBuscar);
           //}
           //else{
           //TamVec = vcPersonal.size() -1;}
         }
         if(request.getParameter("hdTipo").compareTo("1") == 0){
           //vcPersonal = dPerDatos.FindBySelExamen(cBuscar,Integer.parseInt(request.getParameter("iCveUniMed"),10),Integer.parseInt(request.getParameter("iCveModulo"),10),1);
             vcPersonal = dPerDatos.FindBySelExamGenera(cBuscar);
         }
         if(request.getParameter("hdTipo").compareTo("2") == 0){
           //vcPersonal = dPerDatos.FindBySelExamen(cBuscar,Integer.parseInt(request.getParameter("iCveUniMed"),10),Integer.parseInt(request.getParameter("iCveModulo"),10),2);
             vcPersonal = dPerDatos.FindBySelExamGenera(cBuscar);
         }
         if(request.getParameter("hdTipo").compareTo("3") == 0){
           //vcPersonal = dPerDatos.FindBySelExamen(cBuscar,Integer.parseInt(request.getParameter("iCveUniMed"),10),Integer.parseInt(request.getParameter("iCveModulo"),10),3);
             vcPersonal = dPerDatos.FindBySelExamGenera(cBuscar);
         }
         if(request.getParameter("hdTipo").compareTo("4") == 0){
           vcPersonal = dPerDatos.FindBySelExamGenera(cBuscar);
         }
       }catch(Exception e){
         vcPersonal = new Vector();
         e.printStackTrace();
       }

       TVPERDatos vPerDatos;
       if(vcPersonal.size() > 0){
       %>
        <SCRIPT LANGUAGE="JavaScript">
           window.resizeTo(700,500);
        </SCRIPT>
        <table  class="ETablaInfo" border="1" align="center" cellspacing="0" cellpadding="3">
           <tr>
             <td class="ETablaT">RFC</td>
             <td class="ETablaT">CURP</td>
             <td class="ETablaT">Nombre</td>
             <td class="ETablaT">Expediente</td>
             <td class="ETablaT">Último Examen</td>
             <td class="ETablaT" colspan="3">Clave de Personal</td>
           </tr>
          <%
            for(int i=0;i<vcPersonal.size();i++){
              vPerDatos = (TVPERDatos) vcPersonal.get(i);
              out.print("<tr>");
              out.print(vEti.Texto("ETabla",vPerDatos.getCRFC()+(vPerDatos.getCHomoclave()==null ? "&nbsp;" : vPerDatos.getCHomoclave())));
              out.print(vEti.Texto("ETabla",(""+vPerDatos.getCCURP()).compareTo("null") == 0 ? "&nbsp;" : vPerDatos.getCCURP()));
              out.print(vEti.Texto("ETabla",vPerDatos.getCNombreCompleto()));
              out.print(vEti.Texto("ETabla",""+vPerDatos.getICveExpediente()));
              out.print(vEti.Texto("ETabla",""+vPerDatos.getINumExamen()));
              
               if(vPerDatos.getICvePersonal() > vPerDatos.getICveExpediente()){
                         //System.out.println("La clave personal es mayor a la clave de expediente");
                                try{
                                    String Update = "update PERDATOS set ICVEEXPEDIENTE="+vPerDatos.getICvePersonal()+" WHERE ICVEPERSONAL =  "+vPerDatos.getICvePersonal()+";";
                                       dPerDatos.Sentencia(Update);
                                     }catch(Exception e){
                                     vcPersonal = new Vector();
                                     e.printStackTrace();
                                   }
                         //System.out.println("La clave de expediente ha sido actualizada con respecto a la clave personal");
                     }
              
              
    //          if(vPerDatos.getICvePaisNac() == 0){  
           if(vPerDatos.getICvePaisNac() == 0){
                     out.print("<td><a href=\"SEPerUP.jsp?iCveExpediente="+vPerDatos.getICveExpediente()+"&RFCR="+vPerDatos.getCRFC()+"&Paterno2R="+vPerDatos.getCApPaterno()+"&Materno2R="+vPerDatos.getCApMaterno()+"&Nombre2R="+vPerDatos.getCNombre()+"&SexoR="+vPerDatos.getCSexo()+"\">"+vPerDatos.getICveExpediente()+"</a></td>");
                     out.print("<td><a href=\"SEPerUP.jsp?iCveExpediente="+vPerDatos.getICveExpediente()+"&RFCR="+vPerDatos.getCRFC()+"&Paterno2R="+vPerDatos.getCApPaterno()+"&Materno2R="+vPerDatos.getCApMaterno()+"&Nombre2R="+vPerDatos.getCNombre()+"&SexoR="+vPerDatos.getCSexo()+"\">Actualiza</a></td>");
                     //out.print("<td>"+vEti.clsAnclaTexto("ETabla",""+vPerDatos.getICvePersonal(),"javascript:fSelected('"+vPerDatos.getICvePersonal()+"',"+vPerDatos.getICveExpediente()+","+vPerDatos.getINumExamen()+",'"+ vPerDatos.getCNombreCompleto() + "');","Selected")+"</td>");
                     //out.print("<td>"+vEti.clsAnclaTexto("ETabla","Detalle","javascript:fDetalle('"+vPerDatos.getICvePersonal()+"');","Detalle")+"</td>");
                     out.print("</tr>"); 
                          out.print("<tr>"); 
                          out.print("<SCRIPT LANGUAGE=\"JavaScript\">");
                          out.print("window.resizeTo(550,600);");
                          out.print("</SCRIPT>        ");        
                          out.print("<font color=\"Gray\">");
                          out.print("<br>");              
                          out.print("<b>El expediente No. " + vPerDatos.getICveExpediente() + " no cuenta con información correspondiente a la Dirección y lugar de nacimiento, información requerida si desea realizar un examen Psicofísico.!<br>" );
                          out.print("<center>");      
                          out.print("<br>");  
                          out.print("¿Desea capturar la información faltante?</b>");
                          out.print("</font>");
                          out.print("<br>");
                          out.print("<a href=\"SEPerUP.jsp?iCveExpediente="+vPerDatos.getICveExpediente()+"&RFCR="+vPerDatos.getCRFC()+"&Paterno2R="+vPerDatos.getCApPaterno()+"&Materno2R="+vPerDatos.getCApMaterno()+"&Nombre2R="+vPerDatos.getCNombre()+"&SexoR="+vPerDatos.getCSexo()+"\">Sí</a>");
                          //out.print("<a href=\"SEPerUP.jsp?iCveExpediente="+vPerDatos.getICveExpediente()+"\">Sí</a>");              
                          out.print("<font color=\"Gray\">&nbsp&nbsp   ó    &nbsp&nbsp</font>  ");
                          out.print("<a href=\"javascript:window.close()\">No</a>");
                          out.print("</center>             ");
                          out.print("<br><br>");             
                        out.print("</tr>"); 
                  }
              else{
                      out.print("<td>"+vEti.clsAnclaTexto("ETabla",""+vPerDatos.getICvePersonal(),"javascript:fSelected('"+vPerDatos.getICvePersonal()+"',"+vPerDatos.getICveExpediente()+","+vPerDatos.getINumExamen()+",'"+ vPerDatos.getCNombreCompleto() + "');","Selected")+"</td>");
                      out.print("<td>"+vEti.clsAnclaTexto("ETabla","Detalle","javascript:fDetalle('"+vPerDatos.getICvePersonal()+"');","Detalle")+"</td>");
                      
                      out.print("<td>"+vEti.clsAnclaTexto("ETabla","Modificar","SEPerUP.jsp?iCveExpediente='"+vPerDatos.getICvePersonal()+"'&RFCR='"+vPerDatos.getCRFC()+"'&Paterno2R='"+vPerDatos.getCApPaterno()+"'&Materno2R='"+vPerDatos.getCApMaterno()+"'&Nombre2R='"+vPerDatos.getCNombre()+"'&SexoR="+vPerDatos.getCSexo()+"'","Modificar")+"</td>");
                      
                      out.print("</tr>");
              }
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
         <br>
         <a href="SEPer022envia.jsp">Dar de alta</a>
         <font color="Gray">    ó    </font>  
         <a href="javascript:window.close()">Salir</a>
         </center>
       
      
      
             <%
       }
      }
       %>
</form>

<!--    <a href="seperactualiza.jsp">Actualizar datos</a> -->
</body>
</html>