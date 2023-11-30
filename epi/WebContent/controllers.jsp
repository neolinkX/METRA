<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>    

<%  
TEntorno    vEntorno      = new TEntorno();
vEntorno.setNumModulo(07);
TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
%>    
<script> 
$("#breadcrumb-title").html('Controladores y métodos');
$("#breadcrumb-title").append(' / Alta de pares controlador-método');
</script>
<div class="m-portlet m-portlet--mobile">


	<div class="m-portlet__head">
		<div class="m-portlet__head-caption">
				<div class="col-xl-12 order-1 order-xl-2 m--align-right">
					<a id="cntr_js_fn_04" href="javascript:;" class="btn btn-primary m-btn m-btn--custom m-btn--icon m-btn--air m-btn--pill">
						<span>
							<i class="fa fa-plus left"></i>
							<span>
								Nuevo par
							</span>
						</span>
					</a>
					<div class="m-separator m-separator--dashed d-xl-none"></div>
				</div>
		</div>
	</div>
	<div class="m-portlet__body">
		<table id="controllers" class="table table-striped table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>ID</th>
					<th>Controller</th>
					<th>Method</th>
					<th>Nombre</th>
					<th>Descripción</th>
				</tr>
			</thead>
		</table>
	</div>

</div>

<script>
$(document).ready(function() {
    $('#controllers').dataTable( {
      "fnDrawCallback": function( oSettings ) {
        /**/
      },
    "language": {
        "url": "<%=vParametros.getPropEspecifica("URL_APP")%>assets/plugins/datatables/Spanish.json"
    },
		"searching": true,
    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
    "processing": true,
    "serverSide": true,
		"ajax": {
            "url": "<%=vParametros.getPropEspecifica("URL_APP")%>controllers/obtener_controllers.jsp",
            "type": "POST"
        }
    } );
} );
accion_controller();

var pusher = new Pusher('6187333c14701db03748', {
	encrypted: true
});

var updChannel = pusher.subscribe('controllers');

pusher.connection.bind('connected', function() {
	console.log(' Servicio de actualización de controladores activo');
})
updChannel.bind('evento', function(data) {
	$('#loginusr').DataTable().ajax.reload();
});

</script>
