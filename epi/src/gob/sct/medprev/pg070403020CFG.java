package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para Control al Transporte - Periodos/Pla
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Oscar Castrej�n Adame.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070403020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070403020CFG.png'>
 */
public class pg070403020CFG extends CFGListBase2 {
	int ivAnio = 0;
	int ivMdoTransporte = 0;
	public Vector vAccidentes = new Vector();
	private StringBuffer activeX = new StringBuffer("");

	public pg070403020CFG() {
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
			vAccidentes = DINVRegistro.CountByAnioPaisEdo(ivAnio,
					ivMdoTransporte, 1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vAccidentes;
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLEntidadFed DGRLEntidadFed = new TDGRLEntidadFed();
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

			vDespliega = DGRLEntidadFed.FindByAll(" iCvePais = 1 ",
					" Order by cNombre");

			if (!vDespliega.isEmpty()) {
				iNumReg = vDespliega.size() + 1;
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public void Enviar() {
		TExcel Rep1 = new TExcel("07");
		TDGRLEntidadFed DGRLEntidadFed = new TDGRLEntidadFed();
		Vector vEntidad = new Vector();
		Vector vRegistro = new Vector();
		TFechas Fecha = new TFechas("07");
		String cPlantilla = "pg070403020";
		int ivEnero = 0, ivFebrero = 0, ivMarzo = 0, ivAbril = 0, ivMayo = 0, ivJunio = 0;
		int ivJulio = 0, ivAgosto = 0, ivSeptiembre = 0, ivOctubre = 0, ivNoviembre = 0, ivDiciembre = 0;
		int ivTotEnero = 0, ivTotFebrero = 0, ivTotMarzo = 0, ivTotAbril = 0, ivTotMayo = 0, ivTotJunio = 0;
		int ivTotJulio = 0, ivTotAgosto = 0, ivTotSeptiembre = 0, ivTotOctubre = 0, ivTotNoviembre = 0, ivTotDiciembre = 0;
		int ivInicial = 12; // Valor del Renglon Inicial del Reporte.
		int ivTotUniMed = 0;
		int ivTotGeneral = 0;

		// Obtenci�n de los Valores de los Estados.
		try {
			vEntidad = DGRLEntidadFed.FindByAll(" iCvePais = 1 ",
					" Order by cNombre");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Obtenci�n de los valores de las Cantidades
		vRegistro = this.getValores(ivAnio, ivMdoTransporte);
		Rep1.comEligeHoja(1);
		Rep1.comDespliega("D", 9, "REPORTE DE ACCIDENTES OCURRIDOS");

		if (!vEntidad.isEmpty()) {
			for (int i = 0; i < vEntidad.size(); i++) {
				TVGRLEntidadFed VGRLEntidadFed = new TVGRLEntidadFed();
				VGRLEntidadFed = (TVGRLEntidadFed) vEntidad.get(i);
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
					if (VINVRegistro.getICveEstado() == VGRLEntidadFed
							.getICveEntidadFed()) {
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
				Rep1.comDespliega("C", ivInicial,
						"" + VGRLEntidadFed.getCNombre());
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
				// Acumulaci�n de los Totales.
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

		// Elije la Hoja 2, para la Gr�fica.
		Rep1.comEligeHoja(2);
		Rep1.comDespliega("A", 3, "" + ivTotEnero);
		Rep1.comDespliega("B", 3, "" + ivTotFebrero);
		Rep1.comDespliega("C", 3, "" + ivTotMarzo);
		Rep1.comDespliega("D", 3, "" + ivTotAbril);
		Rep1.comDespliega("E", 3, "" + ivTotMayo);
		Rep1.comDespliega("F", 3, "" + ivTotJunio);
		Rep1.comDespliega("G", 3, "" + ivTotJulio);
		Rep1.comDespliega("H", 3, "" + ivTotAgosto);
		Rep1.comDespliega("I", 3, "" + ivTotSeptiembre);
		Rep1.comDespliega("J", 3, "" + ivTotOctubre);
		Rep1.comDespliega("K", 3, "" + ivTotNoviembre);
		Rep1.comDespliega("L", 3, "" + ivTotDiciembre);
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