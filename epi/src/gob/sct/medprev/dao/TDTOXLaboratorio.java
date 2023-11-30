package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de Laboratorio DAO
 * </p>
 * <p>
 * Description: DAO Tabla TOXLaboratorio
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */

public class TDTOXLaboratorio extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXLaboratorio() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcLaboratorio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVLaboratorio vLaboratorio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveLaboratorio," + "cDscLaboratorio,"
					+ "iCvePais," + "iCveEstado," + "iCveMunicipio"
					+ " from TOXLaboratorio order by iCveLaboratorio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vLaboratorio = new TVLaboratorio();
				vLaboratorio.setICveLaboratorio(rset.getInt(1));
				vLaboratorio.setCDscLaboratorio(rset.getString(2));
				vLaboratorio.setICvePais(rset.getInt(3));
				vLaboratorio.setICveEstado(rset.getInt(4));
				vLaboratorio.setICveMunicipio(rset.getInt(5));
				vcLaboratorio.addElement(vLaboratorio);
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
			return vcLaboratorio;
		}
	}
}