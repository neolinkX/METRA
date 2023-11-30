<%/**
 *
 * Title: pg070101063.jsp
 * Description: Catalogo de configuración de la rama
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
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>
<html>
<%
  pg070101104CFG  clsConfig = new pg070101104CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101104.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101104.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave Sintoma|Pregunta|Genero(M/F/A)|";    // modificar
  String cCveOrdenar  = "iCveSintoma|cPregunta|cGenero|";  // modificar
  String cDscFiltrar  = "Pregunta|Genero(M/F/A)|";    // modificar
  String cCveFiltrar  = "cPregunta|cGenero|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  int iCveLabC = new Integer(vParametros.getPropEspecifica("CveLabClin")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();

  String cNavStatus  = "";
//  if (bs != null){
//     cNavStatus = "FirstRecord";
//  }else{
     cNavStatus = clsConfig.getNavStatus();
//  }

  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
 Vector vMedSrv = new Vector();
 TDMEDServicio dMEDServicio = new TDMEDServicio();
 vMedSrv = dMEDServicio.FindByAll("Where lActivo=1");
 TVMEDServicio vMEDServicio = new TVMEDServicio();
 TDMEDSintoma dMEDSintoma = new TDMEDSintoma(); 

 
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



<SCRIPT language="JavaScript">
		function listbox_move(listID, direction) {
 
			//var listbox = document.getElementById(listID);
                        var listbox = document.forms[0].getElementById(listID);
			var selIndex = listbox.selectedIndex;
 
			if(-1 == selIndex) {
				alert("Please select an option to move.");
				return;
			}
 
			var increment = -1;
			if(direction == 'up')
				increment = -1;
			else
				increment = 1;
 
			if((selIndex + increment) < 0 ||
				(selIndex + increment) > (listbox.options.length-1)) {
				return;
			}
 
			var selValue = listbox.options[selIndex].value;
			var selText = listbox.options[selIndex].text;
			listbox.options[selIndex].value = listbox.options[selIndex + increment].value
			listbox.options[selIndex].text = listbox.options[selIndex + increment].text
 
			listbox.options[selIndex + increment].value = selValue;
			listbox.options[selIndex + increment].text = selText;
 
			listbox.selectedIndex = selIndex + increment;
		}
 
		function listbox_moveacross(sourceID, destID) {
			var src = document.getElementById(sourceID);
                        //var src = document.forms[0].getElementById(sourceID);
			var dest = document.getElementById(destID);
                        //var dest = document.forms[0].getElementById(destID);
 
			for(var count=0; count < src.options.length; count++) {
 
				if(src.options[count].selected == true) {
						var option = src.options[count];
 
						var newOption = document.createElement("option");
                                                //var newOption = document.forms[0].createElement("option");
						newOption.value = option.value;
						newOption.text = option.text;
						newOption.selected = true;
						try {
								 dest.add(newOption, null); //Standard
								 src.remove(count, null);
						 }catch(error) {
								 dest.add(newOption); // IE only
								 src.remove(count);
						 }
						count--;
 
				}
 
			}
 
		}
		function listbox_selectall(listID, isSelect) {
 
			//var listbox = document.getElementById(listID);
                        //var listbox = document.forms[0].getElementById(listID);
			for(var count=0; count < listbox.options.length; count++) {
 				listbox.options[count].selected = isSelect; 
			}
		}
</SCRIPT> 





<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <input type="hidden" name="hdChosen" value="<% if (request.getParameter("hdChosen") != null) out.print(request.getParameter("hdChosen"));%>">
  <input type="hidden" name="IMdoTrans" value="<% if (request.getParameter("iCveMdoTrans2") != null) out.print(request.getParameter("iCveMdoTrans2"));%>">
  <input type="hidden" name="ICategoria" value="<% if (request.getParameter("iCveCategoria2") != null) out.print(request.getParameter("iCveCategoria2"));%>">
  <input type="hidden" name="hdRedir" value="">
  <%
   
  
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave  = ""+bs.getFieldValue("iCveSintoma", "");
       cClave2  = ""+bs.getFieldValue("iCveServicio", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     }

 %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdSintoma" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdSintoma")); else out.print(cClave);%>">
  <input type="hidden" name="hdServicio" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("iCveServicio")); else out.print(cClave);%>">
  <input type="hidden" name="hdServicio2" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdRama" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("iCveRama")); else out.print(cClave);%>">
  
  <input type="hidden" name="hdTpoResp" value="<%=request.getParameter("hdTpoResp")%>">

  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="lLogico" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("lLogica")); else out.print(cClave);%>">
  
  <input type="hidden" name="iCveTpoResp" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("iCveTpoResp")); else out.print(cClave);%>">
  <input type="hidden" name="Categorias" value="<%request.getParameter("Categorias");%>">
  <input type="hidden" name="cServicios" value="<%request.getParameter("cServicios");%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr>
                              <td colspan="4" class="ETablaT">Configuración de la Alerta
                              </td>
                            </tr>
                            <%

                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                
                              if (lNuevo){

                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta","Servicio:"));
                                   TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                   TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                   out.println("<td class='ECampo' >");
                                   out.println(vEti.SelectOneRowSinTD("iCveServicio","llenaSLT(1,this.value,'','',document.forms[0].iCveRama);",vMedSrv,"iCveServicio", "cDscServicio", request, "0", true));
                                   out.println("</td>");
                                   out.println(vEti.Texto("EEtiqueta","Rama:"));

                                   if (request.getParameter("iCveRama") == null){
                                      out.println("<td class='ECampo'>");
                                      out.println("<SELECT NAME='iCveRama' SIZE='1' OnChange='fillBusca();'");
                                      out.println("</SELECT>");
                                      out.println("</td>");
                                      out.println("</tr>");
                                   }
                                   else{
                                      TDMEDRama dRama = new TDMEDRama();
                                      out.println("<td>");
                                      out.println(vEti.SelectOneRowSinTD("iCveRama","",dRama.FindByAll(" Where iCveServicio =" + request.getParameter("iCveServicio")),"iCveRama", "cDscRama", request, "0", true));
                                      out.println("</td>");
                                      out.println("</tr>");
                                }
                                   
                                   
                                                                  
                                    
                                   String Breve = "";
                                   try{
                                       Breve = dMEDSintoma.Sentencia(" SELECT CDSCBREVE FROM MEDSINTOMAS WHERE ICVESERVICIO = "+ request.getParameter("iCveServicio")
                                                             + " AND ICVERAMA = "+request.getParameter("iCveRama")
                                                             + "  AND ICVESINTOMA = "+request.getParameter("iCveSintoma"));
                                   }catch(Exception ex){
                                       Breve = "";
                                   }
                                   
                                   String Pregunta = "";
                                   try{
                                       Pregunta = dMEDSintoma.Sentencia(" SELECT CPREGUNTA FROM MEDSINTOMAS WHERE ICVESERVICIO = "+ request.getParameter("iCveServicio")
                                                             + " AND ICVERAMA = "+request.getParameter("iCveRama")
                                                             + "  AND ICVESINTOMA = "+request.getParameter("iCveSintoma"));
                                   }catch(Exception ex){
                                       Pregunta = "";
                                   }

                                   
                                    out.println("</tr>");
                                    out.println("<td class=\"EEtiqueta\">Descripción Breve:</td>");
                                    out.println("<td class=\"ECampo\" colspan=\"4\">"+Breve+"</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Pregunta:</td>");
                                    out.println("<td class=\"ECampo\" colspan=\"4\">"+Pregunta+"</td>");
                                    out.println("</tr>");

                                    
                                    if(request.getParameter("hdTpoResp").trim().equals("1")){
                                        %>
                                            <input type="hidden" name="iCveSintoma" 
                                                   value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("iCveSintoma")); else out.print(cClave);%>">
                                        <%  out.println("<tr>");
                                            out.println(vEti.Texto("EEtiqueta","La regla se aplicara si la respuesta es igual a:"));
                                        %>
                                            <td class="ECampo"  colspan="4" >Si
                            <% 
                                            String cDisable = "";
                                            String cChecked = "";
                                            out.println("<input type=\"radio\" name=\"Logica\" value=\"1\" " +cDisable+ ">");
                                            out.println("No");
                                            out.println("<input type=\"radio\" name=\"Logica\" value=\"0\" " +cDisable+ ">");
                                            out.println("</td>");
                                            out.println("</tr>");
                                           /* 
                                            out.println("<tr>");
                                            out.print(vEti.EtiCampoCS("EEtiqueta","Mayor a:","ECampo","text",50,250,4,"iMayorA", "",0,"","fMayus(this);",false,true,lCaptura));
                                            out.println("</tr>");
                                            
                                            out.println("<tr>");
                                            out.print(vEti.EtiCampoCS("EEtiqueta","Menor a:","ECampo","text",50,250,4,"iMenorA", "",0,"","fMayus(this);",false,true,lCaptura));
                                            out.println("</tr>");
                                            
                                            out.println("<tr>");
                                            out.print(vEti.EtiCampoCS("EEtiqueta","Igual a:","ECampo","text",50,250,4,"iIgualA", "",0,"","fMayus(this);",false,true,lCaptura));
                                            out.println("</tr>");
                                             */        

                                            out.println("<input type=\"hidden\" name=\"iMayorA\" value=\"\">");
                                            out.println("<input type=\"hidden\" name=\"iMenorA\" value=\"\">");
                                            out.println("<input type=\"hidden\" name=\"iIgualA\" value=\"\">");

                                                                                                                                                                                                                                                        
                                            out.println("<tr>");
                                            out.println(vEti.Texto("EEtiqueta","Regla Activa:"));
                                            String cDisable2 = "";
                                            String cChecked2 = "";
                                            out.println("<td class=\"ECampo\" colspan=3>");
                                            out.println("<input type=\"checkbox\" name=\"lActivo\"  " + cDisable2 + " " +  cChecked2+ ">");
                                            out.println("</td>");
                                            
                                            
                                            out.println("<tr>");
                                            out.println(vEti.EtiAreaTextoCS("EEtiqueta","Leyenda de alerta:","ECampo",150,5,5,"cAlerta","",0,"","fMayus(this);",false,true,lCaptura));
                                            //out.print(vEti.EtiCampoCS("EEtiqueta","Leyenda de alerta","ECampo","text",200,200,4,"cAlerta", "",0,"","",false,true,lCaptura));
                                            out.println("</tr>");
                                            
                                            
                                            out.println("<tr>");
                                            //out.println("<input type=\"hidden\" name=\"cdscRegla\" value=\"\">");
                                            out.print(vEti.EtiCampoCS("EEtiqueta","Restringir el número de decimales a:","ECampo","text",50,250,4,"cdscRegla", "",0,"","fMayus(this);",false,true,lCaptura));
                                            //out.print(vEti.EtiCampoCS("EEtiqueta","Restringir el número de decimales a","ECampo","text",300,300,4,"cdscRegla", "",0,"","",false,true,lCaptura));
                                            out.println("</tr>");
                                            
                                            
                                            %>
                                            
                                            </table>
                                                                                   
                                  <!--
                                       </tr>
                                       </table>
                                       </td>-->
                                    <%
                                 }
                                 if(request.getParameter("hdTpoResp").trim().equals("8")){
                                                                                                 %>
                                                            <input type="hidden" name="iCveSintoma" 
                                                                   value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("iCveSintoma")); else out.print(cClave);%>">
                                                        <% 
                                                             /*                                                                 
                                                            out.println("<tr>");
                                                            out.println(vEti.Texto("EEtiqueta","La regla se aplicara si la respuesta es igual a:"));
                 *                                          out.println("<td class=\"ECampo\"  colspan=\"4\" >Si");

                                                            String cDisable = "";
                                                            String cChecked = "";
                                                            out.println("<input type=\"radio\" name=\"Logica\" value=\"1\" " +cDisable+ ">");
                                                            out.println("No");
                                                            out.println("<input type=\"radio\" name=\"Logica\" value=\"0\" " +cDisable+ ">");
                                                            out.println("</td>");
                                                            out.println("</tr>");

                                                            out.println("<tr>");
                                                            out.print(vEti.EtiCampoCS("EEtiqueta","Mayor a","ECampo","text",50,250,4,"iMayorA", "",0,"","fMayus(this);",false,true,lCaptura));
                                                            out.println("</tr>");

                                                            out.println("<tr>");
                                                            out.print(vEti.EtiCampoCS("EEtiqueta","Menor a:","ECampo","text",50,250,4,"iMenorA", "",0,"","fMayus(this);",false,true,lCaptura));
                                                            out.println("</tr>");

                                                            out.println("<tr>");
                                                            out.print(vEti.EtiCampoCS("EEtiqueta","Igual a:","ECampo","text",50,250,4,"iIgualA", "",0,"","fMayus(this);",false,true,lCaptura));
                                                            out.println("</tr>");
                                                            */

                                                            out.println("<input type=\"hidden\" name=\"Logica\" value=\"\">");
                                                            out.println("<input type=\"hidden\" name=\"iMayorA\" value=\"\">");
                                                            out.println("<input type=\"hidden\" name=\"iMenorA\" value=\"\">");

                                                            out.println("<tr>");
                                                            out.println("<td class=\"EEtiqueta\">Igual a:</td>");
                                                            out.println("<td class='ECampo' >");
                                                            out.println("<SELECT NAME=\"iIgualA\" SIZE=\"1\" onChange=\"\">");
                                                                    out.println("<option value=\"-1\">Seleccione...</option>");
                                                            TDMEDRespSint dMEDRespSint = new TDMEDRespSint();
                                                            TVMEDRespSint vMEDRespSint = new TVMEDRespSint();
                                                            Vector vcdMEDRespSint = new Vector();
                                                           String cWhere = " icveservicio = "+ request.getParameter("iCveServicio")+ " and "+
                                                                            " icverama = " + request.getParameter("iCveRama")+ " and ";
                                                                            if(request.getParameter("iCveSintoma") != null)
                                                                                    cWhere = cWhere +" icvesintoma = " + request.getParameter("iCveSintoma");
                                                                             else{
                                                                                    if(request.getParameter("iCveSintoma") != null)
                                                                                               cWhere = cWhere +" icvesintoma = " + request.getParameter("hdSintoma");
                                                                                      else
                                                                                               cWhere = cWhere +" icvesintoma = 0";                           
                                                                             }

                                                            try{
                                                                vcdMEDRespSint = dMEDRespSint.FindByAll(cWhere);
                                                            }catch(Exception e){
                                                                vcdMEDRespSint = new Vector();
                                                            }
                                                            if (vcdMEDRespSint.size() > 0){
                                                                   for (int i = 0; i < vcdMEDRespSint.size(); i++){
                                                                      vMEDRespSint = (TVMEDRespSint)vcdMEDRespSint.get(i);
                                                                          %>
                                                                          <option value="<%=vMEDRespSint.getIOrden()%>.0"><%=vMEDRespSint.getCDescripcion()%></option>
                                                                        <%                                         
                                                                   }
                                                            }



                                                            out.println("</SELECT>");
                                                            out.println("</td></tr>");


                                                            out.println("<tr>");
                                                            out.println(vEti.Texto("EEtiqueta","Regla Activa:"));
                                                            String cDisable2 = "";
                                                            String cChecked2 = "";
                                                            out.println("<td class=\"ECampo\" colspan=3>");
                                                            out.println("<input type=\"checkbox\" name=\"lActivo\"  " + cDisable2 + " " +  cChecked2+ ">");
                                                            out.println("</td>");
