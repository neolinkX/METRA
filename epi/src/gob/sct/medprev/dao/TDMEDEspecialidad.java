package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de MEDEspecialidad DAO
 * </p>
 * <p>
 * Description: DAO de la tabla MEDEspecialidad
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

public class TDMEDEspecialidad extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDEspecialidad() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDEspecialidad = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDEspecialidad vMEDEspecialidad;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEspecialidad," + "cDscEspecialidad,"
					+ "cObservacion," + "lActivo" + " from MEDEspecialidad "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDEspecialidad = new TVMEDEspecialidad();
				vMEDEspecialidad.setICveEspecialidad(rset.getInt(1));
				vMEDEspecialidad.setCDscEspecialidad(rset.getString(2));
				vMEDEspecialidad.setCObservacion(rset.getString(3));
				vMEDEspecialidad.setLActivo(rset.getInt(4));
				vcMEDEspecialidad.addElement(vMEDEspecialidad);
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
			return vcMEDEspecialidad;
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
			TVMEDEspecialidad vMEDEspecialidad = (TVMEDEspecialidad) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDEspecialidad(" + "iCveEspecialidad,"
					+ "cDscEspecialidad," + "cObservacion," + "lActivo"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveEspecialidad) from MEDEspecialidad";
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
			vMEDEspecialidad.setICveEspecialidad(iMax);
			// ******************************************************************
			pstmt.setInt(1, vMEDEspecialidad.getICveEspecialidad());
			pstmt.setString(2, vMEDEspecialidad.getCDscEspecialidad());
			pstmt.setString(3, vMEDEspecialidad.getCObservacion());
			pstmt.setInt(4, vMEDEspecialidad.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vMEDEspecialidad.getICveEspecialidad();
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
			TVMEDEspecialidad vMEDEspecialidad = (TVMEDEspecialidad) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDEspecialidad" + " set cDscEspecialidad= ?, "
					+ "cObservacion= ?, " + "lActivo= ? "
					+ "where iCveEspecialidad = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vMEDEspecialidad.getCDscEspecialidad());
			pstmt.setString(2, vMEDEspecialidad.getCObservacion());
			pstmt.setInt(3, vMEDEspecialidad.getLActivo());
			pstmt.setInt(4, vMEDEspecialidad.getICveEspecialidad());
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
			TVMEDEspecialidad vMEDEspecialidad = (TVMEDEspecialidad) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDEspecialidad"
					+ " where iCveEspecialidad = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDEspecialidad.getICveEspecialidad());
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
				warn("delete.closeMEDEspecialidad", ex2);
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
			TVMEDEspecialidad vMEDEspecialidad = (TVMEDEspecialidad) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDEspecialidad" + " set lActivo= ? "
					+ "where iCveEspecialidad = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vMEDEspecialidad.getICveEspecialidad());
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
	 * Metodo getEspecPerfil
	 */
	public Vector getEspecPerfil(HashMap hmfiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDEspecialidad = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVMEDEspecialidad vMEDEspecialidad;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cActivo = (String) hmfiltros.get("lActivo");
			String cCvePerfil = (String) hmfiltros.get("iCvePerfil");
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "a.iCveEspecialidad,a.cDscEspecialidad,a.cObservacion,a.lActivo,"
					+ "b.iCvePerfil " + "from "
					+ "MEDEspecialidad a inner join MEDPerfilEspec b on ("
					+ "a.iCveEspecialidad=b.iCveEspecialidad)";
			if (cActivo != null) {
				cSQL += " where a.lActivo=?";
				cWhereAnd = " and";
			}
			if (cCvePerfil != null) {
				cSQL += cWhereAnd + " b.iCvePerfil=?";
			}
			cSQL += " order by a.cDscEspecialidad";
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cActivo != null)
				pstmt.setInt(i++, Integer.parseInt(cActivo));
			if (cCvePerfil != null)
				pstmt.setInt(i++, Integer.parseInt(cCvePerfil));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDEspecialidad = new TVMEDEspecialidad();
				vMEDEspecialidad.setICveEspecialidad(rset
						.getInt("iCveEspecialidad"));
				vMEDEspecialidad.setCDscEspecialidad(rset
						.getString("cDscEspecialidad"));
				vMEDEspecialidad
						.setCObservacion(rset.getString("cObservacion"));
				vMEDEspecialidad.setLActivo(rset.getInt("lActivo"));
				vMEDEspecialidad.setICvePerfil(rset.getInt("iCvePerfil"));
				vcMEDEspecialidad.addElement(vMEDEspecialidad);
			}
		} catch (Exception ex) {
			warn("getEspecPerfil", ex);
			throw new DAOException("getEspecPerfil");
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
				warn("getEspecPerfil.close", ex2);
			}
		}
		return vcMEDEspecialidad;
	}
}
