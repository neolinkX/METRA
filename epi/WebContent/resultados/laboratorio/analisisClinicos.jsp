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
             <button type="button" id="hematologiai" class="btn btn-primary">
             Hematolog&iacute;a I
             </button>
             <button type="button" id="quimicac" class="btn btn-primary">
             Qu&iacute;mica Cl&iacute;nica
             </button>
             <button type="button" id="ego" class="btn btn-primary">
             E.G.O
             </button>
             <button type="button" id="hematologia" class="btn btn-primary">
             Hematolog&iacute;a
             </button>
             <button type="button" id="drogas" class="btn btn-primary">
             Drogas Terap&eacute;uticas
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
          $("#hematologiai").click(function(){ carga_archivo('contRamas','resultados/laboratorio/resultadosLaboratorio.jsp?hdrama=1',''); });
        </script>
    
        <script>
          //$("#quimicac").click(function(){ carga_archivo('contRamas','resultados/laboratorio/resultadosQuimicac/'+episodio,''); });
          //$("#quimicac").click(function(){ carga_archivo('contRamas','resultados/laboratorio/quimica_clinica.jsp',''); });
          $("#quimicac").click(function(){ carga_archivo('contRamas','resultados/laboratorio/resultadosLaboratorio.jsp?hdrama=4',''); });
        </script>
   
        <script>
          //$("#ego").click(function(){ carga_archivo('contRamas','resultados/laboratorio/resultadosEgo/'+episodio,''); });
          //$("#ego").click(function(){ carga_archivo('contRamas','resultados/laboratorio/general_orina.jsp',''); });
          $("#ego").click(function(){ carga_archivo('contRamas','resultados/laboratorio/resultadosLaboratorio.jsp?hdrama=2',''); });
        </script>
        
        <script>
          //$("#hematologia").click(function(){ carga_archivo('contRamas','resultados/laboratorio/resultadosHematologia/'+episodio,''); });
          //$("#hematologia").click(function(){ carga_archivo('contRamas','resultados/laboratorio/hematologia.jsp',''); });
          $("#hematologia").click(function(){ carga_archivo('contRamas','resultados/laboratorio/resultadosLaboratorio.jsp?hdrama=3',''); });
        </script>
    
        <script>
          //$("#drogas").click(function(){ carga_archivo('contRamas','resultados/laboratorio/resultadosDrogas/'+episodio,''); });
          //$("#drogas").click(function(){ carga_archivo('contRamas','resultados/laboratorio/drogas_terapeuticas.jsp',''); });
          $("#drogas").click(function(){ carga_archivo('contRamas','resultados/laboratorio/resultadosLaboratorio.jsp?hdrama=5',''); });
        </script>

  </div>
</div>
