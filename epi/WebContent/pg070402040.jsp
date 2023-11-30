<%/**
 * Title: Listado de Investigaciones por Unidad Médica
 * Description: Investigación del Personal
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. González Paz
 * @modified by: Javier Mendoza
 * @modification date: August 18th 2004
 * @version 1
 * Clase: ?
 */%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<html>
<%
  pg070402040CFG  clsConfig = new pg070402040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070402040.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cModulo   = "";
  String cFecha    = "";
  String cCita   = "";

  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "ID DGPMPT|";    // modificar
  String cCveOrdenar  = "iIDDGPMPT|";  // modificar
  String cDscFiltrar  = "ID DGPMPT|";    // modificar
  String cCveFiltrar  = "iIDDGPMPT|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                 // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

      String cCampo = "";
      String cPanel = "";

    if ( (request.getParameter("hdOPPbaRapida") == null) ||
         (request.getParameter("hdBoton").compareTo("Guardar") == 0) ||
          (request.getParameter("hdBoton").compareTo("Cancelar") == 0)){
         cCampo = "0";
    }
    else{
         cCampo = "" + request.getParameter("hdOPPbaRapida");
     }



  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = "Hidden"; //clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  //int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  //TFechas dtFecha = new TFechas();
  //int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<%   /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */
    new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros);
%><link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">

 <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave2  = "" + bs.getFieldValue("iCveUniMed", "");
       cModulo  = "" + bs.getFieldValue("iCveModulo", "");
       cFecha  = "" + bs.getFieldValue("cDscFecha", "");
       cCita  = "" + bs.getFieldValue("iCveCita", "");
        }
  %>


  <input type="hidden" name="FILNumReng" value="<%=request.getParameter("FILNumReng")!=null?request.getParameter("FILNumReng"):vParametros.getPropEspecifica("NumRengTab")%>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):"4"%>">

  <!-- input type="hidden" name="hdLCondicion" value="<%=request.getParameter("hdLCondicion")!=null?request.getParameter("hdLCondicion"):""%>" -->
  <!-- input type="hidden" name="hdLOrdenar" value="<%=request.getParameter("hdLOrdenar")!=null?request.getParameter("hdLOrdenar"):""%>" -->


  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">

  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdiCvePersonal" value="">
  <input type="hidden" name="iCvePersonal" value="">
  <input type="hidden" name="hdiIDDGPMPT" value="<%=bs != null ?  bs.getFieldValue("iIDDGPMPT","") : ""%>">


  <input type="hidden" name="iCveUniMed" value="<%=cClave2%>">
  <input type="hidden" name="hdICveUniMed" value="<%=cClave2%>">
  <input type="hidden" name="iCveModulo" value="<%=cModulo%>">
  <input type="hidden" name="hdICveModulo" value="<%=cModulo%>">

  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%

   TEtiCampo vEti = new TEtiCampo();
   if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="11">Datos del Accidente</td></tr>
          <td class="EEtiqueta" colspan="2">Año:</td>
          <td class="ECampo" colspan="2"><select name="iAnioSel">
            <option value="0">Seleccione...</option>
<%    int iAnioFin=java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
      int iAnioIni=iAnioFin-Integer.parseInt(vParametros.getPropEspecifica("Anios"));
      do{
        String selected=Integer.toString(iAnioFin).equals(request.getParameter("iAnioSel"))?" selected":"";
%>            <option value="<%=iAnioFin%>"<%=selected%>><%=iAnioFin%></option>
<%    }while(iAnioFin-->iAnioIni);
%>          </select></td>
          <td class="EEtiqueta" colspan="2">Modo de Transporte:</td>
          <td class="ECampo" colspan="3"><select name="iCveMdoTransSel">
<%    Vector vTmp=clsConfig.getMdoTrans();
%>            <option value="0"><%=(vTmp!=null && vTmp.size()!=0)?"Seleccione...":"Sin datos disponibles"%></option>
<%    if(vTmp!=null && vTmp.size()!=0){
        for(Enumeration e=vTmp.elements();e.hasMoreElements();){
          TVGRLMdoTrans vGRLMdoTrans=(TVGRLMdoTrans)e.nextElement();
          String selected=Integer.toString(vGRLMdoTrans.getICveMdoTrans()).equals(request.getParameter("iCveMdoTransSel"))?" selected":"";
%>            <option value="<%=vGRLMdoTrans.getICveMdoTrans()%>"<%=selected%>><%=vGRLMdoTrans.getCDscMdoTrans()%></option>
<%      }
      }
     out.println("<input type=\"hidden\" name=\"iAnioSel\" value=\""+request.getParameter("iAnioSel")+"\">");
     out.println("<input type=\"hidden\" name=\"iCveMdoTransSel\" value=\""+request.getParameter("iCveMdoTransSel")+"\">");
     out.println("<input type=\"hidden\" name=\"iCveMdoTrans\" value=\""+request.getParameter("iCveMdoTransSel")+"\">");

