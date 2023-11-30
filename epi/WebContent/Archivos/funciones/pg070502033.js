
  function fValidaTodo(){
    var form = document.forms[0];
        cVMsg = '';

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

       if (document.forms[0].dtSolicitud)
         cVMsg = cVMsg + fSinValor(document.forms[0].dtSolicitud,5,'Fecha de Solicitud:', true);

       if (document.forms[0].dtSolicitud){
         FechaIni = document.forms[0].dtSolicitud.value;
         diaIni = FechaIni.substring(0,2);
         mesIni = FechaIni.substring(3,5);
         anioIni = FechaIni.substring(6,10);
         FechaFin = document.forms[0].dtToday.value;
         diaFin = FechaFin.substring(0,2);
         mesFin = FechaFin.substring(3,5);
         anioFin = FechaFin.substring(6,10);
         timeA = new Date(anioIni,mesIni,diaIni);
         timeB = new Date(anioFin,mesFin,diaFin);
         if (timeA > timeB)
           cVMsg = cVMsg + "\n - La Fecha debe ser Menor o Igual a Hoy.";
       }

       if (form.SLSSolicitante){
         if (form.SLSSolicitante.value == 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el Solicitante.";
         }
       }

       if (form.hdExterno){
         if (form.hdExterno.value == "1"){
           if (form.TXTSolicitante){
             if (form.TXTSolicitante.value == ""){
               cVMsg = cVMsg + "\n - Debe Proporcionar el Nombre del Externo.";
             }
           }
         }
       }


       if (form.SLSEtapa){
         if (form.SLSEtapa.value == 0){
           cVMsg = cVMsg + "\n - Debe seleccionar la Etapa.";
         }
       }

      if (form.SLSUsuReg){
        if (form.SLSUsuReg.value == 0){
          cVMsg = cVMsg + "\n - Debe seleccionar el Usuario.";
        }
      }

      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }
      if (!confirm(" ¿ Desea Guardar el Movimiento de la Plantilla ? "))
        return false;
    }
    return true;
  }

