<%/**
 * Title: Consulta de Comportamiento de los Controles
 * Description: JSP para listar y consultar los controles registrados
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Esteban Viveros
 * @version 1
 * Clase: ?
 */%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<html>
<%
  pg070306050CFG  clsConfig = new pg070306050CFG();               // modificar Ok

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070306050.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306050.jsp\" target=\"FRMCuerpo"); // modificar Ok
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070306051.jsp";           // modificar Ok
  String cOperador    = "1";                         // modificar ?
  String cDscOrdenar  = "Control|";                  // modificar Ok
  String cCveOrdenar  = "iCveCtrolCalibra|";         // modificar Ok
  String cDscFiltrar  = "Control|";                  // modificar Ok
  String cCveFiltrar  = "iCveCtrolCalibra|";         // modificar Ok
  String cTipoFiltrar = "7|";                        // modificar Ok
  boolean lFiltros    = false;                        // modificar Ok
  boolean lIra        = false;                        // modificar Ok
  String cEstatusIR   = "Imprimir";                  // modificar ?

  // LLamado al Output Header
  TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
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
  String cClave3    = "";
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

  function fIrCatalogo(cCampoClave, cCampoClave3){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCampoClave3.value = cCampoClave3;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070306021.jsp';
    form.submit();
  }
  function fCambiaLab(cValor){
    form = document.forms[0];
    form.hdBoton.value = cValor;
    form.target = "_self";
//    form.submit();
  }
  function fValidaFecha(cFecha,cName){
    var cErr="";
    var acTmp=cFecha.split("/");
    var dtFecha=new Date(acTmp[2],acTmp[1]-1,acTmp[0]);
    if(isNaN(dtFecha)) cErr="- La "+cName+" no es una fecha valida\n";
    return cErr;
  }

  function fValidaTodo(form){
    var cErr="";
    var cTmp="";
    form = document.forms[0];
    if (form.cbDtPreparacion.checked){
       cTmp=form.dtPreparacion.value;
       if(cTmp.length>0)
          cErr+=fValidaFecha(cTmp,"Fecha de preparación");
       else
          cErr+=fValidaFecha(cTmp,"Fecha de preparación");
    }
    if (form.cbDtCaducidad.checked){
       cTmp=form.dtCaducidad.value;
       if(cTmp.length>0)
          cErr+=fValidaFecha(cTmp,"Fecha de Caducidad");
       else
          cErr+=fValidaFecha(cTmp,"Fecha de Caducidad");
    }
    if (cErr.length>0){
      alert(cErr);
      return false;
    }
    return true;
  }
</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<%
new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm()%>" onsubmit="return fValidaTodo(this);">
  <input type="hidden" name="pg070306050.noFirstTime" value="false">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdRowNum" value="<% if (bs!=null) out.print(bs.pageNo());%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave3" value="<%=cClave3%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%=vParametros.getPropEspecifica("RutaImg")%>fondo01.jpg" width="100%" height="100%">
    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr><td class="ETablaT" colspan="3">Filtros</td></tr>
        <tr><td class="EEtiqueta" colspan="2">Laboratorio:</td><td class="ECampo"><%=vEti.SelectOneRowSinTD("iCveLaboratorio", "fCambiaLab('" + clsConfig.getAccionOriginal() + "');", (Vector) AppCacheManager.getColl("GRLUniMed",""), "iCveUniMed", "cDscUniMed", request, "0",true)%></td></tr>
<%
boolean lFistTime=!"TRUE".equalsIgnoreCase(request.getParameter("pg070306050.noFirstTime"));
  String cChecked=(lFistTime && "1".equals(request.getParameter("cbDtPreparacion")))?" checked":"";
%>        <tr><td class="EEtiqueta"><input type="checkbox" name="cbDtPreparacion" value="1"<%=cChecked%>></td><%=vEti.EtiCampo("EEtiqueta","Fecha de Preparación:","ECampo","text",15,10,"dtPreparacion",request.getParameter("dtPreparacion")!=null?request.getParameter("dtPreparacion"):"",0,"","",false,true,true)%></tr>
<%  cChecked=(lFistTime && "1".equals(request.getParameter("cbDtCaducidad")))?" checked":"";
%>        <tr><td class="EEtiqueta"><input type="checkbox" name="cbDtCaducidad" value="1"<%=cChecked%>></td><%=vEti.EtiCampo("EEtiqueta","Fecha de Caducidad:","ECampo","text",15,10,"dtCaducidad",request.getParameter("dtCaducidad")!=null?request.getParameter("dtCaducidad"):"",0,"","",false,true,true)%></tr>
<%
  TVUsuario vUsuario=(TVUsuario)request.getSession(true).getAttribute("UsrID");
  Vector vcPersonal;
  int iCveProceso=Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
  int iUniMed=0;
  if(request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareTo("")!=0){
    iUniMed=Integer.parseInt(request.getParameter("iCveUniMed"),10);
  }else{
     vcPersonal=vUsuario.getVUniFiltro(iCveProceso);
     if(vcPersonal.size()!=0){
       iUniMed=((TVGRLUMUsuario)vcPersonal.get(0)).getICveUniMed();
     }
  }
  vcPersonal=new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
  cChecked=(lFistTime && "1".equals(request.getParameter("cbICveUsuPrepara")))?" checked":"";
