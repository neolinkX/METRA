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
            ' \n \n Existió una inconsistencia en la obtención de datos del servidor.'+
            ' \n La referencia se muestra en la página desplegada atrás de este mensaje.'+
            ' \n \n Al cerrar este mensaje (dar click en el botón ACEPTAR) el sistema '+
            ' \n "REESTABLECERÁ LA CONEXIÓN" en 5 segundos, después de ello, '+
            ' \n \n "CONTINÚE" con la última acción que realizó.');
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
