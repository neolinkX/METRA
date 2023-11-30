<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamAplica"%>
<%  
TEntorno    vEntorno4      = new TEntorno();
vEntorno4.setNumModulo(07);
TParametro  vParametros4 = new TParametro(vEntorno4.getNumModuloStr());
%>

  <div class="form-group m-form__group">
    <div class="alert m-alert m-alert--default" role="alert">
        <b>RAMA NEUROPSI</b>
    </div>
</div>
<form class="m-form" name="frm_neuropsi" id="frm_neuropsi">

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

    <div class="col-md-12">
      <div class="m-portlet__body">
        <div class="m-section">
            <div class="row">
              <label for="neuro_otiempo" class="col-2 col-form-label">
                Orientación Tiempo:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_otiempo">
              </div>
              <label for="neuro_olugar" class="col-2 col-form-label">
                Orientación Lugar:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_olugar">
              </div>
              <label for="neuro_opersona" class="col-2 col-form-label">
                Orientación Persona:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_opersona">
              </div>
            </div>
            <div class="row">
              <label for="neuro_digitos" class="col-2 col-form-label">
                Atención y Concentración Digitos:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_digitos">
              </div>
              <label for="neuro_visual" class="col-2 col-form-label">
                Atención y Concentración Detección Visual:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_visual">
              </div>
              <label for="neuro_20_3" class="col-2 col-form-label">
                Atención y Concentración 20 - 3:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_20_3">
              </div>
            </div>
            <div class="row">
              <label for="neuro_cpalabras" class="col-2 col-form-label">
                Memoria Codificación Palabras:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_cpalabras">
              </div>
              <label for="neuro_msemicompleja" class="col-2 col-form-label">
                Memoria Codificación Figura Semicompleja:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_msemicompleja">
              </div>
              <label for="neuro_mespontanea" class="col-2 col-form-label">
                Memoria Evocación Espontánea:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_mespontanea">
              </div>
            </div>
            <div class="row">
              <label for="mcategorias" class="col-2 col-form-label">
                Memoria Evocación por Categorías:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="mcategorias">
              </div>
              <label for="neuro_mreconocimiento" class="col-2 col-form-label">
                  Memoria Evocación Reconocimiento:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_mreconocimiento">
              </div>
              <label for="neuro_mevocacion_semicompleja" class="col-2 col-form-label">
                Memoria Evocación Figura Semicompleja:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_mevocacion_semicompleja">
              </div>
            </div>
            <div class="row">
              <label for="neuro_ldenominacion" class="col-2 col-form-label">
                Lenguaje Denominación:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_ldenominacion">
              </div>
              <label for="neuro_lrepeticion" class="col-2 col-form-label">
                Lenguaje Repetición:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_lrepeticion">
              </div>
              <label for="neuro_lcomprension" class="col-2 col-form-label">
                Lenguaje Comprensión:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_lcomprension">
              </div>
            </div>
            <div class="row">
              <label for="neuro_lsemantica" class="col-2 col-form-label">
                Lenguaje Fluidez Verbal Semántica:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_lsemantica">
              </div>
              <label for="neuro_lfonologia" class="col-2 col-form-label">
                Lenguaje Fluidez Verbal Fonológica:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_lfonologia">
              </div>
              <label for="neuro_lectura" class="col-2 col-form-label">
                Lectura:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_lectura">
              </div>
            </div>
            <div class="row">
              <label for="neuro_dictado" class="col-2 col-form-label">
                Dictado:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_dictado">
              </div>
              <label for="neuro_copiado" class="col-2 col-form-label">
                Copiado:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_copiado">
              </div>
              <label for="neuro_semejanzas" class="col-2 col-form-label">
                Funciones Ejecutivas Semejanzas:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_semejanzas">
              </div>
            </div>
            <div class="row">
              <label for="neuro_calculo" class="col-2 col-form-label">
                Funciones Ejecutivas Cálculo:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_calculo">
              </div>
              <label for="neuro_secuenciacion" class="col-2 col-form-label">
                Funciones Ejecutivas Secuenciación:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_secuenciacion">
              </div>
              <label for="neuro_mano_derecha" class="col-2 col-form-label">
                Funciones Ejecutivas Mano Derecha:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_mano_derecha">
              </div>
            </div>
            <div class="row">
              <label for="neuro_mano_izquierda" class="col-2 col-form-label">
                Funciones Ejecutivas Mano Izquierda:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_mano_izquierda">
              </div>
              <label for="neuro_malternos" class="col-2 col-form-label">
                Funciones Ejecutivas Movimientos Alternos:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_malternos">
              </div>
              <label for="neuro_ropuestas" class="col-2 col-form-label">
                Funciones Ejecutivas Reacciones Opuestas:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_ropuestas">
              </div>
            </div>
            <div class="row">
              <label for="neuro_aestudio" class="col-2 col-form-label">
                Años de Estudio:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_aestudio">
              </div>
              <label for="neuro_ptotal" class="col-2 col-form-label">
                Puntaje Total:
              </label>
              <div class="form-group m-form__group col-2">
                <input class="form-control m-input" type="number" name="neuro_ptotal">
              </div>
            </div>
            <div class="row">
              <label for="neuro_interpretacion" class="col-2 col-form-label">
                Interpretación:
              </label>
              <div class="form-group m-form__group col-10">
                <textarea maxlength="2000" class="form-control" name="neuro_interpretacion" id="neuro_interpretacion" rows="3"></textarea>
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
</form>
<script src="<%=vParametros4.getPropEspecifica("URL_APP")%>assets/js/validaciones/psicologia/valida_neuropsi.js"></script>
 