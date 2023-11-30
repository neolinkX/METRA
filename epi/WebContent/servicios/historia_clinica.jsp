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


%>  
    <div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__head" style="background:#f7f8fa">
      <div class="m-portlet__head-caption">
        <div class="m-portlet__head-title">
           <span class="m-portlet__head-icon">
             <img src="img/servicios/historia_clinica.svg" width="25" class="svg-inject signal-strong">
           </span>
           <h3 class="m-portlet__head-text m--font-primary">
              Historia Clínica
           </h3>
        </div>
      </div>
      <div class="m-portlet__head-tools">
         <ul class="m-portlet__nav">
            <li class="m-portlet__nav-item">
               <a href="" data-portlet-tool="toggle" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Collapse">
               <i class="la la-plus"></i>
               </a>
            </li>
            <li class="m-portlet__nav-item">
               <a href="#" data-portlet-tool="fullscreen" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Pantalla Completa">
               <i class="la la-expand"></i>
               </a>
            </li>
         </ul>
      </div>
   </div>
   <div class="m-portlet__body">
     <nav aria-label="breadcrumb" role="navigation">
      <ol class="breadcrumb">
         <!-- <li class="breadcrumb-item"><a id="liheredofamiliares" class="gli" href="javascript:;" onclick="resalta_seleccionado('liheredofamiliares');carga_archivo('contRamas','historiaclinica/resultadoshistoriaclinica/<?php echo $id_episodio; ?>/<?php echo $id_persona; ?>');"></a></li>-->
          <li class="breadcrumb-item"><a id="liheredofamiliares" class="gli" href="javascript:;" onclick="resalta_seleccionado('liheredofamiliares');carga_archivo('contRamas','historiaclinica/antecedentesHeredofamiliares');"> Heredofamiliares</a> / </li>
       <script>
      $("#liheredofamiliares").click(function(){ carga_archivo('contRamas','<%=vParametros.getPropEspecifica("URL_APP")%>servicios/heredofamiliares/antecedentesHeredofamiliares.jsp?hdrama=1'); });
    </script>
          <!--<li class="breadcrumb-item" aria-current="page"><a id="lino_patologicos" class="gli" href="javascript:;" onclick="resalta_seleccionado('lino_patologicos');carga_archivo('contRamas','historiaclinica/resultados_no_patologico/<?php echo $id_episodio; ?>/<?php echo $id_persona; ?>');">No Patológicos</a></li>-->
     <li class="breadcrumb-item" aria-current="page"><a id="lino_patologicos" class="gli" href="javascript:;" onclick="resalta_seleccionado('lino_patologicos');carga_archivo('contRamas','historiaclinica/antecedentesNoPatologicos');"> No Patológicos</a> / </li>
            <script>
      $("#lino_patologicos").click(function(){ carga_archivo('contRamas','<%=vParametros.getPropEspecifica("URL_APP")%>servicios/patologias/antecedentesNoPatologicos.jsp?hdrama=1'); });
    </script>         
        <!--  <li class="breadcrumb-item" aria-current="page"><a id="lidatos_patologicos"  class="gli" href="javascript:;" onclick="resalta_seleccionado('lidatos_patologicos');carga_archivo('contRamas','historiaclinica/resultados_patologico/<?php echo $id_episodio; ?>/<?php echo $id_persona; ?>');">Patológicos</a></li> -->
       <li class="breadcrumb-item" aria-current="page"><a id="lidatos_patologicos" class="gli" href="javascript:;" onclick="resalta_seleccionado('lidatos_patologicos');carga_archivo('contRamas','historiaclinica/antecedentesPatologicos');"> Patológicos</a> / </li>
        <script>
         $("#lidatos_patologicos").click(function(){ carga_archivo('contRamas','<%=vParametros.getPropEspecifica("URL_APP")%>servicios/patologias/antecedentesPatologicos.jsp?hdrama=1'); });
         </script>
       <!--   <li class="breadcrumb-item" aria-current="page"><a id="li_interrogatorio" class="gli" href="javascript:;" onclick="resalta_seleccionado('li_interrogatorio');carga_archivo('contRamas','historiaclinica/resultados_interrogatorio/<?php echo $id_episodio; ?>/<?php echo $id_persona; ?>');">Interrogatorio</a></li>-->
         <li class="breadcrumb-item" aria-current="page"><a id="li_interrogatorio" class="gli" href="javascript:;" > Interrogatorio</a> / </li>
         <script>
	    $("#li_interrogatorio").click(function(){ carga_archivo('contRamas','<%=vParametros.getPropEspecifica("URL_APP")%>servicios/interrogatorio/interrogatorio.jsp'); });
	    </script>
        <!--  <li class="breadcrumb-item" aria-current="page"><a id="li_efisica" class="gli" href="javascript:;" onclick="resalta_seleccionado('li_efisica');carga_archivo('contRamas','historiaclinica/resultados_efisica/<?php echo $id_episodio; ?>/<?php echo $id_persona; ?>');">Exploración Física</a></li>-->
       <li class="breadcrumb-item" aria-current="page"><a id="li_efisica" class="gli" href="javascript:;" >Exploración Física</a>/</li>
         <script>
	    $("#li_efisica").click(function(){ carga_archivo('contRamas','<%=vParametros.getPropEspecifica("URL_APP")%>servicios/exploracionFisica/nuevaExploracion.jsp'); });
	    </script>        
        <!--  <li class="breadcrumb-item" aria-current="page"><a id="li_gineco" class="gli" href="javascript:;" onclick="resalta_seleccionado('li_gineco');carga_archivo('contRamas','historiaclinica/resultados_gineco/<?php echo $id_episodio; ?>/<?php echo $id_persona; ?>');">Exploración Ginecológica</a></li>-->
          <li class="breadcrumb-item" aria-current="page"><a id="li_gineco" class="gli" href="javascript:;" >Exploración Ginecológica</a></li>
          <script>
          $("#li_gineco").click(function(){ carga_archivo('contRamas','<%=vParametros.getPropEspecifica("URL_APP")%>servicios/ginecologia/nuevaExploracionGinecologica.jsp'); });
          </script>
      </ol>
    </nav>
    <div id="contRamas"></div>
    </div>
</div>
    