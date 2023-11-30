package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Clase para el listado de TOXCortexSust
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco antonio Hern�ndez Garc�a
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301220.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301220.jsp.png'>
 */
public class pg070301311CFG extends CFGCatBase2 {
	public pg070301311CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		// TDTOXCorteXSust dTOXCorteXSust = new TDTOXCorteXSust();
		TDTOXANPRECC dTOXANPRECC = new TDTOXANPRECC();
		try {
			// cClave = (String) dTOXCorteXSust.insert(null, this.getInputs());
			cClave = (String) dTOXANPRECC.insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	}

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDTOXCorteXSust dTOXCorteXSust = new TDTOXCorteXSust();
		try {
			cClave = (String) dTOXCorteXSust.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	}

	/**
	 * Metodo BorrarB
	 */
	public void BorrarB() {
		TDTOXCorteXSust dTOXCorteXSust = new TDTOXCorteXSust();
		try {
			// cClave = (String) dTOXCorteXSust.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.BorrarB();
		}
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		// TDTOXCorteXSust dTOXCorteXSust = new TDTOXCorteXSust();
		TDTOXANPRECC dTOXANPRECC = new TDTOXANPRECC();
		/*
		 * if (this.getLPagina("pg070301220.jsp")) { cPaginas =
		 * "pg070301220.jsp|"; } try { if (cCondicion != null &&
		 * cCondicion.compareTo("") != 0) { cCondicion = " WHERE " + cCondicion;
		 * } if (request.getParameter("iCveSustancia") != null &&
		 * request.getParameter("iCveSustancia").compareTo("") != 0) { if
		 * (cCondicion.compareTo("") != 0 &&
		 * request.getParameter("iCveSustancia") != null) { cCondicion +=
		 * " AND TOXCorteXSust.iCveSustancia = " +
		 * request.getParameter("iCveSustancia"); } else if
		 * (request.getParameter("iCveSustancia") != null) { cCondicion +=
		 * " WHERE TOXCorteXSust.iCveSustancia = " +
		 * request.getParameter("iCveSustancia"); } }
		 */
		if (this.getLPagina("pg070301310.jsp")) {
			cPaginas = "pg070301310.jsp|";
		}
		try {
			if (cCondicion != null && cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
			}
			if (request.getParameter("iCveSustancia") != null
					&& request.getParameter("iCveSustancia").compareTo("") != 0) {
				if (cCondicion.compareTo("") != 0
						&& request.getParameter("iCveSustancia") != null) {
					cCondicion += " AND TOXANPRECC.iCveSustancia = "
							+ request.getParameter("iCveSustancia");
				} else if (request.getParameter("iCveSustancia") != null) {
					cCondicion += " WHERE TOXANPRECC.iCveSustancia = "
							+ request.getParameter("iCveSustancia");
				}
			}

			// vDespliega = dTOXCorteXSust.FindByAll(cCondicion);
			vDespliega = dTOXANPRECC.FindByAll(cCondicion);
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		mPk.add(request.getParameter("iCveSustancia"));

		mPk.add(cActual);
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		double dCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVTOXCorteXSust vTOXCorteXSust = new TVTOXCorteXSust();
		try {
			cCampo = "" + request.getParameter("iCveSustancia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXCorteXSust.setiCveSustancia(new Integer(iCampo));

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXCorteXSust.setiCveCorte(new Integer(iCampo));

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				cCampo = "" + request.getParameter("lActivoM");
				if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
					iCampo = Integer.parseInt(cCampo, 10);
				} else {
					iCampo = 0;
				}
			}
			vTOXCorteXSust.setlActivo(new Integer(iCampo));

			cCampo = "" + request.getParameter("lCuantCual");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXCorteXSust.setLCuantCual(iCampo);

			cCampo = "" + request.getParameter("dtInicio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vTOXCorteXSust.setdtInicio(dtCampo);

			cCampo = "" + request.getParameter("dtFin");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vTOXCorteXSust.setdtFin(dtCampo);

			cCampo = "" + request.getParameter("iCveUsuAutoriza");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXCorteXSust.setiCveUsuAutoriza(new Integer(iCampo));

			cCampo = "" + request.getParameter("dCorte");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				cCampo = "" + request.getParameter("dCorte1");
				if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
					fCampo = Float.parseFloat(cCampo);
				} else {
					cCampo = "" + request.getParameter("dCorte2");
					if (cCampo.compareTo("null") != 0
							&& cCampo.compareTo("") != 0) {
						fCampo = Float.parseFloat(cCampo);
					} else {

						fCampo = 0;
					}
				}
			}

			vTOXCorteXSust.setdCorte(new Double(fCampo));

			cCampo = "" + request.getParameter("dCorteNeg");

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				cCampo = "" + request.getParameter("dCorteNeg1");
				if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
					fCampo = Float.parseFloat(cCampo);
				} else {
					cCampo = "" + request.getParameter("dCorteNeg2");
					if (cCampo.compareTo("null") != 0
							&& cCampo.compareTo("") != 0) {
						fCampo = Float.parseFloat(cCampo);
					} else {

						fCampo = 0;
					}
				}

			}
			vTOXCorteXSust.setdCorteNeg(new Double(fCampo));

			cCampo = "" + request.getParameter("dCortePost");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vTOXCorteXSust.setdCortePost(new Double(fCampo));

			cCampo = "" + request.getParameter("dMargenError");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vTOXCorteXSust.setdMargenError(new Double(fCampo));

			cCampo = "" + request.getParameter("cControles");
			if (cCampo != null && cCampo.compareTo("null") != 0
					&& cCampo.compareTo("") != 0) {
				vTOXCorteXSust.setCControles(cCampo);
			} else {
				vTOXCorteXSust.setCControles("");

			}
			dCampo = 0;
			cCampo = "" + request.getParameter("dMargConcCal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dCampo = Double.parseDouble(cCampo);
			}
			vTOXCorteXSust.setDMargConcCal(dCampo);
			dCampo = 0;
			cCampo = "" + request.getParameter("dMargTmpRet");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dCampo = Double.parseDouble(cCampo);
			}
			vTOXCorteXSust.setDMargTmpRet(dCampo);
			dCampo = 0;
			cCampo = "" + request.getParameter("dMargRelacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dCampo = Double.parseDouble(cCampo);
			}
			vTOXCorteXSust.setDMargRelacion(dCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXCorteXSust;
	}
}
