/**
 * @author AG SA.
 * @version 1.0
 */

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
 * Title: Data Acces Object de MEDSintoma DAO
 * </p>
 * <p>
 * Description: DAO Consulta de configuracion de la rama
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

public class TDMEDREGSIN extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDREGSIN() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDREGSIN = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDREGSIN vMEDREGSIN;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT M.IMAYORA, M.IIGUALA, M.IMENORA, M.LOGICA,  "
					+ " M.LDICTAMENF, M.CALERTA, M.CDSCREGLA, M.LDEDPENDIENTE, M.ICVEREGLA, M.LACTIVO, T.CDSCMDOTRANS, C.CDSCCATEGORIA, S.ICVETPORESP,"
					+ " M.ICVEMDOTRANS, M.ICVECATEGORIA "
					+ " FROM	MEDREGSIN AS M, GRLMDOTRANS AS T, GRLCATEGORIA AS C, MEDSINTOMAS AS S  "
					+ " " + cWhere + "  AND  "
					+ " M.ICVEMDOTRANS = T.ICVEMDOTRANS AND	"
					+ " M.ICVEMDOTRANS = C.ICVEMDOTRANS AND	"
					+ " M.ICVECATEGORIA = C.ICVECATEGORIA AND		"
					+ " M.ICVESERVICIO = S.ICVESERVICIO AND		"
					+ " M.ICVERAMA = S.ICVERAMA AND		"
					+ " M.ICVESINTOMA = S.ICVESINTOMA "
					+ " ORDER BY M.ICVEREGLA";

			// System.out.println("MEDREGSIN = "+cSQL +"  **** ");

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDREGSIN = new TVMEDREGSIN();
				vMEDREGSIN.setIMayorA(new Double(rset.getDouble(1)));
				vMEDREGSIN.setIIgualA(new Double(rset.getDouble(2)));
				vMEDREGSIN.setIMenorA(new Double(rset.getDouble(3)));
				vMEDREGSIN.setLogica(rset.getInt(4));
				vMEDREGSIN.setLDictamenF(rset.getInt(5));
				vMEDREGSIN.setCAlerta(rset.getString(6));
				vMEDREGSIN.setCdscRegla(rset.getString(7));
				vMEDREGSIN.setLDependiente(rset.getInt(8));
				vMEDREGSIN.setICveRegla(rset.getInt(9));
				vMEDREGSIN.setLActivo(rset.getInt(10));
				vMEDREGSIN.setCMdoTrans(rset.getString(11));
				vMEDREGSIN.setCCategorias(rset.getString(12));
				vMEDREGSIN.setICveTpoResp(rset.getInt(13));
				vMEDREGSIN.setICveMdoTrans(rset.getInt(14));
				vMEDREGSIN.setICveCategoria(rset.getInt(15));
				vcMEDREGSIN.addElement(vMEDREGSIN);
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
			return vcMEDREGSIN;
		}
	}

	/**
	 * M??todo Find By All
	 */
	public Vector FindByAllDet(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDREGSIN = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDREGSIN vMEDREGSIN;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT S.IMAYORA, S.IIGUALA, S.IMENORA, S.LOGICA,  S.LDICTAMENF, S.CALERTA, "
					+ " S.CDSCREGLA, S.LDEDPENDIENTE, S.LACTIVO,  S.ICVESINTOMA, S.ICVEREGLA, M.CPREGUNTA, M.CDSCBREVE, "
					+ " T.CDSCMDOTRANS, C.CDSCCATEGORIA, M.ICVETPORESP "
					+ " FROM MEDREGSIN AS S, MEDSINTOMAS AS M,GRLMDOTRANS AS T, GRLCATEGORIA AS C   "
					+ " "
					+ cWhere
					+ " AND   S.ICVEMDOTRANS = T.ICVEMDOTRANS AND        "
					+ " S.ICVEMDOTRANS = C.ICVEMDOTRANS AND     "
					+ " S.ICVECATEGORIA = C.ICVECATEGORIA "
					+ " ORDER BY S.ICVEREGLA";

			// System.out.println("MEDREGSIN--det = "+cSQL +"  **** ");

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDREGSIN = new TVMEDREGSIN();
				vMEDREGSIN.setIMayorA(new Double(rset.getDouble(1)));
				vMEDREGSIN.setIIgualA(new Double(rset.getDouble(2)));
				vMEDREGSIN.setIMenorA(new Double(rset.getDouble(3)));
				vMEDREGSIN.setLogica(rset.getInt(4));
				vMEDREGSIN.setLDictamenF(rset.getInt(5));
				vMEDREGSIN.setCAlerta(rset.getString(6));
				vMEDREGSIN.setCdscRegla(rset.getString(7));
				vMEDREGSIN.setLDependiente(rset.getInt(8));
				vMEDREGSIN.setLActivo(rset.getInt(9));
				vMEDREGSIN.setICveSintoma(rset.getInt(10));
				vMEDREGSIN.setICveRegla(rset.getInt(11));
				vMEDREGSIN.setCPregunta(rset.getString(12));
				vMEDREGSIN.setCDscBreve(rset.getString(13));
				vMEDREGSIN.setCMdoTrans(rset.getString(14));
				vMEDREGSIN.setCCategorias(rset.getString(15));
				vMEDREGSIN.setICveTpoResp(rset.getInt(16));
				vcMEDREGSIN.addElement(vMEDREGSIN);
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
			return vcMEDREGSIN;
		}
	}

	/**
	 * M??todo Insert - NO BORRAR JMG
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {

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
			TVMEDREGSIN vMEDREGSIN = (TVMEDREGSIN) obj;
			TDMEDAReg dMEDAReg = new TDMEDAReg();
			Vector MdoTrans = new Vector();
			Vector Categorias = new Vector();
			String Resultado = "";
			for (int a = 0; a < vMEDREGSIN.getCCategorias().length(); a++) {
				if (vMEDREGSIN.getCCategorias().charAt(a) == '/'
						|| vMEDREGSIN.getCCategorias().charAt(a) == '-') {
					if (vMEDREGSIN.getCCategorias().charAt(a) == '-')
						MdoTrans.addElement(Resultado);
					if (vMEDREGSIN.getCCategorias().charAt(a) == '/')
						Categorias.addElement(Resultado);
					Resultado = "";
				} else {
					Resultado = "" + Resultado
							+ vMEDREGSIN.getCCategorias().charAt(a);
				}
			}

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// /Verificamos si hay datos ya agregados.
			String cSQLMax2 = "select max(iCveRegla) from MEDREGSIN"
					+ " Where iCveServicio = " + vMEDREGSIN.getICveServicio()
					+ " And iCveRama = " + vMEDREGSIN.getICveRama()
					+ " And iCveSintoma = " + vMEDREGSIN.getICveSintoma();

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
			/*
			 * if(iMax2 < 1) iMax2 = 0;
			 */
			if (Categorias.size() > 0)
				for (int b = 0; b < Categorias.size(); b++) {
					String Trans = (String) MdoTrans.elementAt(b);
					String Catego = (String) Categorias.elementAt(b);

					cSQL = "INSERT INTO MEDREGSIN	(ICVESERVICIO, "
							+ "ICVERAMA, 	" + "ICVESINTOMA, " + "ICVEREGLA, 	"
							+ "RDINFO, " + "RDACCION, " + "LACTIVO, "
							+ "IMAYORA, " + "IIGUALA, " + "IMENORA, "
							+ "LOGICA,     " + "LDICTAMENS, " + "LDICTAMENF, "
							+ "ICVEUSUGENERA, " + "DTGENERADO, " + "CALERTA, "
							+ "CDSCREGLA, " + "LDEDPENDIENTE, "
							+ "ICVEMDOTRANS, " + "ICVECATEGORIA)   "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					// System.out.println(cSQL);

					pstmt = conn.prepareStatement(cSQL);

					// Calcular Timestamp para el campo TINIEXA
					java.util.Date utilDate = new java.util.Date(); // fecha
																	// actual
					long lnMilisegundos = utilDate.getTime();
					java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
							lnMilisegundos);
					// System.out.println("sql.Timestamp: "+sqlTimestamp);
					pstmt.setInt(1, vMEDREGSIN.getICveServicio());
					pstmt.setInt(2, vMEDREGSIN.getICveRama());
					pstmt.setInt(3, vMEDREGSIN.getICveSintoma());
					pstmt.setInt(4, iMax2);// REGLA
					pstmt.setInt(5, vMEDREGSIN.getRdInfo());
					pstmt.setInt(6, vMEDREGSIN.getRdAccion());
					pstmt.setInt(7, vMEDREGSIN.getLActivo());
					pstmt.setDouble(8, vMEDREGSIN.getIMayorA());
					pstmt.setDouble(9, vMEDREGSIN.getIIgualA());
					pstmt.setDouble(10, vMEDREGSIN.getIMenorA());

					pstmt.setInt(11, vMEDREGSIN.getLogica());
					pstmt.setInt(12, vMEDREGSIN.getLDictamenS());
					pstmt.setInt(13, vMEDREGSIN.getLDictamenF());
					pstmt.setInt(14, vMEDREGSIN.getICveUsugenera());
					pstmt.setTimestamp(15, sqlTimestamp);
					pstmt.setString(16, vMEDREGSIN.getCAlerta());
					pstmt.setString(17, vMEDREGSIN.getCdscRegla());
					pstmt.setInt(18, vMEDREGSIN.getLDependiente());
					pstmt.setString(19, Trans);
					pstmt.setString(20, Catego);

					pstmt.executeUpdate();
					cClave = "" + iMax;
					if (conGral == null) {
						conn.commit();
					}
				}// Extraccion Vector

			dMEDAReg.insert(null, vMEDREGSIN.getCServicios(), iMax2, obj);

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
	 * M??todo Insert - NO BORRAR JMG
	 */
	public Object insertAlert(Connection conGral, Object obj)
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
			TVMEDREGSIN vMEDREGSIN = (TVMEDREGSIN) obj;
			TDMEDAReg dMEDAReg = new TDMEDAReg();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// /Verificamos si hay datos ya agregados.
			String cSQLMax2 = "select max(iCveRegla) from MEDREGSIN"
					+ " Where iCveServicio = " + vMEDREGSIN.getICveServicio()
					+ " And iCveRama = " + vMEDREGSIN.getICveRama()
					+ " And iCveSintoma = " + vMEDREGSIN.getICveSintoma();

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
			/*
			 * if(iMax2 < 1) iMax2 = 0;
			 */

			cSQL = "INSERT INTO MEDREGSIN	(ICVESERVICIO, " + "ICVERAMA, 	"
					+ "ICVESINTOMA, " + "ICVEREGLA, 	" + "RDINFO, "
					+ "RDACCION, " + "LACTIVO, " + "IMAYORA, " + "IIGUALA, "
					+ "IMENORA, " + "LOGICA,     " + "LDICTAMENS, "
					+ "LDICTAMENF, " + "ICVEUSUGENERA, " + "DTGENERADO, "
					+ "CALERTA, " + "CDSCREGLA, " + "LDEDPENDIENTE, "
					+ "ICVEMDOTRANS, " + "ICVECATEGORIA)   "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			// System.out.println("sql.Timestamp: "+sqlTimestamp);
			pstmt.setInt(1, vMEDREGSIN.getICveServicio());
			pstmt.setInt(2, vMEDREGSIN.getICveRama());
			pstmt.setInt(3, vMEDREGSIN.getICveSintoma());
			pstmt.setInt(4, iMax2);// REGLA
			pstmt.setInt(5, vMEDREGSIN.getRdInfo());
			pstmt.setInt(6, vMEDREGSIN.getRdAccion());
			pstmt.setInt(7, vMEDREGSIN.getLActivo());
			pstmt.setDouble(8, vMEDREGSIN.getIMayorA());
			pstmt.setDouble(9, vMEDREGSIN.getIIgualA());
			pstmt.setDouble(10, vMEDREGSIN.getIMenorA());

			pstmt.setInt(11, vMEDREGSIN.getLogica());
			pstmt.setInt(12, vMEDREGSIN.getLDictamenS());
			pstmt.setInt(13, vMEDREGSIN.getLDictamenF());
			pstmt.setInt(14, vMEDREGSIN.getICveUsugenera());
			pstmt.setTimestamp(15, sqlTimestamp);
			pstmt.setString(16, vMEDREGSIN.getCAlerta());
			pstmt.setString(17, vMEDREGSIN.getCdscRegla());
			pstmt.setInt(18, vMEDREGSIN.getLDependiente());
			pstmt.setInt(19, vMEDREGSIN.getICveMdoTrans());
			pstmt.setInt(20, vMEDREGSIN.getICveCategoria());

			pstmt.executeUpdate();
			cClave = "" + iMax;
			if (conGral == null) {
				conn.commit();
			}

			// dMEDAReg.insert(null, vMEDREGSIN.getCServicios(), iMax2, obj);

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
	 * Metodo Update
	 */
	public Object UpdateAlert(Connection conGral, Object obj)
			throws DAOException {
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
			TVMEDREGSIN vMEDREGSIN = (TVMEDREGSIN) obj;
			TDMEDAReg dMEDAReg = new TDMEDAReg();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "UPDATE MEDREGSIN SET ICVEREGLA = ?,"
					+ "LACTIVO = ?, "
					+ "IMAYORA= ?, "
					+ "IIGUALA = ?, "
					+ "IMENORA = ?, "
					+ "ICVEUSUGENERA = ?, "
					+ "DTGENERADO = ?, "
					+ "CALERTA = ?, "
					+ "CDSCREGLA = ? "
					+ "where ICVESERVICIO =? and ICVERAMA = ? and ICVESINTOMA = ?";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			// System.out.println("sql.Timestamp: "+sqlTimestamp);
			pstmt.setInt(1, vMEDREGSIN.getICveRegla());
			pstmt.setInt(2, vMEDREGSIN.getLActivo());
			pstmt.setDouble(3, vMEDREGSIN.getIMayorA());
			pstmt.setDouble(4, vMEDREGSIN.getIIgualA());
			pstmt.setDouble(5, vMEDREGSIN.getIMenorA());
			pstmt.setInt(6, vMEDREGSIN.getICveUsugenera());
			pstmt.setTimestamp(7, sqlTimestamp);
			pstmt.setString(8, vMEDREGSIN.getCAlerta());
			pstmt.setString(9, vMEDREGSIN.getCdscRegla());
			pstmt.setInt(10, vMEDREGSIN.getICveServicio());
			pstmt.setInt(11, vMEDREGSIN.getICveRama());
			pstmt.setInt(12, vMEDREGSIN.getICveSintoma());
			/*
			 * System.out.println(vMEDREGSIN.getICveRegla());
			 * System.out.println(vMEDREGSIN.getLActivo());
			 * System.out.println(vMEDREGSIN.getIMayorA()); System.out.println(
			 * vMEDREGSIN.getIIgualA()); System.out.println(
			 * vMEDREGSIN.getIMenorA()); System.out.println(
			 * vMEDREGSIN.getICveUsugenera()); System.out.println(
			 * sqlTimestamp); System.out.println( vMEDREGSIN.getCAlerta());
			 * System.out.println( vMEDREGSIN.getICveServicio());
			 * System.out.println( vMEDREGSIN.getICveRama());
			 * System.out.println( vMEDREGSIN.getICveSintoma());
			 */
			pstmt.executeUpdate();
			cClave = "" + iMax;
			if (conGral == null) {
				conn.commit();
			}

			// dMEDAReg.insert(null, vMEDREGSIN.getCServicios(), iMax2, obj);

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
	public Object Update(Connection conGral, Object obj)
			throws DAOException {
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
			TVMEDREGSIN vMEDREGSIN = (TVMEDREGSIN) obj;
			TDMEDAReg dMEDAReg = new TDMEDAReg();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "UPDATE MEDREGSIN SET LACTIVO = ?,  "
					+ "DTGENERADO = ? "				
					+ "where ICVESERVICIO =? and ICVERAMA = ? and ICVESINTOMA = ?"
					+ " AND ICVEMDOTRANS=? and ICVECATEGORIA = ?";
			

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			// System.out.println("sql.Timestamp: "+sqlTimestamp);			
			pstmt.setInt(1, vMEDREGSIN.getLActivo());			
			pstmt.setTimestamp(2, sqlTimestamp);
			pstmt.setInt(3, vMEDREGSIN.getICveServicio());
			pstmt.setInt(4, vMEDREGSIN.getICveRama());
			pstmt.setInt(5, vMEDREGSIN.getICveSintoma());
			pstmt.setInt(6, vMEDREGSIN.getICveMdoTrans());
			pstmt.setInt(7, vMEDREGSIN.getICveCategoria());
			  
			/* System.out.println(vMEDREGSIN.getLActivo());			  
			  System.out.println( vMEDREGSIN.getICveServicio()); 
			  System.out.println( vMEDREGSIN.getICveRama()); 
			  System.out.println( vMEDREGSIN.getICveSintoma());
			  System.out.println( vMEDREGSIN.getICveMdoTrans());
			  System.out.println( vMEDREGSIN.getICveCategoria()); 
			 */
			pstmt.executeUpdate();
			cClave = "" + iMax;
			if (conGral == null) {
				conn.commit();
			}

			// dMEDAReg.insert(null, vMEDREGSIN.getCServicios(), iMax2, obj);

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
	 * M??todo Insert - NO BORRAR JMG
	 */
	public Object insert2(Connection conGral, Object obj) throws DAOException {

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
			TVMEDREGSIN vMEDREGSIN = (TVMEDREGSIN) obj;
			TDMEDAReg dMEDAReg = new TDMEDAReg();
			Vector MdoTrans = new Vector();
			Vector Categorias = new Vector();
			String Resultado = "";
			for (int a = 0; a < vMEDREGSIN.getCCategorias().length(); a++) {
				if (vMEDREGSIN.getCCategorias().charAt(a) == '/'
						|| vMEDREGSIN.getCCategorias().charAt(a) == '-') {
					if (vMEDREGSIN.getCCategorias().charAt(a) == '-') {
						MdoTrans.addElement(Resultado);
					}
					if (vMEDREGSIN.getCCategorias().charAt(a) == '/') {
						Categorias.addElement(Resultado);
					}
					Resultado = "";
				} else {
					Resultado = "" + Resultado
							+ vMEDREGSIN.getCCategorias().charAt(a);
				}
			}

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// /Verificamos si hay datos ya agregados.
			String cSQLMax2 = "select max(iCveRegla) from MEDREGSIN"
					+ " Where iCveServicio = " + vMEDREGSIN.getICveServicio()
					+ " And iCveRama = " + vMEDREGSIN.getICveRama()
					+ " And iCveSintoma = " + vMEDREGSIN.getICveSintoma();

			// System.out.println(cSQLMax2);

			pstmtMax2 = conn.prepareStatement(cSQLMax2);
			rsetMax2 = pstmtMax2.executeQuery();
			while (rsetMax2.next()) {
				iMax2 = rsetMax2.getInt(1);
			}
			rsetMax2.close();
			pstmtMax2.close();
			iMax2 = iMax2 + 1;

			if (Categorias.size() > 0) {
				for (int b = 0; b < Categorias.size(); b++) {
					String Trans = (String) MdoTrans.elementAt(b);
					String Catego = (String) Categorias.elementAt(b);

					// System.out.println("Trans = " +Trans);
					// System.out.println("Catego = " +Catego);

					cSQL = "INSERT INTO MEDREGSIN	(ICVESERVICIO, "
							+ "ICVERAMA, 	" + "ICVESINTOMA, " + "ICVEREGLA, 	"
							+ "RDINFO, " + "RDACCION, " + "LACTIVO, "
							+ "IMAYORA, " + "IIGUALA, " + "IMENORA, "
							+ "LOGICA,     " + "LDICTAMENS, " + "LDICTAMENF, "
							+ "ICVEUSUGENERA, " + "DTGENERADO, " + "CALERTA, "
							+ "CDSCREGLA, " + "LDEDPENDIENTE, "
							+ "ICVEMDOTRANS, " + "ICVECATEGORIA)   "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					// System.out.println(cSQL);

					pstmt = conn.prepareStatement(cSQL);

					// Calcular Timestamp para el campo TINIEXA
					java.util.Date utilDate = new java.util.Date(); // fecha
																	// actual
					long lnMilisegundos = utilDate.getTime();
					java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
							lnMilisegundos);

					pstmt.setInt(1, vMEDREGSIN.getICveServicio());
					pstmt.setInt(2, vMEDREGSIN.getICveRama());
					pstmt.setInt(3, vMEDREGSIN.getICveSintoma());
					pstmt.setInt(4, iMax2);// REGLA
					pstmt.setInt(5, vMEDREGSIN.getRdInfo());
					pstmt.setInt(6, vMEDREGSIN.getRdAccion());
					pstmt.setInt(7, vMEDREGSIN.getLActivo());
					pstmt.setDouble(8, vMEDREGSIN.getIMayorA());
					pstmt.setDouble(9, vMEDREGSIN.getIIgualA());
					pstmt.setDouble(10, vMEDREGSIN.getIMenorA());

					pstmt.setInt(11, vMEDREGSIN.getLogica());
					pstmt.setInt(12, vMEDREGSIN.getLDictamenS());
					pstmt.setInt(13, vMEDREGSIN.getLDictamenF());
					pstmt.setInt(14, vMEDREGSIN.getICveUsugenera());
					pstmt.setTimestamp(15, sqlTimestamp);
					pstmt.setString(16, vMEDREGSIN.getCAlerta());
					pstmt.setString(17, vMEDREGSIN.getCdscRegla());
					pstmt.setInt(18, vMEDREGSIN.getLDependiente());
					pstmt.setString(19, Trans);
					pstmt.setString(20, Catego);

					pstmt.executeUpdate();
					cClave = "" + iMax;
					if (conGral == null) {
						conn.commit();
					}

					if (Categorias.size() == 1)
						b = b + 2;

				}// Extraccion Vector
			}

			dMEDAReg.insertDep(null, iMax2, obj);

			// System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%% Fin del insert %%%%%%%%%%%");
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

	/*
	 * M??todo Find By All
	 */
	public int FindByAllDep(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDAReg = new Vector();
		int dependiente = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT COUNT(LDEDPENDIENTE)               "
					+ "FROM MEDREGSIN 	" + "WHERE		"
					+ "LDEDPENDIENTE = 1 AND		" + cWhere;
			// + " R.ICVESERVICIO = 49 AND		"
			// + " R.ICVERAMA = 1 AND		"
			// + " R.ICVEMDOTRANS = 2 AND		"
			// + " R.ICVECATEGORIA = 7 ";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				dependiente = rset.getInt(1);
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
			return dependiente;
		}
	}

	/*
	 * M??todo Find By All
	 */
	public int FindByAllDep2(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDAReg = new Vector();
		int dependiente = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT COUNT(D.LDEDPENDIENTE)  "
					+ "              FROM MEDREGSIN AS D, EXPRESULTADO AS R	"
					+ "              WHERE		"
					+ "              D.ICVESERVICIO = R.ICVESERVICIO AND       "
					+ "              D.ICVERAMA = R.ICVERAMA AND		"
					+ "              D.ICVESINTOMA = R.ICVESINTOMA AND	      "
					+ "              D.LDEDPENDIENTE = 1 AND" + cWhere;

			// System.out.println("Dep = "+cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				dependiente = rset.getInt(1);
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
			return dependiente;
		}
	}

	/*
	 * M??todo Find By All
	 */
	public int FindByAllDep3(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDAReg = new Vector();
		int dependiente = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT COUNT(D.LDEDPENDIENTE)  "
					+ "                             FROM MEDREGSIN AS D, MEDSINTEXAMENTMP AS R	"
					+ "                             WHERE		"
					+ "                             D.ICVESERVICIO = R.ICVESERVICIO AND       "
					+ "                             D.ICVERAMA = R.ICVERAMA AND		"
					+ "                             D.ICVESINTOMA = R.ICVESINTOMA AND	  "
					+ "			     D.ICVEMDOTRANS = R.ICVEMDOTRANS AND "
					+ "			     D.ICVEMDOTRANS = R.ICVEMDOTRANS AND "
					+ "                             R.ICVEPROCESO = 1 AND "
					+ "                             D.LDEDPENDIENTE = 1 AND "
					+ cWhere;

			// System.out.println("Nuevo Metodo = "+cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				dependiente = rset.getInt(1);
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
			return dependiente;
		}
	}

	/*
	 * M??todo Find By All
	 */
	public int FindByAllDepEMO(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDAReg = new Vector();
		int dependiente = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT COUNT(D.LDEDPENDIENTE)  "
					+ "                             FROM MEDREGSIN AS D, MEDSINTEXAMENTMP AS R	"
					+ "                             WHERE		"
					+ "                             D.ICVESERVICIO = R.ICVESERVICIO AND       "
					+ "                             D.ICVERAMA = R.ICVERAMA AND		"
					+ "                             D.ICVESINTOMA = R.ICVESINTOMA AND	  "
					+ "			     D.ICVEMDOTRANS = R.ICVEMDOTRANS AND "
					+ "			     D.ICVEMDOTRANS = R.ICVEMDOTRANS AND "
					+ "                             R.ICVEPROCESO = 2 AND "
					+ "                             D.LDEDPENDIENTE = 1 AND "
					+ cWhere;

			// System.out.println("Nuevo Metodo = "+cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				dependiente = rset.getInt(1);
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
			return dependiente;
		}
	}

	/**
	 * Metodo Find By All Identifica las alertas de cada sintoma tiene para cada
	 * regla que se cumpla
	 */
	public String FindByAlReg(String cWhere, int Cat, int Atendido)
			throws DAOException {
		//System.out.println("Cat = "+Cat);
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		TDEXPRegSint dDatos = new TDEXPRegSint();
		String Respuesta = "";
		int count = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDRespSint vMEDRespSint;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			/*
			 * cSQL = " SELECT CALERTA " + "FROM MEDREGSIN " + "WHERE " +
			 * "RDACCION = 1 AND " + cWhere;
			 */
			cSQL = "   SELECT T.ICVEMDOTRANS, G.ICVECATEGORIA, T.CDSCMDOTRANS, G.CDSCCATEGORIA, M.CALERTA, "
					+ "M.LDICTAMENS, M.LDICTAMENF, M.ICVESERVICIO, M.ICVERAMA, M.ICVESINTOMA, C.ICVEEXPEDIENTE, C.INUMEXAMEN  "
					+ "FROM MEDREGSIN AS M, EXPEXAMCAT AS C, GRLMDOTRANS AS T, GRLCATEGORIA AS G "
					+ "WHERE "
					+ "M.RDACCION = 1 AND  "
					+ "M.LACTIVO = 1 AND "
					+ "M.ICVEMDOTRANS = C.ICVEMDOTRANS AND "
					+ "M.ICVECATEGORIA = C.ICVECATEGORIA AND "
					+ "M.ICVEMDOTRANS = T.ICVEMDOTRANS AND "
					+ "M.ICVEMDOTRANS = G.ICVEMDOTRANS AND "
					+ "M.ICVECATEGORIA = G.ICVECATEGORIA AND " + cWhere;

			 System.out.println("###########  ALERTA  ##########");
			 System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				if (Cat >= 1) {
					if (count == 0) {
						Respuesta = "<strong>" + rset.getString(3) + "/"
								+ rset.getString(4) + "</strong><br>"
								+ rset.getString(5);
					} else {
						Respuesta = Respuesta + "<br><br><strong>"
								+ rset.getString(3) + "/" + rset.getString(4)
								+ "</strong><br>" + rset.getString(5);
					}
					count++;
					// Validamos que la alerta no sea motivo de no APTITUD de un
					// servicio o dictamen final
					//System.out.println("rset.getInt(6) = "+rset.getInt(6) );
					//System.out.println("rset.getInt(7) = "+rset.getInt(7) );
					//System.out.println("Atendido = "+Atendido);
					
					if ((rset.getInt(6) == 1 || rset.getInt(7) == 1)
							&& Atendido == 0) {
						//System.out.println("$$$$$$$$$$$$$$$$   EJECUTANDO INSERT DE ALERTA S F    $$$$$$$$$$$$$$$$$$$$$$$$$");
						TVEXPRegSin vDatos = new TVEXPRegSin();
						vDatos.setICveExpediente(rset.getInt(11));
						vDatos.setINumExamen(rset.getInt(12));
						vDatos.setICveServicio(rset.getInt(8));
						vDatos.setICveRama(rset.getInt(9));
						vDatos.setICveSintoma(rset.getInt(10));
						vDatos.setICveMdoTrans(rset.getInt(1));
						vDatos.setICveCategoria(rset.getInt(2));
						vDatos.setLDictamenS(rset.getInt(6));
						vDatos.setLDictamenF(rset.getInt(7));
						try {
							dDatos.insert(null, vDatos);
						} catch (Exception ex) {
							warn("TDEXPRegSinT.Insert()", ex);
							throw new DAOException("TDEXPRegSinT.Insert()");
						}

					}
				} else {
					if (count == 0) {
						Respuesta = rset.getString(5);
					} else {
						Respuesta = Respuesta + "<br>" + rset.getString(5);
					}
					count++;
				}
			}
			//System.out.println("###########  FIN ALERTA  ##########");
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
			return Respuesta;
		}
	}

	/**
	 * Metodo Find By All Identifica el operador a asignar de acuerdo a las
	 * alertas que cada sintoma tiene para cada regla
	 */
	public int FindOp(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		TDEXPRegSint dDatos = new TDEXPRegSint();
		String Respuesta = "";
		int count = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDRespSint vMEDRespSint;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT R.IMAYORA, " + "R.IIGUALA," + "R.IMENORA "
					+ "FROM MEDREGSIN AS R, MEDSINTOMAS AS S  " + "WHERE "
					+ "R.ICVESERVICIO = S.ICVESERVICIO AND "
					+ "R.ICVERAMA = S.ICVERAMA AND "
					+ "R.ICVESINTOMA = S.ICVESINTOMA AND "
					+ "R.LACTIVO = 1 AND " + "S.LACTIVO = 1 AND " + cWhere
					// + "R.ICVESERVICIO = 49 AND "
					// + "R.ICVERAMA = 1 AND "
					// + "R.ICVESINTOMA = 7 "
					+ "GROUP BY R.IMAYORA, R.IIGUALA, R.IMENORA    ";

			 System.out.println("###########  OPERADOR  ##########");
			 System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				if (rset.getDouble(1) > 0.0)
					count = count + 1;
				if (rset.getDouble(2) > 0.0)
					count = count + 10;
				if (rset.getDouble(3) > 0.0)
					count = count + 100;
			}
			//System.out.println("count = "+count);
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
			return count;
		}
	}

	/**
	 * M�todo Delete
	 */
	public Object delete(Connection conGral, int iCveServicio, int iCveRama,
			int iCveSintoma, String cWhere, int usr) throws DAOException {
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
				cSQL = "delete from MEDREGSIN" + " WHERE iCveServicio = ?"
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

	/**
	 * Metodo Find By All que muestra las alertas informativas configuradas
	 */
	public Vector FindByAllAlInf(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDREGSIN = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDREGSIN vMEDREGSIN;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT M.IMAYORA, M.IIGUALA, M.IMENORA, M.LOGICA,  "
					+ " M.CALERTA, M.LACTIVO, S.ICVETPORESP, M.ICVEREGLA "
					+ " FROM	MEDREGSIN AS M,  MEDSINTOMAS AS S  " + " "
					+ cWhere + "  AND  "
					+ " M.ICVESERVICIO = S.ICVESERVICIO AND		"
					+ " M.ICVERAMA = S.ICVERAMA AND		"
					+ " M.ICVESINTOMA = S.ICVESINTOMA "
					+ " ORDER BY M.ICVEREGLA";

			// System.out.println("MEDREGSIN = "+cSQL +"  **** ");

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDREGSIN = new TVMEDREGSIN();
				vMEDREGSIN.setIMayorA(new Double(rset.getDouble(1)));
				vMEDREGSIN.setIIgualA(new Double(rset.getDouble(2)));
				vMEDREGSIN.setIMenorA(new Double(rset.getDouble(3)));
				vMEDREGSIN.setLogica(rset.getInt(4));
				vMEDREGSIN.setCAlerta(rset.getString(5));
				vMEDREGSIN.setLActivo(rset.getInt(6));
				vMEDREGSIN.setICveTpoResp(rset.getInt(7));
				vMEDREGSIN.setICveRegla(rset.getInt(8));
				vcMEDREGSIN.addElement(vMEDREGSIN);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAllAlInf");
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
			return vcMEDREGSIN;
		}
	}

	/**
	 * M??todo Find By All
	 */
	public Vector FindByAllDetAl(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDREGSIN = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDREGSIN vMEDREGSIN;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT S.IMAYORA, S.IIGUALA, S.IMENORA, S.LOGICA,  S.LDICTAMENF, S.CALERTA, "
					+ " S.CDSCREGLA, S.LDEDPENDIENTE, S.LACTIVO,  S.ICVESINTOMA, S.ICVEREGLA, M.CPREGUNTA, M.CDSCBREVE, "
					+ " M.ICVETPORESP, V.CDSCSERVICIO, R.CDSCRAMA, V.ICVESERVICIO, R.ICVERAMA "
					+ " FROM MEDREGSIN AS S, MEDSINTOMAS AS M, MEDSERVICIO AS V, MEDRAMA AS R     "
					+ " " + cWhere + " ORDER BY S.ICVEREGLA";
			System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDREGSIN = new TVMEDREGSIN();
				vMEDREGSIN.setIMayorA(new Double(rset.getDouble(1)));
				vMEDREGSIN.setIIgualA(new Double(rset.getDouble(2)));
				vMEDREGSIN.setIMenorA(new Double(rset.getDouble(3)));
				vMEDREGSIN.setLogica(rset.getInt(4));
				vMEDREGSIN.setLDictamenF(rset.getInt(5));
				vMEDREGSIN.setCAlerta(rset.getString(6));
				vMEDREGSIN.setCdscRegla(rset.getString(7));
				vMEDREGSIN.setLDependiente(rset.getInt(8));
				vMEDREGSIN.setLActivo(rset.getInt(9));
				vMEDREGSIN.setICveSintoma(rset.getInt(10));
				vMEDREGSIN.setICveRegla(rset.getInt(11));
				vMEDREGSIN.setCPregunta(rset.getString(12));
				vMEDREGSIN.setCDscBreve(rset.getString(13));
				vMEDREGSIN.setICveTpoResp(rset.getInt(14));
				vMEDREGSIN.setCDscServicio(rset.getString(15));
				vMEDREGSIN.setCDscRama(rset.getString(16));
				vMEDREGSIN.setICveServicio(rset.getInt(17));
				vMEDREGSIN.setICveRama(rset.getInt(18));
				vcMEDREGSIN.addElement(vMEDREGSIN);
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
			return vcMEDREGSIN;
		}
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
		Vector vcMEDREGSIN = new Vector();
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}

			String cSQL = "";
			//TVMEDREGSIN vMEDREGSIN = (TVMEDREGSIN) obj;
			TVMEDREGSIN vMEDREGSIN = null;;
			TDMEDAReg dMEDAReg = new TDMEDAReg();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			// /Verificamos si hay datos ya agregados.
			/*String cSQLMax2 = "select max(iCveRegla) from MEDREGSIN"
					+ " Where iCveServicio = " + vMEDREGSIN.getICveServicio()
					+ " And iCveRama = " + vMEDREGSIN.getICveRama()
					+ " And iCveSintoma = " + vMEDREGSIN.getICveSintoma();
*/
			String cSQLMax2 = "SELECT ICVESERVICIO, " + "ICVERAMA, 	"
					+ "ICVESINTOMA, " + "ICVEREGLA, 	" + "RDINFO, "
					+ "RDACCION, " + "LACTIVO, " + "IMAYORA, " + "IIGUALA, "
					+ "IMENORA, " + "LOGICA,     " + "LDICTAMENS, "
					+ "LDICTAMENF, " + "ICVEUSUGENERA, " + "DTGENERADO, "
					+ "CALERTA, " + "CDSCREGLA, " + "LDEDPENDIENTE, "
					+ "ICVEMDOTRANS, " + "ICVECATEGORIA FROM MEDREGSIN " 
					+ "WHERE iCveServicio = ?"
					+ " And iCveRama = ?" + " And iCveSintoma = ? " + cWhere;
			
			
			//System.out.println(cSQLMax2);
			pstmtMax2 = conn.prepareStatement(cSQLMax2);
			pstmtMax2.setInt(1,iCveServicio);
			pstmtMax2.setInt(2,iCveRama);
			pstmtMax2.setInt(3,iCveSintoma);
			rsetMax2 = pstmtMax2.executeQuery();
			while (rsetMax2.next()) {
				vMEDREGSIN = new TVMEDREGSIN();
				vMEDREGSIN.setICveServicio(rsetMax2.getInt(1));
				vMEDREGSIN.setICveRama(rsetMax2.getInt(2));
				vMEDREGSIN.setICveSintoma(rsetMax2.getInt(3));
				vMEDREGSIN.setICveRegla(rsetMax2.getInt(4));
				vMEDREGSIN.setRdInfo(rsetMax2.getInt(5));
				vMEDREGSIN.setRdAccion(rsetMax2.getInt(6));
				vMEDREGSIN.setLActivo(rsetMax2.getInt(7));
				vMEDREGSIN.setIMayorA(rsetMax2.getDouble(8));
				vMEDREGSIN.setIIgualA(rsetMax2.getDouble(9));
				vMEDREGSIN.setIMenorA(rsetMax2.getDouble(10));
				vMEDREGSIN.setLogica(rsetMax2.getInt(11));
				vMEDREGSIN.setLDictamenS(rsetMax2.getInt(12));
				vMEDREGSIN.setLDictamenF(rsetMax2.getInt(13));
				vMEDREGSIN.setICveUsugenera(rsetMax2.getInt(14));
				vMEDREGSIN.setDtGenerado(rsetMax2.getTimestamp(15));
				vMEDREGSIN.setCAlerta(rsetMax2.getString(16));
				vMEDREGSIN.setCdscRegla(rsetMax2.getString(17));
				vMEDREGSIN.setLDependiente(rsetMax2.getInt(18));
				vMEDREGSIN.setICveMdoTrans(rsetMax2.getInt(19));
				vMEDREGSIN.setICveCategoria(rsetMax2.getInt(20));
				vcMEDREGSIN.addElement(vMEDREGSIN);
			}
			rsetMax2.close();
			pstmtMax2.close();
			/*
			 * if(iMax2 < 1) iMax2 = 0;
			 */
			
			
			for(int i=0;i<vcMEDREGSIN.size();i++){
				vMEDREGSIN = (TVMEDREGSIN) vcMEDREGSIN.get(i);
	
				cSQL = "INSERT INTO MEDREGSINBITACORA	(ICVESERVICIO, " + "ICVERAMA, 	"
						+ "ICVESINTOMA, " + "ICVEREGLA, 	" + "RDINFO, "
						+ "RDACCION, " + "LACTIVO, " + "IMAYORA, " + "IIGUALA, "
						+ "IMENORA, " + "LOGICA,     " + "LDICTAMENS, "
						+ "LDICTAMENF, " + "ICVEUSUGENERA, " + "DTGENERADO, "
						+ "CALERTA, " + "CDSCREGLA, " + "LDEDPENDIENTE, "
						+ "ICVEMDOTRANS, " + "ICVECATEGORIA,   "
						+ "DTELIMINADO, " + "ICVEUSUELIMINA)   "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
				 //System.out.println(cSQL);
				pstmt = conn.prepareStatement(cSQL);
	
				// Calcular Timestamp para el campo TINIEXA
				java.util.Date utilDate = new java.util.Date(); // fecha actual
				long lnMilisegundos = utilDate.getTime();
				java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
						lnMilisegundos);
				// System.out.println("sql.Timestamp: "+sqlTimestamp);
				pstmt.setInt(1, vMEDREGSIN.getICveServicio());
				pstmt.setInt(2, vMEDREGSIN.getICveRama());
				pstmt.setInt(3, vMEDREGSIN.getICveSintoma());
				pstmt.setInt(4, vMEDREGSIN.getICveRegla());// REGLA
				pstmt.setInt(5, vMEDREGSIN.getRdInfo());
				pstmt.setInt(6, vMEDREGSIN.getRdAccion());
				pstmt.setInt(7, vMEDREGSIN.getLActivo());
				pstmt.setDouble(8, vMEDREGSIN.getIMayorA());
				pstmt.setDouble(9, vMEDREGSIN.getIIgualA());
				pstmt.setDouble(10, vMEDREGSIN.getIMenorA());
	
				pstmt.setInt(11, vMEDREGSIN.getLogica());
				pstmt.setInt(12, vMEDREGSIN.getLDictamenS());
				pstmt.setInt(13, vMEDREGSIN.getLDictamenF());
				pstmt.setInt(14, vMEDREGSIN.getICveUsugenera());
				pstmt.setTimestamp(15, vMEDREGSIN.getDtGenerado());
				pstmt.setString(16, vMEDREGSIN.getCAlerta());
				pstmt.setString(17, vMEDREGSIN.getCdscRegla());
				pstmt.setInt(18, vMEDREGSIN.getLDependiente());
				pstmt.setInt(19, vMEDREGSIN.getICveMdoTrans());
				pstmt.setInt(20, vMEDREGSIN.getICveCategoria());
				pstmt.setTimestamp(21, sqlTimestamp);
				pstmt.setInt(22, usr);
	
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


	/*
	 * M??todo Find By All
	 */
	public String PreguntasDeLasQueDepende(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDAReg = new Vector();
		String dependiente = "NO";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count = 0;
			cSQL = " SELECT R.SINTOMADEP "
					+ "              FROM MEDREGSIN AS D, MEDAREG AS R	"
					+ "              WHERE		"
					+ "              D.ICVESERVICIO = R.ICVESERVICIO AND       "
					+ "              D.ICVERAMA = R.ICVERAMA AND		"
					+ "              D.ICVESINTOMA = R.ICVESINTOMA AND	      "
					+ "              D.LDEDPENDIENTE = 1 AND " + cWhere 
					+"               GROUP BY R.SINTOMADEP";

			//System.out.println("Dep = "+cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if(count == 0){
					dependiente = rset.getString(1)+"";	
				}else{
					dependiente = ","+dependiente+rset.getString(1)+"";
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
			return dependiente;
		}
	}	
	

	/*
	 * M??todo Find By All
	 */
	public String PreguntaConReglas(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDAReg = new Vector();
		String Regla = "NO";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDAReg vMEDAReg;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT D.ICVESINTOMA "
					+ "              FROM MEDREGSIN AS D	"
					+ "              WHERE		"
					+ "              D.LDEDPENDIENTE = 0 AND " 
					+ cWhere;
			//System.out.println("Reglas1 = "+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Regla = "SI";
			}
			
			cSQL = " SELECT D.ICVESINTOMA "
					+ "              FROM MEDREGSIN AS D	"
					+ "              WHERE		"
					+ "              D.LDEDPENDIENTE = 0 AND "
					+ "              D.LDICTAMENF = 1 AND " 
					+ cWhere;
			//System.out.println("Reglas2 = "+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Regla = "SI - Dictamen Final";
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
			return Regla;
		}
	}	
		
}