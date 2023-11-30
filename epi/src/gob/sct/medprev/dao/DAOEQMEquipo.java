package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de Equipo DAO
 * </p>
 * <p>
 * Description: DAO para la tabla EQMEquipo
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

public class DAOEQMEquipo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private Object oReturn = null;
	private boolean lSuccess = false;

	public DAOEQMEquipo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMEquipo vEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEquipo," + "cDscEquipo," + "cNumSerie,"
					+ "cModelo," + "iCveTpoEquipo," + "iCveMarca"
					+ " from EQMEquipo order by iCveEquipo";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lSuccess = true;
				vEquipo = new TVEQMEquipo();
				vEquipo.setICveEquipo(rset.getInt(1));
				vEquipo.setCDscEquipo(rset.getString(2));
				vEquipo.setCNumSerie(rset.getString(3));
				vEquipo.setCModelo(rset.getString(4));
				vEquipo.setICveTpoEquipo(rset.getInt(5));
				vEquipo.setICveMarca(rset.getInt(6));
				vcEquipo.addElement(vEquipo);
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
			return vcEquipo;
		}
	}

	/**
	 * Metodo que permite la posibilidad de llamar al DAO a trav�s del
	 * AppCacheManager.
	 * 
	 * @param String
	 *            , Nombre de la Colecci�n.
	 * @param String
	 *            , permite pasar una lista de parametros para hacer la
	 *            b�squeda sobre la colecci�n.
	 * @return boolean, permite conocer el resultado de la b�squeda.
	 */
	public boolean findCollection(String cCollName, String cKey) {
		if (cCollName.compareTo("EQMEquipo") == 0) {
			try {
				oReturn = this.FindByAll();
			} catch (Exception e) {
				warn("findCollection:" + cCollName + '.' + cKey, e);
				lSuccess = false;
			}
		}
		return lSuccess;
	}

	/**
	 * Metodo que permite la posibilidad de llamar al DAO a trav�s del
	 * AppCacheManager.
	 * 
	 * @return Object, Objeto que regresa el resultado de la b�squeda.
	 */
	public Object getCollection() {
		return oReturn;
	}

}
