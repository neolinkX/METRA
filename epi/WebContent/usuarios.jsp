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
$("#breadcrumb-title").html('Registro de usuarios');
$("#breadcrumb-title").append(' / Administración de usuarios y roles');
</script>
		<div class="m-portlet m-portlet--mobile">

			<div class="m-portlet__head">
				<div class="m-portlet__head-caption">
						<div class="col-xl-12 order-1 order-xl-2 m--align-right">

							<a id="usr_js_fn_01" href="javascript:;" class="btn btn-primary m-btn m-btn--custom m-btn--icon m-btn--air m-btn--pill">
								<span>
									<i class="fa fa-users left"></i>
									<span>
										Nuevo Usuario
									</span>
								</span>
							</a>

							
							<a id="rls_js_fn_02" href="javascript:;" class="btn btn-primary m-btn m-btn--custom m-btn--icon m-btn--air m-btn--pill">
								<span>
									<i class="fa fa-users left"></i>
									<span>
										Roles
									</span>
								</span>
							</a>

							
							

							<div class="m-separator m-separator--dashed d-xl-none"></div>
						</div>
				</div>
			</div>



			<div class="m-portlet__body">
				<table id="usuarios" class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
						<tr>
								<th>ID</th>
								<th>Usuario</th>
								<th>Correo</th>
								<th>Nombre</th>
								<th>Apellido Paterno</th>
								<th>Apellido Materno</th>
								<th>Rol</th>
								<th>&nbsp;</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
<script>

$("#usr_js_fn_08").each(function() {
		var t = $(this).data("title")
			, a = $(this).data("message")
			, s = $(this).data("type")
			, e = $(this).data("allow-outside-click")
			, n = $(this).data("show-confirm-button")
			, c = $(this).data("show-cancel-button")
			, o = $(this).data("close-on-confirm")
			, i = $(this).data("close-on-cancel")
			, l = $(this).data("confirm-button-text")
			, u = $(this).data("cancel-button-text")
			, h = $(this).data("popup-title-success")
			, d = $(this).data("popup-message-success")
			, r = $(this).data("popup-title-cancel")
			, f = $(this).data("popup-message-cancel")
			, p = $(this).data("confirm-button-class")
			, m = $(this).data("cancel-button-class");
		$(this).click(function() {
				swal({
						title: t,
						text: a,
						type: s,
						allowOutsideClick: e,
						showConfirmButton: n,
						showCancelButton: c,
						confirmButtonClass: p,
						cancelButtonClass: m,
						closeOnConfirm: o,
						closeOnCancel: i,
						confirmButtonText: l,
						cancelButtonText: u
				}, function(t) {
					if(t){
						$.ajax({
							url: 'usuarios/desbloquear_usuarios',
							dataType: 'json',
							success: function(resp_success){
								if (resp_success['resp'] == true) {
									swal(h, d, "success");
									$('#usr_js_fn_08').remove();
									$('#usuarios').DataTable().ajax.reload();
								}else{
									alerta_div('error_alerta',resp_success['mensaje'],resp_success['error']);
								}
							},
							error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-08');}
						});
					}else{
						swal(r, f, "error");
					}
				})
		})
});

    $(document).ready(function() {
        $('#usuarios').dataTable( {
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
                "url": "<%=vParametros.getPropEspecifica("URL_APP")%>usuarios/obtener_usuarios.jsp",
                "type": "POST"
            }
        } );

    } );
		var pusher = new Pusher('6187333c14701db03748', {
			encrypted: true
		});

		var updChannel = pusher.subscribe('usuarios');

		pusher.connection.bind('connected', function() {
			console.log(' Servicio de actualización de usuarios activo');
		})
		updChannel.bind('evento', function(data) {
			$('#loginusr').DataTable().ajax.reload();
		});
</script>

