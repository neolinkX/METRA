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
<meta charset="utf-8" />
<title>Ayuda</title>
<%
pg0700013CFG  clsConfig = new pg0700013CFG();
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setNombrePagina("pg0700013.jsp");
  TEtiCampo vEti = new TEtiCampo();
  TSimpleCampo vSCampo = new TSimpleCampo();
  int TamVec=0;
  TVUsuario vUsuario2 = (TVUsuario) request.getSession(true).getAttribute("UsrID");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></SCRIPT>

<script language="JavaScript">
 function fAlerta(){
   alert("Este Expediente se encuentra Inhabilitado para Generar Examenes en Medicina Preventiva!");
  }
</script>
<!-- Elementos de pestañas y Secciones -->
	  <script src="<%=vParametros.getPropEspecifica("RutaFuncs")+"PestaniasJquery/jquery-1.9.1.js"%>"></script>
      <script>
      $(function() {
        $( "#tabs" ).tabs();
      });
      </script>
 <!-- Fin de Elementos de pestañas y Secciones -->


<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">

<!-- Elementos de pestañas y secciones -->
	 <link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + "Pestanias/jquery-ui.css"%>" />
     <!--Codigo de Secciones-->
    <link href="<%= vParametros.getPropEspecifica("RutaCSS") + "Secciones/layout.css?t=1357166275"%>" rel="stylesheet" type="text/css"/>
    <link href="<%= vParametros.getPropEspecifica("RutaCSS") + "Secciones/font.css?t=1357166275"%>" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + "Secciones/jquery-ui.css"%>" type="text/css"/>
	<script src="<%=vParametros.getPropEspecifica("RutaFuncs")+"SeccionesJquery/jquery-1.8.2.js"%>" type="text/javascript">
	</script>
	<script src="<%=vParametros.getPropEspecifica("RutaFuncs")+"SeccionesJquery/jquery-ui.js"%>" type="text/javascript">
	</script>
	<script type="text/javascript">
		//<![CDATA[
		    $(function() {
		    	$( "#accordion" ).accordion();
        		$( "#accordion2" ).accordion();
				$( "#accordion3" ).accordion();
		    });
		//]]>
	</script>
<!-- Fin de Elementos de pestañas y secciones -->

