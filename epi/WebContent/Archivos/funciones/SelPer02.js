  var wExp;
  function fSelPer(iTipo){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       if(iTipo){
         if(iTipo != '')
           cInicio = "?hdTipo=" + iTipo;
       }
       wExp = open("SEPer02.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=550,height=300,screenX=800,screenY=600');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }
