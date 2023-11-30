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

public class TDEXPResultado extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private int iInserted; // Agregado por Rafael Alcocer Caldera 06/Nov/2006
	private int iUpdated; // Agregado por Rafael Alcocer Caldera 06/Nov/2006

	public TDEXPResultado() {
	}

	/**
	 * Método Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
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
	 * Método Find Dsc's con clave de la Rama
	 */
	public Vector FindDsc(String cExpediente, String cNumExamen,
			String cServicio, String cRama) throws DAOException {
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
			cSQL = "select "
					+ "EXPResultado.iCveExpediente, "
					+ "EXPResultado.iNumExamen, "
					+ "EXPResultado.iCveServicio, "
					+ "EXPResultado.iCveRama, "
					+ "EXPResultado.iCveSintoma, "
					+ "EXPResultado.dValorIni, "
					+ "EXPResultado.lLogico, "
					+ "EXPResultado.cCaracter, "
					+ "EXPResultado.dValorFin, "
					+ "EXPResultado.cValRef, "
					+ "MEDServicio.cDscServicio, "
					+ "MEDRama.cDscRama, "
					+ "MEDSintomas.cPregunta, "
					+ "MEDSintomas.iCveTpoResp "
					+ "from EXPResultado "
					+ "inner join MEDServicio on MEDServicio.iCveServicio = EXPResultado.iCveServicio "
					+ "inner join MEDRama on MEDRama.iCveServicio = EXPResultado.iCveServicio "
					+ "       and MEDRama.iCveRama = EXPResultado.iCveRama "
					+ "inner join MEDSintomas on MEDSintomas.iCveServicio = EXPResultado.iCveServicio "
					+ "       and MEDSintomas.iCveRama = EXPResultado.iCveRama "
					+ "       and MEDSintomas.iCveSintoma = EXPResultado.iCveSintoma "
					+ "where EXPResultado.iCveExpediente = " + cExpediente
					+ "  and EXPResultado.iNumExamen = " + cNumExamen
					+ "  and EXPResultado.iCveServicio = " + cServicio
					+ "  and EXPResultado.iCveRama = " + cRama + "  order by "
					+ "  EXPResultado.iCveServicio, " + "  MEDRama.iOrden, "
					+ "  MEDSintomas.iOrden ";

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

				String temp = "" + rset.getString(7);
				if (temp.compareTo("null") != 0)
					vEXPResultado.setLLogico(rset.getInt(7));
				else
					vEXPResultado.setLLogico(-1);

				vEXPResultado.setCCaracter(rset.getString(8));
				vEXPResultado.setDValorFin(rset.getFloat(9));
				vEXPResultado.setCValRef(rset.getString(10));
				vEXPResultado.setCDscServicio(rset.getString(11));
				vEXPResultado.setCDscRama(rset.getString(12));
				vEXPResultado.setCPregunta(rset.getString(13));
				vEXPResultado.setICveTpoResp(rset.getInt(14));
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
	 * Método Find Dsc's sin clave de la Rama
	 */
	public Vector FindDsc(String cExpediente, String cNumExamen,
			String cServicio) throws DAOException {
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
			cSQL = "select "
					+ "EXPResultado.iCveExpediente, "
					+ "EXPResultado.iNumExamen, "
					+ "EXPResultado.iCveServicio, "
					+ "EXPResultado.iCveRama, "
					+ "EXPResultado.iCveSintoma, "
					+ "EXPResultado.dValorIni, "
					+ "EXPResultado.lLogico, "
					+ "EXPResultado.cCaracter, "
					+ "EXPResultado.dValorFin, "
					+ "EXPResultado.cValRef, "
					+ "MEDServicio.cDscServicio, "
					+ "MEDRama.cDscRama, "
					+ "MEDSintomas.cPregunta, "
					+ "MEDSintomas.iCveTpoResp, "
					+ "MEDRama.iCveRama "
					+ "from EXPResultado "
					+ "inner join MEDServicio on MEDServicio.iCveServicio = EXPResultado.iCveServicio "
					+ "inner join MEDRama on MEDRama.iCveServicio = EXPResultado.iCveServicio "
					+ "       and MEDRama.iCveRama = EXPResultado.iCveRama "
					+ "inner join MEDSintomas on MEDSintomas.iCveServicio = EXPResultado.iCveServicio "
					+ "       and MEDSintomas.iCveRama = EXPResultado.iCveRama "
					+ "       and MEDSintomas.iCveSintoma = EXPResultado.iCveSintoma "
					+ "where EXPResultado.iCveExpediente = "
					+ cExpediente
					+ "  and EXPResultado.iNumExamen = "
					+ cNumExamen
					+ "  and EXPResultado.iCveServicio = "
					+ cServicio
					+ "  order by "
					+ "  EXPResultado.iCveExpediente, EXPResultado.iNumExamen, EXPResultado.iCveServicio, "
					+ "  MEDRama.iOrden, " + "  MEDSintomas.iOrden ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPResultado = new TVEXPResultado();
				vEXPResultado.setICveExpediente(rset.getInt(1));
				vEXPResultado.setINumExamen(rset.getInt(2));
				vEXPResultado.setICveServicio(rset.getInt(3));
				vEXPResultado.setICveRama(rset.getInt(4));
				vEXPResultado.setICveSintoma(rset.getInt(5));

				// ORIG vEXPResultado.setDValorIni(rset.getFloat(6)); //FCS : Se
				// cambio el Tipo De Dato, de float a Double,
				// ya que formateaba a 4 posiciones en la parte decimal y le
				// resta 1,
				// ver linea siguiente.
				vEXPResultado.setDValorIni(rset.getDouble(6));

				vEXPResultado.setLLogico(rset.getInt(7));
				vEXPResultado.setCCaracter(rset.getString(8));
				vEXPResultado.setDValorFin(rset.getFloat(9));
				vEXPResultado.setCValRef(rset.getString(10));
				vEXPResultado.setCDscServicio(rset.getString(11));
				vEXPResultado.setCDscRama(rset.getString(12));
				vEXPResultado.setCPregunta(rset.getString(13));
				vEXPResultado.setICveTpoResp(rset.getInt(14));
				vEXPResultado.setICveRama(rset.getInt(15));

				vcEXPResultado.addElement(vEXPResultado);
			}
		} catch (Exception ex) {
			warn("FindDsc", ex);
			throw new DAOException("FindDsc");
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
				warn("FindDsc.close", ex2);
			}
			return vcEXPResultado;
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
	 * Método FindSignos
	 */
	public Vector FindServAdicionales(String cWhere) throws DAOException {
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
			cSQL = "select "
					+ "EXPResultado.iCveExpediente,"
					+ "EXPResultado.iNumExamen,"
					+ "EXPResultado.iCveServicio,"
					+ "EXPResultado.iCveRama,"
					+ "EXPResultado.iCveSintoma,"
					+ "EXPResultado.dValorIni,"
					+ "EXPResultado.lLogico,"
					+ "EXPResultado.cCaracter,"
					+ "EXPResultado.dValorFin,"
					+ "MEDSintomas.cPregunta,"
					+ "MEDSintomas.iCveTpoResp,"
					+ "EXPRama.lConcluido "
					+ " from EXPResultado "
					+ "inner join MEDSintomas on MEDSintomas.iCveServicio = EXPResultado.iCveServicio "
					+ "and MEDSintomas.iCveRama = EXPResultado.iCveRama "
					+ "and MEDSintomas.iCveSintoma = EXPResultado.iCveSintoma "
					+ "and MEDSintomas.lActivo = 1 "
					+ "and MEDSintomas.iCveTpoResp not In(6) "
					+ "inner join EXPRama on EXPRama.iCveExpediente = EXPResultado.iCveExpediente "
					+ "and EXPRama.iNumExamen = EXPResultado.iNumExamen "
					+ "and EXPRama.iCveServicio = EXPResultado.iCveServicio "
					+ "and EXPRama.iCveRama = EXPResultado.iCveRama " + cWhere
					+ " order by iCveExpediente";

			// System.out.println(cSQL);

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
				vEXPResultado.setCPregunta(rset.getString(10));
				vEXPResultado.setICveTpoResp(rset.getInt(11));
				vEXPResultado.setLConcluido(rset.getInt(12));
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
			TVEXPResultado vEXPResultado = (TVEXPResultado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPResultado(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "iCveRama,"
					+ "iCveSintoma," + "dValorIni," + "lLogico," + "cCaracter,"
					+ "dValorFin," + "cValRef"
					+ ")values(?,?,?,?,?,null,null,null,null,?)";
			
			/*System.out.println("insert into EXPResultado(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "iCveRama,"
					+ "iCveSintoma," + "dValorIni," + "lLogico," + "cCaracter,"
					+ "dValorFin," + "cValRef"
					+ ")");
			System.out.println("values("+vEXPResultado.getICveExpediente()+","+vEXPResultado.getINumExamen()+","+vEXPResultado.getICveServicio()+","+vEXPResultado.getICveRama()+","+vEXPResultado.getICveSintoma()+",null,null,null,null,null);");
*/
			
			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vEXPResultado.getICveExpediente());
			pstmt.setInt(2, vEXPResultado.getINumExamen());
			pstmt.setInt(3, vEXPResultado.getICveServicio());
			pstmt.setInt(4, vEXPResultado.getICveRama());
			pstmt.setInt(5, vEXPResultado.getICveSintoma());
			if (vEXPResultado.getCValRef() == null)
				pstmt.setString(6, null);
			else
				pstmt.setString(6, vEXPResultado.getCValRef());
			// pstmt.setString(8, vEXPResultado.getCCaracter());
			// pstmt.setFloat(6, vEXPResultado.getDValorIni());
			// pstmt.setInt(7, vEXPResultado.getLLogico());
			// pstmt.setString(8, vEXPResultado.getCCaracter());
			// pstmt.setFloat(9, vEXPResultado.getDValorFin());
			iInserted = pstmt.executeUpdate(); // Agregado por Rafael Alcocer
												// Caldera 06/Nov/2006
			cClave = "" + vEXPResultado.getICveExpediente();
			
			////Se agrego la siguiente linea el dia 24 de marzo 2014 y se comentaron las posterires
			conn.commit();
			/*
			if (conGral == null) {
				conn.commit();
			}*/
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
	 * /** Método que inserta todos los síntomas de un servicio
	 */
	public boolean insert2(Connection conGral, String cValue) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		boolean insertado = false;
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
			cSQL = "insert into EXPResultado(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "iCveRama,"
					+ "iCveSintoma," + "dValorIni," + "lLogico," + "cCaracter,"
					+ "dValorFin," + "cValRef"
					+ ")values"+cValue;
			
			//System.out.println(cSQL);
			
			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			
			iInserted = pstmt.executeUpdate(); 
						
			conn.commit();
			/*
			if (conGral == null) {
				conn.commit();
			}*/
			insertado = true;
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insert", ex1);
				insertado = false;
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
			return insertado;
		}
	}

	public Object insertFromEXPExamAplica(Connection conGral, Object obj,
			int iCveServicio, int iCveRama, int iCveSintoma)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

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
			cSQL = "insert into EXPResultado(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "iCveRama,"
					+ "iCveSintoma" + ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			String cSQLMax = "select count(*) from EXPResultado "
					+ "Where iCveExpediente = "
					+ vEXPExamAplica.getICveExpediente()
					+ " And  iNumExamen = " + vEXPExamAplica.getINumExamen()
					+ " And  iCveServicio = " + iCveServicio
					+ " And  iCveRama = " + iCveRama + " And  iCveSintoma = "
					+ iCveSintoma;
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			pstmt.setInt(1, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(2, vEXPExamAplica.getINumExamen());
			pstmt.setInt(3, iCveServicio);
			pstmt.setInt(4, iCveRama);
			pstmt.setInt(5, iCveSintoma);
			if (iMax == 0) {
				pstmt.executeUpdate();
			}
			cClave = "" + vEXPExamAplica.getICveExpediente();
			
			////Se agrego la siguiente linea el dia 24 de marzo 2014 y se comentaron las posterires
			conn.commit();
			/*
			if (conGral == null) {
				conn.commit();
			}*/
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
			System.out.println(cSQL);
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
			
			
			
			
			
			
			System.out.println("1, "+vEXPResultado.getDValorIni());
			if (vEXPResultado.getLLogico() != 99) {
				System.out.println("2, "+vEXPResultado.getLLogico());
			} else {
				System.out.println("2, "+ Types.INTEGER);
			}
			System.out.println("3, "+ vEXPResultado.getCCaracter());
			System.out.println("4, "+ vEXPResultado.getDValorFin());
			System.out.println("5, "+ vEXPResultado.getICveExpediente());
			System.out.println("6, "+ vEXPResultado.getINumExamen());
			System.out.println("7, "+vEXPResultado.getICveServicio());
			System.out.println("8, "+vEXPResultado.getICveRama());
			System.out.println("9, "+ vEXPResultado.getICveSintoma());
			
			
			
			
			
			
			
			
			iUpdated = pstmt.executeUpdate();
			cClave = "";
			
			////Se agrego la siguiente linea el dia 24 de marzo 2014 y se comentaron las posterires
			conn.commit();
			/*
			if (conGral == null) {
				conn.commit();
			}*/
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
	 * Método update
	 */
	public Object updateSPV(Connection conGral, Object obj, String DAdicional ) throws DAOException {
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
			cSQL = "update EXPResultado" + " set cCaracter= ? " +
					" where iCveExpediente = ? " + " and iNumExamen = ?"
					+ " and iCveServicio = ?" + " and iCveRama = ?"
					+ " and iCveSintoma in (11,12,5,15)";
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, DAdicional);
			pstmt.setInt(2, vEXPResultado.getICveExpediente());
			pstmt.setInt(3, vEXPResultado.getINumExamen());
			pstmt.setInt(4, vEXPResultado.getICveServicio());
			pstmt.setInt(5, vEXPResultado.getICveRama());
/*
			System.out.println(DAdicional);
			System.out.println(vEXPResultado.getICveExpediente());
			System.out.println(vEXPResultado.getINumExamen());
			System.out.println(vEXPResultado.getICveServicio());
			System.out.println(vEXPResultado.getICveRama());
	*/		
			
			iUpdated = pstmt.executeUpdate();
			cClave = "";
			conn.commit();

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
	 * Método que obtiene los resultados de cada s�ntoma, para un expediente,
	 * examen, servicio y rama
	 * 
	 * @param param
	 *            una colecci�n (HashMap) con los parametros a utilizar en la
	 *            consulta
	 * @return una colecci�n (Vector) de Value Objectos, con los registros
	 *         obtenidos
	 * @throws DAOException
	 * @author Romeo S�nchez
	 */
	public Vector findResultadoSintoma(HashMap param) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPResultado = new Vector();
		Vector vcEXPResultado2 = new Vector();
		TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
		ServicioAleatorio ServAl = new ServicioAleatorio(); 

		int iCveExpediente = Integer.parseInt((String) param
				.get("iCveExpediente"));
		int iNumExamen = Integer.parseInt((String) param.get("iNumExamen"));
		int iCveServicio = Integer.parseInt((String) param.get("iCveServicio"));
		int iCveRama = Integer.parseInt((String) param.get("iCveRama"));
		String cGenero = (String) param.get("cGenero");
		String ServicioRandom = VParametros.getPropEspecifica("ServicioRandom").toString();
		Vector<Integer> VSerR = new Vector<Integer>();
		int [] temp ;
		int count = 0;
		long time_start, time_end;
		time_start = System.currentTimeMillis();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cOrder = "";
			TVEXPResultado vEXPResultado;
			TVMEDSintoma vMEDSintoma = new TVMEDSintoma();
			int existe = 0;
			int secciones = 0;
			
			
			/////Obtener servicios Random///////
			String [] str ; 
		      str = ServicioRandom.split( "," ); 
		      temp =  new  int [str.length];
		      for  ( int i =  0 ; i < str.length ; i ++)  {
		    	  //System.out.println ( str ); 
		            temp [i]  =  Integer.parseInt(str[i]); 
		            VSerR.add(temp[i]); 
		        } 
		      
		      for(int i = 0 ; i < VSerR.size() ; i++){
		    	  //System.out.println ( VSerR.elementAt(i) );
		    	  if(iCveServicio == VSerR.elementAt(i)){
			    	  existe = 1;
			    	  //System.out.println ("igual");
				    }
		      }
		    //////////////////////		    
		    
		      if(existe == 1){
		    	  cOrder = " RAND() ";
		    	  //cOrder = " s.iOrden ";
		      }else{
		    	  cOrder = " s.iOrden ";
		      }
			
			
			/////Actualizar servicios de Oftamologia para generar el Random
			if (iCveServicio == 67 && iCveRama == 1) {
				dMEDSintoma.updateOrdenOfta();
			}
			/////////////////////////////////////////////////////////
			

			cSQL = "select "
					+ "r.iCveExpediente,r.iNumExamen,r.iCveServicio,r.iCveRama,r.iCveSintoma,"
					+ "r.dValorIni,r.lLogico,r.cCaracter,r.dValorFin,"
					+ "s.cDscBreve,s.cPregunta,s.iCveTpoResp,s.lObligatorio,s.lEvalAuto, s.cEtiqueta, s.cValRef , s.iOrden "
					+ " from EXPResultado r "
					+ " JOIN MEDSintomas s ON s.iCveServicio=r.iCveServicio "
					+ " AND  s.iCveRama=r.iCveRama AND s.iCveSintoma=r.iCveSintoma and s.lActivo = 1 "
					+ " where r.iCveExpediente   =  " + iCveExpediente
					+ "       and r.iNumExamen   =  " + iNumExamen
					+ "       and r.iCveServicio =  " + iCveServicio
					+ "       and r.iCveRama     =  " + iCveRama
					+ "       and (s.cGenero     =  '" + cGenero + "'"
					+ "           OR s.cGenero='A') " + // masculino y femenino
					" order by "+cOrder;
			pstmt = conn.prepareStatement(cSQL);

			 //System.out.println(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				// atributos de EXPResultado
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
				// atributos de MEDSintoma
				vEXPResultado.setCDscBreve(rset.getString(10));
				vEXPResultado.setCPregunta(rset.getString(11));
				vEXPResultado.setICveTpoResp(rset.getInt(12));
				vEXPResultado.setLObligatorio(rset.getInt(13));
				vEXPResultado.setLEvalAuto(rset.getInt(14));
				vEXPResultado.setCEtiqueta(rset.getString(15));
				vEXPResultado.setCValRef(rset.getString(16));
				vEXPResultado.setIOrden(rset.getInt(17));

				if(rset.getInt(12)==6){///Tipo de respuesta igual a etiqueta
					secciones++;
				}
				
				vcEXPResultado.addElement(vEXPResultado);
			}
			
			//System.out.println("vcEXPResultado "+ vcEXPResultado.size());
			
			////Seccionar el servicio Aleatorio 
			  if(existe == 1 && secciones > 0){
				  vcEXPResultado2 = ServAl.SeccionaCuestionario(vcEXPResultado, iCveServicio, iCveRama);
				  //System.out.println("Op1");
			  }else{
				  if(existe == 1 && secciones == 0){
					  vcEXPResultado2 = ServAl.SinSeccionReordenaDependiente(vcEXPResultado, iCveServicio, iCveRama);
					  //System.out.println("Op2");
				  }else{
					  vcEXPResultado2 = vcEXPResultado;
					  //System.out.println("Op3");
				  }
			  }
			
			  //vcEXPResultado2 = vcEXPResultado;
			  time_end = System.currentTimeMillis();
			  //System.out.println("Ejecucion del metodo "+ ( time_end - time_start ) +" milliseconds");
			  //System.out.println("vcEXPResultado2 "+ vcEXPResultado2.size());
			
		} catch (Exception ex) {
			warn("findResultadoSintoma", ex);
			throw new DAOException("findResultadoSintoma");
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
		}
		return vcEXPResultado2;
	}

	/**
	 * Método que obtiene los resultados de cada s�ntoma, para un expediente,
	 * examen, servicio y rama
	 * 
	 * @param param
	 *            una colecci�n (HashMap) con los parametros a utilizar en la
	 *            consulta
	 * @return una colecci�n (Vector) de Value Objectos, con los registros
	 *         obtenidos
	 * @throws DAOException
	 * @author Romeo S�nchez
	 */
	public Vector findResultadoSintoma2(String filtro) throws DAOException {
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
			TVMEDSintoma vMEDSintoma = new TVMEDSintoma();
			int count;
			cSQL = "select "
					+ "r.iCveExpediente,r.iNumExamen,r.iCveServicio,r.iCveRama,r.iCveSintoma,"
					+ "r.dValorIni,r.lLogico,r.cCaracter,r.dValorFin,"
					+ "s.cDscBreve,s.cPregunta,s.iCveTpoResp,s.lObligatorio,s.lEvalAuto, s.cEtiqueta, s.cValRef "
					+ " from EXPResultado r "
					+ " JOIN MEDSintomas s ON s.iCveServicio=r.iCveServicio "
					+ " AND  s.iCveRama=r.iCveRama AND s.iCveSintoma=r.iCveSintoma and s.lActivo = 1 "
					+ filtro + " order by s.iOrden ";
			
			System.out.println("findResultadoSintoma2 = "+cSQL);
			
			pstmt = conn.prepareStatement(cSQL);
	
			rset = pstmt.executeQuery();
			while (rset.next()) {
				// atributos de EXPResultado
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
				// atributos de MEDSintoma
				vEXPResultado.setCDscBreve(rset.getString(10));
				vEXPResultado.setCPregunta(rset.getString(11));
				vEXPResultado.setICveTpoResp(rset.getInt(12));
				vEXPResultado.setLObligatorio(rset.getInt(13));
				vEXPResultado.setLEvalAuto(rset.getInt(14));
				vEXPResultado.setCEtiqueta(rset.getString(15));
				vEXPResultado.setCValRef(rset.getString(16));

				vcEXPResultado.addElement(vEXPResultado);
			}
		} catch (Exception ex) {
			warn("findResultadoSintoma", ex);
			throw new DAOException("findResultadoSintoma");
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
		}
		return vcEXPResultado;
	}

	public Vector findResultadoSintoma3(String filtro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPResultado = new Vector();
		System.out.println("findResultadoSintoma3");
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPResultado vEXPResultado;
			TVMEDSintoma vMEDSintoma = new TVMEDSintoma();
			int count;
			cSQL = "select "
					+ "       r.iCveServicio, "
					+ "	r.iCveRama, "
					+ "	r.iCveSintoma, "
					+ "       s.cPregunta, "
					+ "	s.iCveTpoResp, "
					+ "	s.lObligatorio, "
					+ "	s.cetiqueta "
					+ " from MEDSINTEXAMENTMP r "
					+ "       JOIN MEDSintomas s ON s.iCveServicio=r.iCveServicio "
					+ "           AND  s.iCveRama=r.iCveRama AND s.iCveSintoma=r.iCveSintoma and s.lActivo = 1 "
					+ filtro + " order by s.iOrden ";
			pstmt = conn.prepareStatement(cSQL);

			System.out.println("findResultadoSintoma3 nuevo metodo = "+cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				// atributos de EXPResultado
				vEXPResultado = new TVEXPResultado();
				vEXPResultado.setICveServicio(rset.getInt(1));
				vEXPResultado.setICveRama(rset.getInt(2));
				vEXPResultado.setICveSintoma(rset.getInt(3));
				vEXPResultado.setCPregunta(rset.getString(4));
				vEXPResultado.setICveTpoResp(rset.getInt(5));
				vEXPResultado.setLObligatorio(rset.getInt(6));
				vEXPResultado.setCEtiqueta(rset.getString(7));
				vcEXPResultado.addElement(vEXPResultado);
			}
		} catch (Exception ex) {
			warn("findResultadoSintoma", ex);
			throw new DAOException("findResultadoSintoma");
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
		}
		return vcEXPResultado;
	}

	/**
	 * Método FindResultadoServicioRama
	 */
	public Vector FindResultadoServicioRama(HashMap hmFiltros)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPResultado vEXPResultado;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "a.iCveExpediente,a.iNumExamen,a.iCveServicio,a.iCveRama,"
					+ "a.iCveSintoma,a.dValorIni,a.lLogico,a.cCaracter,a.dValorFin,"
					+ "b.cDscServicio,c.cDscRama,d.cPregunta,d.cEtiqueta,e.cCampo "
					+ "from EXPResultado a "
					+ "inner join MEDServicio b on (a.iCveServicio=b.iCveServicio) "
					+ "inner join MEDRama c on (a.iCveServicio=c.iCveServicio and "
					+ "a.iCveRama=c.iCveRama) "
					+ "inner join MEDSintomas d on (a.iCveServicio=d.iCveServicio and "
					+ "a.iCveRama=d.iCveRama and a.iCveSintoma=d.iCveSintoma) "
					+ "inner join MEDTpoResp e on (d.iCveTpoResp=e.iCveTpoResp)";
			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");
			String cCveServicio = (String) hmFiltros.get("iCveServicio");
			String cCveRama = (String) hmFiltros.get("iCveRama");
			String cCveSintoma = (String) hmFiltros.get("iCveSintoma");
			if (cCveExpediente != null) {
				cSQL += " where a.iCveExpediente=?";
				cWhereAnd = " and";
			}
			if (cNumExamen != null) {
				cSQL += cWhereAnd + " a.iNumExamen=?";
				cWhereAnd = " and";
			}
			if (cCveServicio != null) {
				cSQL += cWhereAnd + " a.iCveServicio=?";
				cWhereAnd = " and";
			}
			if (cCveRama != null) {
				cSQL += cWhereAnd + " a.iCveRama=?";
				cWhereAnd = " and";
			}
			if (cCveSintoma != null) {
				cSQL += cWhereAnd + " a.iCveSintoma=?";
			}
			cSQL += " order by "
					+ "a.iCveExpediente,a.iNumExamen,a.iCveServicio,a.iCveRama,d.iOrden,a.iCveSintoma";
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cCveExpediente != null) {
				pstmt.setInt(i++, Integer.parseInt(cCveExpediente));
			}
			if (cNumExamen != null) {
				pstmt.setInt(i++, Integer.parseInt(cNumExamen));
			}
			if (cCveServicio != null) {
				pstmt.setInt(i++, Integer.parseInt(cCveServicio));
			}
			if (cCveRama != null) {
				pstmt.setInt(i++, Integer.parseInt(cCveRama));
			}
			if (cCveSintoma != null) {
				pstmt.setInt(i++, Integer.parseInt(cCveSintoma));
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPResultado = new TVEXPResultado();
				vEXPResultado.setICveExpediente(rset.getInt("iCveExpediente"));
				vEXPResultado.setINumExamen(rset.getInt("iNumExamen"));
				vEXPResultado.setICveServicio(rset.getInt("iCveServicio"));
				vEXPResultado.setICveRama(rset.getInt("iCveRama"));
				vEXPResultado.setICveSintoma(rset.getInt("iCveSintoma"));

				// vEXPResultado.setDValorIni(rset.getFloat("dValorIni")); //FCS
				// : Se cambio el Tipo De Dato, de float a Double,
				// ya que formateaba a 4 posiciones en la parte decimal y le
				// resta 1,
				// ver linea siguiente.
				vEXPResultado.setDValorIni(rset.getDouble("dValorIni"));

				String temp = "" + rset.getString("lLogico");
				if (temp.compareTo("null") != 0)
					vEXPResultado.setLLogico(rset.getInt("lLogico"));
				else
					vEXPResultado.setLLogico(-1);

				vEXPResultado.setCCaracter(rset.getString("cCaracter"));
				vEXPResultado.setDValorFin(rset.getFloat("dValorFin"));
				vEXPResultado.setCDscServicio(rset.getString("cDscServicio"));
				vEXPResultado.setCDscRama(rset.getString("cDscRama"));
				vEXPResultado.setCPregunta(rset.getString("cPregunta"));
				vEXPResultado.setCEtiqueta(rset.getString("cEtiqueta"));
				vEXPResultado.setCCampo(rset.getString("cCampo"));
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
		}
		return vcEXPResultado;
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

	public int Liberar(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iRset = 0;
		try {
			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");
			String cServicio = (String) hmFiltros.get("iCveServicio");
			String cRama = "";
			String cCveSintoma = "";

			if (hmFiltros.get("iCveRama") != null)
				cRama = (String) hmFiltros.get("iCveRama");
			if (hmFiltros.get("iCveSintoma") != null)
				cCveSintoma = (String) hmFiltros.get("iCveSintoma");

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " UPDATE EXPResultado SET "
					+ " dValorIni = null , "
					+ " lLogico = 0, "
					+ " cCaracter = null, "
					+ " dValorFin = null "
					+ " WHERE iCveExpediente = ? "
					+ " AND iNumExamen = ? "
					+ " AND iCveServicio = ? "
					+ (cRama.trim().length() > 0 ? " AND iCveRama = ? " : "")
					+ (cCveSintoma.trim().length() > 0 ? " AND iCveSintoma = ? "
							: "");

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, Integer.parseInt(cCveExpediente));
			pstmt.setInt(2, Integer.parseInt(cNumExamen));
			pstmt.setInt(3, Integer.parseInt(cServicio));
			if (cRama.trim().length() > 0)
				pstmt.setInt(4, Integer.parseInt(cRama));
			if (cCveSintoma.trim().length() > 0)
				pstmt.setInt(5, Integer.parseInt(cCveSintoma));

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

	public Vector findResultExamA(int iCveExpediente, int iNumExamen,
			int iCveServicio, int iCveRama) throws DAOException {
		DbConnection dbConn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		StringBuffer cSQL = new StringBuffer();
		TVDinRep02 vExpRama = new TVDinRep02();
		Vector vResultado = new Vector();
		int iServLC = Integer.parseInt(VParametros.getPropEspecifica(
				"CveLabClin").toString());
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			cSQL.append(" select  ER.iCveExpediente, ER.iNumExamen,  ")
					.append("         ER.iCveServicio, ER.iCveRama,  ER.iCveSintoma,  ")
					.append("         ER.dValorIni, ER.lLogico, ER.cCaracter, ER.dValorFin, ")
					.append("         ER.cValRef, ")
					.append("         MR.iCveRama, MR.cDscRama,  ")
					.append("         MS.cPregunta,  MS.iCveTpoResp,  MS.cEtiqueta, ")
					.append("         ERM.lConcluido, ERM.dtFin, ERM.iCveUsuAplica,")
					.append("         U.cApPaterno || ' ' || U.cApMaterno || ' ' || U.cNombre as cMedico, ")
					.append("         IC.cSiglasProf, IC.cCedula ")
					.append(" from EXPResultado  ER ")
					.append(" inner join EXPRama ERM on ERM.iCveExpediente = ER.iCveExpediente ")
					.append("                       and ERM.iNumExamen     = ER.iNumExamen ")
					.append("                       and ERM.iCveServicio   = ER.iCveServicio ")
					.append("                       and ERM.iCveRama       = ER.iCveRama  ")
					.append(" inner join MEDRama MR on MR.iCveServicio = ER.iCveServicio  ")
					.append("                      and MR.iCveRama     = ER.iCveRama  ")
					.append(" inner join MEDSintomas MS on MS.iCveServicio = ER.iCveServicio  ")
					.append("                          and MS.iCveRama     = ER.iCveRama  ")
					.append("                          and MS.iCveSintoma  = ER.iCveSintoma  ")
					.append(" left join SEGUsuario U on U.iCveUsuario =  ERM.iCveUsuAplica ")
					.append(" left join GRLUsuario IC on IC.iCveUsuario = U.iCveUsuario ")
					.append(" where ER.iCveExpediente = ")
					.append(iCveExpediente)
					.append("   and ER.iNumExamen     = ").append(iNumExamen)
					.append("   and ER.iCveServicio   = ").append(iCveServicio);
			if (iCveRama > 0)
				cSQL.append("   and ER.iCveRama = ").append(iCveRama);
			cSQL.append(
					" order by  ER.iCveExpediente, ER.iNumExamen, ER.iCveServicio,  ")
					.append("           MR.iOrden, MS.iOrden ");

			// System.out.println("EXPRESULTADO = "+cSQL);
			int iCveTpoResp7 = 4;

			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vExpRama = new TVDinRep02();
				vExpRama.put("iCveExpediente", rset.getInt("iCveExpediente"));
				vExpRama.put("iNumExamen", rset.getInt("iNumExamen"));
				vExpRama.put("iCveServicio", rset.getInt("iCveServicio"));
				vExpRama.put("iCveRama", rset.getInt("iCveRama"));
				vExpRama.put("iCveSintoma", rset.getInt("iCveSintoma"));
				vExpRama.put("dValorIni", rset.getDouble("dValorIni"));
				vExpRama.put("lLogico", rset.getInt("lLogico"));
				vExpRama.put(
						"cCaracter",
						rset.getString("cCaracter") != null ? rset
								.getString("cCaracter") : "&nbsp;");
				vExpRama.put("dValorFin", rset.getDouble("dValorFin"));
				vExpRama.put("cValRef", rset.getString("cValRef") != null
						&& rset.getString("cValRef").trim().length() > 0 ? "("
						+ rset.getString("cValRef").trim() + ")" : "");
				vExpRama.put("iCveRama", rset.getInt("iCveRama"));
				vExpRama.put("cDscRama", rset.getString("cDscRama"));
				vExpRama.put("cPregunta", rset.getString("cPregunta"));
				if (rset.getInt("iCveTpoResp") == 7)
					vExpRama.put("iCveTpoResp", iCveTpoResp7);
				else
					vExpRama.put("iCveTpoResp", rset.getInt("iCveTpoResp"));

				vExpRama.put(
						"cEtiqueta",
						rset.getString("cEtiqueta") != null
								&& rset.getString("cEtiqueta").length() > 0 ? rset
								.getString("cEtiqueta") : "&nbsp;");
				vExpRama.put("lConcluido", rset.getInt("lConcluido"));
				vExpRama.put("dtFin", rset.getDate("dtFin"));
				vExpRama.put("iCveUsuAplica", rset.getInt("iCveUsuAplica"));
				vExpRama.put("cMedico", rset.getString("cMedico"));
				vExpRama.put(
						"cSiglasProf",
						rset.getString("cSiglasProf") != null ? rset
								.getString("cSiglasProf") : "");
				vExpRama.put("cCedula",
						rset.getString("cCedula") != null ? "(Ced. Prof."
								+ rset.getString("cCedula") + ")" : "");
				// Codificar el Valor L�gico
				switch (rset.getInt("lLogico")) {
				case 0:
					vExpRama.put("cLogico", iServLC == iCveServicio ? "(-)"
							: "No");
					break;
				case 1:
					vExpRama.put("cLogico", iServLC == iCveServicio ? "(+)"
							: "S�");
					break;
				default:
					vExpRama.put("cLogico", "&nbsp;");
				}
				// Dependiendo del Tipo de Respuesta es el valor a enviar

				switch (vExpRama.getInt("iCveTpoResp")) {
				case 1: // L�gico
					vExpRama.put("cResultado", vExpRama.getString("cLogico"));
					break;
				case 2: // Caracter
				case 4: // Nota M�dica
					vExpRama.put("cResultado", vExpRama.getString("cCaracter"));
					break;
				case 3: // Num�rico
					vExpRama.put("cResultado", vExpRama.getString("dValorIni"));
					break;
				case 5:
					vExpRama.put("cResultado", vExpRama.getString("dValorIni")
							+ " - " + vExpRama.getString("dValorFin"));
					break;
				case 6: // T�tulo
				default:
					break;
				}
				vResultado.add(vExpRama);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("findResultExamA");
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
				warn("findResultExamA.close", ex2);
			}
		}
		return vResultado;
	}

	public Vector findResultExamA2(int iCveExpediente, int iNumExamen,
			int iCveServicio, int iCveRama) throws DAOException {
		DbConnection dbConn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		StringBuffer cSQL = new StringBuffer();
		TVDinRep02 vExpRama = new TVDinRep02();
		Vector vResultado = new Vector();
		int iServLC = Integer.parseInt(VParametros.getPropEspecifica(
				"CveLabClin").toString());
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			cSQL.append(" select  ER.iCveExpediente, ER.iNumExamen,  ")
					.append("         ER.iCveServicio, ER.iCveRama,  ER.iCveSintoma,  ")
					.append("         ER.dValorIni, ER.lLogico, ER.cCaracter, ER.dValorFin, ")
					.append("         ER.cValRef, ")
					.append("         MR.iCveRama, MR.cDscRama,  ")
					.append("         MS.cPregunta,  MS.iCveTpoResp,  MS.cEtiqueta, ")
					.append("         ERM.lConcluido, ERM.dtFin, ERM.iCveUsuAplica,")
					.append("         U.cApPaterno || ' ' || U.cApMaterno || ' ' || U.cNombre as cMedico, ")
					.append("         IC.cSiglasProf, IC.cCedula ")
					.append(" from EXPResultado  ER ")
					.append(" inner join EXPRama ERM on ERM.iCveExpediente = ER.iCveExpediente ")
					.append("                       and ERM.iNumExamen     = ER.iNumExamen ")
					.append("                       and ERM.iCveServicio   = ER.iCveServicio ")
					.append("                       and ERM.iCveRama       = ER.iCveRama  ")
					.append(" inner join MEDRama MR on MR.iCveServicio = ER.iCveServicio  ")
					.append("                      and MR.iCveRama     = ER.iCveRama  ")
					.append(" inner join MEDSintomas MS on MS.iCveServicio = ER.iCveServicio  ")
					.append("                          and MS.iCveRama     = ER.iCveRama  ")
					.append("                          and MS.iCveSintoma  = ER.iCveSintoma  ")
					.append(" left join SEGUsuario U on U.iCveUsuario =  ERM.iCveUsuAplica ")
					.append(" left join GRLUsuario IC on IC.iCveUsuario = U.iCveUsuario ")
					.append(" where ER.iCveExpediente = ")
					.append(iCveExpediente)
					.append("   and ER.iNumExamen     = ").append(iNumExamen)
					.append("   and ER.iCveServicio   = ").append(iCveServicio);
			if (iCveRama > 0)
				cSQL.append("   and ER.iCveRama >= ").append(iCveRama);
			cSQL.append(
					" order by  ER.iCveExpediente, ER.iNumExamen, ER.iCveServicio, ER.iCveRama,   ")
					.append("           MR.iOrden, MS.iOrden ");

			// System.out.println("EXPRESULTADO = "+cSQL);
			int iCveTpoResp7 = 4;

			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vExpRama = new TVDinRep02();
				vExpRama.put("iCveExpediente", rset.getInt("iCveExpediente"));
				vExpRama.put("iNumExamen", rset.getInt("iNumExamen"));
				vExpRama.put("iCveServicio", rset.getInt("iCveServicio"));
				vExpRama.put("iCveRama", rset.getInt("iCveRama"));
				vExpRama.put("iCveSintoma", rset.getInt("iCveSintoma"));
				vExpRama.put("dValorIni", rset.getDouble("dValorIni"));
				vExpRama.put("lLogico", rset.getInt("lLogico"));
				vExpRama.put(
						"cCaracter",
						rset.getString("cCaracter") != null ? rset
								.getString("cCaracter") : "&nbsp;");
				vExpRama.put("dValorFin", rset.getDouble("dValorFin"));
				vExpRama.put("cValRef", rset.getString("cValRef") != null
						&& rset.getString("cValRef").trim().length() > 0 ? "("
						+ rset.getString("cValRef").trim() + ")" : "");
				vExpRama.put("iCveRama", rset.getInt("iCveRama"));
				vExpRama.put("cDscRama", rset.getString("cDscRama"));
				vExpRama.put("cPregunta", rset.getString("cPregunta"));
				if (rset.getInt("iCveTpoResp") == 7) {
					vExpRama.put("iCveTpoResp", iCveTpoResp7);
				} else {
					vExpRama.put("iCveTpoResp", rset.getInt("iCveTpoResp"));
				}

				vExpRama.put(
						"cEtiqueta",
						rset.getString("cEtiqueta") != null
								&& rset.getString("cEtiqueta").length() > 0 ? rset
								.getString("cEtiqueta") : "&nbsp;");
				vExpRama.put("lConcluido", rset.getInt("lConcluido"));
				vExpRama.put("dtFin", rset.getDate("dtFin"));
				vExpRama.put("iCveUsuAplica", rset.getInt("iCveUsuAplica"));
				vExpRama.put("cMedico", rset.getString("cMedico"));
				vExpRama.put(
						"cSiglasProf",
						rset.getString("cSiglasProf") != null ? rset
								.getString("cSiglasProf") : "");
				vExpRama.put("cCedula",
						rset.getString("cCedula") != null ? "(Ced. Prof."
								+ rset.getString("cCedula") + ")" : "");
				// Codificar el Valor L�gico
				switch (rset.getInt("lLogico")) {
				case 0:
					vExpRama.put("cLogico", iServLC == iCveServicio ? "(-)"
							: "No");
					break;
				case 1:
					vExpRama.put("cLogico", iServLC == iCveServicio ? "(+)"
							: "Si");
					break;
				default:
					vExpRama.put("cLogico", "&nbsp;");
				}
				// Dependiendo del Tipo de Respuesta es el valor a enviar

				switch (vExpRama.getInt("iCveTpoResp")) {
				case 1: // L�gico
					vExpRama.put("cResultado", vExpRama.getString("cLogico"));
					break;
				case 2: // Caracter
				case 4: // Nota M�dica
					vExpRama.put("cResultado", vExpRama.getString("cCaracter"));
					break;
				case 3: // Num�rico
					vExpRama.put("cResultado", vExpRama.getString("dValorIni"));
					break;
				case 5:
					vExpRama.put("cResultado", vExpRama.getString("dValorIni")
							+ " - " + vExpRama.getString("dValorFin"));
					break;
				case 6: // T�tulo
				case 8:
					vExpRama.put("cResultado", vExpRama.getString("cCaracter"));
					break;
				case 11:
					vExpRama.put("cResultado", vExpRama.getString("cCaracter"));
					break;
				case 13:
					vExpRama.put("cResultado", vExpRama.getString("cCaracter"));
					break;
				default:
					break;
				}
				vResultado.add(vExpRama);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("findResultExamA");
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
				warn("findResultExamA.close", ex2);
			}
		}
		return vResultado;
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
	

	/**
	 * Método Find By All
	 * 
	 * @author AG SA
	 * 
	 */
	public String FindByDispositivo(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String DatosDispositivo = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPResultado vEXPResultado;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "EXPResultado.cCaracter "
					+" from EXPResultado " + cWhere + "";
			
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				DatosDispositivo = rset.getString(1);
			}
			//System.out.println(DatosDispositivo);
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
			return DatosDispositivo;
		}
	}

}
