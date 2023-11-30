package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.util.TExcel;
import com.micper.util.TFechas;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.excepciones.*;
import com.micper.seguridad.vo.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuracion para Diagnostico y Recomendacion
 * <p>
 * Administraci�n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104031CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104031CFG.png'>
 */
public class pg070106053CFG extends CFGListBase2 {
	public pg070106053CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070106051.jsp";
	}

	private StringBuffer activeX = new StringBuffer("");

	/**
	 * M�todo Guardar
	 */
	public void Guardar() {
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			/**
			 * Inserta / Actualiza a EXPExamCat
			 */
			TVEXPDictamenServ vcListaCat = new TVEXPDictamenServ();
			Vector vListaCat = new Vector();
			vListaCat = this.findCatSol();
			int lDictamen = 0;
			int iCveMotivo = 0;
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

					if (request.getParameter("iCveMotivo" + i) != null) {
						iCveMotivo = Integer.parseInt(request.getParameter(
								"iCveMotivo" + i).toString());
					} else {
						iCveMotivo = 0;
					}
					cClave = (String) dEXPDictamenServ
							.insertExamCat(null, this.getInputs(),
									vcListaCat.getICveCategoria(),
									vcListaCat.getICveMdoTrans(), lDictamen,
									iCveMotivo);
				}
				dEXPDictamenServ.updateEXPEADictaminado(null, this.getInputs());
			}
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // M�todo Guardar

	/**
	 * M�todo GuardarA
	 */
	public void GuardarA() {
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			// cClave = (String) dEXPDictamenServ.update(null,
			// this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // M�todo GuardarA

	/**
	 * M�todo Borrar
	 */
	public void Borrar() {
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			cClave = (String) dEXPDictamenServ.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // M�todo Borrar

	/**
	 * M�todo FillVector
	 */
	public void fillVector() {
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			String lAction = request.getParameter("hdBoton");
			String where = " where icveexpediente = "
					+ request.getParameter("iCveExpediente")
					+ " and inumexamen = " + request.getParameter("iNumExamen")
					+ " ";

			vDespliega = dEXPDictamenServ.FindByAll(where);

			if (request.getParameter("hdBoton").compareToIgnoreCase(
					"Imprime Documentacion") == 0) {
				activeX.append(this.GenRep(this.getInputs()));
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * M�todo getEdad
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

	public TVPERDatos findExpediente() {
		TVPERDatos vPerDat = null;
		try {
			vPerDat = new TDEXPDictamenServ().findUserExp(this.getInputs());
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return vPerDat;
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

	public Vector listaCategoria() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.FindListaCat(this.getInputs());
		} catch (Exception ex) {
			error("listaCategoria", ex);
		}
		return vcDictamenServ;
	}

	public int lDictaminadoEA() {
		int lDictaminado = 0;
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			lDictaminado = dEXPDictamenServ.lDictaminadoExpExamAplica(null,
					this.getInputs());
		} catch (Exception ex) {
			error("lDictaminadoEA", ex);
		}
		return lDictaminado;
	}

	public Vector listanMedica() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpSer(this.getInputs());
		} catch (Exception ex) {
			error("listanMedica", ex);
		}
		return vcDictamenServ;
	}

	public Vector listaDicSer() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpDicSer(this.getInputs());
		} catch (Exception ex) {
			error("listaDicSer", ex);
		}
		return vcDictamenServ;
	}

	public Vector listaExaCat() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ
					.findByExpExamCat(this.getInputs());
		} catch (Exception ex) {
			error("listaExaCat", ex);
		}
		return vcDictamenServ;
	}

	public Vector listaExpRec() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpRec(this.getInputs());
		} catch (Exception ex) {
			error("listaExpRec", ex);
		}
		return vcDictamenServ;
	}

	public Vector listaExpDia() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpDia(this.getInputs());
		} catch (Exception ex) {
			error("listaExpDia", ex);
		}
		return vcDictamenServ;
	}

	/**
	 * M�todo FillPK
	 */
	/*
	 * public void FillPK() { mPk.add(cActual); }
	 */

	/**
	 * M�todo getInputs
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
				iCampo = 10;
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

			cCampo = "" + request.getParameter("lDictamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setLDictamen(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPDictamenServ;
	}

	public StringBuffer GenRep(Object obj) {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas();
		String cNomArchivo = new String("pg070105040");
		int iRenglon = 0;
		int iFilasxPag = 65;
		int iPag = 0;
		int iPagAnt = 0;
		int iRenIni = 0;
		int iRenFin = 0;
		int iRenxPag = 4;
		int iCvePersonal = 0;
		int iCveUniMed = 0;
		int iCveProceso = 0;
		String cPersonal = "";
		String cCveUniMed = "";
		String cCveProceso = "";
		String pName = "";
		String pEmpresa = "";
		String Doctor = "";
		StringBuffer buffer = new StringBuffer();
		java.sql.Date dtConcluido;
		String dFinalExamen = "";
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;

		try {

			cPersonal = "" + request.getParameter("iCvePersonal");
			if (cPersonal.compareTo("null") != 0
					&& cPersonal.compareTo("") != 0) {
				iCvePersonal = Integer.parseInt(cPersonal, 10);
			} else {
				iCvePersonal = 0;
			}

			cCveUniMed = "" + request.getParameter("iCveUniMed");
			if (cCveUniMed.compareTo("null") != 0
					&& cCveUniMed.compareTo("") != 0) {
				iCveUniMed = Integer.parseInt(cCveUniMed, 10);
			} else {
				iCveUniMed = 0;
			}

			cCveProceso = "" + request.getParameter("iCveProceso");
			if (cCveUniMed.compareTo("null") != 0
					&& cCveProceso.compareTo("") != 0) {
				iCveProceso = Integer.parseInt(cCveProceso, 10);
			} else {
				iCveProceso = 0;
			}

			TVPERDatos vPERDatos = new TVPERDatos();
			Vector vcPERDatos = new Vector();
			TDPERDatos dPERDatos = new TDPERDatos();
			vcPERDatos = dPERDatos.FindByPersona("" + iCvePersonal);

			vPERDatos = (TVPERDatos) vcPERDatos.get(0);

			TVPERDireccion vPERDireccion = new TVPERDireccion();
			Vector vcPERDireccion = new Vector();
			TDPERDireccion dPERDireccion = new TDPERDireccion();
			vcPERDireccion = dPERDireccion
					.FindByAllcWhere(" Where iCvePersonal = " + iCvePersonal);

			vPERDireccion = (TVPERDireccion) vcPERDireccion.get(0);

			pName = "" + request.getParameter("pName");
			if (pName.compareTo("null") == 0) {
				pName = "";
			} else {
				pName = request.getParameter("pName");
			}

			pEmpresa = "" + request.getParameter("pEmpresa");
			if (pEmpresa.compareTo("null") == 0) {
				pEmpresa = "";
			} else {
				pEmpresa = request.getParameter("pEmpresa");
			}

			Doctor = "" + request.getParameter("Doctor");
			if (Doctor.compareTo("null") == 0) {
				Doctor = "";
			} else {
				Doctor = request.getParameter("Doctor");
			}

			if (request.getParameter("hdDtConcluido") != null) {
				dtConcluido = pFecha.getDateSQL(request.getParameter(
						"hdDtConcluido").toString());
			} else {
				dtConcluido = pFecha.TodaySQL();
			}

			dFinalExamen = spanishExamDate(dtConcluido);

			int lActivo = 1;

			HashMap hmfieldsTable = new HashMap();
			hmfieldsTable.put("iCveProceso", "" + iCveProceso);
			hmfieldsTable.put("lActivo", "" + lActivo);

			TVEXPDictamenServ vcReporte = new TVEXPDictamenServ();
			Vector vReporte = new Vector();
			vReporte = this.findCatSol();

			TVGRLUniMed vGRLUniMed = new TVGRLUniMed();
			Vector vcGRLUniMed = new Vector();
			TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();

			vcGRLUniMed = dEXPDictamenServ.getUniMed(iCveProceso, iCveUniMed);
			vGRLUniMed = (TVGRLUniMed) vcGRLUniMed.get(0);

			TVGRLProceso vGRLProceso = new TVGRLProceso();
			Vector vcGRLProceso = new Vector();
			TDGRLProceso dGRLProceso = new TDGRLProceso();

			vcGRLProceso = dGRLProceso.FindByAll(hmfieldsTable);

			vGRLProceso = (TVGRLProceso) vcGRLProceso.get(0);

			if (vReporte.size() == 0) {
				vErrores.acumulaError("", 15, "");
				return buffer;
			}

			int lDictamen = 0;
			int iCveMotivo = 0;
			if (vReporte.size() > 0) {
				for (int i = 0; i < vReporte.size(); i++) {
					vcReporte = (TVEXPDictamenServ) vReporte.get(i);
					if (request.getParameter("lDictamen"
							+ vcReporte.getICveCategoria() + "_"
							+ vcReporte.getICveMdoTrans()) != null) {
						lDictamen = Integer.parseInt(request.getParameter(
								"lDictamen" + vcReporte.getICveCategoria()
										+ "_" + vcReporte.getICveMdoTrans())
								.toString());
					} else {
						lDictamen = 0;
					}

					if (request.getParameter("iCveMotivo" + i) != null) {
						iCveMotivo = Integer.parseInt(request.getParameter(
								"iCveMotivo" + i).toString());
					} else {
						iCveMotivo = 0;
					}

					if (1 == 1) {

						if (lDictamen == 1) {
							xl.comDespliega("G", 5, "");
							xl.comDespliega("D", 25, "APTO");
						} else {
							xl.comDespliega("G", 5, "NO");
							xl.comDespliega("D", 25, "NO APTO");
						}

						xl.comDespliega("F", 4,
								"" + vEXPDictamenServ.getICveExpediente());
						xl.comDespliega("E", 6, vGRLProceso.getCDscProceso());
						xl.comDespliega("E", 8, vGRLUniMed.getCDscUniMed());
						xl.comDespliega(
								"E",
								9,
								vGRLUniMed.getCDscUMEstado().substring(0, 1)
										.toUpperCase()
										+ vGRLUniMed
												.getCDscUMEstado()
												.substring(
														1,
														vGRLUniMed
																.getCDscUMEstado()
																.length())
												.toLowerCase()
										+ " "
										+ vGRLUniMed.getCDscUMPais()
												.substring(0, 1).toUpperCase()
										+ vGRLUniMed
												.getCDscUMPais()
												.substring(
														1,
														vGRLUniMed
																.getCDscUMPais()
																.length())
												.toLowerCase()
										+ this.spanishDate());
						xl.comDespliega("C", 14, pName);
						xl.comDespliega("B", 24, " resultado del "
								+ vGRLProceso.getCDscProceso()
								+ " que le fue practicado el d�a "
								+ dFinalExamen);
						xl.comDespliega("B", 15, vPERDireccion.getCCalle()
								+ " # " + vPERDireccion.getCNumExt() + " INT. "
								+ vPERDireccion.getCNumInt());
						xl.comDespliega("B", 16,
								"COLONIA " + vPERDireccion.getCColonia());
						xl.comDespliega("B", 17, vPERDireccion.getCCiudad()
								+ ", " + vPERDireccion.getCDscEstado());
						xl.comDespliega("B", 18, vPERDireccion.getCDscPais());
						xl.comDespliega("C", 42, Doctor);
						xl.comDespliega("B", 46, vcReporte.getCDscMdoTrans());
						xl.comDespliega("B", 47, vPERDatos.getCDscEmpresa());
						xl.comDespliega("B", 58, pName);
						xl.comDespliega(
								"B",
								59,
								vGRLUniMed.getCDscUMEstado().substring(0, 1)
										.toUpperCase()
										+ vGRLUniMed
												.getCDscUMEstado()
												.substring(
														1,
														vGRLUniMed
																.getCDscUMEstado()
																.length())
												.toLowerCase()
										+ " "
										+ vGRLUniMed.getCDscUMPais()
												.substring(0, 1).toUpperCase()
										+ vGRLUniMed
												.getCDscUMPais()
												.substring(
														1,
														vGRLUniMed
																.getCDscUMPais()
																.length())
												.toLowerCase()
										+ this.spanishDate());
						// xl.comDespliega("B", 60, this.spanishDate());
					}
				}
			}

		} // / try
		catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Exception in Generar Reporte : " +
			// ex.getMessage());
		}
		buffer = xl.creaActiveX("pg070105040", cNomArchivo, false, false, true);
		return buffer;
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

	public String spanishDate() {
		String fechaFormat = "";
		TFechas dtFecha = new TFechas("07");
		int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
		int iDay = dtFecha.getIntDay(dtFecha.TodaySQL());
		String cMes = dtFecha.getMonthName(dtFecha.TodaySQL());
		fechaFormat = " a " + iDay + " de " + cMes + " de " + iAnio;
		return fechaFormat;
	}

	public String spanishExamDate(java.sql.Date dConcluido) {
		String fechaFormat = "";
		TFechas dtFecha = new TFechas("07");
		int iAnio = dtFecha.getIntYear(dConcluido);
		int iDay = dtFecha.getIntDay(dConcluido);
		String cMes = dtFecha.getMonthName(dConcluido);
		fechaFormat = iDay + " del mes " + cMes + " y a�o de " + iAnio;
		return fechaFormat;
	}

}