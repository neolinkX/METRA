var wExp;
  function fServicio(iCveExpediente,iNumExamen,iCveServicio,cDscServicio){
      cInicio = "";
      form = document.forms[0];
      if((wExp != null) && (!wExp.closed))  
        wExp.focus();

       cInicio = "?hdiCveExpediente=" + iCveExpediente;
       cInicio += "&hdiNumExamen=" + iNumExamen;
       cInicio += "&hdiCveServicio=" + iCveServicio;
       cInicio += "&hdcDscServicio=" + cDscServicio;

       //alert("valor de cInicio" + cInicio);

       wExp = open("SEServ01.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=600,height=300,screenX=800,screenY=600');
       //wExp.creator = self;
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus 
       fSetModal();
   }





  function fValidaTodo(){
    var form = document.forms[0];
    lRadioCheck = false;
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
if(confirm('¿Al Guardar este Examen quedara concluido y la informacion no podra ser modificada posteriormente, Esta seguro que desea Guardar?')){
    if (form.hdBoton.value != 'Cancelar') {
       form = document.forms[0];

       // VALIDA QUE SE SELECCIONE APTO O NO APTO ANTES DE GUARDAR
       bandera = false;
       for(i=0; i<form.length-1; i++){
         str = form.elements[i].name;
         str2 = form.elements[i+1].name;
         if(str.substring(0,'lDictamen'.length)=='lDictamen' && str2.substring(0,'lDictamen'.length)=='lDictamen' && bandera==false){
           if(!form.elements[i].checked && !form.elements[i+1].checked && bandera==false){
             alert("Seleccione: Apto o No Apto");
             bandera = true;
             return false;
           }
         }
       }


       // VALIDA QUE SE SELECCIONE AL MENOS UN DIAGNOSTICO AL SELECCIONAR NO APTO
       for(i=0; i<form.length-1; i++){
         str = form.elements[i].name;
         str2 = form.elements[i+1].name;
         if(str.substring(0,'lDictamen'.length)=='lDictamen' && str2.substring(0,'lDictamen'.length)=='lDictamen' && lRadioCheck==false){
           if(!form.elements[i].checked && form.elements[i+1].checked && lRadioCheck==false){
             lRadioCheck = true;
           }
         }
       }

       if(form.iCveDiagnostico.length > 0){
         for (var i=0;i<form.iCveDiagnostico.length;i++){
             form.iCveDiagnostico.options[i].selected=true;
         }
       }
       else if(form.iEMOServ.value==1 && lRadioCheck){
         alert("Debe Seleccionar al Menos un Diagnostico");
         return false;
       }

       if(form.iCveRecomendacion.length > 0){
         for (var j=0;j<form.iCveRecomendacion.length;j++){
             form.iCveRecomendacion.options[j].selected=true;
         }
       }
       
       if(form.iCveDiagnostico2.length > 0){
           for (var j=0;j<form.iCveDiagnostico2.length;j++){
               form.iCveDiagnostico2.options[j].selected=true;
           }
         }
       
       //else{
       //  alert("Debe Seleccionar al Menos una Recomendacion");
       //  return false;
       //}

    }
    return true;
}

  }


  function fOnAdd(){
    form = document.forms[0];
    foundIt = 0;
    iCont = 0;
    iBusquedaAlter = 0;

     cVMsg = '';

      if(form.iCveDiagnosticoS){
     cVMsg = cVMsg + fSinValor(form.iCveDiagnosticoS,0,'Diagnï¿½stico', true);

     }

      if (cVMsg != ''){
          alert("Datos no Vï¿½lidos: \n" + cVMsg);
          return false;
        }


    for (var i=0;i<aMEDDiagDsc.length;i++){
       for (var j=0;j<aMEDDiagDsc[i].length;j++){
          if(form.iCveDiagnosticoS.value == aMEDDiagDsc[i][j]){
            if(form.iCveDiagnostico.length > 0){
               for (var k=0;k<form.iCveDiagnostico.length;k++){
                   if(aMEDDiagDsc[i][1]+"_"+aMEDDiagDsc[i][0] == form.iCveDiagnostico.options[k].value){
                     foundIt = 1;
                     iCont = k;
                   }
               }
               if (foundIt == 1){
                  alert("Esta Opcion ya Existe en la Lista !!!"); 
                  foundIt = 0;
                  iBusquedaAlter = 1;
               }else{
                  form.iCveDiagnostico.options [form.iCveDiagnostico.length] = new Option(aMEDDiagDsc[i][2],aMEDDiagDsc[i][1]+"_"+aMEDDiagDsc[i][0]);
                  form.iCveDiagnosticoS.value == "";
                  foundIt = 0;
                  iBusquedaAlter = 1;
               }
            }else{
               form.iCveDiagnostico.options [form.iCveDiagnostico.length] = new Option(aMEDDiagDsc[i][2],aMEDDiagDsc[i][1]+"_"+aMEDDiagDsc[i][0]);
               form.iCveDiagnosticoS.value == "";
               iBusquedaAlter = 1;
            }
          }
       }
  
     }
    //if (iBusquedaAlter == 0){ 
    //   alert("El diagnostico no es de los mï¿½s frecuentes. La bï¿½squeda puede durar algunos segundos !!");
    //   fOnAddTodos();
    //}
  }


  function fOnAddCif(){
    form = document.forms[0];
    foundIt = 0;
    iCont = 0;
    iBusquedaAlter = 0;
    cVMsg = '';

      if(form.iCveDiagnosticoS2){
    	  cVMsg = cVMsg + fSinValor(form.iCveDiagnosticoS2,0,'Diagnóstico Cif', true);
     }

      if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
   

    for (var i=0;i<aMEDCifDsc.length;i++){
       for (var j=0;j<aMEDCifDsc[i].length;j++){
          if(form.iCveDiagnosticoS2.value == aMEDCifDsc[i][j]){
            if(form.iCveDiagnostico2.length > 0){
               for (var k=0;k<form.iCveDiagnostico2.length;k++){
                   if(aMEDCifDsc[i][1]+"_"+aMEDCifDsc[i][0] == form.iCveDiagnostico2.options[k].value){
                     foundIt++;
                     iCont = k;
                   }
               }
               if (foundIt == 0){
                   form.iCveDiagnostico2.options [form.iCveDiagnostico2.length] = new Option(aMEDCifDsc[i][2],aMEDCifDsc[i][1]+"_"+aMEDCifDsc[i][0]);
                   form.iCveDiagnosticoS2.value == "";
                   foundIt = 0;
                   iBusquedaAlter = 1;
                }
               if (foundIt == 3){
                  alert("Esta Opcion ya Existe en la Lista !!!"); 
                  foundIt = 0;
                  iBusquedaAlter = 1;
               }
              
            }
            else{
               form.iCveDiagnostico2.options [form.iCveDiagnostico2.length] = new Option(aMEDCifDsc[i][2],aMEDCifDsc[i][1]+"_"+aMEDCifDsc[i][0]);
               form.iCveDiagnosticoS2.value == "";
               iBusquedaAlter = 1;
            }
          }
       }
  
     }
  }


    function fOnAddTodos(){
    form = document.forms[0];
    foundIt = 0;
    iCont = 0;
    iBusquedaAlter = 0;
    for (var i=0;i<aMEDDiagDscTodos.length;i++){

        for (var j=0;j<aMEDDiagDscTodos[i].length;j++){

          if(form.iCveDiagnosticoS.value == aMEDDiagDscTodos[i][j]){
            if(form.iCveDiagnostico.length > 0){
               for (var k=0;k<form.iCveDiagnostico.length;k++){
                   if(aMEDDiagDscTodos[i][1]+"_"+aMEDDiagDscTodos[i][0] == form.iCveDiagnostico.options[k].value){
                     foundIt = 1;
                     iCont = k;
                   }
               }
               if (foundIt == 1){
                  alert("Esta Opcion ya Existe en la Lista !!!"); 
                  foundIt = 0;
                  iBusquedaAlter = 1;
               }else{
                  form.iCveDiagnostico.options [form.iCveDiagnostico.length] = new Option(aMEDDiagDscTodos[i][2],aMEDDiagDscTodos[i][1]+"_"+aMEDDiagDscTodos[i][0]);
                  form.iCveDiagnosticoS.value == "";
                  foundIt = 0;
                 iBusquedaAlter = 1;
                 //alert("else de nbo foundit");
               }
            }else{
               form.iCveDiagnostico.options [form.iCveDiagnostico.length] = new Option(aMEDDiagDscTodos[i][2],aMEDDiagDscTodos[i][1]+"_"+aMEDDiagDscTodos[i][0]);
               form.iCveDiagnosticoS.value == "";
               iBusquedaAlter = 1;
               //alert("else de no esta en la lista");
            }
          }
                
       }
  
     }
    if (iBusquedaAlter == 0 )
       alert("El diagnostico no fue encontrado !!!!");
  }



  function fOnAddR(){
    form = document.forms[0];
    foundIt = 0;
    iCont = 0;
    for (var i=0;i<aMEDRecDsc.length;i++){
       for (var j=0;j<aMEDRecDsc[i].length;j++){
          if(form.iCveRecomendacionS.value == aMEDRecDsc[i][j]){
            if(form.iCveRecomendacion.length > 0){
               for (var k=0;k<form.iCveRecomendacion.length;k++){
                   if(aMEDRecDsc[i][1]+"_"+aMEDRecDsc[i][0] == form.iCveRecomendacion.options[k].value){
                     foundIt = 1;
                     iCont = k;
                   }
               }
               if (foundIt == 1){
                  alert("Esta Opcion ya Existe en la Lista !!!");
                  foundIt = 0;
                  break;
               }else{
                  form.iCveRecomendacion.options [form.iCveRecomendacion.length] = new Option(aMEDRecDsc[i][2],aMEDRecDsc[i][1]+"_"+aMEDRecDsc[i][0]);
                  form.iCveRecomendacionS.value == "";
                  foundIt = 0;
                  break;
               }
            }else{
               form.iCveRecomendacion.options [form.iCveRecomendacion.length] = new Option(aMEDRecDsc[i][2],aMEDRecDsc[i][1]+"_"+aMEDRecDsc[i][0]);
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

  function fBuscarCIE10(ivar){
      cInicio = "";
      form = document.forms[0];

      if((wExp != null) && (!wExp.closed))
        wExp.focus();

       wExp = open("pg070105012.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=800,height=600,screenX=800,screenY=600');
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
   }

  
  
  function fIrVerExamen(cCampoClave, cPropiedad, cPagina, examen){
     form = document.forms[0];
     form.hdCampoClave.value = cCampoClave;
     //form.hdCampoClave1.value = cCampoClave;
 	//form.iCvePerfil.value = cCampoClave;
     form.hdCPropiedad.value = cPropiedad;
     form.hdRowNum.value = cCampoClave;
 	form.hdLOrdenar.value = '';   // para no arrastrar el parï¿½metro
 	form.hdLCondicion.value = '';
     form.hdBoton.value = 'Actual';

    form.hdiCveExpediente.value = form.iCveExpediente.value;
    form.hdiNumExamen.value = examen;
    form.hdICveServicio.value =  form.iCveServicio.value;
    form.hdICveProceso.value = form.iCveProceso.value;
   
     form.target = 'FRMDatos';
     form.action = cPagina;
     form.submit();
   }

  
  function fVerExamen(iCveExpediente,iNumExamen,iCveServicio,iCveProceso){
      cInicio = "";
      form = document.forms[0];

      cInicio = "?hdiCveExpediente=" + iCveExpediente;
      cInicio += "&hdiNumExamen=" + iNumExamen;
      cInicio += "&hdICveServicio=" + iCveServicio;
      cInicio += "&hdICveProceso=" + iCveProceso;

      if((wExp != null) && (!wExp.closed))
        wExp.focus();

       //  wExp = open("pg070104021.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=700,height=500,screenX=800,screenY=600');
       wExp = open("pg070104070.jsp"+cInicio, "Consulta",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=300,screenX=800,screenY=600');
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
   }


  
  
  
  
  
  