package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Clase de configuracion para Cat�logo de la
 * tabla TOXTurnoValida
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301061CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301061CFG.png'>
 */
public class pg070301061CFG extends CFGCatBase2 {
	public pg070301061CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDTOXTurnoValida dTOXTurnoValida = new TDTOXTurnoValida();
		try {
			cClave = (String) dTOXTurnoValida.insert(null, this.getInputs());
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
		TDTOXTurnoValida dTOXTurnoValida = new TDTOXTurnoValida();
		try {
			cClave = (String) dTOXTurnoValida.update(null, this.getInputs());
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
		TDTOXTurnoValida dTOXTurnoValida = new TDTOXTurnoValida();
		try {
			cClave = (String) dTOXTurnoValida.delete(null, this.getInputs());
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
		try {
			TDTOXTurnoValida dTOXTurnoValida = new TDTOXTurnoValida();
			if (this.getLPagina("pg070301060.jsp"))
				cPaginas = "pg070301060.jsp|";
			String cWhere = "";
			String cOrderBy = "";

			if (cCondicion.compareTo("") != 0) {
				cWhere += " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			} else
				cOrderBy = " order by iCveTurnoValida ";

			vDespliega = dTOXTurnoValida.FindByAll3(cWhere, cOrderBy);
		} catch (Exception ex) {
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
		TVTOXTurnoValida vTOXTurnoValida = new TVTOXTurnoValida();
		try {
			cCampo = "" + request.getParameter("SLSICveArea");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXTurnoValida.setICveArea(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXTurnoValida.setICveTurnoValida(iCampo);

			cCampo = "" + request.getParameter("cDscTurno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXTurnoValida.setCDscTurno(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXTurnoValida.setCDscBreve(cCampo);

			cCampo = "" + request.getParameter("iCveUsuRespon");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXTurnoValida.setICveUsuRespon(iCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXTurnoValida;
	}

	public StringBuffer miSelectOneRowSinTD(String cNombre, String cOnChange,
			java.util.Vector vDatos, String cCampoCve, String cCampoDsc,
			String cCampoSelected) {
		StringBuffer sbRes = new StringBuffer("");
		try {
			boolean lFirst = false;
			com.micper.sql.BeanScroller beanSc = new com.micper.sql.BeanScroller(
					vDatos);
			sbRes.append("<SELECT NAME=\"" + cNombre
					+ "\" SIZE=\"1\" onChange=\"" + cOnChange + "\">");
			for (int i = 0; i < beanSc.rowSize(); i++) {
				beanSc.setRowIdx(i + 1);
				String CampoClave = beanSc.getFieldValue(cCampoCve, "")
						.toString();
				if ("".equals(cCampoSelected) && lFirst == false) {
					CampoClave = "SELECTED ";
					lFirst = true;
				} else if (CampoClave.equalsIgnoreCase(cCampoSelected))
					CampoClave = "SELECTED ";
				else
					CampoClave = "";
				sbRes.append("<option " + CampoClave + "value=\""
						+ beanSc.getFieldValue(cCampoCve, "" + i) + "\">"
						+ beanSc.getFieldValue(cCampoDsc, "") + "</option>");
			}
			sbRes.append("</SELECT>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbRes;
	}
}