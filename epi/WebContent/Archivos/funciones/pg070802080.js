  function fValidaBuscar(){
    var form = document.forms[0];
        cVMsg = '';
    var iOpcion = 0;

    if (form.RSTOpcion){
      if (form.RSTOpcion[0].checked && form.RSTOpcion[0].value == "1")
        iOpcion = 1;
      if (form.RSTOpcion[1].checked && form.RSTOpcion[1].value == "2")
        iOpcion = 2;
      if (iOpcion != 2)
        cVMsg = cVMsg + "\n - Búsqueda necesaria solo para opción de Pruebas Rápidas.";
    }
    else
      cVMsg = cVMsg + "\n - Debe elegir una opcion para generar.";
    if (iOpcion == 2){
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
      if (form.SLSFamilia){
        if (form.SLSFamilia.value <= 0){
          cVMsg = cVMsg + "\n - Debe seleccionar la Familia del Artículo.";
        }
      }
      if (form.SLSArticulo){
        if (form.SLSArticulo.value <= 0){
          cVMsg = cVMsg + "\n - Debe seleccionar el Artículo.";
        }
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

    if (form.hdBoton.value == "Buscar"){
      if (window.fValidaBuscar)
        if (!window.fValidaBuscar())
          return false;
    }
    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){
       if (form.iEjercicio){
         if (form.iEjercicio.value <= 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el Ejercicio.";
         }
       }
       if (form.iCveUniMed){
         if (form.iCveUniMed.value <= 0){
           cVMsg = cVMsg + "\n - Debe seleccionar la Unidad Médica.";
         }
       }
       if (form.RSTOpcion){
         if (form.RSTOpcion[0].checked && form.RSTOpcion[0].value == "1")
           iOpcion = 1;
         if (form.RSTOpcion[1].checked && form.RSTOpcion[1].value == "2")
           iOpcion = 2;
         if (iOpcion < 1 || iOpcion > 2)
           cVMsg = cVMsg + "\n - Opción de generación incorrecta, por favor elija una opción.";
         
       }
       else
         cVMsg = cVMsg + "\n - Debe elegir una opcion para generar.";
       if (iOpcion == 2){
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
         if (form.SLSFamilia){
           if (form.SLSFamilia.value <= 0){
             cVMsg = cVMsg + "\n - Debe seleccionar la Familia del Artículo.";
           }
         }
         if (form.SLSArticulo){
           if (form.SLSArticulo.value <= 0){
             cVMsg = cVMsg + "\n - Debe seleccionar el Artículo.";
           }
         }
       }
       if (form.iCantidad){
         if (form.iCantidad.value.length <= 0)
           cVMsg = cVMsg + "\n - Debe proporcionar el número de unidades a generar.";
         else{
           var iCant = parseInt(form.iCantidad.value,10);
           var iTot = 0;
           if (iOpcion == 2)
             if (form.hdTotal){
               iTot = parseInt(form.hdTotal.value,10);
               if (iTot <= 0)
                 cVMsg = cVMsg + "\n - No hay existencias para generar unidades.";
               else
                 if (iCant > iTot)
                   cVMsg = cVMsg + "\n - No puede asignar mas unidades que las existentes."; 
             }
         }
       }

      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }

      if (!confirm(" ¿ Esta seguro que desea asignar las pruebas o formatos ? "))
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
           window.parent.FRMOtroPanel.location="pg070802080P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           objDes2.length=0;
           objDes2.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070802080P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
