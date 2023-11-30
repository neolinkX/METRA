package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de MEDRecomendacion DAO
 * </p>
 * <p>
 * Description: DAO de la tabla MEDRecomendacion
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

public class TDMEDRecomendacion extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDRecomendacion() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDRecomendacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDRecomendacion vMEDRecomendacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "MEDRecomendacion.iCveEspecialidad, "
					+ "iCveRecomendacion, "
					+ "cDscRecomendacion, "
					+ "cDscBreve, "
					+ "cIdentificador, "
					+ "MEDRecomendacion.lActivo, "
					+ "MEDEspecialidad.cDscEspecialidad "
					+ "from MEDRecomendacion "
					+ "join MEDEspecialidad on MEDEspecialidad.iCveEspecialidad = MEDRecomendacion.iCveEspecialidad "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDRecomendacion = new TVMEDRecomendacion();
				vMEDRecomendacion.setICveEspecialidad(rset.getInt(1));
				vMEDRecomendacion.setICveRecomendacion(rset.getInt(2));
				vMEDRecomendacion.setCDscRecomendacion(rset.getString(3));
				vMEDRecomendacion.setCDscBreve(rset.getString(4));
				vMEDRecomendacion.setCIdentificador(rset.getString(5));
				vMEDRecomendacion.setLActivo(rset.getInt(6));
				vMEDRecomendacion.setCDscEspecialidad(rset.getString(7));
				vcMEDRecomendacion.addElement(vMEDRecomendacion);
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
			return vcMEDRecomendacion;
		}
	}

	/**
	 * 
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
			TVMEDRecomendacion vMEDRecomendacion = (TVMEDRecomendacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDRecomendacion(" + "iCveEspecialidad,"
					+ "iCveRecomendacion," + "cDscRecomendacion,"
					+ "cDscBreve," + "cIdentificador," + "lActivo"
					+ ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveRecomendacion) from MEDRecomendacion";
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
			vMEDRecomendacion.setICveRecomendacion(iMax);
			// ******************************************************************
			pstmt.setInt(1, vMEDRecomendacion.getICveEspecialidad());
			pstmt.setInt(2, vMEDRecomendacion.getICveRecomendacion());
			pstmt.setString(3, vMEDRecomendacion.getCDscRecomendacion());
			pstmt.setString(4, vMEDRecomendacion.getCDscBreve());
			pstmt.setString(5, vMEDRecomendacion.getCIdentificador());
			pstmt.setInt(6, vMEDRecomendacion.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vMEDRecomendacion.getICveRecomendacion();
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
			TVMEDRecomendacion vMEDRecomendacion = (TVMEDRecomendacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDRecomendacion" + " set cDscRecomendacion= ?, "
					+ "cDscBreve= ?, " + "cIdentificador= ?, " + "lActivo= ? "
					+ "where iCveEspecialidad = ? "
					+ " and iCveRecomendacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vMEDRecomendacion.getCDscRecomendacion());
			pstmt.setString(2, vMEDRecomendacion.getCDscBreve());
			pstmt.setString(3, vMEDRecomendacion.getCIdentificador());
			pstmt.setInt(4, vMEDRecomendacion.getLActivo());
			pstmt.setInt(5, vMEDRecomendacion.getICveEspecialidad());
			pstmt.setInt(6, vMEDRecomendacion.getICveRecomendacion());

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
			TVMEDRecomendacion vMEDRecomendacion = (TVMEDRecomendacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDRecomendacion"
					+ " where iCveEspecialidad = ?"
					+ " and iCveRecomendacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDRecomendacion.getICveEspecialidad());
			pstmt.setInt(2, vMEDRecomendacion.getICveRecomendacion());
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
				warn("delete.closeMEDRecomendacion", ex2);
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
			TVMEDRecomendacion vMEDRecomendacion = (TVMEDRecomendacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDRecomendacion" + " set lActivo= ? "
					+ "where iCveEspecialidad = ?"
					+ " and iCveRecomendacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vMEDRecomendacion.getICveEspecialidad());
			pstmt.setInt(3, vMEDRecomendacion.getICveRecomendacion());
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

	/*
	 * // Obtiene el n�mero m�ximo de registros
	 */
	public int ObtenerRegMax() {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQLMax = "select max(iCveRecomendacion) from MEDRecomendacion";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
		} catch (Exception ex) {
		} finally {
			try {
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return iMax;
		}
	}

	// Genera el Archivo JS de Diagn�sticos ...
	public boolean genJS() {
		DbConnection dbConn = null;
		Connection conn = null;
		boolean lSuccess = false;
		TGeneradorJS jsDiag = new TGeneradorJS();
		PreparedStatement pstmt0 = null, pstmt1 = null, pstmt2 = null;
		ResultSet rset0 = null, rset1 = null, rset2 = null;
		String cSQL = "";
		int i = 0;
		try {
			StringBuffer sbJS = new StringBuffer();
			sbJS.append("var aMEDEsp2 = new Array();");

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "select * " + "from MEDEspecialidad";

			pstmt0 = conn.prepareStatement(cSQL);
			rset0 = pstmt0.executeQuery();

			while (rset0.next()) {
				sbJS.append("aMEDEsp2[" + i + "]=['"
						+ rset0.getInt("ICVEESPECIALIDAD") + "','"
						+ rset0.getString("CDSCESPECIALIDAD") + "']; ");
				i++;
			}

			//
			sbJS.append("var aMEDRecID = new Array();");
			cSQL = "select "
					+ "iCveEspecialidad,iCveRecomendacion,cDscBreve,cDscRecomendacion,cIdentificador "
					+ "from MEDRecomendacion where lActivo = 1 order by iCveEspecialidad, cIdentificador";
			pstmt1 = conn.prepareStatement(cSQL);
			rset1 = pstmt1.executeQuery();
			i = 0;
			while (rset1.next()) {
				sbJS.append("aMEDRecID[" + i + "]=['"
						+ rset1.getInt("iCveEspecialidad") + "','"
						+ rset1.getInt("iCveRecomendacion") + "','"
						+ rset1.getString("cDscBreve") + "','"
						+ rset1.getString("cDscRecomendacion") + "','"
						+ rset1.getString("cIdentificador") + "'];");
				i++;
			}

			//
			sbJS.append("var aMEDRecDsc = new Array();");
			cSQL = "select "
					+ "iCveEspecialidad,iCveRecomendacion,cDscBreve,cDscRecomendacion,cIdentificador "
					+ "from MEDRecomendacion where lActivo = 1 order by iCveEspecialidad, cDscBreve";
			pstmt2 = conn.prepareStatement(cSQL);
			rset2 = pstmt1.executeQuery();
			i = 0;
			while (rset2.next()) {
				sbJS.append("aMEDRecDsc[" + i + "]=['"
						+ rset2.getInt("iCveEspecialidad") + "','"
						+ rset2.getInt("iCveRecomendacion") + "','"
						+ rset2.getString("cDscBreve") + "','"
						+ rset2.getString("cDscRecomendacion") + "','"
						+ rset2.getString("cIdentificador") + "'];");
				i++;
			}

			lSuccess = jsDiag.createJS(
					VParametros.getPropEspecifica("RutaGenJS"), "reco.js",
					sbJS, true);
		} catch (Exception e) {
			warn("genJS", e);
		} finally {
			try {
				if (pstmt0 != null) {
					pstmt0.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (rset0 != null) {
					rset0.close();
				}
				if (rset1 != null) {
					rset1.close();
				}
				if (rset2 != null) {
					rset2.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("genJS.close", ex2);
			}
			return lSuccess;
		}
	}
}