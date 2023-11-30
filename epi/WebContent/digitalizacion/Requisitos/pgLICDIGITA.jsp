<%@ page import="java.util.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.logging.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="com.micper.seguridad.dao.*" %>
<%@ page import="gob.sct.elic.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%
/**
 * <p>Title: pgLICSOLICITUD.jsp</p>
 * <p>Description: JSP "Catálogo" de la entidad LICSOLICITUD</p>
 * <p>Copyright: Copyright (c) 2005 </p>
 * <p>Company: Tecnología InRed S.A. de C.V. </p>
 * @author JESR
 * @version 1.0
 */
  TLogger.setSistema("13");
  TParametro  vParametros = new TParametro("13");
  TVDinRep vDinRep;
  TDLICSOLICITUD dLICSOLICITUD = new TDLICSOLICITUD();
  String cError = "";
  CFGAccion oAccion = new CFGAccion(pageContext.getRequest());
  /** Verifica si existe una o más sesiones */
  if(!oAccion.unaSesion(vParametros,(CFGSesiones)application.getAttribute("Sesiones"),(TVUsuario)request.getSession(true).getAttribute("UsrID")))
    out.print(oAccion.getErrorSesion(vParametros.getPropEspecifica("RutaFuncs")));
  else{
  /** Verifica si la Acción a través de hdBotón es igual a "Guardar" */
  if(oAccion.getCAccion().equals("Guardar")){
    vDinRep = oAccion.setInputs("ICVEPERSONAL,TSSOLICITUD,ICVEUSUARIO");
    try{
      vDinRep = dLICSOLICITUD.insert(vDinRep,null);
    }catch(Exception e){
      cError="Guardar";
    }
    oAccion.setBeanPK(vDinRep.getPK());
  }
 /** Se realiza la actualización de Datos a través de actualizar el vector con el Query */
  String cSQL = "Select ICVEPERSONAL,CRFC,CHOMOCLAVE,CCURP,CNOMBRE,CAPPATERNO,CAPMATERNO,CSEXO,DTNACIMIENTO " +
                "from PERDATOS "+
                oAccion.getCFiltro() +
                oAccion.getCOrden();

  Vector vcListado = dLICSOLICITUD.findByCustom("ICVESOLICITUD",cSQL);
  oAccion.navega(vcListado);
  String cNavStatus = oAccion.getCNavStatus();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>CD/ineng.js"></SCRIPT>
<script language="JavaScript">
<%
   out.print(oAccion.getArrayCD());
%>
  fEngResultado('<%=request.getParameter("cNombreFRM")%>',
                '<%=request.getParameter("cId")%>',
                '<%=cError%>',
                '<%=cNavStatus%>',
                '<%=oAccion.getIRowPag()%>',
                '<%=oAccion.getBPK()%>');
</script>
<%}%>
