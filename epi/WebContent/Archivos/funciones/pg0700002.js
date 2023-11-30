function fPag1(){
    var cp = '';
    cp=cp+'<html><head><SCRIPT LANGUAGE="JavaScript" SRC="'+cRutaFuncs+'toolbars.js"></SCRIPT>';
    cp=cp+'<SCRIPT LANGUAGE="JavaScript" SRC="'+cRutaFuncs+'valida_nt.js"></SCRIPT>';
    cp=cp+'<SCRIPT language="JavaScript" src="'+cRutaFuncs+'t07_Tooltips.js"></SCRIPT>';
    cp=cp+'<script type="text/javascript" src="'+cRutaFuncs+'swfobject.js"></script></head>';
    cp=cp+'<link rel="stylesheet" href="'+Style+'" TYPE="text/css">';
    cp=cp+'<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">';
    cp=cp+'     <form method="POST" action="pg0700002.jsp">';
    cp=cp+'         <input type="hidden" name="hdBoton" value="">';
    cp=cp+'         <table border="0" align="center">';
    cp=cp+'             <tr>';
    cp=cp+'                 <td width="623" style="background: url('+cRutaImgServer+'celda1.jpg)">';
    cp=cp+'                 <br /><br /><br /><br />';
    cp=cp+'                 &nbsp;&nbsp;&nbsp;<input type="text" size="17" maxlength="25" name="FILUsuario" value="'+cEntrada+'"; onfocus="this.select();" onMouseOut="if (window.fOutField) window.fOutField();" onMouseOver="if (window.fOverField) window.fOverField(1);" />';
    cp=cp+'                 &nbsp;&nbsp;<input type="password" size="17" maxlength="15" name="FILContrasena" value=""; onfocus="this.select();" onKeyPress="fCheckReturn(event);" onMouseOut="if (window.fOutField) window.fOutField();" onMouseOver="if (window.fOverField) window.fOverField(2);" />';
    cp=cp+'                 &nbsp;&nbsp;<input type="password" size="17" maxlength="12" name="FILClues" value=""; onfocus="this.select();" onKeyPress="fCheckReturn(event);" onMouseOut="if (window.fOutField) window.fOutField();" onMouseOver="if (window.fOverField) window.fOverField(3);" />';
    cp=cp+'                 &nbsp;&nbsp;&nbsp;<a href="JavaScript:if (window.fSubmitForm) window.fSubmitForm('+"'"+'Aceptar'+"'"+')"';
    cp=cp+"                 onMouseOut="+'"'+"if(top.fCambiaImagen)top.fCambiaImagen(document, 'BtnAceptar','','"+cRutaImgServer+"boton1.jpg',1);self.status='';return true;" +'"';
    cp=cp+"                 onMouseOver=" + '"' + "if(top.fCambiaImagen)top.fCambiaImagen(document, 'BtnAceptar','','"+cRutaImgServer+"boton1.jpg',1);self.status='Ingresar al sistema';return true;"+'"'+'>  <img name="BtnAceptar" border="0" src="'+cRutaImgServer+'boton1.jpg"></a>';
    cp=cp+'                 &nbsp;&nbsp;&nbsp;<a href="JavaScript:document.forms[0].reset();" ';
    cp=cp+"                 onMouseOut="+'"' + "if(top.fCambiaImagen)top.fCambiaImagen(document, 'BtnLimpiar','','"+cRutaImgServer+"boton2.jpg',1);self.status='';return true;"+'"';
    cp=cp+"                 onMouseOver="+'"'+"if(top.fCambiaImagen)top.fCambiaImagen(document, 'BtnLimpiar','','"+cRutaImgServer+"boton2.jpg',1);self.status='Restaurar datos';return true;"+'"';
    cp=cp+'                 onClick="">  <img name="BtnLimpiar" border="0" src="'+cRutaImgServer+'boton2.jpg"></a></td>';
    cp=cp+'                 <td><img src="'+cRutaImgServer+'lsctPortada.jpg" /></td>';
    cp=cp+'             </tr>';
    cp=cp+'             <tr>';
    cp=cp+'                 <td colspan="2"><input name="msg" size="1000" type="hidden" value="" /> ';

    //cp=cp+'                 <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0"  width="800" height="484"id="contenido" align="middle">';
    //cp=cp+'                 <param name="allowScriptAccess" value="sameDomain" />';
    //cp=cp+'                 <param name="movie" value="'+cRutaImgServer+'contenido.swf" />';
    //cp=cp+'                 <param name="quality" value="high" />';
    //cp=cp+'                 <param name="bgcolor" value="#ffffff" />';
    //cp=cp+'                 <embed src="'+cRutaImgServer+'contenido.swf" quality="high" bgcolor="#ffffff"  width="800" height="484" name="contenido" align="middle" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />';
    //cp=cp+'                 </object>';

    //cp=cp+'<div id="flashcontent">Es necesario tener el plugin de flash instalado</div>';
    //cp=cp+'<script type="text/javascript">';
    //var cont = new SWFObject(cRutaImgServer+"contenido.swf", "contenido", "800", "484", "7", "#ffffff");
    //cont.write(flashcontent);
    //cp=cp+'</script>';

    document.write(cp);
}

