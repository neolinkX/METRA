package gob.sct.medprev;

import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Litado para captura de Parametros de Validacion
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070306081CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070306081CFG.png'>
 */
public class pg070306081CFG extends CFGListBase2 {

	private StringBuffer activeX = new StringBuffer("");

	public pg070306081CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		cPaginas = "pg070306080.jsp|"; // Aqui va la pagina de origen

		// TDTOXAbsorMuestra dTOXAbsorvmuestra = new TDTOXAbsorMuestra();
		// String xAnio = "";
		// String xCve = "";
		// xAnio = request.getParameter("iAnio");
		// xCve = request.getParameter("iCveAbsorvancia");
		// if(xCve == null || xCve.equalsIgnoreCase("null")){
		// xCve = "";
		// }

		/*
		 * if(cParam != null || !cParam.equalsIgnoreCase("null") ||
		 * !cParam.equalsIgnoreCase("")){ xCve = cParam; }
		 */
		try {
			// vDespliega = dTOXAbsorvmuestra.FindByAll(xAnio,xCve);
			// ## AQUI METER EL CODIGO PARA EXCEL ##
			if (request.getParameter("hdReporte") != null) {
				// ***************************
				// ********** VPAT1 **********
				// ***************************
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"VPAT1") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("VPAT1", "VPAT1",
								false, false, true));
					}
					// ***************************
					// ********** VPAT2 **********
					// ***************************
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("VPAT2") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("VPAT2",
								"VPAT2-out", false, false, true));
					}
					// ***************************
					// ********** VPAT3 **********
					// ***************************
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("VPAT3") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						// this.activeX.append(Rep.creaActiveX("VPAT3", "VPAT3",
						// false, false, true));
						this.activeX.append(Rep.creaActiveX("VPAT3", "VPAT3",
								false, false, true));
					}
					// ***************************
					// ********** VPAT4 **********
					// ***************************
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("VPAT4") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						// this.activeX.append(Rep.creaActiveX("VPAT4", "VPAT4",
						// false, false, true));
						this.activeX.append(Rep.creaActiveX("VPAT4", "VPAT4",
								false, false, true));
					}
					// ***************************
					// ********** VPAT5 **********
					// ***************************
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("VPAT5") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("VPAT5", "VPAT5",
								false, false, true));
					}
					// ***************************
					// ********** VPAT6 **********
					// ***************************
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("VPAT6") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("VPAT6", "VPAT6",
								false, false, true));
					}
					// ***************************
					// ********** VPAT7 **********
					// ***************************
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("VPAT7") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("VPAT7", "VPAT7",
								false, false, true));
					}
					// ***************************
					// ********** VPAF1 **********
					// ***************************
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("VPAF1") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("VPAF1", "VPAF1",
								false, false, true));
					}
					// ***************************
					// ********** VPAF2 **********
					// ***************************
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("VPAF2") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("VPAF2", "VPAF2",
								false, false, true));
					}
					// ***************************
					// ********** VPAF3 **********
					// ***************************
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("VPAF3") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("VPAF3", "VPAF3",
								false, false, true));
					}
					// ***************************
					// ********** VPAF4 **********
					// ***************************
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("VPAF4") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("VPAF4", "VPAF4",
								false, false, true));
					}
				}
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public void Guardar() {
		TDTOXAbsorMuestra dTOXAbsorMuestra = new TDTOXAbsorMuestra();
		Vector vTOXListaSust = new Vector();
		TVTOXSustancia vTOXMuestraItera = new TVTOXSustancia();
		DAOTOXSustancia dSustancia = new DAOTOXSustancia();
		try {
			vTOXListaSust = dSustancia.FindByAll();

			String cAnio = "";
			String cSinEspacio = "";
			String cClave2 = "";
			String cIteraPt1 = "";
			String cICveMaAbs = "";
			String cICveSubst = "";
			String cConcentra = "";
			String cdPorcenta = "";
			String cCarrusels = "";
			String cPosicions = "";
			String cSustancia = "";
			String cICveMaAbsR = "";
			String cICveSubstR = "";
			String cConcentraR = "";
			String cdPorcentaR = "";
			String cCarruselsR = "";
			String cPosicionsR = "";

			float iConcentra = 0;
			float iPorcentaj = 0;
			float iResult = 0;
			String cResult = "";

			int iIteraPt1 = 0;
			cIteraPt1 = request.getParameter("hdTotRows");
			iIteraPt1 = Integer.parseInt(cIteraPt1);
			cAnio = request.getParameter("iAnio");

			/**
			 * Insert a la Tabla TOXAbsorvancia
			 */
			TDTOXAbsorvancia dTOXAbsorvancia = new TDTOXAbsorvancia();
			cClave = (String) dTOXAbsorvancia.insert(null, this.getInputs());
			/**
			 * Insert a la Tabla TOXAbsorMuestra
			 */
			for (int i = 0; i < iIteraPt1; i++) {
				cICveMaAbs = "iCveMaAbsorvancia" + i;
				cICveSubst = "iCveSustancia" + i;
				cConcentra = "dConcentracion" + i;
				cdPorcenta = "dPorcentaje" + i;
				cCarrusels = "iCarrusel" + i;
				cPosicions = "iPosicion" + i;
				cICveMaAbsR = request.getParameter(cICveMaAbs);
				cICveSubstR = request.getParameter(cICveSubst);
				cConcentraR = request.getParameter(cConcentra);
				cdPorcentaR = request.getParameter(cdPorcenta);
				cCarruselsR = request.getParameter(cCarrusels);
				cPosicionsR = request.getParameter(cPosicions);

				iConcentra = Float.parseFloat(cConcentraR);
				iPorcentaj = Float.parseFloat(cdPorcentaR);

				cClave2 = (String) dTOXAbsorMuestra.insert(null, cAnio, cClave,
						cICveMaAbsR, cICveSubstR, iConcentra, iPorcentaj,
						cCarruselsR, cPosicionsR);

				/**
				 * Inserta sobre la tabla TOXAbsorvResult
				 */

				for (int x = 0; x < vTOXListaSust.size(); x++) {
					vTOXMuestraItera = (TVTOXSustancia) vTOXListaSust.get(x);
					cSinEspacio = vTOXMuestraItera.getcDscSustancia().replace(
							' ', '_');
					cSustancia = cSinEspacio + i;
					cResult = request.getParameter(cSustancia);
					iResult = Float.parseFloat(cResult);
					if (Integer.parseInt(cICveSubstR) == vTOXMuestraItera
							.getiCveSustancia()) {
						dTOXAbsorMuestra.insertRes(null, cAnio, cClave,
								cICveMaAbsR, cICveSubstR, iResult);
					}
				}
			}
			this.fillVector();

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVTOXAbsorvancia vTOXAbsorvancia = new TVTOXAbsorvancia();
		try {
			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXAbsorvancia.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCveAbsorvancia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXAbsorvancia.setICveAbsorvancia(iCampo);

			cCampo = "" + request.getParameter("iCveEquipo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXAbsorvancia.setICveEquipo(iCampo);

			cCampo = "" + request.getParameter("dtEstudio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vTOXAbsorvancia.setDtEstudio(dtCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXAbsorvancia.setCObservacion(cCampo);

			cCampo = "" + request.getParameter("iCveUsuResp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXAbsorvancia.setICveUsuResp(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXAbsorvancia;
	}

	public String getActiveX() {
		return this.activeX.toString();
	}

	public String getAccion() {
		return this.cAccionOriginal;
	}
}
