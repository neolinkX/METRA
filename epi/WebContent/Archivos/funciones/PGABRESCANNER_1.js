//Modificacion al archivo JS que abre el cliente del scanner de finger print en la maquina cliente
// MetaCD=1.2
function fReadFromFile(iCvePersonal)
{
    var fso;
    var result = false;
    var ForReading = 1;
    var flag = 0;
    var oShell = new ActiveXObject("Shell.Application");
    var aplicacion = "C:\\fingerprint\\fingerprint.exe";
    var parametros_del_comando = "";
 
    if(iCvePersonal == "0"){
        var URL = 'http://aplicaciones3.sct.gob.mx/elic/LICDownload?ICVEPERSONAL=_CLAVE_&ICVETIPOFFH=3&IDEDO=_DEDO_';
            var expediente = iCvePersonal;
            var dedo = 2;
        parametros_del_comando =  " "+URL+" "+expediente +" "+dedo;
    }	else {
        parametros_del_comando =  " "+URL+" "+expediente +" "+dedo;
        //fAlert("ccahuellas (servidor interno)");
    }
 
    oShell.ShellExecute(aplicacion, parametros_del_comando, "", "open", "1");
    fso = new ActiveXObject("Scripting.FileSystemObject");
         
    while(flag==0){
        try{
            archivo = fso.OpenTextFile("C:\\fingerprint\\matchresult.txt", ForReading, false); 		
            result = archivo.readline();            
            archivo.Close();
            flag = 1;
        }catch(err){
            //fAlert(err);
        }
    }
    return result;
}