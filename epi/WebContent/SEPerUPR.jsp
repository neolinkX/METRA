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
String iCveExpediente=request.getParameter("iCveExpediente").toString();//recoge parametro y lo convierte a string
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
        
        String Update = "update PERDATOS set ICVEPAISNAC="+iCvePaisNac+", ICVEESTADONAC="+iCveEstadoNac+ " where icveexpediente = "+iCveExpediente+";";
                        
        String IserDir = " update PERDIRECCION set ICVEPAIS="+iCvePaisD+", ICVEESTADO="+iCveEstadoD+ ", iCveMunicipio="+iCveMunicipio+", "+ 
                         " CCALLE ='"+Calle+"', CCOLONIA = '"+Colonia+"', CNUMEXT = '"+NoExt+"', CNUMINT = '"+NoInt+"' , ICP = "+CP+
                         " where icvepersonal = "+iCveExpediente+";";
        
              
        //String IserDir = "insert into PERDIRECCION(iCvePersonal, ICVEDIRECCION, CCALLE, CNUMEXT, CNUMINT, CCOLONIA, ICP, ICVEPAIS, ICVEESTADO, ICVEMUNICIPIO) values("+iCveExpediente+", " + ICVEDIRECCION + ", '" + Calle + "', '" + NoExt + "', '" + NoInt + "', '" + Colonia + "', " + CP + ", " + iCvePaisD + ", " + iCveEstadoD + ", " + iCveMunicipio + ")";          
              
        TVPERDatos vPERDatos = new TVPERDatos();
        TDPERDatosIn dPerDatosIn = new TDPERDatosIn();
        int iCampo;
        String cCampo= ""; 
        Vector respuesta = new Vector();

  /*      try {
                  cCampo = "" + request.getParameter("iCvePaisNac");
                  if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                    iCampo = Integer.parseInt(cCampo, 10); 
                  }
                  else {
                    iCampo = 0;
                  }
                  vPERDatos.setICvePaisNac(iCampo);
                  
                  cCampo = "" + request.getParameter("iCveEstadoNac");
                  if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                    iCampo = Integer.parseInt(cCampo, 10);
                  }
                  else {
                    iCampo = 0;
                  }
                  vPERDatos.setICveEstadoNac(iCampo);
                  
                  cCampo = "" + request.getParameter("iCvePaisD");
                  if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                    iCampo = Integer.parseInt(cCampo, 10);
                  }
                  else {
                    iCampo = 0;
                  }
                  vPERDatos.setICvePais(iCampo);
                  
                  cCampo = "" + request.getParameter("iCveEstadoD");
                  if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                    iCampo = Integer.parseInt(cCampo, 10);
                  }
                  else {
                    iCampo = 0;
                  }
                  vPERDatos.setICveEstado(iCampo);
                  
                  cCampo = "" + request.getParameter("iCveMunicipio");
                  if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
                    iCampo = Integer.parseInt(cCampo, 10);
                  }
                  else {
                    iCampo = 0;
                  }
                  vPERDatos.setICveMunicipio(iCampo);
                                    
                  dPerDatosIn.update(null,vPERDatos);
                  
            } catch(Exception e){
                 //e.printStackTrace();
            }*/
%>
            <center>           
            <br>
<%
       TDPERDatos dPerDatos = new TDPERDatos();
       
       String cBuscar = "where icvepersonal="+iCveExpediente+" ";
       
       Vector vcPersonal = new Vector();
       int TamVec=0;

       try{
                  dPerDatos.Sentencia(Update);
                  dPerDatos.Sentencia(IserDir);
                  
                  vcPersonal = dPerDatos.FindBySelExamGenera(cBuscar);
                   if(vcPersonal.size() == 0){
                   vcPersonal = dPerDatos.FindBySelector(cBuscar);
                   }
                   else{
                   TamVec = vcPersonal.size() -1;
                   }
         }catch(Exception e){
         vcPersonal = new Vector();
         e.printStackTrace();
       }
              
       TVPERDatos vPerDatos;
       if(vcPersonal.size() > 0){
       %>
        <SCRIPT LANGUAGE="JavaScript">
           window.resizeTo(550,400);
        </SCRIPT>
        <table  class="ETablaInfo" border="1" align="center" cellspacing="0" cellpadding="3">
           <tr>
             <td class="ETablaT">RFC</td>
             <td class="ETablaT">CURP</td>
             <td class="ETablaT">Nombre</td>
             <td class="ETablaT">Expediente</td>
             <td class="ETablaT">Último Examen</td>
             <td class="ETablaT" colspan="2">Clave de Personal</td>
           </tr>
          <%
            for(int i=TamVec;i<vcPersonal.size();i++){
              vPerDatos = (TVPERDatos) vcPersonal.get(i);
              out.print("<tr>");
              out.print(vEti.Texto("ETabla",vPerDatos.getCRFC()+(vPerDatos.getCHomoclave()==null ? "&nbsp;" : vPerDatos.getCHomoclave())));
              out.print(vEti.Texto("ETabla",(""+vPerDatos.getCCURP()).compareTo("null") == 0 ? "&nbsp;" : vPerDatos.getCCURP()));
              out.print(vEti.Texto("ETabla",vPerDatos.getCNombreCompleto()));
              out.print(vEti.Texto("ETabla",""+vPerDatos.getICveExpediente()));
              out.print(vEti.Texto("ETabla",""+vPerDatos.getINumExamen()));
              out.print("<td>"+vEti.clsAnclaTexto("ETabla",""+vPerDatos.getICvePersonal(),"javascript:fSelected('"+vPerDatos.getICvePersonal()+"',"+vPerDatos.getICveExpediente()+","+vPerDatos.getINumExamen()+",'"+ vPerDatos.getCNombreCompleto() + "');","Selected")+"</td>");
              out.print("<td>"+vEti.clsAnclaTexto("ETabla","Detalle","javascript:fDetalle('"+vPerDatos.getICvePersonal()+"');","Detalle")+"</td>");
              out.print("</tr>");
              out.print("<font color=\"Gray\">");
              out.print("<br>");              
              out.print("<b>Los datos correspondientes al expediente No.  " + vPerDatos.getICveExpediente() + ", se han actualizado de forma satisfactoria, ahora es posible realizar un Examen Psicofísico..!<br>" );
              out.print("<center>");      
              out.print("<br>");                
              out.print("</font>");
            }}
          %>
       </table>
            
            
            </center>
          
</body>
</html>