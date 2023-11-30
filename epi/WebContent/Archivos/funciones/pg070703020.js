  function fIrAsignacion(cCampoClave1, cCampoClave2, cCampoClave3){
    form = document.forms[0];
    form.iAnio.value = cCampoClave1;
    form.iCveUniMed.value = cCampoClave2;
    form.iCveSolicitud.value = cCampoClave3;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Primero';
    form.target = 'FRMDatos';
    form.action = 'pg070703021.jsp';
    form.submit();
  }

  function fIrModifica(cCampoClave1, cCampoClave2, cCampoClave3){
    form = document.forms[0];
    form.iAnio.value = cCampoClave1;
    form.iCveUniMed.value = cCampoClave2;
    form.iCveSolicitud.value = cCampoClave3;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070703023.jsp';
    form.submit();
  }


    function fIrRevision(cCampoClave1, cCampoClave2, cCampoClave3){
    form = document.forms[0];
    form.iAnio.value = cCampoClave1;
    form.iCveUniMed.value = cCampoClave2;
    form.iCveSolicitud.value = cCampoClave3;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070703022.jsp';
    form.submit();
  }


  function fValidaBuscar(){
    cVMsg = '';
    form = document.forms[0];    

    if (form.dtSolicitudDe)
       cVMsg = cVMsg + fSinValor(form.dtSolicitudDe,5,'Fecha Inicio', true);
    if (form.dtSolicitudA)
       cVMsg = cVMsg + fSinValor(form.dtSolicitudA,5,'Fecha Fin', true);

    if(form.iCveUniMed.value<1)
      cVMsg = cVMsg + "\n - Seleccione una Unidad Médica.";

    dtFechaDe = form.dtSolicitudDe.value;
    dtFechaA = form.dtSolicitudA.value;

    if(fValFecha(dtFechaDe) && fValFecha(dtFechaA)){
      cadenaDe = dtFechaDe.substring(6,11) + dtFechaDe.substring(3,5) + dtFechaDe.substring(0,2);
      cadenaA = dtFechaA.substring(6,11) + dtFechaA.substring(3,5) + dtFechaA.substring(0,2);
      if(cadenaA < cadenaDe)
        cVMsg = cVMsg + "\n - Verifique el rango de Fechas.";
    }

    if(cVMsg!=""){
      alert('Datos no Válidos: \n' + cVMsg);
      return false;
    }
    else
      return true;
  }
