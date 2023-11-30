  var wExp;

  function fVerExamen(iCveExpediente,iNumExamen,iCveServicio,iCveProceso){
      cInicio = "";
      form = document.forms[0];

      cInicio = "?hdiCveExpediente=" + iCveExpediente;
      cInicio += "&hdiNumExamen=" + iNumExamen;
      cInicio += "&hdICveServicio=" + iCveServicio;
      cInicio += "&hdICveProceso=" + iCveProceso;

      if((wExp != null) && (!wExp.closed))
        wExp.focus();

//       wExp = open("pg070104021.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=700,height=500,screenX=800,screenY=600');
//       wExp = open("pg070104070.jsp"+cInicio, "Consulta",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=300,screenX=800,screenY=600');
       wExp = open("pg070104071.jsp"+cInicio, "Consulta",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=780,height=500,screenX=800,screenY=600');
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
   }

   function fIrCatalogo(page,val){
    var form=document.forms[0];
    form.action=page + "?iNumExamen=" + val;
    form.target="FRMDatos";
    form.submit();
  }


  function fSelectedPer(iCvePersonal,iNumExamen,solicitado){
    var form = document.forms[0];
    form.hdiCveExpediente.value = iCvePersonal;
    form.hdiNumExamen.value = iNumExamen;
    form.hdSolicitado.value = solicitado;
    form.target = "_self";
    form.submit();
  }

