<%/**
 * Title: Catálogo del Personal
 * Description: Catálogo de Direcciones
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. González Paz
 * @version 1.0
 * Clase:SEDetPerCFG
 *  */%>


<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%> 
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<html>
<%
  pg070103042CFG  clsConfig = new pg070103042CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070103042.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103042.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave1    = "";
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
  String cTipoFiltrar = "|";                // modificar
  boolean lFiltros    = true;                 // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

      String cCampo = "";
      String cPanel = "";

    if ( (request.getParameter("hdOPPbaRapida") == null) ||
         (request.getParameter("hdBoton").compareTo("Guardar") == 0) ||
          (request.getParameter("hdBoton").compareTo("Cancelar") == 0)){
         cCampo = "0";
    }
    else{
         cCampo = "" + request.getParameter("hdOPPbaRapida");
     }



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
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  
  TDPERDireccion dPERDireccion = new TDPERDireccion();
  TDGRLLocalidad dLocalidad = new TDGRLLocalidad();
  
%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">


  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SEDetPer.js"%>"></SCRIPT>

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
       cClave1  = ""+bs.getFieldValue("iCvePersonal", "");
       cClave2  = ""+bs.getFieldValue("iCveDireccion", "");
        }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave1%>">
  <input type="hidden" name="hdCampoClave2" value="<%=cClave2%>">
  <input type="hidden" name="hdAnio" value="<%=iAnio%>">
  <input type="hidden" name="hdPbaRapida" >
  <input type="hidden" name="hdPersonal">
  <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal")%>">


  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo")%>">
  <input type="hidden" name="dtFecha" value="<%=request.getParameter("dtFecha")%>">
  <input type="hidden" name="cHora" value="<%=request.getParameter("cHora")%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td>
  <input type="hidden" name="hdBoton">
  </td><td valign="top">

                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                 <tr>
                                 <td colspan="4" class="ETablaT">Domicilio
                                 </td>
                                 </tr>
                                 <tr>

                              <%
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Personal:","ECampo","text",10,10,"iCvePersonal","&nbsp;",0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Cve.Dirección:","ECampo","text",10,10,"iCveDireccion","&nbsp;",0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Calle:","ECampo","text",50,50,4,"cCalle","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Ext.:","ECampo","text",25,25,"cNumExt","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Int.:","ECampo","text",25,25,"cNumInt","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",30,30,"cColonia","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","CP:","ECampo","text",5,5,"iCP","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Ciudad:","ECampo","text",50,50,"cCiudad","",0,"","fMayus(this);",false,true,lCaptura));
                                    TDPais dPais = new TDPais();
                                    out.println(vEti.Texto("EEtiqueta","País:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePais","llenaSLT(2,this.value,'','',document.forms[0].iCveEstado);", dPais.FindByAll(), "iCvePais", "cNombre", request, "0", true));
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","EDO (Estado):"));
                                    out.println("<td class='ECampo'>");
                                    out.println("<SELECT NAME='iCveEstado' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio);\" ");
                                    out.println("</SELECT>");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
                                    out.println("<td class='ECampo'>");
                                    out.println("<SELECT NAME='iCveMunicipio' SIZE='1' onChange=\"llenaSLT(5,document.forms[0].iCvePais.value,document.forms[0].iCveEstado.value,this.value,document.forms[0].iCveLocalidad);\" ");
                                    out.println("</SELECT>");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","LOC (Población):"));
                                    out.println("<td class='ECampo'>");
                                    out.println("<SELECT NAME='iCveLocalidad' SIZE='1' ");
                                    out.println("</SELECT>");
                                    out.println("</td>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Tel:","ECampo","text",20,20,4,"cTel","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                }
                                else {
                                    /*if(request.getParameter("iCveUniMed").equals("107")){
                                    	//System.out.println("tercero");
                                    	out.println("<tr>");
	                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Personal:","ECampo","text",10,10,"iCvePersonal", bs.getFieldValue("iCvePersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Dirección:","ECampo","text",10,10,"iCveDireccion", bs.getFieldValue("iCveDireccion","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampoCS("EEtiqueta","Calle:","ECampo","text",50,50,4,"cCalle", bs.getFieldValue("cCalle","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampo("EEtiqueta","No. Ext.:","ECampo","text",25,25,"cNumExt", bs.getFieldValue("cNumExt","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.print(vEti.EtiCampo("EEtiqueta","No. Int.:","ECampo","text",25,25,"cNumInt", bs.getFieldValue("cNumInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",30,30,"cColonia", bs.getFieldValue("cColonia","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.print(vEti.EtiCampo("EEtiqueta","CP:","ECampo","text",5,5,"iCP", bs.getFieldValue("iCP","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampo("EEtiqueta","Ciudad:","ECampo","text",50,50,"cCiudad", bs.getFieldValue("cCiudad","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.print(vEti.EtiCampo("EEtiqueta","País:","ECampo","text",10,10,"iCvePais", bs.getFieldValue("cDscPais","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampo("EEtiqueta","EDO (Estado):","ECampo","text",10,10,"iCveEstado", bs.getFieldValue("cDscEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.print(vEti.EtiCampo("EEtiqueta","MUN (Municipio):","ECampo","text",01,10,"iCveMunicipio", bs.getFieldValue("cDscMunicipio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                   
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampoCS("EEtiqueta","Tel.:","ECampo","text",20,20,4,"cTel", bs.getFieldValue("cTel","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.println("</tr>");
	                               }else{         */   
	                            	   //System.out.println("unidad");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Personal:","ECampo","text",10,10,"iCvePersonal", bs.getFieldValue("iCvePersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Dirección:","ECampo","text",10,10,"iCveDireccion", bs.getFieldValue("iCveDireccion","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampoCS("EEtiqueta","Calle:","ECampo","text",50,50,4,"cCalle", bs.getFieldValue("cCalle","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampo("EEtiqueta","No. Ext.:","ECampo","text",25,25,"cNumExt", bs.getFieldValue("cNumExt","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
	                                    out.print(vEti.EtiCampo("EEtiqueta","No. Int.:","ECampo","text",25,25,"cNumInt", bs.getFieldValue("cNumInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",30,30,"cColonia", bs.getFieldValue("cColonia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
	                                    out.print(vEti.EtiCampo("EEtiqueta","CP:","ECampo","text",5,5,"iCP", bs.getFieldValue("iCP","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampo("EEtiqueta","Ciudad:","ECampo","text",50,50,"cCiudad", bs.getFieldValue("cCiudad","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
	                                     if (request.getParameter("hdBoton").compareTo("Modificar") != 0 ){
	                                    out.print(vEti.EtiCampo("EEtiqueta","País:","ECampo","text",10,10,"iCvePais", bs.getFieldValue("cDscPais","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.print(vEti.EtiCampo("EEtiqueta","EDO (Estado):","ECampo","text",10,10,"iCveEstado", bs.getFieldValue("cDscEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
	                                    out.print(vEti.EtiCampo("EEtiqueta","MUN (Municipio):","ECampo","text",01,10,"iCveMunicipio", bs.getFieldValue("cDscMunicipio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
	                                    out.println("</tr>");
	                                    
	                                    boolean localidad = dPERDireccion.FLocalidad(bs.getFieldValue("iCvePersonal","").toString());
	                                    String Localidad2 = "";
	                                    String iCveLocalidad = "";
	                                    if(localidad){
											String cWhere = " icvepais = "+bs.getFieldValue("iCvePais","").toString()+
													" and icveentidadfed = "+bs.getFieldValue("iCveEstado","").toString()+
													" and icvemunicipio = "+bs.getFieldValue("iCveMunicipio","").toString()+
													" and icvelocalidad = "+bs.getFieldValue("iCveLocalidad","").toString();
											Localidad2 = dLocalidad.UbicaLocalidad(cWhere);
											iCveLocalidad = ""+bs.getFieldValue("iCveLocalidad","").toString();
										}else{
											Localidad2 = "DESCONOCIDO";
											iCveLocalidad = "0"; 
										}
	                                    
	                                    out.print(vEti.EtiCampo("EEtiqueta","LOC (Población):","ECampo","text",01,10,"iCveLocalidad", Localidad2,0,"","fMayus(this);",false,true,lCaptura));
	                                    //out.println("</tr>");
	                                     }
	                                    else{
	                                    TDPais dPaisNac = new TDPais();
	                                    //out.println("<tr>");
	                                    out.println(vEti.Texto("EEtiqueta","País:"));
	                                    out.println("<td>");
	                                    out.println(vEti.SelectOneRowSinTD("iCvePais","llenaSLT(2,this.value,'','',document.forms[0].iCveEstado);", dPaisNac.FindByAll(), "iCvePais", "cNombre",request, bs.getFieldValue("iCvePais","&nbsp;").toString() , true));
	                                    out.println("</td>");
	                                    out.println("</tr>");
	                                    out.println(vEti.Texto("EEtiqueta","EDO (Estado):"));
	                                    out.println("<td class='ECampo'>");
	
	                                    TVEntidadFed vEntidadFed = new TVEntidadFed();
	                                    TDEntidadFed dEntidadFed = new TDEntidadFed();
	                                    Vector vcEntidadFed = new Vector();
	                                    vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais","&nbsp;").toString());
	                                    out.println(vEti.SelectOneRowSinTD("iCveEstado","llenaSLT(3,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio);", vcEntidadFed, "iCveEntidadFed", "cNombre",request, bs.getFieldValue("iCveEstado","&nbsp;").toString() , true));
	                                    out.println("</td>");
	                                    out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
	                                    out.println("<td class='ECampo'>");
	                                    TVMunicipio vMunicipio= new TVMunicipio();
	                                    TDMunicipio dMunicipio = new TDMunicipio();
	                                    Vector vcMunicipio= new Vector();
	                                    vcMunicipio = dMunicipio.FindByAll(bs.getFieldValue("iCvePais","&nbsp;").toString(),bs.getFieldValue("iCveEstado","&nbsp;").toString());
	                                    //out.println(vEti.SelectOneRowSinTD("iCveMunicipio","llenaSLT(5,document.forms[0].iCvePais.value,document.forms[0].iCveEstado.value,this.value,'',document.forms[0].iCveLocalidad);", vcMunicipio, "iCveMunicipio", "cNombre",request, bs.getFieldValue("iCveMunicipio","&nbsp;").toString() , true));
	                                    out.println(vEti.SelectOneRowSinTD("iCveMunicipio","llenaSLT(5,document.forms[0].iCvePais.value,document.forms[0].iCveEstado.value,this.value,document.forms[0].iCveLocalidad);", vcMunicipio, "iCveMunicipio", "cNombre",request, bs.getFieldValue("iCveMunicipio","&nbsp;").toString() , true));
	                                    out.println("</tr>");
	                                    out.println("<tr>");
	                                    out.println(vEti.Texto("EEtiqueta","LOC (Población):"));
	                                    out.println("<td class='ECampo'>");
	                                    TVGRLLocalidad vLocalidad= new TVGRLLocalidad();
	                                    Vector vcLocalidad= new Vector();
	                                    vcLocalidad = dLocalidad.FindByAll(bs.getFieldValue("iCvePais","&nbsp;").toString(),bs.getFieldValue("iCveEstado","&nbsp;").toString(),bs.getFieldValue("iCveMunicipio","&nbsp;").toString());
	                                    out.println(vEti.SelectOneRowSinTD("iCveLocalidad","", vcLocalidad, "iCveLocalidad", "cNombre",request, bs.getFieldValue("iCveLocalidad","&nbsp;").toString() , true));
	                                    
	                                    //out.println("</tr>");
	                                    //out.println("<tr>");
	                                    }
	                                    //out.println("</tr>");
	                                    //out.println("<tr>");
	                                    out.print(vEti.EtiCampoCS("EEtiqueta","Tel.:","ECampo","text",20,20,4,"cTel", bs.getFieldValue("cTel","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
	                                    out.println("</tr>");
	                                    
                                    //}                                   
                                    
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
Adicionales