  function fOnLoad(){
    cambiaEstado('', 'Hidden', '', '', '');
    fAsignaImg(cRutaImgServer,cRutaImgLocal);
  }
  function fValidaTodo(){
    if (!top.fConfirmaBorrar(document))
      return false;
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
    if (cImagen == 'Nuevo')
      objImg = cForm.Nuevo;
    if (cImagen == 'Guardar')
      objImg = cForm.Guardar;
    if (cImagen == 'Actualizar')
      objImg = cForm.Actualizar;
    if (cImagen == 'Cancelar')
      objImg = cForm.Cancelar;
    if (cImagen == 'Borrar')
      objImg = cForm.Borrar;
    if (cImagen == 'BorrarB')
      objImg = cForm.BorrarB;
    if (cImagen == 'Imprimir')
      objImg = cForm.Imprimir;
    if (cImagen == 'Reporte')
      objImg = cForm.Reporte;
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
  function cambiaEstado(cCanWrite, cEstado, cSave, cDelete, cImprimir){
    document.forms[0].hdCanWrite.value = cCanWrite;
    document.forms[0].hdSaveAction.value = cSave;
    document.forms[0].hdDeleteAction.value = cDelete;

    if (cCanWrite.toLowerCase() == 'no')
      if (cEstado != 'Hidden')
        cEstado = 'Disabled';

    document.forms[0].hdEstado.value = cEstado;
    var cForm = document.forms[0];
    var objBorrar;
    if (cForm.Borrar)
      objBorrar = cForm.Borrar;
    if (cForm.BorrarB)
      objBorrar = cForm.BorrarB;

    cambiaSource(cForm.Imprimir, '4');
    cambiaSource(cForm.Reporte, '4');

    if (cImprimir == 'Imprimir'){
      cambiaSource(cForm.Imprimir, '1');
      cambiaSource(cForm.Reporte, '4');
    }
    if (cImprimir == 'Reporte'){
      cambiaSource(cForm.Imprimir, '4');
      cambiaSource(cForm.Reporte, '1');
    }
    if (cImprimir == 'IR'){
      cambiaSource(cForm.Imprimir, '1');
      cambiaSource(cForm.Reporte, '1');
    }

    if (cEstado == 'UpdateBegin'){
      cambiaSource(cForm.Nuevo, '3');
      cambiaSource(cForm.Guardar, '1');
      cambiaSource(cForm.Actualizar, '3');
      cambiaSource(cForm.Cancelar, '1');
      cambiaSource(objBorrar, '3');
      window.parent.FRMNavPanel.cambiaEstado('Disabled');
    }
    if (cEstado == 'UpdateComplete'){
      cambiaSource(cForm.Nuevo, '1');
      cambiaSource(cForm.Guardar, '3');
      cambiaSource(cForm.Actualizar, '1');
      cambiaSource(cForm.Cancelar, '3');
      cambiaSource(objBorrar, '1');
    }
    if (cEstado == 'AddOnly'){
      cambiaSource(cForm.Nuevo, '1');
      cambiaSource(cForm.Guardar, '3');
      cambiaSource(cForm.Actualizar, '3');
      cambiaSource(cForm.Cancelar, '3');
      cambiaSource(objBorrar, '3');
      window.parent.FRMNavPanel.cambiaEstado('Disabled');
    }
    if (cEstado == 'SaveOnly'){
      cambiaSource(cForm.Nuevo, '4');
      cambiaSource(cForm.Guardar, '1');
      cambiaSource(cForm.Actualizar, '4');
      cambiaSource(cForm.Cancelar, '4');
      cambiaSource(objBorrar, '4');
    }
    if (cEstado == 'UpdateOnly'){
      cambiaSource(cForm.Nuevo, '4');
      cambiaSource(cForm.Guardar, '4');
      cambiaSource(cForm.Actualizar, '1');
      cambiaSource(cForm.Cancelar, '4');
      cambiaSource(objBorrar, '4');
    }
    if (cEstado == 'SaveCancelOnly'){
      cambiaSource(cForm.Nuevo, '4');
      cambiaSource(cForm.Guardar, '1');
      cambiaSource(cForm.Actualizar, '4');
      cambiaSource(cForm.Cancelar, '1');
      cambiaSource(objBorrar, '4');
    }
    if (cEstado == 'Add'){
      cambiaSource(cForm.Nuevo, '1');
      cambiaSource(cForm.Guardar, '4');
      cambiaSource(cForm.Actualizar, '4');
      cambiaSource(cForm.Cancelar, '4');
      cambiaSource(objBorrar, '4');
    }
    if (cEstado == 'Modificar'){
      cambiaSource(cForm.Nuevo, '4');
      cambiaSource(cForm.Guardar, '4');
      cambiaSource(cForm.Actualizar, '1');
      cambiaSource(cForm.Cancelar, '4');
      cambiaSource(objBorrar, '4');
    }
    if (cEstado == 'SaveCancel'){
      cambiaSource(cForm.Nuevo, '4');
      cambiaSource(cForm.Guardar, '1');
      cambiaSource(cForm.Actualizar, '4');
      cambiaSource(cForm.Cancelar, '1');
      cambiaSource(objBorrar, '4');
    }
    if (cEstado == 'AddSaveCancel'){
      cambiaSource(cForm.Nuevo, '1');
      cambiaSource(cForm.Guardar, '1');
      cambiaSource(cForm.Actualizar, '4');
      cambiaSource(cForm.Cancelar, '1');
      cambiaSource(objBorrar, '4');
    }
    if (cEstado == 'DeleteOnly'){
      cambiaSource(cForm.Nuevo, '4');
      cambiaSource(cForm.Guardar, '4');
      cambiaSource(cForm.Actualizar, '4');
      cambiaSource(cForm.Cancelar, '4');
      cambiaSource(objBorrar, '1');
    }
    if (cEstado == 'SaveCancelDeleteOnly'){
      cambiaSource(cForm.Nuevo, '4');
      cambiaSource(cForm.Guardar, '1');
      cambiaSource(cForm.Actualizar, '4');
      cambiaSource(cForm.Cancelar, '1');
      cambiaSource(objBorrar, '1');
    }
    if (cEstado == 'Disabled'){
      cambiaSource(cForm.Nuevo, '3');
      cambiaSource(cForm.Guardar, '3');
      cambiaSource(cForm.Actualizar, '3');
      cambiaSource(cForm.Cancelar, '3');
      cambiaSource(objBorrar, '3');
      if (cImprimir != 'Imprimir' && cImprimir != 'IR')
        cambiaSource(cForm.Imprimir, '3');
      if (cImprimir != 'Reporte' && cImprimir != 'IR')
        cambiaSource(cForm.Reporte, '3');
    }
    if (cEstado == 'Hidden'){
      cambiaSource(cForm.Nuevo, '4');
      cambiaSource(cForm.Guardar, '4');
      cambiaSource(cForm.Actualizar, '4');
      cambiaSource(cForm.Cancelar, '4');
      cambiaSource(objBorrar, '4');
    }
    if (cEstado == ''){
      cambiaSource(cForm.Nuevo, '4');
      cambiaSource(cForm.Guardar, '4');
      cambiaSource(cForm.Actualizar, '4');
      cambiaSource(cForm.Cancelar, '4');
      cambiaSource(objBorrar, '4');
      cambiaSource(cForm.Imprimir, '4');
      cambiaSource(cForm.Reporte, '4');
    }


    if (cEstado == 'SaveCancelUpDateOnly'){
      cambiaSource(cForm.Nuevo, '1');
      cambiaSource(cForm.Guardar, '3');
      cambiaSource(cForm.Actualizar, '1');
      cambiaSource(cForm.Cancelar, '3');
      cambiaSource(objBorrar, '4');
    }
    if (cEstado == 'UpdateCancelBegin'){
      cambiaSource(cForm.Nuevo, '3');
      cambiaSource(cForm.Guardar, '1');
      cambiaSource(cForm.Actualizar, '3');
      cambiaSource(cForm.Cancelar, '1');
      cambiaSource(objBorrar, '4');
      window.parent.FRMNavPanel.cambiaEstado('Disabled');
    }
    if (cEstado == 'AddOnly2'){
      cambiaSource(cForm.Nuevo, '1');
      cambiaSource(cForm.Guardar, '3');
      cambiaSource(cForm.Actualizar, '3');
      cambiaSource(cForm.Cancelar, '3');
      cambiaSource(objBorrar, '4');
      window.parent.FRMNavPanel.cambiaEstado('Disabled');
    }

  }



  function fPag(){
    cp='<html>'  ;
    cp=cp+'<SCRIPT LANGUAGE="JavaScript" SRC="'+cRutaFuncs+'toolbars.js"></SCRIPT>'  ;
    cp=cp+'<SCRIPT LANGUAGE="JavaScript" SRC="'+cRutaFuncs+'valida_nt.js"></SCRIPT>'  ;
    cp=cp+'<SCRIPT language="JavaScript" src="'+cRutaFuncs+'t07_Tooltips.js"></SCRIPT>'  ;
    cp=cp+'<link rel="stylesheet" href="'+Style+'" TYPE="text/css">'  ;
    cp=cp+'<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">'  ;
    cp=cp+'<form method="POST" action="pg0700005.jsp">'  ;
    cp=cp+'  <input type="hidden" name="hdEstado" value="UpdateComplete">'  ;
    cp=cp+'  <input type="hidden" name="hdCanWrite" value="no">'  ;
    cp=cp+'  <input type="hidden" name="hdEstatus" value=" ">'  ;
    cp=cp+'  <input type="hidden" name="hdSaveAction" value="">'  ;
    cp=cp+'  <input type="hidden" name="hdDeleteAction" value="">'  ;
    cp=cp+'  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background="'+cRutaImgServer+'FondoStitulo.jpg">'  ;
    cp=cp+'    <tr>'  ;
    cp=cp+'      <td align="center" width="100%" valign="middle">'  ;
    cp=cp+'        <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" WIDTH="100%">'  ;
    cp=cp+'                                        <TR>'  ;
    cp=cp+'                                          <TD ALIGN="CENTER" VALIGN="TOP">'  ;
    cp=cp+'                                                  <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER">        <tr>'  ;
    cp=cp+'          <td>'  ;
    cp=cp+"  <a href="+'"'+"JavaScript:fSubmite(document.forms[0].Nuevo, 'Nuevo')"+'"'  ;
    cp=cp+"  onMouseOut="+'"'+"if(fMouseOut)fMouseOut(document, 'Nuevo');self.status='';return true;"+'"'  ;
    cp=cp+"  onMouseOver="+'"'+"if(fMouseOver)fMouseOver(document, 'Nuevo');self.status='Nuevo registro';return true;"+'"'+'>  <img name="Nuevo" border="0" src="'+cRutaImgServer+'bNuevo01.gif"></a></td>'  ;
    cp=cp+'          <td>'  ;
    cp=cp+"  <a href="+'"'+"JavaScript:fSubmite(document.forms[0].Guardar, document.forms[0].hdSaveAction.value)"+'"'  ;
    cp=cp+"  onMouseOut="+'"'+"if(fMouseOut)fMouseOut(document, 'Guardar');self.status='';return true;"+'"'  ;
    cp=cp+"  onMouseOver="+'"'+"if(fMouseOver)fMouseOver(document, 'Guardar');self.status='Guardar cambios';return true;"+'"'+'>  <img name="Guardar" border="0" src="'+cRutaImgServer+'bGuardar01.gif"></a></td>'  ;
    cp=cp+'          <td>'  ;
    cp=cp+"  <a href="+'"'+"JavaScript:fSubmite(document.forms[0].Actualizar, 'Modificar')" +'"'  ;
    cp=cp+"  onMouseOut="+'"'+"if(fMouseOut)fMouseOut(document, 'Actualizar');self.status='';return true;"+'"'  ;
    cp=cp+"  onMouseOver="+'"'+"if(fMouseOver)fMouseOver(document, 'Actualizar');self.status='Modificar registro';return true;"+'"'+'>  <img name="Actualizar" border="0" src="'+cRutaImgServer+'bActualizar01.gif"></a></td>'  ;
    cp=cp+'          <td>'  ;
    cp=cp+"  <a href="+'"'+"JavaScript:fSubmite(document.forms[0].Cancelar, 'Cancelar')"+'"'  ;
    cp=cp+"  onMouseOut="+'"'+"if(fMouseOut)fMouseOut(document, 'Cancelar');self.status='';return true;"+'"'  ;
    cp=cp+"  onMouseOver="+'"'+"if(fMouseOver)fMouseOver(document, 'Cancelar');self.status='Cancelar cambios';return true;"+'"'+'>  <img name="Cancelar" border="0" src="'+cRutaImgServer+'bCancelar01.gif"></a></td>'  ;
    cp=cp+'          <td>'  ;
    cp=cp+"  <a href="+'"'+"JavaScript:if (document.forms[0].Borrar) fSubmite(document.forms[0].Borrar, document.forms[0].hdDeleteAction.value); else fSubmite(document.forms[0].BorrarB, document.forms[0].hdDeleteAction.value);"+'"'  ;
    cp=cp+"  onMouseOut="+'"'+"if(fMouseOut)fMouseOut(document, 'BorrarB');self.status='';return true;"+'"'  ;
    cp=cp+"  onMouseOver="+'"'+"if(fMouseOver)fMouseOver(document, 'BorrarB');self.status='Eliminar registro';return true;"+'"'+'>  <img name="BorrarB" border="0" src="'+cRutaImgServer+'bBorrarB01.gif"></a></td>'  ;
    cp=cp+'          <td>'  ;
    cp=cp+"  <a href="+'"'+"JavaScript:fSubmite(document.forms[0].Imprimir, 'Imprimir')"+'"'  ;
    cp=cp+"  onMouseOut="+'"'+"if(fMouseOut)fMouseOut(document, 'Imprimir');self.status='';return true;"+'"'  ;
    cp=cp+"  onMouseOver="+'"'+"if(fMouseOver)fMouseOver(document, 'Imprimir');self.status='Imprimir registro';return true;"+'"'+'>  <img name="Imprimir" border="0" src="'+cRutaImgServer+'bImprimir01.gif"></a></td>'  ;
    cp=cp+'          <td>'  ;
    cp=cp+"  <a href="+'"'+"JavaScript:fSubmite(document.forms[0].Reporte, 'Generar Reporte')"+'"'  ;
    cp=cp+"  onMouseOut="+'"'+"if(fMouseOut)fMouseOut(document, 'Reporte');self.status='';return true;"+'"'  ;
    cp=cp+"  onMouseOver="+'"'+"if(fMouseOver)fMouseOver(document, 'Reporte');self.status='Generar Reporte';return true;"+'"'+'>  <img name="Reporte" border="0" src="'+cRutaImgServer+'bReporte01.gif"></a></td>'  ;
    cp=cp+'        </tr>'  ;
    cp=cp+'</TABLE>'  ;
    cp=cp+'                                          </TD>'  ;
    cp=cp+'          </TR>'  ;
    cp=cp+'                                </TABLE>'  ;
    cp=cp+'      </td>'  ;
    cp=cp+'    </tr>'  ;
    cp=cp+'  </table>'  ;
    cp=cp+'</form>'  ;
    cp=cp+'</body>'  ;
    cp=cp+'<SCRIPT LANGUAGE="JavaScript">'  ;
    cp=cp+'    if (top.fPreCargarImagen){'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bNuevo01.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bNuevo02.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bNuevo03.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bNuevo04.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bGuardar01.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bGuardar02.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bGuardar03.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bGuardar04.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bActualizar01.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bActualizar02.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bActualizar03.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bActualizar04.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bCancelar01.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bCancelar02.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bCancelar03.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bCancelar04.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bBorrarB01.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bBorrarB02.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bBorrarB03.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bBorrarB04.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bImprimir01.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bImprimir02.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bImprimir03.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bImprimir04.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bReporte01.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bReporte02.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bReporte03.gif");'  ;
    cp=cp+'    top.fPreCargarImagen(document,"'+cRutaImgServer+'bReporte04.gif");'  ;
    cp=cp+'    }'  ;
    cp=cp+'</SCRIPT>'  ;
    cp=cp+'</html>';

    document.write(cp);
  }

