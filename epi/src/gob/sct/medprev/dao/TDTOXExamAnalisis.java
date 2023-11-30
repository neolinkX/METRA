package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXExamAnalisis DAO
 * </p>
 * <p>
 * Description: DAO para la tabla TOXExamAnalisis
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

public class TDTOXExamAnalisis extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXExamAnalisis() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXExamAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTOXExamAnalisis vTOXExamAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "iCveLaboratorio,iAnio,iCveLoteCualita,iCveExamCualita,iCveAnalisis,"
					+ "iCarrusel,iPosicion "
					+ "from TOXExamAnalisis "
					+ "where iCveLaboratorio=? and iAnio=? and iCveLoteCualita=? and "
					+ "iCveExamCualita=?" + "order by iCveAnalisis";
			pstmt = conn.prepareStatement(cSQL);

			String cTmp = (String) hmFiltro.get("iCveLaboratorio");
			pstmt.setInt(1, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iAnio");
			pstmt.setInt(2, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLoteCualita");
			pstmt.setInt(3, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveExamCualita");
			pstmt.setInt(4, cTmp != null ? Integer.parseInt(cTmp) : 0);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXExamAnalisis = new TVTOXExamAnalisis();
				vTOXExamAnalisis.setICveLaboratorio(rset
						.getInt("iCveLaboratorio"));
				vTOXExamAnalisis.setIAnio(rset.getInt("iAnio"));
				vTOXExamAnalisis.setICveLoteCualita(rset
						.getInt("iCveLoteCualita"));
				vTOXExamAnalisis.setICveExamCualita(rset
						.getInt("iCveExamCualita"));
				vTOXExamAnalisis.setICveAnalisis(rset.getInt("iCveAnalisis"));
				vTOXExamAnalisis.setICarrusel(rset.getInt("iCarrusel"));
				vTOXExamAnalisis.setIPosicion(rset.getInt("iPosicion"));
				vcTOXExamAnalisis.addElement(vTOXExamAnalisis);
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
			return vcTOXExamAnalisis;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXExamAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXExamAnalisis vTOXExamAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveLaboratorio," + "iAnio,"
					+ "iCveLoteCualita," + "iCveExamCualita," + "iCveAnalisis,"
					+ "iCarrusel," + "iPosicion"
					+ " from TOXExamAnalisis order by iCveLaboratorio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXExamAnalisis = new TVTOXExamAnalisis();
				vTOXExamAnalisis.setICveLaboratorio(rset.getInt(1));
				vTOXExamAnalisis.setIAnio(rset.getInt(2));
				vTOXExamAnalisis.setICveLoteCualita(rset.getInt(3));
				vTOXExamAnalisis.setICveExamCualita(rset.getInt(4));
				vTOXExamAnalisis.setICveAnalisis(rset.getInt(5));
				vTOXExamAnalisis.setICarrusel(rset.getInt(6));
				vTOXExamAnalisis.setIPosicion(rset.getInt(7));
				vcTOXExamAnalisis.addElement(vTOXExamAnalisis);
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
			return vcTOXExamAnalisis;
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
			TVTOXExamAnalisis vTOXExamAnalisis = (TVTOXExamAnalisis) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXExamAnalisis(" + "iCveLaboratorio,"
					+ "iAnio," + "iCveLoteCualita," + "iCveExamCualita,"
					+ "iCveAnalisis," + "iCarrusel," + "iPosicion"
					+ ")values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vTOXExamAnalisis.getICveLaboratorio());
			pstmt.setInt(2, vTOXExamAnalisis.getIAnio());
			pstmt.setInt(3, vTOXExamAnalisis.getICveLoteCualita());
			pstmt.setInt(4, vTOXExamAnalisis.getICveExamCualita());
			pstmt.setInt(5, vTOXExamAnalisis.getICveAnalisis());
			pstmt.setInt(6, vTOXExamAnalisis.getICarrusel());
			pstmt.setInt(7, vTOXExamAnalisis.getIPosicion());
			pstmt.executeUpdate();
			cClave = "" + vTOXExamAnalisis.getICveLaboratorio();
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
			TVTOXExamAnalisis vTOXExamAnalisis = (TVTOXExamAnalisis) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXExamAnalisis" + " set iCarrusel= ?, "
					+ "iPosicion= ? " + "where iCveLaboratorio = ? "
					+ "and iAnio = ?" + "and iCveLoteCualita = ?"
					+ "and iCveExamCualita = ?" + " and iCveAnalisis = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXExamAnalisis.getICarrusel());
			pstmt.setInt(2, vTOXExamAnalisis.getIPosicion());
			pstmt.setInt(3, vTOXExamAnalisis.getICveLaboratorio());
			pstmt.setInt(4, vTOXExamAnalisis.getIAnio());
			pstmt.setInt(5, vTOXExamAnalisis.getICveLoteCualita());
			pstmt.setInt(6, vTOXExamAnalisis.getICveExamCualita());
			pstmt.setInt(7, vTOXExamAnalisis.getICveAnalisis());
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
			TVTOXExamAnalisis vTOXExamAnalisis = (TVTOXExamAnalisis) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXExamAnalisis" + " where iCveLaboratorio = ?"
					+ " and iAnio = ?" + " and iCveLoteCualita = ?"
					+ " and iCveExamCualita = ?" + " and iCveAnalisis = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXExamAnalisis.getICveLaboratorio());
			pstmt.setInt(2, vTOXExamAnalisis.getIAnio());
			pstmt.setInt(3, vTOXExamAnalisis.getICveLoteCualita());
			pstmt.setInt(4, vTOXExamAnalisis.getICveExamCualita());
			pstmt.setInt(5, vTOXExamAnalisis.getICveAnalisis());
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
				warn("delete.closeTOXExamAnalisis", ex2);
			}
			return cClave;
		}
	}
}