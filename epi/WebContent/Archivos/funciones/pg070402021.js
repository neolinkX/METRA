function fValidaTodo(){
    var form = document.forms[0];
    var cVMsg = '';

     if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
     }

     if(!confirm(" ¿ Está Seguro de Guardar la Asignación del Equipo de Investigación ? "))
        return false;

     return true;
   }
