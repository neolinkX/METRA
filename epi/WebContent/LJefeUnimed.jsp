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
  
  String NumExp = "";
%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"ValidaJUM2.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"ValJUM2.js"%>"></SCRIPT>

<!--
<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\archivos\funciones\ValidaJUM2.js"></SCRIPT>

<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\archivos\funciones\ValJUM2.js"></SCRIPT>
-->

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
      <td class="ETablaT" colspan="2">Validacion Jefe de Unidad</td>
    </tr>
    <%
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Usuario:","ECampo", "text", 35, 35, "cUser", "", "\" onkeyup=\"fVer(event);", "", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Password:","ECampo", "password", 35, 35, "cConstraseña", "", "", "", true, true, true, request));
      out.print("</tr>");
      out.print("<tr><td colspan=\"2\" align=\"center\">");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar","javascript:fBuscar();","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Cancelar","javascript:window.close();","Cancelar"));
      out.print("</td></tr></table>&nbsp;");
      String cBuscar = ""+request.getParameter("hdBuscar");
      if(cBuscar.compareTo("1") == 0){
       TDSEGUsuario dSEGUsuario = new TDSEGUsuario();
       TVSEGUsuario vSEGUsuario;
       //dSEGUsuario.FindByAllJUM();
       cBuscar = ""+request.getParameter("hdCondicion");
       
       //System.out.println("cBuscar = " + cBuscar);
       //System.out.println("hdTipo = "+request.getParameter("hdTipo"));
       
       Vector vcSEGUsuario = new Vector();
           
       try{
           vcSEGUsuario = dSEGUsuario.FindByAllJUM(cBuscar);
       }catch(Exception e){
         //vcSEGUsuario = new Vector();
         e.printStackTrace();
       }

       
          //System.out.println("vcSEGUsuario = " +vcSEGUsuario.size());
       if(vcSEGUsuario.size() > 0){
       %>
        <SCRIPT LANGUAGE="JavaScript">  
           window.resizeTo(700,500);
        </SCRIPT>
          <%
         
            for(int i=0;i<vcSEGUsuario.size();i++){
              vSEGUsuario = (TVSEGUsuario) vcSEGUsuario.get(i);
                //System.out.println(vSEGUsuario.getICveUsuario());
                //System.out.println(vSEGUsuario.getICodigoPostal());
                if(vSEGUsuario.getICodigoPostal()==46){
                    //System.out.println("Usuario es jefe de unidad");
                    String ruta = vParametros.getPropEspecifica("RutaDominio")+"medprev/SEPer022envia.jsp?icvejunimed="+vSEGUsuario.getICveUsuario();
                    response.sendRedirect(ruta);
                %>
                         </table>   
                          <!--            <script type="text/javascript">
                                    var valor = '<%=ruta%>';
                                    
                                    var icvejunimed = '<%=vSEGUsuario.getICveUsuario()%>';
                                    var segundos = 10;
                                    function redireccion() {
                                    location = location.pathname + valor + 'icvejunimed=' + icvejunimed;
                                    //location = location.pathname + pagina;
                                    //document.location.href=pagina;
                                    }
                                    setTimeout("redireccion()",segundos);
                                    </script>

                       
                         <SCRIPT LANGUAGE="JavaScript">
                         fSelected('<%=vSEGUsuario.getICveUsuario()%>',<%=vSEGUsuario.getICveUsuario()%>,<%=vSEGUsuario.getICodigoPostal()%>,'FRANCISCO SANCHEZ AGUIRRE');
                        </SCRIPT>        
                         -->
               <% 
               }else{%>
                       <SCRIPT LANGUAGE="JavaScript">
                           window.resizeTo(550,390);
                        </SCRIPT>        
                        <center>
                         <font color="Gray">
                             <b>El usuario no es Jefe de Unidad.!</b>
                         </font>
                         </center>
               <%
                   }
               }
       }else{
       %> 
        <SCRIPT LANGUAGE="JavaScript">
           window.resizeTo(550,390);
        </SCRIPT>        
        <center>
         <font color="Gray">
             <b>El usuario no existe, favor de verificar sus datos.!</b>
         </font>
         </center>

             <%
       }
      }
       %>
</form>


<!--    <a href="seperactualiza.jsp">Actualizar datos</a> -->
</body>
</html>