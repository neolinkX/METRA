  var wExp;
  function fSelEmp(){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       wExp = open("SEEmp01.jsp","Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=300,screenX=800,screenY=600');
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }