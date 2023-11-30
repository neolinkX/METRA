<%
/**
 *
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
  String cPagina = "";
  if(request.getParameter("cPagina")!=null)
    cPagina = request.getParameter("cPagina").toString();
  pg070104070CFG  clsConfig = new pg070104070CFG(cPagina);               // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070105021.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070105021.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");

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

  TDEXPDiagnostico dEXPDiagnostico = new TDEXPDiagnostico();
  TVEXPDiagnostico vEXPDiagnostico = new TVEXPDiagnostico();
  Vector vcEXPDiagnostico = new Vector();

  TDEXPRecomendacion dEXPRecomendacion = new TDEXPRecomendacion();
  TVEXPRecomendacion vEXPRecomendacion = new TVEXPRecomendacion();
  Vector vcEXPRecomendacion = new Vector();

  int iCveLabC = new Integer(vParametros.getPropEspecifica("CveLabClin")).intValue();
  int iServicioCardio = new Integer(vParametros.getPropEspecifica("EPIServicioCardio")).intValue();
  TFechas dtFecha = new TFechas();
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
     <font size=2>
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
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  
           if(clsConfig.getAccesoValido()){
           
           //audiometria cellpadding="0"
           if (vParametros.getPropEspecifica("2").equals(request.getParameter("hdICveServicio")) || vParametros.getPropEspecifica("2").equals(request.getParameter("hdICveServicio")) || vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
        vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))){ 
%>
    <tr><td valign="top">
    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="0"><% // Inicio de Datos %>
      <tr><td class="ETablaT" colspan="4">Datos del Personal y del Examen</td></tr>
 <%
    }else{
  %>
        <tr><td valign="top">
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
        <tr><td class="ETablaT" colspan="4">Datos del Personal</td></tr>
    <%  }
    // Encabezado del documento
    TVDinRep02 vEncabezado = new TVDinRep02();
    vEncabezado = clsConfig.getVEXPDatos();
    out.print("<tr>");
    out.println(vEti.Texto("EEtiqueta","Expediente:").toString() + vEti.Texto("ETabla",vEncabezado.getString("iCveExpediente")).toString());
    out.println(vEti.Texto("EEtiqueta","Edad:").append(vEti.Texto("ETabla", "" + clsConfig.getEdadPersonal(vEncabezado.getDate("dtNacimiento")) ) ));
    out.print("</tr>");
    out.print("<tr>");
    out.println(vEti.Texto("EEtiqueta","RFC:").append(vEti.Texto("ETabla",vEncabezado.getString("cRFC"))));
    out.println(vEti.Texto("EEtiqueta","Sexo:").append(vEti.Texto("ETabla","F".compareTo(vEncabezado.getString("cSexo")) == 0? "Femenino" : "Masculino" )));
    out.print("</tr>");
    out.println("<tr>" + vEti.Texto("EEtiqueta","Nombre:").append(vEti.TextoCS("ETabla", vEncabezado.getString("cApPaterno") + "&nbsp;" + vEncabezado.getString("cApMaterno") + "&nbsp;" + vEncabezado.getString("cNombre"),3)).toString() + "</tr>");
    if (!(vParametros.getPropEspecifica("2").equals(request.getParameter("hdICveServicio")))) { 
        out.println("<tr><td class='ETablaST' colspan='4'>Datos del Examen</td></tr>");}
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
  <tr>
  <td valign="top">
<%        //audiometria cellpadding="0"
        if (vParametros.getPropEspecifica("2").equals(request.getParameter("hdICveServicio")) || vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
        vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))) { %>      
   <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="0"><% // Inicio de Datos 
       }else{%>
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos 
       }    
    %>
    <%
      // Información del Servicio
      int iServicioActual  = Integer.parseInt(request.getParameter("hdICveServicio").toString());
      int iServLC  = Integer.parseInt(vParametros.getPropEspecifica("CveLabClin").toString());
      String cMedicoAplica = null;
      String cMedicoAplica2 = null;
       if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
       vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))) {
            out.println("<tr><td class='ETablaT' colspan='6'>" + vEncabezado.getString("cDscServicio") + "   Servicio Efectuado: "+ dtFecha.getFechaDDMMYYYY(vEncabezado.getDate("dtFin"),"/")); 
       }else{
      out.println("<tr><td class='ETablaT' colspan='6'>" + vEncabezado.getString("cDscServicio") + "</td></tr>");
      }
      
      // Impresión de los Resultados
      if (clsConfig.getVSintomas() != null && clsConfig.getVSintomas().size() > 0){
        if(vEncabezado.getInt("lVariosMeds") == 0){
             if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
            vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))) {
                 out.println("");
            }else{
               out.println("<tr>");
               out.println(vEti.Texto("EEtiqueta","Servicio Efectuado:").append(vEti.TextoCS("ETabla",dtFecha.getFechaDDMMYYYY(vEncabezado.getDate("dtFin"),"/"),5)));
               out.println("<tr>");
        }
          cMedicoAplica = vEncabezado.getString("cSiglasProf") + "&nbsp;" + vEncabezado.getString("cMedico") + "<br>" + vEncabezado.getString("cCedula");
          cMedicoAplica2 = vEncabezado.getString("cSiglasProf") + "&nbsp;" + vEncabezado.getString("cMedico") + " " + vEncabezado.getString("cCedula");
        }
        if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
            vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))) {
                out.println("<tr><td colspan='6' class='ETablaT'>Resultados de AUDIOMETRÍA TONAL</td></tr>");}
        else{
            out.println("<tr><td colspan='6' class='ETablaT'>Resultados</td></tr>");

                //System.out.println("Servicio:"+request.getParameter("hdICveServicio"));
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
            //out.println("<tr><td colspan='6' class='ETabla'>El servicio "+request.getParameter("hdICveServicio")+"</td></tr>");
        }
        
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
            if(vEncabezado.getInt("lVariosMeds") == 0){
                    if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
                        vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))) {
                        out.println("");
                        }
              else{
              out.println(vEti.TextoCS("ETablaT",vSintoma.getString("cDscRama"),6));}
            }else{
              out.println(vEti.TextoCS("ETablaT",vSintoma.getString("cDscRama"),4));
              if(vSintoma.getInt("lConcluido") == 1){
                out.println(vEti.Texto("ETablaSTR","Servicio Efectuado:").append(vEti.Texto("ETablaST",dtFecha.getFechaDDMMYYYY(vSintoma.getDate("dtFin"),"/"))));
                // Persona que efectuó el examen
                if(iServicioActual != iServLC){
                  out.println("<tr>");
                  out.println(vEti.TextoCS("ETablaSTR","Realizado Por:",2));
                  out.println(vEti.TextoCS("ETablaST", vSintoma.getString("cSiglasProf") + "&nbsp;" + vSintoma.getString("cMedico") + "&nbsp;" +  vSintoma.getString("cCedula") ,4));
                  cMedicoAplica = vSintoma.getString("cSiglasProf") + "&nbsp;" + vSintoma.getString("cMedico") + "<br>" + vSintoma.getString("cCedula");
                  cMedicoAplica2 = vSintoma.getString("cSiglasProf") + "&nbsp;" + vSintoma.getString("cMedico") + " " + vSintoma.getString("cCedula");
                  out.println("</tr>");
                }                 
              }
              else
                out.println(vEti.TextoCS("EEtiquetaL","No Concluido",2));
            }
            out.println("</tr>");
            iRama = vSintoma.getInt("iCveRama");
            iContador = 0;
          }

          
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
                out.println(vEti.Texto("EEtiqueta",vSintoma.getString("cPregunta") + ":"));
            }
            switch(vSintoma.getInt("iCveTpoResp")){
              case 1:
              case 2:
                out.println(vEti.Texto("ETabla",vSintoma.getString("cResultado")));
                break;
              case 4:
                out.println(vEti.TextoCS("ETabla",vSintoma.getString("cResultado"),4));
                iCol = 2;
                break;
              case 3:
              case 5:
                out.println(vEti.Texto("ETablaR",vSintoma.getString("cResultado")));
                break;
            } // Tipo de Respuesta
            // Presentar Etiqueta y Valores de Referencia
            if((iServOdontologia != iServicioActual && vSintoma.getInt("iCveTpoResp") != 6 )
                || iContador == 33)
              out.println(vEti.Texto("ETabla",vSintoma.getString("cEtiqueta") + " " + vSintoma.getString("cValRef")));
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
  
  <tr><td valign="top">
   <%//audiometria cellpadding="0"
        if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
        vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))) { %>
              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="0"><% // Inicio de Datos 
       }else{ %>
              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
   <%
      }
      // Impresión de Diagnósticos, Recomendaciones, Nota Médica y Firma del Médico que emite la Nota
      if(vEncabezado.getInt("lReqDiag") == 1){
          if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
                        vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))) {
                             out.println("<tr><td class='ETablaT'>Diagnósticos</td>");
                             int iEspecialidad = 0;
                             for(int i=0; i< clsConfig.getVDiagnostico().size(); i++){
                                vEXPDiagnostico = (TVEXPDiagnostico) clsConfig.getVDiagnostico().get(i);
                                if(iEspecialidad != vEXPDiagnostico.getICveEspecialidad()){
                                   iEspecialidad = vEXPDiagnostico.getICveEspecialidad();
                                   out.println("<tr><td class='ETablaST' >" + vEXPDiagnostico.getCDscEspecialidad() + "</td></tr>");
                                }
                                out.println("<td class='ETabla'>" + vEXPDiagnostico.getCDscDiagnostico() + "</td></tr>");
                             }
                             out.println("<tr><td class='ETablaT'>Recomendaciones</td>");
                             int iRecomendacion = 0;
                             for(int i=0; i < clsConfig.getVRemendacion().size(); i++){
                                vEXPRecomendacion = (TVEXPRecomendacion) clsConfig.getVRemendacion().get(i);
                                if(iRecomendacion != vEXPRecomendacion.getICveEspecialidad()){
                                  iRecomendacion = vEXPRecomendacion.getICveEspecialidad();
                                  out.println("<td class='ETablaST'>" + vEXPRecomendacion.getCDscEspecialidad() + "</td>");
                                }
                                out.println("<td class='ETabla'>" + vEXPRecomendacion.getCDscRecomendacion() + "</td></tr>");
                             }
                             out.print("<tr><td class='ETablaT'>Nota Médica</td>");
                             out.print("<td class='ETabla'>" + vEncabezado.getString("cNotaMedica") + "</td></tr>");
                             if(iServicioActual != iServLC){
                             out.print("<tr><td class='ETablaT'>Medico</td>");
                             out.print("<td class='ETabla'>" + cMedicoAplica2 + "</td></tr>");}
                       }
          else{
                             out.println("<tr><td class='ETablaT'>Diagnósticos</td></tr>");
                             int iEspecialidad = 0;
                             for(int i=0; i< clsConfig.getVDiagnostico().size(); i++){
                                vEXPDiagnostico = (TVEXPDiagnostico) clsConfig.getVDiagnostico().get(i);
                                if(iEspecialidad != vEXPDiagnostico.getICveEspecialidad()){
                                   iEspecialidad = vEXPDiagnostico.getICveEspecialidad();
                                   out.println("<tr><td class='ETablaST' >" + vEXPDiagnostico.getCDscEspecialidad() + "</td></tr>");
                                }
                                out.println("<td class='ETabla'>" + vEXPDiagnostico.getCDscDiagnostico() + "</td></tr>");
                             }
                             out.println("<tr><td class='ETablaT'>Recomendaciones</td></tr>");
                             int iRecomendacion = 0;
                             for(int i=0; i < clsConfig.getVRemendacion().size(); i++){
                                vEXPRecomendacion = (TVEXPRecomendacion) clsConfig.getVRemendacion().get(i);
                                if(iRecomendacion != vEXPRecomendacion.getICveEspecialidad()){
                                  iRecomendacion = vEXPRecomendacion.getICveEspecialidad();
                                  out.println("<td class='ETablaST'>" + vEXPRecomendacion.getCDscEspecialidad() + "</td></tr>");
                                }
                                out.println("<td class='ETabla'>" + vEXPRecomendacion.getCDscRecomendacion() + "</td></tr>");
                             }
                             out.print("<tr><td class='ETablaT'>Nota Médica</td></tr>");
                             out.print("<td class='ETabla'>" + vEncabezado.getString("cNotaMedica") + "</td></tr>");
                             
      }}// Se imprimen diagnósticos y Recomendaciones
    %>
   </table>
  <%
    if(iServicioActual != iServLC){
          if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
                             vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))) {
                             out.print("");
          }else{
          out.println("<tr><td class='ETablaC'><br><b>" + cMedicoAplica + "</b></td></tr>");}
    }
   if(iServicioCardio == Integer.parseInt(request.getParameter("hdICveServicio"))){
      out.print("<tr><td class='ETablaC'>");
      out.print(vEti.clsAnclaTexto("EAncla","Impresión en el Electrocardiograma","JavaScript:Genera_Doc();", "Ver Examen",""));
      out.print("</td></tr>");
   }
      
      //grafica nueva
     %>
</td></tr>

     <% 
   //Inserción para visualizar las gráficas de Audiometría
     Vector vaudioy = new Vector(); 
     Vector vaudiox = new Vector();
     String y = "";
     String x = "";
     

   if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
       vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))) {
%>
  <tr><td valign="top">
     
     <table width="600" border="1" class="ETablaInfo" align="center">
     <tr>
     <td class="ETablaT" ><div align="center">Oido Derecho</div></td>
     <td class="ETablaT"><div align="center">Oido Izquierdo</div></td>
     </tr>
     </table>
     <table width="600" class="ETablaInfo" border="0" align="center">
     <tr>
      <td><div align="center">

<%         
       TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
       TVEXPAudiomet vEXPAudiomet;
       Vector vcAudio = new Vector();
       vcAudio = dEXPAudiomet.FindByAll(" where iCveExpediente = " + vEncabezado.getString("iCveExpediente") + " and iNumExamen = " + vEncabezado.getString("iNumExamen") +" AND iCveServicio = "+request.getParameter("hdICveServicio"));
       if(vcAudio.size() > 0){
//         out.print("<SCRIPT LANGUAGE='JavaScript'>");
         for (int i = 0; i < vcAudio.size(); i++){
            vEXPAudiomet = (TVEXPAudiomet) vcAudio.get(i);
 //           out.print("aSel["+i+"]=["+vEXPAudiomet.getIOido()+","+vEXPAudiomet.getITipo()+
 //                                  ","+vEXPAudiomet.getIX()+","+vEXPAudiomet.getIY()+"];");
                      
           y = String.valueOf(vEXPAudiomet.getIY());
           vaudioy.addElement(y);
           
           x = String.valueOf(vEXPAudiomet.getIX());
           vaudiox.addElement(x);
         }
//         out.print("</SCRIPT>");
       }
       
       
 /*      
      out.print("<tr><td>&nbsp;</td></tr>");
      out.print("<tr><td>");
      out.print("<table table border='1' border='0' align='center' width='424'><tr><td class='ETablaT'>Oido Derecho</td></tr></table>");
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
      out.print("</td></tr>");*/
               
          
          
          

     //out.print("<table width=\"750\" border=\"1\" class=\"ETablaInfo\" align=\"center\">");
     //out.print("<tr>");
     //out.print("<td class=\"ETablaT\" ><div align=\"center\">Oido Derecho</div></td>");
     //out.print("<td class=\"ETablaT\"><div align=\"center\">Oido Izquierdo</div></td>");
     //out.print("</tr>");
     //out.print("</table>");
     //out.print("<table width=\"750\" border=\"0\" align=\"center\">");
     //out.print("<tr>");
      //out.print("<td><div align=\"center\">");
      out.print("<table width=\"250\" class=\"ETablaInfo\" height=\"0\" border=\"0\" align=\"center\" cellspacing=\"0\" background=\""+cRutaImg+"cuadricula.jpg\">");
      
      //Variables
      String temp ="";
      temp = vaudioy.elementAt(0) +"";
      int y0 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(1) +"";
      int y1 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(2) +"";
      int y2 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(3) +"";
      int y3 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(4) +"";
      int y4 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(5) +"";
      int y5 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(6) +"";
      int y6 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(7) +"";
      int y7 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(8) +"";
      int y8 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(9) +"";
      int y9 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(10) +"";
      int y10 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(11) +"";
      int y11 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(12) +"";
      int y12 = Integer.parseInt(temp.trim());
      temp = vaudioy.elementAt(13) +"";
      int y13 = Integer.parseInt(temp.trim());
      
      
      
      temp = vaudiox.elementAt(0) +"";
      int x0 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(1) +"";
      int x1 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(2) +"";
      int x2 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(3) +"";
      int x3 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(4) +"";
      int x4 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(5) +"";
      int x5 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(6) +"";
      int x6 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(7) +"";
      int x7 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(8) +"";
      int x8 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(9) +"";
      int x9 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(10) +"";
      int x10 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(11) +"";
      int x11 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(12) +"";
      int x12 = Integer.parseInt(temp.trim());
      temp = vaudiox.elementAt(13) +"";
      int x13 = Integer.parseInt(temp.trim());
      
      
      for (int i = 0; i < 31; i++){
              out.print("<tr>");
              if(i==0 || i > 29){
              out.print("<td height=\"21\">&nbsp;&nbsp;</td>");
              out.print("<td height=\"21\">&nbsp;&nbsp;</td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
                /*out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");*/
                }
              else{                  
              out.print("<td height=\"21\">&nbsp;&nbsp;</td>");
              out.print("<td height=\"21\">&nbsp;&nbsp;</td>");
              
                if(i == y0 &&  1 == x0 || i == y1 &&  1 == x1 ||
                   i == y2 &&  1 == x2 || i == y3 &&  1 == x3 ||
                   i == y4 &&  1 == x4 || i == y5 &&  1 == x5 ||
                   i == y6 &&  1 == x6){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido05.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
              
                if(i == y0 &&  2 == x0 || i == y1 &&  2 == x1 ||
                   i == y2 &&  2 == x2 || i == y3 &&  2 == x3 ||
                   i == y4 &&  2 == x4 || i == y5 &&  2 == x5 ||
                   i == y6 &&  2 == x6){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido05.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
              
                if(i == y0 &&  3 == x0 || i == y1 &&  3 == x1 ||
                   i == y2 &&  3 == x2 || i == y3 &&  3 == x3 ||
                   i == y4 &&  3 == x4 || i == y5 &&  3 == x5 ||
                   i == y6 &&  3 == x6){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido05.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
             
                if(i == y0 &&  4 == x0 || i == y1 &&  4 == x1 ||
                   i == y2 &&  4 == x2 || i == y3 &&  4 == x3 ||
                   i == y4 &&  4 == x4 || i == y5 &&  4 == x5 ||
                   i == y6 &&  4 == x6){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido05.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
                

                if(i == y0 &&  5 == x0 || i == y1 &&  5 == x1 ||
                   i == y2 &&  5 == x2 || i == y3 &&  5 == x3 ||
                   i == y4 &&  5 == x4 || i == y5 &&  5 == x5 ||
                   i == y6 &&  5 == x6){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido05.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
                
                if(i == y0 &&  6 == x0 || i == y1 &&  6 == x1 ||
                   i == y2 &&  6 == x2 || i == y3 &&  6 == x3 ||
                   i == y4 &&  6 == x4 || i == y5 &&  6 == x5 ||
                   i == y6 &&  6 == x6){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido05.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
                
                if(i == y0 &&  7 == x0 || i == y1 &&  7 == x1 ||
                   i == y2 &&  7 == x2 || i == y3 &&  7 == x3 ||
                   i == y4 &&  7 == x4 || i == y5 &&  7 == x5 ||
                   i == y6 &&  7 == x6){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido05.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
                
                if(i == y0 &&  8 == x0 || i == y1 &&  8 == x1 ||
                   i == y2 &&  8 == x2 || i == y3 &&  8 == x3 ||
                   i == y4 &&  8 == x4 || i == y5 &&  8 == x5 ||
                   i == y6 &&  8 == x6){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido05.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
                }
                
              }
              out.print("</tr>");
            
      
      out.print("</table>");
      out.print("</div></td>");
      out.print("<td><div align=\"center\">");
      out.print("<table width=\"250\" class=\"ETablaInfo\" height=\"0\" border=\"0\"  align=\"center\" cellspacing=\"0\" background=\""+cRutaImg+"cuadricula.jpg\">");
      
            for (int i = 0; i < 31; i++){
              out.print("<tr>");
                 if(i==0 || i > 29){
              out.print("<td height=\"21\">&nbsp;&nbsp;</td>");
              out.print("<td height=\"21\">&nbsp;&nbsp;</td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
              out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido03.gif\" width=\"14\" height=\"14\" /></td>");
                /*out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");
                out.print("<td height=\"21\">&nbsp;</td>");*/
                }
              else{                  
              out.print("<td height=\"21\">&nbsp;&nbsp;</td>");
              out.print("<td height=\"21\">&nbsp;&nbsp;</td>");
              
                if(i == y7 &&  1 == x7 || i == y8 &&  1 == x8 ||
                   i == y9 &&  1 == x9 || i == y10 &&  1 == x10 ||
                   i == y11 &&  1 == x11 || i == y12 &&  1 == x12 ||
                   i == y13 &&  1 == x13){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido04.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
              
                if(i == y7 &&  2 == x7 || i == y8 &&  2 == x8 ||
                   i == y9 &&  2 == x9 || i == y10 &&  2 == x10 ||
                   i == y11 &&  2 == x11 || i == y12 &&  2 == x12 ||
                   i == y13 &&  2 == x13){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido04.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
              
                if(i == y7 &&  3 == x7 || i == y8 &&  3 == x8 ||
                   i == y9 &&  3 == x9 || i == y10 &&  3 == x10 ||
                   i == y11 &&  3 == x11 || i == y12 &&  3 == x12 ||
                   i == y13 &&  3 == x13){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido04.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
             

                if(i == y7 &&  4 == x7 || i == y8 &&  4 == x8 ||
                   i == y9 &&  4 == x9 || i == y10 &&  4 == x10 ||
                   i == y11 &&  4 == x11 || i == y12 &&  4 == x12 ||
                   i == y13 &&  4 == x13){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido04.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
                

                if(i == y7 &&  5 == x7 || i == y8 &&  5 == x8 ||
                   i == y9 &&  5 == x9 || i == y10 &&  5 == x10 ||
                   i == y11 && 5 == x11 || i == y12 &&  5 == x12 ||
                   i == y13 &&  5 == x13){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido04.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
                
                if(i == y7 &&  6 == x7 || i == y8 &&  6 == x8 ||
                   i == y9 &&  6 == x9 || i == y10 &&  6 == x10 ||
                   i == y11 &&  6 == x11 || i == y12 &&  6 == x12 ||
                   i == y13 &&  6 == x13){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido04.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
                
                if(i == y7 &&  7 == x7 || i == y8 &&  7 == x8 ||
                   i == y9 &&  7 == x9 || i == y10 &&  7 == x10 ||
                   i == y11 &&  7 == x11 || i == y12 &&  7 == x12 ||
                   i == y13 &&  7 == x13){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido04.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
                
                if(i == y7 &&  8 == x7 || i == y8 &&  8 == x8 ||
                   i == y9 &&  8 == x9 || i == y10 &&  8 == x10 ||
                   i == y11 &&  8 == x11 || i == y12 &&  8 == x12 ||
                   i == y13 &&  8 == x13){
                out.print("<td height=\"21\"><img src=\""+cRutaImg+"AuOido04.gif\" width=\"14\" height=\"14\" /></td>");
                }else{out.print("<td height=\"21\">&nbsp;&nbsp;</td>");}
                }
              }
      }
      out.print("</table>");
      out.print("</div></td>");
      out.print("</tr>");
      out.print("</table>");
      
      
      
      //fin grafica nueva
      
      
      
      
      
  %>
  
    </td></tr>
  
  </td></tr>
<%  }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>
</table>

</form>
 </font>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>



