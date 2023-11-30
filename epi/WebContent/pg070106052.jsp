<%/** 
/**  
 * @author AG
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
  pg070106052CFG  clsConfig = new pg070106052CFG();               // modificar

  TEntorno    vEntorno      = new TEntorno(); 
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070106052.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070106052.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070105021.jsp";     // modificar
  String cOperador    = "3";                   // modificar
  String cDscOrdenar  = "No Disponible|";      // modificar
  String cCveOrdenar  = "0|";                  // modificar
  String cDscFiltrar  = "No Disponible|";      // modificar
  String cCveFiltrar  = "0|";                  // modificar
  String cTipoFiltrar = "8|";                  // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
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

  TVUsuario vUsuario=(TVUsuario)request.getSession(true).getAttribute("UsrID");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"Audio02.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
  function fIrCatalogo(page){
    var form=document.forms[0];
    form.action=page;
    form.target="FRMDatos";
    form.submit();
  }

  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cStyle = '<link rel="stylesheet" href="/medprev/wwwrooting/estilos/07_estilos.css" TYPE="text/css">';

  var aSel = new Array();

  // Agregado IVAN SANTIAGO  06/Oct/2010
  // Variable global para hacer referencia a la subventana
  var newPlacaToraxFile;
    function showPlacasToraxFiles() {

        // asegurarse de que no se habia abierto
        if (!newPlacaToraxFile || newPlacaToraxFile.closed) {
            //newPlacaToraxFile = window.open("./SubirArchivo.jsp", "", "width=400,height=640,status=no,resizable=yes,menubar=yes,titlebar=yes,top=0,left=500,screenY=0,screenX=500,scrollbars=yes");
            newPlacaToraxFile = window.open("./MostrarArchivos.jsp?"
                +"iCveExpediente="+<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>
                +"&iNumExamen="+<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>
                +"&iCveServicio="+<%=request.getParameter("hdICveServicio")!=null?request.getParameter("hdICveServicio"):""%>
                +"&iCveRama="+0
                +"&iCveUsuario="+0, "", "height=200,width=650,resizable=yes,menubar=0,scrollbars=yes");
            // retardar escritura hasta que la ventana exista en IE/Windows
            //setTimeout("writeToWindowPlacaToraxFile()", 50);
            newPlacaToraxFile.focus( );
        } else if (newPlacaToraxFile.focus) {
            // la ventana ya estaba abierta y con focus para traerla al frente
            newPlacaToraxFile.focus( );
        }
    }
</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <!--input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null) out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>"-->
  <input type="hidden" name="FILNumReng" value="200">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?Integer.toString(bs.pageNo()):""%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <input type="hidden" name="hdICveServicio" value="<%=request.getParameter("hdICveServicio")!=null?request.getParameter("hdICveServicio"):""%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdINumExamTmp" value="<%=request.getParameter("hdINumExamTmp")!=null?request.getParameter("hdINumExamTmp"):""%>">
   <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal")%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr><td class="ETablaT" colspan="4">Datos del Personal</td></tr>
<%    TVPERDatos vPERDatos=clsConfig.getUser(request.getParameter("hdICveExpediente"));
      if(vPERDatos!=null){
%>        <tr>
          <td class="EEtiqueta">Nombre:</td>
          <td class="ETabla"><%=vPERDatos.getCNombre()+" "+vPERDatos.getCApPaterno()+" "+vPERDatos.getCApMaterno()%></td>
          <td class="EEtiqueta">Expediente:</td>
          <td class="ETabla"><%=vPERDatos.getICveExpediente()%></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Edad:</td>
          <td class="ETabla"><%=clsConfig.getEdad(vPERDatos.getDtNacimiento())%></td>
          <td class="EEtiqueta">Sexo:</td>
          <td class="ETabla"><%=vPERDatos.getCSexo()%></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Proceso:</td>
          <td class="ETabla" colspan="3"><%=clsConfig.getProceso(request.getParameter("hdICveProceso")).getCDscProceso()%></td>
        </tr>
<%    }else{
%>        <tr><td class="EResalta" colspan="4">Datos no disponibles</td></tr>
<%    }
%>      </table>
    </td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
<%    if(bs!=null){
        bs.start();
        String cDscServicio="";
        String cDscRama    ="";
        while(bs.nextRow()){
          if(!cDscServicio.equals(bs.getFieldValue("cDscServicio",""))){
            cDscServicio=(String)bs.getFieldValue("cDscServicio","");
%>        <tr class="ETablaT"><td colspan="2">Servicio: <%=cDscServicio%></td></tr>

  <%            //System.out.println("Servicio:"+request.getParameter("hdICveServicio"));
                //Se muestra la liga solo cuando es para servicio de
                if(request.getParameter("hdICveServicio").equals("4")){
                    %>
                      <tr class="EEtiquetaL"><td align="center">
                      <%
                        out.print(vEti.clsAnclaTexto("EAncla","Mostrar archivos de placas de torax","JavaScript:showPlacasToraxFiles();","Placa de torax"));
                      %>
                          </td></tr>
                    <%
                }
  %>

<%        }
          if(!cDscRama.equals(bs.getFieldValue("cDscRama",""))){
            cDscRama=(String)bs.getFieldValue("cDscRama","");
%>        <tr class="ETablaSTC"><td colspan="2"><%=cDscRama%></td></tr>
        <tr class="ETablaSTC"><td>S&iacute;ntoma/Pregunta/Resultado</td><td>Valor</td></tr>
<%        }
          String cTmp=(String)bs.getFieldValue("cCampo","");
          if     ("dValorI"  .equals(cTmp)) cTmp=(String)bs.getFieldValue("dValorIni","0.0");
          else if("dValorF"  .equals(cTmp)) cTmp=(String)bs.getFieldValue("dValorIni","0.0")+" - "+(String)bs.getFieldValue("dValorFin","0.0");
          else if("lLogico"  .equals(cTmp)) cTmp="1".equals((String)bs.getFieldValue("lLogico","0"))?"Si":"No";
          else if("cCaracter".equals(cTmp)) cTmp=(String)bs.getFieldValue("cCaracter","&nbsp;");
          else cTmp=(String)bs.getFieldValue(cTmp,"&nbsp;");
          String ptod = "PTERIGIÓN EN OJO DERECHO";
          String ptoi = "PTERIGIÓN EN OJO IZQUIERDO ";
          String ptbi = "PTERIGIÓN BILATERAL";
          String caod = "CATARATA EN OJO DERECHO";
          String caoi = "CATARATA DE OJO IZQUIERDO";
          String cabi = "CATARATA BILATERAL";
          String mid  = "MIDRIASIS";

          if(!ptod.equals(bs.getFieldValue("cPregunta"))){
          if(!ptoi.equals(bs.getFieldValue("cPregunta"))){
          if(!ptbi.equals(bs.getFieldValue("cPregunta"))){
          if(!caod.equals(bs.getFieldValue("cPregunta"))){
          if(!caoi.equals(bs.getFieldValue("cPregunta"))){
          if(!cabi.equals(bs.getFieldValue("cPregunta"))){
          if(!mid.equals(bs.getFieldValue("cPregunta"))){
%>        <tr><td class="EEtiquetaL"><%=bs.getFieldValue("cPregunta","&nbsp;")%></td><td class="ETabla"><%=cTmp%> <%=bs.getFieldValue("cEtiqueta","&nbsp;")%></td></tr>
<%      }}}}}}}
        }
      }else{
%>        <tr class="EResalta"><td colspan="7" align="center">Datos no disponibles</td></tr>
<%    }
%>
<table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
 <tr>
  <% if (vParametros.getPropEspecifica("Audiometria").compareTo(request.getParameter("hdICveServicio")) == 0) {
    TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
    TVEXPAudiomet vEXPAudiomet;
    Vector vcAudio = new Vector();

    vcAudio = dEXPAudiomet.FindByAll(" where iCveExpediente = " + request.getParameter("hdICveExpediente") + " and iNumExamen = " + request.getParameter("hdINumExamen") +" ");
    if (vcAudio.size() > 0){
          out.print("<SCRIPT LANGUAGE='JavaScript'>");
           for (int i = 0; i < vcAudio.size(); i++){
               vEXPAudiomet = (TVEXPAudiomet) vcAudio.get(i);
                out.print("aSel["+i+"]=["+vEXPAudiomet.getIOido()+","+vEXPAudiomet.getITipo()+
                                        ","+vEXPAudiomet.getIX()+","+vEXPAudiomet.getIY()+"];");
                //aSel[0]=[1,1,1,1];
           }
          out.print("</SCRIPT>");
    }

  %>
   <td><%= vEti.clsAnclaTexto("EAncla","Oido Izquierdo","JavaScript:fGraphAudio(1,aSel);", "") %></td>
   <td><%= vEti.clsAnclaTexto("EAncla","Oido Derecho","JavaScript:fGraphAudio(2,aSel);", "") %></td>
  <% }%>
 <tr>
</table>

 </table>
    </td></tr>
<%  }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>
</table>

</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>