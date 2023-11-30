<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>

<html>
<%
  pg070801011CFG  clsConfig = new pg070801011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070801011.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070801011.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción Breve|";    // modificar
  String cCveOrdenar  = "iCveTpoArticulo|cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave|Descripción Breve|";    // modificar
  String cCveFiltrar  = "iCveTpoArticulo|cDscBreve|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
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
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }



</SCRIPT>
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
       cClave2  = ""+bs.getFieldValue("iCveTpoArticulo", "");
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%if (cPosicion.compareTo("")!=0) out.print(cPosicion); else out.print(request.getParameter("hdRowNum"));%>">
  <input type="hidden" name="hdCampoClave" value="<%if (cClave2.compareTo("")!=0) out.print(cClave2); else out.print(request.getParameter("hdCampoClave"));%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                            %>

                             <tr>
                             <td colspan="2" class="ETablaT">Tipos de Artículos</td>
                             </tr>

                             <%
                               if (lNuevo){ // Nuevo Registro

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                    out.println("<td>");
                                    out.print("<input type='text' size='10' disabled>");
                                    out.println("</td>");
                                    out.println("</tr>");

                                    // Descripcion - cDscTpoArticulo
                                    out.print("<tr>"+vEti.EtiCampo("EEtiqueta","Descripción:","ECampo","text",100,100,"cDscTpoArticulo","",0,"","fMayus(this);",false,true,lCaptura)+"</tr>");

                                    // Descripcion Breve - cDscBreve
                                    out.print("<tr>"+vEti.EtiCampo("EEtiqueta","Descripción Breve:","ECampo","text",50,50,"cDscBreve","",0,"","fMayus(this);",false,true,lCaptura)+"</tr>");

                                    // Partida - iIDPartida
                                    out.print("<tr>"+vEti.EtiCampo("EEtiqueta","Partida:","ECampo","text",5,5,"iIDPartida","",0,"","",false,true,lCaptura)+"</tr>");

                                    // Activo - lActivo
                                    out.print("<tr>"+vEti.EtiToggle("EEtiqueta","Situación:","ECampo","lActivo","1","",1,true,"Si","No",lCaptura)+"<tr>");

/* Modificacion */                               }
                                  else{      // Consultar o Modificar Registro
                                 if (bs != null){

                                    // Clave - iCveTpoArticulo
                                    out.print("<tr>"+vEti.EtiCampo("EEtiqueta","Clave:","ECampo","text",5,5,"iCveTpoArticulo", bs.getFieldValue("iCveTpoArticulo","&nbsp;").toString(),0,"","",false,true,false)+"</tr>");

                                    // Descripcion - cDscTpoArticulo
                                    out.print("<tr>"+vEti.EtiCampo("EEtiqueta","Descripción:","ECampo","text",100,100,"cDscTpoArticulo", bs.getFieldValue("cDscTpoArticulo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura)+"</tr>");

                                    // Descripcion Breve - cDscBreve
                                    out.print("<tr>"+vEti.EtiCampo("EEtiqueta","Descripción Breve:","ECampo","text",50,50,"cDscBreve", bs.getFieldValue("cDscBreve","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura)+"</tr>");

                                    // Partida - iIDPartida
                                    if (lCaptura)
                                          out.print("<tr>"+vEti.EtiCampo("EEtiqueta","Partida:","ECampo","text",5,5,"iIDPartida", bs.getFieldValue("iIDPartida","").toString(),0,"","",false,true,lCaptura)+"</tr>");
                                    else
                                          out.print("<tr>"+vEti.EtiCampo("EEtiqueta","Partida:","ECampo","text",5,5,"iIDPartida", bs.getFieldValue("iIDPartida","&nbsp;").toString(),0,"","",false,true,lCaptura)+"</tr>");

                                    // Activo - lActivo
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Situación:"));
                                    String cChecked = "";
                                    if (!lCaptura) {
                                           if (bs.getFieldValue("lActivo").toString().compareToIgnoreCase("1")==0)
                                              out.print(vEti.Texto("ETabla","ACTIVO"));
                                           else
                                              out.print(vEti.Texto("ETabla","INACTIVO"));
                                    } else {
                                            if (bs.getFieldValue("lActivo","0").toString().compareToIgnoreCase("0")!=0)
                                                cChecked = "checked";
                                            out.println("<td>");
                                            out.println("<input type=\"checkbox\" name=\"lActivo\" value=\"1\" " + cChecked + ">");
                                            out.println("</td>");
                                            out.println("</tr>");
                                    }
                                 }
                                 else{
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
                                 }
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
