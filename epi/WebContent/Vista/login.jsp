<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
 

<!DOCTYPE html>
<%  
TEntorno    vEntorno      = new TEntorno();
vEntorno.setNumModulo(07);
TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
%>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>
M E D P R E V - Ingreso</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="SCT"
            name="description" />
        <meta content="" name="author" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/ladda/ladda-themeless.min.css" rel="stylesheet" type="text/css" />
	 <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/sctreg/components-md.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/sctreg/plugins-md.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/sctreg/login-5.min.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="favicon.ico" /> </head>

    <body class=" login">
        <div class="user-login-5">
            <div class="row bs-reset">
                <div class="col-md-6 bs-reset mt-login-5-bsfix">
                    <div class="login-bg" style="background-image:url(<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/sctreg/bg1.jpg)">
                        <img class="login-logo"  src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/sctreg/sct_corner.png" /> </div>
                </div>
                <div class="col-md-6 login-container bs-reset mt-login-5-bsfix">
                    <div class="login-content">
                        <div style="text-align:center"><img  src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/sctreg/sct_back.png"></div>
                        <br>
                        <form action="javascript:;" class="login-form" id="login">
                            <div class="alert alert-danger display-hide">
                                <button class="close" data-close="alert"></button>
                                <span>Debe escribir sus credenciales. </span>
                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <input class="form-control form-control-solid placeholder-no-fix form-group" type="text" autocomplete="off" placeholder="Usuario" name="usuario" id="usuario" onkeypress="valida_logeo(event,'noDec','2');" required/> </div>
                                <div class="col-xs-6">
                                    <input class="form-control form-control-solid placeholder-no-fix form-group" type="password" autocomplete="off" placeholder="Contraseña" name="password" id="password"  onkeypress="valida_logeo(event,'noDec','2');" required/> </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="rem-password">
										<div class="md-checkbox">
											<input type="checkbox" id="checkbox1" class="md-check">
											<label for="checkbox1">
												<span></span>
												<span class="check"></span>
												<span class="box"></span> Recordarme </label>
										</div>

                                    </div>
                                </div>
                                <div class="col-sm-8 text-right">
                                    <button onclick="valida_logeo(event,'noDec','1');" class="btn green">Enviar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="login-footer">
                        <div class="row bs-reset">
                            <div class="col-xs-5 bs-reset">
                                <ul class="login-social">
                                    <li>
                                        <a href="javascript:;">
                                            <i class="icon-social-facebook"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <i class="icon-social-twitter"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <i class="icon-social-dribbble"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-xs-7 bs-reset">
                                <div class="login-copyright text-right">
                                    <p>Copyright &copy;</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END : LOGIN PAGE 5-1 -->
        <!--[if lt IE 9]>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/respond.min.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/excanvas.min.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/ie8.fix.min.js"></script>
<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/sctreg/app.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/sctreg/login-5.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/ladda/spin.min.js" type="text/javascript"></script>
        <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/ladda/ladda.min.js" type="text/javascript"></script>
		<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/sctreg/ui-buttons.min.js" type="text/javascript"></script>
		<script>
			var url_app = '<%=vParametros.getPropEspecifica("URL_APP")%>';
		</script>
		<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/generales.js"></script>
		<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/common.js"></script>
    </body>

</html>
