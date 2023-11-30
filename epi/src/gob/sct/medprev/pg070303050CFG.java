package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;
import com.micper.excepciones.*;

/**
 * * Clase de configuracion para Muestras
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301110CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301110CFG.png'>
 */
public class pg070303050CFG extends CFGListBase2 {
	public pg070303050CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXCalibCualita dTOXCalibCualita = new TDTOXCalibCualita();
		cPaginas = "comingsoon.jsp|";
		try {
			String aWhereDesc = "";
			if (aWhereDesc.length() != 0) {
				vDespliega = dTOXCalibCualita.FindByAll("");
			}

			String lAction = request.getParameter("hdBoton");

			String cAnio = "";
			String cLab = "";
			String cExam = "";
			String cLote = "";

			String cSust = "";
			String cRSust = "";
			String cReac = "";
			String cRReac = "";
			String cCali = "";
			String cRCali = "";
			String cNega = "";
			float cRNega = 0;
			String cCort = "";
			float cRCort = 0;
			String cPost = "";
			float cRPost = 0;
			String cAcep = "";
			String cRAcep = "";
			String cAcco = "";
			String cRAcco = "";
			String cObse = "";
			String cRObse = "";
			String lActivoA = "";
			String lActivoB = "";
			String cCveCalibCualita = "";
			int iCveCalibCualita = 0;

			float iCalc1 = 0;
			float iCalc2 = 0;
			int iActivoA = 0;
			int iActivoB = 0;
			int result = 0;

			String cParam = "";
			String cWhere = "";

			String uId = "";

			String xWhere = "";
			String cRow = "";
			int iRow = 0;
			String tTipoM;

			if (lAction.equalsIgnoreCase("Guardar")) {
				DAOTOXSustancia dSustancia = new DAOTOXSustancia();
				// TDTOXCalibCualita dTOXCalibCualita = new TDTOXCalibCualita();
				vDespliega = dSustancia.FindByAll();
				cAnio = request.getParameter("iAnio");
				cLab = request.getParameter("iCveUniMed");
				cLote = request.getParameter("iCveLoteCualita");
				cExam = request.getParameter("iCveExamCualita");
				uId = request.getParameter("hdUserId");
				cRow = request.getParameter("TotRows");
				iRow = Integer.parseInt(cRow);

				if (!vDespliega.isEmpty()) {
					for (int i = 0; i < iRow; i++) {
						Vector vTOXReactivo = new Vector();

						// TVTOXSustancia vTOXSustanciaItera = new
						// TVTOXSustancia();
						// vTOXSustanciaItera = (TVTOXSustancia)
						// vDespliega.get(i);
						lActivoA = "lActualizarA" + i; // Actualiza e inserta el
														// registro
						lActivoB = "lActualizarB" + i; // Inserta un registro
														// nuevo
						cCveCalibCualita = "iCveCalibCualita" + i;

						lActivoA = "" + request.getParameter(lActivoA);

						if (lActivoA.compareTo("null") != 0
								&& lActivoA.compareTo("") != 0) {
							iActivoA = Integer.parseInt(lActivoA, 10);
						} else {
							iActivoA = 0;
						}

						lActivoB = "" + request.getParameter(lActivoB);

						if (lActivoB.compareTo("null") != 0
								&& lActivoB.compareTo("") != 0) {
							iActivoB = Integer.parseInt(lActivoB, 10);
						} else {
							iActivoB = 0;
						}

						cCveCalibCualita = ""
								+ request.getParameter(cCveCalibCualita);

						if (cCveCalibCualita.compareTo("null") != 0
								&& cCveCalibCualita.compareTo("") != 0) {
							iCveCalibCualita = Integer.parseInt(
									cCveCalibCualita, 10);
						} else {
							iCveCalibCualita = 0;
						}

						cSust = "iCveSustancia" + i; // vTOXSustanciaItera.getiCveSustancia();
						cReac = "iCveReactivo" + i; // vTOXSustanciaItera.getiCveSustancia();
						cCali = "iCveCtrolCalibra" + i; // vTOXSustanciaItera.getiCveSustancia();
						cNega = "dCorteNeg" + i; // vTOXSustanciaItera.getiCveSustancia();
						cCort = "dCorte" + i; // vTOXSustanciaItera.getiCveSustancia();
						cPost = "dCortePost" + i; // vTOXSustanciaItera.getiCveSustancia();
						cAcco = "iCveAcCorrectiva" + i; // vTOXSustanciaItera.getiCveSustancia();
						cObse = "cObservacion" + i; // vTOXSustanciaItera.getiCveSustancia();

						cSust = request.getParameter(cSust);

						cReac = request.getParameter(cReac);

						cCali = request.getParameter(cCali);

						cNega = request.getParameter(cNega);

						cCort = request.getParameter(cCort);

						cPost = request.getParameter(cPost);

						cAcco = request.getParameter(cAcco);

						cObse = request.getParameter(cObse);

						if (cNega.length() == 0 || cCort.length() == 0
								|| cPost.length() == 0) {

						} else {
							xWhere = " where iCveLaboratorio = " + cLab
									+ " And iCveReactivo = " + cReac;
							TVTOXReactivo vTOXReactivoItera = new TVTOXReactivo();
							vTOXReactivo = dTOXCalibCualita.findCteReac(xWhere);
							vTOXReactivoItera = (TVTOXReactivo) vTOXReactivo
									.get(0);
							cRNega = Float.parseFloat(cNega);
							cRCort = Float.parseFloat(cCort);
							cRPost = Float.parseFloat(cPost);

							iCalc1 = cRCort - cRNega; // vTOXReactivoItera.getDCorteNeg();
							iCalc2 = cRPost - cRCort; // vTOXReactivoItera.getDCortePost()
														// - cRCort;

							// Tipo de Validaci�n
							// CASO 1: POR RANGOS. DENTRO DE LOS CORTES.
							if (vTOXReactivoItera.getDCortePost() > 0) {
								if (iCalc1 >= vTOXReactivoItera.getDCorteNeg()
										&& iCalc2 >= vTOXReactivoItera
												.getDCortePost())
									result = 1;
								else
									result = 0;
							}
							// CASO 2: SUPERIOR A UN CORTE
							if (vTOXReactivoItera.getDCortePost() == 0) {
								if (cRCort >= cRNega)
									result = 1;
								else
									result = 0;
							}
							if (iActivoA == 1) {
								// update
								dTOXCalibCualita.update(null, cAnio, cLab,
										cExam, cLote, cSust, iCveCalibCualita,
										iActivoA);
								dTOXCalibCualita.insert(null, cAnio, cLab,
										cLote, cExam, cSust, cReac, cCali,
										cRNega, cRCort, cRPost, result, uId,
										cAcco, cObse);
							}

							if (iActivoB == 1) {
								dTOXCalibCualita.insert(null, cAnio, cLab,
										cLote, cExam, cSust, cReac, cCali,
										cRNega, cRCort, cRPost, result, uId,
										cAcco, cObse);
								dTOXCalibCualita.insertTOXCualtSust(null,
										cAnio, cLab, cLote, cExam, cSust);

							}
						}
					} // For
				} else {

				}
				// vDespliega = dTOXMuestra.FindByAll();
				this.fillVectorA(this.getInputs());
			}
			if (lAction.equalsIgnoreCase("BtnBuscar")) {
				this.fillVectorA(this.getInputs());
			}
			if (lAction.equalsIgnoreCase("New_Cal")) {
				this.fillVectorA(this.getInputs());
			}

		} catch (Exception ex) {
			error("FillVector", ex);

		}
	}

	public void fillVectorA(Object obj) {
		/**
		 * Este Fill solo es utilizado en la pagina de calibraciones
		 */
		TDTOXCalibCualita dTOXCalibC = new TDTOXCalibCualita();
		TVTOXCalibCualita vTOXCalibC = (TVTOXCalibCualita) obj;
		cPaginas = "comingsoon.jsp|"; // Aqui va la pagina de origen
		String aWhereDesc = "";

		if (vTOXCalibC.getIAnio() != 0) {
			aWhereDesc += " Where a.iAnio = " + vTOXCalibC.getIAnio();
		}
		if (vTOXCalibC.getICveLaboratorio() != 0) {
			aWhereDesc += " And a.iCveLaboratorio = "
					+ vTOXCalibC.getICveLaboratorio();
		}
		if (vTOXCalibC.getICveLoteCualita() != 0) {
			aWhereDesc += " And a.iCveLoteCualita = "
					+ vTOXCalibC.getICveLoteCualita();
		}
		if (vTOXCalibC.getICveExamCualita() != 0) {
			aWhereDesc += " And a.iCveExamCualita = "
					+ vTOXCalibC.getICveExamCualita();
		}

		try {

			if (aWhereDesc.length() != 0) {
				vDespliega = dTOXCalibC.FindByAll(aWhereDesc);
			}
		} catch (Exception ex) {
			error("FillVector", ex);

		}
	}

	public Object getInputs() throws CFGException {
		String cCampo;
		String cCampob;
		int iCampo;
		int iCuenta;
		float fCampo;
		java.sql.Date dtCampo;
		TVTOXCalibCualita vTOXCalibCualita = new TVTOXCalibCualita();

		try {

			cCampo = "" + request.getParameter("iAnio");

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXCalibCualita.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCveUniMed");

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXCalibCualita.setICveLaboratorio(iCampo);

			cCampo = "" + request.getParameter("iCveLoteCualita");

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXCalibCualita.setICveLoteCualita(iCampo);

			cCampo = "" + request.getParameter("iCveExamCualita");

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXCalibCualita.setICveExamCualita(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXCalibCualita;
	}

}
