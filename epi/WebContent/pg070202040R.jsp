<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Jaime Enrique Suàrez Romero
 * @version
 * Clase:
 */%>
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
  Vector vcTmp = new Vector();
  Vector vcMdoTrans = new Vector();

  TVDinRep vRecord;
  String cRep = ""+request.getParameter("hdRep");
  String cCond = ""+request.getParameter("hdQuery");
  int iTmp1 = 0, iTmp2 = 0;

  try{

    TDDinRep dDinRep = new TDDinRep();

    if(cRep.equals("1"))
       //cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + cCond;
        cRep =  " and {FN YEAR(expexamcat.dtiniciovig)} = " + cCond;

    if(cRep.equals("2")){
       StringTokenizer stAnioMes = new StringTokenizer(cCond,"|");
 /*      cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + stAnioMes.nextToken();
       cRep =  cRep + " and {FN MONTH(expexamaplica.dtsolicitado)} = " + stAnioMes.nextToken();*/

       cRep =  " and {FN YEAR(expexamcat.dtiniciovig)} = " + stAnioMes.nextToken();
       cRep =  cRep + " and {FN MONTH(expexamcat.dtiniciovig)} = " + stAnioMes.nextToken();       
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
       
     /*  cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + anio;
       
       cRep =  cRep + " and {FN MONTH(expexamaplica.dtsolicitado)} = " + mes;
       
       cRep =  cRep + " and {FN DAY(expexamaplica.dtsolicitado)} = " + dia;*/
       
       cRep =  " and {FN YEAR(expexamcat.dtiniciovig)} = " + anio;
       
       cRep =  cRep + " and {FN MONTH(expexamcat.dtiniciovig)} = " + mes;
       
       cRep =  cRep + " and {FN DAY(expexamcat.dtiniciovig)} = " + dia;
       
        
       }

    String cSQL =
       "select icvemdotrans, cdscmdotrans "+
       "from grlmdotrans "+
       "order by icvemdotrans ";

    vcMdoTrans = dDinRep.findByAll(cSQL);

    for(int j = 0;j<vcMdoTrans.size();j++){
       iTmp1 = 0;
       iTmp2 = 0;

       cSQL =
       "select  "+
       "cdscmdotrans,  "+
       "cdscpuesto,  "+
       "cdsccategoria, "+
//       "'Aptos: ', count(ldictamen) as iAptos  "+
       "'Aptos: ', count(distinct iCvePersonal) as iAptos  "+
       "from expexamcat   "+
       "join expexamaplica on expexamcat.icveexpediente = expexamaplica.icveexpediente  "+
       "and expexamcat.inumexamen = expexamaplica.inumexamen   "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       " and expexamaplica.icveproceso = " + vParametros.getPropEspecifica("EMOProceso") + " " +
       cRep+
       " join expexampuesto on expexamcat.icveexpediente = expexampuesto.icveexpediente  "+
       " and  expexamcat.inumexamen = expexampuesto.inumexamen "+
       " and  expexamcat.icvemdotrans = expexampuesto.icvemdotrans "+
       " join grlpuesto on expexampuesto.icvemdotrans = grlpuesto.icvemdotrans "+
       " and  expexampuesto.icvepuesto = grlpuesto.icvepuesto "+
       " and  expexamcat.icvecategoria = grlpuesto.icvecategoria "+
       " join grlmdotrans on expexamcat.icvemdotrans = grlmdotrans.icvemdotrans "+
       " join grlcategoria on expexamcat.icvemdotrans = grlcategoria.icvemdotrans "+
       " and  expexamcat.icvecategoria = grlcategoria.icvecategoria "+
       " where expexamcat.icvemdotrans = "+ ((TVDinRep) vcMdoTrans.get(j)).getInt("ICVEMDOTRANS") +
       " and ldictamen = 1  "+
       " and expexamaplica.icveexpediente > 1  "+                      
       " group by cdscmdotrans, cdscpuesto, cdsccategoria ";


       //System.out.print(cSQL);

       vcTmp = dDinRep.findByAll(cSQL);

       vcRecords.addAll(vcTmp);

       cSQL =
       "select  "+
       "cdscmdotrans,  "+
       "cdscpuesto,  "+
       "cdsccategoria, "+
//       "'No Aptos: ', count(ldictamen) as iAptos  "+
       "'No Aptos: ', count(distinct iCvePersonal) as iAptos  "+
       "from expexamcat   "+
       "join expexamaplica on expexamcat.icveexpediente = expexamaplica.icveexpediente  "+
       "and expexamcat.inumexamen = expexamaplica.inumexamen   "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       " and expexamaplica.icveproceso = " + vParametros.getPropEspecifica("EMOProceso") + " " +
       cRep+
       " join expexampuesto on expexamcat.icveexpediente = expexampuesto.icveexpediente  "+
       " and  expexamcat.inumexamen = expexampuesto.inumexamen "+
       " and  expexamcat.icvemdotrans = expexampuesto.icvemdotrans "+
       " join grlpuesto on expexampuesto.icvemdotrans = grlpuesto.icvemdotrans "+
       " and  expexampuesto.icvepuesto = grlpuesto.icvepuesto "+
       " and  expexamcat.icvecategoria = grlpuesto.icvecategoria "+
       " join grlmdotrans on expexamcat.icvemdotrans = grlmdotrans.icvemdotrans "+
       " join grlcategoria on expexamcat.icvemdotrans = grlcategoria.icvemdotrans "+
       " and  expexamcat.icvecategoria = grlcategoria.icvecategoria "+
       " where expexamcat.icvemdotrans = "+ ((TVDinRep) vcMdoTrans.get(j)).getInt("ICVEMDOTRANS") +
       " and ldictamen = 0  "+
       " and expexamaplica.icveexpediente > 1  "+       
       " group by cdscmdotrans, cdscpuesto, cdsccategoria ";

       vcTmp = dDinRep.findByAll(cSQL);

       vcRecords.addAll(vcTmp);

        //System.out.print(cSQL);

    }
  }catch(Exception e){
    cError = e.getMessage();
  }

  String cCols = "";
  String cExcel = ""+request.getParameter("hdTipo");

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
<%=""+request.getParameter("hdTit")%>
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
                              <td colspan="<%=iCols%>" class="ERTablaT"><%=""+request.getParameter("hdTit")%></td>
                            </tr>
                            <%
                               if (!vcRecords.isEmpty()){
                                  if(cExcel.equals("1")){ // Excel
                                     out.print("<TR><td class='ERCampo' colspan='"+iCols+"'>El reporte ha sido generado a través de Excel</td></TR>");
                                     TExcel xlsObj = new TExcel("07");
                                     xlsObj.comDespliega("A",8,request.getParameter("hdTit"));
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
                                       Cel = 'B';
                                       for(int j=0; j<vcKeys.size(); j++){
                                         Celda = Celda.valueOf(Cel);
                                         xlsObj.comDespliega(Celda,11 + i,""+vDinRep.get((String) vcKeys.get(j)));
                                         Cel = (char)(Cel + 1);
                                       }
                                     }
                                     xlsObj.comBordeTotal("B",11,"F",i+10,1);
                                     out.print(xlsObj.creaActiveX("pg070202040R","",false,false,true));
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
