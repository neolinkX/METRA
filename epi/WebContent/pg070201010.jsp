
<%/**
 * Title: pg070201010
 * Description:
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>


<html onMouseOver="mostrardiv();">
<%
  pg070201010CFG  clsConfig = new pg070201010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070201010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070201010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070201010.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  if(request.getParameter("cFlag")!=null && request.getParameter("cFlag").length() >0){
     cUpdStatus  = "SaveCancelOnly";
  }else{
     cUpdStatus  = "Hidden";
  }
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  /*
   * Calcula Fecha Actual
   */
  java.util.Date today = new java.util.Date();
  java.sql.Date defFecha = new java.sql.Date(today.getTime());
  java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
  String dFechaActual = "";
  TFechas dtFecha = new TFechas();
  dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha,"/");
  TDEMOExamen dEMOExamen = new TDEMOExamen();
  TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
%>
<SCRIPT LANGUAGE="JavaScript"
	SRC='<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>'
	type="text/javascript"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC='<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>'
	type="text/javascript"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC='<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>'
	type="text/javascript"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC='<%=vParametros.getPropEspecifica("RutaFuncs")+"selEmp.js"%>'
	type="text/javascript"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';
function goTo_nextPage(){
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    <%
    int iNE = 0;
    if (request.getParameter("iCvePersonal") != null && request.getParameter("iCvePersonal").length()>0){
       //System.out.println("request.getParameter(iCveExpediente) ==>> "+request.getParameter("iCveExpediente"));
       iNE = dEMOExamen.getINumExamenMaximo(Integer.parseInt(request.getParameter("iCvePersonal").toString()));
    }else{
       iNE = 0;
    }
    %>
       form.iNumExamen.value="<%=iNE%>";
    <%
    if(request.getParameter("iCvePersonal")!= null && request.getParameter("iCvePersonal").length()>0){
    %>
       form.iCvePersonal.value = "<%=request.getParameter("iCvePersonal")%>";
       form.cCvePersonal.value = "<%=request.getParameter("iCvePersonal")%>";
    <%
    }

    Object objGrupo;
    objGrupo = clsConfig.getInputsExamGrupo();
    TVEXPExamGrupo vEXPExamGrupo = (TVEXPExamGrupo) objGrupo;
    if (request.getParameter("iCveUniMed")!= null && request.getParameter("iCveUniMed").length()>0){
    %>
       form.iCveUniMed.value ='<%=request.getParameter("iCveUniMed")%>';
    <%
    }
    if (request.getParameter("iCveModulo")!= null && request.getParameter("iCveModulo").length()>0){
    %>
       form.iCveModulo.value = '<%=request.getParameter("iCveModulo")%>';
    <%
    }
    if (request.getParameter("iCveProceso")!= null && request.getParameter("iCveProceso").length()>0){
    %>
       form.iCveProceso.value = '<%=request.getParameter("iCveProceso")%>';
    <%
    }
    %>
    form.iCvePerfil.value = <%=vEXPExamGrupo.getICvePerfil()%>
    form.action = 'pg070201011.jsp';
    form.submit();
}

function sLic(cVencida){
       form = document.forms[0];
       form.target =  "FRMDatos";
       if (cVencida == "No"){
          form.cLicencia.disabled=true;
          form.lLicVencida[0].disabled=true;
          form.lLicVencida[1].disabled=true;
          form.dtVenceLic.disabled=true;
          form.dtVenceLic.value="";
       }else{
          form.cLicencia.disabled=false;
          form.lLicVencida[0].disabled=false;
          form.lLicVencida[1].disabled=false;
          form.dtVenceLic.disabled=false;
          form.dtVenceLic.value="<%=dFechaActual%>";
       }
}


function fSelected(iCvePersonal){
  alert("Identificar " + iCvePersonal);
    form = document.forms[0];
    form.target =  "FRMDatos";
    form.iCvePersonal.value = iCvePersonal;
    form.iCveExpediente.value = iCvePersonal;
    form.hdType.value = "P";
    form.cFlag.value = "T";
    form.submit();
}


function mostrardiv() {
    form = document.forms[0];
     if(form.iCveUsuAplicoEMO.value == -2){
        div = document.getElementById('cMedDic');
        div.style.display = '';
        div = document.getElementById('cMedDic');
        div.style.display = '';
	}else{
	    div = document.getElementById('cMedDic');
	    div.style.display='none';
	    div = document.getElementById('cMedDic');
	    div.style.display='none';
	}
     if(form.iCveUsuAplicoEMO.value == -2){
         div = document.getElementById('cMedDic2');
         div.style.display = '';
         div = document.getElementById('cMedDic2');
         div.style.display = '';
	 }else{
	     div = document.getElementById('cMedDic2');
	     div.style.display='none';
	     div = document.getElementById('cMedDic2');
	     div.style.display='none';
	 }
	 if(form.iCveUsuAplicoEMO.value == -2){
         div = document.getElementById('cCedula');
         div.style.display = '';
         div = document.getElementById('cCedula');
         div.style.display = '';
	 }else{
	     div = document.getElementById('cCedula');
	     div.style.display='none';
	     div = document.getElementById('cCedula');
	     div.style.display='none';
	 }
	 if(form.iCveUsuAplicoEMO.value == -2){
         div = document.getElementById('cCedula2');
         div.style.display = '';
         div = document.getElementById('cCedula2');
         div.style.display = '';
	 }else{
	     div = document.getElementById('cCedula2');
	     div.style.display='none';
	     div = document.getElementById('cCedula2');
	     div.style.display='none';
	 }
	 
}

     

</SCRIPT>

<%//Funciones para introducir la hora %>
<script type="text/javascript"
	src='<%=vParametros.getPropEspecifica("RutaFuncs")+"jquery.min.js"%>'></script>
<link rel="Stylesheet" media="screen"
	href='<%= vParametros.getPropEspecifica("RutaCSS")+"time/ui.core.css"%>' />
<link rel="Stylesheet" media="screen"
	href='<%= vParametros.getPropEspecifica("RutaCSS")+"time/ui.timepickr.css"%>' />
<script type="text/javascript"
	src='<%=vParametros.getPropEspecifica("RutaFuncs")+"time/jquery.js"%>'></script>
<script type="text/javascript"
	src='<%=vParametros.getPropEspecifica("RutaFuncs")+"time/jquery.utils.js"%>'></script>
