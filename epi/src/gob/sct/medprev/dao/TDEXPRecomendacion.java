package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EXPRecomendacion DAO
 * </p>
 * <p>
 * Description: Data Access Object para EXPRecomendacion
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

public class TDEXPRecomendacion extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPRecomendacion() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRecomendacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRecomendacion vEXPRecomendacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveServicio," + "iCveEspecialidad,"
					+ "iCveRecomendacion," + "cDetalle," + "iCveUsuRecom"
					+ " from EXPRecomendacion order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRecomendacion = new TVEXPRecomendacion();
				vEXPRecomendacion.setICveExpediente(rset.getInt(1));
				vEXPRecomendacion.setINumExamen(rset.getInt(2));
				vEXPRecomendacion.setICveServicio(rset.getInt(3));
				vEXPRecomendacion.setICveEspecialidad(rset.getInt(4));
				vEXPRecomendacion.setICveRecomendacion(rset.getInt(5));
				vEXPRecomendacion.setCDetalle(rset.getString(6));
				vEXPRecomendacion.setICveUsuRecom(rset.getInt(7));
				vcEXPRecomendacion.addElement(vEXPRecomendacion);
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
			return vcEXPRecomendacion;
		}
	}

	/**
	 * Metodo getRecEspXServ
	 */
	public Vector getRecEspXServ(String cExpediente, String cExamen,
			String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRecomendacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRecomendacion vEXPRecomendacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "EXPRecomendacion.iCveEspecialidad, "
					+ "EXPRecomendacion.iCveRecomendacion, "
					+ "MEDEspecialidad.cDscEspecialidad, "
					+ "MEDRecomendacion.cDscBreve "
					+ "from EXPRecomendacion "
					+ "join MEDEspecialidad on MEDEspecialidad.iCveEspecialidad = EXPRecomendacion.iCveEspecialidad "
					+ "join MEDRecomendacion on MEDRecomendacion.iCveEspecialidad = EXPRecomendacion.iCveEspecialidad "
					+ "                   and MEDRecomendacion.iCveRecomendacion = EXPRecomendacion.iCveRecomendacion "
					+ "where EXPRecomendacion.iCveExpediente = " + cExpediente
					+ "  and EXPRecomendacion.iNumExamen = " + cExamen
					+ "  and EXPRecomendacion.iCveServicio = " + cServicio;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRecomendacion = new TVEXPRecomendacion();
				vEXPRecomendacion.setICveEspecialidad(rset.getInt(1));
				vEXPRecomendacion.setICveRecomendacion(rset.getInt(2));
				vEXPRecomendacion.setCDscEspecialidad(rset.getString(3));
				vEXPRecomendacion.setCDscRecomendacion(rset.getString(4));
				vcEXPRecomendacion.addElement(vEXPRecomendacion);
			}
		} catch (Exception ex) {
			warn("getRecEspXServ", ex);
			throw new DAOException("getRecEspXServ");
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
			return vcEXPRecomendacion;
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
			TVEXPRecomendacion vEXPRecomendacion = (TVEXPRecomendacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPRecomendacion(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "iCveEspecialidad,"
					+ "iCveRecomendacion," + "cDetalle," + "iCveUsuRecom"
					+ ")values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vEXPRecomendacion.getICveExpediente());
			pstmt.setInt(2, vEXPRecomendacion.getINumExamen());
			pstmt.setInt(3, vEXPRecomendacion.getICveServicio());
			pstmt.setInt(4, vEXPRecomendacion.getICveEspecialidad());
			pstmt.setInt(5, vEXPRecomendacion.getICveRecomendacion());
			pstmt.setString(6, vEXPRecomendacion.getCDetalle());
			pstmt.setInt(7, vEXPRecomendacion.getICveUsuRecom());
			pstmt.executeUpdate();
			cClave = "" + vEXPRecomendacion.getICveExpediente();
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
			TVEXPRecomendacion vEXPRecomendacion = (TVEXPRecomendacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPRecomendacion" + " set cDetalle= ?, "
					+ "iCveUsuRecom= ? " + "where iCveExpediente = ? "
					+ "and iNumExamen = ?" + "and iCveServicio = ?"
					+ "and iCveEspecialidad = ?" + " and iCveRecomendacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vEXPRecomendacion.getCDetalle());
			pstmt.setInt(2, vEXPRecomendacion.getICveUsuRecom());
			pstmt.setInt(3, vEXPRecomendacion.getICveExpediente());
			pstmt.setInt(4, vEXPRecomendacion.getINumExamen());
			pstmt.setInt(5, vEXPRecomendacion.getICveServicio());
			pstmt.setInt(6, vEXPRecomendacion.getICveEspecialidad());
			pstmt.setInt(7, vEXPRecomendacion.getICveRecomendacion());
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
			TVEXPRecomendacion vEXPRecomendacion = (TVEXPRecomendacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPRecomendacion" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveServicio = ?"
					+ " and iCveEspecialidad = ?"
					+ " and iCveRecomendacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPRecomendacion.getICveExpediente());
			pstmt.setInt(2, vEXPRecomendacion.getINumExamen());
			pstmt.setInt(3, vEXPRecomendacion.getICveServicio());
			pstmt.setInt(4, vEXPRecomendacion.getICveEspecialidad());
			pstmt.setInt(5, vEXPRecomendacion.getICveRecomendacion());
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
				warn("delete.closeEXPRecomendacion", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo getRecomendaciones
	 */
	public Vector getRecomendaciones(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRecomendacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPRecomendacion vEXPRecomendacion;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "a.iCveExpediente,a.iNumExamen,a.iCveServicio,a.iCveEspecialidad,"
					+ "a.iCveRecomendacion,a.cDetalle,a.iCveUsuRecom,b.cIdentificador,b.cDscBreve "
					+ "from "
					+ "EXPRecomendacion a inner join MEDRecomendacion b on ("
					+ "a.iCveEspecialidad=b.iCveEspecialidad and "
					+ "a.iCveRecomendacion=b.iCveRecomendacion)";
			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");
			String cCveServicio = (String) hmFiltros.get("iCveServicio");
			if (cCveExpediente != null) {
				cSQL += " where a.iCveExpediente=?";
				cWhereAnd = " and";
			}
			if (cNumExamen != null) {
				cSQL += cWhereAnd + " a.iNumExamen=?";
				cWhereAnd = " and";
			}
			if (cCveServicio != null) {
				cSQL += cWhereAnd + " a.iCveServicio=?";
			}
			cSQL += " order by b.cIdentificador,a.iCveUsuRecom";
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cCveExpediente != null)
				pstmt.setInt(i++, Integer.parseInt(cCveExpediente));
			if (cNumExamen != null)
				pstmt.setInt(i++, Integer.parseInt(cNumExamen));
			if (cCveServicio != null)
				pstmt.setInt(i++, Integer.parseInt(cCveServicio));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRecomendacion = new TVEXPRecomendacion();
				vEXPRecomendacion.setICveExpediente(rset
						.getInt("iCveExpediente"));
				vEXPRecomendacion.setINumExamen(rset.getInt("iNumExamen"));
				vEXPRecomendacion.setICveServicio(rset.getInt("iCveServicio"));
				vEXPRecomendacion.setICveEspecialidad(rset
						.getInt("iCveEspecialidad"));
				vEXPRecomendacion.setICveRecomendacion(rset
						.getInt("iCveRecomendacion"));
				vEXPRecomendacion.setCDetalle(rset.getString("cDetalle"));
				vEXPRecomendacion.setICveUsuRecom(rset.getInt("iCveUsuRecom"));
				vEXPRecomendacion.setCIdentificador(rset
						.getString("cIdentificador"));
				vEXPRecomendacion.setCDscBreve(rset.getString("cDscBreve"));
				vcEXPRecomendacion.addElement(vEXPRecomendacion);
			}
		} catch (Exception ex) {
			warn("getRecomendaciones", ex);
			throw new DAOException("getRecomendaciones");
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
				warn("getRecomendaciones.close", ex2);
			}
		}
		return vcEXPRecomendacion;
	}

	/**
	 * Metodo checkDelete
	 */
	public int checkDelete(String cExpediente, String cExamen, String cServicio)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		int count = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRecomendacion vEXPRecomendacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " SELECT * " + " FROM EXPRecomendacion "
					+ " WHERE iCveExpediente = " + cExpediente
					+ " AND iNumExamen = " + cExamen + " AND iCveServicio = "
					+ cServicio;
			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			while (rset.next()) {
				count++;
			}
			stmt.close();
			if (count != 0) {
				cSQL = " DELETE " + " FROM EXPRecomendacion "
						+ " WHERE iCveExpediente = " + cExpediente
						+ " AND iNumExamen = " + cExamen
						+ " AND iCveServicio = " + cServicio;

				stmt = conn.createStatement();
				count = stmt.executeUpdate(cSQL);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("checkDelete.close", ex2);
			}
		}
		return count;
	}

}