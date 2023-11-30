package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de OGRLAreaModulo DAO
 * </p>
 * <p>
 * Description: DAO para la tabla GRLAreaModulo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hern�ndez Garc�a
 * @version 1.0
 */

public class DAOGRLAreaModulo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public DAOGRLAreaModulo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcOGRLAreaModulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLAreaModulo vOGRLAreaModulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select iCveUniMed,  "
					+ "        iCveModulo,  "
					+ "        GRLAreaModulo.iCveArea, "
					+ "        GRLArea.cdscArea, cResponsable "
					+ "        from GRLAreaModulo  "
					+ " left join GRLArea on GRLArea.iCveArea = GRLAreaModulo.iCveArea "
					+ cFiltro + " order by GRLArea.cdscArea ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vOGRLAreaModulo = new TVGRLAreaModulo();
				vOGRLAreaModulo.setICveUniMed(rset.getInt(1));
				vOGRLAreaModulo.setICveModulo(rset.getInt(2));
				vOGRLAreaModulo.setICveArea(rset.getInt(3));
				vOGRLAreaModulo.setCDscArea(rset.getString(4));
				vOGRLAreaModulo.setCResponsable(rset.getString(5));
				vcOGRLAreaModulo.addElement(vOGRLAreaModulo);
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
			return vcOGRLAreaModulo;
		}
	}

	/**
	 * B�squeda de la �reas configuradas al usuario.
	 * 
	 * @param cFiltro
	 *            condici�n de B�squeda de los permisos.
	 * @return Vector con los values de la informaci�n de las �reas.
	 * @throws DAOException
	 */
	public Vector FindPermUsuario(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vAreas = new Vector();
		TVGRLAreaModulo vAreaModulo;
		StringBuffer cSQL = new StringBuffer();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select UA.iCveUniMed, UA.iCveModulo, UA.iCveArea, ")
					// 1, 2, 3
					.append("        A.cDscArea ")
					// 4
					.append(" from GRLUsuArea UA ")
					.append("        inner join GRLArea A on A.iCveArea = UA.iCveArea ")
					.append(cFiltro);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vAreaModulo = new TVGRLAreaModulo();
				vAreaModulo.setICveUniMed(rset.getInt(1));
				vAreaModulo.setICveModulo(rset.getInt(2));
				vAreaModulo.setICveArea(rset.getInt(3));
				vAreaModulo.setCDscArea(rset.getString(4));
				vAreas.addElement(vAreaModulo);
			}
		} catch (Exception ex) {
			warn("FindPermUsuario", ex);
			throw new DAOException("FindPermUsuario");
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
			return vAreas;
		}
	}

}
