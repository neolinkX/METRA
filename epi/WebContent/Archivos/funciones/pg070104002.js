function fillNext(){
       form = document.forms[0];
       form.target =  "FRMDatos";
       form.submit();
}
function fSelectedPer(iCvePersonal,iCveExpediente,iNumExamen){
    form = document.forms[0];
    form.target =  "FRMDatos";
    form.hdICvePersonal.value = iCvePersonal;
    form.iNumExamen.value = iNumExamen;
    form.hdType.value = "P";
    form.submit();
}
 
function fSelExp(){
    form = document.forms[0];
    form.target =  "FRMDatos";
    cVMsg = '';
    if ((form.iCveExpediente.value != null && form.iCveExpediente.value.length > 0) &&
        (form.iCveModulo.value != 0) && (form.iCveProceso.value != 0)){
       if (form.iCveExpediente)
         cVMsg = cVMsg + fSinValor(form.iCveExpediente,3,'Expediente', false);
         if (cVMsg != ''){
            alert("Datos no Válidos: \n" + cVMsg);
         }else{
            form.iNumExamen.value = "";
            form.hdType.value = "E";
            form.submit();
         }
    }else{

       if (form.iCveExpediente.value == null || form.iCveExpediente.value.length == 0){
           cVMsg = cVMsg + "- Ingrese un Número de Expediente \n";
       }
       if (form.iCveModulo.value == 0){
           cVMsg = cVMsg + "- Ingrese la Clave del Módulo \n";
       }
       if (form.iCveProceso.value == 0){
           cVMsg = cVMsg + "- Ingrese la Clave del Proceso \n";
       }
       if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
       }
    }
}


function fValidaTodo(){
    var form = document.forms[0];
    if (form.hdBoton.value != 'Cancelar') {

      cVMsg = '';
       if (form.iCveUniMed.value < 1){
           cVMsg = cVMsg + "- Seleccione una Unidad Médica \n";
       }
       if (form.iCveModulo.value < 1){
           cVMsg = cVMsg + "- Seleccione un Módulo \n";
       }
       if (form.iCveProceso.value < 1){
           cVMsg = cVMsg + "- Seleccione un Proceso \n";
       }
       if (form.iCveExpediente.value == null || form.iCveExpediente.value.length == 0){
           cVMsg = cVMsg + "- Ingrese un Número de Expediente \n";
       }
       else if (form.hdBuscado.value != 1){
           cVMsg = cVMsg + "- Antes de Guardar, básque el Expediente \n";
       }
       if (form.hdBuscado.value == 1 && form.iFolioEs.value==""){
         cVMsg = cVMsg + "- Escriba la encuesta de salida \n";
       }

       if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
       }else{
          if (form.hdBoton.value == 'Guardar'){
            if(confirm("Está seguro de guardar la información?")){
            	form.hdRecarga.value = "1";
              return true;
            }
            else
              return false;
          }
       }
    }
   }
