       

        function datos(response){
    		var msg = '';
    		var java2= '';
    		msg ="Query = "+response.query+"Pais = "+response.country+
    			"Ciudad = "+response.city+"Region = "+response.regionName+
    			"Latitude = "+response.lat+"Longitude = "+response.lon+
    			"ZonaHoraria = "+response.timezone+
    			"ProvedorSI = "+response.isp+
    			"Organizacion = "+response.org;
    		form = document.forms[0];
    		form.msg.value = msg;
    		//alert(msg);
    	}
