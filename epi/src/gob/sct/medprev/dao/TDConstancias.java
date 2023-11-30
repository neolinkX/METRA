package gob.sct.medprev.dao; 

import java.sql.*;
import java.util.*;

import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import gob.sct.medprev.vo.*;

import com.micper.util.*;
import com.micper.seguridad.vo.TVDinRep02;
import java.util.StringTokenizer;

/**
 * <p>
 * Title: Data Acces Object de Cosnatancias
 * </p>
 * <p>
 * Description: Data Access Object para Generar Constancias
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * 
 * @author AG SA SANDOVAL
 * @version 1.0
 */

public class TDConstancias extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDConstancias() {
	}

	/**
	 * Método Regresa UN STRING
	 * 
	 * @Autor: AG SA
	 */
	public String RegresaS(String SQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String regreso = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = SQL;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regreso = rset.getString(1);
			}

		} catch (Exception ex) {
			warn("RegresaString", ex);
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
				warn("RegresaString", ex2);
			}
			return regreso;
		}
	}
}
