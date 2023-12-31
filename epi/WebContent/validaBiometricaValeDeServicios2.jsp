<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.cis.dao.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>Validaci�n de Huella Dactilar</title>
<%
	TParametro vParametros = new TParametro("07");

int icveexpediente = 0;
int iDoctoHuellaDigital = 0;
int inumeroDedoMedico = 0;
int idPersona = 0;
int iCveExp = 0;
int iNumExm = 0;                
boolean esMedicoTercero = false;

int icveUsuario = 0;

	/*Se agrega validacion en javascript para toma de huella
	 * cuando ya se tiene un usuario dentro de la pagina se carga el script para abrir el scanner instalado
	 * en la maquina cliente y se le envia el n�mero del expediente que se busca para la huella dactilar
	 */
	
	 
	 TVUsuario vUsuario = (TVUsuario) request.getSession(true)
			.getAttribute("UsrID");
	icveexpediente = vUsuario.getIdIcveExpediente();
	        System.out.println("EXPEDIENTE HUELLA" + icveexpediente);
	iDoctoHuellaDigital = vUsuario.getIcveDoctoHuella();//Numero de Documento de la huella del doctor
	        System.out.println("IDOCTO HUELLA" + iDoctoHuellaDigital);
	inumeroDedoMedico = vUsuario.getiDedoAValidar();
	        System.out.println("IDOCTO inumeroDedoMedico" + inumeroDedoMedico);
	idPersona = Integer.parseInt(request.getParameter("idPersona"));
	        System.out.println("idPersona HUELLA" + idPersona);
	iCveExp = Integer.parseInt(request.getParameter("iCveExp"));
	        System.out.println("iCveExp HUELLA" + iCveExp);
	iNumExm = Integer.parseInt(request.getParameter("iNumExm"));
	        System.out.println("iNumExm HUELLA" + iNumExm);
                    HashMap iDoctoHuellaDigitalPaciente = vUsuario.getIcveDoctosHuellasPaciente(request.getParameter("iCveExp"));//Numero de Documento de la huella del doctor
                          System.out.println("IDOCTO HUELLA" + iDoctoHuellaDigital);
	esMedicoTercero = false;
	icveUsuario = vUsuario.getICveusuario();
	Vector vUsuMedicos = vUsuario.getVUsuMedicos();
	for (int i = 0; i < vUsuMedicos.size(); i++) {
		if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveUniMed() == 107) {
			esMedicoTercero = true;
		}
	}
%>
	
<script type='text/javascript' src='/epi/dwr/engine.js'></script>
<script type='text/javascript' src='/epi/dwr/util.js'></script>
<script type='text/javascript'
	src='/epi/dwr/interface/MedPredDwr.js'></script>

	
	
<script	src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>

<style type="text/css">
#aep_ovrl { 
background-color: #999999;
-moz-opacity: 0.7; opacity: 0.7;
top: 0; left: 0; position: fixed;
width: 100%; z-index: 99;
} 
#aep_ww { position: fixed; z-index: 100; top: 0; left: 0; width: 100%; height: 100%; text-align: center;} 
#aep_win { margin: auto auto auto auto; width: 400px; text-align: left;} 
#aep_w {background-color: white; padding: 3px; border: 1px solid black; background-color: #EEE;} 
#aep_t {color: white; margin: 0 0 2px 3px; font-family: Arial, sans-serif; font-size: 10pt;} 
#aep_select {width: 100%;} 
#aep_w span {font-family: Arial, sans-serif; font-size: 10pt;} 
#aep_w div {text-align: right; margin-top: 5px;} 
</style>
<!-- IE specific code: -->
<!--[if lte IE 7]> 
<style type="text/css"> 
#aep_ovrl { 
position: absolute; 
filter:alpha(opacity=70); 
top: expression(eval(document.body.scrollTop)); 
width: expression(eval(document.body.clientWidth)); 
} 
#aep_ww {  
position: absolute;  
top: expression(eval(document.body.scrollTop));  
} 
</style> 
<![endif]-->	
<script type="text/javascript">
<!--
// This is variable for storing callback function 
var ae_cb = null;
 
