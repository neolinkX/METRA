<%/**
 * Title: Modificación de Categoría y Motivo para examenes no inicializados.
 * Description: JSP para listar las categorias y motivos solicitados en un examen.
 * Copyright: 2006
 * Company: Micros Personales S.A. de C.V.
 * @author Itzia María del Carmen Sánchez Méndez
 * @version 1
 */%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<html>
<%
  pg070107040CFG  clsConfig = new pg070107040CFG();               // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070107040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070107040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";     // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                 // modificar
  boolean lIra        = false;                 // modificar
  String cEstatusIR   = "";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
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
  String cNavStatus  = "Hidden"; //clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();   
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

//  String dFechaActual=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):new TFechas().getFechaDDMMYYYY(new java.sql.Date(new java.util.Date().getTime()),"/");
  TVUsuario vUsuario=(TVUsuario)request.getSession(true).getAttribute("UsrID");
//        System.out.println("BOTON = "+request.getParameter("hdBoton") );

%>
<!--
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev2\archivos\funciones\pg070107040.js"></SCRIPT>
-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

function fBuscar(){
		var msg = "";
		if (form.iCveExpediente1.value == ""){
			msg = "Favor de introducir un valor para el Expediente 1\n";
		}
		if (form.iCveExpediente2.value == ""){
			msg = "Favor de introducir un valor para el Expediente 1\n";
		}
		if (!/^([0-9])*$/.test(form.iCveExpediente1.value)){
			msg = "El valor del expediente 1 no es numero\n";
		}
		if (!/^([0-9])*$/.test(form.iCveExpediente2.value)){
			msg = msg +"El valor del expediente 2 no es numero\n";
		}

                if(form.iCveExpediente1.value == form.iCveExpediente2.value){
			msg = msg+"El expediente uno y dos son iguales\n"
		}
	if(msg == ""){
		form.hdBoton.value = 'Buscar';
		form.target = "_self";
		form.submit();
	}else{
		alert(msg);
	}
}

