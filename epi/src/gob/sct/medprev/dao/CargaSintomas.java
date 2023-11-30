/*
 * <p>
 * Title: Data Acces Object de EXPServicio DAO
 * </p>
 * <p>
 * Description: EXPServicio
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


package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;

import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.dao.*;




/**
 * <p>
 * Title: Data Acces Object de EXPServicio DAO
 * </p>
 * <p>
 * Description: EXPServicio
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

public class CargaSintomas extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private int iInserted; // Agregado por Rafael Alcocer Caldera 08/Nov/2006
	private int iUpdated; // Agregado por Rafael Alcocer Caldera 08/Nov/2006

	public CargaSintomas() {
	}

	
	
	/**
	 * Método que valida y ejecuta la carga de sintomas
	 */
	public boolean ValidaCargaSintomas(String cExpediente, String cExamen, String Servicio) throws DAOException {
		boolean Carga = false;
		System.out.println("ValidaCargaSintomas");
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		////////Validando existan registros para el servicio que desea atender
		int ExistenRegistros = 0;
		String ExistenRegistrosSQL = " SELECT COUNT (ICVEEXPEDIENTE) FROM EXPRESULTADO " 
									+" WHERE "
									+" ICVEEXPEDIENTE = "+cExpediente+" AND "
									+" INUMEXAMEN = "+cExamen+" AND "
									+" ICVESERVICIO = "+Servicio+" ";
		System.out.println(ExistenRegistrosSQL);
		try {
			ExistenRegistros = dEXPExamAplica.RegresaInt(ExistenRegistrosSQL);
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		////////// Si no existen resgistros se realizara la carga
		if(ExistenRegistros == 0){
			System.out.println("NO existen registros, se realizara la carga");
			////////// Obteniendo Genero ///////////////
				String cGenero = ""; 
				String cGeneroSQL = "SELECT CSEXO FROM PERDATOS WHERE ICVEEXPEDIENTE = "+cExpediente;
				try {
					cGenero = dEXPExamAplica.RegresaS(cGeneroSQL);
				}catch(Exception e){
					System.out.println(e);
				}
				System.out.println("Genero = "+cGenero);
				
			//////// CARGAMOS SINTOMAS /////////////// 
				try {
					this.CargandoSintomas(cExpediente, cExamen, Servicio, cGenero);
				}catch(Exception e){
					System.out.println(e);
				}
			
		}else{
			System.out.println("NO es necesario realizar una carga");
			Carga =  true;
		}
		
		return Carga;
	}

	

	/**
	 * Método que valida y ejecuta la carga de sintomas
	 * @param fAccepted 
	 */
	public boolean CargandoSintomas(String cExpediente, String cExamen, 
			String Servicio, String cGenero) throws DAOException {
		
		System.out.println("Generar carga de sintomas");
		
		/*
		 * Calcula Fecha Actual
		 */
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		String dFechaActual = "";
		TFechas dtFecha = new TFechas();
		dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha, "/");
		String cCampo = dFechaActual;
		dtCampo = tfCampo.getDateSQL(cCampo);
		boolean fAccepted = false;
		
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		try {
				Object objjsp = this.Inputs(cExpediente,cExamen);
				TVEXPExamAplica vExpExAp = (TVEXPExamAplica) objjsp;
				TVEXPExamCat vEXPExamCat = new TVEXPExamCat();
				TVGRLMotivo vGRLMotivo = new TVGRLMotivo();
				TVMEDSintExamen vMEDSintExamen = new TVMEDSintExamen();
				TVMEDSintoma vMEDSintoma = new TVMEDSintoma();
				
				TVGRLUniMed vGRLUniMed = new TVGRLUniMed();
				TVEXPServicio vEXPServicio = new TVEXPServicio();
				TVEXPRama vEXPRama = new TVEXPRama();
				TVEXPResultado vEXPResultado = new TVEXPResultado();
				
				TDGRLMotivo dGRLMotivo = new TDGRLMotivo();
				TDMEDSintExamen dMEDSintExamen = new TDMEDSintExamen();
				TDEXPServicio dEXPServicio = new TDEXPServicio();
				TDEXPRama dEXPRama = new TDEXPRama();
				TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
				TDEXPResultado dEXPResultado = new TDEXPResultado();
				
				TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
				TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
		
				Vector vcExpExamCat = new Vector();
				Vector vcGrlMotivo = new Vector();
				Vector vcMedSintExamen = new Vector();
				Vector vcMedSintoma = new Vector();					
				
				Vector vcGrlUniMed = new Vector();
				Vector vcEXPExamCat = new Vector();
				Vector vcMEDSintExamen = new Vector();
				
				Vector vcMEDSintExamenServ = new Vector();
				Vector vcMEDSintExamenRama = new Vector();
				Vector vcEXPResultado = new Vector();
				Vector vcDatos = new Vector();
				Vector vcEXPServicio = new Vector();
				Vector vcEXPRama = new Vector();
				Vector vcExamAp = new Vector();

				String SQLUpdate = "";
		
				/**
				 * Paso 1
				 * Con iCveExpediente e iNumExam traigo de EXPExamCat: -
				 * iCveModoTrans - iCveCategoria - iCveMotivo
				 */
		
				vcExpExamCat = dEXPExamAplica.FindExpExamCat(null, objjsp);
				System.out.println("Sizecategorias = "+vcExpExamCat.size());
				vcExamAp = dEXPExamAplica
						.FindByAll(" where EXPExamAplica.iCveExpediente = "
								+ cExpediente
								+ " and EXPExamAplica.iNumExamen = "
								+ cExamen);
				System.out.println("Sizeaplica = "+vcExamAp.size());
				if (vcExamAp.size() > 0
						&& ((TVEXPExamAplica) (vcExamAp.get(0))).getLIniciado() == 1) {
					System.out.println("Input con valores");
					if (vcExpExamCat.size() > 0) {
		
						for (int w = 0; w < vcExpExamCat.size(); w++)
							vEXPExamCat = (TVEXPExamCat) vcExpExamCat.get(w);
		
						/**
						 * Paso 2
						 * 
						 * Se Obtiene si se pueden realizar pagos a la Unidad Medica
						 * 
						 */
		
						HashMap hmMot = new HashMap();
						String cCveMotivo = "" + vEXPExamCat.getICveMotivo();
						hmMot.put("iCveMotivo", cCveMotivo);
		
						vcGrlUniMed = dGRLUniMed.FindByAll(hmMot);
		
						if (vcGrlUniMed.size() > 0) {
							vGRLUniMed = (TVGRLUniMed) vcGrlUniMed.get(0);
							if (vGRLUniMed.getLPago() != 0) {
		
								/**
								 * Paso 3
								 * 
								 * Se extrae la Lista de Motivos con el objetivo de
								 * tener el valor de lPago por motivo especifico
								 * 
								 */
								vcGrlMotivo = dGRLMotivo
										.FindByAll(" Where iCveMotivo = "
												+ vEXPExamCat.getICveMotivo());
								if (vcGrlMotivo.size() > 0) {
									for (int x = 0; x < vcGrlMotivo.size(); x++) {
										vGRLMotivo = (TVGRLMotivo) vcGrlMotivo
												.get(x);
										if (vGRLMotivo.getLPago() == 0) {
											fAccepted = true; // Esto es por el
																// momento DEBE SER
																// FALSE
										} else {
											fAccepted = true;
										}
									}
								}
		
								StringBuffer cMotivo = new StringBuffer();
								StringBuffer cMdoTrans = new StringBuffer();
								StringBuffer cWhere = new StringBuffer();
								StringBuffer cWhere2 = new StringBuffer();
								StringBuffer MotivoTransporte = new StringBuffer();
		
								// Busca los Motivos y Modos de Transporte y crea
								// cadenas de caracteres
								vcEXPExamCat = dEXPExamCat
										.FindByAll(" where iCveExpediente = "
												+ cExpediente
												+ " and iNumExamen = "
												+ cExamen);
								for (int i = 0; i < vcEXPExamCat.size(); i++) {
									vEXPExamCat = (TVEXPExamCat) vcEXPExamCat
											.get(i);
									cMotivo.append(" iCveMotivo = "
											+ vEXPExamCat.getICveMotivo());
									cMdoTrans.append(" iCveMdoTrans = "
											+ vEXPExamCat.getICveMdoTrans());
									MotivoTransporte.append("( iCveMotivo = "+ vEXPExamCat.getICveMotivo()+" and "+
											" iCveMdoTrans = "+ vEXPExamCat.getICveMdoTrans()+")");
					
									if (i < vcEXPExamCat.size() - 1) {
										cMotivo.append(" or ");
										cMdoTrans.append(" or ");
										MotivoTransporte.append(" or ");
									}
									
									
								}
		
								// Crea el where
								cWhere.append(" where S.iCveProceso  = 1"
										+ " and ("
										+ cMotivo
										+ ")"
										+ " and ("
										+ cMdoTrans
										+ ")"
										+ " and ( SN.cGenero = 'A' or SN.cGenero = '"
										+ cGenero + "')"
										+ " and S.iCveServicio = "+Servicio);
								
								cWhere2.append(" where S.iCveProceso  = 1"
										+ " and ("
										+ MotivoTransporte
										+ ")"
										+ " and ( SN.cGenero = 'A' or SN.cGenero = '"
										+ cGenero + "')"
										+ " and S.iCveServicio = "+Servicio);
								
								SQLUpdate = "UPDATE EXPSERVICIO SET " +
										"TSINICIO = (CURRENT TIMESTAMP) " +
										"WHERE ICVEEXPEDIENTE = "+cExpediente+" AND " +
										"INUMEXAMEN = "+cExamen+" AND ICVESERVICIO = "+Servicio;
		
								vcMEDSintExamen = dMEDSintExamen.FindAllExam2(cWhere);
								/*
								vcMEDSintExamenServ = dMEDSintExamen
										.FindAllServ2(cWhere);*/
								/*vcMEDSintExamenRama = dMEDSintExamen
										.FindAllRama2(cWhere);*/
								vcEXPServicio = new Vector();
		
								// Crea tres vectores para Insertar en EXPServicio,
								// EXPRama, EXPResultado
		/*
								for (int i = 0; i < vcMEDSintExamenServ.size(); i++) {												
									vMEDSintExamen = (TVMEDSintExamen) vcMEDSintExamenServ.get(i);			
									vEXPServicio = new TVEXPServicio();
									vEXPServicio
											.setICveExpediente(Integer.parseInt(cExpediente));
									vEXPServicio.setINumExamen(Integer
											.parseInt(cExamen));
									
									vEXPServicio.setICveServicio(vMEDSintExamen
											.getICveServicio());
									vEXPServicio.setDtFin(dtCampo);
									vEXPServicio.setDtInicio(dtCampo);
									vEXPServicio.setLConcluido(0);
									vcEXPServicio.addElement(vEXPServicio);
								}
		
								for (int i = 0; i < vcMEDSintExamenRama.size(); i++) {
									vMEDSintExamen = (TVMEDSintExamen) vcMEDSintExamenRama
											.get(i);
									vEXPRama = new TVEXPRama();
									vEXPRama.setICveExpediente(Integer.parseInt(cExpediente));
									vEXPRama.setINumExamen(Integer.parseInt(cExamen));
									vEXPRama.setICveServicio(vMEDSintExamen
											.getICveServicio());
									vEXPRama.setICveRama(vMEDSintExamen
											.getICveRama());
									vEXPRama.setDtFin(dtCampo);
									vEXPRama.setDtInicio(dtCampo);
									vEXPRama.setIConcluido(0);
									vcEXPRama.addElement(vEXPRama);
								}*/
										
								for (int i = 0; i < vcMEDSintExamen.size(); i++) {
									vMEDSintExamen = (TVMEDSintExamen) vcMEDSintExamen
											.get(i);
									vEXPResultado = new TVEXPResultado();
									vEXPResultado
											.setICveExpediente(Integer.parseInt(
													cExpediente,
													10));
									vEXPResultado
											.setINumExamen(Integer.parseInt(cExamen, 10));
									vEXPResultado.setICveServicio(vMEDSintExamen
											.getICveServicio());
									vEXPResultado.setICveRama(vMEDSintExamen
											.getICveRama());
									vEXPResultado.setICveSintoma(vMEDSintExamen
											.getICveSintoma());
									vEXPResultado.setCValRef(vMEDSintExamen
											.getCValRef());
									vcEXPResultado.addElement(vEXPResultado);
								}
								
								
////#######################Nueva implementacion 1 insert					
								/*dEXPExamAplica.GeneraExamen(vcEXPServicio,
										vcEXPRama, vcEXPResultado);*/
								dEXPExamAplica.GeneraExamen2(vcEXPResultado,SQLUpdate);
								
								System.out.println("Genero los resultados correctamente");
		
								// Thread.sleep(50000);
		
////#######################Nueva implementacion 1 insert								
								//int sint = dMEDSintExamen.FindAllExam2Count(cWhere);
								int sint = dMEDSintExamen.FindAllExam3Count(cWhere);
								
								
								/*int serv = dMEDSintExamen.FindAllServ2Count(cWhere);
								int rama = dMEDSintExamen.FindAllRama2Count(cWhere);
								int exaserv = dEXPExamAplica
										.RegresaInt("SELECT COUNT(ICVEEXPEDIENTE) FROM EXPSERVICIO WHERE iCveExpediente = "
												+ cExpediente
												+ " and iNumExamen = "
												+ cExamen
												+ "");
								int exarama = dEXPExamAplica
										.RegresaInt("SELECT COUNT(ICVEEXPEDIENTE) FROM EXPRAMA WHERE iCveExpediente = "
												+ cExpediente
												+ " and iNumExamen = "
												+ cExamen
												+ "");
*/
								int exasint = dEXPExamAplica
										.RegresaInt("SELECT COUNT(ICVEEXPEDIENTE) FROM EXPRESULTADO WHERE iCveExpediente = "
												+ cExpediente
												+ " and iNumExamen = "
												+ cExamen
												+ "");
		
								 System.out.println(""+sint +">"+ exasint);
								 System.out.println("Genero las consultas ");
								 
								if (sint > exasint) {
								 System.out.println("Se detecto Servicio mal generado y sera corregido automaticamente");
////#######################Nueva implementacion 1 insert					
									/*dEXPExamAplica.GeneraExamen(vcEXPServicio,
											vcEXPRama, vcEXPResultado);*/
									dEXPExamAplica.GeneraExamen2(vcEXPResultado,SQLUpdate);
									
									System.out.println("Se genero nuevamente el vale de Servicio mal generado");
								}
			
								}
							}
			
							// } (int w = 0; w < vcExpExamCat.size(); w++)
						}
			
						if (vcGrlUniMed.size() > 0) {
							dEXPExamAplica.updateArchivo(null, objjsp);
			//#######################Nueva implementacion 1 insert
							//dEXPServicio.updateSQL(null, SQLUpdate);
						}
					}
					// dEXPExamAplica.update(null, this.getInputs());
		} catch (Exception ex) {
			error("Error al insertar el registro", ex);
		} finally {
			//super.CargandoSintomas();
		}
		return fAccepted;
	}
	
	/**
	 * Metodo getInputs
	 */
	public Object Inputs(String cExpediente, String cNumexamen) throws DAOException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		/*
		 * Calcula Fecha Actual
		 */
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		String dFechaActual = "";
		TFechas dtFecha = new TFechas();
		dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha, "/");
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		TVEXPExamAplica vEXPExamAplica = null;
		TVEXPExamAplica vEXPExamAplica2 = new TVEXPExamAplica();
		Vector vcPersona = new Vector();
		String cWhere = " Where EXPExamAplica.iCveExpediente = "+cExpediente
					+ " and EXPExamAplica.iNumExamen = "+cNumexamen +" ";
		try {
			vcPersona = dEXPExamAplica.FindByAll(cWhere);
			if(vcPersona.size() > 0){
				  for(int i=0;i<vcPersona.size();i++){
					  vEXPExamAplica = (TVEXPExamAplica) vcPersona.get(i);
					  //System.out.println("Vector = "+vEXPExamAplica.getICveExpediente());
						vEXPExamAplica2.setICveExpediente(vEXPExamAplica.getICveExpediente());
						vEXPExamAplica2.setINumExamen(vEXPExamAplica.getINumExamen());
						vEXPExamAplica2.setICveUniMed(vEXPExamAplica.getICveUniMed());
						vEXPExamAplica2.setICveProceso(vEXPExamAplica.getICveProceso());
						vEXPExamAplica2.setICveModulo(vEXPExamAplica.getICveModulo());
						vEXPExamAplica2.setICvePersonal(vEXPExamAplica.getICvePersonal());
						vEXPExamAplica2.setDtSolicitado(vEXPExamAplica.getDtSolicitado());
						vEXPExamAplica2.setIFolioEs(vEXPExamAplica.getIFolioEs());
						vEXPExamAplica2.setDtAplicacion(tfCampo.TodaySQL());
						vEXPExamAplica2.setDtConcluido(vEXPExamAplica.getDtConcluido());
						vEXPExamAplica2.setLIniciado(vEXPExamAplica.getLIniciado());
						vEXPExamAplica2.setLDictaminado(vEXPExamAplica.getLDictaminado());
						vEXPExamAplica2.setLInterConsulta(vEXPExamAplica.getLInterConsulta());
						vEXPExamAplica2.setLInterConcluye(vEXPExamAplica.getLInterConcluye());
						vEXPExamAplica2.setLConcluido(vEXPExamAplica.getLConcluido());
						vEXPExamAplica2.setDtDictamen(vEXPExamAplica.getDtDictamen());
						vEXPExamAplica2.setDtEntregaRes(vEXPExamAplica.getDtEntregaRes());
						vEXPExamAplica2.setDtArchivado(vEXPExamAplica.getDtArchivado());
						vEXPExamAplica2.setICveNumEmpresa(vEXPExamAplica.getICveNumEmpresa());
				}
			}
		} catch (Exception ex) {
			error("getInputs", ex);
		}
		return vEXPExamAplica2;
	}

	
	
	
}


