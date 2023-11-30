



//Se Agrego para desplegar pantalla alterna de los servicios

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

       wExp = open("SEServ01.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=600,height=300,screenX=800,screenY=600');
       //wExp.creator = self;
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus 
       fSetModal();
   }


function fBuscar(){
  if(fValidaTodo()){
    form = document.forms[0];
  if (form.iCveExpediente)
	cVMsg = cVMsg + fSinValor(form.iCveExpediente,3,'Expediente', true);
	if (cVMsg != ''){
	    alert("Datos no Válidos: \n" + cVMsg);
	}  else {
		form.hdBoton.value = 'Actual';
		form.tpoBusqueda.value = 'porExpediente';
		form.target = "_self";
		form.submit();
	}
  }
}
 function fIr(cCampoClave, cPropiedad, cPagina){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
   // form.hdCampoClave1.value = cCampoClave;
	form.iCvePerfil.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
	form.hdLOrdenar.value = '';   // para no arrastrar el parámetro
	form.hdLCondicion.value = '';
    form.hdBoton.value = 'Actual';
    
   form.hdiCveExpediente.value = form.hdiCveExpediente.value;
   form.hdiNumExamen.value = form.hdiNumExamen.value;
   form.hdICveServicio.value =  form.hdICveServicio.value;
   form.hdICveProceso.value = form.hdICveProceso.value;
    
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }

/*
  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir();
    

      
     if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
       cErrores = "";
       //fValidaTodo2();
       form.hdBoton.value = "activo";
       for(i=0; i<form.length-3; i++){
         str = form.elements[i].name;
         str2 = form.elements[i+3].name;
         if(str.substring(0,'hdTitulo_'.length)=='hdTitulo_' && str2.substring(0,'hdTitulo_'.length)!='hdTitulo_'){
		   alert(1)
           cTitulo = form.elements[i].value;
           lControlesVacios = true;
           do{
             i++;
             str = form.elements[i].name;
             if(str.substring(0,'lLogico_'.length) == 'lLogico_'){
               if(form.elements[i].checked)
                 lControlesVacios = false;
             }
             if(str.substring(0,'cCaracter_'.length) == 'cCaracter_'){
             alert('cCaracter_'.length);
               if(form.elements[i].value != "" || form.elements[i].value != "-1" )
                 lControlesVacios = false;
             }
             if(str.substring(0,'dValorI_'.length) == 'dValorI_'){
               if(form.elements[i].value != "")
                 lControlesVacios = false;
             }
           }while(str.substring(0,'hdTitulo_'.length)!='hdTitulo_' && i<form.length-1);
           i--;
           if(lControlesVacios)
             cErrores += " - Seleccione una opción de: " + cTitulo + "\n";
         }
       }
       if(cErrores!=""){
         alert(cErrores);
         return false;
       }
       else if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.iCveUniMed)
        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad médica', true);
      if (form.iCveModulo)
        cVMsg = cVMsg + fSinValor(form.iCveModulo,3,'Módulo', true);
      if (form.iCveServicio)
        cVMsg = cVMsg + fSinValor(form.iCveServicio,0,'Servicio', false);
      if (form.iCveExpediente)
        cVMsg = cVMsg + fSinValor(form.iCveExpediente,3,'Expediente', false);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }
*/

function truncar(campo){
  campo.value=campo.value.substring(0,499);
return true;
}

function truncar2(campo){
  campo.value=campo.value.substring(0,999);
return true;
}


function validaNumero(campo){
  if(isNaN(campo.value)) {
    alert("Debe escribir un número");
    campo.focus();
    return false;
  }
  return true;
}


 function fIrVerExamen(cCampoClave, cPropiedad, cPagina){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    //form.hdCampoClave1.value = cCampoClave;
	form.iCvePerfil.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
	form.hdLOrdenar.value = '';   // para no arrastrar el parámetro
	form.hdLCondicion.value = '';
    form.hdBoton.value = 'Actual';
    
   form.hdiCveExpediente.value = form.hdiCveExpediente.value;
   form.hdiNumExamen.value = form.hdiNumExamen.value;
   form.hdICveServicio.value =  form.hdICveServicio.value;
   form.hdICveProceso.value = form.hdICveProceso.value;
    
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }
  


  
   function fMosRef70I(orden){
      form = document.forms[0];
                    cVMsg = '';
                    form.icveexpediente.value = form.icveexpediente.value;
                    form.inumexamen.value = form.inumexamen.value;
                    form.servicio.value = form.servicio.value;
                    form.rama.value = form.rama.value;
					form.iorden.value = orden;
                    form.submit();
  }
