package gob.sct.medprev;

import gob.sct.medprev.dao.TDEXPDiagnostico;
import gob.sct.medprev.dao.TDEXPExamAplica;
import gob.sct.medprev.dao.TDEXPRecomendacion;
import gob.sct.medprev.dao.TDEXPRegSint;
import gob.sct.medprev.dao.TDEXPResultado;
import gob.sct.medprev.dao.TDMEDREGSINExt1;
import gob.sct.medprev.vo.TVEXPRama;
import gob.sct.medprev.vo.TVEXPResultado;

import java.util.Vector;

import com.micper.excepciones.CFGException;
import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.seguridad.dao.CFGListBase2;
import com.micper.util.TExcel;
import com.micper.util.TFechas;

/**
 * * Clase de configuraci???n para CFG de la pagina pg070104070
 * <p>
 * Administraci???n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104070CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104070CFG.png'>
 */
public class pg070104071CFG extends CFGListBase2 {
	public pg070104071CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070105020.jsp";
		NavStatus = "Disabled";
	}

	public pg070104071CFG(String cOpcPaginas) {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = cOpcPaginas;
		// cPaginas="pg070105020.jsp";
		NavStatus = "Disabled";
	}

	private StringBuffer activeX = new StringBuffer("");
	private com.micper.seguridad.vo.TVDinRep02 vEXPDatos;
	private java.util.Vector vSintomas;
	private java.util.Vector vDiagnostico;
	private java.util.Vector vRemendacion;

	/**
	 * M??todo FillVector
	 */
	public void fillVector() {
		TDEXPResultado dEXPResultado = new TDEXPResultado();
		Vector vcReporte = new Vector();
		try {
			
			////////////////// MODULO DIABETES CALCULO DE METAS /////////////////////////////
		    TDMEDREGSINExt1 dMEDREGSINExt1 = new TDMEDREGSINExt1();
		    TDEXPRegSint dEXPRegSint = new TDEXPRegSint();
		    String cSql ="";
		    int Reglas = 0;
		    Reglas = dEXPRegSint.RegresaInt("Select count(icveexpediente) from expregsin where "+ 
		    		" icveexpediente = "+request.getParameter("hdiCveExpediente")+
		    		" and inumexamen = "+request.getParameter("hdiNumExamen") +
		    		" and icveservicio = 75");
		    if(request.getParameter("hdICveServicio").toString().equals("75") && Reglas == 0){
		    	//System.out.println("Calcular Metas");
		    	dMEDREGSINExt1.CalculaMetaDiabetes(request.getParameter("hdiCveExpediente"), request.getParameter("hdiNumExamen"));
		    }
			/////////////////////////////////////////////////////////////////////////////////
			
			
			// Obtener datos del Examen.
			TVEXPRama vDatos = (TVEXPRama) this.getInputs();
			if (vDatos.getICveExpediente() == 0)
				vDatos = (TVEXPRama) this.getInputs05020();
			this.vEXPDatos = new TDEXPExamAplica().findResultExamA(
					vDatos.getICveExpediente(), vDatos.getINumExamen(),
					vDatos.getICveServicio());
			vDespliega = dEXPResultado.findResultExamA2(
					vDatos.getICveExpediente(), vDatos.getINumExamen(),
					vDatos.getICveServicio(), vDatos.getICveRama());
			this.vSintomas = vDespliega;
			iNumReg = vDespliega.size();
			// Obtener informacion de los diagnosticos y Recomendaciones
			if (this.vEXPDatos.getInt("lReqDiag") == 1) {
				this.vDiagnostico = new TDEXPDiagnostico().getDiagEspXServ(
						String.valueOf(vDatos.getICveExpediente()),
						String.valueOf(vDatos.getINumExamen()),
						String.valueOf(vDatos.getICveServicio()));
				this.vRemendacion = new TDEXPRecomendacion().getRecEspXServ(
						String.valueOf(vDatos.getICveExpediente()),
						String.valueOf(vDatos.getINumExamen()),
						String.valueOf(vDatos.getICveServicio()));
			}
			
			//System.out.println("hdBoton2 = "+request.getParameter("hdBoton2"));
			if (request.getParameter("hdReporte") != null) {
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Reporte") == 0) {
					vcReporte = vDespliega;
					activeX.append(this.GenRep(vcReporte));
				}
			}
			
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public StringBuffer GenRep(Object obj) {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas();
		String cNomArchivo = new String("pg070104011");
		StringBuffer buffer = new StringBuffer();
		TVEXPResultado vEXPResultado = new TVEXPResultado();
		Vector vcResultado = new Vector();
		TFechas dtFecha = new TFechas("07");
		int iServicioCardio = new Integer(
				vParametros.getPropEspecifica("EPIServicioCardio")).intValue();
		int iRamaCardio = new Integer(
				vParametros.getPropEspecifica("EPIRamaCardio")).intValue();
		int iSintomaCardio = new Integer(
				vParametros.getPropEspecifica("EPISintomaCardio")).intValue();

		try {
			vcResultado = (Vector) obj;
			for (int j = 0; j < vcResultado.size(); j++) {
				vEXPResultado = (TVEXPResultado) vcResultado.get(j);
				if (vEXPResultado.getICveServicio() == iServicioCardio
						&& vEXPResultado.getICveRama() == iRamaCardio
						&& vEXPResultado.getICveSintoma() == iSintomaCardio) {
					xl.comDespliega("C", 8, vEXPResultado.getCCaracter());
					xl.comDespliega("I", 13, request.getParameter("hdCNombre"));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		buffer = xl.creaActiveX("pg070104011", cNomArchivo, false, false, true);
		return buffer;
	}
	
	


	public String getActiveX() {
		return activeX.toString();
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

	public com.micper.seguridad.vo.TVDinRep02 getVEXPDatos() {
		return vEXPDatos;
	}

	/**
	 * M??todo utilizado para obtener los par???metros enviados a la p???gina.
	 * 
	 * @return Object TVEXPRama Contiene la informaci???n del Expediente, el
	 *         n???mero de Examen, el servicio y la rama que se est???
	 *         consultado.
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		TVEXPRama vDatos = new TVEXPRama();
		try {
			cCampo = "" + request.getParameter("hdiCveExpediente");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveExpediente(iCampo);
			cCampo = "" + request.getParameter("hdiNumExamen");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setINumExamen(iCampo);
			cCampo = "" + request.getParameter("hdICveServicio");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveServicio(iCampo);
			cCampo = "" + request.getParameter("hdICveRama");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveRama(iCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vDatos;
	}

	/**
	 * M??todo utilizado para obtener los par???metros enviados a la p???gina.
	 * 
	 * @return Object TVEXPRama Contiene la informaci???n del Expediente, el
	 *         n???mero de Examen, el servicio y la rama que se est???
	 *         consultado.
	 */
	public Object getInputs05020() throws CFGException {
		String cCampo;
		int iCampo;
		TVEXPRama vDatos = new TVEXPRama();
		try {
			cCampo = "" + request.getParameter("hdICveExpediente");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveExpediente(iCampo);
			cCampo = "" + request.getParameter("hdINumExamen");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setINumExamen(iCampo);
			cCampo = "" + request.getParameter("hdICveServicio");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveServicio(iCampo);
			cCampo = "" + request.getParameter("hdICveRama");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveRama(iCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vDatos;
	}

	public long getEdadPersonal(java.sql.Date dtNacimiento) {
		java.sql.Date dtFecha = new java.sql.Date(
				new java.util.Date().getTime()); // fecha actual
		long edad = dtFecha.getTime() - dtNacimiento.getTime();
		edad /= (1000 * 60 * 60 * 24 * 365.25);
		return edad;
	}

	public java.util.Vector getVSintomas() {
		return vSintomas;
	}

	public java.util.Vector getVDiagnostico() {
		return vDiagnostico;
	}

	public java.util.Vector getVRemendacion() {
		return vRemendacion;
	}

	// -- Termina la escritura en EXPDictamenServ

	// /Regresa modo de transporte
	/**
	 * Devuelve el siguiente registro por procesar de EXPRama.
	 * 
	 * @return un value object con el registro de la rama, o null si no hay
	 *         m???s registros.
	 * @author Romeo Sanchez
	 */
	public int getMDOTrans(String exp, String exa) {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int transporte = 0;
		String cWhere = "SELECT ICVEMDOTRANS FROM EXPEXAMCAT "
				+ "WHERE ICVEEXPEDIENTE = " + exp + " " + "AND INUMEXAMEN = "
				+ exa + " ORDER BY ICVEMDOTRANS DESC";

		try {
			transporte = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}

		return transporte;

	}

	// Regresa Categoria

	/**
	 * Devuelve el siguiente registro por procesar de EXPRama.
	 * 
	 * @return un value object con el registro de la rama, o null si no hay
	 *         m???s registros.
	 * @author Romeo Sanchez
	 */
	public int getCategoria(String exp, String exa) {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int categoria = 0;
		int transporte = this.getMDOTrans(exp, exa);

		String cWhere = "SELECT ICVECATEGORIA FROM EXPEXAMCAT "
				+ "WHERE ICVEEXPEDIENTE = " + exp + " " + "AND INUMEXAMEN = "
				+ exa + " " + "AND ICVEMDOTRANS = " + transporte
				+ " ORDER BY ICVECATEGORIA DESC";

		try {
			categoria = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}

		return categoria;

	}

	/**
	 * Devuelve el siguiente registro por procesar de EXPRama.
	 * 
	 * @return un value object con el registro de la rama, o null si no hay
	 *         m???s registros.
	 * @author Romeo Sanchez
	 */
	public int getDictamen(String exp, String exa, String Serv) {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int dictaminado = 0;

		String cWhere = "SELECT COUNT (LDICTAMEN) " + "FROM EXPDICTAMENSERV "
				+ "WHERE ICVEEXPEDIENTE = " + exp + " AND " + "INUMEXAMEN = "
				+ exa + "  AND " + "ICVESERVICIO = " + Serv + " ";
		try {
			dictaminado = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}

		return dictaminado;

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
						Alerta = Alerta
								+ "<br>TRATAMIENTO FARMACOLOGICO DIABETES";
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
		float Decimal = Float.parseFloat(Valor2.trim());
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
		if (pregunta == 39 || pregunta == 40 || pregunta == 42
				|| pregunta == 45 || pregunta == 46 || pregunta == 47
				|| pregunta == 48 || pregunta == 49 || pregunta == 50
				|| pregunta == 51 || pregunta == 52 || pregunta == 53
				|| pregunta == 54 || pregunta == 55 || pregunta == 56
				|| pregunta == 57 || pregunta == 88 || pregunta == 89
				|| pregunta == 58 || pregunta == 59) {
			if (Valor2.equals("1")) {
				puntaje = puntaje + 1;
			}
		}
		if (pregunta == 38 || pregunta == 41 || pregunta == 43
				|| pregunta == 44) {
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
		float Decimal = Float.parseFloat(Valor2.trim());
		int valor3 = (int) Decimal;
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
		float Decimal = Float.parseFloat(Valor2.trim());
		int valor3 = (int) Decimal;
		if (pregunta == 30 || pregunta == 31 || pregunta == 32
				|| pregunta == 33 || pregunta == 34 || pregunta == 35
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
		if (pregunta == 8 || pregunta == 9 || pregunta == 10 || pregunta == 11
				|| pregunta == 12 || pregunta == 13 || pregunta == 14
				|| pregunta == 15 || pregunta == 1 || pregunta == 2
				|| pregunta == 3) {
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
	public String EmoValidaLEntes(int Pregunta, int Expediente, int Examen, int trans, int categoria) throws DAOException {
		String Alerta = "";
		int resultado = 0;
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		resultado = dEXPExamAplica.RegresaInt("select dvalorini from expresultado " +
				"where icveexpediente = "+Expediente+
				" and inumexamen = "+Examen +" AND  ICVESERVICIO = 1 " +
				"AND ICVERAMA = 9 AND ICVESINTOMA = 87");
		//System.out.println("######### Reglas EMO ########### EmoValidaLEntes - 1");
		//System.out.println("resultado = "+resultado);
		if(resultado == 3){
			TDEXPRegSint dEXPRegSint = new TDEXPRegSint();
			//System.out.println("######### Reglas EMO ########### EmoValidaLEntes - 2");
			resultado = dEXPRegSint.FindByCount("SELECT COUNT(ICVEEXPEDIENTE) FROM EXPREGISIN "+ 
					"WHERE ICVEEXPEDIENTE = "+Expediente+" AND INUMEXAMEN = "+Examen+" AND  ICVESERVICIO = 1 AND ICVERAMA = 9 AND ICVESINTOMA = 87");
			//System.out.println("######### Reglas EMO ########### EmoValidaLEntes - 3");
			if(resultado == 0){
				//System.out.println("######### Reglas EMO ########### EmoValidaLEntes - 4");
				Alerta = dEXPRegSint.insert(null, Expediente, Examen, 1, 9, 87, trans, categoria, 0, 1);
				if(Alerta.length()>0){
					Alerta = "CAUSA DE NO APTITUD";		
					//System.out.println("######### Reglas EMO ########### EmoValidaLEntes - 5");
				}
			}else{
				//System.out.println("######### Reglas EMO ########### EmoValidaLEntes - 6");
				Alerta = "CAUSA DE NO APTITUD";
			}
		}
		return Alerta;
	}
	
	
	/**
	 * Devuelve la informacion del Dipositivo que capturo los Signos Vitales
	 * del Humor.
	 * 
	 * @author AG SA L RECOMENDACIONES TERAPEUTICAS
	 * @throws CFGException 
	 */
	public String getDispositivoSVS() throws CFGException {
		String Alerta = "";
		TDEXPResultado dEXPResultado = new TDEXPResultado();
		// Obtener datos del Examen.
			TVEXPRama vDatos = (TVEXPRama) this.getInputs();
			String Where = " Where iCveExpediente = "+vDatos.getICveExpediente()
						  +" and iNumExamen = "+vDatos.getINumExamen()
						  +" and iCveServicio = 11"
						  +" and iCveRama = 1 and iCveSintoma = 15 ";
			try{
				Alerta = dEXPResultado.FindByDispositivo(Where);
			} catch (Exception ex) {
				System.out.println("La consulta ha fallado");
			}
		return Alerta;
	}

}
