package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.seguridad.vo.TVDinRep02;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.util.ServicioAleatorio;

/**
 * <p>
 * Title: Data Acces Object de EXPResultado DAO
 * </p>
 * <p>
 * Description: DAO EXPResultado
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

public class TDPEREXAMENNoAp extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private int iInserted; // Agregado por Rafael Alcocer Caldera 06/Nov/2006
	private int iUpdated; // Agregado por Rafael Alcocer Caldera 06/Nov/2006

	public TDPEREXAMENNoAp() {
	}

	/**
	 * Método Find By All
	 */
	@SuppressWarnings("finally")
	public String RegresaValorCampo(String exp, String exa, int serv, int rama, int campo, int sintoma) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String respuesta = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			if (campo == 1) { // /LOGICA
				cSQL = "SELECT lLogico FROM PEREXAMENNOAP	WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? AND "+
				"ICVESERVICIO = ? AND ICVERAMA = ? AND ICVESINTOMA = ? ";
			}

			if (campo == 2) { // /FLOTANTE INICIO
				cSQL = "SELECT dValorIni FROM PEREXAMENNOAP	WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? AND "+
				"ICVESERVICIO = ? AND ICVERAMA = ? AND ICVESINTOMA = ? ";
			}

			if (campo == 3) { // / CARACTER
				cSQL = "SELECT cCaracter FROM PEREXAMENNOAP	WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? AND "+
				"ICVESERVICIO = ? AND ICVERAMA = ? AND ICVESINTOMA = ? ";
			}
			if (campo == 4) { // / FLOTANTE FIN
				cSQL = "SELECT dValorFin FROM PEREXAMENNOAP	WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? AND "+
				"ICVESERVICIO = ? AND ICVERAMA = ? AND ICVESINTOMA = ? ";
			}
			
			if (campo == 5) { // / FECHA
				cSQL = "SELECT tiFecha FROM PEREXAMENNOAP	WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? AND "+
						"ICVESERVICIO = ? AND ICVERAMA = ? AND ICVESINTOMA = ? ";
			}

			System.out.println(cSQL);
		
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, exp);
			pstmt.setString(2, exa);
			pstmt.setInt(3, serv);
			pstmt.setInt(4, rama);
			pstmt.setInt(5, sintoma);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				respuesta = rset.getString(1);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (final Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return respuesta;
		}
	}

	
	/**
	 * Método Find By All
	 * 
	 * @author Marco A. Gonz�lez Paz Para busqueda de Factor Sanguineo
	 * 
	 */
	public Vector FindByAllFC(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPResultado vEXPResultado;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "EXPResultado.iCveExpediente,"
					+ "EXPResultado.iNumExamen," + "EXPResultado.iCveServicio,"
					+ "EXPResultado.iCveRama," + "EXPResultado.iCveSintoma,"
					+ "EXPResultado.dValorIni," + "EXPResultado.lLogico,"
					+ "EXPResultado.cCaracter," + "EXPResultado.dValorFin" +
					// "MEDSintomas.cPregunta" +
					" from EXPResultado " + cWhere + " order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPResultado = new TVEXPResultado();
				vEXPResultado.setICveExpediente(rset.getInt(1));
				vEXPResultado.setINumExamen(rset.getInt(2));
				vEXPResultado.setICveServicio(rset.getInt(3));
				vEXPResultado.setICveRama(rset.getInt(4));
				vEXPResultado.setICveSintoma(rset.getInt(5));

				// vEXPResultado.setDValorIni(rset.getFloat(6)); //FCS : Se
				// cambio el Tipo De Dato, de float a Double,
				// ya que formateaba a 4 posiciones en la parte decimal y le
				// resta 1,
				// ver linea siguiente.
				vEXPResultado.setDValorIni(rset.getDouble(6));

				vEXPResultado.setLLogico(rset.getInt(7));
				vEXPResultado.setCCaracter(rset.getString(8));
				vEXPResultado.setDValorFin(rset.getFloat(9));
				vcEXPResultado.addElement(vEXPResultado);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcEXPResultado;
		}
	}

	/**
	 * /** Método Insert
	 */
	public Object insert(Connection conGral, int exp, int exa, int serv,
			int rama, int sintoma, String valor, int campo) throws DAOException {
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
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			if (campo == 1) { // /LOGICA
				cSQL = "insert into PEREXAMENNOAP(" + "iCveExpediente,"
						+ "iNumExamen," + "iCveServicio," + "iCveRama,"
						+ "iCveSintoma," + "lLogico" + ")values(?,?,?,?,?,"
						+ valor + ")";
			}

			if (campo == 2) { // /FLOTANTE INICIO
				cSQL = "insert into PEREXAMENNOAP(" + "iCveExpediente,"
						+ "iNumExamen," + "iCveServicio," + "iCveRama,"
						+ "iCveSintoma," + "dValorIni" + ")values(?,?,?,?,?,"
						+ valor + ")";
			}

			if (campo == 3) { // / CARACTER
				cSQL = "insert into PEREXAMENNOAP(" + "iCveExpediente,"
						+ "iNumExamen," + "iCveServicio," + "iCveRama,"
						+ "iCveSintoma," + "cCaracter" + ")values(?,?,?,?,?,'"
						+ valor + "')";
			}
			if (campo == 4) { // / FLOTANTE FIN
				cSQL = "insert into PEREXAMENNOAP(" + "iCveExpediente,"
						+ "iNumExamen," + "iCveServicio," + "iCveRama,"
						+ "iCveSintoma," + "dValorFin" + ")values(?,?,?,?,?,"
						+ valor + ")";
			}
			
			if (campo == 5) { // / FECHA
				cSQL = "insert into PEREXAMENNOAP(" + "iCveExpediente,"
						+ "iNumExamen," + "iCveServicio," + "iCveRama,"
						+ "iCveSintoma," + "tiFecha" + ")values(?,?,?,?,?,'"
						+ valor + " 00:00:00.001')";
			}

			System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, exp);
			pstmt.setInt(2, exa);
			pstmt.setInt(3, serv);
			pstmt.setInt(4, rama);
			pstmt.setInt(5, sintoma);

			iInserted = pstmt.executeUpdate();
			cClave = "" + exp;

			// conn.commit();
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * /** Método Insert
	 */
	public Object eliminaExamen(Connection conGral, int exp, int exa) throws DAOException {
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
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "delete from PEREXAMENNOAP" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?";
			
			System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, exp);
			pstmt.setInt(2, exa);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
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
			TVEXPResultado vEXPResultado = (TVEXPResultado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPResultado" + " set dValorIni= ?, "
					+ "lLogico= ?, " + "cCaracter= ?, "
					+ "dValorFin= ? "
					+
					// "cValRef= ? " +
					"where iCveExpediente = ? " + "and iNumExamen = ?"
					+ "and iCveServicio = ?" + "and iCveRama = ?"
					+ " and iCveSintoma = ?";

			// System.out.println("vEXPResultado.getDValorIni() = "+vEXPResultado.getDValorIni());
			// System.out.println("vEXPResultado.getLLogico() = "
			// +vEXPResultado.getLLogico());
			// System.out.println("vEXPResultado.getCCaracter() = "+vEXPResultado.getCCaracter());
			// System.out.println("vEXPResultado.getICveSintoma() = "+vEXPResultado.getICveSintoma());

			pstmt = conn.prepareStatement(cSQL);
			String cValorRef = vEXPResultado.getCValRef();
			// pstmt.setFloat(1, vEXPResultado.getDValorIni()); //FCS : Se
			// cambio el Tipo De Dato, de float a Double,
			// ya que formateaba a 4 posiciones en la parte decimal y le resta
			// 1,
			// ver linea siguiente.
			pstmt.setDouble(1, vEXPResultado.getDValorIni());

			// double x = 1.015;
			// System.out.println("********** x -> (update)  (" +x+
			// ") **********");
			// pstmt.setDouble(1, x);

			if (vEXPResultado.getLLogico() != 99) {
				pstmt.setInt(2, vEXPResultado.getLLogico());
			} else {
				pstmt.setNull(2, Types.INTEGER);
			}
			pstmt.setString(3, vEXPResultado.getCCaracter());
			pstmt.setFloat(4, vEXPResultado.getDValorFin());
			/*
			 * if (cValorRef != null && cValorRef.compareTo("null") != 0) { //
			 * Ajustar cuando se cambie el campo a 50 Caracteres //Marco
			 * Gonz�lez Paz 18/nov/2004 11:20 a.m. if (cValorRef.length() > 20)
			 * { pstmt.setString(5, cValorRef.substring(0, 19)); } else {
			 * pstmt.setString(5, cValorRef); } } else { pstmt.setString(5, "");
			 * }
			 */

			pstmt.setInt(5, vEXPResultado.getICveExpediente());
			pstmt.setInt(6, vEXPResultado.getINumExamen());
			pstmt.setInt(7, vEXPResultado.getICveServicio());
			pstmt.setInt(8, vEXPResultado.getICveRama());
			pstmt.setInt(9, vEXPResultado.getICveSintoma());
			iUpdated = pstmt.executeUpdate();
			cClave = "";

			// //Se agrego la siguiente linea el dia 24 de marzo 2014 y se
			// comentaron las posterires
			conn.commit();
			/*
			 * if (conGral == null) { conn.commit(); }
			 */
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
		}
		return cClave;
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
			TVEXPResultado vEXPResultado = (TVEXPResultado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPResultado" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveServicio = ?"
					+ " and iCveRama = ?" + " and iCveSintoma = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPResultado.getICveExpediente());
			pstmt.setInt(2, vEXPResultado.getINumExamen());
			pstmt.setInt(3, vEXPResultado.getICveServicio());
			pstmt.setInt(4, vEXPResultado.getICveRama());
			pstmt.setInt(5, vEXPResultado.getICveSintoma());
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("delete.closeEXPResultado", ex2);
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
		if (debug) {
			// System.out.println(this.getClass().getName() + ":" +
			// obj.toString());
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 06/Nov/2006
	 * 
	 * @return int
	 */
	public int getIInserted() {
		return iInserted;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 06/Nov/2006
	 * 
	 * @return int
	 */
	public int getIUpdated() {
		return iUpdated;
	}

}
