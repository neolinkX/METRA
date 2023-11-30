<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="gob.sct.medprev.dao.TDEXPResultado"%>
<%@ page import="gob.sct.medprev.dao.TDPERDatos"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamAplica"%>
<%@ page import="gob.sct.medprev.vo.TVEXPExamAplica"%>
<%@ page import="gob.sct.medprev.dao.TDEXPServicio"%>
<%@ page import="gob.sct.medprev.vo.TVEXPServicio"%>
<%@ page import="gob.sct.medprev.vo.TVEXPResultado"%>
<%@ page import="java.util.*"%>
	
<%
//////oBTENER EXAMENES /////////////
	String cExpediente = "0";
	cExpediente = session.getAttribute("id")+"";
	
	TDEXPExamAplica dAplica = new TDEXPExamAplica();

	String cBuscar = " where EXPExamAplica.iCveExpediente = "+cExpediente;
	System.out.println("buscando1");
		Vector vcAplica = new Vector();

		try {
			vcAplica = dAplica.FindByAll(cBuscar);
			System.out.println("vcAplica ="+vcAplica.size());
		} catch (Exception e) {
			vcAplica = new Vector();
			e.printStackTrace();
			System.out.println("resultados error");
		}
		
		TVEXPExamAplica vEXPExamAplica;
		int contador = 0;
		String regresa="";
		System.out.println("resultados="+vcAplica.size() );
		if (vcAplica.size() > 0) {
			for (int i = 0; i < vcAplica.size(); i++) {
				vEXPExamAplica = (TVEXPExamAplica) vcAplica.get(i);
				System.out.println(vEXPExamAplica.getICveExpediente());
				if(contador==0){
					//regresa="["+vEXPExamAplica.getICveExpediente()+",\""+vEXPExamAplica.getICveExpediente()+"\",\""+vEXPExamAplica.getCNombre()+"\",\""+vEXPExamAplica.getCApPaterno()+"\",\""+vEXPExamAplica.getCApMaterno()+"\",\""+vEXPExamAplica.getDtSolicitado()+"\"]";
					if(vEXPExamAplica.getLIniciado()==1){
						regresa="["+vEXPExamAplica.getINumExamen()+",\""+vEXPExamAplica.getICveExpediente()+"\",\""+vEXPExamAplica.getCNombre()+" "+vEXPExamAplica.getCApPaterno()+" "+vEXPExamAplica.getCApMaterno()+"\",\""+vEXPExamAplica.getDtSolicitado()+"\",\""+vEXPExamAplica.getDtSolicitado()+"\",\"<a data-paciente='"+vEXPExamAplica.getICveExpediente()+"' id='btn_atender_persona'  data-episodio='"+vEXPExamAplica.getINumExamen()+"' class='btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air' title='Atender Persona'>        <i class='flaticon-user-ok'></i>      </a>  \"]";
						System.out.println("lista 1");
					}else{
						regresa="["+vEXPExamAplica.getINumExamen()+",\""+vEXPExamAplica.getICveExpediente()+"\",\""+vEXPExamAplica.getCNombre()+" "+vEXPExamAplica.getCApPaterno()+" "+vEXPExamAplica.getCApMaterno()+"\",\""+vEXPExamAplica.getDtSolicitado()+"\",\""+vEXPExamAplica.getDtSolicitado()+"\",\"<a data-function='2' class='usr_js_fn_03 btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air'>  <i class='flaticon-cogwheel'></i>  </a>     \"]";
						System.out.println("lista 2");
					}
				}else{
					if(vEXPExamAplica.getLIniciado()==1){
						regresa=regresa+",["+vEXPExamAplica.getINumExamen()+",\""+vEXPExamAplica.getICveExpediente()+"\",\""+vEXPExamAplica.getCNombre()+" "+vEXPExamAplica.getCApPaterno()+" "+vEXPExamAplica.getCApMaterno()+"\",\""+vEXPExamAplica.getDtSolicitado()+"\",\""+vEXPExamAplica.getDtSolicitado()+"\",\"<a data-paciente='"+vEXPExamAplica.getICveExpediente()+"' id='btn_atender_persona'  data-episodio='"+vEXPExamAplica.getINumExamen()+"' class='btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air' title='Atender Persona'>        <i class='flaticon-user-ok'></i>      </a>  \"]";
						System.out.println("lista 3");
					}else{
						regresa=regresa+",["+vEXPExamAplica.getINumExamen()+",\""+vEXPExamAplica.getICveExpediente()+"\",\""+vEXPExamAplica.getCNombre()+" "+vEXPExamAplica.getCApPaterno()+" "+vEXPExamAplica.getCApMaterno()+"\",\""+vEXPExamAplica.getDtSolicitado()+"\",\""+vEXPExamAplica.getDtSolicitado()+"\",\"<a data-function='2' class='usr_js_fn_03 btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air'>  <i class='flaticon-cogwheel'></i>  </a>     \"]";
						System.out.println("lista 4");
					}
				}
				contador++;
			}
			regresa = "{\"draw\":"+contador+",\"recordsTotal\":"+contador+",\"recordsFiltered\":"+contador+",\"data\":["+regresa+"]}";
			
		}else{
			regresa = "{\"draw\":"+contador+",\"recordsTotal\":"+contador+",\"recordsFiltered\":"+contador+",\"data\":["+regresa+"]}";
			//regresa = "{\"draw\":false}";
		}
	
	out.println(regresa);

	//{"draw":1,"recordsTotal":2,"recordsFiltered":2,"data":[[1,586135,"framedev@gmail.com","Usuario1",45,"\r\n      <a data-paciente=\"586135\" id=\"btn_atender_persona\"  data-episodio=\"6\" class=\"btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air\" title=\"Atender Persona\">        <i class=\"flaticon-user-ok\"><\/i>      <\/a>'\r\n      "],[2,"admin1","framedev@gmail.com","Usuario12",6,"\r\n      <a data-function=\"2\" class=\"usr_js_fn_03 btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air\">\r\n        <i class=\"flaticon-cogwheel\"><\/i>\r\n      <\/a>\r\n      "]]}
	
%>	
	
