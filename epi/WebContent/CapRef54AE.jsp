<%-- 
    Document   : CapRef54AE
    Created on : 12/07/2012, 01:11:28 PM
    Author     : AG
--%>

<%-- 
    Document   : CapRef70
    Created on : 14/05/2012, 09:44:41 PM
    Author     : AG SA
--%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<% //Variables

           String x1=request.getParameter("hx1");
           String y1=request.getParameter("hy1");
           String x2=request.getParameter("hx2");
           String y2=request.getParameter("hy2");
           String width=request.getParameter("hwidth");
           String height=request.getParameter("hheight");
           String cDscPref=request.getParameter("hcDscPref");
           String expediente = request.getParameter("icveexpediente");
           String examen = request.getParameter("inumexamen");

           
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setNombrePagina("SEPer01.jsp");
  TEtiCampo vEti = new TEtiCampo();
  TSimpleCampo vSCampo = new TSimpleCampo();
  TDEXPSERVPRef dEXPSERVPRef = new TDEXPSERVPRef();

           String cPreDet = "";
           if((""+request.getParameter("hdPreDet")).compareTo("1")==0){
             cPreDet = "" + request.getParameter("iCveUniMed") + "|" + request.getParameter("iCveModulo") + "|";
             request.getSession(true).setAttribute("SelPer",cPreDet);
           }

           String cOmision1 = "", cOmision2="";
           if(request.getSession(true).getAttribute("SelPer")!=null){
             StringTokenizer stSelPer = new StringTokenizer(""+request.getSession(true).getAttribute("SelPer"),"|");
             cOmision1=stSelPer.nextToken();
             cOmision2=stSelPer.nextToken();
           }

           
           TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
           
           if(request.getParameter("hx1") != null){
                  //System.out.println("Se guardara la informacion y se cerrara automaticamente esta ventana");                  
                                String cCampo;
                                int iCampo;
                                float fCampo;
                                java.sql.Date dtCampo;
                                TFechas tfCampo = new TFechas();
                                TVEXPSERVPRef vEXPSERVPRef = new TVEXPSERVPRef();


                  try {
                                cCampo = "" + request.getParameter("icveexpediente");
                                if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                                    iCampo = Integer.parseInt(cCampo, 10);
                                }
                                else {
                                    iCampo = 0;
                                }
                                vEXPSERVPRef.setiCveExpediente(iCampo);
                                
                                cCampo = "" + request.getParameter("inumexamen");
                                if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                                    iCampo = Integer.parseInt(cCampo, 10);
                                }
                                else {
                                    iCampo = 0;
                                }
                                vEXPSERVPRef.setiNumExamen(iCampo);
                                
                                cCampo = "" + request.getParameter("hx1");
                                if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                                    iCampo = Integer.parseInt(cCampo, 10);
                                }
                                else {
                                    iCampo = 0;
                                }
                                vEXPSERVPRef.setx1(iCampo); 
                               
                                cCampo = "" + request.getParameter("hy1");
                                if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                                    iCampo = Integer.parseInt(cCampo, 10);
                                }
                                else {
                                    iCampo = 0;
                                }
                                vEXPSERVPRef.sety1(iCampo);
                                                               
                                cCampo = "" + request.getParameter("hx2");
                                if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                                    iCampo = Integer.parseInt(cCampo, 10);
                                }
                                else {
                                    iCampo = 0;
                                }
                                vEXPSERVPRef.setx2(iCampo);
                                                               
                                cCampo = "" + request.getParameter("hy2");
                                if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                                    iCampo = Integer.parseInt(cCampo, 10);
                                }
                                else {
                                    iCampo = 0;
                                }
                                vEXPSERVPRef.sety2(iCampo);
                                                               
                                cCampo = "" + request.getParameter("hwidth");
                                if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                                    iCampo = Integer.parseInt(cCampo, 10);
                                }
                                else {
                                    iCampo = 0;
                                }
                                vEXPSERVPRef.setwidth(iCampo);
                                                               
                                cCampo = "" + request.getParameter("hheight");
                                if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                                    iCampo = Integer.parseInt(cCampo, 10);
                                }
                                else {
                                    iCampo = 0;
                                }
                                vEXPSERVPRef.setheight(iCampo);
                                                               
                                cCampo = "" + request.getParameter("hcDscPref");
                                if (cCampo.compareTo("null") == 0) {
                                             cCampo = "";
                                }
                                vEXPSERVPRef.setcDescripcion(cCampo);
                                                               
                                cCampo = "" + request.getParameter("servicio");
                                if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                                    iCampo = Integer.parseInt(cCampo, 10); 
                                }
                                else {
                                    iCampo = 0;   
                                }
                                vEXPSERVPRef.setiCveServicio(iCampo);
                                
                                cCampo = "" + request.getParameter("rama");
                                if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                                    iCampo = Integer.parseInt(cCampo, 10);
                                }
                                else {
                                    iCampo = 0;
                                }
                                vEXPSERVPRef.setiCveRama(iCampo);
                                
                                iCampo = 2;
                                vEXPSERVPRef.setiCveDocumento(iCampo);
                                
                }catch (Exception e) {
                             e.printStackTrace();
                }
                  
                  String guardado ="";
                                try {
                                        guardado = (String) dEXPSERVPRef.insert(null, vEXPSERVPRef);
                                   }
                                catch (Exception e) {
                                        e.printStackTrace();
                                }               
               %>
                    <html>  
                    <head>

                    <script> 

                    function fCerrar(){
                            form = document.forms[0];
                            cVMsg = '';
                <%if(guardado.equals("")){ %>
                            alert("Se produjeron errores en el guardado del punto de referencia.\n");
               <%}else{ %>
                           alert("El punto de referencia de aputación de extremidades ha sido guardado exitosamente\n");
                   <%}%>
                            window.close();
                    }    


                    </script>
                    <SCRIPT LANGUAGE="JavaScript">
                        window.resizeTo(150,100);
                    </SCRIPT>                            
                    </head>
                        <body onLoad="fCerrar();">
                    </body>
                    </html>                   
               
