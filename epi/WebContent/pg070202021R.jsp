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
  Vector vcDictamenNeg = new Vector();
  Vector vcDictamenPos = new Vector();
  Vector vcRecords = new Vector();
  Vector vcDiagnostico = new Vector();
  Vector vcDiags = new Vector();

  String cRep = "";
  int iAptos = 0, iNoAptos = 0, i = 0;
  HashMap hmDiag = new HashMap();
  HashMap hmNumDiag = new HashMap();
  try{
    TDDinRep dDinRep = new TDDinRep();

    cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + request.getParameter("iAnio") +
            " and {FN MONTH(expexamaplica.dtsolicitado)} = " + request.getParameter("iMeses");

    String cSQL =
       "select count(ldictamen) as iNoAptos "+
       "from emoexamen "+
       "join expexamaplica on emoexamen.icveexpediente = expexamaplica.icveexpediente "+
       "and emoexamen.inumexamen = expexamaplica.inumexamen "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       " and expexamaplica.icvenumempresa = " + request.getParameter("iCveEmpresa") +
       cRep +
       " join expexamcat on emoexamen.icveexpediente = expexamcat.icveexpediente "+
       "and emoexamen.inumexamen = expexamcat.inumexamen "+
       "where ldictamen = 0 ";

    vcDictamenNeg = dDinRep.findByAll(cSQL);
    if(vcDictamenNeg != null && vcDictamenNeg.size() > 0){
      iNoAptos = ((TVDinRep) vcDictamenNeg.get(0)).getInt("INOAPTOS");
    }

    cSQL =
       "select count(ldictamen) as iAptos  "+
       "from emoexamen "+
       "join expexamaplica on emoexamen.icveexpediente = expexamaplica.icveexpediente "+
       "and emoexamen.inumexamen = expexamaplica.inumexamen "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       " and expexamaplica.icvenumempresa = " + request.getParameter("iCveEmpresa") +
       cRep +
       " join expexamcat on emoexamen.icveexpediente = expexamcat.icveexpediente "+
       "and emoexamen.inumexamen = expexamcat.inumexamen "+
       "where ldictamen = 1 ";

    vcDictamenPos = dDinRep.findByAll(cSQL);
    if(vcDictamenPos != null && vcDictamenPos.size() > 0)
      iAptos = ((TVDinRep) vcDictamenPos.get(0)).getInt("IAPTOS");

    String cEmpresa = " and grlempresas.icveempresa = " + request.getParameter("iCveEmpresa") ;
    if(request.getParameter("cEmpresa").toString().equals("*"))
      cEmpresa = "";


    cSQL =
       "select {FN DAYOFMONTH(expexamaplica.dtsolicitado)} as iDia, emoexamen.icveexpediente, emoexamen.inumexamen, (perdatos.crfc || perdatos.chomoclave) as cRFC,"+
       "(perdatos.cnombre || ' ' || perdatos.cappaterno || ' ' || perdatos.capmaterno) as cnombre, "+
       "(cIDDGPMPT || ' - ' || grlempresas.cnombrers || ' ' || grlempresas.cappaterno || ' ' || grlempresas.capmaterno) as cdscempresa,  "+
       "'' as cDiag, (segusuario.cnombre || ' ' || segusuario.cappaterno || ' ' || segusuario.capmaterno) as cusuario "+
       "from emoexamen  "+
       "join expexamaplica on emoexamen.icveexpediente = expexamaplica.icveexpediente "+
       "and emoexamen.inumexamen = expexamaplica.inumexamen  "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       cRep +
       " join expexamcat on emoexamen.icveexpediente = expexamcat.icveexpediente "+
       " and emoexamen.inumexamen = expexamcat.inumexamen  "+
       " join perdatos on emoexamen.icveexpediente = perdatos.icveexpediente "+
       " join grlempresas on expexamaplica.icvenumempresa = grlempresas.icveempresa "+
       cEmpresa +
       " join segusuario on expexamaplica.icveususolicita = segusuario.icveusuario "+
       " where ldictamen = 0 " +
       " order by expexamaplica.dtsolicitado, emoexamen.icveexpediente, emoexamen.inumexamen ";

    vcDiagnostico = dDinRep.findByAll(cSQL);

    String cDiags = "";
    TVDinRep vDinRep, vDinDiag;
    for(i=0;i<vcDiagnostico.size();i++){
       vDinRep = (TVDinRep) vcDiagnostico.get(i);

       cSQL =
       " select ccie, cdscbreve "+
       " from expdiagnostico "+
       " join meddiagnostico on expdiagnostico.icveespecialidad = meddiagnostico.icveespecialidad  "+
       " and  expdiagnostico.icvediagnostico = meddiagnostico.icvediagnostico  "+
       " where icveexpediente = "+vDinRep.get("ICVEEXPEDIENTE")+
       " and   inumexamen = "+vDinRep.get("INUMEXAMEN");

       cDiags = "";
       vcDiags = dDinRep.findByAll(cSQL);
       int iNum = 0;
       for(int j=0;j<vcDiags.size();j++){
          vDinDiag = (TVDinRep) vcDiags.get(j);
          cDiags = cDiags + vDinDiag.get("CCIE") + ", ";
          hmDiag.put(vDinDiag.get("CCIE"),vDinDiag.get("CDSCBREVE"));
          iNum = 1;
          if(hmNumDiag.containsKey(vDinDiag.get("CCIE"))){
            iNum = Integer.parseInt(hmNumDiag.get(vDinDiag.get("CCIE")).toString()) + 1;
          }
          hmNumDiag.put(vDinDiag.get("CCIE"),""+iNum);
       }
       vDinRep.put("CDIAG",cDiags);
       vcRecords.add(vDinRep);
    }

  }catch(Exception e){
    e.printStackTrace();
    cError = e.getMessage();
  }

  String cExcel = ""+request.getParameter("hdTipo");

