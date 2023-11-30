
var frm;
var aPERDatosINT;
var cNombre;

var aReqTot;
var tCpoListado;

function fBefLoad(){
  cPaginaWebJS = "pg13032100000.js";
    cTitulo = "Integraci�n de Requisitos";
}
function fDefPag(){
  fInicioPagina(cColorGenJS);
  Estilo("A:Link","COLOR:RED;font-family:Verdana;font-size:8pt;font-weight:bold;TEXT-DECORATION:none");
  Estilo("A:Visited","COLOR:RED;font-family: Verdana;font-size:8pt;font-weight:bold;TEXT-DECORATION:none");
  InicioTabla("",0,"100%","");
    ITRTD("ESTitulo",0,"100%","1","center");
      Hidden("hdClase","");
      Hidden("hdUri","");
      Hidden("hdCampoValor","");
      Hidden("hdReqExtras","");
      Hidden("hdIren","");
      Hidden("hdIreq","");
      Hidden("CRFC","");
      Hidden("ICVEPERSONAL","");
      Hidden("ICVETIPOLICENCIA","");
      Hidden("ICVETIPOTRALIC","");
      Hidden("ICVECAPACIDAD","");
      Hidden("FILTROTIPOLIC","");
      Hidden("FILTROTIPOTRAMITES","");
      Hidden("ICVEREQUISITO","");
      Hidden("hdEntregaCaps","");
      //Hidden("ICVEUSUARIO",""+top.fGetUsrID());
      Hidden("ICVEUSUARIO","");
      Hidden("Observaciones","");
      Hidden("iCveTramiteCIS","");
      Hidden("cCatego","");
      TextoSimple(cTitulo);
    FTDTR();
    ITRTD("EEtiquetaC",0,"100%","1","center");
      InicioTabla("",0,"","","","",".1",".1");
      TextoSimple("N�MERO DE PERSONAL (N�mero M�dico): ");
      Text(true,'ICVESOLICITUD','',10,10,'','','onKeyDown="return fCheckReturn(event);"');
      BtnImg('buscar','lupa','fBuscar();','');
      FinTabla();
    FTDTR();
    
    ITRTD("EEtiquetaC",0,"100%","1","center");
    InicioTabla("",0,"","","","",".1",".1");
    ITRTD("EPersona",0,"100%","20","center");    
    	Etiqueta("LBLSolicitante","EPersona","");    
    FTDTR();
    FinTabla();
    FTDTR();
    
    ITRTD("EEtiquetaC",0,"100%","1","center");
    	DinTabla("TCue","ETablaInfoLST",1,'90%','','','','','.1','.1');
    FTDTR();
    ITRTD("",0,"","100%","center","top");
      IFrame("IPanel","95%","34","Paneles.js");
    FTDTR();
  FinTabla();   
  fFinPagina();
}
function fOnLoad(){
  //fsObj = new ActiveXObject("Scripting.FileSystemObject");
  frm = document.forms[0];
  oLBLSolicitante = (document.all)?document.all.LBLSolicitante:document.getElementById("LBLSolicitante");
  theTable = (document.all) ? document.all.TCue:document.getElementById("TCue");
  tCpoListado= theTable.tBodies[0];
  
  FRMPanel = fBuscaFrame("IPanel");
  FRMPanel.fSetControl(self,cPaginaWebJS);
  FRMPanel.fShow(",");
  FRMPanel.fSetTraStatus("Disabled");
  
  // fPagFolder(1);
  fShowCue([]);
  // fShowPer([]);
}
function fFolderOnChange( iPag ){
  if(iPag > 1){
    if(fSoloNum2(frm.ICVESOLICITUD.value)==true){
       FRMHuella = fBuscaFrame("PEM3");
       FRMHuella.fSetICVEPERSONAL(frm.ICVEPERSONAL.value);       
    }else{
      fAlert('\n - Debe buscar una solicitud.');
      return false;
    }
  }
  if(iPag == 4){
    FRMCF = fBuscaFrame("PEM4");  
    FRMCF.fCapturar();
  }
}
function fBuscar(){
  if(fSoloNum2(frm.ICVESOLICITUD.value,10)==true && frm.ICVESOLICITUD.value != ""){
    frm.hdOrden.value = ''; 
    frm.hdFiltro.value = 'ICVEPERSONAL = '+frm.ICVESOLICITUD.value;
    frm.hdNumReg.value = 10000;
      fEngSubmite('pgLICDIGITA.jsp','solicitud');
    }else{
      fAlert("- Por favor coloque el N�mero de Personal o N�mero M�dico en la casilla correctamente.");
      frm.ICVESOLICITUD.value = '';
      FRMPanel.fSetTraStatus("Disabled,");
      oLBLSolicitante.innerText = "";
      fShowCue([]);
    }
  }  

