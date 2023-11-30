package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de OAcCorrectiva DAO
 * </p>
 * <p>
 * Description: DAO para AppCache de AcCorrectiva
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

public class DAOTOXAcCorrectiva extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private Object oReturn = null;
	private boolean lSuccess = false;

	public DAOTOXAcCorrectiva() {
	}

	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcOAcCorrectiva = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVAcCorrectiva vOAcCorrectiva;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select           "
					+ "iCveAcCorrectiva,"
					+ "cDscacCorrectiva,"
					+ "lActivo          "
					+ " from TOXAcCorrectiva where lActivo = 1 order by cDscAcCorrectiva";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lSuccess = true;
				vOAcCorrectiva = new TVAcCorrectiva();
				vOAcCorrectiva.setICveAcCorrectiva(rset.getInt(1));
				vOAcCorrectiva.setCDscAcCorrectiva(rset.getString(2));
				vOAcCorrectiva.setLActivo(rset.getInt(3));
				vcOAcCorrectiva.addElement(vOAcCorrectiva);
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
			return vcOAcCorrectiva;
		}
	}

	/**
	 * Metodo que permite la posibilidad de llamar al DAO a trav�s del
	 * AppCacheManager.
	 * 
	 * @param cCollName
	 *            Nombre de la Colecci�n.
	 * @param cKey
	 *            permite pasar una lista de parametros para hacer la b�squeda
	 *            sobre la colecci�n.
	 * @return boolean, permite conocer el resultado de la b�squeda.
	 */
	public boolean findCollection(String cCollName, String cKey) {
		if (cCollName.compareTo("AcCorrectiva") == 0) {
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