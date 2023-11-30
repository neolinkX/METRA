
  function fValidaTodo(){
    var form = document.forms[0];
       cVMsg = '';

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

       for (i = 0; i < form.length; i++){
          strmin = form.elements[i].name;
          strmax = form.elements[i].name;
          if (strmin.substring(0,7) == "FILMin-"){
             if (form.elements[i].value != ''){
                if (parseFloat(form.elements[i].value) < 0)
                    cVMsg = cVMsg + "\n - El Valor Mínimo del Artículo debe ser positivo";
                else
                    cVMsg = cVMsg + fSinValor(form.elements[i],4,'Valor Mínimo del Artículo ', true);
             }
          }
          if (strmax.substring(0,7) == "FILMax-"){
             if (form.elements[i].value != ''){
                if (parseFloat(form.elements[i].value) < 0)
                   cVMsg = cVMsg + "\n - El Valor Máximo del Artículo debe ser positivo";
                else
                   cVMsg = cVMsg + fSinValor(form.elements[i],4,'Valor Máximo del Artículo ', true);
             }
          }
       }

       if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
       }

       if (!confirm(" ¿ Desea Asignar los Artículos por Almacén ? "))
         return false;

    }
    return true;
  }

  function llenaSLT(opc,val1,val2,val3,objDes,objDes2){
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
           window.parent.FRMOtroPanel.location="pg070802020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           objDes2.length=0;
           objDes2.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070802020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fListado(rwId){
     forma = document.forms[0];

     forma.hdIni.value    = rwId;
     forma.hdBoton.value  = 'Actual';
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070802021.jsp';
     forma.submit();
  }

  function fSubmitForm1(theButton){
    var form = document.forms[0];
    form.hdBoton.value = theButton;
    if(form.hdBoton.value == 'Imprimir')
      fImprimir();
    else{
      form.target="_self";
      if (window.fValidaTodo)
        lSubmitir = fValidaTodo();
      else
        lSubmitir = true;
      if (lSubmitir){
        window.parent.parent.FRMCuerpo.FRMFiltro.FRMBusqueda.fSubmiteInt();
      }else
        form.hdBoton.value = "";
    }
  }