function Migrar(Expediente1, Examen1, Expediente2, Examen2){
                var r=confirm("¡La información después de la migración para el Expediente2 se perderá! \n ¿Estás seguro que deseas realizar la migración?");
                if(r==true){
                	var  macAddress = "";
                	var ipAddress = "";
                	var computerName = "";
                	var revision = "C:/Windows/Temp/FingerInfo.jar";
                	var fso = new ActiveXObject("Scripting.FileSystemObject");
                	var folderExistencia = fso.FileExists(revision);
                	 
                	if (BrowserDetect.browser == 'Explorer' && parseInt(BrowserDetect.version,10) >= 9) {
                	//alert("entro a explorer 9 o mayor");
                		if (folderExistencia) {
                			var ts;
                			var ForReading = 1;
                			ts = fso.OpenTextFile("C:\\Windows\\Temp\\FingerPrintInf.txt", ForReading);
                			ipAddress = ts.ReadLine();
                			computerName = ts.ReadLine();
                			macAddress =ts.ReadLine();
                			//Response.Write("Contenido del archivo= '" + s + "'");
                			ts.Close();
                		
                		}

                	} else {
                		var wmi = GetObject("winmgmts:{impersonationLevel=impersonate}");
                		e = new Enumerator(wmi.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration WHERE IPEnabled = True"));
                		for(; !e.atEnd(); e.moveNext()) {
                			var s = e.item();
                			macAddress = s.MACAddress;
                			ipAddress = s.IPAddress(0);
                			computerName = s.DNSHostName;
                		}
                	}
                	form.hdMacAddress.value = macAddress;
                	form.hdIpAddress.value = ipAddress;
                	form.hdComputerName.value = computerName;
                    form.hdBoton.value = 'Migrar';
                    form.iCveExpediente1.value = Expediente1;
                    form.iNumExamen1.value = Examen1;
                    form.iNumExamen2.value = Examen2;
                    form.iCveExpediente2.value = Expediente2;
                    form.target = "_self";
		    form.submit();
                }
}

  </SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
  <style type="text/css">
<!--
.Estilo2 {color: #FF0000; font-weight: bold; }
.Estilo9 {font-family: Arial, Helvetica, sans-serif; font-size: 12px; color: #003300; }
-->
  </style>
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <!--input type="hidden" name="FILNumReng" value="< % if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));% >"-->
  <input type="hidden" name="FILNumReng" value="200">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?Integer.toString(bs.pageNo()):""%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdICveExpediente" value="">
  <input type="hidden" name="hdINumExamen" value="">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("dtConcluido")!=null?request.getParameter("dtConcluido"):request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("iCveUniMed")!=null?request.getParameter("iCveUniMed"):request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("iCveModulo")!=null?request.getParameter("iCveModulo"):request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("iCveProceso")!=null?request.getParameter("iCveProceso"):request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("dtConcluido")!=null?request.getParameter("dtConcluido"):request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="iNumExamen1" value="">
  <input type="hidden" name="iNumExamen2" value="">
  <input type="hidden" name="hdMacAddress" value="">
<input type="hidden" name="hdIpAddress" value="">
<input type="hidden" name="hdComputerName" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">

<%  if(clsConfig.getAccesoValido()){
      String cTmp = request.getParameter("iCveUniMed")!=null?request.getParameter("iCveUniMed"):request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):"0";
      String cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                       "  and GRLUsuMedicos.iCveUniMed =  " + cTmp ;
      //Vector vGModulo = new TDGRLUSUMedicos().FindModulos(cFiltro);
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
       <tr>
          <td colspan="4" class="ETablaT">MIGRACIÓN DE EXPEDIENTES</td>
        </tr>

        <tr>
          <td class="EEtiqueta"><div align="center">Expediente1:<br>
            (Original) </div></td>
          <td class="ETabla" colspan = "3"><span class="ECampo">
            <input type="text" size="19" maxlength="19" name="iCveExpediente1"
value=""
 onMouseOut="if (window.fOutField) window.fOutField();"
 onMouseOver="if (window.fOverField) window.fOverField(0);">
          </span></td>
        </tr>
        <tr>
          <td class="EEtiqueta"><div align="center">Expediente2: <br>
            (a eliminar) </div></td>
          <td class="ECampo" colspan="1"><input type="text" size="19" maxlength="19" name="iCveExpediente2"
value=""
 onMouseOut="if (window.fOutField) window.fOutField();"
 onMouseOver="if (window.fOverField) window.fOverField(0);">
          <td class="ETablaC" colspan="2">
<a class="EAnclaC" name="Buscar" href="javascript:fBuscar();">Buscar Expedientes</a></td>
        </tr>
      </table>


<%if(request.getParameter("iCveExpediente1") != null && request.getParameter("iCveExpediente1").trim().length() > 0 && Integer.parseInt(request.getParameter("iCveExpediente1")) > 0 ){
    Vector Expediente1 = new Vector();
    Vector Expediente2 = new Vector();
    TDPERDatos dPERDatos = new TDPERDatos();
    TVPERDatos vPerDatos;
    TVPERDatos vPerDatos2;
    String Igualdad = "";
    String Resultado = "";

    try{
           Expediente1 = dPERDatos.Migraciones(request.getParameter("iCveExpediente1"));
           Expediente2 = dPERDatos.Migraciones(request.getParameter("iCveExpediente2"));

    }catch(Exception e){
      e.printStackTrace();
    }

    if(Expediente1.size() > 0 && Expediente2.size() > 0){
    for(int i=0;i<Expediente1.size();i++){
              vPerDatos = (TVPERDatos) Expediente1.get(i);
              vPerDatos2 = (TVPERDatos) Expediente2.get(i);
            //        System.out.println(vPerDatos.getICveExpediente());
            //        System.out.println(vPerDatos.getCApMaterno());
            //        System.out.println(vPerDatos.getCNombre());

            //        System.out.println(vPerDatos.getCDscEstadoNac());
            //        System.out.println(vPerDatos.getINumExamen());
    %>
 <br>
<table border=1  class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                 <td colspan="5" class="ETablaT">Informaci&oacute;n de Expedientes </td>
             <tr>
               <td colspan="2" class="ETablaT">Expediente1</td>
               <td colspan="1" class="ETablaT">Igualdad</td>
               <td colspan="2" class="ETablaT">Expediente2 </td>
             </tr>
	     <tr>
               <td class="EEtiqueta"><div align="left">Nombre: </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos.getCNombre()%></span></td>
               <td><div align="center" class="Estilo2"><%
               if(vPerDatos.getCNombre().toString().equals(vPerDatos2.getCNombre().toString()))
                    Igualdad = "=";
               else
                    Igualdad = "!";%><%=Igualdad%>
               </div></td>
               <td class="EEtiqueta"><div align="left">Nombre: </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos2.getCNombre()%></span></td>
             </tr>
             <tr>
               <td class="EEtiqueta"><div align="left">Apellido Paterno: </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos.getCApPaterno()%></span></td>
               <td><div align="center" class="Estilo2"><%
               if(vPerDatos.getCApPaterno().toString().equals(vPerDatos2.getCApPaterno().toString()))
                    Igualdad = "=";
               else
                    Igualdad = "!";%><%=Igualdad%></div></td>
               <td class="EEtiqueta"><div align="left">Apellido Paterno: </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos2.getCApPaterno()%></span></td>
             </tr>
             <tr>
               <td class="EEtiqueta"><div align="left">Apellido Materno: </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos.getCApMaterno()%></span></td>
               <td><div align="center" class="Estilo2"><%
               if(vPerDatos.getCApMaterno().toString().equals(vPerDatos2.getCApMaterno().toString()))
                    Igualdad = "=";
               else
                    Igualdad = "!";%><%=Igualdad%>
               </div></td>
               <td class="EEtiqueta"><div align="left">Apellido Materno: </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos2.getCApMaterno()%></span></td>
             </tr>
             <tr>
               <td class="EEtiqueta"><div align="left">RFC:</div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos.getCRFC()%></span></td>
               <td><div align="center" class="Estilo2"><%
               if(vPerDatos.getCRFC().toString().equals(vPerDatos2.getCRFC().toString()))
                    Igualdad = "=";
               else
                    Igualdad = "!";%><%=Igualdad%>
               </div></td>
               <td class="EEtiqueta"><div align="left">RFC:</div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos2.getCRFC()%></span></td>
             </tr>
             <tr>
               <td class="EEtiqueta"><div align="left">FECNAC (Fecha de Nacimiento): </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos.getDtNacimiento()%></span></td>
               <td><div align="center" class="Estilo2"><%
               if(vPerDatos.getDtNacimiento().toString().equals(vPerDatos2.getDtNacimiento().toString()))
                    Igualdad = "=";
               else
                    Igualdad = "!";%><%=Igualdad%>
               </div></td>
               <td class="EEtiqueta"><div align="left">FECNAC (Fecha de Nacimiento): </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos2.getDtNacimiento()%></span></td>
             </tr>
             <tr>
               <td class="EEtiqueta"><div align="left">Pais de nacimiento: </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos.getCDscPaisNac()%></span></td>
               <td><div align="center" class="Estilo2"><%
               if(vPerDatos.getCDscPaisNac().toString().equals(vPerDatos2.getCDscPaisNac().toString()))
                    Igualdad = "=";
               else
                    Igualdad = "!";%><%=Igualdad%>
               </div></td>
               <td class="EEtiqueta"><div align="left">Pais de nacimiento: </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos2.getCDscPaisNac()%></span></td>
             </tr>
             <tr>
               <td class="EEtiqueta"><div align="left">Estado de nacimiento:</div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos.getCDscEstadoNac()%></span></td>
               <td><div align="center" class="Estilo2"><%
               if(vPerDatos.getCDscEstadoNac().toString().equals(vPerDatos2.getCDscEstadoNac().toString()))
                    Igualdad = "=";
               else
                    Igualdad = "!";%><%=Igualdad%>
               </div></td>
               <td class="EEtiqueta"><div align="left">Estado de nacimiento:</div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos2.getCDscEstadoNac()%></span></td>
             </tr>
			 <tr>
               <td class="EEtiqueta"><div align="left">Expediente:</div></td>
               <td class="Estilo9"><%=vPerDatos.getICveExpediente()%></td>
               <td><div align="center" class="Estilo2">... </div></td>
               <td class="EEtiqueta"><div align="left">
                 <div align="left">Expediente:</div>
               </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos2.getICveExpediente()%></span></td>
             </tr>
			 <tr>
               <td class="EEtiqueta"><div align="left">Numero de examenes: </div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos.getINumExamen()%></span></td>
               <td><div align="center" class="Estilo2">... </div></td>
               <td class="EEtiqueta"><div align="left">Numero de examenes:</div></td>
               <td class="Estilo9"><span class="Estilo9"><%=vPerDatos2.getINumExamen()%></span></td>
             </tr>
             <%if(request.getParameter("hdBoton").equals("Buscar")){%>
             <tr>
               <td colspan="5" class="EEtiqueta"><div align="center"><a class="EAnclaC" name="Buscar" href="javascript:Migrar(Expediente1=<%=vPerDatos.getICveExpediente()%>,Examen1 = <%=vPerDatos.getINumExamen()%>,Expediente2=<%=vPerDatos2.getICveExpediente()%>,Examen2 = <%=vPerDatos2.getINumExamen()%> );">Migrar Expediente</a></div></td>
             </tr>
             <%}else{
                        if(request.getParameter("hdBoton").equals("Migrar")){
                                Resultado = clsConfig.MigrandoExp(vPerDatos.getICveExpediente(), vPerDatos.getINumExamen(), vPerDatos2.getICveExpediente(), vPerDatos2.getINumExamen());
                                %>
                                <div align="center" class="Estilo2">
                                            <%=Resultado%>
                                </div>
             <%
                        }
               }%>
        </table>


<%  }// llave del for
    }else{
%>
    <table border=1  class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                 <td width="370" class="ETablaT">Informaci&oacute;n de Expedientes </td>
		 <tr>
               <td class="EEtiqueta"><div align="center">No existen Datos coincidentes con el filtro </div></td>
             </tr>
     </table>
<%
    }
}else{
          //        System.out.println("La condicion no se cumplio");
}


      %>

<%  }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
<%
  if(clsConfig.getIFlag() != 0){
    out.println("<script language=\"JavaScript\">alert('La información fue modificada de manera correcta.');</script>");
  }
%>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