<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">
<form method="POST" action="pg0700013.jsp">&nbsp;
  <input type="hidden" name="hdBuscar" value="0">
  <input type="hidden" name="hdCondicion" value="">
  <input type="hidden" name="hdiCvePersonal">
    <input type="hidden" name="hdTipo" value="<%if((""+request.getParameter("hdTipo")).compareTo("null") != 0) out.print(""+request.getParameter("hdTipo")); %>">
  <input type="hidden" name="hdPreDet" value="">
  <table class="ETablaInfo" background="" border="1" align="center" cellspacing="0" cellpadding="3">
  <input type="hidden" name="hdAdmin" value="<%=clsConfig.getAdmin(vUsuario2.getICveusuario()) %>">
  
  
    <% String cTipo = ""+request.getParameter("hdTipo");
       if(cTipo.compareTo("1") == 0 || cTipo.compareTo("2") == 0){
    %>
    <tr>
      <td class="ETablaT" colspan="2">Seleccione Unidad M&eacute;dica y M&oacute;dulo</td>
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
      <td class="ETablaT" colspan="2">Ayuda</td>
    </tr>
    <%
    out.print("<tr>");
    //out.print(vEti.EtiCampo("EEtiqueta","Nueva Contraseña:","ECampo","password", 12, 10, "cNvaContrasenia", "", 0, "", "", true, true, true));
    out.print(" <td colspan =\"2\" class=\"ECampo\"> <center>");
    out.print(" <input type=\"text\" size=\"100\" maxlength=\"100\" name=\"cTitulo\" ");
	  out.print(" value=\"Favor de Ingresar una palabra clave del título que deseamos consultar\" onfocus=\"this.select();this.value='';\" ");
	  out.print(" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
	  out.print(" onMouseOver=\"if (window.fOverField) window.fOverField(0);\"");
	  out.print(" onBlur=\"fMayus(this);\" onKeyDown=\"cuenta()\" onKeyUp=\"cuenta()\" ></td>");
    out.print("<input type=\"hidden\" size=\"12\" maxlength=\"11\" name=\"caracteres\" value=\"\">");
    out.print("</tr>");
    out.print("<tr>");
    out.print("<td class=\"EEtiquetaL\">");
    out.print(vEti.clsAnclaTexto("EEtiqueta","<center>B&uacute;squeda por Titulo","javascript:fBuscar(1);","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
    out.print("</td><td class=\"EEtiquetaL\">");
    out.print(vEti.clsAnclaTexto("EEtiqueta","<center>B&uacute;squeda por descripci&oacute;n","javascript:fBuscar(2);","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
    //out.print(vEti.Texto("EEtiquetaL", "Busqueda por descripcion"));
    out.print("</td></tr>");
    /*
      out.print("<tr><td colspan=\"2\" align=\"center\">");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar","javascript:fBuscar();","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Cancelar","javascript:window.close();","Cancelar"));
      out.print("</td></tr></table>&nbsp;");*/
      out.print("</table>&nbsp;");
      String cBuscar = ""+request.getParameter("hdBuscar");
      if(cBuscar.compareTo("1") == 0){
       TDPERDatos dPerDatos = new TDPERDatos();
       TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
       TDGRLAyudaDGPMPT dGRLAyudaDGPMPT = new TDGRLAyudaDGPMPT();
       TFechas dtFecha = new TFechas();
       cBuscar = ""+request.getParameter("hdCondicion");

       Vector vcPersonal = new Vector();
       Vector vcGrlAyudaDGPMPT = new Vector ();
       Vector vcInhabilita = new Vector();
     
       try{
         if(request.getParameter("hdTipo").compareTo("") == 0){
        	 vcGrlAyudaDGPMPT = dGRLAyudaDGPMPT.FindByAll(cBuscar,"");
         }
         if(request.getParameter("hdTipo").compareTo("1") == 0){
        	 vcGrlAyudaDGPMPT = dGRLAyudaDGPMPT.FindByAll(cBuscar,"");
         }
         if(request.getParameter("hdTipo").compareTo("2") == 0){
        	 vcGrlAyudaDGPMPT = dGRLAyudaDGPMPT.FindByAll(cBuscar,"");
         }
         if(request.getParameter("hdTipo").compareTo("3") == 0){
        	 vcGrlAyudaDGPMPT = dGRLAyudaDGPMPT.FindByAll(cBuscar,"");
         }
         if(request.getParameter("hdTipo").compareTo("4") == 0){
        	 vcGrlAyudaDGPMPT = dGRLAyudaDGPMPT.FindByAll(cBuscar,"");
         }
       }catch(Exception e){
    	   vcGrlAyudaDGPMPT = new Vector();
         e.printStackTrace();
       }

       TVPERDatos vPerDatos;
       TVMEDInhabilita vMEDInhabilita;
       TVGRLAyudaDGPMPT vGRLAyudaDGPMPT;
       if(vcGrlAyudaDGPMPT.size() > 0){
       %>
        <SCRIPT LANGUAGE="JavaScript">
           window.resizeTo(860,610);
        </SCRIPT>
        <br>
        <table  class="ETablaInfo" border="1" align="center" cellspacing="0" cellpadding="3">
         <tr>
             <td class="ETablaT">Resultados</td>
           </tr>
           <tr>
             <td class="ETablaInfo">
              <br>
						  <div id="tabs">
						                  <ul>
						                    <li><a href="#tabs-1">Guías de usuario</a></li>
						                    <li><a href="#tabs-2">Manuales de instalación</a></li>
						                    <li><a href="#tabs-3">Software y paquetes</a></li>
						                  </ul>
						                  <div id="tabs-1">
						                    <p>Resultados...</p>
						                    	<div id="cc-matrix-1402327277" class="postauthor">
											        <div class="n j-text">
											          <div id="accordion">

											      
         				 <%////// IMPRIMIENDO GUIAS //////
         				int guias = 0;
            				for(int i=0;i<vcGrlAyudaDGPMPT.size();i++){
            						vGRLAyudaDGPMPT = (TVGRLAyudaDGPMPT) vcGrlAyudaDGPMPT.get(i);
            						if(vGRLAyudaDGPMPT.getLGuia()==1){
            							String guia =""; 
            							guia = dGRLAyudaDGPMPT.removeExtension(vGRLAyudaDGPMPT.getCUrl())+"";
            							guia = dGRLAyudaDGPMPT.cUrl(guia)+"";
            							guia = vParametros.getPropEspecifica("RutaImg") + "iconos/"+guia;
            				%>
            													<h3>
						                                        <%= vGRLAyudaDGPMPT.getCDsAyuda() %>
						                                    	</h3>
						                                        <div>
						                                            <p>
						                                            <a class="EEtiqueta" name="Buscar" href="<%=vGRLAyudaDGPMPT.getCUrl()%>" target="_blank" >
						                                                <img src="<%=guia%>" width="90" height="90" align="right"></a>
						                                                <%=vGRLAyudaDGPMPT.getCDscDescripcion()%> </p>
						                                        </div>
            	
            				<%		
            							guias++;
            						}
            				}
            				if(guias ==0){
            					%><h3>No existe ninguna gu&iacute;a bajo el criterio de b&uacute;squeda seleccionado!</h3><%
            				}
          				%>
													
						                                    
						        
														</div>
						                            </div>
						                         </div>
						                  </div>
						                  <div id="tabs-2">
						                   <p>Resultados...</p>
						                    	<div id="cc-matrix-1402327277" class="postauthor">
											        <div class="n j-text">
											          <div id="accordion2">

											      
         				 <%////// IMPRIMIENDO MANUALES //////
         				 int manuales = 0;
            				for(int i=0;i<vcGrlAyudaDGPMPT.size();i++){
            						vGRLAyudaDGPMPT = (TVGRLAyudaDGPMPT) vcGrlAyudaDGPMPT.get(i);
            						if(vGRLAyudaDGPMPT.getLManual()==1){
            							String guia =""; 
            							guia = dGRLAyudaDGPMPT.removeExtension(vGRLAyudaDGPMPT.getCUrl())+"";
            							guia = dGRLAyudaDGPMPT.cUrl(guia)+"";
            							guia = vParametros.getPropEspecifica("RutaImg") + "iconos/"+guia;
            				%>
            													<h3>
						                                        <%= vGRLAyudaDGPMPT.getCDsAyuda() %>
						                                    	</h3>
						                                        <div>
						                                            <p>
						                                                <a class="EEtiqueta" name="Buscar" href="<%=vGRLAyudaDGPMPT.getCUrl()%>" target="_blank" >
						                                                <img src="<%=guia%>" width="90" height="90" align="right"></a>
						                                                <%=vGRLAyudaDGPMPT.getCDscDescripcion()%> </p>
						                                        </div>
            	
            				<%		
            							manuales++;
            						}
            						
            				}
            				if(manuales ==0){
            					%><h3>No existe ning&uacute;n manual bajo el criterio de b&uacute;squeda seleccionado!</h3><%
            				}
            				
          				%>
													
						                                    
						        
														</div>
						                            </div>
						                         </div>
						                  </div>
						                  <div id="tabs-3">
						                    <p>Resultados...</p>
						                    	<div id="cc-matrix-1402327277" class="postauthor">
											        <div class="n j-text">
											          <div id="accordion3">

											      
         				 <%////// IMPRIMIENDO SOFTWARE //////
         				 int software = 0;
            				for(int i=0;i<vcGrlAyudaDGPMPT.size();i++){
            						vGRLAyudaDGPMPT = (TVGRLAyudaDGPMPT) vcGrlAyudaDGPMPT.get(i);
            						if(vGRLAyudaDGPMPT.getLSoftware()==1){
            							String guia =""; 
            							guia = dGRLAyudaDGPMPT.removeExtension(vGRLAyudaDGPMPT.getCUrl())+"";
            							guia = dGRLAyudaDGPMPT.cUrl(guia)+"";
            							guia = vParametros.getPropEspecifica("RutaImg") + "iconos/"+guia;
            				%>
            													<h3>
						                                        <%= vGRLAyudaDGPMPT.getCDsAyuda() %>
						                                    	</h3>
						                                        <div>
						                                            <p>
						                                               <a class="EEtiqueta" name="Buscar" href="<%=vGRLAyudaDGPMPT.getCUrl()%>" target="_blank" >
						                                                <img src="<%=guia%>" width="90" height="90" align="right"></a>
						                                                <%=vGRLAyudaDGPMPT.getCDscDescripcion()%> </p>
						                                        </div>
            	
            				<%		
            							software++;
            						}
            				}
	         				if(software ==0){
	        					%><h3>No existe ning&uacute;n software bajo el criterio de b&uacute;squeda seleccionado!</h3><%
	        				}
          				%>
													
						                                    
						        
														</div>
						                            </div>
						                         </div>
						                  </div>
						                </div>        
             
             </td>
           </tr>          
          
          
          
                         </table>   
       <%
       }else{
       %> 
        <SCRIPT LANGUAGE="JavaScript">
           window.resizeTo(850,190);
        </SCRIPT>        
        <center>
         <font color="Gray">
             <b>No existe ning&uacute;n documento bajo el criterio de b&uacute;squeda seleccionado!</b>
         </font>
         <br>
         <!--
          <a href="SEPer022envia.jsp">Dar de alta</a>
         <font color="Gray">   &oacute;   </font>  
         <a href="javascript:window.close()">Salir</a>
          -->
         </center>
       
      
      
             <%
       }
      }
       %>
</form>

<!--    <a href="seperactualiza.jsp">Actualizar datos</a> -->
</body>
</html>