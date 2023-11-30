package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuración para CFG del catálogo de Usuarios y Unidades Médicas
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Jaime Enrique Suárez Romero
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071001021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071001021CFG.png'>
 */
public class pg071001021CFG extends CFGCatBase2 {
	String cClave2 = "", cClave3 = "", cClave1 = "";

	public pg071001021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071001020.jsp|";
	}

	public void mainBlock() {
		cClave1 = "" + request.getParameter("hdCampoClave");
		cClave2 = "" + request.getParameter("hdiCveUniMed");
		cClave3 = "" + request.getParameter("hdiCveProceso");
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
		try {
			TVGRLUMUsuario vGRLUMUsuario = (TVGRLUMUsuario) dGRLUMUsuario
					.insert(null, this.getInputs());
			cClave1 = "" + vGRLUMUsuario.getICveUsuario();
			cClave2 = "" + vGRLUMUsuario.getICveUniMed();
			cClave3 = "" + vGRLUMUsuario.getICveProceso();
		} catch (Exception ex) {
			error("Error al insertar el registro", ex);
			vErrores.acumulaError("", 14, "");
		} finally {
			super.Guardar();
		}
	} // Método Guardar

	/**
	 * Método Borrar
	 */
	public void Borrar() {
		TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
		TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();
		try {
			vGRLUMUsuario.setICveUsuario(Integer.parseInt(
					"" + request.getParameter("hdCampoClave"), 10));
			vGRLUMUsuario.setICveUniMed(Integer.parseInt(
					"" + request.getParameter("hdiCveUniMed"), 10));
			vGRLUMUsuario.setICveProceso(Integer.parseInt(
					"" + request.getParameter("hdiCveProceso"), 10));
			cClave1 = (String) dGRLUMUsuario.delete(null, vGRLUMUsuario);
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
		TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
		try {
			vDespliega = dGRLUMUsuario.FindByAll("");
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método FillPK
	 */
	public void fillPK() {
		mPk.add(cClave1);
		mPk.add(cClave2);
		mPk.add(cClave3);
	}

	/**
	 * Método getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();
		try {
			cCampo = "" + request.getParameter("iCveUsuario");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUMUsuario.setICveUsuario(iCampo);

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUMUsuario.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCveProceso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUMUsuario.setICveProceso(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vGRLUMUsuario;
	}
}