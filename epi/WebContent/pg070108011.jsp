<%/**
 * Title: Listado del detalle de Exámenes Concluidos
 * Description: JSP para mostrar los examenes con servicios por capturar
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author AG SA SANDOVAL
 * @version 1
 * Clase: ?
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
  pg070108011CFG  clsConfig = new pg070108011CFG();               // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070108011.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070108011.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070108011.jsp";     // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Expediente|";      // modificar
  String cCveOrdenar  = "Expediente|";                  // modificar
  String cDscFiltrar  = "Expediente|";      // modificar
  String cCveFiltrar  = "iCveExpediente|";                  // modificar
  String cTipoFiltrar = "8|";                  // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Disabled";            // modificar

  // LLamado al Output Header
 
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
 
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = "";//clsConfig.getPaginas();
  String cDscPaginas = "";//clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = "Disabled"; //clsConfig.getNavStatus();
  String cOtroStatus = "Disabled";//clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  TVUsuario  vUsuario =(TVUsuario)request.getSession(true).getAttribute("UsrID");
  TVPERDatos vPERDatos=clsConfig.getUser(request.getParameter("hdICveExpediente"));
 
%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\archivos\funciones\pg070108011.js"></SCRIPT>-->
<SCRIPT LANGUAGE="JavaScript" SRC=""></SCRIPT>
 
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
    
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
  
  function fIrCatalogo(page,fieldName,value,fieldName2,value2){
    var form=document.forms[0];
    form.action=page;
    if(value ) form[fieldName ].value=value ;
    if(value2) form[fieldName2].value=value2;
    form.target="FRMDatos";
    form.submit();
  }
  
  
</SCRIPT>
<head>
    
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>

<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">

<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
  
<!--<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">-->
<form method="POST" action="pg070108011.jsp" target="FRMCuerpo">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <!--input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null) out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>"-->
  <input type="hidden" name="FILNumReng" value="200">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?bs.pageNo():0%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdCDscGrupo" value="">
  <input type="hidden" name="hdICvePerfil" value="">
<%-- Estos parametros son para las paginas 070101080,070104031,070104033 y 070105040--%>
  <input type="hidden" name="iCveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="iNumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="iCveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">
  <input type="hidden" name="iCvePersonal" value="<%=vPERDatos!=null?vPERDatos.getICvePersonal():0%>">
  <input type="hidden" name="iCveServicio" value="-1">
<%-- Hasta aqui los parametros para las paginas 070101080,070104031,070104033 y 070105040--%>
  <input type="hidden" name="hdCPropiedad" value="Servicios">
  <input type="hidden" name="hdBoton" value="Hidden">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
    
<%  if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr><td class="ETablaT" colspan="6">Datos del Personal</td></tr>
<%    if(vPERDatos!=null){
%>          <td class="EEtiqueta">Nombre:</td>
          <td class="ETabla" colspan="2"><%=vPERDatos.getCNombre()+" "+vPERDatos.getCApPaterno()+" "+vPERDatos.getCApMaterno()%></td>
          <td class="EEtiqueta">Expediente:</td>
          <td class="ETabla" colspan='2'><%=vPERDatos.getICveExpediente()%></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Edad:</td>
          <td class="ETabla" colspan="2"><%=clsConfig.getEdad(vPERDatos.getDtNacimiento())%></td>
          <td class="EEtiqueta">Sexo:</td>
          <td class="ETabla" colspan='2'><%=vPERDatos.getCSexo()%></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Proceso:</td>
          <td class="ETabla" colspan="5"><%=clsConfig.getProceso(request.getParameter("hdICveProceso")).getCDscProceso()%></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Unidad M&eacute;dica:</td>
          <td class="ETabla" colspan="2"><%=clsConfig.getUniMed(request.getParameter("hdICveUniMed")).getCDscUniMed()%></td>
          <td class="EEtiqueta">M&oacute;dulo:</td>
          <td class="ETabla" colspan='2'><%=clsConfig.getModulo(request.getParameter("hdICveUniMed"),request.getParameter("hdICveModulo")).getCDscModulo()%></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Fecha:</td>
          <td class="ETabla" colspan="5"><%=request.getParameter("hdDtConcluido")%></td>
        </tr>
<%    }else{
%>        <tr><td class="EResalta" colspan="5">Datos no disponibles</td></tr>
<%    }
       
%>      </table>
    </td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="3">Servicios por capturar</td></tr>
<%    int i=1;
      //String cadena="OBSERVACIONES DE DIAGNÓSTICO Y RECOMENDACIÓN DEL DICTAMINADOR";
      int x=0;
      if(bs!=null){
        bs.start();
        while(bs.nextRow()){
                  String Capturado = "";
                  if(bs.getFieldValue("lConcluido").toString().compareTo("1")==0){
                      Capturado = "Servicio capturado";
                  }else{
                      Capturado = "Servicio por capturar";
                  }
                  
%>                <tr class="ETabla"><td><%=i++%></td><td><%=bs.getFieldValue("cDscServicio","&nbsp;")%></td><td><%=Capturado%></td></tr>
<%         }
      }else{

%>
   <tr><td class="ETabla" colspan="5">No Existen Servicios por capturar</td></tr>
<%    }
      
      
    TDTSEGEXAR dTSEGEXAR = new TDTSEGEXAR();
    TVTSEGEXAR vTSEGEXAR;
    int Estatus = 0;
    String Sentencia = "";
    Vector vcTSEGEXAR = new Vector();
    //Actualizando el Estatus
      try{
           Sentencia = "UPDATE TSEGEXAR SET ESTATUS = 1 WHERE ESTATUS = 0 AND ICVEEXPEDIENTE = "+request.getParameter("hdICveExpediente")+"  AND INUMEXAMEN = "+request.getParameter("hdINumExamen")+" ";
           if(request.getParameter("hdActualiza") != null){
                    if(request.getParameter("hdActualiza").toString().compareTo("Actualiza") == 0)
                    dTSEGEXAR.update(null,request.getParameter("hdICveExpediente"),request.getParameter("hdINumExamen"),Sentencia);
           }
          }catch(Exception e){
                          vcTSEGEXAR = new Vector();
                          e.printStackTrace();
           }
      
      
      //Validar el Estatus del examen

                   try{ 
                            vcTSEGEXAR = dTSEGEXAR.FindByAll2(request.getParameter("hdICveExpediente"),request.getParameter("hdINumExamen"));
                   }catch(Exception e){
                            vcTSEGEXAR = new Vector();
                           e.printStackTrace();
                     }
                       for(int j=0;j<vcTSEGEXAR.size();j++){
                              vTSEGEXAR = (TVTSEGEXAR) vcTSEGEXAR.get(j);
                              Estatus = vTSEGEXAR.getEstatus();
                      }
          

      
%>
   <tr class="ETablaC"><td colspan="3">
    <!--   <form method="POST" action="pg070108011.jsp">
           <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")%>">
           <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")%>">-->
           <input type="hidden" name="hdActualiza" value="Actualiza">
           
           <%if(Estatus == 0)%>
           <input name="enviar" type="submit" id="enviar" value="Activar captura de servicios" onClick="fSubmitForm1(this.form)">
          <%if(Estatus == 1)%>
          <a class="EAncla" >Servicios Activados para su captura</a>
          <%if(Estatus == 2)%>
          <a class="EAncla" >La captura de servicios ha finalizado</a>
       </form>
        </td></tr>
      </table>
    </td></tr>
<%  }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>