<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.seguridad.dao.TDPermisos"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.logging.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="gob.sct.medprev.dwr.vo.*"%>
<%@ page import="gob.sct.medprev.dwr.MedPredDwr"%>


<%

            String cUsuario = request.getParameter("usuario");
            //System.out.println("INPUT ES  " + request.getParameter("usuario"));
             String cPassword = request.getParameter("password");
            //System.out.println("INPUT ES  " + request.getParameter("password"));
                        
            TDPermisos dPermisosExp = new TDPermisos();
            try{
                if(dPermisosExp.checkUserPass(cUsuario, cPassword))
                {
                out.println("[{\"resp\":\"acceso_correcto\"},{\"dispositivo\":\"pc\"},{\"via\":\"correcta\"}]");
                
                }
                else {
              	//out.println("[{\"resp\":\"acceso_incorrecto\"},{\"dispositivo\":\"pc\"},{\"via\":\"correcta\"}]");
                out.println("[{\"resp\":\"acceso_incorrecto\"}]");
                
                }	
            }catch(Exception e){
            	e.printStackTrace();
            }
                
//System.out.println("llegA--?");




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
            clsConfig.outputHeader2(vEntorno, vErrores, pageContext, request, response, cUsuario, cPassword);

            TDPermisos dPermisos = new TDPermisos();
            Vector vcMenuUsuario;
            HashMap hmPUsuario;
            boolean lAcceso = false;
            boolean UsuPassIgual = false;

            if (clsConfig.getVUsuario() != null) {
                dPermisos.menuUsuario(vEntorno.getNumModuloStr(), clsConfig.getVUsuario().getICveusuario());
                vcMenuUsuario = dPermisos.getVcMenuUsuario();
                hmPUsuario = dPermisos.getHmPUsuario();

                if (!vcMenuUsuario.isEmpty()) {
                    request.getSession(true).setAttribute("UsrID", clsConfig.getVUsuario());
                    request.getSession(true).setAttribute("MenuUsuario", vcMenuUsuario);
                    request.getSession(true).setAttribute("PermisosUsuario", hmPUsuario);
                    lAcceso = true;
                }
             }



%>