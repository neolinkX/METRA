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
  <div class="m-portlet__body">
  
    <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
      <li class="nav-item m-tabs__item">
        <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
          Familiares
        </a>
      </li>
      <li class="nav-item m-tabs__item">
        <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_2" role="tab">
          Antecedentes
        </a>
      </li>
    </ul>
    <form name="frm_heredofamiliares" id="frm_heredofamiliares" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
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
        <!-- <?php include 'familiares.php'; ?>-->
        <%@include file="/servicios/heredofamiliares/familiares.jsp"%>
       </div>
       <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
         <table class="table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
               <tr>
                  <th>Categoría</th>
                  <th>Madre</th>
                  <th>Especifique</th>
                  <th>Padre</th>
                  <th>Especifique</th>
                  <th>Hermanos</th>
                  <th>Especifique</th>
                  <th>Tíos</th>
                  <th>Especifique</th>
               </tr>
            </thead>
            <tbody>
               <tr>
                  <td><br>Diabetes Mellitus</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                       <label>
                         <input type="checkbox" name="ant_diabetes_madre" id="ant_diabetes_madre" value="1" data-enable_input="ant_diabetes_madre_des">
                         <span></span>
                       </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ant_diabetes_madre_des" name="ant_diabetes_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ant_diabetes_padre" id="ant_diabetes_padre" value="1" data-enable_input="ant_diabetes_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ant_diabetes_padre_des" name="ant_diabetes_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ant_diabetes_hermanos" id="ant_diabetes_hermanos" value="1" data-enable_input="ant_diabetes_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control m-input m-input--air" id="ant_diabetes_hermanos_des" name="ant_diabetes_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ant_diabetes_tios" id="ant_diabetes_tios" value="1" data-enable_input="ant_diabetes_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ant_diabetes_tios_des" name="ant_diabetes_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Hipertensión Arterial</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ha_madre" id="ha_madre" value="1" data-enable_input="ha_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ha_madre_des" name="ha_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ha_padre" id="ha_padre" value="1" data-enable_input="ha_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ha_padre_des" name="ha_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ha_hermanos" id="ha_hermanos" value="1" data-enable_input="ha_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ha_hermanos_des" name="ha_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ha_tios" id="ha_tios" value="1" data-enable_input="ha_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ha_tios_des" name="ha_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Obsesidad</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ob_madre" id="ob_madre" value="1" data-enable_input="ob_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ob_madre_des" name="ob_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ob_padre" id="ob_padre" value="1" data-enable_input="ob_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ob_padre_des" name="ob_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ob_hermanos" id="ob_hermanos" value="1" data-enable_input="ob_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ob_hermanos_des" name="ob_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ob_tios" id="ob_tios" value="1" data-enable_input="ob_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ob_tios_des" name="ob_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Cardiopatia Isquemica</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="car_madre" id="car_madre" value="1" data-enable_input="car_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="car_madre_des" name="car_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="car_padre" id="car_padre" value="1" data-enable_input="car_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="car_padre_des" name="car_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="car_hermanos" id="car_hermanos" value="1" data-enable_input="car_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="car_hermanos_des" name="car_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="car_tios" id="car_tios" value="1" data-enable_input="car_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="car_tios_des" name="car_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Efermedad Vascular Cerebral</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="evc_madre" id="evc_madre" value="1" data-enable_input="evc_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="evc_madre_des" name="evc_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="evc_padre" id="evc_padre" value="1" data-enable_input="evc_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="evc_padre_des" name="evc_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="evc_hermanos" id="evc_hermanos" value="1" data-enable_input="evc_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="evc_hermanos_des" name="evc_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="evc_tios" id="evc_tios" value="1" data-enable_input="evc_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="evc_tios_des" name="evc_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Infarto Agudo Miocardio</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="iam_madre" id="iam_madre" value="1" data-enable_input="iam_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="iam_madre_des" name="iam_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="iam_padre" id="iam_padre" value="1" data-enable_input="iam_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="iam_padre_des" name="iam_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="iam_hermanos" id="iam_hermanos" value="1" data-enable_input="iam_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="iam_hermanos_des" name="iam_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="iam_tios" id="iam_tios" value="1" data-enable_input="iam_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="iam_tios_des" name="iam_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Patología de Tiroides</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="pt_madre" id="pt_madre" value="1" data-enable_input="pt_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="pt_madre_des" name="pt_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="pt_padre" id="pt_padre" value="1" data-enable_input="pt_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="pt_padre_des" name="pt_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="pt_hermanos" id="pt_hermanos" value="1" data-enable_input="pt_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="pt_hermanos_des" name="pt_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="pt_tios" id="pt_tios" value="1" data-enable_input="pt_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="pt_tios_des" name="pt_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Enfermedades Neoplasicas</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="en_madre" id="en_madre" value="1" data-enable_input="en_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="en_madre_des" name="en_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="en_padre" id="en_padre" value="1" data-enable_input="en_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="en_padre_des" name="en_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="en_hermanos" id="en_hermanos" value="1" data-enable_input="en_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="en_hermanos_des" name="en_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="en_tios" id="en_tios" value="1" data-enable_input="en_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="en_tios_des" name="en_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Otras Enfermedades Relevantes</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="oer_madre" id="oer_madre" value="1" data-enable_input="oer_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="oer_madre_des" name="oer_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="oer_padre" id="oer_padre" value="1" data-enable_input="oer_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="oer_padre_des" name="oer_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="oer_hermanos" id="oer_hermanos" value="1" data-enable_input="oer_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="oer_hermanos_des" name="oer_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="oer_tios" id="oer_tios" value="1" data-enable_input="oer_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="oer_tios_des" name="oer_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Nefropatias</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="nef_madre" id="nef_madre" value="1" data-enable_input="nef_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="nef_madre_des" name="nef_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="nef_padre" id="nef_padre" value="1" data-enable_input="nef_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="nef_padre_des" name="nef_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="nef_hermanos" id="nef_hermanos" value="1" data-enable_input="nef_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="nef_hermanos_des" name="nef_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="nef_tios" id="nef_tios" value="1" data-enable_input="nef_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="nef_tios_des" name="nef_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Enfermedades Psquiatricas</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ep_madre" id="ep_madre" value="1" data-enable_input="ep_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ep_madre_des" name="ep_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ep_padre" id="ep_padre" value="1" data-enable_input="ep_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control m-input m-input--air" id="ep_padre_des" name="ep_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ep_hermanos" id="ep_hermanos" value="1" data-enable_input="ep_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ep_hermanos_des" name="ep_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="ep_tios" id="ep_tios" value="1" data-enable_input="ep_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="ep_tios_des" name="ep_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Hepatopatias</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="hepa_madre" id="hepa_madre" value="1" data-enable_input="hepa_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="hepa_madre_des" name="hepa_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="hepa_padre" id="hepa_padre" value="1" data-enable_input="hepa_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="hepa_padre_des" name="hepa_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="hepa_hermanos" id="hepa_hermanos" value="1" data-enable_input="hepa_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="hepa_hermanos_des" name="hepa_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="hepa_tios" id="hepa_tios" value="1" data-enable_input="hepa_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="hepa_tios_des" name="hepa_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Infectocontagiosas</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="inf_madre" id="inf_madre" value="1" data-enable_input="inf_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="inf_madre_des" name="inf_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="inf_padre" id="inf_padre" value="1" data-enable_input="inf_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="inf_padre_des" name="inf_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="inf_hermanos" id="inf_hermanos" value="1" data-enable_input="inf_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="inf_hermanos_des" name="inf_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="inf_tios" id="inf_tios" value="1" data-enable_input="inf_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="inf_tios_des" name="inf_tios_des" rows="1" disabled></textarea></td>
               </tr>
               <tr>
                  <td>Hematologicas</td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="hemato_madre" id="hemato_madre" value="1" data-enable_input="hemato_madre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="hemato_madre_des" name="hemato_madre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="hemato_padre" id="hemato_padre" value="1" data-enable_input="hemato_padre_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="hemato_padre_des" name="hemato_padre_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="hemato_hermanos" id="hemato_hermanos" value="1" data-enable_input="hemato_hermanos_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="hemato_hermanos_des" name="hemato_hermanos_des" rows="1" disabled></textarea></td>
                  <td>
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input type="checkbox" name="hemato_tios" id="hemato_tios" value="1" data-enable_input="hemato_tios_des">
                     <span></span>
                     </label>
                     </span>
                  </td>
                  <td><textarea maxlength="200" class="form-control" id="hemato_tios_des" name="hemato_tios_des" rows="1" disabled></textarea></td>
               </tr>
            </tbody>
         </table>
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
<script src="assets/js/validaciones/heredofamiliares/valida_heredofamiliares.js"></script>
    