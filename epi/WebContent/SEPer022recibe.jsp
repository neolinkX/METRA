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

<jsp:useBean id="datos" scope="page" class="gob.sct.medprev.dao.TDPERDatosIn">
    <jsp:setProperty name="datos" property="*"/>
</jsp:useBean>   


<html>
<title>Alta Personal</title>
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
//Recibiendo dato de SEPer22recibe
String RFC2=request.getParameter("RFC2").toString();//recoge parametro y lo convierte a string
String Paterno2=request.getParameter("Paterno2").toString();//recoge parametro y lo convierte a string
String Materno2=request.getParameter("Materno2").toString();//recoge parametro y lo convierte a string
String Nombre2=request.getParameter("Nombre2").toString();//recoge parametro y lo convierte a string
String Sexo=request.getParameter("Sexo").toString();//recoge parametro y lo convierte a string
String iCvePaisNac=request.getParameter("iCvePaisNac").toString();//recoge parametro y lo convierte a string
String iCveEstadoNac=request.getParameter("iCveEstadoNac").toString();//recoge parametro y lo convierte a string
String iCvePaisD=request.getParameter("iCvePaisD").toString();//recoge parametro y lo convierte a string
String iCveEstadoD=request.getParameter("iCveEstadoD").toString();//recoge parametro y lo convierte a string
String iCveMunicipio=request.getParameter("iCveMunicipio").toString();//recoge parametro y lo convierte a string
String Calle=request.getParameter("Calle").toString();//recoge parametro y lo convierte a string
String Colonia=request.getParameter("Colonia").toString();//recoge parametro y lo convierte a string
String NoExt=request.getParameter("NoExt").toString();//recoge parametro y lo convierte a string
String NoInt=request.getParameter("NoInt").toString();//recoge parametro y lo convierte a string  
String CP=request.getParameter("CP").toString();//recoge parametro y lo convierte a string
String CCURP=request.getParameter("CCURP").toString();//recoge parametro y lo convierte a string
////Nuevos campos agregados el dia 25/04/2013
String CTel=request.getParameter("CTel").toString();//recoge parametro y lo convierte a string
String CCiudad=request.getParameter("CCiudad").toString();//recoge parametro y lo convierte a string
        
String Sexo_DGIS = "";
		if(Sexo.equals("F")){
			Sexo_DGIS = "M";
		}
		if(Sexo.equals("M")){
			Sexo_DGIS = "H";
		}

        //Obetener año de nacimiento
        String cadena = new String(RFC2);
        String Ano = "19" + cadena.charAt(4) + cadena.charAt(5);

       
        //Obetener mes de nacimiento
        String cadena2 = new String(RFC2);
        String Mes = "" + cadena2.charAt(6) + cadena2.charAt(7);
       

        //Obetener dia de nacimiento
        String cadena3 = new String(RFC2);
        String Dia = "" + cadena3.charAt(8) + cadena3.charAt(9);
        
        
        //Armando sentencia SQL
        String Huella2="0";
        String Donadororg2="0";
        String ICVEDIRECCION="1";
        
        String insertsql = "insert into PERDatos(iCvePersonal, iCveExpediente, cNombre, cSexo, cApPaterno, cApMaterno, cRFC, DTNACIMIENTO, LDONADORORG, LNOHUELLAS, ICVEPAISNAC, ICVEESTADONAC, ICVEDIRECCION, CCURP, iCveUsuRegistra, tsgenerado, cSexo_DGIS) values(";
        String insertsql2= "'" + Nombre2 + "', '" + Sexo + "', '" + Paterno2 + "', '" + Materno2 + "', '" + RFC2 + "', '" + Ano + "-" + Mes + "-" + Dia + "', " + Donadororg2 + ", " + Huella2 + ", " + iCvePaisNac + ", " + iCveEstadoNac + ", 1, '" + CCURP + "',"+vUsuario.getICveusuario()+", (SELECT current timestamp FROM sysibm.sysdummy1),'"+Sexo_DGIS+"')";
              
        String insertsql3 = "insert into PERDIRECCION(iCvePersonal, ICVEDIRECCION, CCALLE, CNUMEXT, CNUMINT, CCOLONIA, ICP, ICVEPAIS, ICVEESTADO, ICVEMUNICIPIO, CCIUDAD, CTEL) values(";
        String insertsql4= ", " + ICVEDIRECCION + ", '" + Calle + "', '" + NoExt + "', '" + NoInt + "', '" + Colonia + "', " + CP + ", " + iCvePaisD + ", " + iCveEstadoD + ", " + iCveMunicipio + ", '" + CCiudad + "', '" + CTel + "')";
              
              
              //Enviando datos al Bean
              datos.setRFC2(RFC2);
              datos.setPaterno2(Paterno2);
              datos.setMaterno2(Materno2);
              datos.setNombre2(Nombre2);
             
              datos.setinsertsql(insertsql);   
              datos.setinsertsql2(insertsql2);   
              datos.setinsertsql3(insertsql3);   
              datos.setinsertsql4(insertsql4);
