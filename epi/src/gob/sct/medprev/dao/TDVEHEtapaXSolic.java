package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de VEHEtapaXSolic DAO
 * </p>
 * <p>
 * Description: Control de Vehiculos - Etapas por solicitud
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */

public class TDVEHEtapaXSolic extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDVEHEtapaXSolic() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHEtapaXSolic = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHEtapaXSolic vVEHEtapaXSolic;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveUniMed," + "iCveSolicitud,"
					+ "iCveEtapaSolic," + "dtRegistro," + "iCveUsuRegistra"
					+ " from VEHEtapaXSolic " + cWhere + cOrderBy;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHEtapaXSolic = new TVVEHEtapaXSolic();
				vVEHEtapaXSolic.setIAnio(rset.getInt(1));
				vVEHEtapaXSolic.setICveUniMed(rset.getInt(2));
				vVEHEtapaXSolic.setICveSolicitud(rset.getInt(3));
				vVEHEtapaXSolic.setICveEtapaSolic(rset.getInt(4));
				vVEHEtapaXSolic.setDtRegistro(rset.getDate(5));
				vVEHEtapaXSolic.setICveUsuRegistra(rset.getInt(6));
				vcVEHEtapaXSolic.addElement(vVEHEtapaXSolic);
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
			return vcVEHEtapaXSolic;
		}
	}

	/**
	 * 
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVVEHEtapaXSolic vVEHEtapaXSolic = (TVVEHEtapaXSolic) obj;
			cSQL = "insert into VEHEtapaXSolic(" + "iAnio," + "iCveUniMed,"
					+ "iCveSolicitud," + "iCveEtapaSolic," + "dtRegistro,"
					+ "iCveUsuRegistra" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vVEHEtapaXSolic.getIAnio());
			pstmt.setInt(2, vVEHEtapaXSolic.getICveUniMed());
			pstmt.setInt(3, vVEHEtapaXSolic.getICveSolicitud());
			pstmt.setInt(4, vVEHEtapaXSolic.getICveEtapaSolic());
			pstmt.setDate(5, vVEHEtapaXSolic.getDtRegistro());
			pstmt.setInt(6, vVEHEtapaXSolic.getICveUsuRegistra());
			pstmt.executeUpdate();
			cClave = "" + vVEHEtapaXSolic.getIAnio();
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
					dbConn.closeConnection();
				}
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
			TVVEHEtapaXSolic vVEHEtapaXSolic = (TVVEHEtapaXSolic) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VEHEtapaXSolic" + " set dtRegistro= ?, "
					+ "iCveUsuRegistra= ? " + "where iAnio = ? "
					+ "and iCveUniMed = ?" + "and iCveSolicitud = ?"
					+ " and iCveEtapaSolic = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vVEHEtapaXSolic.getDtRegistro());
			pstmt.setInt(2, vVEHEtapaXSolic.getICveUsuRegistra());
			pstmt.setInt(3, vVEHEtapaXSolic.getIAnio());
			pstmt.setInt(4, vVEHEtapaXSolic.getICveUniMed());
			pstmt.setInt(5, vVEHEtapaXSolic.getICveSolicitud());
			pstmt.setInt(6, vVEHEtapaXSolic.getICveEtapaSolic());
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
			TVVEHEtapaXSolic vVEHEtapaXSolic = (TVVEHEtapaXSolic) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from VEHEtapaXSolic" + " where iAnio = ?"
					+ " and iCveUniMed = ?" + " and iCveSolicitud = ?"
					+ " and iCveEtapaSolic = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHEtapaXSolic.getIAnio());
			pstmt.setInt(2, vVEHEtapaXSolic.getICveUniMed());
			pstmt.setInt(3, vVEHEtapaXSolic.getICveSolicitud());
			pstmt.setInt(4, vVEHEtapaXSolic.getICveEtapaSolic());
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
				warn("delete.closeVEHEtapaXSolic", ex2);
			}
			return cClave;
		}
	}
}