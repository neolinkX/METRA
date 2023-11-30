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
  function fCambiaServicio() {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101052.jsp';
    form.submit();
  }

  function fIrRamas(cCampoClave){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101050.jsp';
    form.submit();
 }

