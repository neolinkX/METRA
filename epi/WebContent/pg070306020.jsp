<%/**
 * Title: Listado de Consulta de Calibradores
 * Description: Listado de Consulta de Calibradores
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Juan Manuel Vazquez
 * @version 1
 * Clase: pg070306020CFG
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<%
  pg070306020CFG  clsConfig = new pg070306020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070306020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070306021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripci�n Breve|Lote|Tipo Calibrador|Fecha de Preparaci�n|";    // modificar
  String cCveOrdenar  = "C.iCveCtrolCalibra|C.cDscBreve|C.cLote|C.iCveEmpleoCalib|C.dtPreparacion|";  // modificar
  String cDscFiltrar  = "Clave|Descripci�n Breve|Lote|Fecha de Preparaci�n|Fecha de Autorizaci�n|";    // modificar
  String cCveFiltrar  = "iCveCtrolCalibra|cDscBreve|cLote|dtPreparacion|dtAutoriza|";  // modificar
  String cTipoFiltrar = "7|8|8|5|5|";                // modificar
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
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cClave3   = "";
  String cPosicion = "";

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  function fIrCatalogo(cCampoClave, cCampoClave3){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCampoClave3.value = cCampoClave3;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070306021.jsp';
    form.submit();
  }

</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
       cClave   = ""+bs.getFieldValue("iCveLaboratorio", "");
       cClave3  = ""+bs.getFieldValue("iCveCtrolCalibra", "");
     }

 %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave3" value="<%=cClave3%>">
  <input type="hidden" name="hdCPropiedad" value="">



  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">

                         <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                          <tr>
                           <td class="ETablaT" colspan="4">Filtrar</td>
                            </tr>
                            <tr>
                              <td class="ETabla">A�o:</td>
                              <td class="ETabla">
                                 <select name="iAnio" size="1">
                                    <%
                                       //TEtiCampo vEti = new TEtiCampo();
                                       for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                          if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                             out.print("<option value = " + i + " selected>" + i + "</option>");
                                          else
                                             out.print("<option value = " + i + ">" + i + "</option>");
                                       }
                                    %>
                                 </select>
                              </td>
                              <td class="ETabla">Laboratorio:</td>
                              <td class="ETabla">
                                  <%  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                      int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                      out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                                      // llenaSLT(1,document.forms[0].iAnio.value,this.value,'',document.forms[0].iCveLoteCualita);
                                  %>
                              </td>
                            </tr>
                          </table>
                          <br>


                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <%
                                //TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                            %>
                            <tr>
                              <td class="ETablaT" colspan="9">Calibradores</td>
                            </tr>
                            <%
                               if (bs != null){
                                  out.println("<tr>");
                                  out.println("<td class=\"ETablaT\">Clave</td>");
                                  out.println("<td class=\"ETablaT\">Lote</td>");
                                  out.println("<td class=\"ETablaT\">Descripci�n Breve</td>");
                                  out.println("<td class=\"ETablaT\">Tipo</td>");
                                  out.println("<td class=\"ETablaT\">Preparaci�n</td>");
                                  out.println("<td class=\"ETablaT\">Autorizaci�n</td>");
                                  out.println("<td class=\"ETablaT\">Situaci�n</td>");
                                  out.println("<td class=\"ETablaT\" colspan=\"2\">Volumen Total</td>");
                                  out.println(" </tr>");
                                   bs.start();
                                   int iCve = 0;
                                   while(bs.nextRow()){
                                       out.println("<tr>");

                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveCtrolCalibra", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cLote", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscBreve", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscEmpleoCalib", "&nbsp;")));


                                     /*
                                       if (bs.getFieldValue("iCveEmpleoCalib").toString().compareToIgnoreCase("1")==0)
                                          out.print(vEti.Texto("ETabla","Control"));
                                       else
                                          out.print(vEti.Texto("ETabla","Calibrador"));
                                       */

                                       out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("cDtPreparacion", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("cDtAutoriza", "&nbsp;")));


                                       if (bs.getFieldValue("lBaja", "&nbsp;").toString().compareTo("1") == 0 )
                                          out.print(vEti.Texto("ETablaC","BAJA"));
                                       else{
                                           if (bs.getFieldValue("lAgotado", "&nbsp;").toString().compareTo("1") == 0 )
                                               out.print(vEti.Texto("ETablaC","AGOTADO"));
                                           else
                                               out.print(vEti.Texto("ETablaC","ACTIVO"));
                                       }


                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("dVolumen", "&nbsp;")));

                                       if (clsConfig.getLPagina(cCatalogo)){
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" + bs.getFieldValue("iCveLaboratorio","") + "'," +"'"+ bs.getFieldValue("iCveCtrolCalibra","") + "'" + ");","Ir a..."));
                                           out.print("</td>");
                                       }
                                   }
                               }else{
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 5,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
                               }
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