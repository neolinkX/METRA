

 function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070103013P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }


// FUNCIÓN PARA VALIDAR LA INFORMACIÓN DEL PERSONAL
function fValidaTodo(){
    var form = document.forms[0];
   if (form.hdBoton.value == 'Nuevo' || form.hdBoton.value == 'Borrar') {
     alert("Función deshabilitada !")
     return false;
   }
   if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      if (form.iCveUniMed)
        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad Médica:', false);
      if (form.dtFecha)
        cVMsg = cVMsg + fSinValor(form.dtFecha,5,'Fecha:', false);
      if (form.iCveCita)
        cVMsg = cVMsg + fSinValor(form.iCveCita,3,'Cve. Cita:', false);

      if (form.cApPaterno)
        cVMsg = cVMsg + fSinValor(form.cApPaterno,1,'Paterno', false);
      if (form.cApMaterno)
        cVMsg = cVMsg + fSinValor(form.cApMaterno,1,'Materno', false);
      if (form.cNombre)
        cVMsg = cVMsg + fSinValor(form.cNombre,1,'Nombre', false);
/*      if (form.cGenero)
        cVMsg = cVMsg + fSinValor(form.cGenero,1,'Sexo:', false);*/
      if (form.dtNacimiento)
        cVMsg = cVMsg + fSinValor(form.dtNacimiento,5,'Fec. Nac.:', false);
      if (form.cRFC){
         cVMsg = cVMsg + fSinValor(form.cRFC,2,'RFC:', true);
         if ( fValRFC(form.cRFC.value,1) == false)
           cVMsg = cVMsg + "\n - El RFC de la Persona Física es Incorrecto. \n";
      }
      //if (form.cRFC)
      //  cVMsg = cVMsg + fSinValor(form.cRFC,1,'RFC:', false);
      if (form.cCURP)
        cVMsg = cVMsg + fSinValor(form.cCURP,2,'CURP', false);
      if (form.iCvePaisNac)
        cVMsg = cVMsg + fSinValor(form.iCvePaisNac,3,'Pais Nac', false);
      if (form.iCveEstadoNac)
        cVMsg = cVMsg + fSinValor(form.iCveEstadoNac,3,'Edo Nac', false);
      if (form.cExpediente)
        cVMsg = cVMsg + fSinValor(form.cExpediente,2,'Expediente Anterior', false);
      if (form.iCvePersonal)
        cVMsg = cVMsg + fSinValor(form.iCvePersonal,3,'Personal', false);
      if (form.cCalle)
        cVMsg = cVMsg + fSinValor(form.cCalle,0,'Calle', false);
      if (form.cNumExt)
        cVMsg = cVMsg + fSinValor(form.cNumExt,0,'No. Ext', false);
      if (form.cNumInt)
        cVMsg = cVMsg + fSinValor(form.cNumInt,0,'No. Int.', false);
      if (form.cColonia)
        cVMsg = cVMsg + fSinValor(form.cColonia,0,'Colonia', false);
      if (form.iCP)
        cVMsg = cVMsg + fSinValor(form.iCP,3,'C.P.', false);
      if (form.cCiudad)
        cVMsg = cVMsg + fSinValor(form.cCiudad,0,'Ciudad', false);
      if (form.iCvePais)
        cVMsg = cVMsg + fSinValor(form.iCvePais,3,'Pais', false);
      if (form.iCveEstado)
        cVMsg = cVMsg + fSinValor(form.iCveEstado,3,'Edo.', false);
      if (form.iCveMunicipio)
        cVMsg = cVMsg + fSinValor(form.iCveMunicipio,3,'Municipio', false);
      if (form.cTelefono)
        cVMsg = cVMsg + fSinValor(form.cTelefono,1,'Tel.', false);
      if (form.lCambioDir)
        cVMsg = cVMsg + fSinValor(form.lCambioDir,3,'Cambio Dir', false);
      if (form.iCveMdoTrans)
        cVMsg = cVMsg + fSinValor(form.iCveMdoTrans,3,'Mdo Trans', false);
      if (form.iCvePuesto)
        cVMsg = cVMsg + fSinValor(form.iCvePuesto,3,'Puesto', false);
      if (form.cCategoria)
        cVMsg = cVMsg + fSinValor(form.cCategoria,1,'Categoria', false);
      if (form.iCveMotivo)
        cVMsg = cVMsg + fSinValor(form.iCveMotivo,3,'Motivo', false);
      if (form.cObservacion)
        cVMsg = cVMsg + fSinValor(form.cObservacion,1,'Observacion', false);
      if (form.iCveSituacion)
        cVMsg = cVMsg + fSinValor(form.iCveSituacion,3,'Situacion', false);
      if (form.iCveUsuCita)
        cVMsg = cVMsg + fSinValor(form.iCveUsuCita,3,'UsuCita', false);
      if (form.lRealizoExam)
        cVMsg = cVMsg + fSinValor(form.lRealizoExam,3,'Realizo Exam', false);
      if (form.iCveUsuMCIS)
        cVMsg = cVMsg + fSinValor(form.iCveUsuMCIS,3,'usuUMCis', false);
      if (form.lProdNoConf)
        cVMsg = cVMsg + fSinValor(form.lProdNoConf,3,'ProdNoConf', false);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }


// Validación y Generación del Expediente
  function fGenExpediente(lValida){
   var form = document.forms[0];
   if(confirm("¿Desea dar de alta a la persona correspondiente ?")){
      form.lValidado.value = lValida;
      form.hdBoton.value  = "Expediente";
      form.target = "_self";
      form.submit();
   }
  }

 function fPuesto(Personal,UniMed,Modulo){
   var form = document.forms[0];
    form.hdICvePersonal.value = Personal;
    form.hdICveUniMed.value   =  UniMed;
    form.hdICveModulo.value   = Modulo;
    form.hdBoton.value  = "Expediente";
    form.target = 'FRMDatos';
    form.action = 'pg070103010.jsp';
    form.submit();
  }

function fDetalle(Personal){
   var form = document.forms[0];
    form.hdICvePersonal.value = Personal;
    form.hdBoton.value  = "Detalle";
    form.target = 'FRMDatos';
    form.action = 'pg070103041.jsp';
    form.submit();
  }
