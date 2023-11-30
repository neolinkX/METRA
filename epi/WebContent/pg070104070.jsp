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
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070104070CFG  clsConfig = new pg070104070CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104070.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104070.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101011.jsp";       // modificar
  String cDetalle    = "pg070101011.jsp";       // modificar
  String cDiagnostico    = "pg070101020.jsp";       // modificar
  String cRecomendaciones    = "pg070101030.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  int iServicioCardio = new Integer(vParametros.getPropEspecifica("EPIServicioCardio")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();
  TFechas dtFecha = new TFechas();

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

  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

  TDEXPDiagnostico dEXPDiagnostico = new TDEXPDiagnostico();
  TVEXPDiagnostico vEXPDiagnostico = new TVEXPDiagnostico();
  Vector vcEXPDiagnostico = new Vector();

  TDEXPRecomendacion dEXPRecomendacion = new TDEXPRecomendacion();
  TVEXPRecomendacion vEXPRecomendacion = new TVEXPRecomendacion();
  Vector vcEXPRecomendacion = new Vector();

  int iCveLabC = new Integer(vParametros.getPropEspecifica("CveLabClin")).intValue();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg070104021.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"Audio02.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  function Genera_Doc(){

   form = document.forms[0];
   form.target="_self";
   form.hdReporte.value = 'Reporte';
   form.submit();
}

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cStyle = '<link rel="stylesheet" href="/medprev/wwwrooting/estilos/07_estilos.css" TYPE="text/css">';

  var aSel = new Array();
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

  <%

  %>

  <input type="hidden" name="hdiCveExpediente" value="<%=request.getParameter("hdiCveExpediente")%>">
  <input type="hidden" name="hdiNumExamen" value="<%=request.getParameter("hdiNumExamen")%>">
  <input type="hidden" name="hdICveServicio" value="<%=request.getParameter("hdICveServicio")%>">
  <input type="hidden" name="hdICveRama" value="<%=request.getParameter("hdICveRama")%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")%>">


