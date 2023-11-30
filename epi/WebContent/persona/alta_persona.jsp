<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>  

<%
String mdotrasnpore = "<option value=\"-1\">Seleccione una opci&oacute;n</option>";
/*
TDGRLMdoTrans mdotrans = new TDGRLMdoTrans();
TVGRLMdoTrans vGRLMdoTrans;
Vector vcGRLMdoTrans = new Vector();
try{
	vcGRLMdoTrans = mdotrans.findByAll(" where lActivo = 1");	
	for (int i = 0; i < vcGRLMdoTrans.size(); i++) {
		vGRLMdoTrans = (TVGRLMdoTrans) vcGRLMdoTrans.get(i);
			mdotrasnpore = mdotrasnpore+"<option value=\""+vGRLMdoTrans.getICveMdoTrans()+"\">"+vGRLMdoTrans.getCDscMdoTrans()+"</option>"; 
	}
}catch(Exception e){
	System.out.println("Error al buscar registros del catalogo de Categorias"+ e);
}

String cMotivos = "";
TDGRLMotivo dmotivos = new TDGRLMotivo();
TVGRLMotivo vGRLMotivo;
Vector vcGRLMotivo = new Vector();
try{
	vcGRLMotivo = dmotivos.FindByAll(" where lActivo = 1 and icveproceso = 1");	
	for (int i = 0; i < vcGRLMotivo.size(); i++) {
		vGRLMotivo = (TVGRLMotivo) vcGRLMotivo.get(i);
		cMotivos = cMotivos+"<option value=\""+vGRLMotivo.getICveMotivo()+"\">"+vGRLMotivo.getCDscMotivo()+"</option>"; 
	}
}catch(Exception e){
	System.out.println("Error al buscar registros del catalogo de Categorias"+ e);
}




*/

TDGRLPais pais = new TDGRLPais();
TVGRLPais vGRLPais;
Vector vcGRLPais = new Vector();
String  Nacionalidad = "<option value=\"-1\">Seleccione una opci&oacute;n</option>";
try{
	vcGRLPais = pais.FindByAll();	
	System.out.println(vcGRLPais.size());
	for (int i = 0; i < vcGRLPais.size(); i++) {
		vGRLPais = (TVGRLPais) vcGRLPais.get(i);
		Nacionalidad = Nacionalidad+"<option value=\""+vGRLPais.getICvePais()+"\">"+vGRLPais.getCNombre()+"</option>"; 
	}
}catch(Exception e){
	System.out.println("Error al buscar registros del catalogo de paises"+ e);
}

System.out.println(Nacionalidad);


%> 
    
    
<div class="m-portlet">
<div class="m-portlet__head">
   <div class="m-portlet__head-caption">
      <div class="m-portlet__head-title">
           <span class="m-portlet__head-icon">
           <i class="fa fa-user-plus m--font-primary"></i>
           </span>
           <h3 class="m-portlet__head-text m--font-primary">
              Alta de Persona
           </h3>
        </div>
   </div>
