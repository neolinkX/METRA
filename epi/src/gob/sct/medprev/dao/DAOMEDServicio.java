package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.dao.*;

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

public class DAOMEDServicio extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private Object oReturn = null;
	private boolean lSuccess = false;

	public DAOMEDServicio() {
	}

	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcOMEDServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDServicio vMEDServicio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveServicio," + "cDscServicio "
					+ " from MEDServicio order by iCveServicio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lSuccess = true;
				vMEDServicio = new TVMEDServicio();
				vMEDServicio.setICveServicio(rset.getInt(1));
				vMEDServicio.setCDscServicio(rset.getString(2));
				vcOMEDServicio.addElement(vMEDServicio);
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
			return vcOMEDServicio;
		}
	}

	public Vector FindByProcesoMotivo(int iCveProceso, int iCveMotivo)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcOTOXSustancia = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDServicio vMEDServicio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select MEDServicio.iCveServicio, "
					+ "       MEDServicio.cDscServicio "
					+ " from MEDServExamen "
					+ " join GRLMotivo "
					+ " on   MEDServExamen.iCveProceso = GRLMotivo.iCveProceso "
					+ " and  MEDServExamen.iCveMotivo = GRLMotivo.iCveMotivo  "
					+ " and  GRLMotivo.lActivo = 1 "
					+ " join MEDServicio "
					+ " on   MEDServExamen.iCveServicio = MEDServicio.iCveServicio "
					+ " and  MEDServicio.lActivo = 1 "
					+ " where MEDServExamen.iCveProceso = "
					+ iCveProceso
					+ " and   MEDServExamen.iCveMotivo = "
					+ iCveMotivo
					+ " order by MEDServExamen.iCveProceso, MEDServExamen.iCveMotivo";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lSuccess = true;
				vMEDServicio = new TVMEDServicio();
				vMEDServicio.setICveServicio(rset.getInt(1));
				vMEDServicio.setCDscServicio(rset.getString(2));
				vcOTOXSustancia.addElement(vMEDServicio);
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
		// System.out.println("cCollName === "+cCollName);
		if (cCollName.compareTo("MEDServicio") == 0) {
			try {
				StringTokenizer stKey = new StringTokenizer(cKey, "|");
				DAOMEDServicioTmp aOMEDServicio = new DAOMEDServicioTmp();
				// oReturn =
				// this.FindByProcesoMotivo(Integer.parseInt(stKey.nextToken(),10),Integer.parseInt(stKey.nextToken(),10));
				oReturn = aOMEDServicio.FindByProcesoMotivo(
						Integer.parseInt(stKey.nextToken(), 10),
						Integer.parseInt(stKey.nextToken(), 10));
			} catch (Exception e) {
				warn("findCollection:" + cCollName + '.' + cKey, e);
				lSuccess = false;
			}
		}

		if (cCollName.compareTo("MEDServAll") == 0) {
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

	public TVMEDServicio FindById(String cveServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVMEDServicio vMEDServicio = new TVMEDServicio();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select " + "iCveServicio," + "cDscServicio "
					+ " from MEDServicio where iCveServicio = " + cveServicio;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lSuccess = true;
				vMEDServicio = new TVMEDServicio();
				vMEDServicio.setICveServicio(rset.getInt(1));
				vMEDServicio.setCDscServicio(rset.getString(2));
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindById");
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
			return vMEDServicio;
		}
	}

}