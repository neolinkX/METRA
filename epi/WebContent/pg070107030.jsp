<%/**
 * Title: Listado de Exámenes Concluidos
 * Description: JSP para listar los exámenes concluidos
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Esteban Viveros
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
  pg070107030CFG  clsConfig = new pg070107030CFG();               // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070107030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070107030.jsp\" target=\"FRMCuerpo"); // modificar
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
%>
<script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--
<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev2\archivos\funciones\pg070107030.js"></SCRIPT>
-->
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
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
  <input type="hidden" name="hdUser" value="<%=vUsuario.getICveusuario()%>">
  <input type="hidden" name="hdMacAddress" value="">
<input type="hidden" name="hdIpAddress" value="">
<input type="hidden" name="hdComputerName" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
      String cTmp = request.getParameter("iCveUniMed")!=null?request.getParameter("iCveUniMed"):request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):"0";
      String cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                       "  and GRLUsuMedicos.iCveUniMed =  " + cTmp ;
      Vector vGModulo = new TDGRLUSUMedicos().FindModulos(cFiltro);
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr><td colspan="4" class="ETablaT">CAMBIO DE CATEGOR&Iacute;A Y MOTIVO EX&Aacute;MENES INICIADOS</td></tr>
        <tr>
          <td class="EEtiqueta">Unidad M&eacute;dica:</td>
          <td class="ETabla">
            <%=vEti.SelectOneRowSinTD("iCveUniMed","fillNext();",vUsuario.getVUniNoDup(),"iCveUniMed","cDscUniMed",request,cTmp,true)%>
          </td>
          <td class="EEtiqueta">M&oacute;dulo:</td>
          <td class="ETabla"><select name="iCveModulo" size="1">
            <option value="0"><%=vGModulo.size()>0?"Seleccione...":"No Existen Datos Relacionados"%></option>
<%    cTmp=request.getParameter("iCveModulo")!=null?request.getParameter("iCveModulo"):request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):"0";
      for(int x=0;x<vGModulo.size();x++){
        TVGRLUSUMedicos vGRLModulo = (TVGRLUSUMedicos) vGModulo.get(x);
%>            <option value="<%=vGRLModulo.getICveModulo()%>"<%=cTmp.equals(Integer.toString(vGRLModulo.getICveModulo()))?" selected":""%>><%=vGRLModulo.getCDscModulo()%></option>
<%    }
%>          </select></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Proceso:</td>
          <td class="ETabla" colspan = "3"><select name="iCveProceso" size="1">
<%    cTmp=request.getParameter("iCveUniMed")!=null?request.getParameter("iCveUniMed"):request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):"0";
      int iCveUniMed=cTmp!=null?Integer.parseInt(cTmp):Integer.parseInt(vParametros.getPropEspecifica("EPIProceso"));
      Vector vProceso=vUsuario.getVUnidades();
%>            <option value="0"><%=vProceso.size()>0?"Seleccione...":"No Existen Datos Relacionados"%></option>
<%    cTmp=request.getParameter("iCveProceso")!=null?request.getParameter("iCveProceso"):request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):"0";
      for(int w=0;w<vProceso.size();w++){
        TVGRLUMUsuario vGRLUMUsu = (TVGRLUMUsuario) vProceso.get(w);
        if(vGRLUMUsu.getICveUniMed()==iCveUniMed){
%>            <option value="<%=vGRLUMUsu.getICveProceso()%>"<%=cTmp.equals(Integer.toString((vGRLUMUsu.getICveProceso())))?" selected":""%>><%=vGRLUMUsu.getCDscProceso()%></option>
<%      }
      }
%>          </select></td>
        </tr>
        <tr><%=vEti.EtiCampoCS("EEtiqueta", "Expediente: ", "ECampo", "text", 19, 19,1,"iCveExpediente", "", 0, "", "", false, true,true, request)%>
                              <td class="ETablaC" colspan="2">
<%                               out.print(vEti.clsAnclaTexto("EAnclaC","Buscar Expediente","javascript:fBuscar();","Buscar"));
%>                            </td>

        </tr>
      </table>
           <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">

                            <tr>
                              <td class="ETablaT" colspan="1">Expediente</td>
                              <td class="ETablaT" colspan="3">Nombre</td>
                              <td class="ETablaT" colspan="1">RFC</td>
                              <td class="ETablaT" colspan="1">Fecha</td>
                            </tr>
                            <%
                            if (bs != null){
                              bs.start();
                              int iVariosMedicos = 0;
                              String exp = bs.getFieldValue("iCveExpediente","").toString();
                              String exm = bs.getFieldValue("iNumExamen","").toString();
                              String per = bs.getFieldValue("iCvePersonal","").toString();
%>                              <input type="hidden" name="iNumExamen" value="<%= exm %>">
<%                              while(bs.nextRow()){
%>                                <tr>
                                    <td class="ETablaC" colspan="1"><%=bs.getFieldValue("iCveExpediente","&nbsp;")%>
<%//                                    out.print(vEti.clsAnclaTexto("EAnclaC","Capturar Servicio", "javascript:if(fValidaSelectores())fIr('pg070104030.jsp'," + exp + "," + exm + "," + per + ");","exp"));
%>                                  </td>
                                    <td class="ETablaC" colspan="3"><%=bs.getFieldValue("cNombre","&nbsp;")+" "+bs.getFieldValue("cApPaterno","&nbsp;")+" "+bs.getFieldValue("cApMaterno","&nbsp;")%></td>
                                    <td class="ETablaC" colspan="1"><%=bs.getFieldValue("cRFC","&nbsp;")%></td>
                                    <td class="ETablaC" colspan="1"><%=clsConfig.formatDate(bs.getFieldValue("dtSolicitado").toString())%></td>
                                  </tr>
<%                              }
%>                              </table>
<%                                Vector vExamCat = new TDEXPExamCat().freeExam(exp,exm);
                                  cTmp=request.getParameter("iCveProceso")!=null?request.getParameter("iCveProceso"):vParametros.getPropEspecifica("EPIProceso");
                                  Vector vMotivo = new TDGRLMotivo().FindByAll("where icveproceso = " + cTmp + " order by cDscMotivo");
                                  if(vExamCat != null && vExamCat.size() > 0){
%>                                <br>
                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="4">
                                   <tr>
                                     <td class="ETablaT" >&nbsp;</td>
                                     <td class="ETablaT" >Modo de Transporte</td>
                                     <td class="ETablaT" >Categoria</td>
                                     <td class="ETablaT" >Puesto</td>
                                     <td class="ETablaT" >Motivo</td>
                                   </tr>
                                   <input type="hidden" name="maxValue" value="<%=vExamCat.size()%>">
<%
                                     for(int i = 0; i < vExamCat.size(); i++){
                                       TVEXPExamCat tvEXPExamCat = (TVEXPExamCat)vExamCat.get(i);/*
                                       String cChecked = "";
                                       if(request.getParameter("id"+i) != null)
                                         cChecked = "checked";*/
                                      String PuestoCds = "";
                                       String cSQL = "SELECT G.CDSCPUESTO "
                                                        + "FROM 	"
                                                        + "     EXPEXAMPUESTO AS P, GRLPUESTO AS G "
                                                        + "WHERE 	"
                                                        + "     P.ICVEEXPEDIENTE = "+exp+" AND 	"
                                                        + "     P.INUMEXAMEN = "+exm+" AND 	"
                                                        + "     P.ICVEMDOTRANS = "+tvEXPExamCat.getICveMdoTrans()+" AND "
                                                        + "     P.ICVEPUESTO = "+tvEXPExamCat.getICvePuesto()+" AND 	"
                                                        + "     P.ICVEMDOTRANS = G.ICVEMDOTRANS AND 	"
                                                        + "     P.ICVEPUESTO = G.ICVEPUESTO";
                                       try{
                                       TDEXPExamPuesto dEXPExamPuesto = new TDEXPExamPuesto();
                                       PuestoCds = dEXPExamPuesto.CdsPuesto(cSQL);
                                        }catch(Exception ex2){
                                            PuestoCds = "";
                                        }
