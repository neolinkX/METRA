<%/**
 * Title: Listado de Turnos de Validaci�n
 * Description: Consulta de citas
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Esteban Viveros
 * @version 1
 * Clase: ?
 */%>   
<%@ page import="gob.sct.medprev.*" %>  
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %> 
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<html>
<%
  pg070103010CFG clsConfig = new pg070103010CFG();                // modificar Ok

  TEntorno       vEntorno  = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070103010.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103010.jsp\" target=\"FRMCuerpo"); // modificar Ok
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070103011.jsp";           // modificar Ok
  String cOperador    = "3";                         // modificar ?
  String cDscOrdenar  = "No Disponible|";                // modificar Ok
  String cCveOrdenar  = "No Disponible|";            // modificar Ok
  String cDscFiltrar  = "No Disponible|";                // modificar Ok
  String cCveFiltrar  = "No Disponible|";            // modificar Ok
  String cTipoFiltrar = "8|";                        // modificar Ok
  boolean lFiltros    = true;                       // modificar Ok
  boolean lIra        = true;                       // modificar Ok
  String cEstatusIR   = "Imprimir";                  // modificar ?

  // LLamado al Output Header
  TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  TFechas Fecha = new TFechas();
  //Vector vcPuestos=(Vector) request.getSession().getAttribute("pg07010301x.vcPuestos");
%>
<head>
<meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
</SCRIPT>
</head>
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>" onSubmit="return fOnSubmit();">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="200">
  <input type="hidden" name="hdRowNum" value="200">
  <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal")!=null?request.getParameter("hdICvePersonal"):"1"%>"><!-- falta ver como obtengo este dato-->
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):"1"%>"><!-- falta ver como obtengo este dato-->
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):"1"%>"><!-- falta ver como obtengo este dato-->
  <input type="hidden" name="hdCPropiedad" value="">

  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo")%>">
  <input type="hidden" name="dtFecha" value="<%=request.getParameter("dtFecha")%>">
  <input type="hidden" name="cHora" value="<%=request.getParameter("cHora")%>">

  <input type="hidden" name="hdBoton" value="Primero">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="4">Datos Personales</td></tr>
