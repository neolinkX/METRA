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
 * Title: Data Acces Object de ALMDtlleInve DAO
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

public class TDALMDtlleInve extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMDtlleInve() {
	}

	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMDtlleInve = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMDtlleInve vALMDtlleInve;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iAnio,  " + " iCveAlmacen,  "
					+ " iCveInventario,  " + " iCveArticulo,  "
					+ " iCveLote,  " + " dELogica,  " + " dEFisica  "
					+ " from ALMDtlleInve " + cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMDtlleInve = new TVALMDtlleInve();
				vALMDtlleInve.setiAnio(rset.getInt(1));
				vALMDtlleInve.setiCveAlmacen(rset.getInt(2));
				vALMDtlleInve.setiCveInventario(rset.getInt(3));
				vALMDtlleInve.setiCveArticulo(rset.getInt(4));
				vALMDtlleInve.setiCveLote(rset.getInt(5));
				vALMDtlleInve.setdELogica(rset.getDouble(6));
				vALMDtlleInve.setdEFisica(rset.getDouble(7));
				vcALMDtlleInve.addElement(vALMDtlleInve);
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
			return vcALMDtlleInve;
		}
	}

	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMDtlleInve = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMDtlleInve vALMDtlleInve;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " ALMDtlleInve.iAnio,  "
					+ " ALMDtlleInve.iCveAlmacen,  "
					+ " ALMDtlleInve.iCveInventario,  "
					+ " ALMDtlleInve.iCveArticulo, "
					+ " ALMDtlleInve.iCveLote " + " from ALMDtlleInve "
					+ cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMDtlleInve = new TVALMDtlleInve();
				vALMDtlleInve.setiAnio(rset.getInt(1));
				vALMDtlleInve.setiCveAlmacen(rset.getInt(2));
				vALMDtlleInve.setiCveInventario(rset.getInt(3));
				vALMDtlleInve.setiCveArticulo(rset.getInt(4));
				vALMDtlleInve.setiCveLote(rset.getInt(5));
				vcALMDtlleInve.addElement(vALMDtlleInve);
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
			return vcALMDtlleInve;
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
			TVALMDtlleInve VALMDtlleInve = (TVALMDtlleInve) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMDtlleInve" + " set dELogica = ? ,"
					+ "     dEFisica = ? " + " where iAnio = ? "
					+ "   and iCveAlmacen = ? " + "   and iCveInventario = ? "
					+ "   and iCveArticulo = ? " + "   and iCveLote = ? " + "";
			pstmt = conn.prepareStatement(cSQL);

			if (new Double(VALMDtlleInve.getdELogica()) == null)
				pstmt.setNull(1, Types.DOUBLE);
			else
				pstmt.setDouble(1, VALMDtlleInve.getdELogica());

			if (new Double(VALMDtlleInve.getdEFisica()) == null)
				pstmt.setNull(2, Types.DOUBLE);
			else
				pstmt.setDouble(2, VALMDtlleInve.getdEFisica());

			pstmt.setInt(3, VALMDtlleInve.getiAnio());
			pstmt.setInt(4, VALMDtlleInve.getiCveAlmacen());
			pstmt.setInt(5, VALMDtlleInve.getiCveInventario());
			pstmt.setInt(6, VALMDtlleInve.getiCveArticulo());
			pstmt.setInt(7, VALMDtlleInve.getiCveLote());

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
			TVALMDtlleInve vALMDtlleInve = (TVALMDtlleInve) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " delete from ALMDtlleInve " + " where iAnio = ?     "
					+ "   and iCveAlmacen = ?      "
					+ "   and iCveInventario = ?      "
					+ "   and iCveArticulo = ?      " + "   and iCveLote = ? "
					+ "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMDtlleInve.getiAnio());
			pstmt.setInt(2, vALMDtlleInve.getiCveAlmacen());
			pstmt.setInt(3, vALMDtlleInve.getiCveInventario());
			pstmt.setInt(4, vALMDtlleInve.getiCveArticulo());
			pstmt.setInt(5, vALMDtlleInve.getiCveLote());
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
				warn("delete.closeALMDtlleInve", ex2);
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
				warn("delete.closeALMDtlleInve", ex2);
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
			TVALMDtlleInve vALMDtlleInve = (TVALMDtlleInve) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " insert into ALMDtlleInve( " + " iAnio, "
					+ " iCveAlmacen, " + " iCveInventario, "
					+ " iCveArticulo, " + " iCveLote, " + " dELogica, "
					+ " dEFisica " + " ) values (?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vALMDtlleInve.getiAnio());
			pstmt.setInt(2, vALMDtlleInve.getiCveAlmacen());
			pstmt.setInt(3, vALMDtlleInve.getiCveInventario());
			pstmt.setInt(4, vALMDtlleInve.getiCveArticulo());
			pstmt.setInt(5, vALMDtlleInve.getiCveLote());

			if (new Double(vALMDtlleInve.getdELogica()) == null)
				pstmt.setNull(6, Types.FLOAT);
			else
				pstmt.setDouble(6, vALMDtlleInve.getdELogica());

			if (new Double(vALMDtlleInve.getdEFisica()) == null)
				pstmt.setNull(7, Types.FLOAT);
			else
				pstmt.setDouble(7, vALMDtlleInve.getdEFisica());

			pstmt.executeUpdate();
			cClave = "" + vALMDtlleInve.getiCveAlmacen();
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