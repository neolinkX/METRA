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
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070104061CFG  clsConfig = new pg070104061CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104061.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104061.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070104061.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Ficha|Expediente|";    // modificar
  String cCveOrdenar  = "EA.icveUniMed, EA.iCveModulo, EA.iCveProceso, EA.iFolioES|EA.icveUniMed, EA.iCveModulo, EA.iCveProceso, EA.iCveExpediente|";  // modificar
  String cDscFiltrar  = "Ficha|Expediente|";    // modificar
  String cCveFiltrar  = "EA.iFolioES|EA.iCveExpediente|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  int iEPIProceso  = new Integer(vParametros.getPropEspecifica("EPIProceso")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = "Hidden";
  String cOtroStatus = "Hidden";
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

  TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
  TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
  Vector vcGRLUSUMedicos = new Vector();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fSelectedPer(iCvePersonal,iCveExpediente,iNumExamen){
    var form = document.forms[0];
    form.hdiCveExpediente.value = iCvePersonal;
    form.target = "_self";
    form.submit();
  }

 function llenaSLT(opc,val1,val2,val3,val4,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070104061P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&val4=" + val4 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070104061P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

  function fVerExamen(iCveExpediente,iNumExamen,iCveServicio,iCveProceso){
      cInicio = "";
      form = document.forms[0];

      cInicio = "?hdiCveExpediente=" + iCveExpediente;
      cInicio += "&hdiNumExamen=" + iNumExamen;
      cInicio += "&hdICveServicio=" + iCveServicio;
      cInicio += "&hdICveProceso=" + iCveProceso;

      if((wExp != null) && (!wExp.closed))
        wExp.focus();

//       wExp = open("pg070104021.jsp"+cInicio, "Consulta",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=600,height=300,screenX=800,screenY=600');
       wExp = open("pg070104070.jsp"+cInicio, "Consulta",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=800,height=300,screenX=800,screenY=600');
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
//       fSetModal();
   }


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
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdiCveExpediente" value="">
  <input type="hidden" name="hdiNumExamen" value="">


  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                             <%
                             

                               int iUniMed = 0, iModulo = 0;
                               out.print("<tr>");
                               out.print("<td class='ETablaT' colspan='5'>Operadores por Sevicio</td>");
                               out.print("</tr>");
                               out.println("<tr>");
                                out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                out.println("<td class='ECampo' >");
                                out.println(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(4,this.value,"+vUsuario.getICveusuario()+",'','',document.forms[0].iCveModulo);",dUMUsuario.getUniMedxUsu(vUsuario.getICveusuario()),"iCveUniMed", "cDscUniMed", request, "0", true));
                                out.println("</td>");
                                //System.out.println("antes " + request.getParameter("iCveUniMed") + "   " + request.getParameter("iCveModulo"));
                              
                                if(request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareTo("")!=0 && (!request.getParameter("iCveUniMed").equals("null")) && (!request.getParameter("iCveUniMed").equals("-1")))
                                iUniMed = Integer.valueOf(request.getParameter("iCveModulo").toString(),10).intValue() ;
                                if(request.getParameter("iCveModulo")!=null && request.getParameter("iCveModulo").compareTo("")!=0 && (!request.getParameter("iCveModulo").equals("null")))
                                iModulo = Integer.valueOf(request.getParameter("iCveModulo").toString(),10).intValue() ;
                                
                                out.println(vEti.Texto("EEtiqueta","Módulo:"));
                                if (request.getParameter("iCveModulo") == null){

                                  out.println("<td class='ECampo' colspan='2'>");
                                  out.println("<SELECT NAME='iCveModulo' SIZE='1' onChange=\"llenaSLT(5,document.forms[0].iCveUniMed.options[document.forms[0].iCveUniMed.selectedIndex].value,"+ vUsuario.getICveusuario() + ",this.options[this.selectedIndex].value,'',document.forms[0].iCveServicio);\" ");
                                  out.println("</SELECT>");
                                  out.println("</td>");
                                }
                                else{
                                  //System.out.println("Parte tres");
                                  TDGRLModulo dModulo = new TDGRLModulo();
                                  out.println("<td colspan='2'>");
                                  out.println(vEti.SelectOneRowSinTD("iCveModulo","llenaSLT(5,document.forms[0].iCveUniMed.options[document.forms[0].iCveUniMed.selectedIndex].value,"+ vUsuario.getICveusuario() + ",this.options[this.selectedIndex].value,'',document.forms[0].iCveServicio);"
                                                                     ,dModulo.FindByAll("where iCveUniMed =" + iUniMed), "iCveModulo", "cDscModulo", request, "0", true));
                                  out.println("</td>");
                                }
                              out.println("</tr>");
                               out.println("<tr>");
                               TFechas dtFecha = new TFechas();
                               out.println(vEti.EtiCampo("EEtiqueta","Fecha:", "ETabla","text",10,10,"dtFechaI",dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/"),0,"","fValFecha(this.value);",true,true, true, request));
                               out.println(vEti.ObjRadioCE("EEtiqueta","De:","ETabla", "RSFecha","1","Solicitud","1","","","",0,true,true));
                               out.println("<td class=\"ETabla\">" + vEti.ObjRadioSE("ETabla", "RSFecha","2","Aplicación","1","","","",0,true,true) + "</td>");

                              vcGRLUSUMedicos = dGRLUSUMedicos.FindServConsulta(vUsuario.getICveusuario(), iUniMed, iModulo);
                              Vector vRama = new Vector();
                              if(request.getParameter("iCveServicio") != null){

                                 vRama = new TDGRLUSUMedicos().findRamas(vUsuario.getICveusuario(),Integer.parseInt(request.getParameter("iCveUniMed"),10), 1, Integer.parseInt(request.getParameter("iCveModulo"),10), Integer.parseInt(request.getParameter("iCveServicio"),10));
                              }
                              if(request.getParameter("iCveServicio")==null || request.getParameter("iCveServicio").compareTo("")==0) {
                   
                                 vGRLUSUMedicos.setCDscServicio("Seleccione...");
                                 vGRLUSUMedicos.setICveServicio(0);
                                 vcGRLUSUMedicos.add(vGRLUSUMedicos);
                              }
                              else if(request.getParameter("iCveServicio")!=null && Integer.parseInt(request.getParameter("iCveServicio"))<1 ){

                                 vGRLUSUMedicos.setCDscServicio("Seleccione...");
                                 vGRLUSUMedicos.setICveServicio(0);
                                 vcGRLUSUMedicos.add(vGRLUSUMedicos);
                               }
                               out.println("</tr><tr>");
                               out.print(vEti.Texto("EEtiqueta", "Servicio:"));
                               out.println("<td colspan='2'>");
                               out.println(vEti.SelectOneRowSinTD("iCveServicio", "llenaSLT(6,document.forms[0].iCveUniMed.options[document.forms[0].iCveUniMed.selectedIndex].value,"+ vUsuario.getICveusuario() + ",document.forms[0].iCveModulo.options[document.forms[0].iCveModulo.selectedIndex].value,this.options[this.selectedIndex].value,document.forms[0].iCveRama);"
                               , vcGRLUSUMedicos, "iCveServicio", "cDscServicio", request, "0"));
                               out.println("</td>");
                               out.print(vEti.Texto("EEtiqueta", "Rama:"));
                               out.println("<td colspan='2'>");
                               out.println(vEti.SelectOneRowSinTD("iCveRama","",
                                                             vRama,
                                                             "iCveRama","cDscRama",request,"0",true,clsConfig.getLVariosMed()==1?true:false));
                               out.println("</td>");
                               out.println("</tr><tr>");
                               String iSituacion = request.getParameter("RSSitua") !=  null? request.getParameter("RSSitua").toString():"1";

                               out.println(vEti.ObjRadioCE("EEtiqueta","Situación:","ETabla", "RSSitua","1","Pendientes",iSituacion,"","","",0,true,true));
                               out.println("<td class=\"ETabla\">" + vEti.ObjRadioSE("ETabla", "RSSitua","2","Atendidos",iSituacion,"","","",0,true,true) + "</td>");
                               out.println("<td class=\"ETabla\" colspan=\"2\">" + vEti.ObjRadioSE("ETabla", "RSSitua","3","Todos",iSituacion,"","","",0,true,true) + "</td>");
                               out.println("</tr>");
                           %>
                          </table>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                             <%
                               // Resultado de la Búsqueda
                               TVEXPAtenServ vAtenServ ;
                               if(clsConfig.vResultado.size() > 0){
                                   
                                  out.println("<tr>");
                                  out.println(vEti.Texto("ETablaT", "Ficha"));
                                  out.println(vEti.Texto("ETablaT", "Expediente"));
                                  out.println(vEti.Texto("ETablaT", "RFC"));
                                  out.println(vEti.Texto("ETablaT", "Nombres"));
                                  if(clsConfig.getLVariosMed() == 1)
                                    out.println(vEti.Texto("ETablaT", "Rama"));
                                  out.println(vEti.Texto("ETablaT", "Atendido"));
                                  out.println(vEti.Texto("ETablaT\" colspan=\"2", "Por"));

                                  out.println("</tr>");
                                  for(int i= 0; i < clsConfig.vResultado.size(); i++){

                                   out.print("<tr>");
                                      vAtenServ = new TVEXPAtenServ();
                                      vAtenServ = (TVEXPAtenServ) clsConfig.vResultado.get(i);
                                      out.println(vEti.Texto("ETablaR", String.valueOf(vAtenServ.VExamAplica.getIFolioEs())));
                                      out.println(vEti.Texto("ETablaR", String.valueOf(vAtenServ.VServ.getICveExpediente())));
                                      out.println(vEti.Texto("ETabla", vAtenServ.VServ.getCRFC()));
                                      out.println(vEti.Texto("ETabla", vAtenServ.VServ.getCNombre()));
                                      // Despliegue varios Médicos
                                      if(clsConfig.getLVariosMed() == 1){
                                         out.println(vEti.Texto("ETabla", vAtenServ.VRama.getCDscRama()));
                                         out.println(vEti.Texto(vAtenServ.VRama.getIConcluido() == 1? "EPositivosC" : "ETablaC",
                                                                vAtenServ.VRama.getIConcluido() == 1? "SI" : "NO" ));
                                         if(vAtenServ.VRama.getIConcluido() == 1){
                                           out.println("<td>");
                                           out.print(vEti.clsAnclaTexto("EAncla","Resultado","JavaScript:fVerExamen("
                                                                        + vAtenServ.VServ.getICveExpediente() + ", " + vAtenServ.VExamAplica.getINumExamen()
                                                                        + ", " + vAtenServ.VServ.getICveServicio() + ", " + vParametros.getPropEspecifica("EPIProceso") + ");", "Ver Examen",""));
                                           out.println("</td>");
                                         }
                                         else
                                           out.println(vEti.Texto("ETabla", "&nbsp;"));
                                      }
                                      else{
                                         out.println(vEti.Texto(vAtenServ.VServ.getLConcluido() == 1? "EPositivosC" : "ETablaC",
                                                                vAtenServ.VServ.getLConcluido() == 1? "SI" : "NO" ));
                                         if(vAtenServ.VServ.getLConcluido() == 1){
                                           out.println("<td>");
                                           out.print(vEti.clsAnclaTexto("EAncla","Resultado","JavaScript:fVerExamen("
                                                                        + vAtenServ.VServ.getICveExpediente() + ", " + vAtenServ.VExamAplica.getINumExamen()
                                                                        + ", " + vAtenServ.VServ.getICveServicio() + ", " + vParametros.getPropEspecifica("EPIProceso") + ");", "Ver Examen",""));
                                           out.println("</td>");
                                         }
                                         else
                                           out.println(vEti.Texto("ETabla", "&nbsp;"));

                                      }
                                      out.println(vEti.Texto("ETabla", vAtenServ.VMedico.getCNombre() != null ? vAtenServ.VMedico.getCNombre(): "&nbsp;" ));
                                   out.print("</tr>");
                                  }
                               }
                               else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='5'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                   out.print("</tr>");
                               }

                            %>
                          </table>
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
