<%@ page import="com.micper.ingsw.*"%>
<%
  TParametro  vParametros = new TParametro("13");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>CD/ineng.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>PanelClip.js"></SCRIPT>
<script language="JavaScript">
  fPag();
</script>
