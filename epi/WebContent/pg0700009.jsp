
<%
	/**
	 * Title:        Administraci�n y Seguridad
	 * Description:  M�dulo de Administraci�n y Seguridad
	 * Copyright:    Copyright (c) 2003
	 * Company:      Tecnolog�a Inred S.A. de C.V.
	 * @author       Lic Jaime Enrique Su�rez Romero
	 * @Modificado por Lic AG SA L
	 * @version 1.0
	 * Clase:        JSP para prueba de ingenier�a
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="gob.sct.cis.dao.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.dwr.vo.ClaseDatosInicio"%>
<%@ page import="gob.sct.medprev.dwr.MedPredDwr"%>
<%@ page import="java.io.*"%>
<% 	/**
	* Validacion de sesion iniciada
	* @author Ing. Andres Esteban Bernal Muñoz - 16/05/2014
	*/
      if(request.getSession(true).getAttribute("UsrID")==null){    	    
    	    response.sendRedirect("pg0700000.jsp");
    	    return;    	  
      } //Fin validacion %>

<html>
<!-- Version 13-12-2017 14:30 pm -->
<%



	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
	vEntorno.setArchFuncs("FValida");
	vEntorno.setArchTooltips("07_Tooltips");
	vEntorno.clearListaImgs();
	vEntorno.setMetodoForm("POST");
	vEntorno.setActionForm("pg0700009.jsp");
	vEntorno.setNombrePagina("pg0700009.jsp");
	vEntorno.setOnLoad("fOnLoad();");
	vEntorno.setArchFCatalogo("FFiltro");
	vEntorno.setArchAyuda(vEntorno.getNombrePagina());

	String cRutaImg = vParametros.getPropEspecifica("RutaImg");
	String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
	String cRutaAyuda = vParametros.getPropEspecifica("html");
	ClaseDatosInicio datosUsuario = new ClaseDatosInicio();
	MedPredDwr medPred = new MedPredDwr();

	TVUsuario vUsuario = (TVUsuario) request.getSession(true)
			.getAttribute("UsrID");
	
	///Registrando Accesos en session
	if(vUsuario.getContadorAcceso()<0){
		vUsuario.setContadorAcceso(1);
	}else{
		vUsuario.setContadorAcceso(vUsuario.getContadorAcceso()+1);
	}
	
	
	ACMCFG.setACMCFG("07");
	/////////  CONRTOL DE ACCESO PWD  ////////
	SEGAccPwd dSEGAccPwd = new SEGAccPwd();
	int expira = 0;
	//System.out.println("usuario = "+ vUsuario.getICveusuario());
	expira = dSEGAccPwd.Expira(vUsuario.getICveusuario());
%>
<script type='text/javascript' src='/medprev/dwr/engine.js'></script>
<script type='text/javascript' src='/medprev/dwr/util.js'></script>
<script type='text/javascript'
	src='/medprev/dwr/interface/MedPredDwr.js'></script>
