function fValidaTodo(){
    var form = document.forms[0];
    var cVMsg = '';

     if (cVMsg != ''){
        alert("Datos no V�lidos: \n" + cVMsg);
        return false;
     }

     if(!confirm(" � Est� Seguro de Guardar la Asignaci�n del Equipo de Investigaci�n ? "))
        return false;

     return true;
   }
