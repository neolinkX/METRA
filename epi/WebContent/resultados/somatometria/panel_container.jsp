<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamAplica"%>
<%  
TEntorno    vEntorno4      = new TEntorno();
vEntorno4.setNumModulo(07);
TParametro  vParametros4 = new TParametro(vEntorno4.getNumModuloStr());

System.out.println("IDdd2="+request.getParameter("id"));
%>

<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm"  data-portlet="true" id="m_portlet_tools_2">
  <div class="m-portlet__head" style="background:#f7f8fa">
     <div class="m-portlet__head-caption">
        <div class="m-portlet__head-title">
           <span class="m-portlet__head-icon">
             <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/signos_vitales.svg" width="25" class="svg-inject signal-strong">
           </span>
           <h3 class="m-portlet__head-text m--font-primary">
              Signos Vitales y Somatometría
           </h3>
        </div>
     </div>
     <div class="m-portlet__head-tools">
        <ul class="m-portlet__nav">           
           <li class="m-portlet__nav-item">
              <a href="#" data-portlet-tool="fullscreen" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Pantalla Completa">
              <i class="la la-expand"></i>
              </a>
           </li>
        </ul>
     </div>
  </div>
  <div class="m-portlet__body">
      <!-- <?php include 'res_somatometria.php'; ?> -->
      <%@ include file="res_somatometria.jsp" %>
  </div>
</div>
 