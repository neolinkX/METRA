<%/**
 * Title: Detalle de las Citas
 * Description: Detalle de las Citas
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. Gonzï¿½lez Paz
 * @version 1.0
 * Clase:CFG
 */%>


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
  pg070103015CFG  clsConfig = new pg070103015CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070103015.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103015.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";
  TDPais dPaisNac = new TDPais();
  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No Disponible|";    // modificar
  String cCveOrdenar  = "No Disponible|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "No Disponible|";  // modificar
  String cTipoFiltrar = "8|";                // modificar
  boolean lFiltros    = true;                 // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

      String cCampo = "";
      String cPanel = "";

    if ( (request.getParameter("hdOPPbaRapida") == null) ||
         (request.getParameter("hdBoton").compareTo("Guardar") == 0) ||
          (request.getParameter("hdBoton").compareTo("Cancelar") == 0)){
         cCampo = "0";
    }
    else{
         cCampo = "" + request.getParameter("hdOPPbaRapida");
     }



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
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  TEtiCampo vEti = new TEtiCampo();


  TDPERDatos dPERDatos = new TDPERDatos();
    int Suma = 0;
    int ValidaCurp = 0;

%>

<script language="JavaScript">

 function (opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           //window.parent.FRMOtroPanel.location="pg070102020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
           window.parent.FRMOtroPanel.location="pg070103015P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       //window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
       window.parent.FRMOtroPanel.location="pg070103015P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberï¿½n ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensiï¿½n js (vg. pg0702061.js)


  function fIrPagina(){
    form = document.forms[0];
    form.hdiCvePersonal.value = '1';
    form.target = 'FRMDatos';
    form.action = 'SEDetPer.jsp';
    form.submit();
  }



  // Esta funciï¿½n no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }

  function fCURRP(msg){
            alert(msg);
  }

</script>

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
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>

<head>
<style type="text/css">
<!--
.style3 {
	font-size: 18px;
	color: #FF0000;
	font-weight: bold;
	font-style: italic;
}
-->
</style>
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
	<div id="loading"
		style="position: absolute; width: 100%; text-align: center; top: 250px;">
		<img src="<%=vParametros.getPropEspecifica("RutaImg")%>nuevo6.gif"	border=0>
	</div>
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave2  = ""+bs.getFieldValue("iCveMuestra", "");
        }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdAnio" value="<%=iAnio%>">
  <input type="hidden" name="hdPbaRapida" >
  <input type="hidden" name="hdPersonal">
  <input type="hidden" name="hdiCvePersonal">
  <input type="hidden" name="hdCveUniMed" value="<%=request.getParameter("hdCveUniMed")%>">
  <input type="hidden" name="hdCveModulo" value="<%=request.getParameter("hdCveModulo")%>">
  <input type="hidden" name="hdFecha" value="<%=request.getParameter("hdFecha")%>">
  <input type="hidden" name="hdCveCita" value="<%=request.getParameter("hdCveCita")%>">
  <input type="hidden" name="hdICvePersonal">
  <input type="hidden" name="hdICveUniMed">
  <input type="hidden" name="hdICveModulo">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo")%>">
  <input type="hidden" name="dtFecha" value="<%=request.getParameter("dtFecha")%>">
  <input type="hidden" name="cHora" value="<%=request.getParameter("cHora")%>">
  <input type="hidden" name="lValidado" value="false">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
      <tr><td class="ETablaC" valign="top">
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
<%
  	boolean lCaptura = clsConfig.getCaptura();
	boolean tieneCoincidenciaCURP = false;
	
	//Valida transfronterizo
	boolean bTransfronterizo =  false;
	if(request.getParameter("hdCveUniMed").equals("1")){
		if(request.getParameter("hdCveModulo").equals("14")){
			bTransfronterizo =  true;
		}
	}
	
    if(clsConfig.getlValidar() &&
      (clsConfig.getVResValida() != null && clsConfig.getVResValida().size() > 0)){ %>

          <tr>
            <td colspan="12" class="ETablaAyuda">Coincidencias</td>
          </tr>
             <%
               for(int j=0; j < clsConfig.getVResValida().size(); j++){
                 Vector vResultado = (Vector) clsConfig.getVResValida().get(j);
                 TVPERDatos vPERDatos;
                  // Impresiï¿½n de los tï¿½tulos
                  
                 if( j == 0)
                	 out.println("<tr><td colspan=\"12\" class=\"style3\">MISMO CURP</td></tr>");
                 else if( j == 1)
                	 out.println("<tr><td colspan=\"12\" class=\"style3\">MISMO RFC</td></tr>");
                 else if(j==2)
                	 out.println("<tr><td colspan=\"12\" class=\"style3\">MISMO NOMBRE COMPLETO Y FECHA DE NACIMIENTO</td></tr>");
                 else if(j==3)
                	 out.println("<tr><td colspan=\"12\" class=\"style3\">MISMO APELLIDO PATERNO Y FECHA DE NACIMIENTO</td></tr>");
                 else
                	 out.println("<tr><td colspan=\"12\" class=\"style3\">MISMO APELLIDO MATERNO Y FECHA DE NACIMIENTO</td></tr>");
                  
                 if(vResultado != null && vResultado.size() > 0){
               	 	if(j==0)tieneCoincidenciaCURP = true;
                  // Impresiï¿½n de encabezados
                  out.println("<tr class='ETablaST'>");
                  out.println(vEti.Texto("ETablaST","&nbsp;&nbsp;"));
                  out.println(vEti.Texto("ETablaST","Expediente"));
                  out.println(vEti.Texto("ETablaST","RFC"));
                  out.println(vEti.Texto("ETablaST","CURP"));
                  out.println(vEti.Texto("ETablaST\" colspan=\"3","Nombre"));
                  out.println(vEti.Texto("ETablaST","Sexo"));
                  out.println(vEti.Texto("ETablaST","Fecha de Nacimiento"));
                  out.println(vEti.Texto("ETablaST","Lugar de Nacimiento"));
                  out.println(vEti.Texto("ETablaST","Direcci&oacute;n"));
                  out.println(vEti.Texto("ETablaST","Validaci&oacute;n Dactilar"));
                  out.println("</tr>");
                  // Impresiï¿½n de los datos
                  for(int i=0; i < vResultado.size(); i++){
                    vPERDatos = (TVPERDatos) vResultado.get(i);
                    out.println("<tr class='EPie'>");
                    out.println("<td class='EPieC'>" + vEti.ObjRadioSE("EPieC","iCvePersonalS",String.valueOf(vPERDatos.getICveExpediente()),"","","","","",0,true,true) + "</td>");
                    out.println(vEti.Texto("ETablaR",String.valueOf(vPERDatos.getICveExpediente())));
                    out.println(vEti.Texto("ETabla",vPERDatos.getCRFC()));
                    out.println(vEti.Texto("ETabla",vPERDatos.getCCURP()));
                    out.println(vEti.Texto("ETabla",vPERDatos.getCApPaterno()));
                    out.println(vEti.Texto("ETabla",vPERDatos.getCApMaterno()));
                    out.println(vEti.Texto("ETabla",vPERDatos.getCNombre()));
                    out.println(vEti.Texto("EPieC",vPERDatos.getCSexo()));
                    out.println(vEti.Texto("EPie", vPERDatos.getCDscFecNacimiento()));
                  out.println(vEti.Texto("EPie", vPERDatos.getCDscEstadoNac()));
                  out.println(vEti.Texto("EPie", vPERDatos.getCCalle()));
                  
                //+ "ValidacionHuellaDactiar2('true'"+String.valueOf(vPERDatos.getICveExpediente())+");"
                  
                  out.print("<td>"+vEti.clsAnclaTexto("EAnclaC", "Validar Huella",
                          "javascript:"                          
                          + "procesoValidacionHuella("+String.valueOf(vPERDatos.getICveExpediente())+","+String.valueOf(vPERDatos.getINumExamen())+",'"+request.getParameter("iCveModulo")+"','"+request.getParameter("iCveUniMed")+"')"
                          + "", "exp")+"</td>");
                  out.println("</tr>");
                  }

                  %>

<script language="JavaScript">
  function ValidacionHuellaDactiar3(value){
      if(value == 'true'){
          alert("La huella SI pertenece a la persona");
      }else{
          alert("La huella NO pertenece a la persona");
      }
  }

  function ValidacionHuellaDactiar2(value,ICVEEXPEDIENTE){
      if(value == 'true'){
              alert("Colocar huella del paciente en el lector");
              var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, width=10, height=10, top=20, left=20";
              window.open("validaBiometrico.jsp?iCveDocto=0&iCveExpediente="+ICVEEXPEDIENTE+"&idPersona=2", "",opciones);
      }
  }
  
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
		var popurl = "validaBiometricoComparacionPaciente.jsp?idPersona=2&iNumExm="+ GRALiNumExm + "&iCveExp=" + GRALiCveExp+"&ICVEUNIMED="+ ICVEUNIMED + "&ICVEMODULO=" + ICVEMODULO;
		window.open(popurl, "", "width=600,height=200,status,menubar");
  }
  function respuestaopenpopupValidaPaciente(){
      fGenExpediente(true);
  }
  
</script>

          <%

                } else
                  out.println("<tr><td colspan=\"12\" class=\"ETablaT\">No se encontraron registros coincidentes</td></tr>");
               } // for
               // Opciï¿½n Generar Expediente
               
               if(!tieneCoincidenciaCURP){
                   out.println("<tr>");
                   out.println("<td class='EPieC'>" + vEti.ObjRadioSE("EPieC","iCvePersonalS","0","","","\" Checked \"","","",0,true,true) + "</td>");
                   out.println(vEti.TextoCS("EEtiquetaL","Generar nuevo Expediente",9));
                   out.println("</tr>");
                   out.println("<tr><td colspan=\"12\" class=\"ETablaC\">");
                   out.println(vEti.clsAnclaTexto("ETabla","Aceptar","JavaScript:fGenExpediente(true);","Validar la Generaci&oacute;n del Expediente","lGenerar"));
                   out.println("</td></tr>");
               }               
 }
