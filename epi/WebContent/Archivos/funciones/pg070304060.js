
  function fValidaTodo(){
    var form = document.forms[0];

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

      if (!confirm(" ¿ Desea Guardar los Resultados de las Muestras ? "))
         return false;
    }
   return true;
  }


  var wExp;

  function fGetFile(cServlet){
     form = document.forms[0];
     if((wExp != null) && (!wExp.closed))
        wExp.focus();
     else{
           cPagina = '<HTML><title>Cargar Información</title>'+cStyle+'\n'+
           '<SCRIPT LANGUAGE="JavaScript">\n'+
           '  function fSaveFile(){\n'+
           '    form = document.forms[0];\n'+
//           '    cArchivo="'+cArchivo+'" \n'+
           '     form.submit();\n'+
           '  }\n'+
           '  function fClose(){\n'+
           '    form = window.opener;\n'+
           '    form.submit(); window.close();\n'+
           '  }\n'+
           '</SCRIPT>\n'+
           '<body bgcolor="white">'+
//           '<form METHOD="POST" ACTION="servUpCroma2?iAnio='+form.SLSAnio.value+'&iCveUniMed='+form.iCveUniMed.value+'&iCveSustancia='+form.SLSSustancia.value+'&iCveLoteCuantita='+form.SLSLoteConfirmativo2.value+'" ENCTYPE="multipart/form-data">'+
           '<form METHOD="POST" ACTION="' +  cServlet +'?iAnio='+form.SLSAnio.value+'&iCveUniMed='+form.iCveUniMed.value+'&iCveSustancia='+form.SLSSustancia.value+'&iCveLoteCuantita='+form.SLSLoteConfirmativo2.value+'&iTipoCarga=2" ENCTYPE="multipart/form-data">'+
			   // El parámetro iTipoCarga define si son calibradores o muestras : 1 Calibradores - 2 Muestras
           '<table border="1" class="ETablaInfo" width="100%" height="100%" cellspacing="0" cellpadding="3">\n'+
           '<tr><td class="ETablaT" align="center">Cargar Información<tr><td>'+
           '<tr><td align="center">' +
           '<input type="File" name="fArchivo" size="50" maxlength="80">\n'+
           '</td></tr>'+
           '<tr><td align="center">\n'+
           '<A HREF="javascript:fSaveFile();">Cargar Información</A>\n'+
           '</td></tr>\n'+
           '</table>\n'+
           '</form></body></HTML>\n';
       wExp = window.open("", "","width=600,height=15,status=no,resizable=no,top=200,left=200");
       wExp.opener = window;
       wExp.moveTo(100,100);
       this.wExp.document.write(cPagina);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();

     }
  }

