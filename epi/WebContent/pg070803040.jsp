<%/**
 * Title:       Consulta de M�ximos y m�nimos por almacen.
 * Description: Pantalla para solicitar informaci�n para ajustes
 * Copyright:   2004
 * Company:     Micros Personales
 * @author      Rafael Miranda Blumenkron
 * @version     1.0
 * Clase:       pg070803040.jsp
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.text.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<%
  pg070803040CFG  clsConfig = new pg070803040CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070803040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070803040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());
  String cCatalogo    = "";  // modificar
  String cOperador    = "1"; // modificar
  String cDscOrdenar  = "";  // modificar
  String cCveOrdenar  = "";  // modificar
  String cDscFiltrar  = "";  // modificar
  String cCveFiltrar  = "";  // modificar
  String cTipoFiltrar = "";  // modificar
  boolean lFiltros    = true;  // modificar
  boolean lIra        = false;  // modificar
  String cEstatusIR   = "Imprimir";  // modificar
  Vector vAlmacen     = new Vector(),
         vTpoArticulo = new Vector(),
         vFamilia     = new Vector(),
         vArticExist;
  String cvUnidadMed   = "",
         cvAlmacen     = "",
         cvTpoArticulo = "",
         cvFamilia     = "",
         cvExcede      = "",
         cvDebajo      = "",
         cvCerca       = "";
  TVALMArticulo VArticulo;
  TVALMArtxAlm  VArtxAlm;

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
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  boolean lCaptura = clsConfig.getCaptura();
  boolean lNuevo = clsConfig.getNuevo();

  TFechas dtFecha = new TFechas(vEntorno.getNumModuloStr());
  String cFiltro = "";

  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  String cUsuario = "";
  if (vUsuario!=null)
     cUsuario = vUsuario.getCNombre() + " " + vUsuario.getCApPaterno() + " " + vUsuario.getCApMaterno();

  String cCondicion = "";
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
</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
 %>
  <tr>
      <td valign="top">
          <input type="hidden" name="hdBoton" value="">
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="5">M�ximos y M�nimos por Art�culo</td>
            </tr>
             <tr>
              <td class="EEtiqueta">Unidad M�dica:</td>
              <td class="ECampo"><%
                int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("AlmacenProc"));
                if (vUsuario!=null)
                  out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(3,document.forms[0].iCveUniMed.value,'','',document.forms[0].SLSAlmacen,'','');",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
              %></td>
              <td class="EEtiqueta">Almac�n:</td>
               <td class="ECampo">
                 <select name="SLSAlmacen" size="1" onChange="llenaSLT(1,document.forms[0].SLSAlmacen.value,'','',document.forms[0].SLSTipoArticulo,'','');">
                 <% vAlmacen = clsConfig.vAlmacenes;
                    if (request.getParameter("SLSAlmacen") != null){
                      if (request.getParameter("SLSAlmacen") != "")
                        cvAlmacen = request.getParameter("SLSAlmacen");
                    }
                    if (cvAlmacen.compareToIgnoreCase("") == 0)
                      out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    else
                      out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    if (!vAlmacen.isEmpty()){
                      for(int i=0;i<vAlmacen.size();i++){
                        TVALMAlmacen VALMAlmacen = new TVALMAlmacen();
                        VALMAlmacen = (TVALMAlmacen) vAlmacen.get(i);
                        if (cvAlmacen.compareToIgnoreCase(new Integer(VALMAlmacen.getICveAlmacen()).toString()) == 0)
                          out.print("<option selected value=\"" + new Integer(VALMAlmacen.getICveAlmacen()).toString() + "\">"  + VALMAlmacen.getCDscAlmacen().toString() + "</option>");
                        else
                          out.print("<option value=\"" + new Integer(VALMAlmacen.getICveAlmacen()).toString() + "\">"  + VALMAlmacen.getCDscAlmacen().toString() + "</option>");
                      }
                    }
                 %></select>
               </td>
             <td class="ECampo" rowspan="4"><%
               out.println(new TCreaBoton().clsCreaBoton(vEntorno, 1, "Buscar", "bBuscar", vEntorno.getTipoImg(),
                              "Buscar los Art�culos","Buscar", vParametros));
             %></td>
             </tr>
             <tr>
               <td class="EEtiqueta">Tipo de Art�culo:</td>
               <td class="ETabla">
               <select name="SLSTipoArticulo" size="1" onChange="llenaSLT(2,document.forms[0].SLSAlmacen.value,document.forms[0].SLSTipoArticulo.value,'',document.forms[0].SLSFamilia,'','');">
               <% vTpoArticulo = clsConfig.vTpoArticulos;
                  if (request.getParameter("SLSTipoArticulo") != null){
                    if (request.getParameter("SLSTipoArticulo") != "")
                      cvTpoArticulo = request.getParameter("SLSTipoArticulo");
                  }
                  if (cvTpoArticulo.compareToIgnoreCase("") == 0)
                    out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                  else
                    out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                  if (!vTpoArticulo.isEmpty()){
                    for(int i=0;i<vTpoArticulo.size();i++){
                      TVALMTpoArticulo VALMTpoArticulo = new TVALMTpoArticulo();
                      VALMTpoArticulo = (TVALMTpoArticulo) vTpoArticulo.get(i);
                      if (cvTpoArticulo.compareToIgnoreCase(new Integer(VALMTpoArticulo.getICveTpoArticulo()).toString()) == 0)
                        out.print("<option selected value=\"" + new Integer(VALMTpoArticulo.getICveTpoArticulo()).toString() + "\">" + VALMTpoArticulo.getCDscBreve().toString() + "</option>");
                      else
                        out.print("<option value=\"" + new Integer(VALMTpoArticulo.getICveTpoArticulo()).toString() + "\">" + VALMTpoArticulo.getCDscBreve().toString() + "</option>");
                    }
                  }
               %></select></td>
               <td class="EEtiqueta">Familia:</td>
               <td class="ECampo" colspan="3">
                 <select name="SLSFamilia" size="1" >
                 <% if (request.getParameter("SLSFamilia") != null){
                      if (request.getParameter("SLSFamilia") != "")
                        cvFamilia = request.getParameter("SLSFamilia");
                    }
                    if (cvFamilia.compareToIgnoreCase("") == 0)
                      out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    else
                      out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    vFamilia = clsConfig.vFamilias;
                    if (vFamilia != null)
                      if (!vFamilia.isEmpty())
                        for(int i=0;i<vFamilia.size();i++){
                          TVALMFamilia VALMFamilia = (TVALMFamilia) vFamilia.get(i);
                          if (cvFamilia.compareToIgnoreCase(new Integer(VALMFamilia.getiCveFamilia()).toString()) == 0)
                            out.print("<option selected value=\"" + new Integer(VALMFamilia.getiCveFamilia()).toString() + "\">"  + VALMFamilia.getcDscBreve().toString() + "</option>");
                          else
                            out.print("<option value=\"" + new Integer(VALMFamilia.getiCveFamilia()).toString() + "\">"  + VALMFamilia.getcDscBreve().toString() + "</option>");
                        }
                 %></select></td>
             </tr>
             <tr>
               <td class="EEtiqueta">Excede del:</td>
               <td class="ECampo">
               <% if (request.getParameter("FILExcede") != null){
                    if (request.getParameter("FILExcede") != "")
                      cvExcede = request.getParameter("FILExcede");
                  }
                  out.print(vEti.CampoSinCelda("text",5,3,"FILExcede",cvExcede,0,"","fMayus(this);",false,true));
               %>
               &nbsp;% del m�ximo.</td>
               <td class="EEtiqueta">Debajo del:</td>
               <td class="ECampo">
               <% if (request.getParameter("FILDebajo") != null){
                    if (request.getParameter("FILDebajo") != "")
                      cvDebajo = request.getParameter("FILDebajo");
                  }
                  out.print(vEti.CampoSinCelda("text",5,3,"FILDebajo",cvDebajo,0,"","fMayus(this);",false,true));
               %>
               &nbsp;% del m�nimo.</td>
             </tr>
             <tr>
               <td class="EEtiqueta">Cercan�a a l�mites:</td>
               <td class="ECampo" colspan="3">
               <% if (request.getParameter("FILCerca") != null){
                    if (request.getParameter("FILCerca") != "")
                      cvCerca = request.getParameter("FILCerca");
                  }
                  out.print(vEti.CampoSinCelda("text",5,3,"FILCerca",cvCerca,0,"","fMayus(this);",false,true));
               %>
               &nbsp;%</td>
             </tr>
          </table>
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
            <tr>
              <td class="ETablaT" colspan="13">Datos de los Art�culos Encontrados</td>
            </tr>
            <tr>
              <td class="ETablaT">Unidad<br>M�dica</td>
              <td class="ETablaT">Almac�n</td>
              <td class="ETablaT">Tipo de<br>Art�culo</td>
              <td class="ETablaT">Familia</td>
              <td class="ETablaT">Id.</td>
              <td class="ETablaT">Clave</td>
              <td class="ETablaT">Descripci�n</td>
              <td class="ETablaT">Unidad de<br>Almacenaje</td>
              <td class="ETablaT">M�ximos y<br>M�nimos</td>
              <td class="ETablaT">M�nimo</td>
              <td class="ETablaT">M�ximo</td>
              <td class="ETablaT">Existencia</td>
              <td class="ETablaT">Estado</td>
            </tr><%
              if (bs != null)
                vArticExist = bs.getPageVector();
              else
                vArticExist = new Vector();
              String cTemp;
              DecimalFormat df = new DecimalFormat("#,##0");
              if (vArticExist != null && vArticExist.size() > 0){
                for (int i = 0; i < vArticExist.size(); i++){
                  VArticulo = (TVALMArticulo)((Vector)vArticExist.get(i)).get(0);
                  VArtxAlm  = (TVALMArtxAlm)((Vector)vArticExist.get(i)).get(1);
                  out.println("<tr>");
                  cTemp = VArticulo.getCDscUniMed();  // Unidad M�dica
                  cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                  out.println(vEti.Texto("ETabla",cTemp));
                  cTemp = VArticulo.getCDscAlmacen();  // Almac�n
                  cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                  out.println(vEti.Texto("ETabla",cTemp));
                  cTemp = VArticulo.getcDscTpoArticulo();  // Tipo de Art�culo
                  cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                  out.println(vEti.Texto("ETabla",cTemp));
                  cTemp = VArticulo.getcDscFamilia();  // Familia
                  cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                  out.println(vEti.Texto("ETabla",cTemp));
                  cTemp = VArticulo.getiCveArticulo() + "";  // Identificador de Articulo
                  cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                  out.println(vEti.Texto("ETablaR",cTemp));
                  cTemp = VArticulo.getcCveArticulo();  // Clave de Art�culo
                  cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                  out.println(vEti.Texto("ETabla",cTemp));
                  cTemp = VArticulo.getcDscBreve();  // Descripci�n de Art�culo
                  cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                  out.println(vEti.Texto("ETabla",cTemp));
                  cTemp = VArticulo.getcDscUnidad();  // Unidad de almacenaje
                  cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                  out.println(vEti.Texto("ETabla",cTemp));
                  cTemp = VArticulo.getlMaxMin()==1?"S�":"No"; // Maneja M�ximos y M�nimos
                  cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                  out.println(vEti.Texto("ETabla",cTemp));
                  if (VArticulo.getlMaxMin() == 1){
                    cTemp = df.format(VArtxAlm.getdMinimo());  // Cantidad del m�nimo
                    cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                    out.println(vEti.Texto("ETablaR",cTemp));
                    cTemp = df.format(VArtxAlm.getdMaximo());  // Cantidad del m�ximo
                    cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                    out.println(vEti.Texto("ETablaR",cTemp));
                  }
                  else{  // Saltar columnas de Minimo y Maximo
                    out.println("<td class=\"ETabla\">&nbsp;</td>");
                    out.println("<td class=\"ETabla\">&nbsp;</td>");
                  }
                  cTemp = df.format(VArtxAlm.getdExistencia());  // Existencias
                  cTemp = cTemp!=null?(cTemp.equals("")?"&nbsp;":cTemp):"&nbsp;";
                  out.println(vEti.Texto("ETablaR",cTemp));
                  if (VArticulo.getlMaxMin() == 1){
                    cTemp = "";
                    int iExist = (int)VArtxAlm.getdExistencia();
                    int iMin   = (int)VArtxAlm.getdMinimo();
                    int iMax   = (int)VArtxAlm.getdMaximo();
                    if (iExist >= iMax){
                      if (iExist == iMax)
                        cTemp = "M�ximo Alcanzado";
                      else{
                        cTemp = "Sobre el M�ximo";
                        // Que tan arriba del m�ximo
                        int ivExcede;
                        try{
                          ivExcede = Integer.parseInt(cvExcede, 10);
                        }catch(Exception ex){ ivExcede = 0; }
                        if (ivExcede > 0 && iMax > 0){
                          int iTemp = (int)(100-((iExist*100)/iMax));
                          if (iTemp >= ivExcede)
                            cTemp += ": " + iTemp + "%";
                        }
                      }
                    }
                    else if (iExist <= iMin){
                      if (iExist == iMin)
                        cTemp = "M�nimo Alcanzado";
                      else{
                        cTemp = "Debajo del M�nimo";
                        // Que tan abajo del m�nimo
                        int ivDebajo;
                        try{
                          ivDebajo = Integer.parseInt(cvDebajo, 10);
                        }catch(Exception ex){ ivDebajo = 0; }
                        if (ivDebajo > 0 && iMin > 0){
                          int iTemp = (int)(100-((iExist*100)/iMin));
                          if (iTemp >= ivDebajo)
                            cTemp += ": " + iTemp + "%";
                        }
                      }
                    }
                    else{
                      if (!cvCerca.equals("")){
                        int ivCerca;
                        try{
                          ivCerca = Integer.parseInt(cvCerca, 10);
                        }catch(Exception ex){ ivCerca = 0; }
                        if (ivCerca > 0){
                          // Calcular que tan cerca de l�mite inferior
                          if (((int)(((iExist/iMin)-1)*100)) <= ivCerca)
                            cTemp = "Cerca M�nimo";
                          else if (((int)(100-((iExist/iMax)*100))) <= ivCerca)
                            cTemp = "Cerca M�ximo";
                          else
                            cTemp = "Normal";
                        }
                        else  // No se requiere saber que tan cerca de limites esta.
                          cTemp = "Normal";
                      }
                      else  // No se requiere saber que tan cerca de l�mites est�.
                        cTemp = "Normal";
                    }
                    out.println("<td class=\"ETabla\">" + cTemp + "</td>");
                  }
                  else
                    out.println("<td class=\"ETabla\">- - - - -</td>");
                  out.println("</tr>");
                }
              }
            %>
          </table>
      </td>
  </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

