package gob.sct.medprev;

import java.util.Vector;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.caching.AppCacheManager;

import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG del cat�logo de Usuarios y Unidades
 * M�dicas
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Jaime Enrique Su�rez Romero
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
public class pg071001031CFG extends CFGCatBase2 {
	String cClave2 = "", cClave3 = "", cClave1 = "", cClave4 = "",
			cClave5 = "", cClave6 = "";

	public pg071001031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071001030.jsp|";
	}

	public void mainBlock() {
		cClave1 = "" + request.getParameter("hdCampoClave");
		cClave2 = "" + request.getParameter("hdiCveUniMed");
		cClave3 = "" + request.getParameter("hdiCveProceso");
		cClave4 = "" + request.getParameter("hdiCveModulo");
		cClave5 = "" + request.getParameter("hdiCveServicio");
		cClave6 = "" + request.getParameter("hdiCveRama");
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {

		try {
			TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
			TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
			int contador = 0;
			TVGRLUSUMedicos parametrosIniciales = dGRLUSUMedicos
					.deleteAllByPermisos(null, this.getInputs(0, 0));
			Vector vcMEDServicio = new DAOMEDServicio().FindByAll();
			TDGRLUSUMedicos tdUsuMed = new TDGRLUSUMedicos();
			for (int i = 0; i < vcMEDServicio.size(); i++) {
				TVMEDServicio serv = (TVMEDServicio) vcMEDServicio.get(i);
				Vector vc = (Vector) AppCacheManager.getColl("MEDRama",
						serv.getICveServicio() + "|");
				for (int j = 0; j < vc.size(); j++) {
					TVMEDRama rama = (TVMEDRama) vc.get(j);
					String cCampo = request
							.getParameter("cb|" + serv.getICveServicio() + "|"
									+ rama.getICveRama());
					if (cCampo != null) {
						if (cCampo.equals("1")) {
							vGRLUSUMedicos = (TVGRLUSUMedicos) dGRLUSUMedicos
									.insert(null, this.getInputs(
											serv.getICveServicio(),
											rama.getICveRama()));
							contador++;
						}
					}

				}
			}

			cClave1 = "" + parametrosIniciales.getICveUsuario();
			cClave2 = "" + parametrosIniciales.getICveUniMed();
			cClave3 = "" + parametrosIniciales.getICveProceso();
			cClave4 = "" + parametrosIniciales.getICveModulo();
			cClave5 = "";
			cClave6 = "";
		} catch (Exception ex) {
			error("Error al insertar el registro", ex);
			vErrores.acumulaError("", 14, "");
		} finally {
			super.Guardar();
			vErrores.acumulaError("", 21, "");
		}
	} // Método Guardar

	/**
	 * Método Borrar
	 */
	public void Borrar() {
		TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
		TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
		try {
			vGRLUSUMedicos.setICveUsuario(Integer.parseInt(cClave1, 10));
			vGRLUSUMedicos.setICveUniMed(Integer.parseInt(cClave2, 10));
			vGRLUSUMedicos.setICveProceso(Integer.parseInt(cClave3, 10));
			vGRLUSUMedicos.setICveModulo(Integer.parseInt(cClave4, 10));
			vGRLUSUMedicos.setICveServicio(Integer.parseInt(cClave5, 10));
			vGRLUSUMedicos.setICveRama(Integer.parseInt(cClave6, 10));
			cClave1 = (String) dGRLUSUMedicos.delete(null, vGRLUSUMedicos);
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
		TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
		try {
			// System.out.println("fillVector = "+request.getParameter("hdiCveUsuario"));
			// vDespliega = dGRLUSUMedicos.FindByAll("");
			/*
			 * TVGRLUSUMedicos vGRLUSUMedicos = (TVGRLUSUMedicos)
			 * this.getInputs(); cClave1 = ""+vGRLUSUMedicos.getICveUsuario();
			 * cClave2 = ""+vGRLUSUMedicos.getICveUniMed(); cClave3 =
			 * ""+vGRLUSUMedicos.getICveProceso(); cClave4 =
			 * ""+vGRLUSUMedicos.getICveModulo(); cClave5 =
			 * ""+vGRLUSUMedicos.getICveServicio(); cClave6 =
			 * ""+vGRLUSUMedicos.getICveRama();
			 */
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
		mPk.add(cClave4);
		mPk.add(cClave5);
		mPk.add(cClave6);
	}

	/**
	 * Método getInputs
	 */
	public Object getInputs(int servicio, int rama) throws CFGException {
		String cCampo;
		int iCampo;
		TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
		try {
			cCampo = "" + request.getParameter("iCveUsuario");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUSUMedicos.setICveUsuario(iCampo);

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUSUMedicos.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCveProceso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUSUMedicos.setICveProceso(iCampo);

			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLUSUMedicos.setICveModulo(iCampo);
			vGRLUSUMedicos.setICveServicio(servicio);
			vGRLUSUMedicos.setICveRama(rama);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vGRLUSUMedicos;
	}
}