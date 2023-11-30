package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de CTRUMPeriodo DAO
 * </p>
 * <p>
 * Description: Data Access Object de la Tabla CTRUMPeriodo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrej�n Adame.
 * @version 1.0
 */

public class TDCTRUMPeriodo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDCTRUMPeriodo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRUMPeriodo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRUMPeriodo vCTRUMPeriodo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCvePeriodPla," + "iCveUniMed,"
					+ "iCveMdoTrans," + "dtGeneradas " + " from CTRUMPeriodo "
					+ cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRUMPeriodo = new TVCTRUMPeriodo();
				vCTRUMPeriodo.setiAnio(rset.getInt(1));
				vCTRUMPeriodo.setiCvePeriodPla(rset.getInt(2));
				vCTRUMPeriodo.setiCveUniMed(rset.getInt(3));
				vCTRUMPeriodo.setiCveMdoTrans(rset.getInt(4));
				vCTRUMPeriodo.setdtGeneradas(rset.getDate(5));
				vcCTRUMPeriodo.addElement(vCTRUMPeriodo);
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
			return vcCTRUMPeriodo;
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
		int iMax = 0;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVCTRUMPeriodo vCTRUMPeriodo = (TVCTRUMPeriodo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into CTRUMPeriodo(" + "iAnio," + "iCvePeriodPla,"
					+ "iCveUniMed," + "iCveMdoTrans," + "dtGeneradas "
					+ ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCvePeriodPla) from CTRUMPeriodo";
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
			vCTRUMPeriodo.setiCvePeriodPla(iMax);
			// ******************************************************************
			pstmt.setInt(1, vCTRUMPeriodo.getiAnio());
			pstmt.setInt(2, vCTRUMPeriodo.getiCvePeriodPla());
			pstmt.setInt(3, vCTRUMPeriodo.getiCveUniMed());
			pstmt.setInt(4, vCTRUMPeriodo.getiCveMdoTrans());
			pstmt.setDate(5, vCTRUMPeriodo.getdtGeneradas());

			pstmt.executeUpdate();
			cClave = "" + vCTRUMPeriodo.getiCvePeriodPla();
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
			TVCTRUMPeriodo vCTRUMPeriodo = (TVCTRUMPeriodo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from CTRUMPeriodo" + " where iAnio = ? "
					+ " and iCvePeriodPla = ?" + " and iCveUniMed = ? "
					+ " and iCveMdoTrans = ? " + "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCTRUMPeriodo.getiAnio());
			pstmt.setInt(2, vCTRUMPeriodo.getiCvePeriodPla());
			pstmt.setInt(3, vCTRUMPeriodo.getiCveUniMed());
			pstmt.setInt(4, vCTRUMPeriodo.getiCveMdoTrans());

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
				warn("delete.closeCTRUMPeriodo2", ex2);
			}
			return cClave;
		}
	}
}
