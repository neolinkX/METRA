/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;

/**
 * 
 * @author AG SA
 */

public class TDEPICisCitaAdnl extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private static SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

	public TDEPICisCitaAdnl() {
	}

	/**
	 * MÃ©todo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtFind = null;
		PreparedStatement pstmtValida = null;
		ResultSet rset = null;
		ResultSet rsetValida = null;

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";

			TVEPICisCita vEPICisCita = (TVEPICisCita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int iConsecutivo = 0;
			int iExistencia = 0;
			String cSQLFind;
			String cRes;
			String cSQLValida = "";

			cSQLValida = "select iCvePersonal from EPICisCita "
					+ "where iCvePersonal = ? and iCveUniMed = ? and dtFecha = ?"; // and
																					// iCveModulo
																					// =
																					// ?";

			pstmtValida = conn.prepareStatement(cSQLValida);
			pstmtValida.setInt(1, vEPICisCita.getICvePersonal());
			pstmtValida.setInt(2, vEPICisCita.getICveUniMed());
			pstmtValida.setDate(3, vEPICisCita.getDtFecha());
			// pstmtValida.setInt(4, vEPICisCita.getICveModulo());
			rsetValida = pstmtValida.executeQuery();

			while (rsetValida.next()) {
				iExistencia = rsetValida.getInt(1);
			}

			// Validamos que la persona que realiza la cita no sea tercero
			int esTercero = 0;// No es tercero
			String cSQLValidaTerceros = "SELECT LPRIVADA FROM GRLUNIMED WHERE ICVEUNIMED =  "
					+ vEPICisCita.getICveUniMed();
			PreparedStatement pstmtValidaTerceros = conn
					.prepareStatement(cSQLValidaTerceros);
			ResultSet rsetValidaTerceros = pstmtValidaTerceros.executeQuery();
			while (rsetValidaTerceros.next()) {
				esTercero = rsetValidaTerceros.getInt(1);
			}
			if (vEPICisCita.getICveMotivo() == 5 && esTercero == 1) {// Si es
																		// REVALORACION
																		// y es
																		// TERCERO
				cSQLValidaTerceros = "SELECT A.LDICTAMEN, B.LDICTAMINADO, C.LPRIVADA, A.ICVEEXPEDIENTE, A.INUMEXAMEN, B.ICVEUNIMED  "
						+ "FROM EXPEXAMCAT A "
						+ "JOIN EXPEXAMAPLICA B on A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE "
						+ "AND A.INUMEXAMEN = B.INUMEXAMEN "
						+ " JOIN GRLUNIMED C ON B.ICVEUNIMED = C.ICVEUNIMED "
						+ "WHERE A.ICVEEXPEDIENTE = "
						+ vEPICisCita.getICvePersonal()
						+ " AND B.ICVEPROCESO = 1 "
						+ " AND B.LDICTAMINADO = 1 ORDER BY INUMEXAMEN";
				// System.out.println(cSQLValidaTerceros);
				PreparedStatement pstmtValidaTerceros2 = conn
						.prepareStatement(cSQLValidaTerceros);
				ResultSet rsetValidaTerceros2 = pstmtValidaTerceros2
						.executeQuery();
				int ldictamen = 1;// APTO
				int ldictaminado = 1;// DICTAMINADO
				int icveunimedEsTercero = 0;// ES INTERNA DE LA SCT
				while (rsetValidaTerceros2.next()) {// Se quedara con los
													// valores del ultimo examen
													// por el ciclo
					ldictamen = rsetValidaTerceros2.getInt(1);
					ldictaminado = rsetValidaTerceros2.getInt(2);
					icveunimedEsTercero = rsetValidaTerceros2.getInt(3);
					// System.out.println(rsetValidaTerceros2.getInt(5));
				}
				if (ldictamen == 0 && ldictaminado == 1
						&& icveunimedEsTercero == 0) {// Si el ultimo examen fue
														// DICTAMINADO y resulto
														// NO APTO y fue por
														// DICTAMINADO POR LA
														// SCT
					// if (ldictamen == 0 && ldictaminado == 1) {//Si el ultimo
					// examen fue DICTAMINADO y resulto NO APTO y fue por
					// DICTAMINADO POR LA SCT
					cClave = "ERROR2";
					return cClave;
				}
			}
			// FIN DE Validamos que la persona que realiza la cita no sea
			// tercero

			if (iExistencia == 0) {

				cSQLFind = "select max(iCveCita) "
						+ "from EPICisCita "
						+ "where iCveUniMed = ? and dtFecha = ? and iCveModulo = ?";
				pstmtFind = conn.prepareStatement(cSQLFind);
				pstmtFind.setInt(1, vEPICisCita.getICveUniMed());
				pstmtFind.setDate(2, vEPICisCita.getDtFecha());
				pstmtFind.setInt(3, vEPICisCita.getICveModulo());

				rset = pstmtFind.executeQuery();
				while (rset.next()) {
					iConsecutivo = rset.getInt(1);
				}
				vEPICisCita.setICveCita(iConsecutivo + 1);

				cSQL = "insert into EPICisCita(" + "iCveUniMed,"
						+ "dtFecha,"
						+ "iCveCita,"
						+ "cHora,"
						+ "cApPaterno,"
						+ "cApMaterno,"
						+ "cNombre,"
						+ "cGenero,"
						+ "dtNacimiento,"
						+ "cRFC,"
						+ "cCURP,"
						+ "iCvePaisNac,"
						+ "iCveEstadoNac,"
						+ "cExpediente,"
						+ "iCvePersonal,"
						+ "cCalle,"
						+ "cNumExt,"
						+ "cNumInt,"
						+ "cColonia,"
						+ "iCP,"
						+ "cCiudad,"
						+ "iCvePais,"
						+ "iCveEstado,"
						+ "iCveMunicipio,"
						+ "cTel,"
						+ "lCambioDir,"
						+ "iCveMdoTrans,"
						+ "iCvePuesto,"
						+ "iCveCategoria,"
						+ "iCveMotivo,"
						+ "cObservacion,"
						+ "iCveSituacion,"
						+ "iCveUsuCita,"
						+ "lRealizoExam,"
						+ "iCveUsuMCIS,"
						+ "lProdNoConf,"
						+
						// Licencias
						"cLicencia,"
						+ "cLicenciaInt,"
						+ "iCveModulo"
						+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEPICisCita.getICveUniMed());
				pstmt.setDate(2, vEPICisCita.getDtFecha());
				pstmt.setInt(3, vEPICisCita.getICveCita());

				GregorianCalendar g = new GregorianCalendar(0, 0, 0,
						vEPICisCita.getIHora(), vEPICisCita.getIMinutos());
				Time t = new Time(g.getTimeInMillis());
				pstmt.setString(4, "" + t);

				pstmt.setString(5, vEPICisCita.getCApPaterno());
				pstmt.setString(6, vEPICisCita.getCApMaterno());
				pstmt.setString(7, vEPICisCita.getCNombre());
				pstmt.setString(8, vEPICisCita.getCGenero());
				pstmt.setDate(9, vEPICisCita.getDtNacimiento());
				pstmt.setString(10, vEPICisCita.getCRFC());
				pstmt.setString(11, vEPICisCita.getCCURP());
				pstmt.setInt(12, vEPICisCita.getICvePaisNac());
				pstmt.setInt(13, vEPICisCita.getICveEstadoNac());
				pstmt.setString(14, vEPICisCita.getCExpediente());
				pstmt.setInt(15, vEPICisCita.getICvePersonal());
				pstmt.setString(16, vEPICisCita.getCCalle());
				pstmt.setString(17, vEPICisCita.getCNumExt());
				pstmt.setString(18, vEPICisCita.getCNumInt());
				pstmt.setString(19, vEPICisCita.getCColonia());
				pstmt.setInt(20, vEPICisCita.getICP());
				pstmt.setString(21, vEPICisCita.getCCiudad());
				pstmt.setInt(22, vEPICisCita.getICvePais());
				pstmt.setInt(23, vEPICisCita.getICveEstado());
				pstmt.setInt(24, vEPICisCita.getICveMunicipio());
				pstmt.setString(25, vEPICisCita.getCTelefono());
				pstmt.setInt(26, vEPICisCita.getLCambioDir());
				pstmt.setInt(27, vEPICisCita.getICveMdoTrans());
				pstmt.setInt(28, vEPICisCita.getICvePuesto());

				// Validar si es de Autotrasporte
				// Si lo es se aplicara la categoria 7
				if (vEPICisCita.getICveMdoTrans() == 2) {
					int catAutotrans = 7;
					pstmt.setInt(29, catAutotrans);
				} else {
					pstmt.setInt(29, vEPICisCita.getICveCategoria());
				}

				pstmt.setInt(30, vEPICisCita.getICveMotivo());
				pstmt.setString(31, vEPICisCita.getCObservacion());
				pstmt.setInt(32, vEPICisCita.getICveSituacion());
				pstmt.setInt(33, vEPICisCita.getICveUsuCita());
				pstmt.setInt(34, vEPICisCita.getLRealizoExam());
				pstmt.setInt(35, vEPICisCita.getICveUsuMCIS());
				pstmt.setInt(36, vEPICisCita.getLProdNoConf());
				// Licencias
				pstmt.setString(37, vEPICisCita.getCLicencia());
				pstmt.setString(38, vEPICisCita.getCLicenciaInt());
				pstmt.setInt(39, vEPICisCita.getICveModulo());
				pstmt.executeUpdate();
				cClave = "" + vEPICisCita.getICveCita();
			} else {
				cClave = "ERROR";
			}

			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insert", ex1);
			}
			warn("insert", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (pstmtFind != null) {
					pstmtFind.close();
				}

				if (pstmtValida != null) {
					pstmtValida.close();
				}

				if (rset != null) {
					rset.close();
				}

				if (rsetValida != null) {
					rsetValida.close();
				}

				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

}