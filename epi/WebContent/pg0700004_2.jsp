<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.cis.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.dwr.vo.*"%>
<%@ page import="gob.sct.medprev.dwr.MedPredDwr"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Validación de Huella Dactilar</title>
    </head>
    <%
    
    String msg = "";
    if(request.getParameter("msg") == null){
    	msg = "Query =  Pais =  Ciudad =  Region =  Latitude =  Longitude =  ZonaHoraria =  ProvedorSI =  Organizacion =  ";
    }else{
    	if(request.getParameter("msg").toString().length()<10){
    		msg = "Query =  Pais =  Ciudad =  Region =  Latitude =  Longitude =  ZonaHoraria =  ProvedorSI =  Organizacion =  ";
    	}else{
    		msg = request.getParameter("msg");
    		//System.out.println(":D"+msg);
    	}
    }
    //System.out.println(msg);
    
                TParametro vParametros = new TParametro("07");
                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                vUsuario.setDirIp(msg);
                int icveexpediente = vUsuario.getIdIcveExpediente();
                //System.out.println("EXPEDIENTE HUELLA"+icveexpediente);
                int iDoctoHuellaDigital = vUsuario.getIcveDoctoHuella();//Numero de Documento de la huella del doctor
                //System.out.println("IDOCTO HUELLA"+iDoctoHuellaDigital);
                int inumeroDedoMedico = vUsuario.getiDedoAValidar();

                boolean esMedicoTercero = false;
                Vector vUsuMedicos = vUsuario.getVUsuMedicos();
                for (int i = 0; i < vUsuMedicos.size(); i++) {
                    if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveUniMed() == 107) {
                        esMedicoTercero = true;
                    }
                }
                //System.out.println("Es medico tercero "+esMedicoTercero);

                /*Se agrega validacion en javascript para toma de huella
                 * cuando ya se tiene un usuario dentro de la pagina se carga el script para abrir el scanner instalado
                 * en la maquina cliente y se le envia el número del expediente que se busca para la huella dactilar
                 */
                MedPredDwr medPrev = new MedPredDwr();
                DatosAvisoVo datos = new DatosAvisoVo();

                datos = medPrev.getDatosAviso();

    %>
    <SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>PGABRESCANNER.js">
    </SCRIPT>
    <script type='text/javascript' src='/medprev/dwr/engine.js'></script>
    <script type='text/javascript' src='/medprev/dwr/util.js'></script>
    <script type='text/javascript' src='/medprev/dwr/interface/MedPredDwr.js'></script>
    <script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>

    <script language="JavaScript">
        function ValidacionHuellaDactilarMedicos(){
            var fso;
            var result = false;
            var ForReading = 1;
            var flag = 0;
            var shell = new ActiveXObject("WScript.shell");
            //VARIABLE  para bajar las huellas del Content por medio de la clave del expediente
            //var iNoMedico = <%=icveexpediente%>;

            //VARIABLE para bajar las huellas del Content por medio de la clave del documento
            var iNoMedico = <%=iDoctoHuellaDigital%>;

            var iNoDedo = ' <%=inumeroDedoMedico%>';
            var pathProg = "c:\\fingerprint\\Sct_Fingerprint.exe ";
            
            //Ruta para bajar las huellas del Content por medio de la clave del expediente
            //var pathWS = "http://aplicaciones3.sct.gob.mx/elic/LICDownload?ICVEPERSONAL=_CLAVE_&ICVETIPOFFH=3&IDEDO=_DEDO_ ";

            //Ruta para bajar las huellas del Content por medio de la clave del documento
            var pathWS = "<%=vParametros.getPropEspecifica("RutaContextoGral")%>BajarArchivoCMServlet?LINTICVEDOCUMEN=_CLAVE_&idTipoDocumento=3&IDEDO=_DEDO_ ";

            //Ruta para PROBAR desde una maquina localmente
            //var pathWS = "http://10.33.143.52:7001/medprev/BajarArchivoCMServlet?LINTICVEDOCUMEN=_CLAVE_&idTipoDocumento=3&IDEDO=_DEDO_ ";

            shell.run(pathProg + pathWS + iNoMedico + iNoDedo);
            fso = new ActiveXObject("Scripting.FileSystemObject");
            while(flag==0){
                try{
                    archivo = fso.OpenTextFile("c:\\fingerprint\\temp\\matchresult.txt", ForReading, false);
                    result = archivo.readline();
                    archivo.Close();
                    flag = 1;
                }catch(err){
                    //alert(err);
                }
            }
            return result;
        }

        function ValidacionHuellaDactilarPacientes(){
            var fso;
            var result = false;
            var ForReading = 1;
            var flag = 0;
            var shell = new ActiveXObject("WScript.shell");
            var iNoMedico = <%=icveexpediente%>;
            //var iNoMedico = <%=iDoctoHuellaDigital%>;
            var iNoDedo = " 7";
            var pathProg = "c:\\fingerprint\\Sct_Fingerprint.exe ";
            
            //Ruta para bajar las huellas del Content por medio de la clave del expediente
            var pathWS = "http://aplicaciones3.sct.gob.mx/elic/LICDownload?ICVEPERSONAL=_CLAVE_&ICVETIPOFFH=3&IDEDO=_DEDO_ ";

            //Ruta para bajar las huellas del Content por medio de la clave del documento
            //var pathWS = "<%=vParametros.getPropEspecifica("RutaContextoGral")%>BajarArchivoCMServlet?LINTICVEDOCUMEN=_CLAVE_&idTipoDocumento=3&IDEDO=_DEDO_ ";

            //Ruta para PROBAR desde una maquina localmente
            //var pathWS = "http://10.33.143.52:7001/medprev/BajarArchivoCMServlet?LINTICVEDOCUMEN=_CLAVE_&idTipoDocumento=3&IDEDO=_DEDO_ ";

            shell.run(pathProg + pathWS + iNoMedico + iNoDedo);
            fso = new ActiveXObject("Scripting.FileSystemObject");
            while(flag==0){
                try{
                    archivo = fso.OpenTextFile("c:\\fingerprint\\temp\\matchresult.txt", ForReading, false);
                    result = archivo.readline();
                    archivo.Close();
                    flag = 1;
                }catch(err){
                    //alert(err);
                }
            }
            return result;
        }

        function Validacion(){
            /***ROberto Mireles solicita que el bloqueo de las cuentas sea solo en el dictamen*/
            /*MedPredDwr.validaAccesosIncorrectosBitacora(<%=vUsuario.getICveusuario()%>,{
                callback : function(data) {
                    if(data >= 3){
                        alert('Su cuenta a sido bloqueada, debido a que la validación dactilar excedió el número de intentos');
                        window.top.location.href  ='./pg0700004_3.jsp'
                    }else{*/
            /***ROberto Mireles solicita que el bloqueo de las cuentas sea solo en el dictamen*/
            //if(<%=esMedicoTercero%>==true){//Si ES Medico tercero
            	if (false) {
                MedPredDwr.ejecutaValidacionBiometrica(<%=vUsuario.getICveusuario()%>,{
                    callback : function(data) {//Respuesta de busqueda del metodo si se ejecuta validacion biometrica
                        if(data==0){
                            redirigirAPantallaAvisoOaPantallaPrincipal();
                        }else{
                            var V = ValidacionHuellaDactilarMedicos();
                            if(V == 'true'){
                                redirigirAPantallaAvisoOaPantallaPrincipal();
                            }else{
                                MedPredDwr.insertAccessFallidoBiometrico(<%=vUsuario.getICveusuario()%>,"",BrowserDetect.browser,BrowserDetect.version,"","",{
                                    callback : function(data) {
                                        redirigirAPantallaInicial();
                                    },
                                    scope : this
                                });
                            }
                        }
                    },
                    scope : this
                });
            }else{//Si NO es medico tercero
                redirigirAPantallaAvisoOaPantallaPrincipal();
            }
            /***ROberto Mireles solicita que el bloqueo de las cuentas sea solo en el dictamen*/
            /*}
                },
                scope : this
            });*/
            /***ROberto Mireles solicita que el bloqueo de las cuentas sea solo en el dictamen*/
        }
        
        function redirigirAPantallaAvisoOaPantallaPrincipal(){
            if('<%=datos.getMostrarAvisoConfig()%>'=='true'){
                window.location ='./pg0700009_0.jsp';
            } else {
                window.location ='./pg0700004.jsp?PagPrin=pg0700009';
            }
        }
        
        function redirigirAPantallaInicial(){            
            window.top.location.href  ='./pg0700004_3.jsp';
        }


