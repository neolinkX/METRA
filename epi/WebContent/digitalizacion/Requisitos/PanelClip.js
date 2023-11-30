// MetaCD=1.0 
 // Title: PanelClip.js
 // Description: JS que genera el panel de forma NO RECARGABLE por IFrames
 // Company: Tecnología InRed S.A. de C.V. 
 // Author: JESR
function fPanel(){ // Define la página a ser mostrada 
   cTxt=Estilo("A:Link","COLOR:BLACK;font-family:Verdana;font-size:10pt;TEXT-DECORATION:none;font-weight:Bold;");
   cTxt+=Estilo("A:Visited","COLOR:BLACK;font-family: Verdana;font-size:10pt;TEXT-DECORATION:none;font-weight:Bold;");
   cTxt+=Estilo(".EDisPanel","COLOR:GRAY;font-family:Verdana;font-size:10pt;text-align:center;border:1px solid #6B96AD;padding:0px;spacing:0px;background-color: #"+cColorGenJS+";");
   cTxt+=Estilo(".ERegla","border: 1px solid #6B96AD;padding: 0px;spacing: 0px;");      
   cTxt+=Estilo(".ETablaInfoPanel","border:1px solid #6B96AD;background-color:#"+cColorBGPanel+";text-align:center;");   
   cTxt+=DinTabla("TPnl","",0,"","34","center","top","",".1",".1");
   return cTxt;
}
function fPanelOnLoad(aPanel,docRef){
   theTable=(docRef.all) ? docRef.all.TPnl:docRef.getElementById("TPnl");   
   tCPnl=theTable.tBodies[0];
   aPanel[0]=tCPnl;// Objeto Tabla
   aPanel[1]="";// cEstatusGral
   aPanel[2]="";// cEstadoActual    
   aPanel[3]=false;// lDeshabTra
   aPanel[4]=false;// lModificar     
}
function fPanelShow(aPanel,cEstatus,lHabDes){
   tCPnl = aPanel[0];
   lDeshabTra = aPanel[3];   
   aPanel[1]=cEstatus;// cEstatusGral        
   if(lHabDes != true){
      lNuevo=false;lGuardar=false;lActualizar=false;lCancelar=false;lBorrar=false;
   }
   for(i=0;tCPnl.rows.length;i++){
     tCPnl.deleteRow(0);
   }
   if(cEstatus){
     iNumEst=fNumEntries(cEstatus,',');
     if(iNumEst > 0){
       lPTra=false;lPRep=false;
       for(i=0;i<iNumEst;i++){
          if(fEntry(i+1,cEstatus,",")=="Tra") lPTra=true;
          if(fEntry(i+1,cEstatus,",")=="Rep") lPRep=true;
       }
     }
   }else{
     lPTra=true;lPRep=true;
   }
   i=0;
   Rw=tCPnl.insertRow(0);
   if(lPTra==true && lDeshabTra==false){
     Cll=Rw.insertCell(i++);Cll.width="80";
     if(lNuevo == true){Cll.className="ETablaInfoPanel"; Cll.innerHTML=Liga("Nuevo","top.fPanelActual('Nuevo',aPanel,self)","Nuevo...");}
     else{Cll.className="EDisPanel";Cll.innerHTML=TextoSimple("Nuevo");}          
     Cll=Rw.insertCell(i++);Cll.width="80";
     if(lGuardar == true){Cll.className = "ETablaInfoPanel"; Cll.innerHTML=Liga("Guardar","top.fPanelActual('Guardar',aPanel,self)","Guardar...");}
     else{Cll.className="EDisPanel";Cll.innerHTML=TextoSimple("Guardar");} 
     Cll=Rw.insertCell(i++);Cll.width="80";
     if(lActualizar == true){Cll.className = "ETablaInfoPanel"; Cll.innerHTML=Liga("Modificar","top.fPanelActual('Modificar',aPanel,self)","Modificar...");}
     else{Cll.className="EDisPanel";Cll.innerHTML=TextoSimple("Modificar");}     
     Cll=Rw.insertCell(i++);Cll.width="80";
     if(lCancelar == true){Cll.className = "ETablaInfoPanel"; Cll.innerHTML=Liga("Cancelar","top.fPanelActual('Cancelar',aPanel,self)","Cancelar...");}
     else{Cll.className="EDisPanel";Cll.innerHTML=TextoSimple("Cancelar");}     
     Cll=Rw.insertCell(i++);Cll.width="80";
     if(lBorrar == true){Cll.className = "ETablaInfoPanel"; Cll.innerHTML=Liga("Borrar","top.fPanelActual('Borrar',aPanel,self)","Borrar...");}
     else{Cll.className="EDisPanel";Cll.innerHTML=TextoSimple("Borrar");}     
     Cll=Rw.insertCell(i++);
     Cll.innerHTML=InicioTabla("ERegla",0,"0","100%","","",".1",".1")+ITRTD()+FTDTR();
   }
   Cll=Rw.insertCell(i++);Cll.className = "ETablaInfoPanel";Cll.width="100";   
   Cll.innerHTML=InicioTabla("",0)+ITRTD()+Liga("- Imprimir -","top.fPanelActual('Imprimir',aPanel,self)","Imprimir...")+FTDTR();
   if(lPRep==true && lDeshabTra==false){
     Cll=Rw.insertCell(i++);
     Cll.innerHTML=InicioTabla("ERegla",0,"0","100%","","",".1",".1")+ITRTD()+FTDTR();  
     Cll=Rw.insertCell(i++);Cll.className = "ETablaInfoPanel";Cll.width="100";
     Cll.innerHTML=InicioTabla("",0)+ITRTD()+Liga("- Reporte -","top.fPanelActual('Reporte',aPanel,self)","Reporte...")+FTDTR();
   }
}
function fPanelSetControl(cPW,aPanel){
   if(cPW && top.fGetPermiso){
     if(top.fGetPermiso(cPW) == 0)    
       aPanel[3]=true;// lDeshabTra=true;
   }
}
function fPanelActual(cTipo,aPanel,FRMCtrl){
        docRef = FRMCtrl.document;
        docRef.forms[0].hdBoton.value=cTipo;       
        if(cTipo=='Nuevo'){
           aPanel[4]=false;
           FRMCtrl.fNuevo();
           FRMCtrl.fOnFocus();
        }       
        if(cTipo=='Guardar'){
            if(aPanel[4]==false){
              docRef.forms[0].hdBoton.value="Guardar";
              FRMCtrl.fGuardar();
            }else{
              docRef.forms[0].hdBoton.value="GuardarA";
              FRMCtrl.fGuardarA();
            }
        }
        if(cTipo=='Modificar'){
           aPanel[4]=true;
           FRMCtrl.fModificar();
           FRMCtrl.fOnFocus();
        }
        if(cTipo=='Cancelar'){
           aPanel[4]=false;
           FRMCtrl.fCancelar();
        }
        if(cTipo=='Borrar'){
           aPanel[4]=false;
           FRMCtrl.fBorrar;
        }
        if(cTipo=='Imprimir'){
           FRMCtrl.fImprimir();
        }
        if(cTipo=='Reporte'){
           FRMCtrl.fReporte();
        }
        docRef.forms[0].hdBoton.value="";
}