%>
          
            <center>           
            <br>
            <font color="Gray">
            <jsp:getProperty name="datos" property="insert2"/>
            </font>
            
<%
       TDPERDatos dPerDatos = new TDPERDatos();
       String Referencia = RFC2;
       Referencia += "'";
       
       String cBuscar = "where UCASE(PERDatos.cRFC) like '";
       cBuscar += Referencia;
       
       
       Vector vcPersonal = new Vector();

       try{
           if(RFC2 != null){
           vcPersonal = dPerDatos.FindBySelector(cBuscar);
         }
         }catch(Exception e){
         vcPersonal = new Vector();
         e.printStackTrace();
       }
              
       TVPERDatos vPerDatos;
       if(vcPersonal.size() > 0){
       %>
        <SCRIPT LANGUAGE="JavaScript">
           window.resizeTo(650,190);
        </SCRIPT>
        <table  class="ETablaInfo" border="1" align="center" cellspacing="0" cellpadding="3">
           <tr>
             <td class="ETablaT">RFC</td>
             <td class="ETablaT">CURP</td>
             <td class="ETablaT">Nombre</td>
             <td class="ETablaT">Expediente</td>
             <td class="ETablaT">Ultimo Examen</td>
             <td class="ETablaT" colspan="2">Clave de Personal</td>
           </tr>
          <%
            for(int i=0;i<vcPersonal.size();i++){
              vPerDatos = (TVPERDatos) vcPersonal.get(i);
              if(vPerDatos.getICvePersonal() > vPerDatos.getICveExpediente()){
                         //System.out.println("La clave personal es mayor a la clave de expediente");
                                try{
                                    String Update = "update PERDATOS set ICVEEXPEDIENTE="+vPerDatos.getICvePersonal()+" WHERE ICVEPERSONAL =  "+vPerDatos.getICvePersonal()+";";
                                       dPerDatos.Sentencia(Update);
                                     }catch(Exception e){
                                     vcPersonal = new Vector();
                                     e.printStackTrace();
                                   }
                         //System.out.println("La clave de expediente ha sido actualizada con respecto a la clave personal");
                     }              
              out.print("<tr>");
              out.print(vEti.Texto("ETabla",vPerDatos.getCRFC()+(vPerDatos.getCHomoclave()==null ? "&nbsp;" : vPerDatos.getCHomoclave())));
              out.print(vEti.Texto("ETabla",(""+vPerDatos.getCCURP()).compareTo("null") == 0 ? "&nbsp;" : vPerDatos.getCCURP()));
              out.print(vEti.Texto("ETabla",vPerDatos.getCNombreCompleto()));
              out.print(vEti.Texto("ETabla",""+vPerDatos.getICvePersonal()));
              out.print(vEti.Texto("ETabla",""+vPerDatos.getINumExamen()));
              out.print("<td>"+vEti.clsAnclaTexto("ETabla",""+vPerDatos.getICvePersonal(),"javascript:fSelected('"+vPerDatos.getICvePersonal()+"',"+vPerDatos.getICveExpediente()+","+vPerDatos.getINumExamen()+",'"+ vPerDatos.getCNombreCompleto() + "');","Selected")+"</td>");
              out.print("<td>"+vEti.clsAnclaTexto("ETabla","Detalle","javascript:fDetalle('"+vPerDatos.getICvePersonal()+"');","Detalle")+"</td>");
              out.print("</tr>");
            }}
          %>
       </table>
            
            
            </center>
          
</body>
</html>