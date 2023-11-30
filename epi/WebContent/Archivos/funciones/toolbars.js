  var wEOpen; 

  function setwExp(wOpen){
    wEOpen = wOpen;
    window.onclick=HandleThisFocus 
    window.onfocus=HandleThisFocus
  }

  function HandleThisFocus(){
    if (wEOpen){
      if (!wEOpen.closed){
        wEOpen.focus()
      }
      else{
        window.onclick="";
        window.onfocus="";
      }
    }
    return false
  }

  function getIEVersion(){
    var ua = navigator.userAgent
    var IEOffset = ua.indexOf('MSIE ')
    return parseFloat(ua.substring(IEOffset + 5, ua.indexOf(';', IEOffset)))
  }

  function fVerLocalImages(cLocal) {
    try{
      fsObj = new ActiveXObject("Scripting.FileSystemObject")
      return fsObj.FolderExists(cLocal);
    }catch(e){
      return false;
    }
  }

  function fAsignaImg(cValor,cLocal) {
    for(l=0;l<document.images.length;l++){
       img = document.images[l];
       cImg = img.src+'/';
       cImg = fEntry(fNumEntries(cImg,"/"),cImg,"/");
       if(navigator.appName == 'Microsoft Internet Explorer' && parseInt(getIEVersion()) > 4 && fVerLocalImages(cLocal)){
          img.src = cLocal + cImg;
       }else{
          img.src = cValor + cImg;
       }
    }
  }

  function fVerRutaImg(cValor,cLocal) {
    cSuccess = cValor;
    if(navigator.appName == 'Microsoft Internet Explorer' && parseInt(getIEVersion()) > 4 && fVerLocalImages(cLocal)){
      cSuccess = cLocal;
    }else{
      cSuccess = cValor;
    }
    return cSuccess;
  }

//  var msgcder="No es posible acceder al sistema a trav�s de este bot�n del mouse."
//  function click(e) { 3.0
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


