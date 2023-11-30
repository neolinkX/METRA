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
 * Title: Data Acces Object de TOXCalib DAO
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

public class TDTOXCalib extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXCalib() {
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
		Vector vcTOXCalib = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCalib vTOXCalib;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iCveEquipo," + " iCveCalib,"
					+ " iCveLaboratorio," + " iCveParamCalib, " + " dValor "
					+ " from TOXCalib" + cvFiltro + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCalib = new TVTOXCalib();
				vTOXCalib.setiCveEquipo(new Integer(rset.getInt(1)));
				vTOXCalib.setiCveCalib(new Integer(rset.getInt(2)));
				vTOXCalib.setiCveLaboratorio(new Integer(rset.getInt(3)));
				vTOXCalib.setiCveParamCalib(new Integer(rset.getInt(4)));
				vTOXCalib.setdValor(new Double(rset.getInt(5)));
				vcTOXCalib.addElement(vTOXCalib);
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
			return vcTOXCalib;
		}
	}

	public Object insert(Object obj) throws DAOException {
		TVTOXCalib VTOXCalib = (TVTOXCalib) obj;
		TFechas Fecha = new TFechas();
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		String cRegresa = "";
		String cClave = "";
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " insert into TOXCalib ( ";
			lSQL += " iCveEquipo, ";
			lSQL += " iCveCalib, ";
			lSQL += " iCveLaboratorio, ";
			lSQL += " iCveParamCalib, ";
			lSQL += " dValor ";
			lSQL += " ) values (? ,? ,? ,?, ?)";

			lPStmt = conn.prepareStatement(lSQL);

			lPStmt.setInt(1, VTOXCalib.getiCveEquipo().intValue());
			lPStmt.setInt(2, VTOXCalib.getiCveCalib().intValue());
			lPStmt.setInt(3, VTOXCalib.getiCveLaboratorio().intValue());
			lPStmt.setInt(4, VTOXCalib.getiCveParamCalib().intValue());
			lPStmt.setDouble(5, VTOXCalib.getdValor().doubleValue());
			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);
			debug("Se hizo la inserci�n");
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectu� el RollBack: Insert", ex1);
			}
			warn("No se efectu� la Inserci�n", ex);
			ex.printStackTrace();
			throw new DAOException("Error en DAO Claves SAT");
		} finally {
			try {

				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				// fatal(VTOXCalib, "No se efectu� la inserci�n", ex2);
				cRegresa = "";
			}
		}
		return cRegresa;
	}

	public boolean update(Object obj) throws DAOException {
		TVTOXCalib VTOXCalib = (TVTOXCalib) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		TFechas Fecha = new TFechas();
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " update TOXCalib           "
					+ " set dValor = ?            "
					+ " where iCveEquipo = ?      "
					+ "   and iCveCalib = ?       "
					+ "   and iCveLaboratorio = ? "
					+ "   and iCveParamCalib = ?  " + "";

			lPStmt = conn.prepareStatement(lSQL);

			lPStmt.setDouble(1, VTOXCalib.getdValor().doubleValue());
			lPStmt.setInt(2, VTOXCalib.getiCveEquipo().intValue());
			lPStmt.setInt(3, VTOXCalib.getiCveCalib().intValue());
			lPStmt.setInt(4, VTOXCalib.getiCveLaboratorio().intValue());
			lPStmt.setInt(5, VTOXCalib.getiCveParamCalib().intValue());

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);
			debug("Se hizo la inserci�n");

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectu� el RollBack: Update", ex1);
			}
			warn("No se efectu� la Update", ex);
			ex.printStackTrace();
			throw new DAOException("Error en DAO Claves SAT");
		} finally {
			try {

				if (lPStmt != null)
					lPStmt.close();
				if (conn != null)
					conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectu� el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}
}