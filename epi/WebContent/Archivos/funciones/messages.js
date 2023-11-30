Ext.ns('com.sct.mensajes');

com.sct.mensajes.Msg = {
	init: function(){
		Ext.Msg.wait("Se esta actualizando el sistema, por favor espere unos minutos...", '.: MedPrev :.');
		//alert("Paso");
		MedPredDwr.imageClient({
			callback : function(data) {
				Ext.Msg.hide();
				recarga();
			},
			//async : false,
			scope : this
		});
	},
	callback: function(txt){
		//alert(txt);
	}
}

Ext.onReady(com.sct.mensajes.Msg.init,com.sct.mensajes.Msg); 