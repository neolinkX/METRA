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
 * Title: Data Acces Object de CTRPlantilla DAO
 * </p>
 * <p>
 * Description: Data Access Object de la Tabla CTRPlantilla
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

public class TDCTRPlantilla extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDCTRPlantilla() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRPlantilla = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRPlantilla vCTRPlantilla;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "  select  CTRPlantilla.iCveEmpresa, "
					+ "  CTRPlantilla.iCvePlantilla,  "
					+ "  CTRPlantilla.iCveTpoEntrega,  "
					+ "  CTRPlantilla.lProgramada,  "
					+ "  CTRPlantilla.iAnio,  "
					+ "  CTRPlantilla.iCvePeriodPla,  "
					+ "  CTRPlantilla.dtSolicitud,  "
					+ "  CTRPlantilla.dtVencimiento,  "
					+ "  CTRPlantilla.dtRecepcion,  "
					+ "  CTRPlantilla.iCveUsuSolicita,  "
					+ "  CTRPlantilla.iCveUMSolicita,  "
					+ "  CTRPlantilla.iCveUsuRecibe,  "
					+ "  CTRPlantilla.iCveUMRecibe,  "
					+ "  CTRPlantilla.iCveMedRecep, "
					+ "  GRLEmpresas.cDscEmpresa, "
					+ "  GRLEmpresas.cIDDGPMPT, "
					+ "  GRLEmpresas.iIDMdoTrans, "
					+ "  GRLEmpresas.cRFC, "
					+ "  GRLEmpresas.cTpoPersona, "
					+ "  CTRPeriodPla.cDscPeriodPla "
					+ "  from CTRPlantilla "
					+ "  join GRLEmpresas ON GRLEmpresas.iCveEmpresa = CTRPlantilla.iCveEmpresa "
					+ "  join CTRPeriodPla ON CTRPeriodPla.iAnio = CTRPlantilla.iAnio and "
					+ "                       CTRPeriodPla.iCvePeriodPla = CTRPlantilla.iCvePeriodPla "
					+ "  join CTRDomicilio ON CTRDomicilio.iCveEmpresa = CTRPlantilla.iCveEmpresa and "
					+ "                       CTRDomicilio.lActivo = 1 "
					+ cWhere + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRPlantilla = new TVCTRPlantilla();
				vCTRPlantilla.setICveEmpresa(rset.getInt(1));
				vCTRPlantilla.setiCvePlantilla(rset.getInt(2));
				vCTRPlantilla.setiCveTpoEntrega(rset.getInt(3));
				vCTRPlantilla.setlProgramada(rset.getInt(4));
				vCTRPlantilla.setiAnio(rset.getInt(5));
				vCTRPlantilla.setiCvePeriodPla(rset.getInt(6));
				vCTRPlantilla.setdtSolicitud(rset.getDate(7));
				vCTRPlantilla.setdtVencimiento(rset.getDate(8));
				vCTRPlantilla.setdtRecepcion(rset.getDate(9));
				vCTRPlantilla.setiCveUsuSolicita(rset.getInt(10));
				vCTRPlantilla.setiCveUMSolicita(rset.getInt(11));
				vCTRPlantilla.setiCveUsuRecibe(rset.getInt(12));
				vCTRPlantilla.setiCveUMRecibe(rset.getInt(13));
				vCTRPlantilla.setiCveMedRecep(rset.getInt(14));
				vCTRPlantilla.setCDscEmpresa(rset.getString(15));
				vCTRPlantilla.setcIDDGPMPT(rset.getString(16));
				vCTRPlantilla.setIIDMdoTrans(rset.getInt(17));
				vCTRPlantilla.setCRFC(rset.getString(18));
				vCTRPlantilla.setCTpoPersona(rset.getString(19));
				vCTRPlantilla.setCDscPeriodPla(rset.getString(20));
				vcCTRPlantilla.addElement(vCTRPlantilla);
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
			return vcCTRPlantilla;
		}
	}

	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRPlantilla = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRPlantilla vCTRPlantilla;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEmpresa," + "iCvePlantilla,"
					+ "iCveTpoEntrega," + "lProgramada," + "iAnio,"
					+ "iCvePeriodPla," + "dtSolicitud," + "dtVencimiento,"
					+ "dtRecepcion," + "iCveUsuSolicita," + "iCveUMSolicita,"
					+ "iCveUsuRecibe," + "iCveUMRecibe," + "iCveMedRecep "
					+ " from CTRPlantilla " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRPlantilla = new TVCTRPlantilla();
				vCTRPlantilla.setICveEmpresa(rset.getInt(1));
				vCTRPlantilla.setiCvePlantilla(rset.getInt(2));
				vCTRPlantilla.setiCveTpoEntrega(rset.getInt(3));
				vCTRPlantilla.setlProgramada(rset.getInt(4));
				vCTRPlantilla.setiAnio(rset.getInt(5));
				vCTRPlantilla.setiCvePeriodPla(rset.getInt(6));
				vCTRPlantilla.setdtSolicitud(rset.getDate(7));
				vCTRPlantilla.setdtVencimiento(rset.getDate(8));
				vCTRPlantilla.setdtRecepcion(rset.getDate(9));
				vCTRPlantilla.setiCveUsuSolicita(rset.getInt(10));
				vCTRPlantilla.setiCveUMSolicita(rset.getInt(11));
				vCTRPlantilla.setiCveUsuRecibe(rset.getInt(12));
				vCTRPlantilla.setiCveUMRecibe(rset.getInt(13));
				vCTRPlantilla.setiCveMedRecep(rset.getInt(14));
				vcCTRPlantilla.addElement(vCTRPlantilla);
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
			return vcCTRPlantilla;
		}
	}

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
			TVCTRPlantilla vCTRPlantilla = (TVCTRPlantilla) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into CTRPlantilla(" + "iCveEmpresa,"
					+ "iCvePlantilla," + "iCveTpoEntrega," + "lProgramada,"
					+ "iAnio," + "iCvePeriodPla," + "dtSolicitud,"
					+ "dtVencimiento," + "iCveUsuSolicita," + "iCveUMSolicita"
					+ ")values(?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCTRPlantilla.getICveEmpresa());
			pstmt.setInt(2, vCTRPlantilla.getiCvePlantilla());
			pstmt.setInt(3, vCTRPlantilla.getiCveTpoEntrega());
			pstmt.setInt(4, vCTRPlantilla.getlProgramada());
			pstmt.setInt(5, vCTRPlantilla.getiAnio());
			pstmt.setInt(6, vCTRPlantilla.getiCvePeriodPla());
			pstmt.setDate(7, vCTRPlantilla.getdtSolicitud());

			if (vCTRPlantilla.getdtVencimiento() == null)
				pstmt.setNull(8, Types.DATE);
			else
				pstmt.setDate(8, vCTRPlantilla.getdtVencimiento());

			pstmt.setInt(9, vCTRPlantilla.getiCveUsuSolicita());
			pstmt.setInt(10, vCTRPlantilla.getiCveUMSolicita());
			pstmt.executeUpdate();
			cClave = "" + vCTRPlantilla.getiCvePlantilla();
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

	public Object updCierre(Connection conGral, Object obj) throws DAOException {
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
			TVCTRPlantilla vCTRPlantilla = (TVCTRPlantilla) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update CTRPlantilla     " + " set dtRecepcion    = ?, "
					+ "     iCveUsuRecibe  = ?, " + "     iCveUMRecibe   = ?, "
					+ "     iCveMedRecep   = ?, " + "     iCveTpoEntrega = ?  "
					+ " where iCveEmpresa  = ?  " + " and iCvePlantilla  = ?  "
					+ "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vCTRPlantilla.getdtRecepcion());
			pstmt.setInt(2, vCTRPlantilla.getiCveUsuRecibe());
			pstmt.setInt(3, vCTRPlantilla.getiCveUMRecibe());
			pstmt.setInt(4, vCTRPlantilla.getiCveMedRecep());
			pstmt.setInt(5, vCTRPlantilla.getiCveTpoEntrega());
			pstmt.setInt(6, vCTRPlantilla.getICveEmpresa());
			pstmt.setInt(7, vCTRPlantilla.getiCvePlantilla());
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

	public Object updPreCierre(Connection conGral, Object obj)
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
			TVCTRPlantilla vCTRPlantilla = (TVCTRPlantilla) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update CTRPlantilla     " + " set iCveUsuRecibe  = ?, "
					+ "     iCveUMRecibe   = ?, " + "     iCveMedRecep   = ?, "
					+ "     iCveTpoEntrega = ?  " + " where iCveEmpresa  = ?  "
					+ " and iCvePlantilla  = ?  " + "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCTRPlantilla.getiCveUsuRecibe());
			pstmt.setInt(2, vCTRPlantilla.getiCveUMRecibe());
			pstmt.setInt(3, vCTRPlantilla.getiCveMedRecep());
			pstmt.setInt(4, vCTRPlantilla.getiCveTpoEntrega());
			pstmt.setInt(5, vCTRPlantilla.getICveEmpresa());
			pstmt.setInt(6, vCTRPlantilla.getiCvePlantilla());
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

}
