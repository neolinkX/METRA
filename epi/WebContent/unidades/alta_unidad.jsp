<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>

<%
	String mdotrasnpore = "<option value=\"-1\">Seleccione una opci&oacute;n</option>";

	TDGRLPais pais = new TDGRLPais();
	TVGRLPais vGRLPais;
	Vector vcGRLPais = new Vector();
	String Nacionalidad = "<option value=\"-1\">Seleccione una opci&oacute;n</option>";
	try {
		vcGRLPais = pais.FindByAll();
		System.out.println(vcGRLPais.size());
		for (int i = 0; i < vcGRLPais.size(); i++) {
			vGRLPais = (TVGRLPais) vcGRLPais.get(i);
			Nacionalidad = Nacionalidad + "<option value=\"" + vGRLPais.getICvePais() + "\">"
					+ vGRLPais.getCNombre() + "</option>";
		}
	} catch (Exception e) {
		System.out.println("Error al buscar registros del catalogo de paises" + e);
	}

	System.out.println(Nacionalidad);

	TDGRLUniMed unidad = new TDGRLUniMed();
	TVGRLUniMed vGRLUniMed;
	Vector vcGRLUniMed = new Vector();
	try {
		vcGRLUniMed = unidad.FindUniMedDos(request.getParameter("icveunimed")); 
	} catch (Exception e) {
		System.out.println("Error al buscar info de la unidad medica " + e);
	}


			//////////////////Entidades/////////////////////////////////////
			TDPERDatos dPERDatos = new TDPERDatos();
			String cPais = "";
			try {
				//cPais = dPERDatos.SenFindBy("Select cNombre from GrlPais where icvepais = " + vGRLUniMed.getICvePais());
			} catch (Exception e) {
				cPais = "";
			}
			
			String cEntidadFed = "";
			try {
				//cEntidadFed = dPERDatos.SenFindBy("Select cNombre from GrlEntidadFed where icvepais = " + vGRLUniMed.getICvePais() +" and icveentidadfed="+vGRLUniMed.getICveEstado());
			} catch (Exception e) {
				cEntidadFed = "";
			}
			
			String cMunicipio = "";
			try {
				//cMunicipio = dPERDatos.SenFindBy("Select cNombre from GrlMunicipio where icvepais = " + vGRLUniMed.getICvePais() +" and icveentidadfed="+vGRLUniMed.getICveEstado() +" and icvemunicipio = "+vGRLUniMed.getICveMunicipio());
			} catch (Exception e) {
				cMunicipio = "";
			}
			

			
%>


