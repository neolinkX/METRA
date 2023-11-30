package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG de ToxValPresuntivo
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Juan Manuel Vazquez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070306070CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070306070CFG.png'>
 */

public class pg070306070CFG extends CFGCatBase2 {

	private int iCveValPres = 0;

	public pg070306070CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070306071.jsp";

	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDTOXValPresuntivo dTOXValPresuntivo = new TDTOXValPresuntivo();
		try {
			cClave = (String) dTOXValPresuntivo.insert(null, this.getInputs());
			iCveValPres = Integer.parseInt(cClave);
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
		TDTOXValPresuntivo dTOXValPresuntivo = new TDTOXValPresuntivo();
		try {
			cClave = (String) dTOXValPresuntivo.update(null, this.getInputs());
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
		TDTOXValPresuntivo dTOXValPresuntivo = new TDTOXValPresuntivo();
		try {

			cClave = (String) dTOXValPresuntivo.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXValPresuntivo dTOXValPresuntivo = new TDTOXValPresuntivo();
		try {
			String cWhere = "";
			String cCond = request.getParameter("hdLCondicion");
			String cAnio = "";
			String cUnMed = "";
			String cValPres = "";
			String cBoton = "";

			cBoton = request.getParameter("hdBoton");

			if (cBoton.compareTo("Nuevo") != 0)
				UpdStatus = "AddOnly";
			if (cBoton.compareTo("Nuevo") == 0)
				UpdStatus = "SaveCancelOnly";

			if (cBoton != null
					&& (cBoton.compareToIgnoreCase("Modificar") == 0 || cBoton
							.compareToIgnoreCase("Cancelar") == 0)) {
				cAnio = request.getParameter("hdCampoClave");
				cUnMed = request.getParameter("hdCampoClave3");
				cValPres = request.getParameter("hdCampoClave4");
			} else {
				cAnio = request.getParameter("iAnio");
				cUnMed = request.getParameter("iCveUniMed");
			}

			if (cCond != null && cCond.compareTo("") != 0) {
				cWhere = cCond;
				if (cAnio != null && cAnio.compareTo("") != 0) {
					cWhere += " and V.iAnio = " + cAnio;
				}
				if (cUnMed != null && cUnMed.compareTo("") != 0) {
					cWhere += " and V.iCveLaboratorio = " + cUnMed;
				}

				if (cValPres != null && cValPres.compareTo("") != 0) {
					cWhere += " and V.iCveValPres = " + cValPres;
				}
			} else {
				if (cAnio != null && cAnio.compareTo("") != 0) {
					cWhere = " V.iAnio = " + cAnio;
					if (cUnMed != null && cUnMed.compareTo("") != 0) {
						cWhere += " and V.iCveLaboratorio = " + cUnMed;
					}
					if (cValPres != null && cValPres.compareTo("") != 0) {
						cWhere += " and V.iCveValPres = " + cValPres;
					}

				} else {
					if (cUnMed != null && cUnMed.compareTo("") != 0) {
						cWhere += " V.iCveLaboratorio = " + cUnMed;
						if (cValPres != null && cValPres.compareTo("") != 0) {
							cWhere += " and V.iCveValPres = " + cValPres;
						} else {
							if (cValPres != null && cValPres.compareTo("") != 0) {
								cWhere += " V.iCveValPres = " + cValPres;
							}
						}

					}
				}

			}

			if (cWhere != null && cWhere.compareTo("") != 0) {
				cWhere = " where " + cWhere;
			} else
				cWhere = "";

			String cOrden = "" + request.getParameter("hdLOrdenar");

			if (cOrden != null && cOrden.compareTo("") != 0) {
				cOrden = request.getParameter("hdLOrdenar");
			} else
				cOrden = " Order By V.iCveValPres";
			if (cWhere != null && cOrden != null)
				vDespliega = dTOXValPresuntivo.FindByAll(cWhere, cOrden);
			else
				vDespliega = dTOXValPresuntivo.FindByAll("", "");
		}

		catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void FillPK() {
		mPk.add(cActual);
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
		TVTOXValPresuntivo vTOXValPresuntivo = new TVTOXValPresuntivo();

		try {
			cCampo = "" + request.getParameter("hdCampoClave"); // iAnio
																// hdCampoClave
			if (cCampo != null && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				cCampo = "" + request.getParameter("iAnio"); // iAnio
																// hdCampoClave
				if (cCampo != null && cCampo.compareTo("") != 0) {
					iCampo = Integer.parseInt(cCampo, 10);
				} else
					iCampo = 0;
			}
			vTOXValPresuntivo.setIAnio(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave3"); // iCveLaboratorio
																	// hdCampoClave3
			if (cCampo != null && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				cCampo = "" + request.getParameter("iCveLaboratorio"); // iCveLaboratorio
																		// hdCampoClave3
				if (cCampo != null && cCampo.compareTo("") != 0) {
					iCampo = Integer.parseInt(cCampo, 10);
				} else
					iCampo = 0;
			}
			vTOXValPresuntivo.setICveLaboratorio(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave4"); // iCveValPres
																	// hdCampoClave4
			if (cCampo != null && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXValPresuntivo.setICveValPres(iCampo);

			cCampo = "" + request.getParameter("iCveCtrolCalibra");
			if (cCampo != null && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXValPresuntivo.setICveCtrolCalibra(iCampo);

			if (request.getParameter("iCveEquipo") != null)
				cCampo = "" + request.getParameter("iCveEquipo");
			if (cCampo != null && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXValPresuntivo.setICveEquipo(iCampo);

			cCampo = "" + request.getParameter("iCveSustancia");
			if (cCampo != null && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXValPresuntivo.setICveSustancia(iCampo);

			cCampo = "" + request.getParameter("dtEstudio");
			if (cCampo != null && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vTOXValPresuntivo.setDtEstudio(dtCampo);

			cCampo = "" + request.getParameter("dCorte");
			if (cCampo != null && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vTOXValPresuntivo.setDCorte(fCampo);

			cCampo = "" + request.getParameter("dCorteNeg");
			if (cCampo != null && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vTOXValPresuntivo.setDCorteNeg(fCampo);

			cCampo = "" + request.getParameter("dCortePost");
			if (cCampo != null && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vTOXValPresuntivo.setDCortePost(fCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXValPresuntivo.setCObservacion(cCampo);

			cCampo = "" + request.getParameter("iCveUsuResp");
			if (cCampo != null && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXValPresuntivo.setICveUsuResp(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXValPresuntivo;
	}

	public int getICveValPres() {
		return iCveValPres;
	}

	public void setICveValPres(int iCveValPres) {
		this.iCveValPres = iCveValPres;
	}
}