<script language="JavaScript">
var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
var cPagina = '<%=vEntorno.getNumModuloStr()%>';
var cAyuda = '<%=vEntorno.getArchAyuda()%>';


	function fGetFile(cLocal,cURL){
	  oHTP.cArchivoLocal = cLocal;
	  oHTP.cURLArchivo = cURL;
	  oHTP.fGetFile();
	}
	
	
	function fGetFile2(cLocal, cURL) {
		oHTP.cArchivoLocal = cLocal;
		oHTP.cURLArchivo = cURL;
		oHTP.fGetFile();
	}
	

	function ReadFile() {
		var fso, f1, ts, s;
		var ForReading = 1;
		fso = new ActiveXObject("Scripting.FileSystemObject");
		f = fso.OpenTextFile("C:\\Windows\\Temp\\Win07v4", ForReading);
		//alert("resultado1 = "+f.ReadAll());
		return (f.ReadAll());
	}

	function ReadFile2() {
		var fso, f1, ts, s;
		var ForReading = 1;
		fso = new ActiveXObject("Scripting.FileSystemObject");
		f = fso.OpenTextFile("C:\\Windows\\Temp\\Win07hn", ForReading);
		//alert("resultado2 = "+f.ReadAll());
		return (f.ReadAll());
	}

	function ReadFile3() {
		var fso, f1, ts, s;
		var ForReading = 1;
		fso = new ActiveXObject("Scripting.FileSystemObject");
		var FileName = "C:\\Windows\\Temp\\Win07ne";
		//f = fso.OpenTextFile("C:\\Windows\\Temp\\Win07ne", ForReading);
		var ForReading = 1;
		var ForWriting = 2;
		var ForAppending = 8;
		var TristateUseDefault = -2;
		var TristateTrue = -1;
		var TristateFalse = 0;
		f = fso.OpenTextFile(FileName, ForReading, true, TristateTrue);
		//alert("resultado3 = "+f.ReadAll());
		return (f.ReadAll());
	}

	function ReadFile4() {
		var fso, f1, ts, s;
		var ForReading = 1;
		fso = new ActiveXObject("Scripting.FileSystemObject");
		//f = fso.OpenTextFile("C:\\Windows\\Temp\\Win07sn", ForReading);
		var FileName = "C:\\Windows\\Temp\\Win07sn";
		var ForReading = 1;
		var ForWriting = 2;
		var ForAppending = 8;
		var TristateUseDefault = -2;
		var TristateTrue = -1;
		var TristateFalse = 0;
		f = fso.OpenTextFile(FileName, ForReading, true, TristateTrue);
		//alert("resultado4 = "+f.ReadAll());
		return (f.ReadAll());
	}
</script>

<!-- Piwik -->
<script type="text/javascript">
	var _paq = _paq || [];
	_paq.push(['trackPageView']);
	_paq.push(['enableLinkTracking']);
	(function() {
		var u="//207.248.168.150/pwk/";
		_paq.push(['setTrackerUrl', u+'piwik.php']);
		_paq.push(['setSiteId', 8]);
		var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
		g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);
	})();
</script>
<noscript>
	<p>
		<img src="//207.248.168.150/pwk/piwik.php?idsite=8" style="border: 0;"
			alt="" />
	</p>
</noscript>
<!-- End Piwik Code -->




<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700009.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery-1.6.4.min.js"></SCRIPT>
<style>
#cuadroFlotante {
	position: absolute;
	left: 20px;
	top: 0; //
	background-color: #11913E;
	background: rgba(255, 255, 255, 0.7);
	color: #000;
	padding: 20px; //
	filter: alpha(opacity = 50);
}
</style>

<%
	new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */
%>
<link rel="stylesheet"
	href="<%=vParametros.getPropEspecifica("RutaCSS")
					+ vParametros.getPropEspecifica("HojaCSS")%>"
	TYPE="text/css">