<div class="m-portlet">
	<div class="m-portlet__head">
		<div class="m-portlet__head-caption">
			<div class="m-portlet__head-title">
				<span class="m-portlet__head-icon"> <i
					class="fa fa-user-plus m--font-primary"></i>
				</span>
				<h3 class="m-portlet__head-text m--font-primary">Unidad
					M&eacute;dica</h3>
			</div>
		</div>
	</div>
	<!--begin::Form-->
	<form class="m-form m-form--fit m-form--label-align-right"
		id="form_modifica_unidad">
		<div class="m-portlet__body">
			<div class="m-form__content">
				<ul
					class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary"
					role="tablist">
					<li class="nav-item m-tabs__item"><a
						class="nav-link m-tabs__link active" data-toggle="tab"
						href="#m_tabs_6_1" role="tab" aria-expanded="false"> <i
							class="flaticon-profile-1" aria-hidden="true"></i> Datos de la
							Unidad
					</a></li>
				</ul>


				<div class="m-alert m-alert--icon alert alert-danger m--hide"
					role="alert" id="m_form_1_msg">
					<div class="m-alert__icon">
						<i class="la la-warning"></i>
					</div>
					<div class="m-alert__text">Los Campos marcados con * son
						obligatorios</div>
					<div class="m-alert__close">
						<button type="button" class="close" data-close="alert"
							aria-label="Close"></button>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-lg-3">
						<label class="col-form-label"> Clave </label> <input
							value="" type="text"
							class="form-control m-input text-uppercase" name="icveunimed"
							maxlength="30" onkeyup="mayus(this);" readonly>
					</div>
					<div class="form-group col-lg-3">
						<label class="col-form-label"> Nombre * </label> <input
							value="" type="text"
							class="form-control m-input text-uppercase" name="cdscunimmed"
							maxlength="30" onkeyup="mayus(this);">
					</div>
				</div>
				<div
					class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
				<div class="row">


					<div class="form-group col-lg-3">
						<label class=""> CIS: </label>
						<div class="form-check">
							<label class="m-radio m-radio--solid"> <input
								type="checkbox" class="form-check-input" id="lcis" name="lcis"
								> <span></span>
							</label>
						</div>
					</div>



					<div class="form-group col-lg-3">
						<label class=""> Pago: </label>
						<div class="form-check">
							<label class="m-radio m-radio--solid"> <input
								type="checkbox" class="form-check-input" id="lpago" name="lpago"
								> <span></span>
							</label>
						</div>
					</div>
				</div>
				<div
					class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
				<div class="row">

					
					<div class="form-group col-lg-3">
						<label class=""> Privada: </label>
						<div class="form-check">
							<label class="m-radio m-radio--solid"> <input
								type="checkbox" class="form-check-input" id="lprivada"
								name="lprivada" > <span></span>
							</label>
						</div>
					</div>



					<div class="form-group col-lg-3">
						<label class=""> Vigente: </label>
						<div class="form-check">
							<label class="m-radio m-radio--solid"> <input
								type="checkbox" class="form-check-input" id="lvigente"
								name="lvigente" > <span></span>
							</label>
						</div>
					</div>
				</div>
				<div
					class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

				<ul
					class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary"
					role="tablist">
					<li class="nav-item m-tabs__item"><a
						class="nav-link m-tabs__link active" data-toggle="tab"
						href="#m_tabs_6_2" role="tab"> <i
							class="flaticon-map-location" aria-hidden="true"></i>
							Direcci&oacute;n
					</a></li>
				</ul>



				<div class="row">


					<div class="col-lg-3">
						<label class="col-form-label"> Pa&iacute;s * </label>
						<div class="form-group">
							<!-- <select class="form-control m-select2" id="dom_id_pais" name="dom_id_pais"> -->
							<select class="form-control m-select2" id="dom_id_pais"
								name="dom_id_pais" data-load_datacombo="dom_id_estado"
								onchange="generaCombosSecundario(this,'cat_estado');">
								<option value=""></option>
							</select> <span class="m-form__help"></span>
						</div>
					</div>

					<div class="col-lg-3">
						<label class="col-form-label"> Estado * </label>
						<div class="form-group">
							<select class="form-control m-select2" id="dom_id_estado"
								name="dom_id_estado" data-load_datacombo="dom_id_municipio"
								onchange="generaCombosSecundario(this,'cat_municipio');">
								<option value=""></option>
							</select> <span class="m-form__help"></span>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-3">
						<label class="col-form-label"> Municipio * </label>
						<div class="form-group">
							<select class="form-control m-select2" id="dom_id_municipio"
								name="dom_id_municipio" data-load_datacombo="dom_id_localidad"
								onchange="generaCombosSecundario(this,'cat_localidad');">
								<option value=""></option>
							</select> <span class="m-form__help"></span>
						</div>
					</div>

				</div>
				<div
					class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
					
				<div class="row">
					<div class="form-group col-lg-3">
						<label class="col-form-label"> Calle * </label> <input type="text"
							value="" class="form-control m-input"
							name="ccalle">
					</div>
					<div class="form-group col-lg-3">
						<label class="col-form-label"> Colonia * </label> <input
							value="" type="text"
							class="form-control m-input" name="ccolonia">
					</div>
				</div>
				<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
				<div class="row">
					<div class="form-group col-lg-3">
						<label class="col-form-label"> Ciudad * </label>
						<!--  <input type="text" class="form-control m-input" id="dom_codpos" name="dom_codpos" maxlength="5" data-only_digit onchange="carga_codpos();"/>-->
						<input type="text" class="form-control m-input" id="dom_ciudad"
							value="" name="dom_ciudad" maxlength="50" />
					</div>
					
					<div class="form-group col-lg-3">
						<label class="col-form-label"> C&oacute;digo Postal * </label>
						<!--  <input type="text" class="form-control m-input" id="dom_codpos" name="dom_codpos" maxlength="5" data-only_digit onchange="carga_codpos();"/>-->
						<input type="text" class="form-control m-input" id="dom_codpos"
							value="" name="icp" maxlength="8"
							data-only_digit />
					</div>

				</div>


				<div
					class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
					
					
				<div class="row">
					<div class="form-group col-lg-3">
						<label class="col-form-label"> Correo </label> <input type="text"
							value=""
							class="form-control m-input" id="ccorreo" name="ccorreo"
							 maxlength="50">
					</div>

					<div class="form-group col-lg-3">
						<label class="col-form-label"> Telefono * </label> <input
							value="" type="text"
							class="form-control m-input" id="ctel" name="ctel"
							 maxlength="10">
					</div>
				</div>
			</div>
		</div>
		<div class="m-portlet__foot m-portlet__foot--fit">
			<div class="m-form__actions m-form__actions">
				<div class="row">
					<div class="col-lg-9 ml-lg-auto">
						<button type="submit" class="btn btn-primary"
							id="btn_save_unidad">Guardar</button>
						<button type="reset" class="btn btn-secondary">Limpiar</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!--end::Form-->
</div>
<script type="text/javascript">
	$("select").select2({
		width : '100%'
	});
</script>

