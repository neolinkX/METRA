  var wExp;
  function fShowReport(cPagina,iTipo){
      frm = document.forms[0];
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
        if(fValidaTodo()){
          cCols = frm.cCampos.value;
          cTit = frm.cTitulo.value;
          wExp = open(cPagina+'?hdTit='+cTit+'&hdCols='+cCols+'&hdQuery='+frm.cSQL.value+'&hdTipo='+iTipo,"Selector",'dependent=yes,hotKeys=no,location=no,menubar=yes,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=550,screenX=800,screenY=600');
          wExp.moveTo(50, 50);
          window.onclick=HandleFocus
          window.onfocus=HandleFocus
          fSetModal();
        }
      }
  }

  function fSaveReport(){
      frm = document.forms[0];
      if(fValidaTodo()){
         if(frm.cReportes.value == '-1'){
           answer = prompt("Introduzca el nombre del reporte...","")
           frm.hdBoton.value = 'Guardar';
         }
         else{
           answer = frm.cReportes.value;
           frm.hdBoton.value = 'GuardarA';
         }
         if (answer) {
            frm.hdNameRep.value = answer;
            frm.target = '_self';
            frm.submit();
         }
      }
  }

  function fValidaTodo(){
      frm = document.forms[0];
      cMsg = '';
      if(frm.cSQL){
         if(frm.cSQL.value == '')
            cMsg = cMsg + '\n - Debe introducir una sentencia select válida!'
      }
      if(frm.cTitulo){
         if(frm.cTitulo.value == '')
            cMsg = cMsg + '\n - Debe introducir el título del reporte!'
      }
      if(frm.cCampos){
         if(frm.cCampos.value == '')
            cMsg = cMsg + '\n - Debe introducir la etiqueta de los campos!'
      }
      if(cMsg != ''){
        alert('Se presentaron los siguientes mensajes:' + cMsg);
        return false;
      }
      return true;
  }

  function fSelRep(){
    frm = document.forms[0];
    frm.hdBoton.value = 'Selected';
    frm.target = '_self';
    frm.submit();
  }

  function fDelReport(){
    frm = document.forms[0];
    if(frm.cReportes.value != '-1'){
      if(confirm("¿Desea usted eliminar el reporte '" + frm.cReportes.value + "'?")){
        frm.hdBoton.value = 'Borrar';
        frm.target = '_self';
        frm.submit();
      }
    }else{
      alert('Debe seleccionar un Reporte');
    }
  }