%>                                    <tr>
                                        <td class="ETablaC" ><input type="checkbox" name="id<%=i%>" value="<%=i%>" onClick="Habilita('iCveMdoTrans<%=i%>','iCveMotivo<%=i%>','iCveCategoria<%=i%>','iCvePuesto<%=i%>');" >  </td>
                                        <td class="ETablaC" ><%= tvEXPExamCat.getCDscMdoTrans() %></td>
                                        <td class="ETablaC" ><%= tvEXPExamCat.getCDscCategoria() %></td>
                                        <td class="ETablaC" ><%= PuestoCds %></td>
                                        <td class="ETablaC" ><%= tvEXPExamCat.getCDscMotivo() %></td>
                                      </tr>
                                      <tr>
                                         <input type="hidden" name="iCveCategoriaInicial<%=i%>" value="<%=tvEXPExamCat.getICveCategoria()%>">
<!-- Mvo Tranporte -->
                                        <td class="ETablaC" >&nbsp;</td>
                                        <td class="ETablaC" >
                                          <input type="hidden" name="iCveMdoTransInicial<%=i%>" value="<%=tvEXPExamCat.getICveMdoTrans()%>">
                                          <select name="iCveMdoTrans<%=i%>" disabled onChange="llenaSLT(1,this.value,'<%=i%>','','');">

                                            <option value="0">Seleccione...</option>
