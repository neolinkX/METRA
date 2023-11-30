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
 * Title: Data Acces Object de MEDAReg DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * 
 * @author AG SA
 * @version 1.0
 */

public class TDMEDAReg extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDAReg() {
	}

	/*
	 * Método Find By All
	 */
	public Vector FindByAllDet(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDAReg = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT M.ICVEREGLA, M.ICVEACCION, M.SERINTERCON, "
					+ " M.ICVEUSUGENERA, M.DTGENERADO, S.CDSCSERVICIO 	"
					+ " FROM MEDAREG AS M, MEDSERVICIO AS S	" + cWhere
					+ " M.SERINTERCON = S.ICVESERVICIO ";

			// System.out.println("MEDAReg--det = "+cSQL +"  **** ");

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDAReg = new TVMEDAReg();
				vMEDAReg.setICveRegla(rset.getInt(1));
				vMEDAReg.setICveAccion(rset.getInt(2));
				vMEDAReg.setServInterCon(rset.getInt(3));
				vMEDAReg.setICveUsugenera(rset.getInt(4));
				vMEDAReg.setDtGenerado(rset.getTimestamp(5));

				vcMEDAReg.addElement(vMEDAReg);
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
			return vcMEDAReg;
		}
	}

	/*
	 * Método Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDAReg = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = " SELECT R.ICVESINTOMA, A.SINTOMADEP,   R.IIGUALA, A.ICVEACCION, M.ICVETPORESP "
					+ "FROM MEDREGSIN AS R, MEDAREG AS A, EXPRESULTADO AS T, MEDSINTOMAS AS M, MEDSINTOMAS AS MM    "
					+ "WHERE              "
					+ "R.ICVESERVICIO = A.ICVESERVICIO AND            "
					+ "R.ICVERAMA = A.ICVERAMA AND                 "
					+ "R.ICVESINTOMA = A.ICVESINTOMA AND  "
					+ "R.ICVESERVICIO = T.ICVESERVICIO AND            "
					+ "R.ICVERAMA = T.ICVERAMA AND                 "
					+ "R.ICVESINTOMA = T.ICVESINTOMA AND  "
					+ "R.ICVESERVICIO = M.ICVESERVICIO AND            "
					+ "R.ICVERAMA = M.ICVERAMA AND                 "
					+ "A.SINTOMADEP = M.ICVESINTOMA AND  "
					+ "M.LACTIVO = 1 AND   "
					+ "R.ICVESERVICIO = MM.ICVESERVICIO AND            "
					+ "R.ICVERAMA = MM.ICVERAMA AND  "
					+ "A.ICVESINTOMA = MM.ICVESINTOMA AND    "
					+ "MM.LACTIVO = 1 AND "
					+ "R.LDEDPENDIENTE = 1 AND     "
					+ cWhere;

			// System.out.println("REglas ==========\n" +cSQL+
			// "\n=======================");
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDAReg = new TVMEDAReg();
				vMEDAReg.setICveSintoma(rset.getInt(1));
				vMEDAReg.setICveSintomaDep(rset.getInt(2));
				vMEDAReg.setIIgualA((int) rset.getFloat(3));
				vMEDAReg.setICveAccion(rset.getInt(4));
				vMEDAReg.setICveTpoResp(rset.getInt(5));
				vcMEDAReg.addElement(vMEDAReg);
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
			return vcMEDAReg;
		}
	}

	/*
	 * Método Find By All
	 */
	public Vector FindByAll2(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDAReg = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "SELECT R.ICVESINTOMA, A.SINTOMADEP,   R.IIGUALA, " +
					//"A.ICVEACCION, " + Se omitio este campo para simplificar la consulta / 16 de julio 2015
					"M.ICVETPORESP, R.IMAYORA, R.IMENORA "
					+ "  FROM MEDREGSIN AS R, MEDAREG AS A, MEDSINTEXAMENTMP AS T, MEDSINTOMAS AS M, MEDSINTOMAS AS MM    "
					+ "     WHERE              "
					+ "               R.ICVESERVICIO = A.ICVESERVICIO AND            "
					+ "               R.ICVERAMA = A.ICVERAMA AND                 "
					+ "               R.ICVESINTOMA = A.ICVESINTOMA AND  "
					+ "               R.ICVESERVICIO = T.ICVESERVICIO AND            "
					+ "               R.ICVERAMA = T.ICVERAMA AND                 "
					+ "               R.ICVESINTOMA = T.ICVESINTOMA AND  "
					+ "               R.ICVEMDOTRANS = T.ICVEMDOTRANS AND "
					+ "               R.ICVESERVICIO = M.ICVESERVICIO AND            "
					+ "               R.ICVERAMA = M.ICVERAMA AND                 "
					+ "               A.SINTOMADEP = M.ICVESINTOMA AND  "
					+ "               M.LACTIVO = 1 AND   "
					+ "               R.ICVESERVICIO = MM.ICVESERVICIO AND           "
					+ "               R.ICVERAMA = MM.ICVERAMA AND "
					+ "               A.ICVESINTOMA = MM.ICVESINTOMA AND    "
					+ "               MM.LACTIVO = 1 AND "
					+ "               R.LDEDPENDIENTE = 1 AND "
					+ cWhere
					+ " "
					+ "GROUP BY R.ICVESINTOMA, A.SINTOMADEP,   R.IIGUALA, R.IMAYORA, R.IMENORA, " +
					//"A.ICVEACCION, " + Se omitio este campo para simplificar la consulta / 16 de julio 2015
					"M.ICVETPORESP " +
					"ORDER BY R.ICVESINTOMA ASC";

			 //System.out.println("REglas nuevo metodo ==========\n" +cSQL+"\n=======================");
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDAReg = new TVMEDAReg();
				vMEDAReg.setICveSintoma(rset.getInt(1));
				vMEDAReg.setICveSintomaDep(rset.getInt(2));
				vMEDAReg.setIIgualA((int) rset.getFloat(3));
				//vMEDAReg.setICveAccion(rset.getInt(4));Se omitio este campo para simplificar la consulta / 16 de julio 2015
				vMEDAReg.setICveTpoResp(rset.getInt(4));
				vMEDAReg.setIMayorA(rset.getInt(5));
				vMEDAReg.setIMenorA(rset.getInt(6));
				vMEDAReg.setFIgualA(rset.getFloat(3));
				vMEDAReg.setFMayorA(rset.getFloat(5));
				vMEDAReg.setFMenorA(rset.getFloat(6));
				vcMEDAReg.addElement(vMEDAReg);
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
			return vcMEDAReg;
		}
	}
	

	/*
	 * Método Find By All
	 */
	public String FindCondicionDependientes(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String vcMEDAReg = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "SELECT R.ICVESINTOMA, A.SINTOMADEP,   R.IIGUALA, A.ICVEACCION, M.ICVETPORESP "
					+ "  FROM MEDREGSIN AS R, MEDAREG AS A, MEDSINTEXAMENTMP AS T, MEDSINTOMAS AS M, MEDSINTOMAS AS MM    "
					+ "     WHERE "
					+ "               R.ICVESERVICIO = A.ICVESERVICIO AND  "
					+ "               R.ICVERAMA = A.ICVERAMA AND  "
					+ "               R.ICVESINTOMA = A.ICVESINTOMA AND "
					+ "               R.ICVESERVICIO = T.ICVESERVICIO AND  "
					+ "               R.ICVERAMA = T.ICVERAMA AND   "
					+ "               R.ICVESINTOMA = T.ICVESINTOMA AND  "
					+ "               R.ICVEMDOTRANS = T.ICVEMDOTRANS AND "
					+ "               R.ICVESERVICIO = M.ICVESERVICIO AND            "
					+ "               R.ICVERAMA = M.ICVERAMA AND                 "
					+ "               A.SINTOMADEP = M.ICVESINTOMA AND  "
					+ "               M.LACTIVO = 1 AND   "
					+ "               R.ICVESERVICIO = MM.ICVESERVICIO AND           "
					+ "               R.ICVERAMA = MM.ICVERAMA AND "
					+ "               A.ICVESINTOMA = MM.ICVESINTOMA AND    "
					+ "               MM.LACTIVO = 1 AND "
					+ "               R.LDEDPENDIENTE = 1 AND "
					+ cWhere
					+ " "
					+ "GROUP BY R.ICVESINTOMA, A.SINTOMADEP,   R.IIGUALA, A.ICVEACCION, M.ICVETPORESP ";

			 //System.out.println("REglas nuevo metodo ==========\n" +cSQL+
			 //"\n=======================");
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDAReg = new TVMEDAReg();
				vMEDAReg.setICveSintoma(rset.getInt(1));
				vMEDAReg.setICveSintomaDep(rset.getInt(2));
				vMEDAReg.setIIgualA((int) rset.getFloat(3));
				vMEDAReg.setICveAccion(rset.getInt(4));
				vMEDAReg.setICveTpoResp(rset.getInt(5));
				//vcMEDAReg.addElement(vMEDAReg);
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
			return vcMEDAReg;
		}
	}	

	/**
	 * Método Insert - Acciones
	 */
	// public Object insert(Connection conGral, Object obj) throws DAOException
	// {
	public Object insert(Connection conGral, String obj, int regla, Object obj2)
			throws DAOException {

		// System.out.println("Entrando en el insert MedAReg");

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		PreparedStatement pstmtMax2 = null;
		ResultSet rsetMax2 = null;
		String cClave = "";
		int iMax = 0;
		int iMax2 = 0;
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}

			String cSQL = "";
			TVMEDREGSIN vMEDREGSIN = (TVMEDREGSIN) obj2;
			Vector Servicios = new Vector();
			String Resultado = "";
			for (int a = 0; a < obj.length(); a++) {
				if (obj.charAt(a) == '/') {
					Servicios.addElement(Resultado);
					Resultado = "";
				} else {
					Resultado = "" + Resultado + obj.charAt(a);
				}
			}

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// /Verificamos si hay datos ya agregados.
			String cSQLMax2 = "select max(iCveAccion) from MEDAREG"
					+ " Where iCveRegla = " + regla + " and "
					+ " ICVESERVICIO = " + vMEDREGSIN.getICveServicio()
					+ " AND " + " ICVERAMA = " + vMEDREGSIN.getICveRama()
					+ " AND " + " ICVESINTOMA = " + vMEDREGSIN.getICveSintoma()
					+ "";

			// System.out.println(cSQLMax2);

			pstmtMax2 = conn.prepareStatement(cSQLMax2);
			rsetMax2 = pstmtMax2.executeQuery();
			while (rsetMax2.next()) {
				iMax2 = rsetMax2.getInt(1);
			}
			rsetMax2.close();
			pstmtMax2.close();
			iMax2 = iMax2 + 1;

			// System.out.println("iMax2 = "+iMax2);

			if (Servicios.size() > 0)
				for (int b = 0; b < Servicios.size(); b++) {
					String Serv = (String) Servicios.elementAt(b);

					cSQL = "INSERT INTO MEDAReg	(ICVESERVICIO, " + "ICVERAMA, "
							+ "ICVESINTOMA," + "ICVEREGLA, " + "ICVEACCION, 	"
							+ "SERINTERCON, " + "ICVEUSUGENERA, 	"
							+ "DTGENERADO)   " + "VALUES(?,?,?,?,?,?,?,?)";

					// System.out.println(cSQL);

					pstmt = conn.prepareStatement(cSQL);

					// Calcular Timestamp para el campo TINIEXA
					java.util.Date utilDate = new java.util.Date(); // fecha
																	// actual
					long lnMilisegundos = utilDate.getTime();
					java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
							lnMilisegundos);

					// ///////////////////////////////////
					pstmt.setInt(1, vMEDREGSIN.getICveServicio());
					pstmt.setInt(2, vMEDREGSIN.getICveRama());
					pstmt.setInt(3, vMEDREGSIN.getICveSintoma());
					pstmt.setInt(4, regla);
					pstmt.setInt(5, iMax2 + b);
					pstmt.setInt(6, Integer.parseInt(Serv));
					pstmt.setInt(7, vMEDREGSIN.getICveUsugenera());// REGLA
					pstmt.setTimestamp(8, sqlTimestamp);
					// pstmt.setInt(9, vMEDREGSIN.getSintomaDep());//Dependiente

					// System.out.println(vMEDREGSIN.getICveServicio());
					// System.out.println(vMEDREGSIN.getICveRama());
					// System.out.println( vMEDREGSIN.getICveSintoma());

					pstmt.executeUpdate();
					cClave = "" + iMax;
					if (conGral == null) {
						conn.commit();
					}

				}// Extraccion Vector

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
	 * Método Insert - Dependencia
	 */
	// public Object insert(Connection conGral, Object obj) throws DAOException
	// {
	public Object insertDep(Connection conGral, int regla, Object obj2)
			throws DAOException {

		// System.out.println("Entrando en el insert de Dependencia MedAReg");

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		PreparedStatement pstmtMax2 = null;
		ResultSet rsetMax2 = null;
		String cClave = "";
		int iMax = 0;
		int iMax2 = 0;
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}

			String cSQL = "";
			TVMEDREGSIN vMEDREGSIN = (TVMEDREGSIN) obj2;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// /Verificamos si hay datos ya agregados.
			String cSQLMax2 = "select max(iCveAccion) from MEDAREG"
					+ " Where iCveRegla = " + regla;

			// System.out.println(cSQLMax2);

			pstmtMax2 = conn.prepareStatement(cSQLMax2);
			rsetMax2 = pstmtMax2.executeQuery();
			while (rsetMax2.next()) {
				iMax2 = rsetMax2.getInt(1);
			}
			rsetMax2.close();
			pstmtMax2.close();
			iMax2 = iMax2 + 1;

			// System.out.println("iMax2 = "+iMax2);

			cSQL = "INSERT INTO MEDAReg	(ICVESERVICIO, " + "ICVERAMA, "
					+ "ICVESINTOMA," + "ICVEREGLA, " + "ICVEACCION, 	"
					+ "SERINTERCON, " + "ICVEUSUGENERA, 	" + "DTGENERADO,   "
					+ "SINTOMADEP)   " + "VALUES(?,?,?,?,?,?,?,?,?)";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			// ///////////////////////////////////
			pstmt.setInt(1, vMEDREGSIN.getICveServicio());
			pstmt.setInt(2, vMEDREGSIN.getICveRama());
			pstmt.setInt(3, vMEDREGSIN.getICveSintoma());
			pstmt.setInt(4, regla);
			pstmt.setInt(5, iMax2);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, vMEDREGSIN.getICveUsugenera());// REGLA
			pstmt.setTimestamp(8, sqlTimestamp);
			pstmt.setInt(9, vMEDREGSIN.getSintomaDep());// Dependiente

			// System.out.println(vMEDREGSIN.getICveServicio());
			// System.out.println(vMEDREGSIN.getICveRama());
			// System.out.println( vMEDREGSIN.getICveSintoma());

			pstmt.executeUpdate();
			cClave = "" + iMax;
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
	 * MÃ©todo Find By All
	 */
	public String SenFindBy(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDictamenServ = new Vector();
		String Regresa = "";
		int count = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPDictamenServ vEXPDictamenServ;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = cWhere;
			
			System.out.println("123456 SenFindBy = "+cSQL);
			
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Regresa = rset.getString(1);
				if (count == 0) {
					Regresa = rset.getString(1);
				} else {
					Regresa = Regresa + "," + rset.getString(1);
				}
				count++;
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
			return Regresa;
		}
	}

	/**
	 * M�todo Delete
	 */
	public Object delete(Connection conGral, int iCveServicio, int iCveRama,
			int iCveSintoma, String cWhere,  int usr) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		boolean bitacora = false;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			

			bitacora= this.insertBitacora(conGral, null, iCveServicio, iCveRama,
					iCveSintoma,cWhere, usr);
			
			if(bitacora){
				String cSQL = "";
				cSQL = "delete from MEDAREG" + " WHERE iCveServicio = ?"
						+ " And iCveRama = ?" + " And iCveSintoma = ? " + cWhere;
				/*System.out.println(cSQL);
				System.out.println(iCveServicio);
				System.out.println(iCveRama);
				System.out.println(iCveSintoma);*/
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, iCveServicio);
				pstmt.setInt(2, iCveRama);
				pstmt.setInt(3, iCveSintoma);
				pstmt.executeUpdate();
				cClave = "";
				if (conGral == null) {
					conn.commit();
				}
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("delete.closeMEDREGSIN", ex2);
			}
			return cClave;
		}
	}

	/*
	 * Método Find By All para obtener solo servicio dependiente y de cual depende
	 */
	public Vector FindByAll3(int Servicio , int Rama) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector<TVMEDAReg> vcMEDAReg = new Vector<TVMEDAReg>();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "SELECT R.ICVESINTOMA, A.SINTOMADEP " +
					"FROM MEDREGSIN AS R, " +
					"MEDAREG AS A, MEDSINTOMAS AS M, MEDSINTOMAS AS MM " +
					"WHERE                             " +
					"R.ICVESERVICIO = A.ICVESERVICIO AND " +
					"R.ICVERAMA = A.ICVERAMA AND " +
					"R.ICVESINTOMA = A.ICVESINTOMA AND " +
					"R.ICVESERVICIO = M.ICVESERVICIO AND " +
					"R.ICVERAMA = M.ICVERAMA AND " +
					"A.SINTOMADEP = M.ICVESINTOMA AND " +
					"M.LACTIVO = 1 AND " +
					"R.ICVESERVICIO = MM.ICVESERVICIO AND " +
					"R.ICVERAMA = MM.ICVERAMA AND " +
					"A.ICVESINTOMA = MM.ICVESINTOMA AND " +
					"MM.LACTIVO = 1 AND " +
					"R.LDEDPENDIENTE = 1 AND " +
					"M.ICVESERVICIO = "+Servicio+" AND " +
					"M.ICVERAMA = "+Rama+" " +
					"GROUP BY R.ICVESINTOMA, A.SINTOMADEP";

			 /*System.out.println("REglas nuevo metodo ==========\n" +cSQL+
			 "\n=======================");*/
			pstmt = conn.prepareStatement(cSQL);
			//System.out.println("Preparando");
			rset = pstmt.executeQuery();
			//System.out.println("ejecutado");
			while (rset.next()) {
				//System.out.println("iterando");
				//System.out.println(rset.getInt(1));
				vMEDAReg = new TVMEDAReg();
				vMEDAReg.setICveSintoma(rset.getInt(1));
				vMEDAReg.setICveSintomaDep(rset.getInt(2));
				vcMEDAReg.addElement(vMEDAReg);
			}
			 //System.out.println("Teminado metodo nuevas reglas");
			 //System.out.println(vcMEDAReg.size());
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
		}
		return vcMEDAReg;
	}	
	
	
	/**
	 * M??todo Insert - NO BORRAR JMG
	 */
	public boolean insertBitacora(Connection conGral, Object obj,int iCveServicio, int iCveRama,
			int iCveSintoma, String cWhere, int usr)
			throws DAOException {
	
		// System.out.println("Entrando en el insert");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		PreparedStatement pstmtMax2 = null;
		ResultSet rsetMax2 = null;
		String cClave = "";
		int iMax = 0;
		int iMax2 = 0;
		boolean exito = false;
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		Vector vcMEDAREG = new Vector();
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
	
			String cSQL = "";
			TVMEDAReg vMEDAReg = null;;
			TDMEDAReg dMEDAReg = new TDMEDAReg();
	
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQLMax2 = "SELECT ICVEREGLA,ICVEACCION,SERINTERCON,ICVEUSUGENERA," 
					+ "DTGENERADO,ICVESERVICIO,ICVERAMA,ICVESINTOMA,SINTOMADEP " 
					+ "FROM MEDAREG WHERE iCveServicio = ?"
					+ " And iCveRama = ?" + " And iCveSintoma = ? " + cWhere;
			
			
			System.out.println(cSQLMax2);
			System.out.println(iCveServicio);
			System.out.println(iCveRama);
			System.out.println(iCveSintoma);
			
			pstmtMax2 = conn.prepareStatement(cSQLMax2);
			pstmtMax2.setInt(1,iCveServicio);
			pstmtMax2.setInt(2,iCveRama);
			pstmtMax2.setInt(3,iCveSintoma);
			rsetMax2 = pstmtMax2.executeQuery();
			while (rsetMax2.next()) {
				vMEDAReg = new TVMEDAReg();
				vMEDAReg.setICveRegla(rsetMax2.getInt(1));
				vMEDAReg.setICveAccion(rsetMax2.getInt(2));
				vMEDAReg.setServInterCon(rsetMax2.getInt(3));
				vMEDAReg.setICveUsugenera(rsetMax2.getInt(4));
				vMEDAReg.setDtGenerado(rsetMax2.getTimestamp(5));
				vMEDAReg.setICveServicio(rsetMax2.getInt(6));
				vMEDAReg.setICveRama(rsetMax2.getInt(7));
				vMEDAReg.setICveSintoma(rsetMax2.getInt(8));
				vMEDAReg.setICveSintomaDep(rsetMax2.getInt(9));
				vcMEDAREG.addElement(vMEDAReg);
			}
			rsetMax2.close();
			pstmtMax2.close();
			/*
			 * if(iMax2 < 1) iMax2 = 0;
			 */
			
			
			for(int i=0;i<vcMEDAREG.size();i++){
				vMEDAReg = (TVMEDAReg) vcMEDAREG.get(i);
		
				cSQL = "INSERT INTO MEDARegBitacora	(ICVESERVICIO, " + "ICVERAMA, "
						+ "ICVESINTOMA," + "ICVEREGLA, " + "ICVEACCION, 	"
						+ "SERINTERCON, " + "ICVEUSUGENERA, 	"
						+ "DTGENERADO, SINTOMADEP, DTELIMINADO, ICVEUSUELIMINA)   " 
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				
				 System.out.println(cSQL);
				pstmt = conn.prepareStatement(cSQL);
	
				int dependiente = 0;
				if(vMEDAReg.getICveSintomaDep() > 0){
					dependiente = vMEDAReg.getICveSintomaDep();
				}
				
				// Calcular Timestamp para el campo TINIEXA
				java.util.Date utilDate = new java.util.Date(); // fecha actual
				long lnMilisegundos = utilDate.getTime();
				java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
						lnMilisegundos);
				 System.out.println("sql.Timestamp: "+sqlTimestamp);
				 System.out.println( vMEDAReg.getICveServicio());
				 System.out.println( vMEDAReg.getICveRama());
				 System.out.println( vMEDAReg.getICveSintoma());
				 System.out.println( vMEDAReg.getICveRegla());// REGLA
				 System.out.println( vMEDAReg.getICveAccion());
				 System.out.println( vMEDAReg.getServInterCon());
				 System.out.println( vMEDAReg.getICveUsugenera());
				 System.out.println( vMEDAReg.getDtGenerado());
				 System.out.println( dependiente);
				 System.out.println( sqlTimestamp);
				 System.out.println(usr);

				 
				 
				pstmt.setInt(1, vMEDAReg.getICveServicio());
				pstmt.setInt(2, vMEDAReg.getICveRama());
				pstmt.setInt(3, vMEDAReg.getICveSintoma());
				pstmt.setInt(4, vMEDAReg.getICveRegla());// REGLA
				pstmt.setInt(5, vMEDAReg.getICveAccion());
				pstmt.setInt(6, vMEDAReg.getServInterCon());
				pstmt.setInt(7, vMEDAReg.getICveUsugenera());
				pstmt.setTimestamp(8, vMEDAReg.getDtGenerado());
				pstmt.setInt(9, dependiente);
				pstmt.setTimestamp(10, sqlTimestamp);
				pstmt.setInt(11, usr);

				pstmt.executeUpdate();
				cClave = "" + iMax;
				if (conGral == null) {
					conn.commit();
				}
			}
			
			// dMEDAReg.insert(null, vMEDREGSIN.getCServicios(), iMax2, obj);
			exito = true;
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
			return exito;
		}
	}

}