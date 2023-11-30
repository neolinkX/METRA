<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>

<html>

<%

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("CargaCitas.jsp");                     // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
 // vEntorno.setActionForm("pg070101091.jsp\" target=\"FRMCuerpo");  // modificar
  vEntorno.setUrlLogo("Acerca");
 // vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());
// LLamado al Output Header
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());


%>

<head>
  <meta name="Autor" content="Micros Personales S.A. de C.V.">
</head>

<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">
<form method="POST" action="pg070102020.jsp" target="FRMCuerpo">
  <input type="hidden" name="hdCCondicion" value="">
  <input type="hidden" name="hdCOrdenar" value="">

  <input type="hidden" name="hdRowNum" value="">
  <input type="hidden" name="hdCampoClave" value="">
  <input type="hidden" name="hdModulo" value="">
  <input type="hidden" name="hdFecha" value="">
  <input type="hidden" name="hdCita" value="">
  <input type="hidden" name="hdAnio" value="2011">
  <input type="hidden" name="hdPbaRapida" >
  <input type="hidden" name="hdPersonal">
  <input type="hidden" name="hdiCvePersonal">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<tr>
<td class="EEtiqueta">
<p>&nbsp;</p>
                                    <table align="center">
                          <tr>
                            <td><div align="center">
                              <img src="<%= vParametros.getPropEspecifica("RutaImg") %>loading.gif"  align="absmiddle" />
                              <br>
                            </div></td>
                          </tr>
                          <tr>
                            <td>Cargando citas</td>
                          </tr>
                        </table>

</td>  
</tr>
                          </table>

  </td></tr>

 </table>
</form>
</body>


</html>

