package gob.sct.medprev.util.reglas;  

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.micper.excepciones.DAOException;
import com.micper.sql.DbConnection;

import gob.sct.medprev.dao.TDEXPDictamenServ;
import gob.sct.medprev.dao.TDEXPExamAplica;
import gob.sct.medprev.dao.TDEXPRegSint;
import gob.sct.medprev.vo.TVMEDRespSint;

public class RImpedirExamenMedico {

	public boolean DiagnosticoDeDiabetesMellitusInsulinodependiente(String iCveExpediente) throws DAOException{
		int INSULINODEPENDIENTE = 0;
		boolean DIAGNOSTICODEDIABETESMELLITUS = false;
		try{
			TDEXPExamAplica dAplica = new TDEXPExamAplica();
			// Verificar que haya contestado SI a la pregunta INSULINODEPENDIENTE
			INSULINODEPENDIENTE = dAplica.RegresaInt(" "
													+ " SELECT ICVEEXPEDIENTE "
													+ "FROM EXPREGSIN "
													+ "WHERE "
													+ "ICVESERVICIO = 50 AND "
													+ "ICVERAMA = 1 AND "
													+ "ICVESINTOMA = 91 AND "
													+ "ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ "INUMEXAMEN =(SELECT MAX(INUMEXAMEN) "
													+ "FROM EXPEXAMAPLICA "
													+ "WHERE "
													+ "ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ "ICVEPROCESO = 1 )");
			if(INSULINODEPENDIENTE > 0){
				DIAGNOSTICODEDIABETESMELLITUS = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return DIAGNOSTICODEDIABETESMELLITUS;
	}
	

	public int NumeroDeReglasDeNoAptitudActivas(String iCveExpediente) throws DAOException{
		int NumeroDeReglasDeNoAptitudActivas = 0;
		try{
			TDEXPRegSint dEXPRegSint = new TDEXPRegSint();
			// Verificar que haya contestado SI a la pregunta INSULINODEPENDIENTE
			NumeroDeReglasDeNoAptitudActivas = dEXPRegSint.FindByCount(" "
													+ " SELECT Count (ICVEEXPEDIENTE) "
													+ "FROM EXPREGSIN "
													+ "WHERE "
													//+ "ICVESERVICIO = 50 AND "
													//+ "ICVERAMA = 1 AND "
													//+ "ICVESINTOMA = 91 AND "
													+ "ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ "INUMEXAMEN =(SELECT MAX(INUMEXAMEN) "
													+ "FROM EXPEXAMAPLICA "
													+ "WHERE "
													+ "ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ "ICVEPROCESO = 1 )");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return NumeroDeReglasDeNoAptitudActivas;
	}
	
	
	
	
	
	public boolean DiagnosticoDeUsoDeAnticoagulantes(String iCveExpediente) throws DAOException{
		int Anticoagulantes = 0;
		boolean DIAGNOSTICODEAnticoagulantes = false;
		try{
			TDEXPExamAplica dAplica = new TDEXPExamAplica();
			// Verificar que haya contestado SI a la pregunta INSULINODEPENDIENTE
			Anticoagulantes = dAplica.RegresaInt(" "
													+ " SELECT ICVEEXPEDIENTE "
													+ "FROM EXPREGSIN "
													+ "WHERE "
													+ "ICVESERVICIO = 48 AND "
													+ "ICVERAMA = 1 AND "
													+ "ICVESINTOMA = 21 AND "
													+ "ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ "INUMEXAMEN =(SELECT MAX(INUMEXAMEN) "
													+ "FROM EXPEXAMAPLICA "
													+ "WHERE "
													+ "ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ "ICVEPROCESO = 1 )");
			if(Anticoagulantes > 0){
				DIAGNOSTICODEAnticoagulantes = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return DIAGNOSTICODEAnticoagulantes;
	}

	public boolean ExamenEnCDIoCenma(String iCveExpediente, String iNumExamen) throws DAOException{
		boolean CDI = false;
		int resultado = 0;
		try{
			TDEXPExamAplica dAplica = new TDEXPExamAplica();
			// Verificar que haya contestado SI a la pregunta INSULINODEPENDIENTE
			resultado = dAplica.RegresaInt(" "
													+ "SELECT INUMEXAMEN "
													+ "FROM EXPEXAMAPLICA "
													+ "WHERE "
													+ "ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ "INUMEXAMEN = "+iNumExamen +" AND "
													+ "ICVEPROCESO = 1 AND "
													+ "ICVEUNIMED = 1 AND "
													+ "ICVEMODULO IN (1,2)");
			if(resultado > 0){
				CDI = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return CDI;
	}
	
	
	/**
	 * Metodo DeterminaVigenciaInsulinodependiente
	 */
	@SuppressWarnings("finally")
	public java.sql.Date DeterminaVigenciaInsulinodependiente() throws DAOException {
		java.sql.Date dtFinV = null;
		TDEXPDictamenServ dDicServ = new TDEXPDictamenServ();
		int iTmpVigencia = 0;
		int ianosVigencia = 0;
		int iMesesVigencia = 0;
		String str_date = "";
		Date date;
		DateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
				iTmpVigencia = 6;
					ianosVigencia = iTmpVigencia / 12;
					int ano = iTmpVigencia / 12;
					iMesesVigencia = iTmpVigencia - (ano * 12);
					if (ianosVigencia > 0 && iMesesVigencia > 0) {
						System.out.println("OP 1");
						System.out.println(ianosVigencia+" > 0 && "+iMesesVigencia+" > 0");
							str_date = dDicServ.SenFindBy("select current date +" + ianosVigencia + "  YEARS + "
									+ iMesesVigencia + " MONTHS from sysibm.sysdummy1");
							// Convierte String a Date
							date = (Date) formatter.parse(str_date);
							java.util.Date y2 = new java.util.Date();
							y2 = date;
							java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
							dtFinV = CalVigencia;
					} else {
						if (ianosVigencia > 0) {
							System.out.println("OP 2");
								str_date = dDicServ.SenFindBy(
										"select current date +" + ianosVigencia + "  YEARS from sysibm.sysdummy1");
								// Convierte String a Date
								date = (Date) formatter.parse(str_date);
								java.util.Date y2 = new java.util.Date();
								y2 = date;
								java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
								dtFinV = CalVigencia;
						}
						if (iMesesVigencia > 0) {
							System.out.println("OP 3");
								str_date = dDicServ.SenFindBy(
										"select current date +" + iMesesVigencia + " MONTHS from sysibm.sysdummy1");
								// Convierte String a Date
								date = (Date) formatter.parse(str_date);
								java.util.Date y2 = new java.util.Date();
								y2 = date;
								java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
								dtFinV = CalVigencia;
						}
					}

		 System.out.println("dtFinV = "+dtFinV);
		} catch (Exception ex) {
			System.out.println("DeterminaVigenciaInsulinodependiente "+ ex);
			throw new DAOException("FindByAll");
		} finally {
			/// Validar reglas con respecto a los valores en EXPRESULTADO
			return dtFinV;
		}

	}
	

	/**
	 * Metodo DeterminaVigenciaInsulinodependiente
	 */
	@SuppressWarnings("finally")
	public java.sql.Date DeterminaVigenciaAnticoagulante() throws DAOException {
		java.sql.Date dtFinV = null;
		TDEXPDictamenServ dDicServ = new TDEXPDictamenServ();
		int iTmpVigencia = 0;
		int ianosVigencia = 0;
		int iMesesVigencia = 0;
		String str_date = "";
		Date date;
		DateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
				iTmpVigencia = 6;
					ianosVigencia = iTmpVigencia / 12;
					int ano = iTmpVigencia / 12;
					iMesesVigencia = iTmpVigencia - (ano * 12);
					if (ianosVigencia > 0 && iMesesVigencia > 0) {
						System.out.println("OP 1");
						System.out.println(ianosVigencia+" > 0 && "+iMesesVigencia+" > 0");
							str_date = dDicServ.SenFindBy("select current date +" + ianosVigencia + "  YEARS + "
									+ iMesesVigencia + " MONTHS from sysibm.sysdummy1");
							// Convierte String a Date
							date = (Date) formatter.parse(str_date);
							java.util.Date y2 = new java.util.Date();
							y2 = date;
							java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
							dtFinV = CalVigencia;
					} else {
						if (ianosVigencia > 0) {
							System.out.println("OP 2");
								str_date = dDicServ.SenFindBy(
										"select current date +" + ianosVigencia + "  YEARS from sysibm.sysdummy1");
								// Convierte String a Date
								date = (Date) formatter.parse(str_date);
								java.util.Date y2 = new java.util.Date();
								y2 = date;
								java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
								dtFinV = CalVigencia;
						}
						if (iMesesVigencia > 0) {
							System.out.println("OP 3");
								str_date = dDicServ.SenFindBy(
										"select current date +" + iMesesVigencia + " MONTHS from sysibm.sysdummy1");
								// Convierte String a Date
								date = (Date) formatter.parse(str_date);
								java.util.Date y2 = new java.util.Date();
								y2 = date;
								java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
								dtFinV = CalVigencia;
						}
					}

		 System.out.println("dtFinV = "+dtFinV);
		} catch (Exception ex) {
			System.out.println("DeterminaVigenciaAnticoagulante "+ ex);
			throw new DAOException("FindByAll");
		} finally {
			/// Validar reglas con respecto a los valores en EXPRESULTADO
			return dtFinV;
		}

	}
	
}
