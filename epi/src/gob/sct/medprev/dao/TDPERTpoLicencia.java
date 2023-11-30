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
 * Title: Data Acces Object de PERTpoLicencia DAO
 * </p>
 * <p>
 * Description: DAO Tabal PerTpoLicencia
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonz�lez Paz
 * @version 1.0
 */

public class TDPERTpoLicencia extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERTpoLicencia() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERTpoLicencia = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERTpoLicencia vPERTpoLicencia;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePersonal," + "iCveMdoTrans,"
					+ "iCveCategoria," + "cLicencia," + "lDictamen"
					+ " from PERTpoLicencia order by iCvePersonal";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERTpoLicencia = new TVPERTpoLicencia();
				vPERTpoLicencia.setICvePersonal(rset.getInt(1));
				vPERTpoLicencia.setICveMdoTrans(rset.getInt(2));
				vPERTpoLicencia.setICveCategoria(rset.getInt(3));
				vPERTpoLicencia.setCLicencia(rset.getString(4));
				vPERTpoLicencia.setLDictamen(rset.getInt(5));
				vcPERTpoLicencia.addElement(vPERTpoLicencia);
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
			return vcPERTpoLicencia;
		}
	}

	/**
	 * Metodo Find By PersonaMdoTrans
	 * 
	 * @author Marco A. Gonz�lez Paz
	 * @parameter cWhere Recibe como cadena la condicion de busqueda por Persona
	 *            , Modo de Transporte y Categor�a
	 */
	public Vector FindByPersonaMdoTrans(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERTpoLicencia = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERTpoLicencia vPERTpoLicencia;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePersonal," + "iCveMdoTrans,"
					+ "iCveCategoria," + "cLicencia," + "lDictamen"
					+ " from PERTpoLicencia ";

			cSQL = cSQL + cWhere;
			cSQL = cSQL + " order by iCvePersonal ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERTpoLicencia = new TVPERTpoLicencia();
				vPERTpoLicencia.setICvePersonal(rset.getInt(1));
				vPERTpoLicencia.setICveMdoTrans(rset.getInt(2));
				vPERTpoLicencia.setICveCategoria(rset.getInt(3));
				vPERTpoLicencia.setCLicencia(rset.getString(4));
				vPERTpoLicencia.setLDictamen(rset.getInt(5));
				vcPERTpoLicencia.addElement(vPERTpoLicencia);
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
			return vcPERTpoLicencia;
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
			TVPERTpoLicencia vPERTpoLicencia = (TVPERTpoLicencia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into PERTpoLicencia(" + "iCvePersonal,"
					+ "iCveMdoTrans," + "iCveCategoria," + "cLicencia,"
					+ "lDictamen" + ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vPERTpoLicencia.getICvePersonal());
			pstmt.setInt(2, vPERTpoLicencia.getICveMdoTrans());
			pstmt.setInt(3, vPERTpoLicencia.getICveCategoria());
			pstmt.setString(4, vPERTpoLicencia.getCLicencia());
			pstmt.setInt(5, vPERTpoLicencia.getLDictamen());
			pstmt.executeUpdate();
			cClave = "" + vPERTpoLicencia.getICvePersonal();
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
	 * Metodo Insert
	 */
	public int insert(Connection conGral, Vector vcPERTpoLicencia)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "insert into PERTpoLicencia ( "
					+ "iCvePersonal,iCveMdoTrans,iCveCategoria,cLicencia,lDictamen ) "
					+ "values (?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			for (Enumeration ePERTpoLicencia = vcPERTpoLicencia.elements(); ePERTpoLicencia
					.hasMoreElements();) {
				TVPERTpoLicencia vPERTpoLicencia = (TVPERTpoLicencia) ePERTpoLicencia
						.nextElement();
				pstmt.setInt(1, vPERTpoLicencia.getICvePersonal());
				pstmt.setInt(2, vPERTpoLicencia.getICveMdoTrans());
				pstmt.setInt(3, vPERTpoLicencia.getICveCategoria());
				pstmt.setString(4, vPERTpoLicencia.getCLicencia());
				pstmt.setInt(5, vPERTpoLicencia.getLDictamen());
				try {
					iCta += pstmt.executeUpdate();
				} catch (SQLException ex) {
					// 0xFFFFFCDD (-803) significa que intentamos insertar un
					// datos ya existente
					if (ex.getErrorCode() != -803)
						throw ex;
				}
			}
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
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
			TVPERTpoLicencia vPERTpoLicencia = (TVPERTpoLicencia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update PERTpoLicencia" + " set cLicencia= ?, "
					+ "lDictamen= ? " + "where iCvePersonal = ? "
					+ "and iCveMdoTrans = ?" + " and iCveCategoria = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vPERTpoLicencia.getCLicencia());
			pstmt.setInt(2, vPERTpoLicencia.getLDictamen());
			pstmt.setInt(3, vPERTpoLicencia.getICvePersonal());
			pstmt.setInt(4, vPERTpoLicencia.getICveMdoTrans());
			pstmt.setInt(5, vPERTpoLicencia.getICveCategoria());
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
			TVPERTpoLicencia vPERTpoLicencia = (TVPERTpoLicencia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERTpoLicencia" + " where iCvePersonal = ?"
					+ " and iCveMdoTrans = ?" + " and iCveCategoria = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERTpoLicencia.getICvePersonal());
			pstmt.setInt(2, vPERTpoLicencia.getICveMdoTrans());
			pstmt.setInt(3, vPERTpoLicencia.getICveCategoria());
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
				warn("delete.closePERTpoLicencia", ex2);
			}
			return cClave;
		}
	}
}