<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>
<%
	TEntorno vEntorno2 = new TEntorno();
	vEntorno2.setNumModulo(07);
	TParametro vParametros2 = new TParametro(vEntorno2.getNumModuloStr());
	String CodigoValida2 = "";
	session.setAttribute("servicio", "65");
	session.setAttribute("rama", "1");
	String cRama = "1";
	boolean seagrego = false;
	TDEXPExamCat cat2 = new TDEXPExamCat();
	String cnombrerama = cat2.SentenciaSQL("Select cdscrama from medrama where icveservicio = "+session.getAttribute("servicio")+" and icverama = "+cRama);

%>
<%@ include file="/persona/datosServicios.jsp"%>

<div
	class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm"
	data-portlet="true" id="m_portlet_tools_2">
	<!--<div class="m-portlet__head" style="background: #f7f8fa">
		<div class="m-portlet__head-caption">
			<div class="m-portlet__head-title">
				<span class="m-portlet__head-icon"> <img
					src="<%=vParametros2.getPropEspecifica("RutaImgServerExp")%>servicios/cardiologia.svg"
					width="25" class="svg-inject signal-strong">
				</span>
				<h3 class="m-portlet__head-text m--font-primary"> 	EVALUACION CARDIOLOGICA NOM024</h3>
			</div>
		</div>
		<div class="m-portlet__head-tools">
			<ul class="m-portlet__nav">
				<li class="m-portlet__nav-item"><a href=""
					data-portlet-tool="toggle"
					class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill"
					title="" data-original-title="Collapse"> <i class="la la-plus"></i>
				</a></li>
				<li class="m-portlet__nav-item"><a href="#"
					data-portlet-tool="fullscreen"
					class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill"
					title="" data-original-title="Pantalla Completa"> <i
						class="la la-expand"></i>
				</a></li>
			</ul>
		</div>
	</div>-->
	<form
		class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed"
		id="frm_svs" name="frm_svs">
		<div class="m-portlet__body">
			<br>

			<div class="m-form__content">
				<div class="m-alert m-alert--icon alert alert-danger m--hide"
					role="alert" id="m_form_1_msg">
					<div class="m-alert__icon">
						<i class="la la-warning"></i>
					</div>
					<div class="m-alert__text">Favor de Verificar los datos.</div>
					<div class="m-alert__close">
						<button type="button" class="close" data-close="alert"
							aria-label="Close"></button>
					</div>
				</div>


				<%
				String stToken ="";
				int contadortipodiv = 0;
					for (int i = 0; i < vcEXPResultados.size(); i++) {
						vEXPResultados = (TVEXPResultado) vcEXPResultados.get(i);
						System.out.println("contadortipodiv=" + contadortipodiv);
						seagrego = false;
						if (!(vEXPResultados.getICveTpoResp() == 6)) {
							String cFlag = "1";
							String cTipoDato = "";
							String CampoFinal = "";

							if (vEXPResultados.getICveTpoResp() == 1) {
								cTipoDato = "lLogico_";
							}
							if (vEXPResultados.getICveTpoResp() == 2 || vEXPResultados.getICveTpoResp() == 4
									|| vEXPResultados.getICveTpoResp() == 7 || vEXPResultados.getICveTpoResp() == 8) {
								cTipoDato = "cCaracter_";
							}
							if (vEXPResultados.getICveTpoResp() == 3 || vEXPResultados.getICveTpoResp() == 3) {
								cTipoDato = "dValorI_";
							}

							CampoFinal = cTipoDato + "" + cFlag + "" + vEXPResultados.getICveSintoma();

							if (contadortipodiv == 0) {
				%>
				
				<div class="row">
					<%
						}
								if (contadortipodiv >= 0) {
									System.out.println("Sintoma = " + vEXPResultados.getICveSintoma() + " Dato = "
											+ vEXPResultados.getICveTpoResp());

									if (vEXPResultados.getICveTpoResp() == 1) {
										seagrego = true;
					%>
					
				      <div class="form-group" > 
				      <label for="svs_presion_sistolica"> 
				       <%=vEXPResultados.getCPregunta()%> 
				        </label> 
				           <span class="m-switch m-switch--sm m-switch--icon"> 
				            <label> 
				             <input name="<%=CampoFinal%>" type="checkbox" >  &nbsp;
				              <span> </span>
				            </label>
				          </span>
				          
				        </div>

					<%
						}


									if (vEXPResultados.getICveTpoResp() == 2 ) {
										seagrego = true;
					%>
					<div class="form-group col-md-3">
						<label for="svs_presion_sistolica"> <%=vEXPResultados.getCPregunta()%>
						</label> <input type="text" class="form-control m-input"
							id="<%=CampoFinal%>" name="<%=CampoFinal%>" maxlength="200"
							> <span> <%=vEXPResultados.getCEtiqueta()%>
						</span>
					</div>
					<%
									}
									if (vEXPResultados.getICveTpoResp() == 8) {
										seagrego = true;
										TDMEDRespSint respuestas = new TDMEDRespSint();
					%>

										<div class="col-lg-3">
							             <label>
							             <%=vEXPResultados.getCPregunta()%>
							             </label>
							             <select class="form-control m-bootstrap-select" title="Seleccione" id="<%=CampoFinal%>" name="<%=CampoFinal%>" >
							            <%=respuestas.FindByAllOption(" icveservicio = "+session.getAttribute("servicio")+" and icverama = "+ session.getAttribute("rama") +" and icvesintoma = "+vEXPResultados.getICveSintoma()) %>
							             </select>
							          </div>
							          
							          
					<%
						}									
									

				
						
									if (vEXPResultados.getICveTpoResp() == 3) {
					%>
					<div class="form-group col-md-3">
						<label for="svs_presion_sistolica"> <%=vEXPResultados.getCPregunta()%>
						</label> <input type="text" class="form-control m-input"
							id="<%=CampoFinal%>" name="<%=CampoFinal%>" maxlength="3"
							data-number> <span> <%=vEXPResultados.getCEtiqueta()%>
						</span>
					</div>

					<%
						seagrego = true;
									}
									if (vEXPResultados.getICveTpoResp() == 4 || vEXPResultados.getICveTpoResp() == 7) {
										String largocampo = "";
										if (vEXPResultados.getICveTpoResp() == 4) {
											largocampo = "500";
										} else {
											largocampo = "2000";
										}
					%>
					<div class="form-group m-form__group row">
						<label for="svs_presion_sistolica" class="col-2 col-form-label">
							<%=vEXPResultados.getCPregunta()%></label>
							<textarea maxlength="<%=largocampo%>"
								class="form-control m-input m-input--air" id="<%=CampoFinal%>"
								name="<%=CampoFinal%>" rows="2"></textarea>
						<span> <%=vEXPResultados.getCEtiqueta()%>
						</span>
					</div>

					<%
						seagrego = true;
									}
									if (seagrego) {
										System.out.println("Cumplio");
									} else {
										System.out.println("No cumplio");
									}

								}
								if (contadortipodiv == 3) {
					%>
				</div>
				
			<div
					class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
				<%
					contadortipodiv = 0;
							} else {
								contadortipodiv++;
							}


								///Codigo para Validacion de campos
								if (vEXPResultados.getLObligatorio() == 1 && (vEXPResultados.getICveTpoResp()!=1 && vEXPResultados.getICveTpoResp()!=4)) {
									if (CodigoValida2.length() > 0) {
										CodigoValida2 = CodigoValida2 + ",";
									}
									CodigoValida2 = CodigoValida2 + CampoFinal + ":{ required: true, min: 0, max: 299 }";
								} else {
									//CodigoValida = CodigoValida + CampoFinal + ":{ required: false, min: 0, max: 299 }";
								}
								stToken=stToken+""+CampoFinal+"|";
							} ///No es un titulo
							System.out.println("salida contadortipodiv=" + contadortipodiv);
						} ////Fin del Ciclo
						System.out.println("-stToken="+stToken);
						session.setAttribute("stToken",stToken);
				%>


			</div>
			</div>
			<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions">
					<button type="submit" class="btn btn-primary">Guardar</button>
					<button type="reset" class="btn btn-secondary">Cancel</button>
				</div>
			</div>
	</form>
