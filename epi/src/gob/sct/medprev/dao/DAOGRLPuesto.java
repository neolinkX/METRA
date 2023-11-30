package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de OGRLPuesto DAO
 * </p>
 * <p>
 * Description: DAO de la Entidad GRLPuesto
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

public class DAOGRLPuesto extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private Object oReturn = null;
	private boolean lSuccess = false;

	public DAOGRLPuesto() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(int iCveModoTrans) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcOGRLPuesto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLPuesto vOGRLPuesto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "iCveModoTrans,"
					+ "iCvePuesto,"
					+ "iCveCategoria,"
					+ "cDscPuesto,"
					+ "cDscCategoria"
					+ " from GRLPuesto "
					+ "join grlcategoria on grlpuesto.icvemodotrans = grlcategoria.icvemodotrans "
					+ "and grlpuesto.icvecategoria = grlcategoria.icvecategoria "
					+ " where grlpuesto.icvemodotrans = " + iCveModoTrans
					+ " order by iCveModoTrans";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lSuccess = true;
				vOGRLPuesto = new TVGRLPuesto();
				vOGRLPuesto.setICveModoTrans(rset.getInt(1));
				vOGRLPuesto.setICvePuesto(rset.getInt(2));
				vOGRLPuesto.setICveCategoria(rset.getInt(3));
				vOGRLPuesto.setCDscPuesto(rset.getString(4));
				vOGRLPuesto.setCDscCategoria(rset.getString(5));
				vcOGRLPuesto.addElement(vOGRLPuesto);
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
			return vcOGRLPuesto;
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
		if (cCollName.compareTo("GRLProceso") == 0) {
			try {
				StringTokenizer stKey = new StringTokenizer(cKey, "|");
				oReturn = this
						.FindByAll(Integer.parseInt(stKey.nextToken(), 10));
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