<%}else{
%>               
          
            <html>
            <head>



            <script> 

            function Validar(form){
                    form = document.forms[0];
                    cVMsg = '';
                    if(form.x1.value == "-")
                            cVMsg = cVMsg + "\n - Trace un punto de referenca en la imagen. \n";

                    if(cVMsg != ''){
                            alert("Datos no Válidos: \n" + cVMsg);
                            return false;
                    }
                    form.hx1.value = form.x1.value;
                    form.hy1.value = form.y1.value;
                    form.hx2.value = form.x2.value;
                    form.hy2.value = form.y2.value;
                    form.hwidth.value = form.w.value;
                    form.hheight.value = form.h.value;
                    form.hcDscPref.value = form.cDscPref.value;
                    form.icveexpediente.value = form.icveexpediente.value;
                    form.inumexamen.value = form.inumexamen.value;
                    form.servicio.value = form.servicio.value;
                    form.rama.value = form.rama.value;
                    form.submit();
            }    


            function fCerrar(){
                    form = document.forms[0];
                    cVMsg = '';
                    alert("El punto de referencia de amputacion de extremidades ha sido guardado\n");
                    window.close();
            }    


            </script>



            <title>Exploraci&oacute;n fisica</title>



            <link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">


            <link rel="stylesheet" type="text/css"
                    href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/main.css" />

                <link rel="stylesheet" type="text/css" href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/imgareaselect-animated.css" />

                <script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>prettify.js"></script>

                <style type="text/css">
                <style type="text/css">
            <!--
            .Estilo2 {
                    color: #FBFBEF;
                    font-weight: bold;
            }
            body {
                    background-color: #D5FFD7;
            }
            .Estilo3 {color: #088A08}
            .Estilo4 {
                    color: #FFFFFF;
                    font-weight: bold;
            }
            -->
                </style>
            </head>

            <body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">


            <div id="content" class="container">
                    <script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery-1.6.1.min.js"></script>
            <script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery.imgareaselect.pack.js"></script>
            <script type="text/javascript">
            function preview(img, selection) {
            form = document.forms[0];
                if (!selection.width || !selection.height)
                    return;

                var scaleX = 100 / selection.width;
                var scaleY = 100 / selection.height;

                $('#preview img').css({
                    //width: Math.round(scaleX * 300),
                    //height: Math.round(scaleY * 300),
                            width: Math.round(scaleX * 232),
                    height: Math.round(scaleY * 447),
                    marginLeft: -Math.round(scaleX * selection.x1),
                    marginTop: -Math.round(scaleY * selection.y1)
                });

                $('#x1').val(selection.x1);
                $('#y1').val(selection.y1);
                $('#x2').val(selection.x2);
                $('#y2').val(selection.y2);
                $('#w').val(selection.width);
                $('#h').val(selection.height); 
            }

            $(function () {
                $('#photo').imgAreaSelect({ aspectRatio: '1:1', handles: true,
                    fadeSpeed: 200, onSelectChange: preview });
            });
            </script>
            <table width="638" border="1" align="center">
            <tr bgcolor="#088A08">
                <td width="572"><div align="center"><span class="Estilo4">AMPUTACI&Oacute;N DE EXTREMIDADES</span></div></td>
            </tr>
            <tr bgcolor="#FBFBEF">
                <td><div align="justify" class="Estilo3">AMPUTACI&Oacute;N DE EXTREMIDADES: EN EL DIBUJO SE DETERMINARÁ EL LUGAR EN DÓNDE SE ENCUENTRA UBICADA LA ALTERACI&Oacute;N O ANORMALIDAD ENCONTRADA, UNA VEZ QUE SEA SELECCIONADA EN AUTOM&Aacute;TICO EL SISTEMA DETERMINAR&Aacute; LAS COORDENADAS ANAT&Oacute;MICAS.</div></td>
            </tr>
            </table>
            <br>
            <div class="container demo">
            <div style="float: left; width: 67%;">
                <p class="Estilo1 Estilo3" style="font-size: 110%; font-weight: bold; padding-left: 0.1em;">
                Selecciona un area    </p>


                <div class="frame" style="margin: 0 0.3em; width: 232px; height: 447px;">
                <img src="<%=vParametros.getPropEspecifica("RutaImgServer")%>AmpExt.jpg" width="232" height="447" id="photo" />    </div>
            </div>

            <div style="float: left; width: 32%;">
                <p class="Estilo1 Estilo3" style="font-size: 110%; font-weight: bold; padding-left: 0.1em;">
                Selecci&oacute;n previa    </p>

                <div class="frame" 
                style="margin: 0 1em; width: 100px; height: 100px;">
                <div id="preview" style="width: 100px; height: 100px; overflow: hidden;">
                    <img src="<%=vParametros.getPropEspecifica("RutaImgServer")%>AmpExt.jpg" style="width: 100px; height: 100px;" />
                </div>
                </div>

                <table style="margin-top: 1em;">
                <form name="formulario" method="post" action="CapRef54AE.jsp">
                <thead>
                    <tr>
                    <th colspan="2" style="font-size: 110%; font-weight: bold; text-align: left; padding-left: 0.1em;">
                        <span class="Estilo1 Estilo3">Coordenadas</span>          </th>
                    </tr>
                </thead>
                <tbody>
                    <input type="hidden" name="hx1" value="">
                    <input type="hidden" name="hy1" value="">
                    <input type="hidden" name="hx2" value="">
                    <input type="hidden" name="hy2" value="">
                    <input type="hidden" name="hwidth" value="">
                    <input type="hidden" name="hheight" value="">
                    <input type="hidden" name="hcDscPref" value="">
                    <input type="hidden" name="icveexpediente" value="<%=request.getParameter("icveexpediente")%>">
                    <input type="hidden" name="inumexamen" value="<%=request.getParameter("inumexamen")%>">
                    <input type="hidden" name="servicio" value="<%=request.getParameter("servicio")%>">
                    <input type="hidden" name="rama" value="<%=request.getParameter("rama")%>">
                    
                    <tr>
                    <td style="width: 10%;"><span class="Estilo1 Estilo3"><b>X<sub>1</sub>:</b></span></td>
                                <td style="width: 30%;"><input type="text" id="x1" value="-" /></td>
                            </tr>
                    <tr>
                    <td><span class="Estilo1 Estilo3"><b>Y<sub>1</sub>:</b></span></td>
                    <td><input type="text" id="y1" value="-" /></td>
                    </tr>
                    <tr>
                    <td><span class="Estilo1 Estilo3"><b>X<sub>2</sub>:</b></span></td>
                    <td><input type="text" id="x2" value="-" /></td>
                    </tr>
                    <tr>
                    <td><span class="Estilo1 Estilo3"><b>Y<sub>2</sub>:</b></span></td>
                    <td><input type="text" id="y2" value="-" /></td>
                    </tr>
                    <tr>
                    <td><span class="Estilo3"></span></td>
                    <td><span class="Estilo3"></span></td>
                    </tr>
                    <tr>
                    <th colspan="2" style="font-size: 110%; font-weight: bold; text-align: left; padding-left: 0.1em;">
                        <span class="Estilo1 Estilo3">Dimensiones</span></th>
                    </tr>

                    <tr>
                    <td style="width: 10%;"><span class="Estilo1 Estilo3"><b>Width:</sub></b></span></td>
                                <td style="width: 30%;"><input type="text" id="w" value="-" /></td>
                            </tr>
                    <tr>
                    <td><span class="Estilo1 Estilo3"><b>Height:</sub></b></span></td>
                    <td><input type="text" id="h" value="-" /></td>
                    </tr>

                    <tr>
                    <td><span class="Estilo3"></span></td>
                    <td><span class="Estilo3"></span></td>
                    </tr>
                    <tr>
                    <td colspan="2"><span class="Estilo3"></span> <textarea name="cDscPref" id="cDscPref" cols="45" rows="10"></textarea></td>
                    </tr>
                </tbody>
                </form>
                </table>

                <p>
                <input name="Envio" type="button" onClick="Validar(this.form)" value="Enviar">
                </p>
            </div>
            </div>

            <p class="major">&nbsp;</p>

            <h2>&nbsp;</h2>

            </div> <!-- /#content -->


            </body>
            </html>


<%               
}
%>