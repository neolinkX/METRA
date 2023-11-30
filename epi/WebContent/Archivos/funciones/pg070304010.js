
  function fValidaTodo(){
    var form = document.forms[0];
       cVMsg = '';

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

       var iCuantos = 0;
       for(var i=0;i<form.elements.length;i++){
         var e = form.elements[i];
         if(e.type == "checkbox"){
            var nombre = e.name;
            if(nombre.substring(0,7) == "TBXSel-"){
              if (e.checked)
                iCuantos = iCuantos + 1;
            }
         }

         strmin = form.elements[i].name;
         if (strmin.substring(0,12) == "FILDilucion-"){
            if (form.elements[i].value != ''){
               if (parseFloat(form.elements[i].value) < 0)
                   cVMsg = cVMsg + "\n - El Valor de la Dilución debe ser positivo";
               else
                   cVMsg = cVMsg + fSinValor(form.elements[i],4,'Valor de la Dilución ', true);
            }
         }
       }
       if (iCuantos == 0){
         cVMsg = cVMsg + '\n - Debe Seleccionar al menos un Lote Presuntivo';
         //return false;
       }

       if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
       }

      if (!confirm(" ¿ Desea Procesar la Generación del Lote Confirmatorio ? "))
        return false;

    }






    return true;
  }
