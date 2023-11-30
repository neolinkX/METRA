<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__body">
       <div class="form-group m-form__group">
         <div class="alert m-alert m-alert--default" role="alert">
           <b>EXPLORACIÓN GINECOLÓGICA</b>
         </div>
       </div>
       <form id="frm_interrogatorio" name="frm_interrogatorio" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
        <div class="row">
           <div class="col-3">
              <ul class="nav nav-pills flex-column m-tabs-line--2x m-tabs-line--primary" role="tablist">
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link active load_tab_content" data-toggle="tab" data-container="interroga_1" data-url_content="servicios/ginecologia/exploracion_mamas.jsp" id="I1" href="#interroga_1">
                    Exploración Clínica de Mamas</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content"  data-toggle="tab" data-container="interroga_2" data-url_content="servicios/ginecologia/exploracion_fisicaGineco.jsp" id="I2"  href="#interroga_2">
                    Favor de Verificar los datos.</a>
                 </li>
              </ul>
           </div>
           <div class="col-9">
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
                  <div class="tab-pane active" id="interroga_1" role="tabpanel">
	    			<%@ include file="/servicios/ginecologia/exploracion_mamas.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_2" role="tabpanel">
                 </div>
                 
              </div>

           </div>
        </div>
       
        </form>
     </div>

</div>