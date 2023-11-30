<%@ page import="com.micper.ingsw.*"%><%TParametro  vParametros = new TParametro("13");%>
<!DOCTYPE html>
<script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>prop.js"></script>
<script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>CD/componentes.js"></script>
<script type="text/javascript" src= "<%=vParametros.getPropEspecifica("RutaFuncs")%>jQuery/jquery-1.8.2.js"></script>
<script type="text/javascript" src= "<%=vParametros.getPropEspecifica("RutaFuncs")%>jQuery/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src= "<%=vParametros.getPropEspecifica("RutaFuncs")%>jQuery/chosen.jquery.min.js"></script>
<script type="text/javascript" src= "<%=vParametros.getPropEspecifica("RutaFuncs")%>jQuery/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src= "<%=vParametros.getPropEspecifica("RutaFuncs")%>jQuery/jquery.ajax.js"></script>
<script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")+""+request.getParameter("cPagina")%>"></script>
<script type="text/javascript">fPag();</script>
<script type="text/javascript" src= "<%=vParametros.getPropEspecifica("RutaFuncs")%>jQuery/jquery.thinClient.js"></script>
<%if(request.getParameter("cScript")!=null&&request.getParameter("cScript")!=""){%>
<script type="text/javascript">
try{eval("<%=request.getParameter("cScript")%>");}catch(e){alert("Error:"+e.description);}
</script>
<%}%>
<style type="text/css">
body {
	font-size:75%;
}

#noti_Container {
    position:relative;     /* This is crucial for the absolutely positioned element */
    border:1px solid blue; /* This is just to show you where the container ends */
    width:16px;
    height:16px;
}
.noti_bubble {
    position:absolute;    /* This breaks the div from the normal HTML document. */
    top: -6px;
    right:-6px;
    padding:1px 2px 1px 2px;
    background-color:red; /* you could use a background image if you'd like as well */
    color:white;
    font-weight:bold;
    font-size:0.55em;
	display:'none';
    /* The following is CSS3, but isn't crucial for this technique to work. */
    /* Keep in mind that if a browser doesn't support CSS3, it's fine! They just won't have rounded borders and won't have a box shadow effect. */
    /* You can always use a background image to produce the same effect if you want to, and you can use both together so browsers without CSS3 still have the rounded/shadow look. */
    border-radius:30px;
    box-shadow:1px 1px 1px gray;
}
</style>



