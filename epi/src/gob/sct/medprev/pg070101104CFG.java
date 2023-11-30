package gob.sct.medprev;

import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG Catalogo de reglas de negocio en el examen
 * 
 * @version 1.0
 *          <p>
 * @author <dd>AG SA
 *         <p>
 *         <img src='pg070101100CFG.png'>
 */
public class pg070101104CFG extends CFGCatBase2 {
	int iCveRama = 0;
	int iCveServicio = 0;

	public pg070101104CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
		cPaginas = "pg070101103.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDMEDREGSIN dMEDREGSIN = new TDMEDREGSIN();
		try {
			cClave = (String) dMEDREGSIN.insertAlert(null, this.getInputs());
			cAccion = "Ultimo";
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
		TDMEDREGSIN dMEDREGSIN = new TDMEDREGSIN();
		try {
			cClave = (String) dMEDREGSIN.UpdateAlert(null, this.getInputs());
			cAccion = "Ultimo";
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}

		// System.out.println("Guardar2");
	} // Metodo GuardarA

	/**
	 * Metodo BorrarB
	 */
	public void BorrarB() {
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		try {
			cClave = (String) dMEDSintoma.disable(null, this.getInputs());

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.BorrarB();
		}
	} // Metodo BorrarB

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDMEDREGSIN dMEDREGSIN = new TDMEDREGSIN();
		String cWhere = "";
		try {
			String lAction = "";

			if (request.getParameter("hdBoton") != null) {
				lAction = request.getParameter("hdBoton");
			} else {
				lAction = "";
			}

			if (lAction.trim().length() == 0) {
				lAction = "Buscar";
			}

			String cCond = "";
			String cOrder = "";
			if (lAction.equalsIgnoreCase("Primero")
					|| lAction.equalsIgnoreCase("Siguiente")
					|| lAction.equalsIgnoreCase("Anterior")
					|| lAction.equalsIgnoreCase("Actual")
					|| lAction.equalsIgnoreCase("Ultimo")
					|| lAction.equalsIgnoreCase("Primero")) {
				cWhere = "";
				cCond = request.getParameter("hdCCondicion");
				cOrder = request.getParameter("hdCOrdenar");
				if (cCond != null) {
					if (cCond.compareTo("null") == 0 || cCond.length() == 0) {
						cCond = "";
					} else {
						cCond = " Where " + cCond;
					}
				} else {
					cCond = "";
				}

				if (cOrder != null) {
					if (cOrder.compareTo("null") == 0) {
						cOrder = "";
					}
				} else {
					cOrder = "";
				}

				if (cCond.trim().length() == 0) {
					lAction = "Buscar";
				} else {
					String xCveServicio = "";
					String xCveRama = "";
					String xCveSintoma = "";
					String xCveRegla = "";
					String xCveMdoTrans = "";
					String xCveCategoria = "";

					int inCveServicio = 0;
					int inCveRama = 0;
					int inCveSintoma = 0;
					int inCveRegla = 0;
					int inCveMdoTrans = 0;
					int inCveCategoria = 0;

					xCveServicio = "" + request.getParameter("iCveServicio");
					xCveRama = "" + request.getParameter("iCveRama");
					xCveSintoma = "" + request.getParameter("iCveSintoma");
					xCveRegla = "" + request.getParameter("iCveRegla");
					xCveMdoTrans = "" + request.getParameter("iCveMdoTrans2");
					xCveCategoria = "" + request.getParameter("iCveCategoria2");
					// System.out.println("transporte = "+request.getParameter("iCveMdoTrans2"));
					// System.out.println("iCveCategoria = "+request.getParameter("iCveCategoria2"));

					// /////////
					if (xCveServicio.compareTo("null") != 0
							&& xCveServicio.compareTo("") != 0) {
						inCveServicio = Integer.parseInt(xCveServicio, 10);
					} else {
						inCveServicio = 0;
					}
					// /////////
					if (xCveRama.compareTo("null") != 0
							&& xCveRama.compareTo("") != 0) {
						inCveRama = Integer.parseInt(xCveRama, 10);
					} else {
						inCveRama = 0;
					}
					// /////////
					if (xCveSintoma.compareTo("null") != 0
							&& xCveSintoma.compareTo("") != 0) {
						inCveSintoma = Integer.parseInt(xCveSintoma, 10);
					} else {
						inCveSintoma = 0;
					}

					// /////////
					if (xCveRegla.compareTo("null") != 0
							&& xCveRegla.compareTo("") != 0) {
						inCveRegla = Integer.parseInt(xCveRegla, 10);
					} else {
						inCveRegla = 0;
					}

					// /////////
					if (xCveMdoTrans.compareTo("null") != 0
							&& xCveMdoTrans.compareTo("") != 0) {
						inCveMdoTrans = Integer.parseInt(xCveMdoTrans, 10);
					} else {
						inCveMdoTrans = 0;
					}

					// /////////
					if (xCveCategoria.compareTo("null") != 0
							&& xCveCategoria.compareTo("") != 0) {
						inCveCategoria = Integer.parseInt(xCveCategoria, 10);
					} else {
						inCveCategoria = 0;
					}

					cCond += " WHERE S.ICVESERVICIO = V.ICVESERVICIO AND "
							+ " S.ICVESERVICIO = R.ICVESERVICIO AND "
							+ " S.ICVERAMA = R.ICVERAMA AND  "
							+ " S.ICVESERVICIO = M.ICVESERVICIO AND "
							+ " S.ICVERAMA = M.ICVERAMA AND "
							+ " S.ICVESINTOMA = M.ICVESINTOMA AND "
							+ " S.iCveServicio = " + inCveServicio + " And "
							+ " S.iCveRama = " + inCveRama + " And "
							+ " S.iCveSintoma = " + inCveSintoma + " And "
							+ " S.iCveMdoTrans = 0 And "
							+ " S.iCveCategoria = 0 And " + " S.RDINFO = 1 ";

					cWhere += cCond + " ";
					vDespliega = dMEDREGSIN.FindByAllDetAl(cWhere);
				}
			}

			if (lAction.equalsIgnoreCase("Buscar")
					|| lAction.equalsIgnoreCase("Modificar")) {
				String cFiltro = "";
				String cCveServicio = "";
				String cCveRama = "";
				String cCveSintoma = "";
				String cCveRegla = "";
				String cCveMdoTrans = "";
				String cCveCategoria = "";

				int iCveServicio = 0;
				int iCveRama = 0;
				int iCveSintoma = 0;
				int iCveRegla = 0;
				int iCveMdoTrans = 0;
				int iCveCategoria = 0;

				cCveServicio = "" + request.getParameter("iCveServicio");
				cCveRama = "" + request.getParameter("iCveRama");
				cCveSintoma = "" + request.getParameter("iCveSintoma");
				cCveRegla = "" + request.getParameter("iCveRegla");
				cCveMdoTrans = "" + request.getParameter("iCveMdoTrans2");
				cCveCategoria = "" + request.getParameter("iCveCategoria2");

				if (cCveServicio.compareTo("null") != 0
						&& cCveServicio.compareTo("") != 0) {
					iCveServicio = Integer.parseInt(cCveServicio, 10);
				} else {
					iCveServicio = 0;
				}

				if (cCveRama.compareTo("null") != 0
						&& cCveRama.compareTo("") != 0) {
					iCveRama = Integer.parseInt(cCveRama, 10);
				} else {
					iCveRama = 0;
				}

				if (cCveSintoma.compareTo("null") != 0
						&& cCveSintoma.compareTo("") != 0) {
					iCveSintoma = Integer.parseInt(cCveSintoma, 10);
				} else {
					iCveSintoma = 0;
				}

				if (cCveRegla.compareTo("null") != 0
						&& cCveRegla.compareTo("") != 0) {
					iCveRegla = Integer.parseInt(cCveRegla, 10);
				} else {
					iCveRegla = 0;
				}

				if (cCveMdoTrans.compareTo("null") != 0
						&& cCveMdoTrans.compareTo("") != 0) {
					iCveMdoTrans = Integer.parseInt(cCveMdoTrans, 10);
				} else {
					iCveMdoTrans = 0;
				}

				if (cCveCategoria.compareTo("null") != 0
						&& cCveCategoria.compareTo("") != 0) {
					iCveCategoria = Integer.parseInt(cCveCategoria, 10);
				} else {
					iCveCategoria = 0;
				}

				cWhere = " WHERE S.ICVESERVICIO = V.ICVESERVICIO AND "
						+ " S.ICVESERVICIO = R.ICVESERVICIO AND "
						+ " S.ICVERAMA = R.ICVERAMA AND  "
						+ " S.ICVESERVICIO = M.ICVESERVICIO AND "
						+ " S.ICVERAMA = M.ICVERAMA AND "
						+ " S.ICVESINTOMA = M.ICVESINTOMA AND "
						+ " S.iCveServicio = " + iCveServicio + " And "
						+ " S.iCveRama = " + iCveRama + " And "
						+ " S.iCveSintoma = " + iCveSintoma + " And "
						+ " S.RDINFO = 1";

				vDespliega = dMEDREGSIN.FindByAllDetAl(cWhere);

			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void fillPK() {
		mPk.add("" + request.getParameter("iCveServicio"));
		mPk.add("" + request.getParameter("iCveRama"));
		mPk.add(cActual);
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		double dCampo = 0.0;
		float dValorI = 0.0f;
		java.sql.Date dtCampo;
		// TFechas tfCampo = new TFechas();
		// TVMEDRespSint vMEDRespSint = new TVMEDRespSint();
		TVMEDREGSIN vMEDREGSIN = new TVMEDREGSIN();
		try {
			cCampo = "" + request.getParameter("iCveServicio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDREGSIN.setICveServicio(iCampo);

			cCampo = "" + request.getParameter("iCveRama");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDREGSIN.setICveRama(iCampo);

			cCampo = "" + request.getParameter("iCveSintoma");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDREGSIN.setICveSintoma(iCampo);

			cCampo = "" + request.getParameter("hdTpoResp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDREGSIN.setICveTpoResp(iCampo);

			cCampo = "" + request.getParameter("Logica");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDREGSIN.setLogica(iCampo);

			cCampo = "" + request.getParameter("iMayorA");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				// iCampo = Integer.parseInt(cCampo, 10);
				// fCampo = Float.parseFloat(cCampo);
				dCampo = Double.parseDouble(cCampo);
			} else {
				dCampo = 0;
			}
			vMEDREGSIN.setIMayorA(dCampo);

			cCampo = "" + request.getParameter("iMenorA");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				// iCampo = Integer.parseInt(cCampo, 10);
				// fCampo = Float.parseFloat(cCampo);
				dCampo = Double.parseDouble(cCampo);
			} else {
				dCampo = 0;
			}
			vMEDREGSIN.setIMenorA(dCampo);

			cCampo = "" + request.getParameter("iIgualA");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				// iCampo = Integer.parseInt(cCampo, 10);
				// fCampo = Float.parseFloat(cCampo);
				dCampo = Double.parseDouble(cCampo);
			} else {
				dCampo = 0;
			}
			vMEDREGSIN.setIIgualA(dCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				if (cCampo.compareTo("1") == 0 || cCampo.compareTo("on") == 0)
					iCampo = 1;
				else
					iCampo = 0;
			} else {
				iCampo = 0;
			}
			vMEDREGSIN.setLActivo(iCampo);

			cCampo = "" + request.getParameter("lDictamenF");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				if (cCampo.compareTo("on") == 0)
					iCampo = 1;
				else
					iCampo = 0;
			} else {
				iCampo = 0;
			}
			vMEDREGSIN.setLDictamenF(iCampo);

			cCampo = "" + request.getParameter("cAlerta");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDREGSIN.setCAlerta(cCampo);

			cCampo = "" + request.getParameter("cdscRegla");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDREGSIN.setCdscRegla(cCampo);

			cCampo = "" + request.getParameter("iCveRegla");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMEDREGSIN.setICveRegla(iCampo);

			cCampo = "" + request.getParameter("Categorias");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDREGSIN.setCCategorias(cCampo);

			cCampo = "" + request.getParameter("cServicios");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMEDREGSIN.setCServicios(cCampo);

			vMEDREGSIN.setICveMdoTrans(0);
			vMEDREGSIN.setICveCategoria(0);

			TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true)
					.getAttribute("UsrID");
			vMEDREGSIN.setICveUsugenera(vUsuario.getICveusuario());

			// Accion
			vMEDREGSIN.setRdAccion(0);
			// Info
			vMEDREGSIN.setRdInfo(1);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMEDREGSIN;
	}
}