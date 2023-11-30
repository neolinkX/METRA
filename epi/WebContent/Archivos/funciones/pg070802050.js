
  function fValidaTodo(){
    var form = document.forms[0];

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

       var iSolicitud = 0;
       var lGuardar = true;
       cVMsg = '';
       for (i = 0; i < form.length; i++){
          str = form.elements[i].name;
          if (str.substring(0,7) == "FILFis-"){
             if (form.elements[i].value != ''){
                 //iSolicitud = str.substring(10,form.elements[i].length);
                 if (form.elements[i]){
                     if (parseFloat(form.elements[i].value) < 0)
                        cVMsg = cVMsg + "\n - La Existencia Física del Artículo debe ser positivas";
                     else
                        cVMsg = fSinValor(form.elements[i],4,'Existencia Física ', true);
                 }
             }
          }
       }

       if (document.forms[0].dtCaptura){
          if (document.forms[0].dtCaptura.value != '')
           cVMsg = cVMsg + fSinValor(document.forms[0].dtCaptura,5,'Fecha de Captura:', true);
       }

       if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
       }

       if (!confirm(" ¿ Desea Guardar el Inventario de los Artículos del Almacén ? "))
         return false;
    }

    return true;
  }

  function llenaSLT(opc,val1,val2,val3,objDes,objDes2,objDes3){

     if(objDes3!=''){
        if(objDes3.name!=''){
           objDes3.length=1;
           objDes3[0].text="Inventario Nuevo...";
        }
     }
     if(objDes2!=''){
        if(objDes2.name!=''){
           objDes2.length=1;
           objDes2[0].text="Seleccione...";
        }
     }
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070802050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&objDes2=" + objDes3.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           objDes2.length=0;
           objDes2.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070802050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&objDes2=" + objDes3.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fListado(rwId){
     forma = document.forms[0];

     forma.hdIni.value    = rwId;
     forma.hdBoton.value  = 'Actual';
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070802050.jsp';
     forma.submit();
  }
