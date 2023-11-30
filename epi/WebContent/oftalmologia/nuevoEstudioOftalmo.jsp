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

session.setAttribute("servicio","67");    /////////////////////////MODIFICAR LA CLAVE DEL SERVICIO (TABLA MEDSERVICIO)/////////////////////////////////////////////

TDEXPExamCat cat = new TDEXPExamCat();
int lconcluido = cat.FindByInt("Select lconcluido from expservicio where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
																" and icveservicio = "+session.getAttribute("servicio"));
int usuario = 0;
String fechaconcluido = "";
String cusuario = "";
if(lconcluido==1){
usuario = cat.FindByInt("Select icveusuaplica from expservicio where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
		" and icveservicio = "+session.getAttribute("servicio"));
	fechaconcluido = cat.SentenciaSQL("Select tsfin from expservicio where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
		" and icveservicio = "+session.getAttribute("servicio"));
	cusuario = cat.SentenciaSQL("Select cnombre || ' ' || cappaterno || ' ' || capmaterno from segusuario where icveusuario = "+usuario);
}

%>

   <div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
  <div class="m-portlet__head" style="background:#f7f8fa">
     <div class="m-portlet__head-caption">
       <div class="m-portlet__head-title">
          <span class="m-portlet__head-icon">
          <img src="img/servicios/oftalmologia.svg" width="25" class="svg-inject signal-strong">
          </span>
          <h3 class="m-portlet__head-text m--font-primary">
             Gabinete Oftalmología
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
  
  
  
   
<%
//////////AGREGAR PART1//////////
if(lconcluido == 1){
	//System.out.println(fechaconcluido.length());
	if(fechaconcluido !=null){
		System.out.println("fecha/="+fechaconcluido.length());	
	}else{
		fechaconcluido="sin registro";
	}
	System.out.println("fecha="+fechaconcluido.length());
	
%>	
	
              <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-8">
                  <div class="m-alert m-alert--outline alert alert-danger alert-dismissible fade show" role="alert">
                      <button type="button" class="close" data-dismiss="" aria-label="Close"></button>
                      <strong>
                        Servicio concluido! <br>
                      </strong>
                          El servicio fue concluido por el Dr. <%=cusuario %> <br>Fecha de conclusion: <%=fechaconcluido.length()>19?fechaconcluido.substring(0,19):"sin fecha" %> 
                            
                    </div>
                </div>
              </div>
<%	
}else{
//////////AGREGAR FIN PART1//////////	
%>
  
  
  
  
   <div class="m-portlet__body">

         <div class="m-portlet__body">
     <div class="form-group m-form__group">
         <button type="button" id="agudezavisual" class="btn btn-primary">
    				Agudeza Visual
    		 </button>
         <button type="button" id="fondoojo" class="btn btn-primary">
    				Valoración de Fondo de Ojo
    		 </button>
     </div>

     <div id="contRamas"></div>

  </div>
      <div class="m-form__content">
        <div class="m-alert m-alert--icon alert alert-danger m--hide" role="alert" id="m_form_1_msg">
          <div class="m-alert__icon">
            <i class="la la-warning"></i>
          </div>
          <div class="m-alert__text">
            Favor de Verificar los datos.
          </div>
          <div class="m-alert__close">
            <button type="button" class="close" data-close="alert" aria-label="Close"></button>
          </div>
        </div>
      </div>
    


<script>
   var id_persona = $('#id_persona_left').val();
   var num_expediente = $('#id_numexp_left').val();
   var episodio = $('#id_episodio_left').val();
  /*
      $("#torax").click(function(){ carga_archivo('contRamas','rayosx/teleTorax.jsp?hdrama=1'); });
      $("#neumologia").click(function(){ carga_archivo('contRamas','rayosx/neumologia.jsp?hdrama=2'); });
 */    
      $("#agudezavisual").click(function(){ carga_archivo('contRamas','oftalmologia/PreguntasOftalmo.jsp?hdrama=1'); });
      $("#fondoojo").click(function(){ carga_archivo('contRamas','oftalmologia/PreguntasOftalmo.jsp?hdrama=2'); });
    
      
</script>

<% 
//////////AGREGAR PART2//////////	
}
//////////AGREGAR FIN PART2//////////
%>    