%>        <tr><td class="EEtiqueta"><input type="checkbox" name="cbICveUsuPrepara" value="1"<%=cChecked%>></td><td class="EEtiqueta">Preparado por:</td><td><%=vEti.SelectOneRowSinTD("iCveUsuPrepara", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "")%></td></tr>
<%  cChecked=(lFistTime && "1".equals(request.getParameter("cbICveEmpleoCalib")))?" checked":"";
%>        <tr><td class="EEtiqueta"><input type="checkbox" name="cbICveEmpleoCalib" value="1"<%=cChecked%>></td><td class="EEtiqueta">Tipo:</td><td><%=vEti.SelectOneRowSinTD("iCveEmpleoCalib", "", clsConfig.getTipos(),"iCveEmpleoCalib", "cDscEmpleoCalib", request, "")%></td></tr>
<%  cChecked=(lFistTime && "1".equals(request.getParameter("cbRSituacion")))?" checked":"";
%>        <tr>
          <td class="EEtiqueta"><input type="checkbox"  name="cbRSituacion" value="1"<%=cChecked%>></td><td class="EEtiqueta">Situaci&oacute;n:</td>
<%
  String cSelected0="";String cSelected1="";String cSelected2="";
  switch(Integer.parseInt(request.getParameter("rSituacion")!=null?request.getParameter("rSituacion"):"0")){
    case 2:cSelected2=" checked";break;
    case 1:cSelected1=" checked";break;
    default:cSelected0=" checked";
  }
%>
          <td class="EEtiqueta" style="text-align:left">
            <input type="radio" name="rSituacion" value="0"<%=cSelected0%>>En Uso<br>
            <input type="radio" name="rSituacion" value="1"<%=cSelected1%>>Agotado<br>
            <input type="radio" name="rSituacion" value="2"<%=cSelected2%>>Baja
          </td>
        </tr>
<%
        out.println("<tr><td align=\"center\" colspan=\"3\">");
        out.println(new TCreaBoton().clsCreaBoton(vEntorno, 1, "Buscar", "bBuscar", vEntorno.getTipoImg(), "Buscar el Lote Confirmativo","", vParametros));
        out.println("</td></tr>");
%>

      </table>
    </td>
 </tr>
<%
  if(request.getParameter("pg070306050.noFirstTime")!=null){
    if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr><td class="ETablaT" colspan="8">Controles</td></tr>
        <tr>
          <td class="ETablaT">Control</td>
          <td class="ETablaT">Lote</td>
          <td class="ETablaT">Descripci&oacute;n Breve</td>
          <td class="ETablaT">Tipo</td>
          <td class="ETablaT">Fecha de Preparaci&oacute;n</td>
          <td class="ETablaT" colspan="3">Volumen Total</td>
        </tr>
<%      if (bs != null){
          TFechas dtFechas = new TFechas();
          java.sql.Date dtDespliegue;
          String cFecha = "";
          bs.start();
          while(bs.nextRow()){
            out.print("<tr>");
            out.print(vEti.Texto("ETabla",""+bs.getFieldValue("iCveCtrolCalibra","&nbsp;")));
            out.print(vEti.Texto("ETabla",""+bs.getFieldValue("cLote"          ,"&nbsp;")));
            out.print(vEti.Texto("ETabla",""+bs.getFieldValue("cDscBreve"      ,"&nbsp;")));
            out.print(vEti.Texto("ETabla",""+bs.getFieldValue("cDscEmpleoCalib","&nbsp;")));
            cFecha = "" + bs.getFieldValue("dtPreparacion","&nbsp;");
            //System.out.println("cFecha: "  + cFecha);
            if (cFecha.compareTo("null") != 0 && cFecha.compareTo("") != 0){
                dtDespliegue = dtFechas.getSQLDatefromSQLString(cFecha);
                out.print(vEti.Texto("ETabla","" + dtFechas.getFechaDDMMYYYY(dtDespliegue,"/")));
            }
            else{
              out.print(vEti.Texto("ETabla","&nbsp;"));
           }
            out.print(vEti.Texto("ETabla",""+bs.getFieldValue("dVolumen"       ,"&nbsp;")));

            out.print("<td class=\"ETablaC\">");
            out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" + bs.getFieldValue("iCveLaboratorio","") + "'," +"'"+ bs.getFieldValue("iCveCtrolCalibra","") + "'" + ");","Ir a..."));
            out.println("</td>");

            /*out.print("<td class=\"ETablaC\">");
            out.print(vEti.clsAnclaTexto("EAncla","Comportamiento","JavaScript:document.forms[0].submit();",""));
            out.println("</td></tr>");*/

          }
        }else{
            out.println("<tr>");
            out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 5,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
            out.println("</tr>");
        }
%>      </table>
    </td></tr>
<%    }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%    }  // fin de if(clsConfig.getAccesoValido())
  }  // fin de if(request.getParameter("pg070306050.firstTime")!=null)
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>