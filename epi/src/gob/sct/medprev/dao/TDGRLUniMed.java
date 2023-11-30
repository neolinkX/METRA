package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLUniMed
 * </p>
 * <p>
 * Description: Data Acces Object de GRLUniMed
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

public class TDGRLUniMed extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLUniMed() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		return FindByAll(new HashMap(), " order by iCveUniMed");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cOrderBy) throws DAOException {
		return FindByAll(new HashMap(), cOrderBy);
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(HashMap hmFiltros) throws DAOException {
		return FindByAll(hmFiltros, " order by iCveUniMed");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(HashMap hmFiltros, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUniMed = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLUniMed vGRLUniMed;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "iCveUniMed,cDscUniMed,iCvePais,iCveEstado,iCveMunicipio,lCis,lPago "
					+ "from GRLUniMed";
			String cCveUniMed = (String) hmFiltros.get("iCveUniMed");
			String cCis = (String) hmFiltros.get("lCis");
			if (cCveUniMed != null) {
				cSQL += " where iCveUniMed=?";
				cWhereAnd = " and";
			}
			if (cCis != null) {
				cSQL += cWhereAnd + " lCis=?";
			}
			if (cOrderBy != null) {
				cSQL += cOrderBy;
			}
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cCveUniMed != null) {
				pstmt.setInt(i++, Integer.parseInt(cCveUniMed));
			}
			if (cCis != null) {
				pstmt.setInt(i++, Integer.parseInt(cCis));
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUniMed = new TVGRLUniMed();
				vGRLUniMed.setICveUniMed(rset.getInt("iCveUniMed"));
				vGRLUniMed.setCDscUniMed(rset.getString("cDscUniMed"));
				vGRLUniMed.setICvePais(rset.getInt("iCvePais"));
				vGRLUniMed.setICveEstado(rset.getInt("iCveEstado"));
				vGRLUniMed.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vGRLUniMed.setLCis(rset.getInt("lCis"));
				vGRLUniMed.setLPago(rset.getInt("lPago"));
				vcGRLUniMed.addElement(vGRLUniMed);
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
		}
		return vcGRLUniMed;
	}

	/**
	 * Metodo Find By All con Filtro y Orden
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUniMed2 = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUniMed vGRLUniMed2;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "GRLUniMed.iCveUniMed," + "cDscUniMed,"
					+ "iCvePais," + "iCveEstado," + "iCveMunicipio," + "lCIS,"
					+ "lPago," + "cCalle," + "cColonia," + "iCP," + "cCiudad,"
					+ "cTel," + "cCorreoE," + "lPrivada," + "lVigente, "
					+ "iCveUsuRespUM " + " from GRLUniMed " + cWhere + cOrderBy;

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUniMed2 = new TVGRLUniMed();
				vGRLUniMed2.setICveUniMed(rset.getInt(1));
				vGRLUniMed2.setCDscUniMed(rset.getString(2));
				vGRLUniMed2.setICvePais(rset.getInt(3));
				vGRLUniMed2.setICveEstado(rset.getInt(4));
				vGRLUniMed2.setICveMunicipio(rset.getInt(5));
				vGRLUniMed2.setLCis(rset.getInt(6));
				vGRLUniMed2.setLPago(rset.getInt(7));
				vGRLUniMed2.setCCalle(rset.getString(8));
				vGRLUniMed2.setCColonia(rset.getString(9));
				vGRLUniMed2.setICP(rset.getInt(10));
				vGRLUniMed2.setCCiudad(rset.getString(11));
				vGRLUniMed2.setCTel(rset.getString(12));
				vGRLUniMed2.setCCorreoE(rset.getString(13));
				vGRLUniMed2.setLPrivada(rset.getInt(14));
				vGRLUniMed2.setLVigente(rset.getInt(15));
				vGRLUniMed2.setICveUsuResp(rset.getInt(16));

				vcGRLUniMed2.addElement(vGRLUniMed2);
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
			return vcGRLUniMed2;
		}
	}

	/**
	 * Metodo FindUniMed
	 * 
	 * @author Marco A. Gonz�lez Paz.
	 */
	public Vector FindUniMed(String cUniMed) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUniMed = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLUniMed vGRLUniMed;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select " + "iCveUniMed," + "cDscUniMed,"
					+ "iCvePais," + "iCveEstado," + "iCveMunicipio," + "lCis,"
					+ "lPago, " + "icveuddadmiva " + "from GRLUniMed ";
			cSQL = cSQL + " where iCveUniMed = " + cUniMed;

