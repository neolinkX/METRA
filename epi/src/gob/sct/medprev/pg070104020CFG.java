package gob.sct.medprev;

import java.util.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Handler de acciones para la p�gina
 * pg070104020
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Romeo Sanchez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104020.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104020.jsp.png'>
 */
public class pg070104020CFG extends CFGListBase2 {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private TFechas tf = new TFechas();
	private java.sql.Date d = null;
	private String fechaFormateada = "";
	private String fechaTmp = "";

	/**
	 * Metodo que devuelve una fecha en el formato dd/MM/yyyy
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

	/**
	 * Propiedad que indica si los mensajes enviados a la salida est�ndar por
	 * medio del Metodo log() se muestran (true) o no (false).
	 */
	private boolean debug = false;

	private void log(Object obj) {
		if (debug)
			; // System.out.println(this.getClass().getName()+":"+obj.toString());
	}

	public pg070104020CFG() {
		this.vParametros = new TParametro("07");
		// cPaginas = "pg070104021.jsp"; // Ir a...
	}

	public void fillVector() {
		log("fillVector()");
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		String tpoBusqueda = request.getParameter("tpoBusqueda");
		String cCveExpediente = request.getParameter("iCveExpediente");
		String cNumExamen = request.getParameter("iNumExamen");
		String cCvePersonal = request.getParameter("iCvePersonal");
		String cCveUniMed = request.getParameter("iCveUniMed");
		String cCveProceso = request.getParameter("iCveProceso");
		String cCveModulo = request.getParameter("iCveModulo");
		java.sql.Date dtFecha = new java.sql.Date(new Date().getTime()); // fecha
																			// actual

		if (tpoBusqueda == null || tpoBusqueda.equals("null")
				|| tpoBusqueda.equals("") || tpoBusqueda.equals("undefined")) {
			tpoBusqueda = "";
		}
		// determinar si se reciben los par�metros necesarios para los filtros
		if (cCveExpediente == null || cCveExpediente.equals("null")
				|| cCveExpediente.equals("")
				|| cCveExpediente.equals("undefined")) {
			cCveExpediente = "";
		}
		if (cNumExamen == null || cNumExamen.equals("null")
				|| cNumExamen.equals("") || cNumExamen.equals("undefined")) {
			cNumExamen = "";
		}
		if (cCvePersonal == null || cCvePersonal.equals("null")
				|| cCvePersonal.equals("") || cCvePersonal.equals("undefined")) {
			cCvePersonal = "";
		}
		if (cCveUniMed == null || cCveUniMed.equals("null")
				|| cCveUniMed.equals("") || cCveUniMed.equals("undefined")) {
			cCveUniMed = "";
		}
		if (cCveProceso == null || cCveProceso.equals("null")
				|| cCveProceso.equals("") || cCveProceso.equals("undefined")) {
			cCveProceso = "";
		}
		if (cCveModulo == null || cCveModulo.equals("null")
				|| cCveModulo.equals("") || cCveModulo.equals("undefined")) {
			cCveModulo = "";
		}

		try {
			if (tpoBusqueda.equals("")) {
				log("sin busqueda");

				vDespliega = new Vector();

			} else if (tpoBusqueda.equals("porExpediente")) {
				log("por expediente");
				// construir una colecci�n con los par�metros
				HashMap p = new HashMap();
				p.put("iCveUniMed", cCveUniMed);
				p.put("iCveProceso", cCveProceso);
				p.put("iCveModulo", cCveModulo);
				p.put("iCveExpediente", cCveExpediente);
				p.put("dtFecha", dtFecha);
				p.put("iCvePersonal", cCvePersonal);

				vDespliega = dEXPExamAplica.findByUniMedModExp(p);
				// System.out.println("1a. Opcion");

			} else if (tpoBusqueda.equals("porPersonal")) {
				log("por personal");

				vDespliega = dEXPExamAplica.findExamPersonal(cCveExpediente,
						cNumExamen, cCvePersonal);
				//System.out.println("2a. Opcion");
			}

			log(" expedientes obtenidos: " + vDespliega.size());
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo que devuelve la lista de unidades m�dicas v�lidas
	 * 
	 * @return un Vector con los value objects de las unidades m�dicas
	 */
	public Vector getUniMedsValidas(String cCveProceso) {
		log("getUniMedsValidas()");
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

			vcUMValidas = vUsuario.getVUniFiltro(iCveProceso); // new
																// TDGRLUMUsuario().getUniMedxUsu(vUsuario.getICveusuario());
		} catch (Exception ex) {
			error("getUniMedsValidas", ex);
		}
		return vcUMValidas;
	}

	/**
	 * Metodo que devuelve todos los m�dulos correspondientes a la unidad
	 * m�dica indicada en el par�metro "iCveUniMed" del request de HTTP.
	 * 
	 * @return un Vector con los m�dulos
	 */
	public Vector getModulos() {
		log("getModulos()");
		Vector vcModulos = null;
		try {
			String cTmp = request.getParameter("iCveUniMed");
			if (cTmp != null) {
				vcModulos = new TDGRLModulo().getComboModulos(Integer
						.parseInt(cTmp));
			}
		} catch (Exception ex) {
			error("getModulos", ex);
		}
		return vcModulos;
	}

	/**
	 * Metodo que devuelve todos los m�dulos correspondientes a la unidad
	 * m�dica indicada en el par�metro que recibe el Metodo.
	 * 
	 * @return un Vector con los m�dulos
	 */
	public Vector getModulos(String cCveUniMed, int User) {
		log("getModulos(" + cCveUniMed + ")");
		Vector vcModulos = null;
		try {
			if (cCveUniMed != null && !cCveUniMed.equals("null")
					&& !cCveUniMed.equals("undefined")
					&& !cCveUniMed.equals("")) {
				vcModulos = new TDGRLModulo().getComboModulos2(
						Integer.parseInt(cCveUniMed), User);
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
	 * Metodo que devuelve la descripci�n correspondiente al n�mero de
	 * proceso.
	 * 
	 * @param param
	 *            el n�mero de proceso, obtenido en la p�gina JSP durante la
	 *            inicializaci�n.
	 * @return la descripci�n del proceso obtenida, o una cadena vac�a si no
	 *         encontr� ninguno.
	 */
	public String getProceso(String param) {
		log("getProceso(" + param + ")");
		if (param == null || param.length() == 0)
			return "";
		int proc = 0;
		try {
			proc = Integer.parseInt(param);
		} catch (NumberFormatException ex) {
			error("Error al convertir el n�mero de proceso", ex);
		}
		String proceso = "";
		TVGRLProceso tv = null;
		DAOGRLProceso tdProceso = new DAOGRLProceso();
		Vector v = null;
		try {
			v = tdProceso.FindByAll();
		} catch (DAOException ex1) {
			error("Error al obtener la descripci�n del proceso", ex1);
		}
		for (int i = 0; i < v.size(); i++) {
			tv = (TVGRLProceso) v.elementAt(i);
			if (tv.getICveProceso() == proc) { // si coincide...
				return tv.getCDscProceso();
			}
		}

		return proceso;
	}

	/**
	 * Metodo que convierte la colecci�n de servicios recibida como HashMap en
	 * un Vector
	 * 
	 * @param hm
	 *            el HashMap que contiene los servicios
	 * @return el Vector con los servicios
	 */
	public Vector getServicios(HashMap hm) {
		log("getServicios(" + hm.size() + ") servicios");
		Vector result = new Vector();
		String k = "";
		Set llaves = hm.keySet();
		Iterator keys = llaves.iterator();

		while (keys.hasNext()) {
			TVGRLUSUMedicos usmed = new TVGRLUSUMedicos();
			k = (String) keys.next();
			usmed.setICveServicio(Integer.parseInt(k)); // la propia llave del
														// HashMap
			usmed.setCDscServicio((String) hm.get(k)); // la descripci�n en la
														// posici�n de la
														// llave
			log("servicio obtenido:" + k + hm.get(k));
			result.addElement(usmed);
		}
		// Collections.sort(result);
		return result;
	}

}
