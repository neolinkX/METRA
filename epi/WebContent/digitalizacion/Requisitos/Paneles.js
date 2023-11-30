// MetaCD=1.0
var FRMCtrl;
var tCPnl;
var frm;
var cNavega = '';
var lModificar=false;
var lDeshabTra=false;
var lNuevo;
var lGuardar;
var lActualizar;
var lCancelar;
var lBorrar;
var cEstatusGral;
var cEstadoActual;
function fBefLoad(){
  cPaginaWebJS = "Paneles.js";
}
function fDefPag(){ // Define la página a ser mostrada
   Estilo("A:Link","COLOR:BLACK;font-family:Verdana;font-size:10pt;TEXT-DECORATION:none;font-weight:Bold;background-color: #"+cColorBGPanel +";");
   Estilo("A:Visited","COLOR:BLACK;font-family: Verdana;font-size:10pt;TEXT-DECORATION:none;font-weight:Bold;background-color: #"+cColorBGPanel +";");
   Estilo(".EDisPanel","COLOR:GRAY;font-family:Verdana;font-size:10pt;text-align:center;border:1px solid #6B96AD;padding:0px;spacing:0px;background-color: #"+cColorGenJS+";");
   Estilo(".ERegla","border: 1px solid #6B96AD;padding: 0px;spacing: 0px;");      
   Estilo(".ETablaInfoPanel","border:1px solid #6B96AD;background-color:#"+cColorBGPanel+";text-align:center;");   
   fInicioPagina(cColorGenJS);
   InicioTabla("",0,"100%","34","center","",".1",".1");
   ITRTD("",0,"","100%","center","top");
     InicioTabla("",0,"","100%","","",".1",".1");
       ITRTD();
         DinTabla("TPnl","",0,"","100%","center","top","",".1",".1");
       FTDTR();
     FinTabla();
   FTDTR();
   FinTabla();
   cGPD+='</form></body></html>';
}
function fOnLoad(){
   frm=document.forms[0];
   theTable=(document.all) ? document.all.TPnl:
   document.getElementById("TPnl");
   tCPnl=theTable.tBodies[0];
}
function fShow(cEstatus,lHabDes){
   cEstatusGral = cEstatus;    
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
     if(lNuevo == true){Cll.className="ETablaInfoPanel"; Cll.innerHTML=Liga("Nuevo","fActual(document.forms[0].Nuevo,'Nuevo')","Nuevo...");}
     else{Cll.className="EDisPanel";Cll.innerHTML=TextoSimple("Nuevo");}          
     Cll=Rw.insertCell(i++);Cll.width="80";
     if(lGuardar == true){Cll.className = "ETablaInfoPanel"; Cll.innerHTML=Liga("Guardar","fActual(document.forms[0].Guardar,'Guardar')","Guardar...");}
     else{Cll.className="EDisPanel";Cll.innerHTML=TextoSimple("Guardar");} 
     Cll=Rw.insertCell(i++);Cll.width="80";
     if(lActualizar == true){Cll.className = "ETablaInfoPanel"; Cll.innerHTML=Liga("Modificar","fActual(document.forms[0].Actualizar,'Modificar')","Modificar...");}
     else{Cll.className="EDisPanel";Cll.innerHTML=TextoSimple("Modificar");}     
     Cll=Rw.insertCell(i++);Cll.width="80";
     if(lCancelar == true){Cll.className = "ETablaInfoPanel"; Cll.innerHTML=Liga("Cancelar","fActual(document.forms[0].Cancelar,'Cancelar')","Cancelar...");}
     else{Cll.className="EDisPanel";Cll.innerHTML=TextoSimple("Cancelar");}     
     Cll=Rw.insertCell(i++);Cll.width="80";
     if(lBorrar == true){Cll.className = "ETablaInfoPanel"; Cll.innerHTML=Liga("Borrar","fActual(document.forms[0].Borrar,'Borrar')","Borrar...");}
     else{Cll.className="EDisPanel";Cll.innerHTML=TextoSimple("Borrar");}     
     Cll=Rw.insertCell(i++);
     Cll.innerHTML=InicioTabla("ERegla",0,"0","100%","","",".1",".1")+ITRTD()+FTDTR();
   }
   Cll=Rw.insertCell(i++);Cll.className = "ETablaInfoPanel";Cll.width="100";   
   Cll.innerHTML=InicioTabla("",0)+ITRTD()+Liga("- Imprimir -","fActual(document.forms[0].Imprimir,'Imprimir')","Imprimir...")+FTDTR();
   if(lPRep==true && lDeshabTra==false){
     Cll=Rw.insertCell(i++);
     Cll.innerHTML=InicioTabla("ERegla",0,"0","100%","","",".1",".1")+ITRTD()+FTDTR();  
     Cll=Rw.insertCell(i++);Cll.className = "ETablaInfoPanel";Cll.width="100";
     Cll.innerHTML=InicioTabla("",0)+ITRTD()+Liga("- Reporte -","fActual(document.forms[0].Reporte,'Reporte')","Reporte...")+FTDTR();
   }
}
function fSetControl(oControlM,cPW){
   FRMCtrl=oControlM;
   if(cPW && top.fGetPermiso){
     if(top.fGetPermiso(cPW) == 0) lDeshabTra=true;
   }
}
function fActual(obj,cTipo){
  iEstatus=0;//parseInt(obj.src.substring(obj.src.length-5,obj.src.length-4));
  if(iEstatus < 3){
    if(FRMCtrl){
        FRMCtrl.document.forms[0].hdBoton.value=cTipo;
        if(cTipo=='Nuevo'){
          lModificar=false;
          (FRMCtrl.fNuevo)?FRMCtrl.fNuevo():fAlert('FRMCtrl-fNuevo');
          FRMCtrl.fOnFocus();
        }
        if(cTipo=='Guardar'){
            if(lModificar==false){
              FRMCtrl.document.forms[0].hdBoton.value="Guardar";
              (FRMCtrl.fGuardar)?FRMCtrl.fGuardar():fAlert('FRMCtrl-fGuardar');
            }else{
              FRMCtrl.document.forms[0].hdBoton.value="GuardarA";
              (FRMCtrl.fGuardarA)?FRMCtrl.fGuardarA():fAlert('FRMCtrl-fGuardarA');
            }
        }
        if(cTipo=='Modificar'){
          if(FRMCtrl.fModificar){
             lModificar=true;
             FRMCtrl.fModificar();
             FRMCtrl.fOnFocus();
          }else fAlert('FRMCtrl-fModificar');
        }
        if(cTipo=='Cancelar'){
          if(FRMCtrl.fCancelar){
             lModificar=false;
             FRMCtrl.fCancelar();
          }else fAlert('FRMCtrl-fCancelar');
        }
        if(cTipo=='Borrar'){
          lModificar=false;
          (FRMCtrl.fBorrar)?FRMCtrl.fBorrar():fAlert('FRMCtrl-fBorrar');
        }
        if(cTipo=='Imprimir'){
          if(FRMCtrl.fImprimir){
             FRMCtrl.fImprimir();
          }else fAlert('FRMCtrl-fImprimir');
        }
        if(cTipo=='Reporte'){
          if(FRMCtrl.fReporte){
             FRMCtrl.fReporte();
          }else fAlert('FRMCtrl-fReporte');
        }
        FRMCtrl.document.forms[0].hdBoton.value="";
    }else
      fAlert('FRMCtrl-fSetControl');
  }
}

function fSetTraStatus(cTraStatus){
if(cEstadoActual != cTraStatus){
  cEstadoActual = cTraStatus;  
  lNuevo=false;lGuardar=false;lActualizar=false;lCancelar=false;lBorrar=false;
    if(cTraStatus=='UpdateBegin'){
      lNuevo=false;lGuardar=true;lActualizar=false;lCancelar=true;lBorrar=false;
    }
    if (cTraStatus=='UpdateComplete'){
      lNuevo=true;lGuardar=false;lActualizar=true;lCancelar=false;lBorrar=true;lModificar=false;
    }
    if (cTraStatus=='AddOnly'){
      lNuevo=true;lGuardar=false;lActualizar=false;lCancelar=false;lBorrar=false;lModificar=false;
    }
    if (cTraStatus=='Disabled'){
      lNuevo=false;lGuardar=false;lActualizar=false;lCancelar=false;lBorrar=false;lModificar=false;
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
    fShow(cEstatusGral,true);
  }
}
