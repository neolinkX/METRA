package gom.sct.medprev.reglasexp;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.seguridad.dao.CFGListBase2;

import gob.sct.medprev.dao.TDEXPExamAplica;
import gob.sct.medprev.dao.TDEXPExamCat;
import gob.sct.medprev.dao.TDEXPRegSint;
import gob.sct.medprev.dao.TDMEDAReg;
import gob.sct.medprev.dao.TDMEDREGSIN;

public class AlertaSintoma extends CFGListBase2 {

	public AlertaSintoma() {

	}

	public String AlertaXSintoma(int Sintoma, int tpoResp, String cValor, int iValor, String cExpediente,
			String cExamen, int iServicioActual, int iRama) throws NumberFormatException, DAOException {
		vParametros = new TParametro("07");
		
		
		System.out.println(Sintoma);
		System.out.println(tpoResp);
		System.out.println(cValor);
		System.out.println(iValor);
		System.out.println(cExpediente);
		System.out.println(cExamen);
		System.out.println(iServicioActual);
		System.out.println(iRama);
		
		
		
		//// Obtener numero de categorias y modos de transporte
		TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
		int NumTramites = 0;
		try {
			String Query = " SELECT COUNT(ICVEEXPEDIENTE) FROM EXPEXAMCAT WHERE ICVEEXPEDIENTE = " + cExpediente
					+ " AND INUMEXAMEN = " + cExamen + "";
			NumTramites = dEXPExamCat.FindByInt(Query);
		} catch (Exception e) {
			NumTramites = 0;
			e.printStackTrace();
		}

		int Atendido = 0;
		try {
			String Query = " SELECT COUNT(ICVEEXPEDIENTE) FROM EXPREGSIN WHERE ICVEEXPEDIENTE = " + cExpediente
					+ " AND INUMEXAMEN = " + cExamen + " AND ICVESERVICIO = " + iServicioActual;
			Atendido = dEXPExamCat.FindByInt(Query);
		} catch (Exception e) {
			Atendido = 0;
			e.printStackTrace();
		}
		int iCveMdoTrans = this.getMDOTrans(cExpediente, cExamen);
		int iCvecategoria = this.getCategoria(cExpediente, cExamen);

		//// Obteniendo Alertas////
		TDMEDREGSIN dMEDREGSIN = new TDMEDREGSIN();
		TDMEDAReg dMEDAReg = new TDMEDAReg();
		String cInterCon = "";
		int Hipertension = 0;
		int Glucemia = 0;
		int Puntaje = 0;
		int PuntajeRep = 0;
		int PuntajeIAM = 0;
		int PuntajeSaos = 0;
		int Obesidad = 0;
		int FreCar = 0;
		int FSistolica = 0;
		int FDiastolica = 0;
		int Reposo = 0;
		int Glucemia2 = 0;

		///////////// Prueba de detección de alcoholismo de
		///////////// Michigan(MAST)////////////////
		int IMichigan = 0;
		/////////////////////////////////// TABAQUISMO
		/////////////////////////////////// ///////////////////////////////////////////////
		int Itabaquismo = 0;
		/////////////////////////////////// DROGAS
		/////////////////////////////////// ///////////////////////////////////////////////
		int idrogas = 0;
		/////////////////////////////////// PSICOLOGIA
		/////////////////////////////////// ///////////////////////////////////////////////
		int Psicologia1 = 0;
		int Psicologia2 = 0;
		int Psicologia3 = 0;
		int Psicologia4 = 0;

		int ResulTorax = 0;

		String AlertaR = "";
		String sentencia = "";
		String sentencia2 = "";
		String OperadorS = "";
		int operador = 0;
		String resultado = "";
		if( tpoResp == 3){
			resultado = iValor+"";
		}else{
			resultado = cValor;
		}
		
		
		
		
System.out.println("Alerta 1");
		if (tpoResp == 1 || tpoResp == 3 || tpoResp == 5 || tpoResp == 8) {

			if (tpoResp == 8 && cValor.equals("-1")) {
				resultado = "0";
			}

			if (cValor.equals("Sí")) {
				resultado = "1";
			}
			if (cValor.equals("Si")) {
				resultado = "1";
			}
			if (cValor.equals("SI")) {
				resultado = "1";
			}
			if (cValor.equals("No")) {
				resultado = "0";
			}
			if (cValor.equals("NO")) {
				resultado = "0";
			}
			
			System.out.println("Alerta 2");
			
			try {

				if (tpoResp == 1) {
					sentencia2 = "( M.LOGICA = " + resultado + ") ";
					sentencia = sentencia2 + " AND " + " C.ICVEEXPEDIENTE = " + cExpediente + " AND "
							+ " C.INUMEXAMEN = " + cExamen + " AND " + " M.ICVESERVICIO = " + iServicioActual + " AND "
							+ " M.ICVERAMA = " + iRama + " AND " + " M.ICVESINTOMA = " + Sintoma + " ";
					AlertaR = dMEDREGSIN.FindByAlReg(sentencia, NumTramites, Atendido);
					System.out.println("Alerta ="+AlertaR);
				} else {

					OperadorS = "   R.ICVESERVICIO = " + iServicioActual + " AND " + " R.ICVERAMA = " + iRama + " AND "
							+ " R.ICVESINTOMA = " + Sintoma + " AND " + " R.RDACCION = 1 ";
					operador = dMEDREGSIN.FindOp(OperadorS);
					System.out.println("operador ="+operador);
					System.out.println("Alerta 3");
					if (operador > 0) {
						if (operador == 1) {
							sentencia2 = "( " + resultado + " > M.IMAYORA) ";
						}
						if (operador == 10) {
							sentencia2 = "( M.IIGUALA = " + resultado + ") ";
						}
						if (operador == 11) {
							sentencia2 = "( " + resultado + " > M.IMAYORA OR M.IIGUALA = " + resultado + ") ";
						}
						if (operador == 100) {
							sentencia2 = "( " + resultado + " < M.IMENORA) ";
						}
						System.out.println("Alerta 4");
						if (operador == 101) {
							sentencia2 = "( " + resultado + " > M.IMAYORA OR " + resultado + " < M.IMENORA) ";
						}
						if (operador == 110) {
							sentencia2 = "( " + resultado + " < M.IMENORA OR M.IIGUALA = " + resultado + ") ";
						}
						if (operador == 111) {
							sentencia2 = "( " + resultado + " < M.IMENORA OR " + resultado
									+ " > M.IMAYORA OR M.IIGUALA = " + resultado + ") ";
						}

						sentencia = sentencia2 + " AND " + " C.ICVEEXPEDIENTE = " + cExpediente + " AND "
								+ " C.INUMEXAMEN = " + cExamen + " AND " + " M.ICVESERVICIO = " + iServicioActual
								+ " AND " + " M.ICVERAMA = " + iRama + " AND " + " M.ICVESINTOMA = " + Sintoma + " ";
						if(!sentencia2.equals(""))
                    		AlertaR = dMEDREGSIN.FindByAlReg(sentencia,NumTramites,Atendido);
					}
				}
				
				System.out.println("Alerta 5");
			} catch (Exception e) {
				System.out.println("AlertaXSintoma "+e);
				AlertaR = "";
				e.printStackTrace();
			}
		}
		
		System.out.println(Sintoma + " = " + AlertaR);
		/////////////////////////// SI EXISTE ALERTA VERIFICAR
		/////////////////////////// INTERCONSULTA/////////////////////////////////////////////////
		if (!AlertaR.equals("")) {
			System.out.println("Alerta 6");
			String ServInter = "";
			try {
				ServInter = dMEDAReg.SenFindBy("SELECT A.SERINTERCON " + "FROM MEDREGSIN AS S, MEDAREG AS A " + "WHERE "
						+ "S.ICVESERVICIO = A.ICVESERVICIO  AND " + "S.ICVERAMA = A.ICVERAMA AND "
						+ "S.ICVESINTOMA = A.ICVESINTOMA AND " + "S.ICVEREGLA = A.ICVEREGLA AND "
						+ "S.RDACCION = 1  AND " + "LACTIVO = 1 AND " + "(S.IMAYORA > " + resultado + " OR "
						+ "S.IIGUALA = " + resultado + " OR " + "S.IMENORA < " + resultado + "  ) AND "
						+ "S.ICVEMDOTRANS = " + iCveMdoTrans + " AND  " + "S.ICVECATEGORIA = " + iCvecategoria + " AND "
						+ "S.ICVESERVICIO = " + iServicioActual + " AND " + "S.ICVERAMA = " + iRama + " AND "
						+ "S.ICVESINTOMA = " + Sintoma);
				if (!ServInter.equals("")) {
					if (!cInterCon.equals("")) {
						cInterCon = cInterCon + "," + ServInter;
					} else {
						cInterCon = ServInter;
					}
				}
			} catch (Exception e) {
				ServInter = "";
			}
		}

		///////////////////// R I E S G O C O R O N A R I O
		///////////////////// //////////////////////////
		if (iServicioActual == 65) {
			if (iRama == 1) {
				float Decimal = 0.0F;
				try {
					if (Sintoma >= 28) {
						Decimal = Float.parseFloat(resultado.trim());
					}
				} catch (NumberFormatException ex) {
					Decimal = 0.0F;
				}
				/// EDAD / ANT. FAM. IAM. EDAD / ANT. PERSONAL IAM.
				/// /////////////
				if (Sintoma >= 28 && Sintoma <= 34) {
					Puntaje = Puntaje + this.RiesgoCor(Sintoma, (int) Decimal, resultado);
				}

				/// TABAQUISMO / TENSION ARTERIAL / OBESIDAD / FREC. CARDIACA
				/// /////////////
				if (Sintoma >= 35 && Sintoma <= 38) {
					AlertaR = this.RiesgoCor(Sintoma, (int) Decimal, 0);
					Puntaje = Puntaje + this.RiesgoCor(Sintoma, (int) Decimal, resultado);
					if (Sintoma == 34) {/// ASIGNANDO PUNTAJE ANT. PERSONAL IAM.
						PuntajeIAM = this.RiesgoCor(Sintoma, (int) Decimal, resultado);
					}
					if (Sintoma == 37) {/// ASIGNANDO VALOR OBESIDAD.
						Obesidad = (int) Decimal;
					}
					if (Sintoma == 38) {/// ASIGNANDO VALOR FRECUENCIA CARDIACA.
						FreCar = (int) Decimal;
					}
				}
				///// HIPERTENCION / SISTOLICA / DISTOLICA ////////
				if (Sintoma == 39 || Sintoma == 40 || Sintoma == 41) {
					Hipertension = Hipertension + (int) Decimal;
					if (Sintoma == 41) {
						AlertaR = this.RiesgoCor(Sintoma, Hipertension, 0);
					}
					Puntaje = Puntaje + this.RiesgoCor(Sintoma, (int) Decimal, resultado);
					if (Sintoma == 40) {/// ASIGNANDO VALOR SISTOLICA
						FSistolica = (int) Decimal;
					}
					if (Sintoma == 41) {/// ASIGNANDO VALOR DIASTOLICA
						FDiastolica = (int) Decimal;
					}
				}
				///// REPOSO / COLESTEROL /////
				if (Sintoma == 42 || Sintoma == 45) {
					AlertaR = this.RiesgoCor(Sintoma, (int) Decimal, 0);
					Puntaje = Puntaje + this.RiesgoCor(Sintoma, (int) Decimal, resultado);
					if (Sintoma == 42) {//// ASIGNANDO PUNTAJE REPOSO
						PuntajeRep = this.RiesgoCor(Sintoma, (int) Decimal, resultado);
						Reposo = (int) Decimal;
					}

				}
				///// DIABETES / GLUCEMIA /////
				if (Sintoma >= 43 && Sintoma <= 44) {
					Glucemia = Glucemia + (int) Decimal;
					if (Sintoma == 44) {
						AlertaR = this.RiesgoCor(Sintoma, (int) Decimal, Glucemia);
						Glucemia2 = (int) Decimal;
					}
					Puntaje = Puntaje + this.RiesgoCor(Sintoma, (int) Decimal, resultado);

				}
				/// TRIGLICERIDOS / ACIDO URICO /////////////
				if (Sintoma >= 46 && Sintoma <= 47) {
					Puntaje = Puntaje + this.RiesgoCor(Sintoma, (int) Decimal, resultado);
				}
				///// DIAGNOSTICOS RIESGO CORONARIO /////
				if (Sintoma >= 48 || Sintoma <= 51) {
					AlertaR = this.RiesgoCor(Sintoma, Puntaje, 0);
					if (Sintoma == 49) {//// REQUIERE PRUEBA ESFUERZO?
						if (PuntajeRep == 3) {
							AlertaR = "SI";
						}
						if (PuntajeRep < 3 && Puntaje <= 17) {
							AlertaR = "NO";
						}
						if (Puntaje >= 18) {
							AlertaR = "SI";
						}
					}
					if (Sintoma == 50) {//// RAZONES PARA NAP?
						if (PuntajeIAM == 0) {
							AlertaR = "NO";
						}
						if (PuntajeIAM >= 1) {
							AlertaR = "SI";
						}
					}
					if (Sintoma == 51) {//// OTRAS RAZONES PARA NAT?
						if (Obesidad == 4) {
							AlertaR = "SI";
						} else {
							if (FreCar >= 105) {
								AlertaR = "SI";
							} else {
								if (FSistolica >= 160) {
									AlertaR = "SI";
								} else {
									if (FDiastolica >= 100) {
										AlertaR = "SI";
									} else {
										if (Reposo == 3) {
											AlertaR = "SI";
										} else {
											if (Glucemia2 >= 180) {
												AlertaR = "SI";
											}
											if (Glucemia2 < 180) {
												AlertaR = "NO";
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////

		/////////////////////////////// REGLAS MEDICO EN OPERACION
		/////////////////////////////// ////////////////////////////////////
		if (iServicioActual == 1) {
			if (iRama == 9) {
				if (Sintoma == 87) {
					AlertaR = this.EmoValidaLEntes(Sintoma, Integer.parseInt(cExpediente), Integer.parseInt(cExamen),
							iCveMdoTrans, iCvecategoria);
				}
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////

		switch (tpoResp) {
		case 1:
		case 2:
			if (iServicioActual == 48 && iRama == 16) {///////////////////////////// CARGA
														///////////////////////////// PUNTAJE
														///////////////////////////// /////////////////////////////////
				PuntajeSaos = PuntajeSaos + this.SAOS(Sintoma, iValor, resultado);
			}
			if (iServicioActual == 50 && iRama == 1) {//////// Prueba de
														//////// detección de
														//////// alcoholismo de
														//////// Michigan(MAST)
														//////// ////////
				IMichigan = IMichigan + this.MAST(Sintoma, iValor, resultado);
				if (Sintoma == 39) {
					AlertaR = this.AMAST(Sintoma, IMichigan, IMichigan);
				}
			}
			if (iServicioActual == 69 && iRama == 1) {//////// Prueba de
														//////// detección de
														//////// Transtornos
														//////// Psiquiatricos
														//////// ////////
				Psicologia1 = Psicologia1 + this.TPsi(Sintoma, iValor, resultado);
				Psicologia2 = Psicologia2 + this.TMen(Sintoma, iValor, resultado);
				Psicologia3 = Psicologia3 + this.PETD(Sintoma, iValor, resultado);
				Psicologia4 = Psicologia4 + this.TAH(Sintoma, iValor, resultado);
				if (Sintoma == 3) {
					AlertaR = this.ATPsi(Sintoma, Psicologia1, Psicologia1);
				}
				if (Sintoma == 32) {
					AlertaR = this.ATMen(Sintoma, Psicologia2, Psicologia2);
				}
				if (Sintoma == 42) {
					AlertaR = this.APETD(Sintoma, Psicologia3, Psicologia3);
				}
				if (Sintoma == 59) {
					AlertaR = this.APETD(Sintoma, Psicologia4, Psicologia4);
				}
			}
			break;
		case 4:
		case 3:
		case 5:
			if (iServicioActual == 48 && iRama == 16) {///////////////////////////// CARGA
														///////////////////////////// PUNTAJE
														///////////////////////////// /////////////////////////////////
				PuntajeSaos = PuntajeSaos + this.SAOS(Sintoma, iValor, resultado);
			}
			break;
		case 7:
		case 8:
			if (iServicioActual == 54 && iRama == 2) {///////////////////////////// ALERTAS
														///////////////////////////// DE
														///////////////////////////// RESPUESTAS
														///////////////////////////// A
														///////////////////////////// TORAX/////////////////////////////////

				if (iRama == 2 && Sintoma == 2) {
					ResulTorax = ResulTorax + Integer.parseInt(cValor);
				}
				if (iRama == 2 && Sintoma == 3) {
					if (cValor.equals("2"))
						AlertaR = "Solicite radiografía de tórax";
					if (cValor.equals("3"))
						AlertaR = "Investigue causas de disminución de la ventilación";
					ResulTorax = ResulTorax + Integer.parseInt(cValor);
				}
				if (iRama == 2 && Sintoma == 4) {
					if (cValor.equals("2"))
						AlertaR = "Posible condensación";
					if (cValor.equals("3"))
						AlertaR = "Solicite estudio radiográfico";
					ResulTorax = ResulTorax + Integer.parseInt(cValor);
				}
				if (iRama == 2 && Sintoma == 5) {
					if (cValor.equals("2"))
						AlertaR = "Posible condensación";
					if (cValor.equals("3"))
						AlertaR = "Solicite estudio radiográfico";
					if (cValor.equals("4")) {
						AlertaR = "Considere hiperreactividad bronquial, recomendable realizar espirometría";
						ResulTorax = ResulTorax + 4;
					}
					ResulTorax = ResulTorax + Integer.parseInt(cValor);
				}
			} else {
				if (iServicioActual == 48 && iRama == 15) {///////////////////////////// CARGA
															///////////////////////////// PUNTAJE
															///////////////////////////// /////////////////////////////////
					Puntaje = Puntaje + this.Epworth(Sintoma, iValor, resultado);
				} else {
					if (iServicioActual == 50 && iRama == 1) {///////////////////////////// Tabaquismo
																///////////////////////////// y
																///////////////////////////// Drogas/////////////////////////////////
						Itabaquismo = Itabaquismo + this.Tabaquismo(Sintoma, iValor, resultado);
						idrogas = idrogas + this.Drogas(Sintoma, iValor, resultado);
						if (Sintoma == 66) {
							AlertaR = this.ATabaquismo(Sintoma, Itabaquismo, Itabaquismo);
						}
						if (Sintoma == 87) {
							AlertaR = this.ADrogas(Sintoma, idrogas, idrogas);
							// System.out.println("idrogas = "+idrogas + "
							// Alerta = "+AlertaR);
						}
					}
				}
			}
			break;
		case 9:
		case 10:
		case 11:
			if (iServicioActual == 54 && iRama == 2) {///////////////////////////// ALERTAS
														///////////////////////////// DE
														///////////////////////////// RESPUESTAS
														///////////////////////////// A
														///////////////////////////// TORAX/////////////////////////////////
				if (iRama == 2 && Sintoma == 1) {
					if (cValor.equals("1")) {
						AlertaR = "Tórax en tonel, considere enfisema";
					}
					if (cValor.equals("2")) {
						AlertaR = "Investigue trastornos congénitos o adquitridos de la caja torácica";
					}
					if (cValor.equals("3")) {
						AlertaR = "Pectum excavatum, investigue grado de restricción";
					}
					if (cValor.equals("4")) {
						AlertaR = "Tórax en quilla";
					}

					boolean esnumero2 = true;
					esnumero2 = this.isIntNumber(cValor.trim());
					try {
						if (esnumero2) {
							ResulTorax = ResulTorax + Integer.parseInt(cValor);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} else {
				if (iServicioActual == 48) {///////////////////////////// ALERTAS
											///////////////////////////// DE
											///////////////////////////// RESPUESTAS
											///////////////////////////// A LA
											///////////////////////////// ESCALA
											///////////////////////////// DE
											///////////////////////////// SOMNOLENCIA
											///////////////////////////// DE
											///////////////////////////// EPWORTH
											///////////////////////////// /////////////////////////////////
					if (iRama == 15) {
						AlertaR = this.EsEpworth(Sintoma, Puntaje, 0);
					}
					if (iRama == 16) {
						AlertaR = this.PSAOS(Sintoma, PuntajeSaos, 0);
					}
				}
			}
			break;
		case 13:
		case 12:
		case 14:
		case 15:
		case 16:
			if (iServicioActual == 54 && iRama == 2) {///////////////////////////// ALERTAS
														///////////////////////////// DE
														///////////////////////////// RESPUESTAS
														///////////////////////////// A
														///////////////////////////// TORAX
														///////////////////////////// /////////////////////////////////
				String ResultadoFin = "";
				if (iRama == 2) {
					ResulTorax = ResulTorax - 4;// Regla debido a que los combos
												// se inicializan con 1 y no con
												// 0 y son 4 respuestas de
												// combo.
					if (ResulTorax >= 0 && ResulTorax < 3)
						ResultadoFin = "Sin compromiso respiratorio relevante";
					if (ResulTorax == 3)
						ResultadoFin = "Verifique movilidad torácica";
					if (ResulTorax >= 4 && ResulTorax < 6)
						ResultadoFin = "Probable Síndrome de condensación";
					if (ResulTorax >= 6 && ResulTorax < 8)
						ResultadoFin = "Probable síndrome de sustitución";
					if (ResulTorax >= 8)
						ResultadoFin = "Considere hiperreactividad bronquial, muy frecuente";

				}
				AlertaR = AlertaR + "" + ResultadoFin;

			}
		}
		System.out.println("#####################AlertaR="+AlertaR);
		return AlertaR;
	}

	// Obtener modo de transporte
	public int getMDOTrans(String exp, String exa) {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int transporte = 0;
		String cWhere = "SELECT ICVEMDOTRANS FROM EXPEXAMCAT " + "WHERE ICVEEXPEDIENTE = " + exp + " "
				+ "AND INUMEXAMEN = " + exa + " ORDER BY ICVEMDOTRANS DESC";

		try {
			transporte = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			System.out.println("Error al buscar registros en EXPExamCat " + ex);
		}

		return transporte;

	}

	// Obtener Categoria
	public int getCategoria(String exp, String exa) {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int categoria = 0;
		int transporte = this.getMDOTrans(exp, exa);

		String cWhere = "SELECT ICVECATEGORIA FROM EXPEXAMCAT " + "WHERE ICVEEXPEDIENTE = " + exp + " "
				+ "AND INUMEXAMEN = " + exa + " " + "AND ICVEMDOTRANS = " + transporte + " ORDER BY ICVECATEGORIA DESC";

		try {
			categoria = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			System.out.println("Error al buscar registros en EXPExamCat " + ex);
		}

		return categoria;

	}

	/**
	 * Devuelve la alerta correspondiente a Riesgo Coronario.
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 */
	public String RiesgoCor(int Pregunta, int Valor, int Valor2) {
		String Alerta = "";
		// TABAQUISMO
		if (Pregunta == 35) {
			if (Valor == 0) {
				Alerta = "REFORZAR EL NO FUMAR";
			}
			if (Valor >= 1) {
				Alerta = "SUSPENDER TABAQUISMO";
			}
			if (Valor > 5) {
				Alerta = "DISMINUIR CONSUMO DE CIGARRILLOS";
			}
		}
		// TENSION O ANSIEDAD
		if (Pregunta == 36) {
			if (Valor >= 0) {
				Alerta = "FOMENTAR DESCANSO";
			}
			if (Valor >= 2) {
				Alerta = "EVITAR ESTRES Y FATIGA";
			}
		}
		// TENSION O ANSIEDAD
		if (Pregunta == 37) {
			if (Valor >= 0) {
				Alerta = "REFORZAR HABITOS ALIMENTICIOS";
			}
			if (Valor >= 2) {
				Alerta = "DIETA PARA REDUCCION DE PESO";
			}
		}
		// TENSION O ANSIEDAD
		if (Pregunta == 37) {
			if (Valor >= 0) {
				Alerta = "REFORZAR HABITOS ALIMENTICIOS";
			}
			if (Valor >= 2) {
				Alerta = "DIETA PARA REDUCCION DE PESO";
			}
		}
		// FREC. CARDIACA
		if (Pregunta == 38) {
			if (Valor > 40) {
				Alerta = "FOMENTAR ACTIVIDAD FISICA";
			}
			if (Valor >= 85) {
				Alerta = "CAMINATA 20 MINUTOS DIARIAMENTE";
			}
		}
		// FREC. CARDIACA
		if (Pregunta == 41) {
			if (Valor < 5) {
				Alerta = "MEDIDAS PROFILACTICAS HIPERTENSION";
			}
			if (Valor >= 5) {
				Alerta = "TRATAMIENTO FARMACOLOGICO HIPERTENSION";
			}
		}
		// REPOSO
		if (Pregunta == 42) {
			if (Valor < 2) {
				Alerta = "INFORMAR QUE SU TRAZO E.C.G. ES NORMAL";
			}
			if (Valor == 2) {
				Alerta = "DESCARTAR OTRAS CARDIOPATIAS";
			}
			if (Valor == 3) {
				Alerta = "INVESTIGAR ISQUEMIA METODO APROPIADO";
			}
		}
		// DIABETES
		if (Pregunta == 44) {
			if (Valor2 == 0 && Valor >= 120) {
				Alerta = "PRUEBA TOLERANCIA GLUCOSA Y DIETA";
			}
			if (Valor2 == 1) {
				Alerta = "DIETA PARA DIABETICO";
			}
			if (Valor >= 120) {
				if (Valor >= 160) {
					if (Alerta.length() > 1) {
						Alerta = Alerta + "<br>TRATAMIENTO FARMACOLOGICO DIABETES";
					} else {
						Alerta = "TRATAMIENTO FARMACOLOGICO DIABETES";
					}
				} else {
					if (Alerta.length() > 1) {
						Alerta = Alerta + "<br>CONTROL GLUCEMIA";
					} else {
						Alerta = "CONTROL GLUCEMIA";
					}
				}
			}
		}
		// COLESTEROL
		if (Pregunta == 45) {
			if (Valor > 240) {
				Alerta = "TRATAMIENTO FARMACOLOGICO HIPERCOLESTEROLEMIA";
			}
			if (Valor <= 240) {
				Alerta = "DIETA  BAJA EN GRASAS DE ORIGEN ANIMAL";
			}
		}
		// RIESGO CORONARIO
		if (Pregunta == 48) {
			if (Valor >= 0) {
				Alerta = "MUY BAJO";
			}
			if (Valor >= 5) {
				Alerta = "BAJO";
			}
			if (Valor >= 15) {
				Alerta = "REGULAR";
			}
			if (Valor >= 25) {
				Alerta = "ALTO";
			}
			if (Valor >= 35) {
				Alerta = "MUY ALTO";
			}
		}
		// REQUIERE PRUEBA ESFUERZO ?
		if (Pregunta == 49) {
			if (Valor >= 0) {
				Alerta = "MUY BAJO";
			}
			if (Valor >= 5) {
				Alerta = "BAJO";
			}
			if (Valor >= 15) {
				Alerta = "REGULAR";
			}
			if (Valor >= 25) {
				Alerta = "ALTO";
			}
			if (Valor >= 35) {
				Alerta = "MUY ALTO";
			}
		}

		return Alerta;
	}

	/**
	 * Incrementa el contador de puntaje de Riesgo Coronario.
	 * 
	 * @author AG SA L
	 */
	public int RiesgoCor(int pregunta, int Valor, String Valor2) {
		int puntaje = 0;
		float Decimal = Float.parseFloat(Valor2.trim());
		// // Edad
		if (pregunta == 32) {
			if (Valor >= 30 && Valor < 40) {
				puntaje = puntaje + 1;
			}
			if (Valor >= 40 && Valor < 50) {
				puntaje = puntaje + 2;
			}
			if (Valor >= 50 && Valor < 60) {
				puntaje = puntaje + 3;
			}
			if (Valor >= 60) {
				puntaje = puntaje + 4;
			}
		}
		// // ANT. FAM. IAM. EDAD
		if (pregunta == 33) {
			if (Valor > 10 && Valor < 50) {
				puntaje = puntaje + 4;
			}
			if (Valor >= 50) {
				puntaje = puntaje + 2;
			}
		}
		// // ANT. PERSONAL IAM.
		if (pregunta == 34) {
			if (Decimal > 0.01 && Decimal < 1) {
				puntaje = puntaje + 8;
			}
			if (Decimal >= 1 && Decimal < 2) {
				puntaje = puntaje + 4;
			}
			if (Decimal >= 2 && Decimal < 5) {
				puntaje = puntaje + 3;
			}
			if (Decimal >= 5) {
				puntaje = puntaje + 2;
			}
		}
		// // TABAQUISMO
		if (pregunta == 35) {
			if (Valor >= 6 && Valor < 11) {
				puntaje = puntaje + 2;
			}
			if (Valor >= 11 && Valor < 30) {
				puntaje = puntaje + 3;
			}
			if (Valor >= 30) {
				puntaje = puntaje + 4;
			}
		}
		// // TENSION O ANSIEDAD
		if (pregunta == 36) {
			puntaje = puntaje + Valor;
		}
		// // OBESIDAD
		if (pregunta == 37) {
			puntaje = puntaje + Valor;
		}
		// // FREC. CARDIACA
		if (pregunta == 38) {
			if (Valor >= 75 && Valor < 85) {
				puntaje = puntaje + 1;
			}
			if (Valor >= 85 && Valor < 90) {
				puntaje = puntaje + 2;
			}
			if (Valor >= 90 && Valor < 100) {
				puntaje = puntaje + 3;
			}
			if (Valor >= 100) {
				puntaje = puntaje + 4;
			}
		}
		// // HIPERTENSION
		if (pregunta == 39) {
			if (Valor == 1) {
				puntaje = puntaje + 2;
			}
		}
		// // PRESION SISTOLICA
		if (pregunta == 40) {
			if (Valor >= 121 && Valor < 131) {
				puntaje = puntaje + 1;
			}
			if (Valor >= 131 && Valor < 141) {
				puntaje = puntaje + 2;
			}
			if (Valor >= 141 && Valor < 150) {
				puntaje = puntaje + 3;
			}
			if (Valor >= 150) {
				puntaje = puntaje + 4;
			}
		}
		// // PRESION DIASTOLICA
		if (pregunta == 41) {
			if (Valor >= 81 && Valor < 87) {
				puntaje = puntaje + 1;
			}
			if (Valor >= 87 && Valor < 96) {
				puntaje = puntaje + 2;
			}
			if (Valor >= 96 && Valor < 100) {
				puntaje = puntaje + 3;
			}
			if (Valor >= 100) {
				puntaje = puntaje + 4;
			}
		}
		// // REPOSO
		if (pregunta == 42) {
			puntaje = puntaje + Valor;
		}
		// // DIABETES
		if (pregunta == 43) {
			if (Valor >= 1) {
				puntaje = puntaje + 2;
			}
		}
		// // GLUCEMIA
		if (pregunta == 44) {
			if (Valor >= 101 && Valor < 110) {
				puntaje = puntaje + 1;
			}
			if (Valor >= 110 && Valor < 121) {
				puntaje = puntaje + 2;
			}
			if (Valor >= 121) {
				puntaje = puntaje + 3;
			}
		}
		// // COLESTEROL
		if (pregunta == 45) {
			if (Valor >= 201 && Valor < 221) {
				puntaje = puntaje + 2;
			}
			if (Valor >= 221 && Valor < 240) {
				puntaje = puntaje + 2;
			}
			if (Valor >= 240 && Valor < 261) {
				puntaje = puntaje + 3;
			}
			if (Valor >= 261) {
				puntaje = puntaje + 4;
			}
		}
		// // TRIGLICERIDOS
		if (pregunta == 46) {
			if (Valor >= 111 && Valor < 150) {
				puntaje = puntaje + 1;
			}
			if (Valor >= 150 && Valor < 231) {
				puntaje = puntaje + 2;
			}
			if (Valor >= 231) {
				puntaje = puntaje + 3;
			}
		}
		// // ACIDO URICO
		if (pregunta == 47) {
			if (Decimal >= 6.7 && Decimal < 7.4) {
				puntaje = puntaje + 1;
			}
			if (Decimal >= 7.4 && Decimal < 8.2) {
				puntaje = puntaje + 2;
			}
			if (Decimal >= 8.2) {
				puntaje = puntaje + 3;
			}
		}
		return puntaje;
	}

	/**
	 * Incrementa el contador de puntaje de Riesgo Coronario.
	 * 
	 * @author AG SA L
	 */
	public int Epworth(int pregunta, int Valor, String Valor2) {
		int puntaje = 0;
		// // EPWORTH
		if (pregunta > 1 && pregunta < 10) {
			puntaje = puntaje + Valor;
		}
		return puntaje;
	}

	/**
	 * Incrementa el contador de puntaje de Riesgo Coronario.
	 * 
	 * @author AG SA L
	 */
	public String Epworth2(int pregunta, int Valor, String Valor2) {
		int puntaje = 0;
		String alert = "";
		// // EPWORTH
		if (pregunta > 1 && pregunta < 10) {
			puntaje = puntaje + Valor;
		}
		return alert;
	}

	/**
	 * Devuelve la alerta correspondiente a la Escala de somolencia de Epworth.
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 */
	public String EsEpworth(int Pregunta, int Valor, int Valor2) {
		String Alerta = "";
		// Escala de somolencia de Epworth
		if (Pregunta == 10) {
			if (Valor <= 9) {
				Alerta = "Baja probabilidad de somnolencia diurna";
			}
			if (Valor >= 10) {
				Alerta = "Probabilidad moderada de hipersomnolencia diurna";
			}
			if (Valor >= 15) {
				Alerta = "Requiere estudio para descatar trastorno del sue?o";
			}
			if (Valor >= 18) {
				Alerta = "Alta probabilidad de trastorno del sue?o, no apto";
			}
		}
		return Alerta;
	}

	public boolean isIntNumber(String num) {
		try {
			Integer.parseInt(num);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Incrementa el contador de puntaje de Prob-SAOS.
	 * 
	 * @author AG SA L
	 */
	public int SAOS(int pregunta, int Valor, String Valor2) {
		int puntaje = 0;
		float Decimal = Float.parseFloat(Valor2.trim());
		int valor3 = (int) Decimal;
		// // SAOS
		if (pregunta == 1) {
			if (valor3 == 1) {
				puntaje = puntaje + 2;
			}
		}
		if (pregunta == 2) {
			if (valor3 == 1) {
				puntaje = puntaje + 2;
			}
		}
		if (pregunta == 3) {
			if (valor3 == 1) {
				puntaje = puntaje + 3;
			}
		}
		if (pregunta == 4) {
			if (valor3 == 1) {
				puntaje = puntaje + 1;
			}
		}
		if (pregunta == 5) {
			if (valor3 == 1) {
				puntaje = puntaje + 2;
			}
		}
		if (pregunta == 6) {
			puntaje = puntaje + valor3;
		}
		if (pregunta == 8) {
			if (valor3 >= 30) {
				puntaje = puntaje + 3;
			}
		}
		return puntaje;
	}

	/**
	 * Devuelve la alerta correspondiente a la Probabilidad SAOS.
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 */
	public String PSAOS(int Pregunta, int Valor, int Valor2) {
		String Alerta = "";
		if (Pregunta == 7) {
			if (Valor >= 48) {
				Alerta = "Alta probabilidad de SAOS";
			}
		}
		if (Pregunta == 7) {
			if (Valor <= 47) {
				Alerta = "Probabilidad intermedia de SAOS";
			}
		}
		if (Pregunta == 7) {
			if (Valor <= 42) {
				Alerta = "Baja probabilidad de SAOS";
			}
		}
		return Alerta;
	}

	/**
	 * Incrementa el contador de puntaje de la Prueba de detección de
	 * alcoholismo de Michigan(MAST).
	 * 
	 * @author AG SA L
	 */
	public int MAST(int pregunta, int Valor, String Valor2) {
		int puntaje = 0;
		if (pregunta == 39 || pregunta == 40 || pregunta == 42 || pregunta == 45 || pregunta == 46 || pregunta == 47
				|| pregunta == 48 || pregunta == 49 || pregunta == 50 || pregunta == 51 || pregunta == 52
				|| pregunta == 53 || pregunta == 54 || pregunta == 55 || pregunta == 56 || pregunta == 57
				|| pregunta == 88 || pregunta == 89 || pregunta == 58 || pregunta == 59) {
			if (Valor2.equals("1")) {
				puntaje = puntaje + 1;
			}
		}
		if (pregunta == 38 || pregunta == 41 || pregunta == 43 || pregunta == 44) {
			if (Valor2.equals("2")) {
				puntaje = puntaje + 2;
			}
			if (Valor2.equals("1")) {
				puntaje = puntaje + 1;
			}
		}
		return puntaje;
	}

	/**
	 * Devuelve la alerta correspondiente a la Prueba de detección de
	 * alcoholismo de Michigan(MAST).
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 */
	public String AMAST(int Pregunta, int Valor, int Valor2) {
		String Alerta = "";
		if (Pregunta == 39) {
			if (Valor > 5) {
				Alerta = "Prueba de detección de alcoholismo de Michigan(MAST), indica que deberá solicitarse evaluación con psiquiatría";
			}
		}
		return Alerta;
	}

	/**
	 * Incrementa el contador de puntaje de Tabaquismo.
	 * 
	 * @author AG SA L
	 */
	public int Tabaquismo(int pregunta, int Valor, String Valor2) {
		int puntaje = 0;
		if (pregunta == 61) {
			if (Valor == 1) {
				puntaje = puntaje + 3;
			}
			if (Valor == 2) {
				puntaje = puntaje + 4;
			}
			if (Valor == 3) {
				puntaje = puntaje + 5;
			}
			if (Valor == 4) {
				puntaje = puntaje + 6;
			}
		}
		if (pregunta == 63) {
			if (Valor == 1) {
				puntaje = puntaje + 3;
			}
			if (Valor == 3) {
				puntaje = puntaje + 1;
			}
			if (Valor == 3) {
				puntaje = puntaje + 2;
			}
		}
		if (pregunta == 64) {
			if (Valor == 2) {
				puntaje = puntaje + 1;
			}
		}
		if (pregunta == 65) {
			if (Valor == 1) {
				puntaje = puntaje + 1;
			}
		}
		if (pregunta == 66) {
			if (Valor == 2) {
				puntaje = puntaje + 1;
			}
		}
		return puntaje;
	}

	/**
	 * Devuelve la alerta correspondiente a la Prueba de Tabaquismo.
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 */
	public String ATabaquismo(int Pregunta, int Valor, int Valor2) {
		String Alerta = "";
		if (Pregunta == 66) {
			if (Valor >= 5) {
				Alerta = "La Prueba de Tabaquismo, diagnostica que es Dependiente  y requerirá evaluación medicina interna";
			}
		}
		return Alerta;
	}

	/*
	 * Incrementa el contador de puntaje de Drogas.
	 * 
	 * @author AG SA L
	 */
	public int Drogas(int pregunta, int Valor, String Valor2) {
		int puntaje = 0;
		if (pregunta == 30 || pregunta == 31 || pregunta == 32 || pregunta == 33 || pregunta == 34 || pregunta == 35
				|| pregunta == 87) {
			if (Valor == 1) {
				puntaje = puntaje + 1;
			}
		}

		return puntaje;
	}

	/**
	 * Devuelve la alerta correspondiente a la Prueba de Drogas.
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 */
	public String ADrogas(int Pregunta, int Valor, int Valor2) {
		String Alerta = "";
		if (Pregunta == 87) {
			if (Valor >= 5) {
				Alerta = "La Prueba de Drogas diagnostico que Requiere evaluación de psicología y psicquiatría";
			}
		}
		return Alerta;
	}

	/**
	 * Incrementa el contador de puntaje de la Prueba de Transtornos
	 * Psiquiatricos.
	 * 
	 * @author AG SA L
	 */
	public int TPsi(int pregunta, int Valor, String Valor2) {
		int puntaje = 0;
		if (pregunta == 8 || pregunta == 9 || pregunta == 10 || pregunta == 11 || pregunta == 12 || pregunta == 13
				|| pregunta == 14 || pregunta == 15 || pregunta == 1 || pregunta == 2 || pregunta == 3) {
			if (Valor2.equals("1")) {
				puntaje = puntaje + 1;
			}
		}
		return puntaje;
	}

	/**
	 * Devuelve la alerta correspondiente a la Prueba de Transtornos
	 * Psiquiatricos.
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 */
	public String ATPsi(int Pregunta, int Valor, int Valor2) {
		String Alerta = "";
		if (Pregunta == 3) {
			if (Valor > 2) {
				Alerta = "La Prueba de Transtornos Psiquiatricos, diagnostica que Requiere evaluación Psicológica";
			}
		}
		return Alerta;
	}

	/**
	 * Incrementa el contador de puntaje de la Prueba de Transtornos Mentales.
	 * 
	 * @author AG SA L
	 */
	public int TMen(int pregunta, int Valor, String Valor2) {
		int puntaje = 0;
		if (pregunta > 15 && pregunta < 33) {
			if (Valor2.equals("1")) {
				puntaje = puntaje + 1;
			}
		}
		return puntaje;
	}

	/**
	 * Devuelve la alerta correspondiente a la Prueba de Transtornos Mentales.
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 */
	public String ATMen(int Pregunta, int Valor, int Valor2) {
		String Alerta = "";
		if (Pregunta == 32) {
			if (Valor > 3) {
				Alerta = "La Prueba de Transtornos Mentales, diagnostica que Requiere evaluación Psicológica";
			}
		}
		return Alerta;
	}

	/**
	 * Incrementa el contador de puntaje de la Prueba de Esquizofrenia y
	 * trastornos delirantes.
	 * 
	 * @author AG SA L
	 */
	public int PETD(int pregunta, int Valor, String Valor2) {
		int puntaje = 0;
		if (pregunta > 34 && pregunta < 43) {
			if (Valor2.equals("1")) {
				puntaje = puntaje + 1;
			}
		}
		return puntaje;
	}

	/**
	 * Devuelve la alerta correspondiente a la Prueba de Esquizofrenia y
	 * trastornos delirantes.
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 */
	public String APETD(int Pregunta, int Valor, int Valor2) {
		String Alerta = "";
		if (Pregunta == 42) {
			if (Valor > 0) {
				Alerta = "La Prueba de Esquizofrenia y trastornos delirantes, diagnostica que Requiere evaluación Psiquiátrica";
			}
		}
		return Alerta;
	}

	/**
	 * Incrementa el contador de puntaje de la Prueba del Transtorno afectivos
	 * del Humor.
	 * 
	 * @author AG SA L
	 */
	public int TAH(int pregunta, int Valor, String Valor2) {
		int puntaje = 0;
		if (pregunta > 43 && pregunta < 60) {
			if (Valor2.equals("1")) {
				puntaje = puntaje + 1;
			}
		}
		return puntaje;
	}

	/**
	 * Devuelve la alerta correspondiente a la Prueba del Transtorno afectivos
	 * del Humor.
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 */
	public String ATAH(int Pregunta, int Valor, int Valor2) {
		String Alerta = "";
		if (Pregunta == 59) {
			if (Valor > 1) {
				Alerta = "La Prueba de trastornos afectivos del humor, diagnostica que Requiere evaluación Psicológica";
			}
		}
		return Alerta;
	}

	/**
	 * Devuelve la alerta correspondiente a la Prueba del Transtorno afectivos
	 * del Humor.
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 * @throws DAOException
	 */
	public String EmoValidaLEntes(int Pregunta, int Expediente, int Examen, int trans, int categoria)
			throws DAOException {
		String Alerta = "";
		int resultado = 0;
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		resultado = dEXPExamAplica.RegresaInt(
				"select dvalorini from expresultado " + "where icveexpediente = " + Expediente + " and inumexamen = "
						+ Examen + " AND  ICVESERVICIO = 1 " + "AND ICVERAMA = 9 AND ICVESINTOMA = 87");
		
		if (resultado == 3) {
			TDEXPRegSint dEXPRegSint = new TDEXPRegSint();
			resultado = dEXPRegSint.FindByCount("SELECT COUNT(ICVEEXPEDIENTE) FROM EXPREGISIN "
					+ "WHERE ICVEEXPEDIENTE = " + Expediente + " AND INUMEXAMEN = " + Examen
					+ " AND  ICVESERVICIO = 1 AND ICVERAMA = 9 AND ICVESINTOMA = 87");
			if (resultado == 0) {
				Alerta = dEXPRegSint.insert(null, Expediente, Examen, 1, 9, 87, trans, categoria, 0, 1);
				if (Alerta.length() > 0) {
					Alerta = "CAUSA DE NO APTITUD";
				}
			} else {
				Alerta = "CAUSA DE NO APTITUD";
			}
		}
		return Alerta;
	}

}
