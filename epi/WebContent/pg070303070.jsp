<% /**
 * Title: Catalogo de las Sustancias
 * Description: Catalogo de las Sustancias
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070306031CFG
 */%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>
<html>
<%  pg070303070CFG  clsConfig=new pg070303070CFG();                 // modificar

    TEntorno vEntorno=new TEntorno();
    vEntorno.setNumModulo(07);
    vEntorno.setOnLoad("fOnLoad();");
    vEntorno.setNombrePagina("pg070303070.jsp");                    // modificar
    vEntorno.setArchFuncs("FValida");
    vEntorno.setArchTooltips("07_Tooltips");
    vEntorno.setMetodoForm("POST");
    vEntorno.setActionForm("pg070303070.jsp\" target=\"FRMCuerpo"); // modificar
    vEntorno.setUrlLogo("Acerca");
    vEntorno.setBtnPrincVisible(true);
    vEntorno.setArchFCatalogo("FFiltro");
    vEntorno.setArchAyuda(vEntorno.getNombrePagina());

    String cCatalogo    = "pg070303071.jsp";           // modificar Ok
    String cOperador    = "1";                         // modificar ?
    String cDscOrdenar  = "Sustancia|";                // modificar Ok
    String cCveOrdenar  = "cDscSustancia|";            // modificar Ok
    String cDscFiltrar  = "Sustancia|";                // modificar Ok
    String cCveFiltrar  = "cDscSustancia|";            // modificar Ok
    String cTipoFiltrar = "8|";                        // modificar Ok
    boolean lFiltros    = true;                        // modificar Ok
    boolean lIra        = false;                       // modificar Ok
    String cEstatusIR   = "Imprimir";                  // modificar ?

    TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
    int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
    int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

    TError       vErrores     =new TError();
    StringBuffer sbErroresAcum = new StringBuffer();
    TEtiCampo    vEti          = new TEtiCampo();

    clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);
    TVUsuario vUsuario=(TVUsuario)request.getSession(true).getAttribute("UsrID");

    String cPaginas = clsConfig.getPaginas();
    String cDscPaginas = clsConfig.getDscPaginas();
    PageBeanScroller bs = clsConfig.getBeanScroller();
    String cUpdStatus  = clsConfig.getUpdStatus(); //"SaveCancelOnly";
    String cNavStatus  = clsConfig.getNavStatus();
    String cOtroStatus = clsConfig.getOtroStatus();
    String cCanWrite   = clsConfig.getCanWrite();
    String cSaveAction = "Guardar";
    String cDeleteAction = "BorrarB";
    String cClave     = "";
    String cPosicion  = "";
    int iCveEquipo = 0,
        iCveEquipoEsp = new Integer(vParametros.getPropEspecifica("TOXEquipoEsp")).intValue();
    int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
    TFechas dtFecha = new TFechas();
    int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
    if(bs!=null){
      cClave    =bs.getFieldValue("iCveSustancia", "").toString();
    }
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">
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
           window.parent.FRMOtroPanel.location="pg070303070P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070303070P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }
  function fIrCatalogo(cCampoClave, cPropiedad){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070303071.jsp';
    form.submit();
  }
  function fValidaTodo(){
    return true;
  }
  function fAccion(cAccion){
    form = document.forms[0];
    if(confirm("¿Está Seguro de Autorizar el Resultado del Lote Seleccionado?")){
      form.target="_self";
      form.hdBoton.value = cAccion;
      form.submit();
    }
  }

</script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdRowNum" value="<% if (bs!=null) out.print(bs.pageNo());%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr><td class="ETablaT" colspan="8">B&uacute;squeda del Análisis</td></tr>
        <tr>
          <td class="EEtiqueta">Año:</td>
          <td class="ETabla">
            <select name="iAnio" size="1" onChange="">
