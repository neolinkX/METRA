<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Jaime Enrique Su�rez Romero
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="com.micper.util.caching.*" %>

<html>
<%
  pg070202020CFG  clsConfig = new pg070202020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070202020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070202020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "|";    // modificar
  String cCveOrdenar  = "|";  // modificar
  String cDscFiltrar  = "|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";            // modificar

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
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";

  Calendar calendar = new GregorianCalendar();
  calendar.setTime(new java.util.Date(new java.util.Date().getTime()));
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  var iMes = <%=request.getParameter("iMeses") == null ? ""+calendar.get(Calendar.MONTH) : request.getParameter("iMeses") %>;
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
    fTipo();
  }
</SCRIPT>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td align="center" width="100%">
  </td></tr>
  <tr><td valign="top">
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
              <tr>
                <td colspan="6" class="ETablaT">Seleccione las Caracter�sticas del Reporte
                </td>
              </tr>
              <tr>
                <%
                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                Vector vcGRLUniMed = vUsuario.getVUniFiltro(Integer.parseInt(vParametros.getPropEspecifica("EMOProceso")));
                out.print(vEti.Texto("EEtiqueta","Unidad M�dica"));
                out.print(vEti.SelectOneRow("ECampo","iCveUniMed","fChg();",vcGRLUniMed,"iCveUniMed","cDscUniMed",request,""));
                  String cUniMed = "0";
                  try{
                    if(request.getParameter("iCveUniMed")==null)
                      cUniMed = ""+((TVGRLUMUsuario)vcGRLUniMed.get(0)).getICveUniMed();
                    else
                      cUniMed = ""+request.getParameter("iCveUniMed");
                  }catch(Exception e){
                    cUniMed = "0";
                  }
                %>
              </tr>
              <tr>
                <%

                  TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
                  TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
                  Vector vGModulo = new Vector();
                  String cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                                      " and GRLUsuMedicos.iCveUniMed =  " + cUniMed +
                                      " and GRLUsuMedicos.iCveProceso = " + vParametros.getPropEspecifica("EMOProceso")  ;
                   //System.out.println("Filtro : " + cFiltro);
                   vGModulo = dGRLUSUMedicos.FindModulos(cFiltro);

                  out.print(vEti.Texto("EEtiqueta", "M�dulo:"));
                  //out.print(vEti.SelectOneRow("ECampo","iCveModulo","",(Vector) AppCacheManager.getColl("GRLModulo",cUniMed+"|"),"iCveModulo","cDscModulo",request,""));+}
                  out.print(vEti.SelectOneRow("ECampo","iCveModulo","fChg();",vGModulo,"iCveModulo","cDscModulo",request,""));


                  /*
                  out.print(vEti.Texto("EEtiqueta", "M�dulo:"));
                  Vector vcModulo = (Vector) AppCacheManager.getColl("GRLModulo",cUniMed+"|");
                  out.print(vEti.SelectOneRow("ECampo","iCveModulo","fChg();",vcModulo,"iCveModulo","cDscModulo",request,""));
                   */
                  String cModulo = ""+request.getParameter("iCveModulo");
                  if(cModulo.equals("null")){
                    cModulo = ""+((TVGRLUSUMedicos) vGModulo.get(0)).getICveModulo();
                  }
                %>
              </tr>
              <tr>
                <%
                int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
                TFechas dtFecha = new TFechas();
                int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
                Vector vcAnios = new Vector();
                int i;
                for(i = 0; i < iAniosAtras; i++){
                   vcAnios.add(""+iAnio+"|"+iAnio);
                   iAnio--;
                }
                out.print(vEti.Texto("EEtiqueta","A�o:"));
                out.print(vEti.SelectOneRow("ECampo","iAnio","fChg();",vcAnios,request,""+iAnio));
                %>
              </tr>
              <tr>
                <%
                StringTokenizer stMeses = new StringTokenizer(vParametros.getPropEspecifica("NombresMes"),",");
                Vector vcMeses = new Vector();
                i = 1;
                while(stMeses.hasMoreTokens()){
                  vcMeses.add(""+i+"|"+stMeses.nextToken());
                  i++;
                }
                out.print(vEti.Texto("EEtiqueta","Mes:"));
                out.print(vEti.SelectOneRow("ECampo","iMeses","fChg();",vcMeses,request,""));
                %>
              </tr>
              <tr>
                <%
                String cMeses = ""+request.getParameter("iMeses");
                String cCond = " and {FN YEAR(expexamaplica.dtsolicitado)} = " + calendar.get(Calendar.YEAR) +
                               " and {FN MONTH(expexamaplica.dtsolicitado)} = " + calendar.get(Calendar.MONTH);

                if(!cMeses.equals("null")){
                   cCond = " and {FN YEAR(expexamaplica.dtsolicitado)} = " + request.getParameter("iAnio") +
                           " and {FN MONTH(expexamaplica.dtsolicitado)} = " + request.getParameter("iMeses");
                }

                out.print(vEti.Texto("EEtiqueta","Empresas con EMOs en el periodo:"));
                out.print(vEti.SelectOneRow("ECampo","iCveEmpresa","",new TDGRLEmpresas().FindByEmpresasEMO(cCond + " ",cUniMed,cModulo),"ICVEEMPRESA","CNOMBRE",request,""));
                %>
              </tr>
              <tr>
              <td colspan="6" align="center">
                <%
                out.print(vEti.clsAnclaTexto("EAncla","Notificaci�n por Empresa","JavaScript:fBuscar1();", "Oficio...","")+"&nbsp;&nbsp;&nbsp;");
                out.print(vEti.clsAnclaTexto("EAncla","No aptos por Empresa","JavaScript:fBuscar2(false);", "Operadores...","")+"&nbsp;&nbsp;&nbsp;");
                out.print(vEti.clsAnclaTexto("EAncla","No aptos Global","JavaScript:fBuscar2(true);", "Operadores...",""));
                %>
              </td></tr>
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
