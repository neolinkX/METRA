<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Jaime Enrique Suárez Romero
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
  pg070106030CFG  clsConfig = new pg070106030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070106030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070106030.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cSQL = "";
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

  TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
  TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
  Vector vcGRLUSUMedicos = new Vector();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  var wExp;

  function fShowReport(cPagina){
      frm = document.forms[0];
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
          wExp = open(cPagina,"Selector",'dependent=yes,hotKeys=no,location=no,menubar=yes,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=550,screenX=800,screenY=600');
          wExp.moveTo(50, 50);
          window.onclick=HandleFocus
          window.onfocus=HandleFocus
          fSetModal();
      }
  }

  function fSelText(oSel){
    for(i=0; i<oSel.length; i++){
      if(oSel[i].value == oSel.value){
        return oSel[i].text;
      }
    }
    return '';
  }

  function fBuscar(){
    frm = document.forms[0];

    cDscUniMed = '';
    cDscModulo = '';
    cMeses = '';
    cUsuario = '';
    iCveRama = -1;
    frm = document.forms[0];
    cDscModulo = fSelText(frm.iCveModulo);
    cDscUniMed = fSelText(frm.iCveUniMed);
    if(!frm.iCveRama.disabled)
       iCveRama = frm.iCveRama.value;
    if(confirm("El reporte tomará algunos minutos en crearse ¿Desea continuar?"))
        fShowReport('pg070106030R.jsp?hdTipo=1&iCveUniMed='+frm.iCveUniMed.value+'&iCveModulo='+frm.iCveModulo.value+'&cDscUniMed='+cDscUniMed+'&cDscModulo='+cDscModulo+'&dtFechaI='+frm.dtFechaI.value+'&dtFechaF='+frm.dtFechaF.value+'&iCveServicio='+frm.iCveServicio.value+'&iCveRama='+iCveRama);
  }

 function llenaSLT(opc,val1,val2,val3,val4,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070104061P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&val4=" + val4 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070104061P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }


function fValidacion(){
  var form = document.forms[0];
  var cVMsg = '';

    if(form.dtFechaI && form.dtFechaI){
     if (form.dtFechaI)
        cVMsg = cVMsg + fSinValor(form.dtFechaI,5,'Fec. De:', true);

      if (form.dtFechaF)
        cVMsg = cVMsg + fSinValor(form.dtFechaF,5,'Fec. Hasta:', true);

      if (form.dtFechaI){
         if (form.dtFechaI.value != null){
         Dia  = form.dtFechaI.value.substring(0,2);
         Mes  = form.dtFechaI.value.substring(3,5)-1;
         Anio = form.dtFechaI.value.substring(6,10);
         dtFecApi = new Date(Anio,Mes,Dia);

         Dia  = form.dtFechaF.value.substring(0,2);
         Mes  = form.dtFechaF.value.substring(3,5)-1;
         Anio = form.dtFechaF.value.substring(6,10);
         dtFecHoy = new Date(Anio,Mes,Dia);
          if (dtFecApi > dtFecHoy){
            cVMsg = cVMsg + "\n - La Fecha de inicial no puede ser mayor a la fecha final. \n";
           }
         }
      }
    }
    if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
    }
    return true;

}
  function fChg(){
    form = document.forms[0];
    form.target = 'FRMDatos';
    form.action = 'pg070106030.jsp';
    form.submit();
  }

  function fHab(){
    frm = document.forms[0];
    frm.iCveUsuario.disabled = !frm.TBXMedico.checked;
  }

  function fRecargar(){
    form = document.forms[0];
    form.target = "_self";
    form.submit();
  }

