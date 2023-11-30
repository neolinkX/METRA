<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%  
TEntorno    vEntorno      = new TEntorno();
vEntorno.setNumModulo(07);
TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
System.out.println("epidosdioModal="+request.getParameter("episodio"));
System.out.println("IdModal="+request.getParameter("Id"));
%>

<div class="modal fade" id="modalPerfil" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document" style="max-width: 1200px !important;">
        <div class="modal-content">

            <div class="modal-body" id="modal_content" style="overflow-y: auto;">
              <!-- <?php include URL_VISTA.'/resultados/somatometria/panel_container.php'; ?> -->
              <!-- <%=vParametros.getPropEspecifica("URL_APP")%>/resultados/somatometria/panel_container.jsp -->
              
            </div>

            <div class="modal-footer">

                <button type="button" class="btn btn-primary" data-dismiss="modal">
                  Cerrar
                </button>

            </div>
        </div>
    </div> 
</div>

<iframe width="1200" height="700" src="pg070104060-2.jsp" scrolling="yes"  frameborder="0" allowfullscreen></iframe>