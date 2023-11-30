 var ObjPersona;
 var objDsc;

  function fPerObjeto(objeto,objdsc){
   var form = document.forms[0];
   ObjPersona = objeto;
   objDsc = objdsc;
   fSelPer();
  }

  function fSelectedPer(iCvePersonal,iCveExpediente,iExamen,cNombre){
    var form = document.forms[0];
    var flag = "true";
    var Contador = 0;
    cExpediente = iCvePersonal;


    for(i=0;i < form.elements.length;i++){
       if (form.elements[i].name.substring(0,11) == "txtPersonal"){
          if (form.elements[i].value == iCvePersonal){
             //alert("Valor de algun txtPersonal : " + form.elements[i].value + " flag: " + flag);
             Contador = Contador + 1;
          }
       }
     
    }

    if (Contador > 0)
        flag = "false";



    if (flag == "true"){

       for(i=0;i < form.elements.length;i++){
          if (form.elements[i].name == ObjPersona ){
             form.elements[i].value = iCvePersonal;
          }
          if (form.elements[i].name == objDsc ){
             form.elements[i].value = cNombre;
          }
       }
   }
   else
     alert("Favor de seleccionar personal no repetido.");
  }


function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070302030P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }


  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)
  function fValidaTodo(){
    var form = document.forms[0];
    var iExpediente;
    var iPuesto;
    var iSituacion;
    var iVehiculo;
    var iPersonalIgual; 

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?")) 
        return false; 
    }



    if ((form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'PbaRapida' ) && form.hdBoton.value != 'Cancelar' ) {
    //alert("Si entro a la vlaidacion");
      cVMsg = '';



       iExpediente = 0;
       iPuesto = 0;
       iSituacion = 0;
       iVehiculo = 0;
       iFechas = 0;

      

       for(i=0;i < form.elements.length;i++){
           if (form.elements[i].name.substring(0,11) == "txtPersonal"){
              if (form.elements[i].value == "")
                 iExpediente = iExpediente + 1;
           }
           if (form.elements[i].name.substring(0,10) == "iCvePuesto"){
              if (form.elements[i].value == "-1" || form.elements[i].value == "")
                 iPuesto = iPuesto + 1;
           }
            if (form.elements[i].name.substring(0,13) == "iCveSituacion"){
              if (form.elements[i].value == "-1" || form.elements[i].value == "")
                 iSituacion = iSituacion + 1;
           }
            if (form.elements[i].name.substring(0,12) == "iCveVehiculo"){
              if (form.elements[i].value == "-1" || form.elements[i].value == "")
                 iVehiculo = iVehiculo + 1;
           }
          if (form.elements[i].name.substring(0,7) == "dtFecha"){
            cVMsg = cVMsg + fSinValor(form.elements[i],5,'Fecha Vigencia:', true);
           }

       }


      if (iExpediente > 0)
          cVMsg = cVMsg + "\n - Falta Seleccionar Personal Involucrado \n";
      if (iPuesto > 0)
          cVMsg = cVMsg + "\n - Falta Seleccionar Puestos Involucrado \n";
      if (iSituacion > 0)
          cVMsg = cVMsg + "\n - Falta Seleccionar Situaciones Involucrado \n";
      if (iVehiculo > 0)
          cVMsg = cVMsg + "\n - Falta Seleccionar Vehiculos Involucrado \n";


      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }

      if (form.hdBoton.value == 'Guardar') {
      if(!confirm("¿Desea guardar la información del personal involucrado?"))
        return false;
     }

    }
    return true;
  }


 function fEnvio(){
  form = document.forms[0];
  form.hdBoton.value = 'Enviar';
  form.target = "_self";
  form.submit();
}


 function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}
