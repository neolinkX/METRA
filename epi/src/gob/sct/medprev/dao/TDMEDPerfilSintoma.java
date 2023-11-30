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
 * Title: Data Acces Object de MEDPerfilSintoma DAO
 * </p>
 * <p>
 * Description: Data Access Object de la tabla MEDPerfilSintoma
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Romeo S�nchez
 * @version 1.0
 */

public class TDMEDPerfilSintoma extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDPerfilSintoma() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilSintoma = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilSintoma vMEDPerfilSintoma;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePerfil," + "iCveServicio," + "iCveRama,"
					+ "iCveSintoma," + "dValorI," + "dValorF," + "lLogico,"
					+ "cCaracter"
					+ " from MEDPerfilSintoma order by iCvePerfil";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilSintoma = new TVMEDPerfilSintoma();
				vMEDPerfilSintoma.setICvePerfil(rset.getInt(1));
				vMEDPerfilSintoma.setICveServicio(rset.getInt(2));
				vMEDPerfilSintoma.setICveRama(rset.getInt(3));
				vMEDPerfilSintoma.setICveSintoma(rset.getInt(4));
				vMEDPerfilSintoma.setDValorI(rset.getFloat(5));
				vMEDPerfilSintoma.setDValorF(rset.getFloat(6));
				vMEDPerfilSintoma.setLLogico(rset.getInt(7));
				vMEDPerfilSintoma.setCCaracter(rset.getString(8));
				vcMEDPerfilSintoma.addElement(vMEDPerfilSintoma);
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
			return vcMEDPerfilSintoma;
		}
	}

	public Vector findByJoin(String perfil, String servicio, String rama)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilSintoma = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilSintoma vMEDPerfilSintoma;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT  "
					+ " (select iCvePerfil from MEDPerfilSintoma where iCvePerfil="
					+ perfil
					+ "  and iCveServicio=s.iCveServicio "
					+ "  and iCveRama=s.iCveRama "
					+ "  and iCveSintoma=s.iCveSintoma) "
					+ "       as iCvePerfil, "
					+ " s.iCveServicio, "
					+ " s.iCveRama, "
					+ " s.iCveSintoma, "
					+ " (select dValorI from MEDPerfilSintoma where iCvePerfil="
					+ perfil
					+ "  and iCveServicio=s.iCveServicio "
					+ "  and iCveRama=s.iCveRama "
					+ "  and iCveSintoma=s.iCveSintoma) "
					+ "       as dValorI, "
					+ " (select dValorF from MEDPerfilSintoma where iCvePerfil="
					+ perfil
					+ "  and iCveServicio=s.iCveServicio "
					+ "  and iCveRama=s.iCveRama "
					+ "  and iCveSintoma=s.iCveSintoma) "
					+ "       as dValorF, "
					+ " (select lLogico from MEDPerfilSintoma where iCvePerfil="
					+ perfil
					+ "  and iCveServicio=s.iCveServicio "
					+ "  and iCveRama=s.iCveRama "
					+ "  and iCveSintoma=s.iCveSintoma) "
					+ "       as lLogico, "
					+ " (select cCaracter from MEDPerfilSintoma where iCvePerfil="
					+ perfil
					+ "  and iCveServicio=s.iCveServicio "
					+ "  and iCveRama=s.iCveRama "
					+ "  and iCveSintoma=s.iCveSintoma) "
					+ "       as cCaracter, "
					+ " s.cPregunta, "
					+ " s.iCveTpoResp, "
					+ " t.cDscTpoResp "
					+ " FROM MEDSintomas s  , "
					+ " MEDTpoResp t  "
					+ " WHERE s.lActivo=1  "
					+ " AND  t.iCveTpoResp = s.iCveTpoResp  "
					+ " AND s.iCveServicio = "
					+ servicio
					+ " AND s.iCveRama = " + rama + " ORDER BY iCveSintoma ";
			pstmt = conn.prepareStatement(cSQL);
			// System.out.println(this.getClass().getName()+".findByJoin SQL: "
			// + cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilSintoma = new TVMEDPerfilSintoma();
				vMEDPerfilSintoma.setICvePerfil(rset.getInt(1));
				vMEDPerfilSintoma.setICveServicio(rset.getInt(2));
				vMEDPerfilSintoma.setICveRama(rset.getInt(3));
				vMEDPerfilSintoma.setICveSintoma(rset.getInt(4));
				vMEDPerfilSintoma.setDValorI(rset.getFloat(5));
				vMEDPerfilSintoma.setDValorF(rset.getFloat(6));
				vMEDPerfilSintoma.setLLogico(rset.getInt(7));
				vMEDPerfilSintoma.setCCaracter(rset.getString(8));
				vMEDPerfilSintoma.setCPregunta(rset.getString(9));
				vMEDPerfilSintoma.setICveTpoResp(rset.getInt(10));
				vMEDPerfilSintoma.setCDscTpoResp(rset.getString(11));
				vcMEDPerfilSintoma.addElement(vMEDPerfilSintoma);
			}
		} catch (Exception ex) {
			warn("FindByJoin", ex);
			throw new DAOException("FindByJoin");
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
			return vcMEDPerfilSintoma;
		}
	}

	public Vector findByJoin(String where, String orderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilSintoma = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilSintoma vMEDPerfilSintoma;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT  " + " p.iCvePerfil, " + " s.iCveServicio, "
					+ " s.iCveRama, " + " s.iCveSintoma, " + " p.dValorI, "
					+ " p.dValorF, " + " p.lLogico, " + " p.cCaracter, "
					+ " s.cPregunta, " + " s.iCveTpoResp, " + " t.cDscTpoResp "
					+ " FROM MEDPerfilSintoma p "
					+ " RIGHT JOIN MEDSintomas s ON  ( "
					+ "      p.iCveServicio = s.iCveServicio  AND "
					+ "      p.iCveRama = s.iCveRama AND "
					+ "      p.iCveSintoma = s.iCveSintoma "
					+ " ), MEDTpoResp t " + " WHERE s.lActivo=1 "
					+ " AND  t.iCveTpoResp = s.iCveTpoResp " + where + orderBy;
			pstmt = conn.prepareStatement(cSQL);
			// System.out.println(this.getClass().getName()+".findByJoin SQL: "
			// + cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilSintoma = new TVMEDPerfilSintoma();
				vMEDPerfilSintoma.setICvePerfil(rset.getInt(1));
				vMEDPerfilSintoma.setICveServicio(rset.getInt(2));
				vMEDPerfilSintoma.setICveRama(rset.getInt(3));
				vMEDPerfilSintoma.setICveSintoma(rset.getInt(4));
				vMEDPerfilSintoma.setDValorI(rset.getFloat(5));
				vMEDPerfilSintoma.setDValorF(rset.getFloat(6));
				vMEDPerfilSintoma.setLLogico(rset.getInt(7));
				vMEDPerfilSintoma.setCCaracter(rset.getString(8));
				vMEDPerfilSintoma.setCPregunta(rset.getString(9));
				vMEDPerfilSintoma.setICveTpoResp(rset.getInt(10));
				vMEDPerfilSintoma.setCDscTpoResp(rset.getString(11));
				vcMEDPerfilSintoma.addElement(vMEDPerfilSintoma);
			}
		} catch (Exception ex) {
			warn("FindByJoin", ex);
			throw new DAOException("FindByJoin");
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
			return vcMEDPerfilSintoma;
		}
	}

	/**
	 * Metodo Insert
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
			TVMEDPerfilSintoma vMEDPerfilSintoma = (TVMEDPerfilSintoma) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDPerfilSintoma(" + "iCvePerfil,"
					+ "iCveServicio," + "iCveRama," + "iCveSintoma,"
					+ "dValorI," + "dValorF," + "lLogico," + "cCaracter"
					+ ")values(?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vMEDPerfilSintoma.getICvePerfil());
			pstmt.setInt(2, vMEDPerfilSintoma.getICveServicio());
			pstmt.setInt(3, vMEDPerfilSintoma.getICveRama());
			pstmt.setInt(4, vMEDPerfilSintoma.getICveSintoma());
			pstmt.setFloat(5, vMEDPerfilSintoma.getDValorI());
			pstmt.setFloat(6, vMEDPerfilSintoma.getDValorF());
			pstmt.setInt(7, vMEDPerfilSintoma.getLLogico());
			pstmt.setString(8, vMEDPerfilSintoma.getCCaracter());
			pstmt.executeUpdate();
			cClave = "" + vMEDPerfilSintoma.getICvePerfil();
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
	 * Metodo update
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
			TVMEDPerfilSintoma vMEDPerfilSintoma = (TVMEDPerfilSintoma) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDPerfilSintoma" + " set dValorI= ?, "
					+ "dValorF= ?, " + "lLogico= ?, " + "cCaracter= ? "
					+ "where iCvePerfil = ? " + "and iCveServicio = ?"
					+ "and iCveRama = ?" + " and iCveSintoma = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setFloat(1, vMEDPerfilSintoma.getDValorI());
			pstmt.setFloat(2, vMEDPerfilSintoma.getDValorF());
			pstmt.setInt(3, vMEDPerfilSintoma.getLLogico());
			pstmt.setString(4, vMEDPerfilSintoma.getCCaracter());
			pstmt.setInt(5, vMEDPerfilSintoma.getICvePerfil());
			pstmt.setInt(6, vMEDPerfilSintoma.getICveServicio());
			pstmt.setInt(7, vMEDPerfilSintoma.getICveRama());
			pstmt.setInt(8, vMEDPerfilSintoma.getICveSintoma());
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
				if (dbConn != null)
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
			TVMEDPerfilSintoma vMEDPerfilSintoma = (TVMEDPerfilSintoma) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDPerfilSintoma" + " where iCvePerfil = ?"
					+ " and iCveServicio = ?" + " and iCveRama = ?"
					+ " and iCveSintoma = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDPerfilSintoma.getICvePerfil());
			pstmt.setInt(2, vMEDPerfilSintoma.getICveServicio());
			pstmt.setInt(3, vMEDPerfilSintoma.getICveRama());
			pstmt.setInt(4, vMEDPerfilSintoma.getICveSintoma());
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
				warn("delete.closeMEDPerfilSintoma", ex2);
			}
			return cClave;
		}
	}
}
