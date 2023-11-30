<div class="form-group m-form__group">
  <div class="alert m-alert m-alert--default" role="alert">
    <b>EXAMEN GENERAL DE ORINA</b>
  </div>
</div>

<ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
   <li class="nav-item m-tabs__item">
      <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
      Examen Físico
      </a>
   </li>
   <li class="nav-item m-tabs__item">
      <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_2" role="tab">
      Examen Químico
      </a>
   </li>
   <li class="nav-item m-tabs__item">
      <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_3" role="tab">
      Sedimento Urinario
      </a>
   </li>
</ul>

<form class="m-form m-form--fit m-form--group-seperator-dashed" name="frm_ego" id="frm_ego">
	<div class="tab-content">

    <!-- INICIA Examen Físicoa -->

    <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
      <div class="form-group m-form__group row" align="center">
        <label for="qclinica_glucosa" class="col-2 col-form-label">
          Color:
        </label>
        <div class="col-9">
          <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_color" name="ego_color" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_color']); ?></textarea>
        </div>
      </div>

      <div class="form-group m-form__group row" align="center">
        <label for="ego_aspecto" class="col-2 col-form-label">
          Aspecto:
        </label>
        <div class="col-9">
          <textarea maxlength="500" class="form-control m-input m-input--air" name="ego_aspecto" id="ego_aspecto" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_aspecto']); ?></textarea>
        </div>
      </div>

      <div class="form-group m-form__group row" align="center" align="center">
        <label for="ego_densidad" class="col-2 col-form-label">
            Densidad:
        </label>
        <div class="col-4">
          <input type="number" class="form-control m-input" name="ego_densidad" disabled value="<?=$datos_ego[0]['ego_densidad']?>">
        </div>
        <div class="col-5" align="left">
          <div class="alert m-alert m-alert--default" role="alert">
            1.010 - 1.030
          </div>
        </div>
      </div>

      <div class="form-group m-form__group row" align="center">
        <label for="ego_ph" class="col-2 col-form-label">
            PH:
        </label>
        <div class="col-4">
          <input class="form-control m-input" type="number" id="ego_ph" name="ego_ph" disabled value="<?=$datos_ego[0]['ego_ph']?>">
        </div>
        <div class="col-5" align="left">
          <div class="alert m-alert m-alert--default" role="alert">
            5.0 - 7.5
          </div>
        </div>
      </div>
    </div>

    <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
      <div class="m-section">
        <div class="form-group m-form__group row" align="center">
          <label for="ego_eq_leucocitos" class="col-2 col-form-label">
            Leucocitos:
          </label>
          <div class="col-4" align="center">
            <span class="m-switch m-switch--sm m-switch--icon">
              <label>
                <input type="checkbox" name="ego_leucocitosego_eq_leucocitos" id="ego_leucocitosego_eq_leucocitos" disabled value="<?=$datos_ego[0]['ego_leucocitosego_eq_leucocitos']?>">
                <span></span>
              </label>
            </span>
          </div>
        </div>
        <div class="form-group m-form__group row" align="center">
          <label for="ego_leucocitos_valor" class="col-2 col-form-label">
            Valor Leucocitos:
          </label>
          <div class="col-4">
            <input type="number" class="form-control" name="ego_leucocitos_valor" disabled value="<?=$datos_ego[0]['ego_leucocitos_valor']?>">
          </div>
          <div class="col-6" align="left">
            <div class="alert m-alert m-alert--default" role="alert">
              0 - 10 leu/ÂµL
            </div>
          </div>
        </div>

        <div class="form-group m-form__group row">
          <div class="col-2" align="center">
            <label for="ego_nitritos" class="col-form-label">
              Nitritos:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_nitritos" name="ego_nitritos" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_nitritos']); ?></textarea>
          </div>
          <div class="col-2">
            <label for="ego_proteinas" class="col-2 col-form-label">
              Proteínas:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" name="ego_proteinas" id="ego_proteinas" rows="2"><?php echo ucwords($datos_ego[0]['ego_proteinas']); ?></textarea>
          </div>
        </div>

        <div class="form-group m-form__group row">
          <label for="ego_proteinas_valor" class="col-2 col-form-label">
            Valor Proteínas:
          </label>
          <div class="col-4">
            <input type="number" class="form-control" name="ego_proteinas_valor" disabled value="<?=$datos_ego[0]['ego_proteinas_valor']?>">
          </div>
          <div class="col-6">
            <div class="alert m-alert m-alert--default" role="alert">
               mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0 - 10
            </div>
          </div>
        </div>

        <div class="form-group m-form__group row">
          <label for="ego_glucosa" class="col-2 col-form-label">
            Glucosa:
          </label>
          <div class="col-10">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_glucosa" name="ego_glucosa" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_glucosa']); ?></textarea>
          </div>
        </div>

      <div class="form-group m-form__group row">
        <label for="ego_glucosa_valor" class="col-2 col-form-label">
          Valor Glucosa:
        </label>
        <div class="col-4">
          <input type="number" class="form-control" name="ego_glucosa_valor" disabled value="<?=$datos_ego[0]['ego_glucosa_valor']?>">
        </div>
        <div class="col-6">
          <div class="alert m-alert m-alert--default" role="alert">
             mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0 - 10
          </div>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="ego_ccetonicos" class="col-2 col-form-label">
            Cuerpos Cetonicos:
          </label>
        <div class="col-10">
          <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_ccetonicos" name="ego_ccetonicos" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_ccetonicos']); ?></textarea>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="ego_ccetonicos_valor" class="col-2 col-form-label">
          Valor Cuerpos Cetonicos:
        </label>
        <div class="col-4">
          <input type="number" class="form-control" name="ego_ccetonicos_valor" disabled value="<?=$datos_ego[0]['ego_ccetonicos_valor']?>">
        </div>
        <div class="col-5">
          <div class="alert m-alert m-alert--default" role="alert">
            mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0 - 4
          </div>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="ego_estercobilinogeno" class="col-2 col-form-label">
            Estercobilinógeno:
        </label>
        <div class="col-10">
          <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_estercobilinógeno" name="ego_estercobilinógeno" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_estercobilinógeno']); ?></textarea>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="ego_estercobilinógeno_valor" class="col-2 col-form-label">
          Valor Estercobilinógeno:
        </label>
        <div class="col-4">
          <input type="number" class="form-control" name="ego_estercobilinógeno_valor" disabled value="<?=$datos_ego[0]['ego_estercobilinógeno_valor']?>">
        </div>
        <div class="col-6">
          <div class="alert m-alert m-alert--default" role="alert">
            mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0.0 - 0.2
          </div>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="ego_bilirrubina" class="col-2 col-form-label">
            Bilirrubina:
          </label>
        <div class="col-10">
          <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_bilirrubina" name="ego_bilirrubina" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_bilirrubina']); ?></textarea>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="ego_bilirrubina_valor" class="col-2 col-form-label">
          Valor Bilirrubina:
        </label>
        <div class="col-4">
          <input type="number" class="form-control" name="ego_bilirrubina_valor" disabled value="<?=$datos_ego[0]['ego_bilirrubina_valor']?>">
        </div>
        <div class="col-6">
          <div class="alert m-alert m-alert--default" role="alert">
             mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0.0 - 0.2
          </div>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="ego_hemoglobina" class="col-2 col-form-label">
            Hemoglobina:
        </label>
        <div class="col-10">
          <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_hemoglobina" name="ego_hemoglobina" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_hemoglobina']); ?></textarea>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="ego_hemoglobina_valor" class="col-2 col-form-label">
          Valor Hemoglobina:
        </label>
        <div class="col-4">
          <input type="number" class="form-control" name="ego_hemoglobina_valor" disabled value="<?=$datos_ego[0]['ego_hemoglobina_valor']?>">
        </div>
        <div class="col-6">
          <div class="alert m-alert m-alert--default" role="alert">
            mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0.00 - 0.05
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="tab-pane" id="m_tabs_6_3" role="tabpanel">
    <div class="m-section">
      <div class="form-group m-form__group row">
          <div class="col-2" align="center">
            <label for="ego_leucocitos" class="col-form-label">
                Leucocitos:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_leucocitos" name="ego_leucocitos" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_leucocitos']); ?></textarea>
          </div>
          <div class="col-2">
            <label for="ego_eritrocitos" class="col-form-label">
                Eritrocitos:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_eritrocitos" name="ego_eritrocitos" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_eritrocitos']); ?></textarea>
          </div>
        </div>

        <div class="form-group m-form__group row">
          <div class="col-2" align="center">
            <label for="ego_cepiteliales" class="col-form-label">
                Celulas Epiteliales:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_cepiteliales" name="ego_cepiteliales" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_cepiteliales']); ?></textarea>
          </div>
          <div class="col-2">
            <label for="ego_fmucoide" class="col-form-label">
                Filamento Mucoide:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_fmucoide" name="ego_fmucoide" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_fmucoide']); ?></textarea>
          </div>
        </div>
        <div class="form-group m-form__group row">
          <div class="col-2" align="center">
            <label for="ego_caurico" class="col-form-label">
                Cristales de Ácido Úrico:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_caurico" name="ego_caurico" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_caurico']); ?></textarea>
          </div>
          <div class="col-2">
            <label for="ego_ocalcio" class="col-form-label">
              Oxalato De Calcio:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_ocalcio" name="ego_ocalcio" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_ocalcio']); ?></textarea>
          </div>
        </div>

        <div class="form-group m-form__group row">
          <div class="col-2" align="center">
            <label for="ego_famorfo" class="col-form-label">
              Fosfato Amorfo:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_famorfo" name="ego_famorfo" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_famorfo']); ?></textarea>
          </div>
          <div class="col-2">
            <label for="ego_uamorfo" class="col-form-label">
              Urato Amorfo:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_uamorfo" name="ego_uamorfo" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_uamorfo']); ?></textarea>
          </div>
        </div>

        <div class="form-group m-form__group row">
          <div class="col-2" align="center">
            <label for="ego_cilindros" class="col-form-label">
              Cilindros:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_cilindros" name="ego_cilindros" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_cilindros']); ?></textarea>
          </div>
          <div class="col-2">
            <label for="ego_obs" class="col-form-label">
              Observaciones:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="ego_obs" name="ego_obs" rows="2" disabled><?php echo ucwords($datos_ego[0]['ego_obs']); ?></textarea>
          </div>
        </div>

        <div class="form-group m-form__group row">
          <div class="col-2" align="center">
            <label for="ego_metodo" class="col-form-label">
                Método:
            </label>
          </div>
          <div class="col-4">
            <textarea maxlength="500" class="form-control m-input m-input--air" id="surinario_metodo" name="surinario_metodo" rows="2" disabled><?php echo ucwords($datos_ego[0]['surinario_metodo']); ?></textarea>
          </div>
        </div>

      </div>
    </div>
  </div>
</form>
