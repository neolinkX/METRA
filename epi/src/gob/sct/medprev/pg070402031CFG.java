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
 *      <img src='pg070104030.jsp.png'>
 */
public class pg070402031CFG extends CFGListBase2 {

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

	public pg070402031CFG() {
		this.vParametros = new TParametro("07");
		cPaginas = "pg070402030.jsp"; // Ir a...
	}

	public void fillVector() {
		TDINVServicio dINVServicio = new TDINVServicio();
		TDINVRama dINVRama = new TDINVRama();
		TDINVResultado dINVResultado = new TDINVResultado();
		TDMEDRama dMEDRama = new TDMEDRama();
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		TDMEDSintExamen dMEDSintExamen = new TDMEDSintExamen();
		TVMEDSintExamen vMEDSintExamen = new TVMEDSintExamen();
		TVMEDRama vMEDRama = new TVMEDRama();
		TVMEDSintoma vMEDSintoma = new TVMEDSintoma();
		Vector vcINVResultado = new Vector();
		Vector vcMedSintExamen = new Vector();
		Vector vcMedRama = new Vector();
		Vector vcMedSintoma = new Vector();
		Vector vRama = new Vector();
		Vector vResultado = new Vector();

		int iCveMdoTrans = 0;
		int iCveProceso = 0;
		int iCveMotivo = 0;
		int iAnio = 0;
		int iIDDGPMPT = 0;
		int iCveUsuario = 0;

		if (cAccion.equalsIgnoreCase("Guardar")) {
			this.Guardar();
		}
		try {

			if (request.getParameter("iCveMdoTrans") != null
					&& request.getParameter("iCveMdoTrans").length() > 0) {
				iCveMdoTrans = Integer.parseInt(request.getParameter(
						"iCveMdoTrans").toString());
			}
			if (request.getParameter("iCveProceso") != null
					&& request.getParameter("iCveProceso").length() > 0) {
				iCveProceso = Integer.parseInt(request.getParameter(
						"iCveProceso").toString());
			}
			if (request.getParameter("iCveMotivo") != null
					&& request.getParameter("iCveMotivo").length() > 0) {
				iCveMotivo = Integer.parseInt(request
						.getParameter("iCveMotivo").toString());
			}
			if (request.getParameter("iIDDGPMPT") != null
					&& request.getParameter("iIDDGPMPT").length() > 0) {
				iIDDGPMPT = Integer.parseInt(request.getParameter("iIDDGPMPT")
						.toString());
			}

			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").length() > 0) {
				iAnio = Integer.parseInt(request.getParameter("iAnio")
						.toString());
			}
			if (request.getParameter("iCveUsuario") != null
					&& request.getParameter("iCveUsuario").length() > 0) {
				iCveUsuario = Integer.parseInt(request.getParameter(
						"iCveUsuario").toString());
			}
			vcINVResultado = dINVResultado.FindByAll(" Where iAnio=" + iAnio
					+ " And iIDDGPMPT=" + iIDDGPMPT + " And iCveMdoTrans="
					+ iCveMdoTrans);

			if (vcINVResultado.size() > 0) {
				// VOID
			} else {
				vcMedSintExamen = dMEDSintExamen
						.FindByAllDistinct(" Where iCveProceso = "
								+ iCveProceso + " And   iCveMotivo  = "
								+ iCveMotivo + " And   iCveMdoTrans= "
								+ iCveMdoTrans);

				if (vcMedSintExamen.size() > 0) {
					// Unicamente los Servicios Diferentes del Anterior.
					int ivServicioAnt = 0;
					for (int y = 0; y < vcMedSintExamen.size(); y++) {
						vMEDSintExamen = (TVMEDSintExamen) vcMedSintExamen
								.get(y);

						/**
						 * Paso 1
						 * 
						 * Se inserta en la tabla INVServicio los campos
						 * 
						 * - iAnio - iCveMdoTrans - iIDDGPMPT - iCveServicio -
						 * iCveUsuario
						 * 
						 */
						if (ivServicioAnt != vMEDSintExamen.getICveServicio())
							dINVServicio.insertFromExpExamen(null, iAnio,
									iCveMdoTrans, iIDDGPMPT,
									vMEDSintExamen.getICveServicio(),
									iCveUsuario);

						vcMedRama = dMEDRama.FindByAll(" Where iCveServicio = "
								+ vMEDSintExamen.getICveServicio());
						if (vcMedRama.size() > 0) {
							for (int a = 0; a < vcMedRama.size(); a++) {
								vMEDRama = (TVMEDRama) vcMedRama.get(a);

								/**
								 * Paso 2
								 * 
								 * Se inserta en la tabla INVRama los campos
								 * 
								 * - iAnio - iCveMdoTrans - iIDDGPMPT -
								 * iCveServicio - iCveRama
								 * 
								 */
								vRama = null;
								vRama = dINVRama
										.FindCustomWhere(" where iAnio        = "
												+ iAnio
												+ "   and iCveMdoTrans = "
												+ iCveMdoTrans
												+ "   and iIDDGPMPT    = "
												+ iIDDGPMPT
												+ "   and iCveServicio = "
												+ vMEDSintExamen
														.getICveServicio()
												+ "   and iCveRama     = "
												+ vMEDRama.getICveRama());
								if (vRama.isEmpty())
									dINVRama.insertFromExpExamen(null, iAnio,
											iCveMdoTrans, iIDDGPMPT,
											vMEDSintExamen.getICveServicio(),
											vMEDRama.getICveRama(), iCveUsuario);
								/**
								 * Paso 3
								 * 
								 * Se inserta en la tabla INVResultado los
								 * campos
								 * 
								 * - iAnio - iCveMdoTrans - iIDDGPMPT -
								 * iCveServicio - iCveRama - iCveSintoma
								 * 
								 */
								vcMedSintoma = dMEDSintoma
										.FindByAll(" Where a.iCveServicio = "
												+ vMEDSintExamen
														.getICveServicio()
												+ " And  a.iCveRama     = "
												+ vMEDRama.getICveRama());
								if (vcMedSintoma.size() > 0) {
									int ivSintomaAnt = 0;
									for (int b = 0; b < vcMedSintoma.size(); b++) {
										vMEDSintoma = (TVMEDSintoma) vcMedSintoma
												.get(b);

										vResultado = dINVResultado
												.FindByAll(" where iAnio        = "
														+ iAnio
														+ "   and iCveMdoTrans = "
														+ iCveMdoTrans
														+ "   and iIDDGPMPT    = "
														+ iIDDGPMPT
														+ "   and iCveServicio = "
														+ vMEDSintExamen
																.getICveServicio()
														+ "   and iCveRama     = "
														+ vMEDRama
																.getICveRama()
														+ "   and iCveSintoma  = "
														+ vMEDSintoma
																.getICveSintoma());
										if (vResultado.isEmpty())
											dINVResultado.insertFromExpExamen(
													null, iAnio, iCveMdoTrans,
													iIDDGPMPT, vMEDSintExamen
															.getICveServicio(),
													vMEDRama.getICveRama(),
													vMEDSintoma
															.getICveSintoma());
									}
								}
							}
						}
						ivServicioAnt = vMEDSintExamen.getICveServicio();
					}
				}
			}

		} catch (Exception ex) {
			error("fillVector", ex);
		}
		log("fin de fillVector()");
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDEnvio dEnvio = new TDEnvio();
		int iCveMdoTrans = 0;
		int iCveProceso = 0;
		int iCveMotivo = 0;
		int iAnio = 0;
		int iIDDGPMPT = 0;
		int iCveUsuario = 0;
		int iIDMdoTrans = 0;
		java.sql.Date dtAccidente = null;
		TFechas fechas = new TFechas();
		TVINVResultado vINVResultado = new TVINVResultado();
		TVINVResultado vINVResultadoS = new TVINVResultado();
		TDINVResultado dINVResultado = new TDINVResultado();

