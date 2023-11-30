<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamAplica"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamCat"%>    
<%
TDEXPExamCat cat = new TDEXPExamCat();

String cnombrerama = cat.SentenciaSQL("Select cdscrama from medrama where icveservicio = "+session.getAttribute("resservicio")+" and icverama = "+request.getParameter("hdrama"));

System.out.println("Rama="+cnombrerama);

    System.out.println("res_somatometria");
    System.out.println("IDdd3="+request.getParameter("id")); 
    TEntorno    vEntorno2      = new TEntorno();
    vEntorno2.setNumModulo(07);
    TParametro  vParametros2 = new TParametro(vEntorno2.getNumModuloStr());
    String cExpediente2 = ""+session.getAttribute("id");
    session.setAttribute("resservicio", "2");
    session.setAttribute("resrama", "1");
	//String cInumExamen2 = "6";
	String cInumExamen2 = ""+session.getAttribute("inumExamen");
	String iCveServicio2 = ""+session.getAttribute("resservicio");
	session.setAttribute("resrama", ""+request.getParameter("hdrama"));
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

<div class="form-group m-form__group">
  <div class="alert m-alert m-alert--default" role="alert">
    <b><%=cnombrerama %></b>
  </div>
</div>
<div class="row">
  <form class="m-form m-form--fit m-form--group-seperator-dashed" id="frm_hematologiaI" name="frm_hematologiaI">
    <div class="col-md-12">
      <div class="m-portlet__body">
        <div class="m-section">


              <%
              System.out.println("Concluido-="+Concluido);
                  if(Concluido > 0){
                	  %>
                	  <%@ include file="../somatometria/res_somatometria_data.jsp"%> 
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
                          No existe ningún registro de este servicio.
                    </div>
                </div>
              </div>
              <%
                  }
              %>


          </div>
        </div>
      </div>    
    </form>
  </div>
</div>
<script src="<?=URL_PUBLIC?>assets/js/validaciones/laboratorio/valida_hemologiaI.js"></script>
