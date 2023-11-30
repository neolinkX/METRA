package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * * Clase de configuracion para Generales - Listado de M�dulos
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003020CFG.png'>
 */
public class pg071003030CFG extends CFGListBase2 {
	public pg071003030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071003031.jsp|pg071003010.jsp|pg071003032.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLModulo dGRLModulo = new TDGRLModulo();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo("") != 0)
				cWhere += " where iCveUniMed = "
						+ request.getParameter("iCveUniMed");
			else if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where iCveUniMed = "
						+ request.getParameter("iCveUniMed");

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy += cOrden;
			} else {
				// cOrderBy = " order by iCveModulo ";
				cOrderBy = " order by cDscModulo ";
			}

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo("") != 0
					&& request.getParameter("iCveUniMed").compareTo("null") != 0
					&& Integer.parseInt(request.getParameter("iCveUniMed")) > 0)
				vDespliega = dGRLModulo.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
	

	/**
	 * Metodo FillVector
	 */
	public boolean CargaModulosYServiciosAdmin(){
		boolean resultado = false;
		TDGRLUSUMedicos dUSUMedicos = new TDGRLUSUMedicos();
		try{
			resultado = dUSUMedicos.InsertAdministradores(null);
		}catch(Exception e){
			resultado = false;
		}
		return resultado;
	}
	
}