<script type="text/javascript"
	src='<%=vParametros.getPropEspecifica("RutaFuncs")+"time/jquery.strings.js"%>'></script>
<script type="text/javascript"
	src='<%=vParametros.getPropEspecifica("RutaFuncs")+"time/jquery.anchorHandler.js"%>'></script>
<script type="text/javascript"
	src='<%=vParametros.getPropEspecifica("RutaFuncs")+"time/jquery.ui.all.js"%>'></script>
<script type="text/javascript"
	src='<%=vParametros.getPropEspecifica("RutaFuncs")+"time/ui.timepickr.js"%>'></script>
<script type="text/javascript">
		$(document).ready(function(){	
			$("#clockpick2").click(function() {
					var image = $("#clockpick2");
					image.attr("src", "http://aplicaciones9.sct.gob.mx/imagenes/medprev/img/medprev/trans.png");
					$("#demo-1").timepickr().focus();
					$("#demo-1").focus();
			});
		});
		</script>


<head>
<meta name="Autor"
	content='<%= vParametros.getPropEspecifica("Autor") %>'>
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracterï¿½sticas generales de las pï¿½ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet"
	href='<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>'
	TYPE="text/css">
<body bgcolor='<%= vParametros.getPropEspecifica("ColorFondoPagina") %>'
	topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
	<form method="<%= vEntorno.getMetodoForm() %>"
		action="<%= vEntorno.getActionForm() %>">
		<input type="hidden" name="hdLCondicion"
			value='<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>'>
		<input type="hidden" name="hdLOrdenar"
			value='<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>'>
		<input type="hidden" name="FILNumReng"
			value='<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>'>
		<input type="hidden" name="cFlag" value="">
		<%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
		<input type="hidden" name="hdRowNum" value="<%=cPosicion%>"> <input
			type="hidden" name="hdCampoClave" value="<%=cClave%>"> <input
			type="hidden" name="hdCPropiedad" value=""> <input
			type="hidden" name="hdType" value=""> <input type="hidden"
			name="lIniciado" value=""> <input type="hidden"
			name="lConcluido" value=""> <input type="hidden"
			name="lAplicado" value=""> <input type="hidden"
			name="lDictamen" value=""> <input type="hidden"
			name="iCvePerfil" value=""> <input type="hidden"
			name="iNumExamen" value=""> <input type="hidden"
			name="ramaInicial" value="0"> <input type="hidden"
			name="cCvePersonal" value="0"> <input type="hidden"
			name="iCveServicio"> <input type="hidden"
			name="hdiCveEmpresa">
		<%  if (request.getParameter("iCvePersonal") != null){
%>
		<input type="hidden" name="iCvePersonal"
			value='<%=request.getParameter("iCvePersonal").toString()%>'>
		<%
    }else{
%>
		<input type="hidden" name="iCvePersonal" value="">
		<%
    }
if (request.getParameter("iCveExpediente") != null){
	if(!request.getParameter("iCveExpediente").equals("")){
	%>
		<input type="hidden" name="iCveExpediente"
			value='<%=request.getParameter("iCvePersonal").toString()%>'>
		<%
	}else{
	%>
		<input type="hidden" name="iCveExpediente" value="">
		<%
    }
}else{
%>
		<input type="hidden" name="iCveExpediente" value="">
		<%
    }
