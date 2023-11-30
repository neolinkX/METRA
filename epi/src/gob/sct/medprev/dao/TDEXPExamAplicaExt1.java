package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;

import com.micper.util.*;
import com.micper.seguridad.vo.TVDinRep02;

/**
 * <p>
 * Title: Data Acces Object de EXPExamAplica DAO
 * </p>
 * <p>
 * Description: Data Access Object para EXPExamAplica
 * </p>
  
 * @author AG SA L
 * @version 1.0
 * @fecha 3 de marzo 2015
 */

public class TDEXPExamAplicaExt1 extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPExamAplicaExt1() {
	}

	/**
	 * Método findUser (Utilizado para la Selecci�n de personal Asegurandose que
	 * lIniciado de la tabla de EXPExamAplica esta en "falso" (0))
	 * 
	 * @Autor: AG SA
	 */
	public TVPERDatos findUserV2(int iCvePersonal)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select"
					+ " a.iCvePersonal,a.iCveExpediente,a.cNombre,a.cApPaterno,a.cApMaterno,a.cSexo, "
					+ " a.dtNacimiento,a.iCveNumEmpresa,a.cRFC"
					+ " from PerDatos a "
					+ " inner join EXPExamAplica b on   a.iCveExpediente = b.iCveExpediente "
					+ " Where a.iCvePersonal= " + iCvePersonal
					+ " And   b.iNumExamen = (Select max(p.inumexamen) from expexamaplica as p where p.icveexpediente = b.icveexpediente) " +
					" And   a.iCvePersonal = b.iCvePersonal";
			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				vPERDatos = new TVPERDatos();
				vPERDatos.setICvePersonal(rset.getInt("iCvePersonal"));
				vPERDatos.setICveExpediente(rset.getInt("iCveExpediente"));
				vPERDatos.setCNombre(rset.getString("cNombre"));
				vPERDatos.setCApPaterno(rset.getString("cApPaterno"));
				vPERDatos.setCApMaterno(rset.getString("cApMaterno"));
				vPERDatos.setCSexo(rset.getString("cSexo"));
				vPERDatos.setDtNacimiento(rset.getDate("dtNacimiento"));
				vPERDatos.setICveNumEmpresa(rset.getInt("iCveNumEmpresa"));
				vPERDatos.setCRFC(rset.getString("cRFC"));
			}
		} catch (Exception ex) {
			warn("findUser", ex);
			throw new DAOException("findUser");
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
				warn("findUser.close", ex2);
			}
			return vPERDatos;
		}
	}	
	
	/**
	 * Método findExpToDelete (Utilizado en la sección de ADMINISTRACIÓN DE
	 * EXPEDIENTES BORRAR EXPEDIENTE )
	 * 
	 * @Autor: Ivan Santiago Méndez
	 */
	public TVPERDatos findExpToEliminaBiometricoNAS(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "a.iCvePersonal,a.iCveExpediente,a.cNombre,a.cApPaterno,a.cApMaterno, "
					+ "a.cSexo,a.dtNacimiento,a.iCveNumEmpresa,a.cRFC, b.iNumExamen, c.cdscunimed "
					+ "from PerDatos a "
					+ "inner join  EXPExamAplica b  on a.iCveExpediente = b.iCveExpediente "
					+ " join GRLUNIMED c on b.icveunimed = c.icveunimed "
					+ "where a.iCveExpediente= "
					+ vEXPExamAplica.getICveExpediente()
					+ " And   b.INUMEXAMEN = (Select max(p.inumexamen) from expexamaplica as p where p.icveexpediente = b.icveexpediente) ";
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			vPERDatos = new TVPERDatos();
			while (rset.next()) {
				vPERDatos.setICvePersonal(rset.getInt("iCvePersonal"));
				vPERDatos.setICveExpediente(rset.getInt("iCveExpediente"));
				vPERDatos.setCNombre(rset.getString("cNombre"));
				vPERDatos.setCApPaterno(rset.getString("cApPaterno"));
				vPERDatos.setCApMaterno(rset.getString("cApMaterno"));
				vPERDatos.setCSexo(rset.getString("cSexo"));
				vPERDatos.setDtNacimiento(rset.getDate("dtNacimiento"));
				vPERDatos.setICveNumEmpresa(rset.getInt("iCveNumEmpresa"));
				vPERDatos.setCRFC(rset.getString("cRFC"));
				vPERDatos.setINumExamen(rset.getInt("iNumExamen"));
				vPERDatos.setCDscEmpresa(rset.getString("cdscunimed"));
			}
		} catch (Exception ex) {
			warn("findUserbyExp", ex);
			throw new DAOException("findUserbyExp");
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
				warn("findUserbyExp.close", ex2);
			}
			return vPERDatos;
		}
	}

	
	/**
	 * Método Find By All
	 */
	public Vector FindByAllReglaExpVirtual(int iCveExpediente, int usuario) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamAplica vEXPExamAplica;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT A.ICVEUNIMED, A.ICVEMODULO, A.INUMEXAMEN, A.LDICTAMINADO, A.DTSOLICITADO, A.DTDICTAMEN, (CURRENT DATE) AS HOY "+
					"FROM  "+
					"	EXPEXAMAPLICA AS A, "+
					"	EXPEXAMCAT AS B, "+
					"	GRLUSUMEDICOS C "+
					"WHERE  "+
					"A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE AND "+
					"A.INUMEXAMEN = B.INUMEXAMEN AND "+
					"A.ICVEEXPEDIENTE = ? AND "+
					"A.INUMEXAMEN = (SELECT MAX(AA.INUMEXAMEN) FROM EXPEXAMAPLICA AS AA WHERE AA.ICVEEXPEDIENTE = A.ICVEEXPEDIENTE ) AND "+
					"A.ICVEUNIMED = C.ICVEUNIMED AND "+
					"A.ICVEMODULO = C.ICVEMODULO AND "+
					"C.ICVEUSUARIO = ? "+
					"GROUP BY A.ICVEUNIMED, A.ICVEMODULO, A.INUMEXAMEN, A.LDICTAMINADO, A.DTSOLICITADO, A.DTDICTAMEN";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iCveExpediente);
			pstmt.setInt(2, usuario);

			//System.out.println(cSQL);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica = new TVEXPExamAplica();
				vEXPExamAplica.setICveUniMed(rset.getInt(1));
				vEXPExamAplica.setICveModulo(rset.getInt(2));
				vEXPExamAplica.setINumExamen(rset.getInt(3));
				vEXPExamAplica.setLDictaminado(rset.getInt(4));
				vEXPExamAplica.setDtSolicitado(rset.getDate(5));
				vEXPExamAplica.setDtDictamen(rset.getDate(6));
				vEXPExamAplica.setDtAplicacion(rset.getDate(7));
				vcEXPExamAplica.addElement(vEXPExamAplica);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAllReglaExpVirtual");
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
			return vcEXPExamAplica;
		}
	}
		
	
}
