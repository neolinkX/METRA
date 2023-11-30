<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.TDTOXSustancia"%>
<%@ page import="gob.sct.medprev.dao.TDGRLUMUsuario"%>
<%@ page import="gob.sct.medprev.vo.TVTOXSustancia"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.micper.ingsw.TEtiCampo"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="com.micper.seguridad.vo.TVGRLUMUsuario"%>
<html>
<%
  pg070301311CFG  clsConfig = new pg070301311CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070301311.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070301311.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());
  String cClave    = "";
  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  TEtiCampo    vEti          = new TEtiCampo();
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Corte|";    // modificar
  String cCveOrdenar  = "iCveCorte|";  // modificar
  String cDscFiltrar  = "Corte|";    // modificar
  String cCveFiltrar  = "iCveCorte|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
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

  TDTOXSustancia dTOXSustancia = new TDTOXSustancia();
  TVTOXSustancia vTOXSustancia = new TVTOXSustancia();
  Vector VSustancia = new Vector();

  String cFiltro = " WHERE lActivo = 1 ";
  String cOrden  = " ORDER BY iCveSustancia ";

  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  TFechas tf = new TFechas();
  String fechaFormateada = "";
  boolean lCaptura = clsConfig.getCaptura();
  boolean lNuevo = clsConfig.getNuevo();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\pg070301311.js"></SCRIPT>-->
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>