%>          </select></td>
        </tr>
        <tr>
          <td class="EEtiqueta" colspan="2">ID DGPMPT:</td>
          <%
          if(bs!=null){
           out.print(vEti.CeldaCampoCS("ECampo","text",10,10,2,"iIDDGPMPT", bs.getFieldValue("iIDDGPMPT","&nbsp;").toString(),0,"","fMayus(this);",false,true,true));
          }
          else
            out.println("<td class='ECampo' colspan='2'>&nbsp;</td>");
          %>
          <td class="EEtiqueta" colspan="2">ID Mdo Transporte:</td>
          <%
          if(bs!=null){
           out.print(vEti.CeldaCampoCS("ECampo","text",10,10,3,"iIDMdoTrans", bs.getFieldValue("iIDMdoTrans","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
          }
          else
            out.println("<td class='ECampo' colspan='2'>&nbsp;</td>");
          %>
         </tr>
         <tr>
          <td class="EEtiqueta" colspan="2">Fecha del Accidente:</td>
            <%
          if(bs!=null){
           out.print(vEti.CeldaCampoCS("ECampo","text",10,10,2,"dtAccidente", bs.getFieldValue("cDscDtAccidente","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
          }
          else
            out.println("<td class='ECampo' colspan='2'>&nbsp;</td>");
          %>
          <td class="EEtiqueta" colspan="2">Descripci&oacute;n Breve:</td>
            <%
          if(bs!=null){
           out.print(vEti.CeldaCampoCS("ECampo","text",10,10,3,"cDscBreve", bs.getFieldValue("cDscBreve","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
          }
          else
             out.println("<td class='ECampo' colspan='2'>&nbsp;</td>");
          %>
        </tr>
<%    if(bs!=null){


         out.println("<tr>");
         out.println(vEti.TextoCS("ETablaSTC","Personal",9));
         out.println("</tr>");
         out.println("<tr>");
         out.println("<td align='center' class='EEtiquetaC'>Expediente</td>");
         out.println("<td align='center' class='EEtiquetaC'>Nombre</td>");
         out.println("<td align='center' class='EEtiquetaC' colspan='7'>Situación</td>");
         out.println("</tr>");


         Vector vcPersonal = new Vector();
         Object obElemento;
         int i = 0;
         TDINVPersonal dINVPersonal = new TDINVPersonal();
         vcPersonal = dINVPersonal.FindPersonal(request.getParameter("iAnioSel"),
                                                request.getParameter("iCveMdoTransSel"),
                                                bs.getFieldValue("iIDDGPMPT","&nbsp;").toString());

        if (vcPersonal.size() > 0){
           for (i = 0;i < vcPersonal.size();i++){
           obElemento =  vcPersonal.get(i);
           TVINVPersonal vINVPersonal = (TVINVPersonal) obElemento;
           out.println("<tr>");
           out.println(vEti.Texto("ECampo","" + vINVPersonal.getICveExpediente()));
           out.println("<input type=\"hidden\" name=\"iCveExpediente\" value=\""+vINVPersonal.getICveExpediente()+"\">");
           out.println("<input type=\"hidden\" name=\"hdICveExpediente\" value=\""+vINVPersonal.getICveExpediente()+"\">");
           out.println(vEti.Texto("ECampo",vINVPersonal.getCNombreCompleto()));
           out.println(vEti.Texto("ECampo",vINVPersonal.getCDscSituacion()));
           out.println("<input type=\"hidden\" name=\"iNumExamen\" value=\""+vINVPersonal.getINumExamINV()+"\">");
            out.println("<input type=\"hidden\" name=\"hdINumExamen\" value=\""+vINVPersonal.getINumExamINV()+"\">");
           out.print("<td align='center'>");
           out.print(vEti.clsAnclaTexto("EEtiqueta","Investigación","javascript:fIrPagina("+vINVPersonal.getICvePersonal()+",43,0);","Investigación"));
           out.print("</td>");
           out.print("<td align='center'>");
           out.print(vEti.clsAnclaTexto("EEtiqueta","Accidentes Previos","javascript:fIrPagina("+vINVPersonal.getICvePersonal()+",42,0);","Buscar"));
           out.print("</td>");
           out.println(vEti.Texto("ECampo","Temporal"));
           out.print("<td align='center'>");
           out.print(vEti.clsAnclaTexto("EEtiqueta","Resultado del Examen Post-Accidente","javascript:fIrPagina("+vINVPersonal.getICvePersonal()+",20,1);","Buscar"));
           out.print("</td>");
           /*
           out.print("<td align='center'>");
           out.print(vEti.clsAnclaTexto("EEtiqueta","Emitir Recomendación","javascript:fIrPagina("+vINVPersonal.getICvePersonal()+",42,0);","Buscar"));
           out.print("</td>");
           */
           out.print("<td align='center'>");
           out.print(vEti.clsAnclaTexto("EEtiqueta","Dictamen","javascript:fIrPagina("+vINVPersonal.getICvePersonal()+",40,1);","Buscar"));
           out.print("</td>");
           out.println("</tr>");
          }
        }


%>
<%    }else{
%>        <tr class="EEtiqueta"><td>Mensaje:</td><td class="ECampo" colspan="10">No existen datos coincidentes con el filtro proporcionado</td></tr>
<%    }
%>      </table>
    </td></tr>
<%  }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<%  vEntorno.clearListaImgs();
%></html>