/*
                                                            out.println("<tr>");
                                                            out.println(vEti.Texto("EEtiqueta","La Regla es motivo de No Aptitud:"));
                                                            String cDisable3 = "";
                                                            String cChecked3 = "";
                                                            out.println("<td class=\"ECampo\" colspan=3>");
                                                            out.println("<input type=\"checkbox\" name=\"lDictamenF\"  " + cDisable3 + " " +  cChecked3+ ">");
                                                            out.println("</td>");
                                                            */
                                                            
                                                            out.println("<tr>");
                                                            out.println(vEti.EtiAreaTextoCS("EEtiqueta","Leyenda de alerta:","ECampo",150,5,5,"cAlerta","",0,"","fMayus(this);",false,true,lCaptura));
                                                            //out.print(vEti.EtiCampoCS("EEtiqueta","Leyenda de alerta","ECampo","text",200,200,4,"cAlerta", "",0,"","",false,true,lCaptura));
                                                            out.println("</tr>");


                                                            out.println("<tr>");
                                                            //out.println("<input type=\"hidden\" name=\"cdscRegla\" value=\"\">");
                                                            out.print(vEti.EtiCampoCS("EEtiqueta","Restringir el número de decimales a:","ECampo","text",50,250,4,"cdscRegla", "",0,"","fMayus(this);",false,true,lCaptura));
                                                            //out.print(vEti.EtiCampoCS("EEtiqueta","Restringir el número de decimales a","ECampo","text",300,300,4,"cdscRegla", "",0,"","",false,true,lCaptura));
                                                            out.println("</tr>");



                                           %>
                                               <!--    </tr>   -->
                                                       </table>
                                               <!--    </td>   -->
                                              <%
                                      } 
                                            
                                      if(request.getParameter("hdTpoResp").trim().equals("3") || request.getParameter("hdTpoResp").trim().equals("5")){
                                                    %>
                                                        <input type="hidden" name="iCveSintoma" 
                                                               value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("iCveSintoma")); else out.print(cClave);%>">
                                                    <%  
                                                    /*
                                                        out.println("<tr>");
                                                        out.println(vEti.Texto("EEtiqueta","La regla se aplicara si la respuesta es igual a:"));
 *                                                  */
                                                    %>
                                                     <!-- 
                                                        <td class="ECampo"  colspan="4" >Si
                                                     -->       
                                                     <% 
                                                     /*   String cDisable = "";
                                                        String cChecked = "";
                                                        out.println("<input type=\"radio\" name=\"Logica\" value=\"1\" " +cDisable+ ">");
                                                        out.println("No");
                                                        out.println("<input type=\"radio\" name=\"Logica\" value=\"0\" " +cDisable+ ">");
                                                        out.println("</td>");
                                                        out.println("</tr>");
                                                      */  
                                                        
                                                        out.println("<input type=\"hidden\" name=\"Logica\" value=\"\">");
                                                        
                                                        out.println("<tr>");
                                                        out.print(vEti.EtiCampoCS("EEtiqueta","Mayor a:","ECampo","text",50,250,4,"iMayorA", "",0,"","fMayus(this);",false,true,lCaptura));
                                                        out.println("</tr>");

                                                        out.println("<tr>");
                                                        out.print(vEti.EtiCampoCS("EEtiqueta","Menor a:","ECampo","text",50,250,4,"iMenorA", "",0,"","fMayus(this);",false,true,lCaptura));
                                                        out.println("</tr>");

                                                        out.println("<tr>");
                                                        out.print(vEti.EtiCampoCS("EEtiqueta","Igual a:","ECampo","text",50,250,4,"iIgualA", "",0,"","fMayus(this);",false,true,lCaptura));
                                                        out.println("</tr>");
                                                      
                                                        //out.println(vEti.EtiAreaTextoCS("EEtiqueta","Nota o Caracter igual a:","ECampo",50,5,5,"cdscRegla","",0,"","",false,true,lCaptura));
                                                        ///out.println("<input type=\"hidden\" name=\"cdscRegla\" value=\"\">");
                                                        out.println("<input type=\"hidden\" name=\"iCveRegla\" value=\"\">");

                                                        out.println("<tr>");
                                                        out.println(vEti.Texto("EEtiqueta","Regla Activa:"));
                                                        String cDisable2 = "";
                                                        String cChecked2 = "";
                                                        out.println("<td class=\"ECampo\" colspan=3>");
                                                        out.println("<input type=\"checkbox\" name=\"lActivo\"  " + cDisable2 + " " +  cChecked2+ ">");
                                                        out.println("</td>");
