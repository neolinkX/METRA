  function fValidaBuscar(){
    var form = document.forms[0];
        cVMsg = '';

    if (form.iCveUniMed){
      if (form.iCveUniMed.value <= 0){
        cVMsg = cVMsg + "\n - Debe seleccionar la Unidad Médica.";
      }
    }
    if (form.SLSAlmacen){
      if (form.SLSAlmacen.value <= 0){
        cVMsg = cVMsg + "\n - Debe seleccionar el Almacén.";
      }
    }
    if (form.SLSTipoArticulo){
      if (form.SLSTipoArticulo.value <= 0){
        cVMsg = cVMsg + "\n - Debe seleccionar el Tipo de Artículo.";
      }
    }

    if (cVMsg != ''){
      alert("Datos no Válidos: \n" + cVMsg);
      return false;
    }
    return true;
  }

  function fValidaTodo(){
    var form = document.forms[0];
        cVMsg = '';
    var iOpcion = 0;

    if (window.fValidaBuscar)
      if (!window.fValidaBuscar())
        return false;
    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){
       if (form.SLSTipoMov){
         if (form.SLSTipoMov.value <= 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el Tipo de Movimiento.";
         }
       }
       if (form.SLSConceptoMov){
         if (form.SLSConceptoMov.value <= 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el Concepto de Movimiento.";
         }
       }
       if (form.iEjercicio){
         if (form.iEjercicio.value <= 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el Ejercicio.";
         }
       }
       var objetoFocus = null;
       for (i=0; i<form.length; i++){
         str = form.elements[i].name;
         str1 = "";
         if (i > 0)
           str1 = form.elements[i-1].name;
         cSubNombre = 'iCantidad_';
         cSubNombre2 = 'cObserva_';
         if (str.substring(0,cSubNombre.length)==cSubNombre){
           if(form.elements[i].value.length > 0) 
             if(!fSoloNumeros(form.elements[i].value)){
               cVMsg = cVMsg + "\n - El campo Cantidad solo permite caracteres numéricos.";
               if (objetoFocus == null)
                 objetoFocus = form.elements[i];
             }
         }
         else if (str.substring(0,cSubNombre2.length)==cSubNombre2){
           if (form.elements[i].value.length > 0)
             if (str1.substring(0,cSubNombre.length)==cSubNombre){
               if(form.elements[i-1].value.length == 0){
                 cVMsg = cVMsg + "\n - El campo Cantidad debe ser proporcionado si desea realizar un ajuste y guardar observación.";
                 if (objetoFocus == null)
                   objetoFocus = form.elements[i-1];
               }
             }
         }
       }
       if (objetoFocus != null)
         objetoFocus.focus();

      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }

      if (!confirm(" ¿ Esta seguro que desea guardar los datos y realizar el ajuste ? "))
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
           window.parent.FRMOtroPanel.location="pg070802070P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           objDes2.length=0;
           objDes2.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070802070P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
