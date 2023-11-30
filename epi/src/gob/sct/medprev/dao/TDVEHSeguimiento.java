package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de VEHSeguimiento DAO
 * </p>
 * <p>
 * Description: DAO para la tabla VEHSeguimiento
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hern�ndez Garc�a
 * @version 1.0
 */

public class TDVEHSeguimiento extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDVEHSeguimiento() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHSeguimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHSeguimiento vVEHSeguimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "iCveMantenimiento,iCveMovimiento,iCveVehiculo,"
					+ "VEHSeguimiento.iCveProceso,VEHSeguimiento.iCveEtapa,VEHSeguimiento.iCveSolictante,"
					+ "dtSolicitud,iCveUsuReg,iCveUsuSolicita,cSolicitante,cObservacion,"
					+ "cDscEtapa,cDscSolicitante"
					+ " from VEHSeguimiento "
					+ " left join GRLEtapa ON GRLEtapa.iCveProceso = VEHSeguimiento.iCveProceso "
					+ "      and GRLEtapa.iCveEtapa = VEHSeguimiento.iCveEtapa "
					+ " left join GRLSolicitante ON GRLSolicitante.iCveSolictante = VEHseguimiento.iCveSolictante "
					+ cFiltro + " " + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHSeguimiento = new TVVEHSeguimiento();
				vVEHSeguimiento.setICveMantenimiento(rset.getInt(1));
				vVEHSeguimiento.setICveMovimiento(rset.getInt(2));
				vVEHSeguimiento.setICveVehiculo(rset.getInt(3));
				vVEHSeguimiento.setICveProceso(rset.getInt(4));
				vVEHSeguimiento.setICveEtapa(rset.getInt(5));
				vVEHSeguimiento.setICveSolicitante(rset.getInt(6));
				vVEHSeguimiento.setDtSolicitud(rset.getDate(7));
				vVEHSeguimiento.setICveUsuReg(rset.getInt(8));
				vVEHSeguimiento.setICveUsuSolicita(rset.getInt(9));
				vVEHSeguimiento.setCSolicitante(rset.getString(10));
				vVEHSeguimiento.setCObservacion(rset.getString(11));
				vVEHSeguimiento.setCDscEtapa(rset.getString(12));
				vVEHSeguimiento.setCDscSolicitante(rset.getString(13));
				vcVEHSeguimiento.addElement(vVEHSeguimiento);
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
			return vcVEHSeguimiento;
		}
	}

	/**
	 * 
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null, pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVVEHSeguimiento vVEHSeguimiento = (TVVEHSeguimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT MAX(iCveMovimiento) FROM VehSeguimiento WHERE iCveVehiculo= ? AND iCveMantenimiento = ? ";
			pstmtMax = conn.prepareStatement(cSQL);
			pstmtMax.setInt(1, vVEHSeguimiento.getICveVehiculo());
			pstmtMax.setInt(2, vVEHSeguimiento.getICveMantenimiento());

			rsetMax = pstmtMax.executeQuery();

			if (rsetMax.next())
				iMax = rsetMax.getInt(1);

			iMax++;
			vVEHSeguimiento.setICveMovimiento(iMax);
			cClave = "" + iMax;
			cSQL = "insert into VEHSeguimiento(" + "iCveMantenimiento,"
					+ "iCveMovimiento," + "iCveVehiculo," + "iCveProceso,"
					+ "iCveEtapa," + "iCveSolictante," + "dtSolicitud,"
					+ "iCveUsuReg," + "iCveUsuSolicita," + "cSolicitante,"
					+ "cObservacion" + ")values(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vVEHSeguimiento.getICveMantenimiento());
			pstmt.setInt(2, vVEHSeguimiento.getICveMovimiento());
			pstmt.setInt(3, vVEHSeguimiento.getICveVehiculo());
			pstmt.setInt(4, vVEHSeguimiento.getICveProceso());
			pstmt.setInt(5, vVEHSeguimiento.getICveEtapa());
			pstmt.setInt(6, vVEHSeguimiento.getICveSolicitante());
			pstmt.setDate(7, vVEHSeguimiento.getDtSolicitud());
			pstmt.setInt(8, vVEHSeguimiento.getICveUsuReg());
			pstmt.setInt(9, vVEHSeguimiento.getICveUsuSolicita());
			pstmt.setString(10, vVEHSeguimiento.getCSolicitante().toUpperCase()
					.trim());
			pstmt.setString(11, vVEHSeguimiento.getCObservacion().toUpperCase()
					.trim());

			pstmt.executeUpdate();
			cClave = "" + vVEHSeguimiento.getICveMantenimiento();
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
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
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
			TVVEHSeguimiento vVEHSeguimiento = (TVVEHSeguimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VEHSeguimiento" + " set iCveProceso= ?, "
					+ "iCveEtapa= ?, " + "iCveSolictante= ?, "
					+ "dtSolicitud= ?, " + "iCveUsuReg= ?, "
					+ "iCveUsuSolicita= ?, " + "cSolicitante= ?, "
					+ "cObservacion= ? " + "where iCveMantenimiento = ? "
					+ "and iCveMovimiento = ?" + " and iCveVehiculo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHSeguimiento.getICveProceso());
			pstmt.setInt(2, vVEHSeguimiento.getICveEtapa());
			pstmt.setInt(3, vVEHSeguimiento.getICveSolicitante());
			pstmt.setDate(4, vVEHSeguimiento.getDtSolicitud());
			pstmt.setInt(5, vVEHSeguimiento.getICveUsuReg());
			pstmt.setInt(6, vVEHSeguimiento.getICveUsuSolicita());
			pstmt.setString(7, vVEHSeguimiento.getCSolicitante().toUpperCase()
					.trim());
			pstmt.setString(8, vVEHSeguimiento.getCObservacion().toUpperCase()
					.trim());
			pstmt.setInt(9, vVEHSeguimiento.getICveMantenimiento());
			pstmt.setInt(10, vVEHSeguimiento.getICveMovimiento());
			pstmt.setInt(11, vVEHSeguimiento.getICveVehiculo());
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
			TVVEHSeguimiento vVEHSeguimiento = (TVVEHSeguimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from VEHSeguimiento"
					+ " where iCveMantenimiento = ?"
					+ " and iCveMovimiento = ?" + " and iCveVehiculo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHSeguimiento.getICveMantenimiento());
			pstmt.setInt(2, vVEHSeguimiento.getICveMovimiento());
			pstmt.setInt(3, vVEHSeguimiento.getICveVehiculo());
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
				warn("delete.closeVEHSeguimiento", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All Seguimiento del Mantenimiento x Veh
	 */
	public Vector FindByAllSegxVeh(String sWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHSeguimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHSeguimiento vVEHSeguimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select VehSeguimiento.dtSolicitud, GrlEtapa.cDscEtapa,"
					+ "GrlSolicitante.cDscSolicitante, SegUsuario.cNombre, SegUsuario.cApPaterno,"
					+ "SegUsuario.cApMaterno, VehSeguimiento.cObservacion, VehSeguimiento.cSolicitante, "
					+ "        M.lConcluido, M.lCancelado "
					+ "from VehSeguimiento "
					+ " inner join VEHMantenimiento M on M.iCveVehiculo      = VehSeguimiento.iCveVehiculo "
					+ "                              and M.iCveMantenimiento = VehSeguimiento.iCveMantenimiento "
					+ "Left Join GrlEtapa on VehSeguimiento.iCveProceso=GrlEtapa.iCveProceso "
					+ "     and VehSeguimiento.iCveEtapa=GrlEtapa.iCveEtapa "
					+ "Left Join GrlSolicitante on VehSeguimiento.iCveSolictante=GrlSolicitante.iCveSolictante "
					+ "Left Join SegUsuario on VehSeguimiento.iCveUsuSolicita=SegUsuario.iCveUsuario "
					+ sWhere;
			// System.out.println("Detalle = " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHSeguimiento = new TVVEHSeguimiento();
				vVEHSeguimiento.setDtSolicitud(rset.getDate(1));
				vVEHSeguimiento.setCDscEtapa(rset.getString(2));
				vVEHSeguimiento.setCDscSolicitante(rset.getString(3));
				vVEHSeguimiento.setCDscUsuReg(rset.getString(4) + " "
						+ rset.getString(5) + " " + rset.getString(6));
				vVEHSeguimiento.setCObservacion(rset.getString(7));
				vVEHSeguimiento.setCSolicitante(rset.getObject(8) == null ? ""
						: rset.getString(8));
				vVEHSeguimiento.setLConcluido(rset.getInt("lConcluido"));
				vVEHSeguimiento.setLCancelado(rset.getInt("lCancelado"));
				vcVEHSeguimiento.addElement(vVEHSeguimiento);
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
			return vcVEHSeguimiento;
		}
	}
}
