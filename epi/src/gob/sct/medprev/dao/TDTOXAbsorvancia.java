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
 * Title: Data Acces Object de TOXAbsorvancia DAO
 * </p>
 * <p>
 * Description: DAO para manejo de TOX Absorvancia
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */

public class TDTOXAbsorvancia extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXAbsorvancia() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TFechas dtFecha = new TFechas();
		Vector vcTOXAbsorvancia = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cFecha = "";
			TVTOXAbsorvancia vTOXAbsorvancia;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "a.iAnio, "
					+ "a.iCveAbsorvancia, "
					+ "a.iCveEquipo, "
					+ "a.dtEstudio, "
					+ "a.cObservacion, "
					+ "a.iCveUsuResp, "
					+ "cDscEquipo "
					+ "from TOXAbsorvancia a "
					+ "left join  EqmEquipo on EqmEquipo.iCveEquipo = a.iCveEquipo "
					+ cWhere + " order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXAbsorvancia = new TVTOXAbsorvancia();
				vTOXAbsorvancia.setIAnio(rset.getInt(1));
				vTOXAbsorvancia.setICveAbsorvancia(rset.getInt(2));
				vTOXAbsorvancia.setICveEquipo(rset.getInt(3));
				vTOXAbsorvancia.setDtEstudio(rset.getDate(4));
				vTOXAbsorvancia.setCObservacion(rset.getString(5));
				vTOXAbsorvancia.setICveUsuResp(rset.getInt(6));
				vTOXAbsorvancia.setCDscEquipo(rset.getString(7));
				cFecha = dtFecha.getFechaDDMMYYYY(
						vTOXAbsorvancia.getDtEstudio(), "/");
				vTOXAbsorvancia.setCFechaFormat(cFecha);

				vcTOXAbsorvancia.addElement(vTOXAbsorvancia);

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
			return vcTOXAbsorvancia;
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
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVTOXAbsorvancia vTOXAbsorvancia = (TVTOXAbsorvancia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXAbsorvancia(" + "iAnio,"
					+ "iCveAbsorvancia," + "iCveEquipo," + "dtEstudio,"
					+ "cObservacion," + "iCveUsuResp" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA

			String cSQLMax = "select max(iCveAbsorvancia)  "
					+ "From TOXAbsorvancia Where iAnio = "
					+ vTOXAbsorvancia.getIAnio();

			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			if (iMax == 0) {
				iMax = 1;
			} else {
				iMax += 1;
			}
			vTOXAbsorvancia.setICveAbsorvancia(iMax);

			// /////////////////////////////////////////////////
			pstmt.setInt(1, vTOXAbsorvancia.getIAnio());
			pstmt.setInt(2, vTOXAbsorvancia.getICveAbsorvancia());
			pstmt.setInt(3, vTOXAbsorvancia.getICveEquipo());
			pstmt.setDate(4, vTOXAbsorvancia.getDtEstudio());
			pstmt.setString(5, vTOXAbsorvancia.getCObservacion());
			pstmt.setInt(6, vTOXAbsorvancia.getICveUsuResp());
			pstmt.executeUpdate();
			cClave = "" + vTOXAbsorvancia.getICveAbsorvancia();
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
			TVTOXAbsorvancia vTOXAbsorvancia = (TVTOXAbsorvancia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXAbsorvancia" + " set iCveEquipo= ?, "
					+ "dtEstudio= ?, " + "cObservacion= ?, "
					+ "iCveUsuResp= ? " + "where iAnio = ? "
					+ " and iCveAbsorvancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXAbsorvancia.getICveEquipo());
			pstmt.setDate(2, vTOXAbsorvancia.getDtEstudio());
			pstmt.setString(3, vTOXAbsorvancia.getCObservacion());
			pstmt.setInt(4, vTOXAbsorvancia.getICveUsuResp());
			pstmt.setInt(5, vTOXAbsorvancia.getIAnio());
			pstmt.setInt(6, vTOXAbsorvancia.getICveAbsorvancia());
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
			TVTOXAbsorvancia vTOXAbsorvancia = (TVTOXAbsorvancia) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXAbsorvancia" + " where iAnio = ?"
					+ " and iCveAbsorvancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXAbsorvancia.getIAnio());
			pstmt.setInt(2, vTOXAbsorvancia.getICveAbsorvancia());
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
				warn("delete.closeTOXAbsorvancia", ex2);
			}
			return cClave;
		}
	}
}