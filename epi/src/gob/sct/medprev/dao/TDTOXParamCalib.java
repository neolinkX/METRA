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
 * Title: Data Acces Object de TOXParamCalib DAO
 * </p>
 * <p>
 * Description: Lotes Cuantitativos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrej�n Adame.
 * @version 1.0
 */

public class TDTOXParamCalib extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXParamCalib() {
	}

	/**
	 * Metodo Find By All
	 * 
	 * @param cvFiltro
	 *            Valor del Filtro a Aplicar en la Extracci�n de los Datos
	 * @return Value Object de los Resultados de los Examenes.
	 * @throws DAOException
	 */
	public Vector FindByAll(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXParamCalib = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXParamCalib vTOXParamCalib;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iCveLaboratorio," + " iCveParamCalib,"
					+ " cDscParam," + " lActivo, " + " dValorMin "
					+ " , dValorMax " + " from TOXParamCalib" + cvFiltro + " ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXParamCalib = new TVTOXParamCalib();
				vTOXParamCalib.setiCveLaboratorio(new Integer(rset.getInt(1)));
				vTOXParamCalib.setiCveParamCalib(new Integer(rset.getInt(2)));
				vTOXParamCalib.setcDscParam(rset.getString(3));
				vTOXParamCalib.setlActivo(new Integer(rset.getInt(4)));
				vTOXParamCalib.setdValorMin(new Double(rset.getDouble(5)));
				vTOXParamCalib.setDValorMax(new Double(rset.getDouble(6)));
				vcTOXParamCalib.addElement(vTOXParamCalib);
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
			return vcTOXParamCalib;
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
			TVTOXParamCalib vParam = (TVTOXParamCalib) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXParamCalib(" + " iCveLaboratorio,"
					+ " iCveParamCalib, " + " cDscParam, " + " lActivo,   "
					+ " dValorMin   " + ", dValorMax   " + ")values(?,?,?,?,? "
					+ ",?" + ")";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cNewClave = "SELECT MAX(iCveParamCalib) FROM TOXParamCalib where iCveLaboratorio = "
					+ vParam.getiCveLaboratorio().toString();
			PreparedStatement psNewCve = conn.prepareStatement(cNewClave);
			ResultSet rsNewClave = psNewCve.executeQuery();
			int newCve = 0;
			if (rsNewClave.next()) {
				newCve = rsNewClave.getInt(1) + 1;
			}
			rsNewClave.close();
			psNewCve.close();
			// Asignar valores
			pstmt.setInt(1, vParam.getiCveLaboratorio().intValue());
			pstmt.setInt(2, newCve);
			pstmt.setString(3, vParam.getcDscParam());
			pstmt.setInt(4, vParam.getlActivo().intValue());
			pstmt.setDouble(5, vParam.getdValorMin().doubleValue());
			pstmt.setDouble(6, vParam.getDValorMax().doubleValue());
			pstmt.executeUpdate();
			cClave = "" + newCve;
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
			TVTOXParamCalib vParam = (TVTOXParamCalib) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update TOXParamCalib" + "    set cDscParam  = ?, "
					+ "        lActivo   = ?, " + "        dValorMin = ?  "
					+ "       ,dValorMax = ?  "
					+ //
					" where iCveLaboratorio  = ?"
					+ "   and iCveParamCalib = ? ";
			pstmt = conn.prepareStatement(cSQL);
			// Asignar valores
			pstmt.setString(1, vParam.getcDscParam());
			pstmt.setInt(2, vParam.getlActivo().intValue());
			pstmt.setDouble(3, vParam.getdValorMin().doubleValue());
			pstmt.setDouble(4, vParam.getDValorMax().doubleValue());
			pstmt.setInt(5, vParam.getiCveLaboratorio().intValue());
			pstmt.setInt(6, vParam.getiCveParamCalib().intValue());

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
	 * Metodo update
	 */
	public Object deshabilitar(Connection conGral, Object obj)
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
			TVTOXParamCalib vParam = (TVTOXParamCalib) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update TOXParamCalib" + "    set lActivo   = ? "
					+ " where iCveLaboratorio  = ?"
					+ "   and iCveParamCalib = ? ";
			pstmt = conn.prepareStatement(cSQL);
			// Asignar valores
			pstmt.setInt(1, vParam.getlActivo().intValue());
			pstmt.setInt(2, vParam.getiCveLaboratorio().intValue());
			pstmt.setInt(3, vParam.getiCveParamCalib().intValue());

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
				warn("deshabilitar", ex1);
			}
			warn("deshabilitar", ex);
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
				warn("deshabilitar.close", ex2);
			}
			return cClave;
		}
	}

}
