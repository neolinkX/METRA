function fBuscar(){ 
     var form = document.forms[0];
    form.hdBoton.value = "Cancelar";
    form.target = "_self";
    form.submit();
}


  function fSelectedPer(iCvePersonal){
    var form = document.forms[0];
    form.hdiCvePersonal.value = iCvePersonal;
    form.hdBoton.value = "Nuevo";
    form.target = "_self";
    form.submit(); 
  }  
  
  
  function fNuevo(){
    var form = document.forms[0];
    form.hdBoton.value = "Nuevo";
    form.target = "_self";
    form.submit();
 
  }
  

 function Variables(){
    var form = document.forms[0];
    ////DISCAPACIDADES
        /*document.getElementById("IdiCveGrupoD").selectedIndex = 6;
        var s1=document.getElementById('IdiCveSubgrupoD');
        s1.options[0]=new Option("NINGUNA","NINGUNA","NINGUNA");
        var s2=document.getElementById('iCveDiscapacidad');
        s2.options[0]=new Option("NINGUNA","1162","1162");
        s1.selectedIndex=0;
        s2.selectedIndex=0;*/
////RELIGION
        /*document.getElementById("IdiCveGrupo").selectedIndex = 11;
        var r1=document.getElementById('IdiCveSubgrupo');
        r1.options[0]=new Option("NINGUNA","NINGUNA","NINGUNA");
        var r2=document.getElementById('iCveReligiones');
        r2.options[0]=new Option("NINGUNA","925","925");
        r1.selectedIndex=0;
        r2.selectedIndex=0;*/
  }
  


