package gob.sct.medprev;

import java.util.*;

import org.jxls.transformer.*;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.msc.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para BO del Cat�logo de Reactivos
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Jaime Enrique Su�rez Romero
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070306011.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070306011.png'>
 */
public class pg070306011CFG extends CFGCatBase2 {

	public String cActual2 = "";
	private StringBuffer activeX = new StringBuffer("");

	public pg070306011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070306010.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDTOXReactivo dTOXReactivo = new TDTOXReactivo();
		try {
			// modificado para el manejo de la llave primaria
			TVTOXReactivo tr = (TVTOXReactivo) this.getInputs();

			// el a�o actual
			TFechas t = new TFechas();
			int y = t.getIntYear(t.TodaySQL());
			tr.setIAnio(y);

			cClave = (String) dTOXReactivo.insert(null, tr);
			this.cActual = cClave;
			cActual2 = ((TVTOXReactivo) this.getInputs()).getICveLaboratorio()
					+ "";
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDTOXReactivo dTOXReactivo = new TDTOXReactivo();
		try {
			// modificado para el manejo de la llave primaria
			TVTOXReactivo tr = (TVTOXReactivo) this.getInputs();

			// el a�o actual
			TFechas t = new TFechas();
			int y = t.getIntYear(t.TodaySQL());
			tr.setIAnio(y);
			cClave = (String) dTOXReactivo.update(null, tr);
			cActual2 = ((TVTOXReactivo) this.getInputs()).getICveLaboratorio()
					+ "";
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		TDTOXReactivo dTOXReactivo = new TDTOXReactivo();
		try {
			cClave = (String) dTOXReactivo.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo getActiveX
	 */
	public String getActiveX() {
		return activeX.toString();
	}

	/**
	 * Metodo getAccion
	 */
	public String getAccion() {
		return request.getParameter("hdBoton");
		// return this.cAccionOriginal;
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXReactivo dTOXReactivo = new TDTOXReactivo();
		try {
			String accion = request.getParameter("hdBoton");
			String lab = request.getParameter("hdCampoClave1"); // iCveLaboratorio
			String react = request.getParameter("hdCampoClave2"); // iCveReactivo
			String pag = request.getParameter("hdPagina");

			if (accion.startsWith("Guardar")) {
				// react = cClave;
			}
			String where = "";

			if ((lab == null || lab.equals(""))
					&& (react == null || react.equals(""))) {
				where += "";
			}
			if ((lab != null && !lab.equals(""))
					&& (react == null || react.equals(""))) {
				where += " AND iCveLaboratorio = " + lab;
			}

			/*
			 * if ( (pag != null && pag.equals("pg070306010.jsp")) || // si
			 * viene de la p�gina de listado...
			 * (accion.startsWith("Guardar"))) { // accion.equals("Modificar")
			 * || if ( (lab != null && !lab.equals("")) && (react != null &&
			 * !react.equals(""))) { where += " AND iCveReactivo = " + react +
			 * " and iCveLaboratorio = " + lab; } }
			 */
			// if(cCondicion!=null&&!cCondicion.equals("")) where += " and " +
			// cCondicion;

			if ("Actual".compareToIgnoreCase(this.cAccionOriginal) == 0) {
				if (cCondicion.compareTo("") != 0) {
					where += " AND " + cCondicion + " AND iAnio = "
							+ request.getParameter("iAnio")
							+ " AND iCveLaboratorio = "
							+ request.getParameter("iCveUniMed");
				} else {
					where += " AND iAnio = " + request.getParameter("iAnio")
							+ " AND iCveLaboratorio = "
							+ request.getParameter("iCveUniMed");
					// vDespliega = dToxReactivo.findByWhere(cCondicion,
					// cOrden);
				}

			}

			if (request.getParameter("iAnioSelect") != null
					&& request.getParameter("iCveUniMed") != null) {
				if (cCondicion.compareTo("") != 0) {
					where += " AND " + cCondicion + " AND iAnio = "
							+ request.getParameter("iAnioSelect")
							+ " AND iCveLaboratorio = "
							+ request.getParameter("iCveUniMed");
				} else {
					where += " AND iAnio = "
							+ request.getParameter("iAnioSelect")
							+ " AND iCveLaboratorio = "
							+ request.getParameter("iCveUniMed");
					// vDespliega = dToxReactivo.findByWhere(cCondicion,
					// cOrden);
				}
			}

			vDespliega = dTOXReactivo.findByWhere(where, cOrden);

			// Modificado Rafael Alcocer Caldera 22/Sep/2006
			// *********************************************
			// Agrego MGonzalez 11/Noviembre/2004
			if (request.getParameter("hdReporte") != null) {
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Inmunoensayo") == 0) {
					// activeX.append(this.GenRepI(this.getInputs()));

					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Inmunoensayo".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");

						// Primero bajo la plantilla del servidor al disco local
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("pg070306011AP",
								"pg070306011AP", false, false, false));

						where += " and R.iCveReactivo = "
								+ Integer.parseInt(request
										.getParameter("iCveReactivo"));

						vDespliega = dTOXReactivo.findByWhere(where, cOrden);
						JXLSBean jxlsBean = dTOXReactivo.getJXLSBean();

						Map beans = new HashMap();
						beans.put("jxlsBean", jxlsBean);
						XLSTransformer transformer = new XLSTransformer();
						transformer.transformXLS(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "pg070306011AP.xls", beans,
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "pg070306011AP-out.xls");

						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX(
								"pg070306011AP-out", false, false, true));
					}
				}
			}

