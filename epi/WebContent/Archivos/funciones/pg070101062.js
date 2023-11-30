  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070101062P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070101062P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fillBusca(){
    form = document.forms[0];
    form.hdBoton.value = "Buscar";
    form.target =  "FRMDatos";
    form.submit();
  }

  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir();
    if (form.hdBoton.value == 'Guardar') {

        msg="¿ Desea guardar el ordenamiento de las ramas ? ";
          if (!confirm(msg))
            return false;

        var msg = "Error. \n\n El orden de las ramas se encuentra duplicado.";
        var pasa = true;
        var i = 0;
        var j = 0;
        var pos = 0;
        var primera = true;
        form = document.forms[0];
        var valores = new Array(form.elements.length);

        for (i = 0; i < form.elements.length; i++) {
            if (form.elements[i].name.substring(0, 6) == 'iOrden') {
                var nombre = form.elements[i].name;
                viOrden = form.elements[nombre].value;
                valores[pos] = viOrden;
                pos++;
            }
        }

        for (i = 0; i < form.elements.length; i++) {
            var encontrado = 0;
            if (form.elements[i].name.substring(0, 6) == 'iOrden') {
                viOrden = form.elements[i].value;
                for (j = 0; j < pos; j++) {
                    if (viOrden == valores[j]) {
                        encontrado++;
                    }
                }
                if (encontrado > 1) {
                    pasa = false;
                }
            }
        }
        if (!pasa) {
            alert(msg);
            return false;
        }
    }
    return true;
  }
