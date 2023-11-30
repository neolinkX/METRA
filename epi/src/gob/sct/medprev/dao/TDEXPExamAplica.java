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
import java.util.StringTokenizer;

/**
 * <p>
 * Title: Data Acces Object de EXPExamAplica DAO  
 * </p>
 * <p>
 * Description: Data Access Object para EXPExamAplica
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros - Romeo Sanchez - Javier Mendoza - Marco Gonz�lez
 * @version 1.0
 */ 

public class TDEXPExamAplica extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPExamAplica() {
	}

	/**
	 * Método Find By All
	 */
	public Vector FindByAll() throws DAOException {
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
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveUniMed," + "iCveProceso," + "iCvePersonal,"
					+ "iCveModulo," + "dtSolicitado," + "iFolioEs,"
					+ "dtAplicacion," + "dtConcluido," + "lIniciado,"
					+ "lDictaminado," + "lInterConsulta," + "lInterConcluye,"
					+ "lConcluido," + "dtDictamen," + "dtEntregaRes,"
					+ "dtArchivado," + "iCveNumEmpresa," + "iCveUsuSolicita"
					+ " from EXPExamAplica order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica = new TVEXPExamAplica();
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICvePersonal(rset.getInt(5));
				vEXPExamAplica.setICveModulo(rset.getInt(6));
				vEXPExamAplica.setDtSolicitado(rset.getDate(7));
				vEXPExamAplica.setIFolioEs(rset.getInt(8));
				vEXPExamAplica.setDtAplicacion(rset.getDate(9));
				vEXPExamAplica.setDtConcluido(rset.getDate(10));
				vEXPExamAplica.setLIniciado(rset.getInt(11));
				vEXPExamAplica.setLDictaminado(rset.getInt(12));
				vEXPExamAplica.setLInterConsulta(rset.getInt(13));
				vEXPExamAplica.setLInterConcluye(rset.getInt(14));
				vEXPExamAplica.setLConcluido(rset.getInt(15));
				vEXPExamAplica.setDtDictamen(rset.getDate(16));
				vEXPExamAplica.setDtEntregaRes(rset.getDate(17));
				vEXPExamAplica.setDtArchivado(rset.getDate(18));
				vEXPExamAplica.setICveNumEmpresa(rset.getInt(19));
				vEXPExamAplica.setICveUsuSolicita(rset.getInt(20));
				vcEXPExamAplica.addElement(vEXPExamAplica);
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
			return vcEXPExamAplica;
		}
	}

	/**
	 * Método GeneraExamen
	 */
	public void GeneraExamen(Vector vcServicios, Vector vcRamas,
			Vector vcResultados) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			TDEXPServicio dEXPServicio = new TDEXPServicio();
			TVEXPServicio vEXPServicio = new TVEXPServicio();
			TDEXPRama dEXPRama = new TDEXPRama();
			TVEXPRama vEXPRama = new TVEXPRama();
			TDEXPResultado dEXPResultado = new TDEXPResultado();
			TVEXPResultado vEXPResultado = new TVEXPResultado();

			for (int i = 0; i < vcServicios.size(); i++) {
				vEXPServicio = (TVEXPServicio) vcServicios.get(i);
				dEXPServicio.insert(conn, vEXPServicio);
			}

			for (int i = 0; i < vcRamas.size(); i++) {
				vEXPRama = (TVEXPRama) vcRamas.get(i);
				dEXPRama.insert(conn, vEXPRama);
			}
			
			
			long time_start, time_end, time_end2;
			time_start = System.currentTimeMillis();
			for (int i = 0; i < vcResultados.size(); i++) {
				vEXPResultado = (TVEXPResultado) vcResultados.get(i);
				dEXPResultado.insert(conn, vEXPResultado);				
			}
			time_end = System.currentTimeMillis();
			//System.out.println("Tiempo de la generacion delos sintomas igual a "+ ( time_end - time_start ) +" milliseconds");
			
			
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception e) {
				warn("update.rollback", ex);
			}
			ex.printStackTrace();
			log("rolling back...");
			warn("GeneraExamen", ex);
			throw new DAOException("GeneraExamen");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("GeneraExamen.close", ex2);
			}
		}
	}
	
	
	/**
	 * Método GeneraExamen
	 */
	public void GeneraExamen2(Vector vcResultados, String cUpdate) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			TDEXPServicio dEXPServicio = new TDEXPServicio();
			TDEXPResultado dEXPResultado = new TDEXPResultado();
			TVEXPResultado vEXPResultado = new TVEXPResultado();
			
			/////////////////////////////////////Nuevo Metodo insert /////////////////////////////////////
			String insertValue = "";
			String ref = "";
			int counSintomas = 0;
			boolean  inserto = false;
			
			long time_start, time_end, time_end2;
			
			time_start = System.currentTimeMillis();
			for (int i = 0; i < vcResultados.size(); i++) {
				vEXPResultado = (TVEXPResultado) vcResultados.get(i);
				//System.out.println(i);
				////////////
				if (vEXPResultado.getCValRef() == null){
						ref = "null";
				}else{
					ref = vEXPResultado.getCValRef();
				}
				
				if(i==0){
					insertValue = "("+vEXPResultado.getICveExpediente()+"," +
								  vEXPResultado.getINumExamen()+"," +
								  vEXPResultado.getICveServicio()+"," +
								  vEXPResultado.getICveRama()+"," +
								  vEXPResultado.getICveSintoma()+"," +
								  "null,null,null,null,'"+ref+"')"; 
				}else{
				  insertValue = insertValue+",("+vEXPResultado.getICveExpediente()+"," +
							  vEXPResultado.getINumExamen()+"," +
							  vEXPResultado.getICveServicio()+"," +
							  vEXPResultado.getICveRama()+"," +
							  vEXPResultado.getICveSintoma()+"," +
							  "null,null,null,null,'"+ref+"')";
				}				
				counSintomas++;
			}
			time_end = System.currentTimeMillis();
			
			if(counSintomas>0){
				inserto = dEXPResultado.insert2(conn, insertValue);
				//System.out.println(insertValue);
			}
			
			if(inserto){
				dEXPServicio.updateSQL(null, cUpdate);
			}
			
			time_end2 = System.currentTimeMillis();
			//System.out.println("Tiempo del armado del query en java "+ ( time_end - time_start ) +" milliseconds");
			//System.out.println("Ejecucion del query "+ ( time_end2 - time_start ) +" milliseconds");
			
			
			
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception e) {
				warn("update.rollback", ex);
			}
			ex.printStackTrace();
			log("rolling back...");
			warn("GeneraExamen", ex);
			throw new DAOException("GeneraExamen");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("GeneraExamen.close", ex2);
			}
		}
	}
	
	

	/**
	 * Método Find By All c/Where
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
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
			cSQL = "select "
					+ "EXPExamAplica.iCveExpediente,"
					+ "EXPExamAplica.iNumExamen,"
					+ "EXPExamAplica.iCveUniMed,"
					+ "EXPExamAplica.iCveProceso,"
					+ "EXPExamAplica.iCvePersonal,"
					+ "EXPExamAplica.iCveModulo,"
					+ "EXPExamAplica.dtSolicitado,"
					+ "EXPExamAplica.iFolioEs,"
					+ "EXPExamAplica.dtAplicacion,"
					+ "EXPExamAplica.dtConcluido,"
					+ "EXPExamAplica.lIniciado,"
					+ "EXPExamAplica.lDictaminado,"
					+ "EXPExamAplica.lInterConsulta,"
					+ "EXPExamAplica.lInterConcluye,"
					+ "EXPExamAplica.lConcluido,"
					+ "EXPExamAplica.dtDictamen,"
					+ "EXPExamAplica.dtEntregaRes,"
					+ "EXPExamAplica.dtArchivado,"
					+ "EXPExamAplica.iCveNumEmpresa,"
					+ "EXPExamAplica.iCveUsuSolicita,"
					+ "EXPExamAplica.tInicio, "
					+ "EXPExamAplica.tConcluido, "
					+ "GRLProceso.cDscProceso, "
					+ "GRLUniMed.cDscUniMed, "
					
					// Agregando nuevo campo 25 mayo 2011
					+"GRLModulo.cDscModulo, "
					+"EXPExamAplica.dtBlqDictamen, "
					+"Perdatos.CNombre, "
					+"Perdatos.cApPaterno, "
					+"Perdatos.cApMaterno "
					+ " from EXPExamAplica "
					+ " inner join GRLProceso on GRLProceso.iCveProceso = EXPExamAplica.iCveProceso "
					+ " inner join GRLUniMed on GRLUniMed.iCveUniMed = EXPExamAplica.iCveUniMed "
					+ " inner join GRLModulo on GRLModulo.iCveModulo = EXPExamAplica.iCveModulo "
					+ " inner join Perdatos on Perdatos.iCveExpediente = EXPExamAplica.iCveExpediente "
					+ cWhere + " "
					+ "  AND GRLModulo.iCveUnimed = EXPExamAplica.iCveUnimed "
					+ "order by EXPExamAplica.iNumExamen, EXPExamAplica.iCveExpediente";

			System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica = new TVEXPExamAplica();
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICvePersonal(rset.getInt(5));
				vEXPExamAplica.setICveModulo(rset.getInt(6));
				vEXPExamAplica.setDtSolicitado(rset.getDate(7));
				vEXPExamAplica.setIFolioEs(rset.getInt(8));
				vEXPExamAplica.setDtAplicacion(rset.getDate(9));
				vEXPExamAplica.setDtConcluido(rset.getDate(10));
				vEXPExamAplica.setLIniciado(rset.getInt(11));
				vEXPExamAplica.setLDictaminado(rset.getInt(12));
				vEXPExamAplica.setLInterConsulta(rset.getInt(13));
				vEXPExamAplica.setLInterConcluye(rset.getInt(14));
				vEXPExamAplica.setLConcluido(rset.getInt(15));
				vEXPExamAplica.setDtDictamen(rset.getDate(16));
				vEXPExamAplica.setDtEntregaRes(rset.getDate(17));
				vEXPExamAplica.setDtArchivado(rset.getDate(18));
				vEXPExamAplica.setICveNumEmpresa(rset.getInt(19));
				vEXPExamAplica.setICveUsuSolicita(rset.getInt(20));
				vEXPExamAplica.setTInicio(rset.getTime(21));
				vEXPExamAplica.setTConcluido(rset.getTime(22));
				vEXPExamAplica.setCDscProceso(rset.getString(23));
				vEXPExamAplica.setCDscUniMed(rset.getString(24));
				vEXPExamAplica.setCDscModulo(rset.getString(25));
				vEXPExamAplica.setDtBlqDictamen(rset.getDate(26));
				vEXPExamAplica.setCNombre(rset.getString(27));
				vEXPExamAplica.setCApPaterno(rset.getString(28));
				vEXPExamAplica.setCApMaterno(rset.getString(29));				
				vcEXPExamAplica.addElement(vEXPExamAplica);

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
			return vcEXPExamAplica;
		}
	}

	/**
	 * Método FindLastExam
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Vector FindLastExam(String iCveExpediente, String iCveProceso)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcAUXEMO = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVAUXEMO vAUXEMO;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "iCveNumEmpresa,"
					+ "iCveCategoria,"
					+ "iCveMotivo,"
					+ "iCveGrupo,"
					+ "iCvePuesto,"
					+ "iCveMomentoAP,"
					+ "cLicencia, "
					+ "cDscEmpresa "
					+ "from EXPExamAplica "
					+ "inner join EXPExamCat on EXPExamCat.iCveExpediente = EXPExamAplica.iCveExpediente "
					+ "and EXPExamCat.iNumExamen = EXPExamAplica.iNumExamen "
					+ "inner join EXPExamGrupo on EXPExamGrupo.iCveExpediente = EXPExamAplica.iCveExpediente "
					+ "and EXPExamGrupo.iNumExamen = EXPExamAplica.iNumExamen "
					+ "inner join EXPExamPuesto on EXPExamPuesto.iCveExpediente = EXPExamAplica.iCveExpediente "
					+ "and EXPExamPuesto.iNumExamen = EXPExamAplica.iNumExamen "
					+ "inner join EMOExamen on EMOExamen.iCveExpediente = EXPExamAplica.iCveExpediente "
					+ "and EMOExamen.iNumExamen = EXPExamAplica.iNumExamen "
					+ "inner join GRLEmpresas on GRLEmpresas.iCveEmpresa = EXPExamAplica.iCveNumEmpresa "
					+ "where EXPExamAplica.iCveExpediente = ? and EXPExamAplica.iNumExamen = ? ";

			pstmt = conn.prepareStatement(cSQL);
			String q = "select max(iNumExamen) from EXPExamAplica where "
					+ "iCveExpediente = " + iCveExpediente
					+ " and iCveProceso =" + iCveProceso;
			PreparedStatement psNewCve = conn.prepareStatement(q);
			int iNewCve = 0;
			ResultSet rsNewCve = psNewCve.executeQuery();
			while (rsNewCve.next()) {
				iNewCve = rsNewCve.getInt(1);
			}
			rsNewCve.close();
			psNewCve.close();

			pstmt.setInt(1, Integer.parseInt(iCveExpediente));
			pstmt.setInt(2, iNewCve);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vAUXEMO = new TVAUXEMO();
				vAUXEMO.setICveNumEmpresa(rset.getInt(1));
				vAUXEMO.setICveCategoria(rset.getInt(2));
				vAUXEMO.setICveMotivo(rset.getInt(3));
				vAUXEMO.setICveGrupo(rset.getInt(4));
				vAUXEMO.setICvePuesto(rset.getInt(5));
				vAUXEMO.setICveMomentoAP(rset.getInt(6));
				vAUXEMO.setCLicencia(rset.getString(7));
				vAUXEMO.setCDscEmpresa(rset.getString(8));
				vcAUXEMO.addElement(vAUXEMO);

			}
		} catch (Exception ex) {
			warn("FindLastExam", ex);
			throw new DAOException("FindLastExam");
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
				warn("FindLastExam.close", ex2);
			}
			return vcAUXEMO;
		}
	}

	/**
	 * Método FindLastEPI
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public int FindLastEPI(String iCveExpediente, String iCveProceso)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcAUXEMO = new Vector();
		int iNewCve = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVAUXEMO vAUXEMO;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			String q = "select max(iNumExamen) from EXPExamAplica where "
					+ "iCveExpediente = " + iCveExpediente
					+ " and iCveProceso =" + iCveProceso;
			PreparedStatement psNewCve = conn.prepareStatement(q);

			ResultSet rsNewCve = psNewCve.executeQuery();
			while (rsNewCve.next()) {
				iNewCve = rsNewCve.getInt(1);
			}
			rsNewCve.close();
			psNewCve.close();

		} catch (Exception ex) {
			warn("FindLastExam", ex);
			throw new DAOException("FindLastExam");
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
				warn("FindLastExam.close", ex2);
			}
			return iNewCve;
		}
	}

	/**
	 * Método Find By Archivo
	 * 
	 * @param UniMed
	 *            La clave de la Unidad M�dica.
	 * @param Modulo
	 *            La clave del M�dulo.
	 * @param Proceso
	 *            La clave del proceso.
	 * @param Solicitado
	 *            El d�a solicitado.
	 * @return Vector con registros obtenidos
	 * @throws DAOException
	 * @author Marco A. Gonz�lez Paz
	 */
	public Vector FindByArchivo(String UniMed, String Modulo, String Proceso,
			String Solicitado, String cArchivado) throws DAOException {
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
			TFechas dtFecha = new TFechas();
			java.sql.Date dtSolicitado;
			String cFechaSolicitado = "";
			dtSolicitado = dtFecha.getDateSQL(Solicitado);
			cFechaSolicitado = dtFecha.getFechaYYYYMMDD(dtSolicitado, "-");
			int count;
			cSQL = "select "
					+ "iCveExpediente,"
					+ "iNumExamen,"
					+ "iCveUniMed,"
					+ "iCveProceso,"
					+ "iCvePersonal,"
					+ "iCveModulo,"
					+ "dtSolicitado,"
					+ "iFolioEs,"
					+ "dtAplicacion,"
					+ "dtConcluido,"
					+ "lIniciado,"
					+ "lDictaminado,"
					+ "lInterConsulta,"
					+ "lInterConcluye,"
					+ "lConcluido,"
					+ "dtDictamen,"
					+ "dtEntregaRes,"
					+ "dtArchivado,"
					+ "iCveNumEmpresa,"
					+ "iCveUsuSolicita, "
					+ "(select distinct(b.iCveExpediente) from EXPExamAplica b "
					+ " where b.iCveExpediente = a.iCveExpediente "
					+ " and b.iNumExamen  < a.iNumExamen "
					+ " and b.iCveUniMed  = a.iCveUniMed "
					+ " and b.iCveModulo  = a.iCveModulo "
					+ " and b.iCveProceso = a.iCveProceso  ) "
					+ " from EXPExamAplica a " + "where a.iCveUniMed = "
					+ UniMed + " " + "and a.iCveModulo = " + Modulo + " "
					+ "and a.iCveProceso = " + Proceso + " "
					+ "and a.dtSolicitado= '" + cFechaSolicitado + "' " +
					// "and lDictaminado = 1 " +
					"AND a.ICVEEXPEDIENTE > 1 ";
			// ES HARDCODE EL CODIGO DE LA LINEA ANTERIO SE CAMBIO POR
			// NECESIADES PRIORITARIAS
			// "and lConcluido = 1 " ;

			if (cArchivado.compareTo("SI") == 0) {
				cSQL = cSQL + "and a.dtArchivado is null ";
				// "and dtEntregaRes is not null " +
				// "and dtArchivado is null " +
			}
			cSQL = cSQL + "order by a.iCveExpediente";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica = new TVEXPExamAplica();
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICvePersonal(rset.getInt(5));
				vEXPExamAplica.setICveModulo(rset.getInt(6));
				vEXPExamAplica.setDtSolicitado(rset.getDate(7));
				vEXPExamAplica.setIFolioEs(rset.getInt(8));
				vEXPExamAplica.setDtAplicacion(rset.getDate(9));
				vEXPExamAplica.setDtConcluido(rset.getDate(10));
				vEXPExamAplica.setLIniciado(rset.getInt(11));
				vEXPExamAplica.setLDictaminado(rset.getInt(12));
				vEXPExamAplica.setLInterConsulta(rset.getInt(13));
				vEXPExamAplica.setLInterConcluye(rset.getInt(14));
				vEXPExamAplica.setLConcluido(rset.getInt(15));
				vEXPExamAplica.setDtDictamen(rset.getDate(16));
				vEXPExamAplica.setDtEntregaRes(rset.getDate(17));
				vEXPExamAplica.setDtArchivado(rset.getDate(18));
				vEXPExamAplica.setICveNumEmpresa(rset.getInt(19));
				vEXPExamAplica.setICveUsuSolicita(rset.getInt(20));

				String cSituacion = "" + rset.getInt(21);

				if (cSituacion.compareTo("null") != 0) {
					vEXPExamAplica.setCDscSituacion("NUEVO");
				} else {
					vEXPExamAplica.setCDscSituacion("EXISTENTE");

					/*
					 * vEXPExamAplica.setCDscSituacion(this.FindBySituacion("" +
					 * rset.getInt(1), "" + rset.getInt(3), "" + rset.getInt(6),
					 * "" + rset.getInt(2), "" + rset.getInt(4)));
					 */
				}
				vcEXPExamAplica.addElement(vEXPExamAplica);
			}
		} catch (Exception ex) {
			warn("FindByArchivo", ex);
			throw new DAOException("FindByArchivo");
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
				warn("FindByArchivo.close", ex2);
			}
			return vcEXPExamAplica;
		}
	}

	/**
	 * Método Find By Situacion
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public String FindBySituacion(String Expediente, String UniMed,
			String Modulo, String Examen, String Proceso) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		int iNuevo = 0;
		String cValor = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamAplica vEXPExamAplica;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveExpediente," + "iNumExamen,"
					+ "iCveUniMed," + "iCveProceso," + "iCvePersonal,"
					+ "iCveModulo," + "dtSolicitado," + "iFolioEs,"
					+ "dtAplicacion," + "dtConcluido," + "lIniciado,"
					+ "lDictaminado," + "lInterConsulta," + "lInterConcluye,"
					+ "lConcluido," + "dtDictamen," + "dtEntregaRes,"
					+ "dtArchivado," + "iCveNumEmpresa," + "iCveUsuSolicita"
					+ " from EXPExamAplica " + "where iCveExpediente = "
					+ Expediente + " and iNumExamen  < " + Examen + " "
					+ " and iCveUniMed  = " + UniMed + " "
					+ " and iCveModulo  = " + Modulo + " "
					+ " and iCveProceso = " + Proceso + " "
					+ "order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if (iNuevo == 0 && rset.getInt(3) == Integer.parseInt(UniMed)
						&& rset.getInt(6) == Integer.parseInt(Modulo)) {
					iNuevo = 1;
				}

			}

			if (iNuevo == 1) {
				cValor = "EXISTE";
			} else {
				cValor = "NUEVO";

			}
		} catch (Exception ex) {
			warn("FindBySituacion", ex);
			throw new DAOException("FindBySituacion");
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
				warn("FindBySituacion.close", ex2);
			}
			return cValor;
		}
	}

	/**
	 * Método Insert
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
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// Calculando Timestamp para el campo TINIEXA
			// AG SA 25 mayo 2010

			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			// System.out.println("sql.Timestamp: "+sqlTimestamp);

			String cSQL = "insert into EXPExamAplica("
					+ "iCveExpediente,iNumExamen,iCveUniMed,iCveProceso,iCvePersonal,"
					+ "iCveModulo,dtSolicitado,"
					+
					// Campo que bloquea el examen
					"dtBlqDictamen,"
					+ "iFolioEs,dtAplicacion,dtConcluido,lIniciado,"
					+ "lDictaminado,lInterConsulta,lInterConcluye,lConcluido,dtDictamen,"
					+ "dtEntregaRes,dtArchivado,iCveNumEmpresa,iCveUsuSolicita,tInicio"
					+
					// El siguiente campo se agrego con la finalidad de capturar
					// la fecha de inicio del examen
					// AG SA 25 de mayo 2010
					",tIniExa,cConstancia"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,{FN CURTIME()}"
					+
					// AG SA 25 de mayo 2010
					",?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// System.out.println();

			int iNumExamen = getINumExamenMaximo(vEXPExamAplica
					.getICveExpediente());
			vEXPExamAplica.setINumExamen(iNumExamen);

			pstmt.setInt(1, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(2, vEXPExamAplica.getINumExamen());
			pstmt.setInt(3, vEXPExamAplica.getICveUniMed());
			pstmt.setInt(4, vEXPExamAplica.getICveProceso());
			pstmt.setInt(5, vEXPExamAplica.getICvePersonal());
			pstmt.setInt(6, vEXPExamAplica.getICveModulo());
			pstmt.setDate(7, vEXPExamAplica.getDtSolicitado());
			// Campo para igualar el dtbloqueado al solicitado
			pstmt.setDate(8, vEXPExamAplica.getDtSolicitado());
			pstmt.setInt(9, vEXPExamAplica.getIFolioEs());
			pstmt.setDate(10, vEXPExamAplica.getDtAplicacion());
			pstmt.setDate(11, vEXPExamAplica.getDtConcluido());
			pstmt.setInt(12, vEXPExamAplica.getLIniciado());
			pstmt.setInt(13, vEXPExamAplica.getLDictaminado());
			pstmt.setInt(14, vEXPExamAplica.getLInterConsulta());
			pstmt.setInt(15, vEXPExamAplica.getLInterConcluye());
			pstmt.setInt(16, vEXPExamAplica.getLConcluido());
			pstmt.setDate(17, vEXPExamAplica.getDtDictamen());
			pstmt.setDate(18, vEXPExamAplica.getDtEntregaRes());
			pstmt.setDate(19, vEXPExamAplica.getDtArchivado());
			pstmt.setInt(20, vEXPExamAplica.getICveNumEmpresa());
			pstmt.setInt(21, vEXPExamAplica.getICveUsuSolicita());
			// El siguiente campo se agrego con la finalidad de capturar la
			// fecha de inicio del examen
			// AG SA 25 de mayo 2010
			pstmt.setTimestamp(22, sqlTimestamp);
			pstmt.setString(23, "TDEXPExamAplica - insert");
			pstmt.executeUpdate();
			cClave = "" + vEXPExamAplica.getINumExamen();
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
		}
		return cClave;
	}
	
	
	/**
	 * Método Insert para prueba rapida
	 */
	public Object insertPR(Connection conGral, Object obj) throws DAOException {
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
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// Calculando Timestamp para el campo TINIEXA
			// AG SA 25 mayo 2010

			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);
			// System.out.println("sql.Timestamp: "+sqlTimestamp);

			String cSQL = "insert into EXPExamAplica("
					+ "iCveExpediente,iNumExamen,iCveUniMed,iCveProceso,iCvePersonal,"
					+ "iCveModulo,dtSolicitado,"
					+
					// Campo que bloquea el examen
					"dtBlqDictamen,"
					+ "iFolioEs,dtAplicacion,dtConcluido,lIniciado,"
					+ "lDictaminado,lInterConsulta,lInterConcluye,lConcluido,dtDictamen,"
					+ "dtEntregaRes,dtArchivado,iCveNumEmpresa,iCveUsuSolicita,tInicio"
					+
					// El siguiente campo se agrego con la finalidad de capturar
					// la fecha de inicio del examen
					// AG SA 25 de mayo 2010
					",tIniExa,cConstancia"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,{FN CURTIME()}"
					+
					// AG SA 25 de mayo 2010
					",?,'PBA RAPIDA')";

			pstmt = conn.prepareStatement(cSQL);
			// System.out.println();

			int iNumExamen = getINumExamenMaximo(vEXPExamAplica
					.getICveExpediente());
			vEXPExamAplica.setINumExamen(iNumExamen);

			pstmt.setInt(1, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(2, vEXPExamAplica.getINumExamen());
			pstmt.setInt(3, vEXPExamAplica.getICveUniMed());
			pstmt.setInt(4, vEXPExamAplica.getICveProceso());
			pstmt.setInt(5, vEXPExamAplica.getICvePersonal());
			pstmt.setInt(6, vEXPExamAplica.getICveModulo());
			pstmt.setDate(7, vEXPExamAplica.getDtSolicitado());
			// Campo para igualar el dtbloqueado al solicitado
			pstmt.setDate(8, vEXPExamAplica.getDtSolicitado());
			pstmt.setInt(9, vEXPExamAplica.getIFolioEs());
			pstmt.setDate(10, vEXPExamAplica.getDtAplicacion());
			pstmt.setDate(11, vEXPExamAplica.getDtConcluido());
			pstmt.setInt(12, vEXPExamAplica.getLIniciado());
			pstmt.setInt(13, vEXPExamAplica.getLDictaminado());
			pstmt.setInt(14, vEXPExamAplica.getLInterConsulta());
			pstmt.setInt(15, vEXPExamAplica.getLInterConcluye());
			pstmt.setInt(16, vEXPExamAplica.getLConcluido());
			pstmt.setDate(17, vEXPExamAplica.getDtDictamen());
			pstmt.setDate(18, vEXPExamAplica.getDtEntregaRes());
			pstmt.setDate(19, vEXPExamAplica.getDtArchivado());
			pstmt.setInt(20, vEXPExamAplica.getICveNumEmpresa());
			pstmt.setInt(21, vEXPExamAplica.getICveUsuSolicita());
			// El siguiente campo se agrego con la finalidad de capturar la
			// fecha de inicio del examen
			// AG SA 25 de mayo 2010
			pstmt.setTimestamp(22, sqlTimestamp);
			pstmt.executeUpdate();
			cClave = "" + vEXPExamAplica.getINumExamen();
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
		}
		return cClave;
	}

	/**
	 * Método Insert
	 */
	public Object insertEmo(Connection conGral, Object obj) throws DAOException {
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
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			String cSQL = "insert into EXPExamAplica("
					+ "iCveExpediente,iNumExamen,iCveUniMed,iCveProceso,iCvePersonal,"
					+ "iCveModulo,dtSolicitado,"
					+ "dtBlqDictamen,"
					+ "iFolioEs,dtAplicacion,dtConcluido,lIniciado,"
					+ "lDictaminado,lInterConsulta,lInterConcluye,lConcluido,dtDictamen,"
					+ "dtEntregaRes,dtArchivado,iCveNumEmpresa,iCveUsuSolicita,tInicio,tIniExa,cConstancia"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			int iNumExamen = getINumExamenMaximo(vEXPExamAplica
					.getICveExpediente());
			vEXPExamAplica.setINumExamen(iNumExamen);

			pstmt.setInt(1, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(2, vEXPExamAplica.getINumExamen());
			pstmt.setInt(3, vEXPExamAplica.getICveUniMed());
			pstmt.setInt(4, vEXPExamAplica.getICveProceso());
			pstmt.setInt(5, vEXPExamAplica.getICvePersonal());
			pstmt.setInt(6, vEXPExamAplica.getICveModulo());
			pstmt.setDate(7, vEXPExamAplica.getDtSolicitado());
			pstmt.setDate(8, vEXPExamAplica.getDtSolicitado());
			pstmt.setInt(9, vEXPExamAplica.getIFolioEs());
			pstmt.setDate(10, vEXPExamAplica.getDtAplicacion());
			pstmt.setDate(11, vEXPExamAplica.getDtConcluido());
			pstmt.setInt(12, vEXPExamAplica.getLIniciado());
			pstmt.setInt(13, vEXPExamAplica.getLDictaminado());
			pstmt.setInt(14, vEXPExamAplica.getLInterConsulta());
			pstmt.setInt(15, vEXPExamAplica.getLInterConcluye());
			pstmt.setInt(16, vEXPExamAplica.getLConcluido());
			pstmt.setDate(17, vEXPExamAplica.getDtDictamen());
			pstmt.setDate(18, vEXPExamAplica.getDtEntregaRes());
			pstmt.setDate(19, vEXPExamAplica.getDtArchivado());
			pstmt.setInt(20, vEXPExamAplica.getICveNumEmpresa());
			pstmt.setInt(21, vEXPExamAplica.getICveUsuSolicita());
			pstmt.setTime(22, vEXPExamAplica.getTInicio());
			pstmt.setTimestamp(23, sqlTimestamp);
			pstmt.setString(24, "TDEXPExamAplica - insertEmo");
			pstmt.executeUpdate();
			cClave = "" + vEXPExamAplica.getINumExamen();
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
		}
		return cClave;
	}

	/**
	 * Método update Modificado por JMG La funcion primordial es actualizar
	 * unicamente del registro encontrado lIniciado == 1
	 * 
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
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamAplica" + " Set lIniciado= ?,    "
					+ "     iFolioEs = ?    " 
					+ " Where iCveExpediente = ? "
					+ " And iNumExamen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamAplica.getLIniciado());
			pstmt.setInt(2, vEXPExamAplica.getIFolioEs());
			pstmt.setInt(3, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(4, vEXPExamAplica.getINumExamen());
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
	 * Método updateArchivado
	 * 
	 * @author Marco A. Gonz�lez Paz
	 * 
	 */
	public Object updateArchivado(Connection conGral, String cExpediente,
			String cExamen, String cFecha) throws DAOException {
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
			cSQL = "update EXPExamAplica" + " Set dtArchivado ='" + cFecha
					+ "', " + " tConcluido = {FN CURTIME()} "
					+ " Where iCveExpediente = " + cExpediente
					+ " And iNumExamen = " + cExamen;
			pstmt = conn.prepareStatement(cSQL);
			pstmt.executeUpdate();
			cClave = cExpediente;
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
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPExamAplica" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(2, vEXPExamAplica.getINumExamen());
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
				warn("delete.closeEXPExamAplica", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método noConcluido
	 */
	public boolean noConcluido(int iCveExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean bRet = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// pstmt =
			// conn.prepareStatement("select lConcluido from EXPExamAplica where iCveExpediente=?");
			pstmt = conn
					.prepareStatement(" SELECT  P.LDICTAMINADO FROM EXPEXAMAPLICA AS P "
							+ " WHERE P.ICVEEXPEDIENTE = ? AND P.INUMEXAMEN = (	SELECT MAX(A.INUMEXAMEN) "
							+ " FROM EXPEXAMAPLICA AS A "
							+ " WHERE A.ICVEPROCESO = 1 AND A.ICVEEXPEDIENTE = P.ICVEEXPEDIENTE) ");
			pstmt.setInt(1, iCveExpediente);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				// if (rset.getInt("lConcluido") != 1) {
				if (rset.getInt(1) != 1) {
					bRet = true;
				}
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
			return bRet;
		}
	}

	/**
	 * Método getINumExamenMaximo
	 */
	private int getINumExamenMaximo(int iCveExpediente) throws DAOException {
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
				iMax = rset.getInt("cta") + 1;
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
	 * Método que devuelve el examen del expediente correspondiente al usuario
	 * solicitado.
	 * 
	 * @param cCveExpediente
	 *            La clave del expediente.
	 * @param cNumExamen
	 *            El n�mero del examen.
	 * @param cCvePersonal
	 *            La clave del personal.
	 * @return un Vector con los registros obtenidos
	 * @throws DAOException
	 * @author Romeo S�nchez
	 */
	public Vector findExamPersonal(String cCveExpediente, String cNumExamen,
			String cCvePersonal) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		String where = "";

		// determinar condiciones adicionales de b�squeda (filtros)
		if (cCveExpediente != null && cCveExpediente.length() != 0) {
			where += " and EXPExamAplica.iCveExpediente = " + cCveExpediente;
		}
		if (cNumExamen != null && cNumExamen.length() != 0) {
			where += " and EXPExamAplica.iNumExamen = " + cNumExamen;
		}
		if (cCvePersonal != null && cCvePersonal.length() != 0) {
			where += " and EXPExamAplica.iCvePersonal = " + cCvePersonal;
		}

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamAplica vEXPExamAplica;
			int count;
			cSQL = "select "
					+ "EXPExamAplica.iCveExpediente,"
					+ "iNumExamen,"
					+ "iCveUniMed,"
					+ "iCveProceso,"
					+ "EXPExamAplica.iCvePersonal,"
					+ "iCveModulo,"
					+ "dtSolicitado,"
					+ "iFolioEs,"
					+ "dtAplicacion,"
					+ "dtConcluido,"
					+ "lIniciado,"
					+ "lDictaminado,"
					+ "lInterConsulta,"
					+ "lInterConcluye,"
					+ "lConcluido,"
					+ "dtDictamen,"
					+ "dtEntregaRes,"
					+ "dtArchivado,"
					+ "EXPExamAplica.iCveNumEmpresa,"
					+ "iCveUsuSolicita,"
					+ "cRFC,"
					+ // �nico campo que necesito de PERDatos (PERPersonal)
					"cNombre,"
					+ // �nico campo que necesito de PERDatos (PERPersonal)
					"cApPaterno,"
					+ // �nico campo que necesito de PERDatos (PERPersonal)
					"cApMaterno"
					+ // �nico campo que necesito de PERDatos (PERPersonal)
					" from EXPExamAplica , PERDatos "
					+ "where EXPExamAplica.iCveExpediente = PERDatos.iCveExpediente "
					+ where + " order by iCveExpediente";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica = new TVEXPExamAplica();
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICvePersonal(rset.getInt(5));
				vEXPExamAplica.setICveModulo(rset.getInt(6));
				vEXPExamAplica.setDtSolicitado(rset.getDate(7));
				vEXPExamAplica.setIFolioEs(rset.getInt(8));
				vEXPExamAplica.setDtAplicacion(rset.getDate(9));
				vEXPExamAplica.setDtConcluido(rset.getDate(10));
				vEXPExamAplica.setLIniciado(rset.getInt(11));
				vEXPExamAplica.setLDictaminado(rset.getInt(12));
				vEXPExamAplica.setLInterConsulta(rset.getInt(13));
				vEXPExamAplica.setLInterConcluye(rset.getInt(14));
				vEXPExamAplica.setLConcluido(rset.getInt(15));
				vEXPExamAplica.setDtDictamen(rset.getDate(16));
				vEXPExamAplica.setDtEntregaRes(rset.getDate(17));
				vEXPExamAplica.setDtArchivado(rset.getDate(18));
				vEXPExamAplica.setICveNumEmpresa(rset.getInt(19));
				vEXPExamAplica.setICveUsuSolicita(rset.getInt(20));
				vEXPExamAplica.setCRFC(rset.getString(21));
				vEXPExamAplica.setCNombre(rset.getString("cNombre"));
				vEXPExamAplica.setCApPaterno(rset.getString("cApPaterno"));
				vEXPExamAplica.setCApMaterno(rset.getString("cApMaterno"));
				vcEXPExamAplica.addElement(vEXPExamAplica);
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
			return vcEXPExamAplica;
		}
	}

	/**
	 * Método que devuelve los registros con los expedientes correspondientes al
	 * usuario solicitado.
	 * 
	 * @param p
	 *            una colecci�n con los parametros
	 * @return un Vector con los registros obtenidos
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	public Vector findByUniMedModExp(HashMap p) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		TVEXPExamAplica datos = new TVEXPExamAplica();
		String cWhere = "";
		// obtener los parametros
		String cUniMed = (String) p.get("iCveUniMed");
		String cCveProceso = (String) p.get("iCveProceso");
		String cCveModulo = (String) p.get("iCveModulo");
		String cCveExpediente = (String) p.get("iCveExpediente");
		java.sql.Date dtFecha = (java.sql.Date) p.get("dtFecha");
		java.sql.Date dtFechaIni = null;
		String cDictaminado = (String) p.get("lDictaminado");

		TParametro vParametros = new TParametro("07");
		int iNumDias = new Integer(
				vParametros.getPropEspecifica("PlazoTerminarEPI")).intValue()
				* (-1);

		TFechas dtFechaTmp = new TFechas();
		dtFechaIni = dtFechaTmp.aumentaDisminuyeDias(dtFecha, iNumDias);

		String cCvePersonal = (String) p.get("iCvePersonal");
		int iUniMed = 0;
		int iCveMod = 0;
		int iCveExp = 0;
		int iCvePrc = 0;

		// determinar condiciones adicionales de b�squeda (filtros)
		if (cCveExpediente != null && cCveExpediente.length() != 0) {
			cWhere += " and EXPExamAplica.iCveExpediente = " + cCveExpediente;
		}
		if (cCveModulo != null && cCveModulo.length() != 0) {
			cWhere += " and EXPExamAplica.iCveModulo = " + cCveModulo;
		}
		if (cCveProceso != null && cCveProceso.length() != 0) {
			cWhere += " and EXPExamAplica.iCveProceso = " + cCveProceso;
		}
		if (cUniMed != null && cUniMed.length() != 0) {
			cWhere += " and EXPExamAplica.iCveUniMed = " + cUniMed;
		}
		if (cDictaminado != null && cDictaminado.trim().length() != 0) {
			cWhere += " AND EXPExamAplica.lDictaminado = " + cDictaminado;
		}

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";

			/*
			 * A. Primero buscar todos los PERPersonal-EPICisCita que
			 * correspondan a la Unidad M�dica y M�dulo que corresponda. B. Una
			 * vez encontrados los registros, buscar el m�s reciente
			 * EXPExamAplica para cada uno de los expedientes encontrados.
			 */

			// ---------------------------------- A
			cSQL = "select "
					+ "     PERDatos.iCvePersonal, "
					+ // 1
					"     PERDatos.iCveExpediente, "
					+ // 2
					"     PERDatos.cRFC, "
					+ // 3
					"     EPICisCita.iCveUniMed,"
					+ // 4
					"     EPICisCita.iCveModulo,"
					+ // 5
					"     EPICisCita.dtFecha, "
					+ // 6
					"     PERDatos.cNombre, "
					+ // 3
					"     PERDatos.cApPaterno, "
					+ // 3
					"     PERDatos.cApMaterno "
					+ // 3
					" from EPICisCita , PERDatos , EXPExamAplica "
					+ " where PERDatos.iCvePersonal = EPICisCita.iCvePersonal"
					+ " and EPICisCita.iCveUniMed = ?"
					+ " and EPICisCita.iCveModulo = ?"
					+ " and PERDatos.iCveExpediente = ?"
					+ " and EPICisCita.dtFecha >= '"
					+ dtFechaIni
					+ "' and EPICisCita.dtFecha <= '"
					+ dtFecha
					+ "'"
					+ "      AND EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
					+ "      AND EXPExamAplica.lConcluido = 0  AND EXPExamAplica.lDictaminado = 0  "
					+ cWhere;
			// System.out.println(cSQL);
			log("SQL: " + cSQL);
			log("con parametros: " + p.toString());
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, Integer.parseInt(cUniMed));
			pstmt.setInt(2, Integer.parseInt(cCveModulo));
			pstmt.setInt(3, Integer.parseInt(cCveExpediente));
			// pstmt.setDate(4, dtFecha);
			rset = pstmt.executeQuery();
			iCvePrc = Integer.parseInt(cCveProceso);
			while (rset.next()) {
				iUniMed = rset.getInt(4);
				iCveMod = rset.getInt(5);
				iCveExp = rset.getInt(2);
				// ---------------------------------- B
				// buscar en EXPExamAplica
				datos = this.findExamAplica(conn, iCveExp, iUniMed, iCvePrc,
						iCveMod);
				datos.setCRFC(rset.getString(3)); // el dato faltante
				datos.setCNombre(rset.getString("cNombre")); // el dato faltante
				datos.setCApPaterno(rset.getString("cApPaterno")); // el dato
																	// faltante
				datos.setCApMaterno(rset.getString("cApMaterno")); // el dato
																	// faltante
				vcEXPExamAplica.addElement(datos);
				log("-registro obtenido de ExamAplica: "
						+ datos.toHashMap().toString());
			}
		} catch (Exception ex) {
			warn("findByUniMedModExp", ex);
			throw new DAOException("findByUniMedModExp");
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
				warn("findByUniMedModExp.close", ex2);
			}
		}
		return vcEXPExamAplica;
	}

	/**
	 * Método que devuelve los registros con los expedientes correspondientes al
	 * usuario solicitado.
	 * 
	 * @param p
	 *            una colecci�n con los parametros
	 * @return un Vector con los registros obtenidos
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	public Vector findByUniMedModExpONEROW(HashMap p) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		TVEXPExamAplica datos = new TVEXPExamAplica();
		String cWhere = "";
		// obtener los parametros
		String cUniMed = (String) p.get("iCveUniMed");
		String cCveProceso = (String) p.get("iCveProceso");
		String cCveModulo = (String) p.get("iCveModulo");
		String cCveExpediente = (String) p.get("iCveExpediente");
		java.sql.Date dtFecha = (java.sql.Date) p.get("dtFecha");
		java.sql.Date dtFechaIni = null;
		String cDictaminado = (String) p.get("lDictaminado");

		TParametro vParametros = new TParametro("07");
		int iNumDias = new Integer(
				vParametros.getPropEspecifica("PlazoTerminarEPI")).intValue()
				* (-1);

		TFechas dtFechaTmp = new TFechas();
		dtFechaIni = dtFechaTmp.aumentaDisminuyeDias(dtFecha, iNumDias);

		String cCvePersonal = (String) p.get("iCvePersonal");
		int iUniMed = 0;
		int iCveMod = 0;
		int iCveExp = 0;
		int iCvePrc = 0;

		// determinar condiciones adicionales de b�squeda (filtros)
		if (cCveExpediente != null && cCveExpediente.length() != 0) {
			cWhere += " and EXPExamAplica.iCveExpediente = " + cCveExpediente;
		}
		if (cCveModulo != null && cCveModulo.length() != 0) {
			cWhere += " and EXPExamAplica.iCveModulo = " + cCveModulo;
		}
		if (cCveProceso != null && cCveProceso.length() != 0) {
			cWhere += " and EXPExamAplica.iCveProceso = " + cCveProceso;
		}
		if (cUniMed != null && cUniMed.length() != 0) {
			cWhere += " and EXPExamAplica.iCveUniMed = " + cUniMed;
		}
		if (cDictaminado != null && cDictaminado.trim().length() != 0) {
			cWhere += " AND EXPExamAplica.lDictaminado = " + cDictaminado;
		}

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";

			/*
			 * A. Primero buscar todos los PERPersonal-EPICisCita que
			 * correspondan a la Unidad M�dica y M�dulo que corresponda. B. Una
			 * vez encontrados los registros, buscar el m�s reciente
			 * EXPExamAplica para cada uno de los expedientes encontrados.
			 */

			// ---------------------------------- A
			cSQL = "select "
					+ "     PERDatos.iCvePersonal, "
					+ // 1
					"     PERDatos.iCveExpediente, "
					+ // 2
					"     PERDatos.cRFC, "
					+ // 3
					"     EPICisCita.iCveUniMed,"
					+ // 4
					"     EPICisCita.iCveModulo,"
					+ // 5
					"     EPICisCita.dtFecha, "
					+ // 6
					"     PERDatos.cNombre, "
					+ // 3
					"     PERDatos.cApPaterno, "
					+ // 3
					"     PERDatos.cApMaterno "
					+ // 3
					" from EPICisCita , PERDatos , EXPExamAplica "
					+ " where PERDatos.iCvePersonal = EPICisCita.iCvePersonal"
					+ " and EPICisCita.iCveUniMed = ?"
					+ " and EPICisCita.iCveModulo = ?"
					+ " and PERDatos.iCveExpediente = ?"
					+ " and EPICisCita.dtFecha >= '"
					+ dtFechaIni
					+ "' and EPICisCita.dtFecha <= '"
					+ dtFecha
					+ "'"
					+ "      AND EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
					+
					// "      AND EXPExamAplica.lConcluido = 0  AND EXPExamAplica.lDictaminado = 0  "
					// +
					"     AND EXPExamAplica.lDictaminado = 0  " + cWhere
					+ " FETCH FIRST ROW ONLY ";
			System.out.println("findByUniMedModExpONEROW = "+cSQL);
			System.out.println("cUniMed = "+cUniMed);
			System.out.println("cCveModulo = "+cCveModulo);
			System.out.println("cCveExpediente = "+cCveExpediente);
			log("SQL: " + cSQL);
			log("con parametros: " + p.toString());
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, Integer.parseInt(cUniMed));
			pstmt.setInt(2, Integer.parseInt(cCveModulo));
			pstmt.setInt(3, Integer.parseInt(cCveExpediente));
			// pstmt.setDate(4, dtFecha);
			rset = pstmt.executeQuery();
			iCvePrc = Integer.parseInt(cCveProceso);
			while (rset.next()) {
				iUniMed = rset.getInt(4);
				iCveMod = rset.getInt(5);
				iCveExp = rset.getInt(2);
				// ---------------------------------- B
				// buscar en EXPExamAplica
				datos = this.findExAplicaCapSrv(conn, iCveExp, iUniMed,
						iCvePrc, iCveMod);
				datos.setCRFC(rset.getString(3)); // el dato faltante
				datos.setCNombre(rset.getString("cNombre")); // el dato faltante
				datos.setCApPaterno(rset.getString("cApPaterno")); // el dato
																	// faltante
				datos.setCApMaterno(rset.getString("cApMaterno")); // el dato
																	// faltante
				vcEXPExamAplica.addElement(datos);
				log("-registro obtenido de ExamAplica: "
						+ datos.toHashMap().toString());
			}
		} catch (Exception ex) {
			warn("findByUniMedModExp", ex);
			throw new DAOException("findByUniMedModExp");
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
				warn("findByUniMedModExp.close", ex2);
			}
		}
		return vcEXPExamAplica;
	}

	/**
	 * Método que devuelve los registros con los expedientes correspondientes al
	 * usuario solicitado.
	 * 
	 * @param p
	 *            una colecci�n con los parametros
	 * @return un Vector con los registros obtenidos
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	public Vector findByUniMedModExp2(HashMap p) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		TVEXPExamAplica datos = new TVEXPExamAplica();
		String cWhere = "";
		// obtener los parametros
		String cUniMed = (String) p.get("iCveUniMed");
		String cCveProceso = (String) p.get("iCveProceso");
		String cCveModulo = (String) p.get("iCveModulo");
		String cCveExpediente = (String) p.get("iCveExpediente");
		String cNumExamen = (String) p.get("iNumExamen");
		java.sql.Date dtFecha = (java.sql.Date) p.get("dtFecha");
		java.sql.Date dtFechaIni = null;
		String cDictaminado = (String) p.get("lDictaminado");

		TParametro vParametros = new TParametro("07");
		int iNumDias = new Integer(
				vParametros.getPropEspecifica("PlazoTerminarEPI")).intValue()
				* (-1);

		TFechas dtFechaTmp = new TFechas();
		dtFechaIni = dtFechaTmp.aumentaDisminuyeDias(dtFecha, iNumDias);

		String cCvePersonal = (String) p.get("iCvePersonal");
		int iUniMed = 0;
		int iCveMod = 0;
		int iCveExp = 0;
		int iCvePrc = 0;

		// determinar condiciones adicionales de b�squeda (filtros)
		if (cCveExpediente != null && cCveExpediente.length() != 0) {
			cWhere += " and EXPExamAplica.iCveExpediente = " + cCveExpediente;
		}
		if (cNumExamen != null && cNumExamen.length() != 0) {
			cWhere += " and EXPExamAplica.iNumExamen = "
					+ cNumExamen
					+ " AND TSEGEXAR.iNumExamen = EXPExamAplica.iCveExpediente ";
		}
		if (cCveModulo != null && cCveModulo.length() != 0) {
			cWhere += " and EXPExamAplica.iCveModulo = " + cCveModulo;
		}
		if (cCveProceso != null && cCveProceso.length() != 0) {
			cWhere += " and EXPExamAplica.iCveProceso = " + cCveProceso;
		}
		if (cUniMed != null && cUniMed.length() != 0) {
			cWhere += " and EXPExamAplica.iCveUniMed = " + cUniMed;
		}
		if (cDictaminado != null && cDictaminado.trim().length() != 0) {
			cWhere += " AND EXPExamAplica.lDictaminado = " + cDictaminado;
		}

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";

			/*
			 * A. Primero buscar todos los PERPersonal-EPICisCita que
			 * correspondan a la Unidad M�dica y M�dulo que corresponda. B. Una
			 * vez encontrados los registros, buscar el m�s reciente
			 * EXPExamAplica para cada uno de los expedientes encontrados.
			 */

			// ---------------------------------- A
			cSQL = "select "
					+ "     PERDatos.iCvePersonal, "
					+ // 1
					"     PERDatos.iCveExpediente, "
					+ // 2
					"     PERDatos.cRFC, "
					+ // 3
					"     EXPExamAplica.iCveUniMed,"
					+ // 4
					"     EXPExamAplica.iCveModulo,"
					+ // 5
					"     EXPExamAplica.dtSolicitado, "
					+ // 6
					"     PERDatos.cNombre, "
					+ // 3
					"     PERDatos.cApPaterno, "
					+ // 3
					"     PERDatos.cApMaterno "
					+ // 3
					" from EPICisCita , PERDatos , EXPExamAplica "
					+ " where PERDatos.iCvePersonal = EPICisCita.iCvePersonal"
					+ " and EPICisCita.iCveUniMed = ?"
					+ " and EPICisCita.iCveModulo = ?"
					+ " and PERDatos.iCveExpediente = ?"
					+ " and EXPExamAplica.iNumExamen = ? "
					+ " and EXPExamAplica.dtSolicitado >= '"
					+ dtFechaIni
					+ "' and EXPExamAplica.dtSolicitado <= '"
					+ dtFecha
					+ "'"
					+ "      AND EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
					+ "      AND TSEGEXAR.iCveExpediente=PERDatos.iCveExpediente "
					+ "      AND TSEGEXAR.Estatus < 2          " + cWhere;
			log("SQL: " + cSQL);
			log("con parametros: " + p.toString());
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, Integer.parseInt(cUniMed));
			pstmt.setInt(2, Integer.parseInt(cCveModulo));
			pstmt.setInt(3, Integer.parseInt(cCveExpediente));
			pstmt.setInt(4, Integer.parseInt(cNumExamen));
			// pstmt.setDate(4, dtFecha);
			rset = pstmt.executeQuery();
			iCvePrc = Integer.parseInt(cCveProceso);
			while (rset.next()) {
				iUniMed = rset.getInt(4);
				iCveMod = rset.getInt(5);
				iCveExp = rset.getInt(2);
				// ---------------------------------- B
				// buscar en EXPExamAplica
				datos = this.findExAplicaCapSrv(conn, iCveExp, iUniMed,
						iCvePrc, iCveMod);
				datos.setCRFC(rset.getString(3)); // el dato faltante
				datos.setCNombre(rset.getString("cNombre")); // el dato faltante
				datos.setCApPaterno(rset.getString("cApPaterno")); // el dato
																	// faltante
				datos.setCApMaterno(rset.getString("cApMaterno")); // el dato
																	// faltante
				vcEXPExamAplica.addElement(datos);
				log("-registro obtenido de ExamAplica: "
						+ datos.toHashMap().toString());
			}
		} catch (Exception ex) {
			warn("findByUniMedModExp", ex);
			throw new DAOException("findByUniMedModExp");
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
				warn("findByUniMedModExp.close", ex2);
			}
		}
		return vcEXPExamAplica;
	}

	/**
	 * Método privado que obtiene un registro de la tabla EXPExamAplica para el
	 * expediente solicitado
	 * 
	 * @param conGral
	 *            la conexi�n que es obtenida desde el Método anterior
	 * @param iCveExp
	 *            la clave del expediente
	 * @param iUniMed
	 *            la unidad m�dica
	 * @param iCvePrc
	 *            la clave del proceso
	 * @param iCveMod
	 *            la clave del m�dulo
	 * @return un Value Object con el registro obtenido
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	public TVEXPExamAplica findExAplicaCapSrv(Connection conGral, int iCveExp,
			int iUniMed, int iCvePrc, int iCveMod) throws DAOException {
		DbConnection dbConn = null;
		// Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String cSQL = null;
		TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}

			if (iCvePrc == 1) {
				cSQL = "select "
						+ "iCveExpediente,"
						+ "iNumExamen,"
						+ "iCveUniMed,"
						+ "iCveProceso,"
						+ "iCvePersonal,"
						+ "iCveModulo,"
						+ "dtSolicitado,"
						+ "iFolioEs,"
						+ "dtAplicacion,"
						+ "dtConcluido,"
						+ "lIniciado,"
						+ "lDictaminado,"
						+ "lInterConsulta,"
						+ "lInterConcluye,"
						+ "lConcluido,"
						+ "dtDictamen,"
						+ "dtEntregaRes,"
						+ "dtArchivado,"
						+ "iCveNumEmpresa,"
						+ "iCveUsuSolicita"
						+
						// "cRFC" + // �nico campo que necesito de PERDatos
						// (PERPersonal)
						" from EXPExamAplica where iCveExpediente = ? "
						+ " and iNumExamen = (select max(iNumExamen) from EXPExamAplica where iCveExpediente = ? and lDictaminado=0)"
						+ " and lDictaminado=0";
			} else {
				cSQL = "select "
						+ "iCveExpediente,"
						+ "iNumExamen,"
						+ "iCveUniMed,"
						+ "iCveProceso,"
						+ "iCvePersonal,"
						+ "iCveModulo,"
						+ "dtSolicitado,"
						+ "iFolioEs,"
						+ "dtAplicacion,"
						+ "dtConcluido,"
						+ "lIniciado,"
						+ "lDictaminado,"
						+ "lInterConsulta,"
						+ "lInterConcluye,"
						+ "lConcluido,"
						+ "dtDictamen,"
						+ "dtEntregaRes,"
						+ "dtArchivado,"
						+ "iCveNumEmpresa,"
						+ "iCveUsuSolicita"
						+ " from EXPExamAplica where iCveExpediente = ? "
						+ " and iNumExamen = (select max(iNumExamen) from EXPExamAplica where iCveExpediente = ?)"
						+ "";
			}

			pstmt = conn.prepareStatement(cSQL);
			// System.out.println("findExamAplica = "+cSQL);

			pstmt.setInt(1, iCveExp);
			pstmt.setInt(2, iCveExp);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICvePersonal(rset.getInt(5));
				vEXPExamAplica.setICveModulo(rset.getInt(6));
				vEXPExamAplica.setDtSolicitado(rset.getDate(7));
				vEXPExamAplica.setIFolioEs(rset.getInt(8));
				vEXPExamAplica.setDtAplicacion(rset.getDate(9));
				vEXPExamAplica.setDtConcluido(rset.getDate(10));
				vEXPExamAplica.setLIniciado(rset.getInt(11));
				vEXPExamAplica.setLDictaminado(rset.getInt(12));
				vEXPExamAplica.setLInterConsulta(rset.getInt(13));
				vEXPExamAplica.setLInterConcluye(rset.getInt(14));
				vEXPExamAplica.setLConcluido(rset.getInt(15));
				vEXPExamAplica.setDtDictamen(rset.getDate(16));
				vEXPExamAplica.setDtEntregaRes(rset.getDate(17));
				vEXPExamAplica.setDtArchivado(rset.getDate(18));
				vEXPExamAplica.setICveNumEmpresa(rset.getInt(19));
				vEXPExamAplica.setICveUsuSolicita(rset.getInt(20));
				// vEXPExamAplica.setCRFC(rset.getString(21));
				// vcEXPExamAplica.addElement(vEXPExamAplica);
				// log("examAplica: " + vEXPExamAplica.toHashMap().toString());
			}
		} catch (Exception ex) {
			warn("findExamAplica", ex);
			throw new DAOException("findExamAplica");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null) {
					if (dbConn != null) {
						dbConn.closeConnection();
					}
				}
			} catch (Exception ex2) {
				warn("findExamAplica.close", ex2);
			}
		}
		return vEXPExamAplica;
	}

	/**
	 * Método privado que obtiene un registro de la tabla EXPExamAplica para el
	 * expediente solicitado
	 * 
	 * @param conGral
	 *            la conexi�n que es obtenida desde el Método anterior
	 * @param iCveExp
	 *            la clave del expediente
	 * @param iUniMed
	 *            la unidad m�dica
	 * @param iCvePrc
	 *            la clave del proceso
	 * @param iCveMod
	 *            la clave del m�dulo
	 * @return un Value Object con el registro obtenido
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	public TVEXPExamAplica findExamAplica(Connection conGral, int iCveExp,
			int iUniMed, int iCvePrc, int iCveMod) throws DAOException {
		DbConnection dbConn = null;
		// Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String cSQL = null;
		TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}

			if (iCvePrc == 1) {
				cSQL = "select "
						+ "iCveExpediente,"
						+ "iNumExamen,"
						+ "iCveUniMed,"
						+ "iCveProceso,"
						+ "iCvePersonal,"
						+ "iCveModulo,"
						+ "dtSolicitado,"
						+ "iFolioEs,"
						+ "dtAplicacion,"
						+ "dtConcluido,"
						+ "lIniciado,"
						+ "lDictaminado,"
						+ "lInterConsulta,"
						+ "lInterConcluye,"
						+ "lConcluido,"
						+ "dtDictamen,"
						+ "dtEntregaRes,"
						+ "dtArchivado,"
						+ "iCveNumEmpresa,"
						+ "iCveUsuSolicita"
						+
						// "cRFC" + // �nico campo que necesito de PERDatos
						// (PERPersonal)
						" from EXPExamAplica where iCveExpediente = ? "
						+ " and iNumExamen = (select max(iNumExamen) from EXPExamAplica where iCveExpediente = ? and lDictaminado=0)"
						+ " ";
			} else {
				cSQL = "select "
						+ "iCveExpediente,"
						+ "iNumExamen,"
						+ "iCveUniMed,"
						+ "iCveProceso,"
						+ "iCvePersonal,"
						+ "iCveModulo,"
						+ "dtSolicitado,"
						+ "iFolioEs,"
						+ "dtAplicacion,"
						+ "dtConcluido,"
						+ "lIniciado,"
						+ "lDictaminado,"
						+ "lInterConsulta,"
						+ "lInterConcluye,"
						+ "lConcluido,"
						+ "dtDictamen,"
						+ "dtEntregaRes,"
						+ "dtArchivado,"
						+ "iCveNumEmpresa,"
						+ "iCveUsuSolicita"
						+ " from EXPExamAplica where iCveExpediente = ? "
						+ " and iNumExamen = (select max(iNumExamen) from EXPExamAplica where iCveExpediente = ?)"
						+ "";
			}

			pstmt = conn.prepareStatement(cSQL);
			// System.out.println("findExamAplica = "+cSQL);

			pstmt.setInt(1, iCveExp);
			pstmt.setInt(2, iCveExp);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICvePersonal(rset.getInt(5));
				vEXPExamAplica.setICveModulo(rset.getInt(6));
				vEXPExamAplica.setDtSolicitado(rset.getDate(7));
				vEXPExamAplica.setIFolioEs(rset.getInt(8));
				vEXPExamAplica.setDtAplicacion(rset.getDate(9));
				vEXPExamAplica.setDtConcluido(rset.getDate(10));
				vEXPExamAplica.setLIniciado(rset.getInt(11));
				vEXPExamAplica.setLDictaminado(rset.getInt(12));
				vEXPExamAplica.setLInterConsulta(rset.getInt(13));
				vEXPExamAplica.setLInterConcluye(rset.getInt(14));
				vEXPExamAplica.setLConcluido(rset.getInt(15));
				vEXPExamAplica.setDtDictamen(rset.getDate(16));
				vEXPExamAplica.setDtEntregaRes(rset.getDate(17));
				vEXPExamAplica.setDtArchivado(rset.getDate(18));
				vEXPExamAplica.setICveNumEmpresa(rset.getInt(19));
				vEXPExamAplica.setICveUsuSolicita(rset.getInt(20));
				// vEXPExamAplica.setCRFC(rset.getString(21));
				// vcEXPExamAplica.addElement(vEXPExamAplica);
				// log("examAplica: " + vEXPExamAplica.toHashMap().toString());
			}
		} catch (Exception ex) {
			warn("findExamAplica", ex);
			throw new DAOException("findExamAplica");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null) {
					if (dbConn != null) {
						dbConn.closeConnection();
					}
				}
			} catch (Exception ex2) {
				warn("findExamAplica.close", ex2);
			}
		}
		return vEXPExamAplica;
	}

	/**
	 * Método privado que obtiene un registro de la tabla EXPExamAplica para el
	 * expediente solicitado
	 * 
	 * @param conGral
	 *            la conexi�n que es obtenida desde el Método anterior
	 * @param iCveExp
	 *            la clave del expediente
	 * @param iUniMed
	 *            la unidad m�dica
	 * @param iCvePrc
	 *            la clave del proceso
	 * @param iCveMod
	 *            la clave del m�dulo
	 * @return un Value Object con el registro obtenido
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	public TVEXPExamAplica findExamAplica2(Connection conGral, int iCveExp,
			int iUniMed, int iCvePrc, int iCveMod) throws DAOException {
		DbConnection dbConn = null;
		// Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String cSQL = null;
		TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			cSQL = "select "
					+ "iCveExpediente,"
					+ "iNumExamen,"
					+ "iCveUniMed,"
					+ "iCveProceso,"
					+ "iCvePersonal,"
					+ "iCveModulo,"
					+ "dtSolicitado,"
					+ "iFolioEs,"
					+ "dtAplicacion,"
					+ "dtConcluido,"
					+ "lIniciado,"
					+ "lDictaminado,"
					+ "lInterConsulta,"
					+ "lInterConcluye,"
					+ "lConcluido,"
					+ "dtDictamen,"
					+ "dtEntregaRes,"
					+ "dtArchivado,"
					+ "iCveNumEmpresa,"
					+ "iCveUsuSolicita"
					+
					// "cRFC" + // �nico campo que necesito de PERDatos
					// (PERPersonal)
					" from EXPExamAplica where iCveExpediente = ? "
					+ " and iNumExamen = (select max(iNumExamen) from EXPExamAplica where iCveExpediente = ? and lDictaminado=1)"
					+ " and lDictaminado=1";
			pstmt = conn.prepareStatement(cSQL);
			// System.out.println("findExamAplica = "+cSQL);

			pstmt.setInt(1, iCveExp);
			pstmt.setInt(2, iCveExp);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICvePersonal(rset.getInt(5));
				vEXPExamAplica.setICveModulo(rset.getInt(6));
				vEXPExamAplica.setDtSolicitado(rset.getDate(7));
				vEXPExamAplica.setIFolioEs(rset.getInt(8));
				vEXPExamAplica.setDtAplicacion(rset.getDate(9));
				vEXPExamAplica.setDtConcluido(rset.getDate(10));
				vEXPExamAplica.setLIniciado(rset.getInt(11));
				vEXPExamAplica.setLDictaminado(rset.getInt(12));
				vEXPExamAplica.setLInterConsulta(rset.getInt(13));
				vEXPExamAplica.setLInterConcluye(rset.getInt(14));
				vEXPExamAplica.setLConcluido(rset.getInt(15));
				vEXPExamAplica.setDtDictamen(rset.getDate(16));
				vEXPExamAplica.setDtEntregaRes(rset.getDate(17));
				vEXPExamAplica.setDtArchivado(rset.getDate(18));
				vEXPExamAplica.setICveNumEmpresa(rset.getInt(19));
				vEXPExamAplica.setICveUsuSolicita(rset.getInt(20));
				// vEXPExamAplica.setCRFC(rset.getString(21));
				// vcEXPExamAplica.addElement(vEXPExamAplica);
				// log("examAplica: " + vEXPExamAplica.toHashMap().toString());
			}
		} catch (Exception ex) {
			warn("findExamAplica", ex);
			throw new DAOException("findExamAplica");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null) {
					if (dbConn != null) {
						dbConn.closeConnection();
					}
				}
			} catch (Exception ex2) {
				warn("findExamAplica.close", ex2);
			}
		}
		return vEXPExamAplica;
	}

	/**
	 * Método publico que obtiene un registro de la tabla EXPExamAplica para el
	 * expediente solicitado
	 * 
	 * @param conGral
	 *            la conexi�n que es obtenida desde el Método anterior
	 * @param iCveExp
	 *            la clave del expediente
	 * @param cFecha
	 *            Fecha del Expediente
	 * @return un Value Object con el registro obtenido
	 * @throws DAOException
	 * @author Marco A. Gonz�lez Paz.
	 */
	public TVEXPExamAplica findExpediente(Connection conGral, int iCveExp,
			String cFecha) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String cSQL = null;
		TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
		TFechas dtFecha = new TFechas();
		java.sql.Date dtSolicitado;
		String cFechaSolicitado = "";
		dtSolicitado = dtFecha.getDateSQL(cFecha);
		cFechaSolicitado = dtFecha.getFechaYYYYMMDD(dtSolicitado, "-");

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			cSQL = "select "
					+ "EXPExamAplica.iCveExpediente,"
					+ "EXPExamAplica.iNumExamen,"
					+ "EXPExamAplica.iCveUniMed,"
					+ "EXPExamAplica.iCveProceso,"
					+ "EXPExamAplica.iCvePersonal,"
					+ "EXPExamAplica.iCveModulo,"
					+ "EXPExamAplica.dtSolicitado,"
					+ "EXPExamAplica.iFolioEs,"
					+ "EXPExamAplica.dtAplicacion,"
					+ "EXPExamAplica.dtConcluido,"
					+ "EXPExamAplica.lIniciado,"
					+ "EXPExamAplica.lDictaminado,"
					+ "EXPExamAplica.lInterConsulta,"
					+ "EXPExamAplica.lInterConcluye,"
					+ "EXPExamAplica.lConcluido,"
					+ "EXPExamAplica.dtDictamen,"
					+ "EXPExamAplica.dtEntregaRes,"
					+ "EXPExamAplica.dtArchivado,"
					+ "EXPExamAplica.iCveNumEmpresa,"
					+ "EXPExamAplica.iCveUsuSolicita,"
					+ "GRLUniMed.lPrivada "
					+ "from EXPExamAplica "
					+ "inner join GRLUniMed ON GRLUniMed.iCveUniMed = EXPExamAplica.iCveUniMed "
					+ "where iCveExpediente = ? " + "and dtSolicitado = '"
					+ cFechaSolicitado + "'";
			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, iCveExp);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICvePersonal(rset.getInt(5));
				vEXPExamAplica.setICveModulo(rset.getInt(6));
				vEXPExamAplica.setDtSolicitado(rset.getDate(7));
				vEXPExamAplica.setIFolioEs(rset.getInt(8));
				vEXPExamAplica.setDtAplicacion(rset.getDate(9));
				vEXPExamAplica.setDtConcluido(rset.getDate(10));
				vEXPExamAplica.setLIniciado(rset.getInt(11));
				vEXPExamAplica.setLDictaminado(rset.getInt(12));
				vEXPExamAplica.setLInterConsulta(rset.getInt(13));
				vEXPExamAplica.setLInterConcluye(rset.getInt(14));
				vEXPExamAplica.setLConcluido(rset.getInt(15));
				vEXPExamAplica.setDtDictamen(rset.getDate(16));
				vEXPExamAplica.setDtEntregaRes(rset.getDate(17));
				vEXPExamAplica.setDtArchivado(rset.getDate(18));
				vEXPExamAplica.setICveNumEmpresa(rset.getInt(19));
				vEXPExamAplica.setICveUsuSolicita(rset.getInt(20));
				vEXPExamAplica.setCDscSituacion(this.FindBySituacion(
						"" + rset.getInt(1), "" + rset.getInt(3),
						"" + rset.getInt(6), "" + rset.getInt(2),
						"" + rset.getInt(4)));
				vEXPExamAplica.setLPrivada(rset.getInt(21));
			}
		} catch (Exception ex) {
			warn("findExpediente", ex);
			throw new DAOException("findExpediente");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null) {
					if (dbConn != null) {
						dbConn.closeConnection();
					}
				}
			} catch (Exception ex2) {
				warn("findExpediente.close", ex2);
			}
		}
		return vEXPExamAplica;
	}

	/**
	 * Método publico que obtiene un registro de la tabla EXPExamAplica para el
	 * expediente solicitado
	 * 
	 * @param conGral
	 *            la conexi�n que es obtenida desde el Método anterior
	 * @param iCveExp
	 *            la clave del expediente
	 * @return un Value Object con el registro obtenido
	 * @throws DAOException
	 * @author Marco A. Gonz�lez Paz.
	 */
	public TVEXPExamAplica FindExpCon(Connection conGral, int iCveExp)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String cSQL = null;
		TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
		TFechas dtFecha = new TFechas();
		String cHoy = "";

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}

			cHoy = dtFecha.getFechaDDMMMYYYY(dtFecha.TodaySQL(), "/");
			cSQL = "select "
					+ "EXPExamAplica.iCveExpediente,"
					+ "EXPExamAplica.iNumExamen,"
					+ "EXPExamAplica.iCveUniMed,"
					+ "EXPExamAplica.iCveProceso,"
					+ "EXPExamAplica.iCvePersonal,"
					+ "EXPExamAplica.iCveModulo,"
					+ "EXPExamAplica.dtSolicitado,"
					+ "EXPExamAplica.iFolioEs,"
					+ "EXPExamAplica.dtAplicacion,"
					+ "EXPExamAplica.dtConcluido,"
					+ "EXPExamAplica.lIniciado,"
					+ "EXPExamAplica.lDictaminado,"
					+ "EXPExamAplica.lInterConsulta,"
					+ "EXPExamAplica.lInterConcluye,"
					+ "EXPExamAplica.lConcluido,"
					+ "EXPExamAplica.dtDictamen,"
					+ "EXPExamAplica.dtEntregaRes,"
					+ "EXPExamAplica.dtArchivado,"
					+ "EXPExamAplica.iCveNumEmpresa,"
					+ "EXPExamAplica.iCveUsuSolicita,"
					+ "GRLUniMed.lPrivada "
					+ "from EXPExamAplica "
					+ "inner join GRLUniMed ON GRLUniMed.iCveUniMed = EXPExamAplica.iCveUniMed "
					+ "where iCveExpediente = ? " + "and lConcluido = 1 "
					+ "and dtSolicitado = '" + cHoy + "'";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, iCveExp);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICvePersonal(rset.getInt(5));
				vEXPExamAplica.setICveModulo(rset.getInt(6));
				vEXPExamAplica.setDtSolicitado(rset.getDate(7));
				vEXPExamAplica.setIFolioEs(rset.getInt(8));
				vEXPExamAplica.setDtAplicacion(rset.getDate(9));
				vEXPExamAplica.setDtConcluido(rset.getDate(10));
				vEXPExamAplica.setLIniciado(rset.getInt(11));
				vEXPExamAplica.setLDictaminado(rset.getInt(12));
				vEXPExamAplica.setLInterConsulta(rset.getInt(13));
				vEXPExamAplica.setLInterConcluye(rset.getInt(14));
				vEXPExamAplica.setLConcluido(rset.getInt(15));
				vEXPExamAplica.setDtDictamen(rset.getDate(16));
				vEXPExamAplica.setDtEntregaRes(rset.getDate(17));
				vEXPExamAplica.setDtArchivado(rset.getDate(18));
				vEXPExamAplica.setICveNumEmpresa(rset.getInt(19));
				vEXPExamAplica.setICveUsuSolicita(rset.getInt(20));
				vEXPExamAplica.setCDscSituacion(this.FindBySituacion(
						"" + rset.getInt(1), "" + rset.getInt(3),
						"" + rset.getInt(6), "" + rset.getInt(2),
						"" + rset.getInt(4)));
				vEXPExamAplica.setLPrivada(rset.getInt(21));
			}
		} catch (Exception ex) {
			warn("findExpediente", ex);
			throw new DAOException("findExpediente");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null) {
					if (dbConn != null) {
						dbConn.closeConnection();
					}
				}
			} catch (Exception ex2) {
				warn("findExpediente.close", ex2);
			}
		}
		return vEXPExamAplica;
	}

	/**
	 * Método Find By All S
	 */
	public Vector FindByAll(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPExamAplica vEXPExamAplica;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "iCveExpediente,iNumExamen,iCveUniMed,iCveProceso,iCvePersonal,"
					+ "iCveModulo,dtSolicitado,iFolioEs,dtAplicacion,dtConcluido,"
					+ "lIniciado,lDictaminado,lInterConsulta,lInterConcluye,lConcluido,"
					+ "dtDictamen,dtEntregaRes,dtArchivado,iCveNumEmpresa,iCveUsuSolicita "
					+ "from EXPExamAplica " + "where " +
					// "lConcluido=1 and " +
					" iCveUniMed = ? and iCveModulo = ? and iCveProceso = ? " +
					// " and dtConcluido=? " +
					// hay que quitar el filtro de abajo solo temporal
					// Agrego MGonz�lez Paz 6:56 P.M. 17-enero-2005
					" and dtsolicitado=?" + " order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1,
					Integer.parseInt((String) hmFiltro.get("iCveUniMed")));
			pstmt.setInt(2,
					Integer.parseInt((String) hmFiltro.get("iCveModulo")));
			pstmt.setInt(3,
					Integer.parseInt((String) hmFiltro.get("iCveProceso")));
			pstmt.setDate(4, (java.sql.Date) hmFiltro.get("dtConcluido"));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica = new TVEXPExamAplica();
				vEXPExamAplica.setICveExpediente(rset.getInt("iCveExpediente"));
				vEXPExamAplica.setINumExamen(rset.getInt("iNumExamen"));
				vEXPExamAplica.setICveUniMed(rset.getInt("iCveUniMed"));
				vEXPExamAplica.setICveProceso(rset.getInt("iCveProceso"));
				vEXPExamAplica.setICvePersonal(rset.getInt("iCvePersonal"));
				vEXPExamAplica.setICveModulo(rset.getInt("iCveModulo"));
				vEXPExamAplica.setDtSolicitado(rset.getDate("dtSolicitado"));
				vEXPExamAplica.setIFolioEs(rset.getInt("iFolioEs"));
				vEXPExamAplica.setDtAplicacion(rset.getDate("dtAplicacion"));
				vEXPExamAplica.setDtConcluido(rset.getDate("dtConcluido"));
				vEXPExamAplica.setLIniciado(rset.getInt("lIniciado"));
				vEXPExamAplica.setLDictaminado(rset.getInt("lDictaminado"));
				vEXPExamAplica.setLInterConsulta(rset.getInt("lInterConsulta"));
				vEXPExamAplica.setLInterConcluye(rset.getInt("lInterConcluye"));
				vEXPExamAplica.setLConcluido(rset.getInt("lConcluido"));
				vEXPExamAplica.setDtDictamen(rset.getDate("dtDictamen"));
				vEXPExamAplica.setDtEntregaRes(rset.getDate("dtEntregaRes"));
				vEXPExamAplica.setDtArchivado(rset.getDate("dtArchivado"));
				vEXPExamAplica.setICveNumEmpresa(rset.getInt("iCveNumEmpresa"));
				vEXPExamAplica.setICveUsuSolicita(rset
						.getInt("iCveUsuSolicita"));
				vcEXPExamAplica.addElement(vEXPExamAplica);
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
		}
		return vcEXPExamAplica;
	}

	/**
	 * Método findUser (Utilizado para la Selecci�n de personal Asegurandose que
	 * lIniciado de la tabla de EXPExamAplica esta en "falso" (0))
	 * 
	 * @Autor: Javier Mendoza
	 */
	public TVPERDatos findUser(int iCvePersonal, int iNumExamen)
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
					+ " And   b.iNumExamen = " + iNumExamen +
					// " And   b.lIniciado  = 0" +
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
	 * Método findUserNoExam (Utilizado para la Selecci�n de personal
	 * Asegurandose que lIniciado de la tabla de EXPExamAplica esta en "falso"
	 * (0))
	 * 
	 * @Autor: Javier Mendoza
	 */
	public TVPERDatos findUserNoExam(int iCvePersonal) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "select"
					+ " a.iCvePersonal,a.iCveExpediente,a.cNombre,a.cApPaterno,a.cApMaterno,a.cSexo, a.cCurp,"
					+ " a.dtNacimiento,a.iCveNumEmpresa,a.cRFC"
					+ " From PerDatos a, EXPExamAplica b"
					+ " Where a.iCvePersonal= " + iCvePersonal
					+ " And   b.lIniciado  = 0"
					+ " And   a.iCvePersonal = b.iCvePersonal";
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
				vPERDatos.setCCURP(rset.getString("cCurp"));
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
	 * Método findUserNoExam (Utilizado para la Selecci�n de personal
	 * Asegurandose que lIniciado de la tabla de EXPExamAplica esta en "falso"
	 * (0))
	 * 
	 * @Autor: Javier Mendoza
	 */
	public TVPERDatos findUserNoExamNoAp(int iCvePersonal) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "select"
					+ " a.iCvePersonal,a.iCveExpediente,a.cNombre,a.cApPaterno,a.cApMaterno,a.cSexo, a.cCurp,"
					+ " a.dtNacimiento,a.iCveNumEmpresa,a.cRFC"
					+ " From PerDatos a " + " Where a.iCvePersonal= "
					+ iCvePersonal;
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
				vPERDatos.setCCURP(rset.getString("cCurp"));
				vPERDatos.setDtNacimiento(rset.getDate("dtNacimiento"));
				vPERDatos.setICveNumEmpresa(rset.getInt("iCveNumEmpresa"));
				vPERDatos.setCRFC(rset.getString("cRFC"));
			}
		} catch (Exception ex) {
			warn("findUserNoExamNoAp", ex);
			throw new DAOException("findUserNoExamNoAp");
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
				warn("findUserNoExamNoAp.close", ex2);
			}
			return vPERDatos;
		}
	}

	/**
	 * Método findUserbyExp (Utilizado para la Selecci�n de personal por
	 * Expediente Asegurandose que lIniciado de la tabla de EXPExamAplica esta
	 * en "falso" (0))
	 * 
	 * @Autor: Javier Mendoza
	 */
	public TVPERDatos findUserbyExp(Object obj) throws DAOException {
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
					+ "a.cSexo,a.dtNacimiento,a.iCveNumEmpresa,a.cRFC, b.iNumExamen "
					+ "from PerDatos a "
					+
					// , EXPExamAplica b " +
					"inner join  EXPExamAplica b  on a.iCveExpediente = b.iCveExpediente "
					+ "where a.iCveExpediente= "
					+ vEXPExamAplica.getICveExpediente()
					+ " And   b.iCveUniMed=    "
					+ vEXPExamAplica.getICveUniMed()
					+ " And   b.iCveModulo=    "
					+ vEXPExamAplica.getICveModulo()
					+ " And   b.iCveProceso=   "
					+ vEXPExamAplica.getICveProceso()
					+ " And   b.dtSolicitado = '"
					+ vEXPExamAplica.getDtSolicitado() + "' " +
					// " And   b.lIniciado = 0 " +
					" Order by b.iNumExamen ";
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
	public Vector FindExpExamCat(Connection conGral, Object obj)
			throws DAOException {
		TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamCat = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPExamCat vEXPExamCat;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select iCveMdoTrans, iCveCategoria, iCveMotivo "
					+ " From   EXPExamCat " + " Where iCveExpediente = "
					+ vEXPExamAplica.getICveExpediente()
					+ " And   iNumExamen     = "
					+ vEXPExamAplica.getINumExamen();

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamCat = new TVEXPExamCat();
				vEXPExamCat.setICveMdoTrans(rset.getInt(1));
				vEXPExamCat.setICveCategoria(rset.getInt(2));
				vEXPExamCat.setICveMotivo(rset.getInt(3));
				vcEXPExamCat.addElement(vEXPExamCat);
			}
		} catch (Exception ex) {
			warn("FindExpExamCat", ex);
			throw new DAOException("FindExpExamCat");
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
				warn("FindExpExamCat.close", ex2);
			}
			return vcEXPExamCat;
		}
	}

	/**
	 * Método Find By All
	 */
	public Vector FindExamAplicaXCat(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPExamAplica vEXPExamAplica;
			TVEXPExamCat vEXPExamCat;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "a.iCveExpediente,a.iNumExamen,a.iCveUniMed,a.iCveProceso,"
					+ "a.iCvePersonal,a.iCveModulo,a.dtSolicitado,a.iFolioEs,"
					+ "a.dtAplicacion,a.dtConcluido,a.lIniciado,a.lDictaminado,"
					+ "a.lInterConsulta,a.lInterConcluye,a.lConcluido,a.dtDictamen,"
					+ "a.dtEntregaRes,a.dtArchivado,a.iCveNumEmpresa,a.iCveUsuSolicita,"
					+ "b.cDscUniMed,c.cDscProceso,d.lDictamen,e.cDscMdoTrans,f.cDscCategoria "
					+ "from EXPExamAplica a "
					+ "inner join GRLUniMed b on (a.iCveUniMed=b.iCveUniMed) "
					+ "inner join GRLProceso c on (a.iCveProceso=c.iCveProceso) "
					+ "inner join EXPExamCat d on (a.iCveExpediente=d.iCveExpediente and "
					+ "a.iNumExamen=d.iNumExamen) "
					+ "inner join GRLMdoTrans e on (d.iCveMdoTrans=e.iCveMdoTrans) "
					+ "inner join GRLCategoria f on (d.iCveMdoTrans=f.iCveMdoTrans and "
					+ "d.iCveCategoria=f.iCveCategoria)";
			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");
			String cConcluido = (String) hmFiltros.get("lConcluido");
			java.sql.Date dtSolicitado = (java.sql.Date) hmFiltros
					.get("dtSolicitado");
			if (cCveExpediente != null) {
				cSQL += " where a.iCveExpediente=?";
				cWhereAnd = " and";
			}
			if (cNumExamen != null) {
				cSQL += cWhereAnd + " a.iNumExamen<?";
				cWhereAnd = " and";
			}
			if (cConcluido != null) {
				cSQL += cWhereAnd + " a.lConcluido=?";
				cWhereAnd = " and";
			}
			if (dtSolicitado != null) {
				cSQL += cWhereAnd + " a.dtSolicitado<?";
			}
			cSQL += " order by a.iCveExpediente,a.iNumExamen";
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cCveExpediente != null) {
				pstmt.setInt(i++, Integer.parseInt(cCveExpediente));
			}
			if (cNumExamen != null) {
				pstmt.setInt(i++, Integer.parseInt(cNumExamen));
			}
			if (cConcluido != null) {
				pstmt.setInt(i++, Integer.parseInt(cConcluido));
			}
			if (dtSolicitado != null) {
				pstmt.setDate(i++, dtSolicitado);
			}
			rset = pstmt.executeQuery();
			TVEXPExamAplica vEXPExamAplicaOld = null;
			Vector vcEXPExamCat = null;
			while (rset.next()) {
				vEXPExamAplica = new TVEXPExamAplica();
				vEXPExamCat = new TVEXPExamCat();
				vEXPExamAplica.setICveExpediente(rset.getInt("iCveExpediente"));
				vEXPExamAplica.setINumExamen(rset.getInt("iNumExamen"));

				if (!vEXPExamAplica.equals(vEXPExamAplicaOld)) {
					vcEXPExamCat = new Vector();
					vEXPExamCat.setLDictamen(rset.getInt("lDictamen"));
					vEXPExamCat.setCDscMdoTrans(rset.getString("cDscMdoTrans"));
					vEXPExamCat.setCDscCategoria(rset
							.getString("cDscCategoria"));
					vcEXPExamCat.addElement(vEXPExamCat);
					vEXPExamAplicaOld = vEXPExamAplica;
					vEXPExamAplica.setICveUniMed(rset.getInt("iCveUniMed"));
					vEXPExamAplica.setICveProceso(rset.getInt("iCveProceso"));
					vEXPExamAplica.setICvePersonal(rset.getInt("iCvePersonal"));
					vEXPExamAplica.setICveModulo(rset.getInt("iCveModulo"));
					vEXPExamAplica
							.setDtSolicitado(rset.getDate("dtSolicitado"));
					vEXPExamAplica.setIFolioEs(rset.getInt("iFolioEs"));
					vEXPExamAplica
							.setDtAplicacion(rset.getDate("dtAplicacion"));
					vEXPExamAplica.setDtConcluido(rset.getDate("dtConcluido"));
					vEXPExamAplica.setLIniciado(rset.getInt("lIniciado"));
					vEXPExamAplica.setLDictaminado(rset.getInt("lDictaminado"));
					vEXPExamAplica.setLInterConsulta(rset
							.getInt("lInterConsulta"));
					vEXPExamAplica.setLInterConcluye(rset
							.getInt("lInterConcluye"));
					vEXPExamAplica.setLConcluido(rset.getInt("lConcluido"));
					vEXPExamAplica.setDtDictamen(rset.getDate("dtDictamen"));
					vEXPExamAplica
							.setDtEntregaRes(rset.getDate("dtEntregaRes"));
					vEXPExamAplica.setDtArchivado(rset.getDate("dtArchivado"));
					vEXPExamAplica.setICveNumEmpresa(rset
							.getInt("iCveNumEmpresa"));
					vEXPExamAplica.setICveUsuSolicita(rset
							.getInt("iCveUsuSolicita"));
					vEXPExamAplica.setCDscUniMed(rset.getString("cDscUniMed"));
					vEXPExamAplica
							.setCDscProceso(rset.getString("cDscProceso"));
					vEXPExamAplica.setVcEXPExamCat(vcEXPExamCat);
					vcEXPExamAplica.addElement(vEXPExamAplica);
				} else {
					vEXPExamCat.setLDictamen(rset.getInt("lDictamen"));
					vEXPExamCat.setCDscMdoTrans(rset.getString("cDscMdoTrans"));
					vEXPExamCat.setCDscCategoria(rset
							.getString("cDscCategoria"));
					vcEXPExamCat.addElement(vEXPExamCat);
				}
			}
		} catch (Exception ex) {
			warn("FindExamAplicaXCat", ex);
			throw new DAOException("FindExamAplicaXCat");
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
				warn("FindExamAplicaXCat.close", ex2);
			}
		}
		return vcEXPExamAplica;
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
			;
			// // System.out.println(this.getClass().getName() + ":" +
			// obj.toString());
		}
	}

	/**
	 * Método FindProdDictamen
	 */
	public Vector FindProdDictamen(String iCveUniMed, String iCveModulo,
			String iCveProceso, String cDesde, String cHasta)
			throws DAOException {

		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vRegresa = new Vector();
		TVEXPExamAplica tvEXPExamAplica;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " SELECT E.dtSolicitado, ( "
					+ " SELECT count(EA.lDictaminado) "
					+ " FROM EXPExamAplica EA "
					+ " WHERE EA.icveexpediente > 1 "
					+ " AND EA.dtSolicitado = E.dtSolicitado "
					+ " GROUP BY dtSolicitado ) Generados, "
					+ "(SELECT count(EA.lDictaminado) "
					+ " FROM EXPExamAplica EA "
					+ " WHERE EA.icveexpediente > 1 "
					+ " AND EA.lDictaminado = 1 "
					+ " AND EA.dtSolicitado = E.dtSolicitado "
					+ " GROUP BY lDictaminado) Dictamenes, "
					+ "(SELECT count(EA.lDictaminado) "
					+ " FROM EXPExamAplica EA "
					+ " WHERE EA.icveexpediente > 1 "
					+ " AND EA.lDictaminado = 0 "
					+ " AND EA.dtSolicitadocitado = E.dtSolicitado "
					+ " GROUP BY lDictaminado) No_Aptos_Temporales "
					+ " FROM EXPExamAplica E "
					+ " WHERE E.icveexpediente > 1 "
					+ ((cDesde.trim().length() > 0 && cHasta.trim().length() > 0) ? " AND E.dtSolicitado BETWEEN '"
							+ cDesde + "' AND '" + cHasta + "' "
							: "") +
					// " AND E.dtSolicitado BETWEEN '2005-04-11' AND '2005-04-15' "+
					" GROUP BY E.dtSolicitado ";
			/*
			 * 
			 * 
			 * Modificacion del proceso
			 */
			// ========================================================================================

			String fechaExamen2 = cDesde + "-";
			String dia = "";
			String mes = "";
			String Ano = "";
			// //System.out.println();
			StringTokenizer solDatos = new StringTokenizer(fechaExamen2, "-");
			Ano = solDatos.nextToken();
			mes = solDatos.nextToken();
			dia = solDatos.nextToken();
			int diaDesde = Integer.parseInt(dia.trim());
			int mesDesde = Integer.parseInt(mes.trim());
			int AnoDesde = Integer.parseInt(Ano.trim());

			fechaExamen2 = cHasta + "-";

			solDatos = new StringTokenizer(fechaExamen2, "-");
			Ano = solDatos.nextToken();
			mes = solDatos.nextToken();
			dia = solDatos.nextToken();
			int diaHasta = Integer.parseInt(dia.trim());
			int mesHasta = Integer.parseInt(mes.trim());
			int AnoHasta = Integer.parseInt(Ano.trim());

			String fechasol = "";
			String fechal = "0000, 00, 00";
			String fecha2 = "0000, 00, 00";
			String diafecha = "";
			String diaT;
			String mesT;
			String AnoT;

			java.sql.Date dtSolicitado2;
			int d = 0;
			int m = 0;
			int a = 0;
			int diaMax = 0;
			int diaMin = 0;

			int AptosQ = 0;
			int AptosQS = 0;

			String AptosQ2 = "";
			int NoAptosQ = 0;
			int NoAptosQS = 0;
			String NoAptosQ2 = "";
			int cont = 0;
			int contR = 0;
			int contR2 = 0;
			int contM = 0;
			int diaMinimo = 0;

			stmt = conn.createStatement();
			// tvEXPExamAplica = new TVEXPExamAplica();
			Vector vcEXPExamCat = null;

			Vector FVig = new Vector();
			Vector Apts = new Vector();
			Vector Apts2 = new Vector();
			Vector NoApts = new Vector();
			Vector NoApts2 = new Vector();

			Vector VectorNP1 = new Vector();
			Vector VectorNP2 = new Vector();
			Vector VectorNP3 = new Vector();

			// String cSQL= "";

			for (a = AnoDesde; a <= AnoHasta; a++) {
				// System.out.println("Ano = "+ a);
				// System.out.println("m = "+ mesDesde +"  mesHasta ="
				// +mesHasta);

				for (m = mesDesde; m <= mesHasta; m++) {

					// System.out.println("Entrando al for del mes");

					if (m != mesHasta) {
						if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8
								|| m == 10 || m == 12)
							diaMax = 31;
						if (m == 4 || m == 6 || m == 9 || m == 11)
							diaMax = 30;
						if (m == 2)
							diaMax = 28;
						if (m == 2 && ((a % 4 == 0) && (a % 100 != 0))
								|| (a % 400 == 0))
							diaMax = 29;
						diaMin = 1;
					} else {
						diaMax = diaHasta;
					}
					if (m == mesDesde)
						diaMin = diaDesde;
					if (m == mesHasta)
						diaMin = 1;

					if (contM == 0)
						diaMin = diaDesde;
					contM++;

					diaMinimo = diaMin;
					int diaM = diaMin;
					int Mes = m;
					// int Ano = a;

					// System.out.println("Ano = " +a+" mes = "+ m + " diamax" +
					// diaMax + " diaMin " + diaMin);

					// Metodo Alternativo

					// // aptos
					cSQL = "select  dtinicioVig, icvemdotrans, count(distinct iCvePersonal) "
							+ "from expexamcat  "
							+ "join expexamaplica on expexamcat.icveexpediente = expexamaplica.icveexpediente "
							+ "and expexamcat.inumexamen = expexamaplica.inumexamen "
							+

							(iCveUniMed.trim().length() > 0 ? "  AND expexamaplica.iCveUniMed = "
									+ iCveUniMed
									: "")
							+ (iCveModulo.trim().length() > 0 ? "  AND expexamaplica.iCveModulo = "
									+ iCveModulo
									: "")
							+ (iCveProceso.trim().length() > 0 ? "  AND expexamaplica.iCveProceso = "
									+ iCveProceso
									: "")
							+

							// " and expexamaplica.icveunimed = 1 "+
							// " and expexamaplica.icvemodulo = 1 "+
							// " and expexamaplica.icveproceso = 1 "+
							" and expexamcat.DTINICIOVIG BETWEEN '"
							+ a
							+ "-"
							+ m
							+ "-"
							+ diaMin
							+ "'  AND '"
							+ a
							+ "-"
							+ m
							+ "-"
							+ diaMax
							+ "' "
							+ " where icvemdotrans <9 "
							+ " and icvepersonal > 1 "
							+ " and ldictamen = 1 group by dtinicioVig, icvemdotrans";
					// System.out.println(cSQL);
					rset = stmt.executeQuery(cSQL);

					SimpleDateFormat format = new SimpleDateFormat(
							"yyyy, MM, dd");
					java.util.Date parsedUtilDate = format.parse(fechal);
					java.sql.Date sqltDate2 = new java.sql.Date(
							parsedUtilDate.getTime());

					while (rset.next()) {

						if (m < 10)
							fechasol = "" + a + ", 0" + m + "";

						if (m > 9)
							fechasol = "" + a + ", " + m + "";

						if (diaMin < 10)
							fechasol += ", 0" + diaMin + "";

						if (diaMin > 9)
							fechasol += ", " + diaMin + "";

						// System.out.println("Fecha solicitada = " +fechasol+
						// " Registro Fecha = "+rset.getDate(1)+
						// "  Registro = "+rset.getInt(3)+ "dia minimo = "+
						// diaMin);
						SimpleDateFormat format1 = new SimpleDateFormat(
								"yyyy, MM, dd");
						java.util.Date parsedUtilDate1 = format1
								.parse(fechasol);
						java.sql.Date sqltDate = new java.sql.Date(
								parsedUtilDate.getTime());
						long dif = sqltDate.getTime()
								- rset.getDate("dtinicioVig").getTime();

						AptosQ = rset.getInt(3);

						diafecha = rset.getString("dtinicioVig") + "-";

						StringTokenizer solDatos1 = new StringTokenizer(
								diafecha, "-");
						AnoT = solDatos1.nextToken();
						mesT = solDatos1.nextToken();
						diaT = solDatos1.nextToken();

						// System.out.println("Ano = " + AnoT +"  mes = "+mesT+
						// "  dia = " +diaT );
						int temp3 = Integer.parseInt(diaT.trim());

						for (int h = 0; diaMin <= temp3; diaMin++) {
							if (cont > 0) {
								VectorNP1.insertElementAt("0", contR);
								contR++;
							}
						}

						// System.out.println(" dia desde = " + diaDesde +
						// " diaMin = " + diaMin + " dia temp3 = " + temp3 +
						// "");
						if (rset.getDate("dtinicioVig").compareTo(sqltDate2) > 0) {
							// System.out.println("mayor");

							if (cont == 0 && diaDesde < temp3) {
								int resdia = temp3 - diaDesde;
								// System.out.println("Nueva condicion si se cumplio  temp3 = "
								// + temp3 +" cont = " +cont+ " diaDesde = " +
								// diaDesde +" resdia = "+resdia);
								for (int h = 0; resdia > 0; resdia--) {
									VectorNP1.insertElementAt("0", contR);
									contR++;
								}
								// System.out.println("Nueva condicion para el for si se cumplio temp3 = "+temp3
								// + " resdia = " + resdia + " contR = " +contR
								// +"");
							}
							VectorNP1.insertElementAt(AptosQ + "", contR);
						} else {
							// //
							// System.out.println("menor     temp3 = "+temp3);
							contR--;
							String temp = VectorNP1.elementAt(contR) + "";
							int temp2 = Integer.parseInt(temp.trim());
							temp2 += AptosQ;
							VectorNP1.removeElementAt(contR);
							VectorNP1.insertElementAt(temp2 + "", contR);
							diaMin--;
							// //
							// System.out.println("temp2 = "+temp2+"  diaMin = "
							// +diaMin+ "  contR = " +contR);
						}

						sqltDate2 = rset.getDate("dtinicioVig");

						contR++;
						cont++;
						diaMin++;
						contR2 = contR;
					}

					contR = 0;
					cont = 0;
					AptosQ = 0;

					// ////////// noaptos
					// System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");

					cSQL = " select  dtinicioVig, icvemdotrans, count(distinct iCvePersonal) "
							+ " from expexamcat  "
							+ " join expexamaplica on expexamcat.icveexpediente = expexamaplica.icveexpediente "
							+ " and expexamcat.inumexamen = expexamaplica.inumexamen "
							+

							(iCveUniMed.trim().length() > 0 ? "  AND expexamaplica.iCveUniMed = "
									+ iCveUniMed
									: "")
							+ (iCveModulo.trim().length() > 0 ? "  AND expexamaplica.iCveModulo = "
									+ iCveModulo
									: "")
							+ (iCveProceso.trim().length() > 0 ? "  AND expexamaplica.iCveProceso = "
									+ iCveProceso
									: "")
							+

							// " and expexamaplica.icveunimed = 1 "+
							// " and expexamaplica.icvemodulo = 1 "+
							// " and expexamaplica.icveproceso = 1 "+
							" and expexamcat.DTINICIOVIG BETWEEN '"
							+ a
							+ "-"
							+ m
							+ "-"
							+ diaMinimo
							+ "'  AND '"
							+ a
							+ "-"
							+ m
							+ "-"
							+ diaMax
							+ "' "
							+ " where icvemdotrans <9 "
							+ " and icvepersonal > 1 "
							+ " and ldictamen = 0 group by dtinicioVig, icvemdotrans";
					// System.out.println(cSQL);

					rset = stmt.executeQuery(cSQL);

					format = new SimpleDateFormat("yyyy, MM, dd");
					parsedUtilDate = format.parse(fecha2);
					java.sql.Date sqltDate4 = new java.sql.Date(
							parsedUtilDate.getTime());

					while (rset.next()) {

						// System.out.println("============================================================================");
						// System.out.println(rset.getString("dtinicioVig"));
						// System.out.println(rset.getInt(3));

						if (m < 10)
							fechasol = "" + a + ", 0" + m + "";

						if (m > 9)
							fechasol = "" + a + ", " + m + "";

						if (diaMinimo < 10)
							fechasol += ", 0" + diaMinimo + "";

						if (diaMinimo > 9)
							fechasol += ", " + diaMinimo + "";

						// System.out.println("Fecha solicitada = " +fechasol+
						// " Registro Fecha = "+rset.getDate(1)+
						// "  Registro = "+rset.getInt(3)+ "dia minimo = "+
						// diaMinimo);
						SimpleDateFormat format1 = new SimpleDateFormat(
								"yyyy, MM, dd");
						java.util.Date parsedUtilDate1 = format1
								.parse(fechasol);
						java.sql.Date sqltDate3 = new java.sql.Date(
								parsedUtilDate.getTime());
						long dif = sqltDate3.getTime()
								- rset.getDate("dtinicioVig").getTime();

						NoAptosQ = rset.getInt(3);

						diafecha = rset.getString("dtinicioVig") + "-";

						StringTokenizer solDatos1 = new StringTokenizer(
								diafecha, "-");
						AnoT = solDatos1.nextToken();
						mesT = solDatos1.nextToken();
						diaT = solDatos1.nextToken();

						// System.out.println("Ano = " + AnoT +"  mes = "+mesT+
						// "  dia = " +diaT );
						int temp3 = Integer.parseInt(diaT.trim());
						// System.out.println("diaMinimo " +diaMinimo);
						// System.out.println("tem3 "+temp3);
						for (int h = 0; diaMinimo <= temp3; diaMinimo++) {
							if (cont > 0) {
								VectorNP2.insertElementAt("0", contR);
								contR++;
								// System.out.println("insertando en la pocision "
								// + contR+ " Vector2 = 0");
							}
						}

						// System.out.println(" dia desde = " + diaDesde +
						// " diaMinimo = " + diaMinimo + " dia temp3 = " + temp3
						// + "");

						// System.out.println("dtinicioVig " +
						// rset.getDate("dtinicioVig")+
						// " sqltDate = "+sqltDate4);
						if (rset.getDate("dtinicioVig").compareTo(sqltDate4) > 0) {
							// //System.out.println("mayor");

							if (cont == 0 && diaDesde < temp3) {
								int resdia = temp3 - diaDesde;
								// //System.out.println("Nueva condicion si se cumplio diaDesde = "
								// +diaDesde+ " temp3 = "+ temp3 );
								for (int h = 0; resdia > 0; resdia--) {
									// //System.out.println("Nueva condicion para el for si se cumplio resdia = "
									// + resdia +"");
									VectorNP2.insertElementAt("0", contR);
									contR++;
									// //System.out.println("insertando en la pocision "
									// + contR+ " Vector2 = 0");
								}
							}
							VectorNP2.insertElementAt(NoAptosQ + "", contR);
						} else {
							// //System.out.println("menor");
							contR--;
							String temp = VectorNP2.elementAt(contR) + "";
							int temp2 = Integer.parseInt(temp.trim());
							temp2 += NoAptosQ;
							// //System.out.println("temp2 = " + temp2 +
							// " AptosQ = " +NoAptosQ);
							VectorNP2.removeElementAt(contR);
							VectorNP2.insertElementAt(temp2 + "", contR);
							diaMinimo--;
							// //System.out.println("insertando en la pocision "
							// + contR+ " Vector2 = " +temp2);
						}

						sqltDate4 = rset.getDate("dtinicioVig");

						contR++;
						cont++;
						diaMinimo++;
						// //System.out.println("\n");
					}

					// //System.out.println(" Vector1 = "+VectorNP1.size() +
					// "  vector2 = " +VectorNP2.size());
					if (VectorNP1.size() > VectorNP2.size()) {
						// //System.out.println("vector 1 mayor a vector 2");
						int restantes = 0;
						restantes = VectorNP1.size() - VectorNP2.size();
						for (int c = 0; VectorNP1.size() > VectorNP2.size(); c++) {
							// //System.out.println("entrando al for");
							// //System.out.println(c);
							int tamAno = 0;
							tamAno = VectorNP2.size();

							VectorNP2.insertElementAt("0", tamAno);
							// //System.out.println(VectorNP2.size());
							// //System.out.println(tamAno);
						}
					}

					if (VectorNP2.size() > VectorNP1.size()) {
						// //System.out.println("vector 1 mayor a vector 2");
						int restantes = 0;
						restantes = VectorNP2.size() - VectorNP1.size();
						for (int c = 0; VectorNP2.size() > VectorNP1.size(); c++) {
							// //System.out.println("entrando al for");
							// //System.out.println(c);
							int tamAno = 0;
							tamAno = VectorNP1.size();
							VectorNP1.insertElementAt("0", tamAno);
							// //System.out.println(VectorNP1.size());
							// //System.out.println(tamAno);
						}
					}

					// System.out.println(" Vector1 = "+VectorNP1.size() +
					// "  vector2 = " +VectorNP2.size());
					for (cont = 0; cont < contR2; cont++) {
						String fechaF = "";
						if (m < 10)
							fechaF = "" + Ano + "-0" + Mes + "";

						if (m > 9)
							fechaF = "" + Ano + "-" + Mes + "";

						if (diaM < 10)
							fechaF += "-0" + diaM + "";

						if (diaM > 9)
							fechaF += "-" + diaM + "";

						/*
						 * SimpleDateFormat format5 = new
						 * SimpleDateFormat("yyyy, MM, dd"); java.util.Date
						 * parsedUtilDate5 = format.parse(fechaF); java.sql.Date
						 * sqltDate5= new
						 * java.sql.Date(parsedUtilDate.getTime());
						 */
						// System.out.println("fechaF="+fechaF);
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd");
						java.sql.Date effect_from = new java.sql.Date(formatter
								.parse(fechaF).getTime());

						tvEXPExamAplica = new TVEXPExamAplica();
						// System.out.println("FECHA" +effect_from);
						tvEXPExamAplica.setDtSolicitado(effect_from);
						// System.out.println("Contador "+cont+"");
						// System.out.println("Vector1  "+VectorNP1.elementAt(cont)+"");
						// System.out.println("Vector2  "+VectorNP2.elementAt(cont)+"");

						String AptosD = VectorNP1.elementAt(cont) + "";
						int AptosD2 = Integer.parseInt(AptosD.trim());
						String NoAptosD = VectorNP2.elementAt(cont) + "";
						int NoAptosD2 = Integer.parseInt(NoAptosD.trim());

						tvEXPExamAplica.setIGenerados(AptosD2 + NoAptosD2);
						tvEXPExamAplica.setIDictaminados(AptosD2);
						tvEXPExamAplica.setINoDictaminados(NoAptosD2);
						vRegresa.addElement(tvEXPExamAplica);
						// System.out.println(" cont =  "+cont + "  vector1 = "
						// +VectorNP1.elementAt(cont)+ "  vector2 = "
						// +VectorNP2.elementAt(cont)+ " effect_from " +
						// effect_from+"  fechaF  " + fechaF);

						diaM++;
					}

					contR = 0;
					cont = 0;
					AptosQ = 0;
					NoAptosQ = 0;
					/*
					 * cSQL=
					 * "select  dtinicioVig, icvemdotrans, count(distinct iCvePersonal) "
					 * + "from expexamcat  "+
					 * "join expexamaplica on expexamcat.icveexpediente = expexamaplica.icveexpediente "
					 * +
					 * "and expexamcat.inumexamen = expexamaplica.inumexamen "+
					 * "and expexamaplica.icveunimed = 1 "+
					 * "and expexamaplica.icvemodulo = 1 "+
					 * "and expexamaplica.icveproceso = 1 "+
					 * "and YEAR(expexamcat.dtiniciovig) = " + a +
					 * " and MONTH(expexamcat.dtiniciovig) = " + m +
					 * " where icvemdotrans < 9 "+ " and icvepersonal > 1 "+
					 * "and ldictamen = 0 group by dtinicioVig, icvemdotrans";
					 * rset = stmt.executeQuery(cSQL);
					 * 
					 * contR=0;
					 * 
					 * while (rset.next()) { if(m < 10)
					 * fechasol=""+a+", 0"+m+"";
					 * 
					 * if(m > 9) fechasol=""+a+", "+m+"";
					 * 
					 * if(diaMin < 10) fechasol+=", 0"+diaMin+"";
					 * 
					 * if(diaMin > 9) fechasol+=", "+diaMin+"";
					 * 
					 * sqltDate2= rset.getDate("dtinicioVig"); // fechasol=""
					 * +a+"-"+ m + "-" + diaMin + ""; SimpleDateFormat format1 =
					 * new SimpleDateFormat("yyyy, MM, dd"); java.util.Date
					 * parsedUtilDate1 = format1.parse(fechasol); java.sql.Date
					 * sqltDate= new java.sql.Date(parsedUtilDate.getTime());
					 * long dif = sqltDate.getTime() - sqltDate2.getTime();
					 * 
					 * if(cont == 0 && dif == 0){ NoAptosQS = rset.getInt(3);
					 * NoAptosQ += NoAptosQS; cont++; diaMin++; }
					 * 
					 * if(cont > 0 && dif == 1){ NoAptosQS = rset.getInt(3);
					 * NoAptosQ += NoAptosQS; cont++; }
					 * 
					 * if(cont > 0 && dif == 0){
					 * VectorNP2.insertElementAt(NoAptosQ+"", contR); contR++;
					 * NoAptosQ = 0; NoAptosQS = 0; NoAptosQS = rset.getInt(3);
					 * NoAptosQ += NoAptosQS; cont++; diaMin++; }
					 * 
					 * if(cont > 0 && dif < 0){ for(int i = 0; dif == 0; dif++){
					 * VectorNP2.insertElementAt(NoAptosQ+"", contR); contR++;
					 * NoAptosQ = 0; NoAptosQS = 0; } NoAptosQS =
					 * rset.getInt(3); NoAptosQ += NoAptosQS; cont++; diaMin++;
					 * } }
					 * 
					 * for(cont=0; diaMax == cont; cont++){
					 * VectorNP2.elementAt(cont);
					 * //System.out.print("====  "+cont + "  ===   "
					 * +VectorNP1.elementAt(cont)+"  ===   "
					 * +VectorNP2.elementAt(cont)+"\t"); }
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * 
					 * //fin metodo Alternativo
					 * 
					 * for( d = diaMin ; d <= diaMax; d++){
					 * ////System.out.println("Entrando al for del dia");
					 * ////System.out.println("dia "+ d);
					 * 
					 * //Query y llenado del vector cSQL=
					 * "select  icvemdotrans, count(distinct iCvePersonal) "+
					 * "from expexamcat  "+
					 * "join expexamaplica on expexamcat.icveexpediente = expexamaplica.icveexpediente "
					 * +
					 * "and expexamcat.inumexamen = expexamaplica.inumexamen "+
					 * "and expexamaplica.icveunimed = 1 "+
					 * "and expexamaplica.icvemodulo = 1 "+
					 * "and expexamaplica.icveproceso = 1 "+
					 * "and YEAR(expexamcat.dtiniciovig) = " + a +
					 * " and MONTH(expexamcat.dtiniciovig) = " + m +
					 * " and DAY(expexamcat.dtiniciovig) = " + d +
					 * " where icvemdotrans <9 "+ " and icvepersonal > 1 "+
					 * "and ldictamen = 1 group by icvemdotrans";
					 * 
					 * 
					 * rset = stmt.executeQuery(cSQL); int rowCount =
					 * rset.getRow(); contR = rowCount;
					 * ////System.out.println("ContadorRow = " + rowCount);
					 * while (rset.next()) { //contR = contR-1;
					 * ////System.out.println
					 * ("Resultado = "+rset.getString(2)+" ContR = " +contR);
					 * AptosQS = rset.getInt(2); AptosQ += AptosQS; //
					 * Apts2.insertElementAt(rset.getString(2), contR);
					 * 
					 * //AptosQ = rset.getInt(1); }
					 * 
					 * cSQL= "select  count(distinct iCvePersonal) "+
					 * "from expexamcat  "+
					 * "join expexamaplica on expexamcat.icveexpediente = expexamaplica.icveexpediente "
					 * +
					 * "and expexamcat.inumexamen = expexamaplica.inumexamen "+
					 * "and expexamaplica.icveunimed = 1 "+
					 * "and expexamaplica.icvemodulo = 1 "+
					 * "and expexamaplica.icveproceso = 1 "+
					 * "and YEAR(expexamcat.dtiniciovig) = " + a +
					 * " and MONTH(expexamcat.dtiniciovig) = " + m +
					 * " and DAY(expexamcat.dtiniciovig) = " + d +
					 * " where icvemdotrans <9 "+ " and icvepersonal > 1 "+
					 * "and ldictamen = 0; ";
					 * 
					 * ////System.out.println(
					 * "========================================");
					 * ////System.out.println(cSQL); ////System.out.println(
					 * "========================================");
					 * 
					 * rset = stmt.executeQuery(cSQL); while (rset.next()) {
					 * NoAptosQ = rset.getInt(1); }
					 * 
					 * if(m < 10) fechasol=""+a+", 0"+m+"";
					 * 
					 * if(m > 9) fechasol=""+a+", "+m+"";
					 * 
					 * if(d < 10) fechasol+=", 0"+d+"";
					 * 
					 * if(d > 9) fechasol+=", "+d+"";
					 * 
					 * /* if(m < 10 && d < 10) fechasol=""+a+", 0"+m+", 0"+d+"";
					 * 
					 * if(m < 10 && d > 10) fechasol=""+a+", 0"+m+", "+d+"";
					 * 
					 * if(m > 10 && d < 10) fechasol=""+a+", "+m+", 0"+d+"";
					 * 
					 * if(m > 10 && d > 10) fechasol=""+a+", 0"+m+", 0"+d+"";
					 */
					/*
					 * FVig.insertElementAt(fechasol, cont);
					 * Apts.insertElementAt(AptosQ2, cont);
					 * NoApts.insertElementAt(NoAptosQ2, cont); cont ++;
					 * 
					 * SimpleDateFormat format = new
					 * SimpleDateFormat("yyyy, MM, dd"); java.util.Date
					 * parsedUtilDate = format.parse(fechasol); java.sql.Date
					 * sqltDate= new java.sql.Date(parsedUtilDate.getTime());
					 * 
					 * tvEXPExamAplica = new TVEXPExamAplica();
					 * tvEXPExamAplica.setDtSolicitado(sqltDate);
					 * tvEXPExamAplica.setIGenerados(AptosQ+NoAptosQ);
					 * tvEXPExamAplica.setIDictaminados(AptosQ);
					 * tvEXPExamAplica.setINoDictaminados(NoAptosQ);
					 * vRegresa.addElement(tvEXPExamAplica);
					 * 
					 * ////System.out.println(
					 * "========================================");
					 * ////System.out.println("Ano = " +a+" mes = "+ m +
					 * " diamax" + diaMax + " diaMin " + diaMin);
					 * 
					 * ////System.out.println(fechasol);
					 * ////System.out.println(AptosQ+NoAptosQ);
					 * ////System.out.println(AptosQ);
					 * ////System.out.println(NoAptosQ);
					 * 
					 * // AptosQ = 0; // AptosQS = 0; //for }
					 */
					if (m == 13)
						m = 1;
				}
				// //System.out.println("Ano = " +a);
				a++;
			}

			// ========================================================================================

			// //System.out.println("Query numero 2" + cSQL);
			/*
			 * stmt = conn.createStatement(); rset = stmt.executeQuery(cSQL);
			 * Vector vcEXPExamCat = null; while (rset.next()) { tvEXPExamAplica
			 * = new TVEXPExamAplica();
			 * tvEXPExamAplica.setDtSolicitado(rset.getDate("dtSolicitado"));
			 * tvEXPExamAplica.setIGenerados(rset.getInt("Generados"));
			 * tvEXPExamAplica.setIDictaminados(rset.getInt("Dictamenes"));
			 * tvEXPExamAplica
			 * .setINoDictaminados(rset.getInt("No_Aptos_Temporales"));
			 * vRegresa.addElement(tvEXPExamAplica); }
			 * 
			 * 
			 * cont = 0;
			 * 
			 * while(cont <= FVig.size()){ SimpleDateFormat format = new
			 * SimpleDateFormat("yyyy, MM, dd"); java.util.Date parsedUtilDate =
			 * format.parse(FVig.elementAt(cont).toString()); java.sql.Date
			 * sqltDate= new java.sql.Date(parsedUtilDate.getTime());
			 * 
			 * FVig.insertElementAt(fechasol, cont); AptosQ =
			 * Integer.parseInt(Apts.elementAt(cont).toString().trim());
			 * NoAptosQ =
			 * Integer.parseInt(Apts.elementAt(cont).toString().trim());
			 * 
			 * cont++;
			 * 
			 * tvEXPExamAplica = new TVEXPExamAplica();
			 * tvEXPExamAplica.setDtSolicitado(sqltDate);
			 * tvEXPExamAplica.setIGenerados(AptosQ+NoAptosQ);
			 * tvEXPExamAplica.setIDictaminados(AptosQ);
			 * tvEXPExamAplica.setINoDictaminados(NoAptosQ);
			 * vRegresa.addElement(tvEXPExamAplica);
			 * 
			 * }
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindProdDictamen.close", ex2);
			}
		}
		return vRegresa;
	}

	/**
	 * Método FindProdDictamen2
	 */
	public Vector FindProdDictamen2(String iCveUniMed, String iCveModulo,
			String iCveProceso, String cDesde, String cHasta)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vRegresa = new Vector();
		TVEXPExamAplica tvEXPExamAplica;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " SELECT E.dtSolicitado, E.iCveUsuDictamen, U.cNombre || ' ' || U.cApPaterno || ' ' || U.cApMaterno AS cDictaminador, "
					+ " (SELECT count(EA.lDictaminado) "
					+ " FROM EXPExamAplica EA "
					+ " WHERE EA.icveexpediente > 1 "
					+ " AND EA.lDictaminado = 1 "
					+ " AND EA.dtSolicitado = E.dtSolicitado "
					+ " AND EA.iCveUsuDictamen = E.iCveUsuDictamen "
					+ " GROUP BY lDictaminado) Dictamenes "
					+ " FROM EXPExamAplica E "
					+ " INNER JOIN SEGUsuario U ON U.icveUsuario = E.iCveUsuDictamen "
					+ " WHERE E.icveexpediente > 1 "
					+ (iCveUniMed.trim().length() > 0 ? " AND E.iCveUniMed = "
							+ iCveUniMed : "")
					+ (iCveModulo.trim().length() > 0 ? " AND E.iCveModulo = "
							+ iCveModulo : "")
					+ (iCveProceso.trim().length() > 0 ? " AND E.iCveProceso = "
							+ iCveProceso
							: "")
					+ ((cDesde.trim().length() > 0 && cHasta.trim().length() > 0) ? " AND E.dtSolicitado BETWEEN '"
							+ cDesde + "' AND '" + cHasta + "' "
							: "")
					+ " GROUP BY E.iCveUsuDictamen, U.cNombre , U.cApPaterno ,U.cApMaterno, E.dtSolicitado ";

			/*
			 * String cSQL =
			 * "SELECT C.DTINICIOVIG, E.iCveUsuDictamen, U.cNombre || ' ' || U.cApPaterno || ' ' || U.cApMaterno AS cDictaminador,  "
			 * + // "  (	SELECT count(distinct EA.lDictaminado) "+
			 * " (	SELECT count(distinct EC.icveexpediente)  "+
			 * "          FROM EXPEXAMCAT EC,EXPExamAplica EA "+
			 * "          WHERE 	EA.icveexpediente > 1 AND "+
			 * "                  EA.iCveExpediente = EC.IcveExpediente AND "+
			 * "                  EA.iNumexamen = EC.iNumexamen AND "+
			 * "                  EA.lDictaminado = 1 AND  EC.icvemdotrans < 9 and "
			 * + "                  EC.DTINICIOVIG = C.DTINICIOVIG AND "+
			 * "                  EA.iCveUsuDictamen = E.iCveUsuDictamen "+
			 * "          GROUP BY lDictaminado) Dictamenes  "+
			 * "  FROM EXPEXAMCAT C,EXPExamAplica E "+
			 * "  INNER JOIN SEGUsuario U "+
			 * "  ON U.icveUsuario = E.iCveUsuDictamen  "+
			 * "  WHERE E.icveexpediente > 1  "+
			 * "  AND E.iCveExpediente = C.IcveExpediente "+
			 * "  AND E.iNumexamen = C.iNumexamen "+ (iCveUniMed.trim().length()
			 * > 0 ?"  AND E.iCveUniMed = "+iCveUniMed:"")+
			 * (iCveModulo.trim().length() > 0
			 * ?"  AND E.iCveModulo = "+iCveModulo:"")+
			 * (iCveProceso.trim().length() > 0
			 * ?"  AND E.iCveProceso = "+iCveProceso:"")+
			 * " AND C.icvemdotrans < 9 "+ ((cDesde.trim().length() > 0 &&
			 * cHasta.trim().length() > 0)
			 * ?"  AND C.DTINICIOVIG  BETWEEN '"+cDesde
			 * +"' AND '"+cHasta+"' ":"")+
			 * "  GROUP BY E.iCveUsuDictamen, U.cNombre , U.cApPaterno ,U.cApMaterno, C.DTINICIOVIG "
			 * ;
			 */

			//System.out.println("Query numero 1" + cSQL);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			Vector vcEXPExamCat = null;
			while (rset.next()) {
				tvEXPExamAplica = new TVEXPExamAplica();
				tvEXPExamAplica.setDtSolicitado(rset.getDate("dtSolicitado"));
				// tvEXPExamAplica.setDtSolicitado(rset.getDate("DTINICIOVIG"));
				tvEXPExamAplica.setICveUsuDictamen(rset
						.getInt("iCveUsuDictamen"));
				tvEXPExamAplica.setCDictaminador(rset
						.getString("cDictaminador"));
				tvEXPExamAplica.setIDictamenes(rset.getInt("Dictamenes"));
				vRegresa.addElement(tvEXPExamAplica);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindProdDictamen2.close", ex2);
			}
		}
		return vRegresa;
	}

	/**
	 * Método FindProdDictamen
	 */
	public Vector FindProdDictamen3(String iCveUniMed, String iCveModulo,
			String iCveProceso, String cDesde, String cHasta)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vRegresa = new Vector();
		TVEXPExamAplica tvEXPExamAplica;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " SELECT E.dtSolicitado, ( "
					+ " SELECT count(EA.lDictaminado) "
					+ " FROM EXPExamAplica EA "
					+ " WHERE EA.icveexpediente > 1  AND "
					+ " EA.iCveUniMed = "
					+ iCveUniMed
					+ " AND "
					+ " EA.iCveModulo = "
					+ iCveModulo
					+ " AND "
					+ " EA.iCveProceso = "
					+ iCveProceso
					+ "  "
					+ " AND EA.dtSolicitado = E.dtSolicitado "
					+ " GROUP BY dtSolicitado ) Generados, "
					+ "(SELECT count(EA.lDictaminado) "
					+ " FROM EXPExamAplica EA "
					+ " WHERE EA.icveexpediente > 1 AND "
					+ " EA.iCveUniMed = "
					+ iCveUniMed
					+ " AND "
					+ " EA.iCveModulo = "
					+ iCveModulo
					+ " AND "
					+ " EA.iCveProceso = "
					+ iCveProceso
					+ "  "
					+ " AND EA.lDictaminado = 1 "
					+ " AND EA.dtSolicitado = E.dtSolicitado "
					+ " GROUP BY lDictaminado) Dictamenes, "
					+ "(SELECT count(EA.lDictaminado) "
					+ " FROM EXPExamAplica EA "
					+ " WHERE EA.icveexpediente > 1 AND "
					+ " EA.iCveUniMed = "
					+ iCveUniMed
					+ " AND "
					+ " EA.iCveModulo = "
					+ iCveModulo
					+ " AND "
					+ " EA.iCveProceso = "
					+ iCveProceso
					+ "  "
					+ " AND EA.lDictaminado = 0 "
					+ " AND EA.dtSolicitado = E.dtSolicitado "
					+ " GROUP BY lDictaminado) No_Aptos_Temporales "
					+ " FROM EXPExamAplica E "
					+ " WHERE E.icveexpediente > 1 AND "
					+ " E.iCveUniMed = "
					+ iCveUniMed
					+ " AND "
					+ " E.iCveModulo = "
					+ iCveModulo
					+ " AND "
					+ " E.iCveProceso = "
					+ iCveProceso
					+ "  "
					+ ((cDesde.trim().length() > 0 && cHasta.trim().length() > 0) ? " AND E.dtSolicitado BETWEEN '"
							+ cDesde + "' AND '" + cHasta + "' "
							: "") +
					// " AND E.dtSolicitado BETWEEN '2005-04-11' AND '2005-04-15' "+
					" GROUP BY E.dtSolicitado ";

			//System.out.println(cSQL);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			Vector vcEXPExamCat = null;
			while (rset.next()) {
				tvEXPExamAplica = new TVEXPExamAplica();
				tvEXPExamAplica.setDtSolicitado(rset.getDate("dtSolicitado"));
				tvEXPExamAplica.setIGenerados(rset.getInt("Generados"));
				tvEXPExamAplica.setIDictaminados(rset.getInt("Dictamenes"));
				tvEXPExamAplica.setINoDictaminados(rset
						.getInt("No_Aptos_Temporales"));
				vRegresa.addElement(tvEXPExamAplica);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindProdDictamen.close", ex2);
			}
		}
		return vRegresa;
	}

	/**
	 * Método que devuelve los registros con los expedientes correspondientes al
	 * usuario solicitado.
	 * 
	 * @param p
	 *            una colecci�n con los parametros
	 * @return un Vector con los registros obtenidos
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	public Vector buscaXLiberarDictamen(HashMap p) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		TVEXPExamAplica datos = new TVEXPExamAplica();
		String cWhere = "";
		String cWhere2 = "";
		// obtener los parametros
		String cUniMed = (String) p.get("iCveUniMed");
		String cCveProceso = (String) p.get("iCveProceso");
		String cCveModulo = (String) p.get("iCveModulo");
		String cCveExpediente = (String) p.get("iCveExpediente");
		java.sql.Date dtFecha = (java.sql.Date) p.get("dtFecha");
		java.sql.Date dtFechaIni = null;
		String cDictaminado = (String) p.get("lDictaminado");

		TParametro vParametros = new TParametro("07");
		int iNumDias = Integer.parseInt(vParametros
				.getPropEspecifica("PlazoTerminarEPI")) * (-1);

		TFechas dtFechaTmp = new TFechas();
		dtFechaIni = dtFechaTmp.aumentaDisminuyeDias(dtFecha, iNumDias);

		String cCvePersonal = (String) p.get("iCvePersonal");
		int iUniMed = 0;
		int iCveMod = 0;
		int iCveExp = 0;
		int iCvePrc = 0;

		// determinar condiciones adicionales de b�squeda (filtros)
		if (cCveExpediente != null && cCveExpediente.length() != 0) {
			cWhere += " and EXPExamAplica.iCveExpediente = " + cCveExpediente;
			cWhere2 += "  iCveExpediente = " + cCveExpediente;
		}
		if (cCveModulo != null && cCveModulo.length() != 0) {
			cWhere += " and EXPExamAplica.iCveModulo = " + cCveModulo;
			// cWhere2 += " and iCveModulo = " + cCveModulo;
		}
		if (cCveProceso != null && cCveProceso.length() != 0) {
			cWhere += " and EXPExamAplica.iCveProceso = " + cCveProceso;
			// cWhere2 += " and iCveProceso = " + cCveProceso;
		}
		if (cUniMed != null && cUniMed.length() != 0) {
			cWhere += " and EXPExamAplica.iCveUniMed = " + cUniMed;
			// cWhere2 += " and iCveUniMed = " + cUniMed;
		}
		if (cDictaminado != null && cDictaminado.trim().length() != 0) {
			cWhere += " AND EXPExamAplica.lDictaminado = " + cDictaminado;
			// cWhere2 += " AND lDictaminado = " + cDictaminado;
		}

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";

			if (cCveProceso != null && cCveProceso.toString().equals("1")) {
				cSQL = "select "
						+ "     PERDatos.iCvePersonal, "
						+ // 1
						"     PERDatos.iCveExpediente, "
						+ // 2
						"     PERDatos.cRFC, "
						+ // 3
						"     EPICisCita.iCveUniMed,"
						+ // 4
						"     EPICisCita.iCveModulo,"
						+ // 5
						"     EPICisCita.dtFecha, "
						+ // 6
						"     PERDatos.cNombre, "
						+ // 3
						"     PERDatos.cApPaterno, "
						+ // 3
						"     PERDatos.cApMaterno "
						+ // 3
						" from EPICisCita , PERDatos , EXPExamAplica "
						+ " where PERDatos.iCvePersonal = EPICisCita.iCvePersonal"
						+ " and EPICisCita.dtFecha BETWEEN '"
						+ dtFechaIni
						+ "' AND '"
						+ dtFecha
						+ "'"
						+ "      AND EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
						+
						// "      AND EXPExamAplica.lConcluido = 0 " +
						"      AND EXPExamAplica.iNumExamen = (Select max(inumexamen) from expexamaplica where "
						+ cWhere2 + ") " + cWhere;
			} else {
				cSQL = "select "
						+ "     PERDatos.iCvePersonal, "
						+ // 1
						"     PERDatos.iCveExpediente, "
						+ // 2
						"     PERDatos.cRFC, "
						+ // 3
						"     EXPExamAplica.iCveUniMed,"
						+ // 4
						"     EXPExamAplica.iCveModulo,"
						+ // 5
						"     EXPExamAplica.dtSolicitado, "
						+ // 6
						"     PERDatos.cNombre, "
						+ // 3
						"     PERDatos.cApPaterno, "
						+ // 3
						"     PERDatos.cApMaterno "
						+ // 3
						" from EXPExamAplica , PERDatos "
						+ " where EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
						+ cWhere;
			}
			 System.out.println("cSQL = "+cSQL);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			iCvePrc = Integer.parseInt(cCveProceso);
			while (rset.next()) {
				iUniMed = rset.getInt(4);
				iCveMod = rset.getInt(5);
				iCveExp = rset.getInt(2);

				datos = this.findExamAplica2(conn, iCveExp, iUniMed, iCvePrc,
						iCveMod);
				datos.setCRFC(rset.getString("cRFC")); // el dato faltante
				datos.setCNombre(rset.getString("cNombre")); // el dato faltante
				datos.setCApPaterno(rset.getString("cApPaterno")); // el dato
																	// faltante
				datos.setCApMaterno(rset.getString("cApMaterno")); // el dato
																	// faltante
				vcEXPExamAplica.addElement(datos);
				log("-registro obtenido de ExamAplica: "
						+ datos.toHashMap().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("buscaXLiberarServicio", ex);
			throw new DAOException("buscaXLiberarServicio");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("buscaXLiberarServicio.close", ex2);
			}
		}
		return vcEXPExamAplica;
	}

	/**
	 * Método que devuelve los registros con los expedientes correspondientes al
	 * usuario solicitado.
	 * 
	 * @param p
	 *            una colecci�n con los parametros
	 * @return un Vector con los registros obtenidos
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	public Vector buscaXLiberarDictamen2(HashMap p) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		TVEXPExamAplica datos = new TVEXPExamAplica();
		String cWhere = "";
		String cWhere2 = "";
		// obtener los parametros
		String cUniMed = (String) p.get("iCveUniMed");
		String cCveProceso = (String) p.get("iCveProceso");
		String cCveModulo = (String) p.get("iCveModulo");
		String cCveExpediente = (String) p.get("iCveExpediente");
		java.sql.Date dtFecha = (java.sql.Date) p.get("dtFecha");
		java.sql.Date dtFechaIni = null;
		String cDictaminado = (String) p.get("lDictaminado");

		TParametro vParametros = new TParametro("07");
		int iNumDias = Integer.parseInt(vParametros
				.getPropEspecifica("PlazoTerminarEPI")) * (-1);

		TFechas dtFechaTmp = new TFechas();
		dtFechaIni = dtFechaTmp.aumentaDisminuyeDias(dtFecha, iNumDias);

		String cCvePersonal = (String) p.get("iCvePersonal");
		int iUniMed = 0;
		int iCveMod = 0;
		int iCveExp = 0;
		int iCvePrc = 0;

		// determinar condiciones adicionales de b�squeda (filtros)
		if (cCveExpediente != null && cCveExpediente.length() != 0) {
			cWhere += " and EXPExamAplica.iCveExpediente = " + cCveExpediente;
			cWhere2 += "  iCveExpediente = " + cCveExpediente;
		}
		if (cCveModulo != null && cCveModulo.length() != 0) {
			cWhere += " and EXPExamAplica.iCveModulo = " + cCveModulo;
			// cWhere2 += " and iCveModulo = " + cCveModulo;
		}
		if (cCveProceso != null && cCveProceso.length() != 0) {
			cWhere += " and EXPExamAplica.iCveProceso = " + cCveProceso;
			// cWhere2 += " and iCveProceso = " + cCveProceso;
		}
		if (cUniMed != null && cUniMed.length() != 0) {
			cWhere += " and EXPExamAplica.iCveUniMed = " + cUniMed;
			// cWhere2 += " and iCveUniMed = " + cUniMed;
		}
		if (cDictaminado != null && cDictaminado.trim().length() != 0) {
			cWhere += " AND EXPExamAplica.lDictaminado = " + cDictaminado;
			// cWhere2 += " AND lDictaminado = " + cDictaminado;
		}

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";

			if (cCveProceso != null && cCveProceso.toString().equals("1")) {
				cSQL = "select "
						+ "     PERDatos.iCvePersonal, "
						+ // 1
						"     PERDatos.iCveExpediente, "
						+ // 2
						"     PERDatos.cRFC, "
						+ // 3
						"     EXPExamAplica.iCveUniMed,"
						+ // 4
						"     EXPExamAplica.iCveModulo,"
						+ // 5
						"     EXPExamAplica.dtSolicitado, "
						+ // 6
						"     PERDatos.cNombre, "
						+ // 3
						"     PERDatos.cApPaterno, "
						+ // 3
						"     PERDatos.cApMaterno "
						+ // 3
						" from PERDatos , EXPExamAplica "
						+ " where PERDatos.iCvePersonal = EPICisCita.iCvePersonal"
						+ "      AND EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
						+
						// "      AND EXPExamAplica.lConcluido = 0 " +
						"      AND EXPExamAplica.iNumExamen = (Select max(inumexamen) from expexamaplica where "
						+ cWhere2 + ") " + cWhere;
			} else {
				cSQL = "select "
						+ "     PERDatos.iCvePersonal, "
						+ // 1
						"     PERDatos.iCveExpediente, "
						+ // 2
						"     PERDatos.cRFC, "
						+ // 3
						"     EXPExamAplica.iCveUniMed,"
						+ // 4
						"     EXPExamAplica.iCveModulo,"
						+ // 5
						"     EXPExamAplica.dtSolicitado, "
						+ // 6
						"     PERDatos.cNombre, "
						+ // 3
						"     PERDatos.cApPaterno, "
						+ // 3
						"     PERDatos.cApMaterno "
						+ // 3
						" from EXPExamAplica , PERDatos "
						+ " where EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
						+ cWhere;
			}
			// System.out.println("cSQL = "+cSQL);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			iCvePrc = Integer.parseInt(cCveProceso);
			while (rset.next()) {
				iUniMed = rset.getInt(4);
				iCveMod = rset.getInt(5);
				iCveExp = rset.getInt(2);

				datos = this.findExamAplica2(conn, iCveExp, iUniMed, iCvePrc,
						iCveMod);
				datos.setCRFC(rset.getString("cRFC")); // el dato faltante
				datos.setCNombre(rset.getString("cNombre")); // el dato faltante
				datos.setCApPaterno(rset.getString("cApPaterno")); // el dato
																	// faltante
				datos.setCApMaterno(rset.getString("cApMaterno")); // el dato
																	// faltante
				vcEXPExamAplica.addElement(datos);
				log("-registro obtenido de ExamAplica: "
						+ datos.toHashMap().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("buscaXLiberarServicio", ex);
			throw new DAOException("buscaXLiberarServicio");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("buscaXLiberarServicio.close", ex2);
			}
		}
		return vcEXPExamAplica;
	}

	/**
	 * Método que devuelve los registros con los expedientes correspondientes al
	 * usuario solicitado.
	 * 
	 * @param p
	 *            una colecci�n con los parametros
	 * @return un Vector con los registros obtenidos
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	public Vector buscaXLiberarServicio(HashMap p) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		TVEXPExamAplica datos = new TVEXPExamAplica();
		String cWhere = "";
		String cWhere2 = "";
		// obtener los parametros
		String cUniMed = (String) p.get("iCveUniMed");
		String cCveProceso = (String) p.get("iCveProceso");
		String cCveModulo = (String) p.get("iCveModulo");
		String cCveExpediente = (String) p.get("iCveExpediente");
		java.sql.Date dtFecha = (java.sql.Date) p.get("dtFecha");
		java.sql.Date dtFechaIni = null;
		String cDictaminado = (String) p.get("lDictaminado");
		String cServicio = (String) p.get("iCveServicio");

		if (cServicio != null && cServicio.toString().equals("1")) {
			cCveProceso = "2";
		}

		TParametro vParametros = new TParametro("07");
		int iNumDias = Integer.parseInt(vParametros
				.getPropEspecifica("PlazoTerminarEPI")) * (-1);

		TFechas dtFechaTmp = new TFechas();
		dtFechaIni = dtFechaTmp.aumentaDisminuyeDias(dtFecha, iNumDias);

		String cCvePersonal = (String) p.get("iCvePersonal");
		int iUniMed = 0;
		int iCveMod = 0;
		int iCveExp = 0;
		int iCvePrc = 0;

		// determinar condiciones adicionales de b�squeda (filtros)
		if (cCveExpediente != null && cCveExpediente.length() != 0) {
			cWhere += " and EXPExamAplica.iCveExpediente = " + cCveExpediente;
			cWhere2 += " iCveExpediente = " + cCveExpediente;
		}
		if (cCveModulo != null && cCveModulo.length() != 0) {
			cWhere += " and EXPExamAplica.iCveModulo = " + cCveModulo;
			// cWhere2 += " and iCveModulo = " + cCveModulo;
		}
		if (cCveProceso != null && cCveProceso.length() != 0) {
			cWhere += " and EXPExamAplica.iCveProceso = " + cCveProceso;
			// cWhere2 += " and iCveProceso = " + cCveProceso;
		}
		if (cUniMed != null && cUniMed.length() != 0) {
			cWhere += " and EXPExamAplica.iCveUniMed = " + cUniMed;
			// cWhere2 += " and iCveUniMed = " + cUniMed;
		}
		if (cDictaminado != null && cDictaminado.trim().length() != 0) {
			cWhere += " AND EXPExamAplica.lDictaminado = " + cDictaminado;
			// cWhere2 += " AND lDictaminado = " + cDictaminado;
		}

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";

			if (cServicio != null && cServicio.toString().equals("1")) {
				cSQL = "select "
						+ "     PERDatos.iCvePersonal, "
						+ // 1
						"     PERDatos.iCveExpediente, "
						+ // 2
						"     PERDatos.cRFC, "
						+ // 3
						"     EXPExamAplica.iCveUniMed,"
						+ // 4
						"     EXPExamAplica.iCveModulo,"
						+ // 5
						"     EXPExamAplica.dtSolicitado, "
						+ // 6
						"     PERDatos.cNombre, "
						+ // 3
						"     PERDatos.cApPaterno, "
						+ // 3
						"     PERDatos.cApMaterno "
						+ // 3
						" from EXPExamAplica , PERDatos "
						+ " where EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
						+ cWhere;
				// System.out.println("1 = "+cSQL);
			} else {
				cSQL = "select "
						+ "     PERDatos.iCvePersonal, "
						+ // 1
						"     PERDatos.iCveExpediente, "
						+ // 2
						"     PERDatos.cRFC, "
						+ // 3
						"     EPICisCita.iCveUniMed,"
						+ // 4
						"     EPICisCita.iCveModulo,"
						+ // 5
						"     EPICisCita.dtFecha, "
						+ // 6
						"     PERDatos.cNombre, "
						+ // 3
						"     PERDatos.cApPaterno, "
						+ // 3
						"     PERDatos.cApMaterno "
						+ // 3
						" from EPICisCita , PERDatos , EXPExamAplica "
						+ " where PERDatos.iCvePersonal = EPICisCita.iCvePersonal"
						+ " and EPICisCita.dtFecha BETWEEN '"
						+ dtFechaIni
						+ "' AND '"
						+ dtFecha
						+ "'"
						+ "      AND EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
						+
						// "      AND EXPExamAplica.lConcluido = 0 " +
						"      AND EXPExamAplica.iNumExamen = (Select Max(inumexamen) from ExpExamaplica where "
						+ cWhere2 + ") " + cWhere;
				// System.out.println("2 = "+cSQL);
			}
			/*
			 * cSQL = "select " + "     PERDatos.iCvePersonal, " + // 1
			 * "     PERDatos.iCveExpediente, " + // 2 "     PERDatos.cRFC, " +
			 * // 3 "     EPICisCita.iCveUniMed," + // 4
			 * "     EPICisCita.iCveModulo," + // 5 "     EPICisCita.dtFecha, "
			 * + // 6 "     PERDatos.cNombre, " + // 3
			 * "     PERDatos.cApPaterno, " + // 3 "     PERDatos.cApMaterno " +
			 * // 3 " from EPICisCita , PERDatos , EXPExamAplica " +
			 * " where PERDatos.iCvePersonal = EPICisCita.iCvePersonal" +
			 * " and EPICisCita.dtFecha BETWEEN '" + dtFechaIni + "' AND '" +
			 * dtFecha + "'" +
			 * "      AND EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
			 * + "      AND EXPExamAplica.lConcluido = 0 " + cWhere;
			 */

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			iCvePrc = Integer.parseInt(cCveProceso);
			while (rset.next()) {
				iUniMed = rset.getInt(4);
				iCveMod = rset.getInt(5);
				iCveExp = rset.getInt(2);

				datos = this.findExamAplica(conn, iCveExp, iUniMed, iCvePrc,
						iCveMod);
				datos.setCRFC(rset.getString("cRFC")); // el dato faltante
				datos.setCNombre(rset.getString("cNombre")); // el dato faltante
				datos.setCApPaterno(rset.getString("cApPaterno")); // el dato
																	// faltante
				datos.setCApMaterno(rset.getString("cApMaterno")); // el dato
																	// faltante
				vcEXPExamAplica.addElement(datos);
				log("-registro obtenido de ExamAplica: "
						+ datos.toHashMap().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("buscaXLiberarServicio", ex);
			throw new DAOException("buscaXLiberarServicio");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("buscaXLiberarServicio.close", ex2);
			}
		}
		return vcEXPExamAplica;
	}

	/**
	 * Método que devuelve los registros con los expedientes correspondientes al
	 * usuario solicitado.
	 * 
	 * @param p
	 *            una colecci�n con los parametros
	 * @return un Vector con los registros obtenidos
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	public Vector buscaXLiberarServicio2(HashMap p) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		TVEXPExamAplica datos = new TVEXPExamAplica();
		String cWhere = "";
		String cWhere2 = "";
		// obtener los parametros
		String cUniMed = (String) p.get("iCveUniMed");
		String cCveProceso = (String) p.get("iCveProceso");
		String cCveModulo = (String) p.get("iCveModulo");
		String cCveExpediente = (String) p.get("iCveExpediente");
		java.sql.Date dtFecha = (java.sql.Date) p.get("dtFecha");
		java.sql.Date dtFechaIni = null;
		String cDictaminado = (String) p.get("lDictaminado");
		String cServicio = (String) p.get("iCveServicio");

		if (cServicio != null && cServicio.toString().equals("1")) {
			cCveProceso = "2";
		}

		TParametro vParametros = new TParametro("07");
		int iNumDias = Integer.parseInt(vParametros
				.getPropEspecifica("PlazoTerminarEPI")) * (-1);

		TFechas dtFechaTmp = new TFechas();
		dtFechaIni = dtFechaTmp.aumentaDisminuyeDias(dtFecha, iNumDias);

		String cCvePersonal = (String) p.get("iCvePersonal");
		int iUniMed = 0;
		int iCveMod = 0;
		int iCveExp = 0;
		int iCvePrc = 0;

		// determinar condiciones adicionales de b�squeda (filtros)
		if (cCveExpediente != null && cCveExpediente.length() != 0) {
			cWhere += " and EXPExamAplica.iCveExpediente = " + cCveExpediente;
			cWhere2 += " iCveExpediente = " + cCveExpediente;
		}
		if (cCveModulo != null && cCveModulo.length() != 0) {
			cWhere += " and EXPExamAplica.iCveModulo = " + cCveModulo;
			// cWhere2 += " and iCveModulo = " + cCveModulo;
		}
		if (cCveProceso != null && cCveProceso.length() != 0) {
			cWhere += " and EXPExamAplica.iCveProceso = " + cCveProceso;
			// cWhere2 += " and iCveProceso = " + cCveProceso;
		}
		if (cUniMed != null && cUniMed.length() != 0) {
			cWhere += " and EXPExamAplica.iCveUniMed = " + cUniMed;
			// cWhere2 += " and iCveUniMed = " + cUniMed;
		}
		if (cDictaminado != null && cDictaminado.trim().length() != 0) {
			cWhere += " AND EXPExamAplica.lDictaminado = " + cDictaminado;
			// cWhere2 += " AND lDictaminado = " + cDictaminado;
		}

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";

			if (cServicio != null && cServicio.toString().equals("1")) {
				cSQL = "select "
						+ "     PERDatos.iCvePersonal, "
						+ // 1
						"     PERDatos.iCveExpediente, "
						+ // 2
						"     PERDatos.cRFC, "
						+ // 3
						"     EXPExamAplica.iCveUniMed,"
						+ // 4
						"     EXPExamAplica.iCveModulo,"
						+ // 5
						"     EXPExamAplica.dtSolicitado, "
						+ // 6
						"     PERDatos.cNombre, "
						+ // 3
						"     PERDatos.cApPaterno, "
						+ // 3
						"     PERDatos.cApMaterno "
						+ // 3
						" from EXPExamAplica , PERDatos "
						+ " where EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
						+ cWhere;
				// System.out.println("1 = "+cSQL);
			} else {
				cSQL = "select "
						+ "     PERDatos.iCvePersonal, "
						+ // 1
						"     PERDatos.iCveExpediente, "
						+ // 2
						"     PERDatos.cRFC, "
						+ // 3
						"     EXPExamAplica.iCveUniMed,"
						+ // 4
						"     EXPExamAplica.iCveModulo,"
						+ // 5
						"     EXPExamAplica.dtSolicitado, "
						+ // 6
						"     PERDatos.cNombre, "
						+ // 3
						"     PERDatos.cApPaterno, "
						+ // 3
						"     PERDatos.cApMaterno "
						+ // 3
						" from  PERDatos , EXPExamAplica "
						+ " where PERDatos.iCvePersonal = EXPExamAplica.iCvePersonal"
						+ "      AND EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
						+
						// "      AND EXPExamAplica.lConcluido = 0 " +
						"      AND EXPExamAplica.iNumExamen = (Select Max(inumexamen) from ExpExamaplica where "
						+ cWhere2 + ") " + cWhere;
				// System.out.println("2 = "+cSQL);
			}
			/*
			 * cSQL = "select " + "     PERDatos.iCvePersonal, " + // 1
			 * "     PERDatos.iCveExpediente, " + // 2 "     PERDatos.cRFC, " +
			 * // 3 "     EPICisCita.iCveUniMed," + // 4
			 * "     EPICisCita.iCveModulo," + // 5 "     EPICisCita.dtFecha, "
			 * + // 6 "     PERDatos.cNombre, " + // 3
			 * "     PERDatos.cApPaterno, " + // 3 "     PERDatos.cApMaterno " +
			 * // 3 " from EPICisCita , PERDatos , EXPExamAplica " +
			 * " where PERDatos.iCvePersonal = EPICisCita.iCvePersonal" +
			 * " and EPICisCita.dtFecha BETWEEN '" + dtFechaIni + "' AND '" +
			 * dtFecha + "'" +
			 * "      AND EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
			 * + "      AND EXPExamAplica.lConcluido = 0 " + cWhere;
			 */

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			iCvePrc = Integer.parseInt(cCveProceso);
			while (rset.next()) {
				iUniMed = rset.getInt(4);
				iCveMod = rset.getInt(5);
				iCveExp = rset.getInt(2);

				datos = this.findExamAplica(conn, iCveExp, iUniMed, iCvePrc,
						iCveMod);
				datos.setCRFC(rset.getString("cRFC")); // el dato faltante
				datos.setCNombre(rset.getString("cNombre")); // el dato faltante
				datos.setCApPaterno(rset.getString("cApPaterno")); // el dato
																	// faltante
				datos.setCApMaterno(rset.getString("cApMaterno")); // el dato
																	// faltante
				vcEXPExamAplica.addElement(datos);
				log("-registro obtenido de ExamAplica: "
						+ datos.toHashMap().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("buscaXLiberarServicio", ex);
			throw new DAOException("buscaXLiberarServicio");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("buscaXLiberarServicio.close", ex2);
			}
		}
		return vcEXPExamAplica;
	}

	/**
	 * Método FindByExamenPagado
	 * 
	 * @param cExpediente
	 * @param iProceso
	 * @return iNumExamen Indicador si tiene pago
	 * @throws DAOException
	 */

	public int FindByExamenPagado(int iExpediente, int iProceso,
			java.sql.Date dtSolicitado) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		java.sql.Date vdtFechaLimite;
		TFechas vFechas = new TFechas();
		int iDias = -30;
		int count = 0;
		vdtFechaLimite = vFechas.aumentaDisminuyeDias(dtSolicitado, iDias);

		Vector vcEXPExamCat = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "select " + " iCveExpediente," + " iNumExamen"
					+ " from EXPExamAplica " + " where iCveExpediente = ? "
					+ " and iCveProceso = ? " + " and dtSolicitado >= ? "
					+ " order by iNumExamen DESC";

			pstmt = conn.prepareStatement(cSQL);
			// System.out.println("ExamenPagado:"+cSQL+" "+iExpediente+
			// " "+iProceso+ " "+ vdtFechaLimite);
			pstmt.setInt(1, iExpediente);
			pstmt.setInt(2, iProceso);
			pstmt.setDate(3, vdtFechaLimite);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				count = 1;
				break;
			}
		} catch (Exception ex) {
			warn("FindByExamenPagado", ex);
			throw new DAOException("FindByExamenPagado");
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
				warn("FindByExamenPagado.close", ex2);
			}
			return count;
		}
	}

	/**
	 * Método noConcluido
	 */
	public boolean noDictaminado(int iCveExpediente, int iCveProceso)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean bRet = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			pstmt = conn
					.prepareStatement("select lDictaminado from EXPExamAplica where iCveExpediente=? and iCveProceso=? and lDictaminado = 0");
			pstmt.setInt(1, iCveExpediente);
			pstmt.setInt(2, iCveProceso);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if (rset.getInt("lDictaminado") == 0) {
					bRet = true;
				}
			}
		} catch (Exception ex) {
			warn("Dictaminado", ex);
			throw new DAOException("Dictaminado");
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
				warn("Dictaminado.close", ex2);
			}
			return bRet;
		}
	}

	public TVEXPExamAplica CategAdicional(int iCveExpediente, int iCveProceso)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVEXPExamAplica vExamAplica = new TVEXPExamAplica();
		boolean bRet = false;
		StringBuffer cSQL = new StringBuffer();
		TFechas Fecha = new TFechas();
		java.util.Date dtLimite = new java.util.Date();
		java.util.Date dtSolicitada = new java.util.Date();
		try {
			cSQL.append(
					" select EA.iCveExpediente, EA.iNumExamen, EA.dtSolicitado, EA.lDictaminado ")
					.append("  from EXPExamAplica EA ")
					.append("  where EA.iCveExpediente = ")
					.append(iCveExpediente)
					.append("    and EA.iNumExamen     = (select MAX(E.iNumExamen) ")
					.append("                              from EXPExamAplica E ")
					.append("                              where E.iCveExpediente = ")
					.append(iCveExpediente)
					.append("                                and E.iCveProceso    = ")
					.append(iCveProceso)
					.append("                              )");

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vExamAplica.setLDictaminado(rset.getInt("lDictaminado"));
				vExamAplica.setICveExpediente(rset.getInt("iCveExpediente"));
				vExamAplica.setDtSolicitado(rset.getDate("dtSolicitado"));
				if (vExamAplica.getLDictaminado() == 0)
					vExamAplica.setINumExamen(rset.getInt("iNumExamen"));
				else {
					dtLimite = Fecha.aumentaDisminuyeDias(rset
							.getDate("dtSolicitado"), Integer.parseInt(
							this.VParametros.getPropEspecifica("EPIDiasCatAd")
									.toString(), 10));
					if (dtLimite.compareTo(Fecha.TodaySQL()) >= 0)
						vExamAplica.setINumExamen(rset.getInt("iNumExamen"));
					else
						vExamAplica.setINumExamen(0);
				}
			}
		} catch (Exception ex) {
			warn("Dictaminado", ex);
			throw new DAOException("Dictaminado");
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
				warn("Dictaminado.close", ex2);
			}
			return vExamAplica;
		}
	}

	/**
	 * Actualiza la informaci�n del examen aplicado cuando es iniciado en el
	 * archivo.
	 * 
	 * @param conGral
	 *            Connection Conecci�n a la base de datos. Puede ser null.
	 * @param obj
	 *            Object Objeto con la informaci�n del Examen aplicado.
	 * @throws DAOException
	 * @return Object Objeto actualizado.
	 */
	public Object updateArchivo(Connection conGral, Object obj)
			throws DAOException {
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
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamAplica" + " Set lIniciado    = ?,"
					+ "     iFolioEs     = ?," + "     dtAplicacion = ? "
					+ " Where iCveExpediente = ? "
					+ "   and iNumExamen     = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamAplica.getLIniciado());
			pstmt.setInt(2, vEXPExamAplica.getIFolioEs());
			pstmt.setDate(3, vEXPExamAplica.getDtAplicacion());
			pstmt.setInt(4, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(5, vEXPExamAplica.getINumExamen());
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
			warn("updateArchivo", ex);
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
				warn("updateArchivo.close", ex2);
			}
			return cClave;
		}
	}
	

	/**
	 * Actualiza la informaci�n del examen aplicado cuando es iniciado en el
	 * archivo.
	 * 
	 * @param conGral
	 *            Connection Conecci�n a la base de datos. Puede ser null.
	 * @param obj
	 *            Object Objeto con la informaci�n del Examen aplicado.
	 * @throws DAOException
	 * @return Object Objeto actualizado.
	 */
	public Object updateArchivoDos(Connection conGral, Object obj)
			throws DAOException {
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
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamAplica" + " Set lIniciado    = ?,"
					+ "     iFolioEs     = ?," + "     dtAplicacion = current date "
					+ " Where iCveExpediente = ? "
					+ "   and iNumExamen     = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 1);
			//pstmt.setDate(3, vEXPExamAplica.getDtAplicacion());
			pstmt.setInt(3, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(4, vEXPExamAplica.getINumExamen());
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
			warn("updateArchivo", ex);
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
				warn("updateArchivo.close", ex2);
			}
			return cClave;
		}
	}

	
	/**
	 * Actualiza la informaci�n del examen aplicado cuando es iniciado en el
	 * archivo.
	 * 
	 * @param conGral
	 *            Connection Conecci�n a la base de datos. Puede ser null.
	 * @param obj
	 *            Object Objeto con la informaci�n del Examen aplicado.
	 * @throws DAOException
	 * @return Object Objeto actualizado.
	 */
	public Object updateLiberaVale(Connection conGral, Object obj)
			throws DAOException {
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
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamAplica" + " Set lIniciado    = ?,"
					+ "     iFolioEs     = ? "
					+ " Where iCveExpediente = ? "
					+ "   and iNumExamen     = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamAplica.getLIniciado());
			pstmt.setInt(2, vEXPExamAplica.getIFolioEs());
			pstmt.setInt(3, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(4, vEXPExamAplica.getINumExamen());
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
			warn("updateArchivo", ex);
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
				warn("updateArchivo.close", ex2);
			}
			return cClave;
		}
	}
	
	
	/**
	 * Actualiza la informaci�n del examen aplicado cuando es iniciado en el
	 * archivo.
	 * 
	 * @param conGral
	 *            Connection Conecci�n a la base de datos. Puede ser null.
	 * @param obj
	 *            Object Objeto con la informaci�n del Examen aplicado.
	 * @throws DAOException
	 * @return Object Objeto actualizado.
	 */
	public Object updateValeServicio(Connection conGral, Object obj)
			throws DAOException {
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
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			
			String cSQLVale = "SELECT COUNT(ICVEEXPEDIENTE) "
					+ "FROM EXPEXAMAPLICA "
					+ "WHERE ICVEEXPEDIENTE = "+vEXPExamAplica.getICveExpediente()+" AND "
					+ "INUMEXAMEN = "+vEXPExamAplica.getINumExamen()+" AND "
					+ "TIVALESERVICIO IS NULL";
			int regresa = this.RegresaInt(cSQLVale);
			//System.out.println("regresa = "+regresa);
			//System.out.println("cSQLVale = "+cSQLVale);
			if(regresa == 1)
			{
				//System.out.println("cSQLVale = "+cSQLVale);
				String cSQL = "";
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
				cSQL = "update EXPExamAplica" + " Set TIVALESERVICIO = (CURRENT TIMESTAMP)  "
						+ " Where iCveExpediente = ? "
						+ "   and iNumExamen     = ?";
				//System.out.println("cSQL = "+cSQL);
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt.setInt(2, vEXPExamAplica.getINumExamen());
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
				warn("update", ex1);
			}
			warn("updateArchivo", ex);
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
				warn("updateArchivo.close", ex2);
			}
			return cClave;
		}
	}
	
	
	public Vector buscarExamenNoIniciado(HashMap p) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		TVEXPExamAplica datos = new TVEXPExamAplica();
		String cWhere = "";
		// obtener los parametros
		String cUniMed = (String) p.get("iCveUniMed");
		String cCveProceso = (String) p.get("iCveProceso");
		String cCveModulo = (String) p.get("iCveModulo");
		String cCveExpediente = (String) p.get("iCveExpediente");
		java.sql.Date dtFecha = (java.sql.Date) p.get("dtFecha");
		java.sql.Date dtFechaIni = null;
		String cDictaminado = (String) p.get("lDictaminado");
		String cIniciado = (String) p.get("llInciado");

		TParametro vParametros = new TParametro("07");
		int iNumDias = Integer.parseInt(vParametros
				.getPropEspecifica("PlazoTerminarEPI")) * (-1);

		TFechas dtFechaTmp = new TFechas();
		dtFechaIni = dtFechaTmp.aumentaDisminuyeDias(dtFecha, iNumDias);

		String cCvePersonal = (String) p.get("iCvePersonal");
		int iUniMed = 0;
		int iCveMod = 0;
		int iCveExp = 0;
		int iCvePrc = 0;

		// determinar condiciones adicionales de b�squeda (filtros)
		if (cCveExpediente != null && cCveExpediente.length() != 0) {
			cWhere += " and EXPExamAplica.iCveExpediente = " + cCveExpediente;
		}
		if (cCveModulo != null && cCveModulo.length() != 0) {
			cWhere += " and EXPExamAplica.iCveModulo = " + cCveModulo;
		}
		if (cCveProceso != null && cCveProceso.length() != 0) {
			cWhere += " and EXPExamAplica.iCveProceso = " + cCveProceso;
		}
		if (cUniMed != null && cUniMed.length() != 0) {
			cWhere += " and EXPExamAplica.iCveUniMed = " + cUniMed;
		}
		if (cDictaminado != null && cDictaminado.trim().length() != 0) {
			cWhere += " AND EXPExamAplica.lDictaminado = " + cDictaminado;
		}
		if (cIniciado != null && cIniciado.trim().length() != 0) {
			cWhere += " AND EXPExamAplica.lIniciado = " + cIniciado;
		}

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";

			cSQL = "select "
					+ "     PERDatos.iCvePersonal, "
					+ // 1
					"     PERDatos.iCveExpediente, "
					+ // 2
					"     PERDatos.cRFC, "
					+ // 3
					"     EPICisCita.iCveUniMed,"
					+ // 4
					"     EPICisCita.iCveModulo,"
					+ // 5
					"     EPICisCita.dtFecha, "
					+ // 6
					"     PERDatos.cNombre, "
					+ // 3
					"     PERDatos.cApPaterno, "
					+ // 3
					"     PERDatos.cApMaterno "
					+ // 3
					" from EPICisCita , PERDatos , EXPExamAplica "
					+ " where PERDatos.iCvePersonal = EPICisCita.iCvePersonal"
					+ " and EPICisCita.dtFecha BETWEEN '"
					+ dtFechaIni
					+ "' AND '"
					+ dtFecha
					+ "'"
					+ "      AND EXPExamAplica.iCveExpediente=PERDatos.iCveExpediente "
					+ "      AND EXPExamAplica.lConcluido = 0 " + cWhere;
			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			iCvePrc = Integer.parseInt(cCveProceso);

			while (rset.next()) {
				iUniMed = rset.getInt(4);
				iCveMod = rset.getInt(5);
				iCveExp = rset.getInt(2);

				datos = this.findExamAplica(conn, iCveExp, iUniMed, iCvePrc,
						iCveMod);
				datos.setCRFC(rset.getString("cRFC")); // el dato faltante
				datos.setCNombre(rset.getString("cNombre")); // el dato faltante
				datos.setCApPaterno(rset.getString("cApPaterno")); // el dato
																	// faltante
				datos.setCApMaterno(rset.getString("cApMaterno")); // el dato
																	// faltante
				vcEXPExamAplica.addElement(datos);
				log("-registro obtenido de ExamAplica: "
						+ datos.toHashMap().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("buscaXLiberarServicio", ex);
			throw new DAOException("buscaXLiberarServicio");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("buscaXLiberarServicio.close", ex2);
			}
		}
		return vcEXPExamAplica;
	}

	/**
	 * 
	 * @param iUniMed
	 *            int
	 * @param iCvePrc
	 *            int
	 * @param iCveMod
	 *            int
	 * @throws DAOException
	 * @return TVEXPExamAplica
	 * @author Itzia Mar�a del Carmen S�nchez M�ndez
	 * @version 1.0
	 */
	public TVDinRep02 findResultExamA(int iCveExpediente, int iNumExamen,
			int iCveServicio) throws DAOException {
		DbConnection dbConn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		StringBuffer cSQL = new StringBuffer();
		TVDinRep02 vExpServcio = new TVDinRep02();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			cSQL.append(" select EA.iCveExpediente, EA.iNumExamen, ")
					.append("        EA.dtSolicitado, EA.dtAplicacion, ")
					.append("        U.cDscUniMed, M.cDscModulo, P.cDscProceso, ")
					.append("        D.cRFC, D.cSexo, D.cApPaterno, D.cApMaterno, D.cNombre,")
					.append("        D.dtNacimiento,")
					.append("        ES.iCveServicio, ")
					.append("        MS.cDscServicio, MS.lReqDiag, MS.lVariosMeds,")
					.append("        ES.lConcluido, ES.dtInicio, ES.dtFin, ES.iCveUsuNotaMed, ")
					.append("        ES.cNotaMedica, ")
					.append("        U.cApPaterno || ' ' || U.cApMaterno || ' ' || U.cNombre as cMedico, ")
					.append("        IC.cSiglasProf, IC.cCedula ")
					.append(" from EXPExamAplica EA ")
					.append(" inner join GRLUniMed U on U.iCveUniMed = EA.iCveUniMed ")
					.append(" inner join GRLModulo M on M.iCveUniMed = EA.iCveUniMed ")
					.append("                       and M.iCveModulo = EA.iCveModulo ")
					.append(" inner join GRLProceso P on P.iCveProceso = EA.iCveProceso ")
					.append(" inner join PERDatos  D on D.iCvePersonal = EA.iCveExpediente ")
					.append(" inner join EXPServicio ES on ES.iCveExpediente = EA.iCveExpediente ")
					.append("                          and ES.iNumExamen     = EA.iNumExamen ")
					.append(" inner join MEDServicio MS on MS.iCveServicio = ES.iCveServicio ")
					.append(" left join SEGUsuario U on U.iCveUsuario = ES.iCveUsuNotaMed ")
					.append(" left join GRLUsuario IC on IC.iCveUsuario = U.iCveUsuario ")
					.append(" where EA.iCveExpediente = ")
					.append(iCveExpediente)
					.append("   and EA.iNumExamen     = ").append(iNumExamen)
					.append("   and ES.iCveServicio   = ").append(iCveServicio);

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vExpServcio
						.put("iCveExpediente", rset.getInt("iCveExpediente"));
				vExpServcio.put("iNumExamen", rset.getInt("iNumExamen"));
				vExpServcio.put("dtSolicitado", rset.getDate("dtSolicitado"));
				vExpServcio.put("dtAplicacion", rset.getDate("dtAplicacion"));
				vExpServcio.put("cDscUniMed", rset.getString("cDscUniMed"));
				vExpServcio.put("cDscModulo", rset.getString("cDscModulo"));
				vExpServcio.put("cDscProceso", rset.getString("cDscProceso"));
				vExpServcio.put("cRFC", rset.getString("cRFC"));
				vExpServcio.put("cSexo", rset.getString("cSexo"));
				vExpServcio.put("cApPaterno", rset.getString("cApPaterno"));
				vExpServcio.put("cApMaterno", rset.getString("cApMaterno"));
				vExpServcio.put("cNombre", rset.getString("cNombre"));
				vExpServcio.put("dtNacimiento", rset.getDate("dtNacimiento"));
				vExpServcio.put("iCveServicio", rset.getInt("iCveServicio"));
				vExpServcio.put("cDscServicio", rset.getString("cDscServicio"));
				vExpServcio.put("lReqDiag", rset.getInt("lReqDiag"));
				vExpServcio.put("lVariosMeds", rset.getInt("lVariosMeds"));
				vExpServcio.put("lConcluido", rset.getInt("lConcluido"));
				vExpServcio.put("dtInicio", rset.getDate("dtInicio"));
				vExpServcio.put("dtFin", rset.getDate("dtFin"));
				vExpServcio
						.put("iCveUsuNotaMed", rset.getInt("iCveUsuNotaMed"));
				vExpServcio.put("cNotaMedica", rset.getString("cNotaMedica"));
				vExpServcio.put("cMedico", rset.getString("cMedico"));
				vExpServcio.put(
						"cSiglasProf",
						rset.getString("cSiglasProf") != null ? rset
								.getString("cSiglasProf") : "");
				vExpServcio.put("cCedula",
						rset.getString("cCedula") != null ? "(Ced. Prof."
								+ rset.getString("cCedula") + ")" : "");
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
		return vExpServcio;
	}

	/*
	 * ACTUALIZA EL LA FECHA DE FIN DEL EXAMEN AG SA 27 MAYO 2010
	 */
	public String tfinexaupdate(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";

		// Calculando Timestamp para el campo TFINEXA
		// AG SA 27 mayo 2010

		java.util.Date utilDate = new java.util.Date(); // fecha actual
		long lnMilisegundos = utilDate.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		// System.out.println("sql.Timestamp: "+sqlTimestamp);

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			// TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			TVEXPDictamenServ vEXPDictamenServ = (TVEXPDictamenServ) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamAplica" + " Set TFinExa = ?    "
					+ "where icveexpediente = ? " + " and inumexamen = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setTimestamp(1, sqlTimestamp);
			pstmt.setInt(2, vEXPDictamenServ.getICveExpediente());
			pstmt.setInt(3, vEXPDictamenServ.getINumExamen());
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
	 * Método Find By All c/Where
	 */
	public TVEXPExamAplica FindByAll(int iCvePersonal) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVEXPExamAplica vEXPExamAplica = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVEXPExamAplica vEXPExamAplica;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "EXPExamAplica.iCveExpediente,"
					+ "EXPExamAplica.iNumExamen,"
					+ "EXPExamAplica.iCveUniMed,"
					+ "EXPExamAplica.iCveProceso,"
					+ "EXPExamAplica.iCvePersonal,"
					+ "EXPExamAplica.iCveModulo,"
					+ "EXPExamAplica.dtSolicitado,"
					+ "EXPExamAplica.iFolioEs,"
					+ "EXPExamAplica.dtAplicacion,"
					+ "EXPExamAplica.dtConcluido,"
					+ "EXPExamAplica.lIniciado,"
					+ "EXPExamAplica.lDictaminado,"
					+ "EXPExamAplica.lInterConsulta,"
					+ "EXPExamAplica.lInterConcluye,"
					+ "EXPExamAplica.lConcluido,"
					+ "EXPExamAplica.dtDictamen,"
					+ "EXPExamAplica.dtEntregaRes,"
					+ "EXPExamAplica.dtArchivado,"
					+ "EXPExamAplica.iCveNumEmpresa,"
					+ "EXPExamAplica.iCveUsuSolicita,"
					+ "EXPExamAplica.tInicio, "
					+ "EXPExamAplica.tConcluido, "
					+ "GRLProceso.cDscProceso, "
					+ "GRLUniMed.cDscUniMed, "
					+ "EXPExamAplica.dtBlqDictamen "
					+ " from EXPExamAplica "
					+ " inner join GRLProceso on GRLProceso.iCveProceso = EXPExamAplica.iCveProceso "
					+ " inner join GRLUniMed on GRLUniMed.iCveUniMed = EXPExamAplica.iCveUniMed "
					+ " where EXPExamAplica.iCveExpediente =" + iCvePersonal
					+ " and EXPExamAplica.Ldictaminado = 0 "
					+ " order by iNumExamen, iCveExpediente";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica = new TVEXPExamAplica();
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICvePersonal(rset.getInt(5));
				vEXPExamAplica.setICveModulo(rset.getInt(6));
				vEXPExamAplica.setDtSolicitado(rset.getDate(7));
				vEXPExamAplica.setIFolioEs(rset.getInt(8));
				vEXPExamAplica.setDtAplicacion(rset.getDate(9));
				vEXPExamAplica.setDtConcluido(rset.getDate(10));
				vEXPExamAplica.setLIniciado(rset.getInt(11));
				vEXPExamAplica.setLDictaminado(rset.getInt(12));
				vEXPExamAplica.setLInterConsulta(rset.getInt(13));
				vEXPExamAplica.setLInterConcluye(rset.getInt(14));
				vEXPExamAplica.setLConcluido(rset.getInt(15));
				vEXPExamAplica.setDtDictamen(rset.getDate(16));
				vEXPExamAplica.setDtEntregaRes(rset.getDate(17));
				vEXPExamAplica.setDtArchivado(rset.getDate(18));
				vEXPExamAplica.setICveNumEmpresa(rset.getInt(19));
				vEXPExamAplica.setICveUsuSolicita(rset.getInt(20));
				vEXPExamAplica.setTInicio(rset.getTime(21));
				vEXPExamAplica.setTConcluido(rset.getTime(22));
				vEXPExamAplica.setCDscProceso(rset.getString(23));
				vEXPExamAplica.setCDscUniMed(rset.getString(24));
				vEXPExamAplica.setDtBlqDictamen(rset.getDate(25));
				// vcEXPExamAplica.addElement(vEXPExamAplica);
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
			return vEXPExamAplica;
		}
	}

	/*
	 * Update para desbloquear el dictamen de un examen
	 */
	public String updateDesbloquear(String cExpediente, String cExamen,
			String cFecha) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int iNewCve = 0;
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamAplica" + " Set dtBlqDictamen ='" + cFecha
					+ "' " + " Where iCveExpediente = " + cExpediente
					+ " And iNumExamen = " + cExamen;
			pstmt = conn.prepareStatement(cSQL);
			pstmt.executeUpdate();
			cClave = "La actualizacion a sido exitosa";

		} catch (Exception ex) {
			try {

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
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Método Find By All c/Where
	 */
	public Vector FindByAllDesbl(String cWhere) throws DAOException {
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
			cSQL = "select " + "EXPExamAplica.iCveExpediente,"
					+ "EXPExamAplica.iNumExamen," + "EXPExamAplica.iCveUniMed,"
					+ "EXPExamAplica.iCveProceso,"
					+ "EXPExamAplica.iCveModulo,"
					+ "EXPExamAplica.dtSolicitado,"
					+ "EXPExamAplica.lDictaminado,"
					+ "EXPExamAplica.dtDictamen,"
					+ "EXPExamAplica.dtBlqDictamen " + " from EXPExamAplica "
					+ cWhere + " ";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica = new TVEXPExamAplica();
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICveModulo(rset.getInt(5));
				vEXPExamAplica.setDtSolicitado(rset.getDate(6));
				vEXPExamAplica.setLDictaminado(rset.getInt(7));
				vEXPExamAplica.setDtDictamen(rset.getDate(8));
				vEXPExamAplica.setDtBlqDictamen(rset.getDate(9));
				vcEXPExamAplica.addElement(vEXPExamAplica);
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
			return vcEXPExamAplica;
		}
	}

	/**
	 * Método Actualizar para generar el Vale de servicio nuevamente
	 */
	public void GeneraValeDeServicioNuevamente(TVEXPExamAplica Examen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPExamAplica = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			Examen.setIFolioEs(0);
			Examen.setDtAplicacion(null);
			Examen.setLIniciado(0);
			this.updateCondicion(conn, Examen);
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception e) {
				warn("update.rollback", ex);
			}
			ex.printStackTrace();
			log("rolling back...");
			warn("GeneraExamen", ex);
			throw new DAOException("GeneraExamen");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("GeneraExamen.close", ex2);
			}
		}
	}

	/**
	 * Método update Modificado por JMG La funcion primordial es actualizar
	 * unicamente del registro encontrado lIniciado == 1
	 * 
	 */
	public Object updateCondicion(Connection conGral, Object obj)
			throws DAOException {
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
			TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPExamAplica" + " Set DTAPLICACION= ?,    "
					+ "     LINICIADO = ?," + "     IFOLIOES=?     "
					+ " Where iCveExpediente = ? " + " And iNumExamen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vEXPExamAplica.getDtAplicacion());
			pstmt.setInt(2, vEXPExamAplica.getLIniciado());
			pstmt.setInt(3, vEXPExamAplica.getIFolioEs());
			pstmt.setInt(4, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(5, vEXPExamAplica.getINumExamen());
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
	 * Método findExpToDelete (Utilizado en la sección de ADMINISTRACIÓN DE
	 * EXPEDIENTES BORRAR EXPEDIENTE )
	 * 
	 * @Autor: Ivan Santiago Méndez
	 */
	public TVPERDatos DeleteExpExam(Object obj, int icveusuario, ExpBitMod bita)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;

		TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		PreparedStatement pstmt8 = null;
		PreparedStatement pstmt9 = null;
		PreparedStatement pstmt10 = null;
		PreparedStatement pstmt11 = null;
		PreparedStatement pstmt12 = null;
		PreparedStatement pstmt13 = null;
		PreparedStatement pstmt14 = null;
		PreparedStatement pstmt15 = null;
		PreparedStatement pstmt16 = null;
		PreparedStatement pstmt17 = null;
		PreparedStatement pstmt18 = null;
		PreparedStatement pstmt19 = null;
		PreparedStatement pstmt20 = null;
		PreparedStatement pstmt21 = null;
		PreparedStatement pstmt22 = null;
		PreparedStatement pstmt23 = null;

		ResultSet rset = null;

		boolean borrado = false;

		int numexadia = 0;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			int Examen = 0;
			int proceso = 0;
			String FechaSol = "";

			String cSQL = "SELECT * FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? "
					+ " AND ICVEUSUDICTAMEN IS NULL AND (SELECT MAX (INUMEXAMEN) FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = ?) = ? AND ICVEPROCESO < 3";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(2, vEXPExamAplica.getINumExamen());
			pstmt.setInt(3, vEXPExamAplica.getICveExpediente());
			pstmt.setInt(4, vEXPExamAplica.getINumExamen());
			rset = pstmt.executeQuery();
			boolean tieneLicenciaGenerada = true;

			while (rset.next()) {
				tieneLicenciaGenerada = false;
			}

			String cSQLExa = " SELECT COUNT(A.INUMEXAMEN) FROM EXPEXAMAPLICA AS A"
					+ " WHERE A.ICVEEXPEDIENTE = "
					+ vEXPExamAplica.getICveExpediente()
					+ " AND "
					+ " A.DTSOLICITADO = (SELECT B.DTSOLICITADO FROM EXPEXAMAPLICA AS B "
					+ " WHERE B.ICVEEXPEDIENTE = A.ICVEEXPEDIENTE AND B.INUMEXAMEN = "
					+ vEXPExamAplica.getINumExamen() + ")";
			numexadia = this.RegresaInt(cSQLExa);

			if (!tieneLicenciaGenerada) {// si no tiene licencias generadas se
											// borra todo
				String cSQL1 = "DELETE FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL1);
				pstmt1 = conn.prepareStatement(cSQL1);
				pstmt1.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt1.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt1.executeUpdate();

				String cSQL2 = "DELETE FROM EXPEXAMCAT WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL2);
				pstmt2 = conn.prepareStatement(cSQL2);
				pstmt2.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt2.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt2.executeUpdate();

				String cSQL3 = "DELETE FROM EXPEXAMGRUPO WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL3);
				pstmt3 = conn.prepareStatement(cSQL3);
				pstmt3.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt3.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt3.executeUpdate();

				String cSQL4 = "DELETE FROM EXPEXAMGENERA WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL4);
				pstmt4 = conn.prepareStatement(cSQL4);
				pstmt4.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt4.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt4.executeUpdate();

				String cSQL5 = "DELETE FROM EXPEXAMPUESTO WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL5);
				pstmt5 = conn.prepareStatement(cSQL5);
				pstmt5.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt5.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt5.executeUpdate();

				String cSQL6 = "DELETE FROM EXPRAMA WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL6);
				pstmt6 = conn.prepareStatement(cSQL6);
				pstmt6.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt6.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt6.executeUpdate();

				String cSQL7 = "DELETE FROM EXPRECOMENDACION WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL7);
				pstmt7 = conn.prepareStatement(cSQL7);
				pstmt7.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt7.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt7.executeUpdate();

				String cSQL8 = "DELETE FROM EXPREQUISITOS WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL8);
				pstmt8 = conn.prepareStatement(cSQL8);
				pstmt8.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt8.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt8.executeUpdate();

				String cSQL9 = "DELETE FROM EXPRESULTADO WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL9);
				pstmt9 = conn.prepareStatement(cSQL9);
				pstmt9.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt9.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt9.executeUpdate();

				String cSQL10 = "DELETE FROM EXPSERVICIO WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL10);
				pstmt10 = conn.prepareStatement(cSQL10);
				pstmt10.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt10.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt10.executeUpdate();

				String cSQL11 = "DELETE FROM PERLICENCIA WHERE ICVEPERSONAL = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL11);
				pstmt11 = conn.prepareStatement(cSQL11);
				pstmt11.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt11.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt11.executeUpdate();

				String cSQL12 = "DELETE FROM EXPDICTAMENSERV WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL12);
				pstmt12 = conn.prepareStatement(cSQL12);
				pstmt12.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt12.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt12.executeUpdate();

				String cSQL13 = "DELETE FROM EXPDIAGNOSTICO WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL13);
				pstmt13 = conn.prepareStatement(cSQL13);
				pstmt13.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt13.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt13.executeUpdate();

				String cSQL14 = "DELETE FROM EXPFUNDICTAMEN WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL14);
				pstmt14 = conn.prepareStatement(cSQL14);
				pstmt14.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt14.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt14.executeUpdate();

				if (numexadia == 1) {
					String cSQL15 = "DELETE FROM EPICISCITA  WHERE ICVEPERSONAL = ? AND DTFECHA = ? ";
					// System.out.println(cSQL15);
					pstmt15 = conn.prepareStatement(cSQL15);
					pstmt15.setInt(1, vEXPExamAplica.getICveExpediente());
					pstmt15.setDate(2, vEXPExamAplica.getDtSolicitado());
					pstmt15.executeUpdate();
				}

				String cSQL16 = "DELETE FROM EMOEXAMEN WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				pstmt16 = conn.prepareStatement(cSQL16);
				pstmt16.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt16.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt16.executeUpdate();

				if (numexadia == 1) {
					String cSQL17 = "DELETE FROM EPICISCITAADNL  WHERE ICVEPERSONAL = ? AND DTFECHA = ? ";
					pstmt17 = conn.prepareStatement(cSQL17);
					pstmt17.setInt(1, vEXPExamAplica.getICveExpediente());
					pstmt17.setDate(2, vEXPExamAplica.getDtSolicitado());
					pstmt17.executeUpdate();
				}

				String cSQL18 = "DELETE FROM EXPREGSIN  WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				pstmt18 = conn.prepareStatement(cSQL18);
				pstmt18.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt18.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt18.executeUpdate();

				String cSQL19 = "DELETE FROM EXPSERVPREF  WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				// System.out.println(cSQL17);
				pstmt19 = conn.prepareStatement(cSQL19);
				pstmt19.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt19.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt19.executeUpdate();

				String cSQL20 = "DELETE FROM  EXPAUDIOMET  WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				pstmt20 = conn.prepareStatement(cSQL20);
				pstmt20.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt20.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt20.executeUpdate();

				String cSQL21 = "DELETE FROM  PERCATALOGONOAP  WHERE ICVEPERSONAL = ? AND DTINICIO >= ? ";
				pstmt21 = conn.prepareStatement(cSQL21);
				pstmt21.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt21.setDate(2, vEXPExamAplica.getDtSolicitado());
				pstmt21.executeUpdate();

				String cSQL22 = "DELETE FROM EXPDIAGNOSTICOCIF WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				//System.out.println(cSQL22);
				pstmt22 = conn.prepareStatement(cSQL22);
				pstmt22.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt22.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt22.executeUpdate();
				
				String cSQL23 = "DELETE FROM EXPRESULTADODOS WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
				//System.out.println(cSQL23);
				pstmt23 = conn.prepareStatement(cSQL23);
				pstmt23.setInt(1, vEXPExamAplica.getICveExpediente());
				pstmt23.setInt(2, vEXPExamAplica.getINumExamen());
				pstmt23.executeUpdate();
				
				borrado = true;

				// Guardando histirco en bitacora
				// INSERTANDO EN BITACORA
				String result = "";
				String Servicio = "0";
				// Calculando Timestamp para el campo TINIEXA
				java.util.Date utilDate = new java.util.Date(); // fecha actual
				long lnMilisegundos = utilDate.getTime();
				java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
						lnMilisegundos);
				// System.out.println("sql.Timestamp: "+sqlTimestamp);
				// Guardando el registro en bitacora de cambios
				String Nota1 = "";
				String Descripcion = "El examen numero "
						+ vEXPExamAplica.getINumExamen()
						+ " que se solicito el dia "
						+ vEXPExamAplica.getDtSolicitado()
						+ " fue eliminado por el usuario " + icveusuario + " "
						+ Nota1;
				String cSQLB = "insert into EXPBITMOD "
						+ "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, icveservicio, CMACADDRESS, CCOMPUTERNAME, CIPACCESO)"
						+ "values(" + vEXPExamAplica.getICveExpediente() + ", "
						+ vEXPExamAplica.getINumExamen() + ", 2, '"
						+ sqlTimestamp + "', '" + Descripcion + "', "
						+ icveusuario + ", " + Servicio + " , '"
						+ bita.getMacAddress() + "' , '"
						+ bita.getComputerName() + "' , '"
						+ bita.getIpAddress() + "')";
				// GENERANDO DESCRIPCIÓN
				TDEXPServicio dEXPServicio = new TDEXPServicio();
				try {
					dEXPServicio.Sentencia(cSQLB);
				} catch (Exception ex) {
					warn("Sentencia", ex);
				}

			}
		} catch (Exception ex) {
			warn("findUserbyExp", ex);
			throw new DAOException("findUserbyExp");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (pstmt3 != null) {
					pstmt3.close();
				}
				if (pstmt4 != null) {
					pstmt4.close();
				}
				if (pstmt5 != null) {
					pstmt5.close();
				}
				if (pstmt6 != null) {
					pstmt6.close();
				}
				if (pstmt7 != null) {
					pstmt7.close();
				}
				if (pstmt8 != null) {
					pstmt8.close();
				}
				if (pstmt9 != null) {
					pstmt9.close();
				}
				if (pstmt10 != null) {
					pstmt10.close();
				}
				if (pstmt11 != null) {
					pstmt11.close();
				}
				if (pstmt12 != null) {
					pstmt12.close();
				}
				if (pstmt13 != null) {
					pstmt13.close();
				}
				if (pstmt14 != null) {
					pstmt14.close();
				}
				if (pstmt15 != null) {
					pstmt15.close();
				}
				if (pstmt16 != null) {
					pstmt16.close();
				}
				if (pstmt17 != null) {
					pstmt17.close();
				}
				if (pstmt18 != null) {
					pstmt18.close();
				}
				if (pstmt19 != null) {
					pstmt19.close();
				}
				if (pstmt20 != null) {
					pstmt20.close();
				}
				if (pstmt21 != null) {
					pstmt21.close();
				}
				if (pstmt22 != null) {
					pstmt22.close();
				}
				if (pstmt23 != null) {
					pstmt23.close();
				}

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("findUserbyExp.close", ex2);
			}
			if (borrado) {
				return null;
			} else {
				return new TVPERDatos();
			}

		}
	}

	/**
	 * Método findExpToDelete (Utilizado en la sección de ADMINISTRACIÓN DE
	 * EXPEDIENTES BORRAR EXPEDIENTE )
	 * 
	 * @Autor: Ivan Santiago Méndez
	 */
	public TVPERDatos findExpToDelete(Object obj) throws DAOException {
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
					+ " And   b.INUMEXAMEN = " + vEXPExamAplica.getINumExamen()
					+ " " + " And   b.dtSolicitado = '"
					+ vEXPExamAplica.getDtSolicitado() + "' "
					+ " Order by b.iNumExamen ";
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
	 * Método findExpToDelete (Utilizado en la sección de ADMINISTRACIÓN DE
	 * EXPEDIENTES BORRAR EXPEDIENTE )
	 * 
	 * @Autor: AG SA
	 */
	public int RegresaInt(String SQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		int regreso = 0;
		// TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = SQL;
			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			vPERDatos = new TVPERDatos();
			while (rset.next()) {
				regreso = rset.getInt(1);
				System.out.println("regreso = "+regreso);
			}

		} catch (Exception ex) {
			warn("RegresaInt", ex);
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
				warn("RegresaInt", ex2);
			}
			return regreso;
		}
	}

	/**
	 * Método RegresaS
	 * 
	 * @Autor: AG SA
	 */
	public String RegresaS(String SQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		String regreso = "";
		// TVEXPExamAplica vEXPExamAplica = (TVEXPExamAplica) obj;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = SQL;
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			vPERDatos = new TVPERDatos();
			while (rset.next()) {
				regreso = rset.getString(1);
				// System.out.println(regreso);
			}

		} catch (Exception ex) {
			warn("RegresaString", ex);
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
				warn("RegresaInt", ex2);
			}
			return regreso;
		}
	}

	/**
	 * Método Find By All Numeros de Examen EPI y EMO
	 */
	public Vector FindByAllEPIEMO(String cWhere) throws DAOException {
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
			cSQL = "select "
					+ "EXPExamAplica.iCveExpediente,"
					+ "EXPExamAplica.iNumExamen,"
					+ "EXPExamAplica.iCveUniMed,"
					+ "EXPExamAplica.iCveProceso,"
					+ "EXPExamAplica.iCvePersonal,"
					+ "EXPExamAplica.iCveModulo,"
					+ "EXPExamAplica.dtSolicitado,"
					+

					"EXPExamAplica.lDictaminado,"
					+ "EXPExamAplica.dtDictamen,"
					+

					"GRLProceso.cDscProceso, "
					+ "GRLUniMed.cDscUniMed, "
					+ "GRLModulo.cDscModulo "
					+

					" from EXPExamAplica "
					+ " inner join GRLProceso on GRLProceso.iCveProceso = EXPExamAplica.iCveProceso "
					+ " inner join GRLUniMed on GRLUniMed.iCveUniMed = EXPExamAplica.iCveUniMed "
					+ " inner join GRLModulo on GRLModulo.iCveModulo = EXPExamAplica.iCveModulo "
					+ cWhere + " "
					+ "  AND GRLModulo.iCveUnimed = EXPExamAplica.iCveUnimed "
					+ "order by iNumExamen, iCveExpediente";

			 System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamAplica = new TVEXPExamAplica();
				vEXPExamAplica.setICveExpediente(rset.getInt(1));
				vEXPExamAplica.setINumExamen(rset.getInt(2));
				vEXPExamAplica.setICveUniMed(rset.getInt(3));
				vEXPExamAplica.setICveProceso(rset.getInt(4));
				vEXPExamAplica.setICvePersonal(rset.getInt(5));
				vEXPExamAplica.setICveModulo(rset.getInt(6));
				vEXPExamAplica.setDtSolicitado(rset.getDate(7));

				vEXPExamAplica.setLDictaminado(rset.getInt(8));

				vEXPExamAplica.setDtDictamen(rset.getDate(9));
				vEXPExamAplica.setCDscProceso(rset.getString(10));
				vEXPExamAplica.setCDscUniMed(rset.getString(11));
				vEXPExamAplica.setCDscModulo(rset.getString(12));
				vcEXPExamAplica.addElement(vEXPExamAplica);

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
			return vcEXPExamAplica;
		}
	}


	/**
	 * Metodo Find By Selector (Utilizado para la Seleccion de personal) AG
	 * SA
	 */
	public int FindByMaxExamen(int cExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iNumExamen = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT MAX(INUMEXAMEN) FROM EXPEXAMAPLICA " +
					" WHERE ICVEEXPEDIENTE = "+cExpediente;

			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if(rset.getInt(1) > 0){
					iNumExamen = rset.getInt(1);
				}else{
					iNumExamen = 0;
				}
			}
			//System.out.println(iNumExamen);
		} catch (Exception ex) {
			warn("FindBySelector", ex);
			throw new DAOException("FindBySelector");
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
				warn("FindBySelector.close", ex2);
			}
			return iNumExamen;
		}
	}
	
	/**
	 * Metodo Find By Selector (Utilizado para la Seleccion de personal) AG
	 * SA
	 */
	public int FindByCountExamenEPI(int cExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iNumExamen = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT COUNT(INUMEXAMEN) FROM EXPEXAMAPLICA " +
					" WHERE ICVEPROCESO = 1 AND ICVEEXPEDIENTE = "+cExpediente;

			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if(rset.getInt(1) > 0){
					iNumExamen = rset.getInt(1);
				}else{
					iNumExamen = 0;
				}
			}
			//System.out.println(iNumExamen);
		} catch (Exception ex) {
			warn("FindBySelector", ex);
			throw new DAOException("FindBySelector");
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
				warn("FindBySelector.close", ex2);
			}
			return iNumExamen;
		}
	}
		
	
}


