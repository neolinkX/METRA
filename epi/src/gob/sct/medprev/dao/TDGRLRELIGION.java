/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * 
 * @author AG SA
 */

public class TDGRLRELIGION extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLRELIGION() {
	}

	/**
	 * Metodo que muestra los Grupos de DISCAPACIDAD
	 */
	public Vector FindByAllG() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLRELIGION = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLRELIGION vGRLRELIGION;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT iCveGrupo, cGrupo FROM GRLRELIGION GROUP BY iCveGrupo, cGrupo";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLRELIGION = new TVGRLRELIGION();
				vGRLRELIGION.setICveGrupo(rset.getInt(1));
				vGRLRELIGION.setCGrupo(rset.getString(2));
				vcGRLRELIGION.addElement(vGRLRELIGION);
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
			return vcGRLRELIGION;
		}
	}

	/**
	 * Metodo que muestra los SubGrupos de DISCAPACIDAD por Grupo
	 */
	public Vector FindByAllS(String where) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLRELIGION = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLRELIGION vGRLRELIGION;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT iCveSubGrupo, cSubGrupo FROM GRLRELIGION " + where
					+ " GROUP BY iCveSubGrupo, cSubGrupo";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLRELIGION = new TVGRLRELIGION();
				vGRLRELIGION.setICveSubGrupo(rset.getInt(1));
				vGRLRELIGION.setCSubGrupoD(rset.getString(2));
				vcGRLRELIGION.addElement(vGRLRELIGION);
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
			return vcGRLRELIGION;
		}
	}

	/**
	 * Metodo que muestra la DISCAPACIDAD por Grupo y SubGrupo
	 */
	public Vector FindByAllD(String Grupo, String SubGrupo) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLRELIGION = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLRELIGION vGRLRELIGION;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT iCveReligion, cCodDsc " + "FROM GRLRELIGION "
					+ "where iCveGrupo = " + Grupo + " and iCveSubGrupo = '"
					+ SubGrupo + "' " + "GROUP BY iCveReligion, cCodDsc";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLRELIGION = new TVGRLRELIGION();
				vGRLRELIGION.setICveReligion(rset.getInt(1));
				vGRLRELIGION.setCCodDsc(rset.getString(2));
				vcGRLRELIGION.addElement(vGRLRELIGION);
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
			return vcGRLRELIGION;
		}
	}

}