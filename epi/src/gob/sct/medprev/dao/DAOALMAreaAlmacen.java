package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMAreaAlmacen DAO
 * </p>
 * <p>
 * Description: DAO para la tabla ALMAreaAlmacen
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

public class DAOALMAreaAlmacen extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public DAOALMAreaAlmacen() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMAreaAlmacen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMAreaAlmacen vALMAreaAlmacen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select ALMAreaAlmacen.iCveUniMed,  "
					+ "        iCveModulo,  "
					+ "        iCveArea,  "
					+ "        ALMAreaAlmacen.iCveAlmacen, "
					+ "        ALMAlmacen.cDscAlmacen "
					+ "        from ALMAreaAlmacen  "
					+ " inner join ALMAlmacen on ALMAlmacen.iCveAlmacen = ALMAreaAlmacen.iCveAlmacen "
					+
					// "                      and ALMAlmacen.iCveUniMed = ALMAreaAlmacen.iCveUniMed "
					// +
					cFiltro + " order by iCveUniMed ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMAreaAlmacen = new TVALMAreaAlmacen();
				vALMAreaAlmacen.setICveUniMed(rset.getInt(1));
				vALMAreaAlmacen.setICveModulo(rset.getInt(2));
				vALMAreaAlmacen.setICveArea(rset.getInt(3));
				vALMAreaAlmacen.setICveAlmacen(rset.getInt(4));
				vALMAreaAlmacen.setCDscAlmacen(rset.getString(5));
				vcALMAreaAlmacen.addElement(vALMAreaAlmacen);
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
			return vcALMAreaAlmacen;
		}
	}
}
