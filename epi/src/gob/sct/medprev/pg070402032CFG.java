package gob.sct.medprev;

import java.util.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Handler de acciones para la p�gina
 * pg070104030
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104030.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070402032.jsp.png'>
 */
public class pg070402032CFG extends CFGListBase2 {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private TFechas tf = new TFechas();
	private String fechaFormateada = "";
	private String fechaTmp = "";
	private String tpoBusqueda = "";
	private java.sql.Date dtFecha = new java.sql.Date(
			new java.util.Date().getTime()); // fecha actual
	private final HashMap param = new HashMap();
	public boolean ultimaRama;
	public boolean primeraRama;
	private boolean variosMedicos;
	private TVEXPRama nextRama;
	/**
	 * Propiedad que almacenar� los datos del personal, mostrado en el
	 * encabezado del detalle del examen
	 */
	private TVPERDatos personal = new TVPERDatos();

	/**
	 * Variable de instancia que almacena los value objects de los registros por
	 * actualizar
	 */

	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private final int SI_NO = 1;
	private final int LETRAS_NUMEROS = 2;
	private final int NUMEROS = 3;
	private final int NOTAS = 4;
	private final int RANGO = 5;

	/**
	 * Propiedad que indica si los mensajes enviados a la salida est�ndar por
	 * medio del Metodo log() se muestran (true) o no (false).
	 */
	private boolean debug = false;

	private void log(Object obj) {
		if (debug) {
			// System.out.println(this.getClass().getName() + ":" +
			// obj.toString());
		}
	}

	public pg070402032CFG() {
		this.vParametros = new TParametro("07");
		cPaginas = "pg070402030.jsp"; // Ir a...

	}

	public void fillVector() {
		try {

		} catch (Exception ex) {
			error("fillVector", ex);
		}
		log("fin de fillVector()");
	}

	public Vector listaInvRecomFinal() {
		Vector vcRecomFinal = new Vector();
		TDINVRecomFinal dINVRecomFinal = new TDINVRecomFinal();
		Object ObjRecomFinal = new Object();
		try {

			ObjRecomFinal = this.getInputs();
			TVINVRecomFinal vINVRecomFinal = (TVINVRecomFinal) ObjRecomFinal;

			vcRecomFinal = dINVRecomFinal.findByAll(" Where a.iAnio ="
					+ vINVRecomFinal.getIAnio() + " And a.iCveMdoTrans = "
					+ vINVRecomFinal.getICveMdoTrans() + " And a.iIDDGPMPT="
					+ vINVRecomFinal.getIIDDGPMPT());

		} catch (Exception ex) {
			error("listaInvRecomFinal", ex);
		}
		return vcRecomFinal;
	}

	public Vector listaInvRegistro() {
		Vector vcInvRegistro = new Vector();
		TDINVRegistro dINVRegistro = new TDINVRegistro();
		Object ObjRegistro = new Object();
		String cWhere = "";
		String cOrder = "";
		try {

			ObjRegistro = this.getInputs();
			TVINVRecomFinal vINVRecomFinal = (TVINVRecomFinal) ObjRegistro;
			cWhere = " a.iAnio =" + vINVRecomFinal.getIAnio()
					+ " And a.iCveMdoTrans = "
					+ vINVRecomFinal.getICveMdoTrans() + " And a.iIDDGPMPT="
					+ vINVRecomFinal.getIIDDGPMPT();
			cOrder = " Order by a.iAnio,a.iCveMdoTrans,a.iIDDGPMPT";
			vcInvRegistro = dINVRegistro.FindByAll(cWhere, cOrder);

		} catch (Exception ex) {
			error("listaInvRecomFinal", ex);
		}
		return vcInvRegistro;
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {
			String aCveRecomendacion[] = request
					.getParameterValues("iCveRecomendacion");
			String iCveRecomendacion = "";
			String cConclusion = "";
			if (request.getParameter("cConclusion") != null) {
				cConclusion = request.getParameter("cConclusion");
			}
			TDINVRecomFinal dINVRecomFinal = new TDINVRecomFinal();
			Object ObjRecomFinal = new Object();
			ObjRecomFinal = this.getInputs();
			if (aCveRecomendacion.length > 0) {
				for (int x = 0; x < aCveRecomendacion.length; x++) {
					dINVRecomFinal.insert(null, ObjRecomFinal,
							aCveRecomendacion[x]);
				}
			}
			dINVRecomFinal.insertNota(null, ObjRecomFinal, cConclusion);
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVINVRecomFinal vINVRecomFinal = new TVINVRecomFinal();
		try {
			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRecomFinal.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRecomFinal.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iIDDGPMPT");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRecomFinal.setIIDDGPMPT(iCampo);

			cCampo = "" + request.getParameter("iCveRecomendacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vINVRecomFinal.setICveRecomendacion(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vINVRecomFinal;
	}

}
