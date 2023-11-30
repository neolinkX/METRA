<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamAplica"%>
    <%
    System.out.println("res_somatometria");
    System.out.println("IDdd3="+request.getParameter("id")); 
    TEntorno    vEntorno2      = new TEntorno();
    vEntorno2.setNumModulo(07);
    TParametro  vParametros2 = new TParametro(vEntorno2.getNumModuloStr());
    String cExpediente2 = ""+session.getAttribute("id");
    session.setAttribute("resservicio", "11");
    session.setAttribute("resrama", "1");
	//String cInumExamen2 = "6";
	String cInumExamen2 = ""+session.getAttribute("episodio");
	String iCveServicio2 = ""+session.getAttribute("resservicio");
	String iCveRama22 = ""+session.getAttribute("resrama");
	String cWhere2 = "";
	TDEXPExamAplica dEXPExamAplica2 = new TDEXPExamAplica();
    int Concluido =0;
    cWhere2 = "SELECT LCONCLUIDO FROM EXPSERVICIO "
			+ "WHERE ICVEEXPEDIENTE = " + cExpediente2 + " "
			+ "AND INUMEXAMEN = " + cInumExamen2 + " "
			+ "AND ICVESERVICIO = " + iCveServicio2 + " ";
	try {
		Concluido = dEXPExamAplica2.RegresaInt(cWhere2);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("Concluido="+Concluido);
    %>
<h2 class="text-center"><img src="<%=vParametros2.getPropEspecifica("URL_APP")%>img/servicios/signos_vitales.svg" width="25" class="svg-inject signal-strong"> Resultados - Signos Vitales y Somatometría</h2>
         <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
            <li class="nav-item m-tabs__item">
               <a class="nav-link m-tabs__link active" data-toggle="tab" href="#nuevo_doc_digital" role="tab" aria-expanded="false">
               <i class="" aria-hidden="true"></i>
               Mi Episodio Actual
               </a> 
            </li>          
         </ul>
         <div class="tab-content">
            <div class="tab-pane active" id="nuevo_doc_digital" role="tabpanel">



              <%
              System.out.println("Concluido-="+Concluido);
                  if(Concluido > 0){
                	  %>
                	  <%@ include file="res_somatometria_data.jsp"%> 
                 <% }else{
              %>
              
              <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-8">
                  <div class="m-alert m-alert--outline alert alert-danger alert-dismissible fade show" role="alert">
                      <button type="button" class="close" data-dismiss="" aria-label="Close"></button>
                      <strong>
                        Sin Captura!
                      </strong>
                          No existe ningún registro de Somatometría y Signos Vitales para este episodio.
                    </div>
                </div>
              </div>
              <%
                  }
              %>
            </div>

         </div>
