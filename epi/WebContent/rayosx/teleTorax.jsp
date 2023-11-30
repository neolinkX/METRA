<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
    <div class="form-group m-form__group">
  <div class="alert m-alert m-alert--default" role="alert">
    RAMA TELE DE TORAX
  </div>
</div>
<form class="m-form m-form--fit m-form--group-seperator-dashed" id="frm_teletorax" name="frm_teletorax">

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

  <div class="row">
    <div class="col-md-6">
      <div class="m-portlet__body">
        <div class="m-section">

          <div class="m-form__group form-group row">
            <label for="tt_torax" class="col-4 col-form-label">
              Placa de Torax:
            </label>
            <div class="col-8">
              <span class="m-switch m-switch--sm m-switch--icon">
                <label>
                  <input type="checkbox" name="tt_torax">
                  <span></span>
                </label>
              </span>
            </div>
          </div>
          <div class="m-form__group form-group row">
            <label for="tt_fuma" class="col-4 col-form-label">
              Fuma:
            </label>
            <div class="col-8">
              <span class="m-switch m-switch--sm m-switch--icon">
                <label>
                  <input type="checkbox" name="tt_fuma">
                  <span></span>
                </label>
              </span>
            </div>
          </div>
          <div class="form-group m-form__group row">
            <label for="tt_nplacas" class="col-4 col-form-label">
              Numero de Placas:
            </label>
            <div class="col-8">
              <input class="form-control m-input" type="number" id="tt_nplacas" name="tt_nplacas">
            </div>
          </div>
          <div class="form-group m-form__group row">
            <label for="tt_obs" class="col-4 col-form-label">
              Observaciones:
            </label>
            <div class="col-8">
              <textarea class="form-control m-input m-input--air" id="tt_obs" name="tt_obs" rows="4" maxlength="500"></textarea>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="m-portlet__body">
        <div class="m-section">
          <div class="m-portlet__body">
            <div class="form-group m-form__group row">
              <label for="">
                Cargar Archivo de Imagen
              </label>
              <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="m-dropzone dropzone" id="teletorax_img">
                  <div class="m-dropzone__msg dz-message needsclick">
                    <h3 class="m-dropzone__msg-title">
                      Arrastra aqui la imagen o haz click para subir una.
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
          </div>
        </div>
      </div>
    </div>
    <div id="teletorax_div"></div>
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
<script>
$(document).ready(function() {
  $("#teletorax_img").dropzone({
    url: "usuarios/upload_dropzone/examenes|rayosx|teletorax|users/upload_avatar",<?php //usuarios/upload_dropzone/estructura|del|directorio/permisos ?>
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
        $( "#teletorax_div" ).append("<input type='hidden' name='toraximage[]' value='examenes/rayosx/teletorax/users/"+img[1]+"'>");
      });
    }
   });


});
</script>
<script src="<?=URL_PUBLIC?>assets/js/validaciones/rayosx/valida_teletorax.js"></script>
    