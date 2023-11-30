<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
    <%
    System.out.println("res_somatometria");
    System.out.println("IDdd3="+request.getParameter("id")); 
    TEntorno    vEntorno2      = new TEntorno();
    vEntorno2.setNumModulo(07);
    TParametro  vParametros2 = new TParametro(vEntorno2.getNumModuloStr());
    
    %>
<!-- <?php include '../resources/contentTextForms.php'; ?> -->
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


<%@ include file="res_somatometria_data.jsp"%> 
              <?php
                  if(count($datos_som) > 0){
                      include 'res_somatometria_data.php';
                  }else{
              ?>
              
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
              <?php
                  }
              ?>
            </div>

         </div>
