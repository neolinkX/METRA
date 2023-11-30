function fillNext(){
       form = document.forms[0];
       form.target =  "FRMDatos"; 
       form.submit();
}

  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070201010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name+ "&hdAccion=" + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070201010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name+ "&hdAccion=" + document.forms[0].action;
    }
  }
 

function fSelectedPer(iCvePersonal,iCveExpediente,iNumExamen){
    form = document.forms[0];
    form.target =  "FRMDatos";
    form.iCvePersonal.value = iCvePersonal;
    form.iNumExamen.value = iNumExamen;
    form.iCveExpediente.value = iCveExpediente;
    form.hdType.value = "P";
    form.cFlag.value = "T";
    form.submit();
}

function fEmpSelected(iCveEmpresa, cNombreRS, cApPaterno, cApMaterno, cRFC, cCURP, cTpoPersona){
    form = document.forms[0];
    form.target =  "FRMDatos";
    form.iCveNumEmpresa.value = iCveEmpresa;
    form.cNombreRS.value = cNombreRS;
    form.hdiCveEmpresa.value = iCveEmpresa;
    //alert(form.iCveEmpresa.value+" "+form.cNombreRS.value);
}

function fSelExp(){
    form = document.forms[0];
    form.target =  "FRMDatos";
    cVMsg = '';
    if (form.iCveUniMed.value == -1){
        cVMsg = cVMsg + "- Seleccione una Unidad Medica \n";
    }
    if (form.iCveProceso.value == 0){
       cVMsg = cVMsg + "- Seleccione un Proceso \n";
   }
    if (form.iCveModulo.value == 0){
       cVMsg = cVMsg + "- Seleccione un Modulo \n";
    }
    if (form.iCveSubModulo.value == 0){
        cVMsg = cVMsg + "- Seleccione un SubModulo \n";
     }
    if (form.iCveMdoTrans.value == 0){
       cVMsg = cVMsg + "- Seleccione un Modo de Transporte \n";
    }
    if (cVMsg != ''){
      alert("Datos no Validos: \n" + cVMsg);
    }else{
      fSelPer2();
    }
}

  function fValidaTodo(){
    var form = document.forms[0];
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
       if (form.lSinLicencia.value == 1){
            if (form.cLicencia.value == null || form.cLicencia.value.length == 0){
               cVMsg = cVMsg + "- Ingrese un Número de Licencia \n";
            }
            /*if (form.lLicVencida.value == null || form.lLicVencida.value.length == 0){
               cVMsg = cVMsg + "- Indique si la Licencia Se encuentra Vencida \n";
            }*/
       }


       

       if (form.iCveCapturaDelExamen.value == 0){
           cVMsg = cVMsg + "- Ingrese la Captura del Examen \n";
       }
       if (form.iCveUsuAplicoEMO.value == -1){
           cVMsg = cVMsg + "- Ingrese el Médico Dictaminador \n";
       }
       if (form.iCveCategoria.value == 0){
           cVMsg = cVMsg + "- Ingrese la Clave de la Categoria \n";
       }
       if (form.iCveMotivo.value == 0){
           cVMsg = cVMsg + "- Ingrese la Clave del Motivo \n";
       }
       if (form.iCveMomentoAP.value == 0){
           cVMsg = cVMsg + "- Ingrese la Clave del Momento \n";
       }
       if (form.iCveNumEmpresa.value == "" || form.iCveNumEmpresa.value.length == 0){
           cVMsg = cVMsg + "- Seleccione una Empresa \n";
       }

       /*if(form.lLicVencida[0].checked == false){ 
           if( form.lLicVencida[1].checked == false)
             cVMsg = cVMsg + "- Seleccione si la licencia esta vencida \n";

       }*/
     
       if (form.cMatricula.value == "" || form.cMatricula.value.length == 0){
           cVMsg = cVMsg + "- Ingrese La Matricula del Vehiculo \n";
       }
       if (form.cOrigen.value == "" || form.cOrigen.value.length == 0){
           cVMsg = cVMsg + "- Ingrese El Origen del Transporte \n";
       }
       if (form.cDestino.value == "" || form.cDestino.value.length == 0){
           cVMsg = cVMsg + "- Ingrese El Destino del Transporte \n";
       }
       if (form.iCvePaisOr.value == -1){
           cVMsg = cVMsg + "- Seleccione el Pais de Origen \n";
       }
       if (form.iCveEdoOr.value == 0){
           cVMsg = cVMsg + "- Seleccione el Estado de Origen \n";
       }
       if (form.iCvePaisDes.value == -1){
           cVMsg = cVMsg + "- Seleccione el Pais de Destino \n";
       }
       if (form.iCveEdoDes.value == 0){
           cVMsg = cVMsg + "- Seleccione el Estado de Destino \n";
       }
       if (form.iCveGrupo.value == 0){
           cVMsg = cVMsg + "- Seleccione el Grupo \n";
       }
       if (form.iCvePuesto.value == 0){
           cVMsg = cVMsg + "- Seleccione el Puesto \n";
       }
       
       if (form.iCveFolio){
           //cVMsg = cVMsg + fSinValor(form.iCveFolio,3,'Folio', false);
           var str =form.iCveFolio.value+"";
           var largo = form.iCveFolio.value.length;
           //alert("1---"+str.substring(0,1));
           //alert("2---"+str.substring(1, largo));
           //alert(largo+"");
           
           if(largo < 7){
        	   cVMsg = cVMsg + "- Folio incorrecto, debe contener 7 caracteres \n";
           }else{
	           var e = str.substring(0,1)+"";
	           var f = str.substring(1,largo)+"";
	           if(/^[a-z][a-z]*/.test(e.toLocaleLowerCase())==false){
	        	   cVMsg = cVMsg + "- Folio incorrecto, el folio debe iniciar con una letra \n";
	            }
	           if(isNaN(f)){
	        	   cVMsg = cVMsg + "- Folio incorrecto, solo el primer carácter debe ser letra y los dem\u00E1s n\u00Fameros \n";
	            }
           }
       }
       

       hora=form.horas.value;
       if (hora=='') {return}
       if (hora.length>5) {
    	   		cVMsg = cVMsg + "- Introdujo una cadena mayor a 5 caracteres para la hora \n";
    	   }
       if (hora.length!=5) {
    	   		cVMsg = cVMsg + "- Introducir HH:MM \n";
    	   }
       a=hora.charAt(0) //<=2
       b=hora.charAt(1) //<4
       c=hora.charAt(2) //:
       d=hora.charAt(3) //<=5
       e=hora.charAt(5) //:
       f=hora.charAt(6) //<=5
       if ((a==2 && b>3) || (a>2)) {
    	   		cVMsg = cVMsg + "- El valor que introdujo en la Hora no corresponde, introduzca un digito entre 00 y 23 \n";
    	   }
       if (d>5) {
    	   		cVMsg = cVMsg + "- El valor que introdujo en los minutos no corresponde, introduzca un digito entre 00 y 59 \n";
    	   }
       /*if (f>5) {
    	   		cVMsg = cVMsg + "- El valor que introdujo en los segundos no corresponde \n";
    	   }*/
       /*if (c!=':' || e!=':') {*/
       if (c!=':') {
    	   		cVMsg = cVMsg + "- Introduzca el caracter ':' para separar la hora y los minutos \n";
    	   }
       
   
          
       
       if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
       }else{
          if (form.hdBoton.value == 'Guardar'){
             
             if (confirm("¿Esta usted seguro que desea guardar la informacion?")){                
                form.lIniciado.value = "1";
                form.lAplicado.value = "1";
                form.lConcluido.value = "0";
                form.lDictamen.value = "0";
                return true;
             }
          }
       }
       
    }else{
       return true;         
    }
   }
function fChgArea(fArea,Cont){
  form = document.forms[0];
      cText = fArea.value;
      if(cText.length > 249)
        fArea.value = cText = cText.substring(0,249);

      form.iNoLetras.value = 249 - cText.length;

}
function fChgAreab(fArea,Cont){
  form = document.forms[0];
      cText = fArea.value;
      if(cText.length > 249)
        fArea.value = cText = cText.substring(0,249);

      form.iNoLetrasb.value = 249 - cText.length;

}
