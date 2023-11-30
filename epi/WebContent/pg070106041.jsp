
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
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html onMouseOver="mostrardiv();" >
<%
  pg070106041CFG  clsConfig = new pg070106041CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070106041.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070106041.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Expediente|";    // modificar
  String cCveOrdenar  = "iCveExpediente|";  // modificar
  String cDscFiltrar  = "Expediente|";    // modificar
  String cCveFiltrar  = "iCveExpediente|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus = "hidden";

  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();

  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

  /*
   * Calcula Fecha Actual
   */
  java.util.Date today = new java.util.Date();
  java.sql.Date defFecha = new java.sql.Date(today.getTime());
  java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
  String dFechaActual = "";
  dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha,"/");
  TDEMOExamen dEMOExamen = new TDEMOExamen();
  TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
  

  ///Datos para el examen
  String exp = "";
  String exa = "";
  int examen = 0;
  int serv = 76;
  int rama = 1;
  String valor=""; 
  TDPEREXAMENNoAp dEXAMENNoAp = new TDPEREXAMENNoAp();
  TDPERCatalogoNoAp dPERCatalogoNoAp = new TDPERCatalogoNoAp();
  if(request.getParameter("iCvePersonal") != null){
	  if(!request.getParameter("iCvePersonal").toString().equals("")){
		//System.out.println("Se ejecuta = "+request.getParameter("iCvePersonal"));
		examen = dPERCatalogoNoAp.ExamenMaximo(request.getParameter("iCvePersonal"));
  		exa = (examen + 1) +"";
  	}
  }
  
  
  ///////VALIDAR EL USUARIO PARA VER RESULTADOS TOXICOLOGICOS
  System.out.println("usuario = "+vUsuario.getICveusuario());
  System.out.println("propiedad = "+vParametros.getPropEspecifica("UsrAdminCatNoAptosTox"));
  String UsrAdminCatNoAptosTox = ""+vParametros.getPropEspecifica("UsrAdminCatNoAptosTox");
  String[] parts = UsrAdminCatNoAptosTox.split(",");
  System.out.println("tamaño="+parts.length);
  boolean acceso = false;
  //String part1 = parts[0]; // 004
  //String part2 = parts[1]; // 034556
  for(int u=0; parts.length > u;u++){
	  //System.out.println("Var-"+u+"-"+parts[u]);
	  if((vUsuario.getICveusuario()+"").equals(parts[u]+"")){
		  //System.out.println("Verdadero");  
		  acceso = true;
	  }
  }
  
