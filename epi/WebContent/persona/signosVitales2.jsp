<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.*"%>    
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>
<%  
TEntorno    vEntorno      = new TEntorno();
vEntorno.setNumModulo(07);
TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
String CodigoValida = "";
%>    

<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
  <div class="m-portlet__head" style="background:#f7f8fa">
    <div class="m-portlet__head-caption">
     <div class="m-portlet__head-title">
        <span class="m-portlet__head-icon">
        <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/signos_vitales.svg" width="25" class="svg-inject signal-strong">
        </span>
        <h3 class="m-portlet__head-text m--font-primary">
           Signos Vitales y Somatometría
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
   <form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed" id="frm_svs" name="frm_svs">
      <div class="m-portlet__body"><br>

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






          <div class="row">
             <div class="form-group col-md-3">
                <label for="svs_presion_sistolica">
                <?=$q_signos['signos_campo1']['label'];?>
                </label>
                <input type="text" class="form-control m-input" id="svs_presion_sistolica" name="svs_presion_sistolica" maxlength="3" data-number>
                <span>
                <?=$q_signos['signos_campo1']['form_help'];?>
                </span>
             </div>
             <div class="form-group col-md-3">
                <label for="svs_presion_diastolica">
                <?=$q_signos['signos_campo2']['label'];?>
                </label>
                <input type="text" class="form-control m-input" id="svs_presion_diastolica" name="svs_presion_diastolica" maxlength="3" data-number>
                <span >
                <?=$q_signos['signos_campo2']['form_help'];?>
                </span>
             </div>
             <div class="form-group col-md-3">
                <label for="svs_frec_cardiaca">
                <?=$q_signos['signos_campo3']['label'];?>
                </label>
                <input type="text" class="form-control m-input" id="svs_frec_cardiaca" name="svs_frec_cardiaca" maxlength="3" data-number>
                <span>
                <?=$q_signos['signos_campo3']['form_help'];?>
                </span>
             </div>
             <div class="form-group col-md-3">
                <label for="svs_frec_respiratoria">
                <?=$q_signos['signos_campo4']['label'];?>
                </label>
                <input type="text" class="form-control m-input" id="svs_frec_respiratoria" name="svs_frec_respiratoria" maxlength="2" data-number>
                <span>
                <?=$q_signos['signos_campo4']['form_help'];?>
                </span>
             </div>
          </div>
          <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

          <div class="row">
            <div class="form-group col-md-3">
               <label for="svs_temperatura">
               <?=$q_signos['signos_campo7']['label'];?>
               </label>
               <input type="text" class="form-control m-input" id="svs_temperatura" name="svs_temperatura" data-number_dec>
               <span>
               <?=$q_signos['signos_campo7']['form_help'];?>
               </span>
            </div>
             <div class="form-group col-md-3">
                <label for="svs_peso">
                <?=$q_signos['signos_campo5']['label'];?>
                </label>
                <input type="text" onchange="calcula_imc();" class="form-control m-input" id="svs_peso" name="svs_peso"  data-number_dec>
                <span>
                <?=$q_signos['signos_campo5']['form_help'];?>
                </span>
             </div>
             <div class="form-group col-md-3">
                <label for="svs_estatura">
                <?=$q_signos['signos_campo6']['label'];?>
                </label>
                <input type="text" onchange="calcula_imc();" class="form-control m-input" id="svs_estatura" name="svs_estatura" data-number maxlength="3">
                <span>
                <?=$q_signos['signos_campo6']['form_help'];?>
                </span>
             </div>

             <div class="form-group col-md-3">
                <label for="svs_imc">
                <?=$q_signos['signos_campo8']['label'];?>
                </label>
                <input type="text" class="form-control m-input" id="svs_imc" name="svs_imc" readonly>
                <span>
                <?=$q_signos['signos_campo8']['form_help'];?>
                </span>
             </div>
          </div>
          <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
          <div class="row">
             <div class="form-group col-md-3">
                <label for="svs_grasa">
                <?=$q_signos['signos_campo9']['label'];?>
                </label>
                <input type="text" class="form-control m-input" id="svs_grasa" name="svs_grasa" data-number_dec>
                <span>
                <?=$q_signos['signos_campo9']['form_help'];?>
                </span>
             </div>
             <div class="form-group col-md-3">
                <label for="svs_cintura">
                <?=$q_signos['signos_campo10']['label'];?>
                </label>
                <input type="text" class="form-control m-input" id="svs_cintura" name="svs_cintura" data-number_dec maxlength="5">
                <span>
                <?=$q_signos['signos_campo10']['form_help'];?>
                </span>
             </div>
             <div class="form-group col-md-3">
                <label for="svs_cuello">
                <?=$q_signos['signos_campo11']['label'];?>
                </label>
                <input type="text" class="form-control m-input" id="svs_cuello" name="svs_cuello" data-number_dec maxlength="5">
                <span>
                <?=$q_signos['signos_campo11']['form_help'];?>
                </span>
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
            Cancel
            </button>
         </div>
      </div>
   </form>
</div>

<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/validaciones/signos_vitales/valida_svs.js"></script>
<script>
function guarda_signos_vitales(){

  var options = {type:'question',title:'¿Desea guardar Signos Vitales?'}
  modalConfirm(function (){
    $.ajax({
      //url: 'http://10.33.143.52:7001/epi/somatometria/guarda_signosVitales/'+$('#id_episodio_left').val(),
      url: 'http://10.33.143.52:7001/epi/somatometria/guarda_signosVitales.jsp,
      type: 'POST',
      data: $("#frm_svs").serialize(),
      dataType: 'json',
      success: function(resp_success){
        swal('Se guardó correctamente la información!', '', "success");
        get_signosVitales();
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PER-06');}
    });
  },options);
}
$("[data-number_dec]").inputmask("decimal", { min: 0, max: 350, allowMinus: false, digits: 1, integerDigits: 3 });
/*$("[data-number_dec]").inputmask({
   mask: "(999)|(999.9{1})|(99.9{1})",
  'alias': 'decimal',
   rightAlign: true,
   'groupSeparator': '.',
   'autoGroup': true
 });*/ 
  
</script>
