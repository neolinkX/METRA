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
 * Title: Data Acces Object de ALMUbicacion DAO
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

public class TDALMUbicacion extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMUbicacion() {
	}

	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMUbicacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMUbicacion vALMUbicacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iCveAlmacen,  " + " iCveArea,  "
					+ " iCveSeccion,  " + " iCveArticulo   "
					+ " from ALMUbicacion " + cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMUbicacion = new TVALMUbicacion();
				vALMUbicacion.setiCveAlmacen(rset.getInt(1));
				vALMUbicacion.setiCveArea(rset.getInt(2));
				vALMUbicacion.setiCveSeccion(rset.getInt(3));
				vALMUbicacion.setiCveArticulo(rset.getInt(4));
				vcALMUbicacion.addElement(vALMUbicacion);
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
			return vcALMUbicacion;
		}
	}

	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMUbicacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMUbicacion vALMUbicacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " ALMUbicacion.iCveAlmacen, "
					+ " ALMUbicacion.iCveArea,    "
					+ " ALMUbicacion.iCveSeccion, "
					+ " ALMUbicacion.iCveArticulo " + " from ALMUbicacion "
					+ cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMUbicacion = new TVALMUbicacion();
				vALMUbicacion.setiCveAlmacen(rset.getInt(1));
				vALMUbicacion.setiCveArea(rset.getInt(2));
				vALMUbicacion.setiCveSeccion(rset.getInt(3));
				vALMUbicacion.setiCveArticulo(rset.getInt(4));
				vcALMUbicacion.addElement(vALMUbicacion);
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
			return vcALMUbicacion;
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
			TVALMUbicacion vALMUbicacion = (TVALMUbicacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " delete from ALMUbicacion " + " where iCveAlmacen = ?    "
					+ "   and iCveArea = ?       "
					+ "   and iCveSeccion = ?    "
					+ "   and iCveArticulo = ?   " + "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMUbicacion.getiCveAlmacen());
			pstmt.setInt(2, vALMUbicacion.getiCveArea());
			pstmt.setInt(3, vALMUbicacion.getiCveSeccion());
			pstmt.setInt(4, vALMUbicacion.getiCveArticulo());
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
				warn("delete.closeALMUbicacion", ex2);
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
				warn("delete.closeALMUbicacion", ex2);
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
			TVALMUbicacion vALMUbicacion = (TVALMUbicacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " insert into ALMUbicacion( "
					+ " iCveAlmacen,              "
					+ " iCveArea,                 "
					+ " iCveSeccion,              "
					+ " iCveArticulo              " + " ) values (?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vALMUbicacion.getiCveAlmacen());
			pstmt.setInt(2, vALMUbicacion.getiCveArea());
			pstmt.setInt(3, vALMUbicacion.getiCveSeccion());
			pstmt.setInt(4, vALMUbicacion.getiCveArticulo());
			pstmt.executeUpdate();
			cClave = "" + vALMUbicacion.getiCveAlmacen();
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

}