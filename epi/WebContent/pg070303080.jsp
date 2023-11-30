<%/**
 * Title: Listado de Turnos de Validación
 * Description: JSP para listar los turnos de validación de toxicología
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Esteban Viveros
 * @version 1
 * Clase: ?
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<html>
<%
  pg070303080CFG  clsConfig = new pg070303080CFG();               // modificar Ok

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070303080.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070303080.jsp\" target=\"FRMCuerpo"); // modificar Ok
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070303081.jsp";           // modificar Ok
  String cOperador    = "1";                         // modificar ?
  String cDscOrdenar  = "Sustancia|";                // modificar Ok
  String cCveOrdenar  = "cDscSustancia|";            // modificar Ok
  String cDscFiltrar  = "Sustancia|";                // modificar Ok
  String cCveFiltrar  = "cDscSustancia|";            // modificar Ok
  String cTipoFiltrar = "8|";                        // modificar Ok
  boolean lFiltros    = true;                        // modificar Ok
  boolean lIra        = false;                       // modificar Ok
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
  String cUpdStatus  = clsConfig.getUpdStatus();//Si hay elementos en el bs muestra el boton de guardar
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  int iCveEquipo = 0;
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070303080P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070303080P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }
  function fIrCatalogo(cCampoClave, cPropiedad){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070303081.jsp';
    form.submit();
  }
  function fValidaTodo(){
    var form=document.forms[0];
    var elems=form.elements;
    var re=/[a-z,A-Z]/;
    for(var i=0;i<elems.length;i++){
      var elem=elems[i];
      if(elem.type.toUpperCase()=="TEXT" &&
         elem.name.indexOf("dDilucion")==0 &&
         elem.value.length>0){
        if(isNaN(elem.value) || re.test(elem.value)){
          if(elem.value.toUpperCase()!="D"){
            alert("Los Datos deben ser numéricos, o una 'D'(=1).");
            elem.focus();
            elem.select();
            return false;
          }
        }else{
          var fVal=parseFloat(elem.value);
          if(fVal<0){
            alert("Los Datos deben ser positivos.");
            elem.focus();
            elem.select();
            return false;
          }
          elem.value=fVal;
        }
      }
    }
    return true;
  }
</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>" onSubmit="return fOnSubmit();">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdRowNum" value="<% if (bs!=null) out.print(bs.pageNo());%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table border="1" background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
%>  <tr>
      <td valign="top">
      &nbsp;
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="8">B&uacute;squeda del Lote</td></tr>
      <tr>
        <td class="EEtiqueta">A&ntilde;o:</td>
        <td class="ETabla">
          <select name="iAnio" size="1" onChange="">
<%    for(int i=iAnio;i>=(iAnio-iAniosAtras);i--){
        if(request.getParameter("iAnio")!=null&&request.getParameter("iAnio").equalsIgnoreCase(Integer.toString(i)))
          out.print("<option value=\""+i+"\" selected>"+i+"</option>");
        else
          out.print("<option value=\""+i+"\">"+i+"</option>");
      }
%>          </select>
        </td>
        <td class="EEtiqueta">Laboratorio:</td>
        <td class="ETabla">
<%    TVUsuario vUsuario=(TVUsuario)request.getSession(true).getAttribute("UsrID");
      int iCveProceso=Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
      out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,document.forms[0].iAnio.value,document.forms[0].iCveUniMed.value,'',document.forms[0].iCveLoteCualita);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0", true));
%>        </td>
        <td class="EEtiqueta">Lote:</td>
        <td class="ETabla">
          <select name="iCveLoteCualita" size="1" onChange="llenaSLT(2,document.forms[0].iAnio.value,document.forms[0].iCveUniMed.value,document.forms[0].iCveLoteCualita.value,document.forms[0].iCveExamCualita);">
<%    if(request.getParameter("iAnio")!=null && request.getParameter("iCveUniMed")!=null){
        TDLoteCualita dLoteCualita=new TDLoteCualita();
        TVLoteCualita vLoteCualita=new TVLoteCualita();
        vLoteCualita.setIAnio(Integer.parseInt(request.getParameter("iAnio")));
        vLoteCualita.setICveLaboratorio(Integer.parseInt(request.getParameter("iCveUniMed")));
        Vector vExCuali=new Vector();
        vExCuali=dLoteCualita.FindAllLoteCualita2(vLoteCualita);
        if(vExCuali.size()>0){
          out.print("<option value=\"0\">Seleccione...</option>");
          for(int i=0;i<vExCuali.size();i++){
            vLoteCualita=(TVLoteCualita)vExCuali.get(i);
             if(request.getParameter("iCveLoteCualita")!=null&&request.getParameter("iCveLoteCualita").equalsIgnoreCase(Integer.toString(vLoteCualita.getICveLoteCualita())))
               out.print("<option value=\""+vLoteCualita.getICveLoteCualita()+"\" selected>"+vLoteCualita.getICveLoteCualita()+"</option>");
             else
               out.print("<option value=\""+vLoteCualita.getICveLoteCualita()+"\">"+vLoteCualita.getICveLoteCualita()+"</option>");
          }
        }else{
          out.print("<option value=\"0\">Datos no disponibles...</option>");
        }
      }
%>          </select>
        </td>
        <td class="EEtiqueta">Ex&aacute;menes:</td>
        <td class="ETabla">
          <select name="iCveExamCualita" size="1">
<%    if(request.getParameter("iAnio")!=null && request.getParameter("iCveUniMed")!=null && request.getParameter("iCveLoteCualita")!=null){
        TDTOXExamenCualita dExamenCualita=new TDTOXExamenCualita();
        TVTOXExamenCualita vExamenCualita=new TVTOXExamenCualita();
        Vector vExamCualita=new Vector();
        String cFiltro="";
        String cOrden="";
        cFiltro = " WHERE TOXExamenCualita.iAnio = " + request.getParameter("iAnio") +
                  "   AND TOXExamenCualita.iCveLaboratorio = " + request.getParameter("iCveUniMed") +
                  "   AND TOXExamenCualita.iCveLoteCualita = " + request.getParameter("iCveLoteCualita") +
                  "   AND TOXExamenCualita.lAutorizado = 1";
        cOrden = " ORDER BY TOXExamenCualita.iCveExamCualita " ;
        vExamCualita=dExamenCualita.FindByAll(cFiltro,cOrden);
        if(vExamCualita.size()>0){
          out.print("<option value=\"0\">Seleccione...</option>");
          for(int i=0;i<vExamCualita.size();i++){
             vExamenCualita=(TVTOXExamenCualita)vExamCualita.get(i);
             if(request.getParameter("iCveExamCualita")!=null&&request.getParameter("iCveExamCualita").equalsIgnoreCase(Integer.toString(vExamenCualita.getICveExamCualita()))){
               out.print("<option value=\""+vExamenCualita.getICveExamCualita()+"\" selected>"+vExamenCualita.getICveExamCualita()+"</option>");
               iCveEquipo = vExamenCualita.getICveEquipo();
             }
             else
               out.print("<option value=\""+vExamenCualita.getICveExamCualita()+"\">"+vExamenCualita.getICveExamCualita()+"</option>");
          }
        }else{
          out.print("<option value=\"0\">Datos no disponibles...</option>");
        }
      }
%>          </select>
        </td>
      </tr>
    </table>
    &nbsp;
    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="10">Calibradores del Lote por Sustancia</td></tr>
      <%
      Vector vcCalibradores=clsConfig.getCalibradores();
      boolean lEspecial = false;
      if(vcCalibradores.size()>0){
        if (iCveEquipo == Integer.parseInt(vParametros.getPropEspecifica("TOXEquipoEsp").toString()))
          lEspecial = true;
      %>
      <tr>
        <td class="ETablaT" rowspan="2">Sustancia</td>
        <td class="ETablaT" colspan="3">Concentraci&oacute;n</td>
        <td class="ETablaT" rowspan="2">Reactivo</td>
        <td class="ETablaT" colspan="2">Especificaciones</td>
        <td class="ETablaT" colspan="3"><%= lEspecial ? "Absorbancia" : "Coeficiente de Correlaci&oacute;n" %></td>
      </tr>
      <tr>
        <td class="ETablaT"><%= lEspecial ? "Negativo" : "&nbsp;" %></td>
        <td class="ETablaT">Corte</td>
        <td class="ETablaT"><%= lEspecial ? "Positivo" : "&nbsp;" %></td>
        <td class="ETablaT"><%= lEspecial ? "Negativo - Corte" : "&nbsp;" %></td>
        <td class="ETablaT"><%= lEspecial ? "Corte - Positivo" : "&nbsp;" %></td>
        <td class="ETablaT"><%= lEspecial ? "Negativo " : "Curva Te&oacute;rica" %></td>
        <td class="ETablaT"><%= lEspecial ? "Corte " : "Curva Experimental" %></td>
        <td class="ETablaT"><%= lEspecial ? "Positivo" : "&nbsp;" %></td>
      </tr>
      <%
        for(Enumeration eCalibradores=vcCalibradores.elements();eCalibradores.hasMoreElements();){
          TVTOXCualtSust vTVTOXCualtSust=(TVTOXCualtSust)eCalibradores.nextElement();
      %>
        <tr>
        <td class="EEtiquetaL"><%=vTVTOXCualtSust.getcDscSustancia()%></td>
        <td class="ETablaR"><%=vTVTOXCualtSust.getDCorteNeg_co()%></td>
        <td class="ETablaR"><%=vTVTOXCualtSust.getDCorte_co()%></td>
        <td class="ETablaR"><%=vTVTOXCualtSust.getDCortePost_co()%></td>
        <td class="EEtiquetaL"><%=vTVTOXCualtSust.getCDscReactivo()%></td>
        <td class="ETablaR"><%=lEspecial ? vTVTOXCualtSust.getDCorteNeg_r(): vTVTOXCualtSust.getDCorteNeg_ca()%></td>
        <td class="ETablaR"><%=vTVTOXCualtSust.getDCortePost_r()%></td>
        <td class="ETablaR"><%=vTVTOXCualtSust.getDCorteNeg_ca()%></td>
        <td class="ETablaR"><%=vTVTOXCualtSust.getDCorte_ca()%></td>
        <td class="ETablaR"><%=vTVTOXCualtSust.getDCortePost_ca()%></td>
      </tr>
    <% }
    }
    else{
      out.println("<tr>");
      out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
      out.println("</tr>");
    }
    %>
    </table>
    &nbsp;
<%    Vector vcSubstancias=clsConfig.getSubstancias();
      int iSize=vcSubstancias.size();
%>
     <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="<%=2+2*iSize%>">An&aacute;lisis Presuntos Positivos</td></tr>
      <tr>

        <td class="ETablaT" rowspan="2">An&aacute;lisis</td>
<%    for(Enumeration e=vcSubstancias.elements();e.hasMoreElements();){
%>        <td class="ETablaT" colspan="2"><%=((TVTOXCualtSust)e.nextElement()).getcDscSustancia()%></td>
<%    }
%>        <td class="ETablaT" rowspan="2">Resultado</td>
      </tr>
      <tr>
<%    for(int i=0;i<iSize;i++){
%>        <td class="ETablaT">Concentraci&oacute;n</td>
        <td class="ETablaT">Diluci&oacute;n</td>
<%    }
%>      </tr>
<%    if(bs!=null){
        bs.start();
        while(bs.nextRow()){
%>      <tr>
        <%=vEti.Texto("ETablaR",bs.getFieldValue("iCveAnalisis", "&nbsp;").toString())%>
<%        String cPositivo="Negativo";
          for(Enumeration e=vcSubstancias.elements();e.hasMoreElements();){
            int iIndice=((TVTOXCualtSust)e.nextElement()).getICveSustancia();
            String cIndice=
              bs.getFieldValue("iCveAnalisis"   ,"0")+"|"+
              bs.getFieldValue("iCveLaboratorio","0")+"|"+
              bs.getFieldValue("iAnio"          ,"0")+"|"+
              bs.getFieldValue("iCveLoteCualita","0")+"|"+
              bs.getFieldValue("iCveExamCualita","0")+"|"+
              iIndice;
            boolean lCampo="1".equals(bs.getFieldValue("lPositivoParaIdSustancia"+iIndice,"0").toString());
            if(!lCampo)
              lCampo="1".equals(bs.getFieldValue("lDudosoParaIdSustancia"+iIndice,"0").toString());

            if(lCampo) cPositivo="Positivo";
            String cDilucion=bs.getFieldValue("dDilucionParaIdSustancia"+iIndice,"").toString();
            if(1.0==Double.parseDouble(cDilucion)) cDilucion="D";
%>        <%=vEti.Texto(lCampo?"EPositivosR":"ETablaR",bs.getFieldValue("dResultadoParaIdSustancia"+iIndice,"").toString())%>
        <%=clsConfig.miCeldaCampo("ETablaR","text",10,10,"dDilucion|"+cIndice,lCampo?cDilucion:"",0,"","",false,true,lCampo,lCampo?"&nbsp;1:&nbsp;":"&nbsp;")%>
<%        }
%>        <td class="EPositivos"><%=cPositivo%></td>
      </tr>
<%      }
      }
      else{
        out.println("<tr>");
        out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
        out.println("</tr>");
      }

%>    </table>
      </td>
      </tr>
<%  }else{
%>  <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
