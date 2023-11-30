package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;

/**
 * <p>
 * Title: Data Acces Object de PERDatos DAO
 * </p>
 * <p>
 * Description: DAO de la entidad PERDatos (PERPersona)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Su�rez Romero
 * @version 1.0
 */

/*
 * JESR: Solo estoy utilizando el Metodo findBySelector;
 */
 
public class TDPERDatosExt2 extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERDatosExt2() {
	}

	/**
	 * Metodo Find By All
	 * 
	 * @author: Marco A. Gonz�lez Paz
	 * @param: cCvePersona - Clave del Personal en Caracter. Incluye Join con
	 *         las Direcciones
	 */
	public boolean FindByAutorizacionDeAccesoAExp(String cCvePersona) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtDir = null;
		ResultSet rsetDir = null;
		Vector vcPERDatos = new Vector();
		boolean AccesoAutorizado = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLDir = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "PERDatos.iCvePersonal,"
					+ ""
					+ "where PERDatos.iCvePersonal = " + cCvePersona + " "
					+ "order by PERDatos.iCvePersonal";

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				AccesoAutorizado = true;
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
		} finally {
			try {

				if (pstmtDir != null) {
					pstmtDir.close();
				}
				if (rsetDir != null) {
					rsetDir.close();
				}

				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return AccesoAutorizado;
		}
	}

}