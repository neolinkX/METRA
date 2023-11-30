<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%/**
 * Title: Listado de Turnos de Validaci�n
 * Description: Consulta de citas
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Esteban Viveros
 * @version 1
 * Clase: ?
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="gob.sct.ingresosws.ws.ConUsrIng.*"%>

<html>
<%
  pg070103011CFG clsConfig = new pg070103011CFG();                // modificar Ok

  TEntorno       vEntorno  = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070103011.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103011.jsp\" target=\"FRMCuerpo"); // modificar Ok
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070103010.jsp";           // modificar Ok
  String cOperador    = "1";                         // modificar ?
  String cDscOrdenar  = "No Disponible|";                // modificar Ok
  String cCveOrdenar  = "No Disponible|";            // modificar Ok
  String cDscFiltrar  = "No Disponible|";                // modificar Ok
  String cCveFiltrar  = "No Disponible|";            // modificar Ok
  String cTipoFiltrar = "8|";                        // modificar Ok
  boolean lFiltros    = true;                       // modificar Ok
  boolean lIra        = true;                       // modificar Ok
  String cEstatusIR   = "Imprimir";                  // modificar ?

  // LLamado al Output Header
  TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
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
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  Vector vcUsuarios = new Vector();
  TVINGUsuario vUsuario =  new TVINGUsuario();

%>
<SCRIPT LANGUAGE="JavaScript">
  function fGenMovto(){
    fIrA('GuardarA','pg070103011.jsp');
  }
  var wExp;
  function fIrRecibo(cValor,cSet){
      cInicio = "";
      form = document.forms[0];

      if((wExp != null) && (!wExp.closed))
         wExp.focus();

       wExp = open(cValor, "",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=700,height=500,screenX=800,screenY=600');
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
  }

  function fAsigna(cNombre, cRFC){
    form = document.forms[0];
    form.cNombre.value = cNombre;
    form.cRFC.value    = cRFC;
  }

  function fSubmite(){
    alert("en el submit");
    if((wIng != null) && (!wIng.closed))
         wIng.close();
    alert(form.action);
    form = document.forms[0];
    form.submit();
    form.hdBoton.value = "Primero";
  }
</SCRIPT>

<script language="JavaScript">

 function fGetFile2(cLocal,cURL){
	  oHTP.cArchivoLocal = cLocal;
	  oHTP.cURLArchivo = cURL;
	  oHTP.fGetFile();
	}
	function descArch(){
		var rutaLocalTemp = "<%=""+new TParametro("07").getPropEspecifica("LOCAL_FOLDER_TEMP")%>";
		var fs = new ActiveXObject("Scripting.FileSystemObject");
		var rutaDllsEspanol64 = "C:\\Archivos de Programa (x86)\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		var rutaDllsEspanol32 = "C:\\Archivos de Programa\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		var rutaDllsIngles64 = "C:\\Program Files (x86)\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		var rutaDllsIngles32 = "C:\\Program Files\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		try{
			//alert(rutaLocalTemp);
			folderBool = fs.FolderExists(rutaLocalTemp);
			if (!folderBool){
			 	fs.CreateFolder(rutaLocalTemp);
			}
			folderA = fs.FolderExists(rutaDllsEspanol64);
			if (folderA){
				fs.CopyFile(rutaDllsEspanol64 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			folderB = fs.FolderExists(rutaDllsEspanol32);
			if (folderB){
				fs.CopyFile(rutaDllsEspanol32 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			folderC = fs.FolderExists(rutaDllsIngles64);
			if (folderC){
				fs.CopyFile(rutaDllsIngles64 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			folderD = fs.FolderExists(rutaDllsIngles32);
			if (folderD){
				fs.CopyFile(rutaDllsIngles32 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			
		 } catch (err) {
			//alert("Favor de instalar FingerPrint. " + err.message);
			 descargaFinal();
		 }
	}
	function descargaFinal(){
		var fsx = new ActiveXObject("Scripting.FileSystemObject");
		var archiExist1 = fsx.FileExists("C:/Windows/Temp/TCS/Sct_Fingerprint.zip");
		var archiExist2 = fsx.FileExists("C:/Windows/Temp/TCS/fingerUtilities.jar");
		var archiExist3 = fsx.FileExists("C:/Windows/Temp/TCS/FingerDesc.jar");
		//alert(archiExist1 + " " + archiExist2);
		if(!archiExist1){
			fGetFile2("C:\\Windows\\Temp\\TCS\\Sct_Fingerprint.zip","http://servidorimagenes.sct.gob.mx/medprev/Temp/Sct_Fingerprint.zip");
		}
		if(!archiExist2){
			fGetFile2("C:\\Windows\\Temp\\TCS\\fingerUtilities.jar","http://servidorimagenes.sct.gob.mx/medprev/Temp/fingerUtilities.jar");
		}
		if(!archiExist3){
			fGetFile2("C:\\Windows\\Temp\\TCS\\FingerDesc.jar","http://servidorimagenes.sct.gob.mx/medprev/Temp/FingerDesc.jar");
		}
		var shell = new ActiveXObject("WScript.shell");
		var pathProg = "javaw -jar c:\\Windows\\Temp\\TCS\\FingerDesc.jar ";
		shell.run(pathProg);
	}
</script>

<head>

<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>



<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
 </SCRIPT>
</head>
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>" onSubmit="return fOnSubmit();">
	<div id="loading"
		style="position: absolute; width: 100%; text-align: center; top: 250px;">
		<img src="<%=vParametros.getPropEspecifica("RutaImg")%>nuevo6.gif"	border=0>
	</div>
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="200">
  <input type="hidden" name="hdRowNum" value="200">
  <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal")!=null?request.getParameter("hdICvePersonal"):"0"%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):"0"%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):"0"%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="Primero">
  <input type="hidden" name="lNuevo" value="<%=request.getParameter("lNuevo")%>">


  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo")%>">
  <input type="hidden" name="dtFecha" value="<%=request.getParameter("dtFecha")%>">
  <input type="hidden" name="cHora" value="<%=request.getParameter("cHora")%>">

  <input type="hidden" name="cNombre" value="">
  <input type="hidden" name="cRFC"    value="">



  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
      TVPERDatos vPerDatos=clsConfig.findUserComplete();
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="4">Datos Personales</td></tr>
<%    if(vPerDatos!=null){
%>      <tr>
        <td class="EEtiqueta">Nombre:</td><td class="ETabla"><%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%></td>
        <td class="EEtiqueta">Expediente:</td><td class="ETabla"><%=vPerDatos.getICveExpediente()%></td>
      </tr>
      <tr>
        <td class="EEtiqueta">Edad:</td><td class="ETabla"><%=clsConfig.getEdad(vPerDatos.getDtNacimiento())%></td>
        <td class="EEtiqueta">Sexo:</td><td class="ETabla"><%=vPerDatos.getCSexo_DGIS()%></td>
      </tr>
<%    }else{
%>      <tr><td class="EResalta" colspan="4" align="center">Datos no disponibles</td></tr>
<%    }
%>    </table></td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
<%
    TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
    TFechas Fechas = new TFechas();
    java.sql.Date dtActual = Fechas.TodaySQL();
    Vector vcEXPExamCat = new Vector();
    int iLastExam = 0;
    iLastExam = dEXPExamAplica.FindLastEPI(request.getParameter("hdICvePersonal"),vParametros.getPropEspecifica("EPIProceso").toString());
    TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
    TVEXPExamCat vEXPExamCat = new TVEXPExamCat();
    // Verificar que se solicita un examen nuevo
    TVEXPExamAplica vExamAplica = dEXPExamAplica.CategAdicional(Integer.parseInt(request.getParameter("hdICvePersonal").toString()),Integer.parseInt(vParametros.getPropEspecifica("EPIProceso").toString()));
    if(request.getParameter("lNuevo") != null && "true".compareToIgnoreCase(request.getParameter("lNuevo").toString()) == 0 &&
       "Actual".compareToIgnoreCase(request.getParameter("hdBoton").toString()) == 0  ){
      vcEXPExamCat = dEXPExamCat.FindByExpedienteExamen(request.getParameter("hdICvePersonal"),"" + iLastExam);
      vExamAplica.setINumExamen(0);
    }else{
    if(vExamAplica.getINumExamen() == 0)
      vcEXPExamCat = dEXPExamCat.FindByExpedienteExamen(request.getParameter("hdICvePersonal"),"" + iLastExam);
    else
      vcEXPExamCat = dEXPExamCat.FindByExpedienteExamen(String.valueOf(vExamAplica.getICveExpediente()),String.valueOf(vExamAplica.getINumExamen()));
    }
    Vector vcEmpresa = new Vector();


    //out.print("<td colspan='4'>");
    //out.print("<table class='ETabla' align='center'>");
    out.print("<tr class='ETablaT'><td colspan='5'>CATEGORIAS REGISTRADAS</td></tr>");
    out.print("<tr><td class='ETablaST' colspan='2' align='center'>Modo de Transporte</td>");
    out.print("<td class='ETablaST'  align='center'>Categoría</td>");
    out.print("<td class='ETablaST'  align='center'>Motivo</td>");
    out.print("<td class='ETablaST'  align='center'>Ref.Alfanumúrica</td></tr>");

    String vcSinPago = "";
    int viExamenPagado = 0;
    TFechas Fecha = new TFechas();
    if (vExamAplica.getINumExamen() > 0){
    if (vcEXPExamCat.size() > 0 ){
      for (int i=0;i<vcEXPExamCat.size();i++){
        vEXPExamCat = (TVEXPExamCat) vcEXPExamCat.get(i);
        out.print("<tr>");
        out.println("<td class=\"ECampo\" colspan='2'>"+vEXPExamCat.getCDscMdoTrans()+"</td>");
        out.println("<td class=\"ECampo\">"+vEXPExamCat.getCDscCategoria()+"</td>");
        out.println("<td class=\"ECampo\">"+vEXPExamCat.getCDscMotivo()+"</td>");
        if (vEXPExamCat.getLPago() == 1) {
           if (vEXPExamCat.getCRefAlfanum() == null || vEXPExamCat.getCRefAlfanum().equals("")) {
             // Verifica si existe un EPI pagado en los �ltimos 30 dias
              viExamenPagado = dEXPExamAplica.FindByExamenPagado(vEXPExamCat.getICveExpediente(), Integer.parseInt( vParametros.getPropEspecifica("EPIProceso")), dtActual);

               if (viExamenPagado == 0){
                 if (vPerDatos.getICveEmpresa() != 0) {
                    TDGRLEmpresas dEmpresa = new TDGRLEmpresas();
                    vcEmpresa = dEmpresa.FindByEmpDom(" WHERE GRLEmpresas.iCveEmpresa = "+vPerDatos.getICveEmpresa());
                 }
               }
               vcSinPago += vEXPExamCat.getICveMdoTrans()+","+vEXPExamCat.getICveCategoria()+","+vEXPExamCat.getCDscMotivo()+"|";
            }
            else out.println(" <input type='hidden' name='hdMovtoRegistrado'" +"  value='1'> ");
           out.println("<td class=\"ECampo\">"+vEXPExamCat.getCRefAlfanum()+"</td>");
        }
        else
            out.println("<td class=\"ECampo\">No requiere pago</td>");
        out.print("</tr>");

      }
      if (!vcSinPago.equals("")) {
          if (vcEmpresa.size() > 0)
            vcUsuarios = clsConfig.getUsuarios(vPerDatos, (TVGRLEmpresas) vcEmpresa.get(0));
          else
            vcUsuarios = clsConfig.getUsuarios(vPerDatos, new TVGRLEmpresas());
          out.println(" <input type='hidden' name='hdPago'" +"  value='"+vcSinPago+"' > ");
      }
    }
    }
    //out.print("<tr><td>&nbsp;</td></tr>");
    //out.print("</table>");
    //out.print("</td>");

     if(bs!=null && bs.rowSize() > 0){
        bs.start();
        String cDscMdoTrans="";
        String cDscCategoria="";
        Vector vcMotivos=clsConfig.getMotivos();
        Vector vcMotivosVacio=new Vector();
        vcMotivosVacio.add(vParametros.getPropEspecifica("MotRevaloracion")+"|- REVALORACION -");
        while(bs.nextRow()){
          String cPostfix=bs.getFieldValue("iCveModoTrans")+"|"+bs.getFieldValue("iCvePuesto")+"|"+bs.getFieldValue("iCveCategoria")+"|"+bs.getFieldValue("iCveGrupo");
//          String cPostfix=bs.getFieldValue("iCveModoTrans")+"|"+bs.getFieldValue("iCvePuesto");
          String cNomCombo="cbo|"+cPostfix;
          String cNomCbox="cb|"+cPostfix;
          if(!cDscMdoTrans.equals(bs.getFieldValue("cDscMdoTrans"))){
            cDscMdoTrans=(String)bs.getFieldValue("cDscMdoTrans");
%>      <tr class="ETablaT"><td colspan="5"><%=cDscMdoTrans%></td></tr>
<%        }
          if(!cDscCategoria.equals(bs.getFieldValue("cDscCategoria"))){
            cDscCategoria=(String)bs.getFieldValue("cDscCategoria");
%>      <tr>
        <td class="ETablaT">Categor&iacute;a:</td>
        <td class="ETabla"><%=cDscCategoria%></td>
        <td class="ETablaT">Motivo:</td>
        <td class="ETabla">
<%        if(clsConfig.estaEnNoAptos((String)bs.getFieldValue("iCveModoTrans"),(String)bs.getFieldValue("iCveCategoria"))){
%>          <%=vEti.SelectOneRowSinTD("noCuenta","\" disabled \"",vcMotivosVacio,request,"")%><input type="hidden" name="<%=cNomCombo%>" value="<%=vParametros.getPropEspecifica("MotRevaloracion")%>">
<%          }else{
%>          <%=vEti.SelectOneRowSinTD(cNomCombo,"",vcMotivos,"iCveMotivo","cDscMotivo",request,request.getParameter(cNomCombo)!=null?request.getParameter(cNomCombo):"0")%>
<%          }
%>        </td>
      </tr>
<%        }
%>    <tr class="ETabla">
        <td colspan="2" style="padding-left:10"><%=bs.getFieldValue("cDscPuesto")%></td>
        <td colspan="2" style="padding-left:10"><%=bs.getFieldValue("cDscGrupo")%><input type="hidden" name="<%=cNomCbox%>" value="1"></td>
      </tr>
<%      }
%>      <tr class="ETabla">
<%      if("yes".equalsIgnoreCase(cCanWrite)){
%>        <td colspan="4" align="center">
<script language="JavaScript">
  /*Nueva implementacion de validacion de huella dactilar*/
  var GRALiCveExp, GRALiNumExm,ICVEMODULO,ICVEUNIMED;
  function procesoValidacionHuella(icveexpediente,icvenumexampen,icvemodulo,icveunimed){
	  GRALiCveExp=icveexpediente;
	  GRALiNumExm=icvenumexampen;
	  ICVEMODULO=icvemodulo;
	  ICVEUNIMED=icveunimed;
	  openpopupValidaPaciente();
  }
  function Validacion(value){
      if(value == 'true'){
          alert("La huella coincide con este expediente");
      }else{
          alert("La huella no pertenece a la persona");
      }
  }
  function openpopupValidaPaciente(){
		var popurl = "validaBiometricoSeleccionMotivo.jsp?idPersona=2&iNumExm="+ GRALiNumExm + "&iCveExp=" + GRALiCveExp+"&ICVEUNIMED="+ ICVEUNIMED + "&ICVEMODULO=" + ICVEMODULO;
		window.open(popurl, "", "width=600,height=200,status,menubar");
  }
  function respuestaopenpopupValidaPaciente(){
	  fIrCatalogo();
  }  