%>
		<table
			background='<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg'
			width="100%" height="100%">
			<% if(clsConfig.getAccesoValido()){
       TVPERDatos vPerDatos = null;
      // System.out.println("hdType ==>>"+request.getParameter("hdType"));
       if (request.getParameter("hdType") != null){
          if (request.getParameter("hdType").toString().equalsIgnoreCase("P")){
             vPerDatos=clsConfig.findUser();
          }
       }
%>

			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><input type="hidden" name="hdBoton" value=""></td>
				<td valign="top">
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<% // Inicio de Datos %>
						<tr>
							<td colspan="6" class="ETablaT">Inicio del Examen</td>
						</tr>
						<tr>
							<td class="EEtiqueta">Unidad M&eacute;dica:</td>
							<td class="ETabla">
								<%  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                  %> <input type="hidden" name="UserId"
								value="<%=vUsuario.getICveusuario()%>"> <%
                                      Vector vProceso = new Vector();
                                      vProceso = vUsuario.getVUnidades();
                                      int iCveUniMed = 0;
                                      TVGRLUMUsuario vGRLUMUsu;
                                      if (request.getParameter("iCveUniMed") != null){
                                           out.print(vEti.SelectOneRowSinTD("iCveUniMed","fillNext();",clsConfig.getUniMedsValidas(vParametros.getPropEspecifica("EMOProceso")),"iCveUniMed","cDscUniMed",request,request.getParameter("iCveUniMed").toString(),true));
                                           iCveUniMed = Integer.parseInt(request.getParameter("iCveUniMed").toString());
                                      }else{
                                           out.print(vEti.SelectOneRowSinTD("iCveUniMed","fillNext();",clsConfig.getUniMedsValidas(vParametros.getPropEspecifica("EMOProceso")),"iCveUniMed","cDscUniMed",request,"0",true));
                                      }
                                  %>
							</td>
							<td class="EEtiqueta">Proceso:</td>
							<td class="ETabla" colspan="3"><select name="iCveProceso"
								size="1" onChange="">
									<%
                                      if (request.getParameter("iCveUniMed") != null){
                                         iCveUniMed = Integer.parseInt(request.getParameter("iCveUniMed").toString());
                                      }else{
                                         iCveUniMed = Integer.parseInt(vParametros.getPropEspecifica("EMOProceso"));
                                      }
                                      int iCveProceso=0;
                                      if(vProceso.size() >0 ){
                                        out.print("<option value=0>Seleccione...</option>");
                                        for (int w = 0;w<vProceso.size(); w++){
                                           vGRLUMUsu = (TVGRLUMUsuario) vProceso.get(w);
                                           if (vGRLUMUsu.getICveUniMed() == iCveUniMed){
                                              if (request.getParameter("iCveProceso")!=null&&vParametros.getPropEspecifica("EMOProceso").compareToIgnoreCase(new Integer(vGRLUMUsu.getICveProceso()).toString())==0){
                                                 out.print("<option value =" + vGRLUMUsu.getICveProceso() + " Selected>" + vGRLUMUsu.getCDscProceso() + "</option>");
                                                 iCveProceso = Integer.parseInt(request.getParameter("iCveProceso").toString());
                                             }else{
                                                 out.print("<option value =" + vGRLUMUsu.getICveProceso() + ">" + vGRLUMUsu.getCDscProceso() + "</option>");
                                             }
                                           }
                                         }
                                      }else{
                                         out.print("<option value=0>No Existen Datos Relacionados...</option>");
                                      }
                                    %>
							</select></td>
						</tr>
						<tr>
							<td class="EEtiqueta">Modulo:</td>
							<td class="ETabla">
								<%

                                        TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
                                        String cUniMed =  "" + request.getParameter("iCveUniMed");
                                        if (cUniMed.compareTo("null") == 0)
                                           cUniMed = "0";
                                        Vector vGModulo = new Vector();
                                        String cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                                                         " and GRLUsuMedicos.iCveUniMed =  " + cUniMed +
                                                         " and GRLUsuMedicos.iCveProceso = " + vParametros.getPropEspecifica("EMOProceso")  ;


                                        vGModulo = dGRLUSUMedicos.FindModulos(cFiltro);
                                     %> <select name="iCveModulo"
								size="1" onChange="fillNext();">
									<%
                                    int iCveModulo = 0;
                                    if (vGModulo.size() > 0){
                                     out.print("<option value=0>Seleccione...</option>");
                                     for (int x = 0;x<vGModulo.size(); x++){
                                          vGRLUSUMedicos = (TVGRLUSUMedicos) vGModulo.get(x);
                                          if (request.getParameter("iCveModulo")!=null&&request.getParameter("iCveModulo").compareToIgnoreCase(new Integer(vGRLUSUMedicos.getICveModulo()).toString())==0){
                                             out.print("<option value =" + vGRLUSUMedicos.getICveModulo() + " Selected>" + vGRLUSUMedicos.getCDscModulo() + "</option>");
                                             iCveModulo = Integer.parseInt(request.getParameter("iCveModulo").toString());
                                          }else{
                                             out.print("<option value =" + vGRLUSUMedicos.getICveModulo() + ">" + vGRLUSUMedicos.getCDscModulo() + "</option>");
                                          }
                                       }
                                    }else{
                                       out.print("<option value=0>No Existen Datos Relacionados...</option>");
                                    }
                                    %>
							</select>
							</td>
							<td class="EEtiqueta">Modo de Transporte:</td>
							<td class="ETabla">
								<%
                                        String cOrden="";
                                        TDGRLProcesoMDOT dGRLProcesoMDOT = new TDGRLProcesoMDOT();
                                        TVGRLProcesoMDOT vGRLProcesoMDOT = new TVGRLProcesoMDOT();
                                        Vector vGProcesoMDOT = new Vector();
                                        String cFiltroT = "";
                                        if (request.getParameter("iCveUniMed") != null){
                                            cFiltroT = " Where iCveUniMed = "+request.getParameter("iCveUniMed");
                                        }else{
                                            cFiltroT = " Where iCveUniMed = 0";
                                        }
                                        if (request.getParameter("iCveModulo") != null){
                                            cFiltroT += " And iCveModulo = "+request.getParameter("iCveModulo");
                                        }else{
                                            cFiltroT += " And iCveModulo = 0";
                                        }
                                        if (request.getParameter("iCveProceso") != null){
                                            cFiltroT += " And iCveProceso = "+request.getParameter("iCveProceso");
                                        }else{
                                            cFiltroT += " And iCveProceso = 0";
                                        }
                                        cFiltroT +=    " And    a.iCveMdoTrans = b.iCveMdoTrans ";
                                        cOrden=" Order by a.iCveUniMed ";
                                        vGProcesoMDOT = dGRLProcesoMDOT.FindByUMMDPR(cFiltroT,cOrden);
                                     %> <select name="iCveMdoTrans"
								size="1" onChange="fillNext();">
									<%
                                    if (vGProcesoMDOT.size() > 0){
                                     out.print("<option value=0>Seleccione...</option>");
                                     for (int y = 0;y<vGProcesoMDOT.size(); y++){
                                          vGRLProcesoMDOT = (TVGRLProcesoMDOT) vGProcesoMDOT.get(y);
                                          if (request.getParameter("iCveMdoTrans")!=null&&request.getParameter("iCveMdoTrans").compareToIgnoreCase(new Integer(vGRLProcesoMDOT.getICveMdoTrans()).toString())==0){
                                             out.print("<option value =" + vGRLProcesoMDOT.getICveMdoTrans() + " Selected>" + vGRLProcesoMDOT.getCDscMdoTrans()+ "</option>");
                                          }else{
                                             out.print("<option value =" + vGRLProcesoMDOT.getICveMdoTrans() + ">" + vGRLProcesoMDOT.getCDscMdoTrans() + "</option>");
                                          }
                                       }
                                    }else{
                                       out.print("<option value=0>No Existen Datos Relacionados...</option>");
                                    }
                                     
                                    %>
							</select>
							</td>
						</tr>
						<tr>
							<td class="EEtiqueta">SubModulo:</td>
							<td class="ETabla" colspan="3">
								<%
										TDGRLSUBModulo dSubModulo = new TDGRLSUBModulo(); 
                              			String cModulo = ""; 
                              			TVGRLUSUMedicos vGRLUSUMedicos2 = new TVGRLUSUMedicos();
                              			TVGRLSUBModulo vGRLSUBModulo = new TVGRLSUBModulo();
                              			Vector vGSubModulo = new Vector();
                              			cModulo =  "" + request.getParameter("iCveModulo");

                                        if (cModulo.compareTo("null") == 0)
                                        	cModulo = "0";
                                         cFiltro = " where iCveUniMed =  " + cUniMed +
                                        		   " and iCveModulo =  " + cModulo +
                                                   " and lVigente = 1";

                                         vGSubModulo = dSubModulo.FindByAll(cFiltro);

                                     %> <select name="iCveSubModulo"
								size="1">
									<%
                                    int iCveSubModulo = 0;
                                    if (vGSubModulo.size() > 0){
                                     out.print("<option value=0>Seleccione...</option>");
                                     for (int x = 0;x<vGSubModulo.size(); x++){
                                          //vGRLUSUMedicos2 = (TVGRLUSUMedicos) vGSubModulo.get(x);
                                          vGRLSUBModulo = (TVGRLSUBModulo) vGSubModulo.get(x);
                                          if (request.getParameter("iCveSubModulo")!=null&&request.getParameter("iCveSubModulo").compareToIgnoreCase(new Integer(vGRLSUBModulo.getICveSubModulo()).toString())==0){
                                             out.print("<option value =" + vGRLSUBModulo.getICveSubModulo() + " Selected>" + vGRLSUBModulo.getCDscModulo() + "</option>");
                                             iCveSubModulo = Integer.parseInt(request.getParameter("iCveSubModulo").toString());
                                          }else{
                                             out.print("<option value =" + vGRLSUBModulo.getICveSubModulo() + ">" + vGRLSUBModulo.getCDscModulo() + "</option>");
                                          }
                                       }
                                    }else{
                                       out.print("<option value=0>No Existen Datos Relacionados...</option>");
                                    }
                                   
                                    %>
							</select>
							</td>
						</tr>
						<%
                                 out.print("<tr>");
                                 out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha de aplicación<br>del examen:", "ECampo", "text", 10, 10,1,"dtSolicitado", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                                 out.print("<td class=\"EEtiqueta\">Hora de aplicación<br>del examen:</td>");
                                 out.print("<td class=\"ETabla\" colspan = \"3\">");
                                 String horas = "02:30";
                                 if (request.getParameter("horas") != null){
                                	 horas = request.getParameter("horas");
                                 }
                                 
                                 %>
						<div id="splash">
							<div id="demo">
								<div id="d-demo-wrapper-1" class="demo-wrapper">
									<input name="horas" id="demo-1" type="text" value="<%=horas%>"
										class="demo"> <img
										src="http://aplicaciones9.sct.gob.mx/imagenes/medprev/img/medprev/clock.gif"
										id="clockpick2" alt="">
								</div>
							</div>
							<ul id="demo-nav">
								<li style="list-style: none; padding: 8px 0;"><div
										id="switcher"></div></li>
							</ul>
						</div>
						<%
                                 out.print("</td>");
                                 out.print("</tr>");
                                 out.print("<tr>");
                                 out.print("<td class=\"EEtiqueta\">Personal:</td>");
                                 out.print("<td ColSpan=\"6\">");
                                 out.print(vEti.clsAnclaTexto("EAncla","Buscar Personal","JavaScript:fSelExp();", "Buscar Personal",""));
                                 out.print("</td>");
                                 out.print("</tr>");
                              %>
					</table>&nbsp; <%
     if(request.getParameter("hdBoton")!= null){
       if(request.getParameter("hdBoton").equalsIgnoreCase("Guardar")){
         %> <script type="text/javascript">
             document.write("<tr>");
             document.write("<td class='EEtiqueta'>");
             document.write("Espere...");
             document.write("</td>");
             document.write("</tr>");
             goTo_nextPage();
          </script> <%
       }
     }
