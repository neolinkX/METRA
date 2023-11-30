package gob.sct.medprev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Vector;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.sql.DAOBase;
import com.micper.sql.DbConnection;

import gob.sct.medprev.vo.TVEXPMulta;


/**
 * <p>
 * Title: Data Acces Object de EXPMultas DAO
 * </p>
 * <p>
 * Description: Data Access Object para EXPMultas
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author AG SA
 * @version 1.0
 */

public class TDEXPMultas extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPMultas() {
	}

	/*
	 * Metodo Boolean Multa
	 * Existe o no un antecedente de Multa
	 */
	@SuppressWarnings("finally")
	public boolean MultaReincidencia(int iCveExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean ReincidenciaMulta = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "	SELECT "
					+ "			COUNT(ICVEEXPEDIENTE) "
					+ "	FROM "
					+ "			EXPMULTAS "
					+ "	WHERE "
					+ "			ICVEEXPEDIENTE = ?";

			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iCveExpediente);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if(rset.getInt(1)>0){
					ReincidenciaMulta = true;	
				}				
				
			}
			//System.out.println("ReincidenciaMulta  "+ReincidenciaMulta);
			
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
			return ReincidenciaMulta;
		}
	}
	
	

	/**
	 * Metodo Insert para el proceso EPI NOM-024
	 */
	@SuppressWarnings("finally")
	public Object insertCA(Connection conGral, Object obj) throws DAOException {
		System.out.println("Generando Multa");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtAdnl = null;
		PreparedStatement pstmtFind = null;
		PreparedStatement pstmtValida = null;
		ResultSet rset = null;
		ResultSet rsetValida = null;

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			//TvEXPMulta vEXPMulta = (TvEXPMulta) obj;
			TVEXPMulta vEXPMulta = (TVEXPMulta) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int iConsecutivo = 0;
			String cSQLFind;
			
			//Formatear Fecha de Pago
			String fecha = "";
			fecha = vEXPMulta.getDtFecha()+" ";
			if(vEXPMulta.getIHora()>9){
				fecha = fecha + vEXPMulta.getIHora()+":";	
			}else{
				fecha = fecha +"0"+vEXPMulta.getIHora()+":";
			}
			
			if(vEXPMulta.getIMinutos()>9){
				fecha = fecha + vEXPMulta.getIMinutos()+":00.000";
			}else{
				fecha = fecha +"0"+vEXPMulta.getIMinutos()+":00.000";
			}
			


			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss.SSSSSS");
			java.util.Date parsedDate = dateFormat.parse(fecha);
			java.sql.Timestamp timestamp = new java.sql.Timestamp(
					parsedDate.getTime());
			
			
			cSQLFind = "select max(iCveMulta) "
						+ "from EXPMultas "
						+ "where iCveExpediente = ?";
			//System.out.println("icvepersonal = "+vEXPMulta.getICvePersonal());
				pstmtFind = conn.prepareStatement(cSQLFind);
				pstmtFind.setInt(1, vEXPMulta.getICvePersonal());

				rset = pstmtFind.executeQuery();
				while (rset.next()) {
					iConsecutivo = rset.getInt(1);
				}

				vEXPMulta.setICveMulta(iConsecutivo + 1);

				cSQL = "insert into EXPMultas(" 
						+ "iCveExpediente,"
						+ "iCveMulta,"
						+ "tRealizoPago,"
						+ "iCantidad,"
						+ "iTarifa,"
						+ "tRegistroPago"
						+ ")values(?,?,?,?,?,current timestamp)";

				//System.out.println("icvepersonal = "+vEXPMulta.getICvePersonal());
				//System.out.println("iConsecutivo = "+vEXPMulta.getICveMulta());
				//System.out.println("timestamp = "+timestamp);
				//System.out.println("getICantidad = "+vEXPMulta.getICantidad());
				//System.out.println("getITarifa = "+vEXPMulta.getITarifa());
				
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEXPMulta.getICvePersonal());
				pstmt.setInt(2, vEXPMulta.getICveMulta());
				pstmt.setTimestamp(3, timestamp);
				pstmt.setInt(4, vEXPMulta.getICantidad());
				pstmt.setInt(5, vEXPMulta.getITarifa());
				pstmt.executeUpdate();
				cClave = "" + vEXPMulta.getICveMulta();			

			if (conGral == null) {
				conn.commit();
			}
			
			//System.out.println("Se ejecuto la alta");
			
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
				if (pstmtAdnl != null) {
					pstmtAdnl.close();
				}

				if (pstmtFind != null) {
					pstmtFind.close();
				}

				if (pstmtValida != null) {
					pstmtValida.close();
				}

				if (rset != null) {
					rset.close();
				}

				if (rsetValida != null) {
					rsetValida.close();
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
	@SuppressWarnings("finally")
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
			TVEXPMulta vEXPMulta = (TVEXPMulta) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EPICisCita " + "set cHora = ? "
					+ "where iCveUniMed = ? " + "and dtFecha = ? "
					+ "and iCveCita = ? " + "and iCveModulo = ?";
			pstmt = conn.prepareStatement(cSQL);

			GregorianCalendar g = new GregorianCalendar(0, 0, 0,
					vEXPMulta.getIHora(), vEXPMulta.getIMinutos());
			Time t = new Time(g.getTimeInMillis());
			pstmt.setString(1, "" + t);
			pstmt.setInt(2, vEXPMulta.getICveUniMedA());
			pstmt.setDate(3, vEXPMulta.getDtFechaA());
			pstmt.setInt(4, vEXPMulta.getICveMulta());
			pstmt.setInt(5, vEXPMulta.getICveModuloA());
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
	 * Metodo Find By All
	 */
	@SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	public Vector FindByAll(String cCondicion)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		Vector vcEXPMulta = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPMulta vEXPMulta;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT "+
						"M.ICVEEXPEDIENTE, "+ 
						"M.ICVEMULTA,  "+
						"M.TREALIZOPAGO,  "+
						"M.ICANTIDAD,  "+
						"M.ITARIFA,  "+
						"M.TREGISTROPAGO, "+
						"P.CAPPATERNO, "+
						"P.CAPMATERNO, "+
						"P.CNOMBRE, "+
						"P.CSEXO, "+
						"P.CRFC, "+
						"P.CCURP "+
					"FROM  "+
						"EXPMULTAS AS M, "+
						"PERDATOS AS P "+
					"WHERE "+
						"M.ICVEEXPEDIENTE = P.ICVEEXPEDIENTE AND "+
						"M.ICVEMULTA = (SELECT MAX(C.ICVEMULTA) FROM EXPMULTAS AS C WHERE C.ICVEEXPEDIENTE = M.ICVEEXPEDIENTE) AND "+
						"M.ICVEEXPEDIENTE = ?";

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, Integer.parseInt(cCondicion));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPMulta = new TVEXPMulta();
				vEXPMulta.setICvePersonal(rset.getInt(1));
				vEXPMulta.setICveMulta(rset.getInt(2));
				vEXPMulta.setTRealizoPago(rset.getTimestamp(3));
				vEXPMulta.setICantidad(rset.getInt(4));
				vEXPMulta.setITarifa(rset.getInt(5));
				vEXPMulta.setTRegistroPago(rset.getTimestamp(6));
				vEXPMulta.setCApPaterno(rset.getString(7));
				vEXPMulta.setCApMaterno(rset.getString(8));
				vEXPMulta.setCNombre(rset.getString(9));
				vEXPMulta.setCGenero(rset.getString(10));
				vEXPMulta.setCRFC(rset.getString(11));
				vEXPMulta.setCCURP(rset.getString(12));
				vcEXPMulta.addElement(vEXPMulta);

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

				if (rsetPEM != null) {
					rsetPEM.close();
				}
				if (pstmtPEM != null) {
					pstmtPEM.close();
				}

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (final Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcEXPMulta;
		}
	}	
	
	


	/**
	 * Metodo Find By All
	 */
	@SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	public Vector FindByAllHist(String cCondicion)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		Vector vcEXPMulta = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPMulta vEXPMulta;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT "+
						"M.ICVEEXPEDIENTE, "+ 
						"M.ICVEMULTA,  "+
						"M.TREALIZOPAGO,  "+
						"M.ICANTIDAD,  "+
						"M.ITARIFA,  "+
						"M.TREGISTROPAGO, "+
						"P.CAPPATERNO, "+
						"P.CAPMATERNO, "+
						"P.CNOMBRE, "+
						"P.CSEXO, "+
						"P.CRFC, "+
						"P.CCURP "+
					"FROM  "+
						"EXPMULTAS AS M, "+
						"PERDATOS AS P "+
					"WHERE "+
						"M.ICVEEXPEDIENTE = P.ICVEEXPEDIENTE AND "+
						"M.ICVEEXPEDIENTE = ?";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, Integer.parseInt(cCondicion));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPMulta = new TVEXPMulta();
				vEXPMulta.setICvePersonal(rset.getInt(1));
				vEXPMulta.setICveMulta(rset.getInt(2));
				vEXPMulta.setTRealizoPago(rset.getTimestamp(3));
				vEXPMulta.setICantidad(rset.getInt(4));
				vEXPMulta.setITarifa(rset.getInt(5));
				vEXPMulta.setTRegistroPago(rset.getTimestamp(6));
				vEXPMulta.setCApPaterno(rset.getString(7));
				vEXPMulta.setCApMaterno(rset.getString(8));
				vEXPMulta.setCNombre(rset.getString(9));
				vEXPMulta.setCGenero(rset.getString(10));
				vEXPMulta.setCRFC(rset.getString(11));
				vEXPMulta.setCCURP(rset.getString(12));
				vcEXPMulta.addElement(vEXPMulta);

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

				if (rsetPEM != null) {
					rsetPEM.close();
				}
				if (pstmtPEM != null) {
					pstmtPEM.close();
				}

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (final Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcEXPMulta;
		}
	}	
	

	/*
	 * Metodo Find By All
	 */
	@SuppressWarnings("finally")
	public boolean RealizoPagoPorMulta(int iCveExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean RealizoPagoPorMulta = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "	SELECT "
					+ "		A.ICVEEXPEDIENTE, "
					+ "		DATE(B.TREALIZOPAGO), "
					+ "		A.DTDICTAMINADO "
					+ "FROM "
					+ "		EXPEXAMCAT AS A,"
					+ "		EXPMULTAS AS B "
					+ "WHERE"
					+ "		A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE AND"
					+ "		B.ICVEMULTA = (SELECT MAX(C.ICVEMULTA) FROM EXPMULTAS AS C WHERE C.ICVEEXPEDIENTE = A.ICVEEXPEDIENTE) AND"
					+ "		A.INUMEXAMEN = (SELECT MAX(D.INUMEXAMEN) FROM EXPEXAMCAT AS D WHERE D.ICVEEXPEDIENTE = A.ICVEEXPEDIENTE) AND"
					+ "		DATE(B.TREALIZOPAGO) > A.DTDICTAMINADO AND"
					+ "		A.ICVEEXPEDIENTE = ?";

			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iCveExpediente);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				RealizoPagoPorMulta =  true;
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
			return RealizoPagoPorMulta;
		}
	}

	/*
	 * Metodo Find By All
	 */
	@SuppressWarnings("finally")
	public int MaximoMulta(int iCveExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int MaximoMulta = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "	SELECT "
					+ "		MAX(ICVEMULTA) "
					+ "FROM "
					+ "		EXPMULTAS "
					+ "WHERE "
					+ "		ICVEEXPEDIENTE = ?";

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iCveExpediente);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				MaximoMulta =  rset.getInt(1);
			}
			System.out.println("MaximoMulta ="+MaximoMulta);
			
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
			return MaximoMulta;
		}
	}

	
}

