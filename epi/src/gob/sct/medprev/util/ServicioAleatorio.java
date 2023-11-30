package gob.sct.medprev.util;

import gob.sct.medprev.dao.TDMEDAReg;
import gob.sct.medprev.vo.TVEXPResultado;
import gob.sct.medprev.vo.TVMEDAReg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;


public class ServicioAleatorio {

	public ServicioAleatorio() {
	}

	public Vector SeccionaCuestionario(Vector Sintomas, int Servicio, int Rama) {
		Vector datos = new Vector();
		Vector dependientes = new Vector();
		Vector<TVEXPResultado> vcEXPResultado = new Vector<TVEXPResultado>();
		TVEXPResultado vEXPResultado = new TVEXPResultado();
		TVEXPResultado vEXPResultadoN2 = new TVEXPResultado();
		TVEXPResultado vEXPResultado2;
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		ArrayList<Integer> numerosAleatorio = new ArrayList<Integer>();
		ArrayList<Integer> secciones = new ArrayList<Integer>();
		ArrayList<Integer> seccionfin = new ArrayList<Integer>();
		ArrayList<Integer> preguntadependiente = new ArrayList<Integer>();
		ArrayList<Integer> dependienteagregado = new ArrayList<Integer>();
		ArrayList<Integer> sintomas = new ArrayList<Integer>();
		ArrayList<Integer> provicional = new ArrayList<Integer>(); 
		ArrayList<Integer> provicional2 = new ArrayList<Integer>(); 
		TDMEDAReg dMEDAReg = new TDMEDAReg();
		TVMEDAReg vMEDAReg = new TVMEDAReg();
		boolean sintomadependiente = false;
		int aux;
		int indexseccion = 0;
		long time_start, time_end;
		time_start = System.currentTimeMillis();
		int tmpmenor = 0;
		int tmpmayor = 0;
		int menorsintoma = 1000;
		int menortitulo = 1000;
		
		if (Sintomas.size() > 0) {

			try {
				///////////// Obtenemos preguntas dependientes  //////////////
				dependientes = dMEDAReg.FindByAll3(Servicio, Rama);

				for (int i = 0; i < dependientes.size(); i++) {
					vMEDAReg = (TVMEDAReg) dependientes.get(i);
					preguntadependiente.add(vMEDAReg.getICveSintoma());
				}	
					
				////////Obtenemos por separado las secciones y los sintomas del servicio y rama ///////////
				for (int i = 0; i < Sintomas.size(); i++) {
					vEXPResultado = (TVEXPResultado) Sintomas.get(i);
					if (vEXPResultado.getICveTpoResp() == 6) {
						//System.out.println(vEXPResultado.getCDscBreve() + " - "	+ vEXPResultado.getIOrden());
						numeros.add(vEXPResultado.getIOrden());
						numerosAleatorio.add(vEXPResultado.getIOrden());
						if(menortitulo > vEXPResultado.getIOrden()){
							menortitulo = vEXPResultado.getIOrden();
						}
					}else{
						for(int a = 0; a < preguntadependiente.size(); a++){
							if(preguntadependiente.get(a) == vEXPResultado.getICveSintoma()){
								sintomadependiente = true;
								//System.out.println("DependienteFuera = "+vEXPResultado.getICveSintoma());
							}
						}
						if(!sintomadependiente){
							sintomas.add(vEXPResultado.getIOrden());	
							//System.out.println("Sintomas= "+vEXPResultado.getCDscBreve() + " - "	+ vEXPResultado.getIOrden());
							if(menorsintoma > vEXPResultado.getIOrden()){
								menorsintoma = vEXPResultado.getIOrden();
							}
						}
						sintomadependiente = false;						
					}
				}
				
				

				
				if(menorsintoma < menortitulo){
					numerosAleatorio.add(-1000);
					numeros.add(-1000);
				}
				
				
				
				//System.out.println("numeros.size() = "+numeros.size());
				//System.out.println("preguntadependiente.size() = "+preguntadependiente.size());
				//System.out.println("sintomas.size() = "+sintomas.size());
				///////// Ordenamos las etiquetas o secciones de menor a mayor para definir los rangos con 
				////////  los que deberan de cumplir los sintomas por seccion
				for (int a = 0; a < numeros.size() - 1; a++) {
						for (int j = a + 1; j < numeros.size(); j++) {
							if (numeros.get(a) > numeros.get(j)) {
								aux = numeros.get(a);
								numeros.set(a, numeros.get(j));
								numeros.set(j, aux);
							}
						}
					}

				
				///////// Agrupamos el listado de sintomas aleatorios y etiquetas como encabezado de secciones, estas ordenadas de menor a mayor/////////////////
				for (int a = 0; a < numeros.size() - 1; a++) {
						for (int j = a + 1; j < numeros.size(); j++) {
							for (int b = 0; b < sintomas.size(); b++) {
							if(a == j-1){
								//System.out.println("sintoma = "+sintomas.get(b));				
								//System.out.println("numeros> = "+numeros.get(a));
								//System.out.println("numeros< = "+numeros.get(j));
								if(b==0){
									secciones.add(numeros.get(indexseccion));
									indexseccion++;
								}
								if(sintomas.get(b) >= numeros.get(a) && sintomas.get(b) <  numeros.get(j)) {
									secciones.add(sintomas.get(b));
									//System.out.println("SeccionAsignado = "+sintomas.get(b));
								}else{
									if(numeros.size()-1 == j && sintomas.get(b) >  numeros.get(j)){
										if(seccionfin.size()==0){
											//System.out.println("SeccionAsignado2 inicio = "+numeros.get(indexseccion));
											seccionfin.add(numeros.get(indexseccion));
											indexseccion++;
											//System.out.println("SeccionAsignado2 sintoma1 = "+sintomas.get(b));
											seccionfin.add(sintomas.get(b));
										}else{
											seccionfin.add(sintomas.get(b));
											//System.out.println("SeccionAsignado2 sintoma2= "+sintomas.get(b));
										}
										//System.out.println("SeccionAsignado2 = "+sintomas.get(b));
									}
								}
							}
						}
					}
				}
				
			
				

				if(numeros.size() == 1){
					//System.out.println("SSSSSSSSSSSSSSSSSSSSS ");
					for (int b = 0; b < sintomas.size(); b++) {
							//System.out.println("sintoma = "+sintomas.get(b));				
							//System.out.println("numeros> = "+numeros.get(0));
							//System.out.println("numeros< = "+numeros.get(0));
							if(b==0){
								seccionfin.add(numeros.get(indexseccion));
								indexseccion++;
							}
							if(sintomas.get(b) >  numeros.get(0)){
									seccionfin.add(sintomas.get(b));
									//System.out.println("SeccionAsignado5 sintoma2= "+sintomas.get(b));
							}
							//System.out.println("SeccionAsignado6 = "+sintomas.get(b));
					}
				}
				
						
				
				
				
				
				////////////Quitar repetidos del Array si los hay ////////
				/*HashSet hs = new HashSet();
				hs.addAll(seccionfin);
				seccionfin.clear();
				seccionfin.addAll(hs);*/
				
				/////Vaciamos la lista de sintomas de la seccion final del cuestionario a la lista general /////////
				for (int i = 0; i < seccionfin.size(); i++) {
						secciones.add(seccionfin.get(i));
				}


				////// Impresion de seccioness //////////
				for (int i = 0; i < numeros.size(); i++) {
					//System.out.println("etiquetas = "+numeros.get(i)); 
				}
				
				////// Impresion de seccioness aleatorias //////////
				for (int i = 0; i < numerosAleatorio.size(); i++) {
					//System.out.println("etiquetas2 = "+numerosAleatorio.get(i)); 
				}
				
				/// Impresion de las secciones por etiquetas como encabezado, sintomas aleatorios y secciones de menor a mayor /////////
				//System.out.println("Seccion size= "+secciones.size());
				for (int i = 0; i < secciones.size(); i++) {
							//System.out.println("Seccion = "+secciones.get(i));
				} 
		
				
				///////Obtenemos el vector de secciones ordenadas de forma aleatoria /////////////		
				int pocision = 0;
				for (int i = 0; i < numerosAleatorio.size(); i++) {
					for (int j = 0; j < numeros.size(); j++) {
						//System.out.println(numerosAleatorio.get(i) +" == "+ numeros.get(j));
						//System.out.println("opciones = "+i +" - Size "+numerosAleatorio.size());
						//System.out.println("opciones = "+j +" - Size "+numeros.size());
							if(numerosAleatorio.get(i) == numeros.get(j)){
								//System.out.println("numeros = "+numeros.get(j) +"=="+ numeros.get(numeros.size()-1));
								if(numeros.get(j) == numeros.get(numeros.size()-1)){
									//System.out.println("condicion1");
									for (int k = 0; k < secciones.size(); k++) {
										//System.out.println("0-"+secciones.get(k));
										if(secciones.get(k)>= numeros.get(j)){
											provicional.add(secciones.get(k));
											provicional2.add(secciones.get(k));
											//System.out.println(secciones.get(k)+" >= "+ numeros.get(j)+"");
										}
									}
								}else{
									//System.out.println("condicion2");
									for (int l = 0; l < secciones.size(); l++) {
										//System.out.println("3-"+secciones.get(l));
											if(secciones.get(l)>= numeros.get(j) && secciones.get(l)< numeros.get(j+1)){
											provicional.add(secciones.get(l));
											provicional2.add(secciones.get(l));
											//System.out.println(secciones.get(l)+" >= "+ numeros.get(j)+" && "+secciones.get(l) +"<"+ numeros.get(j+1));
										}
									}
								}					
							}else{
								if((numerosAleatorio.get(i) - numeros.get(j)) == 0){
									//System.out.println("Entraron aqui");
									//System.out.println("numeros = "+numeros.get(j) +"=="+ numeros.get(numeros.size()-1));
									if(numeros.get(j) == numeros.get(numeros.size()-1)){
										//System.out.println("condicion1");
										for (int k = 0; k < secciones.size(); k++) {
											//System.out.println("0-"+secciones.get(k));
											if(secciones.get(k)>= numeros.get(j)){
												provicional.add(secciones.get(k));
												provicional2.add(secciones.get(k));
												//System.out.println(secciones.get(k)+" >= "+ numeros.get(j)+"");
											}
										}
									}else{
										//System.out.println("condicion2");
										for (int l = 0; l < secciones.size(); l++) {
											//System.out.println("3-"+secciones.get(l));
												if(secciones.get(l)>= numeros.get(j) && secciones.get(l)< numeros.get(j+1)){
												provicional.add(secciones.get(l));
												provicional2.add(secciones.get(l));
												//System.out.println(secciones.get(l)+" >= "+ numeros.get(j)+" && "+secciones.get(l) +"<"+ numeros.get(j+1));
											}
										}
									}	
									
								}
							}
					}
				}
				
				

				for (int a = 0; a < provicional2.size() - 1; a++) {
					for (int j = a + 1; j < provicional2.size(); j++) {
						if (provicional2.get(a) > provicional2.get(j)) {
							aux = provicional2.get(a);
							provicional2.set(a, provicional2.get(j));
							provicional2.set(j, aux);
						}
					}
				}
				
				
				///// Recorremos las secciones de sintomas aleatorios para ajustar los datos generales del examen al orden resultante /////
				//System.out.println("provicionalSize = "+provicional.size());
				boolean TituloVirtual = false;
				//System.out.println("MenorTitulo = "+menortitulo);
				//System.out.println("MenorSintoma = "+menorsintoma);
				for (int i = 0; i < provicional.size(); i++) {
					for (int j = 0; j < Sintomas.size(); j++) {
						vEXPResultado = (TVEXPResultado) Sintomas.get(j);
						if (provicional.get(i) == vEXPResultado.getIOrden()) {
							vEXPResultado2 = new TVEXPResultado();
							vEXPResultado2.setICveExpediente(vEXPResultado.getICveExpediente());
							vEXPResultado2.setINumExamen(vEXPResultado.getINumExamen());
							vEXPResultado2.setICveServicio(vEXPResultado.getICveServicio());
							vEXPResultado2.setICveRama(vEXPResultado.getICveRama());
							vEXPResultado2.setICveSintoma(vEXPResultado.getICveSintoma());
							vEXPResultado2.setDValorIni(vEXPResultado.getDValorIni());
							vEXPResultado2.setLLogico(vEXPResultado.getLLogico());
							vEXPResultado2.setCCaracter(vEXPResultado.getCCaracter());
							vEXPResultado2.setDValorFin(vEXPResultado.getDValorFin());
							vEXPResultado2.setCDscBreve(vEXPResultado.getCDscBreve());
							vEXPResultado2.setCPregunta(vEXPResultado.getCPregunta());
							vEXPResultado2.setICveTpoResp(vEXPResultado.getICveTpoResp());
							vEXPResultado2.setLObligatorio(vEXPResultado.getLObligatorio());
							vEXPResultado2.setLEvalAuto(vEXPResultado.getLEvalAuto());
							vEXPResultado2.setCEtiqueta(vEXPResultado.getCEtiqueta());
							vEXPResultado2.setCValRef(vEXPResultado.getCValRef());
							vEXPResultado2.setIOrden(vEXPResultado.getIOrden());
							
							vcEXPResultado.addElement(vEXPResultado2);

							
							/////////////Obtenemos el mayor para los rangos de dependientes
							for (int a = 0; a < provicional2.size(); a++) {
								if(provicional2.get(a) == vEXPResultado.getIOrden()){
									if((a+1) < provicional2.size()){
										tmpmayor = provicional2.get(a+1) ;
									}else{
										tmpmayor = provicional2.get(a) * 1000;
									}
								}
							}
							
							/////////////Obtenemos las dependientes que encajan en el rango
							for (int a = 0; a < preguntadependiente.size(); a++) {
								for (int b = 0; b < Sintomas.size(); b++) {
									vEXPResultadoN2 = (TVEXPResultado) Sintomas.get(b);
									
									if(preguntadependiente.get(a) == vEXPResultadoN2.getICveSintoma()){
										if(vEXPResultadoN2.getIOrden() > vEXPResultado.getIOrden() &&
										    vEXPResultadoN2.getIOrden() < tmpmayor ){
											boolean agregado = false;
											for (int c = 0; c < dependienteagregado.size(); c++) {
												if(dependienteagregado.get(c) == vEXPResultadoN2.getICveSintoma()){
													agregado = true;
												}
											}		
											
											if(!agregado){
													vEXPResultado2 = new TVEXPResultado();
													vEXPResultado2.setICveExpediente(vEXPResultadoN2.getICveExpediente());
													vEXPResultado2.setINumExamen(vEXPResultadoN2.getINumExamen());
													vEXPResultado2.setICveServicio(vEXPResultadoN2.getICveServicio());
													vEXPResultado2.setICveRama(vEXPResultadoN2.getICveRama());
													vEXPResultado2.setICveSintoma(vEXPResultadoN2.getICveSintoma());
													vEXPResultado2.setDValorIni(vEXPResultadoN2.getDValorIni());
													vEXPResultado2.setLLogico(vEXPResultadoN2.getLLogico());
													vEXPResultado2.setCCaracter(vEXPResultadoN2.getCCaracter());
													vEXPResultado2.setDValorFin(vEXPResultadoN2.getDValorFin());
													vEXPResultado2.setCDscBreve(vEXPResultadoN2.getCDscBreve());
													vEXPResultado2.setCPregunta(vEXPResultadoN2.getCPregunta());
													vEXPResultado2.setICveTpoResp(vEXPResultadoN2.getICveTpoResp());
													vEXPResultado2.setLObligatorio(vEXPResultadoN2.getLObligatorio());
													vEXPResultado2.setLEvalAuto(vEXPResultadoN2.getLEvalAuto());
													vEXPResultado2.setCEtiqueta(vEXPResultadoN2.getCEtiqueta());
													vEXPResultado2.setCValRef(vEXPResultadoN2.getCValRef());
													vEXPResultado2.setIOrden(vEXPResultadoN2.getIOrden());
													vcEXPResultado.addElement(vEXPResultado2);
													dependienteagregado.add(vEXPResultadoN2.getICveSintoma());
												}
										}
									}
									
									
								}
							}
							
						}else{
							if(provicional.get(i) == -1000 && (!TituloVirtual)){
								vEXPResultado2 = new TVEXPResultado();
								vEXPResultado2.setICveExpediente(vEXPResultado.getICveExpediente());
								vEXPResultado2.setINumExamen(vEXPResultado.getINumExamen());
								vEXPResultado2.setICveServicio(vEXPResultado.getICveServicio());
								vEXPResultado2.setICveRama(vEXPResultado.getICveRama());
								vEXPResultado2.setICveSintoma(-100);
								vEXPResultado2.setDValorIni(0);
								vEXPResultado2.setLLogico(0);
								vEXPResultado2.setCCaracter("");
								vEXPResultado2.setDValorFin(0);
								vEXPResultado2.setCDscBreve("");
								vEXPResultado2.setCPregunta("");
								vEXPResultado2.setICveTpoResp(6);
								vEXPResultado2.setLObligatorio(0);
								vEXPResultado2.setLEvalAuto(0);
								vEXPResultado2.setCEtiqueta("");
								vEXPResultado2.setCValRef("");
								vEXPResultado2.setIOrden(-100);
								vcEXPResultado.addElement(vEXPResultado2);
								TituloVirtual = true;
							}
						}
					}
				}
		}catch(Exception ex) {
			System.out.println("ServicioAleatorio"+ ex);
		}
			
		}
		return vcEXPResultado;
	}
	
