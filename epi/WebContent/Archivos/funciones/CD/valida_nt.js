  function fReemplazar(Valor){
    var cComillaS = String.fromCharCode(39),
        cComillaD = String.fromCharCode(34),
        cRetorno1 = String.fromCharCode(10),
        cRetorno2 = String.fromCharCode(13),
        cRetorno3 = String.fromCharCode(12),
        cTabula   = String.fromCharCode(9),
        cCaracter = "",
        cFinal    = "";
    for (var i = 0; i <= Valor.length; i++){
      cCaracter = Valor.substring(i, i+1);
      if (cCaracter != cComillaD &&
          cCaracter != cRetorno1 && cCaracter != cRetorno2 && cCaracter != cRetorno3 &&
          cCaracter != cTabula &&
		  cCaracter != cComillaS)
        cFinal+= Valor.substring(i, i+1);
      /* if (cCaracter == cComillaD)
        cFinal+=cComillaS; */
    }
  return cFinal;
  }

/* Funci�n para convertir a may�sculas el valor de un campo
   Par�metros: campoFil -- nombre del campo a convertir(this) */
function fMayus(campoFil){
  campoFil.value = fReemplazar(fTrim(campoFil.value.toUpperCase()));
}
function fSinMayus(campoFil){
  campoFil.value = fTrim(fReemplazar(campoFil.value));
}
function fMayusNoReplace(campoFil){
  campoFil.value = fTrim(campoFil.value.toUpperCase());
}
/* Funci�n para localizar un caracter espec�fico dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar el caracter
               cVCaract -- Caracter a buscar
   Valor que regresa:   true  -- Si lo localiza
                        false -- Si no lo encuentra */
function fEncCaract(cVCadena, cVCaract){
   if (cVCadena.indexOf(cVCaract) != -1)
      return true;
   else
      return false;
}
/* Funci�n para localizar una coma
   Par�metros: cVCadena -- Cadena en la cual se va a localizar la coma
   Valor que regresa:   true  -- Si lo localiza
                        false -- Si no lo encuentra */
function fComa(cVCadena){
   if (fEncCaract(cVCadena.toUpperCase(),","))
      return true;
   else
      return false;
}
/* Funci�n para localizar un gui�n bajo
   Par�metros: cVCadena -- Cadena en la cual se va a localizar el gui�n bajo
   Valor que regresa:   true  -- Si lo localiza
                        false -- Si no lo encuentra */
function fGuionBajo(cVCadena){
   if (fEncCaract(cVCadena.toUpperCase(),"_"))
      return true;
   else
      return false;
}
/* Funci�n para localizar un espacio
   Par�metros: cVCadena -- Cadena en la cual se va a localizar el espacio
               cVCaract -- Caracter a buscar
   Valor que regresa:   true  -- Si lo localiza
                        false -- Si no lo encuentra */
function fEspacio(cVCadena){
   if (fEncCaract(cVCadena.toUpperCase()," "))
      return true;
   else
      return false;
}
/* Funci�n para localizar n�meros dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar que exista un n�mero
   Valor que Regresa: true  -- Si localiza por lo menos un n�mero
                      false -- Si no localiza ning�n n�mero  */
function fNum(cVCadena){
   if ( fEncCaract(cVCadena.toUpperCase(),"1") ||
        fEncCaract(cVCadena.toUpperCase(),"2") ||
        fEncCaract(cVCadena.toUpperCase(),"3") ||
        fEncCaract(cVCadena.toUpperCase(),"4") ||
        fEncCaract(cVCadena.toUpperCase(),"5") ||
        fEncCaract(cVCadena.toUpperCase(),"6") ||
        fEncCaract(cVCadena.toUpperCase(),"7") ||
        fEncCaract(cVCadena.toUpperCase(),"8") ||
        fEncCaract(cVCadena.toUpperCase(),"9") ||
        fEncCaract(cVCadena.toUpperCase(),"0") )
       return true;
   else
       return false;
}
/* Funci�n para localizar letras dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar una letra
   Valor que Regresa: true  -- Si localiza por lo menos una letra
                      false -- Si no localiza ninguna letra  */
