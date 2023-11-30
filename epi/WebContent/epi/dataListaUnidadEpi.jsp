<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="gob.sct.medprev.dao.TDEXPResultado"%>
<%@ page import="gob.sct.medprev.dao.TDPERDatos"%>
<%@ page import="gob.sct.medprev.dao.TDGRLUniMed"%>
<%@ page import="gob.sct.medprev.vo.TVGRLUniMed"%>
<%@ page import="gob.sct.medprev.dao.TDEXPServicio"%>
<%@ page import="gob.sct.medprev.vo.TVEXPServicio"%>
<%@ page import="gob.sct.medprev.vo.TVEXPResultado"%>
<%@ page import="java.util.*"%>
	
<%
//////oBTENER EXAMENES /////////////
	String cExpediente = "0";
	cExpediente = session.getAttribute("id")+"";
	
	TDGRLUniMed dUnimed = new TDGRLUniMed();

	System.out.println("buscando1");
		//Vector vcAplica = new Vector();
		Vector vcUnidades = new Vector();

		try {
			vcUnidades = dUnimed.FindByAll("", "");
			System.out.println("vcAplica ="+vcUnidades.size());
		} catch (Exception e) {
			vcUnidades = new Vector();
			e.printStackTrace();
			System.out.println("resultados error");
		}
		
		//TvGRLUniMed vGRLUniMed;
		TVGRLUniMed vGRLUniMed;
		int contador = 0;
		int filtro = 1;
		int draw = 1;
		String regresa="";
		System.out.println("resultados="+vcUnidades.size() );
		if (vcUnidades.size() > 0) {
			for (int i = 0; i < vcUnidades.size(); i++) {
				vGRLUniMed = (TVGRLUniMed) vcUnidades.get(i);
				//System.out.println(vGRLUniMed.getICveUniMed());
				if(contador==0){
						regresa="["+vGRLUniMed.getICveUniMed()+",\""+vGRLUniMed.getCDscUniMed()+"\",\""+vGRLUniMed.getCCalle()+"\",\""+vGRLUniMed.getCColonia()+"\",\""+vGRLUniMed.getCCiudad()+"\",\"<a data-paciente='"+vGRLUniMed.getICveUniMed()+"' id='btn_unimed'  data-unimed='"+vGRLUniMed.getICveUniMed()+"' class='btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air' title='Atender Persona'>        <i class='flaticon-user-ok'></i>      </a>  \"]";
						//System.out.println("lista 1");
				}else{
						regresa=regresa+",["+vGRLUniMed.getICveUniMed()+",\""+vGRLUniMed.getCDscUniMed()+"\",\""+vGRLUniMed.getCCalle()+"\",\""+vGRLUniMed.getCColonia()+"\",\""+vGRLUniMed.getCCiudad()+"\",\"<a data-paciente='"+vGRLUniMed.getICveUniMed()+"' id='btn_unimed'  data-unimed='"+vGRLUniMed.getICveUniMed()+"' class='btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air' title='Atender Persona'>        <i class='flaticon-user-ok'></i>      </a>  \"]";
						//System.out.println("lista 3");
				}
				contador++;
			}
			regresa = "{\"draw\":"+draw+",\"recordsTotal\":"+contador+",\"recordsFiltered\":"+contador+",\"data\":["+regresa+"]}";
			
		}else{
			regresa = "{\"draw\":"+draw+",\"recordsTotal\":"+contador+",\"recordsFiltered\":"+contador+",\"data\":["+regresa+"]}";
			//regresa = "{\"draw\":false}";
		}
	
	out.println(regresa);

	//{"draw":1,"recordsTotal":2,"recordsFiltered":2,"data":[[1,586135,"framedev@gmail.com","Usuario1",45,"\r\n      <a data-paciente=\"586135\" id=\"btn_unimed\"  data-episodio=\"6\" class=\"btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air\" title=\"Atender Persona\">        <i class=\"flaticon-user-ok\"><\/i>      <\/a>'\r\n      "],[2,"admin1","framedev@gmail.com","Usuario12",6,"\r\n      <a data-function=\"2\" class=\"usr_js_fn_03 btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air\">\r\n        <i class=\"flaticon-cogwheel\"><\/i>\r\n      <\/a>\r\n      "]]}
	
%>	
	
