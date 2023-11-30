
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deber�n ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensi�n js (vg. pg0702061.js)
function fValidaTodo(){
    var form = document.forms[0];
    var Dia;
    var Mes;
    var Anio;
     if (form.hdBoton.value == 'Guardar' && form.hdBoton.value != 'Cancelar' ) {
      cVMsg = '';

       if (form.iCveUniMed){
         if (form.iCveUniMed.value <= 0){
           cVMsg = cVMsg + " - Debe seleccionar alguna unidad m�dica. \n";
         }
      }
       if (form.iCveProceso){
         if (form.iCveProceso.value <= 0 || form.iCveProceso.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar alg�n proceso. \n";
         }
      }
       if (form.iCveMotivo){
         if (form.iCveMotivo.value <= 0 || form.iCveMotivo.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar alg�n motivo. \n";
         }
      }
      if (form.iCveUsuRecolec){
         if (form.iCveUsuRecolec.value <= 0 || form.iCveUsuRecolec.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar M�dico Recolector. \n";
         }
      }

       if (form.iCveMdoTrans){
         if (form.iCveMdoTrans.value <= 0 || form.iCveMdoTrans.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar el modo de Transporte. \n";
         }
      }

       if (form.hdCvePersonal){
         cVMsg = cVMsg + fSinValor(form.hdCvePersonal,3,'Personal', true);
       }

      if (form.iAnio)
        cVMsg = cVMsg + fSinValor(form.iAnio,3,'Anio', true);
      if (form.iCveMuestra)
        cVMsg = cVMsg + fSinValor(form.iCveMuestra,3,'Muestra', true);
      if (form.hdCveEmpresa)
        cVMsg = cVMsg + fSinValor(form.hdCveEmpresa,3,'Empresa', false);
      if (form.iCvePbaRapida)
        cVMsg = cVMsg + fSinValor(form.iCvePbaRapida,3,'Prueba R�pida', false);
      if (form.iCveUniMed)
        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad M�dica', false);
      if (form.iCveEnvio)
        cVMsg = cVMsg + fSinValor(form.iCveEnvio,3,'Env�o', false);
      if (form.iCveProceso)
        cVMsg = cVMsg + fSinValor(form.iCveProceso,3,'Proceso', false);
      if (form.iCveMotivo)
        cVMsg = cVMsg + fSinValor(form.iCveMotivo,3,'Motivo', false);
      if (form.iCveSituacion)
        cVMsg = cVMsg + fSinValor(form.iCveSituacion,3,'Situacion', false);

     if (form.dtRecoleccion){
         if (form.dtRecoleccion.value != null){
         Dia  = form.dtRecoleccion.value.substring(0,2);
         Mes  = form.dtRecoleccion.value.substring(3,5)-1;
         Anio = form.dtRecoleccion.value.substring(6,10);
         dtFecApi = new Date(Anio,Mes,Dia);

         Dia  = form.hdHoy.value.substring(0,2);
         Mes  = form.hdHoy.value.substring(3,5)-1;
         Anio = form.hdHoy.value.substring(6,10);
         dtFecHoy = new Date(Anio,Mes,Dia);
          if (dtFecApi > dtFecHoy){
            cVMsg = cVMsg + "\n - La Fecha de recolecci�n no puede ser mayor a la fecha actual. \n";
           }
         }
         cVMsg = cVMsg + fSinValor(form.dtRecoleccion,5,'Fecha de recolecci�n', true);
      }

        if (cVMsg != ''){
          alert("Datos no V�lidos: \n" + cVMsg);
          return false;
        }
        msg="� Desea Almacenar la informaci�n del Formato Federal de Control y Cadena de Custodia? ";
        if (!confirm(msg)) return false;

    }
     return true;
   }

 function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}