/* SEGUNDA VERSION ANTERIOR PARA BAJAR EL ARCHIOV DE LA HUELLA CON LA CLAVE DEL EXPEDIETE
         *
        function ValidaHuella(iCveDocto)
        {
            var fso;
            var result = false;
            var ForReading = 1;
            var flag = 0;
            var oShell = new ActiveXObject("Shell.Application");
            var aplicacion = "C:\\fingerprint\\fingerprint.exe";
            var parametros_del_comando = "";

            if(iCveDocto != "0"){
                var URL = '"<%=vParametros.getPropEspecifica("RutaContextoGral")%>BajarArchivoCMServlet?LINTICVEDOCUMEN=_CLAVE_&IDEDO=_DEDO_&idTipoDocumento=3"';
                var dedo = 2;
                parametros_del_comando =  " "+URL+" "+iCveDocto +" "+dedo;
            }	else {
                parametros_del_comando =  " "+URL+" "+iCveDocto +" "+dedo;
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
                    //alert(err);
                }
            }

            return result;
        }
        */
        /* VERSION ANTERIOR PARA BAJAR EL ARCHIOV DE LA HUELLA CON LA CLAVE DEL EXPEDIETE
         *
       function ValidaHuellaURL(iCvePersonal)
        {
            var fso;
            var result = false;
            var ForReading = 1;
            var flag = 0;
            var oShell = new ActiveXObject("Shell.Application");
            var aplicacion = "C:\\fingerprint\\fingerprint.exe";
            var parametros_del_comando = "";

            if(iCvePersonal != "0"){
                var URL = '"http://aplicaciones3.sct.gob.mx/elic/LICDownload?ICVEPERSONAL=_CLAVE_&IDEDO=_DEDO_&ICVETIPOFFH=3"';
                //var URL = 'http://aplicaciones3.sct.gob.mx/elic/LICDownload?ICVEPERSONAL=_CLAVE_&ICVETIPOFFH=3&IDEDO=2';
                var expediente = iCvePersonal;
                var dedo = 2;
                parametros_del_comando =  " "+URL+" "+expediente +" "+dedo;
                //parametros_del_comando =  " 0 "+expediente ;
            }	else {
                parametros_del_comando =  " "+URL+" "+expediente +" "+dedo;
                //parametros_del_comando =  " 0 "+expediente;
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
                    //alert(err);
                }
            }

            return result;
        }
         */
    </script>
   <body  onload="Validacion();">
        <form>
   <!--           PARA PRUEBAS DE PAGINA INTERMEDIA DESCOMENTAR
            <body>
               <form>
               <p>&nbsp; </p>
               <p><a href="javascript:Validacion();">validarhuella</a> </p>
               <p><a href="javascript:muestraParametros(<%=icveexpediente%>);">Parametros</a> </p>
-->

            <p>
                <%
                            try {
                                String el = "";
                                java.util.Enumeration en = request.getParameterNames();
                                while (en.hasMoreElements()) {
                                    el = (String) en.nextElement();
                                    if (el.compareTo("Submit") != 0) {
                                        String input = "<input name=\"" + el + "\" type=\"hidden\" value=\"" + request.getParameter(el) + "\">";
                                        out.print(input);
                                    }
                                }
                            } // fin del try
                            catch (Exception e) { // catch 1
                                out.print("<strong>Excepción:</strong> ");
                                e.printStackTrace(response.getWriter());
                                out.print("<br><br></td></tr></table>");
                            }  // end catch 1
                %>
            </p>
        </form>
    </body>
</html>
