<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<html>
<%
  pg070801051CFG  clsConfig = new pg070801051CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070801051.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070801051.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveSeccion|cDscSeccion|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveSeccion|cDscSeccion|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070801051.js)

  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <%
    if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
      cPosicion = Integer.toString(bs.rowNo());
      cClave  = ""+bs.getFieldValue("iCveSeccion", "");
    }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdCampoClave3" value="">
  <input type="hidden" name="hdAlmacen" value="<%out.print(request.getParameter("iCveAlmacen"));%>">
  <input type="hidden" name="hdArea" value="<%out.print(request.getParameter("iCveArea"));%>">
  <input type="hidden" name="iNoLetras" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
  <% TEtiCampo vEti = new TEtiCampo();
    boolean lCaptura = clsConfig.getCaptura();
    boolean lNuevo = clsConfig.getNuevo();
  %>
   <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
   <td colspan="6" class="ETablaT">Sección
   </td>
   <%

// C O M B O S
        %>
            <tr>
            <td class="ETablaTR">Almacén:</td>
            <td class="ETabla" colspan='5'>
              <select name="iCveAlmacen" size="1" OnChange="<%out.print("llenaSLT(1,document.forms[0].iCveAlmacen.value,'','',document.forms[0].iCveArea); document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();");%>">
              <%
              TDALMAlmacen dALMAlmacen = new TDALMAlmacen();
              TVALMAlmacen vALMAlmacen = new TVALMAlmacen();
              Vector vcALMAlmacen = new Vector();
              vcALMAlmacen = dALMAlmacen.FindByAll(""," order by iCveAlmacen ");
              if (vcALMAlmacen.size() > 0){
                out.print("<option value = 0>Seleccione...</option>");
                for (int i = 0; i < vcALMAlmacen.size(); i++){
                  vALMAlmacen = (TVALMAlmacen)vcALMAlmacen.get(i);
                  if (request.getParameter("iCveAlmacen")!=null && request.getParameter("iCveAlmacen").compareToIgnoreCase(new Integer(vALMAlmacen.getICveAlmacen()).toString())==0)
                    out.print("<option value = " + vALMAlmacen.getICveAlmacen() + " selected>" + vALMAlmacen.getCDscAlmacen() + "</option>");
                  else
                    out.print("<option value = " + vALMAlmacen.getICveAlmacen() + ">" + vALMAlmacen.getCDscAlmacen() + "</option>");
                  }
              }
              else{
                out.print("<option value = 0>Datos no disponibles...</option>");
              }
              %>
              </select>
            </td>
            </tr>
            <tr>
              <td class="ETablaTR">Área:</td>
              <td class="ETabla" colspan='5'>
              <select name="iCveArea" size="1" OnChange="<%out.print("document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();");%>">
              <%
              if(request.getParameter("iCveAlmacen") != null){
                TDALMArea dALMArea = new TDALMArea();
                TVALMArea vALMArea = new TVALMArea();
                Vector vcALMArea = new Vector();
                vcALMArea = dALMArea.FindByAll(" where ALMARea.iCveAlmacen = " + request.getParameter("iCveAlmacen")," order by ALMArea.cDscBreve ");
                if (vcALMArea.size() > 0){
                  out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcALMArea.size(); i++){
                    vALMArea = (TVALMArea)vcALMArea.get(i);
                    if (request.getParameter("iCveArea")!=null && request.getParameter("iCveArea").compareToIgnoreCase(new Integer(vALMArea.getICveArea()).toString())==0)
                      out.print("<option value = " + vALMArea.getICveArea() + " selected>" + vALMArea.getCDscBreve() + "</option>");
                    else
                      out.print("<option value = " + vALMArea.getICveArea() + ">" + vALMArea.getCDscBreve() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              else
                out.println("<option value='0' selected>Datos no disponibles</option>");
              %>
              </select>
              </td>
              </tr>
              <%

    if (lNuevo){ // Modificar de acuerdo al catálogo específico
      out.println("<tr>");
      out.print(vEti.Texto("EEtiqueta", "Clave:"));
      out.println("<td>");
      out.print("<input type='text' size='10' disabled>");
      out.println("</td>");
      out.println("</tr>");
      out.println("<tr>");
      out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 100, 100, "cDscSeccion", "", 3, "", "fMayus(this);", true, true, lCaptura));
      out.println("</tr>");
      out.print("<tr>");
      out.print(vEti.Texto("EEtiqueta", "Observaciones:"));
      out.print("<td class='ETablaL'>");
      out.print("<textarea cols=\"100\" rows=\"5\" name=\"cObservacion\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
      out.print("</textarea>");
      out.print("</td>");
      out.print("</tr>");
      out.println("<tr>");
          out.print(vEti.Texto("EEtiqueta", "Situación:"));
          %>
          <td>
            <input type="checkbox" name="chklActivo" CHECKED>
          </td>
          <%
      out.println("</tr>");
    }
    else{
      if (bs != null){
        out.println("<tr>");
        out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 4, 4, "iCveSeccion", ""+bs.getFieldValue("iCveSeccion", "&nbsp;"), 3, "", "", true, true, false));
        out.println("</tr>");
        out.println("<tr>");
        out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 100, 100, "cDscSeccion", ""+bs.getFieldValue("cDscSeccion", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
        out.println("</tr>");
        if(request.getParameter("hdBoton").compareTo("Modificar")==0){
          out.print("<tr>");
          out.print(vEti.Texto("EEtiqueta", "Observaciones:"));
          out.print("<td class='ETablaL'>");
          out.print("<textarea cols=\"100\" rows=\"5\" name=\"cObservacion\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
          out.print(""+bs.getFieldValue("cObservacion", "&nbsp;"));
          out.print("</textarea>");
          out.print("</td>");
          out.print("</tr>");
        }
        else{
          out.print("<tr>");
          out.print(vEti.Texto("EEtiqueta", "Observaciones:"));
          out.print("<td class='ETablaL'>");
          out.print("<textarea cols=\"100\" rows=\"5\" name=\"cObservacion\" readOnly>");
          out.print(""+bs.getFieldValue("cObservacion", "&nbsp;"));
          out.print("</textarea>");
          out.print("</td>");
          out.print("</tr>");
        }
        if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
          if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
            out.print(vEti.EtiAreaTexto("EEtiqueta", "Situación:", "ECampo", 10, 10, "lActivo", "INACTIVO", 3, "", "", true, true, false));
          else
            out.print(vEti.EtiAreaTexto("EEtiqueta", "Situación:", "ECampo", 10, 10, "lActivo", "ACTIVO", 3, "", "", true, true, false));
        }
        else{
          out.print(vEti.Texto("EEtiqueta", "Situación:"));
          %>
          <td>
            <input type="checkbox" name="chklActivo"<%
              if(bs.getFieldValue("lActivo").toString().compareTo("0")!=0)
                out.print(" CHECKED");
            %>>
          </td>
          <%
          }
          out.println("</tr>");
      }
      else{
        out.print("<tr>");
        out.print("<td class='ECampo' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td>");
        out.print("</tr>");
      }
    }
  %>
  </table><% // Fin de Datos %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
