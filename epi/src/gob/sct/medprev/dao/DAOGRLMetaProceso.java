package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLMetaProceso DAO
 * </p>
 * <p>
 * Description: DAO para la tabla GRLMetaProceso
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

public class DAOGRLMetaProceso extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public DAOGRLMetaProceso() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLMetaProceso = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLMetaProceso vGRLMetaProceso;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveMeta," + "cDscMeta," + "lActivo"
					+ " from GRLMetaProceso " + cFiltro + "  order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLMetaProceso = new TVGRLMetaProceso();
				vGRLMetaProceso.setIAnio(rset.getInt(1));
				vGRLMetaProceso.setICveMeta(rset.getInt(2));
				vGRLMetaProceso.setCDscMeta(rset.getString(3));
				vGRLMetaProceso.setLActivo(rset.getInt(4));
				vcGRLMetaProceso.addElement(vGRLMetaProceso);
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
			return vcGRLMetaProceso;
		}
	}
}
