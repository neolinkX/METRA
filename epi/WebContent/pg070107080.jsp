<%/**
 * Title: Listado de Turnos de Validación
 * Description: Expediente Virtual
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. González Paz
 * @version 1
 * Clase: ?
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<html>
<%
  pg070107080CFG clsConfig = new pg070107080CFG();                // modificar Ok

  TEntorno       vEntorno  = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070107080.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070107080.jsp\" target=\"FRMCuerpo"); // modificar Ok
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070102031.jsp";           // modificar Ok
  String cOperador    = "1";                         // modificar ?
  String cDscOrdenar  = "Sustancia|";                // modificar Ok
  String cCveOrdenar  = "cDscSustancia|";            // modificar Ok
  String cDscFiltrar  = "Sustancia|";                // modificar Ok
  String cCveFiltrar  = "cDscSustancia|";            // modificar Ok
  String cTipoFiltrar = "8|";                        // modificar Ok
  boolean lFiltros    = false;                       // modificar Ok
  boolean lIra        = false;                       // modificar Ok
  String cEstatusIR   = "Imprimir";                  // modificar ?

  // LLamado al Output Header
  TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
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
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
%>
<head>
<meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--
<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev2\archivos\funciones\pg070107080.js"></SCRIPT>
-->

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  function fSelectedPer(iCvePersonal,iCveExpediente,iNumExamen){
    var form = document.forms[0];
    form.hdICvePersonal.value = iCvePersonal;

   if (form.iCveUniMed){
      form.hdBoton.value = "Nuevo";
   }
   else{
       form.hdBoton.value = "Cancelar";
   }

    form.target = "_self";
    form.submit();

  }

 function fIrPagina(iCveExp,iNumExm,iUniMed,iModulo,iProceso,iActivar){
    var form=document.forms[0];
    form.hdICveExpediente.value=iCveExp;
    form.hdICvePersonal.value = iCveExp;

    form.hdINumExamen.value=iNumExm;
    form.hdICveUniMed.value= iUniMed;
   //alert("valor de uniMed " + form.hdICveUniMed.value);
    form.hdICveModulo.value= iModulo;
    form.hdICveProceso.value= iProceso;
    form.hdActivar.value= iActivar;
   //alert("Val's: Exp-" + iCveExp + "** NumExam:" + iNumExm + "**UniMed: " + iUniMed + "**Mod:" + iModulo + "**Proc:" + iProceso + "**Activar" + iActivar);
    form.action="pg070107080.jsp";
    form.target="FRMDatos";
    form.submit();
  }

function fIrPagina2(iCveExp,iNumExm,iUniMed,iModulo,iProceso,iActivar){
    var form=document.forms[0];
    form.hdICveExpediente.value=iCveExp;
    form.iCveExpediente.value = iCveExp;

    form.iNumExamen.value=iNumExm;
    form.iCveUniMed.value= iUniMed;
   //alert("valor de uniMed " + form.hdICveUniMed.value);
    form.iCveModulo.value= iModulo;
    form.iCveProceso.value= iProceso;
    form.hdActivar.value= iActivar;
    form.iCveServicio.value = "45";
    form.iCveRama.value = "1";
    form.ramaInicial.value = "0";
   //alert("Val's: Exp-" + iCveExp + "** NumExam:" + iNumExm + "**UniMed: " + iUniMed + "**Mod:" + iModulo + "**Proc:" + iProceso + "**Activar" + iActivar);
    form.action="pg070108021.jsp";
    form.target="FRMDatos";
    form.submit();
  }



/*
    function fIrCatalogo(iUniMed,iModulo,iCveServicio,iCveRama,ramaInicial,iCveExp,iNumExm){
    var form=document.forms[0];
//    alert("Val's: Exp-" + iCveExpediente + "** NumExam:" + iNumExamen + "**UniMed: " + iCveUniMed + "**Mod:" + iCveModulo + "**Serv" + iCveServicio + "**Activar" + ramaInicial);
	form.iCveUniMed.value = iUniMed;
	form.iCveProceso.value = "1";
	form.iCveModulo.value = iModulo;
	form.iCveServicio.value = iCveServicio;
	form.iCveRama.value = iCveRama;
	form.ramaInicial.value = ramaInicial;
	form.iCveExpediente.value = iCveExp;
	form.iNumExamen.value = iNumExm;
        form.action="pg070108021.jsp";
        form.target="FRMDatos";
        form.submit();
  }
*/

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }


  var cFoto1ClassID = '<%=vParametros.getPropEspecifica("Foto1ClassID")%>';
  var cFoto1CodeBase = '<%=vParametros.getPropEspecifica("Foto1CodeBase")%>';
  var cFoto1GetRuta = '<%=vParametros.getPropEspecifica("Foto1GetRuta")%>';

  var cFirma1ClassID = '<%=vParametros.getPropEspecifica("Firma1ClassID")%>';
  var cFirma1CodeBase = '<%=vParametros.getPropEspecifica("Firma1CodeBase")%>';
  var cFirma1GetRuta = '<%=vParametros.getPropEspecifica("Firma1GetRuta")%>';

  var cHuella1ClassID = '<%=vParametros.getPropEspecifica("Huella1ClassID")%>';
  var cHuella1CodeBase = '<%=vParametros.getPropEspecifica("Huella1CodeBase")%>';
  var cHuella1GetRuta = '<%=vParametros.getPropEspecifica("Huella1GetRuta")%>';

