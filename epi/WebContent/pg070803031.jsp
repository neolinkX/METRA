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
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070803031CFG  clsConfig = new pg070803031CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070803031.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070803031.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg0702061.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Solicitud|";    // modificar
  String cCveOrdenar  = "almsolicsumin.icvesolicsum|";  // modificar
  String cDscFiltrar  = "Solicitud|";    // modificar
  String cCveFiltrar  = "almsolicsumin.icvesolicsum|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                 // modificar
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
  String cUpdStatus  = "SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Si";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  String cArticulo = "";
  String cFamilia = "";
  String cDscTpoArticulo = "";
  DecimalFormat df = new DecimalFormat("##,###,##0.00");
  String cValor = "";
  float iExistencias = 0;
  TFechas dtFecha = new TFechas();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
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
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
       if (bs.getFieldValue("cDscFamilia", "")!=null && bs.getFieldValue("cDscFamilia", "").toString().compareTo("")!=0){
          cFamilia = bs.getFieldValue("cDscFamilia", "").toString();
          cDscTpoArticulo = bs.getFieldValue("cDscTpoArticulo", "").toString();
          iExistencias = new Double(bs.getFieldValue("dExistencia", "0").toString()).floatValue();
          cArticulo = bs.getFieldValue("cDscArticulo","").toString();
          bs.firstPage();
       }
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if (request.getParameter("hdCampoClave") != null) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">


  <input type="hidden" name="iAnio" value="<% if (request.getParameter("iAnio") != null) out.print(request.getParameter("iAnio"));%>">
  <input type="hidden" name="iCvePeriodo" value="<% if (request.getParameter("iCvePeriodo") != null) out.print(request.getParameter("iCvePeriodo"));%>">
  <input type="hidden" name="iCveTpoArticulo" value="<% if (request.getParameter("iCveTpoArticulo") != null) out.print(request.getParameter("iCveTpoArticulo"));%>">
  <input type="hidden" name="iCveFamilia" value="<% if (request.getParameter("iCveFamilia") != null) out.print(request.getParameter("iCveFamilia"));%>">
  <input type="hidden" name="lProgramada" value="<% if (request.getParameter("lProgramada") != null) out.print(request.getParameter("lProgramada"));%>">

  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
            &nbsp;
            <% if (bs != null){ %>
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
              <tr>
                <td class="ETablaT" colspan="2">Concentrado</td>
              </tr>
              <tr>
                <td class="EEtiqueta">Articulo:</td>
                <td class="ETabla"><%=cArticulo%></td>
              </tr>

              <tr>
                <td class="EEtiqueta">Familia:</td>
                <td class="ETabla"><%=cFamilia%></td>
              </tr>

              <tr>
                <td class="EEtiqueta">Tipo de Artículo:</td>
                <td class="ETabla"><%=cDscTpoArticulo%></td>
              </tr>

              <tr>
                <td class="EEtiqueta">Existencias:</td>
                <td class="ETabla">
                   <%
                      cValor = df.format(Double.parseDouble(Double.toString(iExistencias)));
                      out.print(cValor);
                   %>
                   <input type="hidden" name="iExistencias" value="<%=iExistencias%>">
                </td>
              </tr>
                <tr>
                 <td class="EEtiqueta">Núm. Solicitud:</td>
                 <td class="ETabla" colspan="1">
                   <% TDALMSolicSumin DSolic = new TDALMSolicSumin();
                      Vector vSolic = DSolic.FindByAllCustom(" WHERE S.lRevisada = 0 AND S.lAutorizada = 1 ", " ORDER BY S.iCveSolicSum");
                      if (vSolic==null)
                        vSolic = new Vector();
                      out.println("<select name=\"iCveSolicitud\" size=\"1\">");
                      if (request.getParameter("iCveSolicitud")==null || (request.getParameter("iCveSolicitud")!=null&&request.getParameter("iCveSolicitud").equals("0")))
                        out.println("<option selected value\"0\">Seleccione...</option>");
                      else
                        out.println("<option value\"0\">Seleccione...</option>");
                      if (vSolic != null && vSolic.size() > 0){
                        for (int i=0; i<vSolic.size();i++){
                          TVALMSolicSumin VSolic = (TVALMSolicSumin)vSolic.get(i);
                          if (request.getParameter("iCveSolicitud")!=null && request.getParameter("iCveSolicitud").equals(""+VSolic.getICveSolicSum()))
                            out.println("<option selected value\"" + VSolic.getICveSolicSum() + "\">" + VSolic.getICveSolicSum() + "</option>");
                          else
                            out.println("<option value\"" + VSolic.getICveSolicSum() + "\">" + VSolic.getICveSolicSum() + "</option>");
                        }
                      }
                      out.print("</select>");
                   %>
                 </td>
               </tr>
            </table>
            &nbsp;
            <% } %>

            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
              <tr>
                <td class="ETablaT" colspan="7">Detalle del Concentrado de Solicitudes</td>
              </tr>
              <tr>
                <td class="ETablaT">Solicitud</td>
                <td class="ETablaT">Unidad<br>Medica</td>
                <td class="ETablaT">Módulo</td>
                <td class="ETablaT">Área</td>
                <td class="ETablaT">Unidades<br>Solicitadas</td>
                <td class="ETablaT">Unidades<br>Autorizadas</td>
                <td class="ETablaT">Diferencia</td>
              </tr>
              <%
                 float fDiferencia = 0;
                 float dTotalUniSolicitadas = 0;
                 if (bs != null){
                     bs.start();
                     while(bs.nextRow()){
                         out.println("<tr>");
                         out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveSolicSum", "&nbsp;")));
                         out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("cDscUniMed", "&nbsp;")));
                         out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("cDscModulo", "&nbsp;")));
                         out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("cDscArea", "&nbsp;")));

                         if (bs.getFieldValue("dUnidSolicita")!=null && bs.getFieldValue("dUnidSolicita").toString().compareTo("")!=0)
                            cValor = df.format(Double.parseDouble(bs.getFieldValue("dUnidSolicita","0").toString()));
                         else
                            cValor = "&nbsp;";

                         out.print(vEti.Texto("ETablaR",cValor));
                         out.print("<input type=\"hidden\" name=\"dUnidSolicita"+bs.getFieldValue("iCveSolicSum", "0").toString()+"\" value=\""+bs.getFieldValue("dUnidSolicita", "0").toString()+"\">");
                         if (bs.getFieldValue("lAnalizada", "0").toString().compareTo("0")==0)
                            out.print(vEti.CeldaCampo("EEtiqueta","text",10,10,"dUnidAutor"+bs.getFieldValue("iCveSolicSum", "0").toString(), "",0,"Suma(this,"+"document.forms[0].dDiferencia"+bs.getFieldValue("iCveSolicSum", "0").toString()+","+"dUnidSolicita"+bs.getFieldValue("iCveSolicSum", "0").toString()+");","fMayus(this);",false,true,true));
                         else{
                            if (bs.getFieldValue("dUnidAutor")!=null && bs.getFieldValue("dUnidAutor").toString().compareTo("")!=0)
                               cValor = df.format(Double.parseDouble(bs.getFieldValue("dUnidAutor","0").toString()));
                            else
                               cValor = "&nbsp;";
                            out.print(vEti.CeldaCampo("EEtiqueta","text",10,10,"dUnidAutor"+bs.getFieldValue("iCveSolicSum", "0").toString(), cValor,0,"","fMayus(this);",false,true,false));
                            out.print("<input type=\"hidden\" name=\"dUnidAutor"+bs.getFieldValue("iCveSolicSum", "0").toString()+"\" value=\""+cValor+"\">");
                         }

                         out.print(vEti.CeldaCampo("EEtiqueta","text",10,10,"dDiferencia"+bs.getFieldValue("iCveSolicSum", "0").toString(),"",0,"","fMayus(this);",false,true,true));
                         out.print("</tr>");
                         dTotalUniSolicitadas += new Double(bs.getFieldValue("dUnidSolicita", "0").toString()).floatValue();
                     }
                     out.println("<tr>");
                     out.print(vEti.TextoCS("EEtiqueta","Total:",4));

                     cValor = df.format(Double.parseDouble(Double.toString(dTotalUniSolicitadas)));
                     out.print(vEti.Texto("EEtiqueta",cValor));

                     out.print(vEti.CeldaCampo("EEtiqueta","text",10,10,"dTotUniAuto","",0,"","fMayus(this);",false,true,true));
                     out.print(vEti.CeldaCampo("EEtiqueta","text",10,10,"dTotDif","",0,"","fMayus(this);",false,true,true));

                     out.print("</tr>");
                 }
              %>
            </table>

          &nbsp;

          <table border="1" class="ETablaInfo" align="center">
              <td>
                 <%  out.print(vEti.clsAnclaTexto("EAncla","Autorizar","JavaScript:fAccion('Autorizar');", "Autorizar")); %>
               </td>
            </tr>
          </table>

      <script language="JavaScript">
         form = document.forms[0];
         for (i = 0; i < form.length; i++){
            str = form.elements[i].name;
            if (str.substring(0,11)=="dDiferencia")
               form.elements[i].readOnly = true;
         }
         if (form.dTotUniAuto)
           form.dTotUniAuto.readOnly = true;

         if (form.dTotDif)
           form.dTotDif.readOnly = true;


        var dTotUniAuto = 0;
        for (i = 0; i < form.length; i++){
           str = form.elements[i].name;
           if (str.substring(0,10)=="dUnidAutor"){
              if (form.elements[i].value!='')
                  dTotUniAuto = dTotUniAuto + parseFloat(form.elements[i].value);
           }
        }
        document.forms[0].dTotUniAuto.value = dTotUniAuto;

      </script>
      </td>
   </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>


