<%/**
 * Title: Archivo
 * Description: Archivo
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Marco A. González Paz
 * @version 1
 * Clase: pg070306010CFG
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.util.*"%>
 
<html>
<%
  pg070105070CFG  clsConfig = new pg070105070CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070105070.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070105070.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070306011.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Código|Descrpción Breve|";    // modificar
  String cCveOrdenar  = "iCodigo|cDscBreve|";  // modificar
  String cDscFiltrar  = "Código|Descripción Breve|";    // modificar
  String cCveFiltrar  = "iCodigo|cDscBreve|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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
  //String cUpdStatus  = "Hidden";
  String cUpdStatus  = "SaveOnly";
 //String cNavStatus  = clsConfig.getNavStatus();
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

function fValidaFecha(fecha){ 

 var form = document.forms[0];
    var Dia;
    var Mes;
    var Año;

 if (form.dtFecha){

         if (form.dtFecha.value != null){
         Dia  = form.dtFecha.value.substring(0, 2);
         Mes  = form.dtFecha.value.substring(3, 5)-1;
         Anio = form.dtFecha.value.substring(6, 10);
         dtFecApi = new Date(Anio,Mes,Dia);

         Dia  = form.Limite.value.substring(0, 2);
         Mes  = form.Limite.value.substring(3, 5)-1;
         Anio = form.Limite.value.substring(6, 10);
         dtFecLimite = new Date(Anio,Mes,Dia);
          if (dtFecApi < dtFecLimite){
                alert("La Fecha no puede ser menor a " +  form.Limite.value + ".");
                 form.dtFecha.value = form.Limite.value;
           }

      }
 }

}

function fBuscar(){
     var form = document.forms[0];
    form.hdBoton.value = "Expediente";
    form.target = "_self";
    form.submit();
}

 function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070105070P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;

        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070105070P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

function fReporte(Expediente){
    var form = document.forms[0];
    form.hdExpImp.value = Expediente;
    form.hdBoton.value = "Reporte";
    form.target = "_self";
    form.submit();
}


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
<form method="<%= vEntorno.getMetodoForm() %>" action="<%=vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%

  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdExpImp" value="<%=request.getParameter("hdExpImp")%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){

        if(request.getParameter("hdBoton").toString().compareTo("Reporte")==0) {
           //out.println(clsConfig.getActiveX());
        }


                                if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
      
                                       if(clsConfig.getAccion().compareToIgnoreCase("Reporte")==0) {
                                                     byte[] data = (byte[])request.getAttribute("REPORTE_PDF");
                                                     long time = System.currentTimeMillis();
                                                                  if(data==null){
                                                                        request.getRequestDispatcher("DictamenError.jsp?time="+time).forward(request,response);
                                                                  }
                                                     response.resetBuffer();
                                                     request.getRequestDispatcher("DictamenPDF.jsp?time="+time).forward(request,response);
                                               }
                                   }
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