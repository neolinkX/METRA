<%/**
 * Title: Selector Detalle del Personal
 * Description: Registro de Citas
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
  SEDetPerCFG  clsConfig = new SEDetPerCFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("SEDetPer.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("SEDetPer.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Muestra|";    // modificar
  String cCveOrdenar  = "iCveMuestra|";  // modificar
  String cDscFiltrar  = "Muestra|";    // modificar
  String cCveFiltrar  = "iCveMuestra|";  // modificar
  String cTipoFiltrar = "8|";                // modificar
  boolean lFiltros    = false;                 // modificar
  boolean lIra        = false;                  // modificar
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
%>

<script language="JavaScript">
 function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070102020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)
function fValidaTodo(){
    var form = document.forms[0];
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.iCveUniMed)
        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad Médica:', false);
      if (form.dtFecha)
        cVMsg = cVMsg + fSinValor(form.dtFecha,5,'Fecha:', false);
      if (form.iCveCita)
        cVMsg = cVMsg + fSinValor(form.iCveCita,3,'Cve. Cita:', false);
      if (form.cHora)
        cVMsg = cVMsg + fSinValor(form.cHora,1,'Hora', false);
      if (form.cApPaterno)
        cVMsg = cVMsg + fSinValor(form.cApPaterno,1,'Paterno', false);
      if (form.cApMaterno)
        cVMsg = cVMsg + fSinValor(form.cApMaterno,1,'Materno', false);
      if (form.cNombre)
        cVMsg = cVMsg + fSinValor(form.cNombre,1,'Nombre', false);
      if (form.cGenero)
        cVMsg = cVMsg + fSinValor(form.cGenero,1,'Sexo:', false);
      if (form.dtNacimiento)
        cVMsg = cVMsg + fSinValor(form.dtNacimiento,5,'Fec. Nac.:', false);
      if (form.cRFC)
        cVMsg = cVMsg + fSinValor(form.cRFC,1,'RFC:', false);
      if (form.cCURP)
        cVMsg = cVMsg + fSinValor(form.cCURP,1,'CURP', false);
      if (form.iCvePaisNac)
        cVMsg = cVMsg + fSinValor(form.iCvePaisNac,3,'Pais Nac', false);
      if (form.iCveEstadoNac)
        cVMsg = cVMsg + fSinValor(form.iCveEstadoNac,3,'Edo Nac', false);
      if (form.cExpediente)
        cVMsg = cVMsg + fSinValor(form.cExpediente,1,'Municipio', false);
      if (form.iCvePersonal)
        cVMsg = cVMsg + fSinValor(form.iCvePersonal,3,'Personal:', false);
      if (form.cCalle)
        cVMsg = cVMsg + fSinValor(form.cCalle,1,'Calle', false);
      if (form.cNumExt)
        cVMsg = cVMsg + fSinValor(form.cNumExt,3,'No. Ext', false);
      if (form.cNumInt)
        cVMsg = cVMsg + fSinValor(form.cNumInt,3,'No. Int.', false);
      if (form.cColonia)
        cVMsg = cVMsg + fSinValor(form.cColonia,1,'Colonia', false);
      if (form.iCP)
        cVMsg = cVMsg + fSinValor(form.iCP,3,'C.P.', false);
      if (form.cCiudad)
        cVMsg = cVMsg + fSinValor(form.cCiudad,1,'Ciudad', false);
      if (form.iCvePais)
        cVMsg = cVMsg + fSinValor(form.iCvePais,3,'Pais', false);
      if (form.iCveEstado)
        cVMsg = cVMsg + fSinValor(form.iCveEstado,3,'Edo.', false);
      if (form.iCveMunicipio)
        cVMsg = cVMsg + fSinValor(form.iCveMunicipio,3,'Municipio', false);
      if (form.cTelefono)
        cVMsg = cVMsg + fSinValor(form.cTelefono,1,'Tel.', false);
      if (form.lCambioDir)
        cVMsg = cVMsg + fSinValor(form.lCambioDir,3,'Cambio Dir', false);
      if (form.iCveMdoTrans)
        cVMsg = cVMsg + fSinValor(form.iCveMdoTrans,3,'Mdo Trans', false);
      if (form.iCvePuesto)
        cVMsg = cVMsg + fSinValor(form.iCvePuesto,3,'Puesto', false);
      if (form.cCategoria)
        cVMsg = cVMsg + fSinValor(form.cCategoria,1,'Categoria', false);
      if (form.iCveMotivo)
        cVMsg = cVMsg + fSinValor(form.iCveMotivo,3,'Motivo', false);
      if (form.cObservacion)
        cVMsg = cVMsg + fSinValor(form.cObservacion,1,'Observacion', false);
      if (form.iCveSituacion)
        cVMsg = cVMsg + fSinValor(form.iCveSituacion,3,'Situacion', false);
      if (form.iCveUsuCita)
        cVMsg = cVMsg + fSinValor(form.iCveUsuCita,3,'UsuCita', false);
      if (form.lRealizoExam)
        cVMsg = cVMsg + fSinValor(form.lRealizoExam,3,'Realizo Exam', false);
      if (form.iCveUsuMCIS)
        cVMsg = cVMsg + fSinValor(form.iCveUsuMCIS,3,'usuUMCis', false);
      if (form.lProdNoConf)
        cVMsg = cVMsg + fSinValor(form.lProdNoConf,3,'ProdNoConf', false);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }

 function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}


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
       cClave2  = ""+bs.getFieldValue("iCveMuestra", "");
        }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdAnio" value="<%=iAnio%>">
  <input type="hidden" name="hdPbaRapida" >
  <input type="hidden" name="hdPersonal">
  <input type="hidden" name="hdiCvePersonal">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td>
  <input type="hidden" name="hdBoton">
  </td><td valign="top">

                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                 <tr>
                                 <td colspan="4" class="ETablaT">Detalle del Personal
                                 </td>
                                 </tr>
                                 <tr>

                               <%
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){

                                }
                                else {
                                  if (bs != null){
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("EEtiquetaL","Datos Personal",4));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",10,10,"cApPaterno", bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",10,10,"cApMaterno", bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Nombre(s):","ECampo","text",10,10,"cNombre", bs.getFieldValue("cNombre","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    //out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cSexo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cSexo_DGIS", bs.getFieldValue("cSexo_DGIS","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","FECNAC (Fecha de Nacimiento):","ECampo","text",10,10,4,"cDscFecNacimiento", bs.getFieldValue("cDscFecNacimiento","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",10,10,"cRFC", bs.getFieldValue("cRFC","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",10,10,"cCURP", bs.getFieldValue("cCURP","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","País de Nacimiento:","ECampo","text",10,10,"iCvePaisNac", bs.getFieldValue("cDscPaisNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Lugar de Nacimientos:","ECampo","text",10,10,"iCveEstadoNac", bs.getFieldValue("cDscEstadoNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Expediente Anterior:","ECampo","text",10,10,4,"cExpedAnt", bs.getFieldValue("cExpediente","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Correo Electrónico:","ECampo","text",10,10,"cCorreoElec", bs.getFieldValue("cCorreoElec","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));

                                    if (bs.getFieldValue("lDonadorOrg","&nbsp;").toString().compareTo("1") == 0)
                                       out.print(vEti.EtiCampo("EEtiqueta","Donador de Órganos:","ECampo","text",10,10,"lDonadorOrg","SI",0,"","fMayus(this);",false,true,false));
                                    else
                                       out.print(vEti.EtiCampo("EEtiqueta","Donador de Órganos:","ECampo","text",10,10,"lDonadorOrg","NO",0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Señas Particulares:","ECampo","text",10,10,4,"cSenasPersonal", bs.getFieldValue("cSenasPersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("EEtiquetaL","Domicilio",4));
                                    out.println("</tr>");
                                    /*out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Personal:","ECampo","text",10,10,"iCvePersonal", bs.getFieldValue("iCvePersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");*/
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Calle","ECampo","text",10,10,4,"cCalle", bs.getFieldValue("cCalle","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Ext","ECampo","text",10,10,"cNumExt", bs.getFieldValue("cNumExt","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Int.","ECampo","text",10,10,"cNumInt", bs.getFieldValue("cNumInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Colonia","ECampo","text",10,10,"cColonia", bs.getFieldValue("cColonia","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","C.P.","ECampo","text",10,10,"iCP", bs.getFieldValue("iCP","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Ciudad","ECampo","text",10,10,"cCiudad", bs.getFieldValue("cCiudad","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Pais","ECampo","text",10,10,"iCvePais", bs.getFieldValue("cDscPais","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Edo.","ECampo","text",10,10,"iCveEstado", bs.getFieldValue("cDscEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Municipio","ECampo","text",10,10,"iCveMunicipio", bs.getFieldValue("cDscMunicipio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Tel.","ECampo","text",10,10,4,"cTel", bs.getFieldValue("cTelefono","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("EEtiquetaL","En caso de accidente avisar a:",4));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Nombre:","ECampo","text",10,10,4,"cPersonaAviso", bs.getFieldValue("cPersonaAviso","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Domicilio:","ECampo","text",10,10,4,"cDirecAviso", bs.getFieldValue("cDirecAviso","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Teléfono:","ECampo","text",10,10,4,"cTelAviso", bs.getFieldValue("cTelAviso","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Empresa:","ECampo","text",10,10,4,"cDscEmpresa", bs.getFieldValue("cDscEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.print("<tr><td colspan=\"4\" align=\"center\">");
                                    out.print(vEti.clsAnclaTexto("EEtiqueta","Identificar","javascript:fIdentificar("+ request.getParameter("hdiCvePersonal") +");","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                    out.print(vEti.clsAnclaTexto("EEtiqueta","Cancelar","javascript:window.close();","Buscar"));
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