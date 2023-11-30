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
 * Title: Data Acces Object de ALMLote DAO
 * </p>
 * <p>
 * Description: DAO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrejón Adame
 * @version 1.0
 */

public class TDALMLote extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMLote() {
	}

	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMLote = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMLote vALMLote;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iCveAlmacen,  " + " iCveArticulo,   "
					+ " iCveLote,   " + " dUnidades,   " + " iCveUsuario,   "
					+ " dtIngreso,   " + " dtCaducidad,   "
					+ " iCveTpoMovimiento,   " + " iCveConcepto,   "
					+ " cObservacion,   " + " dtAgotado,   " + " cLote   "
					+ " from ALMLote " + cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMLote = new TVALMLote();
				vALMLote.setiCveAlmacen(rset.getInt(1));
				vALMLote.setiCveArticulo(rset.getInt(2));
				vALMLote.setiCveLote(rset.getInt(3));
				vALMLote.setdUnidades(rset.getDouble(4));
				vALMLote.setiCveUsuario(rset.getInt(5));
				vALMLote.setdtIngreso(rset.getDate(6));
				vALMLote.setdtCaducidad(rset.getDate(7));
				vALMLote.setiCveTpoMovimiento(rset.getInt(8));
				vALMLote.setiCveConcepto(rset.getInt(9));
				vALMLote.setcObservacion(rset.getString(10));
				vALMLote.setdtAgotado(rset.getDate(11));
				vALMLote.setcLote(rset.getString(12));
				vcALMLote.addElement(vALMLote);
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
			return vcALMLote;
		}
	}

	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMLote = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMLote vALMLote;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " ALMLote.iCveAlmacen,  "
					+ " ALMLote.iCveArticulo,   " + " ALMLote.iCveLote,   "
					+ " ALMLote.dUnidades,   " + " ALMLote.iCveUsuario,   "
					+ " ALMLote.dtIngreso,   " + " ALMLote.dtCaducidad,   "
					+ " ALMLote.iCveTpoMovimiento,   "
					+ " ALMLote.iCveConcepto,   " + " ALMLote.cObservacion,   "
					+ " ALMLote.dtAgotado,   " + " ALMLote.cLote   "
					+ " from ALMLote " + cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMLote = new TVALMLote();
				vALMLote.setiCveAlmacen(rset.getInt(1));
				vALMLote.setiCveArticulo(rset.getInt(2));
				vALMLote.setiCveLote(rset.getInt(3));
				vALMLote.setdUnidades(rset.getDouble(4));
				vALMLote.setiCveUsuario(rset.getInt(5));
				vALMLote.setdtIngreso(rset.getDate(6));
				vALMLote.setdtCaducidad(rset.getDate(7));
				vALMLote.setiCveTpoMovimiento(rset.getInt(8));
				vALMLote.setiCveConcepto(rset.getInt(9));
				vALMLote.setcObservacion(rset.getString(10));
				vALMLote.setdtAgotado(rset.getDate(11));
				vALMLote.setcLote(rset.getString(12));
				vcALMLote.addElement(vALMLote);
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
			return vcALMLote;
		}
	}

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
			TVALMLote VALMLote = (TVALMLote) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMLote" + " set dUnidades = ? ,"
					+ "     iCveUsuario = ? ," + "     dtIngreso = ? ,"
					+ "     dtCaducidad = ? ," + "     iCveTpoMovimiento = ? ,"
					+ "     iCveConcepto = ? ," + "     cObservacion = ? ,"
					+ "     dtAgotado = ? ," + "     cLote = ? "
					+ " where iCveAlmacen = ? " + "   and iCveArticulo = ? "
					+ "   and iCveLote = ? " + "";
			pstmt = conn.prepareStatement(cSQL);

			if (new Double(VALMLote.getdUnidades()) == null)
				pstmt.setNull(1, Types.FLOAT);
			else
				pstmt.setDouble(1, VALMLote.getdUnidades());

			if (new Integer(VALMLote.getiCveUsuario()) == null)
				pstmt.setNull(2, Types.INTEGER);
			else
				pstmt.setInt(2, VALMLote.getiCveUsuario());

			if (VALMLote.getdtIngreso() == null)
				pstmt.setNull(3, Types.DATE);
			else
				pstmt.setDate(3, VALMLote.getdtIngreso());

			if (VALMLote.getdtCaducidad() == null)
				pstmt.setNull(4, Types.DATE);
			else
				pstmt.setDate(4, VALMLote.getdtCaducidad());

			if (new Integer(VALMLote.getiCveTpoMovimiento()) == null)
				pstmt.setNull(5, Types.INTEGER);
			else
				pstmt.setInt(5, VALMLote.getiCveTpoMovimiento());

			if (new Integer(VALMLote.getiCveConcepto()) == null)
				pstmt.setNull(6, Types.INTEGER);
			else
				pstmt.setInt(6, VALMLote.getiCveConcepto());

			if (VALMLote.getcObservacion() == null)
				pstmt.setNull(7, Types.VARCHAR);
			else
				pstmt.setString(7, VALMLote.getcObservacion().toUpperCase()
						.trim());

			if (VALMLote.getdtAgotado() == null)
				pstmt.setNull(8, Types.DATE);
			else
				pstmt.setDate(8, VALMLote.getdtAgotado());

			if (VALMLote.getcLote() == null)
				pstmt.setNull(9, Types.VARCHAR);
			else
				pstmt.setString(9, VALMLote.getcLote().toUpperCase().trim());

			pstmt.setInt(10, VALMLote.getiCveAlmacen());
			pstmt.setInt(11, VALMLote.getiCveArticulo());
			pstmt.setInt(12, VALMLote.getiCveLote());

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

	public Object updExistencias(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null)
				conn = conGral;
			else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVALMLote VALMLote = (TVALMLote) obj;
			cSQL = "update ALMLote           " + " set dUnidades      = ? ,"
					+ "     dtAgotado      = ?  " + " where iCveAlmacen  = ?  "
					+ "   and iCveArticulo = ?  " + "   and iCveLote     = ?  "
					+ "";
			pstmt = conn.prepareStatement(cSQL);

			if (new Double(VALMLote.getdUnidades()) == null)
				pstmt.setNull(1, Types.FLOAT);
			else
				pstmt.setDouble(1, VALMLote.getdUnidades());
			if (VALMLote.getdtAgotado() == null)
				pstmt.setNull(2, Types.DATE);
			else
				pstmt.setDate(2, VALMLote.getdtAgotado());

			pstmt.setInt(3, VALMLote.getiCveAlmacen());
			pstmt.setInt(4, VALMLote.getiCveArticulo());
			pstmt.setInt(5, VALMLote.getiCveLote());

			if (pstmt.executeUpdate() > 0)
				cClave = "" + VALMLote.getiCveLote();
			else
				cClave = "";
			if (conGral == null)
				conn.commit();
		} catch (Exception ex) {
			try {
				if (conGral == null)
					conn.rollback();
			} catch (Exception ex1) {
				warn("update", ex1);
			}
			warn("update", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conGral == null) {
					if (!conn.isClosed()) {
						conn.close();
						dbConn.closeConnection();
					}
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

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
			TVALMLote vALMLote = (TVALMLote) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " delete from ALMLote " + " where iCveAlmacen = ?     "
					+ "   and iCveArticulo = ?      "
					+ "   and iCveLote = ?      " + "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMLote.getiCveAlmacen());
			pstmt.setInt(2, vALMLote.getiCveArticulo());
			pstmt.setInt(3, vALMLote.getiCveLote());
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
				warn("delete.closeALMLote", ex2);
			}
			return cClave;
		}
	}

	public Object deleteCustomWhere(Connection conGral, String cSQL)
			throws DAOException {
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
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

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
				warn("delete.closeALMLote", ex2);
			}
			return cClave;
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
			TVALMLote vALMLote = (TVALMLote) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " insert into ALMLote( " + " iCveAlmacen, "
					+ " iCveArticulo," + " iCveLote," + " dUnidades,"
					+ " iCveUsuario," + " dtIngreso," + " dtCaducidad,"
					+ " iCveTpoMovimiento," + " iCveConcepto,"
					+ " cObservacion," + " dtAgotado," + " cLote "
					+ " ) values (?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vALMLote.getiCveAlmacen());
			pstmt.setInt(2, vALMLote.getiCveArticulo());
			pstmt.setInt(3, vALMLote.getiCveLote());

			if (new Double(vALMLote.getdUnidades()) == null)
				pstmt.setNull(4, Types.FLOAT);
			else
				pstmt.setDouble(4, vALMLote.getdUnidades());

			if (new Integer(vALMLote.getiCveUsuario()) == null)
				pstmt.setNull(5, Types.INTEGER);
			else
				pstmt.setInt(5, vALMLote.getiCveUsuario());

			if (vALMLote.getdtIngreso() == null)
				pstmt.setNull(6, Types.DATE);
			else
				pstmt.setDate(6, vALMLote.getdtIngreso());

			if (vALMLote.getdtCaducidad() == null)
				pstmt.setNull(7, Types.DATE);
			else
				pstmt.setDate(7, vALMLote.getdtCaducidad());

			if (new Integer(vALMLote.getiCveTpoMovimiento()) == null)
				pstmt.setNull(8, Types.INTEGER);
			else
				pstmt.setInt(8, vALMLote.getiCveTpoMovimiento());

			if (new Integer(vALMLote.getiCveConcepto()) == null)
				pstmt.setNull(9, Types.INTEGER);
			else
				pstmt.setInt(9, vALMLote.getiCveConcepto());

			if (vALMLote.getcObservacion() == null)
				pstmt.setNull(10, Types.VARCHAR);
			else
				pstmt.setString(10, vALMLote.getcObservacion().toUpperCase()
						.trim());

			if (vALMLote.getdtAgotado() == null)
				pstmt.setNull(11, Types.DATE);
			else
				pstmt.setDate(11, vALMLote.getdtAgotado());

			if (vALMLote.getcLote() == null)
				pstmt.setNull(12, Types.VARCHAR);
			else
				pstmt.setString(12, vALMLote.getcLote().toUpperCase().trim());

			pstmt.executeUpdate();
			cClave = "" + vALMLote.getiCveAlmacen();
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

}