			 System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUniMed = new TVGRLUniMed();
				vGRLUniMed.setICveUniMed(rset.getInt("iCveUniMed"));
				vGRLUniMed.setCDscUniMed(rset.getString("cDscUniMed"));
				vGRLUniMed.setICvePais(rset.getInt("iCvePais"));
				vGRLUniMed.setICveEstado(rset.getInt("iCveEstado"));
				vGRLUniMed.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vGRLUniMed.setLCis(rset.getInt("lCis"));
				vGRLUniMed.setLPago(rset.getInt("lPago"));
				vGRLUniMed.setICveUddAdmiva(rset.getString("iCveUddadmiva"));
				vcGRLUniMed.addElement(vGRLUniMed);
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
		}
		return vcGRLUniMed;
	}
	
	

	/**
	 * Metodo FindUniMed
	 * 
	 * @author Marco A. Gonz�lez Paz.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector FindUniMedDos(String cUniMed) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUniMed = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLUniMed vGRLUniMed;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select " + "iCveUniMed," + "cDscUniMed,"
					+ "iCvePais," + "iCveEstado," + "iCveMunicipio," + "lCis,"
					+ "lPago, " + "icveuddadmiva, "
					+ "ccorreoe, " + "ctel, " 
					+ "ccalle, " + "ccolonia, " 
					+ "icp, " + "cciudad, " 
					+ "lprivada, " + "lvigente " + "from GRLUniMed ";
			cSQL = cSQL + " where iCveUniMed = " + cUniMed;

			 System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUniMed = new TVGRLUniMed();
				vGRLUniMed.setICveUniMed(rset.getInt("iCveUniMed"));
				vGRLUniMed.setCDscUniMed(rset.getString("cDscUniMed"));
				vGRLUniMed.setICvePais(rset.getInt("iCvePais"));
				vGRLUniMed.setICveEstado(rset.getInt("iCveEstado"));
				vGRLUniMed.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vGRLUniMed.setLCis(rset.getInt("lCis"));
				vGRLUniMed.setLPago(rset.getInt("lPago"));
				vGRLUniMed.setICveUddAdmiva(rset.getString("iCveUddadmiva"));
				
				vGRLUniMed.setCCorreoE(rset.getString("ccorreoe"));
				vGRLUniMed.setCTel(rset.getString("ctel"));
				vGRLUniMed.setCCalle(rset.getString("ccalle"));
				vGRLUniMed.setCColonia(rset.getString("ccolonia"));
				vGRLUniMed.setICP(rset.getInt("icp"));
				vGRLUniMed.setCCiudad(rset.getString(14));
				
				System.out.println("*ciudad = "+rset.getString("cciudad"));
				System.out.println("**ciudad = "+rset.getString(14));
				
				vGRLUniMed.setLPrivada(rset.getInt("lprivada"));
				vGRLUniMed.setLVigente(rset.getInt("lvigente"));
				
				vcGRLUniMed.addElement(vGRLUniMed);
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
		}
		return vcGRLUniMed;
	}	
	
	

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVGRLUniMed vGRLUniMed = (TVGRLUniMed) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLUniMed(" + "iCveUniMed," + "cDscUniMed,"
					+ "iCvePais," + "iCveEstado," + "iCveMunicipio," + "lCis,"
					+ "lPago, " + "cCalle, " + "cColonia, " + "iCP, "
					+ "cCiudad, " + "cTel, " + "cCorreoE, " + "lPrivada, "
					+ "lVigente, " + "iCveUddAdmiva "
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveUniMed) from GRLUniMed";
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
			vGRLUniMed.setICveUniMed(iMax);
			// ******************************************************************
			pstmt.setInt(1, vGRLUniMed.getICveUniMed());
			pstmt.setString(2, vGRLUniMed.getCDscUniMed());
			pstmt.setInt(3, vGRLUniMed.getICvePais());
			pstmt.setInt(4, vGRLUniMed.getICveEstado());
			pstmt.setInt(5, vGRLUniMed.getICveMunicipio());
			pstmt.setInt(6, vGRLUniMed.getLCis());
			pstmt.setInt(7, vGRLUniMed.getLPago());
			pstmt.setString(8, vGRLUniMed.getCCalle());
			pstmt.setString(9, vGRLUniMed.getCColonia());
			pstmt.setInt(10, vGRLUniMed.getICP());
			pstmt.setString(11, vGRLUniMed.getCCiudad());
			pstmt.setString(12, vGRLUniMed.getCTel());
			pstmt.setString(13, vGRLUniMed.getCCorreoE());
			pstmt.setInt(14, vGRLUniMed.getLPrivada());
			pstmt.setInt(15, vGRLUniMed.getLVigente());
			pstmt.setString(16, vGRLUniMed.getICveUddAdmiva());

			pstmt.executeUpdate();
			cClave = "" + vGRLUniMed.getICveUniMed();
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
		}
		return cClave;
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
			TVGRLUniMed vGRLUniMed = (TVGRLUniMed) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLUniMed" + " set cDscUniMed= ?, "
					+ "iCvePais= ?, " + "iCveEstado= ?, "
					+ "iCveMunicipio= ?, " + "lCIS= ?, " + "lPago= ?, "
					+ "cCalle= ?, " + "cColonia= ?, " + "iCP= ?, "
					+ "cCiudad= ?, " + "cTel= ?, " + "cCorreoE= ?, "
					+ "lPrivada= ?, " + "lVigente= ?, " +
					// "iCveUddAdmiva = ?," +
					"iCveUsuRespUM = ? " + "where iCveUniMed = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vGRLUniMed.getCDscUniMed());
			pstmt.setInt(2, vGRLUniMed.getICvePais());
			pstmt.setInt(3, vGRLUniMed.getICveEstado());
			pstmt.setInt(4, vGRLUniMed.getICveMunicipio());
			pstmt.setInt(5, vGRLUniMed.getLCis());
			pstmt.setInt(6, vGRLUniMed.getLPago());
			pstmt.setString(7, vGRLUniMed.getCCalle());
			pstmt.setString(8, vGRLUniMed.getCColonia());
			pstmt.setInt(9, vGRLUniMed.getICP());
			pstmt.setString(10, vGRLUniMed.getCCiudad());
			pstmt.setString(11, vGRLUniMed.getCTel());
			pstmt.setString(12, vGRLUniMed.getCCorreoE());
			pstmt.setInt(13, vGRLUniMed.getLPrivada());
			pstmt.setInt(14, vGRLUniMed.getLVigente());
			// pstmt.setString(15, vGRLUniMed.getICveUddAdmiva());
			pstmt.setInt(15, vGRLUniMed.getICveUsuResp());
			pstmt.setInt(16, vGRLUniMed.getICveUniMed());

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
		}
		return cClave;
	}
	
	
	/**
	 * Metodo update
	 */
	public int updateDos(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int regresa = 1;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVGRLUniMed vGRLUniMed = (TVGRLUniMed) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLUniMed" + " set cDscUniMed= ?, "
					+ "iCvePais= ?, " + "iCveEstado= ?, "
					+ "iCveMunicipio= ?, " + "lCIS= ?, " + "lPago= ?, "
					+ "cCalle= ?, " + "cColonia= ?, " + "iCP= ?, "
					+ "cCiudad= ?, " + "cTel= ?, " + "cCorreoE= ?, "
					+ "lPrivada= ?, " + "lVigente= ?, " +
					// "iCveUddAdmiva = ?," +
					"iCveUsuRespUM = ? " + "where iCveUniMed = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vGRLUniMed.getCDscUniMed());
			pstmt.setInt(2, vGRLUniMed.getICvePais());
			pstmt.setInt(3, vGRLUniMed.getICveEstado());
			pstmt.setInt(4, vGRLUniMed.getICveMunicipio());
			pstmt.setInt(5, vGRLUniMed.getLCis());
			pstmt.setInt(6, vGRLUniMed.getLPago());
			pstmt.setString(7, vGRLUniMed.getCCalle());
			pstmt.setString(8, vGRLUniMed.getCColonia());
			pstmt.setInt(9, vGRLUniMed.getICP());
			pstmt.setString(10, vGRLUniMed.getCCiudad());
			pstmt.setString(11, vGRLUniMed.getCTel());
			pstmt.setString(12, vGRLUniMed.getCCorreoE());
			pstmt.setInt(13, vGRLUniMed.getLPrivada());
			pstmt.setInt(14, vGRLUniMed.getLVigente());
			// pstmt.setString(15, vGRLUniMed.getICveUddAdmiva());
			pstmt.setInt(15, vGRLUniMed.getICveUsuResp());
			pstmt.setInt(16, vGRLUniMed.getICveUniMed());

			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			regresa = 0;
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
		}
		return regresa;
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
			TVGRLUniMed vGRLUniMed = (TVGRLUniMed) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLUniMed" + " where iCveUniMed = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLUniMed.getICveUniMed());
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
				warn("delete.closeGRLUniMed", ex2);
			}
		}
		return cClave;
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
			TVGRLUniMed vGRLUniMed = (TVGRLUniMed) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLUniMed" + " set lVigente= ? "
					+ "where iCveUniMed = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vGRLUniMed.getICveUniMed());
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

	/**
	 * Metodo FindUniMed
	 * 
	 * @author AG SA L
	 */
	public String SelectUniMed(String csql) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String vcGRLUniMed="";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = csql;
			//System.out.println(csql);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vcGRLUniMed = rset.getString(1);
			}
		} catch (Exception ex) {
			warn("SelectUniMed", ex);
			throw new DAOException("SelectUniMed");
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
				warn("SelectUniMed.close", ex2);
			}
		}
		return vcGRLUniMed;
	}
	
	
	/**
	 * Metodo Find By All con Filtro y Orden
	 */
	public String FindByAllEpi2(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUniMed2 = new Vector();
		String UnidadesMedicas = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUniMed vGRLUniMed2;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "GRLUniMed.iCveUniMed," + "cDscUniMed " + " from GRLUniMed " + cWhere + cOrderBy;

			System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUniMed2 = new TVGRLUniMed();
				vGRLUniMed2.setICveUniMed(rset.getInt(1));
				vGRLUniMed2.setCDscUniMed(rset.getString(2));
				vcGRLUniMed2.addElement(vGRLUniMed2);
				UnidadesMedicas = UnidadesMedicas +" <option value=\""+rset.getInt(1)+"\">"+rset.getString(2)+"</option>";

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
			return UnidadesMedicas;
		}
	}

	
	
}
