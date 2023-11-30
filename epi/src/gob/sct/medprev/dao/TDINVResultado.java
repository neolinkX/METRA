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
 * Title: Data Acces Object de INVResultado DAO
 * </p>
 * <p>
 * Description: DAO INVResultado
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

public class TDINVResultado extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDINVResultado() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVResultado vINVResultado;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveMdoTrans," + "iIDDGPMPT,"
					+ "iCveServicio," + "iCveRama," + "iCveSintoma,"
					+ "dValorIni," + "lLogico," + "cCaracter," + "dValorFin"
					+ " from INVResultado " + cWhere + " order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVResultado = new TVINVResultado();
				vINVResultado.setIAnio(rset.getInt(1));
				vINVResultado.setICveMdoTrans(rset.getInt(2));
				vINVResultado.setIIDDGPMPT(rset.getInt(3));
				vINVResultado.setICveServicio(rset.getInt(4));
				vINVResultado.setICveRama(rset.getInt(5));
				vINVResultado.setICveSintoma(rset.getInt(6));
				vINVResultado.setDValorIni(rset.getFloat(7));
				vINVResultado.setLLogico(rset.getInt(8));
				vINVResultado.setCCaracter(rset.getString(9));
				vINVResultado.setDValorFin(rset.getFloat(10));
				vcINVResultado.addElement(vINVResultado);
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
			return vcINVResultado;
		}
	}

	/**
	 * Metodo FindBy All With Join
	 */
	public Vector FindByAllWithJoin(String cWhere, int iCveProceso)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVResultado vINVResultado;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "    select a.iAnio," + "           a.iCveMdoTrans,"
					+ "           a.iIDDGPMPT," + "           a.iCveServicio,"
					+ "           a.iCveRama," + "           a.iCveSintoma,"
					+ "           a.dValorIni," + "           a.lLogico,"
					+ "           a.cCaracter," + "           a.dValorFin,"
					+ "           b.cDscRama," + "           c.cPregunta,"
					+ "           c.iCveTpoResp," + "           c.lObligatorio"
					+ "    From   INVResultado a, " + "           MEDRama b,"
					+ "           MEDSintomas c,"
					+ "           MEDSintExamen d " + cWhere;
			if (cWhere.trim().length() > 0) {
				cSQL += "    And    a.iCveRama =  b.iCveRama";
			} else {
				cSQL += "    Where  a.iCveRama =  b.iCveRama";
			}
			cSQL += "    And    b.iCveServicio = a.iCveServicio";
			cSQL += "    And c.iCveServicio    = a.iCveServicio";
			cSQL += "    And    a.iCveSintoma  = c.iCveSintoma";
			cSQL += "    And    a.iCveRama     = c.iCveRama";
			cSQL += "    And    b.lActivo = 1";
			cSQL += "    And    c.lActivo = 1";
			cSQL += "    And    d.iCveProceso = 4";
			cSQL += "    And    d.iCveServicio = a.iCveServicio";
			cSQL += "    And    d.iCveRama = a.iCveRama";
			cSQL += "    And    d.iCveSintoma = a.iCveSintoma";
			cSQL += "    And    d.iCveMdoTrans = a.iCveMdoTrans";
			// cSQL+="    Group by a.iCveRama,a.iCveSintoma,";
			// cSQL+="             a.iAnio,";
			// cSQL+="             a.iCveMdoTrans,";
			// cSQL+="             a.iIDDGPMPT,";
			// cSQL+="             a.iCveServicio,";
			// cSQL+="             a.dValorIni,";
			// cSQL+="             a.lLogico,";
			// cSQL+="             a.cCaracter,";
			// cSQL+="             a.dValorFin,";
			// cSQL+="             b.cDscRama,";
			// cSQL+="             c.cPregunta,";
			// cSQL+="             c.iCveTpoResp,";
			// cSQL+="             c.lObligatorio";
			cSQL += " order by a.iAnio,";
			cSQL += "          a.iCveMdoTrans,";
			cSQL += "          a.iIDDGPMPT,";
			cSQL += "          a.iCveServicio,";
			cSQL += "          b.iOrden,";
			cSQL += "          c.iOrden";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVResultado = new TVINVResultado();
				vINVResultado.setIAnio(rset.getInt(1));
				vINVResultado.setICveMdoTrans(rset.getInt(2));
				vINVResultado.setIIDDGPMPT(rset.getInt(3));
				vINVResultado.setICveServicio(rset.getInt(4));
				vINVResultado.setICveRama(rset.getInt(5));
				vINVResultado.setICveSintoma(rset.getInt(6));
				vINVResultado.setDValorIni(rset.getFloat(7));
				vINVResultado.setLLogico(rset.getInt(8));
				vINVResultado.setCCaracter(rset.getString(9));
				vINVResultado.setDValorFin(rset.getFloat(10));
				vINVResultado.setCDscRama(rset.getString(11));
				vINVResultado.setCPregunta(rset.getString(12));
				vINVResultado.setICveTpoResp(rset.getInt(13));
				vINVResultado.setLObligatorio(rset.getInt(14));
				vcINVResultado.addElement(vINVResultado);
			}
		} catch (Exception ex) {
			warn("FindByAllWithJoin", ex);
			throw new DAOException("FindByAllWithJoin");
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
				warn("FindByAllWithJoin.close", ex2);
			}
			return vcINVResultado;
		}
	}

	/**
	 * Metodo Insert - No Utilizado
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
			TVINVResultado vINVResultado = (TVINVResultado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into INVResultado(" + "iAnio," + "iCveMdoTrans,"
					+ "iIDDGPMPT," + "iCveServicio," + "iCveRama,"
					+ "iCveSintoma," + "dValorIni," + "lLogico," + "cCaracter,"
					+ "dValorFin" + ")values(?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVResultado.getIAnio());
			pstmt.setInt(2, vINVResultado.getICveMdoTrans());
			pstmt.setInt(3, vINVResultado.getIIDDGPMPT());
			pstmt.setInt(4, vINVResultado.getICveServicio());
			pstmt.setInt(5, vINVResultado.getICveRama());
			pstmt.setInt(6, vINVResultado.getICveSintoma());
			pstmt.setFloat(7, vINVResultado.getDValorIni());
			pstmt.setInt(8, vINVResultado.getLLogico());
			pstmt.setString(9, vINVResultado.getCCaracter());
			pstmt.setFloat(10, vINVResultado.getDValorFin());
			pstmt.executeUpdate();
			cClave = "" + vINVResultado.getIAnio();
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
	 * Metodo insertFromExpExamen
	 */
	public Object insertFromExpExamen(Connection conGral, int iAnio,
			int iCveMdoTrans, int iIDDGPMPT, int iCveServicio, int iCveRama,
			int iCveSintoma) throws DAOException {
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
			cSQL = "insert into INVResultado(" + "iAnio," + "iCveMdoTrans,"
					+ "iIDDGPMPT," + "iCveServicio," + "iCveRama,"
					+ "iCveSintoma," + "dValorIni," + "lLogico," + "dValorFin"
					+ ")values(?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iCveMdoTrans);
			pstmt.setInt(3, iIDDGPMPT);
			pstmt.setInt(4, iCveServicio);
			pstmt.setInt(5, iCveRama);
			pstmt.setInt(6, iCveSintoma);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.executeUpdate();
			cClave = "" + iAnio;
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
	 * Metodo update
	 */
	public Object Update(Connection conGral, Object obj) throws DAOException {
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
			TVINVResultado vINVResultado = (TVINVResultado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "Update INVResultado" + "       Set dValorIni= ?, "
					+ "       lLogico= ?, " + "       cCaracter= ?, "
					+ "       dValorFin= ? " + " Where iAnio = ? "
					+ " And   iCveMdoTrans = ?" + " And   iIDDGPMPT = ?"
					+ " And   iCveRama = ?" + " And   iCveSintoma = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setFloat(1, vINVResultado.getDValorIni());
			pstmt.setInt(2, vINVResultado.getLLogico());
			pstmt.setString(3, vINVResultado.getCCaracter());
			pstmt.setFloat(4, vINVResultado.getDValorFin());
			pstmt.setInt(5, vINVResultado.getIAnio());
			pstmt.setInt(6, vINVResultado.getICveMdoTrans());
			pstmt.setInt(7, vINVResultado.getIIDDGPMPT());
			pstmt.setInt(8, vINVResultado.getICveRama());
			pstmt.setInt(9, vINVResultado.getICveSintoma());
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
	 * Metodo Delete
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
			TVINVResultado vINVResultado = (TVINVResultado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from INVResultado" + " where iAnio = ?"
					+ " and iCveMdoTrans = ?" + " and iIDDGPMPT = ?"
					+ " and iCveServicio = ?" + " and iCveRama = ?"
					+ " and iCveSintoma = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVResultado.getIAnio());
			pstmt.setInt(2, vINVResultado.getICveMdoTrans());
			pstmt.setInt(3, vINVResultado.getIIDDGPMPT());
			pstmt.setInt(4, vINVResultado.getICveServicio());
			pstmt.setInt(5, vINVResultado.getICveRama());
			pstmt.setInt(6, vINVResultado.getICveSintoma());
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
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("delete.closeINVResultado", ex2);
			}
			return cClave;
		}
	}
}