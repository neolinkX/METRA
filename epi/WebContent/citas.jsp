<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm"  data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__head" style="background:#f7f8fa">
      <div class="m-portlet__head-caption">
         <div class="m-portlet__head-title">
            <span class="m-portlet__head-icon">
            <i class="fa fa-calendar"></i>
            </span>
            <h3 class="m-portlet__head-text m--font-primary">
               Consulta de Citas con Creación de Expediente
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
      <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
         <!--<li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
            <input type="text" class="form-control m-input">
            </a>
         </li>-->
      </ul>
      <div class="tab-content">
         <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
           <table id="consulta_citas" class="table table-striped table-bordered" cellspacing="0" width="100%">
             <thead>
               <tr>
                   <th>Cita</th>
                   <th>RFC</th>
                   <th>Nombre</th>
                   <th>Hora</th>
                   <th>Cve. Personal</th>
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

    $('#consulta_citas').dataTable( {
        "fnDrawCallback": function( oSettings ) {

        },
        "searching": true,
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        "processing": true,
        "serverSide": true,
        "ajax": {
            //"url": "citas/getCitasData",
            "url": "citas/obtener_citas.jsp",
            "type": "POST"
        }
    } );

} );



</script>
    