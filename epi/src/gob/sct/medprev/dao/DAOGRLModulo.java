package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.TVGRLModulo;

/**
 * <p>
 * Title: Data Acces Object de GRLModulo
 * </p>
 * <p>
 * Description: DAO de la Entidad GRLModulo
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

public class DAOGRLModulo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private Object oReturn = null;
	private boolean lSuccess = false;

	public DAOGRLModulo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(int iCveUniMed) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcOGRLModulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLModulo vOGRLModulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = " select " + " iCveUniMed," + " iCveModulo,"
					+ " cDscModulo," + " cCalle," + " cColonia," + " iCP,"
					+ " cCiudad," + " iCvePais," + " iCveEstado,"
					+ " iCveMunicipio," + " cTel," + " cCorreoe,"
					+ " linterconsulta " + " from GRLModulo ";
			if (new Integer(iCveUniMed).toString().compareTo("") != 0)
				cSQL = cSQL + " where iCveUniMed = " + iCveUniMed + " ";
			cSQL = cSQL + "order by iCveUniMed, cdscmodulo";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vOGRLModulo = new TVGRLModulo();
				vOGRLModulo.setICveUniMed(rset.getInt(1));
				vOGRLModulo.setICveModulo(rset.getInt(2));
				vOGRLModulo.setCDscModulo(rset.getString(3));
				vOGRLModulo.setCCalle(rset.getString(4));
				vOGRLModulo.setCColonia(rset.getString(5));
				vOGRLModulo.setICP(rset.getInt(6));
				vOGRLModulo.setCCiudad(rset.getString(7));
				vOGRLModulo.setICvePais(rset.getInt(8));
				vOGRLModulo.setICveEstado(rset.getInt(9));
				vOGRLModulo.setICveMunicipio(rset.getInt(10));
				vOGRLModulo.setCTel(rset.getString(11));
				vOGRLModulo.setCCorreoe(rset.getString(12));
				vOGRLModulo.setLinterconsulta(rset.getInt(13));
				vcOGRLModulo.addElement(vOGRLModulo);
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
			return vcOGRLModulo;
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
		if (cCollName.compareTo("GRLModulo") == 0) {
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
