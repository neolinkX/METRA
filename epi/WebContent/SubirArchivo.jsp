<%@ page import="java.util.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.cntmgr.placas.*"%>

<% 
TEntorno    vEntorno      = new TEntorno();
vEntorno.setNumModulo(07);
TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
//Verificar Hora para cargar archivos del log
Calendar calendar = Calendar.getInstance();
int hora = calendar.get(Calendar.HOUR_OF_DAY);
int horaCargaImgServMed = Integer.parseInt(vParametros.getPropEspecifica("HoraCargaImgServMedNasLog"));
String msg = "";
/*
Este segmento fue comentado debido a que se optimizo la carga y comprobacion de la carga de documentos
if(hora>=horaCargaImgServMed){
	//System.out.println("Cargar imagenes nuevamente");
	AdministradorContenidosArchivoLog cargarlog = new AdministradorContenidosArchivoLog();
	try{
		//subir.CargaDeServiciosDesdeNas(desde, hasta);
		cargarlog.LeerLineasDeArchivo();
		msg="La carga fue exitosa";
	}catch(Exception ex){
		System.out.println("error al realizar la carga\n"+ex);
		msg="La carga tuvo errores";
	}
	//System.out.println(msg);
}else{
	//System.out.println("No cargar servicios medicos");
}
*/
%>


<html>
<head>
<script language="JavaScript" type="text/JavaScript">
<!--

function getValues(){
    var arrValores = leerGET();
    var uno = arrValores[0].split("="); 
    var dos = arrValores[1].split("=");
    var tres = arrValores[2].split("=");
    var cuatro = arrValores[3].split("=");
    var cinco = arrValores[4].split("=");
    var frm = document.forms[0];
    frm.iCveExpediente.value = uno[1];
    frm.iNumExamen.value = dos[1];
    frm.iCveServicio.value = tres[1];
    frm.iCveRama.value = cuatro[1];
    frm.iCveUsuario.value = cinco[1];
    
}   
function leerGET(){
  var cadGET = location.search.substr(1,location.search.length);
  var arrGET = cadGET.split("&");
  return arrGET;
 }

function validaTipoArchivo(){
    	var flag = 0;
	var frm = document.forms[0];
	var ruta = frm.FICHERO.value;
	var archivoValido = false;
	if (ruta.indexOf('.jpg')!=-1 || ruta.indexOf('.jpeg')!=-1) {
		//archivoValido = validaSize(frm.FICHERO.value);
                archivoValido = true; 
	}
	if(archivoValido){
		if(flag<1){
			flag++;
                        ocultarBoton();
			frm.submit();
		}else{
			alert('El archivo ya ha sido enviado para su guardado');
		}
	}else{
		alert('El formato del archivo debe de ser jpg o  jpeg');
	}        
	return archivoValido;
}
function validaSize(strFilePath) {
    var fso, f, s;
    fso = new ActiveXObject("Scripting.FileSystemObject");
    if (fso.FileExists(strFilePath)) {
        f = fso.GetFile(strFilePath);
        s = f.Size;
        return true;
    } else {
        return false;
    }
}
function on(o){
	o.style.background="#ffabab";
	o.style.cursor="hand";
}
function off(o){
	o.style.background="#999999";
	o.style.cursor="pointer";
}
function ocultarBoton(){
    var boton = document.getElementById('boton');
    boton.style.display='';
}


//-->
</script>
<title>Seleccion de archivo</title>
<style type="text/css">
<!--
.style1 {
	font-family: Arial, Helvetica, sans-serif;
	color: #FFFFFF;
	font-weight: bold;
}
.style2 {
	font-family: Geneva, Arial, Helvetica, sans-serif;
	color: #800000;
	font-size: 12px;
}
.style3 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; }
-->
</style>
</head>
<body onLoad='getValues();'  background="http://aplicaciones2.sct.gob.mx/imagenes/medprev/img/medprev/fondo01.jpg">
    <form action="AdministradorContenidosServlet" method="POST"
          enctype="multipart/form-data">
     <input type="hidden" name="iCveExpediente" value="0">
     <input type="hidden" name="iNumExamen" value="0">
     <input type="hidden" name="iCveServicio" value="0">
     <input type="hidden" name="iCveRama" value="0">
     <input type="hidden" name="iCveUsuario" value="0">

<table  background="http://aplicaciones2.sct.gob.mx/imagenes/medprev/img/medprev/fondo01.jpg" width="100%" border="0">
  <tr>
    <th colspan="3" scope="row"><span class="style2">NOTA: El nombre del archivo no debe contener espacios, ni caracteres especiales. (jpg o jpeg) </span></th>
    </tr>
  <tr>
    <th colspan="2" scope="row"><div align="right"><span class="style3">Selecciona al archivo a subir:</span></div></th>
    <td width="316"><input type="file" name="FICHERO" id="FICHERO" size="50"></td>
  </tr>
  <tr>
    <th width="60" scope="row">&nbsp;</th>
    <td width="310"><div class="style1" style="background:#999999" onMouseOver="on(this);" onMouseOut="off(this);" onClick="validaTipoArchivo();">
            <input type="submit">
      <div id="boton" name="boton" align="center" style="CURSOR: hand">GUARDAR</div>

    </div></td>
    <td>&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>
