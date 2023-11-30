     
     function fCatalogo(forma,pagina,rwId){
        if (fValidaTodo()) {
                forma.hdIni.value       = rwId;
                forma.hdBoton.value  = 'Actual';
                forma.action              = pagina;
                forma.submit();
        }
     }
     
     function fCompleta(forma, valor){
          var x=forma.SLOperador.length;
          var z=forma.SLOperador.selectedIndex;
          if (valor != x) {
             switch (valor){
             case 5:    if (x > 7){
                          for(i=x; i > 7; i--){
                             forma.SLOperador.options[i-1] = null;
                          }
                        }
             case 6:    if (x > 7){
                          for(i=x; i > 7; i--){
                             forma.SLOperador.options[i-1] = null;
                          }
                        }
              break;
             case 7:    if (x > 7){
                          for(i=x; i > 7; i--){
                             forma.SLOperador.options[i-1] = null;
                          }
                        }
                break;

                case 8: if( x > 8){
                        forma.SLOperador.options[8] = null;
                        }
                        if( x < 8){
                           var opcion = new Option("Inicie", "Inicie");
                           forma.SLOperador.options[7] = opcion;
                        }
                break;
                case 9: if( x < 8){
                           var opcion = new Option("Inicie", "Inicie");
                           forma.SLOperador.options[7] = opcion;
                           x++;
                        }
                        if( x < 9){
                           var opcion = new Option("Contenga", "Contenga");
                           forma.SLOperador.options[8] = opcion;
                        }
                break;
             }
          }
          return true;
         }

     function fValida(forma, valor){
      if (forma.SLOperador.selectedIndex != 0) {
       switch (valor){
         case 5:   // Fecha
                 if(!fValFecha(forma.FILBuscar.value)){
                   alert('Favor de verificar el criterio de b£squeda. \\nFormato~  de la fecha: dd/mm/aaaa.');
                   return false;
                 }
         break;
         case 6:   // Decimal
                 if(!fDecimal(forma.FILBuscar.value)){
                   alert('Favor de verificar el criterio de b£squeda. \\nDebe se~ r un n£mero decimal.');
                   return false;
                 }
         break;
          case 7 : // Entero
               if(fSoloNumeros(forma.FILBuscar.value))
                  return true;
               else
                  alert('Por favor verifique el criterio de b£squeda');
                return false;
          break;
          case 8 :
                   return true;
          break;
          case 9 :
               return true; 
          break;
       }
      }
      return true;
     }

function fSetFocus(doc){
  if (doc)
    if (doc.forms[0]){
      form = doc.forms[0];
      var campoEncontrado = false;
      var i;
      for (i=0; i<form.elements.length;i++){
        objeto = form.elements[i];
        if (!objeto.disabled){
          if (objeto.type == 'file' || objeto.type == 'button' || objeto.type == 'checkbox' ||
              objeto.type == 'password' || objeto.type == 'radio' || objeto.type == 'reset' ||
              objeto.type == 'submit' || objeto.type == 'text' || objeto.type == 'textarea' ||
              objeto.type == 'select-one' || objeto.type == 'select-multiple'){
            campoEncontrado = true;
            objeto.focus();
          }
        }
        if (campoEncontrado)
          i = form.elements.length + 1;
      }
    }
}

