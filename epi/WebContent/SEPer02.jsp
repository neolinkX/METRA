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

<html>
<title>Selector de Personal</title>
<%
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setNombrePagina("SEPer02.jsp");
  TEtiCampo vEti = new TEtiCampo();
  TSimpleCampo vSCampo = new TSimpleCampo();
  int TamVec=0;
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></SCRIPT>

<script language="JavaScript">
 function fAlerta(){
   alert("Este Expediente se encuentra Inhabilitado para Generar Examenes en Medicina Preventiva!");
  }
</script>

<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">
<form method="POST" action="_self">&nbsp;
  <input type="hidden" name="hdBuscar" value="0">
  <input type="hidden" name="hdCondicion" value="">
  <input type="hidden" name="hdiCvePersonal">
  <input type="hidden" name="hdTipo" value="<%if((""+request.getParameter("hdTipo")).compareTo("null") != 0) out.print(""+request.getParameter("hdTipo")); %>">
  <input type="hidden" name="hdPreDet" value="">
  <table class="ETablaInfo" background="" border="1" align="center" cellspacing="0" cellpadding="3">
    <% String cTipo = ""+request.getParameter("hdTipo");
       if(cTipo.compareTo("1") == 0 || cTipo.compareTo("2") == 0){
    %>
    <tr>
      <td class="ETablaT" colspan="2">Seleccione Unidad M&eacute;dica y M&oacute;dulo</td>
    </tr>
    <tr>
      <td class="ETabla" colspan="2">
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

           String cUniMed = "";
           TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
           int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("EPIProceso"));
           out.print(vEti.SelectOneRowSinTD("iCveUniMed","fChgUniMed();",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,cOmision1)+"&nbsp;");
           if(request.getParameter("iCveUniMed") == null){
             Vector vcUsuario = vUsuario.getVUniFiltro(iCveProceso);
             boolean lVer = false;
             for(int i=0;i<vcUsuario.size();i++){
               cUniMed = ""+((TVGRLUMUsuario)vcUsuario.get(i)).getICveUniMed();
               if(cUniMed.compareTo(cOmision1) == 0){
                  lVer = true;
                  break;
               }
               if(lVer == false){
                  cUniMed = ""+((TVGRLUMUsuario)vcUsuario.get(0)).getICveUniMed();
               }
             }
           }else{
             cUniMed = ""+request.getParameter("iCveUniMed");
           }
           out.print(vEti.SelectOneRowSinTD("iCveModulo","",(Vector) AppCacheManager.getColl("GRLModulo",cUniMed+"|"),"iCveModulo","cDscModulo",request,cOmision2)+"&nbsp;");
           out.print(vEti.clsAnclaTexto("ETabla","Predeterminar","javascript:fPreDet();","Predeterminar"));
         %>
      </td>
    </tr>   
    <%
       }
    %>
    <tr>
      <td class="ETablaT" colspan="2">Selector de Personal</td>
    </tr>
    <%
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "RFC","ECampo", "text", 13, 13, "cRFC", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "CURP","ECampo", "text", 30, 30, "cCURP", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Paterno","ECampo", "text", 35, 35, "cPaterno", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Materno","ECampo", "text", 35, 35, "cMaterno", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "Nombre(s)","ECampo", "text", 35, 35, "cNombre", "", "\" onkeyup=\"fVer(event);", "fMayus(this);", true, true, true, request));
      out.print("</tr>");
      out.print("<tr>");
      out.print(vSCampo.EtiCampo("EEtiqueta", "No. Personal","ECampo", "text", 35, 35, "iCvePersonal", "", "", "", true, true, true, request));
      out.print("</tr>");
      out.print("<tr><td colspan=\"2\" align=\"center\">");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar","javascript:fBuscar();","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
      out.print(vEti.clsAnclaTexto("EEtiqueta","Cancelar","javascript:window.close();","Cancelar"));
      out.print("</td></tr></table>&nbsp;");
      String cBuscar = ""+request.getParameter("hdBuscar");
      if(cBuscar.compareTo("1") == 0){
       TDPERDatos dPerDatos = new TDPERDatos();
       TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
       TFechas dtFecha = new TFechas();
       cBuscar = ""+request.getParameter("hdCondicion");

       Vector vcPersonal = new Vector();
       Vector vcInhabilita = new Vector();
     
       try{
         if(request.getParameter("hdTipo").compareTo("") == 0){
           //vcPersonal = dPerDatos.FindBySelector(cBuscar);
           //vcPersonal = dPerDatos.FindBySelExamGenera(cBuscar);
           //if(vcPersonal.size() == 0){
           vcPersonal = dPerDatos.FindBySelector(cBuscar);
           //}
           //else{
           //TamVec = vcPersonal.size() -1;}
         }
         if(request.getParameter("hdTipo").compareTo("1") == 0){
           //vcPersonal = dPerDatos.FindBySelExamen(cBuscar,Integer.parseInt(request.getParameter("iCveUniMed"),10),Integer.parseInt(request.getParameter("iCveModulo"),10),1);
             vcPersonal = dPerDatos.FindBySelExamGenera(cBuscar);
         }
         if(request.getParameter("hdTipo").compareTo("2") == 0){
           //vcPersonal = dPerDatos.FindBySelExamen(cBuscar,Integer.parseInt(request.getParameter("iCveUniMed"),10),Integer.parseInt(request.getParameter("iCveModulo"),10),2);
             vcPersonal = dPerDatos.FindBySelExamGenera(cBuscar);
         }
         if(request.getParameter("hdTipo").compareTo("3") == 0){
           //vcPersonal = dPerDatos.FindBySelExamen(cBuscar,Integer.parseInt(request.getParameter("iCveUniMed"),10),Integer.parseInt(request.getParameter("iCveModulo"),10),3);
             vcPersonal = dPerDatos.FindBySelExamGenera(cBuscar);
         }
         if(request.getParameter("hdTipo").compareTo("4") == 0){
           vcPersonal = dPerDatos.FindBySelExamGenera(cBuscar);
         }
       }catch(Exception e){
         vcPersonal = new Vector();
         e.printStackTrace();
       }

       TVPERDatos vPerDatos;
       TVMEDInhabilita vMEDInhabilita;
       if(vcPersonal.size() > 0){
       %>
        <SCRIPT LANGUAGE="JavaScript">
           window.resizeTo(700,500);
        </SCRIPT>
        <table  class="ETablaInfo" border="1" align="center" cellspacing="0" cellpadding="3">
           <tr>
             <td class="ETablaT">RFC</td>
             <td class="ETablaT">CURP</td>
             <td class="ETablaT">Nombre</td>
             <td class="ETablaT">Expediente</td>
             <td class="ETablaT">&Uacute;ltimo Examen</td>
             <td class="ETablaT" colspan="3">Clave de Personal</td>
           </tr>
          <%
            for(int i=0;i<vcPersonal.size();i++){
              vPerDatos = (TVPERDatos) vcPersonal.get(i);
              out.print("<tr>");
              out.print(vEti.Texto("ETabla",vPerDatos.getCRFC()+(vPerDatos.getCHomoclave()==null ? "&nbsp;" : vPerDatos.getCHomoclave())));
              out.print(vEti.Texto("ETabla",(""+vPerDatos.getCCURP()).compareTo("null") == 0 ? "&nbsp;" : vPerDatos.getCCURP()));
              out.print(vEti.Texto("ETabla",vPerDatos.getCNombreCompleto()));
              out.print(vEti.Texto("ETabla",""+vPerDatos.getICveExpediente()));
              out.print(vEti.Texto("ETabla",""+vPerDatos.getINumExamen()));
              
               if(vPerDatos.getICvePersonal() > vPerDatos.getICveExpediente()){
                         //System.out.println("La clave personal es mayor a la clave de expediente");
                                try{
                                    String Update = "update PERDATOS set ICVEEXPEDIENTE="+vPerDatos.getICvePersonal()+" WHERE ICVEPERSONAL =  "+vPerDatos.getICvePersonal()+"";
                                       dPerDatos.Sentencia(Update);
                                     }catch(Exception e){
                                     vcPersonal = new Vector();
                                     e.printStackTrace();
                                   }
                                %>
                   <SCRIPT LANGUAGE="JavaScript">
                        fBuscar();
                   </SCRIPT>
           <%

               }

              /*
              VALIDANDO SI ES UN EXPEDIENTE INHABILITADO
              */
                        int Inhabilitado = 0;
                        String fecha1 = "";
                        String fecha2 = "";
                        int Activo = 0;
                        
                         try{
                             String cCondicion = "iCvePersonal = "+ vPerDatos.getICvePersonal();
                             vcInhabilita = dMEDInhabilita.FindByAll(cCondicion);
                             fecha1 = dtFecha.getFechaYYYYMMDD(dtFecha.TodaySQL(),"-").toString();

                             for(int j=0;j<vcInhabilita.size();j++){
                                    Inhabilitado = 1;
                                    vMEDInhabilita = (TVMEDInhabilita) vcInhabilita.get(j);
                                    fecha2 = vMEDInhabilita.getFinInh().toString();
                             }
                             //System.out.println(Inhabilitado);

                             if(Inhabilitado == 1){
                            fecha1 = fecha1 + "-";
                                   int dia1;
                                   int mes1;
                                   int anno1;
                                   StringTokenizer solDatos = new StringTokenizer(fecha1, "-");
                                   anno1 = Integer.parseInt(solDatos.nextToken()+"".toString());
                                   mes1 = Integer.parseInt(solDatos.nextToken()+"".toString());
                                   dia1 = Integer.parseInt(solDatos.nextToken()+"".toString());
                            fecha2 = fecha2 + "-";
                                   int dia2;
                                   int mes2;
                                   int anno2;
                                   StringTokenizer solDatos2 = new StringTokenizer(fecha2, "-");
                                   anno2 = Integer.parseInt(solDatos2.nextToken()+"".toString());
                                   mes2 = Integer.parseInt(solDatos2.nextToken()+"".toString());
                                   dia2 = Integer.parseInt(solDatos2.nextToken()+"".toString());
                              
                                   if(anno2 > anno1){
                                      Activo = 1;
                                   }
                                   if(anno2 == anno1){
                                       if(mes2 > mes1){
                                             Activo = 1;
                                           }
                                       if(mes2 == mes1){
                                               if(dia2 >= dia1){
                                                   Activo = 1;
                                               }
                                           }
                                   }

                            
                               if(Activo == 1){
                                      out.print("<td>"+vEti.clsAnclaTexto("ETabla",""+vPerDatos.getICvePersonal(),"javascript:fSelected('"+vPerDatos.getICvePersonal()+"',"+vPerDatos.getICveExpediente()+","+vPerDatos.getINumExamen()+",'"+ vPerDatos.getCNombreCompleto() + "');fAlerta();","Selected")+"</td>");
                               }
                               else{
                                      out.print("<td>"+vEti.clsAnclaTexto("ETabla",""+vPerDatos.getICvePersonal(),"javascript:fSelected('"+vPerDatos.getICvePersonal()+"',"+vPerDatos.getICveExpediente()+","+vPerDatos.getINumExamen()+",'"+ vPerDatos.getCNombreCompleto() + "');","Selected")+"</td>");
                               }
                           }else{
                               out.print("<td>"+vEti.clsAnclaTexto("ETabla",""+vPerDatos.getICvePersonal(),"javascript:fSelected('"+vPerDatos.getICvePersonal()+"',"+vPerDatos.getICveExpediente()+","+vPerDatos.getINumExamen()+",'"+ vPerDatos.getCNombreCompleto() + "');","Selected")+"</td>");
                           }
                         }catch(Exception e){
                                 vcInhabilita = new Vector();
                                 e.printStackTrace();
                               }


                      out.print("<td>"+vEti.clsAnclaTexto("ETabla","Detalle","javascript:fDetalle('"+vPerDatos.getICvePersonal()+"');","Detalle")+"</td>");
                      out.print("</tr>");
            }
          %>
                         </table>   
       <%
       }else{
       %> 
        <SCRIPT LANGUAGE="JavaScript">
           window.resizeTo(550,390);
        </SCRIPT>        
        <center>
         <font color="Gray">
             <b>No existe Personal bajo el criterio de b&uacute;squeda seleccionado!</b>
         </font>
         <br>
         <a href="SEPer022envia.jsp">Dar de alta</a>
         <font color="Gray">   ó   </font>  
         <a href="javascript:window.close()">Salir</a>
         </center>
       
      
      
             <%
       }
      }
       %>
</form>

<!--    <a href="seperactualiza.jsp">Actualizar datos</a> -->
</body>
</html>