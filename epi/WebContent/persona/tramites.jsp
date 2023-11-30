<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>  
<%@ include file="/busqueda/busca_persona_foco.jsp"%>    

<%
String mdotrasnpore = "<option value=\"-1\">Seleccione una opci&oacute;n</option>";

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


%> 
    
<div class="modal fade" id="modalTramites" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document" style="max-width: 1200px !important;">
        <div class="modal-content">
            <div class="modal-header">

                <h5 class="modal-title" id="myModalLabel"><i class="flaticon-transport"></i> Tr&aacute;mites</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="modal_content">
              <div class="row">
                <div class="col-2"></div>
                <div class="col-8 text-center">
                  <table class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <tr class=" text-center">
                      <th>Nombre</th>
                      <th>Sexo</th>
                      <th>Edad</th>
                      <th>RFC</th>
                    </tr>
                    <tr>
                      <td><%=session.getAttribute("nombreExp") %></td>
                      <td><%=session.getAttribute("sexoExp") %></td>
                      <td><%=session.getAttribute("edadExp") %> a&ntilde;os</td>
                      <td><%=session.getAttribute("rfcExp") %></td>
                    </tr>
                  </table>
                </div>
              </div>

              <div class="row">
                <div class="col-12">

            <form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed" id="form_tramites_cita">
              <div class="form-group m-form__group row">
                <!-- <?php
                if(count($datos_episodio)>0){
                ?>
                eXAMEN GENERADO
 				
                <div class="col-lg-3 col-md-9 col-sm-12">
                   <label class="">
                   Modo de Transporte:
                   </label>
                   <div class="m-select2 m-select2--air">
                     <textarea class="form-control m-input" disabled="disabled" rows="5"><?=$datos_episodio['id_modotransporte_tramite']?></textarea>
                   </div>
                </div>
                <div class="col-lg-3 col-md-9 col-sm-12">
                   <label class="">
                   Categor&iacute;a:
                   </label>
                   <div class="m-select2 m-select2--air">
                     <textarea class="form-control m-input" disabled="disabled" rows="5"><?=$datos_episodio['id_categoria_tramite']?></textarea>
                   </div>
                </div>
                <div class="col-lg-3 col-md-9 col-sm-12">
                   <label class="">
                   Puesto:
                   </label>
                   <div class="m-select2 m-select2--air">
                        <textarea class="form-control m-input" disabled="disabled" rows="5"><?=$datos_episodio['id_puesto_tramite']?></textarea>
                   </div>
                </div>
                <div class="col-lg-3 col-md-9 col-sm-12">
                   <label class="">
                   Motivo:
                   </label>
                   <div class="m-select2 m-select2--air">
                     <textarea class="form-control m-input" disabled="disabled" rows="5"><?=$datos_episodio['id_motivo_tramite']?></textarea>
                   </div>
                </div>
                <?php
                  }else{
                ?>-->
                 <div class="col-lg-3 col-md-9 col-sm-12">
                    <label class="">
                    Modo de Transporte:
                    </label>
                    <div class="m-select2 m-select2--air">
                       <select class="form-control" id="id_modo_transporte"  name="id_modo_transporte" data-placeholder="Seleccionar" data-load_datacombo="categoria_tramite" onchange="generaCombosSecundario(this,'cat_tramite_categoria');">
                         
                          <%=mdotrasnpore %>
                       </select>
                    </div>
                 </div>
                 <div class="col-lg-3 col-md-9 col-sm-12">
                    <label class="">
                    Categor&iacute;a:
                    </label>

                    <div class="m-select2 m-select2--air">
                       <select class="form-control" id="categoria_tramite"  name="categoria_tramite" data-placeholder="Seleccionar" data-load_datacombo="puesto_tramite" onchange="generaCombosSecundario(this,'cat_tramite_puesto');">
                       </select>
                    </div>
                 </div>
                 <div class="col-lg-3 col-md-9 col-sm-12">
                    <label class="">
                    Puesto:
                    </label>
                    <div class="m-select2 m-select2--air">
                       <select class="form-control" id="puesto_tramite"  name="puesto_tramite" data-placeholder="Seleccionar">
                       </select>
                    </div>
                 </div>
                 <div class="col-lg-3 col-md-9 col-sm-12">
                    <label class="">
                    Motivo:
                    </label>
                    <div class="m-select2 m-select2--air">
                       <select class="form-control" id="id_motivo_tramite"  name="id_motivo" data-placeholder="Seleccionar">
                         
                         <%=cMotivos %>
                       </select>
                    </div>
                 </div>
              </div>
           </form>
         </div>
       </div>
       <div class="row m--margin-top-10">
         <div class="col-12 text-center">
            <div class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;">
              <button type="button" class="close" data-dismiss="" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
              <strong>Advertencia!</strong> Debe Seleccionar una opci&oacute;n para cada uno de los campos: Modo de Transporte, Categor&iacute;a, Puesto y Motivo.
             </div>
         </div>
       </div>

       <div class="row m--margin-top-10">
         <div class="col-12 text-center">
           <a href="#" class="m-link m--font-transform-u">
						Generar Linea de Captura - Formato de Pago DGPOP
					 </a>
         </div>
       </div>

            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">
                Cerrar
              </button>

              <button type="button" id="btn_guarda_tramite" class="btn btn-primary" onclick="guarda_tarmite_cita(<%=session.getAttribute("id") %>);">
                Guardar
              </button>

            </div>
        </div>
    </div>
</div>
