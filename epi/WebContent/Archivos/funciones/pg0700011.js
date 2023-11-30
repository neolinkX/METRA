
  function fOculto(cValor){
    form = document.forms[0];
    for(i = 0; i < form.elements.length; i++){
       form.elements[i].disabled = cValor;
    }
  }

  function fSubmite(){
    if (window.parent.parent.parent.FRMCuerpo.FRMDatos){
      if (window.parent.parent.parent.FRMCuerpo.FRMDatos.fValidaIrA)
        if (!window.parent.parent.parent.FRMCuerpo.FRMDatos.fValidaIrA())
          return false;
    }
    if(window.parent.parent.parent.FRMTitulo){
      vFRM = window.parent.parent.parent.FRMTitulo;
      for(i=0; i<vForm.SLSAccesos.length; i++){
        if(vForm.SLSAccesos[i].value == vForm.SLSAccesos.value){
          vFRM.fCargarPagOpener(vForm.SLSAccesos.value, vForm.SLSAccesos[i].text);
          break;
        }
      }
    }
  }

  function fPag(){
      cPagina ='<SCRIPT LANGUAGE="JavaScript" SRC="'+cRutaFuncs+'toolbars.js"></SCRIPT>'+
         '<SCRIPT LANGUAGE="JavaScript" SRC="'+cRutaFuncs+'valida_nt.js"></SCRIPT>'+
         '<SCRIPT language="JavaScript" src="'+cRutaFuncs+'t07_Tooltips.js"></SCRIPT>'+
         '<SCRIPT LANGUAGE="JavaScript" SRC="'+cRutaFuncs+'buscar_nt.js"></SCRIPT>'+
         '<html>'+
         '<link rel="stylesheet" href="'+Style+'" TYPE="text/css">'+
         '<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">'+
         '<form method="POST" action="pg0700011.jsp">'+
         '  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background="'+cRutaImgServer+'fondoFiltro.jpg">'+
         '    <tr>'+
         '      <td>'+
         '        <table border="0" width="100%" cellspacing="0" cellpadding="0" align="center">'+
         '          <tr>'+
         '                <td valign="middle" >'+
         '                  <img SRC="'+cRutaImgServer+'division.gif">'+
         '                </td>'+
         '                <td width="10%" valign="middle" align="left">&nbsp;'+
         '                    <a href="javascript:fSubmite();" '+
         '  onMouseOut="if(top.fCambiaImagen)top.fCambiaImagen(document,'+"'"+'Ira a...'+"'"+','+"''"+','+"'"+cRutaImgServer+'bIra01.gif'+"'"+',1);self.status='+"''"+';return true;"'+
         ' onMouseOver="if(top.fCambiaImagen)top.fCambiaImagen(document,'+"'"+'Ira a...'+"'"+','+"''"+','+"'"+cRutaImgServer+'bIra02.gif'+"'"+',1);self.status='+"'"+'Ir a ...'+"'"+';return true;"><img name="Ira a..." border="0" src="'+cRutaImgServer+'bIra01.gif"></a>'+
         '                </td>'+
         '                <td width="100%" valign="middle" >'+
         '                  <select name="SLSAccesos" size="1">'+
         '                  </select>'+
         '                </td>'+
         '          </tr>'+
         '        </table>'+
         '      </td>'+
         '    </tr>'+
         '  </table>'+
         '</form>'+
         '</body>'+
         '<SCRIPT LANGUAGE="JavaScript">'+
         '    if (top.fPreCargarImagen){'+
         '        top.fPreCargarImagen(document,"'+cRutaImgServer+'bIra01.gif");'+
         '        top.fPreCargarImagen(document,"'+cRutaImgServer+'bIra02.gif");'+
         '    }'+
         '</SCRIPT>'+
         '</html>';
         document.write(cPagina);
  }


  function fOnLoad(){
    fAsignaImg(cRutaImgServer,cRutaImgLocal);
  }

  function fAccesos(cDscBotones, cPaginas){
    vForm = document.forms[0];
    cNumCampos = fNumEntries(cDscBotones,"|");
    if(cNumCampos > 0){
       fOculto(false);
       vForm.SLSAccesos.length = cNumCampos;
       for(i=1; i <= cNumCampos; i++){
          cNombre = fEntry(i,cDscBotones,"|");
          vForm.SLSAccesos[i-1].value = fEntry(i,cPaginas,"|");
          cText = cNombre.substring(0,1) + cNombre.toLowerCase().substring(1);
          if(screen.availWidth > 1000)
	    iLetras = 40;
          else
	    iLetras = 30;

          if(cText.length > iLetras)
            cText = cText.substring(0,iLetras) + '..';
          vForm.SLSAccesos[i-1].text  = cText;
       }
       vForm.SLSAccesos[0].selected = true;
    }else{
       vForm.SLSAccesos.length = 0;
       fOculto(true);
    }
  }
