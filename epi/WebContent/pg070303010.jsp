<%/**
 * Title: pg070303010.jsp
 * Description: Recepcion de Envio
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version 1.0
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<html>
<%
  pg070303010CFG  clsConfig = new pg070303010CFG();                 // modificar
  String cCatalogo  = "pg070303011.jsp";
  String cPagOrden  = "pg070303012.jsp";
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070303010.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070303010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";
  String cCveEnvio  = "";
  String cClaveA    = "";
  String cClaveB   = "";
  String cClaveC   = "";

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Envio|";    // modificar
  String cCveOrdenar  = "iCveEnvio|";  // modificar
  String cDscFiltrar  = "Envio|";    // modificar
  String cCveFiltrar  = "iCveEnvio|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  TCreaBoton bBtn = new TCreaBoton();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cUpdStatus  = "";
  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String iFechaRecep = "";
  java.sql.Date tdFechas = null;
  int    iCveEnvio = 0;
  if (bs != null){
     if(bs.getFieldValue("cObsRecep","").toString().length() != 0){
        cUpdStatus  = "Hidden";
     }else{
        cUpdStatus  = "SaveCancelOnly"; //clsConfig.getUpdStatus();
     }
       cClaveA = ""+bs.getFieldValue("iAnio", "");
       cClaveB = ""+bs.getFieldValue("iCveUniMed", "");
       cClaveC = ""+bs.getFieldValue("iCveEnvio", "");
  }else{
     cUpdStatus  = "Hidden";
  }
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070306011.js)

  function fValidaTodo(){
    var form = document.forms[0];
       if (form.bCCveEnvio.value.length > 0 ){
         if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarA"){
             if (confirm("¿Desea realizar la recepción del envío seleccionado?")){
                return true;
             }else{
                return false;
             }
        }else{
           return true;
        }
       }else {
         alert("Favor de ingresar el número de envío.");
         return false;
      }

   }

  function fAccion(cAccion){
    form = document.forms[0];
    form.target="_self";
    form.hdBoton.value = cAccion;
    form.submit();
  }

function fIrMuestras(cAnio, cUnidad, cEnvio, cAccion){
    form = document.forms[0];
    form.hdCampoClave.value = cAnio;
    form.hdCPropiedad.value = cUnidad;
    form.hdCCveEnvio.value  = cEnvio;
    form.hdRowNum.value = cAnio;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
//    form.action = 'pg070303011.jsp';
    form.action = cAccion;
    form.submit();
  }


  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">

  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave2  = ""+bs.getFieldValue("iCveSistema", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
       cCveEnvio = ""+bs.getFieldValue("iCveEnvio","");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="hdCCveEnvio" value="<%=cCveEnvio%>">
  <input type="hidden" name="hdCampoClaveA" value="<%if (request.getParameter("hdCampoClaveA")!=null) out.print(request.getParameter("hdCampoClaveA")); else out.print(cClaveA);%>">
  <input type="hidden" name="hdCampoClaveB" value="<%if (request.getParameter("hdCampoClaveB")!=null) out.print(request.getParameter("hdCampoClaveB")); else out.print(cClaveB);%>">
  <input type="hidden" name="hdCampoClaveC" value="<%if (request.getParameter("hdCampoClaveC")!=null) out.print(request.getParameter("hdCampoClaveC")); else out.print(cClaveC);%>">
  <input type="hidden" name="iCveMuestra" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Búsqueda del Envío
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Año:</td>
                              <td class="ETabla">
                                 <select name="iAnio" size="1">
                                    <%
                                       TEtiCampo vEti = new TEtiCampo();
                                       for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                          if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                             out.print("<option value = " + i + " selected>" + i + "</option>");
                                          else
                                             out.print("<option value = " + i + ">" + i + "</option>");
                                       }
                                    %>
                                 </select>
                              </td>
                              <td class="EEtiqueta">Unidad Médica:</td>
                              <td class="ETabla">
                                  <%  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                      //System.out.println("UserId ==>> "+vUsuario.getICveusuario());
                                  %>
                                     <input type="hidden" name="hdUserId" value="<%=vUsuario.getICveusuario()%>">
                                  <%
                                     TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                     vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                     out.print(vEti.SelectOneRowSinTD("iCveUniMed","", vUsuario.getVUniMed(),"iClave","cDescripcion",request,"0"));
                                 %>
                              </td>
                              <%
                              out.print(vEti.EtiCampo("EEtiqueta","Envío:","ECampo","text",10,10,"bCCveEnvio",cCveEnvio,0,"","",false,true,true));
                              %>
                              <td>
                              <%
                               out.println(bBtn.clsCreaBoton(vEntorno, 1, "BtnBuscar","bBuscar", vEntorno.getTipoImg(),"Buscar Envios","BtnBuscar", vParametros));
                              %>
                              </td>
                            </tr>
                          </table>
                         &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="4" class="ETablaT">Envío de la unidad Médica
                              </td>
                            <tr></tr>
                              <td colspan="4" class="ETablaST">Información del Envío
                              </td>
                            </tr>

                          <%
                          if (bs != null){
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){
                                   %><input type="hidden" name="iCveEnvio" value=""><%
                                }
                                else {
                                    iCveEnvio =Integer.parseInt(bs.getFieldValue("iCveEnvio", "").toString());
                                    %><input type="hidden" name="iCveEnvio" value="<%=iCveEnvio%>"><%
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Número:","ECampo","text",5,5,"iCveEnvio",bs.getFieldValue("iCveEnvio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",50,50,"cdtEnvio",bs.getFieldValue("cdtEnvio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Encargado:","ECampo\" colspan=\"3","text",50,50,"cNombre",bs.getFieldValue("cNombre","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Forma de Envío:","ECampo\" colspan=\"3","text",50,50,"cDscTipoEnvio",bs.getFieldValue("cDscTipoEnvio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Muestras Enviadas:","ECampo\" colspan=\"3","text",100,50,"iTotalEnviadas",bs.getFieldValue("iTotalEnviadas","0").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Observaciones:","ECampo\" colspan=\"3","text",100,50,"cObsEnvio",bs.getFieldValue("cObsEnvio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr><td colspan=\"4\" class=\"ETablaST\">Información de la Recepción</td></tr>");
                                    if(!lCaptura){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo\" colspan=\"3","text",100,50,"dtRecepcion",bs.getFieldValue("dtRecepcion","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Encargado:","ECampo\" colspan=3\"","text",50,50,"cNombre",bs.getFieldValue("cNombreRec","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    }
                                    boolean lRecibe = false;
                                    if(bs.getFieldValue("cObsRecep") == null || bs.getFieldValue("cObsRecep","").toString().length() == 0){
                                       out.print(vEti.EtiAreaTexto("EEtiqueta","Observaciones:","ECampo\" colspan=\"3",100,5,"cObsRecep",bs.getFieldValue("cObsRecep","").toString(),0,"","",true,true,true));
                                    }else{
                                       out.print(vEti.EtiCampo("EEtiqueta","Observaciones:","ECampo\" colspan=\"3","text",50,50,"cObsRecep",bs.getFieldValue("cObsRecep","&nbsp;").toString() + "&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                       lRecibe = true;
                                    }
                                    out.println("</tr>");
                                    %>
                                    </table><% // Fin de Datos %>
                                    &nbsp;
                                    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                    <%
                                    if (clsConfig.getLPagina(cCatalogo)){
                                        out.println("<tr>");
                                        if(lRecibe){
                                          if(clsConfig.getLPagina(cPagOrden)){
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Orden de Recepci&oacute;n","JavaScript:fIrMuestras('" + bs.getFieldValue("iAnio","") + "','" + bs.getFieldValue("iCveUniMed","") + "','" + bs.getFieldValue("iCveEnvio","") + "'" + ", 'pg070303012.jsp');","Registrar el orden de las muestras"));
                                           out.print("</td>");
                                          }
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Muestras","JavaScript:fIrMuestras('" + bs.getFieldValue("iAnio","") + "','" + bs.getFieldValue("iCveUniMed","") + "','" + bs.getFieldValue("iCveEnvio","") + "'" + ", 'pg070303011.jsp');","Ver muestras del envío"));
                                           out.print("</td>");
                                        }
                                        else
                                           out.println(vEti.Texto("EResalta", "Para registrar las muestras en necesario recibir el envío."));


                                        out.println("</tr>");

                                    }
                                    %>
                                    </table>
                                    <%
                                }
                          }else{
                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                out.println("</tr>");
                                out.println("</table>");
                          }
                          %>

  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