%>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070106041.js)
  // Esta función no debe modificarse
  function fOnLoad(){ 
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
  
  function mostrardiv() {
      form = document.forms[0];
      //alert(form.toxpendiente.value );
      if (form.toxpendiente){
       if(form.toxpendiente.value == 1 || form.toxpendiente.value == 2){
          div = document.getElementById('alcolimetria');
          div.style.display = '';
          div = document.getElementById('alcolimetria2');
          div.style.display = '';
          div = document.getElementById('pbaorina');
          div.style.display = '';
          div = document.getElementById('pbaorina2');
          div.style.display = '';
          div = document.getElementById('numffccc');
          div.style.display = '';
          div = document.getElementById('numffccc2');
          div.style.display = '';
          div = document.getElementById('numenvio');
          div.style.display = '';
          div = document.getElementById('numenvio2');
          div.style.display = '';
  		}else{
	      div = document.getElementById('alcolimetria');
	      div.style.display='none';
	      div = document.getElementById('alcolimetria2');
	      div.style.display='none';
	      div = document.getElementById('pbaorina');
	      div.style.display='none';
	      div = document.getElementById('pbaorina2');
	      div.style.display='none';
	      div = document.getElementById('numffccc');
	      div.style.display='none';
	      div = document.getElementById('numffccc2');
	      div.style.display='none';
	      div = document.getElementById('numenvio');
	      div.style.display='none';
	      div = document.getElementById('numenvio2');
	      div.style.display='none';
		}
      }
      
      if (form.alcolimetria){
       if(form.alcolimetria.value == 1){
          div = document.getElementById('numpba');
          div.style.display = '';
          div = document.getElementById('numpba2');
          div.style.display = '';
          div = document.getElementById('numeqp');
          div.style.display = '';
          div = document.getElementById('numeqp2');
          div.style.display = '';
  		}else{
	      div = document.getElementById('numpba');
	      div.style.display='none';
	      div = document.getElementById('numpba2');
	      div.style.display='none';
	      div = document.getElementById('numeqp');
	      div.style.display='none';
	      div = document.getElementById('numeqp2');
	      div.style.display='none';
		}
      }
       
      if (form.pbaorina){
       if(form.pbaorina.value == 2){
           div = document.getElementById('rpositivo');
           div.style.display = '';
           div = document.getElementById('rpositivo2');
           div.style.display = '';
   		}else{
 	      div = document.getElementById('rpositivo');
 	      div.style.display='none';
 	      div = document.getElementById('rpositivo2');
 	      div.style.display='none';
 		}
      }
  }
  
  
  var newImagenServicioFile;
  function makenewImagenServicioFile() {
      if (!newImagenServicioFile || newImagenServicioFile.closed) {
          newImagenServicioFile = window.open("SubirArchivo.jsp?"
                +"iCveExpediente="+<%=request.getParameter("iCvePersonal")%>
                +"&iNumExamen="+<%=exa%>
                +"&iCveServicio="+<%=serv%>
                +"&iCveRama="+<%=rama%>
                +"&iCveUsuario="+<%=vUsuario.getICveusuario()%>, "", "height=200,width=650,scrollbars=0,menubar=0");
          newImagenServicioFile.focus( );
      } else if (newImagenServicioFile.focus) {
          newImagenServicioFile.focus( );
      }
  }
  
  var newPlacaToraxFile;
  function showPlacasToraxFiles() {
   if (!newPlacaToraxFile || newPlacaToraxFile.closed) {
        newPlacaToraxFile = window.open("./MostrarArchivos.jsp?"
              +"iCveExpediente="+<%=request.getParameter("iCvePersonal")%>
              +"&iNumExamen="+<%=exa%>
              +"&iCveServicio="+<%=serv%>
              +"&iCveRama="+<%=rama%>
              +"&iCveUsuario="+<%=vUsuario.getICveusuario()%>, "", "height=200,width=650,resizable=yes,menubar=0,scrollbars=yes");
        newPlacaToraxFile.focus( );
    } else if (newPlacaToraxFile.focus) {
        newPlacaToraxFile.focus( );
    }
}
  
  
  //var newImagenServicioFile;
  function makenewImagenServicioFile2(exa) {
      if (!newImagenServicioFile || newImagenServicioFile.closed) {
          newImagenServicioFile = window.open("SubirArchivo.jsp?"
                +"iCveExpediente="+<%=request.getParameter("iCvePersonal")%>
                +"&iNumExamen="+exa
                +"&iCveServicio="+<%=serv%>
                +"&iCveRama="+<%=rama%>
                +"&iCveUsuario="+<%=vUsuario.getICveusuario()%>, "", "height=200,width=650,scrollbars=0,menubar=0");
          newImagenServicioFile.focus( );
      } else if (newImagenServicioFile.focus) {
          newImagenServicioFile.focus( );
      }
  }
  
  //var newPlacaToraxFile;
  function showPlacasToraxFiles2(exa) {
   if (!newPlacaToraxFile || newPlacaToraxFile.closed) {
        newPlacaToraxFile = window.open("./MostrarArchivos.jsp?"
              +"iCveExpediente="+<%=request.getParameter("iCvePersonal")%>
              +"&iNumExamen="+exa
              +"&iCveServicio="+<%=serv%>
              +"&iCveRama="+<%=rama%>
              +"&iCveUsuario="+<%=vUsuario.getICveusuario()%>, "", "height=200,width=650,resizable=yes,menubar=0,scrollbars=yes");
        newPlacaToraxFile.focus( );
    } else if (newPlacaToraxFile.focus) {
        newPlacaToraxFile.focus( );
    }
}  
  
</script>
<head>
<meta name="Autor"
	content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet"
	href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>"
	TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>"
	topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
	<form method="<%= vEntorno.getMetodoForm() %>"
		action="<%= vEntorno.getActionForm() %>">
		<input type="hidden" name="hdCCondicion"
			value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
		<input type="hidden" name="hdCOrdenar"
			value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
		<%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave  = ""+bs.getFieldValue("iCveCatalogoNoAp", "");
     }
		
  %>
		<input type="hidden" name="hdRowNum" value="<%=cPosicion%>"> <input
			type="hidden" name="hdCampoClave"
			value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
		<input type="hidden" name="hdCampoClave1" value=""> <input
			type="hidden" name="hdCampoClave2" value=""> <input
			type="hidden" name="hdCampoClave3" value=""> <input
			type="hidden" name="hdCampoClave4" value=""> <input
			type="hidden" name="iCveCatalogoNoAp" value="<%=cClave%>"> <input
			type="hidden" name="iCvePersonal"
			value='<%out.print(request.getParameter("iCvePersonal"));%>'>
		<input type="hidden" name="lMostrarDatos"
			value='<%out.print(request.getParameter("lMostrarDatos"));%>'>
		<input type="hidden" name="lAgregar" value=''> <input
			type="hidden" name="lGuardar" value=''> <input type="hidden"
			name="lQuitar" value=''> <input type="hidden"
			name="hdiCveMdoTrans"
			value='<%= request.getParameter("hdiCveMdoTrans")    != null?request.getParameter("hdiCveMdoTrans")    :""%>'>
		<input type="hidden" name="hdiCveCategoria"
			value='<%= request.getParameter("hdiCveCategoria")   != null?request.getParameter("hdiCveCategoria")   :""%>'>
		<input type="hidden" name="hdiCveUniMed"
			value='<%= request.getParameter("hdiCveUniMed")      != null?request.getParameter("hdiCveUniMed")      :""%>'>
		<input type="hidden" name="hdiCveCatalogoNoAp"
			value='<%= request.getParameter("hdiCveCatalogoNoAp")!= null?request.getParameter("hdiCveCatalogoNoAp"):""%>'>

		<table
			background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg"
			width="100%" height="100%">
			<% if(clsConfig.getAccesoValido()){ %>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><input type="hidden" name="hdBoton" value=""></td>
				<td valign="top">
					<% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<% // Inicio de Datos %>
						<td colspan="2" class="ETablaT">Actualización de No Aptitud</td>
						<%

                               int iCveMdoTrans     = 0;
                               int iCveCategoria    = 0;
                               int iCveUniMed       = 0;
                               int iCveCatalogoNoAp = 0;
                               int iCvePersonal     = 0;
                               int iCveProceso      = Integer.parseInt(vParametros.getPropEspecifica("EPIProceso").toString());

                               out.print("<tr>");
                               out.print("<td class='ETablaC' colspan='2'>");
                               out.print(vEti.clsAnclaTexto("EAncla","Buscar Personal","JavaScript:fSelPer();", "Buscar Personal",""));
                               out.print("</td>");
                               out.print("</tr>");

                               if(bs!=null){
                                 iCvePersonal = Integer.parseInt(bs.getFieldValue("iCvePersonal", "&nbsp;").toString());
                                 out.println("<tr>");
                                 out.print("<td class='EEtiqueta'>Clave Personal:</td>");
                                 out.print("<td class='ETabla'>" + bs.getFieldValue("iCvePersonal", "&nbsp;") + "</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print("<td class='EEtiqueta'>Nombre:</td>");
                                 out.print("<td class='ETabla'>" + bs.getFieldValue("cNombre", "&nbsp;") + " " + bs.getFieldValue("cApPaterno", "&nbsp;") + " " + bs.getFieldValue("cApMaterno", "&nbsp;") + "</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print("<td class='EEtiqueta'>R.F.C.:</td>");
                                 out.print("<td class='ETabla'>" + bs.getFieldValue("cRFC", "&nbsp;") + "</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print("<td class='EEtiqueta'>C.U.R.P.:</td>");
                                 out.print("<td class='ETabla'>" + bs.getFieldValue("cCURP", "&nbsp;") + "</td>");
                                 out.println("</tr>");

                                 //Codigo para desplegar sus No Aptitudes.
                                 Vector vNoAptitudes = new Vector();
                                 TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
                                 Vector vcGRLUniMed = dGRLUniMed.FindByAll(" order by cDscUniMed ");
                                 Vector vcGRLUniMed2 = dGRLUniMed.FindByAll("join GRLUMUsuario on GRLUMUsuario.iCveUsuario = " + vUsuario.getICveusuario() +
                                                                            "                 and GRLUMUsuario.iCveUniMed  = GRLUniMed.iCveUniMed " +
                                                                            "                 and GRLUMUsuario.iCveProceso = " + iCveProceso,
                                                                            " order by cDscUniMed ");
                                 Vector vcGRLMdoTrans = new TDGRLMdoTrans().findByAll("");

                                 Vector vcGRLCategoria = new TDGRLCategoria().FindByAll("","");


                                 try{
                                    vNoAptitudes = new TDPERCatalogoNoAp().FindNoAptitudes(" where PERCatalogoNoAp.iCvePersonal = " + bs.getFieldValue("iCvePersonal", "&nbsp;").toString() +
                                                                                           "   and PERCatalogoNoAp.lVigente   = 1" ,"");
                                 } catch(Exception ex){
                                   ex.printStackTrace();
                                 }

                                 if(
                                     //(request.getParameter("lAgregar").compareTo("true")!=0 &&
                                     // request.getParameter("lQuitar").compareTo("true") !=0   )
                                     // ||
                                         request.getParameter("lAccion").compareToIgnoreCase("Motivos")==0
                                      || request.getParameter("lAccion").compareToIgnoreCase("AgregarMot")==0
                                      || request.getParameter("lAccion").compareToIgnoreCase("")==0
                                      || request.getParameter("lAccion").compareToIgnoreCase("GuardarNoAp")==0
                                      || request.getParameter("lAccion").compareToIgnoreCase("Quitar")==0 
                                	 || request.getParameter("lAccion").compareToIgnoreCase("ActualizaNoAp")==0 ){
                                    iCveMdoTrans     = 0;
                                    iCveCategoria    = 0;
                                    iCveUniMed       = 0;
                                    iCveCatalogoNoAp = 0;
                                 if (!vNoAptitudes.isEmpty()){
                                   for(int j=0;j<vcGRLUniMed.size();j++){
                                     TVGRLUniMed VGRLUniMed = new TVGRLUniMed();
                                     VGRLUniMed = (TVGRLUniMed) vcGRLUniMed.get(j);
                                     for(int jj=0;jj<vcGRLMdoTrans.size();jj++){
                                        TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
                                        VGRLMdoTrans = (TVGRLMdoTrans) vcGRLMdoTrans.get(jj);
                                        for(int jjj=0;jjj<vcGRLCategoria.size();jjj++){
                                           TVGRLCategoria VGRLCategoria = new TVGRLCategoria();
                                           VGRLCategoria = (TVGRLCategoria) vcGRLCategoria.get(jjj);


                                           if (VGRLCategoria.getICveMdoTrans() == VGRLMdoTrans.getICveMdoTrans()){
                                             int m = 0;
                                             for(int i=0;i<vNoAptitudes.size();i++){
                                                TVPERCatalogoNoAp VPERCatalogoNoAp = new TVPERCatalogoNoAp();
                                                VPERCatalogoNoAp = (TVPERCatalogoNoAp) vNoAptitudes.get(i);
                                                if (VPERCatalogoNoAp.getICveUniMed()    == VGRLUniMed.getICveUniMed()     &&
                                                    VPERCatalogoNoAp.getICveMdoTrans()  == VGRLMdoTrans.getICveMdoTrans() &&
                                                    VPERCatalogoNoAp.getICveCategoria() == VGRLCategoria.getICveCategoria()){
                                                  if (m == 0){
                                                    if ((((request.getParameter("hdiCveUniMed")    != null &&
                                                            request.getParameter("hdiCveMdoTrans")  != null &&
                                                            request.getParameter("hdiCveCategoria") != null   ) &&
                                                           (request.getParameter("hdiCveUniMed").toString().compareToIgnoreCase(Integer.toString(VGRLUniMed.getICveUniMed())) ==0 &&
                                                            request.getParameter("hdiCveMdoTrans").toString().compareToIgnoreCase(Integer.toString(VGRLMdoTrans.getICveMdoTrans())) ==0 &&
                                                            request.getParameter("hdiCveCategoria").toString().compareToIgnoreCase(Integer.toString(VGRLCategoria.getICveCategoria())) ==0
                                                           )) && (request.getParameter("lAccion").compareToIgnoreCase("Motivos")==0))
                                                          || (request.getParameter("lAccion").compareToIgnoreCase("")==0 ||
                                                              request.getParameter("lAccion").compareToIgnoreCase("GuardarNoAp")==0 ||
                                                              request.getParameter("lAccion").compareToIgnoreCase("AgregarMot")==0 ||
                                                            		  request.getParameter("lAccion").compareToIgnoreCase("ActualizaNoAp")==0 ||
                                                              request.getParameter("lAccion").compareToIgnoreCase("Quitar")==0)){
                                                      out.print("<tr>");
                                                      out.print("<td colspan=\"2\" class=\"ETablaT\"><br>----------------------    N&uacute;mero de Examen "
                                                      +VPERCatalogoNoAp.getINumExamen()+"    ----------------------<br>&nbsp;</td>");
                                                      out.print("</tr>");
                                                      out.print("<tr>");
                                                      out.print("<td colspan=\"2\" class=\"ETablaT\">Unidad, Modo y Categoría</td>");
                                                      out.print("</tr>");
                                                      out.print("<tr>");
                                                      out.print("<td class=\"EEtiqueta\">Unidad Médica</td>");
                                                      out.print("<td class=\"ETabla\">" + VPERCatalogoNoAp.getCDscUniMed() + "</td>");
                                                      out.print("</tr>");
                                                      out.print("<tr>");
                                                      out.print("<td class=\"EEtiqueta\">Modo de Transporte</td>");
                                                      out.print("<td class=\"ETabla\">" + VPERCatalogoNoAp.getCDscMdoTrans() + "</td>");
                                                      out.print("</tr>");
                                                      out.print("<tr>");
                                                      out.print("<td class=\"EEtiqueta\">Categoría-1</td>");
                                                      out.print("<td class=\"ETabla\">" + VPERCatalogoNoAp.getCDscCategoria() + "</td>");
                                                      out.print("</tr>");
                                                      out.print("<tr>");
                                                      out.print("<td class=\"EEtiqueta\">N&uacute;mero de Examen</td>");
                                                      out.print("<td class=\"ETabla\">" + VPERCatalogoNoAp.getINumExamen() + "</td>");
                                                      out.print("</tr>");                                                      

                                                      iCveUniMed    = VGRLUniMed.getICveUniMed();
                                                      iCveMdoTrans  = VGRLMdoTrans.getICveMdoTrans();
                                                      iCveCategoria = VGRLCategoria.getICveCategoria();
                                                      iCveCatalogoNoAp = VPERCatalogoNoAp.getICveCatalogoNoAp();

                                                      if (request.getParameter("lAccion").compareToIgnoreCase("Motivos")!=0){
                                                        out.print("<tr>");
                                                        out.print("<td class='ETablaC' colspan='2'>");
                                                        out.print(vEti.clsAnclaTexto("EAncla","Bajar del Catálogo de No Aptos",
                                                                       "JavaScript:fQuitar(" + iCveMdoTrans     + "," +
                                                                                               iCveCategoria    + "," +
                                                                                               iCveCatalogoNoAp +
                                                                       ");", "Quitar",""));
                                                        out.print("</td>");
                                                        out.print("</tr>");
                                                      }

                                                      exp = VPERCatalogoNoAp.getICvePersonal()+"";
                                                      exa = VPERCatalogoNoAp.getINumExamen()+"";
                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 3, 1);
                                                      out.print("<td class=\"EEtiqueta\">Observación de la No Aptitud</td>");
                                                      out.print("<td class=\"ETabla\">" + valor + "</td>");
                                                      out.print("</tr>");
                                                      
                                                      if(VPERCatalogoNoAp.getINumExamen()>0){
	                                                      
	                                                      String respuesta = "";
														  valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 9);
														  valor = ""+clsConfig.Dato(valor); 
														  System.out.println("valor = "+valor);
														  System.out.println("examen = "+examen);
														  System.out.println("VPERCatalogoNoAp.getINumExamen() = "+VPERCatalogoNoAp.getINumExamen());
	                                                      
														if(examen == VPERCatalogoNoAp.getINumExamen() && valor.equals("1")){
		                                                      out.print("<tr>");
		                                                      out.print("<td colspan=\"2\" class=\"ETablaT\">EXAMEN TOXICOLÓGICO</td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      out.print("<input type=\"hidden\" name=\"hdNumExamen\" value=\""+VPERCatalogoNoAp.getINumExamen()+"\">"); 
															  out.print("<tr>");
		                                                      out.println("<td class=\"EEtiqueta\">El examen Toxicológico quedara pendiente:</td>");
		                                                      out.println("<td class=\"ECampo\"> <SELECT NAME=\"toxpendiente\" SIZE=\"1\" onChange=\"\">");
		                                                      out.println("<option value=\"-1\">Seleccione...</option><option  value=\"1\" selected>SI</option><option  value=\"2\">NO</option></SELECT>");
		                                                      out.println("</td>");
		                                                      out.print("</tr>");
		
		                                                      String op1 ="";
		                                                      String op2="";
		                                                      String op3="";
		                                                      String op4="";
		                                                      String op5="";
		                                                      String op6="";
		                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 2);
		                                                      System.out.println("2-"+valor);
															  valor = ""+clsConfig.Dato(valor); 
															  if(valor.equals("-1")){op1 = "selected";}
		                                                      if(valor.equals("1")){op2 = "selected";}
		                                                      if(valor.equals("2")){op3 = "selected";}
		                                                      
		                                                      out.print("<tr>");
		                                                      out.println("<td class=\"EEtiqueta\"><div id=\"alcolimetria\" style=\"display:none;\">Alcoholimetría:</div></td>");
		                                                      out.println("<td class=\"ECampo\"><div id=\"alcolimetria2\" style=\"display:none;\"><SELECT NAME=\"alcolimetria\" SIZE=\"1\" onChange=\"\">");
		                                                      out.println("<option value=\"-1\" "+op1+">Seleccione...</option><option  value=\"1\"  "+op2+">SI</option><option  value=\"2\"  "+op3+">NO</option></SELECT>");
		                                                      out.println("</div></td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 10);
		                                                      System.out.println("10-"+valor);
															  valor = ""+clsConfig.Dato(valor); 
		                                                      out.print("<tr>");
		                                                      out.println("<td class=\"EEtiqueta\"><div id=\"numpba\" style=\"display:none;\">Número de Prueba:</div></td>");
		                                                      out.println("<td class=\"ECampo\"><div id=\"numpba2\" style=\"display:none;\">");
		                                                      out.println("<input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"numpba\" value=\""+valor+"\" onfocus=\"this.select();\""); 
		                                                      out.println("onBlur=\"validaNumero(this);\" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
		                                                      out.println("onMouseOver=\"if (window.fOverField) window.fOverField(0);\">");
		                                                      out.println("</div></td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 11);
		                                                      System.out.println("11-"+valor);
															  valor = ""+clsConfig.Dato(valor); 
		                                                      out.print("<tr>");
		                                                      out.println("<td class=\"EEtiqueta\"><div id=\"numeqp\" style=\"display:none;\">Número de Equipo:</div></td>");
		                                                      out.println("<td class=\"ECampo\"><div id=\"numeqp2\" style=\"display:none;\">");
		                                                      out.println("<input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"numeqp\" value=\""+valor+"\" onfocus=\"this.select();\""); 
		                                                      out.println("onBlur=\"validaNumero(this);\" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
		                                                      out.println("onMouseOver=\"if (window.fOverField) window.fOverField(0);\">");
		                                                      out.println("</div></td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 3);
		                                                      System.out.println("3-"+valor);
															  valor = ""+clsConfig.Dato(valor);
															  if(valor.equals("-1")){op1 = "selected";op2 = "";op3 = "";}
		                                                      if(valor.equals("1")){op2 = "selected";op3 = "";op1 = "";}
		                                                      if(valor.equals("2")){op3 = "selected";op1 = "";op2 = "";}
		                                                      out.print("<tr>");
		                                                      out.println("<td class=\"EEtiqueta\"><div id=\"pbaorina\" style=\"display:none;\">Prueba rápida en orina:</div></td>");
		                                                      out.println("<td class=\"ECampo\"><div id=\"pbaorina2\" style=\"display:none;\"><SELECT NAME=\"pbaorina\" SIZE=\"1\" onChange=\"\">");
		                                                      out.println("<option value=\"-1\" "+op1+">Seleccione...</option><option  value=\"1\" "+op2+">Negativa</option><option  value=\"2\" "+op3+">Positivo</option></SELECT>");
		                                                      out.println("</div></td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 12);
		                                                      System.out.println("12-"+valor);
															  valor = ""+clsConfig.Dato(valor); 
															  if(valor.equals("-1")){op1 = "selected";op2 = "";op3 = "";}
		                                                      if(valor.equals("1")){op2 = "selected";op3 = "";op1 = "";}
		                                                      if(valor.equals("2")){op3 = "selected";op1 = "";op2 = "";}
		                                                      if(valor.equals("3")){op4 = "selected";op1 = "";op2 = "";}
		                                                      if(valor.equals("4")){op5 = "selected";op1 = "";op2 = "";}
		                                                      if(valor.equals("5")){op6 = "selected";op1 = "";op2 = "";}
		                                                      out.print("<tr>");
		                                                      out.println("<td class=\"EEtiqueta\"><div id=\"rpositivo\" style=\"display:none;\">Prueba rápida positiva:</div></td>");
		                                                      out.println("<td class=\"ECampo\"><div id=\"rpositivo2\" style=\"display:none;\"><SELECT NAME=\"rpositivo\" SIZE=\"1\" onChange=\"\">");
		                                                      out.println("<option value=\"-1\" "+op1+">Seleccione...</option><option  value=\"1\" "+op2+">Anfetaminas</option><option  value=\"2\" "+op3+">Cocaína</option>"+
		                                                     		 "<option  value=\"3\" "+op4+">TCHC</option><option  value=\"4\" "+op5+">PCP</option><option  value=\"5\" "+op6+">Opiáceos</option></SELECT>");
		                                                      out.println("</div></td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 4);
		                                                      System.out.println("4-"+valor);
															  valor = ""+clsConfig.Dato(valor); 
		                                                      out.print("<tr>");
		                                                      out.println("<td class=\"EEtiqueta\"><div id=\"numffccc\" style=\"display:none;\">Número de FFCCC:</div></td>");
		                                                      out.println("<td class=\"ECampo\"><div id=\"numffccc2\" style=\"display:none;\">");
		                                                      out.println("<input type=\"ETablaC\" size=\"8\" maxlength=\"8\" name=\"numffccc\" value=\""+valor+"\" onfocus=\"this.select();\""); 
		                                                      out.println("onBlur=\"validaNumero(this);\" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
		                                                      out.println("onMouseOver=\"if (window.fOverField) window.fOverField(0);\">");
		                                                      out.println("</div></td>");
		                                                      out.print("</tr>");
		                                                      System.out.println("opcion 8");
		                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 5);
		                                                      System.out.println("5-"+valor);
															  valor = ""+clsConfig.Dato(valor); 
		                                                      out.print("<tr>");
		                                                      out.println("<td class=\"EEtiqueta\"><div id=\"numenvio\" style=\"display:none;\">Número de Envi&oacute;:</div></td>");
		                                                      out.println("<td class=\"ECampo\"><div id=\"numenvio2\" style=\"display:none;\">");
		                                                      out.println("<input type=\"ETablaC\" size=\"4\" maxlength=\"4\" name=\"numenvio\" value=\""+valor+"\" onfocus=\"this.select();\""); 
		                                                      out.println("onBlur=\"validaNumero(this);\" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
		                                                      out.println("onMouseOver=\"if (window.fOverField) window.fOverField(0);\">");
		                                                      out.println("</div></td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 5, 6);
		                                                      String anio = valor.substring(0, 4);
		                                                      String mes = valor.substring(5, 7);
		                                                      String dia = valor.substring(8, 10);
		                                                      valor = dia+"/"+mes+"/"+anio;
		                                                      out.print("<tr>");
		                                                      out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha de Toma:", "ECampo", "text", 10, 10,1,"dttomada", valor, 0, "", "fValFecha(this.value);", false, true,true, request));
		                                                      out.print("</tr>");
		                                                      
		                                                      out.print("<tr>");
		                                                      out.print("<td class='ETablaC' colspan='2'>");
		                                                      out.print(vEti.clsAnclaTexto("EAncla","Guardar","JavaScript:fGuardar2();", "Guardar",""));
		                                                      out.print("</td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      out.println("</td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      out.print("<tr>");
		                                                      out.print("<td colspan=\"2\" class=\"ETablaT\">CAPTURA DE EVIDENCIA DOCUMENTAL</td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      //Acción de Guardar.
		                                                      out.print("<tr>");
		                                                      out.print("<td class='ETablaC' colspan='2'>");
		                                                      out.print(vEti.clsAnclaTexto("EAncla","Cargar archivo de imagen","JavaScript: makenewImagenServicioFile2('"+exa+"');","Subir imagenes relacionadas"));
		                                                      out.print("</td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      out.print("<tr>");
		                                                      out.print("<td class='ETablaC' colspan='2'>");
		                                                      out.print(vEti.clsAnclaTexto("EAncla","Mostrar archivos de imagenes","JavaScript:showPlacasToraxFiles2('"+exa+"');","Ver imagenes relacionadas"));
		                                                      out.print("</td>");
		                                                      out.print("</tr>");
		                                                      
		                                                      
		                                                      out.print("<tr>");
		                                                      out.print("<td colspan=\"2\" class=\"ETablaT\">Motivos de No Aptitud</td>");
		                                                      out.print("</tr>");
		                                                      out.print("<tr>");
		                                                      out.print("<td class=\"ETablaT\">Motivo</td>");
		                                                      out.print("<td class=\"ETablaT\">Rubro</td>");
		                                                      out.print("</tr>");
		                                                      
														}else{
															  if(acceso){////Usuario Permitido
																  out.print("<tr>");
			                                                      out.print("<td colspan=\"2\" class=\"ETablaT\">EXAMEN TOXICOLÓGICO</td>");
			                                                      out.print("</tr>");
																  respuesta = "";
																  valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 9);
																  System.out.println("9-"+valor);
																  valor = ""+clsConfig.Dato(valor); 
																  out.print("<tr>");
			                                                      out.println("<td class=\"EEtiqueta\">El examen Toxicológico quedara pendiente:</td>");
			                                                      out.println("<td class=\"ECampo\">");
			                                                      if(valor.equals("-1")){respuesta = "Seleccione...";}
			                                                      if(valor.equals("1")){respuesta = "SI";}
			                                                      if(valor.equals("2")){respuesta = "NO";}
			                                                      out.println(respuesta);
			                                                      out.println("</td>");
			                                                      out.print("</tr>");
		
			                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 2);
			                                                      System.out.println("2-"+valor);
																  valor = ""+clsConfig.Dato(valor); 
			                                                      out.print("<tr>");
			                                                      out.println("<td class=\"EEtiqueta\">Alcoholimetría:</td>");
			                                                      out.println("<td class=\"ECampo\">");
			                                                      if(valor.equals("-1")){respuesta = "Seleccione...";}
			                                                      if(valor.equals("1")){respuesta = "SI";}
			                                                      if(valor.equals("2")){respuesta = "NO";}
			                                                      out.println(respuesta);
			                                                      out.println("</td>");
			                                                      out.print("</tr>");
			                                                      
			                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 10);
			                                                      System.out.println("10-"+valor);
																  valor = ""+clsConfig.Dato(valor); 
			                                                      out.print("<tr>");
			                                                      out.println("<td class=\"EEtiqueta\">Número de Prueba:</td>");
			                                                      out.println("<td class=\"ECampo\">");
			                                                      out.println(valor);
			                                                      out.println("</td>");
			                                                      out.print("</tr>");
			                                                      
			                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 11);
			                                                      System.out.println("11-"+valor);
																  valor = ""+clsConfig.Dato(valor); 
			                                                      out.print("<tr>");
			                                                      out.println("<td class=\"EEtiqueta\">Número de Equipo:</td>");
			                                                      out.println("<td class=\"ECampo\">");
			                                                      out.println(valor);
			                                                      out.println("</td>");
			                                                      out.print("</tr>");
			                                                      
			                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 3);
			                                                      System.out.println("3-"+valor);
																  valor = ""+clsConfig.Dato(valor); 
			                                                      out.print("<tr>");
			                                                      out.println("<td class=\"EEtiqueta\">Prueba rápida en orina:</td>");
			                                                      out.println("<td class=\"ECampo\">");
			                                                      if(valor.equals("-1")){respuesta = "Seleccione...";}
			                                                      if(valor.equals("1")){respuesta = "Negativa";}
			                                                      if(valor.equals("2")){respuesta = "Positiva";}
			                                                      out.println(respuesta);
			                                                      out.println("</td>");
			                                                      out.print("</tr>");
			                                                      
			                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 12);
			                                                      System.out.println("12-"+valor);
																  valor = ""+clsConfig.Dato(valor); 
			                                                      out.print("<tr>");
			                                                      out.println("<td class=\"EEtiqueta\">Prueba rápida positiva:</td>");
			                                                      out.println("<td class=\"ECampo\">");
			                                                      if(valor.equals("-1")){respuesta = "Seleccione...";}
			                                                      if(valor.equals("1")){respuesta = "Anfetaminas";}
			                                                      if(valor.equals("2")){respuesta = "Cocaína";}
			                                                      if(valor.equals("3")){respuesta = "TCHC";}
			                                                      if(valor.equals("4")){respuesta = "PCP";}
			                                                      if(valor.equals("5")){respuesta = "Opiáceos";}
			                                                      out.println(respuesta);
			                                                      out.println("</td>");
			                                                      out.print("</tr>");
			                                                      
			                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 4);
			                                                      System.out.println("4-"+valor);
																  valor = ""+clsConfig.Dato(valor); 
			                                                      out.print("<tr>");
			                                                      out.println("<td class=\"EEtiqueta\">Número de FFCCC:</td>");
			                                                      out.println("<td class=\"ECampo\">");
			                                                      out.println(valor);
			                                                      out.println("</td>");
			                                                      out.print("</tr>");
	
			                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 2, 5);
			                                                      System.out.println("5-"+valor);
																  valor = ""+clsConfig.Dato(valor); 
			                                                      out.print("<tr>");
			                                                      out.println("<td class=\"EEtiqueta\">Número de Envi&oacute;:</td>");
			                                                      out.println("<td class=\"ECampo\">");
			                                                      out.println(valor);
			                                                      out.println("</td>");
			                                                      out.print("</tr>");
			                                                      
			                                                      valor = dEXAMENNoAp.RegresaValorCampo(exp, exa, serv, rama, 5, 6);
			                                                      out.print("<tr>");
			                                                      out.println("<td class=\"EEtiqueta\">Fecha de Toma:</td>");
			                                                      out.println("<td class=\"ECampo\">");
	
			                                                      if(valor != null){
				                                                      if(valor.length()>10){
				                                                      	out.println(valor.substring(0, 10));
				                                                      }else{
				                                                    	  out.println("&nbsp;");
				                                                      }
			                                                      }
			                                                      out.println("</td>");
			                                                      out.print("</tr>");
															  }     
			                                                 out.print("<tr>");
			                                                 out.print("<td colspan=\"2\" class=\"ETablaT\">CAPTURA DE EVIDENCIA DOCUMENTAL</td>");
			                                                 out.print("</tr>");
			                                                      
			                                                //Acción de Guardar.
			                                                out.print("<tr>");
			                                                out.print("<td class='ETablaC' colspan='2'>");
			                                                out.print(vEti.clsAnclaTexto("EAncla","Cargar archivo de imagen","JavaScript: makenewImagenServicioFile2('"+exa+"');","Subir imagenes relacionadas"));
			                                                out.print("</td>");
			                                                out.print("</tr>");
			                                                      
			                                                out.print("<tr>");
			                                                out.print("<td class='ETablaC' colspan='2'>");
			                                                out.print(vEti.clsAnclaTexto("EAncla","Mostrar archivos de imagenes","JavaScript:showPlacasToraxFiles2('"+exa+"');","Ver imagenes relacionadas"));
			                                                out.print("</td>");
			                                                out.print("</tr>");
			                                                      
			                                                out.print("<tr>");
			                                                out.print("<td colspan=\"2\" class=\"ETablaT\">Motivos de No Aptitud</td>");
			                                                out.print("</tr>");
			                                                out.print("<tr>");
			                                                out.print("<td class=\"ETablaT\">Motivo</td>");
			                                                out.print("<td class=\"ETablaT\">Rubro</td>");
			                                                out.print("</tr>");
															
														}
                                                      }
                                                      
                                                    }
                                                    m = m + 1;
                                                  }
                                                  if (VPERCatalogoNoAp.getCDscMotivoNoAp() != null && VPERCatalogoNoAp.getCDscRubroNoAp() != null){
                                                    if (request.getParameter("lAccion").compareToIgnoreCase("Motivos")!=0){
                                                      out.println("<tr>");
                                                      out.println(vEti.Texto("ETabla","" + VPERCatalogoNoAp.getCDscMotivoNoAp()));
                                                      out.println(vEti.Texto("ETabla","" + VPERCatalogoNoAp.getCDscRubroNoAp()));
                                                      out.println("</tr>");
                                                    } else {
                                                      if (request.getParameter("hdiCveUniMed").toString().compareToIgnoreCase(Integer.toString(VGRLUniMed.getICveUniMed())) ==0 &&
                                                           request.getParameter("hdiCveMdoTrans").toString().compareToIgnoreCase(Integer.toString(VGRLMdoTrans.getICveMdoTrans())) ==0 &&
                                                           request.getParameter("hdiCveCategoria").toString().compareToIgnoreCase(Integer.toString(VGRLCategoria.getICveCategoria())) ==0){
                                                        out.println("<tr>");
                                                        out.println(vEti.Texto("ETabla","" + VPERCatalogoNoAp.getCDscMotivoNoAp()));
                                                        out.println(vEti.Texto("ETabla","" + VPERCatalogoNoAp.getCDscRubroNoAp()));
                                                        out.println("</tr>");
                                                      }
                                                    }
                                                  }
                                                }
                                             }
                                             if (m >= 1){
                                               if (request.getParameter("lAccion").compareToIgnoreCase("Motivos")!=0){
                                                 out.print("<tr>");
                                                 out.print("<td class='ETablaC' colspan='2'>");
                                                 out.print(vEti.clsAnclaTexto("EAncla","Agregar Motivos de No Aptitud","JavaScript:fMotivos(" + iCveUniMed + "," + iCveMdoTrans +"," + iCveCategoria + "," + iCveCatalogoNoAp +");", "Motivos",""));
                                                 out.print("</td>");
                                                 out.print("</tr>");
                                               }
                                             }
                                           }
                                        }
                                     }
                                   }
                                   out.print("</tr>");
                                 }

                                 }

                                 out.print("<input type='hidden' name='iCveUsuResp' value='" + vUsuario.getICveusuario() + "'>");

                                 if(request.getParameter("lAccion").compareToIgnoreCase("Agregar")==0){
                                   out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                   out.println("<td>");
                                   out.println(vEti.SelectOneRowSinTD("iCveUniMed","", vcGRLUniMed2, "iCveUniMed", "cDscUniMed", request, "0", true));
                                   out.println("</td>");
                                   out.println("</tr>");

                                   //out.print("<input type='hidden' name='iCveUsuResp' value='" + vUsuario.getICveusuario() + "'>");

                                   TDGRLMdoTrans dGRLMdoTrans = new TDGRLMdoTrans();
                                   out.println(vEti.Texto("EEtiqueta","Modo de Transporte:"));
                                   out.println("<td>");
                                   out.println(vEti.SelectOneRowSinTD("iCveMdoTrans","llenaSLTCategorias(1,this.value,'',''," + iCvePersonal + "," + iCveMdoTrans + "," + iCveCatalogoNoAp + ",document.forms[0].iCveCategoria);" , dGRLMdoTrans.findByAll("") , "iCveMdoTrans", "cDscMdoTrans", request, "0", true));
                                   out.println("</td>");
                                   out.println("</tr>");
 
                                   out.println("<tr>"); 
                                   out.println(vEti.Texto("EEtiqueta","Categoría-2:"));
                                   out.println("<td class='ECampo'>");
                                   out.println("<SELECT NAME='iCveCategoria' SIZE='1'");
                                   out.println("</SELECT>");
                                   out.println("</td>");
                                   out.println("</tr>");

                                   out.print("<tr>");
//FCSReq4                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Periodo de:", "ECampo", "text", 10, 10,1,"dtInicio", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10,1,"dtInicio", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                                   out.print("</tr>");
/*FCSReq4 
                                   out.print("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "a:", "ECampo", "text", 10, 10,1,"dtFin", "", 0, "", "fValFecha(this.value);", false, true,true, request));
                                   out.print("</tr>");
*/
//                                  out.print("<tr>");
//                                   out.print("<td class='EEtiqueta'>Periodo:</td>");
                                   out.print("<td class='ETabla'><input type='hidden' name='iPeriodo' size='2' maxlength='2' ></td>");
//                                   out.print("<td class='ETabla'><input type='hidden' name='iPeriodo' size='2' maxlength='2' >(meses)</td>");
//                                   out.print("</tr>");

                                   out.print("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha de Captura:", "ECampo", "text", 10, 10,1,"dtCaptura", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                                   out.print("</tr>");
                                 }

                                 //Motivos de No Aptitud.
                                 if(request.getParameter("lAccion").compareToIgnoreCase("Motivos")==0){
                                   //out.print("<tr>");
                                   //out.print("<td colspan=\"2\" class=\"ETablaT\">Motivos de No Aptitud</td>");
                                   //out.print("</tr>");
                                   TDPERMotivoNoAp    dPERMotivoNoAp    = new TDPERMotivoNoAp();
                                   TDPERRubroNoAp     dPERRubroNoAp     = new TDPERRubroNoAp();
                                   TDPERCatMotRubNoAp DPERCatMotRubNoAp = new TDPERCatMotRubNoAp();
                                   Vector vPERCatMotRubNoAp = new Vector();
                                   Vector vPERMotivoNoAp    = new Vector();
                                   Vector vPERRubroNoAp     = new Vector();
                                   try{
                                     if (request.getParameter("iCvePersonal") != null && request.getParameter("iCveMdoTrans") != null &&
                                         request.getParameter("iCveCategoria") != null){
                                       vPERCatMotRubNoAp = DPERCatMotRubNoAp.FindByAll(" where PERCatMotRubNoAp.iCvePersonal  = " + request.getParameter("iCvePersonal")  +
                                                                                       "   and PERCatMotRubNoAp.iCveMdoTrans  = " + request.getParameter("iCveMdoTrans")  +
                                                                                       "   and PERCatMotRubNoAp.iCveCategoria = " + request.getParameter("iCveCategoria") +
                                                                                       "   and PERCatMotRubNoAp.lActivo = 1 ");
                                       vPERMotivoNoAp = dPERMotivoNoAp.FindByAll(" order by cDscMotivoNoAp ");
                                       vPERRubroNoAp  = dPERRubroNoAp.FindByAll(" where lactivo = 1 order by cDscRubroNoAp ");
                                     }
                                   } catch (Exception ex){
                                     ex.printStackTrace();
                                   }
                                   //out.print("<tr>");
                                   //out.print("<td class=\"ETablaT\">Motivo</td>");
                                   //out.print("<td class=\"ETablaT\">Rubro</td>");
                                   //out.print("</tr>");
                                   out.print("<tr>");
                                   if (!vPERCatMotRubNoAp.isEmpty()){
                                     for(int i=0;i<vPERCatMotRubNoAp.size();i++){
                                       TVPERCatMotRubNoAp VPERCatMotRubNoAp = new TVPERCatMotRubNoAp();
                                       VPERCatMotRubNoAp = (TVPERCatMotRubNoAp) vPERCatMotRubNoAp.get(i);
                                       if (!vPERRubroNoAp.isEmpty()){
                                         for(int j=0;j<vPERRubroNoAp.size();j++){
                                           TVPERRubroNoAp VPERRubroNoAp = new TVPERRubroNoAp();
                                           VPERRubroNoAp = (TVPERRubroNoAp) vPERRubroNoAp.get(j);
                                           if (VPERCatMotRubNoAp.getICveMotivoNoAp() == VPERRubroNoAp.getICveMotivoNoAp() &&
                                               VPERCatMotRubNoAp.getICveRubroNoAp()  == VPERRubroNoAp.getICveRubroNoAp()){
                                              //Desplegar el Motivo.
                                              for(int m=0;m<vPERMotivoNoAp.size();m++){
                                                TVPERMotivoNoAp VPERMotivoNoAp = new TVPERMotivoNoAp();
                                                VPERMotivoNoAp = (TVPERMotivoNoAp) vPERMotivoNoAp.get(m);
                                                if (VPERMotivoNoAp.getICveMotivoNoAp() == VPERCatMotRubNoAp.getICveMotivoNoAp())
                                                  out.println(vEti.Texto("EEtiqueta","" + VPERMotivoNoAp.getCDscMotivoNoAp()));
                                              }
                                              //Desplegar el Rubro.
                                              out.print  ("<td class=\"EEtiqueta\">" + VPERRubroNoAp.getcDscRubroNoAp());
                                              out.print  (vEti.clsAnclaTexto("EAncla","Borrar Motivo y Rubro","JavaScript:fBorrarMot();", "BorrarMot",""));
                                              out.println("</td");

                                           }
                                         }
                                       }
                                     }
                                   }
                                   out.println("</tr>");
                                   out.print("<tr>");
                                   out.println("<td>");
                                   out.println(vEti.SelectOneRowSinTD("iCveMotivoNoAp","llenaSLTRubros(2,this.value,'',''," + iCvePersonal + ","+ iCveMdoTrans + "," + iCveCategoria +"," + iCveCatalogoNoAp +",document.forms[0].iCveRubroNoAp);", dPERMotivoNoAp.FindByAll(" order by cDscMotivoNoAp "), "iCveMotivoNoAp", "cDscMotivoNoAp", request, "0", true));
                                   out.println("</td>");
                                   out.println("<td>");
                                   out.println(vEti.SelectOneRowSinTD("iCveRubroNoAp","", vPERRubroNoAp, "iCveRubroNoAp", "cDscRubroNoAp", request, "0", true));
                                   out.println(vEti.clsAnclaTexto("EAncla","Agregar Motivo y Rubro","JavaScript:fAgregarMot(" + iCveUniMed + "," + iCveMdoTrans + "," + iCveCategoria + "," + iCveCatalogoNoAp + ");", "AgregarMot",""));
                                   out.println("</td>");
                                   out.println("</tr>");
                                 }

                                 if(request.getParameter("lAccion").compareToIgnoreCase("Agregar")==0){
                                   if (request.getParameter("lAccion").compareTo("Motivos")!=0){
                                     //Observaciones de la No Aptitud.
                                     out.print("<tr>");
                                     out.print("<td colspan=\"2\" class=\"ETablaT\">Observación de la No Aptitud</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.println(vEti.EtiAreaTexto("EEtiqueta","Observaciones:","ECampo",50,3,"cObsevaciones","",3,"","",true,true,true));
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.print("<td colspan=\"2\" class=\"ETablaT\">EXAMEN TOXICOLÓGICO</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                     out.println("<td class=\"EEtiqueta\">El examen Toxicológico quedara pendiente:</td>");
                                     out.println("<td class=\"ECampo\"> <SELECT NAME=\"toxpendiente\" SIZE=\"1\" onChange=\"\">");
                                     out.println("<option value=\"-1\">Seleccione...</option><option  value=\"1\">SI</option><option  value=\"2\">NO</option></SELECT>");
                                     out.println("</td>");
                                     out.print("</tr>");

                                     out.print("<tr>");
                                     out.println("<td class=\"EEtiqueta\"><div id=\"alcolimetria\" style=\"display:none;\">Alcoholimetría:</div></td>");
                                     out.println("<td class=\"ECampo\"><div id=\"alcolimetria2\" style=\"display:none;\"><SELECT NAME=\"alcolimetria\" SIZE=\"1\" onChange=\"\">");
                                     out.println("<option value=\"-1\">Seleccione...</option><option  value=\"1\">SI</option><option  value=\"2\">NO</option></SELECT>");
                                     out.println("</div></td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.println("<td class=\"EEtiqueta\"><div id=\"numpba\" style=\"display:none;\">Número de Prueba:</div></td>");
                                     out.println("<td class=\"ECampo\"><div id=\"numpba2\" style=\"display:none;\">");
                                     out.println("<input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"numpba\" value=\"\" onfocus=\"this.select();\""); 
                                     out.println("onBlur=\"validaNumero(this);\" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
                                     out.println("onMouseOver=\"if (window.fOverField) window.fOverField(0);\">");
                                     out.println("</div></td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.println("<td class=\"EEtiqueta\"><div id=\"numeqp\" style=\"display:none;\">Número de Equipo:</div></td>");
                                     out.println("<td class=\"ECampo\"><div id=\"numeqp2\" style=\"display:none;\">");
                                     out.println("<input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"numeqp\" value=\"\" onfocus=\"this.select();\""); 
                                     out.println("onBlur=\"validaNumero(this);\" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
                                     out.println("onMouseOver=\"if (window.fOverField) window.fOverField(0);\">");
                                     out.println("</div></td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.println("<td class=\"EEtiqueta\"><div id=\"pbaorina\" style=\"display:none;\">Prueba rápida en orina:</div></td>");
                                     out.println("<td class=\"ECampo\"><div id=\"pbaorina2\" style=\"display:none;\"><SELECT NAME=\"pbaorina\" SIZE=\"1\" onChange=\"\">");
                                     out.println("<option value=\"-1\">Seleccione...</option><option  value=\"1\">Negativa</option><option  value=\"2\">Positivo</option></SELECT>");
                                     out.println("</div></td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.println("<td class=\"EEtiqueta\"><div id=\"rpositivo\" style=\"display:none;\">Prueba rápida positiva:</div></td>");
                                     out.println("<td class=\"ECampo\"><div id=\"rpositivo2\" style=\"display:none;\"><SELECT NAME=\"rpositivo\" SIZE=\"1\" onChange=\"\">");
                                     out.println("<option value=\"-1\">Seleccione...</option><option  value=\"1\">Anfetaminas</option><option  value=\"2\">Cocaína</option>"+
                                    		 "<option  value=\"3\">TCHC</option><option  value=\"4\">PCP</option><option  value=\"5\">Opiáceos</option></SELECT>");
                                     out.println("</div></td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.println("<td class=\"EEtiqueta\"><div id=\"numffccc\" style=\"display:none;\">Número de FFCCC:</div></td>");
                                     out.println("<td class=\"ECampo\"><div id=\"numffccc2\" style=\"display:none;\">");
                                     out.println("<input type=\"ETablaC\" size=\"8\" maxlength=\"8\" name=\"numffccc\" value=\"\" onfocus=\"this.select();\""); 
                                     out.println("onBlur=\"validaNumero(this);\" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
                                     out.println("onMouseOver=\"if (window.fOverField) window.fOverField(0);\">");
                                     out.println("</div></td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.println("<td class=\"EEtiqueta\"><div id=\"numenvio\" style=\"display:none;\">Número de Envi&oacute;:</div></td>");
                                     out.println("<td class=\"ECampo\"><div id=\"numenvio2\" style=\"display:none;\">");
                                     out.println("<input type=\"ETablaC\" size=\"4\" maxlength=\"4\" name=\"numenvio\" value=\"\" onfocus=\"this.select();\""); 
                                     out.println("onBlur=\"validaNumero(this);\" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
                                     out.println("onMouseOver=\"if (window.fOverField) window.fOverField(0);\">");
                                     out.println("</div></td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha de Toma:", "ECampo", "text", 10, 10,1,"dttomada", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.print("<td colspan=\"2\" class=\"ETablaT\">CAPTURA DE EVIDENCIA DOCUMENTAL</td>");
                                     out.print("</tr>");
                                     
                                     //Acción de Guardar.
                                     out.print("<tr>");
                                     out.print("<td class='ETablaC' colspan='2'>");
                                     out.print(vEti.clsAnclaTexto("EAncla","Cargar archivo de imagen","JavaScript: makenewImagenServicioFile();","Subir imagenes relacionadas"));
                                     out.print("</td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.print("<td class='ETablaC' colspan='2'>");
                                     out.print(vEti.clsAnclaTexto("EAncla","Mostrar archivos de imagenes","JavaScript:showPlacasToraxFiles();","Ver imagenes relacionadas"));
                                     out.print("</td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.print("<td colspan=\"2\" class=\"ETablaT\">&nbsp;</td>");
                                     out.print("</tr>");
                                     
                                     out.print("<tr>");
                                     out.print("<td class='ETablaC' colspan='2'>");
                                     out.print(vEti.clsAnclaTexto("EAncla","Guardar","JavaScript:fGuardar();", "Guardar",""));
                                     out.print("</td>");
                                     out.print("</tr>");
                                     
                                   }
                                 }

                                 System.out.println(request.getParameter("lAccion"));
                                 System.out.println(request.getParameter("lAgregar"));
                                 if((request.getParameter("lAccion").compareToIgnoreCase("")==0 &&
                                     request.getParameter("lAgregar").compareTo("true")!=0) ||
                                    (request.getParameter("lAccion").compareToIgnoreCase("")==0 ||
                                     request.getParameter("lAccion").compareToIgnoreCase("AgregarMot")==0 ||
                                     request.getParameter("lAccion").compareToIgnoreCase("ActualizaNoAp")==0 ||
                                     request.getParameter("lAccion").compareToIgnoreCase("Quitar")==0 )){
                                   out.print("<tr>");
                                   out.print("<td colspan=\"2\" class=\"ETablaT\">Seleccione la Accion a Ejecutar</td>");
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='2'>");
                                   out.print(vEti.clsAnclaTexto("EAncla","Agregar a Catálogo de No Aptos","JavaScript:fAgregar();", "Agregar",""));
                                   out.print("</td>");
                                   out.print("</tr>");
                                 }

                               }
                               else{
                                 out.print("<tr>");
                                 out.print("<td class='ETablaC' colspan='2'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                 out.print("</tr>");
                               }

                            %>
					</table>
					<% // Fin de Datos %>
				</td>
			</tr>
			<%}else{%>
			<script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
			<%}%>
			<input type="hidden" name="lAccion" value=''>
		</table>
	</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>