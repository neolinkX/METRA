package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXMotivoRecep DAO
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

public class TDTOXMotivoRecep extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXMotivoRecep() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXMotivoRecep = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXMotivoRecep vTOXMotivoRecep;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMotRecep," + "iCveTipoRecep,"
					+ "cDscMotRecep"
					+ " from TOXMotivoRecep order by iCveMotRecep";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXMotivoRecep = new TVTOXMotivoRecep();
				vTOXMotivoRecep.setICveMotRecep(rset.getInt(1));
				vTOXMotivoRecep.setICveTipoRecep(rset.getInt(2));
				vTOXMotivoRecep.setCDscMotRecep(rset.getString(3));
				vcTOXMotivoRecep.addElement(vTOXMotivoRecep);
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
			return vcTOXMotivoRecep;
		}
	}

	/**
	 * Metodo Find By Where
	 */
	public Vector FindByWhere(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXMotivoRecep = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXMotivoRecep vTOXMotivoRecep;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select iCveMotRecep,iCveTipoRecep,cDscMotRecep from TOXMotivoRecep "
					+ cFiltro + " order by iCveTipoRecep, iOrden";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXMotivoRecep = new TVTOXMotivoRecep();
				vTOXMotivoRecep.setICveMotRecep(rset.getInt(1));
				vTOXMotivoRecep.setICveTipoRecep(rset.getInt(2));
				vTOXMotivoRecep.setCDscMotRecep(rset.getString(3));
				vcTOXMotivoRecep.addElement(vTOXMotivoRecep);
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
			return vcTOXMotivoRecep;
		}
	}

}
