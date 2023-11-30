  var wExp2;
  var aDib2;
  var aTemporal;

  function fAsigna(iOido){
     form = document.forms[0];
     form.target =  "FRMDatos";
     form.hdBoton.value = 'AudioMuestra';
     form.hdGOido.value = iOido;
     //guarda_signos_vitales3();
     form.action='/epi/grdservicios/graficasAudio.jsp';
     form.method='get'; 
     form.target='_blank';
     form.submit();
  }


   function fAsigna2(iOido){
     form = document.forms[0];
     form.target =  "FRMDatos";
     form.hdBoton.value = 'AudioMuestra';
     form.hdGOido.value = iOido;
     guarda_signos_vitales2();
     //form.submit();
  }


  function fMuestraOidos(){
    form = document.forms[0];
    alert("Prueba O Izquierdo: " + form.hdOIM.value);
    alert("Prueba O Derecho: " + form.hdODM.value);
  }

  function fGraphAudio(iOido, aSel){
     cOido = '';
     if(iOido == 1)
       cOido = 'Derecho';
     else
       cOido = 'Izquierdo';

     aDib2 = aSel;
     aTemporal = aSel;

//     alert("valor para el barrido: " + aSel + "**" + aDib2);
     form = document.forms[0];

     if((wExp2 != null) && (!wExp2.closed))
        wExp2.focus();
     else{
           cPagina = '<HTML><title>Audiometria - Oido: ' + cOido + '</title>\n'+
           cStyle +
           '<body bgcolor="white">'+
          '<form METHOD="POST">'+
//           '<table border="0" class="ETablaInfo" width="100%" background="C:\\sct\\medprev\\img\\CopiaAudio02.gif">\n'+
           '<table border="0" class="ETablaInfo" width="100%" background="'+cRutaImgServer+'CopiaAudio02.gif">\n'+
           '<tr><td colspan="9">&nbsp;</td>\n';

           cb1 = '';
           var iRow = -10;
           for(j=0;j<29;j++){
             cb1 = cb1 + '</tr><tr><td width="10%">'+iRow+'</td>\n';
             for(i=0;i<8;i++){
               cDato = fSel(i+1,j+1,iOido);
               cb1 += '<td width="10%">'+cDato+'</td>\n';
             }
             iRow +=5;
           }

           cPagina = cPagina + cb1 +
           '</tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr>\n'+
           '</table>\n'+
           '</form></body></HTML>\n'+
           '<SCRIPT LANGUAGE="JavaScript">\n'+
           '  //window.print();\n'+
           '</SCRIPT>\n';
       wExp2 = window.open("", "","width=530,height=600,status=no,resizable=no,top=200,left=200,scrollbars=yes");
       wExp2.opener = window;
       wExp2.moveTo(100,100);
       this.wExp2.document.write(cPagina);
//       window.onclick=HandleFocus
//       window.onfocus=HandleFocus
//       fSetModal();
     }
  }


function fGraphAudioV(iOido, aSel){
     cOido = '';
     var vArreglo = new Array();
     if(iOido == 1)
       cOido = 'Derecho';
     else
       cOido = 'Izquierdo';

     aDib2 = aSel;
     vArreglo = aSel;
     form = document.forms[0];

     if((wExp2 != null) && (!wExp2.closed))
        wExp2.focus();
     else{
           /*cPagina = '<HTML><title>Audiometria - Oido: ' + cOido + '</title>\n'+
           cStyle +
           '<body bgcolor="white">'+
           '<form METHOD="POST">'+*/
           cPagina = '<table border="0" class="ETablaInfo" align="center" width="530" background="'+cRutaImgServer+'CopiaAudio03.gif">\n'+
//           '<table border="0" align="center" width="530" background="C:\\sct\\medprev\\img\\CopiaAudio03.gif">\n'+
           '<tr><td colspan="9">&nbsp;</td>\n';

           //<td class='ETablaT' colspan='9'>Datos Personales</td>
           cb1 = '';
           var iRow = -10;
           for(j=0;j<29;j++){
             cb1 += '</tr><tr><td width="10%">'+iRow+'</td>\n';
             for(i=0;i<8;i++){
               cDato = fSel(i+1,j+1,iOido);
               cb1 +=  '<td width="10%">'+cDato+'</td>\n';
             }
             iRow +=5;
           }
           cPagina = cPagina + cb1 +
           '</tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr>\n'+
           '</table>\n';
           document.write(cPagina);

           /*+
           '</form></body></HTML>\n'+
           '<SCRIPT LANGUAGE="JavaScript">\n'+
           '  window.print();\n'+
           '</SCRIPT>\n';

       wExp2 = window.open("", "","width=524,height=437,menubar=yes,status=no,resizable=no,top=200,left=200");
       wExp2.opener = window;
       wExp2.moveTo(100,100);
       this.wExp2.document.write(cPagina);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();*/
     }
  }
