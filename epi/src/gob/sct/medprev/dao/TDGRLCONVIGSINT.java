/* <p>Administracion y Seguridad generada en JAVA (J2EE).    
 * @version 1.0
 * <p>
 * @author <dd>AG SA L.
 * <p> 
 */

package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

import com.micper.util.TFechas;

/**
 * <p>
 * Title: Data Acces Object de EXPExamCat DAO
 * </p>
 * <p>
 * Description: Data Access Object para EXPExamCat
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */

public class TDGRLCONVIGSINT extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLCONVIGSINT() {
	}

	/**
	 * Metodo Find By All Identifica el operador a asignar de acuerdo a las
	 * alertas que cada sintoma tiene para cada regla
	 */
	public java.sql.Date DeterminaVigenciaPorSintoma(int edad, String puesto, int transporte, int categoria,
			int expediente, int examen) throws DAOException {
		java.sql.Date dtFinV = null;
		java.sql.Date dtFinVEnvia = null;
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TDEXPRegSint dDatos = new TDEXPRegSint();
		TDEXPDictamenServ dDicServ = new TDEXPDictamenServ();
		String Respuesta = "";
		int count = 0;
		int asignavigencia = 0;
		int operador = 0;
		String sentencia = "";
		String sentencia2 = "";
		String servicio = "";
		String rama = "";
		String sintoma = "";
		int tporesp = 0;
		int iTmpVigencia = 0;
		int ianosVigencia = 0;
		int iMesesVigencia = 0;
		int iEdMayor = 0;
		int iEdMenor = 0;
		int iCveReglaVigencia = 0;
		String cSexo = "";
		String str_date = "";
		Date date;
		DateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		boolean ReglaManifiesta = false;
		try {
			// Obtener las reglas listando la regla con la vigencia mas corta
			// primero, ya que si la vigencia mas corta se cumple
			// No es necesario validar las demas reglas.
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDRespSint vMEDRespSint;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT "
					+ " G.IMAYORA, " //1
					+ " G.IIGUALA," //2
					+ " G.IMENORA, "//3
					+ " G.ICVESERVICIO, " //4
					+ " G.ICVERAMA, " //5
					+ " G.ICVESINTOMA, " //6
					+ " M.ICVETPORESP, " //7
					+ " G.IEDMAYOR, " //8
					+ " G.IEDMENOR, " //9
					+ " G.ITMPVIGENCIA, " //10
					+ " G.ICVEREGLAVIGENCIA, " //11
					+ " G.CSEXO " //12
					+ " FROM GRLCONVIGSINT AS G, "
					+ "      MEDSINTOMAS AS M " 
					+ " WHERE "
					+ " G.ICVESERVICIO = M.ICVESERVICIO AND " 
					+ " G.ICVERAMA = M.ICVERAMA AND "
					+ " G.ICVESINTOMA = M.ICVESINTOMA AND " 
					+ " G.LACTIVO = 1 AND " 
					//+ " G.ICVEMDOTRANS = " + transporte	+ " AND " 
					//+ " G.ICVECATEGORIA = " + categoria + " AND  " 
					//+ " G.ICVEPUESTO = " + puesto + " AND "
					+ " ( G.IEDMAYOR < " + edad + " AND " 
					+ "   G.IEDMENOR > " + edad + " ) AND "
					+ " G.CSEXO = (SELECT CSEXO FROM PERDATOS WHERE ICVEEXPEDIENTE = "+expediente+") AND "
					+ " G.LACTIVO = 1 AND " 
					+ " M.LACTIVO = 1 " + "  ";

			System.out.println("########### OPERACIONES DE VIGENCIA POR SINTOMA ##########");
			System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				System.out.println("############ResultadosIniciaasignavigencia########="+asignavigencia);
				if (rset.getDouble(1) > 0.0)
					count = count + 1;
				if (rset.getDouble(2) > 0.0)
					count = count + 10;
				if (rset.getDouble(3) > 0.0)
					count = count + 100;
				servicio = rset.getString(4);
				rama = rset.getString(5);
				sintoma = rset.getString(6);
				tporesp = rset.getInt(7);
				iEdMayor = rset.getInt(8);
				iEdMenor = rset.getInt(9);
				iTmpVigencia = rset.getInt(10);
				iCveReglaVigencia = rset.getInt(11);
				cSexo = rset.getString(12);


				System.out.println("########### OPERADOR ##########");
				System.out.println("iTmpVigencia = "+iTmpVigencia);
				System.out.println("count = "+count);
			//}

			operador = count;

			if (operador > 0) {
				if (operador == 1) {
					sentencia2 = "( R.DVALORINI > M.IMAYORA) ";
				}
				if (operador == 10) {
					sentencia2 = "( M.IIGUALA = R.DVALORINI) ";
				}
				if (operador == 11) {
					sentencia2 = "( R.DVALORINI > M.IMAYORA OR M.IIGUALA = R.DVALORINI) ";
				}
				if (operador == 100) {
					sentencia2 = "( R.DVALORINI < M.IMENORA) ";
				}
				if (operador == 101) {
					sentencia2 = "( R.DVALORINI > M.IMAYORA AND R.DVALORINI < M.IMENORA) ";
				}
				if (operador == 110) {
					sentencia2 = "( R.DVALORINI < M.IMENORA OR M.IIGUALA = R.DVALORINI) ";
				}
				if (operador == 111) {
					sentencia2 = "( (R.DVALORINI < M.IMENORA AND R.DVALORINI > M.IMAYORA) OR M.IIGUALA = R.DVALORINI) ";
				}

				/*sentencia = "SELECT R.ICVEEXPEDIENTE " + " FROM EXPRESULTADO AS R, GRLCONVIGSINT AS M " + " WHERE "
						+ " R.ICVEEXPEDIENTE = " + expediente + " AND " + " R.INUMEXAMEN = " + examen + " AND "
						+ " R.ICVESERVICIO = M.ICVESERVICIO AND " + " R.ICVERAMA = M.ICVERAMA AND "
						+ " R.ICVESINTOMA = M.ICVESINTOMA AND " + " R.ICVESERVICIO = " + servicio + " AND "
						+ " R.ICVERAMA =  " + rama + " AND " + " R.ICVESINTOMA =  " + sintoma + " AND " + sentencia2;*/

				ReglaManifiesta = this.FindResultadosRegla(transporte, categoria, servicio, rama, sintoma, expediente,
						examen, sentencia2, cSexo, iCveReglaVigencia+"");
				/// Definir Operaciones en caso de que la regla se manifieste o
				/// se cumpla
				if (ReglaManifiesta) {
					System.out.println("Se detecto una coincidencia");
					ianosVigencia = iTmpVigencia / 12;
					int ano = iTmpVigencia / 12;
					iMesesVigencia = iTmpVigencia - (ano * 12);
					if (ianosVigencia > 0 && iMesesVigencia > 0) {
						System.out.println("OP 1");
						System.out.println(ianosVigencia+" > 0 && "+iMesesVigencia+" > 0");
						if (edad > iEdMayor && edad < iEdMenor) {
							System.out.println(edad+" > "+iEdMayor+" && "+edad+" < "+iEdMenor);
							str_date = dDicServ.SenFindBy("select current date +" + ianosVigencia + "  YEARS + "
									+ iMesesVigencia + " MONTHS from sysibm.sysdummy1");
							// Convierte String a Date
							date = (Date) formatter.parse(str_date);
							java.util.Date y2 = new java.util.Date();
							y2 = date;
							java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
							dtFinV = CalVigencia;
						}
					} else {
						if (ianosVigencia > 0) {
							System.out.println("OP 2");
							if (edad > iEdMayor && edad < iEdMenor) {
								System.out.println(edad+" > "+iEdMayor+" && "+edad+" < "+iEdMenor);
								str_date = dDicServ.SenFindBy(
										"select current date +" + ianosVigencia + "  YEARS from sysibm.sysdummy1");
								// Convierte String a Date
								date = (Date) formatter.parse(str_date);
								java.util.Date y2 = new java.util.Date();
								y2 = date;
								java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
								dtFinV = CalVigencia;
							}
						}
						if (iMesesVigencia > 0) {
							System.out.println("OP 3");
							System.out.println("edad = "+edad);
							System.out.println("iEdMayor = "+iEdMayor);
							System.out.println("iEdMenor = "+iEdMenor);
							if (edad > iEdMayor && edad < iEdMenor) {
								System.out.println(edad+" > "+iEdMayor+" && "+edad+" < "+iEdMenor);
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
					}
				
				
				/////////////Regas Especiales De Sintomas///////////////////////////////////////////////
						//////SIGNOS VITALES Y SOMATOMETRIA - Porcentaje de grasa ///////
							double anterior=0.0;
							double ultimo=0.0;
							double PuntoCincoPorCiento = 0;
							if(servicio.equals("11") && rama.equals("1") && sintoma.equals("16") && (iCveReglaVigencia == 3 || iCveReglaVigencia == 6) ){
								///No se valida el sexo porque se realiza en un paso previo
								anterior = this.FindResultadosHistorico(servicio, rama, sintoma, expediente, examen, sentencia2);
								ultimo = this.FindResultadosUltimoExamen(servicio, rama, sintoma, expediente, examen, sentencia2);
								System.out.println("anterior = "+anterior);
								System.out.println("ultimo = "+ultimo);
								PuntoCincoPorCiento = (double) ((.5*100)/anterior);
								System.out.println("Formula1 = "+PuntoCincoPorCiento);
								PuntoCincoPorCiento = (double) ((.5*anterior)/100);
								System.out.println("Formula2 = "+PuntoCincoPorCiento);
								if((PuntoCincoPorCiento-anterior) >= ultimo ){
									System.out.println("Se cumplio (PuntoCincoPorCiento-anterior/="+(PuntoCincoPorCiento-anterior)+") >= ultimo");
									ianosVigencia = 3 / 12;
									ano = 3 / 12;
									iMesesVigencia = 3 - (ano * 12);
									str_date = dDicServ.SenFindBy("select current date +" + iMesesVigencia + " MONTHS from sysibm.sysdummy1");
									// Convierte String a Date
									date = (Date) formatter.parse(str_date);
									java.util.Date y2 = new java.util.Date();
									y2 = date;
									java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
									dtFinV = CalVigencia;
								}else{
									System.out.println("No Se cumplio (PuntoCincoPorCiento-anterior/="+(PuntoCincoPorCiento-anterior)+") >= ultimo");
									ianosVigencia = 0 / 12;
									ano = 0 / 12;
									iMesesVigencia = 0 - (ano * 12);
									str_date = dDicServ.SenFindBy("select current date +" + iMesesVigencia + " MONTHS from sysibm.sysdummy1");
									// Convierte String a Date
									date = (Date) formatter.parse(str_date);
									java.util.Date y2 = new java.util.Date();
									y2 = date;
									java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
									dtFinV = CalVigencia;
								}
							}
							
				}
			}
			
			System.out.println("ResultadosFinasignavigencia="+asignavigencia);
			if(asignavigencia==0){
				dtFinVEnvia = dtFinV;
				System.out.println("ResultadosFinasignavigencia Op1");
			}else{
				if(dtFinVEnvia != null){
				if(dtFinVEnvia.compareTo(dtFinV) > 0){
					dtFinVEnvia = dtFinV;
					System.out.println("ResultadosFinasignavigencia Op2");
				}
				}else{
					dtFinVEnvia = dtFinV;
					System.out.println("ResultadosFinasignavigencia Op3");
				}
			}
			
			asignavigencia = asignavigencia+1;
			
		}
			
		 System.out.println("dtFinV = "+dtFinV);
		 System.out.println("########### FIN DE OPERACIONES DE VIGENCIA POR SINTOMA ##########");
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

			/// Validar reglas con respecto a los valores en EXPRESULTADO

			return dtFinV;
		}

	}

	/**
	 * Metodo Find By All Identifica el operador a asignar de acuerdo a las
	 * alertas que cada sintoma tiene para cada regla
	 */
	public boolean FindResultadosRegla(int transporte, int categoria, String servicio, String rama, String sintoma,
			int expediente, int examen, String sentencia2, String cSexo, String iCveReglaVigencia) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		TDEXPRegSint dDatos = new TDEXPRegSint();
		int count = 0;
		boolean SeCumplioLaREgla = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDRespSint vMEDRespSint;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT R.ICVEEXPEDIENTE, R.DVALORINI, M.ICVEREGLAVIGENCIA, M.IMAYORA, M.IMENORA " 
					+ " FROM EXPRESULTADO AS R, GRLCONVIGSINT AS M " 
					+ " WHERE "
					+ " R.ICVEEXPEDIENTE = " + expediente + " AND " + " R.INUMEXAMEN = " + examen + " AND "
					+ " M.CSEXO = '"+cSexo+"' AND M.ICVEREGLAVIGENCIA = "+iCveReglaVigencia+" AND "
					+ " R.ICVESERVICIO = M.ICVESERVICIO AND " + " R.ICVERAMA = M.ICVERAMA AND "
					+ " R.ICVESINTOMA = M.ICVESINTOMA AND " + " R.ICVESERVICIO = " + servicio + " AND "
					+ " R.ICVERAMA =  " + rama + " AND " + " R.ICVESINTOMA =  " + sintoma + " AND " + sentencia2;

			System.out.println("***********FindResultadosRegla ********   \n"+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				SeCumplioLaREgla = true;
				System.out.println("Resultado ="+rset.getDouble(2));
				System.out.println("ICVEREGLAVIGENCIA ="+rset.getDouble(3));
				System.out.println("IMAYORA ="+rset.getDouble(4));
				System.out.println("IMENORA ="+rset.getDouble(5));
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
			return SeCumplioLaREgla;
		}
	}
	

	/**
	 * Metodo Find By All Identifica el operador a asignar de acuerdo a las
	 * alertas que cada sintoma tiene para cada regla
	 */
	@SuppressWarnings("finally")
	public double FindResultadosHistorico(String servicio, String rama, String sintoma,int expediente, 
			int examen, String sentencia2) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		double dValorIni=0.0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT R.ICVEEXPEDIENTE, R.DVALORINI " 
					+ " FROM EXPRESULTADO AS R " 
					+ " WHERE "
					+ " R.ICVEEXPEDIENTE = " + expediente + " AND " 
					+ " R.INUMEXAMEN = (SELECT MAX(A.INUMEXAMEN) FROM EXPEXAMAPLICA AS A "
					+ "					WHERE A.ICVEPROCESO = 1 AND "
					+ "						  A.ICVEEXPEDIENTE=R.ICVEEXPEDIENTE AND "
					+ "						  A.INUMEXAMEN < " + examen + ") AND "
					+ " R.ICVESERVICIO = " + servicio + " AND "
					+ " R.ICVERAMA = " + rama + " AND " 
					+ " R.ICVESINTOMA =  " + sintoma ;

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				dValorIni=rset.getDouble(2);
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
			return dValorIni;
		}
	}
	
	/**
	 * Metodo Find By All Identifica el operador a asignar de acuerdo a las
	 * alertas que cada sintoma tiene para cada regla
	 */
	@SuppressWarnings("finally")
	public double FindResultadosUltimoExamen(String servicio, String rama, String sintoma,int expediente, 
			int examen, String sentencia2) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		double dValorIni=0.0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT R.ICVEEXPEDIENTE, R.DVALORINI " 
					+ " FROM EXPRESULTADO AS R " 
					+ " WHERE "
					+ " R.ICVEEXPEDIENTE = " + expediente + " AND " 
					+ " R.INUMEXAMEN = " + examen + " AND "
					+ " R.ICVESERVICIO = " + servicio + " AND "
					+ " R.ICVERAMA = " + rama + " AND " 
					+ " R.ICVESINTOMA =  " + sintoma ;

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				dValorIni=rset.getDouble(2);
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
			return dValorIni;
		}
	}
	
	
}
