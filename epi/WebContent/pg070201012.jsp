<%/**
 * Title: Sel
 * Title: SelecciÔøΩn del paciente al servicio
 * Description: SelecciÔøΩn del paciente al servicio
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Romeo Sanchez
 * @version 1
 * Clase: pg070104030
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<html>
<%
	//pg070104035CFG  clsConfig = new pg070104035CFG();                 // modificar
  pg070201012CFG  clsConfig = new pg070201012CFG();                 // modificar
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
//  clsConfig.setUsuario(vUsuario.getICveusuario());
  int user = vUsuario.getICveusuario();
                    
                    
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070201012.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070201012.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "3";                   // modificar
  String cDscOrdenar  = "No Disponible|";    // modificar
  String cCveOrdenar  = "|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "8|";                // modificar 7/8
  boolean lFiltros    = true;                  // modificar

  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  int iCveLabC = new Integer(vParametros.getPropEspecifica("CveLabClin")).intValue();
  

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

	///////////////////////Motivo NEGATIVA A EXAMEN ///////////////////////////////
	int iCveMotivo = clsConfig.getMotivo();
	System.out.println("iCveMotivo = " + iCveMotivo);
	if (iCveMotivo == 57) {
		TDDicNoApto dicNoApto = new TDDicNoApto();
		dicNoApto.insertNoAptoEmo(null,
				request.getParameter("iCveExpediente"),
				request.getParameter("iNumExamen"), user + "");
		/*Cierre
		 request.getParameter("iCveExpediente");
		 request.getParameter("iNumExamen");
		 user
		 pg070105040*/
		System.out.println("***iCveMotivo = " + iCveMotivo);
		pageContext.forward("pg070105040.jsp?hdBoton=Actual"); 
	}

	// si los sintomas que se guardaron corresponden a la ultima rama del servicio,
	// se debe direccionar a la pagina de diagnosticos (pg070104032.jsp)
	//System.out.println("ultima rama: " + clsConfig.ultimaRama);
	if (clsConfig.ultimaRama) {
		//pageContext.forward("pg070104031.jsp?hdBoton=Actual"); //pg070104032.jsp
		pageContext.forward("pg070104071.jsp?hdBoton=Actual");
	}

	//clsConfig.setCPaginas("pg070201011.jsp|");
	clsConfig.verOtrosAccesos();
	String cPaginas = clsConfig.getPaginas();
	String cDscPaginas = clsConfig.getDscPaginas();
	PageBeanScroller bs = clsConfig.getBeanScroller();
	String cUpdStatus = "SaveOnly";
	String cNavStatus = "Hidden";//clsConfig.getNavStatus();
	String cOtroStatus = clsConfig.getOtroStatus();
	String cCanWrite = clsConfig.getCanWrite();
	String cSaveAction = "Guardar";
	String cDeleteAction = "BorrarB";
	String cClave = "";
	String cPosicion = "";

	int iCveUniMed = Integer.parseInt(request
			.getParameter("iCveUniMed") == null ? "0" : request
			.getParameter("iCveUniMed"));
	int iCveProceso = Integer.parseInt(request
			.getParameter("iCveProceso") == null ? "0" : request
			.getParameter("iCveProceso"));
	int iCveModulo = Integer.parseInt(request
			.getParameter("iCveModulo") == null ? "0" : request
			.getParameter("iCveModulo"));
	int iCveServicio = Integer.parseInt(request
			.getParameter("iCveServicio") == null ? "0" : request
			.getParameter("iCveServicio"));
	int iCveRama = Integer
			.parseInt(request.getParameter("iCveRama") == null
					? "0"
					: request.getParameter("iCveRama"));

	TVEXPRama nextRama2;
	nextRama2 = clsConfig.getNextRama2();

	int iCveMdoTrans = clsConfig.getMDOTrans();
	int iCvecategoria = clsConfig.getCategoria();
	String iCveMotivo22 = clsConfig.getMotivo22();
	int iCveRama2 = 0;
	int Dependiente2 = 0;
	TDMEDREGSIN dMEDREGSIN2 = new TDMEDREGSIN();
	TDPERDatos dPERDatos = new TDPERDatos();

    /////Averiguar si la persona ha utilizado lentes anteriormente/////
    TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
    TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
    int UsoLentes = 0;
	UsoLentes = dEXPExamAplica.RegresaInt("Select count(icveexpediente)"+ 
			"from expexamaplica where icveexpediente = "+request.getParameter("iCveExpediente") +" and lusalentes = 1");
	System.out.println("UsoLentes = "+UsoLentes);
	//////////////////////////////////////////////////////////////////////////////

	if (nextRama2 != null) {
		iCveRama2 = nextRama2.getICveRama();
		///EXISTIRAN PREGUNTAS DEPENDIENTES
		String SCWhere2 = "";

		SCWhere2 = "      D.ICVESERVICIO = " + iCveServicio + "  AND"
				+ "                           D.ICVERAMA = "
				+ iCveRama2 + " AND"
				+ "                           D.ICVEMDOTRANS =   "
				+ iCveMdoTrans + " AND		"
				+ "                           D.ICVECATEGORIA =   "
				+ iCvecategoria + "  AND" + " 			  R.ICVEMOTIVO =  "
				+ iCveMotivo + " ";
		try {
			Dependiente2 = dMEDREGSIN2.FindByAllDepEMO(SCWhere2);
		} catch (Exception ex) {
			Dependiente2 = 0;
		}
		if (Dependiente2 > 0) {
			if (iCveRama2 == 0) {
%>
                    <html onMouseOver="fIrVerExamen();" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <%           }else{
    %>
                    <html onMouseOver="mostrardiv();"xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <%                
                 }
      }else{ 
                if(iCveRama2 == 0){
    %>
                    <html onMouseOver="fIrVerExamen();" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <%           }else{    %>
                    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <%                
                 }
      }                  
}else{
        if(Dependiente2 > 0){       %>
            <html onMouseOver="mostrardiv();fIrVerExamen(0, 0,'pg07010407.jsp');" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <%}else{%>
            <html onMouseOver="fIrVerExamen(0, 0,'pg07010407.jsp');" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <%}
}


%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
function truncar(campo){
  campo.value=campo.value.substring(0,499);
return true;
}

