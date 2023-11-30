<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<html>
<%
  pg071001031CFG  clsConfig = new pg071001031CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg071001031.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071001031.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave1 = "", cClave2 = "", cClave3 = "", cClave4 = "", cClave5 = "", cClave6 = "";
  String cPosicion  = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "3";                   // modificar
  String cDscOrdenar  = "No Disponible|";    // modificar
  String cCveOrdenar  = "No Disponible|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "No Disponible|";  // modificar
  String cTipoFiltrar = "8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  TEtiCampo vEti = new TEtiCampo();
  
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPerM.js"%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\pg071001031.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\SelPerM.js"></SCRIPT>-->
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg071001031.js)
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
   <!-- Estilos de Extjs-Base -->
<link rel="stylesheet" type="text/css" href="/medprev/extjs/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="/medprev/extjs/resources/css/Ingresos.css" >
<!-- Librerias de Extjs-Base -->
<script type="text/javascript" src="/medprev/extjs/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/medprev/extjs/ext-all.js"></script>
  <!-- Librerias de Dwr-Base -->
<script type='text/javascript' src='/medprev/dwr/engine.js'></script>
<script type='text/javascript' src='/medprev/dwr/util.js'></script>
<script type="text/javascript" src="/medprev/extjs/examples/ux/SearchField.js"></script>
<script type="text/javascript" src="/medprev/extjs/examples/ux/PagingMemoryProxy.js"></script>
<!-- Objetos de Dwr de la Aplicación -->
<script type='text/javascript' src='/medprev/dwr/interface/MedPredDwr.js'></script>
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
       cClave1  = ""+bs.getFieldValue("iCveUsuario", "");
       cClave2  = ""+bs.getFieldValue("iCveUniMed", "");
       cClave3  = ""+bs.getFieldValue("iCveProceso", "");
       cClave4  = ""+bs.getFieldValue("iCveModulo", "");
       cClave5  = ""+bs.getFieldValue("iCveServicio", "");
       cClave6  = ""+bs.getFieldValue("iCveRama", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave1%>">
  <input type="hidden" name="hdiCveUniMed" value="<%=cClave2%>">
  <input type="hidden" name="hdiCveProceso" value="<%=cClave3%>">
  <input type="hidden" name="hdiCveModulo" value="<%=cClave4%>">
  <input type="hidden" name="hdiCveServicio" value="<%=cClave5%>">
  <input type="hidden" name="hdiCveRama" value="<%=cClave6%>">
  <input type="hidden" name="hdiCveUsuario" value ="<%=request.getParameter("hdiCveUsuario")%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <%  out.println("<tr>");
                                    out.println("<td colspan='4' align='center'>");
                                    out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar Medico","javascript:fSelPerM();","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    String where="";
                                   if (request.getParameter("hdiCveUsuario") != null && request.getParameter("hdiCveUsuario").compareTo("") != 0  ) {      
                                        where =  " where  segusuario.icveusuario = "+ request.getParameter("hdiCveUsuario") +" ";
                                    }else{
                                        where =  " and segusuario.icveusuario = 0 ";
                                    }
%>
                              <tr><% 
                                   //TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                              <td colspan="2" class="ETablaT">Usuarios, Unidades Médicas y Procesos</td>
                            </tr>
                            <% if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Usuario:"));
                                 
                                 //out.print(vEti.SelectOneRow("ECampo","iCveUsuario","",new TDSEGUsuario().FindByAll(""),"iCveUsuario","cNomCompleto",request,""));
                                 out.print(vEti.SelectOneRow("ECampo","iCveUsuario","",new TDSEGUsuario().FindByAll(where),"iCveUsuario","cNomCompleto",request,""));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 Vector vcGRLUniMed = (Vector) AppCacheManager.getColl("GRLUniMed","");
                                 out.print(vEti.Texto("EEtiqueta", "Unidad Médica:"));
                                 out.print(vEti.SelectOneRow("ECampo","iCveUniMed","fChg();",vcGRLUniMed,"iCveUniMed","cDscUniMed",request,""));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 String cUniMed = "";
                                 try{
                                    if(request.getParameter("iCveUniMed")==null)
                                      cUniMed = ""+((TVUniMed)vcGRLUniMed.get(0)).getICveUniMed();
                                    else
                                      cUniMed = ""+request.getParameter("iCveUniMed");
                                 }catch(Exception e){
                                    e.printStackTrace();
                                    cUniMed = "";
                                 }
                                 out.print(vEti.Texto("EEtiqueta", "Proceso:"));
                                 out.print(vEti.SelectOneRow("ECampo","iCveProceso","fChg();",(Vector) AppCacheManager.getColl("GRLProceso",cUniMed+"|"),"iCveProceso","cDscProceso",request,""));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Módulo:"));
                                 out.print(vEti.SelectOneRow("ECampo","iCveModulo","fChg();",(Vector) AppCacheManager.getColl("GRLModulo",cUniMed+"|"),"iCveModulo","cDscModulo",request,""));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 Vector vcMEDServicio = new DAOMEDServicio().FindByAll();
                                 TDGRLUSUMedicos tdUsuMed = new TDGRLUSUMedicos();
                                 /*String wherePriv = " where icveusuario=" + request.getParameter("hdiCveUsuario") 
                                 					+ " and icveunimed=" + request.getParameter("hdiCveUniMed")
                                 					+ " and icveproceso=" + request.getParameter("hdiCveProceso")
                                 					+ " and icvemodulo=" + request.getParameter("hdiCveModulo");
                                 Vector privilegios = tdUsuMed.FindByAllSimple(wherePriv);*/
                                 
                               //inicio mod
                                 out.println("<table border='1' class='ETablaInfo' align='center' cellspacing='0' cellpadding='2'>");
                                 out.println("<tr><tr class='EEtiquetaL'><td>Servicios:</td><td><input type='checkbox' name='SeleccionAll' value='1' onclick='habilitaChecks()'></td></tr>");
                                 
                                 //out.println("<tr class='EEtiquetaR'><td>Selecciona todo:</td></tr>");
                                 for (int i = 0; i < vcMEDServicio.size(); i++) {
                                	 TVMEDServicio serv = (TVMEDServicio)vcMEDServicio.get(i);
                                	 Vector vc = (Vector) AppCacheManager.getColl("MEDRama",serv.getICveServicio()+"|");
                                	 for (int j = 0; j < vc.size(); j++) {
                                		 if (j==0) {
                                        	 out.println("<tr><tr class='ETablaT'><td colspan='2'>" + serv.getCDscServicio() + "</td></tr>");
                                        	 out.println("<tr><tr class='ETablaST'><td colspan='2'>Ramas</td></tr>");	 
                                		 }
                                		 TVMEDRama rama = (TVMEDRama)vc.get(j);
                                		 out.println("<tr class='ETabla'><td>" + rama.getCDscRama() + "</td>");
                                		 out.println("<td><input type='checkbox' name='cb|"+ serv.getICveServicio() + "|"+ rama.getICveRama() +"' value='1'></td></tr>");
                                	 }
                                 }
                                 out.println("</table>");
                                 /* out.print(vEti.Texto("EEtiqueta", "Servicio:"));
                                 out.print(vEti.SelectOneRow("ECampo","iCveServicio","fChg();",vcMEDServicio,"iCveServicio","cDscServicio",request,""));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 String cServicio = "";
                                 try{
                                    if(request.getParameter("iCveServicio")==null)
                                      cServicio = ""+((TVMEDServicio)vcMEDServicio.get(0)).getICveServicio();
                                    else
                                      cServicio = ""+request.getParameter("iCveServicio");
                                 }catch(Exception e){
                                    e.printStackTrace();
                                    cServicio = "";
                                 }
                                 out.print(vEti.Texto("EEtiqueta", "Rama:"));
                                 out.print(vEti.SelectOneRow("ECampo","iCveRama","",(Vector) AppCacheManager.getColl("MEDRama",cServicio+"|"),"iCveRama","cDscRama",request,""));*/
                                 out.println("</tr>"); 
                               }
                               else{
                                 if (bs != null){
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Usuario:", "ECampo", "text", 4, 4, "", ""+bs.getFieldValue("iCveUsuario", "&nbsp;")+" - "+bs.getFieldValue("cNombreCompleto", "&nbsp;"), 3, "", "", false, true, false));
                                   out.println("</tr><tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Unidad Médica:", "ECampo", "text", 4, 4, "", ""+bs.getFieldValue("cDscUniMed", "&nbsp;"), 3, "", "", false, true, false));
                                   out.println("</tr><tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Proceso:", "ECampo", "text", 4, 4, "", ""+bs.getFieldValue("cDscProceso", "&nbsp;"), 3, "", "", false, true, false));
                                   out.println("</tr>");
                                   out.println("</tr><tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Modulo:", "ECampo", "text", 4, 4, "", ""+bs.getFieldValue("cDscModulo", "&nbsp;"), 3, "", "", false, true, false));
                                   out.println("</tr>");
                                   out.println("</tr><tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Servicio:", "ECampo", "text", 4, 4, "", ""+bs.getFieldValue("cDscServicio", "&nbsp;"), 3, "", "", false, true, false));
                                   out.println("</tr>");
                                   out.println("</tr><tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Rama:", "ECampo", "text", 4, 4, "", ""+bs.getFieldValue("cDscRama", "&nbsp;"), 3, "", "", false, true, false));
                                   out.println("</tr>");
                                 }
                                 else{
                                   out.print(vEti.Texto("ECampo", ""));
                                 }
                               }
                            %>
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
<script language="JavaScript">
function habilitaChecks() {
	var forma = document.forms[0];
	<%
	Vector vcMEDServicio = new DAOMEDServicio().FindByAll();
	TDGRLUSUMedicos tdUsuMed = new TDGRLUSUMedicos();
	%>
	if (forma.elements['SeleccionAll'].checked == true) {
		<%
		for (int i = 0; i < vcMEDServicio.size(); i++) {
			 TVMEDServicio serv = (TVMEDServicio)vcMEDServicio.get(i);
			 Vector vc = (Vector) AppCacheManager.getColl("MEDRama",serv.getICveServicio()+"|");
			 for (int j = 0; j < vc.size(); j++) {
				 TVMEDRama rama = (TVMEDRama)vc.get(j);
				 out.println("forma.elements['cb|"+ serv.getICveServicio() + "|"+ rama.getICveRama() +"'].checked = true;");
			 }
		}
		%>
	} else {
		<%
		for (int i = 0; i < vcMEDServicio.size(); i++) {
			 TVMEDServicio serv = (TVMEDServicio)vcMEDServicio.get(i);
			 Vector vc = (Vector) AppCacheManager.getColl("MEDRama",serv.getICveServicio()+"|");
			 for (int j = 0; j < vc.size(); j++) {
				 TVMEDRama rama = (TVMEDRama)vc.get(j);
				 out.println("forma.elements['cb|"+ serv.getICveServicio() + "|"+ rama.getICveRama() +"'].checked = false;");
			 }
		}
		%>
	}
}

