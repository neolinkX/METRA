package gob.sct.medprev;

import com.micper.util.*;
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
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.TVUsuario;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuracion para Diagnostico y Recomendacion
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104031CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104031CFG.png'>
 */
public class pg070104031CFG extends CFGListBase2 {

	private StringBuffer activeX = new StringBuffer("");

	public pg070104031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			/**
			 * Inserta / Actualiza a EXPDictamenServ
			 */
			TVEXPDictamenServ Serv = new TVEXPDictamenServ();
			Serv = (TVEXPDictamenServ) this.getInputs();
			Vector vServicio = (new TDEXPServicio()).getLConcluido(
					String.valueOf(Serv.getICveExpediente()),
					String.valueOf(Serv.getINumExamen()),
					String.valueOf(Serv.getICveServicio()));
			/*
			 * if (vServicio.size() > 0 && ( (TVEXPServicio)
			 * vServicio.get(0)).getLConcluido() != 1) {
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
			 * Inserci�n Marco Gonz�lez 21/Octubre/2004 Inserci�n a tabla
			 * EXPExamCat para confrimaci�n de Aptos o no Aptos en EMO Solo
			 * para proceso EMO uso del par�metro EMOServicio
			 */
			if (request.getParameter("iCveServicio").compareTo(
					vParametros.getPropEspecifica("EMOServicio").toString()) == 0) {

				TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
				TVEXPExamCat vEXPExamCat = new TVEXPExamCat();

				vEXPExamCat.setINumExamen(Integer.parseInt(request
						.getParameter("iNumExamen")));
				vEXPExamCat.setICveExpediente(Integer.parseInt(request
						.getParameter("iCveExpediente")));
				vEXPExamCat.setICveMdoTrans(Integer.parseInt(request
						.getParameter("iCveMdoTrans")));
				vEXPExamCat.setICveCategoria(Integer.parseInt(request
						.getParameter("iCveCategoria")));
				vEXPExamCat.setLDictamen(Integer.parseInt(request
						.getParameter("lDictamen"
								+ vEXPExamCat.getICveCategoria() + "_"
								+ vEXPExamCat.getICveMdoTrans())));
				dEXPExamCat.updateEMOCat(null, vEXPExamCat);
			}
			/***********************************************************************/

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
			

			////Registrar claves CIF
			String aCveDiagnostico2[] = request
					.getParameterValues("iCveDiagnostico2");
		 //System.out.println("Diagnostico = "+request.getParameterValues("iCveDiagnostico2"));
			String iCveRamaCif = "";
			String iCveCif = "";

