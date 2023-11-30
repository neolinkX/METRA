package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de MEDPerfilEspec DAO
 * </p>
 * <p>
 * Description: Data Access Object de la tabla MEDPerfilEspec
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo S�nchez
 * @version 1.0
 */

public class TDMEDPerfilEspec extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDPerfilEspec() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilEspec = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilEspec vMEDPerfilEspec;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePerfil," + "iCveEspecialidad,"
					+ "cEspecificacion"
					+ " from MEDPerfilEspec order by iCvePerfil";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilEspec = new TVMEDPerfilEspec();
				vMEDPerfilEspec.setICvePerfil(rset.getInt(1));
				vMEDPerfilEspec.setICveEspecialidad(rset.getInt(2));
				vMEDPerfilEspec.setCEspecificacion(rset.getString(3));
				vcMEDPerfilEspec.addElement(vMEDPerfilEspec);
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
			return vcMEDPerfilEspec;
		}
	}

	/**
	 * Metodo find by Where
	 */
	public Vector findByWhere(String where, String orderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilEspec = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilEspec vMEDPerfilEspec;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePerfil," + "iCveEspecialidad,"
					+ "cEspecificacion" + " from MEDPerfilEspec " + where
					+ orderBy;
			// System.out.println(this.getClass().getName()+" SQL: " + cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilEspec = new TVMEDPerfilEspec();
				vMEDPerfilEspec.setICvePerfil(rset.getInt(1));
				vMEDPerfilEspec.setICveEspecialidad(rset.getInt(2));
				vMEDPerfilEspec.setCEspecificacion(rset.getString(3));
				vcMEDPerfilEspec.addElement(vMEDPerfilEspec);
			}
		} catch (Exception ex) {
			warn("FindByWhere", ex);
			throw new DAOException("FindByWhere");
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
			return vcMEDPerfilEspec;
		}
	}

	public Vector findEspecDescr(String where, String orderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilEspec = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilEspec vMEDPerfilEspec;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "a.iCvePerfil," + "a.iCveEspecialidad,"
					+ "a.cEspecificacion," + "b.cDscEspecialidad"
					+ " from MEDPerfilEspec a JOIN MEDEspecialidad b "
					+ " ON a.iCveEspecialidad = b.iCveEspecialidad " + where
					+ orderBy;
			// System.out.println(this.getClass().getName()+" SQL: " + cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilEspec = new TVMEDPerfilEspec();
				vMEDPerfilEspec.setICvePerfil(rset.getInt(1));
				vMEDPerfilEspec.setICveEspecialidad(rset.getInt(2));
				vMEDPerfilEspec.setCEspecificacion(rset.getString(3));
				vMEDPerfilEspec.setCDscEspecialidad(rset.getString(4));
				vcMEDPerfilEspec.addElement(vMEDPerfilEspec);
			}
		} catch (Exception ex) {
			warn("FindByWhere", ex);
			throw new DAOException("FindByWhere");
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
			return vcMEDPerfilEspec;
		}
	}

	public Vector findPerfilEspec(String where, String orderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilEspec = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilEspec vMEDPerfilEspec;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT "
					+ " pe.iCvePerfil, pe.iCveEspecialidad, pe.cEspecificacion, e.cDscEspecialidad , "
					+ " (select cDscMdoTrans||' - '||cDscGrupo  from GRLGrupo g JOIN GRLMdoTrans m  "
					+ "  ON g.iCveMdoTrans = m.iCveMdoTrans JOIN MEDPerfilMC p ON (p.iCveMdoTrans = g.iCveMdoTrans  "
					+ "  AND p.iCveGrupo = g.iCveGrupo) WHERE  iCvePerfil = pe.iCvePerfil) as Perfil "
					+ " FROM MEDPerfilEspec pe  "
					+ " JOIN MEDEspecialidad e ON pe.iCveEspecialidad = e.iCveEspecialidad "
					+ " JOIN MEDPerfilMC p ON pe.iCvePerfil = p.iCvePerfil "
					+ where + orderBy;
			// System.out.println(this.getClass().getName()+" SQL (perfil-espec): "
			// + cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilEspec = new TVMEDPerfilEspec();
				vMEDPerfilEspec.setICvePerfil(rset.getInt(1));
				vMEDPerfilEspec.setICveEspecialidad(rset.getInt(2));
				vMEDPerfilEspec.setCEspecificacion(rset.getString(3));
				vMEDPerfilEspec.setCDscEspecialidad(rset.getString(4));
				vMEDPerfilEspec.setCDscPerfil(rset.getString(5));
				vcMEDPerfilEspec.addElement(vMEDPerfilEspec);
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
			return vcMEDPerfilEspec;
		}
	}

	/**
	 * Metodo para insertar en la tabla MEDPerfilEspec. Modificado para que
	 * devuelva la llave primaria. Romeo S�nchez NOTA: No devuelve un objeto
	 * de tipo BeanPK, por consistencia con los Metodos de la clase CFG, que
	 * contiene una variabla de tipo String (cClave) para almacenar el valor
	 * obtenido.
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
			TVMEDPerfilEspec vMEDPerfilEspec = (TVMEDPerfilEspec) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDPerfilEspec(" + "iCvePerfil,"
					+ "iCveEspecialidad," + "cEspecificacion"
					+ ")values(?,?,?)";
			// System.out.println("--insertando en MEDPerfilEspec: " +
			// vMEDPerfilEspec.toHashMap().toString());
			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			// en esta clase no es necesario, porque los campos llave son
			// tomados de otras tablas
			pstmt.setInt(1, vMEDPerfilEspec.getICvePerfil());
			pstmt.setInt(2, vMEDPerfilEspec.getICveEspecialidad());
			pstmt.setString(3, vMEDPerfilEspec.getCEspecificacion());
			pstmt.executeUpdate();
			// l�nea modificada: devuelve la llave en el formato:
			// "perfil,especialidad"
			cClave = vMEDPerfilEspec.getICvePerfil() + ","
					+ vMEDPerfilEspec.getICveEspecialidad();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Throwable ex) {
			/*
			 * Fue necesario "cachar" Throwable, en vez de Exception, pues se
			 * genera una excepci�n: com.ibm.db2.jdbc.DB2Exception al tratar
			 * de insertar un registro con llave existente, y no es subclase de
			 * Exception
			 */
			// System.out.println("***Exception en insert: " + ex.getMessage());
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insert", ex1);
			}
			// warn("insert", ex);
			throw new DAOException(
					"No fue posible insertar el registro porque ya exist�a previamente");
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
			TVMEDPerfilEspec vMEDPerfilEspec = (TVMEDPerfilEspec) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDPerfilEspec" + " set cEspecificacion= ? "
					+ "where iCvePerfil = ? " + " and iCveEspecialidad = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vMEDPerfilEspec.getCEspecificacion());
			pstmt.setInt(2, vMEDPerfilEspec.getICvePerfil());
			pstmt.setInt(3, vMEDPerfilEspec.getICveEspecialidad());
			pstmt.executeUpdate();
			// l�nea modificada: devuelve la llave en el formato:
			// "perfil,especificacion"
			cClave = vMEDPerfilEspec.getICvePerfil() + ","
					+ vMEDPerfilEspec.getICveEspecialidad();
			// System.out.println("regresando llave para TDMEDPerfilSpec: " +
			// cClave);
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
			TVMEDPerfilEspec vMEDPerfilEspec = (TVMEDPerfilEspec) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDPerfilEspec" + " where iCvePerfil = ?"
					+ " and iCveEspecialidad = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDPerfilEspec.getICvePerfil());
			pstmt.setInt(2, vMEDPerfilEspec.getICveEspecialidad());
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
				warn("delete.closeMEDPerfilEspec", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo getPerfilEspecXDiag
	 */
	public Vector getPerfilEspecXDiag(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilEspec = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVMEDPerfilEspec vMEDPerfilEspec;
			TVMEDPerfilDiag vMEDPerfilDiag;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cCvePerfil = (String) hmFiltros.get("iCvePerfil");
			String cCveEspecialidad = (String) hmFiltros
					.get("iCveEspecialidad");
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "a.iCvePerfil,a.iCveEspecialidad,a.cEspecificacion,b.iCveDiagnostico,"
					+ "b.lAlarma,c.cDscDiagnostico,c.cDscBreve,c.cCIE,c.lActivo "
					+ "from "
					+ "MEDPerfilEspec a left join MEDPerfilDiag b on ("
					+ "a.iCvePerfil=b.iCvePerfil and a.iCveEspecialidad=b.iCveEspecialidad) "
					+ "left join MEDDiagnostico c on (b.iCveEspecialidad=c.iCveEspecialidad "
					+ "and b.iCveDiagnostico=c.iCveDiagnostico)";
			if (cCvePerfil != null) {
				cSQL += " where a.iCvePerfil=?";
				cWhereAnd = " and";
			}
			if (cCveEspecialidad != null) {
				cSQL += cWhereAnd + " a.iCveEspecialidad=?";
				cWhereAnd = " and";
			}
			cSQL += " order by a.iCvePerfil,a.iCveEspecialidad";
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cCvePerfil != null)
				pstmt.setInt(i++, Integer.parseInt(cCvePerfil));
			if (cCveEspecialidad != null)
				pstmt.setInt(i++, Integer.parseInt(cCveEspecialidad));
			rset = pstmt.executeQuery();
			TVMEDPerfilEspec vMEDPerfilEspecOld = null;
			Vector vcMEDPerfilDiag = null;
			while (rset.next()) {
				vMEDPerfilEspec = new TVMEDPerfilEspec();
				vMEDPerfilDiag = new TVMEDPerfilDiag();
				vMEDPerfilEspec.setICvePerfil(rset.getInt("iCvePerfil"));
				vMEDPerfilEspec.setICveEspecialidad(rset
						.getInt("iCveEspecialidad"));
				if (!vMEDPerfilEspec.equals(vMEDPerfilEspecOld)) {
					vMEDPerfilEspecOld = vMEDPerfilEspec;
					vcMEDPerfilDiag = new Vector();
					vMEDPerfilDiag.setICveDiagnostico(rset
							.getInt("iCveDiagnostico"));
					if (!rset.wasNull()) {
						vMEDPerfilDiag.setLAlarma(rset.getInt("lAlarma"));
						vMEDPerfilDiag.setCDscDiagnostico(rset
								.getString("cDscDiagnostico"));
						vMEDPerfilDiag
								.setCDscBreve(rset.getString("cDscBreve"));
						vMEDPerfilDiag.setCCIE(rset.getString("cCIE"));
						vMEDPerfilDiag.setLActivo(rset.getInt("lActivo"));
						vcMEDPerfilDiag.addElement(vMEDPerfilDiag);
						vMEDPerfilEspec.setVcMEDPerfilDiag(vcMEDPerfilDiag);
					}
					vMEDPerfilEspec.setCEspecificacion(rset
							.getString("cEspecificacion"));
					vcMEDPerfilEspec.addElement(vMEDPerfilEspec);
				} else {
					vMEDPerfilDiag.setICveDiagnostico(rset
							.getInt("iCveDiagnostico"));
					if (!rset.wasNull()) {
						vMEDPerfilDiag.setLAlarma(rset.getInt("lAlarma"));
						vMEDPerfilDiag.setCDscDiagnostico(rset
								.getString("cDscDiagnostico"));
						vMEDPerfilDiag
								.setCDscBreve(rset.getString("cDscBreve"));
						vMEDPerfilDiag.setCCIE(rset.getString("cCIE"));
						vMEDPerfilDiag.setLActivo(rset.getInt("lActivo"));
						vcMEDPerfilDiag.addElement(vMEDPerfilDiag);
					}
				}
			}
		} catch (Exception ex) {
			warn("getPerfilEspecXDiag", ex);
			throw new DAOException("getPerfilEspecXDiag");
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
				warn("getPerfilEspecXDiag.close", ex2);
			}
		}
		return vcMEDPerfilEspec;
	}
}