<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
    
<%  
TEntorno    vEntorno      = new TEntorno();
vEntorno.setNumModulo(07);
TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());


System.out.println("Session \n"+session.getAttribute("Sicveusuario"));
System.out.println("Session \n"+session.getAttribute("Scusuario"));
System.out.println("Session \n"+session.getAttribute("SNombre"));


%>    
    
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>M E D P R E V</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="Framedev"
            name="description" />
        <meta content="" name="author" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaCssExp")%>login/components-md.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaCssExp")%>login/plugins-md.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaCssExp")%>login/lock-2.min.css" rel="stylesheet" type="text/css" />
        <!--  <link rel="icon" href="http://10.33.143.52:7001/epi/fw7/img/favicon.ico" />-->
        <link rel="icon" href="<%=vParametros.getPropEspecifica("URL_APP")%>img/favicon.ico" />
        
    <body class="">
        <div class="page-lock">
            <div class="page-logo">
                <a class="brand" href="javascript:;">
                    <img height="30px" src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>medprev_lck.png" class="logo-default"> </a>
            </div>
            <div class="page-body">

                                      <img class="page-lock-img" src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>lock4.png" width="250px" alt="Candado">
                


                <div class="page-lock-info">
                    <h1>TECNOLOGIA APLICADA</h1>
                    <span class="email">ta@gmail.com</span>
                    <span class="locked"> Sesión asegurada </span>
                    <form class="form-inline">
                        <div class="input-group input-medium">
                            <input type="hidden" name="usuario" id="usuario" value="tecnoaplicada">

							              <input class="form-control" type="password" placeholder="Contraseña" name="password" id="password" required="" autocomplete="off">

                            <span class="input-group-btn">
                                <a class="btn btn-primary loginfn" rel="nofollow">
                                    <i class="fa fa-sign-in" aria-hidden="true"></i>
                                </a>
                            </span>
                        </div>
                        <div class="relogin">
                            <a href="../">¿ No eres  TECNOLOGIA APLICADA ?</a>
                        </div>
                        <input type="hidden" name="usuario" id="usuario" value="tecnoaplicada"/>
                    </form>
                </div>
            </div>
            <div class="page-footer-custom"> 2017 &copy; M E D P R E V </div>
        </div>
        <!--[if lt IE 9]>
		<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/respond.min.js"></script>
		<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/excanvas.min.js"></script>
		<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/ie8.fix.min.js"></script>
		<![endif]-->
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/pages/app.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/pages/lock-2.js" type="text/javascript"></script>

		<script>var url_app = '<%=vParametros.getPropEspecifica("URL_APP")%>';</script>

		<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/generales.js"></script>
		<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/common.js"></script>
    </body>

</html>

