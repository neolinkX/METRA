
<%@ page import="gob.sct.medprev.*"%> 
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<html>
<%
    SEDetEmpDirCFG  clsConfig = new SEDetEmpDirCFG(); //Obt. Datos Generales
    TDPERDatos   tdPerDatos = new TDPERDatos(); //Obt. Datos de los Combos
    TDGRLEmpresas   tdGRLEmpresas = new TDGRLEmpresas(); //Obt. Datos de los Combos
    TSimpleCampo vSCampo = new TSimpleCampo();  //Captura de Datos    
    String sAccion = request.getParameter("sAccion");   
    
    String          valoren="valoren";
    String          iCvePaisDR=request.getParameter("iCvePaisDR");
    String          iCveEstadoDR=request.getParameter("iCveEstadoDR");

    
        
    if ( (sAccion != null) && ((sAccion.equalsIgnoreCase("G")) || (sAccion.equalsIgnoreCase("GA")))){
    	TVPEREmpresa c = new TVPEREmpresa();
    	c.setICveEmpresa(Integer.parseInt(request.getParameter("hdiCveEmpresa")));
        c.seticvedomicilio(Integer.parseInt(request.getParameter("icvedomicilio")));
        c.setccalle(request.getParameter("ccalle"));        
        c.setccolonia(request.getParameter("ccolonia"));
        c.setcciudad(request.getParameter("cciudad"));
        c.seticp(Integer.parseInt(request.getParameter("icp")));
        c.seticvepais(Integer.parseInt(request.getParameter("iCvePaisD")));
        c.seticveestado(Integer.parseInt(request.getParameter("iCveEstadoD")));
        c.seticvemunicipio(Integer.parseInt(request.getParameter("iCveMunicipio")));
        c.setctel(request.getParameter("ctel"));
        c.setcfax(request.getParameter("cfax"));
        c.setccorreoelec(request.getParameter("ccorreoelec"));
        if(request.getParameter("lactivo") != null){
        	c.setlactivo(Integer.parseInt(request.getParameter("lactivo")));
        }else{
        	c.setlactivo(0);        	
        }
        
        if(sAccion.equalsIgnoreCase("G")){
        	tdGRLEmpresas.UpdEmpresa(c);
        }
        if(sAccion.equalsIgnoreCase("GA")){
        	tdGRLEmpresas.insertCT(null, c);
        }
       //tdPerDatos.UpdEmpresa(c);
      }


  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("SEDetEmp.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("SEDetEmp.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  TError      vErrores      = new TError();  
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  
/*
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Muestra|";    // modificar
  String cCveOrdenar  = "iCveMuestra|";  // modificar
  String cDscFiltrar  = "Muestra|";    // modificar
  String cCveFiltrar  = "iCveMuestra|";  // modificar
  String cTipoFiltrar = "8|";                // modificar
  boolean lFiltros    = false;                 // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";             // modificar
*/
  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  /*String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();*/
  BeanScroller bs = clsConfig.getBeanScroller();
  /*String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();*/
  
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SEDetEmp.js"%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\SEDetEmp.js"></SCRIPT>-->
<script language="JavaScript">
//-------FCSReq7 
function fValidaTodo(Accion){
    var form = document.forms[0];

    if (Accion == 'G' || Accion == 'GA') {
      cVMsg = '';

      if (form.ccalle)
        cVMsg = cVMsg + fSinValor(form.ccalle,0,'Calle:', true);

      if (form.cciudad)
        cVMsg = cVMsg + fSinValor(form.cciudad,2,'Ciudad:', true);
      
      if (form.ccolonia)
          cVMsg = cVMsg + fSinValor(form.ccolonia,2,'Colonia:', true);

      if (form.icp)
          cVMsg = cVMsg + fSinValor(form.icp,3,'C.P.:', true);
      
      if (form.iCvePaisD)
        cVMsg = cVMsg + fSinValor(form.iCvePaisD,3,'País:', true);
      
      if (form.iCveEstadoD)
          cVMsg = cVMsg + fSinValor(form.iCveEstadoD ,3,'EDO (Estado):', true);
      
      if (form.iCveMunicipio)
          cVMsg = cVMsg + fSinValor(form.iCveMunicipio,3,'Muncipio:', true);

      if (form.ctel)
        cVMsg = cVMsg + fSinValor(form.ctel,3,'Teléfono:', true);


        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }



function fModif(iCveEmpresa, Accion){
    form = document.forms[0];

    if ( fValidaTodo(Accion) ) {
      form.target = "_self";
      form.action = "SEDetEmpDir.jsp?hdiCveEmpresa=" +iCveEmpresa+ "&sAccion="+Accion;
      form.submit();
    }
}


function fIdentificar(iCveEmpresa){
    alert(iCveEmpresa);
  if(window.opener.fEmpSelected){
     window.opener.fEmpSelected(iCveEmpresa);
     window.close();
  }else{
      alert(iCveEmpresa);
     alert('El módulo ya no se encuentra disponible.');
     window.close();
  }
}



function llenaSLT(opc,val1,val2,val3,objDes){
    if(objDes!=''){
        if(objDes.name!=''){
            objDes.length=1;
            objDes[0].text="Cargando Datos...";
            window.parent.FRMOtroPanel.location="pg070102021P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
        } else {
            alert("Debe seleccionar un dato valido!");
            objDes.length=0;
            objDes.length=1;
            return false;
        }
    } else {
        window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
    }
}

function enviar_parametro(valor){
	var formulario = document.forms[0]; 
	var iCvePaisDR = formulario.iCvePaisD.value;
	var iCveEstadoDR = formulario.iCveEstadoD.value;
	var hdiCveEmpresa = formulario.hdiCveEmpresa.value;
	var sAccion = 'E';

	location = location.pathname + '?param=' + valor + '&sAccion='+sAccion+'&hdiCveEmpresa='+hdiCveEmpresa+'&iCveEstadoDR='+iCveEstadoDR+'&iCvePaisDR=' + iCvePaisDR;
	"&sAccion="+Accion;
	} 
	
function enviar_parametro2(valor){
	var formulario = document.forms[0]; 
	var iCvePaisDR = formulario.iCvePaisD.value;
	var iCveEstadoDR = formulario.iCveEstadoD.value;
	var hdiCveEmpresa = formulario.hdiCveEmpresa.value;
	var sAccion = 'ND';

	location = location.pathname + '?param=' + valor + '&sAccion='+sAccion+'&hdiCveEmpresa='+hdiCveEmpresa+'&iCveEstadoDR='+iCveEstadoDR+'&iCvePaisDR=' + iCvePaisDR;
	"&sAccion="+Accion;
	} 	
	
function displayResult(sel)
{
	var form = document.forms[0]; 
	form.iCveMunicipio.value = sel;
}


//-------FCSReq7



/*
  function fOnLoad(){

  }
*/

</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SEDetPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>

<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">


<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0">



<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  
  <input type="hidden" name="hdiCveEmpresa" value="<%=request.getParameter("hdiCveEmpresa")%>">    <% //FCSReq7 %>


  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
   <tr>
    <td valign="top">

                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
 
                               <%
                               
                               String Mun = request.getParameter("iCveMunicipio2");
                               
                                TEtiCampo vEti = new TEtiCampo();
                               TDPais dPaisNac = new TDPais();

                                  if (bs != null){
                                	  
                                	  %> <tr>
                                  			<td colspan="4" class="ETablaT">Detalle de la Dirección</td>
                                 		</tr>
                                	  <%
                                	  
                                    //Formatea Fecha
                                      String sFchRegistro = bs.getFieldValue("sRegistro","&nbsp;").toString();
                                      String[] fch = sFchRegistro.split("-");
                                      sFchRegistro = fch[2]+ "/" +fch[1]+ "/" +fch[0];
                                    //Formatea Fecha

                                    if ( (sAccion == null) || sAccion.equals("G") || sAccion.equals("GA")  ) {
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Empresa:","ECampo","text",10,10,"sDscEmpresa", bs.getFieldValue("sDscEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Clave Domicilio:","ECampo","text",10,10,"icvedomicilio", bs.getFieldValue("icvedomicilio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println(" <input type='hidden' name='icvedomicilio' value='"+ bs.getFieldValue("icvedomicilio","") + "'>");
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Calle:","ECampo","text",10,10,"ccalle", bs.getFieldValue("ccalle","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");
                                        
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",10,10,"ccolonia", bs.getFieldValue("ccolonia","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Ciudad:","ECampo","text",10,10,"cciudad", bs.getFieldValue("cciudad","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","C.P.:","ECampo","text",10,10,"icp", bs.getFieldValue("icp","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");
                                        
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","País:","ECampo","text",10,10,"cdscpais", bs.getFieldValue("cnombrePais","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
										out.println(" <input type='hidden' name='iCvePaisD' value='"+ bs.getFieldValue("icvepais","&nbsp;") + "'>");
										out.println(" <input type='hidden' name='iCvePaisDR' value='"+ bs.getFieldValue("icvepais","&nbsp;") + "'>");
										out.println("</tr>");
										
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta","EDO (Estado):","ECampo","text",10,10,"cdscestado", bs.getFieldValue("cnombreEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
										out.println(" <input type='hidden' name='iCveEstadoD' value='"+ bs.getFieldValue("icveestado","&nbsp;") + "'>");
										out.println(" <input type='hidden' name='iCveEstadoDR' value='"+ bs.getFieldValue("icveestado","&nbsp;") + "'>");
										out.println("</tr>");
										
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta","MUN (Municipio):","ECampo","text",10,10,"cdscmunicipio",bs.getFieldValue("cnombreMun","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
										out.println(" <input type='hidden' name='iCveMunicipio' value='"+ bs.getFieldValue("icvemunicipio","&nbsp;") + "'>");
										out.println(" <input type='hidden' name='iCveMunicipio2' value='"+ bs.getFieldValue("icvemunicipio","&nbsp;") + "'>");
										out.println("</tr>");
										
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta","Tel&eacute;fono:","ECampo","text",10,10,"ctel",bs.getFieldValue("ctel","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
										out.println("</tr>");
										
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta","Fax:","ECampo","text",10,10,"cfax",bs.getFieldValue("cfax","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
										out.println("</tr>");
										
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta","Correo electr&oacute;nico:","ECampo","text",10,10,"ccorreoelec",bs.getFieldValue("ccorreoelec","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
										out.println("</tr>");
										
										out.println("<tr>");
										out.println(vEti.Texto("EEtiqueta","Activo:"));
										String cDisable = "";
	                                    String cChecked = "";
	                                       cDisable = "disabled";
	                                    if (bs.getFieldValue("lactivo","0").toString().compareToIgnoreCase("0")!=0)
	                                        cChecked = "checked";
	                                    out.println("<td>");
	                                    out.println("<input type=\"checkbox\" name=\"lactivo\" value=\"1\" " + cDisable + " " +  cChecked+ ">");
	                                    out.println("</td>");
	                                    out.println("</tr>");
										
                                    }
                                    else{
                                    	if ( sAccion.equals("E") ){                                    
                                    	
                                        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd,MM,yyyy");
                                        String cDate=sdf.format(new java.util.Date()); 

                                        out.println("<tr>");
                                        //out.print(vSCampo.EtiCampo("EEtiqueta", "Empresa:","ECampo", "text", 50, 95, "sEmpresa", bs.getFieldValue("sDscEmpresa","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
                                        out.print(vEti.EtiCampo("EEtiqueta","Empresa:","ECampo","text",10,10,"sDscEmpresa", bs.getFieldValue("sDscEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        //out.print(vSCampo.EtiCampo("EEtiqueta", "Clave Domicilio:","ECampo", "text", 50, 95, "icvedomicilio", bs.getFieldValue("icvedomicilio","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
                                        out.print(vEti.EtiCampo("EEtiqueta","Clave Domicilio:","ECampo","text",10,10,"icvedomicilio", bs.getFieldValue("icvedomicilio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println(" <input type='hidden' name='icvedomicilio' value='"+ bs.getFieldValue("icvedomicilio","") + "'>");
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vSCampo.EtiCampo("EEtiqueta", "Calle:","ECampo", "text", 50, 50, "ccalle", bs.getFieldValue("ccalle","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vSCampo.EtiCampo("EEtiqueta", "Colonia:","ECampo", "text", 30, 30, "ccolonia", bs.getFieldValue("ccolonia","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vSCampo.EtiCampo("EEtiqueta", "Ciudad:","ECampo", "text", 50, 100, "cciudad", bs.getFieldValue("cciudad","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vSCampo.EtiCampo("EEtiqueta","C.P.:","ECampo","text",10,10,"icp", bs.getFieldValue("icp","&nbsp;").toString(),"","fMayus(this);",false,true,true, request));
                                        out.println("</tr>");
                                        
                                        out.println("<tr>");
                                        if (request.getParameter("iCvePaisDR")==null && request.getParameter("iCveEstadoDR")==null ){
                                        out.println("<tr>");
                                        out.println(vEti.Texto("EEtiqueta","Pa&iacute;s:"));
                                        out.println("<td>");
                                        out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, "0", true));
                                        out.println("</td>");
                                        out.println("</tr>");
                                        
                                        out.println("<tr>");
                                        out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
                                        out.println("</td>");  
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<select name=\"iCveEstadoD\">");
                                        out.println("<option value=\"valoren\">Seleccione...</option>");
                                        out.println("</select>");
                                        out.println("</td>");
                                        out.println("</tr>");
                                        
                                        out.println("<tr>");
                                        out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
                                        out.println("<td class='ECampo'>");
                                        out.println("<SELECT NAME='iCveMunicipio' SIZE='1' ");
                                        out.println("<option value=\"0\">Seleccione...</option>");
                                        out.println("</SELECT>");
                                        out.println("</td>");
                                        out.println("</tr>");
                                        }
                                        
                                        if (request.getParameter("iCvePaisDR")!=null && valoren.equalsIgnoreCase(iCveEstadoDR.trim())){
                                        
                                        out.println("<tr>");
                                        out.println(vEti.Texto("EEtiqueta","Pa&iacute;s:"));
                                        out.println("<td>");
                                        out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisDR, true));
                                        out.println("</td>");
                                        out.println("</td>");                                   
                                        out.println("</tr>");
                                        
                                        out.println("<tr>");
                                        out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
                                        out.println("</td>");  
                                        out.println("<td class='ECampo'>");
                                        out.println("<select name=\"iCveEstadoD\" onChange=\"enviar_parametro(this.value);\">");
                                        
                                        // Estado
                                       TVEntidadFed vEntidadFed = new TVEntidadFed();
                                       TDEntidadFed dEntidadFed = new TDEntidadFed();
                                       Vector vcEntidadFed = new Vector();
                                       vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString());
                                           if (vcEntidadFed.size() > 0){
                                           out.println("<option value=\"0\">Seleccione...</option>");
                                           for (int i = 0; i < vcEntidadFed.size(); i++){
                                               int j = 0;
                                               j = j + 1;
                                              vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
                                              if(vEntidadFed.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
                                                 out.println("");}
                                             else{
                                                 out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
                                           }}

                                           }else{
                                           out.println("<option value=\"0\">Seleccione...</option>");
                                           }
                                       out.println("</SELECT>");
                                       out.println("</td>");
                                       out.println("</tr>");
                                        
                                        out.println("<tr>");
                                        out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
                                        out.println("<td class='ECampo'>");
                                        out.println("<SELECT NAME='iCveMunicipio' SIZE='1' ");
                                        out.println("<option value=\"0\">Seleccione...</option>");
                                        out.println("</SELECT>");
                                        out.println("</td>");
                                        out.println("</tr>");
                                        }
                                        
       
                                       if(request.getParameter("iCvePaisDR")!=null && iCveEstadoDR != null){
                                            if(!(valoren.equalsIgnoreCase(iCveEstadoDR.trim()))){
                                       
                                        out.println("<tr>");
                                        out.println(vEti.Texto("EEtiqueta","Pa&iacute;s:"));
                                        out.println("<td>");
                                        out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisDR, true));
                                        out.println("</td>");
                                        out.println("</td>");                                
                                        out.println("</tr>");
                                        
                                        out.println("<tr>");
                                        out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
                                        out.println("</td>");  
                                        out.println("<td class='ECampo'>");
                                        out.println("<select name=\"iCveEstadoD\" onChange=\"enviar_parametro(this.value);\">");
                                        
                                        // Estado
                                       TVEntidadFed vEntidadFed = new TVEntidadFed();
                                       TDEntidadFed dEntidadFed = new TDEntidadFed();
                                       Vector vcEntidadFed = new Vector();
                                       vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString()+ " AND iCveEntidadFed = " + request.getParameter("iCveEstadoDR").toString());
                                           if (vcEntidadFed.size() > 0){
                                           for (int i = 0; i < vcEntidadFed.size(); i++){
                                               int j = 0;
                                               j = j + 1;
                                              vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
                                            if(vEntidadFed.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
                                                 out.println("");}
                                             else{
                                                 out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
                                           }}
                                         }
                                       TVEntidadFed vEntidadFed2 = new TVEntidadFed();
                                       TDEntidadFed dEntidadFed2 = new TDEntidadFed();
                                       Vector vcEntidadFed2 = new Vector();
                                       vcEntidadFed2 = dEntidadFed2.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString());
                                           if (vcEntidadFed2.size() > 0){
                                           for (int i = 0; i < vcEntidadFed2.size(); i++){
                                               int j = 0;
                                               j = j + 1;
                                              vEntidadFed2 = (TVEntidadFed) vcEntidadFed2.get(i);
	                                            if(vEntidadFed2.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
	                                                 out.println("");}
	                                             else{
	                                                 out.println("<option value=\""+vEntidadFed2.getICveEntidadFed()+"\">"+vEntidadFed2.getCNombre()+"</option>");
	                                           		}
                                            }
                                           }else{
                                           		out.println("<option value=\"0\">Seleccione...</option>");
                                           }
                                       out.println("</SELECT>");
                                       out.println("</td>");
                                       out.println("</tr>");
                                       
                                       out.println("<tr>");
                                       out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
                                       out.println("<td class='ECampo'>");
                                       out.println("<SELECT id='iCveMunicipio' NAME='iCveMunicipio' SIZE='1' ");
                                       TVMunicipio vMunicipio= new TVMunicipio();
                                           TDMunicipio dMunicipio = new TDMunicipio();
                                           Vector vcMunicipio= new Vector();
                                           vcMunicipio = dMunicipio.FindByAll(request.getParameter("iCvePaisDR").toString(),request.getParameter("iCveEstadoDR").toString());
                                           if (vcMunicipio.size() > 0){
                                           for (int i = 0; i < vcMunicipio.size(); i++){
                                              int j = 0;
                                              j = j + 1;
                                              vMunicipio = (TVMunicipio) vcMunicipio.get(i);
                                                out.println("<option value="+ vMunicipio.getICveMunicipio()+">"+vMunicipio.getCNombre()+"</option>");
                                               }
                                            }else{
                                       			out.println("<option value=\"0\">Seleccione...</option>");
                                       		}
                                       out.println("</SELECT>");
                                       out.println("</td>");
                                       out.println("</tr>");
	                                      }
                                         }
                                       
                                       
                                       out.println(" <input type='hidden' name='iCveMunicipio2' value='"+ request.getParameter("iCveMunicipio2") + "'>");
                                       
                                       %>
                                       <SCRIPT LANGUAGE="JavaScript">
                                       		displayResult(<%=Mun%>);
         								</SCRIPT>	
                                       <%

										out.println("<tr>");
										out.print(vSCampo.EtiCampo("EEtiqueta","Tel&eacute;fono:","ECampo","text",20,20,"ctel", bs.getFieldValue("ctel","&nbsp;").toString(),"","fMayus(this);",false,true,true, request));
										out.println("</tr>");
										
                                        out.println("<tr>");
										out.print(vSCampo.EtiCampo("EEtiqueta","Fax:","ECampo","text",20,20,"cfax", bs.getFieldValue("cfax","&nbsp;").toString(),"","fMayus(this);",false,true,true, request));
										out.println("</tr>");
										
										out.println("<tr>");
										out.print(vSCampo.EtiCampo("EEtiqueta","Correo electr&oacute;nico:","ECampo","text",50,50,"ccorreoelec", bs.getFieldValue("ccorreoelec","&nbsp;").toString(),"","fMayus(this);",false,true,true, request));
										out.println("</tr>");
										
										out.println("<tr>");
										out.println(vEti.Texto("EEtiqueta","Activo:"));
										String cDisable = "";
	                                    String cChecked = "";
	                                       cDisable = "enabled ";
	                                    if (bs.getFieldValue("lactivo","0").toString().compareToIgnoreCase("0")!=0)
	                                        cChecked = "checked";
	                                    out.println("<td>");
	                                    out.println("<input type=\"checkbox\" name=\"lactivo\" value=\"1\" " + cDisable + " " +  cChecked+ ">");
	                                    //out.println("<input type=\"checkbox\" name=\"lactivo\" value=\"1" +  cChecked+ "\">");
	                                    out.println("</td>");
	                                    out.println("</tr>");
                                       
                                    }else{
                                    	if(sAccion.equals("ND")){
                                    		out.println("<tr>");
 	                                       out.print(vEti.EtiCampo("EEtiqueta","Empresa:","ECampo","text",10,10,"sDscEmpresa", bs.getFieldValue("sDscEmpresa","&nbsp;").toString() ,0,"","fMayus(this);",false,true,false));
 	                                       out.println("</tr>");
 	                                       
 	                                       out.println("<tr>");
 	                                        //out.print(vSCampo.EtiCampo("EEtiqueta", "Clave Domicilio:","ECampo", "text", 50, 95, "icvedomicilio", bs.getFieldValue("icvedomicilio","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
 	                                        //int dom = Integer.parseInt(request.getParameter("icvedomicilio")) +1;
 	                                        //out.print(vEti.EtiCampo("EEtiqueta","Clave Domicilio:","ECampo","text",10,10,"icvedomicilio", Integer.toString(dom).toString(),0,"","fMayus(this);",false,true,false));
 	                                        out.println(" <input type='hidden' name='icvedomicilio' value='1'>");
 	                                        out.println("</tr>");
 	                                        
 	                                       out.println(" <input type='hidden' name='sAccion' value='ND'>");

	                                        out.println("<tr>");
	                                        if (request.getParameter("iCvePaisDR")==null && request.getParameter("iCveEstadoDR")==null ){
	                                        out.println("<tr>");
	                                        out.println(vEti.Texto("EEtiqueta","Pa&iacute;s:"));
	                                        out.println("<td>");
	                                        out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro2(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, "0", true));
	                                        out.println("</td>");
	                                        out.println("</tr>");
	                                        
	                                        out.println("<tr>");
	                                        out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
	                                        out.println("</td>");  
	                                        out.println("<td class=\"ECampo\">");
	                                        out.println("<select name=\"iCveEstadoD\">");
	                                        out.println("<option value=\"valoren\">Seleccione...</option>");
	                                        out.println("</select>");
	                                        out.println("</td>");
	                                        out.println("</tr>");
	                                        
	                                        out.println("<tr>");
	                                        out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
	                                        out.println("<td class='ECampo'>");
	                                        out.println("<SELECT NAME='iCveMunicipio' SIZE='1' ");
	                                        out.println("<option value=\"0\">Seleccione...</option>");
	                                        out.println("</SELECT>");
	                                        out.println("</td>");
	                                        out.println("</tr>");
	                                        }
	                                        
	                                        if (request.getParameter("iCvePaisDR")!=null && valoren.equalsIgnoreCase(iCveEstadoDR.trim())){
	                                        
	                                        out.println("<tr>");
	                                        out.println(vEti.Texto("EEtiqueta","Pa&iacute;s:"));
	                                        out.println("<td>");
	                                        out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro2(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisDR, true));
	                                        out.println("</td>");
	                                        out.println("</td>");                                   
	                                        out.println("</tr>");
	                                        
	                                        out.println("<tr>");
	                                        out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
	                                        out.println("</td>");  
	                                        out.println("<td class='ECampo'>");
	                                        out.println("<select name=\"iCveEstadoD\" onChange=\"enviar_parametro2(this.value);\">");
	                                        
	                                        // Estado
	                                       TVEntidadFed vEntidadFed = new TVEntidadFed();
	                                       TDEntidadFed dEntidadFed = new TDEntidadFed();
	                                       Vector vcEntidadFed = new Vector();
	                                       vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString());
	                                           if (vcEntidadFed.size() > 0){
	                                           out.println("<option value=\"0\">Seleccione...</option>");
	                                           for (int i = 0; i < vcEntidadFed.size(); i++){
	                                               int j = 0;
	                                               j = j + 1;
	                                              vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
	                                              if(vEntidadFed.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
	                                                 out.println("");}
	                                             else{
	                                                 out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
	                                           }}

	                                           }else{
	                                           out.println("<option value=\"0\">Seleccione...</option>");
	                                           }
	                                       out.println("</SELECT>");
	                                       out.println("</td>");
	                                       out.println("</tr>");
	                                        
	                                        out.println("<tr>");
	                                        out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
	                                        out.println("<td class='ECampo'>");
	                                        out.println("<SELECT NAME='iCveMunicipio' SIZE='1' ");
	                                        out.println("<option value=\"0\">Seleccione...</option>");
	                                        out.println("</SELECT>");
	                                        out.println("</td>");
	                                        out.println("</tr>");
	                                        }
	                                        
	       
	                                       if(request.getParameter("iCvePaisDR")!=null && iCveEstadoDR != null){
	                                            if(!(valoren.equalsIgnoreCase(iCveEstadoDR.trim()))){
	                                       
	                                        out.println("<tr>");
	                                        out.println(vEti.Texto("EEtiqueta","Pa&iacute;s:"));
	                                        out.println("<td>");
	                                        out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro2(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisDR, true));
	                                        out.println("</td>");
	                                        out.println("</td>");                                
	                                        out.println("</tr>");
	                                        
	                                        out.println("<tr>");
	                                        out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
	                                        out.println("</td>");  
	                                        out.println("<td class='ECampo'>");
	                                        out.println("<select name=\"iCveEstadoD\" onChange=\"enviar_parametro2(this.value);\">");
	                                        
	                                        // Estado
	                                       TVEntidadFed vEntidadFed = new TVEntidadFed();
	                                       TDEntidadFed dEntidadFed = new TDEntidadFed();
	                                       Vector vcEntidadFed = new Vector();
	                                       vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString()+ " AND iCveEntidadFed = " + request.getParameter("iCveEstadoDR").toString());
	                                           if (vcEntidadFed.size() > 0){
	                                           for (int i = 0; i < vcEntidadFed.size(); i++){
	                                               int j = 0;
	                                               j = j + 1;
	                                              vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
	                                            if(vEntidadFed.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
	                                                 out.println("");}
	                                             else{
	                                                 out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
	                                           }}
	                                         }
	                                       TVEntidadFed vEntidadFed2 = new TVEntidadFed();
	                                       TDEntidadFed dEntidadFed2 = new TDEntidadFed();
	                                       Vector vcEntidadFed2 = new Vector();
	                                       vcEntidadFed2 = dEntidadFed2.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString());
	                                           if (vcEntidadFed2.size() > 0){
	                                           for (int i = 0; i < vcEntidadFed2.size(); i++){
	                                               int j = 0;
	                                               j = j + 1;
	                                              vEntidadFed2 = (TVEntidadFed) vcEntidadFed2.get(i);
		                                            if(vEntidadFed2.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
		                                                 out.println("");}
		                                             else{
		                                                 out.println("<option value=\""+vEntidadFed2.getICveEntidadFed()+"\">"+vEntidadFed2.getCNombre()+"</option>");
		                                           		}
	                                            }
	                                           }else{
	                                           		out.println("<option value=\"0\">Seleccione...</option>");
	                                           }
	                                       out.println("</SELECT>");
	                                       out.println("</td>");
	                                       out.println("</tr>");
	                                       
	                                       out.println("<tr>");
	                                       out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
	                                       out.println("<td class='ECampo'>");
	                                       out.println("<SELECT id='iCveMunicipio' NAME='iCveMunicipio' SIZE='1' ");
	                                       TVMunicipio vMunicipio= new TVMunicipio();
	                                           TDMunicipio dMunicipio = new TDMunicipio();
	                                           Vector vcMunicipio= new Vector();
	                                           vcMunicipio = dMunicipio.FindByAll(request.getParameter("iCvePaisDR").toString(),request.getParameter("iCveEstadoDR").toString());
	                                           if (vcMunicipio.size() > 0){
	                                           for (int i = 0; i < vcMunicipio.size(); i++){
	                                              int j = 0;
	                                              j = j + 1;
	                                              vMunicipio = (TVMunicipio) vcMunicipio.get(i);
	                                                out.println("<option value="+ vMunicipio.getICveMunicipio()+">"+vMunicipio.getCNombre()+"</option>");
	                                               }
	                                            }else{
	                                       			out.println("<option value=\"0\">Seleccione...</option>");
	                                       		}
	                                       out.println("</SELECT>");
	                                       out.println("</td>");
	                                       out.println("</tr>");
		                                      }
	                                         }
	                                       
	                                        out.println("<tr>");
	                                        out.print(vSCampo.EtiCampo("EEtiqueta", "Calle:","ECampo", "text", 50, 50, "ccalle", "","", "fMayus(this);", true, true, true, request));
	                                        out.println("</tr>");

	                                        out.println("<tr>");
	                                        out.print(vSCampo.EtiCampo("EEtiqueta", "Colonia:","ECampo", "text", 30, 30, "ccolonia", "","", "fMayus(this);", true, true, true, request));
	                                        out.println("</tr>");

	                                        out.println("<tr>");
	                                        out.print(vSCampo.EtiCampo("EEtiqueta", "Ciudad:","ECampo", "text", 50, 100, "cciudad", "","", "fMayus(this);", true, true, true, request));
	                                        out.println("</tr>");

	                                        out.println("<tr>");
	                                        out.print(vSCampo.EtiCampo("EEtiqueta","C.P.:","ECampo","text",10,10,"icp", "","","fMayus(this);",false,true,true, request));
	                                        out.println("</tr>");
	                                        
	                                       
	                                       
	                                       
											out.println("<tr>");
											out.print(vSCampo.EtiCampo("EEtiqueta","Tel&eacute;fono:","ECampo","text",20,20,"ctel", "","","fMayus(this);",false,true,true, request));
											out.println("</tr>");
											
	                                        out.println("<tr>");
											out.print(vSCampo.EtiCampo("EEtiqueta","Fax:","ECampo","text",20,20,"cfax", "","","fMayus(this);",false,true,true, request));
											out.println("</tr>");
											
											out.println("<tr>");
											out.print(vSCampo.EtiCampo("EEtiqueta","Correo electr&oacute;nico:","ECampo","text",50,50,"ccorreoelec", "","","fMayus(this);",false,true,true, request));
											out.println("</tr>");
											
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta","Activo:"));
											String cDisable = "";
		                                    String cChecked = "";
		                                       cDisable = "enabled ";
		                                        cChecked = "checked";
		                                    out.println("<td>");
		                                    out.println("<input type=\"checkbox\" name=\"lactivo\" value=\"1\" " + cDisable + " " +  cChecked+ ">");
		                                    out.println("</td>");
		                                    out.println("</tr>");

                                    	}
                                    }     
                                    }     
                                    
                                  
                                    out.print("<tr><td colspan=\"4\" align=\"center\">");

                                    if ( (sAccion == null) || sAccion.equals("G") || sAccion.equals("GA")){
                                      out.print(vEti.clsAnclaTexto("EEtiqueta","Editar","javascript:fModif("+request.getParameter("hdiCveEmpresa")+",'E');","Editarr") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                    }else{ 
                                    	if ( sAccion.equals("E") ){
                                      		out.print(vEti.clsAnclaTexto("EEtiqueta","Guardar","javascript:fModif("+request.getParameter("hdiCveEmpresa")+",'G');","Guardarr") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                      }
                                    	if( sAccion.equals("ND") ){
                                    		out.print(vEti.clsAnclaTexto("EEtiqueta","Guardar","javascript:fModif("+request.getParameter("hdiCveEmpresa")+",'GA');","Guardarr") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                    	}
                                    }
                                    out.print(vEti.clsAnclaTexto("EEtiqueta","Cancelar","javascript:window.close();","Cancelarr"));
                                    out.print("</td>");
                                    out.print("</tr>");
                                    out.print("<tr><td colspan=\"4\" align=\"center\">");
                                    if ( (sAccion == null) || sAccion.equals("G") || sAccion.equals("GA")){
                                      out.print(vEti.clsAnclaTexto("EEtiqueta","Agregar nueva dirección","javascript:fModif("+request.getParameter("hdiCveEmpresa")+",'ND');","NuevaDir") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                      }
                                    out.print("</td>");
                                    out.print("</tr>");
                                    out.print("<tr><td colspan=\"4\" align=\"center\">");
                                    out.print(vEti.clsAnclaTexto("EEtiqueta","Identificar","javascript:fIdentificar2("+ request.getParameter("hdiCveEmpresa") +",'"+bs.getFieldValue("sDscEmpresa","&nbsp;").toString()+"');","Identificar2"));
                                   }else{
                                	   
                                	   %> <tr>
                             				<td colspan="4" class="ETablaT">Agregar Dirección</td>
                            			</tr>
                           	  		<%
											///agregar nueva direccion
                                       java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd,MM,yyyy");
                                       String cDate=sdf.format(new java.util.Date()); 

                                       TDPERDatos dPerDatos = new TDPERDatos();
                                       Vector vcPersonal = new Vector();
                                       try{
                                         if(request.getParameter("hdiCveEmpresa") != null){
                                           vcPersonal = dPerDatos.DetalleEmpresa(Integer.parseInt(request.getParameter("hdiCveEmpresa")));
                                         }
                                       }catch(Exception e){
                                           vcPersonal = new Vector();
                                           e.printStackTrace();
                                       }
                                         TVPEREmpresa vEmpresa;
                                         if(vcPersonal.size() > 0){
                                         %>
                                          <SCRIPT LANGUAGE="JavaScript">
                                             window.resizeTo(700,500);
                                          </SCRIPT><%
                                          
                                          for(int a=0;a<vcPersonal.size();a++){
                                        	  vEmpresa = (TVPEREmpresa) vcPersonal.get(a);
		                                       out.println("<tr>");
		                                       out.print(vEti.EtiCampo("EEtiqueta","Empresa:","ECampo","text",10,10,"sDscEmpresa", vEmpresa.getSDscEmpresa() ,0,"","fMayus(this);",false,true,false));
		                                       out.println("</tr>");
		                                       
		                                       out.println("<tr>");
		                                        //out.print(vSCampo.EtiCampo("EEtiqueta", "Clave Domicilio:","ECampo", "text", 50, 95, "icvedomicilio", bs.getFieldValue("icvedomicilio","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
		                                        out.print(vEti.EtiCampo("EEtiqueta","Clave Domicilio:","ECampo","text",10,10,"icvedomicilio", "1",0,"","fMayus(this);",false,true,false));
		                                        out.println(" <input type='hidden' name='icvedomicilio' value='1'>");
		                                        out.println("</tr>");


		                                        out.println("<tr>");
		                                        if (request.getParameter("iCvePaisDR")==null && request.getParameter("iCveEstadoDR")==null ){
		                                        out.println("<tr>");
		                                        out.println(vEti.Texto("EEtiqueta","Pa&iacute;s:"));
		                                        out.println("<td>");
		                                        out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, "0", true));
		                                        out.println("</td>");
		                                        out.println("</tr>");
		                                        
		                                        out.println("<tr>");
		                                        out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
		                                        out.println("</td>");  
		                                        out.println("<td class=\"ECampo\">");
		                                        out.println("<select name=\"iCveEstadoD\">");
		                                        out.println("<option value=\"valoren\">Seleccione...</option>");
		                                        out.println("</select>");
		                                        out.println("</td>");
		                                        out.println("</tr>");
		                                        
		                                        out.println("<tr>");
		                                        out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
		                                        out.println("<td class='ECampo'>");
		                                        out.println("<SELECT NAME='iCveMunicipio' SIZE='1' ");
		                                        out.println("<option value=\"0\">Seleccione...</option>");
		                                        out.println("</SELECT>");
		                                        out.println("</td>");
		                                        out.println("</tr>");
		                                        }
		                                        
		                                        if (request.getParameter("iCvePaisDR")!=null && valoren.equalsIgnoreCase(iCveEstadoDR.trim())){
		                                        
		                                        out.println("<tr>");
		                                        out.println(vEti.Texto("EEtiqueta","Pa&iacute;s:"));
		                                        out.println("<td>");
		                                        out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisDR, true));
		                                        out.println("</td>");
		                                        out.println("</td>");                                   
		                                        out.println("</tr>");
		                                        
		                                        out.println("<tr>");
		                                        out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
		                                        out.println("</td>");  
		                                        out.println("<td class='ECampo'>");
		                                        out.println("<select name=\"iCveEstadoD\" onChange=\"enviar_parametro(this.value);\">");
		                                        
		                                        // Estado
		                                       TVEntidadFed vEntidadFed = new TVEntidadFed();
		                                       TDEntidadFed dEntidadFed = new TDEntidadFed();
		                                       Vector vcEntidadFed = new Vector();
		                                       vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString());
		                                           if (vcEntidadFed.size() > 0){
		                                           out.println("<option value=\"0\">Seleccione...</option>");
		                                           for (int i = 0; i < vcEntidadFed.size(); i++){
		                                               int j = 0;
		                                               j = j + 1;
		                                              vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
		                                              if(vEntidadFed.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
		                                                 out.println("");}
		                                             else{
		                                                 out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
		                                           }}

		                                           }else{
		                                           out.println("<option value=\"0\">Seleccione...</option>");
		                                           }
		                                       out.println("</SELECT>");
		                                       out.println("</td>");
		                                       out.println("</tr>");
		                                        
		                                        out.println("<tr>");
		                                        out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
		                                        out.println("<td class='ECampo'>");
		                                        out.println("<SELECT NAME='iCveMunicipio' SIZE='1' ");
		                                        out.println("<option value=\"0\">Seleccione...</option>");
		                                        out.println("</SELECT>");
		                                        out.println("</td>");
		                                        out.println("</tr>");
		                                        }
		                                        
		       
		                                       if(request.getParameter("iCvePaisDR")!=null && iCveEstadoDR != null){
		                                            if(!(valoren.equalsIgnoreCase(iCveEstadoDR.trim()))){
		                                       
		                                        out.println("<tr>");
		                                        out.println(vEti.Texto("EEtiqueta","Pa&iacute;s:"));
		                                        out.println("<td>");
		                                        out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisDR, true));
		                                        out.println("</td>");
		                                        out.println("</td>");                                
		                                        out.println("</tr>");
		                                        
		                                        out.println("<tr>");
		                                        out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
		                                        out.println("</td>");  
		                                        out.println("<td class='ECampo'>");
		                                        out.println("<select name=\"iCveEstadoD\" onChange=\"enviar_parametro(this.value);\">");
		                                        
		                                        // Estado
		                                       TVEntidadFed vEntidadFed = new TVEntidadFed();
		                                       TDEntidadFed dEntidadFed = new TDEntidadFed();
		                                       Vector vcEntidadFed = new Vector();
		                                       vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString()+ " AND iCveEntidadFed = " + request.getParameter("iCveEstadoDR").toString());
		                                           if (vcEntidadFed.size() > 0){
		                                           for (int i = 0; i < vcEntidadFed.size(); i++){
		                                               int j = 0;
		                                               j = j + 1;
		                                              vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
		                                            if(vEntidadFed.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
		                                                 out.println("");}
		                                             else{
		                                                 out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
		                                           }}
		                                         }
		                                       TVEntidadFed vEntidadFed2 = new TVEntidadFed();
		                                       TDEntidadFed dEntidadFed2 = new TDEntidadFed();
		                                       Vector vcEntidadFed2 = new Vector();
		                                       vcEntidadFed2 = dEntidadFed2.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString());
		                                           if (vcEntidadFed2.size() > 0){
		                                           for (int i = 0; i < vcEntidadFed2.size(); i++){
		                                               int j = 0;
		                                               j = j + 1;
		                                              vEntidadFed2 = (TVEntidadFed) vcEntidadFed2.get(i);
			                                            if(vEntidadFed2.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
			                                                 out.println("");}
			                                             else{
			                                                 out.println("<option value=\""+vEntidadFed2.getICveEntidadFed()+"\">"+vEntidadFed2.getCNombre()+"</option>");
			                                           		}
		                                            }
		                                           }else{
		                                           		out.println("<option value=\"0\">Seleccione...</option>");
		                                           }
		                                       out.println("</SELECT>");
		                                       out.println("</td>");
		                                       out.println("</tr>");
		                                       
		                                       out.println("<tr>");
		                                       out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
		                                       out.println("<td class='ECampo'>");
		                                       out.println("<SELECT id='iCveMunicipio' NAME='iCveMunicipio' SIZE='1' ");
		                                       TVMunicipio vMunicipio= new TVMunicipio();
		                                           TDMunicipio dMunicipio = new TDMunicipio();
		                                           Vector vcMunicipio= new Vector();
		                                           vcMunicipio = dMunicipio.FindByAll(request.getParameter("iCvePaisDR").toString(),request.getParameter("iCveEstadoDR").toString());
		                                           if (vcMunicipio.size() > 0){
		                                           for (int i = 0; i < vcMunicipio.size(); i++){
		                                              int j = 0;
		                                              j = j + 1;
		                                              vMunicipio = (TVMunicipio) vcMunicipio.get(i);
		                                                out.println("<option value="+ vMunicipio.getICveMunicipio()+">"+vMunicipio.getCNombre()+"</option>");
		                                               }
		                                            }else{
		                                       			out.println("<option value=\"0\">Seleccione...</option>");
		                                       		}
		                                       out.println("</SELECT>");
		                                       out.println("</td>");
		                                       out.println("</tr>");
			                                      }
		                                         }
		                                       
		                                        out.println("<tr>");
		                                        out.print(vSCampo.EtiCampo("EEtiqueta", "Calle:","ECampo", "text", 50, 50, "ccalle", "","", "fMayus(this);", true, true, true, request));
		                                        out.println("</tr>");

		                                        out.println("<tr>");
		                                        out.print(vSCampo.EtiCampo("EEtiqueta", "Colonia:","ECampo", "text", 30, 30, "ccolonia", "","", "fMayus(this);", true, true, true, request));
		                                        out.println("</tr>");

		                                        out.println("<tr>");
		                                        out.print(vSCampo.EtiCampo("EEtiqueta", "Ciudad:","ECampo", "text", 50, 100, "cciudad", "","", "fMayus(this);", true, true, true, request));
		                                        out.println("</tr>");

		                                        out.println("<tr>");
		                                        out.print(vSCampo.EtiCampo("EEtiqueta","C.P.:","ECampo","text",10,10,"icp", "","","fMayus(this);",false,true,true, request));
		                                        out.println("</tr>");
		                                        
		                                       
		                                       
		                                       
												out.println("<tr>");
												out.print(vSCampo.EtiCampo("EEtiqueta","Tel&eacute;fono:","ECampo","text",20,20,"ctel", "","","fMayus(this);",false,true,true, request));
												out.println("</tr>");
												
		                                        out.println("<tr>");
												out.print(vSCampo.EtiCampo("EEtiqueta","Fax:","ECampo","text",20,20,"cfax", "","","fMayus(this);",false,true,true, request));
												out.println("</tr>");
												
												out.println("<tr>");
												out.print(vSCampo.EtiCampo("EEtiqueta","Correo electr&oacute;nico:","ECampo","text",50,50,"ccorreoelec", "","","fMayus(this);",false,true,true, request));
												out.println("</tr>");
												
												out.println("<tr>");
												out.println(vEti.Texto("EEtiqueta","Activo:"));
												String cDisable = "";
			                                    String cChecked = "";
			                                       cDisable = "enabled ";
			                                        cChecked = "checked";
			                                    out.println("<td>");
			                                    out.println("<input type=\"checkbox\" name=\"lactivo\" value=\"1\" " + cDisable + " " +  cChecked+ ">");
			                                    out.println("</td>");
			                                    out.println("</tr>");
			                                    
			                                    out.print("<tr><td colspan=\"4\" align=\"center\">");		                                       
			                                    out.print(vEti.clsAnclaTexto("EEtiqueta","Guardar","javascript:fModif("+request.getParameter("hdiCveEmpresa")+",'GA');","Guardarr") + "&nbsp;&nbsp;&nbsp;&nbsp;");
			                                    out.print(vEti.clsAnclaTexto("EEtiqueta","Cancelar","javascript:window.close();","Cancelarr"));
			                                    out.print("</td>");
			                                    out.print("</tr>");

                                          }
                                         }
                                   }
                            %>
                             </td>
                           </tr>
                          </table><% // Fin de Datos %>

  </td></tr>
  <%}else{%>
      <script language="JavaScript">
          fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');
      </script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
