<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<script>
$("#breadcrumb-title").html('Logger');
$("#breadcrumb-title").append(' / registro de accesos');
</script>
		<div class="m-portlet m-portlet--mobile">
			<div class="m-portlet__body">
				<table id="logger" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
						<tr>
								<th>ID</th>
								<th>Activa</th>
								<th>F Logueo</th>
								<th>Verificación</th>
                <th>F Logout</th>
                <th>Duración</th>
                <th>ipv4</th>
                <th>Usuario</th>
                <th>Rol</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
<script>
    $(document).ready(function() {
        $('#logger').dataTable( {
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
                "url": "login/loginlogger_get.jsp",
                "type": "POST"
            }
        } );

    } );
		var pusher = new Pusher('6187333c14701db03748', {
			encrypted: true
		});

		var updChannel = pusher.subscribe('logger');

		pusher.connection.bind('connected', function() {
			console.log('Servicio de actualización de logger activo');
		})
		updChannel.bind('evento', function(data) {
			$('#loginusr').DataTable().ajax.reload();
		});
</script>
