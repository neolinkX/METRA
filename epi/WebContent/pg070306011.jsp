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
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<html>
<%
String accion = request.getParameter("hdBoton");

  pg070306011CFG  clsConfig = new pg070306011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070306011.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306011.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cClaveA    = "";
  String cClaveB    = "";
  String cPosicion  = "";
  String cPropiedad = "";
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  TFechas tf = new TFechas();
  java.sql.Date d = null;
  String fechaFormateada = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No. de Código|Descripción|";    // modificar
  String cCveOrdenar  = "iCodigo|cDscReactivo|";  // modificar
  String cDscFiltrar  = "No. de Código|Descripción|";    // modificar
  String cCveFiltrar  = "iCodigo|cDscReactivo|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
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
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070306011.js)

  // Con esta funcion se obtiene el valor del Checkbox
  function getCheckValue(objeto) {
    return objeto.checked ? objeto.value : 0;
  }

  // Con esta funcion se obtiene el valor del Radio Button
  function getRadioValue(objeto) {
    var iRadio = 0 ;

    for(i=0; i < objeto.length; i++ ) {
      if (objeto[i].checked) {
        iRadio = parseInt(objeto[i].value);
      }
    }

    return iRadio;
  }

  // Con esta funcion se obtiene el valor del Combo
  function getComboValue(objeto) {
    return objeto[objeto.selectedIndex].value;
  }

  // Abrir nueva ventana para generar el reporte de Excel
 function Genera_Doc(tipo){
   form = document.forms[0];
   form.target="_self";

   if (tipo == 1) {
       form.hdReporte.value = 'Inmunoensayo';
       form.hdBoton.value = 'Inmunoensayo';

       cPagina= "<html><head><title>Reporte Excel</title></head>" +
       "  <b>INSTRUCCIONES </b>" +
       " <br> 1.Presione el Botón <b>Generar Excel</b> para obtener el reporte. " +
       " <br> 2.Espere que se presente la ventana <b>Descarga de Archivos</b>." +
       " <br> 3.Presione el botón Abrir y aguarde un momento para que el archivo sea presentado.  " +
       " <body>"+
       "<form method=\"post\" action=\"servXLSpg070306011?hdCampoClave1=" + form.hdCampoClave1.value +
                                      "&hdCampoClave2=" + form.hdCampoClave2.value +
                                      "&iCveLaboratorio=" + form.iCveLaboratorio.value +
                                      "&iAnio=" + form.iAnio.value +
                                      "&iAnioSelect=" + getComboValue(form.iAnioSelect) +
                                      "&iCveUniMed=" + getComboValue(form.iCveUniMed) +
                                      "&hdReporte=" + form.hdReporte.value +
                                      "&iCveReactivo=" + form.iCveReactivo.value +
                                      "\"" +
       " enctype=\"multipart/form-data\">" +
       " <input type=\"submit\" value=\"Generar Excel\"> " +
       " </form>" +
       " <br> Favor de esperar a que se presente el archivo de Excel ...  " +
       " </body>" +
       "</html>";
       wExp = window.open("", "", "width=600,height=200,status=no,resizable=yes,menubar=yes,titlebar=yes,top=200,left=200,screenY=200,screenX=200,scrollbars=yes");
       wExp.document.write(cPagina);
   } else {
       form.hdReporte.value = 'Cromatografia';
       form.hdBoton.value = 'Cromatografia';

       cPagina= "<html><head><title>Reporte Excel</title></head>" +
       "  <b>INSTRUCCIONES </b>" +
       " <br> 1.Presione el Botón <b>Generar Excel</b> para obtener el reporte. " +
       " <br> 2.Espere que se presente la ventana <b>Descarga de Archivos</b>." +
       " <br> 3.Presione el botón Abrir y aguarde un momento para que el archivo sea presentado.  " +
       " <body>"+
       "<form method=\"post\" action=\"servXLSpg070306011?hdCampoClave1=" + form.hdCampoClave1.value +
                                      "&hdCampoClave2=" + form.hdCampoClave2.value +
                                      "&iCveLaboratorio=" + form.iCveLaboratorio.value +
                                      "&iAnio=" + form.iAnio.value +
                                      "&iAnioSelect=" + getComboValue(form.iAnioSelect) +
                                      "&iCveUniMed=" + getComboValue(form.iCveUniMed) +
                                      "&hdReporte=" + form.hdReporte.value +
                                      "&iCveReactivo=" + form.iCveReactivo.value +
                                      "\"" +
       " enctype=\"multipart/form-data\">" +
       " <input type=\"submit\" value=\"Generar Excel\"> " +
       " </form>" +
       " <br> Favor de esperar a que se presente el archivo de Excel ...  " +
       " </body>" +
       "</html>";
       wExp = window.open("", "", "width=600,height=200,status=no,resizable=yes,menubar=yes,titlebar=yes,top=200,left=200,screenY=200,screenX=200,scrollbars=yes");
       wExp.document.write(cPagina);
   }

   //form.hdBoton.value = 'Actual';
   //form.submit();
}

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');


    //fChgTog();
  }
</script>

