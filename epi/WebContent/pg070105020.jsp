<%/**
 * Title: Listado del detalle de Exámenes Concluidos
 * Description: JSP para mostrar el detalle de los exámenes concluidos
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Esteban Viveros
 * @version 1
 * Clase: ?
 */%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<html>
<%
  pg070105020CFG  clsConfig = new pg070105020CFG();               // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070105020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070105020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070105021.jsp";     // modificar
  String cOperador    = "3";                   // modificar
  String cDscOrdenar  = "No Disponible|";      // modificar
  String cCveOrdenar  = "0|";                  // modificar
  String cDscFiltrar  = "No Disponible|";      // modificar
  String cCveFiltrar  = "0|";                  // modificar
  String cTipoFiltrar = "8|";                  // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  String cNumExamTmp=request.getParameter("hdINumExamTmp");//si existe esta variable es que venimos de la 5050
  boolean lMuestraBotones=cNumExamTmp==null || cNumExamTmp.length()==0;
//  if(lMuestraBotones) clsConfig.setPaginas("pg070402040.jsp");
//  else clsConfig.setPaginas("pg070105050.jsp");
/*cPaginas temporal para prueba de EPI*/  clsConfig.setPaginas("pg070105010.jsp");
  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = "Hidden"; //clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  TVUsuario  vUsuario =(TVUsuario)request.getSession(true).getAttribute("UsrID");

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>

<script type='text/javascript' src='/medprev/dwr/engine.js'></script>
<script type='text/javascript' src='/medprev/dwr/util.js'></script>
<script type='text/javascript' src='/medprev/dwr/interface/MedPredDwr.js'></script>

<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
	var GRALiCveExp, GRALiNumExm;
	
	function fIrCatalogoISM(iCveExp, iNumExm) {
		var form = document.forms[0];
		GRALiCveExp = iCveExp;
		GRALiNumExm = iNumExm;

		openpopupValidaMedico();

	}
  
	function estaDictaminado(iCveExp, iNumExm) {
		var form = document.forms[0];
		if(<%=request.getParameter("hdICveProceso") != null ? ""+request
				.getParameter("hdICveProceso") : 0%> == 1){
			MedPredDwr.evaluaSiExamenEstaDictaminado(iCveExp, iNumExm, {
				async : false, // synchronous call
				callback : function(data) {
					
					// alert(data);
					if (data == 1) {
						respuestaopenpopupValidaPaciente();
					} else {
						fIrCatalogoISM(iCveExp, iNumExm);
					}
				}
			});			
		}else{
			respuestaopenpopupValidaPaciente();
		}
	}
  
	function openpopupValidaMedico() {
		Loading();
		var popurl = "./validaBiometricoMedicoPacienteDictamen.jsp?idPersona=1&iNumExm=0&iCveExp=0";
		window.open(popurl, "", "width=10,height=10,status,menubar,");
	}
	function respuestaopenpopupValidaMedico() {
		openpopupValidaPaciente();
	}
	function openpopupValidaPaciente() {
		Loading();
		var popurl = "./validaBiometricoMedicoPacienteDictamen.jsp?idPersona=2&iNumExm="
				+ GRALiNumExm + "&iCveExp=" + GRALiCveExp;
		window.open(popurl, "", "width=10,height=10,status,menubar,");
	}
	function respuestaopenpopupValidaPaciente() {
		fIrCatalogo('pg070105040.jsp');
	}  
</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
	<div id="loading"
		style="position: absolute; width: 100%; text-align: center; top: 250px;">
		<img src="<%=vParametros.getPropEspecifica("RutaImg")%>nuevo6.gif"	border=0>
	</div>
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <!--input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null) out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>"-->
  <input type="hidden" name="FILNumReng" value="200">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?Integer.toString(bs.pageNo()):""%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdICveServicio" value="">
  <input type="hidden" name="hdINumExamTmp" value="<%=cNumExamTmp!=null?cNumExamTmp:""%>">
  <input type="hidden" name="iAnioSel" value="<%=request.getParameter("iAnioSel")%>">
  <input type="hidden" name="iCveMdoTransSel" value="<%=request.getParameter("iCveMdoTransSel")%>">
  <input type="hidden" name="hdICveProceso" value="">
  <input type="hidden" name="cPagina" value="pg070105020.jsp">
