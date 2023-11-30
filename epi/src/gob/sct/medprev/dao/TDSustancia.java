package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de Sustancia DAO
 * </p>
 * <p>
 * Description: DAO TOXSustancia
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Eavalos
 * @version 1.0
 */

public class TDSustancia extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDSustancia() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String filtro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcSustancia = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVSustancia vSustancia;
			int count;
			cSQL = "select " + "iCveSustancia," + "cDscSustancia,"
					+ "cTitRepConf," + "cPrefLoteConf," + "cAbrvEq, "
					+ "lActivo," + "lAnalizada, " + "lPositiva, " + "dIon01, "
					+ "dIon02, " + "dIon03, " + "dIon04, " + "dIon05, "
					+ "iMuestrasLC, " + "cSustUnifica, " + "cAbrevEqAC, "
					+ " lValidaCtrol, " + " lValConcentracion "
					+ " from TOXSustancia " + filtro;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vSustancia = new TVSustancia();
				vSustancia.setICveSustancia(rset.getInt(1));
				vSustancia.setCDscSustancia(rset.getString(2));
				vSustancia.setCTitRepConf(rset.getString(3));
				vSustancia.setCPrefLoteConf(rset.getString(4));
				vSustancia.setCAbrvEq(rset.getString(5));
				vSustancia.setLActivo(rset.getInt(6));
				vSustancia.setLAnalizada(rset.getInt(7));
				vSustancia.setLPositiva(rset.getInt(8));
				vSustancia.setDIon01(rset.getDouble(9));
				vSustancia.setDIon02(rset.getDouble(10));
				vSustancia.setDIon03(rset.getDouble(11));
				vSustancia.setDIon04(rset.getDouble(12));
				vSustancia.setDIon05(rset.getDouble(13));
				vSustancia.setIMuestrasLC(rset.getInt(14));
				vSustancia.setCSustUnifica(rset.getString("cSustUnifica"));
				vSustancia.setCAbrevEqAC(rset.getString("cAbrevEqAC"));
				vSustancia.setLValidaCtrol(rset.getInt("lValidaCtrol"));
				vSustancia.setLValConcentracion(rset
						.getInt("lValConcentracion"));
				vcSustancia.addElement(vSustancia);
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
			return vcSustancia;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
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
			TVSustancia vSustancia = (TVSustancia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXSustancia(" + "iCveSustancia,"
					+ "cDscSustancia," + "cTitRepConf,  " + "cPrefLoteConf,"
					+ "cAbrvEq," + "lActivo," + "lAnalizada, " + "lPositiva,  "
					+ "iMuestrasLC, " + "cSustUnifica, "
					+ "cAbrevEqAC, lValidaCtrol, lValConcentracion "
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cNewClave = "SELECT MAX(ICVESUSTANCIA) FROM TOXSUSTANCIA";
			PreparedStatement psNewCve = conn.prepareStatement(cNewClave);
			ResultSet rsNewClave = psNewCve.executeQuery();
			int newCve = 0;
			if (rsNewClave.next()) {
				newCve = rsNewClave.getInt(1) + 1;
			}
			rsNewClave.close();
			psNewCve.close();

			pstmt.setInt(1, newCve);
			pstmt.setString(2, vSustancia.getCDscSustancia());
			pstmt.setString(3, vSustancia.getCTitRepConf());
			pstmt.setString(4, vSustancia.getCPrefLoteConf());
			pstmt.setString(5, vSustancia.getCAbrvEq());
			pstmt.setInt(6, vSustancia.getLActivo());
			pstmt.setInt(7, vSustancia.getLAnalizada());
			pstmt.setInt(8, vSustancia.getLPositiva());
			pstmt.setInt(9, vSustancia.getIMuestrasLC());
			pstmt.setString(10, vSustancia.getCSustUnifica());
			pstmt.setString(11, vSustancia.getCAbrevEqAC());
			pstmt.setInt(12, vSustancia.getLValidaCtrol());
			pstmt.setInt(13, vSustancia.getLValConcentracion());
			pstmt.executeUpdate();
			cClave = "" + newCve;
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
			TVSustancia vSustancia = (TVSustancia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXSustancia " + " set cDscSustancia= ?, "
					+ "cTitRepConf= ?,   " + "cPrefLoteConf= ?, "
					+ "cAbrvEq = ?, " + "lActivo= ?,  " + "lAnalizada= ?, "
					+ "lPositiva= ?,  " + "iMuestrasLC  = ? , "
					+ "cSustUnifica = ? , " + "cAbrevEqAC   = ? , "
					+ "lValidaCtrol = ?,  " + " lValConcentracion = ? "
					+ "where iCveSustancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vSustancia.getCDscSustancia());
			pstmt.setString(2, vSustancia.getCTitRepConf());
			pstmt.setString(3, vSustancia.getCPrefLoteConf());
			pstmt.setString(4, vSustancia.getCAbrvEq());
			pstmt.setInt(5, vSustancia.getLActivo());
			pstmt.setInt(6, vSustancia.getLAnalizada());
			pstmt.setInt(7, vSustancia.getLPositiva());
			pstmt.setInt(8, vSustancia.getIMuestrasLC());
			pstmt.setString(9, vSustancia.getCSustUnifica());
			pstmt.setString(10, vSustancia.getCAbrevEqAC());
			pstmt.setInt(11, vSustancia.getLValidaCtrol());
			pstmt.setInt(12, vSustancia.getLValConcentracion());
			pstmt.setInt(13, vSustancia.getICveSustancia());
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
			TVSustancia vSustancia = (TVSustancia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXSustancia" + " where iCveSustancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vSustancia.getICveSustancia());
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
				warn("delete.closeSustancia", ex2);
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
			TVSustancia vSustancia = (TVSustancia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXSustancia" + " set lActivo= ? "
					+ "where iCveSustancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vSustancia.getICveSustancia());
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
