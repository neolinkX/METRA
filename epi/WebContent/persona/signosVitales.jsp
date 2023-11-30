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
session.setAttribute("servicio","11");
session.setAttribute("rama","1");

TDEXPExamCat cat = new TDEXPExamCat();
int lconcluido = cat.FindByInt("Select lconcluido from expservicio where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
																" and icveservicio = "+session.getAttribute("servicio"));
int usuario = 0;
String fechaconcluido = "";
String cusuario = "";
if(lconcluido==1){
usuario = cat.FindByInt("Select icveusuaplica from expservicio where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
		" and icveservicio = "+session.getAttribute("servicio"));
fechaconcluido = cat.SentenciaSQL("Select tsfin from expservicio where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
		" and icveservicio = "+session.getAttribute("servicio"));
cusuario = cat.SentenciaSQL("Select cnombre || ' ' || cappaterno || ' ' || capmaterno from segusuario where icveusuario = "+usuario);
}


String SinEscritura="";

%>
<div
	class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm"
	data-portlet="true" id="m_portlet_tools_2">
	<div class="m-portlet__head" style="background: #f7f8fa">
		<div class="m-portlet__head-caption">
			<div class="m-portlet__head-title">
				<span class="m-portlet__head-icon"> <img
					src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/signos_vitales.svg"
					width="25" class="svg-inject signal-strong">
				</span>
				<h3 class="m-portlet__head-text m--font-primary">Signos Vitales
					y Somatometría</h3>
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
	</div>
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
<%@ include file="/persona/datosServicios.jsp"%>

				<%
		
		int contadortipodiv = 0;
		String stToken = "";
		for (int i = 0; i < vcEXPResultados.size(); i++) {
			vEXPResultados = (TVEXPResultado) vcEXPResultados.get(i);
			if(!(vEXPResultados.getICveTpoResp()==6)){
				String cFlag =  "1";
				String cTipoDato= "";
				String CampoFinal ="";
				
				if(vEXPResultados.getICveTpoResp()==1){
					cTipoDato="lLogico_";
				}
				if(vEXPResultados.getICveTpoResp()==2 || 
						vEXPResultados.getICveTpoResp()==4 || 
						vEXPResultados.getICveTpoResp()==7 || 
						vEXPResultados.getICveTpoResp()==8){
					cTipoDato="cCaracter_";
				}
				if(vEXPResultados.getICveTpoResp()==3 || 
						vEXPResultados.getICveTpoResp()==3){
					cTipoDato="dValorI_";
				}
				
				
				if(vEXPResultados.getICveSintoma()==17){
					SinEscritura=" data-decimal readonly";
				}else{
					SinEscritura="data-number";
				}
				
				CampoFinal=cTipoDato+""+cFlag+""+vEXPResultados.getICveSintoma();
				
			if (contadortipodiv == 0) {
	%>
				<div class="row">
					<%
			}
				if (contadortipodiv >= 0) {
		%>
					<div class="form-group col-md-3">
						<label for="svs_presion_sistolica"> <%=vEXPResultados.getCPregunta() %>
						</label> <input type="text" class="form-control m-input"
							id="<%=CampoFinal %>" name="<%=CampoFinal %>" maxlength="5"
							   <%=SinEscritura %> onclick="IMC()" onchange="IMC()" 
							> <span> <%=vEXPResultados.getCEtiqueta() %>
						</span>
					</div>

					<%
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
		if(CodigoValida.length()>0){
			CodigoValida=CodigoValida+",";
		}
		if(vEXPResultados.getLObligatorio()==1){
			CodigoValida=CodigoValida+CampoFinal+":{ required: true, min: 0, max: 299 }";
		}else{
			CodigoValida=CodigoValida+CampoFinal+":{ required: false, min: 0, max: 299 }";
		}
		stToken=stToken+""+CampoFinal+"|";
		}///No es un titulo
		} ////Fin del Ciclo
		System.out.println("-stToken="+stToken);
		session.setAttribute("stToken",stToken);
		
	%>


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

 
 function IMC(){
	 form = document.forms[0];
     var peso = form.dValorI_16.value ;
     var estatura = form.dValorI_17.value;
     var estaturametros = estatura/100;
     var imc = peso / (estaturametros * estaturametros);
     imc = imc.toFixed(2); //trunca a 2 decimales
   //form.dValorI_18.value = imc;
     if(isNaN(imc)){
     	form.dValorI_117.value = "0";
     }else{
    	 if(imc == 'Infinity'){
    		 form.dValorI_117.value = 0;
    	 }else{
    		 form.dValorI_117.value = imc;	 
    	 }
     	
     }
 }
 
 
</script>
<% 
}
%>