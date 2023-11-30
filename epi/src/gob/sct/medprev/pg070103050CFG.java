package gob.sct.medprev;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.seguridad.dao.CFGListBase2;
import com.micper.seguridad.vo.TVUsuario;
import com.micper.util.TFechas;

import gob.sct.medprev.dao.LICDownFotoHist;
import gob.sct.medprev.dao.TDEPICisCita;
import gob.sct.medprev.dao.TDGRLModulo;
import gob.sct.medprev.dao.TDGRLUMUsuario;
import gob.sct.medprev.dao.TDLICFFH;
import gob.sct.medprev.dao.TDPERDatos;

/**
 * * Clase de configuracion para la Consulta de Citas
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author
 *         <dd>AG SA L
 *         <p>
 *         <img src='pg070103040CFG.png'>
 */
public class pg070103050CFG extends CFGListBase2 {
	private TParametro VParametros = new TParametro("7");

	public pg070103050CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		String cWhere = "";
		String cClave = "";
		String cActualiza = "";
		String cFecha = "";
		TFechas dtFecha = new TFechas();
		int iUser = 0;
		try {
			HashMap hmParams = getParameters();
			TDEPICisCita dEPICisCita = new TDEPICisCita();
			if (request.getParameter("hdBoton").compareTo("Expediente") == 0) {

				if (hmParams.get("iCveUniMed") == null) {
					hmParams.put("iCveUniMed", "-1");
				}

				cWhere = "where EPICisCita.iCveUniMed = " + request.getParameter("hdCveUniMed") + " ";
				cWhere = cWhere + "and EPICisCita.iCveModulo = " + request.getParameter("hdCveModulo") + " ";
				cWhere = cWhere + "and EPICisCita.dtFecha = '" + dtFecha.getDateSQL(request.getParameter("hdFecha"))
						+ "' ";
				cWhere = cWhere + "and EPICisCita.iCveCita = " + request.getParameter("hdCveCita") + " ";

				iUser = Integer.parseInt(request.getParameter("hdUsuario"));

				cActualiza = " where iCveUniMed = " + request.getParameter("hdCveUniMed") + " " + "and dtFecha =  '"
						+ dtFecha.getDateSQL(request.getParameter("hdFecha")) + "' " + "and iCveCita =  "
						+ request.getParameter("hdCveCita") + " " + "and iCveModulo = "
						+ request.getParameter("hdCveModulo") + " ";

				dEPICisCita.AltaPersonal(cWhere, iUser, cActualiza);
				vDespliega = new TDEPICisCita().FindConsultaCitas(hmParams);
				iNumReg = vDespliega.size(); // Despliega todos registros

			} else {
				if (hmParams.get("iCveUniMed") == null) {
					hmParams.put("iCveUniMed", "-1");
				}
				vDespliega = new TDEPICisCita().FindConsultaCitas(hmParams);
				iNumReg = vDespliega.size(); // Despliega todos registros
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo getUniMedsValidas
	 */
	public Vector getUniMedsValidas() {
		Vector vcUMValidas = null;
		try {
			TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true).getAttribute("UsrID");
			vcUMValidas = new TDGRLUMUsuario().getUniMedxUsu(vUsuario.getICveusuario());
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcUMValidas;
	}

	/**
	 * Metodo getModulos
	 */
	public Vector getModulos() {
		Vector vcModulos = null;
		try {
			String cTmp = request.getParameter("iCveUniMed");
			if (cTmp != null) {
				vcModulos = new TDGRLModulo().getComboModulos(Integer.parseInt(cTmp));
			}
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcModulos;
	}

	/**
	 * Metodo getModulos
	 */
	public Vector getModulos(String cCveUniMed) {
		Vector vcModulos = null;
		try {
			if (cCveUniMed != null) {
				vcModulos = new TDGRLModulo().getComboModulos(Integer.parseInt(cCveUniMed));
			}
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcModulos;
	}

	/**
	 * Metodo getHorasDeCitas
	 */
	public Vector getHorasDeCitas() {
		Vector vcHoras = null;
		try {
			HashMap hmParams = getParameters();
			if (hmParams.get("iCveUniMed") == null) {
				hmParams.put("iCveUniMed", "-1");
			}
			vcHoras = new TDEPICisCita().getHorasDeCitas(hmParams);
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcHoras;
	}

	/**
	 * Metodo getHorasDeCitas
	 */
	public Vector getHorasDeCitas(String cCveUniMed, String cCveModulo, String cFecha) {
		Vector vcHoras = null;
		try {
			HashMap hmParams = new HashMap();
			hmParams.put("iCveUniMed", cCveUniMed);
			hmParams.put("iCveModulo", cCveModulo);
			hmParams.put("dtFecha", cFecha);
			if (hmParams.get("iCveUniMed") == null) {
				hmParams.put("iCveUniMed", "-1");
			}
			vcHoras = new TDEPICisCita().getHorasDeCitas(hmParams);
		} catch (Exception ex) {
			error("getHoras", ex);
		}
		return vcHoras;
	}

	/**
	 * Metodo getParameters
	 */
	private HashMap getParameters() {
		HashMap hmTmp = new HashMap();
		hmTmp.put("iCveUniMed", request.getParameter("iCveUniMed"));
		hmTmp.put("iCveModulo", request.getParameter("iCveModulo"));
		hmTmp.put("dtFecha", request.getParameter("dtFecha"));
		hmTmp.put("cHora", request.getParameter("cHora"));

		return hmTmp;
	}

	public boolean validaCURP2(String cCURP) {

		if (cCURP.matches("[A-Z]{4}[0-9]{2}" + "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" + "[HM]{1}"
				+ "(AS|BC|BS|CC|CH|CL|CS|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)"
				+ "[B-DF-HJ-NP-TV-Z]{3}" + "[0-9A-Z]{1}[0-9]{1}$")) {// AAAA######AAAAAA##

			return true;
		}
		return false;
	}

	/**
	 * 04/06/2014 Valida la CURP de cita deEPICisCita ademas de no encuentrarse
	 * en PERDATOS
	 * 
	 * @author Ing. Andres Esteban Bernal Muñoz
	 */
	public int checkCURP(String cCveUniMed, String cCveModulo, String cFecha, String cCveCita) {
		TDEPICisCita dEPICisCita = new TDEPICisCita();
		TDPERDatos dPERDatos = new TDPERDatos();
		String cCURP = "";
		// String aMSG="";
		int count;
		int regresa = 0;
		try {
			cCURP = dEPICisCita.FindCURPByCita(cCveUniMed, cCveModulo, cFecha, cCveCita);
			// aMSG="curp: "+cCURP+"\n";

			if (!dPERDatos.validaCURP2(cCURP)) {
				return 1;
			}
			count = dPERDatos.iCURP(cCURP);
			if (count > 0) {
				return dPERDatos.getExpediente(cCURP); // DEVUELVE EL NUMERO DE
														// EXPEDIENTE
				// return 2;
			}

		} catch (DAOException e) {
			error("getExpediente", e);
		}
		return regresa;
	}

	/**
	 * 19/08/2014 Descargar la foto del ultimo dictamen emitido para solicitar
	 * nueva toma de huellas
	 * 
	 * @author Lic. AG SA L.
	 * @throws Exception
	 */
	public int DescargaFoto(String cExpediente) throws Exception {
		String dataSourceName = VParametros.getPropEspecifica("ConDBModulo");
		//com.micper.sql.DbConnection dbConn = new com.micper.sql.DbConnection(dataSourceName);
		//java.sql.Connection conn = dbConn.getConnection();
		///// OBTENER INODOCTO DE FOTO/////////
		TDLICFFH dTDLICFFH = new TDLICFFH();
		dTDLICFFH.findFotoHuellasValidas(cExpediente);
		int inodoctoFoto = 0;
		try {
			inodoctoFoto = dTDLICFFH.findFotoHuellasValidas(cExpediente);
			int[] inodoctos = new int[3];
			inodoctos[0] = inodoctoFoto;

			if (inodoctoFoto > 0) {
				LICDownFotoHist dLICDownFotoHist = new LICDownFotoHist();
				String sFichero = "" + VParametros.getPropEspecifica("RutaNASH").toString() + "f-" + cExpediente
						+ "-SolHuella.jpg";
				File fichero = new File(sFichero);
				if (!fichero.exists()) {
					try {
						dLICDownFotoHist.getImgSolHuella(Integer.toString(inodoctos[0]), cExpediente);
					} catch (Exception e) {
						System.out.println("Se produjo un error en el metodo getImgSolHuella");
					}
				}

				// / VALIDAR QUE LA FOTO NO SEA IGUAL A 0 BYTES
				if (fichero.length() == 0) {
					fichero.delete();
					return inodoctoFoto;
				}
			}
		} catch (Exception e) {
			System.out.println("Se produjo un error en el metodo findFotoHuellasValidas");
		} 
		return inodoctoFoto;
	}
}