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
 * Title: Data Acces Object de VEHMantenimiento DAO
 * </p>
 * <p>
 * Description: DAO para acceso a la tabla VEHMantenimiento
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LSC. Rafael Miranda Blumenkron
 * @version 1.0
 */

public class TDVEHMantenimiento extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDVEHMantenimiento() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHMantenimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "";
			TVVEHMantenimiento vVEHMantenimiento;
			int count;
			cSQL = "select "
					+ "M.iCveVehiculo, M.iCveMantenimiento, M.iCveTpoMantto, TM.cDscTpoMantto, M.dtSolicitud, "
					+ "M.dtProgramado, M.dtInicia, M.iCveEmpMantto, E.cNombreRS, E.cApPaterno, E.cApMaterno, "
					+ "E.cDscEmpMantto, E.cDscBreve, M.cAccesorios, M.cResultado, M.cObservaciones, "
					+ "M.lConcluido, M.iKilometraje, M.dtRecepcion, M.iCveUsuSolicita, U1.cNombre, U1.cApPaterno, "
					+ "U1.cApMaterno, M.iCveUsuAutoriza, U2.cNombre, U2.cApPaterno, U2.cApMaterno, M.iCveUsuRecibe, "
					+ "U3.cNombre, U3.cApPaterno, U3.cApMaterno, M.lCancelado, M.dtCancelacion, V.cPlacas "
					+ "from VEHMantenimiento M "
					+ "left join VEHTpoMantto TM ON TM.iCveTpoMantto = M.iCveTpoMantto "
					+ "left join VEHEmpMantto E ON E.iCveEmpMantto = M.iCveEmpMantto "
					+ "left join SEGUsuario U1 ON U1.iCveUsuario = M.iCveUsuSolicita "
					+ "left join SEGUsuario U2 ON U2.iCveUsuario = M.iCveUsuAutoriza "
					+ "left join SEGUsuario U3 ON U3.iCveUsuario = M.iCveUsuRecibe "
					+ "left join VEHVehiculo V ON V.iCveVehiculo = M.iCveVehiculo "
					+ cFiltro + " " + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			String cNombre = "";
			while (rset.next()) {
				vVEHMantenimiento = new TVVEHMantenimiento();
				vVEHMantenimiento.setICveVehiculo(rset.getInt(1));
				vVEHMantenimiento.setICveMantenimiento(rset.getInt(2));
				vVEHMantenimiento.setICveTpoMantto(rset.getInt(3));
				vVEHMantenimiento.setCDscTpoMantto(rset.getString(4));
				vVEHMantenimiento.setDtSolicitud(rset.getDate(5));
				vVEHMantenimiento.setDtProgramado(rset.getDate(6));
				vVEHMantenimiento.setDtInicia(rset.getDate(7));
				vVEHMantenimiento.setICveEmpMantto(rset.getInt(8));
				cNombre = rset.getString(9) != null ? rset.getString(9) : "";
				cNombre += rset.getString(10) != null ? rset.getString(10)
						.length() != 0 ? " " + rset.getString(10) : "" : "";
				cNombre += rset.getString(11) != null ? rset.getString(11)
						.length() != 0 ? " " + rset.getString(11) : "" : "";
				vVEHMantenimiento.setCNombreEmpMantto(cNombre);
				vVEHMantenimiento.setCDscEmpMantto(rset.getString(12));
				vVEHMantenimiento.setCDscBreveEmpMantto(rset.getString(13));
				vVEHMantenimiento.setCAccesorios(rset.getString(14));
				vVEHMantenimiento.setCResultado(rset.getString(15));
				vVEHMantenimiento.setCObservaciones(rset.getString(16));
				vVEHMantenimiento.setLConcluido(rset.getInt(17));
				vVEHMantenimiento.setIKilometraje(rset.getInt(18));
				vVEHMantenimiento.setDtRecepcion(rset.getDate(19));
				vVEHMantenimiento.setICveUsuSolicita(rset.getInt(20));
				cNombre = rset.getString(21) != null ? rset.getString(21) : "";
				cNombre += rset.getString(22) != null ? rset.getString(22)
						.length() != 0 ? " " + rset.getString(22) : "" : "";
				cNombre += rset.getString(23) != null ? rset.getString(23)
						.length() != 0 ? " " + rset.getString(23) : "" : "";
				vVEHMantenimiento.setCDscUsuSolicita(cNombre);
				vVEHMantenimiento.setICveUsuAutoriza(rset.getInt(24));
				cNombre = rset.getString(25) != null ? rset.getString(25) : "";
				cNombre += rset.getString(26) != null ? rset.getString(26)
						.length() != 0 ? " " + rset.getString(26) : "" : "";
				cNombre += rset.getString(27) != null ? rset.getString(27)
						.length() != 0 ? " " + rset.getString(27) : "" : "";
				vVEHMantenimiento.setCDscUsuAutoriza(cNombre);
				vVEHMantenimiento.setICveUsuRecibe(rset.getInt(28));
				cNombre = rset.getString(29) != null ? rset.getString(29) : "";
				cNombre += rset.getString(30) != null ? rset.getString(30)
						.length() != 0 ? " " + rset.getString(30) : "" : "";
				cNombre += rset.getString(31) != null ? rset.getString(31)
						.length() != 0 ? " " + rset.getString(31) : "" : "";
				vVEHMantenimiento.setCDscUsuRecibe(cNombre);
				vVEHMantenimiento.setLCancelado(rset.getInt(32));
				vVEHMantenimiento.setDtCancelacion(rset.getDate(33));
				vVEHMantenimiento.setCPlacas(rset.getString(34));
				vcVEHMantenimiento.addElement(vVEHMantenimiento);
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcVEHMantenimiento;
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);

			}
			String cSQL = "";
			TVVEHMantenimiento vVEHMantenimiento = (TVVEHMantenimiento) obj;

			cSQL = "SELECT MAX(iCveMantenimiento) FROM VEHMantenimiento WHERE iCveVehiculo = ?";
			pstmtMax = conn.prepareStatement(cSQL);
			pstmtMax.setInt(1, vVEHMantenimiento.getICveVehiculo());
			rsetMax = pstmtMax.executeQuery();
			if (rsetMax.next())
				iMax = rsetMax.getInt(1);
			iMax++;
			vVEHMantenimiento.setICveMantenimiento(iMax);
			cClave = "" + iMax;

			cSQL = "insert into VEHMantenimiento(" + "iCveVehiculo,"
					+ "iCveMantenimiento," + "iCveTpoMantto," + "dtSolicitud,"
					+ "dtProgramado," + "dtInicia," + "iCveEmpMantto,"
					+ "cAccesorios," + "cResultado," + "cObservaciones,"
					+ "lConcluido," + "iKilometraje," + "dtRecepcion,"
					+ "iCveUsuSolicita," + "iCveUsuAutoriza,"
					+ "iCveUsuRecibe," + "lCancelado," + "dtCancelacion"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vVEHMantenimiento.getICveVehiculo());
			pstmt.setInt(2, vVEHMantenimiento.getICveMantenimiento());
			pstmt.setInt(3, vVEHMantenimiento.getICveTpoMantto());
			pstmt.setDate(4, vVEHMantenimiento.getDtSolicitud());
			pstmt.setDate(5, vVEHMantenimiento.getDtProgramado());
			pstmt.setDate(6, vVEHMantenimiento.getDtInicia());
			pstmt.setInt(7, vVEHMantenimiento.getICveEmpMantto());
			if (vVEHMantenimiento.getCAccesorios() == null)
				pstmt.setNull(8, Types.VARCHAR);
			else
				pstmt.setString(8, vVEHMantenimiento.getCAccesorios()
						.toUpperCase().trim());
			if (vVEHMantenimiento.getCResultado() == null)
				pstmt.setNull(9, Types.VARCHAR);
			else
				pstmt.setString(9, vVEHMantenimiento.getCResultado()
						.toUpperCase().trim());
			if (vVEHMantenimiento.getCObservaciones() == null)
				pstmt.setNull(10, Types.VARCHAR);
			else
				pstmt.setString(10, vVEHMantenimiento.getCObservaciones()
						.toUpperCase().trim());
			pstmt.setInt(11, vVEHMantenimiento.getLConcluido());
			pstmt.setInt(12, vVEHMantenimiento.getIKilometraje());
			pstmt.setDate(13, vVEHMantenimiento.getDtRecepcion());
			pstmt.setInt(14, vVEHMantenimiento.getICveUsuSolicita());
			pstmt.setInt(15, vVEHMantenimiento.getICveUsuAutoriza());
			pstmt.setInt(16, vVEHMantenimiento.getICveUsuRecibe());
			pstmt.setInt(17, vVEHMantenimiento.getLCancelado());
			pstmt.setDate(18, vVEHMantenimiento.getDtCancelacion());
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
					dbConn.closeConnection();
				}
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVVEHMantenimiento vVEHMantenimiento = (TVVEHMantenimiento) obj;
			cSQL = "update VEHMantenimiento" + " set iCveTpoMantto= ?, "
					+ "dtSolicitud= ?, " + "dtProgramado= ?, "
					+ "dtInicia= ?, " + "iCveEmpMantto= ?, "
					+ "cAccesorios= ?, " + "cResultado= ?, "
					+ "cObservaciones= ?, " + "lConcluido= ?, "
					+ "iKilometraje= ?, " + "dtRecepcion= ?, "
					+ "iCveUsuSolicita= ?, " + "iCveUsuAutoriza= ?, "
					+ "iCveUsuRecibe= ?, " + "lCancelado= ?, "
					+ "dtCancelacion= ? " + "where iCveVehiculo = ? "
					+ " and iCveMantenimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHMantenimiento.getICveTpoMantto());
			pstmt.setDate(2, vVEHMantenimiento.getDtSolicitud());
			pstmt.setDate(3, vVEHMantenimiento.getDtProgramado());
			pstmt.setDate(4, vVEHMantenimiento.getDtInicia());
			pstmt.setInt(5, vVEHMantenimiento.getICveEmpMantto());
			if (vVEHMantenimiento.getCAccesorios() == null)
				pstmt.setNull(6, Types.VARCHAR);
			else
				pstmt.setString(6, vVEHMantenimiento.getCAccesorios()
						.toUpperCase().trim());
			if (vVEHMantenimiento.getCResultado() == null)
				pstmt.setNull(7, Types.VARCHAR);
			else
				pstmt.setString(7, vVEHMantenimiento.getCResultado()
						.toUpperCase().trim());
			if (vVEHMantenimiento.getCObservaciones() == null)
				pstmt.setNull(8, Types.VARCHAR);
			else
				pstmt.setString(8, vVEHMantenimiento.getCObservaciones()
						.toUpperCase().trim());
			pstmt.setInt(9, vVEHMantenimiento.getLConcluido());
			pstmt.setInt(10, vVEHMantenimiento.getIKilometraje());
			pstmt.setDate(11, vVEHMantenimiento.getDtRecepcion());
			pstmt.setInt(12, vVEHMantenimiento.getICveUsuSolicita());
			pstmt.setInt(13, vVEHMantenimiento.getICveUsuAutoriza());
			pstmt.setInt(14, vVEHMantenimiento.getICveUsuRecibe());
			pstmt.setInt(15, vVEHMantenimiento.getLCancelado());
			pstmt.setDate(16, vVEHMantenimiento.getDtCancelacion());
			pstmt.setInt(17, vVEHMantenimiento.getICveVehiculo());
			pstmt.setInt(18, vVEHMantenimiento.getICveMantenimiento());
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
					dbConn.closeConnection();
				}
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVVEHMantenimiento vVEHMantenimiento = (TVVEHMantenimiento) obj;
			cSQL = "delete from VEHMantenimiento" + " where iCveVehiculo = ?"
					+ " and iCveMantenimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHMantenimiento.getICveVehiculo());
			pstmt.setInt(2, vVEHMantenimiento.getICveMantenimiento());
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("delete.closeVEHMantenimiento", ex2);
			}
			return cClave;
		}
	}

	public Object insertWithSequence(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iMax = 0;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVVEHMantenimiento vVEHMantenimiento = (TVVEHMantenimiento) obj;
			cSQL = "SELECT MAX(iCveMantenimiento) FROM VEHMantenimiento WHERE iCveVehiculo= ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHMantenimiento.getICveVehiculo());
			rset = pstmt.executeQuery();
			if (rset.next())
				iMax = rset.getInt(1);
			iMax++;
			vVEHMantenimiento.setICveMantenimiento(iMax);
			cClave = "" + this.insert(conn, vVEHMantenimiento);
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insertWithSequence", ex1);
			}
			warn("insertWithSequence", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insertWithSequence.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All Consulta de Solicitudes
	 */
	public Vector FindByAllConsSol(String sWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHMantenimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "";
			TVVEHMantenimiento vVEHMantenimiento;
			int count;
			cSQL = "Select VehVehiculo.iCveVehiculo, VehMantenimiento.dtProgramado, VehTpoMantto.cDscBreve, "
					+ "VehTpoVehiculo.cDscBreve, GrlUniMed.cDscUniMed, VehVehiculo.cPlacas, "
					+ "VehMantenimiento.dtInicia, VehMantenimiento.iCveMantenimiento, VehMantenimiento.dtSolicitud, "
					+ " VehMantenimiento.lConcluido, VehMantenimiento.lCancelado "
					+ "from VehMantenimiento "
					+ "Left Join VehTpoMantto on VehMantenimiento.iCveTpoMantto=VehTpoMantto.iCveTpoMantto "
					+ "join VehVehiculo on VehVehiculo.iCveVehiculo=VehMantenimiento.iCveVehiculo "
					+ "Left Join VehTpoVehiculo on VehVehiculo.iCveTpoVehiculo=VehTpoVehiculo.iCveTpoVehiculo "
					+ "join VehUbicacion on VehUbicacion.iCveVehiculo=VehVehiculo.iCveVehiculo "
					+ "     and VehUbicacion.lActivo = 1 "
					+ "left join GrlUniMed on GrlUniMed.iCveUniMed=VehUbicacion.iCveUniMed "
					+
					// "Where VehMantenimiento.lConcluido = 0 and VehMantenimiento.lCancelado=0 "
					// +
					" Where 1 = 1 " + sWhere;
			// System.out.println("Busqueda Veh�culos = " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			String cNombre = "";
			while (rset.next()) {
				vVEHMantenimiento = new TVVEHMantenimiento();
				vVEHMantenimiento.setICveVehiculo(rset.getInt(1));
				vVEHMantenimiento.setDtProgramado(rset.getDate(2));
				vVEHMantenimiento.setCDscTpoMantto(rset.getString(3));
				vVEHMantenimiento.setCDscTpoVehiculo(rset.getString(4));
				vVEHMantenimiento.setCObservaciones(rset.getString(5));
				vVEHMantenimiento.setCPlacas(rset.getString(6));
				vVEHMantenimiento.setDtInicia(rset.getDate(7));
				vVEHMantenimiento.setICveMantenimiento(rset.getInt(8));
				vVEHMantenimiento.setDtSolicitud(rset.getDate(9));
				vVEHMantenimiento.setLConcluido(rset.getInt("lConcluido"));
				vVEHMantenimiento.setLCancelado(rset.getInt("lCancelado"));
				vcVEHMantenimiento.addElement(vVEHMantenimiento);
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcVEHMantenimiento;
		}
	}

	/**
	 * Metodo update
	 */
	public Object updateSolicitudes(Connection conGral, Object obj)
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVVEHMantenimiento vVEHMantenimiento = (TVVEHMantenimiento) obj;
			cSQL = "update VEHMantenimiento " + "set dtInicia= ?, "
					+ "iCveUsuAutoriza= ? " + "where iCveVehiculo = ? "
					+ " and iCveMantenimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vVEHMantenimiento.getDtInicia());
			pstmt.setInt(2, vVEHMantenimiento.getICveUsuAutoriza());
			pstmt.setInt(3, vVEHMantenimiento.getICveVehiculo());
			pstmt.setInt(4, vVEHMantenimiento.getICveMantenimiento());
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All Detalle Mantenimiento
	 */
	public Vector FindByAllDetalle(String sWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHMantenimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "";
			TVVEHMantenimiento vVEHMantenimiento;
			int count;
			cSQL = "Select "
					+ "VehMantenimiento.iCveVehiculo, VehMantenimiento.iCveMantenimiento, VehMantenimiento.iCveTpoMantto, "
					+ "VehMantenimiento.dtSolicitud, VehMantenimiento.iCveUsuSolicita, VehMantenimiento.dtInicia, "
					+ "VehMantenimiento.dtProgramado, VehMantenimiento.iCveEmpMantto, VehMantenimiento.iKilometraje, "
					+ "VehMantenimiento.iCveUsuAutoriza, VehMantenimiento.cAccesorios, VehMantenimiento.cResultado, "
					+ "VehMantenimiento.cObservaciones, VehMantenimiento.dtRecepcion, VehMantenimiento.iCveUsuRecibe, "
					+ "VehMantenimiento.lConcluido, VehMantenimiento.lCancelado, VehMantenimiento.dtCancelacion, "
					+ "VehTpoMantto.cDscBreve, U1.cNombre, U1.cApPaterno, U1.cApMaterno, "
					+ "U2.cNombre, U2.cApPaterno, U2.cApMaterno, U3.cNombre, U3.cApPaterno, U3.cApMaterno "
					+ "from VehMantenimiento "
					+ "Left Join VehTpoMantto on VehMantenimiento.iCveTpoMantto=VehTpoMantto.iCveTpoMantto "
					+ "Left Join SegUsuario U1 on U1.iCveUsuario = VehMantenimiento.iCveUsuSolicita "
					+ "Left Join SegUsuario U2 on U2.iCveUsuario = VehMantenimiento.iCveUsuAutoriza "
					+ "Left Join SegUsuario U3 on U3.iCveUsuario = VehMantenimiento.iCveUsuRecibe "
					+ sWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			String cNombre = "";
			while (rset.next()) {
				vVEHMantenimiento = new TVVEHMantenimiento();
				vVEHMantenimiento.setICveVehiculo(rset.getInt(1));
				vVEHMantenimiento.setICveMantenimiento(rset.getInt(2));
				vVEHMantenimiento.setICveTpoMantto(rset.getInt(3));
				vVEHMantenimiento.setDtSolicitud(rset.getDate(4));
				vVEHMantenimiento.setICveUsuSolicita(rset.getInt(5));
				vVEHMantenimiento.setDtInicia(rset.getDate(6));
				vVEHMantenimiento.setDtProgramado(rset.getDate(7));
				vVEHMantenimiento.setICveEmpMantto(rset.getInt(8));
				vVEHMantenimiento.setIKilometraje(rset.getInt(9));
				vVEHMantenimiento.setICveUsuAutoriza(rset.getInt(10));
				vVEHMantenimiento.setCAccesorios(rset.getString(11));
				vVEHMantenimiento.setCResultado(rset.getString(12));
				vVEHMantenimiento.setCObservaciones(rset.getString(13));
				vVEHMantenimiento.setDtRecepcion(rset.getDate(14));
				vVEHMantenimiento.setICveUsuRecibe(rset.getInt(15));
				vVEHMantenimiento.setLConcluido(rset.getInt(16));
				vVEHMantenimiento.setLCancelado(rset.getInt(17));
				vVEHMantenimiento.setDtCancelacion(rset.getDate(18));
				vVEHMantenimiento.setCDscTpoMantto(rset.getString(19));
				cNombre = "";
				if (rset.getInt(5) > 0) {
					cNombre = rset.getString(20).equals("null") ? "" : rset
							.getString(20);
					cNombre += rset.getString(21).equals("null") ? "" : " "
							+ rset.getString(21);
					cNombre += rset.getString(22).equals("null") ? "" : " "
							+ rset.getString(22);
				}
				vVEHMantenimiento.setCDscUsuSolicita(cNombre);
				cNombre = "";
				if (rset.getInt(10) > 0) {
					cNombre = rset.getString(23).equals("null") ? "" : rset
							.getString(23);
					cNombre += rset.getString(24).equals("null") ? "" : " "
							+ rset.getString(24);
					cNombre += rset.getString(25).equals("null") ? "" : " "
							+ rset.getString(25);
				}
				vVEHMantenimiento.setCDscUsuAutoriza(cNombre);
				cNombre = "";
				if (rset.getInt(15) > 0) {
					cNombre = rset.getString(26).equals("null") ? "" : rset
							.getString(26);
					cNombre += rset.getString(27).equals("null") ? "" : " "
							+ rset.getString(27);
					cNombre += rset.getString(28).equals("null") ? "" : " "
							+ rset.getString(28);
				}
				vVEHMantenimiento.setCDscUsuRecibe(cNombre);
				vcVEHMantenimiento.addElement(vVEHMantenimiento);
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcVEHMantenimiento;
		}
	}

	/**
	 * Metodo Find By All Consulta de Solicitudes
	 */
	public Vector FindByAllUsuarios(String sWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHMantenimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "";
			TVVEHMantenimiento vVEHMantenimiento;
			int count;
			cSQL = "Select Distinct(GrlUmUsuario.iCveUsuario), "
					+ "SegUsuario.cNombre || ' ' || SegUsuario.cApPaterno || ' ' || SegUsuario.cApMaterno "
					+ "From GrlUmUsuario "
					+ "Join SegUsuario on GrlUmUsuario.iCveUsuario=SegUsuario.iCveUsuario"
					+ sWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			String cNombre = "";
			while (rset.next()) {
				vVEHMantenimiento = new TVVEHMantenimiento();
				vVEHMantenimiento.setICveUsuAutoriza(rset.getInt(1));
				vVEHMantenimiento.setCDscUsuAutoriza(rset.getString(2));
				vcVEHMantenimiento.addElement(vVEHMantenimiento);
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcVEHMantenimiento;
		}
	}

	/**
	 * Metodo update Detalle de los Mantenimientos
	 */
	public Object updateDetMan(Connection conGral, Object obj)
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TFechas hoy = new TFechas();
			TVVEHMantenimiento vVEHMantenimiento = (TVVEHMantenimiento) obj;
			cSQL = "update VEHMantenimiento" + " set iCveEmpMantto= ?, "
					+ "cAccesorios= ?, " + "cResultado= ?, "
					+ "cObservaciones= ?, " + "lConcluido= ?, "
					+ "iKilometraje= ?, " + "dtRecepcion= ?, "
					+ "iCveUsuAutoriza= ?, " + "iCveUsuRecibe= ?, "
					+ "lCancelado= ?, " + "dtCancelacion= ? "
					+ "where iCveVehiculo = ? " + " and iCveMantenimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHMantenimiento.getICveEmpMantto());
			pstmt.setString(2, vVEHMantenimiento.getCAccesorios().toUpperCase()
					.trim());
			pstmt.setString(3, vVEHMantenimiento.getCResultado().toUpperCase()
					.trim());
			pstmt.setString(4, vVEHMantenimiento.getCObservaciones()
					.toUpperCase().trim());
			pstmt.setInt(5, vVEHMantenimiento.getLConcluido());
			pstmt.setInt(6, vVEHMantenimiento.getIKilometraje());
			pstmt.setDate(7, vVEHMantenimiento.getDtRecepcion());
			pstmt.setInt(8, vVEHMantenimiento.getICveUsuAutoriza());
			pstmt.setInt(9, vVEHMantenimiento.getICveUsuRecibe());
			pstmt.setInt(10, vVEHMantenimiento.getLCancelado());
			if (vVEHMantenimiento.getLCancelado() == 1)
				vVEHMantenimiento.setDtCancelacion(hoy.TodaySQL());
			pstmt.setDate(11, vVEHMantenimiento.getDtCancelacion());
			pstmt.setInt(12, vVEHMantenimiento.getICveVehiculo());
			pstmt.setInt(13, vVEHMantenimiento.getICveMantenimiento());
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}
}
