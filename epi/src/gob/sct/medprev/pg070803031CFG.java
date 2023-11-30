package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.Vector;

/**
 * * Clase de configuración para Clase para el lsitado de la tabla ALMSolicSumin
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hernández García
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070803031.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070803031.java.png'>
 */
public class pg070803031CFG extends CFGListBase2 {
	public pg070803031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		// cPaginas = "pg070803031.jsp|";
	}

	public void Despliega() {
		TDALMSolicSumin dALMSolicSumin = new TDALMSolicSumin();
		TVALMSolicSumin vALMSolicSumin = new TVALMSolicSumin();
		try {
			vALMSolicSumin.setICveArticulo(new Integer(0).intValue());
			if (request.getParameter("hdCampoClave") != null)
				vALMSolicSumin.setICveArticulo(new Integer(request
						.getParameter("hdCampoClave")).intValue());

			vALMSolicSumin.setIAnio(new Integer(0).intValue());
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").toString().compareTo("") != 0
					&& new Integer(request.getParameter("iAnio")).intValue() > 0)
				vALMSolicSumin.setIAnio(new Integer(request
						.getParameter("iAnio")).intValue());

			vALMSolicSumin.setICvePeriodo(new Integer(0).intValue());
			if (request.getParameter("iCvePeriodo") != null
					&& request.getParameter("iCvePeriodo").toString()
							.compareTo("") != 0
					&& new Integer(request.getParameter("iCvePeriodo"))
							.intValue() > 0)
				vALMSolicSumin.setICvePeriodo(new Integer(request
						.getParameter("iCvePeriodo")).intValue());

			vALMSolicSumin.setICveTpoArticulo(new Integer(0).intValue());
			if (request.getParameter("iCveTpoArticulo") != null
					&& request.getParameter("iCveTpoArticulo").toString()
							.compareTo("") != 0
					&& new Integer(request.getParameter("iCveTpoArticulo"))
							.intValue() > 0)
				vALMSolicSumin.setICveTpoArticulo(new Integer(request
						.getParameter("iCveTpoArticulo")).intValue());

			vALMSolicSumin.setICveFamilia(new Integer(0).intValue());
			if (request.getParameter("iCveFamilia") != null
					&& request.getParameter("iCveFamilia").toString()
							.compareTo("") != 0
					&& new Integer(request.getParameter("iCveFamilia"))
							.intValue() > 0)
				vALMSolicSumin.setICveFamilia(new Integer(request
						.getParameter("iCveFamilia")).intValue());

			vALMSolicSumin.setLProgramada(new Integer(0).intValue());
			if (request.getParameter("lProgramada") != null
					&& request.getParameter("lProgramada").toString()
							.compareTo("") != 0)
				vALMSolicSumin.setLProgramada(new Integer(request
						.getParameter("lProgramada")).intValue());

			if (cOrden.compareTo("") == 0)
				cOrden = " order by almsolicsumin.icvesolicsum ";

			vALMSolicSumin.setICveSolicSum(0);
			if (request.getParameter("iCveSolicitud") != null
					&& !request.getParameter("iCveSolicitud").equals("0")
					&& !request.getParameter("iCveSolicitud").equalsIgnoreCase(
							"Seleccione..."))
				vALMSolicSumin.setICveSolicSum(new Integer(request
						.getParameter("iCveSolicitud")).intValue());
			else
				vALMSolicSumin.setICveSolicSum(0);

			vDespliega = dALMSolicSumin.FindByAll3(cCondicion, cOrden,
					vALMSolicSumin);

		} catch (Exception x) {
		}
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDALMSolicSumin dALMSolicSumin = new TDALMSolicSumin();
		TVALMSolicSumin vALMSolicSumin = new TVALMSolicSumin();
		TDALMSumArt dALMSumArt = new TDALMSumArt();
		TVALMSumArt vALMSumArt = new TVALMSumArt();
		cPaginas = "pg070803030.jsp|";
		try {
			this.Despliega();
			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton").toString()
							.compareTo("Guardar") == 0) {
				if (vDespliega.size() > 0) {
					for (int i = 0; i < vDespliega.size(); i++) {
						vALMSolicSumin = (TVALMSolicSumin) vDespliega.get(i);
						vALMSumArt.setIAnio(vALMSolicSumin.getIAnio());
						vALMSumArt.setICveAlmacen(vALMSolicSumin
								.getICveAlmacen());
						vALMSumArt.setICveSolicSum(vALMSolicSumin
								.getICveSolicSum());
						vALMSumArt.setICveArticulo(vALMSolicSumin
								.getICveArticulo());
						if (request.getParameter("dUnidAutor"
								+ vALMSolicSumin.getICveSolicSum()) != null
								&& request
										.getParameter(
												"dUnidAutor"
														+ vALMSolicSumin
																.getICveSolicSum())
										.toString().compareTo("") != 0) {
							vALMSumArt
									.setDUnidAutor(new Double(request
											.getParameter("dUnidAutor"
													+ vALMSolicSumin
															.getICveSolicSum()))
											.floatValue());
							dALMSumArt.update3(null, vALMSumArt);
						}
					}
				}
				this.Despliega();
			} else if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton").toString()
							.compareTo("Autorizar") == 0) {
				int count = 0;
				String cFiltro = "";
				Vector cTemp = new Vector();
				if (vDespliega.size() > 0) {
					for (int i = 0; i < vDespliega.size(); i++) {
						vALMSolicSumin = (TVALMSolicSumin) vDespliega.get(i);
						vALMSumArt.setIAnio(vALMSolicSumin.getIAnio());
						vALMSumArt.setICveAlmacen(vALMSolicSumin
								.getICveAlmacen());
						vALMSumArt.setICveSolicSum(vALMSolicSumin
								.getICveSolicSum());
						count = dALMSumArt.FindByCount(vALMSumArt);
						if (count == 0) {
							dALMSolicSumin.updRevisada(null, vALMSolicSumin);
							cFiltro = " WHERE iAnio        = "
									+ vALMSolicSumin.getIAnio()
									+ "   AND iCveAlmacen  = "
									+ vALMSolicSumin.getICveAlmacen()
									+ "   AND iCveSolicSum = "
									+ vALMSolicSumin.getICveSolicSum();
							cTemp = dALMSumArt.FindByAll(cFiltro, "");
							if (cTemp.size() > 0) {
								for (int j = 0; j < cTemp.size(); j++) {
									vALMSumArt = (TVALMSumArt) cTemp.get(j);
									dALMSumArt.update4(null, vALMSumArt);
								}
							}
						}
					}
					this.Despliega();
				}
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
