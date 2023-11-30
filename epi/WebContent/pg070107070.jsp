<%/**
 * Title: Inhabilita
 * Description: Inhabilita Expedientes
 * Copyright: 2011
  * @author AG SA
 * @version 1.0
 * Clase:pg070107070CFG
 */%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<html>
<%
  pg070107070CFG  clsConfig = new pg070107070CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070107070.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070107070.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cModulo   = "";
  String cFecha    = "";
  String cCita   = "";

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
  String cEstatusIR   = "Imprimir";             // modificar

  //System.out.println("numero personal = "+request.getParameter("hdiCvePersonal"));
  //System.out.println("boton = "+(request.getParameter("hdBoton")));

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
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
  String iCveProceso = vParametros.getPropEspecifica("EPIProceso");
%>
<script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\pg070107070.js"></SCRIPT>-->
<script language="JavaScript">

 function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070102020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
    }
  }


function fChgArea(fArea){
  form = document.forms[0];
  cText = fArea.value;
  if(cText.length > 1499)
    fArea.value = cText = cText.substring(0,1499);
  form.iNoLetras.value = 1499 - cText.length;
}


function fChgArea(fArea){
  form = document.forms[0];
  cText = fArea.value;
  if(cText.length > 1999)
    fArea.value = cText = cText.substring(0,1999);
  form.iNoLetras.value = 1999 - cText.length;
  fArea.value = revisaCaracteresEspeciales(cText);
}

//Valida Apostrofo y Enter

function revisaCaracteresEspeciales(texto){
      if(fEncCaract(texto, "'")){
            alert("No se permite la comilla simple");
            texto = texto.replace("'","");
      }
      if(fEncCaract(texto, String.fromCharCode(10))||fEncCaract(texto, String.fromCharCode(13))){
          alert("No se permite ejecutar un ENTER en este campo");
          texto = texto.replace(String.fromCharCode(13),"");
          texto = texto.replace(String.fromCharCode(10),"");
      }
      return texto;
  }

  /* Funci�n para localizar un caracter espec�fico dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar el caracter
               cVCaract -- Caracter a buscar
   Valor que regresa:   true  -- Si lo localiza
                        false -- Si no lo encuentra */
function fEncCaract(cVCadena, cVCaract){
   if (cVCadena.indexOf(cVCaract) != -1){
        return true;
   }else{
       return false;
   }
}




  // Esta funci�n no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }

  var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';
</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"PermCombos.js"%>"></SCRIPT>

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
       cClave2  = "" + bs.getFieldValue("iCveUniMed", "");
       cModulo  = "" + bs.getFieldValue("iCveModulo", "");
       cFecha  = "" + bs.getFieldValue("cDscFecha", "");
       cCita  = "" + bs.getFieldValue("iCveCita", "");
        }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdModulo" value="<%=cModulo%>">
  <input type="hidden" name="hdFecha" value="<%=cFecha%>">
  <input type="hidden" name="hdCita" value="<%=cCita%>">
  <input type="hidden" name="hdAnio" value="<%=iAnio%>">
  <input type="hidden" name="hdPbaRapida" >
  <input type="hidden" name="hdPersonal">
  <input type="hidden" name="hdiCvePersonal">
  <input type="hidden" name="hdMacAddress" value="">
