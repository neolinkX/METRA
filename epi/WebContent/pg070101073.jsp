<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.*" %>


<html>
<%
  pg070101073CFG  clsConfig = new pg070101073CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070101073.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101073.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101073.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Lote Presuntivo|No.Análisis|";    // modificar
  String cCveOrdenar  = "iCveLoteCualita|iCveAnalisis|";  // modificar
  String cDscFiltrar  = "Lote Presuntivo|No.Análisis|";    // modificar
  String cCveFiltrar  = "iCveLoteCualita|iCveAnalisis|";  // modificar
  String cTipoFiltrar = "7|7|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar

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
  String cUpdStatus  = clsConfig.getUpdStatus(); //"SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cEstatusIR   = clsConfig.cImprimir;            // modificar
  String cCanWrite   = "yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  String cAnio = "";
  String cvLoteConfirmativo = "";
  int ivCuantos = 0;

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function llenaSLT(opc,val1,val2,val3,objDes,objDes2,objDes3){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070101073P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 +
                                               "&objDes=" + objDes.name + "&objDes2=" + objDes2.name +
                                               "&objDes3=" + objDes3.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070101073P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 +
                                           "&objDes=" + objDes.name + "&objDes2=" + objDes2.name +
                                           "&objDes3=" + objDes3.name + '&hdAccion=' + document.forms[0].action;
    }
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
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="iCveProceso" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Búsqueda  del Detalle del Exámen</td>
           </tr><tr>
             <td class="EEtiqueta">Proceso:</td>
             <td class="ECampo">
             <%
                 out.print(vEti.SelectOneRowSinTD("SLSProceso","llenaSLT(1,document.forms[0].SLSProceso.value,'','',document.forms[0].SLSMotivo,document.forms[0].SLSServicio,document.forms[0].SLSRama);", (Vector) AppCacheManager.getColl("GRLProceso",""),"iCveProceso","cDscProceso",request,"0",true));
             %>
             </td>
             <td class="EEtiqueta">Motivo:</td>
             <td class="ETabla">
             <%
               if (request.getParameter("SLSProceso") != null){
                   if (new Integer(request.getParameter("SLSProceso").toString()).intValue() != 0)
                      out.print(vEti.SelectOneRowSinTD("SLSMotivo","llenaSLT(2,document.forms[0].SLSProceso.value,document.forms[0].SLSMotivo.value,'',document.forms[0].SLSServicio,document.forms[0].SLSRama,'');", (Vector) AppCacheManager.getColl("GRLMotivo", request.getParameter("SLSProceso").toString()),"iCveMotivo","cDscMotivo",request,"0",true));
                   else {
                      out.print("<select name=\"SLSMotivo\" size=\"1\" onChange=\"llenaSLT(2,document.forms[0].SLSProceso.value,document.forms[0].SLSMotivo.value,'',document.forms[0].SLSServicio,document.forms[0].SLSRama,'');\">");
                      out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                      out.print("</select>");
                   }
               }
                else {
                    out.print("<select name=\"SLSMotivo\" size=\"1\" onChange=\"llenaSLT(2,document.forms[0].SLSProceso.value,document.forms[0].SLSMotivo.value,'',document.forms[0].SLSServicio,document.forms[0].SLSRama,'');\">");
                    out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    out.print("</select>");
                }
             %>
             </td>
             </tr><tr>
             <td class="EEtiqueta">Servicio:</td>
             <td class="ECampo">
               <%
               if (request.getParameter("SLSMotivo") != null){
                   if (new Integer(request.getParameter("SLSProceso").toString()).intValue() != 0 &&
                       new Integer(request.getParameter("SLSMotivo").toString()).intValue() != 0)
                     out.println(vEti.SelectOneRowSinTD("SLSServicio", "llenaSLT(3,document.forms[0].SLSServicio.value,'','',document.forms[0].SLSRama,'','');", (Vector) AppCacheManager.getColl("MEDServicio",request.getParameter("SLSProceso").toString() + "|" + request.getParameter("SLSMotivo").toString()), "iCveServicio", "cDscServicio", request, "07",true));
                   else {
                     out.print("<select name=\"SLSServicio\" size=\"1\" onChange=\"llenaSLT(3,document.forms[0].SLSServicio.value,'','',document.forms[0].SLSRama,'','');\">");
                     out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                     out.print("</select>");
                   }

               } else {
                  out.print("<select name=\"SLSServicio\" size=\"1\" onChange=\"llenaSLT(3,document.forms[0].SLSServicio.value,'','',document.forms[0].SLSRama,'','');\">");
                  out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                  out.print("</select>");
               }
               %>
             </td>
             <td class="EEtiqueta">Rama:</td>
             <td class="ECampo">
                <%
               if (request.getParameter("SLSServicio") != null){
                   if (new Integer(request.getParameter("SLSServicio").toString()).intValue() != 0){
                     if (request.getParameter("SLSRama") != null){
                            Vector vExCuali = new Vector();
                            vExCuali = (Vector) AppCacheManager.getColl("MEDRama",request.getParameter("SLSServicio").toString() + "|"); 

                            out.print("<select name=\"SLSRama\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                            if (request.getParameter("SLSRama").toString().compareToIgnoreCase("0") == 0)
                                out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                            else
                                out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                            for (int i=0;i<vExCuali.size();i++){
                               TVMEDRama vMEDRama = new TVMEDRama();
                               vMEDRama = (TVMEDRama) vExCuali.get(i);
                               if (new Integer(vMEDRama.getICveRama()).toString().compareToIgnoreCase(request.getParameter("SLSRama").toString()) == 0)
                                  out.print("<option selected value=\"" + new Integer(vMEDRama.getICveRama()).toString() + "\">" + vMEDRama.getCDscRama() + "</option>");
                               else
                                  out.print("<option value=\"" + new Integer(vMEDRama.getICveRama()).toString() + "\">" + vMEDRama.getCDscRama() + "</option>");
                            }
                            out.print("</select>");
                     } 
                   }

                   else {
                     out.print("<select name=\"SLSRama\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                     out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                     out.print("</select>");
                   }
               } else {
                  out.print("<select name=\"SLSRama\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                  out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                  out.print("</select>");
               }
                %>
             </td>
           </tr>

           <%
           out.println("<tr>");
           out.print("<td class=\"ETablaC\" colspan=\"5\">");
           out.print(vEti.clsAnclaTexto("EAncla","Configuración del Examen","JavaScript:fIrConfig('" + request.getParameter("SLSProceso") + "'," + "'');","Detalle del Examen"));
           out.print("</td>");
           out.println("</tr>");
           %>


         </table>
      </td>
      </tr>
      <tr>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                            <%
                            Vector vGRLMdoTrans = new Vector();
                            vGRLMdoTrans = clsConfig.vGRLMdoTrans;
                            ivCuantos = vGRLMdoTrans.size();

                            if (bs != null) {
                            %>
                            <tr>
                              <td colspan="<%= clsConfig.ivCuantos + 3 %>" class="ETablaT">Sintomas</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Pregunta</td>
                              <td class="ETablaT">Tipo de Respuesta</td>
                              <td class="ETablaT">General<input type="checkbox" name="chkGeneral" OnClick="ChangeAllCheck(document, this.checked , 'TBXGral-', 'TBXSel-')"></td>
                            <% } %>
                              <%
                                 if (!vGRLMdoTrans.isEmpty()){
                                    for (int i=0;i<vGRLMdoTrans.size();i++){
                                       TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
                                       VGRLMdoTrans = (TVGRLMdoTrans) vGRLMdoTrans.get(i);
                                       if (bs != null)
                                         out.print(vEti.Texto("ETablaT", VGRLMdoTrans.getCDscMdoTrans().toString()));
                                    }
                                 }
                              %>


                            </tr>
                             <% // modificar según listado
                               if (bs != null){
                                   int m = 0;
                                   bs.start();
                                   while(bs.nextRow()){
                                     boolean lPinto = false;
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiquetaL", bs.getFieldValue("cPregunta", "&nbsp;").toString()));
                                     switch (new Integer(bs.getFieldValue("iCveTpoResp", "&nbsp;").toString()).intValue()){
                                       case 1:{
                                         out.print(vEti.Texto("ETabla", "Si/No"));
                                         break;
                                       }
                                       case 2:{
                                         out.print(vEti.Texto("ETabla", "Cadena"));
                                         break;
                                       }
                                       case 3:{
                                         out.print(vEti.Texto("ETabla", "Número"));
                                         break;
                                       }
                                       case 4:{
                                         out.print(vEti.Texto("ETabla", "Texto"));
                                         break;
                                       }
                                       case 5:{
                                         out.print(vEti.Texto("ETabla", "Rango"));
                                         break;
                                       }
                                       case 6:{
                                         out.print(vEti.Texto("ETabla", "Título"));
                                         break;
                                       }
                                       case 7:{
                                         out.print(vEti.Texto("ETabla", "Cadena"));
                                         break;
                                       }
                                       case 8:{
                                         out.print(vEti.Texto("ETabla", "Combo Box"));
                                         break;
                                       }
                                       case 9:{
                                         out.print(vEti.Texto("ETabla", "Imagen"));
                                         break;
                                       }
                                       case 13:{
                                           out.print(vEti.Texto("ETabla", "Combo Multiple"));
                                           break;
                                         }
                                       case 14:{
                                           out.print(vEti.Texto("ETabla", "Hora"));
                                           break;
                                         }
                                       case 15:{
                                           out.print(vEti.Texto("ETabla", "Texto"));
                                           break;
                                         }
                                       case 16:{
                                           out.print(vEti.Texto("ETabla", "Texto"));
                                           break;
                                         }
                                       
                                     }

                                     if (!vGRLMdoTrans.isEmpty()){
                                       Vector vMEDSintExamen = new Vector();
                                       vMEDSintExamen = clsConfig.vMEDSintExamen;
                                       boolean lPrincipal = false;

                                       if (!vMEDSintExamen.isEmpty()){
                                         for(int jj=0;jj<vMEDSintExamen.size();jj++){
                                            TVMEDSintExamen VMEDSintExamen = new TVMEDSintExamen();
                                            VMEDSintExamen = (TVMEDSintExamen) vMEDSintExamen.get(jj);
                                            if (VMEDSintExamen.getICveSintoma() == new Integer(bs.getFieldValue("iCveSintoma", "&nbsp;").toString()).intValue() &&
                                                VMEDSintExamen.getICveMdoTrans() == 0) {
                                                out.print(vEti.ToggleMov("ETablaC", "TBXGral-" + bs.getFieldValue("iCveSintoma", "&nbsp;").toString()
                                                                        ,"1","fHabDes(this," + ivCuantos + ");", 04, true, "Sí", "No", true));
                                                lPrincipal = true;
                                            }
                                         }
                                         if (!lPrincipal){
                                            out.print(vEti.ToggleMov("ETablaC", "TBXGral-" + bs.getFieldValue("iCveSintoma", "&nbsp;").toString()
                                                                    ,"0","fHabDes(this," + ivCuantos + ");", 04, true, "Sí", "No", true));
                                         }
                                       } else
                                            out.print(vEti.ToggleMov("ETablaC", "TBXGral-" + bs.getFieldValue("iCveSintoma", "&nbsp;").toString()
                                                                     ,"0","fHabDes(this," + ivCuantos + ");", 04, true, "Sí", "No", true));

                                       for (int i=0;i<vGRLMdoTrans.size();i++){
                                         lPinto = false;
                                         TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
                                         VGRLMdoTrans = (TVGRLMdoTrans) vGRLMdoTrans.get(i);

                                         if (!vMEDSintExamen.isEmpty()){
                                           for(int j=0;j<vMEDSintExamen.size();j++){
                                             TVMEDSintExamen VMEDSintExamen = new TVMEDSintExamen();
                                             VMEDSintExamen = (TVMEDSintExamen) vMEDSintExamen.get(j);
                                             if (bs.getFieldValue("iCveSintoma", "&nbsp;").toString().compareToIgnoreCase(new Integer(VMEDSintExamen.getICveSintoma()).toString()) == 0 &&
                                                 new Integer(VGRLMdoTrans.getICveMdoTrans()).toString().compareToIgnoreCase(new Integer(VMEDSintExamen.getICveMdoTrans()).toString()) == 0 ){
                                                 if (!lPrincipal)
                                                    out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveSintoma", "&nbsp;").toString() +
                                                                                                    VGRLMdoTrans.getICveMdoTrans()
                                                                                       ,"1" ,"", 59, true, "Sí", "No", true));
                                                 else
                                                    out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveSintoma", "&nbsp;").toString() +
                                                                                                    VGRLMdoTrans.getICveMdoTrans()
                                                                                       ,"1" ,"", 59, true, "Sí", "No", false));
                                                lPinto = true;
                                             }
                                           }
                                           if (!lPinto) {
                                            out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveSintoma", "&nbsp;").toString() +
                                                                                            VGRLMdoTrans.getICveMdoTrans()
                                                                     ,"0" ,"", 59, true, "Sí", "No", true));
                                           }
                                         }
                                         else {
                                            out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveSintoma", "&nbsp;").toString() +
                                                                                            VGRLMdoTrans.getICveMdoTrans()
                                                                     ,"0" ,"", 59, true, "Sí", "No", true));
                                                lPinto = true;
                                         }
                                       }
                                     }
                                     out.print("</tr>");
                                   }
                               }
                            %>
                          </table>
      </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
<script language="JavaScript">
function fHabDes(obj,ivCuantos){
       form = document.forms[0];
       var iCuantos = 0;
       for(var i=0;i<form.elements.length;i++){
         var e = form.elements[i];
         if(e.type == "checkbox"){
            if(e.name == obj.name){
               for (var j=1;j<=ivCuantos;j++){
                 if (document.forms[0].elements[i].checked == true){
                    document.forms[0].elements[i + j].disabled = true;
                    document.forms[0].elements[i + j].checked  = true;
                 }
                 else {
                    document.forms[0].elements[ i + j].disabled = false;
                    document.forms[0].elements[ i + j].checked  = false;
                 }
               }
           }
         }
       }
  return true;
}

  function ChangeAllCheck(doc, Checked, cSubNombre, cSubNombre2){
     form = doc.forms[0];
     for (i = 0; i < form.length; i++){
        str = form.elements[i].name;
        if (str.substring(0,cSubNombre.length) == cSubNombre)
           form.elements[i].checked = Checked;
        if (str.substring(0,cSubNombre2.length) == cSubNombre2){
           form.elements[i].checked = Checked;
           form.elements[i].disabled = Checked;
        }
     }
  }

</script>


 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
