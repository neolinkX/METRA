package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EXPDiagnostico DAO
 * </p>
 * <p>
 * Description: Data Access Object para EXPDiagnostico
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

public class TDEXPDiagnostico extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPDiagnostico() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDiagnostico = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPDiagnostico vEXPDiagnostico;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveServicio," + "iCveEspecialidad," + "iCveDiagnostico"
					+ " from EXPDiagnostico order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDiagnostico = new TVEXPDiagnostico();
				vEXPDiagnostico.setICveExpediente(rset.getInt(1));
				vEXPDiagnostico.setINumExamen(rset.getInt(2));
				vEXPDiagnostico.setICveServicio(rset.getInt(3));
				vEXPDiagnostico.setICveEspecialidad(rset.getInt(4));
				vEXPDiagnostico.setICveDiagnostico(rset.getInt(5));
				vcEXPDiagnostico.addElement(vEXPDiagnostico);
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
			return vcEXPDiagnostico;
		}
	}

	/**
	 * Metodo getDiagEspXServ
	 */
	public Vector getDiagEspXServ(String cExpediente, String cExamen,
			String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDiagnostico = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPDiagnostico vEXPDiagnostico;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "EXPDiagnostico.iCveEspecialidad, "
					+ "EXPDiagnostico.iCveDiagnostico, "
					+ "MEDEspecialidad.cDscEspecialidad, "
					+ "MEDDiagnostico.cDscBreve, "
					+ "MEDDiagnostico.cCIE "
					+ "from EXPDiagnostico "
					+ "inner join MEDEspecialidad on MEDEspecialidad.iCveEspecialidad = EXPDiagnostico.iCveEspecialidad "
					+ "inner join MEDDiagnostico on MEDDiagnostico.iCveEspecialidad = EXPDiagnostico.iCveEspecialidad "
					+ "                   and MEDDiagnostico.iCveDiagnostico = EXPDiagnostico.iCveDiagnostico "
					+ "where EXPDiagnostico.iCveExpediente =  " + cExpediente
					+ "  and EXPDiagnostico.iNumExamen = " + cExamen
					+ "  and EXPDiagnostico.iCveServicio = " + cServicio
					+ " order by TIDIAGNOSTICO ASC";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDiagnostico = new TVEXPDiagnostico();
				vEXPDiagnostico.setICveEspecialidad(rset.getInt(1));
				vEXPDiagnostico.setICveDiagnostico(rset.getInt(2));
				vEXPDiagnostico.setCDscEspecialidad(rset.getString(3));
				vEXPDiagnostico.setCDscDiagnostico(rset.getString(4));
				vEXPDiagnostico.setCCIE(rset.getString(5));
				vcEXPDiagnostico.addElement(vEXPDiagnostico);
			}
		} catch (Exception ex) {
			warn("getDiagEspXServ", ex);
			throw new DAOException("getDiagEspXServ");
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
				warn("getDiagEspXServ.close", ex2);
			}
			return vcEXPDiagnostico;
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
			TVEXPDiagnostico vEXPDiagnostico = (TVEXPDiagnostico) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPDiagnostico(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "iCveEspecialidad,"
					+ "iCveDiagnostico" + ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR Cï¿½DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vEXPDiagnostico.getICveExpediente());
			pstmt.setInt(2, vEXPDiagnostico.getINumExamen());
			pstmt.setInt(3, vEXPDiagnostico.getICveServicio());
			pstmt.setInt(4, vEXPDiagnostico.getICveEspecialidad());
			pstmt.setInt(5, vEXPDiagnostico.getICveDiagnostico());
			pstmt.executeUpdate();
			cClave = "" + vEXPDiagnostico.getICveExpediente();
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
			TVEXPDiagnostico vEXPDiagnostico = (TVEXPDiagnostico) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPDiagnostico" + "where iCveExpediente = ? "
					+ "and iNumExamen = ?" + "and iCveServicio = ?"
					+ "and iCveEspecialidad = ?" + " and iCveDiagnostico = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPDiagnostico.getICveExpediente());
			pstmt.setInt(2, vEXPDiagnostico.getINumExamen());
			pstmt.setInt(3, vEXPDiagnostico.getICveServicio());
			pstmt.setInt(4, vEXPDiagnostico.getICveEspecialidad());
			pstmt.setInt(5, vEXPDiagnostico.getICveDiagnostico());
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
			TVEXPDiagnostico vEXPDiagnostico = (TVEXPDiagnostico) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPDiagnostico" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveServicio = ?"
					+ " and iCveEspecialidad = ?" + " and iCveDiagnostico = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPDiagnostico.getICveExpediente());
			pstmt.setInt(2, vEXPDiagnostico.getINumExamen());
			pstmt.setInt(3, vEXPDiagnostico.getICveServicio());
			pstmt.setInt(4, vEXPDiagnostico.getICveEspecialidad());
			pstmt.setInt(5, vEXPDiagnostico.getICveDiagnostico());
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
				warn("delete.closeEXPDiagnostico", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo getDiagnosticos
	 */
	public Vector getDiagnosticos(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDiagnostico = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPDiagnostico vEXPDiagnostico;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "a.iCveExpediente,a.iNumExamen,a.iCveServicio,a.iCveEspecialidad,"
					+ "a.iCveDiagnostico,b.iCvePerfil,b.iCveMdoTrans,b.iCveGrupo,c.cCIE,"
					+ "c.cDscDiagnostico,d.cDscGrupo,e.lAlarma "
					+ "from EXPDiagnostico a inner join EXPExamGrupo b on ("
					+ "a.iCveExpediente=b.iCveExpediente and a.iNumExamen=b.iNumExamen) "
					+ "inner join MEDDiagnostico c on ("
					+ "a.iCveEspecialidad=c.iCveEspecialidad and "
					+ "a.iCveDiagnostico=c.iCveDiagnostico) inner join GRLGrupo d on ("
					+ "b.iCveMdoTrans=d.iCveMdoTrans and b.iCveGrupo=d.iCveGrupo) "
					+ "left join MEDPerfilDiag e on (a.iCveEspecialidad=e.iCveEspecialidad "
					+ "and a.iCveDiagnostico=e.iCveDiagnostico and b.iCvePerfil=e.iCvePerfil)";
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
			cSQL += "order by c.cCIE,c.cDscDiagnostico,d.cDscGrupo";
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
				vEXPDiagnostico = new TVEXPDiagnostico();
				vEXPDiagnostico
						.setICveExpediente(rset.getInt("iCveExpediente"));
				vEXPDiagnostico.setINumExamen(rset.getInt("iNumExamen"));
				vEXPDiagnostico.setICveServicio(rset.getInt("iCveServicio"));
				vEXPDiagnostico.setICveEspecialidad(rset
						.getInt("iCveEspecialidad"));
				vEXPDiagnostico.setICveDiagnostico(rset
						.getInt("iCveDiagnostico"));
				vEXPDiagnostico.setICvePerfil(rset.getInt("iCvePerfil"));
				vEXPDiagnostico.setICveMdoTrans(rset.getInt("iCveMdoTrans"));
				vEXPDiagnostico.setICveGrupo(rset.getInt("iCveGrupo"));
				vEXPDiagnostico.setCCIE(rset.getString("cCIE"));
				vEXPDiagnostico.setCDscDiagnostico(rset
						.getString("cDscDiagnostico"));
				vEXPDiagnostico.setCDscGrupo(rset.getString("cDscGrupo"));
				i = rset.getInt("lAlarma");
				if (rset.wasNull())
					i = -1;
				vEXPDiagnostico.setLAlarma(i);
				vcEXPDiagnostico.addElement(vEXPDiagnostico);
			}
		} catch (Exception ex) {
			warn("getDiagnosticos", ex);
			throw new DAOException("getDiagnosticos");
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
				warn("getDiagnosticos.close", ex2);
			}
		}
		return vcEXPDiagnostico;
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
		Statement stmt2 = null;
		ResultSet rset2 = null;
		int count2 = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQL2 = "";
			TVEXPRecomendacion vEXPRecomendacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " SELECT * " + " FROM EXPDiagnostico "
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
				cSQL = " DELETE " + " FROM EXPDiagnostico "
						+ " WHERE iCveExpediente = " + cExpediente
						+ " AND iNumExamen = " + cExamen
						+ " AND iCveServicio = " + cServicio;

				stmt = conn.createStatement();
				count = stmt.executeUpdate(cSQL);
			}
			
			cSQL2 = " SELECT * " + " FROM EXPDiagnosticoCif "
					+ " WHERE iCveExpediente = " + cExpediente
					+ " AND iNumExamen = " + cExamen + " AND iCveServicio = "
					+ cServicio;
			stmt2 = conn.createStatement();
			rset2 = stmt2.executeQuery(cSQL2);
			while (rset2.next()) {
				count2++;
			}
			stmt2.close();
			if (count2 != 0) {
				cSQL2 = " DELETE " + " FROM EXPDiagnosticoCif "
						+ " WHERE iCveExpediente = " + cExpediente
						+ " AND iNumExamen = " + cExamen
						+ " AND iCveServicio = " + cServicio;

				stmt2 = conn.createStatement();
				count2 = stmt2.executeUpdate(cSQL2);
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
				if (rset2 != null) {
					rset2.close();
				}
				if (stmt2 != null) {
					stmt2.close();
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

	/**
	 * Metodo updateArchivado
	 * 
	 * @author AG SA.
	 * 
	 */
	public Object updateQuery(Connection conGral, String Query)
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
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = Query;
			pstmt = conn.prepareStatement(cSQL);
			pstmt.executeUpdate();
			cClave = "exitoso";
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
	 * Metodo Find By All
	 */
	public boolean ExistePrevioDiagnostico(String expediente, String Especialidad, String Diagnistico) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDiagnostico = new Vector();
		boolean respuesta = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPDiagnostico vEXPDiagnostico;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "count(iCveExpediente) "
					+ " from EXPDiagnostico" +
					" where " +
					" icveexpediente = ? and " +
					" icveespecialidad = ? and " +
					" icvediagnostico = ? " ;
			System.out.println(cSQL);
			System.out.println(expediente);
			System.out.println(Especialidad);
			System.out.println(Diagnistico);
			
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, Integer.parseInt(expediente));
			pstmt.setInt(2, Integer.parseInt(Especialidad));
			pstmt.setInt(3, Integer.parseInt(Diagnistico));
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if(rset.getInt(1)>0){
					respuesta = true;
				}else{
					System.out.println("Valor igual a 0");
				}
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
			return respuesta;
		}
	}
	

	/**
	 * Metodo Find By All
	 */
	@SuppressWarnings("finally")
	public boolean Diabetes(String expediente, String inumexamen) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean respuesta = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select " + "count(iCveExpediente) "
					+ " from EXPDiagnostico" +
					" where " +
					" icveexpediente = ? and " +
					" inumexamen = ? and " +
					" icveespecialidad = 4 and " +
					" ( icvediagnostico = 49 or " +
					" icvediagnostico = 50 or " +
					" icvediagnostico = 53 or " +
					" icvediagnostico = 58 ) ";
			System.out.println(cSQL);
			System.out.println(expediente);
			System.out.println(inumexamen);
			
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, Integer.parseInt(expediente));
			pstmt.setInt(2, Integer.parseInt(inumexamen));
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if(rset.getInt(1)>0){
					respuesta = true;
				}else{
					System.out.println("Valor igual a 0");
				}
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
			} catch (final Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return respuesta;
		}
	}
	


	/**
	 * Metodo Find By All
	 */
	@SuppressWarnings("finally")
	public int ReglaVigenciaPorDiagnostico(String expediente, String inumexamen, int IUnidadMedica, int IModulo) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean respuesta = false;
		int especialidad = 0; 
		int diagnostico = 0;
		int vigencia = -1; 
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select " + "iCveEspecialidad, iCveDiagnostico "
					+ " from EXPDiagnostico" +
					" where " +
					" icveexpediente = ? and " +
					" inumexamen = ? ";
			System.out.println(cSQL);
			System.out.println(expediente);
			System.out.println(inumexamen);
			System.out.println("UNidad ="+IUnidadMedica);
			System.out.println("Modulo = "+IModulo);
			
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, Integer.parseInt(expediente));
			pstmt.setInt(2, Integer.parseInt(inumexamen));
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				especialidad = rset.getInt(1);
				diagnostico = rset.getInt(2);
				System.out.println("especialidad = "+especialidad);
				System.out.println("diagnostico = "+diagnostico);
				
				if(especialidad == 7 && ((diagnostico ==192 || diagnostico == 202))){//Glaucoma
					if(vigencia > 12 || vigencia == -1){vigencia = 12;}
				}
				if(especialidad == 7 && diagnostico ==189){//Retinopatía diabética
					if(vigencia > 12 || vigencia == -1){vigencia = 12;}
				}
				if(especialidad == 7 && diagnostico ==178){//RETINOPATIAS DEL FONDO Y CAMBIOS VASCULARES
					if(vigencia > 12 || vigencia == -1){vigencia = 12;}
				}
				if(especialidad == 4 && diagnostico ==49){//Diabetes mellitus  insulinodependiente
					if(IUnidadMedica == 1 && (IModulo == 1 || IModulo ==2)){
						if(vigencia > 6 || vigencia == -1){vigencia = 6;}
					}else{vigencia = 0;}
				}
				if(especialidad == 9 && diagnostico ==91){//Cardiopatía isquémica (CARDIOMIOPATIA ISQUEMICA)
					if(IUnidadMedica == 1 && (IModulo == 1 || IModulo ==2)){
						if(vigencia > 6 || vigencia == -1){vigencia = 6;}
					}else{vigencia = 0;}
				}
				if(especialidad == 9 && diagnostico ==94){//Infarto antiguo de miocardio
					if(IUnidadMedica == 1 && (IModulo == 1 || IModulo ==2)){
						if(vigencia > 6 || vigencia == -1){vigencia = 6;}
					}else{vigencia = 0;}
				}
				if(especialidad == 19 && diagnostico ==1332){//Injerto de puente de arteria coronaria (COMPLICACIONES DE DISPOSITIVOS PROTESICOS, IMPLANTES E INJERTOS CARDIOVASCULARES)
					if(IUnidadMedica == 1 && (IModulo == 1 || IModulo ==2)){
						if(vigencia > 6 || vigencia == -1){vigencia = 6;}
					}else{vigencia = 0;}
				}
				
			}
			System.out.println("vigencia ="+vigencia);
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
			} catch (final Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vigencia;
		}
	}
	
	

}