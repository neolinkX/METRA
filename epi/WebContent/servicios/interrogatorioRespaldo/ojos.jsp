<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.*"%> 
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>
<%
	TEntorno vEntornoR482 = new TEntorno();
	vEntornoR482.setNumModulo(07);
	TParametro vParametrosR482 = new TParametro(vEntornoR482.getNumModuloStr());
	String CodigoValidaR482 = "";
	session.setAttribute("servicio", "48");
	String cRamaR482 = "2";
	session.setAttribute("rama482", "" + cRamaR482);  
	boolean seagregoR482 = false;
	TDEXPExamCat catR482 = new TDEXPExamCat();
	String cnombreramaR482 = catR482.SentenciaSQL("Select cdscRama from medrama where icveservicio = "
			+ session.getAttribute("servicio") + " and icverama = " + cRamaR482);
%>
<%@ include file="ojosPreguntas.jsp"%>







   <div class="m-portlet__body">
   
	<form
		class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed"
		id="frm_svs" name="frm_svs">

				<% 
				String stTokenR482 ="";
				int contadortipodivR482 = 0;
					for (int i = 0; i < vcEXPResultadosR482.size(); i++) {
						vEXPResultadosR482 = (TVEXPResultado) vcEXPResultadosR482.get(i);
						System.out.println("contadortipodiv=" + contadortipodivR482);
						seagregoR482 = false;
						if (!(vEXPResultadosR482.getICveTpoResp() == 6)) {
							String cFlag = "1";
							String cTipoDato = "";
							String CampoFinalR482 = "";

							if (vEXPResultadosR482.getICveTpoResp() == 1) {
								cTipoDato = "lLogico_";
							}
							if (vEXPResultadosR482.getICveTpoResp() == 2 || vEXPResultadosR482.getICveTpoResp() == 4
									|| vEXPResultadosR482.getICveTpoResp() == 7 || vEXPResultadosR482.getICveTpoResp() == 8 || vEXPResultadosR482.getICveTpoResp() == 13) {
								cTipoDato = "cCaracter_";
							}
							if (vEXPResultadosR482.getICveTpoResp() == 3 || vEXPResultadosR482.getICveTpoResp() == 3) {
								cTipoDato = "dValorI_";
							}

							CampoFinalR482 = cTipoDato + "" + cFlag + "" + vEXPResultadosR482.getICveSintoma();

							if (contadortipodivR482 == 0) {
				%>
				<div class="form-group m-form__group row">
					<%
						}
								if (contadortipodivR482 >= 0) {
									System.out.println("Sintoma = " + vEXPResultadosR482.getICveSintoma() + " Dato = "
											+ vEXPResultadosR482.getICveTpoResp());

									if (vEXPResultadosR482.getICveTpoResp() == 1) {
										seagregoR482 = true;
					%>
				         <label for="int_fiebre" class="col-3 col-form-label">
				       <%=vEXPResultadosR482.getCPregunta()%>
				        </label>
				                   <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
				              <input name="<%=CampoFinalR482%>" type="checkbox">
				             <span></span>
               </label>
               </span>
            </div>
         </div>
				      


					<%
						}

									if (vEXPResultadosR482.getICveTpoResp() == 2 ) {
										seagregoR482 = true;
					%>
					
						 <label for="int_fiebre" class="col-3 col-form-label"> <%=vEXPResultadosR482.getCPregunta()%>
						</label>
						         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label> 
						<input type="text" class="form-control m-input"
							id="<%=CampoFinalR482%>" name="<%=CampoFinalR482%>" maxlength="200"
							> <span> <%=vEXPResultadosR482.getCEtiqueta()%>
						</span>
               </label>
               </span>
            </div>
         </div>
					

					<%
					
									}		
									
					if (vEXPResultadosR482.getICveTpoResp() == 8 || vEXPResultadosR482.getICveTpoResp() == 13) {
						seagregoR482 = true;
						TDMEDRespSint respuestas = new TDMEDRespSint();
	%>

			             <label for="int_fiebre" class="col-3 col-form-label">
			             <%=vEXPResultadosR482.getCPregunta()%>
			             </label>
			                      <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
			             <select class="form-control m-select2 styleSelect2" title="Seleccione" id="<%=CampoFinalR482%>" name="<%=CampoFinalR482%>" >
			                <option value="-1">Seleccione</option><%=respuestas.FindByAllOption(" icveservicio = "+session.getAttribute("servicio")+" and icverama = "+cRamaR482 +" and icvesintoma = "+vEXPResultadosR482.getICveSintoma()) %>
			             </select>
			          <span></span>
               </label>
               </span>
            </div>
         </div>
			          
			          
			          
	<%
		}									
					
									if (vEXPResultadosR482.getICveTpoResp() == 3) {
					%>
					
						<label for="int_fiebre" class="col-3 col-form-label"> <%=vEXPResultadosR482.getCPregunta()%>
						</label> 
						         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
               <input type="text" class="form-control m-input"
							id="<%=CampoFinalR482%>" name="<%=CampoFinalR482%>" maxlength="3" 
							data-number> <span> <%=vEXPResultadosR482.getCEtiqueta()%>
						</span>
               </label>
               </span>
            </div>
         </div>

					<%
						seagregoR482 = true;
									}
									if (vEXPResultadosR482.getICveTpoResp() == 4 || vEXPResultadosR482.getICveTpoResp() == 7) {
										String largocampo = "";
										if (vEXPResultadosR482.getICveTpoResp() == 4) {
											largocampo = "500";
										} else {
											largocampo = "2000";
										}
					%>
					
					<label for="int_fiebre" class="col-3 col-form-label">
							<%=vEXPResultadosR482.getCPregunta()%></label>
							
					<div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
							<textarea maxlength="<%=largocampo%>"
								class="form-control m-input m-input--air" id="<%=CampoFinalR482%>"
								name="<%=CampoFinalR482%>" rows="2"></textarea>
						<span> <%=vEXPResultadosR482.getCEtiqueta()%>
						</span>
					
               </label>
               </span>
            </div>
         </div>

					<%
						seagregoR482 = true;
									}
									if (seagregoR482) {
										System.out.println("Cumplio");
									} else {
										System.out.println("No cumplio");
									}

								}
								if (contadortipodivR482 == 1) {
					%>
				 </div>
				<%

				contadortipodivR482 = 0;
						} else {
							contadortipodivR482++;
						}

						///Codigo para Validacion de campos
						if (vEXPResultadosR482.getLObligatorio() == 1 && (vEXPResultadosR482.getICveTpoResp()!=1 && vEXPResultadosR482.getICveTpoResp()!=4)) {
							if (CodigoValidaR482.length() > 0) {
								CodigoValidaR482 = CodigoValidaR482 + ",";
							}
							if(vEXPResultadosR482.getICveTpoResp()==8){
								CodigoValidaR482 = CodigoValidaR482 + CampoFinalR482 + ":{ valueNotEquals: '-1'}";
							}else{
								CodigoValidaR482 = CodigoValidaR482 + CampoFinalR482 + ":{ required: true, min: 0, max: 299 }";	
							}
							
						} else {
							//CodigoValida = CodigoValida + CampoFinal + ":{ required: false, min: 0, max: 299 }";
						}
						stTokenR482=stTokenR482+""+CampoFinalR482+"|";
					} ///No es un titulo
					System.out.println("salida contadortipodiv=" + contadortipodivR482);
				} ////Fin del Ciclo
				System.out.println("-stToken="+stTokenR482);
				session.setAttribute("stToken",stTokenR482);
				
				
				if(contadortipodivR482==1){
					%></div><% 
				}
				
				%>
				
				<button class="btn btn-primary" onClick="fboton482()" id="boton482">save</button>
