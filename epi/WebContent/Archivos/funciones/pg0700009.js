  function fOnLoad(){
    if(window.parent.parent.FRMTitulo){
       window.parent.parent.FRMTitulo.fFiltros(false);
       window.parent.parent.FRMTitulo.fSinBarras(true);
       window.parent.parent.FRMTitulo.asignaAyuda(cAyuda);
    }
    if(top.FRMTitulo)top.FRMTitulo.submite();
    fAsignaImg(cRutaImgServer,cRutaImgLocal);
  }


   function fExpira(expira){
    alert("Su contraseña expira en "+expira+" días, favor de cambiarla.");
  } 