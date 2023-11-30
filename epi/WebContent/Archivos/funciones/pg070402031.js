var aINVResultado = new Array(); //[Nombre campo Tipo de Respuesta,Pregunta,Descripcion de la rama,Obligatorio,Tipo de Respuesta]

function fValidaTodo(){
form = document.forms[0];
cVMsg = '';
if (form.hdBoton.value != 'Cancelar') {
    for (var i=0;i<aINVResultado.length;i++){          // Lee Renglones
        if(aINVResultado[i][3] == '1'){                // Si Es  Obligatorio
           if(aINVResultado[i][4] == '1'){             // Valida el Tipo de Campo Logico
              for (var e=0;e<form.elements.length;e++){
                 if (aINVResultado[i][0] == form.elements[e].name){
                    if (form.elements[e].name){
                       if (form.elements[e].checked == false){
                          cVMsg += '\n - El campo '+aINVResultado[i][1]+' - RAMA: '+aINVResultado[i][2]+' es Obligatorio, favor de introducir su valor.';
                       }
                    }
                 }
              }
           }
           if(aINVResultado[i][4] == '2'){             // Valida el Tipo de Campo Caracter
              for (var e=0;e<form.elements.length;e++){
                 if (aINVResultado[i][0] == form.elements[e].name){
                    if (form.elements[e].name){
                       if(!fSoloAlfanumericos(form.elements[e].value)){
                          cVMsg += '\n - El campo '+aINVResultado[i][1]+' - RAMA: '+aINVResultado[i][2]+' solo permite caracteres alfanuméricos.';
                       }
                       if(form.elements[e].value.length == 0){
                         cVMsg += '\n - El campo '+aINVResultado[i][1]+' - RAMA: '+aINVResultado[i][2]+' es Obligatorio, favor de introducir su valor.';
                       }
                    }
                 }
              }
           }
           if(aINVResultado[i][4] == '3'){             // Valida el Tipo de Campo Numerico
              for (var e=0;e<form.elements.length;e++){
                 if (aINVResultado[i][0] == form.elements[e].name){
                    if (form.elements[e].name){
                       if(!fDecimal(form.elements[e].value)){
                         cVMsg += '\n - El campo '+aINVResultado[i][1]+' - RAMA: '+aINVResultado[i][2]+' solo permite valores decimales.';
                       }
                       if(form.elements[e].value.length == 0){
                         cVMsg += '\n - El campo '+aINVResultado[i][1]+' - RAMA: '+aINVResultado[i][2]+' es Obligatorio, favor de introducir su valor.';
                       }
                    }
                 }
              }
           }
           if(aINVResultado[i][4] == '4'){             // Valida el Tipo de Campo Caracter
              for (var e=0;e<form.elements.length;e++){
                 if (aINVResultado[i][0] == form.elements[e].name){
                    if (form.elements[e].name){
                       if(!fSoloAlfanumericos(form.elements[e].value)){
                          cVMsg += '\n - El campo '+aINVResultado[i][1]+' - RAMA: '+aINVResultado[i][2]+' solo permite caracteres alfanuméricos.';
                       }
                       if(form.elements[e].value.length == 0){
                         cVMsg += '\n - El campo '+aINVResultado[i][1]+' - RAMA: '+aINVResultado[i][2]+' es Obligatorio, favor de introducir su valor.';
                       }
                    }
                 }
              }
           }
           if(aINVResultado[i][4] == '5'){             // Valida el Tipo de Campo Numerico Rango
              for (var e=0;e<form.elements.length;e++){
                 if (aINVResultado[i][0] == form.elements[e].name){
                    if (form.elements[e].name){
                       if(!fDecimal(form.elements[e].value)){
                         cVMsg += '\n - El campo '+aINVResultado[i][1]+' - RAMA: '+aINVResultado[i][2]+' solo permite valores decimales.';
                       }
                       if(form.elements[e].value.length == 0){
                         cVMsg += '\n - El campo '+aINVResultado[i][1]+' - RAMA: '+aINVResultado[i][2]+' es Obligatorio, favor de introducir su valor.';
                       }
                    }
                 }else{
                    if (aINVResultado[i][5] == form.elements[e].name){
                       if (form.elements[e].name){
                          if(!fDecimal(form.elements[e].value)){
                            cVMsg += '\n - El campo '+aINVResultado[i][1]+' - RAMA: '+aINVResultado[i][2]+' solo permite valores decimales.';
                          }
                          if(form.elements[e].value.length == 0){
                            cVMsg += '\n - El campo '+aINVResultado[i][1]+' - RAMA: '+aINVResultado[i][2]+' es Obligatorio, favor de introducir su valor.';
                          }
                       }
                    }
                 }
              }
           }
        }
    }
    if (cVMsg != ''){
       alert("Datos no Válidos: \n" + cVMsg);
       return false;
   }else{
       if (confirm("¿Esta seguro que desea guardar la informacion?")){
          return true;
       }else{
          return false;
       }  
   }
}else{
   form.hdBoton.value='Cancelar';
   form.action = "pg070402030.jsp";
   form.submit();
}
}
