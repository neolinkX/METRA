package gob.sct.medprev;

import java.util.*;
import java.text.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

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
 * pg070108021
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070108021.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070108021.jsp.png'>
 */
public class pg070108021CFG extends CFGListBase2 {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private TFechas tf = new TFechas();
	private java.sql.Date d = null;
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
	private boolean lAudio = false;
	/**
	 * Propiedad que almacenar� los datos del personal, mostrado en el
	 * encabezado del detalle del examen
	 */
	private TVPERDatos personal = new TVPERDatos();

	/**
	 * Variable de instancia que almacena los value objects de los registros por
	 * actualizar
	 */
	private Vector actualizar = new Vector();

	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private final int SI_NO = 1;
	private final int LETRAS_NUMEROS = 2;
	private final int NUMEROS = 3;
	private final int NOTAS = 4;
	private final int RANGO = 5;

	/**
	 * Metodo que devuelve una fecha en el formato dd/MM/yyyy
	 * 
	 * @param fecha
	 *            un String con la fecha en formato yyyy-MM-dd
	 * @return un String con la fecha convertida al formato dd/MM/yyyy
	 */
	public String formatDate(String fecha) {
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
		if (debug) {
			// System.out.println(this.getClass().getName() + ":" +
			// obj.toString());
		}
	}

	public pg070108021CFG() {
		this.vParametros = new TParametro("07");
		cPaginas = "pg070201011.jsp"; // Ir a...
	}

	/**
	 * Metodo invocado desde clsConfig. S�lo se implementa.
	 */
	public void fillVector() {
		TDEXPResultado dEXPResultado = new TDEXPResultado();
		HashMap filtro = null;

		this.validaParametros();
		this.setPersonal();
		if (personal.getCSexo() != null)
			param.put("cGenero", personal.getCSexo()); // el par�metro
														// faltante

		if (cAccion.equalsIgnoreCase("AudioMuestra")) {
			this.audiometriadel(
					Integer.parseInt(request.getParameter("iCveExpediente")),
					Integer.parseInt(request.getParameter("iNumExamen")),
					Integer.parseInt(request.getParameter("iCveServicio")));
			this.audiometriai();
			this.audiometriad();
		}

		if (cAccion.equalsIgnoreCase("Guardar")) {
			this.Guardar();
		}

		/*
		 * Si el proceso es por un s�lo m�dico, deben obtenerse todas las
		 * ramas del servicio e iterar para procesar cada una de ellas.
		 * 
		 * Si el proceso es por varios m�dicos, se debe tomar la rama que
		 * viene seleccionada y procesar s�lo esa rama.
		 */
		nextRama = this.getNextRama();

		try {
			if (nextRama == null) { // si ya no hay m�s ramas
				vDespliega = new Vector(); // no enviar m�s datos
				// habilitar la bandera, para que el JSP haga el forward
				// correspondiente
				ultimaRama = true;

				// terminar el servicio

				if (!hayMasRamas()) {
					try {
						this.terminarServicio();
					} catch (DAOException ex1) {
						// warn("fillVector", ex1);
					}
				}

			} else {
				// existe al menos una rama m�s
				// log("nextRama existe");
				ultimaRama = false;
				filtro = new HashMap();
				filtro.put("iCveExpediente", nextRama.getICveExpediente() + "");
				filtro.put("iNumExamen", nextRama.getINumExamen() + "");
				filtro.put("iCveServicio", nextRama.getICveServicio() + "");
				filtro.put("iCveRama", nextRama.getICveRama() + ""); // para el
																		// caso
																		// de
																		// buscar
																		// todas
																		// las
																		// ramas
																		// de un
																		// servicio
				filtro.put("cGenero", personal.getCSexo()); // para garantizar
															// que el
															// par�metro se
															// env�a...
				// log("filtro para buscar resultados: " + filtro.toString());
				vDespliega = dEXPResultado.findResultadoSintoma(filtro);
				iNumReg = vDespliega.size();
			}
		} catch (Exception ex) {
			error("fillVector", ex);
		}
		// log("fin de fillVector()");
	}