<input type="hidden" name="hdIpAddress" value="">
<input type="hidden" name="hdComputerName" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td>
  <input type="hidden" name="hdBoton">
  </td><td valign="top">

                            <%
                              TEtiCampo vEti = new TEtiCampo();
                              TCreaBoton bBtn = new TCreaBoton();
                              boolean lCaptura = clsConfig.getCaptura();
                              boolean lNuevo = clsConfig.getNuevo();
                              lCaptura = false;
                              lNuevo = true;
                              TFechas oFecha = new TFechas();
                              String cHoy = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(),"/");

                            %>
                             <input type="hidden" name="hdHoy" value="<%=dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/")%>" >
                             
                                <%if (bs != null || lNuevo){%>

                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                 <tr>
                                 <td colspan="4" class="ETablaT">Inhabilita Expediente
                                 </td>
                                 </tr>
                                 <tr>

                                 <%}%>

                               <%
                                 if (lNuevo){
                                	 int Inhabilitado = 0;
                                	 if (request.getParameter("hdiCvePersonal") != null && request.getParameter("hdiCvePersonal").compareTo("") != 0  ) 
	                                   {
                                	 		Inhabilitado = clsConfig.getInhabilitado(request.getParameter("hdiCvePersonal"));
	                                   }
                                	 if(Inhabilitado == 1){
                                		 %>
                                		 	<tr><td class="ETablaAyuda"><p>Este expediente posee una Inhabilitaci&oacute;n vigente y</p>
                                         	<p>por tal motivo no es posible agregar una nueva.</p><p></p></td></tr>
                                         <%
                                	 }else{
		                                    out.println("<tr>");
		                                    out.println("<td colspan='4' align='center'>");
		                                    out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar Persona","javascript:fSelPer();","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
		                                    out.println("</td>");
		                                    out.println("</tr>");
		                                   if (request.getParameter("hdiCvePersonal") != null && request.getParameter("hdiCvePersonal").compareTo("") != 0  ) 
		                                   {
		                                    out.println("<input type='hidden' name='iCvePersonal' value='" + request.getParameter("hdiCvePersonal")  + "'>" );
		                                    int i = 0;
		                                    Object obElemento;
		                                    TDPERDatos dPerDatos = new TDPERDatos();
		                                    Vector vcPersonal = new Vector();
		                                    vcPersonal = dPerDatos.FindByAll(request.getParameter("hdiCvePersonal"));
		                                    obElemento =  vcPersonal.get(i);
		                                    TVPERDatos vPERDatos = (TVPERDatos) obElemento;
		                                    out.println("<tr>");
		                                    out.println(vEti.TextoCS("ETablaSTC","Datos Personal",4));
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    //System.out.println("Acceso 1");
		                                    out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",10,10,"", vPERDatos.getCApPaterno(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cApPaterno' value='" + vPERDatos.getCApPaterno() + "'>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",10,10,"", vPERDatos.getCApMaterno(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cApMaterno' value='" + vPERDatos.getCApMaterno() + "'>");
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","Nombre(s):","ECampo","text",10,10,"",vPERDatos.getCNombre(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cNombre' value='" + vPERDatos.getCNombre() + "'>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","GSexonero:","ECampo","text",10,10,"",vPERDatos.getCSexo(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cGenero' value='" + vPERDatos.getCSexo() + "'>");
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampoCS("EEtiqueta","FECNAC (Fecha de Nacimiento):","ECampo","text",10,10,4,"",vPERDatos.getCDscFecNacimiento(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='dtNacimiento' value='" + vPERDatos.getCDscFecNacimiento() + "'>");
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",10,10,"", vPERDatos.getCRFC(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cRFC' value='" + vPERDatos.getCRFC() + "'>");
		                                    if (vPERDatos.getCCURP() != null ){
		                                    out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",10,10,"",vPERDatos.getCCURP() ,0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cCURP' value='" + vPERDatos.getCCURP() + "'>");
		                                    }
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","Pa&iacute;s de Nacimiento:","ECampo","text",10,10,"", vPERDatos.getCDscPaisNac(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='iCvePaisNac' value='" + vPERDatos.getICvePaisNac() + "'>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","EDONAC (Lugar de Nacimiento):","ECampo","text",10,10,"", vPERDatos.getCDscEstadoNac(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='iCveEstadoNac' value='" + vPERDatos.getICveEstadoNac() + "'>");
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampoCS("EEtiqueta","Expediente Anterior:","ECampo","text",10,10,4,"", vPERDatos.getCExpediente(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cExpediente' value='" + vPERDatos.getCExpediente() + "'>");
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    
		                                    out.println(vEti.TextoCS("EEtiquetaL","Direcci&oacute;n",4));
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampoCS("EEtiqueta","Calle:","ECampo","text",10,10,4,"",vPERDatos.getCCalle(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cCalle' value='" + vPERDatos.getCCalle() + "'>");
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","No. Ext.:","ECampo","text",25,25,"", vPERDatos.getCNumExt(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cNumExt' value='" + vPERDatos.getCNumExt() + "'>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","No. Int.:","ECampo","text",25,25,"", vPERDatos.getCNumInt(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cNumInt' value='" + vPERDatos.getCNumInt() + "'>");
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",10,10,"",vPERDatos.getCColonia(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cColonia' value='" + vPERDatos.getCColonia() + "'>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","C.P.:","ECampo","text",10,10,"", "" + vPERDatos.getICP(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='iCP' value='" + vPERDatos.getICP() + "'>");
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","Pa&iacute;s:","ECampo","text",10,10,"",vPERDatos.getCDscPais(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='iCvePais' value='" + vPERDatos.getICvePais() + "'>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","EDO (Estado):","ECampo","text",10,10,"", vPERDatos.getCDscEstado(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='iCveEstado' value='" + vPERDatos.getICveEstado() + "'>");
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","MUN (Municipio):","ECampo","text",10,10,"",vPERDatos.getCDscMunicipio() ,0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='iCveMunicipio' value='" + vPERDatos.getICveMunicipio() + "'>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","PoblaciPoblaci&oacute;nn:","ECampo","text",10,10,"", vPERDatos.getCCiudad(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cCiudad' value='" + vPERDatos.getCCiudad() + "'>");
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampoCS("EEtiqueta","Tel.:","ECampo","text",10,10,4,"", vPERDatos.getCTelefono(),0,"","fMayus(this);",false,true,false));
		                                    out.println(" <input type='hidden' name='cTelefono' value='" + vPERDatos.getCTelefono() + "'>");
		                                    out.println("</tr>");
		                                    out.println("<tr>");
		                                    out.println(vEti.TextoCS("ETablaSTC","Tr&aacute;mite",4));
		                                    out.println("</tr>");
		                                    
		                                    out.println("<tr>");
		                                    out.println(vEti.Texto("EEtiqueta","Proceso:"));
		                                    out.println(vEti.Texto("EEtiquetaL","Inhabilita"));
		                                    out.println(vEti.Texto("EEtiqueta","Motivo:"));
		                                    out.println("<td>");
		                                    out.println(vEti.SelectOneRowSinTD("iCveMotivo", "", (Vector) AppCacheManager.getColl("GRLMotivo","10"), "iCveMotivo", "cDscMotivo", request, "0", true));
		                                    out.println("</td>");
		                                    out.println("</tr>");
		
		                                    out.println("<tr>");
		                                    out.print(vEti.EtiCampo("EEtiqueta","Inicio:","ECampo","text",10,10,"dtFecha",cHoy,0,"","fMayus(this);",false,true,true,request));
		                                    out.print(vEti.EtiCampo("EEtiqueta","Fin:","ECampo","text",10,10,"dtFecha2",cHoy,0,"","fMayus(this);",false,true,true,request));
		                                    out.println("</tr>");
		
		                                    out.println("<tr>");
		                                    out.println("<td class=\"EEtiqueta\">Observaciones:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></td>");
		                                    out.print("<td colspan='3'><textarea cols=\"80\" rows=\"5\" name=\"cObservacion\" value=\"\" onkeypress=\"fChgArea(this);\" onChange=\"fChgArea(this);\"></textarea></td>");
		                                    //out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,5,5,"cObservacion","",0,"","",false,true,lCaptura));
		                                    out.println("</tr>");
		
		                                   }
		
		                                 // Alta al nuevo Usuario
		                                   else{
		                                       //System.out.println("bs"+bs);
		                                       if(bs != null){
		                                          out.println("<tr>");
		                                          out.println(vEti.TextoCS("ETablaSTC","El Expediente " +bs.getFieldValue("iCvePersonal","&nbsp;").toString()+
		                                                                   " ha quedado inhabilitado para realizar ex&aacute;menes Psicof&iacute;sico Integral apartir de "+bs.getFieldValue("InicioInh","&nbsp;").toString()+
		                                                                   " y hasta "+bs.getFieldValue("FinInh","&nbsp;").toString()+".",4));
		                                          out.println("</tr>");/*
		                                        //        System.out.println(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
		                                        //        System.out.println(bs.getFieldValue("iCveMotivo","&nbsp;").toString());
		                                        //        System.out.println(bs.getFieldValue("FinInh","&nbsp;").toString());*/
		                                       }else{
		                                    out.println("");
		                                    //System.out.println("Acceso 2");
		                                    //System.out.println(request.getParameter("hdiCvePersonal"));
		                                    }
		
		                                   }
                                 	}
                                }
                                else {
                                  if (bs != null){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Unidad M&eacute;dica:","ECampo","text",10,10,"iCveUniMed", bs.getFieldValue("cDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println(" <input type='hidden' name='iCveUniMedA' value='" +  bs.getFieldValue("iCveUniMed","&nbsp;").toString() + "'>");
                                    out.print(vEti.EtiCampo("EEtiqueta","M&oacute;dulo:","ECampo","text",10,10,"iCveModulo", bs.getFieldValue("cDscModulo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println(" <input type='hidden' name='iCveModuloA' value='" +  bs.getFieldValue("iCveModulo","&nbsp;").toString() + "'>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtFecha",bs.getFieldValue("cDscDtFecha","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println(" <input type='hidden' name='dtFechaA' value='" +  bs.getFieldValue("cDscDtFecha","&nbsp;").toString() + "'>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Hora:","ECampo","text",10,10,"cHora", bs.getFieldValue("cHora","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    if (request.getParameter("hdBoton").compareTo("Modificar") == 0) {
                                    %>
                                    <tr>
                                    <td class="ETabla">&nbsp;</td>
                                    <td class="ETabla">&nbsp;</td>
                                    <td class="EEtiqueta">Nueva Hora:</td>
                                    <td>
                                    <select name="iHora" size="1">
                                    <% for (int i = 8; i <= 18; i++){
                                          out.print("<option value = " + i + ">" + i + "</option>");
                                       }
                                    %>
                                   </select>
                                    <select name="iMinutos" size="1">
                                    <% for (int i = 0; i <= 45; i = i + 15){
                                          if (i == 0)
                                          out.print("<option value = " + i + ">00</option>");
                                          else
                                           out.print("<option value = " + i + ">" + i + "</option>");

                                       }
                                    %>
                                   </select>
                                   </td>
                                   </tr>
                                   <%
                                    }
                                    out.println("</tr>");
                                    /*out.println("<tr>");
                                    out.println(vEti.TextoCS("EEtiquetaL","Boton Buscar",4));
                                    out.println("</tr>");*/
                                    out.println("<tr>");
                                   // if (request.getParameter("hdBoton").compareTo("Modificar") == 0)
                                       out.print(vEti.EtiCampoCS("EEtiqueta","Cve. Cita:","ECampo","text",10,10,4,"iCveCita", bs.getFieldValue("iCveCita","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                       out.println(" <input type='hidden' name='iCveCita' value='" +  bs.getFieldValue("iCveCita","&nbsp;").toString() + "'>");
                                    //else
                                    //   out.print(vEti.EtiCampoCS("EEtiqueta","Cve. Cita:","ECampo","text",10,10,4,"iCveCita", bs.getFieldValue("iCveCita","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaSTC","Datos Personal",4));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    //System.out.println("Acceso 3");
                                    out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",10,10,"cApPaterno", bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",10,10,"cApMaterno", bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Nombre(s):","ECampo","text",10,10,"cNombre", bs.getFieldValue("cNombre","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cGenero","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","FECNAC (Fecha de Nacimiento):","ECampo","text",10,10,4,"dtNacimiento", bs.getFieldValue("cDscDtFechaNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",10,10,"cRFC", bs.getFieldValue("cRFC","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",10,10,"cCURP", bs.getFieldValue("cCURP","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","PaPa&iacute;ss de Nacimiento:","ECampo","text",10,10,"iCvePaisNac", bs.getFieldValue("cDscPaisNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","EDONAC (Lugar de Nacimiento):","ECampo","text",10,10,"iCveEstadoNac", bs.getFieldValue("cDscEstadoNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Expediente:","ECampo","text",10,10,4,"cExpedAnt", bs.getFieldValue("cExpediente","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");


                                    //Nuevos datos para pais y estado de nacimiento

                                if (request.getParameter("hdBoton").compareTo("Modificar") == 0) {
                                    out.println(vEti.TextoCS("ETablaSTC","Nuevos datos de Nacimiento",4));
                                    TDPais dPaisNac = new TDPais();
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Pa&iacute;s de Nacimiento:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisNac","llenaSLT(2,this.value,'','',document.forms[0].iCveEstadoNac);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, "0", true));
                                    out.println("</td>");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","EDONAC (Lugar de Nacimiento):"));
                                    out.println("<td class='ECampo'>");
                                    out.println("<SELECT NAME='iCveEstadoNac' SIZE='1' ");
                                    out.println("</SELECT>");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    }

                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaSTC","DirecciDirecci&oacute;nn",4));
                                    out.println("</tr>");
                                    /*out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Cambio de  Direcci&oacute;n","ECampo","text",10,10,4,"lCambioDir", bs.getFieldValue("lCambioDir","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");*/
                                    /*out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Personal:","ECampo","text",10,10,"iCvePersonal", bs.getFieldValue("iCvePersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");*/
                                  if (request.getParameter("hdBoton").compareTo("Modificar") != 0) {
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
                                    out.print(vEti.EtiCampo("EEtiqueta","Pa&iacute;s:","ECampo","text",10,10,"iCvePais", bs.getFieldValue("cDscPais","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","EDO (Estado):","ECampo","text",10,10,"iCveEstado", bs.getFieldValue("cDscEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","MUN (Municipio):","ECampo","text",10,10,"iCveMunicipio", bs.getFieldValue("cDscMunicipio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Poblaci&oacute;n:","ECampo","text",10,10,"cCiudad", bs.getFieldValue("cCiudad","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Tel.:","ECampo","text",10,10,4,"cTel", bs.getFieldValue("cTelefono","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                   }


                                  //habilitar Modificacion de direccion
                                  if (request.getParameter("hdBoton").compareTo("Modificar") == 0) {
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Pa&iacute;s:","ECampo","text",10,10,"iCvePais", bs.getFieldValue("cDscPais","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","EDO (Estado):","ECampo","text",10,10,"iCveEstado", bs.getFieldValue("cDscEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","MUN (Municipio):","ECampo","text",10,10,"iCveMunicipio", bs.getFieldValue("cDscMunicipio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");

                                    out.println(vEti.TextoCS("ETablaSTC","Nuevos datos de Direcci&oacute;n",4));
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Calle:","ECampo","text",35,50,4,"cCalle",bs.getFieldValue("cCalle","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Ext.:","ECampo","text",25,25,"cNumExt", bs.getFieldValue("cNumExt","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Int.:","ECampo","text",25,25,"cNumInt", bs.getFieldValue("cNumInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",30,30,"cColonia", bs.getFieldValue("cColonia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","C.P.:","ECampo","text",5,5,"iCP", bs.getFieldValue("iCP","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");


                                    TDPais dPais = new TDPais();
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Pa&iacute;s:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePais","llenaSLT(2,this.value,'','',document.forms[0].iCveEstado);", dPais.FindByAll(), "iCvePais", "cNombre", request, "0", true));
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","EDO (Estado):"));
                                    out.println("<td class='ECampo'>");
                                    out.println("<SELECT NAME='iCveEstado' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio);\" ");
                                    out.println("</SELECT>");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
                                    out.println("<td class='ECampo'>");
                                    out.println("<SELECT NAME='iCveMunicipio' SIZE='1' ");
                                    out.println("</SELECT>");
                                    out.println("</td>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Poblaci&oacute;n:","ECampo","text",35,50,"cCiudad", bs.getFieldValue("cCiudad","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Tel.:","ECampo","text",20,20,4,"cTelefono",bs.getFieldValue("cTelefono","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    }

                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaSTC","TrTr&aacute;mitemite",4));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Modo de Transporte:","ECampo","text",10,10,"iCveMdoTrans", bs.getFieldValue("cDscMdoTransporte","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                     out.print(vEti.EtiCampo("EEtiqueta","Categor&iacute;a:","ECampo","text",10,10,"iCveCategoría", bs.getFieldValue("cDscCategoria","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Puesto:","ECampo","text",10,10,4,"iCvePuesto", bs.getFieldValue("cDscPuesto","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Proceso:"));
                                    out.println(vEti.Texto("EEtiquetaL","Inhabilita"));
                                    out.print(vEti.EtiCampo("EEtiqueta","Motivo:","ECampo","text",10,10,"iCveMotivo", bs.getFieldValue("cDscMotivo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Inicio:","ECampo","text",10,10,"dtFecha",cHoy,0,"","fMayus(this);",false,true,true,request));
                                    out.print(vEti.EtiCampo("EEtiqueta","Fin:","ECampo","text",10,10,"dtFecha2",cHoy,0,"","fMayus(this);",false,true,true,request));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Observaci&oacute;n:","ECampo","text",10,10,4,"cObservacion", bs.getFieldValue("cObservacion","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("</tr>");

                                   }
                                   else{
                                   %>
                                   <p>&nbsp;</p>
                                  <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                  <%
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
                                   out.println("</table>");
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
