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
<%@ page import="gob.sct.medprev.vo.*" %>
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

  String cUniMed = "" + request.getParameter("iCveUniMed");
  String cLote = "" + request.getParameter("cLote");
  String cFIni = "" + request.getParameter("dtFechaI");
  String cFFin = "" + request.getParameter("dtFechaF");
  String clFecha = "" + request.getParameter("lfecha");

  int iTmp1 = 0, iTmp2 = 0;

  try{

    TDDinRep dDinRep = new TDDinRep();

     /*
    if(cRep.equals("1"))
       cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + cCond;

    if(cRep.equals("2")){
       StringTokenizer stAnioMes = new StringTokenizer(cCond,"|");
       cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + stAnioMes.nextToken();
       cRep =  cRep + " and {FN MONTH(expexamaplica.dtsolicitado)} = " + stAnioMes.nextToken();
    }

    if(cRep.equals("3"))
       cRep =  " and expexamaplica.dtsolicitado = '" + cCond + "'";

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
       "'Aptos: ', count(ldictamen) as iAptos  "+
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
       " group by cdscmdotrans, cdscpuesto, cdsccategoria ";


System.out.print(cSQL);

       vcTmp = dDinRep.findByAll(cSQL);

       vcRecords.addAll(vcTmp);
*/
       String cSQL;
       cSQL =
            "select TOXCtrolCalibra.cLote, TOXExamenCualita.dtProcesado, " +
                    "TOXAnalisis.iAnio, TOXAnalisis.iCveAnalisis, " +
                    "TOXCortexSust.dCorte, TOXExamResult.dResultado " +
                    "from TOXAnalisis " +
                    "inner join TOXCtrolCalibra on " +
                    "TOXCtrolCalibra.iCveLaboratorio = TOXAnalisis.iCveLaboratorio and " +
                    "TOXCtrolCalibra.iCveCtrolCalibra = TOXAnalisis.iCveCtrolCalibra and " +
                    "TOXCtrolCalibra.cLote = '"+ cLote + "' " +
                    "inner join TOXExamResult on " +
                    "TOXExamResult.iAnio = TOXAnalisis.iAnio and " +
                    "TOXExamResult.iCveLaboratorio = TOXAnalisis.iCveLaboratorio and " +
                    "TOXExamResult.iCveLoteCualita = TOXAnalisis.iCveLoteCualita and " +
                    "TOXExamResult.iCveAnalisis = TOXAnalisis.iCveAnalisis and " +
                    "TOXExamResult.iCvesustancia = TOXCtrolCalibra.iCveSustancia " +
                    "inner join TOXExamenCualita on " +
                    "TOXExamenCualita.iAnio = TOXExamResult.iAnio and " +
                    "TOXExamenCualita.iCveLaboratorio = TOXExamResult.iCveLaboratorio and " +
                    "TOXExamenCualita.iCveLoteCualita = TOXExamResult.iCveLoteCualita and " +
                    "TOXExamenCualita.iCveExamCualita = TOXExamResult.iCveExamCualita " ;

         if (clFecha.compareTo("true") == 0){
             cSQL = cSQL + "and TOXExamenCualita.dtProcesado >= '" + cFIni + "' and " +
                           "TOXExamenCualita.dtProcesado <= '" + cFFin + "' ";
             //System.out.println("Fechas: " + "and TOXExamenCualita.dtProcesado >= '" + cFIni + "' and " +
             //              "TOXExamenCualita.dtProcesado <= '" + cFFin + "' ");
          }



       cSQL = cSQL +"inner join TOXCualtSust on " +
                    "TOXCualtSust.iAnio = TOXExamResult.iAnio and " +
                    "TOXCualtSust.iCveLoteCualita = TOXExamResult.iCveLoteCualita and " +
                    "TOXCualtSust.iCveExamCualita = TOXExamResult.iCveExamCualita and " +
                    "TOXCualtSust.iCveLaboratorio = TOXExamResult.iCveLaboratorio and " +
                    "TOXCualtSust.iCveSustancia =  TOXExamResult.iCveSustancia " +
                    "inner join TOXCortexSust on " +
                    "TOXCortexSust.iCveSustancia = TOXCualtSust.iCveSustancia and " +
                    "TOXCortexSust.iCveCorte = TOXCualtSust.iCveCorte ";


       vcTmp = dDinRep.findByAll(cSQL);

       vcRecords.addAll(vcTmp);
       //vcRecords = null;


    //}
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
                                TFechas dtFecha = new TFechas();
                                String cFecha = "";
                                java.sql.Date dtTemporal;
                                int iRenglones = 5;
                                int iTope = 0;
                                int iBandera = 0;

                                TVTOXAnalisis vTOXAnalisis = new TVTOXAnalisis();
                                if (!vcRecords.isEmpty()){
                                  if(cExcel.equals("1")){ // Excel
                                     out.print("<TR><td class='ERCampo' colspan='"+iCols+"'>El reporte ha sido generado a través de Excel</td></TR>");
                                     TExcel xlsObj = new TExcel("07");
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

                                     xlsObj.comDespliega("B",11,""+vDinRep.get((String) vcKeys.get(0)));

                                    if (iRenglones > vcRecords.size())
                                       iRenglones = vcRecords.size();

                                    if (vcRecords.size() > iRenglones){
                                        iTope = iRenglones;
                                        iBandera = 1;

                                    }
                                    else{
                                        iTope = vcRecords.size();
                                       }
                                     //System.out.print("Topes: " + iTope + " R:" +iRenglones + " Size: " + vcRecords.size());
                                     for(i=0; i<iTope; i++){
                                       vDinRep = (TVDinRep) vcRecords.get(i);
                                       Cel = 'D';

                                       for(int j=1; j<vcKeys.size(); j++){
                                         Celda = Celda.valueOf(Cel);

                                        switch (j){

                                         case 1:
                                           java.sql.Date dtTemp;
                                           cFecha = "" + vDinRep.get((String) vcKeys.get(j));
                                            if (cFecha.compareTo("null") == 0)
                                             cFecha = "";
                                            else{
                                             dtTemp = dtFecha.getSQLDatefromSQLString(cFecha);
                                             cFecha= "" + dtFecha.getFechaDDMMYYYY(dtTemp,"/");
                                            }


                                            xlsObj.comDespliega(Celda,11 + i,""+ cFecha);

                                           break;
                                           case 2:
                                                 vTOXAnalisis.setiAnio((Integer) vDinRep.get((String) vcKeys.get(j)));
                                                 vTOXAnalisis.setiCveAnalisis((Integer) vDinRep.get((String) vcKeys.get(j + 1)));
                                                 xlsObj.comDespliega(Celda,11 + i,"" + vTOXAnalisis.getCAnalisis());
                                           break;
                                           case 3:
                                               Cel = (char)(Cel - 1);
                                           break;
                                           default:
                                            xlsObj.comDespliega(Celda,11 + i,""+vDinRep.get((String) vcKeys.get(j)));
                                         }
                                         Cel = (char)(Cel + 1);
                                         xlsObj.comDespliega("H",11 + i,"" + (i+1));
                                       }
                                     }
                                     xlsObj.comBordeTotal("D",11,"G",i+10,1);
                                     //out.print(xlsObj.creaActiveX("pgAsegCalidad","",false,false,true));

                                     if (iBandera == 1){
                                         Celda = "";
                                         int iARenglon = 11 - iTope;
                                         xlsObj.comDespliega("I",11,""+vDinRep.get((String) vcKeys.get(0)));
                                         java.sql.Date dtTemp2;
                                         String cFecha2;
                                        for(i=iTope; i<vcRecords.size(); i++){
                                          vDinRep = (TVDinRep) vcRecords.get(i);
                                          Cel = 'K';
                                          for(int j=1; j<vcKeys.size(); j++){
                                             cFecha2 = "" + vDinRep.get((String) vcKeys.get(1));
                                             dtTemp2 = dtFecha.getSQLDatefromSQLString(cFecha2);
                                             cFecha2 = "" + dtFecha.getFechaDDMMYYYY(dtTemp2,"/");
                                             xlsObj.comDespliega("K",iARenglon + i,""+ cFecha2);
                                             vTOXAnalisis.setiAnio((Integer) vDinRep.get((String) vcKeys.get(2)));
                                             vTOXAnalisis.setiCveAnalisis((Integer) vDinRep.get((String) vcKeys.get(3)));
                                             xlsObj.comDespliega("L",iARenglon + i,""+ vTOXAnalisis.getCAnalisis());
                                             xlsObj.comDespliega("M",iARenglon + i,""+vDinRep.get((String) vcKeys.get(4)));
                                             xlsObj.comDespliega("N",iARenglon + i,""+vDinRep.get((String) vcKeys.get(5)));
                                             xlsObj.comDespliega("O",iARenglon + i,""+ (i + 1));
                                          }
                                        }
                                            // xlsObj.comBordeTotal("K",11,"0",13,1);

                                    /*for(i=iTope; i<vcRecords.size(); i++){
                                       vDinRep = (TVDinRep) vcRecords.get(i);
                                       Cel = 'K';

                                       for(int j=1; j<vcKeys.size(); j++){
                                         Celda = Celda.valueOf(Cel);

                                        switch (j){

                                         case 1:
                                            cFecha = "" + vDinRep.get((String) vcKeys.get(j));
                                            if (cFecha.compareTo("null") == 0)
                                             cFecha = "";

                                            //System.out.println("nFecha: " + vDinRep.get((String) vcKeys.get(j)) + " T: " + cFecha);
                                          //        System.out.println("Salida 1: Celda:" + Celda + (iARenglon + i) + " ** " + j + " --> " +  vDinRep.get((String) vcKeys.get(j)));
                                            xlsObj.comDespliega(Celda, iARenglon + i,""+ cFecha);

                                           break;
                                           case 2:
                                                 vTOXAnalisis.setiAnio((Integer) vDinRep.get((String) vcKeys.get(j)));
                                                 vTOXAnalisis.setiCveAnalisis((Integer) vDinRep.get((String) vcKeys.get(j + 1)));
                                               //        System.out.println("Salida 2: Celda:" + Celda  + (iARenglon + i) + " ** " + j + " --> " +  vTOXAnalisis.getCAnalisis());
                                                 xlsObj.comDespliega(Celda,iARenglon + i,"" + vTOXAnalisis.getCAnalisis());
                                           break;
                                           case 3:
                                               Cel = (char)(Cel - 1);
                                           break;
                                           default:
                                          //        System.out.println("Salida 3: Celda :" + Celda + (iARenglon + i) +  " ** " + j + " --> " +  vDinRep.get((String) vcKeys.get(j)));
                                            xlsObj.comDespliega(Celda,iARenglon  + i,""+vDinRep.get((String) vcKeys.get(j)));
                                         }
                                         Cel = (char)(Cel + 1);
                                         xlsObj.comDespliega("0",iARenglon  + i,"" + (i+1));
                                       }
                                     }*/

                                  }
                                     out.print(xlsObj.creaActiveX("pg070307030","",false,false,true));

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
