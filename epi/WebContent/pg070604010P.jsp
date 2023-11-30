<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>

<html>
<%
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg0700007.jsp");
  vEntorno.setOnLoad("fOnLoad();");

  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cRutaAyuda = vParametros.getPropEspecifica("html");
  String cTitulo = "" + request.getParameter("hdTitulo");
  if(cTitulo.compareTo("null") == 0)
     cTitulo = "";
  else
     cTitulo = vParametros.getPropEspecifica("NomModulo") + " " + cTitulo;

  String cAccion = ""+request.getParameter("hdAccion");
  if(cAccion.compareTo("") != 0){
    try{
       HashMap hmPermisos = (HashMap) request.getSession(true).getAttribute("PermisosUsuario");
       if(hmPermisos.containsKey(cAccion)){
          StringTokenizer stTitulo = new StringTokenizer(""+hmPermisos.get(cAccion),"|");
          cTitulo = stTitulo.nextToken();
       }
    }catch(Exception e){
       System.out.print("pg0700007.jsp:");
       e.printStackTrace();
    }
  }
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700007.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdEstado" value="">
  <input type="hidden" name="hdAccion" value="">
  <input type="hidden" name="hdTitulo" value="">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>fondoSTitulo<%= cTipoImg %>">
    <tr>
     <TD class="ETituloTPag" align="center" width="100%"><%=cTitulo.toUpperCase()%></TD>
    </tr>
  </table>
<%
  int j = 0;
  out.println("<script LANGUAGE=" + "\""  + "JavaScript"  + "\""  + ">");
  if (request.getParameter("opc") != null){
     String ObjetoDes = request.getParameter("objDes");
     String iUniMed = request.getParameter("val1");
     switch(new Integer(request.getParameter("opc")).intValue()) {
        case 1:
            TDGRLModulo dModulo = new TDGRLModulo();
            Vector vModulos = new Vector();
            vModulos = dModulo.FindByAll(" where iCveUniMed = " + iUniMed);
            TVGRLModulo tvModulo = new TVGRLModulo();

            out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
            out.println("form." + ObjetoDes + ".length=1;");
            if (vModulos.size() > 0){
               out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
               out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
               for (int i = 0; i < vModulos.size(); i++){
                  j = j + 1;
                  tvModulo = (TVGRLModulo) vModulos.get(i);
                  out.println("i=form." + ObjetoDes + ".length + 1;");
                  out.println("form." + ObjetoDes + ".length=i;");
                  out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + tvModulo.getCDscModulo() + "\"" +";");
                  out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + tvModulo.getICveModulo() + "\"" +";");
               }
            } else {
               out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
               out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
            }
        break;
        case 2:
            iUniMed = request.getParameter("val2");
            String iModulo = request.getParameter("val1");
            DAOGRLAreaModulo dAreaModulo = new DAOGRLAreaModulo();
            TVGRLAreaModulo tvAreaM = new TVGRLAreaModulo();
            Vector vAreas = new Vector();
            String q = " WHERE GRLAreaModulo.iCveUniMed = " + iUniMed +
                       "  AND GRLAreaModulo.iCveModulo = " + iModulo + " ";
            vAreas = dAreaModulo.FindByAll(q);
            out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
            out.println("form." + ObjetoDes + ".length=1;");
            if (vAreas.size() > 0) {
               out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
               out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
               for (int i = 0; i < vAreas.size(); i++) {
                   j = j + 1;
                   TVGRLAreaModulo tvAreaModulo = (TVGRLAreaModulo) vAreas.get(i);
                   out.println("i=form." + ObjetoDes + ".length + 1;");
                   out.println("form." + ObjetoDes + ".length=i;");
                   out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + tvAreaModulo.getCDscArea() + "\"" +";");
                   out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + tvAreaModulo.getICveArea() + "\"" +";");
               }
            } else {
               out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
               out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
            }
        break;
     }
  }
  out.print("</script>");
%>
</form>
</body>
</html>
