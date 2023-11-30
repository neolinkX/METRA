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
  pg070106010CFG  clsConfig = new pg070106010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070106010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070106010.jsp\" target=\"FRMCuerpo"); // modificar
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

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
    fTipo();
  }

  var wExp;

  function fShowReport(cPagina,iRep,cTit,cCols,cSQL,iTipo){
      frm = document.forms[0];
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
          wExp = open(cPagina+'?hdRep='+iRep+'&hdTit='+cTit+'&hdCols='+cCols+'&hdQuery='+cSQL+'&hdTipo='+iTipo+'&iCveUniMed='+frm.iCveUniMed.value+'&iCveModulo='+frm.iCveModulo.value,"Selector",'dependent=yes,hotKeys=no,location=no,menubar=yes,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=550,screenX=800,screenY=600');
          wExp.moveTo(50, 50);
          window.onclick=HandleFocus
          window.onfocus=HandleFocus
          fSetModal();
      }
  }

  function fBuscar(){
    frm = document.forms[0];

    cDscUniMed = '';
    cDscModulo = '';
    cMeses = '';
    for(i=0; i<frm.iCveUniMed.length; i++){
      if(frm.iCveUniMed[i].value == frm.iCveUniMed.value){
        cDscUniMed = frm.iCveUniMed[i].text;
        break;
      }
    }
    for(i=0; i<frm.iCveModulo.length; i++){
      if(frm.iCveModulo[i].value == frm.iCveModulo.value){
        cDscModulo = frm.iCveModulo[i].text;
        break;
      }
    }
    for(i=0; i<frm.iMeses.length; i++){
      if(frm.iMeses[i].value == frm.iMeses.value){
        cMeses = frm.iMeses[i].text;
        break;
      }
    }

    if(frm.iTipo.value == 1){
      if(confirm("El reporte tomará algunos minutos en crearse ¿Desea continuar?"))
        fShowReport('pg070106010R.jsp',1,cDscUniMed + ' -- Módulo:  ' + cDscModulo + ' --  Encuesta de Salida del Año: ' + frm.iAnio.value,'',frm.iAnio.value,1);
    }
    if(frm.iTipo.value == 2){
      if(confirm("El reporte tomará algunos minutos en crearse ¿Desea continuar?"))
        fShowReport('pg070106010R.jsp',2,cDscUniMed + ' -- Módulo:  ' + cDscModulo + ' --  Encuesta de Salida del mes: ' + cMeses,'',frm.iMeses.value,1);
    }
    if(frm.iTipo.value == 3){
      if(fValFecha(frm.dtFecha.value)){
        fShowReport('pg070106010R.jsp',3,cDscUniMed + ' -- Módulo:  ' + cDscModulo + ' --  Encuesta de Salida del día: ' + frm.dtFecha.value,'',frm.dtFecha.value,1);
      }
      else
        alert('La fecha contiene un dato no válido!');
    }
  }

  function fChg(){
    form = document.forms[0];
    form.target = 'FRMDatos';
    form.action = 'pg070106010.jsp';
    form.submit();
  }

  function fTipo(){
    frm = document.forms[0];
    frm.iAnio.disabled = false;
    frm.iMeses.disabled = false;
    frm.dtFecha.disabled = false;

    if(frm.iTipo.value == 1){
      frm.iMeses.disabled = true;
      frm.dtFecha.disabled = true;
    }
    if(frm.iTipo.value == 2){
      frm.dtFecha.disabled = true;
    }
    if(frm.iTipo.value == 3){
      frm.iAnio.disabled = true;
      frm.iMeses.disabled = true;
    }
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
              <tr>
                <td colspan="6" class="ETablaT">Encuesta de Salida
                </td>
              </tr>
              <tr>
                <%
                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                Vector vcGRLUniMed = vUsuario.getVUniFiltro(Integer.parseInt(vParametros.getPropEspecifica("EPIProceso")));
                out.print(vEti.Texto("EEtiqueta","Unidad Médica"));
                out.print(vEti.SelectOneRow("ECampo","iCveUniMed","fChg();",vcGRLUniMed,"iCveUniMed","cDscUniMed",request,""));
                  String cUniMed = "";
                  try{
                    if(request.getParameter("iCveUniMed")==null)
                      cUniMed = ""+((TVGRLUMUsuario)vcGRLUniMed.get(0)).getICveUniMed();
                    else
                      cUniMed = ""+request.getParameter("iCveUniMed");
                  }catch(Exception e){
                    cUniMed = "";
                  }
                %>
              </tr>
              <tr>
                <%
                  out.print(vEti.Texto("EEtiqueta", "Módulo:"));
                  out.print(vEti.SelectOneRow("ECampo","iCveModulo","",(Vector) AppCacheManager.getColl("GRLModulo",cUniMed+"|"),"iCveModulo","cDscModulo",request,""));
                %>
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
                out.print(vEti.Texto("EEtiqueta","Año:"));
                out.print(vEti.SelectOneRow("ECampo","iAnio","",vcAnios,request,""+iAnio));
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
                out.print(vEti.SelectOneRow("ECampo","iMeses","",vcMeses,request,""));
                %>
              </tr>
              <tr>
                <%out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtFecha","",0,"","",true,true,true,request));%>
              </tr>
              <tr>
                <%
                Vector vcTipo = new Vector();
                vcTipo.add("1|Por Año");
                vcTipo.add("2|Por Año y Mes");
                vcTipo.add("3|Por Fecha Exacta");
                out.print(vEti.Texto("EEtiqueta","Tipo de Consulta"));
                out.print(vEti.SelectOneRow("ECampo","iTipo","fTipo();",vcTipo,request,""));
                %>
              </tr>
              <tr><td colspan="6" align="center">
                <%
                out.print(vEti.clsAnclaTexto("EAncla","Buscar","JavaScript:fBuscar();", "Buscar...",""));
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
