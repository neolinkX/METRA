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
  pg070303060CFG  clsConfig = new pg070303060CFG();               // modificar Ok

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070303060.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070303060.jsp\" target=\"FRMCuerpo"); // modificar Ok
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070303061.jsp";           // modificar Ok
  String cOperador    = "1";                         // modificar ?
  String cDscOrdenar  = "Sustancia|";                // modificar Ok
  String cCveOrdenar  = "cDscSustancia|";            // modificar Ok
  String cDscFiltrar  = "Sustancia|";                // modificar Ok
  String cCveFiltrar  = "cDscSustancia|";            // modificar Ok
  String cTipoFiltrar = "8|";                        // modificar Ok
  boolean lFiltros    = true;                        // modificar Ok
  boolean lIra        = false;                       // modificar Ok
  String cEstatusIR   = "Reporte";                  // modificar ?

  // LLamado al Output Header
  TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);
  Vector vcSubstancias=clsConfig.getSubstancias();  //substancias que manejo para varias cosas

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = clsConfig.getUpdStatus(vcSubstancias);//el metodo outputHeader llena el bs asi que cuando llama al getUpdStatus ya lo puedo usar
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
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';

  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070303060P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070303060P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }
  function fIrCatalogo(cCampoClave, cPropiedad){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070303061.jsp';
    form.submit();
  }
  function fValidaTodo(){
    var form=document.forms[0];
    if(form.hdBoton.value=='Guardar' || form.hdBoton.value=='GuardarA'){
      var elems=form.elements;
      var re=/[a-z,A-Z]/;
      for(var i=0;i<elems.length;i++){
        var elem=elems[i];
        if(elem.type.toUpperCase()=="TEXT" &&
           elem.name.indexOf("dResultado")==0 &&
           elem.value.length>0){
          if(isNaN(elem.value) || re.test(elem.value)){
            alert("Los Datos deben ser numéricos.");
            elem.focus();
            elem.select();
            return false;
          }else{
            elem.value=parseFloat(elem.value);
          }
        }
      }
      return confirm("¿Desea guardar el resultado de los análisis?");
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
  <input type="hidden" name="hdTipo" value="">
  <table border="1" background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  String cFaltaCalibrar="";
    if(clsConfig.getAccesoValido()){
    if(clsConfig.getAccion().compareToIgnoreCase("Generar Reporte")==0) {
        out.println(clsConfig.getActiveX());
     }
%>  <tr>
    <td valign="top">
      &nbsp;
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="8">B&uacute;squeda del Lote</td></tr>
      <tr>
        <td class="EEtiqueta">Año:</td>
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
      out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,document.forms[0].iAnio.value,document.forms[0].iCveUniMed.value,'',document.forms[0].iCveLoteCualita);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
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
        cFiltro=" WHERE TOXExamenCualita.iAnio="+request.getParameter("iAnio")+
                "   AND TOXExamenCualita.iCveLaboratorio="+request.getParameter("iCveUniMed")+
                "   AND TOXExamenCualita.iCveLoteCualita="+request.getParameter("iCveLoteCualita");
        cOrden=" ORDER BY TOXExamenCualita.iCveExamCualita";
        vExamCualita=dExamenCualita.FindByAll(cFiltro,cOrden);
        if(vExamCualita.size()>0){
          out.print("<option value=\"0\">Seleccione...</option>");
          for(int i=0;i<vExamCualita.size();i++){
             vExamenCualita=(TVTOXExamenCualita)vExamCualita.get(i);
             if(request.getParameter("iCveExamCualita")!=null&&request.getParameter("iCveExamCualita").equalsIgnoreCase(Integer.toString(vExamenCualita.getICveExamCualita())))
               out.print("<option value=\""+vExamenCualita.getICveExamCualita()+"\" selected>"+vExamenCualita.getICveExamCualita()+"</option>");
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
    &nbsp
    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
<%if (vcSubstancias.size()>0){%>
      <tr>
        <td class="ETablaT">An&aacute;lisis</td>
<%      for(Enumeration e=vcSubstancias.elements();e.hasMoreElements();){%>
            <td class="ETablaT"><%=((TVTOXCualtSust)e.nextElement()).getcDscSustancia()%>
            </td>
<%      }
%>      </tr>

<%  }
    cFaltaCalibrar=clsConfig.verificaSustancias();
    if(cFaltaCalibrar.length()==0 && bs!=null){
        if(cCanWrite.compareToIgnoreCase("yes") == 0    &&
           cUpdStatus.compareToIgnoreCase("SaveCancelOnly") == 0){
           out.print("<tr><td colspan=\"" + (vcSubstancias.size() + 1) + "\" align=\"center\">");
           out.print(vEti.clsAnclaTexto("EAncla","Cargar Información","JavaScript:fGetFile();", "Cargar Información...",""));
           out.print("</td></tr>");
        }
        bs.start();
        while(bs.nextRow()){
%>      <tr>
          <%=vEti.Texto("ETablaR",bs.getFieldValue("iCveAnalisis", "&nbsp;").toString())%>
<%        for(Enumeration e=vcSubstancias.elements();e.hasMoreElements();){
            int iIndice=((TVTOXCualtSust)e.nextElement()).getICveSustancia();
            String cIndice=
              bs.getFieldValue("iCveAnalisis"   ,"0")+"|"+
              bs.getFieldValue("iCveLaboratorio","0")+"|"+
              bs.getFieldValue("iAnio"          ,"0")+"|"+
              bs.getFieldValue("iCveLoteCualita","0")+"|"+
              bs.getFieldValue("iCveExamCualita","0")+"|"+
              iIndice;
            String cResultado=bs.getFieldValue("dResultadoParaIdSustancia"+iIndice,"").toString();
            boolean lCampo=(cResultado.length()==0);
            if(lCampo) cUpdStatus="SaveCancelOnly";
%>        <%=vEti.CeldaCampo("ETablaR","text",10,10,"dResultado|"+cIndice,lCampo?"":cResultado,0,"","",false,true,lCampo)%>
          <input type="hidden" name="<%=cIndice%>" value="<%=cUpdStatus%>">
<%        }
%>      </tr>
<%      }
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
<%  if(cFaltaCalibrar.length()>0){
%><script language="JavaScript">alert("Falta calibrar la(s) siguiente(s) substancia(s):\n<%=cFaltaCalibrar%>");</script>
<%  }
%></html>