function fPanelSetTraStatus(cTraStatus,aPanel){
if(aPanel[2] != cTraStatus){
  aPanel[2] = cTraStatus;  
  lNuevo=false;lGuardar=false;lActualizar=false;lCancelar=false;lBorrar=false;
    if(cTraStatus=='UpdateBegin'){
      lNuevo=false;lGuardar=true;lActualizar=false;lCancelar=true;lBorrar=false;
    }
    if (cTraStatus=='UpdateComplete'){
      lNuevo=true;lGuardar=false;lActualizar=true;lCancelar=false;lBorrar=true;aPanel[4]=false;
    }
    if (cTraStatus=='AddOnly'){
      lNuevo=true;lGuardar=false;lActualizar=false;lCancelar=false;lBorrar=false;aPanel[4]=false;
    }
    if (cTraStatus=='Disabled'){
      lNuevo=false;lGuardar=false;lActualizar=false;lCancelar=false;lBorrar=false;aPanel[4]=false;
    }
    iNumEst=fNumEntries(cTraStatus,',');
    if(iNumEst > 0){
       for(i=0;i<iNumEst;i++){
          if(fEntry(i+1,cTraStatus,",")=="Add")
            lNuevo=true;
          if(fEntry(i+1,cTraStatus,",")=="Sav")
            lGuardar=true;
          if(fEntry(i+1,cTraStatus,",")=="Mod")
            lActualizar=true;
          if(fEntry(i+1,cTraStatus,",")=="Can")
            lCancelar=true;
          if(fEntry(i+1,cTraStatus,",")=="Del")
            lBorrar=true;
       }
    }
    fPanelShow(aPanel,aPanel[1],true);
  }
}
