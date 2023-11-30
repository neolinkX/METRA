<%--  
    Document   : pg070101103
    Created on : 28/12/2011, 04:03:53 PM
    Author     : AG SA
--%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>
 
<html>
<%
  pg070101103CFG  clsConfig = new pg070101103CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070101103.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101103.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101103.jsp";       // modificar
  String cReordenar   = "pg070101104.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Orden|Pregunta|Sexo(M/F/A)|";    // modificar
  String cCveOrdenar  = "iOrden|cPregunta|cGenero|";  // modificar
  String cDscFiltrar  = "Orden|Pregunta|Sexo(M/F/A)|";    // modificar
  String cCveFiltrar  = "iOrden|cPregunta|cGenero|";  // modificar
  String cTipoFiltrar = "7|8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();
 
  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  //String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  //String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  
  
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();

  
    String cCanWrite   = clsConfig.getCanWrite();
  //String cSaveAction = clsConfig.getSaveAction();
  //String cDeleteAction = clsConfig.getDeleteAction();
  

/**
* Este segmento de codigo inicializa los vectores
* para el llenado de los combos
*/
 Vector vMedSrv = new Vector();
 TDMEDServicio dMEDServicio = new TDMEDServicio();
 vMedSrv = dMEDServicio.FindByAll("Where lActivo=1");
 TVMEDServicio vMEDServicio = new TVMEDServicio();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT> 
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">

  <input type="hidden" name="hdTpoResp" value="<% if (request.getParameter("hdTpoResp") != null) out.print(request.getParameter("hdTpoResp"));%>">  
  
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%  
   if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<% if (request.getParameter("hdRowNum") != null) out.print(request.getParameter("hdRowNum"));%>">  
  <input type="hidden" name="hdCampoClave" value="<% if (request.getParameter("hdCampoClave") != null) out.print(request.getParameter("hdCampoClave"));%>">  
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="iCveMdoTrans2" value="">
  <input type="hidden" name="iCveCategoria2" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td>
  <input type="hidden" name="iCveRegla" value="">
  <input type="hidden" name="hdBoton" value="">
  <!--<input type="hidden" name="iCveSintoma" value="">-->
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="EEtiqueta">Servicio:</td>
                              <td class="ETabla">
                               <select name="iCveServicio" size="1" onChange="fillNext();">
                                 <%                                 
                                 if (request.getParameter("iCveServicio")!=null){
                                 %>
                                  <option value=0 Selected>Seleccione...</option>
                                 <%
                                 }else{
                                 %>
                                  <option value=0>Seleccione...</option>
                                 <%
                                 }
                                     for (int i = 0;i<vMedSrv.size(); i++){
                                          vMEDServicio = (TVMEDServicio) vMedSrv.get(i);
                                          if (request.getParameter("iCveServicio")!=null&&request.getParameter("iCveServicio").compareToIgnoreCase(new Integer( vMEDServicio.getICveServicio()).toString())==0){
                                             out.print("<option value =" + vMEDServicio.getICveServicio() + " Selected>" + vMEDServicio.getCDscServicio() + "</option>");
                                          }else{
                                             out.print("<option value =" + vMEDServicio.getICveServicio() + ">" + vMEDServicio.getCDscServicio() + "</option>");
                                          }
                                       }
                                    %>
                                 </select>
                              </td>
                              <td class="EEtiqueta">Rama:</td>
                              <td class="ETabla"><%
                                        TDMEDRama dMEDRama = new TDMEDRama();
                                        TVMEDRama vMEDRama = new TVMEDRama();
                                        Vector vMEDr = new Vector();
                                        String cFiltro = "";
                                        if (request.getParameter("iCveServicio") != null){
                                            cFiltro = " WHERE iCveServicio = " + request.getParameter("iCveServicio");
                                        }else{
                                            cFiltro = " WHERE iCveServicio = " + "0";
                                        }
                                        vMEDr = dMEDRama.FindByAll(cFiltro);
                                     %>
                                    <select name="iCveRama" size="1" onChange="fillBusca();">
                                     <%
                                    out.print("<option value=0>Seleccione...</option>");
                                    if (vMEDr.size() > 0){
                                     for (int x = 0;x<vMEDr.size(); x++){
                                          vMEDRama = (TVMEDRama) vMEDr.get(x);
                                          if (request.getParameter("iCveRama")!=null&&request.getParameter("iCveRama").compareToIgnoreCase(new Integer(vMEDRama.getICveRama()).toString())==0){
                                             out.print("<option value =" + vMEDRama.getICveRama() + " Selected>" + vMEDRama.getCDscRama() + "</option>");
                                          }else{
                                             out.print("<option value =" + vMEDRama.getICveRama() + ">" + vMEDRama.getCDscRama() + "</option>");
                                          }
                                       }
                                    }
                                    %>
                                 </select>
                              </td>
                              <%
                                boolean lReordenar = clsConfig.getLPagina(cReordenar);
                                if(lReordenar){
                                  out.print("<td class=\"ECampo\">");
                                  //out.print(vEti.clsAnclaTexto("EAncla","Reordenar","JavaScript:fReordenar('" + request.getParameter("iCveServicio") + "', '" + request.getParameter("iCveRama") + "');", ""));
                                  out.print("</td>");
                                }
                              %>

                            </tr>
                            <tr>
                              <td class="EEtiqueta">Sintoma:</td>
                              <td class="ETabla"><%
                              
                              String Rama = "";
                              if (request.getParameter("iCveRama") != null)
                                  Rama = request.getParameter("iCveRama");
                              
                                        TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
                                        TVMEDSintoma vMEDSintoma = new TVMEDSintoma();
                                        Vector vMEDr2 = new Vector();
                                        String cFiltro2 = "";
                                        if (request.getParameter("iCveRama") != null){
                                            cFiltro2 = " WHERE a.iCveServicio = " + request.getParameter("iCveServicio")+
                                                       " and a.iCveRama = " + Rama;
                                        }else{
                                            cFiltro2 = " WHERE a.iCveServicio = 0"+
                                                       " and a.iCveRama = 0";
                                        }
                                        vMEDr2 = dMEDSintoma.FindByAll(cFiltro2);
                                     %>
                                    <select name="iCveSintoma" size="1" onChange="fillBusca();">
                                     <%
                                        out.print("<option value=0>Seleccione...</option>");
                                        if (vMEDr2.size() > 0){
                                         for (int x = 0;x<vMEDr2.size(); x++){
                                              vMEDSintoma = (TVMEDSintoma) vMEDr2.get(x);
                                              if (request.getParameter("iCveSintoma")!=null&&request.getParameter("iCveSintoma").compareToIgnoreCase(new Integer(vMEDSintoma.getICveSintoma()).toString())==0){
                                                 out.print("<option value =" + vMEDSintoma.getICveSintoma() + " Selected>" + vMEDSintoma.getCPregunta() + "</option>");
                                              }else{
                                                 out.print("<option value =" + vMEDSintoma.getICveSintoma() + ">" + vMEDSintoma.getCPregunta() + "</option>");
                                              }
                                           }
                                        }
                                    %>
                                 </select>
                              </td>
                            </tr>
                          </table>

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="11" class="ETablaT">Consulta de la Configuración de reglas aplicables al síntoma
                              </td>
                            </tr>
                  <%          
                      if (bs != null){
                  %>
                            <tr>
                              <td class="ETablaT">Regla:</td>
                              <input type="hidden" name="hdnuevo" value="nuevo">
                              
                    <%
                          if(bs.getFieldValue("iCveTpoResp").toString().equalsIgnoreCase("1")){
                    %>
                              <td class="ETablaT">Logica Igual a:</td>
                    <%
                         }else{
                    %>
                              <td class="ETablaT">Mayor a:</td>
                              <td class="ETablaT">Menor a:</td>
                              <td class="ETablaT">Igual a:</td>
                    <%
                         }
                    %>
                              <!--<td class="ETablaT">Dictamen Final:</td>-->
                              <td class="ETablaT">Alerta:</td>
                              <td class="ETablaT">Regla Activa:</td>
                              <td class="ETablaT">Detalle:</td>
                            </tr>
                             <% // modificar según listado
                               
                                  String cGenero ="";
                                   bs.start();
                                   while(bs.nextRow()){
                                       out.println("<tr>");
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iCveRegla", "&nbsp;")));
                                       //out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cMdoTrans", "&nbsp;")));
                                       //out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cCategorias", "&nbsp;")));
                                       %>
                                       <input type="hidden" name="iCveMdoTrans" value="<%=bs.getFieldValue("iCveMdoTrans")%>">
                                       <input type="hidden" name="iCveCategoria" value="<%=bs.getFieldValue("iCveCategoria")%>">
                                       <%
                                       
                                       if(bs.getFieldValue("iCveTpoResp").toString().equalsIgnoreCase("1")){
                                                    if(bs.getFieldValue("Logica", "").toString().equalsIgnoreCase("1")){
                                                                  out.print(vEti.Texto("ETablaC","SI"));
                                                               }else{
                                                                  out.print(vEti.Texto("ETablaC","NO"));
                                                      }
                                       }else{
                                            out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iMayorA", "&nbsp;")));
                                            out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iMenorA", "&nbsp;")));
                                             if(bs.getFieldValue("iCveTpoResp").toString().equalsIgnoreCase("4")){
                                                    String Descripcion  =  dMEDSintoma.Sentencia("  SELECT CDSCREGLA "
                                                                                                 + "FROM MEDREGSIN WHERE 	"
                                                                                                 + "ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND	"
                                                                                                 + "ICVERAMA = "+request.getParameter("iCveRama")+" AND	"
                                                                                                 + "ICVESINTOMA = "+request.getParameter("iCveSintoma")+" AND	"
                                                                                                 + "ICVEREGLA = "+bs.getFieldValue("iCveRegla","&nbsp;").toString()+" AND	"
                                                                                                 + "ICVEMDOTRANS = "+bs.getFieldValue("iCveMdoTrans")+" AND "
                                                                                                 + "ICVECATEGORIA = "+bs.getFieldValue("iCveCategoria")+"");
                                                    out.print(vEti.Texto("ETabla",""+Descripcion));
                                               }else{
                                                    out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iIgualA", "&nbsp;")));
                                               }
                                       }
                                       
                                       /* 
                                       if(bs.getFieldValue("lDictamenF", "").toString().equalsIgnoreCase("1")){
                                          out.print(vEti.Texto("ETablaC","SI"));
                                       }else{
                                          out.print(vEti.Texto("ETablaC","NO"));
                                       }*/
                                      
                                      out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cAlerta", "&nbsp;")));
                                      
                                      if(bs.getFieldValue("lActivo", "").toString().equalsIgnoreCase("1")){
                                          out.print(vEti.Texto("ETablaC","SI"));
                                       }else{
                                          out.print(vEti.Texto("ETablaC","NO"));
                                       }
                                      

                                      out.print("<td class=\"EEtiquetaC\">");
                                      out.print(vEti.clsAnclaTexto("EEtiquetaC","Detalle","JavaScript:fIrCatalogo('"+request.getParameter("iCveServicio")+"','"+
                                                request.getParameter("iCveRama")+"','"+request.getParameter("iCveSintoma")+"','" +bs.getFieldValue("iCveRegla","") + 
                                                "','" +bs.getFieldValue("iCveMdoTrans","") + "','" +bs.getFieldValue("iCveCategoria","") + "');",""));
                                      out.print("</td>");
                                                                              
                                      out.println("</tr>");
                                   }
                               }else{%>
                                        <tr>
					<td colspan="11" class="ETablaC">No existen reglas configuradas para este síntoma.</td>
					<input type="hidden" name="hdnuevo" value="">
					</tr>
                               <%}
                            %>
                          </table>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

