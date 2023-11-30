Ext.BLANK_IMAGE_URL = './extjs/resources/images/default/s.gif';
pg070107092 = function() {
    var  storeUsuarios, storeExpedientes, storeUsuariosXExp, ventanaMod, ventanaModificar, record,ventanaBuscaUsuario, ventanaBuscaExpediente,ventanaResultadoUsuario,gridUsuarios,gridExpedientes;
    return {

        init: function(){
            Ext.QuickTips.init();
            this.buildDataStores();
            this.buildLayout();
            this.loadData();
        },
        buildDataStores : function() {
            storeUsuarios = new Ext.data.Store({
                proxy : new Ext.ux.data.PagingMemoryProxy(),
                reader : new Ext.data.ArrayReader( {},[
                {
                    name : 'ICVEUSUARIO',
                    mapping : 'ICVEUSUARIO'
                },

                {
                    name : 'CUSUARIO',
                    mapping : 'CUSUARIO'
                },

                {
                    name : 'CNOMBRE',
                    mapping : 'CNOMBRE'
                },

                {
                    name : 'CAPPATERNO',
                    mapping : 'CAPPATERNO'
                },

                {
                    name : 'CAPMATERNO',
                    mapping : 'CAPMATERNO'
                }
                ])
            });
            storeExpedientes = new Ext.data.Store({
                proxy : new Ext.ux.data.PagingMemoryProxy(),
                reader : new Ext.data.ArrayReader( {},[
                {
                    name : 'ICVEPERSONAL',
                    mapping : 'ICVEPERSONAL'
                },

                {
                    name : 'ICVEEXPEDIENTE',
                    mapping : 'ICVEEXPEDIENTE'
                },

                {
                    name : 'CNOMBRE',
                    mapping : 'CNOMBRE'
                },

                {
                    name : 'CAPPATERNO',
                    mapping : 'CAPPATERNO'
                },

                {
                    name : 'CAPMATERNO',
                    mapping : 'CAPMATERNO'
                }
                ])
            });

            storeUsuariosXExp = new Ext.data.Store({
                proxy : new Ext.ux.data.PagingMemoryProxy(),
                reader : new Ext.data.ArrayReader( {},[
                {
                    name : 'ICVEUSUARIO',
                    mapping : 'ICVEUSUARIO'
                },

                {
                    name : 'ICVEEXPEDIENTE',
                    mapping : 'ICVEEXPEDIENTE'
                },

                {
                    name : 'INODOCTO',
                    mapping : 'INODOCTO'
                },

                {
                    name : 'TSCAPTURA',
                    mapping : 'TSCAPTURA'
                },

                {
                    name : 'IDEDO',
                    mapping : 'IDEDO'
                },

                {
                    name : 'CCVEUSUARIOREGISTRO',
                    mapping : 'CCVEUSUARIOREGISTRO'
                },

                {
                    name : 'ICVEUSUARIOREGISTRO',
                    mapping : 'ICVEUSUARIOREGISTRO'
                },

                {
                    name : 'CUSUARIO',
                    mapping : 'CUSUARIO'
                },

                {
                    name : 'CNOMBRE',
                    mapping : 'CNOMBRE'
                },

                {
                    name : 'CAPPATERNO',
                    mapping : 'CAPPATERNO'
                },

                {
                    name : 'CAPMATERNO',
                    mapping : 'CAPMATERNO'
                }
                ])
            });
        },
        loadData : function() {
            this.cargarListaUsuariosXExpediente();
        // this.buscarUsuarioX('NA','NA','NA','NA','NA');
        //this.cargarListaExpedientes();
        },
        buildLayout : function() {
            var searchUsuario = new Ext.ux.form.SearchField({
                width: 220,
                fieldLabel: 'Usuario',
                id: 'search_Usu',
                editable:false
            });
            searchUsuario.onTrigger2Click = function() {
                ventanaBuscaUsuario.show();
            }

            var searchExpediente = new Ext.ux.form.SearchField({
                width: 220,
                fieldLabel: 'Expediente',
                id: 'search_Exp',
                editable:false
            });

            searchExpediente.onTrigger2Click = function() {
                createExpedienteBtn.disable();
                ventanaBuscaExpediente.show();
            }

            // create the combo instance
            var comboDedos = new Ext.form.ComboBox({
                typeAhead: true,
                triggerAction: 'all',
                lazyRender:true,
                width:150,
                mode: 'local',
                store: new Ext.data.ArrayStore({
                    id: 0,
                    fields: [
                    'idDedo',
                    'nombreDedo'
                    ],
                    data: [[1, 'Pulgar Derecho']
                    , [2, 'Indice Derecho']
                    , [3, 'Medio Derecho']
                    , [4, 'Anular Derecho']
                    , [5, 'Meñique Derecho']
                    , [6, 'Pulgar Izquierdo']
                    , [7, 'Indice Izquierdo']
                    , [8, 'Medio Izquierdo']
                    , [9, 'Anular Izquierdo']
                    , [10, 'Meñique Izquierdo']]
                }),
                valueField: 'idDedo',
                displayField: 'nombreDedo'
            });
            comboDedos.setValue(comboDedos.store.getAt(1).get('idDedo'));

            // create the combo instance
            var comboDedosModificar = new Ext.form.ComboBox({
                typeAhead: true,
                triggerAction: 'all',
                lazyRender:true,
                width:150,
                mode: 'local',
                store: new Ext.data.ArrayStore({
                    id: 0,
                    fields: [
                    'idDedoModificar',
                    'nombreDedoModificar'
                    ],
                    data: [[1, 'Pulgar Derecho']
                    , [2, 'Indice Derecho']
                    , [3, 'Medio Derecho']
                    , [4, 'Anular Derecho']
                    , [5, 'Meñique Derecho']
                    , [6, 'Pulgar Izquierdo']
                    , [7, 'Indice Izquierdo']
                    , [8, 'Medio Izquierdo']
                    , [9, 'Anular Izquierdo']
                    , [10, 'Meñique Izquierdo']]
                }),
                valueField: 'idDedoModificar',
                displayField: 'nombreDedoModificar'
            });
            comboDedosModificar.setValue(comboDedosModificar.store.getAt(1).get('idDedo'));


            var txtCUSUARIOBuscar = new Ext.form.TextField({
                fieldLabel: 'Nombre del usuario',
                width: 150,
                id : 'txtCUSUARIOBuscar',
                name : 'txtCUSUARIOBuscar'
            });
            var txtCUSUARIOBuscar = new Ext.form.TextField({
                fieldLabel: 'Nombre del usuario',
                width: 150,
                id : 'txtCUSUARIOBuscar',
                name : 'txtCUSUARIOBuscar'
            });
            var txtiCveUSUARIOModificar = new Ext.form.TextField({
                fieldLabel: 'Número del usuario',
                width: 150,
                id : 'txtiCveUSUARIOModificar',
                disabled: true,
                name : 'txtiCveUSUARIOModificar'
            });
            
            var txtICVEEXPEDIENTEModificar = new Ext.form.TextField({
                fieldLabel: 'Número de expediente',
                width: 150,
                disabled: true,
                id : 'txtICVEEXPEDIENTEModificar',
                name : 'txtICVEEXPEDIENTEModificar'
            });

            var txtICVEEXPEDIENTEBuscar = new Ext.form.TextField({
                fieldLabel: 'Número de expediente',
                width: 150,
                id : 'txtICVEEXPEDIENTEBuscar',
                name : 'txtICVEEXPEDIENTEBuscar'
            });

            var txtICRFCBuscar = new Ext.form.TextField({
                fieldLabel: 'RFC',
                width: 150,
                id : 'txtICRFCBuscar',
                name : 'txtICRFCBuscar'
            });

            var txtICVEUSUARIOBuscar = new Ext.form.TextField({
                fieldLabel: 'Número del Usuario',
                width: 150,
                id : 'txtICVEUSUARIOBuscar',
                name : 'txtICVEUSUARIOBuscar'
            });

            var txtCNOMBREBuscarUsu = new Ext.form.TextField({
                fieldLabel: 'Nombre',
                width: 150,
                id : 'txtCNOMBREBuscarUsu',
                name : 'txtCNOMBREBuscarUsu'
            });

            var txtCNOMBREBuscarExp = new Ext.form.TextField({
                fieldLabel: 'Nombre',
                width: 150,
                id : 'txtCNOMBREBuscarExp',
                name : 'txtCNOMBREBuscarExp'
            });

            var txtCAPPATERNOBuscarUsu = new Ext.form.TextField({
                fieldLabel: 'Apellido paterno',
                width: 150,
                id : 'txtCAPPATERNOBuscarUsu',
                name : 'txtCAPPATERNOBuscarUsu'
            });
            var txtCAPPATERNOBuscarExp = new Ext.form.TextField({
                fieldLabel: 'Apellido paterno',
                width: 150,
                id : 'txtCAPPATERNOBuscarExp',
                name : 'txtCAPPATERNOBuscarExp'
            });
            var txtCAPMATERNOBuscarUsu = new Ext.form.TextField({
                fieldLabel: 'Apellido Materno',
                width: 150,
                id : 'txtCAPMATERNOBuscarUsu',
                name : 'txtCAPMATERNOBuscarUsu'
            });
            var txtCAPMATERNOBuscarExp = new Ext.form.TextField({
                fieldLabel: 'Apellido Materno',
                width: 150,
                id : 'txtCAPMATERNOBuscarExp',
                name : 'txtCAPMATERNOBuscarExp'
            });

            var searchUsuarioBtnHandler = function(btn) {
                var CUSUARIO = "", ICVEUSUARIO="", CNOMBRE="", CAP="", CAM="";
                if (Ext.getCmp("txtCUSUARIOBuscar").getValue()!="")CUSUARIO = Ext.getCmp("txtCUSUARIOBuscar").getValue();
                if (Ext.getCmp("txtICVEUSUARIOBuscar").getValue()!="")ICVEUSUARIO = Ext.getCmp("txtICVEUSUARIOBuscar").getValue();
                if (Ext.getCmp("txtCNOMBREBuscarUsu").getValue()!="")CNOMBRE = Ext.getCmp("txtCNOMBREBuscarUsu").getValue();
                if (Ext.getCmp("txtCAPPATERNOBuscarUsu").getValue()!="")CAP = Ext.getCmp("txtCAPPATERNOBuscarUsu").getValue();
                if (Ext.getCmp("txtCAPMATERNOBuscarUsu").getValue()!="")CAM = Ext.getCmp("txtCAPMATERNOBuscarUsu").getValue();
                if(CUSUARIO!=""||ICVEUSUARIO!=""||CNOMBRE!=""||CAP!=""||CAM!=""){
                    //AdmonUsuarios.findUsuarioBloqueado(CUSUARIO,ICVEUSUARIO, CNOMBRE,CAP,CAM,{
                    AdmonUsuarios.findUsuarioBloqueado(CUSUARIO,"","","","",{
                        callback : function (data){
                            if (data.success) {
                                //Ext.Msg.hide();
                                //alert("traigo datos" +  data.data);
                                storeUsuarios.proxy.data = data.data;
                                storeUsuarios.load({
                                    params : {
                                        start : 0,
                                        limit : 10
                                    },
                                    callback : function(rs,
                                        opts, success) {
                                        gridUsuarios.getStore().load();
                                    //Ext.getCmp('botonModificarC').setDisabled(true);
                                    },
                                    scope : this
                                });
                            } else {
                                alert('No hay usuario coincidiente con la información');
                            }
                        },
                        scope : this
                    });
                }
            }
            
            var searchExpedienteBtnHandler = function(btn) {
                var ICVEEXPEDIENTE = "", CRFC="", CNOMBRE="", CAP="", CAM="";
                if (txtICVEEXPEDIENTEBuscar.getValue()!="")ICVEEXPEDIENTE = txtICVEEXPEDIENTEBuscar.getValue();
                if (txtICRFCBuscar.getValue()!="")CRFC = txtICRFCBuscar.getValue();
                if (txtCNOMBREBuscarExp.getValue()!="")CNOMBRE = txtCNOMBREBuscarExp.getValue();
                if (txtCAPPATERNOBuscarExp.getValue()!="")CAP = txtCAPPATERNOBuscarExp.getValue();
                if (txtCAPMATERNOBuscarExp.getValue()!="")CAM = txtCAPMATERNOBuscarExp.getValue();
                if(ICVEEXPEDIENTE!=""||CRFC!=""||CNOMBRE!=""||CAP!=""||CAM!=""){
                    AdmonUsuarios.findExpedienteBloqueado(ICVEEXPEDIENTE,"", "","","",{
                        callback : function (data){
                            if (data.success) {
                                //Ext.Msg.hide();
                                //alert("traigo datos" +  data.data);
                                storeExpedientes.proxy.data = data.data;
                                storeExpedientes.load({
                                    params : {
                                        start : 0,
                                        limit : 10
                                    },
                                    callback : function(rs,
                                        opts, success) {
                                        gridExpedientes.getStore().load();
                                    //Ext.getCmp('botonModificarC').setDisabled(true);
                                    },
                                    scope : this
                                });


                            } else {
                                alert('No hay usuario coincidiente con la información');
                                createExpedienteBtn.enable();
                            }
                        },
                        scope : this
                    });
                }
            }

            var searchUsuarioBtn =  new Ext.Button({
                text:'Buscar',
                tooltip : 'Buscar Usuario',
                iconCls : 'icon-zoom',
                handler: searchUsuarioBtnHandler
            });

            var createExpedienteBtn =  new Ext.Button({
                text:'Crear Expediente',
                tooltip : 'Crear un nuevo Expediente',
                iconCls : 'iconoAdd',
                disabled: true,
                handler: function(){
                    window.open("SEPer022envia.jsp");
                }
            });

            var searchExpedienteBtn =  new Ext.Button({
                text:'Buscar',
                tooltip : 'Buscar Expediente',
                iconCls : 'icon-zoom',
                handler: searchExpedienteBtnHandler
            });

            var searchUsuarioToolbar = new Ext.Toolbar({
                items : [searchUsuarioBtn/*,
                {
                    text:'Cancelar',
                    tooltip : 'Cerrar Ventana',
                    iconCls:'icon-cancel',
                    id:'cancelarUsuario',
                    handler: function(){
                        ventanaBuscaUsuario.hide();
                    }
                }*/
                ]
            });

            var searchExpedienteToolbar = new Ext.Toolbar({
                items : [searchExpedienteBtn/*,
                {
                    text:'Cancelar',
                    tooltip : 'Cerrar Ventana',
                    iconCls:'icon-cancel',
                    id:'cancelarExpediente',
                    handler: function(){
                        ventanaBuscaExpediente.hide();
                    }
                },createExpedienteBtn*/]
            });

            gridUsuarios = new Ext.grid.GridPanel({
                //xtype:'grid',
                id : 'gridUsuarios',
                name : 'gridUsuarios',
                //title:'Resultado de la búsqueda de usarios',
                ds : storeUsuarios,
                autoScroll : 'auto',
                //                        width : 500,
                height : 200,
                stripeRows : true,
                trackMouseOver : true,
                viewConfig : {
                    forceFit : true,
                    markDirty : false
                },
                selectionModel : new Ext.grid.RowSelectionModel({
                    singleSelect : true
                }),
                cm:new Ext.grid.ColumnModel([
                    new Ext.grid.RowNumberer(),
                    {
                        header: 'Usuario',
                        width: 30,
                        sortable: true,
                        dataIndex: 'CUSUARIO',
                        filter: {}
                    },
                    {
                        header: 'Número del Usuario',
                        width: 30,
                        sortable: true,
                        dataIndex : 'ICVEUSUARIO',
                        filter: {}
                    },
                    {
                        header: 'Nombre',
                        align: 'center',
                        width: 30,
                        sortable: true,
                        dataIndex : 'CNOMBRE',
                        filter: {}
                    },
                    {
                        header: 'Apellido paterno',
                        width: 20,
                        align: 'center',
                        sortable: true,
                        dataIndex : 'CAPPATERNO'
                    },
                    {
                        header: 'Apellido materno',
                        width: 20,
                        align: 'center',
                        sortable: true,
                        dataIndex : 'CAPMATERNO'
                    }]),
                /*plugins: [filterRow*/
                bbar : new Ext.PagingToolbar({
                    pageSize : 5,
                    store : storeUsuarios,
                    displayInfo : true,
                    emptyMsg : 'No existen elementos'
                }),
                tbar : searchUsuarioToolbar,
                listeners : {
                    click : function (){
                        var registro = Ext.getCmp('gridUsuarios').getSelectionModel().getSelected();
                        Ext.Msg.confirm(
                            'Desbloqueo',
                            'Deseas desbloquear el usuario '+registro.get('CUSUARIO')+'?',
                            function(btn) {
                                if(btn === 'yes'){
                                    AdmonUsuarios.desbloqueaUsuario(registro.get('ICVEUSUARIO'),{
                                        callback : function (data){
                                            if (data.success) {
                                                Ext.Msg.alert("Notificacion","Usuario desbloqueado");
                                                AdmonUsuarios.findUsuarioBloqueado("0","","","","",{
                                                    callback : function (data){
                                                        if (data.success) {
                                                            //Ext.Msg.hide();
                                                            //alert("traigo datos" +  data.data);
                                                            storeUsuarios.proxy.data = data.data;
                                                            storeUsuarios.load({
                                                                params : {
                                                                    start : 0,
                                                                    limit : 10
                                                                },
                                                                callback : function(rs,
                                                                    opts, success) {
                                                                    gridUsuarios.getStore().load();
                                                                //Ext.getCmp('botonModificarC').setDisabled(true);
                                                                },
                                                                scope : this
                                                            });
                                                        } else {
                                                            alert('No hay usuario coincidiente con la información');
                                                        }
                                                    },
                                                    scope : this
                                                });
                                            }else{
                                                Ext.Msg.alert("Notificacion","Hubo un error en el desbloqueo favor de intentar nuevamente");
                                            }
                                        }
                                    });
                                }
                            },
                            this
                            );
                    //searchUsuario.setValue(registro.get('ICVEUSUARIO'));
                    //ventanaBuscaUsuario.hide();
                    }
                }
            });

            gridExpedientes = new Ext.grid.GridPanel({
                //xtype:'grid',
                id : 'gridExpedientes',
                name : 'gridExpedientes',
                //title:'Resultado de la búsqueda de usarios',
                ds : storeExpedientes,
                autoScroll : 'auto',
                //                        width : 500,
                height : 200,
                stripeRows : true,
                trackMouseOver : true,
                viewConfig : {
                    forceFit : true,
                    markDirty : false
                },
                selectionModel : new Ext.grid.RowSelectionModel({
                    singleSelect : true
                }),
                cm:new Ext.grid.ColumnModel([
                    new Ext.grid.RowNumberer(),
                    {
                        header: 'Número Expediente',
                        width: 30,
                        sortable: true,
                        dataIndex: 'ICVEEXPEDIENTE',
                        filter: {}
                    },
                    {
                        header: 'Nombre',
                        align: 'center',
                        width: 30,
                        sortable: true,
                        dataIndex : 'CNOMBRE',
                        filter: {}
                    },
                    {
                        header: 'Apellido paterno',
                        width: 20,
                        align: 'center',
                        sortable: true,
                        dataIndex : 'CAPPATERNO'
                    },
                    {
                        header: 'Apellido materno',
                        width: 20,
                        align: 'center',
                        sortable: true,
                        dataIndex : 'CAPMATERNO'
                    }]),
                /*plugins: [filterRow*/
                bbar : new Ext.PagingToolbar({
                    pageSize : 5,
                    store : storeExpedientes,
                    displayInfo : true,
                    emptyMsg : 'No existen elementos'
                }),
                tbar : searchExpedienteToolbar,
                listeners : {
                    click : function (){
                        var registro = Ext.getCmp('gridExpedientes').getSelectionModel().getSelected();
                        Ext.Msg.confirm(
                            'Desbloqueo',
                            'Deseas desbloquear el expediente '+registro.get('ICVEEXPEDIENTE')+'?',
                            function(btn) {
                                if(btn === 'yes'){
                                    AdmonUsuarios.desbloqueaExpediente(registro.get('ICVEEXPEDIENTE'),{
                                        callback : function (data){
                                            if (data.success) {
                                                Ext.Msg.alert("Notificacion","Expediente desbloqueado");
                                                AdmonUsuarios.findExpedienteBloqueado('0',"", "","","",{
                                                    callback : function (data){
                                                        if (data.success) {
                                                            //Ext.Msg.hide();
                                                            //alert("traigo datos" +  data.data);
                                                            storeExpedientes.proxy.data = data.data;
                                                            storeExpedientes.load({
                                                                params : {
                                                                    start : 0,
                                                                    limit : 10
                                                                },
                                                                callback : function(rs,
                                                                    opts, success) {
                                                                    gridExpedientes.getStore().load();
                                                                //Ext.getCmp('botonModificarC').setDisabled(true);
                                                                },
                                                                scope : this
                                                            });


                                                        } else {
                                                            alert('No hay usuario coincidiente con la información');
                                                            createExpedienteBtn.enable();
                                                        }
                                                    },
                                                    scope : this
                                                });
                                            }else{
                                                Ext.Msg.alert("Notificacion","Hubo un error en el desbloqueo favor de intentar nuevamente");
                                            }
                                        }
                                    });
                                    
                                }
                            },
                            this
                            );
                    //searchExpediente.setValue(registro.get('ICVEEXPEDIENTE'));
                    //ventanaBuscaExpediente.hide();
                    }
                }
            });

            ventanaBuscaUsuario = new Ext.Window({
                title : "Busqueda de usuario",
                autoHeight: true,
                width: 450,
                draggable: true,
                modal: true,
                closable:false,
                resizable: true,
                layout: 'fit',
                viewConfig: {
                    forceFit: true
                },
                window : {
                    layout : 'fit'
                },
                items : [{
                    xtype : 'panel',
                    border : true,
                    id:'BuscarUsuario',
                    name:'BuscarUsuario',
                    frame:true,
                    layout:'form',
                    autoHeight:true,
                    align : 'center',
                    items:[txtCUSUARIOBuscar,txtICVEUSUARIOBuscar,txtCNOMBREBuscarUsu,txtCAPPATERNOBuscarUsu,txtCAPMATERNOBuscarUsu,gridUsuarios]
                }]
            });

            ventanaBuscaExpediente = new Ext.Window({
                title : "Busqueda de expediente",
                autoHeight: true,
                width: 450,
                draggable: true,
                modal: true,
                closable:false,
                resizable: true,
                layout: 'fit',
                viewConfig: {
                    forceFit: true
                },
                window : {
                    layout : 'fit'
                },
                items : [{
                    xtype : 'panel',
                    border : true,
                    id:'BuscarExpediente',
                    name:'BuscarExpediente',
                    frame:true,
                    layout:'form',
                    autoHeight:true,
                    align : 'center',
                    items:[txtICVEEXPEDIENTEBuscar,txtICRFCBuscar,txtCNOMBREBuscarExp,txtCAPPATERNOBuscarExp,txtCAPMATERNOBuscarExp,gridExpedientes]
                }]
            });

            ventanaMod = new Ext.Window({
                layout : 'anchor',
                title : "Registro de acceso",
                autoHeight: true,
                width: 450,
                draggable: true,
                modal: true,
                closable:false,
                resizable: false,
                window : {
                    layout : 'anchor'
                },
                items : [{
                    xtype : 'panel',
                    border : true,
                    id:'Agregar',
                    name:'Agregar',
                    frame:true,
                    layout:'form',
                    autoHeight:true,
                    align : 'center',
                    items:[searchUsuario,
                    searchExpediente,comboDedos],
                    buttonAlign : 'center',
                    buttons: [{
                        text:'Guardar',
                        tooltip : 'Guardar registro',
                        iconCls:'icon-disk',
                        id:'guardarBoton',
                        handler: function(){
                            var usuarioSesion = document.getElementById('iCvePersonal').value;                            
                            AdmonUsuarios.guardaAccesoUsuario(searchUsuario.getValue(),searchExpediente.getValue(), comboDedos.getValue(),usuarioSesion,{
                                callback : function (data){
                                    if (data.success) {
                                        alert('Guardado realizado exitosamente');
                                        AdmonUsuarios.findAllUsuXExpLogin({
                                            callback : function (data){
                                                if (data.success) {
                                                    storeUsuariosXExp.proxy.data = data.data;
                                                    storeUsuariosXExp.load({
                                                        params : {
                                                            start : 0,
                                                            limit : 10
                                                        },
                                                        callback : function(rs,
                                                            opts, success) {
                                                            gridMovimientos.getStore().load();
                                                            ventanaMod.hide();
                                                        },
                                                        scope : this
                                                    });
                                                } else {
                                                    alert('No cargo la info guarda usuario');
                                                }
                                            },
                                            scope : this
                                        });
                                    } else {
                                        alert('Error con la siguiente advertencia: '+data.data);
                                        AdmonUsuarios.findAllUsuXExpLogin({
                                            callback : function (data){
                                                if (data.success) {
                                                    storeUsuariosXExp.proxy.data = data.data;
                                                    storeUsuariosXExp.load({
                                                        params : {
                                                            start : 0,
                                                            limit : 10
                                                        },
                                                        callback : function(rs,
                                                            opts, success) {
                                                            gridMovimientos.getStore().load();
                                                            ventanaMod.hide();
                                                        },
                                                        scope : this
                                                    });
                                                } else {
                                                    alert('No cargo la info guarda usuario');
                                                }
                                            },
                                            scope : this
                                        });
                                    }
                                },
                                scope : this
                            });
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

            ventanaModificar = new Ext.Window({
                layout : 'anchor',
                title : "Modificación de acceso",
                autoHeight: true,
                width: 450,
                draggable: true,
                modal: true,
                closable:false,
                resizable: false,
                window : {
                    layout : 'anchor'
                },
                items : [{
                    xtype : 'panel',
                    border : true,
                    id:'Modificar',
                    name:'Modificar',
                    frame:true,
                    layout:'form',
                    autoHeight:true,
                    align : 'center',
                    items:[txtiCveUSUARIOModificar,
                    txtICVEEXPEDIENTEModificar,comboDedosModificar],
                    buttonAlign : 'center',
                    buttons: [{
                        text:'Modificar',
                        tooltip : 'Modificar registro',
                        iconCls:'icon-disk',
                        id:'modificarBoton',
                        handler: function(){
                            var usuarioSesion = document.getElementById('iCvePersonal').value;
                            AdmonUsuarios.modificaAccesoUsuario(txtiCveUSUARIOModificar.getValue(),txtICVEEXPEDIENTEModificar.getValue(), comboDedosModificar.getValue(),usuarioSesion,{
                                callback : function (data){
                                    if (data.success) {
                                        alert('Se realizo la modificación exitosamente');
                                        AdmonUsuarios.findAllUsuXExpLogin({
                                            callback : function (data){
                                                if (data.success) {
                                                    storeUsuariosXExp.proxy.data = data.data;
                                                    storeUsuariosXExp.load({
                                                        params : {
                                                            start : 0,
                                                            limit : 10
                                                        },
                                                        callback : function(rs,
                                                            opts, success) {
                                                        },
                                                        scope : this
                                                    });
                                                } else {
                                                    alert('No cargo la info guarda usuario');
                                                }
                                            },
                                            scope : this
                                        });;
                                    } else {
                                        alert('Se guardo la modificación con la siguiente advertencia: '+data.data);
                                        AdmonUsuarios.findAllUsuXExpLogin({
                                            callback : function (data){
                                                if (data.success) {
                                                    storeUsuariosXExp.proxy.data = data.data;
                                                    storeUsuariosXExp.load({
                                                        params : {
                                                            start : 0,
                                                            limit : 10
                                                        },
                                                        callback : function(rs,
                                                            opts, success) {

                                                        },
                                                        scope : this
                                                    });
                                                } else {
                                                    alert('No cargo la info guarda usuario');
                                                }
                                            },
                                            scope : this
                                        });;
                                    }
                                },
                                scope : this
                            });
                        }
                    },{
                        text:'Cancelar',
                        tooltip : 'Cancelar Modificacion',
                        iconCls:'icon-cancel',
                        id:'cancelarBoton',
                        handler: function(){
                            ventanaModificar.hide();
                        }
                    }]
                }]
            });


            var filterRow = new Ext.ux.grid.FilterRow({
                autoFilter: true,
                listeners: {
                    change: function(data) {
                        storeUsuariosXExp.load({
                            params: data
                        });
                    }
                }
            });

            var gridMovimientos = new Ext.grid.GridPanel({
                id : 'gridMovimientos',
                renderTo: Ext.getBody(),
                name : 'gridMovimientos',
                autoScroll : true,
                title:'Consulta',
                anchor: '100%',
                frame: true,
                autoHeight : true,
                monitorResize: true,
                ds : storeUsuariosXExp,
                viewConfig : new Ext.grid.GridView({
                    forceFit : true
                }),
                loadMask: true,
                scrolls: true,
                tbar : [
                '-','-',{
                    text:'Agregar',
                    tooltip : 'Agregar asignación',
                    iconCls : 'iconoAdd',
                    id:'agregarBoton',
                    handler: function(){
                        searchUsuario.setValue("");
                        searchExpediente.setValue("");
                        comboDedosModificar.setValue(comboDedos.store.getAt(1).get('idDedo'));
                        ventanaMod.show();
                    }
                },'-','-',{
                    text:'Modificar',
                    tooltip : 'Modificar asignación',
                    iconCls : 'icon_pencil',
                    id:'modificarBoton'
                    ,
                    handler:function(){
                        var registro = Ext.getCmp('gridMovimientos').getSelectionModel().getSelected();
                        if(registro != null && registro !=""){
                            txtiCveUSUARIOModificar.setValue(registro.get('ICVEUSUARIO'));
                            txtICVEEXPEDIENTEModificar.setValue(registro.get('ICVEEXPEDIENTE'));
                            comboDedosModificar.setValue(comboDedosModificar.store.getAt(registro.get('IDEDO')-1).get('idDedo'));
                            ventanaModificar.show();
                        }else{
                            alert("Selecciona un registro de la lista para poder modificarlo");
                        }
                    }
                }/*,'-','-',{
                    text:'Eliminar',
                    tooltip : 'Eliminar asignación',
                    iconCls : 'icon-delete',
                    id:'eliminarBoton'
                //,handler: this.busquedaMovimientos
                },'-','-'*/
                ],
                selectionModel : new Ext.grid.RowSelectionModel({
                    singleSelect : true
                }),
                cm:new Ext.grid.ColumnModel([
                    new Ext.grid.RowNumberer(),
                    {
                        header: 'Usuario',
                        width: 30,
                        sortable: true,
                        dataIndex: 'CUSUARIO',
                        filter: {}
                    },

                    {
                        header: 'Expediente',
                        width: 30,
                        sortable: true,
                        dataIndex : 'ICVEEXPEDIENTE',
                        filter: {}
                    },

                    {
                        header: 'Fecha de registro',
                        width: 35,
                        sortable: true,
                        dataIndex : 'TSCAPTURA',
                        filter: {}
                    },

                    {
                        header: 'Usuario registro',
                        align: 'center',
                        width: 30,
                        sortable: true,
                        dataIndex : 'CCVEUSUARIOREGISTRO',
                        filter: {}
                    },

                    {
                        header: 'Dedo para login',
                        width: 20,
                        align: 'center',
                        sortable: true,
                        dataIndex : 'IDEDO'
                    }]),
                plugins: [filterRow],
                bbar : new Ext.PagingToolbar({
                    pageSize : 10,
                    store : storeUsuariosXExp,
                    displayInfo : true,
                    emptyMsg : 'No existen elementos'
                }),
                listeners : {
                    click : function (){
                        var registro = Ext.getCmp('gridMovimientos').getSelectionModel().getSelected();
                    }
                }
            });

            var viewport = new Ext.Viewport({
                layout : 'accordion',
                title: 'BUSQUEDAS DE USUARIOS BLOQUEADOS',
                items: [{
                    xtype : 'panel',
                    title: 'Busqueda de medico bloqueado',
                    border : true,
                    id:'BuscarUsuario',
                    name:'BuscarUsuario',
                    frame:true,
                    layout:'form',
                    autoHeight:true,
                    align : 'center',
                    items:[txtCUSUARIOBuscar/*,txtICVEUSUARIOBuscar,txtCNOMBREBuscarUsu,txtCAPPATERNOBuscarUsu,txtCAPMATERNOBuscarUsu*/,gridUsuarios]
                },{
                    xtype : 'panel',
                    title: 'Busqueda de paciente bloqueado',
                    border : true,
                    id:'BuscarExpediente',
                    name:'BuscarExpediente',
                    frame:true,
                    layout:'form',
                    autoHeight:true,
                    align : 'center',
                    items:[txtICVEEXPEDIENTEBuscar/*,txtICRFCBuscar,txtCNOMBREBuscarExp,txtCAPPATERNOBuscarExp,txtCAPMATERNOBuscarExp*/,gridExpedientes]
                }]
            });
        
        //ventanaBuscaUsuario.show();
        //ventanaBuscaExpediente.show();
        },
        cargarListaUsuariosXExpediente : function(){
            AdmonUsuarios.findAllUsuXExpLogin({
                callback : function (data){
                    if (data.success) {
                        //Ext.Msg.hide();
                        //alert("traigo datos" +  data.data);
                        storeUsuariosXExp.proxy.data = data.data;
                        storeUsuariosXExp.load({
                            params : {
                                start : 0,
                                limit : 10
                            },
                            callback : function(rs,
                                opts, success) {
                            //Ext.getCmp('botonModificarC').setDisabled(true);
                            },
                            scope : this
                        });
                    } else {
                        alert('No cargo la info de los USXXEXP');
                    }
                },
                //async : false,
                scope : this
            });
        }
    } //end return main object
}(); //end main object

Ext.EventManager.onDocumentReady(pg070107092.init, pg070107092, true);