/*
                                                        out.println("<tr>");
                                                        out.println(vEti.Texto("EEtiqueta","La Regla es motivo de No Aptitud:"));
                                                        String cDisable3 = "";
                                                        String cChecked3 = "";
                                                        out.println("<td class=\"ECampo\" colspan=3>");
                                                        out.println("<input type=\"checkbox\" name=\"lDictamenF\"  " + cDisable3 + " " +  cChecked3+ ">");
                                                        out.println("</td>");
*/
                                                        out.println("<tr>");
                                                        out.println(vEti.EtiAreaTextoCS("EEtiqueta","Leyenda de alerta:","ECampo",150,5,5,"cAlerta","",0,"","fMayus(this);",false,true,lCaptura));
                                                        //out.print(vEti.EtiCampoCS("EEtiqueta","Leyenda de alerta","ECampo","text",200,200,4,"cAlerta", "",0,"","",false,true,lCaptura));
                                                        out.println("</tr>");


                                                        out.println("<tr>");
                                                        out.print(vEti.EtiCampoCS("EEtiqueta","Restringir el número de decimales a:","ECampo","text",50,250,4,"cdscRegla", "",0,"","fMayus(this);",false,true,lCaptura));
                                                        //out.print(vEti.EtiCampoCS("EEtiqueta","Restringir el número de decimales a","ECampo","text",300,300,4,"cdscRegla", "",0,"","",false,true,lCaptura));
                                                        out.println("</tr>");
                                                        %>
                                                        </table>
                                                     <% 
                                                     }
                                    
                                    
    
                                      if(request.getParameter("hdTpoResp").trim().equals("2") || request.getParameter("hdTpoResp").trim().equals("4") || request.getParameter("hdTpoResp").trim().equals("7")){
                                                    %>
                                                        <input type="hidden" name="iCveSintoma" 
                                                               value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("iCveSintoma")); else out.print(cClave);%>">
                                                    <%  
                                                     /*
                                                        out.println("<tr>");
                                                        out.println(vEti.Texto("EEtiqueta","La regla se aplicara si la respuesta es igual a:"));
 *                                                   */
                                                    %>
                                                     <!--
                                                        <td class="ECampo"  colspan="4" >Si
                                                      -->       
                                                     <% 
                                                     /*
                                                        String cDisable = "";
                                                        String cChecked = "";
                                                        out.println("<input type=\"radio\" name=\"Logica\" value=\"1\" " +cDisable+ ">");
                                                        out.println("No");
                                                        out.println("<input type=\"radio\" name=\"Logica\" value=\"0\" " +cDisable+ ">");
                                                        out.println("</td>");
                                                        out.println("</tr>");
                                                        
                                                       
                                                        out.println("<tr>");
                                                        out.print(vEti.EtiCampoCS("EEtiqueta","Mayor a:","ECampo","text",50,250,4,"iMayorA", "",0,"","fMayus(this);",false,true,lCaptura));
                                                        out.println("</tr>");

                                                        out.println("<tr>");
                                                        out.print(vEti.EtiCampoCS("EEtiqueta","Menor a:","ECampo","text",50,250,4,"iMenorA", "",0,"","fMayus(this);",false,true,lCaptura));
                                                        out.println("</tr>");

                                                        out.println("<tr>");
                                                        out.print(vEti.EtiCampoCS("EEtiqueta","Igual a:","ECampo","text",50,250,4,"iIgualA", "",0,"","fMayus(this);",false,true,lCaptura));
                                                        out.println("</tr>");
 *                                                      */
                                                        out.println("<input type=\"hidden\" name=\"Logica\" value=\"\">");
                                                        out.println("<input type=\"hidden\" name=\"iMayorA\" value=\"\">");
                                                        out.println("<input type=\"hidden\" name=\"iMenorA\" value=\"\">");
                                                        out.println("<input type=\"hidden\" name=\"iIgualA\" value=\"\">");
                                                        
                                                        out.println(vEti.EtiAreaTextoCS("EEtiqueta","Nota o Caracter igual a:","ECampo",50,5,5,"cdscRegla","",0,"","",false,true,lCaptura));

                                                        out.println("<tr>");
                                                        out.println(vEti.Texto("EEtiqueta","Regla Activa:"));
                                                        String cDisable2 = "";
                                                        String cChecked2 = "";
                                                        out.println("<td class=\"ECampo\" colspan=3>");
                                                        out.println("<input type=\"checkbox\" name=\"lActivo\"  " + cDisable2 + " " +  cChecked2+ ">");
                                                        out.println("</td>");
