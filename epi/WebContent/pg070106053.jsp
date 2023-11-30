<%/**
 * Title: pg070105040
 * Description:
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>

<html>

<%
  pg070106053CFG  clsConfig = new pg070106053CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070106053.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070106053.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070105040.jsp";     // modificar
  String cOperador    = "1";                   // modificar
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
  TFechas dtFecha = new TFechas();

  //********************************* el problema se presenta en esta linea
  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  int lDictaminado = 0;
  lDictaminado = clsConfig.lDictaminadoEA();
  if (lDictaminado != 0){
     cUpdStatus  = "SaveOnly";
  }else{
     cUpdStatus  = "Hidden";
  }

  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "hidden";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

/**
* Listas de Resultados
*/

       Vector vcExpNMedica = new Vector();
       Vector vcExpDicExam = new Vector();
       Vector vcExpRecomen = new Vector();
       Vector vcExpDiagnos = new Vector();

       vcExpNMedica = clsConfig.listanMedica();
       vcExpDicExam = clsConfig.listaExaCat();
       vcExpRecomen = clsConfig.listaExpRec();
       vcExpDiagnos = clsConfig.listaExpDia();

       TVEXPDictamenServ vExpNMedica = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpDicExam = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpRecomen = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpDiagnos = new TVEXPDictamenServ();


%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script LANGUAGE="JavaScript">

  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Guardar') {
      if(!confirm("¿Está Seguro de Dictaminar el registro?"))
        return false;
    }

    return true;
  }


  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

function Genera_Doc(){
   form = document.forms[0];
   form.target="_self";
   form.hdBoton.value = 'Imprime Documentacion';
   form.submit();
}

  var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';
