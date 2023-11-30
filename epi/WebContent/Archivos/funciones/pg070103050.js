
  function fGenExpediente(UniMed,Modulo,Fecha,Cita){
   var form = document.forms[0];

   if(confirm("¿Desea dar de alta a la persona correspondiente ?")){
        //return false;

    form.hdCveUniMed.value = UniMed;
    form.hdCveModulo.value = Modulo;
    form.hdFecha.value = form.dtFecha.value;
    form.hdCveCita.value = Cita;
    form.hdBoton.value  = "Expediente";
    form.target = "_self";
    form.submit();
   }

  }


 function fPuesto(Personal,UniMed,Modulo){
   var form = document.forms[0];

    form.hdICvePersonal.value = Personal;
    form.hdICveUniMed.value = UniMed;
    form.hdICveModulo.value = Modulo;

    form.hdBoton.value  = "Expediente";

    form.target = 'FRMDatos';
    form.action = 'pg070103010.jsp';
    form.submit();
  }


function fDetalle(Personal){
   var form = document.forms[0];

    form.hdICvePersonal.value = Personal;
    //alert("Personal :" + Personal);

    form.hdBoton.value  = "Detalle";

    form.target = 'FRMDatos';
    form.action = 'pg070103045.jsp';
    form.submit();
  }


  function fIrCatalogo(cCveUniMed,cCveModulo,cFecha,cCveCita,cPropiedad){
    form = document.forms[0];
    form.hdCveUniMed.value = cCveUniMed;
    form.hdCveModulo.value = cCveModulo;
    form.hdFecha.value = cFecha;
    form.hdCveCita.value = cCveCita;
    form.hdCPropiedad.value = cPropiedad;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = cPropiedad + '.jsp';
    form.submit();
  }
  function fSubmite(){
    var forma=document.forms[0];
    if(fValFecha(forma.dtFecha.value)){
      forma.target='FRMDatos';
      forma.submit();
    }else{
      forma.dtFecha.select();
    }
  }
  function fActualizaModulos(forma){
    var cboMod=forma.iCveModulo;
    while(cboMod.length>0) cboMod.options[0]=null;
    cboMod.options[cboMod.length]=new Option("Cargando Datos...","");
    window.parent.FRMOtroPanel.location="pg070103040P.jsp?"+
      "iOpc=2" + // Unidad M�dica
	  "&cCombo=iCveModulo" + 
      "&iCveUniMed=" + forma.iCveUniMed.value + // iCveUniMed
	  "&hdAccion="   + document.forms[0].action; // Action

    //forma.dtFecha.value="";
    var cboHoras=forma.cHora;
    while(cboHoras.length>0) cboHoras.options[0]=null;
    cboHoras.options[cboHoras.length]=new Option("Datos no disponibles...","");
  }

  function fActualizaFechas(forma){
    forma.dtFecha.value="";
    var cboHoras=forma.cHora;
    while(cboHoras.length>0) cboHoras.options[0]=null;
    cboHoras.options[cboHoras.length]=new Option("Datos no disponibles...","");
  }

  function fActualizaHora(forma){
    if(fValFecha(forma.dtFecha.value)){
      var cboHoras=forma.cHora;
      while(cboHoras.length>0) cboHoras.options[0]=null;
      cboHoras.options[cboHoras.length]=new Option("Cargando Datos...","");
      window.parent.FRMOtroPanel.location="pg070102030P.jsp?"+
        "idAxn=BUILD_HRS"+
        "&iCveUniMed="+forma.iCveUniMed.value+
        "&iCveModulo="+forma.iCveModulo.value+
        "&dtFecha="+forma.dtFecha.value + "&hdAccion="+ document.forms[0].action;
    }else{
      forma.dtFecha.select();
    }
  }
  function fValidaCURP(curp){	
		 
		 if(!curp.value.match(/^[A-Z]{4}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CH|CL|CS|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}$/i)){//AAAA######AAAAAA##
			 alert('La CURP ingresada no es valida.');	    	
		 }
	}