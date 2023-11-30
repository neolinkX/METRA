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
<div class="form-group m-form__group">
  <div class="alert m-alert m-alert--default" role="alert">
    <b>HEMATOLOG&Iacute</>A</b>
  </div>
</div>

<form class="m-form m-form--fit m-form--group-seperator-dashed" name="frm_hematologia" id="frm_hematologia">
  <div class="tab-content">

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

    <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
      <div class="form-group m-form__group row" align="center">
        <label for="hem_tipo_sanguineo" class="col-2 col-form-label">
          Tipo Sanguineo:
        </label>
        <div class="col-10">
          <textarea maxlength="500" class="form-control m-input m-input--air" id="hem_tipo_sanguineo" name="hem_tipo_sanguineo" rows="2"></textarea>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="hem_frh" class="col-2 col-form-label">
          Factor RH:
        </label>
        <div class="col-4" align="center">
          <span class="m-switch m-switch--sm m-switch--icon">
            <label>
              <input name="hem_frh" type="checkbox">
              <span></span>
            </label>
          </span>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="hem_obs" class="col-2 col-form-label">
          Observaciones:
        </label>
        <div class="col-10">
          <textarea maxlength="500" class="form-control m-input m-input--air" id="hem_obs" name="hem_obs" rows="2"></textarea>
        </div>
      </div>
    </div>
  </div>

  <div class="m-portlet__foot m-portlet__foot--fit">
    <div class="m-form__actions">
      <button type="subit" class="btn btn-primary">
        Guardar
      </button>
      <button type="reset" class="btn btn-secondary">
        Limpiar
      </button>
    </div>
  </div>

</form>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/validaciones/laboratorio/valida_hemologia.js"></script>
