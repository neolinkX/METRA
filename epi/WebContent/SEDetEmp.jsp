
<%@ page import="gob.sct.medprev.*"%> 
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<html>
<%
    SEDetEmpCFG  clsConfig = new SEDetEmpCFG(); //Obt. Datos Generales
    TDPERDatos   tdPerDatos = new TDPERDatos(); //Obt. Datos de los Combos
    TDEXPExamAplica tdExam = new TDEXPExamAplica(); //Obt. Datos de los Combos
    TSimpleCampo vSCampo = new TSimpleCampo();  //Captura de Datos    
    String sAccion = request.getParameter("sAccion");   
    int IdDir = 0;
   
    //debug
/*
      //        System.out.println("\n********** sAccion " +sAccion);

      //        System.out.println("\n********** sEmpresa     " +request.getParameter("sEmpresa"));
      //        System.out.println("\n********** sDenominacion     " +request.getParameter("sDenominacion"));
      //        System.out.println("\n********** hdiCveEmpresa     " +request.getParameter("hdiCveEmpresa"));
      //        System.out.println("\n********** iCveGrupoEmp " +request.getParameter("iCveGrupoEmp"));
      //        System.out.println("\n********** sIdDgpmpt    " +request.getParameter("sIdDgpmpt"));
      //        System.out.println("\n********** sRFC         " +request.getParameter("sRFC"));
      //        System.out.println("\n********** iCveUniMed      " +request.getParameter("iCveUniMed"));
      //        System.out.println("\n********** iCveMdoTrans      " +request.getParameter("iCveMdoTrans"));
      //        System.out.println("\n********** sTpoPersona  " +request.getParameter("sTpoPersona"));
      //        System.out.println("\n********** sRegistro    " +request.getParameter("sRegistro")+"\n");
*/
    //debug

        
    if ( (sAccion != null) && (sAccion.equalsIgnoreCase("G")) ){
       TVPEREmpresa c = new TVPEREmpresa();
       c.setSDscEmpresa(request.getParameter("sEmpresa"));
       c.setSDenominacion(request.getParameter("sDenominacion"));
       c.setICveEmpresa(Integer.parseInt(request.getParameter("hdiCveEmpresa")));
       c.setICveGrupoEmp(Integer.parseInt(request.getParameter("iCveGrupoEmp")));
       c.setSIdDgpmpt(request.getParameter("sIdDgpmpt"));
       c.setSRFC(request.getParameter("sRFC"));
       c.setICveUniMed(Integer.parseInt(request.getParameter("iCveUniMed")));
       c.setICveMdoTrans(Integer.parseInt(request.getParameter("iCveMdoTrans")));
       c.setSTpoPersona(request.getParameter("sTpoPersona"));

         String[] fchx = request.getParameter("sRegistro").split("/");
         String FchReg = fchx[2]+ "-" +fchx[1]+ "-" +fchx[0];
       c.setSRegistro(FchReg);

       tdPerDatos.UpdEmpresa(c);
      }


  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("SEDetEmp.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("SEDetEmp.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  TError      vErrores      = new TError();  
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  
/*
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Muestra|";    // modificar
  String cCveOrdenar  = "iCveMuestra|";  // modificar
  String cDscFiltrar  = "Muestra|";    // modificar
  String cCveFiltrar  = "iCveMuestra|";  // modificar
  String cTipoFiltrar = "8|";                // modificar
  boolean lFiltros    = false;                 // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";             // modificar
*/
  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  /*String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();*/
  BeanScroller bs = clsConfig.getBeanScroller();
  /*String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();*/
  
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SEDetEmp.js"%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\SEDetEmp.js"></SCRIPT>-->
<script language="JavaScript">
//-------FCSReq7 
function fValidaTodo(Accion){
    var form = document.forms[0];

    if (Accion == 'G') {
      cVMsg = '';

      if (form.iCveGrupoEmp)
        cVMsg = cVMsg + fSinValor(form.iCveGrupoEmp,3,'Grupo Empresa:', true);

      if (form.sRFC)
        cVMsg = cVMsg + fSinValor(form.sRFC,2,'RFC:', true);

      if (form.iCveUniMed)
        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad Médica:', true);

      if (form.iCveMdoTrans)
        cVMsg = cVMsg + fSinValor(form.iCveMdoTrans,3,'Modo Transporte:', true);

      if (form.sTpoPersona)
        cVMsg = cVMsg + fSinValor(form.sTpoPersona,1,'Tipo Persona:', true);

      if (form.sRegistro)
        cVMsg = cVMsg + fSinValor(form.sRegistro,5,'Registro:', false);



        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }



function fModif(iCveEmpresa, Accion){
    form = document.forms[0];

    if ( fValidaTodo(Accion) ) {
      form.target = "_self";
      form.action = "SEDetEmp.jsp?hdiCveEmpresa=" +iCveEmpresa+ "&sAccion="+Accion;
      form.submit();
    }
}


function fIdentificar(iCveEmpresa){
    alert(iCveEmpresa);
  if(window.opener.fEmpSelected){
     window.opener.fEmpSelected(iCveEmpresa);
     window.close();
  }else{
      alert(iCveEmpresa);
     alert('El módulo ya no se encuentra disponible.');
     window.close();
  }
}



//-------FCSReq7



/*
  function fOnLoad(){

  }
*/

</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SEDetPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>

<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">


<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0">



<!-- <form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>"> -->
<form method="<%= vEntorno.getMetodoForm() %>" action="SEDetEmpDir.jsp"> 

  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  
  <input type="hidden" name="hdiCveEmpresa" value="<%=request.getParameter("hdiCveEmpresa")%>">    <% //FCSReq7 %>


  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
   <tr>
    <td valign="top">

                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                 <tr>
                                  <td colspan="4" class="ETablaT">Detalle de la Empresa</td>
                                 </tr>
 
                               <%
                                TEtiCampo vEti = new TEtiCampo();

                                  if (bs != null){
                                    //Formatea Fecha
                                      String sFchRegistro = bs.getFieldValue("sRegistro","&nbsp;").toString();
                                      String[] fch = sFchRegistro.split("-");
                                      sFchRegistro = fch[2]+ "/" +fch[1]+ "/" +fch[0];
                                    //Formatea Fecha

                                    if ( (sAccion == null) || sAccion.equals("G") ) {
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Empresa:","ECampo","text",10,10,"sDscEmpresa", bs.getFieldValue("sDscEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println(" <input type='hidden' name='sDscEmpresa' value='"+ bs.getFieldValue("sDscEmpresa","") + "'>");
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Denominación:","ECampo","text",10,10,"sDenominacion", bs.getFieldValue("sDenominacion","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Grupo Empresa:","ECampo","text",10,10,"iCveGrupoEmp", bs.getFieldValue("iCveGrupoEmp","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");
                                        
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","ID DGPMPT:","ECampo","text",10,10,"sIdDgpmpt", bs.getFieldValue("sIdDgpmpt","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",10,10,"sRFC", bs.getFieldValue("sRFC","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Unidad Médica:","ECampo","text",10,10,"sDscUniMed", bs.getFieldValue("sDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Modo Transporte:","ECampo","text",10,10,"sDscMdoTrans", bs.getFieldValue("sDscMdoTrans","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Tipo Persona:","ECampo","text",10,10,"sTpoPersona", bs.getFieldValue("sTpoPersona","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Registro:","ECampo","text",10,10,"dtRegistro", sFchRegistro,0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");
                                    }                        
                                    else if ( sAccion.equals("E") ){
                                    	
                                        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd,MM,yyyy");
                                        String cDate=sdf.format(new java.util.Date()); 

                                        out.println("<tr>");
                                        //out.print(vSCampo.EtiCampo("EEtiqueta", "Empresa:","ECampo", "text", 50, 95, "sEmpresa", bs.getFieldValue("sDscEmpresa","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
                                        out.print(vEti.EtiCampo("EEtiqueta","Empresa:","ECampo","text",10,10,"sDscEmpresa", bs.getFieldValue("sDscEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println(" <input type='hidden' name='sDscEmpresa' value='"+ bs.getFieldValue("sDscEmpresa","") + "'>");
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vSCampo.EtiCampo("EEtiqueta", "Denominación:","ECampo", "text", 50, 95, "sDenominacion", bs.getFieldValue("sDenominacion","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vSCampo.EtiCampo("EEtiqueta", "Grupo Empresa:","ECampo", "text", 13, 13, "iCveGrupoEmp", bs.getFieldValue("iCveGrupoEmp","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vSCampo.EtiCampo("EEtiqueta", "ID DGPMPT:","ECampo", "text", 13, 13, "sIdDgpmpt", bs.getFieldValue("sIdDgpmpt","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        out.print(vSCampo.EtiCampo("EEtiqueta", "RFC:","ECampo", "text", 13, 13, "sRFC", bs.getFieldValue("sRFC","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
                                        out.println("</tr>");

                                        out.println("<tr>");
                                          out.print("<td class='EEtiqueta'>Unidad Médica:</td>");

                                            out.print("<td class='Etabla'>");
                                              out.print("<select name='iCveUniMed'>");

                                              Vector a = new Vector();
                                              a =  tdPerDatos.DatosUnidMed();

                                              for ( int ind=0; ind < a.size(); ind++ ) {
                                                TVPEREmpresa a1 = (TVPEREmpresa) a.get(ind);

                                                if ( a1.getSDscUniMedCbo().equalsIgnoreCase(bs.getFieldValue("sDscUniMed","&nbsp;").toString()) )
                                                  out.print("<option selected value='" +a1.getICveUniMedCbo()+ "'>" +a1.getSDscUniMedCbo()+ "</option>");
                                                else
                                                  out.print("<option  value='" +a1.getICveUniMedCbo()+ "'>" +a1.getSDscUniMedCbo()+ "</option>");
                                              }

                                              out.print("</select>");
                                            out.print("</td>");
                                        out.println("</tr>");

                                        out.println("<tr>");
                                          out.print("<td class='EEtiqueta'>Modo Transporte:</td>");

                                            out.print("<td class='Etabla'>");
                                              out.print("<select name='iCveMdoTrans'>");

                                              Vector b = new Vector();
                                              b =  tdPerDatos.DatosMdoTrans();

                                              for ( int ind=0; ind < b.size(); ind++ ) {
                                                TVPEREmpresa b1 = (TVPEREmpresa) b.get(ind);

                                                if ( b1.getSDscMdoTransCbo().equalsIgnoreCase(bs.getFieldValue("sDscMdoTrans","&nbsp;").toString()) )
                                                  out.print("<option selected value='" +b1.getICveMdoTransCbo()+ "'>" +b1.getSDscMdoTransCbo()+ "</option>");
                                                else
                                                  out.print("<option  value='" +b1.getICveMdoTransCbo()+ "'>" +b1.getSDscMdoTransCbo()+ "</option>");
                                              }

                                              out.print("</select>");
                                            out.print("</td>");
                                        out.println("</tr>");

                                        out.println("<tr>");
                                        //out.print(vSCampo.EtiCampo("EEtiqueta", "Tipo Persona:","ECampo", "text", 13, 13, "sTpoPersona", bs.getFieldValue("sTpoPersona","&nbsp;").toString(),"", "fMayus(this);", true, true, true, request));
                                          out.print("<td class='EEtiqueta'>Tipo Persona:</td>");

                                            out.print("<td class='Etabla'>");
                                              out.print("<select name='sTpoPersona'>");
                                                if ( bs.getFieldValue("sTpoPersona","&nbsp;").toString().equalsIgnoreCase("F") ){
                                                  out.print("<option selected value='F'>F</option>");
                                                  out.print("<option value='M'>M</option>");
                                                  }
                                                else{
                                                  out.print("<option value='F'>F</option>");
                                                  out.print("<option selected value='M'>M</option>");
                                                  }

                                              out.print("</select>");
                                            out.print("</td>");
                                        out.println("</tr>");

                                        out.println("<tr>");
                                          out.print("<td class='EEtiqueta'>Registro:</td>");

                                            out.print("<td class='Etabla'>");
                                              out.print("<input type='text' size='12' maxlength='10' name='sRegistro' value='" +sFchRegistro+ "''>");
                                              out.print("<a class='EAncla' name='calendario' href='JavaScript:fLoadCal(" +cDate+ ",document.forms[0].sRegistro);document.forms[0].sRegistro.focus();'>(dd/mm/aaaa)</a>");
                                            out.print("</td>");
                                        out.println("</tr>");
                                    }                                                      

                                    out.print("<tr><td colspan=\"4\" align=\"center\">");

                                    if ( (sAccion == null) || sAccion.equals("G") )
                                      out.print(vEti.clsAnclaTexto("EEtiqueta","Editar","javascript:fModif("+request.getParameter("hdiCveEmpresa")+",'E');","Editarr") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                    else if ( sAccion.equals("E") )
                                      out.print(vEti.clsAnclaTexto("EEtiqueta","Guardar","javascript:fModif("+request.getParameter("hdiCveEmpresa")+",'G');","Guardarr") + "&nbsp;&nbsp;&nbsp;&nbsp;");

                                    out.print(vEti.clsAnclaTexto("EEtiqueta","Cancelar","javascript:window.close();","Cancelarr"));
                                    out.print("</td>");
                                    out.print("</tr>");
                                    out.print("<tr><td colspan=\"4\" align=\"center\">");
                                   	out.print(vEti.clsAnclaTexto("EEtiqueta","Direcciones","javascript:Validar();","Identificar2"));
                                    out.print("</td>");
                                    out.print("</tr>");
                                    out.print("<tr><td colspan=\"4\" align=\"center\">");
                                    try{
                                    	IdDir = tdExam.RegresaInt("Select count(icveempresa) from ctrdomicilio where icveempresa = "+request.getParameter("hdiCveEmpresa"));
                                    }catch(Exception e){
                                    	IdDir = 0;
                                            e.printStackTrace();
                                    }
                                    if(IdDir > 0){
                                    	out.print(vEti.clsAnclaTexto("EEtiqueta","Identificar","javascript:fIdentificar2("+ request.getParameter("hdiCveEmpresa") +",'"+bs.getFieldValue("sDscEmpresa","&nbsp;").toString()+"');","Identificar2"));
                                    }
                                   }
                            %>
                             </td>
                           </tr>
                          </table><% // Fin de Datos %>

  </td></tr>
  <%}else{%>
      <script language="JavaScript">
          fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');
      </script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>