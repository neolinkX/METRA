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
 * Title: Data Acces Object de MEDServicio DAO
 * </p>
 * <p>
 * Description: DAO MedServicio
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author
 * @version 1.0
 */

public class TDMEDServicio extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDServicio() {
	}

	/**
	 * Método puedeSubirArchivos
	 */
	public boolean puedeSubirArchivos(int iCveServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean subeArchivo = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDServicio vMEDServicio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count = 0;
			cSQL = " SELECT LSUBIRARCHIVOS FROM MEDServicio " + " WHERE "
					+ " ICVESERVICIO = " + iCveServicio
					+ " ORDER BY cDscServicio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				count = rset.getInt("LSUBIRARCHIVOS");
			}
			if (count > 0)
				subeArchivo = true;
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
			return subeArchivo;
		}
	}

	/**
	 * Método Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
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
			cSQL = "select " + "iCveServicio," + "cDscServicio,"
					+ "cObservacion," + "lVariosMeds," + "lInterConsulta,"
					+ "lDiagnostico," + "lPostDiag," + "lActivo," + "lReqDiag"
					+ " from MEDServicio " + cWhere + " order by cDscServicio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDServicio = new TVMEDServicio();
				vMEDServicio.setICveServicio(rset.getInt(1));
				vMEDServicio.setCDscServicio(rset.getString(2));
				vMEDServicio.setCObservacion(rset.getString(3));
				vMEDServicio.setLVariosMeds(rset.getInt(4));
				vMEDServicio.setLInterConsulta(rset.getInt(5));
				vMEDServicio.setLDiagnostico(rset.getInt(6));
				vMEDServicio.setLPostDiag(rset.getInt(7));
				vMEDServicio.setLActivo(rset.getInt(8));
				vMEDServicio.setLReqDiag(rset.getInt("lReqDiag"));
				vcMEDServicio.addElement(vMEDServicio);
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
			return vcMEDServicio;
		}
	}

	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
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
			cSQL = "select " + "iCveServicio," + "cDscServicio,"
					+ "cObservacion," + "lVariosMeds," + "lInterConsulta,"
					+ "lDiagnostico," + "lPostDiag," + "lActivo,"
					+ "lReqDiag, " + "lSUBIRARCHIVOS" + " from MEDServicio "
					+ cFiltro + cOrden;
			// System.out.println("Dictamen = "+cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDServicio = new TVMEDServicio();
				vMEDServicio.setICveServicio(rset.getInt(1));
				vMEDServicio.setCDscServicio(rset.getString(2));
				vMEDServicio.setCObservacion(rset.getString(3));
				vMEDServicio.setLVariosMeds(rset.getInt(4));
				vMEDServicio.setLInterConsulta(rset.getInt(5));
				vMEDServicio.setLDiagnostico(rset.getInt(6));
				vMEDServicio.setLPostDiag(rset.getInt(7));
				vMEDServicio.setLActivo(rset.getInt(8));
				vMEDServicio.setLReqDiag(rset.getInt(9));
				vMEDServicio.setlSUBIRARCHIVOS(rset.getInt(10));
				vcMEDServicio.addElement(vMEDServicio);
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
			return vcMEDServicio;
		}
	}

	/**
	 * Método Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
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
			TVMEDServicio vMEDServicio = (TVMEDServicio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "insert into MEDServicio(" + "iCveServicio,"
					+ "cDscServicio," + "cObservacion," + "lVariosMeds,"
					+ "lInterConsulta," + "lDiagnostico," + "lPostDiag,"
					+ "lActivo," + "lSUBIRARCHIVOS," + "lReqDiag"
					+ ")values(?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			String cSQLMax = "select max(iCveServicio) from MEDServicio";
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
			vMEDServicio.setICveServicio(iMax);
			// ******************************************************************
			pstmt.setInt(1, vMEDServicio.getICveServicio());
			pstmt.setString(2, vMEDServicio.getCDscServicio());
			pstmt.setString(3, vMEDServicio.getCObservacion());
			pstmt.setInt(4, vMEDServicio.getLVariosMeds());
			pstmt.setInt(5, vMEDServicio.getLInterConsulta());
			pstmt.setInt(6, vMEDServicio.getLDiagnostico());
			pstmt.setInt(7, vMEDServicio.getLPostDiag());
			pstmt.setInt(8, vMEDServicio.getLActivo());
			pstmt.setInt(9, vMEDServicio.getlSUBIRARCHIVOS());
			pstmt.setInt(10, vMEDServicio.getLReqDiag());

			pstmt.executeUpdate();
			cClave = "" + vMEDServicio.getICveServicio();
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
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn == null) {
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
	 * Método update
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
			TVMEDServicio vMEDServicio = (TVMEDServicio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDServicio" + " set cDscServicio= ?, "
					+ "cObservacion= ?, " + "lVariosMeds= ?, "
					+ "lInterConsulta= ?, " + "lDiagnostico= ?, "
					+ "lPostDiag= ?, " + "lActivo= ?, " + "lSUBIRARCHIVOS=?,"
					+ "lReqDiag= ? " + "where iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vMEDServicio.getCDscServicio());
			pstmt.setString(2, vMEDServicio.getCObservacion());
			pstmt.setInt(3, vMEDServicio.getLVariosMeds());
			pstmt.setInt(4, vMEDServicio.getLInterConsulta());
			pstmt.setInt(5, vMEDServicio.getLDiagnostico());
			pstmt.setInt(6, vMEDServicio.getLPostDiag());
			pstmt.setInt(7, vMEDServicio.getLActivo());
			pstmt.setInt(8, vMEDServicio.getlSUBIRARCHIVOS());
			pstmt.setInt(9, vMEDServicio.getLReqDiag());
			pstmt.setInt(10, vMEDServicio.getICveServicio());
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

	/* UpDate Para Dar De Baja Logica */

	/**
	 * Método update
	 */
	public Object updatebaja(Connection conGral, Object obj)
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
			TVMEDServicio vMEDServicio = (TVMEDServicio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDServicio" + " set lActivo= 0 "
					+ "where iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDServicio.getICveServicio());
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
	 * Método Delete
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
			TVMEDServicio vMEDServicio = (TVMEDServicio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDServicio" + " where iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDServicio.getICveServicio());
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
				warn("delete.closeMEDServicio", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método Find By All
	 */
	public Vector FindByAllSerxUM(String cWhere) throws DAOException {
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
			cSQL = "Select GrlServUm.iCveServicio, MedServicio.cDscServicio, EqmAsignacion.lActual "
					+ "from GrlServUm "
					+ "join MedServicio on GrlServUM.iCveServicio=MedServicio.iCveServicio "
					+ "join EqmAsignacion on EqmAsignacion.iCveUniMed=GrlServUm.iCveUniMed "
					+ "and EqmAsignacion.iCveModulo=GrlServUM.iCveModulo "
					+ cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDServicio = new TVMEDServicio();
				vMEDServicio.setICveServicio(rset.getInt(1));
				vMEDServicio.setCDscServicio(rset.getString(2));
				vMEDServicio.setLActivo(rset.getInt(3));
				vMEDServicio.setLDiagnostico(0);
				vcMEDServicio.addElement(vMEDServicio);
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
			return vcMEDServicio;
		}
	}

	public Vector FindForFree(String cFiltro, String cOrden)
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
			cSQL = "SELECT " + "iCveServicio," + "cDscServicio,"
					+ "cObservacion," + "lVariosMeds," + "lInterConsulta,"
					+ "lDiagnostico," + "lPostDiag," + "lActivo," + "lReqDiag"
					+ " FROM MEDServicio " + cFiltro + cOrden;
			// System.out.println("Servicio con Diagnostico !!!! " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDServicio = new TVMEDServicio();
				vMEDServicio.setICveServicio(rset.getInt(1));
				vMEDServicio.setCDscServicio(rset.getString(2));
				vMEDServicio.setCObservacion(rset.getString(3));
				vMEDServicio.setLVariosMeds(rset.getInt(4));
				vMEDServicio.setLInterConsulta(rset.getInt(5));
				vMEDServicio.setLDiagnostico(rset.getInt(6));
				vMEDServicio.setLPostDiag(rset.getInt(7));
				vMEDServicio.setLActivo(rset.getInt(8));
				vMEDServicio.setLReqDiag(rset.getInt(9));

				vcMEDServicio.addElement(vMEDServicio);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
			return vcMEDServicio;
		}
	}

}
