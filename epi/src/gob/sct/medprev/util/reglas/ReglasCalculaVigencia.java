package gob.sct.medprev.util.reglas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.sql.DbConnection;
import com.micper.util.TFechas;
import com.micper.util.TNumeros;

import gob.sct.medprev.dao.TDEXPExamAplica;
import gob.sct.medprev.dao.TDEXPExamCat;
import gob.sct.medprev.dao.TDGRLCONVIGPUE;
import gob.sct.medprev.dao.TDGRLCONVIGSINT;
import gob.sct.medprev.dao.TDPERDatos;
import gob.sct.medprev.vo.TVGRLCONVIGPUE;
import gob.sct.medprev.vo.TVPERDatos;

public class ReglasCalculaVigencia {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	@SuppressWarnings({ "rawtypes", "finally" })
	public int CalculaAptitud(int transporte, int categoria, int icvexpediente, int inumexamen){
			int Aptitud = 0; ///EN ESTE METODO 1 ES NO APTO 0 ES APTO
			StringBuffer cCondicion = new StringBuffer();
			TFechas Fecha = new TFechas();
			TNumeros Numeros = new TNumeros();
			java.sql.Date dtFinV;
			java.sql.Date dtFinVSintomas;
			java.sql.Date dtFinVInsulinodependiente;
			java.sql.Date dtFinVAnticoagulante;
			java.sql.Date dtFinVDiagnostico;
			int IUnidadMedica = 0;
			int IModulo = 0;
			try {
				TDEXPExamAplica dExamA = new TDEXPExamAplica();
				TDPERDatos dPERDatos = new TDPERDatos();
				cCondicion.append("where iCveExpediente = ")
						.append(icvexpediente)
						.append("  and iNumExamen     = ")
						.append(inumexamen);
				Vector vExamAplica = new TDEXPExamAplica().FindByAll(cCondicion
						.toString());

				///OBteneos Unidad y modulo
				IUnidadMedica = dExamA.RegresaInt("Select icveunimed from expexamaplica where icveexpediente = "+icvexpediente+" and inumexamen ="+inumexamen);
				IModulo = dExamA.RegresaInt("Select icvemodulo from expexamaplica where icveexpediente = "+icvexpediente+" and inumexamen ="+inumexamen);
				
				
				
				// Calculamos fecha de nacimiento
				Vector vcPersonal = new Vector();
				TVPERDatos vPerDatos;
				java.util.Date fecha2 = new java.util.Date();
				java.sql.Date FechaNacimiento = new java.sql.Date(fecha2.getTime());
				vcPersonal = dPERDatos.FindByAll(icvexpediente + "");
				if (vcPersonal.size() > 0) {
					for (int i = 0; i < vcPersonal.size(); i++) {
						vPerDatos = (TVPERDatos) vcPersonal.get(i);
						FechaNacimiento = vPerDatos.getDtNacimiento();
					}
				}

				Calendar hoy = Calendar.getInstance();
				Calendar nac = Calendar.getInstance();
				nac.setTime(FechaNacimiento);
				int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);
				if ((hoy.get(Calendar.MONTH) << 5) + hoy.get(Calendar.DATE) < (nac
						.get(Calendar.MONTH) << 5) + nac.get(Calendar.DATE)) {
					edad--;
				}
				 //System.out.println("edad -- = " +edad);

				// Incrementamos 6 meses al dia actual
				hoy.add(Calendar.MONTH, 7);

				int ano = hoy.get(Calendar.YEAR);
				int mes = hoy.get(Calendar.MONTH);
				int dia = hoy.get(Calendar.DATE);

				 //System.out.println(ano +" - "+mes+" - "+dia);

				String str_date = ano + "-" + mes + "-" + dia;

				// Convierte String a Date
				// String str_date="0000-06-00";
				DateFormat formatter;
				Date date;
				formatter = new SimpleDateFormat("yyyy-MM-dd");
				date = (Date) formatter.parse(str_date);

				TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
				
				

				//System.out.println("op1");
				
				// Se encontro el ExamAplica
				if (vExamAplica != null && vExamAplica.size() > 0) {
					// Se asigna categoria y transporte
						// Generar el update para la Entidad EXPExamCat
						//System.out.println("op3");
						dtFinV = Fecha
								.getSQLDatefromSQLString(
								// Se modificaron las siguientes lineas con la
								// finalidad de insertar correctamente el fin de
								// vigencia en Perlicencia
								String.valueOf(Fecha.getIntYear(Fecha.TodaySQL())
										+ 2)
										+ "-"
										+ Numeros.getNumeroSinSeparador(Integer
												.valueOf(String.valueOf(Fecha
														.getIntMonth(Fecha
																.TodaySQL()))), 2)
										+ "-"
										+ Numeros.getNumeroSinSeparador(
												Integer.valueOf(String.valueOf(Fecha
														.getIntDay(Fecha.TodaySQL()))),
												2));

						// / Calculando nuevas Vigencias por puesto
						// AG SA L 2011/09/06

						TDGRLCONVIGPUE dGRLCONVIGPUE = new TDGRLCONVIGPUE();
						TVGRLCONVIGPUE vGRLCONVIGPUE = new TVGRLCONVIGPUE();
						Vector vcGRLCONVIGPUE = new Vector();
						try {
							String SQLQ = "select ep.icvepuesto "
									+ "from 	expexampuesto as ep, 	"
									+ "grlpuesto as gp " + "where "
									+ "gp.icvemdotrans = ep.icvemdotrans and "
									+ "gp.icvepuesto = ep.icvepuesto and "
									+ "gp.icvemdotrans = " + transporte + " and "
									+ "gp.icvecategoria = " + categoria + " and "
									+ "ep.icveexpediente = "
									+ icvexpediente + " and "
									+ "ep.inumexamen = " + inumexamen;
							String puestoS = dEXPExamCat.SentenciaSQL(SQLQ);

							vcGRLCONVIGPUE = dGRLCONVIGPUE
									.FindByAll(" WHERE ICVEMDOTRANS = "
											+ transporte + " AND ICVECATEGORIA = "
											+ categoria + " AND  ICVEPUESTO = "
											+ puestoS + " AND LACTIVO = 1");
						} catch (Exception Ex) {
							vcGRLCONVIGPUE = new Vector();
						}
						
						if (vcGRLCONVIGPUE.size() > 0) {
							for (int j = 0; j < vcGRLCONVIGPUE.size(); j++) {
								vGRLCONVIGPUE = (TVGRLCONVIGPUE) vcGRLCONVIGPUE
										.get(j);
								if (vGRLCONVIGPUE.getIanosVigencia() > 0
										&& vGRLCONVIGPUE.getIMesesVigencia() > 0) {
									if (edad > vGRLCONVIGPUE.getIEdMayor()
											&& edad < vGRLCONVIGPUE.getIEdMenor()) {
										str_date = this
												.SenFindBy("select current date +"
														+ vGRLCONVIGPUE
																.getIanosVigencia()
														+ "  YEARS + "
														+ vGRLCONVIGPUE
																.getIMesesVigencia()
														+ " MONTHS from sysibm.sysdummy1");
										// Convierte String a Date
										date = (Date) formatter.parse(str_date);
										java.util.Date y2 = new java.util.Date();
										y2 = date;
										java.sql.Date CalVigencia = new java.sql.Date(
												y2.getTime());
										dtFinV = CalVigencia;
									}
								} else {
									if (vGRLCONVIGPUE.getIanosVigencia() > 0) {//System.out.println("op8");
										if (edad > vGRLCONVIGPUE.getIEdMayor()
												&& edad < vGRLCONVIGPUE
														.getIEdMenor()) {
											str_date = this
													.SenFindBy("select current date +"
															+ vGRLCONVIGPUE
																	.getIanosVigencia()
															+ "  YEARS from sysibm.sysdummy1");
											// Convierte String a Date
											date = (Date) formatter.parse(str_date);
											java.util.Date y2 = new java.util.Date();
											y2 = date;
											java.sql.Date CalVigencia = new java.sql.Date(
													y2.getTime());
											dtFinV = CalVigencia;
										}
									}
									if (vGRLCONVIGPUE.getIMesesVigencia() > 0) {
										if (edad > vGRLCONVIGPUE.getIEdMayor()
												&& edad < vGRLCONVIGPUE
														.getIEdMenor()) {
											str_date = this
													.SenFindBy("select current date +"
															+ vGRLCONVIGPUE
																	.getIMesesVigencia()
															+ " MONTHS from sysibm.sysdummy1");
											// Convierte String a Date
											date = (Date) formatter.parse(str_date);

											java.util.Date y2 = new java.util.Date();
											y2 = date;
											java.sql.Date CalVigencia = new java.sql.Date(
													y2.getTime());
											dtFinV = CalVigencia;

										}
									}
								}
							}
						}
						
						// / Calculando nuevas Vigencias por sintoma
						// AG SA L 2016/11/08
						TDGRLCONVIGSINT dGRLCONVIGSINT = new TDGRLCONVIGSINT();
						try {
							String SQLQ = "select ep.icvepuesto "
									+ "from expexampuesto as ep, 	"
									+ "grlpuesto as gp " + "where "
									+ "gp.icvemdotrans = ep.icvemdotrans and "
									+ "gp.icvepuesto = ep.icvepuesto and "
									+ "gp.icvemdotrans = " + transporte + " and "
									+ "gp.icvecategoria = " + categoria + " and "
									+ "ep.icveexpediente = "
									+ icvexpediente + " and "
									+ "ep.inumexamen = " + inumexamen;
							String puestoS = dEXPExamCat.SentenciaSQL(SQLQ);
							dtFinVSintomas = dGRLCONVIGSINT.DeterminaVigenciaPorSintoma(edad, puestoS+"", transporte, categoria, 
									icvexpediente, inumexamen);
							//if(dtFinV > dtFinVSintomas before){
							System.out.println("dtFinV "+dtFinV+" es anterior a dtFinVSintomas="+dtFinVSintomas);
					 	    if(dtFinV.compareTo(dtFinVSintomas) > 0){
								dtFinV = dtFinVSintomas;
								System.out.println("dtFinV "+dtFinV+" es mayor a dtFinVSintomas = "+dtFinVSintomas);
							}
					 	    
					 	   
					 	   if (dtFinV.compareTo(dtFinVSintomas) > 0) {
					            System.out.println("Date1 is after Date2");
					        } else if (dtFinV.compareTo(dtFinVSintomas) < 0) {
					            System.out.println("Date1 is before Date2");
					        } else if (dtFinV.compareTo(dtFinVSintomas) == 0) {
					            System.out.println("Date1 is equal to Date2");
					        } else {
					            System.out.println("How to get here?");
					        }
							
							//System.out.println("Puesto = "+puestoS);
							
											
						} catch (Exception Ex) {
							vcGRLCONVIGPUE = new Vector();
						}
						
						///************************************* EXCEPCIONES A LAS REGLAS **************************************************************//
							boolean CDI = false;
							RImpedirExamenMedico rImpedirExamen = new RImpedirExamenMedico();
							CDI = rImpedirExamen.ExamenEnCDIoCenma(icvexpediente+"", inumexamen+"");
							
								// Calculando Vigencia Insulinodependiente solo 6 meses CDI y CENMA NO APTO OTRAS UNIDADES
								// AG SA L 2017/04/05
								boolean InsulinoDependiente = false;							
								try {
									System.out.println("****************** InsulinoDependiente *************************");
									InsulinoDependiente = rImpedirExamen.DiagnosticoDeDiabetesMellitusInsulinodependiente(icvexpediente+"");
									dtFinVInsulinodependiente = rImpedirExamen.DeterminaVigenciaInsulinodependiente();
									if(InsulinoDependiente){
										if(CDI){
											if(rImpedirExamen.NumeroDeReglasDeNoAptitudActivas(icvexpediente+"")==1){
											if(dtFinV.compareTo(dtFinVInsulinodependiente) > 0){
												dtFinV = dtFinVInsulinodependiente;
												System.out.println("dtFinVInsulinodependiente = "+dtFinVInsulinodependiente);
											}
											}
										}							
									}
													
								} catch (Exception Ex) {
									System.out.println("Error al calcular Vigencia Insulinodependiente");
								}
								
															
								// Calculando Vigencia USO DE ANTICOAGULANTES solo 6 meses CDI y CENMA NO APTO OTRAS UNIDADES
								// AG SA L 2017/04/07
								boolean Anticoagulantes = false;
								try {
									System.out.println("****************** Anticoagulantes *************************");
									Anticoagulantes = rImpedirExamen.DiagnosticoDeUsoDeAnticoagulantes(icvexpediente+"");
									dtFinVAnticoagulante = rImpedirExamen.DeterminaVigenciaAnticoagulante();
									if(Anticoagulantes){
										System.out.println("****************** TRUE *************************");
										if(CDI){
											System.out.println("****************** CDI ********************");
											if(dtFinV.compareTo(dtFinVAnticoagulante) > 0){
											if(rImpedirExamen.NumeroDeReglasDeNoAptitudActivas(icvexpediente+"")==1){
												dtFinV = dtFinVAnticoagulante;
												System.out.println("dtFinVAnticoagulante = "+dtFinVAnticoagulante);
												System.out.println("dtFinV = "+dtFinV);
											}
											}
										}							
									}
													
								} catch (Exception Ex) {
									System.out.println("Error al calcular Vigencia ANTICOAGULANTES");
								}
								
								
								
								
								
						///************************************* VIGENCIA POR DIAGNOSTICO **************************************************************//
								System.out.println("****************** VIGENCIA POR DIAGNOSTICO *****************************");
								RDiagnosticoVigencia rDiagnosticoVigencia = new RDiagnosticoVigencia();
								dtFinVDiagnostico = rDiagnosticoVigencia.FechaDeReglaDeVigenciaPorDiagnostico(icvexpediente+"", inumexamen+"", IUnidadMedica, IModulo);
								if(dtFinV.compareTo(dtFinVDiagnostico) > 0){
									dtFinV = dtFinVDiagnostico;
									System.out.println("dtFinVDiagnostico = "+dtFinVDiagnostico);
								}
								System.out.println("****************** FIN VIGENCIA POR DIAGNOSTICO *************************");
						
								
								
								
								/////Validar Vigencias para no aptitud////
								System.out.println("/dtFinV = "+dtFinV);
								System.out.println("Fecha.TodaySQL() = "+Fecha.TodaySQL());
								if (String.valueOf(dtFinV.toString()).contentEquals(String.valueOf(Fecha.TodaySQL()))) {
									Aptitud = 1;
									System.out.println("dtFinV = Fecha.TodaySQL()");
								}
										
				}		
			} // try
			catch (Exception ex) {
				ex.printStackTrace();
				Aptitud = 1;
			} // catch
			finally {
				return Aptitud;
			} // finally
		} // Metodo

		
	

	/**
	 * Metodo Find By All
	 */
	@SuppressWarnings("finally")
	public String SenFindBy(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String Regresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Regresa = rset.getString(1);
			}
		} catch (Exception ex) {
			System.out.println("FindByAll "+ ex);
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
				System.out.println("FindByAll.close"+ ex2);
			}
			return Regresa;
		}
	}
	
}