//Agregado AG SA 23/04/2013
          function mostrardiv() {
              form = document.forms[0];
              <%
          TDMEDAReg dAReg = new TDMEDAReg();
          TVMEDAReg vAReg = new TVMEDAReg();
          String CondReg = "";
          Vector CReglas = new Vector();
          try{
              CondReg = " T.ICVESERVICIO = "+iCveServicio+" AND"
              + "		T.ICVERAMA = "+iCveRama2+" AND"                        
              + "		T.ICVEMOTIVO = "+iCveMotivo+" AND"
              + "		T.ICVEMDOTRANS = "+iCveMdoTrans+" AND"
              + "		R.ICVECATEGORIA = "+iCvecategoria+" AND"
              + "		T.ICVEPROCESO = 2 ";
              CReglas = dAReg.FindByAll2(CondReg);
          }catch(Exception e){
              
          }
           for(int i=0;i<CReglas.size();i++){
                  vAReg = (TVMEDAReg) CReglas.get(i);
                  if(vAReg.getICveTpoResp()==8 || vAReg.getICveTpoResp()==13){
                      
                      if(iCveServicio == 50 && iCveRama2 == 1 && (vAReg.getICveSintomaDep() == 17 || vAReg.getICveSintomaDep() == 27)){
                              out.println(" if(form.cCaracter_"+iCveRama2+""+vAReg.getICveSintomaDep()+".value >= "+vAReg.getIIgualA()+"){");
                      }else{
                              out.println(" if(form.cCaracter_"+iCveRama2+""+vAReg.getICveSintomaDep()+".value == "+vAReg.getIIgualA()+"){");
                      }
                      
                      out.println("                    div = document.getElementById('fcCaracter_"+iCveRama2+""+vAReg.getICveSintoma()+"');");
                      out.println("                    div.style.display = '';");
                      out.println("                    div = document.getElementById('f2cCaracter_"+iCveRama2+""+vAReg.getICveSintoma()+"');");
                      out.println("                    div.style.display = '';");
                      out.println("            }else{");
                      out.println("                div = document.getElementById('fcCaracter_"+iCveRama2+""+vAReg.getICveSintoma()+"');");
                      out.println("                div.style.display='none';");
                      out.println("                div = document.getElementById('f2cCaracter_"+iCveRama2+""+vAReg.getICveSintoma()+"');");
                      out.println("                div.style.display='none';");
                      out.println("  }");
                   }
                if(vAReg.getICveTpoResp()==1){
                      out.println(" if(form.lLogico_"+iCveRama2+""+vAReg.getICveSintomaDep()+"["+vAReg.getIIgualA()+"].checked){");
                      out.println("                    div = document.getElementById('fcCaracter_"+iCveRama2+""+vAReg.getICveSintoma()+"');");
                      out.println("                    div.style.display = '';");
                      out.println("                    div = document.getElementById('f2cCaracter_"+iCveRama2+""+vAReg.getICveSintoma()+"');");
                      out.println("                    div.style.display = '';");
                      out.println("            }else{");
                      out.println("                div = document.getElementById('fcCaracter_"+iCveRama2+""+vAReg.getICveSintoma()+"');");
                      out.println("                div.style.display='none';");
                      out.println("                div = document.getElementById('f2cCaracter_"+iCveRama2+""+vAReg.getICveSintoma()+"');");
                      out.println("                div.style.display='none';");
                      out.println("  }");
                   }              
                
                if (vAReg.getICveTpoResp() == 3) {
					String condicionIgual= "";
					String condicionMayor = "";
					String condicionMenor = "";
					int CondicionesValidas = 0;
					
					System.out.println("Datos =  Dep -"+vAReg.getICveSintomaDep()+" / Sint - "+vAReg.getICveSintoma()+" / ValorIgual "+vAReg.getIIgualA()+ " o "+vAReg.getFIgualA());
					System.out.println("Datos =  Dep -"+vAReg.getICveSintomaDep()+" / Sint - "+vAReg.getICveSintoma()+" / ValorMayor "+vAReg.getIMayorA()+ " o "+vAReg.getFMayorA());
					System.out.println("Datos =  Dep -"+vAReg.getICveSintomaDep()+" / Sint - "+vAReg.getICveSintoma()+" / ValorMenor "+vAReg.getIMenorA()+ " o "+vAReg.getFMenorA());
					
					
					if(vAReg.getIIgualA() >0){
						condicionIgual = "form.dValorI_"+iCveRama2+""+vAReg.getICveSintomaDep()+".value == "+vAReg.getIIgualA()+" ";
						CondicionesValidas++;
					}
					if(vAReg.getIMayorA() >0){
						if(CondicionesValidas == 1){
							condicionMayor = " || form.dValorI_"+iCveRama2+""+vAReg.getICveSintomaDep()+".value > "+vAReg.getIMayorA()+" ";
						}else{
							condicionMayor = "form.dValorI_"+iCveRama2+""+vAReg.getICveSintomaDep()+".value > "+vAReg.getIMayorA()+" ";
						}
						CondicionesValidas++;
					}else{
						if(vAReg.getFMayorA() >0.00001){
							System.out.println("Entro");
							if(CondicionesValidas == 1){
								condicionMayor = " || form.dValorI_"+iCveRama2+""+vAReg.getICveSintomaDep()+".value > "+vAReg.getFMayorA()+" ";
							}else{
								condicionMayor = "form.dValorI_"+iCveRama2+""+vAReg.getICveSintomaDep()+".value > "+vAReg.getFMayorA()+" ";
							}
							CondicionesValidas++;
						}
					}
					
					if(vAReg.getIMenorA() >0){
						if(CondicionesValidas >= 1){
							condicionMenor = " || form.dValorI_"+iCveRama2+""+vAReg.getICveSintomaDep()+".value < "+vAReg.getIMenorA()+" ";
						}else{
							condicionMenor = "form.dValorI_"+iCveRama2+""+vAReg.getICveSintomaDep()+".value < "+vAReg.getIMenorA()+" ";
						}
						CondicionesValidas++;
					}
					
					if(CondicionesValidas > 0){
						out.println("if( " + condicionIgual + condicionMayor + condicionMenor + " ){");
						out.println("                    div = document.getElementById('fcCaracter_"
								+ iCveRama2 + "" + vAReg.getICveSintoma() + "');");
						out.println("                    div.style.display = '';");
						out.println("                    div = document.getElementById('f2cCaracter_"
								+ iCveRama2 + "" + vAReg.getICveSintoma() + "');");
						out.println("                    div.style.display = '';");
						out.println("            }else{");
						out.println("                div = document.getElementById('fcCaracter_"
								+ iCveRama2 + "" + vAReg.getICveSintoma() + "');");
						out.println("                div.style.display='none';");
						out.println("                div = document.getElementById('f2cCaracter_"
								+ iCveRama2 + "" + vAReg.getICveSintoma() + "');");
						out.println("                div.style.display='none';");
						out.println("  }");
					}
				}
            }
       
          %>
          }
          <%
				/////////////////////GENERACION DE ALERTAS///////////////////
				TDEXPResultado dEXPResultados = new TDEXPResultado();
				TVEXPResultado vEXPResultados;
				Vector vcEXPResultados = new Vector();
				
				String filtro = "";
				String genero = "";
				
				try{
					genero = dPERDatos.SenFindBy("Select cSexo from perdatos where icveexpediente = "+request.getParameter("iCveExpediente"));
				}catch(Exception e){
					genero = "";
				}
				filtro = " where                      "
				+ "    	    r.icveproceso = 2"
				+ "		    and r.icvemdotrans = "+iCveMdoTrans+""
				+ "		    and r.icvemotivo = "+iCveMotivo+""
				+ "		    and r.iCveServicio =  "+iCveServicio+""
				+ "		    and r.iCveRama     =  "+iCveRama2+""
				+ "		    and (s.cGenero     =   '" + genero + "' OR s.cGenero='A')  ";
				try{
					vcEXPResultados = dEXPResultados.findResultadoSintoma3(filtro);
				}catch(Exception e){
					vcEXPResultados = new Vector();
				}
		%>
