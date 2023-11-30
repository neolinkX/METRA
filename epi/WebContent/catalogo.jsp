<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<script>
$("#breadcrumb-title").html('Catálogo general');
$("#breadcrumb-title").append(' / Listado catálogos');
</script>

<div class="m-portlet m-portlet--mobile">


	<div class="m-portlet__head">
		<div class="m-portlet__head-caption">
				<div class="col-xl-12 order-1 order-xl-2 m--align-right">

					
					<a id="cat_js_fn_07" href="javascript:;" class="btn btn-primary m-btn m-btn--custom m-btn--icon m-btn--air m-btn--pill">
						<span>
							<i class="fa fa-plus left"></i>
							<span>
								Nuevo elemento
							</span>
						</span>
					</a>

										<div class="m-separator m-separator--dashed d-xl-none"></div>
				</div>
		</div>
	</div>
	<div class="m-portlet__body">
		<table id="catalogo" class="table table-striped table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>ID</th>
					<th>Parent</th>
					<th>Catalogo</th>
					<th>Etiqueta</th>
					<th>Activo</th>
					<th>Orden</th>
					<th>Valor</th>
				</tr>
			</thead>
		</table>
	</div>
</div>


<script>
$(document).ready(function() {
    $('#catalogo').dataTable( {
        "fnDrawCallback": function( oSettings ) {
          /**/
        },
        "language": {
            "url": "http://10.33.143.52:7001/epi/assets/plugins/datatables/Spanish.json"
        },
				"searching": true,
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        "processing": true,
        "serverSide": true,
		    "ajax": {
            "url": "catalogo/obtener_catalogo.jsp",
            "type": "POST"
        }
    } );
} );
accion_catalogo();
var pusher = new Pusher('6187333c14701db03748', {
	encrypted: true
});

var updChannel = pusher.subscribe('catalogo');

pusher.connection.bind('connected', function() {
	console.log(' Servicio de actualización de catalogo activo');
})
updChannel.bind('evento', function(data) {
	$('#loginusr').DataTable().ajax.reload();
});

</script>