%> <%
                        if(vPerDatos!=null){
                                     
                        //Validar si el usuario no tiene un examen de revaloracion pendiente.                                     
                           TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
                           TVDICTAMEN tvDICTAMEN = new TVDICTAMEN();
                           Vector vctvDICTAMEN = new Vector();
                          String cWhere= " Where EA.icveexpediente = EC.icveexpediente and "+
                                         "EA.inumexamen = EC.inumexamen and "+
                                         "EA.icveproceso = GRLP.icveproceso and "+
                                         "EC.icvemotivo = GRLM.icvemotivo and "+
                                         "EA.ldictaminado = 1 and "+
                                         "EA.icveexpediente = "+request.getParameter("iCvePersonal")+" "+
                                         "order by EA.inumexamen desc";
                                   
                           vctvDICTAMEN = dEXPExamCat.FindByDictamen(cWhere);
                           //System.out.println(vctvDICTAMEN.size());
                           //String PUEMED = "";
                           int PUEMED = 0;
                           int    DUEMED = 2;
                           if(vctvDICTAMEN.size() > 0){
                             tvDICTAMEN = (TVDICTAMEN) vctvDICTAMEN.get(0);
                             PUEMED = tvDICTAMEN.getICveProceso();
                             DUEMED = tvDICTAMEN.getLDictamen();
                            }

////////////////////////////////////////////////////////////
              //VALIDANDO SI ES UN EXPEDIENTE INHABILITADO

                        int Inhabilitado = 0;
                        String fecha1 = "";
                        String fecha2 = "";
                        int Activo = 0;
                        Vector vcInhabilita = new Vector();
                        TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
                        TVMEDInhabilita vMEDInhabilita;
                        //TFechas dtFecha = new TFechas();


                         try{
                             String cCondicion = "iCvePersonal = "+ vPerDatos.getICvePersonal();
                             vcInhabilita = dMEDInhabilita.FindByAll(cCondicion);
                             fecha1 = dtFecha.getFechaYYYYMMDD(dtFecha.TodaySQL(),"-").toString();

                             for(int j=0;j<vcInhabilita.size();j++){
                                    Inhabilitado = 1;
                                    vMEDInhabilita = (TVMEDInhabilita) vcInhabilita.get(j);
                                    fecha2 = vMEDInhabilita.getFinInh().toString();
                             }

                             if(Inhabilitado == 1){
                            fecha1 = fecha1 + "-";
                                   int dia1;
                                   int mes1;
                                   int anno1;
                                   StringTokenizer solDatos = new StringTokenizer(fecha1, "-");
                                   anno1 = Integer.parseInt(solDatos.nextToken()+"".toString());
                                   mes1 = Integer.parseInt(solDatos.nextToken()+"".toString());
                                   dia1 = Integer.parseInt(solDatos.nextToken()+"".toString());
                            fecha2 = fecha2 + "-";
                                   int dia2;
                                   int mes2;
                                   int anno2;
                                   StringTokenizer solDatos2 = new StringTokenizer(fecha2, "-");
                                   anno2 = Integer.parseInt(solDatos2.nextToken()+"".toString());
                                   mes2 = Integer.parseInt(solDatos2.nextToken()+"".toString());
                                   dia2 = Integer.parseInt(solDatos2.nextToken()+"".toString());
                              
                                   if(anno2 > anno1){
                                      Activo = 1;
                                      //System.out.println("anno2 > anno1");
                                   }
                                   if(anno2 == anno1){
                                       //System.out.println("anno2 == anno1");
                                       if(mes2 > mes1){
                                             Activo = 1;
                                             //System.out.println("mes2 >= mes1");
                                           }
                                       if(mes2 == mes1){
                                           //System.out.println("mes2 == mes1");
                                               if(dia2 >= dia1){
                                                   Activo = 1;
                                                   //System.out.println("dia2 >= dia1");
                                               }
                                           }
                                   }

                                   //System.out.println("Activo = "+Activo);

                            /*
                                   if(anno2 >= anno1){
                                       if(mes2 >= mes1){
                                           if(dia2 >= dia1){
                                            Activo = 1;
                                            System.out.println("Expediente Inhactivo");
                                           }
                                       }
                                   }*/
                              // out.print("<td>"+vEti.clsAnclaTexto("ETabla",""+vPerDatos.getICvePersonal(),"javascript:fSelected('"+vPerDatos.getICvePersonal()+"',"+vPerDatos.getICveExpediente()+","+vPerDatos.getINumExamen()+",'"+ vPerDatos.getCNombreCompleto() + "');fAlerta();","Selected")+"</td>");
                           }
                         }catch(Exception e){
                                 vcInhabilita = new Vector();
                                 e.printStackTrace();
                               }


////////////////////////////////////////////////////////////

                            //System.out.println("|"+PUEMED+"|");
                            //System.out.println(DUEMED);
                      //Sentencia revaloracion
                       //     if(vctvDICTAMEN.size() > 0 && tvDICTAMEN.getCDSProceso().equals("EXAMEN MEDICO EN OPERACION") && tvDICTAMEN.getLDictamen()== 0){
                        if(Activo == 1){
                           %>
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<tr>
							<td colspan="9" class="ETablaT">Personal Registrado para el
								Examen</td>
						</tr>
						<tr>
							<td colspan="9" class="ECampo">No se puede realizar un
								examen m&eacute;dico en operaci&oacute;n para <%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%>
								con n&uacute;mero de expediente m&eacute;dico <%=vPerDatos.getICveExpediente()%>,
								debido a que se encuentra Inhabilitado para realizar
								Ex&aacute;menes M&eacute;dicos.
							</td>
						</tr>
					</table> <%
                        } else{
                          if(vctvDICTAMEN.size() > 0 && PUEMED <= 2 && DUEMED == 0){
                               //System.out.println("Esta persona en su ultimo examen Emo fue no apta");
                               //System.out.println(tvDICTAMEN.getCDSProceso());
                               //System.out.println(tvDICTAMEN.getLDictamen());
%>
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<tr>
							<td colspan="9" class="ETablaT">Personal Registrado para el
								Examen</td>
						</tr>
						<tr>
							<td colspan="9" class="ECampo">No se puede realizar un
								examen m&eacute;dico en operaci&oacute;n para <%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%>
								con n&uacute;mero de expediente m&eacute;dico <%=vPerDatos.getICveExpediente()%>,
								debido a que se le practico un <%=tvDICTAMEN.getCDSProceso()%>
								el d&iacute;a <%=tvDICTAMEN.getDTSolicitado()%>, en el cual
								resulto NO APTO, es necesario se presente a un examen de
								revaloraci&oacute;n en una Unidad M&eacute;dica.
							</td>
						</tr>
					</table> <%
                      }
                      else{                                     
                           int iLastExam = 0;
                           TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
                           TVAUXEMO vAUXEMO = new TVAUXEMO();
                           Vector vcLastExam = new Vector();
                           vcLastExam = dEXPExamAplica.FindLastExam(request.getParameter("iCvePersonal"),vParametros.getPropEspecifica("EMOProceso"));
                           if(vcLastExam.size() > 0){
                             vAUXEMO = (TVAUXEMO) vcLastExam.get(0);
                               iLastExam = 1;
                            }
                        %>
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<% // Inicio de Datos %>
						<tr>
							<td colspan="9" class="ETablaT">Datos del Examen</td>
						</tr>
						<tr>
							<td class="EEtiqueta">Captura del examen:</td>
							<td ColSpan="7"><select name="iCveCapturaDelExamen" size="1"
								onChange="">
									<option value="0">Seleccione...</option>
									<option value="1">EN TIEMPO REAL</option>
									<option value="2">POSTERIOR A LA REALIZACI&Oacute;N
										DEL EXAMEN</option>
							</select></td>
						</tr>
						<tr>
							<%                               
                               out.println(vEti.Texto("EEtiqueta","Médico Dictaminador:"));


                             if (request.getParameter("iCveUniMed") == null){
                                out.println("<td>");
                                out.println("<SELECT NAME='iCveUsuAplicoEMO' SIZE='1' onChange=''>");
                                out.println("</SELECT>");
                                out.println("</td>");
                             }
                             else{
                                TVUsuario vUsuarioAplica = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                Vector vcPersonal;
                                //int iCveProceso = Integer.parseInt(request.getParameter("iCveProceso"));
                                int iUniMed = 0;
                                int iModulo = 0;

                                if(request.getParameter("iCveUniMed") == null){
                                   vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                   if(vcPersonal.size() != 0){
                                       iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                   }
                                }else{
                                   iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
                             }
                             iModulo = Integer.parseInt(request.getParameter("iCveModulo"),10);

                             vcPersonal = new TDGRLUMUsuario().getUniMedMod(iCveProceso,iUniMed,iModulo);

                             new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
                             out.println("<td>");
                             out.print(vEti.SelectOneRowSinTDOtro("iCveUsuAplicoEMO", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "", true));
                             out.println("</td>");
                             }
                             out.println("</tr>");
                             out.println("<tr>");
                             out.println(vEti.EtiCampoCSOculto("EEtiqueta","Nombre del Médico Dictaminador:","ECampo","text",80,100,7,"cMedDic","",0,"","fMayus(this)",false,true,true,request));
                             out.println("</tr>");
                             out.println("<tr>");
                             out.println(vEti.EtiCampoCSOculto("EEtiqueta","C&eacute;dula:","ECampo","text",20,30,7,"cCedula","",0,"","fMayus(this)",false,true,true,request));
                             out.println("</tr>");
                             out.println("<tr>");
                             out.println(vEti.EtiCampoCS("EEtiqueta","Folio:","ECampo","text",10,7,7,"iCveFolio","",0,"","fMayus(this);",false,true,true,request));
                             out.println("</tr>");
                             out.println("<tr>");
                             
                             %>


						</tr>
						<tr>
							<td colspan="9" class="ETablaT">Personal Registrado para el
								Examen</td>
						</tr>
						<tr>
							<td class="EEtiqueta">Expediente:</td>
							<td class="ECampo"><%=vPerDatos.getICveExpediente()%></td>
							<td class="EEtiqueta">R.F.C.:</td>
							<td class="ECampo"><%=vPerDatos.getCRFC()%></td>
							<td class="EEtiqueta">C.U.R.P.:</td>
							<td class="ECampo" ColSpan="3"><%=vPerDatos.getCCURP()%></td>
							<input type="hidden" name="cGenero"
								value="<%=vPerDatos.getCSexo()%>">
						</tr>
						<tr>
							<td class="EEtiqueta">Nombre:</td>
							<td class="ECampo" colspan="9"><%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%></td>
						</tr>
						<tr>
							<%
                               out.println("<td class=\"EEtiqueta\">¿Licencia?:</td>");
                               out.println("<td class=\"EEtiqueta\">S&iacute;");
                               out.println("<input type=\"radio\" name=\"lSinLicencia\" value=\"0\" onClick=\"sLic('Si');\" checked>");
                               out.println("</td>");
                               out.println("<td class=\"EEtiqueta\">No");
                               out.println("<input type=\"radio\" name=\"lSinLicencia\" value=\"1\" onClick=\"sLic('No');\">");
                               out.println("</td>");
                               if (iLastExam == 0)
                                  out.println(vEti.EtiCampoCS("EEtiqueta","No. de Licencia:","ECampo","text",15,20,7,"cLicencia","",0,"","",false,true,true,request));
                               else
                                  out.println(vEti.EtiCampoCS("EEtiqueta","No. de Licencia:","ECampo","text",15,20,7,"cLicencia",vAUXEMO.getCLicencia(),0,"","",false,true,true,request));
                               out.println("</tr>");
                               /*out.println("<tr>");
                               out.println("<td class=\"EEtiqueta\">¿Licencia Vencida?:</td>");
                               out.println("<td class=\"EEtiqueta\">S&iacute;");
                               out.println("<input type=\"radio\" name=\"lLicVencida\" value=\"1\">");
                               out.println("</td>");
                               out.println("<td class=\"EEtiqueta\">No");
                               out.println("<input type=\"radio\" name=\"lLicVencida\" value=\"0\" checked>");
                               out.println("</td>");
                               out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha de Vencimiento:", "ECampo", "text", 10, 10,6,"dtVenceLic","", 0, "", "fValFecha(this.value);", false, true,true, request));
                               
                               */
                               TDGRLCategoria dGRLCategoria = new TDGRLCategoria();
                               TDGRLMotivo    dGRLMotivo    = new TDGRLMotivo();
                               TDEMOMomentoAP dEMOMomentoAP = new TDEMOMomentoAP();
                               TDGRLGrupo     dGRLGrupo     = new TDGRLGrupo();
                               TVGRLCategoria vGRLCategoria = new TVGRLCategoria();
                               TVGRLMotivo    vGRLMotivo    = new TVGRLMotivo();
                               TVEMOMomentoAP vEMOMomento   = new TVEMOMomentoAP();
                               TVGRLGrupo     vGRLGrupo     = new TVGRLGrupo();
                               Vector vGCategoria = new Vector();
                               Vector vGMotivo    = new Vector();
                               Vector vEMomento   = new Vector();
                               Vector vGrupo      = new Vector();
                               String cFiltroC = "";
                               String cFiltroM = "";
                               String cFiltroMAP = "";
                               String cFiltroG = "";
                               String iCveMdoTrans = "";
                               if (request.getParameter("iCveMdoTrans") != null){
                                  cFiltroC = request.getParameter("iCveMdoTrans");
                                  iCveMdoTrans = request.getParameter("iCveMdoTrans");
                               }else{
                                  cFiltroC = "0";
                               }
                               vGCategoria = dGRLCategoria.FindByAll(cFiltroC);
                               %>
						</tr>
						<tr>
							<td class="EEtiqueta">Categoria:</td>
							<td><select name="iCveCategoria" size="1"
								onChange="llenaSLT(2,this.value,<%=iCveMdoTrans%>,'',document.forms[0].iCvePuesto);">
									<%
                               String ClaveDTransporte = "";
                               ClaveDTransporte = request.getParameter("iCveMdoTrans");
                               
                               if(ClaveDTransporte.equals("2"))
                               {   out.print("<option value=0>Seleccione...</option>");
                                   out.print("<option value=7>UNICA</option>");}
                               else{

                               if (vGCategoria.size() > 0){
                                  out.print("<option value=0>Seleccione...</option>");
                                  for (int xy = 0;xy<vGCategoria.size(); xy++){
                                      vGRLCategoria = (TVGRLCategoria) vGCategoria.get(xy);
                                      if ((request.getParameter("iCveCategoria")!=null&&request.getParameter("iCveCategoria").compareToIgnoreCase(new Integer(vGRLCategoria.getICveCategoria()).toString())==0)
                                         || (iLastExam == 1 && vGRLCategoria.getICveCategoria() == vAUXEMO.getICveCategoria())){
                                         out.print("<option value =" + vGRLCategoria.getICveCategoria() + " Selected>" + vGRLCategoria.getCDscCategoria() + "</option>");
                                      }else{
                                        out.print("<option value =" + vGRLCategoria.getICveCategoria() + ">" + vGRLCategoria.getCDscCategoria() + "</option>");
                                      }

                                  }
                               }else{
                                  out.print("<option value=0>No Existen Datos Relacionados...</option>");
                               }
                                }

                                %>
							</select></td>
							<td class="EEtiqueta">Motivo:</td>
							<td ColSpan="2"><select name="iCveMotivo" size="1"
								onChange="">
									<%
                               cFiltroM = " Where iCveProceso = "+Integer.parseInt(vParametros.getPropEspecifica("EMOProceso"))+" and lactivo = 1 ";
                               vGMotivo = dGRLMotivo.FindByAll(cFiltroM);
                               if (vGMotivo.size() > 0){
                                  out.print("<option value=0>Seleccione...</option>");
                                  for (int xz = 0;xz<vGMotivo.size(); xz++){
                                      vGRLMotivo = (TVGRLMotivo) vGMotivo.get(xz);
                                      if ((request.getParameter("iCveMotivo")!=null&&request.getParameter("iCveMotivo").compareToIgnoreCase(new Integer(vGRLMotivo.getICveMotivo()).toString())==0)
                                          || (iLastExam == 1 && vGRLMotivo.getICveMotivo() == vAUXEMO.getICveMotivo())){
                                         out.print("<option value =" + vGRLMotivo.getICveMotivo() + " Selected>" + vGRLMotivo.getCDscMotivo() + "</option>");
                                      }else{
                                         out.print("<option value =" + vGRLMotivo.getICveMotivo() + ">" + vGRLMotivo.getCDscMotivo() + "</option>");
                                      }
                                  }
                               }else{
                                  out.print("<option value=0>No Existen Datos Relacionados...</option>");
                               }
                               %> 
							</select></td>
							<td class="EEtiqueta">Momento:</td>
							<td ColSpan="3"><select name="iCveMomentoAP" size="1"
								onChange="">
									<%
                               cFiltroMAP = " where lactivo = 1 ";
                               vEMomento = dEMOMomentoAP.FindByAll(cFiltroMAP);
                               if (vEMomento.size() > 0){
                                  out.print("<option value=0>Seleccione...</option>");
                                  for (int xw = 0;xw<vEMomento.size(); xw++){
                                      vEMOMomento = (TVEMOMomentoAP) vEMomento.get(xw);
                                      if ((request.getParameter("iCveMomentoAP")!=null&&request.getParameter("iCveMomentoAP").compareToIgnoreCase(new Integer(vEMOMomento.getICveMomentoAP()).toString())==0)
                                         || (iLastExam == 1 && vEMOMomento.getICveMomentoAP() == vAUXEMO.getICveMomentoAP())){
                                         out.print("<option value =" + vEMOMomento.getICveMomentoAP() + " Selected>" + vEMOMomento.getCDscBreve() + "</option>");
                                      }else{
                                         out.print("<option value =" + vEMOMomento.getICveMomentoAP() + ">" +vEMOMomento.getCDscBreve() + "</option>");
                                      }
                                  }
                               }else{
                                  out.print("<option value=0>No Existen Datos Relacionados...</option>");
                               }
                               %>
							</select></td>
						</tr>
						<tr>
							<%
                               if (request.getParameter("iCveMdoTrans") != null){
                                  cFiltroG = " Where iCveMdoTrans = "+request.getParameter("iCveMdoTrans");
                               }else{
                                  cFiltroG = " Where iCveMdoTrans=0";
                               }
                               vGrupo = dGRLGrupo.findByAll(cFiltroG);
                               %>
						</tr>
						<tr>
							<td class="EEtiqueta">Grupo:</td>
							<td><select name="iCveGrupo" size="1">
									<%
                               if (vGrupo.size() > 0){
                                  out.print("<option value=0>Seleccione...</option>");
                                  for (int xa = 0;xa<vGrupo.size(); xa++){
                                      vGRLGrupo = (TVGRLGrupo) vGrupo.get(xa);
                                      if ((request.getParameter("iCveGrupo")!=null&&request.getParameter("iCveGrupo").compareToIgnoreCase(new Integer(vGRLGrupo.getICveGrupo()).toString())==0)
                                        || (iLastExam == 1 && vGRLGrupo.getICveGrupo() == vAUXEMO.getICveGrupo())){
                                         out.print("<option value =" + vGRLGrupo.getICveGrupo() + " Selected>" + vGRLGrupo.getCDscGrupo() + "</option>");
                                      }else{
                                         out.print("<option value =" + vGRLGrupo.getICveGrupo() + ">" + vGRLGrupo.getCDscGrupo() + "</option>");
                                      }
                                  }
                               }else{
                                  out.print("<option value=0>No Existen Datos Relacionados...</option>");
                               }
                               %>
							</select></td>
							<%
                               out.println("<td class=\"EEtiqueta\">Puesto:</td>");
                               out.println("<td class='ECampo' ColSpan=\"7\">");
                               if (iLastExam == 0){
                                  out.println("<SELECT NAME='iCvePuesto' SIZE='1'");
                                  out.println("</SELECT>");
                               }
                               else{
                                  TVGRLPuesto vGRLPuesto = new TVGRLPuesto();
                                  TDGRLPuesto dGRLPuesto = new TDGRLPuesto();
                                  Vector vcPuesto = new Vector();
                                  //vcPuesto = dGRLPuesto.FindByAll("" + iCveMdoTrans,"" + vAUXEMO.getICveCategoria());
                                  out.print(vEti.SelectOneRowSinTD("iCvePuesto","",dGRLPuesto.FindByAll("" + iCveMdoTrans,"" + vAUXEMO.getICveCategoria()),"iCvePuesto","cDscPuesto",request,"" + vAUXEMO.getICvePuesto(),true));
                               }
                                 out.println("</td>");
                                 out.println("</tr>");
                               %>
						</tr>
						<td colspan="9" class="ETablaT">Empresa</td>
						<%
                                 out.println("<tr>");
                                 out.println("<td class=\"EEtiqueta\">Empresa:</td>");
                                 out.println("<td colSpan=\"8\">");
                                 out.println(vEti.clsAnclaTexto("EAncla","Buscar Empresa","JavaScript:fSelEmp();", "Buscar Empresa",""));
                                 out.println("</td>");
                                 out.println("</tr>");
                                  out.println("<tr>");
                                 if (iLastExam == 0){
                                    out.println(vEti.EtiCampoCS("EEtiqueta","Empresa ID:","ECampo","text",5,15,0,"iCveNumEmpresa","",0,"","",false,false,true,request));
                                    out.println(vEti.EtiCampoCS("EEtiqueta","Empresa:","ECampo","text",50,100,7,"cNombreRS","",0,"","",false,false,true,request));
                                 }
                                 else{
                                    out.println(vEti.EtiCampoCS("EEtiqueta","Empresa ID:","ECampo","text",5,15,0,"iCveNumEmpresa","" + vAUXEMO.getICveNumEmpresa(),0,"","",false,false,true,request));
                                    out.println(vEti.EtiCampoCS("EEtiqueta","Empresa:","ECampo","text",50,100,7,"cNombreRS","" + vAUXEMO.getCDscEmpresa(),0,"","",false,false,true,request));
                                 }
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.println(vEti.EtiCampoCS("EEtiqueta","No. Econ&oacute;mico o Matr&iacute;cula del Veh&iacute;culo:","ECampo","text",15,100,8,"cMatricula","",0,"","fMayus(this);",false,true,true,request));
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 //out.println("<td class=\"EEtiqueta\" RowSpan=2>Origen:<Input Type=\"Text\" Size=\"4\" Value=\"250\" Name=\"iNoLetras\" readonly></td>");
                                 out.println("<td class=\"EEtiqueta\">Origen:<Input Type=\"Text\" Size=\"4\" Value=\"250\" Name=\"iNoLetras\" readonly></td>");
                                 //out.println("<td RowSpan=2>");
                                 out.println("<td>");
                                 out.println("<textarea cols=\"20\" rows=\"5\"  name=\"cOrigen\" value=\"\" onkeypress=\"fChgArea(this);\" onBlur=\"fMayus(this);\" onchange=\"fChgArea(this);\">");
                                 TDGRLModulo dModulo = new TDGRLModulo();
                                 TVGRLModulo vModulo = new TVGRLModulo();

                                 //TDGRLUniMed dUniMed = new TDGRLUniMed();
                                 //TVGRLUniMed vUniMed;
                                 Vector vcUniMed = new Vector();
                                 vcUniMed = dModulo.FindByAll(" where GRLModulo.iCveUniMed = " + request.getParameter("iCveUniMed").toString() + " and GRLModulo.iCveModulo = " + request.getParameter("iCveModulo").toString() );
                                 if (vcUniMed.size() > 0){
                                      vModulo = (TVGRLModulo) vcUniMed.get(0);
                                      out.print(vModulo.getCDscModulo());
                                 }
                                 out.println("</textarea>");
                                 out.println("</td>");
                                 out.print("<td class=\"EEtiqueta\">Pais Origen:</td>");
                                 out.print("<td>");
                                 if (request.getParameter("iCvePaisOr") != null){
                                    out.print(vEti.SelectOneRowSinTD("iCvePaisOr","llenaSLT(1,this.value,'','',document.forms[0].iCveEdoOr);",clsConfig.getPaises(),"iCvePais","cNombre",request,request.getParameter("iCvePaisOr").toString(),true));
                                 }else{
                                    //out.print(vEti.SelectOneRowSinTD("iCvePaisOr","llenaSLT(1,'" + vParametros.getPropEspecifica("Mexico") + "','','',document.forms[0].iCveEdoOr);",clsConfig.getPaises(),"iCvePais","cNombre",request,"" + vParametros.getPropEspecifica("Mexico"),true));
                                	 out.print(vEti.SelectOneRowSinTD("iCvePaisOr","llenaSLT(1,this.value,'','',document.forms[0].iCveEdoOr);",clsConfig.getPaises(),"iCvePais","cNombre",request,"" + vParametros.getPropEspecifica("Mexico"),true));//Correcion realizada el dia 20/12/2012 AG SA.
                                 }
                                 out.print("</td>");

                                 out.print("<td class=\"EEtiqueta\">Estado Origen:</td>");
                                 if (request.getParameter("iCvePaisOr") != null){
                                    out.println("<td class='ECampo' colspan=\"4\">");
                                    out.println("<SELECT NAME=\"iCveEdoOr\" SIZE=\"1\">");
                                    out.println("</SELECT>");
                                    out.println("</td>");
                                }else{
                                     TDEntidadFed dEntidadFed = new TDEntidadFed();
                                     Vector vcEntidadFed = new Vector();
                                     vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + vParametros.getPropEspecifica("Mexico") );
                                     out.print("<td colspan=\"4\">");
                                     out.print(vEti.SelectOneRowSinTD("iCveEdoOr","",dEntidadFed.FindByAll(" where iCvePais = " + vParametros.getPropEspecifica("Mexico")) ,"iCveEntidadFed","cNombre",request,"-1",true));
                                     out.print("</td>");
                                 }


                                 out.print("</tr>");


                                 out.print("<tr>");
                                 out.println("<td class=\"EEtiqueta\" RowSpan=2>Destino:<Input Type=\"Text\" Size=\"4\" Value=\"250\" Name=\"iNoLetrasb\" readonly></td>");
                                 //out.println("<td RowSpan=2>");
                                 out.println("<td>");
                                 out.println("<textarea cols=\"20\" rows=\"5\"  name=\"cDestino\" value=\"\" onkeypress=\"fChgAreab(this);\" onBlur=\"fMayus(this);\" onchange=\"fChgArea(this);\">");
                                 out.println("</textarea>");
                                 out.println("</td>");

                                 out.print("<td class=\"EEtiqueta\">Pais Destino:</td>");
                                 out.print("<td>");
                                 if (request.getParameter("iCvePaisDes") != null){
                                    out.print(vEti.SelectOneRowSinTD("iCvePaisDes","llenaSLT(1,this.value,'','',document.forms[0].iCveEdoDes);",clsConfig.getPaises(),"iCvePais","cNombre",request,request.getParameter("iCvePaisDes").toString(),true));
                                 }else{
                                    out.print(vEti.SelectOneRowSinTD("iCvePaisDes","llenaSLT(1,this.value,'','',document.forms[0].iCveEdoDes);",clsConfig.getPaises(),"iCvePais","cNombre",request,"0",true));
                                 }
                                 out.print("</td>");


                                  out.print("<td class=\"EEtiqueta\">Estado Destino:</td>");
                                 out.println("<td class='ECampo' colspan=\"4\">");
                                 out.println("<SELECT NAME=\"iCveEdoDes\" SIZE=\"1\">");
                                 out.println("</SELECT>");
                                 out.println("</td>");
                                 //out.print("</tr>");



                                 out.print("</tr>");
                                 out.print("<tr>");




                              %>
						<tr></tr>
						<tr></tr>
					</table> <%
                          if (iLastExam == 1){
                             out.println("<SCRIPT LANGUAGE='JavaScript'>");
                             out.println("if (document.forms[0].hdiCveEmpresa){");
                             out.println("document.forms[0].hdiCveEmpresa.value = "+ vAUXEMO.getICveNumEmpresa() +";");
                             out.println("}");
                             out.println("</script>");

                          }
                          %> <%
                          }//termina sentencia revaloracion
                        }//termina valida Inhabilitacion de expediente
                        }else{
                          if (request.getParameter("hdType") != null && request.getParameter("hdType").length()>0){
                             // System.out.println("hdType2 ==>>"+request.getParameter("hdType"));
                          %>
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<%
                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                out.println("</tr>");
                          %>
					</table> <%

                          }
                        }
                        %>
				</td>
			</tr>
			<%}else{%>
			<script language="JavaScript" type="text/javascript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
			<%}%>
		</table>

	</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

