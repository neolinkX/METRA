<%/**
 * Title: pg070303011
 * Description:Listado para el manejo de muestras
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version 1.0
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.TVTOXEnvio" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.Vector"%>

<html>
<%
  pg070303012CFG  clsConfig = new pg070303012CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070303012.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070303012.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Orden|NIM|Fecha de Recolección|";    // modificar
  String cCveOrdenar  = "a.iOrden|iCveMuestra|dtRecoleccion|";  // modificar
  String cDscFiltrar  = "Orden|NIM|Fecha de Recolección|";    // modificar
  String cCveFiltrar  = "a.iOrden|iCveMuestra|dtRecoleccion|";  // modificar
  String cTipoFiltrar = "7|7|5|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

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
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Yes";
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

  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070303012P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato válido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070303012P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

  function SelectMot(Index){
     form = document.forms[0];
     for (i = 0; i < form.length; i++){
        str = form.elements[i].name;
        if (str.substring(0,12)=="iCveMotRecep"){
          if (form.elements[i].type.substring(0,6) == "select")
            form.elements[i].selectedIndex = Index;
        }
     }
  }

  function fValidaTodo(){
    var form = document.forms[0];
    if (form.hdBoton.value == 'Guardar') {
      if(!confirm("¿Está seguro de registrar el orden de recepción de las muestras indicadas?"))
        return false;
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
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     int iCveEnvio = 0;
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
       iCveEnvio = Integer.parseInt(request.getParameter("iCveEnvio").toString());
     }
     else{
       if(request.getParameter("iCveEnvio") != null)
         iCveEnvio =  Integer.parseInt(request.getParameter("iCveEnvio").toString());
     }

     cClave = request.getParameter("hdCampoClave");
     String propiedad = request.getParameter("hdCPropiedad");
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="<%=propiedad%>">
  <input type="hidden" name="iCveEnvio" Value="<%=iCveEnvio%>">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
  %>
  <tr><td>&nbsp;</td></tr>
  <tr><td valign="top">
    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
      <tr>
       <td colspan="6" class="ETablaT">Muestras del Envío Seleccionado</td>
      </tr>
      <%
      if (bs != null && clsConfig.getVEnvio() != null){
        TVTOXEnvio vEnvio = clsConfig.getVEnvio();
        // Desplegar información del Envio
        out.println("<tr><td class='ETablaT' colspan='4'>Informaci&oacute;n del Env&iacute;o</td></tr>");
        out.println("<tr>");
         out.println(vEti.Texto("EEtiqueta","A&ntilde;o:").append(vEti.TextoCS("ETabla",String.valueOf(vEnvio.getIAnio()),3).toString()));
        out.println("</tr>");
        out.println("<tr>");
         out.println(vEti.Texto("EEtiqueta","Unidad M&eacute;dica:").append(vEti.TextoCS("ETabla",vEnvio.getCDscUniMed(),3).toString()));
        out.println("</tr>");
        out.println("<tr>");
         out.println(vEti.Texto("EEtiqueta","Env&iacute;o:").append(vEti.TextoCS("ETabla",String.valueOf(vEnvio.getICveEnvio()),3).toString()));
        out.println("</tr>");
        // No se ha recibo ninguna muestra, por lo tanto se presenta la opción para ordenarlas por NIM
        if(vEnvio.getITotalRecibidas() == vEnvio.getITotalEnviadas()){
          out.println("<tr>");
           out.println(vEti.ToggleMov("ETablaR","lOrdNim","","",0,true,"","0",true));
           out.println(vEti.TextoCS("EEtiquetaL","Ordenar por NIM",3));
          out.println("</tr>");
        }
        if(vEnvio.getITotalRecibidas() > 0 ){
          out.println("<tr><td class='ETablaT' colspan='4'>Registrar Orden de las muestras</td></tr>");
          // Colocar celdas para el ordenamiento
          for(int i=1; i <= clsConfig.getINumCeldas() && i <= vEnvio.getITotalRecibidas(); i++){
           out.println("<tr>");
            out.println(vEti.EtiCampoCS("EEtiqueta",String.valueOf(vEnvio.getIMaxOrden()+i),"ETabla","text", 10,10, 3,"Orden_" + vEnvio.getIMaxOrden() + i,"",0,"","",true,true,true,request));
           out.println("</tr>");
          }
        }
        out.println("<tr><td class='ETablaT' colspan='4'>Ordenamiento Almacenado</td></tr>");
        out.println("<tr>");
        out.println(vEti.Texto("ETablaST","Orden"));
        out.println(vEti.Texto("ETablaST","NIM"));
        out.println(vEti.Texto("ETablaST","Proceso"));
        out.println(vEti.Texto("ETablaST","Recolecci&oacute;n"));
        out.println("</tr>");
        bs.start();
        while(bs.nextRow()){
           out.println("<tr>");
            out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iOrden", "0")));
            out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveMuestra", "&nbsp;")));
            out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscProceso", "&nbsp;")));
            out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cdtRecoleccion", "&nbsp;")));
           out.println("</tr>");
        }
      } // Existen datos
      else{
        out.println(vEti.TextoCS("EResalta","No existe información con el filtro proporcionado.",6));
     }
     %>
   </table>
  </td></tr>
  <%
    }else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
