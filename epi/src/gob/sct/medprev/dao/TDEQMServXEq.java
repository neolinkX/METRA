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
 * Title: Data Acces Object de EQMServXEq DAO
 * </p>
 * <p>
 * Description: DAO Para la Tabla EQMServXEq
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */

public class TDEQMServXEq extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEQMServXEq() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String sWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMServXEq = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMServXEq vEQMServXEq;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEquipo," + "iCveAsignacion,"
					+ "iCveServicio" + " from EQMServXEq " + sWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMServXEq = new TVEQMServXEq();
				vEQMServXEq.setICveEquipo(rset.getInt(1));
				vEQMServXEq.setICveAsignacion(rset.getInt(2));
				vEQMServXEq.setICveServicio(rset.getInt(3));
				vcEQMServXEq.addElement(vEQMServXEq);
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
			return vcEQMServXEq;
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
			TVEQMServXEq vEQMServXEq = (TVEQMServXEq) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EQMServXEq(" + "iCveEquipo,"
					+ "iCveAsignacion," + "iCveServicio" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vEQMServXEq.getICveEquipo());
			pstmt.setInt(2, vEQMServXEq.getICveAsignacion());
			pstmt.setInt(3, vEQMServXEq.getICveServicio());
			pstmt.executeUpdate();
			cClave = "" + vEQMServXEq.getICveEquipo();
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
			TVEQMServXEq vEQMServXEq = (TVEQMServXEq) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMServXEq" + "where iCveEQuipo = ? "
					+ "and iCveAsignacion = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMServXEq.getICveEquipo());
			pstmt.setInt(2, vEQMServXEq.getICveAsignacion());
			pstmt.setInt(3, vEQMServXEq.getICveServicio());
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
			TVEQMServXEq vEQMServXEq = (TVEQMServXEq) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EQMServXEq" + " where iCveEQuipo = ?"
					+ " and iCveAsignacion = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMServXEq.getICveEquipo());
			pstmt.setInt(2, vEQMServXEq.getICveAsignacion());
			pstmt.setInt(3, vEQMServXEq.getICveServicio());
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
				warn("delete.closeEQMServXEq", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All Servicios x Equipo
	 */
	public Vector FindByAllServXEq(String sWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMServXEq = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDServicio vEQMServXEq;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select EqmServxEq.iCveServicio, MedServicio.cDscServicio, "
					+ "EqmAsignacion.lActual "
					+ "from EqmServXEq "
					+ "join EqmAsignacion on EqmAsignacion.iCveEquipo=EqmServXEq.iCveEquipo "
					+ "and EqmAsignacion.iCveAsignacion=EqmServXEq.iCveAsignacion "
					+ "join MedServicio on MedServicio.iCveServicio=EqmServXEq.iCveServicio "
					+ sWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMServXEq = new TVMEDServicio();
				vEQMServXEq.setICveServicio(rset.getInt(1));
				vEQMServXEq.setCDscServicio(rset.getString(2));
				vEQMServXEq.setLActivo(rset.getInt(3));
				vEQMServXEq.setLDiagnostico(1);
				vcEQMServXEq.addElement(vEQMServXEq);
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
			return vcEQMServXEq;
		}
	}

	/**
	 * Metodo Para el Listado y Actualizaci�n de los Servicios por Equipo
	 */
	public Object updateServiciosxEq(Connection conGral, Vector vServ)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement psdel = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			for (int i = 0; i < vServ.size(); i++) {
				String cSQLIns = "";
				String cSQLDel = "";
				TVEQMServXEq vEQMServXEq = (TVEQMServXEq) vServ.get(i);
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
				cSQLIns = "insert into EQMServXEq(" + "iCveEquipo,"
						+ "iCveAsignacion," + "iCveServicio" + ")values(?,?,?)";
				cSQLDel = "delete from EQMServXEq" + " where iCveEQuipo = ?"
						+ " and iCveAsignacion = ?" + " and iCveServicio = ?";
				pstmt = conn.prepareStatement(cSQLIns);
				psdel = conn.prepareStatement(cSQLDel);
				pstmt.setInt(1, vEQMServXEq.getICveEquipo());
				pstmt.setInt(2, vEQMServXEq.getICveAsignacion());
				pstmt.setInt(3, vEQMServXEq.getICveServicio());
				psdel.setInt(1, vEQMServXEq.getICveEquipo());
				psdel.setInt(2, vEQMServXEq.getICveAsignacion());
				psdel.setInt(3, vEQMServXEq.getICveServicio());
				if (vEQMServXEq.getLActual() == 1)
					pstmt.executeUpdate();
				else if (vEQMServXEq.getLActual() == 0)
					psdel.executeUpdate();
				cClave = "" + vEQMServXEq.getICveEquipo();
				psdel.close();
				pstmt.close();
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