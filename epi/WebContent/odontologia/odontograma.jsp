<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>
<%
	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
	String CodigoValida = "";
	session.setAttribute("servicio", "7");
	session.setAttribute("rama", "1");
	boolean seagrego = false;
	TDEXPExamCat cat = new TDEXPExamCat();
	//String cnombrerama = cat.SentenciaSQL("Select cdscrama from medrama where icveservicio = "+session.getAttribute("servicio")+" and icverama = "+request.getParameter("hdrama"));
	
	String stToken = "cCaracter_133|cCaracter_134|cCaracter_135|cCaracter_136|cCaracter_137|cCaracter_138|cCaracter_139|cCaracter_140|"+
					"cCaracter_142|cCaracter_143|cCaracter_144|cCaracter_145|cCaracter_146|cCaracter_18|cCaracter_17|cCaracter_16|cCaracter_15|"+
					"cCaracter_14|cCaracter_13|cCaracter_12|cCaracter_11|cCaracter_124|cCaracter_123|cCaracter_122|cCaracter_121|cCaracter_120|"+
					"cCaracter_119|cCaracter_118|cCaracter_117|cCaracter_19|cCaracter_110|cCaracter_111|cCaracter_112|cCaracter_113|cCaracter_114|"+
					"cCaracter_115|cCaracter_116|cCaracter_125|cCaracter_126|cCaracter_127|cCaracter_128|cCaracter_129|cCaracter_130|cCaracter_131|cCaracter_132|";

	session.setAttribute("stToken",stToken);

	int lconcluido = cat.FindByInt("Select lconcluido from exprama where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
			" and icveservicio = "+session.getAttribute("servicio")+"  and icverama = "+session.getAttribute("rama"));
	int usuario = 0;
	String fechaconcluido = "";
	String cusuario = "";
	if(lconcluido==1){
		usuario = cat.FindByInt("Select icveusuaplica from exprama where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
				" and icveservicio = "+session.getAttribute("servicio")+"  and icverama = "+session.getAttribute("rama"));
		fechaconcluido = cat.SentenciaSQL("Select tsfin from exprama where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
				" and icveservicio = "+session.getAttribute("servicio")+"  and icverama = "+session.getAttribute("rama"));
		cusuario = cat.SentenciaSQL("Select cnombre || ' ' || cappaterno || ' ' || capmaterno from segusuario where icveusuario = "+usuario);
		}
	
%>

      
<form id="frm_odontograma" name="frm_odontograma" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
   <div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
      <div class="m-portlet__head" style="background:#f7f8fa">
         <div class="m-portlet__head-caption">
            <div class="m-portlet__head-title">
               <span class="m-portlet__head-icon">
               <img src="img/servicios/odontologia.svg" width="20" class="svg-inject signal-strong">
               </span>
               <h3 class="m-portlet__head-text m--font-primary">
                  Servicio Odontología
               </h3>
            </div>
         </div>
         <div class="m-portlet__head-tools">
            <ul class="m-portlet__nav">
               <li class="m-portlet__nav-item">
                  <a href="#" data-portlet-tool="fullscreen" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Pantalla Completa">
                  <i class="la la-expand"></i>
                  </a>
               </li>
            </ul>
         </div>
      </div>