function fPag2() {
    var cp='';
    cp=cp+'                 </td>';
    cp=cp+'             </tr>';
    //cp=cp+'             <tr>';
    //cp=cp+'                 <td>Mensajes de la SCT</td>';
    //cp=cp+'             </tr>';
    cp=cp+'         </table>';
    cp=cp+'     </form>';
    cp=cp+'</body>';
    cp=cp+'<SCRIPT LANGUAGE="JavaScript">';
    cp=cp+'    if (top.fPreCargarImagen){';
    cp=cp+'      top.fPreCargarImagen(document,"'+cRutaImgServer+'boton1.jpg");';
    cp=cp+'      top.fPreCargarImagen(document,"'+cRutaImgServer+'boton1.jpg");';
    cp=cp+'      top.fPreCargarImagen(document,"'+cRutaImgServer+'boton2.jpg");';
    cp=cp+'      top.fPreCargarImagen(document,"'+cRutaImgServer+'boton2.jpg");';
    cp=cp+'    }';
    cp=cp+'</SCRIPT>';
    cp=cp+'<html>';

    document.write(cp);
}


function fValidaTodo(){
    if(document.forms[0].FILUsuario.value==""){
        alert("Favor de ingresar el nombre de usuario");
        document.forms[0].FILUsuario.focus();
        return false;
    }
    if(document.forms[0].FILContrasena.value==""){
        alert("Favor de ingresar la contraseña de acceso");
        document.forms[0].FILContrasena.focus();
        return false;
    }
    if(document.forms[0].FILClues.value==""){
        alert("Favor de ingresar la CLUES de acceso");
        document.forms[0].FILClues.focus();
        return false;
    }
    return true;
}

function fOnLoad(){
    top.document.title = titulo;
    form = document.forms[0];
    fAsignaImg(cRutaImgServer,cRutaImgLocal);
    window.defaultStatus = Status;
    if (top.FRMTitulo){
        if (top.FRMTitulo.asignaAyuda)
            resultado = top.FRMTitulo.asignaAyuda(Programa);
        if (!resultado){
            for (var i = 0; i < 50; i++);
            if (top.FRMTitulo.asignaAyuda)
                resultado = top.FRMTitulo.asignaAyuda(Programa);
        }
    }
    if (screen.width < 800 && screen.height < 600)
        alert ("Este sistema se ve mejor empleando resolución de 800x600 como mínimo.");
    if(form.FILUsuario)
        form.FILUsuario.focus();
}



function fCheckReturn(evt){
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if(charCode == 13){
        if (window.fSubmitForm)
            window.fSubmitForm('Aceptar')
    }
}

var newwindow;
function poptastic(url)
{
	newwindow=window.open(url,'CLUES','height=300,width=650');
	if (window.focus) {newwindow.focus()}
}


function fSubmitForm(theButton) {
    document.forms[0].hdBoton.value = theButton;
    document.forms[0].target="_self";
    if (window.fValidaTodo)
        lSubmitir = fValidaTodo();
    else
        lSubmitir = true;
    if (lSubmitir){
        //poptastic('./solicitaCLUES.jsp?usuario='+document.forms[0].FILUsuario.value);
        document.forms[0].submit();
    }else{
        document.forms[0].hdBoton.value = "";
    }
}
function fCLUESInvalidas(){
    alert('La CLUES ingresada es invalidas');
    document.forms[0].hdBoton.value = "";
}
function fUSUARIOInvalido(){
    alert('Las USUARIO ingresado NO existe');
    document.forms[0].hdBoton.value = "";
}

function   fCLUESValidas(){
    document.forms[0].submit();
}