</div>
<!--begin::Form-->
<form class="m-form m-form--fit m-form--label-align-right" id="form_alta_persona">
   <div class="m-portlet__body">
      <div class="m-form__content">
        <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
          <li class="nav-item m-tabs__item">
             <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
             <i class="flaticon-profile-1" aria-hidden="true"></i>
             Datos Personales
             </a>
          </li>
        </ul>


         <div class="m-alert m-alert--icon alert alert-danger m--hide" role="alert" id="m_form_1_msg">
            <div class="m-alert__icon">
               <i class="la la-warning"></i>
            </div>
            <div class="m-alert__text">
               Los Campos marcados con * son obligatorios
            </div>
            <div class="m-alert__close">
               <button type="button" class="close" data-close="alert" aria-label="Close"></button>
            </div>
         </div>
         <div class="row">
            <div class="form-group col-lg-3">
               <label class="col-form-label">
               Apellido Paterno *
               </label>
               <input type="text" class="form-control m-input text-uppercase" name="apaterno" maxlength="50" onkeyup="mayus(this);" >
            </div>
            <div class="form-group col-lg-3">
               <label class="col-form-label">
               Apellido Materno
               </label>
               <input type="text" class="form-control m-input text-uppercase" name="amaterno" maxlength="50" onkeyup="mayus(this);" >
            </div>
            <div class="form-group col-lg-3">
               <label class="col-form-label">
               Nombre *
               </label>
               <input type="text" class="form-control m-input text-uppercase" name="nombre" maxlength="50" onkeyup="mayus(this);" >
            </div>
            <div class="form-group col-lg-3">
                  <label class="">
                  Sexo:
                  </label>
                  <div class="m-radio-inline">
                     <label class="m-radio m-radio--solid">
                     <input type="radio" name="sexo" value="H">
                     Hombre
                     <span></span>
                     </label>
                     <label class="m-radio m-radio--solid">
                     <input type="radio" name="sexo" value="M">
                     Mujer
                     <span></span>
                     </label>
                  </div>
               </div>
         </div>
         <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

         <div class="row">
            <div class="form-group col-lg-3">
               <label class="col-form-label">
               Fecha de Nacimiento *
               </label>
               <input type="text" class="form-control m-input date-picker mask_date" name="fecha_nacimiento">
            </div>
            <div class="form-group col-lg-3">
               <label class="col-form-label">
               RFC *:
               </label>
               <input type="text" class="form-control m-input" id="rfc" name="rfc" maxlength="13"  onkeyup="mayus(this);">
            </div>
            <div class="form-group col-lg-3">
               <label class="col-form-label">
               CURP *:
               </label>
               <input type="text" class="form-control m-input" id="curp" name="curp" maxlength="18" onkeyup="mayus(this);" onchange="buscaCurpPersona2(this);" >
                <pre id="resultado"></pre>
            </div>
            <div class="form-group col-lg-3">
                <label class="col-form-label">
                Licencia:
                </label>
                <input type="text" class="form-control m-input" name="licencia">
             </div>

         </div>

         <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

         <div class="row">
            <div class="col-lg-3">
                 <label class="col-form-label">
                 Licencia Int:
                 </label>
                 <input type="text" class="form-control m-input" name="licencia_int">
              </div>

              <div class="col-lg-3">
                <label class="col-form-label">
                  Nacionalidad *
                </label>
                <div class="form-group">
                  <!-- <select class="form-control m-select2" id="id_nacionalidad" name="id_nacionalidad" data-load_datacombo="estado_nacimiento" onchange="generaCombosSecundario(this,'cat_lugar_nacimiento','cat_lugar_nacimiento');"> -->
                  <select class="form-control m-select2" id="id_nacionalidad" name="id_nacionalidad" data-load_datacombo="estado_nacimiento" onchange="generaCombosSecundario(this,'cat_lugar_nacimiento');">
                      <?=$catalog->selectCatalog($this->help,'nacionalidad',null);?>
                      <%=Nacionalidad %>
                  </select>
                  <span class="m-form__help"></span>
                </div>
              </div>

              <div class="col-lg-3">
                <label class="col-form-label">
                  Lugar de Nacimiento *
                </label>
                <div class="form-group">
                  <select class="form-control m-select2" id="estado_nacimiento" name="estado_nacimiento">
                      <option value="">Seleccione</option>
                  </select>
                  <span class="m-form__help"></span>
                </div>
              </div>
          </div>

            <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
              <li class="nav-item m-tabs__item">
                 <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_2" role="tab">
                 <i class="flaticon-map-location" aria-hidden="true"></i>
                 Direcci&oacute;n
                 </a>
              </li>
            </ul>


            <div class="row">
               <div class="form-group col-lg-3">
                  <label class="col-form-label">
                  Calle *
                  </label>
                  <input type="text" class="form-control m-input" name="dom_calle">
               </div>
               <div class="form-group col-lg-3">
                  <label class="col-form-label">
                  No. Exterior *
                  </label>
                  <input type="text" class="form-control m-input" name="dom_numext">
               </div>
               <div class="form-group col-lg-3">
                  <label class="col-form-label">
                  No. Interior
                  </label>
                  <input type="text" class="form-control m-input" name="dom_numint">
               </div>
               <div class="form-group col-lg-3">
                  <label class="col-form-label">
                  Colonia *
                  </label>
                  <input type="text" class="form-control m-input" name="dom_colonia">
               </div>

            </div>


            <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

            <div class="row">

                  <div class="form-group col-lg-3">
                     <label class="col-form-label">
                     C&oacute;digo Postal *
                     </label>
                     <!--  <input type="text" class="form-control m-input" id="dom_codpos" name="dom_codpos" maxlength="5" data-only_digit onchange="carga_codpos();"/>-->
                     <input type="text" class="form-control m-input" id="dom_codpos" name="dom_codpos" maxlength="5" data-only_digit />
                  </div>
                  <div class="col-lg-3">
                    <label class="col-form-label">
                      Pa&iacute;s *
                    </label>
                    <div class="form-group">
                      <!-- <select class="form-control m-select2" id="dom_id_pais" name="dom_id_pais"> -->
                      <select class="form-control m-select2" id="dom_id_pais" name="dom_id_pais" data-load_datacombo="dom_id_estado" onchange="generaCombosSecundario(this,'cat_estado');">
                          <?=$catalog->selectCatalog($this->help,'cat_paises',null);?>
                          <%=Nacionalidad %>
                      </select>
                      <span class="m-form__help"></span>
                    </div>
                  </div>

                  <div class="col-lg-3">
                    <label class="col-form-label">
                      Estado *
                    </label>
                    <div class="form-group">
                      <select class="form-control m-select2" id="dom_id_estado" name="dom_id_estado" data-load_datacombo="dom_id_municipio" onchange="generaCombosSecundario(this,'cat_municipio');">
                          <option value="">Seleccione</option>
                      </select>
                      <span class="m-form__help"></span>
                    </div>
                  </div>

                  <div class="col-lg-3">
                    <label class="col-form-label">
                      Municipio *
                    </label>
                    <div class="form-group">
                    <select class="form-control m-select2" id="dom_id_municipio" name="dom_id_municipio" data-load_datacombo="dom_id_localidad" onchange="generaCombosSecundario(this,'cat_localidad');">
                          <?=$catalog->selectCatalog($this->help,'nacionalidad',null);?>
                      </select>
                      <span class="m-form__help"></span>
                    </div>
                  </div>

              </div>
              <div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
              <div class="row">
                <div class="col-lg-3">
                  <label class="col-form-label">
                    Localidad *
                  </label>
                  <div class="form-group">
                    <!-- <select class="form-control m-select2" id="dom_id_localidad" name="dom_id_localidad" onchange="obtiene_cp();"> -->
                    <select class="form-control m-select2" id="dom_id_localidad" name="dom_id_localidad" >
                        <option value="">Seleccione</option>
                    </select>
                    <span class="m-form__help"></span>
                  </div>
                </div>

                <div class="form-group col-lg-3">
                   <label class="col-form-label">
                   Telefono *
                   </label>
                   <input type="text" class="form-control m-input" id="telefono" name="telefono" data-only_digit maxlength="10">
                </div>
              </div>
      </div>
    </div>
      <div class="m-portlet__foot m-portlet__foot--fit">
         <div class="m-form__actions m-form__actions">
            <div class="row">
               <div class="col-lg-9 ml-lg-auto">
                  <button type="submit" class="btn btn-primary" id="btn_save_alta_pac">
                  Guardar
                  </button>
                  <button type="reset" class="btn btn-secondary">
                  Limpiar
                  </button>
               </div>
            </div>
         </div>
      </div>
</form>
<!--end::Form-->
</div>
<script type="text/javascript">
   $("select").select2({ width: '100%' });
</script>
    