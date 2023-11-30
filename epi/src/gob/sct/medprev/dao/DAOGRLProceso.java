package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de OGRLProceso DAO
 * </p>
 * <p>
 * Description: DAO para AppCache de GRLProceso
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

public class DAOGRLProceso extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private Object oReturn = null;
	private boolean lSuccess = false;

	public DAOGRLProceso() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcOGRLProceso = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLProceso vOGRLProceso;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveProceso," + "cDscProceso," + "iRefNum"
					+ " from GRLProceso order by iCveproceso";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lSuccess = true;
				vOGRLProceso = new TVGRLProceso();
				vOGRLProceso.setICveProceso(rset.getInt(1));
				vOGRLProceso.setCDscProceso(rset.getString(2));
				vOGRLProceso.setIRefNum(rset.getInt(3));
				vcOGRLProceso.addElement(vOGRLProceso);
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
			return vcOGRLProceso;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByCustom(int iCveUniMed) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcOGRLProceso = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLProceso vOGRLProceso;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select grlprocesoum.iCveProceso, GRLProceso.cDscProceso "
					+ "from grlprocesoum "
					+ "join GRLProceso on grlprocesoum.iCveProceso = GRLProceso.iCveProceso "
					+ "where grlprocesoum.iCveUniMed = " + iCveUniMed;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lSuccess = true;
				vOGRLProceso = new TVGRLProceso();
				vOGRLProceso.setICveProceso(rset.getInt(1));
				vOGRLProceso.setCDscProceso(rset.getString(2));
				vcOGRLProceso.addElement(vOGRLProceso);
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
			return vcOGRLProceso;
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
		if (cCollName.compareTo("GRLProceso") == 0 && cKey.equals("")) {
			try {
				oReturn = this.FindByAll();
			} catch (Exception e) {
				warn("findCollection:" + cCollName + '.' + cKey, e);
				lSuccess = false;
			}
		}
		if (cCollName.compareTo("GRLProceso") == 0 && !cKey.equals("")) {
			try {
				StringTokenizer stKey = new StringTokenizer(cKey, "|");
				oReturn = this.FindByCustom(Integer.parseInt(stKey.nextToken(),
						10));
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