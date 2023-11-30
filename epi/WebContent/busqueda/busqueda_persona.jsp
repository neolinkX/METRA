<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<style>
   .m-form .m-form__group{
   padding-bottom: 0px;
   }
</style>
<div class="row">
   <div class="col-md-4">
      <!--begin::Portlet-->
      <div class="m-portlet m-portlet--tab">
         <div class="m-portlet__head">
            <div class="m-portlet__head-caption">
               <div class="m-portlet__head-title">
                  <span class="m-portlet__head-icon m--hide">
                  <i class="la la-gear"></i>
                  </span>
                  <h3 class="m-portlet__head-text">
                     B&uacute;squeda de Persona
                  </h3>
               </div>
            </div>
         </div>
         <!--begin::Form-->
         <form class="m-form m-form--fit m-form--label-align-right" id="form_busqueda_persona">
            <div class="m-portlet__body">              
               <div class="form-group m-form__group row">
                  <label for="ap_paterno" class="col-4 col-form-label">
                  Paterno:
                  </label>
                  <div class="col-8">
                     <input class="form-control m-input text-uppercase" type="text" value="" id="ap_paterno" name="ap_paterno">
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="ap_materno" class="col-4 col-form-label">
                  Materno:
                  </label>
                  <div class="col-8">
                     <input class="form-control m-input text-uppercase" type="text" value="" id="ap_materno" name="ap_materno">
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nombre" class="col-4 col-form-label">
                  Nombre (s):
                  </label>
                  <div class="col-8">
                     <input class="form-control m-input text-uppercase" type="text" value="" id="nombre" name="nombre">
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="crfc" class="col-4 col-form-label">
                  RFC:
                  </label>
                  <div class="col-8">
                     <input class="form-control m-input text-uppercase" type="text" value="" id="crfc" name="crfc">
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="ccurp" class="col-4 col-form-label">
                  CURP:
                  </label>
                  <div class="col-8">
                     <input class="form-control m-input text-uppercase" type="text" value="" id="ccurp" name="ccurp">
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="id" class="col-4 col-form-label">
                  Id:
                  </label>
                  <div class="col-8">
                     <!-- <input class="form-control m-input text-uppercase" type="text" value="" id="id" name="id"> -->
                     <input type="number" class="form-control m-input" id="id" name="id" maxlength="20" data-number> 
                  </div>
               </div>
               <input type="hidden" class="form-control m-input" id="filtro" name="filtro" maxlength="900">
               <!--
               <div class="form-group m-form__group row">
                  <label for="no_personal" class="col-4 col-form-label">
                  No. Personal:
                  </label>
                  <div class="col-8">
                     <input class="form-control m-input text-uppercase" type="text" value="" id="no_personal" name="no_personal">
                  </div>
               </div>-->

               <div class="row m--margin-top-20 hide_content" id="alert_busqueda">
                 <div class="col-12 text-center">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert" style="">
                      <strong>Aviso!</strong> El campo de Nombre y uno de los Apellidos son requeridos para la búsqueda.
                    </div>
                 </div>
               </div>

            </div>
            <div class="m-portlet__foot m-portlet__foot--fit">
               <div class="m-form__actions">
                  <div class="row">
                     <div class="col-4"></div>
                     <div class="col-8">
                       <button type="reset" class="btn btn-default">
                       Limpiar
                       </button>
                        <button type="button" class="btn btn-primary" onclick="busqueda_persona();">
                        Buscar
                        </button>
                     </div>
                  </div>
               </div>
            </div>
         </form>
         <!--end::Form-->
      </div>
      <!--end::Portlet-->
   </div>
   <div class="col-md-8">
      <!--begin::Portlet-->
      <div class="m-portlet m-portlet--tab">
         <div class="m-portlet__head">
            <div class="m-portlet__head-caption">
               <div class="m-portlet__head-title">
                  <span class="m-portlet__head-icon m--hide">
                  <i class="la la-gear"></i>
                  </span>
                  <h3 class="m-portlet__head-text">
                     Resultados B&uacute;squeda
                  </h3>
               </div>
            </div>
         </div>
         <!--begin::Form-->
         <br>
         <div class="m-portlet m-portlet--tab">
           <div class="row">
             <div class="col-lg-12">
              <table id="busqueda_pac" class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Ap. Paterno</th>
                    <th>Ap. Materno</th>
                    <th>Fecha Nac</th>
                  </tr>
                </thead>
              </table>
            </div>
          </div>
         </div>
         <div class="m-portlet__foot">
            <div class="row align-items-center">
               <div class="col-lg-12">
                  <!-- <button type="button" class="btn btn-primary" onclick="carga_archivo('contenedor_principal','persona/altaPersona/');"> -->
                  <button type="button" class="btn btn-primary" onclick="carga_archivo('contenedor_principal','persona/alta_persona.jsp');">
                  Registro de Cita
                  </button>
               </div>
            </div>
         </div>
         <!--end::Form-->
      </div>
      <!--end::Portlet-->
   </div>
</div>
<script>
   $(document).ready(function() {
     $('#busqueda_pac').dataTable();
   } );
</script>