<body bgcolor="<%=vParametros.getPropEspecifica("ColorFondoPagina")%>"
	topmargin="0" leftmargin="0" onLoad="<%=vEntorno.getOnLoad()%>;history.go(+1)">
	<form method="<%=vEntorno.getMetodoForm()%>"
		action="<%=vEntorno.getActionForm()%>">
		<input type="hidden" name="hdBoton" value="">
		<table border="0" width="100%" height="100%" cellspacing="0"
			cellpadding="0" background=" <%=cRutaImg%>relleno2.jpg">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<!--<tr>
            <td width="50%" align="right" valign="bottom">
                  <img SRC="<%=cRutaImg%>inicio01.jpg">
            </td>
            <td valign="middle" rowspan="2">
                  <img SRC="<%=cRutaImg%>inicio03.jpg">
            </td>
          </tr>
          <tr>
            <td  align="right" valign="top">
                  <img SRC="<%=cRutaImg%>inicio02.jpg">
            </td>
          </tr>-->
						<%
							datosUsuario = medPred.getDatosInicio(vUsuario.getICveusuario());
							//datosUsuario = new ClaseDatosInicio();
						%>
						<div id="cuadroFlotante">
							<p>
								<strong>Nombre:</strong>
								<%=datosUsuario.getNombreCompleto()%></p>
							<p>
								<strong>RFC:</strong>
								<%=datosUsuario.getRfc()%></p>
							<p>
								<strong>C&eacute;dula:</strong>
								<%=datosUsuario.getCedula()%></p>
							<p>
								<strong>Especialidad:</strong>
								<%=datosUsuario.getEspecialidad()%></p>
							<p>
								<strong>Domicilio:</strong>
								<%=datosUsuario.getDomLaboral()%></p>
							<p>
								<strong>CLUES:</strong>
								<%=datosUsuario.getClues()%></p>
						</div>
						<tr>
							<td align="center"><img SRC="<%=cRutaImg%>inicio02.jpg">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2" valign="top"><marquee
									class="MRQ" align="center" scrollamount="1" scrolldelay="10"
									direction="up" width="600" height="125">
									MENSAJES DE LA D.G.P.M.P.T.<br> <br><%=vParametros.getPropEspecifica("msginicio").toUpperCase()%>
								</marquee></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<%
			if (expira < 6) {
		%>
		<script language="JavaScript">fExpira(<%=expira%>);</script>
		<%
			}
		%>
		<input name="msg" size="1000" type="hidden"
			value="<%=vUsuario.getDirIp()%>" /> <input name="Win07df"
			type="hidden" value="" /> <br> <input name="Win07v4"
			type="hidden" value="" /> <br> <input name="Win07hn"
			type="hidden" value="" /> <br><input name="Win07me"
			type="hidden" value="" /> <br><input name="Win07sn"
			type="hidden" value="" /> <br>
	</form>
	<div>
		<object id="oHTP" classid="clsid:4CCB05E0-E1BB-4999-A3BB-84172549A276"
			codebase="/medprev/activex/HTTPGetProj1.ocx" width=1 height=1
			align=center hspace=0 vspace=0></object>
	</div>
	
</body>
<%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<script>
	$(document).ready(function() {
        var posicion = $("#cuadroFlotante").offset();
        var margenSuperior = 15;
        $("#cuadroFlotante").stop().animate({
                     marginTop: margenSuperior
                 });
         $(window).scroll(function() {
             if ($(window).scrollTop() > posicion.top) {
                 $("#cuadroFlotante").stop().animate({
                     marginTop: $(window).scrollTop() - posicion.top + margenSuperior
                 });
             } else {
                 $("#cuadroFlotante").stop().animate({
                     marginTop: margenSuperior
                 });
             };
         }); 
});
</script>
<script
	src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
	
