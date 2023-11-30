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
	TEntorno vEntornoR485 = new TEntorno();
	vEntornoR485.setNumModulo(07);
	TParametro vParametrosR485 = new TParametro(vEntornoR485.getNumModuloStr());
	String CodigoValidaR485 = "";
	session.setAttribute("servicio", "48");
	String cRamaR485 = "5";
	session.setAttribute("rama485", "" + cRamaR485);  
	boolean seagregoR485 = false;
	TDEXPExamCat catR485 = new TDEXPExamCat();
	String cnombreramaR485 = catR485.SentenciaSQL("Select cdscRama from medrama where icveservicio = "
			+ session.getAttribute("servicio") + " and icverama = " + cRamaR485);
%>
<%@ include file="bocaPreguntas.jsp"%>







   <div class="m-portlet__body">
   


				<%
				String stTokenR485 ="";
				int contadortipodivR485 = 0;
					for (int i = 0; i < vcEXPResultadosR485.size(); i++) {
						vEXPResultadosR485 = (TVEXPResultado) vcEXPResultadosR485.get(i);
						System.out.println("contadortipodiv=" + contadortipodivR485);
						seagregoR485 = false;
						if (!(vEXPResultadosR485.getICveTpoResp() == 6)) {
							String cFlag = "1";
							String cTipoDato = "";
							String CampoFinalR485 = "";

							if (vEXPResultadosR485.getICveTpoResp() == 1) {
								cTipoDato = "lLogico_";
							}
							if (vEXPResultadosR485.getICveTpoResp() == 2 || vEXPResultadosR485.getICveTpoResp() == 4
									|| vEXPResultadosR485.getICveTpoResp() == 7 || vEXPResultadosR485.getICveTpoResp() == 8 || vEXPResultadosR485.getICveTpoResp() == 13) {
								cTipoDato = "cCaracter_";
							}
							if (vEXPResultadosR485.getICveTpoResp() == 3 || vEXPResultadosR485.getICveTpoResp() == 3) {
								cTipoDato = "dValorI_";
							}

							CampoFinalR485 = cTipoDato + "" + cFlag + "" + vEXPResultadosR485.getICveSintoma();

							if (contadortipodivR485 == 0) {
				%>
				<div class="form-group m-form__group row">
					<%
						}
								if (contadortipodivR485 >= 0) {
									System.out.println("Sintoma = " + vEXPResultadosR485.getICveSintoma() + " Dato = "
											+ vEXPResultadosR485.getICveTpoResp());

									if (vEXPResultadosR485.getICveTpoResp() == 1) {
										seagregoR485 = true;
					%>
				         <label for="int_fiebre" class="col-3 col-form-label">
				       <%=vEXPResultadosR485.getCPregunta()%>
				        </label>
				                   <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
				              <input name="<%=CampoFinalR485%>" type="checkbox">
				             <span></span>
               </label>
               </span>
            </div>
         </div>
				      


					<%
						}

									if (vEXPResultadosR485.getICveTpoResp() == 2 ) {
										seagregoR485 = true;
					%>
					
						 <label for="int_fiebre" class="col-3 col-form-label"> <%=vEXPResultadosR485.getCPregunta()%>
						</label>
						         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label> 
						<input type="text" class="form-control m-input"
							id="<%=CampoFinalR485%>" name="<%=CampoFinalR485%>" maxlength="200"
							> <span> <%=vEXPResultadosR485.getCEtiqueta()%>
						</span>
               </label>
               </span>
            </div>
         </div>
					

					<%
					
									}		
									
					if (vEXPResultadosR485.getICveTpoResp() == 8 || vEXPResultadosR485.getICveTpoResp() == 13) {
						seagregoR485 = true;
						TDMEDRespSint respuestas = new TDMEDRespSint();
	%>

			             <label for="int_fiebre" class="col-3 col-form-label">
			             <%=vEXPResultadosR485.getCPregunta()%>
			             </label>
			                      <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
			             <select class="form-control m-select2 styleSelect2" title="Seleccione" id="<%=CampoFinalR485%>" name="<%=CampoFinalR485%>" >
			                <option value="-1">Seleccione</option><%=respuestas.FindByAllOption(" icveservicio = "+session.getAttribute("servicio")+" and icverama = "+cRamaR485 +" and icvesintoma = "+vEXPResultadosR485.getICveSintoma()) %>
			             </select>
			          <span></span>
               </label>
               </span>
            </div>
         </div>
			          
			          
			          
	<%
		}									
					
									if (vEXPResultadosR485.getICveTpoResp() == 3) {
					%>
					
						<label for="int_fiebre" class="col-3 col-form-label"> <%=vEXPResultadosR485.getCPregunta()%>
						</label> 
						         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
               <input type="text" class="form-control m-input"
							id="<%=CampoFinalR485%>" name="<%=CampoFinalR485%>" maxlength="3" 
							data-number> <span> <%=vEXPResultadosR485.getCEtiqueta()%>
						</span>
               </label>
               </span>
            </div>
         </div>

					<%
						seagregoR485 = true;
									}
									if (vEXPResultadosR485.getICveTpoResp() == 4 || vEXPResultadosR485.getICveTpoResp() == 7) {
										String largocampo = "";
										if (vEXPResultadosR485.getICveTpoResp() == 4) {
											largocampo = "500";
										} else {
											largocampo = "2000";
										}
					%>
					
					<label for="int_fiebre" class="col-3 col-form-label">
							<%=vEXPResultadosR485.getCPregunta()%></label>
							
					<div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
							<textarea maxlength="<%=largocampo%>"
								class="form-control m-input m-input--air" id="<%=CampoFinalR485%>"
								name="<%=CampoFinalR485%>" rows="2"></textarea>
						<span> <%=vEXPResultadosR485.getCEtiqueta()%>
						</span>
					
               </label>
               </span>
            </div>
         </div>

					<%
						seagregoR485 = true;
									}
									if (seagregoR485) {
										System.out.println("Cumplio");
									} else {
										System.out.println("No cumplio");
									}

								}
								if (contadortipodivR485 == 1) {
					%>
				 </div>
				<%

				contadortipodivR485 = 0;
						} else {
							contadortipodivR485++;
						}

						///Codigo para Validacion de campos
						if (vEXPResultadosR485.getLObligatorio() == 1 && (vEXPResultadosR485.getICveTpoResp()!=1 && vEXPResultadosR485.getICveTpoResp()!=4)) {
							if (CodigoValidaR485.length() > 0) {
								CodigoValidaR485 = CodigoValidaR485 + ",";
							}
							if(vEXPResultadosR485.getICveTpoResp()==8){
								CodigoValidaR485 = CodigoValidaR485 + CampoFinalR485 + ":{ valueNotEquals: '-1'}";
							}else{
								CodigoValidaR485 = CodigoValidaR485 + CampoFinalR485 + ":{ required: true, min: 0, max: 299 }";	
							}
							
						} else {
							//CodigoValida = CodigoValida + CampoFinal + ":{ required: false, min: 0, max: 299 }";
						}
						stTokenR485=stTokenR485+""+CampoFinalR485+"|";
					} ///No es un titulo
					System.out.println("salida contadortipodiv=" + contadortipodivR485);
				} ////Fin del Ciclo
				System.out.println("-stToken="+stTokenR485);
				session.setAttribute("stToken",stTokenR485);
				
				
				if(contadortipodivR485==1){
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
	var options = {type:'question',title:'¿Desea guardar <%=cnombreramaR485%>?'}
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
<%=CodigoValidaR485%>
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

