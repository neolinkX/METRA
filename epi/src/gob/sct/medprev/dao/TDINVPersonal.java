package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;
import javax.swing.*;

/**
 * <p>
 * Title: Data Acces Object de INVPersonal DAO
 * </p>
 * <p>
 * Description: DAO Tabla INVPersonal
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonz�lez Paz
 * @version 1.0
 */

public class TDINVPersonal extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	// JEditorPane jEditorPane1 = new JEditorPane();

	public TDINVPersonal() {
		/*
		 * try { jbInit(); } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	/**
	 * TDINVPersonal
	 * 
	 * @param lBandera
	 *            boolean
	 */
	public TDINVPersonal(boolean lBandera) {

	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVPersonal = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVPersonal vINVPersonal;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveMdoTrans," + "iIDDGPMPT,"
					+ "iCveVehiculo," + "iCveInvPers," + "iCvePuesto,"
					+ "dtVigencia," + "iCveSituacion," + "iCvePersonal,"
					+ "iNumExamen," + "lSinLicencia," + "cLicencia"
					+ " from INVPersonal order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVPersonal = new TVINVPersonal();
				vINVPersonal.setIAnio(rset.getInt(1));
				vINVPersonal.setICveMdoTrans(rset.getInt(2));
				vINVPersonal.setIIDDGPMPT(rset.getInt(3));
				vINVPersonal.setICveVehiculo(rset.getInt(4));
				vINVPersonal.setICveInvPers(rset.getInt(5));
				vINVPersonal.setICvePuesto(rset.getInt(6));
				vINVPersonal.setDtVigencia(rset.getDate(7));
				vINVPersonal.setICveSituacion(rset.getInt(8));
				vINVPersonal.setICvePersonal(rset.getInt(9));
				vINVPersonal.setINumExamen(rset.getInt(10));
				vINVPersonal.setLSinLicencia(rset.getInt(11));
				vINVPersonal.setCLicencia(rset.getString(12));
				vcINVPersonal.addElement(vINVPersonal);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcINVPersonal;
		}
	}

	/**
	 * Metodo FindAccidentes
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Vector FindAccidentes(String cPersonal, String cProceso)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVPersonal = new Vector();
		TFechas dtFecha = new TFechas();
		String cFecha = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVPersonal vINVPersonal;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "       INVPersonal.iAnio, "
					+ "       INVPersonal.iCveMdoTrans, "
					+ "       INVPersonal.iIDDGPMPT, "
					+ "       INVPersonal.iCveVehiculo, "
					+ "       INVPersonal.iCveInvPers, "
					+ "       INVPersonal.iCvePuesto, "
					+ "       INVPersonal.dtVigencia, "
					+ "       INVPersonal.iCveSituacion, "
					+ "       INVPersonal.iCvePersonal, "
					+ "       INVPersonal.iNumExamINV, "
					+ "       INVPersonal.lSinLicencia, "
					+ "       INVPersonal.cLicencia, "
					+ "       GRLUniMed.cDscUniMed, "
					+ "       GRLProceso.cDscProceso, "
					+ "       EXPExamAplica.dtAplicacion, "
					+ "       EXPExamAplica.dtConcluido, "
					+ "       GRLMdoTrans.cDscMdoTrans, "
					+ "       EXPExamCat.iCveCategoria, "
					+ "       EXPExamCat.lDictamen, C.cDscCategoria "
					+ "from INVPersonal "
					+ "inner join EXPExamAplica on EXPExamAplica.iCvePersonal = INVPersonal.iCvePersonal "
					+ "                        and EXPExamAplica.iNumExamen   = INVPersonal.iNumExamINV "
					+ "                        and iCveProceso = "
					+ cProceso
					+ " "
					+
					// "and lConcluido = 1 " +
					"inner join GRLUniMed on GRLUniMed.iCveUniMed = EXPExamAplica.iCveUniMed "
					+ "inner join GRLProceso on GRLProceso.iCveProceso = EXPExamAplica.iCveProceso "
					+ "inner join GRLMdoTrans on GRLMdoTrans.iCveMdoTrans = INVPersonal.iCveMdoTrans "
					+ "inner join EXPExamCat on EXPExamCat.iCveExpediente = EXPExamAplica.iCveExpediente "
					+ "                     and EXPExamCat.iNumExamen = EXPExamAplica.iNumExamen "
					+ "                     and EXPExamCat.iCveMdoTrans = INVPersonal.iCveMdoTrans "
					+ "inner join GRLCategoria C on C.iCveMdoTrans = EXPExamCat.iCveMdoTrans "
					+ "                         and C.iCveCategoria = EXPExamCat.iCveCategoria "
					+ "where INVPersonal.iCvePersonal = " + cPersonal;

			// System.out.println("SQL: " + cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVPersonal = new TVINVPersonal();
				vINVPersonal.setIAnio(rset.getInt(1));
				vINVPersonal.setICveMdoTrans(rset.getInt(2));
				vINVPersonal.setIIDDGPMPT(rset.getInt(3));
				vINVPersonal.setICveVehiculo(rset.getInt(4));
				vINVPersonal.setICveInvPers(rset.getInt(5));
				vINVPersonal.setICvePuesto(rset.getInt(6));
				vINVPersonal.setDtVigencia(rset.getDate(7));
				vINVPersonal.setICveSituacion(rset.getInt(8));
				vINVPersonal.setICvePersonal(rset.getInt(9));
				vINVPersonal.setINumExamINV(rset.getInt(10));
				vINVPersonal.setLSinLicencia(rset.getInt(11));
				vINVPersonal.setCLicencia(rset.getString(12));
				vINVPersonal.setCDscUniMed(rset.getString(13));
				vINVPersonal.setCDscProceso(rset.getString(14));
				vINVPersonal.setDtAplicacion(rset.getDate(15));
				vINVPersonal.setDtConcluido(rset.getDate(16));
				vINVPersonal.setCDscMdoTrans(rset.getString(17));
				vINVPersonal.setICveCategoria(rset.getInt(18));
				vINVPersonal.setLDictamen(rset.getInt(19));
				vINVPersonal.setCDscPuesto(rset.getString(20));

				if (vINVPersonal.getDtAplicacion() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vINVPersonal.getDtAplicacion(), "/");
					vINVPersonal.setCDscDtAplicacion(cFecha);
				} else {
					vINVPersonal.setCDscDtAplicacion("");
				}

				if (vINVPersonal.getDtConcluido() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vINVPersonal.getDtConcluido(), "/");
					vINVPersonal.setCDscDtConcluido(cFecha);
				} else {
					vINVPersonal.setCDscDtConcluido("");
				}

				vcINVPersonal.addElement(vINVPersonal);
			}
		} catch (Exception ex) {
			warn("FindAccidentes", ex);
			throw new DAOException("FindAccidentes");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindAccidentes.close", ex2);
			}
			return vcINVPersonal;
		}
	}

	/**
	 * Metodo FindPersonal
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Vector FindPersonal(String cAnio, String cMdoTrans,
			String cIdentifica) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVPersonal = new Vector();
		String cFecha;
		TFechas dtFecha = new TFechas();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVPersonal vINVPersonal;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ " INVPersonal.iAnio,"
					+ " INVPersonal.iCveMdoTrans,"
					+ " INVPersonal.iIDDGPMPT,"
					+ " INVPersonal.iCveVehiculo,"
					+ " INVPersonal.iCveInvPers,"
					+ " INVPersonal.iCvePuesto,"
					+ " INVPersonal.dtVigencia,"
					+ " INVPersonal.iCveSituacion,"
					+ " INVPersonal.iCvePersonal,"
					+ " INVPersonal.iNumExamen,"
					+ " INVPersonal.lSinLicencia,"
					+ " INVPersonal.cLicencia,"
					+ " GRLPuesto.cDscPuesto, "
					+ " INVSituacion.cDscSituacion, "
					+ " INVRegVehic.cMatricula, "
					+ " PERDatos.cNombre, "
					+ " PERDatos.cApPaterno, "
					+ " PERDatos.cApMaterno, "
					+ " PERDatos.iCveExpediente, "
					+ " INVPersonal.iNumExamINV "
					+ " from INVPersonal "
					+ " inner join GRLPuesto on GRLPuesto.iCveMdoTrans = INVPersonal.iCveMdoTrans "
					+ "                     and GRLPuesto.iCvePuesto   = INVPersonal.iCvePuesto"
					+ " inner join INVSituacion on INVSituacion.iCveSituacion = INVPersonal.iCveSituacion "
					+ " inner join INVRegVehic on INVRegVehic.iAnio = INVPersonal.iAnio "
					+ "                       and INVRegVehic.iCveMdoTrans = INVPersonal.iCveMdoTrans "
					+ "                       and INVRegVehic.iIDDGPMPT = INVPersonal.iIDDGPMPT "
					+ "                       and INVRegVehic.iCveVehiculo = INVPersonal.iCveVehiculo "
					+ " inner join PERDatos on PERDatos.iCvePersonal = INVPersonal.iCvePersonal "
					+ " where INVPersonal.iAnio        = " + cAnio
					+ "   and INVPersonal.iCveMdoTrans = " + cMdoTrans
					+ "   and INVPersonal.iIDDGPMPT    = " + cIdentifica
					+ " order by INVPersonal.iCveInvPers";
			debug(cSQL);
			// System.out.println("Buscar personal \n" + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVPersonal = new TVINVPersonal();
				vINVPersonal.setIAnio(rset.getInt(1));
				vINVPersonal.setICveMdoTrans(rset.getInt(2));
				vINVPersonal.setIIDDGPMPT(rset.getInt(3));
				vINVPersonal.setICveVehiculo(rset.getInt(4));
				vINVPersonal.setICveInvPers(rset.getInt(5));
				vINVPersonal.setICvePuesto(rset.getInt(6));
				vINVPersonal.setDtVigencia(rset.getDate(7));
				vINVPersonal.setICveSituacion(rset.getInt(8));
				vINVPersonal.setICvePersonal(rset.getInt(9));
				vINVPersonal.setINumExamen(rset.getInt(10));
				vINVPersonal.setLSinLicencia(rset.getInt(11));
				vINVPersonal.setCLicencia(rset.getString(12));
				vINVPersonal.setCDscPuesto(rset.getString(13));
				vINVPersonal.setCDscSituacion(rset.getString(14));
				vINVPersonal.setCDscVehiculo(rset.getString(15));
				vINVPersonal.setCNombreCompleto(rset.getString(16) + " "
						+ rset.getString(17) + " " + rset.getString(18));
				vINVPersonal.setICveExpediente(rset.getInt(19));
				vINVPersonal.setINumExamINV(rset.getInt(20));

				if (vINVPersonal.getDtVigencia() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vINVPersonal.getDtVigencia(), "/");
					vINVPersonal.setCDscDtVigencia(cFecha);
				} else {
					vINVPersonal.setCDscDtVigencia("");
				}

				vcINVPersonal.addElement(vINVPersonal);
			}
		} catch (Exception ex) {
			warn("FindPersonal", ex);
			throw new DAOException("FindPersonal");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindPersonal.close", ex2);
			}
			return vcINVPersonal;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVINVPersonal vINVPersonal = (TVINVPersonal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into INVPersonal(" + "iAnio," + "iCveMdoTrans,"
					+ "iIDDGPMPT," + "iCveVehiculo," + "iCveInvPers,"
					+ "iCvePuesto," + "dtVigencia," + "iCveSituacion,"
					+ "iCvePersonal," + "iNumExamen," + "lSinLicencia,"
					+ "cLicencia" + ")values(?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			// System.out.println("Insert... A�o " + vINVPersonal.getIAnio());
			// System.out.println("Insert... MdoTrans " +
			// vINVPersonal.getICveMdoTrans());
			// System.out.println("Insert... IIDDGPMPT " +
			// vINVPersonal.getIIDDGPMPT());
			// System.out.println("Insert... Vehiculo " +
			// vINVPersonal.getICveVehiculo());
			// System.out.println("Insert... iCveINVPers " +
			// vINVPersonal.getICveInvPers());
			pstmt.setInt(1, vINVPersonal.getIAnio());
			pstmt.setInt(2, vINVPersonal.getICveMdoTrans());
			pstmt.setInt(3, vINVPersonal.getIIDDGPMPT());
			pstmt.setInt(4, vINVPersonal.getICveVehiculo());
			pstmt.setInt(5, vINVPersonal.getICveInvPers());
			pstmt.setInt(6, vINVPersonal.getICvePuesto());
			pstmt.setDate(7, vINVPersonal.getDtVigencia());
			pstmt.setInt(8, vINVPersonal.getICveSituacion());
			pstmt.setInt(9, vINVPersonal.getICvePersonal());
			pstmt.setInt(10, vINVPersonal.getINumExamen());
			pstmt.setInt(11, vINVPersonal.getLSinLicencia());
			pstmt.setString(12, vINVPersonal.getCLicencia());
			pstmt.executeUpdate();
			cClave = "" + vINVPersonal.getIAnio();
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

	/**
	 * Metodo update
	 */
	public Object update(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVINVPersonal vINVPersonal = (TVINVPersonal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update INVPersonal" + " set iCvePuesto= ?, "
					+ "dtVigencia= ?, " + "iCveSituacion= ?, "
					+ "iCvePersonal= ?, " + "iNumExamen= ?, "
					+ "lSinLicencia= ?, " + "cLicencia= ? "
					+ "where iAnio = ? " + "and iCveMdoTrans = ?"
					+ "and iIDDGPMPT = ?" + "and iCveVehiculo = ?"
					+ " and iCveInvPers = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVPersonal.getICvePuesto());
			pstmt.setDate(2, vINVPersonal.getDtVigencia());
			pstmt.setInt(3, vINVPersonal.getICveSituacion());
			pstmt.setInt(4, vINVPersonal.getICvePersonal());
			pstmt.setInt(5, vINVPersonal.getINumExamen());
			pstmt.setInt(6, vINVPersonal.getLSinLicencia());
			pstmt.setString(7, vINVPersonal.getCLicencia());
			pstmt.setInt(8, vINVPersonal.getIAnio());
			pstmt.setInt(9, vINVPersonal.getICveMdoTrans());
			pstmt.setInt(10, vINVPersonal.getIIDDGPMPT());
			pstmt.setInt(11, vINVPersonal.getICveVehiculo());
			pstmt.setInt(12, vINVPersonal.getICveInvPers());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("update", ex1);
			}
			warn("update", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo updateExamen
	 */
	public Object updateExamen(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVINVPersonal vINVPersonal = (TVINVPersonal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update INVPersonal" + " set iNumExamINV= ? "
					+ "where iAnio = ? " + "and iCveMdoTrans = ? "
					+ "and iIDDGPMPT = ? " + "and iCveVehiculo = ? "
					+ "and iCveInvPers = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVPersonal.getINumExamINV());
			pstmt.setInt(2, vINVPersonal.getIAnio());
			pstmt.setInt(3, vINVPersonal.getICveMdoTrans());
			pstmt.setInt(4, vINVPersonal.getIIDDGPMPT());
			pstmt.setInt(5, vINVPersonal.getICveVehiculo());
			pstmt.setInt(6, vINVPersonal.getICveInvPers());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("updateExamen", ex1);
			}
			warn("updateExamen", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("updateExamen.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Delete
	 */
	public Object delete(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int iEntidades = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVINVPersonal vINVPersonal = (TVINVPersonal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from INVPersonal" + " where iAnio = ?"
					+ " and iCveMdoTrans = ?" + " and iIDDGPMPT = ?"
					+ " and iCveVehiculo = ?" + " and iCveInvPers = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVPersonal.getIAnio());
			pstmt.setInt(2, vINVPersonal.getICveMdoTrans());
			pstmt.setInt(3, vINVPersonal.getIIDDGPMPT());
			pstmt.setInt(4, vINVPersonal.getICveVehiculo());
			pstmt.setInt(5, vINVPersonal.getICveInvPers());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("delete", ex1);
			}
			warn("delete", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("delete.closeINVPersonal", ex2);
			}
			return cClave;
		}
	}

	/*
	 * private void jbInit() throws Exception {
	 * jEditorPane1.setText("jEditorPane1"); }
	 */
}