</SCRIPT>
</head>
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>" onSubmit="return fOnSubmit();">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?bs.pageNo():0%>">
  <input type="hidden" name="hdCveUniMed" value="">
  <input type="hidden" name="hdCveModulo" value="">
  <input type="hidden" name="hdFecha" value="">
  <input type="hidden" name="hdCveCita" value="">


  <input type="hidden" name="hdLCondicion" value="">
  <input type="hidden" name="hdLOrdenar" value="">
  <!--input type="hidden" name="FILNumReng" value="600"-->
  <input type="hidden" name="FILNumReng" value="600">
  <input type="hidden" name="hdRowNum" value="1">
  <input type="hidden" name="hdCampoClave" value="">
  <input type="hidden" name="hdICveExpediente" value="">
  <!--<input type="hidden" name="hdINumExamen" value="">-->
    <input type="hidden" name="hdICveProceso" value="1">
  <input type="hidden" name="hdDtConcluido" value="01/02/2010">
  <input type="hidden" name="hdDtConcluido2" value="01/12/2010">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="iCveProceso" value="1">
  <input type="hidden" name="iCveServicio" value="">
  <input type="hidden" name="iCveRama" value="">
  <input type="hidden" name="ramaInicial" value="0">
  <input type="hidden" name="iCveExpediente" value="">
  <input type="hidden" name="iNumExamen" value="">



  <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal")%>">

  <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <!--<input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">-->
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">
  <input type="hidden" name="hdActivar" value="<%=request.getParameter("hdActivar")!=null?request.getParameter("hdActivar"):""%>">


  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="Primero">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%
//System.out.println("2");