<%                                        Vector vMdoTrans = new TDGRLMdoTrans().findByAll("");
                                          if(vMdoTrans != null && vMdoTrans.size() > 0){
                                             for(int j = 0; j < vMdoTrans.size(); j++){
                                               TVGRLMdoTrans tvMdoTrans = (TVGRLMdoTrans)vMdoTrans.get(j);
                                                  if((request.getParameter("iCveMdoTrans"+i) != null && Integer.parseInt(request.getParameter("iCveMdoTrans"+i)) == tvMdoTrans.getICveMdoTrans()) ||
                                                      tvEXPExamCat.getICveMdoTrans() == tvMdoTrans.getICveMdoTrans() )
                                                     out.print("<option value=\""+tvMdoTrans.getICveMdoTrans()+"\" selected >"+tvMdoTrans.getCDscMdoTrans()+"</option>");
                                                  else
                                                     out.print("<option value=\""+tvMdoTrans.getICveMdoTrans()+"\" >"+tvMdoTrans.getCDscMdoTrans()+"</option>");
                                             }
                                          }
%>                                        </select>
                                        </td>

<!-- nuevo campo  -->
<!-- Categorias  -->
                                          <td class="ETabla" >
                                          <input type="hidden" name="iCveCategoriaInicial<%=i%>" value="<%= tvEXPExamCat.getICveCategoria() %>">
                                          <select name="iCveCategoria<%=i%>" disabled onChange="llenaSLT(2,iCveMdoTrans<%=i%>.value,'<%=i%>',iCveCategoria<%=i%>.value,'');">
                                            <option value="0">Seleccione...</option>
<%                                        Vector vCategoria = new TDGRLCategoria().FindByAll(""+tvEXPExamCat.getICveMdoTrans());
                                          if(vCategoria != null && vCategoria.size() > 0){
                                             for(int j = 0; j < vCategoria.size(); j++){
                                               TVGRLCategoria tvCategoria = (TVGRLCategoria)vCategoria.get(j);
                                                  if((request.getParameter("iCveCategoria"+i) != null && request.getParameter("iCveCategoria"+i).equals(tvCategoria.getICveCategoria())) ||
                                                      tvEXPExamCat.getICveCategoria() == tvCategoria.getICveCategoria() )
                                                     out.print("<option value=\""+tvCategoria.getICveCategoria()+"\" selected >"+tvCategoria.getCDscCategoria()+"</option>");
                                                  else
                                                     out.print("<option value=\""+tvCategoria.getICveCategoria() +"\" >"+tvCategoria.getCDscCategoria()+"</option>");
                                             }
                                          }
