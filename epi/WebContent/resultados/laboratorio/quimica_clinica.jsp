<div class="form-group m-form__group">
	<div class="alert m-alert m-alert--default" role="alert">
		<b>QUÍMICA CLÍNICA (RUTINA)</b>
	</div>
</div>
<ul class="nav nav-tabs m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
	<li class="nav-item m-tabs__item">
  	<a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
  	   Quimica Clínica
    </a>
	</li>
	<li class="nav-item m-tabs__item">
  	<a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_2" role="tab">
  	   Electrolitos Sericos
    </a>
	</li>
	<li class="nav-item m-tabs__item">
  	<a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_3" role="tab">
  	   Curva de Tolerancia a la Glucosa
    </a>
	</li>
	<li class="nav-item m-tabs__item">
  	<a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_4" role="tab">
  	   Microalbumina
    </a>
	</li>
</ul>
<form class="m-form m-form--fit m-form--group-seperator-dashed" name="frm_qclinica" id="frm_qclinica">
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

		<!-- INICIA Quimica Clínica -->

		<div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_glucosa" class="col-2 col-form-label">
					Glucosa:
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_glucosa" disabled value="<?=$datos_quimicac[0]['qclinica_glucosa']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 75 - 115 mg/dL
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_urea" class="col-2 col-form-label">
					Urea:
		 		</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_urea" disabled value="<?=$datos_quimicac[0]['qclinica_urea']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/dl &nbsp;&nbsp;&nbsp; Rango: &nbsp; 10 - 50 mg/dl
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_creatinina" class="col-2 col-form-label">
					Creatinina:
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_creatinina" disabled value="<?=$datos_quimicac[0]['qclinica_creatinina']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0.5 - 0.9 mg/dL
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_aurico" class="col-2 col-form-label">
					Acido Urico:
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_aurico" disabled value="<?=$datos_quimicac[0]['qclinica_aurico']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; H 3.4 - 7.0 M 2.4 - 5.7 mg/dL
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_colesterol" class="col-2 col-form-label">
						Colesterol Total:
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_colesterol" disabled value="<?=$datos_quimicac[0]['qclinica_colesterol']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0 - 220 mg/dL
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_trigliceridos" class="col-2 col-form-label">
					Triglicéridos:
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_trigliceridos" disabled value="<?=$datos_quimicac[0]['qclinica_trigliceridos']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 30 - 200 mg/dL
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_hdl" class="col-2 col-form-label">
					Lipoproteinas de Alta Densidad (HDL):
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_hdl" disabled value="<?=$datos_quimicac[0]['qclinica_hdl']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/dL &nbsp;&nbsp;&nbsp; Bajo: < a 35 Alto: > a 65 mg/dL
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_ldl" class="col-2 col-form-label">
					Lipoproteinas De Baja Densidad (LDL):
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_ldl" disabled value="<?=$datos_quimicac[0]['qclinica_ldl']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0 - 150 mg/dL
					</div>
				</div>
			</div>
			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_iaterogenico" class="col-2 col-form-label">
					Índice Aterogénico:
			  </label>
				<div class="col-4">
					<input class="form-control m-input" type="number" name="qclinica_iaterogenico" disabled value="<?=$datos_quimicac[0]['qclinica_iaterogenico']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						Rango: &nbsp; 10*/ul
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_hba1c" class="col-2 col-form-label">
					Hemoglobina Glucosilada (HbA1C)
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_hba1c" disabled value="<?=$datos_quimicac[0]['qclinica_hba1c']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						% &nbsp;&nbsp;&nbsp; Rango: &nbsp; No D.M.: 4 - 6 %, D.M. controlado: 6 - 8, DM s/c > 8
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_pcreactiva" class="col-2 col-form-label">
					Proteina C Reactiva:
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_pcreactiva" disabled value="<?=$datos_quimicac[0]['qclinica_pcreactiva']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0 - 0.5 mg/dL
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_proteina" class="col-2 col-form-label">
					Proteina Total:
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_proteina" disabled value="<?=$datos_quimicac[0]['qclinica_proteina']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						g/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 6.4 - 8.3 g/dL
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_albumina" class="col-2 col-form-label">
					Albumina:
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_albumina" disabled value="<?=$datos_quimicac[0]['qclinica_albumina']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
					 	3.8 - 4.4 g/dL
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_bilirrubina" class="col-2 col-form-label">
					Bilirrubina Total:
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_bilirrubina" disabled value="<?=$datos_quimicac[0]['qclinica_bilirrubina']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/gL &nbsp;&nbsp;&nbsp; Rango: &nbsp; Hasta 1
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_bdirecta" class="col-2 col-form-label">
					Bilirrubina Directa:
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_bdirecta" disabled value="<?=$datos_quimicac[0]['qclinica_bdirecta']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/gL &nbsp;&nbsp;&nbsp; Rango: &nbsp; Hasta 2.5 mg/dL
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_bindirecta" class="col-2 col-form-label">
					Bilirrubina Indirecta:
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_bindirecta" disabled value="<?=$datos_quimicac[0]['qclinica_bindirecta']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						mg/dL
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_alcalina" class="col-2 col-form-label">
					Fosfatasa Alcalina (ALP):
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_alcalina" disabled value="<?=$datos_quimicac[0]['qclinica_alcalina']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						98 - 297 U/L
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_ast" class="col-2 col-form-label">
					Aspartato Aminotransferasa (AST):
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_ast" disabled value="<?=$datos_quimicac[0]['qclinica_ast']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						U/L &nbsp;&nbsp;&nbsp; Rango: &nbsp; H: Hasta 37 M: Hasta 31
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_alt" class="col-2 col-form-label">
					Alanina Amino Transferasa (ALT):
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_alt" disabled value="<?=$datos_quimicac[0]['qclinica_alt']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						U/L &nbsp;&nbsp;&nbsp; Rango: &nbsp; H: Hasta 40 M: Hasta 31
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_ck" class="col-2 col-form-label">
					Creatin Kinasa (CK):
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_ck" disabled value="<?=$datos_quimicac[0]['qclinica_ck']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
					 	U/L &nbsp;&nbsp;&nbsp; Rango: &nbsp; H: >195 M: >170
					</div>
				</div>
			</div>

			<div class="form-group m-form__group row" align="center">
				<label for="qclinica_ckmb" class="col-2 col-form-label">
						Creatin Kinasa Freacción MB (CKMB):
				</label>
				<div class="col-4">
					<input type="number" class="form-control m-input" name="qclinica_ckmb" disabled value="<?=$datos_quimicac[0]['qclinica_ckmb']?>">
				</div>
				<div class="col-5" align="left">
					<div class="alert m-alert m-alert--default" role="alert">
						U/L &nbsp;&nbsp;&nbsp; Rango: &nbsp; > 25
					</div>
				</div>
			</div>
		</div>

		<!-- TERMINA Quimica Clínica  -->

		<!-- INICIA Electrolitos Sericos -->

		<div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
			<div class="m-section">
				<div class="form-group m-form__group row" align="center">
					<label for="electrolitos_sodio" class="col-2 col-form-label">
						Sodio (NA+):
					</label>
					<div class="col-4">
						<input type="number" class="form-control" name="electrolitos_sodio" disabled value="<?=$datos_quimicac[0]['electrolitos_sodio']?>">
					</div>
					<div class="col-5" align="left">
						<div class="alert m-alert m-alert--default" role="alert">
							 mmol/L &nbsp;&nbsp;&nbsp; Rango: &nbsp; 136 - 146
						</div>
					</div>
				</div>
				<div class="form-group m-form__group row" align="center">
					<label for="electrolitos_potasio" class="col-2 col-form-label">
						Potasio (K):
					</label>
					<div class="col-4">
						<input type="number" class="form-control" name="electrolitos_potasio" disabled value="<?=$datos_quimicac[0]['electrolitos_potasio']?>">
					</div>
					<div class="col-5" align="left">
						<div class="alert m-alert m-alert--default" role="alert">
							 mmol/L &nbsp;&nbsp;&nbsp; Rango: &nbsp; 3.5 - 5.1
						</div>
					</div>
				</div>
				<div class="form-group m-form__group row" align="center">
					<label for="electrolitos_cloro" class="col-2 col-form-label">
					Cloro (CL-): </label>
					<div class="col-4">
						<input type="number" class="form-control" name="electrolitos_cloro" disabled value="<?=$datos_quimicac[0]['electrolitos_cloro']?>">
					</div>
					<div class="col-5" align="left">
						<div class="alert m-alert m-alert--default" role="alert">
							 mmol/L &nbsp;&nbsp;&nbsp; Rango: &nbsp; 97 - 107
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- TERMINA Electrolitos Sericos -->

		<!-- INICIA Curva de Tolerancia a la Glucosa  -->

		<div class="tab-pane" id="m_tabs_6_3" role="tabpanel">
			<div class="m-section">
				<div class="form-group m-form__group row" align="center">
					<label for="tolerancia_gpostcarga" class="col-3 col-form-label">
						Glucosa Post-Carga:
					</label>
					<div class="col-4">
						<input type="number" class="form-control" name="tolerancia_gpostcarga" disabled value="<?=$datos_quimicac[0]['tolerancia_gpostcarga']?>">
					</div>
					<div class="col-5" align="left">
						<div class="alert m-alert m-alert--default" role="alert">
							 mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 75 - 115
						</div>
					</div>
				</div>
				<div class="form-group m-form__group row" align="center">
					<label for="tolerancia_orina" class="col-3 col-form-label">
						Glucosa en Orina:
					</label>
					<div class="col-4">
						<input type="number" class="form-control" name="tolerancia_orina" disabled value="<?=$datos_quimicac[0]['tolerancia_orina']?>">
					</div>
					<div class="col-5" align="left">
						<div class="alert m-alert m-alert--default" role="alert">
							 mg/dL &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0 - 10
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- TERMINA Curva de Tolerancia a la Glucosa -->

		<!-- INICIA Microalbumina  -->

		<div class="tab-pane" id="m_tabs_6_4" role="tabpanel">
			<div class="m-section">
				<div class="form-group m-form__group row" align="center">
					<label for="microalbumina" class="col-3 col-form-label">
						Microalbumina:
					</label>
					<div class="col-4">
						<input type="number" class="form-control" name="microalbumina" disabled value="<?=$datos_quimicac[0]['microalbumina']?>">
					</div>
					<div class="col-5" align="left">
						<div class="alert m-alert m-alert--default" role="alert">
							 mg/L &nbsp;&nbsp;&nbsp; Rango: &nbsp; 30 - 299
						</div>
					</div>
				</div>
				<div class="form-group m-form__group row" align="center">
					<label for="microalbumina_obs" class="col-3 col-form-label">
						Observaciones:
					</label>
					<div class="col-9">
						<textarea maxlength="500" class="form-control m-input m-input--air" id="microalbumina_obs" name="microalbumina_obs" rows="2" disabled><?php ucwords($datos_quimicac[0]['microalbumina_obs']); ?></textarea>
					</div>
				</div>
				<div class="form-group m-form__group row" align="center">
					<label for="microalbumina_otros" class="col-3 col-form-label">
						Otros:
					</label>
					<div class="col-9">
						<textarea maxlength="500" class="form-control m-input m-input--air" id="microalbumina_otros" name="microalbumina_otros" rows="2" disabled><?php ucwords($datos_quimicac[0]['microalbumina_otros']); ?></textarea>
					</div>
				</div>
				<div class="form-group m-form__group row" align="center">
					<label for="microalbumina_metodo" class="col-3 col-form-label">
						Método:
					</label>
					<div class="col-9">
						<textarea maxlength="500" class="form-control m-input m-input--air" id="microalbumina_metodo" name="microalbumina_metodo" rows="2" disabled><?php ucwords($datos_quimicac[0]['microalbumina_metodo']); ?></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<script src="<?=URL_PUBLIC?>assets/js/validaciones/laboratorio/valida_qclinica.js"></script>
