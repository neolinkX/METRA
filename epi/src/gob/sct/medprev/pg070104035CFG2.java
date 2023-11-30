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
 * * Clase de configuraciï¿½n para Handler de acciones para la pï¿½gina pg070104035
 * <p>
 * Administraciï¿½n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 * @author <dd>AG SA
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104035.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104035.jsp.png'>
 */
public class pg070104035CFG2 extends CFGListBase2 {

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
	 * Propiedad que almacenarï¿½ los datos del personal, mostrado en el
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
	 * MÃ©todo que devuelve una fecha en el formato dd/MM/yyyy
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
	 * Propiedad que indica si los mensajes enviados a la salida estï¿½ndar por
	 * medio del MÃ©todo log() se muestran (true) o no (false).
	 */
	private boolean debug = false;

	private void log(Object obj) {
		if (debug) {
			// System.out.println(this.getClass().getName() + ":" +
			// obj.toString());
		}
	}

	public pg070104035CFG2() {
		this.vParametros = new TParametro("07");
		cPaginas = "pg070201011.jsp"; // Ir a...
	}

	/**
	 * MÃ©todo invocado desde clsConfig. Sï¿½lo se implementa.
	 */
	public void fillVector() {
		/*TDEXPResultado dEXPResultado = new TDEXPResultado();
		HashMap filtro = null;

		this.validaParametros();
		//this.setPersonal();
		if (personal.getCSexo() != null)
			param.put("cGenero", personal.getCSexo()); // el parï¿½metro
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
		 * Si el proceso es por un sï¿½lo mï¿½dico, deben obtenerse todas las ramas
		 * del servicio e iterar para procesar cada una de ellas.
		 * 
		 * Si el proceso es por varios mï¿½dicos, se debe tomar la rama que viene
		 * seleccionada y procesar sï¿½lo esa rama.
		 */
		/*nextRama = this.getNextRama();

		try {
			if (nextRama == null) { // si ya no hay mï¿½s ramas
				vDespliega = new Vector(); // no enviar mï¿½s datos
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
				// existe al menos una rama mï¿½s
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
															// parï¿½metro se
															// envï¿½a...
				// log("filtro para buscar resultados: " + filtro.toString());
				vDespliega = dEXPResultado.findResultadoSintoma(filtro);
				iNumReg = vDespliega.size();
			}
		} catch (Exception ex) {
			error("fillVector", ex);
		}
		// log("fin de fillVector()");
		 * */
		 
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
	 * MÃ©todo Guardar
	 */
	public int Guardar(String iCveServicio, String iCveRama,String ciCveExpediente, String cExamen, String stToken, int usuario) {
		System.out.println("Por fin a guardar");
		int regresa = 0;
		// log("en Guardar() los parametros son: " + param.toString());
		/*
		 * la funcionalidad original de este MÃ©todo se ha cambiado para tomar la
		 * acciï¿½n pertinente, segï¿½n las condiciones definidas en la regla de
		 * negocios durante la ejecuciï¿½n del MÃ©todo getTableValues().
		 */

		// se llenan las tablas de hash con los value objects correspondientes.
		//int n = this.getTableValues(stToken);
		int n = this.getTableValuesDos(stToken, ciCveExpediente, cExamen, iCveServicio, iCveRama);
		// log("se obtuvieron " + n + " registros de la forma HTML");
		System.out.println("n="+n);
		if (false) {
			return regresa; // ****para pruebas
		}

		// log("generando conexion...");

		DbConnection dbConn = null;
		Connection conn = null;
		System.out.println("1");
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			System.out.println("2");
			if (actualizar.size() != 0) { // si hay registros por insertar
				if(Integer.parseInt(iCveServicio)==68 && 
						(!this.ValidaCapturaDeGraficasAudiometria(ciCveExpediente,cExamen, "68"))){
					vErrores.acumulaError("", 27, "Es necesario capturar gráficas de audiometría");
				}
				else{
					System.out.println("··································································");
					System.out.println(iCveServicio);
					System.out.println(iCveRama);
					System.out.println(ciCveExpediente);
					System.out.println(cExamen);
					System.out.println("··································································");
				this.actualizar(conn,iCveServicio, iCveRama,ciCveExpediente, cExamen, usuario);
				if (!lAudio) {
					this.audiometriadel(Integer.parseInt(ciCveExpediente), Integer
							.parseInt(cExamen),
							Integer.parseInt(iCveServicio));
					this.audiometriai();
					this.audiometriad();
					lAudio = true;
				}}
				System.out.println("-3");
			}
			conn.commit();
			regresa=1;
			// log("committing...");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				// log("rolling back...");
				// log(e.getMessage());
				// e.printStackTrace(); // solo habilitar para depuraciï¿½n
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
				error("Error al cerrar la conexiï¿½n", ex1);
			}
		}
		// super.Guardar();
		return regresa;
	} // MÃ©todo Guardar

	/**
	 * Aplica la lï¿½gica de negocios para actualizaciï¿½n de registros.
	 * 
	 * @param conn
	 *            La conexiï¿½n recibida, para actualizar en el contexto de una
	 *            transacciï¿½n
	 * @throws DAOException
	 *             si no se encuentra alguno de los registros por actualizar.
	 * @author Romeo Sï¿½nchez
	 */
	private void actualizar(Connection conn,String iCveServicio, String iCveRama,String ciCveExpediente, String cExamen, int usuario) throws DAOException {
		// log("en actualizar() los parametros son: " + param.toString());
		System.out.println("actualizar = "+usuario);
		TDEXPResultado dEXPResultado = new TDEXPResultado();
		TDEXPResultadoDos dEXPResultadoDos = new TDEXPResultadoDos();
		TDEXPRama dEXPRama = new TDEXPRama();
		TDEXPServicio dEXPServicio = new TDEXPServicio();
		TVEXPResultado resultado = null;
		TVEXPRama rama = new TVEXPRama();
		TVEXPServicio servicio = new TVEXPServicio();
		// log("registros por actualizar:" + actualizar.size());
		System.out.println("1");
		int exp = ((String) ciCveExpediente) == null ? -1 : Integer
				.parseInt((String) ciCveExpediente);
		int exm = ((String) cExamen) == null ? -1 : Integer
				.parseInt((String) cExamen);
		int srv = ((String) iCveServicio) == null ? -1 : Integer
				.parseInt((String) iCveServicio);
		int ram = ((String) iCveRama) == null ? -1 : Integer
				.parseInt((String) iCveRama);
		
		System.out.println("2");
		
		if (exp > -1 && exm > -1 && srv > -1 && ram > -1) {
			// Validaciï¿½n
			Vector vRama = new Vector();
			vRama = (new TDEXPRama()).getLConcluido(String.valueOf(exp),
					String.valueOf(exm), String.valueOf(srv),
					String.valueOf(ram));
			System.out.println("-3");
			if (vRama.size() > 0
					&& ((TVEXPRama) vRama.get(0)).getIConcluido() == 0) {
					// actualizar los registros de EXPResultado
				System.out.println("4");
				for (int i = 0; i < actualizar.size(); i++) {
					// actualizar EXPResultado
					resultado = (TVEXPResultado) actualizar.get(i);
					 log("actualizar:" + resultado.toHashMap().toString());
					try {
						// manejar en una transacciï¿½n
						if(resultado.getICveTpoResp() == 14 || 
							resultado.getICveTpoResp() == 15 ||
							resultado.getICveTpoResp() == 16){
							if(resultado.getICveTpoResp() == 14){
								System.out.println("--Inserta 14");
							cClave = (String) dEXPResultadoDos.insertResp14(conn, resultado);}
							if(resultado.getICveTpoResp() == 15){
								//System.out.println("--Inserta 15");
								cClave = (String) dEXPResultadoDos.insertResp15(conn, resultado);}
							if(resultado.getICveTpoResp() == 16){
								//System.out.println("--Inserta 16");
								cClave = (String) dEXPResultadoDos.insertResp16(conn, resultado);}
						}else{
							cClave = (String) dEXPResultado.update(conn, resultado);							
						}
						System.out.println("cClave="+cClave);
					} catch (Exception ex) {
						ex.printStackTrace();
						vErrores.acumulaError("", 14, "");
						error("Error al actualizar el sintoma "
								+ resultado.getICveSintoma()
								+ " de EXPResultado ", ex);
					}
				}
				

				///Ingresar datos del Spot Vital  Signs LXi/////
				if(srv ==  11){
					boolean SVSLXi = Boolean.parseBoolean(request.getParameter("SVSLXi"));
					if(SVSLXi){
						cClave = (String) dEXPResultado.updateSPV(conn, resultado, "" + request.getParameter("DatosAdicionales"));
						//System.out.println(request.getParameter("DatosAdicionales"));
					}
				}

				// log("--antes de actualizar ramas y servicios, los parï¿½metros son: "
				// + param.toString());

				// buscar registro, y actualizarlo, para conservar los datos
				// actuales, y solo modificar los requeridos
				// llenar la llave primaria de EXPRama, utilizada para la
				// bï¿½squeda
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
													// sï¿½lo si no tiene
					}
					rama.setDtFin(dtFecha);
					rama.setIConcluido(1); // debï¿½a ser setLConcluido...
					//rama.setICveUsuAplica(Integer.parseInt((String) "1"));
					rama.setICveUsuAplica(usuario);
					// actualizar el registro
					String ramaUpdated = (String) dEXPRama.update(conn, rama);
					// log("rama actualizada:" + ramaUpdated);
				}
				
				//FinalizaServicio////
				this.terminarServicioDos(rama);
				
			} else {
				throw new DAOException(
						"No se encontro el registro parent (EXPRama) para la actualizacion");
			}
			System.out.println("actualizar Fin");
		} // Se va a buscar un expediente
	}

	private void terminarServicio() throws DAOException {
		TDEXPServicio dEXPServicio = new TDEXPServicio();
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
		servicio = (TVEXPServicio) this.llenarRegistro(servicio);

		if (servicio != null) {
			// log("existe el servicio: " + servicio.toHashMap());

			/*
			 * Establecer los valores de los campos que se deben actualizar
			 * lConcluido dtInicio (si es la primera rama) dtFin (si es la
			 * ï¿½ltima rama)
			 */

			if (servicio.getDtInicio() == null) {
				// log("servicio sin fecha de inicio");
				servicio.setDtInicio(dtFecha); // poner fecha de inicio sï¿½lo
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
			// log("servicio actualizado:" + servicioUpdated);
		} else {
			throw new DAOException(
					"No se encontro el registro parent (EXPServicio) para la actualizacion");
		}
		// log("******** terminando servicio:" +
		// servicio.toHashMap().toString());
	}
	
	

	private void terminarServicioDos(HashBeanInterface value) throws DAOException {
		

		HashMap filtro = null;
		Vector r = null;
			TVEXPRama rama = (TVEXPRama) value;
			TDEXPRama dRama = new TDEXPRama();
			filtro = new HashMap();
			filtro.put("iCveExpediente", rama.getICveExpediente() + "");
			filtro.put("iNumExamen", rama.getINumExamen() + "");
			filtro.put("iCveServicio", rama.getICveServicio() + "");
			filtro.put("iCveRama", rama.getICveRama() + "");
			String filtros = "";

		
		TDEXPServicio dEXPServicio = new TDEXPServicio();
		TVEXPServicio servicio = new TVEXPServicio();
		int exp = rama.getICveExpediente();
		int exm = rama.getINumExamen();
		int srv = rama.getICveServicio();
		int ram = rama.getICveRama();
		
		if(this.getRamasPendientesI(exp+"", exm+"", srv+"")<=1){
		// log("Primera rama... " + primeraRama + " actualizando");
		// log("Ultima rama... " + ultimaRama + " actualizando");
		servicio.setICveExpediente(exp);
		servicio.setINumExamen(exm);
		servicio.setICveServicio(srv);
		servicio = (TVEXPServicio) this.llenarRegistro(servicio);

		if (servicio != null) {
			// log("existe el servicio: " + servicio.toHashMap());

			/*
			 * Establecer los valores de los campos que se deben actualizar
			 * lConcluido dtInicio (si es la primera rama) dtFin (si es la
			 * ï¿½ltima rama)
			 */

			if (servicio.getDtInicio() == null) {
				// log("servicio sin fecha de inicio");
				servicio.setDtInicio(dtFecha); // poner fecha de inicio sï¿½lo
												// si no tiene
			}
			// log("ultima rama, actualizar servicio");
			servicio.setDtFin(dtFecha);
			servicio.setLConcluido(1);
			// log("servicio por actualizar: " +
			// servicio.toHashMap().toString());
			servicio.setICveUsuAplica(rama.getICveUsuAplica());
			
			// actualizar el registro
			String servicioUpdated = (String) dEXPServicio.update(null,
					servicio);
			// log("servicio actualizado:" + servicioUpdated);
		} else {
			throw new DAOException(
					"No se encontro el registro parent (EXPServicio) para la actualizacion");
		}
		}
		// log("******** terminando servicio:" +
		// servicio.toHashMap().toString());
	}

	/**
	 * Devuelve los campos de un registro, en base a una llave primaria.
	 * <p>
	 * El MÃ©todo hace una bï¿½squeda en la tabla correspondiente al tipo de value
	 * object que se recibe como argumento, y si lo encuentra, devuelve un nuevo
	 * value object con los campos faltantes.
	 * 
	 * @param value
	 *            el value object, como argumento polimï¿½rfico para permitir
	 *            invocar el mismo MÃ©todo, independientemente del tipo de value
	 *            object requerido.
	 * @return un value object con los datos encontrados en la bï¿½squeda. Si no
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
			String filtros = "";
			filtros = " EXPRama.iCveExpediente = " + rama.getICveExpediente()
					+ " and EXPRama.iNumExamen = " + rama.getINumExamen()
					+ " and EXPRama.iCveServicio = " + rama.getICveServicio()
					+ " and EXPRama.iCveRama= " + rama.getICveRama() + " ";
			try {
				r = dRama.findByPK2(filtros);
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
	 * MÃ©todo que evalï¿½a los parï¿½metros recibidos y asigna cadenas vacï¿½as en
	 * caso de ser null.
	 * 
	 * @author Romeo Sï¿½nchez
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

		if (tpoBusqueda == null || tpoBusqueda.equals("null")
				|| tpoBusqueda.equals("") || tpoBusqueda.equals("undefined")) {
			tpoBusqueda = "";
		} else {
			if (tpoBusqueda.equals("variosMedicos")) {
				variosMedicos = true;
			}
		}
		// determinar si se reciben los parï¿½metros necesarios para los filtros
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
			// log("es la ï¿½ltima rama...");
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
	 * @return un value object con el registro de la rama, o null si no hay mï¿½s
	 *         registros.
	 * @author Romeo Sanchez
	 */
	public TVEXPRama getNextRama() {
		// log("getNextRama()");
		TDEXPRama dRama = new TDEXPRama();
		TVEXPRama rama = null;
		HashMap filtro = null;
		Vector r = null;
		/*
		 * filtro = new HashMap(); filtro.put("iCveExpediente",
		 * param.get("iCveExpediente")); filtro.put("iNumExamen",
		 * param.get("iNumExamen")); filtro.put("iCveServicio",
		 * param.get("iCveServicio")); filtro.put("iCveRama", null); // para el
		 * caso de buscar todas las ramas de un servicio
		 * filtro.put("lConcluido", "0");
		 */
		String filtros = "";
		filtros = " EXPRama.iCveExpediente = " + param.get("iCveExpediente")
				+ " and EXPRama.iNumExamen = " + param.get("iNumExamen")
				+ " and EXPRama.iCveServicio = " + param.get("iCveServicio") +
				// " and EXPRama.iCveRama= "+param.get("iCveRama")+
				" and EXPRama.lConcluido= 0 ";

		/*
		 * Si el proceso es por varios mï¿½dicos, la rama se toma del valor
		 * seleccionado Si el proceso es por un solo mï¿½dico, se busca la
		 * siguiente rama disponible
		 */

		if (variosMedicos) {
			filtro.put("iCveRama", param.get("iCveRama")); // para buscar una
															// rama especifica
		}

		try {
			r = dRama.findByPK2(filtros);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPRama", ex);
		}
		if (r != null && r.size() != 0) {
			rama = (TVEXPRama) r.get(0); // obtiene el siguiente
			if (r.size() == 1) {
				ultimaRama = true; // sï¿½lo queda una rama por procesar
			}
		}

		return rama;

	}

	/**
	 * Devuelve el siguiente registro por procesar de EXPRama.
	 * 
	 * @return un value object con el registro de la rama, o null si no hay mï¿½s
	 *         registros.
	 * @author Romeo Sanchez
	 */
	public TVEXPRama getNextRama2() {
		// log("getNextRama()");
		TDEXPRama dRama2 = new TDEXPRama();
		TVEXPRama rama2 = null;
		HashMap filtro2 = null;
		Vector r2 = null;
		/*
		 * filtro2 = new HashMap(); filtro2.put("iCveExpediente",
		 * param.get("iCveExpediente")); filtro2.put("iNumExamen",
		 * param.get("iNumExamen")); filtro2.put("iCveServicio",
		 * param.get("iCveServicio")); filtro2.put("iCveRama", null); // para el
		 * caso de buscar todas las ramas de un servicio
		 * filtro2.put("lConcluido", "0");
		 */
		String filtros = "";
		filtros = " EXPRama.iCveExpediente = " + param.get("iCveExpediente")
				+ " and EXPRama.iNumExamen = " + param.get("iNumExamen")
				+ " and EXPRama.iCveServicio = " + param.get("iCveServicio") +
				// " and iCveRama= "+param.get("iCveRama")+
				" and EXPRama.lConcluido= 0 ";
		/*
		 * Si el proceso es por varios medicos, la rama se toma del valor
		 * seleccionado Si el proceso es por un solo medico, se busca la
		 * siguiente rama disponible
		 */

		if (variosMedicos) {
			filtro2.put("iCveRama", param.get("iCveRama")); // para buscar una
															// rama especï¿½fica
		}

		try {
			r2 = dRama2.findByPK2(filtros);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPRama", ex);
		}
		if (r2 != null && r2.size() != 0) {
			rama2 = (TVEXPRama) r2.get(0); // obtiene el siguiente

		}

		return rama2;

	}

	/**
	 * Indica si existen mï¿½s ramas sin concluir para el servicio procesï¿½ndose
	 * actualmente.
	 * 
	 * @return un boolean indicando si existen mï¿½s ramas o no.
	 */
	private boolean hayMasRamas() {
		// log("hayMasRamas()");
		TDEXPRama dRama = new TDEXPRama();
		TVEXPRama rama = null;
		HashMap filtro = null;
		Vector r = null;
		/*
		 * filtro = new HashMap(); filtro.put("iCveExpediente",
		 * param.get("iCveExpediente")); filtro.put("iNumExamen",
		 * param.get("iNumExamen")); filtro.put("iCveServicio",
		 * param.get("iCveServicio")); filtro.put("iCveRama", null); // para el
		 * caso de buscar todas las ramas de un servicio
		 * filtro.put("lConcluido", "0");
		 */
		String filtros = "";
		filtros = " EXPRama.iCveExpediente = " + param.get("iCveExpediente")
				+ " and EXPRama.iNumExamen = " + param.get("iNumExamen")
				+ " and EXPRama.iCveServicio = " + param.get("iCveServicio")
				+ " and EXPRama.lConcluido= 0 ";
		try {
			r = dRama.findByPK2(filtros);
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
	 * MÃ©todo que busca los datos bï¿½sicos del personal especificado. El nï¿½mero
	 * de personal se toma de la variable de instancia correspondiente,
	 * previamente inicializada.
	 */
	private void setPersonal() {
		// log("setPersonal()");
		System.out.println("//"+param.get("cCvePersonal"));
		int iCvePersonal = Integer.parseInt((String) param.get("cCvePersonal"));
		try {
			personal = new TDPERDatos().findUser(iCvePersonal);
		} catch (DAOException ex) {
			error("Error al buscar los datos del personal", ex);
		}
	}

	/**
	 * Devuelve el Value Object que contiene los datos bï¿½sicos del personal,
	 * previamente buscados.
	 * 
	 * @return el value object con los datos del personal
	 */
	public TVPERDatos getPersonal() {
		return this.personal;
	}

	/**
	 * MÃ©todo que devuelve la lista de unidades mï¿½dicas vï¿½lidas
	 * 
	 * @return un Vector con los value objects de las unidades mï¿½dicas
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
	 * MÃ©todo que devuelve todos los mï¿½dulos correspondientes a la unidad mï¿½dica
	 * indicada en el parï¿½metro "iCveUniMed" del request de HTTP.
	 * 
	 * @return un Vector con los mï¿½dulos
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
	 * MÃ©todo que devuelve todos los mï¿½dulos correspondientes a la unidad mï¿½dica
	 * indicada en el parï¿½metro que recibe el MÃ©todo.
	 * 
	 * @return un Vector con los mï¿½dulos
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
				// ï¿½ poner: "Seleccione..." ?
				vcModulos = new Vector();
			}
		} catch (Exception ex) {
			error("getModulos", ex);
		}
		return vcModulos;
	}

	/**
	 * MÃ©todo que devuelve la descripciï¿½n correspondiente al nï¿½mero de proceso.
	 * 
	 * @param param
	 *            el nï¿½mero de proceso, obtenido en la pï¿½gina JSP durante la
	 *            inicializaciï¿½n.
	 * @return la descripciï¿½n del proceso obtenida, o una cadena vacï¿½a si no
	 *         encontrï¿½ ninguno.
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
			error("Error al convertir el nï¿½mero de proceso", ex);
		}
		String proceso = "";
		TVGRLProceso tv = null;
		DAOGRLProceso tdProceso = new DAOGRLProceso();
		Vector v = null;
		try {
			v = tdProceso.FindByAll();
		} catch (DAOException ex1) {
			error("Error al obtener la descripciï¿½n del proceso", ex1);
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
	 * MÃ©todo que convierte la colecciï¿½n de servicios recibida como HashMap en
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
			usmed.setCDscServicio((String) hm.get(k)); // la descripciï¿½n en la
														// posiciï¿½n de la
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
	 * @return la edad en aï¿½os.
	 */
	public long getEdadPersonal() {
		long edad = dtFecha.getTime() - personal.getDtNacimiento().getTime();
		edad /= (1000 * 60 * 60 * 24 * 365.25);
		return edad;
	}

	/**
	 * MÃ©todo que procesa los renglones obtenidos del request, y los coloca en
	 * la tabla de Hash correspondiente para insertar.
	 * 
	 * @return un int con el nï¿½mero de renglones insertados.
	 */
	private int getTableValues(String cToken) {
		System.out.println("getTableValues");
		String expediente = "586135";
		String examen = "6";
		String cServicio = "11";
		String cRama = "1";
		TVEXPResultado v = null;
		int i = 0;
		int accion = 0;
		System.out.println("getTableValues --");
		System.out.println(request.getParameter("dValorI_119"));
		//int n = Integer.parseInt(request.getParameter("hdTotalRows"));
		int n = 0;
		System.out.println("getTableValues -0");
		// log("registros obtenidos en HTML: " + n);
		int iCveExpediente = 0;
		int iNumExamen = 0;
		int iCveServicio = 0;
		int iCveRama = 0;
		int iCveSintoma = 0;
		System.out.println("1");
		int lAsignar = 0;
		int tpoResp = 0;
		String llave = "";
		boolean existe = false;
		boolean asignar = false;
		boolean alarma = false;
		//boolean SVSLXi = Boolean.parseBoolean(request.getParameter("SVSLXi"));
		System.out.println(request.getAttribute("id"));
		iCveExpediente = Integer.parseInt(expediente);
		iNumExamen = Integer.parseInt(examen);
		iCveServicio = Integer.parseInt(cServicio);
		iCveRama = Integer.parseInt(cRama);
		float dValorI = 0.0f;
		float dValorF = 0.0f;
		int lLogico = 0;
		String cCaracter = "";
		String dvalori = "";
		String dvalorf = "";
		String llogico = "";
		String ccaracter = "";
		String cValRef = "";
		System.out.println("2");
		//String cToken = request.getParameter("hdFlags");
		System.out.println(cToken);
		StringTokenizer stToken = new StringTokenizer(cToken.substring(0,cToken.length()-1)+"", "|");
		// while (i < n) {
		while (stToken.hasMoreTokens()) {
			String cTemp = stToken.nextToken();
			int largoRama = cRama.length();
			
			// armar un V.O. para cada renglï¿½n de la pantalla de captura
			// y agregarlo a la tabla de Hash correspondiente
			v = new TVEXPResultado();
			System.out.println("&3");
			System.out.println("cTemp ="+cTemp);
			System.out.println("cTemp2 ="+cTemp.substring(0, 7));
			System.out.println("largoRama ="+largoRama);
			if(cTemp.substring(0, 7).equals("lLogico")){
				tpoResp=1;
				iCveSintoma = Integer.parseInt(cTemp.substring((8+largoRama), cTemp.length()));
				ccaracter = request.getParameter(cTemp);
			}
			if(cTemp.substring(0, 9).equals("cCaracter")){
				tpoResp=4;
				iCveSintoma = Integer.parseInt(cTemp.substring((10+largoRama), cTemp.length()));
				ccaracter = request.getParameter(cTemp);
			}
			if(cTemp.substring(0, 7).equals("dValorI")){
				tpoResp=3;
				iCveSintoma = Integer.parseInt(cTemp.substring((8+largoRama), cTemp.length()));
				dvalori = request.getParameter(cTemp);
			}
			
			
			//tpoResp = Integer.parseInt(request.getParameter("iCveTpoResp_"+ cTemp));
			//iCveSintoma = Integer.parseInt(request.getParameter("iCveSintoma_"+ cTemp));
			System.out.println("cTemp ="+cTemp);
			System.out.println("tpoResp ="+tpoResp);
			System.out.println("iCveSintoma ="+iCveSintoma);
			/*dvalori = request.getParameter("dValorI_" + cTemp);
			dvalorf = request.getParameter("dValorF_" + cTemp);
			llogico = request.getParameter("lLogico_" + cTemp);
			ccaracter = request.getParameter("cCaracter_" + cTemp);
			cValRef = request.getParameter("cValRef_" + cTemp);*/
			//String[] ccaracter2 = request.getParameterValues("cCaracter_"+ cTemp);
			int count = 0;

			System.out.println("dValorI_" + cTemp +" = "+dvalori);
			
			llave = iCveExpediente + "," + iNumExamen + "," + iCveServicio
					+ "," + iCveRama + "," + iCveSintoma;
			System.out.println("4");
			// llave primaria
			v.setICveExpediente(iCveExpediente);
			v.setINumExamen(iNumExamen);
			v.setICveServicio(iCveServicio);
			v.setICveRama(iCveRama);
			v.setICveSintoma(iCveSintoma);
			v.setICveTpoResp(tpoResp);
			
			System.out.println("5");
			
			
			// campos con valores default

			// ********* CAMBIO POR MARCO GONZALEZ PRUEBA *********
			/*
			 * v.setDValorIni(dValorI); v.setDValorFin(dValorF);
			 * v.setLLogico(lLogico); v.setCCaracter(cCaracter);
			 * v.setCValRef(cValRef);
			 */

			if (tpoResp == 7){
					tpoResp = 4;
				}

			if (tpoResp == 8 || tpoResp == 10){
					tpoResp = 4;
				}

			// verificar que traiga valores para insertar, en cada tipo de dato,
			// y establecer el valor real
			switch (tpoResp) {
			case SI_NO:
				if (true) {
					v.setLLogico(this.getRadioValue(llogico));
					asignar = true;
				}
				break;
			case LETRAS_NUMEROS:
			case NOTAS:
				if (esValido(ccaracter) && ccaracter.length() != 0) {
					v.setCCaracter(ccaracter);
					asignar = true;
				}
				break;
			case NUMEROS:
				if (esValido(dvalori) && !esCero(dvalori)) {
					// v.setDValorIni(Float.parseFloat(dvalori)); //AG SA
					// -- Se modifico para que no restara decimales al dato
					// obtenido.
					//System.out.println("Si entro "+ dvalori);
					v.setDValorIni(Double.parseDouble(dvalori));
					asignar = true;
				}else{
					//System.out.println("No entro = "+dvalori);
				}

				break;
			case RANGO:
				if ((esValido(dvalori) && esValido(dvalorf))
						&& (!esCero(dvalori) || !esCero(dvalorf))) {
					v.setDValorIni(Float.parseFloat(dvalori));
					v.setDValorFin(Float.parseFloat(dvalorf));
					asignar = true;
				}
				break;

			case 11:
				v.setCCaracter(llogico);
				asignar = true;
				break;

			case 13:
				/*if (ccaracter2 != null) {
					for (String cadena : ccaracter2) {
						if (count == 0) {
							ccaracter = cadena;
						} else {
							ccaracter = ccaracter + "," + cadena;
						}
						count++;
					}
					if (esValido(ccaracter) && ccaracter.length() != 0) {
						v.setCCaracter(ccaracter);
						asignar = true;
					}
				}*/
				break;
			case 14:
				//System.out.println("Fecha cTemp"+request.getParameter("dtFecha_"+ cTemp));
				String cFecha = request.getParameter("dtFecha_"
						+ cTemp);
				String cHhora = request.getParameter("cHhora_"
						+ cTemp);
				String cMinutos = request.getParameter("cMinutos_"
						+ cTemp);
				String[] arrayFecha = cFecha.split("/");
				ccaracter = arrayFecha[2] +"-"+arrayFecha[1]+"-"+arrayFecha[0]+" "+ cHhora +":"+ cMinutos +":00.000";
				//System.out.println("-"+ccaracter);
				v.setCCaracter(ccaracter);
				asignar = true;
				break;
				
			case 15:
				String iCvePais = request.getParameter("iCvePais_"
						+ cTemp);
				String iCveEstado = request.getParameter("iCveEstado_"
						+ cTemp);
				String iCveMunicipio = request.getParameter("iCveMunicipio_"
						+ cTemp);
				String iCveLocalidad = request.getParameter("iCveLocalidad_"
						+ cTemp);
				ccaracter = iCvePais +","+ iCveEstado +","+ iCveMunicipio +","+iCveLocalidad;
				//System.out.println("-"+ccaracter);
				v.setCCaracter(ccaracter);
				asignar = true;
				break;
			case 16:
				if (esValido(ccaracter) && ccaracter.length() != 0) {
					v.setCCaracter(ccaracter);
					asignar = true;
				}else{
					v.setCCaracter("  ");
					asignar = true;
				}
				break;
				
			default:
			}
			System.out.println("6");
			if (asignar) {
				actualizar.add(v);
			}
			i++;
			existe = asignar = alarma = false; // re-set de las variables de
												// evaluaciï¿½n
			log("en espera de actualizacion: " + v.toHashMap().toString());
		}
		//System.out.println("-- "+ actualizar.size());
		return actualizar.size();
	}
	
	
	/**
	 * MÃ©todo que procesa los renglones obtenidos del request, y los coloca en
	 * la tabla de Hash correspondiente para insertar.
	 * 
	 * @return un int con el nï¿½mero de renglones insertados.
	 */
	private int getTableValuesDos(String cToken, String expediente, String examen, String cServicio, String cRama) {
		System.out.println("getTableValues");
		/*String expediente = "586135";
		String examen = "6";
		String cServicio = "11";
		String cRama = "1";*/
		TVEXPResultado v = null;
		int i = 0;
		int accion = 0;
		System.out.println("getTableValues --");
		System.out.println(request.getParameter("dValorI_119"));
		//int n = Integer.parseInt(request.getParameter("hdTotalRows"));
		int n = 0;
		System.out.println("getTableValues -0");
		// log("registros obtenidos en HTML: " + n);
		int iCveExpediente = 0;
		int iNumExamen = 0;
		int iCveServicio = 0;
		int iCveRama = 0;
		int iCveSintoma = 0;
		System.out.println("1");
		int lAsignar = 0;
		int tpoResp = 0;
		String llave = "";
		boolean existe = false;
		boolean asignar = false;
		boolean alarma = false;
		//boolean SVSLXi = Boolean.parseBoolean(request.getParameter("SVSLXi"));
		System.out.println(request.getAttribute("id"));
		iCveExpediente = Integer.parseInt(expediente);
		iNumExamen = Integer.parseInt(examen);
		iCveServicio = Integer.parseInt(cServicio);
		iCveRama = Integer.parseInt(cRama);
		float dValorI = 0.0f;
		float dValorF = 0.0f;
		int lLogico = 0;
		String cCaracter = "";
		String dvalori = "";
		String dvalorf = "";
		String llogico = "0";
		String ccaracter = "";
		String cValRef = "";
		System.out.println("2");
		//String cToken = request.getParameter("hdFlags");
		System.out.println(cToken);
		StringTokenizer stToken = new StringTokenizer(cToken.substring(0,cToken.length()-1)+"", "|");
		// while (i < n) {
		while (stToken.hasMoreTokens()) {
			String cTemp = stToken.nextToken();
			int largoRama = cRama.length();
			
			// armar un V.O. para cada renglï¿½n de la pantalla de captura
			// y agregarlo a la tabla de Hash correspondiente
			v = new TVEXPResultado();
			System.out.println(".3");
			System.out.println("cTemp ="+cTemp);
			System.out.println("cTemp2 ="+cTemp.substring(0, 7));
			System.out.println("largoRama ="+largoRama);
			if(cTemp.substring(0, 7).equals("lLogico")){
				tpoResp=1;
				iCveSintoma = Integer.parseInt(cTemp.substring((8+largoRama), cTemp.length()));
				ccaracter = request.getParameter(cTemp);
				if(ccaracter!=null){
					if(ccaracter.equals("on")){
						llogico="1";
					}else{
						llogico="0";
					}
				}else{
					llogico="0";
				}
						
			}
			if(cTemp.substring(0, 9).equals("cCaracter")){
				tpoResp=4;
				iCveSintoma = Integer.parseInt(cTemp.substring((10+largoRama), cTemp.length()));
				ccaracter = request.getParameter(cTemp);
			}
			if(cTemp.substring(0, 7).equals("dValorI")){
				tpoResp=3;
				iCveSintoma = Integer.parseInt(cTemp.substring((8+largoRama), cTemp.length()));
				dvalori = request.getParameter(cTemp);
			}
			
			
			//tpoResp = Integer.parseInt(request.getParameter("iCveTpoResp_"+ cTemp));
			//iCveSintoma = Integer.parseInt(request.getParameter("iCveSintoma_"+ cTemp));
			System.out.println("cTemp ="+cTemp);
			System.out.println("tpoResp ="+tpoResp);
			System.out.println("iCveSintoma ="+iCveSintoma);
			/*dvalori = request.getParameter("dValorI_" + cTemp);
			dvalorf = request.getParameter("dValorF_" + cTemp);
			llogico = request.getParameter("lLogico_" + cTemp);
			ccaracter = request.getParameter("cCaracter_" + cTemp);
			cValRef = request.getParameter("cValRef_" + cTemp);*/
			//String[] ccaracter2 = request.getParameterValues("cCaracter_"+ cTemp);
			int count = 0;

			System.out.println("dValorI_" + cTemp +" = "+dvalori);
			System.out.println("lLogico" + cTemp +" = "+ccaracter);
			
			llave = iCveExpediente + "," + iNumExamen + "," + iCveServicio
					+ "," + iCveRama + "," + iCveSintoma;
			System.out.println("4");
			// llave primaria
			v.setICveExpediente(iCveExpediente);
			v.setINumExamen(iNumExamen);
			v.setICveServicio(iCveServicio);
			v.setICveRama(iCveRama);
			v.setICveSintoma(iCveSintoma);
			v.setICveTpoResp(tpoResp);
			
			System.out.println("5");
			
			
			// campos con valores default

			// ********* CAMBIO POR MARCO GONZALEZ PRUEBA *********
			/*
			 * v.setDValorIni(dValorI); v.setDValorFin(dValorF);
			 * v.setLLogico(lLogico); v.setCCaracter(cCaracter);
			 * v.setCValRef(cValRef);
			 */

			if (tpoResp == 7){
					tpoResp = 4;
				}

			if (tpoResp == 8 || tpoResp == 10){
					tpoResp = 4;
				}

			// verificar que traiga valores para insertar, en cada tipo de dato,
			// y establecer el valor real
			switch (tpoResp) {
			case SI_NO:
				if (true) {
					v.setLLogico(this.getRadioValue(llogico));
					System.out.println("Logico ="+llogico);
					asignar = true;
				}
				break;
			case LETRAS_NUMEROS:
			case NOTAS:
				if (esValido(ccaracter) && ccaracter.length() != 0) {
					v.setCCaracter(ccaracter);
					asignar = true;
				}
				break;
			case NUMEROS:
				if (esValido(dvalori) && !esCero(dvalori)) {
					// v.setDValorIni(Float.parseFloat(dvalori)); //AG SA
					// -- Se modifico para que no restara decimales al dato
					// obtenido.
					//System.out.println("Si entro "+ dvalori);
					v.setDValorIni(Double.parseDouble(dvalori));
					asignar = true;
				}else{
					//System.out.println("No entro = "+dvalori);
				}

				break;
			case RANGO:
				if ((esValido(dvalori) && esValido(dvalorf))
						&& (!esCero(dvalori) || !esCero(dvalorf))) {
					v.setDValorIni(Float.parseFloat(dvalori));
					v.setDValorFin(Float.parseFloat(dvalorf));
					asignar = true;
				}
				break;

			case 11:
				v.setCCaracter(llogico);
				asignar = true;
				break;

			case 13:
				/*if (ccaracter2 != null) {
					for (String cadena : ccaracter2) {
						if (count == 0) {
							ccaracter = cadena;
						} else {
							ccaracter = ccaracter + "," + cadena;
						}
						count++;
					}
					if (esValido(ccaracter) && ccaracter.length() != 0) {
						v.setCCaracter(ccaracter);
						asignar = true;
					}
				}*/
				break;
			case 14:
				//System.out.println("Fecha cTemp"+request.getParameter("dtFecha_"+ cTemp));
				String cFecha = request.getParameter("dtFecha_"
						+ cTemp);
				String cHhora = request.getParameter("cHhora_"
						+ cTemp);
				String cMinutos = request.getParameter("cMinutos_"
						+ cTemp);
				String[] arrayFecha = cFecha.split("/");
				ccaracter = arrayFecha[2] +"-"+arrayFecha[1]+"-"+arrayFecha[0]+" "+ cHhora +":"+ cMinutos +":00.000";
				//System.out.println("-"+ccaracter);
				v.setCCaracter(ccaracter);
				asignar = true;
				break;
				
			case 15:
				String iCvePais = request.getParameter("iCvePais_"
						+ cTemp);
				String iCveEstado = request.getParameter("iCveEstado_"
						+ cTemp);
				String iCveMunicipio = request.getParameter("iCveMunicipio_"
						+ cTemp);
				String iCveLocalidad = request.getParameter("iCveLocalidad_"
						+ cTemp);
				ccaracter = iCvePais +","+ iCveEstado +","+ iCveMunicipio +","+iCveLocalidad;
				//System.out.println("-"+ccaracter);
				v.setCCaracter(ccaracter);
				asignar = true;
				break;
			case 16:
				if (esValido(ccaracter) && ccaracter.length() != 0) {
					v.setCCaracter(ccaracter);
					asignar = true;
				}else{
					v.setCCaracter("  ");
					asignar = true;
				}
				break;
				
			default:
			}
			System.out.println("6");
			if (asignar) {
				actualizar.add(v);
			}
			i++;
			existe = asignar = alarma = false; // re-set de las variables de
												// evaluaciï¿½n
			log("en espera de actualizacion: " + v.toHashMap().toString());
		}
		//System.out.println("-- "+ actualizar.size());
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
		System.out.println("Valor="+valor);
		if (valor == null || valor.equals("null") || valor.equals("")
				|| valor.equals("undefined")) {
			return 99;

		} else {
			if (valor.equals("0")) {
				return 0;
			} else {
				return 1;
			}
		}

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
	// diagnï¿½sticos y/o recomendaciones

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
	} // MÃ©todo GuardarDiagRec

	/**
	 * MÃ©todo getInputs
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

	// /Regresa modo de transporte
	/**
	 * Devuelve el siguiente registro por procesar de EXPRama.
	 * 
	 * @return un value object con el registro de la rama, o null si no hay mï¿½s
	 *         registros.
	 * @author Romeo Sanchez
	 */
	public int getMDOTrans() {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int transporte = 0;
		String cWhere = "SELECT ICVEMDOTRANS FROM EXPEXAMCAT "
				+ "WHERE ICVEEXPEDIENTE = " + param.get("iCveExpediente") + " "
				+ "AND INUMEXAMEN = " + param.get("iNumExamen")
				+ " ORDER BY ICVEMDOTRANS DESC";

		try {
			transporte = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}
		return transporte;
	}

	// -- Termina la escritura en EXPDictamenServ

	// /Regresa modo de transporte
	/**
	 * Devuelve el siguiente registro por procesar de EXPRama.
	 * 
	 * @return un value object con el registro de la rama, o null si no hay mï¿½s
	 *         registros.
	 * @author Romeo Sanchez
	 */
	public int getMDOTransDos(String exp, String exa) {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int transporte = 0;
		String cWhere = "SELECT ICVEMDOTRANS FROM EXPEXAMCAT "
				+ "WHERE ICVEEXPEDIENTE = " + exp + " "
				+ "AND INUMEXAMEN = " + exa
				+ " ORDER BY ICVEMDOTRANS DESC";

		try {
			transporte = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}
		return transporte;
	}
	
	
	// Regresa Categoria

	/**
	 * Devuelve el siguiente registro por procesar de EXPRama.
	 * 
	 * @return un value object con el registro de la rama, o null si no hay mï¿½s
	 *         registros.
	 * @author Romeo Sanchez
	 */
	public int getCategoria() {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int categoria = 0;
		int transporte = this.getMDOTrans();

		String cWhere = "SELECT ICVECATEGORIA FROM EXPEXAMCAT "
				+ "WHERE ICVEEXPEDIENTE = " + param.get("iCveExpediente") + " "
				+ "AND INUMEXAMEN = " + param.get("iNumExamen") + " "
				+ "AND ICVEMDOTRANS = " + transporte
				+ " ORDER BY ICVECATEGORIA DESC";

		try {
			categoria = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}
		return categoria;
	}

	// Regresa Categoria

	/**
	 * Devuelve el siguiente registro por procesar de EXPRama.
	 * 
	 * @return un value object con el registro de la rama, o null si no hay mï¿½s
	 *         registros.
	 * @author Romeo Sanchez
	 */
	public int getCategoriaDos(String exp, String exa, String transporte) {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int categoria = 0;
		//int transporte = this.getMDOTrans();
System.out.println("##############getCategoriaDos");
		String cWhere = "SELECT ICVECATEGORIA FROM EXPEXAMCAT "
				+ "WHERE ICVEEXPEDIENTE = " + exp+ " "
				+ "AND INUMEXAMEN = " + exa+ " "
				+ "AND ICVEMDOTRANS = " + transporte
				+ " ORDER BY ICVECATEGORIA DESC";
		System.out.println("##############getCategoriaDos = "+cWhere);
		try {
			categoria = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}
		return categoria;
	}
	
	
	/**
	 * //Regresa Motivo
	 */
	public int getMotivo() {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int motivo = 0;
		int transporte = this.getMDOTrans();

		String cWhere = "SELECT ICVEMOTIVO FROM EXPEXAMCAT "
				+ "WHERE ICVEEXPEDIENTE = " + param.get("iCveExpediente") + " "
				+ "AND INUMEXAMEN = " + param.get("iNumExamen") + " "
				+ "AND ICVEMDOTRANS = " + transporte + " "
				+ "ORDER BY ICVECATEGORIA DESC";
		try {
			motivo = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}
		return motivo;
	}

	
	/**
	 * //Regresa Motivo
	 */
	public int getMotivoDosI(String exp, String exa, String transporte) {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int motivo = 0;
		//int transporte = this.getMDOTrans();
		System.out.println("getMotivoDosI -1");
		String cWhere = "SELECT ICVEMOTIVO FROM EXPEXAMCAT "
				+ "WHERE ICVEEXPEDIENTE = " + exp + " "
				+ "AND INUMEXAMEN = " + exa + " "
				+ "AND ICVEMDOTRANS = " + transporte + " "
				+ "ORDER BY ICVECATEGORIA DESC";
		try {
			motivo = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}
		System.out.println("getMotivoDosI -2");
		return motivo;
	}
	
	/**
	 * //Regresa Motivo
	 */
	public int getRamasPendientesI(String exp, String exa, String servicio) {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		int ramas = 0;
		//int transporte = this.getMDOTrans();
		System.out.println("getMotivoDosI -1");
		String cWhere = "SELECT count(icverama) FROM EXPRAMA "
				+ "WHERE ICVEEXPEDIENTE = " + exp + " "
				+ "AND INUMEXAMEN = " + exa + " "
				+ "AND ICVESERVICIO = " + servicio
				+" AND LCONCLUIDO = 0";
		try {
			ramas = dEXPExamAplica.RegresaInt(cWhere);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}
		System.out.println("getRamasPendientesI="+ramas);
		return ramas;
	}

	
	/**
	 * //Regresa Motivo
	 */
	public String getMotivo22() {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		String motivo22 = "";
		int motivo = 0;
		int transporte = this.getMDOTrans();

		String cWhere = "SELECT ICVEMOTIVO FROM EXPEXAMCAT "
				+ "WHERE ICVEEXPEDIENTE = " + param.get("iCveExpediente") + " "
				+ "AND INUMEXAMEN = " + param.get("iNumExamen") + " "
				+ "AND ICVEMDOTRANS = " + transporte + " "
				+ "ORDER BY ICVECATEGORIA DESC";
		try {
			motivo = dEXPExamAplica.RegresaInt(cWhere);
			motivo22 = String.valueOf(motivo);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}
		// System.out.println("Se regresara valor = "+motivo22);
		return motivo22;
	}
	
	/**
	 * //Regresa Motivo
	 */
	public String getMotivoDos(String exp, String exa, String transporte) {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		String motivo22 = "";
		int motivo = 0;
		//int transporte = this.getMDOTrans();

		String cWhere = "SELECT ICVEMOTIVO FROM EXPEXAMCAT "
				+ "WHERE ICVEEXPEDIENTE = " + exp + " "
				+ "AND INUMEXAMEN = " + exa + " "
				+ "AND ICVEMDOTRANS = " + transporte + " "
				+ "ORDER BY ICVECATEGORIA DESC";
		try {
			motivo = dEXPExamAplica.RegresaInt(cWhere);
			motivo22 = String.valueOf(motivo);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPExamCat", ex);
		}
		System.out.println("Se regresara valor = "+motivo22);
		return motivo22;
	}

	/**
	 * Actualiza la fecha en que se consulta el cuestionario de la Rama en EXPRama.
	 * @author AG SA
	 */
	public String ActualizaFechaInicioRama(String cExpediente, String cExamen, 
			String cServicio, String cRama) {
		TDEXPRama dRama2 = new TDEXPRama();
		TVEXPRama rama2 = null;
		HashMap filtro2 = null;
		String r2 = "";
		String filtros = "";
		filtros = " EXPRama.iCveExpediente = " + cExpediente
				+ " and EXPRama.iNumExamen = " + cExamen
				+ " and EXPRama.iCveServicio = " + cServicio+
				" and iCveRama= "+cRama+
				" and EXPRama.lConcluido= 0 ";
		//System.out.println(filtros);

		try {
			r2 = dRama2.updateFechaInicioRama(filtros);
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPRama", ex);
		}
		return r2;
	}


	/**
	 * Actualiza la fecha en que se consulta el cuestionario de la Rama en EXPRama.
	 * @author AG SA
	 */
	public boolean ValidaCapturaDeGraficasAudiometria(String cExpediente, String cExamen, 
			String cServicio) {
		boolean GraficaOidoDerecho = false;
		boolean GraficasCapturadas = false;
		boolean GraficaOidoIzquierdo = false;
		TDEXPAudiomet dAudiomet = new TDEXPAudiomet();
		String filtros = "";
		filtros = " where iCveExpediente = " + cExpediente
				+ " and iNumExamen = " + cExamen
				+ " and iCveServicio = " + cServicio 
				+ " and Ioido = ";

		try {
			GraficaOidoDerecho = dAudiomet.ValidaCapturaAudiometria(filtros+"1");
			GraficaOidoIzquierdo = dAudiomet.ValidaCapturaAudiometria(filtros+"2");
			System.out.println("GraficaOidoDerecho="+GraficaOidoDerecho);
			System.out.println("GraficaOidoIzquierdo="+GraficaOidoIzquierdo);
			if(GraficaOidoDerecho && GraficaOidoIzquierdo){
				GraficasCapturadas=true;
			}
			
		} catch (DAOException ex) {
			error("Error al buscar registros en EXPRama", ex);
		}
		return GraficasCapturadas;
	}

	

}