<%
//System.out.println("Acceso ="+vUsuario.getContadorAcceso());
if(vUsuario.getContadorAcceso()==1){
%>	
<script language="JavaScript">
/////Obteniendo valores de session
	//alert("secuencia");
	form = document.forms[0];
	var datos2 = form.msg.value;
	//alert("datos2"+datos2);
	var partir = datos2.split('Query = ');
	var partir2 = partir[1].split('Pais = ');
	var real = partir2[0];
	
	partir = datos2.split('Pais = ');
	partir2 = partir[1].split('Ciudad = ');
	var pais = partir2[0];
	
	partir = datos2.split('Ciudad = ');
	partir2 = partir[1].split('Region = ');
	var ciudad = partir2[0];
	
	partir = datos2.split('Region = ');
	partir2 = partir[1].split('Latitude = ');
	var region = partir2[0];
	
	partir = datos2.split('Latitude = ');
	partir2 = partir[1].split('Longitude = ');
	var lat = partir2[0];
	
	partir = datos2.split('Longitude = ');
	partir2 = partir[1].split('ZonaHoraria = ');
	var lon = partir2[0];
	
	partir = datos2.split('ZonaHoraria = ');
	partir2 = partir[1].split('ProvedorSI = ');
	var ZH = partir2[0];
	
	partir = datos2.split('ProvedorSI = ');
	partir2 = partir[1].split('Organizacion = ');
	var provsi = partir2[0];
	
	
	partir = datos2.split('Organizacion = ');
	var org = partir[1];



    var  macAddress = "";
    var ipAddress = "";
    var computerName = "";
    var fso = new ActiveXObject("Scripting.FileSystemObject");
	 aplica="C:/Windows/";
     //alert(aplica);
	 folderBool = fso.FolderExists(aplica);
	 if (!folderBool){
	 	fso.CreateFolder(aplica);
	 	//alert("No existe "+aplica);
	 }
	 aplicados="C:/Windows/Temp/";
	//alert(aplicados);
	 folderBooll = fso.FolderExists(aplicados);
	 if (!folderBooll){
	 	fso.CreateFolder(aplicados);
	 	//alert("No existe "+aplicados);
	 }
	 var revision = "C:/Windows/Temp/FingerInfo.jar";
	 var folderExistencia = fso.FileExists(revision);
	//alert("exite el folder? " + folderExistencia);
	 if (!folderExistencia){
		 //alert("No existe el archivo");
		 fGetFile("C:/Windows/Temp/FingerInfo.jar","<%=""
					+ new TParametro("07").getPropEspecifica("UrlImagenes")
					+ "FingerInfo.jar"%>");
		 setTimeout(function(){},5000);
	 }
	 
	 
	 //var fsx = new ActiveXObject("Scripting.FileSystemObject");
	 var exito = false;
	 var batt = "C:/Windows/Temp/Win07v5.bat";
	 var v44 = "C:/Windows/Temp/Win07v4";
	 var hnn = "C:/Windows/Temp/Win07hn";
	 var hne = "C:/Windows/Temp/Win07ne";
	 var hsn = "C:/Windows/Temp/Win07sn";
		var archiExist1 = fso.FileExists(batt);
		var archiExist2 = fso.FileExists(v44);
		var archiExist3 = fso.FileExists(hnn);
		var archiExist4 = fso.FileExists(hne);
		var archiExist5 = fso.FileExists(hsn);
		if (!archiExist1) {
			fGetFile("C:/Windows/Temp/Win07v5.bat","<%=""
					+ new TParametro("07").getPropEspecifica("UrlImagenes")
					+ "Win07v5.bat"%>");
			/*alert("zip");
			fGetFile("C:/Windows/Temp/Win07v4.zip","http://10.33.143.52:7001/medprev/applet/"
					+ "Win07v4.zip");
			alert("zip2");*/
			
			setTimeout(function(){},5000);
			//alert("No existe se descarga 2");
		} 

		
		
		var Win07df = "";		
		var Win07v4 = "";
		var Win07me = "";
		var Win07sn = "";
		var texto = "";
		var texto2 = "";
		var texto3 = "";
		var texto4 = "";
		form = document.forms[0];
		if (!(archiExist2 && archiExist3 && archiExist4 && archiExist5)) {
			//alert("no existe los archivos de datos 3");
			var fsx2 = new ActiveXObject("Shell.Application");
			var comando = "C:/Windows/Temp/Win07v5.bat";
			setTimeout(function(){},5000);
			fsx2.ShellExecute(comando);

			exito = true;
		}else{
			//alert("Si existen los archivos de datos");
			exito = true;			
		}
		
		if(exito == true){
			if (archiExist2) {
				//alert("Si extiste el archivo 2");
				setTimeout(function(){},5000);
				texto = ReadFile();
	
				////Obtener fis
				var elem = texto.split('   Direcci¢n f¡sica. . . . . . . . . . . . . : ');
				var elem2 = elem[1].split('   DHCP habilitado ');
				Win07df = elem2[0];
	
				////Obtener V4
				elem2 = texto.split('   Direcci¢n IPv4. . . . . . . . . . . . . . : ');
				var elem3 = elem2[1].split('(');
				Win07v4 = elem3[0];
				////Imprimiendo datos
				form.Win07df.value = Win07df;
				form.Win07v4.value = Win07v4;
				
				//alert(Win07df);
				//alert(Win07v4);
			}else{
				//alert("No existe el archivo2");
			}

			////Obtener HN
			if (archiExist3) {
				//alert("Si existe el archivo3");
				texto2 = ReadFile2();
				form.Win07hn.value = texto2;
			}
			
			if (archiExist4) {
				//alert("Si extiste el archivo 4");
				setTimeout(function(){},5000);
				texto3 = ReadFile3();
	
				////Obtener mod equipo
				//alert("3="+texto3);
				var elem = texto3.split('Name');
				//alert(elem.size());
				//var elem2 = elem[1];
				//Win07me = elem2[0];
				Win07me = elem[1];
				//alert(Win07me);
			}
			
			if (archiExist5) {
				//alert("Si extiste el archivo 5");
				setTimeout(function(){},5000);
				texto4 = ReadFile4();
	
				////Obtener mod equipo
				//alert(texto4);
				var elem = texto4.split('SerialNumber');
				//alert(elem.size());
				//var elem2 = elem[1];
				//Win07me = elem2[0];
				Win07sn = elem[1];
					
				//alert(Win07sn);
			}

			exito = true;
		}
	 
	 
	 
	//alert(BrowserDetect.browser);
	//alert(BrowserDetect.version);
	 
	if (BrowserDetect.browser == 'Explorer' && parseInt(BrowserDetect.version,10) >= 9) {
	//alert("entro a explorer 9 o mayor");
        var shell = new ActiveXObject("WScript.shell");
    
        while(!folderExistencia){
        	folderExistencia = fso.FileExists(revision);
        }
        if (folderExistencia) {
            var pathProg = "javaw -jar c:\\Windows\\Temp\\FingerInfo.jar ";
            shell.run(pathProg);
            var fso, ts;
            var ForReading = 1;
            setTimeout(function(){
            	fso = new ActiveXObject("Scripting.FileSystemObject");
                // Leer los contenidos del archivo.
                ts = fso.OpenTextFile("C:\\Windows\\Temp\\FingerPrintInf.txt", ForReading);
                ipAddress = ts.ReadLine();
                computerName = ts.ReadLine();
                macAddress =ts.ReadLine();
                //Response.Write("Contenido del archivo= '" + s + "'");
                ts.Close();
                
                if(macAddress==null || macAddress == ''){
                	macAddress = Win07df;
                }
                if(computerName==null || computerName == ''){
                	computerName = texto2;
                }
                if(ipAddress==null || ipAddress == ''){
                	ipAddress = Win07v4;
                }
                
                MedPredDwr.insertAccess(<%=vUsuario.getICveusuario()%>,ipAddress,BrowserDetect.browser,BrowserDetect.version,macAddress,computerName,
                		real,pais,ciudad,region,lat,lon,ZH,provsi,org,Win07me,Win07sn,{
                    callback : function(data) {
                            if(data == 1){
                               //alert("Prueba Superada");
                           }
                    },
                    scope : this
                });
            },2000);
            
        
        } else {
        	        
                MedPredDwr.insertAccess(<%=vUsuario.getICveusuario()%>,Win07v4,BrowserDetect.browser,BrowserDetect.version,Win07df,texto2,real,pais,ciudad,region,lat,lon,ZH,provsi,org,Win07me,Win07sn,{
                callback : function(data) {
                    if(data == 1){
                       //alert("Prueba Superada");
                    }
                },
                scope : this
            });
    
        }
    
    } else {
    	//alert("version menor a 9");
        var wmi = GetObject("winmgmts:{impersonationLevel=impersonate}");
        e = new Enumerator(wmi.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration WHERE IPEnabled = True"));
        for(; !e.atEnd(); e.moveNext()) {
            var s = e.item();
            macAddress = s.MACAddress;
            ipAddress = s.IPAddress(0);
            computerName = s.DNSHostName;
        }
        

        if(macAddress==null || macAddress == ''){
        	macAddress = Win07df;
        }
        if(computerName==null || computerName == ''){
        	computerName = texto2;
        }
        if(ipAddress==null || ipAddress == ''){
        	ipAddress = Win07v4;
        }        
        
        MedPredDwr.insertAccess(<%=vUsuario.getICveusuario()%>,ipAddress,BrowserDetect.browser,BrowserDetect.version,macAddress,computerName,real,pais,ciudad,region,lat,lon,ZH,provsi,org,Win07me,Win07sn,{
            callback : function(data) {
                    if(data == 1){
                       //alert("Prueba Superada");
                   }
            },
            scope : this
        });
    }
	
	
	 var rv = -1; // Return value assumes failure.
	  if (navigator.appName == 'Microsoft Internet Explorer')
	  {
	    var ua = navigator.userAgent;
	    var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
	    if (re.exec(ua) != null)
	      rv = parseFloat( RegExp.$1 );
	  }
	 //alert(rv);

</script>

<%} %>
</html>
