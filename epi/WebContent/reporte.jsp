<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>


<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.design.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*"%>
<%@ page import="net.sf.jasperreports.engine.export.*"%>
<%@ page import="net.sf.jasperreports.engine.util.*"%>
<%@ page import="net.sf.jasperreports.view.*"%>
<%@ page import="net.sf.jasperreports.view.save.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>


<html>
<head><title>Generando Reporte...............</title>

<%
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setNombrePagina("SEPer01.jsp");
  TEtiCampo vEti = new TEtiCampo();
  TSimpleCampo vSCampo = new TSimpleCampo();
%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></SCRIPT>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">






<%

          //        System.out.println("Comienza el Reporte----------------------------");

 

 %>

 

<script language="JavaScript">

function regresar(){

            history.back();

}

function error(){

            alert("Error encontrando procesando el periodo: " + periodo);

            history.back();

}

</script>



<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">

<%
           String cPreDet = "";
           if((""+request.getParameter("hdPreDet")).compareTo("1")==0){
             cPreDet = "" + request.getParameter("iCveUniMed") + "|" + request.getParameter("iCveModulo") + "|";
             request.getSession(true).setAttribute("SelPer",cPreDet);
           }

           String cOmision1 = "", cOmision2="";
           if(request.getSession(true).getAttribute("SelPer")!=null){
             StringTokenizer stSelPer = new StringTokenizer(""+request.getSession(true).getAttribute("SelPer"),"|");
             cOmision1=stSelPer.nextToken();
             cOmision2=stSelPer.nextToken();
           }

           
           TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
           
         %>


         <%

          //        System.out.println("Comienza el Reporte----------------------------");

 %>

 </HEAD>


<%

try{

            ResultSet rs = null;

            Statement st = null;

            String sql = null;

            String driver = "COM.ibm.db2.jdbc.net.DB2Driver";

            String connectString = "jdbc:db2://10.33.141.128:6803/MEDPREVD";

            String user = "db2upamp";

            String password = "dbZupamp";

            Class.forName(driver);

            Connection conn = DriverManager.getConnection(connectString, user, password);
            /////////////////////////////////////////////

          //        System.out.println("********Compilamos medprev.jrxml OK********");

            System.setProperty(

                        "jasper.reports.compile.class.path",

                        application.getRealPath("/WEB-INF/lib/jasperreports-2.0.2.jar") +

                        System.getProperty("path.separator") +

                        application.getRealPath("/WEB-INF/classes/")

                        );

          //        System.out.println("*****Cargamos el jasperreports-2.0.2.jar OK*********");

            System.setProperty(

                        "jasper.reports.compile.temp",

                        application.getRealPath("/reports/")

                        );

 

            JasperCompileManager.compileReportToFile(application.getRealPath("/reporte/medprev.jrxml"));

           

          //        System.out.println("******Fin de la Compilamos el archivos***********");

            /////////////////////////////////////////////

 

            File reportFile = new File(application.getRealPath("/reporte/medprev.jasper"));

 

            Map parameters = new HashMap();

                                                

            byte[] bytes =

                        JasperRunManager.runReportToPdf(

                                   reportFile.getPath(),

                                   parameters,

                                   conn

                                   );

           

            response.setContentType("application/pdf");

            response.setContentLength(bytes.length);

            ServletOutputStream ouputStream = response.getOutputStream();

            ouputStream.write(bytes, 0, bytes.length);

            ouputStream.flush();

            ouputStream.close();

           

          //        System.out.println("jasperPDF OK..............");

            ///////////////////////////////////////////////////////////////

          //        System.out.println("Fin del reporte pago_reporte_nomina.............");

           

                                   }catch (JRException e)

                                   {System.out.println("Error:" +e.getMessage());}

                                   catch (Exception e)

                                   {

                                   e.printStackTrace();

                                 //        System.out.println("Error2:" +e.getMessage());

                                   }         

%>



</body>
</html>