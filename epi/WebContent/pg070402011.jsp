<%/**
 * Title: Catálogo de Investigaciones por Unidad Médica
 * Description: JSP para mostrar el Catálogo de Investigaciones por Unidad Médica
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Esteban Viveros
 * @modified Enrique Suarez
*            Javier Mendoza
 * @version 1
 * Clase: ?
 */%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<html>
<%
  pg070402011CFG clsConfig=new pg070402011CFG();                      // modificar
  TEntorno       vEntorno =new TEntorno();
  vEntorno.setNumModulo      (07);
  vEntorno.setNombrePagina   ("pg070402011.jsp");                     // modificar
  vEntorno.setArchTooltips   ("07_Tooltips");
  vEntorno.setOnLoad         ("fOnLoad();");
  vEntorno.setArchFuncs      ("FValida");
  vEntorno.setMetodoForm     ("POST");
  vEntorno.setActionForm     ("pg070402011.jsp\" target=\"FRMCuerpo");// modificar
  vEntorno.setUrlLogo        ("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo  ("FFiltro");
  vEntorno.setArchAyuda      (vEntorno.getNombrePagina());

  //String cCatalogo   ="pg070402011.jsp";                                     // no aplica
  String cOperador   ="1";                                                     // modificar
  String cDscOrdenar ="ID DGPMPT|ID Mdo Transporte|Fecha Accidente|Lugar|";  // modificar
  String cCveOrdenar ="iIDDGPMPT|iIDMdoTrans|dtAccidente|cLugar|";                    // modificar
  String cDscFiltrar ="ID DGPMPT|ID Mdo Transporte|Fecha Accidente|Lugar|";  // modificar
  String cCveFiltrar ="iIDDGPMPT|iIDMdoTrans|dtAccidente|cLugar|";                    // modificar
  String cTipoFiltrar="7|7|5|8";                                                 // modificar
  boolean lFiltros   =true;                                                    // modificar
  boolean lIra       =true;                                                    // modificar
  String cEstatusIR  ="Imprimir";                                              // modificar

  // LLamado al Output Header
  //StringBuffer sbErroresAcum=new StringBuffer();
  TError    vErrores=new TError();
  TEtiCampo vEti    =new TEtiCampo();

  clsConfig.outputHeader(vErrores,pageContext,vEntorno,request);

  BeanScroller bs     =clsConfig.getBeanScroller();
  String cPaginas     =clsConfig.getPaginas();
  String cDscPaginas  =clsConfig.getDscPaginas();
  String cUpdStatus   =clsConfig.getUpdStatus();
//  String cUpdStatus   ="AddOnly";
  String cNavStatus   =clsConfig.getNavStatus();
  String cOtroStatus  =clsConfig.getOtroStatus();
  String cCanWrite    =clsConfig.getCanWrite();
  String cSaveAction  =clsConfig.getSaveAction();
  String cDeleteAction=clsConfig.getDeleteAction();
  String cClave2      ="";
  String cPropiedad   ="";
  int    iFiltro      =0;

  TParametro vParametros=new TParametro(vEntorno.getNumModuloStr());
  //int iNumRowsPrin=Integer.parseInt(vParametros.getPropEspecifica("NumRowsPrin"));
  //int iNumRowsSec =Integer.parseInt(vParametros.getPropEspecifica("NumRowsSec" ));
  //String cRutaImg =vParametros.getPropEspecifica("RutaImg");
  //String cTipoImg =vEntorno.getTiposImg().elementAt(0).toString();
  TFechas dtFecha = new TFechas();
  String dFechaActual = "";
  dFechaActual = dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }

</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<%   /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */
    new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros);
