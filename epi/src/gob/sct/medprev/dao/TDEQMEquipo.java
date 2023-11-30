package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.util.TFechas;

/**
 * <p>
 * Title: Data Acces Object de EQMEquipo DAO
 * </p>
 * <p>
 * Description: DAO de la tabla de EQMEquipo
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

public class TDEQMEquipo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEQMEquipo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String filtro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMEquipo vEQMEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEquipo," + "cDscEquipo," + "cNumSerie,"
					+ "cModelo," + "iCveTpoEquipo," + "iCveMarca, "
					+ "cCveEquipo " + " from EQMEquipo " + filtro;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMEquipo = new TVEQMEquipo();
				vEQMEquipo.setICveEquipo(rset.getInt(1));
				vEQMEquipo.setCDscEquipo(rset.getString(2));
				vEQMEquipo.setCNumSerie(rset.getString(3));
				vEQMEquipo.setCModelo(rset.getString(4));
				vEQMEquipo.setICveTpoEquipo(rset.getInt(5));
				vEQMEquipo.setICveMarca(rset.getInt(6));
				vEQMEquipo.setCCveEquipo(rset.getString(7));
				vcEQMEquipo.addElement(vEQMEquipo);
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
			return vcEQMEquipo;
		}
	}

	/**
	 * Metodo FindDsc
	 */
	public Vector FindDsc(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMEquipo vEQMEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "        EQMEquipo.iCveEquipo, "
					+ "        EQMEquipo.iCveClasificacion, "
					+ "        EQMEquipo.iCveTpoEquipo, "
					+ "        cDscEquipo, "
					+ "        EQMEquipo.iCveMarca, "
					+ "        cModelo, "
					+ "        cNumSerie, "
					+ "        cInventario, "
					+ "        EQMClasificacion.cDscBreve, "
					+ "        EQMTpoEquipo.cDscBreve, "
					+ "        EQMMarca.cDscBreve, "
					+ "        GRLUniMed.iCveUniMed, "
					+ "        GRLUniMed.cDscUniMed, "
					+ "        GRLModulo.iCveModulo, "
					+ "        GRLModulo.cDscModulo, "
					+ "        GRLArea.iCveArea, "
					+ "        GRLArea.cDscArea, "
					+ "        EQMAsignacion.lActual "
					+ "from	EQMEquipo "
					+ "        join (EQMTpoEquipo "
					+ "                join EQMClasificacion on EQMClasificacion.iCveClasificacion = EQMTpoEquipo.iCveClasificacion "
					+ "        ) "
					+ "        on EQMTpoEquipo.iCveTpoEquipo = EQMEquipo.iCveTpoEquipo "
					+ "                         and EQMTpoEquipo.iCveClasificacion = EQMEquipo.iCveClasificacion "
					+ "        join EQMMarca on EQMMarca.iCveMarca = EQMEquipo.iCveMarca "
					+ "        join (EQMAsignacion "
					+ "                join (GRLAreaModulo "
					+ "                         join GRLModulo on GRLModulo.iCveUniMed = GRLAreaModulo.iCveUniMed "
					+ "                                       and GRLModulo.iCveModulo = GRLAreaModulo.iCveModulo "
					+ "                         join GRLUniMed on GRLUniMed.iCveUniMed = GRLAreaModulo.iCveUniMed "
					+ "                         join GRLArea on GRLArea.iCveArea = GRLAreaModulo.iCveArea "
					+ "                ) on GRLAreaModulo.iCveUniMed = EQMAsignacion.iCveUniMed "
					+ "                 and GRLAreaModulo.iCveModulo = EQMAsignacion.iCveModulo "
					+ "                 and GRLAreaModulo.iCveArea = EQMAsignacion.iCveArea "
					+ "        ) on EQMAsignacion.iCveEquipo = EQMEquipo.iCveEquipo "
					+ cWhere;
			if (!cOrderBy.trim().equalsIgnoreCase("ORDER BY"))
				cSQL += " " + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMEquipo = new TVEQMEquipo();
				vEQMEquipo.setICveEquipo(rset.getInt(1));
				vEQMEquipo.setICveClasificacion(rset.getInt(2));
				vEQMEquipo.setICveTpoEquipo(rset.getInt(3));
				vEQMEquipo.setCDscEquipo(rset.getString(4));
				vEQMEquipo.setICveMarca(rset.getInt(5));
				vEQMEquipo.setCModelo(rset.getString(6));
				vEQMEquipo.setCNumSerie(rset.getString(7));
				vEQMEquipo.setCInventario(rset.getString(8));
				vEQMEquipo.setCDscBreveClasificacion(rset.getString(9));
				vEQMEquipo.setCDscBreveTpoEquipo(rset.getString(10));
				vEQMEquipo.setCDscBreveMarca(rset.getString(11));
				vEQMEquipo.setICveUniMed(rset.getInt(12));
				vEQMEquipo.setCDscUniMed(rset.getString(13));
				vEQMEquipo.setICveModulo(rset.getInt(14));
				vEQMEquipo.setCDscModulo(rset.getString(15));
				vEQMEquipo.setICveArea(rset.getInt(16));
				vEQMEquipo.setCDscArea(rset.getString(17));
				vEQMEquipo.setLActual(rset.getInt(18));

				vcEQMEquipo.addElement(vEQMEquipo);
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
			return vcEQMEquipo;
		}
	}

	public Vector FindByNotEqual() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMEquipo vEQMEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "iCveEquipo, "
					+ "cDscEquipo, "
					+ "cNumSerie, "
					+ "cModelo "
					+ "from	EQMEquipo "
					+ "where iCveEquipo not in (select iCveEquipo from TOXEquipo) "
					+ "order by iCveEquipo ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMEquipo = new TVEQMEquipo();
				vEQMEquipo.setICveEquipo(rset.getInt(1));
				vEQMEquipo.setCDscEquipo(rset.getString(2));
				vEQMEquipo.setCNumSerie(rset.getString(3));
				vEQMEquipo.setCModelo(rset.getString(4));
				vcEQMEquipo.addElement(vEQMEquipo);
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
			return vcEQMEquipo;
		}
	}

	public Vector FindByCustomWhere(String filtro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMEquipo vEQMEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select distinct " + "EQMEquipo.iCveEquipo,"
					+ "EQMEquipo.cDscEquipo," + "EQMEquipo.cNumSerie,"
					+ "EQMEquipo.cModelo," + "EQMEquipo.iCveTpoEquipo,"
					+ "EQMEquipo.iCveMarca" + " from EQMEquipo " + filtro;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMEquipo = new TVEQMEquipo();
				vEQMEquipo.setICveEquipo(rset.getInt(1));
				vEQMEquipo.setCDscEquipo(rset.getString(2));
				vEQMEquipo.setCNumSerie(rset.getString(3));
				vEQMEquipo.setCModelo(rset.getString(4));
				vEQMEquipo.setICveTpoEquipo(rset.getInt(5));
				vEQMEquipo.setICveMarca(rset.getInt(6));
				vcEQMEquipo.addElement(vEQMEquipo);
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
			return vcEQMEquipo;
		}
	}

	/**
	 * Metodo Find By Programa
	 */
	public Vector FindByPrograma(String cWhere, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMEquipo vEQMEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select"
					+ " DISTINCT(Equipo.iCveEquipo), TpoEquipo.cDscTpoEquipo, Equipo.cCveEquipo, Marca.cDscMarca,"
					+ " Equipo.cModelo, Equipo.cNumSerie, Area.cDscArea, Equipo.cInventario, Mantenim.dtProgramado "
					+ " FROM EQMEquipo Equipo"
					+ "   LEFT JOIN EQMTpoEquipo TpoEquipo ON TpoEquipo.iCveClasificacion = Equipo.iCveClasificacion"
					+ "      AND TpoEquipo.iCveTpoEquipo = Equipo.iCveTpoEquipo"
					+ "   LEFT JOIN EQMMarca Marca ON Marca.iCveMarca = Equipo.iCveMarca"
					+ "   LEFT JOIN EQMAsignacion Asigna ON Asigna.iCveEquipo = Equipo.iCveEquipo"
					+ "      AND Asigna.lActual = 1 AND Asigna.dtDesasigna IS NULL"
					+ "   LEFT JOIN GRLArea Area ON Area.iCveArea = Asigna.iCveArea"
					+ "   LEFT JOIN EQMMantenimiento Mantenim ON Mantenim.iCveEquipo = Equipo.iCveEquipo"
					+ cWhere + " " + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMEquipo = new TVEQMEquipo();
				vEQMEquipo.setICveEquipo(rset.getInt(1));
				vEQMEquipo.setCDscBreveTpoEquipo(rset.getString(2));
				vEQMEquipo.setCCveEquipo(rset.getString(3));
				vEQMEquipo.setCDscBreveMarca(rset.getString(4));
				vEQMEquipo.setCModelo(rset.getString(5));
				vEQMEquipo.setCNumSerie(rset.getString(6));
				vEQMEquipo.setCDscArea(rset.getString(7));
				vEQMEquipo.setCInventario(rset.getString(8));
				vcEQMEquipo.addElement(vEQMEquipo);
			}
		} catch (Exception ex) {
			warn("FindByPrograma", ex);
			throw new DAOException("FindByPrograma");
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
				warn("FindByPrograma.close", ex2);
			}
			if (vcEQMEquipo == null)
				vcEQMEquipo = new Vector();
			return vcEQMEquipo;
		}
	}

	/**
	 * Metodo Find By Mantto Anual (Todos los mantenimientos de un equipo en un
	 * ejercicio)
	 */
	public Vector FindByManttoAnual(int iCveEquipo, int iEjercicio,
			int iTipoMantto) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vValores = new Vector();
		java.sql.Date dtFecha;
		TFechas vFecha = new TFechas("07");
		for (int i = 0; i < 12; i++)
			vValores.add(new Boolean(false));
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMEquipo vEQMEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT DISTINCT dtProgramado "
					+ "  FROM EQMMantenimiento Mantenim "
					+ "  WHERE iCveEquipo = " + iCveEquipo + " "
					+ "    AND iCveTpoMantto = " + iTipoMantto + " "
					+ "    AND dtProgramado BETWEEN '" + iEjercicio
					+ "-01-01' AND '" + iEjercicio + "-12-31' "
					+ "    AND lCancelado = 0 " + "  ORDER BY dtProgramado ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				dtFecha = rset.getDate(1);
				vValores.setElementAt(new Boolean(true),
						(vFecha.getIntMonth(dtFecha) - 1));
			}
		} catch (Exception ex) {
			warn("FindByManttoAnual", ex);
			throw new DAOException("FindByManttoAnual");
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
				warn("FindByManttoAnual.close", ex2);
			}
			return vValores;
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
			TVEQMEquipo vEQMEquipo = (TVEQMEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EQMEquipo(" + "iCveEquipo," + "cDscEquipo,"
					+ "cNumSerie," + "cModelo," + "iCveTpoEquipo,"
					+ "iCveMarca" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cNewClave = "SELECT MAX(ICVEEQUIPO) FROM EQMEQUIPO";
			PreparedStatement psNewCve = conn.prepareStatement(cNewClave);
			ResultSet rsNewClave = psNewCve.executeQuery();
			int newCve = 0;
			if (rsNewClave.next()) {
				newCve = rsNewClave.getInt(1) + 1;
			}
			rsNewClave.close();
			psNewCve.close();

			pstmt.setInt(1, newCve);
			pstmt.setString(2, vEQMEquipo.getCDscEquipo().toUpperCase().trim());
			pstmt.setString(3, vEQMEquipo.getCNumSerie().toUpperCase().trim());
			pstmt.setString(4, vEQMEquipo.getCModelo().toUpperCase().trim());
			pstmt.setInt(5, vEQMEquipo.getICveTpoEquipo());
			pstmt.setInt(6, vEQMEquipo.getICveMarca());
			pstmt.executeUpdate();
			cClave = "" + newCve;
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
			TVEQMEquipo vEQMEquipo = (TVEQMEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EQMEquipo(" + "iCveEquipo,"
					+ "iCveClasificacion," + "iCveTpoEquipo," + "cCveEquipo,"
					+ "cDscEquipo," + "cNumSerie," + "cInventario,"
					+ "iCveMarca," + "cModelo," + "dtAdquisicion,"
					+ "iCveUsuRegistra," + "iCveEstadoEQM," + "lActivo,"
					+ "iPerManttoPrev," + "iLimiteUso," + "iPerCalibracion,"
					+ "cObservacion"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cNewClave = "SELECT MAX(ICVEEQUIPO) FROM EQMEQUIPO";
			PreparedStatement psNewCve = conn.prepareStatement(cNewClave);
			ResultSet rsNewClave = psNewCve.executeQuery();
			int newCve = 0;
			if (rsNewClave.next()) {
				newCve = rsNewClave.getInt(1) + 1;
			}
			rsNewClave.close();
			psNewCve.close();

			pstmt.setInt(1, newCve);
			pstmt.setInt(2, vEQMEquipo.getICveClasificacion());
			pstmt.setInt(3, vEQMEquipo.getICveTpoEquipo());
			pstmt.setString(4, vEQMEquipo.getCCveEquipo().toUpperCase().trim());
			pstmt.setString(5, vEQMEquipo.getCDscEquipo().toUpperCase().trim());
			pstmt.setString(6, vEQMEquipo.getCNumSerie().toUpperCase().trim());
			pstmt.setString(7, vEQMEquipo.getCInventario().toUpperCase().trim());
			pstmt.setInt(8, vEQMEquipo.getICveMarca());
			pstmt.setString(9, vEQMEquipo.getCModelo().toUpperCase().trim());
			pstmt.setDate(10, vEQMEquipo.getDtAdquisicion());
			pstmt.setInt(11, vEQMEquipo.getICveUsuRegistra());
			pstmt.setInt(12, vEQMEquipo.getICveEstadoEQM());
			pstmt.setInt(13, 1);
			pstmt.setInt(14, vEQMEquipo.getIPerManttoPrev());
			pstmt.setInt(15, vEQMEquipo.getILimiteUso());
			pstmt.setInt(16, vEQMEquipo.getIPerCalibracion());
			pstmt.setString(17, vEQMEquipo.getCObservacion().toUpperCase()
					.trim());

			pstmt.executeUpdate();
			cClave = "" + newCve;
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
	 * Metodo update2
	 */
	public Object update2(Connection conGral, Object obj) throws DAOException {
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
			TVEQMEquipo vEQMEquipo = (TVEQMEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMEquipo set " + "iCveClasificacion=?,"
					+ "iCveTpoEquipo=?," + "cCveEquipo=?," + "cDscEquipo=?,"
					+ "cNumSerie=?," + "cInventario=?," + "iCveMarca=?,"
					+ "cModelo=?," + "dtAdquisicion=?," + "iCveUsuRegistra=?,"
					+ "iCveEstadoEQM=?," + "iPerManttoPrev=?,"
					+ "iLimiteUso=?," + "iPerCalibracion=?,"
					+ "cObservacion=?," + "lBaja=?," + "dtBaja=?,"
					+ "iCveCausaBaja=?, " + "lActivo=? "
					+ "where iCveEquipo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMEquipo.getICveClasificacion());
			pstmt.setInt(2, vEQMEquipo.getICveTpoEquipo());
			pstmt.setString(3, vEQMEquipo.getCCveEquipo().toUpperCase().trim());
			pstmt.setString(4, vEQMEquipo.getCDscEquipo().toUpperCase().trim());
			pstmt.setString(5, vEQMEquipo.getCNumSerie().toUpperCase().trim());
			pstmt.setString(6, vEQMEquipo.getCInventario().toUpperCase().trim());
			pstmt.setInt(7, vEQMEquipo.getICveMarca());
			pstmt.setString(8, vEQMEquipo.getCModelo().toUpperCase().trim());
			pstmt.setDate(9, vEQMEquipo.getDtAdquisicion());
			pstmt.setInt(10, vEQMEquipo.getICveUsuRegistra());
			pstmt.setInt(11, vEQMEquipo.getICveEstadoEQM());
			pstmt.setInt(12, vEQMEquipo.getIPerManttoPrev());
			pstmt.setInt(13, vEQMEquipo.getILimiteUso());
			pstmt.setInt(14, vEQMEquipo.getIPerCalibracion());
			pstmt.setString(15, vEQMEquipo.getCObservacion().toUpperCase()
					.trim());
			pstmt.setInt(16, vEQMEquipo.getLBaja());
			pstmt.setDate(17, vEQMEquipo.getDtBaja());
			pstmt.setInt(18, vEQMEquipo.getICveCausaBaja());
			if (vEQMEquipo.getLBaja() == 1)
				pstmt.setInt(19, 0);
			else
				pstmt.setInt(19, 1);
			pstmt.setInt(20, vEQMEquipo.getICveEquipo());

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
			TVEQMEquipo vEQMEquipo = (TVEQMEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMEquipo" + " set cDscEquipo= ?, "
					+ "cNumSerie= ?, " + "cModelo= ?, " + "iCveTpoEquipo= ?, "
					+ "iCveMarca= ? " + "where iCveEquipo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vEQMEquipo.getCDscEquipo().toUpperCase().trim());
			pstmt.setString(2, vEQMEquipo.getCNumSerie().toUpperCase().trim());
			pstmt.setString(3, vEQMEquipo.getCModelo().toUpperCase().trim());
			pstmt.setInt(4, vEQMEquipo.getICveTpoEquipo());
			pstmt.setInt(5, vEQMEquipo.getICveMarca());
			pstmt.setInt(6, vEQMEquipo.getICveEquipo());
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
			TVEQMEquipo vEQMEquipo = (TVEQMEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EQMEquipo" + " where iCveEquipo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMEquipo.getICveEquipo());
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
				warn("delete.closeEQMEquipo", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All join tpoEquipo, clasificacion, marca, estado,
	 * causabaja
	 */
	public Vector FindByAllDesc(String filtro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMEquipo vEQMEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select EqmEquipo.iCveEquipo,EqmEquipo.iCveClasificacion,EqmEquipo.iCveTpoEquipo, "
					+ "EqmEquipo.cCveEquipo,EqmEquipo.cDscEquipo,EqmEquipo.cNumSerie,EqmEquipo.cInventario, "
					+ "EqmEquipo.iCveMarca,EqmEquipo.cModelo,EqmEquipo.dtAdquisicion,EqmEquipo.iCveUsuRegistra, "
					+ "EqmEquipo.iCveEstadoEQM,EqmEquipo.lActivo,EqmEquipo.iPerManttoPrev,EqmEquipo.iLimiteUso, "
					+ "EqmEquipo.iPerCalibracion,EqmEquipo.cObservacion,EqmEquipo.lBaja,EqmEquipo.dtBaja, "
					+ "EqmEquipo.iCveCausaBaja,EqmTpoEquipo.cDscBreve,EqmClasificacion.cDscBreve, "
					+ "EqmMarca.cDscBreve,EqmEstado.cDscEstadoEqm,EqmCausaBaja.cDscCausaBaja "
					+ "from EqmEquipo "
					+ "join EQMTpoEquipo on EqmEquipo.iCveClasificacion=EQMTpoEquipo.iCveClasificacion "
					+ "and EqmEquipo.iCveTpoEquipo=EQMTpoEquipo.iCveTpoEquipo "
					+ "join EqmClasificacion on EqmEquipo.iCveClasificacion=EqmClasificacion.iCveClasificacion "
					+ "join EqmMarca on EqmEquipo.iCveMarca=EqmMarca.iCveMarca "
					+ "join EqmEstado on EqmEstado.iCveEstadoEQM=EqmEquipo.iCveEstadoEqm "
					+ "left join EqmCausaBaja on EqmEquipo.iCveCausaBaja=EqmCausaBaja.iCveCausaBaja "
					+ filtro;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMEquipo = new TVEQMEquipo();
				vEQMEquipo.setICveEquipo(rset.getInt(1));
				vEQMEquipo.setICveClasificacion(rset.getInt(2));
				vEQMEquipo.setICveTpoEquipo(rset.getInt(3));
				vEQMEquipo.setCCveEquipo(rset.getString(4));
				vEQMEquipo.setCDscEquipo(rset.getString(5));
				vEQMEquipo.setCNumSerie(rset.getString(6));
				vEQMEquipo.setCInventario(rset.getString(7));
				vEQMEquipo.setICveMarca(rset.getInt(8));
				vEQMEquipo.setCModelo(rset.getString(9));
				vEQMEquipo.setDtAdquisicion(rset.getDate(10));
				vEQMEquipo.setICveUsuRegistra(rset.getInt(11));
				vEQMEquipo.setICveEstadoEQM(rset.getInt(12));
				vEQMEquipo.setLActivo(rset.getInt(13));
				vEQMEquipo.setIPerManttoPrev(rset.getInt(14));
				vEQMEquipo.setILimiteUso(rset.getInt(15));
				vEQMEquipo.setIPerCalibracion(rset.getInt(16));
				vEQMEquipo.setCObservacion(rset.getString(17));
				vEQMEquipo.setLBaja(rset.getInt(18));
				vEQMEquipo.setDtBaja(rset.getDate(19));
				vEQMEquipo.setICveCausaBaja(rset.getInt(20));
				vEQMEquipo.setCDscBreveTpoEquipo(rset.getString(21));
				vEQMEquipo.setCDscBreveClasif(rset.getString(22));
				vEQMEquipo.setCDscBreveMarca(rset.getString(23));
				vEQMEquipo.setCDscBreveEstado(rset.getString(24));
				vEQMEquipo.setCDscBreveCausaBaja(rset.getString(25));

				vcEQMEquipo.addElement(vEQMEquipo);
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
			return vcEQMEquipo;
		}
	}

	/**
	 * Metodo encargado de realizar la b�squeda de equipos m�dicos asignados
	 * o no asignados (seg�n filtro).
	 * 
	 * @param filtro
	 *            Filtro con las condiciones que se deben cumplir para la
	 *            obtenci�n de registros.
	 * @return Vector de objetos del tipo TVEquipoAsignacion con los registros
	 *         resultantes de la b�squeda.
	 * @throws DAOException
	 */
	public Vector FindByAsignacion(StringBuffer filtro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEquipoAsignacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer("");
			TVEquipoAsignacion vEquipoAsignacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append("select ")
					.append("E.iCveEquipo, E.cDscEquipo, M.cDscBreve, E.cModelo, E.cNumSerie, A.lActual, ")
					.append("A.iCveUsuResp, U.cNombre, U.cApPaterno, U.cApMaterno, A.iCveAsignacion, ")
					.append("C.cDscBreve, TE.cDscBreve ")
					.append("FROM EQMEquipo E ")
					.append("LEFT JOIN EQMAsignacion A ON E.iCveEquipo = A.iCveEquipo ")
					.append("JOIN EQMMarca M ON M.iCveMarca = E.iCveMarca ")
					.append("LEFT JOIN SEGUsuario U ON U.iCveUsuario = A.iCveUsuResp ")
					.append("LEFT JOIN EQMClasificacion C ON C.iCveClasificacion = E.iCveClasificacion ")
					.append("LEFT JOIN EQMTpoEquipo TE ON TE.iCveClasificacion = E.iCveClasificacion AND TE.iCveTpoEquipo = E.iCveTpoEquipo ")
					.append("WHERE E.lBaja = 0 ").append(filtro);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEquipoAsignacion = new TVEquipoAsignacion();
				vEquipoAsignacion.VEquipo.setICveEquipo(rset.getInt(1));
				vEquipoAsignacion.VEquipo.setCDscEquipo(rset.getString(2));
				vEquipoAsignacion.VEquipo.setCDscBreveMarca(rset.getString(3));
				vEquipoAsignacion.VEquipo.setCModelo(rset.getString(4));
				vEquipoAsignacion.VEquipo.setCNumSerie(rset.getString(5));
				vEquipoAsignacion.VAsignacion.setLActual(rset.getInt(6));
				vEquipoAsignacion.VAsignacion.setICveUsuResp(rset.getInt(7));
				vEquipoAsignacion.setCNombre(rset.getString(8));
				vEquipoAsignacion.setCApPaterno(rset.getString(9));
				vEquipoAsignacion.setCApMaterno(rset.getString(10));
				vEquipoAsignacion.VAsignacion
						.setICveAsignacion(rset.getInt(11));
				vEquipoAsignacion.VEquipo.setCDscBreveClasificacion(rset
						.getString(12));
				vEquipoAsignacion.VEquipo.setCDscBreveTpoEquipo(rset
						.getString(13));
				vcEquipoAsignacion.addElement(vEquipoAsignacion);
			}
		} catch (Exception ex) {
			warn("FindByAsignacion", ex);
			throw new DAOException("FindByAsignacion");
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
				warn("FindByAsignacion.close", ex2);
			}
			return vcEquipoAsignacion;
		}
	}

	/**
	 * Metodo disable
	 */
	public Object disable(Connection conGral, Object obj) throws DAOException {
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
			TVEQMEquipo vEQMEquipo = (TVEQMEquipo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EQMEquipo" + " set lActivo= 0 "
					+ "where iCveEquipo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEQMEquipo.getICveEquipo());
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
	 * Metodo Find By All Equipo
	 */
	public Vector FindByAllEquipo(String cEquipo) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMMantenimiento vEQMMantenimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select EqmClasificacion.cDscBreve,EqmTpoEquipo.cDscBreve,EqmEquipo.cDscEquipo,"
					+ "EqmMarca.cDscBreve,EqmEquipo.cModelo,EqmEquipo.cNumSerie,EqmEquipo.cInventario, EQMEquipo.iCveEquipo "
					+ "from EqmEquipo "
					+ "join EqmClasificacion on EqmClasificacion.iCveClasificacion=EqmEquipo.iCveClasificacion "
					+ "join EqmTpoEquipo on EqmTpoEquipo.iCveClasificacion=EqmEquipo.iCveClasificacion "
					+ "and EqmEquipo.iCveTpoEquipo=EqmTpoEquipo.iCveTpoEquipo "
					+ "join EqmMarca on EqmMarca.iCveMarca=EqmEquipo.iCveMarca "
					+ "where EqmEquipo.iCveEquipo=" + cEquipo;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				TVEQMEquipo vEQMEquipo = new TVEQMEquipo();
				vEQMEquipo.setCDscBreveClasif(rset.getString(1));
				vEQMEquipo.setCDscBreveTpoEquipo(rset.getString(2));
				vEQMEquipo.setCDscEquipo(rset.getString(3));
				vEQMEquipo.setCDscBreveMarca(rset.getString(4));
				vEQMEquipo.setCModelo(rset.getString(5));
				vEQMEquipo.setCNumSerie(rset.getString(6));
				vEQMEquipo.setCInventario(rset.getString(7));
				vEQMEquipo.setICveEquipo(rset.getInt(8));
				vcEQMEquipo.addElement(vEQMEquipo);
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
			return vcEQMEquipo;
		}
	}

	public Vector FindByNotEqualIn(String ivEquipo) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEQMEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEQMEquipo vEQMEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "iCveEquipo, "
					+ "cDscEquipo, "
					+ "cNumSerie, "
					+ "cModelo "
					+ "from	EQMEquipo "
					+ "where iCveEquipo not in (select iCveEquipo from TOXEquipo where iCveEquipo <> "
					+ ivEquipo + ") " + "order by iCveEquipo ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEQMEquipo = new TVEQMEquipo();
				vEQMEquipo.setICveEquipo(rset.getInt(1));
				vEQMEquipo.setCDscEquipo(rset.getString(2));
				vEQMEquipo.setCNumSerie(rset.getString(3));
				vEQMEquipo.setCModelo(rset.getString(4));
				vcEQMEquipo.addElement(vEQMEquipo);
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
			return vcEQMEquipo;
		}
	}
}