		try {
			if (request.getParameter("iCveMdoTrans") != null
					&& request.getParameter("iCveMdoTrans").length() > 0) {
				vINVResultado.setICveMdoTrans(Integer.parseInt(request
						.getParameter("iCveMdoTrans").toString()));
			} else {
				vINVResultado.setICveMdoTrans(0);
			}
			if (request.getParameter("iIDDGPMPT") != null
					&& request.getParameter("iIDDGPMPT").length() > 0) {
				vINVResultado.setIIDDGPMPT(Integer.parseInt(request
						.getParameter("iIDDGPMPT").toString()));
			} else {
				vINVResultado.setIIDDGPMPT(0);
			}

			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").length() > 0) {
				vINVResultado.setIAnio(Integer.parseInt(request.getParameter(
						"iAnio").toString()));
			} else {
				vINVResultado.setIAnio(2004);
			}

			Vector vcInvResultado = new Vector();
			vcInvResultado = this.getINVResultado();
			int lLogico = 0;
			String cCaracter = "";
			int dValorIni = 0;
			int dValorFin = 0;
			int iCveRama = 0;
			int iCveSintoma = 0;
			if (vcInvResultado.size() > 0) {
				for (int r = 0; r < vcInvResultado.size(); r++) { // Itera sobre
																	// los
																	// Resultados
					vINVResultadoS = (TVINVResultado) vcInvResultado.get(r);
					if (request.getParameter("iCveSintoma" + r) != null) {
						vINVResultado.setICveSintoma(Integer.parseInt(request
								.getParameter("iCveSintoma" + r).toString()));
					} else {
						vINVResultado.setICveSintoma(0);
					}
					if (request.getParameter("iCveRama" + r) != null) {
						vINVResultado.setICveRama(Integer.parseInt(request
								.getParameter("iCveRama" + r).toString()));
					} else {
						vINVResultado.setICveRama(vINVResultadoS.getICveRama());
					}

					if (vINVResultadoS.getICveTpoResp() == 1) {
						if (request.getParameter("lLogico" + r) != null) {
							vINVResultado.setLLogico(Integer.parseInt(request
									.getParameter("lLogico" + r).toString()));
						} else {
							vINVResultado.setLLogico(0);
						}
						vINVResultado.setCCaracter("");
						vINVResultado.setDValorIni(0);
						vINVResultado.setDValorFin(0);
					}
					if (vINVResultadoS.getICveTpoResp() == 2) {
						if (request.getParameter("cCaracter" + r) != null) {
							vINVResultado.setCCaracter(request.getParameter(
									"cCaracter" + r).toString());
						} else {
							vINVResultado.setCCaracter("");
						}
						vINVResultado.setDValorIni(0);
						vINVResultado.setDValorFin(0);
						vINVResultado.setLLogico(0);
					}
					if (vINVResultadoS.getICveTpoResp() == 3) {
						if (request.getParameter("dValorIni" + r) != null) {
							vINVResultado.setDValorIni(Float.parseFloat(request
									.getParameter("dValorIni" + r).toString()));
						} else {
							vINVResultado.setDValorIni(0);
						}
						vINVResultado.setCCaracter("");
						vINVResultado.setDValorFin(0);
						vINVResultado.setLLogico(0);
					}
					if (vINVResultadoS.getICveTpoResp() == 4) {
						if (request.getParameter("cCaracter" + r) != null) {
							vINVResultado.setCCaracter(request.getParameter(
									"cCaracter" + r).toString());
						} else {
							vINVResultado.setCCaracter("");
						}
						vINVResultado.setDValorIni(0);
						vINVResultado.setDValorFin(0);
						vINVResultado.setLLogico(0);
					}
					if (vINVResultadoS.getICveTpoResp() == 5) {
						if (request.getParameter("dValorIni" + r) != null) {
							vINVResultado.setDValorIni(Float.parseFloat(request
									.getParameter("dValorIni" + r).toString()));
						} else {
							vINVResultado.setDValorIni(0);
						}
						if (request.getParameter("dValorFin" + r) != null) {
							vINVResultado.setDValorFin(Float.parseFloat(request
									.getParameter("dValorFin" + r).toString()));
						} else {
							vINVResultado.setDValorFin(0);
						}
						vINVResultado.setCCaracter(" ");
						vINVResultado.setLLogico(0);
					}

					dINVResultado.Update(null, vINVResultado);

				}
			}

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * 
	 * Metodo invResultado Este metodo se encarga de recolectar la informacion
	 * para definir el pintadode las ramas y sus sintomas
	 * 
	 * @Author: Javier Mendoza
	 * 
	 */

