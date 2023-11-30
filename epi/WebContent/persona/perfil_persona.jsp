<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    //System.out.println("perfil_persona");
    //System.out.println("idPersona="+request.getParameter("idPersona"));
    session.setAttribute("id", request.getParameter("idPersona"));
    %>
    
    <%@ include file="/busqueda/busca_persona_all.jsp"%> 
    
    <%
    if (vcPersonal.size() > 0) {
		for (int i = 0; i < vcPersonal.size(); i++) {
			vPerDatos = (TVPERDatos) vcPersonal.get(i); 
	%>
    
<div id="contenedor_movimientos">
<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm"  data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__head" style="background:#f7f8fa">
      <div class="m-portlet__head-caption">
         <div class="m-portlet__head-title">
            <span class="m-portlet__head-icon">
            <i class="flaticon-statistics"></i>
            </span>
            <h3 class="m-portlet__head-text m--font-primary">
               Ficha Persona
            </h3>
         </div>
      </div>
      <div class="m-portlet__head-tools">
         <ul class="m-portlet__nav">
            <li class="m-portlet__nav-item">
               <a href="" data-portlet-tool="toggle" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Collapse">
               <i class="la la-plus"></i>
               </a>
            </li>

            <li class="m-portlet__nav-item">
               <a href="#" data-portlet-tool="fullscreen" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Pantalla Completa">
               <i class="la la-expand"></i>
               </a>
            </li>
         </ul>
      </div>
   </div>
   <div class="m-portlet__body">
      <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
            <i class="flaticon-profile-1" aria-hidden="true"></i>
            Datos Personales
            </a>
         </li>
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_2" role="tab">
            <i class="flaticon-map-location" aria-hidden="true"></i>
            Direcci&oacute;n
            </a>
         </li>
        <!-- 
         <?php
         /*
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_3" role="tab">
            <i class="flaticon-transport" aria-hidden="true"></i>
            Tr&aacute;mite
            </a>
         </li>
         */
         ?> -->
      </ul>
      <div class="tab-content">
         <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
            <form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed" id="form_alta_persona" name="form_alta_persona">
               <div class="form-group m-form__group row">
                  <div class="col-lg-3">
                     <label>
                     Apellido Paterno
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCApPaterno() %>" disabled>
                  </div>
                  <div class="col-lg-3">
                     <label class="">
                     Apellido Materno:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCApMaterno() %>" disabled>
                  </div>
                  <div class="col-lg-3">
                     <label>
                     Nombre(s):
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCNombre() %>" disabled>
                  </div>
                  <div class="col-lg-3">
                     <label class="">
                     Sexo:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCSexo() %>" disabled>
                    <!--
                    --Ejemplo de texto subrayado a evaluar

                     <span class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary">
                       <a class="nav-link m-tabs__link active" data-toggle="tab">

                       </a>
                     </span>
                   -->
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <div class="col-lg-3">
                     <label class="">
                     Fecha de Nacimiento:
                     </label>
                     <div class="m-input-icon m-input-icon--right">
                        <input type="text" class="form-control m-input date-picker" value="<%=vPerDatos.getDtNacimiento() %>" disabled>
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
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCRFC() %>" disabled>
                  </div>
                  <div class="col-lg-3">
                     <label class="">
                     CURP:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCCURP() %>" disabled>
                  </div>
                  <div class="col-lg-3">
                     <label class="">
                     Licencia:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCLicencia() %>" disabled>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <div class="col-lg-3">
                     <label class="">
                     Licencia Int:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCLicenciaInt() %>" disabled>
                  </div>
                  <div class="col-lg-3 col-md-9 col-sm-12">
                     <label class="">
                     Nacionalidad:
                     </label>
                     <!-- <input type="text" class="form-control m-input" value="<?=$catalog->getNameByIdCatalogo('framework.cm_catalogo','etiqueta','id_cat',$datos_p->id_nacionalidad);?>" disabled> -->
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCDscPaisNac() %>" disabled>
                  </div>
                  <div class="col-lg-3 col-md-9 col-sm-12">
                     <label class="">
                     Lugar de Nacimiento:
                     </label>
                    <!-- <input type="text" class="form-control m-input date-picker" value="<?=$catalog->getNameByIdCatalogo('framework.cm_catalogo','etiqueta','id_cat',$datos_p->estado_nacimiento);?>" disabled> -->
                    <input type="text" class="form-control m-input date-picker" value="<%=vPerDatos.getCDscEstadoNac() %>" disabled>
                  </div>
               </div>
            </form>
         </div>
         <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
            <form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed" id="form_domicilio_persona" name="form_domicilio_persona">
               <div class="form-group m-form__group row">
                  <div class="col-lg-3">
                     <label>
                     Calle:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCCalle() %>" disabled>
                  </div>
                  <div class="col-lg-3">
                     <label class="">
                     No. Exterior:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCNumExt() %>" disabled>
                  </div>
                  <div class="col-lg-3">
                     <label class="">
                     No. Interior:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCNumInt() %>" disabled>
                  </div>
                  <div class="col-lg-3">
                     <label class="">
                     Colonia:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCColonia() %>" disabled>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <div class="col-lg-3">
                     <label>
                     C&oacute;digo Postal:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getICP() %>" disabled>
                  </div>
                  <div class="col-lg-3">
                     <label class="">
                     Pa&iacute;s:
                     </label>
                    <!-- <input type="text" class="form-control m-input" value="<?=$catalog->getNameByIdCatalogo('framework.cm_catalogo','etiqueta','id_cat',$datos_p->dom_id_pais);?>" disabled> -->
                    <input type="text" class="form-control m-input" value="<%=vPerDatos.getCDscPais() %>" disabled>
                  </div>
                  <div class="col-lg-3">
                     <label class="">
                     Estado:
                     </label>
                     <!-- <input type="text" class="form-control m-input" value="<?=$catalog->getNameByIdCatalogo('miscelanea.cat_entidad','entidad','id_entidad',$datos_p->dom_id_estado);?>" disabled> -->
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCDscEstado() %>" disabled>
                  </div>
                  <div class="col-lg-3">
                     <label class="">
                     Municipio:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCDscMunicipio() %>" disabled>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                 <div class="col-lg-3">
                    <label class="">
                    Localidad:
                    </label>
                    <!-- <input type="text" class="form-control m-input" value="<?=$catalog->getNameByIdCatalogo('miscelanea.cat_asentamientos','asentamiento','id_asentamiento',$datos_p->dom_id_localidad);?>" disabled> -->
                    <input type="text" class="form-control m-input" value="<%=vPerDatos.getCDscMunicipio() %>" disabled>
                 </div>
                  <div class="col-lg-3">
                     <label>
                     Telefono:
                     </label>
                     <input type="text" class="form-control m-input" value="<%=vPerDatos.getCTelefono() %>" disabled>
                  </div>
               </div>
            </form>
         </div>

<!-- 
         <?php
         /*
         <div class="tab-pane" id="m_tabs_6_3" role="tabpanel">
            <form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
               <div class="form-group m-form__group row">
                  <div class="col-lg-3 col-md-9 col-sm-12">
                     <label class="">
                     Modo de Transporte:
                     </label>
                     <input type="text" class="form-control m-input" value="Transporte" disabled>
                  </div>
                  <div class="col-lg-3 col-md-9 col-sm-12">
                     <label class="">
                     Categoría:
                     </label>
                     <input type="text" class="form-control m-input" value="Categoría" disabled>
                  </div>
                  <div class="col-lg-3 col-md-9 col-sm-12">
                     <label class="">
                      Puesto:
                     </label>
                    <input type="text" class="form-control m-input" value="Puesto" disabled>
                  </div>
                  <div class="col-lg-3 col-md-9 col-sm-12">
                     <label class="">
                     Motivo:
                     </label>
                    <input type="text" class="form-control m-input" value="Motivo" disabled>
                  </div>
               </div>
            </form>
         </div>
         */
         ?> -->
      </div>
      <div class="m-portlet__foot">
         <div class="row align-items-center">
            <div class="col-lg-12">
              <?php
              // Condicion del permiso para la generacion de este proceso
              if(count($datos_episodio)==0){
              ?>
                <button class="btn btn-primary" data-paciente="<?=$id?>"  id="gen_exp_fn_03">Nuevo Cita (Tr&aacute;mite)</button>
                <button class="btn btn-success" data-paciente="<?=$id?>" id="btn_inicio_procesoEPI">Inicio Proceso EPI</button>
              <?php
              }
              ?>
            </div>
         </div>
      </div>
   </div>

</div>
</div>

    <%
		}	
	}
    
    %>