function fLetras(cVCadena){
   if ( fEncCaract(cVCadena.toUpperCase(),"A") ||
        fEncCaract(cVCadena.toUpperCase(),"B") ||
        fEncCaract(cVCadena.toUpperCase(),"C") ||
        fEncCaract(cVCadena.toUpperCase(),"D") ||
        fEncCaract(cVCadena.toUpperCase(),"E") ||
        fEncCaract(cVCadena.toUpperCase(),"F") ||
        fEncCaract(cVCadena.toUpperCase(),"G") ||
        fEncCaract(cVCadena.toUpperCase(),"H") ||
        fEncCaract(cVCadena.toUpperCase(),"I")  ||
        fEncCaract(cVCadena.toUpperCase(),"J")  ||
        fEncCaract(cVCadena.toUpperCase(),"K")  ||
        fEncCaract(cVCadena.toUpperCase(),"L")  ||
        fEncCaract(cVCadena.toUpperCase(),"M")  ||
        fEncCaract(cVCadena.toUpperCase(),"N")  ||
        fEncCaract(cVCadena.toUpperCase(),"�")  ||
        fEncCaract(cVCadena.toUpperCase(),"O")  ||
        fEncCaract(cVCadena.toUpperCase(),"P")  ||
        fEncCaract(cVCadena.toUpperCase(),"Q") ||
        fEncCaract(cVCadena.toUpperCase(),"R")  ||
        fEncCaract(cVCadena.toUpperCase(),"S")  ||
        fEncCaract(cVCadena.toUpperCase(),"T")  ||
        fEncCaract(cVCadena.toUpperCase(),"U")  ||
        fEncCaract(cVCadena.toUpperCase(),"V")  ||
        fEncCaract(cVCadena.toUpperCase(),"W") ||
        fEncCaract(cVCadena.toUpperCase(),"X")  ||
        fEncCaract(cVCadena.toUpperCase(),"Y")  ||
        fEncCaract(cVCadena.toUpperCase(),"Z"))
       return true;
   else
       return false;
}
/* Funci�n para localizar caracteres raros dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar un caracter raro
   Valor que Regresa: true  -- Si localiza por lo menos un caracter raro
                      false -- Si no localiza ning�n caracter raro  */
function fRaros(cVCadena){
   if (fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"%")    ||
       fEncCaract(cVCadena.toUpperCase(),"&")    ||
       fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"?")    ||
       fEncCaract(cVCadena.toUpperCase(),"|")    ||
       fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"#")    ||
       fEncCaract(cVCadena.toUpperCase(),"$")    ||
       fEncCaract(cVCadena.toUpperCase(),"=")    ||
       fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"!")    ||
       fEncCaract(cVCadena.toUpperCase(),"*")   ||
       fEncCaract(cVCadena.toUpperCase(),"{")    ||
       fEncCaract(cVCadena.toUpperCase(),"}")   ||
       fEncCaract(cVCadena.toUpperCase(),"[")    ||
       fEncCaract(cVCadena.toUpperCase(),"]")   ||
       fEncCaract(cVCadena.toUpperCase(),"<")    ||
       fEncCaract(cVCadena.toUpperCase(),">")    ||
       fEncCaract(cVCadena.toUpperCase(),"~")    ||
       fEncCaract(cVCadena.toUpperCase(),"� ")   ||
       fEncCaract(cVCadena.toUpperCase(),"'")  ||
       fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"�")   ||
       fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"�")    ||
       fEncCaract(cVCadena.toUpperCase(),"�") )
       return true;
   else
      return false;
}
/* Funci�n para localizar los caracteres + y - dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar el signo
   Valor que Regresa: true  -- Si localiza por lo menos un signo
                      false -- Si no localiza ning�n signo  */
function fSignos(cVCadena){
   if (fEncCaract(cVCadena,"+") || fEncCaract(cVCadena,"-"))
       return true;
   else
       return false;
}
/* Funci�n para localizar : y ; en una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar el signo
   Valor que Regresa: true  -- Si localiza por lo menos un signo
                      false -- Si no localiza ning�n signo  */
function fPuntuacion(cVCadena){
   if (fEncCaract(cVCadena.toUpperCase(),":") ||
       fEncCaract(cVCadena.toUpperCase(),";") )
      return true;
   else
      return false;
}
/* Funci�n para localizar la Diagonal /
   Par�metros: cVCadena -- Cadena en la cual se va a localizar la diagonal
   Valor que Regresa: true  -- Si localiza por lo menos una diagonal
                      false -- Si no localiza ning�n signo  */
function fDiagonal(cVCadena){
   if (fEncCaract(cVCadena.toUpperCase(),"/") )
      return true;
   else
      return false;
}
/* Funci�n para localizar arrobas dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar la arrobas
   Valor que Regresa: true  -- Si localiza por lo menos una arroba
                      false -- Si no localiza ninguna arroba  */
