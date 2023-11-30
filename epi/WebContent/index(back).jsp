<html>
<%@ page import="com.micper.ingsw.*"%>
<head>
<script LANGUAGE="JavaScript" TYPE="text/javascript">
  //var cUrlRedirigir = '<%=""+new TParametro("07").getPropEspecifica("RutaProg")%>pg0700000.jsp';
  var cUrlRedirigir = '<%=""+new TParametro("07").getPropEspecifica("RutaProgIni")%>';
  //var cUrlRedirigir = 'http://10.33.143.52:7001/inventario/pg0700000.jsp';
  	var URLactual = window.location.href;
  	var URLNueveop1 = "http://aplicaciones9.sct.gob.mx/medprev/pg0700000.jsp";
  	var URLNueveop2 = "http://aplicaciones9.sct.gob.mx/medprev/";
  	var URLappop1 = "http://app.sct.gob.mx/medprev/pg0700000.jsp";
  	var URLappop2 = "http://app.sct.gob.mx/medprev/";
  	var URLpruebas = "http://10.33.143.52:7001/medprev/";
	//alert(URLactual);
	if(URLactual == URLNueveop1 || URLactual == URLNueveop2 ||
			URLactual == URLappop1 || URLactual == URLappop2){
		//alert("iguales");
		redireccionar();
	}else{
		if(URLactual == URLpruebas){
			cUrlRedirigir = '<%=""+new TParametro("07").getPropEspecifica("RutaProg")%>pg0700000.jsp';
			//cUrlRedirigir = '<%=""+new TParametro("07").getPropEspecifica("RutaProgIni")%>';
		}
		  window.location=cUrlRedirigir;
	}
	

    /*
	function redireccionar(){
		alert("2");
		  window.location=cUrlRedirigir;
	}*/ 
  //top.location = cUrlRedirigir;
</script>

<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body bgcolor="#FFFFFF" topmargin="0" leftmargin="0">
</body>
</html>
