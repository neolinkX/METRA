<%/**
    Document   : pg070102040
    Created on : 1/07/2011, 01:26:06 PM
    Author     : AG SA
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
  pg070102040CFG clsConfig = new pg070102040CFG();                // modificar Ok

  TEntorno       vEntorno  = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070102040.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070102040.jsp\" target=\"FRMCuerpo"); // modificar Ok
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  String cCatalogo    = "pg070102031.jsp";           // modificar Ok
  String cOperador    = "1";                         // modificar ?
  String cDscOrdenar  = "Sustancia|";                // modificar Ok
  String cCveOrdenar  = "cDscSustancia|";            // modificar Ok
  String cDscFiltrar  = "Sustancia|";                // modificar Ok
  String cCveFiltrar  = "cDscSustancia|";            // modificar Ok
  String cTipoFiltrar = "8|";                        // modificar Ok
  boolean lFiltros    = false;                       // modificar Ok
  boolean lIra        = false;                       // modificar Ok
  String cEstatusIR   = "";                  // modificar ?

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
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
    String iCveProceso = vParametros.getPropEspecifica("EPIProceso");
%>
<head>
<meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070103040.js"%>"></SCRIPT>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\pg070102040.js"></SCRIPT>-->

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"PermCombos.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

function fSubmite2(){
    var forma=document.forms[0];
    if(fValFecha(forma.dtFecha.value)){
      forma.target='FRMDatos';
      form.hdBoton2.value = 'TERMINADO';
      forma.submit();
    }else{
      forma.dtFecha.select();
    }
  }


  //creamos la variable ventana_secundaria que contendrá una referencia al popup que vamos a abrir
//la creamos como variable global para poder acceder a ella desde las distintas funciones
var ventana_secundaria

function abrirVentana(){
	//guardo la referencia de la ventana para poder utilizarla luego
	ventana_secundaria = window.open("CargaCitas.jsp","miventana","width=300,height=200,menubar=no")
}

function cerrarVentana(){
	//la referencia de la ventana es el objeto window del popup. Lo utilizo para acceder al método close
	ventana_secundaria.close()
}


</SCRIPT>
</head>
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>" onSubmit="return fOnSubmit();">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?bs.pageNo():0%>">
  <input type="hidden" name="hdCveUniMed" value="">
  <input type="hidden" name="hdCveModulo" value="">
  <input type="hidden" name="hdFecha" value="">
  <input type="hidden" name="hdCveCita" value="">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="Primero">
  <input type="hidden" name="hdBoton2" value="Primero">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%

  TFechas oFecha = new TFechas();
   String cHoy = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(),"/");

   if(clsConfig.getAccesoValido()){
      java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd,MM,yyyy");
      String cDate=sdf.format(new java.util.Date());


%>

<tr><td>&nbsp;</td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="5">Consulta de Citas</td></tr>
      <tr>
        <td class="EEtiqueta">Fecha:</td>
        <td class="Etabla">
           <input type="text" size="12" maxlength="10" name="dtFecha" value="<%=request.getParameter("dtFecha")!=null?request.getParameter("dtFecha"):cHoy%>">
          <a class="EAncla" name="calendario" href="JavaScript:fLoadCal(<%=cDate%>,document.forms[0].dtFecha);document.forms[0].dtFecha.focus();">(dd/mm/aaaa)</a>
        </td>
        <td class="EEtiqueta">
            <%out.print(vEti.clsAnclaTexto("EEtiqueta","Cargar Citas","javascript:abrirVentana();fSubmite();","Cargar Citas"));%>
        </td>
      </tr></table>

<%  

//System.out.println("dtFecha = "+ request.getParameter("dtFecha"));
//System.out.println("usuario = "+ vUsuario.getICveusuario());
//System.out.println("hdBoton2 = "+ request.getParameter("hdBoton2"));
//System.out.println("regresa = "+request.getParameter("Regresa"));


if(request.getParameter("Regresa") != null){
    %>
    <SCRIPT LANGUAGE="JavaScript">
           abrirVentana();
           cerrarVentana();
     </SCRIPT>
    <p>&nbsp;</p>
         <table align="center">
                          <tr>
                            <td><%=request.getParameter("Regresa")%></td>
                          </tr>
          </table>
    <%
    }else{
        if(request.getParameter("hdBoton2") != null){
            if(request.getParameter("hdBoton2").equals("Primero")){

                //System.out.println("Cita cargada? = "+clsConfig.VerCC());

                    if(clsConfig.VerCC() == 2){
                        // si es igual a 2 la cita se cargo correctamente
                            //System.out.println("Se mando la peticion");
                            //System.out.println("Resultado = "+ request.getParameter("Resultado"));
                        %>
                            <p>&nbsp;</p>
                            <SCRIPT LANGUAGE="JavaScript">
                                    abrirVentana();
                                    cerrarVentana();
                            </SCRIPT>
                                 <table align="center">
                                                  <tr>
                                                    <td>La carga de las citas del día seleccionado ya ha sido realizada.</td>
                                                  </tr>
                           </table>
    <%
                    }else{
                     if(clsConfig.VerCC() == 1){
                            %>
                                <p>&nbsp;</p>
                            <SCRIPT LANGUAGE="JavaScript">
                                    abrirVentana();
                                    cerrarVentana();
                            </SCRIPT>
                                 <table align="center">
                                                  <tr>
                                                    <td>La carga de las citas del día seleccionado se cargó con errores, favor de contactar al administrador del sistema.</td>
                                                  </tr>
                                  </table>
                            <%
                            //System.out.println("La carga de las citas del dia seleccionado se cargaron con errores favor de contactar al administrador del sistema");
                     }else{
                           //System.out.println("Se iniciara la carga de Citas del dia seleccionado");
            %>
                        <p>&nbsp;</p>
                                    <table align="center">
                          <tr>
                            <td>Cargando citas</td>
                          </tr>
                        </table>

                        <%
                         String regreso = "";
                         regreso = clsConfig.CargaCitas(vUsuario.getICveusuario());
                         if(!regreso.equals("")){
                                //System.out.println(regreso);
                               %>
                                <input type="hidden" name="Regresa" value="<%=regreso%>">
                                <SCRIPT LANGUAGE="JavaScript">
                                    fSubmite();
                                    abrirVentana();
                                    cerrarVentana();
                                </SCRIPT>
                        <%
                         }
                        }
                    }
                }
            }
        }

   }else{
%>  <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();
%></html>
