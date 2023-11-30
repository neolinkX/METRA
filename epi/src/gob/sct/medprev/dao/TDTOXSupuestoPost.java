package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de SupuestoPost DAO
 * </p>
 * <p>
 * Description: DAO para el control de la Informaci�n de la tabla
 * TOXSupuestoPost
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

public class TDTOXSupuestoPost extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXSupuestoPost() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcSupuestoPost = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVSupuestoPost vSupuestoPost;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCvePbaRapida," + "iCveSustancia,"
					+ "lResultado" + " from TOXSupuestoPost " + cFiltro + " "
					+ cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vSupuestoPost = new TVSupuestoPost();
				vSupuestoPost.setIAnio(rset.getInt(1));
				vSupuestoPost.setICvePbaRapida(rset.getInt(2));
				vSupuestoPost.setICveSustancia(rset.getInt(3));
				vSupuestoPost.setLResultado(rset.getInt(4));
				vcSupuestoPost.addElement(vSupuestoPost);
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
			return vcSupuestoPost;
		}
	}

	/**
	 * 
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
			TVSupuestoPost vSupuestoPost = (TVSupuestoPost) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXSupuestoPost (" + "iAnio,"
					+ "iCvePbaRapida," + "iCveSustancia," + "lResultado"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vSupuestoPost.getIAnio());
			pstmt.setInt(2, vSupuestoPost.getICvePbaRapida());
			pstmt.setInt(3, vSupuestoPost.getICveSustancia());
			pstmt.setInt(4, vSupuestoPost.getLResultado());
			pstmt.executeUpdate();
			cClave = "" + vSupuestoPost.getIAnio();
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
			TVSupuestoPost vSupuestoPost = (TVSupuestoPost) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXSupuestoPost " + " set lResultado= ?, "
					+ "where iAnio = ? " + "and iCvePbaRapida = ?"
					+ " and iCveSustancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vSupuestoPost.getLResultado());
			pstmt.setInt(2, vSupuestoPost.getIAnio());
			pstmt.setInt(3, vSupuestoPost.getICvePbaRapida());
			pstmt.setInt(4, vSupuestoPost.getICveSustancia());
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
			TVSupuestoPost vSupuestoPost = (TVSupuestoPost) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXSupuestoPost " + " where iAnio = ?"
					+ " and iCvePbaRapida = ?" + " and iCveSustancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vSupuestoPost.getIAnio());
			pstmt.setInt(2, vSupuestoPost.getICvePbaRapida());
			pstmt.setInt(3, vSupuestoPost.getICveSustancia());
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
				warn("delete.closeSupuestoPost ", ex2);
			}
			return cClave;
		}
	}
}
