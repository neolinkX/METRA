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
 * * Clase de configuracion para Handler de acciones para la p�gina pg070104034
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104034.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104034.jsp.png'>
 */
public class pg070104034CFG extends CFGListBase2 {

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
	/**
	 * Propiedad que almacenar� los datos del personal, mostrado en el
	 * encabezado del detalle del examen
	 */
	private TVPERDatos personal = new TVPERDatos();

	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private final int SI_NO = 1;
	private final int LETRAS_NUMEROS = 2;
	private final int NUMEROS = 3;
	private final int NOTAS = 4;
	private final int RANGO = 5;

	/**
	 * Método que devuelve una fecha en el formato dd/MM/yyyy
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
	 * medio del Método log() se muestran (true) o no (false).
	 */
	private boolean debug = false;

	private void log(Object obj) {
		if (debug) {
			// System.out.println(this.getClass().getName() + ":" +
			// obj.toString());
		}
	}

	public pg070104034CFG() {
		this.vParametros = new TParametro("07");
		cPaginas = "pg070104020.jsp|pg070104010.jsp"; // Ir a...
	}

	/**
	 * Método invocado desde clsConfig. S�lo se implementa.
	 */
	public void fillVector() {
		log("fillVector()");
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		HashMap filtro = null;

		this.validaParametros();
		this.setPersonal();
		param.put("cGenero", personal.getCSexo()); // el par�metro faltante

		if (cAccion.equalsIgnoreCase("Guardar")) {
			log(cAccion);
			Vector porInsertar = this.getTableValues();
			// System.out.println("* para insertar: " + porInsertar.toString() +
			// " Tama�o: " + porInsertar.size());

			if (porInsertar.size() > 0) {
				this.insertar(porInsertar);
			}

		}
		try {
			vDespliega = dMEDSintoma.findSintomaRamaServicio(param);
		} catch (DAOException ex) {
			error("Error al buscar los sintomas", ex);
		}

		log("fin de fillVector()");
	}

