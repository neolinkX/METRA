
  function fValidaTodo(){
    var form = document.forms[0];
        cVMsg = '';

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

       if (form.hdModificar){
         //Guardar del Nuevo.
         if (form.hdModificar.value == '1'){

            if (form.dtSolicitud)
             cVMsg = cVMsg + fSinValor(form.dtSolicitud,5,'Fecha de Solicitud:', true);

            if (form.dtVencimiento)
             cVMsg = cVMsg + fSinValor(form.dtVencimiento,5,'Fecha de Vencimiento:', true);

            if (form.SLSAnio){
              if (form.SLSAnio.value == 0){
                cVMsg = cVMsg + "\n - Debe seleccionar el Año.";
              }
            }

            if (form.SLSPeriodo){
              if (form.SLSPeriodo.value == 0){
                cVMsg = cVMsg + "\n - Debe seleccionar el Periodo.";
              }
            }

            if (form.SLSUniMedSolicita){
              if (form.SLSUniMedSolicita.value == 0){
               cVMsg = cVMsg + "\n - Debe seleccionar la Unidad Médica que Solicita.";
              }
            }

            if (form.SLSUsrSolicita){
              if (form.SLSUsrSolicita.value == 0){
                cVMsg = cVMsg + "\n - Debe seleccionar el Usuario que Solicita.";
              }
            }

            if (cVMsg != ''){
              alert("Datos no Válidos: \n" + cVMsg);
              return false;
            }

            if (!confirm(" ¿ Desea Guardar la Plantilla Nueva ? "))
              return false;
         }
         //Guardar del Actualizar.
         else {

            if (form.SLSUniMedRecibe){
              if (form.SLSUniMedRecibe.value == 0){
               cVMsg = cVMsg + "\n - Debe seleccionar la Unidad Médica que Recibe.";
              }
            }

            if (form.SLSUsrRecibe){
              if (form.SLSUsrRecibe.value == 0){
                cVMsg = cVMsg + "\n - Debe seleccionar el Usuario que Recibe.";
              }
            }

            if (form.SLSMedio){
              if (form.SLSMedio.value == 0){
                cVMsg = cVMsg + "\n - Debe seleccionar el Medio de Recepción.";
              }
            }

            if (form.SLSTpoEntrega){
              if (form.SLSTpoEntrega.value == 0){
                cVMsg = cVMsg + "\n - Debe seleccionar el Tipo de Entrega.";
              }
            }

            if (cVMsg != ''){
              alert("Datos no Válidos: \n" + cVMsg);
              return false;
            }

            if (!confirm(" ¿ Desea Guardar la Modificación a la Plantilla ? "))
              return false;

         }
       }
    }
    return true;
  }

  function llenaSLT(opc,val1,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070502032P.jsp?opc=" + opc + "&val1=" + val1 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           objDes2.length=0;
           objDes2.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070502032P.jsp?opc=" + opc + "&val1=" + val1 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function llenaFecha(opc,val1,val2,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           //objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070502032P.jsp?opc=" + opc + "&val1=" + val1  + "&val2=" + val2 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action ;
        } else {
           alert("Debe seleccionar un dato valido!");
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070502032P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function llenaUsuario(opc,val1,val2,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           //objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070502032P.jsp?opc=" + opc + "&val1=" + val1  + "&val2=" + val2 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070502032P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fListado1(rwId,rwId2){
     forma = document.forms[0];
     forma.hdIni.value    = rwId;
     forma.hdIni2.value   = rwId2;
     forma.hdBoton.value  = 'Actual';
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070502033.jsp';
     forma.submit();
  }

  function fListado2(rwId,rwId2){
     forma = document.forms[0];
     forma.hdIni.value    = rwId;
     forma.hdIni2.value   = rwId2;
     forma.hdBoton.value  = 'Actual';
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070502034.jsp';
     forma.submit();
  }

  function fSubmitForm1(theButton){
    var form = document.forms[0];
    form.hdBoton.value = theButton;
    if(form.hdBoton.value == 'Imprimir')
      fImprimir();
    else{
      form.target="_self";
      if (window.fValidaTodo)
        lSubmitir = fValidaTodo();
      else
        lSubmitir = true;
      if (lSubmitir){
        window.parent.parent.FRMCuerpo.FRMFiltro.FRMBusqueda.fSubmiteInt();
      }else
        form.hdBoton.value = "";
    }
  }

  function fCerrar(rwId,rwId2){
    var form = document.forms[0];
        cVMsg = '';

       if (form.SLSUniMedRecibe){
         if (form.SLSUniMedRecibe.value == 0){
           cVMsg = cVMsg + "\n - Debe seleccionar la Unidad Médica.";
         }
       }

       if (form.SLSUsrRecibe){
         if (form.SLSUsrRecibe.value == 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el Usuario que Recibe.";
         }
       }

       if (form.SLSMedio){
         if (form.SLSMedio.value == 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el Medio de Recepción.";
         }
       }

       if (form.SLSTpoEntrega){
         if (form.SLSTpoEntrega.value == 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el Tipo de Entrega.";
         }
       }

      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
      } else {
         if (confirm(" ¿ Desea Cerrar la Plantilla Seleccionada ? ")){
           forma                = document.forms[0];
           forma.hdIni.value    = rwId;
           forma.hdIni2.value   = rwId2;
           forma.hdBoton.value  = 'Cerrar';
           forma.target         = 'FRMDatos';
           forma.action         = 'pg070502032.jsp';
           forma.submit();
         }
      }
  }
