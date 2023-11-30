package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;

import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.dao.*;

/**
 * <p>
 * Title: Data Acces Object de EXPServicio DAO
 * </p>   
 * <p>
 * Description: EXPServicio
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

public class TDEXPServicio extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private int iInserted; // Agregado por Rafael Alcocer Caldera 08/Nov/2006
	private int iUpdated; // Agregado por Rafael Alcocer Caldera 08/Nov/2006

	public TDEXPServicio() {
	}

	/**
	 * Método Find By All
	 */
	public Vector FindByAll() throws DAOException {
		return FindByAll(new HashMap());
	}

	/**
	 * Método Find By All
	 */
	public Vector FindByAll(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "a.iCveExpediente,a.iNumExamen,a.iCveServicio,a.iOrden,a.cNotaMedica,"
					+ "a.iCveUsuNotaMed,a.lConcluido,a.dtInicio,a.dtFin,a.iCveUsuAplica,"
					+ "b.cDscServicio " + ",a.tsInicio,a.tsFin "
					+ "from EXPServicio a inner join MEDServicio b on ("
					+ "a.iCveServicio=b.iCveServicio)";
			String cDiagnostico = (String) hmFiltros.get("lDiagnostico");
			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cCveServicio = (String) hmFiltros.get("iCveServicio");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");
			if (cDiagnostico != null) {
				cSQL += " where b.lDiagnostico=?";
				cWhereAnd = " and";
			}
			if (cCveExpediente != null) {
				cSQL += cWhereAnd + " a.iCveExpediente=?";
				cWhereAnd = " and";
			}
			if (cCveServicio != null) {
				cSQL += cWhereAnd + " a.iCveServicio=?";
				cWhereAnd = " and";
			}
			if (cNumExamen != null) {
				cSQL += cWhereAnd + " a.iNumExamen=?";
			}
			cSQL += " order by a.iCveExpediente,a.iNumExamen,a.iCveServicio";

			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cDiagnostico != null)
				pstmt.setInt(i++, Integer.parseInt(cDiagnostico));
			if (cCveExpediente != null)
				pstmt.setInt(i++, Integer.parseInt(cCveExpediente));
			if (cCveServicio != null)
				pstmt.setInt(i++, Integer.parseInt(cCveServicio));
			if (cNumExamen != null)
				pstmt.setInt(i++, Integer.parseInt(cNumExamen));

			// System.out.println("query =- \n "+cDiagnostico+"\n"+cCveExpediente+"\n"+cCveServicio+"\n"+cNumExamen+"\n"+cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setICveExpediente(rset.getInt("iCveExpediente"));
				vEXPServicio.setINumExamen(rset.getInt("iNumExamen"));
				vEXPServicio.setICveServicio(rset.getInt("iCveServicio"));
				vEXPServicio.setIOrden(rset.getInt("iOrden"));
				vEXPServicio.setCNotaMedica(rset.getString("cNotaMedica"));
				vEXPServicio.setICveUsuNotaMed(rset.getInt("iCveUsuNotaMed"));
				vEXPServicio.setLConcluido(rset.getInt("lConcluido"));
				vEXPServicio.setDtInicio(rset.getDate("dtInicio"));
				vEXPServicio.setDtFin(rset.getDate("dtFin"));
				vEXPServicio.setICveUsuAplica(rset.getInt("iCveUsuAplica"));
				vEXPServicio.setCDscServicio(rset.getString("cDscServicio"));
				vEXPServicio.setTsInicio(rset.getTimestamp(12));
				vEXPServicio.setTsFin(rset.getTimestamp(13));
				vcEXPServicio.addElement(vEXPServicio);
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcEXPServicio;
	}

	/**
	 * Método FindUserXServ
	 */
	public Vector FindUserXServ(String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		String cClave = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPServicio vEXPServicio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			// Calcula Fecha Actual
			java.sql.Date dtCampo;
			TFechas tfCampo = new TFechas();
			java.util.Date today = new java.util.Date();
			java.sql.Date defFecha = new java.sql.Date(today.getTime());
			java.sql.Date defaultFecha = new java.sql.Date(today.getTime());

			cSQL = "select "
					+ "distinct(EXPServicio.iCveExpediente), "
					+ "PERDatos.cApPaterno, "
					+ "PERDatos.cApMaterno, "
					+ "PERDatos.cNombre, "
					+ "PERDatos.cRFC "
					+ ",EXPServicio.tsInicio,EXPServicio.tsFin "
					+ "from EXPServicio "
					+ "join PERDatos on PERDatos.iCveExpediente = EXPServicio.iCveExpediente "
					+ "where EXPServicio.iCveServicio = "
					+ cServicio
					+ " and EXPServicio.lConcluido = 0 "
					+ " and EXPServicio.dtInicio = '"
					+ defaultFecha
					+ "'"
					+ " order by PERDatos.cApPaterno, PERDatos.cApMaterno, PERDatos.cNombre";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setICveExpediente(rset.getInt(1));
				vEXPServicio.setCApPaterno(rset.getString(2));
				vEXPServicio.setCApMaterno(rset.getString(3));
				vEXPServicio.setCNombre(rset.getString(4));
				vEXPServicio.setCRFC(rset.getString(5));
				vEXPServicio.setTsInicio(rset.getTimestamp(6));
				vEXPServicio.setTsFin(rset.getTimestamp(7));

				vcEXPServicio.addElement(vEXPServicio);
			}
		} catch (Exception ex) {
			warn("FindUserXServ", ex);
			throw new DAOException("FindUserXServ");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindUserXServ.close", ex2);
			}
			return vcEXPServicio;
		}
	}

	/**
	 * Método Find By All
	 */
	public Vector findByPK(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "a.iCveExpediente,a.iNumExamen,a.iCveServicio,a.iOrden,a.cNotaMedica,"
					+ "a.iCveUsuNotaMed,a.lConcluido,a.dtInicio,a.dtFin,a.iCveUsuAplica "
					+ ",a.tsInicio,a.tsFin " + "from EXPServicio a";
			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cCveServicio = (String) hmFiltros.get("iCveServicio");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");
			if (cCveExpediente != null) {
				cSQL += cWhereAnd + " a.iCveExpediente=?";
				cWhereAnd = " and";
			}
			if (cCveServicio != null) {
				cSQL += cWhereAnd + " a.iCveServicio=?";
				cWhereAnd = " and";
			}
			if (cNumExamen != null) {
				cSQL += cWhereAnd + " a.iNumExamen=?";
			}
			cSQL += " order by a.iCveExpediente,a.iNumExamen,a.iCveServicio";
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cCveExpediente != null)
				pstmt.setInt(i++, Integer.parseInt(cCveExpediente));
			if (cCveServicio != null)
				pstmt.setInt(i++, Integer.parseInt(cCveServicio));
			if (cNumExamen != null)
				pstmt.setInt(i++, Integer.parseInt(cNumExamen));
			log("findByPK SQL: " + cSQL);
			log("con parametros: " + hmFiltros);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setICveExpediente(rset.getInt("iCveExpediente"));
				vEXPServicio.setINumExamen(rset.getInt("iNumExamen"));
				vEXPServicio.setICveServicio(rset.getInt("iCveServicio"));
				vEXPServicio.setIOrden(rset.getInt("iOrden"));
				vEXPServicio.setCNotaMedica(rset.getString("cNotaMedica"));
				vEXPServicio.setICveUsuNotaMed(rset.getInt("iCveUsuNotaMed"));
				vEXPServicio.setLConcluido(rset.getInt("lConcluido"));
				vEXPServicio.setDtInicio(rset.getDate("dtInicio"));
				vEXPServicio.setDtFin(rset.getDate("dtFin"));
				vEXPServicio.setICveUsuAplica(rset.getInt("iCveUsuAplica"));
				vEXPServicio.setTsInicio(rset.getTimestamp(11));
				vEXPServicio.setTsFin(rset.getTimestamp(12));
				vcEXPServicio.addElement(vEXPServicio);
			}
		} catch (Exception ex) {
			warn("findByPK", ex);
			throw new DAOException("findByPK");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcEXPServicio;
	}

	/**
	 * Método FindByExpLastExamen en donde concluido = 0
	 * 
	 * @author Marco A.Gonz�lez Paz FindByExpLastExamen
	 */
	public Vector FindByExpLastExamen(int iExpediente, int iExamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;
			String cSQL = "select "
					+ "EXPServicio.iCveExpediente,"
					+ "EXPServicio.iNumExamen,"
					+ "EXPServicio.iCveServicio,"
					+ "EXPServicio.iOrden,"
					+ "EXPServicio.cNotaMedica, "
					+ "EXPServicio.iCveUsuNotaMed,"
					+ "EXPServicio.lConcluido,"
					+ "EXPServicio.dtInicio,"
					+ "EXPServicio.dtFin,"
					+ "EXPServicio.iCveUsuAplica, "
					+ "MEDServicio.cDscServicio "
					+ ",EXPServicio.tsInicio,EXPServicio.tsFin "
					+ "from EXPServicio "
					+ "inner join MEDServicio ON MEDServicio.iCveServicio = EXPServicio.iCveServicio "
					+ "where EXPServicio.iCveExpediente = "
					+ iExpediente
					+ " and EXPServicio.iNumExamen = "
					+ iExamen
					+ " and EXPServicio.lConcluido = 0"
					+ " order by EXPServicio.iNumExamen,EXPServicio.iCveServicio";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setICveExpediente(rset.getInt(1));
				vEXPServicio.setINumExamen(rset.getInt(2));
				vEXPServicio.setICveServicio(rset.getInt(3));
				vEXPServicio.setIOrden(rset.getInt(4));
				vEXPServicio.setCNotaMedica(rset.getString(5));
				vEXPServicio.setICveUsuNotaMed(rset.getInt(6));
				vEXPServicio.setLConcluido(rset.getInt(7));
				vEXPServicio.setDtInicio(rset.getDate(8));
				vEXPServicio.setDtFin(rset.getDate(9));
				vEXPServicio.setICveUsuAplica(rset.getInt(10));
				vEXPServicio.setCDscServicio(rset.getString(11));
				vEXPServicio.setTsInicio(rset.getTimestamp(12));
				vEXPServicio.setTsFin(rset.getTimestamp(13));
				vcEXPServicio.addElement(vEXPServicio);
			}
		} catch (Exception ex) {
			warn("FindByExpLastExamen", ex);
			throw new DAOException("FindByExpLastExamen");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByExpLastExamen.close", ex2);
			}
		}
		return vcEXPServicio;
	}

	/**
	 * Método getLConcluido
	 */
	public Vector getLConcluido(String cExpediente, String cExamen,
			String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		CargaSintomas sintomas = new CargaSintomas();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;
			String cSQL = "select " + "lConcluido, " + "iNumExamen "
					+ ",EXPServicio.tsInicio,EXPServicio.tsFin "
					+ "from EXPServicio " + "where iCveExpediente =  "
					+ cExpediente + "  and iNumExamen =  " + cExamen
					+ "  and iCveServicio =  " + cServicio;

		    System.out.println("Query servicio conlcuido: " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setLConcluido(rset.getInt(1));
				vEXPServicio.setINumExamen(rset.getInt(2));

				// vEXPServicio.setTsInicio(rset.getTimestamp(3));
				// vEXPServicio.setTsFin(rset.getTimestamp(4));
				vcEXPServicio.addElement(vEXPServicio);
			}
		} catch (Exception ex) {
			warn("getLConcluido", ex);
			throw new DAOException("getLConcluido");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("getLConcluido.close", ex2);
			}
		}
		return vcEXPServicio;
	}
	

	/**
	 * Método getLConcluido
	 */
	public Vector getLConcluidoCarga(String cExpediente, String cExamen,
			String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		CargaSintomas sintomas = new CargaSintomas();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;
			String cSQL = "select " + "lConcluido, " + "iNumExamen "
					+ ",EXPServicio.tsInicio,EXPServicio.tsFin "
					+ "from EXPServicio " + "where iCveExpediente =  "
					+ cExpediente + "  and iNumExamen =  " + cExamen
					+ "  and iCveServicio =  " + cServicio;

		   System.out.println("Query servicio conlcuido: " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setLConcluido(rset.getInt(1));
				vEXPServicio.setINumExamen(rset.getInt(2));
				if(rset.getTimestamp(3) != null){
					System.out.println("Ya se genero");
				}else{
					System.out.println("No se ha generado");
					System.out.println(cExpediente);
					System.out.println(cExamen);
					System.out.println(cServicio);
					sintomas.ValidaCargaSintomas(cExpediente, cExamen, cServicio);
					System.out.println("Se termino la validacion o carga");
				}
				// vEXPServicio.setTsInicio(rset.getTimestamp(3));
				// vEXPServicio.setTsFin(rset.getTimestamp(4));
				vcEXPServicio.addElement(vEXPServicio);
			}
		} catch (Exception ex) {
			warn("getLConcluido", ex);
			throw new DAOException("getLConcluido");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("getLConcluido.close", ex2);
			}
		}
		return vcEXPServicio;
	}
	

	/**
	 * Método getNotaMed
	 */
	public Vector getNotaMed(String cExpediente, String cExamen,
			String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;
			String cSQL = "select " + "cNotaMedica "
					+ ",EXPServicio.tsInicio,EXPServicio.tsFin "
					+ "from EXPServicio " + "where iCveExpediente =  "
					+ cExpediente + "  and iNumExamen =  " + cExamen
					+ "  and iCveServicio =  " + cServicio;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setCNotaMedica(rset.getString(1));
				vEXPServicio.setTsInicio(rset.getTimestamp(2));
				vEXPServicio.setTsFin(rset.getTimestamp(3));
				vcEXPServicio.addElement(vEXPServicio);
			}
		} catch (Exception ex) {
			warn("getNotaMed", ex);
			throw new DAOException("getNotaMed");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("getNotaMed.close", ex2);
			}
		}
		return vcEXPServicio;
	}

	/*
          */
	public Vector FindServicio(String cExpediente, String cExamen,
			String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;
			String cSQL = "select "
					+ "EXPServicio.iCveExpediente,"
					+ "EXPServicio.iNumExamen,"
					+ "EXPServicio.iCveServicio,"
					+ "EXPServicio.iOrden,"
					+ "EXPServicio.cNotaMedica, "
					+ "EXPServicio.iCveUsuNotaMed,"
					+ "EXPServicio.lConcluido,"
					+ "EXPServicio.dtInicio,"
					+ "EXPServicio.dtFin,"
					+ "EXPServicio.iCveUsuAplica, "
					+ "MEDServicio.cDscServicio "
					+ ",EXPServicio.tsInicio,EXPServicio.tsFin "
					+ "from EXPServicio "
					+ "inner join MEDServicio ON MEDServicio.iCveServicio = EXPServicio.iCveServicio "
					+ "where EXPServicio.iCveExpediente = " + cExpediente
					+ " and EXPServicio.iNumExamen = " + cExamen
					+ " and EXPServicio.iCveServicio =  " + cServicio;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setICveExpediente(rset.getInt(1));
				vEXPServicio.setINumExamen(rset.getInt(2));
				vEXPServicio.setICveServicio(rset.getInt(3));
				vEXPServicio.setIOrden(rset.getInt(4));
				vEXPServicio.setCNotaMedica(rset.getString(5));
				vEXPServicio.setICveUsuNotaMed(rset.getInt(6));
				vEXPServicio.setLConcluido(rset.getInt(7));
				vEXPServicio.setDtInicio(rset.getDate(8));
				vEXPServicio.setDtFin(rset.getDate(9));
				vEXPServicio.setICveUsuAplica(rset.getInt(10));
				vEXPServicio.setCDscServicio(rset.getString(11));
				vEXPServicio.setTsInicio(rset.getTimestamp(12));
				vEXPServicio.setTsFin(rset.getTimestamp(13));
				vcEXPServicio.addElement(vEXPServicio);
			}
		} catch (Exception ex) {
			warn("FindByExpLastExamen", ex);
			throw new DAOException("FindByExpLastExamen");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByExpLastExamen.close", ex2);
			}
		}
		return vcEXPServicio;
	}

	/*
     */
	public Vector FindServicios(int iExpediente, int iExamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;
			String cSQL = "select "
					+ "EXPServicio.iCveExpediente,"
					+ "EXPServicio.iNumExamen,"
					+ "EXPServicio.iCveServicio,"
					+ "EXPServicio.iOrden,"
					+ "EXPServicio.cNotaMedica, "
					+ "EXPServicio.iCveUsuNotaMed,"
					+ "EXPServicio.lConcluido,"
					+ "EXPServicio.dtInicio,"
					+ "EXPServicio.dtFin,"
					+ "EXPServicio.iCveUsuAplica, "
					+ "MEDServicio.cDscServicio "
					+ ",EXPServicio.tsInicio,EXPServicio.tsFin "
					+ "from EXPServicio "
					+ "inner join MEDServicio ON MEDServicio.iCveServicio = EXPServicio.iCveServicio "
					+ "where EXPServicio.iCveExpediente = "
					+ iExpediente
					+ " and EXPServicio.iNumExamen = "
					+ iExamen
					+ " order by EXPServicio.iNumExamen,EXPServicio.iCveServicio";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setICveExpediente(rset.getInt(1));
				vEXPServicio.setINumExamen(rset.getInt(2));
				vEXPServicio.setICveServicio(rset.getInt(3));
				vEXPServicio.setIOrden(rset.getInt(4));
				vEXPServicio.setCNotaMedica(rset.getString(5));
				vEXPServicio.setICveUsuNotaMed(rset.getInt(6));
				vEXPServicio.setLConcluido(rset.getInt(7));
				vEXPServicio.setDtInicio(rset.getDate(8));
				vEXPServicio.setDtFin(rset.getDate(9));
				vEXPServicio.setICveUsuAplica(rset.getInt(10));
				vEXPServicio.setCDscServicio(rset.getString(11));
				vEXPServicio.setTsInicio(rset.getTimestamp(12));
				vEXPServicio.setTsFin(rset.getTimestamp(13));
				vcEXPServicio.addElement(vEXPServicio);
			}
		} catch (Exception ex) {
			warn("FindByExpLastExamen", ex);
			throw new DAOException("FindByExpLastExamen");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByExpLastExamen.close", ex2);
			}
		}
		return vcEXPServicio;
	}

	/**
	 * Método FindByExpedienteExamen
	 * 
	 * @author Marco A.Gonz�lez Paz
	 */
	public Vector FindByExpedienteExamen(String cExpediente, String cExamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;

			if (cExpediente.compareTo("") == 0
					|| cExpediente.compareTo("null") == 0)
				cExpediente = "0";
			if (cExamen.compareTo("") == 0 || cExamen.compareTo("null") == 0)
				cExamen = "0";

			String cSQL = "select "
					+ "EXPServicio.iCveExpediente,"
					+ "EXPServicio.iNumExamen,"
					+ "EXPServicio.iCveServicio,"
					+ "EXPServicio.iOrden,"
					+ "EXPServicio.cNotaMedica, "
					+ "EXPServicio.iCveUsuNotaMed,"
					+ "EXPServicio.lConcluido,"
					+ "EXPServicio.dtInicio,"
					+ "EXPServicio.dtFin,"
					+ "EXPServicio.iCveUsuAplica, "
					+ "MEDServicio.cDscServicio "
					+ ",EXPServicio.tsInicio,EXPServicio.tsFin "
					+ "from EXPServicio "
					+ "inner join MEDServicio ON MEDServicio.iCveServicio = EXPServicio.iCveServicio "
					+ "where EXPServicio.iCveExpediente = "
					+ cExpediente
					+ " and EXPServicio.iNumExamen = "
					+ cExamen
					+ " order by EXPServicio.iNumExamen,EXPServicio.iCveServicio";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setICveExpediente(rset.getInt(1));
				vEXPServicio.setINumExamen(rset.getInt(2));
				vEXPServicio.setICveServicio(rset.getInt(3));
				vEXPServicio.setIOrden(rset.getInt(4));
				vEXPServicio.setCNotaMedica(rset.getString(5));
				vEXPServicio.setICveUsuNotaMed(rset.getInt(6));
				vEXPServicio.setLConcluido(rset.getInt(7));
				vEXPServicio.setDtInicio(rset.getDate(8));
				vEXPServicio.setDtFin(rset.getDate(9));
				vEXPServicio.setICveUsuAplica(rset.getInt(10));
				vEXPServicio.setCDscServicio(rset.getString(11));
				vEXPServicio.setTsInicio(rset.getTimestamp(12));
				vEXPServicio.setTsFin(rset.getTimestamp(13));
				vcEXPServicio.addElement(vEXPServicio);
			}
		} catch (Exception ex) {
			warn("FindByExpedienteExamen", ex);
			throw new DAOException("FindByExpedienteExamen");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByExpedienteExamen.close", ex2);
			}
		}
		return vcEXPServicio;
	}

	/**
	 * Método FindBy???
	 */
	public Vector FindBy(String cExpediente, String cExamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;
			String cSQL = "select "
					+ "        MEDServicio.cDscServicio "
					+ ", EXPServicio.tsInicio, EXPServicio.tsFin "
					+ "from    EXPServicio "
					+ "inner join MEDServicio on MEDServicio.iCveServicio = EXPServicio.iCveServicio "
					+ "where EXPServicio.iCveExpediente = " + cExpediente
					+ "  and EXPServicio.iNumExamen = " + cExamen;
			pstmt = conn.prepareStatement(cSQL);
			//System.out.println(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setCDscServicio(rset.getString(1));
				// vEXPServicio.setTsInicio(rset.getTimestamp(2));
				// vEXPServicio.setTsFin(rset.getTimestamp(3));
				vcEXPServicio.addElement(vEXPServicio);
			}
			//System.out.println("Vector = "+vcEXPServicio.size());
		} catch (Exception ex) {
			warn("FindByExpedienteExamen", ex);
			throw new DAOException("FindByExpedienteExamen");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByExpedienteExamen.close", ex2);
			}
		}
		return vcEXPServicio;
	}

	/**
	 * Método Insert
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
			TVEXPServicio vEXPServicio = (TVEXPServicio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPServicio(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "iOrden,"
					+ "cNotaMedica," + "iCveUsuNotaMed," + "lConcluido,"
					+ "dtInicio," + "dtFin," + "iCveUsuAplica"
					+ ")values(?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vEXPServicio.getICveExpediente());
			pstmt.setInt(2, vEXPServicio.getINumExamen());
			pstmt.setInt(3, vEXPServicio.getICveServicio());
			pstmt.setInt(4, vEXPServicio.getIOrden());
			pstmt.setString(5, vEXPServicio.getCNotaMedica());
			pstmt.setInt(6, vEXPServicio.getICveUsuNotaMed());
			pstmt.setInt(7, vEXPServicio.getLConcluido());
			pstmt.setDate(8, vEXPServicio.getDtInicio());
			pstmt.setDate(9, vEXPServicio.getDtFin());
			pstmt.setInt(10, vEXPServicio.getICveUsuAplica());
			iInserted = pstmt.executeUpdate(); // Agregado por Rafael Alcocer
												// Caldera 08/Nov/2006
			cClave = "" + vEXPServicio.getICveExpediente();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				ex.printStackTrace();
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método InsertFromExpExamen Author: JMG Solo da de alta valores
	 * seleccionados a travez del inicio del examen (EXPExamAplica)
	 */
	public Object insertFromExpExamen(Connection conGral, Object obj,
			int iCveServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		/*
		 * Calcula Fecha Actual
		 */
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPServicio(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "lConcluido,"
					+ "dtInicio," + "dtFin," + "iCveUsuAplica"
					+ ")values(?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from EXPServicio "
					+ "Where iCveExpediente = "
					+ vEXPExamAplica.getICveExpediente()
					+ " And  iNumExamen = " + vEXPExamAplica.getINumExamen()
					+ " And  iCveServicio = " + iCveServicio;
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			pstmt.setInt(1, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(2, vEXPExamAplica.getINumExamen());
			pstmt.setInt(3, iCveServicio);
			pstmt.setInt(4, 0);
			pstmt.setDate(5, defaultFecha);
			pstmt.setDate(6, defaultFecha);
			pstmt.setInt(7, 0);
			if (iMax == 0) {
				pstmt.executeUpdate();
			}
			cClave = "" + vEXPExamAplica.getICveExpediente();
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método update
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
			TVEXPServicio vEXPServicio = (TVEXPServicio) obj;
			log("servicio recibido para actualizar: "
					+ vEXPServicio.toHashMap().toString());
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPServicio" + " set iOrden= ?, "
					+ "cNotaMedica= ?, " + "iCveUsuNotaMed= ?, "
					+ "lConcluido= ?, " + "dtInicio= ?, " + "dtFin= ?, "
					+ "iCveUsuAplica= ?, " + "tsFin= CURRENT TIMESTAMP " + "where iCveExpediente = ? "
					+ "and iNumExamen = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPServicio.getIOrden());
			pstmt.setString(2, vEXPServicio.getCNotaMedica());
			pstmt.setInt(3, vEXPServicio.getICveUsuNotaMed());
			pstmt.setInt(4, vEXPServicio.getLConcluido());
			pstmt.setDate(5, vEXPServicio.getDtInicio());
			pstmt.setDate(6, vEXPServicio.getDtFin());
			pstmt.setInt(7, vEXPServicio.getICveUsuAplica());
			pstmt.setInt(8, vEXPServicio.getICveExpediente());
			pstmt.setInt(9, vEXPServicio.getINumExamen());
			pstmt.setInt(10, vEXPServicio.getICveServicio());
			iUpdated = pstmt.executeUpdate(); // Agregado por Rafael Alcocer
												// Caldera 08/Nov/2006
			cClave = "" + vEXPServicio.getICveServicio();
			log("el servicio " + cClave + " se ha actualizado...");
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
				if (dbConn != null) {
					if (dbConn != null)
						dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método Delete
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
			TVEXPServicio vEXPServicio = (TVEXPServicio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPServicio" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPServicio.getICveExpediente());
			pstmt.setInt(2, vEXPServicio.getINumExamen());
			pstmt.setInt(3, vEXPServicio.getICveServicio());
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("delete.closeEXPServicio", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Atributo que define si los mensajes de depuraci�n se env�an a la consola.
	 * Si se requiere que los mensajes enviados por el Método log() no se
	 * muestren, es necesario establecer su valor a <code>false</code>.
	 */
	private boolean debug = false;

	/**
	 * Método que env�a un Object a la consola, como String, si la bandera debug
	 * est� activada. El nombre cualificado de la clase se env�a antes del
	 * objeto.
	 * 
	 * @param obj
	 *            el objeto que ser� enviado a la consola como String
	 * @author Romeo Sanchez
	 */
	private void log(Object obj) {
		// ************* No modificar *************
		if (debug)
			; // System.out.println(this.getClass().getName()+":"+obj.toString());
	}

	/**
	 * Método getNotaMed
	 */
	public Vector findMedNota(String cExpediente, String cExamen,
			String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		StringBuffer cSQL = new StringBuffer();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;
			cSQL.append("  select S.cNotaMedica, ")
					.append("         U.cApPaterno || ' ' || U.cApMaterno || ', ' || U.cNombre as cMedico ")
					.append(" , S.tsInicio, S.tsFin ")
					.append("  from EXPServicio S ")
					.append("  left join SEGUsuario U on U.iCveUsuario = S.iCveUsuNotaMed ")
					.append("  where S.iCveExpediente = ").append(cExpediente)
					.append("    and S.iNumExamen     = ").append(cExamen)
					.append("    and S.iCveServicio   = ").append(cServicio);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setCNotaMedica(rset.getString(1));
				vEXPServicio.setCNombre(rset.getString(2));
				vEXPServicio.setTsInicio(rset.getTimestamp(3));
				vEXPServicio.setTsFin(rset.getTimestamp(4));
				vcEXPServicio.addElement(vEXPServicio);
			}
		} catch (Exception ex) {
			warn("findMedNota", ex);
			throw new DAOException("findMedNota");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("findMedNota.close", ex2);
			}
		}
		return vcEXPServicio;
	}

	/**
	 * Método para consultar los usuarios atendidos por servicio
	 * 
	 * @param cFiltro
	 *            condici�n de b�squeda
	 * @param lVariosMed
	 *            El servicio es de uno o varios m�dicos
	 * @param iCveServicio
	 *            Clave del servicio a consultar
	 * @param iCveRama
	 *            Clave de la rama a consultar
	 * @return Vector de objetos
	 * @throws DAOException
	 */
	public Vector FindAtenXServ(String cFiltro, int lVariosMed,
			int iCveServicio, int iCveRama) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		String cClave = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVEXPAtenServ vAtenServ;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			// Query
			cSQL.append(" select EA.iCveExpediente, ")
					// 1
					.append("        PD.cRFC, PD.cApPaterno || ' ' || PD.cApMaterno || ' ' || PD.cNombre as cNombre, ") // 2,
																														// 3
					.append("        ES.lConcluido, ES.iCveUsuAplica, ") // 4, 5
					.append("        U.cNombre || ' ' || U.cApPaterno || ' ' || U.cApMaterno as cAplica "); // 6
			cSQL.append("       , ES.tsInicio, ES.tsFin "); // 7,8
			cSQL.append("        , EA.iFolioES  ") // 9
					.append("        , ES.iCveServicio, EA.iNumExamen "); // 10,
																			// 11

			if (lVariosMed == 1) {
				cSQL.append("      , MR.icveRama, ER.lConcluido, ER.iCveUsuAplica, MR.cDscRama "); // 12,
																									// 13,
																									// 14,
																									// 15
				cSQL.append(" , ER.tsInicio, ER.tsFin "); // 16, 17
			}

			cSQL.append(" from EXPExamAplica EA ")
					.append(" inner join EXPServicio ES on ES.iCveExpediente = EA.iCveExpediente ")
					.append("                          and ES.iNumExamen     = EA.iNumExamen ")
					.append("                          and ES.iCveServicio   = ")
					.append(iCveServicio);
			if (lVariosMed == 1) {
				cSQL.append(
						" inner join EXPRama ER on ER.iCveExpediente = ES.iCveExpediente ")
						.append("                      and ER.iNumExamen     = ES.iNumExamen ")
						.append("                      and ER.iCveServicio   = ES.iCveServicio ");
				if (iCveRama > 0)
					cSQL.append(
							"                      and ER.iCveRama       = ")
							.append(iCveRama);
				cSQL.append(
						" inner join MEDRama MR on MR.iCveServicio   = ER.iCveServicio ")
						.append("                      and MR.iCveRama       = ER.iCveRama ")
						.append(" left join SEGUsuario U on U.iCveUsuario  = ER.iCveUsuAplica ");
			} else
				cSQL.append(" left join SEGUsuario U on U.iCveUsuario  = ES.iCveUsuAplica ");
			cSQL.append(
					" inner join PERDatos PD on PD.iCvePersonal = EA.iCveExpediente ")
					.append(cFiltro);

			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vAtenServ = new TVEXPAtenServ();
				// Obtener los valores de los campos
				vAtenServ.VServ.setICveExpediente(rset.getInt(1));
				vAtenServ.VServ.setCRFC(rset.getString(2));
				vAtenServ.VServ.setCNombre(rset.getString(3));
				vAtenServ.VServ.setLConcluido(rset.getInt(4));
				vAtenServ.VServ.setICveUsuAplica(rset.getInt(5));
				vAtenServ.VMedico.setCNombre(rset.getString(6));
				// vAtenServ.VServ.setTsInicio(rset.getTimestamp(7));
				// vAtenServ.VServ.setTsFin(rset.getTimestamp(8));
				vAtenServ.VExamAplica.setIFolioEs(rset.getInt(9));
				vAtenServ.VServ.setICveServicio(rset.getInt(10));
				vAtenServ.VExamAplica.setINumExamen(rset.getInt(11));

				if (lVariosMed == 1) {
					vAtenServ.VRama.setIConcluido(rset.getInt(13));
					vAtenServ.VRama.setICveUsuAplica(rset.getInt(14));
					vAtenServ.VRama.setCDscRama(rset.getString(15));
					// vAtenServ.VRama.setTsInicio(rset.getTimestamp(13));
					// vAtenServ.VRama.setTsFin(rset.getTimestamp(14));
				}
				vcEXPServicio.addElement(vAtenServ);
			}
		} catch (Exception ex) {
			warn("FindAtenXServ", ex);
			throw new DAOException("FindAtenXServ");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindAtenXServ", ex2);
			}
			return vcEXPServicio;
		}
	}

	public int Liberar(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iRset = 0;
		String cWhere = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");
			String cServicio = (String) hmFiltros.get("iCveServicio");
			String cUsuario = (String) hmFiltros.get("iCveUsuario");

			if (cServicio.equals("31")) {
				String cSQL = " UPDATE EXPServicio SET "
						+ " iCveUsuNotaMed = 0, " + " lConcluido = 0, "
						+ " iCveUsuAplica = 0, " + " cNotaMedica = null "
						+ " WHERE iCveExpediente = ? " + " AND iNumExamen = ? "
						+ " AND iCveServicio in (31,44) ";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, Integer.parseInt(cCveExpediente));
				pstmt.setInt(2, Integer.parseInt(cNumExamen));
				cWhere = "iCveExpediente = " + cCveExpediente
						+ " AND iNumExamen = " + cNumExamen
						+ " AND iCveServicio = (31) ";
			} else {
				String cSQL = " UPDATE EXPServicio SET "
						+ " iCveUsuNotaMed = 0, " + " lConcluido = 0, "
						+ " iCveUsuAplica = 0, " + " cNotaMedica = null "
						+ " WHERE iCveExpediente = ? " + " AND iNumExamen = ? "
						+ " AND iCveServicio = ? ";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, Integer.parseInt(cCveExpediente));
				pstmt.setInt(2, Integer.parseInt(cNumExamen));
				pstmt.setInt(3, Integer.parseInt(cServicio));
			}

			iRset = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("Liberar", ex2);
			}
		}
		return iRset;
	}

	/**
	 * Inserta Obsercacions y restricciones En Diagnostico y Recomendaciones del
	 * Dictaminador.
	 **/
	public Object insertObserRes(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int Servicio = 44;
		int Concluido = 1;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVEXPDictamenServ vEXPServicio = (TVEXPDictamenServ) obj;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPServicio" + " set cNotaMedica= ?, "
					+ "iCveUsuNotaMed= ?, " + "lConcluido= ?, "
					+ "iCveUsuAplica= ?, " +"tsFin= CURRENT TIMESTAMP " + "where iCveExpediente = ? "
					+ "and iNumExamen = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vEXPServicio.getCObserRes());
			pstmt.setInt(2, vEXPServicio.getICveUsuDictamen());
			pstmt.setInt(3, Concluido);
			pstmt.setInt(4, vEXPServicio.getICveUsuDictamen());
			pstmt.setInt(5, vEXPServicio.getICveExpediente());
			pstmt.setInt(6, vEXPServicio.getINumExamen());
			pstmt.setInt(7, Servicio);
			iUpdated = pstmt.executeUpdate();
			cClave = "" + vEXPServicio.getICveExpediente();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				ex.printStackTrace();
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método Find by EXPServicio
	 */
	public Vector findByObserRes(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = "select cNotaMedica from EXPServicio "
					+ " where iCveExpediente =  "
					+ vEXPDictamenServ.getICveExpediente()
					+ " and iNumExamen = " + vEXPDictamenServ.getINumExamen()
					+ " and iCveServicio = 44";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setCObserRes(rset.getString(1));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("FindByCatSol", ex);
			throw new DAOException("FindByCatSol");
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
				warn("FindByCatSol.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}

	/**
	 * Método Find by EXPServicio
	 */
	public Vector findByObserResDos(String exp, String exa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		Vector vcEXPDictamenServ = new Vector();
		TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = "select cNotaMedica from EXPServicio "
					+ " where iCveExpediente =  "
					+ exp
					+ " and iNumExamen = " + exa
					+ " and iCveServicio = 44";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPDictamenServ = new TVEXPDictamenServ();
				vEXPDictamenServ.setCObserRes(rset.getString(1));
				vcEXPDictamenServ.addElement(vEXPDictamenServ);
			}
		} catch (Exception ex) {
			warn("FindByCatSol", ex);
			throw new DAOException("FindByCatSol");
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
				warn("FindByCatSol.close", ex2);
			}
			return vcEXPDictamenServ;
		}
	}

	
	/**
	 * Agregado por Rafael Alcocer Caldera 08/Nov/2006
	 * 
	 * @return int
	 */
	public int getIInserted() {
		return iInserted;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 08/Nov/2006
	 * 
	 * @return int
	 */
	public int getIUpdated() {
		return iUpdated;
	}

	/**
	 * Método Insert2
	 */
	public String insertServTox(String Exp, String Examen) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		Vector Query = new Vector();
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			/*
			 * Calcula Fecha Actual
			 */
			java.util.Date today = new java.util.Date();
			java.sql.Date defFecha = new java.sql.Date(today.getTime());
			java.sql.Date defaultFecha = new java.sql.Date(today.getTime());

			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			Query.addElement("insert into EXPServicio(iCveExpediente,iNumExamen,iCveServicio,iOrden,iCveUsuNotaMed,lConcluido,dtInicio,dtFin,iCveUsuAplica)values("
					+ Exp
					+ ","
					+ Examen
					+ ",45,1,0,0,'"
					+ defaultFecha
					+ "','"
					+ defaultFecha + "',0)");
			Query.addElement("insert into EXPRama(iCveExpediente,iNumExamen,iCveServicio,iCveRama,lConcluido,dtInicio,dtFin,iCveUsuAplica)values("
					+ Exp
					+ ","
					+ Examen
					+ ",45,1,0,'"
					+ defaultFecha
					+ "','"
					+ defaultFecha + "',0)");
			Query.addElement("insert into EXPResultado(iCveExpediente,iNumExamen,iCveServicio,iCveRama,iCveSintoma,dValorIni,lLogico,cCaracter,dValorFin,cValRef)values("
					+ Exp + "," + Examen + ",45,1,1,null,null,null,null,' ')");
			Query.addElement("insert into EXPResultado(iCveExpediente,iNumExamen,iCveServicio,iCveRama,iCveSintoma,dValorIni,lLogico,cCaracter,dValorFin,cValRef)values("
					+ Exp + "," + Examen + ",45,1,2,null,null,null,null,' ')");
			Query.addElement("insert into EXPResultado(iCveExpediente,iNumExamen,iCveServicio,iCveRama,iCveSintoma,dValorIni,lLogico,cCaracter,dValorFin,cValRef)values("
					+ Exp + "," + Examen + ",45,1,3,null,null,null,null,' ')");
			Query.addElement("insert into EXPResultado(iCveExpediente,iNumExamen,iCveServicio,iCveRama,iCveSintoma,dValorIni,lLogico,cCaracter,dValorFin,cValRef)values("
					+ Exp + "," + Examen + ",45,1,4,null,null,null,null,' ')");
			Query.addElement("insert into EXPResultado(iCveExpediente,iNumExamen,iCveServicio,iCveRama,iCveSintoma,dValorIni,lLogico,cCaracter,dValorFin,cValRef)values("
					+ Exp + "," + Examen + ",45,1,5,null,null,null,null,' ')");
			Query.addElement("insert into EXPResultado(iCveExpediente,iNumExamen,iCveServicio,iCveRama,iCveSintoma,dValorIni,lLogico,cCaracter,dValorFin,cValRef)values("
					+ Exp + "," + Examen + ",45,1,6,null,null,null,null,' ')");
			Query.addElement("insert into EXPResultado(iCveExpediente,iNumExamen,iCveServicio,iCveRama,iCveSintoma,dValorIni,lLogico,cCaracter,dValorFin,cValRef)values("
					+ Exp + "," + Examen + ",45,1,8,null,null,null,null,' ')");
			// Query.addElement("insert into EXPResultado(iCveExpediente,iNumExamen,iCveServicio,iCveRama,iCveSintoma,dValorIni,lLogico,cCaracter,dValorFin,cValRef)values("+Exp+","+Examen+",45,1,14,null,null,null,null,' ')");

			for (int i = 0; i < Query.size(); i++) {
				pstmt = conn.prepareStatement(Query.get(i).toString());
				// System.out.println(Query.get(i).toString());
				iInserted = pstmt.executeUpdate(); // Agregado por Rafael
													// Alcocer Caldera
													// 08/Nov/2006
			}
			cClave = "" + Exp;
		} catch (Exception ex) {

			warn("insert", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método update AG SA
	 */
	public String Sentencia(String cSQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// TVPERDatos vPERDatos = (TVPERDatos) obj;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			 //System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			pstmt.executeUpdate();
			cClave = "correcto";
			if (conn == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conn == null) {
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
				if (conn == null) {
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
	 * Método Delete
	 */
	public String Sdelete(String SQLS) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int iEntidades = 0;
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			String cSQL = "";
			// TVEXPServicio vEXPServicio = (TVEXPServicio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = SQLS;

			// System.out.println(SQLS);
			pstmt = conn.prepareStatement(cSQL);

			pstmt.executeUpdate();
			cClave = "";
			if (conn == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conn == null) {
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
				if (conn == null) {
					conn.close();
				}
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("delete.closeEXPServicio", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Indica los servicios que hacen falta de concluirse antes de poder
	 * capturar un servicio, de acuerdo al index de servicios
	 */
	public Vector FindSerIndex(String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPServicio = new Vector();
		String cClave = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPServicio vEXPServicio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			// Calcula Fecha Actual
			java.sql.Date dtCampo;
			TFechas tfCampo = new TFechas();
			java.util.Date today = new java.util.Date();
			java.sql.Date defFecha = new java.sql.Date(today.getTime());
			java.sql.Date defaultFecha = new java.sql.Date(today.getTime());

			cSQL = " SELECT E.ICVESERVICIO, M.CDSCSERVICIO "
					+ " FROM EXPSERVICIO AS E, MEDSERVICIO AS M " + " WHERE "
					+ " M.ICVESERVICIO = E.ICVESERVICIO AND "
					+ " E.LCONCLUIDO = 0 AND " + cServicio
					+ "  ORDER BY M.IORDEN";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPServicio = new TVEXPServicio();
				vEXPServicio.setICveServicio(rset.getInt(1));
				vEXPServicio.setCDscServicio(rset.getString(2));

				vcEXPServicio.addElement(vEXPServicio);
			}
		} catch (Exception ex) {
			warn("FindUserXServ", ex);
			throw new DAOException("FindUserXServ");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindUserXServ.close", ex2);
			}
			return vcEXPServicio;
		}
	}
	

	/**
	 * Método update
	 */
	public Object updateSQL(Connection conGral,String cSQL) throws DAOException {
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
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			
			pstmt = conn.prepareStatement(cSQL);
			
			//System.out.println("updateSQL = "+cSQL);
			
			iUpdated = pstmt.executeUpdate(); 
			cClave = "" + iUpdated;

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
				if (dbConn != null) {
					if (dbConn != null)
						dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}
	

	/**
	 * Método update AG SA
	 */
	public int RegresaInt(String SQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		int regreso = 0;
		// TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = SQL;
			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			vPERDatos = new TVPERDatos();
			while (rset.next()) {
				regreso = rset.getInt(1);
				System.out.println("regreso = "+regreso);
			}

		} catch (Exception ex) {
			warn("RegresaInt", ex);
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
				warn("RegresaInt", ex2);
			}
			return regreso;
		}
	}

	/**
	 * Método update AG SA
	 */
	public Vector ServObligatoriosXInterconsulta(String Expediente, String Examen) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		int regreso = 0;
		Vector vcMEDSERVObligatorio = new Vector();
		TVMEDSERVObligatorio vMEDSERVObligatorio;
		// TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "SELECT M.ICVESERVICIO, M.CDSCSERVICIO "+
							"FROM    "+
							"	EXPSERVICIO AS E,   "+
							"	MEDSERVICIO AS M   "+
							"WHERE    "+
							"	E.ICVESERVICIO = M.ICVESERVICIO AND   "+
							"	E.ICVEEXPEDIENTE = "+Expediente+" AND   "+
							"	E.INUMEXAMEN= "+Examen+" AND   "+
							"	E.IORDEN = -100 AND   "+
							"	E.LCONCLUIDO = 0 ";
			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			vPERDatos = new TVPERDatos();
			while (rset.next()) {
				regreso = rset.getInt(1);
				vMEDSERVObligatorio = new TVMEDSERVObligatorio();
				vMEDSERVObligatorio.setICveServicio(rset.getInt(1));
				vMEDSERVObligatorio.setCDscServicio(rset.getString(2));
				vcMEDSERVObligatorio.addElement(vMEDSERVObligatorio);
			}

		} catch (Exception ex) {
			warn("RegresaInt", ex);
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
				warn("RegresaInt", ex2);
			}
			return vcMEDSERVObligatorio;
		}
	}

	
	
}
