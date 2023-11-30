function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070101061P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070101061P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
    }
  }




  function fValidaTodo(){
    var form = document.forms[0];
    var flag = "false";
    
   
   if(form.hdBoton.value == 'Guardar' && (form.hdTpoResp.value == '8' || form.hdTpoResp.value == '13'))
      GetSelectedItem();
      
   if(form.hdBoton.value == 'Guardar' && form.hdTpoResp.value == '1') 
      GetLogica();
      

   if(form.hdBoton.value == 'Guardar' && form.hdTpoResp.value == '9')
      GetSelectedItem();      
      
   if(form.hdBoton.value == 'Nuevo' && (form.hdTpoResp.value == '8'  || form.hdTpoResp.value == '13'))
      GetSelectedItem();
      
	if(form.hdBoton.value == 'Nuevo' && form.hdTpoResp.value == '9')
      GetSelectedItem();   
      
   if(form.hdBoton.value == 'Nuevo' && form.hdTpoResp.value == '1') {
      	//alert(form.hdBoton.value);
      	GetNuevoLog();
      }
      
   if(form.hdBoton.value == 'Modificar' && (form.hdTpoResp.value == '8'  || form.hdTpoResp.value == '13')) {
      	//alert("Habilita función");
      	return true;
      }      
      
      

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
      
    //if (form.hdBoton.value == 'BorrarB' || form.hdBoton.value == 'Modificar') {
    if (form.hdBoton.value == 'BorrarB') {
      //if(!confirm("¿Está Seguro de Desactivar el Registro?"))
      alert("Esta función se encuentra deshabilitada.");
        return false;
    }
    
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cDscBreve)
        cVMsg = cVMsg + fSinValor(form.cDscBreve,0,'Descripción Breve:', true);
      if (form.cPregunta)
        cVMsg = cVMsg + fSinValor(form.cPregunta,0,'Pregunta:', true);

if (form.cGenero && form.hdBoton.value != 'Nuevo'){
     for(i=0;i < form.elements.length;i++){
       if (form.elements[i].name.substring(0,7) == "cGenero"){
          if (form.elements[i].checked){
             flag = "true";
          }
       }
		
    }
     if (flag == "false")
        cVMsg = cVMsg + "\n - El campo 'Genéro:' es Obligatorio, favor de seleccionar su valor.";
}


flag = "false";


if (form.iCveTpoResp && form.hdBoton.value != 'Nuevo'){
     for(i=0;i < form.elements.length;i++){
       if (form.elements[i].name.substring(0,11) == "iCveTpoResp"){
          if (form.elements[i].checked){
             flag = "true";
          }
       }

    }
     //if (flag == "false")
        //cVMsg = cVMsg + "\n - El campo 'Tipo de Respuesta:' es Obligatorio, favor de seleccionar su valor.";
}



      if (form.lEstudio)
        cVMsg = cVMsg + fSinValor(form.lEstudio,0,'Resultado de estudio:', true);
      if (form.lCPersonal)
        cVMsg = cVMsg + fSinValor(form.lCPersonal,0,'Contesta el Personal:', true);
 //     if (form.lObligatorio)
   //     cVMsg = cVMsg + fSinValor(form.lObligatorio,0,'Respuesta Obligatoria:', true);
      if (form.lEvalAuto)
        cVMsg = cVMsg + fSinValor(form.lEvalAuto,0,'Eval. Automatica:', true);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
    return true;
  }

