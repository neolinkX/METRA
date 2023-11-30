package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import gob.sct.cis.dao.*;

/**
 * 
 * @author AG SA
 */

public class pg070102040CFG extends CFGListBase2 {
	public pg070102040CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {
			HashMap hmParams = getParameters();
			if (hmParams.get("iCveUniMed") == null)
				hmParams.put("iCveUniMed", "-1");
			vDespliega = new TDEPICisCita().FindConsultaCitas(hmParams);

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo getParameters
	 */
	private HashMap getParameters() {
		HashMap hmTmp = new HashMap();
		// hmTmp.put("iCveUniMed",request.getParameter("iCveUniMed"));
		// hmTmp.put("iCveModulo",request.getParameter("iCveModulo"));
		hmTmp.put("dtFecha", request.getParameter("dtFecha"));
		// hmTmp.put("cHora" ,request.getParameter("cHora" ));
		return hmTmp;
	}

	/**
	 * Metodo Verificar si ya se cargaron las citas del dia.
	 */
	public int VerCC() {
		int count = 0;
		String Consulta = "";
		TDCARGACITAS dCARGACITAS = new TDCARGACITAS();
		if (request.getParameter("dtFecha") != null) {
			String fecha = request.getParameter("dtFecha").toString() + "/";
			String fecha2 = fecha.subSequence(6, 10) + "-"
					+ fecha.subSequence(3, 5) + "-" + fecha.subSequence(0, 2);
			Consulta = "SELECT COUNT(*) FROM EXPBITMOD "
					+ "WHERE "
					// + "{FN YEAR(DTREALIZADO)}= "+fecha.subSequence(6,
					// 10)+" AND "
					// + "{FN MONTH(DTREALIZADO)} = "+fecha.subSequence(3,
					// 5)+" AND "
					// + "{FN DAY(DTREALIZADO)}= "+fecha.subSequence(0,
					// 2)+" AND "
					+ " IOPERACION = 10 AND CDESCRIPCION = 'CARGA DE CITAS = "
					+ fecha2 + "'";
			try {
				count = dCARGACITAS.RegresaInt(Consulta);
				if (count == 1) {// Confirmamos se alla realizado la carga de la
									// CITAS
					count = dCARGACITAS.RegresaInt(Consulta
							+ " AND LDICTAMEN = 1");// Si el registro de la cita
													// en la bitacora en el
													// campo LDICTAMEN es igual
													// a uno indica que las
													// citas se cargaron
													// correctamente.
					if (count == 1) {
						count = 2;
					} else {
						count = 1;
					}
				}
			} catch (Exception e) {
				count = 0;
			}
		}
		return count;
	}

	/**
	 * Metodo que realiza la carga de Citas.
	 */
	public String CargaCitas(int usr) {
		int count = 0;
		String Consulta = "";
		String Resultado = "";
		if (request.getParameter("dtFecha") != null) {
			Consulta = request.getParameter("dtFecha").subSequence(6, 10) + "-"
					+ request.getParameter("dtFecha").subSequence(3, 5) + "-"
					+ request.getParameter("dtFecha").subSequence(0, 2) + "";
			// System.out.println(Consulta);
			TDCARGACITAS dCARGACITAS = new TDCARGACITAS();
			try {
				Resultado = dCARGACITAS.insert(null, usr, Consulta);
				if (Resultado.equals("CargaExitosa"))
					count = 1;
			} catch (Exception e) {
				count = 0;
			}
		}
		return Resultado;
	}

	/**
	 * Metodo Verificar si ya se cargaron las citas del dia desde el login.
	 */
	public int VerCCLogin(String dtFecha) {
		int count = 0;
		int count2 = 0;
		String Consulta = "";
		TDCARGACITAS dCARGACITAS = new TDCARGACITAS();
		Calendar calendario = Calendar.getInstance();
		if (dtFecha != null) {
			String fecha = dtFecha.toString() + "/";
			String fecha2 = fecha.subSequence(6, 10) + "-"
					+ fecha.subSequence(3, 5) + "-" + fecha.subSequence(0, 2);
			Consulta = "SELECT COUNT(*) FROM EXPBITMOD "
					+ "WHERE  IOPERACION = 10 AND CDESCRIPCION = 'CARGA DE CITAS = "
					+ fecha2 + "'";
			try {
				count = dCARGACITAS.RegresaInt(Consulta);
				if (count > 0) {
					count2 = dCARGACITAS.RegresaInt(Consulta
							+ " and ldictamen = 1");
					int minutos = calendario.get(Calendar.MINUTE);
					// System.out.println("count2 = "+count2);
					// System.out.println("minutos = "+minutos);
					if (count2 == 0
							&& (minutos == 00 || minutos == 15 || minutos == 30 || minutos == 45))
						count = 0;
				}
				// System.out.println("conut = "+ count);
			} catch (Exception e) {
				// System.out.println("Error en la obtension de datos de la carga de citas");
			}
		}
		return count;
	}

	/**
	 * Metodo que realiza la carga de Citas.
	 */
	public String CargaCitasLog(int usr, String dtFecha) {
		int count = 0;
		String Consulta = "";
		String Resultado = "";
		TDCARGACITAS dCARGACITAS = new TDCARGACITAS();
		if (dtFecha != null) {
			Consulta = dtFecha.subSequence(6, 10) + "-"
					+ dtFecha.subSequence(3, 5) + "-"
					+ dtFecha.subSequence(0, 2) + "";
			// System.out.println(Consulta);
			String sQL = "SELECT COUNT(*) FROM EXPBITMOD WHERE IOPERACION = 10 AND LDICTAMEN = 1 AND CDESCRIPCION = 'CARGA DE CITAS = "
					+ Consulta + "'";
			// System.out.println(sQL);
			try {
				if (dCARGACITAS.RegresaInt(sQL) == 0) {
					// System.out.println("Se cumplio la condicion");
					dCARGACITAS.insert2(null, usr, Consulta);
					dCARGACITAS.insertSIAF(null, usr, dtFecha);
				}
			} catch (Exception e) {
				count = 0;
			}
		}
		return Resultado;
	}

}