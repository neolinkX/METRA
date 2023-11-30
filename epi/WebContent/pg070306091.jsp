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
  pg070306091CFG  clsConfig = new pg070306091CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070306091.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306091.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveMotBaja|cDscMotBaja|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveMotBaja|cDscMotBaja|";  // modificar
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


  function fValidaTodo(){
    form = document.forms[0];


    if (form.hdBoton.value == 'BorrarB') {
       if(!confirm("¿Está seguro de desactivar el registro?"))
         return false;
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {

    if (form.cDscMotBaja)
        cVMsg = cVMsg + fSinValor(form.cDscMotBaja,0,'Descripción:', true);

    if (form.cDscBreve)
        cVMsg = cVMsg + fSinValor(form.cDscBreve,0,'Descripción Breve:', true);

     if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }


      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
      form.submit();
    }
}

    return true;
  }

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
       cClave  = ""+bs.getFieldValue("iCveMotBaja", "");
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>

                             <tr>
                             <td colspan="3" class="ETablaT">Motivos de Baja</td>
                             </tr>

 <%
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Clave:","ECampo","text",10,10,"iCveMotBaja","&nbsp;",0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Descripción:","ECampo","text",75,100,"cDscMotBaja","&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Descripción Breve:","ECampo","text",40,40,"cDscBreve","&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                      out.print("<tr>"+vEti.EtiToggle("EEtiqueta","Activo:","ECampo","lActivo","1","",1,true,"Si","No",lCaptura)+"<tr>");
                                    //out.println("<tr>");
                                    //out.print(vEti.EtiCampo("EEtiqueta","Activo:","ECampo","text",5,5,"lActivo","&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                    //out.println("</tr>");
                                }
                                else {
                                   if (bs != null){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Clave:","ECampo","text",10,10,"iCveMotBaja", bs.getFieldValue("iCveMotBaja","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    //System.out.println("Clave: " + bs.getFieldValue("iCveMotBaja","&nbsp;").toString() );
                                    out.println("<input type='hidden' name='iCveMotBaja' value='"+ bs.getFieldValue("iCveMotBaja","&nbsp;").toString() +"'>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Descripción:","ECampo","text",75,100,"cDscMotBaja", bs.getFieldValue("cDscMotBaja","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Descripción Breve:","ECampo","text",40,40,"cDscBreve", bs.getFieldValue("cDscBreve","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    // Activo - lActivo
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Activo:"));
                                    String cDisable = "";
                                    String cChecked = "";
                                    if (!lCaptura)
                                       cDisable = "disabled";
                                    if (bs.getFieldValue("lActivo","0").toString().compareToIgnoreCase("0")!=0)
                                       cChecked = "checked";
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lActivo\" colspan=\"2\"  value=\"1\" " + cDisable + " " +  cChecked + ">");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    //out.println("<tr>");
                                    //out.print(vEti.EtiCampo("EEtiqueta","Activo:","ECampo","text",5,5, bs.getFieldValue("lActivo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    //out.println("</tr>");
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
