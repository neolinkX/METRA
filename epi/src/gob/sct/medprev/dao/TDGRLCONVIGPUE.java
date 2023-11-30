/* <p>Administracion y Seguridad generada en JAVA (J2EE).
 * @version 1.0
 * <p>
 * @author <dd>AG SA L.
 * <p> 
 */
    
package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

import com.micper.util.TFechas;

/**
 * <p>
 * Title: Data Acces Object de EXPExamCat DAO
 * </p>
 * <p>
 * Description: Data Access Object para EXPExamCat
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */

public class TDGRLCONVIGPUE extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros.getPropEspecifica("ConDBModulo");

	public TDGRLCONVIGPUE() {
	}

	/**
	 * Método Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLCONVIGPUE = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLCONVIGPUE vGRLCONVIGPUE;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT ICVEMDOTRANS, " + "ICVECATEGORIA, " + "ICVEPUESTO, " + "IEDMAYOR, " + "IEDMENOR, "
					+ "ITMPVIGENCIA " + "FROM GRLCONVIGPUE " + cWhere;
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLCONVIGPUE = new TVGRLCONVIGPUE();
				vGRLCONVIGPUE.setICveMdoTrans(rset.getInt(1));
				vGRLCONVIGPUE.setICveCategoria(rset.getInt(2));
				vGRLCONVIGPUE.setICvePuesto(rset.getInt(3));
				vGRLCONVIGPUE.setIEdMayor(rset.getInt(4));
				vGRLCONVIGPUE.setIEdMenor(rset.getInt(5));
				vGRLCONVIGPUE.setITmpVigencia(rset.getInt(6));
				vGRLCONVIGPUE.setIanosVigencia(rset.getInt(6));
				vGRLCONVIGPUE.setIMesesVigencia(rset.getInt(6));
				vcGRLCONVIGPUE.addElement(vGRLCONVIGPUE);
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
			return vcGRLCONVIGPUE;
		}
	}

	/**
	 * Método Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLCONVIGPUE = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLCONVIGPUE vGRLCONVIGPUE;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT ICVEMDOTRANS, " + "ICVECATEGORIA, " + "ICVEPUESTO, " + "IEDMAYOR, " + "IEDMENOR, "
					+ "ITMPVIGENCIA, " + "LACTIVO, " + "IORDEN " + "FROM GRLCONVIGPUE " + cWhere + "  " + cOrderBy;
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLCONVIGPUE = new TVGRLCONVIGPUE();
				vGRLCONVIGPUE.setICveMdoTrans(rset.getInt(1));
				vGRLCONVIGPUE.setICveCategoria(rset.getInt(2));
				vGRLCONVIGPUE.setICvePuesto(rset.getInt(3));
				vGRLCONVIGPUE.setIEdMayor(rset.getInt(4));
				vGRLCONVIGPUE.setIEdMenor(rset.getInt(5));
				vGRLCONVIGPUE.setITmpVigencia(rset.getInt(6));

				vGRLCONVIGPUE.setIanosVigencia(rset.getInt(6));

				vGRLCONVIGPUE.setIMesesVigencia(rset.getInt(6));
				vGRLCONVIGPUE.setLActivo(rset.getInt(7));
				vGRLCONVIGPUE.setIOrden(rset.getInt(8));
				vcGRLCONVIGPUE.addElement(vGRLCONVIGPUE);
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
			return vcGRLCONVIGPUE;
		}
	}

	/**
	 * Método que regresa un entero
	 * 
	 * @Autor: AG SA
	 */
	public int RegresaInt(String SQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int regreso = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(SQL.toString());
			// System.out.println(SQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				if (rset.getString(1) != null)
					regreso = rset.getInt(1);
				else
					regreso = 0;
			}

		} catch (Exception ex) {
			warn("RegresaInt", ex);
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
				warn("RegresaInt", ex2);
			}
			return regreso;
		}
	}

	/**
	 * M�todo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			// Calculando Timestamp
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);

			TVGRLCONVIGPUE vGRLCONVIGPUE = (TVGRLCONVIGPUE) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLCONVIGPUE(" + "iCveMdoTrans, " + "iCveCategoria,   " + "iCvePuesto," + "iEdMayor,"
					+ "iEdMenor," + "iTmpVigencia," + "iCveUsuGenera," + "lActivo," + "cDscConVigPue," + "iOrden,"
					+ "tIGenerado" + ")values(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CODIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			String cSQLMax = "select max(iOrden) from GRLCONVIGPUE where " + " iCveMdoTrans = "
					+ vGRLCONVIGPUE.getICveMdoTrans() + " and iCveCategoria = " + vGRLCONVIGPUE.getICveCategoria()
					+ " and iCvePuesto = " + vGRLCONVIGPUE.getICvePuesto();

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
			vGRLCONVIGPUE.setIOrden(iMax);
			// ******************************************************************

			pstmt.setInt(1, vGRLCONVIGPUE.getICveMdoTrans());
			pstmt.setInt(2, vGRLCONVIGPUE.getICveCategoria());
			pstmt.setInt(3, vGRLCONVIGPUE.getICvePuesto());
			pstmt.setInt(4, vGRLCONVIGPUE.getIEdMayor());
			pstmt.setInt(5, vGRLCONVIGPUE.getIEdMenor());
			pstmt.setInt(6, vGRLCONVIGPUE.getITmpVigencia());
			pstmt.setInt(7, vGRLCONVIGPUE.getICveUsuGenera());
			pstmt.setInt(8, vGRLCONVIGPUE.getLActivo());
			pstmt.setString(9, "");
			pstmt.setInt(10, vGRLCONVIGPUE.getIOrden());
			pstmt.setTimestamp(11, sqlTimestamp);

			pstmt.executeUpdate();
			cClave = "" + vGRLCONVIGPUE.getICvePuesto();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
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
	 * M�todo update
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
			TVGRLCONVIGPUE vGRLCONVIGPUE = (TVGRLCONVIGPUE) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLCONVIGPUE" + " set iEdMayor    = ?, " + "     iEdMenor    = ?, "
					+ "     iTmpVigencia    = ?, " + "     lActivo    = ? " + " where iCveMdoTrans = ? "
					+ "   and iCveCategoria   = ? " + "   and iCvePuesto   = ? " + "   and iOrden   = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLCONVIGPUE.getIEdMayor());
			pstmt.setInt(2, vGRLCONVIGPUE.getIEdMenor());
			pstmt.setInt(3, vGRLCONVIGPUE.getITmpVigencia());
			pstmt.setInt(4, vGRLCONVIGPUE.getLActivo());
			pstmt.setInt(5, vGRLCONVIGPUE.getICveMdoTrans());
			pstmt.setInt(6, vGRLCONVIGPUE.getICveCategoria());
			pstmt.setInt(7, vGRLCONVIGPUE.getICvePuesto());
			pstmt.setInt(8, vGRLCONVIGPUE.getIOrden());
			/*
			 * System.out.println("Actualizando reglas vigencia");
			 * System.out.println(cSQL);
			 * System.out.println(vGRLCONVIGPUE.getIEdMayor());
			 * System.out.println(vGRLCONVIGPUE.getIEdMenor());
			 * System.out.println(vGRLCONVIGPUE.getITmpVigencia());
			 * System.out.println(vGRLCONVIGPUE.getLActivo());
			 * System.out.println(vGRLCONVIGPUE.getICveMdoTrans());
			 * System.out.println(vGRLCONVIGPUE.getICveCategoria());
			 * System.out.println(vGRLCONVIGPUE.getICvePuesto());
			 * System.out.println(vGRLCONVIGPUE.getIOrden());
			 * System.out.println("==============================");
			 */

			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
	 * M�todo Delete
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
			TVGRLCONVIGPUE vGRLCONVIGPUE = (TVGRLCONVIGPUE) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLCONVIGPUE " + " where iCveMdoTrans = ?" + " and iCveCategoria = ?"
					+ " and iCvePuesto = ?" + " and iOrden = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLCONVIGPUE.getICveMdoTrans());
			pstmt.setInt(2, vGRLCONVIGPUE.getICveCategoria());
			pstmt.setInt(3, vGRLCONVIGPUE.getICvePuesto());
			pstmt.setInt(4, vGRLCONVIGPUE.getIOrden());
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
				warn("delete.closeGRLPuesto", ex2);
			}
			return cClave;
		}
	}

}
