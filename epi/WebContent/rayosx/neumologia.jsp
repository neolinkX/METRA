<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
         <div class="form-group m-form__group">
        <div class="alert m-alert m-alert--default" role="alert">
            RAMA NEUMOLOGÍA
        </div>
    </div>
    <!--begin::Form-->
    <form class="m-form m-form--fit m-form--group-seperator-dashed" id="frm_neumologia" name="frm_neumologia">

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

            <div id="_div_add_documents">
               <input type="hidden" value='<?=$num_expediente?>' id="num_expediente" name="num_expediente">
               <input type="hidden" value='<?=$id_persona?>' id="id_persona" name="id_persona">
            </div>
            <div class="m-section">

              <div class="m-form__group form-group row">
                <label for="neu_normal" class="col-5 col-form-label">
                  Normal:
                </label>
                <div class="col-7" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="neu_normal">
                      <span></span>
                    </label>
                  </span>
                </div>
              </div>
              <div class="form-group m-form__group row">
                <label for="neu_radiografica" class="col-5 col-form-label">
                  Descripción Radiográfica:
                </label>
                <div class="col-7">
                  <textarea maxlength="500" class="form-control m-input m-input--air" id="neu_radiografica" name="neu_radiografica" rows="3"></textarea>
                </div>
              </div>
              <div class="m-form__group form-group row">
                <label for="neu_tsuperficiales" class="col-5 col-form-label">
                  Tejidos Blandos Superficiales Normales:
                </label>
                <div class="col-7" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="neu_tsuperficiales">
                      <span></span>
                    </label>
                  </span>
                </div>
              </div>
              <div class="m-form__group form-group row">
                <label for="neu_oseas" class="col-5 col-form-label">
                  Partes Oseas sin Alteraciones:
                </label>
                <div class="col-7" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="neu_oseas">
                      <span></span>
                    </label>
                  </span>
                </div>
              </div>
              <div class="m-form__group form-group row">
                <label for="neu_morfologia" class="col-5 col-form-label">
                    Hemidiafragmas de Morfologia Normal:
                </label>
                <div class="col-7" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="neu_morfologia">
                      <span></span>
                    </label>
                  </span>
                </div>
              </div>
              <div class="m-form__group form-group row">
                <label for="neu_mediastino" class="col-5 col-form-label">
                  Mediastino y Silueta Cardiaca sin Datos Anormales:
                </label>
                <div class="col-7" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="neu_mediastino">
                      <span></span>
                    </label>
                  </span>
                </div>
              </div>
              <div class="m-form__group form-group row">
                <label for="neu_bilaterales" class="col-5 col-form-label">
                  Calcificaciones Hiliares Bilaterales:
                </label>
                <div class="col-7" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="neu_bilaterales">
                      <span></span>
                    </label>
                  </span>
                </div>
              </div>
              <div class="m-form__group form-group row">
                <label for="neu_pulmonares" class="col-5 col-form-label">
                  Campos Pulmonares sin Datos Relevantes:
                </label>
                <div class="col-7" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="neu_pulmonares">
                      <span></span>
                    </label>
                  </span>
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
                    <div class="m-dropzone dropzone"  id="neumo_img" data-routeupfile="usuarios/upload_dropzone/examenes|neumologia|<?=$num_expediente?>/upload_avatar">
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
  id_persona = $('#id_persona_left').val();
  num_expediente = $('#id_numexp_left').val();

  $('#neumo_img').dropzone({
    url: $("#neumo_img").data('routeupfile'),
    paramName: "file",
    maxFiles: 5,
    maxFilesize: 5, // MB
    acceptedFiles: "image/*,application/pdf",
    accept: function(file, done) {
        console.log(file);
        done();
    },
    init: function() {
      this.on("success", function(statics,file) {
        var img = file.split("|");
        var num_exp = $("#num_expediente").val();
        $( "#_div_add_documents" ).append("<input type='hidden' name='neumoimage[]' value='examenes/neumologia/"+num_exp+"/"+img[1]+"'>");
      });
    }
   });
});
</script>
<script src="<?=URL_PUBLIC?>assets/js/validaciones/rayosx/valida_neumologia.js"></script>
     