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
 * Title: Data Acces Object de ALMInventario DAO
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

public class TDALMInventario extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMInventario() {
	}

	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMInventario = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMInventario vALMInventario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iAnio,  " + " iCveAlmacen,  "
					+ " iCveInventario,  " + " dtGeneracion,   "
					+ " dtConteo,   " + " dtActualizacion,   "
					+ " iCveUsuGenera,  " + " iCveAutoriza,   "
					+ " cObservacion   " + " from ALMInventario " + cWhere
					+ " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMInventario = new TVALMInventario();
				vALMInventario.setiAnio(rset.getInt(1));
				vALMInventario.setiCveAlmacen(rset.getInt(2));
				vALMInventario.setiCveInventario(rset.getInt(3));
				vALMInventario.setdtGeneracion(rset.getDate(4));
				vALMInventario.setdtConteo(rset.getDate(5));
				vALMInventario.setdtActualizacion(rset.getDate(6));
				vALMInventario.setiCveUsuGenera(rset.getInt(7));
				vALMInventario.setiCveAutoriza(rset.getInt(8));
				vALMInventario.setcObservacion(rset.getString(9));

				vcALMInventario.addElement(vALMInventario);
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
			return vcALMInventario;
		}
	}

	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMInventario = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMInventario vALMInventario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " ALMInventario.iAnio,  "
					+ " ALMInventario.iCveAlmacen,  "
					+ " ALMInventario.iCveInventario,  "
					+ " ALMInventario.dtGeneracion,   "
					+ " ALMInventario.dtConteo,   "
					+ " ALMInventario.dtActualizacion,   "
					+ " ALMInventario.iCveUsuGenera,  "
					+ " ALMInventario.iCveAutoriza,   "
					+ " ALMInventario.cObservacion   " + " from ALMInventario "
					+ cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMInventario = new TVALMInventario();
				vALMInventario.setiAnio(rset.getInt(1));
				vALMInventario.setiCveAlmacen(rset.getInt(2));
				vALMInventario.setiCveInventario(rset.getInt(3));
				vALMInventario.setdtGeneracion(rset.getDate(4));
				vALMInventario.setdtConteo(rset.getDate(5));
				vALMInventario.setdtActualizacion(rset.getDate(6));
				vALMInventario.setiCveUsuGenera(rset.getInt(7));
				vALMInventario.setiCveAutoriza(rset.getInt(8));
				vALMInventario.setcObservacion(rset.getString(9));
				vcALMInventario.addElement(vALMInventario);
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
			return vcALMInventario;
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
			TVALMInventario VALMInventario = (TVALMInventario) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMInventario" + " set dtGeneracion     = ? ,"
					+ "     dtConteo         = ? ,"
					+ "     dtActualizacion  = ? ,"
					+ "     iCveUsuGenera    = ? ,"
					+ "     iCveAutoriza     = ? ,"
					+ "     cObservacion     = ?  "
					+ " where iAnio          = ? "
					+ "   and iCveAlmacen    = ? "
					+ "   and iCveInventario = ? " + "";
			pstmt = conn.prepareStatement(cSQL);

			if (VALMInventario.getdtGeneracion() == null)
				pstmt.setNull(1, Types.DATE);
			else
				pstmt.setDate(1, VALMInventario.getdtGeneracion());

			if (VALMInventario.getdtConteo() == null)
				pstmt.setNull(2, Types.DATE);
			else
				pstmt.setDate(2, VALMInventario.getdtConteo());

			if (VALMInventario.getdtActualizacion() == null)
				pstmt.setNull(3, Types.DATE);
			else
				pstmt.setDate(3, VALMInventario.getdtActualizacion());

			if (new Integer(VALMInventario.getiCveUsuGenera()) == null)
				pstmt.setNull(4, Types.INTEGER);
			else
				pstmt.setInt(4, VALMInventario.getiCveUsuGenera());

			if (new Integer(VALMInventario.getiCveAutoriza()) == null)
				pstmt.setNull(5, Types.INTEGER);
			else
				pstmt.setInt(5, VALMInventario.getiCveAutoriza());

			if (VALMInventario.getcObservacion() == null)
				pstmt.setNull(6, Types.VARCHAR);
			else
				pstmt.setString(6, VALMInventario.getcObservacion()
						.toUpperCase().trim());

			pstmt.setInt(7, VALMInventario.getiAnio());
			pstmt.setInt(8, VALMInventario.getiCveAlmacen());
			pstmt.setInt(9, VALMInventario.getiCveInventario());

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

	public Object updCaptura(Connection conGral, Object obj)
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
			TVALMInventario VALMInventario = (TVALMInventario) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMInventario" + " set dtConteo         = ? "
					+ " where iAnio          = ? "
					+ "   and iCveAlmacen    = ? "
					+ "   and iCveInventario = ? " + "";
			pstmt = conn.prepareStatement(cSQL);

			if (VALMInventario.getdtConteo() == null)
				pstmt.setNull(1, Types.DATE);
			else
				pstmt.setDate(1, VALMInventario.getdtConteo());

			pstmt.setInt(2, VALMInventario.getiAnio());
			pstmt.setInt(3, VALMInventario.getiCveAlmacen());
			pstmt.setInt(4, VALMInventario.getiCveInventario());

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

	public Object updAutorizacion(Connection conGral, Object obj)
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
			TVALMInventario VALMInventario = (TVALMInventario) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMInventario" + " set dtActualizacion  = ? ,"
					+ "     iCveAutoriza     = ? "
					+ " where iAnio          = ? "
					+ "   and iCveAlmacen    = ? "
					+ "   and iCveInventario = ? " + "";
			pstmt = conn.prepareStatement(cSQL);

			if (VALMInventario.getdtActualizacion() == null)
				pstmt.setNull(1, Types.DATE);
			else
				pstmt.setDate(1, VALMInventario.getdtActualizacion());

			if (new Integer(VALMInventario.getiCveAutoriza()) == null)
				pstmt.setNull(2, Types.INTEGER);
			else
				pstmt.setInt(2, VALMInventario.getiCveAutoriza());

			pstmt.setInt(3, VALMInventario.getiAnio());
			pstmt.setInt(4, VALMInventario.getiCveAlmacen());
			pstmt.setInt(5, VALMInventario.getiCveInventario());

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
			TVALMInventario vALMInventario = (TVALMInventario) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " delete from ALMInventario " + " where iAnio = ?     "
					+ "   and iCveAlmacen = ?      "
					+ "   and iCveInventario = ?      " + "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMInventario.getiAnio());
			pstmt.setInt(2, vALMInventario.getiCveAlmacen());
			pstmt.setInt(3, vALMInventario.getiCveInventario());
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
				warn("delete.closeALMInventario", ex2);
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
				warn("delete.closeALMInventario", ex2);
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
			TVALMInventario vALMInventario = (TVALMInventario) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " insert into ALMInventario( " + " iAnio, "
					+ " iCveAlmacen, " + " iCveInventario, " + " dtGeneracion,"
					+ " dtConteo," + " dtActualizacion," + " iCveUsuGenera, "
					+ " iCveAutoriza, " + " cObservacion "
					+ " ) values (?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vALMInventario.getiAnio());
			pstmt.setInt(2, vALMInventario.getiCveAlmacen());
			pstmt.setInt(3, vALMInventario.getiCveInventario());

			if (vALMInventario.getdtGeneracion() == null)
				pstmt.setNull(4, Types.DATE);
			else
				pstmt.setDate(4, vALMInventario.getdtGeneracion());

			if (vALMInventario.getdtConteo() == null)
				pstmt.setNull(5, Types.DATE);
			else
				pstmt.setDate(5, vALMInventario.getdtConteo());

			if (vALMInventario.getdtActualizacion() == null)
				pstmt.setNull(6, Types.DATE);
			else
				pstmt.setDate(6, vALMInventario.getdtActualizacion());

			if (new Integer(vALMInventario.getiCveUsuGenera()) == null)
				pstmt.setNull(7, Types.INTEGER);
			else
				pstmt.setInt(7, vALMInventario.getiCveUsuGenera());

			if (new Integer(vALMInventario.getiCveAutoriza()) == null)
				pstmt.setNull(8, Types.INTEGER);
			else
				pstmt.setInt(8, vALMInventario.getiCveAutoriza());

			if (vALMInventario.getcObservacion() == null)
				pstmt.setNull(9, Types.VARCHAR);
			else
				pstmt.setString(9, vALMInventario.getcObservacion()
						.toUpperCase().trim());

			pstmt.executeUpdate();
			cClave = "" + vALMInventario.getiCveInventario();
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