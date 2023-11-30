<%@ page import="gob.sct.medprev.*" %>
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






<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Validación de Huella Dactilar</title>
    </head>
    <%
                TParametro vParametros = new TParametro("07");
                int icveexpediente = Integer.parseInt(request.getParameter("iCveExpediente"));
              //        System.out.println("EXPEDIENTE HUELLA popup "+icveexpediente);
                int iDoctoHuellaDigital = Integer.parseInt(request.getParameter("iCveDocto"));
              //        System.out.println("IDOCTO HUELLA popup "+iDoctoHuellaDigital);
                int idPersona = Integer.parseInt(request.getParameter("idPersona"));
              //        System.out.println("ID PERSONA popup "+idPersona);
                /*Se agrega validacion en javascript para toma de huella
                 * cuando ya se tiene un usuario dentro de la pagina se carga el script para abrir el scanner instalado
                 * en la maquina cliente y se le envia el número del expediente que se busca para la huella dactilar
                 */

    %>
    <script language="JavaScript">
        function ValidacionHuellaDactiarDoctor(){
            var idPersona = <%=idPersona%>;
            var validacion = 'false';

            if(idPersona == 1){
                validacion=ValidaHuellaDoc(<%=iDoctoHuellaDigital%>);
                opener.ValidacionHuellaDactiar2(validacion);
                window.close();
            }
            if(idPersona == 2){
                validacion=ValidaHuellaDoc2(<%=icveexpediente%>);
                opener.ValidacionHuellaDactiar3(validacion);
                window.close();
            }
        }
        function ValidacionHuellaDactiar(){
            var idPersona = <%=idPersona%>;
            var validacion = 'false';

            if(idPersona == 1){
                validacion=ValidaHuellaDoc(<%=iDoctoHuellaDigital%>);
                opener.ValidacionHuellaDactiar2(validacion);
                window.close();
            }
            if(idPersona == 2){
                validacion=ValidaHuellaDoc2(<%=icveexpediente%>);
                opener.ValidacionHuellaDactiar3(validacion);
                window.close();
            }            
        }

        function ValidaHuellaDoc(iCveDocto)
        {
            var fso;
            var exe;
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

            exe = oShell.ShellExecute(aplicacion, parametros_del_comando, "", "open", "1");
            fso = new ActiveXObject("Scripting.FileSystemObject");

            while(flag==0){
                try{
                    archivo = fso.OpenTextFile("C:\\fingerprint\\matchresult.txt", ForReading, false);
                    result = archivo.readline();
                    archivo.Close();
                    archivo = fso.GetFile("C:\\fingerprint\\matchresult.txt");
                    archivo.Delete();                    
                    flag = 1;                    
                }catch(err){
                    //alert(err);
                }
            }

            return result;
        }

        function ValidaHuellaDoc2(iCvePersonal)
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
                    archivo = fso.GetFile("C:\\fingerprint\\matchresult.txt");
                    archivo.Delete();
                       flag = 1;
                }catch(err){
                    //alert(err);
                }
            }
            return result;
        }

    </script>
    <%
                /*Termina el agregado de la funcionalidad de validacion por medio de huella digital
                 */%>
    <body  onload="ValidacionHuellaDactiar();">
        <form>
       <!--  PARA PRUEBAS DE PAGINA INTERMEDIA DESCOMENTAR
       <body>
          <form>
          <p>&nbsp; </p>
          <p><a href="javascript:ValidacionHuellaDactiar();">validarhuella</a> </p>
          <p><a href="javascript:muestraParametros(<%=icveexpediente%>);">Parametros</a> </p>
       -->
          <p>
<%
	try {
		String el = "";
		java.util.Enumeration en = request.getParameterNames();
		while(en.hasMoreElements()) {
			el = (String)en.nextElement();
			if(el.compareTo("Submit")!= 0) {
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
