<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamCat"%>    
<%
TDEXPExamCat cat = new TDEXPExamCat();
String motivo = "";
String categoria = "";
String puesto = "";
String modtrans = "";
String inumExamen ="";
int imotivo = 0;
int icategoria = 0;
int ipuesto = 0;
int imodtrans = 0;
int epiabierto = 0;



System.out.println("1-"+session.getAttribute("id_motivo"));
System.out.println(session.getAttribute("puesto_tramite"));
System.out.println(session.getAttribute("iCveCategoria"));
System.out.println(session.getAttribute("MdoTransTramite"));

	//if((session.getAttribute("id_motivo")+"").equals("") || (session.getAttribute("id_motivo")+"").equals("null") || session.getAttribute("id_motivo") == null){
		imotivo = cat.FindByInt("Select icvemotivo from expexamcat where icveexpediente = "+session.getAttribute("id") +" and inumexamen =(Select max(inumexamen) from expexamcat where icveexpediente = "+session.getAttribute("id") +")");
		motivo = cat.SentenciaSQL("Select cdscmotivo from grlmotivo where icvemotivo = "+imotivo);
		session.setAttribute("id_motivo",motivo);
		//}

		//if((session.getAttribute("MdoTransTramite")+"").equals("") || (session.getAttribute("iCveCategoria")+"").equals("null") || session.getAttribute("iCveCategoria") == null){
		imodtrans = cat.FindByInt("Select icvemdotrans from expexamcat where icveexpediente = "+session.getAttribute("id") +" and inumexamen =(Select max(inumexamen) from expexamcat where icveexpediente = "+session.getAttribute("id") +")");
		modtrans = cat.SentenciaSQL("Select cdscmdotrans from grlmdotrans where icvemdotrans = "+imodtrans );
		session.setAttribute("MdoTransTramite",modtrans);
		//}
	//if((session.getAttribute("iCveCategoria")+"").equals("") || (session.getAttribute("iCveCategoria")+"").equals("null") || session.getAttribute("iCveCategoria") == null){
		icategoria = cat.FindByInt("Select icvecategoria from expexamcat where icveexpediente = "+session.getAttribute("id") +" and inumexamen =(Select max(inumexamen) from expexamcat where icveexpediente = "+session.getAttribute("id") +")");
		categoria = cat.SentenciaSQL("Select cdsccategoria from grlcategoria where icvemdotrans = "+imodtrans +" and icvecategoria = "+icategoria);
		session.setAttribute("iCveCategoria",categoria);
		//}
		//if((session.getAttribute("puesto_tramite")+"").equals("") || (session.getAttribute("puesto_tramite")+"").equals("null") || session.getAttribute("puesto_tramite") == null){
		ipuesto = cat.FindByInt("Select icvepuesto from expexampuesto where icveexpediente = "+session.getAttribute("id") +" and inumexamen =(Select max(inumexamen) from expexampuesto where icveexpediente = "+session.getAttribute("id") +")");
		puesto = cat.SentenciaSQL("Select cdscpuesto from grlpuesto where icvepuesto = "+ipuesto +" and icvemdotrans = "+imodtrans +" and icvecategoria = "+icategoria);
		session.setAttribute("puesto_tramite",puesto);
		//}
	
	inumExamen = cat.SentenciaSQL("Select max(inumexamen) from expexamcat where icveexpediente = "+session.getAttribute("id"));
	session.setAttribute("inumExamen", inumExamen);
	
	String cGenero = cat.SentenciaSQL("Select cSexo from perdatos where icveexpediente = "+session.getAttribute("id"));
	session.setAttribute("cGenero", cGenero);
	
	epiabierto = cat.FindByInt("Select ldictaminado from expexamaplica where icveexpediente = "+session.getAttribute("id") +" and inumexamen =(Select max(inumexamen) from expexamaplica where icveexpediente = "+session.getAttribute("id") +")");
	System.out.println(epiabierto);
	System.out.println("2-"+session.getAttribute("id_motivo"));
	System.out.println(session.getAttribute("puesto_tramite"));
	System.out.println(session.getAttribute("iCveCategoria"));
	System.out.println(session.getAttribute("MdoTransTramite"));
	
	/*
%>
<!-- 

 <script type="text/javascript">

	  function generaPDFConsentInf(){
		
		 			 
		var cConds = "alwaysRaised=yes,dependent=yes,width=800,height=485,location=no,menubar=no,resizable=yes,scrollbars=yes,titlebar=yes,statusbar=yes,toolbar=no";
		  hWinHojaAyuda = window.open("servConsenInfCFG?hdNoExpedienteRep=<%=session.getAttribute("id")%>&hdiNumExamenRep=<%=session.getAttribute("inumExamen")%>", "FRMConsenInf", cConds);
		 
	  }
 
</script>	
 -->





  
<%  
  */
%>
    
