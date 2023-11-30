
<%/**
 * Title: Evaluación
 * Description: Evaluación
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Romeo Sanchez
 * @version 1
 * Clase: pg070101080
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<%
  pg070101084CFG  clsConfig = new pg070101084CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070101084.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101084.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|Desc. Breve|";    // modificar
  String cCveOrdenar  = "cCIE|cDscBreve|cDscDiagnostico|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|Desc. Breve|";    // modificar
  String cCveFiltrar  = "cCIE|cDscBreve|cDscDiagnostico|";  // modificar
  String cTipoFiltrar = "8|8|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  TFechas tf = new TFechas();
  java.sql.Date d = null;
  String fechaFormateada = "";

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
  String cNavStatus  = "Hidden";//clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
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
  <input type="hidden" name="FILNumReng" value="500"> <!-- <%// if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%> -->
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="500"> <!- <%=cPosicion%> -->
  <input type="hidden" name="hdCampoClave" value=""><%//=request.getParameter("hdCampoClave")%>
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdSaveAction" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">



                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="ETablaTR">Perfil:
                              </td>

<td class="ECampo" colspan="2">
    <SELECT NAME="iCvePerfil" SIZE="1" onChange="fCambiaPerfil('Actual');">
        <option value="0">Seleccione...</option>
<%
//System.out.println("accion: " + request.getParameter("hdBoton"));
//System.out.println("obtenida: " + request.getParameter("hdCampoClave"));

   Vector perfiles = new TDMEDPerfilMC().findPerfilGpoMdo(""," order by m.cDscMdoTrans, g.cDscGrupo "); // where iCvePerfil IN (select iCvePerfil from MEDPerfilEspec)
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
       selected = (llave.equals(request.getParameter("hdCampoClave"))||llave.equals(request.getParameter("iCvePerfil")))?"SELECTED":"";
%>
        <option <%=selected%> value="<%=llave%>"><%=valor%></option>
<% } %>
    </SELECT>
</td>
<%// buscar...
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Buscar",
                                                                        "javascript:fSubmit('Actual')","Buscar"));
                                           out.print("</td>");

// Ir a...
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Ver perfiles",
                                                                        "javascript:fIr('pg070101080','pg070101080.jsp');","Ver perfiles"));
                                           out.print("</td>");
%>
</tr><tr>
                              <td class="ETablaTR">Servicio:
<%
Vector ms = new TDMEDServicio().FindByAll("","");
// agregar la opción 0 "Seleccione..."
TVMEDServicio foo = new TVMEDServicio();
foo.setICveServicio(0);
foo.setCDscServicio("Seleccione...");
ms.insertElementAt(foo,0);

              out.print(vEti.SelectOneRow("ECampo", "iCveServicio", "fCambiaServicio('Actual');", ms , "iCveServicio","cDscServicio", request, ""));

int pf = -1;
if (request.getParameter("iCveServicio")!=null && !request.getParameter("iCveServicio").equals("null")) {
   pf = Integer.parseInt(request.getParameter("iCveServicio"));
} else if (request.getParameter("hdCampoClave")!=null && !request.getParameter("hdCampoClave").equals("null")) {
   pf = Integer.parseInt(request.getParameter("hdCampoClave"));
}

%>                              </td>
                              <td class="ETablaTR">Rama:
<%
String first = "";
ms = new TDMEDRama().FindByAll(" WHERE lActivo=1 AND iCveServicio = "+pf);
TVMEDRama bar = new TVMEDRama();
bar.setICveRama(0);
bar.setCDscRama("Seleccione...");
ms.insertElementAt(bar,0);

if(ms!=null && ms.size()!=0){
//System.out.println("ramas: ");
//for (int i=0; i<ms.size();i++) //System.out.println(i+":"+((TVMEDRama)ms.elementAt(i)).getICveRama());
   first = ((TVMEDRama)ms.elementAt(0)).getICveRama()+"";
}
//System.out.println("first: " + first);
//              out.print("<td class='ECampo' colspan='2'");
              out.print(vEti.SelectOneRow("ECampo", "iCveRama", "fCambia('Actual');", ms , "iCveRama","cDscRama", request, first));
//              out.print("</td");

%>
</td>

</tr>
</table>
&nbsp;
<!-- ******************** -->
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="6" class="ETablaT">S&iacute;ntomas
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Pregunta</td>
                              <td class="ETablaT">Tipo de respuesta</td>
                              <td class="ETablaT">Valor inicial</td>
                              <td class="ETablaT">Valor final</td>
                              <td class="ETablaT">Lógico</td>
                              <td class="ETablaT">Texto</td>
                            </tr>
                            <%
                               if (bs != null){
                                   bs.start();
                                   int c = 0;
                                   while(bs.nextRow()){

                                       String iCvePerfil = bs.getFieldValue("iCvePerfil", "").toString();

/*                                       if ( iCvePerfil!=null &&
                                            (iCvePerfil.equals("0") || iCvePerfil.equals(request.getParameter("iCvePerfil"))) ) {
                                          // si hubiera un registro que no es del perfil solicitado o no viene vacío.
                                          // Cualquier otro registro debe ignorarse
                                          ;
                                       } else {
                                          continue;
                                       }
*/
                                       String iCveServicio = bs.getFieldValue("iCveServicio", "").toString();
                                       String iCveRama = bs.getFieldValue("iCveRama", "").toString();
                                       String iCveSintoma = bs.getFieldValue("iCveSintoma", "").toString();
                                       String cPregunta = bs.getFieldValue("cPregunta", "").toString();
                                       String iCveTpoResp = bs.getFieldValue("iCveTpoResp", "").toString();
                                       String cDscTpoResp = bs.getFieldValue("cDscTpoResp", "").toString();
                                       String dValorI = bs.getFieldValue("dValorI", "").toString();
                                       String dValorF = bs.getFieldValue("dValorF", "").toString();
                                       String lLogico = bs.getFieldValue("lLogico", "").toString();
                                       String cCaracter = bs.getFieldValue("cCaracter", "").toString();

                                       //System.out.println("llave: " + iCveServicio +",\t"+iCveRama+",\t"+iCveSintoma);
                                       boolean existePerfilSintoma = true;
                                       if ( (iCvePerfil==null||iCvePerfil.equals("0")) ) {
                                            existePerfilSintoma = false;
                                       } else {
                                            existePerfilSintoma = true;
                                       }

                                       out.println("<tr>");
