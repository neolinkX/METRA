<%/**
 * Title:        Catalogo de Vehículos
 * Description:  Catalogo de Vehículos
 * Copyright:    2004
 * Company:      Micros Persnales S.A. de C.V.
 * @author       Marco Antonio Hernández García
 * @version      1.0
 * Clase:        pg070702011.jsp
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.StringTokenizer"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.text.*"%>
<html>
<%
  pg070702011CFG  clsConfig = new pg070702011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070702011.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070702011.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Vehículo|Núm. Serie|Placa|";    // modificar
  String cCveOrdenar  = "VEHVehiculo.iCveVehiculo|VEHVehiculo.cNumSerie|VEHVehiculo.cPlacas|";  // modificar
  String cDscFiltrar  = "Vehículo|Núm. Serie|Placa|";    // modificar
  String cCveFiltrar  = "VEHVehiculo.iCveVehiculo|VEHVehiculo.cNumSerie|VEHVehiculo.cPlacas|";  // modificar
  String cTipoFiltrar = "7|8|8|";                // modificar
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
  String cUpdStatus  = "";

  if (request.getParameter("hdBoton")!=null && (request.getParameter("hdBoton").toString().compareTo("Actual")==0 ||
                                                request.getParameter("hdBoton").toString().compareTo("Primero")==0 ||
                                                request.getParameter("hdBoton").toString().compareTo("Cancelar")==0) ||
                                                request.getParameter("hdBoton").toString().compareTo("Ultimo")==0 ||
                                                request.getParameter("hdBoton").toString().compareTo("Anterior")==0 ||
                                                request.getParameter("hdBoton").toString().compareTo("Siguiente")==0 ||
                                                request.getParameter("hdBoton").toString().compareTo("Guardar")==0 ||
                                                request.getParameter("hdBoton").toString().compareTo("GuardarA")==0)
     cUpdStatus  = "SaveCancelUpDateOnly";
  else
     if (request.getParameter("hdBoton")!=null && (request.getParameter("hdBoton").toString().compareTo("Nuevo")==0 ||
                                                   request.getParameter("hdBoton").toString().compareTo("Modificar")==0))
        cUpdStatus  = "UpdateCancelBegin";

  if (bs!=null && bs.getFieldValue("lBaja")!=null && bs.getFieldValue("lBaja","0").toString().compareTo("1")==0)
     cUpdStatus = "AddOnly2";

  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  String cTemp = "";
  int iAnioIni = new Integer(vParametros.getPropEspecifica("iAniosIni")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnioFin = dtFecha.getIntYear(dtFecha.TodaySQL())+1;
  String cMeses = vParametros.getPropEspecifica("NombresMes");
  StringTokenizer st = new StringTokenizer(cMeses,",");
  int i = 0;
  DecimalFormat df = new DecimalFormat("#,##0");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070702011.js)
  function Activar(checked){
     form = document.forms[0];
     if (form.dtBaja)
        form.dtBaja.disabled = !checked;
     if (form.iCveMtvoBaja)
        form.iCveMtvoBaja.disabled = !checked;
     if (form.lActivo)
        form.lActivo.disabled = checked;
  }

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
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       if (request.getParameter("hdVehiculo")!=null)
          cPosicion = request.getParameter("hdVehiculo");
       else
          cPosicion = Integer.toString(bs.rowNo());
     }

  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=request.getParameter("hdVehiculo")%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
     <tr>
        <td valign="top">
          <input type="hidden" name="hdBoton" value="">
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td colspan="4" class="ETablaT">Vehículo</td>
            </tr>
            <%
                TEtiCampo vEti = new TEtiCampo();
                boolean lCaptura = clsConfig.getCaptura();
                boolean lNuevo = clsConfig.getNuevo();
                String cTemporal;
                if (lNuevo){
                    out.println("<tr>");
                    out.println("<td class=\"EEtiqueta\">Tipo de Vehículo:</td>");
                    out.println("<td>");
                    TDVEHTpoVehiculo dVEHTpoVehiculo = new TDVEHTpoVehiculo();
                    TVVEHTpoVehiculo vVEHTpoVehiculo = new TVVEHTpoVehiculo();
                    Vector vTpoVehiculo = new Vector();
                       vTpoVehiculo = dVEHTpoVehiculo.FindByAll();
                    if (vTpoVehiculo.size()>0)
                       out.print(vEti.SelectOneRowSinTD("iCveTpoVehiculo","",vTpoVehiculo,"iCveTpoVehiculo","cDscBreve",request,"0",true));
                    else{
                       out.println("<SELECT NAME=\"iCveTpoVehiculo\" SIZE=\"1\">");
                       out.println("<option value=\"0\">Datos no disponibles</option>");
                       out.println("</SELECT>");
                    }
                    out.println("</td>");
                    out.print(vEti.EtiCampo("EEtiqueta","Vehículo:","ECampo","text",5,5,"iCveVehiculo","?",0,"","fMayus(this);",false,true,false));
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td class=\"EEtiqueta\">Marca:</td>");
                    out.println("<td>");
                    TDVEHMarca dVEHMarca = new TDVEHMarca();
                    TVVEHMarca vVEHMarca = new TVVEHMarca();
                    Vector vMarca = new Vector();
                       vMarca = dVEHMarca.FindByAll();
                    if (vMarca.size()>0)
                       out.print(vEti.SelectOneRowSinTD("iCveMarca","llenaSLT(1,this.value,'','',document.forms[0].iCveModelo);",vMarca,"iCveMarca","cDscBreve",request,"0",true));
                    else{
                       out.println("<SELECT NAME=\"iCveMarca\" SIZE=\"1\">");
                       out.println("<option value=\"0\">Datos no disponibles</option>");
                       out.println("</SELECT>");
                    }
                    out.println("</td>");
                    out.println("<td class=\"EEtiqueta\">Modelo:</td>");
                    out.println("<td>");
                     out.println("<SELECT NAME=\"iCveModelo\" SIZE=\"1\">");
                     out.println("</SELECT>");
                    out.println("</td>");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td class=\"EEtiqueta\">Año:</td>");
                    out.println("<td class=\"EEtiquetaL\">");
                    out.println("<select name=\"iAnioVeh\" size=\"1\">");
                    for (int j = iAnioIni; j <= iAnioFin; j++){
                       out.print("<option value = " + j + ">" + j + "</option>");
                    }
                    out.println("</select>");
                    out.println("</td>");

                    out.println("<td class=\"EEtiqueta\">Color:</td>");
                    out.println("<td>");
                    TDVEHColor dVEHColor = new TDVEHColor();
                    TVVEHColor vVEHColor = new TVVEHColor();
                    Vector vColor = new Vector();
                       vColor = dVEHColor.FindByAll();
                    if (vColor.size()>0)
                       out.print(vEti.SelectOneRowSinTD("iCveColor","",vColor,"iCveColor","cDscColor",request,"0",true));
                    else{
                       out.println("<SELECT NAME=\"iCveColor\" SIZE=\"1\">");
                       out.println("<option value=\"0\">Datos no disponibles</option>");
                       out.println("</SELECT>");
                    }
                    out.println("</td>");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta","Núm. Serie:","ECampo","text",50,50,3,"cNumSerie","",0,"","fMayus(this);",false,true,lCaptura));
                    out.println("</tr>");

                    out.println("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta","Núm. Motor:","ECampo","text",50,50,3,"cNumMotor","",0,"","fMayus(this);",false,true,lCaptura));
                    out.println("</tr>");

                    out.println("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta","Inventario:","ECampo","text",50,50,3,"cInventario","",0,"","fMayus(this);",false,true,lCaptura));
                    out.println("</tr>");

                    out.println("<tr>");
                    out.print(vEti.EtiCampo("EEtiqueta","Placas:","ECampo","text",10,10,"cPlacas", "",0,"","fMayus(this);",false,true,lCaptura));
                    out.print(vEti.EtiCampo("EEtiqueta","Km. Finales:","ECampo","text",10,10,"iKmFinal","",0,"","fMayus(this);",false,true,lCaptura));
                    out.println("</tr>");

                    out.println("<tr>");
                    out.print(vEti.EtiCampo("EEtiqueta","Km. Garantía:","ECampo","text",10,10,"iKmGarantia","",0,"","fMayus(this);",false,true,lCaptura));
                    out.print(vEti.EtiCampo("EEtiqueta","Mes de Garantía:","ECampo","text",10,10,"iMesGarantia", "",0,"","fMayus(this);",false,true,lCaptura));
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td class=\"EEtiqueta\">Mantenimiento a los :</td>");
                    out.println("<td class=\"EEtiquetaL\">");
                    out.println("<input type=\"text\" size=\"8\" maxlength=\"8\" name=\"iKmMantto\" value=\"\"> Km.");
                    out.println("</td>");
                    out.println("<td class=\"EEtiqueta\">o cada:</td>");
                    out.println("<td class=\"EEtiquetaL\">");
                    out.println("<input type=\"text\" size=\"5\" maxlength=\"5\" name=\"iMesMantto\" value=\"\"> meses");
                    out.println("</td>");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta","Inicio de Mantenimiento:","ECampo","text",10,10,3,"dtIniMantto","",0,"","fMayus(this);",false,true,lCaptura));
                    out.println("</tr>");

                    out.println("<tr><td colspan=\"4\" class=\"ETablaT\">Póliza del Seguro</td></tr>");

                    out.println("<tr>");
                    out.print(vEti.EtiAreaTexto("EEtiqueta","Cobertura:<p class='EPieR'>Caracteres Disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetrasCob\" value=\"\" disabled></p>","ECampo",40,3,"cCobertura","",0,"fMayus(this);\" onkeyup=\"fDespRestantes(this,document.forms[0].iNoLetrasCob,200);\" onchange=\"fDespRestantes(this,document.forms[0].iNoLetrasCob,200);","",false,true,lCaptura));
                    out.print(vEti.EtiAreaTexto("EEtiqueta","Aseguradora:","ECampo",40,3,"cAseguradora","",0,"fMayus(this);","",false,true,lCaptura));
                    out.println("</tr>");

                    out.println("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta","Póliza:","ECampo","text",50,50,3,"cPoliza","",0,"","fMayus(this);",false,true,lCaptura));
                    out.println("</tr>");

                    out.println("<tr>");
                    out.print(vEti.EtiCampo("EEtiqueta","Inicio Vig:","ECampo","text",10,10,"dtInicioVig","",0,"","fMayus(this);",false,true,lCaptura));
                    out.print(vEti.EtiCampo("EEtiqueta","Fin Vig:","ECampo","text",10,10,"dtFinVig","",0,"","fMayus(this);",false,true,lCaptura));
                    out.println("</tr>");

                    out.println("<tr><td colspan=\"4\" class=\"ETablaT\">Situación</td></tr>");

                    out.println("<tr>");
                    out.print(vEti.EtiCampo("EEtiqueta","Fecha de Alta:","ECampo","text",10,10,"dtAlta","",0,"","fMayus(this);",false,true,lCaptura));

                    out.println("<td class=\"EEtiqueta\">EDO (Estado):</td>");
                    out.println("<td>");
                    TDVEHEstado dVEHEstado = new TDVEHEstado();
                    TVVEHEstado vVEHEstado = new TVVEHEstado();
                    Vector vEstado = new Vector();
                       vEstado = dVEHEstado.FindByAll();
                    if (vEstado.size()>0)
                       out.print(vEti.SelectOneRowSinTD("iCveEstadoVEH","",vEstado,"iCveEstadoVeh","cDscEstadoVeh",request,"0",true));
                    else{
                       out.println("<SELECT NAME=\"iCveEstadoVEH\" SIZE=\"1\">");
                       out.println("<option value=\"0\">Datos no disponibles</option>");
                       out.println("</SELECT>");
                    }
                    out.println("</td>");
                    out.println("</tr>");

                    out.println("<tr>");
                       out.println("<td class=\"EEtiqueta\">Activo:</td>");
                       out.println("<td><input type=\"checkbox\" name=\"lActivo\" value=\"1\" onclick=\"document.forms[0].lBaja.disabled=this.checked;\"></td>");
                       out.println("<td class=\"EEtiqueta\">Baja:</td>");
                       out.println("<td><input type=\"checkbox\" name=\"lBaja\" value=\"1\" onClick=\"Activar(this.checked);\"></td>");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.print(vEti.EtiCampo("EEtiqueta","Fecha de Baja:","ECampo","text",10,10,"dtBaja","",0,"","fMayus(this);",false,true,lCaptura));
                    out.println("<td class=\"EEtiqueta\">Motivo de Baja:</td>");
                    out.println("<td>");
                    TDVEHMtvoBaja dVEHMtvoBaja = new TDVEHMtvoBaja();
                    TVVEHMtvoBaja vVEHMtvoBaja = new TVVEHMtvoBaja();
                    Vector vMtvoBaja = new Vector();
                       vMtvoBaja = dVEHMtvoBaja.FindByAll();
                    if (vMtvoBaja.size()>0)
                       out.print(vEti.SelectOneRowSinTD("iCveMtvoBaja","",vMtvoBaja,"iCveMtvoBaja","cDscMtvoBaja",request,"0",true));
                    else{
                       out.println("<SELECT NAME=\"iCveMtvoBajaVEH\" SIZE=\"1\">");
                       out.println("<option value=\"0\">Datos no disponibles</option>");
                       out.println("</SELECT>");
                    }
                    out.println("</td>");
                    out.println("</tr>");
                }
                else {
                    cTemporal = bs!=null?bs.getFieldValue("iCveVehiculo","0").toString():"0";
                    out.println("<input type=\"hidden\" name=\"iCveVehiculo\" value=\""+cTemporal+"\">");
                    if (lCaptura){
                       out.println("<tr>");
                        out.println("<td class=\"EEtiqueta\">Tipo de Vehículo:</td>");
                        out.println("<td>");
                        TDVEHTpoVehiculo dVEHTpoVehiculo = new TDVEHTpoVehiculo();
                        TVVEHTpoVehiculo vVEHTpoVehiculo = new TVVEHTpoVehiculo();
                        Vector vTpoVehiculo = new Vector();
                           vTpoVehiculo = dVEHTpoVehiculo.FindByAll();
                        if (vTpoVehiculo.size()>0)
                           out.print(vEti.SelectOneRowSinTD("iCveTpoVehiculo","",vTpoVehiculo,"iCveTpoVehiculo","cDscBreve",request,bs.getFieldValue("iCveTpoVehiculo","0").toString(),true));
                        else{
                           out.println("<SELECT NAME=\"iCveTpoVehiculo\" SIZE=\"1\">");
                           out.println("<option value=\"0\">Datos no disponibles</option>");
                           out.println("</SELECT>");
                        }
                        out.println("</td>");
                       out.print(vEti.EtiCampo("EEtiqueta","Vehículo:","ECampo","text",5,5,"iCveVehiculo", bs.getFieldValue("iCveVehiculo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                       out.println("</tr>");

                       out.println("<tr>");
                        out.println("<td class=\"EEtiqueta\">Marca:</td>");
                        out.println("<td>");
                        TDVEHMarca dVEHMarca = new TDVEHMarca();
                        TVVEHMarca vVEHMarca = new TVVEHMarca();
                        Vector vMarca = new Vector();
                           vMarca = dVEHMarca.FindByAll();
                        if (vMarca.size()>0)
                           out.print(vEti.SelectOneRowSinTD("iCveMarca","llenaSLT(1,this.value,'','',document.forms[0].iCveModelo);",vMarca,"iCveMarca","cDscBreve",request,bs.getFieldValue("iCveMarca","0").toString(),true));
                        else{
                           out.println("<SELECT NAME=\"iCveMarca\" SIZE=\"1\">");
                           out.println("<option value=\"0\">Datos no disponibles</option>");
                           out.println("</SELECT>");
                        }
                        out.println("</td>");
                        out.println("<td class=\"EEtiqueta\">Modelo:</td>");
                        out.println("<td>");
                        TDVEHModelo dVEHModelo = new TDVEHModelo();
                        TVVEHModelo vVEHModelo = new TVVEHModelo();
                        Vector vModelo = new Vector();
                        String cFiltro = " WHERE VEHModelo.iCveMarca = " + bs.getFieldValue("iCveMarca","0").toString();
                        String cOrden = " ORDER BY VEHModelo.cDscBreve ";
                        vModelo = dVEHModelo.FindByAll(cFiltro,cOrden);
                        if (vModelo.size()>0)
                           out.print(vEti.SelectOneRowSinTD("iCveModelo","",vModelo,"iCveModelo","cDscBreve",request,bs.getFieldValue("iCveModelo","0").toString(),true));
                        else{
                           out.println("<SELECT NAME=\"iCveModelo\" SIZE=\"1\">");
                           out.println("<option value=\"0\">Datos no disponibles</option>");
                           out.println("</SELECT>");
                        }
                        out.println("</td>");
                       out.println("</tr>");

                       out.println("<tr>");
                        out.println("<td class=\"EEtiqueta\">Año:</td>");
                        out.println("<td class=\"EEtiquetaL\">");
                        out.println("<select name=\"iAnioVeh\" size=\"1\">");
                        for (int j = iAnioIni; j <= iAnioFin; j++){
                           if (bs.getFieldValue("iAnioVeh","0")!=null && bs.getFieldValue("iAnioVeh","0").toString().compareTo(""+j)==0)
                              out.print("<option value = " + j + " selected>" + j + "</option>");
                           else
                              out.print("<option value = " + j + ">" + j + "</option>");
                        }
                        out.println("</select>");
                        out.println("</td>");

                        out.println("<td class=\"EEtiqueta\">Color:</td>");
                        out.println("<td>");
                        TDVEHColor dVEHColor = new TDVEHColor();
                        TVVEHColor vVEHColor = new TVVEHColor();
                        Vector vColor = new Vector();
                           vColor = dVEHColor.FindByAll();
                        if (vColor.size()>0)
                           out.print(vEti.SelectOneRowSinTD("iCveColor","",vColor,"iCveColor","cDscColor",request,bs.getFieldValue("iCveColor","0").toString(),true));
                        else{
                           out.println("<SELECT NAME=\"iCveColor\" SIZE=\"1\">");
                           out.println("<option value=\"0\">Datos no disponibles</option>");
                           out.println("</SELECT>");
                        }
                        out.println("</td>");
                       out.println("</tr>");
                       out.println("<tr>");
                       out.print(vEti.EtiCampoCS("EEtiqueta","Núm. Serie:","ECampo","text",50,50,3,"cNumSerie", bs.getFieldValue("cNumSerie","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");
                       out.println("<tr>");
                       out.print(vEti.EtiCampoCS("EEtiqueta","Núm. Motor:","ECampo","text",50,50,3,"cNumMotor", bs.getFieldValue("cNumMotor","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");
                       out.println("<tr>");
                       out.print(vEti.EtiCampoCS("EEtiqueta","Inventario:","ECampo","text",50,50,3,"cInventario", bs.getFieldValue("cInventario","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr>");
                       out.print(vEti.EtiCampo("EEtiqueta","Placas:","ECampo","text",10,10,"cPlacas", bs.getFieldValue("cPlacas","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                       cTemporal = bs!=null?bs.getFieldValue("iKmFinal","0").toString():"0";
                       out.print(vEti.EtiCampo("EEtiqueta","Km. Finales:","ECampo","text",10,10,"iKmFinal",cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("iKmGarantia","&nbsp;").toString():"0";
                       out.print(vEti.EtiCampo("EEtiqueta","Km. Garantía:","ECampo","text",10,10,"iKmGarantia",cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.print(vEti.EtiCampo("EEtiqueta","Mes de Garantía:","ECampo","text",10,10,"iMesGarantia", bs.getFieldValue("iMesGarantia","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");


                       out.println("<tr>");
                       out.println("<td class=\"EEtiqueta\">Mantenimiento a los :</td>");
                       out.println("<td class=\"EEtiquetaL\">");
                       out.println("<input type=\"text\" size=\"8\" maxlength=\"8\" name=\"iKmMantto\" value=\""+bs.getFieldValue("iKmMantto","").toString()+"\"> Km.");
                       out.println("</td>");
                       out.println("<td class=\"EEtiqueta\">o cada:</td>");
                       out.println("<td class=\"EEtiquetaL\">");
                       out.println("<input type=\"text\" size=\"5\" maxlength=\"5\" name=\"iMesMantto\" value=\""+bs.getFieldValue("iMesMantto","").toString()+"\"> meses");
                       out.println("</td>");
                       out.println("</tr>");

                       out.println("<tr>");
                       out.print(vEti.EtiCampoCS("EEtiqueta","Inicio de Mantenimiento:","ECampo","text",10,10,3,"dtIniMantto",bs.getFieldValue("dtIniMantto","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr><td colspan=\"4\" class=\"ETablaT\">Póliza del Seguro</td></tr>");

                       out.println("<tr>");
                       if (bs.getFieldValue("cCobertura","").toString().compareTo("")!=0 && bs.getFieldValue("cCobertura","").toString().compareTo("null")!=0)
                          cTemporal = bs.getFieldValue("cCobertura","").toString();
                       else
                          cTemporal = "&nbsp;";
                       out.print(vEti.EtiAreaTexto("EEtiqueta","Cobertura:","ECampo",40,3,"cCobertura",cTemporal,0,"fMayus(this);","",false,true,lCaptura));
                       if (bs.getFieldValue("cAseguradora","").toString().compareTo("")!=0 && bs.getFieldValue("cAseguradora","").toString().compareTo("null")!=0)
                          cTemporal = bs.getFieldValue("cAseguradora","").toString();
                       else
                          cTemporal = "&nbsp;";
                       out.print(vEti.EtiAreaTexto("EEtiqueta","Aseguradora:","ECampo",40,3,"cAseguradora",cTemporal,0,"fMayus(this);","",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr>");
                       out.print(vEti.EtiCampoCS("EEtiqueta","Póliza:","ECampo","text",50,50,3,"cPoliza", bs.getFieldValue("cPoliza","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr>");
                       out.print(vEti.EtiCampo("EEtiqueta","Inicio Vig:","ECampo","text",10,10,"dtInicioVig", bs.getFieldValue("dtInicioVig","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                       out.print(vEti.EtiCampo("EEtiqueta","Fin Vig:","ECampo","text",10,10,"dtFinVig", bs.getFieldValue("dtFinVig","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr><td colspan=\"4\" class=\"ETablaT\">Situación</td></tr>");

                       out.println("<tr>");
                       out.print(vEti.EtiCampo("EEtiqueta","Fecha de Alta:","ECampo","text",10,10,"dtAlta", bs.getFieldValue("dtAlta","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                        out.println("<td class=\"EEtiqueta\">EDO (Estado):</td>");
                        out.println("<td>");
                        TDVEHEstado dVEHEstado = new TDVEHEstado();
                        TVVEHEstado vVEHEstado = new TVVEHEstado();
                        Vector vEstado = new Vector();
                           vEstado = dVEHEstado.FindByAll();
                        if (vEstado.size()>0)
                           out.print(vEti.SelectOneRowSinTD("iCveEstadoVEH","",vEstado,"iCveEstadoVeh","cDscEstadoVeh",request,bs.getFieldValue("iCveEstadoVEH","0").toString(),true));
                        else{
                           out.println("<SELECT NAME=\"iCveEstadoVEH\" SIZE=\"1\">");
                           out.println("<option value=\"0\">Datos no disponibles</option>");
                           out.println("</SELECT>");
                        }
                        out.println("</td>");
                       out.println("</tr>");

                       out.println("<tr>");
                       out.println("<td class=\"EEtiqueta\">Activo:</td>");
                       if (bs.getFieldValue("lActivo","0").toString().compareTo("0")==0)
                          out.println("<td><input type=\"checkbox\" name=\"lActivo\" value=\"1\" onclick=\"document.forms[0].lBaja.disabled=this.checked;\"></td>");
                       else
                          out.println("<td><input type=\"checkbox\" name=\"lActivo\" value=\"1\" onclick=\"document.forms[0].lBaja.disabled=this.checked;\" checked></td>");
                       out.println("<td class=\"EEtiqueta\">Baja:</td>");
                       if (bs.getFieldValue("lBaja","0").toString().compareTo("0")==0)
                          out.println("<td><input type=\"checkbox\" name=\"lBaja\" value=\"1\" onClick=\"Activar(this.checked);\"></td>");
                       else
                          out.println("<td><input type=\"checkbox\" name=\"lBaja\" value=\"1\" checked onClick=\"Activar(this.checked);\"></td>");
                       out.println("</tr>");

                       out.println("<tr>");
                       out.print(vEti.EtiCampo("EEtiqueta","Fecha de Baja:","ECampo","text",10,10,"dtBaja", bs.getFieldValue("dtBaja","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                        out.println("<td class=\"EEtiqueta\">Motivo de Baja:</td>");
                        out.println("<td>");
                        TDVEHMtvoBaja dVEHMtvoBaja = new TDVEHMtvoBaja();
                        TVVEHMtvoBaja vVEHMtvoBaja = new TVVEHMtvoBaja();
                        Vector vMtvoBaja = new Vector();
                           vMtvoBaja = dVEHMtvoBaja.FindByAll();
                        if (vMtvoBaja.size()>0)
                           out.print(vEti.SelectOneRowSinTD("iCveMtvoBaja","",vMtvoBaja,"iCveMtvoBaja","cDscMtvoBaja",request,bs.getFieldValue("iCveMtvoBaja","0").toString(),true));
                        else{
                           out.println("<SELECT NAME=\"iCveMtvoBajaVEH\" SIZE=\"1\">");
                           out.println("<option value=\"0\">Datos no disponibles</option>");
                           out.println("</SELECT>");
                        }
                        out.println("</td>");
                       out.println("</tr>");
                    }else{
                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("cDscTpoVehiculo","&nbsp;").toString():"0";
                       out.print(vEti.EtiCampo("EEtiqueta","Tipo de Vehículo:","ECampo","text",5,5, "cDscTpoVehiculo",cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       cTemporal = bs!=null?bs.getFieldValue("iCveVehiculo","&nbsp;").toString():"0";
                       out.print(vEti.EtiCampo("EEtiqueta","Vehículo:","ECampo","text",5,5,"iCveVehiculo", cTemporal,0,"","fMayus(this);",false,true,false));
                       out.println("</tr>");

                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("cDscMarca","&nbsp;").toString():"0";
                       out.print(vEti.EtiCampo("EEtiqueta","Marca:","ECampo","text",5,5, "cDscMarca",cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       cTemporal = bs!=null?bs.getFieldValue("cDscModelo","&nbsp;").toString():"0";
                       out.print(vEti.EtiCampo("EEtiqueta","Modelo:","ECampo","text",5,5,"cDscModelo", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("iAnioVeh","&nbsp;").toString():"0";
                       out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",5,5, "iAnioVeh", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       cTemporal = bs!=null?bs.getFieldValue("cDscColor","&nbsp;").toString():"0";
                       out.print(vEti.EtiCampo("EEtiqueta","Color:","ECampo","text",5,5,"cDscColor", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");
                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("cNumSerie","&nbsp;").toString():"0";
                       out.print(vEti.EtiCampoCS("EEtiqueta","Núm. Serie:","ECampo","text",50,50,3,"cNumSerie", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");
                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("cNumMotor","&nbsp;").toString():"0";
                       out.print(vEti.EtiCampoCS("EEtiqueta","Núm. Motor:","ECampo","text",50,50,3,"cNumMotor",cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");
                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("cInventario","&nbsp;").toString():"0";
                       out.print(vEti.EtiCampoCS("EEtiqueta","Inventario:","ECampo","text",50,50,3,"cInventario", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("cPlacas","&nbsp;").toString():"0";
                       out.print(vEti.EtiCampo("EEtiqueta","Placas:","ECampo","text",10,10,"cPlacas", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       cTemporal = bs!=null?bs.getFieldValue("iKmFinal","0").toString():"0";
                       out.print(vEti.EtiCampo("EEtiqueta","Km. Finales:","ECampo","text",10,10,"iKmFinal",df.format(Double.parseDouble(""+cTemporal)),0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       cTemporal = bs!=null?bs.getFieldValue("iKmGarantia","0").toString():"0";
                       out.print(vEti.EtiCampo("EEtiqueta","Km. Garantía:","ECampo","text",10,10,"iKmGarantia",df.format(Double.parseDouble(""+cTemporal)),0,"","fMayus(this);",false,true,lCaptura));
                       out.print(vEti.EtiCampo("EEtiqueta","Mes de Garantía:","ECampo","text",10,10,"iMesGarantia",  bs!=null?bs.getFieldValue("iMesGarantia","").toString():"0",0,"","fMayus(this);",false,true,lCaptura));

                       out.println("<tr>");
                       out.println("<td class=\"EEtiqueta\">Mantenimiento a los :</td>");
                       cTemporal = bs!=null?bs.getFieldValue("iKmMantto","&nbsp;").toString():"&nbsp;";
                       out.println("<td class=\"ECampo\">"+cTemporal+" Km.</td>");
                       out.println("<td class=\"EEtiqueta\">o cada:</td>");
                       cTemporal = bs!=null?bs.getFieldValue("iMesMantto","&nbsp;").toString():"&nbsp;";
                       out.println("<td class=\"ECampo\">"+cTemporal+" meses</td>");
                       out.println("</tr>");

                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("dtIniMantto","&nbsp;").toString():"&nbsp;";
                       out.print(vEti.EtiCampoCS("EEtiqueta","Inicio de Mantenimiento:","ECampo","text",10,10,3,"dtIniMantto",cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr><td colspan=\"4\" class=\"ETablaT\">Póliza del Seguro</td></tr>");

                       out.println("<tr>");
                       if (bs!=null && bs.getFieldValue("cCobertura","").toString().compareTo("")!=0 && bs.getFieldValue("cCobertura","").toString().compareTo("null")!=0)
                          cTemporal = bs.getFieldValue("cCobertura","").toString();
                       else
                          cTemporal = "&nbsp;";
                       out.print(vEti.EtiAreaTexto("EEtiqueta","Cobertura:","ECampo",40,3,"cCobertura",cTemporal,0,"fMayus(this);","",false,true,lCaptura));
                       if (bs!=null && bs.getFieldValue("cAseguradora","").toString().compareTo("")!=0 && bs.getFieldValue("cAseguradora","").toString().compareTo("null")!=0)
                          cTemporal = bs.getFieldValue("cAseguradora","").toString();
                       else
                          cTemporal = "&nbsp;";
                       out.print(vEti.EtiAreaTexto("EEtiqueta","Aseguradora:","ECampo",40,3,"cAseguradora",cTemporal,0,"fMayus(this);","",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("cPoliza","&nbsp;").toString():"&nbsp;";
                       out.print(vEti.EtiCampoCS("EEtiqueta","Póliza:","ECampo","text",50,50,3,"cPoliza",cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("dtInicioVig","&nbsp;").toString():"&nbsp;";
                       out.print(vEti.EtiCampo("EEtiqueta","Inicio Vig:","ECampo","text",10,10,"dtInicioVig", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       cTemporal = bs!=null?bs.getFieldValue("dtFinVig","&nbsp;").toString():"&nbsp;";
                       out.print(vEti.EtiCampo("EEtiqueta","Fin Vig:","ECampo","text",10,10,"dtFinVig", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr><td colspan=\"4\" class=\"ETablaT\">Situación</td></tr>");

                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("dtAlta","&nbsp;").toString():"&nbsp;";
                       out.print(vEti.EtiCampo("EEtiqueta","Fecha de Alta:","ECampo","text",10,10,"dtAlta", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       cTemporal = bs!=null?bs.getFieldValue("cDscEstadoVEH","&nbsp;").toString():"&nbsp;";
                       out.print(vEti.EtiCampo("EEtiqueta","EDO (Estado):","ECampo","text",5,5,"cDscEstadoVEH", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr>");
                       if (bs == null || bs.getFieldValue("lActivo","0").toString().compareTo("0")==0)
                          cTemp = "NO";
                       else
                          cTemp = "SI";

                       out.print(vEti.EtiCampo("EEtiqueta","Activo:","ECampo","text",5,5,"lActivo", cTemp,0,"","fMayus(this);",false,true,lCaptura));
                       if (bs == null || bs.getFieldValue("lBaja","0").toString().compareTo("0")==0)
                          cTemp = "NO";
                       else
                          cTemp = "SI";
                       out.print(vEti.EtiCampo("EEtiqueta","Baja:","ECampo","text",5,5,"lBaja", cTemp,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");

                       out.println("<tr>");
                       cTemporal = bs!=null?bs.getFieldValue("dtBaja","&nbsp;").toString():"&nbsp;";
                       out.print(vEti.EtiCampo("EEtiqueta","Fecha de Baja:","ECampo","text",10,10,"dtBaja", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       cTemporal = bs!=null?bs.getFieldValue("cDscMtvoBaja","&nbsp;").toString():"&nbsp;";
                       out.print(vEti.EtiCampo("EEtiqueta","Motivo de Baja:","ECampo","text",5,5,"cDscMtvoBaja", cTemporal,0,"","fMayus(this);",false,true,lCaptura));
                       out.println("</tr>");
                    }
                }
            %>
          <script language="JavaScript">
          form = document.forms[0];
           if (form.dtBaja)
              form.dtBaja.disabled = !form.lBaja.checked;
           if (form.iCveMtvoBaja)
              form.iCveMtvoBaja.disabled = !form.lBaja.checked;
          </script>
          </table>
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
           <%
            boolean pg070702012  = clsConfig.getLPagina("pg070702012.jsp");
            boolean pg070702013  = clsConfig.getLPagina("pg070702013.jsp");
            boolean pg070702020  = clsConfig.getLPagina("pg070702020.jsp");

            if (bs!=null){
               if (pg070702012){
                  out.print("<td class=\"ECampo\">");
                  out.print(vEti.clsAnclaTexto("EAncla","Ubicaciones","JavaScript:fIrCatalogo('"+bs.getFieldValue("iCveVehiculo","")+"','pg070702012.jsp');","Ir a ubicación..."));
                  out.print("</td>");
               }
               if (pg070702013){
                  out.print("<td class=\"ECampo\">");
                  out.print(vEti.clsAnclaTexto("EAncla","Mantenimientos","JavaScript:fIrCatalogo('"+bs.getFieldValue("iCveVehiculo","")+"','pg070702013.jsp');","Ir a mantenimiento..."));
                  out.print("</td>");
               }
               if (pg070702020){
                  out.print("<td class=\"ECampo\">");
                  out.print(vEti.clsAnclaTexto("EAncla","Ubicar","JavaScript:fIrCatalogo('"+bs.getFieldValue("iCveVehiculo","")+"','pg070702020.jsp');","Ir a asignaciones..."));
                  out.print("</td>");
               }
            }
           %>
          </tr>
          </table>
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