</SCRIPT>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdRowNum" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td align="center" width="100%">
  </td></tr>
  <tr><td valign="top">
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <%
                               int iUniMed, iModulo;
                               out.print("<tr>");
                               out.print("<td class='ETablaT' colspan='5'>Reporte de Atención a Usuarios por Servicio y Rama</td>");
                               out.print("</tr>");
                               out.println("<tr>");
                                out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                out.println("<td class='ECampo' >");
                                out.println(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(4,this.value,"+vUsuario.getICveusuario()+",'','',document.forms[0].iCveModulo);",dUMUsuario.getUniMedxUsu(vUsuario.getICveusuario()),"iCveUniMed", "cDscUniMed", request, "0", true));
                                out.println("</td>");
                                iUniMed = request.getParameter("iCveUniMed") == null ? 0 : Integer.valueOf(request.getParameter("iCveModulo").toString(),10).intValue() ;
                                iModulo = request.getParameter("iCveModulo") == null ? 0 : Integer.valueOf(request.getParameter("iCveModulo").toString(),10).intValue() ;
                                out.println(vEti.Texto("EEtiqueta","Módulo:"));
                                if (request.getParameter("iCveModulo") == null){
                                  out.println("<td class='ECampo' colspan='2'>");
                                  out.println("<SELECT NAME='iCveModulo' SIZE='1' onChange=\"llenaSLT(7,document.forms[0].iCveUniMed.options[document.forms[0].iCveUniMed.selectedIndex].value,"+ vUsuario.getICveusuario() + ",this.options[this.selectedIndex].value,'',document.forms[0].iCveServicio);\" ");
                                  out.println("</SELECT>");
                                  out.println("</td>");
                                }
                                else{
                                  TDGRLModulo dModulo = new TDGRLModulo();
                                  out.println("<td colspan='2'>");
                                  out.println(vEti.SelectOneRowSinTD("iCveModulo","llenaSLT(7,document.forms[0].iCveUniMed.options[document.forms[0].iCveUniMed.selectedIndex].value,"+ vUsuario.getICveusuario() + ",this.options[this.selectedIndex].value,'',document.forms[0].iCveServicio);"
                                                                     ,dModulo.FindByAll("where iCveUniMed =" + iUniMed), "iCveModulo", "cDscModulo", request, "0", true));
                                  out.println("</td>");
                                }
                              out.println("</tr>");

                              vcGRLUSUMedicos = dGRLUSUMedicos.findServXUsu(vUsuario.getICveusuario(), iUniMed,1, iModulo);
                              Vector vRama = new Vector();
                              if(request.getParameter("iCveServicio") != null){
                                 vRama = new TDGRLUSUMedicos().findRamas(vUsuario.getICveusuario(),Integer.parseInt(request.getParameter("iCveUniMed"),10), 1, Integer.parseInt(request.getParameter("iCveModulo"),10), Integer.parseInt(request.getParameter("iCveServicio"),10));
                              }
                              if(request.getParameter("iCveServicio")==null || request.getParameter("iCveServicio").compareTo("")==0) {
                                 vGRLUSUMedicos.setCDscServicio("Seleccione...");
                                 vGRLUSUMedicos.setICveServicio(0);
                                 vcGRLUSUMedicos.add(vGRLUSUMedicos);
                              }
                              else if(request.getParameter("iCveServicio")!=null && Integer.parseInt(request.getParameter("iCveServicio"))<1 ){
                                 vGRLUSUMedicos.setCDscServicio("Seleccione...");
                                 vGRLUSUMedicos.setICveServicio(0);
                                 vcGRLUSUMedicos.add(vGRLUSUMedicos);
                               }
                               out.println("<tr>");
                               out.print(vEti.Texto("EEtiqueta", "Servicio:"));
                               out.println("<td>");
                               out.println(vEti.SelectOneRowSinTD("iCveServicio", "llenaSLT(6,document.forms[0].iCveUniMed.options[document.forms[0].iCveUniMed.selectedIndex].value,"+ vUsuario.getICveusuario() + ",document.forms[0].iCveModulo.options[document.forms[0].iCveModulo.selectedIndex].value,this.options[this.selectedIndex].value,document.forms[0].iCveRama);"
                               , vcGRLUSUMedicos, "iCveServicio", "cDscServicio", request, "0"));
                               out.println("</td>");
                               out.print(vEti.Texto("EEtiqueta", "Rama:"));
                               out.println("<td colspan='2'>");
                               out.println(vEti.SelectOneRowSinTD("iCveRama","",
                                                             vRama,
                                                             "iCveRama","cDscRama",request,"0",true,clsConfig.getLVariosMed()==1?true:false));
                               out.println("</td>");
                               out.println("</tr><tr>");
                  TFechas dtFecha = new TFechas();
                  out.println(vEti.EtiCampo("EEtiqueta","De:", "ETabla","text",10,10,"dtFechaI",dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/"),0,"","",true,true, true, request));
                  out.println(vEti.EtiCampo("EEtiqueta","Hasta:", "ETabla","text",10,10,"dtFechaF",dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/"),0,"","",true,true, true, request));
           %></tr>
              <tr><td colspan="6" align="center">
                <%
                out.print(vEti.clsAnclaTexto("EAncla","Buscar","JavaScript:if(fValidacion())fBuscar();", "Buscar...",""));
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
