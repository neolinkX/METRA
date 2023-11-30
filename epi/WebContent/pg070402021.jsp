<%/**
 * Title: Investigación de Accidentes
 * Description: Equipo de Investigación
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. González Paz
 * @version 1.0
 * Clase:pg070402021CFG
 */%>


<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<html>
<%
  pg070402021CFG  clsConfig = new pg070402021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070402021.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402021.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());


  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No Disponible|";    // modificar
  String cCveOrdenar  = "No Disponible|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "No Disponible|";  // modificar
  String cTipoFiltrar = "8|";                // modificar

  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus = "";
  if (clsConfig.getUpdStatus().compareTo("AddOnly") == 0)
     cUpdStatus  = "SaveOnly";
  else
     cUpdStatus  = clsConfig.getUpdStatus();

  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
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
       //cClave2  = "" + bs.getFieldValue("iCveEnvio", "");
       //cPropiedad = "" + bs.getFieldValue(" ", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">



  <input type="hidden" name="hdLCondicion" value="<%=request.getParameter("hdLCondicion")%>">
  <input type="hidden" name="hdLOrdenar" value="<%=request.getParameter("hdLOrdenar")%>">
  <input type="hidden" name="iAnioSel" value="<%=request.getParameter("iAnioSel")%>">
  <input type="hidden" name="iCveMdoTransSel" value="<%=request.getParameter("iCveMdoTransSel")%>">
  <input type="hidden" name="iCveUniMedSel" value="<%=request.getParameter("iCveUniMedSel")%>">


  <input type="hidden" name="hdiAnio" value="<%=request.getParameter("hdiAnio")%>">
  <input type="hidden" name="hdiCveMdoTrans" value="<%=request.getParameter("hdiCveMdoTrans")%>">
  <input type="hidden" name="hdiIdefMedPrev" value="<%=request.getParameter("hdiIdefMedPrev")%>">
  <input type="hidden" name="hdiCveUniMed" value="<%=request.getParameter("hdiCveUniMed")%>">
  <input type="hidden" name="hdcDscUniMed" value="<%=request.getParameter("hdcDscUniMed")%>">




  <!--input type="hidden" name="hdOPPbaRapida" value="<%=cPropiedad%>" -->
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr><%
                                   TCreaBoton bBtn = new TCreaBoton();
                                   TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                                   TFechas dtFechas = new TFechas();
                                   String cBoton = "";
                                   int iPAnio = 0;
                                   int iPUniMed = 1;
                                   int iEnvio = 0;

                                    TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                    out.println("<input type='hidden' name='hdUsuario' value='"+ vUsuario.getICveusuario()+ "'>");

                                   int iProcINV = Integer.parseInt(vParametros.getPropEspecifica("INVProceso"));
                                   out.println("<input type='hidden' name='hdINVProceso' value='"+ iProcINV +"'>");
                                   int iProcTOX = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                   out.println("<input type='hidden' name='hdTOXProceso' value='"+ iProcTOX +"'>");
                                   int iProcEPI = Integer.parseInt(vParametros.getPropEspecifica("EPIProceso"));
                                   out.println("<input type='hidden' name='hdEPIProceso' value='"+ iProcEPI +"'>");
                                   int iProcFallecido = Integer.parseInt(vParametros.getPropEspecifica("INVFallecido"));
                                   out.println("<input type='hidden' name='hdFallecido' value='"+ iProcFallecido +"'>");
                               %>
                              <td colspan="9" class="ETablaT">Equipo de Investigación
                              </td>
                              </tr>
                              <tr>
                              </tr>
                            <%
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",10,10,"iAnio",request.getParameter("hdiAnio"),0,"","fMayus(this);",false,true,false));
                                     TDGRLMdoTrans dMdoTrans = new TDGRLMdoTrans();
                                     TVGRLMdoTrans vMdoTrans;
                                     Vector vcMdoTrans = new Vector();
                                     vcMdoTrans = dMdoTrans.findByAll("where iCveMdoTrans = " + request.getParameter("hdiCveMdoTrans") + " ");
                                     if (vcMdoTrans.size() > 0){
                                        vMdoTrans = (TVGRLMdoTrans) vcMdoTrans.get(0);
                                        out.print(vEti.EtiCampo("EEtiqueta","Modo de Transporte:","ECampo","text",10,10,"iCveMdoTrans",vMdoTrans.getCDscMdoTrans(),0,"","fMayus(this);",false,true,false));
                                     }
                                     out.println("</td>");
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampoCS("EEtiqueta","Identificador de Medicina Preventiva:","ECampo","text",10,10,3,"identificador",request.getParameter("hdiIdefMedPrev"),0,"","fMayus(this);",false,true,false));
                                     out.println("</tr>");

                                     TDGRLUniMed dUniMed = new TDGRLUniMed();
                                     out.println("<tr>");
                                     out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                     out.println(vEti.TextoCS("ECampo",request.getParameter("hdcDscUniMed"),3));
                                     //out.println(vEti.SelectOneRowSinTD("iCveUniMed","",dUniMed.FindByAll(),"iCveUniMed", "cDscUniMed", request, "0", true));
                                     out.println("<tr>");
                                     out.println("<td colspan='3' align='center' class='ETablaT'>Médico / Investigador</td>");
                                     out.println("<td align='center' class='ETablaT'>Asignar</td>");
                                     out.println("</tr>");


                            // Tabla de Captura
                            if (bs != null){

                                if (request.getParameter("hdiCveUniMed") != null){
                                Vector vcMedico  = null;
                                String cWhere = "";
                                String cWhereMedico = "";
                                TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                TDINVEquipoInv dEquipoInv = new TDINVEquipoInv();

                                cWhere = " Where GRLUMUsuario.iCveUniMed = " + request.getParameter("hdiCveUniMed") +
                                         " and GRLUMUsuario.iCveProceso = " +  vParametros.getPropEspecifica("INVProceso") + " ";

                                cWhereMedico = " where iAnio = " + request.getParameter("hdiAnio") +
                                               " and iCveMdoTrans = " + request.getParameter("hdiCveMdoTrans") +
                                               " and iIDDGPMPT = " + request.getParameter("hdiIdefMedPrev") + " ";


                                vcMedico = dUMUsuario.FindByAll(cWhere);

                                Object obElemento;
                                int i;
                                int iActivo = 0;
                                int iNoActivos = 0;

                                for (i = 0;i < vcMedico.size();i++){
                                   String cWhereMedico1 = "";
                                   obElemento =  vcMedico.get(i);
                                   TVGRLUMUsuario vUMUsuario = (TVGRLUMUsuario) obElemento;
                                   out.println("<tr>");
                                   out.println(vEti.TextoCS("ECampo",vUMUsuario.getCNombre() + " " + vUMUsuario.getCApPaterno() + " " + vUMUsuario.getCApMaterno(),3));
                                   out.println("<td align='center'>");
                                   cWhereMedico1 = cWhereMedico + " and iCveUsuInv = " + vUMUsuario.getICveUsuario() + " ";
                                   iActivo = dEquipoInv.FindMedico(cWhereMedico1);

                                   if (iActivo != 0){
                                      out.println("<input type='checkbox' name='"+ vUMUsuario.getICveUsuario() + "' value='ON' disabled = 'true' checked >");
                                      iNoActivos++;
                                   }
                                   else{
                                      out.println("<input type='checkbox' name='"+ vUMUsuario.getICveUsuario() + "' value='ON' disabled = 'true'>");
                                   }
                                   out.println("</td>");
                                   out.println("</tr>");
                                }
                                }
                            }
                            else{

                                //Registrar los Médicos por única vez

                                if (request.getParameter("hdiCveUniMed") != null){
                                Vector vcMedico  = null;
                                String cWhere = "";
                                 TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                TDINVEquipoInv dEquipoInv = new TDINVEquipoInv();

                                cWhere = " Where GRLUMUsuario.iCveUniMed = " + request.getParameter("hdiCveUniMed") +
                                         " and GRLUMUsuario.iCveProceso = " +  vParametros.getPropEspecifica("INVProceso") + " ";
                                vcMedico = dUMUsuario.FindByAll(cWhere);

                                Object obElemento;
                                int i;
                                int iActivo = 0;
                                int iNoActivos = 0;

                                for (i = 0;i < vcMedico.size();i++){
                                   obElemento =  vcMedico.get(i);
                                   TVGRLUMUsuario vUMUsuario = (TVGRLUMUsuario) obElemento;
                                   out.println("<tr>");
                                   out.println(vEti.TextoCS("ECampo",vUMUsuario.getCNombre() + " " + vUMUsuario.getCApPaterno() + " " + vUMUsuario.getCApMaterno(),3));
                                   out.println("<td align='center'>");
                                   out.println("<input type='checkbox' name='"+ vUMUsuario.getICveUsuario() + "' value='ON'>");
                                   out.println("</td>");
                                   out.println("</tr>");
                                }

                                }

                            }
                             /*
                             if (bs != null){
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta","Unidad Médica.:","ECampo","text",10,10,"iCveUniMed", bs.getFieldValue("cDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                 out.print(vEti.EtiCampo("EEtiqueta","Fecha de Asignación:","ECampo","text",10,10,"dtAsigna", bs.getFieldValue("cDscDtAsigna","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                 out.println("</tr>");
                               }
                               else{

                                 out.print(vEti.EtiCampo("EEtiqueta","Fecha de Asignación:","ECampo","text",10,10,"dtAsigna", "",0,"","fMayus(this);",false,true,true));
                                 out.println("</tr>");
                             }
                             */
                            %>
                           <tr>
                           </tr>
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