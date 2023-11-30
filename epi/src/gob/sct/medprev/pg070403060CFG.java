package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuración para Control al Transporte - Periodos/Pla
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Oscar Castrejón Adame.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070403060CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070403060CFG.png'>
 */
public class pg070403060CFG extends CFGListBase2 {
	int ivAnio = 0;
	int ivMdoTransporte = 0;
	public Vector vAccidentes = new Vector();
	private StringBuffer activeX = new StringBuffer("");

	public pg070403060CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		// cPaginas = "pg070501071.jsp|";
	}

	public void mainBlock() {

		if (request.getParameter("iAnio") != null)
			if (request.getParameter("iAnio").toString()
					.compareToIgnoreCase("") != 0)
				ivAnio = new Integer(request.getParameter("iAnio")).intValue();

		if (cAccion.compareToIgnoreCase("Enviar") == 0)
			this.Enviar();

	}

	public Vector getVigentes(int ivAnio) {
		TDINVRegistro DINVRegistro = new TDINVRegistro();
		TFechas Fecha = new TFechas("7");
		java.sql.Date dtVigencia = null;

		dtVigencia = Fecha.TodaySQL();

		try {
			vAccidentes = DINVRegistro
					.CountByAnioLicVigente(ivAnio, dtVigencia);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vAccidentes;
	}

	public Vector getNoVigentes(int ivAnio) {
		TDINVRegistro DINVRegistro = new TDINVRegistro();
		TFechas Fecha = new TFechas("7");
		java.sql.Date dtVigencia = null;

		dtVigencia = Fecha.TodaySQL();

		try {
			vAccidentes = DINVRegistro.CountByAnioLicNoVigente(ivAnio,
					dtVigencia);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vAccidentes;
	}

	public Vector getSeIgnora(int ivAnio) {
		TDINVRegistro DINVRegistro = new TDINVRegistro();

		try {
			vAccidentes = DINVRegistro.CountByAnioLicSeIgnora(ivAnio);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vAccidentes;
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
		try {
			String cWhere = "";
			String cOrderBy = "";

			// if(request.getParameter("iCveUniMed")!=null)
			// cWhere += " where iCveUniMed = " +
			// request.getParameter("iCveUniMed") + " ";

			// if (cCondicion.compareTo("") != 0) {
			// if(cWhere.compareTo("")!=0)
			// cWhere += " and " + cCondicion + " ";
			// else
			// cWhere += cCondicion + " ";
			// }

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			} else
				cOrderBy = "order by cDscUniMed";

			vDespliega = DGRLMdoTrans.findByAll(" where lActivo = 1 ",
					" Order by cDscMdoTrans");

			if (!vDespliega.isEmpty()) {
				iNumReg = vDespliega.size() + 1;
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public void Enviar() {
		TExcel Rep1 = new TExcel("07");
		TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
		Vector vEntidad = new Vector();
		Vector vVigentes = new Vector();
		Vector vNoVigentes = new Vector();
		Vector vSeIgnora = new Vector();
		TFechas Fecha = new TFechas("07");
		String cPlantilla = "pg070403060";
		int ivVigente = 0, ivNoVigente = 0, ivSeIgnora = 0;
		int ivTotVigente = 0, ivTotNoVigente = 0, ivTotSeIgnora = 0;
		int ivInicial = 12; // Valor del Renglon Inicial del Reporte.
		int ivIniPagina2 = 3;
		int ivTotUniMed = 0;
		int ivTotGeneral = 0;

		// Obtención de los Valores de los Estados.
		try {
			vEntidad = DGRLMdoTrans.findByAll(" where lActivo = 1 ",
					" Order by cDscMdoTrans");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Obtención de los valores de las Cantidades
		vVigentes = this.getVigentes(ivAnio);
		vNoVigentes = this.getNoVigentes(ivAnio);

		Rep1.comEligeHoja(1);
		Rep1.comDespliega("D", 9, "REPORTE DE ACCIDENTES OCURRIDOS");

		if (!vEntidad.isEmpty()) {
			for (int i = 0; i < vEntidad.size(); i++) {
				TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
				VGRLMdoTrans = (TVGRLMdoTrans) vEntidad.get(i);
				ivVigente = 0;
				ivNoVigente = 0;
				ivSeIgnora = 0;

				// Valores de Licencias Vigentes.
				for (int j = 0; j < vVigentes.size(); j++) {
					TVINVRegistro VINVRegistro = new TVINVRegistro();
					VINVRegistro = (TVINVRegistro) vVigentes.get(j);
					if (VINVRegistro.getICveMdoTrans() == VGRLMdoTrans
							.getICveMdoTrans()) {
						ivVigente = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
					}
				}
				// Valores de Licencias No Vigentes.
				for (int j = 0; j < vNoVigentes.size(); j++) {
					TVINVRegistro VINVRegistro = new TVINVRegistro();
					VINVRegistro = (TVINVRegistro) vNoVigentes.get(j);
					if (VINVRegistro.getICveMdoTrans() == VGRLMdoTrans
							.getICveMdoTrans()) {
						ivNoVigente = VINVRegistro.getICveMotivo(); // Valor
																	// count que
																	// envia la
																	// BD.
					}
				}

				// Valores de Licencias que se Ignoran.
				for (int j = 0; j < vSeIgnora.size(); j++) {
					TVINVRegistro VINVRegistro = new TVINVRegistro();
					VINVRegistro = (TVINVRegistro) vSeIgnora.get(j);
					if (VINVRegistro.getICveMdoTrans() == VGRLMdoTrans
							.getICveMdoTrans()) {
						ivSeIgnora = VINVRegistro.getICveMotivo(); // Valor
																	// count que
																	// envia la
																	// BD.
					}
				}

				Rep1.comEligeHoja(1);
				Rep1.comDespliega("C", ivInicial,
						"" + VGRLMdoTrans.getCDscMdoTrans());
				Rep1.comDespliega("D", ivInicial, "" + ivVigente);
				Rep1.comDespliega("E", ivInicial, "" + ivNoVigente);
				Rep1.comDespliega("F", ivInicial, "" + ivSeIgnora);
				ivTotUniMed = ivVigente + ivNoVigente + ivSeIgnora;
				Rep1.comDespliega("G", ivInicial, "" + ivTotUniMed);
				ivInicial = ivInicial + 1;

				// Elije la Hoja 2, para la Gráfica.
				Rep1.comEligeHoja(2);
				Rep1.comDespliega("A", ivIniPagina2, "" + ivVigente);
				Rep1.comDespliega("B", ivIniPagina2, "" + ivNoVigente);
				Rep1.comDespliega("C", ivIniPagina2, "" + ivSeIgnora);
				ivIniPagina2 = ivIniPagina2 + 2;

				// Acumulación de los Totales.
				ivTotVigente = ivTotVigente + ivVigente;
				ivTotNoVigente = ivTotNoVigente + ivNoVigente;
				ivTotSeIgnora = ivTotSeIgnora + ivSeIgnora;
			}
		}
		Rep1.comEligeHoja(1);
		Rep1.comDespliega("C", ivInicial, "" + "T O T A L:");
		Rep1.comDespliega("D", ivInicial, "" + ivTotVigente);
		Rep1.comDespliega("E", ivInicial, "" + ivTotNoVigente);
		Rep1.comDespliega("F", ivInicial, "" + ivTotSeIgnora);
		ivTotGeneral = ivTotVigente + ivTotNoVigente + ivTotSeIgnora;
		Rep1.comDespliega("P", ivInicial, "" + ivTotGeneral);

		Rep1.comEligeHoja(3);
		StringBuffer buffer = Rep1
				.creaActiveX(cPlantilla, "InvAcc-Accidentes Ocurridos-"
						+ ivAnio + "-" + ivMdoTransporte, false, false, true);
		activeX.append(buffer);
	}

	public String getActiveX() {
		return activeX.toString();
	}

}