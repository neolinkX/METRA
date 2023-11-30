 function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070402020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070402020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
    }
  }


function fValidaTodo(){
    var form = document.forms[0];
    var Dia;
    var Mes;
    var Anio;
    var cVMsg = '';
    if (form.iCveUniMed){
         if (form.iCveUniMed.value <= 0 || form.iCveUniMed.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar la unidad m�dica.";
         }
      }

    if (form.iCveModulo){
         if (form.iCveModulo.value <= 0 || form.iCveModulo.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar el M�dulo de la Unidad M�dica.";
         }
      }

     if (form.dtAsigna)
        cVMsg = cVMsg + fSinValor(form.dtAsigna,5,'Fecha de Asignaci�n:', true);

     if (cVMsg != ''){
        alert("Datos no V�lidos: \n" + cVMsg);
        return false;
     }

     if(!confirm(" � Est� Seguro de Guardar la Asignaci�n de Unidad M�dica, M�dulo y Fecha ? "))
        return false;

     return true;
   }


  function fIrPagina(){
    form = document.forms[0];
    form.target = 'FRMDatos';
    form.action = 'pg070402021.jsp';
    form.submit();
  }