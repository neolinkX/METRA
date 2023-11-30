package gob.sct.medprev;

import java.util.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * Clase de configuracion para Clase de configuracion para Listado de
 * EXPExamAplica
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070105000CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070105000CFG.png'>
 */
public class pg070106035CFG extends CFGListBase2 {
	private StringBuffer sbReporte = new StringBuffer();

	public pg070106035CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton")
							.equals("Generar Reporte")) {
				Vector vHead;
				Vector vBody;

				String cCveUniMed = "";
				String cCveModulo = "";
				String cCveProceso = "";
				String cDesde = "";
				String cHasta = "";

				if (request.getParameter("iCveUniMed") != null
						&& request.getParameter("iCveUniMed").trim().length() > 0
						&& Integer.parseInt(request.getParameter("iCveUniMed")) > 0)
					cCveUniMed = request.getParameter("iCveUniMed");
				if (request.getParameter("iCveModulo") != null
						&& request.getParameter("iCveModulo").trim().length() > 0
						&& Integer.parseInt(request.getParameter("iCveModulo")) > 0)
					cCveModulo = request.getParameter("iCveModulo");
				if (request.getParameter("iCveProceso") != null
						&& request.getParameter("iCveProceso").trim().length() > 0
						&& Integer
								.parseInt(request.getParameter("iCveProceso")) > 0)
					cCveProceso = request.getParameter("iCveProceso");
				if (request.getParameter("dtDesde") != null
						&& request.getParameter("dtDesde").trim().length() > 0) {
					cDesde = ""
							+ new TFechas().getDateSQL(request
									.getParameter("dtDesde"));
				}
				if (request.getParameter("dtHasta") != null
						&& request.getParameter("dtHasta").trim().length() > 0) {
					cHasta = ""
							+ new TFechas().getDateSQL(request
									.getParameter("dtHasta"));
				}

				// vHead = new TDEXPExamAplica().FindProdDictamen(cCveUniMed,
				// cCveModulo, cCveProceso, cDesde, cHasta);
				vHead = new TDEXPExamAplica().FindProdDictamen3(cCveUniMed,
						cCveModulo, cCveProceso, cDesde, cHasta);
				vBody = new TDEXPExamAplica().FindProdDictamen2(cCveUniMed,
						cCveModulo, cCveProceso, cDesde, cHasta);

				if (vHead != null && vHead.size() > 0 && vBody != null
						&& vBody.size() > 0) {
					this.setExcel(vHead, vBody);
				}
				// iNumReg = vDespliega.size();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}

	private void setExcel(Vector vA, Vector vB) {
		try {
			TExcel repExcel = new TExcel("07");
			int iRow = 13;
			int iUser1 = 0;
			int iUser2 = 0;
			int iTabla1 = iRow;
			int iTabla2 = 0;
			for (int i = 0; i < vA.size(); i++) {
				TVEXPExamAplica tvEXPExamAplica = (TVEXPExamAplica) vA.get(i);
				repExcel.comDespliega("A", iRow,
						"" + tvEXPExamAplica.getDtSolicitado());
				repExcel.comDespliega("B", iRow,
						"" + tvEXPExamAplica.getIGenerados());
				repExcel.comDespliega("C", iRow,
						"" + tvEXPExamAplica.getIDictaminados());
				repExcel.comDespliega("D", iRow,
						"" + tvEXPExamAplica.getINoDictaminados());
				iTabla2 = iRow;
				iRow++;
			}

			repExcel.comDespliega("A", iRow, "TOTAL: ");
			repExcel.comDespliega("B", iRow, "=SUM(B" + iTabla1 + ":B"
					+ iTabla2 + ")");
			repExcel.comDespliega("C", iRow, "=SUM(C" + iTabla1 + ":C"
					+ iTabla2 + ")");
			repExcel.comDespliega("D", iRow, "=SUM(D" + iTabla1 + ":D"
					+ iTabla2 + ")");

			iRow++;
			iRow++;

			repExcel.comFillCells("A", iRow, "D", iRow, 16);
			repExcel.comFontBold("A", iRow, "D", iRow);

			repExcel.comDespliega("A", iRow, "FECHA SOLICITADO");
			repExcel.comDespliega("B", iRow, "CLAVE");
			repExcel.comDespliega("C", iRow, "DICTAMINADOR");
			repExcel.comDespliega("D", iRow, "DICTAMENES");
			iRow++;
			iTabla1 = iRow;
			int j = 0;
			for (j = 0; j < vB.size(); j++) {
				TVEXPExamAplica tvEXPExamAplica = (TVEXPExamAplica) vB.get(j);
				iUser2 = tvEXPExamAplica.getICveUsuDictamen();

				if (iUser1 != iUser2 && iUser1 != 0) {
					repExcel.comFillCells("D", iRow, "D", iRow, 15);
					repExcel.comDespliega("D", iRow, "=SUM(D" + iTabla1 + ":D"
							+ (iRow - 1) + ")");
					iTabla1 = iRow + 1;
					iRow++;
				}

				repExcel.comDespliega("A", iRow,
						"" + tvEXPExamAplica.getDtSolicitado());
				repExcel.comDespliega("B", iRow,
						"" + tvEXPExamAplica.getICveUsuDictamen());
				repExcel.comDespliega("C", iRow,
						tvEXPExamAplica.getCDictaminador());
				repExcel.comDespliega("D", iRow,
						"" + tvEXPExamAplica.getIDictamenes());

				iUser1 = tvEXPExamAplica.getICveUsuDictamen();
				iRow++;
			}
			if (j != 0) {
				repExcel.comFillCells("D", iRow, "D", iRow, 15);
				repExcel.comDespliega("D", iRow, "=SUM(D" + iTabla1 + ":D"
						+ (iRow - 1) + ")");
			}

			sbReporte.append(repExcel.creaActiveX("pg070106035",
					"Productividad Dictamenmes", false, false, true));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public StringBuffer getSbReporte() {
		return sbReporte;
	}
}