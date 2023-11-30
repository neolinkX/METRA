  function fValidaTodo(){
    var form = document.forms[0];
    if (form.hdBoton.value == "Guardar"){
      cVMsg = '';
      if (form.dtInicio)
         cVMsg = cVMsg + fSinValor(form.dtInicio,5,'Fecha Inicio', true);
      if (form.dtFin)
         cVMsg = cVMsg + fSinValor(form.dtFin,5,'Fecha Fin', true);
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      } else {
         vIni = form.dtInicio.value;
         vFin = form.dtFin.value;
         dtIni = vIni.substring(6, 10) + vIni.substring(3, 5) + vIni.substring(0, 2);
         dtFin = vFin.substring(6, 10) + vFin.substring(3, 5) + vFin.substring(0, 2);
//         alert(dtIni < dtFin);
         if (dtIni <= dtFin) {
           if (!confirm(" ¿ Desea Guardar los Cambios ? "))
             return false;
         } else {
           alert('La fecha inicial debe ser menor a la fecha final.');
           return false;
         }
      }
    }
    return true;
  }
  function fDetalles(iPag, iCveMan, iCveVeh, hdLectura) {
    var form = document.forms[0];
    var pag = "";
    form.iCveMantenimiento.value = iCveMan;
    form.iCveVehiculo.value = iCveVeh;
    if (iPag == 1)
       pag = "pg070702041.jsp";
    else if (iPag == 2)
       pag = "pg070702042.jsp";
    form.hdBoton.value = 'Actual';
    form.hdLectura.value = hdLectura;
    form.target = 'FRMDatos';
    form.action = pag;
    form.submit();
  }

  function fValidaBuscar(){
     var form = document.forms[0];
     cVMsg = '';
     if (form.dtInicio)
        cVMsg = cVMsg + fSinValor(form.dtInicio,5,'Fecha Inicio', true);
     if (form.dtFin)
        cVMsg = cVMsg + fSinValor(form.dtFin,5,'Fecha Fin', true);

     if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
     } else {
        vIni = form.dtInicio.value;
        vFin = form.dtFin.value;
        dtIni = vIni.substring(6, 10) + vIni.substring(3, 5) + vIni.substring(0, 2);
        dtFin = vFin.substring(6, 10) + vFin.substring(3, 5) + vFin.substring(0, 2);
        if (dtIni <= dtFin) {
          return true;
        } else {
          alert('La fecha inicial debe ser menor a la fecha final.');
          return false;
        }
     }
     return true;
  }