<script type="text/javascript">

  function Pinta(esto,id)
   {    
    form = document.forms[0];
     if(form.Aukera2.selectedIndex==1)
	{	id=document.getElementById(id);
		id.value="BIOCHIPS";	
        }

     if(form.Aukera2.selectedIndex==2)
	{	id=document.getElementById(id);
		id.value="CONTROL";	
        }

     if(form.Aukera2.selectedIndex==3)
	{	id=document.getElementById(id);
		id.value="CALIBRADOR";	
        }

     if(form.Aukera2.selectedIndex==4)
	{	id=document.getElementById(id);
		id.value="";
        }
   }

   
  function Block(esto,id)
   {    
   form = document.forms[0];
   if(form.Aukera2.selectedIndex==4)
     {
		id=document.getElementById(id);
                document.forms[0].cDscBreve.style.visibility = 'visible';
		id.focus();	
	 }	
	else
	 {
		id=document.getElementById(id);
                document.forms[0].cDscBreve.style.visibility = 'hidden';
                id.focus();	
	 }
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
        cClave2  = bs.getFieldValue("iCveReactivo").toString();
        cClaveA  = bs.getFieldValue("iCveLaboratorio").toString();
        cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
/*        if (accion.equals("Guardar")){
           if (bs.getFieldValue("iCveLaboratorio")!=null)
              cClaveA  = bs.getFieldValue("iCveLaboratorio").toString();
        }
        else
           cClaveA  = request.getParameter("hdCampoClave1");

        if (accion.equals("Guardar")){
           if (bs.getFieldValue("iCveReactivo")!=null)
              cClaveB  = bs.getFieldValue("iCveReactivo").toString();
        }else
           cClaveB  = request.getParameter("hdCampoClave2");
           */
     }

  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCampoClave1" value="<%=cClaveA%>">
  <input type="hidden" name="hdCampoClave2" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">

  <input type="hidden" name="hdReporte">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
      /*
     	if (request.getParameter("hdReporte") != null){
            if(request.getParameter("hdReporte").compareTo("Inmunoensayo") ==0) {
        	out.println(clsConfig.getActiveX());
            }

            if(request.getParameter("hdReporte").compareTo("Cromatografia") ==0) {
        	out.println(clsConfig.getActiveX());
            }
        }
        */
  %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">

          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="8">Filtrar</td>
            </tr>
            <tr>
              <td class="EEtiqueta">Año:</td>
              <td class="ETabla">
                 <select name="iAnioSelect" size="1" onChange="fCambioA();">
                    <%

                                TEtiCampo vEti = new TEtiCampo();
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                Vector vcPersonal = null;



                       for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                          if (request.getParameter("iAnioSelect")!=null&&request.getParameter("iAnioSelect").compareToIgnoreCase(new Integer(i).toString())==0)
                             out.print("<option value = " + i + " selected>" + i + "</option>");
                          else
                             out.print("<option value = " + i + ">" + i + "</option>");
                       }
                    %>
                 </select>
              </td>
              <td class="EEtiqueta ">Laboratorio:</td>
              <td class="ETabla">
                  <%  //TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                      int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                      out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0"));
                  %>
              </td>
            </tr>
          </table>

           &nbsp;

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                          <tr>
                            <%
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();

                                   if (lNuevo) {

                            %>
                            <tr><td class="ETablaT" colspan="6">Reactivo</td></tr>
                              <tr><td class="EEtiqueta" colspan="1">Laboratorio:</td><td class="ECampo">

                              <%
                              //int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                int iUniMed = 0;
                                out.print(vEti.SelectOneRowSinTD("iCveLaboratorio","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,(lNuevo==true||accion.equals("Primero")||accion.equals("Borrar"))?"":bs.getFieldValue("iCveLaboratorio","&nbsp;").toString())); //
                                if(request.getParameter("iCveLaboratorio") == null){
                                   vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                   if(vcPersonal.size() != 0)
                                     iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                }else{
                                     iUniMed = Integer.parseInt(request.getParameter("iCveLaboratorio"),10);
                                }
                                vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
                              %></td>
                            </tr>
                            <%


                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "No. de código:", "ECampo", "text", 20, 20, "iCodigo", "", 0, "", "fMayus(this);", false,true,lCaptura, request));
                                     out.print(vEti.Texto("EEtiqueta","Tipo de reactivo:"));
                                     out.print(vEti.SelectOneRow("ECampo", "iCveTpoReact", "", new TDTOXTpoReact().FindByAll("where lActivo = 1 order by cDscTpoReact"), "iCveTpoReact","cDscTpoReact", request, ""));
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampoCS("EEtiqueta", "Descripción:", "ECampo", "text", 50,100, 3, "cDscReactivo", "", 0, "","fMayus(this);", false, true, lCaptura, request));
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     String htmlcodigo = "Breve:<td><select id=Aukera2 value=Cambiar  onclick=Pinta(this,'cDscBreve');> <option value=1>Seleccione...</option> <option value=BIOCHIPS>BIOCHIPS</option> <option value=CONTROL>CONTROL</option> <option value=CALIBRADOR>CALIBRADOR</option> <option value=OTRO>OTRO</option></select>";
                                     out.print(vEti.EtiCampoCS("EEtiqueta", htmlcodigo, "ECampo", "text", 50, 100, 3,"cDscBreve", "", 0, "", "fMayus(this);", false, true,lCaptura, request));
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.Texto("EEtiqueta","Marca:"));
                                     out.print(vEti.SelectOneRow("ECampo", "iCveMarcaSust", "", new TDTOXMarcaSust().FindByAll("where lActivo = 1 order by cDscMarcaSust"), "iCveMarcaSust","cDscMarcaSust", request, ""));
      //                             out.print(vEti.Texto("EEtiqueta","Utilizado en:"));
      //                             Vector vcLCuantCual = new Vector();
      //                             vcLCuantCual.add("0|CG/EM");
      //                             vcLCuantCual.add("1|INMUNOENSAYO");
      //                             out.print(vEti.SelectOneRow("ECampo", "lCuantCual", "", vcLCuantCual, request, "0"));
                                     out.println(vEti.Texto("EEtiqueta","Utilizado en:"));
                                     out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lCual\" Value=\"1\">InmunoEnsayo");
                                     out.println("<input type=\"checkbox\" name=\"lCuantCual\" value=\"1\">CG/EM</td>");
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "Diferencia entre negativo y corte:","ECampo", "text", 13, 13, "dCorteNeg", "", 0,"", "fMayus(this);", false, true, lCaptura, request));
                                     out.print(vEti.EtiCampo("EEtiqueta", "Diferencia entre positivo y corte:","ECampo", "text", 13, 13, "dCortePost", "", 0,"", "fMayus(this);", false, true, lCaptura, request));

                                     /* se agregaron los siguiente campos Origen*/

                                     out.println("</tr><tr><td class=\"ETablaT\" colspan=\"4\">Origen</td></tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "A partir de:", "ECampo", "text", 25, 100,"cPreparadoDe", "", 0, "", "fMayus(this);", false, true,lCaptura, request));
                                     out.println(vEti.EtiSelectOneRowCS("EEtiqueta","Droga:","ECampo","iCveSustancia","0","",1, new TDTOXSustancia().FindByAll(" where lActivo = 1", " order by cDscSustancia"), "iCveSustancia","cDscSustancia", request,"-1",true,true,lCaptura));
                                     out.println("</tr><tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","N&uacute;mero de lote:","ECampo","text",50,100,"cNumLote","",0,"fMayus(this);","",true,true,lCaptura,request));
                                     out.println(vEti.EtiCampo("EEtiqueta","N&uacute;mero de Cat&aacute;logo:","ECampo","text",50,100,"cNumCatalogo","",0,"fMayus(this);","",true,true,lCaptura,request));
                                     out.println("</tr><tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n:","ECampo","text",10,7,"dConcentracion","",0,"fMayus(this);","",true,true,lCaptura,request));
                                     out.println(vEti.EtiCampo("EEtiqueta","Fecha de Caducidad:","ECampo","text",10,8,"dtCaducAmp","",0,"fMayus(this);","",true,true,lCaptura,request));
                                     out.println("</tr><tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","Proveedor:","ECampo","text",50,100,"cProveedor","",0,"fMayus(this);","",true,true,lCaptura,request));
                                     // La sig. linea la comenté porque no existe en la base de datos
                                     // Rafael Alcocer Caldera Corregido 05/Oct/2006
                                     //out.println(vEti.EtiAreaTexto("EEtiqueta","Observaci&oacute;n","ETabla",40,5,"cObservaAmp","",0,"fMayus(this);","",true,true,lCaptura,request));

                                     /* termina los campos del origen */

                                     out.println("</tr><tr><td class=\"ETablaT\" colspan=\"4\">Preparación</td></tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10,"dtPreparacion", "", 0, "", "fValFecha(this.value);", false, true,lCaptura, request));
                                     out.println(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n Te&oacute;rica:","ECampo","text",10,7,"dConcentTeor","",0,"fMayus(this);","",true,true,lCaptura,request));
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "Volumen (mililitros):", "ECampo", "text", 9, 9,"dVolumen", "", 0, "", "fMayus(this);", false, true,lCaptura, request));
                                     out.print(vEti.EtiCampo("EEtiqueta", "No. de viales:", "ECampo", "text", 4, 4,"iViales", "", 0, "", "fMayus(this);", false, true,lCaptura, request));
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.Texto("EEtiqueta","Responsable:"));
                                     out.println("<td colspan=\"3\">");
                                     out.print(vEti.SelectOneRowSinTD("iCveUsuPrepara", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, ""));
                                     out.println("</td>");
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampoCS("EEtiqueta", "Caducidad:", "ECampo", "text", 10, 10, 3,"dtCaducidad", "", 0, "", "fValFecha(this.value);", false, true,lCaptura, request));
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "Autorizado:", "ECampo", "text", 10, 10,"dtAutoriza", "", 0, "", "fValFecha(this.value);", false, true,lCaptura, request));
                                     out.print(vEti.Texto("EEtiqueta","Por:"));
                                     out.print(vEti.SelectOneRow("ECampo", "iCveUsuAutoriza", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "0"));
                                     out.println("</tr><input type=\"hidden\" name=\"iNoLetras\" value=\"\" size=\"4\" >");
                                     out.println("<tr><td class=\"EEtiqueta\">Observaci&oacute;n:</td><td class=\"ECampo\" colspan=\"3\"><textarea cols=\"100\" rows=\"4\"  name=\"cObservacion\"");
                                     out.println("value=\"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\"></textarea></td>");

                                     /* parte de las valoraciones */

                                     out.println("</tr><tr><td class=\"ETablaT\" colspan=\"4\">Valoraciones</td></tr>");
                                     out.println("</tr><tr><td class=\"ETablaST\" colspan=\"4\">Primer Valoraci&oacute;n</td></tr>");
                                     out.println("<tr>");
                                     // Rafael Alcocer Caldera Corregido 05/Oct/2006 dConcentExp1 --> dConcentExper1
                                     out.println(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n Experimental:","ECampo","text",10,7,"dConcentExper1","",0,"fMayus(this);","",true,true,lCaptura,request));
                                     out.println(vEti.EtiCampo("EEtiqueta","Fecha de Caducidad:","ECampo","text",10,8,"dtValoracion1","",0,"fMayus(this);","",true,true,lCaptura,request));
                                     out.println("</tr><tr>");
                                     out.println(vEti.EtiSelectOneRowCS("EEtiqueta","Equipo:","ECampo","iCveEquipoE1","0","",3, new TDTOXEquipo().FindByAll(" where lCuantCual = 1 ", " order by cCveEquipo "), "iCveEquipo","cCveEquipo", request,"-1",true,true,lCaptura));
                                     out.println("</tr>");
                                     out.println("</tr><tr><td class=\"ETablaST\" colspan=\"4\">Segunda Valoraci&oacute;n</td></tr>");
                                     out.println("<tr>");
                                     // Rafael Alcocer Caldera Corregido 05/Oct/2006 dConcentExp2 --> dConcentExper2
                                     out.println(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n Experimental:","ECampo","text",10,7,"dConcentExper2","",0,"fMayus(this);","",true,true,lCaptura,request));
                                     out.println(vEti.EtiCampo("EEtiqueta","Fecha de Caducidad:","ECampo","text",10,8,"dtValoracion2","",0,"fMayus(this);","",true,true,lCaptura,request));
                                     out.println("</tr><tr>");
                                     out.println(vEti.EtiSelectOneRowCS("EEtiqueta","Equipo:","ECampo","iCveEquipoE2","0","",3, new TDTOXEquipo().FindByAll(" where lCuantCual = 1 ", " order by cCveEquipo "), "iCveEquipo","cCveEquipo", request,"-1",true,true,lCaptura));
                                     out.println("</tr>");

                                     /*termina la parte de valoraciones*/

                                     out.println("<tr><td class=\"ETablaT\" colspan=\"4\">Situación</td></tr><tr>");
                                     out.print(vEti.EtiToggle("EEtiqueta","Agotado:","ECampo","lAgotado","","fChgTog()",0,true,"1","0",true));
                                     out.print(vEti.EtiToggle("EEtiqueta","Baja:","ECampo","lBaja","","",0,true,"1","0",false));
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10, 3, "dtAgotado","", 0, "", "fValFecha(this.value);", false, true,lCaptura));
                                     out.println("</tr>");




                                     if(clsConfig.getAccion().compareToIgnoreCase("Imprime Documentacion")==0) {
                                        out.println(clsConfig.getActiveX());
                                     }

                                   } else {if (bs != null) {

boolean showCombo = accion.equals("Modificar")?true:false;

 %><input type="hidden" name="iCveReactivo" value="<%=bs.getFieldValue("iCveReactivo","nada")%>">
                            <tr><td class="ETablaT" colspan="6">Reactivo</td></tr>
                              <td class="EEtiqueta">Laboratorio:</td>

                              <%//int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                int iUniMed = 0;
%>
                              <td class="ECampo" colspan="3">

<%
if (accion.equals("Modificar")) {
                                out.print(vEti.SelectOneRowSinTD("iCveLaboratorio","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,(lNuevo==true||accion.equals("Primero")||accion.equals("Borrar"))?"":bs.getFieldValue("iCveLaboratorio","&nbsp;").toString())); //
} else {
Vector v = vUsuario.getVUniFiltro(iCveProceso);
String uni = "";
for (int i=0; i<v.size(); i++) {
   TVGRLUMUsuario x = (TVGRLUMUsuario)v.elementAt(i);
   int cl = x.getICveUniMed();
   uni = x.getCDscUniMed();
   if ((""+cl).equals(bs.getFieldValue("iCveLaboratorio","&nbsp;").toString())){
      break;
   }
}
                                out.print(uni);
                                out.println("<input type=\"hidden\" name=\"iCveLaboratorio\" value=\""+""+bs.getFieldValue("iCveLaboratorio","")+"\">");

}
                                if(request.getParameter("iCveLaboratorio") == null){
                                   vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                   if(vcPersonal.size() != 0)
                                     iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                }else{
                                     iUniMed = Integer.parseInt(request.getParameter("iCveLaboratorio"),10);
                                }
                                vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
%>
</td>

<%
                                     out.println("<tr>");
                                     out.println("<input type=\"hidden\" name=\"iAnio\" value=\""+""+bs.getFieldValue("iAnio","")+"\">");
                                     out.print(vEti.EtiCampo("EEtiqueta", "No. de código:", "ECampo", "text", 20, 20, "iCodigo", ""+bs.getFieldValue("iCodigo","&nbsp;"), 0, "", "fMayus(this);", false, true,lCaptura, request));
                                     out.println("<input type=\"hidden\" name=\"iCodigoX\" value=\""+""+bs.getFieldValue("iCodigo","-1")+"\">");
//                                     out.print(vEti.Texto("EEtiqueta","Tipo de Reactivo"));
//                                     out.print(vEti.SelectOneRow("ECampo", "iCveTpoReact", "", new TDTOXTpoReact().FindByAll("where lActivo = 1"), "iCveTpoReact","cDscTpoReact", request, ""+bs.getFieldValue("iCveTpoReact","&nbsp;"))); // ultimo campo, para que tome el actual del registro
if (showCombo) {
                                     out.print(vEti.Texto("EEtiqueta","Tipo de reactivo:"));
                                     out.print(vEti.SelectOneRow("ECampo", "iCveTpoReact", "", new TDTOXTpoReact().FindByAll("where lActivo = 1 order by cDscTpoReact"), "iCveTpoReact","cDscTpoReact", request, bs.getFieldValue("iCveTpoReact","&nbsp;").toString()));
} else {
                                     out.print(vEti.EtiCampo("EEtiqueta", "Tipo de reactivo:","ECampo", "text", 13, 13, "cDscTpoReact", ""+bs.getFieldValue("cDscTpoReact","&nbsp;"), 0,"", "fMayus(this);", false, true, lCaptura, request));
                                     out.println("<input type=\"hidden\" name=\"iCveTpoReact\" value=\""+""+bs.getFieldValue("iCveTpoReact","&nbsp;")+"\">");
}
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampoCS("EEtiqueta", "Descripción:", "ECampo", "text", 50,100, 3, "cDscReactivo", ""+bs.getFieldValue("cDscReactivo","&nbsp;"), 0, "","fMayus(this);", false, true, lCaptura, request));
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampoCS("EEtiqueta", "Breve:", "ECampo", "text", 50, 100, 3,"cDscBreve", ""+bs.getFieldValue("cDscBreve","&nbsp;"), 0, "", "fMayus(this);", false, true,lCaptura, request));
                                     out.println("</tr>");
                                     out.println("<tr>");
//                                     out.print(vEti.Texto("EEtiqueta","Marca"));
//                                     out.print(vEti.SelectOneRow("ECampo", "iCveMarcaSust", "", new TDTOXMarcaSust().FindByAll("where lActivo = 1"), "iCveMarcaSust","cDscMarcaSust", request, ""+bs.getFieldValue("iCveMarcaSust","&nbsp;")));
                                     if (showCombo) {
                                        out.print(vEti.Texto("EEtiqueta","Marca:"));
                                        out.print(vEti.SelectOneRow("ECampo", "iCveMarcaSust", "", new TDTOXMarcaSust().FindByAll("where lActivo = 1 order by cDscMarcaSust"), "iCveMarcaSust","cDscMarcaSust", request, ""+bs.getFieldValue("iCveMarcaSust","&nbsp;")));
                                     } else {
                                        out.print(vEti.EtiCampo("EEtiqueta", "Marca:","ECampo", "text", 13, 13, "cDscMarcaSust", ""+bs.getFieldValue("cDscMarcaSust","&nbsp;"), 0,"", "fMayus(this);", false, true, lCaptura, request));
                                        out.println("<input type=\"hidden\" name=\"iCveMarcaSust\" value=\""+""+bs.getFieldValue("iCveMarcaSust","&nbsp;")+"\">");
                                     }

//************** habilitar al tener el campo correcto
//String cuantCual = bs.getFieldValue("lCuantCual","&nbsp;").toString().equals("1")?"INMUNOENSAYO":"CG/EM";
//***************************************************
//if (showCombo) {
//                                     out.print(vEti.Texto("EEtiqueta","Utilizado en:"));
//                                     Vector vcLCuantCual = new Vector();
 //                                    vcLCuantCual.add("0|CG/EM");
   //                                  vcLCuantCual.add("1|INMUNOENSAYO");
     //                                out.print(vEti.SelectOneRow("ECampo", "lCuantCual", "", vcLCuantCual, request, bs.getFieldValue("lCuantCual","&nbsp;").toString())); //bs.getFieldValue("lCuantcual","&nbsp;").toString()
//} else {
  //                                   out.print(vEti.EtiCampo("EEtiqueta", "Utilizado en:","ECampo", "text", 13, 13, "cDscTpoReact", cuantCual, 0,"", "fMayus(this);", false, true, lCaptura, request));
//}
//
                                     if (lCaptura) {
                                         out.println(vEti.Texto("EEtiqueta","Utilizado en:"));
                                         String cChecked = "checked";

                                          if (bs.getFieldValue("lCual","0").toString().compareToIgnoreCase("1")==0)
                                             out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lCual\" Value=\"1\" " + cChecked +  ">InmunoEnsayo");
                                          else
                                             out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lCual\" Value=\"1\">InmunoEnsayo");

                                          if (bs.getFieldValue("lCuantCual","0").toString().compareToIgnoreCase("1")==0)
                                             out.println("<input type=\"checkbox\" name=\"lCuantCual\" Value=\"1\" "+ cChecked + ">CG/EM</td>");
                                          else
                                             out.println("<input type=\"checkbox\" name=\"lCuantCual\" Value=\"1\" " + ">CG/EM</td>");

                                     } else {
                                         String cDisable = "disabled";
                                         String cChecked = "checked";

                                         out.println(vEti.Texto("EEtiqueta","Utilizado en:"));

                                          if (bs.getFieldValue("lCual","0").toString().compareToIgnoreCase("1")==0)
                                             out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lCual\" Value=\"1\" disabled " + cChecked +  ">InmunoEnsayo");
                                          else
                                             out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lCual\" Value=\"1\" disabled >InmunoEnsayo");

                                          if (bs.getFieldValue("lCuantCual","0").toString().compareToIgnoreCase("1")==0)
                                             out.println("<input type=\"checkbox\" name=\"lCuantCual\" Value=\"1\" disabled "+ cChecked + ">CG/EM</td>");
                                          else
                                             out.println("<input type=\"checkbox\" name=\"lCuantCual\" Value=\"1\" disabled " + ">CG/EM</td>");
                                     }


                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "Diferencia entre negativo y corte:","ECampo", "text", 13, 13, "dCorteNeg", ""+bs.getFieldValue("dCorteNeg","&nbsp;"), 0,"", "fMayus(this);", false, true, lCaptura, request));
                                     out.print(vEti.EtiCampo("EEtiqueta", "Diferencia entre positivo y corte:","ECampo", "text", 13, 13, "dCortePost", ""+bs.getFieldValue("dCortePost","&nbsp;"), 0,"", "fMayus(this);", false, true, lCaptura, request));

                                     /* la parte de Origen (en modificar) */
                                     out.println("</tr><tr><td class=\"ETablaT\" colspan=\"4\">Origen</td></tr>");

                                     //Campo A partir de que me obtiene datos.
                                     out.print(vEti.EtiCampo("EEtiqueta", "A partir de:", "ECampo", "text", 25, 100,"cPreparadoDe", ""+bs.getFieldValue("cPreparadoDe", ""), 0, "", "fMayus(this);", false, true,lCaptura, request));
                                     // Rafael Alcocer Caldera Corregido 05/Oct/2006 "0" --> "" + bs.getFieldValue("cDscSustancia", "")
                                     out.println(vEti.EtiSelectOneRowCS("EEtiqueta","Droga:","ECampo","iCveSustancia","" + bs.getFieldValue("cDscSustancia", ""),"",1, new TDTOXSustancia().FindByAll(" where lActivo = 1", " order by cDscSustancia"), "iCveSustancia","cDscSustancia", request,"-1",true,true,lCaptura));
                                     out.println("</tr><tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","N&uacute;mero de lote:","ECampo","text",50,100,"cNumLote",""+bs.getFieldValue("cNumLote",""),0,"fMayus(this);","",false,true,lCaptura,request));
                                     out.println(vEti.EtiCampo("EEtiqueta","N&uacute;mero de Cat&aacute;logo:","ECampo","text",50,100,"cNumCatalogo",""+bs.getFieldValue("cNumCatalogo", ""),0,"fMayus(this);","",false,true,lCaptura,request));
                                     out.println("</tr><tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n:","ECampo","text",10,7,"dConcentracion",""+bs.getFieldValue("dConcentracion","").toString(),0,"fMayus(this);","",false,true,lCaptura,request));

                                     // dtCaducAmp con formato dd/mm/aaaa

                                     if (bs.getFieldValue("dtCaducAmp","")==null ||
                                         bs.getFieldValue("dtCaducAmp","").equals("null") ||
                                         // Agregado por Rafael Alcocer Caldera 22/Sep/2006
                                         // De no considerar esta validacion envia un error
                                         bs.getFieldValue("dtCaducAmp","").equals("")) {
                                       fechaFormateada = "";
                                     } else {
                                       d = tf.getSQLDatefromSQLString(bs.getFieldValue("dtCaducAmp","").toString());
                                       fechaFormateada = sdf.format(d);
                                     }

                                     out.println(vEti.EtiCampo("EEtiqueta","Fecha de Caducidad:","ECampo","text",10,8,"dtCaducAmp", fechaFormateada,0,"fMayus(this);","",false,true,lCaptura,request));
                                     out.println("</tr><tr>");
                                     out.println(vEti.EtiCampo("EEtiqueta","Proveedor:","ECampo","text",50,100,"cProveedor",""+bs.getFieldValue("cProveedor",""),0,"fMayus(this);","",false,true,lCaptura,request));
                                     // La sig. linea la comenté porque no existe en la base de datos
                                     // Rafael Alcocer Caldera Corregido 05/Oct/2006
                                     //out.println(vEti.EtiAreaTexto("EEtiqueta","Observaci&oacute;n","ETabla",40,5,"cObservaAmp",""+bs.getFieldValue("cObservaAmp", ""),0,"fMayus(this);","",false,true,lCaptura,request));

                                     out.println("</tr><tr><td class=\"ETablaT\" colspan=\"4\">Preparación</td></tr>");
                                     out.println("<tr>");

                                     // dtPreparacion con formato dd/mm/aaaa

                                     if (bs.getFieldValue("dtPreparacion","")==null ||
                                         bs.getFieldValue("dtPreparacion","").equals("null") ||
                                         // Agregado por Rafael Alcocer Caldera 22/Sep/2006
                                         // De no considerar esta validacion envia un error
                                         bs.getFieldValue("dtPreparacion","").equals("")) {
                                       fechaFormateada = "";
                                     } else {
                                       d = tf.getSQLDatefromSQLString(bs.getFieldValue("dtPreparacion","").toString());
                                       fechaFormateada = sdf.format(d);
                                     }

                                     out.print(vEti.EtiCampo("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10,"dtPreparacion", fechaFormateada , 0, "", "fValFecha(this.value);", false, true,lCaptura, request));
                                     out.println(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n Te&oacute;rica:","ECampo","text",10,7,"dConcentTeor",""+bs.getFieldValue("dConcentTeor",""),0,"fMayus(this);","",false,true,lCaptura,request));
                                     out.println("</tr>");


                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "Volumen (mililitros):", "ECampo", "text", 9, 9,"dVolumen", ""+bs.getFieldValue("dVolumen","&nbsp;"), 0, "", "fMayus(this);", false, true,lCaptura, request));
                                     out.print(vEti.EtiCampo("EEtiqueta", "No. de viales:", "ECampo", "text", 4, 4,"iViales", ""+bs.getFieldValue("iViales","&nbsp;"), 0, "", "fMayus(this);", false, true,lCaptura, request));
                                     out.println("</tr>");
                                     out.println("<tr>");
if (showCombo) {
                                     out.print(vEti.Texto("EEtiqueta","Responsable:"));
                                     out.println("<td colspan=\"3\">");
                                     out.print(vEti.SelectOneRowSinTD("iCveUsuPrepara", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, ""+bs.getFieldValue("iCveUsuario","&nbsp;")));
                                     out.println("</td>");
} else {
                                     out.print(vEti.EtiCampoCS("EEtiqueta", "Responsable:","ECampo", "text", 13, 13, 4, "cNomCompletoPrepara", ""+bs.getFieldValue("cNomCompletoPrepara","&nbsp;"), 0,"", "fMayus(this);", false, true, lCaptura, request));
                                     out.println("<input type=\"hidden\" name=\"iCveUsuPrepara\" value=\""+""+bs.getFieldValue("iCveUsuPrepara","&nbsp;")+"\">");
}
                                     out.println("</tr>");
                                     out.println("<tr>");

                                     // dtCaducidad con formato dd/mm/aaaa

                                     if (bs.getFieldValue("dtCaducidad","")==null ||
                                         bs.getFieldValue("dtCaducidad","").equals("null") ||
                                         // Agregado por Rafael Alcocer Caldera 22/Sep/2006
                                         // De no considerar esta validacion envia un error
                                         bs.getFieldValue("dtCaducidad","").equals("")) {
                                       fechaFormateada = "";
                                     } else {
                                       d = tf.getSQLDatefromSQLString(bs.getFieldValue("dtCaducidad","").toString());
                                       fechaFormateada = sdf.format(d);
                                     }

                                     out.print(vEti.EtiCampoCS("EEtiqueta", "Caducidad:", "ECampo", "text", 10, 10, 3,"dtCaducidad", fechaFormateada , 0, "", "fValFecha(this.value);", false, true,lCaptura, request));
                                     out.println("</tr>");
                                     out.println("<tr>");

                                     //dtAutoriza con formato dd/mm/aaaa

                                     if (bs.getFieldValue("dtAutoriza","")==null ||
                                         bs.getFieldValue("dtAutoriza","").equals("null") ||
                                         // Agregado por Rafael Alcocer Caldera 22/Sep/2006
                                         // De no considerar esta validacion envia un error
                                         bs.getFieldValue("dtAutoriza","").equals("")) {
                                       fechaFormateada = "";
                                     } else {
                                       d = tf.getSQLDatefromSQLString(bs.getFieldValue("dtAutoriza","").toString());
                                       fechaFormateada = sdf.format(d);
                                     }

                                     out.print(vEti.EtiCampo("EEtiqueta", "Autorizado:", "ECampo", "text", 10, 10,"dtAutoriza", fechaFormateada , 0, "", "fValFecha(this.value);", false, true,lCaptura, request));
if (showCombo) {
                                     out.print(vEti.Texto("EEtiqueta","Por:"));
                                     out.print(vEti.SelectOneRow("ECampo", "iCveUsuAutoriza", "", vcPersonal,"iCveUsuario", // era iCveUsuario...
                                               "cNomCompleto", request, ""+bs.getFieldValue("iCveUsuAutoriza","&nbsp;")));
} else {
                                     out.print(vEti.EtiCampo("EEtiqueta", "Por:","ECampo", "text", 13, 13, "cNomCompletoAutoriza", ""+bs.getFieldValue("cNomCompletoAutoriza","&nbsp;"), 0,"", "fMayus(this);", false, true, lCaptura, request));
                                     out.println("<input type=\"hidden\" name=\"iCveUsuAutoriza\" value=\""+""+bs.getFieldValue("iCveUsuAutoriza","&nbsp;")+"\">");
}
                                     out.println("</tr><input type=\"hidden\" name=\"iNoLetras\" value=\"\" size=\"4\" >");
if (accion.equals("Modificar")){
                                     out.println("<tr><td class=\"EEtiqueta\">Observación:</td><td class=\"ECampo\" colspan=\"3\"><textarea cols=\"100\" rows=\"4\"  name=\"cObservacion\""); //
                                     out.println("value=\"" +bs.getFieldValue("cObservacion","&nbsp;") + "\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\">"+bs.getFieldValue("cObservacion","&nbsp;")+"</textarea></td>");
} else {
                                     out.println(vEti.EtiAreaTextoCS("EEtiqueta", "Observación:","ECampo", 100, 1, 3, "cObservacion", ""+bs.getFieldValue("cObservacion","&nbsp;"), 0,"", "fMayus(this);", false, true, lCaptura, request));
}
                                     // Parte de la valoracion para modificacion

                                     out.println("</tr><tr><td class=\"ETablaT\" colspan=\"4\">Valoraciones</td></tr>");
                                     out.println("</tr><tr><td class=\"ETablaST\" colspan=\"4\">Primer Valoraci&oacute;n</td></tr>");
                                     out.println("<tr>");
                                     // Rafael Alcocer Caldera Corregido 05/Oct/2006 dConcentExp1 --> dConcentExper1
                                     out.println(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n Experimental:","ECampo","text",10,7,"dConcentExper1",""+bs.getFieldValue("dConcentExper1", ""),0,"fMayus(this);","",false,true,lCaptura,request));

                                     // dtValoracion1 con formato dd/mm/aaaa

                                     if (bs.getFieldValue("dtValoracion1","")==null ||
                                         bs.getFieldValue("dtValoracion1","").equals("null") ||
                                         // Agregado por Rafael Alcocer Caldera 22/Sep/2006
                                         // De no considerar esta validacion envia un error
                                         bs.getFieldValue("dtValoracion1","").equals("")) {
                                       fechaFormateada = "";
                                     } else {
                                       d = tf.getSQLDatefromSQLString(bs.getFieldValue("dtValoracion1","").toString());
                                       fechaFormateada = sdf.format(d);
                                     }

                                     out.println(vEti.EtiCampo("EEtiqueta","Fecha de Caducidad:","ECampo","text",10,8,"dtValoracion1",fechaFormateada,0,"fMayus(this);","",false,true,lCaptura,request));

                                     out.println("</tr><tr>");
                                     // Rafael Alcocer Caldera Corregido 05/Oct/2006 "0" --> "" + bs.getFieldValue("cCveEquipo1", "")
                                     out.println(vEti.EtiSelectOneRowCS("EEtiqueta","Equipo:","ECampo","iCveEquipoE1","" + bs.getFieldValue("cCveEquipo1", ""),"",3, new TDTOXEquipo().FindByAll(" where lCuantCual = 1 ", " order by cCveEquipo "), "iCveEquipo","cCveEquipo", request,"-1",false,true,lCaptura));
                                     out.println("</tr>");
                                     out.println("</tr><tr><td class=\"ETablaST\" colspan=\"4\">Segunda Valoraci&oacute;n</td></tr>");
                                     out.println("<tr>");
                                     // Rafael Alcocer Caldera Corregido 05/Oct/2006 dConcentExp2 --> dConcentExper2
                                     out.println(vEti.EtiCampo("EEtiqueta","Concentraci&oacute;n Experimental:","ECampo","text",10,7,"dConcentExper2",""+bs.getFieldValue("dConcentExper2", ""),0,"fMayus(this);","",false,true,lCaptura,request));

                                     // dtValoracion2 con formato dd/mm/aaaa

                                     if (bs.getFieldValue("dtValoracion2","")==null ||
                                         bs.getFieldValue("dtValoracion2","").equals("null") ||
                                         // Agregado por Rafael Alcocer Caldera 22/Sep/2006
                                         // De no considerar esta validacion envia un error
                                         bs.getFieldValue("dtValoracion2","").equals("")) {
                                       fechaFormateada = "";
                                     } else {
                                       d = tf.getSQLDatefromSQLString(bs.getFieldValue("dtValoracion2","").toString());
                                       fechaFormateada = sdf.format(d);
                                     }

                                     out.println(vEti.EtiCampo("EEtiqueta","Fecha de Caducidad:","ECampo","text",10,8,"dtValoracion2", fechaFormateada ,0,"fMayus(this);","",false,true,lCaptura,request));
                                     out.println("</tr><tr>");
                                     // Rafael Alcocer Caldera Corregido 05/Oct/2006 "0" --> "" + bs.getFieldValue("cCveEquipo2", "")
                                     out.println(vEti.EtiSelectOneRowCS("EEtiqueta","Equipo:","ECampo","iCveEquipoE2","" + bs.getFieldValue("cCveEquipo2", ""),"",3, new TDTOXEquipo().FindByAll(" where lCuantCual = 1 ", " order by cCveEquipo "), "iCveEquipo","cCveEquipo", request,"-1",false,true,lCaptura));
                                     out.println("</tr>");

                                     //Termina la parte de valoraciones

                                     out.println("<tr><td class=\"ETablaT\" colspan=\"4\">Situación</td></tr><tr>");

String esAgotado = bs.getFieldValue("lAgotado").toString();
boolean habil = true;
if (esAgotado != null && !esAgotado.equals("null")) {
   habil = !esAgotado.equals("1");
}
if(accion.equals("Modificar") && esAgotado.equals("1")){
                                     out.print(vEti.EtiToggle("EEtiqueta","Agotado:","ECampo","lAgotado",bs.getFieldValue("lAgotado").toString(),"voidfChgTog()",0,true,"1","0", accion.equals("Modificar")?habil:false));
                                     out.println("<input type=\"hidden\" name=\"lAgotado\" value=\""+esAgotado+"\">");
} else {
                                     out.print(vEti.EtiToggle("EEtiqueta","Agotado:","ECampo","lAgotado",bs.getFieldValue("lAgotado").toString(),"voidfChgTog()",0,true,"1","0", accion.equals("Modificar")?habil:false));
}
                                     out.print(vEti.EtiToggle("EEtiqueta","Baja:","ECampo","lBaja",""+bs.getFieldValue("lBaja","&nbsp;"),"",0,true,"1","0",false));
                                     out.println("</tr>");
                                     out.println("<tr>");
String fechaFormAg = "";
String fechaFormBj = "";

if (bs.getFieldValue("dtAgotado","")==null ||
    bs.getFieldValue("dtAgotado","").equals("null") ||
    // Agregado por Rafael Alcocer Caldera 22/Sep/2006
    // De no considerar esta validacion envia un error
    bs.getFieldValue("dtAgotado","").equals("")) {
  fechaFormAg = "&nbsp;";
} else {
  d = tf.getSQLDatefromSQLString(bs.getFieldValue("dtAgotado","").toString());
  fechaFormAg = sdf.format(d);
}
if (bs.getFieldValue("dtBaja","")==null ||
    bs.getFieldValue("dtBaja","").equals("null") ||
    // Agregado por Rafael Alcocer Caldera 22/Sep/2006
    // De no considerar esta validacion envia un error
    bs.getFieldValue("dtBaja","").equals("")) {
  fechaFormBj = "&nbsp;";
} else {
  d = tf.getSQLDatefromSQLString(bs.getFieldValue("dtBaja","").toString());
  fechaFormBj = sdf.format(d);
}
fechaFormateada = ""; // re-set del valor
boolean activado = true;
if (bs.getFieldValue("lBaja","")!=null &&
    bs.getFieldValue("lBaja","").equals("1")) { // dado de baja
   fechaFormateada = fechaFormBj;
} else
if (bs.getFieldValue("lAgotado","")!=null &&
    bs.getFieldValue("lAgotado","").equals("1")) { // agotado
   fechaFormateada = fechaFormAg;
   activado = false;
}

                                     out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10, 3,"dtAgotado", fechaFormateada , 0, "", "fValFecha(this.value);", false, true ,lCaptura, request));
