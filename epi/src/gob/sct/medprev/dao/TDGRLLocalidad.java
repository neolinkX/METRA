package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLMunicipio DAO
 * </p>
 * <p>
 * Description: Data Access Object para GRLMunicipio
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

public class TDGRLLocalidad extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLLocalidad() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		return FindByAll("", "");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrder) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLMunicipio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLLocalidad vGRLLocalidad;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "iCvePais,iCveEntidadFed,iCveMunicipio,iCveLocalidad,cNombreLoc "
					+ "from GRLLocalidad";
			if (cWhere != null && cWhere.length() != 0) {
				cSQL += " where " + cWhere;
			}
			if (cOrder != null && cOrder.length() != 0) {
				cSQL += cOrder;
			} else {
				cSQL += " order by iCvePais,iCveEntidadFed,iCveMunicipio,iCveLocalidad";
			}
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLLocalidad = new TVGRLLocalidad();
				vGRLLocalidad.setICvePais(rset.getInt("iCvePais"));
				vGRLLocalidad.setICveEntidadFed(rset.getInt("iCveEntidadFed"));
				vGRLLocalidad.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vGRLLocalidad.setICveLocalidad(rset.getInt("iCveLocalidad"));
				vGRLLocalidad.setCNombre(rset.getString("cNombreLoc"));
				vcGRLMunicipio.addElement(vGRLLocalidad);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcGRLMunicipio;
	}
	
	/**
	 * Metodo Find By All
	 */
	public String UbicaLocalidad(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String Localidad = "DESCONOCIDO";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLLocalidad vGRLLocalidad;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "cNombreLoc "
					+ "from GRLLocalidad"
					+ " where " + cWhere;

			//System.out.println(cSQL);
			
				pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Localidad = rset.getString("cNombreLoc");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return Localidad;
	}	

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String pais, String estado, String municipio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLMunicipio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLLocalidad vGRLLocalidad;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ " iCvePais,iCveEntidadFed,iCveMunicipio,iCveLocalidad,cNombreLoc "
					+ " from GRLLocalidad "
					+ " where iCvePais = "+pais
					+ " and iCveEntidadFed = "+estado
					+ " and iCveMunicipio = "+municipio;
			
				cSQL += " order by cNombreLoc, iCvePais,iCveEntidadFed,iCveMunicipio,iCveLocalidad";
				//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLLocalidad = new TVGRLLocalidad();
				vGRLLocalidad.setICvePais(rset.getInt("iCvePais"));
				vGRLLocalidad.setICveEntidadFed(rset.getInt("iCveEntidadFed"));
				vGRLLocalidad.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vGRLLocalidad.setICveLocalidad(rset.getInt("iCveLocalidad"));
				vGRLLocalidad.setCNombre(rset.getString("cNombreLoc"));
				vcGRLMunicipio.addElement(vGRLLocalidad);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcGRLMunicipio;
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
			TVGRLLocalidad vGRLLocalidad = (TVGRLLocalidad) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLMunicipio(" + "iCvePais,"
					+ "iCveEntidadFed," + "iCveMunicipio," + "iCveLocalidad,"
					+ "cNombreLoc" + ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vGRLLocalidad.getICvePais());
			pstmt.setInt(2, vGRLLocalidad.getICveEntidadFed());
			pstmt.setInt(3, vGRLLocalidad.getICveMunicipio());
			pstmt.setInt(4, vGRLLocalidad.getICveLocalidad());
			pstmt.setString(5, vGRLLocalidad.getCNombre());
			pstmt.executeUpdate();
			cClave = "" + vGRLLocalidad.getICvePais();
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
			TVGRLLocalidad vGRLLocalidad = (TVGRLLocalidad) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLMunicipio" + " set cNombreLoc= ? "
					+ "where iCvePais = ? "
					+ "and iCveEntidadFed = ?" + " and iCveMunicipio = ?" + " and iCveLocalidad = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vGRLLocalidad.getCNombre());
			pstmt.setInt(2, vGRLLocalidad.getICvePais());
			pstmt.setInt(3, vGRLLocalidad.getICveEntidadFed());
			pstmt.setInt(4, vGRLLocalidad.getICveMunicipio());
			pstmt.setInt(5, vGRLLocalidad.getICveLocalidad());
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
			TVGRLLocalidad vGRLLocalidad = (TVGRLLocalidad) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLLocalidad" + " where iCvePais = ?"
					+ " and iCveEntidadFed = ?" + " and iCveMunicipio = ?"+ " and iCveLocalidad = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLLocalidad.getICvePais());
			pstmt.setInt(2, vGRLLocalidad.getICveEntidadFed());
			pstmt.setInt(3, vGRLLocalidad.getICveMunicipio());
			pstmt.setInt(4, vGRLLocalidad.getICveLocalidad());
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
				warn("delete.closeGRLMunicipio", ex2);
			}
			return cClave;
		}
	}
}
