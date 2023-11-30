package gob.sct.medprev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.sql.DAOBase;
import com.micper.sql.DbConnection;

import gob.sct.medprev.vo.TVEXPExamAplica;
import gob.sct.medprev.vo.TVPERDatos;


public class TDELIMINAExpAuto extends DAOBase {

	
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private TVEXPExamAplica vEXPExamAplica2;
	private TVPERDatos vDatos2;

	/**
	 * Método DeleteExpConExam 
	 * 
	 * @Autor: AG SA L
	 */
	@SuppressWarnings({ "finally" })
	public TVPERDatos DeleteExpConExam()
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		TDEXPExamAplica dAplica = new TDEXPExamAplica();
		PreparedStatement pstmt0 = null;
		ResultSet rset0 = null;
		Vector vcEXAMAplica = new Vector();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		ResultSet rset = null;
		boolean borrado = false;
		int numexadia = 0;
		Boolean bTransfronterizo = false;
		
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			String cSQL0 = "SELECT A.ICVEEXPEDIENTE, A.INUMEXAMEN, A.DTSOLICITADO, A.TINIEXA, "
					        + "		A.ICVEUNIMED, A.ICVEMODULO, A.ICVEPROCESO,A.ICVEUSUSOLICITA,"
					        + "		HOUR(CURRENT TIMESTAMP - P.TSGENERADO) "
							+ " FROM "
							+ " 	EXPEXAMAPLICA AS A, "
							+ " 	PERDATOS AS P "
							+ " WHERE "
							+ " 	A.ICVEEXPEDIENTE = P.ICVEEXPEDIENTE AND "
							+ " 	DTSOLICITADO = CURRENT DATE AND "
							+ " 	LINICIADO = 0 AND "
							+ " 	TSGENERADO >= CURRENT DATE AND "
							+ " 	TSGENERADO < (CURRENT DATE + 1 DAY) AND "
							+ " 	HOUR(CURRENT TIMESTAMP - TSGENERADO) >="+VParametros.getPropEspecifica("TMPDEELIMINACIONEXPHRS");
			
			System.out.println(cSQL0);
			pstmt0 = conn.prepareStatement(cSQL0);
			//System.out.println("op1");
			rset0 = pstmt0.executeQuery();
			//System.out.println("op2");
			//vEXPExamAplica2 = null;
			//System.out.println("op3");
			while (rset0.next()) {
				vEXPExamAplica2 = new TVEXPExamAplica();
				//System.out.println("op4");
				//System.out.println(rset0.getInt(1));
				vEXPExamAplica2.setICveExpediente(rset0.getInt(1));
				//System.out.println("op4-1");
				vEXPExamAplica2.setINumExamen(rset0.getInt(2));
				//System.out.println("op4-2");
				vEXPExamAplica2.setDtSolicitado(rset0.getDate(3));
				//System.out.println("op4-3");
				vEXPExamAplica2.setTIniExa(rset0.getTimestamp(4));
				//System.out.println("op4-4");
				vEXPExamAplica2.setICveUniMed(rset0.getInt(5));
				//System.out.println("op4-5");
				vEXPExamAplica2.setICveModulo(rset0.getInt(6));
				//System.out.println("op4-6");
				vEXPExamAplica2.setICveProceso(rset0.getInt(7));
				//System.out.println("op4-7");
				vEXPExamAplica2.setICveUsuSolicita(rset0.getInt(8));
				//System.out.println("op4-8");
				vcEXAMAplica.addElement(vEXPExamAplica2);
				//System.out.println("op4-9");
			}
			