<%
  if (request.getParameter("iCveProceso") != null){
     if (request.getParameter("iCveProceso").length()>0)
        out.print(" <input type=\"hidden\" name=\"iCveProceso\" value=\"" + request.getParameter("iCveProceso").toString() +"\"> \n");
     else {
        if (request.getParameter("hdICveProceso") != null)
           out.print(" <input type=\"hidden\" name=\"iCveProceso\" value=\"" + request.getParameter("hdICveProceso").toString() +"\"> \n");
     }
  } else {
        if (request.getParameter("hdICveProceso") != null)
           out.print(" <input type=\"hidden\" name=\"iCveProceso\" value=\"" + request.getParameter("hdICveProceso").toString() +"\"> \n");
  }

   //System.out.println("Expediente JSP:" + request.getParameter("hdICveExpediente"));
   //System.out.println("Exmamen JSP:" + request.getParameter("hdINumExamen"));
   //System.out.println("iAnioSel JSP:" + request.getParameter("iAnioSel"));
   TVPERDatos vPERDatos=clsConfig.getUser(request.getParameter("hdICveExpediente"));

%>
<%-- Estos parametros son para las paginas 070104033 y 070105040--%>
  <input type="hidden" name="iCveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="iNumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="iCvePersonal" value="<%=vPERDatos.getICvePersonal()%>">
  <input type="hidden" name="iCveServicio" value="-1">
<%-- Hasta aqui los parametros para las paginas 070104033 y 070105040--%>
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%

   if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr><td class="ETablaT" colspan="4">Datos del Personal</td></tr>
<%    if(vPERDatos!=null){
%>          <td class="EEtiqueta">Nombre:</td>
          <td class="ETabla"><%=vPERDatos.getCNombre()+" "+vPERDatos.getCApPaterno()+" "+vPERDatos.getCApMaterno()%></td>
          <td class="EEtiqueta">Expediente:</td>
          <td class="ETabla"><%=vPERDatos.getICveExpediente()%></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Edad:</td>
          <td class="ETabla"><%=clsConfig.getEdad(vPERDatos.getDtNacimiento())%></td>
          <td class="EEtiqueta">Sexo:</td>
          <td class="ETabla"><%=("F".compareTo(vPERDatos.getCSexo()) == 0? "Mujer" : "Hombre" )%></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Proceso:</td>
          <td class="ETabla" colspan="3"><%=clsConfig.getProceso(request.getParameter("hdICveProceso")).getCDscProceso()%></td>
        </tr>
<%    }else{
%>        <tr><td class="EResalta" colspan="4">Datos no disponibles</td></tr>
<%    }
%>      </table>
    </td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="7">Resultado del Examen Actual</td></tr>
