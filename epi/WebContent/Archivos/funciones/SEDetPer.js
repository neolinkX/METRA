function fIdentificar(Personal){
  if(window.opener.fSelected){
     window.opener.fSelected(Personal);
     window.close();
  }else{
     alert('El m�dulo ya no se encuentra disponible.'); 
     window.close();
  }
}
