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
 * Title: Data Acces Object de GRLMunicipio DAO
 * </p>
 * <p>
 * Description: DAO Tabal GRLMunicipio
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

public class TDMunicipio extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMunicipio() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cPais, String cEstado) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLMunicipio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMunicipio vGRLMunicipio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePais," + "iCveEntidadFed,"
					+ "iCveMunicipio," + "cNombre," + "cAbreviatura"
					+ " from GRLMunicipio ";

			if ((cPais != null) && (cEstado != null)) {
				cSQL = cSQL + " where iCvePais = " + cPais + " ";
				cSQL = cSQL + " and iCveEntidadFed = " + cEstado + " ";
			}

			cSQL = cSQL + "order by iCvePais,iCveEntidadFed";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLMunicipio = new TVMunicipio();
				vGRLMunicipio.setICvePais(rset.getInt(1));
				vGRLMunicipio.setICveEntidadFed(rset.getInt(2));
				vGRLMunicipio.setICveMunicipio(rset.getInt(3));
				vGRLMunicipio.setCNombre(rset.getString(4));
				vGRLMunicipio.setCAbreviatura(rset.getString(5));
				vcGRLMunicipio.addElement(vGRLMunicipio);
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
			return vcGRLMunicipio;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLMunicipio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMunicipio vGRLMunicipio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePais," + "iCveEntidadFed,"
					+ "iCveMunicipio," + "cNombre," + "cAbreviatura"
					+ " from GRLMunicipio " + cWhere;

			cSQL = cSQL + " order by iCvePais, cNombre ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLMunicipio = new TVMunicipio();
				vGRLMunicipio.setICvePais(rset.getInt(1));
				vGRLMunicipio.setICveEntidadFed(rset.getInt(2));
				vGRLMunicipio.setICveMunicipio(rset.getInt(3));
				vGRLMunicipio.setCNombre(rset.getString(4));
				vGRLMunicipio.setCAbreviatura(rset.getString(5));
				vcGRLMunicipio.addElement(vGRLMunicipio);
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
			return vcGRLMunicipio;
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
			TVMunicipio vGRLMunicipio = (TVMunicipio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLMunicipio(" + "iCvePais,"
					+ "iCveEntidadFed," + "iCveMunicipio," + "cNombre,"
					+ "cAbreviatura" + ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vGRLMunicipio.getICvePais());
			pstmt.setInt(2, vGRLMunicipio.getICveEntidadFed());
			pstmt.setInt(3, vGRLMunicipio.getICveMunicipio());
			pstmt.setString(4, vGRLMunicipio.getCNombre());
			pstmt.setString(5, vGRLMunicipio.getCAbreviatura());
			pstmt.executeUpdate();
			cClave = "" + vGRLMunicipio.getICvePais();
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
			TVMunicipio vGRLMunicipio = (TVMunicipio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLMunicipio" + " set cNombre= ?, "
					+ "cAbreviatura= ? " + "where iCvePais = ? "
					+ "and iCveEntidadFed = ?" + " and iCveMunicipio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vGRLMunicipio.getCNombre());
			pstmt.setString(2, vGRLMunicipio.getCAbreviatura());
			pstmt.setInt(3, vGRLMunicipio.getICvePais());
			pstmt.setInt(4, vGRLMunicipio.getICveEntidadFed());
			pstmt.setInt(5, vGRLMunicipio.getICveMunicipio());
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
			TVMunicipio vGRLMunicipio = (TVMunicipio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLMunicipio" + " where iCvePais = ?"
					+ " and iCveEntidadFed = ?" + " and iCveMunicipio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLMunicipio.getICvePais());
			pstmt.setInt(2, vGRLMunicipio.getICveEntidadFed());
			pstmt.setInt(3, vGRLMunicipio.getICveMunicipio());
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
				warn("delete.closeGRLMunicipio", ex2);
			}
			return cClave;
		}
	}
}