<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Jaime Enrique Su�rez Romero
 * @version
 * Clase:
 */%>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*" %>
<%

  TParametro   vParametros   = new TParametro("07");
  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  String cError = "";

  TEtiCampo    vEti          = new TEtiCampo();
  Vector vcRecords = new Vector();
  Vector vcMdoTrans = new Vector();
  Vector vcTmp = new Vector();

  TVDinRep vRecord;
  String cRep = ""+request.getParameter("hdRep");
  String cRep2 = ""+request.getParameter("hdRep");// VARIABLE PARA OBTENER TOTAL DE EXAMENES/ 12-02-2013 AG SA SANDOVAL
  String cCond = ""+request.getParameter("hdQuery");
  int iTmp1, iTmp2;
  String TotalGen ="";//Variable para obtener el total de examenes dictaminados en tiempo
  String TotalDic ="";//Variable para obtener el total de examenes dictaminados fuera de tiempo
  String TotalExaDic ="";//Variable para obtener el total de examenes dictaminados
  try{

    TDDinRep dDinRep = new TDDinRep();

    if(cRep.equals("1")){
     //  cRep =  " and {FN YEAR(expexamcat.dtiniciovig)} = " + cCond;
         cRep =  " and {FN YEAR(expexamcat.dtiniciovig)} = " + cCond;
         cRep2 =  " and {FN YEAR(c.dtdictaminado)} = " + cCond;
    }

    if(cRep.equals("2")){
       StringTokenizer stAnioMes = new StringTokenizer(cCond,"|");
       StringTokenizer stAnioMes2 = new StringTokenizer(cCond,"|");
   /*    cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + stAnioMes.nextToken();
       cRep =  cRep + " and {FN MONTH(expexamaplica.dtsolicitado)} = " + stAnioMes.nextToken();*/
               
       cRep =  " and {FN YEAR(expexamcat.dtiniciovig)} = " + stAnioMes.nextToken();
       cRep =  cRep + " and {FN MONTH(expexamcat.dtiniciovig)} = " + stAnioMes.nextToken(); 
       
       cRep2 =  " and {FN YEAR(c.dtdictaminado)} = " + stAnioMes2.nextToken();
       cRep2 =  cRep2 + " and {FN MONTH(c.dtdictaminado)} = " + stAnioMes2.nextToken();     
    }

    
    if(cRep.equals("3")){
        String anio="";
        String mes="";
        String dia="";
       //cRep =  " and expexamaplica.dtsolicitado = '" + cCond + "'";
        
        cCond=cCond+"/";
        
       StringTokenizer stAnioMesDia = new StringTokenizer(cCond,"/");
       dia = stAnioMesDia.nextToken();
       mes = stAnioMesDia.nextToken();
       anio = stAnioMesDia.nextToken();
      /* 
       cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + anio;
       
       cRep =  cRep + " and {FN MONTH(expexamaplica.dtsolicitado)} = " + mes;
       
       cRep =  cRep + " and {FN DAY(expexamaplica.dtsolicitado)} = " + dia;   */
       
       cRep =  " and {FN YEAR(expexamcat.dtiniciovig)} = " + anio;
       
       cRep =  cRep + " and {FN MONTH(expexamcat.dtiniciovig)} = " + mes;
       
       cRep =  cRep + " and {FN DAY(expexamcat.dtiniciovig)} = " + dia; 
       
	   cRep2 =  " and {FN YEAR(c.dtdictaminado)} = " + anio;
       
       cRep2 =  cRep2 + " and {FN MONTH(c.dtdictaminado)} = " + mes;
       
       cRep2 =  cRep2 + " and {FN DAY(c.dtdictaminado)} = " + dia; 
       
       }
       
       
    String cSQL =
       "select icvemdotrans, cdscmdotrans "+
       "from grlmdotrans "+
       "order by icvemdotrans ";

    vcMdoTrans = dDinRep.findByAll(cSQL);

    for(int j = 0;j<vcMdoTrans.size();j++){
       iTmp1 = 0; 
       iTmp2 = 0;

       vRecord = new TVDinRep();
       vRecord.put("1",((TVDinRep)vcMdoTrans.get(j)).getString("CDSCMDOTRANS"));
       vRecord.put("2","");
       vcRecords.add(vRecord);

       cSQL =
//       "select icvemdotrans, count(ldictamen) as iAptos "+ 
//       "select  icvemdotrans, count(distinct iCvePersonal) as iAptos "+
       "select  icvemdotrans, count(iCvePersonal) as iAptos "+
       "from expexamcat  "+
       "join expexamaplica on expexamcat.icveexpediente = expexamaplica.icveexpediente "+
       "and expexamcat.inumexamen = expexamaplica.inumexamen "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       " and expexamaplica.icveproceso = " + vParametros.getPropEspecifica("EPIProceso") + " " +
       cRep+
       " where icvemdotrans = "+ ((TVDinRep) vcMdoTrans.get(j)).getInt("ICVEMDOTRANS") +
       " and ldictamen = 1 " +
       " and expexamaplica.icveexpediente > 1  "+                      
       "group by icvemdotrans  ";

            System.out.println("iAptos"+cSQL);
       vcTmp = dDinRep.findByAll(cSQL);
       try{
          iTmp1 = ((TVDinRep) vcTmp.get(0)).getInt("IAPTOS");
       }catch(Exception e){
          iTmp1 = 0;
       }
       
       vRecord = new TVDinRep();
       vRecord.put("1","Aptos");
       vRecord.put("2",iTmp1);
       vcRecords.add(vRecord);

       cSQL =
//       "select icvemdotrans, count(ldictamen) as iNoAptos "+
//       "select  icvemdotrans, count(distinct iCvePersonal) AS iNoAptos "+
       "select  icvemdotrans, count(iCvePersonal) AS iNoAptos "+
       "from expexamcat  "+
       "join expexamaplica on expexamcat.icveexpediente = expexamaplica.icveexpediente "+
       "and expexamcat.inumexamen = expexamaplica.inumexamen "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       " and expexamaplica.icveproceso = " + vParametros.getPropEspecifica("EPIProceso") + " " +
       cRep+
       " where icvemdotrans = "+ ((TVDinRep) vcMdoTrans.get(j)).getInt("ICVEMDOTRANS") +
       " and ldictamen = 0 " +
       " and expexamaplica.icveexpediente > 1  "+                      
       "group by icvemdotrans  ";

             System.out.println("nAptos"+cSQL);
       vcTmp = dDinRep.findByAll(cSQL);
       try{
          iTmp2 = ((TVDinRep) vcTmp.get(0)).getInt("INOAPTOS");
       }catch(Exception e){
          iTmp2 = 0;
       }
       
        
       vRecord = new TVDinRep();
       vRecord.put("1","No Aptos");
       vRecord.put("2",iTmp2);
       vcRecords.add(vRecord);
/*
       cSQL =
         "select  icvemdotrans, count(iCvePersonal) AS iNoAptos "+
         "from expexamcat  "+
         "join expexamaplica on expexamcat.icveexpediente = expexamaplica.icveexpediente "+
         "and expexamcat.inumexamen = expexamaplica.inumexamen "+
         " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
         " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
         " and expexamaplica.icveproceso = " + vParametros.getPropEspecifica("EPIProceso") + " " +
         cRep+
         " where icvemdotrans = "+ ((TVDinRep) vcMdoTrans.get(j)).getInt("ICVEMDOTRANS") +
         " and ldictamen = 0 " +
         " and expexamaplica.icveexpediente > 1  "+                      
         "group by icvemdotrans  ";

               System.out.println("Total"+cSQL);
         vcTmp = dDinRep.findByAll(cSQL);
         try{
            iTmp2 = ((TVDinRep) vcTmp.get(0)).getInt("INOAPTOS");
         }catch(Exception e){
            iTmp2 = 0;
         }       */
       vRecord = new TVDinRep();
       vRecord.put("1","TOTAL");
       vRecord.put("2",iTmp1 + iTmp2);
       vcRecords.add(vRecord);

    }

    TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
    cSQL = "Select count(c.icveexpediente) from expexamaplica as a,  expexamcat as c where "+
           " a.icveexpediente =  c.icveexpediente and "+
    	   " a.inumexamen = c.inumexamen and "+
    	   " a.dtsolicitado = c.dtdictaminado and "+
    	   " a.icveexpediente > 1 "+
    	   " and a.icveunimed = " + request.getParameter("iCveUniMed") +
    	   " and a.icvemodulo = " + request.getParameter("iCveModulo") +
    	   " and a.icveproceso = " + vParametros.getPropEspecifica("EPIProceso") + " " +cRep2;
    System.out.println("Tiempo = "+cSQL);
    TotalGen = dEXPExamAplica.RegresaS(cSQL);
    
    cSQL = "Select count(c.icveexpediente) from expexamaplica as a,  expexamcat as c where "+
    	   " a.icveexpediente =  c.icveexpediente and "+
	   	   " a.inumexamen = c.inumexamen and "+
	       " a.dtsolicitado < c.dtdictaminado and "+
	       " a.icveexpediente > 1 "+
	       " and a.icveunimed = " + request.getParameter("iCveUniMed") +
	       " and a.icvemodulo = " + request.getParameter("iCveModulo") +
	       " and a.icveproceso = " + vParametros.getPropEspecifica("EPIProceso") + " " +cRep2;
    System.out.println("FueraDTiempo = "+cSQL);
    TotalDic = dEXPExamAplica.RegresaS(cSQL);
    
    cSQL = " Select count(icveexpediente) from ("+
    	   " Select c.icveexpediente, c.inumexamen from expexamaplica as a,  expexamcat as c where "+
	   	" a.icveexpediente =  c.icveexpediente and "+
	   	" a.inumexamen = c.inumexamen and "+
    " a.icveexpediente > 1 "+
    " and a.icveunimed = " + request.getParameter("iCveUniMed") +
    " and a.icvemodulo = " + request.getParameter("iCveModulo") +
    " and a.icveproceso = " + vParametros.getPropEspecifica("EPIProceso") + " " +cRep2+
    " group by c.icveexpediente,c.inumexamen ) ";
	System.out.println("Total EPI = "+cSQL);
	TotalExaDic = dEXPExamAplica.RegresaS(cSQL);
    
  }catch(Exception e){
    cError = e.getMessage();
  }

  String cCols = "";
  String cExcel = ""+request.getParameter("hdTipo");
  String titulo = new String(request.getParameter("hdTit").getBytes("ISO-8859-1"),"UTF-8");
  
  //System.out.println(titulo);
  //System.out.println(request.getParameter("hdTit"));
  StringTokenizer stCols = new StringTokenizer(cCols,"|");
  int iCols = stCols.countTokens();

