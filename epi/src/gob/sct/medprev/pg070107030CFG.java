package gob.sct.medprev;

import java.util.*;
import java.text.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.dwr.vo.ExpBitMod;
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
public class pg070107030CFG extends CFGListBase2 {
	private String fechaFormateada = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private java.sql.Date d = null;
	private TFechas tf = new TFechas();
	private int iFlag = 0;

	public pg070107030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {

			if (request.getParameter("hdBoton") != null
					&& (request.getParameter("hdBoton").equals("Buscar") || request
							.getParameter("hdBoton").equals("GuardarA"))) {

				TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
				HashMap p = new HashMap();
				java.sql.Date dtFecha = new java.sql.Date(new Date().getTime()); // fecha
																					// actual

				if (request.getParameter("iCveUniMed") != null
						&& request.getParameter("iCveUniMed").trim().length() > 0
						&& Integer.parseInt(request.getParameter("iCveUniMed")) > 0)
					p.put("iCveUniMed", request.getParameter("iCveUniMed"));

				if (request.getParameter("iCveModulo") != null
						&& request.getParameter("iCveModulo").trim().length() > 0
						&& Integer.parseInt(request.getParameter("iCveModulo")) > 0)
					p.put("iCveModulo", request.getParameter("iCveModulo"));

				if (request.getParameter("iCveProceso") != null
						&& request.getParameter("iCveProceso").trim().length() > 0
						&& Integer
								.parseInt(request.getParameter("iCveProceso")) > 0)
					p.put("iCveProceso", request.getParameter("iCveProceso"));

				if (request.getParameter("iCveExpediente") != null
						&& request.getParameter("iCveExpediente").trim()
								.length() > 0)
					p.put("iCveExpediente",
							request.getParameter("iCveExpediente"));

				p.put("dtFecha", dtFecha);
				p.put("lDictaminado", "0");
				vDespliega = dEXPExamAplica.buscaXLiberarServicio(p);

				// iNumReg = vDespliega.size();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}

	public void GuardarA() {
		try {
			if (request.getParameter("iCveExpediente") != null
					&& request.getParameter("iCveExpediente").trim().length() > 0
					&& request.getParameter("iNumExamen") != null
					&& request.getParameter("iNumExamen").trim().length() > 0) {
				int iUpdate = 0;

				int iMaxValue = Integer.parseInt(request
						.getParameter("maxValue"));
				for (int i = 0; i < iMaxValue; i++) {
					if (request.getParameter("id" + i) != null) {
						TVEXPExamCat tvExamCat = new TVEXPExamCat();

						tvExamCat.setICveExpediente(Integer.parseInt(request
								.getParameter("iCveExpediente")));
						tvExamCat.setINumExamen(Integer.parseInt(request
								.getParameter("iNumExamen")));

						tvExamCat.setICveCategoriaInicial(Integer
								.parseInt(request
										.getParameter("iCveCategoriaInicial"
												+ i)));
						tvExamCat.setICvePuestoInicial(Integer.parseInt(request
								.getParameter("iCvePuestoInicial" + i)));

						String cP = request.getParameter("iCvePuesto" + i);

						tvExamCat
								.setICveMdoTransInicial(Integer.parseInt(request
										.getParameter("iCveMdoTransInicial" + i)));
						tvExamCat.setICveMdoTrans(Integer.parseInt(request
								.getParameter("iCveMdoTrans" + i)));
						tvExamCat.setICveMotivoInicial(Integer.parseInt(request
								.getParameter("iCveMotivoInicial" + i)));
						tvExamCat.setICveMotivo(Integer.parseInt(request
								.getParameter("iCveMotivo" + i)));

						tvExamCat.setICvePuesto(Integer.parseInt(cP.substring(
								0, cP.indexOf("|"))));
						tvExamCat.setICveCategoria(Integer.parseInt(cP
								.substring(cP.indexOf("|") + 1, cP.length())));

						ExpBitMod mod = new ExpBitMod();
						mod.setIpAddress(request.getParameter("hdIpAddress"));
						mod.setMacAddress(request.getParameter("hdMacAddress"));
						mod.setComputerName(request
								.getParameter("hdComputerName"));
						iUpdate = new TDEXPExamCat().UpdateExam(tvExamCat,
								request.getParameter("hdUser"), mod);
						/*
						 * //
						 * System.out.println("getICveExpediente: "+tvExamCat.
						 * getICveExpediente()); //
						 * System.out.println("getINumExamen: "
						 * +tvExamCat.getINumExamen());
						 * 
						 * //
						 * System.out.println("getICvePuestoInicial: "+tvExamCat
						 * .getICvePuestoInicial()); //
						 * System.out.println("getICvePuesto: "
						 * +tvExamCat.getICvePuesto()); //
						 * System.out.println("getICveCategoriaInicial: "
						 * +tvExamCat.getICveCategoriaInicial()); //
						 * System.out.println
						 * ("getICveCategoria: "+tvExamCat.getICveCategoria());
						 * //
						 * System.out.println("getICveMdoTransInicial: "+tvExamCat
						 * .getICveMdoTransInicial()); //
						 * System.out.println("getICveMdoTrans: "
						 * +tvExamCat.getICveMdoTrans()); //
						 * System.out.println("getICveMotivoInicial: "
						 * +tvExamCat.getICveMotivoInicial()); //
						 * System.out.println
						 * ("getICveMotivo: "+tvExamCat.getICveMotivo());
						 */
					}
				}

				if (iUpdate != 0)
					this.setIFlag(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String formatDate(String fecha) {
		if (fecha == null || fecha.indexOf("-") == -1)
			return "&nbsp;";
		d = tf.getSQLDatefromSQLString(fecha);
		fechaFormateada = sdf.format(d);
		return fechaFormateada;
	}

	public int getIFlag() {
		return iFlag;
	}

	private void setIFlag(int iFlag) {
		this.iFlag = iFlag;
	}

}