	public void insertar(Vector rows) {
		/*
		 * la funcionalidad original de este Método se ha cambiado para tomar la
		 * acci�n pertinente, seg�n las condiciones definidas en la regla de
		 * negocios durante la ejecuci�n del Método getTableValues().
		 */

		// se llenan las tablas de hash con los value objects correspondientes.
		if (false) {
			return; // ****para pruebas
		}

		log("generando conexion...");

		DbConnection dbConn = null;
		Connection conn = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			if (rows.size() != 0) { // si hay registros por insertar
				// System.out.println("Si hay registros por insertar !!!");
				// *********************
				this.generar(conn, rows);
				// *********************
			}

			conn.commit();
			log("committing...");
		} catch (Exception e) {
			try {
				conn.rollback();
				log("rolling back...");
				log("excepcion: " + e.getMessage());
				if (debug) {
					e.printStackTrace(); // solo habilitar para depuraci�n
				}
			} catch (Exception ex) {
				vErrores.acumulaError("", 14, "");
				error("Error al crear el registro", ex);
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
					log("--cerrando Connection...");
				}
				if (dbConn != null) {
					dbConn.closeConnection();
					log("--cerrando DbConnection...");
				}
			} catch (SQLException ex1) {
				vErrores.acumulaError("", 14, "");
				error("Error al cerrar la conexi�n", ex1);
			}
		}
		// super.Guardar();
	} // Método Guardar

	/**
	 * Indica si un registro determinado existe o no en la tabla correspondiente
	 * 
	 * @param obj
	 *            el value object con la llave que debe buscarse en la tabla
	 *            correspondiente.
	 * @return un boolean indicando si el registro buscado existe o no.
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	private boolean existe(HashBeanInterface obj) throws DAOException {
		Vector r = null;
		HashMap filtro = new HashMap();
		if (obj instanceof TVEXPServicio) {
			TVEXPServicio servicio = (TVEXPServicio) obj;
			filtro.put("iCveExpediente", servicio.getICveExpediente() + "");
			filtro.put("iNumExamen", servicio.getINumExamen() + "");
			filtro.put("iCveServicio", servicio.getICveServicio() + "");
			r = new TDEXPServicio().findByPK(filtro);
		} else if (obj instanceof TVEXPRama) {
			TVEXPRama rama = (TVEXPRama) obj;
			filtro.put("iCveExpediente", rama.getICveExpediente() + "");
			filtro.put("iNumExamen", rama.getINumExamen() + "");
			filtro.put("iCveServicio", rama.getICveServicio() + "");
			filtro.put("iCveRama", rama.getICveRama() + "");
			// System.out.println("pg070104034CFG");
			r = new TDEXPRama().findByPK(filtro);
		} else if (obj instanceof TVEXPResultado) {
			TVEXPResultado resultado = (TVEXPResultado) obj;
			String where = " where iCveExpediente = "
					+ resultado.getICveExpediente() + " and iNumExamen = "
					+ resultado.getINumExamen() + " and iCveServicio = "
					+ resultado.getICveServicio() + " and iCveRama = "
					+ resultado.getICveRama() + " and iCveSintoma = "
					+ resultado.getICveSintoma();
			r = new TDEXPResultado().FindByAll(where);
		} else if (obj instanceof TVEXPInterconsulta) {
			TVEXPInterconsulta interc = (TVEXPInterconsulta) obj;
			String where = " where iCveExpediente = "
					+ interc.getICveExpediente() + " and iNumExamen = "
					+ interc.getINumExamen() + " and iCveServicio = "
					+ interc.getICveServicio();
			r = new TDEXPInterconsulta().findByAll(where);
		}
		if (r != null && r.size() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * Extrae �nicamente los n�meros de ramas sin duplicados del vector que
	 * contiene los n�meros de ramas y los n�meros de resultado
	 * 
	 * @param datos
	 *            el vector con el dato compuesto de ramas y resultados
	 * @return una colecci�n de n�meros de ramas, sin duplicados
	 */
	private HashSet extraeRamas(Vector datos) {
		HashSet r = new HashSet();

		for (int i = 0; i < datos.size(); i++) {
			String[] x = (datos.elementAt(i).toString()).split("_");
			r.add(x[0]); // la primera posici�n del arreglo contiene el n�mero
							// de rama
		}
		return r;
	}

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
	private void generar(Connection conn, Vector actualizar)
			throws DAOException {
		// System.out.println("Marco ----> en generar() los parametros son: " +
		// param.toString());
		TFechas tfCampo = new TFechas();
		TDEXPResultado dEXPResultado = new TDEXPResultado();
		TDEXPRama dEXPRama = new TDEXPRama();
		TDEXPServicio dEXPServicio = new TDEXPServicio();
		TDEXPInterconsulta dEXPInterc = new TDEXPInterconsulta();
		TVEXPResultado resultado = new TVEXPResultado();
		TVEXPRama rama = new TVEXPRama();
		TVEXPServicio servicio = new TVEXPServicio();
		TVEXPInterconsulta interc = new TVEXPInterconsulta();
		HashSet sRamas = this.extraeRamas(actualizar);

		// System.out.println(" me lleva  --- registros por actualizar:" +
		// actualizar.size());
		int exp = ((String) param.get("iCveExpediente")) == null ? -1 : Integer
				.parseInt((String) param.get("iCveExpediente"));
		// System.out.println("-1 mensaje antes !!!! " + exp );
		int exm = ((String) param.get("iNumExamen")) == null ? -1 : Integer
				.parseInt((String) param.get("iNumExamen"));
		int srv = ((String) param.get("iCveServicio")) == null ? -1 : Integer
				.parseInt((String) param.get("iCveServicio"));
		// System.out.println("0 mensaje antes !!!! " + exp + "," + exm + "," +
		// srv);
		// definir los objetos para cada tabla por procesar.
		// ******* EXPServicio

		servicio.setICveExpediente(exp);
		servicio.setINumExamen(exm);
		servicio.setICveServicio(srv);
		servicio.setLConcluido(0);

		java.sql.Date dFecha;
		TFechas tFecha = new TFechas();
		String cFecha = "";

		cFecha = "" + request.getParameter("dtPropAtencion");
		dFecha = tFecha.getDateSQL(cFecha);

		servicio.setDtInicio(dFecha);
		servicio.setDtFin(dFecha);

		// insertar EXPServicio, si no existe

		if (!existe(servicio)) {
			dEXPServicio.insert(conn, servicio);
		}

		// insertar EXPRama, si no existe
		// ******* EXPRama
		rama.setICveExpediente(exp);
		rama.setINumExamen(exm);
		rama.setICveServicio(srv);
		Iterator it = sRamas.iterator();
		while (it.hasNext()) {
			rama.setICveRama(Integer.parseInt(it.next().toString()));
			log("rama buscada: " + rama.toHashMap().toString());
			if (!existe(rama)) {
				log("no existe la rama");
				dEXPRama.insert(conn, rama);
			}
		}

		// insertar EXPResultado, si no existe
		// actualizar los registros de EXPResultado
		for (int i = 0; i < actualizar.size(); i++) {
			// actualizar EXPResultado
			String ramaRes = (String) actualizar.get(i);
			String[] x = ramaRes.split("_");
			int iCveRama = Integer.parseInt(x[0]);
			int iCveSintoma = Integer.parseInt(x[1]);

			resultado.setICveExpediente(exp);
			resultado.setINumExamen(exm);
			resultado.setICveServicio(srv);
			resultado.setICveRama(iCveRama);
			resultado.setICveSintoma(iCveSintoma);

			// manejar en una transacci�n
			log("resultado buscado: " + resultado.toHashMap().toString());
			if (!existe(resultado)) {
				log("no existe el resultado");
				cClave = (String) dEXPResultado.insert(conn, resultado);
			}
		}

		// ******* EXPInterconsulta
		interc.setICveExpediente(exp);
		interc.setINumExamen(exm);
		interc.setICveServicio(srv);
		interc.setICveUniMed(Integer.parseInt(param.get("iCveUniMed")
				.toString()));
		interc.setICveModulo(Integer.parseInt(param.get("iCveModulo")
				.toString()));
		interc.setDtSolicitud(new java.sql.Date(new java.util.Date().getTime())); // fecha
																					// de
																					// hoy
		interc.setDtPropAtencion(tfCampo.getDateSQL(param.get("dtPropAtencion")
				.toString()));
		interc.setCMtvoSolicitud(param.get("cMtvoSolicitud").toString());

		// insertar EXPInterconsulta, si no existe
		if (!existe(interc)) {
			dEXPInterc.insert(conn, interc);
		}

	}

	/**
	 * Devuelve los campos de un registro, en base a una llave primaria.
	 * <p>
	 * El Método hace una b�squeda en la tabla correspondiente al tipo de value
	 * object que se recibe como argumento, y si lo encuentra, devuelve un nuevo
	 * value object con los campos faltantes.
	 * 
	 * @param value
	 *            el value object, como argumento polim�rfico para permitir
	 *            invocar el mismo Método, independientemente del tipo de value
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
			// System.out.println("pg070104034CFG--");
			try {
				r = dRama.findByPK(filtro);
			} catch (DAOException ex) {
				error("Error al buscar el registro en EXPRama", ex);
			}
			if (r != null && r.size() == 1) {
				rama = (TVEXPRama) r.get(0);
				log("rama antes de actualizar: " + rama.toHashMap().toString());
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
				log("campos para buscar servicio: " + filtro.toString());
				r = dServicio.FindByAll(filtro);
				log("encontrados: " + r.size());
			} catch (DAOException ex) {
				error("Error al buscar el registro en EXPRama", ex);
			}
			if (r != null && r.size() == 1) {
				servicio = (TVEXPServicio) r.get(0);
				log("servicio antes de actualizar: "
						+ servicio.toHashMap().toString());
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
		log("parametros despues de definir USUARIO: " + param.toString());
	}

	/**
	 * Método que eval�a los parametros recibidos y asigna cadenas vac�as en
	 * caso de ser null.
	 * 
	 * @author Romeo S�nchez
	 */
	private void validaParametros() {
		tpoBusqueda = request.getParameter("tpoBusqueda");
		String cCveExpediente = request.getParameter("iCveExpediente");
		String cNumExamen = request.getParameter("iNumExamen");
		String cCvePersonal = request.getParameter("iCvePersonal");
		String cCveUniMed = request.getParameter("iCveUniMed");
		String cCveProceso = request.getParameter("iCveProceso");
		String cCveModulo = request.getParameter("iCveModulo");
		String cCveRama = request.getParameter("iCveRama");
		String cCveServicio = request.getParameter("iCveServicio");
		String cUltimaRama = request.getParameter("ultimaRama");
		String cRamaInicial = request.getParameter("ramaInicial");
		String cCveUsuario = request.getParameter("iCveUsuario"); // siempre
																	// existe
		String hdUniMedMod = request.getParameter("hdUniMedMod");
		String dtPropAtencion = request.getParameter("dtPropAtencion");
		String cMtvoSolicitud = request.getParameter("cMtvoSolicitud");

		if (tpoBusqueda == null || tpoBusqueda.equals("null")
				|| tpoBusqueda.equals("") || tpoBusqueda.equals("undefined")) {
			tpoBusqueda = "";
		} else {
			if (tpoBusqueda.equals("variosMedicos")) {
				variosMedicos = true;
			}
		}
		// determinar si se reciben los parametros necesarios para los filtros
		if (hdUniMedMod == null || hdUniMedMod.equals("null")
				|| hdUniMedMod.equals("") || hdUniMedMod.equals("undefined")) {
			hdUniMedMod = ""; // no debe entrar aqu�
		} else {
			String[] x = hdUniMedMod.split("_");
			cCveUniMed = x[0];
			cCveModulo = x[1];
		}
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
		if (cCveServicio == null || cCveServicio.equals("null")
				|| cCveServicio.equals("") || cCveServicio.equals("undefined")) {
			cCveServicio = "-1";
		}
		if (cCveRama == null || cCveRama.equals("null") || cCveRama.equals("")
				|| cCveRama.equals("undefined")) {
			cCveRama = "";
		}
		if (dtPropAtencion == null || dtPropAtencion.equals("null")
				|| dtPropAtencion.equals("")
				|| dtPropAtencion.equals("undefined")) {
			dtPropAtencion = "";
		}
		if (cMtvoSolicitud == null || cMtvoSolicitud.equals("null")
				|| cMtvoSolicitud.equals("")
				|| cMtvoSolicitud.equals("undefined")) {
			cMtvoSolicitud = "";
		}
		if (cUltimaRama == null || cUltimaRama.equals("null")
				|| cUltimaRama.equals("") || cUltimaRama.equals("undefined")) {
			cUltimaRama = "";
			ultimaRama = false;
		} else if (cUltimaRama.equals("1")) {
			ultimaRama = true;
			log("es la �ltima rama...");
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
		param.put("iCveUniMed", cCveUniMed);
		param.put("iCveModulo", cCveModulo);
		param.put("iCveUsuario", cCveUsuario);
		param.put("dtPropAtencion", dtPropAtencion);
		param.put("cMtvoSolicitud", cMtvoSolicitud);

		log("parametros establecidos:" + param.toString());

	}

	/**
	 * Método que busca los datos b�sicos del personal especificado. El n�mero
	 * de personal se toma de la variable de instancia correspondiente,
	 * previamente inicializada.
	 */
	private void setPersonal() {
		log("setPersonal()");
		// Antes
		// int iCvePersonal = Integer.parseInt( (String)
		// param.get("cCvePersonal"));
		int iCvePersonal = Integer.parseInt((String) param
				.get("iCveExpediente"));

		try {
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
	 * Método que devuelve la lista de servicios marcados como interconsulta,
	 * para usarse en un select de HTML
	 * 
	 * @return un Vector con los value objects de los servicio
	 */
	public Vector getSelectServicios() {
		log("getSelectServicios()");
		TDMEDServicio srv = new TDMEDServicio();
		Vector servicios = new Vector();
		try {
			// buscar los servicios marcados como interconsulta, que sean
			// activos
			servicios = srv.FindByAll(" where lInterConsulta=1 and lActivo=1",
					" order by cDscServicio ");
		} catch (Exception ex) {
			error("getSelectServicios", ex);
		}
		TVMEDServicio foo = new TVMEDServicio();
		foo.setICveServicio(-1);
		foo.setCDscServicio("Seleccione...");
		servicios.insertElementAt(foo, 0);
		return servicios;
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
	 * Método que procesa los renglones obtenidos del request, y los coloca en
	 * la tabla de Hash correspondiente para insertar en las tablas.
	 * 
	 * @return un Vector con las claves de los sintomas a insertar.
	 */
	private Vector getTableValues() {
		int iCveServicio = 0;
		Vector sintomas = new Vector();
		String cCveServicio = request.getParameter("iCveServicio");
		// System.out.println("Servicio: " + cCveServicio);
		if (cCveServicio == null || cCveServicio.equals("")
				|| cCveServicio.equals("null")) {
			cCveServicio = "";
		}
		// obtener todos los sintomas seleccionados
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String tmp = e.nextElement().toString();
			if (!tmp.startsWith("checkSintoma")) {
				// ignorar los parametros que no correspondan a los checkboxes
				// de s�ntomas
				continue;
			}
			sintomas.add(tmp.substring("checkSintoma".length())); // nuevo
																	// String
																	// con la
																	// rama y
																	// sintoma
																	// (r_s)
		}
		// System.out.println("sintomas por insertar: " + sintomas);
		return sintomas;
	}

	/**
	 * Devuelve una cadena con la descripci�n de la unidad m�dica y m�dulo
	 * seleccionados
	 * 
	 * @param ramaSint
	 *            la clave de la unidad m�dica y el m�dulo, separados por "_"
	 * @return la descripci�n de la unidad m�dica y el m�dulo
	 */
	public String getSelectedUniMedModulo() {
		TDGRLServUM dGRLServUM = new TDGRLServUM();
		StringBuffer s = new StringBuffer();
		HashMap p = new HashMap();
		Vector result = new Vector();
		String selec = request.getParameter("hdUniMedMod");
		if (selec == null || selec.length() == 0 || selec.equals("null")) {
			selec = "";
		}
		try {
			result = dGRLServUM.findByAll(""
					+ request.getParameter("iCveServicio"));
			log("obtenidos: " + result.size());
		} catch (DAOException ex) {
			error("Error al obtener las Unidades Medicas/Modulos", ex);
		}
		for (int i = 0; i < result.size(); i++) {
			TVGRLServUM bar = (TVGRLServUM) result.elementAt(i);
			String selected = "";
			String llave = "";
			String valor = "";
			llave = bar.getICveUniMed() + "_" + bar.getICveModulo();
			if (selec.length() != 0 && llave.equals(selec)) {
				return bar.getCDscUniMed() + " - " + bar.getCDscModulo();
			}
		}
		return "&nbsp;";
	}

	/**
	 * Devuelve un select de HTML para mostrar las unidades m�dicas y m�dulos
	 * 
	 * @return el String con la expresi�n HTML
	 */
	public String getSelectUniMedModulo() {
		TDGRLServUM dGRLServUM = new TDGRLServUM();
		StringBuffer s = new StringBuffer();
		HashMap p = new HashMap();
		Vector result = new Vector();
		String selec = request.getParameter("hdUniMedMod");
		if (selec == null || selec.length() == 0 || selec.equals("null")) {
			selec = "";
		}
		try {
			result = dGRLServUM.findByAll(""
					+ request.getParameter("iCveServicio"));
			log("obtenidos: " + result.size());
		} catch (DAOException ex) {
			error("Error al obtener las Unidades Medicas/Modulos", ex);
		}
		TVGRLServUM foo = new TVGRLServUM();
		foo.setICveUniMed(-1);
		foo.setICveModulo(-1);
		foo.setICveServicio(-1);
		foo.setCDscUniMed("Seleccione...");
		foo.setCDscModulo("");
		result.insertElementAt(foo, 0);

		s.append("<select name='hdUniMedMod'>");
		for (int i = 0; i < result.size(); i++) {
			TVGRLServUM bar = (TVGRLServUM) result.elementAt(i);
			String selected = "";
			String llave = "";
			String valor = "";
			llave = bar.getICveUniMed() + "_" + bar.getICveModulo();
			if (i == 0) {
				valor = "Seleccione...";
			} else {
				valor = bar.getCDscUniMed() + " - " + bar.getCDscModulo();
			}

			if (selec.length() != 0 && llave.equals(selec)) {
				selected = " selected ";
			}
			s.append("<option ").append(selected).append(" value='")
					.append(llave).append("'>").append(valor)
					.append("</option>");
		}
		s.append("</select>");

		return s.toString();
	}
}