%>
<input type="hidden" name="existe_<%=c%>" value="<%=existePerfilSintoma%>">

<input type="hidden" name="iCvePerfil_<%=c%>" value="<%=request.getParameter("iCvePerfil")%>">

<input type="hidden" name="iCveServicio_<%=c%>" value="<%=iCveServicio%>">
<input type="hidden" name="iCveRama_<%=c%>" value="<%=iCveRama%>">
<input type="hidden" name="iCveSintoma_<%=c%>" value="<%=iCveSintoma%>">
<input type="hidden" name="cPregunta_<%=c%>" value="<%=cPregunta%>">
<input type="hidden" name="iCveTpoResp_<%=c%>" value="<%=iCveTpoResp%>">
<input type="hidden" name="cDscTpoResp_<%=c%>" value="<%=cDscTpoResp%>">

<%
boolean esTexto = false;
boolean esCheck = false;
boolean esNumero = false;
boolean esRango = false;
int tpoResp = iCveTpoResp==null||iCveTpoResp.equals("null")?0:Integer.parseInt(iCveTpoResp);

switch (tpoResp) {
  case 1: // si/no
    esCheck = true;
    break;
  case 2: // letras y números
    esTexto = true;
    break;
  case 3: // números
    esNumero = true;
    break;
  case 4: // notas
    esTexto = true;
    break;
  case 5: // rango de números
    esRango = true;
    break;
  default:
    // no válido
}


                                    // sólo se despliegan
                                        out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("cPregunta", "&nbsp;")));
                                        out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("cDscTpoResp", "&nbsp;")));

                                    // los siguientes campos se habilitan/deshabilitan
                                    // dependiendo del tipo de pregunta
                                        out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+c,bs.getFieldValue("dValorI","").toString(),0,"","validaNumero(this);",true, esRango||esNumero)+"</td>");
                                        out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorF_"+c,bs.getFieldValue("dValorF","").toString(),0,"","validaNumero(this);",true, esRango)+"</td>");
                                        out.print(vEti.ToggleMov("ETablaC","lLogico_"+c, existePerfilSintoma?bs.getFieldValue("lLogico", "0").toString():"0","",0,true,"1","0", esCheck));
                                        out.println("<td class=\"ECampo\"><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+c+"\""); //
String disabled = esTexto?"":" disabled ";
                                        out.println(disabled  + " onChange=\"javascript:truncar(this)\" >"+bs.getFieldValue("cCaracter","")+"</textarea></td>");

                                        c++;
                                   }
// este campo almacena el total de renglones mostrados en la página actual
%><input type="hidden" name="hdTotalRows" value="<%=c%>">

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
<%
// guardar
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Guardar",
                                                                        "javascript:fSubmit('Guardar')","Guardar"));
                                           out.print("</td>");
// cancelar
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Cancelar",
                                                                        "javascript:fSubmit('Cancelar')","Cancelar"));
                                           out.print("</td>");

%>
                            </tr>
                          </table>
<%
                               } else {
                                  // no hay datos para mostrar
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampoCS("EResalta", "Mensaje:", "EResalta", "text", 25, 50, 5, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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