</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaNas")+"cie.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selDiag.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"reco.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selReco.js"%>"></script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">

  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("iCveUniMed")!=null?request.getParameter("iCveUniMed"):request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("iCveModulo")!=null?request.getParameter("iCveModulo"):request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("iCveProceso")!=null?request.getParameter("iCveProceso"):request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">

  <input type="hidden" name="iCveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="iNumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="iCveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">

  <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal")%>">

  <!--input type="hidden" name="iCvePersonal" value="<%//=vPERDatos!=null?vPERDatos.getICvePersonal():0%>"-->
  <input type="hidden" name="iCveServicio" value="-1">



  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <%
  int iCveExpediente=0;
  int iCveServicio=0;
  int iNumExamen = 0;
  int iCvePersonal = 0;
  int iCveUniMed = 0;
  java.sql.Date hdDtConcluido;

  if (request.getParameter("iCveExpediente")!= null && request.getParameter("iCveExpediente").length()>0){
     out.print("<input type=\"hidden\" name=\"iCveExpediente\" value=\""+request.getParameter("iCveExpediente")+"\"> \n");
     iCveExpediente = Integer.parseInt(request.getParameter("iCveExpediente").toString());
  }else{
     out.print("<input type=\"hidden\" name=\"iCveExpediente\" value=\"10\"> \n");
     iCveExpediente = 1;
  }
  if (request.getParameter("iCveServicio")!= null && request.getParameter("iCveServicio").length()>0){
     out.print(" <input type=\"hidden\" name=\"iCveServicio\" value=\""+request.getParameter("iCveServicio")+"\"> \n");
     iCveServicio = Integer.parseInt(request.getParameter("iCveServicio").toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iCveServicio\" value=\"1\"> \n");
     iCveServicio = 1;
  }
  if (request.getParameter("iNumExamen")!= null && request.getParameter("iNumExamen").length()>0){
     out.print(" <input type=\"hidden\" name=\"iNumExamen\" value=\""+request.getParameter("iNumExamen")+"\"> \n");
     iNumExamen=Integer.parseInt(request.getParameter("iNumExamen").toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iNumExamen\" value=\"1\"> \n");
     iNumExamen=1;
  }

  if (request.getParameter("iCveProceso")!= null && request.getParameter("iCveProceso").length()>0){
     out.print(" <input type=\"hidden\" name=\"iCveProceso\" value=\""+request.getParameter("iCveProceso")+"\"> \n");
     iCveServicio = Integer.parseInt(request.getParameter("iCveProceso").toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iCveProceso\" value=\"1\"> \n");
     iCveServicio = 1;
  }

  if (request.getParameter("iCveUniMed")!= null && request.getParameter("iCveUniMed").length()>0){
     out.print(" <input type=\"hidden\" name=\"iCveUniMed\" value=\""+request.getParameter("iCveUniMed")+"\"> \n");
     iCveUniMed = Integer.parseInt(request.getParameter("iCveUniMed").toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iCveUniMed\" value=\"1\"> \n");
     iCveUniMed = 1;
  }

  if (request.getParameter("hdDtConcluido")!= null && request.getParameter("hdDtConcluido").length()>0){
     out.print(" <input type=\"hidden\" name=\"hdDtConcluido\" value=\""+request.getParameter("hdDtConcluido")+"\"> \n");
     hdDtConcluido = dtFecha.getDateSQL(request.getParameter("hdDtConcluido").toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"hdDtConcluido\" value=\"01/01/2004\"> \n");
     hdDtConcluido = dtFecha.TodaySQL();
  }

  %>


  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
       TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
       Vector vcEXPDicSer = new Vector();
       Vector vcEXPListaCat = new Vector();
       TVEXPDictamenServ vEXPDicSer = new TVEXPDictamenServ();
       TVEXPDictamenServ vEXPListaCat = new TVEXPDictamenServ();
       vcEXPDicSer = clsConfig.findCatSol();
       vcEXPListaCat = clsConfig.listaCategoria();
       TVPERDatos vPerDatos=clsConfig.findExpediente();

       out.print(" <input type=\"hidden\" name=\"Doctor\" value=\""+vUsuario.getCApPaterno().substring(0,1).toUpperCase()+vUsuario.getCApPaterno().substring(1,vUsuario.getCApPaterno().length()).toLowerCase()+", "+
                                                                    vUsuario.getCApMaterno().substring(0,1).toUpperCase()+vUsuario.getCApMaterno().substring(1,vUsuario.getCApMaterno().length()).toLowerCase()+" "+
                                                                    vUsuario.getCNombre().substring(0,1).toUpperCase()+vUsuario.getCNombre().substring(1,vUsuario.getCNombre().length()).toLowerCase()+"\">");

       out.print(" <input type=\"hidden\" name=\"Cedula\" value=\""+vUsuario.getCUsuario()+"\">");

  %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr>
                <td colspan="5" class="ETablaT">Datos del Personal
                </td>
             </tr>
             <%
             if(vPerDatos!=null){
               out.println("<tr>");
               out.print(" <input type=\"hidden\" name=\"iCvePersonal\" value=\""+vPerDatos.getICvePersonal()+"\"> \n");
               out.println("<td class=\"EEtiqueta\">Nombre:</td>");
               out.println("<td class=\"ECampo\">"+vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()+"</td>");
               out.println("<Input Type=\"Hidden\" Name=\"pName\" Value=\""+vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()+"\">");
               out.println("<Input Type=\"Hidden\" Name=\"pEmpresa\" Value=\""+vPerDatos.getCDscEmpresa()+"\">");
               out.println("<td class=\"EEtiqueta\" colspan=\"2\">Expediente:</td>");
               out.println("<td class=\"ECampo\">"+vPerDatos.getICveExpediente()+"</td>");
               out.println("</tr>");
               out.println("<tr>");
               out.println("<td class=\"EEtiqueta\">Edad:</td>");
               out.println("<td class=\"ECampo\">"+clsConfig.getEdad(vPerDatos.getDtNacimiento())+" Años</td>");
               out.println("<td class=\"EEtiqueta\" colspan=\"2\">Sexo:</td>");
               if (vPerDatos.getCSexo().equalsIgnoreCase("M")){
                   out.print("<td class=\"ECampo\">Masculino</td>");
               }else{
                   out.print("<td class=\"ECampo\">Femenino</td>");
                }
               out.print("</tr>");
             }else{
               if (request.getParameter("iCvePersonal")!= null && request.getParameter("iCvePersonal").length()>0){
                  out.print(" <input type=\"hidden\" name=\"iCvePersonal\" value=\""+request.getParameter("iCvePersonal")+"\"> \n");
                  iCvePersonal=Integer.parseInt(request.getParameter("iCvePersonal").toString());
               }else{
                  out.print(" <input type=\"hidden\" name=\"iCvePersonal\" value=\"1\"> \n");
                  iCvePersonal=1;
               }
               out.print("<tr>");
               out.print("<td class=\"EResalta\" colspan=\"4\" align=\"center\">Datos de Personal no disponibles</td>");
               out.print("</tr>");
             }
             %>
          </table>&nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr>
                <td colspan="7" class="ETablaT">Categorias Solicitadas
                </td>
             </tr>
             <%
             if(vcEXPDicSer.size()>0){
               String cModo = "";
               out.println("<tr>");
               out.println("<td class=\"ETablaT\">Modo de Transporte</td>");
               out.println("<td class=\"ETablaT\">Grupo</td>");
               out.println("<td class=\"ETablaT\">Categoría</td>");
               out.println("<td class=\"ETablaT\" ColSpan=\"2\">Motivo</td>");
               out.println("<td class=\"ETablaT\" ColSpan=\"2\">Puesto</td>");
               for (int i=0;i<vcEXPDicSer.size();i++){
                  vEXPDicSer = (TVEXPDictamenServ) vcEXPDicSer.get(i);
                  out.println("<tr>");
                  if (!vEXPDicSer.getCDscMdoTrans().equalsIgnoreCase(cModo)){
                     out.println("<td class=\"ECampo\">"+vEXPDicSer.getCDscMdoTrans()+"</td>");
                  }else{
                      out.println("<td>&nbsp;</td>");
                  }
                  out.println("<td class=\"ECampo\">"+vEXPDicSer.getCDscGrupo()+"</td>");
                  out.println("<td class=\"ECampo\">"+vEXPDicSer.getCDscCategoria()+"</td>");
                  out.println("<td class=\"ECampo\" ColSpan=\"2\">"+vEXPDicSer.getCDscMotivo()+"</td>");
                  out.println("<input type=\"hidden\" name=\"iCveMotivo"+i+"\" value=\""+vEXPDicSer.getICveMotivo()+"\">");
                  out.println("<td class=\"ECampo\" ColSpan=\"2\">"+vEXPDicSer.getCDscPuesto()+"</td>");
                  out.println("</tr>");
                  cModo=vEXPDicSer.getCDscMdoTrans();
               }
             }else{
                  out.println("<tr>");
                  out.println("<td class=\"ECampo\" colspan=\"4\" align=\"center\">Datos de Categorias no disponibles</td>");
                  out.println("</tr>");
             }
             %>
             <tr>
                <td colspan="7" class="ETablaT">Dictamen por Categoría
                </td>
             </tr>
             <%
                     if(vcExpDicExam.size()>0){
                        String cModob = "";
                        for (int b=0;b<vcExpDicExam.size();b++){
                           vExpDicExam = (TVEXPDictamenServ)vcExpDicExam.get(b);
                           out.println("<tr>");
                           if (!vExpDicExam.getCDscMdoTrans().equalsIgnoreCase(cModob)){
                              out.println("<td class=\"ETablaT\">"+vExpDicExam.getCDscMdoTrans()+"</td>");
                           }else{
                              out.println("<td class=\"ETablaT\">&nbsp;</td>");
                           }
                           out.println("<td class=\"ETablaT\">"+vExpDicExam.getCDscCategoria()+"");
                           out.println("<td align=\"right\">");

                        if (lDictaminado == 0){
                           //System.out.print("Entro a Dicatminado == 0");
                           if(vExpDicExam.getLDictamen() == 1){
                              //System.out.println("Primera opcion");
                              out.println("<input type=\"radio\" name=\"lDictamen_X"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"1\" Checked Disabled>");
                              out.println("<input type=\"hidden\" name=\"lDictamen"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"1\">");
                           }else{
                              //System.out.println("Segunda opcion");
                              out.println("<input type=\"radio\" name=\"lDictamen_X"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"1\"  Disabled>");
                           }
                           out.println("</td>");
                           out.println("<td align=\"left\" class=\"EEtiquetaL\">");
                           out.println("Apto");
                           out.println("</td>");
                           out.println("<td align=\"right\">");
                           if(vExpDicExam.getLDictamen() == 0){
                              out.println("<input type=\"radio\" name=\"lDictamen_X"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"0\" Checked  Disabled>");
                              out.println("<input type=\"hidden\" name=\"lDictamen"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"0\">");
                           }else{
                              out.println("<input align=\"right\" type=\"radio\" name=\"lDictamen_X"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"0\"  Disabled>");
                           }
                           out.println("</td>");
                           out.println("<td align=\"left\" class=\"EEtiquetaL\" ColSpan=\"3\">");
                           out.println("No Apto");
                           out.println("</td>");
                           out.println("</tr>");
                        }else{
                           if(vExpDicExam.getLDictamen() == 1){
                              out.println("<input type=\"radio\" name=\"lDictamen"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"1\" Checked>");
                           }else{
                              out.println("<input type=\"radio\" name=\"lDictamen"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"1\" >");
                           }
                           out.println("</td>");
                           out.println("<td align=\"left\" class=\"EEtiquetaL\">");
                           out.println("Apto");
                           out.println("</td>");
                           out.println("<td align=\"right\">");
                           if(vExpDicExam.getLDictamen() == 0){
                              out.println("<input type=\"radio\" name=\"lDictamen"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"0\" Checked>");
                           }else{
                              out.println("<input align=\"right\" type=\"radio\" name=\"lDictamen"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"0\">");
                           }
                           out.println("</td>");
                           out.println("<td align=\"left\" class=\"EEtiquetaL\" ColSpan=\"3\">");
                           out.println("No Apto");
                           out.println("</td>");
                           out.println("</tr>");
                        }
                           cModob=vExpDicExam.getCDscMdoTrans();
                        }
                     }else{
                 if (vcEXPDicSer.size() > 0){
                  String cModoc = "";
                  for (int i=0;i<vcEXPDicSer.size();i++){
                     vEXPDicSer = (TVEXPDictamenServ) vcEXPDicSer.get(i);
                     out.println("<tr>");
                     if (!vEXPDicSer.getCDscMdoTrans().equalsIgnoreCase(cModoc)){
                        out.println("<td class=\"ETablaT\">"+vEXPDicSer.getCDscMdoTrans()+"</td>");
                     }else{
                        out.println("<td class=\"ETablaT\">&nbsp;</td>");
                     }
                     out.println("<td class=\"ETablaT\">"+vEXPDicSer.getCDscCategoria()+"</td>");
                     out.println("<td align=\"right\">");
                     out.println("<input type=\"radio\" name=\"lDictamen"+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+"\" value=\"1\" Checked>");
                     out.println("</td>");
                     out.println("<td align=\"left\" class=\"EEtiquetaL\">");
                     out.println("Apto");
                     out.println("</td>");
                     out.println("<td align=\"right\">");
                     out.println("<input type=\"radio\" name=\"lDictamen"+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+"\" value=\"0\">");
                     out.println("</td>");
                     out.println("<td align=\"left\" class=\"EEtiquetaL\" ColSpan=\"3\">");
                     out.println("No Apto");
                     out.println("</td>");
                     out.println("</tr>");
                     cModoc=vEXPDicSer.getCDscMdoTrans();
                  }
               }
            }
             %>
          </table>
          <%

           //if(clsConfig.getAccion().compareToIgnoreCase("Imprime Documentacion")==0) {
             out.println(clsConfig.getActiveX());
           //}

           /*if(vcExpDicExam.size()>0 && (request.getParameter("hdBoton").compareTo("Guardar") == 0 ||
              request.getParameter("hdBoton").compareTo("Actual") == 0 ||
              request.getParameter("hdBoton").compareTo("Imprime Documentacion") == 0 )){*/

              
//LA SISGUIENTE PARTE SE COMENTO PARA DESAPARECER LA OCPION DE Copia Certificada              
              %>
          <!--<table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <%
//             out.println("<tr>");
//             out.print("<td class=\"ECampo\">");
//             out.print(vEti.clsAnclaTexto("EAncla","Copia Certificada","JavaScript:Genera_Doc();","Generar Documentacion"));
//             out.print("</td>");
//             out.println("</tr>");
          %>
          </table>   -->
          <%
           /*}*/
          %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

