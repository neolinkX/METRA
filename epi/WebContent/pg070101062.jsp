<%/** 
 * Title: Orden de Servicio por Rama
 * Description: Orden de Servicio por Rama
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Leonel Popoca G.
 * @version 1.0
 * Clase: pg070101062CFG
 */%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070101062CFG  clsConfig = new pg070101062CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101062.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101062.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cClave1    = "";
  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());


  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No Disponible|";    // modificar
  String cCveOrdenar  = "No Disponible|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "No Disponible|";  // modificar
  String cTipoFiltrar = "8|";                // modificar

  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar



  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
//  String cUpdStatus  = clsConfig.getUpdStatus();
//  String cUpdStatus  = "SaveCancelOnly";
  String cUpdStatus  = "SaveOnly";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)

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
       cClave1  = ""+bs.getFieldValue("iCveServicio", "");
       cClave2 = ""+bs.getFieldValue("iCveRama", "");
     }

  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave1%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">

              <%
              out.print("<table border='1' class='ETablaInfo' align='center' cellspacing='0' cellpadding='3'>");
              out.print("<tr>");
                out.print("<td class='EEtiqueta'>Servicio:</td>");
                out.print("<td>");
                out.print("<select name='iCveServicio' onChange=\"llenaSLT(1,this.value,'','',document.forms[0].iCveRama);\">");
                TDMEDServicio dMEDServicio = new TDMEDServicio();
                TVMEDServicio vMEDServicio = new TVMEDServicio();
                Vector vcMEDServicio = new Vector();
                vcMEDServicio = dMEDServicio.FindByAll(" where lActivo = 1 ", " order by iCveServicio ");
                if (vcMEDServicio.size() > 0){
                  if(request.getParameter("iCveServicio")==null || request.getParameter("iCveServicio").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  else if(request.getParameter("iCveServicio")!=null && Integer.parseInt(request.getParameter("iCveServicio"))<1)
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcMEDServicio.size(); i++){
                    vMEDServicio = (TVMEDServicio)vcMEDServicio.get(i);
                    if (request.getParameter("iCveServicio")!=null && request.getParameter("iCveServicio").compareToIgnoreCase(new Integer(vMEDServicio.getICveServicio()).toString())==0)
                      out.print("<option value = " + vMEDServicio.getICveServicio() + " selected>" + vMEDServicio.getCDscServicio() + "</option>");
                    else
                      out.print("<option value = " + vMEDServicio.getICveServicio() + ">" + vMEDServicio.getCDscServicio() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
                out.print("</select>");
                out.print("</td>");

                out.print("<td class='EEtiqueta'>Rama:</td>");
                out.print("<td>");
                out.println("<select name='iCveRama' onChange='fillBusca();'>");
                if(request.getParameter("iCveServicio")!= null){
                  TDMEDRama dMEDRama = new TDMEDRama();
                  TVMEDRama vMEDRama = new TVMEDRama();
                  Vector vcMEDRama = new Vector();
                  vcMEDRama = dMEDRama.FindByAll(" where iCveServicio = " + request.getParameter("iCveServicio") + " and lActivo = 1 ");
                  if (vcMEDRama.size() > 0){
                    for (int i = 0; i < vcMEDRama.size(); i++){
                      vMEDRama = (TVMEDRama)vcMEDRama.get(i);
                      if (request.getParameter("iCveRama")!=null && request.getParameter("iCveRama").compareToIgnoreCase(new Integer(vMEDRama.getICveRama()).toString())==0)
                        out.print("<option value = " + vMEDRama.getICveRama() + " selected>" + vMEDRama.getCDscRama() + "</option>");
                      else
                        out.print("<option value = " + vMEDRama.getICveRama() + ">" + vMEDRama.getCDscRama() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else if((request.getParameter("iCveServicio")!=null && Integer.parseInt(request.getParameter("iCveServicio").toString())<1) || request.getParameter("iCveServicio")==null)
                  out.println("<option value='0' selected>Datos no disponibles</option>");
                out.print("</select></td>");
                out.print("</tr>");
              out.print("</table>");
              %>



                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr>
                              <% TEtiCampo vEti = new TEtiCampo();
                              %>
                              <td colspan="4" class="ETablaT">Orden de Pregunta/Síntoma/Resultado por Servicio y Rama</td>
                            </tr>
                            <%
                                 if (bs != null){
                                     TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
                                     Vector vMEDSintoma = new Vector();
                                     vMEDSintoma = dMEDSintoma.FindByAll(" where a.iCveServicio = " + request.getParameter("iCveServicio") + " and a.iCveRama = " + request.getParameter("iCveRama"));

                                     out.println("<tr>");
                                     out.print(vEti.Texto("ETablaT", "Orden Anterior"));
                                     out.print(vEti.Texto("ETablaT", "Nuevo Orden"));
                                     out.print(vEti.Texto("ETablaT", "Descripción"));
                                     out.println("</tr>");
                                     String combo = "";
                                     String valor = "";
                                     int i = 1;
                                     int num_preguntas = 0;
                                     num_preguntas = bs.rowSize() + 1;
                                     String selected = "";
                                     bs.prevTo(1);
                                     int iConsecutivo = 0;
                                     while(bs.nextRow()) {
                                         out.println("<tr>");
                                         out.print(vEti.Texto("ETablaR", bs.getFieldValue("iOrden").toString()));
                                         combo = "<SELECT NAME=\"iOrden" + i + "\" SIZE=\"1\">";
                                         for(int j = 1; j <= num_preguntas; j++) {
                                             if (bs.getFieldValue("iOrden").toString().equals("" + j))
                                                 selected = " selected";
                                             else
                                                 selected = "";
                                             combo += "<option  value=\"" + j + "\"" + selected + ">" + j + "</option>";
                                         }
                                         combo += "</SELECT>";
                                         out.print("<td>" + combo);
                                         out.print("</td>");
                                         out.print(vEti.Texto("ECampo", bs.getFieldValue("cPregunta").toString()));
                                         out.println("</tr>");
                                         out.println("<input type=\"hidden\" name=\"iCvePregunta" + i + "\" value=\"" + bs.getFieldValue("iCveSintoma") + "\">");
                                         i++;
                                     }

                                     out.println("<input type=\"hidden\" name=\"num_preguntas\" value=\"" + i + "\">");
                                 } else {
                                   TDMEDServicio dMedServ = new TDMEDServicio();
                                   TVMEDServicio tvMedServ = new TVMEDServicio();
                                   Vector vMedServ = new Vector();
                                   vMedServ = dMedServ.FindByAll(" where lActivo=1 ", "");

                                   if (request.getParameter("iCveServicio") == null || request.getParameter("iCveServicio").equals("")  || request.getParameter("iCveServicio").equals("0")) {
                                       tvMedServ.setCDscServicio("Seleccione...");
                                       tvMedServ.setICveServicio(0);
                                       vMedServ.add(tvMedServ);
                                   }

                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
                                 }
                            %>
                          </table><% // Fin de Datos %>
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
