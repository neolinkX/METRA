<%-- 
    Document   : pg0700009_0
    Created on : Jul 11, 2012, 1:49:49 PM
    Author     : w3r0SCT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="gob.sct.cis.dao.*"%>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.dwr.vo.*"%>
<%@ page import="gob.sct.medprev.dwr.MedPredDwr"%>
<%@ page import="mx.gob.sct.sigtic.encuesta.Encuesta"%>;
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%
    
    TEntorno    vEntorno      = new TEntorno();
    vEntorno.setNumModulo(07);
    TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
    vEntorno.setArchFuncs("FValida");
    vEntorno.setArchTooltips("07_Tooltips");
    vEntorno.clearListaImgs();
    vEntorno.setMetodoForm("POST");
    vEntorno.setActionForm("pg0700009_0.jsp");
    vEntorno.setNombrePagina("pg0700009_0.jsp");
    vEntorno.setOnLoad("fOnLoad();");
    vEntorno.setArchFCatalogo("FFiltro");
    vEntorno.setArchAyuda(vEntorno.getNombrePagina());

    String cRutaImg = vParametros.getPropEspecifica("RutaImg");
    String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
    String cRutaAyuda = vParametros.getPropEspecifica("html");
    ClaseDatosInicio datosUsuario = new ClaseDatosInicio();
    MedPredDwr medPrev = new MedPredDwr();
    DatosAvisoVo datos = new DatosAvisoVo();
    
    datos = medPrev.getDatosAviso();

    TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
    ACMCFG.setACMCFG("07");
    ////Encuesta    
    int encuesta = 0;
    //encuesta = new checkEncuestaOIC().checkEncuesta(vUsuario.getCUsuario());
    encuesta = new Encuesta().isDone3(vUsuario.getCUsuario(),"MEDPREV");
    TDGRLUniMed unimed = new TDGRLUniMed();
    String csqlencuenta="SELECT ICVEUDDADMIVA FROM GRLUNIMED AS U, GRLUMUSUARIO AS S WHERE S.ICVEUNIMED = U.ICVEUNIMED AND S.ICVEUSUARIO = "+String.valueOf(vUsuario.getICveusuario());
    String cUnid = unimed.SelectUniMed(csqlencuenta);
  //String dirEncuesta =  "window.open('http://sigtic.sct.gob.mx/sigtic/encuesta/encc.jsp?usr="+vUsuario.getCUsuario()+"&unid="+cUnid+"&sys=MEDPREV','popup','width=600,height=600')";
    String dirEncuesta = "abrirVentana('http://aplicaciones13.sct.gob.mx/WSEncuestaOIC/encuesta/encc.jsp?usr="+vUsuario.getCUsuario()+"&unid="+cUnid+"&sys=MEDPREV')";
    System.out.println(vUsuario.getCUsuario() +" = encuesta = "+encuesta);
    vUsuario.getCUsuario();
%>
<script type='text/javascript' src='/medprev/dwr/engine.js'></script>
<script type='text/javascript' src='/medprev/dwr/util.js'></script>
<script type='text/javascript' src='/medprev/dwr/interface/MedPredDwr.js'></script>
<script>
    function submitPag(){
        window.location ='./pg0700004.jsp?PagPrin=pg0700009';
    }

    function EncuestaObligatoria(){
        alert("No podr\u00E1 utilizar el sistema Medprev hasta que haya realizado la Encuesta de Satisfacci\u00F3n de Usuarios del Sistema."+
        		"\nUna vez realizada la encuesta inicie sesi\u00F3n en el sistema Medprev.");
        fSalirAviso();
    }
    
    function abrirVentana(url) {
    	window.open(url,'','width=1000,height=600,left=50,top=50,scrollbars=yes');
	}

    function fSalirAviso(){
       form = document.forms[0];
       form.action = 'pg0700000.jsp';
       form.target = '_top';
       form.submit();
    }
    
</script>
<head>
<title>Aviso Importante</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
body {
	background-repeat:no-repeat;
	background-color:#FFFFFF;
	font-family: Arial, Helvetica, sans-serif;
	color: #878686;
	text-align:justify;
	font-size: 14px;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.aviso{
	 font-weight: bold;
	 text-align: center;
	 font-size: 36px;
	 text-align:center;
}
.links{
	text-align:center;
	color: #878686;
	font-weight: bold;
}
	
a {
	font-family: Arial, Helvetica, sans-serif;
}
a:link {
	color: #878686;
}
.tituloarchivo{
	font-size: 10px;
}
</style>
</head>
<body>
<table width="801" border="0" align="center" cellpadding="0" cellspacing="0">

  <tr>
   
  </tr>

  <tr>
   <td height="118" background="<%=cRutaImg%>header.png" class="aviso">&nbsp;</td>
   
  </tr>
  <tr>
    <td height="451" background="<%=cRutaImg%>bckgd.png"><p align="center"><span class="aviso"><%=datos.getAvisoConfigTit()%></span></p>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td>&nbsp;</td>
          <td><%=datos.getAvisoConfigBody()%></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td width="10">&nbsp;</td>
          <td>
          <%if (!datos.getAvisoConfigLiga().equals("")) {%>
          <div class="links">
              <p><a href="<%=datos.getAvisoConfigLiga()%>" target="_blank"><img src="<%=cRutaImg%>file.png" width="18" height="18" border="0" /><%=datos.getAvisoConfigTitLiga()%><br />
            </a></p>
          </div>
          <%}%>
          <form id="form1" name="form1" method="post" action="">
            <div align="center">
            <% if(encuesta == 2){ %>
              	<input type="button"  id="button1" value="Continuar" onclick="<%=dirEncuesta%>,EncuestaObligatoria()"/>
            <%}else{ %>
            	<input type="button"  id="button1" value="Continuar" onclick="submitPag()"/>
            <%} %>
            </div>
          </form>          <p>&nbsp;</p></td>
          <td width="10">&nbsp;</td>
        </tr>
      </table>
      <p>&nbsp;</p>
     <p></p></td>
    
  </tr>
</table>
</body>
</html>