</script>
          <!-- <a href="javascript:fIrCatalogo();" onMouseOut="self.status='';return true;" onMouseOver="self.status='Guarda los datos';return true;">Aceptar</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
          
          <a href="javascript:procesoValidacionHuella('<%=String.valueOf(vPerDatos.getICveExpediente())%>','<%=String.valueOf(vPerDatos.getINumExamen())%>','<%=request.getParameter("iCveModulo")%>','<%=request.getParameter("iCveUniMed")%>');" onMouseOut="self.status='';return true;" onMouseOver="self.status='Guarda los datos';return true;">Aceptar</a>&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="javascript:fIrA('Actual','pg070103010.jsp');" onMouseOut="self.status='';return true;" onMouseOver="self.status='Regresa a la pantalla anterior';return true;">Regresar</a>
<%
         }
%>
      </td></tr>
<%=vErrores.muestraError()%>
<%    }else{

%><%=vErrores.muestraError()%>      <tr class="ETablaT"><td align="center" colspan="5">No ha Seleccionado Categorías por Agregar</td></tr>
      <tr class="ETabla"><td align="center" colspan="7">
          <a href="javascript:fIrA('Actual','pg070103010.jsp');" onMouseOut="self.status='';return true;" onMouseOver="self.status='Regresa a la pantalla anterior';return true;">Regresar</a>
        </td></tr>
<%    }
%>    </table></td></tr>

