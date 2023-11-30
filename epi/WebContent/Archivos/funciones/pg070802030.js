
  function fValidaTodo(){
    var form = document.forms[0];
        cVMsg = '';

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

       if (form.SLSArticulo){
         if (form.SLSArticulo.value == 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el Artículo.";
         }
       }

       if (form.SLSConcepto){
         if (form.SLSConcepto.value == 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el Concepto.";
         }
       }

      if (form.dtIngreso)
        cVMsg = cVMsg + fSinValor(form.dtIngreso,5,'Fecha de Ingreso:', true);

      if (form.FILUnidades){
        cVMsg = cVMsg + fSinValor(form.FILUnidades,4,'Unidades:', true);
      }

      if (form.FILUnidades){
         if(!fDecimal(form.FILUnidades.value))
           cVMsg = "\n - El campo Unidades: solo permite valores decimales.";
      }

     if (form.FILUnidades){
       if (parseFloat(form.FILUnidades.value) < 0)
         cVMsg = cVMsg + "\n - Las Unidades no pueden ser Menores que Cero.";

     }

     if (form.FILLote){
       if (!form.FILLote.disabled){
         if(!fSoloAlfanumericos(form.FILLote.value) && !fEncCaract(form.FILLote.value, "/") && !fEncCaract(form.FILLote.value, "-"))
           cVMsg = "\n - El campo Lote solo permite caracteres alfanuméricos, diagonal y guión.";
       }
     }

      if (form.dtCaducidad){
        if (!form.dtCaducidad.disabled)
           cVMsg = cVMsg + fSinValor(form.dtCaducidad,5,'Fecha de Caducidad:', true);
      }

      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }

      if (!confirm(" ¿ Desea Asignar la Entrada del Articulo al Almacén ? "))
        return false;

    }
	if(form.lValidaG.value == "0")
	   form.lValidaG.value = "1";
	else{
		alert("- Favor de esperar a que responda la petición del Guardar");
		return false;
	}


    return true;
  }

  function llenaSLT(opc,val1,val2,val3,objDes,objDes2,objDes3){
     if(objDes3!=''){
        if(objDes3.name!=''){
           objDes3.length=1;
           objDes3[0].text="Seleccione...";
        }
     }
     if(objDes2!=''){
        if(objDes2.name!=''){
           objDes2.length=1;
           objDes2[0].text="Seleccione...";
        }
     }
     if (document.forms[0].FILClave)
       document.forms[0].FILClave.disabled = 1;
     if(objDes!=''){
        if(objDes.name!=''){
           if (opc != '4'){
             objDes.length=1;
             objDes[0].text="Cargando Datos...";
           }
           window.parent.FRMOtroPanel.location="pg070802030P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           objDes2.length=0;
           objDes2.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070802030P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