// this is a simple function-shortcut 
// to avoid using lengthy document.getElementById 
function ae$(a) { return document.getElementById(a); } 
 
// This is a main ae_prompt function 
// it saves function callback  
// and sets up dialog 
function ae_prompt(cb, q, a) { 
	ae_cb = cb;
	ae$('aep_t').innerHTML = document.domain + 'Selecciona la huella del paciente a validar:';
	ae$('aep_prompt').innerHTML = q;
	//ae$('aep_text').value = a;
	ae$('aep_select').value = a;
	ae$('aep_ovrl').style.display = ae$('aep_ww').style.display = '';
	ae$('aep_select').focus();
	
	MedPredDwr.ejecutaValidacionBiometrica3(<%=vUsuario.getICveusuario()%>,<%=iCveExp%>,<%=iNumExm%>,{
        async: false, // synchronous call
        callback : function(data) {//Respuesta de busqueda del metodo si se ejecuta validacion biometrica
       	 if(data==0 || data==1){
             opener.Loaded();
             opener.respuestaopenpopupValidaPaciente(true);
             window.close();
            }
    	}
	 });
	
	ae$('aep_select').select();
	//ae$('aep_text').focus();
	//ae$('aep_text').select();
} 
 
// This function is called when user presses OK(m=0) or Cancel(m=1) button 
// in the dialog. You should not call this function directly. 
function ae_clk(m) { 
	// hide dialog layers  
	ae$('aep_ovrl').style.display = ae$('aep_ww').style.display = 'none';
	if (!m){
        opener.Loaded();
        opener.closepopup();
        window.close();
	}  		 
	else{
		//ae_cb(ae$('aep_text').value); // user pressed OK		
		ae_cb(ae$('aep_select').value); // user pressed OK
	} 		  
}

function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>
</head>

<script language="javascript" type="text/javascript">
function runApp(iTipoPersona,iCveExpediente,iNoDocto,iDedo) {
    var shell = new ActiveXObject("WScript.shell");
    //var params = ' 1 586133 5782545 7'; parametros para ISM
    var params = ' '+ iTipoPersona +' '+iCveExpediente+' '+iNoDocto+' '+iDedo;
    var temp = '<%=vParametros.getPropEspecifica("LOCAL_FOLDER_TEMP")%>';
    var jar = temp+'<%=vParametros.getPropEspecifica("LOCAL_JAR_ORCHESTRATOR")%>';
    //alert('jar '+jar);
    //var ejecutar = jar + params;
    var ejecutar = 'file://'+ jar + params + ' '+ temp;    
    	var fso = new ActiveXObject("Scripting.FileSystemObject");
    	if (fso.FileExists(jar)){
    		try{
    			//alert('exe '+ejecutar);
            	shell.run(ejecutar, 0, false);
            	//alert('corecto ');
            	return true;
            }catch(Ex){
            	alert(' 1 Error al ejecutar biometricos.');
            	fso=null;
            	return false;
            	
            }
    	}else{
    		fso=null;
    		alert(' 2 Error al ejecutar biometricos.');
    		return false;
    		
    	}

}

</script>

