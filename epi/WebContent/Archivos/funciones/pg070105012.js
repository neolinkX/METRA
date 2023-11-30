form = document.forms[0];

  function fBuscar(){
	  if(fValidaTodo()){
         form = document.forms[0];
         form.hdBoton.value = 'Buscar';
         form.target = "_self";
         form.submit();
	  }
   }



  function fValidaTodo(){
    var cVMsg = '';
	if(form.cDscDiagnostico.value.length == 0 && 
	   form.cCIE10.value.length == 0)
       cVMsg += "- Debe indicar por lo menos un criterio de b�squeda.";


	if(form.cCIE10.value.length > 0 &&
	   form.cCIE10.value.length < 2)
       cVMsg += "- El Criterio de B�squeda de la Clave CIE 10 debe contener mas de 2 caract�res.\n";

	if(form.cDscDiagnostico.value.length > 0 &&
	   form.cDscDiagnostico.value.length < 5)
       cVMsg += "- El Criterio de B�squeda de la Descripci�n del diagn�stico debe contener mas de 5 letras.\n";

    if (cVMsg != ''){
       alert("Datos no V�lidos: \n" + cVMsg);
       return false;
    } 
    return true;
  }

  function fSeleccionar(iCveEspecialidad, iCveDiagnostico, cDscDiagnostico, cCIE){
	  formaS = window.opener.document.forms[0];
	  // Realizar la validaci�n de diagn�sticos repetidos
	  if(formaS.iCveDiagnostico.length > 0){
		  for (i=0; i <  formaS.iCveDiagnostico.length; i++){
			  if(formaS.iCveDiagnostico.options[i].value  == (iCveDiagnostico + "_" + iCveEspecialidad) ){
				  alert("El diagn�stico ya est� asignado.");
				  return;
			  }
		  }
	  }
	  iValor = formaS.iCveDiagnostico.length + 1;
      formaS.iCveDiagnostico.length = iValor;
	  iValor-=1;
      formaS.iCveDiagnostico.options[iValor].text  = cDscDiagnostico;
      formaS.iCveDiagnostico.options[iValor].value  = iCveDiagnostico + "_" + iCveEspecialidad;
  }

