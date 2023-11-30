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

<%

  pg070108011PCFG  clsConfig = new pg070108011PCFG();               // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070108011P.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070108011P.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(false);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070108011P.jsp";     // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                 // modificar
  boolean lIra        = false;                 // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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
  String iCveProceso = vParametros.getPropEspecifica("EPIProceso");
  String dFechaActual=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):new TFechas().getFechaDDMMYYYY(new java.sql.Date(new java.util.Date().getTime()),"/");
  TVUsuario vUsuario=(TVUsuario)request.getSession(true).getAttribute("UsrID");
  String Aptitud = "";

  String hdLCondicion = request.getParameter("hdLCondicion").trim();
  String hdLOrdenar = request.getParameter("hdLOrdenar").trim();
  String FILNumReng = request.getParameter("FILNumReng").trim();
  String hdRowNum = request.getParameter("hdRowNum").trim();
  String hdCampoClave = request.getParameter("hdCampoClave").trim();
  String hdICveExpediente = request.getParameter("hdICveExpediente").trim();
  String hdINumExamen = request.getParameter("hdINumExamen").trim();
  String hdICveUniMed = request.getParameter("hdICveUniMed").trim();
  String hdICveModulo = request.getParameter("hdICveModulo").trim();
  String hdICveProceso = request.getParameter("hdICveProceso").trim();
  String hdDtConcluido = request.getParameter("hdDtConcluido").trim();
  String hdCDscGrupo = request.getParameter("hdCDscGrupo").trim();
  String hdICvePerfil = request.getParameter("hdICvePerfil").trim();
  
  String iCveExpediente = request.getParameter("iCveExpediente").trim();
  String iNumExamen = request.getParameter("iNumExamen").trim();
  String iCveUniMed = request.getParameter("iCveUniMed").trim();
  String iCveModulo = request.getParameter("iCveModulo").trim();
  //String iCveProceso = request.getParameter("iCveProceso").trim();
  String iCvePersonal = request.getParameter("iCvePersonal").trim();
  String iCveServicio = request.getParameter("iCveServicio").trim();
  
  String hdCPropiedad = request.getParameter("hdCPropiedad").trim();
  String hdBoton = request.getParameter("hdBoton").trim();
  
  String hdActualiza = request.getParameter("hdActualiza").trim();
  
  String cCveExpediente = request.getParameter("hdINumExamen").trim();
  
    TVPERDatos vPERDatos=null;
     try {
          vPERDatos=new TDPERDatos().findUserbyExp(cCveExpediente!=null?Integer.parseInt(cCveExpediente):0);
        }
        catch (Exception ex) {
          //error("getUser", ex);
        }
  
%> 

<html>
 
 
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--
<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\archivos\funciones\pg070108010.js"></SCRIPT>
-->
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"PermCombos.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
  </SCRIPT>
<head>
    
  <meta name="Autor" content="Micros Personales S.A. de C.V.">
</head>
<SCRIPT LANGUAGE="JavaScript" SRC="http://aplicaciones9.sct.gob.mx/imagenes/medprev/funciones/valida_nt.js"></SCRIPT>
<SCRIPT language="JavaScript" src="http://aplicaciones9.sct.gob.mx/imagenes/medprev/funciones/t07_Tooltips.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="http://aplicaciones9.sct.gob.mx/imagenes/medprev/funciones/buscar_nt.js"></SCRIPT>
 
 
<link rel="stylesheet" href="http://aplicaciones9.sct.gob.mx/imagenes/medprev/estilos/07_estilos.css" TYPE="text/css">
 
<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">
  
<!--<form method="POST" action="pg070108011.jsp" target="FRMCuerpo">-->
<form method="POST" action="pg070108011P.jsp" target="FRMCuerpo">
  <input type="hidden" name="hdLCondicion" value="<%=hdLCondicion%>">
  <input type="hidden" name="hdLOrdenar" value="<%=hdLOrdenar%>">
  <input type="hidden" name="FILNumReng" value="<%=FILNumReng%>">
  <input type="hidden" name="hdRowNum" value="<%=hdRowNum%>">
  <input type="hidden" name="hdCampoClave" value="<%=hdCampoClave%>">
  <input type="hidden" name="hdICveExpediente" value="<%=hdICveExpediente%>">
  <input type="hidden" name="hdINumExamen" value="<%=hdINumExamen%>">
  <input type="hidden" name="hdICveUniMed" value="<%=hdICveUniMed%>">
  <input type="hidden" name="hdICveModulo" value="<%=hdICveModulo%>">
  <input type="hidden" name="hdICveProceso" value="<%=hdICveProceso%>">
  <input type="hidden" name="hdDtConcluido" value="<%=hdDtConcluido%>">
  <input type="hidden" name="hdCDscGrupo" value="<%=hdCDscGrupo%>">
  <input type="hidden" name="hdICvePerfil" value="<%=hdICvePerfil%>">
 
  <input type="hidden" name="iCveExpediente" value="<%=iCveExpediente%>">
  <input type="hidden" name="iNumExamen" value="<%=iNumExamen%>">
  <input type="hidden" name="iCveUniMed" value="<%=iCveUniMed%>">
  <input type="hidden" name="iCveModulo" value="<%=iCveModulo%>">
  <input type="hidden" name="iCveProceso" value="<%=iCveProceso%>">
  <input type="hidden" name="iCvePersonal" value="<%=iCvePersonal%>">
  <input type="hidden" name="iCveServicio" value="<%=iCveServicio%>">
 
  <input type="hidden" name="hdCPropiedad" value="<%=hdCPropiedad%>">
  <input type="hidden" name="hdBoton" value="<%=hdBoton%>">
  <table background="http://aplicaciones9.sct.gob.mx/imagenes/medprev/img/medprev/fondo01.jpg" width="100%" height="100%">
    
    <tr><td>&nbsp;</td></tr>
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

          <input type="hidden" name="hdActualiza" value="Actualiza">
           
           <%if(Estatus == 0)%>
           <input name="enviar" type="submit" id="enviar" value="Activar captura de servicios"/>
          <%if(Estatus == 1)%>
          <a class="EAncla" >Servicios Activados para su captura</a>
          <%if(Estatus == 2)%>
          <a class="EAncla" >La captura de servicios ha finalizado</a>
       </form>
        </td></tr>
      </table>
</form>
</body>
 
 
</html>