	public Vector getINVResultado() {
		Vector vcINVResultado = new Vector();
		try {
			TDINVResultado dINVResultado = new TDINVResultado();

			int iCveMdoTrans = 0;
			int iCveProceso = 0;
			int iCveMotivo = 0;
			int iAnio = 0;
			int iIDDGPMPT = 0;
			int iCveUsuario = 0;

			if (request.getParameter("iCveMdoTrans") != null
					&& request.getParameter("iCveMdoTrans").length() > 0) {
				iCveMdoTrans = Integer.parseInt(request.getParameter(
						"iCveMdoTrans").toString());
			}
			if (request.getParameter("iCveProceso") != null
					&& request.getParameter("iCveProceso").length() > 0) {
				iCveProceso = Integer.parseInt(request.getParameter(
						"iCveProceso").toString());
			}
			if (request.getParameter("iCveMotivo") != null
					&& request.getParameter("iCveMotivo").length() > 0) {
				iCveMotivo = Integer.parseInt(request
						.getParameter("iCveMotivo").toString());
			}
			if (request.getParameter("iIDDGPMPT") != null
					&& request.getParameter("iIDDGPMPT").length() > 0) {
				iIDDGPMPT = Integer.parseInt(request.getParameter("iIDDGPMPT")
						.toString());
			}
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").length() > 0) {
				iAnio = Integer.parseInt(request.getParameter("iAnio")
						.toString());
			}
			if (request.getParameter("iCveUsuario") != null
					&& request.getParameter("iCveUsuario").length() > 0) {
				iCveUsuario = Integer.parseInt(request.getParameter(
						"iCveUsuario").toString());
			}
			vcINVResultado = dINVResultado.FindByAllWithJoin(" Where a.iAnio="
					+ iAnio + " And a.iIDDGPMPT=" + iIDDGPMPT
					+ " And a.iCveMdoTrans=" + iCveMdoTrans, iCveProceso);
		} catch (Exception ex) {
			error("invResultado", ex);
		}

		return vcINVResultado;
	}
}
