function fIdentificar(Personal){
  if(window.opener.fSelected){
     window.opener.fSelected(Personal);
     window.close();
  }else{
     alert('El módulo ya no se encuentra disponible.'); 
     window.close();
  }
}