</div>


<script>
$("select").select2({minimumResultsForSearch: -1});

function guarda_signos_vitales(){
	var options = {type:'question',title:'¿Desea guardar <%=cnombrerama%>?'}
  modalConfirm(function (){
    $.ajax({
    	url: 'grdservicios/guardaServicios.jsp',
        type: 'POST',
        data: $("#frm_svs").serialize(),
        dataType: 'json',
        success: function(resp_success){
      	if(resp_success[0].resp=='success'){
          	swal('Se guardó correctamente la información!', '', "success");
          	get_signosVitales();
        	}else{  swal('  '+resp_success[0].resp, '', "error");
        	}},      
        error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PER-06');}
      });
    },options);
}
$("[data-number_dec]").inputmask("decimal", { min: 0, max: 350, allowMinus: false, digits: 1, integerDigits: 3 });
/*$("[data-number_dec]").inputmask({
   mask: "(999)|(999.9{1})|(99.9{1})",
  'alias': 'decimal',
   rightAlign: true,
   'groupSeparator': '.',
   'autoGroup': true
 });*/ 
 
 $("#frm_svs").validate({
	  rules: {
<%=CodigoValida2%>
	  },
	  invalidHandler:function(e,r){
	    var i=$("#m_form_1_msg");
	    i.removeClass("m--hide").show(),
	    mApp.scrollTo(i,-200);
	  },
	  submitHandler: function(form) {
	    guarda_signos_vitales();
	  }
	});

 
</script>
