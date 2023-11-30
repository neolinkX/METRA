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

/**
 * <p>
 * Title: Data Acces Object de GRLMotivo DAO
 * </p>
 * <p>
 * Description: TDGRLMotivo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author
 * @version 1.0
 */

public class TDGRLMotivo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLMotivo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLMotivo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLMotivo vGRLMotivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMotivo," + "iCveProceso," + "cDscMotivo,"
					+ "lCita," + "lPago," + "lConstancia," + "lActivo"
					+ " from GRLMotivo " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLMotivo = new TVGRLMotivo();
				vGRLMotivo.setICveMotivo(rset.getInt(1));
				vGRLMotivo.setICveProceso(rset.getInt(2));
				vGRLMotivo.setCDscMotivo(rset.getString(3));
				vGRLMotivo.setLCita(rset.getInt(4));
				vGRLMotivo.setLPago(rset.getInt(5));
				vGRLMotivo.setLConstancia(rset.getInt(6));
				vGRLMotivo.setLActivo(rset.getInt(7));
				vcGRLMotivo.addElement(vGRLMotivo);
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
			return vcGRLMotivo;
		}
	}

	/**
	 * Metodo Find Dsc
	 */
	public Vector FindDsc(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLMotivo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLMotivo vGRLMotivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "GRLMotivo.iCveMotivo,"
					+ "GRLMotivo.iCveProceso,"
					+ "GRLMotivo.cDscMotivo,"
					+ "GRLMotivo.lCita,"
					+ "GRLMotivo.lPago,"
					+ "GRLMotivo.lConstancia,"
					+ "GRLMotivo.lActivo,"
					+ "GRLProceso.cDscProceso"
					+ " from GRLMotivo "
					+ " join GRLProceso on GRLProceso.iCveProceso = GRLMotivo.iCveProceso "
					+ cWhere;
			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLMotivo = new TVGRLMotivo();
				vGRLMotivo.setICveMotivo(rset.getInt(1));
				vGRLMotivo.setICveProceso(rset.getInt(2));
				vGRLMotivo.setCDscMotivo(rset.getString(3));
				vGRLMotivo.setLCita(rset.getInt(4));
				vGRLMotivo.setLPago(rset.getInt(5));
				vGRLMotivo.setLConstancia(rset.getInt(6));
				vGRLMotivo.setLActivo(rset.getInt(7));
				vGRLMotivo.setCDscProceso(rset.getString(8));
				vcGRLMotivo.addElement(vGRLMotivo);
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
			return vcGRLMotivo;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVGRLMotivo vGRLMotivo = (TVGRLMotivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLMotivo(" + "iCveMotivo," + "iCveProceso,"
					+ "cDscMotivo," + "lCita," + "lPago," + "lConstancia,"
					+ "lActivo" + ")values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveMotivo) from GRLMotivo";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			if (iMax == 0) {
				iMax = 1;
			} else {
				iMax += 1;
			}
			vGRLMotivo.setICveMotivo(iMax);
			// ******************************************************************
			pstmt.setInt(1, vGRLMotivo.getICveMotivo());
			pstmt.setInt(2, vGRLMotivo.getICveProceso());
			pstmt.setString(3, vGRLMotivo.getCDscMotivo());
			pstmt.setInt(4, vGRLMotivo.getLCita());
			pstmt.setInt(5, vGRLMotivo.getLPago());
			pstmt.setInt(6, vGRLMotivo.getLConstancia());
			pstmt.setInt(7, vGRLMotivo.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vGRLMotivo.getICveMotivo();
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
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
				}
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
			TVGRLMotivo vGRLMotivo = (TVGRLMotivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLMotivo" + " set " + "iCveProceso= ?, "
					+ "cDscMotivo= ?, " + "lCita= ?, " + "lPago= ?, "
					+ "lConstancia= ?, " + "lActivo= ? "
					+ "where iCveMotivo = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLMotivo.getICveProceso());
			pstmt.setString(2, vGRLMotivo.getCDscMotivo());
			pstmt.setInt(3, vGRLMotivo.getLCita());
			pstmt.setInt(4, vGRLMotivo.getLPago());
			pstmt.setInt(5, vGRLMotivo.getLConstancia());
			pstmt.setInt(6, vGRLMotivo.getLActivo());
			pstmt.setInt(7, vGRLMotivo.getICveMotivo());
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
			TVGRLMotivo vGRLMotivo = (TVGRLMotivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLMotivo" + " where iCveMotivo = ?"
					+ " and iCveProceso = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLMotivo.getICveMotivo());
			pstmt.setInt(2, vGRLMotivo.getICveProceso());
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
				warn("delete.closeGRLMotivo", ex2);
			}
			return cClave;
		}
	}

	public Vector getMotivos(int iCveProceso) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLMotivo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLMotivo vGRLMotivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select iCveMotivo,iCveProceso,cDscMotivo,lCita,lPago,lConstancia,"
					+ "lActivo "
					+ "from GRLMotivo where lActivo=1 and iCveProceso=? "
					+ "order by cDscMotivo";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iCveProceso);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLMotivo = new TVGRLMotivo();
				vGRLMotivo.setICveMotivo(rset.getInt("iCveMotivo"));
				vGRLMotivo.setICveProceso(rset.getInt("iCveProceso"));
				vGRLMotivo.setCDscMotivo(rset.getString("cDscMotivo"));
				vGRLMotivo.setLCita(rset.getInt("lCita"));
				vGRLMotivo.setLPago(rset.getInt("lPago"));
				vGRLMotivo.setLConstancia(rset.getInt("lConstancia"));
				vGRLMotivo.setLActivo(rset.getInt("lActivo"));
				vcGRLMotivo.addElement(vGRLMotivo);
			}
		} catch (Exception ex) {
			warn("getMotivos", ex);
			throw new DAOException("getMotivos");
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
				warn("getMotivos.close", ex2);
			}
			return vcGRLMotivo;
		}
	}

	public Vector getMotivosTerceros(int iCveProceso) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLMotivo = new Vector();
		String descartar = "not in (28,49)";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLMotivo vGRLMotivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select iCveMotivo,iCveProceso,cDscMotivo,lCita,lPago,lConstancia,"
					+ "lActivo "
					+ "from GRLMotivo where ICVEMOTIVO NOT IN ("
					+ VParametros.getPropEspecifica("MotTerceroNoActivos")
					+ ") and lActivo=1 and iCveProceso=? "
					+ "order by cDscMotivo";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iCveProceso);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLMotivo = new TVGRLMotivo();
				vGRLMotivo.setICveMotivo(rset.getInt("iCveMotivo"));
				vGRLMotivo.setICveProceso(rset.getInt("iCveProceso"));
				vGRLMotivo.setCDscMotivo(rset.getString("cDscMotivo"));
				vGRLMotivo.setLCita(rset.getInt("lCita"));
				vGRLMotivo.setLPago(rset.getInt("lPago"));
				vGRLMotivo.setLConstancia(rset.getInt("lConstancia"));
				vGRLMotivo.setLActivo(rset.getInt("lActivo"));
				vcGRLMotivo.addElement(vGRLMotivo);
			}
		} catch (Exception ex) {
			warn("getMotivos", ex);
			throw new DAOException("getMotivos");
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
				warn("getMotivos.close", ex2);
			}
			return vcGRLMotivo;
		}
	}

	/**
	 * Metodo Disable
	 */
	public Object disable(Connection conGral, Object obj) throws DAOException {
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
			TVGRLMotivo vGRLMotivo = (TVGRLMotivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLMotivo" + " set lActivo= ? "
					+ " where iCveMotivo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vGRLMotivo.getICveMotivo());
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
				warn("disable.close", ex2);
			}
			return cClave;
		}
	}
}