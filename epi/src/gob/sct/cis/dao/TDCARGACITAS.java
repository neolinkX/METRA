/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.cis.dao;

import java.sql.*;
import java.util.*;
import java.text.*;

import com.micper.seguridad.vo.TVUsuario;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.dao.*;

/**
 * 
 * @author AG SA
 */

public class TDCARGACITAS extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private String dataSourceName2 = VParametros.getPropEspecifica("ConDBCis");
	private String dataSourceName3 = VParametros.getPropEspecifica("ConDBSIAF");
	public TDCARGACITAS() {
	}

	/**
	 * M�?©todo Find By All
	 */
	public Vector FindByAll(String fecha, int user) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		Vector vcEPICisCita = new Vector();
		TDPERDatos dTDPERDatos = new TDPERDatos();
		TVPERDatos vTVPERDatos = new TVPERDatos();
		Vector v = new Vector();
		TFechas f = new TFechas();
		Calendar calendar = Calendar.getInstance();

		try {
			dbConn = new DbConnection(dataSourceName2);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLPEM = "";
			String cCita = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVEPICisCita vEPICisCita;
			TDGRLMotivo dTDGRLMotivo = new TDGRLMotivo();
			TVGRLMotivo vTVGRLMotivo = new TVGRLMotivo();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			String infoDinamica[];
			String infoDinamica2[];
			String motivoARM[];
			String Puestos[];

			cSQL = "SELECT  CISCITAS.DTFECHA, "
					+ "CISCITAS.CHORA, "
					+ "CISINTERESADO.CRFC, "
					+ "CISINTERESADO.CCURP,"
					+ " CISINTERESADO.ICVEMUNICIPIO,"
					+ "GRLMUNICIPIO.CNOMBRE,"
					+ " CISINTERESADO.ICVEENTIDADFED,"
					+ "GRLENTIDADFED.CNOMBRE,"
					+ " CISINTERESADO.CNOMBRE, "
					+ "CISINTERESADO.CAPPATERNO,"
					+ "CISINTERESADO.CAPMATERNO,"
					+ " CISINTERESADO.CSEXO, "
					+ " CISINTERESADO.CDIRECCION,"
					+ "CISINTERESADO.CCP,"
					+ // CP 14
					/* 15 */" CISINTERESADO.ICVEENTIDADNAC, "
					+
					/* 16 */" ENTIDADNAC.CNOMBRE, "
					+
					/* 17 */" CISINTERESADO.CTELEFONO, "
					+
					/* 18 */" CSTREGTRAMITE.ICVETRAMITE, "
					+
					/* 19 */" CISCITAS.CDSCCAMPOS,"
					+
					/* 20 */" CISCITAS.CNUMTRAMITES,"
					+
					/* 21 */" CISINTERESADO.CCOLONIA, "
					+
					/* 22 */" CISAREAS.ICVEUNIMED, "
					+
					/* 23 */" CISAREAS.ICVEMODULO "
					+ " FROM    CISCITAS, CISINTERESADO, GRLMUNICIPIO, GRLENTIDADFED, GRLENTIDADFED AS ENTIDADNAC, CSTREGTRAMITE, CISAREAS "
					+ " WHERE   CISCITAS.ICVEINTERESADO = CISINTERESADO.ICVEINTERESADO "
					+ " AND CISCITAS.ICVEAREA = CISAREAS.ICVEAREA "
					+ " AND CISINTERESADO.ICVEMUNICIPIO = GRLMUNICIPIO.ICVEMUNICIPIO "
					+ " AND CISINTERESADO.ICVEENTIDADFED = GRLENTIDADFED.ICVEENTIDADFED "
					+ " AND CISINTERESADO.ICVEENTIDADFED = GRLMUNICIPIO.ICVEENTIDADFED "
					+ " AND CISINTERESADO.ICVEENTIDADNAC = ENTIDADNAC.ICVEENTIDADFED "
					+ " AND  CISCITAS.ICVETRAMITE = CSTREGTRAMITE.ICVETRAMITE "
					+
					// cFiltrado + " ORDER BY CISCITAS.CHORA";
					" AND DTFECHA = '"
					+ fecha
					+ "' AND CISAREAS.ICVEUNIMED >= 0   ORDER BY CISCITAS.CHORA";
			// " AND CISCITAS.ICVEAREA = 160 AND DTFECHA = '2011-06-29' ORDER BY CISCITAS.CHORA";
			pstmt = conn.prepareStatement(cSQL);
			// System.out.println(cSQL);
			// pstmtPEM = conn.prepareStatement(cSQLPEM);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEPICisCita = new TVEPICisCita();

				vEPICisCita.setDtFecha(rset.getDate(1));

				// Validando Hora
				// System.out.println("Hora = "+rset.getString(2));
				if (rset.getString(2).length() >= 7)
					vEPICisCita.setCHora(rset.getString(2));
				else
					vEPICisCita.setCHora("08:00:00");

				vEPICisCita.setCRFC(rset.getString(3).trim());
				vEPICisCita.setCCURP(rset.getString(4));
				vEPICisCita.setICveMunicipio(rset.getInt(5));
				vEPICisCita.setCDscMunicipio(rset.getString(6));

				vEPICisCita.setICveEstado(rset.getInt(7));
				vEPICisCita.setCDscEstado(rset.getString(8));
				vEPICisCita.setCNombre(rset.getString(9));
				vEPICisCita.setCApPaterno(rset.getString(10));
				vEPICisCita.setCApMaterno(rset.getString(11));
				// if(rset.getString(3).equals("PEGS570421"))
				// System.out.println("PEGS570421 Sexo = "+rset.getString(12));
				vEPICisCita.setCGenero(rset.getString(12));
				vEPICisCita.setCCalle(rset.getString(13));

				// Valida el Codigo Postal
				int CodPost = 0;
				int cuenta = 0;
				if (rset.getString(14).toString().length() > 0) {
					for (int j = 0; rset.getString(14).toString().length() > j; j++) {
						if (rset.getString(14).charAt(j) >= '0'
								&& rset.getString(14).charAt(j) <= '9') {
							cuenta++;
						}
					}
					if (cuenta == rset.getString(14).length())
						CodPost = Integer.parseInt(rset.getString(14)
								.toString());
				} else {
					CodPost = 1;
				}
				// System.out.println("Codigo Postal = "+rset.getString(14));
				vEPICisCita.setICP(CodPost);

				vEPICisCita.setICveEstadoNac(rset.getInt(15));
				vEPICisCita.setCDscEstadoNac(rset.getString(16));
				if (rset.getString(17) != null)
					vEPICisCita.setCTelefono(rset.getString(17));
				else
					vEPICisCita.setCTelefono("0");

				// Asignando Modo de transporte y categoria
				int autotrans = 0;
				// System.out.println("MdoTransporte = "+rset.getString(18));
				int MdoAsig = 0;
				if (rset.getInt(18) == 134) {// 134 en pruebas y produccion
					vEPICisCita.setCDscMdoTransporte("AUTOTRANSPORTE");
					vEPICisCita.setICveMdoTrans(2);
					vEPICisCita.setICveCategoria(7);
					autotrans = 1;
					MdoAsig++;
				}
				if (rset.getInt(18) == 287) {// 287 en produccion ---- 302 en
												// pruebas
					vEPICisCita.setCDscMdoTransporte("AUTOTRANSPORTE");
					vEPICisCita.setICveMdoTrans(2);
					vEPICisCita.setICveCategoria(7);
					autotrans = 1;
					MdoAsig++;
				}
				if (rset.getInt(18) == 286) {// 286 en produccion ---- 213 en
												// pruebas
					vEPICisCita.setCDscMdoTransporte("AEREO");
					vEPICisCita.setICveMdoTrans(1);
					vEPICisCita.setICveCategoria(1);
					MdoAsig++;
				}
				if (rset.getInt(18) == 288) {// 288 en produccion ---- 303 en
												// pruebas
					vEPICisCita.setCDscMdoTransporte("MARITIMO");
					vEPICisCita.setICveMdoTrans(4);
					vEPICisCita.setICveCategoria(4);
					MdoAsig++;
				}
				if (rset.getInt(18) == 289) {// 289 en produccion ---- 304 en
												// pruebas
					vEPICisCita.setCDscMdoTransporte("FERROVIARIO");
					vEPICisCita.setICveMdoTrans(3);
					vEPICisCita.setICveCategoria(3);
					MdoAsig++;
				}
				if (MdoAsig == 0) {
					vEPICisCita.setCDscMdoTransporte("AUTOTRANSPORTE");
					vEPICisCita.setICveCategoria(7);
					autotrans = 1;
				}
				// vEPICisCita.setCDscMdoTransporte(rset.getString(18));

				// MOTIVO
				String informacionDinamica = "EXPEDICION";
				if (rset.getString(19) != null) {
					if (rset.getString(19).length() > 0) {

						// System.out.println("length >>" +
						// rset.getString(19).split(",").length);
						// infoDinamica = new String[6];
						infoDinamica = rset.getString(19).split(",");

						// System.out.println("infoDinamica.length"+infoDinamica.length);
						// System.out.println("infoDinamica = "+infoDinamica);
						if (infoDinamica.length >= 6) {
							motivoARM = infoDinamica[5].split("\\|");
							if (motivoARM.length == 2)
								informacionDinamica = motivoARM[1];
							vEPICisCita.setCDscMotivo(informacionDinamica);
							// System.out.println("infoDinamica2 = "+infoDinamica);
						} else
							vEPICisCita.setCDscMotivo(informacionDinamica);
						// System.out.println("infoDinamica else = "+infoDinamica);
					}

					if (rset.getString(19).equals("")
							|| rset.getString(19).length() == 0) {
						vEPICisCita.setCDscMotivo(informacionDinamica);
					}
				} else
					vEPICisCita.setCDscMotivo(informacionDinamica);

				// System.out.println("Motivo = "+informacionDinamica);
				// System.out.println("Motivo2 = "+rset.getString(19));

				// Obtener descripcion de motivo
				v = dTDGRLMotivo.FindDsc(" WHERE GRLMotivo.cDscMotivo = '"
						+ informacionDinamica + "'");
				int x = 0;
				do {
					if (x >= v.size())
						break;
					vTVGRLMotivo = (TVGRLMotivo) v.elementAt(x);
					if (vTVGRLMotivo.getICveProceso() == 1)
						break;
					x++;
				} while (true);
				vEPICisCita.setICveMotivo(vTVGRLMotivo.getICveMotivo());
				if (vEPICisCita.getICveMotivo() == 0)
					vEPICisCita.setICveMotivo(1);

				// vEPICisCita.setCDscMotivo(rset.getString(19));

				// Asignando Puestos
				int puestoDinamica = 1;
				String puestoDina = "";
				String infoDinamica3 = "";
				String Puestos2[];
				// System.out.println("Puesto = "+rset.getString(20));

				if (rset.getString(20) != null) {
					if (!rset.getString(20).equals("")
							&& rset.getString(20).length() != 0) {
						if (autotrans >= 1) {
							vEPICisCita.setICvePuesto(7);
						} else {
							infoDinamica3 = rset
									.getString(20)
									.substring(0,
											rset.getString(20).length() - 1)
									.toString();
							// System.out.println("infoDinamica.length = "+infoDinamica3.length());
							if (infoDinamica3.length() >= 1) {
								Puestos = infoDinamica3.split("\\:");
								if (infoDinamica3.length() > 2) {
									// System.out.println("P = "+Puestos[1]);
									if (Puestos.length <= 2) {
										puestoDina = Puestos[1];
										cuenta = 0;
										for (int j = 0; puestoDina.length() > j; j++) {
											if (puestoDina.charAt(j) <= '0'
													&& puestoDina.charAt(j) >= '9') {
												cuenta++;
											}
										}
										// System.out.println("op1puesto = "+puestoDina);
										if (cuenta == 0)
											puestoDinamica = this
													.RegresaInt("SELECT ICVEPUESTO FROM GRLPUESTO WHERE ICVEPUESTOCIS = "
															+ puestoDina
																	.toString());
										else
											puestoDinamica = 1;
									} else {
										String sobra = "";
										sobra = Puestos[1];
										Puestos2 = sobra.split("\\|");
										puestoDina = Puestos2[0];
										// System.out.println("op1puesto = "+puestoDina);
										cuenta = 0;
										for (int j = 0; puestoDina.length() > j; j++) {
											if (puestoDina.charAt(j) <= '0'
													&& puestoDina.charAt(j) >= '9') {
												cuenta++;
											}
										}
										if (cuenta == 0)
											if (puestoDina.toString() != null)
												if (!puestoDina.toString()
														.equals(""))
													puestoDinamica = this
															.RegresaInt("SELECT ICVEPUESTO FROM GRLPUESTO WHERE ICVEPUESTOCIS = "
																	+ puestoDina
																			.toString());
												else
													puestoDinamica = 1;
									}
									// System.out.println(puestoDinamica);
									if (puestoDinamica >= 1)
										vEPICisCita
												.setICvePuesto(puestoDinamica);
									else
										vEPICisCita.setICvePuesto(1);
								}
							} else
								vEPICisCita.setICvePuesto(puestoDinamica);
						}
					}
				} else
					vEPICisCita.setICvePuesto(puestoDinamica);
				/*
				 * if(rset.getString(19).equals("") ||
				 * rset.getString(19).length()==0){
				 * vEPICisCita.setICvePuesto(puestoDinamica); }
				 */

				// System.out.println("Puesto = "+rset.getInt(20));
				// vEPICisCita.setICvePuesto(1);
				// Asignando Categoria

				// vEPICisCita.setICveCategoria(rset.getInt(21));
				// vEPICisCita.setICveMotivo(rset.getInt(21));

				vEPICisCita.setCColonia(rset.getString(21));
				vEPICisCita.setICveUniMed(rset.getInt(22));
				vEPICisCita.setICveModulo(rset.getInt(23));
				vEPICisCita.setCObservacion("Carga de citas");

				// Obtenemos la fecha de Naciomiento desde el RFC
				String fechaNacimiento = "01/01/1970";
				if (rset.getString(3).length() >= 10) {
					String datosFechaRFC = rset.getString(3).substring(4, 10);
					int esNumero = 0;
					cuenta = 0;
					for (int g = 0; datosFechaRFC.length() > g; g++) {
						if (datosFechaRFC.charAt(g) >= '0'
								&& datosFechaRFC.charAt(g) <= '9') {
							cuenta++;
						}
					}
					if (datosFechaRFC.length() == cuenta) {
						esNumero = 1;
					}

					// Fecha de nacimiento
					// System.out.println("esNumero = "+esNumero);
					if (esNumero == 1) {
						if (datosFechaRFC.length() == 6) {
							int anoNac = Integer.parseInt("19"
									+ datosFechaRFC.subSequence(0, 2)
											.toString());
							int anoAct = calendar.get(Calendar.YEAR);
							if ((anoAct - anoNac) > 100) {
								fechaNacimiento = datosFechaRFC.subSequence(4,
										6)
										+ "/"
										+ datosFechaRFC.subSequence(2, 4)
										+ "/"
										+ "20"
										+ datosFechaRFC.subSequence(0, 2);
							} else {
								fechaNacimiento = datosFechaRFC.subSequence(4,
										6)
										+ "/"
										+ datosFechaRFC.subSequence(2, 4)
										+ "/"
										+ "19"
										+ datosFechaRFC.subSequence(0, 2);
							}
						}
					}
				}
				// System.out.println("fechaNacimiento = "+fechaNacimiento);
				vEPICisCita.setDtNacimiento(f.getDateSQL(fechaNacimiento));

				// Clave personal
				v = dTDPERDatos.FindBySelector(" WHERE CRFC = '"
						+ rset.getString(3).toString() + "' AND "
						+ "CNOMBRE LIKE '%" + rset.getString(9).toString()
						+ "%' AND " + "CAPPATERNO LIKE '%"
						+ rset.getString(10).toString() + "%' AND "
						+ "CAPMATERNO LIKE '%" + rset.getString(11).toString()
						+ "%' ");
				if (v.isEmpty()) {
					vEPICisCita.setICveSituacion(1);
				} else {
					vEPICisCita.setICveSituacion(2);
					for (int j = 0; j < v.size(); j++)
						vTVPERDatos = (TVPERDatos) v.elementAt(j);
					vEPICisCita.setICvePersonal(vTVPERDatos.getICvePersonal());
				}

				// rsetPEM.close();
				vcEPICisCita.addElement(vEPICisCita);
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

				if (rsetPEM != null) {
					rsetPEM.close();
				}
				if (pstmtPEM != null) {
					pstmtPEM.close();
				}

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcEPICisCita;
		}
	}

	/**
	 * M�?©todo Insert
	 */
	public String insert(Connection conGral, int user, String fecha)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		// PreparedStatement pstmt = null;
		// PreparedStatement pstmtFind = null;
		PreparedStatement pstmtValida = null;
		// ResultSet rset = null;
		ResultSet rsetValida = null;
		Vector vcEPICisCita = new Vector();
		TVEPICisCita vEPICisCita;

		Calendar calendar = Calendar.getInstance();

		TVUsuario usuario;
		int contador = 0;
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

			vcEPICisCita = this.FindByAll(fecha, user);
			// TVEPICisCita vEPICisCita = (TVEPICisCita) obj;
			// System.out.println(vcEPICisCita);
			// System.out.println(vcEPICisCita.size());
			if (vcEPICisCita.size() > 0) {
				// Calculando Timestamp para registrar la carga de citas
				int anoAct = calendar.get(Calendar.YEAR);
				int mesAct = calendar.get(Calendar.MONTH) + 1;
				int diaAct = calendar.get(Calendar.DAY_OF_MONTH);
				java.util.Date utilDate = new java.util.Date(); // fecha actual
				long lnMilisegundos = utilDate.getTime();
				java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
						lnMilisegundos);
				String cSQL5 = "insert into EXPBITMOD "
						+ "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, ldictamen)"
						+ " values(0, 0, 10, '" + sqlTimestamp
						+ "', 'CARGA DE CITAS = " + fecha + "', " + user
						+ ", 0)";
				this.Sentencia(cSQL5);// Se cargo el registro de iniciacion de
										// la carga de citas del dia
				cSQL5 = "SELECT DTREALIZADO FROM EXPBITMOD WHERE {FN YEAR(DTREALIZADO)}="
						+ anoAct
						+ " AND {FN MONTH(DTREALIZADO)} = "
						+ mesAct
						+ " AND {FN DAY(DTREALIZADO)}= "
						+ diaAct
						+ " AND IOPERACION = 10 AND CDESCRIPCION = 'CARGA DE CITAS'";
				Timestamp = this.RegresaCad(cSQL5);
				// System.out.println("Timestamp = "+Timestamp);

				for (int i = 0; i < vcEPICisCita.size(); i++) {
					ResultSet rset = null;
					PreparedStatement pstmt = null;
					PreparedStatement pstmtFind = null;

					vEPICisCita = (TVEPICisCita) vcEPICisCita.get(i);

					int iConsecutivo = 0;
					int iExistencia = 0;
					String cSQLFind;
					String cRes;
					String cSQLValida = "";

					/*
					 * cSQLValida = "select iCvePersonal from EPICisCita " +
					 * "where iCvePersonal = ? and iCveUniMed = ? and dtFecha = ? and iCveModulo = ?"
					 * ; // and iCveModulo = ?";
					 * 
					 * pstmtValida = conn.prepareStatement(cSQLValida);
					 * pstmtValida.setInt(1, vEPICisCita.getICvePersonal()); //
					 * System.out.println(vEPICisCita.getICvePersonal());
					 * 
					 * pstmtValida.setInt(2, vEPICisCita.getICveUniMed()); //
					 * System.out.println(vEPICisCita.getICvePersonal());
					 * 
					 * pstmtValida.setDate(3, vEPICisCita.getDtFecha()); //
					 * System.out.println(vEPICisCita.getICvePersonal());
					 * 
					 * pstmtValida.setInt(4, vEPICisCita.getICveModulo()); //
					 * System.out.println(vEPICisCita.getICvePersonal());
					 * 
					 * rsetValida = pstmtValida.executeQuery();
					 * 
					 * while (rsetValida.next()) { iExistencia =
					 * rsetValida.getInt(1); }
					 * 
					 * //Validamos que la persona que realiza la cita no sea
					 * tercero int esTercero = 0;//No es tercero String
					 * cSQLValidaTerceros =
					 * "SELECT LPRIVADA FROM GRLUNIMED WHERE ICVEUNIMED =  " +
					 * vEPICisCita.getICveUniMed(); PreparedStatement
					 * pstmtValidaTerceros =
					 * conn.prepareStatement(cSQLValidaTerceros); ResultSet
					 * rsetValidaTerceros = pstmtValidaTerceros.executeQuery();
					 * while (rsetValidaTerceros.next()) { esTercero =
					 * rsetValidaTerceros.getInt(1); } if
					 * (vEPICisCita.getICveMotivo() == 5 && esTercero == 1) {//
					 * Si es REVALORACION y es TERCERO cSQLValidaTerceros =
					 * "SELECT A.LDICTAMEN, B.LDICTAMINADO, C.LPRIVADA, A.ICVEEXPEDIENTE, A.INUMEXAMEN, B.ICVEUNIMED  "
					 * + "FROM EXPEXAMCAT A " +
					 * "JOIN EXPEXAMAPLICA B on A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE "
					 * + "AND A.INUMEXAMEN = B.INUMEXAMEN "
					 * +" JOIN GRLUNIMED C ON B.ICVEUNIMED = C.ICVEUNIMED " +
					 * "WHERE A.ICVEEXPEDIENTE = " +
					 * vEPICisCita.getICvePersonal() + " AND B.ICVEPROCESO = 1 "
					 * + " AND B.LDICTAMINADO = 1 ORDER BY INUMEXAMEN"; //
					 * System.out.println(cSQLValidaTerceros); PreparedStatement
					 * pstmtValidaTerceros2 =
					 * conn.prepareStatement(cSQLValidaTerceros); ResultSet
					 * rsetValidaTerceros2 =
					 * pstmtValidaTerceros2.executeQuery(); int ldictamen =
					 * 1;//APTO int ldictaminado = 1;//DICTAMINADO int
					 * icveunimedEsTercero=0;//ES INTERNA DE LA SCT while
					 * (rsetValidaTerceros2.next()) {//Se quedara con los
					 * valores del ultimo examen por el ciclo ldictamen =
					 * rsetValidaTerceros2.getInt(1); ldictaminado =
					 * rsetValidaTerceros2.getInt(2); icveunimedEsTercero =
					 * rsetValidaTerceros2.getInt(3); //
					 * System.out.println(rsetValidaTerceros2.getInt(5)); } if
					 * (ldictamen == 0 && ldictaminado == 1&&
					 * icveunimedEsTercero==0 ) {//Si el ultimo examen fue
					 * DICTAMINADO y resulto NO APTO y fue por DICTAMINADO POR
					 * LA SCT // if (ldictamen == 0 && ldictaminado == 1) {//Si
					 * el ultimo examen fue DICTAMINADO y resulto NO APTO y fue
					 * por DICTAMINADO POR LA SCT cClave = "ERROR2"; return
					 * cClave; } } //FIN DE Validamos que la persona que realiza
					 * la cita no sea tercero
					 * 
					 * if (iExistencia == 0) {
					 */
					cSQLFind = "select max(iCveCita) "
							+ "from EPICisCita "
							+ "where iCveUniMed = ? and dtFecha = ? and iCveModulo = ?";
					pstmtFind = conn.prepareStatement(cSQLFind);
					// System.out.println(cSQLFind);
					pstmtFind.setInt(1, vEPICisCita.getICveUniMed());
					// System.out.println("unimed = "+vEPICisCita.getICveUniMed());
					pstmtFind.setDate(2, vEPICisCita.getDtFecha());
					// System.out.println("dtfecha = "+vEPICisCita.getDtFecha());
					pstmtFind.setInt(3, vEPICisCita.getICveModulo());
					// System.out.println("modulo = "+vEPICisCita.getICveModulo());

					rset = pstmtFind.executeQuery();
					while (rset.next()) {
						iConsecutivo = rset.getInt(1);
					}
					vEPICisCita.setICveCita(iConsecutivo + 1);

					pstmtFind.close();

					cSQL = "insert into EPICisCita("
							+ "iCveUniMed,"
							+ "dtFecha,"
							+ "iCveCita,"
							+ "cHora,"
							+ "cApPaterno,"
							+ "cApMaterno,"
							+ "cNombre,"
							+ "cGenero,"
							+ "dtNacimiento,"
							+ "cRFC,"
							+ "cCURP,"
							+ "iCvePaisNac,"
							+ "iCveEstadoNac,"
							+ "cExpediente,"
							+ "iCvePersonal,"
							+ "cCalle,"
							+ "cNumExt,"
							+ "cNumInt,"
							+ "cColonia,"
							+ "iCP,"
							+ "cCiudad,"
							+ "iCvePais,"
							+ "iCveEstado,"
							+ "iCveMunicipio,"
							+ "cTel,"
							+ "lCambioDir,"
							+ "iCveMdoTrans,"
							+ "iCvePuesto,"
							+ "iCveCategoria,"
							+ "iCveMotivo,"
							+ "cObservacion,"
							+ "iCveSituacion,"
							+ "iCveUsuCita,"
							+ "lRealizoExam,"
							+ "iCveUsuMCIS,"
							+ "lProdNoConf,"
							+ "iCveModulo"
							+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					pstmt = conn.prepareStatement(cSQL);
					if (vEPICisCita.getICveUniMed() >= 0)
						pstmt.setInt(1, vEPICisCita.getICveUniMed());
					else
						pstmt.setInt(1, 1);

					// System.out.println("1 = "+vEPICisCita.getICveUniMed());

					pstmt.setDate(2, vEPICisCita.getDtFecha());
					// System.out.println("2 = "+vEPICisCita.getDtFecha());

					pstmt.setInt(3, vEPICisCita.getICveCita());
					// System.out.println("3 = "+vEPICisCita.getICveCita());

					/*
					 * GregorianCalendar g = new GregorianCalendar(0, 0, 0,
					 * vEPICisCita.getIHora(), vEPICisCita.getIMinutos()); Time
					 * t = new Time(g.getTimeInMillis());
					 */
					if (vEPICisCita.getCHora() != null)
						pstmt.setString(4, "" + vEPICisCita.getCHora());
					else
						pstmt.setString(4, "08:00");
					// System.out.println("4 = "+vEPICisCita.getCHora());

					if (vEPICisCita.getCApPaterno() != null)
						if (vEPICisCita.getCApPaterno().length() > 50)
							pstmt.setString(5, vEPICisCita.getCApPaterno()
									.substring(0, 48));
						else
							pstmt.setString(5, vEPICisCita.getCApPaterno());
					else
						pstmt.setString(5, "ninguno");
					// System.out.println("5 = "+vEPICisCita.getCApPaterno());

					if (vEPICisCita.getCApMaterno() != null)
						if (vEPICisCita.getCApMaterno().length() > 50)
							pstmt.setString(6, vEPICisCita.getCApMaterno()
									.substring(0, 48));
						else
							pstmt.setString(6, vEPICisCita.getCApMaterno());
					else
						pstmt.setString(6, "");
					// System.out.println("6 = "+vEPICisCita.getCApMaterno());

					if (vEPICisCita.getCNombre() != null)
						if (vEPICisCita.getCNombre().length() > 80)
							pstmt.setString(7, vEPICisCita.getCNombre()
									.substring(0, 78));
						else
							pstmt.setString(7, vEPICisCita.getCNombre());
					else
						pstmt.setString(7, "");
					// System.out.println("7 = "+vEPICisCita.getCNombre());

					if (vEPICisCita.getCGenero() != null) {
						if (vEPICisCita.getCGenero().length() > 1)
							pstmt.setString(8, "M");
						else
							pstmt.setString(8, vEPICisCita.getCGenero());
						// System.out.println("8 contiene valor ");
					} else {
						pstmt.setString(8, "M");
						// System.out.println("8 es null ");
					}
					// System.out.println("8 = "+vEPICisCita.getCGenero());

					pstmt.setDate(9, vEPICisCita.getDtNacimiento());
					// System.out.println("9 = "+vEPICisCita.getDtNacimiento());

					if (vEPICisCita.getCRFC() != null) {
						if (vEPICisCita.getCRFC().length() > 13)
							pstmt.setString(10, vEPICisCita.getCRFC()
									.substring(0, 12));
						else
							pstmt.setString(10, vEPICisCita.getCRFC());
					} else
						pstmt.setString(10, "");
					// System.out.println("10 = "+vEPICisCita.getCRFC());

					if (vEPICisCita.getCCURP() != null) {
						// System.out.println("Tamaño del curp = "+vEPICisCita.getCRFC().length());
						if (vEPICisCita.getCCURP().length() > 17) {
							pstmt.setString(11, vEPICisCita.getCCURP()
									.substring(0, 17));
							// System.out.println("Truncando CURP = "+vEPICisCita.getCCURP().substring(0,
							// 17));
						} else
							pstmt.setString(11, vEPICisCita.getCCURP());
					} else
						pstmt.setString(11, "");
					// System.out.println("11 = "+vEPICisCita.getCCURP());

					pstmt.setInt(12, 1);// pstmt.setInt(12,
										// vEPICisCita.getICvePaisNac());

					if (vEPICisCita.getICveEstadoNac() >= 0)
						pstmt.setInt(13, vEPICisCita.getICveEstadoNac());
					else
						pstmt.setInt(13, 1);
					// System.out.println("13 = "+vEPICisCita.getICveEstadoNac());

					if (vEPICisCita.getCExpediente() != null) {
						if (vEPICisCita.getCExpediente().length() > 50)
							pstmt.setString(14, vEPICisCita.getCExpediente()
									.substring(0, 48));
						else
							pstmt.setString(14, vEPICisCita.getCExpediente());//
					} else
						pstmt.setString(14, "0");//
					// System.out.println("14 = "+vEPICisCita.getCExpediente());

					if (vEPICisCita.getICvePersonal() >= 0)
						pstmt.setInt(15, vEPICisCita.getICvePersonal());
					else
						pstmt.setInt(15, 0);
					// System.out.println("15 = "+vEPICisCita.getICvePersonal());

					if (vEPICisCita.getCCalle() != null) {
						if (vEPICisCita.getCCalle().length() > 100)
							pstmt.setString(16, vEPICisCita.getCCalle()
									.substring(0, 98));
						else
							pstmt.setString(16, vEPICisCita.getCCalle());
					} else
						pstmt.setString(16, vEPICisCita.getCCalle());
					// System.out.println("16 = "+vEPICisCita.getCCalle());

					if (vEPICisCita.getCNumExt() != null)
						if (vEPICisCita.getCNumExt().length() > 30)
							pstmt.setString(17, vEPICisCita.getCNumExt()
									.substring(0, 28));
						else
							pstmt.setString(17, vEPICisCita.getCNumExt());//
					else
						pstmt.setString(17, "");//
					// System.out.println("17 = "+vEPICisCita.getCNumExt());

					if (vEPICisCita.getCNumInt() != null) {
						if (vEPICisCita.getCNumInt().length() > 30)
							pstmt.setString(18, vEPICisCita.getCNumInt()
									.substring(0, 28));
						else
							pstmt.setString(18, vEPICisCita.getCNumInt());//
					} else
						pstmt.setString(18, "");//
					// System.out.println("18 = "+vEPICisCita.getCNumInt());

					if (vEPICisCita.getCColonia() != null) {
						if (vEPICisCita.getCColonia().length() > 100)
							pstmt.setString(19, vEPICisCita.getCColonia()
									.substring(0, 98));
						else
							pstmt.setString(19, vEPICisCita.getCColonia());

					} else
						pstmt.setString(19, "");
					// System.out.println("19 = "+vEPICisCita.getCColonia());

					if (vEPICisCita.getICP() >= 0)
						pstmt.setInt(20, vEPICisCita.getICP());
					else
						pstmt.setInt(20, 1);
					// System.out.println("20 = "+vEPICisCita.getICP());

					if (vEPICisCita.getCColonia() != null)
						if (vEPICisCita.getCColonia().length() > 100)
							pstmt.setString(21, vEPICisCita.getCColonia()
									.substring(0, 98));
						else
							pstmt.setString(21, vEPICisCita.getCColonia());
					else
						pstmt.setString(21, "");
					// System.out.println("21 = "+vEPICisCita.getCColonia());

					pstmt.setInt(22, 1);// pstmt.setInt(22,
										// vEPICisCita.getICvePais());

					if (vEPICisCita.getICveEstado() >= 0)
						pstmt.setInt(23, vEPICisCita.getICveEstado());
					else
						pstmt.setInt(23, 1);
					// System.out.println("23 = "+vEPICisCita.getICveEstado());

					if (vEPICisCita.getICveMunicipio() >= 0)
						pstmt.setInt(24, vEPICisCita.getICveMunicipio());
					else
						pstmt.setInt(24, 1);
					// System.out.println("24 = "+vEPICisCita.getICveMunicipio());

					if (vEPICisCita.getCTelefono() != null) {
						if (vEPICisCita.getCTelefono().length() > 20)
							pstmt.setString(25, vEPICisCita.getCTelefono()
									.substring(0, 18));
						else
							pstmt.setString(25, vEPICisCita.getCTelefono());
					} else
						pstmt.setString(25, "");
					// System.out.println("25 = "+vEPICisCita.getCTelefono());

					if (vEPICisCita.getLCambioDir() >= 0)
						pstmt.setInt(26, vEPICisCita.getLCambioDir());
					else
						pstmt.setInt(26, 1);
					// System.out.println("26 = "+vEPICisCita.getLCambioDir());

					if (vEPICisCita.getICveMdoTrans() > 0)
						pstmt.setInt(27, vEPICisCita.getICveMdoTrans());
					else
						pstmt.setInt(27, 2);
					// System.out.println("27 = "+vEPICisCita.getICveMdoTrans());

					if (vEPICisCita.getICvePuesto() > 0)
						pstmt.setInt(28, vEPICisCita.getICvePuesto());
					else {
						if (vEPICisCita.getICveMdoTrans() == 2)
							pstmt.setInt(28, 7);
						else
							pstmt.setInt(28, 1);
					}
					// System.out.println("28 = "+vEPICisCita.getICvePuesto());

					// Validar si es de Autotrasporte
					// Si lo es se aplicara la categoria 7

					if (vEPICisCita.getICveMdoTrans() >= 0) {
						if (vEPICisCita.getICveMdoTrans() == 2) {
							int catAutotrans = 7;
							pstmt.setInt(29, catAutotrans);
						} else {
							pstmt.setInt(29, vEPICisCita.getICveCategoria());
						}
					}
					// System.out.println("29 = "+vEPICisCita.getICveCategoria());

					if (vEPICisCita.getICveMotivo() >= 0)
						pstmt.setInt(30, vEPICisCita.getICveMotivo());
					else
						pstmt.setInt(30, 1);
					// System.out.println("30 = "+vEPICisCita.getICveMotivo());

					if (vEPICisCita.getCObservacion() != null) {
						if (vEPICisCita.getCObservacion().length() > 2000)
							pstmt.setString(31, vEPICisCita.getCObservacion()
									.substring(0, 1998));
						else
							pstmt.setString(31, vEPICisCita.getCObservacion());
					} else
						pstmt.setString(31, " ");
					// System.out.println("31 = "+vEPICisCita.getCObservacion());

					if (vEPICisCita.getICveSituacion() >= 0)
						pstmt.setInt(32, vEPICisCita.getICveSituacion());
					else
						pstmt.setInt(32, 2);
					// System.out.println("32 = "+vEPICisCita.getICveSituacion());

					if (user >= 0)
						pstmt.setInt(33, user);
					else
						pstmt.setInt(33, 71);
					// System.out.println("33 = "+user);

					if (vEPICisCita.getLRealizoExam() >= 0)
						pstmt.setInt(34, vEPICisCita.getLRealizoExam());
					else
						pstmt.setInt(34, 1);
					// System.out.println("34 = "+vEPICisCita.getLRealizoExam());

					if (vEPICisCita.getICveUsuMCIS() >= 0)
						pstmt.setInt(35, vEPICisCita.getICveUsuMCIS());
					else
						pstmt.setInt(35, 0);
					// System.out.println("35 = "+vEPICisCita.getICveUsuMCIS());

					if (vEPICisCita.getLProdNoConf() >= 0)
						pstmt.setInt(36, vEPICisCita.getLProdNoConf());
					else
						pstmt.setInt(36, 0);
					// System.out.println("36 = "+vEPICisCita.getLProdNoConf());

					if (vEPICisCita.getICveModulo() >= 0)
						pstmt.setInt(37, vEPICisCita.getICveModulo());
					else
						pstmt.setInt(37, 0);
					// System.out.println("37 = "+vEPICisCita.getICveModulo());

					pstmt.executeUpdate();

					cClave = "Carga Abortada";// + vEPICisCita.getICveCita();
					/*
					 * }terceros else { cClave = "ERROR"; }
					 */

					pstmt.close();
					rset.close();
					conn.commit();

					contador++;
					// System.out.println("contador = "+contador);
				}
				if (vcEPICisCita.size() == contador) {
					cSQL5 = "UPDATE EXPBITMOD SET LDICTAMEN = 1 "
							// + "WHERE DTREALIZADO='"+Timestamp+"' AND "
							+ "WHERE "
							+ "IOPERACION = 10 AND CDESCRIPCION = 'CARGA DE CITAS = "
							+ fecha + "'";
					this.Sentencia(cSQL5);
					// System.out.println("contador = "+contador);
					// System.out.println("vcEPICisCita.size() = "+vcEPICisCita.size());
					cClave = "CargaExitosa";
					// System.out.println("Contador y vector de citas son iguales");
				} else {
					cClave = "Carga Abortada";
					// System.out.println("Contador y vector de citas no son iguales");
				}
			} else {
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
				/*
				 * if (pstmt != null) { pstmt.close(); }
				 */
				/*
				 * if (pstmtFind != null) { pstmtFind.close(); }
				 */

				if (pstmtValida != null) {
					pstmtValida.close();
				}
				/*
				 * if (rset != null) { rset.close(); }
				 */
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
			return cClave;
		}
	}

	/**
	 * Metodo que regresa un entero
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

			// System.out.println("RegresaInt = "+SQL);
			pstmt = conn.prepareStatement(SQL.toString());

			rset = pstmt.executeQuery();
			// vPERDatos = new TVPERDatos();
			while (rset.next()) {
				if (rset.getString(1) != null)
					regreso = rset.getInt(1);
				else
					regreso = 0;
				// System.out.println(regreso);
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
	 * Metodo que regresa un entero
	 * 
	 * @Autor: AG SA
	 */
	public String RegresaCad(String SQL) throws DAOException {
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
			warn("RegresaCad", ex);
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
				warn("RegresaCad", ex2);
			}
			return regreso;
		}
	}

	/**
	 * Metodo update AG SA
	 */
	public String Sentencia(String cSQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// TVPERDatos vPERDatos = (TVPERDatos) obj;

			// conn.setAutoCommit(true);
			// conn.setTransactionIsolation(1);

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			pstmt.executeUpdate();
			cClave = "correcto";
			//pstmt.close();
			conn.commit();
			//conn.close();
			dbConn.closeConnection();
		} catch (Exception ex) {
			System.out.println("Error Sentencia");
			warn("update", ex);
		}finally {
			try {
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
		
		return cClave;
	}

	/**
	 * M�?©todo Insert
	 */
	public String insert2(Connection conGral, int user, String fecha)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		// PreparedStatement pstmt = null;
		// PreparedStatement pstmtFind = null;
		PreparedStatement pstmtValida = null;
		// ResultSet rset = null;
		ResultSet rsetValida = null;
		Vector vcEPICisCita = new Vector();
		TVEPICisCita vEPICisCita;

		Calendar calendar = Calendar.getInstance();

		TVUsuario usuario;
		int contador = 0;
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

			vcEPICisCita = this.FindByAll(fecha, user);
			// TVEPICisCita vEPICisCita = (TVEPICisCita) obj;
			// System.out.println(vcEPICisCita);
			// System.out.println(vcEPICisCita.size());
			if (vcEPICisCita.size() > 0) {
				// Calculando Timestamp para registrar la carga de citas
				/*
				 * int añoAct = calendar.get(Calendar.YEAR); int mesAct =
				 * calendar.get(Calendar.MONTH)+1; int diaAct =
				 * calendar.get(Calendar.DAY_OF_MONTH); java.util.Date utilDate
				 * = new java.util.Date(); //fecha actual long lnMilisegundos =
				 * utilDate.getTime(); java.sql.Timestamp sqlTimestamp = new
				 * java.sql.Timestamp(lnMilisegundos); String cSQL5 =
				 * "insert into EXPBITMOD " +
				 * "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, ldictamen)"
				 * +
				 * " values(0, 0, 10, '"+sqlTimestamp+"', 'CARGA DE CITAS = "+fecha
				 * +"', "+user+", 0)"; //this.Sentencia(cSQL5);// Se cargo el
				 * registro de iniciacion de la carga de citas del dia cSQL5 =
				 * "SELECT DTREALIZADO FROM EXPBITMOD WHERE {FN YEAR(DTREALIZADO)}="
				 * +añoAct+" AND {FN MONTH(DTREALIZADO)} = "+mesAct+
				 * " AND {FN DAY(DTREALIZADO)}= "+diaAct+
				 * " AND IOPERACION = 10 AND CDESCRIPCION = 'CARGA DE CITAS'";
				 * //Timestamp = this.RegresaCad(cSQL5);
				 * //System.out.println("Timestamp = "+Timestamp);
				 */

				for (int i = 0; i < vcEPICisCita.size(); i++) {
					ResultSet rset = null;
					PreparedStatement pstmt = null;
					PreparedStatement pstmtFind = null;

					vEPICisCita = (TVEPICisCita) vcEPICisCita.get(i);

					int iConsecutivo = 0;
					int iExistencia = 0;
					String cSQLFind;
					String cRes;
					String cSQLValida = "";
					String cSexo_DGIS = "";

					cSQLFind = "select max(iCveCita) "
							+ "from EPICisCita "
							+ "where iCveUniMed = ? and dtFecha = ? and iCveModulo = ?";
					pstmtFind = conn.prepareStatement(cSQLFind);
					// System.out.println(cSQLFind);
					pstmtFind.setInt(1, vEPICisCita.getICveUniMed());
					// System.out.println("unimed = "+vEPICisCita.getICveUniMed());
					pstmtFind.setDate(2, vEPICisCita.getDtFecha());
					// System.out.println("dtfecha = "+vEPICisCita.getDtFecha());
					pstmtFind.setInt(3, vEPICisCita.getICveModulo());
					// System.out.println("modulo = "+vEPICisCita.getICveModulo());

					rset = pstmtFind.executeQuery();
					while (rset.next()) {
						iConsecutivo = rset.getInt(1);
					}
					vEPICisCita.setICveCita(iConsecutivo + 1);

					pstmtFind.close();

					cSQL = "insert into EPICisCita("
							+ "iCveUniMed,"
							+ "dtFecha,"
							+ "iCveCita,"
							+ "cHora,"
							+ "cApPaterno,"
							+ "cApMaterno,"
							+ "cNombre,"
							+ "cGenero,"
							+ "dtNacimiento,"
							+ "cRFC,"
							+ "cCURP,"
							+ "iCvePaisNac,"
							+ "iCveEstadoNac,"
							+ "cExpediente,"
							+ "iCvePersonal,"
							+ "cCalle,"
							+ "cNumExt,"
							+ "cNumInt,"
							+ "cColonia,"
							+ "iCP,"
							+ "cCiudad,"
							+ "iCvePais,"
							+ "iCveEstado,"
							+ "iCveMunicipio,"
							+ "cTel,"
							+ "lCambioDir,"
							+ "iCveMdoTrans,"
							+ "iCvePuesto,"
							+ "iCveCategoria,"
							+ "iCveMotivo,"
							+ "cObservacion,"
							+ "iCveSituacion,"
							+ "iCveUsuCita,"
							+ "lRealizoExam,"
							+ "iCveUsuMCIS,"
							+ "lProdNoConf,"
							+ "iCveModulo,"
							+ "cSexo_DGIS "
							+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					pstmt = conn.prepareStatement(cSQL);
					if (vEPICisCita.getICveUniMed() >= 0)
						pstmt.setInt(1, vEPICisCita.getICveUniMed());
					else
						pstmt.setInt(1, 1);

					// System.out.println("1 = "+vEPICisCita.getICveUniMed());

					pstmt.setDate(2, vEPICisCita.getDtFecha());
					// System.out.println("2 = "+vEPICisCita.getDtFecha());

					pstmt.setInt(3, vEPICisCita.getICveCita());
					// System.out.println("3 = "+vEPICisCita.getICveCita());

					/*
					 * GregorianCalendar g = new GregorianCalendar(0, 0, 0,
					 * vEPICisCita.getIHora(), vEPICisCita.getIMinutos()); Time
					 * t = new Time(g.getTimeInMillis());
					 */
					if (vEPICisCita.getCHora() != null)
						pstmt.setString(4, "" + vEPICisCita.getCHora());
					else
						pstmt.setString(4, "08:00");
					// System.out.println("4 = "+vEPICisCita.getCHora());

					String Paterno = "";
					if (vEPICisCita.getCApPaterno() != null) {
						if (vEPICisCita.getCApPaterno().length() > 50) {
							pstmt.setString(5, vEPICisCita.getCApPaterno()
									.substring(0, 48));
							Paterno = ""
									+ vEPICisCita.getCApPaterno().substring(0,
											48);
						} else {
							pstmt.setString(5, vEPICisCita.getCApPaterno());
							Paterno = "" + vEPICisCita.getCApPaterno();
						}
					} else {
						pstmt.setString(5, "ninguno");
						Paterno = "ninguno";
					}
					// System.out.println("5 = "+vEPICisCita.getCApPaterno());

					String Materno = "";
					if (vEPICisCita.getCApMaterno() != null)
						if (vEPICisCita.getCApMaterno().length() > 50) {
							pstmt.setString(6, vEPICisCita.getCApMaterno()
									.substring(0, 48));
							Materno = ""
									+ vEPICisCita.getCApMaterno().substring(0,
											48);
						} else {
							pstmt.setString(6, vEPICisCita.getCApMaterno());
							Materno = "" + vEPICisCita.getCApMaterno();
						}
					else
						pstmt.setString(6, "");
					// System.out.println("6 = "+vEPICisCita.getCApMaterno());

					String Nombre = "";
					if (vEPICisCita.getCNombre() != null) {
						if (vEPICisCita.getCNombre().length() > 80) {
							pstmt.setString(7, vEPICisCita.getCNombre()
									.substring(0, 78));
							Nombre = vEPICisCita.getCNombre().substring(0, 78)
									+ "";
						} else {
							pstmt.setString(7, vEPICisCita.getCNombre());
							Nombre = "" + vEPICisCita.getCNombre();
						}
					} else {
						pstmt.setString(7, "");
					}
					// System.out.println("7 = "+vEPICisCita.getCNombre());

					if (vEPICisCita.getCGenero() != null) {
						if (vEPICisCita.getCGenero().length() > 1){
							pstmt.setString(8, "M");
							cSexo_DGIS = "M";
						}else{
							pstmt.setString(8, vEPICisCita.getCGenero());
							cSexo_DGIS = ""+vEPICisCita.getCGenero();
						}
						// System.out.println("8 contiene valor ");
					} else {
						pstmt.setString(8, "M");
						// System.out.println("8 es null ");
					}
					// System.out.println("8 = "+vEPICisCita.getCGenero());

					pstmt.setDate(9, vEPICisCita.getDtNacimiento());
					// System.out.println("9 = "+vEPICisCita.getDtNacimiento());

					String RFC = "";
					if (vEPICisCita.getCRFC() != null) {
						if (vEPICisCita.getCRFC().length() > 13) {
							pstmt.setString(10, vEPICisCita.getCRFC()
									.substring(0, 12));
							RFC = vEPICisCita.getCRFC().substring(0, 12) + "";
						} else {
							pstmt.setString(10, vEPICisCita.getCRFC());
							RFC = "" + vEPICisCita.getCRFC();
						}
					} else {
						pstmt.setString(10, "");
					}
					// System.out.println("10 = "+vEPICisCita.getCRFC());

					if (vEPICisCita.getCCURP() != null) {
						// System.out.println("Tamaño del curp = "+vEPICisCita.getCRFC().length());
						if (vEPICisCita.getCCURP().length() > 17) {
							pstmt.setString(11, vEPICisCita.getCCURP()
									.substring(0, 17));
							// System.out.println("Truncando CURP = "+vEPICisCita.getCCURP().substring(0,
							// 17));
						} else
							pstmt.setString(11, vEPICisCita.getCCURP());
					} else
						pstmt.setString(11, "");
					// System.out.println("11 = "+vEPICisCita.getCCURP());

					pstmt.setInt(12, 1);// pstmt.setInt(12,
										// vEPICisCita.getICvePaisNac());

					if (vEPICisCita.getICveEstadoNac() >= 0)
						pstmt.setInt(13, vEPICisCita.getICveEstadoNac());
					else
						pstmt.setInt(13, 1);
					// System.out.println("13 = "+vEPICisCita.getICveEstadoNac());

					if (vEPICisCita.getCExpediente() != null) {
						if (vEPICisCita.getCExpediente().length() > 50)
							pstmt.setString(14, vEPICisCita.getCExpediente()
									.substring(0, 48));
						else
							pstmt.setString(14, vEPICisCita.getCExpediente());//
					} else
						pstmt.setString(14, "0");//
					// System.out.println("14 = "+vEPICisCita.getCExpediente());

					if (vEPICisCita.getICvePersonal() >= 0)
						pstmt.setInt(15, vEPICisCita.getICvePersonal());
					else
						pstmt.setInt(15, 0);
					// System.out.println("15 = "+vEPICisCita.getICvePersonal());

					if (vEPICisCita.getCCalle() != null) {
						if (vEPICisCita.getCCalle().length() > 100)
							pstmt.setString(16, vEPICisCita.getCCalle()
									.substring(0, 98));
						else
							pstmt.setString(16, vEPICisCita.getCCalle());
					} else
						pstmt.setString(16, vEPICisCita.getCCalle());
					// System.out.println("16 = "+vEPICisCita.getCCalle());

					if (vEPICisCita.getCNumExt() != null)
						if (vEPICisCita.getCNumExt().length() > 30)
							pstmt.setString(17, vEPICisCita.getCNumExt()
									.substring(0, 28));
						else
							pstmt.setString(17, vEPICisCita.getCNumExt());//
					else
						pstmt.setString(17, "");//
					// System.out.println("17 = "+vEPICisCita.getCNumExt());

					if (vEPICisCita.getCNumInt() != null) {
						if (vEPICisCita.getCNumInt().length() > 30)
							pstmt.setString(18, vEPICisCita.getCNumInt()
									.substring(0, 28));
						else
							pstmt.setString(18, vEPICisCita.getCNumInt());//
					} else
						pstmt.setString(18, "");//
					// System.out.println("18 = "+vEPICisCita.getCNumInt());

					if (vEPICisCita.getCColonia() != null) {
						if (vEPICisCita.getCColonia().length() > 100)
							pstmt.setString(19, vEPICisCita.getCColonia()
									.substring(0, 98));
						else
							pstmt.setString(19, vEPICisCita.getCColonia());

					} else
						pstmt.setString(19, "");
					// System.out.println("19 = "+vEPICisCita.getCColonia());

					if (vEPICisCita.getICP() >= 0)
						pstmt.setInt(20, vEPICisCita.getICP());
					else
						pstmt.setInt(20, 1);
					// System.out.println("20 = "+vEPICisCita.getICP());

					if (vEPICisCita.getCColonia() != null)
						if (vEPICisCita.getCColonia().length() > 100)
							pstmt.setString(21, vEPICisCita.getCColonia()
									.substring(0, 98));
						else
							pstmt.setString(21, vEPICisCita.getCColonia());
					else
						pstmt.setString(21, "");
					// System.out.println("21 = "+vEPICisCita.getCColonia());

					pstmt.setInt(22, 1);// pstmt.setInt(22,
										// vEPICisCita.getICvePais());

					if (vEPICisCita.getICveEstado() >= 0)
						pstmt.setInt(23, vEPICisCita.getICveEstado());
					else
						pstmt.setInt(23, 1);
					// System.out.println("23 = "+vEPICisCita.getICveEstado());

					if (vEPICisCita.getICveMunicipio() >= 0)
						pstmt.setInt(24, vEPICisCita.getICveMunicipio());
					else
						pstmt.setInt(24, 1);
					// System.out.println("24 = "+vEPICisCita.getICveMunicipio());

					if (vEPICisCita.getCTelefono() != null) {
						if (vEPICisCita.getCTelefono().length() > 20)
							pstmt.setString(25, vEPICisCita.getCTelefono()
									.substring(0, 18));
						else
							pstmt.setString(25, vEPICisCita.getCTelefono());
					} else
						pstmt.setString(25, "");
					// System.out.println("25 = "+vEPICisCita.getCTelefono());

					if (vEPICisCita.getLCambioDir() >= 0)
						pstmt.setInt(26, vEPICisCita.getLCambioDir());
					else
						pstmt.setInt(26, 1);
					// System.out.println("26 = "+vEPICisCita.getLCambioDir());

					if (vEPICisCita.getICveMdoTrans() > 0)
						pstmt.setInt(27, vEPICisCita.getICveMdoTrans());
					else
						pstmt.setInt(27, 2);
					// System.out.println("27 = "+vEPICisCita.getICveMdoTrans());

					if (vEPICisCita.getICvePuesto() > 0)
						pstmt.setInt(28, vEPICisCita.getICvePuesto());
					else {
						if (vEPICisCita.getICveMdoTrans() == 2)
							pstmt.setInt(28, 7);
						else
							pstmt.setInt(28, 1);
					}

					// Validar si es de Autotrasporte
					// Si lo es se aplicara la categoria 7

					if (vEPICisCita.getICveMdoTrans() >= 0) {
						if (vEPICisCita.getICveMdoTrans() == 2) {
							int catAutotrans = 7;
							pstmt.setInt(29, catAutotrans);
						} else {
							pstmt.setInt(29, vEPICisCita.getICveCategoria());
						}
					}

					if (vEPICisCita.getICveMotivo() >= 0)
						pstmt.setInt(30, vEPICisCita.getICveMotivo());
					else
						pstmt.setInt(30, 1);
					// System.out.println("30 = "+vEPICisCita.getICveMotivo());

					if (vEPICisCita.getCObservacion() != null) {
						if (vEPICisCita.getCObservacion().length() > 2000)
							pstmt.setString(31, vEPICisCita.getCObservacion()
									.substring(0, 1998));
						else
							pstmt.setString(31, vEPICisCita.getCObservacion());
					} else
						pstmt.setString(31, " ");
					// System.out.println("31 = "+vEPICisCita.getCObservacion());

					if (vEPICisCita.getICveSituacion() >= 0)
						pstmt.setInt(32, vEPICisCita.getICveSituacion());
					else
						pstmt.setInt(32, 2);
					// System.out.println("32 = "+vEPICisCita.getICveSituacion());

					if (user >= 0)
						pstmt.setInt(33, user);
					else
						pstmt.setInt(33, 71);
					// System.out.println("33 = "+user);

					if (vEPICisCita.getLRealizoExam() >= 0)
						pstmt.setInt(34, vEPICisCita.getLRealizoExam());
					else
						pstmt.setInt(34, 1);
					// System.out.println("34 = "+vEPICisCita.getLRealizoExam());

					if (vEPICisCita.getICveUsuMCIS() >= 0)
						pstmt.setInt(35, vEPICisCita.getICveUsuMCIS());
					else
						pstmt.setInt(35, 0);
					// System.out.println("35 = "+vEPICisCita.getICveUsuMCIS());

					if (vEPICisCita.getLProdNoConf() >= 0)
						pstmt.setInt(36, vEPICisCita.getLProdNoConf());
					else
						pstmt.setInt(36, 0);
					// System.out.println("36 = "+vEPICisCita.getLProdNoConf());

					if (vEPICisCita.getICveModulo() >= 0)
						pstmt.setInt(37, vEPICisCita.getICveModulo());
					else
						pstmt.setInt(37, 0);
					// System.out.println("37 = "+vEPICisCita.getICveModulo());

					///Se agrego el campo relacionado al Campo Sexo_DGIS 
					pstmt.setString(38, cSexo_DGIS);
					
					
					int registrado = this
							.RegresaInt("SELECT COUNT (icvecita) FROM EPICISCITA WHERE "
									+ "CRFC='"
									+ RFC
									+ "' AND "
									+ "CNOMBRE = '"
									+ Nombre
									+ "' AND "
									+ "CAPPATERNO = '"
									+ Paterno
									+ "' AND "
									+ "CAPMATERNO = '"
									+ Materno
									+ "' AND "
									+ "DTFECHA='"
									+ fecha
									+ "'");

					if (registrado == 0)
						pstmt.executeUpdate();

					cClave = "Carga Abortada";// + vEPICisCita.getICveCita();
					/*
					 * }terceros else { cClave = "ERROR"; }
					 */

					pstmt.close();
					rset.close();

					if (registrado == 0)
						conn.commit();

					contador++;
					// System.out.println("contador = "+contador);
				}
				if (vcEPICisCita.size() == contador) {
					String cSQL5 = "UPDATE EXPBITMOD SET LDICTAMEN = 1 "
							// + "WHERE DTREALIZADO='"+Timestamp+"' AND "
							+ "WHERE "
							+ "IOPERACION = 10 AND CDESCRIPCION = 'CARGA DE CITAS = "
							+ fecha + "'";
					this.Sentencia(cSQL5);
					// System.out.println("contador = "+contador);
					// System.out.println("vcEPICisCita.size() = "+vcEPICisCita.size());
					cClave = "CargaExitosa";
					// System.out.println("Contador y vector de citas son iguales");
				} else {
					cClave = "Carga Abortada";
					// System.out.println("Contador y vector de citas no son iguales");
				}
			} else {
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
			return cClave;
		}
	}

	/**
	 * M�?©todo Insert Se insertaran a la tabla de citas todos aquellos
	 * transfronterizos
	 */
	public String insertSIAF(Connection conGral, int user, String fecha)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;

		PreparedStatement pstmtValida = null;
		ResultSet rsetValida = null;
		Vector vcEPICisCita = new Vector();
		TVEPICisCita vEPICisCita;

		Calendar calendar = Calendar.getInstance();

		TVUsuario usuario;
		int contador = 0;

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

			vcEPICisCita = this.FindByAllSIAF(fecha, user);

			if (vcEPICisCita.size() > 0) {

				for (int i = 0; i < vcEPICisCita.size(); i++) {
					ResultSet rset = null;
					PreparedStatement pstmt = null;
					PreparedStatement pstmtFind = null;

					// System.out.println("#################   "+i+"   ##################");

					vEPICisCita = (TVEPICisCita) vcEPICisCita.get(i);

					int iConsecutivo = 0;
					int iExistencia = 0;
					String cSQLFind;
					String cRes;
					String cSQLValida = "";

					String nom = "";
					String app = "";
					String amp = "";
					String ctel = "";
					String ccol = "";
					String ccdad = "";
					String date = fecha.subSequence(6, 10) + "-"
							+ fecha.subSequence(3, 5) + "-"
							+ fecha.subSequence(0, 2) + "";

					if (vEPICisCita.getCApPaterno().length() > 50) {
						app = "" + vEPICisCita.getCApPaterno().substring(0, 48);
					} else {
						app = "" + vEPICisCita.getCApPaterno();
					}
					if (vEPICisCita.getCApMaterno().length() > 50) {
						amp = "" + vEPICisCita.getCApMaterno().substring(0, 48);
					} else {
						amp = "" + vEPICisCita.getCApMaterno();
					}
					if (vEPICisCita.getCNombre().length() > 78) {
						nom = "" + vEPICisCita.getCNombre().substring(0, 78);
					} else {
						nom = "" + vEPICisCita.getCNombre();
					}
					if (vEPICisCita.getCTelefono().length() > 18) {
						ctel = "" + vEPICisCita.getCTelefono().substring(0, 18);
					} else {
						ctel = "" + vEPICisCita.getCTelefono();
					}
					if (vEPICisCita.getCColonia().length() > 98) {
						ccol = "" + vEPICisCita.getCColonia().substring(0, 98);
					} else {
						ccol = "" + vEPICisCita.getCColonia();
					}
					if (vEPICisCita.getCCiudad().length() > 98) {
						ccdad = "" + vEPICisCita.getCCiudad().substring(0, 98);
					} else {
						ccdad = "" + vEPICisCita.getCCiudad();
					}

					cSQL = "insert into EPICisCita(" + "iCveUniMed,"
							+ "dtFecha," + "iCveCita," + "cHora,"
							+ "cApPaterno," + "cApMaterno," + "cNombre,"
							+ "cGenero," + "dtNacimiento," + "cRFC," + "cCURP,"
							+ "iCvePaisNac," + "iCveEstadoNac,"
							+ "cExpediente," + "iCvePersonal," + "cCalle,"
							+ "cNumExt," + "cNumInt," + "cColonia," + "iCP,"
							+ "cCiudad," + "iCvePais," + "iCveEstado,"
							+ "iCveMunicipio," + "cTel," + "lCambioDir,"
							+ "iCveMdoTrans," + "iCvePuesto,"
							+ "iCveCategoria," + "iCveMotivo,"
							+ "cObservacion," + "iCveSituacion,"
							+ "iCveUsuCita," + "lRealizoExam," + "iCveUsuMCIS,"
							+ "lProdNoConf," + "iCveModulo" + ")values("
							+ vEPICisCita.getICveUniMed()
							+ ",'"
							+ date
							+ "',"
							+ // vEPICisCita.getDtFecha()+","+
							vEPICisCita.getICveCita()
							+ ",'"
							+ vEPICisCita.getCHora()
							+ "','"
							+ app
							+ "','"
							+ amp
							+ "','"
							+ nom
							+ "','"
							+ vEPICisCita.getCGenero()
							+ "',"
							+ "'1970-01-01','"
							+ // vEPICisCita.getDtNacimiento()+","+
							vEPICisCita.getCRFC()
							+ "','"
							+ vEPICisCita.getCCURP()
							+ "',"
							+ vEPICisCita.getICvePais()
							+ ","
							+ vEPICisCita.getICveEstado()
							+ ",'"
							+ vEPICisCita.getCExpediente()
							+ "',"
							+ vEPICisCita.getICvePersonal()
							+ ",'"
							+ vEPICisCita.getCCalle()
							+ "','"
							+ vEPICisCita.getCNumExt()
							+ "','"
							+ vEPICisCita.getCNumInt()
							+ "','"
							+ ccol
							+ "',"
							+ vEPICisCita.getICP()
							+ ",'"
							+ ccdad
							+ "',"
							+ vEPICisCita.getICvePaisNac()
							+ ","
							+ vEPICisCita.getICveEstadoNac()
							+ ","
							+ vEPICisCita.getICveMunicipio()
							+ ",'"
							+ vEPICisCita.getCTelefono()
							+ "',"
							+ vEPICisCita.getLCambioDir()
							+ ","
							+ vEPICisCita.getICveMdoTrans()
							+ ","
							+ vEPICisCita.getICvePuesto()
							+ ","
							+ vEPICisCita.getICveCategoria()
							+ ","
							+ vEPICisCita.getICveMotivo()
							+ ",'"
							+ vEPICisCita.getCObservacion()
							+ "',"
							+ vEPICisCita.getICveSituacion()
							+ ","
							+ vEPICisCita.getICveUsuCita()
							+ ","
							+ vEPICisCita.getLRealizoExam()
							+ ","
							+ vEPICisCita.getICveUsuMCIS()
							+ ","
							+ vEPICisCita.getLProdNoConf()
							+ ","
							+ vEPICisCita.getICveModulo() + ")";
					// System.out.println(cSQL);

					int registrado = this
							.RegresaInt("SELECT COUNT (icvecita) FROM EPICISCITA WHERE "
									+ "CRFC='"
									+ vEPICisCita.getCRFC()
									+ "' AND "
									+ "CNOMBRE = '"
									+ vEPICisCita.getCNombre()
									+ "' AND "
									+ "CAPPATERNO = '"
									+ vEPICisCita.getCApPaterno()
									+ "' AND "
									+ "CAPMATERNO = '"
									+ vEPICisCita.getCApMaterno()
									+ "' AND "
									+ "DTFECHA='" + date + "'");

					pstmt = conn.prepareStatement(cSQL);

					if (registrado == 0)
						pstmt.executeUpdate();

					pstmt.close();
					// rset.close();

					if (registrado == 0)
						conn.commit();

					contador++;
					// System.out.println("contador = "+contador);
				}
				if (vcEPICisCita.size() == contador) {
					String cSQL5 = "UPDATE EXPBITMOD SET LDICTAMEN = 1 "
							// + "WHERE DTREALIZADO='"+Timestamp+"' AND "
							+ "WHERE "
							+ "IOPERACION = 11 AND CDESCRIPCION = 'CARGA DE CITAS SIAF = "
							+ fecha + "'";
					this.Sentencia(cSQL5);
					// System.out.println("contador = "+contador);
					// System.out.println("vcEPICisCita.size() = "+vcEPICisCita.size());
					cClave = "CargaExitosa";
					// System.out.println("Contador y vector de citas son iguales");
				} else {
					cClave = "Carga Abortada";
					// System.out.println("Contador y vector de citas no son iguales");
				}
			} else {
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
			return cClave;
		}
	}

	/**
	 * M�?©todo Find By All
	 */
	public Vector FindByAllSIAF(String fecha, int user) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		Vector vcEPICisCita = new Vector();
		TDPERDatos dTDPERDatos = new TDPERDatos();
		TVPERDatos vTVPERDatos = new TVPERDatos();
		Vector v = new Vector();
		TFechas f = new TFechas();
		Calendar calendar = Calendar.getInstance();
		String date = fecha.subSequence(6, 10) + "-" + fecha.subSequence(3, 5)
				+ "-" + fecha.subSequence(0, 2) + "";

		// Valores a capturar
		int tramite = 0;
		int tramite2 = 0;
		int unimed = 1;
		;
		int modulo = 14;
		String FecCita = "";
		String FecNac = "1970-01-01";
		int icp = 0;
		int NoConf = 0;
		String colonia = "";
		String tel = "";
		String nombre = "";
		String paterno = "";
		String materno = "";
		String campo = "";
		int mat = 0;
		int pat = 0;
		int nom = 0;

		try {
			dbConn = new DbConnection(dataSourceName3);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLPEM = "";
			String cCita = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVEPICisCita vEPICisCita;
			TDGRLMotivo dTDGRLMotivo = new TDGRLMotivo();
			TVGRLMotivo vTVGRLMotivo = new TVGRLMotivo();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "SELECT PERTIPOPERMISIONAR.CTIPOPERMISIONARIO,                     "
					+ "INTTIPOTRAMITE.cDscTipoTramite,                     "
					+ "INTTRAMITES.ICVETRAMITE,                     " // Clave
																		// de
																		// cita
					+ "INTTRAMITES.ICONSECUTIVO,                    "
					+ "INTTRAMITES.ICVETIPOPERMISIONA,                    "
					+ "INTTRAMITES.ICVETIPOTRAMITE,                    "
					+ "TSREGISTRO,                    "
					+ "tsfin,                    " // fecha de la cita
					+ "INTTRAMITEDETALLE.CCAMPO,                    "
					+ "INTTRAMITEDETALLE.CVALOR1,                    "
					+ "INTTRAMITEDETALLE.CVALOR2                     "
					+ "FROM INTTRAMITES                    "
					+ "join PERTIPOPERMISIONAR on INTTRAMITES.ICVETIPOPERMISIONA =  PERTIPOPERMISIONAR.ICVETIPOPERMISIONA                    "
					+ "join INTTIPOTRAMITE on INTTRAMITES.ICVETIPOTRAMITE = INTTIPOTRAMITE.ICVETIPOTRAMITE                    "
					+ "join INTTRAMITEDETALLE on INTTRAMITES.ICVETRAMITE = INTTRAMITEDETALLE.ICVETRAMITE                                           "
					+ "and INTTRAMITES.ICONSECUTIVO = INTTRAMITEDETALLE.ICONSECUTIVO                    "
					+ "where INTTIPOTRAMITE.ICVETIPOTRAMITE = 28                      "
					+ "and date(tsfin) >= '"
					+ date
					+ "'                    "
					+ "order by PERTIPOPERMISIONAR.CTIPOPERMISIONARIO, "
					+ "INTTIPOTRAMITE.cDscTipoTramite, "
					+ "INTTRAMITES.ICVETRAMITE, "
					+ "ICONSECUTIVO, "
					+ "INTTRAMITEDETALLE.CCAMPO, "
					+ "INTTRAMITEDETALLE.IORDEN ";

			// System.out.println(cSQL);
			// System.out.println("siaf = "+date);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			int contador = 0;
			int asignar = 0;
			int asignar2 = 0;

			while (rset.next()) {
				vEPICisCita = new TVEPICisCita();

				// Tramite y clave cita
				tramite = rset.getInt(3);
				FecCita = rset.getString(8);
				campo = rset.getString(9);

				if (campo != null) {
					if (campo.equals("CDomicilio1")) {
						if (rset.getString(10) != null)
							colonia = rset.getString(10);
					}

					if (campo.equals("cTelefono")) {
						if (rset.getString(10) != null)
							tel = rset.getString(10);
					}

					if (campo.equals("cNombre")) {
						if (rset.getString(10) != null)
							nombre = rset.getString(10);
						nom++;
					}

					if (campo.equals("cApPaterno")) {
						if (rset.getString(10) != null)
							paterno = rset.getString(10);
						pat++;
					}

					if (campo.equals("cApMaterno")) {
						if (rset.getString(10) != null)
							materno = rset.getString(10);
						mat++;
					}

					if (campo.equals("IREGISTRODOT")) {
						asignar++;
					}

				}

				// System.out.println("asignar = " + asignar + "   asignar2 = "
				// + asignar2);

				if (asignar > asignar2) {
					// System.out.println("Asignado");

					if (asignar > nom)
						nombre = "";
					if (asignar > pat)
						paterno = "";
					if (asignar > mat)
						materno = "";

					vEPICisCita.setICveUniMed(unimed);
					vEPICisCita.setICveModulo(modulo);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date d = sdf.parse(FecCita.substring(0, 10));
					java.sql.Date x = new java.sql.Date(d.getTime());
					vEPICisCita.setDtFecha(x);

					tramite2 = tramite2++;
					vEPICisCita.setICveCita(tramite2);

					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date n = sdf2.parse(FecNac);
					java.sql.Date x2 = new java.sql.Date(n.getTime());
					vEPICisCita.setDtNacimiento(x2);

					vEPICisCita.setICP(icp);
					vEPICisCita.setCNombre(nombre);
					vEPICisCita.setCApPaterno(paterno);
					vEPICisCita.setCApMaterno(materno);
					vEPICisCita.setICveUsuCita(user);
					vEPICisCita.setLProdNoConf(0);
					vEPICisCita.setCColonia(colonia);
					vEPICisCita.setCGenero("M");
					vEPICisCita.setCCiudad(colonia);
					vEPICisCita.setCHora("08:00:00");
					vEPICisCita.setCTelefono(tel);
					vEPICisCita.setICvePais(8);
					vEPICisCita.setICveEstado(0);
					vEPICisCita.setICvePaisNac(8);
					vEPICisCita.setICveEstadoNac(0);
					vEPICisCita.setICveMunicipio(0);
					vEPICisCita.setICveMdoTrans(2);
					vEPICisCita.setICveMotivo(1);
					vEPICisCita.setICvePuesto(7);
					vEPICisCita.setICveSituacion(1);
					vEPICisCita.setICveCategoria(7);
					vEPICisCita.setCRFC("TRAN111111");
					vEPICisCita.setCCURP("");
					vEPICisCita.setCObservacion("");
					vEPICisCita.setCExpediente("");
					vEPICisCita.setCCalle("");
					vEPICisCita.setCNumExt("0");
					vEPICisCita.setCNumInt("0");
					vEPICisCita.setICveCita(tramite);

					// Clave personal
					v = dTDPERDatos
							.FindBySelector(" WHERE CRFC = 'TRAN111111' AND "
									+ "CNOMBRE LIKE '%" + nombre + "%' AND "
									+ "CAPPATERNO LIKE '%" + paterno
									+ "%' AND " + "CAPMATERNO LIKE '%"
									+ materno + "%' ");
					if (v.isEmpty()) {
						vEPICisCita.setICveSituacion(1);
					} else {
						vEPICisCita.setICveSituacion(2);
						for (int j = 0; j < v.size(); j++)
							vTVPERDatos = (TVPERDatos) v.elementAt(j);
						vEPICisCita.setICvePersonal(vTVPERDatos
								.getICvePersonal());
					}

					vcEPICisCita.addElement(vEPICisCita);
					asignar2 = asignar;
					nom = asignar;
					mat = asignar;
					pat = asignar;
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

				if (rsetPEM != null) {
					rsetPEM.close();
				}
				if (pstmtPEM != null) {
					pstmtPEM.close();
				}

				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcEPICisCita;
		}
	}

}