/*
  function fSel(iR, iC, iOido){
     alert("adib length:" + aDib2.length);

     var cImagen = "";
     var aTmp = new Array();
     aTmp = aTemporal;
     for(var L = 0; L < aTmp.length; L++){

       if(aTmp[L][2] == iR && aTmp[L][3] == iC){
//         cImagen = '';

         if(iOido == 1){
           if(aTmp[L][0] == 1){
             if(aTmp[L][1] == 1)
                cImagen += '<img SRC="'+cRutaImgServer+'AuOido01.gif">';
             else if(aTmp[L][1] == 2)
                cImagen += '<img SRC="'+cRutaImgServer+'AuCorchete01.gif">';
//             if(aTmp[3] == 15)
             else if(aTmp[L][1] == 3)
                cImagen += '<img SRC="'+cRutaImgServer+'AuTriangulo01.gif">';
             else
                cImagen += '<img SRC="'+cRutaImgServer+'AuAbajo01.gif">';

//             return cImagen;
           }
         }
         else{
           if(aTmp[L][0] == 2){
             else if(aTmp[L][1] == 1)
                cImagen += '<img SRC="'+cRutaImgServer+'AuOido02.gif">';
             else if(aTmp[L][1] == 2)
                cImagen += '<img SRC="'+cRutaImgServer+'AuCorchete02.gif">';
//             if(aTmp[3] == 15)
             else if(aTmp[[L]1] == 3)
               cImagen += '<img SRC="'+cRutaImgServer+'AuTriangulo01.gif">';
             else
                cImagen += '<img SRC="'+cRutaImgServer+'AuAbajo02.gif">';
//             return  cImagen;
           }
         }
       }
     }
     if(cImagen == "")
        cImagen = "&nbsp";

     return cImagen;
  }*/

  function fSel(iR, iC, iOido){
     var cImagen = "";
     for(L=0;L<aDib2.length;L++){
       aTmp = aDib2[L];
       if(aTmp[2] == iR && aTmp[3] == iC){
         if(iOido == 1){
           if(aTmp[0] == 1){
             if(aTmp[1] == 1)
                cImagen += '<img SRC="'+cRutaImgServer+'AuOido01.gif">';
             else if(aTmp[1] == 2)
                cImagen += '<img SRC="'+cRutaImgServer+'AuCorchete01.gif">';
//             if(aTmp[3] == 15)
             else if(aTmp[1] == 3)
                cImagen += '<img SRC="'+cRutaImgServer+'AuTriangulo01.gif">';
             else
                cImagen += '<img SRC="'+cRutaImgServer+'AuAbajo01.gif">';
//             return cImagen;
           }
         }else{
           if(aTmp[0] == 2){
             if(aTmp[1] == 1)
                cImagen += '<img SRC="'+cRutaImgServer+'AuOido02.gif">';
             else if(aTmp[1] == 2)
                cImagen += '<img SRC="'+cRutaImgServer+'AuCorchete02.gif">';
//             if(aTmp[3] == 15)
             else if(aTmp[1] == 3)
               cImagen += '<img SRC="'+cRutaImgServer+'AuTriangulo01.gif">';
             else
                cImagen += '<img SRC="'+cRutaImgServer+'AuAbajo02.gif">';
//             return  cImagen;
           } // if Tipo de Oido
         } 
       } // if aTmp 
     } // for
	   if(cImagen == "")
		  return '&nbsp;'
	   else
		  return cImagen;

  } // funcion