function sendRequest(method,servlet,sFn,eFn,data) {
		
		/*if ('XDomainRequest' in window && window.XDomainRequest !== null) {

	        var xdr = new XDomainRequest(); // Use Microsoft XDR
	        xdr.open('GET', servlet+'&MTHD='+method);
	        xdr.onload = function () {
	            var dom  = new ActiveXObject('Microsoft.XMLDOM'),
	                JSON = $.parseJSON(xdr.responseText);

	            dom.async = false;

	            if (JSON == null || typeof (JSON) == 'undefined') {
	                JSON = $.parseJSON(data.firstChild.textContent);
	            }

	            sFn(JSON); // internal function
	        };

	        xdr.onerror = function() {
	            _result = false;  
	        };

	        xdr.send();
	    } else {*/
	    	$.ajax({
	  		  type: method,
	  		  url: servlet,
	  		  data: data,
	  		  success: sFn,
	  		  error:eFn
	  	   });	
	    //}
	}