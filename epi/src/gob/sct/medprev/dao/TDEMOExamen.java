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
 * Title: Data Acces Object de EMOExamen DAO
 * </p>
 * <p>
 * Description: DAO EMO Examen
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

public class TDEMOExamen extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEMOExamen() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEMOExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEMOExamen vEMOExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveMomentoAp," + "cMatricula," + "iCvePaisOr,"
					+ "iCveEdoOr," + "cOrigen," + "iCvePaisDes,"
					+ "iCveEdoDes," + "cDestino," + "lSinLicencia,"
					+ "cLicencia," + "dtVenceLic," + "lLicVencida"
					+ " from EMOExamen order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEMOExamen = new TVEMOExamen();
				vEMOExamen.setICveExpediente(rset.getInt(1));
				vEMOExamen.setINumExamen(rset.getInt(2));
				vEMOExamen.setICveMomentoAp(rset.getInt(3));
				vEMOExamen.setCMatricula(rset.getString(4));
				vEMOExamen.setICvePaisOr(rset.getInt(5));
				vEMOExamen.setICveEdoOr(rset.getInt(6));
				vEMOExamen.setCOrigen(rset.getString(7));
				vEMOExamen.setICvePaisDes(rset.getInt(8));
				vEMOExamen.setICveEdoDes(rset.getInt(9));
				vEMOExamen.setCDestino(rset.getString(10));
				vEMOExamen.setLSinLicencia(rset.getInt(11));
				vEMOExamen.setCLicencia(rset.getString(12));
				vEMOExamen.setDtVenceLic(rset.getDate(13));
				vEMOExamen.setLLicVencida(rset.getInt(14));
				vcEMOExamen.addElement(vEMOExamen);
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
			return vcEMOExamen;
		}
	}

	/**
	 * 
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
			TVEMOExamen vEMOExamen = (TVEMOExamen) obj;
			int iNumExamen = getINumExamenMaximo(vEMOExamen.getICveExpediente());
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EMOExamen(" + "iCveExpediente," + "iNumExamen,"
					+ "iCveMomentoAp," + "cMatricula," + "iCvePaisOr,"
					+ "iCveEdoOr," + "cOrigen," + "iCvePaisDes,"
					+ "iCveEdoDes," + "cDestino," + "lSinLicencia,"
					+ "cLicencia," + "dtVenceLic," + "lLicVencida,"
					+ "iCveCapturaDelExamen," + "iCveUsuAplicoEMO," 
					+ "cUsuAplicoEmo," + "cUsuAplicoEmoCedula," 
					+ "iCveFolio," + "iCveSubModulo, tiAplicacionEMO"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?)";

			System.out.println(cSQL);
			
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEMOExamen.getICveExpediente());
			pstmt.setInt(2, iNumExamen);
			pstmt.setInt(3, vEMOExamen.getICveMomentoAp());
			pstmt.setString(4, vEMOExamen.getCMatricula());
			pstmt.setInt(5, vEMOExamen.getICvePaisOr());
			pstmt.setInt(6, vEMOExamen.getICveEdoOr());
			pstmt.setString(7, vEMOExamen.getCOrigen());
			pstmt.setInt(8, vEMOExamen.getICvePaisDes());
			pstmt.setInt(9, vEMOExamen.getICveEdoDes());
			pstmt.setString(10, vEMOExamen.getCDestino());
			pstmt.setInt(11, vEMOExamen.getLSinLicencia());
			pstmt.setString(12, vEMOExamen.getCLicencia());
			pstmt.setDate(13, vEMOExamen.getDtVenceLic());
			pstmt.setInt(14, vEMOExamen.getLLicVencida());
			
			pstmt.setInt(15, vEMOExamen.getICveCapturaDelExamen());
			pstmt.setInt(16, vEMOExamen.getICveUsuAplicoEMO());
			pstmt.setString(17, vEMOExamen.getCMedDic());
			pstmt.setString(18, vEMOExamen.getCCedula());
			pstmt.setString(19, vEMOExamen.getICveFolio());
			pstmt.setInt(20, vEMOExamen.getICveSubModulo());
			pstmt.setTimestamp(21, vEMOExamen.getTIAplicacion());
			
			System.out.println("Seteo finalizado");
			
			pstmt.executeUpdate();
			
			System.out.println("Query ejecutado");
			cClave = "" + vEMOExamen.getICveExpediente();
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
			TVEMOExamen vEMOExamen = (TVEMOExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EMOExamen" + " set iCveMomentoAp= ?, "
					+ "cMatricula= ?, " + "iCvePaisOr= ?, " + "iCveEdoOr= ?, "
					+ "cOrigen= ?, " + "iCvePaisDes= ?, " + "iCveEdoDes= ?, "
					+ "cDestino= ?, " + "lSinLicencia= ?, " + "cLicencia= ?, "
					+ "dtVenceLic= ?, " + "lLicVencida= ? "
					+ "where iCveExpediente = ? " + " and iNumExamen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEMOExamen.getICveMomentoAp());
			pstmt.setString(2, vEMOExamen.getCMatricula());
			pstmt.setInt(3, vEMOExamen.getICvePaisOr());
			pstmt.setInt(4, vEMOExamen.getICveEdoOr());
			pstmt.setString(5, vEMOExamen.getCOrigen());
			pstmt.setInt(6, vEMOExamen.getICvePaisDes());
			pstmt.setInt(7, vEMOExamen.getICveEdoDes());
			pstmt.setString(8, vEMOExamen.getCDestino());
			pstmt.setInt(9, vEMOExamen.getLSinLicencia());
			pstmt.setString(10, vEMOExamen.getCLicencia());
			pstmt.setDate(11, vEMOExamen.getDtVenceLic());
			pstmt.setInt(12, vEMOExamen.getLLicVencida());
			pstmt.setInt(13, vEMOExamen.getICveExpediente());
			pstmt.setInt(14, vEMOExamen.getINumExamen());
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
			TVEMOExamen vEMOExamen = (TVEMOExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EMOExamen" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEMOExamen.getICveExpediente());
			pstmt.setInt(2, vEMOExamen.getINumExamen());
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
				warn("delete.closeEMOExamen", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo getINumExamenMaximo
	 */
	public int getINumExamenMaximo(int iCveExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iMax = 1;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			pstmt = conn
					.prepareStatement("select max(iNumExamen) cta from EXPExamAplica where iCveExpediente=?");
			pstmt.setInt(1, iCveExpediente);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				iMax = rset.getInt("cta");
			}
		} catch (Exception ex) {
			warn("getINumExamenMaximo", ex);
			throw new DAOException("getINumExamenMaximo");
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
				warn("getINumExamenMaximo.close", ex2);
			}
			return iMax;
		}
	}
	
	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllEMOV2(String exp, String exa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEMOExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEMOExamen vEMOExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "+
					"	E.iCveExpediente, "+
					"	E.iNumExamen, "+
					"	E.iCveMomentoAp, "+
					"	E.cMatricula, "+
					"	E.iCvePaisOr, "+
					"	E.iCveEdoOr, "+
					"	E.cOrigen, "+
					"	E.iCvePaisDes, "+
					"	E.iCveEdoDes, "+
					"	E.cDestino, "+
					"	E.lSinLicencia, "+
					"	E.cLicencia, "+
					"	E.dtVenceLic, "+
					"	E.lLicVencida, "+
					"	E.iCveUsuAplicoEmo, "+
					"	E.cUsuAplicoEmo, "+
					"	E.cUsuAplicoEmoCedula, "+
					"	E.iCveFolio, "+
					"	E.tiAplicacionEmo, "+
					"	E.iCveCapturaDelExamen, "+
					"	F.CNOMBRE, "+
					"	G.CNOMBRE, "+
					"	S.CNOMBRE, "+
					"	S.CAPPATERNO, "+
					"	S.CAPMATERNO "+
					"from  "+ 
					"	EMOExamen AS E, "+
					"	GRLENTIDADFED AS F, "+
					"	GRLENTIDADFED AS G, "+
					"	SEGUSUARIO AS S "+
					"where  "+
					"	E.iCvePaisOr = F.iCvePais and "+
					"	E.iCveEdoOr = F.iCveEntidadFed and "+
					"	E.iCvePaisDes = G.iCvePais and "+
					"	E.iCveEdoDes = G.iCveEntidadFed and "+
					"	E.iCveUsuAplicoEmo = S.iCveUsuario and "+
					"	E.icveexpediente = "+exp+" and "+
					"	E.inumexamen = "+exa+
					"   order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEMOExamen = new TVEMOExamen();
				vEMOExamen.setICveExpediente(rset.getInt(1));
				vEMOExamen.setINumExamen(rset.getInt(2));
				vEMOExamen.setICveMomentoAp(rset.getInt(3));
				vEMOExamen.setCMatricula(rset.getString(4));
				vEMOExamen.setICvePaisOr(rset.getInt(5));
				vEMOExamen.setICveEdoOr(rset.getInt(6));
				vEMOExamen.setCOrigen(rset.getString(7));
				vEMOExamen.setICvePaisDes(rset.getInt(8));
				vEMOExamen.setICveEdoDes(rset.getInt(9));
				vEMOExamen.setCDestino(rset.getString(10));
				vEMOExamen.setLSinLicencia(rset.getInt(11));
				vEMOExamen.setCLicencia(rset.getString(12));
				vEMOExamen.setDtVenceLic(rset.getDate(13));
				vEMOExamen.setLLicVencida(rset.getInt(14));
				
				vEMOExamen.setICveUsuAplicoEMO(rset.getInt(15));
				vEMOExamen.setCMedDic(rset.getString(16));
				vEMOExamen.setCCedula(rset.getString(17));
				vEMOExamen.setICveFolio(rset.getString(18));
				vEMOExamen.setTIAplicacion(rset.getTimestamp(19));
				vEMOExamen.setICveCapturaDelExamen(rset.getInt(20));
				vEMOExamen.setCDscEstadoOrg(rset.getString(21));
				vEMOExamen.setCDscEstadoDes(rset.getString(22));
				vEMOExamen.setCNombre(rset.getString(23));
				vEMOExamen.setCApPaterno(rset.getString(24));
				vEMOExamen.setCApMaterno(rset.getString(25));
				
				vcEMOExamen.addElement(vEMOExamen);
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
			return vcEMOExamen;
		}
	}

}