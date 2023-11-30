function fValidaTodo(){ 
    form = document.forms[0];
    if(form.hdBoton.value = 'Guardar'){
    	cVMsg = '';
    	X= 0;
		      //if(form.cNvaContrasenia.value != form.cReNvaContrasenia.value || form.cNvaContrasenia.value == '' || form.cNvaContrasenia.value.length < 10)
		      if(form.cNvaContrasenia.value != form.cReNvaContrasenia.value)
		        cVMsg = cVMsg + "\n - No coinciden la Nueva Contraseña y su Reescritura.";
		       
		       
		      var RegExPattern = /(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$/;
			  var errorMessage = 'Password Incorrecta.';
			    if ((form.cNvaContrasenia.value.match(RegExPattern)) && (form.cNvaContrasenia.value!='')) {
			        //alert('Password Correcta'); 
			        X = 1;
			    } else {
			        cVMsg = cVMsg + "\n - Debe contener entre 8 y 15 caracteres \n -Por lo menos debe contener un digito y un alfanumérico \n -No puede contener caracteres especiales.";
			        //campo.focus();
			    } 
		       
		       if(form.cNvaContrasenia.value == form.hdPwd.value)
		       		cVMsg = cVMsg + "\n - Su contraseña no puede ser igual que la anterior..";
		    
		        
		      if (cVMsg != ''){
				      	alert("Datos no Válidos: \n" + cVMsg);
				        return false;
		      }else{
			        if(!confirm("Desea usted hacer el cambio de contraseña?")){
			          return false;
		        	}
		      }
    }
    return true;
  }
   
  
    function fOnLoad2(){
    if(window.parent.parent.FRMTitulo){
       window.parent.parent.FRMTitulo.fFiltros(false);
       window.parent.parent.FRMTitulo.fSinBarras(true);
       window.parent.parent.FRMTitulo.asignaAyuda(cAyuda);
       window.parent.parent.FRMTitulo.asignaBoton('Bloqueado');
    }
    if(top.FRMTitulo)top.FRMTitulo.submite();
    fAsignaImg(cRutaImgServer,cRutaImgLocal);
  }

/*
    function fIrCatalogo(){
        form = document.forms[0];
	    form.target = 'FRMDatos';
	    stBoton = "Bloqueado";
	    //window.parent.parent.FRMTitulo.AsignaBoton(stBoton);
	    //window.parent.parent.FRMTitulo.fFiltros(false);
	    top.FRMTitulo.submite()
		if(top.FRMTitulo)top.FRMTitulo.submite();
	    fAsignaImg(cRutaImgServer,cRutaImgLocal);
	   // form.action = 'pg0720000.jsp';
	   // form.submit();
  }
  */
    function fSalir2(form, window, cPagina){
    alert('Favor de registrarse nuevamente...');
    if(window.parent.parent.FRMTitulo){
       window.parent.parent.FRMTitulo.fSalir();
    }else{
       form.action = 'pg' + cPagina + '00000.jsp';
       form.target = '_top';
       form.submit();
    }
  }
  
    