<script language="JavaScript">
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
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
       cClave    = ""+bs.getFieldValue("iCveCorte", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">

  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
     <td valign="top">
                          <input type="hidden" name="hdBoton" value="">
                          <%if (!lCaptura){%>
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Sustancias</td>
                            </tr>
                            <tr>
                              <td colspan="8" class="EEtiqueta">
                              <%
                                  VSustancia = dTOXSustancia.FindByAll(cFiltro,cOrden);
                                  String iCveSustancia = "";
                                  if (request.getParameter("iCveSustancia")!=null && request.getParameter("iCveSustancia").compareTo("")!=0)
                                     iCveSustancia = request.getParameter("iCveSustancia");
                                  out.print(vEti.SelectOneRowSinTD("iCveSustancia","",VSustancia,"iCveSustancia","cDscSustancia",request,iCveSustancia));
                              %>
                              </td>
                            </tr>
                          </table>
                          <%}%>
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr>
                                 <td colspan="4" class="ETablaT">Configuración del Rango de Error para el Análisis Presuntivo.</td>
                              </tr>
                            <%
                                if (lNuevo){
                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Sustancias:</td>");
                                    out.println("<td>");
                                    VSustancia = dTOXSustancia.FindByAll(cFiltro,cOrden);
                                    out.print(vEti.SelectOneRowSinTD("iCveSustancia","",VSustancia,"iCveSustancia","cDscSustancia",request,"0"));
                                    out.println("</td>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Identificador:","ECampo","text",5,5,"iCveCorte","&nbsp;",0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Activo:</td>");
                                    out.println("<td colspan=\"3\">");
                                    out.println("<input type=\"checkbox\" name=\"lActivo\" value=\"1\">");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Inicio de Vigencia:","ECampo","text",10,10,"dtInicio","&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Fin de Vigencia:","ECampo","text",10,10,"dtFin","&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                       out.println("<td class=\"EEtiqueta\">Usuario que Autoriza:</td>");
                                       out.println("<td colspan=\"3\" class=\"ETabla\">");
                                       TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                       /*int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                       out.println(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,'',document.forms[0].iCveUniMed.value,'',document.forms[0].iCveUsuAutoriza);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                                       out.println("<SELECT NAME=\"iCveUsuAutoriza\" SIZE=\"1\" onChange=\"\">");
                                       out.println("</SELECT>");*/
                                       out.println(""+vUsuario.getCNombre() +" "+ vUsuario.getCApPaterno() +" "+ vUsuario.getCApMaterno()+"");
                                       out.print("<input type=\"hidden\" name=\"iCveUsuAutoriza\" value=\""+ vUsuario.getICveusuario() +"\">");
                                       out.println("</td>");
                                    out.println("</tr>");

                                    /*out.println("<tr>");
                                    out.println("<td colspan=\"1\" class=\"ETablaTL\">");
                                    out.println("<input TYPE=\"radio\" NAME=\"lCuantCual\" VALUE=\"1\" ONCLICK=\"Activar(this.value);\">CG/EM");
                                    out.println("<td>");
                                    out.println("</tr>"); */
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","L&iacute;mite de Corte:","ECampo","text",14,14,"dCorte1","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr><tr>");
                                    //out.print(vEti.EtiCampo("EEtiqueta","L&iacute;mite para determinar positiva la sustancia que unifica:","ECampo","text",14,14,"dCorteNeg1","",0,"","fMayus(this);",false,true,lCaptura));
                                    //out.println("</tr>");
                                    out.println("<tr>");
                                    //out.print(vEti.EtiCampo("EEtiqueta","Controles:","ECampo","text",14,14,"cControles","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Margen de Error:","ECampo","text",14,14,"dMargenError","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr><tr>");
                                    // Configuración para la validación de Cromatografía
                                    /*out.println(vEti.TextoCS("ETablaST","M&aacute;rgenes de Error para evaluaci&oacute;n de An&aacute;lisis Presuntivo",4));
                                    out.println("</tr><tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n del Calibrador (%):","ECampo","text",14,14, "dMargConcCal", "0",0,"","fMayus(this);",true,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Tiempo de Retenci&oacute;n (%):","ECampo","text",14,14, "dMargTmpRet", "0",0,"","fMayus(this);",true,true,lCaptura));
                                    out.println("</tr><tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Relaci&oacute;n de Iones (%):","ECampo","text",14,14,3, "dMargRelacion", "0",0,"","fMayus(this);",true,true,lCaptura));
                                    out.println("</tr><tr>");
                                    out.println("<td colspan=\"1\" class=\"ETablaTL\">");
                                    out.println("<input TYPE=\"radio\" NAME=\"lCuantCual\" VALUE=\"0\" ONCLICK=\"Activar(this.value);\">INMUNOENSAYO");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Límite de Corte:","ECampo","text",14,14,3,"dCorte2","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Negativo:","ECampo","text",14,14,"dCorteNeg2","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Positivo:","ECampo","text",14,14,"dCortePost","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");*/
                                }
                                else {
                                   if (bs!=null){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Sustancia:","ECampo","text",5,5, "iCveSustancia", bs.getFieldValue("cDscSustancia","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    if (lCaptura)
                                        out.print("<input type=\"hidden\" name=\"iCveSustancia\" value=\""+ bs.getFieldValue("iCveSustancia").toString() +"\">");
                                    out.print(vEti.EtiCampo("EEtiqueta","Identificador:","ECampo","text",5,5, "iCveCorte", bs.getFieldValue("iCveCorte","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    String cDisabled = "";
                                    String cChecked = "";
                                    if (!lCaptura || bs.getFieldValue("lActivo").toString().compareTo("1")==0 ){
                                       cDisabled = " disabled ";
                                       out.print("<input type='hidden' name='lActivoM' value='"+ bs.getFieldValue("lActivo").toString() +"'>");
                                    }
                                    if (bs.getFieldValue("lActivo")!=null&&bs.getFieldValue("lActivo").toString().compareTo("1")==0)
                                       cChecked  = " checked ";

                                    out.println("<td class=\"EEtiqueta\">Activo:</td>");
                                    out.println("<td colspan=\"3\">");
                                    out.println("<input type=\"checkbox\" name=\"lActivo\" value=\"1\" "+cDisabled+ " " + cChecked+" >");
                                    out.println("</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    //System.out.println("Antes de fechas");
                                    if (bs.getFieldValue("dtInicio")!=null&&bs.getFieldValue("dtInicio").toString().compareTo("")!=0)
                                       fechaFormateada = sdf.format(tf.getSQLDatefromSQLString(bs.getFieldValue("dtInicio","").toString()));
                                    out.print(vEti.EtiCampo("EEtiqueta","Inicio de Vigencia:","ECampo","text",10,10, "dtInicio", fechaFormateada,0,"","fMayus(this);",false,true,lCaptura));
                                    //System.out.println("Antes de fechas2");
                                    if (bs.getFieldValue("dtFin")!=null&&bs.getFieldValue("dtFin").toString().compareTo("")!=0)
                                       fechaFormateada = sdf.format(tf.getSQLDatefromSQLString(bs.getFieldValue("dtFin","").toString()));
                                    out.print(vEti.EtiCampo("EEtiqueta","Fin de Vigencia:","ECampo","text",10,10, "dtFin", fechaFormateada,0,"","fMayus(this);",false,true,lCaptura));
                                    //System.out.println("Antes de fechas3");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    if (!lCaptura)
                                       out.print(vEti.EtiCampoCS("EEtiqueta","Usuario que Autoriza:","ECampo","text",5,5,3,"cDscUsuAutoriza", bs.getFieldValue("cDscUsuAutoriza","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    else{
                                       out.println("<td class=\"EEtiqueta\">Usuario que Autoriza:</td>");
                                       out.println("<td colspan=\"3\" class=\"ETabla\">");

                                       TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                       int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                       out.println(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,'',document.forms[0].iCveUniMed.value,'',document.forms[0].iCveUsuAutoriza);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,bs.getFieldValue("iCveUsuAutoriza","0").toString(),true));
                                       TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
                                       TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();
                                       Vector vcUMUsuario = new Vector();
                                       Vector vcPersonal = new Vector();
                                       vcUMUsuario = dGRLUMUsuario.getUniMedxUsu(new Integer(bs.getFieldValue("iCveUsuAutoriza","0").toString()).intValue());
                                       int iUniMed = 0;
                                       int j = 0;
                                       if (vcUMUsuario.size()>0){
                                           //System.out.println("UM de Usuario: " + vcUMUsuario.size() );
                                           vGRLUMUsuario = (TVGRLUMUsuario)vcUMUsuario.get(0);
                                           iUniMed = vGRLUMUsuario.getICveUniMed();
                                       }
                                       vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
                                       //System.out.println("Proc : " + iCveProceso + " UM:" + iUniMed);
                                       out.println("<SELECT NAME=\"iCveUsuAutoriza\" SIZE=\"1\" onChange=\"\">");
                                       if (vcPersonal.size() > 0){
                                          for (int i = 0; i < vcPersonal.size(); i++){
                                             j = j + 1;
                                             vGRLUMUsuario = (TVGRLUMUsuario) vcPersonal.get(i);
                                             if (bs.getFieldValue("iCveUsuAutoriza")!=null&&bs.getFieldValue("iCveUsuAutoriza").toString().compareTo(""+vGRLUMUsuario.getICveUsuario())==0)
                                                out.println("<option value=\""+ vGRLUMUsuario.getICveUsuario() +"\" selected>" + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + "</option>");
                                             else
                                                out.println("<option value=\""+ vGRLUMUsuario.getICveUsuario() +"\">" + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + "</option>");
                                          }
                                       }
                                    }
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println("<td colspan=\"1\" class=\"ETablaTL\">");

                                    String cInmuno = "";
                                    String cCroma = "1";

                                    cDisabled = "";
                                    if (!lCaptura)
                                       cDisabled = " disabled ";

                                    if (bs.getFieldValue("lCuantCual")!=null&&bs.getFieldValue("lCuantCual").toString().compareTo("0")==0)
                                       cInmuno = "checked";
                                    else
                                       if (bs.getFieldValue("lCuantCual")!=null&&bs.getFieldValue("lCuantCual").toString().compareTo("1")==0)
                                          cCroma = "checked";

                                    //out.println("<input TYPE=\"radio\" NAME=\"lCuantCual\" VALUE=\"1\" "+cCroma + cDisabled + "  ONCLICK=\"ActivarMod(this.value);\">CG/EM");
                                    out.println("<td>");
                                    out.println("</tr>");
                                    out.println("<tr>");

                                   // System.out.println(bs.getFieldValue("dCorte",""));

                                    if (bs.getFieldValue("dCorte","").toString().compareTo("") != 0 && cCroma.compareTo("")!=0){
                                        //System.out.println("Condicion 1");
                                      out.print(vEti.EtiCampo("EEtiqueta","L&iacute;mite de Corte:","ECampo","text",14,14, "dCorte1", cCroma.compareTo("")!=0?bs.getFieldValue("dCorte","").toString():"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0 ,lCaptura));
                                    }
                                    else{
                                        //System.out.println("Condicion 2");
                                      if (lCaptura){
                                          //System.out.println("Condicion 3");
                                        out.print(vEti.EtiCampo("EEtiqueta","L&iacute;mite de Corte:","ECampo","text",14,14, "dCorte1", cCroma.compareTo("")!=0?bs.getFieldValue("dCorte","").toString():"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0 ,lCaptura));
                                      }
                                      else{
                                            //System.out.println("Condicion 4");
                                        out.print("<td class='EEtiqueta'>L&iacute;mite de Corte:</td>");
                                        out.print("<td class='ECampo'>&nbsp;</td>");
                                      }
                                    }
                                    out.println("</tr><tr>");
                                    /*
                                    if (bs.getFieldValue("dCorteNeg","").toString().compareTo("") != 0 && cCroma.compareTo("")!=0){
                                      out.print(vEti.EtiCampo("EEtiqueta","L&iacute;mite para determinar positiva la sustancia que unifica:","ECampo","text",14,14, "dCorteNeg1", cCroma.compareTo("")!=0?bs.getFieldValue("dCorteNeg","").toString():"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));


                                    }
                                    else{
                                      if (lCaptura){
                                        out.print(vEti.EtiCampo("EEtiqueta","L&íacute;mite para determinar positiva la sustancia que unifica:","ECampo","text",14,14, "dCorteNeg1", cCroma.compareTo("")!=0?bs.getFieldValue("dCorteNeg","").toString():"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));
                                      }
                                      else{
                                        out.print("<td class='EEtiqueta'>Negativo:</td>");
                                        out.print("<td class='ECampo'>&nbsp;</td>");
                                      }
                                    }
                                    */

                                    //out.print(vEti.EtiCampo("EEtiqueta","Límite de Corte:","ECampo","text",14,14, "dCorte1", cCroma.compareTo("")!=0?bs.getFieldValue("dCorte","").toString():"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0 ,lCaptura));
                                    //out.print(vEti.EtiCampo("EEtiqueta","Negativo:","ECampo","text",14,14, "dCorteNeg1", cCroma.compareTo("")!=0?bs.getFieldValue("dCorteNeg","").toString():"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    /*
                                    if (bs.getFieldValue("cControles","").toString().compareTo("") != 0 && cCroma.compareTo("")!=0){
                                      out.print(vEti.EtiCampo("EEtiqueta","Controles:","ECampo","text",14,14,"cControles", cCroma.compareTo("")!=0?bs.getFieldValue("cControles","").toString():"" ,0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));
                                    }
                                    else{
                                      if (lCaptura){
                                        out.print(vEti.EtiCampo("EEtiqueta","Controles:","ECampo","text",14,14,"cControles", cCroma.compareTo("")!=0?bs.getFieldValue("cControles","").toString():"" ,0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));
                                      }
                                      else{
                                        out.print("<td class='EEtiqueta'>Controles:</td>");
                                        out.print("<td class='ECampo'>&nbsp;</td>");
                                      }
                                    }
                                        */

                                    if (bs.getFieldValue("dMargenError","").toString().compareTo("") != 0 && cCroma.compareTo("")!=0){
                                      out.print(vEti.EtiCampo("EEtiqueta","Margen de Error:","ECampo","text",14,14, "dMargenError", cCroma.compareTo("")!=0?bs.getFieldValue("dMargenError","").toString():"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));
                                    }
                                    else{
                                      if (lCaptura){
                                        out.print(vEti.EtiCampo("EEtiqueta","Margen de Error:","ECampo","text",14,14, "dMargenError", cCroma.compareTo("")!=0?bs.getFieldValue("dMargenError","").toString():"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));
                                      }
                                      else{
                                        out.print("<td class='EEtiqueta'>Margen de Error:</td>");
                                        out.print("<td class='ECampo'>&nbsp;</td>");
                                      }
                                    }

                                    //out.print(vEti.EtiCampo("EEtiqueta","Controles:","ECampo","text",14,14,"cControles", cCroma.compareTo("")!=0?bs.getFieldValue("cControles","").toString():"" ,0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));
                                    //out.print(vEti.EtiCampo("EEtiqueta","Margen de Error:","ECampo","text",14,14, "dMargenError", cCroma.compareTo("")!=0?bs.getFieldValue("dMargenError","").toString():"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));
                                    out.println("</tr><tr>");

                                    /*
                                    // Configuración para la validación de Cromatografía
                                    out.println(vEti.TextoCS("ETablaST","M&aacute;rgenes de Error para evaluaci&oacute;n de An&aacute;lisis Presuntivo",4));
                                    out.println("</tr><tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n del Calibrador (%):","ECampo","text",14,14, "dMargConcCal", cCroma.compareTo("")!=0?bs.getFieldValue("dMargConcCal","0").toString():"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Tiempo de Retenci&oacute;n (%):","ECampo","text",14,14, "dMargTmpRet", cCroma.compareTo("")!=0?bs.getFieldValue("dMargTmpRet","0").toString():"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));
                                    out.println("</tr><tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Relaci&oacute;n de Iones (%):","ECampo","text",14,14,3, "dMargRelacion", cCroma.compareTo("")!=0?bs.getFieldValue("dMargRelacion","0").toString() :"",0,"","fMayus(this);",false,cCroma.compareTo("")!=0,lCaptura));
                                    out.println("</tr><tr>");
                                    out.println("<td colspan=\"1\" class=\"ETablaTL\">");
                                    out.println("<input TYPE=\"radio\" NAME=\"lCuantCual\" VALUE=\"0\" " + cInmuno + cDisabled + "  ONCLICK=\"ActivarMod(this.value);\">INMUNOENSAYO");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    if (bs.getFieldValue("dCorte","").toString().compareTo("") != 0 && cInmuno.compareTo("")!=0){
                                      out.print(vEti.EtiCampoCS("EEtiqueta","Límite de Corte:","ECampo","text",14,14,3, "dCorte2", cInmuno.compareTo("")!=0?bs.getFieldValue("dCorte","").toString():"",0,"","fMayus(this);",false,cInmuno.compareTo("")!=0,lCaptura));
                                    }
                                    else{
                                      if (lCaptura){
                                        out.print(vEti.EtiCampoCS("EEtiqueta","Límite de Corte:","ECampo","text",14,14,3, "dCorte2", cInmuno.compareTo("")!=0?bs.getFieldValue("dCorte","").toString():"",0,"","fMayus(this);",false,cInmuno.compareTo("")!=0,lCaptura));

                                      }
                                      else{
                                        out.print("<td class='EEtiqueta'>Límite de Corte:</td>");
                                        out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");
                                      }
                                    }

                                    out.println("</tr>");
                                    out.println("<tr>");

                                    if (bs.getFieldValue("dCorteNeg","").toString().compareTo("") != 0 && cInmuno.compareTo("")!=0){
                                      out.print(vEti.EtiCampo("EEtiqueta","Negativo:","ECampo","text",14,14, "dCorteNeg2", cInmuno.compareTo("")!=0?bs.getFieldValue("dCorteNeg","").toString():"",0,"","fMayus(this);",false,cInmuno.compareTo("")!=0,lCaptura));
                                    }
                                    else{
                                      if (lCaptura){
                                        out.print(vEti.EtiCampo("EEtiqueta","Negativo:","ECampo","text",14,14, "dCorteNeg2", cInmuno.compareTo("")!=0?bs.getFieldValue("dCorteNeg","").toString():"",0,"","fMayus(this);",false,cInmuno.compareTo("")!=0,lCaptura));
                                      }
                                      else{
                                        out.print("<td class='EEtiqueta'>Negativo:</td>");
                                        out.print("<td class='ECampo'>&nbsp;</td>");
                                      }
                                    }

                                     if (bs.getFieldValue("dCortePost","").toString().compareTo("") != 0 && cInmuno.compareTo("")!=0){
                                      out.print(vEti.EtiCampo("EEtiqueta","Positivo:","ECampo","text",14,14, "dCortePost", cInmuno.compareTo("")!=0?bs.getFieldValue("dCortePost","").toString():"",0,"","fMayus(this);",false,cInmuno.compareTo("")!=0,lCaptura));
                                    }
                                    else{
                                      if (lCaptura){
                                        out.print(vEti.EtiCampo("EEtiqueta","Positivo:","ECampo","text",14,14, "dCortePost", cInmuno.compareTo("")!=0?bs.getFieldValue("dCortePost","").toString():"",0,"","fMayus(this);",false,cInmuno.compareTo("")!=0,lCaptura));
                                      }
                                      else{
                                        out.print("<td class='EEtiqueta'>Positivo:</td>");
                                        out.print("<td class='ECampo'>&nbsp;</td>");
                                      }
                                    }
                                        */
                                    //out.print(vEti.EtiCampo("EEtiqueta","Negativo:","ECampo","text",14,14, "dCorteNeg2", cInmuno.compareTo("")!=0?bs.getFieldValue("dCorteNeg","").toString():"",0,"","fMayus(this);",false,cInmuno.compareTo("")!=0,lCaptura));
                                    //out.print(vEti.EtiCampo("EEtiqueta","Positivo:","ECampo","text",14,14, "dCortePost", cInmuno.compareTo("")!=0?bs.getFieldValue("dCortePost","").toString():"",0,"","fMayus(this);",false,cInmuno.compareTo("")!=0,lCaptura));
                                    out.println("</tr>");

                                   }
                                   else{
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 2, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                      out.println("</tr>");
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