<div class="modal fade" id="modalPerfil" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-lg" role="document" style="max-width:1200px !important;">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="myModalLabel"><i class="flaticon-list"></i> Inicio Proceso EPI</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body" id="modal_content">
 <input type="hidden" id="id_episodio_left2" value="<%=inumExamen%>">
            <div class="row">

            <%if(epiabierto==0){ %>
            <div class="m-alert m-alert--icon m-alert--outline alert alert-success alert-dismissible fade show" role="alert">
                     <div class="m-alert__icon">
                        <i class="fa fa-check"></i>
                     </div>
                     <div class="m-alert__text">
                        El proceso EPI se encuentra activo en este momento
                     </div>
                  </div>

            <%   }else{            %>
            <div class="m-alert m-alert--icon m-alert--outline alert alert-success alert-dismissible fade show" role="alert">
                     <div class="m-alert__icon">
                        <i class="fa fa-check"></i>
                     </div>
                     <div class="m-alert__text">
                        El &uacute;ltimo EPI fue concluido
                     </div>
                  </div>
           <%         }            %>
               <div class="col-12 text-center">
                  <h2>Datos Pesonales</h2>
                  <table class="table table-striped table-bordered" cellspacing="0" width="100%">
                     <tr class=" text-center">
                        <th>Expediente</th>
                        <th>Nombre</th>
                        <th>Sexo</th>
                        <th>Edad</th>
                        <th>RFC</th>
                     </tr>
                     <tr>
                        <td><%=session.getAttribute("id") %></td>
                        <td><%=session.getAttribute("nombreExp") %></td>
                        <td><%=session.getAttribute("sexoExp") %></td>
                        <td><%=session.getAttribute("edadExp") %> a&ntilde;os</td>
                        <td><%=session.getAttribute("rfcExp") %></td>
                     </tr>
                  </table>
               </div>
            </div>
            <div class="row">
               <div class="col-12  text-center">
                  <h2>Tr&aacute;mite</h2>
                  <table class="table table-striped table-bordered" cellspacing="0" width="100%">
                     <tr class=" text-center">
                        <th>Modo de Transporte:</th>
                        <th>Categor&iacute;a:</th>
                        <th>Puesto:</th>
                        <th>Motivo:</th>
                     </tr>
                     <tr>
                        <td><%=session.getAttribute("MdoTransTramite") %></td>
                        <td><%=session.getAttribute("iCveCategoria") %></td>
                        <td><%=session.getAttribute("puesto_tramite") %></td>
                        <td><%=session.getAttribute("id_motivo") %></td>
                     </tr>
                  </table>
               </div>
            </div>
            <div class="row">
               <div class="col-3"></div>
               <div class="col-6  text-center">
                  <!-- <h2>Validaci&oacute;n de Biom&eacute;tricos!</h2>
                  <div class="m-alert m-alert--icon m-alert--outline alert alert-success alert-dismissible fade show" role="alert">
                     <div class="m-alert__icon">
                        <i class="fa	fa-check"></i>
                     </div>
                     <div class="m-alert__text">
                        Los Biom&eacute;tricos fueron validados correctamente
                         <button type="button" id="btn_inicio_epi" class="btn btn-primary" onclick="procesoDeValidacion(<%=session.getAttribute("id") %>,<%=inumExamen %>);">
            				Valida Biometricos
            				</button>
            				
            				<form id="valida" name="valida">
            				<input type="hidden" class="form-control m-input" name="iFolioEs">
            				<input type="hidden" class="form-control m-input" name="iCveExpediente" value="<%=session.getAttribute("episodio") %>">
            					<div id="loading"
									style="position: absolute; width: 100%; text-align: center; top: 250px;">
								</div>
            				</form>
            				
            				 
                     </div>
                  </div>-->
               </div>
            </div>
            <div class="row">
               <div class="col-2"></div>
               <div class="col-8  text-center">
                  <h2>Documentos</h2>
                  <table class="table table-striped table-bordered" cellspacing="0" width="100%">
                     <tr>
                        <th class="text-center">Vale de Servicios</th>
                        <th class="text-center">Consentimiento Informado</th>
                        <th class="text-center">Declaraci&oacute;n de Salud</th>
                     </tr>
                     <tr>
                     
                     
                     
                  
                 
                               	
                       <td><span id="pdf_js_fn_02" data-persona="<?=$id_persona?>"><img src="../epi/assets/app/media/img/files/flatpdf.svg" alt="" width="50px"></a></td>
                       
                 
                 
                 <td><span id="pdf_js_fn_03"  data-persona="<?=$id_persona?>"> <img src="../epi/assets/app/media/img/files/flatpdf.svg"  alt="" width="50px"></a></td>
                
               
                  <td><span id="pdf_js_fn_04" data-persona="<?=$id_persona?>"><img src="../epi/assets/app/media/img/files/flatpdf.svg" alt="" width="50px"></a></td>
                 
              

                  
                                  
   
         
                       
                     </tr>
                  </table>
               </div>
            </div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">
            Cerrar
            </button>
            <?php if(count($status_proceso)==0){?>
            <button type="button" id="btn_inicio_epi" class="btn btn-primary" onclick="guarda_inicio_proceso_epi(<%=session.getAttribute("episodio") %>);">
            Iniciar Proceso EPI
            </button>

            <?php } ?>

         </div>
      </div>
   </div>
</div>