/*
function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

     if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
       cErrores = "";
       for(i=0; i<form.length-3; i++){
         str = form.elements[i].name;
         str2 = form.elements[i+3].name;
         if(str.substring(0,'hdTitulo_'.length)=='hdTitulo_' && str2.substring(0,'hdTitulo_'.length)!='hdTitulo_'){
           cTitulo = form.elements[i].value;
           lControlesVacios = true;
           do{
             i++;
             str = form.elements[i].name;
             if(str.substring(0,'lLogico_'.length) == 'lLogico_'){
               if(form.elements[i].checked)
                 lControlesVacios = false;
             }
             if(str.substring(0,'cCaracter_'.length) == 'cCaracter_'){
               if(form.elements[i].value != "")
                 lControlesVacios = false;
             }
             if(str.substring(0,'dValorI_'.length) == 'dValorI_'){
               if(form.elements[i].value != "")
                 lControlesVacios = false;
             }
           }while(str.substring(0,'hdTitulo_'.length)!='hdTitulo_' && i<form.length-1);
           i--;
           if(lControlesVacios)
             cErrores += " - Seleccione una opcion de: " + cTitulo + "\n";
         }
       }
       if(cErrores!=""){
         alert(cErrores);
         return false;
       }
      else if(!confirm("¬øEsta Seguro de Guardar la Informacion?"))
        return false;
    }

  return true;
}*/


		  function fValidaTodo(){
		    var form = document.forms[0];
		    if(form.hdBoton.value == 'Imprimir')
		      fImprimir();
		     
		     if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
		       cVMsg = "";
		       //fValidaTodo2();
		       //form.hdBoton.value = "activo";
		       <%///////Valida Imagen Torax
		       if(iCveServicio == 54 && iCveRama2 == 2){
		        %>
		       var torax = "";
		       
		       if (form.lLogico_21[0].checked)
		           torax = "0";
		       if (form.lLogico_21[1].checked)
		           torax = "0";
		       if (form.lLogico_21[2].checked)
		           torax = "0";
		       if (form.lLogico_21[3].checked)
		           torax = "1";
		       if (form.lLogico_21[4].checked)
		           torax = "2";
		       if (form.lLogico_21[5].checked)
		           torax = "3";
		       if (form.lLogico_21[6].checked)
		           torax = "4";
		       if(torax == "")
		           cVMsg = cVMsg + " -Seleccione una opcion de forma y volumen de Torax .\n "
		       <%
		       }//Fin valida imagen torax
		    
		       for(int i=0;i<vcEXPResultados.size();i++){
		              vEXPResultados = (TVEXPResultado) vcEXPResultados.get(i);

		              if(vEXPResultados.getLObligatorio() == 1){ // La respuesta es obligatoria y debe contener un valor.
		                  
		                  if(vEXPResultados.getICveTpoResp() == 1){
		                            out.println("var Log"+iCveRama2+""+vEXPResultados.getICveSintoma() +" = \"0\";");
		                            out.println("if (form.lLogico_"+iCveRama2+""+vEXPResultados.getICveSintoma()+"[0].checked)");
		                            out.println("Log"+iCveRama2+""+vEXPResultados.getICveSintoma() +" = \"1\";");
		                            out.println("if (form.lLogico_"+iCveRama2+""+vEXPResultados.getICveSintoma()+"[1].checked)");
		                            out.println("Log"+iCveRama2+""+vEXPResultados.getICveSintoma() +" = \"1\";");
		                            out.println("if (Log"+iCveRama2+""+vEXPResultados.getICveSintoma()+" == \"0\")");
		                            out.println("       cVMsg = cVMsg + \" -"+vEXPResultados.getCPregunta()+".\\n\" ");
		                        }
		                  
		                        if(vEXPResultados.getICveTpoResp() == 2){
		                            out.println("if (form.cCaracter_"+iCveRama2+""+vEXPResultados.getICveSintoma()+".value.length == 0)");
		                            out.println("       cVMsg = cVMsg + \" -"+vEXPResultados.getCPregunta()+".\\n\" ");
		                        }
		                        
		                        if(vEXPResultados.getICveTpoResp() == 3){
		                            out.println("if (form.dValorI_"+iCveRama2+""+vEXPResultados.getICveSintoma()+".value.length == 0)");
		                            out.println("       cVMsg = cVMsg + \" -"+vEXPResultados.getCPregunta()+".\\n\" ");
		                            
		                            out.println("    if (!/^([0-9])*$/.test(form.dValorI_"+iCveRama2+""+vEXPResultados.getICveSintoma()+".value))");
		                            out.println(" cVMsg = cVMsg + \" -El valor \" + form.dValorI_"+iCveRama2+""+vEXPResultados.getICveSintoma()+".value + \" no es un n˙mero\"");

		                        }

		                        if(vEXPResultados.getICveTpoResp() == 8 || vEXPResultados.getICveTpoResp() == 13){
		                            out.println("if (form.cCaracter_"+iCveRama2+""+vEXPResultados.getICveSintoma()+".selectedIndex==0)");
		                            out.println("       cVMsg = cVMsg + \" -"+vEXPResultados.getCPregunta()+".\\n\" ");
		                        }
		              }
		        }
		       
		       %>
		       
		       if(cVMsg!=""){
		         alert("FAVOR DE CONTESTAR LAS SIGUIENTES PREGUNTAS: \n" + cVMsg);
		         return false;
		       }
		       else if(!confirm("øEst· Seguro de Guardar la InformaciÛn?"))
		        return false;
		    }

		    if (form.hdBoton.value != 'Cancelar') {
		      cVMsg = '';
		      if (form.iCveUniMed)
		        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad mÈdica', true);
		      if (form.iCveModulo)
		        cVMsg = cVMsg + fSinValor(form.iCveModulo,3,'MÛdulo', true);
		      if (form.iCveServicio)
		        cVMsg = cVMsg + fSinValor(form.iCveServicio,0,'Servicio', false);
		      if (form.iCveExpediente)
		        cVMsg = cVMsg + fSinValor(form.iCveExpediente,3,'Expediente', false);
		        if (cVMsg != ''){
		          alert("Datos no V·lidos: \n" + cVMsg);
		          return false;
		        }
		    }
		     return true;
		   }