function fillNext(){
       form = document.forms[0];
       form.target =  "FRMDatos";
       form.submit();
}
function fillBusca(){
       form = document.forms[0];
       form.hdBoton.value = "Buscar";
       form.target =  "FRMDatos";
       form.submit();
}


  function fIrCatalogo(cCampoClave){
      alert("Boton: " + form.hdBoton.value + " TipoResp: " + form.hdTpoResp.value + " Catalogo");
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdRowNum.value = cCampoClave;
    form.iCveSintoma.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101063.jsp';
    form.submit();
  }
  
  
  function add() {
      	if(form.hdTpoResp.value != '9') {
				var newitem=prompt("Añadir nuevos elementos:","");
				len = document.forms[0].list.length;
				i = 0;
				j = 0;
				newitem = fReemplazar(fTrim(newitem.toUpperCase()));
					for (i = 0; i < len; i++) {
						if (document.forms[0].list[i].selected) {
							if(newitem == document.forms[0].list.options[i].text)
							    j++;
						} 
					}
				
					if(j == 0){
							//document.forms[0].list[document.forms[0].list.length] = new Option(newitem, document.forms[0].list.length);
							//campoFil.value = fReemplazar(fTrim(campoFil.value.toUpperCase()));
							document.forms[0].list[document.forms[0].list.length] = new Option(newitem, document.forms[0].list.length);
					}else{
						alert("La respuesta ya existe, favor de agregar otra distinta");
					}
			
				//alert(document.forms[0].list.options[len].text)
				//document.forms[0].list.options[document.forms[0].list.length].value = new Option(newitem, document.forms[0].list.length);
			    //alert("len = "+len);
				//document.forms[0].list[len].value = newitem;	
				//if(newitem == document.forms[0].list[document.forms[0].list.length].value)
				//alert(ya existe);
		}else{
				len = document.forms[0].list.length;
				if(len < 2){
					var newitem=prompt("Añadir nuevos elementos:","");
					i = 0;
					j = 0;
					extensiones_permitidas = new Array(".gif", ".jpg", ".bmp", ".png"); 
   					mierror = ""; 
   					extension = (newitem.substring(newitem.lastIndexOf("."))).toLowerCase(); 
					mierror = "Comprueba la extensión de los archivos a subir. \nSólo se pueden subir archivos con extensiones: " + extensiones_permitidas.join(); 
					permitida = false; 
				      for (var i = 0; i < extensiones_permitidas.length; i++) { 
					         if (extensiones_permitidas[i] == extension) { 
							         permitida = true; 
							         break; 
					         } 
				      } 
					
					if (permitida) {
						//newitem = fReemplazar(fTrim(newitem.toUpperCase()));
							for (i = 0; i < len; i++) {
								if (document.forms[0].list[i].selected) {
									if(newitem == document.forms[0].list.options[i].text)
									    j++;
								} 
							}
						
							if(j == 0){
									document.forms[0].list[document.forms[0].list.length] = new Option(newitem, document.forms[0].list.length);
							}else{
								alert("La respuesta ya existe, favor de agregar otra distinta");
							}
					}else{//permitida
						alert (mierror); 
					}
			    }
		}		
   }


  function listbox_selectall(listID, isSelect) {
  		var count;
		var listbox = document.getElementById(listID);
		for(count = 0; count < listbox.options.length; count++) {
			listbox.options[count].selected = isSelect;
		}
   }
   
   
  function listbox_selectall2(listID, isSelect) {
  		var count;
		var listbox = document.getElementById(listID);
		if(listbox.options.length > 1){
				alert("Solo puedes ingresar 2 datos");
		}else{
				for(count = 0; count < listbox.options.length; count++) {
					listbox.options[count].selected = isSelect;
				}
		}
   }   

  function GetSelectedItem() { 
      //alert("Boton: " + form.hdBoton.value + " TipoResp: " + form.hdTpoResp.value);
  		listbox_selectall('list', true);
  		form = document.forms[0];
		len = document.forms[0].list.length;
		i = 0;
		chosen = "" ;
		for (i = 0; i < len; i++) {
			if (document.forms[0].list[i].selected) {
				//chosen = chosen + document.forms[0].list[i].value + "\n";
				chosen = chosen + document.forms[0].list.options[i].text + "/";
			} 
		}
        form.hdChosen.value = chosen;
    	form.hdCampoClave.value = form.hdCampoClave.value;
    	form.iCveRama.value = form.hdRama.value;
    	form.iCveServicio.value = form.hdServicio.value;
    	//form.hdRowNum.value = form.hdSintoma.value;
    	form.hdSintoma.value = form.hdSintoma.value;
    	form.hdServicio2.value = form.hdServicio2.value;
    	form.hdTpoResp.value = form.hdTpoResp.value;
    	form.hdRama.value = form.hdRama.value;
    	form.lLogico.value = '0';
    	form.iCveTpoResp.value = form.hdTpoResp.value;
    	//form.hdBoton.value = 'Actual';
    	form.target = 'FRMDatos';
    	form.action = 'pg070101063.jsp';
    	form.submit();
		//return chosen;
       //return false;
}


 function GetLogica() { 
     //alert("Boton: " + form.hdBoton.value + " TipoResp: " + form.hdTpoResp.value + " getLogica");
  		form = document.forms[0];
  		var radio;
  		var check;
  		
  		for(i=0;i<form.lLogico2.length;i++)
        		if(form.lLogico2[i].checked) 
        		   radio = form.lLogico2[i].value;
  		
  /*		if(form.lEspecifica.checked)  		 
        			check = "1/";
        else
       				check = "0/";
        */
    	form.hdCampoClave.value = form.hdCampoClave.value;
    	form.iCveRama.value = form.hdRama.value;
    	form.iCveServicio.value = form.hdServicio.value;
    	form.hdSintoma.value = form.hdSintoma.value;
    	form.iCveSintoma.value = form.iCveSintoma.value;
    	form.iCveSintoma.value = form.iCveSintoma.value;
    	form.hdServicio2.value = form.hdServicio2.value;
    	form.hdTpoResp.value = form.hdTpoResp.value;
    	form.hdRama.value = form.hdRama.value;
    	form.lLogico.value = radio;
    	form.iCveTpoResp.value = form.hdTpoResp.value;
    	form.hdChosen.value = check;
    	form.target = 'FRMDatos';
    	form.action = 'pg070101063.jsp';
    	form.submit();
} 



 function GetNuevoLog() {
     //alert("Boton: " + form.hdBoton.value + " TipoResp: " + form.hdTpoResp.value + "NuevoLog");
  		form = document.forms[0];
  		var radio;
  		var check;
    /*		
  		for(i=0;i<form.lLogico2.length;i++)
        		if(form.lLogico2[i].checked) 
        		   radio = form.lLogico2[i].value;
  		
		if(form.lEspecifica.checked)  		 
        			check = "1/";
        else
       				check = "0/";
    */
        //alert("Nuevo");
    	form.hdCampoClave.value = form.hdCampoClave.value;
    	form.iCveRama.value = form.larama.value;
    	form.iCveServicio.value = form.hdServicio.value;
    	form.hdSintoma.value = form.hdSintoma.value;
    	form.iCveSintoma.value = form.hdSintoma.value;
    	form.hdServicio2.value = form.hdServicio2.value;
    	form.hdTpoResp.value = form.hdTpoResp.value;
    	form.hdRama.value = form.larama.value;
    	//form.lLogico.value = radio;
    	//form.hdChosen.value = "logica";
    	form.hdTpoResp.value = form.hdTpoResp.value;
    	form.iCveTpoResp.value = form.hdTpoResp.value;
    	form.hdChosen.value = "Logica/";
    	form.target = 'FRMDatos';
    	form.action = 'pg070101063.jsp';
    	form.submit();
}

function actualizarCampos() {
    //alert("Entro a la funcion de actualiza");
    form = document.forms[0];
     chosen = "";
    /*if (form.hdTpoResp.value == "8") {
        len = form.hdSizeDatos.value;
        i = 0;
        chosen = "";
        var borrar = 0;
        for (i = 0; i < len; i++) {
            if(form.cValRef+i.value == ""){
                borrar = 1;
            }
        }
        if (borrar == 1) {
            if(!confirm("Esta seguro de borrar el(los) campo(s)?"))
                return false;

        }
    }*/
    
    
    form.hdChosen.value = chosen;
    form.hdCampoClave.value = form.hdCampoClave.value;
    form.iCveRama.value = form.hdRama.value;
    form.iCveServicio.value = form.hdServicio.value;
    //form.hdRowNum.value = form.hdSintoma.value;
    form.hdSintoma.value = form.hdSintoma.value;
    form.hdServicio2.value = form.hdServicio2.value;
    form.hdTpoResp.value = form.hdTpoResp.value;
    form.hdRama.value = form.hdRama.value;
    form.lLogico.value = '0';
    form.iCveTpoResp.value = form.hdTpoResp.value;
    //form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101063.jsp';
    form.submit();
}