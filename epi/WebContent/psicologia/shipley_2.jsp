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
        <b>RAMA SHIPLEY-2</b>
    </div>
</div>
<form class="m-form" name="frm_shipley" id="frm_shipley">

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

          <div class="table-responsive">
            <table class="table table-striped table-bordered" cellspacing="8" width="">
              <thead>
                <tr>
                  <th>Shipley-2</th>
                  <th>Puntuación Natural</th>
                  <th>Puntuación Estandar</th>
                  <th>Rango Percentil</th>
                  <th>Descripción</th>
                </tr>
              </thead>
            <tbody>
              <tr>
                <td>Vocabulario</td>
                  <td>
                    <input class="form-control" type="number" name="shi_vocabulario_natural">
                  </td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_vocabulario_estandar">
                  </td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_vocabulario_percentil">
                  </td>
                  <td>
                    <textarea class="form-control" name="shi_vocabulario_des" id="shi_vocabulario_des" rows="2" maxlength="500"  data-set_max_word="500"></textarea>
                  </td>
                </tr>
                <tr>
                  <td>Abstracción</td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_abstraccion_natural">
                  </td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_abstraccion_estandar">
                  </td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_abstraccion_percentil">
                  </td>
                  <td>
                    <textarea maxlength="500" class="form-control" name="shi_abstraccion_des" rows="3" data-set_max_word="500"></textarea>
                  </td>
                </tr>
                <tr>
                  <td>Bloques</td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_bloques_natural">
                  </td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_bloques_estandar">
                  </td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_bloques_percentil">
                  </td>
                  <td>
                    <textarea maxlength="500" class="form-control" name="shi_bloques_des" rows="3" data-set_max_word="500"></textarea>
                  </td>
                </tr>
                <tr>
                  <td>Combinación A</td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_ca_natural">
                  </td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_ca_estandar">
                  </td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_ca_percentil">
                  </td>
                  <td>
                    <textarea maxlength="500" class="form-control" name="shi_ca_des" rows="3" data-set_max_word="500"></textarea>
                  </td>
                </tr>
                <tr>
                  <td>Combinación B</td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_cb_natural">
                  </td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_cb_estandar">
                  </td>
                  <td>
                    <input class="form-control m-input" type="number" name="shi_cb_percentil">
                  </td>
                  <td>
                    <textarea maxlength="500" class="form-control" id="shi_cb_des" name="shi_cb_des" rows="3" data-set_max_word="500"></textarea>
                  </td>
                </tr>
              </tbody>
            </table>
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
<script src="<%=vParametros4.getPropEspecifica("URL_APP")%>assets/js/validaciones/psicologia/valida_shipley.js"></script>
    