<script language="JavaScript">

        function procesoDeValidacionMedicoyPacienteDictamen(numeroDedo){
            
            var validacion = 'false';
            var idPersona = <%=idPersona%>;
            if(idPersona == 1){
                var  macAddress = "";
                var ipAddress = "";
                var computerName = "";

                /*MedPredDwr.validaAccesosIncorrectosBitacora(<%=icveUsuario%>,{
                    callback : function(data) {
                        if(data >= 3){*/
                       	if(false){                        	
                        	opener.Loaded();
                            alert('El n�mero de intentos de validaci�n dactilar del m�dico se ha sobrepasado no puede continuar con el proceso.\n  El usuario del medico actual  ha sido bloqueado, favor de contactar al administrador.');
                            opener.top.location.href  ='./pg0700004_3.jsp';
                            opener.closepopup();
                            window.close();
                        }else{
                            /*if(<%=esMedicoTercero%>==true){//Si ES Medico tercero*/
                            MedPredDwr.ejecutaValidacionBiometrica3(<%=icveUsuario%>,<%=iCveExp%>,<%=iNumExm%>,{
                                async: false, // synchronous call
                                callback : function(data) {//Respuesta de busqueda del metodo si se ejecuta validacion biometrica
                                	if(data==0 || data==2){
                                    	opener.Loaded();
                                        //redirigirAPantallaAvisoOaPantallaPrincipal();x
                                        opener.closepopup();
                                        opener.respuestaopenpopupValidaMedico(true);                                        
                                        window.close();
                                    }else{
                                        alert("Colocar huella del m�dico en el lector");
                                        var V = ValidacionHuellaDactilarMedicos();
                                        if(V == 'true'){
                                            alert('Validaci�n Biometricas del medico fue correcta');
                                            opener.Loaded();
                                            opener.closepopup();
                                        	//opener.respuestaopenpopupValidaMedico(true);
                                            MedPredDwr.insertValidacionBiometrica(<%=icveUsuario%>,ipAddress,BrowserDetect.browser,BrowserDetect.version,macAddress,computerName,{
                                                callback : function(data) {
                                                	opener.Loaded();
                                                	opener.closepopup();
                                                	opener.respuestaopenpopupValidaMedico(true);
                                                    window.close();
                                                }
                                            });
                                        }else{
                                            /*MedPredDwr.insertAccessFallidoBiometrico(<%=icveUsuario%>,"",BrowserDetect.browser,BrowserDetect.version,"","",{
                                                                                callback : function(data) {*/
                                            alert('Validacion biometrica del medico fue FALLIDA intentelo de nuevo');
                                                                                opener.Loaded();
                                            MedPredDwr.insertValidacionFallidaBiometrico(<%=icveUsuario%>,ipAddress,BrowserDetect.browser,BrowserDetect.version,macAddress,computerName,{
                                                callback : function(data) {
                                                	opener.Loaded();
                                                	opener.closepopup();
                                                    window.close();
                                                },
                                                scope : this
                                            });
                                            /*},
                                                                                scope : this
                                                                            });*/
                                        }

                                    }
                                },
                                scope : this
                            });
                            /* }else{//Si NO es medico tercero
                                            valido=true;
                                        }*/
                        }
                        /*},
                    scope : this
                });*/
            }

            //if(idPersona == 2){
            	if(idPersona == 2){//Si NO es Medico tercero*/
                var  macAddress = "";
                var ipAddress = "";
                var computerName = "";

                //MedPredDwr.validaAccesosIncorrectosBitacoraExp(<%=iCveExp%>,'<%=icveUsuario%>',{
                  //  callback : function(data) {
                        
                    //    if(data >= 3){
                    	    if(false){
                        	opener.Loaded();
                            alert('El n�mero de intentos de validaci�n dactilar del paciente ha sobrepasado no puede continuar con el proceso. \n El expediente '+<%=iCveExp%>+' ha sido bloqueado, favor de contactar al administrador.');
                            opener.closepopup();
                            window.close();
                        }else{
                            MedPredDwr.ejecutaValidacionBiometrica3(<%=icveUsuario%>,<%=iCveExp%>,<%=iNumExm%>,{
                                async: false, // synchronous call
                                callback : function(data) {//Respuesta de busqueda del metodo si se ejecuta validacion biometrica
                                	if(data==0 || data==1){
                                    	opener.Loaded();
                                        //redirigirAPantallaAvisoOaPantallaPrincipal();
                                        opener.closepopup();
                                        opener.respuestaopenpopupValidaPaciente(true);
                                        window.close();
                                    }else{
                                        alert("Colocar huella del paciente en el lector");
                                        var V2 = ValidacionHuellaDactilarPacientes(<%=iCveExp%>,numeroDedo);
                                        if(V2 == 'true'){
                                            alert('Validaci�n Biometricas del paciente fue correcta');
                                            opener.Loaded();
                                            MedPredDwr.insertValidacionBiometricaExpediente(<%=iCveExp%>,{
                                                callback : function(data) {
                                                	opener.Loaded();
                                                	opener.closepopup();
                                                	opener.respuestaopenpopupValidaPaciente(true);
                                                    window.close();
                                                },
                                                scope : this
                                            });

                                        }else{
                                            alert('Validacion biometrica del paciente fue FALLIDA intentelo de nuevo');
                                            opener.Loaded();
                                            opener.closepopup();
                                            MedPredDwr.insertValidacionFallidaBiometricoExpediente(<%=iCveExp%>,{
                                                callback : function(data) {
                                                	opener.Loaded();
                                                	opener.closepopup();
                                                    window.close();
                                                },
                                                scope : this
                                            });
                                            
                                        }	
                                    }
                                }, scope : this
                            });
                        }
                    //}
                //});
            }
        }
		

        function ValidacionHuellaDactilarMedicos(){
            
            var result = false;
            var ForReading = 1;
            var flag = 0;
            var idtipoPersona = '1';
            var icveexpediente = '<%=icveexpediente%>';
            var iDoctoHuellaDigital = '<%=iDoctoHuellaDigital%>';
            var iNoDedo = ' <%=inumeroDedoMedico%>';
            var PATH= '<%=vParametros.getPropEspecifica("LOCAL_FOLDER_TEMP")%>'+
            '<%=vParametros.getPropEspecifica("LOCAL_APP_FOLDER_NAME")%>'+
            '<%=vParametros.getPropEspecifica("LOCAL_APP_HUELLAS_TOMADA_FOLDER_NAME")%>';
            var FILE = '<%=vParametros.getPropEspecifica("LOCAL_RESULT_FILE_NAME")%>';
            var ALL = PATH+FILE;
            
            if(!runApp(idtipoPersona,icveexpediente,iDoctoHuellaDigital,iNoDedo)){
            	var myObject;
                myObject = new ActiveXObject("Scripting.FileSystemObject");
                var jar = '<%=vParametros.getPropEspecifica("LOCAL_JAR_ORCHESTRATOR")%>';
                try{
                	myObject.DeleteFile(jar);
                }catch (e) {
					// TODO: handle exception
				}               
            
            	return false;
            }
            	
            
        	var flag1=0;
            while(flag1==0){
                try{
    				var fso = new ActiveXObject("Scripting.FileSystemObject");
    				if (!fso.FileExists(ALL)){
    					flag1 = 1;
    				}
                }catch(err){
                   
                }
            }
            
            while(flag==0){
                try{
                	var fso = new ActiveXObject("Scripting.FileSystemObject");
                	if (fso.FileExists(ALL)){
                        var archivo = fso.OpenTextFile(ALL, ForReading, false);
                        result = archivo.readline();
                        archivo.Close();
                        flag = 1;
                        fso= null;
                	}
                }catch(err){
                    //alert(err);
                }
            }
            fso=null;
            return result;

        }

        function ValidacionHuellaDactilarPacientes(iCveExp,numeroDedo){
            var result = false;
            var ForReading = 1;
            var flag = 0;
            var iNoMedico = iCveExp;
            //var iNoMedico = <%=icveexpediente%>;
            //var iNoMedico = <%=iDoctoHuellaDigital%>;
            //var iNoDedo = " 7";
            var iNoDedo = " "+numeroDedo;
           
            <% 	
            //obtain an Iterator for Collection
            Iterator it = iDoctoHuellaDigitalPaciente.entrySet().iterator();
            
            

            //iterate through HashMap values iterator
            out.println("var idoctohuella = 0;");
            while(it.hasNext()){
            	Map.Entry e = (Map.Entry)it.next();
            	out.println("if(iNoDedo == ' "+e.getKey()+"')idoctohuella  = " + e.getValue()+";");

            }
            %>            
            
            var result = false;
            var ForReading = 1;
            var flag = 0;
            var idtipoPersona = '2';//paciente
            var icveexpediente = ''+iCveExp;
            var iDoctoHuellaDigital = ''+idoctohuella;
            var iNoDedo = ''+numeroDedo;
            var PATH= '<%=vParametros.getPropEspecifica("LOCAL_FOLDER_TEMP")%>'+
            '<%=vParametros.getPropEspecifica("LOCAL_APP_FOLDER_NAME")%>'+
            '<%=vParametros.getPropEspecifica("LOCAL_APP_HUELLAS_TOMADA_FOLDER_NAME")%>';
            var FILE = '<%=vParametros.getPropEspecifica("LOCAL_RESULT_FILE_NAME")%>';
            var ALL = PATH+FILE;
            
            if(!runApp(idtipoPersona,icveexpediente,iDoctoHuellaDigital,iNoDedo)){
            	var myObject;
                myObject = new ActiveXObject("Scripting.FileSystemObject");
                var jar = '<%=vParametros.getPropEspecifica("LOCAL_JAR_ORCHESTRATOR")%>';
                try{
                	myObject.DeleteFile(jar);
                }catch (e) {
					// TODO: handle exception
				}               
            
            	return false;
            }
            
        	var flag1=0;
            while(flag1==0){
                try{
    				var fso = new ActiveXObject("Scripting.FileSystemObject");
    				if (!fso.FileExists(ALL)){
    					flag1 = 1;
    				}
                }catch(err){
                   
                }
            }
            
            while(flag==0){
                try{
                	var fso = new ActiveXObject("Scripting.FileSystemObject");
                	if (fso.FileExists(ALL)){
                        var archivo = fso.OpenTextFile(ALL, ForReading, false);
                        result = archivo.readline();
                        archivo.Close();
                        flag = 1;
                        fso= null;
                	}
                }catch(err){
                    //alert(err);
                }
            }
            fso=null;
            return result;
        }

    </script>
    
    <!-- -->
