package gob.sct.medprev;

import java.util.*;
import java.text.*;
import java.sql.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuraci�n para Handler de acciones para la p�gina
 * pg070104010
 * <p>
 * Administraci�n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Romeo Sanchez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104010.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104010.jsp.png'>
 */
public class pg070106025CFG extends CFGListBase2 {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private TFechas tf = new TFechas();
	private java.sql.Date d = null;
	private String fechaFormateada = "";
	private String fechaTmp = "";
	private StringBuffer sbReporte = new StringBuffer();

	/**
	 * M�todo que devuelve una fecha en el formato dd/MM/yyyy
	 * 
	 * @param fecha
	 *            un String con la fecha en formato yyyy-MM-dd
	 * @return un String con la fecha convertida al formato dd/MM/yyyy
	 */
	public String formatDate(String fecha) {
		if (fecha == null || fecha.indexOf("-") == -1)
			return "&nbsp;";
		d = tf.getSQLDatefromSQLString(fecha);
		fechaFormateada = sdf.format(d);
		return fechaFormateada;
	}

	public pg070106025CFG() {
		this.vParametros = new TParametro("07");
		// cPaginas = "pg070104021.jsp"; // Ir a...
	}

	/**
	 * M�todo que devuelve la lista de unidades m�dicas v�lidas
	 * 
	 * @return un Vector con los value objects de las unidades m�dicas
	 */
	public Vector getUniMedsValidas(String cCveProceso) {
		Vector vcUMValidas = null;
		int iCveProceso = 0;
		try {
			TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true)
					.getAttribute("UsrID");
			if (cCveProceso != null && !cCveProceso.equals("null")
					&& !cCveProceso.equals("undefined")
					&& !cCveProceso.equals("")) {
				iCveProceso = Integer.parseInt(cCveProceso);
			}

			vcUMValidas = vUsuario.getVUniFiltro(iCveProceso);
		} catch (Exception ex) {
			error("getUniMedsValidas", ex);
		}
		return vcUMValidas;
	}

	/**
	 * M�todo que devuelve todos los m�dulos correspondientes a la unidad
	 * m�dica indicada en el par�metro que recibe el m�todo.
	 * 
	 * @return un Vector con los m�dulos
	 */
	public Vector getModulos(String cCveUniMed) {
		Vector vcModulos = null;
		try {
			if (cCveUniMed != null && !cCveUniMed.equals("null")
					&& !cCveUniMed.equals("undefined")
					&& !cCveUniMed.equals("")) {
				vcModulos = new TDGRLModulo().getComboModulos(Integer
						.parseInt(cCveUniMed));
			} else {
				// � poner: "Seleccione..." ?
				vcModulos = new Vector();
			}
		} catch (Exception ex) {
			error("getModulos", ex);
		}
		return vcModulos;
	}

	/**
	 * M�todo FillVector
	 */
	public void fillVector() {
		try {
			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton")
							.equals("Generar Reporte")) {
				String cCveUniMed = "";
				String cCveModulo = "";
				String cCveServicio = "";
				String cDesde = "";
				String cHasta = "";
				String cFecha = "";
				String cOrdenar = "";
				String cCampo = "";

				if (request.getParameter("iCveUniMed") != null
						&& request.getParameter("iCveUniMed").trim().length() > 0
						&& Integer.parseInt(request.getParameter("iCveUniMed")) > 0)
					cCveUniMed = request.getParameter("iCveUniMed");
				if (request.getParameter("iCveModulo") != null
						&& request.getParameter("iCveModulo").trim().length() > 0
						&& Integer.parseInt(request.getParameter("iCveModulo")) > 0)
					cCveModulo = request.getParameter("iCveModulo");
				if (request.getParameter("iCveServicio") != null
						&& request.getParameter("iCveServicio").trim().length() > 0
						&& Integer.parseInt(request
								.getParameter("iCveServicio")) > 0)
					cCveServicio = request.getParameter("iCveServicio");
				if (request.getParameter("dtDesde") != null
						&& request.getParameter("dtDesde").trim().length() > 0
						&& request.getParameter("dtHasta") != null
						&& request.getParameter("dtHasta").trim().length() > 0) {

					cDesde = ""
							+ new TFechas().getDateSQL(request
									.getParameter("dtDesde"));
					cHasta = ""
							+ new TFechas().getDateSQL(request
									.getParameter("dtHasta"));
					cFecha = " BETWEEN '" + cDesde + "' AND '" + cHasta + "' ";
				}
				/*
				 * if(request.getParameter("rdFecha") != null &&
				 * Integer.parseInt(request.getParameter("rdFecha")) == 0){
				 * cCampo = " ea.dtSolicitado "; } else
				 * if(request.getParameter("rdFecha") != null &&
				 * Integer.parseInt(request.getParameter("rdFecha")) == 1){
				 * cCampo = " ea.dtDictamen "; }
				 */
				cCampo = " ec.dtDictaminado ";

				cFecha = cCampo + cFecha;

				if (request.getParameter("iOrden") != null
						&& Integer.parseInt(request.getParameter("iOrden")) == 0) {
					cOrdenar = cCampo + ", d.iCveExpediente ";
				} else if (request.getParameter("iOrden") != null
						&& Integer.parseInt(request.getParameter("iOrden")) == 1) {
					cOrdenar = cCampo
							+ ", d.cApPaterno, d.cApMaterno, d.cNombre  ";
				}

				cOrdenar += " ,ed.TIDIAGNOSTICO ";

				vDespliega = new TDExamAplica().FindByAll(cCveUniMed,
						cCveModulo, cCveServicio, cFecha, cOrdenar);

				if (vDespliega != null && vDespliega.size() > 0) {
					this.setExcel(vDespliega);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void setExcel(Vector vA) {
		try {
			TExcel repExcel = new TExcel("07");
			TFechas tFechas = new TFechas();
			GregorianCalendar cCalendar = new GregorianCalendar();
			repExcel.comFontBold("F", 4, "G", 4);
			repExcel.comDespliega("F", 4, "FECHA");
			repExcel.comDespliega("F", 5,
					tFechas.getFechaDDMMYYYY(tFechas.TodaySQL(), ""));
			repExcel.comDespliega("G", 4, "HORA");
			repExcel.comDespliega(
					"G",
					5,
					cCalendar.get(Calendar.HOUR_OF_DAY) + ":"
							+ cCalendar.get(Calendar.MINUTE) + ":"
							+ cCalendar.get(Calendar.SECOND));

			int iRow = 11;
			for (int i = 0; i < vA.size(); i++) {
				String cNombre = "";
				String cRFC = "";
				String cExpediente = "";
				String cFechaEx = "";
				String cFechaDict = "";
				String cCategoria = "";
				String cDictExamen = "";
				String cDictServicio = "";
				String cDiagnostico = "";
				String cTiDiagnostico = "";/*
											 * int iTmp1 = 0; int iTmp2 = 0;
											 */
				TVExamAplica tvExamAplica = (TVExamAplica) vA.get(i);
				// iTmp2 = tvExamAplica.getICveExpediente();
				if (tvExamAplica.getCNombre() != null)
					cNombre = tvExamAplica.getCNombre();
				if (tvExamAplica.getCApPaterno() != null)
					cNombre += " " + tvExamAplica.getCApPaterno();
				if (tvExamAplica.getCApMaterno() != null)
					cNombre += " " + tvExamAplica.getCApMaterno();
				if (tvExamAplica.getCRFC() != null)
					cRFC = tvExamAplica.getCRFC();
				if (tvExamAplica.getICveExpediente() != 0)
					cExpediente = "" + tvExamAplica.getICveExpediente();
				if (tvExamAplica.getDtSolicitado() != null)
					cFechaEx = ""
							+ tFechas.getFechaMMDDYYYY(
									tvExamAplica.getDtSolicitado(), "");
				if (tvExamAplica.getDtDictamen() != null)
					cFechaDict = ""
							+ tFechas.getFechaMMDDYYYY(
									tvExamAplica.getDtDictamen(), "");
				if (tvExamAplica.getCDscCategoria() != null)
					cCategoria = tvExamAplica.getCDscCategoria();
				if (tvExamAplica.getLDictExamen() == 1)
					cDictExamen = "SI";
				else
					cDictExamen = "NO";

				if (tvExamAplica.getLDictServicio() == 1)
					cDictServicio = "SI";
				else
					cDictServicio = "NO";

				if (tvExamAplica.getCDscBreve() != null)
					cDiagnostico = tvExamAplica.getCDscBreve();

				if (tvExamAplica.getCTIniExa() != null)
					cTiDiagnostico = tvExamAplica.getCTIniExa();

				repExcel.comDespliega("A", iRow, cNombre);
				repExcel.comDespliega("B", iRow, cRFC);
				repExcel.comDespliega("C", iRow, cExpediente);
				repExcel.comDespliega("D", iRow, cFechaEx);
				repExcel.comDespliega("E", iRow, cFechaDict);
				repExcel.comDespliega("F", iRow, cCategoria);
				repExcel.comDespliega("G", iRow, cDictExamen);
				repExcel.comDespliega("H", iRow, cDictServicio);
				repExcel.comDespliega("I", iRow, cDiagnostico);
				repExcel.comDespliega("J", iRow, cTiDiagnostico);
				/*
				 * if(iTmp1 != iTmp2 && iTmp1 != 0){ repExcel.comDespliega("I",
				 * iRow, cDiagnostico); cDiagnostico = ""; } iTmp1 =
				 * tvExamAplica.getICveExpediente();
				 */
				iRow++;
			}
			sbReporte.append(repExcel.creaActiveX("pg070106025",
					"Comportamiento Diario del Dictamen", false, false, true));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public StringBuffer getSbReporte() {
		return sbReporte;
	}
}