%>
<html>
<SCRIPT LANGUAGE="JavaScript">
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  <%if(!vcRecords.isEmpty() && !cExcel.equals("1")){%>
    window.print();
  <%}%>
</SCRIPT>
<title>
<%//=""+request.getParameter("hdTit")%>
<%=titulo%>
</title>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="">
<form method="POST" action="pgReporteBase.jsp">
  <table width="100%" height="100%">
    <tr>
      <%if(!cExcel.equals("1")){%>
      <td align="center" width="50%" valign="middle">
        <img SRC="<%=vParametros.getPropEspecifica("RutaImgServer")%>LogoRep01.gif">&nbsp;
        <img SRC="<%=vParametros.getPropEspecifica("RutaImgServer")%>LogoRep02.gif">
      </td>
      <%}%>
    </tr>
    <tr>
      <td valign="top">
                          <table border="1" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="<%=iCols%>" class="ERTablaT"><%//=""+request.getParameter("hdTit")%><%=titulo%></td>
                            </tr>
                            <%
                               if (!vcRecords.isEmpty()){
                                  if(cExcel.equals("1")){ // Excel
                                     out.print("<TR><td class='ERCampo' colspan='"+iCols+"'>El reporte ha sido generado a trav&eacute;s de Excel</td></TR>");
                                     TExcel xlsObj = new TExcel("07");
                                     //xlsObj.comDespliega("A",8,request.getParameter("hdTit"));
                                     xlsObj.comDespliega("A",8,titulo);
                                     char   Cel = 'A';
                                     String Celda = "";
                                     while(stCols.hasMoreTokens()){
                                       Celda = Celda.valueOf(Cel);
                                       xlsObj.comDespliega(Celda,10,stCols.nextToken());
                                       Cel = (char)(Cel + 1);
                                     }
                                     TVDinRep vDinRep;
                                     vDinRep = (TVDinRep) vcRecords.get(0);
                                     Vector vcKeys = vDinRep.getVcKeys();
                                     int i=0;
                                     for(i=0; i<vcRecords.size(); i++){
                                       vDinRep = (TVDinRep) vcRecords.get(i);
                                       Cel = 'C';
                                       for(int j=0; j<vcKeys.size(); j++){
                                         Celda = Celda.valueOf(Cel);
                                         xlsObj.comDespliega(Celda,11 + i,""+vDinRep.get((String) vcKeys.get(j)));
                                         xlsObj.comAlineaRangoVer("C", 11, "D", i+10, xlsObj.getAT_VJUSTIFICAR());
                                         Cel = (char)(Cel + 1);
                                       }
                                     }
                                     xlsObj.comBordeTotal("C",11,"D",i+10,1);
                                     //INFORMACION DE EXAMENES//
                                     xlsObj.comDespliega("F",11,"TOTAL DE DICTAMENES");
                                     xlsObj.comDespliega("F",12,"Dictaminados en tiempo");
                                     xlsObj.comDespliega("G",12,""+TotalGen);
                                     xlsObj.comDespliega("F",13,"Dictaminados fuera de tiempo");
                                     xlsObj.comDespliega("G",13,""+TotalDic);
                                     xlsObj.comDespliega("F",16,"TOTAL DE EXAMENES PSICOFISICOS INTEGRALES");
                                     xlsObj.comDespliega("F",17,"TOTAL");
                                     xlsObj.comDespliega("G",17,""+TotalExaDic);
                                     xlsObj.comBordeTotal("F",11,"G",13,1);
                                     xlsObj.comBordeTotal("F",16,"G",17,1);
                                     xlsObj.comAlineaRangoVer("F", 12, "G", 13, xlsObj.getAT_VJUSTIFICAR());
                                     xlsObj.comAlineaRangoVer("F", 17, "G", 17, xlsObj.getAT_VJUSTIFICAR());
                                     out.print(xlsObj.creaActiveX("pg070106020R","",false,false,true));
                                  }
                               }
                               else{
                                 if(cError.equals(""))
                                   out.print("<td class='ERCampo' colspan='"+iCols+"'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                 else
                                   out.print("<td class='ERCampo' colspan='"+iCols+"'>ERROR AL EJECUTAR EL REPORTE: "+cError+"</td>");
                               }
                            %>
                          </table>
  </td></tr>
 </table>
</form>
</body>
<%=vErrores.muestraError()%>
</html>
