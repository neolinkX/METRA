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

public class TDGRLDISCAPACIDAD extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLDISCAPACIDAD() {
	}

	/**
	 * Metodo que muestra los Grupos de DISCAPACIDAD
	 */
	public Vector FindByAllG() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLDISCAPACIDAD = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLDISCAPACIDAD vGRLDISCAPACIDAD;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT iCveGrupo, cDscGrupo FROM GRLDISCAPACIDAD GROUP BY iCveGrupo, cDscGrupo";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLDISCAPACIDAD = new TVGRLDISCAPACIDAD();
				vGRLDISCAPACIDAD.setICveGrupoD(rset.getInt(1));
				vGRLDISCAPACIDAD.setCDscGrupoD(rset.getString(2));
				vcGRLDISCAPACIDAD.addElement(vGRLDISCAPACIDAD);
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
			return vcGRLDISCAPACIDAD;
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
		Vector vcGRLDISCAPACIDAD = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLDISCAPACIDAD vGRLDISCAPACIDAD;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT iCveSubGrupo, cDscSubGrupo FROM GRLDISCAPACIDAD "
					+ where + " GROUP BY iCveSubGrupo, cDscSubGrupo";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLDISCAPACIDAD = new TVGRLDISCAPACIDAD();
				vGRLDISCAPACIDAD.setICveSubGrupoD(rset.getString(1));
				vGRLDISCAPACIDAD.setCDscSubGrupoD(rset.getString(2));
				vcGRLDISCAPACIDAD.addElement(vGRLDISCAPACIDAD);
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
			return vcGRLDISCAPACIDAD;
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
		Vector vcGRLDISCAPACIDAD = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLDISCAPACIDAD vGRLDISCAPACIDAD;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT iCveDiscapacidad, cDcsDiscapacidad "
					+ "FROM GRLDISCAPACIDAD " + "where iCveGrupo = " + Grupo
					+ " and iCveSubGrupo = '" + SubGrupo + "' "
					+ "GROUP BY iCveDiscapacidad, cDcsDiscapacidad";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLDISCAPACIDAD = new TVGRLDISCAPACIDAD();
				vGRLDISCAPACIDAD.setICveDiscapacidad(rset.getInt(1));
				vGRLDISCAPACIDAD.setCDscDiscapacidad(rset.getString(2));
				vcGRLDISCAPACIDAD.addElement(vGRLDISCAPACIDAD);
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
			return vcGRLDISCAPACIDAD;
		}
	}

}