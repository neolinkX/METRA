package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXBaja DAO
 * </p>
 * <p>
 * Description: DAO Tabla TOXBaja
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

public class TDTOXBaja extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXBaja() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXBaja = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cFecha = "";
			TVTOXBaja vTOXBaja;
			TFechas dtFecha = new TFechas();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "TOXBaja.iAnio,"
					+ "TOXBaja.iCveLaboratorio,"
					+ "TOXBaja.iCveBaja,"
					+ "TOXBaja.dtDeshechado,"
					+ "TOXBaja.cOrganoInterno,"
					+ "TOXBaja.iCveUsuBaja,"
					+ "TOXBaja.cObservacion,"
					+ "TOXBaja.cInactiva, "
					+ "SEGUsuario.cNombre, "
					+ "SEGUsuario.cApPaterno, "
					+ "SEGUsuario.cApMaterno, "
					+ "GRLUniMed.cDscUniMed, "
					+ "TOXMotBaja.cDscMotBaja, "
					+ "TOXCtrolCalibra.CLote, "
					+ "TOXCtrolCalibra.cDscBreve,"
					+ "TOXReactivo.iCodigo, "
					+ "TOXReactivo.cDscBreve, "
					+ "TOXBaja.iCveMotBaja, "
					+ "TOXCtrolCalibra.iCveCtrolCalibra, "
					+ "TOXReactivo.iCveReactivo"
					+ " from TOXBaja "
					+ " inner join SEGUsuario on SEGUsuario.iCveUsuario = TOXBaja.iCveUsuBaja "
					+ " inner join GRLUniMed on GRLUniMed.iCveUniMed = TOXBaja.iCveLaboratorio "
					+ " inner join TOXMotBaja on TOXMotBaja.iCveMotBaja = TOXBaja.iCveMotBaja "
					+ " left join TOXCtrolCalibra on TOXCtrolCalibra.iCveBaja = TOXBaja.iCveBaja "
					+
					// " and TOXCtrolCalibra.iAnio = TOXBaja.iAnio  and TOXCtrolCalibra.iCveLaboratorio = TOXBaja.iCveLaboratorio "
					// +
					" and YEAR(TOXCtrolCalibra.dtBaja) = TOXBaja.iAnio  and TOXCtrolCalibra.iCveLaboratorio = TOXBaja.iCveLaboratorio "
					+ " left join TOXReactivo on TOXReactivo.iCveBaja = TOXBaja.iCveBaja "
					+
					// " and TOXReactivo.iAnio = TOXBaja.iAnio  and TOXReactivo.iCveLaboratorio = TOXBaja.iCveLaboratorio "
					// +
					" and YEAR(TOXReactivo.dtBaja) = TOXBaja.iAnio  and TOXReactivo.iCveLaboratorio = TOXBaja.iCveLaboratorio "
					+ cFiltro;
			// System.out.println("FindByAll: " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXBaja = new TVTOXBaja();
				vTOXBaja.setIAnio(rset.getInt(1));
				vTOXBaja.setICveLaboratorio(rset.getInt(2));
				vTOXBaja.setICveBaja(rset.getInt(3));
				vTOXBaja.setDtDesechado(rset.getDate(4));
				vTOXBaja.setCOrganoInterno(rset.getString(5));
				vTOXBaja.setICveUsuBaja(rset.getInt(6));
				vTOXBaja.setCObservacion(rset.getString(7));
				vTOXBaja.setCInactiva(rset.getString(8));
				vTOXBaja.setCNombreUsuario(rset.getString(9) + " "
						+ rset.getString(10) + " " + rset.getString(11));
				vTOXBaja.setCDscLaboratorio(rset.getString(12));
				vTOXBaja.setCDscMotBaja(rset.getString(13));
				vTOXBaja.setCComboCalibra(rset.getString(14) + "-"
						+ rset.getString(15));
				vTOXBaja.setCComboReactivo(rset.getString(16) + "-"
						+ rset.getString(17));
				vTOXBaja.setICveMotBaja(rset.getInt(18));
				vTOXBaja.setICveCtrolCalibra(rset.getInt(19));
				vTOXBaja.setICveReactivo(rset.getInt(20));

				if (vTOXBaja.getDtDesechado() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vTOXBaja.getDtDesechado(), "/");
					vTOXBaja.setCDtDesechado(cFecha);
					// cFecha =
					// dtFecha.getFechaDDMMMMMYYYY(rset.getDate(4)," de ");
					// System.out.println("2da. CFecha: " + cFecha);
					// vTOXBaja.setCFechaCompleta(cFecha);
				} else {
					vTOXBaja.setCDtDesechado("");
					vTOXBaja.setCFechaCompleta("");
				}

				vcTOXBaja.addElement(vTOXBaja);
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
			return vcTOXBaja;
		}
	}

	/**
	 * Metodo Find By AllB
	 */
	public Vector FindByAllB(String cFiltro, String tipo) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXBaja = new Vector();
		TFechas fecha = new TFechas();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cReact = "";
			String cCalibra = "";
			TVTOXBaja vTOXBaja;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cReact = "SELECT R.ICVEREACTIVO, R.CDSCBREVE, R.DTBAJA, B.DTDESHECHADO, B.COBSERVACION, B.CINACTIVA, "
					+ "B.ICVELABORATORIO, B.IANIO, B.ICVEBAJA, R.iCodigo "
					+ "FROM TOXREACTIVO R, TOXBAJA B "
					+ "WHERE R.ICVELABORATORIO=B.ICVELABORATORIO "
					+ "AND R.ICVEBAJA=B.ICVEBAJA "
					+ "AND YEAR( R.dtBaja) = B.IANIO "
					+ cFiltro
					+ " ORDER BY R.ICVEREACTIVO";
			cCalibra = "SELECT C.ICVECTROLCALIBRA, C.CDSCBREVE, C.DTBAJA, B.DTDESHECHADO, B.COBSERVACION, B.CINACTIVA, "
					+ "B.ICVELABORATORIO, B.IANIO, B.ICVEBAJA, C.cLote "
					+ "FROM TOXCTROLCALIBRA C, TOXBAJA B "
					+ "WHERE C.ICVELABORATORIO=B.ICVELABORATORIO "
					+ "AND C.ICVEBAJA=B.ICVEBAJA "
					+ "AND YEAR( C.dtBaja ) = B.IANIO "
					+ cFiltro
					+ " ORDER BY C.ICVECTROLCALIBRA ";

			// Reactivo
			if (tipo.equals("reactivo")) {
				cSQL = cReact;
				pstmt = conn.prepareStatement(cSQL);
				// System.out.println("--" + cSQL);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					vTOXBaja = new TVTOXBaja();
					vTOXBaja.setICveReactivo(rset.getInt(1));
					vTOXBaja.setCLote(rset.getString(10));
					vTOXBaja.setCDscBreve(rset.getString(2));
					vTOXBaja.setCBaja(fecha.getFechaDDMMYYYY(rset.getDate(3),
							"/"));
					vTOXBaja.setCDesechado(fecha.getFechaDDMMYYYY(
							rset.getDate(4), "/"));
					vTOXBaja.setCObservacion(rset.getString(5));
					vTOXBaja.setCInactiva(rset.getString(6));

					vTOXBaja.setICveLaboratorio(rset.getInt(7));
					vTOXBaja.setIAnio(rset.getInt(8));
					vTOXBaja.setICveBaja(rset.getInt(9));

					vcTOXBaja.addElement(vTOXBaja);
				}
			} // Calibrador
			else if (tipo.equals("calibrador")) {
				cSQL = cCalibra;
				// System.out.println("--" + cSQL);
				pstmt = conn.prepareStatement(cSQL);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					vTOXBaja = new TVTOXBaja();
					vTOXBaja.setICveCtrolCalibra(rset.getInt(1));
					vTOXBaja.setCLote(rset.getString(10));
					vTOXBaja.setCDscBreve(rset.getString(2));
					vTOXBaja.setCBaja(fecha.getFechaDDMMYYYY(rset.getDate(3),
							"/"));
					vTOXBaja.setCDesechado(fecha.getFechaDDMMYYYY(
							rset.getDate(4), "/"));
					vTOXBaja.setCObservacion(rset.getString(5));
					vTOXBaja.setCInactiva(rset.getString(6));

					vTOXBaja.setICveLaboratorio(rset.getInt(7));
					vTOXBaja.setIAnio(rset.getInt(8));
					vTOXBaja.setICveBaja(rset.getInt(9));

					vcTOXBaja.addElement(vTOXBaja);
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
			return vcTOXBaja;
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
			TVTOXBaja vTOXBaja = (TVTOXBaja) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXBaja(" + "iAnio," + "iCveLaboratorio,"
					+ "iCveBaja," + "dtDesechado," + "cOrganoInterno,"
					+ "iCveUsuBaja," + "cObservacion," + "cInactiva"
					+ ")values(?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vTOXBaja.getIAnio());
			pstmt.setInt(2, vTOXBaja.getICveLaboratorio());
			pstmt.setInt(3, vTOXBaja.getICveBaja());
			pstmt.setDate(4, vTOXBaja.getDtDesechado());
			pstmt.setString(5, vTOXBaja.getCOrganoInterno());
			pstmt.setInt(6, vTOXBaja.getICveUsuBaja());
			pstmt.setString(7, vTOXBaja.getCObservacion());
			pstmt.setString(8, vTOXBaja.getCInactiva());
			pstmt.executeUpdate();
			cClave = "" + vTOXBaja.getICveBaja();
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
	 * Metodo Insert baja
	 */
	public Object insertBaja(Connection conGral, Object obj, String tipo,
			int llave) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		TFechas fecha = new TFechas();
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVTOXBaja vTOXBaja = (TVTOXBaja) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXBaja(" + "iAnio," + "iCveLaboratorio,"
					+ "iCveBaja," + "dtDeshechado," + "cOrganoInterno,"
					+ "iCveUsuBaja," + "cObservacion," + "cInactiva,"
					+ "iCveMotBaja " + ") values(?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String sqlNewCve = "SELECT MAX(ICVEBAJA) FROM TOXBAJA WHERE IANIO=? AND ICVELABORATORIO=?";
			PreparedStatement psNewCve = conn.prepareStatement(sqlNewCve);
			int iNewCve = 0;

			psNewCve.setInt(1, fecha.getIntYear(fecha.TodaySQL()));
			psNewCve.setInt(2, vTOXBaja.getICveLaboratorio());
			ResultSet rsNewCve = psNewCve.executeQuery();

			if (rsNewCve.next())
				iNewCve = rsNewCve.getInt(1) + 1;
			rsNewCve.close();
			psNewCve.close();

			pstmt.setInt(1, fecha.getIntYear(fecha.TodaySQL()));
			pstmt.setInt(2, vTOXBaja.getICveLaboratorio());
			pstmt.setInt(3, iNewCve);

			pstmt.setDate(4, vTOXBaja.getDtDesechado());
			pstmt.setString(5, vTOXBaja.getCOrganoInterno());
			pstmt.setInt(6, vTOXBaja.getICveUsuBaja());
			pstmt.setString(7, vTOXBaja.getCObservacion());
			pstmt.setString(8, vTOXBaja.getCInactiva());
			pstmt.setInt(9, vTOXBaja.getICveMotBaja());
			pstmt.executeUpdate();

			// Update a las otras tablas
			String sql = "";
			PreparedStatement psOtras;
			if (tipo.equals("reactivo")) {
				sql = "UPDATE TOXREACTIVO SET ICVEBAJA=?, LBAJA=?, DTBAJA=? WHERE ICVEREACTIVO=? AND ICVELABORATORIO=?";
			} else if (tipo.equals("calibrador")) {
				sql = "UPDATE TOXCTROLCALIBRA SET ICVEBAJA=?, LBAJA=?, DTBAJA=? WHERE ICVECTROLCALIBRA=? AND ICVELABORATORIO=?";
			}

			psOtras = conn.prepareStatement(sql);
			psOtras.setInt(1, iNewCve);
			psOtras.setInt(2, 1);
			psOtras.setDate(3, fecha.TodaySQL());
			psOtras.setInt(4, llave);
			psOtras.setInt(5, vTOXBaja.getICveLaboratorio());

			psOtras.executeUpdate();
			psOtras.close();

			cClave = "" + iNewCve;
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
			TVTOXBaja vTOXBaja = (TVTOXBaja) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXBaja"
					+ " set dtDeshechado= ?, "
					+ "cOrganoInterno= ?, "
					+ "iCveUsuBaja= ?, "
					+ "cObservacion= ?, "
					+ "cInactiva= ?, "
					+ "iCveMotBaja = ? "
					+ "where iAnio = ?  and iCveLaboratorio = ? and iCveBaja = ?";
			pstmt = conn.prepareStatement(cSQL);

			pstmt.setDate(1, vTOXBaja.getDtDesechado());
			pstmt.setString(2, vTOXBaja.getCOrganoInterno());
			pstmt.setInt(3, vTOXBaja.getICveUsuBaja());
			pstmt.setString(4, vTOXBaja.getCObservacion());
			pstmt.setString(5, vTOXBaja.getCInactiva());
			pstmt.setInt(6, vTOXBaja.getICveMotBaja());
			pstmt.setInt(7, vTOXBaja.getIAnio());
			pstmt.setInt(8, vTOXBaja.getICveLaboratorio());
			pstmt.setInt(9, vTOXBaja.getICveBaja());

			// System.out.println("Vals: " + vTOXBaja.getIAnio() + "**" +
			// vTOXBaja.getICveLaboratorio() + "**" + vTOXBaja.getICveBaja());

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
			TVTOXBaja vTOXBaja = (TVTOXBaja) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXBaja" + " where Ingrese Campos Llave = ?";
			pstmt = conn.prepareStatement(cSQL);
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
				warn("delete.closeTOXBaja", ex2);
			}
			return cClave;
		}
	}
}
