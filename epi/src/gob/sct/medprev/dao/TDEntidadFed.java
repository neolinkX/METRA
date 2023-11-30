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
 * Title: Data Acces Object de EntidadFed DAO
 * </p>
 * <p>
 * Description:
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

public class TDEntidadFed extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEntidadFed() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cPais) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEntidadFed = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEntidadFed vEntidadFed;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePais," + "iCveEntidadFed," + "cNombre,"
					+ "cAbreviatura" + " from GRLEntidadFed " + " " + cPais
					+ " ORDER BY iCvePais, cNombre ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEntidadFed = new TVEntidadFed();
				vEntidadFed.setICvePais(rset.getInt(1));
				vEntidadFed.setICveEntidadFed(rset.getInt(2));
				vEntidadFed.setCNombre(rset.getString(4) + "   " +rset.getString(3));
				vEntidadFed.setCAbreviatura(rset.getString(4));
				vcEntidadFed.addElement(vEntidadFed);
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
			return vcEntidadFed;
		}
	}

	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEntidadFed = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEntidadFed vEntidadFed;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePais," + "cNombre," + "cAbreviatura"
					+ " from GRLEntidadFed";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEntidadFed = new TVEntidadFed();
				vEntidadFed.setICvePais(rset.getInt(1));
				vEntidadFed.setCNombre(rset.getString(3) +"   "+ rset.getString(2));
				vEntidadFed.setCAbreviatura(rset.getString(3));
				vcEntidadFed.addElement(vEntidadFed);
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
			return vcEntidadFed;
		}
	}
}