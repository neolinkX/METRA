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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070403040CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070403040CFG.png'>
 */
public class pg070403040CFG extends CFGListBase2 {
	int ivAnio = 0;
	int ivMdoTransporte = 0;
	public Vector vAccidentes = new Vector();
	private StringBuffer activeX = new StringBuffer("");

	public pg070403040CFG() {
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

	public Vector getValores(int ivAnio) {
		TDINVRegistro DINVRegistro = new TDINVRegistro();

		try {
			vAccidentes = DINVRegistro.CountByAnioDiaSemana(ivAnio);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vAccidentes;
	}

	/**
	 * Metodo FillVector
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
		Vector vRegistro = new Vector();
		TFechas Fecha = new TFechas("07");
		String cPlantilla = "pg070403040";
		int ivDomingo = 0, ivLunes = 0, ivMartes = 0, ivMiercoles = 0, ivJueves = 0, ivViernes = 0, ivSabado = 0;
		int ivTotDomingo = 0, ivTotLunes = 0, ivTotMartes = 0, ivTotMiercoles = 0, ivTotJueves = 0, ivTotViernes = 0;
		int ivTotSabado = 0;
		int ivInicial = 12; // Valor del Renglon Inicial del Reporte.
		int ivIniPagina2 = 3;
		int ivTotUniMed = 0;
		int ivTotGeneral = 0;

		// Obtenci�n de los Valores de los Estados.
		try {
			vEntidad = DGRLMdoTrans.findByAll(" where lActivo = 1 ",
					" Order by cDscMdoTrans");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Obtenci�n de los valores de las Cantidades
		vRegistro = this.getValores(ivAnio);
		Rep1.comEligeHoja(1);
		Rep1.comDespliega("D", 9, "REPORTE DE ACCIDENTES OCURRIDOS");

		if (!vEntidad.isEmpty()) {
			for (int i = 0; i < vEntidad.size(); i++) {
				TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
				VGRLMdoTrans = (TVGRLMdoTrans) vEntidad.get(i);
				ivDomingo = 0;
				ivLunes = 0;
				ivMartes = 0;
				ivMiercoles = 0;
				ivJueves = 0;
				ivViernes = 0;
				ivSabado = 0;
				for (int j = 0; j < vRegistro.size(); j++) {
					TVINVRegistro VINVRegistro = new TVINVRegistro();
					VINVRegistro = (TVINVRegistro) vRegistro.get(j);
					if (VINVRegistro.getICveMdoTrans() == VGRLMdoTrans
							.getICveMdoTrans()) {
						if (VINVRegistro.getIIDDGPMPT() == 1)
							ivDomingo = VINVRegistro.getICveMotivo(); // Valor
																		// count
																		// que
																		// envia
																		// la
																		// BD.
						if (VINVRegistro.getIIDDGPMPT() == 2)
							ivLunes = VINVRegistro.getICveMotivo(); // Valor
																	// count que
																	// envia la
																	// BD.
						if (VINVRegistro.getIIDDGPMPT() == 3)
							ivMartes = VINVRegistro.getICveMotivo(); // Valor
																		// count
																		// que
																		// envia
																		// la
																		// BD.
						if (VINVRegistro.getIIDDGPMPT() == 4)
							ivMiercoles = VINVRegistro.getICveMotivo(); // Valor
																		// count
																		// que
																		// envia
																		// la
																		// BD.
						if (VINVRegistro.getIIDDGPMPT() == 5)
							ivJueves = VINVRegistro.getICveMotivo(); // Valor
																		// count
																		// que
																		// envia
																		// la
																		// BD.
						if (VINVRegistro.getIIDDGPMPT() == 6)
							ivViernes = VINVRegistro.getICveMotivo(); // Valor
																		// count
																		// que
																		// envia
																		// la
																		// BD.
						if (VINVRegistro.getIIDDGPMPT() == 7)
							ivSabado = VINVRegistro.getICveMotivo(); // Valor
																		// count
																		// que
																		// envia
																		// la
																		// BD.
					}
				}
				Rep1.comEligeHoja(1);
				Rep1.comDespliega("C", ivInicial,
						"" + VGRLMdoTrans.getCDscMdoTrans());
				Rep1.comDespliega("D", ivInicial, "" + ivDomingo);
				Rep1.comDespliega("E", ivInicial, "" + ivLunes);
				Rep1.comDespliega("F", ivInicial, "" + ivMartes);
				Rep1.comDespliega("G", ivInicial, "" + ivMiercoles);
				Rep1.comDespliega("H", ivInicial, "" + ivJueves);
				Rep1.comDespliega("I", ivInicial, "" + ivViernes);
				Rep1.comDespliega("J", ivInicial, "" + ivSabado);
				ivTotUniMed = ivDomingo + ivLunes + ivMartes + ivMiercoles
						+ ivJueves + ivViernes + ivSabado;
				Rep1.comDespliega("P", ivInicial, "" + ivTotUniMed);
				ivInicial = ivInicial + 1;

				// Elije la Hoja 2, para la Gr�fica.
				Rep1.comEligeHoja(2);
				Rep1.comDespliega("A", ivIniPagina2, "" + ivTotDomingo);
				Rep1.comDespliega("B", ivIniPagina2, "" + ivTotLunes);
				Rep1.comDespliega("C", ivIniPagina2, "" + ivTotMartes);
				Rep1.comDespliega("D", ivIniPagina2, "" + ivTotMiercoles);
				Rep1.comDespliega("E", ivIniPagina2, "" + ivTotJueves);
				Rep1.comDespliega("F", ivIniPagina2, "" + ivTotViernes);
				Rep1.comDespliega("G", ivIniPagina2, "" + ivTotSabado);
				ivIniPagina2 = ivIniPagina2 + 2;

				// Acumulaci�n de los Totales.
				ivTotDomingo = ivTotDomingo + ivDomingo;
				ivTotLunes = ivTotLunes + ivLunes;
				ivTotMartes = ivTotMartes + ivMartes;
				ivTotMiercoles = ivTotMiercoles + ivMiercoles;
				ivTotJueves = ivTotJueves + ivJueves;
				ivTotViernes = ivTotViernes + ivViernes;
				ivTotSabado = ivTotSabado + ivSabado;
			}
		}
		Rep1.comEligeHoja(1);
		Rep1.comDespliega("C", ivInicial, "" + "T O T A L:");
		Rep1.comDespliega("D", ivInicial, "" + ivTotDomingo);
		Rep1.comDespliega("E", ivInicial, "" + ivTotLunes);
		Rep1.comDespliega("F", ivInicial, "" + ivTotMartes);
		Rep1.comDespliega("G", ivInicial, "" + ivTotMiercoles);
		Rep1.comDespliega("H", ivInicial, "" + ivTotJueves);
		Rep1.comDespliega("I", ivInicial, "" + ivTotViernes);
		Rep1.comDespliega("J", ivInicial, "" + ivTotSabado);
		ivTotGeneral = ivTotDomingo + ivTotLunes + ivTotMartes + ivTotMiercoles
				+ ivTotJueves + ivTotViernes + ivTotSabado;
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