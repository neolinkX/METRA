<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>

<%
	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
	String CodigoValida = "";
	//session.setAttribute("servicio", "4");
	session.setAttribute("rama", ""+request.getParameter("hdrama"));
	boolean seagrego = false;
	TDEXPExamCat cat = new TDEXPExamCat();
	String cnombrerama = cat.SentenciaSQL("Select cdscrama from medrama where icveservicio = "+session.getAttribute("servicio")+" and icverama = "+request.getParameter("hdrama"));

	////Parametros carga de Documentos
		TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

%>
<%@ include file="/persona/datosServicios.jsp"%>

<div
	class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm"
	data-portlet="true" id="m_portlet_tools_2">
	
    <div class="form-group m-form__group">
  <div class="alert m-alert m-alert--default" role="alert">
    <%=cnombrerama %>
  </div>
</div>
	<form
		class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed"
		id="frm_svs" name="frm_svs">
		<div class="m-portlet__body">
			<br>

			<div class="m-form__content">
				<div class="m-alert m-alert--icon alert alert-danger m--hide"
					role="alert" id="m_form_1_msg">
					<div class="m-alert__icon">
						<i class="la la-warning"></i>
					</div>
					<div class="m-alert__text">Favor de Verificar los datos.</div>
					<div class="m-alert__close">
						<button type="button" class="close" data-close="alert"
							aria-label="Close"></button>
					</div>
				</div>


				<%
				String stToken = "";
					int contadortipodiv = 0;
					for (int i = 0; i < vcEXPResultados.size(); i++) {
						vEXPResultados = (TVEXPResultado) vcEXPResultados.get(i);
						System.out.println("contadortipodiv=" + contadortipodiv);
						seagrego = false;
						if (!(vEXPResultados.getICveTpoResp() == 6)) {
							String cFlag = "1";
							String cTipoDato = "";
							String CampoFinal = "";

							if (vEXPResultados.getICveTpoResp() == 1) {
								cTipoDato = "lLogico_";
							}
							if (vEXPResultados.getICveTpoResp() == 2 || vEXPResultados.getICveTpoResp() == 4
									|| vEXPResultados.getICveTpoResp() == 7 || vEXPResultados.getICveTpoResp() == 8) {
								cTipoDato = "cCaracter_";
							}
							if (vEXPResultados.getICveTpoResp() == 3 || vEXPResultados.getICveTpoResp() == 3) {
								cTipoDato = "dValorI_";
							}

							CampoFinal = cTipoDato + "" + cFlag + "" + vEXPResultados.getICveSintoma();

							if (contadortipodiv == 0) {
				%>
				  <div class="row">
    <div class="col-md-6">
      <div class="m-portlet__body">
        <div class="m-section">
					<%
						}
								if (contadortipodiv >= 0) {
									System.out.println("Sintoma = " + vEXPResultados.getICveSintoma() + " Dato = "
											+ vEXPResultados.getICveTpoResp());

									if (vEXPResultados.getICveTpoResp() == 1) {
										seagrego = true;
					%>
				      <div class="form-group m-form__group row">
				        <label for="neu_morfologia" class="col-5 col-form-label">
				       <%=vEXPResultados.getCPregunta()%>
				        </label>
				        <div class="col-7" align="center">
				          <span class="m-switch m-switch--sm m-switch--icon">
				            <label>
				              <input name="<%=CampoFinal%>" type="checkbox">
				              <span></span>
				            </label>
				          </span>
				      </div></div>


					<%
						}

									if (vEXPResultados.getICveTpoResp() == 2 || vEXPResultados.getICveTpoResp() == 8) {
										seagrego = true;
					%>
					<div class="form-group col-md-3">
						<label for="svs_presion_sistolica"> <%=vEXPResultados.getCPregunta()%>
						</label> <input type="text" class="form-control m-input"
							id="<%=CampoFinal%>" name="<%=CampoFinal%>" maxlength="200"
							> <span> <%=vEXPResultados.getCEtiqueta()%>
						</span>
					</div>

					<%
						}
									if (vEXPResultados.getICveTpoResp() == 3) {
					%>
					<div class="form-group col-md-3">
						<label for="svs_presion_sistolica"> <%=vEXPResultados.getCPregunta()%>
						</label> <input type="text" class="form-control m-input"
							id="<%=CampoFinal%>" name="<%=CampoFinal%>" maxlength="3"
							data-number> <span> <%=vEXPResultados.getCEtiqueta()%>
						</span>
					</div>

					<%
						seagrego = true;
									}
									if (vEXPResultados.getICveTpoResp() == 4 || vEXPResultados.getICveTpoResp() == 7) {
										String largocampo = "";
										if (vEXPResultados.getICveTpoResp() == 4) {
											largocampo = "500";
										} else {
											largocampo = "2000";
										}
					%>
					<div class="form-group m-form__group row">
						<label for="svs_presion_sistolica" class="col-2 col-form-label">
							<%=vEXPResultados.getCPregunta()%></label>
							<textarea maxlength="<%=largocampo%>"
								class="form-control m-input m-input--air" id="<%=CampoFinal%>"
								name="<%=CampoFinal%>" rows="2"></textarea>
						<span> <%=vEXPResultados.getCEtiqueta()%>
						</span>
					</div>

					<%
						seagrego = true;
									}
									if (seagrego) {
										System.out.println("Cumplio");
									} else {
										System.out.println("No cumplio");
									}

								}
								if (contadortipodiv == 3) {
					%>
				</div></div></div></div>
				<div
					class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
				<%
					contadortipodiv = 0;
							} else {
								contadortipodiv++;
							}

							///Codigo para Validacion de campos
							if (vEXPResultados.getLObligatorio() == 1 && (vEXPResultados.getICveTpoResp()!=1 && vEXPResultados.getICveTpoResp()!=4)) {
								if (CodigoValida.length() > 0) {
									CodigoValida = CodigoValida + ",";
								}
								CodigoValida = CodigoValida + CampoFinal + ":{ required: true, min: 0, max: 299 }";
							} else {
								//CodigoValida = CodigoValida + CampoFinal + ":{ required: false, min: 0, max: 299 }";
							}
							stToken=stToken+""+CampoFinal+"|";
						} ///No es un titulo
						System.out.println("salida contadortipodiv=" + contadortipodiv);
					} ////Fin del Ciclo
					System.out.println("-stToken="+stToken);
					session.setAttribute("stToken",stToken);
					
					
					
				%>

        </div>
      </div>
    <!-- 
    <div class="col-md-6">
      <div class="m-portlet__body">
        <div class="m-section">
          <div class="m-portlet__body">
            <div class="form-group m-form__group row">
              <label for="">
                Cargar Archivo de Imagen
              </label>
              <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="m-dropzone dropzone" id="teletorax_img">
                  <div class="m-dropzone__msg dz-message needsclick">
                    <h3 class="m-dropzone__msg-title">
                      Arrastra aqui la imagen o haz click para subir una.
                    </h3>
                    <span class="m-dropzone__msg-desc">
                      Actualmente no hay archivos
                      <strong>
                       no
                      </strong>
                      se han subido archivos.
                    </span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div> -->
    <div id="teletorax_div"></div>
    <div class="m-portlet__foot m-portlet__foot--fit">
      <div class="m-form__actions">
        <button type="submit" class="btn btn-primary">
          Guardar
        </button>
        <button type="reset" class="btn btn-secondary">
          Limpiar
        </button>
      </div>

    </div>
  
