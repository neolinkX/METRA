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

function fValidaTodo(){
    var form = document.forms[0]; 
    var Dia;
    var Mes;
    var Anio;


    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Eliminar el Registro?"))
        return false;
    }


    if (form.hdBoton.value != 'Cancelar' && form.hdBoton.value != 'Nuevo') {
      cVMsg = '';
      if (form.iCveUniMed)
        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad M�dica:', true);
      if (form.iCveModulo){
         if (form.iCveModulo.value <= 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el m�dulo. \n";
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
            cVMsg = cVMsg + "\n - La Fecha de la cita no puede ser menor al d�a de hoy. \n";
           }
         }
         cVMsg = cVMsg + fSinValor(form.dtFecha,5,'Fecha:', true);
      }
   }



      if (form.cApPaterno)
        cVMsg = cVMsg + fSinValor(form.cApPaterno,1,'Paterno', true);
      if (form.cApMaterno)
        cVMsg = cVMsg + fSinValor(form.cApMaterno,1,'Materno', true);
      if (form.cNombre)
        cVMsg = cVMsg + fSinValor(form.cNombre,1,'Nombre', true);

    /*if (form.cGenero){

       if (form.cGenero.value != null){ 
          cVMsg = cVMsg + fSinValor(form.(form.cGenero ,1,'Sexo', true);
       }
       else{
          if (form.cGenero[0].checked == false && form.cGenero[1].checked == false )
             cVMsg = cVMsg + "\n - Debe seleccionar el g�nero. \n";
       }
    } */

     if (form.dtNacimiento)
        cVMsg = cVMsg + fSinValor(form.dtNacimiento,5,'Fec. Nac.:', true);


      if (form.cRFC){
         cVMsg = cVMsg + fSinValor(form.cRFC,2,'RFC:', true);
         if ( fValRFC(form.cRFC.value,1) == false)
           cVMsg = cVMsg + "\n - El RFC de la Persona F�sica es Incorrecto. \n";
      }

      if (form.cCURP){
         //cVMsg = cVMsg + fSinValor(form.cCURP,2,'CURP:', false);
         cVMsg = cVMsg + fSinValor(form.cCURP,2,'CURP:', true);
      }
      
         //Valida CURP
         if ( ! form.cCURP.value.match(/[a-zA-Z]{4,4}[0-9]{6}[a-zA-Z]{6,6}[0-9]{2}/) ) {
         cVMsg = cVMsg + "\n - El formato de CURP  es incorrecto. \n";
         }         
			
	

      if (form.iCvePaisNac){
         if (form.iCvePaisNac.value <= 0 || form.iCvePaisNac.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar alg�n pa�s de nacimiento. \n";
         }
      }
      if (form.iCveEstadoNac){
         if (form.iCveEstadoNac.value <= 0 || form.iCveEstadoNac.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar alg�n lugar de nacimiento. \n";
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


      if (form.cCiudad)
        cVMsg = cVMsg + fSinValor(form.cCiudad,2,'Ciudad', false);

      if (form.iCvePais){
         if (form.iCvePais.value <= 0 || form.iCvePais.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar alg�n pa�s. \n";
         }
      }
      if (form.iCveEstado){
         if (form.iCveEstado.value < 0 || form.iCveEstado.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar alg�n estado. \n";
         }
      }
      if (form.iCveMunicipio){
         if (form.iCveMunicipio.value < 0 || form.iCveMunicipio.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar alg�n municipio. \n";
         }
      }

     if (form.cTelefono)
        cVMsg = cVMsg + fSinValor(form.cTelefono,2,'Tel.', false);


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
           cVMsg = cVMsg + " - Debe seleccionar la situaci�n. \n";
         }
      }
      
      
     if (form.iNoLetras){
        cVMsg = cVMsg + fSinValor(form.iNoLetras,0,'Observaciones', true);
                   if ( fComilla(form.iNoLetras.value) == true)
           cVMsg = cVMsg + "\n - El formato de Observaciones es Incorrecto. \n";
        }
              

        if (cVMsg != ''){
          alert("Datos no V�lidos: \n" + cVMsg);
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
           cPagina = '<HTML><title>Cargar Informaci�n</title>'+cStyle+'\n'+
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
           '<tr><td class="ETablaT" align="center">Cargar Informaci�n<tr><td>'+
           '<tr><td class="EEtiquetaC" >' + cArchivo + '\n'+
           '<tr><td>'+
           '<tr><td align="center">' +
           '<input type="File" name="fArchivo" size="50" maxlength="80">\n'+
           '</td></tr>'+
           '<tr><td align="center">\n'+
           '<A HREF="javascript:fSaveFile();">Cargar Informaci�n</A>\n'+
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
           cVMsg = cVMsg + "- Seleccione una Unidad M�dica \n";
       }
       if (form.iCveModulo.value < 1){
           cVMsg = cVMsg + "- Seleccione un M�dulo \n";
       }
	   if(!fValFecha(form.dtFecha.value)){
           cVMsg = cVMsg + "- Verifique la fecha \n";
       }
       if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);		
       }else
         return true;
 }
