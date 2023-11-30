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
	TEntorno vEntornoR483 = new TEntorno();
	vEntornoR483.setNumModulo(07);
	TParametro vParametrosR483 = new TParametro(vEntornoR483.getNumModuloStr());
	String CodigoValidaR483 = "";
	session.setAttribute("servicio", "48");
	String cRamaR483 = "3";
	session.setAttribute("rama483", "" + cRamaR483);  
	boolean seagregoR483 = false;
	TDEXPExamCat catR483 = new TDEXPExamCat();
	String cnombreramaR483 = catR483.SentenciaSQL("Select cdscRama from medrama where icveservicio = "
			+ session.getAttribute("servicio") + " and icverama = " + cRamaR483);
%>
<%@ include file="oidosPreguntas.jsp"%>







   <div class="m-portlet__body">
   


				<%
				String stTokenR483 ="";
				int contadortipodivR483 = 0;
					for (int i = 0; i < vcEXPResultadosR483.size(); i++) {
						vEXPResultadosR483 = (TVEXPResultado) vcEXPResultadosR483.get(i);
						System.out.println("contadortipodiv=" + contadortipodivR483);
						seagregoR483 = false;
						if (!(vEXPResultadosR483.getICveTpoResp() == 6)) {
							String cFlag = "1";
							String cTipoDato = "";
							String CampoFinalR483 = "";

							if (vEXPResultadosR483.getICveTpoResp() == 1) {
								cTipoDato = "lLogico_";
							}
							if (vEXPResultadosR483.getICveTpoResp() == 2 || vEXPResultadosR483.getICveTpoResp() == 4
									|| vEXPResultadosR483.getICveTpoResp() == 7 || vEXPResultadosR483.getICveTpoResp() == 8 || vEXPResultadosR483.getICveTpoResp() == 13) {
								cTipoDato = "cCaracter_";
							}
							if (vEXPResultadosR483.getICveTpoResp() == 3 || vEXPResultadosR483.getICveTpoResp() == 3) {
								cTipoDato = "dValorI_";
							}

							CampoFinalR483 = cTipoDato + "" + cFlag + "" + vEXPResultadosR483.getICveSintoma();

							if (contadortipodivR483 == 0) {
				%>
				<div class="form-group m-form__group row">
					<%
						}
								if (contadortipodivR483 >= 0) {
									System.out.println("Sintoma = " + vEXPResultadosR483.getICveSintoma() + " Dato = "
											+ vEXPResultadosR483.getICveTpoResp());

									if (vEXPResultadosR483.getICveTpoResp() == 1) {
										seagregoR483 = true;
					%>
				         <label for="int_fiebre" class="col-3 col-form-label">
				       <%=vEXPResultadosR483.getCPregunta()%>
				        </label>
				                   <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
				              <input name="<%=CampoFinalR483%>" type="checkbox">
				             <span></span>
               </label>
               </span>
            </div>
         </div>
				      


					<%
						}

									if (vEXPResultadosR483.getICveTpoResp() == 2 ) {
										seagregoR483 = true;
					%>
					
						 <label for="int_fiebre" class="col-3 col-form-label"> <%=vEXPResultadosR483.getCPregunta()%>
						</label>
						         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label> 
						<input type="text" class="form-control m-input"
							id="<%=CampoFinalR483%>" name="<%=CampoFinalR483%>" maxlength="200"
							> <span> <%=vEXPResultadosR483.getCEtiqueta()%>
						</span>
               </label>
               </span>
            </div>
         </div>
					

					<%
					
									}		
									
					if (vEXPResultadosR483.getICveTpoResp() == 8 || vEXPResultadosR483.getICveTpoResp() == 13) {
						seagregoR483 = true;
						TDMEDRespSint respuestas = new TDMEDRespSint();
	%>

			             <label for="int_fiebre" class="col-3 col-form-label">
			             <%=vEXPResultadosR483.getCPregunta()%>
			             </label>
			                      <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
			             <select class="form-control m-select2 styleSelect2" title="Seleccione" id="<%=CampoFinalR483%>" name="<%=CampoFinalR483%>" >
			                <option value="-1">Seleccione</option><%=respuestas.FindByAllOption(" icveservicio = "+session.getAttribute("servicio")+" and icverama = "+cRamaR483 +" and icvesintoma = "+vEXPResultadosR483.getICveSintoma()) %>
			             </select>
			          <span></span>
               </label>
               </span>
            </div>
         </div>
			          
			          
			          
	<%
		}									
					
									if (vEXPResultadosR483.getICveTpoResp() == 3) {
					%>
					
						<label for="int_fiebre" class="col-3 col-form-label"> <%=vEXPResultadosR483.getCPregunta()%>
						</label> 
						         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
               <input type="text" class="form-control m-input"
							id="<%=CampoFinalR483%>" name="<%=CampoFinalR483%>" maxlength="3" 
							data-number> <span> <%=vEXPResultadosR483.getCEtiqueta()%>
						</span>
               </label>
               </span>
            </div>
         </div>

					<%
						seagregoR483 = true;
									}
									if (vEXPResultadosR483.getICveTpoResp() == 4 || vEXPResultadosR483.getICveTpoResp() == 7) {
										String largocampo = "";
										if (vEXPResultadosR483.getICveTpoResp() == 4) {
											largocampo = "500";
										} else {
											largocampo = "2000";
										}
					%>
					
					<label for="int_fiebre" class="col-3 col-form-label">
							<%=vEXPResultadosR483.getCPregunta()%></label>
							
					<div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
							<textarea maxlength="<%=largocampo%>"
								class="form-control m-input m-input--air" id="<%=CampoFinalR483%>"
								name="<%=CampoFinalR483%>" rows="2"></textarea>
						<span> <%=vEXPResultadosR483.getCEtiqueta()%>
						</span>
					
               </label>
               </span>
            </div>
         </div>

					<%
						seagregoR483 = true;
									}
									if (seagregoR483) {
										System.out.println("Cumplio");
									} else {
										System.out.println("No cumplio");
									}

								}
								if (contadortipodivR483 == 1) {
					%>
				 </div>
				<%

				contadortipodivR483 = 0;
						} else {
							contadortipodivR483++;
						}

						///Codigo para Validacion de campos
						if (vEXPResultadosR483.getLObligatorio() == 1 && (vEXPResultadosR483.getICveTpoResp()!=1 && vEXPResultadosR483.getICveTpoResp()!=4)) {
							if (CodigoValidaR483.length() > 0) {
								CodigoValidaR483 = CodigoValidaR483 + ",";
							}
							if(vEXPResultadosR483.getICveTpoResp()==8){
								CodigoValidaR483 = CodigoValidaR483 + CampoFinalR483 + ":{ valueNotEquals: '-1'}";
							}else{
								CodigoValidaR483 = CodigoValidaR483 + CampoFinalR483 + ":{ required: true, min: 0, max: 299 }";	
							}
							
						} else {
							//CodigoValida = CodigoValida + CampoFinal + ":{ required: false, min: 0, max: 299 }";
						}
						stTokenR483=stTokenR483+""+CampoFinalR483+"|";
					} ///No es un titulo
					System.out.println("salida contadortipodiv=" + contadortipodivR483);
				} ////Fin del Ciclo
				System.out.println("-stToken="+stTokenR483);
				session.setAttribute("stToken",stTokenR483);
				
				
				if(contadortipodivR483==1){
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
	var options = {type:'question',title:'¿Desea guardar <%=cnombreramaR483%>?'}
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
<%=CodigoValidaR483%>
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
