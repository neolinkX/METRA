	//// @author Lic. AG SA L////////////

		  function lee_json(op) {
			  var puerto;
			  var DatosAdicionales;
			  var req = new XMLHttpRequest();
				req.open('POST', 'http://127.0.0.1:9233/WelchAllyn/Device/GetDevices?deviceid', false); 
				req.send(null);
			    //alert(req.responseText);
			    var dispositivo = JSON.parse(req.responseText);
			    puerto = dispositivo[0].deviceid;		
			    DatosAdicionales = "Modelo: "+dispositivo[0].modelname+"<br>N\u00FAmero de Modelo: "+dispositivo[0].modelnumber+"<br>N\u00FAmero de Serie: "+dispositivo[0].serialnumber;
				req.open('POST', 'http://127.0.0.1:9233/WelchAllyn/Device/GetReadings?deviceid='+puerto, false); 				
				req.send(null);
			    var signos = JSON.parse(req.responseText);
			    //alert(signos);
			    if(signos == null){
			    	alert("Favor de realizar una Lectura de Signos Vitales desde Spot Vital Signs LXi y aseg\u00FArese de guardarlo en Memoria");
			    }
				
			    
			    
				if(req.responseText==null || req.responseText=='null'){
					//alert(form.Hoy.value);
				}
				else{
					var LongMem = signos.length-1;
					//alert(LongMem);
					DatosAdicionales= DatosAdicionales+"<br>Fecha y Hora de Lectura: "+signos[LongMem].date;
					var fecha = signos[LongMem].date.replace('-','/');				
					var diastolica = signos[LongMem].diastolic;
					var sistolica = signos[LongMem].systolic;
					var temperatura = signos[LongMem].temperature;
					var pulso = signos[LongMem].pulse;
					var peso = signos[LongMem].weight;
					var estatura = signos[LongMem].height;																
					form = document.forms[0];
					var fechaD = fecha.replace('-','/');
					fechaD = fecha.replace("-", "/");
					var fechaD2 = fechaD.split("T",1);
					var comparaFecha = fn_DateCompare(form.Hoy.value,fechaD2);
				    //alert(comparaFecha);
				    if(comparaFecha=='0'){
				    	if(diastolica=='null' || diastolica==null ||
				    	   sistolica=='null' || sistolica==null ||
				    	   diastolica=='null' || diastolica==null ||
				    	   pulso=='null' || pulso==null){
				    		alert("Importaci\u00F3n fallida! \n\n *Verifique que el Spot Vital Signs LXi no se encuentra leyendo los datos de la memoria \n\n *Verifique que no hizo falta capturar en la última lectura de Signos Vitales la Temperatura, la Frecuencia Cardiaca o la Tensión Arterial");
				    	}else{
					    	//form.fechaS.value = fecha;
							//alert(form.Hoy.value);
							//form.diastolica.value = diastolica;
				    		//alert(diastolica);
				    		//alert(parseFloat(diastolica.replace(',','.')));
							form.dValorI_111.value = Math.round(parseFloat(diastolica.replace(',','.')));
							//form.sistolica.value = sistolica;
							//alert(sistolica);
				    		//alert(parseFloat(sistolica.replace(',','.')));
							form.dValorI_112.value = Math.round(parseFloat(sistolica.replace(',','.')));
							//form.temperatura.value = Math.round(temperatura * 10) /10;
							//alert(temperatura.replace(',','.'));
				    		//alert(parseFloat(temperatura.replace(',','.')));
							form.dValorI_15.value =  Math.round(parseFloat(temperatura.replace(',','.')) * 10) /10;
							//form.pulso.value = pulso;
							//alert(pulso);
				    		//alert(parseFloat(pulso.replace(',','.')));
							form.dValorI_115.value = Math.round(parseFloat(pulso.replace(',','.')));
							//alert(DatosAdicionales);
							form.DatosAdicionales.value =DatosAdicionales;
							//form.peso.value = peso;
							//form.estatura.value = estatura;
							if(op=='1'){
								alert("Se han importado los datos que corresponden a la \u00FAltima lectura de Signos Vitales guardada en la memoria del Spot Vital Signs LXi!");
							}else{
								alert("Importaci\u00F3n finalizada!");
							}
						}
				    }else{
				    		alert("La fecha de la \u00FAltima lecutura no corresponde al dia de hoy,\n favor de verificar la fecha configurada en su dispositivo Spot Vital Signs LXi!");
				    }
					
						
				}
		  var req = "";
        }


			function liberaMem() {
				//alert("liberando");
			  var puerto;
			  var req = new XMLHttpRequest();
				req.open('POST', 'http://127.0.0.1:9233/WelchAllyn/Device/GetDevices?deviceid', false); 
				req.send(null);
			    //alert(req.responseText);
			    var dispositivo = JSON.parse(req.responseText);
			    puerto = dispositivo[0].deviceid;				
				req.open('POST', 'http://127.0.0.1:9233/WelchAllyn/Device/EraseCycleData?deviceid='+puerto, false); 				
				req.send(null);	
				alert("La memoria del Spot Vital Signs LXi ha sido borrada, su dispositivo est� listo para guardar una nueva lectura de signos Vitales");																													
        }
			
			

function fn_DateCompare(DateA, DateB) { // this function is good for dates > 01/01/1970

		var a = new Date(DateA);
		var b = new Date(DateB);
	
		var msDateA = Date.UTC(a.getFullYear(), a.getMonth() + 1, a.getDate());
		var msDateB = Date.UTC(b.getFullYear(), b.getMonth() + 1, b.getDate());
	
		if (parseFloat(msDateA) < parseFloat(msDateB))
			return -1; // menor
		else if (parseFloat(msDateA) == parseFloat(msDateB))
			return 0; // igual
		else if (parseFloat(msDateA) > parseFloat(msDateB))
			return 1; // mayor
		else
			return 3; // error
}

function BloqueaCampos(){
	document.getElementById("dValorI_111").readOnly = true;
	document.getElementById("dValorI_112").readOnly = true;
	document.getElementById("dValorI_15").readOnly = true;
	document.getElementById("dValorI_115").readOnly = true;
	//alert("seejecuto");
}

