<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070201011CFG  clsConfig = new pg070201011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070201011.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad(); cli();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070201011.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
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
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  String hdLCondicion = "";
  String hdLOrdenar= "";
  String FILNumReng= "";
  String          hdRowNum= "";
  String          iCvePersonal= request.getParameter("iCvePersonal");
  String         cCvePersonal=request.getParameter("cCvePersonal");
  String         iCveUniMed=request.getParameter("iCveUniMed");
  String         iCveModulo=request.getParameter("iCveModulo");
  String         iCveProceso=request.getParameter("iCveProceso");
  String          iNumExamen=request.getParameter("iNumExamen");
  String         iCveServicio= "";
  String         lIniciado= "1";
  String          lConcluido= "0";
  String          lAplicado= "1";
  String          lDictamen= "0";
  String          iCveExpediente=request.getParameter("iCveExpediente");
  String          iCvePerfil=request.getParameter("iCvePerfil");
  String         ramaInicial= "0";
  String          iCveMdoTrans=request.getParameter("iCveMdoTrans");
  String DIR ="";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
   
  }
  

</SCRIPT>

<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null){hdLCondicion =request.getParameter("hdLCondicion"); out.print(hdLCondicion);}%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null){ hdLOrdenar =request.getParameter("hdLOrdenar"); out.print(hdLOrdenar);}%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null){ FILNumReng = request.getParameter("FILNumReng"); out.print(FILNumReng);} else{ FILNumReng=vParametros.getPropEspecifica("NumRengTab"); out.print(FILNumReng);}%>">
  <input type="hidden" name="hdRowNum" value="<%hdRowNum=cPosicion;out.print(cPosicion);%>">
  <input type="hidden" name="iCvePersonal" value="<%out.print(iCvePersonal);%>">
  <input type="hidden" name="cCvePersonal" value="<%out.print(cCvePersonal);%>">
  <input type="hidden" name="iCveUniMed" value="<%out.print(iCveUniMed);%>">
  <input type="hidden" name="iCveModulo" value="<%out.print(iCveModulo);%>">
  <input type="hidden" name="iCveProceso" value="<%out.print(iCveProceso);%>">
  <input type="hidden" name="iNumExamen" value="<%out.print(iNumExamen);%>">
  <input type="hidden" name="iCveServicio" value="">
  <input type="hidden" name="lIniciado" value="1">
  <input type="hidden" name="lConcluido" value="0">
  <input type="hidden" name="lAplicado" value="1">
  <input type="hidden" name="lDictamen" value="0">
  <input type="hidden" name="iCveExpediente" value="<%out.print(iCveExpediente);%>">
  <input type="hidden" name="iCvePerfil" value="<%out.print(iCvePerfil);%>">
  <input type="hidden" name="ramaInicial" value="0">
  <input type="hidden" name="iCveMdoTrans" value="<%out.print(iCveMdoTrans);%>">



  <table background="<%= vParametros.getPropEspecifica("RutaImg")%>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value=""></td>
      <td valign="top">
                        <%
                        TVPERDatos vPerDatos = null;
                        try{
                          vPerDatos = new TDPERDatos().findUser(Integer.parseInt(""+request.getParameter("iCvePersonal"),10));
                        }catch(Exception e){}

                        if(vPerDatos!=null){
                        %>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Personal Registrado para el Examen
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Expediente:</td>
                              <td class="ECampo"><%=vPerDatos.getICveExpediente()%></td>
                              <td class="EEtiqueta">R.F.C.:</td>
                              <td class="ECampo"><%=vPerDatos.getCRFC()%></td>
                              <td class="EEtiqueta">C.U.R.P.:</td>
                              <td class="ECampo" ColSpan="3"><%=vPerDatos.getCCURP()%></td>
                              <input type="hidden" name="cGenero" value="<%=vPerDatos.getCSexo()%>">
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Nombre:</td>
                              <td class="ECampo"colspan="7"><%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%></td>
                            </tr>
                          </table>&nbsp;

                        <%
                        }
                        %>

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                             <%
                               Vector vcServicios = null;
                               try{
                                  vcServicios = new TDEXPServicio().FindByExpLastExamen(Integer.parseInt(""+request.getParameter("iCvePersonal"),10),Integer.parseInt(""+request.getParameter("iNumExamen"),10));
                               }catch(Exception e){}

                               if(!vcServicios.isEmpty()){
                                 out.print("<tr>");
                                 out.print("<td class=\"ETablaT\">Listado de Servicios por Aplicar</td>");
                                 out.print("</tr>");
                                 TVEXPServicio vServ;
                                 for(int i = 0; i < vcServicios.size(); i++){
                                   vServ = (TVEXPServicio) vcServicios.get(i);
                                   out.print("<tr><td align=\"center\">");
                                   //DIR = ""+vServ.getICveServicio();
                                   DIR = "1";
                                   out.print(vEti.clsAnclaTexto("EAncla",vServ.getCDscServicio(),"javascript:fIrA("+vServ.getICveServicio()+");",vServ.getCDscServicio(),vServ.getCDscServicio()));
                                   out.print("</tr></td>");
                                 }
                               }else{
                                   out.print("<tr>");
                                   out.print(vEti.Texto("ECampo", "No existen datos coincidentes con el filtro proporcionado."));
                                   out.print("</tr>");
                               }
                                


                            %>
                          </table>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
  
 </table>
 <SCRIPT LANGUAGE="JavaScript">
     
  function cli(){
  javascript:fIrA(<%=DIR%>)     
  }
</SCRIPT>Genero el java script");
</form>
</body>

<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>

</html>
