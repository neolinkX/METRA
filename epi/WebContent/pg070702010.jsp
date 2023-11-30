<%/**
 * Title:        Listado de Vehículos
 * Description:  Listado de Vehículos
 * Copyright:    2004
 * Company:      Micros Personales S.A. de C.V.
 * @author       Marco Antonio Hernández García
 * @version      1.0
 * Clase:        pg070702010.jsp
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>

<html>
<%
  pg070702010CFG  clsConfig = new pg070702010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070702010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070702010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg0702061.jsp";       // modificar
  String cOperador    = "1";                   // modificar

  String cDscOrdenar  = "Vehiculo|Núm.Serie|Placa|";    // modificar
  String cCveOrdenar  = "VEHVehiculo.iCveVehiculo|VEHVehiculo.cNumSerie|VEHVehiculo.cPlacas|";  // modificar
  String cDscFiltrar  = "Vehiculo|Núm.Serie|Placa|";    // modificar
  String cCveFiltrar  = "VEHVehiculo.iCveVehiculo|VEHVehiculo.cNumSerie|VEHVehiculo.cPlacas|";  // modificar

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
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070702010.js"%>"></SCRIPT>
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
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
    <tr>
      <td valign="top">
          <input type="hidden" name="hdBoton" value="">
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="4">Vehículos</td>
            </tr>
            <tr>
              <%
              out.println("<td class=\"EEtiqueta\">Tipo de Vehículo:</td>");
              out.println("<td>");
              TDVEHTpoVehiculo dVEHTpoVehiculo = new TDVEHTpoVehiculo();
              TVVEHTpoVehiculo vVEHTpoVehiculo = new TVVEHTpoVehiculo();
              Vector vTpoVehiculo = new Vector();
                 vTpoVehiculo = dVEHTpoVehiculo.FindByAll();
              if (vTpoVehiculo.size()>0)
                 out.print(vEti.SelectOneRowSinTD("iCveTpoVehiculo","",vTpoVehiculo,"iCveTpoVehiculo","cDscBreve",request,"0",true));
              else{
                 out.println("<SELECT NAME=\"iCveTpoVehiculo\" SIZE=\"1\">");
                 out.println("<option value=\"-1\">Datos no disponibles</option>");
                 out.println("</SELECT>");
              }
              out.println("</td>");


              out.println("<td class=\"EEtiqueta\">Marca:</td>");
              out.println("<td>");
              TDVEHMarca dVEHMarca = new TDVEHMarca();
              TVVEHMarca vVEHMarca = new TVVEHMarca();
              Vector vMarca = new Vector();
                 vMarca = dVEHMarca.FindByAll();
              if (vMarca.size()>0)
                 out.print(vEti.SelectOneRowSinTD("iCveMarca","",vMarca,"iCveMarca","cDscBreve",request,"0",true));
              else{
                 out.println("<SELECT NAME=\"iCveMarca\" SIZE=\"1\">");
                 out.println("<option value=\"-1\">Datos no disponibles</option>");
                 out.println("</SELECT>");
              }
              out.println("</td>");


              %>
            </tr>
          </table>
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="12">Listado de Vehículos</td>
            </tr>
            <tr>
              <td class="ETablaT">Núm.</td>
              <td class="ETablaT">Placa</td>
              <td class="ETablaT">Tipo</td>
              <td class="ETablaT">Marca</td>
              <td class="ETablaT">Modelo</td>
              <td class="ETablaT">Núm. Serie</td>
              <td class="ETablaT">Núm. Motor</td>
              <td class="ETablaT" colspan="5">Inventario</td>
            </tr>
            <%
               boolean pg070702011 = clsConfig.getLPagina("pg070702011.jsp");
               boolean pg070702012  = clsConfig.getLPagina("pg070702012.jsp");
               boolean pg070702013  = clsConfig.getLPagina("pg070702013.jsp");
               boolean pg070702020  = clsConfig.getLPagina("pg070702020.jsp");

               if (bs != null){
                   bs.start();
                   while(bs.nextRow()){
                        out.println("<tr>");
                        out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveVehiculo", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cPlacas", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscTpoVehiculo", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscMarca", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscModelo", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNumSerie", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNumMotor", "&nbsp;")));
                        out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cInventario", "&nbsp;")));
                        if (pg070702011){
                           out.print("<td class=\"ECampo\">");
                           out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('"+bs.getFieldValue("iCveVehiculo","")+"','pg070702011.jsp');","Ir a detalle..."));
                           out.print("</td>");
                        }
                        if (pg070702012){
                           out.print("<td class=\"ECampo\">");
                           out.print(vEti.clsAnclaTexto("EAncla","Ubicaciones","JavaScript:fIrCatalogo('"+bs.getFieldValue("iCveVehiculo","")+"','pg070702012.jsp');","Ir a ubicación..."));
                           out.print("</td>");
                        }
                        if (pg070702013){
                           out.print("<td class=\"ECampo\">");
                           out.print(vEti.clsAnclaTexto("EAncla","Mantenimientos","JavaScript:fIrCatalogo('"+bs.getFieldValue("iCveVehiculo","")+"','pg070702013.jsp');","Ir a mantenimiento..."));
                           out.print("</td>");
                        }
                        if (pg070702020){
                           out.print("<td class=\"ECampo\">");
                           out.print(vEti.clsAnclaTexto("EAncla","Ubicar","JavaScript:fIrCatalogo('"+bs.getFieldValue("iCveVehiculo","")+"','pg070702020.jsp');","Ir a asignaciones..."));
                           out.print("</td>");
                        }
                   }
               }
            %>
          </table>
      </td>
    </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>





