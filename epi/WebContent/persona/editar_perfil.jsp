<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.micper.seguridad.dao.TDPermisos"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%

TDPermisos perm = new TDPermisos();
TVUsuario vUsuario = (TVUsuario) request.getSession(true)
.getAttribute("UsrID");

            String cUsuario = request.getParameter("usuario");
            String cPassword = request.getParameter("password");
                        
            TDPermisos dPermisosExp = new TDPermisos();
            try{
            	if(perm.cambioContrasenia(vUsuario.getCUsuario(), cPassword, "07", vUsuario.getICveusuario())){
            		out.println("[{\"resp\":\"correcto\"},{\"dispositivo\":\"pc\"},{\"via\":\"correcta\"}]");
                }else {
                	out.println("[{\"resp\":\"errores\"}]");
                }	
            }catch(Exception e){
            	e.printStackTrace();
            }
                
%>