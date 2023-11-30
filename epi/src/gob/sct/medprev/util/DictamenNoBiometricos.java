/*
 * Created on 18/10/2006
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
public class DictamenNoBiometricos extends Dictamen {

	/**
	 * 
	 */
	public DictamenNoBiometricos() {
		try {
			report = (JasperReport) JRLoader
					.loadObjectFromLocation("gob/sct/medprev/util/DictamenNoBiometricos.jasper");
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
	public void setClaveExpediente(String claveExpediente) {
		if (claveExpediente == null) {
			claveExpediente = "";
		}
		parametros.put("claveExpediente", claveExpediente);
	}

	/**
	 * @param Nombre
	 *            The Nombre to set.
	 */
	public void setPNombre(String PNombre) {

		if (PNombre == null) {
			PNombre = "";
		}
		parametros.put("PNombre", PNombre);
	}
}
