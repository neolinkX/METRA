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
 * Title: Data Acces Object de MEDRama DAO
 * </p>
 * <p>
 * Description: DAO MED RAMA
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

public class TDMEDRama extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDRama() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDRama vMEDRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveServicio," + "iCveRama," + "cDscRama,"
					+ "cObservacion," + "lEstudio," + "iOrden," + "lActivo"
					+ " from MEDRama " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDRama = new TVMEDRama();
				vMEDRama.setICveServicio(rset.getInt(1));
				vMEDRama.setICveRama(rset.getInt(2));
				vMEDRama.setCDscRama(rset.getString(3));
				vMEDRama.setCObservacion(rset.getString(4));
				vMEDRama.setLEstudio(rset.getInt(5));
				vMEDRama.setIOrden(rset.getInt(6));
				vMEDRama.setLActivo(rset.getInt(7));
				vcMEDRama.addElement(vMEDRama);
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
			return vcMEDRama;
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
			TVMEDRama vMEDRama = (TVMEDRama) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDRama(" + "iCveServicio," + "iCveRama,"
					+ "cDscRama," + "cObservacion," + "lEstudio," + "iOrden,"
					+ "lActivo" + ") values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String q = "SELECT MAX(ICVERAMA) FROM MEDRAMA WHERE ICVESERVICIO=?";
			PreparedStatement psNewCve = conn.prepareStatement(q);
			psNewCve.setInt(1, vMEDRama.getICveServicio());
			int iNewCve = 0;
			ResultSet rsNewCve = psNewCve.executeQuery();
			while (rsNewCve.next()) {
				iNewCve = rsNewCve.getInt(1) + 1;
			}
			rsNewCve.close();
			psNewCve.close();

			pstmt.setInt(1, vMEDRama.getICveServicio());
			pstmt.setInt(2, iNewCve);
			pstmt.setString(3, vMEDRama.getCDscRama());
			pstmt.setString(4, vMEDRama.getCObservacion());
			pstmt.setInt(5, vMEDRama.getLEstudio());
			pstmt.setInt(6, vMEDRama.getIOrden());
			pstmt.setInt(7, vMEDRama.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vMEDRama.getICveServicio();
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
			TVMEDRama vMEDRama = (TVMEDRama) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDRama" + " set cObservacion= ?, "
					+ "lEstudio= ?, " + "cDscRama= ?, " + "iOrden= ?, "
					+ "lActivo= ? " + "where iCveServicio = ? "
					+ " and iCveRama = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vMEDRama.getCObservacion());
			// System.out.println("--->getCObservacion" +
			// vMEDRama.getCObservacion());
			pstmt.setInt(2, vMEDRama.getLEstudio());
			pstmt.setString(3, vMEDRama.getCDscRama());
			pstmt.setInt(4, vMEDRama.getIOrden());
			pstmt.setInt(5, vMEDRama.getLActivo());
			pstmt.setInt(6, vMEDRama.getICveServicio());
			pstmt.setInt(7, vMEDRama.getICveRama());
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
			TVMEDRama vMEDRama = (TVMEDRama) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDRama" + " where iCveServicio = ?"
					+ " and iCveRama = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDRama.getICveServicio());
			pstmt.setInt(2, vMEDRama.getICveRama());
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
				warn("delete.closeMEDRama", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All para Catalogo de Ramas
	 */
	public Vector FindByAllCatalogoRamas(String cCondicion, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDRama vMEDRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select R.iCveServicio, S.cDscServicio, R.iCveRama, R.cDscRama, R.cObservacion, "
					+ "R.lEstudio, R.iOrden, R.lActivo "
					+ "from MedServicio S, MedRama R "
					+ "where S.iCveServicio=R.iCveServicio "
					+ cCondicion
					+ cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDRama = new TVMEDRama();
				vMEDRama.setICveServicio(rset.getInt(1));
				vMEDRama.setCDscServicio(rset.getString(2));
				vMEDRama.setICveRama(rset.getInt(3));
				vMEDRama.setCDscRama(rset.getString(4));
				vMEDRama.setCObservacion(rset.getString(5));
				vMEDRama.setLEstudio(rset.getInt(6));
				vMEDRama.setIOrden(rset.getInt(7));
				vMEDRama.setLActivo(rset.getInt(8));
				vcMEDRama.addElement(vMEDRama);
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
			return vcMEDRama;
		}
	}

	/**
	 * Metodo update Orden de las Ramas
	 */
	public Object updateOrdenRamas(Connection conGral, Vector ramas)
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

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDRama" + " set iOrden= ? "
					+ "where iCveServicio = ? " + " and iCveRama = ?";
			pstmt = conn.prepareStatement(cSQL);
			for (int i = 0; i < ramas.size(); i++) {
				TVMEDRama vMEDRama = (TVMEDRama) ramas.get(i);
				pstmt.setInt(1, vMEDRama.getIOrden());
				pstmt.setInt(2, vMEDRama.getICveServicio());
				pstmt.setInt(3, vMEDRama.getICveRama());
				pstmt.executeUpdate();
			}
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
	 * Metodo Insert
	 */
	public Object insertRamaCatalogo(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iNewCve = 0;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVMEDRama vMEDRama = (TVMEDRama) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDRama(" + "iCveServicio," + "iCveRama,"
					+ "cDscRama," + "cObservacion," + "lEstudio," + "iOrden,"
					+ "lActivo" + ") values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String q = "SELECT MAX(ICVERAMA) FROM MEDRAMA WHERE ICVESERVICIO=?";
			PreparedStatement psNewCve = conn.prepareStatement(q);
			psNewCve.setInt(1, vMEDRama.getICveServicio());
			ResultSet rsNewCve = psNewCve.executeQuery();
			while (rsNewCve.next()) {
				iNewCve = rsNewCve.getInt(1) + 1;
			}
			rsNewCve.close();
			psNewCve.close();

			pstmt.setInt(1, vMEDRama.getICveServicio());
			pstmt.setInt(2, iNewCve);
			pstmt.setString(3, vMEDRama.getCDscRama());
			pstmt.setString(4, vMEDRama.getCObservacion());
			pstmt.setInt(5, vMEDRama.getLEstudio());
			pstmt.setInt(6, iNewCve);
			pstmt.setInt(7, vMEDRama.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + iNewCve;
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
	 * Metodo disable de las Ramas
	 */
	public Object disableRamas(Connection conGral, Object obj)
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

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDRama" + " set lActivo= ?, " + "iOrden= ? "
					+ "where iCveServicio = ? " + " and iCveRama = ?";
			pstmt = conn.prepareStatement(cSQL);
			TVMEDRama vMEDRama = (TVMEDRama) obj;
			pstmt.setInt(1, 0);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, vMEDRama.getICveServicio());
			pstmt.setInt(4, vMEDRama.getICveRama());
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
}