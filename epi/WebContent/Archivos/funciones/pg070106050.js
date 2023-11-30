  var wExp;


/*
  function fSelectedPer(iCvePersonal, iCveExpediente, iNumExamen){
    form = document.forms[0];
    form.hdTipo.value = iCvePersonal;
    form.target = "_self";
    form.submit();
  }

*/

  function fClose(){
    form = document.forms[0];
    form.target = "_self";
    form.submit();
  }

  function fGetFoto(){
     form = document.forms[0];
     if((wExp != null) && (!wExp.closed))
        wExp.focus();
     else{
           cPagina = '<HTML><title>Guardar Fotograf�a</title>'+cStyle+'\n'+
           '<SCRIPT LANGUAGE="JavaScript">\n'+
           '  function fSavePict(){\n'+
           '    form = document.forms[0];\n'+
           '    cArchivo="' + cFoto1GetRuta + form.hdTipo.value + '.jpg" \n'+
           '    if(form.fArchivo.value != cArchivo) \n'+
           '      alert("El archivo seleccionado no es el correcto! \\n " + form.fArchivo.value + " <> " + cArchivo);\n'+
           '    else\n'+
           '      form.submit();\n'+
           '  }\n'+
           '  function fClose(){\n'+
           '    form = window.opener;\n'+
           '    form.submit(); window.close();\n'+
           '  }\n'+
           '</SCRIPT>\n'+
           '<body bgcolor="white">'+
           '<form METHOD="POST" ACTION="servUpFoto?hdiCvePersonal='+form.hdTipo.value+'&hdUp=Foto" ENCTYPE="multipart/form-data">'+
           '<OBJECT\n'+
           'classid="'+cFoto1ClassID+'"\n'+
           'codebase="'+cFoto1CodeBase+'"\n'+
           'width=0\n'+
           'height=0\n'+
           'align=center\n'+
           'hspace=0\n'+
           'vspace=0\n'+
           '>\n'+
           '<PARAM NAME="FNAME" VALUE="'+form.hdTipo.value+'">\n'+
           '<PARAM NAME="URUN" VALUE="YES">\n'+
           '</OBJECT>\n'+
           '<table border="1" class="ETablaInfo" width="100%" height="100%" cellspacing="0" cellpadding="3">\n'+
           '<tr><td class="ETablaT" align="center">Archivo de Fotograf�a<tr><td>'+
           '<tr><td class="EEtiquetaC" >' + cFoto1GetRuta + form.hdTipo.value + '.jpg' + '\n'+
           '<tr><td>'+
           '<tr><td align="center">' +
           '<input type="File" name="fArchivo" size="50" maxlength="80">\n'+
           '</td></tr>'+
           '<tr><td align="center">\n'+
           '<A HREF="javascript:fSavePict();">Guardar Fotograf�a</A>\n'+
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

  function fShowPict(cTipo, iCvePersonal, iCveSec){
     form = document.forms[0];
     if((wExp != null) && (!wExp.closed))
        wExp.focus();
     else{
           cPagina = '<HTML>\n'+
           '    <BODY>\n'+
           '    <FORM ACTION="servDownFoto?hdiCvePersonal='+iCvePersonal+'&hdiCveSec='+iCveSec+'&hdDown='+cTipo+'" METHOD="POST" ENCTYPE="multipart/form-data">\n'+
           '    </FORM>\n'+
           '  </BODY>\n'+
           '<script language="JavaScript">\n'+
           '    document.forms[0].submit();\n'+
           '</script>\n'+
           '</HTML>\n';
        wExp = window.open("", "","width=300,height=300,status=no,resizable=yes,top=200,left=200");
        wExp.opener = window;
        wExp.moveTo(100,100);
        this.wExp.document.write(cPagina);
        window.onclick=HandleFocus
        window.onfocus=HandleFocus
        fSetModal();
     }
  }

  function fGetFirma(){
     form = document.forms[0];
     if((wExp != null) && (!wExp.closed))
        wExp.focus();
     else{
           cPagina = '<HTML><title>Guardar Firma</title>'+cStyle+'\n'+
           '<SCRIPT LANGUAGE="JavaScript">\n'+
           '  function fSavePict(){\n'+
           '    form = document.forms[0];\n'+
           '    cArchivo="' + cFirma1GetRuta + 'S' + form.hdTipo.value + '.jpg" \n'+
           '    if(form.fArchivo.value != cArchivo) \n'+
           '      alert("El archivo seleccionado no es el correcto! \\n " + form.fArchivo.value + " <> " + cArchivo);\n'+
           '    else\n'+
           '      form.submit();\n'+
           '  }\n'+
           '  function fClose(){\n'+
           '    form = window.opener;\n'+
           '    form.submit(); window.close();\n'+
           '  }\n'+
           '</SCRIPT>\n'+
           '<body bgcolor="white">\n'+
           '<form METHOD="POST" ACTION="servUpFoto?hdiCvePersonal='+form.hdTipo.value+'&hdUp=Firma" ENCTYPE="multipart/form-data">\n'+
           '<OBJECT\n'+
           'classid="'+cFirma1ClassID+'"\n'+
           'codebase="'+cFirma1CodeBase+'"\n'+
           '	  width=0\n'+
           '	  height=0\n'+
           '	  align=center\n'+
           '	  hspace=0\n'+
           '	  vspace=0\n'+
           '>\n'+
           '<PARAM NAME="FNAME" VALUE="'+form.hdTipo.value+'">\n'+
           '<PARAM NAME="URUN" VALUE="YES">\n'+
           '</OBJECT>\n'+
           '<table border="1" class="ETablaInfo" width="100%" height="100%" cellspacing="0" cellpadding="3">\n'+
           '<tr><td class="ETablaT" align="center">Archivo de Firma<tr><td>\n'+
           '<tr><td class="EEtiquetaC" >' + cFirma1GetRuta + 'S' + form.hdTipo.value + '.jpg' + '\n'+
           '<tr><td>\n'+
           '<tr><td align="center">\n'+
           '<input type="File" name="fArchivo" size="50" maxlength="80">\n'+
           '</td></tr>\n'+
           '<tr><td align="center">\n'+
           '<A HREF="javascript:fSavePict();">Guardar Firma</A>\n'+
           '</td></tr>\n'+
           '</table>\n'+
           '</form></body>\n'+
           '</HTML>\n';
       wExp = window.open("","","width=600,height=15,status=no,resizable=no,top=200,left=200");
       wExp.opener = window;
       wExp.moveTo(100,100);
       this.wExp.document.write(cPagina);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
     }
  }

  function fGetHuella(iCveHuella){
     form = document.forms[0];
     if((wExp != null) && (!wExp.closed))
        wExp.focus();
     else{
           cPagina = '<HTML><title>Guardar Huella</title>'+cStyle+'\n'+
           '<SCRIPT LANGUAGE="JavaScript">\n'+
           '  function fSavePict(){\n'+
           '    form = document.forms[0];\n'+
           '    cArchivo="' + cHuella1GetRuta + 'H' + form.hdTipo.value + '-' + iCveHuella + '.jpg" \n'+
           '    if(form.fArchivo.value != cArchivo) \n'+
           '      alert("El archivo seleccionado no es el correcto! \\n " + form.fArchivo.value + " <> " + cArchivo);\n'+
           '    else\n'+
           '      form.submit();\n'+
           '  }\n'+
           '  function fClose(){\n'+
           '    form = window.opener;\n'+
           '    form.submit(); window.close();\n'+
           '  }\n'+
           '</SCRIPT>\n'+
           '<body bgcolor="white">'+
           '<form METHOD="POST" ACTION="servUpFoto?hdiCvePersonal='+form.hdTipo.value+'&hdUp=Huella&hdiCveHuella='+iCveHuella+'" ENCTYPE="multipart/form-data">'+
           '<OBJECT\n'+
           'classid="'+cHuella1ClassID+'"\n'+
           'codebase="'+cHuella1CodeBase+'"\n'+
           'width=0\n'+
           'height=0\n'+
           'align=center\n'+
           'hspace=0\n'+
           'vspace=0\n'+
           '>\n'+
           '<PARAM NAME="FNAME" VALUE="'+form.hdTipo.value + '-' + iCveHuella + '">\n'+
           '<PARAM NAME="URUN" VALUE="YES">\n'+
           '</OBJECT>\n'+
           '<table border="1" class="ETablaInfo" width="100%" height="100%" cellspacing="0" cellpadding="3">\n'+
           '<tr><td class="ETablaT" align="center">Archivo de Huella Digital<tr><td>'+
           '<tr><td class="EEtiquetaC" >' + cHuella1GetRuta + 'H' + form.hdTipo.value + '-' + iCveHuella + '.jpg' + '\n'+
           '<tr><td>'+
           '<tr><td align="center">' +
           '<input type="File" name="fArchivo" size="50" maxlength="80">\n'+
           '</td></tr>'+
           '<tr><td align="center">\n'+
           '<A HREF="javascript:fSavePict();">Guardar Huella</A>\n'+
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

  
  function fDetalle(Personal){
	   var form = document.forms[0];
	    form.hdICvePersonal.value = Personal;
	    form.hdBoton.value  = "Detalle";
	    form.target = 'FRMDatos';
	    form.action = 'pg070103041.jsp';
	    form.submit();
	  }
