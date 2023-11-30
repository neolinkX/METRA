  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value != 'Cancelar') {
       form = document.forms[0];
       if(form.iCveRecomendacion.length > 0){
         for (var j=0;j<form.iCveRecomendacion.length;j++){
             form.iCveRecomendacion.options[j].selected=true;
         }
       }else{
         alert("Debe Seleccionar al Menos una Recomendacion");
         return false;
       }
       if(confirm('Al Guardar la informacion no podra ser modificada posteriormente, ¿Esta seguro que desea Guardar?')){
          return true;
       }
   }else{
     form.hdBoton.value='Cancelar';
     form.action = "pg070402030.jsp";
     form.submit();
   }
  }


  function fOnAddR(){
    form = document.forms[0];
    foundIt = 0;
    iCont = 0;
    for (var i=0;i<aINVRecDsc.length;i++){
       for (var j=0;j<aINVRecDsc[i].length;j++){
          if(form.iCveRecomendacionS.value == aINVRecDsc[i][j]){
            if(form.iCveRecomendacion.length > 0){
               for (var k=0;k<form.iCveRecomendacion.length;k++){
                   if(aINVRecDsc[i][0] == form.iCveRecomendacion.options[k].value){
                     foundIt = 1;
                     iCont = k;
                   }
               }
               if (foundIt == 1){
                  alert("Esta Opcion ya Existe en la Lista !!!");
                  foundIt = 0;
                  break;
               }else{
                  form.iCveRecomendacion.options [form.iCveRecomendacion.length] = new Option(aINVRecDsc[i][2],aINVRecDsc[i][0]);
                  form.iCveRecomendacionS.value == "";
                  foundIt = 0;
                  break;
               }
            }else{
               form.iCveRecomendacion.options [form.iCveRecomendacion.length] = new Option(aINVRecDsc[i][2],aINVRecDsc[i][0]);
               form.iCveRecomendacionS.value == "";
               break;
            }
          }
       }
    }
  }

function fChgArea(fArea,Cont){
  form = document.forms[0];
      cText = fArea.value;
      if(cText.length > 1999)
        fArea.value = cText = cText.substring(0,1999);

      form.iNoLetras.value = 1999 - cText.length;

}
