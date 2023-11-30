// Propiedades del Editor
  //var cRutaIniWebSrv = 'http://aplicaciones2.sct.gob.mx/imagenes/elic/';  // Producci�n  
  //var cRutaIniWebSrv = 'http://127.0.0.1:7001/elic/';  // Desa
  var cRutaIniWebSrv = 'http://10.33.143.52:7001/epi/';  // Desa

  var lDesarrollo = true;
  var lBtnDerecho = false; // true : Se permite utilizar el bot�n - false : No se permite.
  var msgcder = "- Esta función está inhabilitada.\nPerdone las molestias.";  
  var cAlertMsgGen = "Sistema de Licencias de la SCT:";
  //var cRutaFuncs     = cRutaIniWebSrv + 'fnc/';
  var cRutaFuncs     = cRutaIniWebSrv + 'Archivos/funciones/';
  var cRutaEstaticos = cRutaIniWebSrv + 'Archivos/wwwrooting/';
  var cRutaImgServer = cRutaEstaticos + 'img/';
  var cRutaEstilos   = cRutaEstaticos + 'estilos/';
  var cRutaAyuda     = cRutaEstaticos + 'ayuda/';
  var cRutaPlantilla = cRutaEstaticos + 'descargar/';
  var cRutaManualUsr = cRutaEstaticos + 'MUsuario/';  
  var cRutaImgLocal  = 'c:\\sct\\elic\\img\\';
  
  var cRutaADMSEGPWD  = 'http://10.33.143.52:7001/sysadmin_ext/jsp/content/mx/sct/utilidades/cambio_contrasena.jsp';

  var cEstilos = 'estilosV2.css';
  var cPagGral = 'CDPagGral.jsp';
  var cPagNva = 'CDPagNva.jsp';
  var cPagIni = 'CD/frmmi';
  var cPagFRMMI = 'CDfrmmi.jsp';  
  var cRutaProg = '/epi/digitalizacion/Requisitos/';
  
  var iNumRegLista = 10;
  var iMaxNumRegLista = 150;
  var cFGColorSelList = '000000';
  var cBGColorSelList = 'BABF1D';//'8DC3C2';
  var cBGColorGrid = "DEECEC";
  var EFolder = "DEEFF7";
  var EFolderDes = "FFFFFF";
  var iNDSADM = 13;
  var iTiempoVerificacion=8;
  var cExtensionAyuda='.pdf';
  var cColorGenJS  = 'FAFAFA';//'DDEDC9';
  var cColorGenJSFolder = '63A58C';
  var cColorLetraArbol = "BLACK";
  var cColorFondoArbol = "WHITE";
  var cColorRamaArbol = "GREEN";
  var iTimeOutSeg = 60;
  var cColorLigasWiz = '004000';
  var cColorArbolSel = 'DCE145';
  var cColorBGWiz = 'EBE6AE';
  var cColorBGPanel = 'BEEEBF';
  var cColorPesFolder = 'BEEECF';
  
  var cFormatoArchivoFirma = "BMP";
  var iAnchoImgFirma = 1500;
  var iAltoImgFirma = 500;
  var cHandHeldText = "SCT - eLicencias";

  var cRutaActiveX = cRutaIniWebSrv + 'wwwrooting/activex/';
  var cFirmaAX = cRutaActiveX + 'PadFirma.CAB#version=1,0,0,0';
  var cFirmaID = 'CLSID:0EC22B02-D57C-4638-957B-8CB581BE675D';

  var cRutaHuellaFotoFirma = "C:\\fotofirmahuella\\";
  var cRutaDoctoHuella = "C:\\Windows\\Temp\\TCS\\MEDPREV\\temp\\resultados.txt";
  var cINIHuella   = "C:\\latinid\\enroller.ini";

  //var cRutaFIEL = 'http://aplicaciones2.sct.gob.mx/cis/jsp/FIEL/firmaInternos.jsp';
  var cRutaFIEL = 'http://10.33.143.52:7001/cis/jsp/FIEL/firmaInternos.jsp';
  var cRutaElic = 'http://127.0.0.1:7001/elic/';
  
  // Requisitos Deshabilitados
  var cCfgReqOblig = "1,342,339,10,759,1087"; // A. Ortiz
  // Requisitos Mutuamente Excluyentes
  var cCfgReqMutExcl = "";  
  // En Solicitud si se presenta RFC
  var cCfgConRFCSol = "4,"; 
  // En Usuarios que Desautorizan
  var cCfgUsrDesautoriza = "1,8,2043,3707,7913,8318,8011,3626,3623,7901,3629,10372,10373,3459,11164,9008,13401,8001,13422,14116,";  

  var cRutaDocElic = "C:\\docelic\\";
  var cExtDocElic = ".pdf";
  var aMinucias = new Array();

  // Definici�n de Minucias requeridas para validar una huella
  var iNumMinucias = 30;
  aMinucias[0] = [iNumMinucias,1,0];
  aMinucias[1] = [iNumMinucias,2,1];
  aMinucias[2] = [iNumMinucias,3,1];
  aMinucias[3] = [iNumMinucias,4,0];
  aMinucias[4] = [iNumMinucias,5,0];
  aMinucias[5] = [iNumMinucias,6,0];
  aMinucias[6] = [iNumMinucias,7,1];
  aMinucias[7] = [iNumMinucias,8,1];
  aMinucias[8] = [iNumMinucias,9,0];
  aMinucias[9] = [iNumMinucias,10,0];                  
 // Extensiones permitidas para subir al contentManager
 var vTipoArch = ".bmp,.jpg,.dat,.raw,.wsq";

  var cRutaFotoExe = "c:\\latinid\\foto";
  var iUsrInternet = 1;
  var iCveUsrMP = 0;
  
     //Agreglo de  la pantalla de LicInfoAdicional, pg13011400000.js
 var aTipoDt = new Array();
                                //empleada tambi�n por pg13011600000.js
 aTipoDt[0] = ['1','ENTERO'];
 aTipoDt[1] = ['2','CARACTER'];
 aTipoDt[2] = ['3','FECHA'];
 aTipoDt[3] = ['4','LOGICO'];
 aTipoDt[4] = ['5','DECIMAL'];
 aTipoDt[5] = ['6','LISTA DE SELECCIÓN'];
 
 
  //Agreglo de  la pantalla de ,LicInfoTraLic, pg13011500000.js
  var aEtapa = new Array();

 aEtapa[0] = ['1','REQUISITOS'];
 aEtapa[1] = ['2','AUTORIZACIÓN'];
 aEtapa[2] = ['3','IMPRESIÓN'];
 
 //Verifica q la fecha inicial no sea mayor a la fecha final
