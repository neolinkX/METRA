package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.naming.*;
import javax.sql.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.sql.*;
import com.micper.ingsw.*;
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import com.micper.util.logging.*;
import com.micper.util.*;
import com.micper.sql.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;
import com.micper.seguridad.vo.*;

/**
 * * Clase de configuración para EXAM Aplica
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104001ACFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104001ACFG.png'>
 */
public class pg070104001BCFG extends CFGListBase2 {
	public pg070104001BCFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		/*
		 * Calcula Fecha Actual
		 */
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		String dFechaActual = "";
		TFechas dtFecha = new TFechas();
		dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha, "/");
		String cCampo = dFechaActual;
		dtCampo = tfCampo.getDateSQL(cCampo);

		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		try {

			boolean fAccepted = false;
			Object objjsp = this.getInputs();

			TVEXPExamAplica vExpExAp = (TVEXPExamAplica) objjsp;

			/*
			 * Cambiamos algunas banderas para permitir generar todo el vale de
			 * servicios nuevamente
			 */

			dEXPExamAplica.GeneraValeDeServicioNuevamente(vExpExAp);

			/* Fin del cambio de vales de servicio */

			TVEXPExamCat vEXPExamCat = new TVEXPExamCat();
			TVGRLMotivo vGRLMotivo = new TVGRLMotivo();
			TVMEDSintExamen vMEDSintExamen = new TVMEDSintExamen();
			TVMEDSintoma vMEDSintoma = new TVMEDSintoma();
			TVGRLUniMed vGRLUniMed = new TVGRLUniMed();
			TVEXPServicio vEXPServicio = new TVEXPServicio();
			TVEXPRama vEXPRama = new TVEXPRama();
			TVEXPResultado vEXPResultado = new TVEXPResultado();

			TDGRLMotivo dGRLMotivo = new TDGRLMotivo();
			TDMEDSintExamen dMEDSintExamen = new TDMEDSintExamen();
			TDEXPServicio dEXPServicio = new TDEXPServicio();
			TDEXPRama dEXPRama = new TDEXPRama();
			TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
			TDEXPResultado dEXPResultado = new TDEXPResultado();
			TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
			TDEXPExamCat dEXPExamCat = new TDEXPExamCat();

			Vector vcExpExamCat = new Vector();
			Vector vcGrlMotivo = new Vector();
			Vector vcMedSintExamen = new Vector();
			Vector vcMedSintoma = new Vector();
			Vector vcGrlUniMed = new Vector();
			Vector vcEXPExamCat = new Vector();
			Vector vcMEDSintExamen = new Vector();
			Vector vcMEDSintExamenServ = new Vector();
			Vector vcMEDSintExamenRama = new Vector();
			Vector vcEXPResultado = new Vector();
			Vector vcDatos = new Vector();
			Vector vcEXPServicio = new Vector();
			Vector vcEXPRama = new Vector();
			Vector vcExamAp = new Vector();

			String cGenero = "";
			if (request.getParameter("cGenero") != null) {
				cGenero = request.getParameter("cGenero");
			}

			/**
			 * Paso 1
			 * 
			 * Con iCveExpediente e iNumExam traigo de EXPExamCat: -
			 * iCveModoTrans - iCveCategoria - iCveMotivo
			 * 
			 */

			vcExpExamCat = dEXPExamAplica
					.FindExpExamCat(null, this.getInputs());
			vcExamAp = dEXPExamAplica
					.FindByAll(" where iCveExpediente = "
							+ request.getParameter("iCveExpediente")
							+ " and iNumExamen = "
							+ request.getParameter("iNumExamen"));
			if (vcExamAp.size() > 0
					&& ((TVEXPExamAplica) (vcExamAp.get(0))).getLIniciado() == 0) {
				if (vcExpExamCat.size() > 0) {

					for (int w = 0; w < vcExpExamCat.size(); w++)
						vEXPExamCat = (TVEXPExamCat) vcExpExamCat.get(w);

					/**
					 * Paso 2
					 * 
					 * Se Obtiene si se pueden realizar pagos a la Unidad Medica
					 * 
					 */

					HashMap hmMot = new HashMap();
					String cCveMotivo = "" + vEXPExamCat.getICveMotivo();
					hmMot.put("iCveMotivo", cCveMotivo);

					vcGrlUniMed = dGRLUniMed.FindByAll(hmMot);

					if (vcGrlUniMed.size() > 0) {
						vGRLUniMed = (TVGRLUniMed) vcGrlUniMed.get(0);
						if (vGRLUniMed.getLPago() != 0) {

							/**
							 * Paso 3
							 * 
							 * Se extrae la Lista de Motivos con el objetivo de
							 * tener el valor de lPago por motivo especifico
							 * 
							 */
							vcGrlMotivo = dGRLMotivo
									.FindByAll(" Where iCveMotivo = "
											+ vEXPExamCat.getICveMotivo());
							if (vcGrlMotivo.size() > 0) {
								for (int x = 0; x < vcGrlMotivo.size(); x++) {
									vGRLMotivo = (TVGRLMotivo) vcGrlMotivo
											.get(x);
									if (vGRLMotivo.getLPago() == 0) {
										// // System.out.println(
										// "NEGATIVO - Queda pendiente el WEBService - Los Motivos y sus Pagos");
										fAccepted = true; // Esto es por el
															// momento DEBE SER
															// FALSE
									} else {
										// // System.out.println(
										// "POSITIVO - Queda pendiente el WEBService - Los Motivos y sus Pagos");
										fAccepted = true;
									}
								}
							}

							StringBuffer cMotivo = new StringBuffer();
							StringBuffer cMdoTrans = new StringBuffer();
							StringBuffer cWhere = new StringBuffer();

							// Busca los Motivos y Modos de Transporte y crea
							// cadenas de caracteres
							vcEXPExamCat = dEXPExamCat
									.FindByAll(" where iCveExpediente = "
											+ request
													.getParameter("iCveExpediente")
											+ " and iNumExamen = "
											+ request
													.getParameter("iNumExamen"));
							for (int i = 0; i < vcEXPExamCat.size(); i++) {
								vEXPExamCat = (TVEXPExamCat) vcEXPExamCat
										.get(i);
								cMotivo.append(" iCveMotivo = "
										+ vEXPExamCat.getICveMotivo());
								cMdoTrans.append(" iCveMdoTrans = "
										+ vEXPExamCat.getICveMdoTrans());
								if (i < vcEXPExamCat.size() - 1) {
									cMotivo.append(" or ");
									cMdoTrans.append(" or ");
								}
							}

							// Crea el where
							cWhere.append(" where S.iCveProceso  = "
									+ request.getParameter("iCveProceso")
									+ " and ("
									+ cMotivo
									+ ")"
									+ " and ("
									+ cMdoTrans
									+ ")"
									+ " and ( SN.cGenero = 'A' or SN.cGenero = '"
									+ request.getParameter("cGenero") + "')");

							vcMEDSintExamen = dMEDSintExamen
									.FindAllExam(cWhere);
							vcMEDSintExamenServ = dMEDSintExamen
									.FindAllServ(cWhere);
							vcMEDSintExamenRama = dMEDSintExamen
									.FindAllRama(cWhere);
							vcEXPServicio = new Vector();

							// Crea tres vectores para Insertar en EXPServicio,
							// EXPRama, EXPResultado

							for (int i = 0; i < vcMEDSintExamenServ.size(); i++) {
								vMEDSintExamen = (TVMEDSintExamen) vcMEDSintExamenServ
										.get(i);
								vEXPServicio = new TVEXPServicio();
								vEXPServicio
										.setICveExpediente(Integer.parseInt(request
												.getParameter("iCveExpediente")));
								vEXPServicio.setINumExamen(Integer
										.parseInt(request
												.getParameter("iNumExamen")));
								vEXPServicio.setICveServicio(vMEDSintExamen
										.getICveServicio());
								vEXPServicio.setDtFin(dtCampo);
								vEXPServicio.setDtInicio(dtCampo);
								vEXPServicio.setLConcluido(0);
								vcEXPServicio.addElement(vEXPServicio);
							}

							for (int i = 0; i < vcMEDSintExamenRama.size(); i++) {
								vMEDSintExamen = (TVMEDSintExamen) vcMEDSintExamenRama
										.get(i);
								vEXPRama = new TVEXPRama();
								vEXPRama.setICveExpediente(Integer.parseInt(request
										.getParameter("iCveExpediente")));
								vEXPRama.setINumExamen(Integer.parseInt(request
										.getParameter("iNumExamen")));
								vEXPRama.setICveServicio(vMEDSintExamen
										.getICveServicio());
								vEXPRama.setICveRama(vMEDSintExamen
										.getICveRama());
								vEXPRama.setDtFin(dtCampo);
								vEXPRama.setDtInicio(dtCampo);
								vEXPRama.setIConcluido(0);
								vcEXPRama.addElement(vEXPRama);
							}

							for (int i = 0; i < vcMEDSintExamen.size(); i++) {
								vMEDSintExamen = (TVMEDSintExamen) vcMEDSintExamen
										.get(i);
								vEXPResultado = new TVEXPResultado();
								vEXPResultado
										.setICveExpediente(Integer.parseInt(
												request.getParameter("iCveExpediente"),
												10));
								vEXPResultado
										.setINumExamen(Integer.parseInt(request
												.getParameter("iNumExamen"), 10));
								vEXPResultado.setICveServicio(vMEDSintExamen
										.getICveServicio());
								vEXPResultado.setICveRama(vMEDSintExamen
										.getICveRama());
								vEXPResultado.setICveSintoma(vMEDSintExamen
										.getICveSintoma());
								vEXPResultado.setCValRef(vMEDSintExamen
										.getCValRef());
								vcEXPResultado.addElement(vEXPResultado);
							}

							dEXPExamAplica.GeneraExamen(vcEXPServicio,
									vcEXPRama, vcEXPResultado);
						}
					}

					// } (int w = 0; w < vcExpExamCat.size(); w++)
				}

				if (vcGrlUniMed.size() > 0) {
					// if (vGRLUniMed.getLPago() != 0) {
					dEXPExamAplica.updateArchivo(null, this.getInputs());
					// }
				}
			}
			// dEXPExamAplica.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		try {
			cClave = (String) dEXPExamAplica.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo BorrarB
	 */
	public void BorrarB() {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		try {
			// cClave = (String) dEXPExamAplica.disable(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.BorrarB();
		}
	} // Metodo BorrarB

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		String lAction = request.getParameter("hdBoton");
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		try {
			// vDespliega = dEXPExamAplica.FindByAll();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo findUser
	 */
	public TVPERDatos findUser() {
		TVPERDatos vPerDat = null;
		try {
			String cUserId = request.getParameter("hdICvePersonal");
			String cNumExa = request.getParameter("iNumExamen");

			if (cUserId == null) {
				cUserId = "0";
			}
			int iCvePersonal = Integer.parseInt(cUserId);

			if (cNumExa == null) {
				cNumExa = "0";
			}
			int iNumExamen = Integer.parseInt(cNumExa);

			vPerDat = new TDEXPExamAplica().findUser(iCvePersonal, iNumExamen);
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return vPerDat;
	}

	/**
	 * Metodo getEdad
	 */
	public int getEdad(java.sql.Date dtFechaNac) {
		Calendar hoy = Calendar.getInstance();
		Calendar nac = Calendar.getInstance();
		nac.setTime(dtFechaNac);
		int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);
		if ((hoy.get(Calendar.MONTH) << 5) + hoy.get(Calendar.DATE) < (nac
				.get(Calendar.MONTH) << 5) + nac.get(Calendar.DATE)) {
			edad--;
		}
		return edad;
	}

	/**
	 * Metodo findExpediente
	 */
	public TVPERDatos findExamenToDelete() {
		TVPERDatos vPerDat = null;
		try {
			vPerDat = new TDEXPExamAplica().findExpToDelete(this.getInputs());
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return vPerDat;
	}

	public TVPERDatos DeleteExam() {
		TVPERDatos datos = null;
		try {
			TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(true)
					.getAttribute("UsrID");
			ExpBitMod mod = new ExpBitMod();
			mod.setIpAddress(request.getParameter("hdIpAddress"));
			mod.setMacAddress(request.getParameter("hdMacAddress"));
			mod.setComputerName(request.getParameter("hdComputerName"));
			datos = new TDEXPExamAplica().DeleteExpExam(this.getInputs(),
					vUsuario.getICveusuario(), mod);
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return datos;
	}

	/**
	 * Metodo FillPK
	 */
	// public void FillPK(){
	// mPk.add(cActual);
	// }
	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		/*
		 * Calcula Fecha Actual
		 */
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		String dFechaActual = "";
		TFechas dtFecha = new TFechas();
		dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha, "/");

		TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();

		try {
			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}

			vEXPExamAplica.setICveExpediente(iCampo);

			cCampo = "" + request.getParameter("iNumExamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setINumExamen(iCampo);

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCveProceso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICveProceso(iCampo);

			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICveModulo(iCampo);

			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICvePersonal(iCampo);

			cCampo = "" + request.getParameter("dtSolicitado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamAplica.setDtSolicitado(dtCampo);

			cCampo = "" + request.getParameter("iFolioEs");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setIFolioEs(iCampo);

			cCampo = "" + request.getParameter("dtAplicacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			// vEXPExamAplica.setDtAplicacion(dtCampo);
			vEXPExamAplica.setDtAplicacion(tfCampo.TodaySQL());

			cCampo = "" + request.getParameter("dtConcluido");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamAplica.setDtConcluido(dtCampo);

			cCampo = "" + request.getParameter("lIniciado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setLIniciado(iCampo);

			cCampo = "" + request.getParameter("lDictaminado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setLDictaminado(iCampo);

			cCampo = "" + request.getParameter("lInterconsulta");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setLInterConsulta(iCampo);

			cCampo = "" + request.getParameter("lInterConcluye");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setLInterConcluye(iCampo);

			cCampo = "" + request.getParameter("lConcluido");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setLConcluido(iCampo);

			cCampo = "" + request.getParameter("dtDictamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}

			cCampo = "" + request.getParameter("dtArchivado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}

			vEXPExamAplica.setDtDictamen(dtCampo);

			cCampo = "" + request.getParameter("dtEntregaRes");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamAplica.setDtEntregaRes(dtCampo);

			cCampo = "" + request.getParameter("dtArchivado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = defaultFecha;
			}
			vEXPExamAplica.setDtArchivado(dtCampo);

			cCampo = "" + request.getParameter("iCveNumEmpresa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPExamAplica.setICveNumEmpresa(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPExamAplica;
	}
}