<%
if (!vcSinPago.equals("") && clsConfig.getVInfoIng() == null) {
%>
     <tr class="ETabla"><td align="center" colspan="7">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
       <tr><td class="ETablaT" colspan="8">Datos para el Recibo</td></tr>
       <tr class="ETabla">
        <td class="ETablaST"  align="center">RFC</td>
        <td class="ETablaST"  align="center">Nombre</td>
        <td class="ETablaST"  align="center">Domicilio</td>
        <td class="ETablaST"  align="center">Colonia</td>
        <td class="ETablaST"  align="center">Municipio</td>
        <td class="ETablaST"  align="center">Entidad Fed.</td>
        <td class="ETablaST"  align="center">C.P.</td>
        <td class="ETablaST"  align="center">Selecci&oacute;n</td>
       </tr>
<%     String cSelected = "", cValor = "";
       for (int i=0; i < vcUsuarios.size(); i++) {
%>      <tr>  <%
           vUsuario =  (TVINGUsuario) vcUsuarios.get(i);
           out.print(vEti.Texto("ETablaC",vUsuario.getCRFC()));
           out.print(vEti.Texto("ETabla", vUsuario.getCNombre() +" "+vUsuario.getCApPaterno()+ " "+vUsuario.getCApMaterno()));
           out.print(vEti.Texto("ETabla", vUsuario.getCCalle()));
           out.print(vEti.Texto("ETabla", vUsuario.getCColonia()));
           out.print(vEti.Texto("ETabla", vUsuario.getCDscMunicipio()));
           out.print(vEti.Texto("ETabla", vUsuario.getCDscEntidadFed()));
           out.print(vEti.Texto("ETablaC",String.valueOf(vUsuario.getICP())));
          %>
          <td class="ETablaC">
          <%
           // cRPA se utuliz� para almacenar la clave de la empresa o el personal segun el caso.
           cValor = vUsuario.getICveUsuario()>0?(vUsuario.getICveUsuario()+"|"+(vUsuario.getLFisicoMoral()?"1":"2")):(vUsuario.getCRPA()+"|"+(vUsuario.getLFisicoMoral()?"3":"4"));
           String cClick = "fAsigna('" + vUsuario.getCNombre() +" " +vUsuario.getCApPaterno()+ " "+vUsuario.getCApMaterno() +"','"+ vUsuario.getCRFC() + "');";
           if (i==0) cSelected = cValor ; else cSelected = "";
           out.print(vEti.ObjRadioSE("ETabla","rstDatRecibo",cValor,"",cSelected,cClick,"","",0,true,true));
%>        </td>
       </tr>
<%     } // for de usuarios %>
       </tr>
       <tr class="ETabla"><td align="center" colspan="8">
          <a href="javascript:fGenMovto();" onMouseOut="self.status='';return true;" onMouseOver="self.status='Confirma la generación del movimiento en Ingresos';return true;">Confirmar generación de movimiento</a>&nbsp;&nbsp;&nbsp;&nbsp;
       </td></tr>

      </table>
     </td></tr>
<%   }%>
       <%
           String cOpen = "dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=no,status=no,titlebar=no,toolbar=no,width=640,height=480,screenX=1024,screenY=768";
           String cParametros = clsConfig.getCParametros();
           if (cParametros!= null && !cParametros.equals("")){
%>
               <script language="JavaScript">fIrRecibo('pg070103012.jsp<%="?"+cParametros%>','<%=cOpen%>');</script>
<%         }%>
<%  }else{
%>  <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
<%
 if(clsConfig.getVInfoIng() != null){
%>
<!-- Forma utilizada para generar el movimiento en ingresos -->
<form name="GeneraMov" method="POST" action="<%= clsConfig.getVInfoIng().get("cPagina")%>">
  <input type="hidden" name="hdBoton"   value="GeneraPorImporteTotal">
  <input type="hidden" name="hdCampoClave" value="<%=clsConfig.getVInfoIng().get("iCliente")%>">
  <input type="hidden" name="FILNombre"    value="<%= clsConfig.getVInfoIng().get("cNombre")%>">
  <input type="hidden" name="FILRFC"       value="<%= clsConfig.getVInfoIng().get("cRFC")%>">
  <input type="hidden" name="SLSCentro"    value="">
  <input type="hidden" name="hdUnidadAdm"  value="<%= clsConfig.getVInfoIng().get("iUnidadAdm")%>">
  <input type="hidden" name="SLSArea"      value="<%= clsConfig.getVInfoIng().getString("iArea")%>">
  <input type="hidden" name="SLSOficina"   value="<%= clsConfig.getVInfoIng().get("iOficina")%>">
  <input type="hidden" name="hdUsrIngresos"     value="<%= clsConfig.getVInfoIng().get("cUsrIngresos")%>">
  <input type="hidden" name="hdPassUsrIngresos" value="<%= clsConfig.getVInfoIng().get("cPassUsrIngresos")%>">

  <input type="hidden" name="hdURLRespuesta"  value="<%= clsConfig.getVInfoIng().get("cURLResp")%>">
  <input type="hidden" name="hdDatosAdicionales" value="<%= clsConfig.getVInfoIng().get("cDatoAdic")%>">
  <%
   TVDinRep02 vMovimiento = new TVDinRep02();
   for(int i=0; i < ((Vector)clsConfig.getVInfoIng().get("vGeneraMov")).size(); i++){
     vMovimiento = (TVDinRep02) ((Vector)clsConfig.getVInfoIng().get("vGeneraMov")).get(i);
     out.println("<input type='hidden' name='FILRefer"   + (i +1) + "' value='" + vMovimiento.get("cRefNum") + "'>");
     out.println("<input type='hidden' name='TBXEmite"   + (i +1) + "' value='1'>");
     out.println("<input type='hidden' name='FILUnidad"  + (i +1) + "' value='" + vMovimiento.get("iUnidad") + "'>");
     out.println("<input type='hidden' name='FILTotal"   + (i +1) + "' value='" + vMovimiento.get("iTotal")  + "'>");
     out.println("<input type='hidden' name='TXTObserva" + (i +1) + "' value='" + vMovimiento.get("cObserva")+ "'>");
   }
  %>
</form>
<!-- submitir la forma -->
<script LANGUAGE="JavaScript">
 var wIng;
  function fSolicitaIng(){
     formIng = document.forms[1];
     wIng = open("about:blank", "vIngresos",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=700,height=500,screenX=800,screenY=600');
     wIng.moveTo(50,50);
     formIng.target = "vIngresos";
     formIng.submit();
  }
  fSolicitaIng();
</script>
<%
 } // else
  System.out.println(new TDefPrecargar(vEntorno.getListaImgs()).getResultado());
%>


	<SCRIPT LANGUAGE="JavaScript"> 
var ld=(document.all);  
var ns4=document.layers; 
var ns6=document.getElementById&&!document.all; 
var ie4=document.all;  
if (ns4) 	ld=document.loading; 
else if (ns6) 	ld=document.getElementById("loading").style; 
else if (ie4) 	ld=document.all.loading.style;  
function Loaded() { 
	if(ns4){ld.visibility="collapse";} 
else if (ns6||ie4) ld.display="none"; } 
function Loading() { 
	if(ns4){ld.visibility="visible";} 
else if (ns6||ie4) ld.display="block"; } 
Loaded();
</script>
<div>
<object id="oHTP" classid="clsid:4CCB05E0-E1BB-4999-A3BB-84172549A276"
codebase="/medprev/activex/HTTPGetProj1.ocx"
width=1 height=1 align=center hspace=0 vspace=0></object>
</div>


</body>
<script>
//setTimeout("insertaImagenes()",50);
descArch();
</script>
<%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();
%></html>