	public Vector SinSeccionReordenaDependiente(Vector Sintomas, int Servicio, int Rama) {
		Vector datos = new Vector();
		Vector dependientes = new Vector();
		Vector<TVEXPResultado> vcEXPResultado = new Vector<TVEXPResultado>();
		TVEXPResultado vEXPResultado = new TVEXPResultado();
		TVEXPResultado vEXPResultado2;
		ArrayList<Integer> preguntadependiente = new ArrayList<Integer>(); 
		ArrayList<Integer> sintomas = new ArrayList<Integer>();
		ArrayList<Integer> provicional = new ArrayList<Integer>(); 
		TDMEDAReg dMEDAReg = new TDMEDAReg();
		TVMEDAReg vMEDAReg = new TVMEDAReg();
		boolean sintomadependiente = false;
		long time_end;
		if (Sintomas.size() > 0) {

			try {
				///////////// Obtenemos preguntas dependientes  //////////////
				dependientes = dMEDAReg.FindByAll3(Servicio, Rama);

				for (int i = 0; i < dependientes.size(); i++) {
					vMEDAReg = (TVMEDAReg) dependientes.get(i);
					preguntadependiente.add(vMEDAReg.getICveSintoma());
				}	
					
				////////Obtenemos por separado las secciones y los sintomas del servicio y rama ///////////
				for (int i = 0; i < Sintomas.size(); i++) {
					vEXPResultado = (TVEXPResultado) Sintomas.get(i);
						for(int a = 0; a < preguntadependiente.size(); a++){
							if(preguntadependiente.get(a) == vEXPResultado.getICveSintoma()){
								sintomadependiente = true;
								//System.out.println("DependienteFuera = "+vEXPResultado.getICveSintoma());
							}
						}
						if(!sintomadependiente){
							sintomas.add(vEXPResultado.getIOrden());	
							//System.out.println("Sintomas= "+vEXPResultado.getCDscBreve() + " - "	+ vEXPResultado.getIOrden());
						}
						sintomadependiente = false;						
					
				}
				
				
				provicional = sintomas;
				///// Recorremos las secciones de sintomas aleatorios para ajustar los datos generales del examen al orden resultante /////
				//System.out.println("provicionalSize = "+provicional.size());
				for (int i = 0; i < provicional.size(); i++) {
					for (int j = 0; j < Sintomas.size(); j++) {
						vEXPResultado = (TVEXPResultado) Sintomas.get(j);
						if (provicional.get(i) == vEXPResultado.getIOrden()) {
							vEXPResultado2 = new TVEXPResultado();
							vEXPResultado2.setICveExpediente(vEXPResultado.getICveExpediente());
							vEXPResultado2.setINumExamen(vEXPResultado.getINumExamen());
							vEXPResultado2.setICveServicio(vEXPResultado.getICveServicio());
							vEXPResultado2.setICveRama(vEXPResultado.getICveRama());
							vEXPResultado2.setICveSintoma(vEXPResultado.getICveSintoma());
							vEXPResultado2.setDValorIni(vEXPResultado.getDValorIni());
							vEXPResultado2.setLLogico(vEXPResultado.getLLogico());
							vEXPResultado2.setCCaracter(vEXPResultado.getCCaracter());
							vEXPResultado2.setDValorFin(vEXPResultado.getDValorFin());
							vEXPResultado2.setCDscBreve(vEXPResultado.getCDscBreve());
							vEXPResultado2.setCPregunta(vEXPResultado.getCPregunta());
							vEXPResultado2.setICveTpoResp(vEXPResultado.getICveTpoResp());
							vEXPResultado2.setLObligatorio(vEXPResultado.getLObligatorio());
							vEXPResultado2.setLEvalAuto(vEXPResultado.getLEvalAuto());
							vEXPResultado2.setCEtiqueta(vEXPResultado.getCEtiqueta());
							vEXPResultado2.setCValRef(vEXPResultado.getCValRef());
							vEXPResultado2.setIOrden(vEXPResultado.getIOrden());
							
							vcEXPResultado.addElement(vEXPResultado2);
							
							for (int k = 0; k < dependientes.size(); k++) {
								vMEDAReg = (TVMEDAReg) dependientes.get(k);
								if(vMEDAReg.getICveSintomaDep() == vEXPResultado.getICveSintoma()){
									for (int l = 0; l < Sintomas.size(); l++) {
										vEXPResultado = (TVEXPResultado) Sintomas.get(l);
										if (vMEDAReg.getICveSintoma()  == vEXPResultado.getICveSintoma()) {
											//System.out.println("Dep = "+vEXPResultado.getCDscBreve());
												vEXPResultado2 = new TVEXPResultado();
												vEXPResultado2.setICveExpediente(vEXPResultado.getICveExpediente());
												vEXPResultado2.setINumExamen(vEXPResultado.getINumExamen());
												vEXPResultado2.setICveServicio(vEXPResultado.getICveServicio());
												vEXPResultado2.setICveRama(vEXPResultado.getICveRama());
												vEXPResultado2.setICveSintoma(vEXPResultado.getICveSintoma());
												vEXPResultado2.setDValorIni(vEXPResultado.getDValorIni());
												vEXPResultado2.setLLogico(vEXPResultado.getLLogico());
												vEXPResultado2.setCCaracter(vEXPResultado.getCCaracter());
												vEXPResultado2.setDValorFin(vEXPResultado.getDValorFin());
												vEXPResultado2.setCDscBreve(vEXPResultado.getCDscBreve());
												vEXPResultado2.setCPregunta(vEXPResultado.getCPregunta());
												vEXPResultado2.setICveTpoResp(vEXPResultado.getICveTpoResp());
												vEXPResultado2.setLObligatorio(vEXPResultado.getLObligatorio());
												vEXPResultado2.setLEvalAuto(vEXPResultado.getLEvalAuto());
												vEXPResultado2.setCEtiqueta(vEXPResultado.getCEtiqueta());
												vEXPResultado2.setCValRef(vEXPResultado.getCValRef());
												vEXPResultado2.setIOrden(vEXPResultado.getIOrden());
												vcEXPResultado.addElement(vEXPResultado2);
											}
									}
								}
							}	
							
						}
					}
				}
		}catch(Exception ex) {
			System.out.println("ServicioAleatorio"+ ex);
		}
			
		}
		return vcEXPResultado;
	}
	
	
	
}