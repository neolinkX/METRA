<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="modal fade" id="modalPerfil" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document" style="max-width: 1200px !important;">
        <div class="modal-content">
            <div class="modal-header">

                <h5 class="modal-title" id="myModalLabel"><i class="flaticon-statistics"></i>Ficha Persona (Detalle de la Cita):</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="modal_content">
              <!-- <?php if($tipo=='modal_existe_curp'){ ?> -->
                <div class="row">
                  <div class="col-md-2"></div>
                  <div class="col-md-8">
                    <div class="m-alert m-alert--icon alert alert-warning" role="alert">
                       <div class="m-alert__icon">
                          <i class="la la-warning"></i>
                       </div>
                       <div class="m-alert__text">
                          <h5>Ya existe un registro con la misma CURP.</h5>
                       </div>
                       <div class="m-alert__close">
                          <button type="button" data-dismiss="modal" class="btn btn-secondary" onclick="carga_ficha_persona(<?=$id_persona?>,'close_modal')">Ir a la ficha de la Persona</button>
                       </div>

                    </div>
                  </div>

                </div>


              <!-- <?php } ?> -->
              <div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm"  data-portlet="true" id="m_portlet_tools_2">
                 <div class="m-portlet__body">
                    <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
                       <li class="nav-item m-tabs__item">
                          <a class="nav-link m-tabs__link active" data-toggle="tab" href="#datos_personales" role="tab" aria-expanded="false">
                          <i class="flaticon-profile-1" aria-hidden="true"></i>
                          Datos Personales
                          </a>
                       </li>
                       <li class="nav-item m-tabs__item">
                          <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_2" role="tab">
                          <i class="flaticon-map-location" aria-hidden="true"></i>
                          Dirección
                          </a>
                       </li>
                    </ul>
                    <div class="tab-content">
                       <div class="tab-pane active" id="datos_personales" role="tabpanel">


                          <form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
                             <div class="form-group m-form__group row">
                                <div class="col-lg-3">
                                   <label>
                                   Apellido Paterno
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->apaterno_paciente?>" disabled>
                                </div>
                                <div class="col-lg-3">
                                   <label class="">
                                   Apellido Materno:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->amaterno_paciente?>" disabled>
                                </div>
                                <div class="col-lg-3">
                                   <label>
                                   Nombre(s):
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->nombre_paciente?>" disabled>
                                </div>
                                <div class="col-lg-3">
                                   <label class="">
                                   Sexo:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$sexo_persona?>" disabled>
                                </div>
                             </div>
                             <div class="form-group m-form__group row">
                                <div class="col-lg-3">
                                   <label class="">
                                   Fecha de Nacimiento:
                                   </label>
                                   <div class="m-input-icon m-input-icon--right">
                                      <input type="text" class="form-control m-input date-picker" value="<?=$datos_p->fecha_nacimiento?>" disabled>
                                      <span class="m-input-icon__icon m-input-icon__icon--right">
                                        <span>
                                          <i class="fa fa-calendar"></i>
                                        </span>
                                      </span>
                                   </div>
                                </div>
                                <div class="col-lg-3">
                                   <label class="">
                                   RFC:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->rfc?>" disabled>
                                </div>
                                <div class="col-lg-3">
                                   <label class="">
                                   CURP:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->curp?>" disabled>
                                </div>
                                <div class="col-lg-3">
                                   <label class="">
                                   Licencia:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->licencia?>" disabled>
                                </div>
                             </div>
                             <div class="form-group m-form__group row">
                                <div class="col-lg-3">
                                   <label class="">
                                   Licencia Int:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->licencia_int?>" disabled>
                                </div>
                                <div class="col-lg-3 col-md-9 col-sm-12">
                                   <label class="">
                                   Nacionalidad:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$catalog->getNameByIdCatalogo('framework.cm_catalogo','etiqueta','id_cat',$datos_p->id_nacionalidad);?>" disabled>
                                </div>
                                <div class="col-lg-3 col-md-9 col-sm-12">
                                   <label class="">
                                   Lugar de Nacimiento:
                                   </label>
                                  <input type="text" class="form-control m-input" value="<?=$catalog->getNameByIdCatalogo('framework.cm_catalogo','etiqueta','id_cat',$datos_p->estado_nacimiento);?>" disabled>
                                </div>
                             </div>
                          </form>
                       </div>
                       <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
                          <form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
                             <div class="form-group m-form__group row">
                                <div class="col-lg-3">
                                   <label>
                                   Calle:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->dom_calle?>" disabled>
                                </div>
                                <div class="col-lg-3">
                                   <label class="">
                                   No. Exterior:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->dom_numext?>" disabled>
                                </div>
                                <div class="col-lg-3">
                                   <label class="">
                                   No. Interior:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->dom_numint?>" disabled>
                                </div>
                                <div class="col-lg-3">
                                   <label class="">
                                   Colonia:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->dom_colonia?>" disabled>
                                </div>
                             </div>
                             <div class="form-group m-form__group row">
                                <div class="col-lg-3">
                                   <label>
                                   Código Postal:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->dom_codpos?>" disabled>
                                </div>
                                <div class="col-lg-3">
                                   <label class="">
                                   País:
                                   </label>
                                  <input type="text" class="form-control m-input" value="<?=$catalog->getNameByIdCatalogo('framework.cm_catalogo','etiqueta','id_cat',$datos_p->dom_id_pais);?>" disabled>
                                </div>
                                <div class="col-lg-3">
                                   <label class="">
                                   Estado:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$catalog->getNameByIdCatalogo('miscelanea.cat_entidad','entidad','id_entidad',$datos_p->dom_id_estado);?>" disabled>
                                </div>
                                <div class="col-lg-3">
                                   <label class="">
                                   Municipio:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$catalog->getNameByIdCatalogo('miscelanea.cat_municipio','municipio','id_municipio',$datos_p->dom_id_municipio);?>" disabled>
                                </div>
                             </div>
                             <div class="form-group m-form__group row">
                               <div class="col-lg-3">
                                  <label class="">
                                  Localidad:
                                  </label>
                                  <input type="text" class="form-control m-input" value="<?=$catalog->getNameByIdCatalogo('miscelanea.cat_asentamientos','asentamiento','id_asentamiento',$datos_p->dom_id_localidad);?>" disabled>
                               </div>
                                <div class="col-lg-3">
                                   <label>
                                   Telefono:
                                   </label>
                                   <input type="text" class="form-control m-input" value="<?=$datos_p->telefono?>" disabled>
                                </div>
                             </div>
                          </form>
                       </div>
                       <div class="tab-pane" id="m_tabs_6_3" role="tabpanel">
                          <form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
                             <div class="form-group m-form__group row">
                                <div class="col-lg-3 col-md-9 col-sm-12">
                                   <label class="">
                                   Modo de Transporte:
                                   </label>
                                   <input type="text" class="form-control m-input" value="" disabled>
                                </div>
                                <div class="col-lg-3 col-md-9 col-sm-12">
                                   <label class="">
                                   Categoría:
                                   </label>
                                   <input type="text" class="form-control m-input" value="" disabled>
                                </div>
                                <div class="col-lg-3 col-md-9 col-sm-12">
                                   <label class="">
                                    Puesto:
                                   </label>
                                  <input type="text" class="form-control m-input" value="" disabled>
                                </div>
                                <div class="col-lg-3 col-md-9 col-sm-12">
                                   <label class="">
                                   Motivo:
                                   </label>
                                  <input type="text" class="form-control m-input" value="" disabled>
                                </div>
                             </div>
                          </form>
                       </div>
                    </div>
                 </div>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">
                Cerrar
              </button>
              <?php if($tipo=='3'){ ?>
                <button type="button" class="btn btn-primary" data-dismiss="" onclick="generaExpediente(<?=$id_cita?>);">
                  Validar y Generar Expediente
                </button>
              <?php } ?>
            </div>
        </div>
    </div>
</div>