function fValidaTodoOriginal(){
    var form = document.forms[0]; 
    var Dia;
    var Mes;
    var Anio;
    
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }


    if (form.hdBoton.value != 'Cancelar' && form.hdBoton.value != 'Nuevo') {
      cVMsg = '';
      if (form.iCveUniMed)
        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad M\u00E9dica:', true);
      if (form.iCveModulo){
         if (form.iCveModulo.value <= 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el m\u00F3dulo. \n";
         }
      }

      if (form.hdBoton.value == 'Guardar'){
        if (form.dtFecha){  
         if (form.dtFecha.value != null){
         Dia  = form.dtFecha.value.substring(0,2); 
         Mes  = form.dtFecha.value.substring(3,5)-1;
         Anio = form.dtFecha.value.substring(6,10);
         dtFecApi = new Date(Anio,Mes,Dia);

         Dia  = form.hdHoy.value.substring(0,2);
         Mes  = form.hdHoy.value.substring(3,5)-1;
         Anio = form.hdHoy.value.substring(6,10); 
         dtFecHoy = new Date(Anio,Mes,Dia);
          if (dtFecApi < dtFecHoy){
            cVMsg = cVMsg + "\n - La Fecha de la cita no puede ser menor al día de hoy. \n";
           }
         }
         cVMsg = cVMsg + fSinValor(form.dtFecha,5,'Fecha:', true);
      }
   }



   	  if(form.cApPaterno.value.length  == 0){
    		  form.cApPaterno.value = '  ';
    	  }

   	  if(form.cApMaterno.value.length  == 0){
        	  form.cApMaterno.value = '  ';  
    	  }
        
      if (form.cNombre)
        cVMsg = cVMsg + fSinValor(form.cNombre,1,'Nombre', true);

    /*if (form.cGenero){

       if (form.cGenero.value != null){ 
          cVMsg = cVMsg + fSinValor(form.(form.cGenero ,1,'Sexo', true);
       }
       else{
          if (form.cGenero[0].checked == false && form.cGenero[1].checked == false )
             cVMsg = cVMsg + "\n - Debe seleccionar el género. \n";
       }
    } */

     if (form.dtNacimiento){
        //cVMsg = cVMsg + fSinValor(form.dtNacimiento,5,'Fec. Nac.:', true);
    	 cVMsg = cVMsg + fSinValor(form.dtNacimiento,6,'Fec. Nac.:', true);
     }
     

	 //////Comparando fecha de nacimiento ///////////
	     Dia  = form.hdHoy.value.substring(0,2);
	     Mes  = form.hdHoy.value.substring(3,5)-1;
	     Anio = form.hdHoy.value.substring(6,10); 
	     dtFecHoyNoventa = new Date(Anio,Mes,Dia);
	     dtFecHoyQuince = new Date(Anio,Mes,Dia);
		 
	     //Dia  = form.dtNacimiento.value.substring(0,2);
	     Anio  = form.dtNacimiento.value.substring(0,4);
	     //alert(Dia);
	     //Mes  = form.dtNacimiento.value.substring(2,4);
	     Mes  = form.dtNacimiento.value.substring(4,6)-1;
	     //alert(Mes);
	     //Anio = form.dtNacimiento.value.substring(4,9);
	     Dia = form.dtNacimiento.value.substring(6,8);
	     //alert(Anio);
	     dtFecNacimiento = new Date(Anio,Mes,Dia);
	     //alert(dtFecNacimiento);
	     dtFecHoyNoventa.setMonth(-1071);
	     //alert(dtFecHoyNoventa);
	     dtFecHoyQuince.setMonth(-171);
	     //alert(dtFecHoyQuince);
	     
	     if (dtFecNacimiento < dtFecHoyNoventa){
	         cVMsg = cVMsg + "\n - La Fecha de Nacimiento es incorrecta, no puede ser mayor a 90 a\u00F1os. \n";
	     }

	     if (dtFecNacimiento > dtFecHoyQuince){
	         cVMsg = cVMsg + "\n - La Fecha de Nacimiento es incorrecta, no puede ser menor a 15 a\u00F1os. \n";
	     }

     
     if (form.iCvePaisNac){
        if (form.iCvePaisNac.value <= 0 || form.iCvePaisNac.value == ""){
          cVMsg = cVMsg + "\n - Debe seleccionar algún país de nacimiento. \n";
        }
     }
     
     
     if (form.iCvePaisNac.value > 1){
    	 //alert("Es extranjero");
     }else{
	      if (form.cRFC){
	         cVMsg = cVMsg + fSinValor(form.cRFC,2,'RFC:', true);
	         if ( fValRFC(form.cRFC.value,1) == false)
	           cVMsg = cVMsg + "\n - El RFC de la Persona Física es Incorrecto. \n";
	      }
	
	      if (form.cCURP){
	         //cVMsg = cVMsg + fSinValor(form.cCURP,2,'CURP:', false);
	         cVMsg = cVMsg + fSinValor(form.cCURP,2,'CURP:', true);
	      }
	      
	        //Valida CURP
	        // if ( ! form.cCURP.value.match(/[a-zA-Z]{4,4}[0-9]{6}[a-zA-Z]{6,6}[0-9]{2}/) ) {
	        // cVMsg = cVMsg + "\n - El formato de CURP  es incorrecto. \n";
	        // }         
		
	      var curp=form.cCURP.value;//si se trata de obtener la curp de un formulario.
	      //var curp='tucurp';//si obtienes la curp de otra forma.
	       
	      //if(curp.match(/^([a-z]{4})([0-9]{6})([a-z]{6})([0-9]{2})$/i)){//Antig�a validacion vigente hasta el 21 de marzo 2017 AG SA
	        if(curp.match(/^([a-z]{4})([0-9]{6})([a-z]{6})([0-9A-Z]{1})([0-9]{1})$/i)){//Antig�a validacion vigente hasta el 21 de marzo 2017 AG SA  
	      //if(curp.match(/^([A-Z]{1})([AEIOU]{1})([A-Z]{2})([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1})((AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3})( [0-9A-Z]{1})([0-9]{1})$/i)){
	      //alert('curp válida!');
	    	  cVMsg = cVMsg;
	      }else{
	      //alert('curp incorrecta!');
	    	  cVMsg = cVMsg + "\n - La CURP ingresada es incorrecta. \n";
	      }
	      
	      if (form.iCveEstadoNac){
	          if (form.iCveEstadoNac.value <= 0 || form.iCveEstadoNac.value == ""){
	            cVMsg = cVMsg + " - Debe seleccionar algún lugar de nacimiento. \n";
	          }
	       }
     }

      if (form.cCalle){
        cVMsg = cVMsg + fSinValor(form.cCalle,0,'Calle', true);
        if ( fComilla(form.cCalle.value) == true)
           cVMsg = cVMsg + "\n - El formato de la Calle es Incorrecto. \n";
        }
        
      if (form.cNumExt){
      //  cVMsg = cVMsg + fSinValor(form.cNumExt,3,'No. Ext', false);
              if ( fComilla(form.cNumExt.value) == true)
           cVMsg = cVMsg + "\n - El formato del No. Ext. es Incorrecto. \n";
      }
      
      
      if (form.cNumInt){
      //  cVMsg = cVMsg + fSinValor(form.cNumInt,3,'No. Int.', false);
           if ( fComilla(form.cNumInt.value) == true)
           cVMsg = cVMsg + "\n - El formato del No. Int. es Incorrecto. \n";
      }
      
      
      if (form.cColonia){
        cVMsg = cVMsg + fSinValor(form.cColonia,0,'Colonia', true);
                   if ( fComilla(form.cColonia.value) == true)
           cVMsg = cVMsg + "\n - El formato de Colonia es Incorrecto. \n";
        }
        
        
        
      if (form.iCP)
        cVMsg = cVMsg + fSinValor(form.iCP,3,'C.P.', false);



      if (form.cCiudad){
          cVMsg = cVMsg + fSinValor(form.cCiudad,0,'Ciudad o Poblacion', true);
                     if ( fComilla(form.cCiudad.value) == true)
             cVMsg = cVMsg + "\n - El formato de Ciudad o Poblacion es Incorrecto. \n";
          }
      
      if (form.iCvePais){
         if (form.iCvePais.value <= 0 || form.iCvePais.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algún país. \n";
         }
      }
      if (form.iCveEstado){
         if (form.iCveEstado.value < 0 || form.iCveEstado.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar algún estado. \n";
         }
      }
      if (form.iCveMunicipio){
         if (form.iCveMunicipio.value < 0 || form.iCveMunicipio.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar algún municipio. \n";
         }
      }


     if (form.cTelefono){
         cVMsg = cVMsg + fSinValor(form.cTelefono,0,'Tel.', true);
                    if ( fComilla(form.cTelefono.value) == true)
            cVMsg = cVMsg + "\n - El formato de Telefono es Incorrecto. \n";
         }
     

     if (form.iCveMdoTrans){
         if (form.iCveMdoTrans.value <= 0 || form.iCveMdoTrans.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar el modo de transporte. \n";
         }
      }

     if (form.iCvePuesto){
         if (form.iCvePuesto.value <= 0 || form.iCvePuesto.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar el puesto. \n";
         }
      }

     if (form.iCveMotivo){
         if (form.iCveMotivo.value <= 0 || form.iCveMotivo.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar el motivo. \n";
         }
      }

     if (form.iCveSituacion){
         if (form.iCveSituacion.value <= 0 || form.iCveSituacion.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar la situación. \n";
         }
      }
      
      
     if (form.iNoLetras){
        cVMsg = cVMsg + fSinValor(form.iNoLetras,0,'Observaciones', true);
                   if ( fComilla(form.iNoLetras.value) == true)
           cVMsg = cVMsg + "\n - El formato de Observaciones es Incorrecto. \n";
        }
        
//ADICIONALES
     /*
      if (form.iCveVivienda){
         if (form.iCveVivienda.value <= 0 || form.iCveVivienda.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algÃÂºn tipo de Vivienda. \n";
         }
      }
            
      if (form.iCveGpoEtnico){
         if (form.iCveGpoEtnico.value <= 0 || form.iCveGpoEtnico.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algÃÂºn grupo Ãâ°tnico. \n";
         }
      }


      if (form.iCveTpoSangre){
         if (form.iCveTpoSangre.value <= 0 || form.iCveTpoSangre.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algÃÂºn tipo sanguÃÂ­neo. \n";
         }
      }
      
      if (form.iCveNivelSEC){
         if (form.iCveNivelSEC.value <= 0 || form.iCveNivelSEC.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algÃÂºn nivel socioeconÃÂ³mico. \n";
         }
      }        
      if (form.iCveParPol){
         if (form.iCveParPol.value <= 0 || form.iCveParPol.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algÃÂºn partido polÃÂ­tico. \n";
         }
      }        
      if (form.iCveECivil){
         if (form.iCveECivil.value <= 0 || form.iCveECivil.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algÃÂºn estado civil. \n";
         }
      }*/
      
      /*MOTIVOS NO VALIDOS*/
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 2){
		  		cVMsg = cVMsg + "\n - El motivo EXAMEN POSTACCIDENTE solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 26){
		  		cVMsg = cVMsg + "\n - El motivo REVALORACION PSICOLOGICA solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 27){
		  		cVMsg = cVMsg + "\n - El motivo REVALORACION POR EXAMEN TOXICOLÓGICO POSITIVO solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 28){
		  		cVMsg = cVMsg + "\n - El motivo Apoyo al Sector solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 30){
		  		cVMsg = cVMsg + "\n - El motivo SOSPECHA RAZONABLE solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 33){
		  		cVMsg = cVMsg + "\n - El motivo ADICIONAL solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 34){
		  		cVMsg = cVMsg + "\n - El motivo ESTUDIOS ADICIONALES solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 35){
		  		cVMsg = cVMsg + "\n - El motivo ASPIRANTE A ESCUELAS NAÚTICAS solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 37){
		  		cVMsg = cVMsg + "\n - El motivo RECUPERACION solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 39){
		  		cVMsg = cVMsg + "\n - El motivo SOLICITUD DE LA EMPRESA solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 40){
		  		cVMsg = cVMsg + "\n - El motivo PETICION DE LA DIRECCION solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 41){
		  		cVMsg = cVMsg + "\n - El motivo POST ACCIDENTE solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 43){
		  		cVMsg = cVMsg + "\n - El motivo DE PRUEBA PARA EL ADMINISTRADOR solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 44){
		  		cVMsg = cVMsg + "\n - El motivo EPI_ER solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 45){
		  		cVMsg = cVMsg + "\n - El motivo ASPIRANTE A ESCUELAS AERONÁUTICAS solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 46){
		  		cVMsg = cVMsg + "\n - El motivo PROTOCOLOS DE INVESTIGACIÓN solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 47){
		  		cVMsg = cVMsg + "\n - El motivo REVALORACION POR EMO solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	  }
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 49){
		  		cVMsg = cVMsg + "\n - El motivo Evaluación Técnica del desempeño solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	}
		  	if (form.iCveUniMed.value ==107 && form.iCveMotivo.value == 56){
		  		cVMsg = cVMsg + "\n - El motivo APOYO AL SECTOR DGMM solo puede ser aplicado por Unidades Medicas. \n \n  Los motivos disponibles para Terceros Particulares son: EXPEDICION, REVALORACION y REVALIDACION. \n";
		  	}
  	/*MOTIVOS NO VALIDOS*/
             

        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }

 function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}

  function fIrPagina(){
    form = document.forms[0];
    form.hdiCvePersonal.value = '1';
    form.target = 'FRMDatos';
    form.action = 'SEDetPer.jsp';
    form.submit();
  }



  var wExp;

  function fGetFile(){
     form = document.forms[0];
     if(fValidaCarga()){

     if((wExp != null) && (!wExp.closed))
        wExp.focus();
     else{
           cArchivo="CIS.txt";
           cPagina = '<HTML><title>Cargar Información</title>'+cStyle+'\n'+
           '<SCRIPT LANGUAGE="JavaScript">\n'+
           '  function fSaveFile(){\n'+
           '    form = document.forms[0];\n'+
           '    cArchivo="'+cArchivo+'" \n'+
           '    if(form.fArchivo.value.substring(form.fArchivo.value.length - 7) != cArchivo) \n'+
           '      alert("El archivo seleccionado no es el correcto! \\n " + form.fArchivo.value + " <> " + cArchivo);\n'+
           '    else \n'+
           '      form.submit();\n'+
           '  }\n'+
           '  function fClose(){\n'+
           '    form = window.opener;\n'+
           '    form.submit(); window.close();\n'+
           '  }\n'+
           '</SCRIPT>\n'+
           '<body bgcolor="white">'+
           '<form METHOD="POST" ACTION="servUpCIS?iCveUniMed='+form.iCveUniMed.value+'&iCveModulo='+form.iCveModulo.value+'" ENCTYPE="multipart/form-data">'+
           '<table border="1" class="ETablaInfo" width="100%" height="100%" cellspacing="0" cellpadding="3">\n'+
           '<tr><td class="ETablaT" align="center">Cargar Información<tr><td>'+
           '<tr><td class="EEtiquetaC" >' + cArchivo + '\n'+
           '<tr><td>'+
           '<tr><td align="center">' +
           '<input type="File" name="fArchivo" size="50" maxlength="80">\n'+
           '</td></tr>'+
           '<tr><td align="center">\n'+
           '<A HREF="javascript:fSaveFile();">Cargar Información</A>\n'+
           '</td></tr>\n'+
           '</table>\n'+
           '</form></body></HTML>\n';
       wExp = window.open("", "","width=600,height=15,status=no,resizable=no,top=200,left=200");
       wExp.opener = window;
       wExp.moveTo(100,100);
       this.wExp.document.write(cPagina);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();

     }
	 }
  }

 function fValidaCarga(){
       form = document.forms[0];
       cVMsg = '';
       if (form.iCveUniMed.value < 1){
           cVMsg = cVMsg + "- Seleccione una Unidad M\u00E9dica \n";
       }
       if (form.iCveModulo.value < 1){
           cVMsg = cVMsg + "- Seleccione un m\u00F3dulo \n";
       }
	   if(!fValFecha(form.dtFecha.value)){
           cVMsg = cVMsg + "- Verifique la fecha \n";
       }
       if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);		
       }else
         return true;
 }

 
