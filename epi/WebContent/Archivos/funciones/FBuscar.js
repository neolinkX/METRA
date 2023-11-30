     
     function fCatalogo(forma,pagina,rwId){
        forma.hdIni.value = rwId;
        forma.action = pagina;
        forma.submit();
     }
     
     function fCompleta(forma, valor){
          var x=forma.CMBOperador.length;
          var z=forma.CMBOperador.selectedIndex;
          if (valor != x) {
             switch (valor){
             case 7:    if (x > 7){
                          for(i=x; i > 7; i--){
                             forma.CMBOperador.options[i-1] = null;
                          }
                        }
                break;

                case 8: if( x > 8){
                        forma.CMBOperador.options[8] = null;
                        }
                        if( x < 8){
                           var opcion = new Option("Inicie", "Inicie");
                           forma.CMBOperador.options[7] = opcion;
                        }
                break;
                case 9: if( x < 8){
                           var opcion = new Option("Inicie", "Inicie");
                           forma.CMBOperador.options[7] = opcion;
                           x++;
                        }
                        if( x < 9){
                           var opcion = new Option("Contenga", "Contenga");
                           forma.CMBOperador.options[8] = opcion;
                        }
                break;
             }
          }
          return true;
         }

     function fValida(forma, valor){
       switch (valor){
          case 7 :
               if(fSoloNumeros(forma.FILBuscar.value))
                  return true;
               else
                  alert('Por favor verifique el criterio de búsqueda');
                return false;
          break;
          case 8 :
                if(fSoloAlfanumerico(forma.FILBuscar.value)) 
                   return true;
                else
                   alert('Por favor verifique el criterio de búsqueda');
                return false;
          break;
          case 9 :
               return true; 
          break;
       }
             
        

     }

     function fValidaTodo(){
            if ((document.forms[0].CMBOperador.selectedIndex != 0) && (document.forms[0].FILBuscar.value == '')){
               alert('No se ingresó el valor del criterio de búsqueda.');
               return false;
            }
            if ((document.forms[0].CMBOperador.selectedIndex == 0) && (document.forms[0].FILBuscar.value != '')){
               alert('No se seleccionó un criterio de búsqueda.');
               return false;
            }
            if (fVerOperador(document.forms[0], 2))
               return true;
         }



