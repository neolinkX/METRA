function fIdentificar2(iCveEmpresa,cNombreRS){
  if(window.opener.fEmpSelected){
     window.opener.fEmpSelected(iCveEmpresa, cNombreRS);
     window.close();
  }else{
     alert('El módulo ya no se encuentra disponible.'); 
     window.close();
  }
}



function Validar(form){
    var form = document.forms[0]; 
form.submit();
}   