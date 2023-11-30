package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLUniMed DAO
 * </p>
 * <p>
 * Description: DAO para AppCache de GRLUniMed
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonz�lez Paz
 * @version 1.0
 */

public class DAOMEDRama extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private Object oReturn = null;
	private boolean lSuccess = false;

	public DAOMEDRama() {
	}

	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcOMEDRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDRama vMEDRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveRama," + "cDscRama "
					+ " from MEDRama order by iCveRama";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lSuccess = true;
				vMEDRama = new TVMEDRama();
				vMEDRama.setICveRama(rset.getInt(1));
				vMEDRama.setCDscRama(rset.getString(2));
				vcOMEDRama.addElement(vMEDRama);
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
			return vcOMEDRama;
		}
	}

	public Vector FindByServicio(int iCveServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcOTOXSustancia = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDRama vMEDRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select MEDRama.iCveRama,     "
					+ "        MEDRama.cDscRama      "
					+ " from MEDRama                 "
					+ " where MEDRama.iCveServicio = " + iCveServicio
					+ " and   MEDRama.lActivo      = 1 "
					+ " order by MEDRama.iCveRama, MEDRama.cDscRama ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lSuccess = true;
				vMEDRama = new TVMEDRama();
				vMEDRama.setICveRama(rset.getInt(1));
				vMEDRama.setCDscRama(rset.getString(2));
				vcOTOXSustancia.addElement(vMEDRama);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("FindByProceso", ex);
			throw new DAOException("FindByProceso");
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
				warn("FindByProceso.close", ex2);
			}
			return vcOTOXSustancia;
		}
	}

	public boolean findCollection(String cCollName, String cKey) {
		if (cCollName.compareTo("MEDRama") == 0) {
			try {
				StringTokenizer stKey = new StringTokenizer(cKey, "|");

				oReturn = this.FindByServicio(Integer.parseInt(
						stKey.nextToken(), 10));
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