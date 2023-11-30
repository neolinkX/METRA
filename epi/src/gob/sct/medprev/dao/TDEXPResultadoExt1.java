package gob.sct.medprev.dao;

import gob.sct.medprev.vo.TVEXPResultado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.sql.DAOBase;
import com.micper.sql.DbConnection;

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

public class TDEXPResultadoExt1 extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private int iInserted; 
	private int iUpdated; 

	public TDEXPResultadoExt1() {
	}

	/**
	 * Método Find By All
	 */
	public int Regresa(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPResultado = new Vector();
		int regresa = 0;
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
			return regresa;
		}
	}
	

	/**
	 * /** Método Insert
	 */
	@SuppressWarnings("finally")
	public Object insert(Connection conGral, String csql) throws DAOException {
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
			cSQL = ""+csql;
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			iInserted = pstmt.executeUpdate(); 
			cClave = "1";
			conn.commit();
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
			} catch (final Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}
	

	public int DeleteSintoma(String expediente, String examen, String servicio, String rama, String sintoma) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iRset = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " DELETE FROM EXPResultado "
					+ " WHERE iCveExpediente = ? " + " AND iNumExamen = ? "
					+ " AND iCveServicio = ? "
					+ " AND iCveRama = ? "
					+ " AND iCveSintoma = ? ";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, Integer.parseInt(expediente));
			pstmt.setInt(2, Integer.parseInt(examen));
			pstmt.setInt(3, Integer.parseInt(servicio));
			pstmt.setInt(4, Integer.parseInt(rama));
			pstmt.setInt(5, Integer.parseInt(sintoma));
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
	 * Método Find By All
	 */
	@SuppressWarnings("finally")
	public boolean RegresaSintomaLogico(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean regresa = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regresa = rset.getBoolean(1);			
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
			return regresa;
		}
	}
	

	/**
	 * Método Find By All
	 */
	@SuppressWarnings("finally")
	public double RegresaSintomaDouble(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		double regresa = 0.0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regresa = rset.getDouble(1);			
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
			return regresa;
		}
	}

}
