package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import gob.sct.medprev.dwr.vo.PermisosUsuMedVo;
import gob.sct.medprev.vo.*;

import com.micper.seguridad.vo.TVDinRep;

/**
 * <p>
 * Title: Data Acces Object de GRLUSUMedicos DAO
 * </p>
 * <p>
 * Description: DAO de la entidad GRLUSUMedicos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Suarez Romero
 * @version 1.0
 */

public class TDGRLUSUMedicos extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLUSUMedicos() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUSUMedicos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUSUMedicos vGRLUSUMedicos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "grlusumedicos.iCveUsuario, "
					+ "grlusumedicos.iCveUniMed, "
					+ "grlusumedicos.iCveProceso, "
					+ "grlusumedicos.iCveModulo, "
					+ "grlusumedicos.iCveServicio, "
					+ "grlusumedicos.iCveRama, "
					+ "segusuario.cnombre,segusuario.cappaterno,segusuario.capmaterno, "
					+ "grlunimed.cdscunimed, "
					+ "grlproceso.cdscproceso, "
					+ "grlmodulo.cdscmodulo, "
					+ "medservicio.cdscservicio, "
					+ "medrama.cdscrama "
					+ "from grlusumedicos "
					+ "join segusuario on grlusumedicos.icveusuario = segusuario.icveusuario "
					+ "join grlunimed on grlusumedicos.icveunimed = grlunimed.icveunimed "
					+ "join grlproceso on grlusumedicos.icveproceso = grlproceso.icveproceso "
					+ "join grlmodulo on grlusumedicos.icvemodulo = grlmodulo.icvemodulo "
					+ "join medservicio on grlusumedicos.icveservicio = medservicio.icveservicio "
					+ "join medrama on grlusumedicos.icveservicio = medrama.icveservicio "
					+ "and grlusumedicos.icverama = medrama.icverama " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUSUMedicos = new TVGRLUSUMedicos();
				vGRLUSUMedicos.setICveUsuario(rset.getInt(1));
				vGRLUSUMedicos.setICveUniMed(rset.getInt(2));
				vGRLUSUMedicos.setICveProceso(rset.getInt(3));
				vGRLUSUMedicos.setICveModulo(rset.getInt(4));
				vGRLUSUMedicos.setICveServicio(rset.getInt(5));
				vGRLUSUMedicos.setICveRama(rset.getInt(6));
				vGRLUSUMedicos.setCNombreCompleto(rset.getString(7) + " "
						+ rset.getString(8) + " " + rset.getString(9));
				vGRLUSUMedicos.setCDscUniMed(rset.getString(10));
				vGRLUSUMedicos.setCDscProceso(rset.getString(11));
				vGRLUSUMedicos.setCDscModulo(rset.getString(12));
				vGRLUSUMedicos.setCDscRama(rset.getString(13));
				vGRLUSUMedicos.setCDscServicio(rset.getString(14));
				vcGRLUSUMedicos.addElement(vGRLUSUMedicos);
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
			return vcGRLUSUMedicos;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllLUSR(String cWhere) throws DAOException {// Listado
																	// de
																	// Usuarios,
																	// Servicios
																	// y Ramas
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUSUMedicos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUSUMedicos vGRLUSUMedicos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "            g.iCveUsuario, "
					+ "            g.iCveUniMed, "
					+ "            g.iCveProceso, "
					+ "            g.iCveModulo, "
					+ "            g.iCveServicio, "
					+ "            g.iCveRama, "
					+ "            s.cnombre,s.cappaterno,s.capmaterno, "
					+ "            u.cdscunimed, "
					+ "            p.cdscproceso, "
					+ "            m.cdscmodulo, "
					+ "            sv.cdscservicio, "
					+ "            r.cdscrama "
					+ "            from grlusumedicos as g, "
					+ "		 segusuario as s, " + "		 grlunimed as u, "
					+ "		 grlmodulo as m," + "		 medservicio as sv,"
					+ "		 medrama as r," + "		 grlproceso as p" + "	where"
					+ "	         g.icveusuario = s.icveusuario and"
					+ "		 g.icveunimed = u.icveunimed and"
					+ "		 g.icveunimed = m.icveunimed and"
					+ "		 g.icvemodulo = m.icvemodulo and"
					+ "		 g.icveservicio = sv.icveservicio and"
					+ "		 g.icveservicio = r.icveservicio and		 "
					+ "             g.icverama = r.icverama   and "
					+ "		 p.icveproceso = 1 and " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUSUMedicos = new TVGRLUSUMedicos();
				vGRLUSUMedicos.setICveUsuario(rset.getInt(1));
				vGRLUSUMedicos.setICveUniMed(rset.getInt(2));
				vGRLUSUMedicos.setICveProceso(rset.getInt(3));
				vGRLUSUMedicos.setICveModulo(rset.getInt(4));
				vGRLUSUMedicos.setICveServicio(rset.getInt(5));
				vGRLUSUMedicos.setICveRama(rset.getInt(6));
				vGRLUSUMedicos.setCNombreCompleto(rset.getString(7) + " "
						+ rset.getString(8) + " " + rset.getString(9));
				vGRLUSUMedicos.setCDscUniMed(rset.getString(10));
				vGRLUSUMedicos.setCDscProceso(rset.getString(11));
				vGRLUSUMedicos.setCDscModulo(rset.getString(12));
				vGRLUSUMedicos.setCDscRama(rset.getString(13));
				vGRLUSUMedicos.setCDscServicio(rset.getString(14));
				vcGRLUSUMedicos.addElement(vGRLUSUMedicos);
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
			return vcGRLUSUMedicos;
		}
	}

	/**
	 * Metodo Find By All Simple
	 */
	public Vector FindByAllSimple(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUSUMedicos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUSUMedicos vGRLUSUMedicos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select " + "iCveUsuario, " + "iCveUniMed, "
					+ "iCveProceso, " + "iCveModulo, " + "iCveServicio, "
					+ "iCveRama " + "from grlusumedicos " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUSUMedicos = new TVGRLUSUMedicos();
				vGRLUSUMedicos.setICveUsuario(rset.getInt(1));
				vGRLUSUMedicos.setICveUniMed(rset.getInt(2));
				vGRLUSUMedicos.setICveProceso(rset.getInt(3));
				vGRLUSUMedicos.setICveModulo(rset.getInt(4));
				vGRLUSUMedicos.setICveServicio(rset.getInt(5));
				vGRLUSUMedicos.setICveRama(rset.getInt(6));
				vcGRLUSUMedicos.addElement(vGRLUSUMedicos);
			}
		} catch (Exception ex) {
			warn("FindByAllSimple", ex);
			throw new DAOException("FindByAllSimple");
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
				warn("FindByAllSimple.close", ex2);
			}
			return vcGRLUSUMedicos;
		}
	}

	/**
	 * Metodo FindModulos
	 */
	public Vector FindModulos(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUSUMedicos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUSUMedicos vGRLUSUMedicos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select distinct GRLUsuMedicos.iCveModulo, GRLModulo.cDscModulo  "
					+ "from GRLUsuMedicos  "
					+ "inner join GRLModulo on GRLModulo.iCveUniMed = GRLUsuMedicos.iCveUniMed "
					+ " and GRLModulo.iCveModulo= GRLUsuMedicos.iCveModulo "
					+ cWhere +" ORDER BY GRLModulo.cDscModulo";
            //System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUSUMedicos = new TVGRLUSUMedicos();
				vGRLUSUMedicos.setICveModulo(rset.getInt(1));
				vGRLUSUMedicos.setCDscModulo(rset.getString(2));
				vcGRLUSUMedicos.addElement(vGRLUSUMedicos);
			}
		} catch (Exception ex) {
			warn("FindModulos", ex);
			throw new DAOException("FindModulos");
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
				warn("FindModulos.close", ex2);
			}
			return vcGRLUSUMedicos;
		}
	}

	/**
	 * Metodo Insert2
	 */
	public Object insert2(Connection conGral, Object obj) throws DAOException {
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
			TVGRLUSUMedicos vGRLUSUMedicos = (TVGRLUSUMedicos) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLUSUMedicos(" + "iCveUsuario,"
					+ "iCveUniMed," + "iCveProceso," + "iCveModulo,"
					+ "iCveServicio," + "iCveRama" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vGRLUSUMedicos.getICveUsuario());
			pstmt.setInt(2, vGRLUSUMedicos.getICveUniMed());
			pstmt.setInt(3, vGRLUSUMedicos.getICveProceso());
			pstmt.setInt(4, vGRLUSUMedicos.getICveModulo());
			pstmt.setInt(5, vGRLUSUMedicos.getICveServicio());
			pstmt.setInt(6, vGRLUSUMedicos.getICveRama());

			pstmt.executeUpdate();
			cClave = "" + vGRLUSUMedicos.getICveRama();
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
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean lExec = false;
		TVGRLUSUMedicos vGRLUSUMedicos = (TVGRLUSUMedicos) obj;
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
			cSQL = "insert into GRLUSUMedicos(" + "iCveUsuario,"
					+ "iCveUniMed," + "iCveProceso," + "iCveModulo,"
					+ "iCveServicio," + "iCveRama" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLUSUMedicos.getICveUsuario());
			pstmt.setInt(2, vGRLUSUMedicos.getICveUniMed());
			pstmt.setInt(3, vGRLUSUMedicos.getICveProceso());
			pstmt.setInt(4, vGRLUSUMedicos.getICveModulo());
			pstmt.setInt(5, vGRLUSUMedicos.getICveServicio());
			pstmt.setInt(6, vGRLUSUMedicos.getICveRama());
			pstmt.executeUpdate();
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
			lExec = true;
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
			if (lExec == true) {
				throw new DAOException("");
			}
			return vGRLUSUMedicos;
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
			TVGRLUSUMedicos vGRLUSUMedicos = (TVGRLUSUMedicos) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLUSUMedicos" + " where iCveUsuario = ?"
					+ " and iCveUniMed = ?" + " and iCveProceso = ?"
					+ " and iCveModulo = ?" + " and iCveServicio = ?"
					+ " and iCveRama = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLUSUMedicos.getICveUsuario());
			pstmt.setInt(2, vGRLUSUMedicos.getICveUniMed());
			pstmt.setInt(3, vGRLUSUMedicos.getICveProceso());
			pstmt.setInt(4, vGRLUSUMedicos.getICveModulo());
			pstmt.setInt(5, vGRLUSUMedicos.getICveServicio());
			pstmt.setInt(6, vGRLUSUMedicos.getICveRama());
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
				warn("delete.closeGRLUSUMedicos", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findServicios(int iCveUsuario, int iCveUniMed,
			int iCveProceso, int iCveModulo, boolean lVarios)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDServicio vMEDServicio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select DISTINCT  medservicio.icveservicio, medservicio.cdscservicio "
					+ "from grlusumedicos "
					+ "join medservicio on grlusumedicos.icveservicio = medservicio.icveservicio "
					+ "where grlusumedicos.icveusuario = "
					+ iCveUsuario
					+ " and grlusumedicos.icveunimed = "
					+ iCveUniMed
					+ " and grlusumedicos.icveproceso = "
					+ iCveProceso
					+ " and grlusumedicos.icvemodulo = "
					+ iCveModulo
					+ " and lactivo = 1 ";
			if (lVarios) {
				cSQL = cSQL + "and LVARIOSMEDS = 1 ";
			} else {
				cSQL = cSQL + "and LVARIOSMEDS = 0 ";
			}

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDServicio = new TVMEDServicio();
				vMEDServicio.setICveServicio(rset.getInt(1));
				vMEDServicio.setCDscServicio(rset.getString(2));
				vcMEDServicio.addElement(vMEDServicio);
			}
		} catch (Exception ex) {
			warn("findServicios", ex);
			throw new DAOException("findServicios");
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
				warn("findServicios.close", ex2);
			}
			return vcMEDServicio;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findServicios2(int iCveUsuario, int iCveUniMed,
			int iCveProceso, int iCveModulo, boolean lVarios)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDServicio vMEDServicio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select DISTINCT  medservicio.icveservicio, medservicio.cdscservicio "
					+ "from grlusumedicos "
					+ "join medservicio on grlusumedicos.icveservicio = medservicio.icveservicio "
					+ "where grlusumedicos.icveusuario = "
					+ iCveUsuario
					+ " and grlusumedicos.icveunimed = "
					+ iCveUniMed
					+ " and grlusumedicos.icveproceso = "
					+ iCveProceso
					+ " and grlusumedicos.icvemodulo = "
					+ iCveModulo
					+ " and lactivo = 1 Order by medservicio.cdscservicio";
			/*
			 * if(lVarios) { cSQL = cSQL + "and LVARIOSMEDS = 1 "; } else { cSQL
			 * = cSQL + "and LVARIOSMEDS = 0 "; }
			 */

		   System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDServicio = new TVMEDServicio();
				vMEDServicio.setICveServicio(rset.getInt(1));
				vMEDServicio.setCDscServicio(rset.getString(2));
				vcMEDServicio.addElement(vMEDServicio);
			}
		} catch (Exception ex) {
			warn("findServicios", ex);
			throw new DAOException("findServicios");
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
				warn("findServicios.close", ex2);
			}
			return vcMEDServicio;
		}
	}

	public Vector findServXUsu(int iCveUsuario, int iCveUniMed,
			int iCveProceso, int iCveModulo) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDServicio vMEDServicio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select DISTINCT  medservicio.icveservicio, medservicio.cdscservicio "
					+ "from grlusumedicos "
					+ "join medservicio on grlusumedicos.icveservicio = medservicio.icveservicio "
					+ "where grlusumedicos.icveusuario = "
					+ iCveUsuario
					+ " and grlusumedicos.icveunimed = "
					+ iCveUniMed
					+ " and grlusumedicos.icveproceso = "
					+ iCveProceso
					+ " and grlusumedicos.icvemodulo = "
					+ iCveModulo
					+ " and lactivo = 1 order by medservicio.cdscservicio";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDServicio = new TVMEDServicio();
				vMEDServicio.setICveServicio(rset.getInt(1));
				vMEDServicio.setCDscServicio(rset.getString(2));
				vcMEDServicio.addElement(vMEDServicio);
			}
		} catch (Exception ex) {
			warn("findServXUsu", ex);
			throw new DAOException("findServXUsu");
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
				warn("findServXUsu.close", ex2);
			}
			return vcMEDServicio;
		}
	}

	/**
	 * Metodo FindServXUsu
	 */
	public Vector FindServXUsu(int iUsuario) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUSUMedicos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUSUMedicos vGRLUSUMedicos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "distinct(GRLUsuMedicos.iCveServicio), "
					+ "MEDServicio.cDscServicio "
					+ "from GRLUsuMedicos "
					+ "join MEDServicio on MEDServicio.iCveServicio = GRLUsuMedicos.iCveServicio "
					+ "where GRLUsuMedicos.iCveUsuario = " + iUsuario;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUSUMedicos = new TVGRLUSUMedicos();
				vGRLUSUMedicos.setICveServicio(rset.getInt(1));
				vGRLUSUMedicos.setCDscServicio(rset.getString(2));
				vcGRLUSUMedicos.addElement(vGRLUSUMedicos);
			}
		} catch (Exception ex) {
			warn("FindServXUsu", ex);
			throw new DAOException("FindServXUsu");
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
				warn("FindServXUsu.close", ex2);
			}
			return vcGRLUSUMedicos;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findRamas(int iCveUsuario, int iCveUniMed, int iCveProceso,
			int iCveModulo, int iCveServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDRama = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDRama vMEDRama;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select DISTINCT  medrama.icverama, medrama.cdscrama "
					+ "from grlusumedicos "
					+ "join medrama on grlusumedicos.icveservicio = medrama.icveservicio "
					+ "and grlusumedicos.icverama = medrama.icverama "
					+ "where grlusumedicos.icveusuario = " + iCveUsuario
					+ " and grlusumedicos.icveunimed = " + iCveUniMed
					+ " and grlusumedicos.icveproceso = " + iCveProceso
					+ " and grlusumedicos.icvemodulo = " + iCveModulo
					+ " and grlusumedicos.icveservicio = " + iCveServicio
					+ " and lactivo = 1 ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDRama = new TVMEDRama();
				vMEDRama.setICveRama(rset.getInt(1));
				vMEDRama.setCDscRama(rset.getString(2));
				vcMEDRama.addElement(vMEDRama);
			}
		} catch (Exception ex) {
			warn("findRamas", ex);
			throw new DAOException("findRamas");
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
				warn("findRamas.close", ex2);
			}
			return vcMEDRama;
		}
	}

	public Vector FindServConsulta(int iUsuario, int iUniMed, int iModulo)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUSUMedicos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVGRLUSUMedicos vGRLUSUMedicos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select distinct (S.iCveServicio), S.cDscServicio ")
					.append("   from MEDServicio S ")
					.append("   where S.iCveServicio in ( ")
					.append("               select  distinct(iCveServCons) ")
					.append("                 from MEDConsulta M ")
					.append("                where M.iCveServicio in ( ")
					.append("                     select  distinct(iCveServicio) ")
					.append("                       from GRLUsuMedicos UM ")
					.append("                      where UM.iCveUsuario = ")
					.append(iUsuario)
					.append("                        and UM.iCveUniMed  = ")
					.append(iUniMed)
					.append("                        and UM.iCveModulo  = ")
					.append(iModulo).append("                      ) ")
					.append("               ) ")
					.append("      or S.iCveServicio in ( ")
					.append("               select  UM.iCveServicio ")
					.append("                 from GRLUsuMedicos UM ")
					.append("                where UM.iCveUsuario = ")
					.append(iUsuario)
					.append("                        and UM.iCveUniMed  = ")
					.append(iUniMed)
					.append("                        and UM.iCveModulo  = ")
					.append(iModulo).append("               )")
					.append(" order by S.cDscServicio ");
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUSUMedicos = new TVGRLUSUMedicos();
				vGRLUSUMedicos.setICveServicio(rset.getInt(1));
				vGRLUSUMedicos.setCDscServicio(rset.getString(2));
				vcGRLUSUMedicos.addElement(vGRLUSUMedicos);
			}
		} catch (Exception ex) {
			warn("FindServConsulta", ex);
			throw new DAOException("FindServConsulta");
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
				warn("FindServConsulta.close", ex2);
			}
			return vcGRLUSUMedicos;
		}
	}

	public Vector findServEsp(int iUsuario, int iUniMed, int iProceso,
			int iModulo, String cParametro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUSUMedicos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVDinRep vGRLUSUMedicos = new TVDinRep();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select distinct UM.iCveServicio, MS.cDscServicio ")
					.append(" from GRLUsuMedicos UM ")
					.append(" inner join MEDServicio MS on MS.iCveServicio = UM.iCveServicio ")
					.append(" where UM.iCveUsuario  = ").append(iUsuario)
					.append("   and UM.iCveUniMed = ").append(iUniMed)
					.append("   and UM.iCveProceso = ").append(iProceso)
					.append("   and UM.iCveModulo  = ").append(iModulo)
					.append("   and UM.iCveServicio in (")
					.append(VParametros.getPropEspecifica(cParametro))
					.append(")").append("   and MS.lActivo = 1");
			// System.out.println("Busqueda = " + cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUSUMedicos = new TVDinRep();
				vGRLUSUMedicos.put("iClave", rset.getInt(1));
				vGRLUSUMedicos.put("cDescripcion", rset.getString(2));
				vcGRLUSUMedicos.addElement(vGRLUSUMedicos);
			}
		} catch (Exception ex) {
			warn("FindServxUsu", ex);
			throw new DAOException("FindServxUsu");
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
				warn("FindServxUsu.close", ex2);
			}
			return vcGRLUSUMedicos;
		}
	}

	/**
	 * Metodo Find By All Simple
	 */
	public List<PermisosUsuMedVo> FindByAllSimplePermiso(String cWhere)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<PermisosUsuMedVo> lista = new ArrayList<PermisosUsuMedVo>();
		PermisosUsuMedVo vGRLUSUMedicos = new PermisosUsuMedVo();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select " + "iCveUsuario, " + "iCveUniMed, "
					+ "iCveProceso, " + "iCveModulo, " + "iCveServicio, "
					+ "iCveRama " + "from grlusumedicos " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUSUMedicos = new PermisosUsuMedVo();
				vGRLUSUMedicos.setiCveUsuario(rset.getInt(1));
				vGRLUSUMedicos.setiCveUniMed(rset.getInt(2));
				vGRLUSUMedicos.setiCveProceso(rset.getInt(3));
				vGRLUSUMedicos.setiCveModulo(rset.getInt(4));
				vGRLUSUMedicos.setiCveServicio(rset.getInt(5));
				vGRLUSUMedicos.setiCveRama(rset.getInt(6));
				lista.add(vGRLUSUMedicos);
			}
		} catch (Exception ex) {
			warn("FindByAllSimple", ex);
			throw new DAOException("FindByAllSimple");
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
				warn("FindByAllSimple.close", ex2);
			}
			return lista;
		}
	}

	/**
	 * Metodo Delete
	 */
	public TVGRLUSUMedicos deleteAllByPermisos(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int iEntidades = 0;
		TVGRLUSUMedicos vGRLUSUMedicos = (TVGRLUSUMedicos) obj;
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
			cSQL = "delete from GRLUSUMedicos" + " where iCveUsuario = ?"
					+ " and iCveUniMed = ?" + " and iCveProceso = ?"
					+ " and iCveModulo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLUSUMedicos.getICveUsuario());
			pstmt.setInt(2, vGRLUSUMedicos.getICveUniMed());
			pstmt.setInt(3, vGRLUSUMedicos.getICveProceso());
			pstmt.setInt(4, vGRLUSUMedicos.getICveModulo());
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
				warn("delete.closeGRLUSUMedicos", ex2);
			}
			return vGRLUSUMedicos;
		}
	}

	/**
	 * Metodo InsertAdministradores
	 */
	public boolean InsertAdministradores(Connection conGral) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		boolean insert = false;
		Vector<TVGRLUSUMedicos> vcUsumedicos = new Vector<TVGRLUSUMedicos>();
		TVGRLUSUMedicos vGRLUSUMedicos;
		try {
			vcUsumedicos =  this.FindByAllAdministradores();
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			int j = 0;
			if(vcUsumedicos.size() > 0){
				for(int i =0; i<vcUsumedicos.size();i++){
					j++;
					vGRLUSUMedicos = (TVGRLUSUMedicos) vcUsumedicos.get(i);
					String cSQL = "";
					
					
					///////////Uno
					
					//System.out.println("-------- Insertando el registro # "+i+"  ------------");
					/*System.out.println(vGRLUSUMedicos.getICveUsuario()+","+
							vGRLUSUMedicos.getICveUniMed()+","+
							vGRLUSUMedicos.getICveProceso()+","+
							vGRLUSUMedicos.getICveModulo()+","+
							vGRLUSUMedicos.getICveServicio()+","+
							vGRLUSUMedicos.getICveRama()+",");*/
					this.InsertAdmin(conGral, 
										vGRLUSUMedicos.getICveUsuario(), 
										vGRLUSUMedicos.getICveUniMed(), 
										vGRLUSUMedicos.getICveProceso(), 
										vGRLUSUMedicos.getICveModulo(), 
										vGRLUSUMedicos.getICveServicio(), 
										vGRLUSUMedicos.getICveRama());
					//System.out.println("------------------------------------------------------");
					
				}
			}
			//System.out.println("Total de registros insertados = "+vcUsumedicos.size());
			insert =  true;
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
			return insert;
		}
	}
	
	/**
	 * Metodo InsertAdministradores
	 */
	public Object InsertAdmin(Connection conGral, int usu, int uni, 
										int proc, int mod, int serv, int ram) 
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
					cSQL = "insert into GRLUSUMedicos(" + "iCveUsuario,"
							+ "iCveUniMed," + "iCveProceso," + "iCveModulo,"
							+ "iCveServicio," + "iCveRama" + ")values(?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(cSQL);
					pstmt.setInt(1, usu);
					pstmt.setInt(2, uni);
					pstmt.setInt(3, proc);
					pstmt.setInt(4, mod);
					pstmt.setInt(5, serv);
					pstmt.setInt(6, ram);
					pstmt.executeUpdate();
					cClave = "" + ram;
					if (conGral == null) {
						conn.commit();
						//System.out.println("-------------------- comit --------------------------");
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
	 * Metodo Find By All
	 */
	public Vector<TVGRLUSUMedicos> FindByAllAdministradores() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector<TVGRLUSUMedicos> vcGRLUSUMedicos = new Vector<TVGRLUSUMedicos>();
		String cWhere = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUSUMedicos vGRLUSUMedicos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT" +
					"	V.ICVEUSUARIO," +
					"	V.ICVEUNIMED," +
					"	V.ICVEPROCESO," +
					"	V.ICVEMODULO," +
					"	V.ICVESERVICIO," +
					"	V.ICVERAMA " +
					"FROM (" +
					"		SELECT" +
					"			U.ICVEUSUARIO," +
					"			D.ICVEUNIMED," +
					"			P.ICVEPROCESO," +
					"			M.ICVEMODULO," +
					"			S.ICVESERVICIO," +
					"			R.ICVERAMA" +
					"		FROM" +
					"			GRLUNIMED AS D," +
					"			GRLMODULO AS M," +
					"			MEDSERVICIO AS S," +
					"			MEDRAMA AS R," +
					"			SEGUSUARIO AS U," +
					"			GRLPROCESO AS P" +
					"		WHERE" +
					"			M.ICVEUNIMED > 0 AND" +
					"			D.LVIGENTE = 1 AND" +
					"			M.LVIGENTE = 1 AND" +
					"			D.ICVEUNIMED = M.ICVEUNIMED AND" +
					"			S.ICVESERVICIO = R.ICVESERVICIO AND" +
					"			S.LACTIVO = 1 AND" +
					"			R.LACTIVO = 1 AND" +
					"			U.ICVEUSUARIO IN ("+VParametros.getPropEspecifica("UsuariosAdmin")+") AND" +
					"			U.LBLOQUEADO = 0 AND" +
					"			P.ICVEPROCESO = 1) V " +
					"LEFT JOIN GRLUSUMEDICOS G ON" +
					"	V.ICVEUSUARIO =G.ICVEUSUARIO AND" +
					"	V.ICVEUNIMED=G.ICVEUNIMED AND" +
					"	V.ICVEPROCESO=G.ICVEPROCESO AND" +
					"	V.ICVEMODULO = G.ICVEMODULO AND" +
					"	V.ICVESERVICIO = G.ICVESERVICIO AND" +
					"   V.ICVERAMA = G.ICVERAMA " +
					"WHERE" +
					"	G.ICVEUNIMED IS NULL ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUSUMedicos = new TVGRLUSUMedicos();
				vGRLUSUMedicos.setICveUsuario(rset.getInt(1));
				vGRLUSUMedicos.setICveUniMed(rset.getInt(2));
				vGRLUSUMedicos.setICveProceso(rset.getInt(3));
				vGRLUSUMedicos.setICveModulo(rset.getInt(4));
				vGRLUSUMedicos.setICveServicio(rset.getInt(5));
				vGRLUSUMedicos.setICveRama(rset.getInt(6));
				vcGRLUSUMedicos.addElement(vGRLUSUMedicos);
			}
			//System.out.println("Total de registros encontrados = "+vcGRLUSUMedicos.size());
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
			return vcGRLUSUMedicos;
		}
	}
	
	
	
}
