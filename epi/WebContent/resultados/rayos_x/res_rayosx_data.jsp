<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamAplica"%>
<%  
TEntorno    vEntorno4      = new TEntorno();
vEntorno4.setNumModulo(07);
TParametro  vParametros4 = new TParametro(vEntorno4.getNumModuloStr());
%>
<h2 class="text-center"><img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/laboratorio.svg" width="25" class="svg-inject signal-strong"> Laboratorio</h2>
 <div class="tab-content">
  <div class="tab-pane active" id="nuevo_doc_digital" role="tabpanel">

    <div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
       <div class="m-portlet__body">
          <div class="form-group m-form__group">
             <button type="button" id="RamaTeleTorax" class="btn btn-primary">
             Rama de Tele Torax
             </button>
             <button type="button" id="RamaNeumo" class="btn btn-primary">
             Rama Neumolog&iacute;a
             </button>
          </div>
          <div id="contRamas"></div>
       </div>
    </div>
    <script>
      var episodio = $("#id_episodio_left").val();
    </script>

        <script>
          //$("#hematologiai").click(function(){ carga_archivo('contRamas','resultados/laboratorio/resultadosHematologiai/'+episodio,''); });
          $("#RamaTeleTorax").click(function(){ carga_archivo('contRamas','resultados/rayos_x/resultadosRayosX.jsp?hdrama=1',''); });
        </script>
    
        <script>
          //$("#quimicac").click(function(){ carga_archivo('contRamas','resultados/laboratorio/resultadosQuimicac/'+episodio,''); });
          //$("#quimicac").click(function(){ carga_archivo('contRamas','resultados/laboratorio/quimica_clinica.jsp',''); });
          $("#RamaNeumo").click(function(){ carga_archivo('contRamas','resultados/rayos_x/resultadosRayosX.jsp?hdrama=2',''); });
        </script>
        
  </div>
</div>





