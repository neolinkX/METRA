 <%--
 MostrarArchivos
    Document   : SubirArchivo
    Created on : 6/10/2010, 07:27:02 PM
    Author     : Ivan Santiago Méndez
--%>
<%@ page import="gob.sct.medprev.cntmgr.placas.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.dao.TDEXPServArchCM"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
function on(o){
    o.style.background="#ffabab";
    o.style.cursor="hand";
}
function off(o){
    o.style.background="#999999";
    o.style.cursor="pointer";
}

function mostrarArchivo(idPlaca) {
    var frm = document.forms[0];
    frm.LINTICVEDOCUMEN.value = idPlaca;
    //alert("LINTI "+frm.LINTICVEDOCUMEN.value);
    frm.submit();
}
//-->
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<body onLoad='getValues();'  >
<form action="MostrarArchivoServlet" method="get">
     <p>
       <input type="hidden" name="iCveExpediente" value="0">
       <input type="hidden" name="iNumExamen" value="0">
       <input type="hidden" name="iCveServicio" value="0">
       <input type="hidden" name="iCveRama" value="0">
       <input type="hidden" name="iCveUsuario" value="0">
       <input type="hidden" name="LINTICVEDOCUMEN" value="0">
     </p>
     <table width="700" border="0">
         <%
         String SQL = "SELECT LINTICVEDOCUMEN FROM EXPSERVARCHCM " +
                        " WHERE ICVEEXPEDIENTE = "+request.getParameter("iCveExpediente")+
                        " AND INUMEXAMEN = "+request.getParameter("iNumExamen")+
                        " AND ICVESERVICIO = "+request.getParameter("iCveServicio")+
                        //" AND ICVERAMA = 1 ";
                        " ORDER BY TSGENERADO";
                        //" AND ICVERAMA = "+request.getParameter("iCveRama");
         //System.out.println(SQL);
         DBObject db = new DBObject();
		ArrayList info = db.executeQuery(SQL);
		Iterator it = info.iterator();
                String renglon ="";
                int contadorArchivos=0;
		while (it.hasNext()) {
			String[] values = (String[]) it.next();
                        try {
                        renglon =
                                "<tr> " +
                                //"  <th scope=\"row\">&nbsp;</th>" +
                                "  <td>" +
                                "      <div class=\"style1\" " +
                                "               style=\"background:#999999\" " +
                                "               onMouseOver=\"on(this);\"" +
                                "               onMouseOut=\"off(this);\"" +
                                "		 onClick=\"mostrarArchivo("+values[0]+");\">" +
                                "      <div align=\"center\">ARCHIVO "+values[0]+"</div>" +
                                "      </div>" +
                                "  </td> " +
                                //"  <td>&nbsp;</td>" +
                                "</tr>";
                       }catch(Exception e ){                            
                       }
                  out.print(renglon);
                  contadorArchivos++;
		}
                if(contadorArchivos==0){
                    out.print("<script language=\"JavaScript\" type=\"text/JavaScript\">");
                    out.print("alert('No se encontraron imagenes para este expendiente y numero de examen');");
                    out.print("window.close();");
                    out.print("</script>");
                    }
        
        ///Verifica archivos del dia menores a 0 bytes para eliminarlos        
        //TDEXPServArchCM servArch = new TDEXPServArchCM();
        //servArch.EliminaDocsFallidosNAS(request.getParameter("iCveExpediente"));
        %>
     </table>
     </form>
</body>
</html>