<%    for(int i=iAnio;i>=(iAnio-iAniosAtras);i--)
        if(request.getParameter("iAnio")!=null&&request.getParameter("iAnio").equalsIgnoreCase(Integer.toString(i)))
          out.print("<option value=\""+ i +"\" selected>"+ i +"</option>");
        else
          out.print("<option value=\""+ i +"\">"+ i +"</option>");
%>            </select></td>
          <td class="EEtiqueta">Laboratorio:</td>
          <td class="ETabla">
<%    int iCveProceso=Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
      out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,document.forms[0].iAnio.value,document.forms[0].iCveUniMed.value,'',document.forms[0].iCveLoteCualita);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0", true));
%>          </td>
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
        }else
          out.print("<option value=\"0\">Datos no disponibles...</option>");
      }
%>            </select>
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
          "   AND TOXExamenCualita.iCveLoteCualita="+request.getParameter("iCveLoteCualita") +
          "   AND TOXExamenCualita.lAutorizado = 0"; // Validación de solo autorizados.
        cOrden=" ORDER BY TOXExamenCualita.iCveExamCualita";
        vExamCualita=dExamenCualita.FindByAll(cFiltro,cOrden);
        if(vExamCualita.size()>0){
          out.print("<option value=\"0\">Seleccione...</option>");
          for(int i=0;i<vExamCualita.size();i++){
            vExamenCualita=(TVTOXExamenCualita)vExamCualita.get(i);
            if(request.getParameter("iCveExamCualita")!=null&&request.getParameter("iCveExamCualita").equalsIgnoreCase(Integer.toString(vExamenCualita.getICveExamCualita()))){
              out.print("<option value=\""+vExamenCualita.getICveExamCualita()+"\" selected>"+vExamenCualita.getICveExamCualita()+"</option>");
              // Asignar la clave del equipo que se está utilizando para el examen
              iCveEquipo = vExamenCualita.getICveEquipo();
            }
            else
              out.print("<option value=\""+vExamenCualita.getICveExamCualita()+"\">"+vExamenCualita.getICveExamCualita()+"</option>");
          }
        }else
          out.print("<option value=\"0\">Datos no disponibles...</option>");
      }
%>            </select>
          </td>
        </tr>
      </table>
    </td></tr>
    <tr><td valign="top">&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="10">Calibradores del Lote por Sustancia</td></tr>
      <%
      Vector vcCalibradores=clsConfig.FindCalibradores();
      boolean lEspecial = false;
      if(vcCalibradores!=null){
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
          TVTOXCualtSust dTOXCualtSust=(TVTOXCualtSust)eCalibradores.nextElement();
%>        <tr class="ETablaR">
          <td class="EEtiquetaL"><%=dTOXCualtSust.getcDscSustancia()%></td>
          <td><%=dTOXCualtSust.getDCorteNeg_co()%></td>
          <td><%=dTOXCualtSust.getDCorte_co()%></td>
          <td><%=dTOXCualtSust.getDCortePost_co()%></td>
          <td class="EEtiquetaL"><%=dTOXCualtSust.getCDscReactivo()%></td>
          <td><%=dTOXCualtSust.getDCorteNeg_r()%></td>
          <td><%=dTOXCualtSust.getDCortePost_r()%></td>
          <td><%=dTOXCualtSust.getDCorteNeg_ca()%></td>
          <td><%=dTOXCualtSust.getDCorte_ca()%></td>
          <td><%=dTOXCualtSust.getDCortePost_ca()%></td>
        </tr>
<%      }
      }else{
%>        <tr><td class="EResalta" align="center" colspan="10">Datos no disponibles.</td></tr>
<%    }
%>      </table>
    </td></tr>
    <tr><td valign="top">&nbsp;</td></tr>
<% if(bs!= null || !clsConfig.isLValidar() ) { %>
                            <tr align="center">
                              <td><%  out.print(vEti.clsAnclaTexto("EAncla","Autorizar","JavaScript:fAccion('Validar');", "Autorizar")); %>
                              </td>
                              </td>
                            </tr>

<% } %>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
<%    Vector vcSubstancias=clsConfig.getSubstancias();
      int iSize=vcSubstancias.size()+5;