</form>


  <form class="m-form m-form--fit m-form--label-align-right">
          <div class="m-portlet__body">
            <div class="form-group m-form__group row">
              <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="m-dropzone dropzone" id="m-dropzone-one">
                  <div class="m-dropzone__msg dz-message needsclick">
                    <h3 class="m-dropzone__msg-title">
                      Arrastra aqui la imagen o haz click para subir una.
                    </h3>
                    <span class="m-dropzone__msg-desc">
                      Actualmente no hay archivos
                      <strong>
                        no
                      </strong>
                      se han subido archivos.
                    </span>
                  </div>
                </div>
                <a class="btn btn-primary" name="Ver imagenes relacionadas" href="JavaScript:showPlacasToraxFiles();">Mostrar archivos de imagenes</a>
              </div>
            </div>
          </div>
        </form>


<script>
function guarda_signos_vitales(){
  var options = {type:'question',title:'¿Desea guardar <%=cnombrerama%>?'}
  modalConfirm(function (){
    $.ajax({
    	url: 'grdservicios/guardaServicios.jsp',
        type: 'POST',
        data: $("#frm_svs").serialize(),
        dataType: 'json',
        success: function(resp_success){
      	if(resp_success[0].resp=='success'){
          	swal('Se guardó correctamente la información!', '', "success");
          	get_signosVitales();
        	}else{  swal('  '+resp_success[0].resp, '', "error");
        	}},      
        error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PER-06');}
      });
    },options);
}
$("[data-number_dec]").inputmask("decimal", { min: 0, max: 350, allowMinus: false, digits: 1, integerDigits: 3 });
/*$("[data-number_dec]").inputmask({
   mask: "(999)|(999.9{1})|(99.9{1})",
  'alias': 'decimal',
   rightAlign: true,
   'groupSeparator': '.',
   'autoGroup': true
 });*/ 
 
 $("#frm_svs").validate({
	  rules: {
<%=CodigoValida%>
	  },
	  invalidHandler:function(e,r){
	    var i=$("#m_form_1_msg");
	    i.removeClass("m--hide").show(),
	    mApp.scrollTo(i,-200);
	  },
	  submitHandler: function(form) {
	    guarda_signos_vitales();
	  }
	});

 
 $(document).ready(function() {
	  $("#m-dropzone-one").dropzone({
	    //url: "usuarios/upload_dropzone/perfiles/upload_avatar",    paramName: "file",
	    url: "AdministradorContenidosServletServicios?archivo=<%=vUsuario.getICveusuario()%>&iCveExpediente=<%=session.getAttribute("id")+""%>&iNumExamen=<%=session.getAttribute("episodio")+""%>&iCveServicio=<%=""+session.getAttribute("servicio")%>&iCveRama=<%=""+session.getAttribute("rama")%>", paramName: "file",
	    maxFiles: 5,
	    maxFilesize: 5, // MB
	    acceptedFiles: "image/*",
	    accept: function(file, done) {
	        console.log(file);
	        done();
	    },
	    init: function() {
	      this.on("success", function(statics,file) {
	        var img = file.split("|");
	        $.post( "usuarios/update_avatar/" + img[1], function( data ) {
	           swal('Se actualizó su avatar correctamente', '', "Actualizado!")
	        });
//	         $('#avatar_actual').html('<center><img src="plugs/timthumb.php?src=tmp/'+img[0]+'&w=300"></center>');
	  			//$('#avatar_top1').attr('src','plugs/c.php?src=tmp/' + img[0] + '&w=80&h=80&a=t');
	        //$('#avatar_top2').attr('src','<%=vParametros.getPropEspecifica("RutaNASAvatarURL")%><%=vUsuario.getICveusuario()%>-2.jpg');
	        //$('#avatar-5').attr('src','<%=vParametros.getPropEspecifica("RutaNASAvatarURL")%><%=vUsuario.getICveusuario()%>-2.jpg');
	        //$('#avatar_top1').attr('src','<%=vParametros.getPropEspecifica("RutaNASAvatarURL")%><%=vUsuario.getICveusuario()%>-2.jpg');
	        //$('#avatar_top2').attr('src','<%=vParametros.getPropEspecifica("RutaNASAvatarURL")%><%=vUsuario.getICveusuario()%>-2.jpg');
	  		//carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>perfil.jsp');
	      });
	    }
	   });

	});
 
 
 var newPlacaToraxFile;
 function showPlacasToraxFiles() {
   if (!newPlacaToraxFile || newPlacaToraxFile.closed) {
       newPlacaToraxFile = window.open("./MostrarArchivos.jsp?"
             	+"iCveExpediente="+545155             
             +"&iNumExamen="+1
             +"&iCveServicio="+4
             +"&iCveRama="+null
             +"&iCveUsuario="+13900, "", "height=200,width=650,resizable=yes,menubar=0,scrollbars=yes");
       newPlacaToraxFile.focus( );
   } else if (newPlacaToraxFile.focus) {
       newPlacaToraxFile.focus( );
   }
 }
 
 
</script>