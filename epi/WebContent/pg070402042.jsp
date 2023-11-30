<%/**
 * Title: Accidentes Previos
 * Description: Investigación del Personal
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. González Paz
 * @version 1
 * Clase: ?
 */%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<html>
<%
  pg070402042CFG  clsConfig = new pg070402042CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070402042.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402042.jsp\" target=\"FRMCuerpo"); // modificar
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



  String cOperador    = "3";                   // modificar
  String cDscOrdenar  = "No Disponible|";      // modificar
  String cCveOrdenar  = "0|";                  // modificar
  String cDscFiltrar  = "No Disponible|";      // modificar
  String cCveFiltrar  = "0|";                  // modificar
  String cTipoFiltrar = "8|";                  // modificar


  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar


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
  String cNavStatus  = "Hidden"; //clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  //int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
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
  <input type="hidden" name="hdLCondicion" value="<%=request.getParameter("hdLCondicion")!=null?request.getParameter("hdLCondicion"):""%>">
  <input type="hidden" name="hdLOrdenar" value="<%=request.getParameter("hdLOrdenar")!=null?request.getParameter("hdLOrdenar"):""%>">


  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">

  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="">

  <input type="hidden" name="iAnio" value="">
  <input type="hidden" name="iCveMdoTrans" value="">
  <input type="hidden" name="iIDDGPMPT" value="">

  <input type="hidden" name="hdiCvePersonal" value="<%=request.getParameter("hdiCvePersonal")%>">


<%-- inicio de los campos para saltar a las paginas pg070402012 y pg070402014--%>
  <input type="hidden" name="hdiAnio" value="">
  <input type="hidden" name="hdiCveMdoTrans" value="">
  <input type="hidden" name="hdiIDDGPMPT" value="">
  <input type="hidden" name="hdiIdefMedPrev" value="">
<%-- fin de los campos para saltar a las paginas pg070402012 y pg070402014--%>
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%

   TEtiCampo vEti = new TEtiCampo();
   if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="11">Datos del Personal</td></tr>
          <tr>
        <%
            out.print(vEti.EtiCampoCS("EEtiqueta","Nombre:","ECampo","text",10,10,4,"cNombreCompleto", bs.getFieldValue("cNombreCompleto","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
            out.print(vEti.EtiCampoCS("EEtiqueta","Expediente:","ECampo","text",10,10,4,"iCveExpediente", bs.getFieldValue("iCveExpediente","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
        %>
        </tr>
        <tr>
        <%

            int iEdad = 0;
            java.sql.Date dtEdad = dtFecha.getSQLDatefromSQLString(bs.getFieldValue("dtNacimiento","").toString());
            String cDtFecha = dtFecha.getFechaDDMMYYYY(dtEdad,"/");
            dtEdad = dtFecha.getDateSQL(cDtFecha) ;
            iEdad = clsConfig.getEdad(dtEdad);
            out.print(vEti.EtiCampoCS("EEtiqueta","Edad:","ECampo","text",10,10,4,"dtNacimiento", "" + iEdad + " AÑOS" ,0,"","fMayus(this);",false,true,false));
            out.print(vEti.EtiCampoCS("EEtiqueta","Sexo:","ECampo","text",10,10,4,"cSexo", bs.getFieldValue("cSexo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
        %>
        </tr>

<%    if(bs!=null){


         out.println("<tr>");
         out.println(vEti.TextoCS("ETablaSTC","Ex&aacute;menes Anteriores",8));
         out.println("</tr>");
         out.println("<tr>");
         out.println("<td align='center' class='EEtiquetaC'>Id. DGPMPT</td>");
         out.println("<td align='center' class='EEtiquetaC'>No.Examen</td>");
         out.println("<td align='center' class='EEtiquetaC'>Unidad M&eacute;dica</td>");
         out.println("<td align='center' class='EEtiquetaC'>Proceso</td>");
         out.println("<td align='center' class='EEtiquetaC'>Aplicado</td>");
         out.println("<td align='center' class='EEtiquetaC'>Concluido</td>");
         out.println("<td align='center' class='EEtiquetaC'>Modo de Transporte</td>");
         out.println("<td align='center' class='EEtiquetaC'>Categor&iacute;a</td>");
         out.println("<td align='center' class='EEtiquetaC'>Dict&aacute;Mmen</td>");
         out.println("</tr>");


         Vector vcAccidentes = new Vector();
         Object obElemento;
         int i = 0;
         TDINVPersonal dINVPersonal = new TDINVPersonal();
         vcAccidentes = dINVPersonal.FindAccidentes(request.getParameter("hdiCvePersonal"),vParametros.getPropEspecifica("INVProceso"));

        if (vcAccidentes.size() > 0){
           for (i = 0;i < vcAccidentes.size();i++){
           obElemento =  vcAccidentes.get(i);
           TVINVPersonal vINVPersonal = (TVINVPersonal) obElemento;
           out.println("<tr>");
           out.println(vEti.Texto("ETablaR","" + vINVPersonal.getIIDDGPMPT()));
           out.println(vEti.Texto("ETablaR","" + vINVPersonal.getINumExamINV()));
           out.println(vEti.Texto("ECampo",vINVPersonal.getCDscUniMed()));
           out.println(vEti.Texto("ECampo",vINVPersonal.getCDscProceso()));
           out.println(vEti.Texto("ECampo",vINVPersonal.getCDscDtAplicacion()));
           out.println(vEti.Texto("ECampo",vINVPersonal.getCDscDtConcluido()));
           out.println(vEti.Texto("ECampo",vINVPersonal.getCDscMdoTrans()));
           out.println(vEti.Texto("ECampo",vINVPersonal.getCDscPuesto()));
           if (vINVPersonal.getLDictamen() > 0)
              out.println(vEti.Texto("ECampo","APTO"));
           else
              out.println(vEti.Texto("ECampo","NO APTO"));
           out.println("</tr>");
/*
           out.print("<td align='center'>");
           out.print(vEti.clsAnclaTexto("EEtiqueta","Investigación","javascript:fIrPagina();","Buscar"));
           out.print("</td>");
           out.print("<td align='center'>");
           out.print(vEti.clsAnclaTexto("EEtiqueta","Accidentes Previos","javascript:fIrPagina();","Buscar"));
           out.print("</td>");
           out.println(vEti.Texto("ECampo","DOPADO"));
           out.print("<td align='center'>");
           out.print(vEti.clsAnclaTexto("EEtiqueta","Resultado del Examen Post-Accidente","javascript:fIrPagina();","Buscar"));
           out.print("</td>");
           out.print("<td align='center'>");
           out.print(vEti.clsAnclaTexto("EEtiqueta","Emitir Recomendación","javascript:fIrPagina();","Buscar"));
           out.print("</td>");
*/
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