//La fechas se reciben en formato "dd/mm/aaaa"
// true iguales, false solo debe ser menor.
function fComparaFecha(fechaIni, fechaFin, permitirIgual){
  var dtFechaIni, dtFechaFin;
  dtFechaIni = fechaIni.substring(6,10) + fechaIni.substring(3,5) + fechaIni.substring(0,2);
  dtFechaFin = fechaFin.substring(6,10) + fechaFin.substring(3,5) + fechaFin.substring(0,2);
  //alert(dtFechaIni + '..' + dtFechaFin);
  if (permitirIgual){
    if ( parseInt(dtFechaIni,10) <= parseInt(dtFechaFin,10) )
      return true;
    else
      return false;
  }
  else{
    if ( parseInt(dtFechaIni,10) < parseInt(dtFechaFin,10) )
      return true;
    else
      return false;
  }
}
// Variables requeridas para presentar la dependencia en las Reglas Autom�ticas
var aCamposDep = new Array();
aCamposDep[0] = [1,'Según embarcación'];

  function fCopiaArreglo(aCopiar){
	  aTemp = new Array();
	  for (var i=0; i<aCopiar.length; i++)
	    aTemp[aTemp.length] = aCopiar[i];
	  return aTemp;
   }

    /**
   * 
   * @param fso ActiveXObject 
   * @param iCvePersonal / iNoExpediente / iNoMedico
   * @param iNoDocto / documento de la huella en caso de no indicar el dedo
   * @param iDedo dedo que se usara para la valudacion de la huella
   * @param fnc funcion de retorno o guardado si la validacion es exitosa
   */
  function validaPersona(fso ,iCvePersonal ,iNoDocto , iDedo, fnc) {
		/**
		 * 1 tipo de persona 1=medico 2=paciente
		 * 2 icveexpediente
		 * 3 inodocto
		 * 4 idedo
		 * */
	  var inicio = 1;
	  /*var interval;
	  var resultFile;*/
		 if (fso.FileExists(cRutaDoctoHuella)) {
			 fso.DeleteFile(cRutaDoctoHuella,true);
		 }
		var shell = new ActiveXObject("WScript.shell");
		var pathProg = "javaw -jar C:\\Windows\\Temp\\TCS\\fingerUtilities.jar 2 " + iCvePersonal + " " + iNoDocto + " " + iDedo;
		try {
			shell.run(pathProg);
			interval = window.setInterval(function () {
				if(inicio==1){ 
					if (fso.FileExists(cRutaDoctoHuella)) {
						resultFile = fso.OpenTextFile(cRutaDoctoHuella, 1,false);
						resultado = resultFile.ReadLine();
						resultFile.Close();
						inicio = 0;
						if (resultado == 'true') {
							fnc();
						} else {
							fAlert('\nLa validaci�nn no fue exitosa y no es posible continuar con la solicitud.');
						}
						clearInterval(interval);
					} 
				}  else {
					   clearInterval(interval);
					   if (window) 
					   window.clearInterval(interval);
					   if (parentWindow)
					   parentWindow.clearInterval(interval);
			    }
		    }, 500);
		} catch (e) {
			clearInterval(interval);
			fAlert("No se encontro la aplicación ejecutable.\n" + e);
		}
	}
	
  function fTmpltImg (image) {
	return '<img src="' + cRutaImgServer + image +'" title="">';
  }
  
  function fAbreSubWindowSinPermisos(cNomPag, cAncho, cAlto) {
		if (!cAncho)
			cAncho = "800";
		if (!cAlto)
			cAlto = "600";
		fAbreSubWindow(true, cNomPag, "no", "yes", "yes", "yes", cAncho, cAlto, "",
				"");
	}