			//System.out.println("op5");
			TVEXPExamAplica vEXPExamAplica;
			//System.out.println("vcEXAMAplica = "+vcEXAMAplica.size());
			if(vcEXAMAplica.size() > 0){
				for (int i = 0; i < vcEXAMAplica.size(); i++) {
					//System.out.println("Entro al For");
					vEXPExamAplica = (TVEXPExamAplica) vcEXAMAplica.get(i);
	
					String cSQL = "SELECT * FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? "
							+ " AND ICVEUSUDICTAMEN IS NULL AND (SELECT MAX (INUMEXAMEN) FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = ?) = ? AND ICVEPROCESO < 3";
					
					//System.out.println(cSQL);
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
	
					////Evitar eliminar expedientes y examenes de transfronterizos
					if(vEXPExamAplica.getICveUniMed()==1 && vEXPExamAplica.getICveModulo() == 14){
						tieneLicenciaGenerada = true;	
					}
					/// Evitar eliminar examenes EMO por falta de vale de servicios
					if(vEXPExamAplica.getICveModulo()==2){
						tieneLicenciaGenerada = true;	
					}
										
					
					String cSQLExa = " SELECT COUNT(A.INUMEXAMEN) FROM EXPEXAMAPLICA AS A" + " WHERE A.ICVEEXPEDIENTE = "
							+ vEXPExamAplica.getICveExpediente() + " AND "
							+ " A.DTSOLICITADO = (SELECT B.DTSOLICITADO FROM EXPEXAMAPLICA AS B "
							+ " WHERE B.ICVEEXPEDIENTE = A.ICVEEXPEDIENTE AND B.INUMEXAMEN = "
							+ vEXPExamAplica.getINumExamen() + ")";
					numexadia = dAplica.RegresaInt(cSQLExa);
					//System.out.println(cSQLExa);
					//System.out.println("tieneLicenciaGenerada = "+tieneLicenciaGenerada);
					if (!tieneLicenciaGenerada) {// si no tiene licencias generadas
													// se
													// borra todo
						String cSQL1 = "DELETE FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
						// //System.out.println(cSQL1);
						pstmt1 = conn.prepareStatement(cSQL1);
						pstmt1.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt1.setInt(2, vEXPExamAplica.getINumExamen());
						pstmt1.executeUpdate();
						//System.out.println("cSQL1 = "+cSQL1);
	
						String cSQL2 = "DELETE FROM EXPEXAMCAT WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
						// //System.out.println(cSQL2);
						pstmt2 = conn.prepareStatement(cSQL2);
						pstmt2.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt2.setInt(2, vEXPExamAplica.getINumExamen());
						pstmt2.executeUpdate();
						//System.out.println("cSQL2 = "+cSQL2);
						
								String cSQL3 = "DELETE FROM EXPEXAMGRUPO WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
						// //System.out.println(cSQL3);
						pstmt3 = conn.prepareStatement(cSQL3);
						pstmt3.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt3.setInt(2, vEXPExamAplica.getINumExamen());
						pstmt3.executeUpdate();
						//System.out.println("cSQL3 = "+cSQL3);
						
						String cSQL4 = "DELETE FROM EXPEXAMGENERA WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
						// //System.out.println(cSQL4);
						pstmt4 = conn.prepareStatement(cSQL4);
						pstmt4.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt4.setInt(2, vEXPExamAplica.getINumExamen());
						pstmt4.executeUpdate();
						//System.out.println("cSQL4 = "+cSQL4);
						
						String cSQL5 = "DELETE FROM EXPEXAMPUESTO WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
						// //System.out.println(cSQL5);
						pstmt5 = conn.prepareStatement(cSQL5);
						pstmt5.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt5.setInt(2, vEXPExamAplica.getINumExamen());
						pstmt5.executeUpdate();
						//System.out.println("cSQL5 = "+cSQL5);
	
						String cSQL6 = "DELETE FROM LICFFH WHERE ICVEPERSONAL = ? ";
						// //System.out.println(cSQL6);
						pstmt6 = conn.prepareStatement(cSQL6);
						pstmt6.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt6.executeUpdate();
						//System.out.println("cSQL6 = "+cSQL6);
						
						String cSQL7 = "DELETE FROM EPICISCITA  WHERE ICVEPERSONAL = ? AND DTFECHA = ? ";
						// //System.out.println(cSQL15);
						pstmt7 = conn.prepareStatement(cSQL7);
						pstmt7.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt7.setDate(2, vEXPExamAplica.getDtSolicitado());
						pstmt7.executeUpdate();
						//System.out.println("cSQL7 = "+cSQL7);
						
						
						// Calculando Timestamp para el campo TINIEXA
						java.util.Date utilDate = new java.util.Date(); // fecha
																		// actual
						long lnMilisegundos = utilDate.getTime();
						//System.out.println("Bitacora");
						java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
						String Descripcion = "El examen numero " + vEXPExamAplica.getINumExamen()
								+ " que se solicito el dia " + vEXPExamAplica.getDtSolicitado()
								+ " fue eliminado debido a que al expediente no se le genero el vale de servicio se eliminó el examen, el cual fue generado  " + vEXPExamAplica.getTIniExa()
								+ " Unidad="+vEXPExamAplica.getICveUniMed()+", Modulo="+vEXPExamAplica.getICveModulo()+", Proceso="+vEXPExamAplica.getICveProceso()
								+ " Usuario que genero examen="+vEXPExamAplica.getICveUsuSolicita();
						
						String cSQLB = "insert into EXPBITMOD "
								+ "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, icveservicio, CMACADDRESS, CCOMPUTERNAME, CIPACCESO)"
								+ "values(" + vEXPExamAplica.getICveExpediente() + ", " + vEXPExamAplica.getINumExamen()
								+ ", 22, '" + sqlTimestamp + "', '" + Descripcion + "', 0, 0, '' , '' , '')";
						
						//System.out.println("cSQLB = "+cSQLB);
						// GENERANDO DESCRIPCIÓN
						TDEXPServicio dEXPServicio = new TDEXPServicio();
						try {
							//System.out.println("Bitacora - 2");
							dEXPServicio.Sentencia(cSQLB);
							//System.out.println("Bitacora - 3");
						} catch (Exception ex) {
							warn("Sentencia", ex);
						}
					}
				}
			}
			/// for
		} catch (Exception ex) {
			warn("DeleteExpConExam", ex);
			throw new DAOException("DeleteExpConExam");
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

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("DeleteExpConExam.close", ex2);
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
	 * @Autor: AG SA
	 */
	@SuppressWarnings({ "finally" })
	public TVPERDatos DeleteExpSinExam()			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt0 = null;
		ResultSet rset0 = null;
		Vector<TVPERDatos> vcDatos = new Vector<TVPERDatos>();
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rset = null;
		boolean borrado = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			String cSQL0 = "SELECT A.ICVEEXPEDIENTE, A.TSGENERADO, A.ICVEUSUREGISTRA,"
					    + " 	A.CNOMBRE, A.CAPPATERNO, A.CAPMATERNO, A.CCURP,"
					    + " 	HOUR(CURRENT TIMESTAMP - A.TSGENERADO)"
						+ " FROM "
						+ "    PERDATOS A"
						+ "    LEFT JOIN EXPEXAMAPLICA B ON A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE "
						+ " WHERE "
						+ "    B.ICVEEXPEDIENTE IS NULL AND "
						+ "    TSGENERADO >= CURRENT DATE AND"
						+ "    TSGENERADO < (CURRENT DATE + 1 DAY) AND"
						+ "    HOUR(CURRENT TIMESTAMP - TSGENERADO) >="+VParametros.getPropEspecifica("TMPDEELIMINACIONEXPHRS");

			System.out.println(cSQL0);
			pstmt0 = conn.prepareStatement(cSQL0);
			
			rset0 = pstmt0.executeQuery();
			vEXPExamAplica2 = null;
			//vDatos2 = null;
			while (rset0.next()) {
				vDatos2 = new TVPERDatos();
				vDatos2.setICveExpediente(rset0.getInt(1));
				vDatos2.setTSGenerado(rset0.getTimestamp(2));
				vDatos2.setICveUsuRegistra(rset0.getInt(3));
				vDatos2.setCNombre(rset0.getString(4));
				vDatos2.setCApPaterno(rset0.getString(5));
				vDatos2.setCApMaterno(rset0.getString(6));
				vDatos2.setCCURP(rset0.getString(7));
				vcDatos.addElement(vDatos2);
			}
			TVPERDatos vPERDatos;
			for (int i = 0; i < vcDatos.size(); i++) {
				vPERDatos = vcDatos.get(i);

				String cSQL = "SELECT ICVEPERSONAL FROM LICPERLICENCIA WHERE ICVEPERSONAL = ? ";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vPERDatos.getICveExpediente());
				rset = pstmt.executeQuery();
				boolean tieneLicenciaGenerada = true;
				while (rset.next()) {
					if(rset.getInt(1)>0){
						tieneLicenciaGenerada = false;
					}
				}
				
				String cSQL4 = "SELECT ICVEPERSONAL FROM EPICISCITA WHERE ICVEPERSONAL = ? AND ICVEUNIMED = 1 AND ICVEMODULO = 14";
				pstmt4 = conn.prepareStatement(cSQL4);
				pstmt4.setInt(1, vPERDatos.getICveExpediente());
				rset = pstmt4.executeQuery();
				while (rset.next()) {
					if(rset.getInt(1)>0){
						tieneLicenciaGenerada = false;
					}
				}
				
				if(tieneLicenciaGenerada){	
					//System.out.println("eLIMINANDO eXPEDIENTES");
					String cSQL1 = "DELETE FROM PERDATOS WHERE ICVEPERSONAL= ?";
					//System.out.println(cSQL1 + " - "+vPERDatos.getICvePersonal());
					pstmt1 = conn.prepareStatement(cSQL1);
					pstmt1.setInt(1, vPERDatos.getICveExpediente());
					pstmt1.executeUpdate();

					//System.out.println("eLIMINANDO dIRECCION DE eXPEDIENTES");
					String cSQL2 = "DELETE FROM PERDIRECCION WHERE ICVEPERSONAL= ?";
					//System.out.println(cSQL2 + " - "+vPERDatos.getICvePersonal());
					pstmt2 = conn.prepareStatement(cSQL2);
					pstmt2.setInt(1, vPERDatos.getICveExpediente());
					pstmt2.executeUpdate();
					
					String cSQL3 = "DELETE FROM EPICISCITA  WHERE ICVEPERSONAL = ?";
					pstmt3 = conn.prepareStatement(cSQL3);
					pstmt3.setInt(1, vPERDatos.getICveExpediente());
					pstmt3.executeUpdate();
					//System.out.println("cSQL3 = "+cSQL3);
					
					borrado = true;

					// Calculando Timestamp para el campo TINIEXA
					java.util.Date utilDate = new java.util.Date(); // fecha
																	// actual
					long lnMilisegundos = utilDate.getTime();
					java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
					// //System.out.println("sql.Timestamp: "+sqlTimestamp);
					// Guardando el registro en bitacora de cambios
					String Descripcion = "El expediente fue eliminado debido a que no se generó el vale de servicio "
							+ "en las 3 primeras horas posteriores a la generación del expediente. Datos del Expediente "
							+ "Nombre="+vPERDatos.getCNombre()+",ApPaterno="+vPERDatos.getCApPaterno()+","
							+ "ApMaterno="+vPERDatos.getCApMaterno()+",CURP="+vPERDatos.getCCURP()+","
							+ "UsuRegistra="+vPERDatos.getICveUsuRegistra()+",Generado"+vPERDatos.getTSGenerado();
					String cSQLB = "insert into EXPBITMOD "
							+ "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, icveservicio, CMACADDRESS, CCOMPUTERNAME, CIPACCESO)"
							+ "values("+vPERDatos.getICveExpediente()+", 0," 
							+ "22, '" + sqlTimestamp + "', '" + Descripcion + "', 0, "
							+ " 0, '' , '' , '')";
					// GENERANDO DESCRIPCIÓN
					TDEXPServicio dEXPServicio = new TDEXPServicio();
					try {
						dEXPServicio.Sentencia(cSQLB);
					} catch (Exception ex) {
						warn("Sentencia", ex);
					}
				}
			} /// for
		} catch (Exception ex) {
			warn("DeleteExpSinExam", ex);
			throw new DAOException("DeleteExpSinExam");
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

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("DeleteExpSinExam.close", ex2);
			}
			if (borrado) {
				return null;
			} else {
				return new TVPERDatos();
			}

		}
	}
	
	/**
	 * Método DeleteExpConExam 
	 * 
	 * @Autor: AG SA L
	 */
	@SuppressWarnings({ "finally" })
	public TVPERDatos DeleteExpConExamCasoEspecial()
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		TDEXPExamAplica dAplica = new TDEXPExamAplica();
		PreparedStatement pstmt0 = null;
		ResultSet rset0 = null;
		Vector vcEXAMAplica = new Vector();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		ResultSet rset = null;
		boolean borrado = false;
		int numexadia = 0;
		
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			String cSQL0 = "SELECT A.ICVEEXPEDIENTE, A.INUMEXAMEN, A.DTSOLICITADO, A.TINIEXA, "
					        + "		A.ICVEUNIMED, A.ICVEMODULO, A.ICVEPROCESO,A.ICVEUSUSOLICITA,"
					        + "		TIMESTAMPDIFF(8,CHAR(CURRENT TIMESTAMP - TSGENERADO)) "
							+ " FROM "
							+ " 	EXPEXAMAPLICA AS A, "
							+ " 	PERDATOS AS P "
							+ " WHERE "
							+ " 	A.ICVEEXPEDIENTE = P.ICVEEXPEDIENTE AND "
							+ " 	DTSOLICITADO >=  (CURRENT DATE - 2 DAY) AND "
							+ " 	LINICIADO = 0 AND "
							+ " 	TSGENERADO >=  (CURRENT DATE - 1 DAY) AND "
							+ " 	TSGENERADO < (CURRENT DATE + 1 DAY) AND "
							+ " 	TIMESTAMPDIFF(8,CHAR(CURRENT TIMESTAMP - TSGENERADO)) >= "+VParametros.getPropEspecifica("TMPDEELIMINACIONEXPHRS");
			
			//System.out.println(cSQL0);
			pstmt0 = conn.prepareStatement(cSQL0);
			//System.out.println("op1");
			rset0 = pstmt0.executeQuery();
			//System.out.println("op2");
			//vEXPExamAplica2 = null;
			//System.out.println("op3");
			while (rset0.next()) {
				vEXPExamAplica2 = new TVEXPExamAplica();
				//System.out.println("op4");
				//System.out.println(rset0.getInt(1));
				vEXPExamAplica2.setICveExpediente(rset0.getInt(1));
				//System.out.println("op4-1");
				vEXPExamAplica2.setINumExamen(rset0.getInt(2));
				//System.out.println("op4-2");
				vEXPExamAplica2.setDtSolicitado(rset0.getDate(3));
				//System.out.println("op4-3");
				vEXPExamAplica2.setTIniExa(rset0.getTimestamp(4));
				//System.out.println("op4-4");
				vEXPExamAplica2.setICveUniMed(rset0.getInt(5));
				//System.out.println("op4-5");
				vEXPExamAplica2.setICveModulo(rset0.getInt(6));
				//System.out.println("op4-6");
				vEXPExamAplica2.setICveProceso(rset0.getInt(7));
				//System.out.println("op4-7");
				vEXPExamAplica2.setICveUsuSolicita(rset0.getInt(8));
				//System.out.println("op4-8");
				vcEXAMAplica.addElement(vEXPExamAplica2);
				//System.out.println("op4-9");
			}
			
			//System.out.println("op5");
			TVEXPExamAplica vEXPExamAplica;
			//System.out.println("vcEXAMAplica = "+vcEXAMAplica.size());
			if(vcEXAMAplica.size() > 0){
				for (int i = 0; i < vcEXAMAplica.size(); i++) {
					//System.out.println("Entro al For");
					vEXPExamAplica = (TVEXPExamAplica) vcEXAMAplica.get(i);
	
					String cSQL = "SELECT * FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? "
							+ " AND ICVEUSUDICTAMEN IS NULL AND (SELECT MAX (INUMEXAMEN) FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = ?) = ? AND ICVEPROCESO < 3";
					
					//System.out.println(cSQL);
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
					
					////Evitar eliminar expedientes y examenes de transfronterizos
					if(vEXPExamAplica.getICveUniMed()==1 && vEXPExamAplica.getICveModulo() == 14){
						tieneLicenciaGenerada = true;	
					}
					/// Evitar eliminar examenes EMO por falta de vale de servicios
					if(vEXPExamAplica.getICveModulo()==2){
						tieneLicenciaGenerada = true;	
					}
									
	
					/*String cSQLExa = " SELECT COUNT(A.INUMEXAMEN) FROM EXPEXAMAPLICA AS A" + " WHERE A.ICVEEXPEDIENTE = "
							+ vEXPExamAplica.getICveExpediente() + " AND "
							+ " A.DTSOLICITADO = (SELECT B.DTSOLICITADO FROM EXPEXAMAPLICA AS B "
							+ " WHERE B.ICVEEXPEDIENTE = A.ICVEEXPEDIENTE AND B.INUMEXAMEN = "
							+ vEXPExamAplica.getINumExamen() + ")";
					numexadia = dAplica.RegresaInt(cSQLExa);*/
					//System.out.println(cSQLExa);
					//System.out.println("tieneLicenciaGenerada = "+tieneLicenciaGenerada);
					if (!tieneLicenciaGenerada) {// si no tiene licencias generadas
													// se
													// borra todo
						String cSQL1 = "DELETE FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
						// //System.out.println(cSQL1);
						pstmt1 = conn.prepareStatement(cSQL1);
						pstmt1.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt1.setInt(2, vEXPExamAplica.getINumExamen());
						pstmt1.executeUpdate();
						//System.out.println("cSQL1 = "+cSQL1);
	
						String cSQL2 = "DELETE FROM EXPEXAMCAT WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
						// //System.out.println(cSQL2);
						pstmt2 = conn.prepareStatement(cSQL2);
						pstmt2.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt2.setInt(2, vEXPExamAplica.getINumExamen());
						pstmt2.executeUpdate();
						//System.out.println("cSQL2 = "+cSQL2);
						
								String cSQL3 = "DELETE FROM EXPEXAMGRUPO WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
						// //System.out.println(cSQL3);
						pstmt3 = conn.prepareStatement(cSQL3);
						pstmt3.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt3.setInt(2, vEXPExamAplica.getINumExamen());
						pstmt3.executeUpdate();
						//System.out.println("cSQL3 = "+cSQL3);
						
						String cSQL4 = "DELETE FROM EXPEXAMGENERA WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
						// //System.out.println(cSQL4);
						pstmt4 = conn.prepareStatement(cSQL4);
						pstmt4.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt4.setInt(2, vEXPExamAplica.getINumExamen());
						pstmt4.executeUpdate();
						//System.out.println("cSQL4 = "+cSQL4);
						
						String cSQL5 = "DELETE FROM EXPEXAMPUESTO WHERE ICVEEXPEDIENTE = ? AND INUMEXAMEN = ? ";
						// //System.out.println(cSQL5);
						pstmt5 = conn.prepareStatement(cSQL5);
						pstmt5.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt5.setInt(2, vEXPExamAplica.getINumExamen());
						pstmt5.executeUpdate();
						//System.out.println("cSQL5 = "+cSQL5);
	
						String cSQL6 = "DELETE FROM LICFFH WHERE ICVEPERSONAL = ? ";
						// //System.out.println(cSQL6);
						pstmt6 = conn.prepareStatement(cSQL6);
						pstmt6.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt6.executeUpdate();
						//System.out.println("cSQL6 = "+cSQL6);
						
						String cSQL7 = "DELETE FROM EPICISCITA  WHERE ICVEPERSONAL = ? AND DTFECHA = ? ";
						// //System.out.println(cSQL15);
						pstmt7 = conn.prepareStatement(cSQL7);
						pstmt7.setInt(1, vEXPExamAplica.getICveExpediente());
						pstmt7.setDate(2, vEXPExamAplica.getDtSolicitado());
						pstmt7.executeUpdate();
						//System.out.println("cSQL7 = "+cSQL7);
						
						
						// Calculando Timestamp para el campo TINIEXA
						java.util.Date utilDate = new java.util.Date(); // fecha
																		// actual
						long lnMilisegundos = utilDate.getTime();
						//System.out.println("Bitacora");
						java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
						String Descripcion = "El examen numero " + vEXPExamAplica.getINumExamen()
								+ " que se solicito el dia " + vEXPExamAplica.getDtSolicitado()
								+ " fue eliminado debido a que al expediente no se le genero el vale de servicio se eliminó el examen, el cual fue generado  " + vEXPExamAplica.getTIniExa()
								+ " Unidad="+vEXPExamAplica.getICveUniMed()+", Modulo="+vEXPExamAplica.getICveModulo()+", Proceso="+vEXPExamAplica.getICveProceso()
								+ " Usuario que genero examen="+vEXPExamAplica.getICveUsuSolicita();
						
						String cSQLB = "insert into EXPBITMOD "
								+ "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, icveservicio, CMACADDRESS, CCOMPUTERNAME, CIPACCESO)"
								+ "values(" + vEXPExamAplica.getICveExpediente() + ", " + vEXPExamAplica.getINumExamen()
								+ ", 22, '" + sqlTimestamp + "', '" + Descripcion + "', 0, 0, '' , '' , '')";
						
						//System.out.println("cSQLB = "+cSQLB);
						// GENERANDO DESCRIPCIÓN
						TDEXPServicio dEXPServicio = new TDEXPServicio();
						try {
							//System.out.println("Bitacora - 2");
							dEXPServicio.Sentencia(cSQLB);
							//System.out.println("Bitacora - 3");
						} catch (Exception ex) {
							warn("Sentencia", ex);
						}
					}
				}
			}
			/// for
		} catch (Exception ex) {
			warn("DeleteExpConExam", ex);
			throw new DAOException("DeleteExpConExam");
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

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("DeleteExpConExam.close", ex2);
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
	 * @Autor: AG SA
	 */
	@SuppressWarnings({ "finally" })
	public TVPERDatos DeleteExpSinExamCasoEspacial()
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt0 = null;
		ResultSet rset0 = null;
		Vector<TVPERDatos> vcDatos = new Vector<TVPERDatos>();
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rset = null;
		boolean borrado = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			String cSQL0 = "SELECT A.ICVEEXPEDIENTE, A.TSGENERADO, A.ICVEUSUREGISTRA,"
					    + " 	A.CNOMBRE, A.CAPPATERNO, A.CAPMATERNO, A.CCURP,"
					    + " 	TIMESTAMPDIFF(8,CHAR(CURRENT TIMESTAMP - A.TSGENERADO)) "
						+ " FROM "
						+ "    PERDATOS A"
						+ "    LEFT JOIN EXPEXAMAPLICA B ON A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE "
						+ " WHERE "
						+ "    B.ICVEEXPEDIENTE IS NULL AND "
						+ "    TSGENERADO >= (CURRENT DATE -1 DAY) AND"
						+ "    TSGENERADO < (CURRENT DATE + 1 DAY) AND"
						+ "    TIMESTAMPDIFF(8,CHAR(CURRENT TIMESTAMP - TSGENERADO)) >= "+VParametros.getPropEspecifica("TMPDEELIMINACIONEXPHRS");

			pstmt0 = conn.prepareStatement(cSQL0);
			//System.out.println(cSQL0);
			rset0 = pstmt0.executeQuery();
			vEXPExamAplica2 = null;
			//vDatos2 = null;
			while (rset0.next()) {
				vDatos2 = new TVPERDatos();
				vDatos2.setICveExpediente(rset0.getInt(1));
				vDatos2.setTSGenerado(rset0.getTimestamp(2));
				vDatos2.setICveUsuRegistra(rset0.getInt(3));
				vDatos2.setCNombre(rset0.getString(4));
				vDatos2.setCApPaterno(rset0.getString(5));
				vDatos2.setCApMaterno(rset0.getString(6));
				vDatos2.setCCURP(rset0.getString(7));
				vcDatos.addElement(vDatos2);
			}
			TVPERDatos vPERDatos;
			for (int i = 0; i < vcDatos.size(); i++) {
				vPERDatos = vcDatos.get(i);

				String cSQL = "SELECT ICVEPERSONAL FROM LICPERLICENCIA WHERE ICVEPERSONAL = ? ";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vPERDatos.getICveExpediente());
				rset = pstmt.executeQuery();
				boolean tieneLicenciaGenerada = true;
				while (rset.next()) {
					if(rset.getInt(1)>0){
						tieneLicenciaGenerada = false;
					}
				}				

				String cSQL4 = "SELECT ICVEPERSONAL FROM EPICISCITA WHERE ICVEPERSONAL = ? AND ICVEUNIMED = 1 AND ICVEMODULO = 14";
				pstmt4 = conn.prepareStatement(cSQL4);
				pstmt4.setInt(1, vPERDatos.getICveExpediente());
				rset = pstmt4.executeQuery();
				while (rset.next()) {
					if(rset.getInt(1)>0){
						tieneLicenciaGenerada = false;
					}
				}
				
				if(tieneLicenciaGenerada){	
					//System.out.println("eLIMINANDO eXPEDIENTES");
					String cSQL1 = "DELETE FROM PERDATOS WHERE ICVEPERSONAL= ?";
					//System.out.println(cSQL1 + " - "+vPERDatos.getICvePersonal());
					pstmt1 = conn.prepareStatement(cSQL1);
					pstmt1.setInt(1, vPERDatos.getICveExpediente());
					pstmt1.executeUpdate();

					//System.out.println("eLIMINANDO dIRECCION DE eXPEDIENTES");
					String cSQL2 = "DELETE FROM PERDIRECCION WHERE ICVEPERSONAL= ?";
					//System.out.println(cSQL2 + " - "+vPERDatos.getICvePersonal());
					pstmt2 = conn.prepareStatement(cSQL2);
					pstmt2.setInt(1, vPERDatos.getICveExpediente());
					pstmt2.executeUpdate();
					
					String cSQL3 = "DELETE FROM EPICISCITA  WHERE ICVEPERSONAL = ?";
					pstmt3 = conn.prepareStatement(cSQL3);
					pstmt3.setInt(1, vPERDatos.getICveExpediente());
					pstmt3.executeUpdate();
					//System.out.println("cSQL3 = "+cSQL3);
					
					borrado = true;

					// Calculando Timestamp para el campo TINIEXA
					java.util.Date utilDate = new java.util.Date(); // fecha
																	// actual
					long lnMilisegundos = utilDate.getTime();
					java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
					// //System.out.println("sql.Timestamp: "+sqlTimestamp);
					// Guardando el registro en bitacora de cambios
					String Descripcion = "El expediente fue eliminado debido a que no se generó el vale de servicio "
							+ "en las 3 primeras horas posteriores a la generación del expediente. Datos del Expediente "
							+ "Nombre="+vPERDatos.getCNombre()+",ApPaterno="+vPERDatos.getCApPaterno()+","
							+ "ApMaterno="+vPERDatos.getCApMaterno()+",CURP="+vPERDatos.getCCURP()+","
							+ "UsuRegistra="+vPERDatos.getICveUsuRegistra()+",Generado"+vPERDatos.getTSGenerado();
					String cSQLB = "insert into EXPBITMOD "
							+ "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, icveservicio, CMACADDRESS, CCOMPUTERNAME, CIPACCESO)"
							+ "values("+vPERDatos.getICveExpediente()+", 0," 
							+ "22, '" + sqlTimestamp + "', '" + Descripcion + "', 0, "
							+ " 0, '' , '' , '')";
					// GENERANDO DESCRIPCIÓN
					TDEXPServicio dEXPServicio = new TDEXPServicio();
					try {
						dEXPServicio.Sentencia(cSQLB);
					} catch (Exception ex) {
						warn("Sentencia", ex);
					}
				}
			} /// for
		} catch (Exception ex) {
			warn("DeleteExpSinExam", ex);
			throw new DAOException("DeleteExpSinExam");
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
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("DeleteExpSinExam.close", ex2);
			}
			if (borrado) {
				return null;
			} else {
				return new TVPERDatos();
			}

		}
	}
	
}
