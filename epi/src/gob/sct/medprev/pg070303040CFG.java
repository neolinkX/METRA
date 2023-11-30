package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.TExcel;
import com.micper.util.TFechas;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.Vector;
import com.micper.util.TNumeros;

//import java.lang.*;

/**
 * * Clase de configuracion para CFG de la pagina pg07030304
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg07030304CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg07030304CFG.png'>
 */
public class pg070303040CFG extends CFGListBase2 {
	public pg070303040CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";

	}

	private String cFiltro = "";
	private String cFiltroRep = "";
	private StringBuffer activeX = new StringBuffer("");
	private StringBuffer activeX1 = new StringBuffer("");
	private StringBuffer activeX2 = new StringBuffer("");
	TDTOXAnalisis dTOXAnalisis = new TDTOXAnalisis();

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveLoteCualita") != null) {
				cFiltro = " where toxexamanalisis.ianio = "
						+ request.getParameter("iAnio")
						+ " and toxexamanalisis.icvelaboratorio = "
						+ request.getParameter("iCveUniMed")
						+ " and toxexamanalisis.iCveLoteCualita = "
						+ request.getParameter("iCveLoteCualita")
						+ " and toxexamanalisis.iCveExamCualita = "
						+ request.getParameter("iCveExamCualita");
				cFiltroRep = " where A.ianio = "
						+ request.getParameter("iAnio")
						+ "   and A.icvelaboratorio = "
						+ request.getParameter("iCveUniMed")
						+ "   and A.iCveLoteCualita = "
						+ request.getParameter("iCveLoteCualita")
						+ "   and A.iCveExamCualita = "
						+ request.getParameter("iCveExamCualita");
			}

			if (cCondicion.compareToIgnoreCase("") != 0) {
				cFiltro += " and " + cCondicion;
				// cFiltroRep = cFiltro;
			}

			if (cOrden.compareToIgnoreCase("") != 0)
				cFiltro += cOrden;

			if (request.getParameter("iCveLoteCualita") != null)
				vDespliega = dTOXAnalisis.FindByAll4(cFiltro);

			if (request.getParameter("hdBoton").compareToIgnoreCase(
					"Generar Reporte") == 0) {
				activeX.append(this.GenRep());
			} else if (request.getParameter("hdBoton").compareToIgnoreCase(
					"Controles") == 0) {
				activeX2.append(this.GenControles());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}

	public StringBuffer GenRep() {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas();
		String cNomArchivo = new String("Lote_");
		int iRenI = 8;

		StringBuffer buffer = new StringBuffer();
		try {
			TDTOXExamenCualita dTOXExamenCualita = new TDTOXExamenCualita();
			Vector vReporte = new Vector();
			vReporte = dTOXExamenCualita.RepLote(cFiltroRep);
			if (vReporte.size() == 0) {
				vErrores.acumulaError("", 15, "");
				return buffer;
			}
			TVTOXExamenCualita VExamen = new TVTOXExamenCualita();
			VExamen = (TVTOXExamenCualita) vReporte.get(0);
			// Presentar informaci�n del equipo
			xl.comDespliega("C", iRenI, VExamen.VEquipo.getCDscEquipo());
			xl.comDespliega("F", iRenI++, VExamen.getCLote());
			cNomArchivo += VExamen.getCLote().replace('/', '-');
			cNomArchivo.replaceAll("/", "-");
			xl.comDespliega("C", iRenI, "'" + VExamen.VEquipo.getCModelo());
			xl.comDespliega("F", iRenI++,
					"'" + pFecha.getFechaDDMMYYYY(VExamen.getDtEntrega(), "/"));
			xl.comDespliega("C", iRenI++, "'" + VExamen.VEquipo.getCNumSerie());
			xl.comDespliega("F", iRenI++, "" + VExamen.getCAnalisis());
			VExamen = (TVTOXExamenCualita) vReporte.get(1);
			xl.comDespliega("F", iRenI++, "" + VExamen.getCAnalisis());
			xl.comDespliega("F", iRenI, "" + VExamen.getIPosicion());
		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Exception in Generar Reporte : " +
			// ex.getMessage());
		}
		buffer = xl.creaActiveX("pg070303040", cNomArchivo, false, false, true);
		return buffer;
	}

	public StringBuffer GenControles() {
		TExcel xl;
		TFechas pFecha = new TFechas();
		String cNomArchivo = new String("Lote_");
		int iReng, iRengE = 1, iHoja = 3;
		StringBuffer buffer = new StringBuffer();
		Vector vReporte = new Vector();
		TVTOXAnalisisCtrol VAnCtrol;
		try {
			// Generaci�n del reporte.
			vReporte = dTOXAnalisis.FindControl(cFiltroRep);
			if (vReporte.size() == 0) {
				vErrores.acumulaError("", 15, "");
				return buffer;
			}
			xl = new TExcel("07");
			TNumeros Numeros = new TNumeros();
			VAnCtrol = new TVTOXAnalisisCtrol();
			xl.comEligeHoja(2);
			// Generaci�n del reporte.
			for (int i = 0; i < vReporte.size(); i++) {
				VAnCtrol = (TVTOXAnalisisCtrol) vReporte.get(i);
				// Generar Etiqueta
				xl.comDespliega("A", iRengE, VAnCtrol.VExamen.getCAnalisis());
				// Generar Formato de Control
				if (VAnCtrol.getLControl() == 1
						&& VAnCtrol.VCtrol.getCDscBreve() != null) {
					xl.comDespliega("D", iRengE, "=A" + iRengE);
					// Copiar hoja para formato de control
					xl.comCopiaHoja(1, xl.getAT_POSFINAL(),
							VAnCtrol.VExamen.getCAnalisis());
					// String.valueOf(VAnCtrol.VExamen.getICveAnalisis()));
					xl.comEligeHoja(iHoja++);
					// Desplegar informaci�n
					iReng = 10;
					// xl.comDespliega("B", iReng,
					// VAnCtrol.VCtrol.getCDscEmpleoCalib() + " " +
					// VAnCtrol.VCtrol.getCDscBreve());
					xl.comDespliega("B", iReng, VAnCtrol.VCtrol.getCDscBreve());
					xl.comDespliega("G", iReng, VAnCtrol.VExamen.getCAnalisis());
					xl.comDespliega("B", iReng += 5,
							VAnCtrol.VExamen.getCLote());
					xl.comDespliega(
							"B",
							iReng += 5,
							"'"
									+ Numeros.getNumeroSinSeparador(
											new Integer(VAnCtrol.VExamen
													.getICarrusel()), 2)
									+ " - "
									+ Numeros.getNumeroSinSeparador(
											new Integer(VAnCtrol.VExamen
													.getIPosicion()), 3));
					xl.comDespliega("B", iReng += 5, VAnCtrol.VCtrol.getCLote());
					xl.comDespliega("B", iReng += 2,
							VAnCtrol.VCtrol.getCDscCtrolCalibra());
					xl.comDespliega(
							"B",
							iReng += 2,
							"SUSTANCIA PSICOTR�PICA: "
									+ VAnCtrol.VCtrol.getCDscSustancia()
									+ " -- CONCENTRACI�N: "
									+ VAnCtrol.VCtrol.getDConcentracion()
									+ " ng/ml");
					xl.comEligeHoja(2);
				} else {
					xl.comDespliega("D", iRengE, "=A" + iRengE);
					xl.comDespliega("G", iRengE, "=A" + iRengE);
					xl.comDespliega("J", iRengE, "=A" + iRengE);
					xl.comDespliega("M", iRengE, "=A" + iRengE);
				}
				if ((i + 1) % 14 == 0)
					iRengE += 17;
				else if ((i + 1) % 2 == 0)
					iRengE++;
				iRengE++;
			} // / for
			cNomArchivo += VAnCtrol.VExamen.getCLote().replace('/', '-');
			cNomArchivo.replaceAll("/", "-");
			cNomArchivo += "_Ctrol";
			buffer = xl.creaActiveX("pg070303040C", cNomArchivo, false, false,
					true);
		} // / try
		catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Exception en Generar Reporte Controles: " +
			// ex.getMessage());
		}
		return buffer;
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public String getControles() {
		return activeX2.toString();
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

}