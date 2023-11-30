<%/**
 * Title: Envío del Laboratorio
 * Description: Personal Involucrado en el Accidente
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. González Paz
 * @version 1.0
 * Clase:pg070402014CFG
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070402014CFG  clsConfig = new pg070402014CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070402014.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402014.jsp\" target=\"FRMCuerpo"); // modificar
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


  String cOperador    = "3";                   // modificar
  String cDscOrdenar  = "No Disponible|";      // modificar
  String cCveOrdenar  = "0|";                  // modificar
  String cDscFiltrar  = "No Disponible|";      // modificar
  String cCveFiltrar  = "0|";                  // modificar
  String cTipoFiltrar = "8|";                  // modificar


  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  /*String cUpdStatus  = clsConfig.getUpdStatus();*/
  String cUpdStatus  = "SaveOnly";
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

//FCSReq6 -Función que hace el Llamado a la Ventana Tipo Pop-Ups del "Detalle del Personal"-
  function fDetalle(iCvePersonal){
    wPrueba = open("SEDetPer.jsp?hdiCvePersonal=" + iCvePersonal, "Selector1",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=550,height=575,screenX=800,screenY=600');
    wPrueba.moveTo(15, 15);
  }




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

  <input type="hidden" name="hdiAnio" value="<%=request.getParameter("hdiAnio")!=null?request.getParameter("hdiAnio"):"0"%>">
  <input type="hidden" name="hdiCveMdoTrans" value="<%=request.getParameter("hdiCveMdoTrans")!=null?request.getParameter("hdiCveMdoTrans"):"0"%>">
  <input type="hidden" name="hdiIdefMedPrev" value="<%=request.getParameter("hdiIdefMedPrev")!=null?request.getParameter("hdiIdefMedPrev"):"0"%>">

  <input type="hidden" name="iAnioSel" value="<%=request.getParameter("iAnioSel")!=null?request.getParameter("iAnioSel"):"0"%>">
  <input type="hidden" name="iCveMdoTransSel" value="<%=request.getParameter("iCveMdoTransSel")!=null?request.getParameter("iCveMdoTransSel"):"0"%>">
  <input type="hidden" name="iCveUniMedSel" value="<%=request.getParameter("iCveUniMedSel")!=null?request.getParameter("iCveUniMedSel"):"0"%>">

  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo")!=null?request.getParameter("iCveModulo"):"0"%>">

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
                              <td colspan="9" class="ETablaT">Datos Generales
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

                                     out.print(vEti.EtiCampo("EEtiqueta","ID DGPMPT:","ECampo","text",10,10,"identificador",request.getParameter("hdiIdefMedPrev"),0,"","fMayus(this);",false,true,false));
                                     out.println("</td>");
                                     out.println("</tr>");
                                     out.println("</table>");
                            // Tabla de Captura

                             if (bs != null){ // Modificar de acuerdo al catálogo específico
                            %>
                                <p>&nbsp;</p>

                              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                 <tr>
                                     <% //FCSReq6 -Se Incremento a 8 Columnas, ya que se agrego DETALLE- %>
                                  <td colspan="8" class="ETablaT">Personal Involucrado en el Accidente En Tabla</td>
                                 </tr>
                                 <tr>
                                 <td class="ETablaT">Cve. Personal</td>
                                 <td class="ETablaT">Personal</td>
                                 <td class="ETablaT">Puesto</td>
                                 <td class="ETablaT">Vigencia</td>
                                 <td class="ETablaT">Situación</td>
                                 <td class="ETablaT">Vehículo</td>
                                 <td class="ETablaT">Detalle Personal</td>
                                 </tr>
                             <% 
                                int iContador = 0;
                                Vector vcInvPersonal = new Vector();
                                TDINVPersonal dINVPersonal = new TDINVPersonal();
                                TVINVPersonal vINVPersonal = new TVINVPersonal();
                                vcInvPersonal = dINVPersonal.FindPersonal(request.getParameter("hdiAnio"),
                                                  request.getParameter("hdiCveMdoTrans"),
                                                  request.getParameter("hdiIdefMedPrev"));
                                if(vcInvPersonal.size() > 0){
                                  for (iContador = 0; iContador < vcInvPersonal.size(); iContador++ ){
                                   vINVPersonal = (TVINVPersonal) vcInvPersonal.get(iContador);
                                   out.println("<tr>");
                                   out.print(vEti.CeldaCampo("ECampo","text",10,10,"iCvePersonal", "" + vINVPersonal.getICvePersonal() ,0,"","fMayus(this);",false,true,false));


                                    //FCSReq6 -Se Agrego- %>
                                    <input type="hidden" name="iCvePersonal" value="<%=vINVPersonal.getICvePersonal()%>">
<%
                                   out.print(vEti.CeldaCampo("ECampo","text",10,10,"cDscPersonal", "" + vINVPersonal.getCNombreCompleto() ,0,"","fMayus(this);",false,true,false));
                                   out.print(vEti.CeldaCampo("ECampo","text",10,10,"iCvePuesto", "" + vINVPersonal.getCDscPuesto() ,0,"","fMayus(this);",false,true,false));
                                   out.print(vEti.CeldaCampo("ECampo","text",10,10,"dtVigencia", "" + vINVPersonal.getCDscDtVigencia() ,0,"","fMayus(this);",false,true,false));
                                   out.print(vEti.CeldaCampo("ECampo","text",10,10,"iCveSituacion", "" + vINVPersonal.getCDscSituacion() ,0,"","fMayus(this);",false,true,false));
                                   out.print(vEti.CeldaCampo("ECampo","text",10,10,"iCveVehiculo", "" + vINVPersonal.getCDscVehiculo() ,0,"","fMayus(this);",false,true,false));

                                    //FCSReq6 -Liga para llamar a "Detalle del Personal"-
                                     out.println("<td align='center'><a class='EEtiqueta' name='Detalle' href='javascript:fDetalle(form.iCvePersonal.value);'>Detalle</a></td>");

                                   out.println("</tr>");
                                  }
                               }
                             }
                               else{
                                 %>
                                <p>&nbsp;</p>

                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                 <tr>
                                  <td colspan="7" class="ETablaT">Personal Involucrado en el Accidente para Guardar</td>
                                 </tr>
                                 <tr>
                                 <td class="ETablaT">Cve. Personal</td>
                                 <td class="ETablaT">Puesto</td>
                                 <td class="ETablaT">Vigencia</td>
                                 <td class="ETablaT">Situación</td>
                                 <td class="ETablaT">Vehículo</td>
                                 </tr>
                              <%
                               if (request.getParameter("hdiAnio") != null){
                               TDINVRegistro dINVRegistro = new TDINVRegistro();
                               TVINVRegistro vINVRegistro = new TVINVRegistro();
                               TDGRLPuesto dPuesto = new TDGRLPuesto();
                               TDINVSituacion dSituacion = new TDINVSituacion();
                               TDINVRegVehic dRegVehic = new TDINVRegVehic();
                               TFechas oFecha = new TFechas();
                               String cHoy = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(),"/");

                               String cWhere = " iAnio = " + request.getParameter("hdiAnio") +
                                         " and iCveMdoTrans = " + request.getParameter("hdiCveMdoTrans") +
                                         " and iIDDGPMPT = " +  request.getParameter("hdiIdefMedPrev") ;
                               String cWhereVehi = " where INVRegVehic.iAnio = " + request.getParameter("hdiAnio") +
                                         " and INVRegVehic.iCveMdoTrans = " + request.getParameter("hdiCveMdoTrans") +
                                         " and INVRegVehic.iIDDGPMPT = " +  request.getParameter("hdiIdefMedPrev") ;
                               Vector vcInvRegistro = new Vector();
                               vcInvRegistro = dINVRegistro.FindByAll(cWhere,"");

                               if (vcInvRegistro.size() > 0){
                                   vINVRegistro =  (TVINVRegistro) vcInvRegistro.get(0);

                               int i;
                               for(i = 0;i < vINVRegistro.getIPerFedInvolucra(); i++){
                                  out.println("<tr>");
                                  out.println("<td align='center'><input type='text' name='txtPersonal" + i + "'" + i + " size=5 >");
                                  out.println("<input type='text' name='DscPersonal" + i + "'" + i + " size=35 >");
                                  out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar Persona","javascript:fPerObjeto('txtPersonal"+ i +"','DscPersonal" + i + "');","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                  out.println("</td>");
                                  out.println("<td class='ECampo'>");
                                  out.println(vEti.SelectOneRowSinTD("iCvePuesto" + i, "",dPuesto.FindByAll(request.getParameter("hdiCveMdoTrans"),""), "iCvePuesto", "cDscPuesto", request, "0", true));
                                  out.println("</td>");
                                  out.print(vEti.CeldaCampo("ECampo","text",10,10,"dtFecha" + i,cHoy,0,"","fMayus(this);",false,true,true,request));
                                  out.println("<td class='ECampo'>");
                                  out.println(vEti.SelectOneRowSinTD("iCveSituacion" + i, "",dSituacion.FindByAll(" lActivo = 1 "," order by cDscSituacion"), "iCveSituacion", "cDscSituacion", request, "0", true));
                                  out.println("</td>");
                                  out.println("<td class='ECampo'>");
                                  out.println(vEti.SelectOneRowSinTD("iCveVehiculo" + i, "",dRegVehic.FindByAll(cWhereVehi), "iCveVehiculo", "cMatricula", request, "0", true));
                                  out.println("</td>");
                                 %>
                                 <script language="JavaScript">
                                  var form = document.forms[0];
                                  var i;
                                  for(i=0;i < form.elements.length;i++){
                                     if (form.elements[i].name.substring(0,11) == "txtPersonal")
                                        form.elements[i].readOnly = true;
                                     if (form.elements[i].name.substring(0,11) == "DscPersonal")
                                        form.elements[i].readOnly = true;
                                  }
                                 </script>
                                 <%
                                 }
                                 }
                               }
                             }
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