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
 * Title: Data Acces Object de AcCorrectiva DAO
 * </p>
 * <p>
 * Description: Catalogo de acciones correctivas
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

public class TDTOXAcCorrectiva extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXAcCorrectiva() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcAcCorrectiva = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXAcCorrectiva vAcCorrectiva;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveAcCorrectiva," + "cDscAcCorrectiva,"
					+ "lActivo" + " from TOXAcCorrectiva  "
					+ " where lActivo = 1 "
					+ "        order by cDscAcCorrectiva";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vAcCorrectiva = new TVTOXAcCorrectiva();
				vAcCorrectiva.setICveAcCorrectiva(rset.getInt(1));
				vAcCorrectiva.setCDscAcCorrectiva(rset.getString(2));
				vAcCorrectiva.setLActivo(rset.getInt(3));
				vcAcCorrectiva.addElement(vAcCorrectiva);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcAcCorrectiva;
		}
	}
}
