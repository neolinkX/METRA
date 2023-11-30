<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg071007021CFG  clsConfig = new pg071007021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071007021.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071007021.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave Usuario|";    // modificar
  String cCveOrdenar  = "iCveUsuario|";  // modificar
  String cDscFiltrar  = "Clave Usuario|";    // modificar
  String cCveFiltrar  = "iCveUsuario|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Si";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  TFechas Fecha = new TFechas();
  TVGRLUsuario vGRLUsuario = new TVGRLUsuario();
  TDGRLUsuario dGRLUsuario = new TDGRLUsuario();
  Vector vcGRLUsuario = new Vector();

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
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave  = ""+bs.getFieldValue("iCveUsuario", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="<%out.print(request.getParameter("hdCampoClave1"));%>">
  <input type="hidden" name="hdCampoClave2" value="<%out.print(request.getParameter("hdCampoClave2"));%>">
  <input type="hidden" name="hdCampoClave3" value="<%out.print(request.getParameter("hdCampoClave3"));%>">
  <input type="hidden" name="hdCampoClave4" value="<%out.print(request.getParameter("hdCampoClave4"));%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                              <% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <td colspan="5" class="ETablaT">Usuario del Sistema MedPrev</td>
              <%
                               if (bs != null){
                                 bs.start();
                                 while(bs.nextRow()){
                                 TVSEGUsuario vUsuario = new TVSEGUsuario();
                                 vUsuario = (TVSEGUsuario) (bs.getFieldValue("vUsuario"));
                               out.print("<tr>");
                               out.println(vEti.Texto("EEtiqueta","Nombre:"));
                               out.println(vEti.TextoCS("ETabla",vUsuario.getCNombre(),3));
                               out.println("<input type='hidden' name='Nuevo' value=" + (vUsuario.getICveUsuario() == 0? "SI":"NO") +">");
                               out.print("</tr><tr>");
                               out.println(vEti.Texto("EEtiqueta","Registrado:"));
                               out.println(vEti.Texto("ETabla", Fecha.getFechaDDMMYYYY(vUsuario.getDtRegistro(),"/")));
                               out.println(vEti.Texto("EEtiqueta","Usuario:"));
                               out.println(vEti.Texto("ETabla",vUsuario.getCUsuario()));
                               out.print("</tr><tr>");
                               out.println(vEti.Texto("EEtiqueta","Dirección:"));
                               out.println(vEti.TextoCS("ETabla",vUsuario.getCCalle(),3));
                               out.print("</tr><tr>");
                               out.println(vEti.TextoCS("ETablaST","Información Adicional",4));
                               out.print("</tr><tr>");
                               out.println(vEti.EtiCampoCS("EEtiqueta","R.F.C.:","ETabla","text",20,15,3,"cRFC",bs.getFieldValue("cRFC","").toString(),0,"fMayus(this);","fMayus(this)",true,true,true,request));
                               out.print("</tr><tr>");
                               out.println(vEti.EtiCampoCS("EEtiqueta","C&eacute;dula:","ETabla","text",22,20,3,"cCedula",bs.getFieldValue("cCedula","").toString(),0,"fMayus(this);","fMayus(this)",true,true,true,request));
                               out.print("</tr><tr>");
                               out.print("</tr><tr>");
                               out.println(vEti.EtiCampoCS("EEtiqueta","Siglas:","ETabla","text",22,20,3,"cSiglasProf",bs.getFieldValue("cSiglasProf","").toString(),0,"fMayus(this);","fMayus(this)",true,true,true,request));
                               out.print("</tr><tr>");
                                     out.print("<td class='EEtiqueta' >Profesión:</td>");
                                     out.print("<td class='ETabla' colspan='3'>");
                                     out.println("<select name=\"iCveProfesion\">");
                                       TDGRLProfesion dGRLProfesion = new TDGRLProfesion();
                                       TVGRLProfesion vGRLProfesion = new TVGRLProfesion();
                                       Vector vcGRLProfesion = new Vector();
                                       vcGRLProfesion = dGRLProfesion.FindByAll("", " order by cPrefesion ");
                                       if (vcGRLProfesion.size() > 0){
                                         out.print("<option value = 0>Seleccione...</option>");
                                         for (int i = 0; i < vcGRLProfesion.size(); i++){
                                           vGRLProfesion = (TVGRLProfesion)vcGRLProfesion.get(i);
                                           if (bs.getFieldValue("iCveProfesion", "-1").toString().compareToIgnoreCase(new Integer(vGRLProfesion.getICveProfesion()).toString())==0)
                                             out.print("<option value = " + vGRLProfesion.getICveProfesion() + " selected>" + vGRLProfesion.getcPrefesion() + "</option>");
                                           else if (request.getParameter("iCveProfesion")!=null && request.getParameter("iCveProfesion").compareToIgnoreCase(new Integer(vGRLProfesion.getICveProfesion()).toString())==0)
                                             out.print("<option value = " + vGRLProfesion.getICveProfesion() + " selected>" + vGRLProfesion.getcPrefesion() + "</option>");
                                           else
                                             out.print("<option value = " + vGRLProfesion.getICveProfesion() + ">" + vGRLProfesion.getcPrefesion() + "</option>");
                                         }
                                       }
                                       else{
                                         out.print("<option value = 0>Datos no disponibles</option>");
                                       }
                                       out.print("</select></td>");
                                     out.print("</tr>");
                                 }
                               }else{

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
