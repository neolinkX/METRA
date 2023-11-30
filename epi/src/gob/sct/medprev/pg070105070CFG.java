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
import java.text.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;
import com.micper.util.TExcel;
import com.micper.util.TFechas;

//import gob.sct.medprev.util.Dictamen;
import gob.sct.medprev.util.Dictamenes;
import gob.sct.medprev.util.ArchivoP;
import gob.sct.medprev.util.ArchivoCP;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import gob.sct.medprev.cntmgr.*;

/**
 * * Clase de configuracion para CFG Archivo
 * <p>
 * Administraci�n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco A. Gonz�lez Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070105070CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070105070CFG.png'>
 */
public class pg070105070CFG extends CFGListBase2 {
	public pg070105070CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		// cPaginas = "pg070105010.jsp";
	}

	private StringBuffer activeX = new StringBuffer("");

	public String getActiveX() {
		return activeX.toString();
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDEnvio dEnvio = new TDEnvio();
		Vector vcArchivado = new Vector();
		String cRetorno;
		int iEnvio = 0;
		try {

			TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
			TVEXPExamAplica vEXPExamAplica;
			TFechas dtFecha = new TFechas();

			vcArchivado = dEXPExamAplica.FindByArchivo(
					request.getParameter("iCveUniMed"),
					request.getParameter("iCveModulo"),
					request.getParameter("iCveProceso"),
					request.getParameter("dtFecha"), "SI");
			if (vcArchivado.size() > 0) {
				for (int i = 0; i < vcArchivado.size(); i++) {
					vEXPExamAplica = (TVEXPExamAplica) vcArchivado.get(i);
					if (request.getParameter("cb"
							+ vEXPExamAplica.getICveExpediente()) != null) {
						// if (request.getParameter("cb" +
						// vEXPExamAplica.getICveExpediente()).compareTo("ON")
						// == 0){
						cRetorno = (String) dEXPExamAplica.updateArchivado(
								null, "" + vEXPExamAplica.getICveExpediente(),
								"" + vEXPExamAplica.getINumExamen(), dtFecha
										.getFechaMMDDYYYY(dtFecha.TodaySQL(),
												"/"));
					}

				}

			}

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Método Guardar

	/**
	 * Método FillVector
	 */
	public void fillVector() {

		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
		try {
			iNumReg = 200;
			if (request.getParameter("hdBoton").compareTo("Expediente") == 0
					|| request.getParameter("hdBoton").compareTo("Guardar") == 0) {
				vDespliega = dEXPExamAplica.FindByArchivo(
						request.getParameter("iCveUniMed"),
						request.getParameter("iCveModulo"),
						request.getParameter("iCveProceso"),
						request.getParameter("dtFecha"), "");
			}
			if (request.getParameter("hdBoton").compareToIgnoreCase("Reporte") == 0) {
				vDespliega = dEXPExamAplica.FindByArchivo(
						request.getParameter("iCveUniMed"),
						request.getParameter("iCveModulo"),
						request.getParameter("iCveProceso"),
						request.getParameter("dtFecha"), "");
				//
				vEXPExamAplica = dEXPExamAplica.findExpediente(null,
						Integer.parseInt(request.getParameter("hdExpImp"), 10),
						request.getParameter("dtFecha"));
				// activeX.append(this.Report(vEXPExamAplica));
				request.setAttribute("REPORTE_PDF", getReporte(vEXPExamAplica));
				// this.getReporte(vEXPExamAplica);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public StringBuffer Report(Object obj) {
		TExcel Rep1 = new TExcel("07");
		Vector vcPersona = new Vector();
		Vector vcUniMed = new Vector();
		Vector vcServicios = new Vector();
		Vector vcModTrans = new Vector();
		String cArchivo = "";
		// TVEXPExamAplica vExamAplica = new TVEXPExamAplica();
		boolean lWhere = false;
		try {
			TFechas tFecha = new TFechas("07");

			TVEXPExamAplica vExamAplica = (TVEXPExamAplica) obj;
			TDPERDatos dPERDatos = new TDPERDatos();
			TVPERDatos vPERDatos;
			TDGRLUniMed dUniMed = new TDGRLUniMed();
			TVGRLUniMed vUniMed;
			TDEXPExamCat dExamCat = new TDEXPExamCat();
			TVEXPExamCat vExamCat;
			TDEXPServicio dEXPServicio = new TDEXPServicio();
			TVEXPServicio vEXPServicio;

			int iAnioNac = 0;
			int iAnioAct = 0;
			String cHoy;
			String cSolicitado;
			String cDictamen;
			String cConcluido;
			int iPrivada;

			String cApto;
			java.util.Date dtMarco = new java.util.Date();
			long lHoras;
			int iMinutos;
			java.text.SimpleDateFormat SDFHora = new java.text.SimpleDateFormat();

			vcPersona = dPERDatos.FindByPersona(""
					+ vExamAplica.getICvePersonal());

			if (vcPersona.size() > 0) {
				vPERDatos = (TVPERDatos) vcPersona.get(0);

				if (vExamAplica.getCDscSituacion().toString()
						.compareTo("NUEVO") == 0) {
					cArchivo = "EXPCLI-01";
				} else {
					cArchivo = "EXPCLIX-01";
				}

				Rep1.comDespliega("F", 27, "" + vExamAplica.getICveExpediente());
				Rep1.comDespliega("U", 37, "" + vExamAplica.getICveExpediente());
				Rep1.comDespliega("C", 72, "" + vExamAplica.getICveExpediente());
				Rep1.comEligeHoja(2);
				Rep1.comDespliega("G", 38,
						"'" + vExamAplica.getICveExpediente());
				Rep1.comDespliega("E", 30,
						"'" + vExamAplica.getICveExpediente());
				Rep1.comEligeHoja(1);

				Rep1.comDespliega("U", 8, vPERDatos.getCNombreCompleto());
				Rep1.comDespliega("C", 73, vPERDatos.getCNombreCompleto());
				Rep1.comEligeHoja(2);
				Rep1.comDespliega(
						"G",
						1,
						vPERDatos.getCApPaterno() + " "
								+ vPERDatos.getCApMaterno() + ", "
								+ vPERDatos.getCNombre());
				Rep1.comEligeHoja(1);

				if (vExamAplica.getLPrivada() > 0) {
					Rep1.comDespliega("S", 24, "PRIVADA");
				} else {
					Rep1.comDespliega("S", 24, "DEPENDENCIA");
				}

				Rep1.comDespliega("C", 75, vPERDatos.getCSexo());

				iAnioNac = tFecha.getIntYear(vPERDatos.getDtNacimiento());
				iAnioAct = tFecha.getIntYear(tFecha.TodaySQL());
				Rep1.comDespliega("C", 74, "" + (iAnioAct - iAnioNac));

				vcUniMed = dUniMed.FindUniMed("" + vExamAplica.getICveUniMed());

				if (vcUniMed.size() > 0) {
					vUniMed = (TVGRLUniMed) vcUniMed.get(0);
					Rep1.comDespliega("S", 8, vUniMed.getCDscUniMed());
					Rep1.comEligeHoja(2);
					Rep1.comDespliega("F", 1, vUniMed.getCDscUniMed());
					Rep1.comEligeHoja(1);
				}

				cHoy = "" + tFecha.getIntDay(tFecha.TodaySQL()) + "/"
						+ tFecha.getMonthName(tFecha.TodaySQL()) + "/"
						+ tFecha.getIntYear(tFecha.TodaySQL());

				Rep1.comDespliega("S", 40, cHoy);
				Rep1.comDespliega("C", 76, cHoy);
				Rep1.comEligeHoja(2);
				Rep1.comDespliega("F", 41, cHoy);
				Rep1.comEligeHoja(1);

				cSolicitado = ""
						+ tFecha.getIntDay(vExamAplica.getDtSolicitado()) + "/"
						+ tFecha.getMonthName(vExamAplica.getDtSolicitado())
						+ "/"
						+ tFecha.getIntYear(vExamAplica.getDtSolicitado());

				cDictamen = "" + tFecha.getIntDay(vExamAplica.getDtDictamen())
						+ "/"
						+ tFecha.getMonthName(vExamAplica.getDtDictamen())
						+ "/" + tFecha.getIntYear(vExamAplica.getDtDictamen());

				// TEMPORALMENTE DESACTIVADO POR LA VALIDACION DE TERMINO DE
				// SERVICIOS
				// MGonzalez 11:50 24/FEB/2005
				/*
				 * cConcluido = "" +
				 * tFecha.getIntDay(vExamAplica.getDtConcluido()) + "/" +
				 * tFecha.getMonthName(vExamAplica.getDtConcluido()) + "/" +
				 * tFecha.getIntYear(vExamAplica.getDtConcluido());
				 */

				Rep1.comDespliega("C", 78, cSolicitado);
				Rep1.comDespliega("C", 80, cDictamen);
				// Rep1.comDespliega("C", 79, cConcluido);

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				String hora = sdf.format(new java.util.Date());
				Rep1.comDespliega("C", 77, hora);

				vcModTrans = dExamCat.FindByExpedienteExamen(
						"" + vExamAplica.getICveExpediente(),
						"" + vExamAplica.getINumExamen());

				if (vcModTrans.size() > 0) {
					for (int iCon = 0; iCon < vcModTrans.size(); iCon++) {
						vExamCat = (TVEXPExamCat) vcModTrans.get(iCon);
						Rep1.comDespliega("B", 83 + iCon,
								vExamCat.getCDscMdoTrans());
						Rep1.comDespliega("D", 83 + iCon,
								vExamCat.getCDscCategoria());
						if (vExamCat.getLDictamen() > 0) {
							Rep1.comDespliega("E", 83 + iCon, "APTO");
						} else {
							Rep1.comDespliega("E", 83 + iCon, "NO APTO");
						}
					}
				}

				Rep1.comBordeRededor("A", 72, "T", 82 + vcModTrans.size() + 1,
						1, 1);

				vcServicios = dEXPServicio.FindByExpedienteExamen(""
						+ vExamAplica.getICveExpediente(),
						"" + vExamAplica.getINumExamen());

				if (vcServicios.size() > 0) {
					for (int iCon = 0; iCon < vcServicios.size(); iCon++) {
						vEXPServicio = (TVEXPServicio) vcServicios.get(iCon);
						Rep1.comDespliega("B", 95 + iCon,
								vEXPServicio.getCDscServicio());
						Rep1.comBordeRededor("H", 95 + iCon, "H", 95 + iCon, 1,
								1);
					}

				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		StringBuffer buffer = Rep1.creaActiveX(cArchivo, cArchivo, false,
				false, true);
		return buffer;
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

	private byte[] getReporte(Object obj) throws Exception {

		TFechas tFecha = new TFechas("07");

		String[] keys = { "userid", "password", "entity", "maxResults",
				"queryOP", "lintiCveDocumen" };
		String[] operators = { "", "", "", "", "", "=" };
		CM_GetContent cmImport = new CM_GetContent();

		byte[] data = null;

		Vector vcPersona = new Vector();
		Vector vcUniMed = new Vector();
		Vector vcServicios = new Vector();
		Vector vcModTrans = new Vector();
		String cArchivo = "";
		// TVEXPExamAplica vExamAplica = new TVEXPExamAplica();
		boolean lWhere = false;

		try {
			TVEXPExamAplica vExamAplica = (TVEXPExamAplica) obj;
			TDPERDatos dPERDatos = new TDPERDatos();
			TVPERDatos vPERDatos;
			TDGRLUniMed dUniMed = new TDGRLUniMed();
			TVGRLUniMed vUniMed;
			TDEXPExamCat dExamCat = new TDEXPExamCat();
			TVEXPExamCat vExamCat;
			TDEXPServicio dEXPServicio = new TDEXPServicio();
			TVEXPServicio vEXPServicio;

			int iAnioNac = 0;
			int iAnioAct = 0;
			String cHoy;
			String unimedp = "";
			String nidum = "";
			String empdep = "";
			String inventario = "";

			java.util.Date dtMarco = new java.util.Date();
			long lHoras;
			int iMinutos;
			java.text.SimpleDateFormat SDFHora = new java.text.SimpleDateFormat();

			vcPersona = dPERDatos.FindByPersona(""
					+ vExamAplica.getICvePersonal());

			/*
			 * if (vExamAplica.getICveUniMed() == 107) vcUniMed =
			 * dUniMed.FindUniMed("" + 125); else
			 */
			vcUniMed = dUniMed.FindUniMed("" + vExamAplica.getICveUniMed());

			if (vcUniMed.size() > 0) {
				vUniMed = (TVGRLUniMed) vcUniMed.get(0);
				unimedp = vUniMed.getCDscUniMed();
				nidum = vUniMed.getICveUddAdmiva();

				if (nidum.equals("660"))
					nidum = "4S";
				else
					nidum = "";
			}

			if (vExamAplica.getLPrivada() > 0) {
				empdep = "PRIVADA";
			} else {
				empdep = "DEPENDENCIA";
			}

			cHoy = "" + tFecha.getIntDay(tFecha.TodaySQL()) + "/"
					+ tFecha.getMonthName(tFecha.TodaySQL()) + "/"
					+ tFecha.getIntYear(tFecha.TodaySQL());

			if (vcPersona.size() > 0) {
				vPERDatos = (TVPERDatos) vcPersona.get(0);

				Dictamenes dictamenes = new Dictamenes();
				ArchivoP archivo = new ArchivoP();

				// Enviando Parametros
				String expedientep = String.valueOf(vExamAplica
						.getICveExpediente());
				String anolec = "" + tFecha.getIntYear(tFecha.TodaySQL());
				String Archivado = "" + vExamAplica.getDtSolicitado();
				String anoap = ""
						+ tFecha.getIntYear(vExamAplica.getDtSolicitado());
				inventario = nidum + ".1.7." + expedientep;

				archivo.setExpedienteP(" " + expedientep + " ");
				archivo.setPNombre(vPERDatos.getCNombreCompleto());
				archivo.setUnimedP(unimedp);
				archivo.setEmpDep(empdep);
				archivo.setFechaap(Archivado);
				archivo.setAnolec(anoap);
				archivo.setAnoap(anoap);
				archivo.setNidum(nidum);
				archivo.setInventario(inventario);

				dictamenes.add(archivo);

				ArchivoCP archivo2 = new ArchivoCP();
				dictamenes.add(archivo2);

				data = dictamenes.getReportePDF();
			}
		}// / try
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}
}