<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions">
					<button type="submit" class="btn btn-primary" onClick="boton482()" id="boton482">Guardar</button>
					<button type="reset" class="btn btn-secondary">Cancel</button>
				</div>
			</div>

			
			

   
   </form>
   
   </div>

<button class="btn btn-primary" onClick="fboton482()" id="boton482">save2</button>


<script>
var boton482 = document.getElementById("boton482");

$("select").select2({minimumResultsForSearch: -1});

function guarda_signos_vitales482(){
	alert("Vitales482");
	var options482 = {type:'question',title:'¿Desea guardar <%=cnombreramaR482%>?'}
	alert("Vitales482-");	
  	modalConfirm(function (){
	  alert("Vitales482=");
		  
	  $.ajax({
    	url: 'grdservicios/guardaServicios2.jsp?newrama=2',
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
    },options482);
  alert("Vitales482+");
}

$("[data-number_dec]").inputmask("decimal", { min: 0, max: 350, allowMinus: false, digits: 1, integerDigits: 3 });
/*$("[data-number_dec]").inputmask({
   mask: "(999)|(999.9{1})|(99.9{1})",
  'alias': 'decimal',
   rightAlign: true,
   'groupSeparator': '.',
   'autoGroup': true
 });*/ 
 
 boton482.onclick = function(e) {	 
	 alert("listo primero");
	 $("#frm_svs").validate({
		  rules: {
	<%=CodigoValidaR482%>
		  },
		  invalidHandler:function(e,r){
		    var i=$("#m_form_1_msg");
		    i.removeClass("m--hide").show(),
		    mApp.scrollTo(i,-200);
		  },
		  submitHandler: function(form) {
		    guarda_signos_vitales482();
		  }
		}); 
	 
	 
	 guarda_signos_vitales482();
	 alert("listo primero3");
 }

 function fboton482(){	 
	 alert("listo :)");
	 $("#frm_svs").validate({
		  rules: {
	<%=CodigoValidaR482%>
		  },
		  invalidHandler:function(e,r){
		    var i=$("#m_form_1_msg");
		    i.removeClass("m--hide").show(),
		    mApp.scrollTo(i,-200);
		  },
		  submitHandler: function(form) {
		    guarda_signos_vitales482();
		  }
		});
	 alert("listo primero4");
 }
 

 $.validator.addMethod("valueNotEquals", function(value, element, arg){
	  return arg !== value;
	 }, "Value must not equal arg.");
 
</script>
