  function fOnLoad(){
    if (window.parent.FRMDatos){
      if (!window.parent.FRMDatos.document.forms[0])
        for (var i = 0; i < 50; i++);
      if (window.parent.FRMDatos.document.forms[0]){
        if (!window.parent.FRMDatos.document.forms[0].hdEstadoNav)
          for (var i = 0; i < 50; i++);
        if (window.parent.FRMDatos.document.forms[0].hdEstadoNav)
          cambiaEstado(window.parent.FRMDatos.document.forms[0].hdEstadoNav.value);
      }
    }
    fAsignaImg(cRutaImgServer,cRutaImgLocal);
  }
  function fValidaTodo(){
    return true;
  }
  function fSubmite(objImg, cValor){
    if ((objImg.src.substr(objImg.src.length - 5, 1) == '1') ||
       (objImg.src.substr(objImg.src.length - 5, 1) == '2')){
      if (window.parent.FRMDatos){
        if (window.parent.FRMDatos.fSubmitForm)
          window.parent.FRMDatos.fSubmitForm(cValor);
      }
    }
  }
  function cambiaSource(objImagen, cEstado){
    var cSourceTemp = objImagen.src;
    var cImageTemp  = cSourceTemp.substr(0, cSourceTemp.length - 5);
    var cExtTemp    = cSourceTemp.substr(cSourceTemp.length - 3, cSourceTemp.length);
    cImg = cImageTemp +'/';
    cImg = fEntry(fNumEntries(cImg,"/"),cImg,"/");
    objImagen.src   = fVerRutaImg(cRutaImgServer,cRutaImgLocal) + cImg + cEstado + '.' + cExtTemp;
  }
  function fMouseOver(objDoc, cImagen, cEstatus){
    var cForm = objDoc.forms[0];
    var objImg;
    var cEdoIni;
    if (cImagen == 'Primero')
      objImg = cForm.Primero;
    if (cImagen == 'Anterior')
      objImg = cForm.Anterior;
    if (cImagen == 'Siguiente')
      objImg = cForm.Siguiente;
    if (cImagen == 'Ultimo')
      objImg = cForm.Ultimo;
    cEdoIni = objImg.src.substr(objImg.src.length - 5, 1);
    if (cEdoIni == '1')
      cambiaSource(objImg, '2');
    if (cEdoIni == '2')
      cambiaSource(objImg, '1');
    if (cEdoIni == '3')
      cambiaSource(objImg, '3');
    if (cEdoIni == '4')
      cambiaSource(objImg, '4');
  }
  function fMouseOut(objDoc, cImagen){
    fMouseOver(objDoc, cImagen);
  }
  function cambiaEstado(cEstado){
    document.forms[0].hdEstado.value = cEstado;
    var cForm = document.forms[0];
    if (cEstado == 'FirstRecord'){
      cambiaSource(cForm.Primero, '3');
      cambiaSource(cForm.Anterior, '3');
      cambiaSource(cForm.Siguiente, '1');
      cambiaSource(cForm.Ultimo, '1');
    }
    if (cEstado == 'LastRecord'){
      cambiaSource(cForm.Primero, '1');
      cambiaSource(cForm.Anterior, '1');
      cambiaSource(cForm.Siguiente, '3');
      cambiaSource(cForm.Ultimo, '3');
    }
    if (cEstado == 'Disabled'){
      cambiaSource(cForm.Primero, '3');
      cambiaSource(cForm.Anterior, '3');
      cambiaSource(cForm.Siguiente, '3');
      cambiaSource(cForm.Ultimo, '3');
    }
    if (cEstado == 'Hidden'){
      cambiaSource(cForm.Primero, '4');
      cambiaSource(cForm.Anterior, '4');
      cambiaSource(cForm.Siguiente, '4');
      cambiaSource(cForm.Ultimo, '4');
    }
    if (cEstado == 'Record'){
      cambiaSource(cForm.Primero, '1');
      cambiaSource(cForm.Anterior, '1');
      cambiaSource(cForm.Siguiente, '1');
      cambiaSource(cForm.Ultimo, '1');
    }
  }
