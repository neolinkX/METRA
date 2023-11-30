
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gom.sct.medprev.reglasexp.AlertaSintoma"%>

<%@ include file="/resources/contentTextForms.jsp"%>

<%
System.out.println("res_somatometria_data");
	System.out.println("IDdd=" + request.getParameter("id"));
	System.out.println("IDdd session=" + session.getAttribute("id"));
	String HabilitaDesabilitaCampos = "enabled";
	if(Concluido2>0){
		HabilitaDesabilitaCampos="disabled";
	}
	
	AlertaSintoma alerta = new AlertaSintoma();
	
%>

<form
	class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">

	<%
		int contadortipodiv = 0;
		for (int i = 0; i < vcEXPResultados.size(); i++) {
			vEXPResultados = (TVEXPResultado) vcEXPResultados.get(i);
			if(!(vEXPResultados.getICveTpoResp()==6)){
				System.out.println("/////////////////////////////////////////////////////////////////////////"+vEXPResultados.getCPregunta());
			if (contadortipodiv == 0) {
	%> 
	<div class="form-group m-form__group row">
		<%
			}
				if (contadortipodiv >= 0) {
		%>
		<div class="col-md-3">
			<label for="svs_presion_sistolica"> <%=vEXPResultados.getCPregunta() %>	</label> 
			<%if(vEXPResultados.getICveTpoResp()==3){ %>
				<input type="text" class="form-control m-input" disabled value="<%=vEXPResultados.getDValorIni()%>">
			<%}if(vEXPResultados.getICveTpoResp()==2){ %> 
				<input type="text" class="form-control m-input" disabled value="<%=((vEXPResultados.getCCaracter()!=null)?vEXPResultados.getCCaracter():"-" ) %>">
			<%}if(vEXPResultados.getICveTpoResp()==4){ %> 
				<textarea class="form-control m-input m-input--air" id="hem_metodo" name="hem_metodo" rows="2" maxlength="500" disabled><%=((vEXPResultados.getCCaracter()!=null)?vEXPResultados.getCCaracter():"-" ) %></textarea>
			<%}if(vEXPResultados.getICveTpoResp()==1){
				  String check = "";
					if(vEXPResultados.getLLogico()==1){
						check = "checked";
					}
				%> 
				<input name="hem_frh" type="checkbox" <%=check %> disabled>
			<%}%>
			
			<span><%=vEXPResultados.getCEtiqueta() %></span>
			<br><span><%=alerta.AlertaXSintoma(vEXPResultados.getICveSintoma(), vEXPResultados.getICveTpoResp(), vEXPResultados.getCCaracter()+"", ((int)vEXPResultados.getDValorIni()), 
											vEXPResultados.getICveExpediente()+"", vEXPResultados.getINumExamen()+"", vEXPResultados.getICveServicio(), vEXPResultados.getICveRama()) %></span>
		</div>
		<%
			}
				if (contadortipodiv == 3) {
		%>
	</div>
	<%
		contadortipodiv = 0;
			} else {
				contadortipodiv++;
			}
		}///No es un titulo
		} ////Fin del Ciclo
	%>









