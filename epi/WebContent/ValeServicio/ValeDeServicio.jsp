<%@ page import="gob.sct.medprev.dao.TDEXPServicio"%>
<%@ page import="gob.sct.medprev.vo.TVEXPServicio"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamCat"%>

<%
TDEXPExamCat cat = new TDEXPExamCat();
int iCveUniMed = cat.FindByInt("Select icveunimed from expexamaplica where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("inumExamen"));
int iCveModulo = cat.FindByInt("Select icveunimed from expexamaplica where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("inumExamen"));
String  cCveUniMed = cat.SentenciaSQL("Select cdscUnimed from grlunimed where icveunimed = "+iCveUniMed);
String cCveModulo = cat.SentenciaSQL("Select cdscModulo from grlmodulo where icveunimed = "+iCveUniMed + " and icvemodulo = "+iCveModulo);
String cFecha = cat.SentenciaSQL("Select dtsolicitado from expexamaplica where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("inumExamen"));
%>

<html>

<script
	src="http://10.33.143.52:7001/epi/Archivos/funciones/pg0700002DB.js"></script>
<SCRIPT LANGUAGE="JavaScript"
	SRC="http://10.33.143.52:7001/epi/Archivos/funciones/pg070104001.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="http://10.33.143.52:7001/epi/Archivos/funciones/calendario.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="http://10.33.143.52:7001/epi/Archivos/funciones/SelPer.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
	function fOnLoad() {
		fOnSet(window, document.forms[0].action, 'yes', 'SaveCancelOnly',
				'Guardar', 'BorrarB', 'Hidden', 'hidden', false, true, '', '',
				'1', 'Propiedad|Valor|', 'cPropiedad|cValor|', '7|8|',
				'Propiedad|Valor|', 'cPropiedad|cValor|', 'Imprimir', true,
				'pg070104001TMP.jsp');
	}
	  function fPrint(){
		    window.print();
		  }
</SCRIPT>
<head>
<meta name="Autor" content="Micros Personales S.A. de C.V.">










<!--  BOOTASTRAP -->
<meta name="description"
	content="Framedev -  Framework para desarrolladores">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="http://10.33.143.52:7001/epi/assets/app/js/webfont.js"></script>
<script>
	WebFont.load({
		//google: {"families":["Poppins:300,400,500,600,700","Roboto:300,400,500,600,700"]},
		google : {
			"families" : [ "Montserrat:300,400,500,600,700",
					"Roboto:300,400,500,600,700" ]
		},
		active : function() {
			sessionStorage.fonts = true;
		}
	});
</script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<link
	href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-sweetalert/sweetalert.css"
	rel="stylesheet" type="text/css" />
<link
	href="http://10.33.143.52:7001/epi/assets/vendors/base/vendors.bundle.css"
	rel="stylesheet" type="text/css" />
<link
	href="http://10.33.143.52:7001/epi/assets/demo/default/base/style.bundle.css"
	rel="stylesheet" type="text/css" />
<link
	href="http://10.33.143.52:7001/epi/assets/demo/default/base/menu_alter.css"
	rel="stylesheet" type="text/css" />

<!--JQuery-->
<script
	src="http://10.33.143.52:7001/epi/assets/plugins/jquery-3.2.1.min.js"></script>

<!--Plugins JQuery-->
<link
	href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-touchspin/bootstrap.touchspin.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-toastr/toastr.min.css"
	rel="stylesheet" type="text/css" />

<!--APP-->
<link href="http://10.33.143.52:7001/epi/css/app.css" rel="stylesheet"
	type="text/css">

<!--calendar-->
<link
	href="http://10.33.143.52:7001/epi/assets/vendors/custom/jquery-ui/jquery-ui.bundle.css"
	rel="stylesheet" type="text/css" />
<link
	href="http://10.33.143.52:7001/epi/assets/vendors/custom/fullcalendar/fullcalendar.bundle.css"
	rel="stylesheet" type="text/css" />

<!--iconos-->
<link href="http://10.33.143.52:7001/epi/css/svg.css" rel="stylesheet"
	type="text/css">

<link href="http://10.33.143.52:7001/epi/css/loader.css"
	rel="stylesheet" type="text/css" />
<script src="http://10.33.143.52:7001/epi/assets/js/loader.js"></script>

<!--jquery steps-->
<link href="http://10.33.143.52:7001/epi/css/jquery.steps.css"
	rel="stylesheet" type="text/css" />

<link rel="shortcut icon"
	href="http://10.33.143.52:7001/epi/img/favicon.ico">
<!--  FIN BOOTASTRAP -->










</head>
<SCRIPT LANGUAGE="JavaScript"
	SRC="http://10.33.143.52:7001/epi/Archivos/funciones/valida_nt.js"></SCRIPT>
<SCRIPT language="JavaScript"
	src="http://10.33.143.52:7001/epi/Archivos/funciones/t07_Tooltips.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="http://10.33.143.52:7001/epi/Archivos/funciones/buscar_nt.js"></SCRIPT>

<link rel="stylesheet"
	href="http://10.33.143.52:7001/epi/Archivos/estilos/07_estilos.css"
	TYPE="text/css">
<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">

	<br>
	<br>	&nbsp;	&nbsp;	&nbsp;
	<table width="80%" height="10%" border="1" class="ETablaInfo"
		align="left">
		<tr>
			<td colspan="3" align="center" class="EResalta">Datos del Personal</td>
		</tr>
		<tr>
			<td class='EEtiqueta'>Expediente:</td>
			<td colspan='2' class='ECampo'><%= session.getAttribute("id") %></td>
		</tr>
		<tr>
			<td class='EEtiqueta'>Nombre:</td>
			<td colspan='2' class='ECampo'><%= session.getAttribute("nombreExp") %></td>
		</tr>
		<tr>
			<td class='EEtiqueta'>R.F.C.:</td>
			<td colspan='2' class='ECampo'><%= session.getAttribute("rfcExp") %></td>
		</tr>
		<tr>
			<td class='EEtiqueta'>Modo Trans.</td>
			<td colspan='2' class='ECampo'><%= session.getAttribute("MdoTransTramite") %></td>
		</tr>
		<tr>
			<td class='EEtiqueta'>Categor&iacute;a:</td>
			<td colspan='2' class='ECampo'><%= session.getAttribute("iCveCategoria") %></td>
		</tr>
		<tr>
			<td class='EEtiqueta'>Motivo:</td>
			<td colspan='2' class='ECampo'><%= session.getAttribute("id_motivo") %></td>
		</tr>
	</table>
	&nbsp;


	<table width="80%" height="10%" border="1" class="ETablaInfo"
		align="left">
		<tr>
			<td align="center" colspan="3" class="EResalta">Vale de
				Servicios</td>
		</tr>
		<tr>
			<td colspan="3" class="ETablaT">Inicio del Examen</td>
		</tr>
		<tr>
			<td class='EEtiqueta'>Unidad M&eacute;dica:</td>
			<td colspan='2' class='ECampo'><%= cCveUniMed%></td>
		</tr>
		<tr>
			<td class='EEtiqueta'>M&oacute;dulo:</td>
			<td colspan='2' class='ECampo'><%= cCveModulo %></td>
		</tr>
		<tr>
			<td class='EEtiqueta'>Proceso:</td>
			<td colspan='2' class='ECampo'>EXAMEN PSICOFISICO INTEGRAL</td>
		</tr>
		<tr>
			<td class='EEtiqueta'>Fecha</td>
			<td colspan='2' class='ECampo'><%=cFecha %></td>
		</tr>
	</table>
	&nbsp;





	<table  width="80%" height="10%" border="1" class="ETablaInfo" align="left" cellspacing="0"
		cellpadding="3">
		<tr>
			<td colspan="5" align="center" class="EResalta">Servicios</td>
		</tr>
		<tr>
			<td class='ETablaT'>Aplicado</td>
			<td class='ETablaT'>Servicio</td>
			<td class='ETablaT'>Hora de Entrada</td>
			<td class='ETablaT'>Hora de Salida</td>
			<td width='200' class='ETablaT'>Firma</td>
		</tr>


<%
//FindByAll
TDEXPServicio dEXPServicio = new TDEXPServicio(); 
Vector servicios = dEXPServicio.FindBy(session.getAttribute("id")+"",session.getAttribute("inumExamen")+"");
TVEXPServicio vServ;
if(servicios.size() > 0){
	for(int i=0;i<servicios.size();i++){
        vServ = (TVEXPServicio) servicios.get(i);

%>


		<tr>
			<td class='ETablaC'><input type='checkbox'></td>
			<td class='ECampo'><%= vServ.getCDscServicio() %></td>
			<td class='ECampo'>&nbsp;</td>
			<td class='ECampo'>&nbsp;</td>
			<td class='ECampo'>&nbsp;</td>
		</tr>
		
<%
			}       
}
%>
				
		
	</table>
	<div class="m-form__actions">
			<button class="btn btn-primary" onClick="fPrint()">Imprimir</button>
		</div>
	</form>


</body>


</html>
