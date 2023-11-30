 <%/* Title: Listado de Turnos de Validación
 * Description: Consulta de citas
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
  pg070103040CFG clsConfig = new pg070103040CFG();                // modificar Ok

  TEntorno       vEntorno  = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070103040.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103040.jsp\" target=\"FRMCuerpo"); // modificar Ok
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
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
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
  String iCveProceso = vParametros.getPropEspecifica("EPIProceso");
%>
<head>
</head>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"PermCombos.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  function fCURRP(msg){
            //alert("Para continuar con su examen es necesario corregir el CURP de este expediente,  \n esto podrá hacerlo dando clic en detalle.");
            alert(msg);
  }


  function fabrirSiaf(tramite) {
      var frm = document.forms[0];
        frm.ICVECITA.value = tramite;
        var direccion = "http://aplicaciones4.sct.gob.mx/siiaf/pgTranMostrarArchivos.jsp?cPagina=CD/frmmi.js&cPagNva=pgTranMostrarArchivos2.js&iNoTramite="+tramite;
        open(direccion,"SIAF", "toolbar=no,directories=no,menubar=no,status=no");
    }

function fGetTramiteInt()
{
    alert(frm.ICVECITA.value);
    return frm.ICVECITA.value;
}



</SCRIPT>

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

  <input type="hidden" name="hdICvePersonal">
  <input type="hidden" name="hdICveUniMed">
  <input type="hidden" name="hdICveModulo">

  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="Primero">
  <input type="hidden" name="ICVECITA" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%

   TFechas oFecha = new TFechas();
   String cHoy = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(),"/");

   if(clsConfig.getAccesoValido()){
      java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd,MM,yyyy");
      java.text.SimpleDateFormat sdf2=new java.text.SimpleDateFormat("dd,MM,yyyy");
      String cDate=sdf.format(new java.util.Date());
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="5">Consulta de Citas con Creación de Expediente</td></tr>
      <tr>
        <td class="EEtiqueta">Unidad M&eacute;dica:</td>
        <td class="ETabla">
          <%= vEti.SelectOneRowSinTD("iCveUniMed","{forma = document.forms[0];fActualizaCombo('3',forma,this,forma.iCveModulo,this.value,0," + iCveProceso + " );}",  vUsuario.getVUniMed(),"iClave","cDescripcion",request,"0",true)%>

          <% int iCveUniMed = (request.getParameter("iCveUniMed") != null && request.getParameter("iCveUniMed").toString().length() > 0) ? Integer.parseInt(request.getParameter("iCveUniMed").toString()) : 0;
          %>
        </td>
        <td class="EEtiqueta">M&oacute;dulo:</td>
        <td class="ETabla"><%=vEti.SelectOneRowSinTD("iCveModulo","fActualizaHora(this.form)",  vUsuario.getModuloFUP(iCveUniMed,Integer.parseInt(iCveProceso)),"iClave","cDescripcion",request,"0",true)%>
        </td>
        <td class="EEtiquetaF" valign="middle" rowspan="2">
          <%
          out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar","javascript:fSubmite();","Buscar"));
          %>
          <% //=new TCreaBoton().clsCreaBoton(vEntorno,3,"Buscar","bBuscar",vEntorno.getTipoImg(),"Buscar","javascript:fSubmite();",vParametros)%>
        </td>
      </tr>
      <tr>
        <td class="EEtiqueta">Fecha:</td>
        <td class="Etabla">
          <input type="text" size="12" maxlength="10" name="dtFecha" value="<%=request.getParameter("dtFecha")!=null?request.getParameter("dtFecha"):cHoy%>" onBlur="fActualizaHora(this.form)">
          <a class="EAncla" name="calendario" href="JavaScript:fLoadCal(<%=cDate%>,document.forms[0].dtFecha);document.forms[0].dtFecha.focus();">(dd/mm/aaaa)</a>
        </td>
        <td class="EEtiqueta">Hora:</td>
        <td class="ETabla"><select name="cHora">
<%    Vector vcCombo=clsConfig.getHorasDeCitas();
      if(vcCombo.size()>0){
        String cSelected="";
        String cNoSelected=" selected";
        for(Enumeration e=vcCombo.elements();e.hasMoreElements();){
          TVDinamico dCombo=(TVDinamico)e.nextElement();
          cSelected=dCombo.get("cIndice").equals(request.getParameter("cHora"))?" selected":"";
          if(cSelected.length()!=0) cNoSelected="";
%>          <option value="<%=dCombo.get("cIndice")%>"<%=cSelected%>><%=dCombo.get("cDescripcion")%></option>
<%      }
%>          <option value=""<%=cNoSelected%>>TODOS</option>
<%    }else{
%>          <option value="">Datos no disponibles...</option>
<%    }
%>        </select></td>
      </tr></table>
    </td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr class="ETablaT">
        <td>Cita</td>
        <td>RFC</td>
        <td>Nombre</td>
        <td>Hora</td>
        <td>Motivo</td>
        <td>Situaci&oacute;n</td>
        <td colspan='3'>Cve. Personal</td>
        <%
            if(request.getParameter("iCveUniMed") != null)
            if(request.getParameter("iCveUniMed").equals("1"))
                if(request.getParameter("iCveModulo") != null)
                if(request.getParameter("iCveModulo").equals("14")){
                    //System.out.println("Se cumplio la condicion1");
                %>
                        <td>Carga Internacional</td>
                <%
             }
%>
      </tr>

<%

    out.println("<input type='hidden' name='hdUsuario' value='"+ vUsuario.getICveusuario()+ "'>");

%>

<%

    TDPERDatos dPERDatos = new TDPERDatos();
    int Suma = 0;
    int ValidaCurp = 0;
    /*abernal inicio*/
    String cCURP="";
    int iCURP=0;
    String alerta = "''";
    /*abernal fin*/

      if(bs!=null){
        bs.start();
        sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
        
        while(bs.nextRow()){
          String cTmp=bs.getFieldValue("iCveCita","&nbsp;").toString();
          String cAlta="";
          
          if(clsConfig.getLPagina(cCatalogo)){
            java.sql.Date dtTmp=((TVEPICisCita)bs.getCurrentBean()).getDtFecha();
            if(dtTmp==null) dtTmp=new java.sql.Date(new Date().getTime());
            /*abernal inicio*/
            iCURP=clsConfig.checkCURP(
          		  bs.getFieldValue("iCveUniMed","&nbsp;").toString(),
          		  bs.getFieldValue("iCveModulo","&nbsp;").toString(),
          		  sdf2.format(dtTmp).toString(),
          		  bs.getFieldValue("iCveCita","&nbsp;").toString()
          		  );
            /*abernal fin*/
            String cLink=bs.getFieldValue("iCveUniMed","")+"','"+bs.getFieldValue("iCveModulo","")+"','"+sdf.format(dtTmp)+"','"+bs.getFieldValue("iCveCita","");
            cAlta = vEti.clsAnclaTexto("EAncla","Generar Exp.","JavaScript:fIrCatalogo('"+cLink+"','pg070103013');","").toString();
            cTmp  = vEti.clsAnclaTexto("EAncla",cTmp,"JavaScript:fIrCatalogo('"+cLink+"','pg070102031');","").toString();
            /*abernal inicio*/
            //System.out.println("cAlta: "+cAlta);
           // System.out.println("cTmp: "+cTmp);
           //cAlta= cAlta.replace("fIrCatalogo", "fCURPP('+alerta+');" );
           StringBuilder sb=new StringBuilder();
           sb.append(cAlta.toString());
           
           if(iCURP==1){
        	   alerta = "'La CURP es invalida,\nAntes de continuar debera corregirla dando clic en el numero de cita.'";
        	   cAlta=sb.replace(cAlta.indexOf("Java"), cAlta.indexOf(";")+1, "javascript:fCURRP("+alerta+");").toString();
        	   
           }
           else if(iCURP==2){
        	   alerta = "'La CURP de este usuario indica que ya existe expediente,\nSi existe un error debera corregirlo para continuar \nDando clic en el numero de cita.'";
        	   cAlta=sb.replace(cAlta.indexOf("Java"), cAlta.indexOf(";")+1, "javascript:fCURRP("+alerta+");").toString();
           }
           
           //System.out.println("new: "+sb.toString());
           //cAlta=sb.toString();
           //fCURRP("+alerta+");
           /*
           if(ValidaCurp == 0)
                    alerta = "'Para continuar con su examen es obligatorio capturar el CURP,\n esto podrá hacerlo dando clic en detalle.'";
                else
                    alerta= "'Para continuar con su examen es necesario corregir el CURP,\n esto podrá hacerlo dando clic en detalle.'";
           */
           /*abernal fin*/
           
          }
%>      <tr class="ETabla">
        <td class="ETablaR"><%=cTmp%></td>
        <td><%=bs.getFieldValue("cRFC"         ,"&nbsp;")%></td>
        <td><%=bs.getFieldValue("cApPaterno"   ,"&nbsp;")+" "+bs.getFieldValue("cApMaterno","&nbsp;")+" "+bs.getFieldValue("cNombre","&nbsp;")%></td>
        <td><%=bs.getFieldValue("cHora"        ,"&nbsp;")%></td>
        <td><%=bs.getFieldValue("cDscMotivo"   ,"&nbsp;")%></td>
        <td><%=bs.getFieldValue("cDscSituacion","&nbsp;")%></td>
       <%

       int iCvePersonal;
       iCvePersonal = Integer.parseInt(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
       if (iCvePersonal > 0){
          try{
                Suma = dPERDatos.FindByValida(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
                ValidaCurp = dPERDatos.ValidaCURP(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
             }catch (Exception ex) {
                Suma = 0;
                ValidaCurp = 0;
             }

          //System.out.println("Suma = "+Suma);
          //System.out.println(ValidaCurp);

        %>
        <td class="EEtiquetaC"><%=bs.getFieldValue("iCvePersonal","&nbsp;")%></td>
        <%
        out.println("<td class=\"ETablaC\">");

        if(ValidaCurp == 18){
                out.print(vEti.clsAnclaTexto("EAncla","Puesto",
                "javascript:fPuesto("+ bs.getFieldValue("iCvePersonal","&nbsp;")+","+ bs.getFieldValue("iCveUniMed","&nbsp;") + "," + bs.getFieldValue("iCveModulo","&nbsp;") + ");","Buscar")
               + "&nbsp;&nbsp;&nbsp;&nbsp;");
                  out.println("</td>");
        }else{
            
                if(ValidaCurp == 0)
                    alerta = "'Para continuar con su examen es obligatorio capturar el CURP,\n esto podrá hacerlo dando clic en detalle.'";
                else
                    alerta= "'Para continuar con su examen es necesario corregir el CURP,\n esto podrá hacerlo dando clic en detalle.'";
                out.print(vEti.clsAnclaTexto("EAncla","Puesto",
                "javascript:fCURRP("+alerta+");","Buscar")
               + "&nbsp;&nbsp;&nbsp;&nbsp;");
                  out.println("</td>");
           }



        if(Suma > 0){
                //out.println("<td background=\"C:/sct/MedprevInt/medprev2/archivos/img/medprev/alerta.gif\" class=\"ETablaC\">");
                out.println("<td background=\""+vParametros.getPropEspecifica("RutaImgServer")+"alerta.gif\" class=\"ETablaC\">");
                out.print(vEti.clsAnclaTexto("EAncla","Detalle",
                "javascript:fDetalle("+ bs.getFieldValue("iCvePersonal","&nbsp;") +  ");","Buscar")
               + "&nbsp;&nbsp;&nbsp;&nbsp;");
                  out.println("</td>");
          }else{
              //System.out.println(bs.getFieldValue("iCvePersonal","&nbsp;"));
              //System.out.println("");
                out.println("<td class=\"ETablaC\">");
                out.print(vEti.clsAnclaTexto("EAncla","Detalle",
                "javascript:fDetalle("+ bs.getFieldValue("iCvePersonal","&nbsp;") +  ");","Buscar")
                + "&nbsp;&nbsp;&nbsp;&nbsp;");
                out.println("</td>");
          }
            if(request.getParameter("iCveUniMed") != null)
            if(request.getParameter("iCveUniMed").equals("1"))
                if(request.getParameter("iCveModulo") != null)
                if(request.getParameter("iCveModulo").equals("14")){
                 /*out.println("<td class=\"ETablaC\">");
                out.print(vEti.clsAnclaTexto("EAncla","Internacional",
                "javascript:fabrirSiaf("+ bs.getFieldValue("iCvePersonal","&nbsp;") +  ");","Internacional")
                + "&nbsp;&nbsp;&nbsp;&nbsp;");
                out.println("</td>");*/
                 //System.out.println("Se cumplio la condicion");
                 out.println("<td class=\"ETablaC\">");
                out.print(vEti.clsAnclaTexto("EAncla","Internacional",
                "javascript:fabrirSiaf("+bs.getFieldValue("iCveCita","&nbsp;").toString()+");","Internacional")
                + "&nbsp;&nbsp;&nbsp;&nbsp;");
                out.println("</td>");
            }

        %>


      <%}else{
          out.println("<td class=\"ETablaC\">");
          out.print(cAlta);
/*        "javascript:fGenExpediente("+ bs.getFieldValue("iCveUniMed","&nbsp;")+","+ bs.getFieldValue("iCveModulo","&nbsp;") + "," + bs.getFieldValue("dtFecha","&nbsp;") +","+ bs.getFieldValue("iCveCita","&nbsp;") +");","Buscar")
        */
          out.println("</td>");
          out.println("<td>&nbsp;</td>");
          out.println("<td>&nbsp;</td>");
        }
%>
      </tr>
<%      }
        out.print("<tr>");
        out.print("<td align='center' colspan='9' class='EResalta'>Total de Citas Registradas: " + bs.rowSize());
        out.print("</td>");
        out.print("</tr>");

      }else{
%>      <tr class="EEtiquetaC" align="center"><td colspan="9">Datos no Disponibles.</td></tr>
<%    }
%>    </table></td></tr>
<%  }else{
%>  <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();
%></html>
