var iPID;
function fPag(){
  document.write('<html><body onLoad="fInicia();"><form method="POST"></FORM></body></HTML>');
}
function fInicia(){
  if(lDesarrollo)
     iPID = setInterval('fVerAnswer();',60000);
  else
     iPID = setInterval('fVerAnswer();',60000);
}
function fVerAnswer(){
    if(""+top.FRMMI.fPag == "undefined"){
      clearInterval(iPID);
      top.document.body.rows="100%,0,0%";
      top.fEndThreat();
      top.fDelThreats();
      top.fLoadIneng();
      alert(' Esto NO es un ERROR. '+
            ' \n \n Existi� una inconsistencia en la obtenci�n de datos del servidor.'+
            ' \n La referencia se muestra en la p�gina desplegada atr�s de este mensaje.'+
            ' \n \n Al cerrar este mensaje (dar click en el bot�n ACEPTAR) el sistema '+
            ' \n "REESTABLECER� LA CONEXI�N" en 5 segundos, despu�s de ello, '+
            ' \n \n "CONTIN�E" con la �ltima acci�n que realiz�.');
      iPID = setInterval('fVerAnswer();',4000);
    }else{
      if(lDesarrollo)
         top.document.body.rows="1,0,100%";
      else
         top.document.body.rows="0,0,100%";
      clearInterval(iPID);
      fInicia();
    }
}
function fGetRutaProg(){
  return cRutaProg;
}
