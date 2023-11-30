package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EQMMantenimiento DAO
 * </p>
 * <p>
 * Description: DAO Tabla EQMMantenimiento
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @author LSC Rafael Miranda Blumenkron
 * @version 1.0
 */

public class TDEQMMantenimiento extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEQMMantenimiento() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMMantenimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMMantenimiento vEQMMantenimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEquipo," + "iCveMantenimiento,"
					+ "dtSolicitud," + "dtProgramado," + "iCveTpoMantto,"
					+ "iCveMotivo," + "cAccesorios," + "cAnalisisOper,"
					+ "iCveEmpMtto," + "cNombre," + "cResultado,"
					+ "cObservaciones," + "lConcluido," + "dtRecepcion,"
					+ "iCveUsuSolicita," + "iCveUsuAutoriza,"
					+ "iCveUsuRecibe," + "lCancelado," + "dtCancelacion"
					+ " from EQMMantenimiento order by iCveEquipo";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMMantenimiento = new TVEQMMantenimiento();
				vEQMMantenimiento.setICveEquipo(rset.getInt(1));
				vEQMMantenimiento.setICveMantenimiento(rset.getInt(2));
				vEQMMantenimiento.setDtSolicitud(rset.getDate(3));
				vEQMMantenimiento.setDtProgramado(rset.getDate(4));
				vEQMMantenimiento.setICveTpoMantto(rset.getInt(5));
				vEQMMantenimiento.setICveMotivo(rset.getInt(6));
				vEQMMantenimiento.setCAccesorios(rset.getString(7));
				vEQMMantenimiento.setCAnalisisOper(rset.getString(8));
				vEQMMantenimiento.setICveEmpMtto(rset.getInt(9));
				vEQMMantenimiento.setCNombre(rset.getString(10));
				vEQMMantenimiento.setCResultado(rset.getString(11));
				vEQMMantenimiento.setCObservaciones(rset.getString(12));
				vEQMMantenimiento.setLConcluido(rset.getInt(13));
				vEQMMantenimiento.setDtRecepcion(rset.getDate(14));
				vEQMMantenimiento.setICveUsuSolicita(rset.getInt(15));
				vEQMMantenimiento.setICveUsuAutoriza(rset.getInt(16));
				vEQMMantenimiento.setICveUsuRecibe(rset.getInt(17));
				vEQMMantenimiento.setLCancelado(rset.getInt(18));
				vEQMMantenimiento.setDtCancelacion(rset.getDate(19));
				vcEQMMantenimiento.addElement(vEQMMantenimiento);
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
			return vcEQMMantenimiento;
		}
	}

	/**
	 * Metodo Find By All con filtro
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMMantenimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMMantenimiento vEQMMantenimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEquipo," + "iCveMantenimiento,"
					+ "dtSolicitud," + "dtProgramado," + "iCveTpoMantto,"
					+ "iCveMotivo," + "cAccesorios," + "cAnalisisOper,"
					+ "iCveEmpMtto," + "cNombre," + "cResultado,"
					+ "cObservaciones," + "lConcluido," + "dtRecepcion,"
					+ "iCveUsuSolicita," + "iCveUsuAutoriza,"
					+ "iCveUsuRecibe," + "lCancelado," + "dtCancelacion"
					+ " from EQMMantenimiento " + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMMantenimiento = new TVEQMMantenimiento();
				vEQMMantenimiento.setICveEquipo(rset.getInt(1));
				vEQMMantenimiento.setICveMantenimiento(rset.getInt(2));
				vEQMMantenimiento.setDtSolicitud(rset.getDate(3));
				vEQMMantenimiento.setDtProgramado(rset.getDate(4));
				vEQMMantenimiento.setICveTpoMantto(rset.getInt(5));
				vEQMMantenimiento.setICveMotivo(rset.getInt(6));
				vEQMMantenimiento.setCAccesorios(rset.getString(7));
				vEQMMantenimiento.setCAnalisisOper(rset.getString(8));
				vEQMMantenimiento.setICveEmpMtto(rset.getInt(9));
				vEQMMantenimiento.setCNombre(rset.getString(10));
				vEQMMantenimiento.setCResultado(rset.getString(11));
				vEQMMantenimiento.setCObservaciones(rset.getString(12));
				vEQMMantenimiento.setLConcluido(rset.getInt(13));
				vEQMMantenimiento.setDtRecepcion(rset.getDate(14));
				vEQMMantenimiento.setICveUsuSolicita(rset.getInt(15));
				vEQMMantenimiento.setICveUsuAutoriza(rset.getInt(16));
				vEQMMantenimiento.setICveUsuRecibe(rset.getInt(17));
				vEQMMantenimiento.setLCancelado(rset.getInt(18));
				vEQMMantenimiento.setDtCancelacion(rset.getDate(19));
				vcEQMMantenimiento.addElement(vEQMMantenimiento);
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
			return vcEQMMantenimiento;
		}
	}

	/**
	 * Metodo Find By All con Filtro y Orden
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMMantenimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMMantenimiento vEQMMantenimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select  "
					+ "       EQMMantenimiento.iCveEquipo,   "
					+ "       EQMMantenimiento.iCveMantenimiento,   "
					+ "       EQMMantenimiento.dtSolicitud,   "
					+ "       EQMMantenimiento.dtProgramado,   "
					+ "       EQMMantenimiento.iCveTpoMantto,   "
					+ "       EQMMantenimiento.iCveMotivo,   "
					+ "       EQMMantenimiento.cAccesorios,   "
					+ "       EQMMantenimiento.cAnalisisOper,   "
					+ "       EQMMantenimiento.iCveEmpMtto,   "
					+ "       EQMMantenimiento.cNombre,   "
					+ "       EQMMantenimiento.cResultado,   "
					+ "       EQMMantenimiento.cObservaciones,   "
					+ "       EQMMantenimiento.lConcluido,   "
					+ "       EQMMantenimiento.dtRecepcion,   "
					+ "       EQMMantenimiento.iCveUsuSolicita,   "
					+ "       EQMMantenimiento.iCveUsuAutoriza,   "
					+ "       EQMMantenimiento.iCveUsuRecibe,   "
					+ "       EQMMantenimiento.lCancelado,   "
					+ "       EQMMantenimiento.dtCancelacion,   "
					+ "       EQMTpoMantto.cDscBreve,   "
					+ "       GRLMotivo.cDscMotivo, "
					+ "       EQMEquipo.cDscEquipo, "
					+ "       EQMEquipo.cNumSerie, "
					+ "       EQMEquipo.cInventario, "
					+ "       GRLUniMed.cDscUniMed, "
					+ "       GRLModulo.cDscModulo, "
					+ "       GRLArea.cDscArea, "
					+ "       EQMTpoEquipo.cDscBreve, "
					+ "       (SEGUsuario.cNombre || ' ' || SEGUsuario.cApPaterno || ' ' || SEGUsuario.cApMaterno) as cDscUsuSolicita "
					+ "       from EQMMantenimiento "
					+ " join EQMTpoMantto ON EQMTpoMantto.iCveTpoMantto = EQMMantenimiento.iCveTpoMantto   "
					+ " join GRLMotivo ON GRLMotivo.iCveMotivo = EQMMantenimiento.iCveMotivo  "
					+ " join EQMEquipo ON EQMEquipo.iCveEquipo = EQMMantenimiento.iCveEquipo "
					+ " join EQMAsignacion ON EQMAsignacion.iCveEquipo = EQMMantenimiento.iCveEquipo AND "
					+ "                                              EQMAsignacion.lActual = 1 "
					+ " join GRLUniMed ON GRLUniMed.iCveUniMed = EQMAsignacion.iCveUniMed "
					+ " join GRLModulo ON GRLModulo.iCveUniMed = EQMAsignacion.iCveUniMed AND "
					+ "                                      GRLModulo.iCveModulo = EQMAsignacion.iCveModulo "
					+ " join GRLArea ON GRLArea.iCveArea = EQMAsignacion.iCveArea "
					+ " join EQMTpoEquipo ON EQMTpoEquipo.iCveClasificacion = EQMEquipo.iCveClasificacion AND "
					+ "                      EQMTpoEquipo.iCveTpoEquipo = EQMEquipo.iCveTpoEquipo "
					+ " join SEGUsuario ON SEGUsuario.iCveUsuario = EQMMantenimiento.iCveUsuSolicita "
					+ cFiltro + " " + cOrden;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMMantenimiento = new TVEQMMantenimiento();
				vEQMMantenimiento.setICveEquipo(rset.getInt(1));
				vEQMMantenimiento.setICveMantenimiento(rset.getInt(2));
				vEQMMantenimiento.setDtSolicitud(rset.getDate(3));
				vEQMMantenimiento.setDtProgramado(rset.getDate(4));
				vEQMMantenimiento.setICveTpoMantto(rset.getInt(5));
				vEQMMantenimiento.setICveMotivo(rset.getInt(6));
				vEQMMantenimiento.setCAccesorios(rset.getString(7));
				vEQMMantenimiento.setCAnalisisOper(rset.getString(8));
				vEQMMantenimiento.setICveEmpMtto(rset.getInt(9));
				vEQMMantenimiento.setCNombre(rset.getString(10));
				vEQMMantenimiento.setCResultado(rset.getString(11));
				vEQMMantenimiento.setCObservaciones(rset.getString(12));
				vEQMMantenimiento.setLConcluido(rset.getInt(13));
				vEQMMantenimiento.setDtRecepcion(rset.getDate(14));
				vEQMMantenimiento.setICveUsuSolicita(rset.getInt(15));
				vEQMMantenimiento.setICveUsuAutoriza(rset.getInt(16));
				vEQMMantenimiento.setICveUsuRecibe(rset.getInt(17));
				vEQMMantenimiento.setLCancelado(rset.getInt(18));
				vEQMMantenimiento.setDtCancelacion(rset.getDate(19));
				vEQMMantenimiento.setCDscTpoMantto(rset.getString(20));
				vEQMMantenimiento.setCDscMotivo(rset.getString(21));
				vEQMMantenimiento.setCDscEquipo(rset.getString(22));
				vEQMMantenimiento.setCNumSerie(rset.getString(23));
				vEQMMantenimiento.setCInventario(rset.getString(24));
				vEQMMantenimiento.setCDscUniMed(rset.getString(25));
				vEQMMantenimiento.setCDscModulo(rset.getString(26));
				vEQMMantenimiento.setCDscArea(rset.getString(27));
				vEQMMantenimiento.setCDscTpoEquipo(rset.getString(28));

				java.util.Date dtFechaTmp = new java.util.Date();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				dtFechaTmp = rset.getDate(4);
				if (dtFechaTmp != null)
					vEQMMantenimiento.setCDtProgramado(formato
							.format(dtFechaTmp));
				else
					vEQMMantenimiento.setCDtProgramado("");
				vEQMMantenimiento.setCDscUsuSolicita(rset.getString(29));

				vcEQMMantenimiento.addElement(vEQMMantenimiento);
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
			return vcEQMMantenimiento;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		String cClave = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEQMMantenimiento vEQMMantenimiento = (TVEQMMantenimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "select max(iCveMantenimiento) from EQMMantenimiento where iCveEquipo = "
					+ vEQMMantenimiento.getICveEquipo();
			pstmtMax = conn.prepareStatement(cSQL);
			rsetMax = pstmtMax.executeQuery();

			if (rsetMax.next()) {
				iMax = rsetMax.getInt(1) + 1;
				vEQMMantenimiento.setICveMantenimiento(iMax);
			} else {
				iMax = 1;
				vEQMMantenimiento.setICveMantenimiento(iMax);
			}
			cClave = "" + iMax;
			cSQL = "insert into EQMMantenimiento(" + "iCveEquipo,"
					+ "iCveMantenimiento," + "dtSolicitud," + "dtProgramado,"
					+ "iCveTpoMantto," + "iCveMotivo," + "cAccesorios,"
					+ "cAnalisisOper," + "iCveEmpMtto," + "cNombre,"
					+ "cResultado," + "cObservaciones," + "lConcluido,"
					+ "dtRecepcion," + "iCveUsuSolicita," + "iCveUsuAutoriza,"
					+ "iCveUsuRecibe," + "lCancelado," + "dtCancelacion"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vEQMMantenimiento.getICveEquipo());
			pstmt.setInt(2, vEQMMantenimiento.getICveMantenimiento());

			if (vEQMMantenimiento.getDtSolicitud() == null)
				pstmt.setNull(3, Types.DATE);
			else
				pstmt.setDate(3, vEQMMantenimiento.getDtSolicitud());

			if (vEQMMantenimiento.getDtProgramado() == null)
				pstmt.setNull(4, Types.DATE);
			else
				pstmt.setDate(4, vEQMMantenimiento.getDtProgramado());

			pstmt.setInt(5, vEQMMantenimiento.getICveTpoMantto());
			pstmt.setInt(6, vEQMMantenimiento.getICveMotivo());

			if (vEQMMantenimiento.getCAccesorios() == null)
				pstmt.setNull(7, Types.VARCHAR);
			else
				pstmt.setString(7, vEQMMantenimiento.getCAccesorios()
						.toUpperCase().trim());

			if (vEQMMantenimiento.getCAnalisisOper() == null)
				pstmt.setNull(8, Types.VARCHAR);
			else
				pstmt.setString(8, vEQMMantenimiento.getCAnalisisOper()
						.toUpperCase().trim());

			pstmt.setInt(9, vEQMMantenimiento.getICveEmpMtto());

			if (vEQMMantenimiento.getCNombre() == null)
				pstmt.setNull(10, Types.VARCHAR);
			else
				pstmt.setString(10, vEQMMantenimiento.getCNombre()
						.toUpperCase().trim());

			if (vEQMMantenimiento.getCResultado() == null)
				pstmt.setNull(11, Types.VARCHAR);
			else
				pstmt.setString(11, vEQMMantenimiento.getCResultado()
						.toUpperCase().trim());

			if (vEQMMantenimiento.getCObservaciones() == null)
				pstmt.setNull(12, Types.VARCHAR);
			else
				pstmt.setString(12, vEQMMantenimiento.getCObservaciones()
						.toUpperCase().trim());

			pstmt.setInt(13, vEQMMantenimiento.getLConcluido());

			if (vEQMMantenimiento.getDtRecepcion() == null)
				pstmt.setNull(14, Types.DATE);
			else
				pstmt.setDate(14, vEQMMantenimiento.getDtRecepcion());

			pstmt.setInt(15, vEQMMantenimiento.getICveUsuSolicita());
			pstmt.setInt(16, vEQMMantenimiento.getICveUsuAutoriza());
			pstmt.setInt(17, vEQMMantenimiento.getICveUsuRecibe());
			pstmt.setInt(18, vEQMMantenimiento.getLCancelado());

			if (vEQMMantenimiento.getDtCancelacion() == null)
				pstmt.setNull(19, Types.DATE);
			else
				pstmt.setDate(19, vEQMMantenimiento.getDtCancelacion());

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
			TVEQMMantenimiento vEQMMantenimiento = (TVEQMMantenimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMMantenimiento" + " set dtSolicitud= ?, "
					+ "dtProgramado= ?, " + "iCveTpoMantto= ?, "
					+ "iCveMotivo= ?, " + "cAccesorios= ?, "
					+ "cAnalisisOper= ?, " + "iCveEmpMtto= ?, "
					+ "cNombre= ?, " + "cResultado= ?, "
					+ "cObservaciones= ?, " + "lConcluido= ?, "
					+ "dtRecepcion= ?, " + "iCveUsuSolicita= ?, "
					+ "iCveUsuAutoriza= ?, " + "iCveUsuRecibe= ?, "
					+ "lCancelado= ?, " + "dtCancelacion= ? "
					+ "where iCveEquipo = ? " + " and iCveMantenimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vEQMMantenimiento.getDtSolicitud());
			pstmt.setDate(2, vEQMMantenimiento.getDtProgramado());
			pstmt.setInt(3, vEQMMantenimiento.getICveTpoMantto());
			pstmt.setInt(4, vEQMMantenimiento.getICveMotivo());
			pstmt.setString(5, vEQMMantenimiento.getCAccesorios().toUpperCase()
					.trim());
			pstmt.setString(6, vEQMMantenimiento.getCAnalisisOper()
					.toUpperCase().trim());
			pstmt.setInt(7, vEQMMantenimiento.getICveEmpMtto());
			pstmt.setString(8, vEQMMantenimiento.getCNombre().toUpperCase()
					.trim());
			pstmt.setString(9, vEQMMantenimiento.getCResultado().toUpperCase()
					.trim());
			pstmt.setString(10, vEQMMantenimiento.getCObservaciones()
					.toUpperCase().trim());
			pstmt.setInt(11, vEQMMantenimiento.getLConcluido());
			pstmt.setDate(12, vEQMMantenimiento.getDtRecepcion());
			pstmt.setInt(13, vEQMMantenimiento.getICveUsuSolicita());
			pstmt.setInt(14, vEQMMantenimiento.getICveUsuAutoriza());
			pstmt.setInt(15, vEQMMantenimiento.getICveUsuRecibe());
			pstmt.setInt(16, vEQMMantenimiento.getLCancelado());
			pstmt.setDate(17, vEQMMantenimiento.getDtCancelacion());
			pstmt.setInt(18, vEQMMantenimiento.getICveEquipo());
			pstmt.setInt(19, vEQMMantenimiento.getICveMantenimiento());
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
	 * Metodo updateCancelado - Actualiza los valores de la Cancelaci�n del
	 * Mantenimiento.
	 */
	public Object updateCancelado(Connection conGral, Object obj)
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
			TVEQMMantenimiento vEQMMantenimiento = (TVEQMMantenimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update EQMMantenimiento" + "    set lCancelado= ?, "
					+ "        dtCancelacion= ? " + "  where iCveEquipo = ? "
					+ "   and  iCveMantenimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMMantenimiento.getLCancelado());
			pstmt.setDate(2, vEQMMantenimiento.getDtCancelacion());
			pstmt.setInt(3, vEQMMantenimiento.getICveEquipo());
			pstmt.setInt(4, vEQMMantenimiento.getICveMantenimiento());
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
				warn("updateCancelado", ex1);
			}
			warn("updateCancelado", ex);
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
				warn("updateCancelado.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo updateConcluir - Actualiza los valor de lConcluido.
	 */
	public Object updateConcluir(Connection conGral, Object obj)
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
			TVEQMMantenimiento vEQMMantenimiento = (TVEQMMantenimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update EQMMantenimiento" + "    set lConcluido= ? "
					+ "  where iCveEquipo = ? "
					+ "    and iCveMantenimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMMantenimiento.getLConcluido());
			pstmt.setInt(2, vEQMMantenimiento.getICveEquipo());
			pstmt.setInt(3, vEQMMantenimiento.getICveMantenimiento());
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
				warn("updateConcluir", ex1);
			}
			warn("updateConcluir", ex);
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
				warn("updateConcluir.close", ex2);
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
			TVEQMMantenimiento vEQMMantenimiento = (TVEQMMantenimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EQMMantenimiento" + " where iCveEquipo = ?"
					+ " and iCveMantenimiento = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMMantenimiento.getICveEquipo());
			pstmt.setInt(2, vEQMMantenimiento.getICveMantenimiento());
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
				warn("delete.closeEQMMantenimiento", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllListaMantto(String where) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMMantenimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMMantenimiento vEQMMantenimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select EqmMantenimiento.iCveMantenimiento,EqmMantenimiento.dtSolicitud, "
					+ "EqmTpoMantto.cDscBreve,GrlMotivo.cDscMotivo,EqmMantenimiento.dtProgramado "
					+ "from EqmMantenimiento "
					+ "join EqmTpoMantto on EqmTpoMantto.iCveTpoMantto=EqmMantenimiento.iCveTpoMantto "
					+ "join GrlMotivo on GrlMotivo.iCveMotivo=EqmMantenimiento.iCveMotivo "
					+ where;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMMantenimiento = new TVEQMMantenimiento();
				vEQMMantenimiento.setICveMantenimiento(rset.getInt(1));
				vEQMMantenimiento.setDtSolicitud(rset.getDate(2));
				vEQMMantenimiento.setCDscTpoMantto(rset.getString(3));
				vEQMMantenimiento.setCDscMotivo(rset.getString(4));
				vEQMMantenimiento.setDtProgramado(rset.getDate(5));
				vcEQMMantenimiento.addElement(vEQMMantenimiento);
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
			return vcEQMMantenimiento;
		}
	}

	/**
	 * Metodo Find By All Consulta Programaci�n
	 */
	public Vector FindByAllConsProgr(String sWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMMantenimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMMantenimiento vEQMMantenimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select EqmMantenimiento.iCveEquipo,EqmMantenimiento.iCvemantenimiento, EqmMantenimiento.dtProgramado, "
					+ "EqmTpoEquipo.cDscBreve, EqmEquipo.cDscEquipo, EqmEquipo.cNumSerie, EqmEquipo.cInventario, "
					+ "GrlUniMed.cDscUnimed, GrlModulo.cDscModulo, GrlArea.cDscArea "
					+ "from EqmMantenimiento "
					+ "join EqmEquipo on EqmEquipo.iCveEquipo=EqmMantenimiento.iCveEquipo "
					+ "join EqmTpoEquipo on EqmTpoEquipo.iCveTpoEquipo=EqmEquipo.iCveTpoEquipo "
					+ "and EqmTpoEquipo.iCveClasificacion=EqmEquipo.iCveClasificacion "
					+ "join EqmAsignacion on EqmAsignacion.iCveEquipo=EqmMantenimiento.iCveEquipo "
					+ "and EqmAsignacion.lActual=1 "
					+ "join GrlUniMed on GrlUniMed.iCveUniMed=EqmAsignacion.iCveUniMed "
					+ "join GrlModulo on GrlModulo.iCveUniMed=EqmAsignacion.iCveUniMed "
					+ "and GrlModulo.iCveModulo=EqmAsignacion.iCveModulo "
					+ "join GrlArea on GrlArea.iCveArea=EqmAsignacion.iCveArea "
					+ sWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMMantenimiento = new TVEQMMantenimiento();
				vEQMMantenimiento.setICveEquipo(rset.getInt(1));
				vEQMMantenimiento.setICveMantenimiento(rset.getInt(2));
				vEQMMantenimiento.setDtProgramado(rset.getDate(3));
				vEQMMantenimiento.setCDscTpoEquipo(rset.getString(4));
				vEQMMantenimiento.setCDscEquipo(rset.getString(5));
				vEQMMantenimiento.setCNumSerie(rset.getString(6));
				vEQMMantenimiento.setCInventario(rset.getString(7));
				vEQMMantenimiento.setCDscUniMed(rset.getString(8));
				vEQMMantenimiento.setCDscModulo(rset.getString(9));
				vEQMMantenimiento.setCDscArea(rset.getString(10));
				vcEQMMantenimiento.addElement(vEQMMantenimiento);
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
			return vcEQMMantenimiento;
		}
	}

	/**
	 * Metodo FindForReport para un equipo y mantenimiento espec�ficos
	 */
	public Vector FindForReport(int iCveEquipo, int iCveMantto)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMMantenimiento = new Vector();
		String cTemp = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMMantenimiento vEQMMantenimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select "
					+ " EQMMantenimiento.iCveEquipo, EQMMantenimiento.iCveMantenimiento, "
					+ " EQMMantenimiento.dtSolicitud, EQMMantenimiento.dtProgramado, "
					+ " EQMMantenimiento.iCveTpoMantto, EQMMantenimiento.iCveMotivo, "
					+ " EQMMantenimiento.cAccesorios, EQMMantenimiento.cAnalisisOper, "
					+ " EQMMantenimiento.iCveEmpMtto, EQMMantenimiento.cNombre, "
					+ " EQMMantenimiento.cResultado, EQMMantenimiento.cObservaciones, "
					+ " EQMMantenimiento.lConcluido, EQMMantenimiento.dtRecepcion, "
					+ " EQMMantenimiento.iCveUsuSolicita, EQMMantenimiento.iCveUsuAutoriza, "
					+ " EQMMantenimiento.iCveUsuRecibe, EQMMantenimiento.lCancelado, "
					+ " EQMMantenimiento.dtCancelacion, "
					+ " EQMTpoMantto.cDscTpoMantto, "
					+ " GRLMotivo.cDscMotivo, "
					+ " EQMEquipo.cCveEquipo, EQMEquipo.cDscEquipo, "
					+ " EQMEquipo.cModelo, EQMEquipo.cNumSerie, EQMEquipo.cInventario, "
					+ " EQMMarca.cDscMarca, "
					+ " GRLUniMed.cDscUniMed, GRLModulo.cDscModulo, GRLArea.cDscArea, "
					+ " EQMTpoEquipo.cDscTpoEquipo, "
					+ " USR1.cNombre, USR1.cApPaterno, USR1.cApMaterno, "
					+ " USR2.cNombre, USR2.cApPaterno, USR2.cApMaterno, "
					+ " USR3.cNombre, USR3.cApPaterno, USR3.cApMaterno, "
					+ " EQMEmpMtto.cNombreRS, EQMEmpMtto.cApPaterno, EQMEmpMtto.cApMaterno "
					+ "       from EQMMantenimiento "
					+ " LEFT JOIN EQMTpoMantto ON EQMTpoMantto.iCveTpoMantto = EQMMantenimiento.iCveTpoMantto   "
					+ " LEFT JOIN GRLMotivo ON GRLMotivo.iCveMotivo = EQMMantenimiento.iCveMotivo  "
					+ " LEFT JOIN EQMEquipo ON EQMEquipo.iCveEquipo = EQMMantenimiento.iCveEquipo "
					+ " LEFT JOIN EQMMarca ON EQMMarca.iCveMarca = EQMEquipo.iCveMarca "
					+ " LEFT JOIN EQMAsignacion ON EQMAsignacion.iCveEquipo = EQMMantenimiento.iCveEquipo "
					+ "                        AND EQMAsignacion.lActual = 1 "
					+ " LEFT JOIN GRLUniMed ON GRLUniMed.iCveUniMed = EQMAsignacion.iCveUniMed "
					+ " LEFT JOIN GRLModulo ON GRLModulo.iCveUniMed = EQMAsignacion.iCveUniMed "
					+ "                    AND GRLModulo.iCveModulo = EQMAsignacion.iCveModulo "
					+ " LEFT JOIN GRLArea ON GRLArea.iCveArea = EQMAsignacion.iCveArea "
					+ " LEFT JOIN EQMTpoEquipo ON EQMTpoEquipo.iCveClasificacion = EQMEquipo.iCveClasificacion "
					+ "                       AND EQMTpoEquipo.iCveTpoEquipo = EQMEquipo.iCveTpoEquipo "
					+ " LEFT JOIN EQMEmpMtto ON EQMEmpMtto.iCveEmpMtto = EQMMantenimiento.iCveEmpMtto "
					+ " LEFT JOIN SEGUsuario USR1 ON USR1.iCveUsuario = EQMMantenimiento.iCveUsuSolicita "
					+ " LEFT JOIN SEGUsuario USR2 ON USR2.iCveUsuario = EQMMantenimiento.iCveUsuRecibe "
					+ " LEFT JOIN SEGUsuario USR3 ON USR3.iCveUsuario = EQMMantenimiento.iCveUsuAutoriza "
					+ " WHERE EQMMantenimiento.iCveEquipo = " + iCveEquipo
					+ "   AND EQMMantenimiento.iCveMantenimiento = "
					+ iCveMantto;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMMantenimiento = new TVEQMMantenimiento();
				vEQMMantenimiento.setICveEquipo(rset.getInt(1));
				vEQMMantenimiento.setICveMantenimiento(rset.getInt(2));
				vEQMMantenimiento.setDtSolicitud(rset.getDate(3));
				vEQMMantenimiento.setDtProgramado(rset.getDate(4));
				vEQMMantenimiento.setICveTpoMantto(rset.getInt(5));
				vEQMMantenimiento.setICveMotivo(rset.getInt(6));
				vEQMMantenimiento.setCAccesorios(rset.getString(7));
				vEQMMantenimiento.setCAnalisisOper(rset.getString(8));
				vEQMMantenimiento.setICveEmpMtto(rset.getInt(9));
				vEQMMantenimiento.setCNombre(rset.getString(10));
				vEQMMantenimiento.setCResultado(rset.getString(11));
				vEQMMantenimiento.setCObservaciones(rset.getString(12));
				vEQMMantenimiento.setLConcluido(rset.getInt(13));
				vEQMMantenimiento.setDtRecepcion(rset.getDate(14));
				vEQMMantenimiento.setICveUsuSolicita(rset.getInt(15));
				vEQMMantenimiento.setICveUsuAutoriza(rset.getInt(16));
				vEQMMantenimiento.setICveUsuRecibe(rset.getInt(17));
				vEQMMantenimiento.setLCancelado(rset.getInt(18));
				vEQMMantenimiento.setDtCancelacion(rset.getDate(19));
				vEQMMantenimiento.setCDscTpoMantto(rset.getString(20));
				vEQMMantenimiento.setCDscMotivo(rset.getString(21));
				cTemp = rset.getString(22) != null ? rset.getString(22) : "";
				cTemp += rset.getString(23) != null ? " -> "
						+ rset.getString(23) : "";
				vEQMMantenimiento.setCDscEquipo(cTemp);
				vEQMMantenimiento.setCModelo(rset.getString(24));
				vEQMMantenimiento.setCNumSerie(rset.getString(25));
				vEQMMantenimiento.setCInventario(rset.getString(26));
				vEQMMantenimiento.setCDscBreveMarca(rset.getString(27));
				vEQMMantenimiento.setCDscUniMed(rset.getString(28));
				vEQMMantenimiento.setCDscModulo(rset.getString(29));
				vEQMMantenimiento.setCDscArea(rset.getString(30));
				vEQMMantenimiento.setCDscTpoEquipo(rset.getString(31));
				cTemp = rset.getString(32) != null ? rset.getString(32) : "";
				cTemp += rset.getString(33) != null ? " " + rset.getString(33)
						: "";
				cTemp += rset.getString(34) != null ? " " + rset.getString(34)
						: "";
				vEQMMantenimiento.setCDscUsuSolicita(cTemp);
				cTemp = rset.getString(35) != null ? rset.getString(35) : "";
				cTemp += rset.getString(36) != null ? " " + rset.getString(36)
						: "";
				cTemp += rset.getString(37) != null ? " " + rset.getString(37)
						: "";
				vEQMMantenimiento.setCDscUsuRecibe(cTemp);
				cTemp = rset.getString(38) != null ? rset.getString(38) : "";
				cTemp += rset.getString(39) != null ? " " + rset.getString(39)
						: "";
				cTemp += rset.getString(40) != null ? " " + rset.getString(40)
						: "";
				vEQMMantenimiento.setCDscUsuAutoriza(cTemp);
				cTemp = rset.getString(41) != null ? rset.getString(41) : "";
				cTemp += rset.getString(42) != null ? " " + rset.getString(42)
						: "";
				cTemp += rset.getString(43) != null ? " " + rset.getString(43)
						: "";
				vEQMMantenimiento.setCDscEmpMtto(cTemp);

				vcEQMMantenimiento.addElement(vEQMMantenimiento);
			}
		} catch (Exception ex) {
			warn("FindForReport", ex);
			throw new DAOException("FindForReport");
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null) {
					conn.close();
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindForReport.close", ex2);
			}
			return vcEQMMantenimiento;
		}
	}

	public StringBuffer ReportSolicMantto(int iCveEquipo, int iCveMantto) {
		TExcel Rep1 = new TExcel("07");
		TFechas pFecha = new TFechas("07");
		TParametro vParametro = new TParametro("07");
		java.sql.Date dtTemp;
		String cNomArch, cTemp;
		TVEQMMantenimiento VEqmMantto;
		Vector vEqmMantto;
		cNomArch = "SOLMTTO_" + iCveEquipo + "_" + iCveMantto;
		try {
			vEqmMantto = this.FindForReport(iCveEquipo, iCveMantto);
			if (vEqmMantto != null && vEqmMantto.size() > 0) {
				VEqmMantto = (TVEQMMantenimiento) vEqmMantto.get(0);
				// Datos de Solicitud de mantenimiento.
				cTemp = VEqmMantto.getICveMantenimiento() > 0 ? VEqmMantto
						.getICveMantenimiento() + "" : "";
				Rep1.comDespliega("E", 13, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				dtTemp = VEqmMantto.getDtSolicitud();
				cTemp = dtTemp != null ? "" + pFecha.getIntDay(dtTemp) : "";
				Rep1.comDespliega("H", 13, cTemp);
				cTemp = dtTemp != null ? "" + pFecha.getIntMonth(dtTemp) : "";
				Rep1.comDespliega("I", 13, cTemp);
				cTemp = dtTemp != null ? "" + pFecha.getIntYear(dtTemp) : "";
				Rep1.comDespliega("J", 13, cTemp);
				cTemp = VEqmMantto.getCDscArea() != null ? VEqmMantto
						.getCDscArea() : "";
				Rep1.comDespliega("C", 14, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				cTemp = VEqmMantto.getCDscUsuSolicita() != null ? VEqmMantto
						.getCDscUsuSolicita() : "";
				Rep1.comDespliega("C", 15, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);

				// Datos de equipo m�dico.
				cTemp = VEqmMantto.getICveEquipo() > 0 ? VEqmMantto
						.getICveEquipo() + "" : "";
				Rep1.comDespliega("E", 17, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				cTemp = VEqmMantto.getCDscEquipo() != null ? VEqmMantto
						.getCDscEquipo() : "";
				Rep1.comDespliega("C", 18, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				cTemp = VEqmMantto.getCDscBreveMarca() != null ? VEqmMantto
						.getCDscBreveMarca() : "";
				Rep1.comDespliega("C", 19, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				cTemp = VEqmMantto.getCModelo() != null ? VEqmMantto
						.getCModelo() : "";
				Rep1.comDespliega("F", 19, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				cTemp = VEqmMantto.getCNumSerie() != null ? VEqmMantto
						.getCNumSerie() : "";
				Rep1.comDespliega("C", 20, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				cTemp = VEqmMantto.getCInventario() != null ? VEqmMantto
						.getCInventario() : "";
				Rep1.comDespliega("F", 20, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);

				// Datos adicionales de solicitud.
				cTemp = VEqmMantto.getCDscTpoMantto() != null ? VEqmMantto
						.getCDscTpoMantto() : "";
				Rep1.comDespliega("D", 22, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				cTemp = VEqmMantto.getCDscMotivo() != null ? VEqmMantto
						.getCDscMotivo() : "";
				Rep1.comDespliega("B", 23, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				cTemp = VEqmMantto.getCAccesorios() != null ? VEqmMantto
						.getCAccesorios() : "";
				Rep1.comDespliega("B", 28, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);

				// Datos de Analisis y resultado.
				cTemp = VEqmMantto.getCAnalisisOper() != null ? VEqmMantto
						.getCAnalisisOper() : "";
				Rep1.comDespliega("B", 34, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				cTemp = VEqmMantto.getICveEmpMtto() > 0 ? VEqmMantto
						.getCDscEmpMtto() != null ? VEqmMantto.getCDscEmpMtto()
						: "" : "";
				Rep1.comDespliega("G", 34, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				cTemp = VEqmMantto.getCResultado() != null ? VEqmMantto
						.getCResultado() : "";
				Rep1.comDespliega("B", 42, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
				cTemp = VEqmMantto.getCObservaciones() != null ? VEqmMantto
						.getCObservaciones() : "";
				Rep1.comDespliega("B", 50, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);

				// Datos de recepci�n.
				dtTemp = VEqmMantto.getDtRecepcion();
				if (dtTemp != null) {
					cTemp = dtTemp != null ? "" + pFecha.getIntDay(dtTemp) : "";
					Rep1.comDespliega("H", 58, cTemp);
					cTemp = dtTemp != null ? "" + pFecha.getIntMonth(dtTemp)
							: "";
					Rep1.comDespliega("I", 58, cTemp);
					cTemp = dtTemp != null ? "" + pFecha.getIntYear(dtTemp)
							: "";
					Rep1.comDespliega("J", 58, cTemp);
					cTemp = VEqmMantto.getCDscArea() != null ? VEqmMantto
							.getCDscArea() : "";
					Rep1.comDespliega("C", 59,
							cTemp.equalsIgnoreCase("null") ? "" : cTemp);
					cTemp = VEqmMantto.getICveUsuRecibe() > 0 ? VEqmMantto
							.getCDscUsuRecibe() != null ? VEqmMantto
							.getCDscUsuRecibe() : "" : "";
					Rep1.comDespliega("C", 60,
							cTemp.equalsIgnoreCase("null") ? "" : cTemp);
				}

				// Datos de Autorizaci�n.
				cTemp = vParametro.getPropEspecifica("EQMAutorizaMantto");
				Rep1.comDespliega("C", 63, (cTemp != null && !cTemp
						.equalsIgnoreCase("null")) ? cTemp : "");
				cTemp = VEqmMantto.getICveUsuAutoriza() > 0 ? VEqmMantto
						.getCDscUsuAutoriza() != null ? VEqmMantto
						.getCDscUsuAutoriza() : "" : "";
				Rep1.comDespliega("C", 64, cTemp.equalsIgnoreCase("null") ? ""
						: cTemp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		StringBuffer buffer = Rep1.creaActiveX("pg070603020", cNomArch, false,
				false, true);
		return buffer;
	}
}