function fArroba(cVCadena){
   if (fEncCaract(cVCadena.toUpperCase(),"@"))
     return true;
   else
     return false;
}
/* Funci�n para localizar par�ntesis tanto de apertura como de cierre dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar el par�ntesis
   Valor que Regresa: true  -- Si localiza por lo menos un par�ntesis
                      false -- Si no localiza ning�n par�ntesis  */
function fParentesis(cVCadena){
   if (fEncCaract(cVCadena.toUpperCase(),"(") &&
       fEncCaract(cVCadena.toUpperCase(),")") )
      return true;
   else
      return false;
}
/* Funci�n para localizar ap�strofe dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar el ap�strofe
   Valor que Regresa: true  -- Si localiza por lo menos un ap�strofe
                      false -- Si no localiza ning�n ap�strofe  */
function fComilla(cVCadena){
   if (fEncCaract(cVCadena,"'"))
       return true;
   else
       return false;
}
/* Funci�n para localizar comas dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar la coma
   Valor que Regresa: true  -- Si localiza por lo menos una coma
                      false -- Si no localiza ninguna coma */
function fComa(cVCadena){
   if (fEncCaract(cVCadena,","))
       return true;
   else
       return false;
}
/* Funci�n para localizar puntos dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar un punto
   Valor que Regresa: true  -- Si localiza por lo menos un punto
                      false -- Si no localiza ning�n punto */
function fPunto(cVCadena){
   if (fEncCaract(cVCadena,"."))
       return true;
   else
       return false;
}
/* Funci�n para validar que una cadena solo contenga letras
   Par�metros: cVCadena -- Cadena a verificar
   Valor que Regresa: true
                      false  */
function fSoloLetras(cVCadena){
   if(cVCadena == "")
      return false;
    if ( fNum(cVCadena)          || fRaros(cVCadena)       ||
         fPuntuacion(cVCadena)   || fSignos(cVCadena)      ||
         fArroba(cVCadena)       || fParentesis(cVCadena)  ||
         fPunto(cVCadena)        || fDiagonal(cVCadena)    ||
         fGuionBajo(cVCadena)    || fComa(cVCadena))
        return false;
    else
        return true;
 }
/* Funci�n para validar que una cadena solo contenga caracteres alfanum�ricos
   Par�metros: cVCadena -- Cadena a verificar
   Valor que Regresa: true
                      false  */
function fSoloAlfanumericos(cVCadena){
    if ( fRaros(cVCadena)      || //fPuntuacion(cVCadena) ||
         fSignos(cVCadena)     || //fArroba(cVCadena)     ||
         fParentesis(cVCadena) || //fPunto(cVCadena)      ||
         fDiagonal(cVCadena)   || fComa(cVCadena))
        return false;
    else
        return true;
}
/* Funci�n para validar que una cadena solo contenga n�meros
   Par�metros: cVCadena -- Cadena a verificar
   Valor que Regresa: true
                      false  */
function fSoloNumeros(cVCadena){
    if ( fRaros(cVCadena)       || fPuntuacion(cVCadena) ||
         fSignos(cVCadena)      || fArroba(cVCadena)     ||
         fParentesis(cVCadena)  || fLetras(cVCadena)     ||
         fDiagonal(cVCadena)    || fPunto(cVCadena)      ||
         fEspacio(cVCadena)     || fGuionBajo(cVCadena)  ||
         fComa(cVCadena))
        return false;
    else
        return true;
}
/* Funci�n para validar que una cadena solo contenga n�meros con Comas
   Par�metros: cVCadena -- Cadena a verificar
   Valor que Regresa: true
                      false  */
function fSoloNumerosC(cVCadena){
    if ( fRaros(cVCadena)       || fPuntuacion(cVCadena) ||
         fSignos(cVCadena)      || fArroba(cVCadena)     ||
         fParentesis(cVCadena)  || fLetras(cVCadena)     ||
         fDiagonal(cVCadena)    || fPunto(cVCadena)      ||
         fEspacio(cVCadena)     || fGuionBajo(cVCadena)  )
        return false;
    else
        return true;
}
/* Funci�n para validar que un campo tenga un decimal
   Par�metros: cVCadena -- Cadena a verificar
   Valor que Regresa: true
                      false  */