%>                                        </select>
                                        </td>
<!-- Mvo categoria -->
                                          <td class="ETabla" >
                                          <input type="hidden" name="iCvePuestoInicial<%=i%>" value="<%= tvEXPExamCat.getICvePuesto() %>">
                                          <select name="iCvePuesto<%=i%>" disabled >
                                            <option value="0">Seleccione...</option>
<%                                        //System.out.println(request.getParameter("iCveMdoTrans"+i+""));
                                          //System.out.println(request.getParameter("iCveCategoria"+i+""));
                                          //String valor1 = request.getParameter("iCveMdoTrans"+i+"");
                                          //String valor2 = request.getParameter("iCveCategoria"+i+"");
                                          
                                          Vector vPuesto = new TDGRLPuesto().FindByAll(""+tvEXPExamCat.getICveMdoTrans(),"");
                                          if(vPuesto != null && vPuesto.size() > 0){
                                             for(int j = 0; j < vPuesto.size(); j++){
                                               TVGRLPuesto tvPuesto = (TVGRLPuesto)vPuesto.get(j);
                                                  if((request.getParameter("iCvePuesto"+i) != null && request.getParameter("iCvePuesto"+i).equals(tvPuesto.getICvePuesto()+"|"+tvPuesto.getICveCategoria())) ||
                                                      tvEXPExamCat.getICvePuesto() == tvPuesto.getICvePuesto() )
                                                     out.print("<option value=\""+tvPuesto.getICvePuesto()+"|"+tvPuesto.getICveCategoria()+ "\" selected >"+tvPuesto.getCDscPuesto()+"</option>");
                                                  else
                                                     out.print("<option value=\""+tvPuesto.getICvePuesto()+"|"+tvPuesto.getICveCategoria()+ "\" >"+tvPuesto.getCDscPuesto()+"</option>");
                                             }
                                          }
%>                                        </select> 
                                        </td>


<!-- Motivos -->
                                        <td class="ETablaC" >
                                          <input type="hidden" name="iCveMotivoInicial<%=i%>" value="<%=tvEXPExamCat.getICveMotivo()%>">
                                          <select name="iCveMotivo<%=i%>" disabled >
                                            <option value="0">Seleccione...</option>
<%
                                          if(vMotivo != null && vMotivo.size() > 0){
                                             for(int j = 0; j < vMotivo.size(); j++){
                                               TVGRLMotivo tvMotivo = (TVGRLMotivo)vMotivo.get(j);
                                                  if((request.getParameter("iCveMotivo"+i) != null && Integer.parseInt(request.getParameter("iCveMotivo"+i)) == tvMotivo.getICveMotivo()) ||
                                                      tvEXPExamCat.getICveMotivo() == tvMotivo.getICveMotivo() )
                                                     out.print("<option value=\""+tvMotivo.getICveMotivo()+"\" selected >"+tvMotivo.getCDscMotivo()+"</option>");
                                                  else
                                                     out.print("<option value=\""+tvMotivo.getICveMotivo()+"\" >"+tvMotivo.getCDscMotivo()+"</option>");
                                             }
                                          }
%>                                        </select>
                                        </td>
                                      </tr>
<%                                   }
%>                                  <tr>
                                      <td class="ETablaC" colspan="5" ><%= (vEti.clsAnclaTexto("EAnclaC","Modificar", "javascript:if(fValidaSelectores())fLiberar('pg070107030.jsp');","exp")) %></td>
                                    </tr>
<%                                }
                            }
                            else{
%>                             <tr>
                                 <td class="ETablaT" colspan="6">No Hay Registro Coincidente</td>
                               </tr>
<%                          }
%>
     </table>
    </td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td>
    </td></tr>
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
