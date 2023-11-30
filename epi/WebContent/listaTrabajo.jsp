<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm"  data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__head" style="background:#f7f8fa">
      <div class="m-portlet__head-caption">
         <div class="m-portlet__head-title">
            <span class="m-portlet__head-icon">
            <i class="fa fa-list-ul"></i>
            </span>
            <h3 class="m-portlet__head-text m--font-primary">
               Lista de Trabajo EPI
            </h3>
         </div>
      </div>
      <div class="m-portlet__head-tools">
         <ul class="m-portlet__nav">
            <li class="m-portlet__nav-item">
               <a href="" data-portlet-tool="toggle" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Collapse">
               <i class="la la-plus"></i>
               </a>
            </li>

            <li class="m-portlet__nav-item">
               <a href="#" data-portlet-tool="fullscreen" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Pantalla Completa">
               <i class="la la-expand"></i>
               </a>
            </li>
         </ul>
      </div>
   </div>
   <div class="m-portlet__body">

      <div class="tab-content">
         <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
           <table id="lista_trabajo_epi" class="table table-striped table-bordered" cellspacing="0" width="100%">
             <thead>
               <tr>
                   <th>Episodio</th>
                   <th>Expediente</th>
                   <th>Nombre</th>
                   <th>Fecha</th>
                   <th>Hora</th>
                   <th>Movimientos</th>
               </tr>
             </thead>
           </table>
         </div>

      </div>
   </div>
   <div class="m-portlet__foot">
      <div class="row align-items-center">
         <div class="col-lg-12">

         </div>
      </div>
   </div>
</div>

<script>
$(document).ready(function() {

    $('#lista_trabajo_epi').dataTable( {
        "fnDrawCallback": function( oSettings ) {

        },
        "searching": true,
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "epi/dataListaEpi.jsp",
            "type": "POST"
        }
    } );

} );
</script>
