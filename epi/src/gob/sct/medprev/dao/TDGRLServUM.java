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
 * Title: Data Acces Object de GRLServUM DAO
 * </p>
 * <p>
 * Description: Data Access Object de la tabla GRLServUM
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo Sanchez
 * @version 1.0
 */

public class TDGRLServUM extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLServUM() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLServUM = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLServUM vGRLServUM;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveUniMed," + "iCveModulo," + "iCveServicio"
					+ " from GRLServUM " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLServUM = new TVGRLServUM();
				vGRLServUM.setICveUniMed(rset.getInt(1));
				vGRLServUM.setICveModulo(rset.getInt(2));
				vGRLServUM.setICveServicio(rset.getInt(3));
				vcGRLServUM.addElement(vGRLServUM);
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcGRLServUM;
		}
	}

	/**
	 * Metodo getModulosXUniMed
	 */
	public Vector getModulosXUniMed(String cUniMed) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLServUM = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLServUM vGRLServUM;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ " distinct GRLServUM.iCveModulo, "
					+ " GRLModulo.cDscModulo "
					+ " from GRLServUM "
					+ " left join GRLModulo on GRLModulo.iCveUniMed = GRLServUM.iCveUniMed "
					+ "                    and GRLModulo.iCveModulo = GRLServUM.iCveModulo "
					+ " where GRLServUM.iCveUniMed = " + cUniMed
					+ " order by GRLModulo.cDscModulo ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLServUM = new TVGRLServUM();
				vGRLServUM.setICveModulo(rset.getInt(1));
				vGRLServUM.setCDscModulo(rset.getString(2));
				vcGRLServUM.addElement(vGRLServUM);
			}
		} catch (Exception ex) {
			warn("getModulosXUniMed", ex);
			throw new DAOException("getModulosXUniMed");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("getModulosXUniMed.close", ex2);
			}
			return vcGRLServUM;
		}
	}

	/**
	 * Metodo getServXUMMod
	 */
	public Vector getServXUMMod(String cUniMed, String cModulo)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLServUM = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLServUM vGRLServUM;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "  GRLServUM.iCveServicio, "
					+ "  MEDServicio.cDscServicio "
					+ "from GRLServUM "
					+ "left join MEDServicio on MEDServicio.iCveservicio = GRLServUM.iCveServicio "
					+ "where GRLServUM.iCveUniMed =  " + cUniMed
					+ "  and GRLServUM.iCveModulo =  " + cModulo
					+ " order by MEDServicio.cDscServicio ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLServUM = new TVGRLServUM();
				vGRLServUM.setICveServicio(rset.getInt(1));
				vGRLServUM.setCDscServicio(rset.getString(2));
				vcGRLServUM.addElement(vGRLServUM);
			}
		} catch (Exception ex) {
			warn("getModulosXuniMed", ex);
			throw new DAOException("getModulosXuniMed");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("getModulosXuniMed.close", ex2);
			}
			return vcGRLServUM;
		}
	}

	/**
	 * Metodo findByUniMedMod
	 * 
	 * @parameter UniMed - Unidad M�dica
	 * @parameter Modulo - M�dulo
	 * @author Marco A. Gonz�lez Paz
	 */
	public Vector findByUniMedMod(String UniMed, String Modulo)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLServUM = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLServUM vGRLServUM;
			int count;
			cSQL = "SELECT "
					+ "  GRLServUM.iCveUniMed, "
					+ "  GRLServUM.iCveModulo, "
					+ "  GRLServUM.iCveServicio, "
					+ "  GRLUniMed.cDscUniMed, "
					+ "  GRLModulo.cDscModulo, "
					+ "  MEDServicio.cDscServicio "
					+ "FROM GRLServUM "
					+ "JOIN GRLUniMed ON GRLServUM.iCveUniMed = GRLUniMed.iCveUniMed "
					+ "JOIN GRLModulo ON GRLServUM.iCveModulo = GRLModulo.iCveModulo "
					+ "                                  AND GRLServUM.iCveUniMed = GRLModulo.iCveUniMed "
					+ "JOIN MEDServicio ON GRLServUM.iCveServicio = MEDServicio.iCveServicio "
					+ "WHERE GRLServUM.iCveUniMed = " + UniMed
					+ " and GRLServUM.iCveModulo = " + Modulo;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLServUM = new TVGRLServUM();
				vGRLServUM.setICveUniMed(rset.getInt(1));
				vGRLServUM.setICveModulo(rset.getInt(2));
				vGRLServUM.setICveServicio(rset.getInt(3));
				vGRLServUM.setCDscUniMed(rset.getString(4));
				vGRLServUM.setCDscModulo(rset.getString(5));
				vGRLServUM.setCDscServicio(rset.getString(6));
				vcGRLServUM.addElement(vGRLServUM);
			}
		} catch (Exception ex) {
			warn("findByUniMedMod", ex);
			throw new DAOException("findByUniMedMod");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("findByUniMedMod.close", ex2);
			}
		}
		return vcGRLServUM;
	}

	/**
	 * Metodo find by all
	 */
	public Vector findByAll(String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLServUM = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLServUM vGRLServUM;
			int count;
			cSQL = "SELECT "
					+ "  GRLServUM.iCveUniMed, "
					+ "  GRLServUM.iCveModulo, "
					+ "  GRLServUM.iCveServicio, "
					+ "  GRLUniMed.cDscUniMed, "
					+ "  GRLModulo.cDscModulo, "
					+ "  MEDServicio.cDscServicio "
					+ "FROM GRLServUM "
					+ "JOIN GRLUniMed ON GRLServUM.iCveUniMed = GRLUniMed.iCveUniMed "
					+ "JOIN GRLModulo ON GRLServUM.iCveModulo = GRLModulo.iCveModulo "
					+ "                                  AND GRLServUM.iCveUniMed = GRLModulo.iCveUniMed "
					+ "JOIN MEDServicio ON GRLServUM.iCveServicio = MEDServicio.iCveServicio "
					+ "WHERE MEDServicio.lInterconsulta = 1 and GRLServUM.iCveServicio = "
					+ cServicio;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLServUM = new TVGRLServUM();
				vGRLServUM.setICveUniMed(rset.getInt(1));
				vGRLServUM.setICveModulo(rset.getInt(2));
				vGRLServUM.setICveServicio(rset.getInt(3));
				vGRLServUM.setCDscUniMed(rset.getString(4));
				vGRLServUM.setCDscModulo(rset.getString(5));
				vGRLServUM.setCDscServicio(rset.getString(6));
				vcGRLServUM.addElement(vGRLServUM);
			}
		} catch (Exception ex) {
			warn("findByAll", ex);
			throw new DAOException("findByAll");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcGRLServUM;
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
			TVGRLServUM vGRLServUM = (TVGRLServUM) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLServUM(" + "iCveUniMed," + "iCveModulo,"
					+ "iCveServicio" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vGRLServUM.getICveUniMed());
			pstmt.setInt(2, vGRLServUM.getICveModulo());
			pstmt.setInt(3, vGRLServUM.getICveServicio());
			pstmt.executeUpdate();
			cClave = "" + vGRLServUM.getICveUniMed();
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
				if (dbConn != null)
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
			TVGRLServUM vGRLServUM = (TVGRLServUM) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLServUM" + "where iCveUniMed = ? "
					+ "and iCveModulo = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLServUM.getICveUniMed());
			pstmt.setInt(2, vGRLServUM.getICveModulo());
			pstmt.setInt(3, vGRLServUM.getICveServicio());
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
				if (dbConn != null)
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
			TVGRLServUM vGRLServUM = (TVGRLServUM) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLServUM" + " where iCveUniMed = ?"
					+ " and iCveModulo = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLServUM.getICveUniMed());
			pstmt.setInt(2, vGRLServUM.getICveModulo());
			pstmt.setInt(3, vGRLServUM.getICveServicio());
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("delete.closeGRLServUM", ex2);
			}
			return cClave;
		}
	}
}
