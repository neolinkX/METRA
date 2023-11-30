<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.*"%> 
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>
<%
	TEntorno vEntornoR484 = new TEntorno();
	vEntornoR484.setNumModulo(07);
	TParametro vParametrosR484 = new TParametro(vEntornoR484.getNumModuloStr());
	String CodigoValidaR484 = "";
	session.setAttribute("servicio", "48");
	String cRamaR484 = "4";
	session.setAttribute("rama484", "" + cRamaR484);  
	boolean seagregoR484 = false;
	TDEXPExamCat catR484 = new TDEXPExamCat();
	String cnombreramaR484 = catR484.SentenciaSQL("Select cdscRama from medrama where icveservicio = "
			+ session.getAttribute("servicio") + " and icverama = " + cRamaR484);
%>
<%@ include file="narizPreguntas.jsp"%>







   <div class="m-portlet__body">
   


				<%
				String stTokenR484 ="";
				int contadortipodivR484 = 0;
					for (int i = 0; i < vcEXPResultadosR484.size(); i++) {
						vEXPResultadosR484 = (TVEXPResultado) vcEXPResultadosR484.get(i);
						System.out.println("contadortipodiv=" + contadortipodivR484);
						seagregoR484 = false;
						if (!(vEXPResultadosR484.getICveTpoResp() == 6)) {
							String cFlag = "1";
							String cTipoDato = "";
							String CampoFinalR484 = "";

							if (vEXPResultadosR484.getICveTpoResp() == 1) {
								cTipoDato = "lLogico_";
							}
							if (vEXPResultadosR484.getICveTpoResp() == 2 || vEXPResultadosR484.getICveTpoResp() == 4
									|| vEXPResultadosR484.getICveTpoResp() == 7 || vEXPResultadosR484.getICveTpoResp() == 8 || vEXPResultadosR484.getICveTpoResp() == 13) {
								cTipoDato = "cCaracter_";
							}
							if (vEXPResultadosR484.getICveTpoResp() == 3 || vEXPResultadosR484.getICveTpoResp() == 3) {
								cTipoDato = "dValorI_";
							}

							CampoFinalR484 = cTipoDato + "" + cFlag + "" + vEXPResultadosR484.getICveSintoma();

							if (contadortipodivR484 == 0) {
				%>
				<div class="form-group m-form__group row">
					<%
						}
								if (contadortipodivR484 >= 0) {
									System.out.println("Sintoma = " + vEXPResultadosR484.getICveSintoma() + " Dato = "
											+ vEXPResultadosR484.getICveTpoResp());

									if (vEXPResultadosR484.getICveTpoResp() == 1) {
										seagregoR484 = true;
					%>
				         <label for="int_fiebre" class="col-3 col-form-label">
				       <%=vEXPResultadosR484.getCPregunta()%>
				        </label>
				                   <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
				              <input name="<%=CampoFinalR484%>" type="checkbox">
				             <span></span>
               </label>
               </span>
            </div>
         </div>
				      


					<%
						}

									if (vEXPResultadosR484.getICveTpoResp() == 2 ) {
										seagregoR484 = true;
					%>
					
						 <label for="int_fiebre" class="col-3 col-form-label"> <%=vEXPResultadosR484.getCPregunta()%>
						</label>
						         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label> 
						<input type="text" class="form-control m-input"
							id="<%=CampoFinalR484%>" name="<%=CampoFinalR484%>" maxlength="200"
							> <span> <%=vEXPResultadosR484.getCEtiqueta()%>
						</span>
               </label>
               </span>
            </div>
         </div>
					

					<%
					
									}		
									
					if (vEXPResultadosR484.getICveTpoResp() == 8 || vEXPResultadosR484.getICveTpoResp() == 13) {
						seagregoR484 = true;
						TDMEDRespSint respuestas = new TDMEDRespSint();
	%>

			             <label for="int_fiebre" class="col-3 col-form-label">
			             <%=vEXPResultadosR484.getCPregunta()%>
			             </label>
			                      <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
			             <select class="form-control m-select2 styleSelect2" title="Seleccione" id="<%=CampoFinalR484%>" name="<%=CampoFinalR484%>" >
			                <option value="-1">Seleccione</option><%=respuestas.FindByAllOption(" icveservicio = "+session.getAttribute("servicio")+" and icverama = "+cRamaR484 +" and icvesintoma = "+vEXPResultadosR484.getICveSintoma()) %>
			             </select>
			          <span></span>
               </label>
               </span>
            </div>
         </div>
			          
			          
			          
	<%
		}									
					
									if (vEXPResultadosR484.getICveTpoResp() == 3) {
					%>
					
						<label for="int_fiebre" class="col-3 col-form-label"> <%=vEXPResultadosR484.getCPregunta()%>
						</label> 
						         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
               <input type="text" class="form-control m-input"
							id="<%=CampoFinalR484%>" name="<%=CampoFinalR484%>" maxlength="3" 
							data-number> <span> <%=vEXPResultadosR484.getCEtiqueta()%>
						</span>
               </label>
               </span>
            </div>
         </div>

					<%
						seagregoR484 = true;
									}
									if (vEXPResultadosR484.getICveTpoResp() == 4 || vEXPResultadosR484.getICveTpoResp() == 7) {
										String largocampo = "";
										if (vEXPResultadosR484.getICveTpoResp() == 4) {
											largocampo = "500";
										} else {
											largocampo = "2000";
										}
					%>
					
					<label for="int_fiebre" class="col-3 col-form-label">
							<%=vEXPResultadosR484.getCPregunta()%></label>
							
					<div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
							<textarea maxlength="<%=largocampo%>"
								class="form-control m-input m-input--air" id="<%=CampoFinalR484%>"
								name="<%=CampoFinalR484%>" rows="2"></textarea>
						<span> <%=vEXPResultadosR484.getCEtiqueta()%>
						</span>
					
               </label>
               </span>
            </div>
         </div>

					<%
						seagregoR484 = true;
									}
									if (seagregoR484) {
										System.out.println("Cumplio");
									} else {
										System.out.println("No cumplio");
									}

								}
								if (contadortipodivR484 == 1) {
					%>
				 </div>
				<%

				contadortipodivR484 = 0;
						} else {
							contadortipodivR484++;
						}

						///Codigo para Validacion de campos
						if (vEXPResultadosR484.getLObligatorio() == 1 && (vEXPResultadosR484.getICveTpoResp()!=1 && vEXPResultadosR484.getICveTpoResp()!=4)) {
							if (CodigoValidaR484.length() > 0) {
								CodigoValidaR484 = CodigoValidaR484 + ",";
							}
							if(vEXPResultadosR484.getICveTpoResp()==8){
								CodigoValidaR484 = CodigoValidaR484 + CampoFinalR484 + ":{ valueNotEquals: '-1'}";
							}else{
								CodigoValidaR484 = CodigoValidaR484 + CampoFinalR484 + ":{ required: true, min: 0, max: 299 }";	
							}
							
						} else {
							//CodigoValida = CodigoValida + CampoFinal + ":{ required: false, min: 0, max: 299 }";
						}
						stTokenR484=stTokenR484+""+CampoFinalR484+"|";
					} ///No es un titulo
					System.out.println("salida contadortipodiv=" + contadortipodivR484);
				} ////Fin del Ciclo
				System.out.println("-stToken="+stTokenR484);
				session.setAttribute("stToken",stTokenR484);
				
				
				if(contadortipodivR484==1){
					%></div><% 
				}
				
				%>
<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions">
					<button type="submit" class="btn btn-primary">Guardar</button>
					<button type="reset" class="btn btn-secondary">Cancel</button>
				</div>
			</div>

			
			

   
   
   </div>




<script>
$("select").select2({minimumResultsForSearch: -1});

function guarda_signos_vitales(){
	var options = {type:'question',title:'¿Desea guardar <%=cnombreramaR484%>?'}
  modalConfirm(function (){
    $.ajax({
    	url: 'grdservicios/guardaServicios.jsp',
        type: 'POST',
        data: $("#frm_interrogatorio").serialize(),
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
 
 $("#frm_interrogatorio").validate({
	  rules: {
<%=CodigoValidaR484%>
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

 $.validator.addMethod("valueNotEquals", function(value, element, arg){
	  return arg !== value;
	 }, "Value must not equal arg.");
 
</script>
