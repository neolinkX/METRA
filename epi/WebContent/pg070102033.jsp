<%/**
 * Title: Detalle de las Citas
 * Description: Detalle de las Citas
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. Gonzï¿½lez Paz
 * @version 1.0
 * Clase:pg070102020CFG
 */%>

 
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
  pg070102033CFG  clsConfig = new pg070102033CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070102033.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070102033.jsp\" target=\"FRMCuerpo"); // modificar
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
  
  TDPais dPaisNac = new TDPais();
  
%>

<script language="JavaScript">

/* function llenaSLT(opc,val1,val2,val3,objDes){
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
  }*/

  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberï¿½n ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensiï¿½n js (vg. pg0702061.js)
 function fValidaCURP(curp){	
		 
		 if(!curp.value.match(/^[A-Z]{4}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CH|CL|CS|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}$/i)){//AAAA######AAAAAA##
			 alert('La CURP ingresada no es valida.');	    	
		 }
	}

function fValidaTodo(){
    var form = document.forms[0];


   if (form.hdBoton.value == 'Nuevo' || form.hdBoton.value == 'Borrar') {
     alert("Función deshabilitada !")
     return false;
  }


    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.iCveUniMed)
        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad M&eacute;dica:', false);
      if (form.dtFecha)
        cVMsg = cVMsg + fSinValor(form.dtFecha,5,'Fecha:', false);
      if (form.iCveCita)
        cVMsg = cVMsg + fSinValor(form.iCveCita,3,'Cve. Cita:', false);
     // if (form.cHora)
     //   cVMsg = cVMsg + fSinValor(form.cHora,1,'Hora', false);
      if (form.cApPaterno)
        cVMsg = cVMsg + fSinValor(form.cApPaterno,1,'Paterno', false);
      if (form.cApMaterno)
        cVMsg = cVMsg + fSinValor(form.cApMaterno,1,'Materno', false);
      if (form.cNombre)
        cVMsg = cVMsg + fSinValor(form.cNombre,1,'Nombre', false);
      if (form.cGenero)
        cVMsg = cVMsg + fSinValor(form.cGenero,1,'Sexo:', false);
      if (form.dtNacimiento)
        cVMsg = cVMsg + fSinValor(form.dtNacimiento,6,'Fec. Nac.:', false);

      if (form.cRFC){
         cVMsg = cVMsg + fSinValor(form.cRFC,2,'RFC:', true);
         if ( fValRFC(form.cRFC.value,1) == false)
           cVMsg = cVMsg + "\n - El RFC de la Persona Física es Incorrecto. \n";
      }


      //if (form.cRFC)
      //  cVMsg = cVMsg + fSinValor(form.cRFC,1,'RFC:', false);
      if (form.cCURP)
        cVMsg = cVMsg + fSinValor(form.cCURP,2,'CURP', false);

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
          alert("Datos no Validos: \n" + cVMsg);
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

  function fIrPagina(){
    form = document.forms[0];
    form.hdiCvePersonal.value = '1';
    form.target = 'FRMDatos';
    form.action = 'SEDetPer.jsp';
    form.submit();
  }



  // Esta funciï¿½n no debe modificarse
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
       cClave2  = ""+bs.getFieldValue("iCveMuestra", "");
        }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdAnio" value="<%=iAnio%>">
  <input type="hidden" name="hdPbaRapida" >
  <input type="hidden" name="hdPersonal">
  <input type="hidden" name="hdiCvePersonal">
  <input type="hidden" name="hdCveUniMed" value="<%=request.getParameter("hdCveUniMed")%>">
  <input type="hidden" name="hdCveModulo" value="<%=request.getParameter("hdCveModulo")%>">
  <input type="hidden" name="hdFecha" value="<%=request.getParameter("hdFecha")%>">
  <input type="hidden" name="hdCveCita" value="<%=request.getParameter("hdCveCita")%>">

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
                                 <%
                                  // Llamado Provisional a Consulta Detallada de Personal
                                   if (request.getParameter("hdBoton").compareTo("Ventana") == 0){
                                  %>
                                 <script language="JavaScript">
                                       fIrPagina();
                                 </script>
                                 <%
                                   }
                                  %>

                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                 <tr>
                                 <td colspan="4" class="ETablaT">Detalle de las Citas
                                 </td>
                                 </tr>
                                 <tr>

                               <%
                                TCreaBoton bBtn = new TCreaBoton();
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){

                                }
                                else {
                                  if (bs != null){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Unidad M&eacute;dica:","ECampo","text",10,10,"iCveUniMed", bs.getFieldValue("cDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","M&oacute;dulo:","ECampo","text",10,10,"iCveModulo", bs.getFieldValue("cDscModulo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtFecha",bs.getFieldValue("cDscDtFecha","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Hora:","ECampo","text",10,10,"cHora", bs.getFieldValue("cHora","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                   out.println("<tr>");
                                   // if (request.getParameter("hdBoton").compareTo("Modificar") == 0)
                                   //    out.print(vEti.EtiCampoCS("EEtiqueta","Cve. Cita:","ECampo","text",10,10,4,"iCveCita", bs.getFieldValue("iCveCita","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                   // else
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Cve. Cita:","ECampo","text",10,10,4,"iCveCita", bs.getFieldValue("iCveCita","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaSTC","Datos Personal",4));
                                    out.println("</tr>");

                                    int Personal = 0;
                                    Personal = Integer.parseInt(bs.getFieldValue("iCvePersonal","&nbsp;").toString());

                                    if (Personal <= 0){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",50,50,"cApPaterno", bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",50,50,"cApMaterno", bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Nombre(s):","ECampo","text",50,80,"cNombre", bs.getFieldValue("cNombre","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    //out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cGenero","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cSexo_DGIS", bs.getFieldValue("cSexo_DGIS","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    //out.print(vEti.EtiCampoCS("EEtiqueta","FENAC (Fecha de Nacimiento):","ECampo","text",10,10,4,"dtNacimiento", bs.getFieldValue("cDscDtFechaNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampoCSNom024("EEtiqueta","FENAC (Fecha de Nacimiento):","ECampo","text",8,8,4,"dtNacimiento", bs.getFieldValue("cDscDtFechaNacNom024","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",13,13,"cRFC", bs.getFieldValue("cRFC","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",18,18,"cCURP", bs.getFieldValue("cCURP","&nbsp;").toString(),0,"fValidaCURP(this);","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    }
                                    else{
                                     %>
                                      <input type="hidden" name="hdFlag" value="<%=Personal%>">
                                     <%
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",50,50,"cApPaterno", bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",50,50,"cApMaterno", bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Nombre(s):","ECampo","text",50,80,"cNombre", bs.getFieldValue("cNombre","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    //out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cGenero","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cSexo_DGIS", bs.getFieldValue("cSexo_DGIS","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    //out.print(vEti.EtiCampoCS("EEtiqueta","FENAC (Fecha de Nacimiento):","ECampo","text",10,10,4,"dtNacimiento", bs.getFieldValue("cDscDtFechaNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampoCSNom024("EEtiqueta","FENAC (Fecha de Nacimiento):","ECampo","text",8,8,4,"dtNacimiento", bs.getFieldValue("cDscDtFechaNacNom024","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",13,13,"cRFC", bs.getFieldValue("cRFC","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",18,18,"cCURP", bs.getFieldValue("cCURP","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");

                                    }

                                    out.println("<tr>");
                                    //out.print(vEti.EtiCampo("EEtiqueta","NACORIGEN (Nacionalidad):","ECampo","text",10,10,"iCvePaisNac", dPaisNac.FindBySAllNacionalidad(Integer.parseInt(bs.getFieldValue("iCvePaisNac","&nbsp;").toString())),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Pa&iacute;s de Nacimiento:","ECampo","text",10,10,"iCvePaisNac", bs.getFieldValue("cDscPaisNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","EDONAC (Lugar de Nacimiento):","ECampo","text",10,10,"iCveEstadoNac", bs.getFieldValue("cDscEstadoNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Expediente Anterior:","ECampo","text",10,10,4,"cExpedAnt", bs.getFieldValue("cExpediente","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaSTC","Dirección",4));
                                    out.println("</tr>");
                                    /*out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Cambio de  Direcciï¿½n","ECampo","text",10,10,4,"lCambioDir", bs.getFieldValue("lCambioDir","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");*/
                                    /*out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Personal:","ECampo","text",10,10,"iCvePersonal", bs.getFieldValue("iCvePersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");*/
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Calle:","ECampo","text",10,10,4,"cCalle", bs.getFieldValue("cCalle","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Ext.:","ECampo","text",25,25,"cNumExt", bs.getFieldValue("cNumExt","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Int.:","ECampo","text",25,25,"cNumInt", bs.getFieldValue("cNumInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",10,10,"cColonia", bs.getFieldValue("cColonia","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","C.P.:","ECampo","text",10,10,"iCP", bs.getFieldValue("iCP","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Ciudad:","ECampo","text",10,10,"cCiudad", bs.getFieldValue("cCiudad","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Pa&iacute;s:","ECampo","text",10,10,"iCvePais", bs.getFieldValue("cDscPais","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","EDO (Estado):","ECampo","text",10,10,"iCveEstado", bs.getFieldValue("cDscEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","MUN (Municipio):","ECampo","text",10,10,"iCveMunicipio", bs.getFieldValue("cDscMunicipio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    TDPERDireccion dPERDireccion = new TDPERDireccion();
                                    TDGRLLocalidad dLocalidad = new TDGRLLocalidad();
                                    
                                    String Localidad2 = "";
                                    String iCveLocalidad = "";
                                    
										String cWhere = " icvepais = "+bs.getFieldValue("iCvePais","").toString()+
												" and icveentidadfed = "+bs.getFieldValue("iCveEstado","").toString()+
												" and icvemunicipio = "+bs.getFieldValue("iCveMunicipio","").toString()+
												" and icvelocalidad = "+bs.getFieldValue("iCveLocalidad","").toString();
										//System.out.println(cWhere);
										Localidad2 = dLocalidad.UbicaLocalidad(cWhere);
										iCveLocalidad = ""+bs.getFieldValue("iCveLocalidad","").toString();
									
                                    
                                    out.print(vEti.EtiCampo("EEtiqueta","LOC (Población):","ECampo","text",01,10,"iCveLocalidad", Localidad2,0,"","fMayus(this);",false,true,false));
                                    
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Tel.:","ECampo","text",10,10,4,"cTel", bs.getFieldValue("cTelefono","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaSTC","Examen Solicitado",4));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Modo de Transporte:","ECampo","text",10,10,"iCveMdoTrans", bs.getFieldValue("cDscMdoTransporte","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Categor&iacute;a:","ECampo","text",10,10,"iCveCategoria", bs.getFieldValue("cDscCategoria","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Puesto:","ECampo","text",10,10,4,"iCvePuesto", bs.getFieldValue("cDscPuesto","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Observaciones:","ECampo","text",10,10,4,"cObservacion", bs.getFieldValue("cObservacion","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    /*out.println("<tr>");
                                                out.println(vEti.TextoCS("ETablaSTC","Adicionales",4));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Tel2.:","ECampo","text",10,10,4,"cTel", bs.getFieldValue("cTelefono2","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Vivienda:","ECampo","text",10,10,4,"iCveVivienda", bs.getFieldValue("cConcepto","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Discapacidad:","ECampo","text",10,10,4,"iCveDiscapacidad", bs.getFieldValue("cDcsDiscapacidad","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Grupo ï¿½tnico:","ECampo","text",10,10,4,"iCveGpoEtnico", bs.getFieldValue("cGpoEtnico","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Religiones:","ECampo","text",10,10,4,"iCveReligiones", bs.getFieldValue("cCodDsc","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                                out.println("</tr>");                                                
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Tipo Sanguï¿½neo:","ECampo","text",10,10,4,"iCveTpoSangre", bs.getFieldValue("cTpoSangre","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Nivel socioeconï¿½mico:","ECampo","text",10,10,4,"iCveNivelSEC", bs.getFieldValue("cNivelSec","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Afiliaciï¿½n o preferencia <br>por un partido polï¿½tico:","ECampo","text",10,10,4,"iCveParPol", bs.getFieldValue("cDscParPol","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.print(vEti.EtiCampoCS("EEtiqueta","Estado civil:","ECampo","text",10,10,4,"iCveECivil", bs.getFieldValue("cECivil","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                                out.println("</tr>");*/
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