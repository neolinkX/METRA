function fGuardar(num) {
   form = document.forms[0];
   form.target = "_self";
   var pasa = true;
   var tienevalor = false;
   var msg = "Error: \n";
   var i = 0;

 //     alert(form.dPorcentaje[i].value);
   for(i = 0; i < num; i++) {
//      x=eval("document.forms[0].dPorcentaje"+i);
//      alert(x);
//        alert(form..value);
       alert(form.dPorcentaje[i].value);
   }
}

function fPasaValor(obj, val) {
    obj.value = val;
}

function fShow(){
	var forma=document.forms[0];
	var elems=forma.elements;
	for(var i=0;i<elems.length;i++){
//		elems[i].value=i;
		alert(elems[i].value);
	}
}
