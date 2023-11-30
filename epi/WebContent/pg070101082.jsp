<%/**
 * Title: Detalle de Perfil Médico Científico
 * Description: Detalle de Perfil Médico Científico
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Romeo Sanchez
 * @version 1
 * Clase: pg070101080
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<html>
<%
String accion = request.getParameter("hdBoton");
//System.out.print("accion: " + accion);
//System.out.print("valor de iCveEspecialidad: " + request.getParameter("iCveEspecialidad"));

  pg070101082CFG  clsConfig = new pg070101082CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101082.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101082.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave      = "";
  String cClaveA     = "";
  String cClaveB     = "";
  String cPosicion  = "";
  String cPropiedad = "";
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  TFechas tf = new TFechas();
  java.sql.Date d = null;
  String fechaFormateada = "";
  boolean showCombo = accion.equals("Modificar")?true:false;

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Perfil|Especialidad|";    // modificar
  String cCveOrdenar  = "iCvePerfil|iCveEspecialidad|";  // modificar
  String cDscFiltrar  = "Perfil|Especialidad|";    // modificar
  String cCveFiltrar  = "iCvePerfil|iCveEspecialidad|";  // modificar
  String cTipoFiltrar = "7|7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "";             // modificar

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
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js

  function fValidaTodo(){
    var form = document.forms[0];
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.iCvePerfil)
        cVMsg = cVMsg + fSinValor(form.iCvePerfil,3,'Perfil', false);
      if (form.iCveEspecialidad)
        cVMsg = cVMsg + fSinValor(form.iCveEspecialidad,3,'Especialidad', false);
      if (form.cEspecificacion)
        cVMsg = cVMsg + fSinValor(form.cEspecificacion,0,'Especificación', true);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
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
       // cPosicion almacena la posición del registro actual...
       cPosicion = Integer.toString(bs.rowNo());
//System.out.println("posicion en bs: " + cPosicion);
       cClave  = accion.startsWith("Guardar")?bs.getFieldValue("iCvePerfil").toString():request.getParameter("hdCampoClave");
       cClaveA  = accion.startsWith("Guardar")?bs.getFieldValue("iCvePerfil").toString():request.getParameter("hdCampoClave1");
       cClaveB  = accion.startsWith("Guardar")?bs.getFieldValue("iCveEspecialidad").toString():request.getParameter("hdCampoClave2");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     } else {
       cClave  = request.getParameter("iCvePerfil");
       cClaveA  = request.getParameter("iCvePerfil");
       cClaveB  = request.getParameter("iCveEspecialidad");
       cPosicion = request.getParameter("hdRowNum");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClaveA%>">
  <input type="hidden" name="hdCampoClave1" value="<%=cClaveA%>"><%//=cClaveA%>
  <input type="hidden" name="hdCampoClave2" value="<%=cClaveB%>"><%//=cClaveB%>
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="iCveMdoTrans" value="<%=request.getParameter("iCveMdoTrans")%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <%
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                Vector vcPersonal;
                            %>
                            <tr><td class="ETablaT" colspan="6">Detalle del Perfil Médico Científico</td></tr>
                            <%
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();

                          if (lNuevo){

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Perfil:"));
                                    //out.print(vEti.SelectOneRow("ECampo", "iCvePerfil", "", new TDMEDPerfilMC().findPerfilGpoMdo(""), "iCvePerfil",desc, request, cClaveA));
%>
<td class="ECampo">
    <SELECT NAME="iCvePerfil" SIZE="1" onChange="">
<%
   Vector perfiles = new TDMEDPerfilMC().findPerfilGpoMdo(" WHERE m.iCveMdoTrans="+request.getParameter("iCveMdoTrans")," order by m.cDscMdoTrans, g.cDscGrupo ");
   String selected="";
   String llave="";
   String valor="";
   for(int c = 0; c < perfiles.size(); c++) {
       TVMEDPerfilMC tv = (TVMEDPerfilMC) perfiles.elementAt(c);
       if (tv.getDtInicio()==null) {
           fechaFormateada = "&nbsp;";
       } else {
           d = tv.getDtInicio();
           fechaFormateada = sdf.format(d);
       }
       llave = ""+tv.getICvePerfil();
       valor = tv.getCDscMdoTrans()+" - "+tv.getCDscGrupo()+" ["+fechaFormateada+"]";
String p = request.getParameter("iCvePerfil");
       selected = p!=null&&p.equals(llave)?"SELECTED":"";
%>
        <option <%=selected%> value="<%=llave%>"><%=valor%></option>
<% } %>
    </SELECT>
</td>
<%
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Especialidad:"));
                                    out.print(vEti.SelectOneRow("ECampo", "iCveEspecialidad", "", new TDMEDEspecialidad().FindByAll("",""), "iCveEspecialidad","cDscEspecialidad", request, ""));
                                    out.println("</tr>");

                                    out.println("<tr>");
//                                    out.print(vEti.Texto("EEtiqueta","Especificación:"));
                                    out.println("<td class=\"EEtiqueta\">Especificación:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                    out.println("<td class=\"ECampo\" colspan=\"1\"><textarea cols=\"50\" rows=\"4\"  name=\"cEspecificacion\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\"></textarea></td>");
                                    out.println("</tr>");

                                } else {
                                   if (bs != null) {


String perf = "0";
String espc   = "0";
String where = "";

where = " and p.iCvePerfil = " + bs.getFieldValue("iCvePerfil");

Vector v = new TDMEDPerfilMC().findPerfilGpoMdo( where , " order by p.iCvePerfil");
if (v.size()!=0){
   TVMEDPerfilMC pmc = (TVMEDPerfilMC)v.elementAt(0);
       if (pmc.getDtInicio()==null) {
           fechaFormateada = "";
       } else {
           d = pmc.getDtInicio();
           fechaFormateada = sdf.format(d);
       }
   perf = pmc.getCDscMdoTrans()+" - "+pmc.getCDscGrupo()+" ["+fechaFormateada+"]";
//System.out.println("----------"+perf);
}
                                      out.println("<tr>");


                                      out.print(vEti.EtiCampo("EEtiqueta", "Perfil", "ECampo", "text", 8, 8, "iCvePerfil", perf , 0, "", "fMayus(this);", false, true, false, request));
                                      out.println("<input type=\"hidden\" name=\"iCvePerfil\" value=\""+bs.getFieldValue("iCvePerfil")+"\">"); // para que almacene el valor
                                      out.println("</tr>");


v = new TDMEDEspecialidad().FindByAll(" where iCveEspecialidad="+bs.getFieldValue("iCveEspecialidad","&nbsp;"),"");
if (v.size()!=0){
   TVMEDEspecialidad vme = (TVMEDEspecialidad)v.elementAt(0);
   espc = vme.getCDscEspecialidad();
}
                                    out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "Especialidad","ECampo", "text", 13, 13, "iCveEspecialidad", espc , 0,"", "", false, true, false, request));
                                     out.println("<input type=\"hidden\" name=\"iCveEspecialidad\" value=\""+""+bs.getFieldValue("iCveEspecialidad","")+"\">"); // para que almacene el valor
                                      out.println("</tr>");

if (accion.equals("Modificar")) {
//System.out.println("**************modificar");
                                    out.println("<tr>");
//                                    out.print(vEti.Texto("EEtiqueta","Especificación:"));
                                    out.println("<td class=\"EEtiqueta\">Especificación:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                    out.println("<td class=\"ECampo\" colspan=\"1\"><textarea cols=\"50\" rows=\"4\"  name=\"cEspecificacion\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">"+bs.getFieldValue("cEspecificacion")+"</textarea></td>");
                                    out.println("</tr>");
} else {
//System.out.println("**************otra accion");
//                                    out.print(vEti.EtiCampo("EEtiqueta", "Especificación", "ECampo", "text", 8, 8, "iCvePerfil", bs.getFieldValue("cEspecificacion").toString() , 0, "", "fMayus(this);", false, true,lCaptura, request));
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Especificación:"));
                                    out.println("<td class=\"ECampo\" colspan=\"1\"><textarea readonly cols=\"50\" rows=\"4\"  name=\"cEspecificacion\">"+bs.getFieldValue("cEspecificacion").toString()+"</textarea></td>");
                                    out.println("</tr>");
}


out.println("</table>");

                                   } else {
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampo("EResalta", "Mensaje:", "EResalta", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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



