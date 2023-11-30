  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      alert("Esta opción no está disponible. No se permite borrar un perfil.");
        return false;
    }
    if (form.hdBoton.value != 'Cancelar' && form.hdBoton.value != 'Modificar') {
      cVMsg = '';
      if (form.iCvePerfil)
        cVMsg = cVMsg + fSinValor(form.iCvePerfil,3,'Perfil', false);
      if (form.iCveMdoTrans)
        cVMsg = cVMsg + fSinValor(form.iCveMdoTrans,3,'Modo de transporte', true);
      if (form.iCveGrupo)
        cVMsg = cVMsg + fSinValor(form.iCveGrupo,3,'Grupo', true);
      if (form.dtInicio)
        cVMsg = cVMsg + fSinValor(form.dtInicio,5,'Inicio de Vigencia', true);
      if (form.dtFin)
        cVMsg = cVMsg + fSinValor(form.dtFin,5,'Fin de Vigencia', true);
      if (form.lVigente)
        cVMsg = cVMsg + fSinValor(form.lVigente,3,'Vigente', false);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }

  function fIr(cCampoClave, cPropiedad, cPagina){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
	form.hdCOrdenar.value = '';   // para no arrastrar el parámetro
	form.hdCCondicion.value = '';
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }

function fCambiaGpo(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}
