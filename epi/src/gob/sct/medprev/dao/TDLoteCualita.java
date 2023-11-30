package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de LoteCualita DAO
 * </p>
 * <p>
 * Description: DOA para TOXLoteCualita
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

public class TDLoteCualita extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDLoteCualita() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcLoteCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVLoteCualita vLoteCualita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveLaboratorio,"
					+ "iCveLoteCualita," + "dtGeneracion"
					+ " from TOXLoteCualita order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vLoteCualita = new TVLoteCualita();
				vLoteCualita.setIAnio(rset.getInt(1));
				vLoteCualita.setICveLaboratorio(rset.getInt(2));
				vLoteCualita.setICveLoteCualita(rset.getInt(3));
				vLoteCualita.setDtGeneracion(rset.getDate(4));
				vcLoteCualita.addElement(vLoteCualita);
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
			return vcLoteCualita;
		}
	}

	/**
	 * Importante: Metodo Find By All Muestra los Lotes que en
	 * toxlotecualita.dtgeneracion is null
	 */
	public Vector FindAllLoteCualita(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcLoteCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVLoteCualita vLoteCualita = (TVLoteCualita) (Obj);

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select icvelotecualita from toxlotecualita "
					+ " where iAnio = " + vLoteCualita.getIAnio()
					+ " and iCveLaboratorio = "
					+ vLoteCualita.getICveLaboratorio()
					+ " and toxlotecualita.dtgeneracion is null ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vLoteCualita = new TVLoteCualita();
				vLoteCualita.setICveLoteCualita(rset.getInt(1));
				vcLoteCualita.addElement(vLoteCualita);
			}
		} catch (Exception ex) {
			warn("FindAllLoteCualita", ex);
			throw new DAOException("FindAllLoteCualita");
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
				warn("FindAllLoteCualita.close", ex2);
			}
			return vcLoteCualita;
		}
	}

	/**
	 * Importante: Metodo Find By All Muestra los Lotes que en
	 * toxlotecualita.dtgeneracion IS NOT NULL
	 */
	public Vector FindAllLoteCualita2(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcLoteCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVLoteCualita vLoteCualita = (TVLoteCualita) (Obj);

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select icvelotecualita from toxlotecualita "
					+ " where iAnio = " + vLoteCualita.getIAnio()
					+ " and iCveLaboratorio = "
					+ vLoteCualita.getICveLaboratorio()
					+ " and toxlotecualita.dtgeneracion is not null "
					+ " order by iAnio, iCveLaboratorio, iCveLotecualita desc";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vLoteCualita = new TVLoteCualita();
				vLoteCualita.setICveLoteCualita(rset.getInt(1));
				vcLoteCualita.addElement(vLoteCualita);
			}
		} catch (Exception ex) {
			warn("FindAllLoteCualita", ex);
			throw new DAOException("FindAllLoteCualita");
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
				warn("FindAllLoteCualita.close", ex2);
			}
			return vcLoteCualita;
		}
	}

	/**
	 * Metodo Find Last
	 */
	public int FindLastLoteCualita(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcLoteCualita = new Vector();
		int iCveLoteCualita = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVLoteCualita vLoteCualita;
			vLoteCualita = (TVLoteCualita) (Obj);
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select Max(iCveLoteCualita) from TOXLoteCualita "
					+ "where iAnio = " + vLoteCualita.getIAnio()
					+ " and iCveLaboratorio = "
					+ vLoteCualita.getICveLaboratorio();
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				iCveLoteCualita = rset.getInt(1);
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
			return iCveLoteCualita;
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
			TVLoteCualita vLoteCualita = (TVLoteCualita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXLoteCualita(" + "iAnio,"
					+ "iCveLaboratorio," + "iCveLoteCualita," + "dtGeneracion"
					+ ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vLoteCualita.getIAnio());
			pstmt.setInt(2, vLoteCualita.getICveLaboratorio());
			pstmt.setInt(3, vLoteCualita.getICveLoteCualita());
			pstmt.setDate(4, vLoteCualita.getDtGeneracion());
			pstmt.executeUpdate();
			cClave = "" + vLoteCualita.getIAnio();
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
			TVLoteCualita vLoteCualita = (TVLoteCualita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update TOXLoteCualita" + " set dtGeneracion= ? "
					+ " where iAnio = ? " + "   and iCveLaboratorio = ? "
					+ "   and iCveLoteCualita= ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vLoteCualita.getDtGeneracion());
			pstmt.setInt(2, vLoteCualita.getIAnio());
			pstmt.setInt(3, vLoteCualita.getICveLaboratorio());
			pstmt.setInt(4, vLoteCualita.getICveLoteCualita());
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
			TVLoteCualita vLoteCualita = (TVLoteCualita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXLoteCualita" + " where iAnio = ?"
					+ " and iCveLaboratorio = ?" + " and iCveLoteCualita = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vLoteCualita.getIAnio());
			pstmt.setInt(2, vLoteCualita.getICveLaboratorio());
			pstmt.setInt(3, vLoteCualita.getICveLoteCualita());
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
				warn("delete.closeLoteCualita", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo DetalleLote
	 * 
	 * @param cvFiltro
	 *            Valor del Filtro a Aplicar en la Extracci�n de los Datos
	 * @return Value Object de los Lotes Cualitativos
	 * @throws DAOException
	 */
	public Vector DetalleLote(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector VResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXLoteCuantDetalle VDetalle;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append("").append(cvFiltro);
			int count;
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				VDetalle = new TVTOXLoteCuantDetalle();
				VResultado.addElement(VDetalle);
			}
		} catch (Exception ex) {
			warn("DetalleLote", ex);
			throw new DAOException("DetalleLote");
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
				warn("DetalleLote.close", ex2);
			}
			return VResultado;
		}
	}

}
