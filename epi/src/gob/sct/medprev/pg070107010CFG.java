package gob.sct.medprev;

import java.util.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Handler de acciones para la p�gina
 * pg070107010
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070107010.jsp.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070107010.jsp.png'>
 */
public class pg070107010CFG extends CFGListBase2 {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private TFechas tf = new TFechas();
	private java.sql.Date d = null;
	private String fechaFormateada = "";
	private String fechaTmp = "";
	private int iFlag = 0;

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

	public pg070107010CFG() {
		this.vParametros = new TParametro("07");
		// cPaginas = "pg070104021.jsp"; // Ir a...
	}

	public void fillVector() {
		try {
			TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
			// String tpoBusqueda = request.getParameter("tpoBusqueda");
			String cCveExpediente = request.getParameter("iCveExpediente");
			String cNumExamen = request.getParameter("iNumExamen");
			String cCvePersonal = request.getParameter("iCvePersonal");
			String cCveUniMed = request.getParameter("iCveUniMed");
			String cCveProceso = request.getParameter("iCveProceso");
			String cCveModulo = request.getParameter("iCveModulo");
			String cServicio = request.getParameter("iCveServicio");
			java.sql.Date dtFecha = new java.sql.Date(new Date().getTime()); // fecha
																				// actual
			/*
			 * // System.out.println("cCveExpediente: "+cCveExpediente); //
			 * System
			 * .out.println("hdiCvePersonal: "+request.getParameter("hdiCvePersonal"
			 * )); //
			 * System.out.println("hdCveExpediente: "+request.getParameter
			 * ("hdCveExpediente")); //
			 * System.out.println("cNumExamen: "+cNumExamen);/* //
			 * System.out.println("cCvePersonal: "+cCvePersonal); //
			 * System.out.println("cCveUniMed: "+cCveUniMed); //
			 * System.out.println("cCveProceso: "+cCveProceso); //
			 * System.out.println("cCveModulo: "+cCveModulo); //
			 * System.out.println
			 * ("tpoBusqueda: "+request.getParameter("tpoBusqueda"));
			 */

			if (request.getParameter("iCveExpediente") != null
					&& request.getParameter("iCveExpediente").trim().length() > 0) {
				cCveExpediente = request.getParameter("iCveExpediente");
			}
			if (request.getParameter("iNumExamen") != null
					&& request.getParameter("iNumExamen").trim().length() > 0
					&& Integer.parseInt(request.getParameter("iNumExamen")) > 0) {
				cNumExamen = request.getParameter("iNumExamen");
			}
			if (request.getParameter("iCvePersonal") != null
					&& request.getParameter("iCvePersonal").trim().length() > 0
					&& Integer.parseInt(request.getParameter("iCvePersonal")) > 0) {
				cCvePersonal = request.getParameter("iCvePersonal");
			}
			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").trim().length() > 0
					&& Integer.parseInt(request.getParameter("iCveUniMed")) > 0) {
				cCveUniMed = request.getParameter("iCveUniMed");
			}
			if (request.getParameter("iCveProceso") != null
					&& request.getParameter("iCveProceso").trim().length() > 0
					&& Integer.parseInt(request.getParameter("iCveProceso")) > 0) {
				cCveProceso = request.getParameter("iCveProceso");
			}
			if (request.getParameter("iCveModulo") != null
					&& request.getParameter("iCveModulo").trim().length() > 0
					&& Integer.parseInt(request.getParameter("iCveModulo")) > 0) {
				cCveModulo = request.getParameter("iCveModulo");
			}

			if (request.getParameter("tpoBusqueda") != null
					&& request.getParameter("tpoBusqueda").equals(
							"porExpediente")) {
				HashMap p = new HashMap();
				p.put("iCveUniMed", cCveUniMed);
				p.put("iCveProceso", cCveProceso);
				p.put("iCveModulo", cCveModulo);
				p.put("iCveExpediente", cCveExpediente);
				p.put("dtFecha", dtFecha);
				p.put("iCvePersonal", cCvePersonal);
				p.put("lDictaminado", "0");
				p.put("iCveServicio", cServicio);
				// vDespliega = dEXPExamAplica.buscaXLiberarServicio(p);
				vDespliega = dEXPExamAplica.buscaXLiberarServicio2(p);
			} else if (request.getParameter("tpoBusqueda") != null
					&& request.getParameter("tpoBusqueda")
							.equals("porPersonal")) {
				vDespliega = dEXPExamAplica.findExamPersonal(cCveExpediente,
						cNumExamen, cCvePersonal);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Metodo que devuelve la lista de unidades m�dicas v�lidas
	 * 
	 * @return un Vector con los value objects de las unidades m�dicas
	 */
	public Vector getUniMedsValidas(String cCveProceso) {
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
			ex.printStackTrace();
		}
		return vcUMValidas;
	}

	/**
	 * Metodo que devuelve todos los m�dulos correspondientes a la unidad
	 * m�dica indicada en el par�metro "iCveUniMed" del request de HTTP.
	 * 
	 * @return un Vector con los m�dulos
	 * 
	 *         public Vector getModulos() { log("getModulos()"); Vector
	 *         vcModulos = null; try { String cTmp =
	 *         request.getParameter("iCveUniMed"); if (cTmp != null) { vcModulos
	 *         = new TDGRLModulo().getComboModulos(Integer.parseInt(cTmp)); } }
	 *         catch (Exception ex) { error("getModulos", ex); } return
	 *         vcModulos; }
	 */

	/**
	 * Metodo que devuelve todos los m�dulos correspondientes a la unidad
	 * m�dica indicada en el par�metro que recibe el Metodo.
	 * 
	 * @return un Vector con los m�dulos
	 */
	public Vector getModulos(String cCveUniMed) {
		Vector vcModulos = null;
		try {
			if (cCveUniMed != null && !cCveUniMed.equals("null")
					&& !cCveUniMed.equals("undefined")
					&& !cCveUniMed.equals("")) {
				vcModulos = new TDGRLModulo().getComboModulos(Integer
						.parseInt(cCveUniMed));
			} else {
				vcModulos = new Vector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
		String proceso = "";
		try {
			if (param == null || param.length() == 0)
				return "";
			int proc = 0;
			proc = Integer.parseInt(param);
			TVGRLProceso tv = null;
			DAOGRLProceso tdProceso = new DAOGRLProceso();
			Vector v = tdProceso.FindByAll();
			for (int i = 0; i < v.size(); i++) {
				tv = (TVGRLProceso) v.elementAt(i);
				if (tv.getICveProceso() == proc) { // si coincide...
					return tv.getCDscProceso();
				}
			}
		} catch (DAOException ex1) {
			ex1.printStackTrace();
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
	 * 
	 *         public Vector getServicios(HashMap hm) {
	 *         log("getServicios("+hm.size()+") servicios"); Vector result = new
	 *         Vector(); String k = ""; Set llaves = hm.keySet(); Iterator keys
	 *         = llaves.iterator();
	 * 
	 *         while (keys.hasNext()) { TVGRLUSUMedicos usmed = new
	 *         TVGRLUSUMedicos(); k = (String)keys.next();
	 *         usmed.setICveServicio(Integer.parseInt(k)); // la propia llave
	 *         del HashMap usmed.setCDscServicio((String)hm.get(k)); // la
	 *         descripci�n en la posici�n de la llave
	 *         log("servicio obtenido:"+k+hm.get(k)); result.addElement(usmed);
	 *         } //Collections.sort(result); return result; }
	 */

	public void GuardarA() {
		try {
			HashMap hmUpdate = new HashMap();
			TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true)
					.getAttribute("UsrID");
			String cCveExpediente = request.getParameter("iCveExpediente");
			String cNumExamen = request.getParameter("iNumExamen");
			String cServicio = request.getParameter("iCveServicio");
			String cRama = "";
			TDEXPFunDictamen dExpFunDictamen = new TDEXPFunDictamen();

			hmUpdate.put("iCveExpediente", cCveExpediente);
			hmUpdate.put("iNumExamen", cNumExamen);
			hmUpdate.put("iCveServicio", cServicio);
			hmUpdate.put("iCveUsuario",
					String.valueOf(vUsuario.getICveusuario()));

			if (request.getParameter("lVariosMeds") != null) {
				if (Integer.parseInt(request.getParameter("lVariosMeds")) == 0) {
					int iUpdate1 = 0;
					int iUpdate2 = 0;
					int iUpdate3 = 0;
					int iUpdate4 = 0;
					iUpdate1 = new TDEXPRama().Liberar(hmUpdate);
					if (iUpdate1 != 0) {
						if (!cServicio.toString().equals("31")) {
							iUpdate2 = new TDEXPResultado().Liberar(hmUpdate);
							new TDEXPResultadoDos().LiberaDos(hmUpdate);
							//System.out.println("esta opcion");
						}else{
							iUpdate2 = 1;
						}
						if (iUpdate2 != 0) {
							if (cServicio.toString().equals("31")) {
								dExpFunDictamen.BitMod(null, cCveExpediente,
										cNumExamen, cServicio, String
												.valueOf(vUsuario
														.getICveusuario()));
								dExpFunDictamen.delete(null, cCveExpediente,
										cNumExamen, cServicio, String
												.valueOf(vUsuario
														.getICveusuario()));
							}
							iUpdate3 = new TDEXPServicio().Liberar(hmUpdate);
							if (iUpdate3 != 0) {
								this.setIFlag(1);
							}
						}
					}
					if (request.getParameter("iReqDiag") != null) {
						iUpdate1 = new TDEXPRecomendacion().checkDelete(
								cCveExpediente, cNumExamen, cServicio);
						iUpdate2 = new TDEXPDiagnostico().checkDelete(
								cCveExpediente, cNumExamen, cServicio);
						iUpdate3 = new TDEXPDictamenServ().checkDelete(
								cCveExpediente, cNumExamen, cServicio);
					}
					/*
					 * if(Integer.parseInt(cServicio) == 6){ iUpdate1 = new
					 * TDEXPAudiomet().Liberar(hmUpdate); }
					 */
					iUpdate1 = new TDEXPAudiomet().Liberar2(hmUpdate);
					iUpdate4 = new TDEXPRegSint().Liberar(hmUpdate);
					iUpdate4 = new TDEXPSERVPRef()
							.Liberar(hmUpdate);
					if(cServicio.equals("75")){
						TDEXPResultadoExt1 dEXPResultadoExt1 = new TDEXPResultadoExt1();
						dEXPResultadoExt1.DeleteSintoma(cCveExpediente, cNumExamen, "75", "1", "86");
					}
				} else {
					if (request.getParameter("maxValue") != null) {
						int iMaxValue = Integer.parseInt(request
								.getParameter("maxValue"));
						for (int i = 0; i < iMaxValue; i++) {
							if (request.getParameter("iRama" + i) != null) {
								cRama = request.getParameter("iRama" + i);
								hmUpdate.put("iCveRama", cRama);
								int iUpdate1 = 0;
								int iUpdate2 = 0;
								int iUpdate3 = 0;
								int iUpdate4 = 0;
								iUpdate1 = new TDEXPRama().Liberar(hmUpdate);
								if (iUpdate1 != 0) {
									iUpdate2 = new TDEXPResultado()
											.Liberar(hmUpdate);
									if (iUpdate2 != 0) {
										iUpdate3 = new TDEXPServicio()
												.Liberar(hmUpdate);
										if (iUpdate3 != 0) {
											this.setIFlag(1);
										}
									}
								}
								if (request.getParameter("iReqDiag") != null) {
									iUpdate1 = new TDEXPRecomendacion()
											.checkDelete(cCveExpediente,
													cNumExamen, cServicio);
									iUpdate2 = new TDEXPDiagnostico()
											.checkDelete(cCveExpediente,
													cNumExamen, cServicio);
									iUpdate3 = new TDEXPDictamenServ()
											.checkDelete(cCveExpediente,
													cNumExamen, cServicio);
								}
								/*
								 * if(Integer.parseInt(cServicio) == 6){
								 * iUpdate1 = new
								 * TDEXPAudiomet().Liberar(hmUpdate); }
								 */
								iUpdate1 = new TDEXPAudiomet()
										.Liberar2(hmUpdate);
								iUpdate4 = new TDEXPRegSint().Liberar(hmUpdate);
								iUpdate4 = new TDEXPSERVPRef()
										.Liberar(hmUpdate);

							}
						}
					}
				}
				// cAccion = "Actual";
				TVMEDServicio servicio = new DAOMEDServicio()
						.FindById(cServicio);
				ExpBitMod mod = new ExpBitMod();
				mod.setiCveExpediente(cCveExpediente);
				mod.setiNumExamen(cNumExamen);
				mod.setOperacion("4");// de liberar Servicio
				mod.setDescripcion("Se libera el Servicio "
						+ servicio.getCDscServicio());
				mod.setiCveUsuarioRealiza(String.valueOf(vUsuario
						.getICveusuario()));
				mod.setiCveServicio(cServicio);
				mod.setIpAddress(request.getParameter("hdIpAddress"));
				mod.setMacAddress(request.getParameter("hdMacAddress"));
				mod.setComputerName(request.getParameter("hdComputerName"));
				int insert = new TDEXPBITMOD().insertServiciosIpMacName(null,
						mod);
				// System.out.println("Liberados");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getIFlag() {
		return iFlag;
	}

	private void setIFlag(int iFlag) {
		this.iFlag = iFlag;
	}
}