%>
<html>
<SCRIPT LANGUAGE="JavaScript">
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  <%if(!vcDictamenPos.isEmpty() && !cExcel.equals("1")){%>
    window.print();
  <%}%>
</SCRIPT>
<title>
Notificación Informe Estadístico
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
                              <td class="ERTablaT">Notificación Informe Estadístico</td>
                            </tr>
                            <%
                               if (!vcDictamenPos.isEmpty() || !vcDictamenNeg.isEmpty()){
                                  if(cExcel.equals("1")){ // Excel
                                     out.print("<TR><td class='ERCampo'>El reporte ha sido generado a través de Excel</td></TR>");
                                     TExcel xlsObj = new TExcel("07");
                                     xlsObj.comDespliega("C",11,""+request.getParameter("cUniMed") + " - " + request.getParameter("cModulo"));
                                     xlsObj.comDespliega("A",7,""+request.getParameter("cMeses") + " de " + request.getParameter("iAnio"));
                                     try{
                                       TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                       xlsObj.comDespliega("F",11,"Dr(a). " + vUsuario.getCNombre() + " " + vUsuario.getCApPaterno() + " " + vUsuario.getCApMaterno());
                                     }catch(Exception e){
                                       e.printStackTrace();
                                       }
                                     char   Cel = 'A', Cel2 = ' ';
                                     String Celda = "";
                                     TVDinRep vDinRep;
                                     Vector vcKeys = new Vector();
                                     if(! vcRecords.isEmpty()){
                                        vDinRep = (TVDinRep) vcRecords.get(0);
                                        vcKeys = vDinRep.getVcKeys();
                                     }
                                     String cDato = "";
                                     for(i=0; i<vcRecords.size(); i++){
                                       vDinRep = (TVDinRep) vcRecords.get(i);
                                       Cel = 'A'; Cel2 = ' ';
                                       for(int j=0; j<vcKeys.size(); j++){
                                         Celda = Celda.valueOf(Cel) + (Celda.valueOf(Cel2).equals(" ") == true ? "" : Celda.valueOf(Cel2));
                                         cDato = ""+vDinRep.get((String) vcKeys.get(j));
                                         if(cDato.equalsIgnoreCase("null"))
                                            cDato = "";
                                         xlsObj.comDespliega(Celda,17 + i,cDato);
                                         if(Cel2 == 'Z' || Cel2 == ' ')
                                           Cel = (char)(Cel + 1);
                                         if(Cel2 != ' ')
                                           Cel2 = (char)(Cel2 + 1);
                                         if(Cel > 90){
                                           Cel = 'A';
                                           if(Cel2 == ' ')
                                             Cel2 = 'A';
                                         }
                                         if(Cel2 > 90)
                                           Cel2 = 'A';
                                       }
                                     }
                                     Cel = (char) (Cel - 2);
                                     xlsObj.comBordeTotal("A",17, Celda.valueOf(Cel),17+i-1,1);
                                     String cLlave = "";

                                     i += 20;
                                     int g = i, iTotal = 0;
                                     if(!hmDiag.isEmpty()){
                                           xlsObj.comDespliega("E",i,"CLAVE CIE");
                                           xlsObj.comDespliega("F",i,"DESCRIPCiÓN");
                                           xlsObj.comDespliega("G",i,"CANTIDAD");
                                           xlsObj.comDespliega("H",i,"PORCENTAJE");
                                           xlsObj.comFontBold("E",i,"H",i);

                                        Iterator iDiag = hmDiag.keySet().iterator();

                                        while(iDiag.hasNext()){
                                           cLlave = iDiag.next().toString();
                                           xlsObj.comDespliega("E",++i,cLlave);
                                           xlsObj.comDespliega("F",i,hmDiag.get(cLlave).toString());
                                           xlsObj.comDespliega("G",i,hmNumDiag.get(cLlave).toString());
                                           iTotal += Integer.parseInt(hmNumDiag.get(cLlave).toString());
                                        }
                                        xlsObj.comFontBold("E",++i,"H",i);
                                        xlsObj.comDespliega("F",i,"TOTAL");
                                        xlsObj.comCreaFormula("G",i,"=SUM(G"+(g+1)+":G"+(i-1)+")");
                                        xlsObj.comCreaFormula("H",i,"=SUM(H"+(g+1)+":H"+(i-1)+")");

                                        xlsObj.comBordeTotal("E",g,"H",i+1,1);
                                        while(g<i-1){
                                           g+=1;
                                           xlsObj.comDespliega("H",g,"=G"+g+"/"+iTotal);
                                        }

                                     }

                                     out.print(xlsObj.creaActiveX("pg070202020b","",false,false,true));
                                  }
                               }
                               else{
                                 if(cError.equals(""))
                                   out.print("<td class='ERCampo' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                 else
                                   out.print("<td class='ERCampo' colspan='6'>ERROR AL EJECUTAR EL REPORTE: "+cError+"</td>");
                               }
                            %>
                          </table>
  </td></tr>
 </table>
</form>
</body>
<%=vErrores.muestraError()%>
</html>
