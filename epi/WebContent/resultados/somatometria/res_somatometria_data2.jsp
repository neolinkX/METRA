
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%

System.out.println("IDdd="+request.getParameter("id"));
%>

<form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">

  <div class="form-group m-form__group row">
     <div class="col-md-3">
        <label for="svs_presion_sistolica">
        <?=$q_signos['signos_campo1']['label'];?>
        </label>
        <input type="text" class="form-control m-input" disabled value="<?=$datos_som[0]['svs_presion_sistolica']?>">
        <span>
        <?=$q_signos['signos_campo1']['form_help'];?>
        </span>
     </div>
     <div class="col-md-3">
        <label for="svs_presion_diastolica">
        <?=$q_signos['signos_campo2']['label'];?>
        </label>
        <input type="text" class="form-control m-input" disabled value="<?=$datos_som[0]['svs_presion_diastolica']?>">
        <span >
        <?=$q_signos['signos_campo2']['form_help'];?>
        </span>
     </div>
     <div class="col-md-3">
        <label for="svs_frec_cardiaca">
        <?=$q_signos['signos_campo3']['label'];?>
        </label>
        <input type="text" class="form-control m-input" disabled value="<?=$datos_som[0]['svs_frec_cardiaca']?>">
        <span>
        <?=$q_signos['signos_campo3']['form_help'];?>
        </span>
     </div>
     <div class="col-md-3">
        <label for="svs_frec_respiratoria">
        <?=$q_signos['signos_campo4']['label'];?>
        </label>
        <input type="text" class="form-control m-input" disabled value="<?=$datos_som[0]['svs_frec_respiratoria']?>">
        <span>
        <?=$q_signos['signos_campo4']['form_help'];?>
        </span>
     </div>
  </div>
  <div class="form-group m-form__group row">
    <div class="col-md-3">
       <label for="svs_temperatura">
       <?=$q_signos['signos_campo7']['label'];?>
       </label>
       <input type="text" step="0.01" class="form-control m-input" disabled value="<?=$datos_som[0]['svs_temperatura']?>">
       <span>
       <?=$q_signos['signos_campo7']['form_help'];?>
       </span>
    </div>
     <div class="col-md-3">
        <label for="svs_peso">
        <?=$q_signos['signos_campo5']['label'];?>
        </label>
        <input type="text" class="form-control m-input" disabled value="<?=$datos_som[0]['svs_peso']?>">
        <span>
        <?=$q_signos['signos_campo5']['form_help'];?>
        </span>
     </div>
     <div class="col-md-3">
        <label for="svs_estatura">
        <?=$q_signos['signos_campo6']['label'];?>
        </label>
        <input type="text" class="form-control m-input" disabled value="<?=$datos_som[0]['svs_estatura']?>">
        <span>
        <?=$q_signos['signos_campo6']['form_help'];?>
        </span>
     </div>

     <div class="col-md-3">
        <label for="svs_imc">
        <?=$q_signos['signos_campo8']['label'];?>
        </label>
        <input type="text" class="form-control m-input" disabled value="<?=$datos_som[0]['svs_imc']?>">
        <span>
        <?=$q_signos['signos_campo8']['form_help'];?>
        </span>
     </div>
  </div>
  <div class="form-group m-form__group row">
   <div class="col-md-3">
      <label for="svs_grasa">
      <?=$q_signos['signos_campo9']['label'];?>
      </label>
      <input type="text" class="form-control m-input" disabled value="<?=$datos_som[0]['svs_grasa']?>">
      <span>
      <?=$q_signos['signos_campo9']['form_help'];?>
      </span>
   </div>
   <div class="col-md-3">
      <label for="svs_cintura">
      <?=$q_signos['signos_campo10']['label'];?>
      </label>
      <input type="text" class="form-control m-input" disabled value="<?=$datos_som[0]['svs_cintura']?>">
      <span>
      <?=$q_signos['signos_campo10']['form_help'];?>
      </span>
   </div>
   <div class="col-md-3">
      <label for="svs_cuello">
      <?=$q_signos['signos_campo11']['label'];?>
      </label>
      <input type="text" class="form-control m-input" disabled value="<?=$datos_som[0]['svs_cuello']?>">
      <span>
      <?=$q_signos['signos_campo11']['form_help'];?>
      </span>
   </div>
</div>
</form> 