			// Modificado Rafael Alcocer Caldera 22/Sep/2006
			// *********************************************
			if (request.getParameter("hdReporte") != null) {
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Cromatografia") == 0) {
					// activeX.append(this.GenRepC(this.getInputs()));

					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Cromatografia".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");

						// Primero bajo la plantilla del servidor al disco local
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("pg070306011AC",
								"pg070306011AC", false, false, false));

						where += " and R.iCveReactivo = "
								+ Integer.parseInt(request
										.getParameter("iCveReactivo"));

						vDespliega = dTOXReactivo.findByWhere(where, cOrden);
						JXLSBean jxlsBean = dTOXReactivo.getJXLSBean();

						Map beans = new HashMap();
						beans.put("jxlsBean", jxlsBean);
						XLSTransformer transformer = new XLSTransformer();
						transformer.transformXLS(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "pg070306011AC.xls", beans,
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "pg070306011AC-out.xls");

						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX(
								"pg070306011AC-out", false, false, true));
					}
				}
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
		{
		}
	}

	/**
	 * Metodo GenRepC
	 */

	public StringBuffer GenRepC(Object obj) {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas();
		String cNomArchivo = new String("pg070306011C");
		int iRenglon = 0;
		int iFilasxPag = 65;
		int iPag = 0;
		int iPagAnt = 0;
		int iRenIni = 0;
		int iRenFin = 0;
		int iRenxPag = 4;
		int iCvePersonal = 0;
		int iCveUniMed = 0;
		int iCveProceso = 0;
		String cPersonal = "";
		String cCveUniMed = "";
		String cCveProceso = "";
		String pName = "";
		String pEmpresa = "";
		String Doctor = "";
		StringBuffer buffer = new StringBuffer();
		java.sql.Date dtConcluido;
		String dFinalExamen = "";
		int lDictamen = 1;
		TVTOXReactivo vTOXReactivo = new TVTOXReactivo();
		TDTOXReactivo dTOXReactivo = new TDTOXReactivo();
		Vector vcTOXReactivo = new Vector();
		TFechas dtFecha = new TFechas("07");

		try {
			// vcTOXReactivo = (Vector) obj;
			// vTOXReactivo()

			vTOXReactivo = (TVTOXReactivo) obj;
			java.sql.Date dtTemp;
			String cFecha = "";

			// Encabezado

			vcTOXReactivo = dTOXReactivo.findByWhere(
					" and R.iCveLaboratorio = "
							+ vTOXReactivo.getICveLaboratorio()
							+ " and R.iCveReactivo = "
							+ vTOXReactivo.getICveReactivo(), "");

			if (vcTOXReactivo.size() > 0) {
				vTOXReactivo = (TVTOXReactivo) vcTOXReactivo.get(0);
			}

			xl.comDespliega("B", 9, vTOXReactivo.getCDscReactivo());
			xl.comDespliega("C", 10, vTOXReactivo.getCDscReactivo());
			xl.comDespliega("C", 11, vTOXReactivo.getICodigo());
			xl.comDespliega("C", 12, "" + vTOXReactivo.getDtPreparacion());
			xl.comDespliega("C", 13, "" + vTOXReactivo.getDtCaducidad());
			xl.comDespliega("C", 14, "" + vTOXReactivo.getCNomCompletoPrepara());
			xl.comDespliega("C", 17, "" + vTOXReactivo.getDVolumen());
			xl.comDespliega("C", 18,
					"" + vTOXReactivo.getCNomCompletoAutoriza());
			xl.comDespliega("C", 19, "" + vTOXReactivo.getDtAutoriza());

			xl.comDespliega("E", 16, "" + vTOXReactivo.getCDscMarcaSust());
			xl.comDespliega("D", 18, "" + vTOXReactivo.getCObservacion());

			// Detalle
			TDTOXCtrolCalibra dTOXCtrolCalibra = new TDTOXCtrolCalibra();
			Vector vcTOXCtrolCalibra = new Vector();
			TVTOXCtrolCalibra vTOXCtrolCalibra = new TVTOXCtrolCalibra();

			String cWhere = "";
			cWhere = " where C.iCveLaboratorio =   "
					+ vTOXReactivo.getICveLaboratorio();
			cWhere = cWhere + " and  C.iCveReactivo = "
					+ vTOXReactivo.getICveReactivo();
			cWhere = cWhere + " and C.lCuantCual = 1 ";

			vcTOXCtrolCalibra = dTOXCtrolCalibra.FindByAll(cWhere, "");

			char Cel = 'C';
			String Celda = "";
			int iSRenglon = 21;

			if (vcTOXCtrolCalibra.size() > 0) {

				// xl.comDespliega("C", 12, "" +
				// vTOXCtrolCalibra.getCDscSustancia());

				for (int i = 0; i < vcTOXCtrolCalibra.size(); i++) {
					Celda = Celda.valueOf(Cel);
					vTOXCtrolCalibra = (TVTOXCtrolCalibra) vcTOXCtrolCalibra
							.get(i);

					xl.comDespliega("B", iSRenglon + 1, "LOTE:");
					xl.comDespliega("C", iSRenglon + 1, "" + (i + 1));
					xl.comDespliega("B", iSRenglon + 2,
							"FECHA DE PREPARACI�N :");
					xl.comDespliega("C", iSRenglon + 2,
							"" + vTOXCtrolCalibra.getDtPreparacion());
					xl.comDespliega("B", iSRenglon + 3, "PREPARO:");
					xl.comDespliega("C", iSRenglon + 3,
							"" + vTOXCtrolCalibra.getCDscUsuPrepara());
					xl.comDespliega("B", iSRenglon + 4, "FECHA DE VALORACION:");
					xl.comDespliega("B", iSRenglon + 5, "HP:");
					xl.comDespliega("B", iSRenglon + 6, "AUTORIZO:");
					xl.comDespliega("C", iSRenglon + 6,
							"" + vTOXCtrolCalibra.getCDscUsuAutoriza());
					xl.comDespliega("B", iSRenglon + 7, "FECHA:");
					xl.comDespliega("C", iSRenglon + 7,
							"" + vTOXCtrolCalibra.getDtPreparacion());
					xl.comDespliega("B", iSRenglon + 8,
							"VOLUMEN UTILIZADO DE SP:");
					xl.comDespliega("B", iSRenglon + 9, "NO. DE LOTES.:");
					xl.comDespliega("C", iSRenglon + 9, "");

					xl.comDespliega("D", iSRenglon + 1, "CLAVE CONTROL:");
					xl.comDespliega("E", iSRenglon + 1,
							"" + vTOXCtrolCalibra.getCLote());
					xl.comDespliega("D", iSRenglon + 2, "VOL�MEN :");
					xl.comDespliega("E", iSRenglon + 2,
							"" + vTOXCtrolCalibra.getDVolumen());
					xl.comDespliega("D", iSRenglon + 3,
							"CONC.TE�RICA (ng/ml):");
					xl.comDespliega("E", iSRenglon + 3,
							"" + vTOXCtrolCalibra.getDConcentracion());
					xl.comDespliega("D", iSRenglon + 4,
							"CONC.CUANTIFICADA (ng/ml):");
					xl.comDespliega("E", iSRenglon + 4,
							"" + vTOXCtrolCalibra.getDConcentracion());
					xl.comDespliega("D", iSRenglon + 5, "OBSERVACIONES:");
					xl.comAlineaRango("D", iSRenglon + 5, "D", iSRenglon + 9,
							xl.getAT_COMBINA_IZQUIERDA());
					xl.comDespliega("E", iSRenglon + 5,
							"" + vTOXCtrolCalibra.getCObservacion());
					xl.comAlineaRango("E", iSRenglon + 5, "E", iSRenglon + 9,
							xl.getAT_COMBINA_IZQUIERDA());

					xl.comBordeTotal("B", iSRenglon + 1, "E", iSRenglon + 9, 1);

					iSRenglon = iSRenglon + 11;

					// Cel = (char)(Cel + 1);

				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		buffer = xl
				.creaActiveX("pg070306011C", cNomArchivo, false, false, true);
		return buffer;
	}

	/**
	 * Metodo GenRepI
	 */

	public StringBuffer GenRepI(Object obj) {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas();
		String cNomArchivo = new String("pg070306011I");
		int iRenglon = 0;
		int iFilasxPag = 65;
		int iPag = 0;
		int iPagAnt = 0;
		int iRenIni = 0;
		int iRenFin = 0;
		int iRenxPag = 4;
		int iCvePersonal = 0;
		int iCveUniMed = 0;
		int iCveProceso = 0;
		String cPersonal = "";
		String cCveUniMed = "";
		String cCveProceso = "";
		String pName = "";
		String pEmpresa = "";
		String Doctor = "";
		StringBuffer buffer = new StringBuffer();
		java.sql.Date dtConcluido;
		String dFinalExamen = "";
		int lDictamen = 1;
		TVTOXReactivo vTOXReactivo = new TVTOXReactivo();
		TDTOXReactivo dTOXReactivo = new TDTOXReactivo();
		Vector vcTOXReactivo = new Vector();
		TFechas dtFecha = new TFechas("07");

		try {
			// vcTOXReactivo = (Vector) obj;
			// vTOXReactivo()

			vTOXReactivo = (TVTOXReactivo) obj;
			java.sql.Date dtTemp;
			String cFecha = "";

			// Encabezado

			vcTOXReactivo = dTOXReactivo.findByWhere(
					" and R.iCveLaboratorio = "
							+ vTOXReactivo.getICveLaboratorio()
							+ " and R.iCveReactivo = "
							+ vTOXReactivo.getICveReactivo(), "");

			if (vcTOXReactivo.size() > 0) {
				vTOXReactivo = (TVTOXReactivo) vcTOXReactivo.get(0);
			}

			String Situacion = "";

			xl.comDespliega("B", 11, vTOXReactivo.getCDscReactivo());
			xl.comDespliega("C", 12, vTOXReactivo.getCDscReactivo());
			xl.comDespliega("C", 13, vTOXReactivo.getICodigo());
			xl.comDespliega("C", 14, "" + vTOXReactivo.getDtPreparacion());
			xl.comDespliega("C", 15, "" + vTOXReactivo.getDtCaducidad());
			xl.comDespliega("C", 16, "" + vTOXReactivo.getCNomCompletoPrepara());
			xl.comDespliega("C", 19, "" + vTOXReactivo.getDVolumen());
			xl.comDespliega("C", 20,
					"" + vTOXReactivo.getCNomCompletoAutoriza());
			xl.comDespliega("C", 21, "" + vTOXReactivo.getDtAutoriza());
			if (vTOXReactivo.getLBaja() == 1) {
				Situacion = "BAJA";
			}
			if (vTOXReactivo.getLAgotado() == 1) {
				Situacion = "AGOTADO";
			}
			if (vTOXReactivo.getLAgotado() == 0 && vTOXReactivo.getLBaja() == 0) {
				Situacion = "NORMAL";

			}

			xl.comDespliega("C", 22, Situacion);

			xl.comDespliega("E", 18, "" + vTOXReactivo.getCDscMarcaSust());
			xl.comDespliega("D", 20, "" + vTOXReactivo.getCObservacion());

			// Detalle
			TDTOXCtrolCalibra dTOXCtrolCalibra = new TDTOXCtrolCalibra();
			Vector vcTOXCtrolCalibra = new Vector();
			TVTOXCtrolCalibra vTOXCtrolCalibra = new TVTOXCtrolCalibra();

			String cWhere = "";
			cWhere = " where C.iCveLaboratorio =  "
					+ vTOXReactivo.getICveLaboratorio();
			cWhere = cWhere + " and  C.iCveReactivo = "
					+ vTOXReactivo.getICveReactivo();
			cWhere = cWhere + " and C.lCual = 1 ";

			vcTOXCtrolCalibra = dTOXCtrolCalibra.FindByAll(cWhere, "");

			char Cel = 'C';
			String Celda = "";

			if (vcTOXCtrolCalibra.size() > 0) {

				// xl.comDespliega("C", 12, "" +
				// vTOXCtrolCalibra.getCDscSustancia());

				for (int i = 0; i < vcTOXCtrolCalibra.size(); i++) {
					Celda = Celda.valueOf(Cel);
					vTOXCtrolCalibra = (TVTOXCtrolCalibra) vcTOXCtrolCalibra
							.get(i);

					xl.comDespliega(Celda, 25, "" + (i + 1));
					xl.comDespliega(Celda, 26, "" + vTOXCtrolCalibra.getCLote());
					xl.comDespliega(Celda, 27,
							"" + vTOXCtrolCalibra.getDtPreparacion());
					xl.comDespliega(Celda, 28,
							"" + vTOXCtrolCalibra.getDtCaducidad());
					xl.comDespliega(Celda, 29,
							"" + vTOXCtrolCalibra.getCDscUsuPrepara());
					xl.comDespliega(Celda, 30,
							"" + vTOXCtrolCalibra.getDVolumen());
					xl.comDespliega(Celda, 32,
							"" + vTOXCtrolCalibra.getDConcentracion());
					xl.comDespliega(Celda, 35,
							"" + vTOXCtrolCalibra.getCDscUsuAutoriza());
					xl.comDespliega(Celda, 36,
							"" + vTOXCtrolCalibra.getDtAutoriza());

					Cel = (char) (Cel + 1);

				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		buffer = xl
				.creaActiveX("pg070306011I", cNomArchivo, false, false, true);
		return buffer;
	}

	/**
	 * Metodo FillPK
	 */
	public void fillPK() {
		if ("Actual".compareToIgnoreCase(this.getAccionOriginal()) == 0) {
			mPk.add(request.getParameter("hdCampoClave1"));
			mPk.add(request.getParameter("hdCampoClave2"));
		}
		if ("Modificar".compareToIgnoreCase(this.getAccionOriginal()) == 0) {
			mPk.add(request.getParameter("hdCampoClave1"));
			mPk.add(request.getParameter("hdCampoClave2"));
		}
		if ("Cancelar".compareToIgnoreCase(this.getAccionOriginal()) == 0) {
			mPk.add(request.getParameter("hdCampoClave1"));
			mPk.add(request.getParameter("hdCampoClave2"));
		}
		if ("Guardar".compareToIgnoreCase(this.getAccionOriginal()) == 0
				|| "GuardarA".compareToIgnoreCase(this.getAccionOriginal()) == 0) {
			mPk.add(this.cActual2);
			mPk.add(this.cActual);
		}
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();

		TVTOXReactivo vTOXReactivo = new TVTOXReactivo();
		try {
			// campo
			cCampo = "" + request.getParameter("iCveLaboratorio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setICveLaboratorio(iCampo);

			// campo
			cCampo = "" + request.getParameter("iCveReactivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = -1;
			}
			vTOXReactivo.setICveReactivo(iCampo);

			// campo
			cCampo = "" + request.getParameter("iCodigo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXReactivo.setICodigo(cCampo);

			// campo
			cCampo = "" + request.getParameter("iCveTpoReact");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setICveTpoReact(iCampo);

			// campo
			cCampo = "" + request.getParameter("cDscReactivo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXReactivo.setCDscReactivo(cCampo);

			// campo
			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXReactivo.setCDscBreve(cCampo);

			// campo
			cCampo = "" + request.getParameter("iCveMarcaSust");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setICveMarcaSust(iCampo);

			// campo
			cCampo = "" + request.getParameter("cDscTpoReact");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXReactivo.setCDscTpoReact(cCampo);

			// campo
			cCampo = "" + request.getParameter("cDscMarcaSust");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXReactivo.setCDscMarcaSust(cCampo);

			// campo
			cCampo = "" + request.getParameter("lCuantCual");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setLCuantCual(iCampo);

			// campo
			cCampo = "" + request.getParameter("lCual");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setLCual(iCampo);

			// campo
			cCampo = "" + request.getParameter("dCorteNeg");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vTOXReactivo.setDCorteNeg(fCampo);

			// campo
			cCampo = "" + request.getParameter("dCortePost");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vTOXReactivo.setDCortePost(fCampo);

			// campo
			cCampo = "" + request.getParameter("dConcentTeor");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vTOXReactivo.setDConcentTeor(fCampo);

			// campo
			cCampo = "" + request.getParameter("iCveSustancia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setICveSustancia(iCampo);

			// campo
			cCampo = "" + request.getParameter("cNumLote");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXReactivo.setCNumLote(cCampo);

			// campo
			cCampo = "" + request.getParameter("cNumCatalogo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXReactivo.setCNumCatalogo(cCampo);

			// campo
			cCampo = "" + request.getParameter("dConcentracion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vTOXReactivo.setDConcentracion(fCampo);

			// campo
			cCampo = "" + request.getParameter("dtCaducAmp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				if (cCampo.equals("")) {
					dtCampo = null; // si la fecha es un espacio
				} else {
					dtCampo = tfCampo.getDateSQL(cCampo);
				}
			} else {
				dtCampo = null;
			}
			vTOXReactivo.setDtCaducAmp(dtCampo);

			// campo
			cCampo = "" + request.getParameter("cProveedor");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXReactivo.setCProveedor(cCampo);

			// campo
			cCampo = "" + request.getParameter("iCveEquipoE1");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setICveEquipoE1(iCampo);

			// campo
			cCampo = "" + request.getParameter("dConcentExper1");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}

			vTOXReactivo.setDConcentExper1(fCampo);

			// campo
			cCampo = "" + request.getParameter("dtValoracion1");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				if (cCampo.equals(" ")) {
					dtCampo = null; // si la fecha es un espacio
				} else {
					dtCampo = tfCampo.getDateSQL(cCampo);
				}
			} else {
				dtCampo = null;
			}
			vTOXReactivo.setDtValoracion1(dtCampo);

			// campo
			cCampo = "" + request.getParameter("iCveEquipoE2");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setICveEquipoE2(iCampo);

			// campo
			cCampo = "" + request.getParameter("dConcentExper2");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vTOXReactivo.setDConcentExper2(fCampo);

			// campo
			cCampo = "" + request.getParameter("dtValoracion2");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				if (cCampo.equals(" ")) {
					dtCampo = null; // si la fecha es un espacio
				} else {
					dtCampo = tfCampo.getDateSQL(cCampo);
				}
			} else {
				dtCampo = null;
			}
			vTOXReactivo.setDtValoracion2(dtCampo);

			// campo
			cCampo = "" + request.getParameter("cPreparadoDe");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXReactivo.setCPreparadoDe(cCampo);

			// campo
			cCampo = "" + request.getParameter("dtPreparacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				if (cCampo.equals(" ")) {
					dtCampo = null; // si la fecha es un espacio
				} else {
					dtCampo = tfCampo.getDateSQL(cCampo);
				}
			} else {
				dtCampo = null;
			}
			vTOXReactivo.setDtPreparacion(dtCampo);

			// campo
			cCampo = "" + request.getParameter("dVolumen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vTOXReactivo.setDVolumen(fCampo);

			// campo
			cCampo = "" + request.getParameter("iViales");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setIViales(iCampo);

			// campo
			cCampo = "" + request.getParameter("iCveUsuPrepara");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setICveUsuPrepara(iCampo);

			// campo
			cCampo = "" + request.getParameter("dtCaducidad");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				if (cCampo.equals(" ")) {
					dtCampo = null; // si la fecha es un espacio
				} else {
					dtCampo = tfCampo.getDateSQL(cCampo);
				}
			} else {
				dtCampo = null;
			}
			vTOXReactivo.setDtCaducidad(dtCampo);

			// campo
			cCampo = "" + request.getParameter("dtAutoriza");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				if (cCampo.equals(" ")) {
					dtCampo = null; // si la fecha es un espacio
				} else {
					dtCampo = tfCampo.getDateSQL(cCampo);
				}
			} else {
				dtCampo = null;
			}
			vTOXReactivo.setDtAutoriza(dtCampo);

			// campo
			cCampo = "" + request.getParameter("iCveUsuAutoriza");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setICveUsuAutoriza(iCampo);

			// campo
			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setIAnio(iCampo);

			// campo
			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXReactivo.setCObservacion(cCampo);

			// campo
			cCampo = "" + request.getParameter("lAgotado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXReactivo.setLAgotado(iCampo);

			// campo
			if (iCampo == 1) { // si el campo anterior, lAgotado, est� marcado
				cCampo = "" + request.getParameter("dtAgotado");
				if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
					if (cCampo.equals(" ")) {
						dtCampo = null; // si la fecha es un espacio
					} else {
						dtCampo = tfCampo.getDateSQL(cCampo);
					}
				} else {
					dtCampo = null;
				}
				vTOXReactivo.setDtAgotado(dtCampo);
			} else {
				vTOXReactivo.setDtAgotado(null);
			}
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXReactivo;
	}

	/**
	 * Propiedad que almacena el n�mero de reactivo obtenido al momento de
	 * insertar un registro nuevo
	 */
	private int cveReactivo = -1;

	/**
	 * Metodo que devuelve el n�mero de reactivo obtenido
	 * 
	 * @return cveReactivo el n�mero de reactivo
	 */
	public String getCClave() {
		return cClave;
	}
}
