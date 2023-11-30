package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.util.reglas.RServicioLaboratorioDeAnalisisClinicos;
import gob.sct.medprev.vo.*;

import com.micper.util.*;
import com.micper.seguridad.vo.TVDinRep02;

/**
 * <p>
 * Title: Data Acces Object de MEDSERVObligatorio DAO
 * </p>
 * 
 * @author AG SA L 01-10-2013
 */

public class TDMEDSERVObligatorio extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo"); 

	public TDMEDSERVObligatorio() {
	}

	/**
	 * Método Find By All
	 */
	@SuppressWarnings({ "unchecked", "finally" })
	public Vector FindByAll(String Expediente, String Examen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtO = null;
		ResultSet rsetO = null;
		@SuppressWarnings("rawtypes")
		Vector vcMEDSERVObligatorio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQL2 = "";
			String Motivos = "";
			String Sexo = "";
			TVMEDSERVObligatorio vMEDSERVObligatorio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select "
					+ "C.iCveMotivo, "
					+ "P.cSexo "
					+ " from EXPExamAplica as A, Expexamcat as C, Perdatos as P "
					+ "Where A.icveexpediente = P.icveexpediente and "
					+ "A.icveexpediente = C.icveexpediente and "
					+ "A.inumexamen = C.inumexamen and "
					+ "A.icveexpediente = " + Expediente + " and "
					+ "A.inumexamen = " + Examen + " and "
					+ "A.ldictaminado = 0 and " + "A.icveproceso = 1 ";
			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if (Motivos.equals("")) {
					Motivos = rset.getString(1);
				} else {
					Motivos = Motivos + "," + rset.getString(1);
				}
				Sexo = rset.getString(2);
			}

			if (!Motivos.equals("")) {
				cSQL2 = "select "
						+ "S.iCveServicio,"
						+ "S.cDscServicio "
						+ " from EXPServicio as E, MedServicio as S, MEDSERVObligatorio as O "
						+ "Where " + "E.icveservicio = S.icveservicio and "
						+ "E.icveservicio = O.icveservicio and "
						+ "E.icveexpediente = " + Expediente + " and "
						+ "E.inumexamen = " + Examen + " and "
						+ "E.Lconcluido = 0 and " + "O.icvemotivo in ("
						+ Motivos + ") and " + "(O.cSexo = 'A' or O.cSexo = '"
						+ Sexo + "') "
						+ "Group By S.icveServicio, S.cDscServicio ";
				System.out.println(cSQL2);
				pstmtO = conn.prepareStatement(cSQL2);
				rsetO = pstmtO.executeQuery();
				while (rsetO.next()) {
					vMEDSERVObligatorio = new TVMEDSERVObligatorio();
					vMEDSERVObligatorio.setICveServicio(rsetO.getInt(1));
					vMEDSERVObligatorio.setCDscServicio(rsetO.getString(2));
					vcMEDSERVObligatorio.addElement(vMEDSERVObligatorio);
				}
			}
			
			System.out.println("vcMEDSERVObligatorio="+vcMEDSERVObligatorio.size());
		  ///////// Validando que Laboratorio de Analisis Clinico sea Obligatorio 2017/10/25  AG SA ///////////////////////////////
			//////////Validar que el servicio de Laboratorio de dAnalisis Clinicos no haya sido Concluido
			TDEXPServicio dEXPServicio = new TDEXPServicio();
			int lconcluidoLab = dEXPServicio.RegresaInt("Select lconcluido from expservicio where icveexpediente = "+Expediente+" and inumexamen = "+Examen+" and icveservicio = 2");
			if(lconcluidoLab == 0){
				RServicioLaboratorioDeAnalisisClinicos LAboObligatorio = new RServicioLaboratorioDeAnalisisClinicos();
				String LabObligatorioResp = "";
				LabObligatorioResp = LAboObligatorio.ObligatoriosLaboratorio(Expediente, Examen);
				if(LabObligatorioResp.length()>10){
					vMEDSERVObligatorio = new TVMEDSERVObligatorio();
					vMEDSERVObligatorio.setICveServicio(2);
					vMEDSERVObligatorio.setCDscServicio("LABORATORIO DE ANÁLISIS CLÍNICOS");
					vcMEDSERVObligatorio.addElement(vMEDSERVObligatorio);
				}
			}
			System.out.println("vcMEDSERVObligatorio="+vcMEDSERVObligatorio.size());
			///////// Validando INTERONSULTAS PENDIENTES DE CAPTURA POR REGLAS DE EXAMEN 2017/12/11 AG SA ///////////////////////////////
				@SuppressWarnings({ "rawtypes" })
				Vector vcMEDSERVObligatorioInterConsulta = new Vector();
				@SuppressWarnings("unused")
				TVMEDSERVObligatorio vMEDSERVObligatorio2 = new TVMEDSERVObligatorio();
				@SuppressWarnings("unused")
				TVMEDSERVObligatorio vMEDSERVObligatorioInterconsulta = new TVMEDSERVObligatorio();
				vcMEDSERVObligatorioInterConsulta = dEXPServicio.ServObligatoriosXInterconsulta(Expediente, Examen);
	
				if (vcMEDSERVObligatorioInterConsulta.size() > 0) {
					System.out.println("Existen servicios de Intercosulta obligatorios");
					for (int a = 0; a < vcMEDSERVObligatorioInterConsulta.size(); a++) {
						vMEDSERVObligatorioInterconsulta = (TVMEDSERVObligatorio) vcMEDSERVObligatorioInterConsulta.get(a);
						boolean coincidenciaDeServicio = false;
						if (vcMEDSERVObligatorio.size() > 0) {
							System.out.println("Existen servicios obligatorios");
							for (int i = 0; i < vcMEDSERVObligatorio.size(); i++) {
								vMEDSERVObligatorio2 = (TVMEDSERVObligatorio) vcMEDSERVObligatorio.get(i);
								if (vMEDSERVObligatorio2.getICveServicio() == vMEDSERVObligatorioInterconsulta
										.getICveServicio()) {
									coincidenciaDeServicio = true;
								}
							}
						}
						if(!coincidenciaDeServicio){
							vMEDSERVObligatorio = new TVMEDSERVObligatorio();
							vMEDSERVObligatorio.setICveServicio(vMEDSERVObligatorioInterconsulta.getICveServicio());
							vMEDSERVObligatorio.setCDscServicio(vMEDSERVObligatorioInterconsulta.getCDcsServicio());
							vcMEDSERVObligatorio.addElement(vMEDSERVObligatorio);	
						}
					}
				}
				System.out.println("vcMEDSERVObligatorio="+vcMEDSERVObligatorio.size());
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
				if (rsetO != null) {
					rsetO.close();
				}
				if (pstmtO != null) {
					pstmtO.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcMEDSERVObligatorio;
		}
	}

	/**
	 * Método para conocer si Laboratorio de analisis clinicos sera un servicio Obligatorio
	 */
	public Vector FindByAll2(String Expediente, String Examen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtO = null;
		ResultSet rsetO = null;
		Vector vcMEDSERVObligatorio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQL2 = "";
			String Motivos = "";
			String Sexo = "";
			TVMEDSERVObligatorio vMEDSERVObligatorio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "C.iCveMotivo, "
					+ "P.cSexo "
					+ " from EXPExamAplica as A, Expexamcat as C, Perdatos as P "
					+ "Where A.icveexpediente = P.icveexpediente and "
					+ "A.icveexpediente = C.icveexpediente and "
					+ "A.inumexamen = C.inumexamen and "
					+ "A.icveexpediente = " + Expediente + " and "
					+ "A.inumexamen = " + Examen + " and "
					+ "A.ldictaminado = 0 and " + "A.icveproceso = 1 ";
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if (Motivos.equals("")) {
					Motivos = rset.getString(1);
				} else {
					Motivos = Motivos + "," + rset.getString(1);
				}
				Sexo = rset.getString(2);
			}

			if (!Motivos.equals("")) {
				cSQL2 = "select "
						+ "S.iCveServicio,"
						+ "S.cDscServicio "
						+ " from EXPServicio as E, MedServicio as S, MEDSERVObligatorio as O "
						+ "Where " + "E.icveservicio = S.icveservicio and "
						+ "E.icveservicio = O.icveservicio and "
						+ "E.icveexpediente = " + Expediente + " and "
						+ "E.inumexamen = " + Examen + " and "
						+ "E.Lconcluido = 0 and " + "O.icvemotivo in ("
						+ Motivos + ") and " + "(O.cSexo = 'A' or O.cSexo = '"
						+ Sexo + "') "
						+ "Group By S.icveServicio, S.cDscServicio ";
				//System.out.println(cSQL2);
				pstmtO = conn.prepareStatement(cSQL2);
				rsetO = pstmtO.executeQuery();
				while (rsetO.next()) {
					vMEDSERVObligatorio = new TVMEDSERVObligatorio();
					vMEDSERVObligatorio.setICveServicio(rsetO.getInt(1));
					vMEDSERVObligatorio.setCDscServicio(rsetO.getString(2));
					vcMEDSERVObligatorio.addElement(vMEDSERVObligatorio);
				}
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
				if (rsetO != null) {
					rsetO.close();
				}
				if (pstmtO != null) {
					pstmtO.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcMEDSERVObligatorio;
		}
	}	
	
	
	

}