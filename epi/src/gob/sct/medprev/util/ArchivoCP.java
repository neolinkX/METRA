/*
 * Created on 13/08/2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gob.sct.medprev.util;

import java.util.HashMap;
import java.util.StringTokenizer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @author Eigen
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ArchivoCP extends Dictamen {

	/**
	 * 
	 */
	public ArchivoCP() {
		try {
			report = (JasperReport) JRLoader
					.loadObjectFromLocation("gob/sct/medprev/util/ArchivoCP.jasper");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parametros = new HashMap();
	}

	/**
	 * @param claveExpediente
	 *            The claveExpediente to set.
	 */
	public void setExpedienteP(String expedientep) {
		if (expedientep == null) {
			expedientep = "";
		}
		parametros.put("expedientep", expedientep);
	}

	/**
	 * @param Nombre
	 *            The Nombre to set.
	 */
	public void setPNombre(String nombrep) {

		if (nombrep == null) {
			nombrep = "";
		}
		parametros.put("nombrep", nombrep);
	}

	/**
	 * @param UnimedP
	 *            The UnimedP to set.
	 */
	public void setUnimedP(String unimedp) {

		if (unimedp == null) {
			unimedp = "";
		}
		parametros.put("unimedp", unimedp);
	}

	/**
	 * @param EmpDep
	 *            The EmpDep to set.
	 */
	public void setEmpDep(String empdep) {

		if (empdep == null) {
			empdep = "";
		}
		parametros.put("empdep", empdep);
	}

	/**
	 * @param Fechaap
	 *            The Fechaap to set.
	 */
	public void setFechaap(String fechaap) {

		if (fechaap == null) {
			fechaap = "";
		}
		parametros.put("fechaap", fechaap);
	}

}