<%
   if (request.getParameter("hdReporte") != null){
     if(request.getParameter("hdReporte").compareTo("Reporte") ==0)
      out.println(clsConfig.getActiveX());
   }
 %>

  <input type="hidden" name="hdReporte" value="">


  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr class="EPie"><td>&nbsp;<input type="hidden" name="hdBoton" value=""></td></tr>
  <tr><td>
    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
      <tr><td class="ETablaT" colspan="4">Datos del Personal</td></tr>
  <% // Encabezado del documento
    TVDinRep02 vEncabezado = new TVDinRep02();
    vEncabezado = clsConfig.getVEXPDatos();
    out.print("<tr>");
    out.println(vEti.Texto("EEtiqueta","Expediente:").toString() + vEti.Texto("ETabla",vEncabezado.getString("iCveExpediente")).toString());
    out.println(vEti.Texto("EEtiqueta","Edad:").append(vEti.Texto("ETabla", "" + clsConfig.getEdadPersonal(vEncabezado.getDate("dtNacimiento")) ) ));
    out.print("</tr>");
    out.print("<tr>");
    out.println(vEti.Texto("EEtiqueta","RFC:").append(vEti.Texto("ETabla",vEncabezado.getString("cRFC"))));
    out.println(vEti.Texto("EEtiqueta","Sexo:").append(vEti.Texto("ETabla","F".compareTo(vEncabezado.getString("cSexo")) == 0? "Mujer" : "Hombre" )));
    out.print("</tr>");
    out.println("<tr>" + vEti.Texto("EEtiqueta","Nombre:").append(vEti.TextoCS("ETabla", vEncabezado.getString("cApPaterno") + "&nbsp;" + vEncabezado.getString("cApMaterno") + "&nbsp;" + vEncabezado.getString("cNombre"),3)).toString() + "</tr>");
    out.println("<tr><td class='ETablaST' colspan='4'>Datos del Examen</td></tr>");
    out.println("<tr>" + vEti.Texto("EEtiqueta","Proceso:").toString() + vEti.TextoCS("ETabla",vEncabezado.getString("cDscProceso"),3).toString() + "</tr>");
    out.print("<tr>");
    out.println(vEti.Texto("EEtiqueta","Unidad M&eacute;dica:").append(vEti.Texto("ETabla",vEncabezado.getString("cDscUniMed"))));
    out.println(vEti.Texto("EEtiqueta","M&oacute;dulo:").append(vEti.Texto("ETabla", vEncabezado.getString("cDscModulo"))));
    out.print("</tr>");
    out.print("<tr>");
    out.println(vEti.Texto("EEtiqueta","Solicitado:").append(vEti.Texto("ETabla",dtFecha.getFechaDDMMYYYY(vEncabezado.getDate("dtSolicitado"),"/"))));
    out.println(vEti.Texto("EEtiqueta","Realizado:").append(vEti.Texto("ETabla", dtFecha.getFechaDDMMYYYY(vEncabezado.getDate("dtAplicacion"),"/"))));
    out.print("</tr>");

  %>
    </table>
  </td></tr>
  <tr><td class="ETablaR"><%= vEti.clsAnclaTexto("EAncla","Imprimir","JavaScript:fPrint();", "Ver Examen","") %></td></tr>
  <tr>
  <td valign="top">
   <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
    <%
      // Información del Servicio
      int iServicioActual  = Integer.parseInt(request.getParameter("hdICveServicio").toString());
      int iServLC  = Integer.parseInt(vParametros.getPropEspecifica("CveLabClin").toString());
      String cMedicoAplica = null;
      out.println("<tr><td class='ETablaT' colspan='6'>" + vEncabezado.getString("cDscServicio") + "</td></tr>");
      // Impresión de los Resultados
      if (clsConfig.getVSintomas() != null && clsConfig.getVSintomas().size() > 0){
        if(vEncabezado.getInt("lVariosMeds") == 0){
           out.println("<tr>");
           out.println(vEti.Texto("EEtiqueta","Servicio Efectuado:").append(vEti.TextoCS("ETabla",dtFecha.getFechaDDMMYYYY(vEncabezado.getDate("dtFin"),"/"),5)));
           out.println("<tr>");
           cMedicoAplica = vEncabezado.getString("cSiglasProf") + "&nbsp;" + vEncabezado.getString("cMedico") + "<br>" + vEncabezado.getString("cCedula");
        }
        out.println("<tr><td colspan='6' class='ETablaT'>Resultados</td></tr>");
        // Variables requeridas
        int iRama = 0, iCol = 0, iContador = 0;;
        int iServOdontologia = Integer.parseInt(vParametros.getPropEspecifica("EPIServicioOdonto").toString());

        String cEtiquetas  = "";
        TVDinRep02 vSintoma = null;
        for(int i=0; i < clsConfig.getVSintomas().size(); i++){ // Despliegue de resultados
          vSintoma = new TVDinRep02();
          vSintoma = (TVDinRep02) clsConfig.getVSintomas().get(i);
          // Titulo de la Rama
          if(iRama != vSintoma.getInt("iCveRama")){
            if(iRama > 0){
            out.println("<tr class='EPie' colspan='6'>&nbsp;</tr>");
            }
            out.println("<tr>");
            if(vEncabezado.getInt("lVariosMeds") == 0)
              out.println(vEti.TextoCS("ETablaT",vSintoma.getString("cDscRama"),6));
            else{
              out.println(vEti.TextoCS("ETablaT",vSintoma.getString("cDscRama"),4));    
              if(vSintoma.getInt("lConcluido") == 1){
                out.println(vEti.Texto("ETablaSTR","Servicio Efectuado:").append(vEti.Texto("ETablaST",dtFecha.getFechaDDMMYYYY(vSintoma.getDate("dtFin"),"/"))));
                // Persona que efectuó el examen
                if(iServicioActual != iServLC){
                  out.println("<tr>");
                  out.println(vEti.TextoCS("ETablaSTR","Realizado Por:",2));
                  out.println(vEti.TextoCS("ETablaST", vSintoma.getString("cSiglasProf") + "&nbsp;" + vSintoma.getString("cMedico") + "&nbsp;" +  vSintoma.getString("cCedula") ,4));
                  cMedicoAplica = vSintoma.getString("cSiglasProf") + "&nbsp;" + vSintoma.getString("cMedico") + "<br>" + vSintoma.getString("cCedula");
                  out.println("</tr>");
                }
              }
              else
                out.println(vEti.TextoCS("EEtiquetaL","No Concluido",2));
            }
            out.println("</tr>");
            iRama = vSintoma.getInt("iCveRama");
            iContador = 0;
            // Impresión de los Títulos para Resultado y Valor de Referencia del Laboratorio
            if(vSintoma.getInt("lConcluido") == 1 &&
               iServicioActual               == iServLC){
               out.println("<tr>");
               for(int r = 0; r < 2; r++){
                  out.println(vEti.Texto("EPTablaSTC","&nbsp;"));
                  out.println(vEti.Texto("EPTablaSTC","Resultado"));
                  out.println(vEti.Texto("EPTablaSTC","Valor de Referencia"));
               }
               out.println("</tr>");
            }
          } // Título de la Rama
          if(vSintoma.getInt("lConcluido") == 1){ // La rama está concluida por lo tanto se despliega la información
            iCol ++;
            // Mostrar el odontograma
            if(iServOdontologia == iServicioActual && iContador < 33){
              iContador++;
              if (iContador == 1)
                 out.print("<tr><td colspan ='6'><table align='center'class='ETablaInfo' border='1'><tr>");
              cEtiquetas += vEti.Texto("ETablaT",vSintoma.getString("cPregunta"));
            } // Mostrar odontograma
            else{
              if(iCol == 3 || vSintoma.getInt("iCveTpoResp") == 4 || vSintoma.getInt("iCveTpoResp") == 6){
                out.println("</tr>");
                iCol = 1;
              }
              if(iCol == 1){
                out.println("<tr>");
              }
              // Mostrar pregunta de manera normal
              if(vSintoma.getInt("iCveTpoResp") == 6){
                out.println(vEti.TextoCS("ETablaST",vSintoma.getString("cPregunta") + ":", 6));
                iCol = 2;
              }
              else
                out.println(vEti.Texto("EPEtiqueta",vSintoma.getString("cPregunta") + ":"));
            }
            String cEtiqueta = "&nbsp;" + vSintoma.getString("cEtiqueta");
            switch(vSintoma.getInt("iCveTpoResp")){
              case 1:
              case 2:
                out.println(vEti.Texto("EPTabla",vSintoma.getString("cResultado") + cEtiqueta));
                break;
              case 4:
                if(iServicioActual == 12){
                    out.println(vEti.TextoCS("EPTabla",vSintoma.getString("cResultado"), 4));
                }
                else{
                    out.println(vEti.TextoCS("EPTabla",vSintoma.getString("cResultado") + cEtiqueta, 4));
                }
                //System.out.println("4.-"+vSintoma.getString("cResultado") + cEtiqueta);
                iCol = 2;
                break;
              case 3:
              case 5:
                out.println(vEti.Texto("EPTablaR",vSintoma.getString("cResultado") + cEtiqueta));
                break;
              case 7:
            } // Tipo de Respuesta
            // Presentar Etiqueta y Valores de Referencia
            if((iServOdontologia != iServicioActual && vSintoma.getInt("iCveTpoResp") != 6 )
                || iContador == 33)
              out.println(vEti.Texto("EPPie", vSintoma.getString("cValRef") + "&nbsp;"));
            if(iContador == 16){
              out.print("</tr><tr>" + cEtiquetas + "</tr>");
              cEtiquetas = "";
            } // iContador == 16
            if(iContador == 32){
              out.print("</tr><tr>" + cEtiquetas + "</tr></table></td></tr>");
              iContador = 33;
              iCol = 0;
            }
          } // Rama concluida
        } // Despliegue de resultados
      } // fin del ciclo para presentar los resultados
    %>
   </table>
  </td></tr>
  <tr><td class="EPie">&nbsp;</td><tr>
  <tr><td valign="top">
   <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
   <%
      // Impresión de Diagnósticos, Recomendaciones, Nota Médica y Firma del Médico que emite la Nota
      if(vEncabezado.getInt("lReqDiag") == 1){
         out.println("<tr><td class='ETablaT'>Diagnósticos</td></tr>");
         int iEspecialidad = 0;
         for(int i=0; i< clsConfig.getVDiagnostico().size(); i++){
            vEXPDiagnostico = (TVEXPDiagnostico) clsConfig.getVDiagnostico().get(i);
            if(iEspecialidad != vEXPDiagnostico.getICveEspecialidad()){
               iEspecialidad = vEXPDiagnostico.getICveEspecialidad();
               out.println("<tr><td class='ETablaST' >" + vEXPDiagnostico.getCDscEspecialidad() + "</td></tr>");
            }
            out.println("<tr><td class='ETabla'>" + vEXPDiagnostico.getCDscDiagnostico() + "</td></tr>");
         }
         out.println("<tr><td class='ETablaT'>Recomendaciones</td></tr>");
         int iRecomendacion = 0;
         for(int i=0; i < clsConfig.getVRemendacion().size(); i++){
            vEXPRecomendacion = (TVEXPRecomendacion) clsConfig.getVRemendacion().get(i);
            if(iRecomendacion != vEXPRecomendacion.getICveEspecialidad()){
              iRecomendacion = vEXPRecomendacion.getICveEspecialidad();
              out.println("<tr><td class='ETablaST'>" + vEXPRecomendacion.getCDscEspecialidad() + "</td></tr>");
            }
            out.println("<tr><td class='ETabla'>" + vEXPRecomendacion.getCDscRecomendacion() + "</td></tr>");
         }
         out.print("<tr><td class='ETablaT'>Nota Médica</td></tr>");
         out.print("<tr><td class='ETabla'>" + vEncabezado.getString("cNotaMedica") + "</td></tr>");
      }// Se imprimen diagnósticos y Recomendaciones
    %>
   </table>
  </td></tr>
  <%
    if(iServicioActual != iServLC){
      out.println("<tr><td class='ETablaC'><br><b>" + cMedicoAplica + "</b></td></tr>");
    }
   if(iServicioCardio == Integer.parseInt(request.getParameter("hdICveServicio"))){
      out.print("<tr><td class='ETablaC'>");
      out.print(vEti.clsAnclaTexto("EAncla","Impresión en el Electrocardiograma","JavaScript:Genera_Doc();", "Ver Examen",""));
      out.print("</td></tr>");
   }
   //Inserción para visualizar las gráficas de Audiometría
   if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
       vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))) {
       TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
       TVEXPAudiomet vEXPAudiomet;
       Vector vcAudio = new Vector();
       vcAudio = dEXPAudiomet.FindByAll(" where iCveExpediente = " + vEncabezado.getString("iCveExpediente") + " and iNumExamen = " + vEncabezado.getString("iNumExamen") +" AND iCveServicio = "+request.getParameter("hdICveServicio"));
       if(vcAudio.size() > 0){
         out.print("<SCRIPT LANGUAGE='JavaScript'>");
         for (int i = 0; i < vcAudio.size(); i++){
            vEXPAudiomet = (TVEXPAudiomet) vcAudio.get(i);
            out.print("aSel["+i+"]=["+vEXPAudiomet.getIOido()+","+vEXPAudiomet.getITipo()+
                                   ","+vEXPAudiomet.getIX()+","+vEXPAudiomet.getIY()+"];");
         }
         out.print("</SCRIPT>");
       }
              out.print("<tr><td>&nbsp;</td></tr>");
              out.print("<tr><td>");
              out.print("<table table border='1' border='0' align='center' width='524'><tr><td class='ETablaT'>Oido Derecho</td></tr></table>");
              out.print("<SCRIPT LANGUAGE='JavaScript'>");
              out.print("fGraphAudioV(1,aSel);");
              out.print("</SCRIPT>");
              out.print("</table>");
              out.print("</td></tr>");
              out.print("<tr><td>");
              out.print("<table table border='1' border='0' align='center' width='524'><tr><td class='ETablaT'>Oido Izquierdo</td></tr></table>");
              out.print("<SCRIPT LANGUAGE='JavaScript'>");
              out.print("fGraphAudioV(2,aSel);");
              out.print("</SCRIPT>");
              out.print("</table>");
              out.print("</td></tr>");
      }

  %>
  </td></tr>
  <tr><td class="ETablaR"><%= vEti.clsAnclaTexto("EAncla","Imprimir","JavaScript:fPrint();", "Ver Examen","") %></td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
   

   
   