<%    if(bs!=null){
	TVEXPDictamenServ vDato2=null;
    Vector vcDatos=bs.getVectorBeans();
    String cDscCategorias="";
    String cTmp="";
    
    for(int i=0;i<(vcDatos.size());i++){
    	cDscCategorias = "";
      TVEXPDictamenServ vDato1=(TVEXPDictamenServ)vcDatos.get(i);
      vDato2=(TVEXPDictamenServ)vcDatos.get(i);
      if(vDato1.getICveServicio()==vDato2.getICveServicio()){
        if(vDato1.getLDictamen()==0){
          		cDscCategorias+= "<td class='ENoAptitud'>NO APTO " + vDato1.getCDscCategoria()+"</td>" ;
          }else{
          		cDscCategorias+= "<td class='EEtiqueta'>APTO " + vDato1.getCDscCategoria()+ "</td>";
          }
      }else{
%>        <tr class="ETablaST" valign="top">
      <td align="right">Servicio:</td>

      <!-- Muestra los resultados solo si el servicio diagnostica o hace recomendaciones -->
      <%
        TDMEDServicio dMEDServicio = new TDMEDServicio();
        TVMEDServicio vMEDServicio = new TVMEDServicio();
        Vector vcMEDServicio = new Vector();
        vcMEDServicio = dMEDServicio.FindByAll(" where lReqDiag = 1 and iCveServicio = " + vDato1.getICveServicio(), "");
        if (vcMEDServicio.size() > 0)
          for (int j = 0; j < vcMEDServicio.size(); j++)
            vMEDServicio = (TVMEDServicio)vcMEDServicio.get(j);
        if(vMEDServicio.getLReqDiag()==1){
      %>
          <td><%=vDato1.getCDscServicio()!=null?vDato1.getCDscServicio():"&nbsp;"%></td>
          <td align="right" colspan='3'>Dictamen:</td>

<%
          int iDictamen = vDato1.getLDictamen();
          if(iDictamen==1){
             cDscCategorias+= "<td class='EEtiqueta'>APTO " + vDato1.getCDscCategoria()+ "</td>";
%>          <!--td colspan="3">Apto&nbsp;<%//out.print(vDato1.getCDscCategoria());%></td-->

          <!-- <td><table><%=cDscCategorias%></table></td>-->
<%
          }else{
           cDscCategorias+= "<td class='ENoAptitud'>NO APTO " + vDato1.getCDscCategoria()+ "</td>";
%>          <!--td class="ENoAptitud">No Apto</td-->
          <!--td align="right">Categor&iacute;as:</td-->
          <!--td><%//out.print(vDato1.getCDscCategoria());%></td-->
          <!-- <td><table><%=cDscCategorias%></table></td> -->
<%          }

      }
      else{
        %>
          <td colspan='5'><%=vDato1.getCDscServicio()!=null?vDato1.getCDscServicio():"&nbsp;"%></td>
        <%
      }
          if(clsConfig.getLPagina(cCatalogo)){
%>         <!-- <td><a class="EAncla" href="javascript:fIrConsultaSintomaRama('<%=vDato1.getICveServicio()%>')">Detalle</a></td> -->
		   <td><a class="EAncla" href="javascript:fVerExamen(<%=vDato1.getICveExpediente()%>,<%=vDato1.getINumExamen()%>,<%=vDato1.getICveServicio()%>,<%=request.getParameter("hdICveProceso")%>)">Detalle</a></td>
<%          }
        if(vMEDServicio.getLReqDiag()==1){
%>        </tr>
        <tr class="EEtiqueta"><td align="left" colspan="7">Nota M&eacute;dica:</td></tr>
        <tr><td class="ETabla" colspan="7"><%=vDato1.getCNotaMedica()!=null?vDato1.getCNotaMedica():"&nbsp;"%></td></tr>
<%          cDscCategorias="";
        }
        else{
          %>
            <tr><td  class="ETablaC" align="left" colspan="8">&nbsp;</td></tr>
          <%
        }
      cDscCategorias = "";
    }
  //}
       
    
    if(vDato2!=null){
          /*if(vDato2.getLDictamen()==0){ 
        	  cDscCategorias+=vDato2.getCDscCategoria()+"<br>";
        	  System.out.println("5 "+cDscCategorias);
       		}*/
%>        <tr class="ETablaST" valign="top">
          <td align="right">Servicio:</td>
          <td><%=vDato2.getCDscServicio()!=null?vDato2.getCDscServicio():"&nbsp;"%></td>
          <td align="right">Dictamen:</td>
<%          //if(cDscCategorias.length()==0){
%>          	
<%          //}else{
%>          	<%=cDscCategorias %>
<%          //}
            if(clsConfig.getLPagina(cCatalogo)){
%>          	<!-- <td><a class="EAncla" href="javascript:fIrConsultaSintomaRama('<%=vDato2.getICveServicio()%>')">Detalle</a></td>-->
				<td><a class="EAncla" href="javascript:fVerExamen(<%=vDato2.getICveExpediente()%>,<%=vDato2.getINumExamen()%>,<%=vDato2.getICveServicio()%>,<%=request.getParameter("hdICveProceso")%>)">Detalle</a></td>
<%          }
%>        </tr>
        <tr class="EEtiqueta"><td align="left" colspan="7">Nota M&eacute;dica:</td></tr>
        <tr><td class="ETabla" colspan="7"><%=vDato2.getCNotaMedica()!=null?vDato2.getCNotaMedica():"&nbsp;"%></td></tr>
<%      }
    }
    
       



        if(lMuestraBotones){
%>        <tr><td class="ETablaC" colspan="7">
          <!-- <a class="EAncla" href="javascript:fIrCatalogo('pg070105040.jsp')">Dictaminar</a>&nbsp; -->
			<a class="EAncla"
			href="javascript:estaDictaminado('<%=request.getParameter("hdICveExpediente") != null
			? request.getParameter("hdICveExpediente")
			: ""%>','<%=request.getParameter("hdINumExamen") != null
			? request.getParameter("hdINumExamen")
			: ""%>');">Dictaminar</a>
          
          <a class="EAncla" href="javascript:fIrCatalogo('pg070104033.jsp')">Interconsulta</a>
        </td></tr>
<%      }
      }else{
%>        <tr class="EResalta"><td colspan="7" align="center">Datos no disponibles</td></tr>
<%    }
%>      </table>
    </td></tr>
<%  }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
	<SCRIPT LANGUAGE="JavaScript">
		var ld = (document.all);
		var ns4 = document.layers;
		var ns6 = document.getElementById && !document.all;
		var ie4 = document.all;
		if (ns4)
			ld = document.loading;
		else if (ns6)
			ld = document.getElementById("loading").style;
		else if (ie4)
			ld = document.all.loading.style;
		function Loaded() {
			if (ns4) {
				ld.visibility = "collapse";
			} else if (ns6 || ie4)
				ld.display = "none";
		}
		function Loading() {
			if (ns4) {
				ld.visibility = "visible";
			} else if (ns6 || ie4)
				ld.display = "block";
		}
		Loaded();
	</script>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