function fBuscarRequistos(){
	fEngSubmite('pgLICREQAPOYOSECTOR.jsp','requisitosApoyoSector');
}

 // RECEPCI�N de Datos de provenientes del Servidor
function fResultado(aRes,cId,cError,cNavStatus,iRowPag,cLlave,aCampos,cNombre){ 
  if(cError != "")
     fAlert("\n Existi� un Error en la aplicaci�n, consulte a su �rea de sistemas.");
     
  if(cId == "solicitud" && cError==""){
     frm.ICVEPERSONAL.value = aRes[0][0];
     frm.CRFC.value = aRes[0][1];
     cNombre = aRes[0][4]+' '+aRes[0][5]+' '+aRes[0][6];     
     oLBLSolicitante.innerText = "SOLICITANTE: "+aRes[0][0]+' - '+aRes[0][1]+' '+aRes[0][2]+' - '+
                                 aRes[0][3]+'   '+cNombre;     
     fBuscarRequistos();      
  }
  
  if(cId == "requisitosApoyoSector" && cError==""){
	     //alert("aqui vamos a consultar los datos de los req de apoyo al sector");
	     //fShowCue([]);

	     fShowCue(aRes,aCampos,cNombre);
  }
  
  if(cId == "Guardar" && cError==""){
	 FRMPanel.fSetTraStatus("Disabled,");
     fAlert("\n - La integraci�n de requisitos se ha realizado exitosamente.");
  }
	  
  if(cId == "DATOSINT"){
     if(aRes.length > 0){
        aPERDatosINT = aRes;
        fAbreSubWindow(true,'pg13031010000A',"no","no","no","yes",'780','580',100,100);
     }              
   }   
}
 
 
function fShowCue(aCue,aCampos,cNombre){	
	lDeshab = false;
    for(i=0;tCpoListado.rows.length;i++){
        tCpoListado.deleteRow(0);
    }
    if(aCue.length > 0){
        aReqTot = aCue;
        iRow = 0;
        iCell = 0;
        newRow  = tCpoListado.insertRow(iRow++);
        newRow.className = "ETablaST";                                    
        
        newCell = newRow.insertCell(iCell++);newCell.innerHTML = 'Documentos<BR>Digitalizados';newCell.colSpan = 2;
        newCell = newRow.insertCell(iCell++);newCell.innerHTML = 'Requisito';newCell.colSpan = 2;
        newCell = newRow.insertCell(iCell++);newCell.innerHTML = 'Observaciones';
               
        jkl=0;
        countComboCaps = 0;

        for(i=0, l=0;l<aCue.length;i++,l++){
            aDato = aCue[l];
            //aField = aCampos[l];
            indiceTipoLicencia = 0;
                     
                newRow  = tCpoListado.insertRow(iRow++);
                iCell = 0;
                
                newRow.className = 'EEtiquetaC';
                if((i%2) == 0)
                    newRow.style.backgroundColor='FFFFFF';

                newCell = newRow.insertCell(iCell++);
                
                iNumDoctoReq = 0;
                tsRecibe = '';
                for(y=0;y<aCampos.length;y++){
                	if(aCampos[y][1] == aDato[1]){
                		iNumDoctoReq = aCampos[y][3];
                		tsRecibe = aCampos[y][5];
                	}
                }
                
                if(iNumDoctoReq != 0){
                    newCell.innerHTML = Liga('�ltimo<BR>Documento<BR>Digitalizado<br><br>'+tsRecibe,'fDownload('+i+','+iNumDoctoReq+');','Mostrar el Documento...');                                           
                }else{
                	newCell.innerHTML = "No se ha digitalizado documento";
                }
                
                newCell = newRow.insertCell(iCell++);	
                newCell.innerHTML = Liga('Digitalizar<BR>Nuevo Documento','fUpload('+i+','+aDato[1]+');','Digitalizar el Documento...')+TextoSimple("<BR>"+cRutaDocElic + "s"+frm.ICVEPERSONAL.value+"r"+aDato[1]+cExtDocElic);
                
                newCell = newRow.insertCell(iCell++);
                newCell.className = 'EEtiquetaC';
                newCell.width = 10;
                newCell.innerHTML = aDato[1];
                
                newCell = newRow.insertCell(iCell++);
                newCell.innerHTML = InicioTabla("EPie",0,"99%","100%")+ITRTD()+aDato[7]+FTDTR()+FinTabla();
                
                newCell = newRow.insertCell(iCell++);
                newCell.className = 'EEtiquetaL';
                newCell.innerHTML = AreaTexto(false,30,4,"Obs-"+i,"","","fMayus(this);","fMayus(this);","",false,false);
                for(GH=0;GH<frm.elements.length;GH++){
                    if(frm.elements[GH].name == "Obs-"+i){
                        frm.elements[GH].value = aDato[10];
                    }
                }
                           
                FRMPanel.fSetTraStatus("UpdateBegin");
        }

        newRow  = tCpoListado.insertRow(iRow++);
        
    }
    if(lDeshab){
    	fDisabled(lDeshab,"ICVESOLICITUD,");
        FRMPanel.fSetTraStatus("Disabled");
    }
}
function fGetPERDatosINT(){
   return aPERDatosINT;   
}
function fGetCNOMBRE(){
   return cNombre;
}
function fBuscaIntegrar(){      
       frm.hdOrden.value = '';
       frm.hdNumReg.value = '10000';
       frm.hdFiltro.value = "CRFC = '" + frm.CRFC.value + "'";              
       fEngSubmite("pgPERDATOSINT.jsp","DATOSINT");
}
function fCheckReturn(evt){
    evt=(evt) ? evt : window.event;
    var charCode=(evt.which)?evt.which:evt.keyCode;
    if(charCode==13){
      fBuscar();
      return false;
    }
    return true;
}
function fContinue(){
}
function fSoloNum2(cVCadena){
        if(cVCadena == "") return false;        
	for(var i= 0; i < cVCadena.length; i ++){
		if(!(cVCadena.charAt(i) == '0' ||
		cVCadena.charAt(i) == '1' ||
		cVCadena.charAt(i) == '2' ||
		cVCadena.charAt(i) == '3' ||
		cVCadena.charAt(i) == '4' ||
		cVCadena.charAt(i) == '5' ||
		cVCadena.charAt(i) == '6' ||
		cVCadena.charAt(i) == '7' ||
		cVCadena.charAt(i) == '8' ||
		cVCadena.charAt(i) == '9')){
			return false;
		}
	}
    return true;
}

