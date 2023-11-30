package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para CFG del Listado de Usuarios, Servicios y Ramas
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071001020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071001020CFG.png'>
 */
public class pg071001030CFG extends CFGListBase2 {
	public pg071001030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071001031.jsp|";
	}

	public void fillVector() {
		TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
		try {
			// vDespliega =
			// dGRLUSUMedicos.FindByAll("order by grlusumedicos.iCveUsuario, grlusumedicos.iCveUniMed, grlusumedicos.iCveProceso, grlusumedicos.iCveModulo, grlusumedicos.iCveServicio, grlusumedicos.iCveRama ");
			if (request.getParameter("hdiCveUsuario") != null
					&& request.getParameter("hdiCveUsuario").compareTo("") != 0) {
				vDespliega = dGRLUSUMedicos
						.FindByAllLUSR(" g.iCveUsuario = 71 "
								+ "		order by g.iCveUsuario, g.iCveUniMed, "
								+ "           g.iCveProceso, g.iCveModulo, "
								+ "           g.iCveServicio, g.iCveRama  "); // AG
																				// SA
																				// 23
																				// de
																				// febrero
																				// 2012
			} else {
				vDespliega = dGRLUSUMedicos
						.FindByAllLUSR("   g.iCveUsuario = 0 "
								+ "		order by g.iCveUsuario, g.iCveUniMed, "
								+ "           g.iCveProceso, g.iCveModulo, "
								+ "           g.iCveServicio, g.iCveRama  ");
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}