if( !accion.equals("Modificar") ){
                                     out.println("<input type=\"hidden\" name=\"dtAgotado\" value=\""+fechaFormateada+"\">");
                                     out.println("<input type=\"hidden\" name=\"lAgotado\" value=\""+bs.getFieldValue("lAgotado","1")+"\">");
}
                                     out.println("</td></tr>");

//out.println("<td class=\"EEtiqueta\">Fecha:</td><td class=\"ECampo\" colspan=\"3\"><input type=\"text\" name=\"dtAgotado\" value=\"\" size=\"10\" ></td>");
//                                     out.println("</tr>");

                                     //Bloque Reportes agrego MGonzalez 12/1/2004
                                     out.println("<tr>");
                                     out.print("<td class=\"ECampoC\" colspan='2'>");

                                     //Links para generacion para reportes

                                     out.print(vEti.clsAnclaTexto("EAncla","An&aacute;lisis Presuntivo","JavaScript:Genera_Doc(1);","Inmunoensayo"));
                                     out.print("</td>");
                                     out.print("<td class=\"ECampoC\" colspan='2'>");
                                     out.print(vEti.clsAnclaTexto("EAncla","An&aacute;lisis Confirmatorio","JavaScript:Genera_Doc(2);","Cromatografia"));
                                     out.print("</td>");
                                     out.println("</tr>");

                                   }else{
                            %><tr><td class="ETablaT" colspan="6">Reactivo</td></tr><%
                   out.println("<tr>");
                   out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 5,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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



