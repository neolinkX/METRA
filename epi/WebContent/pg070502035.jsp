<% /**
 * Title:         Personal de la Plantilla
 * Description:   Catálogo de la Informaión del Personal de la Plantilla
 * Copyright:     2004
 * Company:       Micros Personales
 * @author        Marco Antonio Hernández García
 * @version       1.0
 * Clase:         pg070502035
 */%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.Vector"%>

<html>
<%
  pg070502035CFG  clsConfig = new pg070502035CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070502035.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502035.jsp\" target=\"FRMCuerpo"); // modificar
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

  String cOperador    = "0";                   // modificar

  String cDscOrdenar  = "Número|RFC|CURP|Nombre|Apellido Paterno|Apellido Materno|";
  String cCveOrdenar  = "CTRPersonal.iNumPersonal|CTRPersonal.cRFC|CTRPersonal.cCURP|CTRPersonal.cNombre|CTRPersonal.cApPaterno|CTRPersonal.cApMaterno|";
  String cDscFiltrar  = "Número|RFC|CURP|Nombre|Apellido Paterno|Apellido Materno|";
  String cCveFiltrar  = "CTRPersonal.iNumPersonal|CTRPersonal.cRFC|CTRPersonal.cCURP|CTRPersonal.cNombre|CTRPersonal.cApPaterno|CTRPersonal.cApMaterno|";

  String cTipoFiltrar = "7|8|8|8|8|8|";              // modificar
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
  //String cUpdStatus  = "Add"; //clsConfig.getUpdStatus();
  String cUpdStatus  = clsConfig.getUpdStatus();

  //if (request.getParameter("hdBoton")!=null && request.getParameter("hdBoton").toString().compareTo("Nuevo")==0 )
  //     cUpdStatus = "SaveCancelOnly";

  if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
    cPosicion = Integer.toString(bs.rowNo());
    cClave2  = ""+bs.getFieldValue("iNumPersonal", "");
    if (bs.getFieldValue("dtRecepcion")!=null && bs.getFieldValue("dtRecepcion").toString().compareTo("null")!=0 &&
        bs.getFieldValue("dtRecepcion").toString().compareTo("")!=0){
        cUpdStatus = "";
    }
  }

  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();

  String cFiltro = "";
  int iPlantilla = 0;
  String cDscEmpresa = "";
  TDCTRPlantilla dCTRPlantilla  = new TDCTRPlantilla();
  TVCTRPlantilla vCTRPlantilla  = new TVCTRPlantilla();
  Vector vPlantilla = new Vector();
  Vector vLicencias = new Vector();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">
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
  <input type="hidden" name="SLSUniMed" value="<%if (request.getParameter("SLSUniMed") != null) out.print(request.getParameter("SLSUniMed"));%>">
  <input type="hidden" name="SLSMdoTransporte" value="<%if (request.getParameter("SLSMdoTransporte") != null) out.print(request.getParameter("SLSMdoTransporte"));%>">
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="hdIni" value="<% /*EMPRESA*/ if (request.getParameter("hdIni") != null) out.print(request.getParameter("hdIni")); else out.print("1");%>">
  <input type="hidden" name="cDscEmpresa" value="<% if (request.getParameter("cDscEmpresa") != null && request.getParameter("cDscEmpresa").toString().compareTo("null") != 0) out.print(request.getParameter("cDscEmpresa")); else out.print(cDscEmpresa);%>">
  <input type="hidden" name="hdIni2" value="<% /*PLANTILLA*/ if (request.getParameter("hdIni2") != null) out.print(request.getParameter("hdIni2")); else out.print("1");%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
        cFiltro =  " WHERE CTRPlantilla.iCveEmpresa = " + request.getParameter("hdIni") +
                   "   AND CTRPlantilla.iCvePlantilla = " + request.getParameter("hdIni2");
        vPlantilla = (Vector)dCTRPlantilla.FindByAll(cFiltro,"");
        if (vPlantilla.size()>0){
           vCTRPlantilla = (TVCTRPlantilla)vPlantilla.get(0);
           iPlantilla = vCTRPlantilla.getiCvePlantilla();
           cDscEmpresa = vCTRPlantilla.getCDscEmpresa();
        }
  %>
  <tr>
    <td valign="top">
       <input type="hidden" name="hdBoton" value="">
        &nbsp;
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
            <td class="ETablaT" colspan="2">Datos del Transportista</td>
          </tr>
            <tr>
              <td class="EEtiqueta">Plantilla</td> <td class="ETabla"><%=iPlantilla%></td>
            </tr>
            <tr>
              <td class="EEtiqueta">Nombre o <br> Razón Social</td> <td class="ETabla"><%=cDscEmpresa%></td>
            </tr>
        </table>
        &nbsp;
       <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
            <td class="ETablaT" colspan="7">Datos del Personal</td>
          </tr>

              <%
                  TEtiCampo vEti = new TEtiCampo();
                  boolean lCaptura = clsConfig.getCaptura();
                  boolean lNuevo = clsConfig.getNuevo();
                  if (lNuevo){
                      TDPais dPais = new TDPais();
                      TVPais vPais = new TVPais();
                      Vector VecPais = new Vector();
                      VecPais = dPais.FindByAll();

                      TDEntidadFed dEntidadFed = new TDEntidadFed();
                      TVEntidadFed vEntidadFed = new TVEntidadFed();
                      Vector VecEntidadFed = new Vector();

                      TDMunicipio dMunicipio = new TDMunicipio();
                      TVMunicipio vMunicipio = new TVMunicipio();
                      Vector VecMunicipio = new Vector();

                      String cWhere = "";


                      out.println("<tr>");
                      out.println("<td class=\"EEtiqueta\">No. Personal:</td>");
                      out.println("<td class=\"ECampo\">");
                      out.println("<input type=\"text\" size=\"5\" maxlength=\"5\" name=\"iNumPersonal\" value=\"0\">");
                      out.println("</td>");
                      out.println("<td class=\"EEtiqueta\">Expediente:</td>");
                      out.println("<td class=\"ECampo\">");
                      out.println("<input type=\"text\" size=\"5\" maxlength=\"5\" name=\"iCveExpediente\" value=\"\">");
                      out.println("<a class=\"EEtiqueta\" name=\"Buscar\" href=\"javascript:fSelPer();\">Buscar Persona</a>");
                      out.println("</td>");
                      out.println("</tr>");

                      out.println("<tr>");
                      out.print(vEti.EtiCampo("EEtiqueta","Nombre:","ECampo","text",30,30,"cNombre","",0,"","fMayus(this);",false,true,lCaptura));
                      out.print(vEti.EtiCampo("EEtiqueta","Paterno:","ECampo","text",25,25,"cApPaterno","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");
                      out.println("<tr>");
                      out.print(vEti.EtiCampo("EEtiqueta","Materno:","ECampo","text",25,25,"cApMaterno","",0,"","fMayus(this);",false,true,lCaptura));
                      out.print(vEti.EtiCampo("EEtiqueta","FECNAC (Fecha de Nacimiento):","ECampo","text",10,10,"dtNacimiento","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");
                      out.println("<tr>");
                      out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",13,13,"cRFC","",0,"","fMayus(this);",false,true,lCaptura));
                      out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",18,18,"cCURP","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");
                      out.println("<tr>");
                      out.println("<td class=\"EEtiqueta\">Pais de Nacimiento:</td>");
                      out.println("<td>");
                      if (VecPais.size() > 0){
                         out.println("<select name=\"iCvePaisNac\" size=\"1\" onchange=\"llenaSLT(3,this.value,'','',document.forms[0].iCveEstadoNac);\">");
                         out.println("<option value=\"0\">Seleccione...</option>");
                         for (int i = 0; i < VecPais.size(); i++){
                            vPais = (TVPais)VecPais.get(i);
                            out.println("<option value=\""+vPais.getICvePais()+"\">"+vPais.getCNombre()+"</option>");
                         }
                      }else{
                         out.println("<select name=\"iCvePaisNac\" size=\"1\">");
                         out.println("<option value=\"0\">Datos no disponibles</option>");
                      }
                      out.println("</select>");
                      out.println("</td>");

                      out.println("<td class=\"EEtiqueta\">Estado de Nacimiento:</td>");
                      out.println("<td>");
                      out.println("<select name=\"iCveEstadoNac\" size=\"1\">");
                      out.println("</select>");
                      out.println("</td>");
                      out.println("</tr>");
                      out.println("<tr>");
                      TDGRLMdoTrans dMdoTrans = new TDGRLMdoTrans();
                      out.println("<td class=\"EEtiqueta\">Modo de Transporte:</td>");
                      out.println("<td>");
                      out.print(vEti.SelectOneRowSinTD("iCveMdoTrans","llenaSLT(2,this.value,'','',document.forms[0].iCvePuesto);",dMdoTrans.findByAll(""),"iCveMdoTrans","cDscMdoTrans",request,"0",true));
                      out.println("</td>");
                      out.println("<td class=\"EEtiqueta\">Puesto:</td>");
                      out.println("<td>");
                      out.println("<select name=\"iCvePuesto\" size=\"1\">");
                      out.println("</select>");
                      out.println("</td>");
                      out.println("</tr>");
                      out.println("<tr>");
                      //out.print(vEti.EtiCampoCS("EEtiqueta","Licencia:","ECampo","text",20,20,3,"cLicencia","",0,"","fMayus(this);",false,true,lCaptura));
                      out.print(vEti.EtiCampo("EEtiqueta","Licencia:","ECampo","text",20,20,"cLicencia","",0,"","fMayus(this);",false,true,lCaptura));
                      out.print(vEti.EtiCampo("EEtiqueta","Fecha de Vencimiento:","ECampo","text",10,10,"dtLicVencimiento","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");
                      out.println("<tr>");
                      out.print(vEti.EtiCampo("EEtiqueta","Calle:","ECampo","text",50,50,"cCalle","",0,"","fMayus(this);",false,true,lCaptura));
                      out.print(vEti.EtiCampo("EEtiqueta","No. Exterior:","ECampo","text",30,30,"cNumExt","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");
                      out.println("<tr>");
                      out.print(vEti.EtiCampo("EEtiqueta","No. Interior:","ECampo","text",30,30,"cNumInt","",0,"","fMayus(this);",false,true,lCaptura));
                      out.print(vEti.EtiCampo("EEtiqueta","CP:","ECampo","text",5,5,"iCP","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");
                      out.println("<tr>");
                      out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",30,30,"cColonia","",0,"","fMayus(this);",false,true,lCaptura));
                      out.print(vEti.EtiCampo("EEtiqueta","Ciudad:","ECampo","text",50,50,"cCiudad","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");
                      out.println("<tr>");
                      out.println("<td class=\"EEtiqueta\">Pais:</td>");
                      out.println("<td>");
                      if (VecPais.size() > 0){
                         out.println("<select name=\"iCvePais\" size=\"1\" onchange=\"llenaSLT(3,this.value,'','',document.forms[0].iCveEstado);\">");
                         out.println("<option value=\"0\">Seleccione...</option>");
                         for (int i = 0; i < VecPais.size(); i++){
                            vPais = (TVPais)VecPais.get(i);
                            out.println("<option value=\""+vPais.getICvePais()+"\">"+vPais.getCNombre()+"</option>");
                         }
                      }else{
                         out.println("<select name=\"iCvePais\" size=\"1\">");
                         out.println("<option value=\"0\">Datos no disponibles</option>");
                      }
                      out.println("</select>");
                      out.println("</td>");

                      out.println("<td class=\"EEtiqueta\">EDO (Estado):</td>");
                      out.println("<td>");
                      out.println("<select name=\"iCveEstado\" size=\"1\" onchange=\"llenaSLT(4,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio);\">");
                      out.println("</select>");
                      out.println("</td>");
                      out.println("</tr>");
                      out.println("<tr>");
                      out.println("<td class=\"EEtiqueta\">MUN (Municipio):</td>");
                      out.println("<td>");
                      out.println("<select name=\"iCveMunicipio\" size=\"1\">");
                      out.println("</select>");
                      out.println("</td>");
                      out.print(vEti.EtiCampo("EEtiqueta","Teléfono:","ECampo","text",20,20,"cTel","",0,"","fMayus(this);",false,true,lCaptura));
                      out.println("</tr>");
                      out.println("<tr>");
                      out.println(vEti.EtiToggle("EEtiqueta","Activo:","ECampo","lActivo","1","",3,lCaptura,"1","0",lCaptura));
                      out.println(vEti.EtiToggle("EEtiqueta","Base:","ECampo","lBaseEventual","1","",3,lCaptura,"1","0",lCaptura));
                      out.println("</tr>");
                  }
                  else {
                      if (bs!=null){
                         TDPais dPais = new TDPais();
                         TVPais vPais = new TVPais();
                         Vector VecPais = new Vector();
                         VecPais = dPais.FindByAll();

                         TDEntidadFed dEntidadFed = new TDEntidadFed();
                         //TVEntidadFed vEntidadFed = new TVEntidadFed();
                         Vector VecEntidadFed = new Vector();
                         Vector VcEstado = new Vector();
                         VecEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePaisNac","").toString());
                         VcEstado = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais","").toString());

                         TDMunicipio dMunicipio = new TDMunicipio();
                         //TVMunicipio vMunicipio = new TVMunicipio();
                         Vector VecMunicipio = new Vector();
                         VecMunicipio = dMunicipio.FindByAll(" where iCvePais       = " + bs.getFieldValue("iCvePais","").toString() +
                                                             "   and iCveEntidadFed = " + bs.getFieldValue("iCveEstado","").toString());

                         out.println("<input type=\"hidden\" name=\"iNumPersonal\" value=\""+ bs.getFieldValue("iNumPersonal","").toString() +"\">");
                         out.println("<tr>");
                         out.print(vEti.EtiCampo("EEtiqueta","No. Personal:","ECampo","text",5,5,"iNumPersonal" ,bs.getFieldValue("iNumPersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                         out.print(vEti.EtiCampoCS("EEtiqueta","Expediente:","ECampo","text",12,12,4, "iCveExpediente",bs.getFieldValue("iCveExpediente","&nbsp;").toString(),0,"","fMayus(this);",false,false,false));
                         out.println("</tr>");
                         out.println("<tr>");
                         out.print(vEti.EtiCampoCS("EEtiqueta","Nombre:","ECampo","text",30,30, 6,"cNombre",bs.getFieldValue("cNombre","&nbsp;").toString() + " " + bs.getFieldValue("cApPaterno","&nbsp;").toString() + " " + bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                         out.println("</tr>");
                         out.println("<tr>");
                         out.print(vEti.EtiCampoCS("EEtiqueta","FECNAC (Fecha de Nacimiento):","ECampo","text",10,10, 6,"dtNacimiento",bs.getFieldValue("dtNacimiento","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.println("</tr>");
                         out.println("<tr>");
                         out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",13,13,"cRFC" ,bs.getFieldValue("cRFC","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.print(vEti.EtiCampoCS("EEtiqueta","CURP:","ECampo","text",18,18,4, "cCURP",bs.getFieldValue("cCURP","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.println("</tr>");
                         out.println("<tr>");
                         if (!lCaptura){
                           out.print(vEti.EtiCampo("EEtiqueta","Pais de Nacimiento:","ECampo","text",5,5, "iCvePaisNac",bs.getFieldValue("cDscPaisNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                           out.print(vEti.EtiCampoCS("EEtiqueta","Estado de Nacimiento:","ECampo","text",5,5,4,"iCveEstadoNac",bs.getFieldValue("cDscEstadoNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         } else {
                           out.println("<td class=\"EEtiqueta\">Pais de Nacimiento:</td>");
                           out.println("<td>");
                           if (VecPais.size() > 0){
                             out.println("<select name=\"iCvePaisNac\" size=\"1\" onchange=\"llenaSLT(3,this.value,'','',document.forms[0].iCveEstadoNac);\">");
                             if (new Integer(bs.getFieldValue("iCvePaisNac","").toString()).intValue() == 0)
                                out.println("<option value=\"0\" selected>Seleccione...</option>");
                             else
                                out.println("<option value=\"0\">Seleccione...</option>");
                             for (int i = 0; i < VecPais.size(); i++){
                               vPais = (TVPais)VecPais.get(i);
                               if (new Integer(bs.getFieldValue("iCvePaisNac","").toString()).intValue() == vPais.getICvePais())
                                 out.println("<option value=\""+vPais.getICvePais()+"\" selected>"+vPais.getCNombre()+"</option>");
                               else
                                 out.println("<option value=\""+vPais.getICvePais()+"\">"+vPais.getCNombre()+"</option>");
                             }
                           }else{
                             out.println("<select name=\"iCvePaisNac\" size=\"1\">");
                             out.println("<option value=\"0\">Datos no disponibles</option>");
                           }
                           out.println("</select>");
                           out.println("</td>");

                           //Estado de Nacimiento.
                           out.println("<td class=\"EEtiqueta\">Estado de Nacimiento:</td>");
                           out.println("<td>");
                           out.println("<select name=\"iCveEstadoNac\" size=\"1\">");
                           if (new Integer(bs.getFieldValue("iCveEstadoNac","").toString()).intValue() == -1)
                              out.println("<option value=\"-1\" selected>Seleccione...</option>");
                           else
                              out.println("<option value=\"-1\">Seleccione...</option>");
                           for (int i=0;i<VecEntidadFed.size();i++){
                             TVEntidadFed vEntidadFed = new TVEntidadFed();
                             vEntidadFed = (TVEntidadFed) VecEntidadFed.get(i);
                               if (new Integer(bs.getFieldValue("iCveEstadoNac","").toString()).intValue() == vEntidadFed.getICveEntidadFed())
                                 out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\" selected>"+vEntidadFed.getCNombre()+"</option>");
                               else
                                 out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
                           }
                           out.println("</select>");
                           out.println("</td>");
                         }
                         out.println("</tr>");
                         out.println("<tr>");
                         if (!lCaptura){
                            out.print(vEti.EtiCampo("EEtiqueta","Modo de Transporte:","ECampo","text",5,5, "iCveMdoTrans",bs.getFieldValue("cDscMdotrans","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                            out.print(vEti.EtiCampoCS("EEtiqueta","Puesto:","ECampo","text",5,5,4, "iCvePuesto",bs.getFieldValue("cDscPuesto","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         } else {
                           TDGRLMdoTrans dMdoTrans = new TDGRLMdoTrans();
                           out.println("<td class=\"EEtiqueta\">Modo de Transporte:</td>");
                           out.println("<td>");
                           Vector vMdoTransporte = new Vector();
                           vMdoTransporte = dMdoTrans.findByAll("");
                           out.println("<select name=\"iCveMdoTrans\" size=\"1\" onchange=\"llenaSLT(2,this.value,'','',document.forms[0].iCvePuesto);\">");
                           out.println("<option value=\"-1\">Seleccione...</option>");
                           for(int i=0;i<vMdoTransporte.size();i++){
                             TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
                             VGRLMdoTrans = (TVGRLMdoTrans) vMdoTransporte.get(i);
                             if (new Integer(bs.getFieldValue("iCveMdoTrans","").toString()).intValue() == VGRLMdoTrans.getICveMdoTrans())
                                out.println("<option value=\""+VGRLMdoTrans.getICveMdoTrans()+"\" selected>"+ VGRLMdoTrans.getCDscMdoTrans()+"</option>");
                             else
                                out.println("<option value=\""+VGRLMdoTrans.getICveMdoTrans()+"\">"+VGRLMdoTrans.getCDscMdoTrans()+"</option>");
                           }
                           out.println("</select>");
                           out.println("</td>");

                           //Puesto del Operador.
                           out.println("<td class=\"EEtiqueta\">Puesto:</td>");
                           out.println("<td>");
                           TDGRLPuesto dGRLPuesto = new TDGRLPuesto();
                           Vector vPuesto = new Vector();
                           vPuesto = dGRLPuesto.FindByAll(bs.getFieldValue("iCveMdoTrans","").toString(),"");
                           out.println("<select name=\"iCvePuesto\" size=\"1\">");
                           out.println("<option value=\"-1\">Seleccione...</option>");
                           for (int i=0;i<vPuesto.size();i++){
                              TVGRLPuesto VGRLPuesto = new TVGRLPuesto();
                              VGRLPuesto = (TVGRLPuesto) vPuesto.get(i);
                              if (new Integer(bs.getFieldValue("iCvePuesto","").toString()).intValue() == VGRLPuesto.getICvePuesto())
                                 out.println("<option value=\""+VGRLPuesto.getICvePuesto()+"\" selected>"+ VGRLPuesto.getCDscPuesto()+"</option>");
                              else
                                 out.println("<option value=\""+VGRLPuesto.getICvePuesto()+"\">"+VGRLPuesto.getCDscPuesto()+"</option>");
                           }
                           out.println("</select>");
                           out.println("</td>");
                         }
                         out.println("</tr>");
                         out.println("<tr>");
                         //out.print(vEti.EtiCampoCS("EEtiqueta","Licencia:","ECampo","text",20,20,3,"cLicencia" ,bs.getFieldValue("cLicencia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.print(vEti.EtiCampo("EEtiqueta","Licencia:","ECampo","text",20,20,"cLicencia" ,bs.getFieldValue("cLicencia","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.print(vEti.EtiCampoCS("EEtiqueta","Fecha de Vencimiento:","ECampo","text",10,10, 3,"dtLicVencimiento",bs.getFieldValue("dtLicVencimiento","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.println("</tr>");
                         out.println("<tr>");
                         out.print(vEti.EtiCampo("EEtiqueta","Calle:","ECampo","text",50,50,"cCalle" ,bs.getFieldValue("cCalle","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.print(vEti.EtiCampoCS("EEtiqueta","No. Exterior:","ECampo","text",30,30,4, "cNumExt",bs.getFieldValue("cNumExt","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.println("</tr>");
                         out.println("<tr>");
                         out.print(vEti.EtiCampo("EEtiqueta","No. Interior:","ECampo","text",30,30, "cNumInt",bs.getFieldValue("cNumInt","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.print(vEti.EtiCampoCS("EEtiqueta","CP:","ECampo","text",5,5,4, "iCP",bs.getFieldValue("iCP","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.println("</tr>");
                         out.println("<tr>");
                         out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",30,30, "cColonia",bs.getFieldValue("cColonia","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.print(vEti.EtiCampoCS("EEtiqueta","Ciudad:","ECampo","text",50,50,4, "cCiudad",bs.getFieldValue("cCiudad","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.println("</tr>");
                         out.println("<tr>");
                         if (!lCaptura){
                           out.print(vEti.EtiCampo("EEtiqueta","Pais:","ECampo","text",5,5, "iCvePais",bs.getFieldValue("cDscPais","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                           out.print(vEti.EtiCampoCS("EEtiqueta","EDO (Estado):","ECampo","text",5,5,4, "iCveEstado",bs.getFieldValue("cDscEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         } else {
                           out.println("<td class=\"EEtiqueta\">Pais:</td>");
                           out.println("<td>");
                           if (VecPais.size() > 0){
                             out.println("<select name=\"iCvePais\" size=\"1\" onchange=\"llenaSLT(3,this.value,'','',document.forms[0].iCveEstado);\">");
                             if (new Integer(bs.getFieldValue("iCvePais","").toString()).intValue() == 0)
                                out.println("<option value=\"0\" selected>Seleccione...</option>");
                             else
                                out.println("<option value=\"0\">Seleccione...</option>");
                             for (int i = 0; i < VecPais.size(); i++){
                               vPais = (TVPais)VecPais.get(i);
                               if (new Integer(bs.getFieldValue("iCvePais","").toString()).intValue() == vPais.getICvePais())
                                 out.println("<option value=\""+vPais.getICvePais()+"\" selected>"+vPais.getCNombre()+"</option>");
                               else
                                 out.println("<option value=\""+vPais.getICvePais()+"\">"+vPais.getCNombre()+"</option>");
                             }
                           }else{
                             out.println("<select name=\"iCvePais\" size=\"1\">");
                             out.println("<option value=\"0\">Datos no disponibles</option>");
                           }
                           out.println("</select>");
                           out.println("</td>");

                           //Estado del Domicilio.
                           out.println("<td class=\"EEtiqueta\">Entidad:</td>");
                           out.println("<td>");
                           out.println("<select name=\"iCveEstado\" size=\"1\" onchange=\"llenaSLT(4,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio);\">");
                           if (new Integer(bs.getFieldValue("iCveEstado","").toString()).intValue() == -1)
                              out.println("<option value=\"-1\" selected>Seleccione...</option>");
                           else
                              out.println("<option value=\"-1\">Seleccione...</option>");
                           if (!VcEstado.isEmpty()){
                             for (int i=0;i<VcEstado.size();i++){
                                TVEntidadFed vEntidadFed = new TVEntidadFed();
                                vEntidadFed = (TVEntidadFed) VcEstado.get(i);
                                if (new Integer(bs.getFieldValue("iCveEstado","").toString()).intValue() == vEntidadFed.getICveEntidadFed())
                                  out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\" selected>"+vEntidadFed.getCNombre()+"</option>");
                                else
                                  out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
                             }
                           } else {
                             out.println("<select name=\"iCveEstado\" size=\"1\">");
                             out.println("<option value=\"0\">Datos no disponibles</option>");
                           }
                           out.println("</select>");
                           out.println("</td>");
                         }
                         out.println("</tr>");
                         out.println("<tr>");
                         if (!lCaptura)
                            out.print(vEti.EtiCampo("EEtiqueta","MUN (Municipio):","ECampo","text",5,5, "iCveMunicipio",bs.getFieldValue("cDscMunicipio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         else {
                           out.println("<td class=\"EEtiqueta\">MUN (Municipio):</td>");
                           out.println("<td>");
                           out.println("<select name=\"iCveMunicipio\" size=\"1\">");
                           if (new Integer(bs.getFieldValue("iCveMunicipio","").toString()).intValue() == -1)
                              out.println("<option value=\"-1\" selected>Seleccione...</option>");
                           else
                              out.println("<option value=\"-1\">Seleccione...</option>");
                           if (!VecMunicipio.isEmpty()){
                             for (int i=0;i<VecMunicipio.size();i++){
                                TVMunicipio vMunicipio = new TVMunicipio();
                                vMunicipio = (TVMunicipio) VecMunicipio.get(i);
                                if (new Integer(bs.getFieldValue("iCveMunicipio","").toString()).intValue() == vMunicipio.getICveMunicipio())
                                  out.println("<option value=\""+vMunicipio.getICveMunicipio()+"\" selected>"+vMunicipio.getCNombre()+"</option>");
                                else
                                  out.println("<option value=\""+vMunicipio.getICveMunicipio()+"\">"+vMunicipio.getCNombre()+"</option>");
                             }
                           } else {
                             out.println("<select name=\"iCveMunicipio\" size=\"1\">");
                             out.println("<option value=\"0\">Datos no disponibles</option>");
                           }
                           out.println("</select>");
                           out.println("</td>");
                         }
                         out.print(vEti.EtiCampoCS("EEtiqueta","Teléfono:","ECampo","text",20,20,4, "cTel",bs.getFieldValue("cTel","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                         out.println("</tr>");
                         out.println("<tr>");
                         if (new Integer(bs.getFieldValue("lActivo","").toString()).intValue() == 1)
                            out.println(vEti.EtiToggle("EEtiqueta","Activo:","ECampo","lActivo","1","",3,true,"1","0",lCaptura));
                         else
                            out.println(vEti.EtiToggle("EEtiqueta","Activo:","ECampo","lActivo","0","",3,true,"1","0",lCaptura));
                         if (new Integer(bs.getFieldValue("lBaseEventual","").toString()).intValue() == 1)
                            out.println(vEti.EtiToggle("EEtiqueta","Base:","ECampo","lBaseEventual","1","",3,true,"1","0",lCaptura));
                         else
                            out.println(vEti.EtiToggle("EEtiqueta","Base:","ECampo","lBaseEventual","0","",3,true,"1","0",lCaptura));
                         out.println("</tr>");
                         if (!lCaptura){
                           vLicencias = clsConfig.Licencias(new Integer(bs.getFieldValue("iCveEmpresa","").toString()).intValue(),
                                                            new Integer(bs.getFieldValue("iCvePlantilla","").toString()).intValue(),
                                                            new Integer(bs.getFieldValue("iNumPersonal","").toString()).intValue());
                           if(!vLicencias.isEmpty()){
                             if (vLicencias.size() > 0){
                               out.println("<tr>");
                               out.println("<td class=\"ECampo\" colspan=\"6\">&nbsp</td>");
                               out.println("</tr>");
                               out.println("<tr>");
                               out.println("<td class=\"ETablaT\" colspan=\"6\">Licencias Duplicadas</td>");
                               out.println("</tr>");
                               out.println("<tr>");
                               out.println("<td class=\"ETablaT\">Empresa</td>");
                               out.println("<td class=\"ETablaT\">Plantilla</td>");
                               out.println("<td class=\"ETablaT\">No.Personal</td>");
                               out.println("<td class=\"ETablaT\">Nombre</td>");
                               out.println("<td class=\"ETablaT\">Licencia</td>");
                               out.println("</tr>");
                               for(int i=0;i<vLicencias.size();i++){
                                  TVCTRPersonal VCTRPersonal = new TVCTRPersonal();
                                  VCTRPersonal = (TVCTRPersonal) vLicencias.get(i);
                                  out.println("<tr>");
                                  out.print(vEti.Texto("ETabla",""+ VCTRPersonal.getICveEmpresa() + " - " + VCTRPersonal.getCDscEmpresa()));
                                  out.print(vEti.Texto("ETablaR",""+ VCTRPersonal.getiCvePlantilla()));
                                  out.print(vEti.Texto("ETablaR",""+ VCTRPersonal.getINumPersonal()));
                                  out.print(vEti.Texto("ETabla",""+ VCTRPersonal.getCNombre() + " " + VCTRPersonal.getCApPaterno() + " " + VCTRPersonal.getCApMaterno()));
                                  out.print(vEti.Texto("ETabla",""+ VCTRPersonal.getCLicencia()));
                                  out.println("</tr>");
                               }
                             }
                           }
                         }

                      }else{
                          out.println("<tr>");
                          out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                          out.println("</tr>");
                      }
                  }
              %>
         </table><% // Fin de Datos %>
         <script language="javascript">
            form = document.forms[0];
            if (form.iNumPersonal)
               form.iNumPersonal.readOnly = true;
         </script>
     </td>
  </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>