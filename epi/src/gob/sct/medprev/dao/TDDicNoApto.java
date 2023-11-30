package gob.sct.medprev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.*;
import com.micper.seguridad.vo.*;
import com.micper.sql.DAOBase;
import com.micper.sql.DbConnection;
import com.micper.util.TFechas;

import gob.sct.medprev.vo.*;
import gob.sct.cis.dao.*;

/**
 * 
 * @author AG SA
 */

public class TDDicNoApto extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private String dataSourceName2 = VParametros.getPropEspecifica("ConDBCis");
	private String dataSourceName3 = VParametros.getPropEspecifica("ConDBSIAF");
	private static SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

	public TDDicNoApto() {

	}

	/**
	 * Metodo Verificar si ya se cerraron los examenes.
	 */
	public int VerCNALogin(String dtFecha) {
		int count = 0;
		int count2 = 0;
		String Consulta = "";
		TDCARGACITAS dCARGACITAS = new TDCARGACITAS();
		Calendar calendario = Calendar.getInstance();
		if (dtFecha != null) {
			String fecha = dtFecha.toString() + "/";
			String fecha2 = fecha.subSequence(6, 10) + "-"
					+ fecha.subSequence(3, 5) + "-" + fecha.subSequence(0, 2);
			Consulta = "SELECT COUNT(*) FROM EXPBITMOD "
					+ "WHERE  IOPERACION = 13 AND CDESCRIPCION = 'CERRAR EXAMENES NO APTOS = "
					+ fecha2 + "'";
			try {
				count = dCARGACITAS.RegresaInt(Consulta);
				if (count > 0) {
					count2 = dCARGACITAS.RegresaInt(Consulta
							+ " and ldictamen = 1");
					int minutos = calendario.get(Calendar.MINUTE);

					if (count2 == 0
							&& (minutos == 00 || minutos == 15 || minutos == 30 || minutos == 45))
						count = 0;
				}

			} catch (Exception ex) {
				// System.out.println("Error en la obtension de datos de la carga de citas");
				warn("VerCNALogin", ex);
			}
		}
		return count;
	}

	/**
	 * Metodo que realiza el cierre de no aptos.
	 */
	public String CierraExaNoApto(int usr, String dtFecha, String dtFecha2,
			String dtFecha3) {
		//System.out.println("CierraExaNoApto");
		int count = 0;
		String Consulta = "";
		String Consulta2 = "";
		String Resultado = "";
		TDCARGACITAS dCARGACITAS = new TDCARGACITAS();
		if (dtFecha != null) {
			Consulta = dtFecha;
			Consulta2 = dtFecha3;
			// System.out.println("Consulta = "+Consulta);
			// System.out.println("dtFecha = "+dtFecha);
			// System.out.println("buscando en ExpBitMod");
			String sQL = "SELECT COUNT(*) FROM EXPBITMOD WHERE IOPERACION = 13 AND LDICTAMEN = 1 AND CDESCRIPCION = 'CERRAR EXAMENES NO APTOS = "
					+ dtFecha2 + "'";
			try {
				if (dCARGACITAS.RegresaInt(sQL) == 0) {
					// System.out.println("Se cumplio la condicion, cerrando examenes");
					this.insert(null, Consulta, Consulta2);

					sQL = "SELECT count (A.ICVEEXPEDIENTE) "
							+ "FROM EXPEXAMAPLICA AS A, EXPEXAMCAT AS C "
							+ "WHERE "
							+ "A.ICVEEXPEDIENTE = C.ICVEEXPEDIENTE AND "
							+ "A.INUMEXAMEN = C.INUMEXAMEN AND "
							+ "A.LDICTAMINADO = 0 AND "
							+ "A.ICVEPROCESO = 1 AND "
							+ "A.ICVEUNIMED < 107 AND " 
							+ "(A.DTSOLICITADO = '"+ Consulta + "' OR "
							+ "A.DTSOLICITADO = (DATE('"+ Consulta + "') - 1 DAY)  OR "
							+ "A.DTSOLICITADO = (DATE('"+ Consulta + "') - 2 DAY) )";
					int registros = dCARGACITAS.RegresaInt(sQL);
					//System.out.println(sQL);
					
					sQL = "SELECT count (A.ICVEEXPEDIENTE) "
							+ "FROM EXPEXAMAPLICA AS A, EXPEXAMCAT AS C "
							+ "WHERE "
							+ "A.ICVEEXPEDIENTE = C.ICVEEXPEDIENTE AND "
							+ "A.INUMEXAMEN = C.INUMEXAMEN AND "
							+ "A.LDICTAMINADO = 0 AND "
							+ "A.ICVEPROCESO = 1 AND "
							+ "A.ICVEUNIMED = 107 AND " 
							+ "(A.DTSOLICITADO = '"+ Consulta2 + "' OR "
									+ "A.DTSOLICITADO = (DATE('"+ Consulta2 + "') - 1 DAY)  OR "
									+ "A.DTSOLICITADO = (DATE('"+ Consulta2 + "') - 2 DAY) )";
					int registros2 = dCARGACITAS.RegresaInt(sQL);
					//System.out.println(sQL);
					
					// System.out.println("Quedan "+registros+" de unidades por cerrar como NO APTO y "+registros+" por cerrar de terceros");

					if ((registros + registros2) > 0) {
						String cSQLU = "UPDATE EXPBITMOD SET LDICTAMEN = 0 "
								+ "WHERE "
								+ "IOPERACION = 13 AND DATE (DTREALIZADO) = CURRENT DATE";
						dCARGACITAS.Sentencia(cSQLU);
					}
					if ((registros + registros2) == 0) {
						String cSQLU = "UPDATE EXPBITMOD SET LDICTAMEN = 1 "
								+ "WHERE "
								+ "IOPERACION = 13 AND DATE (DTREALIZADO) = CURRENT DATE";
						dCARGACITAS.Sentencia(cSQLU);
					}
				}
			} catch (Exception e) {
				count = 0;
				warn("CierraExaNoApto", e);
			}
		}
		//System.out.println(" Fin CierraExaNoApto");
		return Resultado;
	}

	/**
	 * Mï¿½?Â©todo Insert
	 */
	public String insert(Connection conGral, String fecha, String fecha2)
			throws DAOException {
		 //System.out.println("Insert");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmtValida = null;
		ResultSet rsetValida = null;
		Vector vcEXPExamCat = new Vector();
		Vector vcEXPExamCat2 = new Vector();
		TVEXPExamCat vEXPExamCat;
		TVEXPExamCat vEXPExamCat2;
		TDCARGACITAS dCARGACITAS = new TDCARGACITAS();
		Calendar calendar = Calendar.getInstance();
		TVUsuario usuario;
		int contador = 0;
		int contador2 = 0;
		// usuario = (TVUsuario)request.getSession().getAttribute("UsrID");

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			String Timestamp = "";

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			vcEXPExamCat = this.FindByAll(fecha, "A.ICVEUNIMED < 107 ");
			vcEXPExamCat2 = this.FindByAll(fecha2, "A.ICVEUNIMED = 107 ");

			if (vcEXPExamCat.size() > 0) {

				// CERRANDO EXAMENES DE UNIDADES MEDICAS
				for (int i = 0; i < vcEXPExamCat.size(); i++) {
					ResultSet rset = null;
					PreparedStatement pstmt = null;
					PreparedStatement pstmtFind = null;
					vEXPExamCat = (TVEXPExamCat) vcEXPExamCat.get(i);

					// //EXPSERVICIO////
					if (vEXPExamCat.getLPago() == 0) {
						cSQL = "INSERT INTO EXPSERVICIO (ICVEEXPEDIENTE,INUMEXAMEN,ICVESERVICIO,LCONCLUIDO,ICVEUSUNOTAMED,ICVEUSUAPLICA,CNOTAMEDICA,DTINICIO,DTFIN) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ","
								+ vEXPExamCat.getINumExamen()
								+ ",44,1,12065,12065,'Artículo 12.- Se emitirá dictamen de no aptitud psicofísica al personal que no reúna las condiciones obligatorias "
								+ "e indispensables enunciadas en el requisito médico correspondiente',CURRENT DATE,CURRENT DATE)";
						dCARGACITAS.Sentencia(cSQL);
					} else {
						cSQL = "UPDATE EXPSERVICIO SET LCONCLUIDO = 1, ICVEUSUNOTAMED = 12065, ICVEUSUAPLICA = 12065, CNOTAMEDICA = 'Artículo 12.- Se emitirá dictamen"
								+ " de no aptitud psicofísica al personal que no reúna las condiciones obligatorias e indispensables enunciadas en el requisito médico correspondiente' "
								+ "WHERE ICVEEXPEDIENTE = "
								+ vEXPExamCat.getICveExpediente()
								+ " AND INUMEXAMEN = "
								+ vEXPExamCat.getINumExamen()
								+ " AND ICVESERVICIO = 44";
						dCARGACITAS.Sentencia(cSQL);
					}
					if (vEXPExamCat.getLPago() == 0) {
						cSQL = "INSERT INTO EXPSERVICIO (ICVEEXPEDIENTE,INUMEXAMEN,ICVESERVICIO,LCONCLUIDO,ICVEUSUNOTAMED,ICVEUSUAPLICA,CNOTAMEDICA,DTINICIO,DTFIN) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ","
								+ vEXPExamCat.getINumExamen()
								+ ",31,1,12065,12065,'Artículo 12.- Se emitirá dictamen de no aptitud psicofísica al personal que no reúna las condiciones obligatorias "
								+ "e indispensables enunciadas en el requisito médico correspondiente',CURRENT DATE,CURRENT DATE)";
						dCARGACITAS.Sentencia(cSQL);
					} else {
						cSQL = "UPDATE EXPSERVICIO SET LCONCLUIDO = 1, ICVEUSUNOTAMED = 12065, ICVEUSUAPLICA = 12065, CNOTAMEDICA = 'Artículo 12.- Se emitirá dictamen"
								+ " de no aptitud psicofísica al personal que no reúna las condiciones obligatorias e indispensables enunciadas en el requisito médico correspondiente' "
								+ "WHERE ICVEEXPEDIENTE = "
								+ vEXPExamCat.getICveExpediente()
								+ " AND INUMEXAMEN = "
								+ vEXPExamCat.getINumExamen()
								+ " AND ICVESERVICIO = 31";
						dCARGACITAS.Sentencia(cSQL);
					}

					// //EXPDICTAMENSERV////
					String sQL = "SELECT COUNT(*) FROM EXPDICTAMENSERV WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat.getINumExamen()
							+ " AND ICVESERVICIO = 31 "
							+ "AND ICVEMDOTRANS = "
							+ vEXPExamCat.getICveMdoTrans()
							+ " AND ICVECATEGORIA = "
							+ vEXPExamCat.getICveCategoria() + "";
					if (dCARGACITAS.RegresaInt(sQL) == 0) {
						cSQL = "INSERT INTO EXPDICTAMENSERV (ICVEEXPEDIENTE,INUMEXAMEN,ICVEMDOTRANS,ICVECATEGORIA,ICVESERVICIO,LDICTAMEN) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ","
								+ vEXPExamCat.getINumExamen()
								+ ","
								+ vEXPExamCat.getICveMdoTrans()
								+ ","
								+ vEXPExamCat.getICveCategoria() + ",31,0)";
						dCARGACITAS.Sentencia(cSQL);
					} else {
						cSQL = "UPDATE EXPDICTAMENSERV SET LDICTAMEN = 0 WHERE ICVEEXPEDIENTE = "
								+ vEXPExamCat.getICveExpediente()
								+ " AND INUMEXAMEN = "
								+ vEXPExamCat.getINumExamen()
								+ " AND ICVESERVICIO = 31";
						dCARGACITAS.Sentencia(cSQL);
					}

					// //EXPFUNDICTAMEN////
					sQL = "SELECT COUNT(*) FROM EXPFUNDICTAMEN WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat.getINumExamen()
							+ " AND ICVESERVICIO = 31";
					if (dCARGACITAS.RegresaInt(sQL) == 0) {
						cSQL = "INSERT INTO EXPFUNDICTAMEN (ICVEEXPEDIENTE,INUMEXAMEN,ICVESERVICIO,CFUNDAMENTACION,LDICTAMENSERV, TIDIAGNOSTICO) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ","
								+ vEXPExamCat.getINumExamen()
								+ ",31,'Artículo 12.- Se emitirá dictamen de no aptitud psicofísica al personal que no reúna las condiciones obligatorias e indispensables "
								+ "enunciadas en el requisito médico correspondiente',0,CURRENT TIMESTAMP)";
						dCARGACITAS.Sentencia(cSQL);
					}

					// //EXPEXAMAPLICA////
					cSQL = "UPDATE EXPEXAMAPLICA SET LDICTAMINADO = 1, DTDICTAMEN = CURRENT DATE - 1 DAYS, ICVEUSUDICTAMEN = 12065, TFINEXA = CURRENT TIMESTAMP "
							+ "WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat.getINumExamen();
					dCARGACITAS.Sentencia(cSQL);

					// //EXPEXAMCAT////
					cSQL = "UPDATE EXPEXAMCAT SET LDICTAMEN = 0, DTINICIOVIG = CURRENT DATE - 1 DAYS, DTFINVIG = CURRENT DATE - 1 DAYS, LDITAMEM = 12065, DTDICTAMINADO = CURRENT DATE - 1 DAYS "
							+ "WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat.getINumExamen();
					// System.out.println(cSQL);
					dCARGACITAS.Sentencia(cSQL);

					// //PERLICENCIA////
					cSQL = "SELECT COUNT(*) FROM PERLICENCIA WHERE ICVEPERSONAL = "
							+ vEXPExamCat.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat.getINumExamen()
							+ " "
							+ "AND ICVEMDOTRANS = "
							+ vEXPExamCat.getICveMdoTrans()
							+ " AND ICVECATEGORIA = "
							+ vEXPExamCat.getICveCategoria() + "";
					// System.out.println(cSQL);
					if (dCARGACITAS.RegresaInt(cSQL) == 0) {
						cSQL = "INSERT INTO PERLICENCIA (ICVEPERSONAL,ICVEMDOTRANS,ICVECATEGORIA,CCONSTANCIA,INUMEXAMEN,LDICTAMEN,CLICENCIA,DTINIVIGENCIA,DTFINVIGENCIA) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ","
								+ vEXPExamCat.getICveMdoTrans()
								+ ","
								+ vEXPExamCat.getICveCategoria()
								+ ",'',"
								+ vEXPExamCat.getINumExamen()
								+ ",0,'',CURRENT DATE - 1 DAYS, CURRENT DATE - 1 DAYS) ";
						// System.out.println(cSQL);
						dCARGACITAS.Sentencia(cSQL);
					}

					// //MEDINHABILITA////
					int AccionInhabilita = 0;
					try {
						AccionInhabilita = Integer.parseInt(VParametros
								.getPropEspecifica("ActivaInhabilitacion"));
					} catch (NumberFormatException nfe) {
						AccionInhabilita = 0;
					}
					if (AccionInhabilita == 1) {
						cSQL = "INSERT INTO MEDINHABILITA (ICVEPERSONAL,ICVEMOTIVOINH,INICIOINH,FININH,ICVEUSUINH,OBSERVACIONES) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ",50,CURRENT TIMESTAMP,(select current timestamp + "
								+ VParametros
										.getPropEspecifica("TiempoInhabilita")
								+ " YEARS from sysibm.sysdummy1),"
								+ "12065,'Artículo 12.- Se emitirá dictamen de no aptitud psicofísica al personal que no reúna las condiciones obligatorias "
								+ "e indispensables enunciadas en el requisito médico correspondiente')";
						dCARGACITAS.Sentencia(cSQL);
					}
					contador++;
					// System.out.println("contador = "+contador);

				}// se cierra el ciclo unidades
			}

			if (vcEXPExamCat2.size() > 0) {
				// CERRANDO EXAMENES DE TERCEROS PARTICULARES
				for (int i = 0; i < vcEXPExamCat2.size(); i++) {
					ResultSet rset = null;
					PreparedStatement pstmt = null;
					PreparedStatement pstmtFind = null;
					vEXPExamCat2 = (TVEXPExamCat) vcEXPExamCat2.get(i);

					// //EXPSERVICIO////
					if (vEXPExamCat2.getLPago() == 0) {
						cSQL = "INSERT INTO EXPSERVICIO (ICVEEXPEDIENTE,INUMEXAMEN,ICVESERVICIO,LCONCLUIDO,ICVEUSUNOTAMED,ICVEUSUAPLICA,CNOTAMEDICA,DTINICIO,DTFIN) "
								+ "VALUES("
								+ vEXPExamCat2.getICveExpediente()
								+ ","
								+ vEXPExamCat2.getINumExamen()
								+ ",44,1,12065,12065,'Artículo 12.- Se emitirá dictamen de no aptitud psicofísica al personal que no reúna las condiciones obligatorias "
								+ "e indispensables enunciadas en el requisito médico correspondiente',CURRENT DATE,CURRENT DATE)";
						dCARGACITAS.Sentencia(cSQL);
					} else {
						cSQL = "UPDATE EXPSERVICIO SET LCONCLUIDO = 1, ICVEUSUNOTAMED = 12065, ICVEUSUAPLICA = 12065, CNOTAMEDICA = 'Artículo 12.- Se emitirá dictamen"
								+ " de no aptitud psicofísica al personal que no reúna las condiciones obligatorias e indispensables enunciadas en el requisito médico correspondiente' "
								+ "WHERE ICVEEXPEDIENTE = "
								+ vEXPExamCat2.getICveExpediente()
								+ " AND INUMEXAMEN = "
								+ vEXPExamCat2.getINumExamen()
								+ " AND ICVESERVICIO = 44";
						dCARGACITAS.Sentencia(cSQL);
					}
					if (vEXPExamCat2.getLPago() == 0) {
						cSQL = "INSERT INTO EXPSERVICIO (ICVEEXPEDIENTE,INUMEXAMEN,ICVESERVICIO,LCONCLUIDO,ICVEUSUNOTAMED,ICVEUSUAPLICA,CNOTAMEDICA,DTINICIO,DTFIN) "
								+ "VALUES("
								+ vEXPExamCat2.getICveExpediente()
								+ ","
								+ vEXPExamCat2.getINumExamen()
								+ ",31,1,12065,12065,'Artículo 12.- Se emitirá dictamen de no aptitud psicofísica al personal que no reúna las condiciones obligatorias "
								+ "e indispensables enunciadas en el requisito médico correspondiente',CURRENT DATE,CURRENT DATE)";
						dCARGACITAS.Sentencia(cSQL);
					} else {
						cSQL = "UPDATE EXPSERVICIO SET LCONCLUIDO = 1, ICVEUSUNOTAMED = 12065, ICVEUSUAPLICA = 12065, CNOTAMEDICA = 'Artículo 12.- Se emitirá dictamen"
								+ " de no aptitud psicofísica al personal que no reúna las condiciones obligatorias e indispensables enunciadas en el requisito médico correspondiente' "
								+ "WHERE ICVEEXPEDIENTE = "
								+ vEXPExamCat2.getICveExpediente()
								+ " AND INUMEXAMEN = "
								+ vEXPExamCat2.getINumExamen()
								+ " AND ICVESERVICIO = 31";
						dCARGACITAS.Sentencia(cSQL);
					}

					// //EXPDICTAMENSERV////
					String sQL = "SELECT COUNT(*) FROM EXPDICTAMENSERV WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat2.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat2.getINumExamen()
							+ " AND ICVESERVICIO = 31 "
							+ "AND ICVEMDOTRANS = "
							+ vEXPExamCat2.getICveMdoTrans()
							+ " AND ICVECATEGORIA = "
							+ vEXPExamCat2.getICveCategoria() + "";
					if (dCARGACITAS.RegresaInt(sQL) == 0) {
						cSQL = "INSERT INTO EXPDICTAMENSERV (ICVEEXPEDIENTE,INUMEXAMEN,ICVEMDOTRANS,ICVECATEGORIA,ICVESERVICIO,LDICTAMEN) "
								+ "VALUES("
								+ vEXPExamCat2.getICveExpediente()
								+ ","
								+ vEXPExamCat2.getINumExamen()
								+ ","
								+ vEXPExamCat2.getICveMdoTrans()
								+ ","
								+ vEXPExamCat2.getICveCategoria() + ",31,0)";
						dCARGACITAS.Sentencia(cSQL);
					} else {
						cSQL = "UPDATE EXPDICTAMENSERV SET LDICTAMEN = 0 WHERE ICVEEXPEDIENTE = "
								+ vEXPExamCat2.getICveExpediente()
								+ " AND INUMEXAMEN = "
								+ vEXPExamCat2.getINumExamen()
								+ " AND ICVESERVICIO = 31";
						dCARGACITAS.Sentencia(cSQL);
					}

					// //EXPFUNDICTAMEN////
					sQL = "SELECT COUNT(*) FROM EXPFUNDICTAMEN WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat2.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat2.getINumExamen()
							+ " AND ICVESERVICIO = 31";
					if (dCARGACITAS.RegresaInt(sQL) == 0) {
						cSQL = "INSERT INTO EXPFUNDICTAMEN (ICVEEXPEDIENTE,INUMEXAMEN,ICVESERVICIO,CFUNDAMENTACION,LDICTAMENSERV, TIDIAGNOSTICO) "
								+ "VALUES("
								+ vEXPExamCat2.getICveExpediente()
								+ ","
								+ vEXPExamCat2.getINumExamen()
								+ ",31,'Artículo 12.- Se emitirá dictamen de no aptitud psicofísica al personal que no reúna las condiciones obligatorias e indispensables "
								+ "enunciadas en el requisito médico correspondiente',0,CURRENT TIMESTAMP)";
						dCARGACITAS.Sentencia(cSQL);
					}

					// //EXPEXAMAPLICA////
					cSQL = "UPDATE EXPEXAMAPLICA SET LDICTAMINADO = 1, DTDICTAMEN = CURRENT DATE - 1 DAYS, ICVEUSUDICTAMEN = 12065, TFINEXA = CURRENT TIMESTAMP "
							+ "WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat2.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat2.getINumExamen();
					dCARGACITAS.Sentencia(cSQL);

					// //EXPEXAMCAT////
					cSQL = "UPDATE EXPEXAMCAT SET LDICTAMEN = 0, DTINICIOVIG = CURRENT DATE - 1 DAYS, DTFINVIG = CURRENT DATE - 1 DAYS, LDITAMEM = 12065, DTDICTAMINADO = CURRENT DATE - 1 DAYS "
							+ "WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat2.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat2.getINumExamen();
					// System.out.println(cSQL);
					dCARGACITAS.Sentencia(cSQL);

					// //PERLICENCIA////
					cSQL = "SELECT COUNT(*) FROM PERLICENCIA WHERE ICVEPERSONAL = "
							+ vEXPExamCat2.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat2.getINumExamen()
							+ " "
							+ "AND ICVEMDOTRANS = "
							+ vEXPExamCat2.getICveMdoTrans()
							+ " AND ICVECATEGORIA = "
							+ vEXPExamCat2.getICveCategoria() + "";
					// System.out.println(cSQL);
					if (dCARGACITAS.RegresaInt(cSQL) == 0) {
						cSQL = "INSERT INTO PERLICENCIA (ICVEPERSONAL,ICVEMDOTRANS,ICVECATEGORIA,CCONSTANCIA,INUMEXAMEN,LDICTAMEN,CLICENCIA,DTINIVIGENCIA,DTFINVIGENCIA) "
								+ "VALUES("
								+ vEXPExamCat2.getICveExpediente()
								+ ","
								+ vEXPExamCat2.getICveMdoTrans()
								+ ","
								+ vEXPExamCat2.getICveCategoria()
								+ ",'',"
								+ vEXPExamCat2.getINumExamen()
								+ ",0,'',CURRENT DATE - 1 DAYS, CURRENT DATE - 1 DAYS) ";
						// System.out.println(cSQL);
						dCARGACITAS.Sentencia(cSQL);
					}

					// //MEDINHABILITA////
					int AccionInhabilita = 0;
					try {
						AccionInhabilita = Integer.parseInt(VParametros
								.getPropEspecifica("ActivaInhabilitacion"));
					} catch (NumberFormatException nfe) {
						AccionInhabilita = 0;
					}
					if (AccionInhabilita == 1) {
						cSQL = "INSERT INTO MEDINHABILITA (ICVEPERSONAL,ICVEMOTIVOINH,INICIOINH,FININH,ICVEUSUINH,OBSERVACIONES) "
								+ "VALUES("
								+ vEXPExamCat2.getICveExpediente()
								+ ",50,CURRENT TIMESTAMP,(select current timestamp + "
								+ VParametros
										.getPropEspecifica("TiempoInhabilita")
								+ " YEARS from sysibm.sysdummy1),"
								+ "12065,'Artículo 12.- Se emitirá dictamen de no aptitud psicofísica al personal que no reúna las condiciones obligatorias "
								+ "e indispensables enunciadas en el requisito médico correspondiente')";
						dCARGACITAS.Sentencia(cSQL);
					}

					contador2++;
					// System.out.println("contador = "+contador);

				}// se cierra el ciclo de terceros
			}

			// /VALIDANDO SE HAYAN CERRADO TODOS LOS EXAMENES ENCONTRADOS
			// System.out.println("unidames ="+vcEXPExamCat.size()
			// +" = "+contador);
			// System.out.println("terceros ="+vcEXPExamCat2.size()+" = "+contador2);

			if (vcEXPExamCat.size() == contador
					&& vcEXPExamCat2.size() == contador2) {
				// System.out.println("Actualizando EXPBITMOD");
				String cSQL5 = "UPDATE EXPBITMOD SET LDICTAMEN = 1 "
						+ "WHERE "
						+ "IOPERACION = 13 AND DATE (DTREALIZADO) = CURRENT DATE";
				dCARGACITAS.Sentencia(cSQL5);
				// System.out.println("contador = "+contador);
				// System.out.println("vcEXPExamCat.size() = "+vcEXPExamCat.size());
				cClave = "CargaExitosa";
				// System.out.println("Contador y vector de citas son iguales");
			} else {
				cClave = "Carga Abortada";
				// System.out.println("Contador y vector de citas no son iguales");
			}

			if ((contador + contador2) == 0) {
				cClave = "No hay citas disponibles para ser cargadas";
				return cClave;
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

				if (pstmtValida != null) {
					pstmtValida.close();
				}

				if (rsetValida != null) {
					rsetValida.close();
				}

				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			//System.out.println(" Fin Insert");
			return cClave;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String fecha, String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcExamCat = new Vector();
		Vector v = new Vector();
		TFechas f = new TFechas();
		Calendar calendar = Calendar.getInstance();

		try {
			// System.out.println("Ejecutando busqueda de registros");
			dbConn = new DbConnection(dataSourceName);
			// System.out.println("Error???");
			conn = dbConn.getConnection();
			// System.out.println("Error!!!!");
			String cSQL = "";
			TFechas dtFecha = new TFechas();
			TVEXPExamCat vEXPExamCat;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "SELECT A.ICVEEXPEDIENTE, A.INUMEXAMEN, C.ICVEMDOTRANS, C.ICVECATEGORIA, A.LINICIADO "
					+ "FROM EXPEXAMAPLICA AS A, EXPEXAMCAT AS C "
					+ "WHERE "
					+ "A.ICVEEXPEDIENTE = C.ICVEEXPEDIENTE AND "
					+ "A.INUMEXAMEN = C.INUMEXAMEN AND "
					+ "A.ICVEPROCESO = 1 AND "
					+ cWhere
					+ " AND "
					+ "A.LDICTAMINADO = 0 AND "
					+ "(A.ICVEUSUDICTAMEN < 1 OR A.ICVEUSUDICTAMEN IS NULL) and "
					+ "C.ICVEMOTIVO NOT IN (2,27) AND "
					+ "(A.DTSOLICITADO = '"+ fecha + "' OR "
					+ "A.DTSOLICITADO = (DATE('"+fecha+"') - 1 DAY) OR "
					+ "A.DTSOLICITADO = (DATE('"+fecha+"') - 2 DAY)) ";

		    //System.out.println("FindByAll = "+ cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamCat = new TVEXPExamCat();

				vEXPExamCat.setICveExpediente(rset.getInt(1));
				vEXPExamCat.setINumExamen(rset.getInt(2));
				vEXPExamCat.setICveMdoTrans(rset.getInt(3));
				vEXPExamCat.setICveCategoria(rset.getInt(4));
				vEXPExamCat.setLPago(rset.getInt(5));
				vcExamCat.addElement(vEXPExamCat);
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
			return vcExamCat;
		}
	}
	

	/**
	 * Metodo Find By All
	 */
	@SuppressWarnings("finally")
	public Vector<TVEXPExamCat> FindByAllEMO(String Expediente, String Examen) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector<TVEXPExamCat> vcExamCat = new Vector<TVEXPExamCat>();
		try {
			// System.out.println("Ejecutando busqueda de registros");
			dbConn = new DbConnection(dataSourceName);
			// System.out.println("Error???");
			conn = dbConn.getConnection();
			// System.out.println("Error!!!!");
			String cSQL = "";
			TVEXPExamCat vEXPExamCat;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT A.ICVEEXPEDIENTE, A.INUMEXAMEN, C.ICVEMDOTRANS, C.ICVECATEGORIA, A.LINICIADO "
					+ "FROM EXPEXAMAPLICA AS A, EXPEXAMCAT AS C "
					+ "WHERE "
					+ "A.ICVEEXPEDIENTE = C.ICVEEXPEDIENTE AND "
					+ "A.INUMEXAMEN = C.INUMEXAMEN AND "
					+ "A.ICVEPROCESO = 2 AND "
					+ "A.LDICTAMINADO = 0 AND "
					+ "(A.ICVEUSUDICTAMEN < 1 OR A.ICVEUSUDICTAMEN IS NULL) and "
					//+ "C.ICVEMOTIVO NOT IN (2,27) AND "
					+ "A.ICVEEXPEDIENTE = "+Expediente+" AND "
					+ "A.INUMEXAMEN = "+Examen+" ";

			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPExamCat = new TVEXPExamCat();

				vEXPExamCat.setICveExpediente(rset.getInt(1));
				vEXPExamCat.setINumExamen(rset.getInt(2));
				vEXPExamCat.setICveMdoTrans(rset.getInt(3));
				vEXPExamCat.setICveCategoria(rset.getInt(4));
				vEXPExamCat.setLPago(rset.getInt(5));
				vcExamCat.addElement(vEXPExamCat);
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
			} catch (final Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcExamCat;
		}
	}	
	
	
	/**
	 * Mï¿½?Â©todo Insert
	 */
	@SuppressWarnings("finally")
	public String insertNoAptoEmo(Connection conGral, String Expediente, String Examen, String usr)
			throws DAOException {
		// System.out.println("Insert");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmtValida = null;
		ResultSet rsetValida = null;
		Vector<TVEXPExamCat> vcEXPExamCat = new Vector<TVEXPExamCat>();
		TVEXPExamCat vEXPExamCat;
		TDCARGACITAS dCARGACITAS = new TDCARGACITAS();
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

			vcEXPExamCat = this.FindByAllEMO(Expediente, Examen);

			if (vcEXPExamCat.size() > 0) {
				// CERRANDO EXAMENES DE UNIDADES MEDICAS
				for (int i = 0; i < vcEXPExamCat.size(); i++) {
					vEXPExamCat = (TVEXPExamCat) vcEXPExamCat.get(i);

					// //EXPSERVICIO////
					if (vEXPExamCat.getLPago() == 0) {
						cSQL = "INSERT INTO EXPSERVICIO (ICVEEXPEDIENTE,INUMEXAMEN,ICVESERVICIO,LCONCLUIDO,ICVEUSUNOTAMED,ICVEUSUAPLICA,CNOTAMEDICA,DTINICIO,DTFIN) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ","
								+ vEXPExamCat.getINumExamen()
								+ ",44,1,"+usr+","+usr+",'Negativa a Examen Médico en Operación. Fundamento: 15 bis, "
								+ " 17 bis del Reglamento del Servicio de Medicina Preventiva en el Transporte.',CURRENT DATE,CURRENT DATE)";
						dCARGACITAS.Sentencia(cSQL);
					} else {
						cSQL = "UPDATE EXPSERVICIO SET LCONCLUIDO = 1, ICVEUSUNOTAMED = "+usr+", ICVEUSUAPLICA = "+usr+", CNOTAMEDICA = 'Negativa a Examen Médico en Operación. " +
								"Fundamento: 15 bis, 17 bis del Reglamento del Servicio de Medicina Preventiva en el Transporte.' "
								+ "WHERE ICVEEXPEDIENTE = "
								+ vEXPExamCat.getICveExpediente()
								+ " AND INUMEXAMEN = "
								+ vEXPExamCat.getINumExamen()
								+ " AND ICVESERVICIO = 44";
						dCARGACITAS.Sentencia(cSQL);
					}
					if (vEXPExamCat.getLPago() == 0) {
						cSQL = "INSERT INTO EXPSERVICIO (ICVEEXPEDIENTE,INUMEXAMEN,ICVESERVICIO,LCONCLUIDO,ICVEUSUNOTAMED,ICVEUSUAPLICA,CNOTAMEDICA,DTINICIO,DTFIN) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ","
								+ vEXPExamCat.getINumExamen()
								+ ",31,1,"+usr+","+usr+",'Negativa a Examen Médico en Operación. Fundamento: 15 bis, 17 bis del " 
								+ "Reglamento del Servicio de Medicina Preventiva en el Transporte.',CURRENT DATE,CURRENT DATE)";
						dCARGACITAS.Sentencia(cSQL);
					} else {
						cSQL = "UPDATE EXPSERVICIO SET LCONCLUIDO = 1, ICVEUSUNOTAMED = "+usr+", ICVEUSUAPLICA = "+usr+", CNOTAMEDICA = 'Negativa a Examen Médico en Operación. " +
								"Fundamento: 15 bis, 17 bis del Reglamento del Servicio de Medicina Preventiva en el Transporte.' "
								+ "WHERE ICVEEXPEDIENTE = "
								+ vEXPExamCat.getICveExpediente()
								+ " AND INUMEXAMEN = "
								+ vEXPExamCat.getINumExamen()
								+ " AND ICVESERVICIO = 31";
						dCARGACITAS.Sentencia(cSQL);
					}

					// //EXPDICTAMENSERV////
					String sQL = "SELECT COUNT(*) FROM EXPDICTAMENSERV WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat.getINumExamen()
							+ " AND ICVESERVICIO = 31 "
							+ "AND ICVEMDOTRANS = "
							+ vEXPExamCat.getICveMdoTrans()
							+ " AND ICVECATEGORIA = "
							+ vEXPExamCat.getICveCategoria() + "";
					if (dCARGACITAS.RegresaInt(sQL) == 0) {
						cSQL = "INSERT INTO EXPDICTAMENSERV (ICVEEXPEDIENTE,INUMEXAMEN,ICVEMDOTRANS,ICVECATEGORIA,ICVESERVICIO,LDICTAMEN) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ","
								+ vEXPExamCat.getINumExamen()
								+ ","
								+ vEXPExamCat.getICveMdoTrans()
								+ ","
								+ vEXPExamCat.getICveCategoria() + ",31,0)";
						dCARGACITAS.Sentencia(cSQL);
					} else {
						cSQL = "UPDATE EXPDICTAMENSERV SET LDICTAMEN = 0 WHERE ICVEEXPEDIENTE = "
								+ vEXPExamCat.getICveExpediente()
								+ " AND INUMEXAMEN = "
								+ vEXPExamCat.getINumExamen()
								+ " AND ICVESERVICIO = 31";
						dCARGACITAS.Sentencia(cSQL);
					}

					// //EXPFUNDICTAMEN////
					sQL = "SELECT COUNT(*) FROM EXPFUNDICTAMEN WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat.getINumExamen()
							+ " AND ICVESERVICIO = 31";
					if (dCARGACITAS.RegresaInt(sQL) == 0) {
						cSQL = "INSERT INTO EXPFUNDICTAMEN (ICVEEXPEDIENTE,INUMEXAMEN,ICVESERVICIO,CFUNDAMENTACION,LDICTAMENSERV, TIDIAGNOSTICO) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ","
								+ vEXPExamCat.getINumExamen()
								+ ",31,'Negativa a Examen Médico en Operación. Fundamento: 15 bis, 17 bis del Reglamento del "
								+ "Servicio de Medicina Preventiva en el Transporte.',0,CURRENT TIMESTAMP)";
						dCARGACITAS.Sentencia(cSQL);
					}
					
					// //EXPDIAGNOSTICO////
					sQL = "SELECT COUNT(*) FROM EXPDIAGNOSTICO WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat.getINumExamen()
							+ " AND ICVESERVICIO = 31";
					if (dCARGACITAS.RegresaInt(sQL) == 0) {
						cSQL = "INSERT INTO EXPDIAGNOSTICO (ICVEEXPEDIENTE,INUMEXAMEN,ICVESERVICIO,ICVEESPECIALIDAD,ICVEDIAGNOSTICO, TIDIAGNOSTICO) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ","
								+ vEXPExamCat.getINumExamen()
								+ ",31,21,21,CURRENT TIMESTAMP)";
						dCARGACITAS.Sentencia(cSQL);
					}

					// //EXPEXAMAPLICA////
					cSQL = "UPDATE EXPEXAMAPLICA SET LDICTAMINADO = 1, DTDICTAMEN = CURRENT DATE, ICVEUSUDICTAMEN = "+usr+", TFINEXA = CURRENT TIMESTAMP "
							+ "WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat.getINumExamen();
					dCARGACITAS.Sentencia(cSQL);

					// //EXPEXAMCAT////
					cSQL = "UPDATE EXPEXAMCAT SET LDICTAMEN = 0, DTINICIOVIG = CURRENT DATE, DTFINVIG = CURRENT DATE, LDITAMEM = "+usr+", DTDICTAMINADO = CURRENT DATE "
							+ "WHERE ICVEEXPEDIENTE = "
							+ vEXPExamCat.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat.getINumExamen();
					// System.out.println(cSQL);
					dCARGACITAS.Sentencia(cSQL);

					// //PERLICENCIA////
					cSQL = "SELECT COUNT(*) FROM PERLICENCIA WHERE ICVEPERSONAL = "
							+ vEXPExamCat.getICveExpediente()
							+ " AND INUMEXAMEN = "
							+ vEXPExamCat.getINumExamen()
							+ " "
							+ "AND ICVEMDOTRANS = "
							+ vEXPExamCat.getICveMdoTrans()
							+ " AND ICVECATEGORIA = "
							+ vEXPExamCat.getICveCategoria() + "";
					// System.out.println(cSQL);
					if (dCARGACITAS.RegresaInt(cSQL) == 0) {
						cSQL = "INSERT INTO PERLICENCIA (ICVEPERSONAL,ICVEMDOTRANS,ICVECATEGORIA,CCONSTANCIA,INUMEXAMEN,LDICTAMEN,CLICENCIA,DTINIVIGENCIA,DTFINVIGENCIA) "
								+ "VALUES("
								+ vEXPExamCat.getICveExpediente()
								+ ","
								+ vEXPExamCat.getICveMdoTrans()
								+ ","
								+ vEXPExamCat.getICveCategoria()
								+ ",'',"
								+ vEXPExamCat.getINumExamen()
								+ ",0,'',CURRENT DATE, CURRENT DATE) ";
						// System.out.println(cSQL);
						dCARGACITAS.Sentencia(cSQL);
					}

				}// se cierra el ciclo unidades
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

				if (pstmtValida != null) {
					pstmtValida.close();
				}

				if (rsetValida != null) {
					rsetValida.close();
				}

				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (final Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}
	

}
