  function fCancela(){
    var elems=document.forms[0].elements;
    for(var i=0;i<elems.length;i++){
      var elem=elems[i];
      if(elem.type=='checkbox') elem.checked=false;
    }
  }

  function fIrCatalogo(){
    var form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070103011.jsp';
    form.submit();
  }

function fDelCat(){
  if(!confirm("¿Está Seguro de Eliminar las Categorias?"))
        return false;
    var form = document.forms[0];
    form.hdBoton.value = 'Eliminar';
    form.target = 'FRMDatos';
    form.action = 'pg070103010.jsp';
    form.submit();

  }


  function fNuevoEx(){
    var form = document.forms[0];
	if(!form.lNuevo.checked)
		return false;
    if(!confirm("¿Está seguro de solicitar un nuevo examen?"))
        return false;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070103010.jsp';
    form.submit();
  }
