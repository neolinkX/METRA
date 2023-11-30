package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de CTRPeriodPla DAO
 * </p>
 * <p>
 * Description: Data Access Object de la Tabla CTRPeriodPla
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */

public class TDCTRPeriodPla extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDCTRPeriodPla() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRPeriodPla = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRPeriodPla vCTRPeriodPla;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCvePeriodPla,"
					+ "iCveTpoPlantilla," + "cDscPeriodPla," + "cObservacion,"
					+ "dtGeneracion," + "dtVencimiento," + "lActivo"
					+ " from CTRPeriodPla " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRPeriodPla = new TVCTRPeriodPla();
				vCTRPeriodPla.setiAnio(rset.getInt(1));
				vCTRPeriodPla.setiCvePeriodPla(rset.getInt(2));
				vCTRPeriodPla.setiCveTpoPlantilla(rset.getInt(3));
				vCTRPeriodPla.setcDscPeriodPla(rset.getString(4));
				vCTRPeriodPla.setcObservacion(rset.getString(5));
				vCTRPeriodPla.setdtGeneracion(rset.getDate(6));
				vCTRPeriodPla.setdtVencimiento(rset.getDate(7));
				vCTRPeriodPla.setlActivo(rset.getInt(8));
				vcCTRPeriodPla.addElement(vCTRPeriodPla);
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
			return vcCTRPeriodPla;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRPeriodPla = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRPeriodPla vCTRPeriodPla;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "        iAnio, "
					+ "        iCvePeriodPla, "
					+ "        CTRTpoPlantilla.iCveTpoPlantilla, "
					+ "        cDscPeriodPla, "
					+ "        cObservacion, "
					+ "        dtGeneracion, "
					+ "        dtVencimiento, "
					+ "        CTRPeriodPla.lActivo, "
					+ "        CTRTpoPlantilla.cDscTpoPlantilla, "
					+ "        CTRTpoPlantilla.cDscBreve "
					+ "from    CTRPeriodPla "
					+ "        join CTRTpoPlantilla on CTRTpoPlantilla.iCveTpoPlantilla = CTRPeriodPla.iCveTpoPlantilla "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRPeriodPla = new TVCTRPeriodPla();
				vCTRPeriodPla.setiAnio(rset.getInt(1));
				vCTRPeriodPla.setiCvePeriodPla(rset.getInt(2));
				vCTRPeriodPla.setiCveTpoPlantilla(rset.getInt(3));
				vCTRPeriodPla.setcDscPeriodPla(rset.getString(4));
				vCTRPeriodPla.setcObservacion(rset.getString(5));
				vCTRPeriodPla.setdtGeneracion(rset.getDate(6));
				vCTRPeriodPla.setdtVencimiento(rset.getDate(7));
				vCTRPeriodPla.setlActivo(rset.getInt(8));
				vCTRPeriodPla.setcDscTpoPlantilla(rset.getString(9));
				vCTRPeriodPla.setcDscBreve(rset.getString(10));

				java.util.Date dtFechaTmp = new java.util.Date();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				dtFechaTmp = rset.getDate(6);
				vCTRPeriodPla.setcdtGeneracion(formato.format(dtFechaTmp));
				dtFechaTmp = rset.getDate(7);
				vCTRPeriodPla.setcdtVencimiento(formato.format(dtFechaTmp));

				vcCTRPeriodPla.addElement(vCTRPeriodPla);
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
			return vcCTRPeriodPla;
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
			TVCTRPeriodPla vCTRPeriodPla = (TVCTRPeriodPla) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into CTRPeriodPla(" + "iAnio," + "iCvePeriodPla,"
					+ "iCveTpoPlantilla," + "cDscPeriodPla," + "cObservacion,"
					+ "dtGeneracion," + "dtVencimiento," + "lActivo"
					+ ")values(?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCvePeriodPla) from CTRPeriodPla";
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
			vCTRPeriodPla.setiCvePeriodPla(iMax);
			// ******************************************************************
			pstmt.setInt(1, vCTRPeriodPla.getiAnio());
			pstmt.setInt(2, vCTRPeriodPla.getiCvePeriodPla());
			pstmt.setInt(3, vCTRPeriodPla.getiCveTpoPlantilla());
			pstmt.setString(4, vCTRPeriodPla.getcDscPeriodPla());
			pstmt.setString(5, vCTRPeriodPla.getcObservacion());
			pstmt.setDate(6, vCTRPeriodPla.getdtGeneracion());
			pstmt.setDate(7, vCTRPeriodPla.getdtVencimiento());
			pstmt.setInt(8, vCTRPeriodPla.getlActivo());
			pstmt.executeUpdate();
			cClave = "" + vCTRPeriodPla.getiCvePeriodPla();
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
			TVCTRPeriodPla vCTRPeriodPla = (TVCTRPeriodPla) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update CTRPeriodPla" + " set iCveTpoPlantilla= ?, "
					+ "cDscPeriodPla= ?, " + "cObservacion= ?, "
					+ "dtGeneracion= ?, " + "dtVencimiento= ?, "
					+ "lActivo= ? " + "where iAnio = ? "
					+ " and iCvePeriodPla = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCTRPeriodPla.getiCveTpoPlantilla());
			pstmt.setString(2, vCTRPeriodPla.getcDscPeriodPla());
			pstmt.setString(3, vCTRPeriodPla.getcObservacion());
			pstmt.setDate(4, vCTRPeriodPla.getdtGeneracion());
			pstmt.setDate(5, vCTRPeriodPla.getdtVencimiento());
			pstmt.setInt(6, vCTRPeriodPla.getlActivo());
			pstmt.setInt(7, vCTRPeriodPla.getiAnio());
			pstmt.setInt(8, vCTRPeriodPla.getiCvePeriodPla());
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
			TVCTRPeriodPla vCTRPeriodPla = (TVCTRPeriodPla) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from CTRPeriodPla" + " where iAnio = ?"
					+ " and iCvePeriodPla = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCTRPeriodPla.getiAnio());
			pstmt.setInt(2, vCTRPeriodPla.getiCvePeriodPla());
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
				warn("delete.closeCTRPeriodPla2", ex2);
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
			TVCTRPeriodPla vCTRPeriodPla = (TVCTRPeriodPla) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update CTRPeriodPla" + " set lActivo= ? "
					+ "where iAnio = ?" + " and iCvePeriodPla = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vCTRPeriodPla.getiAnio());
			pstmt.setInt(3, vCTRPeriodPla.getiCvePeriodPla());
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
