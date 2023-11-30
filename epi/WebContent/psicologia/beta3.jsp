<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
    <div class="form-group m-form__group">
    <div class="alert m-alert m-alert--default" role="alert">
        <b>RAMA BETA III</b>
    </div>
</div>
<form class="m-form m-form--fit" id="frm_beta" name="frm_beta">
  <div class="m-form__content">
    <div class="m-alert m-alert--icon alert alert-danger m--hide" role="alert" id="m_form_1_msg">
      <div class="m-alert__icon">
        <i class="la la-warning"></i>
      </div>
      <div class="m-alert__text">
        Favor de Verificar los datos.
      </div>
      <div class="m-alert__close">
        <button type="button" class="close" data-close="alert" aria-label="Close"></button>
      </div>
    </div>
  </div>
  <div class="m-portlet__body">

    <div class="row">
      <div class="form-group m-form__group row col-6">
        <div class="col-md-6" align="center">
          <label for="beta_subprueba1">
            Subprueba 1:
          </label>
        </div>
        <div class="col-md-6">
          <input class="form-control m-input col-8" type="number" id="beta_subprueba1" name="beta_subprueba1">
          <span class="m-form__help">Puntuación Natural</span>
        </div>
      </div>
      <div class="form-group m-form__group row col-6">
        <div class="col-md-5">
          <label for="beta_subprueba12">
            Subprueba 1:
          </label>
        </div>
        <div class="col-md-6">
          <input class="form-control m-input col-8" type="number" name="beta_subprueba12">
          <span class="m-form__help">Puntuación Escalar</span>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="form-group m-form__group row col-6">
        <div class="col-md-6" align="center">
          <label for="beta_subprueba2">
            Subprueba 2:
          </label>
        </div>
        <div class="col-md-6">
          <input class="form-control m-input col-8" type="number" name="beta_subprueba2">
          <span class="m-form__help">Puntuación Natural</span>
        </div>
      </div>
      <div class="form-group m-form__group row col-6">
        <div class="col-md-5">
          <label for="beta_subprueba22">
            Subprueba 2:
          </label>
        </div>
        <div class="col-md-6">
          <input class="form-control m-input col-8" type="number" name="beta_subprueba22">
          <span class="m-form__help">Puntuación Escalar</span>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="form-group m-form__group row col-6">
        <div class="col-md-6" align="center">
          <label for="beta_subprueba3">
            Subprueba 3:
          </label>
        </div>
        <div class="col-md-6">
          <input class="form-control m-input col-8" type="number" name="beta_subprueba3">
          <span class="m-form__help">Puntuación Natural</span>
        </div>
      </div>
      <div class="form-group m-form__group row col-6">
        <div class="col-md-5">
          <label for="beta_subprueba33">
            Subprueba 3:
          </label>
        </div>
        <div class="col-md-6">
          <input class="form-control m-input col-8" type="number" name="beta_subprueba33">
          <span class="m-form__help">Puntuación Escalar</span>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="form-group m-form__group row col-6">
        <div class="col-md-6" align="center">
          <label for="beta_subprueba4">
            Subprueba 4:
          </label>
        </div>
        <div class="col-md-6">
            <input class="form-control m-input col-8" type="number" name="beta_subprueba4">
            <span class="m-form__help">Puntuación Natural</span>
        </div>
      </div>
      <div class="form-group m-form__group row col-6">
        <div class="col-md-5">
          <label for="beta_subprueba44">
            Subprueba 4:
          </label>
        </div>
        <div class="col-md-6">
          <input class="form-control m-input col-8" type="number" name="beta_subprueba44">
          <span class="m-form__help">Puntuación Escalar</span>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="form-group m-form__group row col-6">
        <div class="col-md-6" align="center">
          <label for="beta_subprueba5">
            Subprueba 5:
          </label>
        </div>
        <div class="col-md-6">
            <input class="form-control m-input col-8" type="number" name="beta_subprueba5">
            <span class="m-form__help">Puntuación Natural</span>
        </div>
      </div>
      <div class="form-group m-form__group row col-6">
        <div class="col-md-5">
          <label for="beta_subprueba55">
            Subprueba 5:
          </label>
        </div>
        <div class="col-md-6">
          <input class="form-control m-input col-8" type="number" name="beta_subprueba55">
          <span class="m-form__help">Puntuación Escalar</span>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="form-group m-form__group row col-6">
        <div class="col-md-6" align="center">
          <label for="beta_suma">
            Suma de Puntuaciones Escalares:
          </label>
        </div>
        <div class="col-md-6">
            <input class="form-control m-input col-8" type="number" name="beta_suma">
        </div>
      </div>
      <div class="form-group m-form__group row col-6">
        <div class="col-md-5">
          <label for="beta_percentil">
            Percentil:
          </label>
        </div>
        <div class="col-md-6">
          <input class="form-control m-input col-8" type="number" name="beta_percentil">
        </div>
      </div>
    </div>

    <div class="row">
      <div class="form-group m-form__group row col-12">
        <div class="col-md-3" align="center">
          <label for="beta_ci">
            CI de Beta:
          </label>
        </div>
        <div class="col-md-3">
          <select class="form-control m-select2" name="beta_ci" data-placeholder="Seleccione">
      		  <?=$catalog->selectCatalog($this->help,'ci_beta',null);?>
          </select>
        </div>
      </div>
    </div>
    <br>
    <div class="row">
      <div class="form-group m-form__group row col-12">
        <div class="col-md-3" align="center">
          <label for="beta_ci">
            Interpretación de Resultados:
          </label>
        </div>
        <div class="col-md-7">
          <textarea class="form-control col-12" name="beta_interpretacion" id="beta_interpretacion" rows="2" maxlength="2000" data-set_max_word="2000"></textarea>
        </div>
      </div>
    </div>
    <br>
    <div class="row">
      <div class="form-group m-form__group row col-12">
        <label for="">
           <h5>Archivos a Digitalizar</h5>
        </label>
        <div class="col-lg-12 col-md-12 col-sm-12 text-center">
           <div id="div_doc_append">
           </div>
           <div class="m-dropzone dropzone dz-clickable up_doc_global " id="up_psico_drop" data-routeupfile="usuarios/upload_dropzone/documentos_digitales|acta_nac|/upload_avatar">
              <div class="m-dropzone__msg dz-message needsclick">
                 <h3 class="m-dropzone__msg-title">
                    Arrastra aquí el documento correspondiente.
                 </h3>
                 <span class="m-dropzone__msg-desc">
                 Actualmente no hay archivos
                 <strong>
                 no
                 </strong>
                 se han subido archivos.
                 </span>
              </div>
           </div>
        </div>
      </div>
    </div><br>
    <div class="m-portlet__foot m-portlet__foot--fit">
        <div class="m-form__actions">
            <button type="submit" class="btn btn-primary">
                Guardar
            </button>
            <button type="reset" class="btn btn-secondary">
                Limpiar
            </button>
        </div>
    </div>
  </div>
</form>
<script type="text/javascript">
   $(document).ready(function() {
     var num_exp = $("#id_numexp_left").val();
     $("#up_psico_drop").dropzone({
       url: "usuarios/upload_dropzone/examenes|psicologia|"+num_exp+"/upload_avatar",
       paramName: "file",
       maxFiles: 5,
       maxFilesize: 5, // MB
       acceptedFiles: "image/*",
       accept: function(file, done) {
           //console.log(file);
           done();
       },
       init: function() {
         this.on("success", function(statics,file) {
           var img = file.split("|");
           $( "#div_doc_append" ).append("<input type='hidden' name='archivo_anexos[]' value='examenes/psicologia/"+num_exp+"/"+img[1]+"'>");
         });
       }
      });
   });
</script>
<script src="<?=URL_PUBLIC?>assets/js/validaciones/psicologia/valida_beta.js"></script>
    