</script>
<script language="JavaScript">
  function cargaDatosUser(){
	  var forma = document.forms[0];
	  //alert(forma.iCveModulo.value);
	  var permisoStore = new Ext.data.Store({
				proxy : new Ext.ux.data.PagingMemoryProxy(),
				reader : new Ext.data.ArrayReader( {}, [
                   {name : 'iCveUsuario', mapping : 'iCveUsuario'}, 
 				   {name : 'iCveUniMed', mapping : 'iCveUniMed'}, 
 				   {name : 'iCveProceso', mapping : 'iCveProceso'},
 				   {name : 'iCveModulo', mapping : 'iCveModulo'},
 				   {name : 'iCveServicio', mapping : 'iCveServicio'},
 				   {name : 'iCveRama', mapping : 'iCveRama'}
 				   ])
			});
	  
	  MedPredDwr.findServiciosUsuario(forma.iCveUsuario.value,forma.iCveUniMed.value,forma.iCveProceso.value,forma.iCveModulo.value,{
          callback : function (data){
        	  if (data.success) {
        		  permisoStore.loadData(data.data);
        		  
        	  }
        	  //alert("Regreso de java contador: " + permisoStore.getTotalCount());
              for(var x=0;x<permisoStore.getTotalCount();x++){
            	  //alert('cb|' + permisoStore.getAt(x).get('iCveServicio') + '|' + permisoStore.getAt(x).get('iCveRama'));
            	  try {
            	  	//Run some code here
            		  forma.elements['cb|' + permisoStore.getAt(x).get('iCveServicio') + '|' + permisoStore.getAt(x).get('iCveRama')].checked = true;
            	  } catch(err) {
            	  //Handle errors here
            	  }
              }
              //alert(permisoStore.getAt(0).get('iCveServicio'));*/
          },
          //async : false,
          scope : this
      });
  	
  }
  cargaDatosUser();
</script>
</html>