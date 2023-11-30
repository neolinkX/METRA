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

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'BorrarB') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
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
     if (flag == "false")
        cVMsg = cVMsg + "\n - El campo 'Tipo de Respuesta:' es Obligatorio, favor de seleccionar su valor.";
}



      if (form.lEstudio)
        cVMsg = cVMsg + fSinValor(form.lEstudio,0,'Resultado de estudio:', true);
      if (form.lCPersonal)
        cVMsg = cVMsg + fSinValor(form.lCPersonal,0,'Contesta el Personal:', true);
      if (form.lObligatorio)
        cVMsg = cVMsg + fSinValor(form.lObligatorio,0,'Respuesta Obligatoria:', true);
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


function fIrCatalogo(Servicio,Rama,Sintoma){
	var radio;
	
    			for(i=0;i<form.iCveTpoResp.length;i++)
        		if(form.iCveTpoResp[i].checked) 
        		   radio = form.iCveTpoResp[i].value;

    form = document.forms[0];
    form.hdCampoClave.value = Servicio;
    form.hdRowNum.value = Sintoma;
    form.iCveSintoma.value = Sintoma;
    form.iCveServicio.value = Servicio;
    form.iCveRama.value = Rama;
    //form.hdCampoClave.value = form.hdCampoClave.value;
    form.hdTpoResp.value = radio;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101063.jsp';
    form.submit();
  }



//Redirecciona hacia la pantalla de configuracion de reglas de Negocio

  function fIrReglaNegocio(cCampoClave,iCveTpoResp){
	var radio;
	
    			for(i=0;i<form.iCveTpoResp.length;i++)
        		if(form.iCveTpoResp[i].checked) 
        		   radio = form.iCveTpoResp[i].value;
  
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdRowNum.value = cCampoClave;
    form.iCveSintoma.value = cCampoClave;
    form.hdCampoClave.value = form.hdCampoClave.value;
    form.hdTpoResp.value = radio;
    //alert("respuesta = "+radio)
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101100.jsp';
    form.submit();
  }



//Redirecciona hacia la pantalla de configuracion de Alertas 

function fIrConfAlert(cCampoClave,iCveTpoResp){
	var radio;
	
    			for(i=0;i<form.iCveTpoResp.length;i++)
        		if(form.iCveTpoResp[i].checked) 
        		   radio = form.iCveTpoResp[i].value;
  
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdRowNum.value = cCampoClave;
    form.iCveSintoma.value = cCampoClave;
    form.hdCampoClave.value = form.hdCampoClave.value;
    form.hdTpoResp.value = radio;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101103.jsp';
    form.submit();
}



//Redirecciona hacia la pantalla de configuracion de Regalas dependientes  

  function fIrConfDep(cCampoClave,iCveTpoResp){
	var radio;
	
    			for(i=0;i<form.iCveTpoResp.length;i++)
        		if(form.iCveTpoResp[i].checked) 
        		   radio = form.iCveTpoResp[i].value;
  
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdRowNum.value = cCampoClave;
    form.iCveSintoma.value = cCampoClave;
    form.hdCampoClave.value = form.hdCampoClave.value;
    form.hdTpoResp.value = radio;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101107.jsp';
    form.submit();
  } 