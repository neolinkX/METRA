package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXEnvioXLote DAO
 * </p>
 * <p>
 * Description: DOA para TDTOXEnvioXLote
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

public class TDTOXEnvioXLote extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXEnvioXLote() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector VResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXEnvioXLote vEnvXLote;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append("select U.cDscUniMed,")
					.append("  EL.iAnio,")
					.append("  EL.iCveLaboratorio,")
					.append("  EL.iCveLoteCualita,")
					.append("  EL.iCveUniMed, ")
					.append("  EL.iCveEnvio, ")
					.append("  EL.iOrden ")
					.append("      from TOXEnvioXLote EL ")
					.append("      inner join GRLUniMed U on U.iCveUniMed = EL.iCveUniMed ")
					.append(cFiltro)
					.append("      Order by EL.iAnio, EL.iCveLaboratorio, EL.iCveLoteCualita, EL.iOrden");
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEnvXLote = new TVTOXEnvioXLote();
				vEnvXLote.VEnvio.setCDscUniMed(rset.getString(1));
				vEnvXLote.VLote.setIAnio(rset.getInt(2));
				vEnvXLote.VLote.setICveLaboratorio(rset.getInt(3));
				vEnvXLote.VLote.setICveLoteCualita(rset.getInt(4));
				vEnvXLote.VEnvio.setICveUniMed(rset.getInt(5));
				vEnvXLote.VEnvio.setICveEnvio(rset.getInt(6));
				vEnvXLote.setIOrden(rset.getInt(7));
				VResultado.addElement(vEnvXLote);
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
			return VResultado;
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
			StringBuffer cSQL = new StringBuffer();
			TVTOXEnvioXLote VEnvXLote = (TVTOXEnvioXLote) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append("insert into TOXEnvioXLote(").append(" iAnio,")
					.append(" iCveLaboratorio,").append(" iCveLoteCualita,")
					.append(" iCveUniMed, ").append(" iCveEnvio, ")
					.append(" iOrden ").append(" )values(?,?,?,?,?,?)");
			pstmt = conn.prepareStatement(cSQL.toString());
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, VEnvXLote.VLote.getIAnio());
			pstmt.setInt(2, VEnvXLote.VLote.getICveLaboratorio());
			pstmt.setInt(3, VEnvXLote.VLote.getICveLoteCualita());
			pstmt.setInt(4, VEnvXLote.VEnvio.getICveUniMed());
			pstmt.setInt(5, VEnvXLote.VEnvio.getICveEnvio());
			pstmt.setInt(6, VEnvXLote.getIOrden());
			pstmt.executeUpdate();
			cClave = "" + VEnvXLote.VLote.getIAnio();
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

	/**
	 * Metodo Delete
	 */
	public Object delete(Connection conGral, Object cFiltro)
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
			StringBuffer cSQL = new StringBuffer();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append("delete from TOXEnvioXLote L ")
					.append((String) cFiltro);
			pstmt = conn.prepareStatement(cSQL.toString());
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
				warn("delete.closeLoteCualita", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindEnvXLote(Object cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector VLote = new Vector();
		StringBuffer cSQL = new StringBuffer();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTOXEnvio VEnvio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select M.iAnio, M.iCveUniMed, M.iCveEnvio ")
					.append("        from TOXMuestraLote L ")
					.append("        inner join TOXMuestra M on M.iAnio       = L.iAnio ")
					.append("                               and M.iCveMuestra = L.iCveMuestra ")
					.append((String) cFiltro)
					.append(" group by M.iAnio, M.iCveUniMed, M.iCveEnvio ");
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				VEnvio = new TVTOXEnvio();
				VEnvio.setIAnio(rset.getInt(1));
				VEnvio.setICveUniMed(rset.getInt(2));
				VEnvio.setICveEnvio(rset.getInt(3));
				VLote.add(VEnvio);
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
			return VLote;
		}
	}

}
