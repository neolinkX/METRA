package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EQMAsignacion DAO
 * </p>
 * <p>
 * Description: DAO para el control de la Informaci�n de la tabla
 * EQMAsignaci�n
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

public class TDEQMAsignacion extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEQMAsignacion() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMAsignacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMAsignacion vEQMAsignacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select   "
					+ " EQMAsignacion.iCveEquipo,  "
					+ " iCveAsignacion,  "
					+ " iCveUniMed,  "
					+ " iCveModulo,  "
					+ " iCveArea,  "
					+ " iCveUsuResp,  "
					+ " lActual,  "
					+ " dtAsigna,  "
					+ " dtDesasigna, "
					+ " EQMEquipo.cDscEquipo, "
					+ " EQMEquipo.cNumSerie, "
					+ " EQMEquipo.cInventario, "
					+ " EQMEquipo.cModelo, "
					+ " EQMMarca.cDscMarca, "
					+ " EQMEstado.cDscEstadoEQM "
					+ " from EQMAsignacion "
					+ " join EQMEquipo ON EQMEquipo.iCveEquipo = EQMAsignacion.iCveEquipo AND "
					+ "                   EQMEquipo.lBaja = 0 "
					+ " join EQMMarca ON EQMMarca.iCveMarca = EQMEquipo.iCveMarca "
					+ " join EQMEstado ON EQMEstado.iCveEstadoEQM = EQMEquipo.iCveEstadoEQM "
					+ cFiltro + "  " + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMAsignacion = new TVEQMAsignacion();
				vEQMAsignacion.setICveEquipo(rset.getInt(1));
				vEQMAsignacion.setICveAsignacion(rset.getInt(2));
				vEQMAsignacion.setICveUniMed(rset.getInt(3));
				vEQMAsignacion.setICveModulo(rset.getInt(4));
				vEQMAsignacion.setICveArea(rset.getInt(5));
				vEQMAsignacion.setICveUsuResp(rset.getInt(6));
				vEQMAsignacion.setLActual(rset.getInt(7));
				vEQMAsignacion.setDtAsigna(rset.getDate(8));
				vEQMAsignacion.setDtDesasigna(rset.getDate(9));
				vEQMAsignacion.setCDscEquipo(rset.getString(10));
				vEQMAsignacion.setCNumSerie(rset.getString(11));
				vEQMAsignacion.setCInventario(rset.getString(12));
				vEQMAsignacion.setCModelo(rset.getString(13));
				vEQMAsignacion.setCDscMarca(rset.getString(14));
				vEQMAsignacion.setCDscEstadoEQM(rset.getString(15));
				vcEQMAsignacion.addElement(vEQMAsignacion);
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
			return vcEQMAsignacion;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindDsc(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMAsignacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMAsignacion vEQMAsignacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "        EQMAsignacion.iCveEquipo, "
					+ "        EQMAsignacion.iCveAsignacion, "
					+ "        EQMAsignacion.iCveUniMed, "
					+ "        EQMAsignacion.iCveModulo, "
					+ "        EQMAsignacion.iCveArea, "
					+ "        EQMAsignacion.iCveUsuResp, "
					+ "        EQMAsignacion.lActual, "
					+ "        EQMAsignacion.dtAsigna, "
					+ "        EQMAsignacion.dtDesasigna, "
					+ "        EQMEquipo.cDscEquipo, "
					+ "        GRLUniMed.cDscUniMed, "
					+ "        GRLModulo.cDscModulo, "
					+ "        GRLArea.cDscArea, "
					+ "        SEGUsuario.cNombre, "
					+ "        SEGUsuario.cApPaterno, "
					+ "        SEGUsuario.cApMaterno "
					+ "from	EQMAsignacion "
					+ "        join    EQMEquipo on EQMEquipo.iCveEquipo = EQMAsignacion.iCveEquipo "
					+ "        join ( "
					+ "                GRLAreaModulo "
					+ "                        join GRLUniMed on GRLUniMed.iCveUniMed = GRLAreaModulo.iCveUniMed "
					+ "                        join GRLModulo on GRLModulo.iCveModulo = GRLAreaModulo.iCveModulo "
					+ "                                and GRLModulo.iCveUniMed = GRLAreaModulo.iCveUniMed "
					+ "                        join GRLArea on GRLArea.iCveArea = GRLAreaModulo.iCveArea "
					+ "        ) "
					+ "                on GRLAreaModulo.iCveUniMed = EQMAsignacion.iCveUniMed "
					+ "                and GRLAreaModulo.iCveModulo = EQMAsignacion.iCveModulo "
					+ "                and GRLAreaModulo.iCveArea   = EQMAsignacion.iCveArea "
					+ "        join	   SEGUsuario on SEGUsuario.iCveUsuario = EQMAsignacion.iCveUsuResp "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMAsignacion = new TVEQMAsignacion();
				vEQMAsignacion.setICveEquipo(rset.getInt(1));
				vEQMAsignacion.setICveAsignacion(rset.getInt(2));
				vEQMAsignacion.setICveUniMed(rset.getInt(3));
				vEQMAsignacion.setICveModulo(rset.getInt(4));
				vEQMAsignacion.setICveArea(rset.getInt(5));
				vEQMAsignacion.setICveUsuResp(rset.getInt(6));
				vEQMAsignacion.setLActual(rset.getInt(7));
				vEQMAsignacion.setDtAsigna(rset.getDate(8));
				vEQMAsignacion.setDtDesasigna(rset.getDate(9));
				vEQMAsignacion.setCDscEquipo(rset.getString(10));
				vEQMAsignacion.setCDscUniMed(rset.getString(11));
				vEQMAsignacion.setCDscModulo(rset.getString(12));
				vEQMAsignacion.setCDscArea(rset.getString(13));
				vEQMAsignacion.setCNombre(rset.getString(14));
				vEQMAsignacion.setCApPaterno(rset.getString(15));
				vEQMAsignacion.setCApMaterno(rset.getString(16));

				java.util.Date dtFechaTmp = new java.util.Date();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				dtFechaTmp = rset.getDate(8);
				if (dtFechaTmp != null)
					vEQMAsignacion.setCDtAsigna(formato.format(dtFechaTmp));
				else
					vEQMAsignacion.setCDtAsigna("");
				dtFechaTmp = rset.getDate(9);
				if (dtFechaTmp != null)
					vEQMAsignacion.setCDtDesasigna(formato.format(dtFechaTmp));
				else
					vEQMAsignacion.setCDtDesasigna("");

				vcEQMAsignacion.addElement(vEQMAsignacion);
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
			return vcEQMAsignacion;
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVEQMAsignacion vEQMAsignacion = (TVEQMAsignacion) obj;

			cSQL = "insert into EQMAsignacion(" + "iCveEquipo,"
					+ "iCveAsignacion," + "iCveUniMed," + "iCveModulo,"
					+ "iCveArea," + "iCveUsuResp," + "lActual," + "dtAsigna,"
					+ "dtDesasigna" + ")values(?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vEQMAsignacion.getICveEquipo());
			pstmt.setInt(2, vEQMAsignacion.getICveAsignacion());
			pstmt.setInt(3, vEQMAsignacion.getICveUniMed());
			pstmt.setInt(4, vEQMAsignacion.getICveModulo());
			pstmt.setInt(5, vEQMAsignacion.getICveArea());
			pstmt.setInt(6, vEQMAsignacion.getICveUsuResp());
			pstmt.setInt(7, vEQMAsignacion.getLActual());
			pstmt.setDate(8, vEQMAsignacion.getDtAsigna());
			if (vEQMAsignacion.getDtDesasigna() == null)
				pstmt.setNull(9, Types.DATE);
			else
				pstmt.setDate(9, vEQMAsignacion.getDtDesasigna());
			pstmt.executeUpdate();
			cClave = "" + vEQMAsignacion.getICveEquipo();
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
			}
			String cSQL = "";
			TVEQMAsignacion vEQMAsignacion = (TVEQMAsignacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMAsignacion" + " set iCveUniMed= ?, "
					+ "iCveModulo= ?, " + "iCveArea= ?, " + "iCveUsuResp= ?, "
					+ "lActual= ?, " + "dtAsigna= ?, " + "dtDesasigna= ? "
					+ "where iCveEquipo = ? " + " and iCveAsignacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMAsignacion.getICveUniMed());
			pstmt.setInt(2, vEQMAsignacion.getICveModulo());
			pstmt.setInt(3, vEQMAsignacion.getICveArea());
			pstmt.setInt(4, vEQMAsignacion.getICveUsuResp());
			pstmt.setInt(5, vEQMAsignacion.getLActual());
			pstmt.setDate(6, vEQMAsignacion.getDtAsigna());
			pstmt.setDate(7, vEQMAsignacion.getDtDesasigna());
			pstmt.setInt(8, vEQMAsignacion.getICveEquipo());
			pstmt.setInt(9, vEQMAsignacion.getICveAsignacion());
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
			TVEQMAsignacion vEQMAsignacion = (TVEQMAsignacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EQMAsignacion" + " where iCveEquipo = ?"
					+ " and iCveAsignacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMAsignacion.getICveEquipo());
			pstmt.setInt(2, vEQMAsignacion.getICveAsignacion());
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
				warn("delete.closeEQMAsignacion", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo encargado de insertar un registro en EQMAsignaci�n para un
	 * equipo espec�fico generando consecutivo de asignaci�n.
	 * 
	 * @param conGral
	 *            Conexi�n a la BD, en caso de ser nulo se crear� la
	 *            conexi�n internamente.
	 * @param obj
	 *            Objeto con los valores necesarios para insertar el registro
	 *            correspondiente.
	 * @return Regresa la clave de equipo en caso de ser exitosa la inserci�n.
	 * @throws DAOException
	 */
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
			}
			String cSQL = "";
			TVEQMAsignacion vEQMAsignacion = (TVEQMAsignacion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT MAX(iCveAsignacion) FROM EQMAsignacion WHERE iCveEquipo= ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMAsignacion.getICveEquipo());
			rset = pstmt.executeQuery();
			if (rset.next())
				iMax = rset.getInt(1);
			iMax++;
			vEQMAsignacion.setICveAsignacion(iMax);
			cClave = "" + this.insert(conn, vEQMAsignacion);
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
	 * Metodo encargado de llevar a cabo la desasignaci�n de un equipo al
	 * �rea de un m�dulo en una unidad m�dica.
	 * 
	 * @param conGral
	 *            Conexi�n a la BD, en caso de ser nulo se crear� la
	 *            conexi�n internamente.
	 * @param obj
	 *            Objeto con los valores necesarios para llevar a cabo la
	 *            desasignaci�n.
	 * @returnm Clave del equipo asignado en caso de exito, cadena vac�a en
	 *          caso contrario.
	 * @throws DAOException
	 */
	public Object desasignar(Connection conGral, Object obj)
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
			StringBuffer cSQL = new StringBuffer("");
			TVEQMAsignacion vEQMAsignacion = (TVEQMAsignacion) obj;
			cSQL.append("update EQMAsignacion").append(" set lActual = ?, ")
					.append("dtDesasigna = ? ").append("where iCveEquipo = ? ")
					.append(" and iCveUniMed = ? ")
					.append(" and iCveModulo = ? ")
					.append(" and iCveArea = ? ").append(" and lActual = 1");
			pstmt = conn.prepareStatement(cSQL.toString());
			pstmt.setInt(1, vEQMAsignacion.getLActual());
			pstmt.setDate(2, vEQMAsignacion.getDtDesasigna());
			pstmt.setInt(3, vEQMAsignacion.getICveEquipo());
			pstmt.setInt(4, vEQMAsignacion.getICveUniMed());
			pstmt.setInt(5, vEQMAsignacion.getICveModulo());
			pstmt.setInt(6, vEQMAsignacion.getICveArea());
			pstmt.executeUpdate();
			cClave = "" + vEQMAsignacion.getICveEquipo();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("desasignar", ex1);
			}
			warn("desasignar", ex);
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
				warn("desasignar.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All Unidad Medica Modulo �rea
	 */
	public Vector FindByAllUniMedModArea(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMAsignacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMAsignacion vEQMAsignacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select EqmAsignacion.iCveEquipo, GrlUniMed.cDscUniMed, "
					+ "GrlModulo.cDscModulo, GrlArea.cDscArea "
					+ "from EqmAsignacion "
					+ "join GrlUniMed on GrlUniMed.iCveUniMed=EqmAsignacion.iCveUniMed "
					+ "join GrlModulo on GrlModulo.iCveUniMed=EqmAsignacion.iCveUniMed "
					+ "and GrlMOdulo.iCveModulo=EqmAsignacion.iCveModulo "
					+ "join GrlArea on GrlArea.iCveArea=EqmAsignacion.iCveArea "
					+ "and GrlArea.lActivo = 1 " + cFiltro;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMAsignacion = new TVEQMAsignacion();
				vEQMAsignacion.setICveEquipo(rset.getInt(1));
				vEQMAsignacion.setCDscUniMed(rset.getString(2));
				vEQMAsignacion.setCDscModulo(rset.getString(3));
				vEQMAsignacion.setCDscArea(rset.getString(4));
				vcEQMAsignacion.addElement(vEQMAsignacion);
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
			return vcEQMAsignacion;
		}
	}
}
