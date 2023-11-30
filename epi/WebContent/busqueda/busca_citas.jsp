<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="gob.sct.medprev.dao.TDGRLUniMed"%>
    
<%
TDGRLUniMed unidad = new TDGRLUniMed();
String ListaUnimed = unidad.FindByAllEpi2(" where lVigente = 1 ", "");
%>    
<style>
   .m-form .m-form__group{
   padding-bottom: 0px;
   }
</style>
<div class="row">
   <div class="col-md-12">
      <!--begin::Portlet-->
      <div class="m-portlet m-portlet--tab">
         <div class="m-portlet__head">
            <div class="m-portlet__head-caption">
               <div class="m-portlet__head-title">
                  <span class="m-portlet__head-icon m--hide">
                  <i class="la la-gear"></i>
                  </span>
                  <h3 class="m-portlet__head-text m--font-primary">
                     Búsqueda de Citas
                  </h3>
               </div>
            </div>
         </div>
         <!--begin::Form-->
         <form class="m-form m-form--fit m-form--label-align-right" id="form_busqueda_cita">
            <div class="m-portlet__body">
              <div class="col-lg-12 row">
                  <div class="form-group col-lg-4">
                       <label for="u_medica" class="col-form-label">
                        Unidad Médica:
                      </label><br>
                       <!-- <select name="unidad_medica" class="styleSelect2 col-md-12" data-live-search="true"> -->
                       <select class="form-control" id="unidad_medica"  name="unidad_medica" data-placeholder="Seleccionar" data-load_datacombo="catalogo_modulo" onchange="generaCombosSecundario(this,'catalogo_modulo');">
                       <%=ListaUnimed %>
                       </select>
                  </div>

                  <div class="form-group col-lg-3">
                       <label for="u_medica" class="col-form-label">
                         Módulo:
                       </label><br>
                       <!-- <select name="catalogo_modulo" id="catalogo_modulo"  class="styleSelect2" data-live-search="false"> -->
                       <select class="form-control" id="catalogo_modulo"  name="catalogo_modulo" data-placeholder="Seleccionar" >
                         <option value="1">UNIDAD MEDICA</option>
                       </select>
                  </div>

                  <div class="form-group col-lg-3">
                       <label for="u_medica" class="col-form-label">
                        Fecha de la Cita:
                       </label>
                       <input type="text" class="form-control m-input date-picker mask_date" name="fecha_cita" id="fecha_cita">
                  </div>
                  <div class="form-group col-lg-2 text-center">
                       <!--  <button type="button" class="btn btn-primary m--margin-top-30">-->
                       <button type="button" class="btn btn-primary" onclick="busqueda_citas();">
                       Buscar
                       </button>
                  </div>
              </div>
            </div>

         </form>
         <!--end::Form-->
      </div>
      <!--end::Portlet-->
   </div>
   <div class="col-md-12">
      <!--begin::Portlet-->
      <div class="m-portlet m-portlet--tab">
         <div class="m-portlet__head">
            <div class="m-portlet__head-caption">
               <div class="m-portlet__head-title">
                  <span class="m-portlet__head-icon m--hide">
                  <i class="la la-gear"></i>
                  </span>
                  <h3 class="m-portlet__head-text">
                     Resultados Búsqueda Cita
                  </h3>
               </div>
            </div>
         </div>
         <!--begin::Form-->
         <br>
         <div class="m-portlet m-portlet--tab">
           <div class="row">
             <div class="col-lg-12">
              <table id="busqueda_cita" class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Expediente</th>
                    <th>Nombre</th>
                    <th>Ap. Paterno</th>
                    <th>Ap. Materno</th>
                    <th>Fecha Cita</th>
                  </tr>
                </thead>
              </table>
            </div>
          </div>
         </div>

         <!--end::Form-->
      </div>
      <!--end::Portlet-->
   </div>
</div>
<script type="text/javascript">
   $(document).ready(function() {
     $(".styleSelect2").selectpicker();
     carga_datepicker();
     $('#busqueda_cita').dataTable();
   } );
   
  
   
   function busqueda_citas(){
   $.ajax({
     url: 'busqueda/busqueda_de_citas.jsp',
     dataType: 'json',
     type: 'Post',
     data: $("#form_busqueda_cita").serialize(),
       success: function(resp_success){
         //carga_archivo('contenedor_principal','http://lalo.expediente.tk/persona/altaPersona');

         $('#busqueda_cita').DataTable( {
             destroy: true,
             searching: true,
             data: resp_success.data
             } );

           $('#busqueda_cita tbody').on('click', 'tr', function () {
       			var id = $('td', this).eq(0).text();
             carga_ficha_persona(id);
       		} );
       },
     error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-01');}
   });
 }

   
   
   
</script>
