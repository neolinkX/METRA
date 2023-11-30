<%/**
 * Title: Listado de Turnos de Validaci�n
 * Description: Expediente Virtual
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. Gonz�lez Paz
 * @version 1
 * Clase: ?
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.util.reglas.ReglasExpedienteVirtual" %>
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
  pg070106050CFG clsConfig = new pg070106050CFG();                // modificar Ok

  TEntorno       vEntorno  = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070106050.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070106050.jsp\" target=\"FRMCuerpo"); // modificar Ok
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
  TDEmpresa dEmpresa = new TDEmpresa();
  int iDespliega = 0;
%>
<head>
<meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
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

 function fIrPagina(iCveExp,iNumExm,iUniMed,iModulo,iProceso){
    var form=document.forms[0];
    form.hdICveExpediente.value=iCveExp;
    form.hdICvePersonal.value = iCveExp;

    form.hdINumExamen.value=iNumExm;
    form.hdICveUniMed.value= iUniMed;
   //alert("valor de uniMed " + form.hdICveUniMed.value);
    form.hdICveModulo.value= iModulo;
    form.hdICveProceso.value= iProceso;
    //alert("Val's: Exp-" + iCveExp + "** NumExam:" + iNumExm + "**UniMed: " + iUniMed + "**Mod:" + iModulo + "**Proc:" + iProceso);
    form.action="pg070106051.jsp";
    form.target="FRMDatos";
    form.submit();
  }
 

 function fIrPagina2(iCveExp,iNumExm,iUniMed,iModulo,iProceso){
    var form=document.forms[0];
    form.hdICveExpediente.value=iCveExp;
    form.hdICvePersonal.value = iCveExp;
    form.iCvePersonal.value = iCveExp;
    form.lAccion.value='';
    form.lAgregar.value='';

    form.hdINumExamen.value=iNumExm;
    form.hdICveUniMed.value= iUniMed;
   //alert("valor de uniMed " + form.hdICveUniMed.value);
    form.hdICveModulo.value= iModulo;
    form.hdICveProceso.value= iProceso;
    form.lMostrarDatos.value='true';
    //alert("Val's: Exp-" + iCveExp + "** NumExam:" + iNumExm + "**UniMed: " + iUniMed + "**Mod:" + iModulo + "**Proc:" + iProceso);
    form.action="pg070106041.jsp";
    form.target="FRMDatos";
    form.submit();
  }


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
  
  <!-- Variables para Actualizacion de No aptos -->
  <input type="hidden" name="lAccion" value="">
  <input type="hidden" name="lMostrarDatos" value="">
  <input type="hidden" name="lAgregar" value=''>
  <!-- Fin Variables para Actualizacion de No aptos -->

  <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal")%>">
  <input type="hidden" name="iCvePersonal" value="<%=request.getParameter("hdICvePersonal")%>">

  <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">


  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="Primero">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%

   TFechas oFecha = new TFechas();
   String cHoy = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(),"/");

   if(clsConfig.getAccesoValido()){
      java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd,MM,yyyy");
      String cDate=sdf.format(new java.util.Date());
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="5">Expediente Virtual</td></tr>

      <%
      out.println("<tr>");
      out.println("<td align='center'c colspan='4'>");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar Persona","javascript:fSelPer();","Buscar"));
      out.println("</td>");
      out.println("</tr>");

      //System.out.println(request.getParameter("hdICvePersonal"));
      
     if (request.getParameter("hdICvePersonal") != null){
       TDPERDatos dPERDatos = new TDPERDatos();
       TVPERDatos vPERDatos = new TVPERDatos();

       Vector vcPersona = new Vector();
       vcPersona = dPERDatos.FindByPersona(request.getParameter("hdICvePersonal"));
       if (vcPersona.size() > 0){
    	   
            vPERDatos = (TVPERDatos) vcPersona.get(0);
            iDespliega = vPERDatos.getICveExpediente();
            out.println("<tr><td class='EEtiqueta'>Expediente:</td>");
            out.println("<td class='ECampo'>"+vPERDatos.getICveExpediente()+"</td></tr>");
            out.println("<tr>");
            out.println("<td class='EEtiqueta'>Nombre:</td>");
            out.println("<td class='ECampo'>" + vPERDatos.getCNombre() + " " + vPERDatos.getCApPaterno() +  " " + vPERDatos.getCApMaterno());
            out.println("</td>");
            out.println("<tr>");
            out.println("<tr><td class='EEtiqueta'>RFC:</td>");
            out.println("<td class='ECampo'>"+vPERDatos.getCRFC()+"</td></tr>");
            out.println("<tr><td class='EEtiqueta'>FECNAC (Fecha de Nacimiento):</td>");
            out.println("<td class='ECampo'>"+ oFecha.getFechaDDMMYYYY(vPERDatos.getDtNacimiento(),"/")+"</td></tr>");
            out.println("<tr><td class='EEtiqueta'>Empresa Actual:</td>");
            
            /////Obteniendo la empresa temporal asociada///////////
            String EmpresaTemporal ="";
            String Empresa ="";
            if(vPERDatos.getCDscEmpresa()!=null){
            	if(vPERDatos.getCDscEmpresa().equals("TEMPORAL ")){
            		EmpresaTemporal = dEmpresa.cEmpresaTemporal(vPERDatos.getICveExpediente()+"");
            		if(EmpresaTemporal.length()>1){
            			EmpresaTemporal = " ("+EmpresaTemporal+" )";
            		}
            	}
            	Empresa = ""+vPERDatos.getCDscEmpresa();
            }else{
            	Empresa ="NO EXISTE EMPRESA ASOCIADA A ESTE EXPEDIENTE";
            }
            ///////////////////////////////////////////////////////
            
            out.println("<td class='ECampo'>"+Empresa+EmpresaTemporal+"</td></tr>");
            out.println("<tr>");
            out.println("<td align='center'c colspan='4'>");
            out.print(vEti.clsAnclaTexto("EEtiqueta","Ficha de Identificaci&oacute;n",
                    "javascript:fDetalle("+ vPERDatos.getICveExpediente() +  ");","Buscar")
                    + "&nbsp;&nbsp;&nbsp;&nbsp;");
            out.println("</td>");
            out.println("</tr>");

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
     
     
     
     /////Reglas Muestra Historial de Examenes //////////////
     ReglasExpedienteVirtual rvirtual = new ReglasExpedienteVirtual(); 
     TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
     boolean accesoPermitidoAExpedinete = false;
     //System.out.println("iDespliega = "+iDespliega);
     if(iDespliega > 0){
    	 accesoPermitidoAExpedinete = rvirtual.AccesoAExpVirtual(vUsuario.getICveusuario(),iDespliega);	 
     }else{
    	 accesoPermitidoAExpedinete = true;
     }
     //System.out.println("Acceso Expediente Virtual ="+accesoPermitidoAExpedinete);
     
     if(!accesoPermitidoAExpedinete){
    	 %>

         </tr></table>
       </td></tr>
       <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
       <tr><td class="ETablaT" colspan="6"><p>ACCESO DENEGADO</p></td></tr>
           <tr class="ETabla">
      <td>De conformidad con la NORMA Oficial Mexicana NOM-004-SSA3-2012, Del expediente cl&iacute;nico, 
      los datos contenidos en el expediente virtual del sistema Medprev, se encuentran considerados como 
      datos confidenciales, mismos que se encuentran protegidos por las leyes de datos personales en posesi&oacute;n 
      de la autoridad o de particulares.</td>
	</tr>
	</tr>
	
   <%
     }else{
     
      %>

      </tr></table>
    </td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
    <tr><td class="ETablaT" colspan="6"><p>Ex&aacute;menes practicados</p></td></tr>
      <tr class="ETablaT">
        <td>Examen</td>
        <td>Fecha</td>
        <td>Proceso</td>
        <td>Situaci&oacute;n</td>
         <td>Unidad M&eacute;dica</td>
         <td>Modulo</td>
      </tr>

<%
    out.println("<input type='hidden' name='hdUsuario' value='"+ vUsuario.getICveusuario()+ "'>");

%>

<%    if(bs!=null){
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
        /*//Se comento esta parte para que la fecha que aparezca siempre sea la de solicitud del examen
           if (bs.getFieldValue("dtConcluido","&nbsp;").toString().compareTo("null") != 0)
           out.print("" + dtFecha.getFechaDDMMYYYY(dtFecha.getSQLDatefromSQLString(bs.getFieldValue("dtConcluido","&nbsp;").toString()),"/"));
           else
           out.print("" + dtFecha.getFechaDDMMYYYY(dtFecha.getSQLDatefromSQLString(bs.getFieldValue("dtSolicitado","&nbsp;").toString()),"/"));
        */
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
         out.print(vEti.clsAnclaTexto("EEtiqueta","Detalle","javascript:fIrPagina("+request.getParameter("hdICvePersonal") + "," + bs.getFieldValue("iNumExamen","&nbsp;") + "," + bs.getFieldValue("iCveUniMed","&nbsp;")  + "," + bs.getFieldValue("iCveModulo","&nbsp;") + "," + bs.getFieldValue("iCveProceso","&nbsp;") + ");","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
         out.print("</td>");
        }
       else  {out.print("<td>");
       if (iBandera == 0)
         out.print(vEti.clsAnclaTexto("EEtiqueta","Detalle","javascript:fIrPagina("+request.getParameter("hdICvePersonal") + "," + bs.getFieldValue("iNumExamen","&nbsp;") + "," + bs.getFieldValue("iCveUniMed","&nbsp;")  + "," + bs.getFieldValue("iCveModulo","&nbsp;") + "," + bs.getFieldValue("iCveProceso","&nbsp;") + ");","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
       else
         out.print("NO CONCLUIDO");
         out.print("</td>");}
        }
         //out.print("<td class='ECampo'>Inconcluso</td>");

           %>
        <td><%=bs.getFieldValue("cDscUniMed","&nbsp;")%></td>
        <td><%=bs.getFieldValue("cDscModulo","&nbsp;")%></td>
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
<%      }
      }else{
%>      <tr class="EEtiquetaC" align="center"><td colspan="9">Datos no Disponibles.</td></tr>
<%    }
%> <!--   </table></td></tr>-->
</table>
<%
           //Registro de Examenes generados por la actualizacion de NO Aptos
        if (request.getParameter("hdICvePersonal") != null){
            TDPERCatalogoNoAp  dPERCatalogoNoAp = new TDPERCatalogoNoAp();
            Vector vcPercatNoAp = new Vector();
            TVPERCatalogoNoAp vPERCatalogoNoAp;
            String cWhere = " PER.ICVEPERSONAL = " + request.getParameter("hdICvePersonal")+"";
            try{
                 vcPercatNoAp = dPERCatalogoNoAp.AgregadosNoAp(cWhere, "");
            }catch(Exception e){
                 vcPercatNoAp = new Vector();
                 e.printStackTrace();
           }
          if(vcPercatNoAp.size() > 0)  {
%>
            <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                        <tr><td class="ETablaT" colspan="6">Registro por Actualizaci&oacute;n de no Aptos</td></tr>
                    <tr class="ETablaT">
                        <td>Expediente</td>
                        <td>Fecha</td>
                        <td>Unidad M&eacute;dica</td>
                        <td>Transporte</td>
                        <td>Categor&iacute;a</td>
                        <td>Detalle</td>
                    </tr>
<%
            for(int j=0;j<vcPercatNoAp.size();j++){
                  vPERCatalogoNoAp = (TVPERCatalogoNoAp) vcPercatNoAp.get(j);
%>
            <tr class="ETabla">
                <td><%=vPERCatalogoNoAp.getICvePersonal()%></td>
                <td><%=vPERCatalogoNoAp.getDtInicio()%></td>
                <td><%=vPERCatalogoNoAp.getCDscUniMed()%></td>
                <td><%=vPERCatalogoNoAp.getCDscMdoTrans()%></td>
                <td><%=vPERCatalogoNoAp.getCDscCategoria()%></td>
                <td><%     out.print(vEti.clsAnclaTexto("EEtiqueta","Detalle","javascript:fIrPagina2("+request.getParameter("hdICvePersonal") 
                		+ "," + bs.getFieldValue("iNumExamen","&nbsp;") + "," + bs.getFieldValue("iCveUniMed","&nbsp;")  + "," 
                        + bs.getFieldValue("iCveModulo","&nbsp;") + "," + bs.getFieldValue("iCveProceso","&nbsp;") + ");","Buscar") 
                        + "&nbsp;&nbsp;&nbsp;&nbsp;");
                %></td>
            </tr>
<%         }    %>
		</table>
<%

            }
          
  

     //   Informacion Adicional de Inhabilitacion
        TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
        TVMEDInhabilita vMEDInhabilita;
        Vector vcMEDInhabilita = new Vector();
        //String cWhere2 = " ICVEPERSONAL = " + request.getParameter("hdICvePersonal")+"";
        String cWhere2 = " " + request.getParameter("hdICvePersonal")+"";
        try{
                 vcMEDInhabilita = dMEDInhabilita.FindByAll2(cWhere2);
            }catch(Exception e){
                 vcMEDInhabilita = new Vector();
                 e.printStackTrace();
           }

        for(int j=0;j<vcMEDInhabilita.size();j++){
                  vMEDInhabilita = (TVMEDInhabilita) vcMEDInhabilita.get(j);
                  %>
                  	
                    <p>&nbsp;</p>
                    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                      <tr>
                        <td class="ETablaT" colspan="5"><p>Inhabilitaci&oacute;n</p></td>
                      </tr>
                      <tr><td class='EEtiqueta'>Resumen:</td>
						<td class='ECampo'>El expediente <%=vMEDInhabilita.getICvePersonal()%> cuanta con una Inhabilitaci&oacute;n para el  periodo, <%=vMEDInhabilita.getInicioInh()%> a <%=vMEDInhabilita.getFinInh()%>.</td></tr>
					  <tr><td class='EEtiqueta'>Medico que Inhabilito:</td>
						<td class='ECampo'><%=vMEDInhabilita.getCMedico()%></td></tr>
						<tr><td class='EEtiqueta'>Periodo de inhabilitacion:</td>
						<td class='ECampo'><%=vMEDInhabilita.getInicioInh()%> - <%=vMEDInhabilita.getFinInh()%></td></tr>
						<tr><td class='EEtiqueta'>Observaciones:</td>
						<td class='ECampo'>"ANTES, DE REVALORAR COMUNICARSE A LA DGPMPT" <BR><%=vMEDInhabilita.getCObservacion()%></td></tr>
						<tr><td class='EEtiqueta'>Motivo:</td>
						<td class='ECampo'><%=vMEDInhabilita.getCDscMotivo()%></td></tr>
                      
                     <!--  
                      <tr class="ETabla">
                        <td><p>El expediente <%=vMEDInhabilita.getICvePersonal()%> cuanta con una Inhabilitaci&oacute;n para el  periodo, <%=vMEDInhabilita.getInicioInh()%> a <%=vMEDInhabilita.getFinInh()%>.</p></td>
                      </tr> -->
                    </table>
                    <p>&nbsp;</p>
                    
        <%
           }%>
           </table></td></tr>
           <%
    }
     }
   }else{
%>  <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();
%></html>