%>      <tr class="ETablaT"><td colspan="<%=iSize%>">An&aacute;lisis de las Muestras</td></tr>
      <tr class="ETablaT">
        <td>An&aacute;lisis</td>
        <td>Control</td>
<%    for(Enumeration eSustancias=vcSubstancias.elements();eSustancias.hasMoreElements();){
%>        <td><%=((TVTOXCualtSust)eSustancias.nextElement()).getcDscSustancia()%></td>
<%    }
%>        <td>Resultado</td>
        <td>Rean&aacute;lisis</td>
        <td>Cercano <br>al Corte</td>
      </tr>
<%    if(bs!=null){
        bs.start();
        while(bs.nextRow()){
         if(bs.getFieldValue("cDscBreve","&nbsp;").toString().equalsIgnoreCase("&nbsp;"))
            out.println("<tr class=\"ETablaR\">");
         else
            out.println("<tr class=\"ETablaSTR\">");
         out.println(vEti.Texto("ETablaR", bs.getFieldValue("iCveAnalisis","&nbsp;").toString()));
         out.println(vEti.Texto("ETabla", bs.getFieldValue("cDscBreve","&nbsp;").toString()));
       boolean esPositivo=false;
          boolean ponEstilo=false;
          boolean esDudoso = false;
          int iSustPost = 0;
          int iSustDudo = 0;
          for(Enumeration eSustancias=vcSubstancias.elements();eSustancias.hasMoreElements();){
            String cEstilo = "";
            int iIndice=((TVTOXCualtSust)eSustancias.nextElement()).getICveSustancia();
            if("1".equals(bs.getFieldValue("lPositivoParaIdSustancia"+iIndice,"").toString())){
               esPositivo=true;
               iSustPost ++;
               cEstilo = " class=\"EPositivosR\"";
            }
           else{
              if("1".equals(bs.getFieldValue("lDudosoParaIdSustancia"+iIndice,"").toString())){
                esDudoso=true;
                iSustDudo ++;
                cEstilo = " class=\"EDudosoR\"";
              }
           }
%>        <td <%=cEstilo%>><%=bs.getFieldValue("dResultadoParaIdSustancia"+iIndice,"&nbsp;")%></td>
<%        }
          String cIndice=
            bs.getFieldValue("iCveAnalisis"   ,"0")+"|"+
            bs.getFieldValue("iCveLaboratorio","0")+"|"+
            bs.getFieldValue("iAnio"          ,"0")+"|"+
            bs.getFieldValue("iCveLoteCualita","0")+"|"+
            bs.getFieldValue("iCveExamCualita","0");
%>        <td class="<%=esPositivo?"EPositivos":"ETabla"%>"><%=esPositivo?"Positivo":"Negativo"%></td>
        <td class="ETablaC">
            <input type="hidden" name="lPositivo|<%=cIndice%>" value="<%=esPositivo?"1":"0"%>">
            <input type="hidden" name="iSustPost|<%=cIndice%>" value="<%=iSustPost%>">
            <input type="checkbox" name="cbReanalisis|<%=cIndice%>" value="1">
        </td>
        <td class="ETablaC">
            <% // Agregar toggle para el dudoso
               if(esDudoso){
                 out.println("<input type=\"checkbox\" name=\"cbDudoso|" + cIndice + "\" value=\"1\">");
                 out.println("<input type=\"hidden\" name=\"iSustDudo|" +  cIndice + "\" value=\"" + iSustDudo + "\">");
               }
               else
                 out.println("&nbsp;");

            %>
        </td>

      </tr>
<%      }
      }else{
%>      <tr class="EResalta" align="center"><td colspan="<%=iSize%>">Datos no disponibles.</td></tr>
<%    }
%>    </table>
    </td></tr>
<%  }else{
%><script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
