<%-- 
    Document   : MosRef54AMI
    Created on : 12/07/2012, 01:23:03 PM
    Author     : AG
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

                        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                        <html xmlns="http://www.w3.org/1999/xhtml">
<%
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setNombrePagina("SEPer01.jsp");
  TEtiCampo vEti = new TEtiCampo();
  TSimpleCampo vSCampo = new TSimpleCampo();
  TDEXPSERVPRef dEXPSERVPRef = new TDEXPSERVPRef();
  TVEXPSERVPRef vEXPSERVPRef;
  
  String expediente = request.getParameter("icveexpediente");
  String examen = request.getParameter("inumexamen");
  String servicio = request.getParameter("servicio");
  String rama = request.getParameter("rama");  
  

if(request.getParameter("iorden") != null){
  
  Vector resultados = new Vector();
  
  String condicion = " ICVEEXPEDIENTE = "+expediente+" AND INUMEXAMEN = "+examen+" AND ICVEDOCUMENTO = 3 AND ICVESERVICIO = "+servicio+" AND  ICVERAMA = "+rama+" and iorden = "+request.getParameter("iorden")+"";
  try {
          resultados = dEXPSERVPRef.FindByAll(condicion);
   }
  catch (Exception e) {
        e.printStackTrace();
        resultados = new Vector();
  }               
  
   if(resultados.size() > 0){
          for(int i=0;i<resultados.size();i++){
                        vEXPSERVPRef = (TVEXPSERVPRef) resultados.get(i);
                        int iorden = vEXPSERVPRef.getiOrden();
                        int x1 = vEXPSERVPRef.getx1();
                        int y1 = vEXPSERVPRef.gety1();
                        int x2 = vEXPSERVPRef.getx2();
                        int y2 = vEXPSERVPRef.gety2();
                        int w = vEXPSERVPRef.getwidth();
                        int h = vEXPSERVPRef.getheight();
                        String Descripcion = vEXPSERVPRef.getcDescripcion();
                        
                        

%>                   

                        <head>

                            <title>AMPUTACI&Oacute;N EN MANO IZQUIERDA</title>

                            <meta name="keywords" content="imgareaselect,javascript,jquery,plugin,image,area,select,crop,cropper,examples,Michal Wojciechowski,odyniec" />

                            <link rel="shortcut icon" href="/favicon.ico" />
                            <link rel="stylesheet" type="text/css" href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/prettify.css" />


                            <link rel="stylesheet" type="text/css"
                                href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/main.css" />



                            <link rel="stylesheet" type="text/css"
                                href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/main.css" />



                            <link rel="stylesheet" type="text/css" href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/index.css" />


                            <link rel="stylesheet" type="text/css" href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/imgareaselect-default.css" />



                            <link rel="stylesheet" type="text/css" href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/examples.css" />


                            <script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>prettify.js"></script>

                        <style type="text/css">
                                    <!--
                                    body {
                                            background-color: #D5FFD7;
                                    }
                        .Estilo5 {color: #FBFBEF}
                        .Estilo6 {
                                color: #FBFBEF;
                                font-size: 18px;
                                font-weight: bold;
                        }
                                    -->
                                    </style>
                        </head>

                        <body onload="prettyPrint();">
            <form name="formulario" method="post" action="MosRef54AMI.jsp">
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
                    <input type="hidden" name="iorden" value="<%=request.getParameter("iorden")%>">

                    </form>
                                    <div id="content" class="container">
                                <script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery-1.6.1.min.js"></script>
                        <script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery.imgareaselect.pack.js"></script>
                        <script type="text/javascript">
                        $(function () {
                            $('#abdomen').imgAreaSelect({ x1: <%=x1%>, y1: <%=y1%>, x2:<%=x2%>, y2: <%=y2%> });
                        });
                        </script>

                        <div class="container"></li>
                        <p>&nbsp;</p>
                        <table width="400" border="1">  
                        <tr>
                            <td bgcolor="#088A08"><span class="Estilo6">Punto<%=iorden%> de referencia de AMPUTACI&Oacute;N EN MANO IZQUIERDA</span><br></td>
                        </tr>
                        <tr>
                            <td bgcolor="#FBFBEF">&nbsp;<span class="Estilo5">
                        <img src="<%=vParametros.getPropEspecifica("RutaImgServer")%>ManoI.jpg" alt="abdomen" name="abdomen" id="abdomen" 
                        title="Why did the duck cross the road?" /></p>
                        </td>
                        </tr>

                        </table>
                        </div> <!-- /#content -->

                        <table width="400" border="1">     
                        <tr>
                            <td bgcolor="#088A08"><span class="Estilo6"><%=Descripcion%></span><br></td>
                        </tr>

                        </table>
                        
                        <a href="javascript:history.go(-1)">CONSULTAR OTRO PUNTO</a>
                        </body>

                        <script type="text/javascript">
                        var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
                        /*document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));*/
                        document.write(unescape("%3Cscript src='<%=vParametros.getPropEspecifica("RutaFuncs")%>ga.js' type='text/javascript'%3E%3C/script%3E"));
                        </script>
                        <script type="text/javascript">
                        var pageTracker = _gat._getTracker("UA-3683332-2");
                        pageTracker._initData();
                        pageTracker._trackPageview();
                        </script>  

                        </html>
<%         
      }
   }else{
      //System.out.println("No existen puntos");
      %>

                <head>

                    <title>AMPUTACI&Oacute;N EN MANO IZQUIERDA</title>

                    <meta name="keywords" content="imgareaselect,javascript,jquery,plugin,image,area,select,crop,cropper,examples,Michal Wojciechowski,odyniec" />

                    <link rel="shortcut icon" href="/favicon.ico" />
                    <link rel="stylesheet" type="text/css" href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/prettify.css" />
                    <link rel="stylesheet" type="text/css"
                        href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/main.css" />
                    <link rel="stylesheet" type="text/css"
                        href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/main.css" />
                    <link rel="stylesheet" type="text/css" href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/index.css" />
                    <link rel="stylesheet" type="text/css" href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/imgareaselect-default.css" />
                    <link rel="stylesheet" type="text/css" href="<%= vParametros.getPropEspecifica("RutaCSS")%>PR/examples.css" />
                    <script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>prettify.js"></script>

                <style type="text/css">
                            <!--
                            body {
                                    background-color: #D5FFD7;
                            }
                .Estilo5 {color: #FBFBEF}
                .Estilo6 {
                        color: #FBFBEF;
                        font-size: 18px;
                        font-weight: bold;
                }
                            -->
                            </style>
                </head>

                <body onload="prettyPrint();">
                <table width="400" border="1">  
                <tr>
                    <td bgcolor="#088A08"><span class="Estilo6">No existen puntos de referencia de AMPUTACI&Oacute;N EN MANO IZQUIERDA</span><br></td>
                </tr>

                </table>
                </body>
                </html>
<%
   }


}else{
      //System.out.println(request.getParameter("iorden"));      
      Vector resultados = new Vector();

        String condicion = " ICVEEXPEDIENTE = "+expediente+" AND INUMEXAMEN = "+examen+" AND ICVEDOCUMENTO = 3 AND ICVESERVICIO = "+servicio+" AND  ICVERAMA = "+rama+"" ;
        try {
                resultados = dEXPSERVPRef.FindByAll(condicion);
        }
        catch (Exception e) {
                e.printStackTrace();
                resultados = new Vector();
        }          
        
   if(resultados.size() > 0){
  
%>

            <head>


            <script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery-1.6.1.min.js"></script>
            <SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"MosRef70I.js"%>"></SCRIPT>
           <script> 
            function fCerrar(){
                    form = document.forms[0];
                    cVMsg = '';
                    alert("El punto de referencia ha sido guardado\n");
                    window.close();
            }    


            </script>
            <script type='text/javascript'>
			// <![CDATA[
			$(document).ready(function() {
			$('a.nudge').hover(function() { //mouse in
			$(this).animate({ paddingLeft: '45px' }, 400);
			}, function() { //mouse out
			$(this).animate({ paddingLeft: '1px' }, 400);
			});
			});
			//]]>
			</script>



            <title>Exploraci&oacute;n fisica</title>




            <link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS")%>07_estilos.css" TYPE="text/css">

            <style type="text/css">
                        <!--
                        body {
                                background-color: #D5FFD7;
                        }
            .Estilo5 {color: #FBFBEF}
            .Estilo6 {
                    color: #FBFBEF;
                    font-size: 18px;
                    font-weight: bold;
            }
                        -->
                        </style>

                        </head>

            <body bgcolor="" topmargin="0" leftmargin="0" >
                <form name="formulario" method="post" action="MosRef54AMI.jsp">
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
                    <input type="hidden" name="iorden" value="<%=request.getParameter("iorden")%>">
                    
                    <br><br>
                    <table width="400" border="1">
                    <tr>
                        <td bgcolor="#088A08"><span class="Estilo6">Consulta de puntos de referencia de AMPUTACI&Oacute;N EN MANO IZQUIERDA</span><br></td>
                    </tr>
                    <%
                    for(int i=0;i<resultados.size();i++){
                        vEXPSERVPRef = (TVEXPSERVPRef) resultados.get(i);
                        int iorden = vEXPSERVPRef.getiOrden();
                    %>  
                    <tr>
                        <td bgcolor="#FBFBEF">&nbsp;<span class="Estilo5">
                                <a class="nudge" href="javascript:fMosRef70I(<%=iorden%>);"><img border="0" src="<%=vParametros.getPropEspecifica("RutaImgServer")%>1.gif"/>&nbsp;Punto-<%=iorden%></a><br>
                        </span></td>
                    </tr>
                    <%}%>
                    </table>
            </body>
</html>

<%
   }          
}
%>