<%
//////////AGREGAR PART1//////////
if(lconcluido == 1){
%>	
	
              <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-8">
                  <div class="m-alert m-alert--outline alert alert-danger alert-dismissible fade show" role="alert">
                      <button type="button" class="close" data-dismiss="" aria-label="Close"></button>
                      <strong>
                        Servicio concluido! <br>
                      </strong>
                          El servicio fue concluido por el Dr. <%=cusuario %> <br>Fecha de conclusion: <%=fechaconcluido.substring(0,19) %> 
                            
                    </div>
                </div>
              </div>
<%	
}else{
//////////AGREGAR FIN PART1//////////	
%>


<%@ include file="/persona/datosServicios.jsp"%>
      
      <div class="m-portlet__body">
         <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
            <li class="nav-item m-tabs__item">
               <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
               Datos Generales
               </a>
            </li>
            <li class="nav-item m-tabs__item">
               <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_2" role="tab">
               Odontograma
               </a>
            </li>
            <li class="nav-item m-tabs__item">
               <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_3" role="tab">
               Digitalización
               </a>
            </li>
         </ul>
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
               <div class="alert m-alert m-alert--default" role="alert">
                  Datos Generales
               </div>
               <div class="form-group m-form__group row" align="center">
                  <label for="cCaracter_133" class="col-2 col-form-label">
                  Labios:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" name="cCaracter_133" id="cCaracter_133" class="form-control" rows="1" data-set_max_word="200"></textarea>
                  </div>
                  <label for="cCaracter_134" class="col-2 col-form-label">
                  Encias:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_134" name="cCaracter_134" rows="1" data-set_max_word="200"></textarea>
                  </div>
               </div>
               <div class="form-group m-form__group row" align="center">
                  <label for="cCaracter_135" class="col-2 col-form-label">
                  Paladar:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_135" name="cCaracter_135" rows="1" data-set_max_word="200"></textarea>
                  </div>
                  <label for="cCaracter_136" class="col-2 col-form-label">
                  Lengua:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_136" name="cCaracter_136" rows="1" data-set_max_word="200"></textarea>
                  </div>
               </div>
               <div class="form-group m-form__group row" align="center">
                  <label for="cCaracter_137" class="col-2 col-form-label">
                  Piso de Boca:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_137" name="cCaracter_137" rows="1" data-set_max_word="200"></textarea>
                  </div>
                  <label for="cCaracter_138" class="col-2 col-form-label">
                  Mucosa Bucal:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_138" name="cCaracter_138" rows="1" data-set_max_word="200"></textarea>
                  </div>
               </div>
               <div class="form-group m-form__group row" align="center">
                  <label for="cCaracter_139" class="col-2 col-form-label">
                  Orofaringe:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_139" name="cCaracter_139" rows="1" data-set_max_word="200"></textarea>
                  </div>
                  <label for="cCaracter_140" class="col-2 col-form-label">
                  Art.Temporo Mandibular:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_140" name="cCaracter_140" rows="1" data-set_max_word="200"></textarea>
                  </div>
               </div>
               <div class="form-group m-form__group row" align="center">
                  <label for="cCaracter_142" class="col-2 col-form-label">
                  Otras Alteraciones Dentarias:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_142" name="cCaracter_142" rows="1" data-set_max_word="200"></textarea>
                  </div>
                  <label for="cCaracter_143" class="col-2 col-form-label">
                  Maloclusiones:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_143" name="cCaracter_143" rows="1" data-set_max_word="200"></textarea>
                  </div>
               </div>
               <div class="form-group m-form__group row" align="center">
                  <label for="cCaracter_144" class="col-2 col-form-label">
                  Indice de Higiene Oral (Simplificado):
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_144" name="cCaracter_144" rows="1" data-set_max_word="200"></textarea>
                  </div>
                  <label for="cCaracter_145" class="col-2 col-form-label">
                  Otros:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_145" name="cCaracter_145" rows="1" data-set_max_word="200"></textarea>
                  </div>
               </div>
               <div class="form-group m-form__group row" align="center">
                  <label for="cCaracter_146" class="col-2 col-form-label">
                  Conclusiones:
                  </label>
                  <div class="col-4">
                     <textarea maxlength="200" class="form-control" id="cCaracter_146" name="cCaracter_146" rows="1" data-set_max_word="200"></textarea>
                  </div>
               </div>
            </div>
            <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
               <div class="alert m-alert m-alert--default" role="alert">
                  Odontograma
               </div>
               <br>
               <input type="hidden" value="4" name="cCaracter_18" id="odo_18">
               <input type="hidden" value="4" name="cCaracter_17" id="odo_17">
               <input type="hidden" value="4" name="cCaracter_16" id="odo_16">
               <input type="hidden" value="4" name="cCaracter_15" id="odo_15">
               <input type="hidden" value="4" name="cCaracter_14" id="odo_14">
               <input type="hidden" value="4" name="cCaracter_13" id="odo_13">
               <input type="hidden" value="4" name="cCaracter_12" id="odo_12">
               <input type="hidden" value="4" name="cCaracter_11" id="odo_11">
               <input type="hidden" value="4" name="cCaracter_124" id="odo_48">
               <input type="hidden" value="4" name="cCaracter_123" id="odo_47">
               <input type="hidden" value="4" name="cCaracter_122" id="odo_46">
               <input type="hidden" value="4" name="cCaracter_121" id="odo_45">
               <input type="hidden" value="4" name="cCaracter_120" id="odo_44">
               <input type="hidden" value="4" name="cCaracter_119" id="odo_43">
               <input type="hidden" value="4" name="cCaracter_118" id="odo_42">
               <input type="hidden" value="4" name="cCaracter_117" id="odo_41">
               <input type="hidden" value="4" name="cCaracter_19" id="odo_21">
               <input type="hidden" value="4" name="cCaracter_110" id="odo_22">
               <input type="hidden" value="4" name="cCaracter_111" id="odo_23">
               <input type="hidden" value="4" name="cCaracter_112" id="odo_24">
               <input type="hidden" value="4" name="cCaracter_113" id="odo_25">
               <input type="hidden" value="4" name="cCaracter_114" id="odo_26">
               <input type="hidden" value="4" name="cCaracter_115" id="odo_27">
               <input type="hidden" value="4" name="cCaracter_116" id="odo_28">
               <input type="hidden" value="4" name="cCaracter_125" id="odo_31">
               <input type="hidden" value="4" name="cCaracter_126" id="odo_32">
               <input type="hidden" value="4" name="cCaracter_127" id="odo_33">
               <input type="hidden" value="4" name="cCaracter_128" id="odo_34">
               <input type="hidden" value="4" name="cCaracter_129" id="odo_35">
               <input type="hidden" value="4" name="cCaracter_130" id="odo_36">
               <input type="hidden" value="4" name="cCaracter_131" id="odo_37">
               <input type="hidden" value="4" name="cCaracter_132" id="odo_38">
               <div class="col-md-12">
                  <div class="form-group m-form__group row">
                     <div class="col-2">
                        <div class="row">
                           <div class="col-1 odonto1" title="18">&nbsp;</div>
                           <div class="col-1">Caries</div>
                        </div>
                     </div>
                     <div class="col-2">
                        <div class="row">
                           <div class="col-1 odonto2" title="18">&nbsp;</div>
                           <div class="col-1">Perdido</div>
                        </div>
                     </div>
                     <div class="col-4">
                        <div class="row">
                           <div class="col-1 odonto4" id="odomap_18" title="18">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_17" title="17">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_16" title="16">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_15" title="15">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_14" title="14">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_13" title="13">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_12" title="12">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_11" title="11">&nbsp;</div>
                        </div>
                     </div>
                     <div class="col-4">
                        <div class="row">
                           <div class="col-1 odonto4" id="odomap_21" title="21">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_22" title="22">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_23" title="23">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_24" title="24">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_25" title="25">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_26" title="26">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_27" title="27">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_28" title="28">&nbsp;</div>
                        </div>
                     </div>
                  </div>
                  <div class="form-group m-form__group row">
                     <div class="col-2">
                        <div class="row">
                           <div class="col-1 odonto3" title="18">&nbsp;</div>
                           <div class="col-1">Obturado</div>
                        </div>
                     </div>
                     <div class="col-2">
                        <div class="row">
                           <div class="col-1 odonto4" title="18">&nbsp;</div>
                           <div class="col-1">Sano</div>
                        </div>
                     </div>
                     <div class="col-4">
                        <div class="row">
                           <div class="col-1 odonto4" id="odomap_48" title="48">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_47" title="47">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_46" title="46">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_45" title="45">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_44" title="44">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_43" title="43">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_42" title="42">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_41" title="41">&nbsp;</div>
                        </div>
                     </div>
                     <div class="col-4">
                        <div class="row">
                           <div class="col-1 odonto4" id="odomap_31" title="31">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_32" title="32">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_33" title="33">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_34" title="34">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_35" title="35">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_36" title="36">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_37" title="37">&nbsp;</div>
                           <div class="col-1 odonto4" id="odomap_38" title="38">&nbsp;</div>
                        </div>
                     </div>
                  </div>
                  <iframe width="100%" height="725px" frameborder="0" src="odontologia/odontograma_frame.jsp" scrolling="no"></iframe>
               </div>
            </div>
            <div class="tab-pane" id="m_tabs_6_3" role="tabpanel">
              <div class="form-group m-form__group row">
                 <div class="col-lg-6">
                    <label for="">
                       <h5>Archivo a Digitalizar</h5>
                    </label>
                    <div class="col-lg-12 col-md-12 col-sm-12 text-center">
                       <div id="div_doc_append"></div>
                       <div class="m-dropzone dropzone dz-clickable up_doc_global " id="up_odonto_drop" data-routeupfile="usuarios/upload_dropzone/documentos_digitales|acta_nac|/upload_avatar">
                          <div class="m-dropzone__msg dz-message needsclick">
                             <h3 class="m-dropzone__msg-title">
                                Arrastra aquí el documento correspondiente.
                             </h3>
                             <span class="m-dropzone__msg-desc">
                             Actualmente no hay archivos
                             <strong>
                             no
                             </strong>
                             se han subido archivos.
                             </span>
                          </div>
                       </div>
                    </div>
                 </div>
               </div>
            </div>
         </div>
         <br>
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
   </div>
</form>
<script type="text/javascript">
$(document).ready(function() {
  var num_exp = $("#id_numexp_left").val();
  $("#up_odonto_drop").dropzone({
    url: "usuarios/upload_dropzone/examenes|odontologia|"+num_exp+"/upload_avatar",
    paramName: "file",
    maxFiles: 5,
    maxFilesize: 5, // MB
    acceptedFiles: "image/*",
    accept: function(file, done) {
        //console.log(file);
        done();
    },
    init: function() {
      this.on("success", function(statics,file) {
        var img = file.split("|");
        $( "#div_doc_append" ).append("<input type='hidden' name='archivo_anexos[]' value='examenes/odontologia/"+num_exp+"/"+img[1]+"'>");
      });
    }
   });
});
</script>
<script src="assets/js/validaciones/odontologia/valida_odontologia.js"></script>
<% 
//////////AGREGAR PART2//////////	
}
//////////AGREGAR FIN PART2//////////
%> 