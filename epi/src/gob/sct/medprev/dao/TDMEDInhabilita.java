/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;
import com.micper.util.*;
import java.sql.Timestamp;

/**
 * 
 * @author AG SA
 */
public class TDMEDInhabilita extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDInhabilita() {
	}

	/**
	 * Metodo Insert
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

			TVMEDInhabilita vMEDInhabilita = (TVMEDInhabilita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int iConsecutivo = 0;
			int iExistencia = 0;
			String cSQLFind;
			String cRes;
			String cSQLValida = "";

			java.sql.Timestamp timeStampDate1 = new Timestamp(vMEDInhabilita
					.getInicioInh().getTime());

			java.sql.Timestamp timeStampDate2 = new Timestamp(vMEDInhabilita
					.getFinInh().getTime());

			cSQL = "insert into MEDInhabilita(" + "iCvePersonal,"
					+ "iCveMotivoInh," + "INICIOINH," + "FININH,"
					+ "iCveUsuInh," + "Observaciones" + ")values(?,?,?,?,?,?)";
			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vMEDInhabilita.getICvePersonal());
			pstmt.setInt(2, vMEDInhabilita.getICveMotivo());
			pstmt.setTimestamp(3, timeStampDate1);
			pstmt.setTimestamp(4, timeStampDate2);
			pstmt.setInt(5, vMEDInhabilita.getiCveUsuInh());
			pstmt.setString(6, vMEDInhabilita.getCObservacion());
			pstmt.executeUpdate();
			cClave = "" + vMEDInhabilita.getiCveUsuInh();

			// Se registra en Bitacora el movimiento
			ExpBitMod mod = new ExpBitMod();
			mod.setiCveExpediente(String.valueOf(vMEDInhabilita
					.getICvePersonal()));
			mod.setiNumExamen("0");
			mod.setOperacion("14");// de liberar dictamen
			String Nota = "Se inhabilito el expediente del periodo "
					+ timeStampDate1 + " a " + timeStampDate2;
			mod.setDescripcion(Nota);
			mod.setiCveUsuarioRealiza(String.valueOf(vMEDInhabilita
					.getiCveUsuInh()));
			mod.setlDictamen(String.valueOf(0));
			int insert = new TDEXPBITMOD().insertDictamenes(null, mod);

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

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cCondicion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		Vector vcMEDInhabilita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLPEM = "";
			String cCita = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVMEDInhabilita vMEDInhabilita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePersonal," + "iCveMotivoInh,"
					+ "InicioInh," + "FinInh," + "iCveUsuInh,"
					+ "Observaciones" + " From MEDInhabilita where "
					+ cCondicion;
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			// pstmtPEM = conn.prepareStatement(cSQLPEM);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDInhabilita = new TVMEDInhabilita();
				vMEDInhabilita.setICvePersonal(rset.getInt(1));
				vMEDInhabilita.setICveMotivo(rset.getInt(2));
				vMEDInhabilita.setInicioInh(rset.getDate(3));
				vMEDInhabilita.setFinInh(rset.getDate(4));
				vMEDInhabilita.setiCveUsuInh(rset.getInt(5));
				vMEDInhabilita.setCObservacion(rset.getString(6));
				vcMEDInhabilita.addElement(vMEDInhabilita);
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

				if (rsetPEM != null) {
					rsetPEM.close();
				}
				if (pstmtPEM != null) {
					pstmtPEM.close();
				}

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcMEDInhabilita;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllMax(String cCondicion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		Vector vcMEDInhabilita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLPEM = "";
			String cCita = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVMEDInhabilita vMEDInhabilita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select"
					+ "            M1.iCvePersonal,"
					+ "            M1.iCveMotivoInh,"
					+ "            M1.InicioInh,"
					+ "            M1.FinInh,"
					+ "            M1.iCveUsuInh,"
					+ "            M1.Observaciones"
					+ "             From MEDInhabilita AS M1 where M1.icvepersonal = "
					+ cCondicion + " AND"
					+ "	    M1.InicioInh = (SELECT MAX(INICIOINH) "
					+ "						FROM MEDINHABILITA AS M2 "
					+ "						WHERE M2.ICVEPERSONAL = M1.ICVEPERSONAL )";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			// pstmtPEM = conn.prepareStatement(cSQLPEM);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDInhabilita = new TVMEDInhabilita();
				vMEDInhabilita.setICvePersonal(rset.getInt(1));
				vMEDInhabilita.setICveMotivo(rset.getInt(2));
				vMEDInhabilita.setInicioInh(rset.getDate(3));
				vMEDInhabilita.setFinInh(rset.getDate(4));
				vMEDInhabilita.setiCveUsuInh(rset.getInt(5));
				vMEDInhabilita.setCObservacion(rset.getString(6));
				vcMEDInhabilita.addElement(vMEDInhabilita);
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

				if (rsetPEM != null) {
					rsetPEM.close();
				}
				if (pstmtPEM != null) {
					pstmtPEM.close();
				}

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcMEDInhabilita;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Boolean Inhabilitado(String cCondicion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		Vector vcMEDInhabilita = new Vector();
		Boolean inhabilitado = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLPEM = "";
			String cCita = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVMEDInhabilita vMEDInhabilita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT M1.ICVEPERSONAL, M1.INICIOINH, M1.FININH FROM MEDINHABILITA AS M1"
					+ " WHERE"
					+ " M1.INICIOINH = (SELECT MAX(INICIOINH) FROM MEDINHABILITA AS M2 "
					+ "				  WHERE M2.ICVEPERSONAL = M1.ICVEPERSONAL ) AND"
					+ " M1.FININH >= (SELECT current date FROM sysibm.sysdummy1) AND"
					+ " M1.INICIOINH <= (SELECT current date FROM sysibm.sysdummy1) AND"
					+ "  " + cCondicion;
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			// pstmtPEM = conn.prepareStatement(cSQLPEM);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				inhabilitado = true;
			}
			// System.out.println(inhabilitado);
		} catch (Exception ex) {
			warn("Inhabilitado", ex);
			throw new DAOException("Inhabilitado");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (rsetPEM != null) {
					rsetPEM.close();
				}
				if (pstmtPEM != null) {
					pstmtPEM.close();
				}

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("Inhabilitado.close", ex2);
			}
			return inhabilitado;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll2(String cCondicion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		Vector vcMEDInhabilita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLPEM = "";
			String cCita = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVMEDInhabilita vMEDInhabilita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select         M.iCvePersonal,"
					+ "            M.iCveMotivoInh,"
					+ "            M.InicioInh,"
					+ "            M.FinInh,"
					+ "            M.iCveUsuInh,"
					+ "            M.Observaciones,"
					+ "    		   U.CNOMBRE || ' ' || U.CAPPATERNO || ' ' || U.CAPMATERNO AS MEDICO, " 
					+ "            T.CDSCMOTIVO "
					+ "   From MEDInhabilita AS M, SEGUSUARIO AS U, GRLMOTIVO AS T "
					+ "   where M.ICVEUSUINH = U.ICVEUSUARIO AND "
					+ "   M.icvepersonal = " + cCondicion +" AND " 
					+ "   M.ICVEMOTIVOINH = T.ICVEMOTIVO";
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			// pstmtPEM = conn.prepareStatement(cSQLPEM);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDInhabilita = new TVMEDInhabilita();
				vMEDInhabilita.setICvePersonal(rset.getInt(1));
				vMEDInhabilita.setICveMotivo(rset.getInt(2));
				vMEDInhabilita.setInicioInh(rset.getDate(3));
				vMEDInhabilita.setFinInh(rset.getDate(4));
				vMEDInhabilita.setiCveUsuInh(rset.getInt(5));
				vMEDInhabilita.setCObservacion(rset.getString(6));
				vMEDInhabilita.setCMedico(rset.getString(7));
				vMEDInhabilita.setCDscMotivo(rset.getString(8));
				vcMEDInhabilita.addElement(vMEDInhabilita);
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

				if (rsetPEM != null) {
					rsetPEM.close();
				}
				if (pstmtPEM != null) {
					pstmtPEM.close();
				}

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcMEDInhabilita;
		}
	}

	public Object updateHabilita(Connection conGral, Object obj)
			throws DAOException {
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

			TVMEDInhabilita vMEDInhabilita = (TVMEDInhabilita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "Update MEDINHABILITA set FININH = (CURRENT_DATE-1 DAY) where icvepersonal = ? and FININH >= CURRENT_DATE";
			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vMEDInhabilita.getICvePersonal());
			pstmt.executeUpdate();
			cClave = "" + vMEDInhabilita.getiCveUsuInh();
			// Se registra en Bitacora el movimiento
			ExpBitMod mod = new ExpBitMod();
			mod.setiCveExpediente(String.valueOf(vMEDInhabilita
					.getICvePersonal()));
			mod.setiNumExamen("0");
			mod.setOperacion("15");// de liberar dictamen
			String Nota = "El expediente fue habilitado";
			mod.setDescripcion(Nota);
			mod.setiCveUsuarioRealiza(String.valueOf(vMEDInhabilita
					.getiCveUsuInh()));
			mod.setlDictamen(String.valueOf(0));
			int insert = new TDEXPBITMOD().insertDictamenes(null, mod);

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
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	public Object delete(Connection conGral, Object obj) throws DAOException {
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

			TVMEDInhabilita vMEDInhabilita = (TVMEDInhabilita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "DELETE FROM MEDINHABILITA where icvepersonal = ? and FININH >= CURRENT_DATE";
			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vMEDInhabilita.getICvePersonal());
			pstmt.executeUpdate();
			cClave = "" + vMEDInhabilita.getiCveUsuInh();

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
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	public Vector FindInhabilitaByAll(String cCondicion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		Vector vcMEDInhabilita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLPEM = "";
			String cCita = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVMEDInhabilita vMEDInhabilita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePersonal," + "iCveMotivoInh,"
					+ "InicioInh," + "FinInh," + "iCveUsuInh,"
					+ "Observaciones" + " From MEDInhabilita where "
					+ cCondicion;
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			// pstmtPEM = conn.prepareStatement(cSQLPEM);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDInhabilita = new TVMEDInhabilita();
				vMEDInhabilita.setICvePersonal(rset.getInt(1));
				vMEDInhabilita.setICveMotivo(rset.getInt(2));
				vMEDInhabilita.setInicioInh(rset.getDate(3));
				vMEDInhabilita.setFinInh(rset.getDate(4));
				vMEDInhabilita.setiCveUsuInh(rset.getInt(5));
				vMEDInhabilita.setCObservacion(rset.getString(6));
				vcMEDInhabilita.addElement(vMEDInhabilita);
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

				if (rsetPEM != null) {
					rsetPEM.close();
				}
				if (pstmtPEM != null) {
					pstmtPEM.close();
				}

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			Vector inhabilita = this.FindByAll(cCondicion);
			for (int i = 0; i < inhabilita.size(); i++) {
				TVMEDInhabilita verifica = (TVMEDInhabilita) inhabilita.get(i);
				if (verifica.getFinInh().after(new Date())) {
					return vcMEDInhabilita;
				}
			}
			return new Vector();
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insertIpMacName(Connection conGral, Object obj, ExpBitMod bit)
			throws DAOException {
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

			TVMEDInhabilita vMEDInhabilita = (TVMEDInhabilita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int iConsecutivo = 0;
			int iExistencia = 0;
			String cSQLFind;
			String cRes;
			String cSQLValida = "";

			java.sql.Timestamp timeStampDate1 = new Timestamp(vMEDInhabilita
					.getInicioInh().getTime());

			java.sql.Timestamp timeStampDate2 = new Timestamp(vMEDInhabilita
					.getFinInh().getTime());

			cSQL = "insert into MEDInhabilita(" + "iCvePersonal,"
					+ "iCveMotivoInh," + "INICIOINH," + "FININH,"
					+ "iCveUsuInh," + "Observaciones" + ")values(?,?,?,?,?,?)";
			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vMEDInhabilita.getICvePersonal());
			pstmt.setInt(2, vMEDInhabilita.getICveMotivo());
			pstmt.setTimestamp(3, timeStampDate1);
			pstmt.setTimestamp(4, timeStampDate2);
			pstmt.setInt(5, vMEDInhabilita.getiCveUsuInh());
			pstmt.setString(6, vMEDInhabilita.getCObservacion());
			pstmt.executeUpdate();
			cClave = "" + vMEDInhabilita.getiCveUsuInh();

			// Se registra en Bitacora el movimiento
			ExpBitMod mod = new ExpBitMod();
			mod.setiCveExpediente(String.valueOf(vMEDInhabilita
					.getICvePersonal()));
			mod.setiNumExamen("0");
			mod.setOperacion("14");// de liberar dictamen
			String Nota = "Se inhabilito el expediente del periodo "
					+ timeStampDate1 + " a " + timeStampDate2;
			mod.setDescripcion(Nota);
			mod.setiCveUsuarioRealiza(String.valueOf(vMEDInhabilita
					.getiCveUsuInh()));
			mod.setlDictamen(String.valueOf(0));
			mod.setIpAddress(bit.getIpAddress());
			mod.setMacAddress(bit.getMacAddress());
			mod.setComputerName(bit.getComputerName());
			int insert = new TDEXPBITMOD().insertDictamenesIpMacName(null, mod);

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

	public Object updateHabilitaIpMacName(Connection conGral, Object obj,
			ExpBitMod bit) throws DAOException {
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

			TVMEDInhabilita vMEDInhabilita = (TVMEDInhabilita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "Update MEDINHABILITA set FININH = (CURRENT_DATE-1 DAY) where icvepersonal = ? and FININH >= CURRENT_DATE";
			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vMEDInhabilita.getICvePersonal());
			pstmt.executeUpdate();
			cClave = "" + vMEDInhabilita.getiCveUsuInh();
			// Se registra en Bitacora el movimiento
			ExpBitMod mod = new ExpBitMod();
			mod.setiCveExpediente(String.valueOf(vMEDInhabilita
					.getICvePersonal()));
			mod.setiNumExamen("0");
			mod.setOperacion("15");// de liberar dictamen
			String Nota = "El expediente fue habilitado";
			mod.setDescripcion(Nota);
			mod.setiCveUsuarioRealiza(String.valueOf(vMEDInhabilita
					.getiCveUsuInh()));
			mod.setlDictamen(String.valueOf(0));
			mod.setIpAddress(bit.getIpAddress());
			mod.setMacAddress(bit.getMacAddress());
			mod.setComputerName(bit.getComputerName());
			int insert = new TDEXPBITMOD().insertDictamenesIpMacName(null, mod);

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
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

}
