<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.logging.*" %>
<%@ page import="com.micper.seguridad.dao.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.dwr.vo.*"%>
<%@ page import="gob.sct.medprev.dwr.MedPredDwr"%>
<%

            TLogger.setSistema("07");
            TParametro vParametros = new TParametro("07");
            Enumeration en = request.getParameterNames();
            pg0700002CFG clsConfig = new pg0700002CFG();
            TError vErrores = new TError();
            StringBuffer sbErroresAcum = new StringBuffer();
            TEntorno vEntorno = new TEntorno();
            Vector vBtnSec = new Vector();
            Vector vUrlSec = new Vector();
            Vector vEstSec = new Vector();
            vEntorno.setNumModulo(07);
            vParametros = new TParametro(vEntorno.getNumModuloStr());
            int iNumRowsSec = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

            clsConfig.runConfig(vEntorno, vBtnSec, vUrlSec, vEstSec, vParametros);
            clsConfig.outputHeader(vEntorno, vErrores, pageContext, request, response);

            TDPermisos dPermisos = new TDPermisos();
            Vector vcMenuUsuario;
            HashMap hmPUsuario;
            boolean lAcceso = false;
            boolean UsuPassIgual = false;

            if (request.getMethod().toUpperCase().compareTo("POST") == 0) {

                if (clsConfig.getVUsuario() != null) {
                    dPermisos.menuUsuario(vEntorno.getNumModuloStr(), clsConfig.getVUsuario().getICveusuario());
                    vcMenuUsuario = dPermisos.getVcMenuUsuario();
                    hmPUsuario = dPermisos.getHmPUsuario();

                    if (!vcMenuUsuario.isEmpty()) {
                        request.getSession(true).setAttribute("UsrID", clsConfig.getVUsuario());
                        request.getSession(true).setAttribute("MenuUsuario", vcMenuUsuario);
                        request.getSession(true).setAttribute("PermisosUsuario", hmPUsuario);
                        lAcceso = true;

                        /////////  CONRTOL DE ACCESO PWD  ////////
                        SEGAccPwd dSEGAccPwd = new SEGAccPwd();
                        int expira = 0;
                        try {
                            expira = dSEGAccPwd.Expira(clsConfig.getVUsuario().getICveusuario());
                        } catch (Exception ex) {
                            expira = 0;
                        }
                        //DETECTAR IP
                        //String ipCustom = request.getRemoteAddr();
                        //String hostName = request.getRemoteHost();
                        //System.out.println("ipCustom = "+ipCustom);
                        //System.out.println("hostName = "+hostName);
                        //System.out.println("probando otra onda" + prueba);
      /*  int dirIp = 0;
                        try {
                        dirIp = dSEGAccPwd.insertAcces(null, clsConfig.getVUsuario().getICveusuario(), ipCustom, "");
                        }catch (Exception ex) {
                        expira = 0;
                        }
                         */
                        if (expira == 0) {
                            pageContext.forward("pg0700004.jsp?PagPrin=pg0700012");
                        } else {
                                                        //validar huella
                            /*boolean flag = false;
                            Vector vUsuMedicos = clsConfig.getVUsuario().getVUsuMedicos();
                            for (int i = 0; i < vUsuMedicos.size(); i++) {
                                if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveUniMed() == 107) {
                                    flag = true;
                                }
                            }
*/
                            //if (flag) {
                            	TDPermisos permisos = new TDPermisos();
                            	try {
                            		UsuPassIgual = clsConfig.usupwdigual(vEntorno, vErrores, pageContext, request, response); 
                            		//permisos.checkUserPass(request.getParameter("FILUsuario"), request.getParameter("FILUsuario"));
                        		} catch (Exception ex) {
                        			UsuPassIgual = false;
                        		}
                            	
                            	
                            	if (UsuPassIgual){
                            		pageContext.forward("pg0700004.jsp?PagPrin=pg0700012");		
                            	}else{
                                	pageContext.forward("pg0700004_2.jsp");
                                }
                                //pageContext.forward("pg0700004.jsp?PagPrin=pg0700009");
                            /*} else {
                                MedPredDwr medPrev = new MedPredDwr();
                                DatosAvisoVo datos = new DatosAvisoVo();
                               // datos = medPrev.getDatosAviso();
                                 datos = new DatosAvisoVo();
                                 datos.setMostrarAvisoConfig("true");
                                if (datos.getMostrarAvisoConfig().equals("true")) {
                                    pageContext.forward("pg0700004.jsp?PagPrin=pg0700009");
                                } else {
                                    pageContext.forward("pg0700009_0.jsp");
                                }
                           }*/
                        }


                        //pageContext.forward("pg0700004_2.jsp");
                        //pageContext.forward("pg0700004.jsp?PagPrin=pg0700009");
                    } else {
                        vErrores.acumulaError("El usuario no tiene configurados permisos de acceso.", 0, "");
                    }
                } else {
                    vErrores.acumulaError("El usuario, contraseña o clues no es válido.", 0, "");
                }
                if (lAcceso == false) {
                    request.getSession(true).removeAttribute("UsrID");
                    request.getSession(true).removeAttribute("MenuUsuario");
                    request.getSession(true).removeAttribute("PermisosUsuario");
                    request.getSession(true).removeAttribute("SelPer");
                }
            }

            String cRutaImg = vParametros.getPropEspecifica("RutaImg");
            String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
            String cRutaAyuda = vParametros.getPropEspecifica("html");
            String cPrograma = cRutaAyuda + "hlp" + vEntorno.getArchAyuda() + ".htm";
            String cUsuario;
            if (request.getParameter("FILUsuario") == null) {
                cUsuario = "";
            } else {
                cUsuario = request.getParameter("FILUsuario");
            }
            
            
            
            ////Valida el tiempo del metodo/////
            if(vParametros.getPropEspecifica("Bloquear").equals("false")){
            if(vParametros.getPropEspecifica("NumRowsSec").equals("1")){
            	boolean enejecucion = false;
              if( new ControlEliminaExp().comprobar() )
		        {
		            //System.out.println("Ejecuto aplicación");
		            enejecucion = true;
		        }        
		        else
		        {
		        	//System.out.println("Aplicacion en ejecución");
		        	enejecucion = false;
		        }
            	}
            //System.out.println("produccion");
            }else{
            	//System.out.println("Desarrollo");
            }
            
