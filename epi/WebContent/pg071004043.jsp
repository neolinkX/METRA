<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author AG SA L.
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
  pg071004043CFG  clsConfig = new pg071004043CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg071004043.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071004043.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cCveOrdenar  = "iCvePuesto|cDscPuesto|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCvePuesto|cDscPuesto|";  // modificar
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
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg071004041.js)

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
       cClave  = ""+bs.getFieldValue("iCvePuesto", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdCampoClave3" value="">
  <input type="hidden" name="iNoLetras" value="">
  <input type="hidden" name="hdMdoTrans" value="<%out.print(request.getParameter("iCveMdoTrans"));%>">
  <input type="hidden" name="hdCategoria" value="<%out.print(request.getParameter("iCveCategoria"));%>">
  <input type="hidden" name="hdPuesto" value="<%out.print(request.getParameter("iCvePuesto"));%>">
  <input type="hidden" name="hdOrden" value="<%out.print(request.getParameter("iOrden"));%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
          <input type="hidden" name="hdBoton2" value="">
          <input type="hidden" name="hdBoton3" value="">
  </td><td valign="top">
                              <% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <td colspan="4" class="ETablaT">Puesto
                              </td>

            <tr>
              <td class="EEtiqueta">Modo de Transporte:</td>
              <td class="ETabla">
              <%

              TDGRLMdoTrans dGRLMdoTrans = new TDGRLMdoTrans();
              TVGRLMdoTrans vGRLMdoTrans = new TVGRLMdoTrans();
              Vector vcGRLMdoTrans = new Vector();

              TDGRLPuesto dGRLPuesto = new TDGRLPuesto();
              TVGRLPuesto vGRLPuesto = new TVGRLPuesto();
              Vector vcGRLPuesto;

              TDGRLCONVIGPUE dGRLCONVIGPUE = new TDGRLCONVIGPUE();
              TVGRLCONVIGPUE vGRLCONVIGPUE = new TVGRLCONVIGPUE();
              Vector vcGRLCONVIGPUE;


              if(request.getParameter("hdBoton").compareTo("Guardar")==0 || request.getParameter("hdBoton").compareTo("GuardarA")==0 || request.getParameter("hdBoton").compareTo("Cancelar")==0){
                out.print("<select name=\"iCveMdoTrans\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcGRLMdoTrans = dGRLMdoTrans.findByAll("");
                if (vcGRLMdoTrans.size() > 0){
                  for (int i = 0; i < vcGRLMdoTrans.size(); i++){
                    vGRLMdoTrans = (TVGRLMdoTrans)vcGRLMdoTrans.get(i);
                    if (request.getParameter("hdMdoTrans")!=null && request.getParameter("hdMdoTrans").compareToIgnoreCase(new Integer(vGRLMdoTrans.getICveMdoTrans()).toString())==0)
                      out.print("<option value = " + vGRLMdoTrans.getICveMdoTrans() + " selected>" + vGRLMdoTrans.getCDscMdoTrans() + "</option>");
                    else
                      out.print("<option value = " + vGRLMdoTrans.getICveMdoTrans() + ">" + vGRLMdoTrans.getCDscMdoTrans() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              else{
                if(request.getParameter("hdBoton").compareTo("Nuevo")==0 || request.getParameter("hdBoton").compareTo("Modificar")==0)
                  out.print("<select name=\"iCveMdoTrans\" disabled>");
                else
                  out.print("<select name=\"iCveMdoTrans\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcGRLMdoTrans = dGRLMdoTrans.findByAll("");
                if (vcGRLMdoTrans.size() > 0){
                  if(request.getParameter("iCveMdoTrans")==null || request.getParameter("iCveMdoTrans").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  else if(request.getParameter("iCveMdoTrans")!=null && Integer.parseInt(request.getParameter("iCveMdoTrans"))<1 )
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcGRLMdoTrans.size(); i++){
                    vGRLMdoTrans = (TVGRLMdoTrans)vcGRLMdoTrans.get(i);
                    if (request.getParameter("iCveMdoTrans")!=null && request.getParameter("iCveMdoTrans").compareToIgnoreCase(new Integer(vGRLMdoTrans.getICveMdoTrans()).toString())==0)
                      out.print("<option value = " + vGRLMdoTrans.getICveMdoTrans() + " selected>" + vGRLMdoTrans.getCDscMdoTrans() + "</option>");
                    else
                      out.print("<option value = " + vGRLMdoTrans.getICveMdoTrans() + ">" + vGRLMdoTrans.getCDscMdoTrans() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              out.print("</select>");
              out.print("</td>");

                out.print("<td class='EEtiqueta'>Categoría:</td>");
                out.print("<td class='ETabla'>");
                if(request.getParameter("hdBoton").compareTo("Modificar")==0 || request.getParameter("hdBoton").compareTo("Nuevo")==0){
                  out.println("<select value='" + request.getParameter("iCveCategoria") + "' name=\"iCveCategoria\" disabled>");
                }
                else
                  out.println("<select name=\"iCveCategoria\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                if(request.getParameter("iCveMdoTrans")!= null){
                  TDGRLCategoria dGRLCategoria = new TDGRLCategoria();
                  TVGRLCategoria vGRLCategoria = new TVGRLCategoria();
                  Vector vcGRLCategoria = new Vector();
                  vcGRLCategoria = dGRLCategoria.FindByAll(request.getParameter("iCveMdoTrans"));
                  if (vcGRLCategoria.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLCategoria.size(); i++){
                      vGRLCategoria = (TVGRLCategoria)vcGRLCategoria.get(i);
                      if (request.getParameter("iCveCategoria")!=null && request.getParameter("iCveCategoria").compareToIgnoreCase(new Integer(vGRLCategoria.getICveCategoria()).toString())==0 && Integer.parseInt(request.getParameter("iCveCategoria"))>0)
                        out.print("<option value = " + vGRLCategoria.getICveCategoria() + " selected>" + vGRLCategoria.getCDscCategoria() + "</option>");
                      else
                      out.print("<option value = " + vGRLCategoria.getICveCategoria() + ">" + vGRLCategoria.getCDscCategoria() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else if(request.getParameter("hdMdoTrans")!= null){
                  TDGRLCategoria dGRLCategoria = new TDGRLCategoria();
                  TVGRLCategoria vGRLCategoria = new TVGRLCategoria();
                  Vector vcGRLCategoria = new Vector();
                  vcGRLCategoria = dGRLCategoria.FindByAll(request.getParameter("hdMdoTrans"));
                  if (vcGRLCategoria.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLCategoria.size(); i++){
                      vGRLCategoria = (TVGRLCategoria)vcGRLCategoria.get(i);
                      if (request.getParameter("hdCategoria")!=null && request.getParameter("hdCategoria").compareToIgnoreCase(new Integer(vGRLCategoria.getICveCategoria()).toString())==0 && Integer.parseInt(request.getParameter("hdCategoria"))>0)
                        out.print("<option value = " + vGRLCategoria.getICveCategoria() + " selected>" + vGRLCategoria.getCDscCategoria() + "</option>");
                      else
                      out.print("<option value = " + vGRLCategoria.getICveCategoria() + ">" + vGRLCategoria.getCDscCategoria() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else if((request.getParameter("iCveMdoTrans")!=null && Integer.parseInt(request.getParameter("iCveMdoTrans").toString())<1) || request.getParameter("iCveMdoTrans")==null)
                  out.println("<option value='0' selected>Datos no disponibles</option>");
                out.print("</select></td>");
                out.println("</tr>");

////////=====================================================


                out.println("<tr>");
                out.print("<td class='EEtiqueta'>Puesto:</td>");
                out.print("<td class='ETabla'>");
                if(request.getParameter("hdBoton").compareTo("Modificar")==0 || request.getParameter("hdBoton").compareTo("Nuevo")==0){
                  out.println("<select value='" + request.getParameter("iCvePuesto") + "' name=\"iCvePuesto\" disabled>");
                }
                else
                  out.println("<select name=\"iCvePuesto\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton2.value='Primero';document.forms[0].submit();\">");

                /*
                out.print("<td class='ETabla' colspan='1'>");
                out.println("<select name=\"iCvePuesto\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton2.value='Primero';document.forms[0].submit();\">");
*/

                if(request.getParameter("iCveMdoTrans")!= null && request.getParameter("iCveCategoria")!= null){
                  dGRLPuesto = new TDGRLPuesto();
                  vGRLPuesto = new TVGRLPuesto();
                  vcGRLPuesto = new Vector();
                  vcGRLPuesto = dGRLPuesto.FindByAll(request.getParameter("iCveMdoTrans"), request.getParameter("iCveCategoria"));
                  if (vcGRLPuesto.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLPuesto.size(); i++){
                      vGRLPuesto = (TVGRLPuesto)vcGRLPuesto.get(i);
                      if (request.getParameter("iCvePuesto")!=null && request.getParameter("iCvePuesto").compareToIgnoreCase(new Integer(vGRLPuesto.getICvePuesto()).toString())==0 && Integer.parseInt(request.getParameter("iCvePuesto"))>0){
                        out.print("<option value = " + vGRLPuesto.getICvePuesto() + " selected>" + vGRLPuesto.getCDscPuesto() + "</option>");
                      }
                      else
                      out.print("<option value = " + vGRLPuesto.getICvePuesto() + ">" + vGRLPuesto.getCDscPuesto() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else {
                    if(request.getParameter("hdMdoTrans")!= null && request.getParameter("hdCategoria")!= null){
                          dGRLPuesto = new TDGRLPuesto();
                          vGRLPuesto = new TVGRLPuesto();
                          vcGRLPuesto = new Vector();
                          vcGRLPuesto = dGRLPuesto.FindByAll(request.getParameter("hdMdoTrans"), request.getParameter("hdCategoria"));
                          if (vcGRLPuesto.size() > 0){
                            out.print("<option value = 0>Seleccione...</option>");
                            for (int i = 0; i < vcGRLPuesto.size(); i++){
                              vGRLPuesto = (TVGRLPuesto)vcGRLPuesto.get(i);
                              if (request.getParameter("hdPuesto")!=null && request.getParameter("hdPuesto").compareToIgnoreCase(new Integer(vGRLPuesto.getICvePuesto()).toString())==0 && Integer.parseInt(request.getParameter("hdPuesto"))>0){
                                out.print("<option value = " + vGRLPuesto.getICvePuesto() + " selected>" + vGRLPuesto.getCDscPuesto() + "</option>");
                              }
                              else
                              out.print("<option value = " + vGRLPuesto.getICvePuesto() + ">" + vGRLPuesto.getCDscPuesto() + "</option>");
                            }
                          }
                          else{
                            out.print("<option value = 0>Datos no disponibles</option>");
                          }
                        }else
                          out.println("<option value='0' selected>Datos no disponibles</option>");
                }
                out.print("</select></td>");



                out.print("<td class='EEtiqueta'>Orden:</td>");
                out.print("<td class='ETabla'>");
                if(request.getParameter("hdBoton").compareTo("Modificar")==0 || request.getParameter("hdBoton").compareTo("Nuevo")==0){
                  out.println("<select value='" + request.getParameter("iOrden") + "' name=\"iOrden\" disabled>");
                }
                else
                  out.println("<select name=\"iOrden\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton3.value='Primero';document.forms[0].submit();\">");

               /* out.print("<td class='ETabla' colspan='1'>");
                out.println("<select name=\"iOrden\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton3.value='Primero';document.forms[0].submit();\">");
                */

                if(request.getParameter("iCveMdoTrans")!= null && request.getParameter("iCveCategoria")!= null && request.getParameter("iCvePuesto")!= null){
                  dGRLCONVIGPUE = new TDGRLCONVIGPUE();
                  vGRLCONVIGPUE = new TVGRLCONVIGPUE();
                  vcGRLCONVIGPUE = new Vector();
                  String Where = " where iCveMdoTrans = " +request.getParameter("iCveMdoTrans") +
                                 "  and     iCveCategoria = " +request.getParameter("iCveCategoria") +
                                 "  and     iCvePuesto = " +request.getParameter("iCvePuesto")+ " ";
                  String Ordeby = " order by iOrden ";
                  vcGRLCONVIGPUE = dGRLCONVIGPUE.FindByAll(Where, Ordeby);
                  if (vcGRLCONVIGPUE.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLCONVIGPUE.size(); i++){
                      vGRLCONVIGPUE = (TVGRLCONVIGPUE)vcGRLCONVIGPUE.get(i);
                      if (request.getParameter("iOrden")!=null && request.getParameter("iOrden").compareToIgnoreCase(new Integer(vGRLCONVIGPUE.getIOrden()).toString())==0 && Integer.parseInt(request.getParameter("iOrden"))>0){
                        out.print("<option value = " + vGRLCONVIGPUE.getIOrden() + " selected>" + vGRLCONVIGPUE.getIOrden() + "</option>");
                      }
                      else
                      out.print("<option value = " + vGRLCONVIGPUE.getIOrden() + ">" + vGRLCONVIGPUE.getIOrden() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else {
                        if(request.getParameter("hdMdoTrans")!= null && request.getParameter("hdCategoria")!= null && request.getParameter("hdPuesto")!= null){
                                  dGRLCONVIGPUE = new TDGRLCONVIGPUE();
                                  vGRLCONVIGPUE = new TVGRLCONVIGPUE();
                                  vcGRLCONVIGPUE = new Vector();
                                  String Where = " where iCveMdoTrans = " +request.getParameter("hdMdoTrans") +
                                                 "  and     iCveCategoria = " +request.getParameter("hdCategoria") +
                                                 "  and     iCvePuesto = " +request.getParameter("hdPuesto")+ " ";
                                  String Ordeby = " order by iOrden ";
                                  vcGRLCONVIGPUE = dGRLCONVIGPUE.FindByAll(Where, Ordeby);
                                  if (vcGRLCONVIGPUE.size() > 0){
                                    out.print("<option value = 0>Seleccione...</option>");
                                    for (int i = 0; i < vcGRLCONVIGPUE.size(); i++){
                                      vGRLCONVIGPUE = (TVGRLCONVIGPUE)vcGRLCONVIGPUE.get(i);
                                      if (request.getParameter("hdOrden")!=null && request.getParameter("hdOrden").compareToIgnoreCase(new Integer(vGRLCONVIGPUE.getIOrden()).toString())==0 && Integer.parseInt(request.getParameter("hdOrden"))>0){
                                        out.print("<option value = " + vGRLCONVIGPUE.getIOrden() + " selected>" + vGRLCONVIGPUE.getIOrden() + "</option>");
                                      }
                                      else
                                      out.print("<option value = " + vGRLCONVIGPUE.getIOrden() + ">" + vGRLCONVIGPUE.getIOrden() + "</option>");
                                    }
                                  }
                                  else{
                                    out.print("<option value = 0>Datos no disponibles</option>");
                                  }
                         }else
                                  out.println("<option value='0' selected>Datos no disponibles</option>");
                }
                out.print("</select></td>");


 ////////=====================================================




              String lActivo       = "";   //FCSReq2 -Se Agrego-

              out.print("</tr>");

                               if (lNuevo){
                                 out.println("<tr>");
                                 out.println(vEti.EtiCampo("EEtiqueta","Edad Mayor a:","ETabla\" colspan='3'","text",5,2,"iEdMayor","0",0,"fMayus(this);","",true,true,true,request));
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.println(vEti.EtiCampo("EEtiqueta","Edad Menor a:","ETabla\" colspan='3'","text",5,2,"iEdMenor","0",0,"fMayus(this);","",true,true,true,request));
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.println(vEti.EtiCampo("EEtiqueta","Vigencia:","ETabla\" colspan='3'","text",5,2,"iTmpVigencia","0",0,"fMayus(this);","",true,true,true,request));
                                 out.println("</tr>");

//-------------------------------------------------------------------------
//FCSReq2 -Se Agrego1-
                                 out.println("<tr colspan='3'>");
                                 out.println(vEti.EtiToggle("EEtiqueta","Activo:","ECampo","lActivo",lActivo,"",3,lNuevo||lCaptura,"","",true));
                                 out.println("</tr>");
//-------------------------------------------------------------------------
                               }
                               else{
                                 if (bs != null){

                                 lActivo = (String)bs.getFieldValue("lActivo"       ,""); //FCSReq2 -Se Agrego-

                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                     out.println("<tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","Edad Mayor a:","ETabla\" colspan='3'","text",5,3,"iEdMayor",bs.getFieldValue("iEdMayor", "0").toString(),0,"fMayus(this);","",true,true,true,request));
                                     out.println("</tr>");

                                     out.println("<tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","Edad Menor a:","ETabla\" colspan='3'","text",5,3,"iEdMenor",bs.getFieldValue("iEdMenor", "0").toString(),0,"fMayus(this);","",true,true,true,request));
                                     out.println("</tr>");

                                     out.println("<tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","Vigenia:","ETabla\" colspan='3'","text",5,3,"iTmpVigencia",bs.getFieldValue("iTmpVigencia", "0").toString(),0,"fMayus(this);","",true,true,true,request));
                                     out.println("</tr>");

//-------------------------------------------------------------------------
//FCSReq2 -Se Agrego2-
                                 out.println("<tr colspan='3'>");
                                 out.println(vEti.EtiToggle("EEtiqueta","Activo:","ECampo","lActivo",lActivo,"",3,lNuevo||lCaptura,"ACTIVO","INACTIVO",true));
                                 out.println("</tr>");
//-------------------------------------------------------------------------


                                   }
                                   else{
                                     out.println("<tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","Edad Mayor a:","ETabla\" colspan='3'","text",5,3,"iEdMayor",bs.getFieldValue("iEdMayor", "0").toString(),0,"fMayus(this);","",true,false,true,request));
                                     out.println("</tr>");

                                     out.println("<tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","Edad Menor a:","ETabla\" colspan='3'","text",5,3,"iEdMenor",bs.getFieldValue("iEdMenor", "0").toString(),0,"fMayus(this);","",true,false,true,request));
                                     out.println("</tr>");

                                     out.println("<tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","Vigencia:","ETabla\" colspan='3'","text",5,3,"iTmpVigencia",bs.getFieldValue("iTmpVigencia", "0").toString(),0,"fMayus(this);","",true,false,true,request));
                                     out.println("</tr>");

//-------------------------------------------------------------------------
//FCSReq2 -Se Agrego3-
                                 out.println("<tr colspan='3'>");
                                 out.println(vEti.EtiToggle("EEtiqueta","Activo:","ECampo","lActivo",lActivo,"",3,lNuevo||lCaptura,"ACTIVO","INACTIVO",true));
                                 out.println("</tr>");
//-------------------------------------------------------------------------

                                   }
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='4'>No existen datos coincidentes con el filtro proporcionado.</td>");
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
