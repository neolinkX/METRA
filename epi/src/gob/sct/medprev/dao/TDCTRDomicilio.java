package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de CTRDomicilio DAO
 * </p>
 * <p>
 * Description: Control al Transporte - Domicilios de las Empresas
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

public class TDCTRDomicilio extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDCTRDomicilio() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRDomicilio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRDomicilio vCTRDomicilio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEmpresa," + "iCveDomicilio," + "cCalle,"
					+ "cColonia," + "cCiudad," + "iCP," + "iCvePais,"
					+ "iCveEstado," + "iCveMunicipio," + "cTel," + "cFax,"
					+ "cCorreoElec," + "lActivo" + " from CTRDomicilio "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRDomicilio = new TVCTRDomicilio();
				vCTRDomicilio.setICveEmpresa(rset.getInt(1));
				vCTRDomicilio.setICveDomicilio(rset.getInt(2));
				vCTRDomicilio.setCCalle(rset.getString(3));
				vCTRDomicilio.setCColonia(rset.getString(4));
				vCTRDomicilio.setCCiudad(rset.getString(5));
				vCTRDomicilio.setICP(rset.getInt(6));
				vCTRDomicilio.setICvePais(rset.getInt(7));
				vCTRDomicilio.setICveEstado(rset.getInt(8));
				vCTRDomicilio.setICveMunicipio(rset.getInt(9));
				vCTRDomicilio.setCTel(rset.getString(10));
				vCTRDomicilio.setCFax(rset.getString(11));
				vCTRDomicilio.setCCorreoElec(rset.getString(12));
				vCTRDomicilio.setLActivo(rset.getInt(13));
				vcCTRDomicilio.addElement(vCTRDomicilio);
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
			return vcCTRDomicilio;
		}
	}

	public Vector FindByCompleto(String cWhere, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRDomicilio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRDomicilio vCTRDomicilio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "iCveEmpresa,"
					+ "iCveDomicilio,"
					+ "cCalle,"
					+ "cColonia,"
					+ "cCiudad,"
					+ "iCP,"
					+ "CTRDomicilio.iCvePais,"
					+ "CTRDomicilio.iCveEstado,"
					+ "CTRDomicilio.iCveMunicipio,"
					+ "cTel,"
					+ "cFax,"
					+ "cCorreoElec,"
					+ "lActivo, "
					+ "GRLPais.cNombre,"
					+ "GRLEntidadFed.cNombre,"
					+ "GRLMunicipio.cNombre"
					+ " from CTRDomicilio "
					+ " join GRLPais on GRLPais.iCvePais = CTRDomicilio.iCvePais"
					+ " join GRLEntidadFed on GRLEntidadFed.iCvePais = CTRDomicilio.iCvePais"
					+ "                   and GRLEntidadFed.iCveEntidadFed = CTRDomicilio.iCveEstado"
					+ " join GRLMunicipio on GRLMunicipio.iCvePais = CTRDomicilio.iCvePais"
					+ "                  and GRLMunicipio.iCveEntidadFed = CTRDomicilio.iCveEstado"
					+ "                  and GRLMunicipio.iCveMunicipio =  CTRDomicilio.iCveMunicipio "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRDomicilio = new TVCTRDomicilio();
				vCTRDomicilio.setICveEmpresa(rset.getInt(1));
				vCTRDomicilio.setICveDomicilio(rset.getInt(2));
				vCTRDomicilio.setCCalle(rset.getString(3));
				vCTRDomicilio.setCColonia(rset.getString(4));
				vCTRDomicilio.setCCiudad(rset.getString(5));
				vCTRDomicilio.setICP(rset.getInt(6));
				vCTRDomicilio.setICvePais(rset.getInt(7));
				vCTRDomicilio.setICveEstado(rset.getInt(8));
				vCTRDomicilio.setICveMunicipio(rset.getInt(9));
				vCTRDomicilio.setCTel(rset.getString(10));
				vCTRDomicilio.setCFax(rset.getString(11));
				vCTRDomicilio.setCCorreoElec(rset.getString(12));
				vCTRDomicilio.setLActivo(rset.getInt(13));
				vCTRDomicilio.setcNombrePais(rset.getString(14));
				vCTRDomicilio.setcNombreEntidad(rset.getString(15));
				vCTRDomicilio.setcNombreMunicipio(rset.getString(16));
				vcCTRDomicilio.addElement(vCTRDomicilio);
			}
		} catch (Exception ex) {
			warn("FindByCompleto", ex);
			throw new DAOException("FindByCompleto");
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
				warn("FindByCompleto.close", ex2);
			}
			return vcCTRDomicilio;
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
			TVCTRDomicilio vCTRDomicilio = (TVCTRDomicilio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into CTRDomicilio(" + "iCveEmpresa,"
					+ "iCveDomicilio," + "cCalle," + "cColonia," + "cCiudad,"
					+ "iCP," + "iCvePais," + "iCveEstado," + "iCveMunicipio,"
					+ "cTel," + "cFax," + "cCorreoElec," + "lActivo"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveDomicilio) from CTRDomicilio where iCveEmpresa = "
					+ vCTRDomicilio.getICveEmpresa();
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
			vCTRDomicilio.setICveDomicilio(iMax);
			// ******************************************************************
			pstmt.setInt(1, vCTRDomicilio.getICveEmpresa());
			pstmt.setInt(2, vCTRDomicilio.getICveDomicilio());
			pstmt.setString(3, vCTRDomicilio.getCCalle());
			pstmt.setString(4, vCTRDomicilio.getCColonia());
			pstmt.setString(5, vCTRDomicilio.getCCiudad());
			pstmt.setInt(6, vCTRDomicilio.getICP());
			pstmt.setInt(7, vCTRDomicilio.getICvePais());
			pstmt.setInt(8, vCTRDomicilio.getICveEstado());
			pstmt.setInt(9, vCTRDomicilio.getICveMunicipio());
			pstmt.setString(10, vCTRDomicilio.getCTel());
			pstmt.setString(11, vCTRDomicilio.getCFax());
			pstmt.setString(12, vCTRDomicilio.getCCorreoElec());
			pstmt.setInt(13, vCTRDomicilio.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vCTRDomicilio.getICveDomicilio();
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
			TVCTRDomicilio vCTRDomicilio = (TVCTRDomicilio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update CTRDomicilio" + " set cCalle= ?, " + "cColonia= ?, "
					+ "cCiudad= ?, " + "iCP= ?, " + "iCvePais= ?, "
					+ "iCveEstado= ?, " + "iCveMunicipio= ?, " + "cTel= ?, "
					+ "cFax= ?, " + "cCorreoElec= ?, " + "lActivo= ? "
					+ "where iCveEmpresa = ? " + " and iCveDomicilio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vCTRDomicilio.getCCalle());
			pstmt.setString(2, vCTRDomicilio.getCColonia());
			pstmt.setString(3, vCTRDomicilio.getCCiudad());
			pstmt.setInt(4, vCTRDomicilio.getICP());
			pstmt.setInt(5, vCTRDomicilio.getICvePais());
			pstmt.setInt(6, vCTRDomicilio.getICveEstado());
			pstmt.setInt(7, vCTRDomicilio.getICveMunicipio());
			pstmt.setString(8, vCTRDomicilio.getCTel());
			pstmt.setString(9, vCTRDomicilio.getCFax());
			pstmt.setString(10, vCTRDomicilio.getCCorreoElec());
			pstmt.setInt(11, vCTRDomicilio.getLActivo());
			pstmt.setInt(12, vCTRDomicilio.getICveEmpresa());
			pstmt.setInt(13, vCTRDomicilio.getICveDomicilio());
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
	 * Metodo update
	 */
	public Object updateActual(Connection conGral, Object obj)
			throws DAOException {
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
			TVCTRDomicilio vCTRDomicilio = (TVCTRDomicilio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update CTRDomicilio" + " set lActivo= ? "
					+ "where iCveEmpresa = ? " + " and iCveDomicilio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCTRDomicilio.getLActivo());
			pstmt.setInt(2, vCTRDomicilio.getICveEmpresa());
			pstmt.setInt(3, vCTRDomicilio.getICveDomicilio());
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
			TVCTRDomicilio vCTRDomicilio = (TVCTRDomicilio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from CTRDomicilio" + " where iCveEmpresa = ?"
					+ " and iCveDomicilio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCTRDomicilio.getICveEmpresa());
			pstmt.setInt(2, vCTRDomicilio.getICveDomicilio());
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
				warn("delete.closeCTRDomicilio", ex2);
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
			TVCTRDomicilio vCTRDomicilio = (TVCTRDomicilio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update CTRDomicilio" + " set lActivo= ? "
					+ "where iCveEmpresa = ?" + "and iCveDomicilio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vCTRDomicilio.getICveEmpresa());
			pstmt.setInt(3, vCTRDomicilio.getICveDomicilio());
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
