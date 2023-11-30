    function fValidaTodo(){
    var form = document.forms[0];
    var flag = "false";
    
    if(form.hdBoton.value == "Nuevo"){
    	if(form.hdnuevo.value == "nuevo"){
    		alert("Solo puede agregar una alerta por síntoma!");
    		 return false;
    	}else{
    		fIrCatalogo2(0,0,0,0,0,0);
    	}
    }
    
    if(form.hdBoton.value == "BorrarB") {
        if(confirm("¿Esta seguro(a) de borrar la alerta registrada para este síntoma?")) {
            fillBorrarReglas();
        } else {
            return false;
        }
        
    }
    
    if(form.hdBoton.value == "Modificar") {
	alert('No disponible');
        return false;
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
  
  function fillBorrarReglas(){
    form = document.forms[0];
    form.hdBoton.value = "BorrarB";
    form.target =  "FRMDatos";
    form.submit();
  }

/*
  function fIrCatalogo(cCampoClave){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdRowNum.value = cCampoClave;
    form.iCveSintoma.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101061.jsp';
    form.submit();
  }
*/

  function fReordenar(cCampoClave1, cCampoClave2){
    form = document.forms[0];
    form.iCveServicio.value = cCampoClave1;
    form.iCveRama.value = cCampoClave2;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    //form.action = 'pg070101062.jsp';
    form.action = 'pg070101103.jsp';
    form.submit();
  }
  
  
  
//Redirecciona hacia la pantalla de configuracion de las alertas

  function fIrCatalogo(servicio,rama,sintoma,regla,trans,cat){
	/*var radio;
	
    			for(i=0;i<form.iCveTpoResp.length;i++)
        		if(form.iCveTpoResp[i].checked) 
        		   radio = form.iCveTpoResp[i].value;
*/    
    form = document.forms[0];
    form.iCveServicio.value = form.iCveServicio.value;
    form.iCveRama.value = form.iCveRama.value;
    form.iCveSintoma.value = form.iCveSintoma.value;
    form.iCveRegla.value = regla;
    form.hdCampoClave.value = form.hdCampoClave.value;
    form.iCveMdoTrans2.value = trans+'';
    form.iCveCategoria2.value = cat+'';

    form.hdTpoResp.value = form.hdTpoResp.value;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101104.jsp';
    form.submit();
  }


  function fIrCatalogo2(servicio,rama,sintoma,regla,trans,cat){
	/*var radio;
	
    			for(i=0;i<form.iCveTpoResp.length;i++)
        		if(form.iCveTpoResp[i].checked) 
        		   radio = form.iCveTpoResp[i].value;
*/    
    form = document.forms[0];
    form.iCveServicio.value = form.iCveServicio.value;
    form.iCveRama.value = form.iCveRama.value;
    form.iCveSintoma.value = form.iCveSintoma.value;
    form.iCveRegla.value = regla;
    form.hdCampoClave.value = form.hdCampoClave.value;
    form.iCveMdoTrans2.value = trans+'';
    form.iCveCategoria2.value = cat+'';

    form.hdTpoResp.value = form.hdTpoResp.value;
    form.hdBoton.value = 'Nuevo';
    form.target = 'FRMDatos';
    form.action = 'pg070101104.jsp';
    form.submit();
  }
  