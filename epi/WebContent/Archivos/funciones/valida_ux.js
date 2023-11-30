

/* ****************************************************

       LIBRERIA DE VALIDACIONES 

      TECNOLOG�A INRED

      SEPTIEMBRE 2002

   ***************************************************** */





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

    if ( fRaros(cVCadena)      || fPuntuacion(cVCadena) || 

         fSignos(cVCadena)     || fArroba(cVCadena)     || 

         fParentesis(cVCadena) || fPunto(cVCadena)      || 

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

function fValFecha(cVCadena){

  if (   (fRaros(cVCadena)        || fPuntuacion(cVCadena) || 

          fSignos(cVCadena)       || fArroba(cVCadena)     || 

          fParentesis(cVCadena)   || fLetras(cVCadena)     ||

          fEspacio(cVCadena)      || fComa(cVCadena))      ||

          fGuionBajo(cVCadena)   ) {

     alert('La informaci�n no es correcta.\\nFavor de verificar el formato de la fecha: dd/mm/aaaa');

     return false;

  }  

  if (cVCadena.length != 10) {

     return false;

  }

  if (!fDiagonal(cVCadena)){

     alert('Favor de verificar el formato de la fecha: dd/mm/aaaa.\\n' + cVCompM);

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

    alert('Favor de verificar el formato de la fecha: dd/mm/aaaa\\n' + cVCompM);

     return false;

  } 

  

  cVDia   = cVCadena.substring(0,iVPDiag[0]);

  cVMes  = cVCadena.substring(iVPDiag[0] + 1,iVPDiag[1]);

  cVAnio  = cVCadena.substring(iVPDiag[1] + 1,cVCadena.length);

  if(parseInt(cVMes,10) < 1 || parseInt(cVMes,10) > 12) {

     alert('El Mes no es v�lido.\\nDebe ser un n�mero entre 1 y 12');

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

   		     alert('El D�a no es v�lido.\\nDebe ser un n�mero entre 1 y 31');

		     return false;

                          }                           

		break;             

    case 2:	if(parseInt(cVDia,10) < 1 || parseInt(cVDia,10) > 29) {

   		     alert('El D�a no es v�lido.\\nDebe ser un n�mero entre 1 y 29');

		     return false;

                          }                           

		break;             

   default :            if(parseInt(cVDia,10) < 1 || parseInt(cVDia,10) > 30) {

   		     alert('El D�a no es v�lido.\\nDebe ser un n�mero entre 1 y 30');

		     return false;

                          }                           

   }       

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

//  var msgcder="No es posible acceder al sistema a trav�s de este bot�n del mouse."
//  function click(e) { //3.0
//    try{
//      if (document.all) {
//        if (event.button == 2 || event.button == 3 || event.button == 6 || event.button == 7) {
//          alert(msgcder);
//          return false;
//        }
//      }
//      if (document.layers) {
//        if (e.which == 3) {
//          alert(msgcder);
//          return false;
//        }
//      }
//    }catch(w){}
//  }
//  document.onmousedown=click;


/* Funci�n para eliminar los espacios en blanco al principio y al final de una palabra 
   19/01/2006
   Itzia Mar�a del C. S�nchez M�ndez
   Jesus Parra (jparra@programmer.net)
*/

function fTrim(cadena)
{
	for(i=0; i<cadena.length; )
	{
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(i+1, cadena.length);
		else
			break;
	}

	for(i=cadena.length-1; i>=0; i=cadena.length-1)
	{
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(0,i);
		else
			break;
	}	
	return cadena;
}