//System.out.println(" =====hdINumExamen="+request.getParameter("hdINumExamen"));

   TFechas oFecha = new TFechas();
   String cHoy = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(),"/");

   TDEXPServicio dEXPServicio = new TDEXPServicio();
   Vector vcEXPServicio = new Vector();
   TVEXPServicio vEXPServicio = new TVEXPServicio();


   if(clsConfig.getAccesoValido()){
      java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd,MM,yyyy");
      String cDate=sdf.format(new java.util.Date());
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="5">Persona a la que se le desea cargar el servicio Toxicológico</td></tr>

      <%
      out.println("<tr>");
      out.println("<td align='center'c colspan='4'>");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar Persona","javascript:fSelPer();","Buscar"));
      out.println("</td>");
      out.println("</tr>");
//System.out.println("3");
      int iDespliega;
     if (request.getParameter("hdICvePersonal") != null){
//System.out.println("4");
       TDPERDatos dPERDatos = new TDPERDatos();
       TVPERDatos vPERDatos = new TVPERDatos();


       Vector vcPersona = new Vector();
       vcPersona = dPERDatos.FindByPersona(request.getParameter("hdICvePersonal"));
       if (vcPersona.size() > 0){
           //System.out.println("5");
            vPERDatos = (TVPERDatos) vcPersona.get(0);
            iDespliega = vPERDatos.getICveExpediente();
            out.println("<tr><td class='EEtiqueta'>Expediente:</td>");
            out.println("<td class='ECampo'>"+vPERDatos.getICveExpediente()+"</td></tr>");
            out.println("<tr>");
            out.println("<td class='EEtiqueta'>Nombre:</td>");
            out.println("<td class='ECampo'>" + vPERDatos.getCNombre() + " " + vPERDatos.getCApPaterno() +  "" + vPERDatos.getCApMaterno());
            out.println("</td>");
            out.println("<tr>");
            out.println("<tr><td class='EEtiqueta'>RFC:</td>");
            out.println("<td class='ECampo'>"+vPERDatos.getCRFC()+"</td></tr>");
            out.println("<tr><td class='EEtiqueta'>FECNAC (Fecha de Nacimiento):</td>");
            out.println("<td class='ECampo'>"+ oFecha.getFechaDDMMYYYY(vPERDatos.getDtNacimiento(),"/")+"</td></tr>");
            out.println("<tr><td class='EEtiqueta'>Empresa Actual:</td>");
            out.println("<td class='ECampo'>"+vPERDatos.getCDscEmpresa()+"</td></tr>");

            TDPERFoto dPERFoto = new TDPERFoto();
            PageBeanScroller bsFoto = new PageBeanScroller(dPERFoto.FindByAll("where iCvePersonal = " + vPERDatos.getICveExpediente()),100);
            if (bsFoto != null){
              if(bsFoto.rowSize()>0){
                 bsFoto.firstPage();
                 bsFoto.start();
                 while(bsFoto.nextRow()){
                   out.print("<tr>");
                   out.print(vEti.Texto("EEtiqueta","Foto:"));
                   out.print("<td colspan=\"3\" class=\"EEtiquetaC\" align=\"center\" >");
                   out.print(vEti.clsAnclaTexto("EAncla","Mostrar","JavaScript:fShowPict('Foto',"+ vPERDatos.getICveExpediente() +","+bsFoto.getFieldValue("iCveFoto","")+");", "Mostrar"));
                   out.print("</td>");
                   out.print("</tr>");
                 }
              }
            }

            TDPERFirma dPERFirma = new TDPERFirma();
            PageBeanScroller bsFirma = new PageBeanScroller(dPERFirma.FindByAll("where iCvePersonal = " + vPERDatos.getICveExpediente()),100);
            if (bsFirma != null){
               if(bsFirma.rowSize()>0){
                 bsFirma.firstPage();
                 bsFirma.start();
                 while(bsFirma.nextRow()){
                   out.print("<tr>");
                   out.print(vEti.Texto("EEtiqueta","Firma:"));
                   out.print("<td colspan=\"4\" class=\"EEtiquetaC\">");
                   out.print(vEti.clsAnclaTexto("EAncla","Mostrar","JavaScript:fShowPict('Firma',"+ vPERDatos.getICveExpediente()+");", "Mostrar"));
                   out.print("</td>");
                   out.print("</tr>");
                 }
              }

           }

        }

     }
      //System.out.println("6");
      %>

      </tr></table>
    </td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr class="ETablaT">
        <td>Examen</td>
        <td>Fecha</td>
        <td>Proceso</td>
        <td>Situación</td>
         <td>Unidad Médica</td>
      </tr>

<%
    TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
    out.println("<input type='hidden' name='hdUsuario' value='"+ vUsuario.getICveusuario()+ "'>");

%>

<%    if(bs!=null){
    //System.out.println("7");
        bs.start();
        TFechas dtFecha = new TFechas();
        sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
        while(bs.nextRow()){
         /* String cTmp=bs.getFieldValue("iCveCita","&nbsp;").toString();
          if(clsConfig.getLPagina(cCatalogo)){
            java.sql.Date dtTmp=((TVEPICisCita)bs.getCurrentBean()).getDtFecha();
            if(dtTmp==null) dtTmp=new java.sql.Date(new Date().getTime());
            String cLink=bs.getFieldValue("iCveUniMed","")+"','"+bs.getFieldValue("iCveModulo","")+"','"+sdf.format(dtTmp)+"','"+bs.getFieldValue("iCveCita","");
            cTmp=vEti.clsAnclaTexto("EAncla",cTmp,"JavaScript:fIrCatalogo('"+cLink+"','pg070102031');","").toString();
          }*/
%>      <tr class="ETabla">
        <td><%=bs.getFieldValue("iNumExamen","&nbsp;")%></td>
        <td><%
           int iBandera = 0;
           if (bs.getFieldValue("dtConcluido","&nbsp;").toString().compareTo("null") != 0)
           out.print("" + dtFecha.getFechaDDMMYYYY(dtFecha.getSQLDatefromSQLString(bs.getFieldValue("dtConcluido","&nbsp;").toString()),"/"));
           else
           out.print("" + dtFecha.getFechaDDMMYYYY(dtFecha.getSQLDatefromSQLString(bs.getFieldValue("dtSolicitado","&nbsp;").toString()),"/"));
           iBandera = 1;
        %></td>
        <td><%=bs.getFieldValue("cDscProceso","&nbsp;")%></td>
 <%
      if(bs.getFieldValue("iCveProceso","&nbsp;").toString().compareTo("0") == 4){
                 out.print("<td>");
                 out.print("NO APLICA");
                 out.print("</td>");
          }
      else{
        if (bs.getFieldValue("lDictaminado","&nbsp;").toString().compareTo("0") == 1){
         out.print("<td>");
         out.print("DICTAMINADO");
         out.print("</td>");
        }
       else  {
                out.print("<td>");
                if (iBandera == 0)
                   out.print("CONCLUIDO");
                else
                   out.print("NO CONCLUIDO");
                   out.print("</td>");
            }
        }
         //out.print("<td class='ECampo'>Inconcluso</td>");

           %>
        <td><%=bs.getFieldValue("cDscUniMed","&nbsp;")%></td>
     <%
   /*
       int iCvePersonal;
       iCvePersonal = Integer.parseInt(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
       if (iCvePersonal > 0){*/
        %>
        <%
         /*out.println("<td align='center'>");
        out.print(vEti.clsAnclaTexto("EEtiquetaC","Puesto",
        "javascript:fPuesto("+ bs.getFieldValue("iCvePersonal","&nbsp;")+","+ bs.getFieldValue("iCveUniMed","&nbsp;") + "," + bs.getFieldValue("iCveModulo","&nbsp;") + ");","Buscar")
       + "&nbsp;&nbsp;&nbsp;&nbsp;");
          out.println("</td>");
         out.println("<td align='center'>");
        out.print(vEti.clsAnclaTexto("EEtiquetaC","Detalle",
        "javascript:fDetalle("+ bs.getFieldValue("iCvePersonal","&nbsp;") +  ");","Buscar")
       + "&nbsp;&nbsp;&nbsp;&nbsp;");
          out.println("</td>");*/
        %>


      <%/*}else{
          out.println("<td>");
          out.print(vEti.clsAnclaTexto("EEtiquetaC","Alta Personal",
        "javascript:fGenExpediente("+ bs.getFieldValue("iCveUniMed","&nbsp;")+","+ bs.getFieldValue("iCveModulo","&nbsp;") + "," + bs.getFieldValue("dtFecha","&nbsp;") +","+ bs.getFieldValue("iCveCita","&nbsp;") +");","Buscar")
       + "&nbsp;&nbsp;&nbsp;&nbsp;");
          out.println("</td>");
          out.println("<td>&nbsp;</td>");
          out.println("<td>&nbsp;</td>");
        }*/%>
      </tr>
<%      
//System.out.println("8");
       String icveexpediente = request.getParameter("hdICvePersonal");
       String inumexamen = bs.getFieldValue("iNumExamen","&nbsp;").toString();
       String unimed = bs.getFieldValue("iCveUniMed","&nbsp;").toString();
       String modulo = bs.getFieldValue("iCveModulo","&nbsp;").toString();
       //String
       String Resultado = "";
        int registrado = 0;
        int concluido = 0;

       //System.out.println(icveexpediente +" and "+ inumexamen);

if(request.getParameter("hdICvePersonal")!=null){
//System.out.println("9");
      try{
               //System.out.println("hdINumExamen ="+request.getParameter("hdINumExamen")+"--");
               //System.out.println("iNumExamen ="+request.getParameter("iNumExamen")+"--");
               //System.out.println("inumexamen ="+inumexamen+"--");

               if(request.getParameter("hdINumExamen").toString().compareTo(inumexamen) == 0 ){
                 //        System.out.println("Son iguales");
               }

                       vcEXPServicio = dEXPServicio.getLConcluido(icveexpediente, inumexamen, " 45 ");
                        for (int i=0;i<vcEXPServicio.size();i++){
                              vEXPServicio = (TVEXPServicio) vcEXPServicio.get(i);
                           }
                        concluido =  vEXPServicio.getLConcluido();
                        registrado = vcEXPServicio.size();
        }
        catch (Exception e) {
                //        System.out.println("Se produjo un error en la obtension de información de los Servicios");
        }
//System.out.println("dff");

     if(!request.getParameter("hdActivar").equals("") && registrado < 1){
            //System.out.println("Generando Registros");
            try{
                if(request.getParameter("hdINumExamen").toString().compareTo(inumexamen)==0)
                    Resultado = dEXPServicio.insertServTox(icveexpediente, inumexamen);
            }
            catch (Exception e) {
                  //        System.out.println("Se produjo un error en la obtension de información de los Servicios");
             }
            if(Resultado.equals(icveexpediente)){
          //        System.out.println("Se activo correctamente el servicio");
                %>
        <!--        <script language="JavaScript">fIrPagina(76543,4,44,1,1,1);</script>  -->
               <%
            }
       }
       //System.out.println("error 2");

        try{
                       vcEXPServicio = dEXPServicio.getLConcluido(icveexpediente, inumexamen, " 45 ");
                        for (int i=0;i<vcEXPServicio.size();i++){
                              vEXPServicio = (TVEXPServicio) vcEXPServicio.get(i);
                           }
                        concluido =  vEXPServicio.getLConcluido();
                        registrado = vcEXPServicio.size();
        }
        catch (Exception e) {
                //        System.out.println("Se produjo un error en la obtension de información de los Servicios");
        }

        //System.out.println("registrado = " +registrado);
        //System.out.println("concluido = " +concluido);
        if(registrado > 0 && concluido == 1){
          //        System.out.println("condicion 0");
                   out.print("<tr class=\"ETablaT\"><td align='center'c colspan='5'>");
                   out.print("ESTE SERVICIO YA FUE CAPTURADO");
                   out.print("</td></tr>");
        }else{
            if(registrado > 0 && concluido == 0){
               if(!request.getParameter("hdActivar").equals("")){
                 //        System.out.println("condicion 1");
                   out.print("<tr><td align='center'c colspan='5'>");
                 //out.print("<a class=\"EAncla\" href=\"javascript:fIrCatalogo('"+unimed+"','"+modulo
                 //           +"','45','0','1','"+icveexpediente+"','"+
                 //           inumexamen+"')\">Capturar Servicio</a>");
                   if(request.getParameter("hdINumExamen").toString().compareTo(inumexamen)==0)
                   out.print(vEti.clsAnclaTexto("EEtiqueta","Capturar  Servicio","javascript:fIrPagina2("+request.getParameter("hdICvePersonal") + "," + bs.getFieldValue("iNumExamen","&nbsp;") + "," + bs.getFieldValue("iCveUniMed","&nbsp;")  + "," + bs.getFieldValue("iCveModulo","&nbsp;") + "," + bs.getFieldValue("iCveProceso","&nbsp;") + ",1);","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                   out.print("</td></tr>");
                  }
               else{
                   //System.out.println("condicion 2");
                        out.print("<tr class=\"ETablaT\"><td align='center'c colspan='5'>");
                        out.print("Este expediente cuenta con la caputa del servicio toxicologico pediente");
                        out.print("</td></tr>");
                        out.print("<tr class=\"ETablaT\"><td>");
                        out.print(vEti.clsAnclaTexto("EEtiqueta","Capturar  Servicio","javascript:fIrPagina2("+request.getParameter("hdICvePersonal") + "," + bs.getFieldValue("iNumExamen","&nbsp;") + "," + bs.getFieldValue("iCveUniMed","&nbsp;")  + "," + bs.getFieldValue("iCveModulo","&nbsp;") + "," + bs.getFieldValue("iCveProceso","&nbsp;") + ",1);","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                        out.print("</td></tr>");
                }
            }else{
                  //System.out.println("condicion 3");
                  out.print("<tr><td align='center'c colspan='5'>");
                  //out.print(vEti.clsAnclaTexto("EEtiqueta","Activar el vale para el Servicio Toxicológico","javascript:fIrPagina("+request.getParameter("hdICvePersonal") + "," + bs.getFieldValue("iNumExamen","&nbsp;") + "," + bs.getFieldValue("iCveUniMed","&nbsp;")  + "," + bs.getFieldValue("iCveModulo","&nbsp;") + "," + bs.getFieldValue("iCveProceso","&nbsp;") + ",1);","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                  String mensaje = "Activar el vale del Servicio Toxicológico para el examen "+bs.getFieldValue("iNumExamen","&nbsp;").toString();
                  out.print(vEti.clsAnclaTexto("EEtiqueta",mensaje,"javascript:fIrPagina("+request.getParameter("hdICvePersonal") + "," + bs.getFieldValue("iNumExamen","&nbsp;") + "," + bs.getFieldValue("iCveUniMed","&nbsp;")  + "," + bs.getFieldValue("iCveModulo","&nbsp;") + "," + bs.getFieldValue("iCveProceso","&nbsp;") + ",1);","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                  out.print("</td></tr>");
            }
        }
      }
     }
        //System.out.println("10");
    }else{
%>      <tr class="EEtiquetaC" align="center"><td colspan="9">Datos no Disponibles.</td></tr>
<%    }
%>    </table></td></tr>
<%
   }else{
%>  <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();
%></html>