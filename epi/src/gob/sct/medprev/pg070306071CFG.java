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
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;
import com.micper.util.*;

/**
 * * Clase de configuración para CFG Catalogo de Equipos
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301071CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301071CFG.png'>
 */
public class pg070306071CFG extends CFGCatBase2 {
	public pg070306071CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDTOXResulVal dTOXResulVal = new TDTOXResulVal();
		TVTOXResulVal tvResul = new TVTOXResulVal();
		float dPorciento = 0;
		float dConcentracion = 0;
		float dResultados = 0;
		int iMuestras = 0;
		int iCveUniMed = 0;
		int iAnio = 0;
		int iCveValPres = 0;
		String iCveLaboratorio = "";
		Vector vMuestras = new Vector();
		try {

			if (request.getParameter("iCveUniMed") != null
					&& !request.getParameter("iCveUniMed").equals(""))
				iCveLaboratorio = request.getParameter("iCveUniMed").toString();
			if (request.getParameter("hdCampoClave3") != null
					&& !request.getParameter("hdCampoClave3").equals(""))
				iCveLaboratorio = request.getParameter("hdCampoClave3")
						.toString();
			if (request.getParameter("iCveLaboratorio") != null
					&& !request.getParameter("iCveLaboratorio").equals(""))
				iCveLaboratorio = request.getParameter("iCveLaboratorio")
						.toString();

			if (request.getParameter("iMuestras") != null) {
				iMuestras = Integer.parseInt(request.getParameter("iMuestras")
						.toString());
			}

			iAnio = Integer.parseInt(request.getParameter("iAnio"));
			iCveUniMed = Integer.parseInt(iCveLaboratorio);
			iCveValPres = Integer.parseInt(request
					.getParameter("hdCampoClave4"));

			for (int i = 0; i < iMuestras; i++) {
				if (request.getParameter("dPorcentaje" + i) != null) {
					tvResul = new TVTOXResulVal();
					tvResul.setIAnio(iAnio);
					tvResul.setICveLaboratorio(iCveUniMed);
					tvResul.setICveValPres(iCveValPres);
					tvResul.setDPorcentaje(Float.parseFloat(request
							.getParameter("dPorcentaje" + i).toString()));
					tvResul.setDConcentracion(Float.parseFloat(request
							.getParameter("dConcentracion" + i).toString()));
					tvResul.setDResultado(Float.parseFloat(request
							.getParameter("dResultados" + i).toString()));
					vMuestras.add(tvResul);
				}
			}
			if (vMuestras != null)
				cClave = (String) dTOXResulVal.insert(null, vMuestras);
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Método Guardar

	/**
	 * Método GuardarA
	 */
	public void GuardarA() {
		TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
		try {
			cClave = (String) dEQMEquipo.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Método GuardarA

	/**
	 * Método Borrar
	 */
	public void Borrar() {
		TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
		try {
			cClave = (String) dEQMEquipo.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Método Borrar

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDTOXResulVal dToxResulVal = new TDTOXResulVal();
		String cFiltro = "";
		cPaginas = "pg070306070.jsp|";
		String cAnio = "";
		String cCveUniMed = "";
		String cCveValPres = "";
		try {

			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").compareTo("") != 0)
				cAnio = " iAnio = " + request.getParameter("iAnio").toString();

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo("") != 0)
				cCveUniMed = " iCveLaboratorio = "
						+ request.getParameter("iCveUniMed").toString();

			if (request.getParameter("hdCampoClave4") != null
					&& request.getParameter("hdCampoClave4").compareTo("") != 0)
				cCveValPres = " iCveValPres = "
						+ request.getParameter("hdCampoClave4").toString();
			if (cAnio.compareTo("") != 0 && cCveUniMed.compareTo("") != 0
					&& cCveValPres.compareTo("") != 0)
				cFiltro = " where " + cAnio + " and " + cCveUniMed + " and "
						+ cCveValPres;

			if (cCondicion != null && cCondicion.toString().compareTo("") != 0) {
				if (cFiltro.compareTo("") != 0)
					cFiltro += " and " + cCondicion;
				else
					cFiltro = " where " + cCondicion;
			}

			cFiltro += cOrden;
			vDespliega = dToxResulVal.FindByAll(cFiltro);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método FillPK
	 */
	public void FillPK() {
		mPk.add(cActual);
	}

	/**
	 * Método getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVEQMEquipo vEQMEquipo = new TVEQMEquipo();
		try {
			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setICveEquipo(iCampo);

			cCampo = "" + request.getParameter("cDscEquipo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEquipo.setCDscEquipo(cCampo);

			cCampo = "" + request.getParameter("cNumSerie");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEquipo.setCNumSerie(cCampo);

			cCampo = "" + request.getParameter("cModelo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEquipo.setCModelo(cCampo);

			cCampo = "" + request.getParameter("iCveTpoEquipo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setICveTpoEquipo(iCampo);

			cCampo = "" + request.getParameter("iCveMarca");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setICveMarca(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEQMEquipo;
	}
}