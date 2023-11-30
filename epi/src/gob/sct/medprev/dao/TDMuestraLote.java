package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de MuestraLote DAO
 * </p>
 * <p>
 * Description: DAO para TOXMuestraLote
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

public class TDMuestraLote extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMuestraLote() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrdenar)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMuestraLote = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMuestraLote vMuestraLote;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveMuestra," + "iCveLaboratorio,"
					+ "iCveLoteCualita" + " from TOXMuestraLote " + cFiltro
					+ cOrdenar;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMuestraLote = new TVMuestraLote();
				vMuestraLote.setIAnio(rset.getInt(1));
				vMuestraLote.setICveMuestra(rset.getInt(2));
				vMuestraLote.setICveLaboratorio(rset.getInt(3));
				vMuestraLote.setICveLoteCualita(rset.getInt(4));
				vcMuestraLote.addElement(vMuestraLote);
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
			return vcMuestraLote;
		}
	}

	/**
	 * Metodo Find By LoteCualita Objetivo: Encontrar todas las muestras que
	 * cumplan con el A�o el Laboratorio y el Lote especificados por el
	 * usuario.
	 */
	public Vector FindByLoteCualita(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMuestraLote = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVMuestraLote vMuestraLote;
			vMuestraLote = (TVMuestraLote) (Obj);
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append("      select L.dtGeneracion, L.iCveLaboratorio, ")
					.append("             ML.icveLoteCualita, ML.iCveMuestra, ")
					.append("             M.iCveUniMed, M.iCveEnvio, ")
					.append("             EL.iOrden, ")
					.append("             U.cDscUniMed ")
					.append("        from TOXLoteCualita L ")
					.append("        inner join TOXMuestraLote ML on ML.iAnio = L.iAnio ")
					.append("                                    and ML.iCveLaboratorio = L.iCveLaboratorio ")
					.append("                                    and ML.iCveLoteCualita = L.iCveLoteCualita ")
					.append("        inner join TOXMuestra M on M.iAnio = ML.iAnio ")
					.append("                               and M.iCveMuestra = ML.iCveMuestra ")
					.append("        inner join TOXEnvioXLote EL on EL.iAnio = ML.iAnio ")
					.append("                                   and EL.iCveLaboratorio = ML.iCveLaboratorio ")
					.append("                                   and EL.iCveLoteCualita = ML.iCveLoteCualita ")
					.append("                                   and EL.iCveUniMed      = M.iCveUniMed ")
					.append("                                   and EL.iCveEnvio       = M.iCveEnvio ")
					.append("        inner join GRLUniMed U on U.iCveUniMed = EL.iCveUniMed ")
					.append("         where L.iAnio = ")
					.append(vMuestraLote.getIAnio())
					.append("           and L.iCveLaboratorio = ")
					.append(vMuestraLote.getICveLaboratorio())
					.append("           and L.iCveLoteCualita = ")
					.append(vMuestraLote.getICveLoteCualita());
			// cSQL.append("    Order by EL.iOrden, ML.iCveMuestra ");
			cSQL.append("    Order by EL.iOrden, M.iOrden ");
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMuestraLote = new TVMuestraLote();
				vMuestraLote.setICveLaboratorio(rset.getInt(2));
				vMuestraLote.setICveMuestra(rset.getInt(4));
				vcMuestraLote.addElement(vMuestraLote);
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
			return vcMuestraLote;
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
			TVMuestraLote vMuestraLote = (TVMuestraLote) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXMuestraLote(" + "iAnio," + "iCveMuestra,"
					+ "iCveLaboratorio," + "iCveLoteCualita"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vMuestraLote.getIAnio());
			pstmt.setInt(2, vMuestraLote.getICveMuestra());
			pstmt.setInt(3, vMuestraLote.getICveLaboratorio());
			pstmt.setInt(4, vMuestraLote.getICveLoteCualita());
			pstmt.executeUpdate();
			cClave = "" + vMuestraLote.getIAnio();
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
			TVMuestraLote vMuestraLote = (TVMuestraLote) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXMuestraLote" + " set iCveLaboratorio= ?, "
					+ "iCveLoteCualita= ? " + "where iAnio = ? "
					+ " and iCveMuestra = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMuestraLote.getICveLaboratorio());
			pstmt.setInt(2, vMuestraLote.getICveLoteCualita());
			pstmt.setInt(3, vMuestraLote.getIAnio());
			pstmt.setInt(4, vMuestraLote.getICveMuestra());
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
			TVMuestraLote vMuestraLote = (TVMuestraLote) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXMuestraLote" + " where iAnio = ?"
					+ " and iCveMuestra = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMuestraLote.getIAnio());
			pstmt.setInt(2, vMuestraLote.getICveMuestra());
			pstmt.executeUpdate();
			cClave = "";
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
				warn("delete.closeMuestraLote", ex2);
			}
			return cClave;
		}
	}
}