			if (aCveDiagnostico2 != null) {
				if (aCveDiagnostico2.length > 0) {
					for (int w = 0; w < aCveDiagnostico2.length; w++) {
						iCveRamaCif = aCveDiagnostico2[w].substring(
								aCveDiagnostico2[w].lastIndexOf("_") + 1,
								aCveDiagnostico2[w].length());
						iCveCif = aCveDiagnostico2[w].substring(0,
								aCveDiagnostico2[w].lastIndexOf("_"));
						// System.out.println(iCveDiagnostico+ "  "
						// +iCveEspecialidad);
						dEXPDictamenServ.insertExpDiaCif(null,
								this.getInputs(), iCveCif,
								iCveRamaCif);
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

			/* } */
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
	} // Metodo GuardarA

	/**
	 * Metodo Borrar
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
	} // Metodo Borrar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		String cWhere = "";
		try {

			if (request.getParameter("hdBoton").compareToIgnoreCase(
					"Imprime Documentacion") == 0) {
				activeX.append(this.GenRep(this.getInputs()));
			}

			String lAction = request.getParameter("hdBoton");
			cWhere = " where iCveExpediente =  "
					+ request.getParameter("iCveExpediente")
					+ " and iNumExamen = " + request.getParameter("iNumExamen");
			vDespliega = dEXPDictamenServ.FindByAll(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
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

	public TVPERDatos findExpediente() {
		TVPERDatos vPerDat = null;
		try {
			vPerDat = new TDEXPDictamenServ().findUserbyExp(this.getInputs());
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

	public int lConcluidoEA() {
		int lConcluye = 0;
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			lConcluye = dEXPDictamenServ.lConcluidoExpExamAplica(null,
					this.getInputs());
		} catch (Exception ex) {
			error("lConcluidoEA", ex);
		}
		return lConcluye;
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

	public Vector listaExpDiaCif() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpDiaCif(this.getInputs());
		} catch (Exception ex) {
			error("listaExpDia", ex);
		}
		return vcDictamenServ;
	}
	
	/**
	 * Metodo FillPK
	 */
	/*
	 * public void FillPK() { mPk.add(cActual); }
	 */

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		// TFechas tfCampo = new TFechas();
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

			vEXPDictamenServ.setICveUsuDictamen(iCampo);
			int iCveUsuario = ((TVUsuario) (((HttpServletRequest) request)
					.getSession(true).getAttribute("UsrID"))).getICveusuario();
			vEXPDictamenServ.setICveUsuDictamen(iCveUsuario);

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

			// Obtener cedula profesional del Doctor
			String cedulaDoctor = "";
			String NumeroDoc = "";
			String ClaveExpediente = request.getParameter("iCveExpediente");
			String Numeroexamen = request.getParameter("iNumExamen");
			com.micper.ingsw.TParametro VParametros = new com.micper.ingsw.TParametro(
					"07");
			String dataSourceName = VParametros
					.getPropEspecifica("ConDBModulo");
			com.micper.sql.DbConnection dbConn = new com.micper.sql.DbConnection(
					dataSourceName);
			java.sql.Connection conn = dbConn.getConnection();
			java.sql.PreparedStatement pstmtNumDoc = null;
			String cSQLNumDoc = "SELECT ICVEUSUDICTAMEN FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ Numeroexamen
					+ ";";
			pstmtNumDoc = conn.prepareStatement(cSQLNumDoc);
			java.sql.ResultSet rsetNumDoc = pstmtNumDoc.executeQuery();
			while (rsetNumDoc.next()) {
				NumeroDoc = rsetNumDoc.getString(1);
			}
			if (NumeroDoc == null) {
				cedulaDoctor = " ";
			} else {
				java.sql.PreparedStatement pstmtNumCed = null;
				String cSQLNumCed = "SELECT CCEDULA FROM GRLUSUARIO WHERE ICVEUSUARIO = "
						+ NumeroDoc + ";";
				pstmtNumCed = conn.prepareStatement(cSQLNumCed);
				java.sql.ResultSet rsetNumCed = pstmtNumCed.executeQuery();
				while (rsetNumCed.next()) {
					cedulaDoctor = rsetNumCed.getString(1);
				}
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

					// if (lDictamen == 0){
					if (1 == 1) {

						if (lDictamen == 1) {
							xl.comDespliega("E", 5,
									"Notificacion de dictamen de Aptitud  Psicof�sica");
							xl.comDespliega("B", 25,
									"en el que result� APTO.");
							xl.comDespliega("E", 34, "Aptitud");
						} else {
							xl.comDespliega("E", 5,
									"Notificacion de dictamen de No Aptitud  Psicof�sica");
							xl.comDespliega("B", 25,
									"en el que result� NO APTO.");
							xl.comDespliega("E", 34, "No Aptitud");
						}

						xl.comDespliega("C", 15,
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
						xl.comDespliega("C", 47, pName);
						xl.comDespliega("B", 24,
								"" + vGRLProceso.getCDscProceso()
										+ " que le fue practicado el d�a "
										+ dFinalExamen);
						/*
						 * xl.comDespliega("B", 15, vPERDireccion.getCCalle() +
						 * " # " + vPERDireccion.getCNumExt() + " INT. " +
						 * vPERDireccion.getCNumInt()); xl.comDespliega("B", 16,
						 * "COLONIA " + vPERDireccion.getCColonia());
						 * xl.comDespliega("B", 17, vPERDireccion.getCCiudad() +
						 * ", " + vPERDireccion.getCDscEstado());
						 * xl.comDespliega("B", 18,
						 * vPERDireccion.getCDscPais());
						 */
						xl.comDespliega("C", 42, Doctor);
						xl.comDespliega("C", 43, cedulaDoctor);
						// xl.comDespliega("B", 46,
						// vcReporte.getCDscMdoTrans());
						// xl.comDespliega("B", 47, vPERDatos.getCDscEmpresa());
						xl.comDespliega("C", 47, pName);
						xl.comDespliega(
								"G",
								47,
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
		buffer = xl.creaActiveX("pg070104031", cNomArchivo, false, false, true);
		return buffer;
	}

	public String getActiveX() {
		return activeX.toString();
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