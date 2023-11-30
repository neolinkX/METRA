<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.*" %>


<html>
<%
  pg070802050CFG  clsConfig = new pg070802050CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070802050.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802050.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802050.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción Breve|";    // modificar
  String cCveOrdenar  = "iCveArticulo|cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave|Descripción Breve|";    // modificar
  String cCveFiltrar  = "ALMArticulo.iCveArticulo|ALMArticulo.cDscBreve|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar

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
  String cUpdStatus  = clsConfig.getUpdStatus(); //"SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cEstatusIR   = "IR";            // modificar
//  String cCanWrite   = "yes";
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  String cAnio = "";
  String cvLoteConfirmativo = "";
  Vector vAlmacen     = new Vector();
  Vector vTpoArticulo = new Vector();
  Vector vFamilia     = new Vector();
  Vector vAlmxArt     = new Vector();
  Vector vInventarios = new Vector();
  Vector vDtlleInve   = new Vector();
  TVUsuario vUsuario     = new TVUsuario();
  String cvAlmacen     = "";
  String cvTpoArticulo = "";
  String cvFamilia     = "";
  String cvInventario  = "";
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas(vEntorno.getNumModuloStr());
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

</SCRIPT>
<head>
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
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
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdIni" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Búsqueda de los Artículos</td>
           </tr><tr>
             <td class="EEtiqueta">Almacén:</td>
             <td class="ECampo">
                <select name="SLSAlmacen" size="1" onChange="llenaSLT(1,document.forms[0].SLSAlmacen.value,'','',document.forms[0].SLSTipoArticulo,document.forms[0].SLSFamilia,document.forms[0].SLSInventario);">
                <%  vAlmacen = clsConfig.vAlmacenes;
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
               %>
                </select>
             </td>
             <td class="EEtiqueta">Tipo de Artículo:</td>
             <td class="ETabla">
                <select name="SLSTipoArticulo" size="1" onChange="llenaSLT(2,document.forms[0].SLSAlmacen.value,document.forms[0].SLSTipoArticulo.value,'',document.forms[0].SLSFamilia,'','');">
             <%     vTpoArticulo = clsConfig.vTpoArticulos;
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
             %>
                </select>
             </td>
             <td class="ECampo"><strong>Fecha: </strong><% out.println(dtFecha.getFechaDDMMMYYYY(dtFecha.TodaySQL(),"/")); %></td>
           </tr><tr>
             <td class="EEtiqueta">Familia:</td>
             <td class="ECampo">
                <select name="SLSFamilia" size="1">
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
                %>
                </select>
             </td>

             <td class="EEtiqueta">Inventario:</td>
             <td class="ECampo">
                <select name="SLSInventario" size="1">
                <% if (request.getParameter("SLSInventario") != null){
                      if (request.getParameter("SLSInventario") != "")
                          cvInventario = request.getParameter("SLSInventario");
                    }

                    if (cvInventario.compareToIgnoreCase("") == 0)
                       out.print("<option selected value=\"" + "0"  + "\">" + "Inventario Nuevo..." + "</option>");
                    else
                       out.print("<option value=\"" + "0"  + "\">" + "Inventario Nuevo..." + "</option>");


                    vInventarios = clsConfig.vInventarios;
                    if (vInventarios != null){
                      if (!vInventarios.isEmpty()){
                        for(int i=0;i<vInventarios.size();i++){
                          TVALMInventario VALMInventario = (TVALMInventario) vInventarios.get(i);
                          if (cvInventario.compareToIgnoreCase(new Integer(VALMInventario.getiCveInventario()).toString()) == 0)
                             out.print("<option selected value=\"" + VALMInventario.getiCveInventario() + "\">"  + VALMInventario.getiCveInventario() + "</option>");
                          else
                             out.print("<option value=\"" + VALMInventario.getiCveInventario() + "\">"  + VALMInventario.getiCveInventario() + "</option>");
                        }
                      }
                    }
                %>
                </select>
             </td>
             <td class="ECampo"><%=  new TCreaBoton().clsCreaBoton(vEntorno, 1, "Buscar",
                              "bBuscar", vEntorno.getTipoImg(),
                              "Buscar los Artículos","Buscar", vParametros) %></td>
           </tr>
         </table>
      </td>
      </tr>
      <tr>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                             <%
                                if (bs != null) {
                             %>
                            <tr>
                              <td colspan="6" class="ETablaT">Información del Inventario del Almacén</td>
                            </tr>
                             <% // modificar según listado
                               vAlmxArt = clsConfig.vAlmxArt;
                               vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                               Vector vcPersonal = new Vector();
                               int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("AlmacenProc"));
                               int iUniMed = 0;
                               if(request.getParameter("iCveUniMed") == null){
                                  vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                  if(vcPersonal.size() != 0){
                                      iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                  }
                               }else{
                                   iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
                               }
                               vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);


                               TFechas Fecha = new TFechas();
                               if (bs != null && cCanWrite.equalsIgnoreCase("yes")){
                                   out.print("<tr>");
                                   out.print("<td class=\"EEtiqueta\">Captura:</td>");
                                   out.print("<td class=\"ECampo\">");
                                   if (!vInventarios.isEmpty()){
                                     if (request.getParameter("dtCaptura") != null)
                                       out.print(vEti.CampoSinCelda("input",10,10,"dtCaptura",request.getParameter("dtCaptura") ,3,"","",true,true));
                                     else
                                       out.print(vEti.CampoSinCelda("input",10,10,"dtCaptura" ,"",3,"","",true,true));
                                   } else
                                       out.print(vEti.CampoSinCelda("input",10,10,"dtCaptura" ,"",3,"","",true,true));
                                   out.print("</td>");
                                   out.print("<td class=\"EEtiqueta\">Usuario:</td>");
                                   out.println("<td class=\"ECampo\" colspan=3>");
                                   out.print(vEti.SelectOneRowSinTD("SLSCaptura", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, vUsuario.getICveusuario()+""));
                                   out.println("</td>");
                                   out.print("</tr>");
                               }
                            %>
                            <tr>
                              <td colspan="6" class="ETablaT">Artículos del Almacén</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Clave</td>
                              <td class="ETablaT">Descripción Breve</td>
                              <td class="ETablaT">Lote</td>
                              <td class="ETablaT">Caducidad</td>
                              <td class="ETablaT">Existencia</td>
                              <% if (cCanWrite.equalsIgnoreCase("yes"))
                                  out.println("<td class=\"ETablaT\">Existencia Física</td>");
                              %>
                            </tr>
                             <%
                                } else {
                                  out.print("<tr>");
                                  out.print("<td class=\"ETablaT\" colspan=\"7\">Mensaje</td>");
                                  out.print("</tr>");
                                  out.print("<td class=\"ETabla\" colspan=\"7\">No existen datos coincidentes con el filtro proporcionado.</td>");
                                  out.print("</tr>");
                                }
                               DecimalFormat dfNumber = new DecimalFormat("##,###,##0.00");
                                // modificar según listado
                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                     boolean lPinto = false;
                                     boolean lPinto2 = false;
                                     vDtlleInve = clsConfig.vDtlleInve;
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveArticulo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscBreve", "&nbsp;").toString()));
                                     if (bs.getFieldValue("lLote", "&nbsp;").toString().compareToIgnoreCase("1") == 0){
                                         if (bs.getFieldValue("cLote", "&nbsp;").toString().compareToIgnoreCase("null") != 0){
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cLote", "&nbsp;").toString()));
                                           out.print(vEti.Texto("ETabla", dtFecha.getFechaDDMMYYYY(dtFecha.getSQLDatefromSQLString(bs.getFieldValue("dtCaducidad", "&nbsp;").toString()),"/")));
                                         }
                                         else{
                                           out.print(vEti.Texto("ETabla", "&nbsp;"));
                                           out.print(vEti.Texto("ETabla", "&nbsp;"));
                                         }
                                     }
                                     else{
                                         out.print(vEti.Texto("ETabla", "&nbsp;"));
                                         out.print(vEti.Texto("ETabla", "&nbsp;"));
                                     }
                                     if (bs.getFieldValue("lLote", "&nbsp;").toString().compareToIgnoreCase("1") == 0)
                                        out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(bs.getFieldValue("dUnidades", "&nbsp;").toString()))));
                                     else {
                                        if (!vAlmxArt.isEmpty()){
                                          for(int i=0;i<vAlmxArt.size();i++){
                                             TVALMArtxAlm VALMArtxAlm = new TVALMArtxAlm();
                                             VALMArtxAlm = (TVALMArtxAlm) vAlmxArt.get(i);
                                             if (bs.getFieldValue("iCveArticulo", "&nbsp;").toString().compareToIgnoreCase(new Integer(VALMArtxAlm.getiCveArticulo()).toString()) == 0){
                                                out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(VALMArtxAlm.getdExistencia()))));
                                                lPinto2 = true;
                                                break;
                                             }
                                          }
                                        }
                                        if (!lPinto2)
                                          out.print(vEti.Texto("ETablaR", ""));
                                     }
                                   if (cCanWrite.equalsIgnoreCase("yes")){
                                     out.print("<td class=\"ETabla\">");
                                     if (!vDtlleInve.isEmpty()){
                                       for(int i=0;i<vDtlleInve.size();i++){
                                         TVALMDtlleInve VALMDtlleInve = new TVALMDtlleInve();
                                         VALMDtlleInve = (TVALMDtlleInve) vDtlleInve.get(i);
                                         if (bs.getFieldValue("iCveArticulo", "&nbsp;").toString().compareToIgnoreCase(new Integer(VALMDtlleInve.getiCveArticulo()).toString()) == 0){
                                            if (bs.getFieldValue("lLote", "&nbsp;").toString().compareToIgnoreCase("1") == 0){
                                               if (bs.getFieldValue("iCveLote", "&nbsp;").toString().compareToIgnoreCase(new Integer(VALMDtlleInve.getiCveLote()).toString()) == 0){
                                                   out.print(vEti.CampoSinCelda("input",10,10,"FILFis-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString() +
                                                                       bs.getFieldValue("iCveLote", "&nbsp;").toString()
                                                                        , new Double(VALMDtlleInve.getdEFisica()).toString(),3,"","",true,true));
                                                   lPinto = true;
                                               }
                                             } else {
                                                   out.print(vEti.CampoSinCelda("input",10,10,"FILFis-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString() +
                                                                       bs.getFieldValue("iCveLote", "&nbsp;").toString()
                                                                        , new Double(VALMDtlleInve.getdEFisica()).toString(),3,"","",true,true));
                                                   lPinto = true;
                                            }
                                         }
                                       }
                                     }
                                    if (!lPinto){
                                       if (bs.getFieldValue("lLote", "&nbsp;").toString().compareToIgnoreCase("1") == 0){
                                           if (bs.getFieldValue("cLote", "&nbsp;").toString().compareToIgnoreCase("null") == 0)
                                              out.print("El artículo maneja lotes y no hay lotes registrados.");
                                           else
                                              out.print(vEti.CampoSinCelda("input",10,10,"FILFis-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString() +
                                                                  bs.getFieldValue("iCveLote", "&nbsp;").toString()
                                                                  , "",3,"","",true,true));
                                       }
                                       else
                                          out.print(vEti.CampoSinCelda("input",10,10,"FILFis-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString() +
                                                                    bs.getFieldValue("iCveLote", "&nbsp;").toString()
                                                                    , "",3,"","",true,true));
                                    }
                                   }
                                   }
                               }
                            %>
                          </table>
      </td></tr>
  <%out.println(clsConfig.getReporte());}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