%>
<!-- Version de desarrollo 2017-10-26 -->

<!DOCTYPE html>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002-2.js"></SCRIPT>
<script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jQuery.js"></script>
<script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery.cross-slide.js"></script>
<script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>DetQueryPI.js"></script>

<script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
<script language="JavaScript">
        var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
        var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
        var resultado;
        var Status = "<%= vParametros.getPropEspecifica("BarraEdo")%>";
        var Programa = '<%= cPrograma%>';
        var titulo = '<%= vEntorno.getTituloVentana()%>';
        var cEntrada = '<%=cUsuario%>';
        var cRutaFuncs = '<%=vParametros.getPropEspecifica("RutaFuncs")%>';
        var Style = '<%=vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS")%>';
        
        function x() {
             var script = document.createElement("script");
                script.type = "text/javascript";
                script.src = "http://ip-api.com/json/?callback=datos";
                document.getElementsByTagName("head")[0].appendChild(script);
        }
        
        
        
        
        if (BrowserDetect.browser == 'Explorer' && parseInt(BrowserDetect.version,10) >= 9) {
        
        } else {
            var wmi = GetObject("winmgmts:{impersonationLevel=impersonate}");
        }
        fPag1();
        
    	/*
        window.onload = function () {
    		 var script = document.createElement("script");
    	        script.type = "text/javascript";
    	        script.src = "http://ip-api.com/json/?callback=datos";
    	        document.getElementsByTagName("head")[0].appendChild(script);
    	};
        */
    	
</script>
<style type="text/css">
    #slideshow {
        width: 800px;
        height: 450px;
    }
</style>

<div id="slideshow" style="background-image:url(<%=vParametros.getPropEspecifica("RutaImgServer")%>foto-2.jpg);"></div>

<script language="JavaScript">
   /* $(function() {
        $('#slideshow').crossSlide({
            sleep: 2,
            fade: 1
        }, [
            { src:  cRutaImgServer+'foto-2.jpg' },
            { src:  cRutaImgServer+'foto-3.jpg' },
            { src:  cRutaImgServer+'foto-4.jpg' },
            { src:  cRutaImgServer+'foto-5.jpg' },
            { src:  cRutaImgServer+'foto-6.jpg' },
            { src:  cRutaImgServer+'foto-7.jpg' },
            { src:  cRutaImgServer+'foto-8.jpg' },
            { src:  cRutaImgServer+'foto-9.jpg' }
        ]);
    });*/
    //document.write(cRutaImgServer+"contenido.swf");
    fPag2();
</script>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>

