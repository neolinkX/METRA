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
	session.setAttribute("servicio", "48");
	String cRama = "15";
	session.setAttribute("rama", ""+cRama);
	boolean seagrego = false;
	TDEXPExamCat cat = new TDEXPExamCat();
	String cnombrerama = cat.SentenciaSQL("Select cdscrama from medrama where icveservicio = "+session.getAttribute("servicio")+" and icverama = "+cRama);

%>
<%@ include file="/persona/datosServicios.jsp"%>







   <div class="m-portlet__body">
   


				<%
				String stToken ="";
				int contadortipodiv = 0;
					for (int i = 0; i < vcEXPResultados.size(); i++) {
						vEXPResultados = (TVEXPResultado) vcEXPResultados.get(i);
						System.out.println("contadortipodiv=" + contadortipodiv);
						seagrego = false;
						if (!(vEXPResultados.getICveTpoResp() == 6)) {
							String cFlag = ""+cRama;
							String cTipoDato = "";
							String CampoFinal = "";

							if (vEXPResultados.getICveTpoResp() == 1) {
								cTipoDato = "lLogico_";
							}
							if (vEXPResultados.getICveTpoResp() == 2 || vEXPResultados.getICveTpoResp() == 4
									|| vEXPResultados.getICveTpoResp() == 7 || vEXPResultados.getICveTpoResp() == 8 || vEXPResultados.getICveTpoResp() == 13) {
								cTipoDato = "cCaracter_";
							}
							if (vEXPResultados.getICveTpoResp() == 3 || vEXPResultados.getICveTpoResp() == 3) {
								cTipoDato = "dValorI_";
							}

							CampoFinal = cTipoDato + "" + cFlag + "" + vEXPResultados.getICveSintoma();

							if (contadortipodiv == 0) {
				%>
				<div class="form-group m-form__group row">
					<%
						}
								if (contadortipodiv >= 0) {
									System.out.println("Sintoma = " + vEXPResultados.getICveSintoma() + " Dato = "
											+ vEXPResultados.getICveTpoResp());

									if (vEXPResultados.getICveTpoResp() == 1) {
										seagrego = true;
					%>
				         <label for="int_fiebre" class="col-3 col-form-label">
				       <%=vEXPResultados.getCPregunta()%>
				        </label>
				                   <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
				              <input name="<%=CampoFinal%>" type="checkbox">
				             <span></span>
               </label>
               </span>
            </div>
         </div>
				      


					<%
						}

									if (vEXPResultados.getICveTpoResp() == 2 ) {
										seagrego = true;
					%>
					
						 <label for="int_fiebre" class="col-3 col-form-label"> <%=vEXPResultados.getCPregunta()%>
						</label>
						         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label> 
						<input type="text" class="form-control m-input"
							id="<%=CampoFinal%>" name="<%=CampoFinal%>" maxlength="200"
							> <span> <%=vEXPResultados.getCEtiqueta()%>
						</span>
               </label>
               </span>
            </div>
         </div>
					

					<%
					
									}		
									
					if (vEXPResultados.getICveTpoResp() == 8 || vEXPResultados.getICveTpoResp() == 13) {
						seagrego = true;
						TDMEDRespSint respuestas = new TDMEDRespSint();
	%>

			             <label for="int_fiebre" class="col-3 col-form-label">
			             <%=vEXPResultados.getCPregunta()%>
			             </label>
			                      <div class="col-2">
			             <select class="form-control m-select2 styleSelect2" title="Seleccione" id="<%=CampoFinal%>" name="<%=CampoFinal%>" >
			                <option value="-1">Seleccione</option><%=respuestas.FindByAllOption(" icveservicio = "+session.getAttribute("servicio")+" and icverama = "+cRama +" and icvesintoma = "+vEXPResultados.getICveSintoma()) %>
			             </select>
         </div>
			          
			          
			          
	<%
		}									
					
									if (vEXPResultados.getICveTpoResp() == 3) {
					%>
					
						<label for="int_fiebre" class="col-3 col-form-label"> <%=vEXPResultados.getCPregunta()%>
						</label> 
						         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
               <input type="text" class="form-control m-input"
							id="<%=CampoFinal%>" name="<%=CampoFinal%>" maxlength="3"
							data-number> <span> <%=vEXPResultados.getCEtiqueta()%>
						</span>
               </label>
               </span>
            </div>
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
					
					<label for="int_fiebre" class="col-3 col-form-label">
							<%=vEXPResultados.getCPregunta()%></label>
							
					<div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
							<textarea maxlength="<%=largocampo%>"
								class="form-control m-input m-input--air" id="<%=CampoFinal%>"
								name="<%=CampoFinal%>" rows="2"></textarea>
						<span> <%=vEXPResultados.getCEtiqueta()%>
						</span>
					
               </label>
               </span>
            </div>
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
								if (contadortipodiv == 1) {
					%>
				 </div>
				<%

				contadortipodiv = 0;
						} else {
							contadortipodiv++;
						}

						///Codigo para Validacion de campos
						if (vEXPResultados.getLObligatorio() == 1 && (vEXPResultados.getICveTpoResp()!=1 && vEXPResultados.getICveTpoResp()!=4)) {
							if (CodigoValida.length() > 0) {
								CodigoValida = CodigoValida + ",";
							}
							if(vEXPResultados.getICveTpoResp()==8){
								CodigoValida = CodigoValida + CampoFinal + ":{ valueNotEquals: '-1'}";
							}else{
								CodigoValida = CodigoValida + CampoFinal + ":{ required: true, min: 0, max: 299 }";	
							}
							
						} else {
							//CodigoValida = CodigoValida + CampoFinal + ":{ required: false, min: 0, max: 299 }";
						}
						stToken=stToken+""+CampoFinal+"|";
					} ///No es un titulo
					System.out.println("salida contadortipodiv=" + contadortipodiv);
				} ////Fin del Ciclo
				System.out.println("-stToken="+stToken);
				session.setAttribute("stToken",stToken);
				
				
				if(contadortipodiv==1){
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
	var options = {type:'question',title:'¿Desea guardar <%=cnombrerama%>?'}
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
<%=CodigoValida%>
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
