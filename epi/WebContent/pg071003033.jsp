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
<%@ page import="java.util.*"%>

<html>
<%
  pg071003033CFG  clsConfig = new pg071003033CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071003033.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071003033.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Descripción U.M.|Descripción Módulo|";    // modificar
  String cCveOrdenar  = "cDscUniMed|cDscModulo|";  // modificar
  String cDscFiltrar  = "Descripción U.M.|Descripción Módulo|";    // modificar
  String cCveFiltrar  = "cDscUniMed|cDscModulo|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
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

  TDVEHEmpMantto dVEHEmpMantto = new TDVEHEmpMantto();
  TVVEHEmpMantto vVEHEmpMantto = new TVVEHEmpMantto();
  Vector vcVEHEmpMantto;

  TVGRLAreaModulo vGRLAreaModulo = new TVGRLAreaModulo();
  TDGRLAreaModulo dGRLAreaModulo = new TDGRLAreaModulo();
  Vector vcGRLAreaModulo = new Vector();

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
       cClave  = ""+bs.getFieldValue("iCveArea", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type='hidden' name='hdEmpMantto' value=''>

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
                              <td colspan="6" class="ETablaT">Asignación de Areas
                              </td>

              <%

                out.print("<tr>");
                out.print("<td class='EEtiqueta' colspan='3'>Área:</td>");
                out.print("<td class='ETabla' colspan='3'>");
                out.println("<select name=\"iCveArea\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                  TDGRLArea dGRLArea = new TDGRLArea();
                  TVGRLArea vGRLArea = new TVGRLArea();
                  Vector vcGRLArea = new Vector();
                  vcGRLArea = dGRLArea.FindByAll("", " order by cDscArea ");
                  if (vcGRLArea.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLArea.size(); i++){
                      vGRLArea = (TVGRLArea)vcGRLArea.get(i);
                      if (request.getParameter("iCveArea")!=null && request.getParameter("iCveArea").compareToIgnoreCase(new Integer(vGRLArea.getICveArea()).toString())==0)
                        out.print("<option value = " + vGRLArea.getICveArea() + " selected>" + vGRLArea.getCDscArea() + "</option>");
                      else
                        out.print("<option value = " + vGRLArea.getICveArea() + ">" + vGRLArea.getCDscArea() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                out.print("</select></td>");
                out.print("</tr>");

                               out.print("<tr>");
                                 out.print("<td class='ETablaT'>Clave</td>");
                                 out.print("<td class='ETablaT'>Unidad Médica</td>");
                                 out.print("<td class='ETablaT'>Clave</td>");
                                 out.print("<td class='ETablaT'>Módulo</td>");
                                 out.print("<td class='ETablaT'>Responsable</td>");
                                 out.print("<td class=\"ETablaT\">Asignar<input type=\"checkbox\" name=\"chKGeneral\" OnClick=\"ChangeAllCheck(document, this.checked , 'chk')\"></td>");
                              out.print("</tr>");
                                 if (bs != null){
                                   bs.start();
                                   int iObjeto = 1;
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                       out.print("<td class='ETablaR'>" + bs.getFieldValue("iCveUniMed") + "</td>");
                                       out.print("<td class='ETabla'>" + bs.getFieldValue("cDscUniMed") + "</td>");
                                       out.print("<td class='ETablaR'>" + bs.getFieldValue("iCveModulo") + "</td>");
                                       out.print("<td class='ETabla'>" + bs.getFieldValue("cDscModulo") + "</td>");

                                       if(request.getParameter("iCveArea")!=null && request.getParameter("iCveArea").compareTo("null")!=0)
                                         vcGRLAreaModulo = dGRLAreaModulo.FindByAll(" where iCveUniMed = " + bs.getFieldValue("iCveUniMed") + " and iCveModulo = " + bs.getFieldValue("iCveModulo") + " and iCveArea = " + request.getParameter("iCveArea"));
                                       else
                                         vcGRLAreaModulo = dGRLAreaModulo.FindByAll(" where iCveUniMed = " + bs.getFieldValue("iCveUniMed") + " and iCveModulo = " + bs.getFieldValue("iCveModulo") + " and iCveArea = 0 ");

                                       if(vcGRLAreaModulo.size() > 0){
                                         vGRLAreaModulo = (TVGRLAreaModulo) vcGRLAreaModulo.get(0);
                                         out.print("<td><input type='text' name='txt" + iObjeto + "' size='40' maxlength='100' value='" + vGRLAreaModulo.getCResponsable() + "'></td>");
                                         out.print("<td align='center'><input type='checkbox' value='' name='chk" + iObjeto + "' checked></td>");
                                       }
                                       else{
                                         out.print("<td><input type='text' name='cResponsable' size='40' maxlength='100' value=''></td>");
                                         out.print("<td align='center'><input type='checkbox' value='' name='chk" + iObjeto + "'></td>");
                                       }

                                     out.print("<input type='hidden' name='hdUniMed" + iObjeto + "' value='" + bs.getFieldValue("iCveUniMed") + "'>");
                                     out.print("<input type='hidden' name='hdModulo" + iObjeto + "' value='" + bs.getFieldValue("iCveModulo") + "'>");

                                     iObjeto++;
                                   }
                                   out.print("<input type='hidden' name='hdTotalReg' value='" + iObjeto + "'>");
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                   out.print("</tr>");
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