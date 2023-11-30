
//Se Agrego para desplegar pantalla alterna de los servicios


 function fIr(cCampoClave, cPropiedad, cPagina){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
   // form.hdCampoClave1.value = cCampoClave;
	form.iCvePerfil.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
	form.hdLOrdenar.value = '';   // para no arrastrar el parámetro
	form.hdLCondicion.value = '';
    form.hdBoton.value = 'Actual';
    
   form.hdiCveExpediente.value = form.hdiCveExpediente.value;
   form.hdiNumExamen.value = form.hdiNumExamen.value;
   form.hdICveServicio.value =  form.hdICveServicio.value;
   form.hdICveProceso.value = form.hdICveProceso.value;
    
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }

 function fIrVerExamen(cCampoClave, cPropiedad, cPagina){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    //form.hdCampoClave1.value = cCampoClave;
	form.iCvePerfil.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
	form.hdLOrdenar.value = '';   // para no arrastrar el parámetro
	form.hdLCondicion.value = '';
    form.hdBoton.value = 'Actual';
    
   form.hdiCveExpediente.value = form.hdiCveExpediente.value;
   form.hdiNumExamen.value = form.hdiNumExamen.value;
   form.hdICveServicio.value =  form.hdICveServicio.value;
   form.hdICveProceso.value = form.hdICveProceso.value;
    
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }
  

  ///FUNCIONES PARA CAPTURAR PUNTOS DE REFERENCIA///

  var wExp;
  function fCapRef70(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("CapRef70.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=1000,height=800,screenX=10,screenY=10');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }


 function fMosRef70(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("MosRef70.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=900,height=800,screenX=800,screenY=900');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }

  var wExp;
  function fCapRef54t(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("CapRef70Pst.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=1000,height=800,screenX=10,screenY=10');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }


 function fMosRef54t(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("MosRef70Pst.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=900,height=800,screenX=800,screenY=900');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }
  
    var wExp;
  function fCapRef54a(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("CapRef54.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=1000,height=800,screenX=10,screenY=10');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }


 function fMosRef54a(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("MosRef54.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=900,height=800,screenX=800,screenY=900');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }
  
 var wExp;
  function fCapRef54AE(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("CapRef54AE.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=1000,height=800,screenX=10,screenY=10');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }


 function fMosRef54AE(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("MosRef54AE.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=900,height=800,screenX=800,screenY=900');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }  
  
 var wExp;
  function fCapRef54AMD(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("CapRef54AMD.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=1000,height=800,screenX=10,screenY=10');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }


 function fMosRef54AMD(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("MosRef54AMD.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=900,height=800,screenX=800,screenY=900');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }   

 var wExp;
  function fCapRef54AMI(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("CapRef54AMI.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=1000,height=800,screenX=10,screenY=10');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }


 function fMosRef54AMI(exp,exa,serv,rama){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       wExp = open("MosRef54AMI.jsp?icveexpediente="+exp+"&inumexamen="+exa+"&servicio="+serv+"&rama="+rama, "TORAX",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=900,height=800,screenX=800,screenY=900');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }   