	private void audiometriadel(int iExpediente, int iExamen, int iServicio) {

		TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
		TVEXPAudiomet vEXPAudiomet = new TVEXPAudiomet();

		try {
			vEXPAudiomet.setICveExpediente(iExpediente);
			vEXPAudiomet.setINumExamen(iExamen);
			vEXPAudiomet.setICveServicio(iServicio);
			dEXPAudiomet.delete(null, vEXPAudiomet);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void audiometriai() {

		TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
		TVEXPAudiomet vEXPAudiomet = new TVEXPAudiomet();

		try {
			int iCveServicio = 6;
			if (request.getParameter("iCveServicio") != null)
				iCveServicio = Integer.parseInt(request
						.getParameter("iCveServicio"));

			if (request.getParameter("hdOIM") != null
					&& request.getParameter("hdOIM").trim().length() > 0
					&& !request.getParameter("hdOIM").equals("null")) {

				StringTokenizer stKey = new StringTokenizer(
						request.getParameter("hdOIM"), ",");
				while (stKey.hasMoreElements()) {

					vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIOido(1);
					vEXPAudiomet.setITipo(1);
					vEXPAudiomet.setICveServicio(iCveServicio);
					dEXPAudiomet.insert(null, this.getInputs(), vEXPAudiomet);
				}

			}
			if (request.getParameter("hdOIC") != null
					&& request.getParameter("hdOIC").trim().length() > 0
					&& !request.getParameter("hdOIC").equals("null")) {
				StringTokenizer stKey = new StringTokenizer(
						request.getParameter("hdOIC"), ",");
				while (stKey.hasMoreElements()) {

					vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIOido(1);
					vEXPAudiomet.setITipo(2);
					vEXPAudiomet.setICveServicio(iCveServicio);
					dEXPAudiomet.insert(null, this.getInputs(), vEXPAudiomet);
				}

			}

			if (request.getParameter("hdOIT") != null
					&& request.getParameter("hdOIT").trim().length() > 0
					&& !request.getParameter("hdOIT").equals("null")) {
				StringTokenizer stKey = new StringTokenizer(
						request.getParameter("hdOIT"), ",");
				while (stKey.hasMoreElements()) {

					vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIOido(1);
					vEXPAudiomet.setITipo(3);
					vEXPAudiomet.setICveServicio(iCveServicio);
					dEXPAudiomet.insert(null, this.getInputs(), vEXPAudiomet);
				}

			}
			if (request.getParameter("hdOIN") != null
					&& request.getParameter("hdOIN").trim().length() > 0
					&& !request.getParameter("hdOIN").equals("null")) {
				StringTokenizer stKey = new StringTokenizer(
						request.getParameter("hdOIN"), ",");
				while (stKey.hasMoreElements()) {

					vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIOido(1);
					vEXPAudiomet.setITipo(4);
					vEXPAudiomet.setICveServicio(iCveServicio);
					dEXPAudiomet.insert(null, this.getInputs(), vEXPAudiomet);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void audiometriad() {

		TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
		TVEXPAudiomet vEXPAudiomet = new TVEXPAudiomet();

		try {
			int iCveServicio = 6;
			if (request.getParameter("iCveServicio") != null)
				iCveServicio = Integer.parseInt(request
						.getParameter("iCveServicio"));

			if (request.getParameter("hdODM") != null
					&& request.getParameter("hdODM").trim().length() > 0
					&& !request.getParameter("hdODM").equals("null")) {
				StringTokenizer stKey = new StringTokenizer(
						request.getParameter("hdODM"), ",");
				while (stKey.hasMoreElements()) {

					vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIOido(2);
					vEXPAudiomet.setITipo(1);
					vEXPAudiomet.setICveServicio(iCveServicio);
					dEXPAudiomet.insert(null, this.getInputs(), vEXPAudiomet);
				}
			}

			if (request.getParameter("hdODC") != null
					&& request.getParameter("hdODC").trim().length() > 0
					&& !request.getParameter("hdODC").equals("null")) {
				StringTokenizer stKey = new StringTokenizer(
						request.getParameter("hdODC"), ",");
				while (stKey.hasMoreElements()) {

					vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIOido(2);
					vEXPAudiomet.setITipo(2);
					vEXPAudiomet.setICveServicio(iCveServicio);
					dEXPAudiomet.insert(null, this.getInputs(), vEXPAudiomet);
				}
			}
			if (request.getParameter("hdODT") != null
					&& request.getParameter("hdODT").trim().length() > 0
					&& !request.getParameter("hdODT").equals("null")) {
				StringTokenizer stKey = new StringTokenizer(
						request.getParameter("hdODT"), ",");
				while (stKey.hasMoreElements()) {

					vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIOido(2);
					vEXPAudiomet.setITipo(3);
					vEXPAudiomet.setICveServicio(iCveServicio);
					dEXPAudiomet.insert(null, this.getInputs(), vEXPAudiomet);
				}
			}
			if (request.getParameter("hdODN") != null
					&& request.getParameter("hdODN").trim().length() > 0
					&& !request.getParameter("hdODN").equals("null")) {
				StringTokenizer stKey = new StringTokenizer(
						request.getParameter("hdODN"), ",");
				while (stKey.hasMoreElements()) {

					vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
					vEXPAudiomet.setIOido(2);
					vEXPAudiomet.setITipo(4);
					vEXPAudiomet.setICveServicio(iCveServicio);
					dEXPAudiomet.insert(null, this.getInputs(), vEXPAudiomet);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		// log("en Guardar() los parametros son: " + param.toString());
		/*
		 * la funcionalidad original de este Metodo se ha cambiado para tomar la
		 * acci�n pertinente, seg�n las condiciones definidas en la regla de
		 * negocios durante la ejecuci�n del Metodo getTableValues().
		 */

		// se llenan las tablas de hash con los value objects correspondientes.
		int n = this.getTableValues();
		// log("se obtuvieron " + n + " registros de la forma HTML");

		if (false) {
			return; // ****para pruebas
		}

		// log("generando conexion...");

		DbConnection dbConn = null;
		Connection conn = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			if (actualizar.size() != 0) { // si hay registros por insertar
				this.actualizar(conn);
				if (!lAudio) {
					this.audiometriadel(Integer.parseInt(request
							.getParameter("iCveExpediente")), Integer
							.parseInt(request.getParameter("iNumExamen")),
							Integer.parseInt(request
									.getParameter("iCveServicio")));
					this.audiometriai();
					this.audiometriad();
					lAudio = true;
				}

			}

			conn.commit();
			// log("committing...");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				// log("rolling back...");
				// log(e.getMessage());
				// e.printStackTrace(); // solo habilitar para depuraci�n
			} catch (Exception ex) {
				vErrores.acumulaError("", 14, "");
				error("Error al crear el registro", ex);
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
					// log("--cerrando Connection...");
				}
				if (dbConn != null) {
					dbConn.closeConnection();
					// log("--cerrando DbConnection...");
				}
			} catch (SQLException ex1) {
				vErrores.acumulaError("", 14, "");
				error("Error al cerrar la conexi�n", ex1);
			}
		}
		// super.Guardar();
	} // Metodo Guardar

	/**
	 * Aplica la l�gica de negocios para actualizaci�n de registros.
	 * 
	 * @param conn
	 *            La conexi�n recibida, para actualizar en el contexto de una
	 *            transacci�n
	 * @throws DAOException
	 *             si no se encuentra alguno de los registros por actualizar.
	 * @author Romeo S�nchez
	 */
	private void actualizar(Connection conn) throws DAOException {
		// log("en actualizar() los parametros son: " + param.toString());
		TDEXPResultado dEXPResultado = new TDEXPResultado();
		TDEXPRama dEXPRama = new TDEXPRama();
		TDEXPServicio dEXPServicio = new TDEXPServicio();
		TVEXPResultado resultado = null;
		TVEXPRama rama = new TVEXPRama();
		TVEXPServicio servicio = new TVEXPServicio();
		// log("registros por actualizar:" + actualizar.size());
		int exp = ((String) param.get("iCveExpediente")) == null ? -1 : Integer
				.parseInt((String) param.get("iCveExpediente"));
		int exm = ((String) param.get("iNumExamen")) == null ? -1 : Integer
				.parseInt((String) param.get("iNumExamen"));
		int srv = ((String) param.get("iCveServicio")) == null ? -1 : Integer
				.parseInt((String) param.get("iCveServicio"));
		int ram = ((String) param.get("iCveRama")) == null ? -1 : Integer
				.parseInt((String) param.get("iCveRama"));
		if (exp > -1 && exm > -1 && srv > -1 && ram > -1) {
			// Validaci�n
			Vector vRama = new Vector();
			vRama = (new TDEXPRama()).getLConcluido(String.valueOf(exp),
					String.valueOf(exm), String.valueOf(srv),
					String.valueOf(ram));
			if (vRama.size() > 0
					&& ((TVEXPRama) vRama.get(0)).getIConcluido() == 0) {
				// actualizar los registros de EXPResultado
				for (int i = 0; i < actualizar.size(); i++) {
					// actualizar EXPResultado
					resultado = (TVEXPResultado) actualizar.get(i);
					// log("actualizar:" + resultado.toHashMap().toString());
					try {
						// manejar en una transacci�n
						cClave = (String) dEXPResultado.update(conn, resultado);
					} catch (Exception ex) {
						ex.printStackTrace();
						vErrores.acumulaError("", 14, "");
						error("Error al actualizar el s�ntoma "
								+ resultado.getICveSintoma()
								+ " de EXPResultado ", ex);
					}
				}

				// log("--antes de actualizar ramas y servicios, los par�metros son: "
				// + param.toString());

				// buscar registro, y actualizarlo, para conservar los datos
				// actuales, y solo modificar los requeridos
				// llenar la llave primaria de EXPRama, utilizada para la
				// b�squeda
				rama.setICveExpediente(exp);
				rama.setINumExamen(exm);
				rama.setICveServicio(srv);
				rama.setICveRama(ram);

				rama = (TVEXPRama) this.llenarRegistro(rama);
				if (rama != null) { // validar que existe el registro
					// log("existe la rama: " + rama.toHashMap());
					/*
					 * Establecer los valores de los campos que se deben
					 * actualizar: lConcluido dtInicio dtFin iCveUsuAplica
					 */

					if (rama.getDtInicio() == null) {
						rama.setDtInicio(dtFecha); // poner fecha de inicio
													// s�lo si no tiene
					}
					rama.setDtFin(dtFecha);
					rama.setIConcluido(1); // deb�a ser setLConcluido...
					rama.setICveUsuAplica(Integer.parseInt((String) param
							.get("iCveUsuario")));

					// actualizar el registro
					String ramaUpdated = (String) dEXPRama.update(conn, rama);
					// log("rama actualizada:" + ramaUpdated);
				}
			} else {
				throw new DAOException(
						"No se encontro el registro parent (EXPRama) para la actualizacion");
			}
		} // Se va a buscar un expediente
	}

	private void terminarServicio() throws DAOException {
		TDEXPServicio dEXPServicio = new TDEXPServicio();
		TDTSSEGEXAR dTSSEGEXAR = new TDTSSEGEXAR();
		TDTSEGEXAR dTSEGEXAR = new TDTSEGEXAR();
		TVTSEGEXAR vTSEGEXAR = new TVTSEGEXAR();
		Vector vcTSEGEXAR = new Vector();
		TVEXPServicio servicio = new TVEXPServicio();
		int exp = ((String) param.get("iCveExpediente")).equals("") ? -1
				: Integer.parseInt((String) param.get("iCveExpediente"));
		int exm = ((String) param.get("iNumExamen")).equals("") ? -1 : Integer
				.parseInt((String) param.get("iNumExamen"));
		int srv = ((String) param.get("iCveServicio")).equals("") ? -1
				: Integer.parseInt((String) param.get("iCveServicio"));
		int ram = ((String) param.get("iCveRama")).equals("") ? -1 : Integer
				.parseInt((String) param.get("iCveRama"));

		// log("Primera rama... " + primeraRama + " actualizando");
		// log("Ultima rama... " + ultimaRama + " actualizando");
		servicio.setICveExpediente(exp);
		servicio.setINumExamen(exm);
		servicio.setICveServicio(srv);
		int iCveUsuario = ((TVUsuario) (((HttpServletRequest) request)
				.getSession(true).getAttribute("UsrID"))).getICveusuario();
		servicio.setICveUsuAplica(iCveUsuario);
		servicio = (TVEXPServicio) this.llenarRegistro(servicio);

		if (servicio != null) {
			// log("existe el servicio: " + servicio.toHashMap());

			/*
			 * Establecer los valores de los campos que se deben actualizar
			 * lConcluido dtInicio (si es la primera rama) dtFin (si es la
			 * �ltima rama)
			 */

			if (servicio.getDtInicio() == null) {
				// log("servicio sin fecha de inicio");
				servicio.setDtInicio(dtFecha); // poner fecha de inicio s�lo
												// si no tiene
			}
			// log("ultima rama, actualizar servicio");
			servicio.setDtFin(dtFecha);
			servicio.setLConcluido(1);
			// log("servicio por actualizar: " +
			// servicio.toHashMap().toString());

			// actualizar el registro
			String servicioUpdated = (String) dEXPServicio.update(null,
					servicio);
			// System.out.println("Se actualizo lconcluido en expservicio");
			servicio.setICveUsuAplica(iCveUsuario);
			String servicioUpdated2 = (String) dTSSEGEXAR
					.update(null, servicio);
			// System.out.println("Seactualizo lconcluido en tdssegexar");
			servicio.setICveUsuAplica(iCveUsuario);
			int NumSer = 0;
			vcTSEGEXAR = dTSEGEXAR.FindByAllS(exp + "", exm + "");
			for (int j = 0; j < vcTSEGEXAR.size(); j++) {
				vTSEGEXAR = (TVTSEGEXAR) vcTSEGEXAR.get(j);
				NumSer = vTSEGEXAR.getNumServC();
			}
			// System.out.println("NumSer = " +NumSer);
			if (NumSer < 1) {
				String servicioUpdated3 = (String) dTSEGEXAR.updateEstatus(
						null, servicio);
				// System.out.println("Se actualizo el Estatus a 2");
			}
			// log("servicio actualizado:" + servicioUpdated);
		} else {
			throw new DAOException(
					"No se encontro el registro parent (EXPServicio) para la actualizacion");
		}
		// log("******** terminando servicio:" +
		// servicio.toHashMap().toString());
	}

	/**
	 * Devuelve los campos de un registro, en base a una llave primaria.
	 * <p>
	 * El Metodo hace una b�squeda en la tabla correspondiente al tipo de
	 * value object que se recibe como argumento, y si lo encuentra, devuelve un
	 * nuevo value object con los campos faltantes.
	 * 
	 * @param value
	 *            el value object, como argumento polim�rfico para permitir
	 *            invocar el mismo Metodo, independientemente del tipo de value
	 *            object requerido.
	 * @return un value object con los datos encontrados en la b�squeda. Si no
	 *         se encontraron datos, el resultado es null.
	 * @throws DAOException
	 *             si no se encuentra el registro solicitado por la llave
	 *             primaria
	 * @author Romeo Sanchez
	 */
	private HashBeanInterface llenarRegistro(HashBeanInterface value)
			throws DAOException {
		HashMap filtro = null;
		Vector r = null;
		if (value instanceof TVEXPRama) { // si recibe una rama
			TVEXPRama rama = (TVEXPRama) value;
			TDEXPRama dRama = new TDEXPRama();
			filtro = new HashMap();
			filtro.put("iCveExpediente", rama.getICveExpediente() + "");
			filtro.put("iNumExamen", rama.getINumExamen() + "");
			filtro.put("iCveServicio", rama.getICveServicio() + "");
			filtro.put("iCveRama", rama.getICveRama() + "");
			try {
				r = dRama.findByPK(filtro);
			} catch (DAOException ex) {
				error("Error al buscar el registro en EXPRama", ex);
			}
			if (r != null && r.size() == 1) {
				rama = (TVEXPRama) r.get(0);
				// log("rama antes de actualizar: " +
				// rama.toHashMap().toString());
			} else {
				throw new DAOException(
						"La tabla EXPRama no contiene el registro solicitado");
			}
			return rama;
		} else if (value instanceof TVEXPServicio) { // si recibe un servicio
			TVEXPServicio servicio = (TVEXPServicio) value;
			TDEXPServicio dServicio = new TDEXPServicio();
			filtro = new HashMap();
			filtro.put("iCveExpediente", servicio.getICveExpediente() + "");
			filtro.put("iNumExamen", servicio.getINumExamen() + "");
			filtro.put("iCveServicio", servicio.getICveServicio() + "");
			try {
				// log("campos para buscar servicio: " + filtro.toString());
				r = dServicio.FindByAll(filtro);
				// log("encontrados: " + r.size());
			} catch (DAOException ex) {
				error("Error al buscar el registro en EXPRama", ex);
			}
			if (r != null && r.size() == 1) {
				servicio = (TVEXPServicio) r.get(0);
				// log("servicio antes de actualizar: " +
				// servicio.toHashMap().toString());
			} else {
				throw new DAOException(
						"La tabla EXPServicio no contiene el registro solicitado");
			}
			return servicio;
		}
		return null;
	}

	public void setUsuario(int usuario) {
		param.put("iCveUsuario", "" + usuario);
		// log("parametros despues de definir USUARIO: " + param.toString());
	}

	/**
	 * Metodo que eval�a los par�metros recibidos y asigna cadenas vac�as
	 * en caso de ser null.
	 * 
	 * @author Romeo S�nchez
	 */
	private void validaParametros() {
		tpoBusqueda = request.getParameter("tpoBusqueda");
		String cCveExpediente = request.getParameter("iCveExpediente");
		String cNumExamen = request.getParameter("iNumExamen");
		String cCvePersonal = request.getParameter("iCveExpediente");
		// System.out.println("cCvePersonal = " +cCvePersonal);
		String cCveUniMed = request.getParameter("iCveUniMed");
		String cCveProceso = request.getParameter("iCveProceso");
		String cCveModulo = request.getParameter("iCveModulo");
		String cCveRama = request.getParameter("iCveRama");
		String cCveServicio = request.getParameter("iCveServicio");
		String cUltimaRama = request.getParameter("ultimaRama");
		String cRamaInicial = request.getParameter("ramaInicial");
		String cCveUsuario = request.getParameter("iCveUsuario"); // siempre
																	// existe
		// System.out.println("Clave Usuario = "+request.getParameter("iCveUsuario"));
		// String cCveUsuario = "71"; // siempre existe

		if (tpoBusqueda == null || tpoBusqueda.equals("null")
				|| tpoBusqueda.equals("") || tpoBusqueda.equals("undefined")) {
			tpoBusqueda = "";
		} else {
			if (tpoBusqueda.equals("variosMedicos")) {
				variosMedicos = true;
			}
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
		if (cCveRama == null || cCveRama.equals("null") || cCveRama.equals("")
				|| cCveRama.equals("undefined")) {
			cCveRama = "";
		}
		if (cUltimaRama == null || cUltimaRama.equals("null")
				|| cUltimaRama.equals("") || cUltimaRama.equals("undefined")) {
			cUltimaRama = "";
			ultimaRama = false;
		} else if (cUltimaRama.equals("1")) {
			ultimaRama = true;
			// log("es la �ltima rama...");
		}
		if (cRamaInicial == null || cRamaInicial.equals("null")
				|| cRamaInicial.equals("") || cRamaInicial.equals("undefined")) {
			cRamaInicial = "";
			primeraRama = false;
		} else {
			primeraRama = true;
		}

		param.put("iCveExpediente", cCveExpediente);
		param.put("iNumExamen", cNumExamen);
		param.put("iCveServicio", cCveServicio);
		param.put("iCveRama", cCveRama);
		param.put("cCvePersonal", cCvePersonal);
		param.put("cCveModulo", cCveModulo);
		param.put("iCveUsuario", cCveUsuario);

	}

	/**
	 * Devuelve el siguiente registro por procesar de EXPRama.
	 * 
	 * @return un value object con el registro de la rama, o null si no hay
	 *         m�s registros.
	 * @author Romeo Sanchez
	 */
	public TVEXPRama getNextRama() {
		// log("getNextRama()");
		TDEXPRama dRama = new TDEXPRama();
		TVEXPRama rama = null;
		HashMap filtro = null;
		Vector r = null;
		filtro = new HashMap();
		filtro.put("iCveExpediente", param.get("iCveExpediente"));
		filtro.put("iNumExamen", param.get("iNumExamen"));
		filtro.put("iCveServicio", param.get("iCveServicio"));
		filtro.put("iCveRama", null); // para el caso de buscar todas las ramas
										// de un servicio
		filtro.put("lConcluido", "0");

		/*
		 * Si el proceso es por varios m�dicos, la rama se toma del valor
		 * seleccionado Si el proceso es por un solo m�dico, se busca la
		 * siguiente rama disponible
		 */

		if (variosMedicos) {
			filtro.put("iCveRama", param.get("iCveRama")); // para buscar una
															// rama espec�fica
		}

		try {
			r = dRama.findByPK(filtro);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPRama", ex);
		}
		if (r != null && r.size() != 0) {
			rama = (TVEXPRama) r.get(0); // obtiene el siguiente
			if (r.size() == 1) {
				ultimaRama = true; // s�lo queda una rama por procesar
			}
		}

		return rama;

	}

	/**
	 * Indica si existen m�s ramas sin concluir para el servicio
	 * proces�ndose actualmente.
	 * 
	 * @return un boolean indicando si existen m�s ramas o no.
	 */
	private boolean hayMasRamas() {
		// log("hayMasRamas()");
		TDEXPRama dRama = new TDEXPRama();
		TVEXPRama rama = null;
		HashMap filtro = null;
		Vector r = null;
		filtro = new HashMap();
		filtro.put("iCveExpediente", param.get("iCveExpediente"));
		filtro.put("iNumExamen", param.get("iNumExamen"));
		filtro.put("iCveServicio", param.get("iCveServicio"));
		filtro.put("iCveRama", null); // para el caso de buscar todas las ramas
										// de un servicio
		filtro.put("lConcluido", "0");

		try {
			r = dRama.findByPK(filtro);
			// log("hayMasRamas-ramas obtenidas: " + r.size());
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPRama", ex);
		}
		if (r != null && r.size() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo que busca los datos b�sicos del personal especificado. El
	 * n�mero de personal se toma de la variable de instancia correspondiente,
	 * previamente inicializada.
	 */
	private void setPersonal() {
		// System.out.println("Yendo a perdatos");
		// log("setPersonal()");
		int iCvePersonal = Integer.parseInt((String) param.get("cCvePersonal"));
		try {
			// System.out.println("iCvePersonal = " + iCvePersonal);
			personal = new TDPERDatos().findUser(iCvePersonal);
		} catch (DAOException ex) {
			error("Error al buscar los datos del personal", ex);
		}
	}

	/**
	 * Devuelve el Value Object que contiene los datos b�sicos del personal,
	 * previamente buscados.
	 * 
	 * @return el value object con los datos del personal
	 */
	public TVPERDatos getPersonal() {
		return this.personal;
	}

	/**
	 * Metodo que devuelve la lista de unidades m�dicas v�lidas
	 * 
	 * @return un Vector con los value objects de las unidades m�dicas
	 */
	public Vector getUniMedsValidas(String cCveProceso) {
		// log("getUniMedsValidas()");
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
		// log("getModulos()");
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
	public Vector getModulos(String cCveUniMed) {
		// log("getModulos(" + cCveUniMed + ")");
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
		// log("getProceso(" + param + ")");
		if (param == null || param.length() == 0) {
			return "";
		}
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
		// log("getServicios(" + hm.size() + ") servicios");
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
			// log("servicio obtenido:" + k + hm.get(k));
			result.addElement(usmed);
		}
		// Collections.sort(result);
		return result;
	}

	/**
	 * Devuelve la edad del personal.
	 * 
	 * @return la edad en a�os.
	 */
	public long getEdadPersonal() {
		long edad = dtFecha.getTime() - personal.getDtNacimiento().getTime();
		edad /= (1000 * 60 * 60 * 24 * 365.25);
		return edad;
	}

	/**
	 * Metodo que procesa los renglones obtenidos del request, y los coloca en
	 * la tabla de Hash correspondiente para insertar.
	 * 
	 * @return un int con el n�mero de renglones insertados.
	 */
	private int getTableValues() {
		TVEXPResultado v = null;
		int i = 0;
		int accion = 0;
		int n = Integer.parseInt(request.getParameter("hdTotalRows"));
		// log("registros obtenidos en HTML: " + n);
		int iCveExpediente = 0;
		int iNumExamen = 0;
		int iCveServicio = 0;
		int iCveRama = 0;
		int iCveSintoma = 0;

		int lAsignar = 0;
		int tpoResp = 0;
		String llave = "";
		boolean existe = false;
		boolean asignar = false;
		boolean alarma = false;
		iCveExpediente = Integer.parseInt(request
				.getParameter("iCveExpediente"));
		iNumExamen = Integer.parseInt(request.getParameter("iNumExamen"));
		iCveServicio = Integer.parseInt(request.getParameter("iCveServicio"));
		iCveRama = Integer.parseInt(request.getParameter("iCveRama"));
		float dValorI = 0.0f;
		float dValorF = 0.0f;
		int lLogico = 0;
		String cCaracter = "";
		String dvalori = "";
		String dvalorf = "";
		String llogico = "";
		String ccaracter = "";
		String cValRef = "";

		String cToken = request.getParameter("hdFlags");
		StringTokenizer stToken = new StringTokenizer(cToken, "|");
		// while (i < n) {
		while (stToken.hasMoreTokens()) {
			String cTemp = stToken.nextToken();
			// armar un V.O. para cada rengl�n de la pantalla de captura
			// y agregarlo a la tabla de Hash correspondiente
			v = new TVEXPResultado();

			tpoResp = Integer.parseInt(request.getParameter("iCveTpoResp_"
					+ cTemp));
			iCveSintoma = Integer.parseInt(request.getParameter("iCveSintoma_"
					+ cTemp));
			dvalori = request.getParameter("dValorI_" + cTemp);
			dvalorf = request.getParameter("dValorF_" + cTemp);
			llogico = request.getParameter("lLogico_" + cTemp);
			ccaracter = request.getParameter("cCaracter_" + cTemp);
			cValRef = request.getParameter("cValRef_" + cTemp);

			llave = iCveExpediente + "," + iNumExamen + "," + iCveServicio
					+ "," + iCveRama + "," + iCveSintoma;

			// llave primaria
			v.setICveExpediente(iCveExpediente);
			v.setINumExamen(iNumExamen);
			v.setICveServicio(iCveServicio);
			v.setICveRama(iCveRama);
			v.setICveSintoma(iCveSintoma);
			// campos con valores default

			// ********* CAMBIO POR MARCO GONZALEZ PRUEBA *********
			/*
			 * v.setDValorIni(dValorI); v.setDValorFin(dValorF);
			 * v.setLLogico(lLogico); v.setCCaracter(cCaracter);
			 * v.setCValRef(cValRef);
			 */

			// verificar que traiga valores para insertar, en cada tipo de dato,
			// y establecer el valor real
			switch (tpoResp) {
			case SI_NO:

				if (true) {
					// v.setLLogico(this.getToggleValue(llogico));
					// System.out.println("Antes de asignar logico: " +
					// llogico);
					v.setLLogico(this.getRadioValue(llogico));
					asignar = true;
				}
				break;
			case LETRAS_NUMEROS:
			case NOTAS:

				// //System.out.println("tipo letras/numeros");
				if (esValido(ccaracter) && ccaracter.length() != 0) {
					v.setCCaracter(ccaracter);
					asignar = true;
				}
				break;
			case NUMEROS:

				// //System.out.println("tipo numeros");
				if (esValido(dvalori) && !esCero(dvalori)) {
					// System.out.println("davori: " + dvalori + " ** " +
					// Float.parseFloat(dvalori));
					v.setDValorIni(Float.parseFloat(dvalori));
					asignar = true;
				}

				break;
			case RANGO:

				// //System.out.println("tipo rango");
				if ((esValido(dvalori) && esValido(dvalorf))
						&& (!esCero(dvalori) || !esCero(dvalorf))) {
					v.setDValorIni(Float.parseFloat(dvalori));
					v.setDValorFin(Float.parseFloat(dvalorf));
					asignar = true;
				}
				break;
			default:
				// System.out.println("ERROR: ninguna accion: " + llave);
			}

			if (asignar) {
				actualizar.add(v);
			}
			i++;
			existe = asignar = alarma = false; // re-set de las variables de
												// evaluaci�n
			log("en espera de actualizacion: " + v.toHashMap().toString());
		}
		// System.out.println(i + ": insertar: " + llave + ", total="
		// +insertar.size());
		return actualizar.size();
	}

	private boolean esValido(String valor) {
		if (valor == null || valor.equals("null") || valor.equals("")
				|| valor.equals("undefined")) {
			// es null
			return false;
		} else {
			return true;
		}
	}

	private int getToggleValue(String valor) {
		if (valor == null || valor.equals("null") || valor.equals("")
				|| valor.equals("undefined")) {
			// es null
			return 0;
		} else {
			return 1;
		}
	}

	private int getRadioValue(String valor) {
		// System.out.println("Metodo RadioValue: **" + valor + "**");
		if (valor == null || valor.equals("null") || valor.equals("")
				|| valor.equals("undefined")) {
			// System.out.println("regresa 99!!!");
			return 99;

		} else {
			// System.out.println("regresa 0/1!!!");
			if (valor.equals("0")) {
				return 0;
			} else {
				return 1;
			}
		}

		/*
		 * if (valor == null || valor.equals("null") || valor.equals("") ||
		 * valor.equals("undefined") || valor.equals("0")) { // es null return
		 * 0; } else { return 1; }
		 */
	}

	private boolean esCero(String valor) {
		if (Float.parseFloat(valor) == 0.0f) {
			return true;
		} else {
			return false;
		}
	}

	public TVEXPRama getCurrentRama() {
		return this.nextRama;
	}

	// -- Escribe en EXPDictamenServ para los servicios que no hacen
	// diagn�sticos y/o recomendaciones

	public void GuardarDiagRec() {
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			/**
			 * Inserta / Actualiza a EXPDictamenServ
			 */
			int lConcluye = 0;
			TVEXPDictamenServ vcListaCat = new TVEXPDictamenServ();
			Vector vListaCat = new Vector();
			vListaCat = this.findCatSol();
			int lDictamen = 0;
			if (vListaCat.size() > 0) {
				for (int i = 0; i < vListaCat.size(); i++) {
					vcListaCat = (TVEXPDictamenServ) vListaCat.get(i);
					if (request.getParameter("lDictamen"
							+ vcListaCat.getICveCategoria() + "_"
							+ vcListaCat.getICveMdoTrans()) != null) {
						lDictamen = Integer.parseInt(request.getParameter(
								"lDictamen" + vcListaCat.getICveCategoria()
										+ "_" + vcListaCat.getICveMdoTrans())
								.toString());
					} else {
						lDictamen = 0;
					}

					cClave = (String) dEXPDictamenServ.insertDicCat(null,
							this.getInputs(), vcListaCat.getICveCategoria(),
							vcListaCat.getICveMdoTrans(), lDictamen);
				}
			}

			/**
			 * Inserta / Actualiza a EXPServicio
			 */

			dEXPDictamenServ.insertExpSer(null, this.getInputs());

			String aCveDiagnostico[] = request
					.getParameterValues("iCveDiagnostico");
			String iCveEspecialidad = "";
			String iCveDiagnostico = "";

			if (aCveDiagnostico != null) {
				if (aCveDiagnostico.length > 0) {
					for (int w = 0; w < aCveDiagnostico.length; w++) {
						iCveEspecialidad = aCveDiagnostico[w].substring(
								aCveDiagnostico[w].lastIndexOf("_") + 1,
								aCveDiagnostico[w].length());
						iCveDiagnostico = aCveDiagnostico[w].substring(0,
								aCveDiagnostico[w].lastIndexOf("_"));
						dEXPDictamenServ.insertExpDia(null, this.getInputs(),
								iCveDiagnostico, iCveEspecialidad);
					}
				}
			}

			String aCveRecomendacion[] = request
					.getParameterValues("iCveRecomendacion");
			String iCveRecomendacion = "";

			if (aCveRecomendacion != null) {
				if (aCveRecomendacion.length > 0) {
					for (int x = 0; x < aCveRecomendacion.length; x++) {
						iCveEspecialidad = aCveRecomendacion[x].substring(
								aCveRecomendacion[x].lastIndexOf("_") + 1,
								aCveRecomendacion[x].length());
						iCveRecomendacion = aCveRecomendacion[x].substring(0,
								aCveRecomendacion[x].lastIndexOf("_"));
						dEXPDictamenServ.insertExpReco(null, this.getInputs(),
								iCveEspecialidad, iCveRecomendacion);
					}
				}
			}

			lConcluye = this.lConcluidoES();

			if (lConcluye == 0) {
				dEXPDictamenServ.updateEXPExamAplica(null, this.getInputs());
			}

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo GuardarDiagRec

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TVEXPDictamenServ vEXPDictamenServ = new TVEXPDictamenServ();
		try {
			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 1;
			}
			vEXPDictamenServ.setICveExpediente(iCampo);

			cCampo = "" + request.getParameter("iNumExamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 1;
			}
			vEXPDictamenServ.setINumExamen(iCampo);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iCveCategoria");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setICveCategoria(iCampo);

			cCampo = "" + request.getParameter("iCveServicio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 1;
			}
			vEXPDictamenServ.setICveServicio(iCampo);

			cCampo = "" + request.getParameter("lDictamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setLDictamen(iCampo);

			cCampo = "" + request.getParameter("cNotaMedica");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEXPDictamenServ.setCNotaMedica(cCampo);
			int iCveUsuario = ((TVUsuario) (((HttpServletRequest) request)
					.getSession(true).getAttribute("UsrID"))).getICveusuario();
			vEXPDictamenServ.setICveUsuDictamen(iCveUsuario);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPDictamenServ;
	}

	public Vector findCatSol() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.FindByCatSol(this.getInputs());
		} catch (Exception ex) {
			error("findCatSol", ex);
		}
		return vcDictamenServ;
	}

	public int lConcluidoES() {
		int lConcluye = 0;
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			lConcluye = dEXPDictamenServ.lConcluidoExpServ(null,
					this.getInputs());
		} catch (Exception ex) {
			error("lConcluidoES", ex);
		}
		return lConcluye;
	}

	// -- Termina la escritura en EXPDictamenServ

}