function validaNumero(campo){
  if(isNaN(campo.value)) {
    alert("Debe escribir un numero");
    campo.focus();
    return false;
  }
  return true;
}


// Esta funcion no debe modificarse
function activa(str){
  var form = document.forms[0];
	var sAux="";
	var idele = 0;
	var frm = document.getElementById("quest");
	for (i=0;i<frm.elements.length;i++)
	{
		if(frm.elements[i].name == str){
			idele = i;
			break 
		}
	}
    var campo=document.forms[0][idele+1];
	campo.checked="true";
	alert("El uso de lentes es obligatorio para este operador.");
	form.lLogico_960[0].checked = true;
} 


  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracterÔøΩsticas generales de las pÔøΩginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>" id="quest">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="500">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     } else {
       cPosicion = request.getParameter("hdRowNum");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">

  <input type="hidden" name="iCvePerfil" value="<%=request.getParameter("iCvePerfil")%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo")%>">

  <input type="hidden" name="iCveMdoTrans" value="<%=request.getParameter("iCveMdoTrans")%>">

  <input type="hidden" name="iCvePersonal" value="<%=request.getParameter("iCvePersonal")%>">
  <input type="hidden" name="iCveExpediente" value="<%=request.getParameter("iCveExpediente")%>">
  <input type="hidden" name="iNumExamen" value="<%=request.getParameter("iNumExamen")%>">
  <input type="hidden" name="hdiCveExpediente" value="<%=request.getParameter("iCveExpediente")%>">
  <input type="hidden" name="hdiNumExamen" value="<%=request.getParameter("iNumExamen")%>">
  <input type="hidden" name="hdICveServicio" value="1">
  
<%

  if (request.getParameter("iCveProceso")!=null && request.getParameter("iCveProceso").length() >0){

%>
     <input type="hidden" name="iCveProceso" value="<%=request.getParameter("iCveProceso")%>">
<%
  }else{
%>
     <input type="hidden" name="iCveProceso" value="<%=vParametros.getPropEspecifica("EPIProceso")%>">
<%
  }
%>

  <input type="hidden" name="tpoBusqueda" value="<%=request.getParameter("tpoBusqueda")%>">
  <input type="hidden" name="iCveUsuario" value="<%=vUsuario.getICveusuario()%>">
<% int ramaInicial = Integer.parseInt(request.getParameter("ramaInicial"));
   ramaInicial++;
%>
  <input type="hidden" name="ramaInicial" value="<%=ramaInicial%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr><td class="ETablaT" colspan="6">Datos del Personal</td></tr>
<% // inicializador de datos
   TVPERDatos personal = clsConfig.getPersonal();
   long edad = clsConfig.getEdadPersonal();
   HashMap hm = vUsuario.getVServicios(iCveUniMed, iCveProceso, iCveModulo);

   String servicio = (String)hm.get(""+iCveServicio); // descripciÔøΩn del servicio

   TVEXPRama voRama = clsConfig.getCurrentRama();
   //TVEXPRama voRama = null;
   TDMEDServicio dMEDServicio = new TDMEDServicio();
   TVMEDServicio vMEDServicio = new TVMEDServicio();
   Vector vcMEDServicio = new Vector();
   vcMEDServicio = dMEDServicio.FindByAll(" where iCveServicio = " + request.getParameter("iCveServicio"));
   for (int x = 0; x < vcMEDServicio.size(); x++){
      vMEDServicio = (TVMEDServicio) vcMEDServicio.get(x);
   }
String rama = "";
if (voRama!=null) {
   rama = voRama.getCDscRama();
} else {
//  pageContext.forward("pg070104031.jsp");
}
%>
  <input type="hidden" name="iCveRama" value="<%=(voRama==null?"":voRama.getICveRama()+"")%>">
  <input type="hidden" name="iCveServicio" value="<%=iCveServicio%>">
