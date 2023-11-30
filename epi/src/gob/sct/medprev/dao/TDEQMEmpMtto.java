package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EQMEmpMtto DAO
 * </p>
 * <p>
 * Description: Calibraci�n de Equipo - Empresas de Mantenimiento
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

public class TDEQMEmpMtto extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEQMEmpMtto() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMEmpMtto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMEmpMtto vEQMEmpMtto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEmpMtto," + "cRFC," + "cCURP,"
					+ "cTpoPersona," + "cApPaterno," + "cApMaterno,"
					+ "cNombreRS," + "cDscEmpMtto," + "cContacto," + "cCalle,"
					+ "cColonia," + "cCiudad," + "iCvePais," + "iCveEstado,"
					+ "iCveMunicipio," + "iCP," + "cTel," + "cFax,"
					+ "cCorreoElec," + "lActivo" + " from EQMEmpMtto " + cWhere
					+ cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMEmpMtto = new TVEQMEmpMtto();
				vEQMEmpMtto.setICveEmpMtto(rset.getInt(1));
				vEQMEmpMtto.setCRFC(rset.getString(2));
				vEQMEmpMtto.setCCURP(rset.getString(3));
				vEQMEmpMtto.setCTpoPersona(rset.getString(4));
				vEQMEmpMtto.setCApPaterno(rset.getString(5));
				vEQMEmpMtto.setCApMaterno(rset.getString(6));
				vEQMEmpMtto.setCNombreRS(rset.getString(7));
				vEQMEmpMtto.setCDscEmpMtto(rset.getString(8));
				vEQMEmpMtto.setCContacto(rset.getString(9));
				vEQMEmpMtto.setCCalle(rset.getString(10));
				vEQMEmpMtto.setCColonia(rset.getString(11));
				vEQMEmpMtto.setCCiudad(rset.getString(12));
				vEQMEmpMtto.setICvePais(rset.getInt(13));
				vEQMEmpMtto.setICveEstado(rset.getInt(14));
				vEQMEmpMtto.setICveMunicipio(rset.getInt(15));
				vEQMEmpMtto.setICP(rset.getInt(16));
				vEQMEmpMtto.setCTel(rset.getString(17));
				vEQMEmpMtto.setCFax(rset.getString(18));
				vEQMEmpMtto.setCCorreoElec(rset.getString(19));
				vEQMEmpMtto.setLActivo(rset.getInt(20));
				vcEQMEmpMtto.addElement(vEQMEmpMtto);
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
			return vcEQMEmpMtto;
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
			TVEQMEmpMtto vEQMEmpMtto = (TVEQMEmpMtto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EQMEmpMtto(" + "iCveEmpMtto," + "cRFC,"
					+ "cCURP," + "cTpoPersona," + "cApPaterno," + "cApMaterno,"
					+ "cNombreRS," + "cDscEmpMtto," + "cContacto," + "cCalle,"
					+ "cColonia," + "cCiudad," + "iCvePais," + "iCveEstado,"
					+ "iCveMunicipio," + "iCP," + "cTel," + "cFax,"
					+ "cCorreoElec," + "lActivo"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveEmpMtto) from EQMEmpMtto";
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
			vEQMEmpMtto.setICveEmpMtto(iMax);
			// ******************************************************************
			pstmt.setInt(1, vEQMEmpMtto.getICveEmpMtto());
			pstmt.setString(2, vEQMEmpMtto.getCRFC().toUpperCase().trim());
			pstmt.setString(3, vEQMEmpMtto.getCCURP().toUpperCase().trim());
			pstmt.setString(4, vEQMEmpMtto.getCTpoPersona().toUpperCase()
					.trim());
			pstmt.setString(5, vEQMEmpMtto.getCApPaterno().toUpperCase().trim());
			pstmt.setString(6, vEQMEmpMtto.getCApMaterno().toUpperCase().trim());
			pstmt.setString(7, vEQMEmpMtto.getCNombreRS().toUpperCase().trim());
			pstmt.setString(8, vEQMEmpMtto.getCDscEmpMtto().toUpperCase()
					.trim());
			pstmt.setString(9, vEQMEmpMtto.getCContacto().toUpperCase().trim());
			pstmt.setString(10, vEQMEmpMtto.getCCalle().toUpperCase().trim());
			pstmt.setString(11, vEQMEmpMtto.getCColonia().toUpperCase().trim());
			pstmt.setString(12, vEQMEmpMtto.getCCiudad().toUpperCase().trim());
			pstmt.setInt(13, vEQMEmpMtto.getICvePais());
			pstmt.setInt(14, vEQMEmpMtto.getICveEstado());
			pstmt.setInt(15, vEQMEmpMtto.getICveMunicipio());
			pstmt.setInt(16, vEQMEmpMtto.getICP());
			pstmt.setString(17, vEQMEmpMtto.getCTel().toUpperCase().trim());
			pstmt.setString(18, vEQMEmpMtto.getCFax().toUpperCase().trim());
			pstmt.setString(19, vEQMEmpMtto.getCCorreoElec().toUpperCase()
					.trim());
			pstmt.setInt(20, vEQMEmpMtto.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vEQMEmpMtto.getICveEmpMtto();
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
			TVEQMEmpMtto vEQMEmpMtto = (TVEQMEmpMtto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMEmpMtto" + " set cRFC= ?, " + "cCURP= ?, "
					+ "cTpoPersona= ?, " + "cApPaterno= ?, "
					+ "cApMaterno= ?, " + "cNombreRS= ?, " + "cDscEmpMtto= ?, "
					+ "cContacto= ?, " + "cCalle= ?, " + "cColonia= ?, "
					+ "cCiudad= ?, " + "iCvePais= ?, " + "iCveEstado= ?, "
					+ "iCveMunicipio= ?, " + "iCP= ?, " + "cTel= ?, "
					+ "cFax= ?, " + "cCorreoElec= ?, " + "lActivo= ? "
					+ "where iCveEmpMtto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vEQMEmpMtto.getCRFC().toUpperCase().trim());
			pstmt.setString(2, vEQMEmpMtto.getCCURP().toUpperCase().trim());
			pstmt.setString(3, vEQMEmpMtto.getCTpoPersona().toUpperCase()
					.trim());
			pstmt.setString(4, vEQMEmpMtto.getCApPaterno().toUpperCase().trim());
			pstmt.setString(5, vEQMEmpMtto.getCApMaterno().toUpperCase().trim());
			pstmt.setString(6, vEQMEmpMtto.getCNombreRS().toUpperCase().trim());
			pstmt.setString(7, vEQMEmpMtto.getCDscEmpMtto().toUpperCase()
					.trim());
			pstmt.setString(8, vEQMEmpMtto.getCContacto().toUpperCase().trim());
			pstmt.setString(9, vEQMEmpMtto.getCCalle().toUpperCase().trim());
			pstmt.setString(10, vEQMEmpMtto.getCColonia().toUpperCase().trim());
			pstmt.setString(11, vEQMEmpMtto.getCCiudad().toUpperCase().trim());
			pstmt.setInt(12, vEQMEmpMtto.getICvePais());
			pstmt.setInt(13, vEQMEmpMtto.getICveEstado());
			pstmt.setInt(14, vEQMEmpMtto.getICveMunicipio());
			pstmt.setInt(15, vEQMEmpMtto.getICP());
			pstmt.setString(16, vEQMEmpMtto.getCTel().toUpperCase().trim());
			pstmt.setString(17, vEQMEmpMtto.getCFax().toUpperCase().trim());
			pstmt.setString(18, vEQMEmpMtto.getCCorreoElec().toUpperCase()
					.trim());
			pstmt.setInt(19, vEQMEmpMtto.getLActivo());
			pstmt.setInt(20, vEQMEmpMtto.getICveEmpMtto());
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
		PreparedStatement pstmtEnt = null;
		ResultSet rset = null;
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
			String cSQLEnt = "";
			TVEQMEmpMtto vEQMEmpMtto = (TVEQMEmpMtto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQLEnt = "select count(iCveEmpMtto) from  where iCveEmpMtto = ?";
			pstmtEnt = conn.prepareStatement(cSQLEnt);
			pstmtEnt.setInt(1, vEQMEmpMtto.getICveEmpMtto());
			rset = pstmtEnt.executeQuery();
			while (rset.next()) {
				iEntidades = rset.getInt(1);
			}
			if (iEntidades == 0) {
				cSQL = "delete from EQMEmpMtto" + " where iCveEmpMtto = ?";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEQMEmpMtto.getICveEmpMtto());
				pstmt.executeUpdate();
			}
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
				if (pstmtEnt != null) {
					pstmtEnt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("delete.closeEQMEmpMtto", ex2);
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
			TVEQMEmpMtto vEQMEmpMtto = (TVEQMEmpMtto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMEmpMtto" + " set lActivo= ? "
					+ "where iCveEmpMtto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vEQMEmpMtto.getICveEmpMtto());
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