/*
                                                        out.println("<tr>");
                                                        out.println(vEti.Texto("EEtiqueta","La Regla es motivo de No Aptitud:"));
                                                        String cDisable3 = "";
                                                        String cChecked3 = "";
                                                        out.println("<td class=\"ECampo\" colspan=3>");
                                                        out.println("<input type=\"checkbox\" name=\"lDictamenF\"  " + cDisable3 + " " +  cChecked3+ ">");
                                                        out.println("</td>");
*/
                                                        out.println("<tr>");
                                                        out.println(vEti.EtiAreaTextoCS("EEtiqueta","Leyenda de alerta:","ECampo",150,5,5,"cAlerta","",0,"","fMayus(this);",false,true,lCaptura));
                                                        //out.print(vEti.EtiCampoCS("EEtiqueta","Leyenda de alerta","ECampo","text",200,200,4,"cAlerta", "",0,"","",false,true,lCaptura));
                                                        out.println("</tr>");

                                                        out.println("<tr>");
                                                        out.print(vEti.EtiCampoCS("EEtiqueta","Restringir el número de decimales a:","ECampo","text",50,250,4,"cdscRegla", "",0,"","fMayus(this);",false,true,lCaptura));
                                                        //out.print(vEti.EtiCampoCS("EEtiqueta","Restringir el número de decimales a","ECampo","text",300,300,4,"cdscRegla", "",0,"","",false,true,lCaptura));
                                                        out.println("</tr>");
                                                        %>
                                                        </table>
                                                     <% 
                                                     }                                    
                                             
                                    %>
                                    </tr>
                                    </table>
                                    </td>
                                    <%
                                    out.println("</tr>");

                                    if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                      out.println("<tr>");
                                      out.println(vEti.Texto("EEtiqueta","Valor de Referencia:"));
                                      out.println("<td colspan='3'><input type='text' name='cValRef' size=55 maxlength=50></td>");
                                      out.println("</tr>");
                                    }

                                }else {

                                     if (bs != null){

                                     if (lCaptura){
                                     %>
                                        <input type="hidden" name="iCveServicio" value="<%=bs.getFieldValue("iCveServicio","&nbsp;").toString()%>">
                                        <input type="hidden" name="iCveRama" value="<%=bs.getFieldValue("iCveRama","").toString()%>">
                                        <input type="hidden" name="iCveSintoma" value="<%=bs.getFieldValue("iCveSintoma","").toString()%>">
                                    <%
                                        out.println("<tr>");
                                    //System.out.println(bs.getFieldValue("cDscServicio","&nbsp;"));
                                    //System.out.println(bs.getFieldValue("cDscRama","&nbsp;"));
                                        out.print(vEti.EtiCampo("EEtiqueta","Servicio:","ECampo","text",10,10,"cDscServicio",bs.getFieldValue("cDscServicio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.print(vEti.EtiCampo("EEtiqueta","Rama:","ECampo","text",10,10,"iCveRama", bs.getFieldValue("cDscRama","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");
                                     }else{
	                                   out.println("<tr>");
	                                   out.println(vEti.Texto("EEtiqueta","Servicio:"));
	                                   TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
	                                   TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
	                                   out.println("<td class='ECampo' >");
	                                   out.println(vEti.SelectOneRowSinTD("iCveServicio","llenaSLT(1,this.value,'','',document.forms[0].iCveRama);",vMedSrv,"iCveServicio", "cDscServicio", request, "0", true));
	                                   out.println("</td>");
	                                   out.println(vEti.Texto("EEtiqueta","Rama:"));
	
	                                   if (request.getParameter("iCveRama") == null){
	                                      out.println("<td class='ECampo'>");
	                                      out.println("<SELECT NAME='iCveRama' SIZE='1' OnChange='fillBusca();'");
	                                      out.println("</SELECT>");
	                                      out.println("</td>");
	                                      out.println("</tr>");
	                                   }
	                                   else{
	                                      TDMEDRama dRama = new TDMEDRama();
	                                      out.println("<td>");
	                                      out.println(vEti.SelectOneRowSinTD("iCveRama","fillBusca();",dRama.FindByAll(" Where iCveServicio =" + request.getParameter("iCveServicio")),"iCveRama", "cDscRama", request, "0", true));
	                                      out.println("</td>");
	                                      out.println("</tr>");
	                                     }
                                    }


                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Clave del Síntoma:","ECampo","text",10,10,4,"iCveSintoma", bs.getFieldValue("iCveSintoma","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    %>
                                    <input type="hidden" name="iCveSintoma" value="<%=bs.getFieldValue("iCveSintoma","").toString()%>">
                                    <%
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Descripción Breve:","ECampo","text",75,150,4,"cDscBreve", bs.getFieldValue("cDscBreve","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Pregunta:","ECampo","text",75,150,4,"cPregunta", bs.getFieldValue("cPregunta","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Clave de la Regla:","ECampo","text",10,10,4,"iCveRegla", bs.getFieldValue("iCveRegla","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    %>
                                    <input type="hidden" name="iCveRegla" value="<%=bs.getFieldValue("iCveRegla","").toString()%>">
                                    <%
                                    out.println("</tr>");
                                    
                                    String Respuesta ="";
                                   //if(bs.getFieldValue("Logica", "").toString().equalsIgnoreCase("1")){
                                    if(bs.getFieldValue("Logica").equals("1")){
                                          Respuesta ="SI";
                                       }else{
                                          Respuesta ="NO";
                                       }

                                if(bs.getFieldValue("Logica").equals("1")){
                                            out.println("<tr>");
                                            out.print(vEti.EtiCampoCS("EEtiqueta","Logica Igual a:","ECampo","text",75,150,4,"Logica", Respuesta,0,"","fMayus(this);",false,true,lCaptura));
                                            out.println("</tr>");
                                }else{
                                        
                                            out.println("<tr>");
                                            out.print(vEti.EtiCampoCS("EEtiqueta","Mayor a:","ECampo","text",75,150,4,"iMayorA", bs.getFieldValue("iMayorA","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                            out.println("</tr>");

                                            out.println("<tr>");
                                            out.print(vEti.EtiCampoCS("EEtiqueta","Menor a:","ECampo","text",75,150,4,"iMenorA", bs.getFieldValue("iMenorA","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                            out.println("</tr>");

                                            out.println("<tr>");

                                            if(request.getParameter("hdTpoResp").trim().equals("8")){
                                                String Descripcion = dMEDSintoma.Sentencia("SELECT CDESCRIPCION "
                                                                                         + "FROM MEDRESPSINT WHERE 	"
                                                                                         + "ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND	"
                                                                                         + "ICVERAMA = "+request.getParameter("iCveRama")+" AND	"
                                                                                         + "ICVESINTOMA = "+ bs.getFieldValue("iCveSintoma").toString()+" AND	"
                                                                                         + "IORDEN = "+bs.getFieldValue("iIgualA").toString()+" AND	"
                                                                                         + "ICVETIPORESP = 8");

                                                out.print(vEti.EtiCampoCS("EEtiqueta","Igual A","ECampo","text",75,150,4,"iIgualA", Descripcion,0,"","fMayus(this);",false,true,lCaptura));
                                            }else{
                                                if(request.getParameter("hdTpoResp").trim().equals("4")){
                                                    String Descripcion = dMEDSintoma.Sentencia("SELECT CDSCREGLA "
                                                                                         + "FROM MEDREGSIN WHERE 	"
                                                                                         + "ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND	"
                                                                                         + "ICVERAMA = "+request.getParameter("iCveRama")+" AND	"
                                                                                         + "ICVESINTOMA = "+ bs.getFieldValue("iCveSintoma").toString()+" AND	"
                                                                                         + "ICVEREGLA = "+bs.getFieldValue("iCveRegla","&nbsp;").toString()+" AND	"
                                                                                         + "ICVEMDOTRANS = "+request.getParameter("iCveMdoTrans2")+" AND "
                                                                                         + "ICVECATEGORIA = "+request.getParameter("iCveCategoria2")+"");
                                                    
                                                    out.print(vEti.EtiCampoCS("EEtiqueta","Nota o Caracter igual a:","ECampo","text",75,150,4,"iIgualA", Descripcion,0,"","fMayus(this);",false,true,lCaptura));
                                            }else
                                                    out.print(vEti.EtiCampoCS("EEtiqueta","Igual A:","ECampo","text",75,150,4,"iIgualA", bs.getFieldValue("iIgualA","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                            }
                                            out.println("</tr>");
                                }
                                    
                                    
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Regla Activa:"));
                                    String cDisable5 = "";
                                    String cChecked5 = "";
                                    if (!lCaptura)
                                       cDisable5 = "disabled";
                                    if (bs.getFieldValue("lActivo").toString().compareToIgnoreCase("0")!=0)
                                        cChecked5 = "checked";
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lActivo\" value=\"1\" " + cDisable5 + " " +  cChecked5+ ">");
                                    out.println("</td>");
                                    out.println("</tr>");
                                   /*
                                    out.println("<tr>");
                                     out.println(vEti.Texto("EEtiqueta","La Regla es motivo de No Aptitud:"));
                                    String cDisable6 = "";
                                    String cChecked6 = "";
                                    if (!lCaptura)
                                       cDisable6 = "disabled";
                                    if (bs.getFieldValue("lDictamenF").toString().compareToIgnoreCase("0")!=0)
                                        cChecked6 = "checked";
                                    
                                    
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lDictamenF\" value=\"1\" " + cDisable6 + " " +  cChecked6+ ">");
                                    out.println("</td>");
                                    out.println("</tr>");*/

                                    out.println("<tr>");
                                    //out.print(vEti.EtiCampoCS("EEtiqueta","Leyenda de alerta:","ECampo","text",150,10,4,"cAlerta", bs.getFieldValue("cAlerta","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println(vEti.EtiAreaTextoCS("EEtiqueta","Leyenda de alerta:","ECampo",150,5,5,"cAlerta",bs.getFieldValue("cAlerta","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Restringir el número de decimales a:","ECampo","text",75,150,4,"cdscRegla", bs.getFieldValue("cdscRegla","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    //out.print(vEti.EtiCampoCS("EEtiqueta","Restringir el número de decimales a:","ECampo","text",10,1,4,"cdscRegla", bs.getFieldValue("cdscRegla","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                  
                                    
                                    String cDisable3 = "";
                                    String cChecked3 = "";

                                    TDMEDTpoResp dTpoResp = new TDMEDTpoResp();
                                    TVMEDTpoResp vTpoResp = new TVMEDTpoResp();
                                    Vector vTipoResp = new Vector();
                                    vTipoResp = dTpoResp.FindByAll();
                                    
                                    TDMEDRespSint dMEDRespSint = new TDMEDRespSint();
                                    TVMEDRespSint vMEDRespSint = new TVMEDRespSint();
                                    Vector vRespSint = new Vector();
                                    String cWhere = " icveservicio = "+ request.getParameter("iCveServicio")+ " and "+
                                                    " icverama = " + request.getParameter("iCveRama")+ " and ";
                                                    if(request.getParameter("hdSintoma") != null)
                                                        cWhere = cWhere +" icvesintoma = " + request.getParameter("hdSintoma");
                                                    else{
                                                        if(request.getParameter("hdSintoma") != null)
                                                                cWhere = cWhere +" icvesintoma = " + request.getParameter("hdSintoma");
                                                        else
                                                                cWhere = cWhere +" icvesintoma = 0";                           
                                                    }
                                    
                                    
                                    try{
                                           vRespSint = dMEDRespSint.FindByAll(cWhere);
                                    }catch(Exception ex) {
                                            vRespSint = new Vector();
                                    }
                                    
                                    if (!lCaptura)
                                       cDisable3 = "disabled";
                                    
                                    if(request.getParameter("hdTpoResp").trim().equals("8")){
                                    if (vRespSint.size() > 0){
                                       for (int i = 0; i < vRespSint.size(); i++){
                                          vMEDRespSint = (TVMEDRespSint)vRespSint.get(i);
                                           if (i>0){
                                                out.println("<tr>");
                                             }
                                             out.println("<td class=\"ECampo\" colspan=\"4\">");
                                             out.println(vMEDRespSint.getCDescripcion());
                                             out.println("</td>");
                                             if (i>0){
                                                out.println("</tr>");
                                             }
                                       }
                                    }
                                   }//Final de Tipo respuesta 8
                                    
                                    
                                    %>
                                    </tr>
                                    </table>
                                    </td>
                                    <%
                                    out.println("</tr>");
                                    

                                    if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                      out.println("<tr>");
                                      out.println(vEti.Texto("EEtiqueta","Valor de Referencia:"));
                                      if(request.getParameter("hdBoton").compareTo("Modificar")==0)
                                        out.println("<td colspan='3'><input type='text' name='cValRef' size=55 maxlength=50 value='" + bs.getFieldValue("cValRef", "&nbsp;") + "'></td>");
                                      else
                                        out.println("<td colspan='3' class='ETabla'>" + bs.getFieldValue("cValRef", "&nbsp;") + "</td>");
                                      out.println("</tr>");
                                    }

                                }else{

                                    %>
                                      <tr>
                                          <td class="EEtiqueta">Servicio:</td>
                                          <td class="ETabla">
                                             <select name="iCveServicio" size="1" onChange="fillNext();">
                                    <%

                                             if (request.getParameter("iCveServicio")!=null){
                                    %>
                                                <option value=0 Selected>Seleccione...</option>
                                    <%
                                            }else{
                                    %>
                                                <option value=0>Seleccione...</option>
                                    <%
                                            }

                                            for (int i = 0;i<vMedSrv.size(); i++){
                                                vMEDServicio = (TVMEDServicio) vMedSrv.get(i);
                                                if (request.getParameter("iCveServicio") != null){
                                                   if (Integer.parseInt((String)request.getParameter("iCveServicio")) == vMEDServicio.getICveServicio()){
                                                      out.print("<option value =" + vMEDServicio.getICveServicio() + " selected>" + vMEDServicio.getCDscServicio() + "</option>");
                                                   }else{
                                                      out.print("<option value =" + vMEDServicio.getICveServicio() + " >" + vMEDServicio.getCDscServicio() + "</option>");
                                                   }
                                                }else{
                                                   out.print("<option value =" + vMEDServicio.getICveServicio() + " >" + vMEDServicio.getCDscServicio() + "</option>");
                                                }
                                            }
                                    %>
                                         </select>
                                    </td>
                                    <td class="EEtiqueta">Rama:</td>
                                    <td class="ETabla">
                                    <%
                                        TDMEDRama dMEDRama = new TDMEDRama();
                                        TVMEDRama vMEDRama = new TVMEDRama();
                                        Vector vMEDr = new Vector();
                                        String cFiltro = "";
                                        if (request.getParameter("iCveServicio") != null){
                                            cFiltro = " WHERE iCveServicio = " + request.getParameter("iCveServicio");
                                        }else{
                                            cFiltro = " WHERE iCveServicio = " + "0";
                                        }
                                        vMEDr = dMEDRama.FindByAll(cFiltro);
                                    %>
                                        <select name="iCveRama" size="1" OnChange="fillBusca();">
                                    <%
                                        out.print("<option value=0 selected>Seleccione...</option>");
                                        if (vMEDr.size() > 0){
                                           for (int x = 0;x<vMEDr.size(); x++){
                                               vMEDRama = (TVMEDRama) vMEDr.get(x);
                                               if (request.getParameter("iCveRama")!= null){
                                                  if (Integer.parseInt((String)request.getParameter("iCveRama")) == vMEDRama.getICveRama()){
                                                     out.print("<option value =" + vMEDRama.getICveRama() + " selected>" + vMEDRama.getCDscRama() + "</option>");
                                                  }else{
                                                     out.print("<option value =" + vMEDRama.getICveRama() + " >" + vMEDRama.getCDscRama() + "</option>");
                                                  }
                                               }else{
                                                  out.print("<option value =" + vMEDRama.getICveRama() + " >" + vMEDRama.getCDscRama() + "</option>");
                                               }
                                           }
                                        }
                                    %>
                                        </select>
                                        </td>
                                        </tr>
                                    <%
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 3,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
                                 }
                                }

                            %>
                          </table><% // Fin de Datos %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
 
 <%
if(request.getParameter("hdRedir")!=null && !request.getParameter("hdBoton").equals("Modificar") ){
	if(request.getParameter("hdRedir").equals("si"))
	%>
	<input type="hidden" name="iCveSintoma" value="<%=request.getParameter("iCveSintoma")%>>">
	  <script language="JavaScript">fIrCatalogo(<%=request.getParameter("iCveSintoma")%>);</script>
	  
	<%
}
%>
 
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