function fOnSet(cWindow, cAccion, cCanWrite,cUpdStatus,cSaveAction,cDeleteAction,cNavStatus,cOtroStatus,lFiltros,lIra,cDscPaginas,cPaginas,
               cOperador,cDscFiltrar,cCveFiltrar,cTipoFiltrar,cDscOrdenar,cCveOrdenar,cImprimir, lRegNum, cAyuda){

  if(cWindow.parent.parent.FRMCuerpo){
    wp = cWindow.parent;
    wpp = wp.parent;
    wppcf = wpp.FRMCuerpo.FRMFiltro;
    if (wp.FRMUpdPanel){
      if (!wp.FRMUpdPanel.cambiaEstado)
        for (var i = 0; i < 50; i++);
      if (wp.FRMUpdPanel.cambiaEstado)
        wp.FRMUpdPanel.cambiaEstado(cCanWrite, cUpdStatus, cSaveAction, cDeleteAction, cImprimir);
      if (!wp.FRMNavPanel.cambiaEstado)
        for (var i = 0; i < 50; i++);
      if (wp.FRMNavPanel.cambiaEstado)
        wp.FRMNavPanel.cambiaEstado(cNavStatus);
      if (!wp.FRMOtroPanel.cambiaEstado)
        for (var i = 0; i < 50; i++);
      if (wp.FRMOtroPanel.cambiaEstado)
        wp.FRMOtroPanel.cambiaEstado(cOtroStatus);
    }
    if(wpp.FRMTitulo){
       wpp.FRMTitulo.fFiltros(lFiltros);
       wpp.FRMTitulo.fIra(lIra,cDscPaginas,cPaginas);
       wpp.FRMTitulo.asignaAyuda(cAyuda);
    }
    if(wppcf.FRMBusqueda){
       wppcf.FRMBusqueda.fOperador(cOperador,cAccion);
       wppcf.FRMBusqueda.fFiltro(cDscFiltrar,cCveFiltrar,cTipoFiltrar);
       wppcf.FRMBusqueda.fOrdenar(cDscOrdenar,cCveOrdenar);
       wppcf.FRMBusqueda.fRegNum(lRegNum);
    }
  }

  if (cWindow.document)
    if (fSetFocus)
      fSetFocus(cWindow.document);
}



  function fSinValor(cValor, Tipo, cNombre, lMandato){
     cVMsg1 = '';
     if(cValor.value != ''){
        if (Tipo == 1){
           if(!fSoloLetras(cValor.value))
             cVMsg1 = "\\n - El campo '" + cNombre + "' solo permite caracteres alfabéticos.";
        }
        if (Tipo == 2){
           if(!fSoloAlfanumericos(cValor.value))
             cVMsg1 = "\\n - El campo '" + cNombre + "' solo permite caracteres alfanuméricos.";
        }
        if (Tipo == 3){
           if(!fSoloNumeros(cValor.value))
             cVMsg1 = "\\n - El campo '" + cNombre + "' solo permite caracteres numéricos.";
        }
        if (Tipo == 4){
           if(!fDecimal(cValor.value))
             cVMsg1 = "\\n - El campo '" + cNombre + "' solo permite valores decimales.";
        }
        if (Tipo == 5){
           if(!fValFecha(cValor.value))
             cVMsg1 = "\\n - El campo '" + cNombre + "' solo permite valores de tipo fecha (dd/mm/aaaa).";
        }
     }
     else{
       if(lMandato){
         cVMsg1 = "\\n - El campo '" + cNombre + "' es Obligatorio, favor de introducir su valor.";
       }
     }

     return cVMsg1;
  }

  function fSalir(form, window, cPagina){
    alert('Acceso incorrecto, favor de registrarse nuevamente...');
    if(window.parent.parent.FRMTitulo){
       window.parent.parent.FRMTitulo.fSalir();
    }else{
       form.action = 'pg' + cPagina + '00000.jsp';
       form.target = '_top';
       form.submit();
    }
  }

  function fSubmitForm(theButton){
    var form = document.forms[0];
    form.hdBoton.value = theButton;
    if(form.hdBoton.value == 'Imprimir')
      fImprimir();
    else{
      form.target="_self";
      if (window.fValidaTodo)
        lSubmitir = fValidaTodo();
      else
        lSubmitir = true;
      if (lSubmitir){
        form.submit();
      }else
        form.hdBoton.value = "";
    }
  }

  function fImprimir(){
    top.FRMCuerpo.FRMDatos.focus();
    top.FRMCuerpo.FRMDatos.print();
  }

  function fOutField() {
    top.status=""; 
  }
  function fOverField(iTooltip) {
    if (arrayTooltip[iTooltip] != "") {
      top.status=arrayTooltip[iTooltip];
    }
  }


  function fSetModal(){
    if(window.parent.parent.FRMCuerpo){
      wp = window.parent;
      wpp = wp.parent;
      wppcf = wpp.FRMCuerpo.FRMFiltro;
      if (wppcf.FRMBusqueda){
         wppcf.FRMBusqueda.setwExp(wExp);
      }
      if (wppcf.FRMBotones){
         wppcf.FRMBotones.setwExp(wExp);
      }
      if (wp.FRMUpdPanel){
         wp.FRMUpdPanel.setwExp(wExp);
      }
      if (wp.FRMNavPanel.cambiaEstado){
        wp.FRMNavPanel.setwExp(wExp);
      }
      if (wp.FRMOtroPanel){
        wp.FRMOtroPanel.setwExp(wExp);
      }
      if(wpp.FRMTitulo){
       wpp.FRMTitulo.setwExp(wExp);
      }
    }
  }

  function HandleFocus(){
    if (wExp){
      if (!wExp.closed){
        wExp.focus()
      }
      else{
        window.onclick="";
        window.onfocus="";
      }
    }
    return false
  }

  function fFillSelect(vSelect, aVar, iCveSel){     
      sIndex = 0;
      vSelect.length = aVar.length;
      for(i=0;i<aVar.length;i++){
        aTmp = aVar[i];
        vSelect[i].text  = aTmp[1];
        vSelect[i].value = aTmp[0];
        if((''+iCveSel) == (''+aTmp[0]))
          sIndex = i;
      }
      vSelect.selectedIndex = sIndex;
  }

  function fSubOculto(cPagina, cBusca,cLlave){
    if(window.parent.FRMOtroPanel){
       wpgen = window.parent.FRMOtroPanel;
       wpgen.fGen(cPagina,cBusca,cLlave);
    }
  }

  function ChangeAllCheck(doc, Checked, cSubNombre){
     form = doc.forms[0];
     for (i = 0; i < form.length; i++){
        str = form.elements[i].name;
        if (str.substring(0,cSubNombre.length)== cSubNombre)
           form.elements[i].checked = Checked;
     }
  }

  function ChangeAllSelect(doc, Index, cSubNombre){
     form = doc.forms[0];
     for (i = 0; i < form.length; i++){
        str = form.elements[i].name;
        if (str.substring(0,cSubNombre.length)==cSubNombre){
          if (form.elements[i].type.substring(0,6) == "select")
             form.elements[i].selectedIndex = Index;
        }
     }
  }
