package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Clase para el lsitado de la tabla ALMSolicSumin
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hern�ndez Garc�a
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070803030.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070803030.java.png'>
 */
public class pg070803030CFG extends CFGListBase2 {
	public pg070803030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		// cPaginas = "pg070803031.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDALMSolicSumin dALMSolicSumin = new TDALMSolicSumin();
		TVALMSolicSumin vALMSolicSumin = new TVALMSolicSumin();
		try {
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").toString().compareTo("") != 0
					&& new Integer(request.getParameter("iAnio")).intValue() > 0)
				vALMSolicSumin.setIAnio(new Integer(request
						.getParameter("iAnio")).intValue());
			else
				vALMSolicSumin.setIAnio(new Integer(0).intValue());

			if (request.getParameter("iCvePeriodo") != null
					&& request.getParameter("iCvePeriodo").toString()
							.compareTo("") != 0
					&& new Integer(request.getParameter("iCvePeriodo"))
							.intValue() > 0)
				vALMSolicSumin.setICvePeriodo(new Integer(request
						.getParameter("iCvePeriodo")).intValue());
			else
				vALMSolicSumin.setICvePeriodo(new Integer(0).intValue());

			if (request.getParameter("iCveTpoArticulo") != null
					&& request.getParameter("iCveTpoArticulo").toString()
							.compareTo("") != 0
					&& new Integer(request.getParameter("iCveTpoArticulo"))
							.intValue() > 0)
				vALMSolicSumin.setICveTpoArticulo(new Integer(request
						.getParameter("iCveTpoArticulo")).intValue());
			else
				vALMSolicSumin.setICveTpoArticulo(new Integer(0).intValue());

			if (request.getParameter("iCveFamilia") != null
					&& request.getParameter("iCveFamilia").toString()
							.compareTo("") != 0
					&& new Integer(request.getParameter("iCveFamilia"))
							.intValue() > 0)
				vALMSolicSumin.setICveFamilia(new Integer(request
						.getParameter("iCveFamilia")).intValue());
			else
				vALMSolicSumin.setICveFamilia(new Integer(0).intValue());

			if (request.getParameter("lProgramada") != null
					&& request.getParameter("lProgramada").toString()
							.compareTo("") != 0)
				vALMSolicSumin.setLProgramada(new Integer(request
						.getParameter("lProgramada")).intValue());
			else
				vALMSolicSumin.setLProgramada(new Integer(0).intValue());

			vALMSolicSumin.setICveSolicSum(0);
			if (request.getParameter("iCveSolicitud") != null
					&& !request.getParameter("iCveSolicitud").equals("0")
					&& !request.getParameter("iCveSolicitud").equalsIgnoreCase(
							"Seleccione..."))
				vALMSolicSumin.setICveSolicSum(new Integer(request
						.getParameter("iCveSolicitud")).intValue());
			else
				vALMSolicSumin.setICveSolicSum(0);

			if (cOrden.compareTo("") == 0)
				cOrden = " order by almsumart.icvearticulo ";
			vDespliega = dALMSolicSumin.FindByAll2(cCondicion, cOrden,
					vALMSolicSumin);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
