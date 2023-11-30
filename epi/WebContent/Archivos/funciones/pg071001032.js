Ext.BLANK_IMAGE_URL = './extjs/resources/images/default/s.gif';

pg070107090 = function() {
    
    return {

        init: function(){
            Ext.QuickTips.init();
            this.buildDataStores();
            this.cargarCombos();
            this.buildLayout();
            this.loadData();
        },

        cargarCombos : function(){
			
        },
        /**
		 * Se realiza la invoaciÃ³n de los data stores a cargar
		 */
        loadData : function() {
            MedPredDwr.getDatosAviso({
                callback : function (data){
                    if (data.mostrarAvisoConfig == 'true') {
                        Ext.getCmp("id-active").setValue(1);
                    } else {
                        Ext.getCmp("id-active").setValue(0);
                    }
                    Ext.getCmp("id-infoAviso").setValue(data.avisoConfigBody);
                    Ext.getCmp("id-tituloAviso").setValue(data.avisoConfigTit);
                    Ext.getCmp("id-titDescarga").setValue(data.avisoConfigTitLiga);
                    Ext.getCmp("id-descarga").setValue(data.avisoConfigLiga);
                },
                //async : false,
                scope : this
            });	
        },

        /**
		 * Se construyen todos los stores con los que interactua la pantalla
		 */
        buildDataStores : function() {
			                   
        },

        /**
		 * InicializaciÃ³n de la pantalla
		 */
        buildLayout : function() {
            
            var titulo = new Ext.form.TextField({  
                fieldLabel:'Título de Aviso',  
                name:'txt-tituloAviso',
                width : 500,
                maxLength: 900,
                id:"id-tituloAviso"  
            });
                    
            var info = new Ext.form.TextArea({  
                fieldLabel:'Información en Aviso',  
                name:'txt-infoAviso',
                height: 100,
                width : 500,
                maxLength: 3000,
                id:"id-infoAviso"  
            });
                    
            var titDescarga = new Ext.form.TextField({  
                fieldLabel:'Titulo de documento para descarga',  
                name:'txt-titDescarga',
                width : 500,
                maxLength: 900,
                id:"id-titDescarga"  
            });
                    
            var descarga = new Ext.form.TextField({  
                fieldLabel:'Dirección documento para descarga',  
                name:'txt-descarga',
                width : 500,
                maxLength: 900,
                id:"id-descarga"  
            });
            
             var textoExtra = new Ext.form.Label({  
                fieldLabel:'*Si no desea colocar un documento de descarga, deje el campo en blanco',  
                name:'txt-textoExtra',
                id:"id-textoExtra"  
            });
            
            var viewport = new Ext.Viewport({
                layout : 'anchor',
                window : {
                    layout : 'border'
                },
                items: [{
                    layout: 'border',
                    anchor: '100% 100%',
                    border: false,
                    autoScroll : true,
                    items:[{
                        region 	: 'center',
                        autoHeight 	: true,
                        title  	: '.:: Configuración de aviso ::.',
                        border 	: false,
                        frame	: true,
                        items	: [{
                            xtype:'fieldset',
                            title:'Aviso :',
                            autoHeight:true,
                            labelWidth: 150,
                            items:[{  
                                xtype: 'checkbox', //defining the type of component  
                                fieldLabel: 'Activar Aviso',//assigning a label  
                                name: 'chk-active',//and a "name" to retrieve it on the server...  
                                id: 'id-active'// ...when the form is sent  
                            },
                            titulo,
                            info, //assigning the instance we created previously  
                            titDescarga, //group of checkboxes  
                            descarga,
                            textoExtra
                            ],  
                            buttonAlign: 'right', //<--botones alineados a la derecha  
                            buttons:[{
                                text:'Guardar',
                                iconCls:'icon-disk',
                                id: 'id-btnGuardar',
                                handler: function(){
                                    var aviso;
                                    if (Ext.getCmp("id-active").checked) {
                                        aviso = "true";
                                    } else {
                                        aviso= "false";
                                    }
                                    var DatosAvisoVo = {
                                        avisoConfigBody: Ext.getCmp("id-infoAviso").getValue(),
                                        avisoConfigTit: Ext.getCmp("id-tituloAviso").getValue(),
                                        mostrarAvisoConfig: aviso,
                                        avisoConfigTitLiga: Ext.getCmp("id-titDescarga").getValue(),
                                        avisoConfigLiga: Ext.getCmp("id-descarga").getValue()
                                    };
                                    MedPredDwr.setDatosAviso(DatosAvisoVo,{
                                        callback : function (data){
                                            Ext.MessageBox.show({
                                                title:'.: Sistema MEDPREV :.',
                                                msg: '¡¡¡ Operación Exitosa !!!',
                                                buttons: Ext.MessageBox.OK,
                                                //fn: this.loadData(),
                                                animEl: 'mb4',
                                                icon: Ext.MessageBox.INFO
                                            });
                                            if (data.mostrarAvisoConfig == 'true') {
                                                Ext.getCmp("id-active").setValue(1);
                                            } else {
                                                Ext.getCmp("id-active").setValue(0);
                                            }
                                            Ext.getCmp("id-infoAviso").setValue(data.avisoConfigBody);
                                            Ext.getCmp("id-tituloAviso").setValue(data.avisoConfigTit);
                                            Ext.getCmp("id-titDescarga").setValue(data.avisoConfigTitLiga);
                                            Ext.getCmp("id-descarga").setValue(data.avisoConfigLiga);
                                        },
                                        //async : false,
                                        scope : this
                                    });
                                }
                            }]
                        }]
                    }]
                }]
            });
        },
        modifyMovimiento : function (){
			
        },
        busquedaMovimientos: function() {
                    
        },
        borrarCampos: function(){
                    
        }
    } //end return main object
}(); //end main object

Ext.EventManager.onDocumentReady(pg070107090.init, pg070107090, true);
