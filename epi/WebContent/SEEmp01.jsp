<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>

<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<html>
<title>Selector de Empresas</title>
<%
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setNombrePagina("SEEmp01.jsp");
  TEtiCampo vEti = new TEtiCampo();
  TSimpleCampo vSCampo = new TSimpleCampo();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\SEEmp01.js"></SCRIPT>-->


<script language="JavaScript">
  <% //FCSReq7 -Función que hace el Llamado a la Ventana Tipo Pop-Ups del "Detalle de la Empresa"-  %>
  //function fDetalle(iCveEmpresa){
    //wPrueba = open("SEDetEmp.jsp?hdiCveEmpresa=" + iCveEmpresa, "Selector1",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=550,height=575,screenX=800,screenY=600');
    //wPrueba.moveTo(15, 15);
  //}

  var wPrueba;
  function fDetalle(iCveEmpresa){
    if((wPrueba != null) && (!wPrueba.closed))
      wPrueba.focus();
    else{
      wPrueba = open("SEDetEmp.jsp?hdiCveEmpresa=" + iCveEmpresa, "Selector1",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=550,height=575,screenX=800,screenY=600');
      wPrueba.moveTo(15, 15);
    }
  }

</script>



<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="" topmargin="0" leftmargin="0">
<form method="POST" action="_self">&nbsp;
  <input type="hidden" name="hdBuscar" value="0">
  <input type="hidden" name="hdCondicion" value="">
  <table class="ETablaInfo" background="" border="1" align="center" cellspacing="0" cellpadding="3">
    <tr>
      <td class="ETablaT" colspan="2">Selector de Empresas</td>
    </tr>
    <%
      out.print("<tr>");
      Vector vcTpoPersona = new Vector();
      vcTpoPersona.add("M|Moral");
      vcTpoPersona.add("F|Física");
      out.print(vEti.Texto("EEtiqueta","Tipo de Persona"));
      out.print("<td>");
      out.print(vEti.SelectOneRowSinTD("cTpoPersona","",vcTpoPersona, request, ""));
      out.print("</td></tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "RFC","ECampo", "text", 13, 13, "cRFC", "","", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "CURP","ECampo", "text", 30, 30, "cCURP", "", "", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Nombre o Razón Social","ECampo", "text", 35, 35, "cNombreRS", "", "", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Paterno","ECampo", "text", 35, 35, "cPaterno", "", "", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Materno","ECampo", "text", 35, 35, "cMaterno", "", "", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Denominación","ECampo", "text", 35, 35, "cDenominacion", "", "", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Clave RPA","ECampo", "text", 35, 35, "cCveRPA", "", "", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr><td colspan=\"2\" align=\"center\">");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar","javascript:fBuscar();","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Cancelar","javascript:window.close();","Cancelar"));
      out.print("</td></tr></table>&nbsp;");
      String cBuscar = ""+request.getParameter("hdBuscar");
      if(cBuscar.compareTo("1") == 0){
       TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
       cBuscar = ""+request.getParameter("hdCondicion");

       Vector vcPersonal = new Vector();

       try{
           vcPersonal = dGRLEmpresas.FindByAll(cBuscar);
       }catch(Exception e){
         vcPersonal = new Vector();
         e.printStackTrace();
       }

       TVGRLEmpresas vGRLEmpresas;
       if(vcPersonal.size() > 0){
       %>
        <SCRIPT LANGUAGE="JavaScript">
           window.resizeTo(750,500);
        </SCRIPT>
        <table  width="95%" class="ETablaInfo" border="1" align="center" cellspacing="0" cellpadding="1">
           <tr>
             <td class="ETablaT" colspan="2">Persona</td>
             <td class="ETablaT">RFC</td>
             <td class="ETablaT">CURP</td>
             <td class="ETablaT">Nombre</td>
             <td class="ETablaT">Denominación</td>
             <td class="ETablaT">Clave RPA</td>
             <td class="ETablaT">DETALLE</td>     <% //FCSReq7 -"Detalle de la Empresa"-  %>
           </tr>
          <%
            for(int i=0;i<vcPersonal.size();i++){
              vGRLEmpresas = (TVGRLEmpresas) vcPersonal.get(i);
              out.print("<tr>");
              if(vGRLEmpresas.getiCveDomicilio() > 0){
              	out.print("<td align=\"center\">"+vEti.clsAnclaTexto("ETabla",""+vGRLEmpresas.getICveEmpresa(),"javascript:fEmpSelected("+vGRLEmpresas.getICveEmpresa()+",'"+vGRLEmpresas.getCNombreRS()+"','"+vGRLEmpresas.getCApPaterno()+"','"+vGRLEmpresas.getCApMaterno()+"','"+vGRLEmpresas.getCRFC()+"','"+vGRLEmpresas.getCCurp()+"','"+vGRLEmpresas.getCTpoPersona()+"');","Seleccionar")+"</td>");
              }else{
            	  out.print("<td align=\"center\">"+vEti.clsAnclaTexto("ETabla",""+vGRLEmpresas.getICveEmpresa(),"javascript:fEmpSelecDom();","Seleccionar")+"</td>");
              }
              out.print(vEti.Texto("ETabla",vGRLEmpresas.getCTpoPersona()));
              out.print(vEti.Texto("ETabla",vGRLEmpresas.getCRFC()));
              out.print(vEti.Texto("ETabla",(""+vGRLEmpresas.getCCurp()).compareTo("null") == 0 ? "&nbsp;" : vGRLEmpresas.getCCurp()));
              out.print(vEti.Texto("ETabla",vGRLEmpresas.getCNombreCompleto()));
              out.print(vEti.Texto("ETabla",""+vGRLEmpresas.getCDenominacion()));
              out.print(vEti.Texto("ETabla",vGRLEmpresas.getCCveRPA().compareTo("") == 0 ? "&nbsp;" : vGRLEmpresas.getCCveRPA()));

               //FCSReq7 -"Detalle de la Empresa"-
                //out.println("<td align='center'><a class='EEtiqueta' name='Detalle' href='javascript:fDetalle2(" +vGRLEmpresas.getICveEmpresa()+ ");'>Detalle</a></td>");
                out.print("<td>"+vEti.clsAnclaTexto("ETabla","Detalle","javascript:fDetalle2('"+vGRLEmpresas.getICveEmpresa()+"');","Detalle")+"</td>");


              out.print("</tr>");
            }
          %>
       </table>
       <%
       }else{
       %>
         <SCRIPT LANGUAGE="JavaScript">
           alert("No existe alguna empresa bajo el criterio de búsqueda seleccionado!");
         </SCRIPT>
       <%
       }
      }
       %>
</form>
</body>
</html>