<!-- Personal -->
                            <tr>
                              <td class="EEtiqueta" colspan="3">Proceso:</td>
                              <td class="ETabla" colspan="3"><%=clsConfig.getProceso(""+iCveProceso)%></td>
                            </tr>

                            <tr>
                              <td class="EEtiqueta" colspan="1">Nombre:</td>
                              <td class="ETabla" colspan="2"><%=personal.getCApPaterno()+" "+personal.getCApMaterno()+" "+personal.getCNombre()%>
                              </td>
                              <td class="EEtiqueta" colspan="2">Expediente:</td>
                              <td class="ETabla" colspan="1"><%=personal.getICveExpediente()%>
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Edad:</td>
                              <td class="ETabla" colspan="2"><%=edad%>&nbsp;a&ntilde;os
                              </td>
                              <td class="EEtiqueta" colspan="2">Sexo:</td>
                              <td class="ETabla" colspan="1"><%=personal.getCSexo().equalsIgnoreCase("M")?"Masculino":"Femenino"%>
                              </td>
                            </tr>
                            <%
                             
                             Vector vcHInicio = new Vector();

                              vcHInicio = dEXPExamAplica.FindByAll(" where iCveExpediente = " + request.getParameter("iCveExpediente") + " and iNumExamen = " + request.getParameter("iNumExamen"));
                              if(vcHInicio.size() > 0){
                                  vEXPExamAplica = (TVEXPExamAplica) vcHInicio.get(0);
                               }

                             %>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Hora de Inicio:</td>
                              <td class="ETabla" colspan="2"><%= vEXPExamAplica.getTInicio().toString()%></td>
<!-- Se a√±adio el campo de la hora Unidad medica y modulo   (LAS 8 de Enero 2008)-->
                              <td class="EEtiqueta" colspan="1">Fecha:</td>
                              <td class="ETabla" colspan="2"><%= vEXPExamAplica.getDtSolicitado().toString()%></td>
                            </tr>
<%   if(iCveProceso != 2){       %>                           
                            <tr>
                                <td class="EEtiqueta" colspan="1">Unidad M&eacute;dica:</td>
                                <td class="ETabla" colspan="2"><%= vEXPExamAplica.getCDscUniMed().toString()%>
                                </td>
                                
                                     <%
                                     TDGRLModulo dGRLModulo = new TDGRLModulo();
                                     TVGRLModulo vGRLModulo = new TVGRLModulo();
                                     Vector vcGRLModulo = new Vector();

                                      vcGRLModulo = dGRLModulo.FindByAll(" where iCveModulo = " + vEXPExamAplica.getICveModulo() + " and iCveUniMed = " + vEXPExamAplica.getICveUniMed()+ "" );
                                      if(vcGRLModulo.size() > 0){
                                          vGRLModulo = (TVGRLModulo) vcGRLModulo.get(0);
                                       }

                                     %>
                                 <td class="EEtiqueta" colspan="1">Modulo:</td>
                                 <td class="ETabla" colspan="2"><%=vGRLModulo.getCDscModulo().toString()%>                               
                              </td>
                            </tr>
<%     }     %>                            
                            
