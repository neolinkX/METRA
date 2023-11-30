package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMSumArt DAO
 * </p>
 * <p>
 * Description: DAO para la tabla ALMSumArt
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

public class TDALMSumArt extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMSumArt() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSumArt = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSumArt vALMSumArt;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveAlmacen," + "iCveSolicSum,"
					+ "iCveArticulo," + "iCveMeta," + "dUnidSolicita,"
					+ "dUnidAutor," + "cObservacion," + "lAutorizada,"
					+ "lAnalizada" + " from ALMSumArt " + cFiltro + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSumArt = new TVALMSumArt();
				vALMSumArt.setIAnio(rset.getInt(1));
				vALMSumArt.setICveAlmacen(rset.getInt(2));
				vALMSumArt.setICveSolicSum(rset.getInt(3));
				vALMSumArt.setICveArticulo(rset.getInt(4));
				vALMSumArt.setICveMeta(rset.getInt(5));
				vALMSumArt.setDUnidSolicita(rset.getFloat(6));
				vALMSumArt.setDUnidAutor(rset.getFloat(7));
				vALMSumArt.setCObservacion(rset.getString(8));
				vALMSumArt.setLAutorizada(rset.getInt(9));
				vALMSumArt.setLAnalizada(rset.getInt(10));
				vcALMSumArt.addElement(vALMSumArt);
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
			return vcALMSumArt;
		}
	}

	/**
	 * Metodo Find By Count - Proceso que regresa el numero de Articulos por
	 * autorizar de una Solicitud.
	 */
	public int FindByCount(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSumArt = new Vector();
		int count = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSumArt vALMSumArt = (TVALMSumArt) Obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL =

			"  select count(iCveArticulo) "
					+ "  from almsolicsumin "
					+ "  join almsumart on almsumart.ianio = almsolicsumin.ianio and "
					+ "                                          almsumart.icvealmacen = almsolicsumin.icvealmacen and "
					+ "                                          almsumart.icvesolicsum =  almsolicsumin.icvesolicsum and "
					+ "                                          almsumart.lanalizada = 0 "
					+ "  where almsolicsumin.ianio = " + vALMSumArt.getIAnio()
					+ "  and almsolicsumin.icvealmacen = "
					+ vALMSumArt.getICveAlmacen()
					+ "  and almsolicsumin.icvesolicsum = "
					+ vALMSumArt.getICveSolicSum();

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				count = rset.getInt(1);
			}
		} catch (Exception ex) {
			warn("FindByCount", ex);
			throw new DAOException("FindByCount");
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
				warn("FindByCount.close", ex2);
			}
			return count;
		}
	}

	/**
	 * Metodo Find By All, Tablas: ALMSumArt, ALMArticulo, ALMUnidad,
	 * ALMFamilia, ALMTpoArticulo
	 */
	public Vector FindByAll2(String cWhere, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSumArt = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSumArt vALMSumArt;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "ALMTpoArticulo.iCveTpoArticulo, "
					+ "ALMTpoArticulo.cDscTpoArticulo, "
					+ "ALMFamilia.iCveFamilia, "
					+ "ALMFamilia.cDscFamilia, "
					+ "ALMSumArt.iCveArticulo, "
					+ "ALMArticulo.cDscArticulo, "
					+ "ALMArticulo.cDscBreve, "
					+ "ALMSumArt.dUnidSolicita, "
					+ "ALMSumArt.dUnidAutor, "
					+ "ALMArticulo.iCveUniSum, "
					+ "ALMUnidad.cDscUnidad "
					+ "from "
					+ "        ALMSumArt join ( "
					+ "                ALMArticulo join ALMTpoArticulo on ALMArticulo.iCveTpoArticulo = ALMTpoArticulo.iCveTpoArticulo "
					+ "                            join ALMUnidad on ALMArticulo.iCveUniSum = ALMUnidad.iCveUnidad "
					+ "                            join ALMFamilia on ALMArticulo.iCveFamilia = ALMFamilia.iCveFamilia "
					+ "                                and ALMArticulo.iCveTpoArticulo = ALMFamilia.iCveTpoArticulo "
					+ "        ) on ALMSumArt.iCveArticulo = ALMArticulo.iCveArticulo "
					+ cWhere + cOrderBy;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSumArt = new TVALMSumArt();

				vALMSumArt.setICveTpoArticulo(rset.getInt(1));
				vALMSumArt.setCDscTpoArticulo(rset.getString(2));
				vALMSumArt.setICveFamilia(rset.getInt(3));
				vALMSumArt.setCDscFamilia(rset.getString(4));
				vALMSumArt.setICveArticulo(rset.getInt(5));
				vALMSumArt.setCDscArticulo(rset.getString(6));
				vALMSumArt.setcDscBreve(rset.getString(7));
				vALMSumArt.setDUnidSolicita(rset.getFloat(8));
				vALMSumArt.setDUnidAutor(rset.getFloat(9));
				vALMSumArt.setICveUniSum(rset.getInt(10));
				vALMSumArt.setCDscUnidad(rset.getString(11));

				vcALMSumArt.addElement(vALMSumArt);
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
			return vcALMSumArt;
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
			TVALMSumArt vALMSumArt = (TVALMSumArt) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into ALMSumArt(" + "iAnio," + "iCveAlmacen,"
					+ "iCveSolicSum," + "iCveArticulo," + "iCveMeta,"
					+ "dUnidSolicita," + "dUnidAutor," + "cObservacion,"
					+ "lAutorizada," + "lAnalizada"
					+ ")values(?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vALMSumArt.getIAnio());
			pstmt.setInt(2, vALMSumArt.getICveAlmacen());
			pstmt.setInt(3, vALMSumArt.getICveSolicSum());
			pstmt.setInt(4, vALMSumArt.getICveArticulo());
			pstmt.setInt(5, vALMSumArt.getICveMeta());
			pstmt.setFloat(6, vALMSumArt.getDUnidSolicita());
			pstmt.setFloat(7, vALMSumArt.getDUnidAutor());
			if (vALMSumArt.getCObservacion() == null)
				pstmt.setNull(8, Types.VARCHAR);
			else
				pstmt.setString(8, vALMSumArt.getCObservacion().toUpperCase()
						.trim());
			pstmt.setInt(9, vALMSumArt.getLAutorizada());
			pstmt.setInt(10, vALMSumArt.getLAnalizada());
			pstmt.executeUpdate();
			cClave = "" + vALMSumArt.getIAnio();
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
			TVALMSumArt vALMSumArt = (TVALMSumArt) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMSumArt" + " set iCveMeta= ?, "
					+ "dUnidSolicita= ?, " + "dUnidAutor= ?, "
					+ "cObservacion= ?, " + "lAutorizada= ?, "
					+ "lAnalizada= ? " + "where iAnio = ? "
					+ "and iCveAlmacen = ?" + "and iCveSolicSum = ?"
					+ " and iCveArticulo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMSumArt.getICveMeta());
			pstmt.setFloat(2, vALMSumArt.getDUnidSolicita());
			pstmt.setFloat(3, vALMSumArt.getDUnidAutor());
			if (vALMSumArt.getCObservacion() == null)
				pstmt.setNull(4, Types.VARCHAR);
			else
				pstmt.setString(4, vALMSumArt.getCObservacion().toUpperCase()
						.trim());
			pstmt.setInt(5, vALMSumArt.getLAutorizada());
			pstmt.setInt(6, vALMSumArt.getLAnalizada());
			pstmt.setInt(7, vALMSumArt.getIAnio());
			pstmt.setInt(8, vALMSumArt.getICveAlmacen());
			pstmt.setInt(9, vALMSumArt.getICveSolicSum());
			pstmt.setInt(10, vALMSumArt.getICveArticulo());
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
	 * Metodo update2, SOLO ACTUALIZA LOS VALORES DE DUNISOLICITA Y ICVEMETA.
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
			TVALMSumArt vALMSumArt = (TVALMSumArt) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update ALMSumArt        " + " set dUnidSolicita  = ?, "
					+ "     iCveMeta       = ?  " + " where iAnio        = ?  "
					+ "   and iCveAlmacen  = ?  " + "   and iCveSolicSum = ?  "
					+ "   and iCveArticulo = ?  ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setFloat(1, vALMSumArt.getDUnidSolicita());
			pstmt.setInt(2, vALMSumArt.getICveMeta());
			pstmt.setInt(3, vALMSumArt.getIAnio());
			pstmt.setInt(4, vALMSumArt.getICveAlmacen());
			pstmt.setInt(5, vALMSumArt.getICveSolicSum());
			pstmt.setInt(6, vALMSumArt.getICveArticulo());
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
				warn("update2", ex1);
			}
			warn("update2", ex);
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
				warn("update2.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo update3, SOLO ACTUALIZA EL VALOR DE LANALIZADA = 1, DUNIDAUTOR =
	 * x;
	 */
	public Object update3(Connection conGral, Object obj) throws DAOException {
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
			TVALMSumArt vALMSumArt = (TVALMSumArt) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update ALMSumArt        " + " set lAnalizada     = ?, "
					+ "     dUnidAutor     = ?  " + " where iAnio        = ?  "
					+ "   and iCveAlmacen  = ?  " + "   and iCveSolicSum = ?  "
					+ "   and iCveArticulo = ?  ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 1);
			pstmt.setFloat(2, vALMSumArt.getDUnidAutor());
			pstmt.setInt(3, vALMSumArt.getIAnio());
			pstmt.setInt(4, vALMSumArt.getICveAlmacen());
			pstmt.setInt(5, vALMSumArt.getICveSolicSum());
			pstmt.setInt(6, vALMSumArt.getICveArticulo());
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
	 * Metodo update4, SOLO ACTUALIZA EL VALOR DE LAUTORIZADA = 1;
	 */
	public Object update4(Connection conGral, Object obj) throws DAOException {
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
			TVALMSumArt vALMSumArt = (TVALMSumArt) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update ALMSumArt        " + " set lAutorizada    = ?  "
					+ " where iAnio        = ?  " + "   and iCveAlmacen  = ?  "
					+ "   and iCveSolicSum = ?  " + "   and iCveArticulo = ?  ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, vALMSumArt.getIAnio());
			pstmt.setInt(3, vALMSumArt.getICveAlmacen());
			pstmt.setInt(4, vALMSumArt.getICveSolicSum());
			pstmt.setInt(5, vALMSumArt.getICveArticulo());
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
				warn("update4", ex1);
			}
			warn("update4", ex);
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
				warn("update4.close", ex2);
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
			TVALMSumArt vALMSumArt = (TVALMSumArt) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMSumArt" + " where iAnio = ?"
					+ " and iCveAlmacen = ?" + " and iCveSolicSum = ?"
					+ " and iCveArticulo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMSumArt.getIAnio());
			pstmt.setInt(2, vALMSumArt.getICveAlmacen());
			pstmt.setInt(3, vALMSumArt.getICveSolicSum());
			pstmt.setInt(4, vALMSumArt.getICveArticulo());
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
				warn("delete.closeALMSumArt", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllSolicSumin(String cFiltro, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSumArt = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSumArt vALMSumArt;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select AlmArticulo.iCveArticulo, AlmArticulo.cDscBreve, "
					+ "AlmSumArt.dUnidSolicita, ALMSumArt.dUnidAutor, AlmSumArt.cObservacion, "
					+ "AlmArticulo.lLote, AlmLote.cLote, AlmLote.dtCaducidad, AlmDetalleMov.dUnidades "
					+ "from almsumart "
					+ "join almarticulo "
					+ "   on almarticulo.iCveArticulo = almsumart.iCveArticulo "
					+ "join almmovimiento "
					+ "   on almmovimiento.iAnio = almsumart.iAnio "
					+ "   and almmovimiento.iCveAlmacen = almsumart.iCveAlmacen "
					+ "   and almmovimiento.iCveArticulo = almsumart.iCveArticulo "
					+ "   and almmovimiento.iCveSolicSum = almsumart.iCveSolicSum "
					+ "left join almdetallemov "
					+ "   on almdetallemov.iAnio = almmovimiento.iAnio "
					+ "   and almdetallemov.iCVeAlmacen = almmovimiento.iCveAlmacen "
					+ "   and almdetallemov.iCVeMovimiento = almmovimiento.icvemovimiento "
					+ "left join almlote "
					+ "   on almlote.iCveAlmacen = almdetallemov.iCveAlmacen "
					+ "   and almlote.iCVeArticulo = almmovimiento.iCveArticulo "
					+ "   and almlote.iCveLote = almdetallemov.iCVeLote "
					+ cFiltro + " order by AlmArticulo.iCveArticulo";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSumArt = new TVALMSumArt();
				vALMSumArt.setICveArticulo(rset.getInt(1));
				vALMSumArt.setcDscBreve(rset.getString(2));
				vALMSumArt.setDUnidSolicita(rset.getFloat(3));
				vALMSumArt.setDUnidAutor(rset.getFloat(4));
				vALMSumArt.setCObservacion(rset.getString(5));
				vALMSumArt.setLLote(rset.getInt(6));
				vALMSumArt.setcLote(rset.getObject(7) != null ? rset
						.getString(7) : "");
				vALMSumArt.setdtCaducidad(rset.getDate(8));
				vALMSumArt.setdUnidades(rset.getFloat(9));
				vcALMSumArt.addElement(vALMSumArt);
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
			return vcALMSumArt;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllSolicSumin2(String cFiltro, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSumArt = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSumArt vALMSumArt;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select AlmArticulo.iCveArticulo, AlmArticulo.cDscBreve, "
					+ "AlmSumArt.dUnidSolicita, ALMSumArt.dUnidAutor, AlmSumArt.cObservacion, "
					+ "AlmArticulo.lLote, AlmLote.cLote, AlmLote.dtCaducidad, AlmDetalleMov.dUnidades, ALMArticulo.cCveArticulo, almunidad.cDscUnidad "
					+ "from almsumart "
					+ "join almarticulo "
					+ "   on almarticulo.iCveArticulo = almsumart.iCveArticulo "
					+ "join almUnidad "
					+ "   on almUnidad.iCveUnidad = almarticulo.iCveUniSum "
					+ "join almmovimiento "
					+ "   on almmovimiento.iAnio = almsumart.iAnio "
					+ "   and almmovimiento.iCveAlmacen = almsumart.iCveAlmacen "
					+ "   and almmovimiento.iCveArticulo = almsumart.iCveArticulo "
					+ "   and almmovimiento.iCveSolicSum = almsumart.iCveSolicSum "
					+ "left join almdetallemov "
					+ "   on almdetallemov.iAnio = almmovimiento.iAnio "
					+ "   and almdetallemov.iCVeAlmacen = almmovimiento.iCveAlmacen "
					+ "   and almdetallemov.iCVeMovimiento = almmovimiento.icvemovimiento "
					+ "left join almlote "
					+ "   on almlote.iCveAlmacen = almdetallemov.iCveAlmacen "
					+ "   and almlote.iCVeArticulo = almmovimiento.iCveArticulo "
					+ "   and almlote.iCveLote = almdetallemov.iCVeLote "
					+ cFiltro + " order by AlmArticulo.iCveArticulo";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSumArt = new TVALMSumArt();
				vALMSumArt.setICveArticulo(rset.getInt(1));
				vALMSumArt.setcDscBreve(rset.getString(2));
				vALMSumArt.setDUnidSolicita(rset.getFloat(3));
				vALMSumArt.setDUnidAutor(rset.getFloat(4));
				vALMSumArt.setCObservacion(rset.getString(5));
				vALMSumArt.setLLote(rset.getInt(6));
				vALMSumArt.setcLote(rset.getObject(7) != null ? rset
						.getString(7) : "");
				vALMSumArt.setdtCaducidad(rset.getDate(8));
				vALMSumArt.setdUnidades(rset.getFloat(9));
				vALMSumArt.setcCveArticulo(rset.getString(10));
				vALMSumArt.setCDscUnidad(rset.getString(11));
				vcALMSumArt.addElement(vALMSumArt);
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
			return vcALMSumArt;
		}
	}
}
