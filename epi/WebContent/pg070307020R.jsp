<%/**vcTemperatura
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
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="java.util.*" %>
<%

  TParametro   vParametros   = new TParametro("07");
  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  String cError = "";

  TEtiCampo    vEti          = new TEtiCampo();
  Vector vcRecords = new Vector();
  Vector vcPrevios = new Vector();

  String cRep = ""+request.getParameter("hdRep");
  String cCond = ""+request.getParameter("hdQuery");
  String cMes = "";
  String cAnio = "";
  String cArea = "";
  String cNomArchivo = "RepTemperatura_";
  TFechas dtFecha = new TFechas("07");

  try{
    if(cRep.equals("2")){
       StringTokenizer stAnioMes = new StringTokenizer(cCond,"|");
       cAnio = stAnioMes.nextToken();
       cRep = " where TOXTurnoRef.iAnio = " + cAnio;
       cMes = "" + stAnioMes.nextToken();
       cRep = cRep + " and TOXTurnoRef.iMes = " + cMes ;
       cMes = "1/" + cMes + "/"+ cAnio;
       cMes = dtFecha.getMonthName(dtFecha.getDateSQL(cMes));
       cArea = stAnioMes.nextToken();
       cNomArchivo += "-" + cAnio + cMes;
    }

    String cSQL =
       "select TOXTemperRefr.iAnio,TOXTemperRefr.iCveTurnoRef,TOXTemperRefr.iCveRefrigerador ,TOXTemperRefr.DTemperatura, TOXTurnoValida.iCveTurnoValida,TOXRefrigerador.iCveArea, TOXTurnoRef.iDia, TOXTemperRefr.iCveRefrigerador, " +
       "TOXTurnoValida.cDscBreve, " +
       " E.cCveEquipo " +
       "from TOXTurnoRef " +
       "inner join TOXTemperRefr on TOXTemperRefr.iAnio = TOXTurnoRef.iAnio " +
       "and TOXTemperRefr.iCveTurnoRef = TOXTurnoRef.iCveTurnoRef " +
       "inner join TOXTurnoValida on TOXTurnoValida.iCveTurnoValida = TOXTurnoRef.iCveTurnoValida " +
       "inner join TOXRefrigerador on TOXRefrigerador.iCveRefrigerador = TOXTemperRefr.iCveRefrigerador " +
       "and TOXRefrigerador.iCveArea = " + cArea  +
       " inner join EQMEquipo E on E.iCveEquipo = TOXRefrigerador.iCveEquipo  " +
       cRep +
       " order by TOXTemperRefr.iCveRefrigerador, TOXTurnoValida.iCveTurnoValida, TOXTurnoRef.iDia ";

//  //        System.out.println("Quey Refris: " + cSQL);
    TDDinRep dDinRep = new TDDinRep();
    vcRecords = dDinRep.findByAll(cSQL);

  }catch(Exception e){
    cError = e.getMessage();
  }

  String cCols = ""+request.getParameter("hdCols");
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
                                     //xlsObj.comDespliega("A",8,request.getParameter("hdTit"));
                                     char   Cel = 'A';
                                     String Celda = "";
                                     while(stCols.hasMoreTokens()){
                                       Celda = Celda.valueOf(Cel);
                                       xlsObj.comDespliega(Celda,10,stCols.nextToken());
                                       Cel = (char)(Cel + 1);
                                     }

                                      TVDinRep vDinRep;
                                     TVDinRep vDinRepA;
                                     vDinRep = (TVDinRep) vcRecords.get(0);
                                     vDinRepA = (TVDinRep) vcRecords.get(0);
                                     Vector vcKeys = vDinRep.getVcKeys();
                                     int iAct = -1, iAnt = -1, iRen = -1;
                                     int iRenglon = 12;
                                     int iRInicio = iRenglon;
                                     int iRfinal  = iRenglon;
                                     Cel = 'A';

                                     char CelDia = 'F';
                                     char CelDiaX = 'A';
                                     String CeldaDia = "";
                                     String CeldaDiaX = "";
                                     int iDiferencia = 0;
                                     CeldaDia = CeldaDia.valueOf(CelDia);

                                     xlsObj.comDespliega("AH",8,cMes);
                                     int iRefri  = Integer.parseInt("" + vDinRep.get("ICVEREFRIGERADOR"));
                                     int iRefriAnt = Integer.parseInt("" + vDinRep.get("ICVEREFRIGERADOR"));

                                     int iPrimRef = 0;
                                     boolean lPresRef = true;
                                     int iRenglonI = iRenglon;

                                     int iTurno  = Integer.parseInt("" + vDinRep.get("ICVETURNOVALIDA"));
                                     int iTurnoAnt = Integer.parseInt("" + vDinRep.get("ICVETURNOVALIDA"));


                                     // Despliegue de Temperaturas
                                     for(int i=0; i<vcRecords.size(); i++){
                                       vDinRep = (TVDinRep) vcRecords.get(i);
                                       if(i==0)
                                         iPrimRef = iRefri = Integer.parseInt("" + vDinRep.get("ICVEREFRIGERADOR"));
                                       iRefri = Integer.parseInt("" + vDinRep.get("ICVEREFRIGERADOR"));
                                       iTurno  = Integer.parseInt("" + vDinRep.get("ICVETURNOVALIDA"));
                                       if (iRefriAnt != iRefri && lPresRef){
                                           xlsObj.comBordeTotal("G",iRenglon,"AK",iRenglon,1);
                                           xlsObj.comDespliega("C",iRenglon,"" + vDinRepA.get("CCVEEQUIPO"));
                                           xlsObj.comAlineaRango("C", iRInicio, "D", iRenglon, xlsObj.getAT_COMBINA_CENTRO());
                                            xlsObj.comBordeTotal("C",iRInicio,"D",iRenglon,1);
                                           iRenglon = iRenglon + 3;
                                           iRInicio = iRenglon;
                                           vDinRepA = (TVDinRep) vcRecords.get(i);
                                       }
                                       else{
                                         if(iTurno != iTurnoAnt){
                                            xlsObj.comBordeTotal("G",iRenglon,"AK",iRenglon,1);
                                            iRenglon = iRenglon + 1;
                                         }
                                       }

                                       iRefriAnt = Integer.parseInt("" + vDinRep.get("ICVEREFRIGERADOR"));
                                       iTurnoAnt = Integer.parseInt("" + vDinRep.get("ICVETURNOVALIDA"));
                                       xlsObj.comDespliega("E",iRenglon,"" + vDinRep.get("CDSCBREVE"));
                                       xlsObj.comAlineaRango("E", iRenglon, "F", iRenglon, xlsObj.getAT_COMBINA_CENTRO());
                                       xlsObj.comBordeTotal("E",iRenglon,"F",iRenglon,1);

                                       CelDia = 'F';
                                       CelDia = (char) (CelDia + Integer.parseInt("" + vDinRep.get("IDIA")));
                                       CeldaDia = CeldaDia.valueOf(CelDia);
                                       iDiferencia = 0;
                                       CelDiaX = 'A';
                                       if(Integer.parseInt("" + vDinRep.get("IDIA")) > 20){
                                         iDiferencia = (Integer.parseInt("" + vDinRep.get("IDIA")) - 21);
                                         CelDiaX = (char) (CelDiaX + iDiferencia);
                                         CeldaDia = "A" + CelDiaX;
                                       }
                                       String cWhere = " where iAnio = " + vDinRep.get("IANIO") + " and iCveTurnoRef = " + vDinRep.get("ICVETURNOREF") + " and iCveRefrigerador = " + vDinRep.get("ICVEREFRIGERADOR");
                                       TDTOXTemperRefr dTDTOXTemperRefr = new TDTOXTemperRefr();
                                       TVTOXTemperRefr vTDTOXTemperRefr = new TVTOXTemperRefr();
                                       Vector vcTemperatura = new Vector();
                                       vcTemperatura = dTDTOXTemperRefr.FindByAll(cWhere);
                                       if (vcTemperatura.size() > 0)  {
                                          vTDTOXTemperRefr  = (TVTOXTemperRefr) vcTemperatura.get(0);
                                        }
                                       xlsObj.comDespliega(CeldaDia,iRenglon,"" + vTDTOXTemperRefr.getDTemperatura());
                                  }
                                     xlsObj.comDespliega("C",iRenglon,"" + vDinRep.get("CCVEEQUIPO"));
                                     xlsObj.comAlineaRango("C", iRInicio, "D", iRenglon, xlsObj.getAT_COMBINA_CENTRO());
                                     xlsObj.comBordeTotal("C",iRInicio,"D",iRenglon,1);
                                     xlsObj.comBordeTotal("G",iRenglon,"AK",iRenglon,1);

                                     TDTOXTurnoValida dTOXTurnoValida = new TDTOXTurnoValida();
                                     TVTOXTurnoValida vTOXTurnoValida = new TVTOXTurnoValida();
                                     Vector vcResponsables = new Vector();
                                     vcResponsables = dTOXTurnoValida.FindByAll3(" where TOXTurnoValida.iCveArea = "  + cArea,"" );
                                     CelDia = 'I';
                                      CeldaDia = CeldaDia.valueOf(CelDia);
                                      if(vcResponsables.size() > 0){
                                      for(int i = 0; i<vcResponsables.size(); i++ ){

                                        vTOXTurnoValida = (TVTOXTurnoValida) vcResponsables.get(i);
                                        xlsObj.comDespliega(CeldaDia,49,"" + vTOXTurnoValida.getCDscUsuResponsable());
                                        xlsObj.comDespliega(CeldaDia,46,"" + vTOXTurnoValida.getCDscBreve());
                                        //System.out.println("prueba: " + vTOXTurnoValida.getCDscUsuResponsable() );
                                        CelDiaX = 'A';
                                        if(i > 1){
                                           //iDiferencia = (Integer.parseInt("" + vDinRep.get("IDIA")) - 21);
                                           //CelDiaX = (char) (CelDiaX + iDiferencia);
                                           CeldaDia = "AC";
                                        }
                                        CelDia = (char) (CelDia + 10);
                                        CeldaDia = CeldaDia.valueOf(CelDia);

                                       }

                                      }
                                     out.print(xlsObj.creaActiveX("pg070307020",cNomArchivo,false,false,true));

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