else {
  // Presentar liga de validaciï¿½n
  if(!lCaptura && !clsConfig.getlGenerar() && clsConfig.getICvePersonalA() == 0){
    out.println("<tr><td colspan=\"11\" class=\"EResaltaR\">");
    out.println(vEti.clsAnclaTexto("ETabla","Validar y Generar el expediente","JavaScript:fGenExpediente(false);","Validar la Generaci&oacute;n del Expediente","lGenerar"));
    out.println("</td></tr>");
  }
}
%>
         </table>
     </td></tr>

  <tr><td>
  <input type="hidden" name="hdBoton">
  </td><tr></tr><td valign="top">
                                 <%
                                  // Llamado Provisional a Consulta Detallada de Personal
                                   if (request.getParameter("hdBoton").compareTo("Ventana") == 0){
                                  %>
                                 <script language="JavaScript">
                                       fIrPagina();
                                 </script>
                                 <%
                                   }
                                  %>

                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                 <tr>
                                 <td colspan="4" class="ETablaT">Detalle de las Citas
                                 </td>
                                 </tr>
                                 <tr>

                               <%
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){

                                }
                                else {
                                  if (bs != null){
                                   if(clsConfig.getICvePersonalA() > 0){

                        //Validando Curp
                              int iCvePersonal;
                               iCvePersonal = Integer.parseInt(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
                               if (iCvePersonal > 0){
                                  try{
                                        Suma = dPERDatos.FindByValida(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
                                        ValidaCurp = dPERDatos.ValidaCURP(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
                                     }catch (Exception ex) {
                                        Suma = 0;
                                        ValidaCurp = 0;
                                     }
                                }

                                     // Imprimir nï¿½mero de expediente generado y las ligas para el puesto y el detalle
                                     out.println("<tr><td class='ETablaAyuda'>Expediente Asignado</td>");
                                     out.println(vEti.Texto("EEtiqueta",bs.getFieldValue("iCvePersonal","&nbsp;").toString()));
                                     out.println("<td class='ETablaC'>");
                                     if(ValidaCurp == 18){
                                     out.print(vEti.clsAnclaTexto("EAncla","Puesto","javascript:fPuesto("+ bs.getFieldValue("iCvePersonal","&nbsp;")+","+ bs.getFieldValue("iCveUniMed","&nbsp;") + "," + bs.getFieldValue("iCveModulo","&nbsp;") + ");","Buscar"));
                                     out.println("</td><td class='ETablaC'>");
                                     }else{
                                     String alerta = "";
                                            if(ValidaCurp == 0)
                                                alerta = "'Para continuar con su examen es obligatorio capturar el CURP,\n esto podrÃ¡ hacerlo dando clic en detalle.'";
                                            else
                                                alerta= "'Para continuar con su examen es necesario corregir el CURP,\n esto podrÃ¡ hacerlo dando clic en detalle.'";
                                            out.print(vEti.clsAnclaTexto("EAncla","Puesto",
                                            "javascript:fCURRP("+alerta+");","Buscar")
                                           + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                              out.println("</td><td class='ETablaC'>");
                                     }
                                     if(!bTransfronterizo){
	                                     out.print("<script language=\"JavaScript\" type=\"text/javascript\">");
	                                     out.print("alert(\"A partir de este momento tiene un máximo de 3 horas para generar el Vale de servicio, en caso de no hacerlo este expediente será eliminado.\");");
	                                     out.print("</script>");
                                     }
                                     //out.print(vEti.clsAnclaTexto("EAncla","Puesto","javascript:fPuesto("+ bs.getFieldValue("iCvePersonal","&nbsp;")+","+ bs.getFieldValue("iCveUniMed","&nbsp;") + "," + bs.getFieldValue("iCveModulo","&nbsp;") + ");","Buscar"));
                                     //out.println("</td><td class='ETablaC'>");
                                     out.print(vEti.clsAnclaTexto("EAncla","Detalle","javascript:fDetalle("+ bs.getFieldValue("iCvePersonal","&nbsp;") +  ");","Buscar"));
                                     out.println("</td></tr>");
                                    }
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Unidad M&eacute;dica:","ECampo","text",10,10,"iCveUniMed", bs.getFieldValue("cDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","M&oacute;dulo:","ECampo","text",10,10,"iCveModulo", bs.getFieldValue("cDscModulo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtFecha",bs.getFieldValue("cDscDtFecha","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Hora:","ECampo","text",10,10,"cHora", bs.getFieldValue("cHora","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                   out.println("<tr>");
                                   // if (request.getParameter("hdBoton").compareTo("Modificar") == 0)
                                   //    out.print(vEti.EtiCampoCS("EEtiqueta","Cve. Cita:","ECampo","text",10,10,4,"iCveCita", bs.getFieldValue("iCveCita","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                   // else
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Cve. Cita:","ECampo","text",10,10,4,"iCveCita", bs.getFieldValue("iCveCita","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaSTC","Datos Personal",4));
                                    out.println("</tr>");

                                    int Personal = 0;
                                    Personal = Integer.parseInt(bs.getFieldValue("iCvePersonal","&nbsp;").toString());

                                    if (Personal <= 0){
                                       // Si no son campos se generan los campos ocultos para realizar las bï¿½squedas
                                       if(!lCaptura){
                                    %>
                                     <input type="hidden" name="cRFC" value="<%=bs.getFieldValue("cRFC","&nbsp;").toString()%>">
                                     <input type="hidden" name="cCURP" value="<%=bs.getFieldValue("cCURP","&nbsp;").toString()%>">

                                     <input type="hidden" name="cLicencia" value="<%=bs.getFieldValue("cLicencia","&nbsp;").toString()%>">
                                     <input type="hidden" name="cLicenciaInt" value="<%=bs.getFieldValue("cLicenciaInt","&nbsp;").toString()%>">

                                     <input type="hidden" name="cApPaterno" value="<%=bs.getFieldValue("cApPaterno","&nbsp;").toString()%>">
                                     <input type="hidden" name="cApMaterno" value="<%=bs.getFieldValue("cApMaterno","&nbsp;").toString()%>">
                                     <input type="hidden" name="cNombre" value="<%=bs.getFieldValue("cNombre","&nbsp;").toString()%>">
                                     <input type="hidden" name="dtNacimiento" value="<%=bs.getFieldValue("cDscDtFechaNac","&nbsp;").toString()%>">
                                     <input type="hidden" name="iCvePaisNac" value="<%=bs.getFieldValue("iCvePaisNac","&nbsp;").toString()%>">
                                     
                                    <%
                                       }

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",13,13,"cRFC", bs.getFieldValue("cRFC","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",18,18,"cCURP", bs.getFieldValue("cCURP","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Licencia:","ECampo","text",13,13,"cLicencia", bs.getFieldValue("cLicencia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Licencia Int:","ECampo","text",18,18,"cLicenciaInt", bs.getFieldValue("cLicenciaInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",50,50,"cApPaterno", bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",50,50,"cApMaterno", bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr><tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Nombre(s):","ECampo","text",50,80,"cNombre", bs.getFieldValue("cNombre","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    if (!lCaptura){
                                    //out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cGenero","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cSexo_DGIS","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    }
                                    else{
                                     out.println(vEti.Texto("EEtiqueta","Sexo:"));
                                     if (bs.getFieldValue("cGenero","&nbsp;").toString().compareTo("M") == 0){
                                    %>
                                    <td class="EEtiquetaL">
                                    Hombre<input type="radio" name="cGenero" value="M" checked>
                                    Mujer<input type="radio" name="cGenero" value="F">
                                    </td>
                                    <%
                                     }
                                    else{
                                    %>
                                    <td class="EEtiquetaL">
                                    Hombre<input type="radio" name="cGenero" value="M">
                                    Mujer<input type="radio" name="cGenero" value="F" checked>
                                    </td>
                                    <%
                                     }
                                    }
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","FECNAC (Fecha de Nacimiento):","ECampo","text",10,10,4,"dtNacimiento", bs.getFieldValue("cDscDtFechaNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    }
                                    else{
                                     %>
                                      <input type="hidden" name="hdFlag" value="<%=Personal%>">
                                     <%
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",50,50,"cApPaterno", bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",50,50,"cApMaterno", bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Nombre(s):","ECampo","text",50,80,"cNombre", bs.getFieldValue("cNombre","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    //out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cGenero","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cSexo_DGIS","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","FECNAC (Fecha de Nacimiento):","ECampo","text",10,10,4,"dtNacimiento", bs.getFieldValue("cDscDtFechaNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",13,13,"cRFC", bs.getFieldValue("cRFC","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",18,18,"cCURP", bs.getFieldValue("cCURP","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Licencia:","ECampo","text",13,13,"cLicencia", bs.getFieldValue("cLicencia","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Licencia Int.:","ECampo","text",18,18,"cLicenciaInt", bs.getFieldValue("cLicenciaInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");

                                    }

                                    out.println("<tr>");
                                    if(!lCaptura){
                                    out.print(vEti.EtiCampo("EEtiqueta","Pa&iacute;s de Nacimiento:","ECampo","text",10,10,"iCvePaisNac", bs.getFieldValue("cDscPaisNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","EDONAC (Lugar de Nacimiento):","ECampo","text",10,10,"iCveEstadoNac", bs.getFieldValue("cDscEstadoNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    } else {

                                      out.println("<tr>");
                                      out.println(vEti.Texto("EEtiqueta","Paa&iacute;s de Nacimiento:"));
                                      out.println("<td>");
                                      out.println(vEti.SelectOneRowSinTD("iCvePaisNac","llenaSLT(2,this.value,'','',document.forms[0].iCveEstadoNac);", dPaisNac.FindByAll(), "iCvePais", "cNombre",request, bs.getFieldValue("iCvePaisNac","&nbsp;").toString() , true));
                                      out.println("</td>");
                                      out.println("</td>");
                                      out.println(vEti.Texto("EEtiqueta","EDONAC (Lugar de Nacimiento):"));
                                      out.println("<td class='ECampo'>");
                                      TVEntidadFed vEntidadFed = new TVEntidadFed();
                                      TDEntidadFed dEntidadFed = new TDEntidadFed();
                                      Vector vcEntidadFed = new Vector();
                                      vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePaisNac","&nbsp;").toString());
                                      out.println(vEti.SelectOneRowSinTD("iCveEstadoNac","", vcEntidadFed, "iCveEntidadFed", "cNombre",request, bs.getFieldValue("iCveEstadoNac","&nbsp;").toString() , true));
                                      out.println("</td>");
                                    }

                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Expediente Anterior:","ECampo","text",55,50,4,"cExpediente", bs.getFieldValue("cExpediente","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaSTC","Domicilio",4));
                                    out.println("</tr>");
                                    /*out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Cambio de  Direcciï¿½n","ECampo","text",10,10,4,"lCambioDir", bs.getFieldValue("lCambioDir","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");*/
                                    /*out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Personal:","ECampo","text",10,10,"iCvePersonal", bs.getFieldValue("iCvePersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");*/
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Calle:","ECampo","text",55,50,4,"cCalle", bs.getFieldValue("cCalle","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Ext.:","ECampo","text",25,30,"cNumExt", bs.getFieldValue("cNumExt","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Int.:","ECampo","text",25,30,"cNumInt", bs.getFieldValue("cNumInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",33,30,"cColonia", bs.getFieldValue("cColonia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","C.P.:","ECampo","text",6,5,"iCP", bs.getFieldValue("iCP","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Ciudad:","ECampo","text",55,50,"cCiudad", bs.getFieldValue("cCiudad","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));

                                    if (!lCaptura){
                                    out.print(vEti.EtiCampo("EEtiqueta","Pa&iacute;s:","ECampo","text",10,10,"iCvePais", bs.getFieldValue("cDscPais","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","EDO (Estado):","ECampo","text",10,10,"iCveEstado", bs.getFieldValue("cDscEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","MUN (Municipio):","ECampo","text",10,10,"iCveMunicipio", bs.getFieldValue("cDscMunicipio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    
                                   out.println("<tr>");
                                    TDPERDireccion dPERDireccion = new TDPERDireccion();
                                    TDGRLLocalidad dLocalidad = new TDGRLLocalidad();
                                    
                                    String Localidad2 = "";
                                    String iCveLocalidad = "";
                                    
										String cWhere = " icvepais = "+bs.getFieldValue("iCvePais","").toString()+
												" and icveentidadfed = "+bs.getFieldValue("iCveEstado","").toString()+
												" and icvemunicipio = "+bs.getFieldValue("iCveMunicipio","").toString()+
												" and icvelocalidad = "+bs.getFieldValue("iCveLocalidad","").toString();
										Localidad2 = dLocalidad.UbicaLocalidad(cWhere);
										iCveLocalidad = ""+bs.getFieldValue("iCveLocalidad","").toString();
									
                                    out.print(vEti.EtiCampo("EEtiqueta","LOC (Población):","ECampo","text",01,10,"iCveLocalidad", Localidad2,0,"","fMayus(this);",false,true,lCaptura));
                                   
                                    
                                     }
                                    else{
                                    //out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Pa&iacute;s:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePais","llenaSLT(2,this.value,'','',document.forms[0].iCveEstado);", dPaisNac.FindByAll(), "iCvePais", "cNombre",request, bs.getFieldValue("iCvePais","&nbsp;").toString() , true));
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println(vEti.Texto("EEtiqueta","EDO (Estado):"));
                                    out.println("<td class='ECampo'>");

                                    TVEntidadFed vEntidadFed = new TVEntidadFed();
                                    TDEntidadFed dEntidadFed = new TDEntidadFed();
                                    Vector vcEntidadFed = new Vector();
                                    vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais","&nbsp;").toString());
                                    out.println(vEti.SelectOneRowSinTD("iCveEstado","llenaSLT(3,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio);", vcEntidadFed, "iCveEntidadFed", "cNombre",request, bs.getFieldValue("iCveEstado","&nbsp;").toString() , true));
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
                                    out.println("<td class='ECampo'>");
                                    TVMunicipio vMunicipio= new TVMunicipio();
                                    TDMunicipio dMunicipio = new TDMunicipio();
                                    Vector vcMunicipio= new Vector();
                                    vcMunicipio = dMunicipio.FindByAll(bs.getFieldValue("iCvePais","&nbsp;").toString(),bs.getFieldValue("iCveEstado","&nbsp;").toString());
                                    //out.println(vEti.SelectOneRowSinTD("iCveMunicipio","", vcMunicipio, "iCveMunicipio", "cNombre",request, bs.getFieldValue("iCveMunicipio","&nbsp;").toString() , true));
                                    out.println(vEti.SelectOneRowSinTD("iCveMunicipio","llenaSLT(5,document.forms[0].iCvePais.value,document.forms[0].iCveEstado.value,this.value,document.forms[0].iCveLocalidad);", vcMunicipio, "iCveMunicipio", "cNombre",request, bs.getFieldValue("iCveMunicipio","&nbsp;").toString() , true));
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","LOC (Población):"));
                                    out.println("<td class='ECampo'>");
                                    TVGRLLocalidad vLocalidad= new TVGRLLocalidad();
                                    TDGRLLocalidad dLocalidad= new TDGRLLocalidad();
                                    Vector vcLocalidad= new Vector();
                                    vcLocalidad = dLocalidad.FindByAll(bs.getFieldValue("iCvePais","&nbsp;").toString(),bs.getFieldValue("iCveEstado","&nbsp;").toString(),bs.getFieldValue("iCveMunicipio","&nbsp;").toString());
                                    out.println(vEti.SelectOneRowSinTD("iCveLocalidad","", vcLocalidad, "iCveLocalidad", "cNombre",request, bs.getFieldValue("iCveLocalidad","&nbsp;").toString() , true));
                                    
                                    
                                    }

                                    
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Tel.:","ECampo","text",10,10,4,"cTel", bs.getFieldValue("cTelefono","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");

                                    out.println(vEti.TextoCS("ETablaSTC","Examen Solicitado",4));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Modo de Transporte:","ECampo","text",10,10,"iCveMdoTrans", bs.getFieldValue("cDscMdoTransporte","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Categor&iacute;a:","ECampo","text",10,10,"iCveCategoria", bs.getFieldValue("cDscCategoria","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    /*
                                    if(ValidaCurp == 18){
                                     out.print(vEti.clsAnclaTexto("EAncla","Puesto","javascript:fPuesto("+ bs.getFieldValue("iCvePersonal","&nbsp;")+","+ bs.getFieldValue("iCveUniMed","&nbsp;") + "," + bs.getFieldValue("iCveModulo","&nbsp;") + ");","Buscar"));
                                     out.println("</td><td class='ETablaC'>");
                                     }else{
                                     String alerta = "";
                                            if(ValidaCurp == 0)
                                                alerta = "'Para continuar con su examen es obligatorio capturar el CURP,\n esto podrï¿½ hacerlo dando clic en detalle.'";
                                            else
                                                alerta= "'Para continuar con su examen es necesario corregir el CURP,\n esto podrï¿½ hacerlo dando clic en detalle.'";
                                            out.print(vEti.clsAnclaTexto("EAncla","Puesto2",
                                            "javascript:fCURRP("+alerta+");","Buscar")
                                           + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                              out.println("</tr>");
                                     }*/
                                    //out.print(vEti.EtiCampoCS("EEtiqueta","Puesto:","ECampo","text",10,10,4,"iCvePuesto", bs.getFieldValue("cDscPuesto","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    //out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Observaciones:","ECampo","text",10,10,4,"cObservacion", bs.getFieldValue("cObservacion","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    /*
                                    out.println(vEti.TextoCS("ETablaSTC","Adicionales",4));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Tel2.:","ECampo","text",10,10,4,"cTel", bs.getFieldValue("cTelefono2","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                                out.println("</tr>");*/
                                          if (!lCaptura){
                                        	  /*
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Vivienda:","ECampo","text",10,10,4,"iCveVivienda", bs.getFieldValue("cConcepto","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Discapacidad:","ECampo","text",10,10,4,"iCveDiscapacidad", bs.getFieldValue("cDcsDiscapacidad","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Grupo ï¿½tnico:","ECampo","text",10,10,4,"iCveGpoEtnico", bs.getFieldValue("cGpoEtnico","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Religiones:","ECampo","text",10,10,4,"iCveReligiones", bs.getFieldValue("cCodDsc","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Tipo Sanguï¿½neo:","ECampo","text",10,10,4,"iCveTpoSangre", bs.getFieldValue("cTpoSangre","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Nivel socioeconï¿½mico:","ECampo","text",10,10,4,"iCveNivelSEC", bs.getFieldValue("cNivelSec","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Afiliaciï¿½n o preferencia <br>por un partido polï¿½tico:","ECampo","text",10,10,4,"iCveParPol", bs.getFieldValue("cDscParPol","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Estado civil:","ECampo","text",10,10,4,"iCveECivil", bs.getFieldValue("cECivil","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                                out.println("</tr>");*/
                                          }else{
                                               /* out.println("<tr>");
                                                TDGRLVIVIENDA dGRLVIVIENDA = new TDGRLVIVIENDA();
                                                out.println(vEti.Texto("EEtiqueta","Vivienda"));
                                                out.println("<td class='ECampo' colspan='3' >");
                                                out.println(vEti.SelectOneRowSinTD("iCveVivienda", "", dGRLVIVIENDA.FindByAll(), "iCveVivienda", "cConcepto", request, "0",true));
                                                out.println("</td>");
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                TDGRLDISCAPACIDAD dGRLDISCAPACIDAD = new TDGRLDISCAPACIDAD();
                                                out.println(vEti.Texto("EEtiqueta","Discapacidad por GRUPO:"));
                                                out.println("<td>");
                                                out.println(vEti.SelectOneRowSinTD("iCveGrupoD","llenaSLT(8,this.value,'','',document.forms[0].iCveSubgrupoD);", dGRLDISCAPACIDAD.FindByAllG(), "iCveGrupoD", "cDscGrupoD", request, "0", true));
                                                out.println("</td>");
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.println(vEti.Texto("EEtiqueta","Discapacidad por SUBGRUPO:"));
                                                out.println("<td class='ECampo'>");
                                                out.println("<SELECT NAME='iCveSubgrupoD' SIZE='1' onChange=\"llenaSLT(9,document.forms[0].iCveGrupoD.value,this.value,'',document.forms[0].iCveDiscapacidad);\" ");
                                                out.println("</SELECT>");
                                                out.println("</td>");
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.println(vEti.Texto("EEtiqueta","Discapacidad:"));
                                                out.println("<td class='ECampo'>");
                                                out.println("<SELECT NAME='iCveDiscapacidad' SIZE='1' ");
                                                out.println("</SELECT>");
                                                out.println("</td>");
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                TDGRLGPOETNICO dGRLGPOETNICO = new TDGRLGPOETNICO();
                                                out.println(vEti.Texto("EEtiqueta","Grupo ï¿½tnico"));
                                                out.println("<td class='ECampo' colspan='3' >");
                                                out.println(vEti.SelectOneRowSinTD("iCveGpoEtnico", "", dGRLGPOETNICO.FindByAll(), "iCveGpoEtnico", "cGpoEtnico", request, "0",true));
                                                out.println("</td>");
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                TDGRLRELIGION dGRLRELIGION = new TDGRLRELIGION();
                                                out.println(vEti.Texto("EEtiqueta","Grupos de Religiones:"));
                                                out.println("<td>");
                                                out.println(vEti.SelectOneRowSinTD("iCveGrupo","llenaSLT(10,this.value,'','',document.forms[0].iCveSubgrupo);", dGRLRELIGION.FindByAllG(), "iCveGrupo", "cGrupo", request, "0", true));
                                                out.println("</td>");
                                                out.println(vEti.Texto("EEtiqueta","Subgrupos de Religiones:"));
                                                out.println("<td class='ECampo'>");
                                                out.println("<SELECT NAME='iCveSubgrupo' SIZE='1' onChange=\"llenaSLT(11,document.forms[0].iCveGrupo.value,this.value,'',document.forms[0].iCveReligiones);\" ");
                                                out.println("</SELECT>");
                                                out.println("</td>");
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.println(vEti.Texto("EEtiqueta","Religiones:"));
                                                out.println("<td class='ECampo'>");
                                                out.println("<SELECT NAME='iCveReligiones' SIZE='1' ");
                                                out.println("</SELECT>");
                                                out.println("</td>");
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                TDGRLTPOSANGRE dGRLTPOSANGRE = new TDGRLTPOSANGRE();
                                                out.println(vEti.Texto("EEtiqueta","Tipo Sanguï¿½neo"));
                                                out.println("<td class='ECampo' colspan='3' >");
                                                out.println(vEti.SelectOneRowSinTD("iCveTpoSangre", "", dGRLTPOSANGRE.FindByAll(), "iCveTpoSangre", "cTpoSangre", request, "0",true));
                                                out.println("</td>");
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                TDGRLNIVELSEC dGRLNIVELSEC = new TDGRLNIVELSEC();
                                                out.println(vEti.Texto("EEtiqueta","Nivel socioeconï¿½mico"));
                                                out.println("<td class='ECampo' colspan='3' >");
                                                out.println(vEti.SelectOneRowSinTD("iCveNivelSEC", "", dGRLNIVELSEC.FindByAll(), "iCveNivelSEC", "cNivelSEC", request, "0",true));
                                                out.println("</td>");
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                TDGRLPARPOL dGRLPARPOL = new TDGRLPARPOL();
                                                out.println(vEti.Texto("EEtiqueta","Afiliaciï¿½n o preferencia <br>por un partido polï¿½tico"));
                                                out.println("<td class='ECampo' colspan='3' >");
                                                out.println(vEti.SelectOneRowSinTD("iCveParPol", "", dGRLPARPOL.FindByAll(), "iCveParPol", "cDscParPol", request, "0",true));
                                                out.println("</td>");
                                                out.println("</tr>");

                                                out.println("<tr>");
                                                TDGRLECIVIL dGRLECIVIL = new TDGRLECIVIL();
                                                out.println(vEti.Texto("EEtiqueta","Estado civil"));
                                                out.println("<td class='ECampo' colspan='3' >");
                                                out.println(vEti.SelectOneRowSinTD("iCveECivil", "", dGRLECIVIL.FindByAll(), "iCveECivil", "cECivil", request, "0",true));
                                                out.println("</td>");
                                                out.println("</tr>"); */
                                          
                                          }
                                    
                                    }
                                }
                            %>
                           <tr>
                           </tr>
                          </table><% // Fin de Datos %>
  </td></tr>

  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
	
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
<%= vErrores.muestraError() /* Al final de la pï¿½gina se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