<%
if(idPersona==1){
%>
	<body onLoad="procesoDeValidacionMedicoyPacienteDictamen();">	
<%}
if(idPersona==2){
%>
	<body onLoad="ae_prompt(procesoDeValidacionMedicoyPacienteDictamen, 'Selecciona el dedo del paciente que deseas validar', '2');">
<% 	
}
%>
	<div id="aep_ovrl" style="display: none;">&nbsp;</div>
	<div id="aep_ww" style="display: none;">
	<div id="aep_win"><div id="aep_t"></div>
	<div id="aep_w"><span id="aep_prompt"></span>
    <select name="aep_select" id="aep_select" onChange="">
       <option value="2" selected>Indice Derecho</option>
       <option value="3">Medio Derecho</option>
       <option value="7">Indice Izquierdo</option>
       <option value="8">Medio Izquierdo</option>       
     </select>
	<div><input type="button" id="aep_ok" onClick="ae_clk(1);" value="OK">
	<input type="button" id="aep_cancel" onClick="ae_clk(0);" value="Cancel">
	</div></div>
	</div>
	</div>	
	<form><!-- -->
	
	
		<!--  PARA PRUEBAS DE PAGINA INTERMEDIA DESCOMENTAR
            <body>
               <form>
               <p>&nbsp; </p>
               <p><a href="javascript:ValidacionHuellaDactiar();">validarhuella</a> </p>
               <p><a href="javascript:muestraParametros(<%=icveexpediente%>);">Parametros</a> </p>
               <p><a href="javascript:runApp(<%=icveexpediente%>);">Ejecuta local JAR</a> </p>
             -->
		<p>
			<%
				try {
					String el = "";
					java.util.Enumeration en = request.getParameterNames();
					while (en.hasMoreElements()) {
						el = (String) en.nextElement();
						if (el.compareTo("Submit") != 0) {
							String input = "<input name=\"" + el
									+ "\" type=\"hidden\" value=\""
									+ request.getParameter(el) + "\">";
							out.print(input);
						}
					}
				} // fin del try
				catch (Exception e) { // catch 1
					out.print("<strong>Excepci�n:</strong> ");
					e.printStackTrace(response.getWriter());
					out.print("<br><br></td></tr></table>");
				} // end catch 1
			%>
		</p>
	</form>

</body>
</html>
