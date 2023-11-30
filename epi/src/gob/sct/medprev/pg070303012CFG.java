package gob.sct.medprev;

import java.util.*;
import java.lang.*;
import java.sql.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
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
 * @author <dd>Itzia Mar�a del Carmen S�nchez M�ndez
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
public class pg070303012CFG extends CFGListBase2 {

	private TVTOXEnvio vEnvio = null;
	private int iNumCeldas;

	public pg070303012CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXMuestra dTOXMuestra = new TDTOXMuestra();
		if (this.getLPagina("pg070303011.jsp"))
			cPaginas += "pg070303011.jsp|";
		if (this.getLPagina("pg070303010.jsp"))
			cPaginas += "pg070303010.jsp|";

		String xCampo = "";
		String cAnio = "";
		String cUnidad = "";
		String cWhere = "";
		String cCondEnvio = "";
		HashMap hMuestra = new HashMap();
		int zCampo = 0;
		try {
			this.iNumCeldas = this.iNumReg;
			xCampo = "" + request.getParameter("iCveEnvio");
			cAnio = "" + request.getParameter("hdCampoClave");
			cUnidad = "" + request.getParameter("hdCPropiedad");
			if (xCampo.compareTo("null") != 0 && xCampo.compareTo("") != 0) {
				zCampo = Integer.parseInt(xCampo, 10);
				cWhere = " Where a.iAnio      = " + cAnio
						+ "   and a.iCveUniMed = " + cUnidad
						+ "   and a.iCveEnvio  = " + zCampo;
				cCondEnvio = " where E.iAnio      = " + cAnio
						+ "   and E.iCveUniMed = " + cUnidad
						+ "   and E.iCveEnvio  = " + zCampo;

			} else {
				zCampo = 0;
			}

			// Se solicit� guardar el orden.
			if ("Guardar".compareToIgnoreCase(this.cAccionOriginal) == 0) {
				Vector vInfoEnvio = new TDTOXEnvio().findEnvioOrden(cCondEnvio,
						"");
				String cMuestra = "", cMuestraNE = "", cMuestraRP = "";
				TVTOXMuestra vMuestra;
				ArrayList aMuestra = new ArrayList();
				boolean lValida = true;
				if (vInfoEnvio.size() > 0) {
					this.vEnvio = (TVTOXEnvio) vInfoEnvio.get(0);
					// Obtener las muestras del Envio

					int iOrdenReal = vEnvio.getIMaxOrden();
					// Se ordenan por el n�mero de indentificaci�n de la
					// muestra
					if (request.getParameter("lOrdNim") != null) {
						aMuestra = dTOXMuestra.guardarOrdenXFiltro(cCondEnvio);
					} else { // El usuario indica el Orden
						hMuestra = dTOXMuestra.ObtenerOrden(cCondEnvio);
						// Obtener los datos a almacenar
						for (int i = 1; i <= this.iNumCeldas
								&& i <= this.vEnvio.getITotalRecibidas(); i++) {
							cMuestra = request.getParameter("Orden_"
									+ vEnvio.getIMaxOrden() + i);
							cMuestra = String.valueOf(Integer
									.parseInt(cMuestra));
							if (hMuestra.containsKey(cMuestra) == true) {
								vMuestra = (TVTOXMuestra) hMuestra
										.get(cMuestra);
								if (vMuestra.getIOrden() == 0) {
									vMuestra.setIOrden(++iOrdenReal);
									aMuestra.add(vMuestra);
								} else
									cMuestraRP += cMuestra + ", ";
							} // La muestra es del Env�o
							else
								cMuestraNE += cMuestra + ", ";
						} // Obtener datos a almacenar
							// Enviar errores de registro
						if (cMuestraRP.length() > 0) {
							this.vErrores
									.acumulaError(
											"Las muestras: "
													+ cMuestraRP
													+ " ya hab�an sido registras, por lo tanto se recorri� el ordenamiento",
											0, "");
							lValida = false;
						}
						if (cMuestraNE.length() > 0) {
							this.vErrores.acumulaError("Las muestras: "
									+ cMuestraNE + " no pertenecen al env�o",
									0, "");
							lValida = false;
						}
					}
					// Enviar actualizaci�n
					if (lValida)
						if (dTOXMuestra.guardarOrden(aMuestra))
							this.vErrores
									.acumulaError(
											"El ordenamiento se almacen� de manera correcta",
											0, "");
						else
							this.vErrores
									.acumulaError(
											"Exist�o un error en el guardado, favor de intentarlo nuevamente.",
											0, "");
				} // Se tiene informaci�n del env�o
				else
					this.vErrores.acumulaError(
							"No se obtuvo la informaci�n del Env�o", 0, "");
			} // Acci�n guardar

			// Agregar filtro a la condici�n.
			if (this.cCondicion != null && !cCondicion.equalsIgnoreCase(""))
				cWhere += " and " + this.cCondicion;
			// Agregar opci�n de ordenamiento.
			if (this.cOrden != null && this.cOrden.length() > 0)
				cWhere += this.cOrden;
			else
				cWhere += " order by a.iOrden";

			vDespliega = dTOXMuestra.FindByAll(cWhere);
			Vector vInfoEnvio = new TDTOXEnvio().findEnvioOrden(cCondEnvio, "");
			if (vInfoEnvio.size() > 0) {
				this.vEnvio = (TVTOXEnvio) vInfoEnvio.get(0);
				// Poner el panel inactivo
				if (vEnvio.getITotalRecibidas() > 0)
					this.UpdStatus = "SaveCancelOnly";
				else
					this.UpdStatus = "Hidden";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
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
		TVTOXMuestra vTOXMuestra = new TVTOXMuestra();

		try {

			cCampo = "" + request.getParameter("iCveMuestra");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXMuestra.setICveMuestra(Integer.parseInt(cCampo));

			cCampo = "" + request.getParameter("iCveTipoRecep");

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXMuestra.setICveTipoRecep(iCampo);

			cCampo = "" + request.getParameter("iCveMotRecep");

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXMuestra.setICveMotRecep(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXMuestra;
	}

	public gob.sct.medprev.vo.TVTOXEnvio getVEnvio() {
		return vEnvio;
	}

	public int getINumCeldas() {
		return iNumCeldas;
	}

}