function fUpload(iRen,iReq){
	   frm.hdIren.value = iRen;
	   frm.hdIreq.value = iReq;
	   frm.ICVEREQUISITO.value = iReq;
	   frm.ICVEUSUARIO.value = ""+top.fGetUsrID();
	   frm.Observaciones.value = ""+fGetObjValor("Obs-"+iRen);
	   fAbreSubWindow(true,"pgLoadDocSect",'no','yes','no','yes',450,140,100,100);
	}

function fGetObj(cName){
	   for(goi=0;goi<form.elements.length;goi++){
	      obj = form.elements[goi];
	      if(obj.name == cName)
	         return obj;
	   }
	   return "";
}

function fGetObjValor(cName){
	   for(goi=0;goi<form.elements.length;goi++){
	      obj = form.elements[goi];
	      //alert(obj.name);
	      //alert(obj.value);
	      if(obj.name == cName)
	         return obj.value;
	   }
	   return "";
}




function fSelAll(){ 
	for (i=0; i<form.elements.length;i++){
        obj = form.elements[i];
        if (obj.type == 'checkbox' && obj.disabled == false){
        	if(frm.lTodos.checked == true){
        		obj.checked = true;
        	}else{
        		obj.checked = false;   
        	}
        }
	}
}

function fDownload(iRen,iReq){
	   frm.hdIren.value = iRen;
	   frm.hdIreq.value = iReq;
	   frm.ICVEREQUISITO.value = iReq;
	   fAbreSubWindow(true,"pgDownDocSect",'no','yes','no','yes',300,300,100,100);
}

