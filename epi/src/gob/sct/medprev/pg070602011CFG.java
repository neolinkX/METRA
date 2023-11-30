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
 * * Clase de configuracion para CFG Catalogo de Equipos
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070602011CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070602011CFG.png'>
 */
public class pg070602011CFG extends CFGCatBase2 {
	public Vector vTpoEquipo = new Vector();
	public Vector vClasif = new Vector();
	public Vector vMarca = new Vector();
	public Vector vEstado = new Vector();
	public Vector vCausa = new Vector();

	public pg070602011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070602010.jsp|pg070602012.jsp|pg070602013.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
		try {
			cClave = (String) dEQMEquipo.insert2(null, this.getInputs());
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
		TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
		try {
			cClave = (String) dEQMEquipo.update2(null, this.getInputs());
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
		TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
		try {
			cClave = (String) dEQMEquipo.disable(null, this.getInputs());
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
		TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
		TDEQMTpoEquipo dTpoEquipo = new TDEQMTpoEquipo();
		TDEQMClasificacion dClasif = new TDEQMClasificacion();
		TDEQMMarca dMarca = new TDEQMMarca();
		TDEQMEstado dEstado = new TDEQMEstado();
		TDEQMCausaBaja dCausa = new TDEQMCausaBaja();
		TVEQMClasificacion tvClas = new TVEQMClasificacion();
		TVEQMTpoEquipo tvTpoEq = new TVEQMTpoEquipo();
		String iClasif = "";
		String iTipo = "";

		try {
			vTpoEquipo = dTpoEquipo.FindByAll(" where lActivo=1 ",
					" order by iCveClasificacion ");
			vClasif = dClasif.FindByAll(" where lActivo=1 ",
					" order by iCveClasificacion ");
			tvClas.setCDscClasificacion("Seleccione...");
			tvClas.setCDscBreve("Seleccione...");
			tvClas.setICveClasificacion(0);
			vClasif.insertElementAt(tvClas, 0);
			vMarca = dMarca.FindByAll(" where lActivo=1 ",
					" order by iCveMarca ");
			vEstado = dEstado.FindByAll(" where lActivo=1 ",
					" order by iCveEstadoEQM ");
			vCausa = dCausa.FindByAll(" where lActivo=1 ",
					" order by iCveCausaBaja ");

			if (request.getParameter("iCveClasificacion") != null
					&& !request.getParameter("iCveClasificacion").equals(""))
				iClasif = request.getParameter("iCveClasificacion").toString();
			if (request.getParameter("iCveTpoEquipo") != null
					&& !request.getParameter("iCveTpoEquipo").equals(""))
				iTipo = request.getParameter("iCveTpoEquipo").toString();
			String q = " where 1=1 ";
			// if (!iClasif.equals("") && !iClasif.equals("0"))
			// q += " and EqmEquipo.iCveClasificacion=" + iClasif;
			// if (!iTipo.equals("") && !iTipo.equals("0"))
			// q += " and EqmEquipo.iCveTpoEquipo=" + iTipo;
			if (!cCondicion.equals(""))
				q += " and " + cCondicion + " ";
			vDespliega = dEQMEquipo.FindByAllDesc(q + cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
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
		TVEQMEquipo vEQMEquipo = new TVEQMEquipo();
		try {
			cCampo = "" + request.getParameter("iCveEquipo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setICveEquipo(iCampo);

			cCampo = "" + request.getParameter("iCveClasificacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setICveClasificacion(iCampo);

			cCampo = "" + request.getParameter("iCveTpoEquipo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setICveTpoEquipo(iCampo);

			cCampo = "" + request.getParameter("cCveEquipo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEquipo.setCCveEquipo(cCampo);

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

			cCampo = "" + request.getParameter("cInventario");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEquipo.setCInventario(cCampo);

			cCampo = "" + request.getParameter("iCveMarca");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setICveMarca(iCampo);

			cCampo = "" + request.getParameter("cModelo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEquipo.setCModelo(cCampo);

			cCampo = "" + request.getParameter("dtAdquisicion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEQMEquipo.setDtAdquisicion(dtCampo);

			cCampo = "" + request.getParameter("iCveUsuRegistra");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setICveUsuRegistra(iCampo);

			cCampo = "" + request.getParameter("iCveEstadoEQM");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setICveEstadoEQM(iCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setLActivo(iCampo);

			cCampo = "" + request.getParameter("iPerManttoPrev");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setIPerManttoPrev(iCampo);

			cCampo = "" + request.getParameter("iLimiteUso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setILimiteUso(iCampo);

			cCampo = "" + request.getParameter("iPerCalibracion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setIPerCalibracion(iCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEQMEquipo.setCObservacion(cCampo);

			cCampo = "" + request.getParameter("lBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setLBaja(iCampo);

			cCampo = "" + request.getParameter("dtBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEQMEquipo.setDtBaja(dtCampo);

			cCampo = "" + request.getParameter("iCveCausaBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEQMEquipo.setICveCausaBaja(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEQMEquipo;
	}
}