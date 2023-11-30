<%/**
 * Title: Detalle de las Citas
 * Description: Detalle de las Citas
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. González Paz
 * @version 1.0
 * Clase:CFG
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
  pg070103013CFG  clsConfig = new pg070103013CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070103013.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103013.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";
  TDPais dPaisNac = new TDPais();
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
  TEtiCampo vEti = new TEtiCampo();


  TDPERDatos dPERDatos = new TDPERDatos();
    int Suma = 0;
    int ValidaCurp = 0;

%>

<script language="JavaScript">

/* function (opc,val1,val2,val3,objDes){
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

  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)


  function fIrPagina(){
    form = document.forms[0];
    form.hdiCvePersonal.value = '1';
    form.target = 'FRMDatos';
    form.action = 'SEDetPer.jsp';
    form.submit();
  }



  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }

  function fCURRP(msg){
            alert(msg);
  }

</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>

<head>

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
  <input type="hidden" name="hdICvePersonal">
  <input type="hidden" name="hdICveUniMed">
  <input type="hidden" name="hdICveModulo">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo")%>">
  <input type="hidden" name="dtFecha" value="<%=request.getParameter("dtFecha")%>">
  <input type="hidden" name="cHora" value="<%=request.getParameter("cHora")%>">
  <input type="hidden" name="lValidado" value="false">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
      <tr><td class="ETablaC" valign="top">
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
<%
  boolean lCaptura = clsConfig.getCaptura();
    if(clsConfig.getlValidar() &&
      (clsConfig.getVResValida() != null && clsConfig.getVResValida().size() > 0)){ %>

          <tr>
            <td colspan="10" class="ETablaAyuda">Coincidencias</td>
          </tr>
             <%
               for(int j=0; j < clsConfig.getVResValida().size(); j++){
                 Vector vResultado = (Vector) clsConfig.getVResValida().get(j);
                 TVPERDatos vPERDatos;
                  // Impresión de los títulos
                 if( j == 0)
                   out.println("<tr><td colspan=\"10\" class=\"ETablaT\">MISMO RFC</td></tr>");
                 else if( j == 1)
                   out.println("<tr><td colspan=\"10\" class=\"ETablaT\">MISMO NOMBRE COMPLETO Y FECHA DE NACIMIENTO</td></tr>");
                 else if(j==2)
                     out.println("<tr><td colspan=\"10\" class=\"ETablaT\">MISMO APELLIDO PATERNO Y FECHA DE NACIMIENTO</td></tr>");
                 else
                     out.println("<tr><td colspan=\"10\" class=\"ETablaT\">MISMO APELLIDO MATERNO Y FECHA DE NACIMIENTO</td></tr>");
                 if(vResultado != null && vResultado.size() > 0){
                  // Impresión de encabezados
                  out.println("<tr class='ETablaST'>");
                  out.println(vEti.Texto("ETablaST","&nbsp;&nbsp;"));
                  out.println(vEti.Texto("ETablaST","Expediente"));
                  out.println(vEti.Texto("ETablaST","RFC"));
                  out.println(vEti.Texto("ETablaST\" colspan=\"3","Nombre"));
                  out.println(vEti.Texto("ETablaST","Sexo"));
                  out.println(vEti.Texto("ETablaST","Fecha de Nacimiento"));
                  out.println(vEti.Texto("ETablaST","Lugar de Nacimiento"));
                  out.println(vEti.Texto("ETablaST","Dirección"));
                  out.println("</tr>");
                  // Impresión de los datos
                  for(int i=0; i < vResultado.size(); i++){
                    vPERDatos = (TVPERDatos) vResultado.get(i);
                    out.println("<tr class='EPie'>");
                    out.println("<td class='EPieC'>" + vEti.ObjRadioSE("EPieC","iCvePersonalS",String.valueOf(vPERDatos.getICveExpediente()),"","","","","",0,true,true) + "</td>");
                    out.println(vEti.Texto("ETablaR",String.valueOf(vPERDatos.getICveExpediente())));
                    out.println(vEti.Texto("ETabla",vPERDatos.getCRFC()));
                    out.println(vEti.Texto("ETabla",vPERDatos.getCApPaterno()));
                    out.println(vEti.Texto("ETabla",vPERDatos.getCApMaterno()));
                    out.println(vEti.Texto("ETabla",vPERDatos.getCNombre()));
                    out.println(vEti.Texto("EPieC",vPERDatos.getCSexo()));
                    out.println(vEti.Texto("EPie",vPERDatos.getCDscFecNacimiento()));
                    out.println(vEti.Texto("EPie",vPERDatos.getCDscEstadoNac()));
                    out.println(vEti.Texto("EPie",vPERDatos.getCCalle()));
                    out.println("</tr>");
                  }
                } else
                  out.println("<tr><td colspan=\"10\" class=\"ETablaT\">No se encontraron registros coincidentes</td></tr>");
               } // for
               // Opción Generar Expediente
               out.println("<tr>");
               out.println("<td class='EPieC'>" + vEti.ObjRadioSE("EPieC","iCvePersonalS","0","","","\" Checked \"","","",0,true,true) + "</td>");
               out.println(vEti.TextoCS("EEtiquetaL","Generar nuevo Expediente",9));
               out.println("</tr>");
               out.println("<tr><td colspan=\"10\" class=\"ETablaC\">");
               out.println(vEti.clsAnclaTexto("ETabla","Aceptar","JavaScript:fGenExpediente(true);","Validar la Generación del Expediente","lGenerar"));
               out.println("</td></tr>");
 }
else {
  // Presentar liga de validación
  if(!lCaptura && !clsConfig.getlGenerar() && clsConfig.getICvePersonalA() == 0){
    out.println("<tr><td colspan=\"10\" class=\"EResaltaR\">");
    out.println(vEti.clsAnclaTexto("ETabla","Validar y Generar el expediente","JavaScript:fGenExpediente(false);","Validar la Generación del Expediente","lGenerar"));
    out.println("</td></tr>");
  }
}
%>
         </table>
     </td></tr>

  <tr><td>
  <input type="hidden" name="hdBoton">
  </td><tr></tr><td valign="top">
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
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){

                                }
                                else {
                                  if (bs != null){
                                   if(clsConfig.getICvePersonalA() > 0){

                        //Validando Curp
                              int iCvePersonal;
                               iCvePersonal = Integer.parseInt(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
                               if (iCvePersonal > 0){
                                  try{
                                        Suma = dPERDatos.FindByValida(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
                                        ValidaCurp = dPERDatos.ValidaCURP(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
                                     }catch (Exception ex) {
                                        Suma = 0;
                                        ValidaCurp = 0;
                                     }
                                }

                                     // Imprimir número de expediente generado y las ligas para el puesto y el detalle
                                     out.println("<tr><td class='ETablaAyuda'>Expediente Asignado</td>");
                                     out.println(vEti.Texto("EEtiqueta",bs.getFieldValue("iCvePersonal","&nbsp;").toString()));
                                     out.println("<td class='ETablaC'>");
                                     if(ValidaCurp == 18){
                                     out.print(vEti.clsAnclaTexto("EAncla","Puesto","javascript:fPuesto("+ bs.getFieldValue("iCvePersonal","&nbsp;")+","+ bs.getFieldValue("iCveUniMed","&nbsp;") + "," + bs.getFieldValue("iCveModulo","&nbsp;") + ");","Buscar"));
                                     out.println("</td><td class='ETablaC'>");
                                     }else{
                                     String alerta = "";
                                            if(ValidaCurp == 0)
                                                alerta = "'Para continuar con su examen es obligatorio capturar el CURP,\n esto podrá hacerlo dando clic en detalle.'";
                                            else
                                                alerta= "'Para continuar con su examen es necesario corregir el CURP,\n esto podrá hacerlo dando clic en detalle.'";
                                            out.print(vEti.clsAnclaTexto("EAncla","Puesto",
                                            "javascript:fCURRP("+alerta+");","Buscar")
                                           + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                              out.println("</td><td class='ETablaC'>");
                                     }
                                     //out.print(vEti.clsAnclaTexto("EAncla","Puesto","javascript:fPuesto("+ bs.getFieldValue("iCvePersonal","&nbsp;")+","+ bs.getFieldValue("iCveUniMed","&nbsp;") + "," + bs.getFieldValue("iCveModulo","&nbsp;") + ");","Buscar"));
                                     //out.println("</td><td class='ETablaC'>");
                                     out.print(vEti.clsAnclaTexto("EAncla","Detalle","javascript:fDetalle("+ bs.getFieldValue("iCvePersonal","&nbsp;") +  ");","Buscar"));
                                     out.println("</td></tr>");
                                    }
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Unidad Médica:","ECampo","text",10,10,"iCveUniMed", bs.getFieldValue("cDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Módulo:","ECampo","text",10,10,"iCveModulo", bs.getFieldValue("cDscModulo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
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
                                       // Si no son campos se generan los campos ocultos para realizar las búsquedas
                                       if(!lCaptura){
                                    %>
                                     <input type="hidden" name="cRFC" value="<%=bs.getFieldValue("cRFC","&nbsp;").toString()%>">
                                     <input type="hidden" name="cCURP" value="<%=bs.getFieldValue("cCURP","&nbsp;").toString()%>">

                                     <input type="hidden" name="cLicencia" value="<%=bs.getFieldValue("cLicencia","&nbsp;").toString()%>">
                                     <input type="hidden" name="cLicenciaInt" value="<%=bs.getFieldValue("cLicenciaInt","&nbsp;").toString()%>">

                                     <input type="hidden" name="cApPaterno" value="<%=bs.getFieldValue("cApPaterno","&nbsp;").toString()%>">
                                     <input type="hidden" name="cApMaterno" value="<%=bs.getFieldValue("cApMaterno","&nbsp;").toString()%>">
                                     <input type="hidden" name="cNombre" value="<%=bs.getFieldValue("cNombre","&nbsp;").toString()%>">
                                     <input type="hidden" name="dtNacimiento" value="<%=bs.getFieldValue("cDscDtFechaNac","&nbsp;").toString()%>">
                                    <%
                                       }

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",13,13,"cRFC", bs.getFieldValue("cRFC","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",18,18,"cCURP", bs.getFieldValue("cCURP","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Licencia:","ECampo","text",13,13,"cLicencia", bs.getFieldValue("cLicencia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Licencia Int:","ECampo","text",18,18,"cLicenciaInt", bs.getFieldValue("cLicenciaInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",50,50,"cApPaterno", bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",50,50,"cApMaterno", bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr><tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Nombre(s):","ECampo","text",50,80,"cNombre", bs.getFieldValue("cNombre","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    if (!lCaptura){
                                    out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cGenero","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    }
                                    else{
                                     out.println(vEti.Texto("EEtiqueta","Sexo:"));
                                     if (bs.getFieldValue("cGenero","&nbsp;").toString().compareTo("M") == 0){
                                    %>
                                    <td class="EEtiquetaL">
                                    Masculino<input type="radio" name="cGenero" value="M" checked>
                                    Femenino<input type="radio" name="cGenero" value="F">
                                    </td>
                                    <%
                                     }
                                    else{
                                    %>
                                    <td class="EEtiquetaL">
                                    Masculino<input type="radio" name="cGenero" value="M">
                                    Femenino<input type="radio" name="cGenero" value="F" checked>
                                    </td>
                                    <%
                                     }
                                    }
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","FECNAC (Fecha de Nacimiento):","ECampo","text",10,10,4,"dtNacimiento", bs.getFieldValue("cDscDtFechaNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
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
                                    out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cGenero","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","FECNAC (Fecha de Nacimiento):","ECampo","text",10,10,4,"dtNacimiento", bs.getFieldValue("cDscDtFechaNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",13,13,"cRFC", bs.getFieldValue("cRFC","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",18,18,"cCURP", bs.getFieldValue("cCURP","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Licencia:","ECampo","text",13,13,"cLicencia", bs.getFieldValue("cLicencia","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Licencia Int.:","ECampo","text",18,18,"cLicenciaInt", bs.getFieldValue("cLicenciaInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");

                                    }

                                    out.println("<tr>");
                                    if(!lCaptura){
                                    out.print(vEti.EtiCampo("EEtiqueta","País de Nacimiento:","ECampo","text",10,10,"iCvePaisNac", bs.getFieldValue("cDscPaisNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","EDONAC (Lugar de Nacimiento):","ECampo","text",10,10,"iCveEstadoNac", bs.getFieldValue("cDscEstadoNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    } else {

                                      out.println("<tr>");
                                      out.println(vEti.Texto("EEtiqueta","País de Nacimiento:"));
                                      out.println("<td>");
                                      out.println(vEti.SelectOneRowSinTD("iCvePaisNac","llenaSLT(2,this.value,'','',document.forms[0].iCveEstadoNac);", dPaisNac.FindByAll(), "iCvePais", "cNombre",request, bs.getFieldValue("iCvePaisNac","&nbsp;").toString() , true));
                                      out.println("</td>");
                                      out.println("</td>");
                                      out.println(vEti.Texto("EEtiqueta","EDONAC (Lugar de Nacimiento):"));
                                      out.println("<td class='ECampo'>");
                                      TVEntidadFed vEntidadFed = new TVEntidadFed();
                                      TDEntidadFed dEntidadFed = new TDEntidadFed();
                                      Vector vcEntidadFed = new Vector();
                                      vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePaisNac","&nbsp;").toString());
                                      out.println(vEti.SelectOneRowSinTD("iCveEstadoNac","", vcEntidadFed, "iCveEntidadFed", "cNombre",request, bs.getFieldValue("iCveEstadoNac","&nbsp;").toString() , true));
                                      out.println("</td>");
                                    }

                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Expediente Anterior:","ECampo","text",55,50,4,"cExpediente", bs.getFieldValue("cExpediente","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaSTC","Domicilio",4));
                                    out.println("</tr>");
                                    /*out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Cambio de  Dirección","ECampo","text",10,10,4,"lCambioDir", bs.getFieldValue("lCambioDir","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");*/
                                    /*out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Personal:","ECampo","text",10,10,"iCvePersonal", bs.getFieldValue("iCvePersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");*/
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Calle:","ECampo","text",55,50,4,"cCalle", bs.getFieldValue("cCalle","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Ext.:","ECampo","text",25,30,"cNumExt", bs.getFieldValue("cNumExt","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Int.:","ECampo","text",25,30,"cNumInt", bs.getFieldValue("cNumInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",33,30,"cColonia", bs.getFieldValue("cColonia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","C.P.:","ECampo","text",6,5,"iCP", bs.getFieldValue("iCP","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Ciudad:","ECampo","text",55,50,"cCiudad", bs.getFieldValue("cCiudad","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));

                                    if (!lCaptura){
                                    out.print(vEti.EtiCampo("EEtiqueta","País:","ECampo","text",10,10,"iCvePais", bs.getFieldValue("cDscPais","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","EDO (Estado):","ECampo","text",10,10,"iCveEstado", bs.getFieldValue("cDscEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","MUN (Municipio):","ECampo","text",10,10,"iCveMunicipio", bs.getFieldValue("cDscMunicipio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                     }
                                    else{
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
                                    out.println(vEti.SelectOneRowSinTD("iCveMunicipio","", vcMunicipio, "iCveMunicipio", "cNombre",request, bs.getFieldValue("iCveMunicipio","&nbsp;").toString() , true));
                                    out.println("</tr>");
                                    }

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Tel.:","ECampo","text",10,10,4,"cTel", bs.getFieldValue("cTelefono","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");

                                    out.println(vEti.TextoCS("ETablaSTC","Examen Solicitado",4));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Modo de Transporte:","ECampo","text",10,10,"iCveMdoTrans", bs.getFieldValue("cDscMdoTransporte","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Categoría:","ECampo","text",10,10,"iCveCategoria", bs.getFieldValue("cDscCategoria","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    /*
                                    if(ValidaCurp == 18){
                                     out.print(vEti.clsAnclaTexto("EAncla","Puesto","javascript:fPuesto("+ bs.getFieldValue("iCvePersonal","&nbsp;")+","+ bs.getFieldValue("iCveUniMed","&nbsp;") + "," + bs.getFieldValue("iCveModulo","&nbsp;") + ");","Buscar"));
                                     out.println("</td><td class='ETablaC'>");
                                     }else{
                                     String alerta = "";
                                            if(ValidaCurp == 0)
                                                alerta = "'Para continuar con su examen es obligatorio capturar el CURP,\n esto podrá hacerlo dando clic en detalle.'";
                                            else
                                                alerta= "'Para continuar con su examen es necesario corregir el CURP,\n esto podrá hacerlo dando clic en detalle.'";
                                            out.print(vEti.clsAnclaTexto("EAncla","Puesto2",
                                            "javascript:fCURRP("+alerta+");","Buscar")
                                           + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                              out.println("</tr>");
                                     }*/
                                    //out.print(vEti.EtiCampoCS("EEtiqueta","Puesto:","ECampo","text",10,10,4,"iCvePuesto", bs.getFieldValue("cDscPuesto","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    //out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Observaciones:","ECampo","text",10,10,4,"cObservacion", bs.getFieldValue("cObservacion","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
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
<%= vErrores.muestraError() /* Al final de la página se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
