Ext.BLANK_IMAGE_URL = './extjs/resources/images/default/s.gif';

pg070107090 = function() {
	var movimientoStore, storeTipoOperaBit, ventanaMod, record;
	return {

		init: function(){
			Ext.QuickTips.init();
			this.buildDataStores();
			this.cargarCombos();
			this.buildLayout();
			this.loadData();
		},

		cargarCombos : function(){
			MedPredDwr.findAllOperaBit({
				callback : function(data) {
					if (data.success) {
						storeTipoOperaBit.loadData(data.data);
					} else {
						alert('No cargo la info');
					}
				},
				scope : this,
				async : false
			});
		},
		/**
		 * Se realiza la invoaci髇 de los data stores a cargar
		 */
		loadData : function() {
			//this.busquedaConceptos();
		},

		/**
		 * Se construyen todos los stores con los que interactua la pantalla
		 */
		buildDataStores : function() {
			movimientoStore = new Ext.data.Store({
				proxy : new Ext.ux.data.PagingMemoryProxy(),
				reader : new Ext.data.ArrayReader( {}, [
                                   {name : 'iCveExpediente', mapping : 'iCveExpediente'},
 				   {name : 'iNumExamen', mapping : 'iNumExamen'},
 				   {name : 'operacion', mapping : 'operacion'},
 				   {name : 'dtRealizado', mapping : 'dtRealizado'},
 				   {name : 'descripcion', mapping : 'descripcion'},
 				   {name : 'iCveUsuarioRealiza', mapping : 'iCveUsuarioRealiza'},
 				   {name : 'iCveServicio', mapping : 'iCveServicio'},
                   {name : 'iCveRama', mapping : 'iCveRama'},
                   {name : 'iCveSintoma', mapping : 'iCveSintoma'},
                   {name : 'lDictamen', mapping : 'lDictamen'}
 				   ])
			});

			storeTipoOperaBit = new Ext.data.Store({
				reader : new Ext.data.ArrayReader( {},[
				          {name : 'iOperacion', mapping : 'iOperacion'},
				          {name : 'cDescripcion', mapping : 'cDescripcion'},
				    ])
				});

			/*storeMeses = new Ext.data.ArrayStore({
					fields: ['idMes','nomMes'],
					data: [
                                        ['0', 'ENERO']
			               ,['1', 'FEBRERO']
			               ,['2', 'MARZO']
			               ,['3', 'ABRIL']
			               ,['4', 'MAYO']
			               ,['5', 'JUNIO']
			               ,['6', 'JULIO']
			               ,['7', 'AGOSTO']
			               ,['8', 'SEPTIEMBRE']
			               ,['9', 'OCTUBRE']
			               ,['10', 'NOVIEMBRE']
			               ,['11', 'DICIEMBRE']
			              ]
			});*/

		},

		/**
		 * Inicializaci贸n de la pantalla
		 */
		buildLayout : function() {

            var comboTipoOperaBit = new Ext.form.ComboBox({
				fieldLabel: 'Operacion',
				typeAhead: true,
                triggerAction: 'all',
                lazyRender:true,
                mode: 'local',
                store: storeTipoOperaBit,
                value: storeTipoOperaBit.getAt(0).get("iOperacion"),
                valueField: 'iOperacion',
                displayField: 'cDescripcion',
                editable:false,
                id : 'cbTipoBitacora',
				name : 'cbTipoBitacora'
			});

			var textPersona = new Ext.form.TextField({
                fieldLabel: 'Persona',
                width: 50,
                id : 'txPersona',
                name : 'txPersona'
			});

            var textUsuario = new Ext.form.TextField({
                fieldLabel: 'Usuario',
                width: 50,
                id : 'txUsuario',
                name : 'txUsuario'
			});

            var dateInicio = new Ext.form.DateField({
                xtype : 'datefield',
                fieldLabel	: 'Fecha Inicio',
                format:'Y-m-d',
                id : 'dtInicio',
                name : 'dtInicio'
			});

            var dateFin = new Ext.form.DateField({
                xtype : 'datefield',
                fieldLabel	: 'Fecha Fin',
                format:'Y-m-d',
                id : 'dtFin',
                name : 'dtFin'
			});

			var barraMenu = new Ext.Toolbar({
		           buttonAlign : 'right', //Alineacion de los botones de la barra
		           items : [ ' ', ' ' , ' ' ,
		                     '-', '-',  //Separadores
		                     { //Los items son del tipo Ext.Button por default
	              	  	  		 text: 'Menu',	//Texto del bot贸n
	              	  	  		 scale: 'small', //Tama帽o del Botones
	              	  	  		 width: 100, //Ancho del Bot贸n
	              	  	  		 iconCls:'menuPrincipal',
	              	  	  		 handler: function() {
	              	  	  			 fCargarPag('pg0100003.jsp', 'FRMCuerpo');
	              	  	  		 }
		                     },
		                     '-', '-'	 //Separadores
		            ]
	    	});

			ventanaMod = new Ext.Window({
				layout : 'anchor',
				title : "Modificaci髇 de movimientos",
				autoHeight: true,
				width: 450,
				draggable: true,
				modal: true,
				closable:false,
				resizable: false,
				window : {
					layout : 'anchor'
				},
				items : [ {
					xtype : 'panel',
					border : true,
					id:'Modificacion',
					name:'Modificacion',
					frame:true,
					layout:'form',
					autoHeight:true,
					align : 'center',
					items:[{
						xtype:'textfield',
						fieldLabel: 'No. de Operaci髇 Banco',
						width: 150,
						id : 'txtidOperaIngresosMod',
						name : 'txtidOperaIngresosMod',
						disabled: true
					},{
						xtype:'textfield',
						fieldLabel: 'Id de Operaci髇 de Ingresos',
						width: 150,
						id : 'txtnumOperaBancoMod',
						name : 'txtnumOperaBancoMod',
						disabled: true
					},{
						xtype:'datefield',
						fieldLabel: 'Fecha de Presentaci髇',
						width: 150,
						id : 'txtfechaPresentacionMod',
						name : 'txtfechaPresentacionMod',
						//xtype: 'datecolumn',
						//format: 'd M Y',
						disabled: true
					},{
						xtype:'textfield',
						fieldLabel: 'Total Efectivamente Pagado',
						width: 150,
						id : 'txttotalEfePagMod',
						name : 'txttotalEfePagMod',
						renderer: Ext.util.Format.usMoney,
						disabled: true
					},{
						xtype:'textfield',
						fieldLabel: 'Banco',
						width: 250,
						height:'auto',
						id : 'txtbancoMod',
						name : 'txtbancoMod',
						disabled: true
					},{
						xtype:'textfield',
						fieldLabel: 'Cadena de la Dependencia',
						width: 150,
						maxLength: 20, // for validation
					    autoCreate: {tag: 'input', type: 'text', size: '20', autocomplete: 'off', maxlength: '20'},
					    maskRe: /[0-9]/,
						id : 'txtcadDepBancoMod',
						name : 'txtcadDepBancoMod',
						allowBlank: false
					},{
						xtype:'textfield',
						fieldLabel: 'Clave de Referencia',
						width: 150,
						maxLength: 9, // for validation
					    autoCreate: {tag: 'input', type: 'text', size: '10', autocomplete: 'off', maxlength: '9'},
					    //maskRe: /[0-9]/,
						id : 'txtcadRefBancoMod',
						name : 'txtcadRefBancoMod',
						allowBlank: false
					}],
					buttonAlign : 'center',
					buttons: [{
						text:'Modificar',
						tooltip : 'Modificar movimiento',
						iconCls:'icon-disk',
						id:'modificarBoton',
						handler: function(){
							var cadDep=Ext.getCmp("txtcadDepBancoMod").getValue();
							var ref=Ext.getCmp("txtcadRefBancoMod").getValue();
							if(cadDep == '' && ref == ''){
								Ext.MessageBox.show({
							           title:'.: Sistema MEDPREV :.',
							           msg: 'Favor de llenar alguno de los campos',
							           buttons: Ext.MessageBox.OK,
							           animEl: 'mb4',
							           icon: Ext.MessageBox.ERROR
							     });
							} else {
								var idUsuario = fGetUser();
								var RequestActualiza = {
										idOperaIngresos: Ext.getCmp("txtidOperaIngresosMod").getValue(),
										fechaPresentacion: Ext.getCmp("txtfechaPresentacionMod").getValue(),
										totalEfePag: Ext.getCmp("txttotalEfePagMod").getValue(),
										banco: Ext.getCmp("txtbancoMod").getValue(),
										numOperaBanco: Ext.getCmp("txtnumOperaBancoMod").getValue(),
										cadDepBanco: Ext.getCmp("txtcadDepBancoMod").getValue(),
										cadRefBancoString: Ext.getCmp("txtcadRefBancoMod").getValue()
									};
									Ext.Msg.wait("Actualizando movimiento. Espere por favor...",'.: Sistema MEDPREV :.');
									IngMovimientosApp.updateMovimientoSinCoincidencia(RequestActualiza,record.get('cadDepBanco'),record.get('cadRefBanco'),Ext.getCmp("cbMeses").getValue(),
											idUsuario,{
										callback : function (data){
											Ext.Msg.hide();
											if (data.success) {
												ventanaMod.hide();
												Ext.MessageBox.show({
											           title:'.: Sistema MEDPREV :.',
											           msg: data.messages,
											           buttons: Ext.MessageBox.OK,
											           fn: pg0804010.busquedaMovimientos,
											           animEl: 'mb4',
											           icon: Ext.MessageBox.INFO
											    });
												//pg0804010.busquedaMovimientos;
											} else {
												Ext.MessageBox.show({
											           title:'.: Sistema MEDPREV :.',
											           msg: data.messages,
											           buttons: Ext.MessageBox.OK,
											           animEl: 'mb4',
											           icon: Ext.MessageBox.ERROR
											       });
											}
										},
										scope : this
									});
							}
						}
					},{
						text:'Cancelar',
						tooltip : 'Cancelar Modificacion',
						iconCls:'icon-cancel',
						id:'cancelarBoton',
						handler: function(){
							ventanaMod.hide();
						}
					}]
				}]
			});

			var formNorth = new Ext.FormPanel({
				region: 'north',
				title:'.: Modificaci髇 del movimientos bancarios sin relacion con INGRESOS :.',
                tbar: barraMenu,
				frame:true,
			    autoWidth: true,
			    height: 0.5,
                border: false,
                //margins: '0 0 5 0',
                id:'panelPrincipal',
                name:'panelPrincipal',
			    layout:'anchor', // arrange items in columns
			    items: []
			});

			var gridMovimientos = new Ext.grid.GridPanel({
				id : 'gridMovimientos',
				renderTo: Ext.getBody(),
				name : 'gridMovimientos',
				autoScroll : true,
				title:'Consulta de Movimientos',
				anchor: '100%',
				frame: true,
				autoHeight : true,
				monitorResize: true,
				ds : movimientoStore,
				viewConfig : new Ext.grid.GridView({
					forceFit : true
				}),
				loadMask: true,
				scrolls: true,
				tbar : ['-','-','Persona:', textPersona,
				        '-','-','Usuario:', textUsuario,
                                        '-','-','Operaci&oacute;n:', comboTipoOperaBit,
                                        '-','-','Fecha Inicio:', dateInicio,
                                        '-','-','Fecha Fin:', dateFin,
				        '-','-',{
					text:'Buscar',
					tooltip : 'Buscar movimientos',
					iconCls : 'icon-zoom',
					id:'buscarBoton',
					handler: this.busquedaMovimientos
				},'-','-'
				],
				selectionModel : new Ext.grid.RowSelectionModel({
					singleSelect : true
				}),
				cm:new Ext.grid.ColumnModel([
				     new Ext.grid.RowNumberer(),
				     {header: 'Expediente', width: 30, sortable: true, dataIndex: 'iCveExpediente'},
				     {header: '# de examen', width: 30, sortable: true,dataIndex : 'iNumExamen'},
                                     {header: 'Operaci&oacute;n', width: 80, sortable: true,dataIndex : 'operacion'},
				     {header: 'Fecha Realizado', align: 'center', width: 35, sortable: true, dataIndex : 'dtRealizado'},
				     {header: 'Descripci&oacute;n', width: 170, align: 'center', sortable: true, dataIndex : 'descripcion'},
				     {header: 'Usuario', width: 25, sortable: true,dataIndex : 'iCveUsuarioRealiza'},
				     {header: 'Servicio', width: 25, sortable: true,dataIndex : 'iCveServicio'},
				     {header: 'Rama', width: 25, sortable: true,dataIndex : 'iCveRama'},
                                     {header: 'Sintoma', width: 25, sortable: true,dataIndex : 'iCveSintoma'},
                                     {header: 'Dictamen', width: 25, sortable: true,dataIndex : 'lDictamen'}
				]),
				//plugins: [filterRow],
				bbar : new Ext.PagingToolbar({
					pageSize : 9999,
					store : movimientoStore,
					displayInfo : true,
					emptyMsg : 'No existen elementos'
				}),
				listeners : {
					click : function (){
						var registro = Ext.getCmp('gridMovimientos').getSelectionModel().getSelected();
					}
				}
			});

			/*var gridBitacora = new Ext.grid.GridPanel({
				id : 'gridBitacora',
				renderTo: Ext.getBody(),
				name : 'gridBitacora',
				autoScroll : true,
				anchor: '100%',
				frame: true,
				autoHeight : true,
				monitorResize: true,
				ds : bitacoraStore,
				viewConfig : new Ext.grid.GridView({
					forceFit : true
				}),
				loadMask: true,
				scrolls: true,
				selectionModel : new Ext.grid.RowSelectionModel({
					singleSelect : true
				}),
				cm:new Ext.grid.ColumnModel([
				     new Ext.grid.RowNumberer(),
				     {header: 'Fecha y Hora', align: 'center', width: 50, sortable: true, dataIndex : 'tsRegistro', xtype: 'datecolumn', format: 'd M Y h:m'},
				     {header: 'Movimiento', width: 130, sortable: true, dataIndex : 'cDscMovimiento'},
				     {header: 'Registr贸', width: 100, sortable: true, dataIndex : 'cNombre'}
				]),
				//plugins: [filterRow],
				bbar : new Ext.PagingToolbar({
					pageSize : 5,
					store : bitacoraStore,
					displayInfo : true,
					emptyMsg : 'No existen elementos'
				})
			});*/

			/*var south = {
				    xtype   :   "panel",
				    region  :   "south",
				    height  :   150,
				    collapsible: true,
				    titleCollapse : true,
				    title   :   ".: Bitacora :.",
				    bodyStyle: 'padding:10px;',
                    items: [gridBitacora]
				};*/

            var viewport = new Ext.Viewport({
            	layout : 'anchor',
    			items: [{
    				layout: 'border',
    				anchor: '100% 100%',
    				border: false,
    				autoScroll : true,
    				items:[/*formNorth,*/{
                	/*
                	 * Panel para grid
                	 */
                    region: 'center',
                    xtype: 'panel',
                    autoHeight: true,
                    autoWidth : true,
                    autoScroll : true,
                    defaults: {
                        bodyStyle: 'padding:100px'
                    },
                    layoutConfig: {
                        titleCollapse: false,
                        animate: true,
                        activeOnTop: false
                    },
                    items: [gridMovimientos]
              }]
    			}]
            });
            //gridMovimientos.on('rowdblclick', this.modifyMovimiento, this);
		},
		modifyMovimiento : function (){
			record = Ext.getCmp('gridMovimientos').getSelectionModel().getSelected();
			if(record != null){
				Ext.getCmp("txtidOperaIngresosMod").setValue(record.get('idOperaIngresos'));
				Ext.getCmp("txtfechaPresentacionMod").setValue(record.get('fechaPresentacion'));
				Ext.getCmp("txttotalEfePagMod").setValue(record.get('totalEfePag'));
				Ext.getCmp("txtbancoMod").setValue(record.get('banco'));
				Ext.getCmp("txtnumOperaBancoMod").setValue(record.get('numOperaBanco'));
				Ext.getCmp("txtcadDepBancoMod").setValue("");
				Ext.getCmp("txtcadRefBancoMod").setValue("");
				ventanaMod.show();
			}else{
				Ext.MessageBox.show({
			           title:'.: Sistema MEDPREV :.',
			           msg: 'No ha seleccionado ningun renglon',
			           buttons: Ext.MessageBox.OK,
			           animEl: 'mb4',
			           icon: Ext.MessageBox.ERROR
			       });
			}
		},
		busquedaMovimientos: function() {
                    var fechIni = "", fechFin="";
                        if(Ext.getCmp("dtInicio").getValue()!=""){
                            fechIni=Ext.getCmp("dtInicio").getValue().format('Y-m-d');
                        }
                        if(Ext.getCmp("dtFin").getValue()!=""){
                            fechFin=Ext.getCmp("dtFin").getValue().format('Y-m-d');
                        }
                        if (Ext.getCmp("txPersona").getValue()==""
                                && Ext.getCmp("txUsuario").getValue()==""
                                && Ext.getCmp("cbTipoBitacora").getValue()==0
                                && Ext.getCmp("dtInicio").getValue()==""
                                && Ext.getCmp("dtFin").getValue()=="") {
                            alert('Favor de llenar alguno de los campos');
                        } else {
                            var BitacoraRetornoVo = {
                                persona: Ext.getCmp("txPersona").getValue(),
                                usuario: Ext.getCmp("txUsuario").getValue(),
                                operacion: Ext.getCmp("cbTipoBitacora").getValue(),
                                fechaInicio: fechIni,
                                fechaFin: fechFin
                        };
			Ext.Msg.wait("Buscando informaci髇...",'.: MEDPREV :.');
                        //alert(Ext.getCmp("txPersona").getValue() + Ext.getCmp("txUsuario").getValue()+ Ext.getCmp("dtInicio").getValue()+Ext.getCmp("dtFin").getValue());
			MedPredDwr.findMovimientosBitacora(BitacoraRetornoVo,{
				callback : function (data){
					Ext.Msg.hide();
					if (data.success) {
						movimientoStore.proxy.data = data.data;
						movimientoStore.load({
							params : {
								start : 0,
								limit : 9999
							},
						callback : function(rs,
							opts, success) {
								//Ext.getCmp('botonModificarC').setDisabled(true);
							},
							scope : this
						});
					} else {
						movimientoStore.removeAll();
						movimientoStore.loadData([],false);
						Ext.MessageBox.show({
					           title:'.: Sistema MEDPREV :.',
					           msg: 'No hay datos',
					           buttons: Ext.MessageBox.OK,
					           animEl: 'mb4',
					           icon: Ext.MessageBox.ERROR
					       });
					}
				},
				//async : false,
				scope : this
			});
                    }

		}
	} //end return main object
}(); //end main object

Ext.EventManager.onDocumentReady(pg070107090.init, pg070107090, true);
