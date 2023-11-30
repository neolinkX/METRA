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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070403030CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070403030CFG.png'>
 */
public class pg070403030CFG extends CFGListBase2 {
	int ivAnio = 0;
	int ivMdoTransporte = 0;
	public Vector vAccidentes = new Vector();
	private StringBuffer activeX = new StringBuffer("");

	public pg070403030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		// cPaginas = "pg070501071.jsp|";
	}

	public void mainBlock() {

		if (request.getParameter("iAnio") != null)
			if (request.getParameter("iAnio").toString()
					.compareToIgnoreCase("") != 0)
				ivAnio = new Integer(request.getParameter("iAnio")).intValue();

		if (request.getParameter("iCveMdoTrans") != null)
			if (request.getParameter("iCveMdoTrans").toString()
					.compareToIgnoreCase("") != 0)
				ivMdoTransporte = new Integer(
						request.getParameter("iCveMdoTrans")).intValue();

		if (cAccion.compareToIgnoreCase("Enviar") == 0)
			this.Enviar();

	}

	public Vector getValores(int ivAnio, int ivMdoTransporte) {
		TDINVRegistro DINVRegistro = new TDINVRegistro();

		try {
			vAccidentes = DINVRegistro.CountByAnioSituacion(ivAnio,
					ivMdoTransporte);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vAccidentes;
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDINVSituacion DINVSituacion = new TDINVSituacion();
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

			vDespliega = DINVSituacion.FindByAll(" lActivo = 1 ",
					" Order by cDscSituacion");

			if (!vDespliega.isEmpty()) {
				iNumReg = vDespliega.size() + 1;
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public void Enviar() {
		TExcel Rep1 = new TExcel("07");
		TDINVSituacion DINVSituacion = new TDINVSituacion();
		Vector vEntidad = new Vector();
		Vector vRegistro = new Vector();
		TFechas Fecha = new TFechas("07");
		String cPlantilla = "pg070403030";
		int ivEnero = 0, ivFebrero = 0, ivMarzo = 0, ivAbril = 0, ivMayo = 0, ivJunio = 0;
		int ivJulio = 0, ivAgosto = 0, ivSeptiembre = 0, ivOctubre = 0, ivNoviembre = 0, ivDiciembre = 0;
		int ivTotEnero = 0, ivTotFebrero = 0, ivTotMarzo = 0, ivTotAbril = 0, ivTotMayo = 0, ivTotJunio = 0;
		int ivTotJulio = 0, ivTotAgosto = 0, ivTotSeptiembre = 0, ivTotOctubre = 0, ivTotNoviembre = 0, ivTotDiciembre = 0;
		int ivInicial = 12; // Valor del Renglon Inicial del Reporte.
		int ivIniHoja2 = 3;
		int ivTotUniMed = 0;
		int ivTotGeneral = 0;

		// Obtención de los Valores de los Estados.
		try {
			vEntidad = DINVSituacion.FindByAll("lActivo = 1",
					" Order by cDscSituacion");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Obtención de los valores de las Cantidades
		vRegistro = this.getValores(ivAnio, ivMdoTransporte);
		Rep1.comEligeHoja(1);
		Rep1.comDespliega("D", 9, "REPORTE DE ACCIDENTES OCURRIDOS");

		if (!vEntidad.isEmpty()) {
			for (int i = 0; i < vEntidad.size(); i++) {
				TVINVSituacion VINVSituacion = new TVINVSituacion();
				VINVSituacion = (TVINVSituacion) vEntidad.get(i);
				ivEnero = 0;
				ivFebrero = 0;
				ivMarzo = 0;
				ivAbril = 0;
				ivMayo = 0;
				ivJunio = 0;
				ivJulio = 0;
				ivAgosto = 0;
				ivSeptiembre = 0;
				ivOctubre = 0;
				ivNoviembre = 0;
				ivDiciembre = 0;
				for (int j = 0; j < vRegistro.size(); j++) {
					TVINVRegistro VINVRegistro = new TVINVRegistro();
					VINVRegistro = (TVINVRegistro) vRegistro.get(j);
					if (VINVRegistro.getICveEstado() == VINVSituacion
							.getICveSituacion()) {
						if (VINVRegistro.getIIDDGPMPT() == 1)
							ivEnero = VINVRegistro.getICveMotivo(); // Valor
																	// count que
																	// envia la
																	// BD.
						if (VINVRegistro.getIIDDGPMPT() == 2)
							ivFebrero = VINVRegistro.getICveMotivo(); // Valor
																		// count
																		// que
																		// envia
																		// la
																		// BD.
						if (VINVRegistro.getIIDDGPMPT() == 3)
							ivMarzo = VINVRegistro.getICveMotivo(); // Valor
																	// count que
																	// envia la
																	// BD.
						if (VINVRegistro.getIIDDGPMPT() == 4)
							ivAbril = VINVRegistro.getICveMotivo(); // Valor
																	// count que
																	// envia la
																	// BD.
						if (VINVRegistro.getIIDDGPMPT() == 5)
							ivMayo = VINVRegistro.getICveMotivo(); // Valor
																	// count que
																	// envia la
																	// BD.
						if (VINVRegistro.getIIDDGPMPT() == 6)
							ivJunio = VINVRegistro.getICveMotivo(); // Valor
																	// count que
																	// envia la
																	// BD.
						if (VINVRegistro.getIIDDGPMPT() == 7)
							ivJulio = VINVRegistro.getICveMotivo(); // Valor
																	// count que
																	// envia la
																	// BD.
						if (VINVRegistro.getIIDDGPMPT() == 8)
							ivAgosto = VINVRegistro.getICveMotivo(); // Valor
																		// count
																		// que
																		// envia
																		// la
																		// BD.
						if (VINVRegistro.getIIDDGPMPT() == 9)
							ivSeptiembre = VINVRegistro.getICveMotivo(); // Valor
																			// count
																			// que
																			// envia
																			// la
																			// BD.
						if (VINVRegistro.getIIDDGPMPT() == 10)
							ivOctubre = VINVRegistro.getICveMotivo(); // Valor
																		// count
																		// que
																		// envia
																		// la
																		// BD.
						if (VINVRegistro.getIIDDGPMPT() == 11)
							ivNoviembre = VINVRegistro.getICveMotivo(); // Valor
																		// count
																		// que
																		// envia
																		// la
																		// BD.
						if (VINVRegistro.getIIDDGPMPT() == 12)
							ivDiciembre = VINVRegistro.getICveMotivo(); // Valor
																		// count
																		// que
																		// envia
																		// la
																		// BD.
					}
				}
				Rep1.comEligeHoja(1);
				Rep1.comDespliega("C", ivInicial,
						"" + VINVSituacion.getCDscSituacion());
				Rep1.comDespliega("D", ivInicial, "" + ivEnero);
				Rep1.comDespliega("E", ivInicial, "" + ivFebrero);
				Rep1.comDespliega("F", ivInicial, "" + ivMarzo);
				Rep1.comDespliega("G", ivInicial, "" + ivAbril);
				Rep1.comDespliega("H", ivInicial, "" + ivMayo);
				Rep1.comDespliega("I", ivInicial, "" + ivJunio);
				Rep1.comDespliega("J", ivInicial, "" + ivJulio);
				Rep1.comDespliega("K", ivInicial, "" + ivAgosto);
				Rep1.comDespliega("L", ivInicial, "" + ivSeptiembre);
				Rep1.comDespliega("M", ivInicial, "" + ivOctubre);
				Rep1.comDespliega("N", ivInicial, "" + ivNoviembre);
				Rep1.comDespliega("O", ivInicial, "" + ivDiciembre);
				ivTotUniMed = ivEnero + ivFebrero + ivMarzo + ivAbril + ivMayo
						+ ivJunio + ivJulio + ivAgosto + ivSeptiembre
						+ ivOctubre + ivNoviembre + ivDiciembre;
				Rep1.comDespliega("P", ivInicial, "" + ivTotUniMed);

				ivInicial = ivInicial + 1;

				Rep1.comEligeHoja(2);
				Rep1.comDespliega("A", ivIniHoja2, "" + ivEnero);
				Rep1.comDespliega("B", ivIniHoja2, "" + ivFebrero);
				Rep1.comDespliega("C", ivIniHoja2, "" + ivMarzo);
				Rep1.comDespliega("D", ivIniHoja2, "" + ivAbril);
				Rep1.comDespliega("E", ivIniHoja2, "" + ivMayo);
				Rep1.comDespliega("F", ivIniHoja2, "" + ivJunio);
				Rep1.comDespliega("G", ivIniHoja2, "" + ivJulio);
				Rep1.comDespliega("H", ivIniHoja2, "" + ivAgosto);
				Rep1.comDespliega("I", ivIniHoja2, "" + ivSeptiembre);
				Rep1.comDespliega("J", ivIniHoja2, "" + ivOctubre);
				Rep1.comDespliega("K", ivIniHoja2, "" + ivNoviembre);
				Rep1.comDespliega("L", ivIniHoja2, "" + ivDiciembre);
				Rep1.comDespliega("N", ivIniHoja2,
						"" + VINVSituacion.getCDscSituacion());

				ivIniHoja2 = ivIniHoja2 + 1;

				// Acumulación de los Totales.
				ivTotEnero = ivTotEnero + ivEnero;
				ivTotFebrero = ivTotFebrero + ivFebrero;
				ivTotMarzo = ivTotMarzo + ivMarzo;
				ivTotAbril = ivTotAbril + ivAbril;
				ivTotMayo = ivTotMayo + ivMayo;
				ivTotJunio = ivTotJunio + ivJunio;
				ivTotJulio = ivTotJulio + ivJulio;
				ivTotAgosto = ivTotAgosto + ivAgosto;
				ivTotSeptiembre = ivTotSeptiembre + ivSeptiembre;
				ivTotOctubre = ivTotOctubre + ivOctubre;
				ivTotNoviembre = ivTotNoviembre + ivNoviembre;
				ivTotDiciembre = ivTotDiciembre + ivDiciembre;
			}
		}
		Rep1.comEligeHoja(1);
		Rep1.comDespliega("C", ivInicial, "" + "T O T A L:");
		Rep1.comDespliega("D", ivInicial, "" + ivTotEnero);
		Rep1.comDespliega("E", ivInicial, "" + ivTotFebrero);
		Rep1.comDespliega("F", ivInicial, "" + ivTotMarzo);
		Rep1.comDespliega("G", ivInicial, "" + ivTotAbril);
		Rep1.comDespliega("H", ivInicial, "" + ivTotMayo);
		Rep1.comDespliega("I", ivInicial, "" + ivTotJunio);
		Rep1.comDespliega("J", ivInicial, "" + ivTotJulio);
		Rep1.comDespliega("K", ivInicial, "" + ivTotAgosto);
		Rep1.comDespliega("L", ivInicial, "" + ivTotSeptiembre);
		Rep1.comDespliega("M", ivInicial, "" + ivTotOctubre);
		Rep1.comDespliega("N", ivInicial, "" + ivTotNoviembre);
		Rep1.comDespliega("O", ivInicial, "" + ivTotDiciembre);
		ivTotGeneral = ivTotEnero + ivTotFebrero + ivTotMarzo + ivTotAbril
				+ ivTotMayo + ivTotJunio + ivTotJulio + ivTotAgosto
				+ ivTotSeptiembre + ivTotOctubre + ivTotNoviembre
				+ ivTotDiciembre;
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