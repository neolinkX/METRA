package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de VEHConfControl DAO
 * </p>
 * <p>
 * Description: Control de Veh�culos - Controles por Etapa
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */

public class TDVEHConfControl extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDVEHConfControl() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHConfControl = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHConfControl vVEHConfControl;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEtapaSolic," + "iCveConfControl,"
					+ "cDscTpoResp," + "cDscBreve," + "cEtiqueta,"
					+ "iCveTpoResp," + "lObligatorio," + "lActivo," + "iOrden"
					+ " from VEHConfControl order by iCveEtapaSolic";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHConfControl = new TVVEHConfControl();
				vVEHConfControl.setICveEtapaSolic(rset.getInt(1));
				vVEHConfControl.setICveConfControl(rset.getInt(2));
				vVEHConfControl.setCDscTpoResp(rset.getString(3));
				vVEHConfControl.setCDscBreve(rset.getString(4));
				vVEHConfControl.setCEtiqueta(rset.getString(5));
				vVEHConfControl.setICveTpoResp(rset.getInt(6));
				vVEHConfControl.setLObligatorio(rset.getInt(7));
				vVEHConfControl.setLActivo(rset.getInt(8));
				vVEHConfControl.setIOrden(rset.getInt(9));
				vcVEHConfControl.addElement(vVEHConfControl);
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
			return vcVEHConfControl;
		}
	}

	/**
	 * Metodo Find By Dsc
	 */
	public Vector FindDsc(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHConfControl = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHConfControl vVEHConfControl;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "        VEHConfControl.iCveEtapaSolic, "
					+ "        iCveConfControl, "
					+ "        VEHConfControl.cDscBreve, "
					+ "        cEtiqueta, "
					+ "        VEHConfControl.iCveTpoResp, "
					+ "        lObligatorio, "
					+ "        VEHConfControl.lActivo, "
					+ "        VEHConfControl.iOrden, "
					+ "        MEDTpoResp.cDscTpoResp, "
					+ "        VEHConfControl.cDscTpoResp "
					+ "from    VEHConfControl "
					+ "        join VEHEtapaSolic on VEHEtapaSolic.iCveEtapaSolic = VEHConfControl.iCveEtapaSolic "
					+ "        join MEDTpoResp on MEDTpoResp.iCvetpoResp = VEHConfControl.iCveTpoResp "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHConfControl = new TVVEHConfControl();
				vVEHConfControl.setICveEtapaSolic(rset.getInt(1));
				vVEHConfControl.setICveConfControl(rset.getInt(2));
				vVEHConfControl.setCDscBreve(rset.getString(3));
				vVEHConfControl.setCEtiqueta(rset.getString(4));
				vVEHConfControl.setICveTpoResp(rset.getInt(5));
				vVEHConfControl.setLObligatorio(rset.getInt(6));
				vVEHConfControl.setLActivo(rset.getInt(7));
				vVEHConfControl.setIOrden(rset.getInt(8));
				vVEHConfControl.setCDscTpoResp(rset.getString(9));
				vVEHConfControl.setCDscTpoResp2(rset.getString(10));
				vcVEHConfControl.addElement(vVEHConfControl);
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
			return vcVEHConfControl;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVVEHConfControl vVEHConfControl = (TVVEHConfControl) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into VEHConfControl(" + "iCveEtapaSolic,"
					+ "iCveConfControl," + "cDscTpoResp," + "cDscBreve,"
					+ "cEtiqueta," + "iCveTpoResp," + "lObligatorio,"
					+ "lActivo," + "iOrden" + ")values(?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveConfControl) from VEHConfControl where iCveEtapaSolic = "
					+ vVEHConfControl.getICveEtapaSolic();
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			if (iMax == 0) {
				iMax = 1;
			} else {
				iMax += 1;
			}
			vVEHConfControl.setICveConfControl(iMax);
			// ******************************************************************
			pstmt.setInt(1, vVEHConfControl.getICveEtapaSolic());
			pstmt.setInt(2, vVEHConfControl.getICveConfControl());
			pstmt.setString(3, vVEHConfControl.getCDscTpoResp().toUpperCase()
					.trim());
			pstmt.setString(4, vVEHConfControl.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setString(5, vVEHConfControl.getCEtiqueta().toUpperCase()
					.trim());
			pstmt.setInt(6, vVEHConfControl.getICveTpoResp());
			pstmt.setInt(7, vVEHConfControl.getLObligatorio());
			pstmt.setInt(8, vVEHConfControl.getLActivo());
			pstmt.setInt(9, vVEHConfControl.getIOrden());
			pstmt.executeUpdate();
			cClave = "" + vVEHConfControl.getICveConfControl();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insert", ex1);
			}
			warn("insert", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo update
	 */
	public Object update(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVVEHConfControl vVEHConfControl = (TVVEHConfControl) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VEHConfControl" + " set cDscTpoResp= ?, "
					+ "cDscBreve= ?, " + "cEtiqueta= ?, " + "iCveTpoResp= ?, "
					+ "lObligatorio= ?, " + "lActivo= ?, " + "iOrden= ? "
					+ "where iCveEtapaSolic = ? " + " and iCveConfControl = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vVEHConfControl.getCDscTpoResp().toUpperCase()
					.trim());
			pstmt.setString(2, vVEHConfControl.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setString(3, vVEHConfControl.getCEtiqueta().toUpperCase()
					.trim());
			pstmt.setInt(4, vVEHConfControl.getICveTpoResp());
			pstmt.setInt(5, vVEHConfControl.getLObligatorio());
			pstmt.setInt(6, vVEHConfControl.getLActivo());
			pstmt.setInt(7, vVEHConfControl.getIOrden());
			pstmt.setInt(8, vVEHConfControl.getICveEtapaSolic());
			pstmt.setInt(9, vVEHConfControl.getICveConfControl());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("update", ex1);
			}
			warn("update", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Delete
	 */
	public Object delete(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int iEntidades = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVVEHConfControl vVEHConfControl = (TVVEHConfControl) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from VEHConfControl" + " where iCveEtapaSolic = ?"
					+ " and iCveConfControl = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHConfControl.getICveEtapaSolic());
			pstmt.setInt(2, vVEHConfControl.getICveConfControl());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("delete", ex1);
			}
			warn("delete", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("delete.closeVEHConfControl", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Disable
	 */
	public Object disable(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVVEHConfControl vVEHConfControl = (TVVEHConfControl) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VEHConfControl" + " set lActivo= ? "
					+ "where iCveEtapaSolic = ?" + " and iCveConfControl = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vVEHConfControl.getICveEtapaSolic());
			pstmt.setInt(3, vVEHConfControl.getICveConfControl());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("update", ex1);
			}
			warn("update", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("disable.close", ex2);
			}
			return cClave;
		}
	}
}