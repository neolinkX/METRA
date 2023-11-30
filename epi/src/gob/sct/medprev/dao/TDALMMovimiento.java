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
 * Title: Data Acces Object de ALMMovimiento DAO
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

public class TDALMMovimiento extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMMovimiento() {
	}

	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMMovimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMMovimiento vALMMovimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iAnio,  " + " iCveAlmacen,  "
					+ " iCveMovimiento,  " + " iCveSolicSum,  "
					+ " iCveArticulo,   " + " iCveTpoMovimiento,   "
					+ " iCveConcepto,   " + " dUnidades,   "
					+ " iCveUsuario,   " + " dtMovimiento,   "
					+ " cObservacion,   " + " lPNC   " + " from ALMMovimiento "
					+ cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMMovimiento = new TVALMMovimiento();
				vALMMovimiento.setiAnio(rset.getInt(1));
				vALMMovimiento.setiCveAlmacen(rset.getInt(2));
				vALMMovimiento.setiCveMovimiento(rset.getInt(3));
				vALMMovimiento.setiCveSolicSum(rset.getInt(4));
				vALMMovimiento.setiCveArticulo(rset.getInt(5));
				vALMMovimiento.setiCveTpoMovimiento(rset.getInt(6));
				vALMMovimiento.setiCveConcepto(rset.getInt(7));
				vALMMovimiento.setdUnidades(rset.getDouble(8));
				vALMMovimiento.setiCveUsuario(rset.getInt(9));
				vALMMovimiento.setdtMovimiento(rset.getDate(10));
				vALMMovimiento.setcObservacion(rset.getString(11));
				vALMMovimiento.setlPNC(rset.getInt(12));
				vcALMMovimiento.addElement(vALMMovimiento);
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
			return vcALMMovimiento;
		}
	}

	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMMovimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMMovimiento vALMMovimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " ALMMovimiento.iAnio,  "
					+ " ALMMovimiento.iCveAlmacen,  "
					+ " ALMMovimiento.iCveMovimiento,  "
					+ " ALMMovimiento.iCveSolicSum,  "
					+ " ALMMovimiento.iCveArticulo,   "
					+ " ALMMovimiento.iCveTpoMovimiento,   "
					+ " ALMMovimiento.iCveConcepto,   "
					+ " ALMMovimiento.dUnidades,   "
					+ " ALMMovimiento.iCveUsuario,   "
					+ " ALMMovimiento.dtMovimiento,   "
					+ " ALMMovimiento.cObservacion,   "
					+ " ALMMovimiento.lPNC   " + " from ALMMovimiento "
					+ cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMMovimiento = new TVALMMovimiento();
				vALMMovimiento.setiAnio(rset.getInt(1));
				vALMMovimiento.setiCveAlmacen(rset.getInt(2));
				vALMMovimiento.setiCveMovimiento(rset.getInt(3));
				vALMMovimiento.setiCveSolicSum(rset.getInt(4));
				vALMMovimiento.setiCveArticulo(rset.getInt(5));
				vALMMovimiento.setiCveTpoMovimiento(rset.getInt(6));
				vALMMovimiento.setiCveConcepto(rset.getInt(7));
				vALMMovimiento.setdUnidades(rset.getDouble(8));
				vALMMovimiento.setiCveUsuario(rset.getInt(9));
				vALMMovimiento.setdtMovimiento(rset.getDate(10));
				vALMMovimiento.setcObservacion(rset.getString(11));
				vALMMovimiento.setlPNC(rset.getInt(12));
				vcALMMovimiento.addElement(vALMMovimiento);
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
			return vcALMMovimiento;
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
			TVALMMovimiento VALMMovimiento = (TVALMMovimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMMovimiento" + " set iCveSolicSum = ? ,"
					+ "     iCveArticulo = ? ,"
					+ "     iCveTpoMovimiento = ? ,"
					+ "     iCveConcepto = ? ," + "     dUnidades = ? ,"
					+ "     iCveUsuario = ? ," + "     dtMovimiento = ? ,"
					+ "     cObservacion = ? ," + "     lPNC = ? "
					+ " where iAnio = ? " + "   and iCveAlmacen = ? "
					+ "   and iCveMovimiento = ? " + "";
			pstmt = conn.prepareStatement(cSQL);

			if (new Integer(VALMMovimiento.getiCveSolicSum()) == null)
				pstmt.setNull(1, Types.INTEGER);
			else
				pstmt.setInt(1, VALMMovimiento.getiCveSolicSum());

			if (new Integer(VALMMovimiento.getiCveArticulo()) == null)
				pstmt.setNull(2, Types.INTEGER);
			else
				pstmt.setInt(2, VALMMovimiento.getiCveArticulo());

			if (new Integer(VALMMovimiento.getiCveTpoMovimiento()) == null)
				pstmt.setNull(3, Types.INTEGER);
			else
				pstmt.setInt(3, VALMMovimiento.getiCveTpoMovimiento());

			if (new Integer(VALMMovimiento.getiCveConcepto()) == null)
				pstmt.setNull(4, Types.INTEGER);
			else
				pstmt.setInt(4, VALMMovimiento.getiCveConcepto());

			if (new Double(VALMMovimiento.getdUnidades()) == null)
				pstmt.setNull(5, Types.FLOAT);
			else
				pstmt.setDouble(5, VALMMovimiento.getdUnidades());

			if (new Integer(VALMMovimiento.getiCveUsuario()) == null)
				pstmt.setNull(6, Types.INTEGER);
			else
				pstmt.setInt(6, VALMMovimiento.getiCveUsuario());

			if (VALMMovimiento.getdtMovimiento() == null)
				pstmt.setNull(7, Types.DATE);
			else
				pstmt.setDate(7, VALMMovimiento.getdtMovimiento());

			if (VALMMovimiento.getcObservacion() == null)
				pstmt.setNull(8, Types.VARCHAR);
			else
				pstmt.setString(8, VALMMovimiento.getcObservacion()
						.toUpperCase().trim());

			if (new Integer(VALMMovimiento.getlPNC()) == null)
				pstmt.setNull(9, Types.INTEGER);
			else
				pstmt.setInt(9, VALMMovimiento.getlPNC());

			pstmt.setInt(10, VALMMovimiento.getiAnio());
			pstmt.setInt(11, VALMMovimiento.getiCveAlmacen());
			pstmt.setInt(12, VALMMovimiento.getiCveMovimiento());

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
			TVALMMovimiento vALMMovimiento = (TVALMMovimiento) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " delete from ALMMovimiento " + " where iAnio = ?     "
					+ "   and iCveAlmacen = ?      "
					+ "   and iCveMovimiento = ?      " + "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMMovimiento.getiAnio());
			pstmt.setInt(2, vALMMovimiento.getiCveAlmacen());
			pstmt.setInt(3, vALMMovimiento.getiCveMovimiento());
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
				warn("delete.closeALMMovimiento", ex2);
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
				warn("delete.closeALMMovimiento", ex2);
			}
			return cClave;
		}
	}

	public Connection getConnection() {
		try {
			super.dbConn = new DbConnection(dataSourceName);
			super.conn = dbConn.getConnection();
			super.conn.setAutoCommit(false);
			super.conn.setTransactionIsolation(2);
		} catch (Exception ex) {
			warn("No se pudo abrir conexión a BD.", ex);
		}
		return super.conn;
	}

	public void closeConnection() {
		try {
			if (super.conn != null && !super.conn.isClosed()) {
				super.conn.close();
				super.dbConn.closeConnection();
			}
		} catch (Exception ex) {
			warn("No se pudo cerrar la conexiónn.", ex);
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVALMMovimiento vALMMovimiento = (TVALMMovimiento) obj;
			cSQL = " insert into ALMMovimiento( " + " iAnio, "
					+ " iCveAlmacen, " + " iCveMovimiento, "
					+ " iCveSolicSum, " + " iCveArticulo,"
					+ " iCveTpoMovimiento," + " iCveConcepto," + " dUnidades,"
					+ " iCveUsuario," + " dtMovimiento," + " cObservacion,"
					+ " lPNC " + " ) values (?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vALMMovimiento.getiAnio());
			pstmt.setInt(2, vALMMovimiento.getiCveAlmacen());
			pstmt.setInt(3, vALMMovimiento.getiCveMovimiento());

			if (new Integer(vALMMovimiento.getiCveSolicSum()) == null)
				pstmt.setNull(4, Types.INTEGER);
			else
				pstmt.setInt(4, vALMMovimiento.getiCveSolicSum());

			if (new Integer(vALMMovimiento.getiCveArticulo()) == null)
				pstmt.setNull(5, Types.INTEGER);
			else
				pstmt.setInt(5, vALMMovimiento.getiCveArticulo());

			if (new Integer(vALMMovimiento.getiCveTpoMovimiento()) == null)
				pstmt.setNull(6, Types.INTEGER);
			else
				pstmt.setInt(6, vALMMovimiento.getiCveTpoMovimiento());

			if (new Integer(vALMMovimiento.getiCveConcepto()) == null)
				pstmt.setNull(7, Types.INTEGER);
			else
				pstmt.setInt(7, vALMMovimiento.getiCveConcepto());

			if (new Double(vALMMovimiento.getdUnidades()) == null)
				pstmt.setNull(8, Types.FLOAT);
			else
				pstmt.setDouble(8, vALMMovimiento.getdUnidades());

			if (new Integer(vALMMovimiento.getiCveUsuario()) == null)
				pstmt.setNull(9, Types.INTEGER);
			else
				pstmt.setInt(9, vALMMovimiento.getiCveUsuario());

			if (vALMMovimiento.getdtMovimiento() == null)
				pstmt.setNull(10, Types.DATE);
			else
				pstmt.setDate(10, vALMMovimiento.getdtMovimiento());

			if (vALMMovimiento.getcObservacion() == null)
				pstmt.setNull(11, Types.VARCHAR);
			else
				pstmt.setString(11, vALMMovimiento.getcObservacion()
						.toUpperCase().trim());

			if (new Integer(vALMMovimiento.getlPNC()) == null)
				pstmt.setNull(12, Types.INTEGER);
			else
				pstmt.setInt(12, vALMMovimiento.getlPNC());

			if (pstmt.executeUpdate() > 0)
				cClave = "" + vALMMovimiento.getiCveMovimiento();
			else
				cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (conGral == null)
					conn.rollback();
			} catch (Exception ex1) {
				warn("insert", ex1);
			}
			warn("insert", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conGral == null)
					if (!conn.isClosed()) {
						conn.close();
						dbConn.closeConnection();
					}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	public Vector FindByAllMovXArt(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMMovimiento = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMMovimiento vALMMovimiento;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select M.iCveMovimiento, T.cDscBreve, C.cDscConcepto, M.dUnidades, "
					+ "U.cNombre||' '||U.cApPaterno||' '||U.cApMaterno, M.dtMovimiento, M.cObservacion "
					+ "from ALMMovimiento M, ALMTpoMovimiento T, ALMConcepto C, SEGUsuario U "
					+ "where M.iCveTpoMovimiento=T.iCveTpoMovimiento "
					+ "and M.iCveTpoMovimiento=C.iCveTpoMovimiento "
					+ "and M.iCveConcepto=C.iCveConcepto "
					+ "and M.iCveUsuario=U.iCveUsuario " + cWhere;
			// System.out.println("Sql ---> " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				// System.out.println("--->Agregando elemento.");
				vALMMovimiento = new TVALMMovimiento();
				vALMMovimiento.setiCveMovimiento(rset.getInt(1));
				vALMMovimiento.setcDscBreve(rset.getString(2));
				vALMMovimiento.setcDscConcepto(rset.getString(3));
				vALMMovimiento.setdUnidades(rset.getDouble(4));
				vALMMovimiento.setcUsuario(rset.getString(5));
				vALMMovimiento.setdtMovimiento(rset.getDate(6));
				vALMMovimiento.setcObservacion(rset.getObject(7) == null ? ""
						: rset.getString(7));
				vcALMMovimiento.addElement(vALMMovimiento);
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
			return vcALMMovimiento;
		}
	}

	public int FindMaxMov(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;

		int iMaxValue = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " SELECT MAX(iCveMovimiento) AS iCveMovimiento "
					+ " FROM ALMMovimiento " + cWhere;

			pstmt = conn.createStatement();
			rset = pstmt.executeQuery(cSQL);
			while (rset.next()) {
				iMaxValue = (rset.getInt("iCveMovimiento"));
			}
			iMaxValue++;
		} catch (Exception ex) {
			ex.printStackTrace();
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

			}
			return iMaxValue;
		}
	}

}