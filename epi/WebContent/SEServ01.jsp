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
<title>Consulta de Servicios</title>
<%
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setNombrePagina("SEServ01.jsp");
  TEtiCampo vEti = new TEtiCampo();
  TSimpleCampo vSCampo = new TSimpleCampo();

  TDEXPResultado dEXPResultado = new TDEXPResultado();
  TVEXPResultado vEXPResultado;
  Vector vcEXPResultado = new Vector();

  vcEXPResultado = dEXPResultado.FindServAdicionales(" where EXPResultado.iCveExpediente = "
  + request.getParameter("hdiCveExpediente") + " and EXPResultado.iNumExamen = " + request.getParameter("hdiNumExamen")
  + " and EXPResultado.iCveServicio = " + request.getParameter("hdiCveServicio") );
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></SCRIPT>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="" topmargin="0" leftmargin="0">
<form method="POST" action="_self">&nbsp;
  <input type="hidden" name="hdBuscar" value="0">
  <input type="hidden" name="hdCondicion" value="">

 <input type="hidden" name="hdiCveExpediente" value="<%=request.getParameter("hdiCveExpediente")%>">
 <input type="hidden" name="hdiNumExamen" value="<%=request.getParameter("hdiNumExamen")%>">
 <input type="hidden" name="hdiCveServicio" value="<%=request.getParameter("hdiCveServicio")%>">
 <input type="hidden" name="hdcDscServicio" value="<%=request.getParameter("hdcDscServicio")%>">


  <input type="hidden" name="hdiCvePersonal">
  <input type="hidden" name="hdTipo" value="<%if((""+request.getParameter("hdTipo")).compareTo("null") != 0) out.print(""+request.getParameter("hdTipo")); %>">
  <input type="hidden" name="hdPreDet" value="">
  <table class="ETablaInfo" background="" border="1" align="center" cellspacing="0" cellpadding="3">
    <tr>
      <td class="ETablaT" colspan="6"><%=request.getParameter("hdcDscServicio")%></td>
    </tr>
    <%
    int y = 0;
                             if (vcEXPResultado.size() > 0){
                               out.println("<tr>");
                               for (int z = 0; z < vcEXPResultado.size(); z++){
                                  vEXPResultado = (TVEXPResultado) vcEXPResultado.get(z);
                                  if (vEXPResultado.getICveTpoResp() == 6)
                                     out.println("<td class='ECampoC' colspan='6'>"+ vEXPResultado.getCPregunta() + "</td>");
                                  else
                                     out.println("<td class='EEtiqueta'>"+ vEXPResultado.getCPregunta() + "</td>");

                                  if (vEXPResultado.getICveTpoResp() == 1 && vEXPResultado.getLConcluido() == 1){
                                    if (vEXPResultado.getLLogico() == 0)
                                       out.println("<td class='ECampo'>NO</td>");
                                    else
                                       out.println("<td class='ECampo'>SI</td>");
                                  }
                                  if ((vEXPResultado.getICveTpoResp() == 2 && vEXPResultado.getLConcluido() == 1) ||
                                      (vEXPResultado.getICveTpoResp() == 4 && vEXPResultado.getLConcluido() == 1)){
                                     if (vEXPResultado.getCCaracter() != null)
                                        out.println("<td class='ECampo'>"+ vEXPResultado.getCCaracter()+"</td>");
                                     else
                                        out.println("<td class='ECampo'>&nbsp;</td>");
                                  }
                                  if (vEXPResultado.getICveTpoResp() == 3 && vEXPResultado.getLConcluido() == 1){
                                    if (vEXPResultado.getDValorIni() != 0){
                                       if (vEXPResultado.getICveServicio() == Integer.parseInt(vParametros.getPropEspecifica("EMOServicio")))
                                         out.println("<td class='ECampo'>"+ vEXPResultado.getIValorIni() +"</td>");
                                       else
                                         out.println("<td class='ECampo'>"+ vEXPResultado.getDValorIni() +"</td>");
                                    }
                                    else
                                       out.println("<td class='ECampo'>&nbsp;</td>");
                                  }
                                  if (vEXPResultado.getICveTpoResp() == 5 && vEXPResultado.getLConcluido() == 1){
                                    if (vEXPResultado.getDValorIni() != 0)
                                       out.println("<td class='ECampo'>"+ vEXPResultado.getDValorIni()+" - "+vEXPResultado.getDValorFin()+"</td>");
                                     else
                                       out.println("<td class='ECampo'>&nbsp;</td>");
                                  }
                                  if (vEXPResultado.getICveTpoResp() == 6 && vEXPResultado.getLConcluido() == 1){
                                        y = 1;
                                  }
                                  else{
                                    if (vEXPResultado.getLConcluido() != 1)
//                                       out.println("<td class='ECampo'>CAP</td>");
//                                   else
                                       out.println("<td class='ECampo' colspan='2'>NC</td>");
                                  }

                                  y += 1 ;
                                  if( y == 2 ){
                                   out.println("<tr>");
                                   out.println("</tr>");
                                    y = 0;
                                  }
                               }
                               out.println("</tr>");
                             }
    %>
       </table>

</form>
</body>
</html>