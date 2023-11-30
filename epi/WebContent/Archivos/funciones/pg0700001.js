
  function fOnLoad(){
    fAsignaImg(cRutaImgServer,cRutaImgLocal);
  }

  function fValidaTodo(){
  }

  function submite(){
    if (document.forms[0].hdSubmitido.value == ""){
      document.forms[0].hdSubmitido.value = "Si";
      document.forms[0].submit();
    }
  }

  var wExp;
  var cAcceso="";

  function fMenu(){
    form = document.forms[0];
    if((wExp != null)
    && (!wExp.closed))
      wExp.focus();
    else
      cAcceso = "";
    if((cAcceso == "")) {
      wExp = open("pg"+cPagina+"00003.jsp?hdMenu=''&hdSubMenu=''",'','dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=370,height=390,screenX=800,screenY=600');
      wExp.creator = self;
      wExp.moveTo(0, 180);
      cAcceso = 'si';
    }
  }

  function fCargarPagOpener(cValor, cTitulo){
      if (window.parent.FRMCuerpo.FRMDatos){
           wpff = window.parent.FRMCuerpo.FRMDatos.document.forms[0];
           wpff.hdBoton.value = 'Primero';
           wpff.action  = cValor;

           if(wpff.hdCCondicion){
              wpff.hdCCondicion.value = '';
              wpff.hdCOrdenar.value = '';
           }
           if(wpff.hdLCondicion){
              wpff.hdLCondicion.value = '';
              wpff.hdLOrdenar.value = '';
           }


           wpff.target  = 'FRMDatos';
           wpff.submit();
           if(cValor.indexOf('?') != -1){
              cPagPanel = cValor.substring(cValor.indexOf('?') + 1);
           }else{
              cPagPanel = 'pg'+cPagina+'00007.jsp';
           }
           cPagPanel = cPagPanel + '?hdTitulo=' + cTitulo;
           wpfo = window.parent.FRMCuerpo.FRMOtroPanel.document.forms[0];
           wpfo.action  = cPagPanel;
           wpfo.target  = 'FRMOtroPanel';
           wpfo.submit();
           top.window.focus();
      }
  }

  function fFiltros(lSin){
    if (window.parent.FRMCuerpo.FRMFiltro.FRMBusqueda){
       wpf = window.parent.FRMCuerpo;
       if(!lSin){
         if(wpf.FRMFiltro.FRMBusqueda.fOculto)
           wpf.FRMFiltro.FRMBusqueda.fOculto(true);
         wpf.document.body.rows="35,0,*";
       }else{
         if(wpf.FRMFiltro.FRMBusqueda.fOculto)
           wpf.FRMFiltro.FRMBusqueda.fOculto(false);
         wpf.document.body.rows="35,34,*";
       }
    }
  }

  function fSinBarras(lSin){
    if (window.parent.FRMCuerpo.FRMFiltro.FRMBusqueda){
       wpf = window.parent.FRMCuerpo;
       if(lSin){
         wpf.document.body.rows="0,0,*";
       }else{
         wpf.document.body.rows="35,34,*";
       }
    }
  }

  function fIra(lIr,cDscBotones,cPaginas){
    if (window.parent.FRMCuerpo.FRMFiltro.FRMBotones){
       wpfb = window.parent.FRMCuerpo.FRMFiltro;
       if(lIr){
         wpfb.document.body.cols="70%,30%";
         if(wpfb.FRMBotones.fOculto)
           wpfb.FRMBotones.fOculto(true);
         wpfb.FRMBotones.fAccesos(cDscBotones,cPaginas);
       }else{
         wpfb.document.body.cols="100%,0%";
         if(wpfb.FRMBotones.fOculto)
           wpfb.FRMBotones.fOculto(true);
         wpfb.FRMBotones.fAccesos('','');
       }
    }
  }

  function fSalir(){
     form = document.forms[0];
     form.action = 'pg'+cPagina+'00000.jsp';
     form.target = '_top';
     if((wExp != null) && (!wExp.closed)){
        wExp.close();
     }
     form.submit();
  }

  function fAyuda() {
        //wExp = open(document.forms[0].hdAyuda.value,"Ayuda","dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,width=700,height=450,screenX=0,screenY=0");
 //     //wExp = open("http://aplicaciones9.sct.gob.mx/imagenes/medprev/ManualUsuario_MEDPREV.pptx","Acerca","dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,width=630,height=530,screenX=20,screenY=10");
	    //wExp = open("pg0700013.jsp","Ayuda","dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,width=850,height=580,screenX=0,screenY=0");
	    //wExp.focus();
	  fAyudaB();
  }

  function asignaAyuda(stAyuda){
    if (document.forms[0]){
      if (document.forms[0].hdAyuda){
        document.forms[0].hdAyuda.value = cRutaAyuda + stAyuda.substring(0,stAyuda.length - 3) + cExtAyuda;
        return true;
      }
    }
    return false;
  }
  
  function asignaBoton(stAyuda){
	    if (document.forms[0]){
	      if (document.forms[0].hdBoton){
	        document.forms[0].hdBoton.value = stAyuda;
	        return true;
	      }
	    }
	    return false;
	  }

  function fAcerca() {
    wExp = open("pgAcerca.jsp","Acerca","dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,width=630,height=530,screenX=20,screenY=10");
    wExp.focus();
  }