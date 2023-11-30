
  function fValidaTodo(){
    var form = document.forms[0];

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

      if (form.TBXValidacion){
         if (form.TBXValidacion.checked == true){
           if (!confirm(" ¿ Desea Procesar la Validación de los Controles ? "))
           return false;
         } else {
           if (!confirm(" ¿ Desea Procesar la Calibración del Lote Confirmatorio ? "))
           return false;
         }
      }
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
//           '<form METHOD="POST" ACTION="servUpRadox?iAnio='+form.iAnio.value+'&iCveUniMed='+form.iCveUniMed.value+'&iCveLoteCualita='+form.iCveLoteCualita.value+'&iCveExamCualita='+form.iCveExamCualita.value+'" ENCTYPE="multipart/form-data">'+
           '<form METHOD="POST" ACTION="' + cServlet + '?iAnio='+form.SLSAnio.value+'&iCveUniMed='+form.iCveUniMed.value+'&iCveSustancia='+form.SLSSustancia.value+'&iCveLoteCuantita='+form.SLSLoteConfirmativo.value+'&iTipoCarga=1" ENCTYPE="multipart/form-data">'+
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

