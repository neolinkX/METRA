  var wExp3;

  function fAudio2(cOido){
     form = document.forms[0];
     if((wExp3 != null) && (!wExp3.closed))
        wExp3.focus();
     else{
           cPagina = '<HTML><title>Audiometria - Oido: ' + cOido + '</title>\n'+
           '<SCRIPT LANGUAGE="JavaScript">\n'+
           '  function fClose(){\n'+
           '    form = window.opener;\n'+
           '    form.submit(); window.close();\n'+
           '  }\n'+
           '  function fAudio(){\n'+
           '    form = document.forms[0]; \n'+
           '    aSel = new Array();\n'+
           '    z=0;\n'+
           '    h=0;\n'+
           '       for(i = 0; i < form.elements.length; i++){\n'+
           '         if(form.elements[i].name.substring(0,9) == "lEstudio-"){\n'+
           '           if(form.elements[i].checked){\n'+
           '              aSel[h] = [form.elements[i].name.substring(9),form.elements[i].value];\n'+
           '              form.elements[i].checked = false;'+
           '              h=h+1;'+
           '           }\n'+
           '         }\n'+
           '         if(form.elements[i].name == "Sel" && form.elements[i].checked)\n'+
           '           z=form.elements[i].value;'+
           '       }\n'+
           '       if(window.opener.fSaveAudio){               '+
           '                                     window.opener.fSaveAudio(aSel,z,"'+cOido+'");\n'+
           '                                     window.close();'+
           '       }\n'+
           '  }\n'+
           '</SCRIPT>\n'+cStyle +
           '<body bgcolor="white">'+
           '<form METHOD="POST">'+
           '<table border="0" class="ETablaInfo" width="100%">\n'+
           '<tr>\n'+
           '<td class="EEtiquetaC"><input type="radio" value="1" checked name="Sel">Via Aerea ' + cOido + '</td>\n'+
           '<td class="EEtiquetaC"><input type="radio" value="2" name="Sel">Via Osea ' + cOido + '</td>\n'+
           '<td class="EEtiquetaC"><input type="radio" value="3" name="Sel">Auxiliar Auditivo</td>\n'+
           '<td class="EEtiquetaC"><input type="radio" value="4" name="Sel">Sin Respuesta</td>\n'+
           '<td class="EEtiquetaC"><a class="EAncla" name="" href="JavaScript:fAudio();">Guardar</a></td>\n'+
           '</tr>\n'+
           '</table>\n'+
//           '<table border="0" class="ETablaInfo" width="100%" background="C:\\sct\\medprev\\img\\CopiaAudio02.gif">\n'+
           '<table border="0" class="ETablaInfo" width="100%" background="'+cRutaImgServer+'CopiaAudio02.gif">\n'+
           '<tr><td colspan="9">&nbsp;</td>\n';

           cb1 = '';
           var iRow = -10;
           for(j=0;j<29;j++){
             cb1 = cb1 + '</tr><tr><td width="10%" >'+iRow+'</td>\n';
             for(i=0;i<8;i++){
               cb1 = cb1 + '<td width="10%"><input type="radio" name="lEstudio-'+(i+1)+'" value="'+(j+1)+'"></td>\n';
             }
             iRow +=5;
           }

           cPagina = cPagina + cb1 +
           '</tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr>\n'+
           '</table>\n'+
           '</form></body></HTML>\n';
       wExp3 = window.open("", "","width=530,height=800,status=no,resizable=no,top=200,left=200,scrollbars=yes");
       wExp3.opener = window;
       wExp3.moveTo(400,100);
       this.wExp3.document.write(cPagina);
//       window.onclick=HandleFocus
//       window.onfocus=HandleFocus
//       fSetModal();
     }
  }