function fDecimal(cVCadena){
   // Encontrar el punto
   iVPosP = cVCadena.indexOf('.');
   iVPosI = 0;
   // Encontrar el signo
   if (fSignos(cVCadena.substring(0,1))) iVPosI = 1;
   if (iVPosP > -1) {
      if (fSoloNumerosC(cVCadena.substring(iVPosI,iVPosP)) && fSoloNumeros(cVCadena.substring(iVPosP+1,cVCadena.length)))
         return true;
      else
         return false;
   }
   if (fSoloNumerosC(cVCadena.substring(iVPosI,cVCadena.length - 1)))
      return true;
   else
     return false;
}
/* Funci�n para validar el RFC
   Par�metros: cVCadena -- Cadena a verificar
   Valor que Regresa: true
                      false  */
function fValRFC (cVRFC,iVTipo){
   switch(iVTipo){
   case 1: iVSiglas = 4; iVMin = 10; iVTam = 13; break;
   case 2: iVSiglas = 3; iVMin = 12; iVTam = 12; break;
   }
   if (cVRFC.length < iVMin || cVRFC.length > iVTam)
      return false;
   if (cVRFC.length > iVMin && cVRFC.length < iVTam) {
         return false;
   }
   if(fSoloLetras(cVRFC.substring(0,iVSiglas))    &&
      fSoloNumeros(cVRFC.substring(iVSiglas,iVSiglas + 6))  &&
      fSoloAlfanumericos(cVRFC.substring(iVSiglas + 6, iVTam)) && !fEspacio(cVRFC)  )
     return true;
   else
     return false;
}
/* Funci�n para validar la Fecha
   Par�metros: cVCadena -- Cadena a verificar
   Valor que Regresa: true
                      false  */
function fValFecha(cVCadena,lMsg){
  if(lMsg != false) lMsg=true;
  if (   (fRaros(cVCadena)        || fPuntuacion(cVCadena) ||
          fSignos(cVCadena)       || fArroba(cVCadena)     ||
          fParentesis(cVCadena)   || fLetras(cVCadena)     ||
          fEspacio(cVCadena)      || fComa(cVCadena))      ||
          fGuionBajo(cVCadena)   ) {
     lMsg==true?alert('La informaci�n no es correcta.\nFavor de verificar el formato de la fecha: dd/mm/aaaa'):lMsg=false;
     return false;
  }
  if (cVCadena.length != 10) {
     lMsg==true?alert('Favor de verificar el formato de la fecha: dd/mm/aaaa.\n'):lMsg=false;
     return false;
  }
  if (!fDiagonal(cVCadena)){
     lMsg==true?alert('Favor de verificar el formato de la fecha: dd/mm/aaaa.\n' + cVCompM):lMsg=false;
     return false;
  }
   // Barrer la cadena
  iVPos = 0;
  var iVPDiag = new Array();
  for(var iVCont = 0; iVCont < cVCadena.length; iVCont ++){
     if(cVCadena.charAt(iVCont) == "/")
        iVPDiag[iVPos++] = iVCont;
  }
  if (iVPos != 2) {
    lMsg==true?alert('Favor de verificar el formato de la fecha: dd/mm/aaaa\n' + cVCompM):lMsg=false;
     return false;
  }
  cVDia   = cVCadena.substring(0,iVPDiag[0]);
  cVMes  = cVCadena.substring(iVPDiag[0] + 1,iVPDiag[1]);
  cVAnio  = cVCadena.substring(iVPDiag[1] + 1,cVCadena.length);
  if(parseInt(cVMes,10) < 1 || parseInt(cVMes,10) > 12) {
     lMsg==true?alert('El Mes no es v�lido.\nDebe ser un n�mero entre 1 y 12'):lMsg=false;
     return false;
  }
  switch (parseInt(cVMes,10)){
  case  1:
  case  3:
  case  5:
  case  7:
  case  8:
  case 10:
  case 12:
           if(parseInt(cVDia,10) < 1 || parseInt(cVDia,10) > 31) {
                     lMsg==true?alert('El D�a no es v�lido.\nDebe ser un n�mero entre 1 y 31'):lMsg=false;
                     return false;
                          }
                break;
    case 2:	if(parseInt(cVDia,10) < 1 || parseInt(cVDia,10) > 29) {
                     lMsg==true?alert('El D�a no es v�lido.\nDebe ser un n�mero entre 1 y 29'):lMsg=false;
                     return false;
                          }
                break;
   default :            if(parseInt(cVDia,10) < 1 || parseInt(cVDia,10) > 30) {
                     lMsg==true?alert('El D�a no es v�lido.\nDebe ser un n�mero entre 1 y 30'):lMsg=false;
                     return false;
                          }
   }
  return true;
}
function fTelefono(cVCampo){
  return true;
}
function fEMail(cVCampo){
  return true;
}
/* Funci�n para localizar un Espacio en Blanco al Inicio de la Cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar el espacio en Blanco
   Valor que regresa:   true  -- Si lo localiza
                        false -- Si no lo encuentra */
