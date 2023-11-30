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
session.setAttribute("servicio","45");
session.setAttribute("rama","1");

TDEXPExamCat cat = new TDEXPExamCat();
int lconcluido = cat.FindByInt("Select lconcluido from expservicio where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
																" and icveservicio = "+session.getAttribute("servicio"));
int usuario = 0;
String fechaconcluido = "";
String cusuario = "";
boolean seagrego = false;
if(lconcluido==1){
usuario = cat.FindByInt("Select icveusuaplica from expservicio where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
		" and icveservicio = "+session.getAttribute("servicio"));
fechaconcluido = cat.SentenciaSQL("Select tsfin from expservicio where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
		" and icveservicio = "+session.getAttribute("servicio"));
cusuario = cat.SentenciaSQL("Select cnombre || ' ' || cappaterno || ' ' || capmaterno from segusuario where icveusuario = "+usuario);
}

%>

<%@ include file="/persona/datosServicios.jsp"%>

 <div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
  <div class="m-portlet__head" style="background:#f7f8fa">
   <div class="m-portlet__head-caption">
    <div class="m-portlet__head-title">
       <span class="m-portlet__head-icon">
       <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/toxicologia.svg" width="25" class="svg-inject signal-strong">
       </span>
       <h3 class="m-portlet__head-text m--font-primary">
          Servicio de Evaluación Toxicológia NOM024
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
 
 
 
 
 
 
<%

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
%>

				<%
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
    <div class="col-md-6">
      <div class="m-portlet__body">
        <div class="m-section">
					<%
						}
								if (contadortipodiv >= 0) {
									System.out.println("Sintoma = " + vEXPResultados.getICveSintoma() + " Dato = "
											+ vEXPResultados.getICveTpoResp());

									if (vEXPResultados.getICveTpoResp() == 1) {
										seagrego = true;
					%>
				      <div class="form-group m-form__group row">
				        <label for="neu_morfologia" class="col-5 col-form-label">
				       <%=vEXPResultados.getCPregunta()%>
				        </label>
				        <div class="col-7" align="center">
				          <span class="m-switch m-switch--sm m-switch--icon">
				            <label>
				              <input name="<%=CampoFinal%>" type="checkbox">
				              <span></span>
				            </label>
				          </span>
				      </div></div>


					<%
						}

									if (vEXPResultados.getICveTpoResp() == 2 || vEXPResultados.getICveTpoResp() == 8) {
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
				</div></div></div></div>
				<div
					class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
				<%
					contadortipodiv = 0;
							} else {
								contadortipodiv++;
							}

							///Codigo para Validacion de campos
							if (CodigoValida.length() > 0) {
								CodigoValida = CodigoValida + ",";
							}
							if (vEXPResultados.getLObligatorio() == 1) {
								CodigoValida = CodigoValida + CampoFinal + ":{ required: true, min: 0, max: 299 }";
							} else {
								CodigoValida = CodigoValida + CampoFinal + ":{ required: false, min: 0, max: 299 }";
							}

						} ///No es un titulo
						System.out.println("salida contadortipodiv=" + contadortipodiv);
					} ////Fin del Ciclo
				%>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 <div class="m-portlet__body">
   <div class="form-group m-form__group">
      <div class="alert m-alert m-alert--default" role="alert">
         Resultados Prueba Presuntiva
      </div>
   </div>
   <form class="m-form m-form--fit m-form--group-seperator-dashed" id="frm_toxicologia" name="frm_toxicologia">

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

    <div class="row">
      <div class="col-md-6">
        <div class="m-portlet__body">
          <div class="m-section">
            <div class="m-form__group form-group row">
              <label for="tox_cocaina" class="col-4 col-form-label">
                Cocaína:
              </label>
              <div class="col-8" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="tox_cocaina">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="m-form__group form-group row">
              <label for="tox_opiaceos" class="col-4 col-form-label">
                Opiáceos:
              </label>
              <div class="col-8" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="tox_opiaceos">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="m-form__group form-group row">
              <label for="tox_anfetaminas" class="col-4 col-form-label">
                Anfetaminas:
              </label>
              <div class="col-8" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="tox_anfetaminas">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="m-form__group form-group row">
              <label for="tox_cannabinoides" class="col-4 col-form-label">
                Cannabinoides:
              </label>
              <div class="col-8" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="tox_cannabinoides">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="m-form__group form-group row">
              <label for="tox_alcohol" class="col-4 col-form-label">
                Alcohol:
              </label>
              <div class="col-8" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="tox_alcohol">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
          </div>
         </div>
      </div>
      <div class="col-md-6">
         <div class="form-group m-form__group">
            <label for="tox_otras">
            Otras, Especifique
            </label>
            <textarea maxlength="500" class="form-control m-input" id="tox_otras" name="tox_otras" rows="8"></textarea>
         </div>
      </div>
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
</form>
<!-- <script src="<?=URL_PUBLIC?>assets/js/validaciones/toxicologia/valida_toxicologia.js"></script> -->

<script>
function guarda_signos_vitales(){
  var options = {type:'question',title:'¿Desea guardar Signos Vitales?'}
  modalConfirm(function (){
    $.ajax({
      //url: 'grdservicios/guardaServicios.jsp',
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
<%=CodigoValida %>
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
<% 
}
%>
 