<!-- Servicio -->
                            <tr><td class="ETablaT" colspan="6">Servicio</td></tr>
                                                  
                            <tr>
                              <td class="EEtiqueta" colspan="1">Servicio:</td>
                              <td class="ETabla" colspan="2"><%=vMEDServicio.getCDscServicio()%>
                              </td>
                                  <td class="EEtiqueta" colspan="1">Rama:</td>
                                  <td class="ETabla" colspan="2"><%=rama%> 
                            </tr>


                            <tr><td class="ETablaT" colspan="6">Preguntas / S&iacute;ntomas / Resultados</td></tr>
                            <%

                               if (bs != null){
                            	   bs.start();
                                   int c = 0;
                                   int d = 0;
                                   int Dependiente = 0;
                                   String cAux  = "",
                                          cFlag = "";;
                                   while(bs.nextRow()){

                            String iCveTpoResp = bs.getFieldValue("iCveTpoResp").toString();
                            String iCveSintoma = bs.getFieldValue("iCveSintoma").toString();
                            cFlag = bs.getFieldValue("iCveRama")+""+ bs.getFieldValue("iCveSintoma");
                            boolean esTexto = false;
                            boolean esCheck = false;
                            boolean esNumero = false;
                            boolean esRango = false;
                            int tpoResp = iCveTpoResp==null||iCveTpoResp.equals("null")?0:Integer.parseInt(iCveTpoResp);


                            /*out.print("<tr>");
                            String titu ="";
                            if(tpoResp != 6){
                                titu = bs.getFieldValue("cPregunta","&nbsp;").toString();
                              out.print("<td class='EEtiqueta' colspan='2' width='30%'>" + titu + "</td>");
                              }*/
                            out.print("<tr>");
                              String titu ="";
                            if(tpoResp != 6){
                            	 titu = bs.getFieldValue("cPregunta","&nbsp;").toString();
                               TDMEDREGSIN dMEDREGSIN = new TDMEDREGSIN();
                               String SCWhere= "";
                               SCWhere = "               ICVESERVICIO = "+iCveServicio+" AND	"
                                       + "               ICVERAMA = "+iCveRama2+" AND		"
                                       + "               ICVEMDOTRANS =  "+iCveMdoTrans+" AND		"
                                       + "               ICVECATEGORIA =  "+iCvecategoria+" AND"
                                       + "	         ICVESINTOMA =  "+iCveSintoma+"";
                                   try{
                                       Dependiente = dMEDREGSIN.FindByAllDep(SCWhere);
                                   }catch(Exception ex){
                                       Dependiente = 0;
                                   }
                                   
                                   
                                   if(Dependiente == 0){
                                       if(!iCveTpoResp.equals("11"))
                                       out.print("<td class='EEtiqueta' colspan='2' width='30%'>" + bs.getFieldValue("cPregunta","&nbsp;").toString() + "</td>");
                                   }else{
                                	   if(cFlag.equals("997") && UsoLentes >= 1){
                                		   System.out.println("No se imprime pregunta Nota de Lentes");
                                	   }else{
                                		   out.print("<td class='EEtiqueta' colspan='2' width='30%'>");
                                		   out.print("<div id=\"fcCaracter_"+cFlag+"\" style=\"display:none;\">");
                                		   out.print( bs.getFieldValue("cPregunta","&nbsp;").toString());
                                		   out.print("</div>");
                                		   out.print("</td>");
                                	    }
                                   }
                            }
// Respuestas del tipo correspondiente

%>
    <input type="hidden" name="iCveTpoResp_<%=cFlag%>" value="<%=iCveTpoResp%>">
    <input type="hidden" name="iCveSintoma_<%=cFlag%>" value="<%=iCveSintoma%>">
<%

String cVal = "";
TDMEDRespSint dMEDRespSint = new TDMEDRespSint();
TVMEDRespSint vMEDRespSint = new TVMEDRespSint();
switch (tpoResp) {
  case 1: // si/no
		    esCheck = true;
		//    out.print(vEti.ToggleMov("ETablaL","lLogico_"+cFlag, "0","",0,true,"1","0", true));
		    out.println("<td class='ECampo'>");
		
		       /*Se agrega codigo para marcar como valor de default el "No" al inicio de la aplicaciÔøΩn para ciertos rubros
		    Cambio 05/03/08 autor Ivan Santiago MÔøΩndez*/
		    //LINEA ANTERIOR AL CAMBIO out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "1", "Si", "1", "", "", "", 0, true, true));
		    //LINEA ANTERIOR AL CAMBIO out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "0", "No", "", "", "", "", 0, true, true));
		    /*********** COMIENZA  CODIGO CAMBIO******/
		    if(titu.equals("SOPLOS ")||titu.equals("SINTOMAS ACTUALES")||titu.equals("DIABETES MELLITUS ")||titu.equals("HIPERTENSI”ìN ARTERIAL SIST…âMICA")||titu.equals("TRATAMIENTO FARMACOL”ìGICO")||titu.equals("USA LENTES")){
		    out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "1", "Si", "", "", "", "", 0, true, true));
		    out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "0", "No", "0", "", "", "", 0, true, true));
		    } 
		    else{
		    if(titu.equals("MARCHA") || titu.equals("LENGUAJE") || titu.equals("FOTOMOTOR") || titu.equals("ORIENTACI”ìN") || 
		    		titu.equals("HORAS LABORADAS EN LOS ⁄öLTIMOS 7 DÕçAS") || titu.equals("ROMBERG") || titu.equals("ALCOHOL EN ALIENTO PRELIMINAR") || 
		    		titu.equals("ALCOHOL EN ALIENTO CONFIRMATORIA") || titu.equals("ALCOHOL EN ALIENTO") || titu.equals("ESTADO DE ALERTA")){
		    	
		    	 if(Dependiente > 0){
                     out.println("<div id=\"f2cCaracter_"+cFlag+"\" style=\"display:none;\">");
                }
		        if(titu.equals("MARCHA") || titu.equals("LENGUAJE") || titu.equals("FOTOMOTOR")){
		        out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "1", "Normal", "1", "", "", "", 0, true, true));
		        out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "0", "Anormal", "", "", "", "", 0, true, true));  
		        }
		        if(titu.equals("ORIENTACI”N")){
		        out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "1", "Orientado", "1", "", "", "", 0, true, true)+"<br>");
		        out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "0", "Desorientado", "", "", "", "", 0, true, true));  
		        }
		        if(titu.equals("HORAS LABORADAS EN LOS ⁄öLTIMOS 7 DÕçAS")){
		        out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "1", "MENOS DE 70 HORAS", "1", "", "", "", 0, true, true)+"<br>");
		        out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "0", "M¡S DE 70 HORAS", "", "", "", "", 0, true, true));  
		        }    
		        if(titu.equals("ROMBERG") || titu.equals("ALCOHOL EN ALIENTO") || titu.equals("ALCOHOL EN ALIENTO CONFIRMATORIA") || titu.equals("ALCOHOL EN ALIENTO PRELIMINAR")){ 
		        out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "1", "Positivo", "", "", "", "", 0, true, true));
		        out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "0", "Negativo", "0", "", "", "", 0, true, true));
		        }
		        if(titu.equals("ESTADO DE ALERTA")){
		        out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "1", "Alerta", "1", "", "", "", 0, true, true));
		        out.println(vEti.ObjRadioSE("ETablaT", "lLogico_"+cFlag, "0", "Somnoliento", "", "", "", "", 0, true, true));  
		        } 
		        if(Dependiente > 0){
                    out.print("<td class='ECampo' colspan='3'> <div id=\"f2cCaracter_"+cFlag+"\" style=\"display:none;\">&nbsp;</div> </td>");
               }else{
                       out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");                                    
               }
              break;
		      
		    }
		    else{
		    	esCheck = true;
                //out.println("<td class='ECampo' colspan='3'>");
                                
                if(Dependiente > 0){
                     out.println("<div id=\"f2cCaracter_"+cFlag+"\" style=\"display:none;\">");
                }
                
                if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                    String cCheck1 = "";
                    String cCheck2 = "";
                    if(request.getParameter("lLogico_"+cFlag) != null && request.getParameter("lLogico_"+cFlag).equals("1")){
                      cCheck1 = "";
                      cCheck2 = "";
                    }

                    out.println("(+)<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"1\" "+cCheck1+" >  ");
                    out.println("(-)<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"0\" "+cCheck2+" >  ");
                 }
                 else{
                	 String cCheck1 = "";
                     String cCheck2 = "";
                     String valida = "";
                	 if(cFlag.equals("960") && UsoLentes > 0){
                		 cCheck1 ="checked ";
                		 valida = " onClick=activa('iLogico_960') ";
                	 }
                    
                    if(request.getParameter("lLogico_"+cFlag) != null && request.getParameter("lLogico_"+cFlag).equals("1")){
                      cCheck1 = "";
                      cCheck2 = "";
                    }
                    //if(cFlag.equals("988") && UsoLentes > 0){
                    	out.println("Si<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"1\" "+cCheck1+" >");
                    	out.println("No<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"0\" "+cCheck2+""+valida+" >");
                    //}
                 }
                if(Dependiente > 0){
                     out.println("</div>");
                }
                 out.println("</td>");
                 
                  if(Dependiente > 0){
                     out.print("<td class='ECampo' colspan='3'> <div id=\"f2cCaracter_"+cFlag+"\" style=\"display:none;\">&nbsp;</div> </td>");
                  }else{
                	 if(cFlag.equals("987") || cFlag.equals("960")){///87 cambiado por 60
                     	if(UsoLentes > 0){
                        	out.print("<td background=\""+ vParametros.getPropEspecifica("RutaImgServer")+ "alerta.gif\" class=\"ETablaC\" colspan='3'>");
                        	out.print("Uso de Lentes Obligatorio</td>");
                        	out.print("<input type=\"hidden\" name=\"hdUsaLentes\" value=\"3\">");
                        }else{
                        	out.print("<input type=\"hidden\" name=\"hdUsaLentes\" value=\"\0\">");
                        }
                     }else{
                    	   	out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");                                    
                     }
                }
               break;   
		    }
		    }
		 
    break;
  case 2: // letras y nÔøΩmeros
			    //esTexto = true;
			    //out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
			    //out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\"></textarea></td>");
				  esTexto = true;
			      String cValor = "";
			      if(request.getParameter("cCaracter_"+cFlag) != null && request.getParameter("cCaracter_"+cFlag).trim().length() > 0){
			        cValor = request.getParameter("cCaracter_"+cFlag);
			      }
			   if(Dependiente == 0){
			                  out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
			                  out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cValor+"</textarea></td>");
			     }else{
			                  out.println("<td class=\"ECampo\" colspan='4'>");
			                  out.println("<div id=\"f2cCaracter_"+cFlag+"\" style=\"display:none;\">");
			                  out.println("<textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
			                  out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cValor+"</textarea>");
			                  out.println("</div>");
			                  out.println("</td>");
			     }
    break;
  case 3: // nÔøΩmeros
		    	esNumero = true;
		    if(titu.equals("COLORACI”ìN DE LA PIEL Y MUCOSAS") || titu.equals("ESTADO DE LA HIDRATACI”ìN") || titu.equals("TIPO DE LENTES") || titu.equals("USA LENTES") || titu.equals("INDICE PUNTA-NARIZ") || titu.equals("PUPILAS") ||
		          titu.equals("OSTEOTENDINOSOS DERECHO") || titu.equals("OSTEOTENDINOSOS IZQUIERDO")){
		        out.println("<td class='ECampo'>");
		        if(titu.equals("COLORACI”N DE LA PIEL Y MUCOSAS")){
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "1", "Normal", "1", "", "", "", 0, true, true));
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "2", "P¡lido", "", "", "", "", 0, true, true)+"<br>");  
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "3", "Rubicundo", "", "", "", "", 0, true, true));  
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "4", "CianÛtico", "", "", "", "", 0, true, true));  
		        }
		        if(titu.equals("USA LENTES")){
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "1", "Contacto", "1", "", "", "", 0, true, true));
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "2", "Aereo", "", "", "", "", 0, true, true)+"");  
		        } 
		        if(titu.equals("INDICE PUNTA-NARIZ")){
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "1", "Normal", "1", "", "", "", 0, true, true));
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "2", "Anormal", "", "", "", "", 0, true, true));  
		        } 
		        if(titu.equals("PUPILAS")){
		                //out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "1", "Normal", "1", "", "", "", 0, true, true));
		                //out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "2", "Anormal", "", "", "", "", 0, true, true)+"<br>");  
		                //out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "3", "Miosis", "", "", "", "", 0, true, true));  
		                //out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "4", "Midirasis", "", "", "", "", 0, true, true)+"<br>");  
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "5", "Simetricas", "", "", "", "", 0, true, true));  
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "6", "Asimetricas", "", "", "", "", 0, true, true));  
		        } 																			
		        if(titu.equals("OSTEOTENDINOSOS DERECHO")){
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "1", "Normal", "1", "", "", "", 0, true, true)+"<br>");
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "2", "Aumentado", "", "", "", "", 0, true, true)+"<br>");  
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "3", "Disminuido", "", "", "", "", 0, true, true));  
		        } 
		        if(titu.equals("OSTEOTENDINOSOS IZQUIERDO")){
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "1", "Normal", "1", "", "", "", 0, true, true)+"<br>");
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "2", "Aumentado", "", "", "", "", 0, true, true)+"<br>");  
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "3", "Disminuido", "", "", "", "", 0, true, true));  
		        } 
		        if(titu.equals("ESTADO DE LA HIDRATACI”ìN")){
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "1", "Adecuado", "1", "", "", "", 0, true, true)+"<br>");
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "2", "Deshidratado", "", "", "", "", 0, true, true));  
		        } 
		        if(titu.equals("TIPO DE LENTES")){
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "1", "Contacto", "1", "", "", "", 0, true, true));
		                out.println(vEti.ObjRadioSE("ETablaT", "dValorI_"+cFlag, "2", "Aereo", "", "", "", "", 0, true, true));  
		        } 
		
		        out.println("</td>");
		        out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");
		    }else{
		                //out.print("<td class='ECampo' colspan='1'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag,"",0,"","validaNumero(this);",true, true)+"</td>");
		                //out.print("<td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
				    	esNumero = true;
				    	
				    	/////Validando Diagnostico de Diabetes Mellitus////////
				    		String Etiqueta = "";
							String Mensaje = "";
							String efectoRojo = "";
							boolean DiagnosticoGlucosa = false;
							TDEXPDiagnostico dDiagnostico = new TDEXPDiagnostico();
							efectoRojo = "<td background=\""+ vParametros.getPropEspecifica("RutaImgServer")+ "alerta.gif\" class=\"ETablaC\">";
					    	if(cFlag.equals("953")){
					    		DiagnosticoGlucosa = dDiagnostico.ExistePrevioDiagnostico(request.getParameter("iCveExpediente"),"4", "53");
					    		System.out.println("iCveExpediente"+request.getParameter("iCveExpediente"));
					    		System.out.println("Bandera"+cFlag);
					    		Mensaje = "Se encontrÛ Diagnostico de DIABETES MELLITUS, NO ESPECIFICADA (CIE E14)";
					    		if(DiagnosticoGlucosa){
					    			Etiqueta =  "<td class='ECampo' colspan='2'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>"+efectoRojo + Mensaje + "</td>";
					    		}else{
					    			Etiqueta = "<td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>";
					    		}
					    	}
					    
				    	/////Validando Diagnostico de Diabetes Mellitus////////
				    
				    	if(Dependiente == 0){
			                  //out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
			                  //out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cValor+"</textarea></td>");
			                  out.print("<td class='ECampo' colspan='1'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag,"",0,"","validaNumero(this);",true, true)+"</td>");
					       	  //out.print("<td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
			                  out.print(Etiqueta);
			     		}else{
			                  out.println("<td class=\"ECampo\" colspan='4'>");
			                  out.println("<div id=\"f2cCaracter_"+cFlag+"\" style=\"display:none;\">");
			                  out.println("<input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"dValorI_"+cFlag+"\""); //
			                  out.println(" value=\"\" onfocus=\"this.select();\" onBlur=\"validaNumero(this);\" ");  
			                  out.println(" onMouseOut=\"if (window.fOutField) window.fOutField();\" onMouseOver=\"if (window.fOverField) window.fOverField(0);\">");
			                  out.println("</div>");
			                  out.println("</td>");
			     		}
				    	
				       	
		    }
    break;
  case 4: // notas
		    /*esTexto = true;
		    out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
		    out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\"></textarea></td>");*/
			  esTexto = true;
		      cVal = "";
		      if(request.getParameter("cCaracter_"+cFlag) != null && request.getParameter("cCaracter_"+cFlag).trim().length() > 0){
		        cVal = request.getParameter("cCaracter_"+cFlag);
		      }
			  
		      if(UsoLentes == 0){
			      ///////////
				  if(bs.getFieldValue("lObligatorio","&nbsp;").equals("1")){
				          out.print("<input type='hidden' name='hdTitulo_" + d + "' value='" + bs.getFieldValue("cPregunta","&nbsp;") + "'>");
				          d++;
				  }
				  ///////////
				  if(Dependiente == 0){
				              out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"4\" name=\"cCaracter_"+cFlag+"\""); //
				              out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cVal+"</textarea></td>");
				 }else{
				                  out.println("<td class=\"ECampo\" colspan='4'>");
				                  out.println("<div id=\"f2cCaracter_"+cFlag+"\" style=\"display:none;\">");
				                  out.println("<textarea cols=\"50\" rows=\"4\" name=\"cCaracter_"+cFlag+"\""); //
				                  out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cVal+"</textarea>");
				                  out.println("</div>");
				                  out.println("</td>");
				              
				  }
			  }
    break;
  case 5: // rango de nÔøΩmeros
	    /*esRango = true;
	    out.print("<td class='ECampo'>Entre</td>");
	    out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag,"",0,"","validaNumero(this);",true, true)+"</td>");
	    out.print("<td class='ECampo'>y</td>");
	    out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorF_"+cFlag,"",0,"","validaNumero(this);",true, true)+"</td>");*/
		  esRango = true;
	      String cValor1 = "";
	      String cValor2 = "";
	      if(request.getParameter("dValorI_"+cFlag) != null && request.getParameter("dValorI_"+cFlag).trim().length() > 0){
	        cValor1 = request.getParameter("dValorI_"+cFlag);
	      }
	      if(request.getParameter("dValorF_"+cFlag) != null && request.getParameter("dValorF_"+cFlag).trim().length() > 0){
	        cValor2 = request.getParameter("dValorF_"+cFlag);
	      }
		  out.print("<td class='ECampo'>Entre</td>");
		  out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag,cValor1,0,"","validaNumero(this);",true, true)+"</td>");
		  out.print("<td class='ECampo'>y</td>");
		  out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorF_"+cFlag,cValor2,0,"","validaNumero(this);",true, true)+"</td>");
    break;
  case 6: // titulo
    	out.println("<td class=\"ETablaSTC\" colspan='6'>" + bs.getFieldValue("cPregunta","&nbsp;") + "</td>");
    	out.print("<input type='hidden' name='hdTitulo_" + d + "' value='" + bs.getFieldValue("cPregunta","&nbsp;") + "'>");
    	d++;
    break;
  case 7: // notas
       esTexto = true;
           cVal = "";
       out.println("<td class=\"ECampo\" colspan='1'><textarea cols=\"50\" rows=\"4\" name=\"cCaracter_"+cFlag+"\""); //
       out.println(" onChange=\"javascript:truncar2(this)\" onBlur=\"fMayus(this);\">"+cVal+"</textarea></td>");
       out.print("<td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
  break;
  case 8: // Combos
		  esTexto = true;
	      String cVal2 = "";
	      if(request.getParameter("cCaracter_"+cFlag) != null && request.getParameter("cCaracter_"+cFlag).trim().length() > 0){
	        cVal2 = request.getParameter("cCaracter_"+cFlag);
	      }
	      
	      Vector respuestas = new Vector();
	 
	      try{
	          String condicion = " icveservicio = "+request.getParameter("iCveServicio")
	                           + " and icverama = "+bs.getFieldValue("iCveRama")
	                           + " and icvesintoma = "+iCveSintoma+" and lactivo = 1 ";
	          respuestas = dMEDRespSint.FindByAll(condicion);
	      }catch(Exception e){
	                  respuestas = new Vector();
	                  e.printStackTrace();
	      }      
			if(Dependiente == 0){
			          out.println("<td class=\"ETabla\" colspan=\"5\">");
			          out.println(vEti.SelectOneRowSinTD("cCaracter_"+cFlag+"","",respuestas,"iOrden","cDescripcion",request,"0",true));
			          out.print("</td></tr>");
			}else{
			          out.println("<td class=\"ETabla\" colspan=\"5\">");
			          out.println("<div id=\"f2cCaracter_"+cFlag+"\" style=\"display:none;\">");
			          out.println(vEti.SelectOneRowSinTD("cCaracter_"+cFlag+"","",respuestas,"iOrden","cDescripcion",request,"0",true));
			          out.println("</div>");
			          out.print("</td></tr>");                                  
			}
       break;
    
  default:
    // no vÔøΩlido
    out.print("<td class='ECampo' colspan='4'>&nbsp;</td>");
}
                                        c++;
%>
                            </tr>
                            <%
                            cAux += cFlag+"|";
                                   } // while
%>  <input type="hidden" name="hdTotalRows" value="<%=c%>">
    <input type="hidden" name="hdFlags" value="<%=cAux%>">
<%
                               } else {
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 6, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
                               }
                           %>
                          </table>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
