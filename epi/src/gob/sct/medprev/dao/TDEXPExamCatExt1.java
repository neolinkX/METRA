package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.text.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;
import com.micper.util.TFechas;

/**
 * <p>
 * Title: Data Acces Object de EXPExamCat DAO
 * </p>
 * <p>
 * Description: Data Access Object para EXPExamCat
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */

public class TDEXPExamCatExt1 extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPExamCatExt1() {
	}

	/**
	 * Metodo FindByExpedienteExamen
	 */
	public String DepedientesSQL(String Expediente, String Examen)
			throws DAOException {
		System.out.println("DepedientesSQL");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamCat = new Vector();
		String Regresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			int contador = 0;
			TVEXPExamCat vEXPExamCat;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT ICVEMOTIVO, ICVEMDOTRANS, ICVECATEGORIA " + "FROM EXPEXAMCAT "
					+ "WHERE " + "ICVEEXPEDIENTE = ? AND " + "INUMEXAMEN = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, Expediente);
			pstmt.setString(2, Examen);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if(contador == 0){
					Regresa = "	(T.ICVEMOTIVO = " + rset.getString(1) + " AND " +
							"	T.ICVEMDOTRANS = " + rset.getString(2)
						+ " AND	R.ICVECATEGORIA = " + rset.getString(3)+") ";
				}else{
					Regresa = Regresa + " OR	(T.ICVEMOTIVO = " + rset.getString(1) + " AND " +
							" T.ICVEMDOTRANS = " + rset.getString(2)
							+ " AND	R.ICVECATEGORIA = " + rset.getString(3)+") ";
				}
				contador++;
			}
			if(contador > 1){
				Regresa = "("+Regresa+")"; 
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
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByExpedienteExamen.close", ex2);
			}
			return Regresa;
		}
	}

	/*
	 * Metodo Find By All
	 */
	public int FindByInt(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDAReg = new Vector();
		int resultado = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				resultado = rset.getInt(1);
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
			return resultado;
		}
	}

	

	/*
	 * Metodo Find By All
	 */
	@SuppressWarnings("finally")
	public boolean MultaRevaloracion(int iCveExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int NumeroDeDiasDeLaNoAptitud = 0;
		int Dictamen = 0;
		boolean AmeritaMulta = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "	SELECT A.ICVEEXPEDIENTE, "
					+ "		A.INUMEXAMEN, "
					+ "		A.LDICTAMEN, "
					+ "		DAYS(CURRENT DATE) - DAYS(A.DTDICTAMINADO) "
					+ "FROM "
					+ "		EXPEXAMCAT AS A, "
					+ "		EXPEXAMAPLICA AS B "
					+ "WHERE	"
					+ "		A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE AND"
					+ "		A.INUMEXAMEN = B.INUMEXAMEN AND"
					+ "		A.ICVEEXPEDIENTE = ? AND"
					+ "		A.INUMEXAMEN = (SELECT MAX(INUMEXAMEN) FROM EXPEXAMCAT AS C WHERE C.ICVEEXPEDIENTE = A.ICVEEXPEDIENTE) AND"
					+ "		B.ICVEPROCESO IN(1,2)";

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iCveExpediente);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Dictamen = rset.getInt(3);
				NumeroDeDiasDeLaNoAptitud = rset.getInt(4);
			}
			
			//System.out.println("Dictamen = "+Dictamen);
			//System.out.println("NumeroDeDiasDeLaNoAptitud = "+NumeroDeDiasDeLaNoAptitud);
			if(Dictamen == 0 && NumeroDeDiasDeLaNoAptitud > 120){
				AmeritaMulta = true;
			}
			//System.out.println("AmeritaMulta = "+AmeritaMulta);
			
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
			return AmeritaMulta;
		}
	}
	

	
}