%><link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="FILNumReng" value="<%=request.getParameter("FILNumReng")!=null?request.getParameter("FILNumReng"):vParametros.getPropEspecifica("NumRengTab")%>">
  <input type="hidden" name="hdCCondicion" value="<%=request.getParameter("hdCCondicion")!=null?request.getParameter("hdCCondicion"):""%>">
  <input type="hidden" name="hdCOrdenar" value="<%=request.getParameter("hdCOrdenar")!=null?request.getParameter("hdCOrdenar"):""%>">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?Integer.toString(bs.rowNo()):request.getParameter("hdRowNum")!=null?request.getParameter("hdRowNum"):"1"%>">
  <input type="hidden" name="iAnio" value="<%=request.getParameter("iAnioSel")!=null?request.getParameter("iAnioSel"):request.getParameter("iAnio")!=null?request.getParameter("iAnio"):"0"%>">
  <input type="hidden" name="iCveMdoTrans" value="<%=request.getParameter("iCveMdoTransSel")!=null?request.getParameter("iCveMdoTransSel"):request.getParameter("iCveMdoTrans")!=null?request.getParameter("iCveMdoTrans"):"0"%>">
  <input type="hidden" name="iIDDGPMPT" value="<%=bs!=null?bs.getFieldValue("iIDDGPMPT","1").toString():request.getParameter("iIDDGPMPT")!=null?request.getParameter("iIDDGPMPT"):"1"%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMedSel")!=null?request.getParameter("iCveUniMedSel"):"0"%>">
  <input type="hidden" name="iCveUniMedSel" value="<%=request.getParameter("iCveUniMedSel")!=null?request.getParameter("iCveUniMedSel"):"0"%>">
  <input type="hidden" name="iCvePaisHdn" value="<%=bs!=null?bs.getFieldValue("iCvePais","-1"):"-1"%>">
  <input type="hidden" name="iCveEstadoHdn" value="<%=bs!=null?bs.getFieldValue("iCveEstado","-1"):"-1"%>">
  <input type="hidden" name="iCveMunicipioHdn" value="<%=bs!=null?bs.getFieldValue("iCveMunicipio","-1"):"-1"%>">
  <input type="hidden" name="iCveTpoCausaHdn" value="<%=bs!=null?bs.getFieldValue("iCveTpoCausa","-1"):"-1"%>">
  <input type="hidden" name="iCveCausaHdn" value="<%=bs!=null?bs.getFieldValue("iCveCausa","-1"):"-1"%>">
<%-- inicio de los campos para saltar a las paginas pg070402012 y pg070402014--%>
  <input type="hidden" name="hdiAnio" value="">
  <input type="hidden" name="hdiCveMdoTrans" value="">
  <input type="hidden" name="hdiIDDGPMPT" value="">
  <input type="hidden" name="hdiIdefMedPrev" value="">
<%-- fin de los campos para saltar a las paginas pg070402012 y pg070402014--%>
  <input type="hidden" name="hdBoton" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
      boolean lCaptura   =clsConfig.getCaptura();
      boolean lNuevo     =clsConfig.getNuevo();
      boolean lCaptONvo  =lCaptura||lNuevo;
      boolean lNoCaptONvo=!lCaptura||lNuevo;// ojo!!!! no es lo mismo que  !(lCaptura||lNuevo)   !!!!
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="6">Registro de Accidentes</td></tr>
        <tr>
          <td class="EEtiqueta">Año:</td>
          <td class="ECampo"><select name="iAnioSel"<%=lNoCaptONvo?"":" disabled"%>>
            <option value="0">Seleccione...</option>
<%      int iAnioFin =java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int iAnioIni=iAnioFin-Integer.parseInt(vParametros.getPropEspecifica("Anios"));
        String cIAnioSel=request.getParameter("iAnioSel")!=null?request.getParameter("iAnioSel"):request.getParameter("iAnio");
        do{
%>            <option value="<%=iAnioFin%>"<%=Integer.toString(iAnioFin).equals(cIAnioSel)?" selected":""%>><%=iAnioFin%></option>
<%      }while(iAnioFin-->iAnioIni);
%>          </select></td>
          <%=vEti.EtiSelectOneRowCS("EEtiqueta","Modo de Transporte:","ECampo","iCveMdoTransSel","&nbsp;","",3,clsConfig.getMdoTrans(),"iCveMdoTrans","cDscMdoTrans",request,request.getParameter("iCveMdoTrans"),true,lNoCaptONvo,true)%>
        </tr>
