<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
  <div class="m-portlet__body">
      <div class="form-group m-form__group">
        <div class="alert m-alert m-alert--default" role="alert">
          <b>ANTECEDENTES PATOLÓGICOS</b>
        </div>
      </div>
      <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
            <i class="flaticon-user-ok" aria-hidden="true"></i>
            Antecedentes
            </a>
         </li>
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_2" role="tab">
            <i class="flaticon-folder-1" aria-hidden="true"></i>
            NOM-028-SSA2-1999
            </a>
         </li>
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_3" role="tab">
            <i class="flaticon-danger" aria-hidden="true"></i>
            Tabaquismo
            </a>
         </li>
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_4" role="tab">
            <i class="flaticon-exclamation-1" aria-hidden="true"></i>
            Consumo de Drogas
            </a>
         </li>
      </ul>
      <form id="frm_patologicos" name="frm_patologicos" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
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
      <div class="tab-content">
         <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
            <!---------- INICIA ANTECEDENTES -------->

            <div class="m-portlet__body">
              <div class="row">
                <label for="pat_ant_econgenita" class="col-lg-3 col-form-label">
                  Antecedente de Enfermedad Congenita:
                </label>
                <div class="form-group m-form__group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="pat_ant_econgenita" id="pat_ant_econgenita" value="1" data-enable_input="pat_ant_econgenita_des">
                      <span></span>
                    </label>
                  </span>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_ant_econgenita_des" name="pat_ant_econgenita_des" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especifique diagnóstico y tratamiento (tiempo)
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_alergias" class="col-lg-3 col-form-label">
                  Historia Alergia(s):
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                    <select name="pat_alergias" id="pat_alergias" class="form-control m-select2 styleSelect2" data-placeholder="Seleccione" data-block_by_id="pat_alergias_des" data-value_block='107'>
                      <?=$catalog->selectCatalog($this->help,'historia_alergia',null);?>
                    </select>
                  </div>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_alergias_des" name="pat_alergias_des" rows="2" data-set_max_word="200"></textarea>
                  <span class="m-form__help">
                    Especifique alergias
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_tmemoria" class="col-lg-3 col-form-label">
                  Transtorno de la Memoria:
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                    <select class="form-control m-select2 styleSelect2" id="pat_tmemoria"  name="pat_tmemoria" data-placeholder="Seleccione" onchange="tmemoria();">
                     <?=$catalog->selectCatalog($this->help,'trans_memoria',null);?>
                    </select>
                  </div>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_tmemoria_especifique" name="pat_tmemoria_especifique" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especifique diagnóstico(año) y tratamiento(tiempo) pérdida de la memoria:
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_epulmunar" class="col-lg-3 col-form-label">
                  Historia de enfermedad pulmonar obstructiva crónica (EPOC):
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                    <select class="form-control m-select2 styleSelect2" id="pat_epulmunar"  name="pat_epulmunar" data-placeholder="Seleccione">
                      <?=$catalog->selectCatalog($this->help,'epoc',null);?>
                    </select>
                  </div>
                </div>
              </div>
              <div class="row">
                <label for="pat_aquirurgicos" class="col-lg-3 col-form-label">
                  Antecedentes Quirúrgicos:
                </label>
                <div class="form-group m-form__group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="pat_aquirurgicos" id="pat_aquirurgicos" value="1" onclick="habilita_dependiente_check('pat_quirurgicos_tratamiento','pat_aquirurgicos');">
                      <span></span>
                    </label>
                  </span>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_quirurgicos_tratamiento" name="pat_quirurgicos_tratamiento" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especifique Diagnóstico y Tratamiento de Antecedentes Quirúrgicos
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_atransfusionales" class="col-lg-3 col-form-label">
                  Antecedentes Transfusionales:
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                    <select class="form-control m-select2 styleSelect2" id="pat_atransfusionales" name="pat_atransfusionales" data-placeholder="Seleccione" data-block_by_id="pat_atransfusionales_tratamiento" data-value_block='125'>
                      <?=$catalog->selectCatalog($this->help,'ant_transfunc',null);?>
                    </select>
                  </div>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_atransfusionales_tratamiento" name="pat_atransfusionales_tratamiento" data-set_max_word="200" rows="2" disabled></textarea>
                  <span class="m-form__help">
                    Especifique Diagnóstico y Tratamiento de Antecedentes Transfusionales
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_diabetesm" class="col-lg-3 col-form-label">
                  Diabetes Mellitus:
                </label>
                <div class="form-group m-form__group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="pat_diabetesm" data-enable_input="pat_diabetesm_tratamiento">
                      <span></span>
                    </label>
                  </span>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_diabetesm_tratamiento" name="pat_diabetesm_tratamiento" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especifique diagnóstico y tratamiento (tiempo) Diabetes Mellitus:
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_insulinodependiente" class="col-lg-3 col-form-label">
                  Insulinodependiente:
                </label>
                <div class="form-group m-form__group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="pat_insulinodependiente" data-enable_input="pat_insulinodependiente_tratamiento">
                      <span></span>
                    </label>
                  </span>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_insulinodependiente_tratamiento" name="pat_insulinodependiente_tratamiento" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especifique diagnóstico y tratamiento (tiempo) Insulinodependiente:
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_dasma" class="col-lg-3 col-form-label">
                  Diagnóstico de Asma:
                </label>
                <div class="form-group m-form__group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="pat_dasma" id="pat_dasma" value="1" onclick="habilita_dependiente_check('pat_dasma_des','pat_dasma');">
                      <span></span>
                    </label>
                  </span>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_dasma_des" name="pat_dasma_des" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especifique diagnóstico (año) y tratamiento asma
                  </span>
                </div>
              </div>
              <div class="row">
                <label class="col-lg-3 col-form-label">
                  Antecedentes de Traumatismo:
                </label>
                <div class="form-group m-form__group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="pat_traumatismo" id="pat_traumatismo" value="1" onclick="habilita_dependiente_check('pat_traumatismo_des','pat_traumatismo');">
                      <span></span>
                    </label>
                  </span>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_traumatismo_des" name="pat_traumatismo_des" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especifique diagnóstico (año), tratamiento y secuelas de traumatismo
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_crisis" class="col-lg-3 col-form-label">
                  Crisis Convulsivas:
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                    <select class="form-control m-select2 styleSelect2" id="pat_crisis"  name="pat_crisis" data-placeholder="Seleccione" data-block_by_id="pat_crisis_tratamiento" data-value_block='142'>
                      <?=$catalog->selectCatalog($this->help,'crisis_convulsivas',null);?>
                    </select>
                  </div>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_crisis_tratamiento" name="pat_crisis_tratamiento" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especifique diagnóstico y tratamiento (tiempo) Crisis Convulsivas:
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_oncologicos" class="col-lg-3 col-form-label">
                  Antecedentes Oncologicos:
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                    <select class="form-control m-select2 styleSelect2" id="pat_oncologicos" name="pat_oncologicos" data-placeholder="Seleccione" data-block_by_id="pat_oncologicos_tratamiento" data-value_block='144'>
                      <?=$catalog->selectCatalog($this->help,'ant_oncologicos',null);?>
                    </select>
                  </div>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_oncologicos_tratamiento" name="pat_oncologicos_tratamiento" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especifique diagnóstico y tratamiento (tiempo) Antecedentes Oncologicos:
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_cardiopatia" class="col-lg-3 col-form-label">
                  Antecedente de Cardiopatia:
                </label>
                <div class="form-group m-form__group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="pat_cardiopatia" id="pat_cardiopatia" value="1" onclick="habilita_dependiente_check('pat_cardiopatia_tratamiento','pat_cardiopatia');">
                      <span></span>
                    </label>
                  </span>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_cardiopatia_tratamiento" name="pat_cardiopatia_tratamiento" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especifique Diagnóstico (año) y tratamiento cardiopatías
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_hipertension" class="col-lg-3 col-form-label">
                  Hipertensión Arterial:
                </label>
                <div class="form-group m-form__group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="pat_hipertension" id="pat_hipertension" value="1" onclick="habilita_dependiente_check('pat_hipertension_des','pat_hipertension');">
                      <span></span>
                    </label>
                  </span>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_hipertension_des" name="pat_hipertension_des" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especifique diagnóstico (año) y tratamiento de hipertensión
                  </span>
                </div>
              </div>
              <div class="row">
                <label for="pat_infectocontagiosos" class="col-lg-3 col-form-label">
                  Antecedentes infectoconagiosos relevantes (Tuberculosis, VIH, Hepatitis, etc):
                </label>
                <div class="form-group m-form__group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="pat_infectocontagiosos" id="pat_infectocontagiosos" value="1" onclick="habilita_dependiente_check('pat_infectocontagiosos_des','pat_infectocontagiosos');">
                      <span></span>
                    </label>
                  </span>
                </div>
                <div class="form-group m-form__group col-lg-6">
                  <textarea maxlength="200" class="form-control" id="pat_infectocontagiosos_des" name="pat_infectocontagiosos_des" rows="2" data-set_max_word="200" disabled></textarea>
                  <span class="m-form__help">
                    Especiique diagnóstico y tratamiento infectocontagiosas
                  </span>
                </div>
              </div>
            </div>
        </div>
        <!---------- TERMINA ANTECEDENTES -------->
        <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">

            <!---------- INICIA NOM -------->

            <div class="m-portlet__body">
              <div class="row">
                <label for="pat_bebidas_alcoholicas" class="col-3 col-form-label">
                  ¿Que tan frecuente ingiere bebibas alcohólicas?
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                 <select class="form-control m-select2 styleSelect2"  id="pat_bebidas_alcoholicas" name="pat_bebidas_alcoholicas" data-placeholder="Seleccione">
                  <!--  <?=$catalog->selectCatalog($this->help,'freq_alcohol',null);?>  -->
                 </select>
                 </div> 
                 </div> 

                <label for="pat_copas_dia" class="col-lg-3 col-form-label">
                  ¿Cuántas copas se toma en un día típico de los que bebe?
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                      <select class="form-control m-select2 styleSelect2" id="pat_copas_dia"  name="pat_copas_dia" data-placeholder="Seleccione">
                        <!--<?=$catalog->selectCatalog($this->help,'copas_dia_tipico',null);?>-->
                      </select>
                  </div>
                </div>
              </div>

              <div class="row">
                <label for="pat_copas_ocasion" class="col-lg-3 col-form-label">
                  ¿Que tan frecuente toma seis o más copas en la misma ocasión?
                </label>
                <div class="form-group m-form__group col-lg-3">
                    <div class="m-select2 m-select2--air">
                      <select class="form-control m-select2 styleSelect2" id="pat_copas_ocasion"  name="pat_copas_ocasion" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'freq_6_o_mas',null);?>
                      </select>
                    </div>
                </div>
                <label for="pat_parar_beber" class="col-lg-3 col-form-label">
                  Durante el ultimo año, ¿Le ocurrió que no pudo parar de beber una vez que había empezado?
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                    <select class="form-control m-select2 styleSelect2" id="pat_parar_beber"  name="pat_parar_beber" data-placeholder="Seleccione">
                      <?=$catalog->selectCatalog($this->help,'parar_de_beber',null);?>
                    </select>
                  </div>
                </div>
              </div>

              <div class="row">
                <label for="pat_dejar_hacer" class="col-lg-3 col-form-label">
                  Durante el ultimo año, ¿Que tan frecuente dejo de hacer algo que debería de haber hecho por beber?
                </label>
                <div class="form-group m-form__group col-lg-3">
                    <div class="m-select2 m-select2--air">
                      <select class="form-control m-select2 styleSelect2" id="pat_dejar_hacer"  name="pat_dejar_hacer" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'dejar_por_beber',null);?>
                      </select>
                    </div>
                </div>
                <label for="pat_frecuente_bebio" class="col-lg-3 col-form-label">
                  Durante el ultimo año, ¿Que tan frecuente bebio a la mañana siguiente, despues de haber bebido en exceso a la semana anterior?
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                    <select class="form-control m-select2 styleSelect2" id="pat_frecuente_bebio"  name="pat_frecuente_bebio" data-placeholder="Seleccione">
                      <?=$catalog->selectCatalog($this->help,'beber_temprano',null);?>
                    </select>
                  </div>
                </div>
              </div>

              <div class="row">
                <label for="pat_remordimiento_bebio" class="col-lg-3 col-form-label">
                  Durante el ultimo año, ¿Que tan culpable o tuvo remordimiento por haber bebido?
                </label>
                <div class="form-group m-form__group col-lg-3">
                    <div class="m-select2 m-select2--air">
                      <select class="form-control m-select2 styleSelect2" id="pat_remordimiento_bebio"  name="pat_remordimiento_bebio" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'remordimiento_beber',null);?>
                      </select>
                    </div>
                </div>
                <label for="pat_olvido_bebio" class="col-lg-3 col-form-label">
                  Durante el ultimo año, ¿Que tan frecuente olvido algo de lo que habia pasado cuando estuvo bebiendo?
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                    <select class="form-control m-select2 styleSelect2" id="pat_olvido_bebio"  name="pat_olvido_bebio" data-placeholder="Seleccione">
                      <?=$catalog->selectCatalog($this->help,'olvidar_mientras_bebe',null);?>
                    </select>
                  </div>
                </div>
              </div>

              <div class="row">
                <label for="pat_lesionado_bebio" class="col-lg-3 col-form-label">
                  ¿Se ha lastimado o alguien ha resultado lesionado como consecuencia de su ingestión al alcohol?
                </label>
                <div class="form-group m-form__group col-lg-3">
                    <div class="m-select2 m-select2--air">
                      <select class="form-control m-select2 styleSelect2" id="pat_lesionado_bebio"  name="pat_lesionado_bebio" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'lesion_por_alcohol',null);?>
                      </select>
                    </div>
                </div>
                <label for="pat_preocupado_forma_bebe" class="col-lg-3 col-form-label">
                  ¿Algún amigo, familiar o doctor se ha preocupado por la forma en la que bebe?
                </label>
                <div class="form-group m-form__group col-lg-3">
                  <div class="m-select2 m-select2--air">
                    <select class="form-control m-select2 styleSelect2" id="pat_preocupado_forma_bebe"  name="pat_preocupado_forma_bebe" data-placeholder="Seleccione">
                      <?=$catalog->selectCatalog($this->help,'sugerir_dejar_beber',null);?>
                    </select>
                  </div>
                </div>
              </div>

          </div>

      </div>

      <!---------- TERMINA NOM -------->
      <div class="tab-pane" id="m_tabs_6_3" role="tabpanel">

          <!---------- INICIA TABAQUISMO -------->
          <div class="m-portlet__body">
            <div class="form-group m-form__group row">
              <label for="pat_fuma" class="col-lg-6 col-form-label">
                ¿Usted Fuma?
              </label>
              <div class="col-lg-2" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="pat_fuma" id="pat_fuma" value="1" onchange="validaciones_fuma();">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_cigarrillos_dia" class="col-lg-6 col-form-label">
                ¿Cuantos cigarrillos fuma al día?
              </label>
              <div class="col-lg-3">
                <div class="m-select2 m-select2--air">
                  <select class="form-control m-select2 styleSelect2" id="pat_cigarrillos_dia"  name="pat_cigarrillos_dia" data-placeholder="Seleccionar" disabled>
                    <?=$catalog->selectCatalog($this->help,'cuantos_cigarros',null);?>
                  </select>
                </div>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_cigarrillos_temprano" class="col-lg-6 col-form-label">
                ¿Fuma usted más cigarrillos durante la primera parte del día que durante el resto?
              </label>
              <div class="col-lg-3">
                <div class="m-select2 m-select2--air">
                  <select class="form-control m-select2 styleSelect2" id="pat_cigarrillos_temprano"  name="pat_cigarrillos_temprano" data-placeholder="Seleccionar" disabled>
                    <?=$catalog->selectCatalog($this->help,'mas_cigarros_temprano',null);?>
                  </select>
                </div>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_cigarrillos_despierta" class="col-lg-6 col-form-label">
                ¿Cuanto tiempo transcurre desde que usted despierta hasta que fuma el primer cigarrillo?
              </label>
              <div class="col-lg-3">
                <div class="m-select2 m-select2--air">
                  <select class="form-control m-select2 styleSelect2" id="pat_cigarrillos_despierta"  name="pat_cigarrillos_despierta" data-placeholder="Seleccionar" disabled>
                     <?=$catalog->selectCatalog($this->help,'cigarro_al_despertar',null);?>
                  </select>
                </div>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_cigarrillos_omitir" class="col-lg-6 col-form-label">
                ¿Que cigarrillo le es mas dificil omitir?
              </label>
              <div class="col-lg-3">
                <div class="m-select2 m-select2--air">
                  <select class="form-control m-select2 styleSelect2" id="pat_cigarrillos_omitir"  name="pat_cigarrillos_omitir" data-placeholder="Seleccionar" disabled>
                    <?=$catalog->selectCatalog($this->help,'omitir_cigarrillo',null);?>
                  </select>
                </div>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_fumar_prohibido" class="col-lg-6 col-form-label">
                ¿Le es dificil no fumar donde ello es prohibido?
              </label>
              <div class="col-lg-3">
                <div class="m-select2 m-select2--air">
                  <select class="form-control m-select2 styleSelect2" id="pat_fumar_prohibido"  name="pat_fumar_prohibido" data-placeholder="Seleccionar" disabled>
                     <?=$catalog->selectCatalog($this->help,'fumar_prohibido',null);?>
                  </select>
                </div>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_fumar_enfermo" class="col-lg-6 col-form-label">
                ¿Fuma usted cuando se halla enfermo e incluso en cama?
              </label>
              <div class="col-lg-3">
                <div class="m-select2 m-select2--air">
                  <select class="form-control m-select2 styleSelect2" id="pat_fumar_enfermo"  name="pat_fumar_enfermo" data-placeholder="Seleccionar" disabled>
                     <?=$catalog->selectCatalog($this->help,'fumar_enfermo',null);?>
                  </select>
                </div>
              </div>
            </div>
          </div>
      </div>

      <!---------- TERMINA TABAQUISMO -------->
      <div class="tab-pane" id="m_tabs_6_4" role="tabpanel">

          <!---------- INICIA DROGAS -------->
          <div class="m-portlet__body">
            <div class="form-group m-form__group row">
              <label for="pat_consumo_drogas" class="col-lg-6 col-form-label">
                ¿Usted alguna vez ha consumido drogas?
              </label>
              <div class="col-lg-3">
                <div class="m-select2 m-select2--air">
                  <select class="form-control m-select2 styleSelect2" id="pat_consumo_drogas"  name="pat_consumo_drogas" onchange="valida_consumo_drogas();" data-placeholder="Seleccionar">
                     <?=$catalog->selectCatalog($this->help,'usar_drogas',null);?>
                  </select>
                </div>
              </div>
            </div>
            <div class="form-group m-form__group row" id="pat_especifique_consumo_drogas" style="display:none">
              <label for="pat_esp_cons_drogas" class="col-lg-4 col-form-label">
                Especifique:
              </label>
              <div class="col-lg-5">
                <textarea maxlength="200" class="form-control" id="pat_esp_cons_drogas" name="pat_esp_cons_drogas" rows="2" data-set_max_word="200"></textarea>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_sigue_consumiendo" class="col-lg-6 col-form-label">
                ¿Actualmente sigue consumiendo drogas?
              </label>
              <div class="col-lg-2" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="pat_sigue_consumiendo" value="1" onchange="validacion_droga();">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_tipo_droga" class="col-lg-6 col-form-label">
                ¿Que tipo de droga consume?
              </label>
              <div class="col-lg-3">
                <div class="m-select2 m-select2--air">
                  <select class="js-example-basic-multiple" id="pat_tipo_droga" name="pat_tipo_droga[]" multiple="multiple" data-placeholder="Seleccionar" disabled>
                     <?=$catalog->selectCatalog($this->help,'tipo_drogas',null);?>
                  </select>
                </div>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_12meses" class="col-lg-6 col-form-label">
                ¿En los 12 ultimos meses?
              </label>
              <div class="col-lg-2" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="pat_12meses" id="pat_12meses" disabled>
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_uso_estimulacion" class="col-lg-6 col-form-label">
                ¿Uso en mas de 5 ocasiones para estimularse, relajarse, sentirse mejor o sentirse mas activo o alerta?
              </label>
              <div class="col-lg-2" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="pat_uso_estimulacion" id="pat_uso_estimulacion" disabled>
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <!-- FALTA UNA PREGUNTA -->
            <div class="form-group m-form__group row">
              <label for="pat_menos_efecto" class="col-lg-6 col-form-label">
                ¿Se dio cuenta de que tenía que usar mas cantidad que antes para lograr el efecto deseado?
              </label>
              <div class="col-lg-2" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="pat_menos_efecto" id="pat_menos_efecto" disabled>
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_mas_cantidad" class="col-lg-6 col-form-label">
                ¿Alguna vez se dio cuenta de que necesitaba mas cantidad parar lograr el mismo efecto?
              </label>
              <div class="col-lg-2" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="pat_mas_cantidad" id="pat_mas_cantidad" disabled>
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_necesidad" class="col-lg-6 col-form-label">
                ¿Ha sentido un deseo o necesidad tan fuerte de consumir que no pudo evitar hacerlo?
              </label>
              <div class="col-lg-2" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="pat_necesidad" id="pat_necesidad" disabled>
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_consumir_desesperadamente" class="col-lg-6 col-form-label">
                ¿Ha deseado consumir tan desesperadamente que no podía pensar en nada más?
              </label>
              <div class="col-lg-2" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="pat_consumir_desesperadamente" id="pat_consumir_desesperadamente" disabled>
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_suspender_consumo" class="col-lg-6 col-form-label">
                ¿Hubo ocasiones en que quiso suspender o disminuir el consumo? Si fue así ¿Ha sido siempre capaz de disminuir su uso por lo menos durante un mes?
              </label>
              <div class="col-lg-2" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="pat_suspender_consumo" id="pat_suspender_consumo" disabled>
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="form-group m-form__group row">
              <label for="pat_dificultad_consumo" class="col-lg-6 col-form-label">
                ¿Ha tenido periodos que usó mayor cantidad o por mas tiempo, o se le dificultó suspender el consumo antes de sentirte intoxicado?
              </label>
              <div class="col-lg-2" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="pat_dificultad_consumo" id="pat_dificultad_consumo" disabled>
                    <span></span>
                  </label>
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
            Limpiar
          </button>
        </div>
      </div>

   </div>
 </form>
 </div>
</div>
<script type="text/javascript">

   $(".styleSelect2").select2({
       width: '100%'
   });

   $("#prueba").change(function() {
     var id_episodio = $("#id_episodio_left").val();
       switch (this.value) {
           case '1':
               carga_archivo('contPrueba','psicologia/beta3/'+id_episodio);
           break;
           case '2':
               carga_archivo('contPrueba','psicologia/shipley/'+id_episodio);
           break;
           case '3':
               carga_archivo('contPrueba','psicologia/neuropsi/'+id_episodio);
           break;
           case '4':
               carga_archivo('contPrueba','psicologia/pai/'+id_episodio);
           break;
       }

   });
</script>
 
<script src="<?=URL_PUBLIC?>assets/js/validaciones/patologicos/valida_patologicos.js"></script>
    