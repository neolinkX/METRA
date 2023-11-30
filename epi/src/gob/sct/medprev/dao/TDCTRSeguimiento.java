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
 * Title: Data Acces Object de CTRSeguimiento DAO
 * </p>
 * <p>
 * Description: Data Access Object de la Tabla CTRSeguimiento
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */

public class TDCTRSeguimiento extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDCTRSeguimiento() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRSeguimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRSeguimiento vCTRSeguimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEmpresa," + "iCvePlantilla,"
					+ "iCveMovimiento," + "iCveProceso," + "iCveEtapa,"
					+ "dtSolicitud," + "iCveSolictante," + "iCveUsuReg,"
					+ "iCveUsuSolicita," + "cSolicitante," + "cObservacion, "
					+ "cNoOficio " + " from CTRSeguimiento " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRSeguimiento = new TVCTRSeguimiento();
				vCTRSeguimiento.setiCveEmpresa(rset.getInt(1));
				vCTRSeguimiento.setiCvePlantilla(rset.getInt(2));
				vCTRSeguimiento.setiCveMovimiento(rset.getInt(3));
				vCTRSeguimiento.setiCveProceso(rset.getInt(4));
				vCTRSeguimiento.setiCveEtapa(rset.getInt(5));
				vCTRSeguimiento.setdtSolicitud(rset.getDate(6));
				vCTRSeguimiento.setiCveSolictante(rset.getInt(7));
				vCTRSeguimiento.setiCveUsuReg(rset.getInt(8));
				vCTRSeguimiento.setiCveUsuSolicita(rset.getInt(9));
				vCTRSeguimiento.setcSolicitante(rset.getString(10));
				vCTRSeguimiento.setcObservacion(rset.getString(11));
				vCTRSeguimiento.setcNoOficio(rset.getString(12));
				vcCTRSeguimiento.addElement(vCTRSeguimiento);
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
			return vcCTRSeguimiento;
		}
	}

	public Vector FindByUltimaEtapa(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRSeguimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRSeguimiento vCTRSeguimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "CTRSeguimiento.iCveEmpresa,"
					+ "max(GRLEtapa.iCveEtapa) iCveEtapa,"
					+ "max(CTRSeguimiento.iCvePlantilla) iCvePlantilla,"
					+ "max(CTRSeguimiento.iCveMovimiento) iCveMovimiento"
					+ " from CTRSeguimiento "
					+ " join GRLEtapa on GRLEtapa.iCveProceso = 5 "
					+ " and GRLEtapa.iCveEtapa = CTRSeguimiento.iCveEtapa "
					+ " and GRLEtapa.lActivo = 1 "
					+ " join GRLEmpresas on GRLEmpresas.iCveEmpresa = CTRSeguimiento.iCveEmpresa "
					+ cWhere + " group by CTRSeguimiento.iCveEmpresa "
					+ " order by max(GRLEtapa.iCveEtapa)";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRSeguimiento = new TVCTRSeguimiento();
				vCTRSeguimiento.setiCveEmpresa(rset.getInt(1));
				vCTRSeguimiento.setiCveEtapa(rset.getInt(2));
				vCTRSeguimiento.setiCvePlantilla(rset.getInt(3));
				vCTRSeguimiento.setiCveMovimiento(rset.getInt(4));
				vcCTRSeguimiento.addElement(vCTRSeguimiento);
			}
		} catch (Exception ex) {
			warn("FindByUltimaEtapa", ex);
			throw new DAOException("FindByUltimaEtapa");
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
				warn("FindByUltimaEtapa.close", ex2);
			}
			return vcCTRSeguimiento;
		}
	}

	public Vector FindByUltimaPlantilla() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRSeguimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRSeguimiento vCTRSeguimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select CTRSeguimiento.iCveEmpresa,    "
					+ "       CTRSeguimiento.iCvePlantilla,  "
					+ "       CTRSeguimiento.iCveMovimiento, "
					+ "       CTRSeguimiento.iCveEtapa       "
					+ "from CTRSeguimiento                   "
					+ "join GRLEmpresas on GRLEmpresas.iCveEmpresa = CTRSeguimiento.iCveEmpresa "
					+ "where CTRSeguimiento.iCvePlantilla in (select max(CTRSeguimiento.iCvePlantilla) "
					+ "                                       from CTRSeguimiento "
					+ "                                       where CTRSeguimiento.iCveEmpresa = GRLEmpresas.iCveEmpresa ) "
					+ "group by CTRSeguimiento.iCveEmpresa,    "
					+ "         CTRSeguimiento.iCvePlantilla,  "
					+ "         CTRSeguimiento.iCveMovimiento, "
					+ "         CTRSeguimiento.iCveEtapa       " + "";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRSeguimiento = new TVCTRSeguimiento();
				vCTRSeguimiento.setiCveEmpresa(rset.getInt(1));
				vCTRSeguimiento.setiCvePlantilla(rset.getInt(2));
				vCTRSeguimiento.setiCveMovimiento(rset.getInt(3));
				vCTRSeguimiento.setiCveEtapa(rset.getInt(4));
				vcCTRSeguimiento.addElement(vCTRSeguimiento);
			}
		} catch (Exception ex) {
			warn("FindByUltimaPlantilla", ex);
			throw new DAOException("FindByUltimaPlantilla");
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
				warn("FindByUltimaPlantilla.close", ex2);
			}
			return vcCTRSeguimiento;
		}
	}

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
			TVCTRSeguimiento vCTRSeguimiento = (TVCTRSeguimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into CTRSeguimiento(" + "iCveEmpresa,"
					+ "iCvePlantilla," + "iCveMovimiento," + "iCveProceso,"
					+ "iCveEtapa," + "dtSolicitud," + "iCveSolictante,"
					+ "iCveUsuReg," + "iCveUsuSolicita," + "cSolicitante,"
					+ "cObservacion, " + "cNoOficio "
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = " select max(iCveMovimiento) "
					+ " from CTRSeguimiento " + " where iCveEmpresa = "
					+ vCTRSeguimiento.getiCveEmpresa()
					+ "   and iCvePlantilla = "
					+ vCTRSeguimiento.getiCvePlantilla();
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
			vCTRSeguimiento.setiCveMovimiento(iMax);
			pstmt.setInt(1, vCTRSeguimiento.getiCveEmpresa());
			pstmt.setInt(2, vCTRSeguimiento.getiCvePlantilla());
			pstmt.setInt(3, vCTRSeguimiento.getiCveMovimiento());
			pstmt.setInt(4, vCTRSeguimiento.getiCveProceso());
			pstmt.setInt(5, vCTRSeguimiento.getiCveEtapa());
			pstmt.setDate(6, vCTRSeguimiento.getdtSolicitud());
			pstmt.setInt(7, vCTRSeguimiento.getiCveSolictante());
			pstmt.setInt(8, vCTRSeguimiento.getiCveUsuReg());
			pstmt.setInt(9, vCTRSeguimiento.getiCveUsuSolicita());
			pstmt.setString(10, vCTRSeguimiento.getcSolicitante());
			pstmt.setString(11, vCTRSeguimiento.getcObservacion());
			pstmt.setString(12, vCTRSeguimiento.getcNoOficio());
			pstmt.executeUpdate();
			cClave = "" + vCTRSeguimiento.getiCveMovimiento();
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
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rsetMax != null) {
					rsetMax.close();
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
}