function fEspBlanco(cVCadena){
   if (cVCadena.indexOf(' ') == 0)
      return true;
   else
      return false;
}
/* Funci�n para mostrar los caracteres restantes de un campo de captura en otro campo con una longitud maxima el exceso es truncado */
  function fDespRestantes(fTextArea,fCampoDesp,iMaxLen){
    cText = fTextArea.value;
    if(cText.length > iMaxLen)
      fTextArea.value = cText = cText.substring(0,iMaxLen);
    fCampoDesp.value = iMaxLen - cText.length;
  }
/* Funci�n que calcula el restante de una �rea texto */
  function fMxTx(oTexto,iMax){
    iMax = iMax - 1;
    if(oTexto.value.length <= iMax){
      for(i=0;i<frm.elements.length;i++){
        if(frm.elements[i].name=="AuxTxt"+oTexto.name)
           frm.elements[i].value=iMax-oTexto.value.length;
        if(frm.elements[i].name.substring(0,6)=="AuxTxt")
           frm.elements[i].disabled=true;
      }
    }else{
      oTexto.value=oTexto.value.substring(0,iMax);
    }
  }
/* Funci�n que valida el formato de hora */
function fValHora(cTexto){
  if(cTexto.indexOf(":") != -1){
    cHora = cTexto.substring(0,cTexto.indexOf(":"));
    cMin =  cTexto.substring(cTexto.indexOf(":")+1);
    if(cMin.length < 2)
      return false;
    if(cHora.length < 2)
      return false;
    if(parseInt(cHora,10) > 23)
      return false;
    if(parseInt(cMin,10) > 59)
      return false;
    return true;
  }
  return false;
}
/* Funci�n de Entero a formato de hora */
function fIntToHora(cTexto){
   iTmp = parseInt(cTexto,10);
   cHora = "";
   cMin = "";
   if(iTmp < 2400){
     cTxt = ""+iTmp;
     if(iTmp == 0)
       return "0:00";
     if(iTmp < 1000){
       cHora = cTxt.substring(0,1);
       cMin = cTxt.substring(1);
     }else{
       cHora = cTxt.substring(0,2);
       cMin = cTxt.substring(2);
     }
     if(parseInt(cMin,10) > 59){
       cMin = "";
       cHora = "";
     }else
       return cHora + ":" + cMin;
   }
   return "";
}
/* Funci�n de Hora a Entero */
function fHoraToInt(cTexto){
  if(cTexto.indexOf(":") != -1){
    iHora = parseInt(cTexto.substring(0,cTexto.indexOf(":")),10);
    iMin =  parseInt(cTexto.substring(cTexto.indexOf(":")+1),10);
    if(iHora > 23)
      return 0;
    if(iMin > 59)
      return 0;
    return parseInt(""+iHora+""+cTexto.substring(cTexto.indexOf(":")+1),10);
  }
  return 0;
}
/* Funci�n que regresa la hora (int) de una cadena en formato HH:MM */
function fGetHora(cTexto){
  return parseInt(cTexto.substring(0,cTexto.indexOf(":")),10);
}
/* Funci�n que regresa los minutos (int) de una cadena en formato HH:MM */
function fGetMinutos(cTexto){
  return parseInt(cTexto.substring(cTexto.indexOf(":")+1),10);
}

/* Funci�n que elimina espacios del principio y fin de una cadena */
function fTrim(cadena){
  for(i=0; i<cadena.length; ){
    if(cadena.charAt(i)==" ")
      cadena=cadena.substring(i+1, cadena.length);
    else
      break;
  }
  for(i=cadena.length-1; i>=0; i=cadena.length-1){
    if(cadena.charAt(i)==" ")
      cadena=cadena.substring(0,i);
    else
      break;
  }
  return cadena;
}

function fAsignaBreve(oDescripcion, oBreve){
	oBreve.value = (oBreve.value.length == 0) ?(parseInt(oBreve.size,10) < (parseInt(oDescripcion.value.length,10)) ?   oDescripcion.value.substring(0,oBreve.size) : oDescripcion.value) :  oBreve.value  ;
}