<%    if(lNuevo||bs!=null){
        String cCvePaisHdn     =request.getParameter("iCvePaisHdn"     )!=null?request.getParameter("iCvePaisHdn"     ):"-1";
        String cCveEstadoHdn   =request.getParameter("iCveEstadoHdn"   )!=null?request.getParameter("iCveEstadoHdn"   ):"-1";
        String cCveMunicipioHdn=request.getParameter("iCveMunicipioHdn")!=null?request.getParameter("iCveMunicipioHdn"):"-1";
        String cCveTpoCausaHdn =request.getParameter("iCveTpoCausaHdn" )!=null?request.getParameter("iCveTpoCausaHdn" ):"-1";
        String cCveCausaHdn    =request.getParameter("iCveCausaHdn"    )!=null?request.getParameter("iCveCausaHdn"    ):"-1";
        int iVehFedInvolucra=0,iVehPartInvolucra=0,iVehEdoInvolucra=0;
        int iPerFedInvolucra=0,iPerPartInvolucra=0,iPerEdoInvolucra=0;
        String cDtAccidente="&nbsp;",cDtNotifica="&nbsp;";
        String cDscAccidente="";
        TVINVRegistro vINVRegistro=null;
        if(!lNuevo){
          SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
          vINVRegistro=(TVINVRegistro)bs.getCurrentBean();
          iVehFedInvolucra =vINVRegistro.getIVehFedInvolucra();
          iVehPartInvolucra=vINVRegistro.getIVehPartInvolucra();
          iVehEdoInvolucra =vINVRegistro.getIVehEdoInvolucra();
          iPerFedInvolucra =vINVRegistro.getIPerFedInvolucra();
          iPerPartInvolucra=vINVRegistro.getIPerPartInvolucra();
          iPerEdoInvolucra =vINVRegistro.getIPerEdoInvolucra();
          cDtAccidente=sdf.format(vINVRegistro.getDtAccidente());
          cDtNotifica=sdf.format(vINVRegistro.getDtNotifica());
          cDscAccidente=vINVRegistro.getCDscAccidente();
        }
%>        <tr class="ETablaT"><td colspan="6">Informaci&oacute;n del Accidente</td></tr>
        <tr>
          <td class="EEtiqueta">ID DGPMPT:</td><td class="ECampo"><%=lNuevo?"&nbsp;":bs.getFieldValue("iIDDGPMPT","&nbsp;").toString()%></td>
          <%=vEti.EtiCampo("EEtiqueta","ID Mdo Transporte:","ECampo","text",12,10,"iIDMdoTrans",lNuevo?"":bs.getFieldValue("iIDMdoTrans","&nbsp;").toString(),3,"","",false,true,lCaptONvo)%>
          <%=vEti.EtiCampo("EEtiqueta","Fecha del accidente:","ECampo","text",12,10,"dtAccidente",lNuevo?"":cDtAccidente,3,"","",false,true,lNuevo)%>
        </tr>
        <tr><%=vEti.EtiCampoCS("EEtiqueta","Lugar:","ECampo","text",110,100,5,"cLugar",lNuevo?"":bs.getFieldValue("cLugar","&nbsp;").toString(),3,"","",false,true,lCaptONvo)%></tr>
        <tr><%=vEti.EtiCampoCS("EEtiqueta","Descripci&oacute;n Breve:","ECampo","text",110,100,5,"cDscBreve",lNuevo?"":bs.getFieldValue("cDscBreve","&nbsp;").toString(),3,"","",false,true,lCaptONvo)%></tr>
        <tr><%=vEti.EtiAreaTextoCS("EEtiqueta","Descripci&oacute;n:","ECampo",100,10,5,"cDscAccidente",lNuevo?"":cDscAccidente,3,"\" onKeyPress=\"return fOnKeyDown(this,2000);","fVerLength(this,2000)",false,lCaptONvo,true)%></tr>
<%      if(lCaptONvo){
%>        <tr class="ECampo"><td colspan="6" align="right"><input type="text" name="cta" size="5" disabled value="<%=2000-cDscAccidente.length()%>"></td></tr>
<%      }
%>        <tr>
          <%=vEti.EtiSelectOneRowCS("EEtiqueta","Pais:","ECampo","iCvePais",lNuevo?"":bs.getFieldValue("cPais","&nbsp;").toString(),"fRecalSelects('ESTADOS')",1,clsConfig.getPaises(),"iCvePais","cNombre",request,cCvePaisHdn,true,lCaptONvo,lCaptONvo)%>
          <%=vEti.EtiSelectOneRowCS("EEtiqueta","EDO (Estado):","ECampo","iCveEstado",lNuevo?"":bs.getFieldValue("cEstado","&nbsp;").toString(),"fRecalSelects('MUNICIPIOS')",1,clsConfig.getEntidadFeds(cCvePaisHdn),"iCveEntidadFed","cNombre",request,cCveEstadoHdn,true,lCaptONvo,lCaptONvo)%>
          <%=vEti.EtiSelectOneRowCS("EEtiqueta","MUN (Municipio):","ECampo","iCveMunicipio",lNuevo?"":bs.getFieldValue("cMunicipio","&nbsp;").toString(),"",1,clsConfig.getMunicipios (cCvePaisHdn,cCveEstadoHdn),"iCveMunicipio","cNombre",request,cCveMunicipioHdn,true,lCaptONvo,lCaptONvo)%>
        </tr>
        <tr>
          <%=vEti.EtiSelectOneRowCS("EEtiqueta","Tipo de Causa:","ECampo","iCveTpoCausa",lNuevo?"":bs.getFieldValue("cDscTpoCausa","&nbsp;").toString(),"fRecalSelects('CAUSAS')",1,clsConfig.getTpoCausas(),"iCveTpoCausa","cDscTpoCausa",request,cCveTpoCausaHdn,true,lCaptONvo,lCaptONvo)%>
          <%=vEti.EtiSelectOneRowCS("EEtiqueta","Posible Causa:","ECampo","iCveCausa",lNuevo?"":bs.getFieldValue("cDscCausa","&nbsp;").toString(),"",3,clsConfig.getCausas(cCveTpoCausaHdn),"iCveCausa","cDscCausa",request,cCveCausaHdn,true,lCaptONvo,lCaptONvo)%>
        </tr>
        <tr>
          <%if(request.getParameter("dtNotifica")!=null && request.getParameter("dtNotifica").length()>0){
             out.println(vEti.EtiCampo("EEtiqueta","Fecha de la Notificaci&oacute;n:","ECampo","text",12,10,"dtNotifica",lNuevo?"":cDtNotifica,3,"","",false,true,lNuevo));
          }else{
             out.println(vEti.EtiCampo("EEtiqueta","Fecha de la Notificaci&oacute;n:","ECampo","text",12,10,"dtNotifica",dFechaActual,3,"","",false,true,lNuevo));
          }
          %>
          <%=vEti.EtiSelectOneRowCS("EEtiqueta","Medio:","ECampo","iCveMedInforma",lNuevo?"":bs.getFieldValue("cDscMedInforma","&nbsp;").toString(),"",3,clsConfig.getMedInforms(),"iCveMedInforma","cDscMedInforma",request,"0",true,lCaptONvo,lNuevo)%>
        </tr>
        <tr>
          <td class="EEtiqueta">Tipo de Investigación:</td>
          <td class="ECampo" colspan="5">
          <% String iCveMotivoExc = vParametros.getPropEspecifica("MtvoXInvAcc");
             if (lCaptura){
                if (lNuevo){
                  Vector vMotivo = new Vector();
                  vMotivo = (Vector) AppCacheManager.getColl("GRLMotivo", "4");
                  out.print("<select name=\"iCveMotivo\">");
                  out.print("<option value=\"0\" selected>Seleccione...</option>");
                  if (!vMotivo.isEmpty()){
                     for(int i=0;i<vMotivo.size();i++){
                        TVMotivo VMotivo = new TVMotivo();
                        VMotivo = (TVMotivo) vMotivo.get(i);
                        if (iCveMotivoExc.toString().compareToIgnoreCase(new Integer(VMotivo.getICveMotivo()).toString()) != 0)
                           out.print("<option value=\"" + VMotivo.getICveMotivo() + "\">" + VMotivo.getCDscMotivo() + "</option>");
                     }
                  }
                }
                else {
                   //out.print(vEti.SelectOneRowSinTD("iCveMotivo","", (Vector) AppCacheManager.getColl("GRLMotivo", "4"),"iCveMotivo","cDscMotivo",null,bs.getFieldValue("iCveMotivo","&nbsp;").toString(),lCaptura));
                  Vector vMotivo = new Vector();
                  vMotivo = (Vector) AppCacheManager.getColl("GRLMotivo", "4");
                  out.print("<select name=\"iCveMotivo\">");
                  out.print("<option value=\"0\">Seleccione...</option>");
                  if (!vMotivo.isEmpty()){
                     for(int i=0;i<vMotivo.size();i++){
                        TVMotivo VMotivo = new TVMotivo();
                        VMotivo = (TVMotivo) vMotivo.get(i);
                        if (iCveMotivoExc.toString().compareToIgnoreCase(new Integer(VMotivo.getICveMotivo()).toString()) != 0){
                          if (bs.getFieldValue("iCveMotivo","&nbsp;").toString().compareToIgnoreCase(new Integer(VMotivo.getICveMotivo()).toString()) == 0)
                            out.print("<option value=\"" + VMotivo.getICveMotivo() + "\" selected>" + VMotivo.getCDscMotivo() + "</option>");
                          else
                            out.print("<option value=\"" + VMotivo.getICveMotivo() + "\">" + VMotivo.getCDscMotivo() + "</option>");
                        }
                     }
                  }
                }
             }
             else {
                Vector vMotivo = new Vector();
                vMotivo = (Vector) AppCacheManager.getColl("GRLMotivo", "4");
                if (!vMotivo.isEmpty()){
                   boolean lPinto = false;
                   for(int i=0;i<vMotivo.size();i++){
                      TVMotivo VMotivo = new TVMotivo();
                      VMotivo = (TVMotivo) vMotivo.get(i);
                      if (bs.getFieldValue("iCveMotivo","&nbsp;").toString().compareToIgnoreCase(new Integer(VMotivo.getICveMotivo()).toString()) == 0){
                        out.print(VMotivo.getCDscMotivo());
                        lPinto = true;
                      }
                   }
                   if (!lPinto)
                     out.print("&nbsp");
                }
                else
                  out.print("&nbsp");
             }
          %>
          </td>
        </tr>
        <tr class="ETablaT"><td colspan="6">Partes Involucradas</td></tr>
        <tr class="ETablaT">
          <td colspan="2">&nbsp;</td>
          <td>Federales</td>
          <td>Particulares</td>
          <td>Estatales</td>
          <td>Total</td>
        </tr>
        <tr class="ECampo">
          <td class="EEtiqueta" colspan="2">Vehiculos:</td>
          <td><%=lNuevo?vEti.CampoSinCelda("text",5,5,"iVehFedInvolucra" ,"",3,"","",false,true).toString():Integer.toString(iVehFedInvolucra )%></td>
          <td><%=lNuevo?vEti.CampoSinCelda("text",5,5,"iVehPartInvolucra","",3,"","",false,true).toString():Integer.toString(iVehPartInvolucra)%></td>
          <td><%=lNuevo?vEti.CampoSinCelda("text",5,5,"iVehEdoInvolucra" ,"",3,"","",false,true).toString():Integer.toString(iVehEdoInvolucra )%></td>
          <td><%=lNuevo?"&nbsp;":Integer.toString(iVehFedInvolucra+iVehPartInvolucra+iVehEdoInvolucra)%></td>
        </tr>
        <tr class="ECampo">
          <td class="EEtiqueta" colspan="2">Personal:</td>
          <td><%=lNuevo?vEti.CampoSinCelda("text",5,5,"iPerFedInvolucra" ,"",3,"","",false,true).toString():Integer.toString(iPerFedInvolucra )%></td>
          <td><%=lNuevo?vEti.CampoSinCelda("text",5,5,"iPerPartInvolucra","",3,"","",false,true).toString():Integer.toString(iPerPartInvolucra)%></td>
          <td><%=lNuevo?vEti.CampoSinCelda("text",5,5,"iPerEdoInvolucra" ,"",3,"","",false,true).toString():Integer.toString(iPerEdoInvolucra )%></td>
          <td><%=lNuevo?"&nbsp;":Integer.toString(iPerFedInvolucra+iPerPartInvolucra+iPerEdoInvolucra)%></td>
        </tr>
<%      if(lCaptONvo){
%>        <tr><td class="EEtiqueta" colspan="2">Registro Finalizado:</td><td class="ECampo" colspan="4" align="right"><input type="checkbox" name="lFinRegistro" value="1"></td></tr>
<%      }
        if(!lCaptONvo){
%>        <tr><td colspan="6" align="center"><a class="EAncla" href="javascript:fIrCatalogo(12)">Vehiculos</a>&nbsp;&nbsp;&nbsp;<a class="EAncla" href="javascript:fIrCatalogo(14)">Personal</a></td></tr>
<%      }
      }else{
%>        <tr><td class="EEtiqueta">Mensaje:</td><td class="ECampo" colspan="6">No existen datos coincidentes con el filtro proporcionado</td></tr>
<%    }
%>      </table>
    </td></tr>
<%  }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<%  vEntorno.clearListaImgs();
%></html>
