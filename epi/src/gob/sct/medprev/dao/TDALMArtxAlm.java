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
 * Title: Data Acces Object de ALMArtxAlm DAO
 * </p>
 * <p>
 * Description: DAO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrejón Adame
 * @version 1.0
 */

public class TDALMArtxAlm extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMArtxAlm() {
	}

	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMArtxAlm = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMArtxAlm vALMArtxAlm;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iCveAlmacen,  " + " iCveArticulo,   "
					+ " dExistencia, " + " dMaximo,     " + " dMinimo  "
					+ " from ALMArtxAlm " + cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMArtxAlm = new TVALMArtxAlm();
				vALMArtxAlm.setiCveAlmacen(rset.getInt(1));
				vALMArtxAlm.setiCveArticulo(rset.getInt(2));
				vALMArtxAlm.setdExistencia(rset.getDouble(3));
				vALMArtxAlm.setdMaximo(rset.getDouble(4));
				vALMArtxAlm.setdMinimo(rset.getDouble(5));
				vcALMArtxAlm.addElement(vALMArtxAlm);
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
			return vcALMArtxAlm;
		}
	}

	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMArtxAlm = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMArtxAlm vALMArtxAlm;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " ALMArtxAlm.iCveAlmacen,  "
					+ " ALMArtxAlm.iCveArticulo,   "
					+ " ALMArticulo.cDscArticulo,   "
					+ " ALMArtxAlm.dExistencia, " + " ALMArtxAlm.dMaximo,     "
					+ " ALMArtxAlm.dMinimo  " + " from ALMArtxAlm " + cWhere
					+ " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMArtxAlm = new TVALMArtxAlm();
				vALMArtxAlm.setiCveAlmacen(rset.getInt(1));
				vALMArtxAlm.setiCveArticulo(rset.getInt(2));
				vALMArtxAlm.setcDscArticulo(rset.getString(3));
				vALMArtxAlm.setdExistencia(rset.getDouble(4));
				vALMArtxAlm.setdMaximo(rset.getDouble(5));
				vALMArtxAlm.setdMinimo(rset.getDouble(6));
				vcALMArtxAlm.addElement(vALMArtxAlm);
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
			return vcALMArtxAlm;
		}
	}

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
			TVALMArtxAlm VALMArtxAlm = (TVALMArtxAlm) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMArtxAlm" + " set dMaximo = ?, "
					+ "     dMinimo = ? " + " where iCveAlmacen = ? "
					+ "   and iCveArticulo = ?";
			pstmt = conn.prepareStatement(cSQL);

			if (new Double(VALMArtxAlm.getdMaximo()) == null)
				pstmt.setNull(1, Types.FLOAT);
			else
				pstmt.setDouble(1, VALMArtxAlm.getdMaximo());

			if (new Double(VALMArtxAlm.getdMinimo()) == null)
				pstmt.setNull(2, Types.FLOAT);
			else
				pstmt.setDouble(2, VALMArtxAlm.getdMinimo());

			pstmt.setInt(3, VALMArtxAlm.getiCveAlmacen());
			pstmt.setInt(4, VALMArtxAlm.getiCveArticulo());

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

	public Object updExistencias(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null)
				conn = conGral;
			else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVALMArtxAlm VALMArtxAlm = (TVALMArtxAlm) obj;
			cSQL = "update ALMArtxAlm" + " set dExistencia = ? "
					+ " where iCveAlmacen = ? " + "   and iCveArticulo = ?";
			pstmt = conn.prepareStatement(cSQL);

			if (new Double(VALMArtxAlm.getdExistencia()) == null)
				pstmt.setNull(1, Types.FLOAT);
			else
				pstmt.setDouble(1, VALMArtxAlm.getdExistencia());

			pstmt.setInt(2, VALMArtxAlm.getiCveAlmacen());
			pstmt.setInt(3, VALMArtxAlm.getiCveArticulo());

			if (pstmt.executeUpdate() > 0)
				cClave = "" + VALMArtxAlm.getiCveArticulo();
			else
				cClave = "";
			if (conGral == null)
				conn.commit();
		} catch (Exception ex) {
			try {
				if (conGral == null)
					conn.rollback();
			} catch (Exception ex1) {
				warn("update", ex1);
			}
			warn("update", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conGral == null)
					if (!conn.isClosed()) {
						conn.close();
						dbConn.closeConnection();
					}
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
			TVALMArtxAlm vALMArtxAlm = (TVALMArtxAlm) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " delete from ALMArtxAlm " + " where iCveAlmacen = ?     "
					+ "   and iCveArticulo = ?      " + "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMArtxAlm.getiCveAlmacen());
			pstmt.setInt(2, vALMArtxAlm.getiCveArticulo());
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
				warn("delete.closeALMArtxAlm", ex2);
			}
			return cClave;
		}
	}

	public Object deleteCustomWhere(Connection conGral, String cSQL)
			throws DAOException {
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
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			pstmt = conn.prepareStatement(cSQL);

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
				warn("delete.closeALMArtxAlm", ex2);
			}
			return cClave;
		}
	}

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
			TVALMArtxAlm vALMArtxAlm = (TVALMArtxAlm) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " insert into ALMArtxAlm( " + " iCveAlmacen, "
					+ " iCveArticulo," + " dExistencia," + " dMaximo,"
					+ " dMinimo " + " ) values (?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vALMArtxAlm.getiCveAlmacen());
			pstmt.setInt(2, vALMArtxAlm.getiCveArticulo());

			if (new Double(vALMArtxAlm.getdExistencia()) == null)
				pstmt.setNull(3, Types.FLOAT);
			else
				pstmt.setDouble(3, vALMArtxAlm.getdExistencia());

			if (new Double(vALMArtxAlm.getdMaximo()) == null)
				pstmt.setNull(4, Types.FLOAT);
			else
				pstmt.setDouble(4, vALMArtxAlm.getdMaximo());

			if (new Double(vALMArtxAlm.getdMinimo()) == null)
				pstmt.setNull(5, Types.FLOAT);
			else
				pstmt.setDouble(5, vALMArtxAlm.getdMinimo());

			pstmt.setDouble(3, vALMArtxAlm.getdExistencia());
			pstmt.setDouble(4, vALMArtxAlm.getdMaximo());
			pstmt.setDouble(5, vALMArtxAlm.getdMinimo());
			pstmt.executeUpdate();
			cClave = "" + vALMArtxAlm.getiCveAlmacen();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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

	public Vector FindByArtAlm(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMArtxAlm = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMArtxAlm vALMArtxAlm;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " ALMArtxAlm.iCveAlmacen, "
					+ " ALMArtxAlm.iCveArticulo, " + " ALMArticulo.cDscBreve, "
					+ " ALMArtxAlm.dExistencia, " + " ALMArtxAlm.dMaximo, "
					+ " ALMArtxAlm.dMinimo, " + " ALMArticulo.lLote  "
					+ " from ALMArtxAlm, ALMArticulo " + cWhere + " ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMArtxAlm = new TVALMArtxAlm();
				vALMArtxAlm.setiCveAlmacen(rset.getInt(1));
				vALMArtxAlm.setiCveArticulo(rset.getInt(2));
				vALMArtxAlm.setcDscBreve(rset.getString(3));
				vALMArtxAlm.setdExistencia(rset.getDouble(4));
				vALMArtxAlm.setdMaximo(rset.getDouble(5));
				vALMArtxAlm.setdMinimo(rset.getDouble(6));
				vALMArtxAlm.setlLote(rset.getInt(7));
				vcALMArtxAlm.addElement(vALMArtxAlm);
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
			return vcALMArtxAlm;
		}
	}
}