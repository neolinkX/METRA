package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuración para Clase para el Listado de la tabla CTRPlantilla
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070503030.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070503030.java.png'>
 */
public class pg070503030CFG extends CFGListBase2 {
	public String cValSQL = "";
	public Vector vSeguimiento = new Vector();
	public Vector vSegUltPlantilas = new Vector();
	public Vector vPersonalUltEtapa = new Vector();
	public Vector vPersonalPenUltEtapa = new Vector();
	public boolean lEnviar = false;
	private StringBuffer activeX = new StringBuffer("");

	public pg070503030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		// cPaginas = "pg070803031.jsp|";
	}

	/**
	 * Método Principal de la Clase.
	 */
	public void mainBlock() {

		lEnviar = false;
		if (cAccion.compareToIgnoreCase("Enviar") == 0)
			lEnviar = true;
	}

	/**
	 * Método del Envio del Documento a Excel.
	 */
	public void Enviar() {
		TExcel Rep1 = new TExcel("07");
		Vector vPrincipal = new Vector();
		Vector vlocSeguimiento = new Vector();
		Vector vlocSegUltPlantillas = new Vector();
		Vector vlocPersonalUltEtapa = new Vector();
		Vector vlocPersonalPenUltEtapa = new Vector();
		String cEtapa = "";
		int ivPosicion = 12;
		int ivRegularizados = 0;
		int ivBajas = 0;
		int ivOperadores = 0;
		int ivOficios = 0;
		int ivTotRegularizados = 0;
		int ivTotBajas = 0;
		int ivTotOperadores = 0;
		int ivTotOficios = 0;
		String cPlantilla = "pg070503030";

		vPrincipal = vDespliega;
		vlocSeguimiento = vSeguimiento;
		vlocSegUltPlantillas = vSegUltPlantilas;
		vlocPersonalUltEtapa = vPersonalUltEtapa;
		vlocPersonalPenUltEtapa = vPersonalPenUltEtapa;

		if (!vlocPersonalUltEtapa.isEmpty()
				&& !vlocPersonalPenUltEtapa.isEmpty()) {
			ivBajas = 0;
			ivOperadores = 0;
			ivTotBajas = 0;
			for (int j = 0; j < vlocPersonalPenUltEtapa.size(); j++) {
				TVCTRPersonal VCTRPersonalPen = new TVCTRPersonal();
				VCTRPersonalPen = (TVCTRPersonal) vlocPersonalPenUltEtapa
						.get(j);

				for (int i = 0; i < vlocPersonalUltEtapa.size(); i++) {
					TVCTRPersonal VCTRPersonal = new TVCTRPersonal();
					VCTRPersonal = (TVCTRPersonal) vlocPersonalUltEtapa.get(i);
					if (j == 0) {
						if (VCTRPersonal.getlActivo() == 0)
							ivBajas = ivBajas + 1;
						if (VCTRPersonal.getlActivo() == 1)
							ivOperadores = ivOperadores + 1;
					}
				}
			}
		}

		if (!vPrincipal.isEmpty()) {
			for (int i = 0; i < vPrincipal.size(); i++) {
				TVGRLEtapa VGRLEtapa = new TVGRLEtapa();
				VGRLEtapa = (TVGRLEtapa) vPrincipal.get(i);

				cEtapa = VGRLEtapa.getcDscEtapa();

				// Regularizados.
				ivRegularizados = 0;
				if (!vlocSeguimiento.isEmpty()) {
					for (int ii = 0; ii < vlocSeguimiento.size(); ii++) {
						TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
						VCTRSeguimiento = (TVCTRSeguimiento) vlocSeguimiento
								.get(ii);
						if (VCTRSeguimiento.getiCveEtapa() == VGRLEtapa
								.getiCveEtapa())
							ivRegularizados = ivRegularizados + 1;
					}
				}

				// Calculo del Número de Oficios.
				ivOficios = 0;
				if (VGRLEtapa.getcDocumento() != null)
					if (VGRLEtapa.getcDocumento().toString()
							.compareToIgnoreCase("") != 0) {
						if (!vlocSegUltPlantillas.isEmpty()) {
							for (int iii = 0; iii < vlocSegUltPlantillas.size(); iii++) {
								TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
								VCTRSeguimiento = (TVCTRSeguimiento) vlocSegUltPlantillas
										.get(iii);
								if (VCTRSeguimiento.getiCveEtapa() == VGRLEtapa
										.getiCveEtapa())
									ivOficios = ivOficios + 1;
							}
						}
					}

				// Valores del Reporte.
				Rep1.comDespliega("C", ivPosicion, cEtapa); // Etapa
				Rep1.comDespliega("D", ivPosicion,
						new Integer(ivRegularizados).toString()); // Etapa

				if (VGRLEtapa.getiCveEtapa() == 2) {
					Rep1.comDespliega("E", ivPosicion,
							new Integer(ivBajas).toString()); // Bajas de
																// Operadores
					Rep1.comDespliega("F", ivPosicion,
							new Integer(ivOperadores).toString()); // Bajas de
																	// Operadores
				} else {
					Rep1.comDespliega("E", ivPosicion, "0"); // Bajas de
																// Operadores
					Rep1.comDespliega("F", ivPosicion, "0"); // Bajas de
																// Operadores
				}
				Rep1.comDespliega("G", ivPosicion,
						new Integer(ivOficios).toString()); // No. de Oficios
				ivPosicion = ivPosicion + 1;

				ivTotRegularizados = ivTotRegularizados + ivRegularizados;
				ivTotBajas = ivTotBajas + ivBajas;
				ivTotOficios = ivTotOficios + ivOficios;
			}
		}

		ivPosicion = ivPosicion + 1;
		Rep1.comDespliega("C", ivPosicion, "T O T A L: "); // Etapa
		Rep1.comDespliega("D", ivPosicion,
				new Integer(ivTotRegularizados).toString()); // Regualri
		Rep1.comDespliega("E", ivPosicion, new Integer(ivBajas).toString()); // Etapa
		Rep1.comDespliega("F", ivPosicion, new Integer(ivOperadores).toString()); // Etapa
		Rep1.comDespliega("G", ivPosicion, new Integer(ivTotOficios).toString()); // Etapa

		StringBuffer buffer = Rep1.creaActiveX(cPlantilla,
				"CtrTrans-Transportistas por Etapa", false, false, true);
		activeX.append(buffer);
	}

	public String getActiveX() {
		return activeX.toString();
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDGRLEtapa DGRLEtapa = new TDGRLEtapa();
		TDCTRSeguimiento DCTRSeguimiento = new TDCTRSeguimiento();
		TDCTRPersonal DCTRPersonal = new TDCTRPersonal();
		String cdtFechaIni = "";
		String cdtFechaFin = "";
		String cCond = "";
		int ivEtapaIni = 0;
		ivEtapaIni = Integer.parseInt(vParametros
				.getPropEspecifica("CTREtapaInicial"));
		int ivEtapa = 0;
		ivEtapa = Integer.parseInt(vParametros
				.getPropEspecifica("CTREtapaFinal"));

		try {

			if (request.getParameter("dtFechaIni") != null) {
				if (request.getParameter("dtFechaIni").toString()
						.compareToIgnoreCase("") != 0) {
					cdtFechaIni = " CTRSeguimiento.dtSolicitud >= '"
							+ request.getParameter("dtFechaIni").toString()
							+ "'";
					if (cCond.compareToIgnoreCase("") != 0)
						cCond = cCond + " and " + cdtFechaIni;
					else
						cCond = " where " + cdtFechaIni;
				}
			}

			if (request.getParameter("dtFechaFin") != null) {
				if (request.getParameter("dtFechaFin").toString()
						.compareToIgnoreCase("") != 0) {
					cdtFechaFin = " CTRSeguimiento.dtSolicitud >= '"
							+ request.getParameter("dtFechaFin").toString()
							+ "'";
					if (cCond.compareToIgnoreCase("") != 0)
						cCond = cCond + " and " + cdtFechaFin;
					else
						cCond = " where " + cdtFechaFin;
				}
			}

			if (cOrden.compareTo("") == 0)
				cOrden = cOrden + " order by GRLEtapa.iCveEtapa";

			if (cCondicion.compareToIgnoreCase("") != 0) {
				cCondicion = " where " + cCondicion;
			}

			if (cCondicion.compareToIgnoreCase("where") == 0)
				cCondicion = "";

			if (cCondicion.compareToIgnoreCase("") == 0)
				cCondicion = cCondicion + " where iCveProceso = 5 ";
			else
				cCondicion = cCondicion + " and iCveProceso = 5 ";

			cCondicion = cCondicion + " and iCveEtapa <> " + ivEtapaIni
					+ " and iCveEtapa <> " + ivEtapa;

			// Obtención del Seguimiento de las Empresas.
			vSeguimiento = DCTRSeguimiento
					.FindByUltimaEtapa(" where CTRSeguimiento.iCveEtapa <> "
							+ ivEtapaIni
							+ "   and CTRSeguimiento.iCveEtapa <> " + ivEtapa);

			// Obtención del Seguimiento de la Ultima plantilla de las Empresas
			vSegUltPlantilas = DCTRSeguimiento.FindByUltimaPlantilla();

			// Obtención del Personal de la Ultima Plantilla de cada Empresa.
			vPersonalUltEtapa = DCTRPersonal.FindByUltimaEtapa();

			// Obtención del Personal de la Penultima Plantilla de cada Empresa.
			vPersonalPenUltEtapa = DCTRPersonal.FindByPenultimaEtapa();

			vDespliega = DGRLEtapa.FindByAll(cCondicion + cOrden);

			if (!vDespliega.isEmpty()) {
				iNumReg = vDespliega.size() + 1;
			}

			if (lEnviar) {
				this.Enviar();
			}

			// cValSQL = DGRLEmpresas.cValSQL;

		} catch (Exception ex) {
			error("FillVector", ex);
		}

		if (!vDespliega.isEmpty()) {
			if (NavStatus.compareToIgnoreCase("Hidden") == 0)
				NavStatus = "FirstRecord";
		} else
			NavStatus = "Hidden";
	}
}