<%    TVPERDatos vPerDatos=clsConfig.findUser();
      if(vPerDatos!=null){
%>      <tr>
        <td class="EEtiqueta">Nombre:</td><td class="ETabla"><%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%></td>
        <td class="EEtiqueta">Expediente:</td><td class="ETabla"><%=vPerDatos.getICveExpediente()%></td>
      </tr>
      <tr>
        <td class="EEtiqueta">Edad:</td><td class="ETabla"><%=clsConfig.getEdad(vPerDatos.getDtNacimiento())%></td>
        <td class="EEtiqueta">Sexo:</td><td class="ETabla"><%=vPerDatos.getCSexo_DGIS()%></td>
      </tr>
<%    }else{
%>      <tr><td class="EResalta" colspan="4" align="center">Datos no disponibles</td></tr>
<%    }
%>    </table></td></tr>
    <!--tr align='center'><td><a href="javascript:fDelCat();" onMouseOut="self.status='';return true;" onMouseOver="self.status='Valida los puestos';return true;">Corregir Categor&iacute;as</a></td></tr-->
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
<%
        int Inhabilitado = clsConfig.getInhabilitado(vPerDatos.getICveExpediente()+"");
        if(bs!=null){
            if(Inhabilitado == 1){
                %>
                   <tr><td class="ETablaAyuda"><p>Este Expediente se encuentra Inhabilitado para Generar Ex&aacute;menes  en Medicina Preventiva!</p>
                    <p>Favor de contactar al Administrador del Sistema.</p></td></tr>
                <%                
        }else{            
        bs.start();
        String cDscMdoTrans="";
        String cDscCategoria="";
        
        String Puesto = "A";
        String Puesto1 = "B";
        String Puesto2 = "C";
        String Puesto3 = "D";
        String Puesto4 = "E";
        String Puesto5 = "F";           
        String PT1 = "CONDUCTOR DE PASAJE";        
        String PT2 = "CONDUCTOR DE CARGA";
        String PT3 = "CONDUCTOR DE CARGAS 3 EJES";
        String PT4 = "CONDUCTOR DE TURISMO MENOR A 15 PASAJEROS";
        String PT5 = "CONDUCTOR DE CARGA DE MATERIALES Y RESIDUOS PELIGROSOS";
        String PT6 = "CONDUCTOR DE PASAJEROS DE Y HACIA PUERTOS Y AEROPUERTOS";
        
        int iLastExam = 0;
        boolean lNoDictaminado = false;
        boolean yesICan="yes".equalsIgnoreCase(cCanWrite);
        TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
        iLastExam = dEXPExamAplica.FindLastEPI(request.getParameter("hdICvePersonal"),"" + vParametros.getPropEspecifica("EPIProceso"));
//        lNoDictaminado = dEXPExamAplica.noDictaminado(Integer.parseInt(request.getParameter("hdICvePersonal")),Integer.parseInt(vParametros.getPropEspecifica("EPIProceso")));
        // Verificar si se va a generar un nuevo examen o no.
        TVEXPExamAplica vExamAplica = dEXPExamAplica.CategAdicional(Integer.parseInt(request.getParameter("hdICvePersonal").toString()),Integer.parseInt(vParametros.getPropEspecifica("EPIProceso").toString()));
        if(vExamAplica.getINumExamen() == 0)
          lNoDictaminado = false;
        else
          lNoDictaminado = true;
        if(request.getParameter("lNuevo") != null && "true".compareToIgnoreCase(request.getParameter("lNuevo").toString()) == 0 ){
          lNoDictaminado = false;
          out.println("<input type='hidden' name='lNuevo' value='true'>");
        }
// Sacar la informaci�n del examen anterior para saber si solo se va a agregar la informaci�n del examen.
         if(lNoDictaminado){
           String cMensaje = "que no est&aacute; dictaminado. Solo se pueden agregar categor&iacute;as.";
          if(vExamAplica.getLDictaminado() == 1)
            cMensaje = "dentro del rango permitido para agregar categor&iacute;as.";
%>
<tr><td colspan="3" class="ETablaAyuda">Tiene un examen anterior <%= cMensaje %> </td></tr>
<tr>
  <td class="EEtiqueta">N&uacute;mero de Examen</td>
  <td class="ETabla" coslpan="6"><%= vExamAplica.getINumExamen()%></td>
<tr>
<tr>
  <td class="EEtiqueta">Fecha de Aplicaci&oacute;n</td>
  <td class="ETabla" coslpan="6"><%= Fecha.getFechaDDMMYYYY(vExamAplica.getDtSolicitado(),"/")%></td>
<tr>
<%
          if(vExamAplica.getLDictaminado() == 1){
	        	  TFechas oFecha = new TFechas();
	        	  String cHoy = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(),"/");
	        	  String examen = Fecha.getFechaDDMMYYYY(vExamAplica.getDtSolicitado(),"/");
	        	  boolean nuevo = true;
	        	  if(cHoy.equals(examen)){
	        	  	System.out.println("iguales");
	        	  	nuevo = false;
	        	  }else{
	        		//System.out.println("diferentes");
	        		nuevo = true;
	        	  }
	          if(nuevo){
%>
<tr>
  <td class="EEtiqueta">Generar un nuevo Examen</td>
  <td class="ETabla" coslpan="4"><input type="checkbox" name="lNuevo" value="true"  onClick="fNuevoEx();"></td>
<tr>
<%
         	}
	     }
           }else // Despliegue del examen - Agregar categorias
%>
<tr><td colspan="3" class="ETablaAyuda">Se va a Generar un Examen</td></tr>
<%

        TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
        Vector vcCategorias = new Vector();

        while(bs.nextRow()){
          String checked="";
          String cPostfix=bs.getFieldValue("iCveModoTrans")+"|"+bs.getFieldValue("iCvePuesto")+"|"+bs.getFieldValue("iCveCategoria")+"|"+bs.getFieldValue("iCveGrupo");
          String cbName="cb|"+cPostfix;
          //if(vcPuestos!=null && vcPuestos.contains(cbName.toUpperCase())) checked=" checked";
          if(request.getParameter(cbName)!=null) checked=" checked";
          if(!cDscMdoTrans.equals(bs.getFieldValue("cDscMdoTrans"))){
            cDscMdoTrans=(String)bs.getFieldValue("cDscMdoTrans");
%>      <tr class="ETablaT"><td colspan="<%=yesICan?3:2%>"><%=cDscMdoTrans%></td></tr>
<%        }
          if(!Puesto.equals(bs.getFieldValue("cDscCategoria"))){
          if(!Puesto1.equals(bs.getFieldValue("cDscCategoria"))){
          if(!Puesto2.equals(bs.getFieldValue("cDscCategoria"))){
          if(!Puesto3.equals(bs.getFieldValue("cDscCategoria"))){
          if(!Puesto4.equals(bs.getFieldValue("cDscCategoria"))){
          if(!Puesto5.equals(bs.getFieldValue("cDscCategoria"))){
          if(!cDscCategoria.equals(bs.getFieldValue("cDscCategoria"))){
            cDscCategoria=(String)bs.getFieldValue("cDscCategoria");
%>      <tr><td class="ETablaSTR">Categor&iacute;a:</td><td class="ETablaST" colspan="<%=yesICan?2:1%>"><%=cDscCategoria%></td></tr>
<%        }}}}}}} 
       
       if(!PT1.equals(bs.getFieldValue("cDscPuesto"))){
       if(!PT2.equals(bs.getFieldValue("cDscPuesto"))){
       if(!PT3.equals(bs.getFieldValue("cDscPuesto"))){
       if(!PT4.equals(bs.getFieldValue("cDscPuesto"))){
       if(!PT5.equals(bs.getFieldValue("cDscPuesto"))){
       if(!PT6.equals(bs.getFieldValue("cDscPuesto"))){
%>      <tr class="ETabla">
        <td style="padding-left:10"><%=bs.getFieldValue("cDscPuesto")%></td>
        <td><%=bs.getFieldValue("cDscGrupo")%></td>

<%           
           if(yesICan){
           if (Integer.parseInt("" + bs.getFieldValue("iExamen")) < 1){
%>        <td><input type="checkbox" name="<%=cbName%>" value="1"<%=checked%>></td>
<%       }         
         else {
            if (lNoDictaminado)
               out.print("<td class='ENoAptitud'>CAP</td>");
            else {
%>              <td><input type="checkbox" name="<%=cbName%>" value="1"<%=checked%>></td>
<%         }
         }
        }
       }}}}}}
%>
        </tr>
<%      }
        if(yesICan && clsConfig.getLPagina(cCatalogo)){
%>      <tr class="ETabla"><td colspan="3" align="center">
        <a href="javascript:fIrCatalogo();" onMouseOut="self.status='';return true;" onMouseOver="self.status='Valida los puestos';return true;">Validar</a>
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:fCancela();" onMouseOut="self.status='';return true;" onMouseOver="self.status='Cancela la operaci&oacute;n';return true;">Cancelar</a>
      </td></tr>
<%      }
        }//Fn condicion Inhabilitado
      }else{
%>      <tr class="EResalta" align="center"><td>Datos no Disponibles.</td></tr>
<%    }
%>    </table></td></tr>
<%  }else{
%>  <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();
%></html>