function fImprimir() {   
	self.focus();
    window.print(); 
}

function fCancelar() {   
	frm.ICVESOLICITUD.value = '';
    FRMPanel.fSetTraStatus("Disabled,");
    oLBLSolicitante.innerText = "";
    fShowCue([]); 
}

function fGuardar() {
	   cMsg = '';
	   cMsg1 = '';
	   cMsg2 = '';
	   aReqs = new Array();
	   aObs  = new Array();
	   frm.hdCampoValor.value = '';
	   frm.hdReqExtras.value = '';
	   j=0;k=0;
	   var countCheck = 0;
	   var checkOpcional = false;
	   for(i=0;i<frm.elements.length;i++){
	     if(frm.elements[i].type == 'checkbox'){
	        if(countCheck != 0 && frm.elements[i].checked == false){
	          cMsg2 = '\n - No todos los requisitos han sido integrados.';
	          aReqs[j++] = 0;
	        } else {
	      	  aReqs[j++] = 1;
	          countCheck++;
	        } 
	     }
	     if(frm.elements[i].type == 'textarea'){
	        aObs[k++] = frm.elements[i].value;
	     }
	   }
	   cMsg = cMsg1 + cMsg2;
	   if(cMsg != ''){
	      if(confirm(cMsg + "\n�Desea continuar con el tr�mite?") == false)
	         return;
	   }
	   for(i=1;i<tCpoListado.rows.length-1;i++){
		   frm.hdCampoValor.value += aReqs[i] + "|" + tCpoListado.rows[i].cells[3].innerText
		   							+ "|" + tCpoListado.rows[i].cells[5].innerText + "|";
	   }
	   fEngSubmite("pgLICSOLREQ_SECTOR.jsp","Guardar");
}


function fGetICVESOLICITUD(){
		var ICVESOLICITUD = frm.ICVESOLICITUD.value;
	   return ICVESOLICITUD;   
}

function fGetICVEREQUISITO(){
	var ICVEREQUISITO = frm.ICVEREQUISITO.value;
   return ICVEREQUISITO;   
}

function fGetUSUARIO(){
	var ICVEUSUARIO = ""+top.fGetUsrID();
   return ICVEUSUARIO;   
}

function fGetObserva(){
	var ren = frm.hdIren.value;
	var Observa = ""+fGetObjValor("Obs-"+ren);
	//alert("5"+Observa);
   return Observa;   
}



function fSetNomFile(nombre){
	var NomFile = "s"+frm.ICVEPERSONAL.value+"r"+aDato[1]+cExtDocElic;
   return NomFile;   
}
         
function fRecarga(carga){
	  fBuscar();
	  if(carga == true){   
		  alert('\n - La digitalizaci�n ha sido satisfactoria.\n');		  
	  }else{
	    alert('\n - La digitalizaci�n no se ha realizado, favor de intentarlo nuevamente.');
	  }
}

function fCerrar(carga){
	wExp.close();
}


