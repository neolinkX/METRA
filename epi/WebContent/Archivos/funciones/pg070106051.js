function fVerExamen(iCveExpediente,iNumExamen,iCveServicio,iCveProceso){
       cInicio = "";
       form = document.forms[0];

       cInicio = "?hdiCveExpediente=" + iCveExpediente;
       cInicio += "&hdiNumExamen=" + iNumExamen;
       cInicio += "&hdICveServicio=" + iCveServicio;
       cInicio += "&hdICveProceso=" + iCveProceso;

       if((wExp != null) && (!wExp.closed))
         wExp.focus();

        wExp = open("pg070104071.jsp"+cInicio, "Consulta",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=780,height=500,screenX=800,screenY=600');
        wExp.moveTo(50,50);
        window.onclick=HandleFocus
        window.onfocus=HandleFocus
        fSetModal();
    }