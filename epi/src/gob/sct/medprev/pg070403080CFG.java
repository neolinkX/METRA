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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070403080CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070403080CFG.png'>
 */
public class pg070403080CFG extends CFGListBase2 {
	int ivAnio = 0;
	int ivMdoTransporte = 0;
	public Vector vAccidentes = new Vector();
	private StringBuffer activeX = new StringBuffer("");

	public pg070403080CFG() {
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
		TFechas Fecha = new TFechas();
		java.sql.Date dtHoy = Fecha.TodaySQL();

		try {
			vAccidentes = DINVRegistro.CountByAnioEdad(ivAnio, dtHoy);
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
		String cPlantilla = "pg070403080";
		int iv18 = 0, iv19 = 0, iv20 = 0, iv21 = 0, iv22 = 0, iv23 = 0, iv24 = 0, iv25 = 0, iv26 = 0, iv27 = 0, iv28 = 0, iv29 = 0, iv30 = 0;
		int iv31 = 0, iv32 = 0, iv33 = 0, iv34 = 0, iv35 = 0, iv36 = 0, iv37 = 0, iv38 = 0, iv39 = 0, iv40 = 0, iv41 = 0, iv42 = 0, iv43 = 0;
		int iv44 = 0, iv45 = 0, iv46 = 0, iv47 = 0, iv48 = 0, iv49 = 0, iv50 = 0, iv51 = 0, iv52 = 0, iv53 = 0, iv54 = 0, iv55 = 0, iv56 = 0;
		int iv57 = 0, iv58 = 0, iv59 = 0, iv60 = 0, ivMayor60 = 0, ivSeIgnora = 0;
		int ivTot18 = 0, ivTot19 = 0, ivTot20 = 0, ivTot21 = 0, ivTot22 = 0, ivTot23 = 0, ivTot24 = 0, ivTot25 = 0, ivTot26 = 0, ivTot27 = 0, ivTot28 = 0, ivTot29 = 0, ivTot30 = 0;
		int ivTot31 = 0, ivTot32 = 0, ivTot33 = 0, ivTot34 = 0, ivTot35 = 0, ivTot36 = 0, ivTot37 = 0, ivTot38 = 0, ivTot39 = 0, ivTot40 = 0, ivTot41 = 0, ivTot42 = 0, ivTot43 = 0;
		int ivTot44 = 0, ivTot45 = 0, ivTot46 = 0, ivTot47 = 0, ivTot48 = 0, ivTot49 = 0, ivTot50 = 0, ivTot51 = 0, ivTot52 = 0, ivTot53 = 0, ivTot54 = 0, ivTot55 = 0, ivTot56 = 0;
		int ivTot57 = 0, ivTot58 = 0, ivTot59 = 0, ivTot60 = 0, ivTotMayor60 = 0, ivTotSeIgnora = 0;
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
				iv18 = 0;
				iv19 = 0;
				iv20 = 0;
				iv21 = 0;
				iv22 = 0;
				iv23 = 0;
				iv24 = 0;
				iv25 = 0;
				iv26 = 0;
				iv27 = 0;
				iv28 = 0;
				iv29 = 0;
				iv30 = 0;
				iv31 = 0;
				iv32 = 0;
				iv33 = 0;
				iv34 = 0;
				iv35 = 0;
				iv36 = 0;
				iv37 = 0;
				iv38 = 0;
				iv39 = 0;
				iv40 = 0;
				iv41 = 0;
				iv42 = 0;
				iv43 = 0;
				iv44 = 0;
				iv45 = 0;
				iv46 = 0;
				iv47 = 0;
				iv48 = 0;
				iv49 = 0;
				iv50 = 0;
				iv51 = 0;
				iv52 = 0;
				iv53 = 0;
				iv54 = 0;
				iv55 = 0;
				iv56 = 0;
				iv57 = 0;
				iv58 = 0;
				iv59 = 0;
				iv60 = 0;
				ivMayor60 = 0;
				ivSeIgnora = 0;
				for (int j = 0; j < vRegistro.size(); j++) {
					TVINVRegistro VINVRegistro = new TVINVRegistro();
					VINVRegistro = (TVINVRegistro) vRegistro.get(j);
					if (VINVRegistro.getICveMdoTrans() == VGRLMdoTrans
							.getICveMdoTrans()) {
						if (VINVRegistro.getIIDDGPMPT() == 18)
							iv18 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 19)
							iv19 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 20)
							iv20 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 21)
							iv21 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 22)
							iv22 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 23)
							iv23 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 24)
							iv24 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 25)
							iv25 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 26)
							iv26 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 27)
							iv27 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 28)
							iv28 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 29)
							iv29 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 30)
							iv30 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 31)
							iv31 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 32)
							iv32 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 33)
							iv33 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 34)
							iv34 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 35)
							iv35 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 36)
							iv36 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 37)
							iv37 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 38)
							iv38 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 39)
							iv39 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 40)
							iv40 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 41)
							iv41 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 42)
							iv42 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 43)
							iv43 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 44)
							iv44 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 45)
							iv45 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 46)
							iv46 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 47)
							iv47 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 48)
							iv48 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 49)
							iv49 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 50)
							iv50 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 51)
							iv51 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 52)
							iv52 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 53)
							iv53 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 54)
							iv54 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 55)
							iv55 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 56)
							iv56 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 57)
							iv57 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 58)
							iv58 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 59)
							iv59 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() == 60)
							iv60 = VINVRegistro.getICveMotivo(); // Valor count
																	// que envia
																	// la BD.
						if (VINVRegistro.getIIDDGPMPT() > 60)
							ivMayor60 = VINVRegistro.getICveMotivo(); // Valor
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
				Rep1.comDespliega("D", ivInicial, "" + iv18);
				Rep1.comDespliega("E", ivInicial, "" + iv19);
				Rep1.comDespliega("F", ivInicial, "" + iv20);
				Rep1.comDespliega("G", ivInicial, "" + iv21);
				Rep1.comDespliega("H", ivInicial, "" + iv22);
				Rep1.comDespliega("I", ivInicial, "" + iv23);
				Rep1.comDespliega("J", ivInicial, "" + iv24);
				Rep1.comDespliega("K", ivInicial, "" + iv25);
				Rep1.comDespliega("L", ivInicial, "" + iv26);
				Rep1.comDespliega("M", ivInicial, "" + iv27);
				Rep1.comDespliega("N", ivInicial, "" + iv28);
				Rep1.comDespliega("O", ivInicial, "" + iv29);
				Rep1.comDespliega("P", ivInicial, "" + iv30);
				Rep1.comDespliega("Q", ivInicial, "" + iv31);
				Rep1.comDespliega("R", ivInicial, "" + iv32);
				Rep1.comDespliega("S", ivInicial, "" + iv33);
				Rep1.comDespliega("T", ivInicial, "" + iv34);
				Rep1.comDespliega("U", ivInicial, "" + iv35);
				Rep1.comDespliega("V", ivInicial, "" + iv36);
				Rep1.comDespliega("W", ivInicial, "" + iv37);
				Rep1.comDespliega("X", ivInicial, "" + iv38);
				Rep1.comDespliega("Y", ivInicial, "" + iv39);
				Rep1.comDespliega("Z", ivInicial, "" + iv40);
				Rep1.comDespliega("AA", ivInicial, "" + iv41);
				Rep1.comDespliega("AB", ivInicial, "" + iv42);
				Rep1.comDespliega("AC", ivInicial, "" + iv43);
				Rep1.comDespliega("AD", ivInicial, "" + iv44);
				Rep1.comDespliega("AE", ivInicial, "" + iv45);
				Rep1.comDespliega("AF", ivInicial, "" + iv46);
				Rep1.comDespliega("AG", ivInicial, "" + iv47);
				Rep1.comDespliega("AH", ivInicial, "" + iv48);
				Rep1.comDespliega("AI", ivInicial, "" + iv49);
				Rep1.comDespliega("AJ", ivInicial, "" + iv50);
				Rep1.comDespliega("AK", ivInicial, "" + iv51);
				Rep1.comDespliega("AL", ivInicial, "" + iv52);
				Rep1.comDespliega("AM", ivInicial, "" + iv53);
				Rep1.comDespliega("AN", ivInicial, "" + iv54);
				Rep1.comDespliega("AO", ivInicial, "" + iv55);
				Rep1.comDespliega("AP", ivInicial, "" + iv56);
				Rep1.comDespliega("AQ", ivInicial, "" + iv57);
				Rep1.comDespliega("AR", ivInicial, "" + iv58);
				Rep1.comDespliega("AS", ivInicial, "" + iv59);
				Rep1.comDespliega("AT", ivInicial, "" + iv60);
				Rep1.comDespliega("AU", ivInicial, "" + ivMayor60);
				Rep1.comDespliega("AV", ivInicial, "" + ivSeIgnora);
				ivTotUniMed = iv18 + iv19 + iv20 + iv21 + iv22 + iv23 + iv24
						+ iv25 + iv26 + iv27 + iv28 + iv29 + iv30 + iv31 + iv32
						+ iv33 + iv34 + iv35 + iv36 + iv37 + iv38 + iv39 + iv40
						+ iv41 + iv42 + iv43 + iv44 + iv45 + iv46 + iv47 + iv48
						+ iv49 + iv50 + iv51 + iv52 + iv53 + iv54 + iv55 + iv56
						+ iv57 + iv58 + iv59 + iv60 + ivMayor60 + ivSeIgnora;
				Rep1.comDespliega("AW", ivInicial, "" + ivTotUniMed);
				ivInicial = ivInicial + 1;

				// Elije la Hoja 2, para la Gr�fica.

				// Acumulaci�n de los Totales.
				ivTot18 = ivTot18 + iv18;
				ivTot19 = ivTot19 + iv19;
				ivTot20 = ivTot20 + iv20;
				ivTot21 = ivTot21 + iv21;
				ivTot22 = ivTot22 + iv22;
				ivTot23 = ivTot23 + iv23;
				ivTot24 = ivTot24 + iv24;
				ivTot25 = ivTot25 + iv25;
				ivTot26 = ivTot26 + iv26;
				ivTot27 = ivTot27 + iv27;
				ivTot28 = ivTot28 + iv28;
				ivTot29 = ivTot29 + iv29;
				ivTot30 = ivTot30 + iv30;
				ivTot31 = ivTot31 + iv31;
				ivTot32 = ivTot32 + iv32;
				ivTot33 = ivTot33 + iv33;
				ivTot34 = ivTot34 + iv34;
				ivTot35 = ivTot35 + iv35;
				ivTot36 = ivTot36 + iv36;
				ivTot37 = ivTot37 + iv37;
				ivTot38 = ivTot38 + iv38;
				ivTot39 = ivTot39 + iv39;
				ivTot40 = ivTot40 + iv40;
				ivTot41 = ivTot41 + iv41;
				ivTot42 = ivTot42 + iv42;
				ivTot43 = ivTot43 + iv43;
				ivTot44 = ivTot44 + iv44;
				ivTot45 = ivTot45 + iv45;
				ivTot46 = ivTot46 + iv46;
				ivTot47 = ivTot47 + iv47;
				ivTot48 = ivTot48 + iv48;
				ivTot49 = ivTot49 + iv49;
				ivTot50 = ivTot50 + iv50;
				ivTot51 = ivTot51 + iv51;
				ivTot52 = ivTot52 + iv52;
				ivTot53 = ivTot53 + iv53;
				ivTot54 = ivTot54 + iv54;
				ivTot55 = ivTot55 + iv55;
				ivTot56 = ivTot56 + iv56;
				ivTot57 = ivTot57 + iv57;
				ivTot58 = ivTot58 + iv58;
				ivTot59 = ivTot59 + iv59;
				ivTot60 = ivTot60 + iv60;
				ivTotMayor60 = ivTotMayor60 + ivMayor60;
				ivTotSeIgnora = ivTotSeIgnora + ivSeIgnora;
			}
		}
		Rep1.comEligeHoja(1);
		Rep1.comDespliega("C", ivInicial, "" + "T O T A L:");
		Rep1.comDespliega("D", ivInicial, "" + ivTot18);
		Rep1.comDespliega("E", ivInicial, "" + ivTot19);
		Rep1.comDespliega("F", ivInicial, "" + ivTot20);
		Rep1.comDespliega("G", ivInicial, "" + ivTot21);
		Rep1.comDespliega("H", ivInicial, "" + ivTot22);
		Rep1.comDespliega("I", ivInicial, "" + ivTot23);
		Rep1.comDespliega("J", ivInicial, "" + ivTot24);
		Rep1.comDespliega("K", ivInicial, "" + ivTot25);
		Rep1.comDespliega("L", ivInicial, "" + ivTot26);
		Rep1.comDespliega("M", ivInicial, "" + ivTot27);
		Rep1.comDespliega("N", ivInicial, "" + ivTot28);
		Rep1.comDespliega("O", ivInicial, "" + ivTot29);
		Rep1.comDespliega("P", ivInicial, "" + ivTot30);
		Rep1.comDespliega("Q", ivInicial, "" + ivTot31);
		Rep1.comDespliega("R", ivInicial, "" + ivTot32);
		Rep1.comDespliega("S", ivInicial, "" + ivTot33);
		Rep1.comDespliega("T", ivInicial, "" + ivTot34);
		Rep1.comDespliega("U", ivInicial, "" + ivTot35);
		Rep1.comDespliega("V", ivInicial, "" + ivTot36);
		Rep1.comDespliega("W", ivInicial, "" + ivTot37);
		Rep1.comDespliega("X", ivInicial, "" + ivTot38);
		Rep1.comDespliega("Y", ivInicial, "" + ivTot39);
		Rep1.comDespliega("Z", ivInicial, "" + ivTot40);
		Rep1.comDespliega("AA", ivInicial, "" + ivTot41);
		Rep1.comDespliega("AB", ivInicial, "" + ivTot42);
		Rep1.comDespliega("AC", ivInicial, "" + ivTot43);
		Rep1.comDespliega("AD", ivInicial, "" + ivTot44);
		Rep1.comDespliega("AE", ivInicial, "" + ivTot45);
		Rep1.comDespliega("AF", ivInicial, "" + ivTot46);
		Rep1.comDespliega("AG", ivInicial, "" + ivTot47);
		Rep1.comDespliega("AH", ivInicial, "" + ivTot48);
		Rep1.comDespliega("AI", ivInicial, "" + ivTot49);
		Rep1.comDespliega("AJ", ivInicial, "" + ivTot50);
		Rep1.comDespliega("AK", ivInicial, "" + ivTot51);
		Rep1.comDespliega("AL", ivInicial, "" + ivTot52);
		Rep1.comDespliega("AM", ivInicial, "" + ivTot53);
		Rep1.comDespliega("AN", ivInicial, "" + ivTot54);
		Rep1.comDespliega("AO", ivInicial, "" + ivTot55);
		Rep1.comDespliega("AP", ivInicial, "" + ivTot56);
		Rep1.comDespliega("AQ", ivInicial, "" + ivTot57);
		Rep1.comDespliega("AR", ivInicial, "" + ivTot58);
		Rep1.comDespliega("AS", ivInicial, "" + ivTot59);
		Rep1.comDespliega("AT", ivInicial, "" + ivTot60);
		Rep1.comDespliega("AU", ivInicial, "" + ivTotMayor60);
		Rep1.comDespliega("AV", ivInicial, "" + ivSeIgnora);
		ivTotGeneral = ivTot18 + ivTot19 + ivTot20 + ivTot21 + ivTot22
				+ ivTot23 + ivTot24 + ivTot25 + ivTot26 + ivTot27 + ivTot28
				+ ivTot29 + ivTot30 + ivTot31 + ivTot32 + ivTot33 + ivTot34
				+ ivTot35 + ivTot36 + ivTot37 + ivTot38 + ivTot39 + ivTot40
				+ ivTot41 + ivTot42 + ivTot43 + ivTot44 + ivTot45 + ivTot46
				+ ivTot47 + ivTot38 + ivTot49 + ivTot50 + ivTot51 + ivTot52
				+ ivTot53 + ivTot54 + ivTot55 + ivTot56 + ivTot57 + ivTot58
				+ ivTot59 + ivTot60 + ivTotMayor60 + ivSeIgnora;
		Rep1.comDespliega("AW", ivInicial, "" + ivTotGeneral);

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